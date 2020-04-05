
package systems.rine.pb.crawler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

public class Skill {
	public String name;
	public List<SkillFact> facts = null;
	public String description;
	@SerializedName("icon")
	public String icon;
	@SerializedName("type")
	public String type;
	@SerializedName("weapon_type")
	public String weaponType;
	@SerializedName("professions")
	public List<String> professions = null;
	@SerializedName("slot")
	public String slot;
	@SerializedName("next_chain")
	public Integer nextChain;
	@SerializedName("flip_skill")
	public Integer flipSkill;
	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("specialization")
	public Integer specialization;
	@SerializedName("id")
	public Integer id;
	public String chatLink;
	@SerializedName("categories")
	public List<Object> categories = null;
	@SerializedName("traited_facts")
	public List<SkillFact> traitedFacts = null;

	public static Object getArrayDeserializer() {
		return new JsonDeserializer<Skill[]>() {

			@Override
			public Skill[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonArray jsonArray = json.getAsJsonArray();
				
				List<Skill> result = new ArrayList<Skill>();
				
				int i = 0;
				for(JsonElement elem : jsonArray) {
					String skillJson = HTTPRequestCache.get("/skills/" + elem.getAsJsonObject().get("id").getAsInt());
					Skill skill = context.deserialize(new JsonParser().parse(skillJson).getAsJsonObject(), Skill.class);
					result.add(skill);
					while(skill.nextChain != null && skill.nextChain != 0) {
						skillJson = HTTPRequestCache.get("/skills/" + skill.nextChain);
						skill = context.deserialize(new JsonParser().parse(skillJson).getAsJsonObject(), Skill.class);
						result.add(skill);
					}
				}
				return result.toArray(new Skill[result.size()]);
			}
		};
	}

}
