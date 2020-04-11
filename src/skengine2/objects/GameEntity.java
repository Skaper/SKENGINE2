package skengine2.objects;

import skengine2.scene.Scene;

public abstract class GameEntity extends Entity {

    private boolean dead = false;

    public GameEntity(Scene scene, Transform transform){
        super(scene, transform);
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
