package game.Level;

import org.joml.Vector2f;
import skengine2.core.GameEngine;
import skengine2.objects.GameEntity;
import skengine2.objects.Transform;
import skengine2.render.Sprite;
import skengine2.scene.Scene;
import skengine2.utils.Debug;

import static org.lwjgl.glfw.GLFW.*;
public class TestLineGameEntity extends GameEntity {
    public TestLineGameEntity(Scene scene, Transform transform) {
        super(scene, transform);
    }

    @Override
    public void setup(GameEngine engine) {
        transform.position = new Vector2f(0, 0);
        transform.position = engine.getInput().mousePositionWorld();
        skin = new Sprite(transform, "./res/test.png", this);
    }

    @Override
    public void update(GameEngine engine) {
        //

        if(engine.getInput().isKeyPressed(GLFW_KEY_SPACE)){
           System.out.println("CAM W: " + engine.getMainCamera().getWidth() + " CAM H: " + engine.getMainCamera().getHeight());
           System.out.println("CAM X: " + engine.getMainCamera().getPosition().x
                    + "CAM Y: " + engine.getMainCamera().getPosition().y
                    + " OBJ X: " + transform.position.x + " OBJ Y: " + transform.position.y);
        }
        if(engine.getInput().isButtonDown(0)){
            transform.position = engine.getInput().mousePositionWorld();
        }
        if(engine.getInput().isButtonDown(1)){
            transform.angle += 0.5;
        }
        if(engine.getInput().isButtonPressed(0)){
            Debug.log("Left mouse button has been pressed!");
        }
    }
}
