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
				current.setOffset(result);
				eventQueue.offer(current);
			}else {
				current.dispose();
			}
		}
	}
	
	public void stop() {
		stop = true;
	}
	
	
	public Event registerEvent(long offset, Target source, EventAffection affectedBy, Action action) {
		Event event = new Event(currentTime + offset, source, affectedBy, action, this);
		eventQueue.offer(event);
		return event;
	}
	
	public void removeEvent(Event event) {
		eventQueue.remove(event);
		event.dispose();
	}

	public long getTime() {
		return currentTime;
	}

	public void updateEvent(Event event) {
		if(eventQueue.remove(event)) {
			eventQueue.add(event);
		}
	}
	
}
