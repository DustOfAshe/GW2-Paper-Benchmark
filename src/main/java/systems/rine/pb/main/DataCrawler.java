package systems.rine.pb.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.util.FSTOutputStream;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import systems.rine.pb.api.ApiData;
import systems.rine.pb.api.HTTPRequestCache;
import systems.rine.pb.api.WeaponList;
import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.api.skills.ApiSkillFact;
import systems.rine.pb.api.traits.ApiTrait;

public class DataCrawler {
	private static final Logger logger = LogManager.getLogger(DataCrawler.class);
	private Gson gson;
	private ApiData data;

	public DataCrawler() {
		data = new ApiData();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(WeaponList.class, WeaponList.getDeserializer());
		gson = gsonBuilder.create();
	}

	/**
	 * This method will request big object arrays from the API then manually stores
	 * it in the DB
	 */
	public void buildData(boolean update) {
		if(update) {
			HTTPRequestCache.clear();
		}
		logger.info("Starting Bulk Fetching...");
		long time = System.currentTimeMillis();

		// ITEMS
		Integer[] itemIds = gson.fromJson(HTTPRequestCache.get("/items"), Integer[].class);
		IntStream.range(0, itemIds.length / 200 + 1).parallel().forEach(i ->{
			ApiItem[] items = gson.fromJson(HTTPRequestCache.get("/items?page=" + i + "&page_size=200"),
					ApiItem[].class);
			for (ApiItem item : items) {
				data.putItem(item);
			}
		});
		for (int i = 0; i <= itemIds.length / 200; i++) {
//			ApiItem[] items = gson.fromJson(HTTPRequestCache.get("/items?page=" + i + "&page_size=200"),
//					ApiItem[].class);
//			for (ApiItem item : items) {
//				data.putItem(item);
//			}
		}

		// ITEMSTATS
		ApiItemStat[] itemStats = gson.fromJson(HTTPRequestCache.get("/itemstats?ids=all"), ApiItemStat[].class);
		for (ApiItemStat apiItemStat : itemStats) {
			data.putItemStat(apiItemStat);
		}

		// SKILLS
		ApiSkill[] skills = gson.fromJson(HTTPRequestCache.get("/skills?ids=all"), ApiSkill[].class);
		for (ApiSkill apiSkill : skills) {
			data.putSkill(apiSkill);
		}

		// TRAITS
		ApiTrait[] traits = gson.fromJson(HTTPRequestCache.get("/traits?ids=all"), ApiTrait[].class);
		for (ApiTrait trait : traits) {
			data.putTrait(trait);
		}
		
		logger.info("Fetching done in " + (System.currentTimeMillis() - time));
	}

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();

		DataCrawler crawler = new DataCrawler();
		crawler.buildData(false);
		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
		Files.write(conf.asByteArray(crawler.data), new File("apidata.obj"));

//		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
//		ApiData data = (ApiData) conf.asObject(Files.toByteArray(new File("apidata.obj")));
		
		System.out.println("load time for objects: " + (System.currentTimeMillis() - time));

//		for (ApiSkill skill : data.getSkills()) {
//			if(skill.facts != null) {
//				for (ApiSkillFact fact : skill.facts) {
//					if(fact.value != null) {
//						System.out.println(skill.name);
//						System.out.println(fact.value);
//					}
//				}
//			}		
//		}

		System.out.println("priunt time: " + (System.currentTimeMillis() - time));
		HTTPRequestCache.save();
	}

}
