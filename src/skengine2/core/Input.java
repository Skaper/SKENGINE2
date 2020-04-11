package skengine2.core;

import org.joml.Vector2f;
import skengine2.Camera;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
    private long windowID;
    private Camera camera;

    private HashMap<Integer, Boolean> keys;
    private boolean[] mouseButtons;

    private int scroll;
    private Vector2f cursorPositionWindow;
    private Vector2f cursorPositionW;

    public static int SCROLL_DOWN = -1;
    public static int SCROLL_UP = 1;
    public static int SCROLL_NONE = 1;

    public Input(long windowID, Camera camera){
        this.windowID = windowID;
        this.camera = camera;
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
        scroll = 0;
        glfwSetScrollCallback(windowID, this::scroll_callback);

        cursorPositionWindow = new Vector2f(0,0);
        glfwSetCursorPosCallback(windowID, this::cursorPosListener);

        mouseButtons = new boolean[GLFW_MOUSE_BUTTON_LAST];
    }


    public boolean isKeyDown(int key){
        return glfwGetKey(windowID, key) == 1;
    }


    public boolean isKeyPressed(int key){
        return (isKeyDown(key) && !keys.get(key));
    }

    public boolean isKeyReleased(int key){
        return (!isKeyDown(key) && keys.get(key));
    }

    public boolean isButtonDown(int key){
        return glfwGetMouseButton(windowID, key) == 1;
    }
    //Mouse
    public boolean isButtonPressed(int button){
        return (isButtonDown(button) && !mouseButtons[button]);
    }

    public boolean isButtonReleased(int button){
        return (!isButtonDown(button) && mouseButtons[button]);
    }

    private void cursorPosListener(long windowID, double x, double y){
        cursorPositionWindow.set(x, y);
    }
    public Vector2f mousePositionWindow(){
        return new Vector2f(cursorPositionWindow.x, -cursorPositionWindow.y);
    }
    public Vector2f mousePositionWorld(){
        float x = (cursorPositionWindow.x/camera.getScale().x) - camera.getWidth()/2f + (camera.getPosition().x);
        float y = (-cursorPositionWindow.y/camera.getScale().x) + camera.getHeight()/2f + (camera.getPosition().y);
        return new Vector2f(x, y);
    }

    public int getScroll(){
        return scroll;
    }
    private void scroll_callback(Long windowID, double xoffset, double yoffset)
    {
        scroll = (int)yoffset;
    }

    public void update(){
        scroll = 0;
        for (Map.Entry entry : keys.entrySet())
        {
            entry.setValue(isKeyDown((int) entry.getKey()));
        }
        for(int i = 0; i < GLFW_MOUSE_BUTTON_LAST; i++){
            mouseButtons[i] = (glfwGetMouseButton(windowID, i) == 1) ? true : false;
        }
    }
}
