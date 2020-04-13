package systems.rine.pb.simulation;

public abstract class Effect {

	abstract void apply(Target source, Target owner);
	
	abstract void remove(Target source, Target owner);
	
}
