package skengine2;

import skengine2.objects.Object;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Vector2f position;
    private Vector2f scale;
    private Vector2f target;
    private Matrix4f projection;



    public Camera(int width, int height){
        position = new Vector2f(0,0);
        scale = new Vector2f(1f,1f);
        projection = new Matrix4f().setOrtho2D(-width/2, width/2, -height/2, height/2);

        target = new Vector2f(0,0);
    }
    public void setPosition(Vector2f position){
        this.position = new Vector2f(-position.x, -position.y).mul(scale);
    }

    public void addPosition(Vector2f position){
        this.position.sub(new Vector2f(position.x, position.y)).mul(scale);;
    }

    public Vector2f getPosition(){ return position;}

    public void update(){
        this.position.x = -target.x;
        this.position.y = -target.y;
        position = position.mul(scale);

    }

    public void setTarget(Object target){
        this.target = target.transform.position;
    }

    public void setScale(Vector2f scale){
        if(scale.x>0 && scale.y > 0) this.scale = new Vector2f(scale.x, scale.y);
    }

    public void addZoom(float value){
        if(value < 0 && scale.x + value > 0 && scale.y + value > 0){
            this.scale.add(new Vector2f(value));
        }else if(value > 0){
            this.scale.add(new Vector2f(value));
        }
    }

    public Vector2f getScale(){
        return new Vector2f(scale.x, scale.y);
    }

    public Matrix4f getProjection(){
        Matrix4f target = new Matrix4f();
        Matrix4f pos = new Matrix4f().setTranslation(new Vector3f(position.x, position.y, 0));

        target = projection.mul(pos, target).scale(new Vector3f(scale.x, scale.y, 0));
        return target;
    }
}
