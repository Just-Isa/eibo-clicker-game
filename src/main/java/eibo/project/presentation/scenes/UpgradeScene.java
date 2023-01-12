package eibo.project.presentation.scenes;

import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.views.menuView.UpgradeMenuViewController;
import eibo.project.presentation.views.upgradeListView.UpgradeListViewController;
import javafx.scene.layout.BorderPane;

public class UpgradeScene extends BorderPane {

	public UpgradeScene(IncrementalGame game, App app) {
		
		var menu = new UpgradeMenuViewController(game, app).getRootView();
		setTop(menu);
		
		var upgradeView = new UpgradeListViewController(game).getView();
		setCenter(upgradeView);

	}
}
