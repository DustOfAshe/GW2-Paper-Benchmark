package systems.rine.pb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import systems.rine.pb.api.items.ApiItemStat;
import systems.rine.pb.api.items.ApiItemstatAttribute;

public class Stats {
	private String prefix;
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
		prefix = stats.name;
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

	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	public void setConditionDamage(int conditionDamage) {
		this.conditionDamage = conditionDamage;
	}

	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}

	public void setFerocity(int ferocity) {
		this.ferocity = ferocity;
	}

	public void setHealingPower(int healingPower) {
		this.healingPower = healingPower;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public void setToughness(int toughness) {
		this.toughness = toughness;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public String getDescription() {
		String result = "";
		List<Attribute> allAttributes = new ArrayList<Attribute>();
		allAttributes.add(new Attribute(power, "Power"));
		allAttributes.add(new Attribute(precision, "Precision"));
		allAttributes.add(new Attribute(ferocity, "Ferocity"));
		allAttributes.add(new Attribute(conditionDamage, "Condition Damage"));
		allAttributes.add(new Attribute(healingPower, "Healing Power"));
		allAttributes.add(new Attribute(toughness, "Toughness"));
		allAttributes.add(new Attribute(vitality, "Vitality"));
		allAttributes.add(new Attribute(expertise, "Expertise"));
		allAttributes.add(new Attribute(concentration, "Concentration"));
		if(prefix != null) {
			result += "[" + prefix + "]" + System.lineSeparator();
		}
		Collections.sort(allAttributes);
		for(Attribute attribute : allAttributes) {
			if(attribute.notZero()) {
				result += attribute.getDescription() + System.lineSeparator();
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Stats [concentration=" + concentration + ", conditionDamage=" + conditionDamage + ", expertise="
				+ expertise + ", ferocity=" + ferocity + ", healingPower=" + healingPower + ", power=" + power
				+ ", precision=" + precision + ", toughness=" + toughness + ", vitality=" + vitality + "]";
	}

	public String getPrefix() {
		return prefix;
	}

}
