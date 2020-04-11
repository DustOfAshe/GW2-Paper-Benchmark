package systems.rine.pb.api.items;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ApiItemstatAttribute implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@SerializedName("attribute")
	public String attribute;
	@SerializedName("multiplier")
	public Double multiplier;
	@SerializedName("value")
	public Integer value;
	
	@Override
	public String toString() {
		return "ApiItemstatAttribute [attribute=" + attribute + ", multiplier=" + multiplier + ", value=" + value + "]";
	}	

}
