import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

class ai
{
    int a;
    int b;
    int c;
    int d;
    int e;
    ai f;
    ai g;
    ai h;
    boolean i;
    boolean j;
    int k;
    int l;
    int m;
    int n;
    int p;
    int[] q;
    boolean r;
    boolean s;
    ah t;
    public ah u;
    ai[] v;
    int w;
    int[] x;
    int[] y;
    int[] z;
    int aa;
    ac ab;
    int[] ac;
    ag ad;
    ac ae;
    l af;
    boolean ag;
    int ah;
    int ai;
    boolean aj;
    Point ak;
    
    ai() {
        this.a = -65536;
        this.i = false;
        this.j = false;
        this.r = false;
        this.s = true;
        this.ag = false;
        this.aj = false;
        this.ak = new Point(0, 0);
        this.f = null;
        this.p = 0;
        this.q = new int[4];
        this.x = new int[16];
        this.y = new int[16];
        this.z = new int[16];
    }
    
    ai(final int n) {
        this();
        this.a(n);
    }
    
    void a(final int n, final int[] array, final int[] array2, final ac ac, final ac ac2) {
        final ac ac4;
        final ac ac3 = ac4 = new ac(ac2);
        ac4.a /= 4;
        final ac ac5 = ac3;
        ac5.d /= 4;
        final ac ac6 = ac3;
        ac6.b /= 4;
        final ac ac7 = ac3;
        ac7.c /= 4;
        final ac ac8 = ac3;
        ac8.e /= 4;
        final ac ac9 = ac3;
        ac9.f /= 4;
        final ac ac10 = new ac(ac);
        final ac ac11 = ac3;
        ac11.e <<= 8;
        final ac ac12 = ac3;
        ac12.f <<= 8;
        final ac ac13 = ac10;
        ac13.e <<= 8;
        final ac ac14 = ac10;
        ac14.f <<= 8;
        this.ab = ac.a(ac10, ac3).b();
        this.ac = new int[257];
        int n2 = 0;
        int n3 = array2[0];
        int n5;
        int n4 = n5 = array[0];
        if (n4 >>> 24 != 255) {
            this.r = true;
        }
        int n6 = 1;
        for (int i = 0; i <= 256; ++i) {
            if (i > n3) {
                n2 = n3;
                n5 = n4;
                if (n6 < n) {
                    n3 = array2[n6];
                    n4 = array[n6];
                    if (n4 >>> 24 != 255) {
                        this.r = true;
                    }
                    ++n6;
                }
                else {
                    n3 = 256;
                }
            }
            final int n7 = n3 - i;
            final int n8 = i - n2;
            final int n9 = n7 + n8;
            if (n9 > 0) {
                this.ac[i] = (((n5 >> 24 & 0xFF) * n7 + (n4 >> 24 & 0xFF) * n8) / n9 << 24 | ((n5 >> 16 & 0xFF) * n7 + (n4 >> 16 & 0xFF) * n8) / n9 << 16 | ((n5 >> 8 & 0xFF) * n7 + (n4 >> 8 & 0xFF) * n8) / n9 << 8 | ((n5 & 0xFF) * n7 + (n4 & 0xFF) * n8) / n9);
            }
            else {
                this.ac[i] = n5;
            }
        }
    }
    
    void a(final int a) {
        this.b = 0;
        this.a = a;
        this.r = ((this.a & 0xFF000000) != 0xFF000000);
    }
    
    int a(final int[] array, final int[] array2, final int n, int n2, final Point point) {
        int x = point.x;
        final int y = point.y;
        for (int i = 0; i < n; ++i) {
            point.x = x;
            point.y = y;
            this.x[0] = this.a(point);
            this.y[0] = this.m;
            this.z[0] = this.l;
            this.aa = 1;
            for (int j = this.w - 1; j >= 0; --j) {
                final int m = this.v[j].m;
                for (int n3 = this.aa, k = 0; k < n3; n3 = this.aa, ++k) {
                    final int n4 = this.y[k];
                    final int n5 = n4 & m;
                    final int n6 = n4 & (m ^ 0xFFFF);
                    if (n5 != 0) {
                        if (n6 != 0) {
                            this.x[this.aa] = this.x[k];
                            this.y[this.aa] = n6;
                            this.y[k] = n5;
                            final int n7 = y.y[n5];
                            this.z[this.aa] = this.z[k] - n7;
                            this.z[k] = n7;
                            ++this.aa;
                        }
                        point.x = x;
                        point.y = y;
                        final int a = this.v[j].a(point);
                        final int n8 = this.x[k];
                        int n9 = a >>> 24;
                        if (n9 > 0) {
                            ++n9;
                        }
                        final int n10 = 256 - n9;
                        this.x[k] = (((a & 0xFF00) * n9 + (n8 & 0xFF00) * n10 >> 8 & 0xFF00) | ((a & 0xFF00FF) * n9 + (n8 & 0xFF00FF) * n10 >> 8 & 0xFF00FF) | 0xFF000000);
                    }
                }
            }
            for (int l = 0; l < this.aa; ++l) {
                final int n11 = this.x[l];
                final int n12 = this.z[l];
                final int n13 = n12 * (n11 >> 16 & 0xFF);
                final int n14 = n12 * (n11 >> 8 & 0xFF);
                final int n15 = n12 * (n11 >> 0 & 0xFF);
                final int n16 = array[i];
                final int n17 = array2[i] + n13;
                array[i] = (n16 & 0xFFFF) + n15 + ((n16 >> 16) + n14 << 16);
                array2[i] = n17;
            }
            ++x;
        }
        n2 += this.l;
        return n2;
    }
    
    void a(final int[] array, int n, final int n2, final Point point) {
        if (this.r) {
            int n3 = this.a >>> 24;
            if (n3 > 0) {
                ++n3;
            }
            final int n4 = 256 - n3;
            for (int i = 0; i < n2; ++i) {
                final int n5 = array[n];
                array[n++] = (((this.a & 0xFF00) * n3 + (n5 & 0xFF00) * n4 >> 8 & 0xFF00) | ((this.a & 0xFF00FF) * n3 + (n5 & 0xFF00FF) * n4 >> 8 & 0xFF00FF) | 0xFF000000);
            }
        }
        else {
            for (int j = 0; j < n2; ++j) {
                array[n++] = this.a;
            }
        }
    }
    
    int a(final Point point) {
        return this.a;
    }
}
