package systems.rine.pb.simulation.time;

import java.util.ArrayList;
import java.util.List;

import systems.rine.pb.simulation.StateListener;
import systems.rine.pb.simulation.Target;

public class Event implements Comparable<Event>{
	private long when;
	private Target source;
	private Action action;
	private List<StateListener> quicknessListeners = new ArrayList<StateListener>();
	private List<StateListener> alacrityListeners = new ArrayList<StateListener>();
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
	
	public void addQuicknessListener(StateListener listener) {
		quicknessListeners.add(listener);
		source.addQuicknessListener(listener);
	}
	
	public void addAlacrityListener(StateListener listener) {
		alacrityListeners.add(listener);
		source.addAlacrityListener(listener);
	}
	
	public void removeListeners() {
		for(StateListener listener: quicknessListeners) {
			source.removeQuicknessListener(listener);
		}
		for(StateListener listener: alacrityListeners) {
			source.removeAlacrityListener(listener);
		}	
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