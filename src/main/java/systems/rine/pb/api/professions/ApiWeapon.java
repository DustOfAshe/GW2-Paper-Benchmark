
package systems.rine.pb.api.professions;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

import systems.rine.pb.api.skills.ApiSkill;

public class ApiWeapon implements Serializable{

	private static final long serialVersionUID = 1L;

	@SerializedName("specialization")
	public Integer specialization = null;
	@SerializedName("flag")
    public List<String> flag = null;
	@SerializedName("skills")
    public List<ApiSkill> skills = null;
	@SerializedName("flags")
    public List<String> flags = null;

}
