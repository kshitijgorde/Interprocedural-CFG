import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class j extends i
{
    public static boolean[] a;
    public static final float[] g;
    public static final float[] i;
    public int v;
    public int t;
    public int m;
    public int[] a;
    public float[] d;
    public DirectColorModel a;
    public MemoryImageSource a;
    public Image a;
    public int J;
    public boolean n;
    public float u;
    public float X;
    public float t;
    public float D;
    public boolean k;
    public boolean m;
    public float v;
    public float l;
    public float ae;
    public float x;
    public int p;
    public int n;
    public int F;
    public int a;
    public int g;
    public boolean d;
    public boolean a;
    public float q;
    public float G;
    public float Z;
    public float o;
    public int E;
    public float aa;
    public int A;
    public int d;
    public int o;
    public float ac;
    public float i;
    public float ad;
    public float w;
    public int j;
    public int y;
    public int G;
    public int q;
    public int r;
    public boolean g;
    public boolean h;
    public float[] a;
    public float[] f;
    public float[] c;
    public float[] h;
    public float[] b;
    public float[] e;
    public int[] d;
    public float O;
    public float y;
    public float W;
    public float R;
    public float M;
    public float n;
    public float T;
    public float k;
    public float C;
    public float a;
    public float f;
    public float z;
    public float e;
    public float E;
    public float J;
    public float s;
    public int c;
    public int x;
    public int u;
    public float af;
    public float V;
    public float g;
    public float d;
    public float ag;
    public float p;
    public float A;
    public float S;
    public float B;
    public float H;
    public float c;
    public float Y;
    public float P;
    public float ah;
    public float b;
    public float ai;
    public float j;
    public float Q;
    public float F;
    public float I;
    public float K;
    public float U;
    public float ab;
    public int B;
    public m d;
    public m c;
    public m b;
    public float[][] b;
    public m a;
    public int i;
    public int[] c;
    public int l;
    public float[][] a;
    public int C;
    public int s;
    public int e;
    public int[] b;
    public h a;
    public int[][] b;
    public int h;
    public c a;
    public int[][] a;
    public int w;
    public boolean e;
    public int z;
    public float N;
    public float m;
    public float h;
    public float r;
    public float L;
    private i a;
    public i[] a;
    public int D;
    public boolean i;
    public boolean b;
    public boolean f;
    public boolean l;
    public int H;
    public int f;
    public int I;
    public int L;
    public d a;
    public boolean c;
    
    public final void b(final int b, final int k) {
        this.b = b;
        this.k = k;
        this.v = this.b - 1;
        this.t = this.k - 1;
        this.j();
        this.f(this.o);
        this.af = 60.0f;
        this.V = this.b / 2.0f;
        this.g = this.k / 2.0f;
        this.d = this.g / this.a(3.1415927f * this.af / 360.0f);
        this.ag = this.d / 10.0f;
        this.p = this.d * 10.0f;
        this.A = this.b / this.k;
        this.a(3);
    }
    
    private final void j() {
        this.m = this.b * this.k;
        this.e = new int[this.m];
        this.d = new float[this.m];
        if (j.a[2]) {
            this.a = new int[this.m];
        }
        for (int i = 0; i < this.m; ++i) {
            this.e[i] = -1;
        }
        this.a = new DirectColorModel(32, 16711680, 65280, 255);
        (this.a = new MemoryImageSource(this.b, this.k, this.e, 0, this.b)).setFullBufferUpdates(true);
        this.a.setAnimated(true);
        this.a = Toolkit.getDefaultToolkit().createImage(this.a);
        this.a = new h(this);
        if (j.a[2]) {
            this.a = new c(this);
        }
    }
    
    public final void o() {
        this.a(1, 255.0f);
        this.c(255.0f);
        this.d(0);
        this.aa = 1.0f;
        this.f(204);
        this.B = 0;
        if (!j.a[2]) {
            this.d = new m(this);
            this.c = new m(this);
            this.b = new m(this);
            this.b.w = 4;
            this.b = new float[2][];
        }
        this.a = null;
        this.k();
        this.a = new float[10];
        this.f = new float[10];
        this.c = new float[10];
        this.h = new float[10];
        this.b = new float[10];
        this.e = new float[10];
        (this.d = new int[10])[0] = 1;
        this.a[0] = 0.0f;
        this.f[0] = 0.0f;
        this.c[0] = 0.0f;
        this.h[0] = 0.0f;
        this.b[0] = 0.0f;
        this.e[0] = 0.0f;
        this.d[1] = 2;
        this.h[1] = this.V;
        this.b[1] = this.g;
        this.e[1] = this.d;
        this.a[1] = 1.0f;
        this.f[1] = 1.0f;
        this.c[1] = 1.0f;
        this.z = 1;
        this.H = 0;
        this.f = 3;
        this.c = 0;
        this.I = 0;
        this.L = 3;
        for (int i = 2; i < 10; ++i) {
            this.d[i] = 0;
        }
    }
    
    public final void i() {
        this.g();
        this.h = 0.0f;
        this.r = 0.0f;
        this.L = 1.0f;
        if (j.a[2]) {
            this.l = 0;
            this.C = 0;
            this.s = 0;
            this.e = 0;
            this.h = 0;
            this.a.a();
            this.w = 0;
            this.a.b();
            this.D = 0;
        }
    }
    
    public final void h() {
        if (j.a[2] && this.e) {
            for (int i = 0; i < this.w; ++i) {
                final float[] array = this.a[this.a[i][0]];
                final float[] array2 = this.a[this.a[i][1]];
                final float[] array3 = this.a[this.a[i][2]];
                final int n = this.a[i][4];
                final int o = this.a[i][3];
                this.a.b();
                if (n > -1 && this.a[n] != null) {
                    this.a.a(this.a[n]);
                    this.a.a(array[7], array[8], array2[7], array2[8], array3[7], array3[8]);
                }
                this.a.a(array[3], array[4], array[5], array[6], array2[3], array2[4], array2[5], array2[6], array3[3], array3[4], array3[5], array3[6]);
                this.a.a(array[0], array[1], array[2], array2[0], array2[1], array2[2], array3[0], array3[1], array3[2]);
                this.a.o = o;
                this.a.a();
            }
            for (int j = 0; j < this.h; ++j) {
                final float[] array4 = this.a[this.b[j][0]];
                final float[] array5 = this.a[this.b[j][1]];
                final int n2 = this.b[j][2];
                this.a.a();
                this.a.a(array4[12], array4[13], array4[14], array4[15], array5[12], array5[13], array5[14], array5[15]);
                this.a.a(array4[0], array4[1], array4[2], array5[0], array5[1], array5[2]);
                this.a.a(n2);
                this.a.b();
            }
        }
        this.a.newPixels(this.e, this.a, 0, this.b);
    }
    
    public final float[] a() {
        if (!j.a[2]) {
            return this.d.a();
        }
        if (this.C == this.a.length) {
            final float[][] a = new float[this.C << 1][24];
            System.arraycopy(this.a, 0, a, 0, this.C);
            this.a = a;
            this.a(0, "allocating more vertices " + this.a.length);
        }
        return this.a[this.C++];
    }
    
    public final void b(final i i) {
        if (this.D == this.a.length - 1) {
            final i[] a = new i[this.D << 1];
            System.arraycopy(this.a, 0, a, 0, this.D);
            this.a = a;
            this.a(0, "allocating more textures " + this.a.length);
        }
        if (this.a[0] != null) {
            ++this.D;
        }
        this.a[this.D] = i;
    }
    
    public final void a(final int n, final int n2) {
        if (this.h == this.b.length) {
            final int[][] b = new int[this.h << 1][4];
            System.arraycopy(this.b, 0, b, 0, this.h);
            this.b = b;
            this.a(0, "allocating more lines " + this.b.length);
        }
        this.b[this.h][0] = n;
        this.b[this.h][1] = n2;
        if (this.j && !this.d) {
            this.b[this.h][2] = this.l;
        }
        else {
            this.b[this.h][2] = -1;
        }
        this.b[this.h][3] = (this.d | this.A);
        ++this.h;
    }
    
    public final void b(final int n, final int n2, final int n3) {
        if (this.w == this.a.length) {
            final int[][] a = new int[this.w << 1][5];
            System.arraycopy(this.a, 0, a, 0, this.w);
            this.a = a;
            this.a(0, "allocating more triangles " + this.a.length);
        }
        this.a[this.w][0] = n;
        this.a[this.w][1] = n2;
        this.a[this.w][2] = n3;
        if (this.a == null) {
            this.a[this.w][4] = -1;
        }
        else {
            this.a[this.w][4] = this.D;
        }
        this.a[this.w][3] = this.l;
        ++this.w;
    }
    
    private final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, float n7, float n8, float n9, final float[] array, final int n10) {
        if (!this.h) {
            array[n10] = n;
            array[n10 + 1] = n2;
            array[n10 + 2] = n3;
            return;
        }
        final float b;
        if ((b = this.b(n7, n8, n9)) != 0.0f) {
            n7 /= b;
            n8 /= b;
            n9 /= b;
        }
        float n11 = 0.0f;
        float n12 = 0.0f;
        float n13 = 0.0f;
        for (int n14 = 1; n14 < 10 && this.d[n14] != 0; ++n14) {
            float n15 = this.h[n14] - n4;
            float n16 = this.b[n14] - n5;
            float n17 = this.e[n14] - n6;
            final float b2;
            if ((b2 = this.b(n15, n16, n17)) != 0.0f) {
                n15 /= b2;
                n16 /= b2;
                n17 /= b2;
            }
            final float n18;
            if ((n18 = n7 * n15 + n8 * n16 + n9 * n17) > 0.0f) {
                n11 += this.a[n14] * n18;
                n12 += this.f[n14] * n18;
                n13 += this.c[n14] * n18;
            }
        }
        array[n10] = this.a[0] + n * n11;
        array[n10 + 1] = this.f[0] + n2 * n12;
        array[n10 + 2] = this.c[0] + n3 * n13;
        if (array[n10] > 1.0f) {
            array[n10] = 1.0f;
        }
        if (array[n10 + 1] > 1.0f) {
            array[n10 + 1] = 1.0f;
        }
        if (array[n10 + 2] > 1.0f) {
            array[n10 + 2] = 1.0f;
        }
    }
    
    public final void c(final int b) {
        this.B = b;
        if (j.a[2]) {
            ++this.l;
            if (this.l == -1) {
                this.l = 0;
            }
            if (this.e) {
                this.s = this.C;
                this.e = 0;
            }
            else {
                this.C = 0;
                this.a.a();
                this.h = 0;
                this.a.b();
                this.w = 0;
            }
        }
        else {
            this.d.a(0);
            this.c.a(4);
            this.b.a(4);
            this.d.d = false;
        }
        this.a = null;
        this.i = true;
        this.b = false;
        this.f = false;
        this.l = false;
    }
    
    public final void a(final i a) {
        this.a = a;
        if (j.a[2]) {
            if (this.e) {
                this.b(a);
            }
            else {
                this.a.a(a);
            }
            return;
        }
        this.d.a(a);
    }
    
    public final void a(final float n, final float m) {
        if (j.a[2]) {
            if (this.a == null) {
                this.a(2, "gotta use texture() after beginShape() and before vertexTexture()");
                return;
            }
            if (this.z == 1) {
                this.N = ((n < this.a.b) ? n : this.a.b);
                if (this.N < 0.0f) {
                    this.N = 0.0f;
                }
                this.m = ((m < this.a.k) ? m : this.a.k);
                if (this.m < 0.0f) {
                    this.m = 0.0f;
                }
                this.N = n / this.a.b;
                this.m = m / this.a.k;
            }
            else {
                this.N = n;
                this.m = m;
                if (this.N < 0.0f) {
                    this.N = 0.0f;
                }
                if (this.m < 0.0f) {
                    this.m = 0.0f;
                }
                if (this.N > 1.0f) {
                    this.N = 1.0f;
                }
                if (this.m > 1.0f) {
                    this.m = 1.0f;
                }
            }
        }
        else {
            if (this.a == null) {
                this.a(2, "gotta use texture() after beginShape() and before vertex()");
                return;
            }
            if (this.z == 1) {
                this.N = ((n < this.d.l) ? n : this.d.l);
                if (this.N < 0.0f) {
                    this.N = 0.0f;
                }
                this.m = ((m < this.d.o) ? m : this.d.o);
                if (this.m < 0.0f) {
                    this.m = 0.0f;
                }
                return;
            }
            if (this.N < 0.0f) {
                this.N = 0.0f;
            }
            if (this.m < 0.0f) {
                this.m = 0.0f;
            }
            if (this.N > 1.0f) {
                this.N = 1.0f;
            }
            if (this.m > 1.0f) {
                this.m = 1.0f;
            }
            this.N = n * this.d.l;
            this.m = m * this.d.o;
        }
    }
    
    public final void c(final float n, final float n2) {
        this.a(this.a(), n, n2, 0.0f);
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5) {
        this.a(n4, n5);
        this.i = false;
        this.u = 3;
        this.a(this.a(), n, n2, n3);
    }
    
    private final void a(final float[] array, final float n, final float n2, final float n3) {
        if (this.d.a(n, n2, n3)) {
            return;
        }
        array[9] = n;
        array[10] = n2;
        array[11] = n3;
        if (this.k) {
            array[3] = this.v;
            array[4] = this.l;
            array[5] = this.ae;
            array[6] = this.x;
        }
        if (this.d) {
            array[12] = this.q;
            array[13] = this.G;
            array[14] = this.Z;
            array[15] = this.o;
            array[23] = this.aa;
        }
        if (this.a != null) {
            array[7] = this.N;
            array[8] = this.m;
        }
        if (this.l) {
            array[16] = this.h;
            array[17] = this.r;
            array[18] = this.L;
        }
    }
    
    public final void n() {
        this.e = this.C;
        boolean b = false;
        if (this.d || this.j) {
            b = true;
        }
        boolean b2 = b;
        if (this.a != null && this.a.K == 4) {
            b2 = false;
        }
        if (b2) {
            switch (this.B) {
                case 16: {
                    for (int e = this.e, i = this.s; i < e; ++i) {
                        this.a(i, i);
                    }
                    break;
                }
                case 32:
                case 33:
                case 34: {
                    final int h = this.h;
                    final int n = this.e - 1;
                    int n2 = 0;
                    if (this.B == 32) {
                        n2 = 1;
                    }
                    for (int n3 = n2 + 1, j = this.s; j < n; j += n3) {
                        this.a(j, j + 1);
                    }
                    if (this.B == 34) {
                        this.a(n, this.b[h][0]);
                    }
                    break;
                }
                case 64:
                case 65: {
                    for (int n4 = this.e - 1, k = this.s; k < n4; ++k) {
                        final int n5 = k - this.s;
                        this.a(k, k + 1);
                        if (this.B == 64 && n5 % 3 == 1) {
                            ++k;
                        }
                    }
                    for (int n6 = this.e - 2, n7 = (this.B == 65) ? 1 : 3, l = this.s; l < n6; l += n7) {
                        this.a(l, l + 2);
                    }
                    break;
                }
                case 128:
                case 129: {
                    for (int n8 = this.e - 1, s = this.s; s < n8; ++s) {
                        final int n9 = s - this.s;
                        this.a(s, s + 1);
                        if (this.B == 128 && n9 % 4 == 2) {
                            ++s;
                        }
                    }
                    for (int n10 = this.e - 2, n11 = (this.B == 129) ? 2 : 4, s2 = this.s; s2 < n10; s2 += n11) {
                        this.a(s2, s2 + 3);
                    }
                    break;
                }
                case 256:
                case 257:
                case 258: {
                    final int h2 = this.h;
                    final int n12 = this.e - 1;
                    for (int s3 = this.s; s3 < n12; ++s3) {
                        this.a(s3, s3 + 1);
                    }
                    this.a(n12, this.b[h2][0]);
                    break;
                }
            }
        }
        if (this.k) {
            switch (this.B) {
                case 64:
                case 65: {
                    for (int n13 = this.e - 2, n14 = (this.B == 64) ? 3 : 1, s4 = this.s; s4 < n13; s4 += n14) {
                        this.b(s4, s4 + 1, s4 + 2);
                    }
                    break;
                }
                case 128:
                case 129: {
                    for (int n15 = this.C - 3, n16 = (this.B == 128) ? 4 : 2, s5 = this.s; s5 < n15; s5 += n16) {
                        this.b(s5, s5 + 1, s5 + 2);
                        this.b(s5, s5 + 2, s5 + 3);
                    }
                    break;
                }
                case 256:
                case 257:
                case 258: {
                    this.e();
                    break;
                }
            }
        }
        if (this.x != 0 && this.u == 0) {
            for (int s6 = this.s; s6 < this.e; ++s6) {
                this.a[s6][0] = this.a[s6][9];
                this.a[s6][1] = this.a[s6][10];
            }
        }
        else if (this.x != 0 && this.u == 2) {
            for (int s7 = this.s; s7 < this.e; ++s7) {
                this.a[s7][0] = this.O * this.a[s7][9] + this.y * this.a[s7][10] + this.R;
                this.a[s7][1] = this.M * this.a[s7][9] + this.n * this.a[s7][10] + this.k;
            }
        }
        else {
            for (int s8 = this.s; s8 < this.e; ++s8) {
                final float[] array2;
                final float[] array = array2 = this.a[s8];
                array[19] = this.O * array2[9] + this.y * array2[10] + this.W * array2[11] + this.R;
                array2[20] = this.M * array2[9] + this.n * array2[10] + this.T * array2[11] + this.k;
                array2[21] = this.C * array2[9] + this.a * array2[10] + this.f * array2[11] + this.z;
                array2[22] = this.e * array2[9] + this.E * array2[10] + this.J * array2[11] + this.s;
            }
        }
        if (!this.l) {
            this.a[this.s][16] = this.h;
            this.a[this.s][17] = this.r;
            this.a[this.s][18] = this.L;
        }
        int s9 = this.s;
        Label_1538: {
            break Label_1538;
            int n17;
            int e2;
            do {
                final float[] array3 = this.a[s9];
                final float n18 = this.O * array3[16] + this.y * array3[17] + this.W * array3[18] + this.R;
                final float n19 = this.M * array3[16] + this.n * array3[17] + this.T * array3[18] + this.k;
                final float n20 = this.C * array3[16] + this.a * array3[17] + this.f * array3[18] + this.z;
                final float n21;
                if ((n21 = this.e * array3[16] + this.E * array3[17] + this.J * array3[18] + this.s) != 0.0f) {
                    array3[16] = n18 / n21;
                    array3[17] = n19 / n21;
                    array3[18] = n20 / n21;
                }
                else {
                    array3[16] = n18;
                    array3[17] = n19;
                    array3[18] = n20;
                }
                final float b3;
                if ((b3 = this.b(array3[16], array3[17], array3[18])) != 0.0f) {
                    final float[] array4 = array3;
                    final int n22 = 16;
                    array4[n22] /= b3;
                    final float[] array5 = array3;
                    final int n23 = 17;
                    array5[n23] /= b3;
                    final float[] array6 = array3;
                    final int n24 = 18;
                    array6[n24] /= b3;
                }
                ++s9;
                n17 = s9;
                e2 = 1;
                if (this.l) {
                    e2 = this.e;
                }
            } while (n17 < e2);
        }
        if (this.h) {
            final float[] array7 = this.a[this.s];
            for (int s10 = this.s; s10 < this.e; ++s10) {
                final float[] array8 = this.a[s10];
                if (this.l) {
                    if (this.k) {
                        this.a(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 3);
                    }
                    if (this.d) {
                        this.a(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array8[16], array8[17], array8[18], array8, 12);
                    }
                }
                else {
                    if (this.k) {
                        this.a(array8[3], array8[4], array8[5], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 3);
                    }
                    if (this.d) {
                        this.a(array8[12], array8[13], array8[14], array8[9], array8[10], array8[11], array7[16], array7[17], array7[18], array8, 12);
                    }
                }
            }
        }
        if (this.x == 3 && this.u == 3) {
            for (int s11 = this.s; s11 < this.e; ++s11) {
                final float[] array9 = this.a[s11];
                float n25 = this.S * array9[19] + this.B * array9[20] + this.H * array9[21] + this.c * array9[22];
                float n26 = this.Y * array9[19] + this.P * array9[20] + this.ah * array9[21] + this.b * array9[22];
                float n27 = this.ai * array9[19] + this.j * array9[20] + this.Q * array9[21] + this.F * array9[22];
                final float n28;
                if ((n28 = this.I * array9[19] + this.K * array9[20] + this.U * array9[21] + this.ab * array9[22]) != 0.0f) {
                    n25 /= n28;
                    n26 /= n28;
                    n27 /= n28;
                }
                array9[0] = this.b * (1.0f + n25) / 2.0f;
                array9[1] = this.k * (1.0f + n26) / 2.0f;
                array9[2] = (n27 + 1.0f) / 2.0f;
            }
        }
        if (this.e) {
            return;
        }
        if (this.k) {
            for (int n29 = 0; n29 < this.w; ++n29) {
                final float[] array10 = this.a[this.a[n29][0]];
                final float[] array11 = this.a[this.a[n29][1]];
                final float[] array12 = this.a[this.a[n29][2]];
                final int o = this.a[n29][3];
                if (this.a != null) {
                    this.a.a(array10[7], array10[8], array11[7], array11[8], array12[7], array12[8]);
                }
                this.a.a(array10[3], array10[4], array10[5], array10[6], array11[3], array11[4], array11[5], array11[6], array12[3], array12[4], array12[5], array12[6]);
                this.a.a(array10[0], array10[1], array10[2], array11[0], array11[1], array11[2], array12[0], array12[1], array12[2]);
                this.a.o = o;
                this.a.a();
            }
        }
        if (this.d || this.j) {
            for (int n30 = 0; n30 < this.h; ++n30) {
                final float[] array13 = this.a[this.b[n30][0]];
                final float[] array14 = this.a[this.b[n30][1]];
                final int n31 = this.b[n30][2];
                this.a.a(array13[12], array13[13], array13[14], array13[15], array14[12], array14[13], array14[14], array14[15]);
                this.a.a(array13[0], array13[1], array13[2], array14[0], array14[1], array14[2]);
                this.a.a(n31);
                this.a.b();
            }
        }
        this.B = 0;
    }
    
    private final void e() {
        float n = 0.0f;
        int n2 = this.e - 1;
        for (int i = this.s; i < this.e; n2 = i++) {
            n += this.a[i][0] * this.a[n2][1] - this.a[n2][0] * this.a[i][1];
        }
        if (0.0f < n) {
            for (int j = this.s; j < this.e; ++j) {
                this.b[j - this.s] = j;
            }
        }
        else {
            for (int k = this.s; k < this.e; ++k) {
                final int n3 = k - this.s;
                this.b[n3] = this.e - 1 - n3;
            }
        }
        int l = this.e - this.s;
        int n4 = 2 * l;
        int n5 = l - 1;
        while (l > 2) {
            boolean b = true;
            if (n4-- <= 0) {
                return;
            }
            int n6 = n5;
            if (l <= n6) {
                n6 = 0;
            }
            n5 = n6 + 1;
            if (l <= n5) {
                n5 = 0;
            }
            int n7 = n5 + 1;
            if (l <= n7) {
                n7 = 0;
            }
            final float n8 = -this.a[this.b[n6]][0];
            final float n9 = this.a[this.b[n6]][1];
            final float n10 = -this.a[this.b[n5]][0];
            final float n11 = this.a[this.b[n5]][1];
            final float n12 = -this.a[this.b[n7]][0];
            final float n13 = this.a[this.b[n7]][1];
            if (1.0E-4f > (n10 - n8) * (n13 - n9) - (n11 - n9) * (n12 - n8)) {
                continue;
            }
            for (int n14 = 0; n14 < l; ++n14) {
                if (n14 != n6 && n14 != n5 && n14 != n7) {
                    final float n15 = -this.a[this.b[n14]][0];
                    final float n16 = this.a[this.b[n14]][1];
                    final float n17 = n12 - n10;
                    final float n18 = n13 - n11;
                    final float n19 = n8 - n12;
                    final float n20 = n9 - n13;
                    final float n21 = n10 - n8;
                    final float n22 = n11 - n9;
                    final float n23 = n15 - n8;
                    final float n24 = n16 - n9;
                    final float n25 = n15 - n10;
                    final float n26 = n16 - n11;
                    final float n27 = n15 - n12;
                    final float n28 = n16 - n13;
                    final float n29 = n17 * n26 - n18 * n25;
                    final float n30 = n21 * n24 - n22 * n23;
                    final float n31 = n19 * n28 - n20 * n27;
                    if (n29 >= 0.0f && n31 >= 0.0f && n30 >= 0.0f) {
                        b = false;
                    }
                }
            }
            if (!b) {
                continue;
            }
            this.b(this.b[n6], this.b[n5], this.b[n7]);
            int n32 = n5;
            for (int n33 = n5 + 1; n33 < l; ++n33) {
                this.b[n32] = this.b[n33];
                ++n32;
            }
            --l;
            n4 = 2 * l;
        }
    }
    
    public final void f() {
        if (j.a[2]) {
            this.n();
            return;
        }
        int w = this.d.w;
        final float[][] a = this.d.a;
        if (this.x == 3 && this.u == 0) {
            this.d.g = false;
            this.b.g = false;
            for (int i = 0; i < w; ++i) {
                a[i][0] = a[i][9];
                a[i][1] = a[i][10];
            }
        }
        else if (this.x == 3 && this.u == 2) {
            this.d.g = false;
            this.b.g = false;
            for (int j = 0; j < w; ++j) {
                a[j][0] = this.O * a[j][9] + this.y * a[j][10] + this.R;
                a[j][1] = this.M * a[j][9] + this.n * a[j][10] + this.k;
            }
        }
        else {
            this.d.g = true;
            this.b.g = true;
            for (final float[] array : a) {
                final float n = this.O * array[9] + this.y * array[10] + this.W * array[11] + this.R;
                final float n2 = this.M * array[9] + this.n * array[10] + this.T * array[11] + this.k;
                final float n3 = this.C * array[9] + this.a * array[10] + this.f * array[11] + this.z;
                final float n4 = this.e * array[9] + this.E * array[10] + this.J * array[11] + this.s;
                float n5 = this.S * n + this.B * n2 + this.H * n3 + this.c * n4;
                float n6 = this.Y * n + this.P * n2 + this.ah * n3 + this.b * n4;
                float n7 = this.ai * n + this.j * n2 + this.Q * n3 + this.F * n4;
                final float n8;
                if ((n8 = this.I * n + this.K * n2 + this.U * n3 + this.ab * n4) != 0.0f) {
                    n5 /= n8;
                    n6 /= n8;
                    n7 /= n8;
                }
                array[0] = this.b * (1.0f + n5) / 2.0f;
                array[1] = this.k * (1.0f + n6) / 2.0f;
                array[2] = (n7 + 1.0f) / 2.0f;
            }
        }
        boolean b = true;
        final int a2 = this.a(a[0][0], a[0][1]);
        for (int l = 1; l < w; ++l) {
            if (this.a(a[l][0], a[l][1]) != a2) {
                b = false;
                break;
            }
        }
        if (a2 != 0 && b) {
            return;
        }
        if (!this.l) {
            a[0][16] = this.h;
            a[0][17] = this.r;
            a[0][18] = this.L;
        }
        int n9 = 0;
        Label_1000: {
            break Label_1000;
            int n10;
            int n11;
            do {
                final float[] array2 = a[n9];
                final float n12 = this.O * array2[16] + this.y * array2[17] + this.W * array2[18] + this.R;
                final float n13 = this.M * array2[16] + this.n * array2[17] + this.T * array2[18] + this.k;
                final float n14 = this.C * array2[16] + this.a * array2[17] + this.f * array2[18] + this.z;
                final float n15;
                if ((n15 = this.e * array2[16] + this.E * array2[17] + this.J * array2[18] + this.s) != 0.0f) {
                    array2[16] = n12 / n15;
                    array2[17] = n13 / n15;
                    array2[18] = n14 / n15;
                }
                else {
                    array2[16] = n12;
                    array2[17] = n13;
                    array2[18] = n14;
                }
                final float b2;
                if ((b2 = this.b(array2[16], array2[17], array2[18])) != 0.0f) {
                    final float[] array3 = array2;
                    final int n16 = 16;
                    array3[n16] /= b2;
                    final float[] array4 = array2;
                    final int n17 = 17;
                    array4[n17] /= b2;
                    final float[] array5 = array2;
                    final int n18 = 18;
                    array5[n18] /= b2;
                }
                ++n9;
                n10 = n9;
                n11 = 1;
                if (this.l) {
                    n11 = w;
                }
            } while (n10 < n11);
        }
        if (this.d.d) {
            this.c.a(this.d.a);
        }
        if (!this.h) {
            this.b.c = this.b;
            this.c.c = this.f;
        }
        else {
            this.b.c = true;
            this.c.c = true;
            final float[] array6 = this.d.a[0];
            for (int n19 = 0; n19 < w; ++n19) {
                final float[] array7 = this.d.a[n19];
                if (this.l) {
                    if (this.k) {
                        this.a(array7[3], array7[4], array7[5], array7[9], array7[10], array7[11], array7[16], array7[17], array7[18], array7, 3);
                    }
                    if (this.d) {
                        this.a(array7[12], array7[13], array7[14], array7[9], array7[10], array7[11], array7[16], array7[17], array7[18], array7, 12);
                    }
                }
                else {
                    if (this.k) {
                        this.a(array7[3], array7[4], array7[5], array7[9], array7[10], array7[11], array6[16], array6[17], array6[18], array7, 3);
                    }
                    if (this.d) {
                        this.a(array7[12], array7[13], array7[14], array7[9], array7[10], array7[11], array6[16], array6[17], array6[18], array7, 12);
                    }
                }
            }
        }
        if (this.B == 256) {
            this.B = (this.a() ? 1 : 0) + 257;
        }
        switch (this.B) {
            case 16: {
                if (this.u == 0 && this.i && this.aa == 1.0f && !this.h) {
                    if (!this.b) {
                        for (int n20 = 0; n20 < w; ++n20) {
                            this.a((int)a[n20][0], (int)a[n20][1], 0.0f, this.E);
                        }
                    }
                    else {
                        for (int n21 = 0; n21 < w; ++n21) {
                            this.a((int)a[n21][0], (int)a[n21][1], 0.0f, a(a[n21][12], a[n21][13], a[n21][14]));
                        }
                    }
                }
                else {
                    final float[] array8 = a[0];
                    for (int n22 = 0; n22 < w; ++n22) {
                        final float[] array9 = a[n22];
                        if (n22 == 0 || this.h || this.b) {
                            this.a(array9[12], array9[13], array9[14], array9[0], array9[1], array9[2], array9[16], array9[17], array9[18], array8, 3);
                        }
                        this.a(array9[0], array9[1], array9[2], array8[3], array8[4], array8[5], array8[15]);
                    }
                }
            }
            case 32:
            case 33:
            case 34: {
                if (!this.d) {
                    return;
                }
                if (this.B == 34) {
                    final float[] array10 = this.d.a[0];
                    final float[] a3 = this.d.a();
                    ++w;
                    a3[0] = array10[0];
                    a3[1] = array10[1];
                    a3[2] = array10[2];
                    a3[12] = array10[12];
                    a3[13] = array10[13];
                    a3[14] = array10[14];
                    a3[15] = array10[15];
                }
                int n23 = 0;
                if (this.B == 32) {
                    n23 = 1;
                }
                this.a(a, w - 1, 1, n23 + 1, 0);
            }
            case 64:
            case 65: {
                final int n24 = (this.B == 64) ? 3 : 1;
                if (this.k) {
                    this.c.w = 3;
                    for (int n25 = 0; n25 < w - 2; n25 += n24) {
                        for (int n26 = 0; n26 < 3; ++n26) {
                            this.c.a[n26][3] = a[n25 + n26][3];
                            this.c.a[n26][4] = a[n25 + n26][4];
                            this.c.a[n26][5] = a[n25 + n26][5];
                            this.c.a[n26][6] = a[n25 + n26][6];
                            this.c.a[n26][0] = a[n25 + n26][0];
                            this.c.a[n26][1] = a[n25 + n26][1];
                            this.c.a[n26][2] = a[n25 + n26][2];
                            if (this.d.d) {
                                this.c.a[n26][7] = a[n25 + n26][7];
                                this.c.a[n26][8] = a[n25 + n26][8];
                            }
                        }
                        this.c.a();
                    }
                }
                if (this.d) {
                    if (this.B == 65) {
                        this.a(a, w - 1, 1, 1, 0);
                    }
                    else {
                        this.a(a, w - 1, 1, 1, 3);
                    }
                    this.a(a, w - 2, 2, n24, 0);
                }
            }
            case 128:
            case 129: {
                final int n27 = (this.B == 128) ? 4 : 2;
                if (this.k) {
                    this.c.w = 4;
                    for (int n28 = 0; n28 < w - 3; n28 += n27) {
                        for (int n29 = 0; n29 < 4; ++n29) {
                            this.c.a[n29][3] = a[n28 + n29][3];
                            this.c.a[n29][4] = a[n28 + n29][4];
                            this.c.a[n29][5] = a[n28 + n29][5];
                            this.c.a[n29][6] = a[n28 + n29][6];
                            this.c.a[n29][0] = a[n28 + n29][0];
                            this.c.a[n29][1] = a[n28 + n29][1];
                            this.c.a[n29][2] = a[n28 + n29][2];
                            if (this.d.d) {
                                this.c.a[n29][7] = a[n28 + n29][7];
                                this.c.a[n29][8] = a[n28 + n29][8];
                            }
                        }
                        this.c.a();
                    }
                }
                if (this.d) {
                    if (this.B == 129) {
                        this.a(a, w - 1, 1, 1, 0);
                    }
                    else {
                        this.a(a, w, 1, 1, 4);
                    }
                    this.a(a, w - 2, 3, n27, 0);
                }
            }
            case 256:
            case 257: {
                if (this.k) {
                    final boolean m = this.j;
                    if (this.d && !j.a[4]) {
                        this.j = false;
                    }
                    this.a();
                    if (this.d && !j.a[4]) {
                        this.j = m;
                    }
                }
                if (this.d) {
                    this.a(a, w - 1, 1, 1, 0);
                    this.b[0] = a[w - 1];
                    this.b[1] = a[0];
                    this.a(this.b, 1, 1, 1, 0);
                }
            }
            case 258: {
                if (this.k) {
                    this.d.a();
                    if (this.d) {
                        this.d.c();
                    }
                }
                if (this.d) {
                    this.a(a, w - 1, 1, 1, 0);
                    this.b[0] = a[w - 1];
                    this.b[1] = a[0];
                    this.a(this.b, 1, 1, 1, 0);
                    break;
                }
                break;
            }
        }
    }
    
    private final boolean a() {
        final float[][] a = this.d.a;
        final int w = this.d.w;
        int n = 0;
        if (w < 3) {
            return true;
        }
        for (int i = 0; i < w; ++i) {
            final int n2 = (i + 1) % w;
            final int n3 = (i + 2) % w;
            final float n4;
            if ((n4 = (a[n2][0] - a[i][0]) * (a[n3][1] - a[n2][1]) - (a[n2][1] - a[i][1]) * (a[n3][0] - a[n2][0])) < 0.0f) {
                n |= 0x1;
            }
            else if (n4 > 0.0f) {
                n |= 0x2;
            }
            if (n == 3) {
                return false;
            }
        }
        return n == 0 || true;
    }
    
    private final void a() {
        final float[][] a = this.d.a;
        if (this.a == null) {
            this.a = new m(this);
            this.c = new int[this.i];
        }
        this.a.a(3);
        if (this.a != null) {
            this.a.a(this.d.a);
        }
        this.a.e = this.d.e;
        this.a.g = this.d.g;
        this.a.d = this.d.d;
        this.a.c = this.d.c;
        final int w = this.d.w;
        final float[] array;
        (array = new float[2])[0] = a[0][0];
        array[1] = a[0][1];
        int n = 0;
        for (int i = 0; i < w; ++i) {
            if (a[i][1] < array[1] || (a[i][1] == array[1] && a[i][0] > array[0])) {
                n = i;
                array[0] = a[n][0];
                array[1] = a[n][1];
            }
        }
        final float[] array2 = new float[2];
        final float[] array3 = new float[2];
        final float[] array4 = new float[2];
        final int n2 = (n + (w - 1)) % w;
        for (int j = 0; j < 2; ++j) {
            array2[j] = a[n2][j];
            array3[j] = a[n][j];
            array4[j] = a[(n + 1) % w][j];
        }
        if (array2[0] * array3[1] - array2[1] * array3[0] + array2[1] * array4[0] - array2[0] * array4[1] + array3[0] * array4[1] - array4[0] * array3[1] <= 0.0f) {
            for (int k = 0; k < w; ++k) {
                this.c[k] = k;
            }
        }
        else {
            for (int l = 0; l < w; ++l) {
                this.c[l] = w - 1 - l;
            }
        }
        int n3 = w;
        int n4 = 2 * n3;
        int n5 = n3 - 1;
        while (n3 > 2) {
            boolean b = true;
            if (n4-- <= 0) {
                return;
            }
            int n6 = n5;
            if (n3 <= n6) {
                n6 = 0;
            }
            n5 = n6 + 1;
            if (n3 <= n5) {
                n5 = 0;
            }
            int n7 = n5 + 1;
            if (n3 <= n7) {
                n7 = 0;
            }
            final float n8 = -a[this.c[n6]][0];
            final float n9 = a[this.c[n6]][1];
            final float n10 = -a[this.c[n5]][0];
            final float n11 = a[this.c[n5]][1];
            final float n12 = -a[this.c[n7]][0];
            final float n13 = a[this.c[n7]][1];
            if (1.0E-4f > (n10 - n8) * (n13 - n9) - (n11 - n9) * (n12 - n8)) {
                continue;
            }
            for (int n14 = 0; n14 < n3; ++n14) {
                if (n14 != n6 && n14 != n5 && n14 != n7) {
                    final float n15 = -a[this.c[n14]][0];
                    final float n16 = a[this.c[n14]][1];
                    final float n17 = n12 - n10;
                    final float n18 = n13 - n11;
                    final float n19 = n8 - n12;
                    final float n20 = n9 - n13;
                    final float n21 = n10 - n8;
                    final float n22 = n11 - n9;
                    final float n23 = n15 - n8;
                    final float n24 = n16 - n9;
                    final float n25 = n15 - n10;
                    final float n26 = n16 - n11;
                    final float n27 = n15 - n12;
                    final float n28 = n16 - n13;
                    final float n29 = n17 * n26 - n18 * n25;
                    final float n30 = n21 * n24 - n22 * n23;
                    final float n31 = n19 * n28 - n20 * n27;
                    if (n29 >= 0.0f && n31 >= 0.0f && n30 >= 0.0f) {
                        b = false;
                    }
                }
            }
            if (!b) {
                continue;
            }
            final int[] array5;
            (array5 = new int[3])[0] = this.c[n6];
            array5[1] = this.c[n5];
            array5[2] = this.c[n7];
            this.a(array5);
            int n32 = n5;
            for (int n33 = n5 + 1; n33 < n3; ++n33) {
                this.c[n32] = this.c[n33];
                ++n32;
            }
            --n3;
            n4 = 2 * n3;
        }
    }
    
    private final void a(final int[] array) {
        for (int i = 0; i < 3; ++i) {
            final float[] array2 = this.d.a[array[i]];
            final float[] array3 = this.a.a[i];
            for (int j = 0; j < 24; ++j) {
                array3[j] = array2[j];
            }
        }
        this.a.a();
    }
    
    private final void b(final int n, final int n2, final float n3, final int n4) {
        final int n5 = n2 * this.b + n;
        this.e[n5] = n4;
        this.d[n5] = n3;
    }
    
    private final void a(final int n, final float n2, final int n3) {
        this.e[n] = n3;
        this.d[n] = n2;
    }
    
    private final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        this.b.a(4);
        this.b.c = false;
        final float n8 = this.aa / 2.0f;
        final float[] array;
        (array = this.b.a[0])[0] = n - n8;
        array[1] = n2 - n8;
        array[2] = n3;
        array[3] = n4;
        array[4] = n5;
        array[5] = n6;
        array[6] = n7;
        final float[] array2;
        (array2 = this.b.a[1])[0] = n + n8;
        array2[1] = n2 - n8;
        array2[2] = n3;
        final float[] array3;
        (array3 = this.b.a[2])[0] = n + n8;
        array3[1] = n2 + n8;
        array3[2] = n3;
        final float[] array4;
        (array4 = this.b.a[3])[0] = n - n8;
        array4[1] = n2 + n8;
        array4[2] = n3;
        this.b.a();
    }
    
    private final void c(final int n, final int n2, final int n3, final int n4) {
        final int a = this.a((float)n, (float)n2);
        final int a2 = this.a((float)n3, (float)n4);
        if ((a & a2) != 0x0) {
            return;
        }
        final int n5;
        int j;
        int k;
        int n6;
        int n7;
        if ((n5 = (a | a2)) != 0) {
            float max = 0.0f;
            float min = 1.0f;
            for (int i = 0; i < 4; ++i) {
                if ((n5 >> i) % 2 == 1) {
                    final float a3 = this.a((float)n, n2, n3, n4, i + 1);
                    if ((a >> i) % 2 == 1) {
                        max = Math.max(a3, max);
                    }
                    else {
                        min = Math.min(a3, min);
                    }
                }
            }
            if (max > min) {
                return;
            }
            j = (int)(n + max * (n3 - n));
            k = (int)(n2 + max * (n4 - n2));
            n6 = (int)(n + min * (n3 - n));
            n7 = (int)(n2 + min * (n4 - n2));
        }
        else {
            j = n;
            n6 = n3;
            k = n2;
            n7 = n4;
        }
        boolean b = false;
        int n8 = n7 - k;
        int n9 = n6 - j;
        if (Math.abs(n8) > Math.abs(n9)) {
            final int n10 = n8;
            n8 = n9;
            n9 = n10;
            b = true;
        }
        int n11;
        if (n9 == 0) {
            n11 = 0;
        }
        else {
            n11 = (n8 << 16) / n9;
        }
        if (j == n6) {
            if (k > n7) {
                final int n12 = k;
                k = n7;
                n7 = n12;
            }
            int n13 = k * this.b + j;
            for (int l = k; l <= n7; ++l) {
                this.a(n13, 0.0f, this.E);
                n13 += this.b;
            }
            return;
        }
        if (k == n7) {
            if (j > n6) {
                final int n14 = j;
                j = n6;
                n6 = n14;
            }
            int n15 = k * this.b + j;
            for (int n16 = j; n16 <= n6; ++n16) {
                this.a(n15++, 0.0f, this.E);
            }
            return;
        }
        if (b) {
            if (n9 > 0) {
                final int n17 = n9 + k;
                int n18 = 32768 + (j << 16);
                while (k <= n17) {
                    this.b(n18 >> 16, k, 0.0f, this.E);
                    n18 += n11;
                    ++k;
                }
                return;
            }
            final int n19 = n9 + k;
            int n20 = 32768 + (j << 16);
            while (k >= n19) {
                this.b(n20 >> 16, k, 0.0f, this.E);
                n20 -= n11;
                --k;
            }
        }
        else {
            if (n9 > 0) {
                final int n21 = n9 + j;
                int n22 = 32768 + (k << 16);
                while (j <= n21) {
                    this.b(j, n22 >> 16, 0.0f, this.E);
                    n22 += n11;
                    ++j;
                }
                return;
            }
            final int n23 = n9 + j;
            int n24 = 32768 + (k << 16);
            while (j >= n23) {
                this.b(j, n24 >> 16, 0.0f, this.E);
                n24 -= n11;
                --j;
            }
        }
    }
    
    private final int a(final float n, final float n2) {
        final int n3 = ((n2 < 0.0f) ? 8 : 0) | ((n2 > this.t) ? 4 : 0) | ((n < 0.0f) ? 2 : 0);
        boolean b = false;
        if (n > this.v) {
            b = true;
        }
        return n3 | (b ? 1 : 0);
    }
    
    private final float a(final float n, final float n2, final float n3, final float n4, final int n5) {
        switch (n5) {
            case 4: {
                return -n2 / (n4 - n2);
            }
            case 3: {
                return (this.t - n2) / (n4 - n2);
            }
            case 2: {
                return -n / (n3 - n);
            }
            case 1: {
                return (this.v - n) / (n3 - n);
            }
            default: {
                return -1.0f;
            }
        }
    }
    
    private final boolean a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7) {
        if (this.aa < 2.0f && !j.a[0]) {
            final int e = this.E;
            this.E = a(n5, n6, n7);
            this.c((int)n, (int)n2, (int)n3, (int)n4);
            this.E = e;
            return true;
        }
        return false;
    }
    
    private final void b(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        final m b = this.b;
        boolean c = false;
        if (n3 != n9 || n4 != n10 || n5 != n11 || n6 != n12) {
            c = true;
        }
        b.c = c;
        this.b.g = false;
        if (!this.b.c && this.a(n, n2, n7, n8, n3, n4, n5)) {
            return;
        }
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n16;
        final float n15 = (n16 = this.aa / this.b(n13 * n13 + n14 * n14)) * n14;
        final float n17 = n16 * n13;
        final float n18 = n16 * n14;
        final float n19 = n16 * n13;
        this.b.a(4);
        final float[] array;
        (array = this.b.a[0])[0] = n + n15;
        array[1] = n2 - n17;
        array[3] = n3;
        array[4] = n4;
        array[5] = n5;
        array[6] = n6;
        final float[] array2;
        (array2 = this.b.a[1])[0] = n - n15;
        array2[1] = n2 + n17;
        array2[3] = n3;
        array2[4] = n4;
        array2[5] = n5;
        array2[6] = n6;
        final float[] array3;
        (array3 = this.b.a[2])[0] = n7 - n18;
        array3[1] = n8 + n19;
        array3[3] = n9;
        array3[4] = n10;
        array3[5] = n11;
        array3[6] = n12;
        final float[] array4;
        (array4 = this.b.a[3])[0] = n7 + n18;
        array4[1] = n8 - n19;
        array4[3] = n9;
        array4[4] = n10;
        array4[5] = n11;
        array4[6] = n12;
        this.b.a();
    }
    
    private final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        final m b = this.b;
        boolean c = false;
        if (n4 != n10 || n5 != n11 || n6 != n12) {
            c = true;
        }
        b.c = c;
        if (!this.b.c && this.a(n, n2, n7, n8, n4, n5, n6)) {
            return;
        }
        this.b.g = true;
        final float n13 = n7 - n + 1.0E-4f;
        final float n14 = n8 - n2 + 1.0E-4f;
        final float n16;
        final float n15 = (n16 = this.aa / this.b(n13 * n13 + n14 * n14)) * n14;
        final float n17 = n16 * n13;
        final float n18 = n16 * n14;
        final float n19 = n16 * n13;
        this.b.a(4);
        final float[] array;
        (array = this.b.a[0])[0] = n + n15;
        array[1] = n2 - n17;
        array[2] = n3;
        array[3] = n4;
        array[4] = n5;
        array[5] = n6;
        final float[] array2;
        (array2 = this.b.a[1])[0] = n - n15;
        array2[1] = n2 + n17;
        array2[2] = n3;
        array2[3] = n4;
        array2[4] = n5;
        array2[5] = n6;
        final float[] array3;
        (array3 = this.b.a[2])[0] = n7 - n18;
        array3[1] = n8 + n19;
        array3[2] = n9;
        array3[3] = n10;
        array3[4] = n11;
        array3[5] = n12;
        final float[] array4;
        (array4 = this.b.a[3])[0] = n7 + n18;
        array4[1] = n8 - n19;
        array4[2] = n9;
        array4[3] = n10;
        array4[4] = n11;
        array4[5] = n12;
        this.b.a();
    }
    
    private final void a(final float[][] array, final int n, final int n2, final int n3, final int n4) {
        if (this.aa < 2.0f) {
            for (int i = 0; i < n; i += n3) {
                if (n4 == 0 || (i + n2) % n4 != 0) {
                    final float[] array2 = array[i];
                    final float[] array3 = array[i + n2];
                    this.a.a();
                    this.a.a(array2[12], array2[13], array2[14], array2[15], array3[12], array3[13], array3[14], array3[15]);
                    this.a.a(array2[0], array2[1], array2[2], array3[0], array3[1], array3[2]);
                    this.a.b();
                }
            }
            return;
        }
        if (this.u != 3 && this.i) {
            if (this.aa < 2.0f && !this.h && !this.b) {
                for (int j = 0; j < n; j += n3) {
                    if (n4 == 0 || (j + n2) % n4 != 0) {
                        this.c((int)array[j][0], (int)array[j][1], (int)array[j + n2][0], (int)array[j + n2][1]);
                    }
                }
            }
            else {
                for (int k = 0; k < n; k += n3) {
                    if (n4 == 0 || (k + n2) % n4 != 0) {
                        final float[] array4 = array[k];
                        final float[] array5 = array[k + n2];
                        this.b(array4[0], array4[1], array4[12], array4[13], array4[14], array4[15], array5[0], array5[1], array5[12], array5[13], array5[14], array5[15]);
                    }
                }
            }
            return;
        }
        for (int l = 0; l < n; l += n3) {
            if (n4 == 0 || (l + n2) % n4 != 0) {
                final float[] array6 = array[l];
                final float[] array7 = array[l + n2];
                this.a(array6[0], array6[1], array6[2], array6[12], array6[13], array6[14], array7[0], array7[1], array7[2], array7[12], array7[13], array7[14]);
            }
        }
    }
    
    private final void a(final int n, final int n2, final float n3, final int n4) {
        if (n < 0 || n > this.v || n2 < 0 || n2 > this.t) {
            return;
        }
        final int n5 = n2 * this.b + n;
        if ((n4 & 0xFF000000) == 0xFF000000) {
            this.e[n5] = n4;
        }
        else {
            final int n7;
            final int n6 = (n7 = (n4 >> 24 & 0xFF)) ^ 0xFF;
            final int e = this.E;
            final int n8 = this.e[n5];
            this.e[n5] = (0xFF000000 | (n6 * (n8 >> 16 & 0xFF) + n7 * (e >> 16 & 0xFF) & 0xFF00) << 8 | (n6 * (n8 >> 8 & 0xFF) + n7 * (e >> 8 & 0xFF) & 0xFF00) | n6 * (n8 & 0xFF) + n7 * (e & 0xFF) >> 8);
        }
        this.d[n5] = n3;
    }
    
    private final void a(int n, int n2, int n3, int n4) {
        if (n4 < n2) {
            final int n5 = n2;
            n2 = n4;
            n4 = n5;
        }
        if (n3 < n) {
            final int n6 = n;
            n = n3;
            n3 = n6;
        }
        if (n > this.v || n3 < 0 || n2 > this.t || n4 < 0) {
            return;
        }
        if (this.k) {
            int n7 = n;
            int n8 = n2;
            int b = n3;
            int k = n4;
            if (n7 < 0) {
                n7 = 0;
            }
            if (b > this.b) {
                b = this.b;
            }
            if (n8 < 0) {
                n8 = 0;
            }
            if (k > this.k) {
                k = this.k;
            }
            final int n9 = b - n7;
            final int n10 = k - n8;
            final int[] array = new int[n9];
            for (int i = 0; i < n9; ++i) {
                array[i] = this.g;
            }
            int n11 = n8 * this.b + n7;
            for (int j = 0; j < n10; ++j) {
                System.arraycopy(array, 0, this.e, n11, n9);
                n11 += this.b;
            }
        }
        if (!j.a[2] && this.d) {
            if (this.aa == 1.0f) {
                this.c(n, n2, n3, n2);
                this.c(n3, n2, n3, n4);
                this.c(n3, n4, n, n4);
                this.c(n, n4, n, n2);
                return;
            }
            this.b(n, n2, this.v, this.l, this.ae, this.x, n3, n2, this.v, this.l, this.ae, this.x);
            this.b(n3, n2, this.v, this.l, this.ae, this.x, n3, n4, this.v, this.l, this.ae, this.x);
            this.b(n3, n4, this.v, this.l, this.ae, this.x, n, n4, this.v, this.l, this.ae, this.x);
            this.b(n, n4, this.v, this.l, this.ae, this.x, n, n2, this.v, this.l, this.ae, this.x);
        }
    }
    
    private final void d(int n, int n2, final int n3) {
        if (this.u == 2) {
            n = (int)this.a((float)n, (float)n2, 0.0f);
            n2 = (int)this.c(n, n2, 0.0f);
        }
        if (this.k) {
            this.a(n, n2, n3);
        }
        if (this.d) {
            this.c(n, n2, n3);
        }
    }
    
    private final void c(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = n3;
        int n5 = 1;
        int n6 = 2 * n3 - 1;
        int n7 = 0;
        while (i < n4) {
            this.a(n + i, n2 + n4, 0.0f, this.E);
            this.a(n + n4, n2 - i, 0.0f, this.E);
            this.a(n - i, n2 - n4, 0.0f, this.E);
            this.a(n - n4, n2 + i, 0.0f, this.E);
            ++i;
            n7 += n5;
            n5 += 2;
            if (n6 < 2 * n7) {
                --n4;
                n7 -= n6;
                n6 -= 2;
            }
            if (i > n4) {
                return;
            }
            this.a(n + n4, n2 + i, 0.0f, this.E);
            this.a(n + i, n2 - n4, 0.0f, this.E);
            this.a(n - n4, n2 - i, 0.0f, this.E);
            this.a(n - i, n2 + n4, 0.0f, this.E);
        }
    }
    
    private final void a(final int n, final int n2, final int n3) {
        int i = 0;
        int n4 = n3;
        int n5 = 1;
        int n6 = 2 * n3 - 1;
        int n7 = 0;
        while (i < n4) {
            for (int j = n; j < n + i; ++j) {
                this.a(j, n2 + n4, 0.0f, this.g);
            }
            for (int k = n; k < n + n4; ++k) {
                this.a(k, n2 - i, 0.0f, this.g);
            }
            for (int l = n - i; l < n; ++l) {
                this.a(l, n2 - n4, 0.0f, this.g);
            }
            for (int n8 = n - n4; n8 < n; ++n8) {
                this.a(n8, n2 + i, 0.0f, this.g);
            }
            ++i;
            n7 += n5;
            n5 += 2;
            if (n6 < 2 * n7) {
                --n4;
                n7 -= n6;
                n6 -= 2;
            }
            if (i > n4) {
                return;
            }
            for (int n9 = n; n9 < n + n4; ++n9) {
                this.a(n9, n2 + i, 0.0f, this.g);
            }
            for (int n10 = n; n10 < n + i; ++n10) {
                this.a(n10, n2 - n4, 0.0f, this.g);
            }
            for (int n11 = n - n4; n11 < n; ++n11) {
                this.a(n11, n2 - i, 0.0f, this.g);
            }
            for (int n12 = n - i; n12 < n; ++n12) {
                this.a(n12, n2 + n4, 0.0f, this.g);
            }
        }
    }
    
    private final void b(final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (b) {
            for (int i = n - n3 + 1; i < n + n3; ++i) {
                this.a(i, n2 - n4, 0.0f, this.g);
                this.a(i, n2 + n4, 0.0f, this.g);
            }
            return;
        }
        this.a(n - n3, n2 + n4, 0.0f, this.E);
        this.a(n + n3, n2 + n4, 0.0f, this.E);
        this.a(n - n3, n2 - n4, 0.0f, this.E);
        this.a(n + n3, n2 - n4, 0.0f, this.E);
    }
    
    private final void a(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int n5 = n3 * n3;
        final int n6 = n4 * n4;
        int n7 = 0;
        int i = n4;
        int n8 = n5 * (1 - 2 * n4) + 2 * n6;
        int n9 = n6 - 2 * n5 * (2 * n4 - 1);
        this.b(n, n2, 0, i, b);
        do {
            if (n8 < 0) {
                n8 += 2 * n6 * (2 * n7 + 3);
                n9 += 4 * n6 * (n7 + 1);
                ++n7;
            }
            else if (n9 < 0) {
                n8 += 2 * n6 * (2 * n7 + 3) - 4 * n5 * (i - 1);
                n9 += 4 * n6 * (n7 + 1) - 2 * n5 * (2 * i - 3);
                ++n7;
                --i;
            }
            else {
                n8 -= 4 * n5 * (i - 1);
                n9 -= 2 * n5 * (2 * i - 3);
                --i;
            }
            this.b(n, n2, n7, i, b);
        } while (i > 0);
    }
    
    private final void b(int n, int n2, final int n3, final int n4) {
        if (this.u == 2) {
            n = (int)this.a((float)n, (float)n2, 0.0f);
            n2 = (int)this.c(n, n2, 0.0f);
        }
        if (this.k) {
            this.a(n, n2, n3, n4, true);
        }
        if (this.d) {
            this.a(n, n2, n3, n4, false);
        }
    }
    
    public final void b(final float n, final float n2, final float n3, final float n4) {
        this.c(32);
        this.c(n, n2);
        this.c(n3, n4);
        this.f();
    }
    
    public final void c(float n, float n2, float n3, float n4) {
        switch (this.H) {
            case 0: {
                n3 += n;
                n4 += n2;
                break;
            }
            case 2: {
                final float n5 = n3;
                final float n6 = n4;
                n3 = n + n5;
                n4 = n2 + n6;
                n -= n5;
                n2 -= n6;
                break;
            }
            case 3: {
                final float n7 = n3 / 2.0f;
                final float n8 = n4 / 2.0f;
                n3 = n + n7;
                n4 = n2 + n8;
                n -= n7;
                n2 -= n8;
                break;
            }
        }
        if (this.u == 0 && !this.h && !this.m) {
            this.a((int)n, (int)n2, (int)n3, (int)n4);
            return;
        }
        this.c(128);
        this.c(n, n2);
        this.c(n3, n2);
        this.c(n3, n4);
        this.c(n, n4);
        this.f();
    }
    
    public final void a(float n, float n2, float n3, float n4) {
        switch (this.f) {
            case 3: {
                n3 /= 2.0f;
                n4 /= 2.0f;
                break;
            }
            case 0: {
                n3 /= 2.0f;
                n4 /= 2.0f;
                n += n3;
                n2 += n4;
                break;
            }
            case 1: {
                n3 = (n3 - n) / 2.0f;
                n4 = (n4 - n2) / 2.0f;
                n += n3;
                n2 += n3;
                break;
            }
        }
        final int n5 = (int)(4 + Math.sqrt(n3 + n4) * 3);
        boolean b = false;
        if (!this.h && !this.j && this.aa == 1.0f && !this.m && !this.a) {
            b = true;
        }
        final boolean b2 = b;
        boolean b3 = false;
        if (this.u == 0 || (this.u == 2 && this.O == this.n && this.O == 1.0f)) {
            b3 = true;
        }
        final boolean b4 = b3;
        if (b2 && b4) {
            if (n3 == n4) {
                this.d((int)n, (int)n2, (int)n3);
            }
            else {
                this.b((int)n, (int)n2, (int)n3, (int)n4);
            }
            return;
        }
        final float n6 = 720.0f / n5;
        float n7 = 0.0f;
        this.c(256);
        for (int i = 0; i < n5; ++i) {
            this.c(n + j.i[(int)n7] * n3, n2 + j.g[(int)n7] * n4);
            n7 += n6;
        }
        if (!j.a[2]) {
            this.c(n + j.i[0] * n3, n2 + j.g[0] * n4);
        }
        this.f();
    }
    
    public final void a(final d a) {
        if (a == null) {
            System.err.println("Ignoring improperly loaded font in textFont()");
            return;
        }
        this.a = a;
        if (this.L != 2) {
            this.a.a();
        }
        else {
            this.a.d = this.a.d;
        }
        this.a.b();
    }
    
    public final void b(final int l) {
        this.L = l;
        if (l == 2 && this.a != null) {
            this.a.d = this.a.d;
            this.a.b();
        }
    }
    
    public final void a(final String s, final float n, final float n2) {
        this.a(s, n, n2, 0.0f);
    }
    
    public final void a(final String s, float n, final float n2, final float n3) {
        if (this.a == null) {
            System.err.println("text(): first set a font before drawing text");
            return;
        }
        if (this.I == 1) {
            n -= this.a.a(s) / 2.0f;
        }
        else if (this.I == 2) {
            n -= this.a.a(s);
        }
        this.a.a(s, n, n2, n3, this);
    }
    
    public final void a(final int n, final float n2, final float n3) {
        this.a(String.valueOf(n), n2, n3, 0.0f);
    }
    
    public final void g() {
        this.u = 0;
        this.O = 1.0f;
        this.y = 0.0f;
        this.W = 0.0f;
        this.R = 0.0f;
        this.M = 0.0f;
        this.n = 1.0f;
        this.T = 0.0f;
        this.k = 0.0f;
        this.C = 0.0f;
        this.a = 0.0f;
        this.f = 1.0f;
        this.z = 0.0f;
        this.e = 0.0f;
        this.E = 0.0f;
        this.J = 0.0f;
        this.s = 1.0f;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12, final float n13, final float n14, final float n15, final float n16) {
        final float o = this.O * n + this.y * n5 + this.W * n9 + this.R * n13;
        final float y = this.O * n2 + this.y * n6 + this.W * n10 + this.R * n14;
        final float w = this.O * n3 + this.y * n7 + this.W * n11 + this.R * n15;
        final float r = this.O * n4 + this.y * n8 + this.W * n12 + this.R * n16;
        final float m = this.M * n + this.n * n5 + this.T * n9 + this.k * n13;
        final float n17 = this.M * n2 + this.n * n6 + this.T * n10 + this.k * n14;
        final float t = this.M * n3 + this.n * n7 + this.T * n11 + this.k * n15;
        final float k = this.M * n4 + this.n * n8 + this.T * n12 + this.k * n16;
        final float c = this.C * n + this.a * n5 + this.f * n9 + this.z * n13;
        final float a = this.C * n2 + this.a * n6 + this.f * n10 + this.z * n14;
        final float f = this.C * n3 + this.a * n7 + this.f * n11 + this.z * n15;
        final float z = this.C * n4 + this.a * n8 + this.f * n12 + this.z * n16;
        final float e = this.e * n + this.E * n5 + this.J * n9 + this.s * n13;
        final float e2 = this.e * n2 + this.E * n6 + this.J * n10 + this.s * n14;
        final float j = this.e * n3 + this.E * n7 + this.J * n11 + this.s * n15;
        final float s = this.e * n4 + this.E * n8 + this.J * n12 + this.s * n16;
        this.O = o;
        this.y = y;
        this.W = w;
        this.R = r;
        this.M = m;
        this.n = n17;
        this.T = t;
        this.k = k;
        this.C = c;
        this.a = a;
        this.f = f;
        this.z = z;
        this.e = e;
        this.E = e2;
        this.J = j;
        this.s = s;
    }
    
    public final void a(final int x) {
        this.x = x;
        if (this.x == 3) {
            this.g();
            this.d(this.af, this.A, this.ag, this.p);
            this.a(this.V, this.g, this.d, this.V, this.g, 0.0f, 0.0f, 1.0f, 0.0f);
            this.p();
            return;
        }
        if (this.x == 2) {
            this.g();
            this.b(0.0f, this.b, 0.0f, this.k, -10.0f, 10.0f);
            this.p();
        }
    }
    
    public final void p() {
        this.S = this.O;
        this.B = this.y;
        this.H = this.W;
        this.c = this.R;
        this.Y = this.M;
        this.P = this.n;
        this.ah = this.T;
        this.b = this.k;
        this.ai = this.C;
        this.j = this.a;
        this.Q = this.f;
        this.F = this.z;
        this.I = this.e;
        this.K = this.E;
        this.U = this.J;
        this.ab = this.s;
        this.g();
    }
    
    public final float a(final float n, final float n2, final float n3) {
        final float n4 = this.O * n + this.y * n2 + this.W * n3 + this.R;
        final float n5 = this.M * n + this.n * n2 + this.T * n3 + this.k;
        final float n6 = this.C * n + this.a * n2 + this.f * n3 + this.z;
        final float n7 = this.e * n + this.E * n2 + this.J * n3 + this.s;
        float n8 = this.S * n4 + this.B * n5 + this.H * n6 + this.c * n7;
        final float n9;
        if ((n9 = this.I * n4 + this.K * n5 + this.U * n6 + this.ab * n7) != 0.0f) {
            n8 /= n9;
        }
        return this.b * (1.0f + n8) / 2.0f;
    }
    
    public final float c(final float n, final float n2, final float n3) {
        final float n4 = this.O * n + this.y * n2 + this.W * n3 + this.R;
        final float n5 = this.M * n + this.n * n2 + this.T * n3 + this.k;
        final float n6 = this.C * n + this.a * n2 + this.f * n3 + this.z;
        final float n7 = this.e * n + this.E * n2 + this.J * n3 + this.s;
        float n8 = this.Y * n4 + this.P * n5 + this.ah * n6 + this.b * n7;
        final float n9;
        if ((n9 = this.I * n4 + this.K * n5 + this.U * n6 + this.ab * n7) != 0.0f) {
            n8 /= n9;
        }
        return this.k * (1.0f + n8) / 2.0f;
    }
    
    public final void b(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.a(2.0f / (n2 - n), 0.0f, 0.0f, -(n2 + n) / (n2 - n), 0.0f, 2.0f / (n4 - n3), 0.0f, -(n4 + n3) / (n4 - n3), 0.0f, 0.0f, -2.0f / (n6 - n5), -(n6 + n5) / (n6 - n5), 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public final void d(final float n, final float n2, final float n3, final float n4) {
        final float n6;
        final float n5 = -(n6 = n3 * this.a(n * 3.1415927f / 360.0f));
        this.a(n5 * n2, n6 * n2, n5, n6, n3, n4);
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.a(2.0f * n5 / (n2 - n), 0.0f, (n2 + n) / (n2 - n), 0.0f, 0.0f, 2.0f * n5 / (n4 - n3), (n4 + n3) / (n4 - n3), 0.0f, 0.0f, 0.0f, -(n6 + n5) / (n6 - n5), -(2.0f * n6 * n5) / (n6 - n5), 0.0f, 0.0f, -1.0f, 0.0f);
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9) {
        float n10 = n - n4;
        float n11 = n2 - n5;
        float n12 = n3 - n6;
        final float b;
        if ((b = this.b(n10 * n10 + n11 * n11 + n12 * n12)) != 0.0f) {
            n10 /= b;
            n11 /= b;
            n12 /= b;
        }
        float n13 = n8 * n12 - n9 * n11;
        float n14 = -n7 * n12 + n9 * n10;
        float n15 = n7 * n11 - n8 * n10;
        float n16 = n11 * n15 - n12 * n14;
        float n17 = -n10 * n15 + n12 * n13;
        float n18 = n10 * n14 - n11 * n13;
        final float b2;
        if ((b2 = this.b(n13 * n13 + n14 * n14 + n15 * n15)) != 0.0f) {
            n13 /= b2;
            n14 /= b2;
            n15 /= b2;
        }
        final float b3;
        if ((b3 = this.b(n16 * n16 + n17 * n17 + n18 * n18)) != 0.0f) {
            n16 /= b3;
            n17 /= b3;
            n18 /= b3;
        }
        this.a(n13, n14, n15, 0.0f, n16, n17, n18, 0.0f, n10, n11, n12, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        this.a(-n, -n2, -n3);
    }
    
    public final void a(final float n, final float n2, final float n3) {
        this.u = 3;
        this.R += n * this.O + n2 * this.y + n3 * this.W;
        this.k += n * this.M + n2 * this.n + n3 * this.T;
        this.z += n * this.C + n2 * this.a + n3 * this.f;
        this.s += n * this.e + n2 * this.E + n3 * this.J;
    }
    
    public final void a(final int n, final float n2) {
        this.a(n, n2, n2, n2, n2);
    }
    
    public final void a(final int j, final float u, final float x, final float t, final float d) {
        this.J = j;
        this.u = u;
        this.X = x;
        this.t = t;
        this.D = d;
        boolean n = false;
        if (d != 1.0f || u != x || x != t || t != d) {
            n = true;
        }
        this.n = n;
    }
    
    public final void d(final float n) {
        this.b(n, this.D);
    }
    
    public final void b(float u, float d) {
        if (u > this.u) {
            u = this.u;
        }
        if (d > this.D) {
            d = this.D;
        }
        if (u < 0.0f) {
            u = 0.0f;
        }
        if (d < 0.0f) {
            d = 0.0f;
        }
        this.ac = (this.n ? (u / this.u) : u);
        this.i = this.ac;
        this.ad = this.ac;
        this.w = (this.n ? (d / this.D) : d);
        this.j = (int)(this.ac * 255.0f);
        this.y = (int)(this.i * 255.0f);
        this.G = (int)(this.ad * 255.0f);
        this.q = (int)(this.w * 255.0f);
        this.r = (this.q << 24 | this.j << 16 | this.y << 8 | this.G);
        boolean g = false;
        if (this.q != 255) {
            g = true;
        }
        this.g = g;
    }
    
    public final void e(final int r) {
        this.r = r;
        this.q = (r >> 24 & 0xFF);
        this.j = (r >> 16 & 0xFF);
        this.y = (r >> 8 & 0xFF);
        this.G = (r & 0xFF);
        this.w = this.q / 255.0f;
        this.ac = this.j / 255.0f;
        this.i = this.y / 255.0f;
        this.ad = this.G / 255.0f;
        boolean g = false;
        if (this.q != 255) {
            g = true;
        }
        this.g = g;
    }
    
    public final void l() {
        this.k = true;
        this.f = true;
        this.v = this.ac;
        this.l = this.i;
        this.ae = this.ad;
        this.x = this.w;
        this.p = this.j;
        this.n = this.y;
        this.F = this.G;
        this.a = this.q;
        this.g = this.r;
        this.m = this.g;
    }
    
    public final void d() {
        this.d = true;
        this.b = true;
        this.q = this.ac;
        this.G = this.i;
        this.Z = this.ad;
        this.o = this.w;
        this.E = this.r;
        this.a = this.g;
    }
    
    public final void b() {
        this.o = this.r;
    }
    
    public final void g(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.u) {
            this.c((float)n);
            return;
        }
        this.e(n);
        this.l();
    }
    
    public final void c(final float n) {
        this.d(n);
        this.l();
    }
    
    public final void c() {
        this.d = false;
    }
    
    public final void d(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.u) {
            this.a((float)n);
            return;
        }
        this.e(n);
        this.d();
    }
    
    public final void a(final float n) {
        this.d(n);
        this.d();
    }
    
    public final void f(final int n) {
        if ((n & 0xFF000000) == 0x0 && n <= this.u) {
            this.b((float)n);
        }
        else {
            this.e(n);
            this.b();
        }
        this.m();
    }
    
    public final void b(final float n) {
        this.d(n);
        this.b();
        this.m();
    }
    
    public final void m() {
        for (int i = 0; i < this.m; ++i) {
            this.e[i] = this.o;
            this.d[i] = Float.MAX_VALUE;
        }
    }
    
    public final void k() {
        this.h = false;
    }
    
    public final void a(final int n, final String s) {
        switch (n) {
            case 0: {}
            case 1: {
                System.err.println("bagel complaint: " + s);
            }
            case 2: {
                System.err.println("bagel problem: " + s);
                break;
            }
        }
    }
    
    private static final int a(final float n, final float n2, final float n3) {
        return 0xFF000000 | (int)(255.0f * n) << 16 | (int)(255.0f * n2) << 8 | (int)(255.0f * n3);
    }
    
    private final float b(final float n, final float n2, final float n3) {
        return (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
    }
    
    private final float b(final float n) {
        return (float)Math.sqrt(n);
    }
    
    private final float a(float n) {
        if (this.c == 1) {
            n *= 0.017453292f;
        }
        return (float)Math.tan(n);
    }
    
    private final void q() {
        this.i = 512;
        this.a = new float[512][24];
        this.b = new int[512];
        this.b = new int[512][4];
        this.a = new int[256][5];
        this.e = true;
        this.a = new i[3];
        this.c = false;
        final float[][] array = { { -1.0f, 3, -3.0f, 1.0f }, { 3, -6.0f, 3, 0.0f }, { -3.0f, 3, 0.0f, 0.0f }, null };
        final int n = 3;
        final float[] array2 = new float[4];
        array2[0] = 1.0f;
        array[n] = array2;
    }
    
    public j() {
        this.q();
    }
    
    public j(final int n, final int n2) {
        this.q();
        this.b(n, n2);
        this.o();
    }
    
    static {
        j.a = new boolean[7];
        g = new float[720];
        i = new float[720];
        for (int j = 0; j < 720; ++j) {
            j.g[j] = (float)Math.sin(j * 0.017453292f * 0.5f);
            j.i[j] = (float)Math.cos(j * 0.017453292f * 0.5f);
        }
    }
}
