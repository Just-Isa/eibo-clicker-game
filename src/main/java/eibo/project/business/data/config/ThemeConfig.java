package eibo.project.business.data.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eibo.project.business.data.config.util.Themes;
import eibo.project.business.services.IncrementalGame.Elements;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class ThemeConfig {
	
	private static final String DEFAULT_THEME_PATH = "config/themes.json";
	
	private static final int UPGRADE_IMAGE_SIZE = 125; 
	private static final int CLICKER_IMAGE_SIZE = 600;
	
	private static ThemeConfig instance;
	
	private ObjectProperty<Themes> activeThemeProperty;
		
	private ObjectProperty<Image> clickableImageProperty;
	private ObjectProperty<Image> clickedImageProperty;
	
	private List<StringProperty> upgradeNames;
	private List<ObjectProperty<Image>> upgradeImages;
	
	private StringProperty currencyNameProperty;
	
	private ThemeConfig() {
		initEmpty();
	}
	
	public static ThemeConfig instance() {
		
		if (instance == null)
			instance = new ThemeConfig();
		
		return instance;
	}
	
	public StringProperty currencyNameProperty() {
		return currencyNameProperty;
	}

	public ObjectProperty<Image> clickableImageProperty() {
		return clickableImageProperty;
	}
	
	public ObjectProperty<Image> clickedImageProperty() {
		return clickedImageProperty;
	}
	
	public StringProperty upgradeNameProperty(int index) {
		return upgradeNames.get(index);
	}
	
	public ObjectProperty<Image> upgradeImageProperty(int index) {
		return upgradeImages.get(index);
	}
	
	public void switchTheme() {
		
		var currentTheme = activeThemeProperty.get();
		var nextTheme = (currentTheme == null) ? Themes.GOOD : currentTheme.nextTheme();
		
		activeThemeProperty.set(nextTheme);
		
		try {
			loadTheme(nextTheme);
		} catch (IOException e) {
			setDefaults();
		}
	}
	
	private void loadTheme(Themes theme) throws JsonProcessingException, IOException {
		
		var mapper          = new ObjectMapper();
		var themeTree       = mapper.readTree(new File(DEFAULT_THEME_PATH));
		
		var activeThemeTree = themeTree.get(theme.getName());
		
		var allUpgrades   = activeThemeTree.get("upgrades");
		var currencyName  = activeThemeTree.get("currency").asText();
		var clickablePath = activeThemeTree.get("clickable_image").asText();
		var clickedPath	  = activeThemeTree.get("clicked_image").asText();
		
		var clickableImage = new Image(clickablePath, CLICKER_IMAGE_SIZE, CLICKER_IMAGE_SIZE, true, true);
		var clickedImage   = new Image(clickedPath, CLICKER_IMAGE_SIZE, CLICKER_IMAGE_SIZE, true, true);
		
		currencyNameProperty.set(currencyName);
		
		clickableImageProperty.set(clickableImage);
		clickedImageProperty.set(clickedImage);
		
		for (var element : Elements.values()) {
			
			var currentUpgrade = allUpgrades.get(element.ordinal());
			
			var currentName = currentUpgrade.get("name").asText();			
			var currentPath = currentUpgrade.get("image").asText();
			
			var currentImage = new Image(currentPath, UPGRADE_IMAGE_SIZE, UPGRADE_IMAGE_SIZE, true, true);
			
			upgradeNames.get(element.ordinal()).set(currentName);
			upgradeImages.get(element.ordinal()).set(currentImage);
		}	
		
	}
	
	private void setDefaults() {
		
		currencyNameProperty.set("Number");
		clickableImageProperty.set(null);
		clickedImageProperty.set(null);
		
		for (var element : Elements.values()) {
			upgradeNames.get(element.ordinal()).set("Upgrade " + element.ordinal());
			upgradeImages.get(element.ordinal()).set(null);
		}
	}
	
	private void initEmpty() {
		
		currencyNameProperty  = new SimpleStringProperty();
		
		clickableImageProperty = new SimpleObjectProperty<>();
		clickedImageProperty   = new SimpleObjectProperty<>();
		
		activeThemeProperty = new SimpleObjectProperty<>();
		
		upgradeNames  = new ArrayList<>();
		upgradeImages = new ArrayList<>();
		
		for (@SuppressWarnings("unused") var element : Elements.values()) {
			upgradeNames.add(new SimpleStringProperty());
			upgradeImages.add(new SimpleObjectProperty<Image>());
		}
	}
	
}
