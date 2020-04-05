package systems.rine.pb.crawler.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class InfusionSlot {

	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("item_id")
	public Integer itemId;

}
