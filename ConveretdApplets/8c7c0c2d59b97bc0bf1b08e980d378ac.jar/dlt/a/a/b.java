// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

import java.util.Vector;
import dlt.a.b.h;
import dlt.a.f.c;

public class b extends f
{
    private g c;
    dlt.mandelbrot.a.b d;
    
    public b(final a a, final l l) {
        super(a, l);
        this.d = new dlt.mandelbrot.a.b();
    }
    
    private void try() {
        if (this.c == null) {
            this.c = new g(this.null, this.else);
        }
        else {
            this.c.a(this.null, this.else);
        }
    }
    
    public void a(final c c) {
        super.a(c);
        if (c.do() == 0) {
            this.try();
        }
        else if (c.do() == 1) {
            this.c.a();
        }
    }
    
    public void for(final dlt.a.c.a a) {
        final dlt.a.c.f[] new1 = a.new();
        final double n = new1.length;
        if (n >= 3.0) {
            for (int n2 = 0; n2 < n - 2.0; ++n2) {
                final h a2 = a.a();
                this.a(new1[0].if(), new1[n2 + 1].if(), new1[n2 + 2].if(), (float)new1[0].a(a2), (float)new1[n2 + 1].a(a2), (float)new1[n2 + 2].a(a2), new1[0].for().a(), new1[n2 + 1].for().a(), new1[n2 + 2].for().a());
            }
        }
    }
    
    private void if(final dlt.a.b.c c, final dlt.a.b.c c2, final dlt.a.b.c c3, float n, float n2, float n3, double n4, double n5, double n6) {
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
            double n12;
            double n13;
            double n14;
            double n15;
            double n16;
            double n17;
            double n18;
            double n19;
            double n20;
            double n21;
            double n22;
            double n23;
            double n24;
            double n25;
            double n26;
            double n27;
            if (a == a2) {
                if (for2 < for1) {
                    final double n7 = for2;
                    final double n8 = int2;
                    final float n9 = n2;
                    final double n10 = n5;
                    for2 = for1;
                    int2 = int1;
                    n2 = n;
                    n5 = n4;
                    for1 = n7;
                    int1 = n8;
                    n = n9;
                    n4 = n10;
                }
                final double n11 = a3 - a;
                n12 = (for3 - for1) / n11;
                n13 = (for3 - for2) / n11;
                n14 = int1;
                n15 = int2;
                n16 = n;
                n17 = n2;
                n18 = n4;
                n19 = n5;
                n20 = (int3 - int1) / n11;
                n21 = (int3 - int2) / n11;
                n22 = (n3 - n) / n11;
                n23 = (n3 - n2) / n11;
                n24 = (n6 - n4) / n11;
                n25 = (n6 - n5) / n11;
                n26 = for1;
                n27 = for2;
            }
            else {
                if (for3 < for2) {
                    final double n28 = for2;
                    final double n29 = int2;
                    final float n30 = n2;
                    final double n31 = n5;
                    for2 = for3;
                    int2 = int3;
                    n2 = n3;
                    n5 = n6;
                    for3 = n28;
                    int3 = n29;
                    n3 = n30;
                    n6 = n31;
                }
                final double n32 = a3 - a;
                n12 = (for2 - for1) / n32;
                n13 = (for3 - for1) / n32;
                n14 = int1;
                n15 = int1;
                n16 = n;
                n17 = n;
                n18 = n4;
                n19 = n4;
                n20 = (int2 - int1) / n32;
                n21 = (int3 - int1) / n32;
                n22 = (n2 - n) / n32;
                n23 = (n3 - n) / n32;
                n24 = (n5 - n4) / n32;
                n25 = (n6 - n4) / n32;
                n26 = for1;
                n27 = for1;
            }
            int n33 = ((int)a + this.else / 2 - 1) * this.null;
            final int n34 = this.null / 2;
            final int n35 = this.else / 2;
            for (int n36 = (int)a; n36 <= a3; ++n36) {
                double n37 = n14;
                double n38 = n16;
                double n39 = n18;
                final double n40 = 1.0 + n27 - n26;
                final double n41 = (n15 - n14) / n40;
                final double n42 = (n17 - n16) / n40;
                final double n43 = (n19 - n18) / n40;
                final int n44 = n36 + n35;
                n33 += this.null;
                if (n44 > 0 && n44 < this.else) {
                    for (int i = (int)(n26 + n34); i <= (int)(n27 + n34); ++i) {
                        if (i >= 0 && i <= this.null && this.c.a(i, n44, n37)) {
                            this.long.a(n33 + i, this.goto.a(this.d.do((int)n39 + 30), n38));
                        }
                        n37 += n41;
                        n38 += n42;
                        n39 += n43;
                    }
                }
                n26 += n12;
                n27 += n13;
                n14 += n20;
                n15 += n21;
                n16 += n22;
                n17 += n23;
                n18 += n24;
                n19 += n25;
            }
        }
        catch (Exception ex) {}
    }
    
    private void a(dlt.a.b.c c, dlt.a.b.c c2, dlt.a.b.c c3, float n, float n2, float n3, double n4, double n5, double n6) {
        if (c2.a() < c.a()) {
            final dlt.a.b.c c4 = c2;
            final float n7 = n2;
            final double n8 = n5;
            c2 = c;
            n2 = n;
            n5 = n4;
            c = c4;
            n = n7;
            n4 = n8;
        }
        if (c3.a() < c.a()) {
            final dlt.a.b.c c5 = c3;
            final float n9 = n3;
            final double n10 = n6;
            c3 = c;
            n3 = n;
            n6 = n4;
            c = c5;
            n = n9;
            n4 = n10;
        }
        if (c3.a() < c2.a()) {
            final dlt.a.b.c c6 = c3;
            final float n11 = n3;
            final double n12 = n6;
            c3 = c2;
            n3 = n2;
            n6 = n5;
            c2 = c6;
            n2 = n11;
            n5 = n12;
        }
        if (c.a() == c2.a() || c.a() == c3.a() || c2.a() == c3.a()) {
            this.if(c, c2, c3, n, n2, n3, n4, n5, n6);
        }
        else {
            final double n13 = (c2.a() - c.a()) / (c3.a() - c.a());
            final double n14 = c.for() + (c3.for() - c.for()) * n13;
            final double n15 = c.int() + (c3.int() - c.int()) * n13;
            final float n16 = n + (n3 - n) * (float)n13;
            final double n17 = n4 + (n6 - n4) * n13;
            final dlt.a.b.c c7 = new dlt.a.b.c(n14, c2.a(), n15);
            this.if(c, c7, c2, n, n16, n2, n4, n17, n5);
            this.if(c2, c7, c3, n2, n16, n3, n5, n17, n6);
        }
    }
    
    protected void a(final Vector vector) {
    }
}
