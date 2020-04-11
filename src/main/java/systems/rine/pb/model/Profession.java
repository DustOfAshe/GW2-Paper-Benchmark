package systems.rine.pb.model;

import systems.rine.pb.api.professions.ApiProfession;

public class Profession {
	private ApiProfession apiProfession;
	private GW2Data gw2Data;
	private ProfessionType type;
	
	public Profession(ApiProfession apiProfession, GW2Data gw2Data) {
		this.apiProfession = apiProfession;
		this.gw2Data = gw2Data;	
		type = ProfessionType.valueOf(apiProfession.name);
	}

	public void create() {
		
	}
	
	public int getBaseHp() {
		return type.getBaseHp();
	}
	
	public WeightClass getWeightClass() {
		return type.getWeightClass();
	}

	public ProfessionType getType() {
		return type;
	}
	
}
