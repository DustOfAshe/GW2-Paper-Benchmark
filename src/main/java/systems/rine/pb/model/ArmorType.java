package systems.rine.pb.model;


public enum ArmorType {

	Boots, Coat, Gloves, Helm, HelmAquatic, Leggings, Shoulders;

	public float getHiddenNumber() {
		switch (this) {
		case Boots: case Gloves: case Shoulders:
			return 134.78f;
		case Helm: case HelmAquatic:
			return 180.67f;
		case Leggings:
			return 269.57f;
		case Coat:
			return 404f;
		default:
			return 0;
		}
	}

}
