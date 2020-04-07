package systems.rine.pb.api.traits;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import systems.rine.pb.api.skills.ApiSkillFact;

public class ApiTraitSkill implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	public Integer id;
	@SerializedName("name")
	public String name;
	@SerializedName("description")
	public String description;
	@SerializedName("icon")
	public String icon;
	@SerializedName("facts")
	public List<ApiSkillFact> facts = null;
	@SerializedName("traited_facts")
	public List<ApiSkillFact> traitedFacts = null;
	

}
