package eibo.project.presentation.views.upgradeListView;

import eibo.project.business.data.Upgrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class UpgradeListView extends ListView<Upgrade> {
	
	protected ObservableList<Upgrade> upgrades;
	
	public UpgradeListView() {	
		upgrades = FXCollections.observableArrayList();
	}

}
