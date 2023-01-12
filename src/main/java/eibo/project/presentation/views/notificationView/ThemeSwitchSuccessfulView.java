package eibo.project.presentation.views.notificationView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.util.Labels;
import javafx.beans.binding.Bindings;

public class ThemeSwitchSuccessfulView extends NotificationView {

	@Override
	protected void bindToConfig() {
		var successTextProperty = LabelConfig.instance().labelTextProperty(Labels.SUCCESSFUL);
		var actionTextProperty = LabelConfig.instance().labelTextProperty(Labels.THEME_SWITCH);
		
		label.textProperty().bind(Bindings.createStringBinding(() -> {
			return actionTextProperty.get() + " " + successTextProperty.get();
		}, successTextProperty, actionTextProperty));
	}

	@Override
	protected void addStyleClass() {
		getStyleClass().add("success");
	}

}
