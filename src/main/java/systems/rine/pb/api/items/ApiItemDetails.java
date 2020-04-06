package systems.rine.pb.api.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiItemDetails {
	
	@SerializedName("type")
	public String type;
	@SerializedName("weight_class")
	public String weightClass;
	@SerializedName("defense")
	public Integer defense;
	@SerializedName("infusion_slots")
	public List<ApiItemInfusionSlot> infusionSlots;
	@SerializedName("infix_upgrade")
	public ApiItemInfixUpgrade infixUpgrade;
	@SerializedName("suffix_item_id")
	public Integer suffixItemId;
	@SerializedName("secondary_suffix_item_id")
	public Integer secondarySuffixItemId;
	@SerializedName("stat_choices")
	public List<Integer> statChoices;
	@SerializedName("size")
	public Integer size;
	@SerializedName("no_sell_or_sort")
	public Boolean noSellOrSort;
	@SerializedName("description")
	public String description;
	@SerializedName("duration_ms")
	public Integer durationMs;
	@SerializedName("unlock_type")
	public String unlockType;
	@SerializedName("color_id")
	public Integer colorId;
	@SerializedName("recipe_id")
	public Integer recipeId;
	@SerializedName("extra_recipe_ids")
	public List<Integer> extraRecipeId;
	@SerializedName("guild_upgrade_id")
	public Integer guildUpgradeId;
	@SerializedName("apply_count")
	public Integer applyCount;
	@SerializedName("name")
	public String name;
	@SerializedName("icon")
	public String icon;
	@SerializedName("skins")
	public List<Integer> skins;
	@SerializedName("vendor_ids")
	public List<Integer> vendorIds;
	@SerializedName("minipet_id")
	public Integer minipetId;
	@SerializedName("flags")
	public List<String> flags;
	@SerializedName("infusion_upgrade_flags")
	public List<String> infusionUpgradeFlags;
	@SerializedName("suffix")
	public String suffix;
	@SerializedName("bonuses")
	public List<String> bonuses;
	@SerializedName("damage_type")
	public String damageType;
	@SerializedName("max_power")
	public Integer maxPower;
	@SerializedName("min_power")
	public Integer minPower;
	
	
}
