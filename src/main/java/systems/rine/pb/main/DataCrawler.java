package systems.rine.pb.main;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nustaq.serialization.FSTConfiguration;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import systems.rine.pb.api.ApiData;
import systems.rine.pb.api.HTTPRequestCache;
import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.professions.ApiProfession;
import systems.rine.pb.api.professions.ApiSpecialization;
import systems.rine.pb.api.professions.ApiWeaponList;
import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.api.skills.ApiSkillFact;
import systems.rine.pb.api.traits.ApiTrait;
import systems.rine.pb.model.ArmorItem;
import systems.rine.pb.model.GW2Data;
import systems.rine.pb.model.InfusionSlot;
import systems.rine.pb.model.Item;

public class DataCrawler {
	private static final Logger logger = LogManager.getLogger(DataCrawler.class);
	private Gson gson;
	private ApiData data;

	public DataCrawler() {
		data = new ApiData();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(ApiWeaponList.class, ApiWeaponList.getDeserializer());
		gson = gsonBuilder.create();
	}

	/**
	 * This method will request big object arrays from the API then manually stores
	 * it in the DB
	 */
	public void buildData(boolean update) {
		if (update) {
			HTTPRequestCache.clear();
		}
		logger.info("Starting Bulk Fetching...");
		long time = System.currentTimeMillis();

		// ITEMS
		Integer[] itemIds = gson.fromJson(HTTPRequestCache.get("/items"), Integer[].class);
		IntStream.range(0, itemIds.length / 200 + 1).parallel().forEach(i -> {
			ApiItem[] items = gson.fromJson(HTTPRequestCache.get("/items?page=" + i + "&page_size=200"),
					ApiItem[].class);
			for (ApiItem item : items) {
				data.putItem(item);
			}
		});

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

		// SPECIALIZATIONS
		ApiSpecialization[] specializations = gson.fromJson(HTTPRequestCache.get("/specializations?ids=all"),
				ApiSpecialization[].class);
		for (ApiSpecialization apiSpecialization : specializations) {
			data.putSpecializatio(apiSpecialization);
		}

		// PROFESSIONS
		ApiProfession[] professions = gson.fromJson(HTTPRequestCache.get("/professions?ids=all"),
				ApiProfession[].class);
		for (ApiProfession profession : professions) {
			data.putProfession(profession);
		}
		logger.info("Fetching done in " + (System.currentTimeMillis() - time));
	}

	public static void main(String[] args) throws IOException {
		long time = System.currentTimeMillis();

		DataCrawler crawler = new DataCrawler();
		crawler.buildData(false);
		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
		Files.write(conf.asByteArray(crawler.data), new File("apidata.obj"));

		ApiData apiData = (ApiData) conf.asObject(Files.toByteArray(new File("apidata.obj")));
		
		
		
		GW2Data gw2Data = new GW2Data(apiData);
		for(Item item : gw2Data.getItems()) {
			if(item instanceof ArmorItem) {
				for(InfusionSlot slot: ((ArmorItem) item).getInfusionSlots()) {
					if(slot.getInfusion() != null) {
						System.out.println(slot.getInfusion().getName());
					}
				}
			}
		}
		

		System.out.println("total time: " + (System.currentTimeMillis() - time));
		HTTPRequestCache.save();
	}

}
