// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.e;

import dlt.a.f.h;
import java.util.Iterator;
import dlt.a.b.e;
import dlt.a.b.c;
import dlt.a.d.d;
import java.util.Vector;
import dlt.a.f.i;
import dlt.a.f.g;
import dlt.a.f.f;

public class b implements f, g, i
{
    private Vector int;
    private boolean try;
    private boolean do;
    private d if;
    private a a;
    private boolean for;
    private boolean new;
    
    public b(final d d, final a a) {
        this.for = true;
        this.new = true;
        this.int = new Vector();
        this.a(d);
        this.a = a;
        if (this.a == null) {
            this.a = new a(new c(0.0, 0.0, 0.0), new dlt.a.b.f(0.0, 0.0, 0.0));
        }
        this.do = true;
        this.a.a(this);
    }
    
    public void do(final dlt.a.c.d d) {
        this.if(d);
        d.a(this);
        this.int.addElement(d);
    }
    
    public void a(final dlt.a.c.d d) {
        if (this.int.contains(d)) {
            d.if(this);
            this.int.remove(d);
        }
    }
    
    public void if() {
        this.for();
        final dlt.a.b.a a = this.a.a();
        for (int i = 0; i < this.int.size(); ++i) {
            ((dlt.a.c.d)this.int.elementAt(i)).a(a);
        }
    }
    
    public void a(final dlt.a.c.d d, final c c, final dlt.a.b.f f) {
        d.if(dlt.a.b.a.a(dlt.a.b.a.a(dlt.a.b.a.if(new c(-c.for(), -c.a(), -c.int())), dlt.a.b.a.a(f)), dlt.a.b.a.if(c)));
    }
    
    public void a(final dlt.a.c.d d, final dlt.a.b.b b) {
        d.if(dlt.a.b.a.a(b));
    }
    
    public void a(final dlt.a.c.d d, final dlt.a.b.f f) {
        d.if(dlt.a.b.a.a(f));
    }
    
    public void a(final dlt.a.c.d d, final e e) {
        d.if(dlt.a.b.a.a(e));
    }
    
    public Vector a() {
        return this.int;
    }
    
    public a do() {
        return this.a;
    }
    
    public void a(final d if1) {
        if (this.if != null) {
            this.if.a(this);
        }
        this.if = if1;
        if (this.if != null) {
            this.if.if(this);
        }
        this.try = true;
    }
    
    public void a(final boolean for1) {
        if (for1 != this.for) {
            this.for = for1;
            this.int();
        }
    }
    
    public void if(final boolean new1) {
        if (new1 != this.new) {
            this.new = new1;
            this.new();
        }
    }
    
    private void if(final dlt.a.c.d d) {
        if (!this.do) {
            if (this.for) {
                d.if(this.a.if());
            }
            else {
                d.int();
            }
        }
        if (!this.try) {
            if (this.new && this.if != null) {
                this.for(d);
            }
            else {
                d.case();
            }
        }
    }
    
    private void for(final dlt.a.c.d d) {
        if (!d.new()) {
            final dlt.a.c.f[] do1 = d.do();
            for (int i = 0; i < do1.length; ++i) {
                final dlt.a.c.f f = do1[i];
                this.if.a(f.a(), f.new());
            }
        }
    }
    
    private void for() {
        if (this.do && this.for) {
            this.int();
        }
        if (this.try && this.new && this.if != null) {
            this.new();
        }
    }
    
    private void int() {
        if (this.for) {
            try {
                final Iterator<dlt.a.c.d> iterator = this.int.iterator();
                while (iterator.hasNext()) {
                    iterator.next().if(this.a.if());
                }
            }
            catch (Exception ex) {}
        }
        else {
            try {
                final Iterator<dlt.a.c.d> iterator2 = this.int.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().int();
                }
            }
            catch (Exception ex2) {}
        }
        this.do = false;
    }
    
    private void new() {
        if (this.new && this.if != null) {
            try {
                final Iterator<dlt.a.c.d> iterator = this.int.iterator();
                while (iterator.hasNext()) {
                    this.for(iterator.next());
                }
            }
            catch (Exception ex) {}
        }
        else {
            try {
                final Iterator<dlt.a.c.d> iterator2 = this.int.iterator();
                while (iterator2.hasNext()) {
                    iterator2.next().case();
                }
            }
            catch (Exception ex2) {}
        }
        this.try = false;
    }
    
    public void a(final h h) {
        this.do = true;
    }
    
    public void a(final dlt.a.f.b b) {
        this.try = true;
    }
    
    public void a(final dlt.a.f.e e) {
        this.if(e.a());
    }
}
