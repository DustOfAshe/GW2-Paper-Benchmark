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
			boons.put(boonType, new Boon(boonType, timeManager, this));
		}
	}
	
	public BoonListener registerBoonListener(BoonType type, BoonListener listener) {
		boons.get(type).addListener(listener);
		boonTypeOfListener.put(listener, type);
		return listener;
	}
	
	public void removeBoonListener(BoonListener listener) {
		boons.get(boonTypeOfListener.get(listener)).removeListener(listener);
		boonTypeOfListener.remove(listener);
	}
	
	public void applyBoon(BoonType type, int duration) {
		applyBoon(type, duration, this, 1);
	}
	
	public void applyBoon(BoonType type, int duration, Target source) {
		applyBoon(type, duration, source, 1);
	}
	
	public void applyBoon(BoonType type, int duration, int stacks) {
		applyBoon(type, duration, this, stacks);
	}

	public void applyBoon(BoonType type, int duration, Target source, int stacks) {
		Boon boon = boons.get(type);
		for(int i = 0; i<stacks;i++){
			boon.apply(duration, source);
		}
		
	}

	public boolean hasBoon(BoonType quickness) {
		return boons.get(quickness).getStacks() > 0;
	}
	
	
}
