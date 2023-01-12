package eibo.project.business.data.config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eibo.project.business.data.config.util.Labels;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LabelConfig {
	
	
	private static final String DEFAULT_PATH = "config/labels.json";
	
	private static LabelConfig instance;
	
	private Map<Labels, StringProperty> labelMap;
	private String configPath;

	private LabelConfig() {
		this.configPath = DEFAULT_PATH;
		initEmpty();
	}
	
	public static LabelConfig instance() {
		
		if (instance == null)
			instance = new LabelConfig();
		
		return instance;
	}
	
	public StringProperty labelTextProperty(Labels name) {
		return labelMap.get(name);
	}
	
	public void update() {
		try {
			load();
		} catch (IOException e) {
			setDefaults();
		}
	}
	
	public void load(String configPath) {
		this.configPath = configPath;
		update();
	}
	
	private void load() throws JsonProcessingException, IOException {
		
		var mapper = new ObjectMapper();
		var tree   = mapper.readTree(new File(configPath));
		
		for (var name : Labels.values()) {
			
			var property = labelMap.get(name);
			var toSet    = tree.get(name.key()).asText();
			
			property.set(toSet);
		}
	}
	
	private void initEmpty() {
		
		labelMap = new HashMap<>();
		
		for (var name : Labels.values())
			labelMap.put(name, new SimpleStringProperty());
	}
	
	private void setDefaults() {
		labelMap.get(Labels.AMOUNT).set("Amount");
		labelMap.get(Labels.BUY).set("Buy");
		labelMap.get(Labels.COST).set("Cost");
		labelMap.get(Labels.GAIN).set("Gain");
		labelMap.get(Labels.NOT_ENOUGH).set("Not enough");
		
		labelMap.get(Labels.PER_CLICK).set("per click");
		labelMap.get(Labels.TIME_INTERVAL).set("per second");
		
		labelMap.get(Labels.FIRST_SCENE).set("First scene");
		labelMap.get(Labels.SECOND_SCENE).set("Second scene");
		labelMap.get(Labels.THIRD_SCENE).set("Third Scene");
	}
	
	
	
	
	
	
	
}
