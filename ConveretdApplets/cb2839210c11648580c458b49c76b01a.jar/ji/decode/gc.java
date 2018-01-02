// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.util.d;
import ji.io.q;
import ji.util.e;
import ji.image.oo;
import ji.image.ds;
import ji.io.hp;
import ji.image.dx;
import ji.v1event.a6;
import ji.v1event.af;

public class gc
{
    private boolean a;
    private boolean b;
    private static op c;
    private static int[][] d;
    private static short[][] e;
    private static short[][] f;
    private static short[][] g;
    private af h;
    private a6 i;
    private dx j;
    private hp k;
    private short[] l;
    private int[] m;
    private byte[] n;
    private boolean o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private int v;
    private ds w;
    private boolean x;
    private int y;
    private Object z;
    private oo aa;
    private short[][] ab;
    private int ac;
    private int ad;
    private short[] ae;
    private int af;
    private int ag;
    private boolean ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private boolean an;
    private int ao;
    private int ap;
    private short aq;
    private int ar;
    private int as;
    private short at;
    private int au;
    private short av;
    private int aw;
    private int ax;
    private int ay;
    private short az;
    private short a0;
    private short[] a1;
    private short[] a2;
    private int a3;
    private int a4;
    private int a5;
    private int a6;
    private int a7;
    private int a8;
    private boolean a9;
    private String ba;
    private int bb;
    
    public gc(final String ba) throws Exception {
        this.a = false;
        this.b = false;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = 0;
        this.w = null;
        this.x = false;
        this.y = 0;
        this.z = null;
        this.aa = new oo();
        this.ab = null;
        this.ac = 0;
        this.ad = 0;
        this.ae = null;
        this.af = 0;
        this.ag = 1;
        this.ah = false;
        this.ai = 0;
        this.aj = 0;
        this.an = true;
        this.ap = 0;
        this.aq = 0;
        this.ar = 7;
        this.as = 0;
        this.at = 0;
        this.au = 0;
        this.av = 0;
        this.aw = 0;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.a0 = 0;
        this.a3 = 0;
        this.a4 = 0;
        this.a5 = 0;
        this.a6 = 0;
        this.a7 = 0;
        this.a8 = 0;
        this.a9 = false;
        this.ba = null;
        this.bb = 0;
        this.ba = ba;
        if (gc.c == null) {
            gc.c = op.a();
            gc.e = op.a.a;
            gc.f = op.b.a;
        }
        this.r = op.c;
        this.s = op.d;
        if (gc.d == null) {
            gc.d = new int[11][255];
            for (int i = 0; i < 11; ++i) {
                for (int j = 0; j < 255; ++j) {
                    gc.d[i][j] = 7;
                }
            }
            for (int k = 0; k < 255; ++k) {
                gc.d[8][k] = 6;
            }
            gc.d[1][1] = 0;
            gc.d[3][3] = 1;
            gc.d[6][3] = 2;
            gc.d[7][3] = 3;
            gc.d[3][2] = -1;
            gc.d[6][2] = -2;
            gc.d[7][2] = -3;
            gc.d[4][1] = 4;
            gc.d[3][1] = 5;
            gc.d[10][15] = 19639;
        }
    }
    
    public final void a() {
        if (ji.util.e.ai()) {
            this.p = true;
        }
    }
    
    public final void a(final af h, final dx j, final ds w, final hp k, final q q, final Object z) throws Exception {
        this.z = z;
        this.u = j.m;
        this.aa.a(z, j, q, this.ba);
        this.y = 0;
        if (j.b3 > 0) {
            this.u = j.b3;
        }
        this.j = j;
        this.h = h;
        this.w = w;
        this.k = k;
        this.ah = w.j();
        this.x = false;
        this.o = false;
        this.a9 = false;
        if (j.ap == 2) {
            j.ar = true;
        }
        else {
            this.q = false;
        }
        if (j.ar) {
            if ((j.at & 0x1) != 0x0) {
                this.x = true;
            }
            else {
                this.x = false;
            }
            if ((j.at & 0x2) > 0) {
                throw new Exception(ji.util.d.b(323, this.ba));
            }
            if ((j.at & 0x4) <= 0) {
                this.o = false;
            }
        }
        else {
            this.o = true;
        }
        int n = j.n;
        int n2 = j.m;
        if (j.b3 > 0) {
            n2 = j.b3;
        }
        if (j.b4 > 0) {
            n = j.b4;
        }
        if (ji.util.d.du()) {
            this.t = Math.max(n / 5, 10);
        }
        else {
            this.t = Math.max(n / 15, 16);
        }
        this.t = Math.min(this.t, j.n);
        this.v = this.t;
        this.l = new short[n2 * 2];
        if (this.ah) {
            this.m = new int[n2];
        }
        this.ac = 2 * n2 + 25;
        this.ae = new short[this.ac];
        for (int i = 1; i < this.ae.length; ++i) {
            this.ae[i] = (short)n2;
        }
        this.ab = new short[2][this.ac];
        for (int l = 0; l < 2; ++l) {
            System.arraycopy(this.ae, 0, this.ab[l], 0, this.ac);
        }
        this.ag = 1;
        this.af = 0;
        if (h != null) {
            if (this.i == null) {
                this.i = new a6(this, 4, "");
            }
            if (ji.util.d.du() && !j.b8 && j.ak) {
                this.i = new a6(this, 23, "");
            }
        }
        this.p = false;
    }
    
    public final void b() {
        this.aa.a(this.z, this.j, this.h, this.w, this.ah, this.ba);
        this.j = null;
        this.h = null;
        this.w = null;
        this.k = null;
        this.n = null;
        this.l = null;
        this.i = null;
    }
    
    public final int a(int b4, final int n, final int n2, final int n3) throws Exception {
        int n4 = 0;
        final int n5 = 10;
        int b5 = 0;
        int n6 = 0;
        if (this.j.b4 > 0) {
            b4 = this.j.b4;
        }
        final boolean o = this.o;
        final boolean q = this.q;
        final int n7 = b4 - 1;
        final int n8 = b4 / 2;
        while (n6 == 0 && n4 < n5) {
            boolean b6;
            try {
                this.a7 = 0;
                this.bb = 0;
                this.aa.a(this.j, this.y);
                b5 = this.b(b4, n, n2, n3);
                if (this.a7 >= n8) {
                    this.a9 = true;
                    n6 = 1;
                    break;
                }
                b6 = true;
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
                if (this.a7 >= n7) {
                    this.a9 = true;
                }
                if (this.a9) {
                    break;
                }
                b6 = true;
            }
            if (b6) {
                switch (n4) {
                    case 0: {
                        this.x = false;
                        this.q = false;
                        this.o = false;
                        break;
                    }
                    case 1: {
                        this.x = false;
                        this.q = false;
                        this.o = true;
                        break;
                    }
                    case 2: {
                        this.x = true;
                        this.q = false;
                        this.o = false;
                        break;
                    }
                    case 3: {
                        this.x = true;
                        this.q = false;
                        this.o = false;
                        break;
                    }
                    case 4: {
                        this.x = true;
                        this.q = false;
                        this.o = true;
                        break;
                    }
                    case 5: {
                        this.x = true;
                        this.q = true;
                        this.o = true;
                        break;
                    }
                    case 6: {
                        this.x = true;
                        this.q = true;
                        this.o = false;
                        break;
                    }
                    case 7: {
                        this.x = false;
                        this.q = true;
                        this.o = true;
                        break;
                    }
                    case 8: {
                        this.x = false;
                        this.q = true;
                        this.o = false;
                        break;
                    }
                    case 9: {
                        if (!this.b) {
                            this.b = true;
                            n4 = 0;
                            this.x = false;
                            this.q = false;
                            this.o = false;
                            break;
                        }
                        break;
                    }
                }
                ++n4;
                this.w.b(this.a7);
            }
        }
        if (this.j.b8) {
            b5 = 0;
            ++this.y;
            if (this.h != null) {
                this.i.a("".concat(String.valueOf(String.valueOf(100 * this.y / this.j.b7))));
                this.h.a(this.i);
            }
        }
        return b5;
    }
    
    private final void c() {
        if (ji.util.e.aj() && this.j.x == 1) {
            this.l[this.as++] = 0;
        }
    }
    
    private final int b(final int a4, final int n, final int a5, final int n2) throws Exception {
        this.ai = 0;
        this.aj = 0;
        this.ak = this.r - 1;
        this.al = this.j.n;
        gc.g = op.a.a;
        this.am = a4 + n2;
        this.an = true;
        this.ao = n2;
        this.ap = 0;
        this.aq = 0;
        this.ar = 7;
        this.as = 0;
        this.at = 0;
        this.au = 0;
        this.av = 0;
        this.aw = 0;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.a0 = 0;
        this.a1 = this.ab[this.ag];
        this.a2 = this.ab[this.af];
        this.a3 = n2;
        this.a4 = a4;
        this.a5 = a5;
        short n3 = 0;
        System.arraycopy(this.ae, 0, this.a2, 0, this.ax);
        System.arraycopy(this.ae, 0, this.a1, 0, this.ad);
        this.a2[0] = -1;
        this.a1[0] = -1;
        int n4 = 1;
        boolean b = false;
        try {
            if (this.j.ap == 3) {
                b = true;
            }
            this.az = 0;
            this.av = 0;
            this.aw = 1;
            this.az = 0;
            this.ay = 0;
            gc.g = gc.e;
            this.ax = 0;
            if (this.n == null) {
                this.n = new byte[a5];
            }
            else if (this.n.length < a5) {
                this.n = null;
                this.n = new byte[a5];
            }
            this.k.a((long)n);
            this.k.a(this.n, 0, a5);
            this.a6 = this.n[this.ai++];
            this.c();
            final int n5 = a5 - 1;
            Block_58: {
                while (this.ao <= this.am) {
                    if (this.ai > n5) {
                        break;
                    }
                    while (this.aj < this.ak && this.ai < a5) {
                        this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                        if (this.ar < 0) {
                            this.ar = 7;
                            this.a6 = this.n[this.ai++];
                        }
                        ++this.aj;
                    }
                    if (this.ai > n5) {
                        break;
                    }
                    if (this.b) {
                        if (this.au < gc.g.length) {
                            this.at = gc.g[this.au][this.aj];
                        }
                    }
                    else {
                        this.at = gc.g[this.au][this.aj];
                    }
                    if (this.at != -9 && this.at < 64) {
                        this.aq += this.at;
                    }
                    else {
                        if (this.at != -9) {
                            this.aq += this.at;
                        }
                        while (true) {
                            this.at = -9;
                            while (this.at == -9 && this.ai < a5) {
                                this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                                if (this.ar < 0) {
                                    this.ar = 7;
                                    this.a6 = this.n[this.ai++];
                                }
                                ++this.aj;
                                if (this.b) {
                                    if (this.aj > 12 && this.au == 1) {
                                        this.aj = 0;
                                        this.au = 0;
                                    }
                                    else {
                                        if (this.au >= gc.g.length || this.aj >= gc.g[this.au].length) {
                                            continue;
                                        }
                                        this.at = gc.g[this.au][this.aj];
                                    }
                                }
                                else {
                                    this.at = gc.g[this.au][this.aj];
                                }
                            }
                            if (this.at == -9) {
                                break;
                            }
                            if (this.at < 64) {
                                this.aq += this.at;
                                break;
                            }
                            if (this.at == 32000) {
                                break;
                            }
                            this.aq += this.at;
                            this.au = 0;
                            this.aj = 0;
                        }
                    }
                    if (this.ai > n5 || (this.ai == n5 && this.ar == 0)) {
                        this.at = 32000;
                    }
                    if (this.b && this.aq >= this.u) {
                        this.at = 32000;
                    }
                    this.au = 0;
                    this.aj = 0;
                    if (this.at != 32000) {
                        this.az += this.aq;
                        this.l[this.as++] = this.aq;
                        n3 += this.aq;
                        this.a1[++this.ax] = this.az;
                        this.an = !this.an;
                        if (this.an) {
                            gc.g = op.a.a;
                            this.ak = this.r - 1;
                        }
                        else {
                            gc.g = op.b.a;
                            this.ak = this.s - 1;
                        }
                        if (this.q) {
                            this.ap += this.aq;
                            if (this.ap == this.u) {
                                this.at = 32000;
                            }
                        }
                        this.aq = 0;
                    }
                    if (this.at != 32000) {
                        continue;
                    }
                    final short n6 = (short)(n3 + this.aq);
                    if (this.p) {
                        break;
                    }
                    this.az = 0;
                    if (this.h != null && !this.j.b8) {
                        --this.v;
                        if (this.v == 0) {
                            this.i.a("".concat(String.valueOf(String.valueOf(100 * this.ao / this.al))));
                            this.h.a(this.i);
                            this.v = this.t;
                        }
                    }
                    if (n4 == 0 && (!this.b || n6 > 0)) {
                        if (this.ah) {
                            if (this.w.x()) {
                                final short[] a6 = this.w.a(this.l, this.as, this.ao);
                                final int length = a6.length;
                                this.a8 = 0;
                                while (this.a8 < length) {
                                    this.m[this.a8] = a6[this.a8];
                                    ++this.a8;
                                }
                                this.aa.a(this.m, length, this.ao, this.bb, this.y, this.j, this.w, true);
                            }
                            else {
                                this.a8 = 0;
                                while (this.a8 < this.as) {
                                    this.m[this.a8] = this.l[this.a8];
                                    ++this.a8;
                                }
                                this.aa.a(this.m, this.as, this.ao, this.bb, this.y, this.j, this.w, true);
                            }
                        }
                        else {
                            this.aa.a(this.l, this.as, this.bb, this.y, this.w, this.j);
                        }
                        ++this.a7;
                        ++this.bb;
                    }
                    n4 = 0;
                    if (this.as > 0) {
                        this.af = this.ag;
                        this.ag = 1 - this.ag;
                        this.a1 = this.ab[this.ag];
                        this.a2 = this.ab[this.af];
                        if (this.ao < this.am) {
                            System.arraycopy(this.ae, 0, this.a1, 0, this.ad);
                            this.a1[0] = -1;
                            this.ad = this.ax;
                        }
                    }
                    this.as = 0;
                    ++this.ao;
                    n3 = 0;
                    this.aq = 0;
                    this.ap = 0;
                    this.az = 0;
                    this.av = 0;
                    this.aw = 1;
                    this.az = 0;
                    this.ay = 0;
                    gc.g = gc.e;
                    this.ax = 0;
                    if (this.o) {
                        if (this.ar != 7 && this.ao < this.am) {
                            this.a6 = this.n[this.ai++];
                            this.ar = 7;
                        }
                    }
                    else if (this.x) {
                        final int n7 = 0x1 & this.a6 >>> this.ar--;
                        if (this.ar < 0) {
                            this.ar = 7;
                            this.a6 = this.n[this.ai++];
                        }
                        if (n7 == 0) {
                            if (this.a7 < a4) {
                                this.d();
                            }
                            n4 = 1;
                            this.as = 0;
                            this.aq = 0;
                            this.ap = 0;
                            this.aw = 1;
                            this.az = 0;
                            this.ay = 0;
                            gc.g = gc.e;
                            this.ax = 0;
                        }
                    }
                    this.ak = this.r - 1;
                    this.an = true;
                    gc.g = op.a.a;
                    this.c();
                    if (b && this.ao - n2 >= a4) {
                        break Block_58;
                    }
                }
                return this.ao - n2;
            }
            this.a7 = this.ao - n2;
        }
        catch (Exception ex) {
            throw ex;
        }
        return this.ao - n2;
    }
    
    private final void d() throws Exception {
        String s = null;
        try {
            this.av = 0;
            this.aw = 1;
            this.az = 0;
            this.ay = 0;
            this.aj = 0;
            this.au = 0;
            this.ak = this.r;
            gc.g = gc.e;
            this.ax = 0;
            this.as = 0;
            this.c();
            while (this.az < this.u) {
                this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                if (this.ar < 0) {
                    this.ar = 7;
                    this.a6 = this.n[this.ai++];
                }
                ++this.aj;
                final int n = gc.d[this.aj][this.au];
                if (n == 7) {
                    continue;
                }
                this.aj = 0;
                this.au = 0;
                if (n == 5) {
                    if (this.ay == 0) {
                        this.ak = this.r;
                        gc.g = gc.e;
                    }
                    else {
                        this.ak = this.s;
                        gc.g = gc.f;
                    }
                    this.aq = 0;
                    while (this.aj < this.ak) {
                        this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                        if (this.ar < 0) {
                            this.ar = 7;
                            this.a6 = this.n[this.ai++];
                        }
                        ++this.aj;
                    }
                    this.at = gc.g[this.au][this.aj];
                    if (this.at != -9 && this.at < 64) {
                        this.aq += this.at;
                    }
                    else {
                        if (this.at != -9) {
                            this.aq += this.at;
                        }
                        while (true) {
                            this.at = -9;
                            while (this.at == -9) {
                                this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                                if (this.ar < 0) {
                                    this.ar = 7;
                                    this.a6 = this.n[this.ai++];
                                }
                                ++this.aj;
                                if (this.b) {
                                    if (this.au >= gc.g.length) {
                                        continue;
                                    }
                                    this.at = gc.g[this.au][this.aj];
                                }
                                else {
                                    this.at = gc.g[this.au][this.aj];
                                }
                            }
                            this.aq += this.at;
                            if (this.at < 64) {
                                break;
                            }
                            this.au = 0;
                            this.aj = 0;
                        }
                    }
                    this.au = 0;
                    this.aj = 0;
                    this.av += this.aq;
                    this.l[this.as++] = this.av;
                    this.av = 0;
                    this.az += this.aq;
                    this.a1[++this.ax] = this.az;
                    this.ay = 1 - this.ay;
                    this.aq = 0;
                    if (this.ay == 0) {
                        this.ak = this.r;
                        gc.g = gc.e;
                    }
                    else {
                        this.ak = this.s;
                        gc.g = gc.f;
                    }
                    while (this.aj < this.ak) {
                        this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                        if (this.ar < 0) {
                            this.ar = 7;
                            this.a6 = this.n[this.ai++];
                        }
                        ++this.aj;
                    }
                    if (this.b) {
                        if (this.au < gc.g.length) {
                            this.at = gc.g[this.au][this.aj];
                        }
                    }
                    else {
                        this.at = gc.g[this.au][this.aj];
                    }
                    if (this.at != -9 && this.at < 64) {
                        this.aq += this.at;
                    }
                    else {
                        if (this.at != -9) {
                            this.aq += this.at;
                        }
                        while (true) {
                            this.at = -9;
                            while (this.at == -9) {
                                this.au = (this.au << 1 | (0x1 & this.a6 >>> this.ar--));
                                if (this.ar < 0) {
                                    this.ar = 7;
                                    this.a6 = this.n[this.ai++];
                                }
                                ++this.aj;
                                if (this.b) {
                                    if (this.au >= gc.g.length) {
                                        continue;
                                    }
                                    this.at = gc.g[this.au][this.aj];
                                }
                                else {
                                    this.at = gc.g[this.au][this.aj];
                                }
                            }
                            this.aq += this.at;
                            if (this.at < 64) {
                                break;
                            }
                            this.au = 0;
                            this.aj = 0;
                        }
                    }
                    this.au = 0;
                    this.aj = 0;
                    this.l[this.as++] = this.aq;
                    if (this.az >= this.u) {
                        continue;
                    }
                    this.au = 0;
                    this.aj = 0;
                    this.az += this.aq;
                    this.ay = 1 - this.ay;
                    this.a1[++this.ax] = this.az;
                    if (this.az >= this.u) {
                        continue;
                    }
                    while (this.a2[this.aw] <= this.az && this.aw < this.ac) {
                        this.aw += 2;
                    }
                }
                else if (n == 0) {
                    if (this.ax > 0) {
                        while (this.a2[this.aw] <= this.az && this.aw < this.ac) {
                            this.aw += 2;
                        }
                    }
                    this.a0 = this.a2[this.aw];
                    this.l[this.as++] = (short)(this.av + (this.a0 - this.az));
                    this.a1[++this.ax] = this.a0;
                    this.az = this.a0;
                    if (this.a0 >= this.u) {
                        continue;
                    }
                    this.av = 0;
                    this.ay = 1 - this.ay;
                    if (this.a2[this.aw] > this.az) {
                        --this.aw;
                    }
                    else {
                        ++this.aw;
                    }
                }
                else if (n == 4) {
                    if (this.ax > 0) {
                        while (this.a2[this.aw] <= this.az && this.aw < this.ac) {
                            this.aw += 2;
                        }
                    }
                    ++this.aw;
                    this.aq = (short)(this.a2[this.aw] - this.az);
                    this.av += this.aq;
                    this.az += this.aq;
                    ++this.aw;
                }
                else if (n < 0) {
                    if (this.ax > 0) {
                        while (this.a2[this.aw] <= this.az && this.aw < this.ac) {
                            this.aw += 2;
                        }
                    }
                    this.a0 = (short)(this.a2[this.aw] + n);
                    this.l[this.as++] = (short)(this.av + (this.a0 - this.az));
                    this.av = 0;
                    this.a1[++this.ax] = this.a0;
                    this.az = this.a0;
                    if (this.az >= this.u) {
                        continue;
                    }
                    this.ay = 1 - this.ay;
                    if (this.a2[this.aw] > this.az) {
                        --this.aw;
                    }
                    else {
                        ++this.aw;
                    }
                }
                else {
                    if (n == 19639) {
                        s = "Uncompressed G4 extensions are not yet supported (please contact support@daeja.com for more information)";
                        this.ao = this.am - 1;
                        break;
                    }
                    if (n == 6) {
                        this.ao = this.am - 1;
                        break;
                    }
                    if (this.ax > 0) {
                        while (this.a2[this.aw] <= this.az && this.aw < this.ac) {
                            this.aw += 2;
                        }
                    }
                    this.a0 = (short)(this.a2[this.aw] + n);
                    this.l[this.as++] = (short)(this.av + (this.a0 - this.az));
                    this.a1[++this.ax] = this.a0;
                    this.az = this.a0;
                    if (this.a0 >= this.u) {
                        continue;
                    }
                    this.av = 0;
                    this.ay = 1 - this.ay;
                    if (this.a2[this.aw] > this.az) {
                        --this.aw;
                    }
                    else {
                        ++this.aw;
                    }
                }
            }
            if (this.ah) {
                if (this.w.x()) {
                    final short[] a = this.w.a(this.l, this.as, this.ao);
                    final int length = a.length;
                    this.a8 = 0;
                    while (this.a8 < length) {
                        this.m[this.a8] = a[this.a8];
                        ++this.a8;
                    }
                    this.aa.a(this.m, length, this.ao, this.bb, this.y, this.j, this.w, true);
                }
                else {
                    this.a8 = 0;
                    while (this.a8 < this.as) {
                        this.m[this.a8] = this.l[this.a8];
                        ++this.a8;
                    }
                    this.aa.a(this.m, this.as, this.ao, this.bb, this.y, this.j, this.w, true);
                }
            }
            else {
                this.aa.a(this.l, this.as, this.bb, this.y, this.w, this.j);
            }
            ++this.bb;
            ++this.a7;
            this.as = 0;
            if (this.h != null && !this.j.b8) {
                --this.v;
                if (this.v <= 0) {
                    this.i.a("".concat(String.valueOf(String.valueOf(100 * this.ao / this.j.n))));
                    this.h.a(this.i);
                    this.v = this.t;
                }
            }
            this.af = this.ag;
            this.ag = 1 - this.ag;
            this.a1 = this.ab[this.ag];
            this.a2 = this.ab[this.af];
            if (this.ao + 1 < this.am) {
                System.arraycopy(this.ae, 0, this.a1, 0, this.ad);
                this.a1[0] = -1;
                this.ad = this.ax;
            }
        }
        catch (Exception ex) {}
        if (s != null) {
            throw new Exception(s);
        }
    }
    
    static {
        gc.c = null;
        gc.d = null;
        gc.e = null;
        gc.f = null;
        gc.g = null;
    }
}
