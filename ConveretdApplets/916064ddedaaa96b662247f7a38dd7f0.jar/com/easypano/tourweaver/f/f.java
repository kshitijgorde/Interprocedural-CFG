// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Toolkit;
import com.easypano.tourweaver.a.e;
import com.easypano.tourweaver.b.kb;
import java.awt.Point;
import java.awt.Shape;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Rectangle;

public class f
{
    Rectangle a;
    int[] b;
    Image c;
    Image d;
    int[] e;
    int[] f;
    int[] g;
    MemoryImageSource h;
    double i;
    double j;
    double k;
    double l;
    int m;
    int n;
    int o;
    int p;
    double q;
    double r;
    double s;
    double t;
    double u;
    double v;
    double w;
    double x;
    int y;
    int z;
    int A;
    int B;
    Shape C;
    int D;
    boolean E;
    
    public f() {
        this.a = new Rectangle();
        this.b = new int[256];
        this.c = null;
        this.d = null;
        this.j = 1.0471975511965976;
        this.E = false;
    }
    
    public Point a() {
        if (this.C instanceof kb) {
            return new kb(this.a.x, this.a.y, this.a.width, this.a.height).a(this.y + this.m + this.a.x, this.z - this.o + this.a.y, this.y + this.a.x, this.z + this.a.y);
        }
        return new Point(this.y + this.m + this.a.x, this.z - this.o + this.a.y);
    }
    
    public Point b() {
        if (this.C instanceof kb) {
            return new kb(this.a.x, this.a.y, this.a.width, this.a.height).a(this.y + this.n + this.a.x, this.z - this.p + this.a.y, this.y + this.a.x, this.z + this.a.y);
        }
        return new Point(this.y + this.n + this.a.x, this.z - this.p + this.a.y);
    }
    
    public int c() {
        return this.y + this.m + this.a.x;
    }
    
    public int d() {
        return this.z - this.o + this.a.y;
    }
    
    public int e() {
        return this.y + this.n + this.a.x;
    }
    
    public int f() {
        return this.z - this.p + this.a.y;
    }
    
    public void g() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.c = null;
        this.h = null;
    }
    
    public void a(final int d) {
        this.b(this.D = d);
    }
    
    public void a(final Image c, final int n, final int n2, final Rectangle bounds, final Shape c2, final int n3) {
        Image image = c;
        if (!com.easypano.tourweaver.f.r.i) {
            if (c == null) {
                return;
            }
            this.c = c;
            image = c;
        }
        com.easypano.tourweaver.a.e.a(image);
        this.a(n3);
        this.E = true;
        this.y = n - bounds.x;
        this.z = n2 - bounds.y;
        this.a.setBounds(bounds);
        this.a(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(c.getSource(), new CropImageFilter(bounds.x, bounds.y, bounds.width, bounds.height))), this.C = c2);
    }
    
    private void a(final Image image, final Shape shape) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        com.easypano.tourweaver.a.e.a(image);
        this.a.width = image.getWidth(com.easypano.tourweaver.a.e.f);
        this.a.height = image.getHeight(com.easypano.tourweaver.a.e.f);
        final int n = this.a.width * this.a.height;
        this.u = this.a(Math.atan2(this.z, this.a.width - 1 - this.y));
        this.v = this.a(Math.atan2(this.z, -this.y));
        this.w = this.a(Math.atan2(this.z + 1 - this.a.height, -this.y));
        this.x = this.a(Math.atan2(this.z + 1 - this.a.height, this.a.width - 1 - this.y));
        f f = this;
        int d = 0;
        final int n2;
        int length = 0;
        final int n3;
        Label_0234: {
            Label_0227: {
                if (!i) {
                    if (this.e != null) {
                        n2 = (d = n);
                        n3 = (length = this.e.length);
                        if (i) {
                            break Label_0234;
                        }
                        if (n2 == n3) {
                            break Label_0227;
                        }
                    }
                    this.e = com.easypano.tourweaver.a.e.b(image);
                    this.f = new int[n];
                    f = this;
                }
                f.g = new int[n];
            }
            final int d2;
            d = (d2 = this.D);
            final int n4;
            length = (n4 = 255);
        }
        int n5 = 0;
        Label_0272: {
            if (!i) {
                if (n2 > n3) {
                    this.D = 255;
                }
                n5 = (d = this.D);
                if (i) {
                    break Label_0272;
                }
                length = -255;
            }
            if (d < length) {
                this.D = -255;
            }
            n5 = 0;
        }
        int j = n5;
        while (true) {
            while (j < n) {
                final int n6 = this.e[j];
                this.f[j] = (this.b[n6 >> 16 & 0xFF] << 16 | this.b[n6 >> 8 & 0xFF] << 8 | this.b[n6 & 0xFF] | (0xFF000000 & n6));
                final int n7 = n6 >> 24 & 0xFF;
                if (i) {
                    final int[] e = this.e;
                    this.e = this.f;
                    this.f = e;
                    (this.h = new MemoryImageSource(this.a.width, this.a.height, this.g, 0, this.a.width)).setAnimated(true);
                    this.d = Toolkit.getDefaultToolkit().createImage(this.h);
                    return;
                }
                if (!i) {
                    Label_0434: {
                        if (n7 == 0) {
                            f f2 = this;
                            if (!i) {
                                if (this.D > 0) {
                                    this.f[j] = (this.D << 24 | (this.f[j] & 0xFFFFFF));
                                    if (!i) {
                                        break Label_0434;
                                    }
                                }
                                f2 = this;
                            }
                            f2.f[j] = (-this.D << 24 | 0x0);
                        }
                    }
                    ++j;
                }
                if (i) {
                    break;
                }
            }
            this.a(this.f, shape, this.e);
            continue;
        }
    }
    
    private void a(final int[] array, final Shape shape, final int[] array2) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final boolean b = shape instanceof Rectangle;
        if (!i) {
            if (b) {
                final Rectangle rectangle = new Rectangle(0, 0, this.a.width, this.a.height);
                int n = 0;
                do {
                    int j = 0;
                Label_0050:
                    while (j < this.a.width) {
                        if (i) {
                            return;
                        }
                        int k = 0;
                        while (k < this.a.height) {
                            int contains;
                            j = (contains = (rectangle.contains(n, k) ? 1 : 0));
                            if (i) {
                                continue Label_0050;
                            }
                            Label_0125: {
                                if (!i) {
                                    if (j != 0) {
                                        break Label_0125;
                                    }
                                    contains = this.a.width * k + n;
                                }
                                final int n2 = contains;
                                array[n2] = array2[n2];
                            }
                            ++k;
                            if (i) {
                                break;
                            }
                        }
                        ++n;
                    }
                    break;
                } while (!i);
                if (!i) {
                    return;
                }
            }
            final boolean b2 = shape instanceof kb;
        }
        if (b) {
            final kb kb = new kb(0, 0, this.a.width, this.a.height);
            int n3 = 0;
            do {
                int l = 0;
            Label_0183:
                while (l < this.a.width) {
                    int n4 = 0;
                    while (n4 < this.a.height) {
                        int contains2;
                        l = (contains2 = (kb.contains(n3, n4) ? 1 : 0));
                        if (i) {
                            continue Label_0183;
                        }
                        Label_0255: {
                            if (!i) {
                                if (l != 0) {
                                    break Label_0255;
                                }
                                contains2 = this.a.width * n4 + n3;
                            }
                            final int n5 = contains2;
                            array[n5] = array2[n5];
                        }
                        ++n4;
                        if (i) {
                            break;
                        }
                    }
                    ++n3;
                }
                break;
            } while (!i);
        }
    }
    
    public Image h() {
        return this.d;
    }
    
    public void a(double a, double a2) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        a = this.a(a);
        a2 = this.a(a2);
        f f = this;
        Label_0159: {
            if (!i) {
                if (Math.abs(this.i - a) <= 1.0E-5) {
                    f = this;
                    if (i) {
                        break Label_0159;
                    }
                    if (Math.abs(this.j - a2) <= 1.0E-5) {
                        f = this;
                        if (i) {
                            break Label_0159;
                        }
                        if (!this.E) {
                            return;
                        }
                    }
                }
                this.E = false;
                this.i = a;
                this.j = a2;
                this.k = this.a(a + a2 / 2.0);
                this.l = this.a(a - a2 / 2.0);
                this.q = Math.tan(this.k);
                this.s = 1.0 / this.q;
                this.r = Math.tan(this.l);
                this.t = 1.0 / this.r;
                f = this;
            }
        }
        f.k();
    }
    
    public double i() {
        return this.i;
    }
    
    public double j() {
        return this.j;
    }
    
    public void k() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        f f = this;
        if (!i) {
            if (this.f == null) {
                return;
            }
            f = this;
        }
        double n3;
        double n2;
        final double n = n2 = (n3 = dcmpg(f.k, this.u));
        Label_0251: {
            if (!i) {
                if (n <= 0) {
                    this.m = this.a.width - this.y - 1;
                    this.o = (int)(this.q * this.m);
                    if (!i) {
                        break Label_0251;
                    }
                }
                final double n4;
                n2 = (n4 = (n3 = dcmpg(this.k, this.v)));
            }
            if (!i) {
                if (n <= 0) {
                    this.o = this.z;
                    this.m = (int)(this.o * this.s);
                    if (!i) {
                        break Label_0251;
                    }
                }
                n3 = (n2 = dcmpg(this.k, this.w));
            }
            f f2 = null;
            Label_0237: {
                if (!i) {
                    if (n2 <= 0) {
                        this.m = -this.y;
                        this.o = (int)(this.q * this.m);
                        if (!i) {
                            break Label_0251;
                        }
                    }
                    f2 = this;
                    if (i) {
                        break Label_0237;
                    }
                    n3 = dcmpg(this.k, this.x);
                }
                if (n3 <= 0) {
                    this.o = this.z + 1 - this.a.height;
                    this.m = (int)(this.o * this.s);
                    if (!i) {
                        break Label_0251;
                    }
                }
                this.m = this.a.width - this.y - 1;
                f2 = this;
            }
            f2.o = (int)(this.q * this.m);
        }
        double n7;
        int n6;
        final double n5 = n6 = (int)(n7 = dcmpg(this.l, this.u));
        Label_0485: {
            if (!i) {
                if (n5 <= 0) {
                    this.n = this.a.width - this.y - 1;
                    this.p = (int)(this.r * this.n);
                    if (!i) {
                        break Label_0485;
                    }
                }
                final double n8;
                n6 = (int)(n8 = (n7 = dcmpg(this.l, this.v)));
            }
            if (!i) {
                if (n5 < 0) {
                    this.p = this.z;
                    this.n = (int)(this.p * this.t);
                    if (!i) {
                        break Label_0485;
                    }
                }
                n7 = (n6 = dcmpg(this.l, this.w));
            }
            f f3 = null;
            Label_0471: {
                if (!i) {
                    if (n6 < 0) {
                        this.n = -this.y;
                        this.p = (int)(this.r * this.n);
                        if (!i) {
                            break Label_0485;
                        }
                    }
                    f3 = this;
                    if (i) {
                        break Label_0471;
                    }
                    n7 = dcmpg(this.l, this.x);
                }
                if (n7 < 0) {
                    this.p = this.z + 1 - this.a.height;
                    this.n = (int)(this.p * this.t);
                    if (!i) {
                        break Label_0485;
                    }
                }
                this.n = this.a.width - this.y - 1;
                f3 = this;
            }
            f3.p = (int)(this.r * this.n);
        }
        f f4 = this;
        int[] array = null;
        double n9 = 0.0;
        double n10 = 0.0;
        int n11 = 0;
        int n12 = 0;
        int n13 = 0;
        int n14 = 0;
        Label_0620: {
            if (!i) {
                if (this.j <= 3.141592653589793) {
                    System.arraycopy(this.f, 0, this.g, 0, this.f.length);
                    array = this.e;
                    n9 = this.s;
                    n10 = this.t;
                    n11 = this.m;
                    n12 = this.n;
                    n13 = this.o;
                    n14 = this.p;
                    if (!i) {
                        break Label_0620;
                    }
                }
                System.arraycopy(this.e, 0, this.g, 0, this.f.length);
                f4 = this;
            }
            array = f4.f;
            n9 = this.t;
            n10 = this.s;
            n11 = this.n;
            n12 = this.m;
            n13 = this.p;
            n14 = this.o;
        }
        final int n15 = (int)(n9 * 1024.0);
        final int n16 = (int)(n10 * 1024.0);
        final int width = this.a.width;
        final int height = this.a.height;
        int n17 = 0;
        f f5 = this;
        Label_2261: {
            if (!i) {
                Label_2260: {
                    if (this.j >= 1.0E-5) {
                        int n21;
                        int z;
                        int n20;
                        int n19;
                        final int n18 = n19 = (n20 = (z = (n21 = dcmpg(6.283185307179586 - this.j, 1.0E-5))));
                        if (!i) {
                            if (n18 < 0 && !i) {
                                break Label_2260;
                            }
                            final int n22;
                            n19 = (n22 = (n20 = (z = (n21 = n13))));
                        }
                        Label_1197: {
                            if (!i) {
                                Label_1195: {
                                    if (n18 >= 0) {
                                        final int n23 = n19 = (n20 = (z = (n21 = n14)));
                                        if (i) {
                                            break Label_1197;
                                        }
                                        if (n23 >= 0) {
                                            int n25;
                                            final int n24 = n25 = n13;
                                            int n27;
                                            final int n26 = n27 = n14;
                                            if (!i) {
                                                Label_0965: {
                                                    if (n24 >= n26) {
                                                        final int z2 = this.z;
                                                        final int n28 = n13;
                                                        final int n29;
                                                        Label_0813: {
                                                            if (!i) {
                                                                if (z2 > n28) {
                                                                    n29 = n11;
                                                                    if (i) {
                                                                        break Label_0813;
                                                                    }
                                                                    if (n29 < 0) {
                                                                        final int n30 = 0;
                                                                        System.arraycopy(array, n30, this.g, n30, width * (this.z - n13) - n30);
                                                                        final int n31 = width * (this.z - n13);
                                                                    }
                                                                }
                                                                final int n32 = this.z - n13;
                                                            }
                                                            n17 = z2 * n28;
                                                        }
                                                        int j = n29;
                                                        while (true) {
                                                            while (j > n14) {
                                                                final int n33 = n17 + (j * n15 >> 10) + this.y;
                                                                System.arraycopy(array, n33, this.g, n33, n17 + width - n33);
                                                                n17 += width;
                                                                --j;
                                                                if (i) {
                                                                    int k = n14;
                                                                    while (k >= 0) {
                                                                        final int n34 = n17 + (k * n15 >> 10);
                                                                        System.arraycopy(array, n34, this.g, n34, n17 + (k * n16 >> 10) - n34);
                                                                        n17 += width;
                                                                        --k;
                                                                        if (i) {
                                                                            return;
                                                                        }
                                                                        if (i) {
                                                                            break Label_0965;
                                                                        }
                                                                    }
                                                                    break Label_2260;
                                                                }
                                                                if (i) {
                                                                    break;
                                                                }
                                                            }
                                                            n17 += this.y;
                                                            continue;
                                                        }
                                                    }
                                                }
                                                final int z3;
                                                n25 = (z3 = this.z);
                                                final int n35;
                                                n27 = (n35 = n14);
                                            }
                                            final int n36;
                                            Label_1046: {
                                                if (!i) {
                                                    if (n24 > n26) {
                                                        n36 = n12;
                                                        if (i) {
                                                            break Label_1046;
                                                        }
                                                        if (n36 > 0) {
                                                            final int n37 = 0;
                                                            System.arraycopy(array, n37, this.g, n37, width * (this.z - n14) - n37);
                                                            final int n38 = width * (this.z - n14);
                                                        }
                                                    }
                                                    n25 = width;
                                                    n27 = this.z - n14;
                                                }
                                                n17 = n25 * n27;
                                            }
                                            int l = n36;
                                            while (true) {
                                                while (l > n13) {
                                                    final int n39 = n17;
                                                    System.arraycopy(array, n39, this.g, n39, n17 + (l * n16 >> 10) + this.y - n39);
                                                    n17 += width;
                                                    --l;
                                                    if (i) {
                                                        int n40 = n13;
                                                        while (n40 >= 0) {
                                                            final int n41 = n17 + (n40 * n15 >> 10);
                                                            System.arraycopy(array, n41, this.g, n41, n17 + (n40 * n16 >> 10) - n41);
                                                            n17 += width;
                                                            --n40;
                                                            if (i) {
                                                                return;
                                                            }
                                                            if (i) {
                                                                break Label_1195;
                                                            }
                                                        }
                                                        break Label_2260;
                                                    }
                                                    if (i) {
                                                        break;
                                                    }
                                                }
                                                n17 += this.y;
                                                continue;
                                            }
                                        }
                                    }
                                }
                                n20 = (n19 = (z = (n21 = n13)));
                            }
                        }
                        Label_1695: {
                            if (!i) {
                                Label_1693: {
                                    if (n19 < 0) {
                                        final int n42 = n20 = (z = (n21 = n14));
                                        if (i) {
                                            break Label_1695;
                                        }
                                        if (n42 < 0) {
                                            n17 = this.z * width + this.y;
                                            final int n43 = n13;
                                            if (!i && n43 <= n14) {
                                                int n44 = 0;
                                                while (true) {
                                                Label_1419_Outer:
                                                    while (n44 >= n14) {
                                                        final int n45 = n17 + (n44 * n16 >> 10);
                                                        System.arraycopy(array, n45, this.g, n45, n17 + (n44 * n15 >> 10) - n45);
                                                        n17 += width;
                                                        --n44;
                                                        if (!i) {
                                                            if (i) {
                                                                break;
                                                            }
                                                            continue;
                                                        }
                                                        else {
                                                            int n46 = n14 - 1;
                                                            while (true) {
                                                                while (n46 >= n13) {
                                                                    final int n47 = n17;
                                                                    System.arraycopy(array, n47, this.g, n47, n17 + (n46 * n15 >> 10) + this.y - n47);
                                                                    n17 += width;
                                                                    --n46;
                                                                    if (!i) {
                                                                        if (i) {
                                                                            break;
                                                                        }
                                                                        continue Label_1419_Outer;
                                                                    }
                                                                    else {
                                                                        final int n48 = n11;
                                                                        if (!i && n48 <= 0) {}
                                                                        final int n50;
                                                                        final int n49 = n50 & n48;
                                                                        if (!i && n49 == 0) {
                                                                            break Label_2260;
                                                                        }
                                                                        final int n51 = n49;
                                                                        System.arraycopy(array, n51, this.g, n51, width * height - n51);
                                                                        if (i) {
                                                                            goto Label_1475;
                                                                        }
                                                                        break Label_2260;
                                                                    }
                                                                }
                                                                final int n50 = this.z + 1 - height;
                                                                if (!i && n50 >= n13) {}
                                                                continue;
                                                            }
                                                        }
                                                    }
                                                    n17 -= this.y;
                                                    continue;
                                                }
                                            }
                                            int n52 = n43;
                                            while (true) {
                                                while (n52 >= n13) {
                                                    final int n53 = n17 + (n52 * n16 >> 10);
                                                    System.arraycopy(array, n53, this.g, n53, n17 + (n52 * n15 >> 10) - n53);
                                                    n17 += width;
                                                    --n52;
                                                    if (!i) {
                                                        if (i) {
                                                            break;
                                                        }
                                                        continue;
                                                    }
                                                    else {
                                                        int n54 = n13 - 1;
                                                        while (n54 >= n14) {
                                                            final int n55 = n17 + (n54 * n16 >> 10) + this.y;
                                                            System.arraycopy(array, n55, this.g, n55, n17 + width - n55);
                                                            n17 += width;
                                                            --n54;
                                                            if (i) {
                                                                break Label_2260;
                                                            }
                                                            if (i) {
                                                                break;
                                                            }
                                                        }
                                                        f5 = this;
                                                        if (i) {
                                                            break Label_2261;
                                                        }
                                                        if (this.z + 1 - height >= n14) {
                                                            break Label_2260;
                                                        }
                                                        final int n56 = n12;
                                                        if (!i && n56 >= 0) {
                                                            break Label_2260;
                                                        }
                                                        final int n57 = n56;
                                                        System.arraycopy(array, n57, this.g, n57, width * height - n57);
                                                        if (i) {
                                                            break Label_1693;
                                                        }
                                                        break Label_2260;
                                                    }
                                                }
                                                n17 -= this.y;
                                                continue;
                                            }
                                        }
                                    }
                                }
                                z = (n20 = (n21 = n13));
                            }
                        }
                        int n59;
                        final int n58 = n59 = n14;
                        if (!i) {
                            Label_1988: {
                                if (n20 >= n58) {
                                    final int z4 = this.z;
                                    final int n60 = n13;
                                    final int n61;
                                    Label_1786: {
                                        if (!i) {
                                            if (z4 > n60) {
                                                n61 = n11;
                                                if (i) {
                                                    break Label_1786;
                                                }
                                                if (n61 < 0) {
                                                    final int n62 = 0;
                                                    System.arraycopy(array, n62, this.g, n62, width * (this.z - n13) - n62);
                                                    final int n63 = width * (this.z - n13);
                                                }
                                            }
                                            final int n64 = this.z - n13;
                                        }
                                        n17 = z4 * n60;
                                    }
                                    int n65 = n61;
                                    while (true) {
                                        while (n65 > 0) {
                                            final int n66 = n17 + (n65 * n15 >> 10) + this.y;
                                            System.arraycopy(array, n66, this.g, n66, n17 + width - n66);
                                            n17 += width;
                                            --n65;
                                            if (!i) {
                                                if (i) {
                                                    break;
                                                }
                                                continue;
                                            }
                                            else {
                                                while (n65 >= n14) {
                                                    final int n67 = n17 + (n65 * n16 >> 10) + this.y;
                                                    System.arraycopy(array, n67, this.g, n67, n17 + width - n67);
                                                    n17 += width;
                                                    --n65;
                                                    if (i) {
                                                        break Label_2260;
                                                    }
                                                    if (i) {
                                                        break;
                                                    }
                                                }
                                                f5 = this;
                                                if (i) {
                                                    break Label_2261;
                                                }
                                                if (this.z + 1 - height >= n14) {
                                                    break Label_2260;
                                                }
                                                final int n68 = n12;
                                                if (!i && n68 >= 0) {
                                                    break Label_2260;
                                                }
                                                final int n69 = n68;
                                                System.arraycopy(array, n69, this.g, n69, width * height - n69);
                                                if (i) {
                                                    break Label_1988;
                                                }
                                                break Label_2260;
                                            }
                                        }
                                        n65 = 0;
                                        continue;
                                    }
                                }
                            }
                            n21 = (z = this.z);
                            final int n70;
                            n59 = (n70 = n14);
                        }
                        final int n71;
                        Label_2069: {
                            if (!i) {
                                if (z > n58) {
                                    n71 = n12;
                                    if (i) {
                                        break Label_2069;
                                    }
                                    if (n71 > 0) {
                                        final int n72 = 0;
                                        System.arraycopy(array, n72, this.g, n72, width * (this.z - n14) - n72);
                                        final int n73 = width * (this.z - n14);
                                    }
                                }
                                n21 = width;
                                n59 = this.z - n14;
                            }
                            n17 = n21 * n59;
                        }
                        int n74 = n71;
                        while (true) {
                            while (n74 > 0) {
                                final int n75 = n17;
                                System.arraycopy(array, n75, this.g, n75, n17 + (n74 * n16 >> 10) + this.y - n75);
                                n17 += width;
                                --n74;
                                if (!i) {
                                    if (i) {
                                        break;
                                    }
                                    continue;
                                }
                                else {
                                    while (n74 >= n13) {
                                        final int n76 = n17;
                                        System.arraycopy(array, n76, this.g, n76, n17 + (n74 * n15 >> 10) + this.y - n76);
                                        n17 += width;
                                        --n74;
                                        if (i) {
                                            break Label_2260;
                                        }
                                        if (i) {
                                            break;
                                        }
                                    }
                                    f5 = this;
                                    if (i) {
                                        break Label_2261;
                                    }
                                    if (this.z + 1 - height >= n13) {
                                        break Label_2260;
                                    }
                                    final int n77 = n11;
                                    if (!i && n77 <= 0) {
                                        break Label_2260;
                                    }
                                    final int n78 = n77;
                                    System.arraycopy(array, n78, this.g, n78, width * height - n78);
                                    break Label_2260;
                                }
                            }
                            n74 = 0;
                            continue;
                        }
                    }
                }
                f5 = this;
            }
        }
        f5.h.newPixels(0, 0, this.a.width, this.a.height, false);
    }
    
    private void b(int n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final int n2 = n;
        if (!i) {
            if (n2 >= 0) {
                int j = 0;
                while (j < 256) {
                    this.b[j] = j + ((255 - j) * n >> 8);
                    ++j;
                    if (i) {
                        return;
                    }
                    if (i) {
                        break;
                    }
                }
                if (!i) {
                    return;
                }
            }
            n += 256;
        }
        int k = n2;
        while (k < 256) {
            this.b[k] = k * n >> 8;
            ++k;
            if (i) {
                break;
            }
        }
    }
    
    private double a(double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final double n2 = 6.283185307179586;
        double n3 = 0.0;
        while (n > n2) {
            n -= n2;
            if (i) {
                return n3;
            }
            if (i) {
                break;
            }
        }
        while (n < 0.0) {
            n3 = n + n2;
            if (i) {
                break;
            }
            n = n3;
            if (i) {
                break;
            }
        }
        return n3;
    }
    
    public static void a(final String[] array) {
    }
}
