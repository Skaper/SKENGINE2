package skengine2.render;
import skengine2.Camera;
import skengine2.objects.Entity;
import skengine2.objects.Transform;
import org.joml.Matrix4f;

import static skengine2.settings.GraphicSettings.DEFAULT_SHADER;

public class Sprite {
    public Transform transform;

    private String fileName;
    private Entity parent;
    private Texture texture;
    private Model model;
    private String shaderName = DEFAULT_SHADER;
    private Shader shader;
    private Matrix4f scaleMatrix;

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
        texture = new Texture(fileName);
        model = new Model(texture.getVertices());
        shader = new Shader(shaderName);
        scaleMatrix = new Matrix4f().scale(1f);
    }

    void render(Camera camera){
        Matrix4f target = camera.getProjection();
        texture.bind();
        shader.bind();
        //shader.setUniform("sampler", 0);
        shader.setUniform("projection", transform.getProjection(target));
        //shader.setUniform("projection", camera.getProjection().mul(target));
        model.render();
        shader.unbind();
        texture.unbind();
    }

    public void setShaderName(String shaderName) {
        this.shaderName = shaderName;
        shader = new Shader(shaderName);
    }

    public String getShaderName() {
        return shaderName;
    }

    public int getHeight(){
        if(texture!=null) return texture.getHeight();
        return 0;
    }

    public int getWidth(){
        if(texture != null) return texture.getWidth();
        return 0;
    }
}
