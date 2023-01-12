package eibo.project.presentation.uicomponents.groups;

import eibo.project.presentation.uicomponents.labels.PrefixSuffixLabel;
import javafx.scene.layout.VBox;

public class UpgradeInfo extends VBox {

	private PrefixSuffixLabel amount;
	private PrefixSuffixLabel cost;
	private PrefixSuffixLabel gain;
	
	public UpgradeInfo() {
		this(
			new PrefixSuffixLabel(), 
			new PrefixSuffixLabel(), 
			new PrefixSuffixLabel()
		);
	}
	
	public UpgradeInfo(PrefixSuffixLabel amount, 
					   PrefixSuffixLabel cost, 
					   PrefixSuffixLabel gain) {
		
		super();
		
		this.amount = amount;
		this.cost = cost;
		this.gain = gain;
		
		getChildren().addAll(
			amount, 
			cost, 
			gain
		);
	}
	
	public PrefixSuffixLabel amount() {
		return amount;
	}
	
	public PrefixSuffixLabel cost() {
		return cost;
	}
	
	public PrefixSuffixLabel gain() {
		return gain;
	}
	
}
