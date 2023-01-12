package eibo.project.presentation.uicomponents.groups;

import eibo.project.presentation.uicomponents.images.ClickerIcon;
import eibo.project.presentation.uicomponents.labels.PrefixSuffixLabel;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class ClickerGroup extends VBox {

	private ClickerIcon clicker;
	private PrefixSuffixLabel amount;
	
	public ClickerGroup() {
		
		super();
		
		clicker = new ClickerIcon();
		amount  = new PrefixSuffixLabel();
		
		getChildren().addAll(clicker, amount);
		setAlignment(Pos.CENTER);
	}
	
	public ClickerIcon clickerIcon() {
		return clicker;
	}
	
	public PrefixSuffixLabel amountLabel() {
		return amount;
	}
	
}
