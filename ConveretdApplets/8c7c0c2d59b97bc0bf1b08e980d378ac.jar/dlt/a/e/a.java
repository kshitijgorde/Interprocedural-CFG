// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.e;

import dlt.a.f.h;
import dlt.a.f.g;
import java.util.Vector;
import dlt.a.b.f;
import dlt.a.b.c;

public class a
{
    private c a;
    private f if;
    private Vector do;
    
    public a(final c a, final f if1) {
        this.a = a;
        this.if = if1;
        this.do = new Vector();
    }
    
    public void a(final g g) {
        this.do.addElement(g);
    }
    
    public void if(final g g) {
        this.do.removeElement(g);
    }
    
    public f do() {
        return this.if.for();
    }
    
    public c if() {
        return this.a.if();
    }
    
    public void a(final f if1) {
        this.if = if1;
    }
    
    public void a(final c a) {
        this.a = a;
        final h h = new h(this);
        for (int i = 0; i < this.do.size(); ++i) {
            ((g)this.do.elementAt(i)).a(h);
        }
    }
    
    public dlt.a.b.a a() {
        return dlt.a.b.a.a(dlt.a.b.a.if(new c(-this.a.for(), -this.a.a(), -this.a.int())), dlt.a.b.a.a(this.if));
    }
}
