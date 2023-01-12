package eibo.project.presentation.uicomponents.groups;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

public class VolumeGroup extends VBox {

	private Slider volumeSlider;
	private Button muteButton;
	
	public VolumeGroup() {
		super();
		
		volumeSlider = new Slider();
		muteButton = new Button();
		
		getChildren().addAll(volumeSlider, muteButton);
		
	}
	
	public Slider volumeSlider() {
		return volumeSlider;
	}
	
	public Button muteButton() {
		return muteButton;
	}
	
}
