package systems.rine.pb.api.items;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiItemStat implements Serializable{

	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public Integer id;
	@SerializedName("name")
	public String name;
	@SerializedName("attributes")
	public List<ApiItemstatAttribute> attributes = null;

}
