import com.daysofwonder.applet.z;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.Rectangle;
import com.daysofwonder.tt.g;
import com.daysofwonder.tt.h;
import com.daysofwonder.tt.a;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.f;
import com.daysofwonder.applet.aL;
import com.daysofwonder.util.t;
import java.awt.Color;
import com.daysofwonder.tt.e;
import java.util.Vector;
import com.daysofwonder.util.G;

// 
// Decompiled by Procyon v0.5.30
// 

public class R
{
    protected int d;
    protected int e;
    protected G f;
    protected G g;
    protected G h;
    protected Vector i;
    protected Vector j;
    protected Vector k;
    protected boolean l;
    protected int[] m;
    protected e n;
    protected int o;
    protected int[] p;
    protected int[] q;
    protected int[] r;
    protected int[] s;
    protected int[] t;
    protected int[] u;
    protected int[] v;
    protected byte[] w;
    protected byte[] x;
    protected int y;
    protected int z;
    protected int A;
    protected int B;
    protected int C;
    protected int D;
    protected int E;
    protected int F;
    protected int G;
    protected int H;
    protected int I;
    protected int J;
    protected int K;
    protected int L;
    protected int M;
    protected int N;
    protected int O;
    protected boolean P;
    protected int Q;
    protected double R;
    protected int[] S;
    protected int T;
    protected int[] U;
    
    public R(final int c, final int d, final int y, final int z, final Vector vector, final int c2, final int d2, final int q, final double r, final int o) {
        this.f = new G();
        this.g = new G();
        this.h = new G();
        this.l = true;
        this.U = new int[] { 0, -1, -1, -1, -1, 1, -1, -1, -1, -1, 2, -1, -1, -1, -1, 3, -1, -1, -1, -1, 4, -1, -1, -1, -1, 5, -1, -1, -1, -1, 6, -1, -1, -1, -1, 7, -1, -1, -1, -1, 8, -1, -1, -1, -1, 9, -1, -1, -1, -1, 10, -1, -1, -1, -1, 11, -1, -1, -1, -1, 12, -1, -1, -1, -1, 13, -1, -1, -1, -1, 14, -1, -1, -1, -1, 15, -1, -1, -1, -1, 16, -1, -1, -1, -1, 17, -1, -1, -1, -1, 18, -1, -1, -1, -1, 19, -1, -1, -1, -1, 20, -1, -1, -1, -1, 21, -1, -1, -1, -1, 22, -1, -1, -1, -1, 23, -1, -1, -1, -1, 24, -1, -1, -1, -1, 25, -1, -1, -1, -1, 26, -1, -1, -1, -1, 27, -1, -1, -1, -1, 28, -1, -1, -1, -1, 29, -1, -1, -1, -1, 30, -1, -1, -1, -1, 31, -1, -1, -1, -1, 32, -1, -1, -1, -1, 33, -1, -1, -1, -1, 34, -1, -1, -1, -1, 35, -1, -1, -1, -1 };
        this.Q = q;
        this.R = r;
        this.C = c;
        this.D = d;
        this.o = o;
        this.p = new int[vector.size()];
        this.q = new int[vector.size()];
        this.r = new int[vector.size()];
        for (int i = 0; i < this.p.length; ++i) {
            final Color color = vector.elementAt(i);
            this.p[i] = color.getRed();
            this.q[i] = color.getGreen();
            this.r[i] = color.getBlue();
            com.daysofwonder.util.t.a("PlayerColor RGB: " + this.p[i] + "," + this.q[i] + "," + this.r[i]);
            final int a = aL.a(this.p[i], this.q[i], this.r[i]);
            this.p[i] = (a & 0xFF0000) >> 16;
            this.q[i] = (a & 0xFF00) >> 8;
            this.r[i] = (a & 0xFF);
            com.daysofwonder.util.t.a("PlayerColor HLS: " + this.p[i] + "," + this.q[i] + "," + this.r[i]);
        }
        this.y = y;
        this.z = z;
        this.A = y >> 1;
        this.B = z >> 1;
        this.C = c2;
        this.D = d2;
    }
    
    public int c() {
        return this.o;
    }
    
    public synchronized void a(final int[] s, final int t) {
        this.T = t;
        this.S = s;
    }
    
    public void a(final G g) {
        for (int i = 0; i < g.a(); ++i) {
            final f f = (f)g.b(i);
            final c f2 = f.f();
            final c g2 = f.g();
            if (!this.g.a(f2)) {
                this.g.c(f2);
            }
            if (!this.g.a(g2)) {
                this.g.c(g2);
            }
        }
    }
    
    public void d() {
        this.g.b();
    }
    
    public void a(final f f) {
        final c f2 = f.f();
        final c g = f.g();
        if (!this.h.a(f2)) {
            this.h.c(f2);
        }
        if (!this.h.a(g)) {
            this.h.c(g);
        }
    }
    
    public void b(final f f) {
        final c f2 = f.f();
        final c g = f.g();
        this.h.d(f2);
        this.h.d(g);
    }
    
    protected int[] a(final int[] array) {
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = (array[i] & 0xFF);
        }
        return array2;
    }
    
    protected Vector a(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 / n;
        final int n6 = n4 / n2;
        final Vector vector = new Vector<byte[]>(n5 * n6);
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                final byte[] array2 = new byte[n * n2];
                final int n7 = j * n + i * n2 * n3;
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < n; ++l) {
                        array2[l + k * n] = (byte)(array[l + k * n3 + n7] & 0xFF);
                    }
                }
                vector.addElement(array2);
            }
        }
        return vector;
    }
    
    protected Vector b(final int[] array, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n3 / n;
        final int n6 = n4 / n2;
        final Vector vector = new Vector<int[]>(n5 * n6);
        for (int i = 0; i < n6; ++i) {
            for (int j = 0; j < n5; ++j) {
                final int[] array2 = new int[n * n2];
                final int n7 = j * n + i * n2 * n3;
                for (int k = 0; k < n2; ++k) {
                    for (int l = 0; l < n; ++l) {
                        array2[l + k * n] = (array[l + k * n3 + n7] & 0xFF) * 255 / 240;
                    }
                }
                vector.addElement(array2);
            }
        }
        return vector;
    }
    
    public void a(final int[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array[j + i * n] = (array[j + i * n] & 0xFF) * 255 / 240;
            }
        }
    }
    
    public byte[] b(final int[] array, final int n, final int n2) {
        final byte[] array2 = new byte[n * n2];
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                array2[j + i * n] = (byte)(array[j + i * n] & 0xFF);
            }
        }
        return array2;
    }
    
    public synchronized int a(int n, int n2) {
        n += this.N;
        n2 += this.O;
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n >= this.d) {
            n = this.d - 1;
        }
        if (n2 >= this.e) {
            n2 = this.e - 1;
        }
        final int n3 = this.m[n + n2 * this.d] >> 24 & 0xFF;
        return (n3 == this.M) ? -1 : n3;
    }
    
    public void a(final o o) {
        final G k = o.k();
        for (int i = 0; i < k.a(); ++i) {
            final a a = (a)k.b(i);
            for (int j = 0; j < this.f.a(); ++j) {
                final Object b = this.f.b(j);
                if (b instanceof a && b == a) {
                    this.f.c(j);
                }
            }
        }
    }
    
    public void a(final o o, final int n) {
        final G k = o.k();
        for (int i = 0; i < k.a(); ++i) {
            final a a = (a)k.b(i);
            a.c(n);
            com.daysofwonder.util.t.a("ZL: " + this.Q + " candidate:" + a.d());
            if (this.Q > 0 && a.d() < 0) {
                a.a((int)(a.b() * this.R));
                a.b((int)(a.c() * this.R));
                com.daysofwonder.util.t.a("ZL: " + this.Q + "Adding train candidate:" + a.d() + ", " + a.d());
            }
            else {
                com.daysofwonder.util.t.a("ZL: " + this.Q + "Adding train candidate:" + a.b() + ", " + a.b());
            }
            this.a(a);
        }
    }
    
    public void a(final c c, final int n) {
        final g g = new g(c);
        g.c(n);
        if (this.Q > 0 && g.d() < 0) {
            g.a((int)(g.b() * this.R));
            g.b((int)(g.c() * this.R));
        }
        this.a(g);
    }
    
    protected void a(final h h) {
        final int a = this.f.a();
        int n = -1;
        int i = 0;
        int n2 = a - 1;
        while (i <= n2) {
            final int n3 = i + n2 >> 1;
            final int a2 = ((h)this.f.b(n3)).a(h);
            if (a2 < 0) {
                i = n3 + 1;
            }
            else {
                if (a2 <= 0) {
                    n = n3;
                    break;
                }
                n2 = n3 - 1;
            }
        }
        if (n < 0) {
            n = i;
        }
        this.f.b(n, h);
    }
    
    public synchronized void b(final int n, final int n2) {
        final Rectangle rectangle = new Rectangle(this.n.b(n).b(this.Q));
        if (aL.d(rectangle, new Rectangle(this.N, this.O, this.C, this.D))) {
            for (int i = rectangle.y; i < rectangle.y + rectangle.height; ++i) {
                final int n3 = i * this.d;
                final int n4 = (i - this.O) * this.T;
                for (int j = rectangle.x, n5 = j - this.N; j < rectangle.x + rectangle.width; ++j, ++n5) {
                    if ((this.m[j + n3] >> 24 & 0xFF) == n) {
                        this.S[n5 + n4] = -16777216 + (aL.a(this.S[n5 + n4], n2) & 0xFFFFFF);
                    }
                }
            }
            this.l = true;
        }
    }
    
    public void a(final int n) {
        com.daysofwonder.util.t.a("hilite: " + n);
        this.b(n, 5247056);
    }
    
    public Point b(final o o) {
        final Point point = new Point();
        final Rectangle b = o.b(this.Q);
        point.x = b.x + (b.width >> 1);
        point.y = b.y + (b.height >> 1);
        return point;
    }
    
    public void b(final int n) {
        final Rectangle rectangle = new Rectangle(this.n.b(n).b(this.Q));
        if (aL.d(rectangle, new Rectangle(this.N, this.O, this.C, this.D))) {
            for (int i = rectangle.y; i < rectangle.y + rectangle.height; ++i) {
                final int n2 = i * this.d;
                final int n3 = (i - this.O) * this.T;
                for (int j = rectangle.x, n4 = j - this.N; j < rectangle.x + rectangle.width; ++j, ++n4) {
                    if ((this.m[j + n2] >> 24 & 0xFF) == n) {
                        this.S[n4 + n3] = -16777216 + (this.m[j + n2] & 0xFFFFFF);
                    }
                }
            }
            this.l = true;
        }
    }
    
    public c c(int n, int n2) {
        n += this.N;
        n2 += this.O;
        if (this.Q == 0) {
            final Enumeration k = this.n.k();
            while (k.hasMoreElements()) {
                final c c = k.nextElement();
                final Point c2 = c.c();
                if (c2 != null && n >= c2.x - 17 && n <= c2.x + 17 && n2 >= c2.y - 17 && n2 <= c2.y + 17) {
                    return c;
                }
            }
        }
        else {
            final Enumeration i = this.n.k();
            while (i.hasMoreElements()) {
                final c c3 = i.nextElement();
                final Point a = c3.a(this.o);
                if (a != null && n >= a.x - (this.G + 2) && n <= a.x + (this.G + 2) && n2 >= a.y - (this.G + 2) && n2 <= a.y + (this.G + 2)) {
                    return c3;
                }
            }
        }
        return null;
    }
    
    public synchronized void a(final Rectangle rectangle) {
        System.currentTimeMillis();
        this.l = true;
        if (this.S == null) {
            return;
        }
        for (int i = rectangle.y; i < rectangle.y + rectangle.height; ++i) {
            for (int j = rectangle.x; j < rectangle.x + rectangle.width; ++j) {
                this.S[j - this.N + (i - this.O) * this.T] = -16777216 + (this.m[j + i * this.d] & 0xFFFFFF);
            }
        }
        if (this.Q == 0) {
            final Rectangle rectangle2 = new Rectangle();
            for (int k = 0; k < this.f.a(); ++k) {
                final h h = (h)this.f.b(k);
                if (h instanceof a) {
                    final a a = (a)h;
                    final Rectangle rectangle3 = rectangle2;
                    final int x = a.b() - this.A;
                    rectangle3.x = x;
                    final int n = x;
                    final Rectangle rectangle4 = rectangle2;
                    final int y = a.c() - this.B;
                    rectangle4.y = y;
                    final int n2 = y;
                    rectangle2.width = this.y;
                    rectangle2.height = this.z;
                    if (aL.d(rectangle2, rectangle)) {
                        final int f = a.f();
                        final int n3 = this.U[a.a()];
                        if (n3 != -1) {
                            this.a((int[])this.i.elementAt(n3), (byte[])this.k.elementAt(n3), (byte[])this.j.elementAt(n3), this.p[f], this.q[f], this.r[f], rectangle2, this.y, n, n2);
                        }
                    }
                }
            }
            for (int l = 0; l < this.g.a(); ++l) {
                final Point c = ((c)this.g.b(l)).c();
                if (c != null) {
                    final Rectangle rectangle5 = rectangle2;
                    final int x2 = c.x - this.G;
                    rectangle5.x = x2;
                    final int n4 = x2;
                    final Rectangle rectangle6 = rectangle2;
                    final int y2 = c.y - this.H;
                    rectangle6.y = y2;
                    final int n5 = y2;
                    rectangle2.width = this.E;
                    rectangle2.height = this.F;
                    if (aL.d(rectangle2, rectangle)) {
                        this.a(this.s, this.t, rectangle2, this.E, n4, n5);
                    }
                }
            }
            for (int n6 = 0; n6 < this.h.a(); ++n6) {
                final Point c2 = ((c)this.h.b(n6)).c();
                if (c2 != null) {
                    final Rectangle rectangle7 = rectangle2;
                    final int x3 = c2.x - this.G;
                    rectangle7.x = x3;
                    final int n7 = x3;
                    final Rectangle rectangle8 = rectangle2;
                    final int y3 = c2.y - this.H;
                    rectangle8.y = y3;
                    final int n8 = y3;
                    rectangle2.width = this.E;
                    rectangle2.height = this.F;
                    if (aL.d(rectangle2, rectangle)) {
                        this.a(this.s, this.t, rectangle2, this.E, n7, n8);
                    }
                }
            }
            for (int n9 = 0; n9 < this.f.a(); ++n9) {
                final h h2 = (h)this.f.b(n9);
                if (h2 instanceof g) {
                    final g g = (g)h2;
                    final Rectangle rectangle9 = rectangle2;
                    final int x4 = g.b() - this.K;
                    rectangle9.x = x4;
                    final int n10 = x4;
                    final Rectangle rectangle10 = rectangle2;
                    final int y4 = g.c() - this.L;
                    rectangle10.y = y4;
                    final int n11 = y4;
                    rectangle2.width = this.I;
                    rectangle2.height = this.J;
                    if (aL.d(rectangle2, rectangle)) {
                        final int f2 = g.f();
                        this.a(this.v, this.w, this.x, this.p[f2], this.q[f2], this.r[f2], rectangle2, this.I, n10, n11);
                    }
                }
            }
        }
        else {
            final Rectangle rectangle11 = new Rectangle();
            for (int n12 = 0; n12 < this.f.a(); ++n12) {
                final h h3 = (h)this.f.b(n12);
                if (h3 instanceof a) {
                    final a a2 = (a)h3;
                    final Rectangle rectangle12 = rectangle11;
                    final int x5 = a2.d() - this.A;
                    rectangle12.x = x5;
                    final int n13 = x5;
                    final Rectangle rectangle13 = rectangle11;
                    final int y5 = a2.e() - this.B;
                    rectangle13.y = y5;
                    final int n14 = y5;
                    rectangle11.width = this.y;
                    rectangle11.height = this.z;
                    if (aL.d(rectangle11, rectangle)) {
                        final int f3 = a2.f();
                        final int n15 = this.U[a2.a()];
                        if (n15 != -1) {
                            this.a((int[])this.i.elementAt(n15), (byte[])this.k.elementAt(n15), (byte[])this.j.elementAt(n15), this.p[f3], this.q[f3], this.r[f3], rectangle11, this.y, n13, n14);
                        }
                    }
                }
            }
            for (int n16 = 0; n16 < this.g.a(); ++n16) {
                final c c3 = (c)this.g.b(n16);
                if (c3 == null) {
                    com.daysofwonder.util.t.a("Unknown CITY: " + n16);
                }
                final Point a3 = c3.a(this.o);
                if (a3 != null) {
                    final Rectangle rectangle14 = rectangle11;
                    final int x6 = a3.x - this.G;
                    rectangle14.x = x6;
                    final int n17 = x6;
                    final Rectangle rectangle15 = rectangle11;
                    final int y6 = a3.y - this.H;
                    rectangle15.y = y6;
                    final int n18 = y6;
                    rectangle11.width = this.E;
                    rectangle11.height = this.F;
                    if (aL.d(rectangle11, rectangle)) {
                        this.a(this.s, this.t, rectangle11, this.E, n17, n18);
                    }
                }
            }
            for (int n19 = 0; n19 < this.h.a(); ++n19) {
                final Point a4 = ((c)this.h.b(n19)).a(this.o);
                if (a4 != null) {
                    final Rectangle rectangle16 = rectangle11;
                    final int x7 = a4.x - this.G;
                    rectangle16.x = x7;
                    final int n20 = x7;
                    final Rectangle rectangle17 = rectangle11;
                    final int y7 = a4.y - this.H;
                    rectangle17.y = y7;
                    final int n21 = y7;
                    rectangle11.width = this.E;
                    rectangle11.height = this.F;
                    if (aL.d(rectangle11, rectangle)) {
                        this.a(this.u, this.t, rectangle11, this.E, n20, n21);
                    }
                }
            }
            for (int n22 = 0; n22 < this.f.a(); ++n22) {
                final h h4 = (h)this.f.b(n22);
                if (h4 instanceof g) {
                    final g g2 = (g)h4;
                    final Rectangle rectangle18 = rectangle11;
                    final int x8 = g2.d() - this.K;
                    rectangle18.x = x8;
                    final int n23 = x8;
                    final Rectangle rectangle19 = rectangle11;
                    final int y8 = g2.e() - this.L;
                    rectangle19.y = y8;
                    final int n24 = y8;
                    rectangle11.width = this.I;
                    rectangle11.height = this.J;
                    if (aL.d(rectangle11, rectangle)) {
                        final int f4 = g2.f();
                        this.a(this.v, this.w, this.x, this.p[f4], this.q[f4], this.r[f4], rectangle11, this.I, n23, n24);
                    }
                }
            }
        }
        System.currentTimeMillis();
    }
    
    public synchronized void e() {
        this.a(new Rectangle(this.N, this.O, this.C, this.D));
    }
    
    protected void a(final int[] array, final byte[] array2, final byte[] array3, final int n, final int n2, final int n3, final Rectangle rectangle, final int n4, int n5, int n6) {
        rectangle.x -= this.N;
        rectangle.y -= this.O;
        n5 -= this.N;
        n6 -= this.O;
        n5 = rectangle.x - n5;
        n6 = rectangle.y - n6;
        final int n7 = n5 + rectangle.width;
        final int n8 = n6 + rectangle.height;
        int y = rectangle.y;
        for (int i = n6; i < n8; ++i) {
            for (int n9 = rectangle.x + y * this.T, n10 = n5 + i * n4, j = n5; j < n7; ++j, ++n9, ++n10) {
                final int n11 = this.S[n9];
                final int b = aL.b(n, array[n10], n3);
                final int n12 = b & 0xFF0000;
                final int n13 = b & 0xFF00;
                final int n14 = b & 0xFF;
                final int n15 = n11 & 0xFF0000;
                final int n16 = n11 & 0xFF00;
                final int n17 = n11 & 0xFF;
                final int n18 = array3[n10] & 0xFF;
                final int n19 = n15 * n18 >>> 8;
                final int n20 = n16 * n18 >> 8;
                final int n21 = n17 * n18 >> 8;
                final int n22 = array2[n10] & 0xFF;
                this.S[n9] = -16777216 + (n19 + ((n12 - n19) * n22 >> 8) & 0xFF0000) + (n20 + ((n13 - n20) * n22 >> 8) & 0xFF00) + (n21 + ((n14 - n21) * n22 >> 8) & 0xFF);
            }
            ++y;
        }
    }
    
    protected void a(final int[] array, final int[] array2, final Rectangle rectangle, final int n, int n2, int n3) {
        rectangle.x -= this.N;
        rectangle.y -= this.O;
        n2 -= this.N;
        n3 -= this.O;
        n2 = rectangle.x - n2;
        n3 = rectangle.y - n3;
        final int n4 = n2 + rectangle.width;
        final int n5 = n3 + rectangle.height;
        int y = rectangle.y;
        for (int i = n3; i < n5; ++i) {
            for (int n6 = rectangle.x + y * this.T, n7 = n2 + i * n, j = n2; j < n4; ++j, ++n6, ++n7) {
                final int n8 = this.S[n6];
                final int n9 = array[n7];
                final int n10 = array2[n7];
                final int n11 = n9 & 0xFF0000;
                final int n12 = n9 & 0xFF00;
                final int n13 = n9 & 0xFF;
                final int n14 = n8 & 0xFF0000;
                final int n15 = n8 & 0xFF00;
                final int n16 = n8 & 0xFF;
                this.S[n6] = -16777216 + (n14 + ((n11 - n14) * n10 >> 8) & 0xFF0000) + (n15 + ((n12 - n15) * n10 >> 8) & 0xFF00) + (n16 + ((n13 - n16) * n10 >> 8) & 0xFF);
            }
            ++y;
        }
    }
    
    public synchronized void a(final com.daysofwonder.b.a a, final int n, final int n2, final int n3, final int n4) {
        if (!this.P) {
            return;
        }
    }
    
    public synchronized void a(final z z, final int n, final int n2, final int n3, final int n4) {
        if (!this.P) {
            return;
        }
    }
    
    public synchronized void d(int width, int height) {
        final int n = this.N;
        final int o = this.O;
        this.N += width;
        this.O += height;
        if (this.N > this.d - this.C) {
            this.N = this.d - this.C;
            width = this.N - n;
        }
        if (this.O > this.e - this.D) {
            final int o2 = this.O;
            this.O = this.e - this.D;
            height = this.O - o;
        }
        if (this.N < 0) {
            this.N = 0;
            width = this.N - n;
        }
        if (this.O < 0) {
            this.O = 0;
            height = this.O - o;
        }
        if (n != this.N || o != this.O) {
            this.l = true;
            if (height >= 0) {
                System.arraycopy(this.S, height * this.T, this.S, 0, (this.D - height) * this.T);
            }
            else {
                System.arraycopy(this.S, 0, this.S, -height * this.T, (this.D + height) * this.T);
            }
            if (width >= 0) {
                for (int i = 0; i < this.D; ++i) {
                    System.arraycopy(this.S, width + i * this.T, this.S, i * this.T, this.T - width);
                }
            }
            else {
                for (int j = 0; j < this.D; ++j) {
                    System.arraycopy(this.S, j * this.T, this.S, -width + j * this.T, this.T + width);
                }
            }
            final Rectangle rectangle = new Rectangle();
            if (width >= 0) {
                rectangle.x = this.N + this.C - width;
                rectangle.y = this.O;
                rectangle.width = width;
                rectangle.height = this.D;
            }
            else {
                rectangle.x = this.N;
                rectangle.y = this.O;
                rectangle.width = -width;
                rectangle.height = this.D;
            }
            this.a(rectangle);
            if (height >= 0) {
                rectangle.x = this.N;
                rectangle.y = this.O + this.D - height;
                rectangle.width = this.C;
                rectangle.height = height;
            }
            else {
                rectangle.x = this.N;
                rectangle.y = this.O;
                rectangle.width = this.C;
                rectangle.height = -height;
            }
            this.a(rectangle);
        }
    }
    
    public synchronized boolean f() {
        return this.P && this.S != null;
    }
}
