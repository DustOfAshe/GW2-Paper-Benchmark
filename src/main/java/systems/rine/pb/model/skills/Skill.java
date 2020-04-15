package systems.rine.pb.model.skills;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import com.google.common.base.Enums;

import systems.rine.pb.api.skills.ApiSkill;
import systems.rine.pb.api.skills.ApiSkillFact;
import systems.rine.pb.model.GW2Data;
import systems.rine.pb.simulation.BoonType;
import systems.rine.pb.simulation.Target;
import systems.rine.pb.simulation.time.EventAffection;

public class Skill {
	private ApiSkill apiSkill;
	private GW2Data gw2Data;
	private List<SkillFact> skillFacts;
	private boolean isReady = true;
	private long recharge = 0;
	private long castTime = 1000; 
	
	public Skill(ApiSkill apiSkill, GW2Data gw2Data) {
		super();
		this.apiSkill = apiSkill;
		this.gw2Data = gw2Data;
	}
	
	public void create() {
		for(ApiSkillFact apiFact : apiSkill.facts) {
			if(apiFact.type.equals("Buff")) {
				if(Enums.getIfPresent(BoonType.class, apiFact.status) != null) {
					skillFacts.add(new BoonFact(apiFact, gw2Data));
				}	
			}else if(apiFact.type.equals("Recharge")){
				recharge = Integer.valueOf(apiFact.value) * 1000;
			}
			
		}
	}
	
	public boolean isReady() {
		return isReady;
	}
	
	public long use(Target source, Target target) {
		for(SkillFact fact: skillFacts) {
			if(fact instanceof BoonFact) {
				((BoonFact) fact).apply(source, target);
			}
		}
		if(recharge != 0) {
			isReady = false;
			source.getTimeManager().registerEvent(recharge, source, EventAffection.Alacrity, (event) -> {
				isReady = true;
				return -1;
			});
		}
		return castTime;
	}
	
	public String getName() {
		return apiSkill.name;
	}
	
	

}
