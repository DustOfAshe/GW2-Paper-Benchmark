
package systems.rine.pb.crawler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class WeaponList {
	
	private List<Weapon> weapons = new ArrayList<>();

	public static JsonDeserializer<WeaponList> getDeserializer(){
		return new JsonDeserializer<WeaponList>() {

			@Override
			public WeaponList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();
				WeaponList weaponList = new WeaponList();
				for(Entry<String, JsonElement> entry : jsonObject.entrySet()) {
					weaponList.weapons.add(context.deserialize(entry.getValue().getAsJsonObject(), Weapon.class));
				}
				return weaponList;
			}
				
			
		};
	}

	public List<Weapon> get() {
		return weapons;
	}

}
