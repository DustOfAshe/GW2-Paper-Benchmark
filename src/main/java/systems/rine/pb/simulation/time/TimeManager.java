package systems.rine.pb.simulation.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import systems.rine.pb.simulation.Target;

public class TimeManager {
	private PriorityQueue<Event> eventQueue;
	private Map<Target, List<Event>> quicknessEvents = new HashMap<>();
	private Map<Target, List<Event>> alacrityEvents = new HashMap<>();
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
	
	public void updateAlacrityEvents(boolean hasQuickness) {
		
	}
	
	public Event registerEvent(long offset, Target source, EventAffection affectedBy, Action action) {
		Event event = new Event(currentTime + offset, source, affectedBy, action, this);
		if(affectedBy == EventAffection.Quickness) {
			if(!quicknessEvents.containsKey(source)) {
				quicknessEvents.put(source, new ArrayList<Event>());
			}
			quicknessEvents.get(source).add(event);
			source.addQuicknessListener((active) -> updateQuicknessEvents(active, source)); 
		}else if(affectedBy == EventAffection.Alacrity) {
			if(!alacrityEvents.containsKey(source)) {
				alacrityEvents.put(source, new ArrayList<Event>());
			}
			alacrityEvents.get(source).add(event);
			source.addAlacrityListener((active) -> updateAlacrityEvents(active)); 
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
