package systems.rine.pb.model;

import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.items.ApiItemstatAttribute;

public class Stats {
	private int concentration;
	private int conditionDamage;
	private int expertise;
	private int ferocity;
	private int healingPower;
	private int power;
	private int precision;
	private int toughness;
	private int vitality;
	
	public Stats() {
	
	}
	
	public Stats(float hiddenNumber, ApiItemStat stats) {
		for(ApiItemstatAttribute attribute : stats.attributes) {
			String name = attribute.attribute;
			if(name.equals("BoonDuration")) {
				concentration += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("ConditionDamage")) {
				conditionDamage += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("ConditionDuration")) {
				expertise += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("CritDamage")) {
				ferocity += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("Healing")) {
				healingPower += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("Power")) {
				power += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("Precision")) {
				precision += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("Toughness")) {
				toughness += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}else if(name.equals("Vitality")) {
				vitality += calcStat(hiddenNumber, attribute.multiplier.floatValue(), attribute.value);
			}
		}
	}
	
	private int calcStat(float hiddenNumber, float multipler, int value) {
		return Math.round(hiddenNumber * multipler + value);
	}

	public void add(Stats other) {
		concentration += other.concentration;
		conditionDamage += other.conditionDamage;
		expertise += other.expertise;
		ferocity += other.ferocity;
		healingPower += other.healingPower;
		power += other.power;
		precision = other.precision;
		toughness = other.toughness;
		vitality = other.vitality;
	}

	public int getConcentration() {
		return concentration;
	}

	public int getConditionDamage() {
		return conditionDamage;
	}

	public int getExpertise() {
		return expertise;
	}

	public int getFerocity() {
		return ferocity;
	}

	public int getHealingPower() {
		return healingPower;
	}

	public int getPower() {
		return power;
	}

	public int getPrecision() {
		return precision;
	}

	public int getToughness() {
		return toughness;
	}

	public int getVitality() {
		return vitality;
	}
	
	@Override
	public String toString() {
		return "Stats [concentration=" + concentration + ", conditionDamage=" + conditionDamage + ", expertise="
				+ expertise + ", ferocity=" + ferocity + ", healingPower=" + healingPower + ", power=" + power
				+ ", precision=" + precision + ", toughness=" + toughness + ", vitality=" + vitality + "]";
	}

}
