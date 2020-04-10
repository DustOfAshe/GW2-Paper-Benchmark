package systems.rine.pb.api.professions;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ApiTraining implements Serializable{

	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public Integer id;
	@SerializedName("category")
	public String category;
	@SerializedName("name")
	public String name;
	@SerializedName("track")
	public List<ApiTrack> track = null;

}