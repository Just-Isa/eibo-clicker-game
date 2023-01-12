package eibo.project.presentation.scenes;

import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.views.menuView.SettingsMenuViewController;
import eibo.project.presentation.views.settingsView.SettingsViewController;
import javafx.scene.layout.BorderPane;

public class SettingsScene extends BorderPane {

	public SettingsScene(IncrementalGame game, App app) {
		
		var menu = new SettingsMenuViewController(game, app).getRootView();
		setTop(menu);
		
		var settingsView = new SettingsViewController(game, app).getRootView();
		setCenter(settingsView);

	}
}
