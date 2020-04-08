package game.Level;

import core.GameEngine;
import core.objects.GameObject;
import core.objects.Transform;
import core.render.Sprite;
import core.scene.Scene;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class PlayerGameObject extends GameObject {
    public float speed = 3.5f;
    public PlayerGameObject(Scene scene, Transform transform) {
        super(scene, transform);

    }

    @Override
    public void setup(GameEngine engine) {
        skin =  new Sprite(transform,"./res/npc.png", this);
        engine.getMainCamera().setTarget(this);
    }

    @Override
    public void update(GameEngine engine) {
        if(engine.getInput().isKeyDown(GLFW_KEY_D)){
            transform.position.x += speed;

        }
        if(engine.getInput().isKeyDown(GLFW_KEY_A)){
            transform.position.x -=speed;

        }
        if(engine.getInput().isKeyDown(GLFW_KEY_W)){
            transform.position.y += speed;
        }
        if(engine.getInput().isKeyDown(GLFW_KEY_S)){
            transform.position.y -= speed;
        }

        if(engine.getInput().isKeyDown(GLFW_KEY_UP)){
            transform.scale.add(new Vector2f(0.01f, 0.01f));
        }
        if(engine.getInput().isKeyDown(GLFW_KEY_DOWN)){
            transform.scale.add(new Vector2f(-0.01f, -0.01f));
        }

        if(engine.getInput().isKeyPressed(GLFW_KEY_LEFT)){
            transform.angle += 10f;
        }
        if(engine.getInput().isKeyPressed(GLFW_KEY_RIGHT)){
            transform.angle -= 10f;
        }

    }

}
