import java.util.Enumeration;
import java.util.Vector;
import java.awt.Color;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Polygon;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_c extends rp_dC
{
    public rp_J a;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    private int[] a;
    private int[] b;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    public int f;
    public int g;
    private Rectangle a;
    private rp_bV a;
    private rp_bV[] a;
    
    public rp_c(final int h, final rp_J a, final int b, final int c, final int d, final int e, final int a2) {
        this.a = new int[4];
        this.b = new int[4];
        this.k = 0;
        this.f = -1;
        this.g = -1;
        this.a = null;
        (this.a = new rp_bV("BASE", rp_dc.a, rp_bV.a, rp_bV.c, 0, 0, 0, 0)).a(this);
        this.a = new rp_bV[1];
        this.h = h;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.a = a2;
        this.a(1, true);
        this.a(4096, true);
        this.a(16, true);
        this.b();
    }
    
    public final Object clone() {
        final rp_c rp_c;
        (rp_c = (rp_c)super.clone()).a = (rp_bV)this.a.clone();
        return rp_c;
    }
    
    public final Rectangle a(final int n) {
        if (n == 0 && this.a != null) {
            return this.a;
        }
        final Rectangle rectangle;
        (rectangle = new Rectangle()).setLocation(this.b, this.c);
        rectangle.add(this.d, this.e);
        if (this.b == this.d) {
            rectangle.grow(1, 0);
        }
        if (this.c == this.e) {
            rectangle.grow(0, 1);
        }
        return rectangle;
    }
    
    public final void a() {
        this.a.a();
    }
    
    public final int a() {
        return 1;
    }
    
    public void b() {
        this.a.n = this.b;
        this.a.o = this.c;
        this.a.p = this.d;
        this.a.q = this.e;
    }
    
    public final Point a() {
        return new Point((this.b + this.d) / 2, (this.c + this.e) / 2);
    }
    
    public final Point b() {
        if (this.k == 3) {
            return new Point(this.p + this.n, this.q + this.o);
        }
        if (this.k == 1) {
            return new Point(this.b + this.n, this.c + this.o);
        }
        return new Point(this.b, this.c);
    }
    
    public final Point c() {
        if (this.k == 3) {
            return new Point(this.p + this.n, this.q + this.o);
        }
        if (this.k == 2) {
            return new Point(this.d + this.n, this.e + this.o);
        }
        return new Point(this.d, this.e);
    }
    
    public final void a(final Point point) {
        this.d = point.x;
        this.e = point.y;
        this.b();
    }
    
    public final int b() {
        return this.a;
    }
    
    public final int c() {
        if (this.b == this.d) {
            return Math.abs(this.e - this.c);
        }
        return Math.abs(this.d - this.b);
    }
    
    public final rp_bV[] a() {
        this.a[0] = this.a;
        return this.a;
    }
    
    private void a(int n, int n2, final int n3, final int n4) {
        final float n5 = n3 - n;
        final float n6 = n4 - n2;
        final float n7 = (float)rp_C.a(n5, n6);
        float n8 = 0.0f;
        float n9 = 0.0f;
        if (n7 > 10.0f) {
            n8 = n5 / n7;
            n9 = n6 / n7;
        }
        final float n10 = -this.a / 2 * n9;
        final float n11 = this.a / 2 * n8;
        this.a[0] = Math.round(n + n10);
        this.b[0] = Math.round(n2 + n11);
        this.a[1] = Math.round(n - n10);
        this.b[1] = Math.round(n2 - n11);
        this.a[2] = Math.round(n3 - n10);
        this.b[2] = Math.round(n4 - n11);
        this.a[3] = Math.round(n3 + n10);
        this.b[3] = Math.round(n4 + n11);
        n = (n + n3) / 2;
        n2 = (n2 + n4) / 2;
        final float n12 = -this.a * n9;
        final float n13 = 1.5f * this.a * n8;
        this.i = Math.round(n - n12);
        this.j = Math.round(n2 - n13);
    }
    
    public final boolean a(final Frame frame) {
        return false;
    }
    
    public final int a(final int n, final int n2) {
        if (rp_C.a(this.b, this.c, n, n2) < this.a) {
            return 4;
        }
        if (rp_C.a(this.d, this.e, n, n2) < this.a) {
            return 4;
        }
        this.a(this.b, this.c, this.d, this.e);
        if (new Polygon(this.a, this.b, 4).contains(n, n2)) {
            return 4;
        }
        return 0;
    }
    
    public final Cursor a(final int n, final int n2) {
        return Cursor.getPredefinedCursor(13);
    }
    
    private void c(final int k) {
        final boolean b = false;
        this.q = (b ? 1 : 0);
        this.p = (b ? 1 : 0);
        final boolean b2 = false;
        this.o = (b2 ? 1 : 0);
        this.n = (b2 ? 1 : 0);
        final boolean b3 = false;
        this.m = (b3 ? 1 : 0);
        this.l = (b3 ? 1 : 0);
        this.k = k;
    }
    
    public final void a(final rp_N rp_N, final int p3, final int q) {
        this.p = p3;
        this.q = q;
        final rp_c rp_c = this;
        final rp_c rp_c2 = this;
        final boolean b = false;
        rp_c2.o = (b ? 1 : 0);
        rp_c.n = (b ? 1 : 0);
        final rp_c rp_c3 = this;
        final rp_c rp_c4 = this;
        final boolean b2 = false;
        rp_c4.m = (b2 ? 1 : 0);
        rp_c3.l = (b2 ? 1 : 0);
        if (rp_C.a(this.b, this.c, p3, q) < this.a) {
            this.k = 1;
            this = (rp_c)rp_N.a(this.f);
            rp_N.b(this, true);
            this.c(2);
            return;
        }
        if (rp_C.a(this.d, this.e, p3, q) < this.a) {
            this.k = 2;
            this = (rp_c)rp_N.a(this.g);
            rp_N.b(this, true);
            this.c(1);
            return;
        }
        this.k = 3;
    }
    
    public final rp_dt a(final boolean b) {
        rp_dt rp_dt = null;
        if (b) {
            switch (this.k) {
                case 3: {
                    this.k = 0;
                    rp_dt = new rp_dM(this, new Point(this.p + this.n, this.q + this.o));
                    break;
                }
                case 1: {
                    this.k = 0;
                    rp_dt = new rp_dL(this, 1, this.n, this.o);
                    break;
                }
                case 2: {
                    this.k = 0;
                    rp_dt = new rp_dL(this, 2, this.n, this.o);
                    break;
                }
            }
        }
        this.k = 0;
        return rp_dt;
    }
    
    public final void a(int n, int n2, final Dimension dimension, final boolean b) {
        this.l += n;
        this.m += n2;
        if (!b) {
            switch (this.k) {
                case 0: {}
                case 1: {
                    n = this.d - (this.b + this.l);
                    n2 = this.e - (this.c + this.m);
                    a(n, n2, dimension, 1);
                }
                case 2: {
                    n = this.d + this.l - this.b;
                    n2 = this.e + this.m - this.c;
                    a(n, n2, dimension, -1);
                }
                case 3: {
                    n = this.p + this.l - this.b;
                    n2 = this.q + this.m - this.c;
                    a(n, n2, dimension, -1);
                    n = this.d - (this.p + this.l);
                    n2 = this.e - (this.q + this.m);
                    a(n, n2, dimension, 1);
                    break;
                }
            }
        }
    }
    
    private static void a(final int n, final int n2, final Dimension dimension, final int n3) {
        if (n == 0) {
            return;
        }
        final double atan;
        final double abs = Math.abs(((atan = Math.atan(n2 / n)) + 1.5707963267948966) % 3.141592653589793);
        if (Math.abs(Math.min(abs, 3.141592653589793 - abs)) < 0.08726646259971647) {
            dimension.width = n3 * n;
        }
        final double abs2 = Math.abs(atan % 3.141592653589793);
        if (Math.abs(Math.min(abs2, 3.141592653589793 - abs2)) < 0.08726646259971647) {
            dimension.height = n3 * n2;
        }
    }
    
    public final int d() {
        return 115;
    }
    
    public final void a(final int n, final int n2, final double n3) {
    }
    
    public final void a(final int n, final int n2, final double n3, final boolean b) {
    }
    
    public final void a(final int n, final int n2) {
        this.n = this.l + n;
        this.o = this.m + n2;
    }
    
    public final void b(final int n, final int n2) {
        this.b += n;
        this.c += n2;
        this.d += n;
        this.e += n2;
        this.b();
    }
    
    public final void a(final rp_eS rp_eS, final rp_eP rp_eP) {
        ((Graphics2D)rp_eS.a()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rp_eS.b(rp_aJ.a);
        this.a = null;
        Point b = null;
        final rp_c rp_c;
        if ((rp_c = (rp_c)this.a.a(this.f)) != null) {
            b = rp_c.b();
        }
        Point c = null;
        final rp_c rp_c2;
        if ((rp_c2 = (rp_c)this.a.a(this.g)) != null) {
            c = rp_c2.c();
        }
        switch (this.k) {
            case 0: {
                this.a(this.b, this.c, this.d, this.e);
                if (!a(rp_eS, this.a, this.a, b, c)) {
                    this.a(rp_eS, this.b, this.c, this.d, this.e, b, c, rp_aJ.a);
                }
                this.a(rp_eS, this.b, this.c, this.d, this.e);
                this.a(rp_eS, rp_eP, 0, this.c, this.e);
            }
            case 3: {
                final int n = this.p + this.n;
                final int n2 = this.q + this.o;
                this.a(this.b, this.c, n, n2);
                this.a(rp_eS, this.b, this.c, n, n2, b, new Point(this.d, this.e), rp_aJ.s);
                this.a(rp_eS, this.b, this.c, n, n2);
                this.a(rp_eS, rp_eP, 1, this.c, n2);
                this.a(n, n2, this.d, this.e);
                this.a(rp_eS, n, n2, this.d, this.e, new Point(this.b, this.c), c, rp_aJ.s);
                this.a(rp_eS, n, n2, this.d, this.e);
                this.a(rp_eS, rp_eP, 2, n2, this.e);
            }
            case 1: {
                final int n3 = this.b + this.n;
                final int n4 = this.c + this.o;
                this.a(n3, n4, this.d, this.e);
                this.a(rp_eS, n3, n4, this.d, this.e, b, c, rp_aJ.s);
                this.a(rp_eS, n3, n4, this.d, this.e);
                this.a(rp_eS, rp_eP, 0, n4, this.e);
            }
            case 2: {
                final int n5 = this.d + this.n;
                final int n6 = this.e + this.o;
                this.a(this.b, this.c, n5, n6);
                this.a(rp_eS, this.b, this.c, n5, n6, b, c, rp_aJ.s);
                this.a(rp_eS, this.b, this.c, n5, n6);
                this.a(rp_eS, rp_eP, 0, this.c, n6);
                break;
            }
        }
    }
    
    private void a(final rp_eS rp_eS, final rp_eP rp_eP, int n, int a, int b) {
        if (rp_eP != null && (!rp_eS.a || rp_eS.c)) {
            int n2 = 2;
            if (b == a) {
                n2 = 1;
            }
            if (b > a) {
                n2 = 0;
            }
            final Point point = new Point();
            final Point point2 = new Point();
            if (this.k == 3) {
                final int n3 = n;
                final Point point3 = point;
                final Point point4 = point2;
                final Point point5 = point3;
                n = n3;
                if (n == 1) {
                    point5.x = this.b;
                    point5.y = this.c;
                    point4.x = this.p + this.n;
                    point4.y = this.q + this.o;
                }
                else {
                    point5.x = this.p + this.n;
                    point5.y = this.q + this.o;
                    point4.x = this.d;
                    point4.y = this.e;
                }
                final double n4 = point4.x - point5.x;
                final double n5 = point4.y - point5.y;
                final double a2 = rp_C.a(n4, n5);
                final double n6 = n4 / a2;
                final double n7 = n5 / a2;
                if (n == 1) {
                    final rp_c rp_c;
                    if ((rp_c = (rp_c)this.a.a(this.f)) != null) {
                        final double a3 = a(rp_c, this, this.h, this.f);
                        final Point point6 = point5;
                        point6.x += (int)(n6 * a3);
                        final Point point7 = point5;
                        point7.y += (int)(n7 * a3);
                    }
                    final double a4 = a(this, this, this.f, this.g);
                    final Point point8 = point4;
                    point8.x -= (int)(n6 * a4);
                    final Point point9 = point4;
                    point9.y -= (int)(n7 * a4);
                }
                else {
                    final double a5 = a(this, this, this.f, this.g);
                    final Point point10 = point5;
                    point10.x += (int)(n6 * a5);
                    final Point point11 = point5;
                    point11.y += (int)(n7 * a5);
                    final rp_c rp_c2;
                    if ((rp_c2 = (rp_c)this.a.a(this.g)) != null) {
                        final double a6 = a(this, rp_c2, this.g, this.h);
                        final Point point12 = point4;
                        point12.x -= (int)(n6 * a6);
                        final Point point13 = point4;
                        point13.y -= (int)(n7 * a6);
                    }
                }
                n = (int)Math.round(rp_C.a(point4.x - point5.x, point4.y - point5.y));
            }
            else {
                final Point point14 = point;
                final Point point15 = point2;
                final Point point16 = point14;
                this.a(point16, point15, this.h);
                final double n8 = point15.x - point16.x;
                final double n9 = point15.y - point16.y;
                final double n10 = (float)rp_C.a(n8, n9);
                final double n11 = n8 / n10;
                final double n12 = n9 / n10;
                final rp_c rp_c3;
                if ((rp_c3 = (rp_c)this.a.a(this.f)) != null) {
                    final double a7 = a(rp_c3, this, this.h, this.h);
                    final Point point17 = point16;
                    point17.x += (int)(n11 * a7);
                    final Point point18 = point16;
                    point18.y += (int)(n12 * a7);
                }
                final rp_c rp_c4;
                if ((rp_c4 = (rp_c)this.a.a(this.g)) != null) {
                    final double a8 = a(this, rp_c4, this.h, this.h);
                    final Point point19 = point15;
                    point19.x -= (int)(n11 * a8);
                    final Point point20 = point15;
                    point20.y -= (int)(n12 * a8);
                }
                n = (int)Math.round(rp_C.a(point15.x - point16.x, point15.y - point16.y));
            }
            if (n < 20000) {
                return;
            }
            final rp_cR a9;
            a = ((a9 = rp_eP.a()).a(true) ? 1 : 0);
            b = (a9.b(false) ? 1 : 0);
            final String a10 = a9.a(n);
            final Rectangle a11 = rp_eS.a(a10, this.i, this.j, n2, 2);
            if (this.a == null) {
                this.a = new Rectangle(a11);
            }
            else {
                this.a.add(a11);
            }
            rp_eS.a(a10, this.i, this.j, n2, 2);
            a9.b(b != 0);
            a9.a(a != 0);
        }
    }
    
    private void a(final Point point, final Point point2, final int n) {
        point.x = this.b;
        point.y = this.c;
        point2.x = this.d;
        point2.y = this.e;
        if (this.k == 1) {
            point.x = this.b + this.n;
            point.y = this.c + this.o;
        }
        if (this.k == 2) {
            point2.x = this.d + this.n;
            point2.y = this.e + this.o;
        }
        if (this.k == 3 && n != -1) {
            if (n == this.f) {
                point2.x = this.p + this.n;
                point2.y = this.q + this.o;
            }
            if (n == this.g) {
                point.x = this.p + this.n;
                point.y = this.q + this.o;
            }
        }
    }
    
    private static double a(final rp_c rp_c, final rp_c rp_c2, final int n, final int n2) {
        final double a = rp_c.a(n);
        final double a2 = rp_c2.a(n2);
        final double n3 = rp_c.a / 2.0;
        if (Math.abs(a - a2) < 1.0E-4) {
            return 0.0;
        }
        double n4;
        if ((n4 = a2 - a) < 0.0) {
            n4 += 6.283185307179586;
        }
        final double n5;
        if (Math.abs(n5 = (3.141592653589793 - n4) / 2.0) < 1.0E-4) {
            return n3;
        }
        double n6;
        if ((n6 = n3 / Math.tan(n5)) < -n3) {
            n6 = -n3;
        }
        return n6;
    }
    
    private double a(final int n) {
        final Point point = new Point();
        final Point point2 = new Point();
        this.a(point, point2, n);
        return rp_C.a(point, point2);
    }
    
    private void a(final rp_eS rp_eS, final int n, final int n2, final int n3, final int n4) {
        int n5 = (int)(0.8 * this.a);
        if (!rp_eS.a) {
            rp_eS.b(Color.gray);
            n5 = this.a / 2;
            rp_eS.b(n - n5, n2 - n5, n5 * 2, n5 * 2);
            rp_eS.b(n3 - n5, n4 - n5, n5 * 2, n5 * 2);
        }
        final Rectangle rectangle;
        (rectangle = new Rectangle()).setLocation(n, n2);
        rectangle.add(n3, n4);
        rectangle.grow(n5 * 2, n5 * 2);
        if (this.a == null) {
            this.a = new Rectangle(rectangle);
            return;
        }
        this.a.add(rectangle);
    }
    
    private static void a(final rp_eS rp_eS, final boolean b, final int n, final int n2, final float n3, final float n4, final float n5, int n6, final int n7, final int n8, final int n9, final float n10, final float n11, final Color color) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        final int n12 = (int)(0.5 - n3 * n5);
        final int n13 = (int)(0.5 + n3 * n4);
        if (b) {
            final double n14 = (n9 - n7) / (n8 - n6);
            final int n15 = (int)((n - n6) * n14) + n7;
            n6 = (int)((n2 - n6) * n14) + n7;
            array[0] = Math.round(n + n12 + n4 * n10);
            array2[0] = Math.round(n15 + n13 + n5 * n10);
            array[1] = Math.round(n - n12 - n4 * n10);
            array2[1] = Math.round(n15 - n13 - n5 * n10);
            array[2] = Math.round(n2 - n12 + n4 * n11);
            array2[2] = Math.round(n6 - n13 + n5 * n11);
            array[3] = Math.round(n2 + n12 - n4 * n11);
            array2[3] = Math.round(n6 + n13 - n5 * n11);
        }
        else {
            final double n16 = (n8 - n6) / (n9 - n7);
            final int n17 = (int)((n - n7) * n16) + n6;
            n6 += (int)((n2 - n7) * n16);
            array[0] = Math.round(n17 + n12 + n4 * n10);
            array2[0] = Math.round(n + n13 + n5 * n10);
            array[1] = Math.round(n17 - n12 - n4 * n10);
            array2[1] = Math.round(n - n13 - n5 * n10);
            array[2] = Math.round(n6 - n12 + n4 * n11);
            array2[2] = Math.round(n2 - n13 + n5 * n11);
            array[3] = Math.round(n6 + n12 - n4 * n11);
            array2[3] = Math.round(n2 + n13 - n5 * n11);
        }
        rp_eS.a(array, array2, 4, color);
    }
    
    private void a(final rp_eS rp_eS, final int n, final int n2, final int n3, final int n4, final Point point, final Point point2, final Color color) {
        final boolean b;
        final int n5 = (b = (((Math.abs(n3 - n) > Math.abs(n4 - n2)) ? 1 : 0) != 0)) ? n : n2;
        final int n6 = b ? n3 : n4;
        final int n7 = this.a / 2;
        final float n8 = n3 - n;
        final float n9 = n4 - n2;
        final float n10 = (float)rp_C.a(n8, n9);
        float n11 = 0.0f;
        float n12 = 0.0f;
        if (n10 > 10.0f) {
            n11 = n8 / n10;
            n12 = n9 / n10;
        }
        int a = 0;
        if (point != null) {
            a = a(new Point(n3, n4), new Point(n, n2), point, this.a);
        }
        int a2 = 0;
        if (point2 != null) {
            a2 = a(point2, new Point(n3, n4), new Point(n, n2), this.a);
        }
        a(rp_eS, b, n5, n6, n7, n11, n12, n, n2, n3, n4, a, a2, color);
    }
    
    static boolean a(final rp_eS rp_eS, final rp_bV rp_bV, int i, Point point, final Point point2) {
        Vector<rp_bV> vector = null;
        for (int j = 0; j < rp_bV.a(); ++j) {
            if (rp_bV.a(j).i == rp_bV.d) {
                if (vector == null) {
                    vector = (Vector<rp_bV>)new rp_gn();
                }
                vector.addElement(rp_bV.a(j));
            }
        }
        final Vector<rp_bV> vector2;
        if ((vector2 = vector) == null) {
            return false;
        }
        final int n = i / 2;
        final boolean b;
        int n2;
        int n3;
        int n4;
        int n5;
        Point point3;
        if (((b = (Math.abs(rp_bV.p - rp_bV.n) > Math.abs(rp_bV.q - rp_bV.o))) && rp_bV.n < rp_bV.p) || (!b && rp_bV.o < rp_bV.q)) {
            n2 = rp_bV.n;
            n3 = rp_bV.o;
            n4 = rp_bV.p;
            n5 = rp_bV.q;
            point3 = point;
            point = point2;
        }
        else {
            n2 = rp_bV.p;
            n3 = rp_bV.q;
            n4 = rp_bV.n;
            n5 = rp_bV.o;
            point3 = point2;
            point = point;
        }
        final float n6 = n4 - n2;
        final float n7 = n5 - n3;
        final float n8 = (float)rp_C.a(n6, n7);
        float n9 = 0.0f;
        float n10 = 0.0f;
        if (n8 > 10.0f) {
            n9 = n6 / n8;
            n10 = n7 / n8;
        }
        int n11 = b ? n2 : n3;
        final int n12 = b ? n4 : n5;
        final Vector<rp_bV> vector3 = vector2;
        final boolean b2 = b;
        final Vector<rp_bV> vector4 = vector3;
        final int size;
        int[] array;
        if ((size = vector3.size()) == 0) {
            array = null;
        }
        else {
            final Vector<rp_bV> vector5 = vector4;
            final boolean b3 = b2;
            final Vector<rp_bV> vector6 = vector5;
            final int[] array2 = new int[2 * vector6.size()];
            for (int k = 0; k < vector6.size(); ++k) {
                final rp_bV rp_bV2 = vector6.elementAt(k);
                if (b3) {
                    if (rp_bV2.n < rp_bV2.p) {
                        array2[k * 2] = rp_bV2.n;
                        array2[k * 2 + 1] = rp_bV2.p;
                    }
                    else {
                        array2[k * 2] = rp_bV2.p;
                        array2[k * 2 + 1] = rp_bV2.n;
                    }
                }
                else if (rp_bV2.o < rp_bV2.q) {
                    array2[k * 2] = rp_bV2.o;
                    array2[k * 2 + 1] = rp_bV2.q;
                }
                else {
                    array2[k * 2] = rp_bV2.q;
                    array2[k * 2 + 1] = rp_bV2.o;
                }
            }
            final int[] array3 = array2;
            if (size > 1) {
                int[] array4;
                for (int n13 = (array4 = array3).length / 2, l = 0; l < n13 - 1; ++l) {
                    for (int n14 = 0; n14 < n13 - 1 - l; ++n14) {
                        if (array4[n14 * 2] > array4[n14 * 2 + 2]) {
                            final int n15 = array4[n14 * 2];
                            array4[n14 * 2] = array4[n14 * 2 + 2];
                            array4[n14 * 2 + 2] = n15;
                            final int n16 = array4[n14 * 2 + 1];
                            array4[n14 * 2 + 1] = array4[n14 * 2 + 3];
                            array4[n14 * 2 + 3] = n16;
                        }
                    }
                }
                final int[] array5;
                if ((array5 = array3) == null) {
                    array = null;
                }
                else {
                    final int[] array6 = new int[array5.length];
                    int n17 = 1;
                    array6[0] = array5[0];
                    array6[1] = array5[1];
                    for (int n18 = 1; n18 < array5.length / 2; ++n18) {
                        if (array5[n18 << 1] <= array6[(n17 << 1) - 1]) {
                            array6[(n17 << 1) - 1] = Math.max(array6[(n17 << 1) - 1], array5[(n18 << 1) + 1]);
                        }
                        else {
                            array6[n17 * 2] = array5[n18 << 1];
                            array6[n17 * 2 + 1] = array5[(n18 << 1) + 1];
                            ++n17;
                        }
                    }
                    if (n17 < array5.length / 2) {
                        final int[] array7 = new int[n17 << 1];
                        for (int n19 = 0; n19 < n17 << 1; ++n19) {
                            array7[n19] = array6[n19];
                        }
                        array = array7;
                    }
                    else {
                        array = array6;
                    }
                }
            }
            else {
                array = array3;
            }
        }
        final int[] array8 = array;
        int a = 0;
        if (point3 != null) {
            a = a(new Point(n4, n5), new Point(n2, n3), point3, i);
        }
        int a2 = 0;
        if (point != null) {
            a2 = a(point, new Point(n4, n5), new Point(n2, n3), i);
        }
        rp_eS.b(Color.black);
        for (i = 0; i < array8.length / 2; ++i) {
            if (n11 < array8[i * 2]) {
                a(rp_eS, b, n11, array8[i * 2], n, n9, n10, n2, n3, n4, n5, (i == 0) ? ((float)a) : 0.0f, 0.0f, rp_aJ.a);
            }
            n11 = array8[i * 2 + 1];
        }
        if (n11 < n12) {
            a(rp_eS, b, n11, n12, n, n9, n10, n2, n3, n4, n5, 0.0f, a2, rp_aJ.a);
        }
        return true;
    }
    
    public final void a(final StringBuffer sb, final rp_eP rp_eP) {
        sb.append("TYPE=WALL\n");
        sb.append("START:[");
        sb.append(this.b);
        sb.append(';');
        sb.append(this.c);
        sb.append("]\n");
        sb.append("END:[");
        sb.append(this.d);
        sb.append(';');
        sb.append(this.e);
        sb.append("]\n");
    }
    
    public final void a(final rp_eg rp_eg) {
        final Vector<String> vector;
        (vector = new Vector<String>()).addElement(rp_eg.a("id", Integer.toString(this.h)));
        vector.addElement(rp_eg.a("width", Integer.toString(this.a)));
        vector.addElement(rp_eg.a("id-start", Integer.toString(this.f)));
        vector.addElement(rp_eg.a("id-end", Integer.toString(this.g)));
        rp_eg.a("wall", vector);
        vector.removeAllElements();
        vector.addElement(rp_eg.a("x1", Integer.toString(this.b)));
        vector.addElement(rp_eg.a("y1", Integer.toString(this.c)));
        vector.addElement(rp_eg.a("x2", Integer.toString(this.d)));
        vector.addElement(rp_eg.a("y2", Integer.toString(this.e)));
        rp_eg.a("position", vector, "");
        rp_eg.a();
    }
    
    public static rp_c a(final rp_J rp_J, final rp_eA rp_eA) {
        final int a = rp_eA.a("id", -1);
        final int a2 = rp_eA.a("id-start", -1);
        final int a3 = rp_eA.a("id-end", -1);
        final int a4 = rp_eA.a("width", 0);
        int a5 = 0;
        int a6 = 0;
        int a7 = 0;
        int a8 = 0;
        final Enumeration<rp_eA> elements = (Enumeration<rp_eA>)rp_eA.a.elements();
        while (elements.hasMoreElements()) {
            final rp_eA rp_eA2;
            if ((rp_eA2 = elements.nextElement()).a.equals("position")) {
                a5 = rp_eA2.a("x1", 0);
                a6 = rp_eA2.a("y1", 0);
                a7 = rp_eA2.a("x2", 0);
                a8 = rp_eA2.a("y2", 0);
            }
        }
        final rp_c rp_c;
        (rp_c = new rp_c(a, rp_J, a5, a6, a7, a8, a4)).f = a2;
        rp_c.g = a3;
        return rp_c;
    }
    
    public final String a() {
        return "";
    }
    
    public final void a(final String s) {
    }
    
    public final String b() {
        return null;
    }
    
    public final void b(final String s) {
    }
    
    private static int a(final Point point, final Point point2, final Point point3, final int n) {
        final double tan;
        if (Math.abs(tan = Math.tan((rp_C.a(point2, point3) - rp_C.a(point2, point)) / 2.0)) < 1.0E-6) {
            return 0;
        }
        return (int)(0.5 + n / 2 / tan);
    }
}
