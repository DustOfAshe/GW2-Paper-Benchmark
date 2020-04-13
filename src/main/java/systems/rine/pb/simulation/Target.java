package systems.rine.pb.simulation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import systems.rine.pb.simulation.time.TimeManager;

public class Target {
	protected int defense;
	protected int hp;
	protected Map<BoonType, Boon> boons;
	protected Map<BoonListener, BoonType> boonTypeOfListener;
	protected List<Condition> conditions;
	
	public Target(TimeManager timeManager) {
		boons = new HashMap<>();
		boonTypeOfListener = new HashMap<>();
		for(BoonType boonType : BoonType.values()) {
			boons.put(boonType, new Boon(timeManager, this));
		}
	}
	
	public void registerBoonListener(BoonType type, BoonListener listener) {
		boons.get(type).addListener(listener);
		boonTypeOfListener.put(listener, type);
	}
	
	public void removeBoonListener(BoonListener listener) {
		boons.get(boonTypeOfListener.get(listener)).removeListener(listener);
		boonTypeOfListener.remove(listener);
	}
	
	
}
