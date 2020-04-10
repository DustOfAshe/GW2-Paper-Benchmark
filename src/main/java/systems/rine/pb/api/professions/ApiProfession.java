
package systems.rine.pb.api.professions;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import systems.rine.pb.api.skills.ApiSkill;

public class ApiProfession implements Serializable{

	private static final long serialVersionUID = 1L;
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
    public ApiWeaponList weapons;
    @SerializedName("flags")    
    public List<String> flags = null;
    @SerializedName("skills")    
    public List<ApiSkill> skills = null;
    @SerializedName("training")    
    public List<ApiTraining> training = null;

}
