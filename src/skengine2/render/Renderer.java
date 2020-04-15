package skengine2.render;

import skengine2.Camera;
import skengine2.core.GameEngine;
import skengine2.render.Shaders.DefaultShader;
import skengine2.render.Shaders.ShaderProgram;

import java.awt.*;

import static skengine2.settings.GraphicSettings.DEFAULT_SHADER;

public class Renderer {
    private GameEngine engine;
    private Camera camera;

    private DefaultShader defaultShader = new DefaultShader();

    private Font awtFont;

    public Renderer(GameEngine engine) {
        this.engine = engine;
        camera = engine.getMainCamera();
        defaultShader.start();
    }

    public void render(Sprite sprite) {

        float camX = camera.getPosition().x;
        float camY = camera.getPosition().y;
        float camHalfW = camera.getWidth() / 2f;
        float camHalfH = camera.getHeight() / 2f;
        float spriteX = sprite.transform.position.x;
        float spriteY = sprite.transform.position.y;
        int maxHalfSize = (sprite.getWidth() > sprite.getHeight()) ? sprite.getWidth() / 2 : sprite.getHeight() / 2;

        if (spriteX - maxHalfSize > camX + camHalfW || spriteX + maxHalfSize < camX - camHalfW) return;
        if (spriteY - maxHalfSize > camY + camHalfH || spriteY + maxHalfSize < camY - camHalfH) return;

        if (sprite.getShaderName().equals(DEFAULT_SHADER)) {
            if(!defaultShader.isBind()) defaultShader.start();
            sprite.render(camera, defaultShader);
        } else {
            if(defaultShader.isBind()) defaultShader.stop();
            sprite.render(camera);
        }

    }

    public void clear(){
        defaultShader.stop();
    }
}
