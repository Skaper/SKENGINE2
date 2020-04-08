package core.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import org.lwjgl.BufferUtils;
import core.utils.Timer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class Texture {
    private int id;
    private int width;
    private int height;

    public static final int LINEAR = GL_LINEAR;
    public static final int NEAREST = GL_NEAREST;

    public static final int CLAMP = GL_CLAMP;
    public static final int CLAMP_TO_EDGE = GL_CLAMP_TO_EDGE;
    public static final int REPEAT = GL_REPEAT;

    public Texture(String filename){
        //double start = Timer.getNano();
        InputStream in = null;
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File(filename));
            width = bi.getWidth();
            height = bi.getHeight();
            int[] pixels_raw;
            pixels_raw = bi.getRGB(0, 0, width, height, null, 0, width);
            ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);
            for(int i = 0; i < height; i++){
                for(int j = 0; j < width; j++){
                    int pixel = pixels_raw[i*width+j];
                    pixels.put((byte)((pixel >> 16) & 0xFF)); //Red
                    pixels.put((byte)((pixel >> 8) & 0xFF));  //Green
                    pixels.put((byte)((pixel ) & 0xFF));      //Blue
                    pixels.put((byte)((pixel >> 24) & 0xFF)); //Alpha
                }
            }

           pixels.flip();
             /*URL url = new File(filename).toURI().toURL();

            in = url.openStream();//new InputStream(filename);
            PNGDecoder dec = new PNGDecoder(in);

            width = dec.getWidth();
            height = dec.getHeight();
            final int bpp = 4;
            ByteBuffer pixels = BufferUtils.createByteBuffer(bpp * width * height);
            dec.decode(pixels, width * bpp, PNGDecoder.Format.RGBA);
            pixels.flip();*/


            id = glGenTextures();

            bind(0);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            //glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            //glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);


            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //System.out.println("Time "+(Timer.getNano()-start));
    }

    @Override
    protected void finalize() throws Throwable {
        glDeleteTextures(id);
        super.finalize();
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
    public void bind(int sampler){
        /*if(sampler >= 0 && sampler <=31) {
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, id);
        }*/
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public float[] getVertices(){
         return new float[]{
                 -width/2f, height/2f, 0, //TOP LEFT
                 width/2f, height/2f,  0, //TOP RIGHT
                 width/2f, -height/2f, 0, //BOTTOM RIGHT
                 -width/2f, -height/2f, 0,//BOTTOM LEFT
         };
    }

    private float normalize(float value, float min, float max) {
        return 1 - ((value - min) / (max - min));
    }
}