package systems.rine.pb.simulation;

public enum BoonType {
	Aegis(9, false), Alacrity(9, false), Fury(9, false),
	Might(25, true), Protection(5, false), Quickness(5, false),
	Regeneration(5, false), Resistance(5, false), 
	Retailiation(5, false), Stability(25, true),
	Swiftness(9, false), Vigor(5, false);

	private int stackLimit;
	private boolean stacksIntensity;
	
	
	private BoonType(int stackLimit, boolean stacksIntensity) {
		this.stackLimit = stackLimit;
		this.stacksIntensity = stacksIntensity;
	}


	public int getStackLimit() {
		return stackLimit;
	}


	public boolean stacksIntensity() {
		return stacksIntensity;
	}
	
	public Effect getEffect() {
		return new Effect() {
			
			@Override
			void remove(Target source, Target owner) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			void apply(Target source, Target owner) {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	
}
	
