package core.render;
import core.Camera;
import core.objects.Object;
import core.objects.Transform;
import org.joml.Matrix4f;
import org.joml.Vector2f;

import static core.settings.GraphicSettings.DEFAULT_SHADER;

public class Sprite {
    public Transform transform;

    private String fileName;
    private Object parent;
    private Texture texture;
    private Model model;
    private String shaderName = DEFAULT_SHADER;
    private Shader shader;
    private Matrix4f scaleMatrix;

    public Sprite(String fileName, Object parent){
        this.transform = new Transform();
        this.fileName = fileName;
        this.parent = parent;
        init();
    }
    public Sprite(Transform transform, Object parent){
        this.transform = transform;
        this.parent = parent;
        init();
    }
    public Sprite(Transform transform, String fileName, Object parent){
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

    public void render(Camera camera){
        Matrix4f target = camera.getProjection();
        texture.bind();
        shader.bind();
        shader.setUniform("sampler", 0);
        //shader.setUniform("projection", target);
        shader.setUniform("projection", transform.getProjection(target));
        //shader.setUniform("projection", camera.getProjection().mul(target));
        model.render();
    }

    public void setShaderName(String shaderName) {
        this.shaderName = shaderName;
        shader = new Shader(shaderName);
    }

    public String getShaderName() {
        return shaderName;
    }
}
