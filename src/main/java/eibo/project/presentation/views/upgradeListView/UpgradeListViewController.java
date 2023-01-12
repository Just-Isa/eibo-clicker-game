package eibo.project.presentation.views.upgradeListView;

import eibo.project.business.data.Upgrade;
import eibo.project.business.services.IncrementalGame;
import javafx.collections.ObservableList;

public class UpgradeListViewController {


	private IncrementalGame game;

	private UpgradeListView view;
	private ObservableList<Upgrade> upgrades;
	
	public UpgradeListViewController(IncrementalGame game) {
		
		this.game = game;
		this.view = new UpgradeListView();
		this.upgrades = view.upgrades;
		
		view.setCellFactory(e -> new UpgradeCell(game));
		
		init();
	}
	
	public UpgradeListView getView() {
		return view;
	}
	
	public void init() {
		upgrades.setAll(game.getAllUpgrades());
		view.setItems(upgrades);
	}
	
	
	
}
