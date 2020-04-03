
package systems.rine.pb.crawler;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {
    public String name;
    public List<SkillFact> facts = null;
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
    @SerializedName("flip_skill")
    public Integer flipSkill;
    @SerializedName("flags")
    public List<String> flags = null;
    @SerializedName("specialization")
    public Integer specialization;
    @SerializedName("id")
    public Integer id;
    public String chatLink;
    @SerializedName("categories")
    public List<Object> categories = null;
    @SerializedName("traited_facts")
    public List<SkillFact> traitedFacts = null;

}
