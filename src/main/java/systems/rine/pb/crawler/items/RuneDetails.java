package systems.rine.pb.crawler.items;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RuneDetails {

	@SerializedName("type")
	public String type;
	@SerializedName("flags")
	public List<String> flags = null;
	@SerializedName("infusion_upgrade_flags")
	public List<Object> infusionUpgradeFlags = null;
	@SerializedName("bonuses")
	public List<String> bonuses = null;
	@SerializedName("infix_upgrade")
	public InfixUpgrade infixUpgrade;
	@SerializedName("suffix")
	public String suffix;

}
