// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.io.ac;
import ji.image.ds;
import ji.v1event.af;
import ji.util.e;
import ji.v1event.a6;
import ji.awt.c;

public class gg
{
    private static int a;
    private static int b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private byte[] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private c m;
    private int[] n;
    private byte[] o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private int w;
    private a6 x;
    
    public gg() {
        this.c = 255;
        this.d = 0;
        this.f = false;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = 0;
        this.x = null;
    }
    
    public final void a() {
        if (ji.util.e.ai()) {
            this.f = true;
        }
    }
    
    public final void a(final af af, final int n) throws Exception {
        this.f = false;
        this.k = 0;
        this.h = 9;
        this.g = null;
        this.o = null;
        this.p = 0;
        this.u = 0;
        if (af != null) {
            this.v = Math.max(n / 15, 16);
            this.v = Math.min(this.v, n);
            this.w = this.v;
            if (this.x == null) {
                this.x = new a6(this, 4, "");
            }
        }
        this.c();
    }
    
    private final void c() {
        if (this.m == null) {
            this.m = new c("jiDEcodeLZW-stringTable", 258, 256);
            this.n = new int[258];
            this.e = 258;
            for (int i = 0; i < 256; ++i) {
                this.m.c(new byte[] { (byte)i });
                this.n[i] = 1;
            }
            this.m.c(new byte[0]);
            this.m.c(new byte[0]);
            this.i = 258;
        }
    }
    
    public final void b() {
        this.g = null;
        this.o = null;
        this.p = 0;
        if (this.m != null) {
            this.m.c();
            this.m = null;
        }
    }
    
    public final int a(final byte[] g, final af af, final int s, final int n, final int n2, final int n3, final int n4, final int n5, final ds ds, final int n6, final ac ac, final Object o, final boolean b, int n7) throws Exception {
        int n8 = 0;
        final int n9 = 0;
        int n10 = 0;
        boolean b2 = false;
        try {
            this.s = s;
            this.g = g;
            int n11 = 0;
            int n12 = 0;
            if (n3 != 0) {
                if (n3 == 1) {
                    final int n13 = 4104 + n * n2 / 8;
                    if (this.o == null) {
                        n11 = 1;
                    }
                    else if (this.o.length < n13) {
                        n11 = 1;
                    }
                    if (n11 != 0) {
                        this.o = null;
                        this.o = new byte[n13];
                    }
                }
                else if (n3 == 4) {
                    this.o = ds.ag();
                    n10 = n6;
                }
                else if (n3 == 8) {
                    if (!ds.g()) {
                        b2 = true;
                        final int n14 = 4096 + 3 * n * n2;
                        if (this.o == null) {
                            n11 = 1;
                        }
                        else if (this.o.length < n14) {
                            n11 = 1;
                        }
                        if (n11 != 0) {
                            this.o = null;
                            this.o = new byte[n14];
                        }
                        n10 = 0;
                    }
                    else {
                        this.o = ds.ag();
                        n10 = n6;
                    }
                }
                else if (n3 == 24) {
                    final int n15 = 4096 + 3 * n * n2;
                    n12 = 3 * n * n2;
                    if (this.o == null) {
                        n11 = 1;
                    }
                    else if (this.o.length < n15) {
                        n11 = 1;
                    }
                    if (n11 != 0) {
                        this.o = null;
                        this.o = new byte[n15];
                    }
                }
                else if (n3 == 32) {
                    final int n16 = 4096 + 4 * n * n2;
                    if (this.o == null) {
                        n11 = 1;
                    }
                    else if (this.o.length < n16) {
                        n11 = 1;
                    }
                    if (n11 != 0) {
                        this.o = null;
                        this.o = new byte[n16];
                    }
                }
            }
            this.p = 0;
            this.k = 0;
            this.h = 9;
            this.j = (g[this.k++] & 0xFF);
            this.l = 7;
            this.q = 8;
            final int j = this.j;
            this.r = j;
            this.t = j;
            this.j = (g[this.k++] & 0xFF);
            this.i = 258;
            boolean b3 = false;
            final int n17 = 100 * n7 / ds.n();
            if (af != null) {
                af.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n17)))));
            }
            while (this.r != gg.a && !this.f && (this.p < n12 || n3 != 24)) {
                if (b3 && n3 == 24 && this.p >= n12) {
                    break;
                }
                while (this.q < this.h) {
                    this.t = (this.t << 1 | (0x1 & this.j >>> this.l--));
                    if (this.l < 0) {
                        this.l = 7;
                        if (this.k >= this.s) {
                            this.t = gg.a;
                            break;
                        }
                        this.j = (g[this.k++] & 0xFF);
                    }
                    ++this.q;
                }
                this.q = 0;
                this.r = this.t;
                this.t = 0;
                if (this.r == gg.a) {
                    break;
                }
                if (this.r == gg.b) {
                    while (this.r == gg.b) {
                        this.h = 9;
                        while (this.q < this.h) {
                            this.t = (this.t << 1 | (0x1 & this.j >>> this.l--));
                            if (this.l < 0) {
                                this.l = 7;
                                if (this.k >= this.s) {
                                    this.t = gg.a;
                                    break;
                                }
                                this.j = (g[this.k++] & 0xFF);
                            }
                            ++this.q;
                        }
                        this.q = 0;
                        this.r = this.t;
                        this.t = 0;
                    }
                    if (this.r == gg.a) {
                        break;
                    }
                    this.i = 258;
                    this.h = 9;
                    final int n18 = this.n[this.r];
                    if (n3 == 0) {
                        ac.b((byte[])this.m.b(this.r), 0, n18);
                    }
                    else if (n10 + this.p + n18 >= this.o.length) {
                        final int n19 = this.o.length - (n10 + this.p);
                        if (n19 > 0) {
                            System.arraycopy(this.m.b(this.r), 0, this.o, n10 + this.p, n19);
                            break;
                        }
                        break;
                    }
                    else {
                        System.arraycopy(this.m.b(this.r), 0, this.o, n10 + this.p, n18);
                    }
                    this.p += n18;
                    n8 = this.r;
                }
                else if (this.r < this.i) {
                    final int n20 = this.n[this.r];
                    if (n3 == 0) {
                        ac.b((byte[])this.m.b(this.r), 0, n20);
                    }
                    else if (n10 + this.p + n20 >= this.o.length) {
                        final int n21 = this.o.length - (n10 + this.p);
                        if (n21 > 0) {
                            System.arraycopy(this.m.b(this.r), 0, this.o, n10 + this.p, n21);
                            break;
                        }
                        break;
                    }
                    else {
                        System.arraycopy(this.m.b(this.r), 0, this.o, n10 + this.p, n20);
                    }
                    this.p += n20;
                    int n22 = this.n[n8];
                    if (this.i >= this.e) {
                        this.d += 256;
                        for (int i = 0; i < this.c; ++i) {
                            this.m.c(new byte[1]);
                        }
                        final int[] n23 = new int[this.e + this.c];
                        System.arraycopy(this.n, 0, n23, 0, this.e);
                        this.n = n23;
                        this.e += this.c;
                    }
                    byte[] array = (byte[])this.m.b(this.i);
                    if (n22 >= array.length) {
                        final byte[] array2 = new byte[n22 + 256];
                        System.arraycopy(array, 0, array2, 0, array.length);
                        array = array2;
                    }
                    System.arraycopy(this.m.b(n8), 0, array, 0, n22);
                    array[n22++] = ((byte[])this.m.b(this.r))[0];
                    this.m.a(array, this.i);
                    this.n[this.i++] = n22;
                    if (this.i > 510) {
                        b3 = (this.i == 511 || this.i == 1023 || this.i == 2047);
                        if (this.i == 511) {
                            this.h = 10;
                        }
                        else if (this.i == 1023) {
                            this.h = 11;
                        }
                        else if (this.i == 2047) {
                            this.h = 12;
                        }
                    }
                    n8 = this.r;
                }
                else {
                    int n24 = this.n[n8];
                    if (this.i >= this.e) {
                        this.d += 256;
                        for (int k = 0; k < this.c; ++k) {
                            this.m.c(new byte[1]);
                        }
                        final int[] n25 = new int[this.e + this.c];
                        System.arraycopy(this.n, 0, n25, 0, this.e);
                        this.n = n25;
                        this.e += this.c;
                    }
                    byte[] array3 = (byte[])this.m.b(this.i);
                    if (n24 >= array3.length) {
                        final byte[] array4 = new byte[this.d + 256];
                        System.arraycopy(array3, 0, array4, 0, array3.length);
                        array3 = array4;
                    }
                    System.arraycopy(this.m.b(n8), 0, array3, 0, n24);
                    array3[n24++] = ((byte[])this.m.b(n8))[0];
                    this.m.a(array3, this.i);
                    this.n[this.i] = n24;
                    if (n3 == 0) {
                        ac.b((byte[])this.m.b(this.i++), 0, n24);
                    }
                    else if (n10 + this.p + n24 >= this.o.length) {
                        final int n26 = this.o.length - (n10 + this.p);
                        if (n26 > 0) {
                            System.arraycopy(this.m.b(this.r), 0, this.o, n10 + this.p, n26);
                            break;
                        }
                        break;
                    }
                    else {
                        final int min = Math.min(n24, this.o.length - n10 - this.p);
                        System.arraycopy(this.m.b(this.i++), 0, this.o, n10 + this.p, min);
                        if (min < n24) {
                            break;
                        }
                    }
                    this.p += n24;
                    if (this.i > 510) {
                        b3 = (this.i == 511 || this.i == 1023 || this.i == 2047);
                        if (this.i == 511) {
                            this.h = 10;
                        }
                        else if (this.i == 1023) {
                            this.h = 11;
                        }
                        else if (this.i == 2047) {
                            this.h = 12;
                        }
                    }
                    n8 = this.r;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (n4 > 1) {
                    if (n3 != 0) {
                        if (n3 != 1) {
                            if (n3 == 4) {
                                int n27 = n;
                                final int n28 = n % 2;
                                if (n28 > 0) {
                                    n27 += 2 - n28;
                                }
                                for (int l = 0; l < n2; ++l) {
                                    final int n29 = n10 + l * n27 / 2;
                                    final int n30 = this.o[n29] >> 4 & 0xF;
                                    byte b4 = (byte)(n30 + (this.o[n29] & 0xF));
                                    this.o[n29] = (byte)(n30 << 4 | b4);
                                    for (int n31 = 1; n31 < n27 / 2; ++n31) {
                                        final int n32 = this.o[n29 + n31] >> 4 & 0xF;
                                        final byte b5 = (byte)(this.o[n29 + n31] & 0xF);
                                        final byte b6 = (byte)(n32 + b4);
                                        final byte b7 = (byte)(b5 + b6);
                                        this.o[n29 + n31] = (byte)(b6 << 4 | b7);
                                        b4 = b7;
                                    }
                                }
                            }
                            else if (n3 == 8) {
                                for (int n33 = 0; n33 < n2; ++n33) {
                                    final int n34 = n10 + n33 * n;
                                    for (int n35 = 1; n35 < n; ++n35) {
                                        final byte[] o2 = this.o;
                                        final int n36 = n34 + n35;
                                        o2[n36] += this.o[n34 + n35 - 1];
                                    }
                                }
                            }
                            else if (n3 == 24) {
                                final int n37 = n * 3;
                                for (int n38 = 0; n38 < n2; ++n38) {
                                    final int n39 = n38 * n37;
                                    for (int n40 = 3; n40 < n37; ++n40) {
                                        final int n41 = n39 + n40;
                                        final byte[] o3 = this.o;
                                        final int n42 = n41;
                                        o3[n42] += this.o[n41 - 3];
                                    }
                                }
                            }
                            else if (n3 == 32) {
                                final int n43 = n * 4;
                                for (int n44 = 0; n44 < n2; ++n44) {
                                    final int n45 = n44 * n43;
                                    for (int n46 = 4; n46 < n43; ++n46) {
                                        final int n47 = n45 + n46;
                                        final byte[] o4 = this.o;
                                        final int n48 = n47;
                                        o4[n48] += this.o[n47 - 4];
                                    }
                                }
                                if (n5 != 0) {
                                    for (int n49 = 0; n49 < this.o.length; ++n49) {
                                        this.o[n49] = (byte)(255 - (this.o[n49] & 0xFF));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex2) {}
            final int n50 = n10 + this.p;
            if (n3 != 0) {
                if (n3 == 4) {
                    if (n5 == 1) {
                        for (int n51 = n10; n51 < n50; ++n51) {
                            this.o[n51] = (byte)(255 - this.o[n51]);
                        }
                    }
                }
                else if (n3 == 8) {
                    if (n5 == 1) {
                        for (int n52 = n10; n52 < n50; ++n52) {
                            this.o[n52] = (byte)(255 - this.o[n52]);
                        }
                    }
                    if (b2) {
                        int n53 = 409600;
                        if (n53 > n * n2) {
                            n53 = n * n2;
                        }
                        final int max;
                        final int n54 = (max = Math.max(n53 / n, 1)) * n;
                        final byte[] array5 = new byte[n54];
                        int n55 = 0;
                        int n56 = n54;
                        int n57 = 0;
                        try {
                            for (int n58 = n10; n58 < n50; ++n58) {
                                array5[n55++] = this.o[n58];
                                if (--n56 == 0) {
                                    ds.b(array5, n55, o, n57 + n7 + 1, n57 + max + n7 + 1, true);
                                    n56 = n54;
                                    n57 += max;
                                    n55 = 0;
                                }
                            }
                        }
                        catch (Exception ex3) {}
                        if (n55 > 0) {
                            ds.b(array5, n55, o, n57 + n7 + 1, n2 + n7 + 1, true);
                        }
                    }
                }
                else if (n3 == 24) {
                    int n59 = 409600;
                    if (n59 > n * n2) {
                        n59 = n * n2;
                    }
                    final int max2;
                    final int n60 = (max2 = Math.max(n59 / n, 1)) * n;
                    final int[] array6 = new int[n60];
                    int n61 = 0;
                    int n62 = n60;
                    int n63 = 0;
                    try {
                        for (int n64 = n10; n64 < n50; n64 += 3) {
                            array6[n61++] = (0xFF000000 | (this.o[n64] & 0xFF) << 16 | (this.o[n64 + 1] & 0xFF) << 8 | (this.o[n64 + 2] & 0xFF));
                            if (--n62 == 0) {
                                ds.a(array6, n61, o, n63 + n7 + 1, n63 + max2 + n7 + 1, true);
                                n62 = n60;
                                n63 += max2;
                                n61 = 0;
                            }
                        }
                    }
                    catch (Exception ex4) {}
                    if (n61 > 0) {
                        ds.a(array6, n61, o, n63 + n7 + 1, n2 + n7 + 1, true);
                    }
                }
                else if (n3 == 32) {
                    int n65 = 409600;
                    if (n65 > n * n2) {
                        n65 = n * n2;
                    }
                    final int max3;
                    final int n66 = (max3 = Math.max(n65 / n, 1)) * n;
                    final int[] array7 = new int[n66];
                    int n67 = 0;
                    int n68 = n66;
                    int n69 = 0;
                    try {
                        for (int n70 = n10; n70 < n50; n70 += 4) {
                            final int n71 = this.o[n70 + 3] & 0xFF;
                            array7[n67++] = (0xFF000000 | n71 * (this.o[n70] & 0xFF) / 255 << 16 | n71 * (this.o[n70 + 1] & 0xFF) / 255 << 8 | n71 * (this.o[n70 + 2] & 0xFF) / 255);
                            if (--n68 == 0) {
                                ds.a(array7, n67, o, n69, n69 + max3 - 1, true);
                                n68 = n66;
                                n69 += max3;
                                n67 = 0;
                            }
                        }
                    }
                    catch (Exception ex5) {}
                    if (n67 > 1) {
                        ds.a(array7, n67, o, n69, n2 - n69 - 1, true);
                    }
                }
                else {
                    ac.b(this.o, 0, this.p);
                }
            }
            try {
                if (!b2) {
                    this.o = null;
                }
                n7 += n2;
            }
            catch (Exception ex6) {}
        }
        if (n3 > 1 && !b && af != null) {
            this.w -= n2;
            if (this.w <= 0) {
                this.x.a("".concat(String.valueOf(String.valueOf(100 * (n7 + n9) / n2))));
                af.a(this.x);
                this.w = this.v;
            }
        }
        if (n3 == 4) {
            int n72 = n;
            final int n73 = n % 2;
            if (n73 > 0) {
                n72 += 2 - n73;
            }
            int n74 = n2;
            final int n75 = n2 % 2;
            if (n75 > 0) {
                n74 += 2 - n75;
            }
            if (n72 != n || n74 != n2) {
                this.p = n2 * (n72 / 2);
            }
        }
        return Math.min(this.p, n * n2);
    }
    
    static {
        gg.a = 257;
        gg.b = 256;
    }
}
