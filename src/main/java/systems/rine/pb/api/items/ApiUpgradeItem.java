package systems.rine.pb.api.items;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ApiUpgradeItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@SerializedName("upgrade")
	public String upgrade;
	@SerializedName("item_id")
	public Integer itemId;
}
