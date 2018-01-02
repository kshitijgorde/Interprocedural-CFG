// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class T
{
    public static final T a;
    public static final T b;
    private List c;
    
    private T(final List list) {
        this.c = Collections.unmodifiableList((List<?>)list);
    }
    
    public final List a() {
        return this.c;
    }
    
    static {
        a = new T(new ArrayList());
        final ArrayList list;
        (list = new ArrayList()).add("AgencyViewState");
        b = new T(list);
        final ArrayList<String> list2;
        (list2 = new ArrayList<String>()).add("AgencyViewState");
        list2.add("AgencyChangeState");
        new T(list2);
    }
}
