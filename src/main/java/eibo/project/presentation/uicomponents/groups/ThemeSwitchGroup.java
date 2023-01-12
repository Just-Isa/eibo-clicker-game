package eibo.project.presentation.uicomponents.groups;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ThemeSwitchGroup extends VBox {

	private ImageView icon;
	private Button button;
	
	public ThemeSwitchGroup() {
		super();
		
		icon   = new ImageView();
		button = new Button();
		
		icon.setFitHeight(100);
		icon.setFitWidth(100);
		
		getChildren().addAll(icon, button);
		setAlignment(Pos.CENTER);
	}
	
	public ImageView icon() {
		return icon;
	}
	
	public Button button() {
		return button;
	}
	
}
