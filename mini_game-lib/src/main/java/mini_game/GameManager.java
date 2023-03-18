package mini_game;

import java.awt.Font;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import de.gurkenlabs.litiengine.*;
import de.gurkenlabs.litiengine.entities.Spawnpoint;
import de.gurkenlabs.litiengine.environment.CreatureMapObjectLoader;
import de.gurkenlabs.litiengine.environment.*;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;
import de.gurkenlabs.litiengine.gui.GuiProperties;
import de.gurkenlabs.litiengine.resources.Resources;
import entities.Player;

public class GameManager {
	public enum GameState {
	    INGAME,
	    MENU,
	    INGAME_MENU
	  }
	
	public static final Font GUI_FONT = Resources.fonts().get("fsex300.ttf").deriveFont(32f);
	public static final Font SPEECH_BUBBLE_FONT = GUI_FONT.deriveFont(4f);
	public static final Font MENU_FONT = Resources.fonts().get("caesar.ttf").deriveFont(40f);
	public static final Font GUI_FONT_ALT = Resources.fonts().get("roman.ttf").deriveFont(40f);
	public static String START_LEVEL = "level0";
	
	private static final Map<String, Runnable> startups = new ConcurrentHashMap<>();
	
	static {
		startups.put("level0", () -> {
			Camera camera = new PositionLockCamera(Player.instance());
		    camera.setClampToMap(true);
		    Game.world().setCamera(camera);
		});
	}
	
	private static GameState state = GameState.INGAME;

	private GameManager() {
	}
	
	public static void init() {
		GuiProperties.setDefaultFont(GUI_FONT);

	    //Environment.registerMapObjectLoader(new CustomCreatureMapObjectLoader());

	    //CreatureMapObjectLoader.registerCustomCreatureType(DecorMob.class);
		
		Camera camera = new PositionLockCamera(Player.instance());
	    camera.setClampToMap(true);
	    Game.world().setCamera(camera);
	    
	    
        Player.instance().getHitPoints().setToMax();
        Player.instance().setIndestructible(false);
        Player.instance().setCollision(true);
        // spawn the player instance on the spawn point with the name "enter"
        Spawnpoint enter = Game.world().environment().getSpawnpoint("enter");
        if (enter != null) {
          enter.spawn(Player.instance());
        }

	}
}
