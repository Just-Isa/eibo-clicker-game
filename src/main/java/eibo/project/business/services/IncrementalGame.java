package eibo.project.business.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import eibo.project.business.data.Upgrade;
import eibo.project.business.exceptions.NotEnoughCurrencyException;
import eibo.project.business.interfaces.Game;
import eibo.project.business.util.Rounder;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class IncrementalGame implements Game {
	
	public static final String SAVEGAME_PATH = "config/savegame.json";
	
	public static final int SAVE_INTERVAL = 30;
	public static final int GAIN_INTERVAL = 1;
	
	public enum Elements {
		CLICKER,
		UPGRADE_1,
		UPGRADE_2,
		UPGRADE_3,
		UPGRADE_4,
		UPGRADE_5,
		UPGRADE_6,
		UPGRADE_7
	}
	
	// https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/ScheduledExecutorService.html
	private ScheduledExecutorService scheduler;
	
	private DoubleProperty currencyAmount;
	private DoubleProperty currencyGain;
	
	private Map<Elements, Upgrade> upgradeMap;
	
	public IncrementalGame() {
		
		scheduler = Executors.newScheduledThreadPool(1);
		
		upgradeMap = new TreeMap<>(); // TreeMap instead of HashMap to keep order!
				
		currencyAmount = new SimpleDoubleProperty();
		currencyGain   = new SimpleDoubleProperty();
		
		try {
			load();
		} catch (IOException e) {
			initDefaults();
		}
		
	}
	
	public double getCurrencyAmount() {
		return currencyAmount.get();
	}
	
	public double getCurrencyGain() {
		return currencyGain.get();
	}
	
	public Upgrade get(Elements element) {
		return upgradeMap.get(element);
	}
	
	public List<Upgrade> getAllUpgrades() {
		return new ArrayList<>(upgradeMap.values());
	}	
	
	public DoubleProperty currencyAmountProperty() {
		return currencyAmount;
	}
	
	public DoubleProperty currencyGainProperty() {
		return currencyGain;
	}
	
	@Override
	public void start() {
		scheduler.scheduleAtFixedRate(() -> Platform.runLater(() -> update()) , 0, GAIN_INTERVAL, TimeUnit.SECONDS);
		scheduler.scheduleAtFixedRate(() -> Platform.runLater(() -> save()), SAVE_INTERVAL, SAVE_INTERVAL, TimeUnit.SECONDS);
	}
	
	@Override
	public void stop() {
		scheduler.shutdown();
		save();
	}
	
	@Override
	public void save() {
		var mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(SAVEGAME_PATH), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void load() throws JsonProcessingException, IOException {
		
		var mapper      = new ObjectMapper();
		var jsonTree    = mapper.readTree(new File(SAVEGAME_PATH));
		var allUpgrades = jsonTree.get("allUpgrades");
		
		currencyAmount.set(jsonTree.get("currencyAmount").asDouble());
		currencyGain.set(jsonTree.get("currencyGain").asDouble());
		
		for (var element : Elements.values()) {
			
			var currentUpgrade  = allUpgrades.get(element.ordinal());
			
			var baseCost        = currentUpgrade.get("baseCost").asDouble();
			var baseAmount		= currentUpgrade.get("baseAmount").asInt();
			var valuePerUpgrade = currentUpgrade.get("valuePerUpgrade").asDouble();
			var amount          = currentUpgrade.get("amount").asInt();
			
			upgradeMap.put(element, new Upgrade.Builder(baseCost, valuePerUpgrade).baseAmount(baseAmount).amount(amount).build());
		}	
	}
	
	public void reset() {
		
		currencyAmount.set(0);
		currencyGain.set(0);
		
		for (var element : Elements.values())
			upgradeMap.get(element).reset();
		save();
	}
	
	public void buy(Elements element) throws NotEnoughCurrencyException {
		var toBuy = upgradeMap.get(element);
		buy(toBuy);
	}
	
	public void buy(Upgrade toBuy) throws NotEnoughCurrencyException {
		
		if (upgradeMap.containsValue(toBuy)) {		
			
			if (currencyAmount.get() < toBuy.getCost())
				throw new NotEnoughCurrencyException("Not enough currency to buy this upgrade!");
			
			currencyAmount.set(currencyAmount.get() - toBuy.getCost());
			toBuy.buyUpgrade();
			
			if (!toBuy.equals(upgradeMap.get(Elements.CLICKER))) 
				currencyGain.set(currencyGain.get() + toBuy.getValuePerUpgrade());	
		}		
	}
	
	public void gainCurrency() {
		currencyAmount.set(currencyAmount.get() + upgradeMap.get(Elements.CLICKER).getAmount());
	}
	
	private void update() {
		var newAmount = currencyAmount.get() + currencyGain.get();
		currencyAmount.set(Rounder.round(newAmount));
	}
	
	private void initDefaults() {
		currencyAmount.set(0);
		currencyGain.set(0);
		
		upgradeMap.put(Elements.CLICKER,   new Upgrade.Builder(10, 1).baseAmount(1).amount(1).build());
		upgradeMap.put(Elements.UPGRADE_1, new Upgrade.Builder(15, 0.1).build());
		upgradeMap.put(Elements.UPGRADE_2, new Upgrade.Builder(100, 0.5).build());
		upgradeMap.put(Elements.UPGRADE_3, new Upgrade.Builder(500, 4).build());
		upgradeMap.put(Elements.UPGRADE_4, new Upgrade.Builder(3_000, 10).build());
		upgradeMap.put(Elements.UPGRADE_5, new Upgrade.Builder(5_000, 25).build());
		upgradeMap.put(Elements.UPGRADE_6, new Upgrade.Builder(10_000, 40).build());
		upgradeMap.put(Elements.UPGRADE_7, new Upgrade.Builder(200_000, 400).build());
	}
	
}
