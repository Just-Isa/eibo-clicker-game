package eibo.project.business.services;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {
	
	public static final String DEFAULT_MUSIC_PATH = "themes/default/background1.mp3";

	public static final double INITIAL_VOLUME = 0.1;
	public static final double MAX_VOLUME = 1;

	private MediaPlayer player;
	private BooleanProperty playingState;
	
	public BackgroundMusic() {
		this(DEFAULT_MUSIC_PATH);
	}
	
	public BackgroundMusic(String path) {
		var media = new Media(new File(path).toURI().toString());

		player       = new MediaPlayer(media);
		playingState = new SimpleBooleanProperty(false);
		
		player.setCycleCount(MediaPlayer.INDEFINITE);
		player.setVolume(INITIAL_VOLUME);
		
		player.play();
	}

	public double getMaxVolume() {
		return MAX_VOLUME;
	}
	
	public MediaPlayer getPlayer() {
		return player;
	}
	
	public BooleanProperty playingStateProperty() {
		return playingState;
	}
	
	public void play() {
		
		if (playingState.get())
			player.play();
		
		else
			player.pause();
		
		switchState();
		
	}
	
	private void switchState() {
		playingState.set(!playingState.get());
	}
}
