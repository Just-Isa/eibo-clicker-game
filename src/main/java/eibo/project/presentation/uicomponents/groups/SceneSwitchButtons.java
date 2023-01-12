package eibo.project.presentation.uicomponents.groups;

import eibo.project.presentation.uicomponents.spacers.HorizontalSpacer;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SceneSwitchButtons extends HBox {
	
	private Button nextButton;
	private Button prevButton;
	
	public SceneSwitchButtons() {
		
		prevButton = new Button();
		nextButton = new Button();
		
		getChildren().addAll(
				HorizontalSpacer.create(),
			prevButton,
			HorizontalSpacer.create(),
			HorizontalSpacer.create(),
			nextButton
		);
		
		getStyleClass().add("sceneswitch");

	}
	
	public Button nextButton() {
		return nextButton;
	}
	
	public Button prevButton() {
		return prevButton;
	}

}
