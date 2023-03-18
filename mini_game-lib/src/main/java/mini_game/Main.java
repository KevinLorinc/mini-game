package mini_game;

import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.input.*;
import de.gurkenlabs.litiengine.resources.*;

public class Main {

	public static void main(String[] args) {
		Game.info().setName("Mini Game");
		Game.info().setVersion("1.0.0");
		
		Game.init(args);
		Input.mouse().setGrabMouse(false);
		
		Resources.load("game.litidata");
		
		GameManager.init();
		
		Game.start();

	}

}
