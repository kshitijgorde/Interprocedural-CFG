import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class k extends j
{
    protected int w;
    protected static final float x = 10000.0f;
    protected n y;
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
    protected float N;
    protected float[] O;
    protected float P;
    protected int Q;
    protected static final int R = 0;
    protected static final int S = 1;
    protected static final int T = 2;
    protected final float U = 0.017453292f;
    private float[] V;
    private float[] W;
    private Point X;
    
    k() {
        this.y = new n();
        this.C = null;
        this.D = null;
        this.G = true;
        this.K = 0.0f;
        this.L = 0.0f;
        this.M = 0.0f;
        this.N = 0.0f;
        this.V = new float[2];
        this.W = new float[2];
        this.X = new Point(0, 0);
    }
    
    void a(final o o) {
        if (this.a == o) {
            return;
        }
        try {
            this.I = (float)o.getProperty("zmax");
            this.H = (float)o.getProperty("zmin");
            this.J = ((float[])o.getProperty("invp"))[3];
            this.P = (float)o.getProperty("irad");
            this.O = (float[])o.getProperty("rfvp");
            try {
                this.K = (float)o.getProperty("vfmn");
            }
            catch (NullPointerException ex) {}
            try {
                this.L = (float)o.getProperty("vfmx");
            }
            catch (NullPointerException ex2) {}
            try {
                this.M = (float)o.getProperty("hfmn");
            }
            catch (NullPointerException ex3) {}
            try {
                this.N = (float)o.getProperty("hfmx");
            }
            catch (NullPointerException ex4) {}
            final float k = this.K;
            this.K = -this.L;
            this.L = -k;
            if (this.H == 0.0f) {
                this.H = 0.5f;
            }
            if (this.I == 0.0f) {
                this.I = (this.P + 1.0f) * 2.8f / 512.0f;
            }
            if (this.M == this.N) {
                float floatValue = 0.0f;
                try {
                    floatValue = (float)o.getProperty("hfov");
                }
                catch (NullPointerException ex5) {}
                this.N = 0.5f * floatValue;
                this.M = -this.N;
            }
            if (this.K == this.L) {
                float floatValue2 = 0.0f;
                try {
                    floatValue2 = (float)o.getProperty("vfov");
                }
                catch (NullPointerException ex6) {}
                if (floatValue2 < 3.1414928f) {
                    this.L = 0.5f * floatValue2;
                    this.K = -this.L;
                }
            }
            final String s = (String)o.getProperty("frmt");
            if (s.equals("SPHERE")) {
                this.Q = 0;
            }
            else if (s.equals("MIRRORED")) {
                this.Q = 1;
            }
            else {
                this.Q = 2;
            }
            this.y.a(this.P);
            this.y.b((float[])o.getProperty("rfvp"));
            super.a(o);
        }
        catch (NullPointerException ex7) {}
    }
    
    void b(final o o) {
        if (this.b == o) {
            return;
        }
        super.b(o);
        final Dimension b = o.b();
        this.d = Math.min((int)Math.ceil(Math.log(b.width * b.height / 75.0f) / Math.log(2.0) / 2.0), 4);
        this.e = 1 << this.d;
        this.w = this.e << 1;
        this.a(o.b());
    }
    
    protected void a(final Dimension dimension) {
        super.a(dimension);
        this.z = new Point[Math.max(this.k, this.l) + 1];
        this.A = new Point[this.z.length];
        this.B = new Point[this.z.length];
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i] = new Point(0, 0);
            this.A[i] = new Point(0, 0);
            this.B[i] = new Point(0, 0);
        }
        this.C = new float[j.a(Math.max(dimension.width, dimension.height), this.w) + this.w];
        this.D = new int[this.C.length];
    }
    
    void a(final float[] array) {
        if (this.H < this.I) {
            array[3] = bj.a(array[3], this.H, this.I);
        }
        final Dimension b = this.b.b();
        final float n = 0.64f * (float)Math.sqrt(b.width * b.width + b.height * b.height) / this.P;
        final int n2 = 3;
        array[n2] *= n;
        if (this.N > this.M && this.N - this.M <= 6.2831855f) {
            final float min = Math.min((this.N - this.M) * 0.5f, 3.1414928f);
            final float n3 = b.width * 0.5f;
            array[3] = Math.max(array[3], n3 / (this.P * (float)Math.tan(min)));
            final float n4 = (float)Math.atan2(n3, array[3] * this.P);
            if (array[0] + n4 > this.N) {
                array[0] = this.N - n4;
            }
            if (array[0] - n4 < this.M) {
                array[0] = this.M + n4;
            }
        }
        else if (this.Q == 2 && this.O[1] > -0.017453292f && this.O[1] < 0.017453292f) {
            array[0] = bj.a(array[0], -1.5707964f, 1.5707964f);
        }
        else {
            array[0] = bj.b(array[0], -3.1415927f, 3.1415927f);
        }
        if (this.L > this.K) {
            final float n5 = (this.L - this.K) * 0.5f;
            final float n6 = b.height * 0.5f;
            array[3] = Math.max(array[3], n6 / (this.P * (float)Math.tan(n5)));
            final float n7 = (float)Math.atan2(n6, array[3] * this.P);
            if (array[1] + n7 > this.L) {
                array[1] = this.L - n7;
            }
            if (array[1] - n7 < this.K) {
                array[1] = this.K + n7;
            }
        }
        else if (this.Q == 2 && (this.O[1] < -1.553343f || this.O[1] > 1.553343f)) {
            if (this.O[1] > 1.553343f) {
                array[1] = bj.a(array[1], 0.0f, 1.5707964f);
            }
            else {
                array[1] = bj.a(array[1], -1.5707964f, 0.0f);
            }
        }
        else {
            array[1] = bj.a(array[1], -1.5707964f, 1.5707964f);
        }
        final int n8 = 3;
        array[n8] /= n;
    }
    
    protected void a(final Point point, final Point point2) {
        this.V[0] = point.x - this.b.c()[0];
        this.V[1] = point.y - this.b.c()[1];
        this.y.a(this.V, this.W);
        final float[] w = this.W;
        final int n = 0;
        w[n] += this.a.c()[0];
        final float[] w2 = this.W;
        final int n2 = 1;
        w2[n2] += this.a.c()[1];
        point2.x = (int)(this.W[0] * 4096.0f);
        point2.y = (int)(this.W[1] * 4096.0f);
    }
    
    protected final void c() {
        final int n = -this.w;
        final int n2 = -this.w;
        final int n3 = j.a(this.b.b().width, this.w) + this.w;
        final int n4 = j.a(this.b.b().height, this.w) + this.w;
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
            float n10 = n8 * -n7 / n5 + this.b.c()[0];
            if (n5 == 0.0) {
                n10 = ((n7 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int i = 0; i < this.C.length; ++i) {
                this.C[i] = n10;
            }
        }
        else if (Math.abs(n9) < 0.001f) {
            float n11 = n8 * -n7 / n6 + this.b.c()[1];
            if (n6 == 0.0) {
                n11 = ((n7 > 0.0) ? -10000.0f : 10000.0f);
            }
            for (int j = 0; j < this.C.length; ++j) {
                this.C[j] = n11;
            }
        }
        else if (!this.G) {
            final float n12 = n8 * -n7 / n5 - this.b.c()[1] / n9 + this.b.c()[0];
            for (int k = 0; k < this.C.length; ++k) {
                this.C[k] = k / n9 + n12;
            }
        }
        else {
            final float n13 = n8 * -n7 / n6 - n9 * this.b.c()[0] + this.b.c()[1];
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
            for (int a = j.a(this.b.b().height, this.e), i = 0; i <= a; i += this.e) {
                if (Math.abs(this.D[i]) != 10000.0f) {
                    this.X.move(this.D[i], i);
                    this.a(this.X, this.z[i >> this.d]);
                    this.X.move(this.D[i] - this.w, i);
                    this.a(this.X, this.A[i >> this.d]);
                    this.X.move(this.D[i] + this.w, i);
                    this.a(this.X, this.B[i >> this.d]);
                }
            }
        }
        else {
            for (int a2 = j.a(this.b.b().width, this.e), j = 0; j <= a2; j += this.e) {
                if (Math.abs(this.D[j]) != 10000.0f) {
                    this.X.move(j, this.D[j]);
                    this.a(this.X, this.z[j >> this.d]);
                    this.X.move(j, this.D[j] - this.w);
                    this.a(this.X, this.A[j >> this.d]);
                    this.X.move(j, this.D[j] + this.w);
                    this.a(this.X, this.B[j >> this.d]);
                }
            }
        }
    }
    
    protected final void e() {
        final int n = this.G ? this.b.b().width : this.b.b().height;
        final int n2 = (this.G ? this.b.b().height : this.b.b().width) + this.e - 1 & ~(this.e - 1);
        final boolean b = (this.G ? this.y.h[2] : this.y.g[2]) >= 0.0f;
        for (int i = 0; i < n; i += this.e) {
            if (this.D[i] <= n2 || this.D[i + this.e] <= n2) {
                if (this.D[i] >= 0 || this.D[i + this.e] >= 0) {
                    this.a(i, Math.min(i + this.e, n), b);
                }
            }
        }
        int n3 = 0;
        for (int n4 = 0, j = 0; j < this.b.b().height; j += this.e, ++n4) {
            for (int k = 0; k < this.b.b().width; k += this.e, ++n4, ++n3) {
                final int n5 = this.G ? j : k;
                final int n6 = this.G ? k : j;
                if (this.C[n6] < n5 || this.C[n6] > n5 + this.e) {
                    if (this.C[n6 + this.e] < n5 || this.C[n6 + this.e] > n5 + this.e) {
                        this.a(this.i[n4], this.i[n4 + 1], this.i[n4 + this.k + 1], this.i[n4 + this.k + 2], this.d, this.d);
                        this.a(this.m[n3], this.C[n6 + this.e / 2] < n5 + this.e / 2 ^ b);
                    }
                }
            }
        }
    }
    
    protected void a(final Rectangle rectangle, final boolean b) {
        final Dimension b2 = this.a.b();
        final Dimension b3 = this.b.b();
        final int[] array = this.a.a().elementAt(0);
        final int[] array2 = this.b.a().elementAt(0);
        int n = rectangle.y * b3.width + rectangle.x;
        if (this.Q == 2 && b) {
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
        if (b && this.Q == 0) {
            n3 += b2.width * b2.height / 2;
        }
        for (int k = 0; k < rectangle.height; ++k) {
            int o = this.o;
            int p2 = this.p;
            int n4 = n;
            for (int l = 0; l < rectangle.width; ++l) {
                try {
                    array2[n4] = this.a(array, b2, o, p2, n3);
                }
                catch (ArrayIndexOutOfBoundsException ex) {
                    array2[n4] = -16777216;
                }
                ++n4;
                o += this.q;
                p2 += this.r;
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
            n += b3.width;
        }
    }
    
    protected void a(final int n, final int n2, final boolean b) {
        final Dimension b2 = this.a.b();
        final Dimension b3 = this.b.b();
        final int[] array = this.a.a().elementAt(0);
        final int[] array2 = this.b.a().elementAt(0);
        int n3 = 0;
        final boolean b4 = false;
        if (this.Q == 0) {
            n3 = b2.width * b2.height / 2;
        }
        int n4 = 0;
        int n5 = 0;
        if (this.Q == 2) {
            n4 = (b ? 1 : 0);
            n5 = (b ? 0 : 1);
        }
        final int n6 = b ? n3 : b4;
        final int n7 = b ? b4 : n3;
        final int n8 = this.G ? 1 : b3.width;
        final int n9 = this.G ? b3.width : 1;
        final int n10 = this.G ? b3.height : b3.width;
        final int n11 = n >> this.d;
        this.a(this.A[n11], this.z[n11], this.A[n11 + 1], this.z[n11 + 1], this.d + 1, this.d);
        for (int i = n; i < n2; ++i) {
            final int max = Math.max(this.D[i] - this.e - 1 & ~(this.e - 1), 0);
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
                int n14 = this.o + n13 * this.q;
                int n15 = this.p + n13 * this.r;
                for (int k = max; k <= min; ++k) {
                    try {
                        array2[n12] = this.a(array, b2, n14, n15, n6);
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        array2[n12] = -16777216;
                    }
                    n12 += n9;
                    n14 += this.q;
                    n15 += this.r;
                }
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
        }
        this.a(this.B[n11], this.z[n11], this.B[n11 + 1], this.z[n11 + 1], this.d + 1, this.d);
        for (int l = n; l < n2; ++l) {
            final int n16 = Math.min(this.D[l] + this.w & ~(this.e - 1), n10) - 1;
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
                int n20 = this.o + n19 * this.q;
                int n21 = this.p + n19 * this.r;
                for (int n22 = n16; n22 >= max2; --n22) {
                    try {
                        array2[n17] = this.a(array, b2, n20, n21, n7);
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        array2[n17] = -16777216;
                    }
                    n17 -= n9;
                    n20 += this.q;
                    n21 += this.r;
                }
            }
            this.o += this.s;
            this.p += this.t;
            this.q += this.u;
            this.r += this.v;
        }
    }
    
    protected int a(final int[] array, final Dimension dimension, final int n, final int n2, final int n3) {
        final int n4 = n3 + (n2 >>> 12) * dimension.width + (n >>> 12);
        if (this.n > 20) {
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
        final Dimension b = this.b.b();
        final float n = 0.64f * (float)Math.sqrt(b.width * b.width + b.height * b.height) / this.y.e;
        final float[] array2;
        final float[] array = array2 = (float[])this.getProperty("ptrz");
        final int n2 = 3;
        array2[n2] *= n;
        try {
            final float[] a = bj.a(array, this.y.a());
            this.n = (int)((float)Math.sqrt(a[0] * a[0] + a[1] * a[1] + a[3] * a[3]) * (2.0f * (float)Math.atan2(1.0, 1.28f * array[3] / n)) * 573.0f);
        }
        catch (NullPointerException ex) {}
        this.y.a(array);
        this.b();
        this.c();
        this.d();
        this.e();
    }
    
    float a(final float n) {
        if (this.K == this.L) {
            return this.K;
        }
        final float[] array = { 0.0f, -3.1415927f, 0.0f, n };
        this.a(array);
        return array[1];
    }
    
    float b(final float n) {
        if (this.K == this.L) {
            return this.L;
        }
        final float[] array = { 0.0f, 3.1415927f, 0.0f, n };
        this.a(array);
        return array[1];
    }
    
    float c(final float n) {
        if (this.M == this.N) {
            return this.M;
        }
        final float[] array = { -6.2831855f, 0.0f, 0.0f, n };
        this.a(array);
        return array[0];
    }
    
    float d(final float n) {
        if (this.M == this.N) {
            return this.N;
        }
        final float[] array = { 6.2831855f, 0.0f, 0.0f, n };
        this.a(array);
        return array[0];
    }
    
    float e(final float n) {
        final Dimension b = this.b.b();
        return (float)Math.atan2(b.height / 2.0, n * 0.64 * (float)Math.sqrt(b.width * b.width + b.height * b.height)) * 2.0f;
    }
}
