// 
// Decompiled by Procyon v0.5.30
// 

class a5 implements h
{
    c a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    float k;
    float l;
    float m;
    private int n;
    int p;
    
    public void a(final c a) {
        this.a = a;
    }
    
    public c a() {
        return this.a;
    }
    
    public void a(final t t) {
    }
    
    void b() {
        this.n = blaze3d.c;
    }
    
    boolean c() {
        return this.n > this.p;
    }
    
    void d() {
        this.p = blaze3d.c;
    }
    
    public a5(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final float n10, final float n11, final float n12) {
        this.a(n, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12);
    }
    
    public a5() {
        this(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }
    
    public a5(final a5 a5) {
        if (a5 == null) {
            return;
        }
        this.a(a5.b, a5.c, a5.d, a5.e, a5.f, a5.g, a5.h, a5.i, a5.j, a5.k, a5.l, a5.m);
    }
    
    public a5(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.a(n, n2, n3);
        this.c(n4, n5, n6);
    }
    
    void a(final float b, final float c, final float d, final float e, final float f, final float g, final float h, final float i, final float j, final float k, final float l, final float m) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
    }
    
    public void a(final float k, final float l, final float m) {
        this.k = k;
        this.l = l;
        this.m = m;
        this.b();
    }
    
    public void c(final float n, final float n2, final float n3) {
        final float n4 = n * 0.017453292f;
        final float n5 = n2 * 0.017453292f;
        final float n6 = n3 * 0.017453292f;
        final float n7 = (float)Math.sin(n4);
        final float n8 = (float)Math.sin(n5);
        final float n9 = (float)Math.sin(n6);
        final float n10 = (float)Math.cos(n4);
        final float n11 = (float)Math.cos(n5);
        final float n12 = (float)Math.cos(n6);
        this.b = n11 * n12;
        this.c = n11 * n9;
        this.d = -n8;
        this.e = n7 * n8 * n12 - n10 * n9;
        this.f = n7 * n8 * n9 + n10 * n12;
        this.g = n7 * n11;
        this.h = n10 * n8 * n12 + n7 * n9;
        this.i = n10 * n8 * n9 - n7 * n12;
        this.j = n10 * n11;
        this.b();
    }
    
    public void e(final float n, final float n2, final float n3) {
        this.k += n;
        this.l += n2;
        this.m += n3;
        this.b();
    }
    
    public void f(final float n, final float n2, final float n3) {
        final a5 a5 = new a5(0.0f, 0.0f, 0.0f, n, n2, n3);
        final a5 a6;
        (a6 = new a5()).b = a5.b * this.b + a5.c * this.e + a5.d * this.h;
        a6.c = a5.b * this.c + a5.c * this.f + a5.d * this.i;
        a6.d = a5.b * this.d + a5.c * this.g + a5.d * this.j;
        a6.e = a5.e * this.b + a5.f * this.e + a5.g * this.h;
        a6.f = a5.e * this.c + a5.f * this.f + a5.g * this.i;
        a6.g = a5.e * this.d + a5.f * this.g + a5.g * this.j;
        a6.h = a5.h * this.b + a5.i * this.e + a5.j * this.h;
        a6.i = a5.h * this.c + a5.i * this.f + a5.j * this.i;
        a6.j = a5.h * this.d + a5.i * this.g + a5.j * this.j;
        this.b = a6.b;
        this.c = a6.c;
        this.d = a6.d;
        this.e = a6.e;
        this.f = a6.f;
        this.g = a6.g;
        this.h = a6.h;
        this.i = a6.i;
        this.j = a6.j;
        this.b();
    }
    
    public bb a(final bb bb) {
        final bb bb2 = new bb();
        bb2.a = bb.a * this.b + bb.b * this.e + bb.c * this.h + this.k;
        bb2.b = bb.a * this.c + bb.b * this.f + bb.c * this.i + this.l;
        bb2.c = bb.a * this.d + bb.b * this.g + bb.c * this.j + this.m;
        return bb2;
    }
    
    public bb b(final bb bb) {
        final bb bb2 = new bb();
        bb2.a = bb.a * this.b + bb.b * this.c + bb.c * this.d;
        bb2.b = bb.a * this.e + bb.b * this.f + bb.c * this.g;
        bb2.c = bb.a * this.h + bb.b * this.i + bb.c * this.j;
        return bb2;
    }
    
    public a5 e() {
        final a5 a5 = new a5();
        final float n = this.b * (this.f * this.j - this.g * this.i) - this.c * (this.e * this.j - this.g * this.h) + this.d * (this.e * this.i - this.f * this.h);
        if (n == 0.0f) {
            return null;
        }
        a5.b = (this.f * this.j - this.g * this.i) / n;
        a5.c = (this.d * this.i - this.c * this.j) / n;
        a5.d = (this.c * this.g - this.d * this.f) / n;
        a5.e = (this.g * this.h - this.e * this.j) / n;
        a5.f = (this.b * this.j - this.d * this.h) / n;
        a5.g = (this.d * this.e - this.b * this.g) / n;
        a5.h = (this.e * this.i - this.f * this.h) / n;
        a5.i = (this.c * this.h - this.b * this.i) / n;
        a5.j = (this.b * this.f - this.c * this.e) / n;
        a5.k = (this.k * (this.g * this.i - this.f * this.j) + this.l * (this.e * this.j - this.h * this.g) + this.m * (this.h * this.f - this.e * this.i)) / n;
        a5.l = (this.k * (this.c * this.j - this.i * this.d) + this.l * (this.h * this.d - this.b * this.j) + this.m * (this.b * this.i - this.h * this.c)) / n;
        a5.m = (this.k * (this.f * this.d - this.c * this.g) + this.l * (this.b * this.g - this.e * this.d) + this.m * (this.e * this.c - this.b * this.f)) / n;
        return a5;
    }
    
    public a5 b(final a5 a5) {
        final boolean l = c.l;
        final a5 a6 = new a5();
        a6.b = this.b * a5.b + this.c * a5.e + this.d * a5.h;
        a6.c = this.b * a5.c + this.c * a5.f + this.d * a5.i;
        a6.d = this.b * a5.d + this.c * a5.g + this.d * a5.j;
        a6.e = this.e * a5.b + this.f * a5.e + this.g * a5.h;
        a6.f = this.e * a5.c + this.f * a5.f + this.g * a5.i;
        a6.g = this.e * a5.d + this.f * a5.g + this.g * a5.j;
        a6.h = this.h * a5.b + this.i * a5.e + this.j * a5.h;
        a6.i = this.h * a5.c + this.i * a5.f + this.j * a5.i;
        a6.j = this.h * a5.d + this.i * a5.g + this.j * a5.j;
        a6.k = this.k * a5.b + this.l * a5.e + this.m * a5.h + a5.k;
        a6.l = this.k * a5.c + this.l * a5.f + this.m * a5.i + a5.l;
        a6.m = this.k * a5.d + this.l * a5.g + this.m * a5.j + a5.m;
        final a5 a7 = a6;
        if (d.g != 0) {
            c.l = !l;
        }
        return a7;
    }
    
    static int a(final double[] array, final double[][] array2) {
        final boolean l = c.l;
        if (array[0] != 1.0) {
            int n = 1;
            while (true) {
                Label_0036: {
                    if (!l) {
                        break Label_0036;
                    }
                    array[n] /= array[0];
                    ++n;
                }
                if (n < 4) {
                    continue;
                }
                break;
            }
        }
        array[0] = 1.0;
        final double n2 = array[1] / 3.0;
        final double n3 = n2 * array[1];
        double n4 = 0.5 * (n2 * (n3 / 1.5 - array[2]) + array[3]);
        final double n5 = (n3 - array[2]) / 3.0;
        double n6 = n5 * n5 * n5;
        double n7 = n4 * n4 - n6;
        if (n7 >= 0.0) {
            final double pow = Math.pow(Math.sqrt(n7) + Math.abs(n4), 0.3333333333333333);
            if (pow != 0.0) {
                Label_0169: {
                    if (n4 > 0.0) {
                        n4 = -pow;
                        if (!l) {
                            break Label_0169;
                        }
                    }
                    n4 = pow;
                }
                n6 = n5 / n4;
            }
            final double[] array3 = array2[2];
            final int n8 = 2;
            final double n9 = Math.sqrt(0.75) * (n4 - n6);
            array3[n8] = n9;
            n7 = n9;
            n4 += n6;
            final double[] array4 = array2[1];
            final int n10 = 2;
            final double n11 = -0.5 * n4 - n2;
            array4[n10] = n11;
            final double n12 = n11;
            if ((n4 > 0.0 && n2 <= 0.0) || (n4 < 0.0 && n2 > 0.0)) {
                array2[1][1] = n12;
                array2[2][1] = -n7;
                array2[1][3] = n4 - n2;
                array2[2][3] = 0.0;
                if (!l) {
                    return 0;
                }
            }
            array2[1][1] = n4 - n2;
            array2[2][1] = 0.0;
            array2[1][3] = n12;
            array2[2][3] = -n7;
            if (!l) {
                return 0;
            }
        }
        Label_0358: {
            if (n4 == 0.0) {
                n7 = Math.atan(1.0) / 1.5;
                if (!l) {
                    break Label_0358;
                }
            }
            n7 = Math.atan(Math.sqrt(-n7) / Math.abs(n4)) / 3.0;
        }
        double n13 = 0.0;
        Label_0392: {
            if (n4 < 0.0) {
                n13 = Math.sqrt(n5) * 2.0;
                if (!l) {
                    break Label_0392;
                }
            }
            n13 = -2.0 * Math.sqrt(n5);
        }
        final double n14 = Math.cos(n7) * n13;
        final double n15 = -Math.sqrt(0.75) * Math.sin(n7) * n13 - 0.5 * n14;
        final double n16 = -n15 - n14 - n2;
        final double n17 = n14 - n2;
        double n18 = n15 - n2;
        Label_0486: {
            if (Math.abs(n17) > Math.abs(n18)) {
                array2[1][3] = n17;
                if (!l) {
                    break Label_0486;
                }
            }
            array2[1][3] = n18;
            n18 = n17;
        }
        Label_0523: {
            if (Math.abs(n16) > Math.abs(n18)) {
                array2[1][2] = n16;
                if (!l) {
                    break Label_0523;
                }
            }
            array2[1][2] = n18;
            n18 = n16;
        }
        array2[1][1] = n18;
        int n19 = 1;
        while (true) {
            Label_0548: {
                if (!l) {
                    break Label_0548;
                }
                array2[2][n19] = 0.0;
                ++n19;
            }
            if (n19 < 4) {
                continue;
            }
            break;
        }
        return 0;
    }
    
    double f() {
        final double[] array = { 1.0, -(this.b + this.f + this.j), this.b * this.f + this.b * this.j + this.f * this.j - this.c * this.e - this.d * this.h - this.g * this.i, this.b * this.g * this.i + this.c * this.e * this.j + this.d * this.h * this.f - this.b * this.f * this.j - this.c * this.g * this.h - this.d * this.e * this.i };
        final double[][] array2 = new double[3][4];
        a(array, array2);
        final double n = array2[1][1] * array2[1][1] + array2[2][1] * array2[2][1];
        final double n2 = array2[1][2] * array2[1][2] + array2[2][2] * array2[2][2];
        final double n3 = array2[1][3] * array2[1][3] + array2[2][3] * array2[2][3];
        final double n4 = (n > n2) ? n : n2;
        return Math.sqrt((n4 > n3) ? n4 : n3);
    }
    
    public String toString() {
        return "(" + this.b + ", " + this.c + ", " + this.d + ", " + this.e + ", " + this.f + ", " + this.g + ", " + this.h + ", " + this.i + ", " + this.j + ", " + this.k + ", " + this.l + ", " + this.m + ")";
    }
    
    public void a(final float b) {
        this.b = b;
        this.b();
    }
    
    public void b(final float c) {
        this.c = c;
        this.b();
    }
    
    public void c(final float d) {
        this.d = d;
        this.b();
    }
    
    public void d(final float e) {
        this.e = e;
        this.b();
    }
    
    public void e(final float f) {
        this.f = f;
        this.b();
    }
    
    public void f(final float g) {
        this.g = g;
        this.b();
    }
    
    public void g(final float h) {
        this.h = h;
        this.b();
    }
    
    public void h(final float i) {
        this.i = i;
        this.b();
    }
    
    public void i(final float j) {
        this.j = j;
        this.b();
    }
    
    public void j(final float k) {
        this.k = k;
        this.b();
    }
    
    public void k(final float l) {
        this.l = l;
        this.b();
    }
    
    public void l(final float m) {
        this.m = m;
        this.b();
    }
    
    public float g() {
        return this.b;
    }
    
    public float h() {
        return this.c;
    }
    
    public float i() {
        return this.d;
    }
    
    public float j() {
        return this.e;
    }
    
    public float k() {
        return this.f;
    }
    
    public float l() {
        return this.g;
    }
    
    public float m() {
        return this.h;
    }
    
    public float n() {
        return this.i;
    }
    
    public float p() {
        return this.j;
    }
    
    public float q() {
        return this.k;
    }
    
    public float r() {
        return this.l;
    }
    
    public float s() {
        return this.m;
    }
}
