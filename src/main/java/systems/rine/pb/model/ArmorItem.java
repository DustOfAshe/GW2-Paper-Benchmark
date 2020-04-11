package systems.rine.pb.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.j2objc.annotations.ReflectionSupport.Level;

import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemDetails;
import systems.rine.pb.api.items.ApiItemInfusionSlot;

public class ArmorItem extends Item {
	private static Map<WeightClass, Map<ArmorType, Map<String, ArmorItem>>> armorBySuffix;
	private ApiItemDetails detailObject;
	private ArmorType type;
	private WeightClass weightClass;
	private List<InfusionSlot> infusionSlots = new ArrayList<>();
	private Stats stats;

	static {
		armorBySuffix = new TreeMap<>();
		for(WeightClass weightClass : WeightClass.values()) {
			Map<ArmorType, Map<String, ArmorItem>> temp = new TreeMap<>();
			armorBySuffix.put(weightClass, temp);
			for(ArmorType armorType : ArmorType.values()) {
				temp.put(armorType, new TreeMap<>());
			}
		}
	}
	
	public ArmorItem(ApiItem apiItem, GW2Data gw2Data) {
		super(apiItem, gw2Data);
		this.detailObject = apiItem.itemObject;
	}

	@Override
	public void create() {
		super.create();
		type = ArmorType.valueOf(detailObject.type);
		weightClass = WeightClass.valueOf(detailObject.weightClass);
		if (detailObject.infusionSlots != null) {
			for (ApiItemInfusionSlot slot : detailObject.infusionSlots) {
				infusionSlots.add(new InfusionSlot(slot, gw2Data));
			}
		}

		if (detailObject.infixUpgrade != null && (rarity == Rarity.Ascended)) {
			stats = new Stats(type.getHiddenNumber(), gw2Data.getItemStat(detailObject.infixUpgrade.id));
			
			armorBySuffix.get(weightClass).get(type).put(stats.getPrefix(), this);
		}
	}
	

	public ArmorType getType() {
		return type;
	}

	public WeightClass getWeightClass() {
		return weightClass;
	}

	public int getDefense() {
		return detailObject.defense;
	}

	public List<InfusionSlot> getInfusionSlots() {
		return infusionSlots;
	}

	public Stats getStats() {
		return stats;
	}
	
	public static List<ArmorItem> getArmorOptions(WeightClass weightClass, ArmorType type){
		return new ArrayList<>(armorBySuffix.get(weightClass).get(type).values());
	}
	
	public static List<String> getAvailableArmorStatsSuffixs(){
		return new ArrayList<>(armorBySuffix.get(WeightClass.Light).get(ArmorType.Shoulders).keySet());
	}
	
	public static ArmorItem getArmorBySuffix(WeightClass weightClass, ArmorType type, String suffix) {
		return armorBySuffix.get(weightClass).get(type).get(suffix);
	}

}
