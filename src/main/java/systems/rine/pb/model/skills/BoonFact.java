package systems.rine.pb.model.skills;

import systems.rine.pb.api.skills.ApiSkillFact;
import systems.rine.pb.model.GW2Data;
import systems.rine.pb.simulation.BoonType;
import systems.rine.pb.simulation.Target;

public class BoonFact extends SkillFact{
	private BoonType type;
	private int stackCount;
	private int duration;

	public BoonFact(ApiSkillFact apiSkillFact, GW2Data gw2Data) {
		super(apiSkillFact, gw2Data);
		type = BoonType.valueOf(apiSkillFact.status);
		stackCount = apiSkillFact.applyCount;
		duration = apiSkillFact.duration * 1000;
	}
	
	public void apply(Target source, Target target) {
		target.applyBoon(type, duration, source, stackCount);
	}

}
