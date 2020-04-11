package systems.rine.pb.model;

public enum ProfessionType { 
	Guardian(1645, WeightClass.Heavy), 
	Warrior(9212, WeightClass.Heavy),
	Engineer(5922, WeightClass.Medium), 
	Ranger(5922, WeightClass.Medium), 
	Thief(1645, WeightClass.Medium),
	Elementalist(1645, WeightClass.Light), 
	Mesmer(5922, WeightClass.Light), 
	Necromancer(9212, WeightClass.Light),
	Revenant(5922, WeightClass.Heavy);

	private int baseHp;
	private WeightClass weightClass;

	private ProfessionType(int baseHp, WeightClass weightClass) {
		this.baseHp = baseHp;
		this.weightClass = weightClass;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public WeightClass getWeightClass() {
		return weightClass;
	}

}
