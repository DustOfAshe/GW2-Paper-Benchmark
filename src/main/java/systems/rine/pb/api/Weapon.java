
package systems.rine.pb.api;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import systems.rine.pb.api.skills.ApiSkill;

public class Weapon {

    public Integer specialization = null;
  
    public List<String> flags = null;
  
    public ApiSkill[] skills = null;

}
