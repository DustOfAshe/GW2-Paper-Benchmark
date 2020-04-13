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
	protected PriorityQueue<Event> boonEndEvents;
	protected Map<Event, Target> boonSource;
	protected int stackCount;
	protected Target owner;
	protected List<BoonListener> listeners;
	
	public Boon(TimeManager timeManager, Target owner) {
		this.timeManager = timeManager;
		boonEndEvents = new PriorityQueue<>();
		boonSource = new HashMap<>();
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
		for(BoonListener listener : listeners) {
			listener.changed(stackCount, gotNew);
		}
	}
	
	public void apply(int duration, Target source) {
		if(stackCount == type.getStackLimit()) {
			Event shortest = boonEndEvents.peek();
			if(duration > shortest.getWhen() - timeManager.getTime()) {
				shortest.setOffset(duration);
				boonSource.put(shortest, source);
			}else {
				logger.info("Boon overcap detected");
			}
		}else if(type.stacksIntensity()) {
			type.getEffect().apply(source, owner);
			boonEndEvents.add(timeManager.registerEvent(duration, owner, EventAffection.None, (event) -> {
				boonEndEvents.remove(event);
				type.getEffect().remove(source, owner);
				notifyListeners(false);
				return -1;
			}));
		}else {
			if(boonEndEvents.isEmpty()) {
				type.getEffect().apply(source, owner);
			}
			Event temp = timeManager.registerEvent(duration, owner, EventAffection.None, (event) -> {
				boonEndEvents.remove(event);
				type.getEffect().apply(source, owner);
				boonSource.remove(event);
				notifyListeners(false);
				if(!boonEndEvents.isEmpty()) {
					type.getEffect().apply(boonSource.get(boonEndEvents.peek()), owner);
				}
				return -1;
			});
			boonSource.put(temp, source);
			boonEndEvents.add(temp);
		}
		notifyListeners(true);
	}
	
	
}
