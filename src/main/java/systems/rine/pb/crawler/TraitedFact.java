
package systems.rine.pb.crawler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TraitedFact {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("requires_trait")
    @Expose
    public Integer requiresTrait;
    @SerializedName("hit_count")
    @Expose
    public Integer hitCount;
    @SerializedName("dmg_multiplier")
    @Expose
    public Double dmgMultiplier;
    @SerializedName("overrides")
    @Expose
    public Integer overrides;

}
