package systems.rine.pb.model.skills;

import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.model.GW2Data;

public class Skill {
	private ApiSkill apiSkill;
	private GW2Data gw2Data;
	
	public Skill(ApiSkill apiSkill, GW2Data gw2Data) {
		super();
		this.apiSkill = apiSkill;
		this.gw2Data = gw2Data;
	}
	
	public void create() {
		
	}
	
	public String getName() {
		return apiSkill.name;
	}
	
	

}
