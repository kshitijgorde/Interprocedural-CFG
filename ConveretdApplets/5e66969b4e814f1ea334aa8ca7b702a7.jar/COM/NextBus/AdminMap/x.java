// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Iterator;

public class x
{
    private static O b;
    public static t a;
    
    public x(final O b) {
        new VerticalFlowPanel();
        new VerticalFlowPanel();
        x.b = b;
    }
    
    public static t a(final String s) {
        final Iterator<t> iterator = (Iterator<t>)x.b.c.b().iterator();
        t t = null;
        while (iterator.hasNext()) {
            t = iterator.next();
            if ((s != null && t.f(s)) || (!iterator.hasNext() && x.a == null)) {
                break;
            }
        }
        return t;
    }
    
    public final void b(final String s) {
        (x.a = a(s)).k(s);
    }
    
    private x() {
    }
    
    public static boolean a(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
}
