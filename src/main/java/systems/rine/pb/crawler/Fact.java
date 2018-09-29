
package systems.rine.pb.crawler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fact {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("icon")
    @Expose
    public String icon;
    @SerializedName("value")
    @Expose
    public String value;
    

}
