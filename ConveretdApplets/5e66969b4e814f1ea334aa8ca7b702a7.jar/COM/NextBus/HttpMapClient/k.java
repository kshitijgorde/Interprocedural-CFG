// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Enumeration;
import java.util.Vector;

public final class k
{
    private static String a;
    private Vector b;
    
    public k() {
        this.b = new Vector();
    }
    
    public final void a(final o o) {
        this.b.addElement(o);
    }
    
    public final String toString() {
        return "Components: " + this.b + ".";
    }
    
    final String a() {
        final StringBuffer sb = new StringBuffer();
        sb.append("v=" + c.a(k.a));
        final Enumeration<o> elements = (this = this).b.elements();
        while (elements.hasMoreElements()) {
            sb.append("&c=" + c.a(elements.nextElement().a()));
        }
        return sb.toString();
    }
    
    static {
        k.a = "6.0";
    }
}
