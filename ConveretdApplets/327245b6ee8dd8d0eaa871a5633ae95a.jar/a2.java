import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class a2 extends m
{
    int a;
    int b;
    int c;
    int d;
    c8[] e;
    da[] f;
    c9[] g;
    int[][] h;
    ba[] i;
    Vector j;
    boolean k;
    a6 l;
    a7 m;
    a8[] n;
    Vector p;
    
    a2() {
        this.j = new Vector();
        this.k = false;
        this.p = new Vector(0, 10);
        super.a = 13;
    }
    
    void a() {
        final boolean l = c.l;
        super.a();
        int n = 0;
        while (true) {
            while (true) {
                Label_0033: {
                    if (!l) {
                        break Label_0033;
                    }
                    this.e[n].a();
                    this.e[n] = null;
                    ++n;
                }
                if (n < this.a) {
                    continue;
                }
                break;
            }
            n = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0070: {
                    if (!l) {
                        break Label_0070;
                    }
                    this.g[n].a();
                    this.g[n] = null;
                    ++n;
                }
                if (n < this.b) {
                    continue;
                }
                break;
            }
            n = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0107: {
                    if (!l) {
                        break Label_0107;
                    }
                    this.f[n].a();
                    final a2 a2 = this;
                    a2.f[n] = null;
                    ++n;
                }
                if (n < this.c) {
                    continue;
                }
                break;
            }
            this.h = null;
            final a2 a2 = this;
            if (l) {
                continue;
            }
            break;
        }
        if (this.i != null) {
            int n2 = 0;
            while (true) {
                Label_0149: {
                    if (!l) {
                        break Label_0149;
                    }
                    this.i[n2].b();
                    ++n2;
                }
                if (n2 < this.i.length) {
                    continue;
                }
                break;
            }
        }
        this.i = null;
        this.j.removeAllElements();
        if (this.m != null) {
            this.m.a();
        }
        this.l = null;
        this.n = null;
        while (true) {
            while (true) {
                Label_0211: {
                    if (!l) {
                        break Label_0211;
                    }
                    final Object firstElement = this.p.firstElement();
                    ((i)firstElement).c();
                }
                if (this.p.size() > 0) {
                    continue;
                }
                break;
            }
            this.p.removeAllElements();
            final Object firstElement = this;
            if (!l) {
                this.p = null;
                return;
            }
            continue;
        }
    }
    
    int a(final x x) {
        final boolean l = c.l;
        super.d = x.e();
        super.e = x.m();
        x.c();
        final int g = x.g();
        int[][] array = null;
        if (g > 0) {
            final int d = x.d();
            array = new int[g][2];
            int n = 0;
            while (true) {
                Label_0084: {
                    if (!l) {
                        break Label_0084;
                    }
                    array[n][0] = x.a(d);
                    array[n][1] = x.a(d);
                    ++n;
                }
                if (n < g) {
                    continue;
                }
                break;
            }
            x.c();
        }
        x.m();
        final db db = new db();
        final int[][] a = db.a(x, array, g, x.e);
        this.a = db.x;
        this.e = db.y;
        this.b = db.w;
        this.h = db.z;
        db.a();
        this.g = new c9[this.b];
        final Vector<da> vector = new Vector<da>();
        this.c = 0;
        final float[] array2 = new float[3];
        int a2 = 0;
        int n12 = 0;
    Label_0402_Outer:
        while (true) {
            Label_0857: {
                if (!l) {
                    break Label_0857;
                }
                int n2 = 0;
                int n4 = 0;
                int g2;
                float n8;
                while (true) {
                    Label_0376: {
                        if (!l) {
                            break Label_0376;
                        }
                        int n3 = n4;
                        if (n3 == 3) {
                            n3 = 0;
                        }
                        final float n5 = this.e[this.h[a2][n3]].a - this.e[this.h[a2][n2]].a;
                        final float n6 = this.e[this.h[a2][n3]].b - this.e[this.h[a2][n2]].b;
                        final float n7 = this.e[this.h[a2][n3]].c - this.e[this.h[a2][n2]].c;
                        array2[n2] = (float)Math.sqrt(n5 * n5 + n6 * n6 + n7 * n7);
                        ++n2;
                    }
                    if (n2 < 3) {
                        continue Label_0402_Outer;
                    }
                    g2 = 0;
                    n8 = 999999.0f;
                    n4 = 0;
                    if (l) {
                        continue Label_0402_Outer;
                    }
                    break;
                }
                int n9 = n4;
                while (true) {
                    Label_0462: {
                        if (!l) {
                            break Label_0462;
                        }
                        int n10 = n9 + 1;
                        if (n10 == 3) {
                            n10 = 0;
                        }
                        float n11 = array2[n9] / array2[n10];
                        if (n11 < 1.0f) {
                            n11 = 1.0f / n11;
                        }
                        if (n11 < n8) {
                            g2 = n10;
                            n8 = n11;
                        }
                        ++n9;
                    }
                    if (n9 < 3) {
                        continue;
                    }
                    break;
                }
                final c8 c8 = this.e[this.h[a2][0]];
                final c8 c9 = this.e[this.h[a2][1]];
                final c8 c10 = this.e[this.h[a2][2]];
                this.g[a2] = new c9(c8, c9, c10);
                this.g[a2].a = a2;
                this.g[a2].g = g2;
                da a3;
                if ((a3 = c9.a(c10, vector)) == null) {
                    a3 = new da(c9, c10);
                    c9.a(this.c);
                    c10.a(this.c);
                    vector.addElement(a3);
                    ++this.c;
                }
                a3.a(a2);
                if (a3.d > 2) {
                    a3.e = true;
                }
                this.g[a2].h = a3;
                da a4;
                if ((a4 = c10.a(c8, vector)) == null) {
                    a4 = new da(c10, c8);
                    c10.a(this.c);
                    c8.a(this.c);
                    vector.addElement(a4);
                    ++this.c;
                }
                a4.a(a2);
                if (a4.d > 2) {
                    a4.e = true;
                }
                this.g[a2].i = a4;
                da a5;
                if ((a5 = c8.a(c9, vector)) == null) {
                    a5 = new da(c8, c9);
                    c8.a(this.c);
                    c9.a(this.c);
                    vector.addElement(a5);
                    ++this.c;
                }
                a5.a(a2);
                if (a5.d > 2) {
                    a5.e = true;
                }
                this.g[a2].j = a5;
                ++a2;
            }
            if (a2 < this.b) {
                continue;
            }
            vector.copyInto(this.f = new da[this.c]);
            n12 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int e;
        while (true) {
            while (true) {
                Label_0950: {
                    if (!l) {
                        break Label_0950;
                    }
                    final a2 a6 = this;
                    final da a7 = a6.e[a[n12][0]].a(this.e[a[n12][1]], vector);
                    if (a7 != null) {
                        a7.e = true;
                    }
                    ++n12;
                }
                if (n12 < g) {
                    continue;
                }
                break;
            }
            x.c();
            e = x.e();
            final a2 a6 = this;
            if (l) {
                continue;
            }
            break;
        }
        this.i = new ba[e];
        int n13 = 0;
        int[] array3;
        while (true) {
            while (true) {
                Label_1080: {
                    if (!l) {
                        break Label_1080;
                    }
                    final x x2 = x;
                    final String m = x2.m();
                    Label_1077: {
                        ba ba;
                        if (m.equals("dattri1")) {
                            ba = new a9();
                        }
                        else if (m.equals("pa2")) {
                            ba = new dg(2);
                        }
                        else {
                            if (!m.equals("pa")) {
                                break Label_1077;
                            }
                            ba = new dg(1);
                        }
                        ba.a(x);
                        this.i[n13] = ba;
                    }
                    ++n13;
                }
                if (n13 < e) {
                    continue;
                }
                break;
            }
            array3 = new int[this.b];
            final x x2 = x;
            if (l) {
                continue;
            }
            break;
        }
        x.g();
        int n14 = 0;
        int n15;
        int n16;
        while (true) {
            while (true) {
                Label_1125: {
                    if (!l) {
                        break Label_1125;
                    }
                    array3[n14] = x.d();
                    ++n14;
                }
                if (n14 < this.b) {
                    continue;
                }
                break;
            }
            n15 = 0;
            n16 = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            Label_1195: {
                if (!l) {
                    break Label_1195;
                }
                final ba ba2 = this.i[n16];
                if (ba2 instanceof a9) {
                    ((a9)ba2).a(n15, array3, this.b);
                    ++n15;
                }
                ba2.a(this);
                ++n16;
            }
            if (n16 < e) {
                continue;
            }
            break;
        }
        int n17 = 0;
        int n18 = 0;
        while (true) {
            while (true) {
                Label_1331: {
                    if (!l) {
                        break Label_1331;
                    }
                    final boolean b = this.i[n18] instanceof a9;
                    final int n19;
                    if (n19 != 0) {
                        final a9 a8 = (a9)this.i[n18];
                        a8.k = n17++;
                        if (this.i[n18].a.endsWith("triangleSet")) {
                            a8.a = a8.a.substring(0, a8.a.length() - 11);
                        }
                        a8.a();
                        this.j.addElement(a8);
                        a8.a();
                        this.d += a8.b(this.d);
                    }
                    ++n18;
                }
                if (n18 < this.i.length) {
                    continue;
                }
                break;
            }
            final int n19 = x.e - x.b;
            if (!l) {
                if (n19 > 12) {
                    final int g3 = x.g();
                    int n20 = 0;
                    while (true) {
                        Label_1492: {
                            if (!l) {
                                break Label_1492;
                            }
                            final String i = x.m();
                            final int g4 = x.g();
                            final int g5 = x.g();
                            a9 a9 = this.a(i);
                            final l j = (l)x.b.a(g4);
                            final char[] array4 = new char[g5];
                            if (a9 != null && a9.b != g5) {
                                a9 = null;
                            }
                            int n21 = 0;
                            while (true) {
                                Label_1463: {
                                    if (!l) {
                                        break Label_1463;
                                    }
                                    array4[n21] = (char)x.d();
                                    ++n21;
                                }
                                if (n21 < g5) {
                                    continue;
                                }
                                break;
                            }
                            if (a9 != null && j != null) {
                                a9.a(array4, j);
                            }
                            ++n20;
                        }
                        if (n20 < g3) {
                            continue;
                        }
                        break;
                    }
                }
                return super.d;
            }
            continue;
        }
    }
    
    void b() {
        final boolean l = c.l;
        this.k = true;
        this.l = new a6(this.a);
        this.m = new a7(this.b);
        final int[] array = new int[9];
        final int[] array2 = new int[9];
        int n = 0;
        int n2;
        while (true) {
            while (true) {
                Label_0094: {
                    if (!l) {
                        break Label_0094;
                    }
                    this.l.a(this.e[n].a, this.e[n].b, this.e[n].c);
                    ++n;
                }
                if (n < this.a) {
                    continue;
                }
                break;
            }
            n2 = 0;
            if (l) {
                continue;
            }
            break;
        }
        int g;
        while (true) {
            while (true) {
                Label_0189: {
                    if (!l) {
                        break Label_0189;
                    }
                    g = this.g[n2].g;
                    this.m.a(this.h[n2][g], this.h[n2][a(g + 1)], this.h[n2][a(g + 2)]);
                    ++n2;
                }
                if (n2 < this.b) {
                    continue;
                }
                break;
            }
            this.n = new a8[this.c];
            g = 0;
            if (l) {
                continue;
            }
            break;
        }
        while (true) {
            Label_1583: {
                if (!l) {
                    break Label_1583;
                }
                final da da = this.f[g];
                this.n[g] = new a8(this);
                this.n[g].b = da.a.d;
                this.n[g].c = da.b.d;
                final c9 c9 = this.g[da.c[0]];
                this.n[g].d = c9.a;
                this.n[g].f = c9.b;
                if (da.d > 2) {
                    this.n[g].a = 3;
                }
                Label_1580: {
                    if (da.d == 2) {
                        final c9 c10 = this.g[da.c[1]];
                        this.n[g].e = c10.a;
                        this.n[g].g = c10.b;
                        final float n3 = (c9.e.b - c9.d.b) * (c9.f.c - c9.d.c) - (c9.f.b - c9.d.b) * (c9.e.c - c9.d.c);
                        final float n4 = (c9.e.c - c9.d.c) * (c9.f.a - c9.d.a) - (c9.f.c - c9.d.c) * (c9.e.a - c9.d.a);
                        final float n5 = (c9.e.a - c9.d.a) * (c9.f.b - c9.d.b) - (c9.f.a - c9.d.a) * (c9.e.b - c9.d.b);
                        final float n6 = (c10.e.b - c10.d.b) * (c10.f.c - c10.d.c) - (c10.f.b - c10.d.b) * (c10.e.c - c10.d.c);
                        final float n7 = (c10.e.c - c10.d.c) * (c10.f.a - c10.d.a) - (c10.f.c - c10.d.c) * (c10.e.a - c10.d.a);
                        final float n8 = (c10.e.a - c10.d.a) * (c10.f.b - c10.d.b) - (c10.f.a - c10.d.a) * (c10.e.b - c10.d.b);
                        final float n9 = (n3 * n6 + n4 * n7 + n5 * n8) / (float)Math.sqrt((n3 * n3 + n4 * n4 + n5 * n5) * (n6 * n6 + n7 * n7 + n8 * n8));
                        boolean b = false;
                        if (n9 < 0.996) {
                            b = true;
                        }
                        if (c9.b != c10.b || (da.e && b)) {
                            this.n[g].a = 2;
                            if (!l) {
                                break Label_1580;
                            }
                        }
                        int n10 = 0;
                        if (c9.d.d == c10.d.d) {
                            array[n10] = 0;
                            array2[n10++] = 0;
                        }
                        if (c9.d.d == c10.e.d) {
                            array[n10] = 0;
                            array2[n10++] = 1;
                        }
                        if (c9.d.d == c10.f.d) {
                            array[n10] = 0;
                            array2[n10++] = 2;
                        }
                        if (c9.e.d == c10.d.d) {
                            array[n10] = 1;
                            array2[n10++] = 0;
                        }
                        if (c9.e.d == c10.e.d) {
                            array[n10] = 1;
                            array2[n10++] = 1;
                        }
                        if (c9.e.d == c10.f.d) {
                            array[n10] = 1;
                            array2[n10++] = 2;
                        }
                        if (c9.f.d == c10.d.d) {
                            array[n10] = 2;
                            array2[n10++] = 0;
                        }
                        if (c9.f.d == c10.e.d) {
                            array[n10] = 2;
                            array2[n10++] = 1;
                        }
                        if (c9.f.d == c10.f.d) {
                            array[n10] = 2;
                            array2[n10++] = 2;
                        }
                        if (n10 != 2) {
                            break Label_1580;
                        }
                        final int n11 = array[0];
                        final int n12 = array2[0];
                        final int n13 = array[1];
                        final int n14 = array2[1];
                        if (c9.b.f != null && c10.b.f != null) {
                            final ba f = c9.b.f;
                            final ba f2 = c10.b.f;
                            final int a = c9.b.a(c9.a);
                            final int a2 = c10.b.a(c10.a);
                            final float[] array3 = f.b[a];
                            final float[] array4 = f2.b[a2];
                            if (array3 != null && array4 != null) {
                                float n15 = array3[n11] - array4[n12];
                                float n16 = array3[n11 + 3] - array4[n12 + 3];
                                float n17 = array3[n13] - array4[n14];
                                float n18 = array3[n13 + 3] - array4[n14 + 3];
                                if (n15 < 0.0f) {
                                    n15 = -n15;
                                }
                                if (n16 < 0.0f) {
                                    n16 = -n16;
                                }
                                if (n17 < 0.0f) {
                                    n17 = -n17;
                                }
                                if (n18 < 0.0f) {
                                    n18 = -n18;
                                }
                                final float n19 = 0.2f;
                                if (n15 > n19 || n16 > n19 || n17 > n19 || n18 > n19) {
                                    this.n[g].a = 2;
                                    if (!l) {
                                        break Label_1580;
                                    }
                                }
                            }
                        }
                        this.n[g].a = 1;
                    }
                    if (da.d == 1) {
                        this.n[g].a = 0;
                    }
                }
                ++g;
            }
            if (g >= this.c) {
                return;
            }
            continue;
        }
    }
    
    a9 a(final String s) {
        final boolean l = c.l;
        final Enumeration elements = this.j.elements();
        while (true) {
            Label_0046: {
                if (!l) {
                    break Label_0046;
                }
                final a9 a9 = elements.nextElement();
                while (a9.a.equals(s)) {
                    final a9 a10 = a9;
                    if (!l) {
                        return a10;
                    }
                }
            }
            if (!elements.hasMoreElements()) {
                return null;
            }
            continue;
        }
    }
    
    static final int a(final int n) {
        if (n < 3) {
            return n;
        }
        return n - 3;
    }
}
