// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.io.ac;
import ji.io.q;
import ji.util.e;
import ji.v1event.af;
import ji.image.ds;
import ji.image.dx;
import ji.image.oo;
import ji.io.hp;
import ji.v1event.a6;

public class ge
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private a6 h;
    private hp i;
    private int j;
    private int k;
    private oo l;
    private int m;
    private String n;
    private Object o;
    private dx p;
    private ds q;
    private af r;
    private int s;
    private boolean t;
    private int[] u;
    
    public ge() {
        this.a = 0;
        this.b = 2048;
        this.c = 0;
        this.e = 0;
        this.f = 0;
        this.g = false;
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = new oo();
        this.m = 0;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = 0;
        this.t = false;
        this.u = null;
    }
    
    public final void a() {
        if (ji.util.e.ai()) {
            this.g = true;
        }
    }
    
    public final void a(final af r, final dx p6, final ds q, final q q2, final Object o, final String n) throws Exception {
        this.j = 0;
        this.l.a(o, p6, q2, n);
        this.k = 0;
        this.o = o;
        this.n = n;
        this.p = p6;
        this.r = r;
        this.q = q;
        this.s = 10 * p6.m;
    }
    
    public final void b() {
        this.l.a(this.o, this.p, this.r, this.q, this.t, this.n);
        this.o = null;
        this.p = null;
        this.r = null;
        this.q = null;
        this.t = false;
        this.u = null;
    }
    
    public final int a(final ac ac, final af af, final int f, int b3, int b4, final dx dx, final int n, final int n2, final ds ds, final Object o) throws Exception {
        this.i = new hp(ac, dx.an);
        int n3 = 0;
        final int n4 = 8;
        Math.min(Math.max(b4 / 15, 16), b4);
        this.g = false;
        final short[] array = new short[b3];
        final byte[] array2 = new byte[b3];
        int n5 = 0;
        try {
            if (af != null) {
                this.h = new a6(this, 4, "");
            }
            final int n6 = dx.n;
            final int m = dx.m;
            if (dx.b3 > 0) {
                final int b5 = dx.b3;
            }
            if (dx.b4 > 0) {
                final int b6 = dx.b4;
            }
            this.m = 0;
            this.f = f;
            this.c = 0;
            this.a = 0;
            this.d = dx.z;
            this.e = 0;
            if (dx.b4 > 0) {
                b4 = dx.b4;
                b3 = dx.b3;
            }
            this.l.a(dx, this.k);
            this.i.a((long)n);
            if (this.d == 4) {
                final byte[] ag = ds.ag();
                final byte[] array3 = new byte[f];
                final int a = this.i.a(ag, this.j, f);
                final int j = this.j;
                final int n7 = j + a;
                if (dx.x == 1) {
                    for (int i = j; i < n7; ++i) {
                        ag[i] = (byte)(255 - ag[i]);
                    }
                }
                this.j += a;
                return b4;
            }
            if (this.d == 8) {
                if (ds.g()) {
                    final byte[] ag2 = ds.ag();
                    final int a2 = this.i.a(ag2, n2 * b3, f);
                    final int n8 = n2 * b3;
                    final int n9 = n8 + a2;
                    if (dx.x == 1) {
                        for (int k = n8; k < n9; ++k) {
                            ag2[k] = (byte)(255 - ag2[k]);
                        }
                    }
                    return b4;
                }
                int n10 = 102400;
                if (n10 > b3 * b4) {
                    n10 = b3 * b4;
                }
                final int max;
                final int n11 = (max = Math.max(n10 / b3, 1)) * b3;
                int n12 = 0;
                try {
                    int l = 0;
                    final byte[] array4 = new byte[n11];
                    while (l < f) {
                        l += this.i.a(array4, 0, n11);
                        final int n13 = 100 * n2 / ds.n();
                        ds.b(array4, n11, o, n2 + n12 + 1, n2 + n12 + max + 1, true);
                        n12 += max;
                        if (af != null) {
                            this.h.a(String.valueOf(String.valueOf(new StringBuffer("").append(n13 + 100 * (b4 - n12) / ds.n()))));
                            af.a(this.h);
                        }
                    }
                }
                catch (Exception ex) {}
                return b4;
            }
            else {
                if (this.d == 24) {
                    int n14 = 409600;
                    if (n14 > b3 * b4) {
                        n14 = b3 * b4;
                    }
                    final int max2;
                    final int n15 = (max2 = Math.max(n14 / b3, 1)) * b3;
                    final int[] array5 = new int[n15];
                    int n16 = 0;
                    int n17 = n15;
                    int n18 = 0;
                    try {
                        final byte[] array6 = new byte[n15 * 3];
                        this.i.a(array6, 0, n15 * 3);
                        int n19 = 0;
                        final int n20 = 100 * n2 / ds.n();
                        for (int n21 = 0; n21 < f && !this.g; n21 += 3) {
                            array5[n16++] = (0xFF000000 | (array6[n19] & 0xFF) << 16 | (array6[n19 + 1] & 0xFF) << 8 | (array6[n19 + 2] & 0xFF));
                            n19 += 3;
                            if (--n17 == 0) {
                                ds.a(array5, n16, o, n2 + n18 + 1, n2 + n18 + max2 + 1, true);
                                n18 += max2;
                                n17 = n15;
                                n16 = 0;
                                n19 = 0;
                                this.i.a(array6, 0, n15 * 3);
                                if (af != null) {
                                    this.h.a(String.valueOf(String.valueOf(new StringBuffer("").append(n20).append(100 * n18 / b4))));
                                    af.a(this.h);
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {}
                    if (n16 > 0) {
                        ds.a(array5, n16, o, n2 + n18 + 1, n2 + b4 - n18, true);
                    }
                    return b4;
                }
                if (this.d == 32) {
                    final byte[] array7 = new byte[f];
                    final int a3 = this.i.a(array7, 0, f);
                    int n22 = 409600;
                    if (n22 > b3 * b4) {
                        n22 = b3 * b4;
                    }
                    final int max3;
                    final int n23 = (max3 = Math.max(n22 / b3, 1)) * b3;
                    final int[] array8 = new int[n23];
                    int n24 = 0;
                    int n25 = n23;
                    int n26 = 0;
                    try {
                        if (dx.x > 4) {
                            for (int n27 = 0; n27 < a3; n27 += 4) {
                                array8[n24++] = (0xFF000000 | 255 - (array7[n27 + 0] & 0xFF) << 16 | 255 - (array7[n27 + 1] & 0xFF) << 8 | 255 - (array7[n27 + 2] & 0xFF));
                                if (--n25 == 0) {
                                    ds.a(array8, n24, o, n26, n26 + max3 - 1, true);
                                    n26 += max3;
                                    n25 = n23;
                                    n24 = 0;
                                }
                            }
                        }
                        else {
                            for (int n28 = 0; n28 < a3; n28 += 4) {
                                array8[n24++] = (0xFF000000 | (array7[n28 + 0] & 0xFF) << 16 | (array7[n28 + 1] & 0xFF) << 8 | (array7[n28 + 2] & 0xFF));
                                if (--n25 == 0) {
                                    ds.a(array8, n24, o, n26, n26 + max3 - 1, true);
                                    n26 += max3;
                                    n25 = n23;
                                    n24 = 0;
                                }
                            }
                        }
                    }
                    catch (Exception ex3) {}
                    if (n24 > 0) {
                        ds.a(array8, n24, o, n26, b4 - n26 - 1, true);
                    }
                    return b4;
                }
                final int n29 = 1 + b3 / 8;
                final int min = Math.min(512000 / n29, b4);
                final int n30 = min * n29;
                int n31 = 0;
                int n32 = 0;
                final byte[] array9 = new byte[n30];
                int n33 = b3;
                final int n34 = b3 % n4;
                if (n34 > 0) {
                    n33 += n4 - n34;
                }
                while (n3 < b4 && !this.g) {
                    if (n3 >= n5) {
                        n31 = n32;
                        this.c = n3 * n33;
                        final int n35 = n + this.c / 8;
                        if (this.i.f() != n35) {
                            this.i.a((long)n35);
                        }
                        final int a4 = this.i.a(array9, 0, n30);
                        n5 = n31 + min;
                        n32 += min;
                        if (dx.x != 0) {
                            for (int n36 = 0; n36 < a4; ++n36) {
                                array9[n36] = (byte)(255 - array9[n36]);
                            }
                        }
                    }
                    int n37 = 0;
                    short n38 = 0;
                    int n39 = 0;
                    int n40 = 7;
                    int n41 = (n3 - n31) * n33 / 8;
                    byte b7 = array9[n41++];
                    for (int n42 = 0; n42 < b3; ++n42) {
                        final int n43 = 0x1 & b7 >>> n40--;
                        if (n40 < 0) {
                            n40 = 7;
                            b7 = array9[n41++];
                        }
                        ++n38;
                        if (n43 != n37) {
                            array[n39++] = n38;
                            n37 = n43;
                            n38 = 0;
                        }
                    }
                    if (n38 > 0) {
                        array[n39++] = n38;
                    }
                    if (ds.j()) {
                        if (this.u == null) {
                            this.u = new int[this.s];
                        }
                        for (int n44 = 0; n44 < n39; ++n44) {
                            this.u[n44] = array[n44];
                        }
                        this.l.a(this.u, n39, n2 + n3, this.m, this.k, dx, ds, true);
                    }
                    else {
                        this.l.a(array, n39, this.m, this.k, ds, dx);
                    }
                    ++n3;
                    ++this.m;
                }
            }
        }
        finally {
            this.i.a();
            this.i = null;
        }
        if (dx.b8) {
            ++this.k;
            if (af != null) {
                this.h.a("".concat(String.valueOf(String.valueOf(100 * this.k / dx.b7))));
                af.a(this.h);
            }
            return 0;
        }
        return n3;
    }
}
