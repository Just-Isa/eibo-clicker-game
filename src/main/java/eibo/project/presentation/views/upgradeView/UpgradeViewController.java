package eibo.project.presentation.views.upgradeView;

import eibo.project.business.data.Upgrade;
import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.ThemeConfig;
import eibo.project.business.data.config.util.Labels;
import eibo.project.business.exceptions.NotEnoughCurrencyException;
import eibo.project.business.services.IncrementalGame;
import eibo.project.business.util.NumberToString;
import eibo.project.presentation.transitions.Notification;
import eibo.project.presentation.uicomponents.groups.UpgradeInfo;
import eibo.project.presentation.uicomponents.groups.UpgradeNameAndIcon;
import eibo.project.presentation.views.notificationView.NotEnoughCurrencyView;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class UpgradeViewController {
	
	private IncrementalGame game;
	private Upgrade upgrade;
	private int index;
	
	private UpgradeNameAndIcon nameAndIcon;
	private UpgradeInfo info;
	private Button buyButton;
	
	private Pane rootView;
	
	public UpgradeViewController(IncrementalGame game, Upgrade upgrade) {
		
		this.game    = game;
		this.upgrade = upgrade;
		this.index   = game.getAllUpgrades().indexOf(upgrade);
		
		var view = new UpgradeView();
		
		this.nameAndIcon = view.nameAndIcon;
		this.buyButton   = view.buyButton;
		this.info        = view.info;
		
		this.rootView = view;
		
		init();
	}
	
	public Pane getRootView() {
		return rootView;
	}
	
	private void init() {
		bindToConfig();
		bindToModel();
		defineBehaviourOnUserInteraction();
	}
	
	private void bindToConfig() {
		
		var name = ThemeConfig.instance().upgradeNameProperty(index);
		var icon = ThemeConfig.instance().upgradeImageProperty(index);
		
		var amountText = LabelConfig.instance().labelTextProperty(Labels.AMOUNT);
		var costText   = LabelConfig.instance().labelTextProperty(Labels.COST);
		var gainText   = LabelConfig.instance().labelTextProperty(Labels.GAIN);
		var buttonText = LabelConfig.instance().labelTextProperty(Labels.BUY);
		
		nameAndIcon.name().textProperty().bind(name);
		nameAndIcon.icon().imageProperty().bind(icon);
		
		info.amount().prefix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return amountText.get() + ": ";
			}, amountText));
		
		info.cost().prefix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return costText.get() + ": ";
			}, costText));
		
		info.gain().prefix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return gainText.get() + ": ";
			}, gainText));
		
		buyButton.textProperty().bind(buttonText);
	}
	
	private void bindToModel() {
		
		var amount = upgrade.amountProperty(); 
		var cost   = upgrade.costProperty();
		var gain   = upgrade.getValuePerUpgrade();
		
		var perClickText  = LabelConfig.instance().labelTextProperty(Labels.PER_CLICK);
		var perSecondText = LabelConfig.instance().labelTextProperty(Labels.TIME_INTERVAL);
		
		var currency = ThemeConfig.instance().currencyNameProperty();
		
		info.amount().suffix().textProperty().bind(
			Bindings.createStringBinding(() -> {
	 	 		return NumberToString.format(amount.get());
		}, amount, currency));
		
		info.cost().suffix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return NumberToString.format(cost.get()) + " " + currency.get();
			}, cost, currency));
		
		info.gain().suffix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				var gainCondition = (index == 0) ? perClickText.get() : perSecondText.get();
				return NumberToString.format(gain) + " " + currency.get() + " " + gainCondition;
		}, perClickText, perSecondText, currency));
		
	}
	
	private void defineBehaviourOnUserInteraction() {
		
		buyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			
			try {
				game.buy(upgrade);
			} catch (NotEnoughCurrencyException e) {
				
				var fromPane = (Pane) rootView.getScene().getRoot();		
				Notification.performTransition(fromPane, new NotEnoughCurrencyView());
			}
		});
	}

}
