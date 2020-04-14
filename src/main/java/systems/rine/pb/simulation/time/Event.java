package systems.rine.pb.simulation.time;

import systems.rine.pb.simulation.Target;

public class Event implements Comparable<Event>{
	private long when;
	private Target source;
	private Action action;
	private EventAffection affectedBy;
	private TimeManager timeManager;
	
	public Event(long when, Target source, EventAffection affectedBy, Action action, TimeManager timeManager) {
		this.when = when;
		this.source = source;
		this.affectedBy = affectedBy;
		this.action = action;
	}

	@Override
	public int compareTo(Event other) {
		if(when > other.when) {
			return 1;
		}else if(when < other.when){
			return -1;
		}else {
			return 0;
		}
	}
	
	public Action getAction() {
		return action;
	}
	
	public EventAffection getEventAffection() {
		return affectedBy;
	}

	public long getWhen() {
		return when;
	}
	
	public void setOffset(long offset) {
		this.when = timeManager.getTime() + offset;
		timeManager.removeEvent(this);
		timeManager.addEvent(this);
	}
	
	public Target getSource() {
		return source;
	}

	public void addOffset(long result) {
		when += result;
	}

}