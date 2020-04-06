package systems.rine.pb.api.items;

import com.google.gson.annotations.SerializedName;

public class ApiItemstatAttribute {
	
	@SerializedName("attribute")
	public String attribute;
	@SerializedName("multiplier")
	public Double multiplier;
	@SerializedName("value")
	public Integer value;

}
