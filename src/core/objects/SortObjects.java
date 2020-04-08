package core.objects;

import java.util.Comparator;

public class SortObjects implements Comparator<Object> {
    public int compare(Object a, Object b)
    {
        return a.layout - b.layout;
    }
}
