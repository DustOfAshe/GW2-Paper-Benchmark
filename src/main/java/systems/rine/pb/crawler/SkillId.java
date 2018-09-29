
package systems.rine.pb.crawler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SkillId {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("slot")
    @Expose
    public String slot;
    @SerializedName("type")
    @Expose
    public String type;

}
