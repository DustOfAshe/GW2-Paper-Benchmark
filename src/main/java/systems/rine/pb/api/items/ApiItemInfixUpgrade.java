package systems.rine.pb.api.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiItemInfixUpgrade {
	@SerializedName("id")
	public Integer id;
	@SerializedName("attributes")
	public List<Object> attributes = null;
}
