// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import dlt.a.f.c;
import java.util.Vector;
import dlt.a.e.b;
import dlt.a.f.d;

public abstract class f implements d
{
    protected l goto;
    protected a long;
    protected int null;
    protected int else;
    
    public f(final a a, final l goto1) {
        this.a(a);
        this.goto = goto1;
    }
    
    public void a(final b b, final m m) {
        try {
            final Vector a = b.a();
            if (a.size() > 0) {
                this.a(a);
                for (int i = 0; i < a.size(); ++i) {
                    final dlt.a.c.d d = a.elementAt(i);
                    final int a2 = m.a(d);
                    if (a2 == 2) {
                        this.if(d, m);
                    }
                    else if (a2 == 1) {
                        this.a(d, m);
                    }
                }
            }
            else {
                this.long.a("Waiting for model(s)");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected abstract void a(final Vector p0);
    
    public void a(final a long1) {
        if (long1 != null) {
            long1.a(this);
        }
        (this.long = long1).if(this);
    }
    
    public void a(final l goto1) {
        this.goto = goto1;
    }
    
    protected int if() {
        return this.null;
    }
    
    protected int do() {
        return this.else;
    }
    
    private void if(final dlt.a.c.d d, final m m) {
        final dlt.a.c.a[] a = d.a();
        for (int i = 0; i < a.length; ++i) {
            final dlt.a.c.a a2 = a[i];
            if (!a2.do()) {
                m.if(a2);
                this.for(a2);
            }
        }
    }
    
    private void a(final dlt.a.c.d d, final m m) {
        final dlt.a.c.a[] a = d.a();
        for (int i = 0; i < a.length; ++i) {
            final dlt.a.c.a a2 = a[i];
            if (!a2.do()) {
                final dlt.a.c.a do1 = m.do(a2);
                if (do1 != null) {
                    this.for(do1);
                }
            }
        }
    }
    
    protected abstract void for(final dlt.a.c.a p0);
    
    public void a(final c c) {
        if (c.do() == 0) {
            this.null = c.if();
            this.else = c.for();
        }
    }
}
