package systems.rine.pb.api.items;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiItemInfusionSlot implements Serializable{

	private static final long serialVersionUID = 1L;
	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("item_id")
	public Integer itemId;

}
