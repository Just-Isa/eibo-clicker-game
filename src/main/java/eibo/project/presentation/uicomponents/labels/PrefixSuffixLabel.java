package eibo.project.presentation.uicomponents.labels;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class PrefixSuffixLabel extends HBox {

	private Label prefix;
	private Label suffix;
	
	public PrefixSuffixLabel() {
		
		prefix = new Label();
		suffix = new Label();
		
		setSpacing(3);
		getChildren().addAll(prefix, suffix);
	}
	
	public Label prefix() {
		return prefix;
	}
	
	public Label suffix() {
		return suffix;
	}
	
}
