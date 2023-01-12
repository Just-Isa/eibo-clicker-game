package eibo.project.business.data;

import eibo.project.business.interfaces.Upgradable;
import eibo.project.business.util.Rounder;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Upgrade implements Upgradable {

	private static final double MULTIPLIER = 1.15;
	
	private final double BASE_COST;
	private final double VALUE_PER_UPGRADE;

	private final int BASE_AMOUNT;

	private IntegerProperty amount;
	private DoubleProperty cost;
	private DoubleProperty value;
	
	public static class Builder {
		
		// required parameters
		private final double baseCost;
		private final double valuePerUpgrade;
		
		// optional Parameters
		private int baseAmount = 0;
		private int amount = 0;
		
		public Builder(double baseCost, double valuePerUpgrade) {
			this.baseCost = baseCost;
			this.valuePerUpgrade = valuePerUpgrade;
		}
		
		public Builder baseAmount(int baseAmount) {
			this.baseAmount = baseAmount;
			return this;
		}
		
		public Builder amount(int amount) {
			this.amount = amount;
			return this;
		}
		
		public Upgrade build() {
			return new Upgrade(this);
		}
		
	}
	
	public Upgrade(Builder builder) {
		
		BASE_COST = builder.baseCost;
		BASE_AMOUNT = builder.baseAmount;
		VALUE_PER_UPGRADE = builder.valuePerUpgrade;
		
		amount = new SimpleIntegerProperty(builder.amount);
		
		cost  = new SimpleDoubleProperty(calculateCost());
		value = new SimpleDoubleProperty(calculateValue());
	}
	
	public IntegerProperty amountProperty() {
		return amount;
	}
	
	public DoubleProperty valueProperty() {
		return value;
	}
	
	public DoubleProperty costProperty() {
		return cost;
	}

	@Override
	public int getAmount() {
		return amount.get();
	}

	@Override
	public double getCost() {
		return cost.get();
	}
	
	@Override
	public double getValue() {
		return value.get();
	}
	
	@Override
	public double getValuePerUpgrade() {
		return VALUE_PER_UPGRADE;
	}
	
	@Override
	public double getBaseCost() {
		return BASE_COST;
	}
	
	public double getBaseAmount() {
		return BASE_AMOUNT;
	}
	
	@Override
	public void buyUpgrade() {
		
		amount.set(amount.get() + 1);
		
		cost.set(calculateCost());
		value.set(calculateValue());
	}
	
	public void reset() {
		amount.set(BASE_AMOUNT);
		cost.set(BASE_COST);
		value.set(calculateValue());
	}
	
	private double calculateCost() {
		var newCost = BASE_COST * Math.pow(MULTIPLIER, amount.get());
		return Rounder.round(newCost);
	}
	
	private double calculateValue() {
		var newValue = VALUE_PER_UPGRADE * amount.get();
		return Rounder.round(newValue);
	}

}
