// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;
import java.util.Hashtable;

public final class dy
{
    private static dy a;
    private ew a;
    private Hashtable a;
    
    private dy() {
        (this.a = ew.a()).a("Creating new MessageRouter");
        this.a = new Hashtable();
    }
    
    public static dy a() {
        return dy.a;
    }
    
    public final void a(final b b) {
        if (null != this.a.put(b.a(), b)) {
            this.a.c("Replaced existing MessageListener with name " + b.a());
        }
    }
    
    public final Object a(final String s, final int n, final Object o) {
        final b b = this.a.get(s);
        if (null != b) {
            b.a(n, o);
            return null;
        }
        final StringBuffer sb;
        (sb = new StringBuffer("Could not find listener:")).append(s);
        this.a.d(sb.toString());
        throw new IOException(sb.toString());
    }
    
    static {
        dy.a = new dy();
    }
}
