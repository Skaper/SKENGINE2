package core.objects;

import core.GameEngine;
import core.render.Sprite;
import org.joml.Vector2f;

public interface IGameObject {

    void move(Vector2f velocity);
    void setPosition(Vector2f position);
    void rotate(float angle);

    void setSprite(Sprite sprite);

    String getTag();
    void setTag();
}
