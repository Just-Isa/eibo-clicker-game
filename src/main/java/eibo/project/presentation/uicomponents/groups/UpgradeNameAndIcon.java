package eibo.project.presentation.uicomponents.groups;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class UpgradeNameAndIcon extends VBox {
	
	public static int DEFAULT_SIZE = 75;

	private ImageView icon;
	private Label name;
	
	public UpgradeNameAndIcon() {
	
		super();
		
		icon = new ImageView();
		name = new Label();
		
		getChildren().addAll(icon, name);

	}
	
	public ImageView icon() {
		return icon;
	}
	
	public Label name() {
		return name;
	}
	
}
