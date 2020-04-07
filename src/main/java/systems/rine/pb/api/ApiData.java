package systems.rine.pb.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.api.traits.ApiTrait;

public class ApiData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Integer, ApiItem> items = new TreeMap<>();
	private Map<Integer, ApiItemStat> itemStats = new TreeMap<>();
	private Map<Integer, ApiSkill> skills = new TreeMap<>();
	private Map<Integer, ApiTrait> traits = new TreeMap<>();
	
	public void putSkill(ApiSkill skill) {
		skills.put(skill.id, skill);
	}
	
	public void putItem(ApiItem item) {
		items.put(item.id, item);
	}
	
	public void putItemStat(ApiItemStat itemstat) {
		itemStats.put(itemstat.id, itemstat);
	}
	
	public void putTrait(ApiTrait trait) {
		traits.put(trait.id, trait);
	}
	
	public List<ApiItem> getItems() {
		return new ArrayList<>(items.values());
	}
	
	public List<ApiItemStat> getItemStats() {
		return new ArrayList<>(itemStats.values());
	}
	
	public List<ApiSkill> getSkills() {
		return new ArrayList<>(skills.values());
	}
	
	public List<ApiTrait> getTraits() {
		return new ArrayList<>(traits.values());
	}

	
}
