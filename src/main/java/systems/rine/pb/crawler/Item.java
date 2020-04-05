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

import systems.rine.pb.crawler.items.ItemObject;

public class Item {

	@SerializedName("name")
	public String name;
	@SerializedName("description")
	public String description;
	@SerializedName("type")
	public String type;
	@SerializedName("level")
	public Integer level;
	@SerializedName("rarity")
	public String rarity;
	@SerializedName("vendor_value")
	public Integer vendorValue;
	@SerializedName("default_skin")
	public String defaultSkin;
	@SerializedName("game_types")
	public List<String> gameTypes = null;
	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("restrictions")
	public List<Object> restrictions = null;
	@SerializedName("id")
	public Integer id;
	@SerializedName("chat_link")
	public String chatLink;
	@SerializedName("icon")
	public String icon;
	@SerializedName("upgrades_from")
	public List<UpgradeItem> upgradesFrom;
	@SerializedName("upgrades_into")
	public List<UpgradeItem> upgradesInto;
	@SerializedName("details")
	public ItemObject itemObject;


}
