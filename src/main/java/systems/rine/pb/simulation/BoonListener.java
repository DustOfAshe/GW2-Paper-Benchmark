package systems.rine.pb.simulation;

/**
 * Single method interface to keep it lambda friendly
 * @author Crigges
 *
 */
public interface BoonListener {
	public BoonType type = null;
	/**
	 * Called when the boon state changes this can happen when a stack was, applied,
	 * lost, overridden, ignored (overcapped). This event triggers even when
	 * overcapped since traits that triggers when boons are applied also triggers in
	 * these cases.
	 * 
	 * @param stackCount The current amount of stacks for this boon. When 0 the boon
	 *                   is lost
	 * @param gotNew     If true a boon has been applied if false a boon has been
	 *                   lost
	 */
	void changed(int stackCount, boolean gotNew);

}
