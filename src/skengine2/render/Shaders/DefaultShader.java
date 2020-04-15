package skengine2.render.Shaders;

import org.joml.Matrix4f;

import static skengine2.settings.GraphicSettings.DEFAULT_SHADER;

public class DefaultShader extends ShaderProgram{


    private int location_projection;

    public DefaultShader() {
        super(DEFAULT_SHADER);
        shaderName = DEFAULT_SHADER;
    }

    @Override
    protected void getAllUniformLocations() {
        location_projection = super.getUniformLocation("projection");
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "vertices");
        bindAttribute(1, "textures");
    }

    public void loadProjection(Matrix4f value){
        super.loadMatrix(location_projection, value);
    }

}
