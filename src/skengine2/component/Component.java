package skengine2.component;

import skengine2.core.GameEngine;
import skengine2.objects.Object;

public abstract class Component {
    public boolean isTrigger = false;
    protected boolean isActive = true;
    protected boolean isRender = true;
    protected String tag;
    protected Object parent;
    protected Type type = Type.None;

    public  enum Type{
        CollisionBox,
        CollisionCircle,
        None
    }

    public abstract void setup(GameEngine gc);
    public abstract void update(GameEngine gc);
    public abstract void render(GameEngine gc);

    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public Object getParent() {
        return parent;
    }
    public void setParent(Object parent) {
        this.parent = parent;
    }
    public boolean isActive(){
        return isActive;
    }
    public void setActive(boolean isActive){
        this.isActive = isActive;
        setRender(isActive);
    }
    public void setRender(boolean render) {
        isRender = render;
    }
    public Type getType() {
        return type;
    }

}
