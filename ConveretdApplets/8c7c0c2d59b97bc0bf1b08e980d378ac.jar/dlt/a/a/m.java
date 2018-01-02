// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import dlt.a.b.h;
import dlt.a.b.g;
import java.util.Vector;
import dlt.a.c.f;
import dlt.a.c.a;
import dlt.a.f.d;

public class m implements d
{
    private double do;
    private double new;
    private double if;
    private c[] try;
    public static final int case = 0;
    public static final int a = 1;
    public static final int char = 2;
    private double byte;
    private double int;
    private double for;
    
    public m(final double n, final double n2) {
        this.do = 100.0;
        this.new = 400.0;
        this.if = 100000.0;
        this.try = new c[6];
        this.if(n);
        this.a(n2);
        this.a();
    }
    
    public int a(final dlt.a.c.d d) {
        final double try1 = d.try();
        final dlt.a.b.c byte1 = d.byte();
        final double sqrt = Math.sqrt(byte1.for() * byte1.for());
        final double sqrt2 = Math.sqrt(byte1.a() * byte1.a());
        final double int1 = byte1.int();
        final double sqrt3 = Math.sqrt(2.0 * (try1 * try1));
        if (this.do < int1 - sqrt3 && this.if > int1 + sqrt3 && int1 > sqrt + sqrt3 && int1 > sqrt2 + sqrt3) {
            return 2;
        }
        if (this.do > int1 + sqrt3 || this.if < int1 - sqrt3 || int1 < sqrt - sqrt3 || int1 < sqrt2 - sqrt3) {
            return 0;
        }
        return 1;
    }
    
    public a do(final a a) {
        final int[] array = { 0, 0, 0, 0, 0, 0 };
        final f[] new1 = a.new();
        for (int i = 0; i < new1.length; ++i) {
            final double for1 = new1[i].do().for();
            final double a2 = new1[i].do().a();
            final double int1 = new1[i].do().int();
            final double sqrt = Math.sqrt(int1 * int1);
            if (this.do > sqrt) {
                final int[] array2 = array;
                final int n = 0;
                ++array2[n];
            }
            if (this.if < sqrt) {
                final int[] array3 = array;
                final int n2 = 1;
                ++array3[n2];
            }
            if (sqrt < for1) {
                final int[] array4 = array;
                final int n3 = 2;
                ++array4[n3];
            }
            if (-sqrt > for1) {
                final int[] array5 = array;
                final int n4 = 3;
                ++array5[n4];
            }
            if (sqrt < a2) {
                final int[] array6 = array;
                final int n5 = 4;
                ++array6[n5];
            }
            if (-sqrt > a2) {
                final int[] array7 = array;
                final int n6 = 5;
                ++array7[n6];
            }
        }
        for (int j = 0; j < 6; ++j) {
            if (array[j] == new1.length) {
                return null;
            }
        }
        final a a3 = this.a(a);
        if (a3 != null) {
            this.if(a3);
        }
        return a3;
    }
    
    public void if(final a a) {
        final f[] new1 = a.new();
        for (int i = 0; i < new1.length; ++i) {
            final dlt.a.b.c do1 = new1[i].do();
            double int1 = do1.int();
            if (int1 == 0.0) {
                int1 = 0.01;
            }
            new1[i].if().a((int)(Object)new Double(this.new * (do1.for() / int1)), -(int)(Object)new Double(this.new * (do1.a() / int1)), (this.if * int1 / this.int - this.for) / int1);
        }
    }
    
    public void if(final double do1) {
        this.do = do1;
        this.try[4] = new c(new dlt.a.b.c(do1, do1, do1), new dlt.a.b.c(do1, -do1, do1), new dlt.a.b.c(-do1, -do1, do1));
        this.int = this.if - do1;
        this.byte = do1 * this.if;
        this.for = this.byte / this.int;
    }
    
    public void a(final double if1) {
        this.if = if1;
        this.try[5] = new c(new dlt.a.b.c(this.do, this.do, if1), new dlt.a.b.c(-this.do, this.do, if1), new dlt.a.b.c(-this.do, -this.do, if1));
        this.int = if1 - this.do;
        this.byte = this.do * if1;
        this.for = this.byte / this.int;
    }
    
    private void a() {
        this.try[0] = new c(new dlt.a.b.c(0.0, 0.0, 0.0), new dlt.a.b.c(-this.do, -this.do, this.do), new dlt.a.b.c(-this.do, this.do, this.do));
        this.try[1] = new c(new dlt.a.b.c(0.0, 0.0, 0.0), new dlt.a.b.c(this.do, this.do, this.do), new dlt.a.b.c(this.do, -this.do, this.do));
        this.try[2] = new c(new dlt.a.b.c(0.0, 0.0, 0.0), new dlt.a.b.c(-this.do, this.do, this.do), new dlt.a.b.c(this.do, this.do, this.do));
        this.try[3] = new c(new dlt.a.b.c(0.0, 0.0, 0.0), new dlt.a.b.c(this.do, -this.do, this.do), new dlt.a.b.c(-this.do, -this.do, this.do));
    }
    
    private a a(final a a) {
        final f[] new1 = a.new();
        final h a2 = a.a();
        Vector<f> vector = new Vector<f>(new1.length);
        for (int i = 0; i < new1.length; ++i) {
            vector.addElement(new1[i]);
        }
        for (int j = 0; j < this.try.length; ++j) {
            final c c = this.try[j];
            final dlt.a.b.c a3 = c.a();
            final h if1 = c.if();
            final Vector<f> vector2 = new Vector<f>();
            for (int k = 0; k < vector.size(); ++k) {
                final f f = vector.elementAt((k + 1) % vector.size());
                final f f2 = vector.elementAt(k);
                final g g = new g(a3, f2.do());
                final g g2 = new g(a3, f.do());
                final double if2 = dlt.a.b.g.if(g, if1);
                final double if3 = dlt.a.b.g.if(g2, if1);
                if ((if2 > 0.0 && if3 <= 0.0) || (if2 <= 0.0 && if3 > 0.0)) {
                    final double n = dlt.a.b.g.if(new g(f2.do(), a3), if1) / dlt.a.b.g.if(new g(f2.do(), f.do()), if1);
                    final f f3 = new f(new dlt.a.b.c(f2.do().for() + n * (f.do().for() - f2.do().for()), f2.do().a() + n * (f.do().a() - f2.do().a()), f2.do().int() + n * (f.do().int() - f2.do().int())), a2, f2.a(a2) + n * (f.a(a2) - f2.a(a2)), new dlt.a.b.c(f2.for().for() + n * (f.for().for() - f2.for().for()), f2.for().a() + n * (f.for().a() - f2.for().a()), f2.for().int() + n * (f.for().int() - f2.for().int())));
                    if (if2 <= 0.0) {
                        vector2.addElement(f3);
                        vector2.addElement(f);
                    }
                    else {
                        vector2.addElement(f3);
                    }
                }
                else if (if2 > 0.0 && if3 > 0.0) {
                    vector2.addElement(f);
                }
            }
            vector = vector2;
        }
        if (vector.size() >= 3) {
            return new a(vector.toArray(new f[vector.size()]), a.if(), a2);
        }
        return null;
    }
    
    public void a(final dlt.a.f.c c) {
        if (c.do() == 0) {
            final double n = c.if();
            final double n2 = c.for();
            if (n > n2) {
                this.new = n / 2.0;
            }
            else {
                this.new = n2 / 2.0;
            }
        }
    }
}
