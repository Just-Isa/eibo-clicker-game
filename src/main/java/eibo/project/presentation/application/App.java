package eibo.project.presentation.application;

import java.util.HashMap;
import java.util.Map;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.ThemeConfig;
import eibo.project.business.services.BackgroundMusic;
import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.scenes.ClickerScene;
import eibo.project.presentation.scenes.SettingsScene;
import eibo.project.presentation.scenes.UpgradeScene;
import eibo.project.presentation.transitions.FadeIn;
import eibo.project.presentation.transitions.FadeIn.Directions;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
	
	public enum Scenes { CLICKER, UPGRADE, SETTINGS };
	
	private Map<Scenes, Pane> scenes;
	
	private Stage primaryStage;
	private IncrementalGame game;
	private BackgroundMusic musicPlayer;
	
	@Override 
	public void init() {
		
		musicPlayer = new BackgroundMusic();
		game        = new IncrementalGame();
		scenes      = new HashMap<>();
		
		ThemeConfig.instance().switchTheme();
		LabelConfig.instance().update();
		
		scenes.put(Scenes.CLICKER, new ClickerScene(game,this));
		scenes.put(Scenes.UPGRADE, new UpgradeScene(game, this));
		scenes.put(Scenes.SETTINGS, new SettingsScene(game,this));

		game.start();
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			this.primaryStage = primaryStage;
			
			primaryStage.setMinHeight(900);
			primaryStage.setMinWidth(650);
			
			BorderPane root = new BorderPane();

			Scene scene = new Scene(root);
			scene.getStylesheets().add("style/application.css");
			
			primaryStage.setScene(scene);
			switchScene(Scenes.CLICKER, Directions.DOWN);
			
			primaryStage.show();
		
			// kill all threads on window close
			primaryStage.setOnCloseRequest(event -> {
				game.stop();
				System.exit(0);
			});
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public void switchScene(Scenes nextScene, Directions direction) {
		
		var scene = primaryStage.getScene();
		var next  = scenes.get(nextScene);
		
		if (next != null && scene.getRoot() != null)
			FadeIn.performTransition((Pane) scene.getRoot(), next, direction);
	}
	
	public BackgroundMusic getMusic() {
		return musicPlayer;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}