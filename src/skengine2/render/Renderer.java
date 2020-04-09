package skengine2.render;

import skengine2.Camera;
import skengine2.core.GameEngine;

import java.awt.*;

public class Renderer {
    private GameEngine engine;
    private Camera camera;

    private Font awtFont;
    public Renderer(GameEngine engine){
        this.engine = engine;
        camera = engine.getMainCamera();
    }

    public void render(Sprite sprite){
        int maxSize = (sprite.getWidth() > sprite.getHeight()) ? sprite.getWidth() : sprite.getHeight();
        int totalHalfSize = maxSize + engine.getWidth()/2;
        if ((Math.abs(-sprite.transform.position.x  + sprite.getWidth()/2 - camera.getPosition().x) > totalHalfSize) ||
                (Math.abs(sprite.transform.position.x  + sprite.getWidth()/2 + camera.getPosition().x) > totalHalfSize))
        {
            return;
        }
        if ((Math.abs(-sprite.transform.position.y  + sprite.getHeight()/2 - camera.getPosition().y) > totalHalfSize) ||
                (Math.abs(sprite.transform.position.y  + sprite.getHeight()/2 + camera.getPosition().y) >totalHalfSize))
        {
            return;
        }
        sprite.render(camera);
    }
}
