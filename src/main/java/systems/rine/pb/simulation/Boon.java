package systems.rine.pb.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import systems.rine.pb.simulation.time.Event;
import systems.rine.pb.simulation.time.EventAffection;
import systems.rine.pb.simulation.time.TimeManager;

public class Boon {
	private static final Logger logger = LogManager.getLogger(Boon.class);
	protected BoonType type;
	protected TimeManager timeManager;
	protected PriorityQueue<Event> boonEndEvents; // For intensity stacking boons
	protected PriorityQueue<BoonStack> durationStacks; // For duration stacking boons
	protected Event durationEvent;
	protected int stackCount;
	protected Target owner;
	protected List<BoonListener> listeners;

	public Boon(BoonType type, TimeManager timeManager, Target owner) {
		this.type = type;
		this.timeManager = timeManager;
		stackCount = 0;
		boonEndEvents = new PriorityQueue<>();
		durationStacks = new PriorityQueue<>();
		listeners = new CopyOnWriteArrayList<>();
		this.owner = owner;
	}

	public void addListener(BoonListener listener) {
		listeners.add(listener);
	}

	public void removeListener(BoonListener listener) {
		listeners.remove(listener);
	}

	private void notifyListeners(boolean gotNew) {
		for (BoonListener listener : listeners) {
			listener.changed(stackCount, gotNew);
		}
	}

	public void apply(int duration, Target source) {
		if(type.stacksIntensity()) {
			type.getEffect().apply(source, owner);
			stackCount++;
			boonEndEvents.add(timeManager.registerEvent(duration, owner, EventAffection.None, (event) -> {
				boonEndEvents.remove(event);
				stackCount--;
				type.getEffect().remove(source, owner);
				notifyListeners(false);
				return -1;
			}));
		}else {
			if(stackCount == type.getStackLimit()) {
				if(duration > durationEvent.getWhen() - timeManager.getTime()) {
					durationEvent.setOffset(duration);
					logger.info("Partial overcap detected");
				}else {
					logger.info("Boon overcap detected");
				}
			}else {				
				if(durationEvent == null) {
					type.getEffect().apply(source, owner);
					durationEvent = timeManager.registerEvent(duration, owner, EventAffection.None, (event) -> {
						type.getEffect().remove(source, owner);
						stackCount--;
						notifyListeners(false);
						BoonStack nextStack = durationStacks.poll();
						if(nextStack != null) {
							type.getEffect().apply(nextStack.source, owner);
							return nextStack.duration;
						}else {
							durationEvent = null;
							return -1;
						}
					});
				}else {
					durationStacks.add(new BoonStack(duration, source));
				}
				stackCount++;
			}		
		}
		notifyListeners(true);
	}

	class BoonStack implements Comparable<BoonStack> {
		long duration;
		Target source;
		
		

		public BoonStack(long duration, Target source) {
	this.duration = duration;
	this.source = source;
}
@Override
		public int compareTo(BoonStack other) {
			if (duration > other.duration) {
				return 1;
			} else if (duration < other.duration) {
				return -1;
			} else {
				return 0;
			}
		}

	}

	public int getStacks() {
		return stackCount;
	}

}
