package systems.rine.pb.simulation.time;

import systems.rine.pb.simulation.BoonListener;
import systems.rine.pb.simulation.BoonType;
import systems.rine.pb.simulation.Target;

public class Event implements Comparable<Event>{
	private long when;
	private Target source;
	private Action action;
	private EventAffection affectedBy;
	private TimeManager timeManager;
	private BoonListener boonListener;
	
	protected Event(long _when, Target source, EventAffection affectedBy, Action action, TimeManager timeManager) {
		this.when = _when;
		this.source = source;
		this.affectedBy = affectedBy;
		this.action = action;
		this.timeManager = timeManager;
		if(affectedBy == EventAffection.Quickness) {
			calcOffset();
			boonListener = source.registerBoonListener(BoonType.Quickness, (stackCount, applied) ->{
				long diff = when - timeManager.getTime();
				if(stackCount == 1 && applied) {
					when = timeManager.getTime() + (long) (diff / 1.5);
				}else {
					when = timeManager.getTime() + (long) (diff * 1.5);
				}
				timeManager.updateEvent(this);
			});
		}
	}
	
	/**
	 * only call this once after updating when
	 */
	private void calcOffset() {
		if(source.hasBoon(BoonType.Quickness)) {
			long diff = when - timeManager.getTime();
			when =  timeManager.getTime() + (long) (diff / 1.5);
			timeManager.updateEvent(this);
		}		
	}
	
	protected void dispose() {
		if(boonListener != null) {
			source.removeBoonListener(boonListener);
		}
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
		calcOffset();
	}
	
	public Target getSource() {
		return source;
	}

}