package systems.rine.pb.simulation;

import java.util.ArrayList;
import java.util.List;

import systems.rine.pb.model.Target;

public class Event implements Comparable<Event>{
	private long offset;
	private Target source;
	private Action action;
	private List<StateListener> quicknessListeners = new ArrayList<StateListener>();
	private List<StateListener> alacrityListeners = new ArrayList<StateListener>();
	private EventAffection affectedBy;
	
	public Event(long offset, Target source, EventAffection affectedBy, Action action) {
		this.offset = offset;
		this.source = source;
		this.affectedBy = affectedBy;
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

	public long getOffset() {
		return offset;
	}
	
	public void setOffset(long offset) {
		this.offset = offset;
	}
	
	public Target getSource() {
		return source;
	}

	public void addOffset(long result) {
		offset += result;
	}

}