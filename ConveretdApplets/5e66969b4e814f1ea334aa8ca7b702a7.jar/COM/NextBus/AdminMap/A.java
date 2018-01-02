// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class A
{
    private Map a;
    private O b;
    
    public A(final O b, final Map a) {
        this.b = b;
        this.a = a;
    }
    
    public final void a() {
        final List b = this.b.c.b();
        int n = 0;
        final Iterator<t> iterator = b.iterator();
        while (iterator.hasNext()) {
            final Iterator g = iterator.next().g();
            while (g.hasNext()) {
                final G g2 = g.next();
                final boolean b2 = this.b(g2.g());
                this.a(g2.g(), b2);
                if (b2) {
                    ++n;
                }
                if (n > 10) {
                    return;
                }
            }
        }
    }
    
    public final void b() {
        final Iterator<t> iterator = this.b.c.b().iterator();
        while (iterator.hasNext()) {
            final Iterator g = iterator.next().g();
            while (g.hasNext()) {
                this.a(g.next().g(), false);
            }
        }
    }
    
    private boolean b(String string) {
        try {
            final String p;
            if ((p = this.b.p()) == null) {
                return true;
            }
            if (p.equals("")) {
                return true;
            }
            final String s = string;
            string = p;
            if ((string = "::" + string + ":").indexOf(":" + s + ":") > 0) {
                return true;
            }
        }
        catch (Throwable t) {
            return true;
        }
        return false;
    }
    
    public final boolean a(final String s) {
        final String s2;
        return (s2 = this.a.get(s)) != null && Boolean.valueOf(s2);
    }
    
    public final void a(final String s, final boolean b) {
        this.a.put(s, new Boolean(b).toString());
    }
}
