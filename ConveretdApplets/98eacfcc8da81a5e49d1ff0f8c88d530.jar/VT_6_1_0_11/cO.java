// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.HashMap;
import java.util.Map;

class cO
{
    private Map a;
    private aG b;
    private final cs c;
    
    public void a(final String s, final int n) {
        this.a.put(s, new Integer(n));
        this.b.a(n, s);
    }
    
    public String a(final int n) {
        return (String)this.b.a(n);
    }
    
    cO(final cs c) {
        this.c = c;
        this.a = new HashMap();
        this.b = new aG(this.c);
    }
}
