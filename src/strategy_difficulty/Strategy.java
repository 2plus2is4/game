package strategy_difficulty;

public interface Strategy {
	/**
	 * 
	 * @return speed of moving objects
	 */
	public int speed();

	/**
	 * 
	 * @return change in starting time.
	 */
	public int time();
}
