import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class m extends l
{
    protected int x;
    protected static final float y = 10000.0f;
    protected s z;
    protected Point[] A;
    protected Point[] B;
    protected Point[] C;
    protected float[] D;
    protected int[] E;
    protected static final boolean F = true;
    protected static final boolean G = false;
    protected boolean H;
    protected float I;
    protected float J;
    protected float K;
    protected float L;
    protected float M;
    protected float N;
    protected int O;
    protected static final int P = 0;
    protected static final int Q = 1;
    protected static final int R = 2;
    private float[] S;
    private float[] T;
    private Point U;
    
    protected final void c() {
        if (!this.H) {
            for (int a = l.a(super.b.c().height, super.f), i = 0; i <= a; i += super.f) {
                if (Math.abs(this.E[i]) != 10000.0f) {
                    this.U.move(this.E[i], i);
                    this.a(this.U, this.A[i >> super.e]);
                    this.U.move(this.E[i] - this.x, i);
                    this.a(this.U, this.B[i >> super.e]);
                    this.U.move(this.E[i] + this.x, i);
                    this.a(this.U, this.C[i >> super.e]);
                }
            }
            return;
        }
        for (int a2 = l.a(super.b.c().width, super.f), j = 0; j <= a2; j += super.f) {
            if (Math.abs(this.E[j]) != 10000.0f) {
                this.U.move(j, this.E[j]);
                this.a(this.U, this.A[j >> super.e]);
                this.U.move(j, this.E[j] - this.x);
                this.a(this.U, this.B[j >> super.e]);
                this.U.move(j, this.E[j] + this.x);
                this.a(this.U, this.C[j >> super.e]);
            }
        }
    }
    
    void a(final t t) {
        if (super.b == t) {
            return;
        }
        super.a(t);
        final Dimension c = t.c();
        super.e = Math.min((int)Math.ceil(Math.log(c.width * c.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        super.f = 1 << super.e;
        this.x = super.f << 1;
        this.a(t.c());
    }
    
    m() {
        this.z = new s();
        this.D = null;
        this.E = null;
        this.H = true;
        this.S = new float[2];
        this.T = new float[2];
        this.U = new Point(0, 0);
    }
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.A = new Point[Math.max(super.l, super.m) + 1];
        this.B = new Point[this.A.length];
        this.C = new Point[this.A.length];
        for (int i = 0; i < this.A.length; ++i) {
            this.A[i] = new Point(0, 0);
            this.B[i] = new Point(0, 0);
            this.C[i] = new Point(0, 0);
        }
        this.D = new float[l.a(Math.max(dimension.width, dimension.height), this.x) + this.x];
        this.E = new int[this.D.length];
    }
    
    protected final void d() {
        l.a(super.b.c().width, this.x);
        l.a(super.b.c().height, this.x);
        final float n = this.z.g[2];
        final float n2 = this.z.h[2];
        final float n3 = this.z.f[2];
        final float n4 = this.z.l[3] * this.z.e;
        float n5 = -n / n2;
        if (n == 0.0f) {
            n5 = 0.0f;
        }
        else if (n2 == 0.0f) {
            n5 = ((n > 0.0f) ? -10000.0f : 10000.0f);
        }
        this.H = (Math.abs(n5) <= 1.0f);
        if (Math.abs(n5) > 1000.0f) {
            float n6 = n4 * -n3 / n + super.b.b()[0];
            if (n == 0.0) {
                n6 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int i = 0; i < this.D.length; ++i) {
                this.D[i] = n6;
            }
        }
        else if (Math.abs(n5) < 0.001f) {
            float n7 = n4 * -n3 / n2 + super.b.b()[1];
            if (n2 == 0.0) {
                n7 = ((n3 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int j = 0; j < this.D.length; ++j) {
                this.D[j] = n7;
            }
        }
        else if (!this.H) {
            final float n8 = n4 * -n3 / n - super.b.b()[1] / n5 + super.b.b()[0];
            for (int k = 0; k < this.D.length; ++k) {
                this.D[k] = k / n5 + n8;
            }
        }
        else {
            final float n9 = n4 * -n3 / n2 - n5 * super.b.b()[0] + super.b.b()[1];
            for (int l = 0; l < this.D.length; ++l) {
                this.D[l] = n5 * l + n9;
            }
        }
        for (int n10 = 0; n10 < this.E.length; ++n10) {
            this.E[n10] = Math.round(this.D[n10]);
        }
    }
    
    float a(final float n) {
        final Dimension c = super.b.c();
        return (float)Math.atan2(c.height / 2.0, n * 0.64 * (float)Math.sqrt(c.width * c.width + c.height * c.height)) * 2.0f;
    }
    
    float b(float n) {
        if (this.J != 0.0f) {
            n = Math.min(n, this.J);
        }
        if (this.I != 0.0f) {
            n = Math.max(n, this.I);
        }
        final Dimension c = super.b.c();
        final float n2 = (float)Math.sqrt(c.width * c.width + c.height * c.height);
        final float n3 = 0.64f * n2 / this.N;
        final float n4 = 2.8f;
        final float n5 = n2 / (3.115f * this.N);
        if (n5 < n4) {
            n = bn.b(n * n3, n5, n4);
        }
        else {
            n = this.K * n3;
        }
        if (this.L > 0.0f && this.L < 3.1415927f) {
            n = Math.max(n, c.height / 2.0f / (this.N * (float)Math.tan(this.L / 2.0f)));
        }
        return n / n3;
    }
    
    protected final void e() {
        final int n = this.H ? super.b.c().width : super.b.c().height;
        final int n2 = (this.H ? super.b.c().height : super.b.c().width) + super.f - 1 & ~(super.f - 1);
        final boolean b = (this.H ? this.z.h[2] : this.z.g[2]) >= 0.0f;
        for (int i = 0; i < n; i += super.f) {
            if ((this.E[i] <= n2 || this.E[i + super.f] <= n2) && (this.E[i] >= 0 || this.E[i + super.f] >= 0)) {
                this.a(i, Math.min(i + super.f, n), b);
            }
        }
        int n3 = 0;
        for (int n4 = 0, j = 0; j < super.b.c().height; j += super.f, ++n4) {
            for (int k = 0; k < super.b.c().width; k += super.f, ++n4, ++n3) {
                final int n5 = this.H ? j : k;
                final int n6 = this.H ? k : j;
                if ((this.D[n6] < n5 || this.D[n6] > n5 + super.f) && (this.D[n6 + super.f] < n5 || this.D[n6 + super.f] > n5 + super.f)) {
                    this.a(super.j[n4], super.j[n4 + 1], super.j[n4 + super.l + 1], super.j[n4 + super.l + 2], super.e, super.e);
                    this.a(super.n[n3], this.D[n6 + super.f / 2] < n5 + super.f / 2 ^ b);
                }
            }
        }
    }
    
    protected void a(final Point point, final Point point2) {
        this.S[0] = point.x - super.b.b()[0];
        this.S[1] = point.y - super.b.b()[1];
        this.z.a(this.S, this.T);
        final float[] t = this.T;
        final int n = 0;
        t[n] += super.a.b()[0];
        final float[] t2 = this.T;
        final int n2 = 1;
        t2[n2] += super.a.b()[1];
        point2.x = (int)(this.T[0] * 4096.0f);
        point2.y = (int)(this.T[1] * 4096.0f);
    }
    
    protected void a(final Rectangle rectangle, final boolean b) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(0).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n = rectangle.y * c2.width + rectangle.x;
        if (this.O == 2 && b) {
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
        if (b && this.O == 0) {
            n3 += c.width * c.height / 2;
        }
        for (int k = 0; k < rectangle.height; ++k) {
            int p2 = super.p;
            int q = super.q;
            int n4 = n;
            for (int l = 0; l < rectangle.width; ++l) {
                try {
                    a2[n4] = this.a(a, c, p2, q, n3);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    a2[n4] = -16777216;
                }
                ++n4;
                p2 += super.r;
                q += super.s;
            }
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            super.s += super.w;
            n += c2.width;
        }
    }
    
    protected void a() {
        final Dimension c = super.b.c();
        final float n = 0.64f * (float)Math.sqrt(c.width * c.width + c.height * c.height) / this.z.e;
        final float[] array2;
        final float[] array = array2 = (float[])this.a("Viewpoint");
        final int n2 = 3;
        array2[n2] *= n;
        try {
            final float[] b = bn.b(array, this.z.a());
            super.o = (int)((float)Math.sqrt(b[0] * b[0] + b[1] * b[1] + b[3] * b[3]) * (2.0f * (float)Math.atan2(1.0, 1.28f * array[3] / n)) * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.z.a(array);
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
            this.J = (float)t.a("ZoomMax");
            this.I = (float)t.a("ZoomMin");
            this.K = ((float[])t.a("DefViewpoint"))[3];
            this.L = (float)t.a("vFOV");
            this.M = (float)t.a("hFOV");
            this.N = (float)t.a("Radius");
            final String s = (String)t.a("Layout");
            if (s.equals("Sphere")) {
                this.O = 0;
            }
            else if (s.equals("Mirrored")) {
                this.O = 1;
            }
            else {
                this.O = 2;
            }
            this.z.a(this.N);
            this.z.b((float[])t.a("RefViewpoint"));
            super.b(t);
        }
        catch (NullPointerException ex) {}
    }
    
    void a(final float[] array) {
        array[3] = this.b(array[3]);
        if (this.M >= 6.2831855f) {
            array[0] = bn.a(array[0], -3.1415927f, 3.1415927f);
        }
        else {
            array[0] = bn.b(array[0], -this.M / 2.0f, this.M / 2.0f);
        }
        final Dimension c = super.b.c();
        final float n = 0.64f * (float)Math.sqrt(c.width * c.width + c.height * c.height) / this.N;
        if (this.L > 0.0f && this.L < 3.1415927f) {
            final float n2 = this.L / 2.0f;
            final float n3 = (float)Math.atan2(c.height / 2.0f, array[3] * n * this.N);
            if (Math.abs(array[1]) + n3 > n2) {
                array[1] = ((array[1] < 0.0f) ? (-(n2 - n3)) : (n2 - n3));
            }
        }
        else {
            array[1] = bn.b(array[1], -1.5707964f, 1.5707964f);
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        final Dimension c = super.a.c();
        final Dimension c2 = super.b.c();
        final int[] a = super.a.a().elementAt(0).a();
        final int[] a2 = super.b.a().elementAt(0).a();
        int n3 = 0;
        final boolean b2 = false;
        if (this.O == 0) {
            n3 = c.width * c.height / 2;
        }
        int n4 = 0;
        int n5 = 0;
        if (this.O == 2) {
            n4 = (b ? 1 : 0);
            n5 = (b ? 0 : 1);
        }
        final int n6 = b ? n3 : b2;
        final int n7 = b ? b2 : n3;
        final int n8 = this.H ? 1 : c2.width;
        final int n9 = this.H ? c2.width : 1;
        final int n10 = this.H ? c2.height : c2.width;
        final int n11 = n >> super.e;
        this.a(this.B[n11], this.A[n11], this.B[n11 + 1], this.A[n11 + 1], super.e + 1, super.e);
        for (int i = n; i < n2; ++i) {
            final int max = Math.max(this.E[i] - super.f - 1 & ~(super.f - 1), 0);
            final int min = Math.min(this.E[i], n10 - 1);
            int n12 = i * n8 + max * n9;
            if (n4 == 1) {
                for (int j = max; j <= min; ++j) {
                    a2[n12] = -16777216;
                    n12 += n9;
                }
            }
            else {
                final int n13 = this.x - (this.E[i] - max);
                int n14 = super.p + n13 * super.r;
                int n15 = super.q + n13 * super.s;
                for (int k = max; k <= min; ++k) {
                    try {
                        a2[n12] = this.a(a, c, n14, n15, n6);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        a2[n12] = -16777216;
                    }
                    n12 += n9;
                    n14 += super.r;
                    n15 += super.s;
                }
            }
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            super.s += super.w;
        }
        this.a(this.C[n11], this.A[n11], this.C[n11 + 1], this.A[n11 + 1], super.e + 1, super.e);
        for (int l = n; l < n2; ++l) {
            final int n16 = Math.min(this.E[l] + this.x & ~(super.f - 1), n10) - 1;
            final int max2 = Math.max(this.E[l] + 1, 0);
            int n17 = l * n8 + n16 * n9;
            if (n5 == 1) {
                for (int n18 = n16; n18 >= max2; --n18) {
                    a2[n17] = -16777216;
                    n17 -= n9;
                }
            }
            else {
                final int n19 = this.x + (this.E[l] - n16);
                int n20 = super.p + n19 * super.r;
                int n21 = super.q + n19 * super.s;
                for (int n22 = n16; n22 >= max2; --n22) {
                    try {
                        a2[n17] = this.a(a, c, n20, n21, n7);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        a2[n17] = -16777216;
                    }
                    n17 -= n9;
                    n20 += super.r;
                    n21 += super.s;
                }
            }
            super.p += super.t;
            super.q += super.u;
            super.r += super.v;
            super.s += super.w;
        }
    }
    
    protected int a(final int[] array, final Dimension dimension, final int n, final int n2, final int n3) {
        final int n4 = n3 + (n2 >>> 12) * dimension.width + (n >>> 12);
        if (super.o > 20) {
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
