package systems.rine.pb.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.IOUtils;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.google.common.collect.Multiset.Entry;
import com.google.common.util.concurrent.RateLimiter;

public class HTTPRequestCache {
	private static DB db;
	private static ConcurrentMap<String, String> map;
	private static RateLimiter limiter;

	static {
		db = DBMaker.fileDB("requestcache.db").transactionEnable().make();
		map = db.hashMap("map", Serializer.STRING, Serializer.STRING).createOrOpen();
		limiter = RateLimiter.create(5);
	}

	public static String get(String url) {
		String value = map.get(url);
		if (value == null) {
			long time = System.currentTimeMillis();
			value = requestFromServer(url);
			System.out.println("download time: " + (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			map.put(url, value);
			System.out.println("cache time: " + (System.currentTimeMillis() - time));
		}
		return value;
	}

	private static String requestFromServer(String urlpath) {
		String langString;
		if (urlpath.contains("?")) {
			langString = "&lang=en";
		} else {
			langString = "?lang=en";
		}

		URLConnection connection = null;
		try {
			limiter.acquire();
			URL url = new URL("https://api.guildwars2.com/v2" + urlpath + langString);
			connection = url.openConnection();
			connection.connect();
			return new String(IOUtils.toByteArray(connection.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			int i = 0;
			String field = null;
			do {
				System.out.print(connection.getHeaderFieldKey(i) + ": ");
				System.out.println(connection.getHeaderField(i++));
			}while(field != null);
			return "";
		}
	}

	public static void save() {
		db.commit();
	}

}