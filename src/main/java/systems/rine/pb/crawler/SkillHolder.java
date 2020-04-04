package systems.rine.pb.crawler;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class SkillHolder {
	private Skill skill;
	
	public SkillHolder(Skill skill) {
		this.skill = skill;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public static JsonDeserializer<SkillHolder> getDeserializer(){
		return new JsonDeserializer<SkillHolder>() {

			@Override
			public SkillHolder deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();
				String skillJson = HTTPRequestCache.get("/skills/" + jsonObject.get("id").getAsInt());
				Skill skill = context.deserialize(new JsonParser().parse(skillJson).getAsJsonObject(), Skill.class);
				return new SkillHolder(skill);
			}
				
			
		};
	}
	
	
}
