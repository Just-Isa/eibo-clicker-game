package eibo.project.presentation.transitions;

import javafx.animation.Interpolator;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Notification {
	
	public static void performTransition(Pane origin, Pane destination) {
		
		var currentScene   = origin.getScene();
		var transitionPane = new StackPane();
		var anim           = initTransition(currentScene, destination);	
		
		transitionPane.getChildren().addAll(origin, destination);
		
		currentScene.setRoot(transitionPane);

		anim.playFromStart();
		
	}
	
	private static SequentialTransition initTransition(Scene mainScene, Pane destination) {

		var transition     = new TranslateTransition();
		var transitionBack = new TranslateTransition();
		
		var animation      = new SequentialTransition();
		
		transition.setNode(destination);
		transition.setInterpolator(Interpolator.EASE_IN);
		transition.setDuration(Duration.millis(300));
		transition.setFromY(-mainScene.getHeight());
		transition.setToY((-(mainScene.getHeight()/2))+(destination.getMaxHeight()/2));			
		
		transitionBack.setInterpolator(Interpolator.EASE_OUT);
		transitionBack.setDuration(Duration.millis(200));
		transitionBack.setDelay(Duration.seconds(1.5));
		transitionBack.setNode(destination);
		transitionBack.setToY(-mainScene.getHeight());

		animation.getChildren().addAll(transition, transitionBack);
		
		return animation;
		
	}

}
