package skengine2.objects;

import java.util.Comparator;

public class SortObjLayout implements Comparator<Entity> {
    public int compare(Entity a, Entity b)
    {
        return a.layout - b.layout;
    }
}
