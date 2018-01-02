import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class m extends l
{
    protected int u;
    protected int v;
    protected static final int w = 4095;
    protected static final float x = 10000.0f;
    protected s y;
    protected Point[] z;
    protected Point[] A;
    protected Point[] B;
    protected float[] C;
    protected int[] D;
    protected static final boolean E = true;
    protected static final boolean F = false;
    protected boolean G;
    protected float H;
    protected float I;
    protected float J;
    protected float K;
    protected float L;
    protected float M;
    protected int N;
    protected static final int O = 0;
    protected static final int P = 1;
    protected static final int Q = 2;
    
    protected final void c() {
        if (!this.G) {
            for (int a = l.a(super.b.c().height, super.e), i = 0; i <= a; i += super.e) {
                if (Math.abs(this.D[i]) != 10000.0f) {
                    this.a(new Point(this.D[i], i), this.z[i >> super.d]);
                    this.a(new Point(this.D[i] - this.u, i), this.A[i >> super.d]);
                    this.a(new Point(this.D[i] + this.u, i), this.B[i >> super.d]);
                }
            }
            return;
        }
        for (int a2 = l.a(super.b.c().width, super.e), j = 0; j <= a2; j += super.e) {
            if (Math.abs(this.D[j]) != 10000.0f) {
                this.a(new Point(j, this.D[j]), this.z[j >> super.d]);
                this.a(new Point(j, this.D[j] - this.u), this.A[j >> super.d]);
                this.a(new Point(j, this.D[j] + this.u), this.B[j >> super.d]);
            }
        }
    }
    
    void a(final t t) {
        if (super.b == t) {
            return;
        }
        super.a(t);
        final Dimension c = t.c();
        super.d = Math.min((int)Math.ceil(Math.log(c.width * c.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        super.e = 1 << super.d;
        this.u = super.e << 1;
        this.a(t.c());
    }
    
    m() {
        this.v = 0;
        this.y = new s();
        this.C = null;
        this.D = null;
        this.G = true;
    }
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.z = new Point[Math.max(super.j, super.k) + 1];
        this.A = new Point[this.z.length];
        this.B = new Point[this.z.length];
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i] = new Point(0, 0);
            this.A[i] = new Point(0, 0);
            this.B[i] = new Point(0, 0);
        }
        this.C = new float[l.a(Math.max(dimension.width, dimension.height), this.u) + this.u];
        this.D = new int[this.C.length];
    }
    
    protected final void d() {
        l.a(super.b.c().width, this.u);
        l.a(super.b.c().height, this.u);
        final float n = this.y.g[2];
        final float n2 = this.y.h[2];
        final float n3 = this.y.f[2];
        final float n4 = this.y.l[3] * this.y.e;
        float n5 = -n / n2;
        if (n == 0.0f) {
            n5 = 0.0f;
        }
        else if (n2 == 0.0f) {
            n5 = ((n > 0.0f) ? -10000.0f : 10000.0f);
        }
        this.G = (Math.abs(n5) <= 1.0f);
        if (Math.abs(n5) > 1000.0f) {
            float n6 = n4 * -n3 / n + super.b.b()[0];
            if (n == 0.0) {
                n6 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int i = 0; i < this.C.length; ++i) {
                this.C[i] = n6;
            }
        }
        else if (Math.abs(n5) < 0.001f) {
            float n7 = n4 * -n3 / n2 + super.b.b()[1];
            if (n2 == 0.0) {
                n7 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int j = 0; j < this.C.length; ++j) {
                this.C[j] = n7;
            }
        }
        else if (!this.G) {
            final float n8 = n4 * -n3 / n - super.b.b()[1] / n5 + super.b.b()[0];
            for (int k = 0; k < this.C.length; ++k) {
                this.C[k] = k / n5 + n8;
            }
        }
        else {
            final float n9 = n4 * -n3 / n2 - n5 * super.b.b()[0] + super.b.b()[1];
            for (int l = 0; l < this.C.length; ++l) {
                this.C[l] = n5 * l + n9;
            }
        }
        for (int n10 = 0; n10 < this.D.length; ++n10) {
            this.D[n10] = Math.round(this.C[n10]);
        }
    }
    
    float a(final float n) {
        return (float)Math.atan2(super.b.c().height / 2.0, n * this.M);
    }
    
    float b(float n) {
        if (this.I != 0.0f) {
            n = Math.min(n, this.I);
        }
        if (this.H != 0.0f) {
            n = Math.max(n, this.H);
        }
        final Dimension c = super.b.c();
        final float n2 = (float)Math.sqrt(c.width * c.width + c.height * c.height);
        final float n3 = 0.64f * n2 / this.M;
        final float n4 = 2.8f;
        final float n5 = n2 / (3.115f * this.M);
        if (n5 < n4) {
            n = bm.b(n * n3, n5, n4);
        }
        else {
            n = this.J * n3;
        }
        if (this.K > 0.0f && this.K < 3.1415927f) {
            n = Math.max(n, c.height / 2.0f / (this.M * (float)Math.tan(this.K / 2.0f)));
        }
        return n / n3;
    }
    
    protected final void e() {
        final int n = this.G ? super.b.c().width : super.b.c().height;
        final int n2 = (this.G ? super.b.c().height : super.b.c().width) + super.e - 1 & ~(super.e - 1);
        final boolean b = (this.G ? this.y.h[2] : this.y.g[2]) >= 0.0f;
        for (int i = 0; i < n; i += super.e) {
            if ((this.D[i] <= n2 || this.D[i + super.e] <= n2) && (this.D[i] >= 0 || this.D[i + super.e] >= 0)) {
                this.a(i, Math.min(i + super.e, n), b);
            }
        }
        int n3 = 0;
        for (int n4 = 0, j = 0; j < super.b.c().height; j += super.e, ++n4) {
            for (int k = 0; k < super.b.c().width; k += super.e, ++n4, ++n3) {
                final int n5 = this.G ? j : k;
                final int n6 = this.G ? k : j;
                if ((this.C[n6] < n5 || this.C[n6] > n5 + super.e) && (this.C[n6 + super.e] < n5 || this.C[n6 + super.e] > n5 + super.e)) {
                    this.a(super.h[n4], super.h[n4 + 1], super.h[n4 + super.j + 1], super.h[n4 + super.j + 2], super.d, super.d);
                    this.a(super.l[n3], this.C[n6 + super.e / 2] < n5 + super.e / 2 ^ b);
                }
            }
        }
    }
    
    protected void a(final Point point, final Point point2) {
        final float[] array = { point.x - super.b.b()[0], point.y - super.b.b()[1] };
        final float[] array2 = new float[2];
        this.y.a(array, array2);
        final float[] array3 = array2;
        final int n = 0;
        array3[n] += super.a.b()[0];
        final float[] array4 = array2;
        final int n2 = 1;
        array4[n2] += super.a.b()[1];
        point2.x = (int)(array2[0] * 4096.0f);
        point2.y = (int)(array2[1] * 4096.0f);
    }
    
    protected void a(final Rectangle rectangle, final boolean b) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(0).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        if (this.N == 2 && b) {
            for (int i = 0; i < rectangle.height; ++i) {
                int n2 = n;
                for (int j = 0; j < rectangle.width; ++j) {
                    a2[n2++] = -16777216;
                }
                n += c2.width;
            }
            return;
        }
        int n3 = 0;
        if (b && this.N == 0) {
            n3 += c.width * c.height / 2;
        }
        for (int k = 0; k < rectangle.height; ++k) {
            int m = super.m;
            int n4 = super.n;
            int n5 = n;
            for (int l = 0; l < rectangle.width; ++l) {
                try {
                    a2[n5] = this.a(a, c, m, n4, n3);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a2[n5] = -16777216;
                }
                ++n5;
                m += super.o;
                n4 += super.p;
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
            n += c2.width;
        }
    }
    
    protected void a() {
        final Dimension c = super.b.c();
        final float n = 0.64f * (float)Math.sqrt(c.width * c.width + c.height * c.height) / this.y.e;
        final float[] array2;
        final float[] array = array2 = (float[])this.a("Viewpoint");
        final int n2 = 3;
        array2[n2] *= n;
        try {
            final float[] b = bm.b(array, this.y.a());
            this.v = (int)((float)Math.sqrt(b[0] * b[0] + b[1] * b[1] + b[3] * b[3]) * (2.0f * (float)Math.atan2(1.0, 1.28f * array[3] / n)) * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.y.a(array);
        this.b();
        this.d();
        this.c();
        this.e();
    }
    
    void b(final t t) {
        if (super.a == t) {
            return;
        }
        try {
            this.I = (float)t.a("ZoomMax");
            this.H = (float)t.a("ZoomMin");
            this.J = ((float[])t.a("DefViewpoint"))[3];
            this.K = (float)t.a("vFOV");
            this.L = (float)t.a("hFOV");
            this.M = (float)t.a("Radius");
            final String s = (String)t.a("Layout");
            if (s.equals("Sphere")) {
                this.N = 0;
            }
            else if (s.equals("Mirrored")) {
                this.N = 1;
            }
            else {
                this.N = 2;
            }
            this.y.a(this.M);
            this.y.b((float[])t.a("RefViewpoint"));
            super.b(t);
        }
        catch (NullPointerException ex) {}
    }
    
    void a(final float[] array) {
        array[3] = this.b(array[3]);
        if (this.L >= 6.2831855f) {
            array[0] = bm.a(array[0], -3.1415927f, 3.1415927f);
        }
        else {
            array[0] = bm.b(array[0], -this.L / 2.0f, this.L / 2.0f);
        }
        final Dimension c = super.b.c();
        final float n = 0.64f * (float)Math.sqrt(c.width * c.width + c.height * c.height) / this.M;
        if (this.K > 0.0f && this.K < 3.1415927f) {
            final float n2 = this.K / 2.0f;
            final float n3 = (float)Math.atan2(c.height / 2.0f, array[3] * n * this.M);
            if (Math.abs(array[1]) + n3 > n2) {
                array[1] = ((array[1] < 0.0f) ? (-(n2 - n3)) : (n2 - n3));
            }
        }
        else {
            array[1] = bm.b(array[1], -1.5707964f, 1.5707964f);
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(0).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n3 = 0;
        final boolean b2 = false;
        if (this.N == 0) {
            n3 = c.width * c.height / 2;
        }
        int n4 = 0;
        int n5 = 0;
        if (this.N == 2) {
            n4 = (b ? 1 : 0);
            n5 = (b ? 0 : 1);
        }
        final int n6 = b ? n3 : b2;
        final int n7 = b ? b2 : n3;
        final int n8 = this.G ? 1 : c2.width;
        final int n9 = this.G ? c2.width : 1;
        final int n10 = this.G ? c2.height : c2.width;
        final int n11 = n >> super.d;
        this.a(this.A[n11], this.z[n11], this.A[n11 + 1], this.z[n11 + 1], super.d + 1, super.d);
        for (int i = n; i < n2; ++i) {
            final int max = Math.max(this.D[i] - super.e - 1 & ~(super.e - 1), 0);
            final int min = Math.min(this.D[i], n10 - 1);
            int n12 = i * n8 + max * n9;
            if (n4 == 1) {
                for (int j = max; j <= min; ++j) {
                    a2[n12] = -16777216;
                    n12 += n9;
                }
            }
            else {
                final int n13 = this.u - (this.D[i] - max);
                int n14 = super.m + n13 * super.o;
                int n15 = super.n + n13 * super.p;
                for (int k = max; k <= min; ++k) {
                    try {
                        a2[n12] = this.a(a, c, n14, n15, n6);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        a2[n12] = -16777216;
                    }
                    n12 += n9;
                    n14 += super.o;
                    n15 += super.p;
                }
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
        }
        this.a(this.B[n11], this.z[n11], this.B[n11 + 1], this.z[n11 + 1], super.d + 1, super.d);
        for (int l = n; l < n2; ++l) {
            final int n16 = Math.min(this.D[l] + this.u & ~(super.e - 1), n10) - 1;
            final int max2 = Math.max(this.D[l] + 1, 0);
            int n17 = l * n8 + n16 * n9;
            if (n5 == 1) {
                for (int n18 = n16; n18 >= max2; --n18) {
                    a2[n17] = -16777216;
                    n17 -= n9;
                }
            }
            else {
                final int n19 = this.u + (this.D[l] - n16);
                int n20 = super.m + n19 * super.o;
                int n21 = super.n + n19 * super.p;
                for (int n22 = n16; n22 >= max2; --n22) {
                    try {
                        a2[n17] = this.a(a, c, n20, n21, n7);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        a2[n17] = -16777216;
                    }
                    n17 -= n9;
                    n20 += super.o;
                    n21 += super.p;
                }
            }
            super.m += super.q;
            super.n += super.r;
            super.o += super.s;
            super.p += super.t;
        }
    }
    
    protected int a(final int[] array, final Dimension dimension, final int n, final int n2, final int n3) {
        final int n4 = n3 + (n2 >>> 12) * dimension.width + (n >>> 12);
        if (this.v > 20) {
            return array[n4];
        }
        final int n5 = array[n4];
        final int n6 = array[n4 + 1];
        final int n7 = array[n4 + dimension.width];
        final int n8 = array[n4 + dimension.width + 1];
        final int n9 = (n5 & 0xFF0000) >> 16;
        final int n10 = (n5 & 0xFF00) >> 8;
        final int n11 = n5 & 0xFF;
        final int n12 = (n6 & 0xFF0000) >> 16;
        final int n13 = (n6 & 0xFF00) >> 8;
        final int n14 = n6 & 0xFF;
        final int n15 = (n7 & 0xFF0000) >> 16;
        final int n16 = (n7 & 0xFF00) >> 8;
        final int n17 = n7 & 0xFF;
        final int n18 = (n8 & 0xFF0000) >> 16;
        final int n19 = (n8 & 0xFF00) >> 8;
        final int n20 = n8 & 0xFF;
        final int n21 = (n & 0xFFF) >> 4;
        final int n22 = (n2 & 0xFFF) >> 4;
        return 0xFF000000 | (((n18 - n15) * n21 + (n15 << 8)) * n22 + ((n12 - n9) * n21 + (n9 << 8)) * (256 - n22) & 0xFF0000) | (((n19 - n16) * n21 + (n16 << 8)) * n22 + ((n13 - n10) * n21 + (n10 << 8)) * (256 - n22) >> 8 & 0xFF00) | (((n20 - n17) * n21 + (n17 << 8)) * n22 + ((n14 - n11) * n21 + (n11 << 8)) * (256 - n22) >> 16 & 0xFF);
    }
}
