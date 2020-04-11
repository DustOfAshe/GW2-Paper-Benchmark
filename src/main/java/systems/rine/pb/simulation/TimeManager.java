package systems.rine.pb.simulation;

import java.util.PriorityQueue;

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
			currentTime = current.offset;
			long result = current.action.run(currentTime);
			if(result > 0) {
				current.offset += result;
				eventQueue.offer(current);
			}
		}
	}
	
	public void stop() {
		stop = true;
	}
	
	public Event registerEvent(int offset, Action action) {
		Event event = new Event(offset, action);
		eventQueue.offer(event);
		return event;
	}
	
	public void removeEvent(Event event) {
		eventQueue.remove(event);
	}
	
	static class Event implements Comparable<Event>{
		private long offset;
		private Action action;
		
		public Event(long offset, Action action) {
			this.offset = offset;
			this.action = action;
		}

		@Override
		public int compareTo(Event other) {
			if(offset > other.offset) {
				return 1;
			}else if(offset < other.offset){
				return -1;
			}else {
				return 0;
			}
		}
	
	}
	
}
