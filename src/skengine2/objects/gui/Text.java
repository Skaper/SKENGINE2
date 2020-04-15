package skengine2.objects.gui;

import org.joml.Vector3f;
import skengine2.objects.Transform;
import skengine2.render.Texture;

public class Text{
    private String text;
    private Transform transform;
    private Vector3f color;
    private static final String DEFAULT_FONT = "./res/fonts/roboto.png";
    private Texture texture;
    public Text(String text, Transform transform){
        this.transform = transform;
        this.text = text;
        color = new Vector3f(1,1,1);
        texture = new Texture(DEFAULT_FONT);
    }
}
