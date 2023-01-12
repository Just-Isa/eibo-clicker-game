package eibo.project.presentation.views.menuView;

import eibo.project.presentation.uicomponents.groups.SceneSwitchButtons;
import javafx.scene.layout.HBox;

public class MenuView extends HBox {

	protected SceneSwitchButtons group;
	
	public MenuView() {
		group = new SceneSwitchButtons();
		this.getChildren().add(group);
	}
	
}
