package core.objects;

import core.GameEngine;

public interface IObjWorkable {
    void setup(GameEngine engine);
    void update(GameEngine engine);
    void render(GameEngine engine);

}
