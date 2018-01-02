// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.d;

import dlt.a.f.a;
import dlt.a.f.j;
import java.util.Vector;
import dlt.a.b.c;

public class b
{
    private c a;
    private Vector if;
    
    public b(final c a) {
        this.a = a;
        this.if = new Vector();
    }
    
    public void a(final j j) {
        this.if.add(j);
    }
    
    public void if(final j j) {
        this.if.remove(j);
    }
    
    public c a() {
        return this.a.if();
    }
    
    public void a(final c a) {
        this.a = a;
        final a a2 = new a(this);
        for (int i = 0; i < this.if.size(); ++i) {
            ((j)this.if.elementAt(i)).a(a2);
        }
    }
}
