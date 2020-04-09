package skengine2.objects;

import java.util.Comparator;

public class SortObjLayout implements Comparator<Object> {
    public int compare(Object a, Object b)
    {
        return a.layout - b.layout;
    }
}
