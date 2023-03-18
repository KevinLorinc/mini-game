package entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.CombatInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.graphics.IRenderable;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.graphics.Spritesheet;
import de.gurkenlabs.litiengine.graphics.animation.Animation;
import de.gurkenlabs.litiengine.graphics.animation.IAnimationController;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;
import de.gurkenlabs.litiengine.physics.IMovementController;
import de.gurkenlabs.litiengine.resources.Resources;
import de.gurkenlabs.litiengine.graphics.animation.AnimationController;

@EntityInfo(width = 11, height = 20)
@MovementInfo(velocity = 30)
@CollisionInfo(collisionBoxWidth = 5, collisionBoxHeight = 8, collision = true)
@CombatInfo(hitpoints = 5, team = 1)
public class Player extends Creature implements IRenderable, IUpdateable {
  public enum PlayerState {
    CONTROLLABLE,
    LOCKED
  }
  
  private static Player instance;
  //private final Strike strike;
  //private final JumpAbility dash;

  private PlayerState state = PlayerState.CONTROLLABLE;
  
  private Player() {
	  super("cat");
	  
	  KeyboardEntityController<Player> movementController = new KeyboardEntityController<>(this);
	  movementController.addUpKey(KeyEvent.VK_W);
	  movementController.addDownKey(KeyEvent.VK_S);
	  movementController.addLeftKey(KeyEvent.VK_A);
	  movementController.addRightKey(KeyEvent.VK_D);
	  
	  this.setController(IMovementController.class, movementController);
	  this.movement().onMovementCheck(e -> {
		  return this.getState() == PlayerState.CONTROLLABLE;
	  });
	  
	  this.setMapId(100000);
  }
  
  public static Player instance() {
    if (instance == null) {
      instance = new Player();
    }

    return instance;
  }
  
  /*
  private void initAnimationController() {
    IAnimationController controller = this.getController(Class<T> AnimationController);

    controller.setDefaultAnimation(controller.getAnimation("cat-walk-left"));
  }

  @Override
  protected void updateAnimationController() {
    super.updateAnimationController();
    this.initAnimationController();
  }*/
  
  public PlayerState getState() {
	  return state;
  }

  public void setState(PlayerState state) {
    this.state = state;
  }

  @Override
  public void update() {
  }

	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
}
