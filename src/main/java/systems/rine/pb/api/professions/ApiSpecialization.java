package systems.rine.pb.api.professions;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiSpecialization implements Serializable{

	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public Integer id;
	@SerializedName("name")
	public String name;
	@SerializedName("profession")
	public String profession;
	@SerializedName("elite")
	public Boolean elite;
	@SerializedName("minor_traits")
	public List<Integer> minorTraits = null;
	@SerializedName("major_traits")
	public List<Integer> majorTraits = null;
	@SerializedName("icon")
	public String icon;
	@SerializedName("background")
	public String background;

}
