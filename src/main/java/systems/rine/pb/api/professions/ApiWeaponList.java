
package systems.rine.pb.api.professions;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ApiWeaponList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<ApiWeapon> weapons = new ArrayList<>();

	public static JsonDeserializer<ApiWeaponList> getDeserializer(){
		return new JsonDeserializer<ApiWeaponList>() {

			@Override
			public ApiWeaponList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				JsonObject jsonObject = json.getAsJsonObject();
				ApiWeaponList weaponList = new ApiWeaponList();
				for(Entry<String, JsonElement> entry : jsonObject.entrySet()) {
					weaponList.weapons.add(context.deserialize(entry.getValue().getAsJsonObject(), ApiWeapon.class));
				}
				return weaponList;
			}
				
			
		};
	}

	public List<ApiWeapon> get() {
		return weapons;
	}

}
