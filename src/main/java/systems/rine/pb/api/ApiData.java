package systems.rine.pb.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.collections.impl.map.mutable.ConcurrentHashMap;

import systems.rine.pb.api.items.ApiItem;
import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.professions.ApiProfession;
import systems.rine.pb.api.professions.ApiSpecialization;
import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.api.traits.ApiTrait;

public class ApiData implements Serializable {
	private static final long serialVersionUID = 1L;
	private Map<Integer, ApiItem> items = new ConcurrentHashMap<>();
	private Map<Integer, ApiItemStat> itemStats = new ConcurrentHashMap<>();
	private Map<Integer, ApiSkill> skills = new ConcurrentHashMap<>();
	private Map<Integer, ApiTrait> traits = new ConcurrentHashMap<>();
	private Map<String, ApiProfession> professions = new ConcurrentHashMap<>();
	private Map<Integer, ApiSpecialization> specializations = new ConcurrentHashMap<>();
	
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
	
	public void putSpecializatio(ApiSpecialization specialization) {
		specializations.put(specialization.id, specialization);
	}
	
	public void putProfession(ApiProfession profession) {
		professions.put(profession.name, profession);
	}
	
	public ApiSkill getSkill(int id) {
		return skills.get(id);
	}
	
	public ApiItem getItem(int id) {
		return items.get(id);
	}
	
	public ApiItemStat getItemStat(int id) {
		return itemStats.get(id);
	}
	
	public ApiTrait getTrait(int id) {
		return traits.get(id);
	}
	
	public ApiSpecialization getSpecialization(int id) {
		return specializations.get(id);
	}
	
	public ApiProfession getProfession(String name) {
		return professions.get(name);
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
	
	public List<ApiProfession> getProfessions() {
		return new ArrayList<>(professions.values());
	}

	public List<ApiSpecialization> getSpecializations() {
		return new ArrayList<>(specializations.values());
	}

	
}
