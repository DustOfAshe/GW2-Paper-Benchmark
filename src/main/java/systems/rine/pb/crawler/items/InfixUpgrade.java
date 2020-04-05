package systems.rine.pb.crawler.items;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class InfixUpgrade {
	@SerializedName("id")
	public Integer id;
	@SerializedName("attributes")
	public List<Object> attributes = null;
}
