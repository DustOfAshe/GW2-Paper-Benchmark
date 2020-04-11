package systems.rine.pb.simulation;

public class Simulation {
	private TimeManager timeManager;
	
	public Simulation() {
		timeManager = new TimeManager();
	}
	
	public void run() {
		timeManager.start();
	}
	
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		Simulation simulation = new Simulation();
		simulation.run();
		System.out.println("Total execution time: " + (System.currentTimeMillis() - time) + "ms");
	}

}
