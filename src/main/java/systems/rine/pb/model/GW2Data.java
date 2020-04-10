package systems.rine.pb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import systems.rine.pb.api.ApiData;
import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemInfixUpgrade;
import systems.rine.pb.api.items.ApiItemStat;

public class GW2Data {
	private ApiData apiData;
	private Map<String, Profession> professions = new ConcurrentHashMap<>();
	private Map<Integer, Item> items = new ConcurrentHashMap<>();
	
	public GW2Data(ApiData apiData) {
		this.apiData = apiData;
		allocObjects();
		buildObjects();
	}
	
	private void allocObjects() {
		for(ApiItem apiItem : apiData.getItems()) {
			Item item;
			if(apiItem.type.equals("Armor")) {
				item = new ArmorItem(apiItem, this);
			}else {
				item = new Item(apiItem, this);
			}
			items.put(apiItem.id, item);
		}
	}
	
	private void buildObjects() {
		for(Item item : items.values()) {
			item.create();
		}
	}

	public Item getItem(int id) {
		return items.get(id);
	}

	public List<Item> getItems() {
		return new ArrayList<Item>(items.values());
	}

	public ApiItemStat getItemStat(int id) {
		return apiData.getItemStat(id);
	}

}
