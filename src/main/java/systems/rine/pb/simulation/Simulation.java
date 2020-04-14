package systems.rine.pb.simulation;

import systems.rine.pb.simulation.time.EventAffection;
import systems.rine.pb.simulation.time.TimeManager;

public class Simulation {
	private TimeManager timeManager;
	private Target fakeChrono;
	private int attackCount;
	
	public Simulation() {
		timeManager = new TimeManager();
		fakeChrono = new Target(timeManager);
		attackCount = 0;
		timeManager.registerEvent(1000, fakeChrono, EventAffection.Quickness, (event) -> {
			System.out.println("attacked at " + timeManager.getTime());
			attackCount++;
			if(attackCount == 5) {
				timeManager.registerEvent(500, fakeChrono, EventAffection.None, (event2) -> {
					System.out.println("Gained Quickness");
					fakeChrono.applyBoon(BoonType.Quickness, 1000);
					fakeChrono.registerBoonListener(BoonType.Quickness, (stackCount, applied) -> {
						if(stackCount == 0) {
							System.out.println("Lost Quickness");
						}	
					});
					return -1;
				});	
			}
			if(attackCount == 10) {
				return -1;
			}
			return 1000;
		});
	}
	
	public void run() {
		timeManager.start();
	}
	
	public long getSimulationTime() {
		return timeManager.getTime();
	}
	
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		Simulation simulation = new Simulation();
		simulation.run();
		System.out.println("Time of simulation: " +  simulation.getSimulationTime() + "ms");
		System.out.println("Total execution time: " + (System.currentTimeMillis() - time) + "ms");
	}

}
