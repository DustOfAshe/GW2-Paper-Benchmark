
package systems.rine.pb.crawler;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Training {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("category")
    @Expose
    public String category;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("track")
    @Expose
    public List<Track> track = null;

}
