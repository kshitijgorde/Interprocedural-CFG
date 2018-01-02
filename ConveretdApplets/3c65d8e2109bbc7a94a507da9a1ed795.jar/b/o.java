// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class o
{
    float[] e;
    int case;
    int h;
    int do;
    int p;
    float[] for;
    int void;
    int f;
    float[] else;
    int c;
    int g;
    float[] new;
    int j;
    int long;
    float[] n;
    float[] d;
    float[] i;
    float[] o;
    float[] char;
    float[] if;
    int goto;
    int k;
    float b;
    b l;
    h byte;
    s try;
    r int;
    m m;
    u a;
    
    public o() {
        this.e = new float[240];
        this.for = new float[223];
        this.else = new float[234];
        this.new = new float[51];
        this.n = new float[] { 0.9595f, 0.8413f, 0.6549f, 0.4154f, 0.1423f, -0.1423f, -0.4154f, -0.6549f, -0.8413f, -0.9595f };
        this.d = new float[10];
        this.i = new float[10];
        this.o = new float[10];
        this.char = new float[10];
        this.if = new float[50];
        this.l = new b();
        this.byte = new h();
        this.try = new s();
        this.int = new r();
        this.m = new m();
        this.a = new u();
    }
    
    public void a() {
        this.p = this.case + 240 - 80;
        this.h = this.p - 40;
        this.do = this.case + 240 - 240;
        this.f = this.void + 143;
        this.g = this.c + 143 + 11;
        this.long = this.j + 11;
        this.k = this.goto + 10;
        this.b = 0.2f;
        System.arraycopy(this.n, 0, this.d, 0, 10);
        this.byte.a();
        this.int.a();
    }
    
    public void a(final float[] array) {
        int n = 239;
        for (int i = 79; i >= 0; --i) {
            this.e[n--] = array[i];
        }
    }
    
    public void a(final int[] array, int n) {
        final float[] array2 = new float[11];
        final float[] array3 = new float[22];
        final float[] array4 = new float[22];
        final float[] array5 = new float[11];
        final float[] array6 = new float[11];
        final float[] array7 = new float[10];
        final float[] array8 = new float[10];
        final float[] array9 = new float[10];
        final float[] array10 = new float[10];
        final float[] array11 = new float[10];
        final float[] array12 = new float[2];
        final float[] array13 = new float[2];
        final float[] array14 = new float[80];
        final float[] array15 = new float[40];
        final float[] array16 = new float[40];
        final float[] array17 = new float[40];
        final float[] array18 = new float[40];
        final float[] array19 = new float[40];
        final float[] array20 = new float[40];
        final float[] array21 = new float[5];
        final af af = new af();
        final af af2 = new af();
        final af af3 = new af();
        float floatValue = 0.0f;
        this.l.a(t.a(this.e, this.do), 10, array2);
        this.l.a(10, array2);
        final float[] a = t.a(array3, 11);
        this.l.if(array2, a, array11);
        t.a(array3, 11, a);
        this.l.a(a, array7, this.n);
        t.a(array3, 11, a);
        this.byte.a(array7, array8, array);
        n += 2;
        ag.a(this.n, array7, array9, array10, array3);
        ag.a(this.d, array8, array4);
        for (int i = 0; i < 10; ++i) {
            this.n[i] = array7[i];
            this.d[i] = array8[i];
        }
        this.try.a(array12, array13, array9, array10, array11);
        ag.a(array3, 0, array12[0], 10, array5, 0);
        ag.a(array3, 0, array13[0], 10, array6, 0);
        ah.a(array5, 0, this.e, this.h, this.for, this.f, 40);
        ah.a(array6, 0, this.for, this.f, this.for, this.f, 40, this.char, 0, 1);
        ag.a(array3, 11, array12[1], 10, array5, 0);
        ag.a(array3, 11, array13[1], 10, array6, 0);
        ah.a(array5, 0, this.e, this.h + 40, this.for, this.f + 40, 40);
        ah.a(array6, 0, this.for, this.f + 40, this.for, this.f + 40, 40, this.char, 0, 1);
        af2.a = new Integer(x.a(this.for, this.f, 20, 143, 80) - 3);
        if (af2.a < 20) {
            af2.a = new Integer(20);
        }
        af3.a = new Integer(af2.a + 6);
        if (af3.a > 143) {
            af3.a = new Integer(143);
            af2.a = new Integer(af3.a - 6);
        }
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        for (int j = 0; j < 80; j += 40) {
            ag.a(array3, n2, array12[n4], 10, array5, 0);
            ag.a(array3, n2, array13[n4], 10, array6, 0);
            ++n4;
            for (int k = 0; k <= 10; ++k) {
                this.new[this.j + k] = array5[k];
            }
            ah.a(array4, n3, this.new, this.j, array15, 0, 40, this.new, this.long, 0);
            ah.a(array6, 0, array15, 0, array15, 0, 40, this.new, this.long, 0);
            ah.a(array4, n3, this.e, this.h + j, this.else, this.g + j, 40);
            ah.a(array4, n3, this.else, this.g + j, this.if, this.k, 40, this.if, this.goto, 0);
            ah.a(array5, 0, this.if, this.k, array16, 0, 40);
            ah.a(array6, 0, array16, 0, array16, 0, 40, this.o, 0, 0);
            final int a2 = x.a(this.else, this.g + j, array16, 0, array15, 0, 40, af2.a, af3.a, j, af);
            final int a3 = x.a(a2, af.a, af2, af3, 20, 143, j);
            array[n++] = a3;
            if (j == 0) {
                array[n++] = ab.a(a3);
            }
            aa.a(this.else, this.g + j, a2, af.a, 40);
            ah.if(this.else, this.g + j, array15, 0, array19, 0, 40);
            float a4 = x.a(array16, 0, array19, 0, array21, 0, 40);
            final int a5 = this.int.a(a2, af.a);
            if (a5 == 1 && a4 > 0.95f) {
                a4 = 0.95f;
            }
            int l;
            for (l = 0; l < 40; ++l) {
                array17[l] = array16[l] - array19[l] * a4;
            }
            final af af4 = new af(new Integer(l));
            final int a6 = this.m.a(array17, array15, a2, this.b, j, array18, array20, af4);
            final int intValue = af4.a;
            array[n++] = a6;
            array[n++] = intValue;
            b.d.a(array16, array19, array20, array21);
            final g g = new g(new Float(a4));
            final g g2 = new g(new Float(floatValue));
            array[n++] = this.a.a(array18, array21, 40, g, g2, a5);
            final float floatValue2 = g.a;
            floatValue = g2.a;
            this.b = floatValue2;
            if (this.b > 0.7945f) {
                this.b = 0.7945f;
            }
            if (this.b < 0.2f) {
                this.b = 0.2f;
            }
            for (int n5 = 0; n5 < 40; ++n5) {
                this.else[this.g + n5 + j] = floatValue2 * this.else[this.g + n5 + j] + floatValue * array18[n5];
            }
            this.int.a(floatValue2, a2);
            ah.a(array4, n3, this.else, this.g + j, array14, j, 40, this.i, 0, 1);
            for (int n6 = 30, n7 = 0; n6 < 40; ++n6, ++n7) {
                this.if[this.goto + n7] = this.e[this.h + j + n6] - array14[j + n6];
                this.o[n7] = array16[n6] - floatValue2 * array19[n6] - floatValue * array20[n6];
            }
            n2 += 11;
            n3 += 11;
        }
        b.j.a(this.e, this.case + 80, this.e, this.case, 160);
        b.j.a(this.for, this.void + 80, this.for, this.void, 143);
        b.j.a(this.else, this.c + 80, this.else, this.c, 154);
    }
}
