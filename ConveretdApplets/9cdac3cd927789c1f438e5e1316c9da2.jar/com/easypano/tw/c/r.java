// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import com.easypano.tw.dt;
import com.easypano.tw.da;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Vector;
import java.awt.Color;
import com.easypano.tw.l;

public class r implements p
{
    protected l a;
    protected Color b;
    private Vector c;
    Dimension d;
    int[] e;
    Image f;
    Image g;
    int[] h;
    int[] i;
    int[] j;
    MemoryImageSource k;
    double l;
    double m;
    double n;
    double o;
    double p;
    int q;
    int r;
    int s;
    int t;
    double u;
    double v;
    double w;
    double x;
    double y;
    double z;
    double A;
    double B;
    int C;
    int D;
    int E;
    int F;
    double G;
    Image H;
    Dimension I;
    Color J;
    int K;
    
    public r(final l a) {
        this.a = null;
        this.b = Color.black;
        this.c = new Vector();
        this.d = new Dimension(1, 1);
        this.e = new int[256];
        this.f = null;
        this.g = null;
        this.i = null;
        this.E = 0;
        this.F = 0;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = -1;
        this.a = a;
    }
    
    public void a(final Color b) {
        this.b = b;
    }
    
    public void a(final da da) {
        this.c.addElement(da);
    }
    
    public void a(final int k) {
        final boolean o = com.easypano.tw.c.a.O;
        final int size = this.c.size();
        int n = 0;
    Label_0227_Outer:
        while (true) {
        Label_0051:
            while (true) {
                Label_0046: {
                    if (!o) {
                        break Label_0046;
                    }
                    final int b = this.c.elementAt(n).b;
                    final int n2;
                    final int n3;
                    if (n2 == n3 && !o) {
                        break Label_0051;
                    }
                    ++n;
                }
                if (n < size) {
                    continue;
                }
                break;
            }
            int i;
            final int n2 = i = n;
            int n4;
            final int n3 = n4 = size;
            if (!o) {
                Label_0553: {
                    r r = null;
                    Label_0085: {
                        if (!o) {
                            if (n2 >= n3) {
                                break Label_0553;
                            }
                            r = this;
                            if (o) {
                                break Label_0085;
                            }
                            i = this.K;
                            n4 = k;
                        }
                        if (i == n4) {
                            return;
                        }
                        this.K = k;
                        r = this;
                    }
                    Object o2;
                    final Image image = (Image)(o2 = r.f);
                    if (!o) {
                        if (image == null) {
                            return;
                        }
                        o2 = this.c.elementAt(n);
                    }
                    final da da = (da)o2;
                    this.G = (da.e - 180) * 3.141592653589793 / 180.0;
                    this.H = da.c;
                    r r2 = this;
                    if (!o) {
                        if (this.H != null) {
                            dt.a(this.H);
                            this.I = new Dimension(this.H.getWidth(this.a), this.H.getHeight(this.a));
                        }
                        this.b(da.d);
                        r2 = this;
                    }
                    final int n5 = r2.d.width * this.d.height;
                    int n6 = 0;
                    while (true) {
                        while (true) {
                            Label_0295: {
                                if (!o) {
                                    break Label_0295;
                                }
                                final r r3 = this;
                                final int n7 = r3.h[n6];
                                this.i[n6] = (this.e[n7 >> 16 & 0xFF] << 16 | this.e[n7 >> 8 & 0xFF] << 8 | this.e[n7 & 0xFF] | 0xFF000000);
                                ++n6;
                            }
                            if (n6 < n5) {
                                continue Label_0227_Outer;
                            }
                            break;
                        }
                        this.J = da.f;
                        this.C = da.a.x;
                        this.D = da.a.y;
                        final r r3 = this;
                        if (o) {
                            continue;
                        }
                        break;
                    }
                    final Rectangle bounds = this.a.getBounds();
                    final int width = bounds.width;
                    final int width2 = this.d.width;
                    if (!o) {
                        if (width > width2) {
                            this.E = (bounds.width - this.d.width) / 2;
                        }
                        final int height = bounds.height;
                        final int height2 = this.d.height;
                    }
                    if (width > width2) {
                        this.F = (bounds.height - this.d.height) / 2;
                    }
                    this.y = this.a(Math.atan2(this.D, this.d.width - 1 - this.C));
                    this.z = this.a(Math.atan2(this.D, -this.C));
                    this.A = this.a(Math.atan2(this.D + 1 - this.d.height, -this.C));
                    this.B = this.a(Math.atan2(this.D + 1 - this.d.height, this.d.width - 1 - this.C));
                    if (!o) {
                        return;
                    }
                }
                this.K = -1;
                this.a.repaint();
                return;
            }
            continue;
        }
    }
    
    public void a(final Image f) {
        final boolean o = com.easypano.tw.c.a.O;
        Image image = f;
        Label_0311: {
            if (!o) {
                if (f == null) {
                    break Label_0311;
                }
                image = f;
            }
            dt.a(image);
            this.f = f;
            this.d.setSize(this.f.getWidth(this.a), this.f.getHeight(this.a));
            final int n = this.d.width * this.d.height;
            this.h = new int[n];
            this.i = new int[n];
            this.j = new int[n];
            final PixelGrabber pixelGrabber = new PixelGrabber(this.f, 0, 0, this.d.width, this.d.height, this.h, 0, this.d.width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            int n2 = 0;
            while (true) {
                while (true) {
                    Label_0221: {
                        if (!o) {
                            break Label_0221;
                        }
                        final r r = this;
                        final int n3 = r.h[n2];
                        this.i[n2] = (this.e[n3 >> 16 & 0xFF] << 16 | this.e[n3 >> 8 & 0xFF] << 8 | this.e[n3 & 0xFF] | 0xFF000000);
                        ++n2;
                    }
                    if (n2 < n) {
                        continue;
                    }
                    break;
                }
                System.arraycopy(this.h, 0, this.j, 0, n);
                (this.k = new MemoryImageSource(this.d.width, this.d.height, this.j, 0, this.d.width)).setAnimated(true);
                final r r = this;
                if (o) {
                    continue;
                }
                break;
            }
            this.g = this.a.createImage(this.k);
            if (!o) {
                return;
            }
        }
        this.f = null;
        this.i = null;
        this.j = null;
        this.d.setSize(0, 0);
    }
    
    public void c(final Graphics graphics) {
        final boolean o = com.easypano.tw.c.a.O;
        int n = (this.a.getBounds().width - this.d.width) / 2;
        final int n2 = (this.a.getBounds().height - this.d.height) / 2;
        r r = this;
        if (!o) {
            if (this.K == -1) {
                if (this.f == null) {
                    return;
                }
                graphics.drawImage(this.f, n, n2, this.a);
                if (!o) {
                    return;
                }
            }
            r = this;
        }
        final Image g = r.g;
        r r2 = null;
        Label_0250: {
            if (!o) {
                if (g == null) {
                    return;
                }
                graphics.drawImage(this.g, n, n2, this.a);
                graphics.setColor(this.J);
                graphics.drawLine(this.C + this.E, this.D + this.F, this.C + this.q + this.E, this.D - this.s + this.F);
                graphics.drawLine(this.C + this.E, this.D + this.F, this.C + this.r + this.E, this.D - this.t + this.F);
                r2 = this;
                if (o) {
                    break Label_0250;
                }
                final Image h = this.H;
            }
            if (g == null) {
                return;
            }
            n = this.C - this.I.width / 2;
            r2 = this;
        }
        graphics.drawImage(this.H, n + this.E, r2.D - this.I.height / 2 + this.F, this.a);
    }
    
    public void a(int n, int n2) {
        final boolean o = com.easypano.tw.c.a.O;
        r r = this;
        r r2 = this;
        if (!o) {
            if (this.K == -1) {
                return;
            }
            r = this;
            r2 = this;
        }
        if (!o) {
            if (r2.f == null) {
                return;
            }
            n -= this.C + this.E;
            r = this;
        }
        n2 = r.D + this.F - n2;
        this.a.e().gotoView((-(Math.atan2(n2, n) - this.G) + 3.141592653589793) * 180.0 / 3.141592653589793, this.m * 180.0 / 3.141592653589793, this.n * 180.0 / 3.141592653589793);
    }
    
    public void a(double a, final double m, double a2) {
        final boolean o = a.O;
        r r = this;
        r r2 = this;
        if (!o) {
            if (this.K == -1) {
                return;
            }
            r = this;
            r2 = this;
        }
        if (!o) {
            if (r2.f == null) {
                return;
            }
            a = this.a(-a + this.G);
            a2 = this.a(a2);
            this.l = a;
            this.m = m;
            this.n = a2;
            this.o = this.a(a + a2 / 2.0);
            this.p = this.a(a - a2 / 2.0);
            this.u = Math.tan(this.o);
            this.w = 1.0 / this.u;
            this.v = Math.tan(this.p);
            this.x = 1.0 / this.v;
            r = this;
        }
        r.e();
    }
    
    public double c() {
        return this.l;
    }
    
    public double d() {
        return this.n;
    }
    
    private void e() {
        final boolean o = com.easypano.tw.c.a.O;
        r r = this;
        if (!o) {
            if (this.i == null) {
                return;
            }
            r = this;
        }
        double k;
        int n3;
        double n2;
        final int n = (int)(n2 = (n3 = (int)(k = r.K)));
        if (!o) {
            if (n == -1) {
                return;
            }
            final double n4;
            n2 = (n4 = (n3 = (int)(k = dcmpg(this.o, this.y))));
        }
        Label_0264: {
            if (!o) {
                if (n <= 0) {
                    this.q = this.d.width - this.C - 1;
                    this.s = (int)(this.u * this.q);
                    if (!o) {
                        break Label_0264;
                    }
                }
                n3 = (int)(n2 = (k = dcmpg(this.o, this.z)));
            }
            if (!o) {
                if (n2 <= 0) {
                    this.s = this.D;
                    this.q = (int)(this.s * this.w);
                    if (!o) {
                        break Label_0264;
                    }
                }
                k = (n3 = dcmpg(this.o, this.A));
            }
            r r2 = null;
            Label_0250: {
                if (!o) {
                    if (n3 <= 0) {
                        this.q = -this.C;
                        this.s = (int)(this.u * this.q);
                        if (!o) {
                            break Label_0264;
                        }
                    }
                    r2 = this;
                    if (o) {
                        break Label_0250;
                    }
                    k = dcmpg(this.o, this.B);
                }
                if (k <= 0) {
                    this.s = this.D + 1 - this.d.height;
                    this.q = (int)(this.s * this.w);
                    if (!o) {
                        break Label_0264;
                    }
                }
                this.q = this.d.width - this.C - 1;
                r2 = this;
            }
            r2.s = (int)(this.u * this.q);
        }
        double n7;
        int n6;
        final double n5 = n6 = (int)(n7 = dcmpg(this.p, this.y));
        Label_0498: {
            if (!o) {
                if (n5 <= 0) {
                    this.r = this.d.width - this.C - 1;
                    this.t = (int)(this.v * this.r);
                    if (!o) {
                        break Label_0498;
                    }
                }
                final double n8;
                n6 = (int)(n8 = (n7 = dcmpg(this.p, this.z)));
            }
            if (!o) {
                if (n5 < 0) {
                    this.t = this.D;
                    this.r = (int)(this.t * this.x);
                    if (!o) {
                        break Label_0498;
                    }
                }
                n7 = (n6 = dcmpg(this.p, this.A));
            }
            r r3 = null;
            Label_0484: {
                if (!o) {
                    if (n6 < 0) {
                        this.r = -this.C;
                        this.t = (int)(this.v * this.r);
                        if (!o) {
                            break Label_0498;
                        }
                    }
                    r3 = this;
                    if (o) {
                        break Label_0484;
                    }
                    n7 = dcmpg(this.p, this.B);
                }
                if (n7 < 0) {
                    this.t = this.D + 1 - this.d.height;
                    this.r = (int)(this.t * this.x);
                    if (!o) {
                        break Label_0498;
                    }
                }
                this.r = this.d.width - this.C - 1;
                r3 = this;
            }
            r3.t = (int)(this.v * this.r);
        }
        r r4 = this;
        int[] array = null;
        double n9 = 0.0;
        double n10 = 0.0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        Label_0633: {
            if (!o) {
                if (this.n <= 3.141592653589793) {
                    System.arraycopy(this.h, 0, this.j, 0, this.i.length);
                    array = this.i;
                    n9 = this.w;
                    n10 = this.x;
                    n11 = this.q;
                    n12 = this.r;
                    n13 = this.s;
                    n14 = this.t;
                    if (!o) {
                        break Label_0633;
                    }
                }
                System.arraycopy(this.i, 0, this.j, 0, this.i.length);
                r4 = this;
            }
            array = r4.h;
            n9 = this.x;
            n10 = this.w;
            n11 = this.r;
            n12 = this.q;
            n13 = this.t;
            n14 = this.s;
        }
        final int n15 = (int)(n9 * 1024.0);
        final int n16 = (int)(n10 * 1024.0);
        final int width = this.d.width;
        final int height = this.d.height;
        int n17 = 0;
        r r5 = this;
        Label_2265: {
            if (!o) {
                Label_2264: {
                    if (this.n >= 1.0E-5) {
                        int n21;
                        int d;
                        int n20;
                        int n19;
                        final int n18 = n19 = (n20 = (d = (n21 = dcmpg(6.283185307179586 - this.n, 1.0E-5))));
                        if (!o) {
                            if (n18 < 0) {
                                break Label_2264;
                            }
                            final int n22;
                            n19 = (n22 = (n20 = (d = (n21 = n13))));
                        }
                        Label_1201: {
                            if (!o) {
                                if (n18 >= 0) {
                                    final int n23 = n19 = (n20 = (d = (n21 = n14)));
                                    if (o) {
                                        break Label_1201;
                                    }
                                    if (n23 >= 0) {
                                        int n25;
                                        final int n24 = n25 = n13;
                                        int n27;
                                        final int n26 = n27 = n14;
                                        if (!o) {
                                            if (n24 >= n26) {
                                                final int d2 = this.D;
                                                final int n28 = n13;
                                                final int n29;
                                                Label_0821: {
                                                    if (!o) {
                                                        if (d2 > n28) {
                                                            n29 = n11;
                                                            if (o) {
                                                                break Label_0821;
                                                            }
                                                            if (n29 < 0) {
                                                                final int n30 = 0;
                                                                System.arraycopy(array, n30, this.j, n30, width * (this.D - n13) - n30);
                                                                final int n31 = width * (this.D - n13);
                                                            }
                                                        }
                                                        final int n32 = this.D - n13;
                                                    }
                                                    n17 = d2 * n28;
                                                }
                                                int n33 = n29;
                                                int n36 = 0;
                                                while (true) {
                                                    while (true) {
                                                        Label_0880: {
                                                            if (!o) {
                                                                break Label_0880;
                                                            }
                                                            final int n34 = n17 + (n33 * n15 >> 10) + this.C;
                                                            final int n35 = n36;
                                                            System.arraycopy(array, n35, this.j, n35, n17 + width - n35);
                                                            n17 += width;
                                                            --n33;
                                                        }
                                                        if (n33 > n14) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                    n17 += this.C;
                                                    n36 = n14;
                                                    if (o) {
                                                        continue;
                                                    }
                                                    break;
                                                }
                                                int n37 = n36;
                                                while (true) {
                                                    Label_0963: {
                                                        if (!o) {
                                                            break Label_0963;
                                                        }
                                                        final int n38 = n17 + (n37 * n15 >> 10);
                                                        System.arraycopy(array, n38, this.j, n38, n17 + (n37 * n16 >> 10) - n38);
                                                        n17 += width;
                                                        --n37;
                                                    }
                                                    if (n37 < 0) {
                                                        break Label_2264;
                                                    }
                                                    continue;
                                                }
                                            }
                                            else {
                                                final int d3;
                                                n25 = (d3 = this.D);
                                                final int n39;
                                                n27 = (n39 = n14);
                                            }
                                        }
                                        final int n40;
                                        Label_1052: {
                                            if (!o) {
                                                if (n24 > n26) {
                                                    n40 = n12;
                                                    if (o) {
                                                        break Label_1052;
                                                    }
                                                    if (n40 > 0) {
                                                        final int n41 = 0;
                                                        System.arraycopy(array, n41, this.j, n41, width * (this.D - n14) - n41);
                                                        final int n42 = width * (this.D - n14);
                                                    }
                                                }
                                                n25 = width;
                                                n27 = this.D - n14;
                                            }
                                            n17 = n25 * n27;
                                        }
                                        int n43 = n40;
                                        int n45 = 0;
                                        while (true) {
                                            Label_1108: {
                                                if (!o) {
                                                    break Label_1108;
                                                }
                                                final int n44 = n45;
                                                System.arraycopy(array, n44, this.j, n44, n17 + (n43 * n16 >> 10) + this.C - n44);
                                                n17 += width;
                                                --n43;
                                            }
                                            if (n43 > n13) {
                                                continue;
                                            }
                                            n17 += this.C;
                                            n45 = n13;
                                            if (o) {
                                                continue;
                                            }
                                            break;
                                        }
                                        int n46 = n45;
                                        while (true) {
                                            Label_1191: {
                                                if (!o) {
                                                    break Label_1191;
                                                }
                                                final int n47 = n17 + (n46 * n15 >> 10);
                                                System.arraycopy(array, n47, this.j, n47, n17 + (n46 * n16 >> 10) - n47);
                                                n17 += width;
                                                --n46;
                                            }
                                            if (n46 < 0) {
                                                break Label_2264;
                                            }
                                            continue;
                                        }
                                    }
                                }
                                n20 = (n19 = (d = (n21 = n13)));
                            }
                        }
                        Label_1699: {
                            if (!o) {
                                if (n19 < 0) {
                                    final int n48 = n20 = (d = (n21 = n14));
                                    if (o) {
                                        break Label_1699;
                                    }
                                    if (n48 < 0) {
                                        n17 = this.D * width + this.C;
                                        final int n49 = n13;
                                        if (!o && n49 <= n14) {
                                            int n50 = 0;
                                            int n52 = 0;
                                            while (true) {
                                                Label_1306: {
                                                    if (!o) {
                                                        break Label_1306;
                                                    }
                                                    final int n51 = n52;
                                                    System.arraycopy(array, n51, this.j, n51, n17 + (n50 * n15 >> 10) - n51);
                                                    n17 += width;
                                                    --n50;
                                                }
                                                if (n50 >= n14) {
                                                    continue;
                                                }
                                                n17 -= this.C;
                                                n52 = n14 - 1;
                                                if (o) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            int n53 = n52;
                                            int n55 = 0;
                                            int n56;
                                            while (true) {
                                                Label_1387: {
                                                    if (!o) {
                                                        break Label_1387;
                                                    }
                                                    final int n54 = n55;
                                                    System.arraycopy(array, n54, this.j, n54, n17 + (n53 * n15 >> 10) + this.C - n54);
                                                    n17 += width;
                                                    --n53;
                                                }
                                                if (n53 >= n13) {
                                                    continue;
                                                }
                                                n55 = (n56 = this.D + 1 - height);
                                                if (o) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            if (!o) {
                                                if (n55 < n13) {
                                                    n56 = 1;
                                                }
                                                else {
                                                    n56 = 0;
                                                }
                                            }
                                            final int n57 = n11;
                                            if (!o && n57 <= 0) {}
                                            final int n58 = n56 & n57;
                                            if (!o && n58 == 0) {
                                                break Label_2264;
                                            }
                                            final int n59 = n58;
                                            System.arraycopy(array, n59, this.j, n59, width * height - n59);
                                            if (o) {
                                                goto Label_1479;
                                            }
                                            break Label_2264;
                                        }
                                        else {
                                            int n60 = n49;
                                            int n62 = 0;
                                            while (true) {
                                                Label_1540: {
                                                    if (!o) {
                                                        break Label_1540;
                                                    }
                                                    final int n61 = n62;
                                                    System.arraycopy(array, n61, this.j, n61, n17 + (n60 * n15 >> 10) - n61);
                                                    n17 += width;
                                                    --n60;
                                                }
                                                if (n60 >= n13) {
                                                    continue;
                                                }
                                                n17 -= this.C;
                                                n62 = n13 - 1;
                                                if (o) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            int n63 = n62;
                                            int n65 = 0;
                                            int n66 = 0;
                                            while (true) {
                                                while (true) {
                                                    Label_1624: {
                                                        if (!o) {
                                                            break Label_1624;
                                                        }
                                                        final int c = this.C;
                                                        final int n64 = n65 + n66;
                                                        System.arraycopy(array, n64, this.j, n64, n17 + width - n64);
                                                        n17 += width;
                                                        --n63;
                                                    }
                                                    if (n63 >= n14) {
                                                        continue;
                                                    }
                                                    break;
                                                }
                                                r5 = this;
                                                if (o) {
                                                    break Label_2265;
                                                }
                                                n65 = this.D + 1 - height;
                                                n66 = n14;
                                                if (o) {
                                                    continue;
                                                }
                                                break;
                                            }
                                            if (n65 >= n66) {
                                                break Label_2264;
                                            }
                                            final int n67 = n12;
                                            if (!o && n67 >= 0) {
                                                break Label_2264;
                                            }
                                            final int n68 = n67;
                                            System.arraycopy(array, n68, this.j, n68, width * height - n68);
                                            if (!o) {
                                                break Label_2264;
                                            }
                                        }
                                    }
                                }
                                d = (n20 = (n21 = n13));
                            }
                        }
                        int n70;
                        final int n69 = n70 = n14;
                        if (!o) {
                            if (n20 >= n69) {
                                final int d4 = this.D;
                                final int n71 = n13;
                                final int n72;
                                Label_1790: {
                                    if (!o) {
                                        if (d4 > n71) {
                                            n72 = n11;
                                            if (o) {
                                                break Label_1790;
                                            }
                                            if (n72 < 0) {
                                                final int n73 = 0;
                                                System.arraycopy(array, n73, this.j, n73, width * (this.D - n13) - n73);
                                                final int n74 = width * (this.D - n13);
                                            }
                                        }
                                        final int n75 = this.D - n13;
                                    }
                                    n17 = d4 * n71;
                                }
                                int n76 = n72;
                                int n79 = 0;
                                while (true) {
                                    while (true) {
                                        Label_1849: {
                                            if (!o) {
                                                break Label_1849;
                                            }
                                            final int n77 = n17 + (n76 * n15 >> 10) + this.C;
                                            final int n78 = n79;
                                            System.arraycopy(array, n78, this.j, n78, n17 + width - n78);
                                            n17 += width;
                                            --n76;
                                        }
                                        if (n76 > 0) {
                                            continue;
                                        }
                                        break;
                                    }
                                    n79 = 0;
                                    if (o) {
                                        continue;
                                    }
                                    break;
                                }
                                int n80 = n79;
                                int n82 = 0;
                                int n83 = 0;
                                while (true) {
                                    while (true) {
                                        Label_1919: {
                                            if (!o) {
                                                break Label_1919;
                                            }
                                            final int c2 = this.C;
                                            final int n81 = n82 + n83;
                                            System.arraycopy(array, n81, this.j, n81, n17 + width - n81);
                                            n17 += width;
                                            --n80;
                                        }
                                        if (n80 >= n14) {
                                            continue;
                                        }
                                        break;
                                    }
                                    r5 = this;
                                    if (o) {
                                        break Label_2265;
                                    }
                                    n82 = this.D + 1 - height;
                                    n83 = n14;
                                    if (o) {
                                        continue;
                                    }
                                    break;
                                }
                                if (n82 >= n83) {
                                    break Label_2264;
                                }
                                final int n84 = n12;
                                if (!o && n84 >= 0) {
                                    break Label_2264;
                                }
                                final int n85 = n84;
                                System.arraycopy(array, n85, this.j, n85, width * height - n85);
                                if (!o) {
                                    break Label_2264;
                                }
                            }
                            n21 = (d = this.D);
                            final int n86;
                            n70 = (n86 = n14);
                        }
                        final int n87;
                        Label_2073: {
                            if (!o) {
                                if (d > n69) {
                                    n87 = n12;
                                    if (o) {
                                        break Label_2073;
                                    }
                                    if (n87 > 0) {
                                        final int n88 = 0;
                                        System.arraycopy(array, n88, this.j, n88, width * (this.D - n14) - n88);
                                        final int n89 = width * (this.D - n14);
                                    }
                                }
                                n21 = width;
                                n70 = this.D - n14;
                            }
                            n17 = n21 * n70;
                        }
                        int n90 = n87;
                        int n92 = 0;
                        while (true) {
                            Label_2129: {
                                if (!o) {
                                    break Label_2129;
                                }
                                final int n91 = n92;
                                System.arraycopy(array, n91, this.j, n91, n17 + (n90 * n16 >> 10) + this.C - n91);
                                n17 += width;
                                --n90;
                            }
                            if (n90 > 0) {
                                continue;
                            }
                            n92 = 0;
                            if (o) {
                                continue;
                            }
                            break;
                        }
                        int n93 = n92;
                        int n95 = 0;
                        while (true) {
                            Label_2196: {
                                if (!o) {
                                    break Label_2196;
                                }
                                final int n94 = n95;
                                System.arraycopy(array, n94, this.j, n94, n17 + (n93 * n15 >> 10) + this.C - n94);
                                n17 += width;
                                --n93;
                            }
                            if (n93 >= n13) {
                                continue;
                            }
                            r5 = this;
                            if (o) {
                                break Label_2265;
                            }
                            n95 = this.D + 1 - height;
                            if (o) {
                                continue;
                            }
                            break;
                        }
                        if (n95 < n13) {
                            final int n96 = n11;
                            if (o || n96 > 0) {
                                final int n97 = n96;
                                System.arraycopy(array, n97, this.j, n97, width * height - n97);
                            }
                        }
                    }
                }
                r5 = this;
            }
        }
        r5.k.newPixels();
    }
    
    private void b(int n) {
        final boolean o = com.easypano.tw.c.a.O;
        final int n2 = n;
        while (true) {
            Label_0083: {
                if (!o) {
                    if (n2 >= 0) {
                        int n3 = 0;
                        while (true) {
                            Label_0039: {
                                if (!o) {
                                    break Label_0039;
                                }
                                this.e[n3] = n3 + ((255 - n3) * n >> 8);
                                ++n3;
                            }
                            if (n3 >= 256) {
                                break Label_0083;
                            }
                            continue;
                        }
                    }
                    else {
                        n += 256;
                    }
                }
                int n3 = n2;
                while (true) {
                    Label_0076: {
                        if (!o) {
                            break Label_0076;
                        }
                        this.e[n3] = n3 * n >> 8;
                        ++n3;
                    }
                    if (n3 < 256) {
                        continue;
                    }
                    break;
                }
            }
            if (!o) {
                return;
            }
            continue;
        }
    }
    
    private double a(double n) {
        final boolean o = com.easypano.tw.c.a.O;
        while (true) {
            Label_0014: {
                if (!o) {
                    break Label_0014;
                }
                n -= 6.283185307179586;
            }
            if (n >= 6.283185307179586) {
                continue;
            }
            break;
        }
        while (true) {
            Label_0032: {
                if (!o) {
                    break Label_0032;
                }
                final double n2;
                n = n2;
            }
            if (n < 0.0) {
                continue;
            }
            final double n2 = n;
            if (!o) {
                return n2;
            }
            continue;
        }
    }
    
    public void a(final Graphics graphics) {
        this.b(graphics);
        this.c(graphics);
        this.d(graphics);
    }
    
    public void b(final Graphics graphics) {
        graphics.setColor(this.a.getBackground());
        graphics.fillRect(0, 0, this.a.getBounds().width, this.a.getBounds().height);
    }
    
    public Insets b() {
        return new Insets(0, 0, 0, 0);
    }
    
    public void a(final Insets insets) {
    }
    
    public void d(final Graphics graphics) {
        if (this.b != null) {
            graphics.setColor(this.b);
            graphics.drawRect(0, 0, this.a.getBounds().width, this.a.getBounds().height);
        }
    }
    
    public Dimension a() {
        return this.d;
    }
    
    public void destroyResource() {
        for (int size = this.c.size(), i = 0; i < size; ++i) {
            ((da)this.c.elementAt(i)).a();
        }
        this.c.removeAllElements();
        this.c = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.H = null;
    }
}