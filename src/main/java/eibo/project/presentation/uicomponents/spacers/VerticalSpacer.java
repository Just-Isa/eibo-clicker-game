package eibo.project.presentation.uicomponents.spacers;

import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class VerticalSpacer {

	public static Node create() {
	    var spacer = new Region();
	    VBox.setVgrow(spacer, Priority.ALWAYS);
	    return spacer;
	}

	
}
