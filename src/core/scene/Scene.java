package core.scene;

import core.GameEngine;
import core.objects.GameObject;
import core.objects.IObjWorkable;
import core.objects.SortObjects;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Scene implements IObjWorkable {
    private long lastObjectID;
    protected ArrayList<GameObject> objects;

    public Scene(){
        lastObjectID = 0;
        objects = new ArrayList<>();


    }
    public void addObject(GameObject obj, GameEngine engine) {
        objects.add(obj);
        obj.setID(lastObjectID);
        obj.setVisible(true);
        obj.setup(engine);
        obj.setupContent(engine);
        obj.setupComponents(engine);
        lastObjectID +=1;
        Collections.sort(objects, new SortObjects());
    }

    public void removeObject(GameObject obj){
        for(int i = 0; i < objects.size(); i++){
            if(objects.get(i).getID() == obj.getID()){
                obj.setVisible(false);
                objects.remove(i);
            }
        }
    }

    public GameObject getGameObject(String tag){
        for(int i=0; i < objects.size(); i++){
            GameObject gameObject = objects.get(i);
            if(gameObject.getTag().equalsIgnoreCase(tag)) return gameObject;
        }
        return null;
    }



    public void setupObjects(GameEngine gc) {

        for(int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
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
            GameObject object = objects.get(i);
            object.update(engine);
            object.updateContent(engine);
            object.updateComponents(engine);
            if(object.isDead()) {
                objects.remove(i);
                i--;
            }
        }


    }

    public void renderObjects(GameEngine engine) {
        for(GameObject obj : objects) {
            obj.renderContent(engine);
            obj.render(engine);

            obj.renderComponents(engine);
        }

    }

    public void destroy(){
        objects = new ArrayList<>();
    }
}
