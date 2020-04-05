
package systems.rine.pb.crawler;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profession {

    @SerializedName("id")    
    public String id;
    @SerializedName("name")    
    public String name;
    @SerializedName("icon")    
    public String icon;
    @SerializedName("icon_big")    
    public String iconBig;
    @SerializedName("specializations")    
    public List<Integer> specializations = null;
    @SerializedName("weapons")    
    public WeaponList weapons;
    @SerializedName("flags")    
    public List<String> flags = null;
    @SerializedName("skills")    
    public Skill[] skills = null;

}
