package skengine2.render.Shaders;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class ShaderProgram {
    private int programID;
    private int vs;
    private int fs;
    protected String shaderName;
    private boolean isBind = false;

    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);

    public ShaderProgram(String filename){
        programID = glCreateProgram();
        vs = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vs, readFile(filename + ".vs"));
        glCompileShader(vs);
        if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1){
            System.err.println(glGetShaderInfoLog(vs));
            System.exit(1);
        }

        fs = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fs, readFile(filename + ".fs"));
        glCompileShader(fs);
        if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1){
            System.err.println(glGetShaderInfoLog(fs));
            System.exit(1);
        }

        glAttachShader(programID, vs);
        glAttachShader(programID, fs);

        bindAttributes();

        glLinkProgram(programID);
        if(glGetProgrami(programID, GL_LINK_STATUS)!=1){
            System.err.println(glGetProgramInfoLog(programID));
            System.exit(1);
        }
        glValidateProgram(programID);
        if(glGetProgrami(programID, GL_VALIDATE_STATUS)!=1){
            System.err.println(glGetProgramInfoLog(programID));
            System.exit(1);
        }

        getAllUniformLocations();
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName){
        return GL20.glGetUniformLocation(programID,uniformName);
    }

    public void start(){
        glUseProgram(programID);
        isBind = true;
    }
    public void stop(){
        glUseProgram(0);
        isBind = false;
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programID, vs);
        GL20.glDetachShader(programID, fs);
        GL20.glDeleteShader(vs);
        GL20.glDeleteShader(fs);
        GL20.glDeleteProgram(programID);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }

    protected void loadFloat(int location, float value){
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value){
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int location, Vector3f vector){
        GL20.glUniform3f(location,vector.x,vector.y,vector.z);
    }

    protected void loadVector(int location, Vector4f vector){
        GL20.glUniform4f(location,vector.x,vector.y,vector.z, vector.w);
    }

    protected void load2DVector(int location, Vector2f vector){
        GL20.glUniform2f(location,vector.x,vector.y);
    }

    protected void loadBoolean(int location, boolean value){
        float toLoad = 0;
        if(value){
            toLoad = 1;
        }
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f matrix){
        matrix.get(matrixBuffer);
        glUniformMatrix4fv(location, false, matrixBuffer);
    }


    private String readFile(String filename){
        StringBuilder string = new StringBuilder();
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(new File("./res/shaders/"+filename)));
            String line;
            while ((line = br.readLine())!= null){
                string.append(line);
                string.append("\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Shader file read error");
            System.exit(-1);
        }
        return string.toString();
    }

    public String getShaderName() {
        return shaderName;
    }

    public boolean isBind() {
        return isBind;
    }
}
