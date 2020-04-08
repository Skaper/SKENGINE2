package core.objects;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Transform {
    public Vector2f position;
    public Vector2f scale;
    public float angle;

    public Transform(){
        position = new Vector2f(0,0);
        scale = new Vector2f(1,1);
        angle = 0;
    }
    public Transform(Vector2f position){
        this.position = position;
        scale = new Vector2f(1,1);
        angle = 0;
    }
    public Transform(Vector2f position, Vector2f scale){
        this.position = position;
        this.scale = scale;
        angle = 0;
    }

    public Matrix4f getProjection(Matrix4f target) {
        target.translate(new Vector3f(position.x, position.y, 0));
        target.scale(new Vector3f(scale.x, scale.y, 0));
        target.rotateZ((float)Math.toRadians(angle));
        return target;
    }

    public Transform clone(){
        return new Transform(new Vector2f(position.x, position.y),
                             new Vector2f(scale.x, scale.y));
    }
}
