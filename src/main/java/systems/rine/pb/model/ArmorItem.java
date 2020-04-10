package systems.rine.pb.model;

import java.util.ArrayList;
import java.util.List;

import com.google.j2objc.annotations.ReflectionSupport.Level;

import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemDetails;
import systems.rine.pb.api.items.ApiItemInfusionSlot;

public class ArmorItem extends Item {
	private ApiItemDetails detailObject;
	private ArmorType type;
	private WeightClass weightClass;
	private List<InfusionSlot> infusionSlots = new ArrayList<>();
	private Stats stats;

	public ArmorItem(ApiItem apiItem, GW2Data gw2Data) {
		super(apiItem, gw2Data);
		this.detailObject = apiItem.itemObject;
	}
	
	@Override
	public void create() {
		super.create();
		type = ArmorType.valueOf(detailObject.type);
		weightClass = WeightClass.valueOf(detailObject.weightClass);
		if(detailObject.infusionSlots != null) {
			for(ApiItemInfusionSlot slot : detailObject.infusionSlots) {
				infusionSlots.add(new InfusionSlot(slot, gw2Data));
			}
		}
		
		if(detailObject.infixUpgrade != null && getLevel() == 80 && 
				(rarity == Rarity.Ascended || rarity == Rarity.Legendary) ) {
			stats = new Stats(type.getHiddenNumber(), gw2Data.getItemStat(detailObject.infixUpgrade.id));
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
	

}
