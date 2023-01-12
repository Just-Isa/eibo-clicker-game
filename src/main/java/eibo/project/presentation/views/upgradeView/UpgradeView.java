package eibo.project.presentation.views.upgradeView;

import eibo.project.presentation.uicomponents.groups.UpgradeInfo;
import eibo.project.presentation.uicomponents.groups.UpgradeNameAndIcon;
import eibo.project.presentation.uicomponents.spacers.HorizontalSpacer;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class UpgradeView extends HBox {

	protected UpgradeNameAndIcon nameAndIcon;
	protected UpgradeInfo info;
	protected Button buyButton;
	
	public UpgradeView() {
		
		nameAndIcon = new UpgradeNameAndIcon();
		buyButton   = new Button();
		info        = new UpgradeInfo();
		
		getChildren().addAll(
			HorizontalSpacer.create(),
			nameAndIcon,
			HorizontalSpacer.create(),
			info,
			HorizontalSpacer.create(),
			buyButton,
			HorizontalSpacer.create()
		);
		
	}
	
}
