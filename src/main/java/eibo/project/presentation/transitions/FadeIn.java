package eibo.project.presentation.transitions;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class FadeIn {
	
	public enum Directions { LEFT, RIGHT, UP, DOWN };
	
	public static void performTransition(Pane origin, Pane destination, Directions animationDirection) {
		
		var currentScene   = origin.getScene();
		var transitionPane = new StackPane();
		var transition     = initTransition(currentScene, destination, animationDirection);	
		

		transitionPane.getChildren().addAll(origin, destination);
		currentScene.setRoot(transitionPane);

		destination.toFront();
		
		transition.setOnFinished(event -> {
			transitionPane.getChildren().remove(origin);
			transitionPane.getChildren().remove(destination);
			currentScene.setRoot(destination);
		});
		
		transition.playFromStart();
		
	}
	
	private static TranslateTransition initTransition(Scene mainScene, Pane destination, Directions animationDirection) {

		var transition = new TranslateTransition();
		
		transition.setNode(destination);
		transition.setInterpolator(Interpolator.EASE_BOTH);
		transition.setDuration(Duration.millis(300));
		
		switch(animationDirection) {
		
			case LEFT:
				transition.setFromX(mainScene.getWidth());
				transition.setToX(0);
				break;
				
			case RIGHT:
				destination.translateXProperty().set(mainScene.getWidth());
				transition.setFromX(-mainScene.getWidth());
				transition.setToX(0);
				break;
				
			case UP:
				transition.setFromY(mainScene.getHeight());
				transition.setToY(0);
				break;
				
			case DOWN:
				transition.setFromY(-mainScene.getHeight());
				transition.setToY(0);
				break;
				
			default:
				break;
		
		}

		return transition;
		
	}

}
