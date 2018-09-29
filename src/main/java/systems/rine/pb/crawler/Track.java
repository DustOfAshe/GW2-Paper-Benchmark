
package systems.rine.pb.crawler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Track {

    @SerializedName("cost")
    @Expose
    public Integer cost;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("skill_id")
    @Expose
    public Integer skillId;
    @SerializedName("trait_id")
    @Expose
    public Integer traitId;

}
