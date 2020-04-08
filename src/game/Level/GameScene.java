package game.Level;

import core.GameEngine;
import core.objects.GameObject;
import core.objects.Transform;
import core.scene.Scene;
import org.joml.Vector2f;

public class GameScene extends Scene {
    @Override
    public void setup(GameEngine engine) {
        System.out.println("GameScene setup");
        for(int y = -500; y < 500; y+=80){
            for(int x = -500; x < 500; x+=80){
                addObject(new TileGameObject(this, new Transform(new Vector2f(x, y))),
                                            engine);
            }
        }
        GameObject go = new PlayerGameObject(this, new Transform());
        addObject(go, engine);

    }

    @Override
    public void update(GameEngine engine) {

    }

    @Override
    public void render(GameEngine engine) {

    }
}
