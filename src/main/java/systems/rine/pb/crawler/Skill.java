
package systems.rine.pb.crawler;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Skill {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("facts")
    @Expose
    public List<Fact> facts = null;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("weapon_type")
    @Expose
    public String weaponType;
    @SerializedName("professions")
    @Expose
    public List<String> professions = null;
    @SerializedName("slot")
    @Expose
    public String slot;
    @SerializedName("next_chain")
    @Expose
    public Integer nextChain;
    @SerializedName("flip_skill")
    @Expose
    public Integer flipSkill;
    @SerializedName("flags")
    @Expose
    public List<String> flags = null;
    @SerializedName("specialization")
    @Expose
    public Integer specialization;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("chat_link")
    @Expose
    public String chatLink;
    @SerializedName("categories")
    @Expose
    public List<Object> categories = null;
    @SerializedName("traited_facts")
    @Expose
    public List<TraitedFact> traitedFacts = null;

}
