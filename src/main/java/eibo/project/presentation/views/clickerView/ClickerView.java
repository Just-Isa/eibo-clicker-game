package eibo.project.presentation.views.clickerView;

import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.uicomponents.groups.ClickerGroup;
import eibo.project.presentation.uicomponents.labels.PrefixSuffixLabel;
import eibo.project.presentation.uicomponents.spacers.VerticalSpacer;
import javafx.scene.layout.VBox;

public class ClickerView extends VBox {

	protected ClickerGroup clickerGroup;
	
	protected PrefixSuffixLabel currencyLabel;
	protected PrefixSuffixLabel gainLabel;
	
	public ClickerView(IncrementalGame game) {
		
		clickerGroup  = new ClickerGroup();
		
		currencyLabel = new PrefixSuffixLabel();
		gainLabel     = new PrefixSuffixLabel();
		
		getChildren().addAll(
			VerticalSpacer.create(),
			currencyLabel,
			VerticalSpacer.create(),
			clickerGroup,
			gainLabel,
			VerticalSpacer.create(),
			VerticalSpacer.create()
		);
	}

}
