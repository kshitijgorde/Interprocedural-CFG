// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.c;

import dlt.a.b.b;
import dlt.a.f.i;
import dlt.a.f.e;
import java.util.Vector;
import dlt.a.b.c;

public class d
{
    private a[] new;
    private f[] try;
    private c int;
    private c if;
    private double do;
    private Vector for;
    private boolean a;
    
    public d(final a[] new1, final f[] try1, final c int1, final double do1) {
        this.a = false;
        this.new = new1;
        this.try = try1;
        this.int = int1;
        this.do = do1;
        this.if = new c(0.0, 0.0, 0.0);
        this.for = new Vector();
        for (int i = 0; i < this.new.length; ++i) {
            this.new[i].int();
        }
    }
    
    public a[] a() {
        return this.new;
    }
    
    public f[] do() {
        return this.try;
    }
    
    public c if() {
        return this.int.if();
    }
    
    public void a(final c c) {
        this.if(dlt.a.b.a.if(new c(-(this.int.for() - c.for()), -(this.int.a() - c.a()), -(this.int.int() - c.int()))));
    }
    
    public c byte() {
        return this.if.if();
    }
    
    public double try() {
        return this.do;
    }
    
    public void if(final dlt.a.b.a a) {
        for (int i = 0; i < this.try.length; ++i) {
            this.try[i].do(a);
        }
        for (int j = 0; j < this.new.length; ++j) {
            this.new[j].int();
        }
        a.a(this.int);
        final e e = new e(this);
        for (int k = 0; k < this.for.size(); ++k) {
            ((i)this.for.elementAt(k)).a(e);
        }
    }
    
    public void a(final dlt.a.b.a a) {
        for (int i = 0; i < this.try.length; ++i) {
            this.try[i].a(a);
        }
        a.a(this.int, this.if);
    }
    
    public void do(final dlt.a.b.a a) {
        for (int i = 0; i < this.try.length; ++i) {
            this.try[i].if(a);
        }
        a.a(this.if, this.if);
    }
    
    public void a(final i i) {
        this.for.add(i);
    }
    
    public void if(final i i) {
        this.for.remove(i);
    }
    
    public void int() {
        for (int i = 0; i < this.new.length; ++i) {
            this.new[i].for();
        }
    }
    
    public void if(final c c) {
        for (int i = 0; i < this.new.length; ++i) {
            this.new[i].a(c);
        }
    }
    
    public void case() {
        this.a = true;
        for (int i = 0; i < this.try.length; ++i) {
            this.try[i].int();
        }
    }
    
    public boolean new() {
        return this.a;
    }
    
    public void for() {
        final c a = this.new[0].new()[0].a();
        double for1 = a.for();
        double a2 = a.a();
        double int1 = a.int();
        double for2 = a.for();
        double a3 = a.a();
        double int2 = a.int();
        for (int i = 0; i < this.new.length; ++i) {
            final f[] new1 = this.new[i].new();
            for (int j = 0; j < 3; ++j) {
                final c a4 = new1[j].a();
                final double for3 = a4.for();
                final double a5 = a4.a();
                final double int3 = a4.int();
                if (for3 < for2) {
                    for2 = for3;
                }
                else if (for3 > for1) {
                    for1 = for3;
                }
                if (a5 < a3) {
                    a3 = a5;
                }
                else if (a5 > a2) {
                    a2 = a5;
                }
                if (int3 < int2) {
                    int2 = int3;
                }
                else if (int3 > int1) {
                    int1 = int3;
                }
            }
        }
        this.if(dlt.a.b.a.a(new b(-((for1 - for2) / 2.0 + for2), -((a2 - a3) / 2.0 + a3), -((int1 - int2) / 2.0 + int2))));
    }
}
