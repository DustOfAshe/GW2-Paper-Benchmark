package systems.rine.pb.simulation.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import systems.rine.pb.simulation.BoonListener;
import systems.rine.pb.simulation.BoonType;
import systems.rine.pb.simulation.Target;

public class TimeManager {
	private PriorityQueue<Event> eventQueue;
	private Map<Target, List<Event>> quicknessEvents = new HashMap<>();
	private Map<Target, List<Event>> alacrityEvents = new HashMap<>();
	private Map<Event, BoonListener> boonListener = new HashMap<>();
	private long currentTime;
	private boolean stop;
	
	public TimeManager() {
		eventQueue = new PriorityQueue<>();
	}
	
	public void start() {
		stop = false;
		while(!eventQueue.isEmpty() && !stop) {
			Event current = eventQueue.poll();
			currentTime = current.getWhen();
			long result = current.getAction().run(current);
			if(result > 0) {
				current.addOffset(result);
				eventQueue.offer(current);
			}else {
				
			}
		}
	}
	
	public void stop() {
		stop = true;
	}
	
	public void updateQuicknessEvents(boolean hasQuickness, Target target) {
		for(Event event : quicknessEvents.get(target)) {
			long diff = event.getWhen() - currentTime;
			if(hasQuickness) {
				diff /= 1.5;
			}else {
				diff *= 1.5;
			}
			event.setOffset(diff);
			eventQueue.remove(event);
			eventQueue.add(event);
		}
	}
	
	public void updateAlacrityEvents(boolean hasQuickness, Target target) {
		
	}
	
	public Event registerEvent(long offset, Target source, EventAffection affectedBy, Action action) {
		Event event = new Event(currentTime + offset, source, affectedBy, action, this);
		if(affectedBy == EventAffection.Quickness) {
			if(!quicknessEvents.containsKey(source)) {
				quicknessEvents.put(source, new ArrayList<Event>());
			}
			quicknessEvents.get(source).add(event);
			boonListener.put(event, source.registerBoonListener(BoonType.Quickness, (stackCount, active) -> {
				if(stackCount == 1 && active) {
					updateQuicknessEvents(true, source);
				}else if(stackCount == 0) {
					updateQuicknessEvents(false, source);
				}
			})); 
		}else if(affectedBy == EventAffection.Alacrity) {
			if(!alacrityEvents.containsKey(source)) {
				alacrityEvents.put(source, new ArrayList<Event>());
			}
			alacrityEvents.get(source).add(event);
			boonListener.put(event, source.registerBoonListener(BoonType.Alacrity, (stackCount, active) -> {
				if(stackCount == 1 && active) {
					updateAlacrityEvents(active, source);
				}else if(stackCount == 0) {
					updateAlacrityEvents(false, source);
				}	
			})); 
		}
		eventQueue.offer(event);
		return event;
	}
	
	public void removeEvent(Event event) {
		eventQueue.remove(event);
	}

	public long getTime() {
		return currentTime;
	}

	protected void addEvent(Event event) {
		eventQueue.offer(event);
	}
	
}
