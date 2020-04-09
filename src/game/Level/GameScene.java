package game.Level;

import skengine2.core.GameEngine;
import skengine2.objects.GameObject;
import skengine2.objects.Transform;
import skengine2.render.Renderer;
import skengine2.scene.Scene;
import org.joml.Vector2f;

public class GameScene extends Scene {
    @Override
    public void setup(GameEngine engine) {
        System.out.println("GameScene setup");
        for(int y = -1000; y < 1000; y+=80){
            for(int x = -1000; x < 1000; x+=80){
                addObject(new TileGameObject(this, new Transform(new Vector2f(x, y))),
                                            engine);
            }
        }
        GameObject go = new PlayerGameObject(this, new Transform());
        addObject(go, engine);

        GameObject test = new TestLineGameObject(this, new Transform());
        addObject(test, engine);

    }

    @Override
    public void update(GameEngine engine) {

    }

    @Override
    public void render(GameEngine engine, Renderer graphic) {

    }
}
