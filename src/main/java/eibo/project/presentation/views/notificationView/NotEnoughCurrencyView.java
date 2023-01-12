package eibo.project.presentation.views.notificationView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.ThemeConfig;
import eibo.project.business.data.config.util.Labels;
import javafx.beans.binding.Bindings;

public class NotEnoughCurrencyView extends NotificationView {

	@Override
	protected void bindToConfig() {
		var errorTextProperty    = LabelConfig.instance().labelTextProperty(Labels.NOT_ENOUGH);
		var currencyNameProperty = ThemeConfig.instance().currencyNameProperty();
		
		label.textProperty().bind(Bindings.createStringBinding(() -> {
			return errorTextProperty.get() + " " + currencyNameProperty.get(); 
		}, errorTextProperty, currencyNameProperty));
	}

	@Override
	protected void addStyleClass() {
		getStyleClass().add("error");
	}

}
