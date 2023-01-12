package eibo.project.presentation.views.settingsView;

import eibo.project.presentation.uicomponents.groups.ThemeSwitchGroup;
import eibo.project.presentation.uicomponents.groups.VolumeGroup;
import eibo.project.presentation.uicomponents.spacers.VerticalSpacer;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SettingsView extends VBox {

	protected ThemeSwitchGroup themeSwitch = new ThemeSwitchGroup();
	protected VolumeGroup volumeGroup = new VolumeGroup();

	protected Button newGameButton;
	
	
	public SettingsView() {
		
		themeSwitch = new ThemeSwitchGroup();
		volumeGroup = new VolumeGroup();
		
		newGameButton = new Button();
			
		newGameButton.getStyleClass().add("error");
		
		getChildren().addAll(
			VerticalSpacer.create(),
			themeSwitch,
			VerticalSpacer.create(),
			volumeGroup,
			VerticalSpacer.create(),
			newGameButton,
			VerticalSpacer.create()
		);
		
	}
}
