// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.b;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import com.easypano.tw.dt;
import java.awt.Image;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import com.easypano.tw.dv;
import java.awt.Rectangle;
import com.easypano.tw.ci;

public final class b extends a
{
    private ci h;
    private Rectangle[] i;
    private dv[] j;
    private Container k;
    private int l;
    private int m;
    private int[] n;
    private com.easypano.tw.c.a o;
    private DirectColorModel p;
    private Image q;
    private int[] r;
    private int s;
    private int t;
    private int[] u;
    private int[] v;
    double w;
    int[] x;
    private volatile double y;
    private volatile double z;
    private volatile double A;
    private volatile double B;
    private volatile double C;
    private volatile double D;
    private volatile double E;
    private volatile double F;
    private volatile double G;
    private volatile double H;
    private volatile double I;
    private volatile double J;
    private volatile double K;
    private volatile double L;
    private volatile double M;
    private double N;
    private volatile double O;
    private volatile double P;
    private volatile double Q;
    double R;
    double S;
    double T;
    double U;
    double V;
    int W;
    double X;
    double Y;
    boolean Z;
    int[] ba;
    int[] bb;
    int[] bc;
    int[] bd;
    int be;
    
    public b() {
        this.i = new Rectangle[0];
        this.j = new dv[0];
        this.k = null;
        this.o = null;
        this.p = new DirectColorModel(32, 16711680, 65280, 255, 0);
        this.r = null;
        this.x = null;
        this.N = 0.005817764173314432;
        this.O = 0.0;
        this.P = 10.0;
        this.Q = 10.0;
        this.Z = false;
        this.ba = new int[4097];
        this.bb = new int[4097];
        this.bc = new int[4097];
        this.bd = new int[4097];
    }
    
    public void a(final ci ci, final Image image, final Container container) {
        final int[] b = dt.b(image);
        ci.m = image.getWidth(container);
        ci.n = image.getHeight(container);
        this.a(ci, b, container);
    }
    
    public void a(final ci h, final int[] n, final Container k) {
        if (this.h == null || !this.h.k.equals(h.k)) {
            this.Z = true;
        }
        this.h = h;
        this.k = k;
        this.l = h.m;
        this.m = h.n;
        this.b(this.l, this.m);
        this.n = n;
        this.w = this.l / 6.283185307179586;
        this.x = new int[this.m];
        for (int i = 0; i < this.m; ++i) {
            this.x[i] = this.l * i;
        }
        this.A = 6.283185307179586;
        this.C = 0.0;
        this.F = Math.atan(3.141592653589793 * this.m / this.l);
        this.H = -this.F;
        this.M = 0.17453292519943295;
        this.a(h.o, h.p);
        this.c(h.r, h.s);
        this.c(h.q);
        this.i(h.t);
        this.i = h.a();
        this.j = h.b();
    }
    
    public void a(final int s, final int t) {
        if (s != this.s || t != this.t) {
            this.s = s;
            this.t = t;
            this.U = this.s / 2.0;
            this.V = this.t / 2.0;
            this.W = (int)(this.s / 2.0 + 0.5);
            this.r = new int[this.s * this.t];
            this.u = new int[this.s * this.t];
            this.v = new int[this.s * this.t];
            this.o = new com.easypano.tw.c.a(this.p, this.s, this.t, this.r);
            this.q = Toolkit.getDefaultToolkit().createImage(this.o);
            this.k.prepareImage(this.q, this.k);
            this.K = Math.atan(Math.tan(this.F) * this.s / this.t) * 2.0;
        }
        this.b(this.h.u, this.h.v);
        this.f(this.h.w);
    }
    
    public Image a() {
        return this.q;
    }
    
    public void b() {
        this.k = null;
        this.o = null;
        this.q = null;
        this.r = null;
        this.u = null;
        this.v = null;
        this.c();
    }
    
    public void c() {
        this.h = null;
        this.n = null;
        this.x = null;
    }
    
    public void a(final double n) {
        this.a(n, this.B);
    }
    
    public void b(final double n) {
        this.a(this.z, n);
    }
    
    private void a(double n, double n2) {
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        while (n < 0.0) {
            n += 6.283185307179586;
        }
        this.z = ((n > this.A) ? this.A : n);
        if (this.z < this.C) {
            this.z = this.C;
        }
        while (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        while (n2 < 0.0) {
            n2 += 6.283185307179586;
        }
        this.B = ((n2 < this.C) ? this.C : n2);
        if (this.B > this.A) {
            this.B = this.A;
        }
        if (this.B > this.z) {
            final double b = this.B;
            this.B = this.z;
            this.z = b;
        }
    }
    
    public void c(double y) {
        while (y >= 6.283185307179586) {
            y -= 6.283185307179586;
        }
        while (y < 0.0) {
            y += 6.283185307179586;
        }
        if (y > this.z) {
            y = this.z;
        }
        else if (y < this.B) {
            y = this.B;
        }
        final double n = y - this.y;
        this.y = y;
        this.O += n;
    }
    
    public double d() {
        return this.y;
    }
    
    public void d(final double n) {
        this.b(n, this.L);
    }
    
    public void e(final double n) {
        this.b(this.J, n);
    }
    
    private void b(final double n, final double n2) {
        this.J = ((n > this.K) ? this.K : n);
        if (this.J < this.M) {
            this.J = this.M;
        }
        this.L = ((n2 < this.M) ? this.M : n2);
        if (this.L > this.K) {
            this.L = this.K;
        }
        if (this.J < this.L) {
            final double j = this.J;
            this.J = this.L;
            this.L = j;
        }
    }
    
    public void f(double i) {
        if (i > this.J) {
            i = this.J;
        }
        else if (i < this.L) {
            i = this.L;
        }
        this.R = i / 2.0;
        this.S = Math.atan(this.t * Math.tan(this.R) / this.s);
        if (this.S > this.F) {
            this.S = this.F;
            this.R = Math.atan(this.s * Math.tan(this.S) / this.t);
            i = this.R * 2.0;
        }
        this.I = i;
        this.T = this.U / Math.tan(this.R);
        this.i(this.D);
    }
    
    public double e() {
        return this.I;
    }
    
    public void g(final double n) {
        this.c(n, this.G);
    }
    
    public void h(final double n) {
        this.c(this.E, n);
    }
    
    private void c(final double n, final double n2) {
        this.E = ((n > this.F) ? this.F : n);
        if (this.E < this.H) {
            this.E = this.H;
        }
        this.G = ((n2 < this.H) ? this.H : n2);
        if (this.G > this.F) {
            this.G = this.F;
        }
        if (this.G > this.E) {
            final double g = this.G;
            this.G = this.E;
            this.E = g;
        }
    }
    
    public void i(double d) {
        if (d > this.E) {
            d = this.E;
        }
        else if (d < this.G) {
            d = this.G;
        }
        if (d + this.S > this.F) {
            d = this.F - this.S;
        }
        if (d - this.S < this.H) {
            d = this.H + this.S;
        }
        this.D = d;
        this.X = Math.cos(d);
        this.Y = Math.sin(d);
    }
    
    public double f() {
        return this.D;
    }
    
    public double g() {
        return this.N;
    }
    
    public void h() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            this.j[i].a(2);
        }
    }
    
    public void i() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            this.j[i].a(1);
        }
    }
    
    public void j() {
        for (int length = this.j.length, i = 0; i < length; ++i) {
            if (this.j[i].d() == 2) {
                this.j[i].a(1);
            }
            else {
                this.j[i].a(2);
            }
        }
    }
    
    public void k() {
        this.a(this.s, this.t, this.W, this.l, this.m, this.T, this.y, this.Y, this.X, 1);
        this.O = 0.0;
    }
    
    private void b(final int n, final int n2) {
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        for (int i = 0; i < 4096; ++i) {
            this.bd[i] = (int)(1.0 / Math.sqrt(1.0 + n4 * n4) * n / 3.141592653589793 / 2.0 * 4096.0);
            n4 += n3;
        }
        this.bd[4096] = (int)(1.0 / Math.sqrt(2.0) * n / 3.141592653589793 / 2.0 * 4096.0);
        this.be = (int)(n * 4096 / 6.283185307179586);
        double n5 = 0.0;
        for (int j = 0; j < 4097; ++j) {
            double atan;
            if (j < 4096) {
                atan = Math.atan(n5 / (1.0 - n5));
            }
            else {
                atan = 1.5707963267948966;
            }
            final int c = this.c((int)(atan * n * 4096.0 / 3.141592653589793 / 2.0), n);
            this.ba[j] = c;
            this.bb[j] = this.c(c + n * 4096 / 2, n);
            n5 += n3;
        }
    }
    
    private int c(int n, final int n2) {
        if (n < 0) {
            n += n2;
        }
        else if (n >= n2) {
            n -= n2;
        }
        return n;
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5, final double n6, final double n7, final double n8, final double n9, final int n10) {
        int n11 = 0;
        final int[] n12 = this.n;
        final int[] r = this.r;
        final int[] u = this.u;
        final int[] v = this.v;
        final int[] x = this.x;
        final int[] ba = this.ba;
        final int[] bb = this.bb;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        final int n13 = (int)((n << 6) / 2 + 0.5);
        final int n14 = (int)(n7 * 4096.0);
        final int n15 = (int)(n7 * n4 * 4096.0 / 6.283185307179586);
        final int n16 = n4 << 12;
        final int n17 = n4 - 1 << 10;
        final int n18 = n5 << 12;
        final int n19 = n5 - 1 << 12;
        final int n20 = n19 >> 1;
        final int n21 = (int)(n8 * 4096.0 + 0.5);
        final int n22 = (int)(n9 * 4096.0 + 0.5);
        final int n23 = (int)(n6 * n22 - n2 * n21 / 2);
        final int n24 = (int)(n6 * n21 + n2 * n22 / 2 + 0.5);
        for (int i = 0; i < n2; ++i) {
            final int n25 = n23 + i * n21 >> 6;
            final int n26 = n24 - i * n22 >> 6;
            int n27 = n25;
            if (n27 < 0) {
                n27 = -n27;
            }
            final int n28 = n27 >> 6;
            for (int j = 0; j < n3; ++j) {
                final int n29 = (j << 6) - n13;
                int n30;
                if (n25 > 0) {
                    if (n29 > 0) {
                        n30 = ba[(n29 << 12) / (n29 + n25)];
                    }
                    else {
                        n30 = -ba[(n29 << 12) / (n29 - n25)];
                    }
                }
                else if (n25 < 0) {
                    if (n29 > 0) {
                        n30 = -bb[(n29 << 12) / (n29 - n25)];
                    }
                    else {
                        n30 = bb[(n29 << 12) / (n29 + n25)];
                    }
                }
                else if (n29 > 0) {
                    n30 = n17;
                }
                else {
                    n30 = -n17;
                }
                int n31 = n30 + n15;
                if (n31 < 0) {
                    n31 += n16;
                }
                else if (n31 >= n16) {
                    n31 -= n16;
                }
                int n32 = n29 >> 6;
                if (n32 < 0) {
                    n32 = -n32;
                }
                int n33;
                if (n28 > n32) {
                    if (n28 != 0) {
                        n33 = n20 - n26 * (bd[(n32 << 12) / n28] >> 6) / n28;
                    }
                    else {
                        n33 = n20 - n26 * (this.be >> 6) / n32;
                    }
                }
                else if (n32 != 0) {
                    n33 = n20 - n26 * (bd[(n28 << 12) / n32] >> 6) / n32;
                }
                else {
                    n33 = n20 - n26 * (this.be >> 6) / n28;
                }
                final int n34 = j + n11;
                final int n35 = n - 1 - j + n11;
                int n36 = n15 + n15 - n31;
                if (n36 < 0) {
                    n36 += n16;
                }
                else if (n36 >= n16) {
                    n36 -= n16;
                }
                if (n33 < 0) {
                    n33 = 0;
                }
                else if (n33 > n19) {
                    n33 = n19;
                }
                u[n34] = n31;
                v[n34] = n33;
                u[n35] = n36;
                v[n35] = n33;
                switch (n10) {
                    case 0: {
                        r[n34] = n12[x[n33 >> 12] + (n31 >> 12)];
                        r[n35] = n12[x[n33 >> 12] + (n36 >> 12)];
                        break;
                    }
                    default: {
                        final int n37 = n31 & 0xFFFFF000;
                        int n38 = n37 + 4096;
                        if (n38 >= n16) {
                            n38 = 0;
                        }
                        final int n39 = n33 & 0xFFFFF000;
                        int n40 = n39 + 4096;
                        if (n40 >= n18) {
                            n40 = 0;
                        }
                        final int n41 = n31 - n37 >> 2;
                        final int n42 = n33 - n39 >> 2;
                        final int n43 = n41 * n42;
                        final int n44 = n37 >> 12;
                        final int n45 = n38 >> 12;
                        final int n46 = n39 >> 12;
                        final int n47 = n40 >> 12;
                        final int n48 = n12[x[n46] + n44];
                        final int n49 = n12[x[n47] + n44];
                        final int n50 = n12[x[n46] + n45];
                        final int n51 = n12[x[n47] + n45];
                        final int n52 = n48 >> 16;
                        final int n53 = n48 >> 8 & 0xFF;
                        final int n54 = n48 & 0xFF;
                        final int n55 = n49 >> 16;
                        final int n56 = n49 >> 8 & 0xFF;
                        final int n57 = n49 & 0xFF;
                        final int n58 = n50 >> 16;
                        final int n59 = n50 >> 8 & 0xFF;
                        final int n60 = n50 & 0xFF;
                        r[n34] = ((n52 << 20) + ((n58 - n52) * n41 << 10) + ((n55 - n52) * n42 << 10) + ((n51 >> 16) - n58 + n52 - n55) * n43 >> 20 << 16 | (n53 << 20) + ((n59 - n53) * n41 << 10) + ((n56 - n53) * n42 << 10) + ((n51 >> 8 & 0xFF) - n59 + n53 - n56) * n43 >> 20 << 8 | (n54 << 20) + ((n60 - n54) * n41 << 10) + ((n57 - n54) * n42 << 10) + ((n51 & 0xFF) - n60 + n54 - n57) * n43 >> 20 | 0xFF000000);
                        final int n61 = n36 & 0xFFFFF000;
                        int n62 = n61 + 4096;
                        if (n62 >= n16) {
                            n62 = 0;
                        }
                        final int n63 = n36 - n61 >> 2;
                        final int n64 = n63 * n42;
                        final int n65 = n61 >> 12;
                        final int n66 = n62 >> 12;
                        final int n67 = n12[x[n46] + n65];
                        final int n68 = n12[x[n47] + n65];
                        final int n69 = n12[x[n46] + n66];
                        final int n70 = n12[x[n47] + n66];
                        final int n71 = n67 >> 16;
                        final int n72 = n67 >> 8 & 0xFF;
                        final int n73 = n67 & 0xFF;
                        final int n74 = n68 >> 16;
                        final int n75 = n68 >> 8 & 0xFF;
                        final int n76 = n68 & 0xFF;
                        final int n77 = n69 >> 16;
                        final int n78 = n69 >> 8 & 0xFF;
                        final int n79 = n69 & 0xFF;
                        r[n35] = ((n71 << 20) + ((n77 - n71) * n63 << 10) + ((n74 - n71) * n42 << 10) + ((n70 >> 16) - n77 + n71 - n74) * n64 >> 20 << 16 | (n72 << 20) + ((n78 - n72) * n63 << 10) + ((n75 - n72) * n42 << 10) + ((n70 >> 8 & 0xFF) - n78 + n72 - n75) * n64 >> 20 << 8 | (n73 << 20) + ((n79 - n73) * n63 << 10) + ((n76 - n73) * n42 << 10) + ((n70 & 0xFF) - n79 + n73 - n76) * n64 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            n11 += n;
        }
        this.a(n4, n5, n, n2, n6, n7, this.D);
    }
    
    private void c(final int n) {
        final int[] n2 = this.n;
        final int[] r = this.r;
        final int[] u = this.u;
        final int[] v = this.v;
        final int[] x = this.x;
        final int n3 = this.l << 12;
        final int n4 = this.m << 12;
        if (Math.abs(this.O) > 1.0E-5 && r != null && n2 != null) {
            this.O %= 6.283185307179586;
            final int n5 = (int)(this.l * this.O / 6.283185307179586 * 4096.0);
            for (int length = r.length, i = 0; i < length; ++i) {
                int n6 = u[i] + n5;
                if (n6 >= n3) {
                    n6 -= n3;
                }
                else if (n6 < 0) {
                    n6 += n3;
                }
                u[i] = n6;
                switch (n) {
                    case 0: {
                        r[i] = n2[x[v[i] >> 12] + (n6 >> 12)];
                        break;
                    }
                    default: {
                        final int n7 = n6 & 0xFFFFF000;
                        int n8 = n7 + 4096;
                        if (n8 >= n3) {
                            n8 = 0;
                        }
                        final int n9 = v[i];
                        final int n10 = n9 & 0xFFFFF000;
                        int n11 = n10 + 4096;
                        if (n11 >= n4) {
                            n11 = 0;
                        }
                        final int n12 = n6 - n7 >> 2;
                        final int n13 = n9 - n10 >> 2;
                        final int n14 = n12 * n13;
                        final int n15 = n7 >> 12;
                        final int n16 = n8 >> 12;
                        final int n17 = n10 >> 12;
                        final int n18 = n11 >> 12;
                        final int n19 = n2[x[n17] + n15];
                        final int n20 = n2[x[n18] + n15];
                        final int n21 = n2[x[n17] + n16];
                        final int n22 = n2[x[n18] + n16];
                        final int n23 = n19 >> 16;
                        final int n24 = n19 >> 8 & 0xFF;
                        final int n25 = n19 & 0xFF;
                        final int n26 = n20 >> 16;
                        final int n27 = n20 >> 8 & 0xFF;
                        final int n28 = n20 & 0xFF;
                        final int n29 = n21 >> 16;
                        final int n30 = n21 >> 8 & 0xFF;
                        final int n31 = n21 & 0xFF;
                        r[i] = ((n23 << 20) + ((n29 - n23) * n12 << 10) + ((n26 - n23) * n13 << 10) + ((n22 >> 16) - n29 + n23 - n26) * n14 >> 20 << 16 | (n24 << 20) + ((n30 - n24) * n12 << 10) + ((n27 - n24) * n13 << 10) + ((n22 >> 8 & 0xFF) - n30 + n24 - n27) * n14 >> 20 << 8 | (n25 << 20) + ((n31 - n25) * n12 << 10) + ((n28 - n25) * n13 << 10) + ((n22 & 0xFF) - n31 + n25 - n28) * n14 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            this.a(this.l, this.m, this.s, this.t, this.T, this.y, this.D);
        }
    }
    
    public void b(final int n) {
        final int n2 = this.l << 12;
        final int n3 = this.m << 12;
        final int[] n4 = this.n;
        final int[] r = this.r;
        final int[] u = this.u;
        final int[] v = this.v;
        final int[] x = this.x;
        final int length = r.length;
        switch (n) {
            case 0: {
                final int n5 = 0;
                if (n5 >= length) {
                    break;
                }
                r[n5] = n4[x[v[n5] >> 12] + (u[n5] >> 12)];
                break;
            }
        }
        for (int i = 0; i < length; ++i) {
            final int n6 = u[i];
            final int n7 = n6 & 0xFFFFF000;
            int n8 = n7 + 4096;
            if (n8 >= n2) {
                n8 = 0;
            }
            final int n9 = v[i];
            final int n10 = n9 & 0xFFFFF000;
            int n11 = n10 + 4096;
            if (n11 >= n3) {
                n11 = 0;
            }
            final int n12 = n6 - n7 >> 2;
            final int n13 = n9 - n10 >> 2;
            final int n14 = n12 * n13;
            final int n15 = n7 >> 12;
            final int n16 = n8 >> 12;
            final int n17 = n10 >> 12;
            final int n18 = n11 >> 12;
            final int n19 = n4[x[n17] + n15];
            final int n20 = n4[x[n18] + n15];
            final int n21 = n4[x[n17] + n16];
            final int n22 = n4[x[n18] + n16];
            final int n23 = n19 >> 16;
            final int n24 = n19 >> 8 & 0xFF;
            final int n25 = n19 & 0xFF;
            final int n26 = n20 >> 16;
            final int n27 = n20 >> 8 & 0xFF;
            final int n28 = n20 & 0xFF;
            final int n29 = n21 >> 16;
            final int n30 = n21 >> 8 & 0xFF;
            final int n31 = n21 & 0xFF;
            r[i] = ((n23 << 20) + ((n29 - n23) * n12 << 10) + ((n26 - n23) * n13 << 10) + ((n22 >> 16) - n29 + n23 - n26) * n14 >> 20 << 16 | (n24 << 20) + ((n30 - n24) * n12 << 10) + ((n27 - n24) * n13 << 10) + ((n22 >> 8 & 0xFF) - n30 + n24 - n27) * n14 >> 20 << 8 | (n25 << 20) + ((n31 - n25) * n12 << 10) + ((n28 - n25) * n13 << 10) + ((n22 & 0xFF) - n31 + n25 - n28) * n14 >> 20 | 0xFF000000);
        }
        this.o.a();
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final double n5, double n6, double n7) {
        n6 -= 3.141592653589793;
        if (Math.abs(n7 - 1.5707963267948966) < 1.0E-5) {
            n7 = 1.570786;
        }
        else if (Math.abs(n7 + 1.5707963267948966) < 1.0E-5) {
            n7 = -1.570786;
        }
        final double cos = Math.cos(n6);
        final double sin = Math.sin(n6);
        final double cos2 = Math.cos(n7);
        final double sin2 = Math.sin(n7);
        for (int length = this.j.length, i = 0; i < length; ++i) {
            final int n8 = this.i[i].width / 2;
            final int n9 = this.i[i].height / 2;
            final double n10 = (this.i[i].x + n8 - n / 2.0) / n * 6.283185307179586;
            final double atan2 = Math.atan2(6.283185307179586 * (n2 / 2.0 - this.i[i].y - n9), n);
            final double cos3 = Math.cos(n10);
            final double sin3 = Math.sin(n10);
            final double cos4 = Math.cos(atan2);
            final double sin4 = Math.sin(atan2);
            final double n11 = cos * cos2 * cos3 * cos4 + sin * cos2 * sin3 * cos4 + sin2 * sin4;
            if (n11 > 0.0) {
                final double n12 = n5 / n11;
                this.j[i].a((int)(n12 * cos4 * Math.sin(n10 - n6) + n3 / 2.0) - n8, (int)(n4 / 2.0 - (int)((n12 * sin4 - n5 * sin2) / cos2)) - n9);
            }
            else {
                this.j[i].a(n3 + 10, n4 + 10);
            }
        }
    }
    
    public dv[] l() {
        return this.j;
    }
    
    public int a(final int n) {
        int n2 = 0;
        if (this.n != null) {
            if (this.Z) {
                this.a(this.s, this.t, this.W, this.l, this.m, this.T, this.y, this.Y, this.X, n);
                this.Q = this.I;
                this.P = this.D;
                this.O = 0.0;
                this.Z = false;
                ++n2;
            }
            else if (Math.abs(this.I - this.Q) + Math.abs(this.D - this.P) > 1.0E-6) {
                this.a(this.s, this.t, this.W, this.l, this.m, this.T, this.y, this.Y, this.X, n);
                this.Q = this.I;
                this.P = this.D;
                this.O = 0.0;
                this.Z = false;
                ++n2;
            }
            else if (Math.abs(this.O) > 1.0E-6) {
                this.c(n);
                this.O = 0.0;
                ++n2;
            }
            if (n2 > 0) {
                this.o.a();
            }
        }
        return n2;
    }
}
