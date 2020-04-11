package skengine2.core;

import skengine2.Camera;
import skengine2.render.Renderer;
import skengine2.render.Window;
import skengine2.scene.Scene;
import skengine2.scene.ScenesManager;
import skengine2.utils.Timer;

import static skengine2.settings.GraphicSettings.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine implements Runnable{
    private Thread mainThread;

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/FPS_LIMIT;
    private int width = SCREEN_WIDTH, height = SCREEN_HEIGHT;
    private float scale = SCREEN_SCALE;
    private String title = "SKENGINE2 v0.0.11";

    private Window window;
    private Renderer renderer;
    private long windowID;
    private Camera mainCamera;
    private Input input;

    private ScenesManager scenesManager;
    private Scene currentScene;

    public GameEngine(ScenesManager scenesManager) {
        this.scenesManager = scenesManager;
    }

    public void start(){
        init();
        window = new Window();
        window.setFullScreen(FULL_SCREEN);
        window.setSize(width, height);
        window.createWindow();
        windowID = window.getWindowID();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        mainCamera = new Camera(width, height);

        input = new Input(windowID, mainCamera);
        //setup scene manager

        //setup current scene
        scenesManager.setup(this);
        currentScene = scenesManager.getMainScene();
        if(currentScene == null){
            System.err.println("Default Scene Not Set");
            return;
        }
        renderer = new Renderer(this);


        mainThread = new Thread(this);
        mainThread.run();

    }
    private void init(){
        Window.setCallBacks();
        if(!glfwInit()){
            System.err.println("GLFW Failed to initialize!");
            System.exit(1);
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
    }
    @Override
    public void run() {

        currentScene.setupObjects(this);
        currentScene.setup(this);

        running = true;
        boolean render = false;
        double firstTime = 0;
        double lastTime = Timer.getSeconds();
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        //TODO SETUP OBJECTS

        while (running && !window.shouldClose()){
            firstTime = Timer.getSeconds();
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            render = !LOCK_FPS;

            while (unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;



                //PHYSICS UPDATE

                //UPDATE SCENE and OBJECTS
                currentScene.update(this);
                currentScene.updateObjects(this);

                //CAMERA UPDATE
                mainCamera.update();

                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }

                //INPUT UPDATE
                input.update();
                render = true;


                //TODO IF NEXT SCENE
            }
            if(render){
                //CLEAR SCREEN
                window.update();
                window.clear();

                //SCENE and OBJECTS RENDER
                currentScene.render(this, renderer);
                currentScene.renderObjects(this, renderer);
                renderer.resetCount();
                //SHOW FPS;
                //System.out.println("FPS: "+fps);
                window.setTitle(title + " | FPS: " + fps);

                //WINDOW UPDATE;

                window.swapBuffers();

                frames ++;
            }else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        glfwTerminate();
    }

    public float deltaTime(){
        return (float)UPDATE_CAP;
    }
    public Camera getMainCamera(){return mainCamera;}

    public Input getInput() {
        return input;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getWindowID() {
        return windowID;
    }
}
