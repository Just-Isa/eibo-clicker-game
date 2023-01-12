package eibo.project.presentation.views.menuView;

import eibo.project.business.services.IncrementalGame;
import eibo.project.presentation.application.App;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public abstract class MenuViewController {
	
	protected IncrementalGame game;
	protected App app;
	
	protected Button next;
	protected Button prev;
	
	protected Pane rootView;
	
	
	public MenuViewController(IncrementalGame game, App app) {

		this.game = game;
		this.app = app;
		
		var view = new MenuView();
		
		this.prev = view.group.prevButton(); 
		this.next = view.group.nextButton();
		
		rootView = view;
		
		init();
	}
	
	
	public void init() {
		defineBehaviourOnUserInteraction();
		bindButtonTexts();
	}
	
	public Pane getRootView() {
		return rootView;
	}

	protected void defineBehaviourOnUserInteraction() {
		next.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> goToNext());
		prev.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> goToPrev());
	}
	
	protected abstract void bindButtonTexts();
	
	protected abstract void goToNext();
	protected abstract void goToPrev();
	
}
