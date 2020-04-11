package skengine2.render;

import skengine2.Camera;
import skengine2.core.GameEngine;

import java.awt.*;

public class Renderer {
    private GameEngine engine;
    private Camera camera;

    private int test_countObjects = 0;

    private Font awtFont;
    public Renderer(GameEngine engine){
        this.engine = engine;
        camera = engine.getMainCamera();
    }

    public void render(Sprite sprite){

        float camX = camera.getPosition().x;
        float camY = camera.getPosition().y;
        float camHalfW = camera.getWidth() / 2f;
        float camHalfH = camera.getHeight() / 2f;
        float spriteX = sprite.transform.position.x;
        float spriteY = sprite.transform.position.y;
        int maxHalfSize = (sprite.getWidth() > sprite.getHeight()) ? sprite.getWidth()/2 : sprite.getHeight()/2;

        if(spriteX - maxHalfSize > camX + camHalfW || spriteX + maxHalfSize < camX - camHalfW) return;
        if(spriteY - maxHalfSize > camY + camHalfH || spriteY + maxHalfSize < camY - camHalfH) return;

        sprite.render(camera);
        test_countObjects++;
    }

    public void resetCount() {
        //System.out.println(" 627 / "+test_countObjects);
        test_countObjects = 0;

    }
}
