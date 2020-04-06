package systems.rine.pb.api.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiItemInfusionSlot {

	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("item_id")
	public Integer itemId;

}
