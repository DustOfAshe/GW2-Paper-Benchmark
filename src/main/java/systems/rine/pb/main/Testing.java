package systems.rine.pb.main;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import systems.rine.pb.crawler.GsonHelper;
import systems.rine.pb.crawler.Profession;
import systems.rine.pb.crawler.Skill;
import systems.rine.pb.crawler.SkillHolder;
import systems.rine.pb.crawler.Weapon;
import systems.rine.pb.crawler.WeaponList;

public class Testing {
	static Gson gson = null;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		GsonBuilder gsonBuilder = new GsonBuilder();	
		gsonBuilder.registerTypeAdapter(SkillHolder.class, SkillHolder.getDeserializer());
		gsonBuilder.registerTypeAdapter(WeaponList.class, WeaponList.getDeserializer());
		gson = gsonBuilder.create();
		
		
		Profession guard = gson.fromJson(GsonHelper.getJson("/professions/Guardian"), Profession.class);

		
		for(Weapon weapon: guard.weapons.get()) {
			for(SkillHolder skh : weapon.skills) {
				System.out.println(skh.getSkill().name);
			}
		}
		
	}

	
	
	
}
