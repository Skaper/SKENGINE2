package skengine2.render;
import skengine2.Camera;
import skengine2.objects.Entity;
import skengine2.objects.Transform;
import org.joml.Matrix4f;
import skengine2.render.Shaders.DefaultShader;
import skengine2.render.Shaders.ShaderProgram;

import static skengine2.settings.GraphicSettings.DEFAULT_SHADER;

public class Sprite {
    public Transform transform;

    private String fileName;
    private Entity parent;
    private Model model;
    private String shaderName = DEFAULT_SHADER;
    private ShaderProgram shader;

    public Sprite(String fileName, Entity parent){
        this.transform = new Transform();
        this.fileName = fileName;
        this.parent = parent;
        init();
    }
    public Sprite(Transform transform, Entity parent){
        this.transform = transform;
        this.parent = parent;
        init();
    }
    public Sprite(Transform transform, String fileName, Entity parent){
        this.transform = transform;
        this.fileName = fileName;
        this.parent = parent;
        init();
    }


    public void setImage(String fileName){
        this.fileName = fileName;
        init();
    }

    private void init(){
        model = new Model(new Texture(fileName));
        shader = null;
    }

    void render(Camera camera){
        shader.start();
        //((DefaultShader)shader).loadProjection(transform.getProjection(camera.getProjection()));
        model.render();
        shader.stop();
    }

    void render(Camera camera, DefaultShader shader){
        shader.loadProjection(transform.getProjection(camera.getProjection()));
        model.render();
    }


    public void setShader(ShaderProgram shader) {
        this.shader = shader;
        this.shaderName = shader.getShaderName();
    }

    public String getShaderName() {
        return shaderName;
    }

    public int getHeight(){
        if(model!=null) return model.getTexture().getHeight();
        return 0;
    }

    public int getWidth(){
        if(model!=null) return model.getTexture().getWidth();
        return 0;
    }
}
