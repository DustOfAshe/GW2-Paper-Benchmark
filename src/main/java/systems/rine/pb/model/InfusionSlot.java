package systems.rine.pb.model;

import systems.rine.pb.api.items.ApiItemInfusionSlot;

public class InfusionSlot {
	private ApiItemInfusionSlot apiInfusionSlot;
	private GW2Data gw2Data;
	private InfusionType type;
	private Item infusion = null;
	
	public InfusionSlot(ApiItemInfusionSlot apiInfusionSlot, GW2Data gw2Data) {
		this.apiInfusionSlot = apiInfusionSlot;
		this.gw2Data = gw2Data;
		type = InfusionType.valueOf(apiInfusionSlot.flags.get(0));
		if(apiInfusionSlot.itemId != null && apiInfusionSlot.itemId != 0) {
			infusion = gw2Data.getItem(apiInfusionSlot.itemId);
		}
	}
	
	public Item getInfusion() {
		return infusion;
	}
	
	public InfusionType getType() {
		return type;
	}

}
