package systems.rine.pb.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import systems.rine.pb.crawler.HTTPRequestCache;
import systems.rine.pb.crawler.Item;
import systems.rine.pb.crawler.Skill;
import systems.rine.pb.crawler.WeaponList;

public class DataCrawler {
	private static final Logger logger = LogManager.getLogger(DataCrawler.class);
	private Gson gson;

	public DataCrawler() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WeaponList.class, WeaponList.getDeserializer());
		gsonBuilder.registerTypeAdapter(Skill[].class, Skill.getArrayDeserializer());
		gson = gsonBuilder.create();
	}

	/**
	 * This method will request big object arrays from the API then manually stores
	 * it in the DB
	 */
	public void fastPrefetch(boolean update) {
		logger.info("Starting Bulk Prefetch...");
		long time = System.currentTimeMillis();
		JsonParser parser = new JsonParser();

		// ITEMS
		if (!HTTPRequestCache.hasKey("HasItems") || update) {
			Integer[] itemIds = gson.fromJson(HTTPRequestCache.get("/items"), Integer[].class);
			for (int i = 0; i <= itemIds.length / 200; i++) {
				JsonArray items = parser.parse(HTTPRequestCache.get("/items?page=" + i + "&page_size=200", false))
						.getAsJsonArray();
				for (JsonElement item : items) {
					HTTPRequestCache.putManually("/items/" + item.getAsJsonObject().get("id"), item.toString());
				}
			}
			HTTPRequestCache.putManually("HasItems", "yep");
			HTTPRequestCache.save();
		} else {
			logger.info("Items cached already, skipped...");
		}

		// ITEMSTATS
		if (!HTTPRequestCache.hasKey("HasItemStats") || update) {
			JsonArray itemstats = parser.parse(HTTPRequestCache.get("/itemstats?ids=all", false)).getAsJsonArray();
			for (JsonElement itemstat : itemstats) {
				HTTPRequestCache.putManually("/itemstats/" + itemstat.getAsJsonObject().get("id"), itemstat.toString());
			}
			HTTPRequestCache.putManually("HasItemStats", "yep");
			HTTPRequestCache.save();
		} else {
			logger.info("ItemStats cached already, skipped...");
		}

		// SKILLS
		if (!HTTPRequestCache.hasKey("HasSkills") || update) {
			JsonArray skills = parser.parse(HTTPRequestCache.get("/skills?ids=all", false)).getAsJsonArray();
			for (JsonElement skill : skills) {
				HTTPRequestCache.putManually("/skills/" + skill.getAsJsonObject().get("id"), skill.toString());
			}
			HTTPRequestCache.putManually("HasSkills", "yep");
			HTTPRequestCache.save();
		} else {
			logger.info("Skills cached already, skipped...");
		}

		// TRAITS
		if (!HTTPRequestCache.hasKey("HasTraits") || update) {
		JsonArray traits = parser.parse(HTTPRequestCache.get("/traits?ids=all", false)).getAsJsonArray();
		for (JsonElement trait : traits) {
			HTTPRequestCache.putManually("/traits/" + trait.getAsJsonObject().get("id"), trait.toString());
		}
		HTTPRequestCache.putManually("HasTraits", "yep");
		HTTPRequestCache.save();
		}else {
			logger.info("Traits cached already, skipped...");
		}
		logger.info("Bulk Prefetch done in " + (System.currentTimeMillis() - time));
	}

	public void loadGW2Data() {

	}

	public static void main(String[] args) throws IOException, InterruptedException {
		long time = System.currentTimeMillis();

		DataCrawler crawler = new DataCrawler();
		crawler.fastPrefetch(false);

		System.out.println("load time for all professions: " + (System.currentTimeMillis() - time));
		
		Integer[] itemIds = crawler.gson.fromJson(HTTPRequestCache.get("/items"), Integer[].class);
		List<Item> items = new ArrayList<>();
		for(int itemId : itemIds) {
			System.out.println("current id:" + itemId);
			Item item = crawler.gson.fromJson(HTTPRequestCache.get("/items/" + itemId), Item.class);
			items.add(item);
		}
		
		time = System.currentTimeMillis();
		System.out.println("starting printing");
		for(Item item: items) {
			
		}
		System.out.println("priunt time: " + (System.currentTimeMillis() - time));
		HTTPRequestCache.save();
	}

}
