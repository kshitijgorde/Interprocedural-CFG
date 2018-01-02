// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b.a;

import java.io.InputStream;

public class g
{
    private static g a;
    
    static {
        g.a = new g();
    }
    
    public static InputStream a(final String s) {
        return g.a.getClass().getResourceAsStream(s);
    }
}
