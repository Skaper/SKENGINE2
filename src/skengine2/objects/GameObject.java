package skengine2.objects;

import skengine2.scene.Scene;

public abstract class GameObject extends Object{

    private boolean dead = false;

    public GameObject(Scene scene, Transform transform){
        super(scene, transform);
    }

    public boolean isDead() {
        return dead;
    }
    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
