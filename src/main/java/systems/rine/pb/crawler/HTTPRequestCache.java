package systems.rine.pb.crawler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import com.google.common.util.concurrent.RateLimiter;

public class HTTPRequestCache {
	private static final Logger logger = LogManager.getLogger(HTTPRequestCache.class);
	private static DB db;
	private static ConcurrentMap<String, String> map;
	private static RateLimiter limiter;

	static {
		db = DBMaker.fileDB("requestcache.db").transactionEnable().make();
		map = db.hashMap("map", Serializer.STRING, Serializer.STRING).createOrOpen();
		limiter = RateLimiter.create(5);
	}
	
	public static String get(String url) {
		return get(url, true);
	}
	
	public static String get(String url, boolean cache) {
		String value = map.get(url);
		if (value == null) {
			long time = System.currentTimeMillis();
			logger.info("Downloading " + url + " ...");
			value = requestFromServer(url);
			if(value == null) {
				logger.warn("Download failed");
			}else {
				logger.info("Download done in: " + (System.currentTimeMillis() - time) + "ms");
			}
			
			if(cache && value != null) {
				map.put(url, value);	
			}			
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
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			int i = 0;
			String field = null;
			do {
				System.out.print(connection.getHeaderFieldKey(i) + ": ");
				System.out.println(connection.getHeaderField(i++));
			}while(field != null);
			return null;
		}
	}
	
	public static boolean hasKey(String key) {
		return map.get(key) != null;
	}
	
	public static void putManually(String url, String json) {
		map.put(url, json);
	}

	public static void save() {
		db.commit();
	}

}