package systems.rine.pb.api.professions;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ApiTrack implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@SerializedName("cost")
	public Integer cost;
	@SerializedName("type")
	public String type;
	@SerializedName("skill_id")
	public Integer skillId;
	@SerializedName("trait_id")
	public Integer traitId;

}
