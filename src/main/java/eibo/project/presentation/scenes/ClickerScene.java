package eibo.project.presentation.scenes;

import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.views.clickerView.ClickerViewController;
import eibo.project.presentation.views.menuView.ClickerMenuViewController;
import javafx.scene.layout.BorderPane;

public class ClickerScene extends BorderPane {
	
	public ClickerScene(IncrementalGame game, App app) {

		var clickerView = new ClickerViewController(game, app).getRootView();
		setCenter(clickerView);

		var menu = new ClickerMenuViewController(game, app).getRootView();
		setTop(menu);

	}
	
}