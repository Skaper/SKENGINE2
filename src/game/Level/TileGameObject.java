package game.Level;

import skengine2.core.GameEngine;
import skengine2.objects.GameObject;
import skengine2.objects.Transform;
import skengine2.render.Renderer;
import skengine2.render.Sprite;
import skengine2.scene.Scene;

public class TileGameObject extends GameObject {
    Sprite point;

    public TileGameObject(Scene scene, Transform transform) {
        super(scene, transform);

    }

    @Override
    public void setup(GameEngine engine) {
        skin =  new Sprite(transform,"./res/tile.png", this);
        //point = new Sprite(transform.clone(), "./res/point.png", this);
    }

    @Override
    public void update(GameEngine engine) {
        transform.angle +=0.2f;
    }

    @Override
    public void render(GameEngine engine, Renderer graphic) {
        super.render(engine, graphic);
        //graphic.render(point);
    }
}