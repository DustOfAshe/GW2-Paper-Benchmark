package systems.rine.pb.crawler.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ArmorObject extends ItemObject {

	@SerializedName("type")
	public String type;
	@SerializedName("weight_class")
	public String weightClass;
	@SerializedName("defense")
	public Integer defense;
	@SerializedName("infusion_slots")
	public List<InfusionSlot> infusionSlots = null;
	@SerializedName("secondary_suffix_item_id")
	public String secondarySuffixItemId;
	@SerializedName("stat_choices")
	public List<Integer> statChoices = null;

}
