package systems.rine.pb.model;

public class Attribute implements Comparable<Attribute> {
	private int value;
	private String name;
	
	public Attribute(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public int compareTo(Attribute other) {
		if(value < other.value) {
			return 1;
		}else if(value > other.value) {
			return -1;
		}else {
			return 0;
		}
	}
	
	public String getDescription() {
		return "+" + value + " " + name;
	}
	
	public boolean notZero() {
		return value != 0;
	}
	
}
