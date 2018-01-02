// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.util.d;
import ji.io.q;
import ji.util.e;
import ji.image.oo;
import ji.io.hp;
import ji.image.ds;
import ji.image.dx;
import ji.v1event.af;
import ji.v1event.a6;

public class gd
{
    private op a;
    private short[][] b;
    private short[][] c;
    private short[][] d;
    private static a6 e;
    private af f;
    private dx g;
    private int h;
    private int i;
    private int j;
    private int k;
    private long l;
    private boolean m;
    private byte[] n;
    private int o;
    private int p;
    private long q;
    private int r;
    private ds s;
    private int t;
    private int u;
    private int[] v;
    private hp w;
    private int[][] x;
    private int[][] y;
    private boolean z;
    private int aa;
    private int ab;
    private int[] ac;
    private int ad;
    private oo ae;
    private int af;
    private int ag;
    private short[] ah;
    private String ai;
    private Object aj;
    
    public gd(final String ai) throws Exception {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.f = null;
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = 0L;
        this.m = false;
        this.n = null;
        this.o = 0;
        this.p = 0;
        this.q = 0L;
        this.r = 0;
        this.s = null;
        this.t = 0;
        this.u = 1;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = false;
        this.aa = 0;
        this.ab = 0;
        this.ac = null;
        this.ad = 0;
        this.ae = new oo();
        this.af = 0;
        this.ag = 0;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ai = ai;
        if (this.a == null) {
            this.a = op.a();
            this.b = op.a.a;
            this.c = op.b.a;
            this.h = op.c;
            this.i = op.d;
        }
        if (this.x == null) {
            this.x = new int[11][255];
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 255; ++j) {
                    this.x[i][j] = 7;
                }
            }
            for (int k = 0; k < 255; ++k) {
                this.x[8][k] = 6;
            }
            this.x[1][1] = 0;
            this.x[3][3] = 1;
            this.x[6][3] = 2;
            this.x[7][3] = 3;
            this.x[3][2] = -1;
            this.x[6][2] = -2;
            this.x[7][2] = -3;
            this.x[4][1] = 4;
            this.x[3][1] = 5;
            this.x[10][15] = 19639;
        }
    }
    
    public final void a() {
        if (ji.util.e.ai()) {
            this.m = true;
        }
    }
    
    public final void a(final af f, final dx g, final ds s, final hp w, final q q, final Object aj) throws Exception {
        this.aj = aj;
        this.ae.a(aj, g, q, this.ai);
        this.ad = 0;
        int n = g.n;
        int r = g.m;
        if (g.b3 > 0) {
            r = g.b3;
        }
        if (g.b4 > 0) {
            n = g.b4;
        }
        this.r = r;
        this.g = g;
        this.f = f;
        this.s = s;
        this.w = w;
        this.z = s.j();
        this.ag = 10 * this.r;
        this.ah = new short[this.ag];
        this.v = new int[this.ag];
        this.aa = this.ag + 25;
        this.ac = new int[this.ag + 25];
        for (int i = 1; i < this.ac.length; ++i) {
            this.ac[i] = this.r;
        }
        this.y = new int[2][this.ag + 25];
        for (int j = 0; j < 2; ++j) {
            System.arraycopy(this.ac, 0, this.y[j], 0, this.aa);
        }
        this.u = 1;
        this.t = 0;
        if (f != null) {
            if (gd.e == null) {
                gd.e = new a6(this, 4, "");
            }
            if (ji.util.d.du() && !g.b8 && g.ak) {
                gd.e = new a6(this, 23, "");
            }
        }
        this.m = false;
        if (ji.util.d.du()) {
            this.j = Math.max(n / 5, 10);
        }
        else {
            this.j = Math.max(n / 15, 16);
        }
        this.j = Math.min(this.j, n);
        this.k = this.j;
    }
    
    public final void b() {
        this.ae.a(this.aj, this.g, this.f, this.s, this.z, this.ai);
        this.g = null;
        this.s = null;
        this.v = null;
        this.y = null;
        this.f = null;
        gd.e = null;
        this.c();
        this.w = null;
    }
    
    private byte a(final int o) throws Exception {
        if (o >= this.o && o < this.p) {
            return this.n[o - this.o];
        }
        this.w.a(this.q + o);
        this.o = o;
        this.p = o + this.w.a(this.n, 0, this.n.length);
        return this.n[o - this.o];
    }
    
    private void a(int n, final int n2) throws Exception {
        if (this.n == null) {
            final int n3 = 2097152;
            if (n > n3) {
                n = n3;
            }
            this.n = new byte[n];
        }
        else if (this.n.length < n) {
            this.n = null;
            this.n = new byte[n];
        }
        this.q = n2;
        this.w.a((long)n2);
        this.o = 0;
        this.p = this.w.a(this.n, 0, n);
    }
    
    private void c() {
        this.n = null;
        this.o = 0;
        this.p = 0;
        this.q = 0L;
    }
    
    public final int a(int b4, final int n, int n2, final int n3) throws Exception {
        final boolean b5 = false;
        int n4 = n3;
        String s = null;
        int n5 = 0;
        try {
            if (this.g.b4 > 0) {
                b4 = this.g.b4;
            }
            int n6 = 0;
            int min = b4 + n3;
            int n7 = 0;
            int n8 = 0;
            int n9 = 7;
            int ab = 0;
            int[] array = this.y[this.u];
            int[] array2 = this.y[this.t];
            final int m = this.g.m;
            final int n10 = this.g.n;
            this.af = 0;
            this.ae.a(this.g, this.ad);
            try {
                this.a(n2, n);
                n7 = 0;
                System.arraycopy(this.ac, 0, array2, 0, this.ab);
                array[0] = (array2[0] = -1);
                n8 = this.a(n6++);
                if (ji.util.e.aj() && this.g.x == 1) {
                    this.v[n7++] = 0;
                }
                min = Math.min(min, n10);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (m <= 8) {
                ++n2;
            }
            while (n4 < min && n6 < n2 && !this.m) {
                short n11 = 0;
                int n12 = 1;
                short n13 = 0;
                int n14 = 0;
                int i = 0;
                int n15 = 0;
                final int h = this.h;
                this.d = this.b;
                ab = 0;
                Label_1560: {
                    try {
                        int n16 = 0;
                        Label_1539: {
                            Block_62: {
                                while (n13 < this.r && n6 <= n2) {
                                    n15 = (n15 << 1 | (0x1 & n8 >>> n9--));
                                    if (n9 < 0) {
                                        n9 = 7;
                                        n8 = this.a(n6++);
                                    }
                                    ++i;
                                    final int n17 = this.x[i][n15];
                                    if (n17 == 7) {
                                        continue;
                                    }
                                    if (n17 != 6) {
                                        i = 0;
                                        n15 = 0;
                                    }
                                    if (n17 == 5) {
                                        int n18;
                                        if (n14 == 0) {
                                            n18 = this.h;
                                            this.d = this.b;
                                        }
                                        else {
                                            n18 = this.i;
                                            this.d = this.c;
                                        }
                                        short n19 = 0;
                                        while (i < n18) {
                                            n15 = (n15 << 1 | (0x1 & n8 >>> n9--));
                                            if (n9 < 0) {
                                                n9 = 7;
                                                n8 = this.a(n6++);
                                            }
                                            ++i;
                                        }
                                        final short n20 = this.d[n15][i];
                                        if (n20 != -9 && n20 < 64) {
                                            n19 += n20;
                                        }
                                        else {
                                            if (n20 != -9) {
                                                n19 += n20;
                                            }
                                            while (true) {
                                                short n21;
                                                for (n21 = -9; n21 == -9; n21 = this.d[n15][i]) {
                                                    n15 = (n15 << 1 | (0x1 & n8 >>> n9--));
                                                    if (n9 < 0) {
                                                        n9 = 7;
                                                        n8 = this.a(n6++);
                                                    }
                                                    ++i;
                                                }
                                                n19 += n21;
                                                if (n21 < 64) {
                                                    break;
                                                }
                                                n15 = 0;
                                                i = 0;
                                            }
                                        }
                                        int n22 = 0;
                                        int j = 0;
                                        this.v[n7++] = n11 + n19;
                                        final int n23 = n16 + this.v[n7 - 1];
                                        n11 = 0;
                                        n13 += n19;
                                        array[++ab] = n13;
                                        n14 = 1 - n14;
                                        short n24 = 0;
                                        int n25;
                                        if (n14 == 0) {
                                            n25 = this.h;
                                            this.d = this.b;
                                        }
                                        else {
                                            n25 = this.i;
                                            this.d = this.c;
                                        }
                                        while (j < n25) {
                                            n22 = (n22 << 1 | (0x1 & n8 >>> n9--));
                                            if (n9 < 0) {
                                                n9 = 7;
                                                n8 = this.a(n6++);
                                            }
                                            ++j;
                                        }
                                        final short n26 = this.d[n22][j];
                                        if (n26 != -9 && n26 < 64) {
                                            n24 += n26;
                                        }
                                        else {
                                            if (n26 != -9) {
                                                n24 += n26;
                                            }
                                            while (true) {
                                                short n27;
                                                for (n27 = -9; n27 == -9; n27 = this.d[n22][j]) {
                                                    n22 = (n22 << 1 | (0x1 & n8 >>> n9--));
                                                    if (n9 < 0) {
                                                        n9 = 7;
                                                        n8 = this.a(n6++);
                                                    }
                                                    ++j;
                                                }
                                                n24 += n27;
                                                if (n27 < 64) {
                                                    break;
                                                }
                                                n22 = 0;
                                                j = 0;
                                            }
                                        }
                                        n15 = 0;
                                        i = 0;
                                        this.v[n7++] = n24;
                                        n16 = n23 + this.v[n7 - 1];
                                        if (n13 >= this.r) {
                                            continue;
                                        }
                                        n15 = 0;
                                        i = 0;
                                        n13 += n24;
                                        n14 = 1 - n14;
                                        if ((array[++ab] = n13) >= this.r) {
                                            continue;
                                        }
                                        while (array2[n12] <= n13 && n12 < this.aa) {
                                            n12 += 2;
                                        }
                                    }
                                    else if (n17 == 0) {
                                        if (ab > 0) {
                                            while (array2[n12] <= n13 && n12 < this.aa) {
                                                n12 += 2;
                                            }
                                        }
                                        final int n28 = array2[n12];
                                        this.v[n7++] = n11 + (n28 - n13);
                                        n16 += this.v[n7 - 1];
                                        array[++ab] = n28;
                                        if ((n13 = (short)n28) >= this.r) {
                                            continue;
                                        }
                                        n11 = 0;
                                        n14 = 1 - n14;
                                        if (array2[n12] > n13) {
                                            --n12;
                                        }
                                        else {
                                            ++n12;
                                        }
                                    }
                                    else if (n17 == 4) {
                                        final short n29 = n13;
                                        if (ab > 0) {
                                            while (array2[n12] <= n13 && n12 < this.aa) {
                                                n12 += 2;
                                            }
                                        }
                                        ++n12;
                                        final int n30 = array2[n12] - n13;
                                        n11 += (short)n30;
                                        n16 += n30;
                                        n13 += (short)n30;
                                        ++n12;
                                        if (n13 < this.r) {
                                            continue;
                                        }
                                        n13 = n29;
                                    }
                                    else if (n17 < 0) {
                                        if (ab > 0) {
                                            while (array2[n12] <= n13 && n12 < this.aa) {
                                                n12 += 2;
                                            }
                                        }
                                        final int n31 = array2[n12] + n17;
                                        this.v[n7++] = n11 + (n31 - n13);
                                        n16 += this.v[n7 - 1];
                                        n11 = 0;
                                        array[++ab] = n31;
                                        n13 = (short)n31;
                                        if (n13 >= this.r) {
                                            continue;
                                        }
                                        n14 = 1 - n14;
                                        if (array2[n12] > n13) {
                                            --n12;
                                        }
                                        else {
                                            ++n12;
                                        }
                                    }
                                    else {
                                        if (n17 == 19639) {
                                            break Block_62;
                                        }
                                        if (n17 == 6) {
                                            break Label_1539;
                                        }
                                        if (ab > 0) {
                                            while (array2[n12] <= n13 && n12 < this.aa) {
                                                n12 += 2;
                                            }
                                        }
                                        final int n32 = array2[n12] + n17;
                                        this.v[n7++] = n11 + (n32 - n13);
                                        n16 += this.v[n7 - 1];
                                        array[++ab] = n32;
                                        if ((n13 = (short)n32) >= this.r) {
                                            continue;
                                        }
                                        n11 = 0;
                                        n14 = 1 - n14;
                                        if (array2[n12] > n13) {
                                            --n12;
                                        }
                                        else {
                                            ++n12;
                                        }
                                    }
                                }
                                break Label_1560;
                            }
                            s = "Optional uncompressed G4 extensions are not supported (please contact support@daeja.com for more information)";
                            n4 = min - 1;
                            break Label_1560;
                        }
                        n4 = min - 1;
                    }
                    catch (Exception ex2) {
                        if (b5) {
                            ex2.printStackTrace();
                        }
                    }
                    try {
                        try {
                            if (this.z) {
                                if (this.s.x()) {
                                    if (this.r < 65535) {
                                        for (int k = 0; k < n7; ++k) {
                                            this.ah[k] = (short)(this.v[k] & 0xFFFF);
                                        }
                                        final short[] a = this.s.a(this.ah, n7, n4);
                                        final int length = a.length;
                                        for (int l = 0; l < length; ++l) {
                                            this.v[l] = a[l];
                                        }
                                        this.ae.a(this.v, length, n4, this.af, this.ad, this.g, this.s, true);
                                    }
                                    else {
                                        int n33 = 0;
                                        for (int n34 = 0; n34 < n7; ++n34) {
                                            if (this.v[n34] > 65535) {
                                                int n35 = this.v[n34];
                                                short n36 = -1;
                                                while (n35 > 0) {
                                                    this.ah[n33++] = n36;
                                                    n35 -= 65535;
                                                    if (n35 > 0) {
                                                        this.ah[n33++] = 0;
                                                    }
                                                    if (n35 <= 65535) {
                                                        n36 = (short)(n35 & 0xFFFF);
                                                    }
                                                }
                                            }
                                            else {
                                                this.ah[n33++] = (short)(this.v[n34] & 0xFFFF);
                                            }
                                        }
                                        final short[] a2 = this.s.a(this.ah, n7, n4);
                                        final int length2 = a2.length;
                                        for (int n37 = 0; n37 < length2; ++n37) {
                                            this.v[n37] = a2[n37];
                                        }
                                        this.ae.a(this.v, length2, n4, this.af, this.ad, this.g, this.s, true);
                                    }
                                }
                                else {
                                    this.ae.a(this.v, n7, n4, this.af, this.ad, this.g, this.s, true);
                                }
                            }
                            else if (this.r < 65535) {
                                for (int n38 = 0; n38 < n7; ++n38) {
                                    this.ah[n38] = (short)(this.v[n38] & 0xFFFF);
                                }
                                this.ae.a(this.ah, n7, this.af, this.ad, this.s, this.g);
                            }
                            else {
                                int n39 = 0;
                                for (int n40 = 0; n40 < n7; ++n40) {
                                    if (this.v[n40] > 65535) {
                                        int n41 = this.v[n40];
                                        short n42 = -1;
                                        while (n41 > 0) {
                                            this.ah[n39++] = n42;
                                            n41 -= 65535;
                                            if (n41 > 0) {
                                                this.ah[n39++] = 0;
                                            }
                                            if (n41 <= 65535) {
                                                n42 = (short)(n41 & 0xFFFF);
                                            }
                                        }
                                    }
                                    else {
                                        this.ah[n39++] = (short)(this.v[n40] & 0xFFFF);
                                    }
                                }
                                this.ae.a(this.ah, n7, this.af, this.ad, this.s, this.g);
                            }
                        }
                        catch (Exception ex3) {
                            ex3.printStackTrace();
                        }
                        n7 = 0;
                        try {
                            if (this.f != null && !this.g.b8) {
                                --this.k;
                                if (this.k <= 0) {
                                    if (System.currentTimeMillis() - this.l > 1000) {
                                        this.l = System.currentTimeMillis();
                                        gd.e.a("".concat(String.valueOf(String.valueOf(100 * n4 / n10))));
                                        this.f.a(gd.e);
                                    }
                                    this.k = this.j;
                                }
                            }
                        }
                        catch (Exception ex7) {}
                        this.t = this.u;
                        this.u = 1 - this.u;
                        array = this.y[this.u];
                        array2 = this.y[this.t];
                        ++n4;
                        ++this.af;
                        ++n5;
                        try {
                            if (n4 >= min) {
                                continue;
                            }
                            System.arraycopy(this.ac, 0, array, 0, this.ab);
                            array[0] = -1;
                            this.ab = ab;
                            if (!ji.util.e.aj() || this.g.x != 1) {
                                continue;
                            }
                            this.v[n7++] = 0;
                        }
                        catch (Exception ex4) {
                            ex4.printStackTrace();
                        }
                        continue;
                    }
                    catch (Exception ex5) {
                        if (b5) {
                            ex5.printStackTrace();
                            continue;
                        }
                        continue;
                    }
                }
                break;
            }
            try {
                System.arraycopy(this.ac, 0, array2, 0, ab);
                System.arraycopy(this.ac, 0, array, 0, this.ab);
            }
            catch (Exception ex8) {}
        }
        catch (Exception ex6) {
            ji.util.d.b(ex6);
        }
        if (s != null) {
            throw new Exception(s);
        }
        if (this.g.b8) {
            ++this.ad;
            if (this.f != null) {
                gd.e.a("".concat(String.valueOf(String.valueOf(100 * this.ad / this.g.b7))));
                this.f.a(gd.e);
            }
            return 0;
        }
        return n4 - n3;
    }
    
    public void finalize() {
    }
    
    static {
        gd.e = null;
    }
}
