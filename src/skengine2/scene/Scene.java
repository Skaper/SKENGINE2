package skengine2.scene;

import skengine2.core.GameEngine;
import skengine2.objects.GameEntity;
import skengine2.objects.IObjWorkable;
import skengine2.objects.SortObjLayout;
import skengine2.render.Renderer;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Scene implements IObjWorkable {
    private long lastObjectID;
    protected ArrayList<GameEntity> objects;

    public Scene(){
        lastObjectID = 0;
        objects = new ArrayList<>();


    }
    public void addObject(GameEntity obj, GameEngine engine) {
        objects.add(obj);
        obj.setID(lastObjectID);
        obj.setVisible(true);
        obj.setup(engine);
        obj.setupContent(engine);
        obj.setupComponents(engine);
        lastObjectID +=1;
        Collections.sort(objects, new SortObjLayout());
    }

    public void removeObject(GameEntity obj){
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i).getID() == obj.getID()){
                obj.setVisible(false);
                objects.remove(i);
            }
        }
    }

    public GameEntity getGameObject(String tag){
        for(int i=0; i < objects.size(); i++){
            GameEntity gameObject = objects.get(i);
            if(gameObject.getTag().equalsIgnoreCase(tag)) return gameObject;
        }
        return null;
    }



    public void setupObjects(GameEngine gc) {

        for(int i = 0; i < objects.size(); i++) {
            GameEntity object = objects.get(i);
            object.setupContent(gc);
            object.setup(gc);
            object.setupComponents(gc);
            if(object.isDead()) {
                objects.remove(i);
                i--;
            }
        }

    }

    public void updateObjects(GameEngine engine) {

        for(int i = 0; i < objects.size(); i++) {
            GameEntity object = objects.get(i);
            object.update(engine);
            object.updateContent(engine);
            object.updateComponents(engine);
            if(object.isDead()) {
                objects.remove(i);
                i--;
            }
        }


    }

    public void renderObjects(GameEngine engine, Renderer graphic) {
        for(GameEntity obj : objects) {
            obj.renderContent(engine, graphic);
            obj.render(engine, graphic);

            obj.renderComponents(engine);
        }

    }

    public void destroy(){
        objects = new ArrayList<>();
    }
}
