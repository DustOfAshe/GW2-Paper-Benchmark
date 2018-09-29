
package systems.rine.pb.crawler;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profession {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("icon_big")
    @Expose
    public String iconBig;
    @SerializedName("specializations")
    @Expose
    public List<Integer> specializations = null;
    @SerializedName("weapons")
    @Expose
    public Weapons weapons;
    @SerializedName("flags")
    @Expose
    public List<String> flags = null;
    @SerializedName("skills")
    @Expose
    public List<SkillId> skills = null;
    @SerializedName("training")
    @Expose
    public List<Training> training = null;

}
