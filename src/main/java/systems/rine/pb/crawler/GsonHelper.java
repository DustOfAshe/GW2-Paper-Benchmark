package systems.rine.pb.crawler;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class GsonHelper {

	public static String getJson(String urlpath) {
		int attempt = 1;
		while(true) {
			try {
				URL url = new URL("https://api.guildwars2.com/v2" + urlpath + "?lang=en");
				return new String(IOUtils.toByteArray(url.openStream()));
			} catch (IOException e) {
				System.out.println("HTTP request failed:" + e.getCause());
				System.out.println("Retrying, attempt " + attempt++ + " of 5...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(attempt > 5) {
					System.out.println("Stoped program after 5 failed HTTP request");
					System.exit(0);
				}
			}
		}
	}
	
}
