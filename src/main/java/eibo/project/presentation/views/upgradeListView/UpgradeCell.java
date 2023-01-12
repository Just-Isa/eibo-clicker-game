package eibo.project.presentation.views.upgradeListView;

import eibo.project.business.data.Upgrade;
import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.views.upgradeView.UpgradeViewController;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Pane;

public class UpgradeCell extends ListCell<Upgrade> {
	
	private IncrementalGame game;
	
	public UpgradeCell() {
		super();
		setPrefWidth(0);
	}
	
	public UpgradeCell(IncrementalGame game) {
		super();
		setPrefWidth(0);
		this.game = game;
	}
	
	@Override
	protected void updateItem(Upgrade upgrade, boolean empty) {
		
		super.updateItem(upgrade, empty);
		
		setText(null);
		setGraphic(null);
		
		if (upgrade != null) {
			Pane view = new UpgradeViewController(game, upgrade).getRootView();
			setGraphic(view);
		}
		
	}
	
}
