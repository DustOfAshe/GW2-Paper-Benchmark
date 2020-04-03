package systems.rine.pb.crawler;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class GsonHelper {

	public static String getJson(String urlpath) {
		try {
			URL url = new URL("https://api.guildwars2.com/v2" + urlpath + "?lang=en");
			return new String(IOUtils.toByteArray(url.openStream()));
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}
	
}
