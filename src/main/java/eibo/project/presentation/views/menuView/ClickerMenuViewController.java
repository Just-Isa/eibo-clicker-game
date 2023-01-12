package eibo.project.presentation.views.menuView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.util.Labels;
import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.application.App.Scenes;
import eibo.project.presentation.transitions.FadeIn.Directions;

public class ClickerMenuViewController extends MenuViewController {

	public ClickerMenuViewController(IncrementalGame game, App app) {
		super(game, app);
	}

	@Override
	protected void bindButtonTexts() {
		prev.textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.THIRD_SCENE));
		next.textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.SECOND_SCENE));
	}

	@Override
	protected void goToNext() {
		app.switchScene(Scenes.UPGRADE, Directions.LEFT);
	}

	@Override
	protected void goToPrev() {
		app.switchScene(Scenes.SETTINGS, Directions.RIGHT);
	}

}
