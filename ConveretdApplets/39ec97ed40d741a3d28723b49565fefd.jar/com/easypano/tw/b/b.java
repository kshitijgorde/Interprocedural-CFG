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
import com.easypano.tw.du;
import java.awt.Rectangle;
import com.easypano.tw.ci;

public final class b extends a
{
    private ci h;
    private Rectangle[] i;
    private du[] j;
    private Container k;
    private Image l;
    private int m;
    private int n;
    private int[] o;
    private MemoryImageSource p;
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
        this.j = new du[0];
        this.k = null;
        this.p = null;
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
    
    public void a(final ci h, final Image l, final Container k) {
        if (this.h == null || !this.h.k.equals(h.k)) {
            this.Z = true;
        }
        this.h = h;
        this.k = k;
        this.l = l;
        this.m = l.getWidth(k);
        this.n = l.getHeight(k);
        this.b(this.m, this.n);
        this.o = new int[this.m * this.n];
        final PixelGrabber pixelGrabber = new PixelGrabber(l, 0, 0, this.m, this.n, this.o, 0, this.m);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.w = this.m / 6.283185307179586;
        this.x = new int[this.n];
        for (int i = 0; i < this.n; ++i) {
            this.x[i] = this.m * i;
        }
        this.A = 6.283185307179586;
        this.C = 0.0;
        this.F = Math.atan(3.141592653589793 * this.n / this.m);
        this.H = -this.F;
        this.M = 0.17453292519943295;
        this.a(h.m, h.n);
        this.c(h.p, h.q);
        this.c(h.o);
        this.i(h.r);
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
            (this.p = new MemoryImageSource(this.s, this.t, this.r, 0, this.s)).setFullBufferUpdates(true);
            this.p.setAnimated(true);
            this.q = Toolkit.getDefaultToolkit().createImage(this.p);
            this.K = Math.atan(Math.tan(this.F) * this.s / this.t) * 2.0;
        }
        this.b(this.h.s, this.h.t);
        this.f(this.h.u);
    }
    
    public Image a() {
        return this.q;
    }
    
    public void b() {
        this.k = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.u = null;
        this.v = null;
        this.c();
    }
    
    public void c() {
        this.h = null;
        this.l = null;
        this.o = null;
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
        this.a(this.s, this.t, this.W, this.m, this.n, this.T, this.y, this.Y, this.X, 1);
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
        final int n12 = (int)((n << 6) / 2 + 0.5);
        final int n13 = (int)(n7 * 4096.0);
        final int n14 = (int)(n7 * n4 * 4096.0 / 6.283185307179586);
        final int n15 = n4 << 12;
        final int n16 = n4 - 1 << 10;
        final int n17 = n5 << 12;
        final int n18 = n5 - 1 << 12;
        final int n19 = n18 >> 1;
        final int n20 = (int)(n8 * 4096.0 + 0.5);
        final int n21 = (int)(n9 * 4096.0 + 0.5);
        final int n22 = (int)(n6 * n21 - n2 * n20 / 2);
        final int n23 = (int)(n6 * n20 + n2 * n21 / 2 + 0.5);
        for (int i = 0; i < n2; ++i) {
            final int n24 = n22 + i * n20 >> 6;
            final int n25 = n23 - i * n21 >> 6;
            int n26 = n24;
            if (n26 < 0) {
                n26 = -n26;
            }
            final int n27 = n26 >> 6;
            for (int j = 0; j < n3; ++j) {
                final int n28 = (j << 6) - n12;
                int n29;
                if (n24 > 0) {
                    if (n28 > 0) {
                        n29 = this.ba[(n28 << 12) / (n28 + n24)];
                    }
                    else {
                        n29 = -this.ba[(n28 << 12) / (n28 - n24)];
                    }
                }
                else if (n24 < 0) {
                    if (n28 > 0) {
                        n29 = -this.bb[(n28 << 12) / (n28 - n24)];
                    }
                    else {
                        n29 = this.bb[(n28 << 12) / (n28 + n24)];
                    }
                }
                else if (n28 > 0) {
                    n29 = n16;
                }
                else {
                    n29 = -n16;
                }
                int n30 = n29 + n14;
                if (n30 < 0) {
                    n30 += n15;
                }
                else if (n30 >= n15) {
                    n30 -= n15;
                }
                int n31 = n28 >> 6;
                if (n31 < 0) {
                    n31 = -n31;
                }
                int n32;
                if (n27 > n31) {
                    if (n27 != 0) {
                        n32 = n19 - n25 * (this.bd[(n31 << 12) / n27] >> 6) / n27;
                    }
                    else {
                        n32 = n19 - n25 * (this.be >> 6) / n31;
                    }
                }
                else if (n31 != 0) {
                    n32 = n19 - n25 * (this.bd[(n27 << 12) / n31] >> 6) / n31;
                }
                else {
                    n32 = n19 - n25 * (this.be >> 6) / n27;
                }
                final int n33 = j + n11;
                final int n34 = n - 1 - j + n11;
                int n35 = n14 + n14 - n30;
                if (n35 < 0) {
                    n35 += n15;
                }
                else if (n35 >= n15) {
                    n35 -= n15;
                }
                if (n32 < 0) {
                    n32 = 0;
                }
                else if (n32 > n18) {
                    n32 = n18;
                }
                this.u[n33] = n30;
                this.v[n33] = n32;
                this.u[n34] = n35;
                this.v[n34] = n32;
                switch (n10) {
                    case 0: {
                        this.r[n33] = this.o[this.x[n32 >> 12] + (n30 >> 12)];
                        this.r[n34] = this.o[this.x[n32 >> 12] + (n35 >> 12)];
                        break;
                    }
                    default: {
                        final int n36 = n30 & 0xFFFFF000;
                        int n37 = n36 + 4096;
                        if (n37 >= n15) {
                            n37 = 0;
                        }
                        final int n38 = n32 & 0xFFFFF000;
                        int n39 = n38 + 4096;
                        if (n39 >= n17) {
                            n39 = 0;
                        }
                        final int n40 = n30 - n36 >> 2;
                        final int n41 = n32 - n38 >> 2;
                        final int n42 = n40 * n41;
                        final int n43 = n36 >> 12;
                        final int n44 = n37 >> 12;
                        final int n45 = n38 >> 12;
                        final int n46 = n39 >> 12;
                        final int n47 = this.o[this.x[n45] + n43];
                        final int n48 = this.o[this.x[n46] + n43];
                        final int n49 = this.o[this.x[n45] + n44];
                        final int n50 = this.o[this.x[n46] + n44];
                        final int n51 = n47 >> 16;
                        final int n52 = n47 >> 8 & 0xFF;
                        final int n53 = n47 & 0xFF;
                        final int n54 = n48 >> 16;
                        final int n55 = n48 >> 8 & 0xFF;
                        final int n56 = n48 & 0xFF;
                        final int n57 = n49 >> 16;
                        final int n58 = n49 >> 8 & 0xFF;
                        final int n59 = n49 & 0xFF;
                        this.r[n33] = ((n51 << 20) + ((n57 - n51) * n40 << 10) + ((n54 - n51) * n41 << 10) + ((n50 >> 16) - n57 + n51 - n54) * n42 >> 20 << 16 | (n52 << 20) + ((n58 - n52) * n40 << 10) + ((n55 - n52) * n41 << 10) + ((n50 >> 8 & 0xFF) - n58 + n52 - n55) * n42 >> 20 << 8 | (n53 << 20) + ((n59 - n53) * n40 << 10) + ((n56 - n53) * n41 << 10) + ((n50 & 0xFF) - n59 + n53 - n56) * n42 >> 20 | 0xFF000000);
                        final int n60 = n35 & 0xFFFFF000;
                        int n61 = n60 + 4096;
                        if (n61 >= n15) {
                            n61 = 0;
                        }
                        final int n62 = n35 - n60 >> 2;
                        final int n63 = n62 * n41;
                        final int n64 = n60 >> 12;
                        final int n65 = n61 >> 12;
                        final int n66 = this.o[this.x[n45] + n64];
                        final int n67 = this.o[this.x[n46] + n64];
                        final int n68 = this.o[this.x[n45] + n65];
                        final int n69 = this.o[this.x[n46] + n65];
                        final int n70 = n66 >> 16;
                        final int n71 = n66 >> 8 & 0xFF;
                        final int n72 = n66 & 0xFF;
                        final int n73 = n67 >> 16;
                        final int n74 = n67 >> 8 & 0xFF;
                        final int n75 = n67 & 0xFF;
                        final int n76 = n68 >> 16;
                        final int n77 = n68 >> 8 & 0xFF;
                        final int n78 = n68 & 0xFF;
                        this.r[n34] = ((n70 << 20) + ((n76 - n70) * n62 << 10) + ((n73 - n70) * n41 << 10) + ((n69 >> 16) - n76 + n70 - n73) * n63 >> 20 << 16 | (n71 << 20) + ((n77 - n71) * n62 << 10) + ((n74 - n71) * n41 << 10) + ((n69 >> 8 & 0xFF) - n77 + n71 - n74) * n63 >> 20 << 8 | (n72 << 20) + ((n78 - n72) * n62 << 10) + ((n75 - n72) * n41 << 10) + ((n69 & 0xFF) - n78 + n72 - n75) * n63 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            n11 += n;
        }
        this.a(n4, n5, n, n2, n6, n7, this.D);
    }
    
    private void c(final int n) {
        final int n2 = this.m << 12;
        final int n3 = this.n << 12;
        if (Math.abs(this.O) > 1.0E-5 && this.r != null && this.o != null) {
            this.O %= 6.283185307179586;
            final int n4 = (int)(this.m * this.O / 6.283185307179586 * 4096.0);
            for (int length = this.r.length, i = 0; i < length; ++i) {
                int n5 = this.u[i] + n4;
                if (n5 >= n2) {
                    n5 -= n2;
                }
                else if (n5 < 0) {
                    n5 += n2;
                }
                this.u[i] = n5;
                switch (n) {
                    case 0: {
                        this.r[i] = this.o[this.x[this.v[i] >> 12] + (n5 >> 12)];
                        break;
                    }
                    default: {
                        final int n6 = n5 & 0xFFFFF000;
                        int n7 = n6 + 4096;
                        if (n7 >= n2) {
                            n7 = 0;
                        }
                        final int n8 = this.v[i];
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
                        final int n18 = this.o[this.x[n16] + n14];
                        final int n19 = this.o[this.x[n17] + n14];
                        final int n20 = this.o[this.x[n16] + n15];
                        final int n21 = this.o[this.x[n17] + n15];
                        final int n22 = n18 >> 16;
                        final int n23 = n18 >> 8 & 0xFF;
                        final int n24 = n18 & 0xFF;
                        final int n25 = n19 >> 16;
                        final int n26 = n19 >> 8 & 0xFF;
                        final int n27 = n19 & 0xFF;
                        final int n28 = n20 >> 16;
                        final int n29 = n20 >> 8 & 0xFF;
                        final int n30 = n20 & 0xFF;
                        this.r[i] = ((n22 << 20) + ((n28 - n22) * n11 << 10) + ((n25 - n22) * n12 << 10) + ((n21 >> 16) - n28 + n22 - n25) * n13 >> 20 << 16 | (n23 << 20) + ((n29 - n23) * n11 << 10) + ((n26 - n23) * n12 << 10) + ((n21 >> 8 & 0xFF) - n29 + n23 - n26) * n13 >> 20 << 8 | (n24 << 20) + ((n30 - n24) * n11 << 10) + ((n27 - n24) * n12 << 10) + ((n21 & 0xFF) - n30 + n24 - n27) * n13 >> 20 | 0xFF000000);
                        break;
                    }
                }
            }
            this.a(this.m, this.n, this.s, this.t, this.T, this.y, this.D);
        }
    }
    
    public void b(final int n) {
        final int n2 = this.m << 12;
        final int n3 = this.n << 12;
        final int length = this.r.length;
        switch (n) {
            case 0: {
                final int n4 = 0;
                if (n4 >= length) {
                    break;
                }
                this.r[n4] = this.o[this.x[this.v[n4] >> 12] + (this.u[n4] >> 12)];
                break;
            }
        }
        for (int i = 0; i < length; ++i) {
            final int n5 = this.u[i] & 0xFFFFF000;
            int n6 = n5 + 4096;
            if (n6 >= n2) {
                n6 = 0;
            }
            final int n7 = this.u[i];
            final int n8 = this.v[i];
            final int n9 = n8 & 0xFFFFF000;
            int n10 = n9 + 4096;
            if (n10 >= n3) {
                n10 = 0;
            }
            final int n11 = n7 - n5 >> 2;
            final int n12 = n8 - n9 >> 2;
            final int n13 = n11 * n12;
            final int n14 = n5 >> 12;
            final int n15 = n6 >> 12;
            final int n16 = n9 >> 12;
            final int n17 = n10 >> 12;
            final int n18 = this.o[this.x[n16] + n14];
            final int n19 = this.o[this.x[n17] + n14];
            final int n20 = this.o[this.x[n16] + n15];
            final int n21 = this.o[this.x[n17] + n15];
            final int n22 = n18 >> 16;
            final int n23 = n18 >> 8 & 0xFF;
            final int n24 = n18 & 0xFF;
            final int n25 = n19 >> 16;
            final int n26 = n19 >> 8 & 0xFF;
            final int n27 = n19 & 0xFF;
            final int n28 = n20 >> 16;
            final int n29 = n20 >> 8 & 0xFF;
            final int n30 = n20 & 0xFF;
            this.r[i] = ((n22 << 20) + ((n28 - n22) * n11 << 10) + ((n25 - n22) * n12 << 10) + ((n21 >> 16) - n28 + n22 - n25) * n13 >> 20 << 16 | (n23 << 20) + ((n29 - n23) * n11 << 10) + ((n26 - n23) * n12 << 10) + ((n21 >> 8 & 0xFF) - n29 + n23 - n26) * n13 >> 20 << 8 | (n24 << 20) + ((n30 - n24) * n11 << 10) + ((n27 - n24) * n12 << 10) + ((n21 & 0xFF) - n30 + n24 - n27) * n13 >> 20 | 0xFF000000);
        }
        this.p.newPixels();
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
    
    public du[] l() {
        return this.j;
    }
    
    public int a(final int n) {
        int n2 = 0;
        if (this.o != null) {
            if (this.Z) {
                this.a(this.s, this.t, this.W, this.m, this.n, this.T, this.y, this.Y, this.X, n);
                this.Q = this.I;
                this.P = this.D;
                this.O = 0.0;
                this.Z = false;
                ++n2;
            }
            else if (Math.abs(this.I - this.Q) + Math.abs(this.D - this.P) > 1.0E-6) {
                this.a(this.s, this.t, this.W, this.m, this.n, this.T, this.y, this.Y, this.X, n);
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
                this.p.newPixels();
            }
        }
        return n2;
    }
}
