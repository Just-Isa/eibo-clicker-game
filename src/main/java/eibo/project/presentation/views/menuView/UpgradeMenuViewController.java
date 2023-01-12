package eibo.project.presentation.views.menuView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.util.Labels;
import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.application.App.Scenes;
import eibo.project.presentation.transitions.FadeIn.Directions;

public class UpgradeMenuViewController extends MenuViewController {

	public UpgradeMenuViewController(IncrementalGame game, App app) {
		super(game, app);
	}

	@Override
	protected void bindButtonTexts() {
		prev.textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.FIRST_SCENE));
		next.textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.THIRD_SCENE));
	}

	@Override
	protected void goToNext() {
		app.switchScene(Scenes.SETTINGS, Directions.LEFT);
	}

	@Override
	protected void goToPrev() {
		app.switchScene(Scenes.CLICKER, Directions.RIGHT);
	}
	
}
