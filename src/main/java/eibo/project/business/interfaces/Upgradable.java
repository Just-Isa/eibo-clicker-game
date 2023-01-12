package eibo.project.business.interfaces;

/**
 * This interface describes the behaviour of an upgradable object.
 *
 */
public interface Upgradable {
	
	/**
	 * Getter for current amount of purchased upgrades. 
	 * @return (int) Current amount of purchased upgrades.
	 */
	int getAmount();
	
	/**
	 * Getter for current cost.
	 * @return (double) Current cost of purchasing an upgrade. 
	 */
	double getCost();
	
	/**
	 * Getter for current value.
	 * @return (double) Current value of the upgraded object.
	 */
	double getValue();
	
	/**
	 * Getter for value per upgrade.
	 * @return (double) Value per upgrade bought.
	 */
	double getValuePerUpgrade();

	/**
	 * Getter for value base cost.
	 * @return (double) Initial cost of the upgrade.
	 */
	double getBaseCost();
	
	/**
	 * Performs an upgrade.
	 */
	void buyUpgrade();
	
}
