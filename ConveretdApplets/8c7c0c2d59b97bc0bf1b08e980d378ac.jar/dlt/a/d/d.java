// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.d;

import dlt.a.f.a;
import dlt.a.b.c;
import dlt.a.f.f;
import java.util.Vector;
import dlt.a.f.j;

public abstract class d implements j
{
    protected b if;
    protected double a;
    private Vector do;
    
    public d(final b if1, final double a) {
        this.if = if1;
        this.a = a;
        this.do = new Vector();
        this.if.a(this);
    }
    
    public void if(final f f) {
        this.do.addElement(f);
    }
    
    public void a(final f f) {
        this.do.removeElement(f);
    }
    
    public b if() {
        return this.if;
    }
    
    public void a(final b if1) {
        if (this.if != null) {
            this.if.if(this);
        }
        (this.if = if1).a(this);
        final dlt.a.f.b b = new dlt.a.f.b(this);
        for (int i = 0; i < this.do.size(); ++i) {
            ((f)this.do.elementAt(i)).a(b);
        }
    }
    
    public double a() {
        return this.a;
    }
    
    public void a(final double a) {
        this.a = a;
        final dlt.a.f.b b = new dlt.a.f.b(this);
        for (int i = 0; i < this.do.size(); ++i) {
            ((f)this.do.elementAt(i)).a(b);
        }
    }
    
    public abstract void a(final c p0, final dlt.a.c.b p1);
    
    public void a(final a a) {
        final dlt.a.f.b b = new dlt.a.f.b(this);
        for (int i = 0; i < this.do.size(); ++i) {
            ((f)this.do.elementAt(i)).a(b);
        }
    }
}
