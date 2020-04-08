package core;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private long windowID;

    private HashMap<Integer, Boolean> keys;
    public Input(long windowID){
        this.windowID = windowID;
        this.keys =  new HashMap<>();//new boolean[GLFW_KEY_LAST];
        for (int i = 32; i < 96; i++)
        {
            keys.put(i, false);
        }

        keys.put(161, false);
        keys.put(162, false);

        for (int i = 256;i < 348; i++)
        {
            keys.put(i, false);
        }
    }

    public boolean isKeyDown(int key){
        return glfwGetKey(windowID, key) == 1;
    }
    public boolean isMouseButtonDown(int key){
        return glfwGetMouseButton(windowID, key) == 1;
    }
    public Vector2f mousePosition(){
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(windowID, posX, posY);
        return new Vector2f((float)posX.get(0), (float)posY.get(0));
    }

    public boolean isKeyPressed(int key){
        return (isKeyDown(key) && !keys.get(key));
    }

    public boolean isKeyReleased(int key){
        //return (!isKeyDown(key) && keys[key]);
        return (!isKeyDown(key) && keys.get(key));
    }


    public void update(){
        for (Map.Entry entry : keys.entrySet())
        {
            entry.setValue(isKeyDown((int) entry.getKey()));
        }
    }
}
