// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.b;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Container;
import com.easypano.tw.dv;
import java.awt.Rectangle;
import com.easypano.tw.cj;

public final class d extends a
{
    private cj h;
    private Rectangle[] i;
    private dv[] j;
    private Container k;
    private Image l;
    private int m;
    private int n;
    double o;
    double p;
    private int[] q;
    private MemoryImageSource r;
    private Image s;
    private int[] t;
    private int u;
    private int v;
    private int[] w;
    private int[] x;
    double y;
    int[] z;
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
    private volatile double N;
    private volatile double O;
    private double P;
    private volatile double Q;
    private volatile double R;
    private volatile double S;
    double T;
    double U;
    double V;
    double W;
    double X;
    double Y;
    int Z;
    double ba;
    double bb;
    boolean bc;
    int[] bd;
    int[] be;
    int[] bf;
    int[] bg;
    
    public d() {
        this.i = new Rectangle[0];
        this.j = new dv[0];
        this.k = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.z = null;
        this.P = 0.005817764173314432;
        this.Q = 0.0;
        this.R = 10.0;
        this.S = 10.0;
        this.bc = false;
        this.bd = new int[4097];
        this.be = new int[4097];
        this.bf = new int[4097];
        this.bg = new int[4097];
    }
    
    public void a(final cj h, final Image l, final Container k) {
        if (this.h == null || !this.h.k.equals(h.k)) {
            this.bc = true;
        }
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = l.getWidth(k);
        this.n = l.getHeight(k);
        this.o = 6.283185307179586;
        this.p = this.o * this.n / this.m;
        if (this.p > 3.141592653589793) {
            this.p = 3.141592653589793;
        }
        this.b(this.m, this.n);
        this.q = new int[this.m * this.n];
        final PixelGrabber pixelGrabber = new PixelGrabber(l, 0, 0, this.m, this.n, this.q, 0, this.m);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.y = this.m / 6.283185307179586;
        this.z = new int[this.n];
        for (int i = 0; i < this.n; ++i) {
            this.z[i] = this.m * i;
        }
        this.W = this.n / 3.141592653589793;
        this.C = this.o;
        this.E = 0.0;
        this.H = this.p / 2.0;
        this.J = -this.H;
        this.M = this.p;
        this.O = 0.17453292519943295;
        this.a(h.m, h.n);
        this.c(h.p, h.q);
        this.c(h.o);
        this.i(h.r);
        this.i = h.a();
        this.j = h.b();
    }
    
    public void a(final int u, final int v) {
        if (u != this.u || v != this.v) {
            this.u = u;
            this.v = v;
            this.X = this.u / 2.0;
            this.Y = this.v / 2.0;
            this.Z = (int)(this.u / 2.0 + 0.5);
            this.V = this.X / Math.tan(this.T);
            this.ba = Math.cos(this.F);
            this.bb = Math.sin(this.F);
            this.w = new int[this.u * this.v];
            this.x = new int[this.u * this.v];
            this.t = new int[this.u * this.v];
            (this.r = new MemoryImageSource(this.u, this.v, this.t, 0, this.u)).setFullBufferUpdates(true);
            this.r.setAnimated(true);
            this.s = Toolkit.getDefaultToolkit().createImage(this.r);
            this.M = Math.atan(Math.tan(this.H) * this.u / this.v) * 2.0;
        }
        this.b(this.h.s, this.h.t);
        this.f(this.h.u);
    }
    
    public Image a() {
        return this.s;
    }
    
    public void b() {
        this.k = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.w = null;
        this.x = null;
        this.c();
    }
    
    public void c() {
        this.h = null;
        this.l = null;
        this.q = null;
        this.z = null;
    }
    
    public void a(final double n) {
        this.a(n, this.D);
    }
    
    public void b(final double n) {
        this.a(this.B, n);
    }
    
    private void a(double n, double n2) {
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        while (n < 0.0) {
            n += 6.283185307179586;
        }
        this.B = ((n > this.C) ? this.C : n);
        if (this.B < this.E) {
            this.B = this.E;
        }
        while (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        while (n2 < 0.0) {
            n2 += 6.283185307179586;
        }
        this.D = ((n2 < this.E) ? this.E : n2);
        if (this.D > this.C) {
            this.D = this.C;
        }
        if (this.D > this.B) {
            final double d = this.D;
            this.D = this.B;
            this.B = d;
        }
    }
    
    public void c(double a) {
        while (a >= 6.283185307179586) {
            a -= 6.283185307179586;
        }
        while (a < 0.0) {
            a += 6.283185307179586;
        }
        if (a > this.B) {
            a = this.B;
        }
        else if (a < this.D) {
            a = this.D;
        }
        final double n = a - this.A;
        this.A = a;
        this.Q += n;
    }
    
    public double d() {
        return this.A;
    }
    
    public void d(final double n) {
        this.b(n, this.N);
    }
    
    public void e(final double n) {
        this.b(this.L, n);
    }
    
    private void b(final double n, final double n2) {
        this.L = ((n > this.M) ? this.M : n);
        if (this.L < this.O) {
            this.L = this.O;
        }
        this.N = ((n2 < this.O) ? this.O : n2);
        if (this.N > this.M) {
            this.N = this.M;
        }
        if (this.L < this.N) {
            final double l = this.L;
            this.L = this.N;
            this.N = l;
        }
    }
    
    public void f(double k) {
        if (k > this.L) {
            k = this.L;
        }
        else if (k < this.N) {
            k = this.N;
        }
        this.T = k / 2.0;
        if (Math.abs(this.p - 3.141592653589793) > 1.0E-6) {
            this.U = Math.atan(this.v * Math.tan(this.T) / this.u);
            if (this.U > this.H) {
                this.U = this.H;
                this.T = Math.atan(this.u * Math.tan(this.U) / this.v);
                k = this.T * 2.0;
            }
            this.i(this.F);
        }
        this.K = k;
        this.V = this.X / Math.tan(this.T);
    }
    
    public double e() {
        return this.K;
    }
    
    public void g(final double n) {
        this.c(n, this.I);
    }
    
    public void h(final double n) {
        this.c(this.G, n);
    }
    
    private void c(final double n, final double n2) {
        this.G = ((n > this.H) ? this.H : n);
        if (this.G < this.J) {
            this.G = this.J;
        }
        this.I = ((n2 < this.J) ? this.J : n2);
        if (this.I > this.H) {
            this.I = this.H;
        }
        if (this.I > this.G) {
            final double i = this.I;
            this.I = this.G;
            this.G = i;
        }
    }
    
    public void i(double f) {
        if (f > this.G) {
            f = this.G;
        }
        else if (f < this.I) {
            f = this.I;
        }
        if (Math.abs(this.p - 3.141592653589793) > 1.0E-6) {
            if (f + this.U > this.H) {
                f = this.H - this.U;
            }
            if (f - this.U < this.J) {
                f = this.J + this.U;
            }
        }
        this.F = f;
        this.ba = Math.cos(f);
        this.bb = Math.sin(f);
    }
    
    public double f() {
        return this.F;
    }
    
    public double g() {
        return this.P;
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
        this.a(this.u, this.v, this.Z, this.m, this.n, this.V, this.A, this.bb, this.ba, 1);
        this.Q = 0.0;
    }
    
    private void b(final int n, final int n2) {
        final double n3 = 2.44140625E-4;
        double n4 = 0.0;
        final double n5 = this.p / 2.0;
        final int n6 = n - 1;
        final int n7 = n2 - 1;
        for (int i = 0; i < 4096; ++i) {
            this.bg[i] = (int)(Math.sqrt(1.0 + n4 * n4) * 4096.0 + 0.5);
            n4 += n3;
        }
        this.bg[4096] = (int)(Math.sqrt(2.0) * 4096.0);
        double n8 = 0.0;
        for (int j = 0; j < 4097; ++j) {
            double atan;
            if (j < 4096) {
                atan = Math.atan(n8 / (1.0 - n8));
            }
            else {
                atan = 1.5707963267948966;
            }
            final int c = this.c((int)(atan * n6 * 4096.0 / this.o), n);
            this.bd[j] = c;
            this.be[j] = this.c(c + n6 * 4096 / 2, n);
            this.bf[j] = (int)((n5 - atan) * n7 * 4096.0 / this.p);
            n8 += n3;
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
        final int n12 = (int)((n << 6) / 2 + 0.5);
        final int n13 = (int)(n7 * 4096.0);
        final int n14 = (int)(n7 * (n4 - 1) * 4096.0 / 6.283185307179586);
        final int n15 = n4 << 12;
        final int n16 = n4 - 1 << 10;
        final int n17 = n5 << 12;
        final int n18 = n5 - 1 << 12;
        final int n19 = (int)(n8 * 4096.0 + 0.5);
        final int n20 = (int)(n9 * 4096.0 + 0.5);
        final int n21 = (int)(n6 * n20 - n2 * n19 / 2 + 0.5);
        final int n22 = (int)(n6 * n19 + n2 * n20 / 2 + 0.5);
        for (int i = 0; i < n2; ++i) {
            final int n23 = n21 + i * n19 >> 6;
            final int n24 = n22 - i * n20 >> 6;
            int n25 = n23;
            if (n25 < 0) {
                n25 = -n25;
            }
            final int n26 = n25 >> 6;
            for (int j = 0; j < n3; ++j) {
                int n27 = (j << 6) - n12;
                int n28;
                if (n23 > 0) {
                    if (n27 > 0) {
                        n28 = this.bd[(n27 << 12) / (n27 + n23)];
                    }
                    else {
                        n28 = -this.bd[(n27 << 12) / (n27 - n23)];
                    }
                }
                else if (n23 < 0) {
                    if (n27 > 0) {
                        n28 = -this.be[(n27 << 12) / (n27 - n23)];
                    }
                    else {
                        n28 = this.be[(n27 << 12) / (n27 + n23)];
                    }
                }
                else if (n27 > 0) {
                    n28 = n16;
                }
                else {
                    n28 = -n16;
                }
                int n29 = n28 + n14;
                if (n29 < 0) {
                    n29 += n15;
                }
                else if (n29 >= n15) {
                    n29 -= n15;
                }
                if (n27 < 0) {
                    n27 = -n27;
                }
                final int n30 = n27 >> 6;
                int n31;
                if (n30 > n26) {
                    if (n30 != 0) {
                        n31 = n30 * this.bg[(n26 << 12) / n30];
                    }
                    else {
                        n31 = n26;
                    }
                }
                else if (n26 != 0) {
                    n31 = n26 * this.bg[(n30 << 12) / n26];
                }
                else {
                    n31 = n30;
                }
                final int n32 = n31 >> 6;
                int n33;
                if (n32 != 0) {
                    if (n24 > 0) {
                        n33 = this.bf[(n24 << 12) / (n32 + n24)];
                        if (n33 < 0) {
                            n33 = 0;
                        }
                    }
                    else {
                        n33 = n18 - this.bf[(n24 << 12) / (n24 - n32)];
                        if (n33 > n18) {
                            n33 = n18;
                        }
                    }
                }
                else if (n24 > 0) {
                    n33 = 0;
                }
                else {
                    n33 = n18;
                }
                final int n34 = j + n11;
                final int n35 = n - 1 - j + n11;
                int n36 = n14 + n14 - n29;
                if (n36 < 0) {
                    n36 += n15;
                }
                else if (n36 >= n15) {
                    n36 -= n15;
                }
                this.w[n34] = n29;
                this.x[n34] = n33;
                this.w[n35] = n36;
                this.x[n35] = n33;
                switch (n10) {
                    case 0: {
                        this.t[n34] = this.q[this.z[n33 >> 12] + (n29 >> 12)];
                        this.t[n35] = this.q[this.z[n33 >> 12] + (n36 >> 12)];
                        break;
                    }
                    default: {
                        final int n37 = n29 & 0xFFFFF000;
                        int n38 = n37 + 4096;
                        if (n38 >= n15) {
                            n38 = 0;
                        }
                        final int n39 = n33 & 0xFFFFF000;
                        int n40 = n39 + 4096;
                        if (n40 >= n17) {
                            n40 = 0;
                        }
                        final int n41 = n29 - n37 >> 2;
                        final int n42 = n33 - n39 >> 2;
                        final int n43 = n41 * n42;
                        final int n44 = n37 >> 12;
                        final int n45 = n38 >> 12;
                        final int n46 = n39 >> 12;
                        final int n47 = n40 >> 12;
                        final int n48 = this.q[this.z[n46] + n44];
                        final int n49 = this.q[this.z[n47] + n44];
                        final int n50 = this.q[this.z[n46] + n45];
                        final int n51 = this.q[this.z[n47] + n45];
                        final int n52 = n48 >> 16;
                        final int n53 = n48 >> 8 & 0xFF;
                        final int n54 = n48 & 0xFF;
                        final int n55 = n49 >> 16;
                        final int n56 = n49 >> 8 & 0xFF;
                        final int n57 = n49 & 0xFF;
                        final int n58 = n50 >> 16;
                        final int n59 = n50 >> 8 & 0xFF;
                        final int n60 = n50 & 0xFF;
                        this.t[n34] = ((n52 << 20) + ((n58 - n52) * n41 << 10) + ((n55 - n52) * n42 << 10) + ((n51 >> 16) - n58 + n52 - n55) * n43 >> 20 << 16 | (n53 << 20) + ((n59 - n53) * n41 << 10) + ((n56 - n53) * n42 << 10) + ((n51 >> 8 & 0xFF) - n59 + n53 - n56) * n43 >> 20 << 8 | (n54 << 20) + ((n60 - n54) * n41 << 10) + ((n57 - n54) * n42 << 10) + ((n51 & 0xFF) - n60 + n54 - n57) * n43 >> 20 | 0xFF000000);
                        final int n61 = n36 & 0xFFFFF000;
                        int n62 = n61 + 4096;
                        if (n62 >= n15) {
                            n62 = 0;
                        }
                        final int n63 = n36 - n61 >> 2;
                        final int n64 = n63 * n42;
                        final int n65 = n61 >> 12;
                        final int n66 = n62 >> 12;
                        final int n67 = this.q[this.z[n46] + n65];
                        final int n68 = this.q[this.z[n47] + n65];
                        final int n69 = this.q[this.z[n46] + n66];
                        final int n70 = this.q[this.z[n47] + n66];
                        final int n71 = n67 >> 16;
                        final int n72 = n67 >> 8 & 0xFF;
                        final int n73 = n67 & 0xFF;
                        final int n74 = n68 >> 16;
                        final int n75 = n68 >> 8 & 0xFF;
                        final int n76 = n68 & 0xFF;
                        final int n77 = n69 >> 16;
                        final int n78 = n69 >> 8 & 0xFF;
                        final int n79 = n69 & 0xFF;
                        this.t[n35] = ((n71 << 20) + ((n77 - n71) * n63 << 10) + ((n74 - n71) * n42 << 10) + ((n70 >> 16) - n77 + n71 - n74) * n64 >> 20 << 16 | (n72 << 20) + ((n78 - n72) * n63 << 10) + ((n75 - n72) * n42 << 10) + ((n70 >> 8 & 0xFF) - n78 + n72 - n75) * n64 >> 20 << 8 | (n73 << 20) + ((n79 - n73) * n63 << 10) + ((n76 - n73) * n42 << 10) + ((n70 & 0xFF) - n79 + n73 - n76) * n64 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            n11 += n;
        }
        this.a(n4, n5, n, n2, n6, n7, this.F);
    }
    
    public void b(final int n) {
        final int n2 = this.m << 12;
        final int n3 = this.n << 12;
        final int length = this.t.length;
        switch (n) {
            case 0: {
                for (int i = 0; i < length; ++i) {
                    this.t[i] = this.q[this.z[this.x[i] >> 12] + (this.w[i] >> 12)];
                }
                break;
            }
            default: {
                for (int j = 0; j < length; ++j) {
                    final int n4 = this.w[j] & 0xFFFFF000;
                    int n5 = n4 + 4096;
                    if (n5 >= n2) {
                        n5 = 0;
                    }
                    final int n6 = this.w[j];
                    final int n7 = this.x[j];
                    final int n8 = n7 & 0xFFFFF000;
                    int n9 = n8 + 4096;
                    if (n9 >= n3) {
                        n9 = 0;
                    }
                    final int n10 = n6 - n4 >> 2;
                    final int n11 = n7 - n8 >> 2;
                    final int n12 = n10 * n11;
                    final int n13 = n4 >> 12;
                    final int n14 = n5 >> 12;
                    final int n15 = n8 >> 12;
                    final int n16 = n9 >> 12;
                    final int n17 = this.q[this.z[n15] + n13];
                    final int n18 = this.q[this.z[n16] + n13];
                    final int n19 = this.q[this.z[n15] + n14];
                    final int n20 = this.q[this.z[n16] + n14];
                    final int n21 = n17 >> 16;
                    final int n22 = n17 >> 8 & 0xFF;
                    final int n23 = n17 & 0xFF;
                    final int n24 = n18 >> 16;
                    final int n25 = n18 >> 8 & 0xFF;
                    final int n26 = n18 & 0xFF;
                    final int n27 = n19 >> 16;
                    final int n28 = n19 >> 8 & 0xFF;
                    final int n29 = n19 & 0xFF;
                    this.t[j] = ((n21 << 20) + ((n27 - n21) * n10 << 10) + ((n24 - n21) * n11 << 10) + ((n20 >> 16) - n27 + n21 - n24) * n12 >> 20 << 16 | (n22 << 20) + ((n28 - n22) * n10 << 10) + ((n25 - n22) * n11 << 10) + ((n20 >> 8 & 0xFF) - n28 + n22 - n25) * n12 >> 20 << 8 | (n23 << 20) + ((n29 - n23) * n10 << 10) + ((n26 - n23) * n11 << 10) + ((n20 & 0xFF) - n29 + n23 - n26) * n12 >> 20 | 0xFF000000);
                }
                break;
            }
        }
        this.r.newPixels();
    }
    
    private void c(final int n) {
        final int n2 = this.m << 12;
        final int n3 = this.n << 12;
        if (Math.abs(this.Q) > 1.0E-5 && this.t != null && this.q != null) {
            this.Q %= 6.283185307179586;
            final int n4 = (int)(this.m * this.Q / 6.283185307179586 * 4096.0);
            for (int length = this.t.length, i = 0; i < length; ++i) {
                int n5 = this.w[i] + n4;
                if (n5 >= n2) {
                    n5 -= n2;
                }
                else if (n5 < 0) {
                    n5 += n2;
                }
                this.w[i] = n5;
                switch (n) {
                    case 0: {
                        this.t[i] = this.q[this.z[this.x[i] >> 12] + (n5 >> 12)];
                        break;
                    }
                    default: {
                        final int n6 = n5 & 0xFFFFF000;
                        int n7 = n6 + 4096;
                        if (n7 >= n2) {
                            n7 = 0;
                        }
                        final int n8 = this.x[i];
                        final int n9 = n8 & 0xFFFFF000;
                        int n10 = n9 + 4096;
                        if (n10 >= n3) {
                            n10 = 0;
                        }
                        final int n11 = n5 - n6 >> 2;
                        final int n12 = n8 - n9 >> 2;
                        final int n13 = n11 * n12;
                        final int n14 = n6 >> 12;
                        final int n15 = n7 >> 12;
                        final int n16 = n9 >> 12;
                        final int n17 = n10 >> 12;
                        final int n18 = this.q[this.z[n16] + n14];
                        final int n19 = this.q[this.z[n17] + n14];
                        final int n20 = this.q[this.z[n16] + n15];
                        final int n21 = this.q[this.z[n17] + n15];
                        final int n22 = n18 >> 16;
                        final int n23 = n18 >> 8 & 0xFF;
                        final int n24 = n18 & 0xFF;
                        final int n25 = n19 >> 16;
                        final int n26 = n19 >> 8 & 0xFF;
                        final int n27 = n19 & 0xFF;
                        final int n28 = n20 >> 16;
                        final int n29 = n20 >> 8 & 0xFF;
                        final int n30 = n20 & 0xFF;
                        this.t[i] = ((n22 << 20) + ((n28 - n22) * n11 << 10) + ((n25 - n22) * n12 << 10) + ((n21 >> 16) - n28 + n22 - n25) * n13 >> 20 << 16 | (n23 << 20) + ((n29 - n23) * n11 << 10) + ((n26 - n23) * n12 << 10) + ((n21 >> 8 & 0xFF) - n29 + n23 - n26) * n13 >> 20 << 8 | (n24 << 20) + ((n30 - n24) * n11 << 10) + ((n27 - n24) * n12 << 10) + ((n21 & 0xFF) - n30 + n24 - n27) * n13 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            this.a(this.m, this.n, this.u, this.v, this.V, this.A, this.F);
        }
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
            final double n10 = (this.i[i].x + n8 - n / 2.0) / n * this.o;
            final double n11 = (n2 / 2.0 - this.i[i].y - n9) / n2 * this.p;
            final double cos3 = Math.cos(n10);
            final double sin3 = Math.sin(n10);
            final double cos4 = Math.cos(n11);
            final double sin4 = Math.sin(n11);
            final double n12 = cos * cos2 * cos3 * cos4 + sin * cos2 * sin3 * cos4 + sin2 * sin4;
            if (n12 > 0.0) {
                final double n13 = n5 / n12;
                this.j[i].a((int)(n13 * cos4 * Math.sin(n10 - n6) + n3 / 2.0) - n8, (int)(n4 / 2.0 - (int)((n13 * sin4 - n5 * sin2) / cos2)) - n9);
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
        if (this.q != null && this.t != null) {
            if (this.bc) {
                this.a(this.u, this.v, this.Z, this.m, this.n, this.V, this.A, this.bb, this.ba, n);
                this.S = this.K;
                this.R = this.F;
                this.Q = 0.0;
                this.bc = false;
                ++n2;
            }
            else if (Math.abs(this.K - this.S) + Math.abs(this.F - this.R) > 1.0E-6) {
                this.a(this.u, this.v, this.Z, this.m, this.n, this.V, this.A, this.bb, this.ba, n);
                this.S = this.K;
                this.R = this.F;
                this.Q = 0.0;
                this.bc = false;
                ++n2;
            }
            else if (Math.abs(this.Q) > 1.0E-6) {
                this.c(n);
                this.Q = 0.0;
                ++n2;
            }
            if (n2 > 0) {
                this.r.newPixels();
            }
        }
        return n2;
    }
}
