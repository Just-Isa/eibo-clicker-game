package eibo.project.presentation.uicomponents.spacers;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HorizontalSpacer {

	public static Node create() {
	    var spacer = new Region();
	    HBox.setHgrow(spacer, Priority.ALWAYS);
	    return spacer;
	}

}
