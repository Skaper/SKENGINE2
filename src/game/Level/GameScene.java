package game.Level;

import skengine2.core.GameEngine;
import skengine2.objects.GameEntity;
import skengine2.objects.Transform;
import skengine2.render.Renderer;
import skengine2.scene.Scene;
import org.joml.Vector2f;

public class GameScene extends Scene {
    @Override
    public void setup(GameEngine engine) {
        System.out.println("GameScene setup");
        int total = 0;
        for(int y = -1000; y < 1000; y+=200){
            for(int x = -1000; x < 1000; x+=200){
                addObject(new TileGameEntity(this, new Transform(new Vector2f(x, y))),
                                            engine);
                total++;
                System.out.println("Total: "+total);
            }
        }
        GameEntity go = new PlayerGameEntity(this, new Transform());
        addObject(go, engine);

        GameEntity test = new TestLineGameEntity(this, new Transform());
        addObject(test, engine);

    }

    @Override
    public void update(GameEngine engine) {

    }

    @Override
    public void render(GameEngine engine, Renderer graphic) {

    }
}
