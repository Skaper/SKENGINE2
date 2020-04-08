package core.render;
import core.Input;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
public class Window {
    private String title = "";
    private long windowID;
    private boolean fullScreen;


    private int width, height;

    public static void setCallBacks(){
        /*glfwSetErrorCallback(new GLFWErrorCallback() {
            @Override
            public void invoke(int error, long description) {
                throw new IllegalAccessError("GLFW error [" + Integer.toHexString(error) + "]: " + GLFWErrorCallback.getDescription(description));
            }
        });*/
        glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
    }

    public Window(){
        setSize(640, 480);
        setFullScreen(false);
    }

    public void createWindow(){
        setCallBacks();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        windowID = glfwCreateWindow(width,
                height,
                title,
                fullScreen ? glfwGetPrimaryMonitor() : 0,
                0);
        if(windowID==0)
            throw new IllegalStateException("Failed to create window");
        if(!fullScreen) {
            GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwSetWindowPos(windowID,
                    (videoMode.width() - width) / 2,
                    (videoMode.height() - height) / 2);

            glfwShowWindow(windowID);

        }
        glfwMakeContextCurrent(windowID);
        GL.createCapabilities();
        //glEnable(GL_TEXTURE_2D);

        /*glViewport(0, 0, width, height); //NEW
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, width, 0, height, -1, 1);*/
    }

    public void update(){
        glfwPollEvents();
    }

    public void clear(){
        glClear(GL_COLOR_BUFFER_BIT);
    }


    public void setFullScreen(boolean fullScreen){
        this.fullScreen = fullScreen;
    }
    public boolean isFullScreen(){
        return fullScreen;
    }
    public boolean shouldClose(){
        return glfwWindowShouldClose(windowID);
    }

    public void swapBuffers(){
        glfwSwapBuffers(windowID);
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getWindowID(){
        return windowID;
    }

    public void setTitle(String title) {
        this.title = title;
        glfwSetWindowTitle(windowID, title);
    }
}
