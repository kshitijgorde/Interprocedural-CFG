// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Hashtable;

public class k
{
    public ek a;
    private Hashtable a;
    public av a;
    private bx a;
    
    public k() {
        this.a = new Hashtable(7);
    }
    
    public void a() {
        if (this.a.a(32L)) {
            this.a = new fk(this, this.a);
            return;
        }
        this.c();
    }
    
    public void b() {
        if (this.a != null) {
            this.a.l();
        }
    }
    
    public final void c() {
        this.a.a(this.a);
    }
    
    public final void a(final String s, final String s2) {
        this.a.put(s, s2);
    }
}
