
package systems.rine.pb.api.skills;

import java.io.Serializable;

import com.google.gson.JsonPrimitive;
import com.google.gson.annotations.SerializedName;

public class ApiSkillFact implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@SerializedName("requires_trait")
	public Integer requiresTrait;
	@SerializedName("overrides")
	public Integer overrides;

	@SerializedName("text")
	public String text;
	@SerializedName("icon")
	public String icon;
	@SerializedName("type")
	public String type;

	@SerializedName("value")
	public String value;
	@SerializedName("target")
	public String target;
	@SerializedName("status")
	public String status;
	@SerializedName("description")
	public String description;
	@SerializedName("apply_count")
	public Integer applyCount;
	@SerializedName("duration")
	public Integer duration;
	@SerializedName("field_type")
	public String fieldType;
	@SerializedName("finisher_type")
	public String finisherType;
	@SerializedName("percent")
	public Double percent;
	@SerializedName("hit_count")
	public Integer hitCount;
	@SerializedName("dmg_multiplier")
	public Double dmgMultiplier;
	@SerializedName("distance")
	public Integer distance;
	//PREFIXED BUFF???
	

}
