
package systems.rine.pb.api.skills;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ApiSkill implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName("name")
	public String name;
	@SerializedName("description")
	public String description;
	@SerializedName("icon")
	public String icon;
	@SerializedName("type")
	public String type;
	@SerializedName("weapon_type")
	public String weaponType;
	@SerializedName("professions")
	public List<String> professions = null;
	@SerializedName("slot")
	public String slot;
	@SerializedName("next_chain")
	public Integer nextChain;
	@SerializedName("prev_chain")
	public Integer prevChain;
	@SerializedName("flip_skill")
	public Integer flipSkill;
	@SerializedName("transform_skills")
	public List<Integer> transformSkills;
	@SerializedName("bundle_skills")
	public List<Integer> bundleSkills;
	@SerializedName("toolbelt_skill")
	public Integer toolbeltSkill;
	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("specialization")
	public Integer specialization;
	@SerializedName("id")
	public Integer id;
	@SerializedName("chatLink")
	public String chatLink;
	@SerializedName("categories")
	public List<Object> categories = null;
	@SerializedName("facts")
	public List<ApiSkillFact> facts = null;
	@SerializedName("traited_facts")
	public List<ApiSkillFact> traitedFacts = null;
	@SerializedName("attunement")
	public String attunement = null;
	@SerializedName("cost")
	public Integer cost = null;
	@SerializedName("dual_wield")
	public String dualWield = null;
	@SerializedName("initiative")
	public Integer initiative = null;

	

}
