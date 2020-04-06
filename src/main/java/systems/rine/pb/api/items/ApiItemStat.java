package systems.rine.pb.api.items;

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

import systems.rine.pb.api.HTTPRequestCache;

public class ApiItemStat {

	@SerializedName("id")
	public Integer id;
	@SerializedName("name")
	public String name;
	@SerializedName("attributes")
	public List<ApiItemstatAttribute> attributes = null;

}
