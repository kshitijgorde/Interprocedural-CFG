import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class i extends h
{
    protected int w;
    protected static final float x = 10000.0f;
    protected l y;
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
    private float[] R;
    private float[] S;
    private Point T;
    
    i() {
        this.y = new l();
        this.C = null;
        this.D = null;
        this.G = true;
        this.R = new float[2];
        this.S = new float[2];
        this.T = new Point(0, 0);
    }
    
    void a(final m m) {
        if (super.a == m) {
            return;
        }
        try {
            this.I = (float)m.a("ZoomMax");
            this.H = (float)m.a("ZoomMin");
            this.J = ((float[])m.a("DefViewpoint"))[3];
            this.K = (float)m.a("vFOV");
            this.L = (float)m.a("hFOV");
            this.M = (float)m.a("Radius");
            final String s = (String)m.a("Layout");
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
            this.y.b((float[])m.a("RefViewpoint"));
            super.a(m);
        }
        catch (NullPointerException ex) {}
    }
    
    void b(final m m) {
        if (super.b == m) {
            return;
        }
        super.b(m);
        final Dimension b = m.b();
        super.d = Math.min((int)Math.ceil(Math.log(b.width * b.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        super.e = 1 << super.d;
        this.w = super.e << 1;
        this.a(m.b());
    }
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.z = new Point[Math.max(super.k, super.l) + 1];
        this.A = new Point[this.z.length];
        this.B = new Point[this.z.length];
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i] = new Point(0, 0);
            this.A[i] = new Point(0, 0);
            this.B[i] = new Point(0, 0);
        }
        this.C = new float[h.a(Math.max(dimension.width, dimension.height), this.w) + this.w];
        this.D = new int[this.C.length];
    }
    
    float a(float n) {
        if (this.I != 0.0f) {
            n = Math.min(n, this.I);
        }
        if (this.H != 0.0f) {
            n = Math.max(n, this.H);
        }
        final Dimension b = super.b.b();
        final float n2 = (float)Math.sqrt(b.width * b.width + b.height * b.height);
        final float n3 = 0.64f * n2 / this.M;
        final float n4 = 2.8f;
        final float n5 = n2 / (3.115f * this.M);
        if (n5 < n4) {
            n = ba.a(n * n3, n5, n4);
        }
        else {
            n = this.J * n3;
        }
        if (this.K > 0.0f && this.K < 3.1415927f) {
            n = Math.max(n, b.height / 2.0f / (this.M * (float)Math.tan(this.K / 2.0f)));
        }
        return n / n3;
    }
    
    void a(final float[] array) {
        array[3] = this.a(array[3]);
        if (this.L >= 6.2831855f) {
            array[0] = ba.b(array[0], -3.1415927f, 3.1415927f);
        }
        else {
            array[0] = ba.a(array[0], -this.L / 2.0f, this.L / 2.0f);
        }
        final Dimension b = super.b.b();
        final float n = 0.64f * (float)Math.sqrt(b.width * b.width + b.height * b.height) / this.M;
        if (this.K > 0.0f && this.K < 3.1415927f) {
            final float n2 = this.K / 2.0f;
            final float n3 = (float)Math.atan2(b.height / 2.0f, array[3] * n * this.M);
            if (Math.abs(array[1]) + n3 > n2) {
                array[1] = ((array[1] < 0.0f) ? (-(n2 - n3)) : (n2 - n3));
            }
        }
        else {
            array[1] = ba.a(array[1], -1.5707964f, 1.5707964f);
        }
    }
    
    protected void a(final Point point, final Point point2) {
        this.R[0] = point.x - super.b.c()[0];
        this.R[1] = point.y - super.b.c()[1];
        this.y.a(this.R, this.S);
        final float[] s = this.S;
        final int n = 0;
        s[n] += super.a.c()[0];
        final float[] s2 = this.S;
        final int n2 = 1;
        s2[n2] += super.a.c()[1];
        point2.x = (int)(this.S[0] * 4096.0f);
        point2.y = (int)(this.S[1] * 4096.0f);
    }
    
    protected final void c() {
        final int n = -this.w;
        final int n2 = -this.w;
        final int n3 = h.a(super.b.b().width, this.w) + this.w;
        final int n4 = h.a(super.b.b().height, this.w) + this.w;
        final float n5 = this.y.g[2];
        final float n6 = this.y.h[2];
        final float n7 = this.y.f[2];
        final float n8 = this.y.l[3] * this.y.e;
        float n9 = -n5 / n6;
        if (n5 == 0.0f) {
            n9 = 0.0f;
        }
        else if (n6 == 0.0f) {
            n9 = ((n5 > 0.0f) ? -10000.0f : 10000.0f);
        }
        this.G = (Math.abs(n9) <= 1.0f);
        if (Math.abs(n9) > 1000.0f) {
            float n10 = n8 * -n7 / n5 + super.b.c()[0];
            if (n5 == 0.0) {
                n10 = ((n7 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int i = 0; i < this.C.length; ++i) {
                this.C[i] = n10;
            }
        }
        else if (Math.abs(n9) < 0.001f) {
            float n11 = n8 * -n7 / n6 + super.b.c()[1];
            if (n6 == 0.0) {
                n11 = ((n7 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int j = 0; j < this.C.length; ++j) {
                this.C[j] = n11;
            }
        }
        else if (!this.G) {
            final float n12 = n8 * -n7 / n5 - super.b.c()[1] / n9 + super.b.c()[0];
            for (int k = 0; k < this.C.length; ++k) {
                this.C[k] = k / n9 + n12;
            }
        }
        else {
            final float n13 = n8 * -n7 / n6 - n9 * super.b.c()[0] + super.b.c()[1];
            for (int l = 0; l < this.C.length; ++l) {
                this.C[l] = n9 * l + n13;
            }
        }
        for (int n14 = 0; n14 < this.D.length; ++n14) {
            this.D[n14] = Math.round(this.C[n14]);
        }
    }
    
    protected final void d() {
        if (!this.G) {
            for (int a = h.a(super.b.b().height, super.e), i = 0; i <= a; i += super.e) {
                if (Math.abs(this.D[i]) != 10000.0f) {
                    this.T.move(this.D[i], i);
                    this.a(this.T, this.z[i >> super.d]);
                    this.T.move(this.D[i] - this.w, i);
                    this.a(this.T, this.A[i >> super.d]);
                    this.T.move(this.D[i] + this.w, i);
                    this.a(this.T, this.B[i >> super.d]);
                }
            }
        }
        else {
            for (int a2 = h.a(super.b.b().width, super.e), j = 0; j <= a2; j += super.e) {
                if (Math.abs(this.D[j]) != 10000.0f) {
                    this.T.move(j, this.D[j]);
                    this.a(this.T, this.z[j >> super.d]);
                    this.T.move(j, this.D[j] - this.w);
                    this.a(this.T, this.A[j >> super.d]);
                    this.T.move(j, this.D[j] + this.w);
                    this.a(this.T, this.B[j >> super.d]);
                }
            }
        }
    }
    
    protected final void e() {
        final int n = this.G ? super.b.b().width : super.b.b().height;
        final int n2 = (this.G ? super.b.b().height : super.b.b().width) + super.e - 1 & ~(super.e - 1);
        final boolean b = (this.G ? this.y.h[2] : this.y.g[2]) >= 0.0f;
        for (int i = 0; i < n; i += super.e) {
            if (this.D[i] <= n2 || this.D[i + super.e] <= n2) {
                if (this.D[i] >= 0 || this.D[i + super.e] >= 0) {
                    this.a(i, Math.min(i + super.e, n), b);
                }
            }
        }
        int n3 = 0;
        for (int n4 = 0, j = 0; j < super.b.b().height; j += super.e, ++n4) {
            for (int k = 0; k < super.b.b().width; k += super.e, ++n4, ++n3) {
                final int n5 = this.G ? j : k;
                final int n6 = this.G ? k : j;
                if (this.C[n6] < n5 || this.C[n6] > n5 + super.e) {
                    if (this.C[n6 + super.e] < n5 || this.C[n6 + super.e] > n5 + super.e) {
                        this.a(super.i[n4], super.i[n4 + 1], super.i[n4 + super.k + 1], super.i[n4 + super.k + 2], super.d, super.d);
                        this.a(super.m[n3], this.C[n6 + super.e / 2] < n5 + super.e / 2 ^ b);
                    }
                }
            }
        }
    }
    
    protected void a(final Rectangle rectangle, final boolean b) {
        final Dimension b2 = super.a.b();
        final Dimension b3 = super.b.b();
        final int[] array = super.a.a().elementAt(0);
        final int[] array2 = super.b.a().elementAt(0);
        int n = rectangle.y * b3.width + rectangle.x;
        if (this.N == 2 && b) {
            for (int i = 0; i < rectangle.height; ++i) {
                int n2 = n;
                for (int j = 0; j < rectangle.width; ++j) {
                    array2[n2++] = -16777216;
                }
                n += b3.width;
            }
            return;
        }
        int n3 = 0;
        if (b && this.N == 0) {
            n3 += b2.width * b2.height / 2;
        }
        for (int k = 0; k < rectangle.height; ++k) {
            int o = super.o;
            int p2 = super.p;
            int n4 = n;
            for (int l = 0; l < rectangle.width; ++l) {
                try {
                    array2[n4] = this.a(array, b2, o, p2, n3);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    array2[n4] = -16777216;
                }
                ++n4;
                o += super.q;
                p2 += super.r;
            }
            super.o += super.s;
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            n += b3.width;
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        final Dimension b2 = super.a.b();
        final Dimension b3 = super.b.b();
        final int[] array = super.a.a().elementAt(0);
        final int[] array2 = super.b.a().elementAt(0);
        int n3 = 0;
        final boolean b4 = false;
        if (this.N == 0) {
            n3 = b2.width * b2.height / 2;
        }
        int n4 = 0;
        int n5 = 0;
        if (this.N == 2) {
            n4 = (b ? 1 : 0);
            n5 = (b ? 0 : 1);
        }
        final int n6 = b ? n3 : b4;
        final int n7 = b ? b4 : n3;
        final int n8 = this.G ? 1 : b3.width;
        final int n9 = this.G ? b3.width : 1;
        final int n10 = this.G ? b3.height : b3.width;
        final int n11 = n >> super.d;
        this.a(this.A[n11], this.z[n11], this.A[n11 + 1], this.z[n11 + 1], super.d + 1, super.d);
        for (int i = n; i < n2; ++i) {
            final int max = Math.max(this.D[i] - super.e - 1 & ~(super.e - 1), 0);
            final int min = Math.min(this.D[i], n10 - 1);
            int n12 = i * n8 + max * n9;
            if (n4 == 1) {
                for (int j = max; j <= min; ++j) {
                    array2[n12] = -16777216;
                    n12 += n9;
                }
            }
            else {
                final int n13 = this.w - (this.D[i] - max);
                int n14 = super.o + n13 * super.q;
                int n15 = super.p + n13 * super.r;
                for (int k = max; k <= min; ++k) {
                    try {
                        array2[n12] = this.a(array, b2, n14, n15, n6);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        array2[n12] = -16777216;
                    }
                    n12 += n9;
                    n14 += super.q;
                    n15 += super.r;
                }
            }
            super.o += super.s;
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
        }
        this.a(this.B[n11], this.z[n11], this.B[n11 + 1], this.z[n11 + 1], super.d + 1, super.d);
        for (int l = n; l < n2; ++l) {
            final int n16 = Math.min(this.D[l] + this.w & ~(super.e - 1), n10) - 1;
            final int max2 = Math.max(this.D[l] + 1, 0);
            int n17 = l * n8 + n16 * n9;
            if (n5 == 1) {
                for (int n18 = n16; n18 >= max2; --n18) {
                    array2[n17] = -16777216;
                    n17 -= n9;
                }
            }
            else {
                final int n19 = this.w + (this.D[l] - n16);
                int n20 = super.o + n19 * super.q;
                int n21 = super.p + n19 * super.r;
                for (int n22 = n16; n22 >= max2; --n22) {
                    try {
                        array2[n17] = this.a(array, b2, n20, n21, n7);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        array2[n17] = -16777216;
                    }
                    n17 -= n9;
                    n20 += super.q;
                    n21 += super.r;
                }
            }
            super.o += super.s;
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
        }
    }
    
    protected int a(final int[] array, final Dimension dimension, final int n, final int n2, final int n3) {
        final int n4 = n3 + (n2 >>> 12) * dimension.width + (n >>> 12);
        if (super.n > 20) {
            return array[n4];
        }
        return this.a(array[n4], array[n4 + 1], array[n4 + dimension.width], array[n4 + dimension.width + 1], n, n2);
    }
    
    protected int a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = (n & 0xFF0000) >> 16;
        final int n8 = (n & 0xFF00) >> 8;
        final int n9 = n & 0xFF;
        final int n10 = (n2 & 0xFF0000) >> 16;
        final int n11 = (n2 & 0xFF00) >> 8;
        final int n12 = n2 & 0xFF;
        final int n13 = (n3 & 0xFF0000) >> 16;
        final int n14 = (n3 & 0xFF00) >> 8;
        final int n15 = n3 & 0xFF;
        final int n16 = (n4 & 0xFF0000) >> 16;
        final int n17 = (n4 & 0xFF00) >> 8;
        final int n18 = n4 & 0xFF;
        final int n19 = (n5 & 0xFFF) >> 4;
        final int n20 = (n6 & 0xFFF) >> 4;
        return 0xFF000000 | (((n16 - n13) * n19 + (n13 << 8)) * n20 + ((n10 - n7) * n19 + (n7 << 8)) * (256 - n20) & 0xFF0000) | (((n17 - n14) * n19 + (n14 << 8)) * n20 + ((n11 - n8) * n19 + (n8 << 8)) * (256 - n20) >> 8 & 0xFF00) | (((n18 - n15) * n19 + (n15 << 8)) * n20 + ((n12 - n9) * n19 + (n9 << 8)) * (256 - n20) >> 16 & 0xFF);
    }
    
    protected void a() {
        final Dimension b = super.b.b();
        final float n = 0.64f * (float)Math.sqrt(b.width * b.width + b.height * b.height) / this.y.e;
        final float[] array2;
        final float[] array = array2 = (float[])this.a("Viewpoint");
        final int n2 = 3;
        array2[n2] *= n;
        try {
            final float[] a = ba.a(array, this.y.a());
            super.n = (int)((float)Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[3] * a[3]) * (2.0f * (float)Math.atan2(1.0, 1.28f * array[3] / n)) * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.y.a(array);
        this.b();
        this.c();
        this.d();
        this.e();
    }
    
    float b(final float n) {
        final Dimension b = super.b.b();
        return (float)Math.atan2(b.height / 2.0, n * 0.64 * (float)Math.sqrt(b.width * b.width + b.height * b.height)) * 2.0f;
    }
}
