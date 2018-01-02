// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import java.util.Vector;
import dlt.a.b.h;
import dlt.a.f.c;

public abstract class d extends f
{
    private g void;
    
    public d(final a a, final l l) {
        super(a, l);
        this.for();
    }
    
    protected abstract void for();
    
    private void int() {
        if (this.void == null) {
            this.void = new g(this.null, this.else);
        }
        else {
            this.void.a(this.null, this.else);
        }
    }
    
    public void a(final c c) {
        super.a(c);
        if (c.do() == 0) {
            this.int();
        }
        else if (c.do() == 1) {
            this.void.a();
        }
    }
    
    public void for(final dlt.a.c.a a) {
        final dlt.a.c.f[] new1 = a.new();
        final double n = new1.length;
        if (n >= 3.0) {
            for (int n2 = 0; n2 < n - 2.0; ++n2) {
                final h a2 = a.a();
                this.a(new1[0].if(), new1[n2 + 1].if(), new1[n2 + 2].if(), (float)new1[0].a(a2), (float)new1[n2 + 1].a(a2), (float)new1[n2 + 2].a(a2));
            }
        }
    }
    
    private void if(final dlt.a.b.c c, final dlt.a.b.c c2, final dlt.a.b.c c3, float n, float n2, float n3) {
        try {
            double for1 = c.for();
            final double a = c.a();
            double int1 = c.int();
            double for2 = c2.for();
            final double a2 = c2.a();
            double int2 = c2.int();
            double for3 = c3.for();
            final double a3 = c3.a();
            double int3 = c3.int();
            double n8;
            double n9;
            double n10;
            double n11;
            double n12;
            double n13;
            double n14;
            double n15;
            double n16;
            double n17;
            double n18;
            double n19;
            if (a == a2) {
                if (for2 < for1) {
                    final double n4 = for2;
                    final double n5 = int2;
                    final float n6 = n2;
                    for2 = for1;
                    int2 = int1;
                    n2 = n;
                    for1 = n4;
                    int1 = n5;
                    n = n6;
                }
                final double n7 = a3 - a;
                n8 = (for3 - for1) / n7;
                n9 = (for3 - for2) / n7;
                n10 = int1;
                n11 = int2;
                n12 = n;
                n13 = n2;
                n14 = (int3 - int1) / n7;
                n15 = (int3 - int2) / n7;
                n16 = (n3 - n) / n7;
                n17 = (n3 - n2) / n7;
                n18 = for1;
                n19 = for2;
            }
            else {
                if (for3 < for2) {
                    final double n20 = for2;
                    final double n21 = int2;
                    final float n22 = n2;
                    for2 = for3;
                    int2 = int3;
                    n2 = n3;
                    for3 = n20;
                    int3 = n21;
                    n3 = n22;
                }
                final double n23 = a3 - a;
                n8 = (for2 - for1) / n23;
                n9 = (for3 - for1) / n23;
                n10 = int1;
                n11 = int1;
                n12 = n;
                n13 = n;
                n14 = (int2 - int1) / n23;
                n15 = (int3 - int1) / n23;
                n16 = (n2 - n) / n23;
                n17 = (n3 - n) / n23;
                n18 = for1;
                n19 = for1;
            }
            int n24 = ((int)a + this.else / 2 - 1) * this.null;
            final int n25 = this.null / 2;
            final int n26 = this.else / 2;
            for (int n27 = (int)a; n27 <= a3; ++n27) {
                double n28 = n10;
                double n29 = n12;
                final double n30 = 1.0 + n19 - n18;
                final double n31 = (n11 - n10) / n30;
                final double n32 = (n13 - n12) / n30;
                final int n33 = n27 + n26;
                n24 += this.null;
                if (n33 > 0 && n33 < this.else) {
                    for (int i = (int)(n18 + n25); i <= (int)(n19 + n25); ++i) {
                        if (i >= 0 && i <= this.null && this.void.a(i, n33, n28)) {
                            this.long.a(n24 + i, this.goto.a(n29));
                        }
                        n28 += n31;
                        n29 += n32;
                    }
                }
                n18 += n8;
                n19 += n9;
                n10 += n14;
                n11 += n15;
                n12 += n16;
                n13 += n17;
            }
        }
        catch (Exception ex) {}
    }
    
    private void a(dlt.a.b.c c, dlt.a.b.c c2, dlt.a.b.c c3, float n, float n2, float n3) {
        if (c2.a() < c.a()) {
            final dlt.a.b.c c4 = c2;
            final float n4 = n2;
            c2 = c;
            n2 = n;
            c = c4;
            n = n4;
        }
        if (c3.a() < c.a()) {
            final dlt.a.b.c c5 = c3;
            final float n5 = n3;
            c3 = c;
            n3 = n;
            c = c5;
            n = n5;
        }
        if (c3.a() < c2.a()) {
            final dlt.a.b.c c6 = c3;
            final float n6 = n3;
            c3 = c2;
            n3 = n2;
            c2 = c6;
            n2 = n6;
        }
        if (c.a() == c2.a() || c.a() == c3.a() || c2.a() == c3.a()) {
            this.if(c, c2, c3, n, n2, n3);
        }
        else {
            final double n7 = (c2.a() - c.a()) / (c3.a() - c.a());
            final double n8 = c.for() + (c3.for() - c.for()) * n7;
            final double n9 = c.int() + (c3.int() - c.int()) * n7;
            final float n10 = n + (n3 - n) * (float)n7;
            final dlt.a.b.c c7 = new dlt.a.b.c(n8, c2.a(), n9);
            this.if(c, c7, c2, n, n10, n2);
            this.if(c2, c7, c3, n2, n10, n3);
        }
    }
    
    protected void a(final Vector vector) {
    }
}
