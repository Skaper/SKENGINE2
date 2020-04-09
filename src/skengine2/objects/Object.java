package skengine2.objects;

import skengine2.core.GameEngine;
import skengine2.component.Component;
import skengine2.render.Renderer;
import skengine2.render.Sprite;
import skengine2.scene.Scene;

import java.util.ArrayList;


public abstract class Object implements  IObjWorkable, IObjComponentWorkable, Layout{
    public Transform transform;
    protected int layout = Layout.DEFAULT;

    protected Scene scene;
    protected Sprite skin;

    protected long id;
    protected String tag;

    protected ArrayList<Component> components;
    protected boolean isVisible;

    public Object(Scene scene, Transform transform){
        this.scene = scene;
        this.transform = transform;

        skin = null;
        tag = "default";
        components = new ArrayList<>();
        isVisible = true;
    }

    public abstract void setup (GameEngine engine);
    public abstract void update(GameEngine engine);
    public void render(GameEngine engine, Renderer graphic){
        if(skin != null) graphic.render(skin); //skin.render(engine.getMainCamera());
    }

    public void setupContent(GameEngine engine) {

    }

    public void updateContent(GameEngine engine) {

    }

    public void renderContent(GameEngine engine, Renderer graphic) {

    }

    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void setupComponents(GameEngine engine) {

    }

    @Override
    public void updateComponents(GameEngine engine) {

    }

    @Override
    public void renderComponents(GameEngine engine) {

    }

    @Override
    public void removeComponent(String tag) {
        for(int i = 0; i < components.size(); i++){
            if(components.get(i).getTag().equalsIgnoreCase(tag)){
                components.remove(i);
                i--;
            }
        }
    }

    @Override
    public Component findComponent(String tag) {
        for(int i = 0; i < components.size(); i++){
            if(components.get(i).getTag().equalsIgnoreCase(tag)){
                return components.get(i);
            }
        }
        return null;
    }

    public void setID(long id){
        this.id = id;
    }

    public long getID(){
        return id;
    }

    public boolean isVisible() {
        return isVisible;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getTag() {
        return tag;
    }

}
