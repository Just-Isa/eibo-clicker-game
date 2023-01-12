package eibo.project.presentation.views.settingsView;

import eibo.project.business.data.config.LabelConfig;
import eibo.project.business.data.config.ThemeConfig;
import eibo.project.business.data.config.util.Labels;
import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import eibo.project.presentation.transitions.Notification;
import eibo.project.presentation.uicomponents.groups.ThemeSwitchGroup;
import eibo.project.presentation.uicomponents.groups.VolumeGroup;
import eibo.project.presentation.views.notificationView.ResetSuccessfulView;
import eibo.project.presentation.views.notificationView.ThemeSwitchSuccessfulView;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class SettingsViewController {

	private IncrementalGame game;
	private App app;
	
	private ThemeSwitchGroup themeSwitch;
	private VolumeGroup volumeGroup;

	private Button newGameButton;
	
	private Pane rootView;
	
	public SettingsViewController(IncrementalGame game, App app) {	

		var view = new SettingsView();

		this.game = game;
		this.app = app;
		
		themeSwitch   = view.themeSwitch;
		volumeGroup   = view.volumeGroup;
		newGameButton = view.newGameButton;
		
		rootView = view;

		init();

	}
	
	public Pane getRootView() {
		return rootView;
	}
	
	public void init() {
		bindToConfig();
		bindToModel();
		defineBehaviourOnUserInteraction();
	}
	
	private void bindToConfig() {
		
		var musicText   = LabelConfig.instance().labelTextProperty(Labels.MUSIC);
		var turnOnText  = LabelConfig.instance().labelTextProperty(Labels.TURN_ON);
		var turnOffText = LabelConfig.instance().labelTextProperty(Labels.TURN_OFF);
		
		var activeState = app.getMusic().playingStateProperty();
		
		themeSwitch.button().textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.THEME_SWITCH));
		themeSwitch.icon().imageProperty().bind(ThemeConfig.instance().clickableImageProperty());
		
		newGameButton.textProperty().bind(LabelConfig.instance().labelTextProperty(Labels.RESET));
		
		volumeGroup.muteButton().textProperty().bind(Bindings.createStringBinding(() -> {
			var actionText = activeState.get() ? turnOnText.get() : turnOffText.get();
			return musicText.get() + " " + actionText;
		}, musicText, activeState, turnOnText, turnOffText));
	}
	
	private void bindToModel() {
		
		volumeGroup.volumeSlider().maxProperty().set(app.getMusic().getMaxVolume());
		volumeGroup.volumeSlider().valueProperty().bindBidirectional(app.getMusic().getPlayer().volumeProperty());
	}
	
	private void defineBehaviourOnUserInteraction() {
		
		volumeGroup.muteButton().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> app.getMusic().play());
		
		themeSwitch.button().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			var fromPane = (Pane) rootView.getScene().getRoot();
			ThemeConfig.instance().switchTheme();			
			Notification.performTransition(fromPane, new ThemeSwitchSuccessfulView());
		});
		
		newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
			var fromPane = (Pane) rootView.getScene().getRoot();
			game.reset();
			Notification.performTransition(fromPane, new ResetSuccessfulView());
		});		

	}
}
