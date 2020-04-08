package game.Level;

import core.GameEngine;
import core.objects.GameObject;
import core.objects.Transform;
import core.render.Sprite;
import core.scene.Scene;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class TileGameObject extends GameObject {

    public TileGameObject(Scene scene, Transform transform) {
        super(scene, transform);

    }

    @Override
    public void setup(GameEngine engine) {
        skin =  new Sprite(transform,"./res/tile.png", this);
    }

    @Override
    public void update(GameEngine engine) {
    }

}