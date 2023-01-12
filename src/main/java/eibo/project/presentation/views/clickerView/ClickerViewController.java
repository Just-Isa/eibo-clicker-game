package eibo.project.presentation.views.clickerView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.ThemeConfig;
import eibo.project.business.data.config.util.Labels;
import eibo.project.business.services.IncrementalGame;
import eibo.project.business.services.IncrementalGame.Elements;
import eibo.project.business.util.NumberToString;
import eibo.project.presentation.application.App;
import eibo.project.presentation.uicomponents.images.ClickerIcon;
import eibo.project.presentation.uicomponents.labels.PrefixSuffixLabel;
import javafx.beans.binding.Bindings;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class ClickerViewController {

	private IncrementalGame game;
	private App app;

	private ClickerIcon clickIcon;
	
	private PrefixSuffixLabel gainLabel;
	private PrefixSuffixLabel currencyLabel;
	private PrefixSuffixLabel amountLabel;
	
	private Pane rootView;	
	
	public ClickerViewController(IncrementalGame game, App app) {
		
		var view = new ClickerView(game);
		
		this.app     = app;
		this.game    = game;		
		
		this.clickIcon = view.clickerGroup.clickerIcon();

		this.gainLabel     = view.gainLabel;
		this.amountLabel   = view.clickerGroup.amountLabel();
		this.currencyLabel = view.currencyLabel;
		
		rootView = view;
				
		init();
	}
	
	
	public Pane getRootView() {
		return rootView;
	}
	
	
	
	private void init() {
		
		bindToConfig();
		bindToModel();
		defineBehaviourOnThemeChange();
		defineBehaviourOnUserInteraction();
		
		clickIcon.setClickable();
	}
	
	private void bindToConfig() {
		
		var currencyText  = ThemeConfig.instance().currencyNameProperty();
		var perClickText  = LabelConfig.instance().labelTextProperty(Labels.PER_CLICK);
		var perSecondText = LabelConfig.instance().labelTextProperty(Labels.TIME_INTERVAL);
		
		clickIcon.clickableImageProperty().bind(ThemeConfig.instance().clickableImageProperty());
		clickIcon.clickedImageProperty().bind(ThemeConfig.instance().clickedImageProperty());
		
		currencyLabel.suffix().textProperty().bind(currencyText);
		
		gainLabel.suffix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return currencyText.get() + " " + perSecondText.get();
			}, currencyText, perSecondText));
		
		amountLabel.suffix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return currencyText.get() + " " + perClickText.get();
			}, currencyText, perClickText));
	}
	
	private void bindToModel() {
		
		var currencyBalance = game.currencyAmountProperty();
		var gainPerSecond   = game.currencyGainProperty();
		var gainPerClick    = game.get(Elements.CLICKER).valueProperty();
		
		currencyLabel.prefix().textProperty().bind(
			Bindings.createStringBinding(()-> {
				return NumberToString.format(currencyBalance.get());
			}, currencyBalance));
		
		gainLabel.prefix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return NumberToString.format(gainPerSecond.get());
			}, gainPerSecond));
		
		amountLabel.prefix().textProperty().bind(
			Bindings.createStringBinding(() -> {
				return NumberToString.format(gainPerClick.get());
			}, gainPerClick));
		
	}
	
	private void defineBehaviourOnThemeChange() {
		ThemeConfig.instance().clickableImageProperty().addListener((obs, o, n) -> clickIcon.setClickable());
	}
	
	private void defineBehaviourOnUserInteraction() {	
		clickIcon.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> clickIcon.setClicked());
		clickIcon.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> clickIcon.setClickable());
		clickIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> game.gainCurrency());
	}

}
