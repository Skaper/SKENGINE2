package game.Level;

import skengine2.core.GameEngine;
import skengine2.objects.GameObject;
import skengine2.objects.Transform;
import skengine2.render.Sprite;
import skengine2.scene.Scene;

public class TestLineGameObject extends GameObject {
    public TestLineGameObject(Scene scene, Transform transform) {
        super(scene, transform);
    }

    @Override
    public void setup(GameEngine engine) {
        skin = new Sprite(transform, "./res/test.png", this);
    }

    @Override
    public void update(GameEngine engine) {
        transform.angle += 0.3;
        transform.position = engine.getInput().mousePosition();
    }
}
