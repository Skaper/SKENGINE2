package skengine2.objects;

import skengine2.core.GameEngine;
import skengine2.render.Renderer;

public interface IObjWorkable {
    void setup(GameEngine engine);
    void update(GameEngine engine);
    void render(GameEngine engine, Renderer graphic);

}
