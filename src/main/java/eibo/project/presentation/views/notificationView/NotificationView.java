package eibo.project.presentation.views.notificationView;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public abstract class NotificationView extends StackPane {
	
	protected Label label;
	
	public NotificationView() {
		
		label = new Label();
		label.setAlignment(Pos.BASELINE_CENTER);
		
		setMinWidth(500);
		setPrefWidth(500);
		setMinHeight(50);
		setMaxHeight(50);
		
		getChildren().addAll(label);
		
		bindToConfig();
		addStyleClass();
	}
	
	protected abstract void bindToConfig();
	protected abstract void addStyleClass();

}
