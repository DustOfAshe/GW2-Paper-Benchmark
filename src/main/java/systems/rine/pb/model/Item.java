package systems.rine.pb.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiUpgradeItem;

public class Item {
	private static final Logger logger = LogManager.getLogger(Item.class);
	protected ApiItem apiItem;
	protected GW2Data gw2Data;
	protected Rarity rarity;
	protected List<Item> upgradesFrom = new ArrayList<>();
	protected List<Item> upgradesInto = new ArrayList<>();

	public Item(ApiItem apiItem, GW2Data gw2Data) {
		this.apiItem = apiItem;
		this.gw2Data = gw2Data;
	}

	public void create() {
		if (apiItem.upgradesFrom != null) {
			for (ApiUpgradeItem upgradeItem : apiItem.upgradesFrom) {
				Item upgrade = gw2Data.getItem(upgradeItem.itemId);
				if (upgrade != null) {
					upgradesFrom.add(upgrade);
				} else {
					logger.warn("Couldn't find upgrade Item with Id: " + upgradeItem.itemId + " for Item: " + getName()
							+ " This might be a bug in the API");
				}
			}
		}

		if (apiItem.upgradesInto != null) {
			for (ApiUpgradeItem upgradeItem : apiItem.upgradesInto) {
				Item upgrade = gw2Data.getItem(upgradeItem.itemId);
				if (upgrade != null) {
					upgradesInto.add(upgrade);
				} else {
					logger.warn("Couldn't find upgrade Item with Id: " + upgradeItem.itemId + " for Item: " + getName()
							+ " This might be a bug in the API");
				}
			}
		}
		rarity = Rarity.valueOf(apiItem.rarity);
	}

	public String getName() {
		return apiItem.name;
	}
	
	public int getLevel() {
		return apiItem.level;
	}

	public List<Item> getUpgradesFrom() {
		return upgradesFrom;
	}

	public List<Item> getUpgradesInto() {
		return upgradesInto;
	}

}
