package core.render;

public class ModelData {
    public static float[] getTextureCoords(){
        return new float[] {
                0,0,
                1,0,
                1,1,
                0,1,
        };
    }
    public static int[] getIndices(){
        return new int[]{
                0,1,2,
                2,3,0
        };
    }
}
