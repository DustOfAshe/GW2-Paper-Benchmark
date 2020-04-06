package systems.rine.pb.api.items;

import com.google.gson.annotations.SerializedName;

public class ApiUpgradeItem {
	@SerializedName("upgrade")
	public String upgrade;
	@SerializedName("item_id")
	public Integer itemId;
}
