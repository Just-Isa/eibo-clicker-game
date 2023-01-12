package eibo.project.business.interfaces;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Game {

	/**
	 * This method starts the game.
	 */
	void start();
	
	/**
	 * This method stops the game.
	 */
	void stop();
	
	/**
	 * This method saves the current state of the game.
	 */
	void save();
	
	/**
	 * This method loads the last state of the game.
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	void load() throws JsonProcessingException, IOException;
	
}
