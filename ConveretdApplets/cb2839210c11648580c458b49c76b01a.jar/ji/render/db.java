// 
// Decompiled by Procyon v0.5.30
// 

package ji.render;

import java.awt.image.IndexColorModel;
import ji.util.c3;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import ji.io.h;
import java.util.zip.CRC32;
import ji.util.i;
import ji.util.e;
import ji.util.d;
import java.awt.Color;
import java.awt.Rectangle;
import ji.v1event.a6;
import ji.v1event.af;
import java.util.Vector;
import ji.image.dx;
import java.awt.image.ColorModel;
import ji.image.ds;

public class db
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    public int i;
    public int j;
    public boolean k;
    private ds l;
    private int m;
    private int n;
    private int o;
    private int[] p;
    private int q;
    private int r;
    private byte[] s;
    private byte[] t;
    private byte[] u;
    private byte[] v;
    private byte[] w;
    private byte[] x;
    private byte[] y;
    private byte[] z;
    private boolean aa;
    private ColorModel ab;
    private ColorModel ac;
    private ColorModel ad;
    private ColorModel ae;
    private ColorModel af;
    private ColorModel ag;
    private ColorModel ah;
    private ColorModel ai;
    private ColorModel aj;
    private ColorModel ak;
    private int al;
    private int am;
    private boolean an;
    private boolean ao;
    private int[] ap;
    private int[] aq;
    private int[] ar;
    private int[] as;
    private int[] at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private int ay;
    private int az;
    private int a0;
    private dx a1;
    private ColorModel a2;
    private ColorModel a3;
    private int a4;
    private int a5;
    private byte a6;
    private int a7;
    private int a8;
    private boolean a9;
    private boolean ba;
    private boolean bb;
    private int bc;
    private int bd;
    private int be;
    private String bf;
    private int[] bg;
    private boolean bh;
    private int[][] bi;
    private byte[] bj;
    private byte[] bk;
    private byte[] bl;
    private int[] bm;
    private Vector bn;
    private Vector bo;
    private boolean[] bp;
    private int bq;
    private int br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    private int b0;
    private boolean b1;
    private boolean b2;
    private byte[] b3;
    private byte[] b4;
    private byte[] b5;
    private int[] b6;
    private int[] b7;
    private int[] b8;
    private boolean b9;
    private byte[] ca;
    private int[] cb;
    private String cc;
    private boolean cd;
    private int ce;
    private boolean cf;
    private boolean cg;
    private boolean ch;
    private boolean ci;
    private af cj;
    private a6 ck;
    private boolean cl;
    private boolean cm;
    private boolean cn;
    private boolean co;
    private boolean cp;
    private boolean cq;
    private Rectangle cr;
    private short[] cs;
    private byte[] ct;
    private int cu;
    private Vector cv;
    private short[] cw;
    private int[] cx;
    private int[] cy;
    private int[] cz;
    private int c0;
    private boolean c1;
    private boolean c2;
    private int c3;
    private long c4;
    private long c5;
    private long c6;
    private long c7;
    private Object c8;
    private int c9;
    private int da;
    private Color db;
    private Color dc;
    private boolean dd;
    private int de;
    private boolean df;
    private boolean dg;
    private boolean dh;
    private long di;
    private boolean dj;
    private int dk;
    private int dl;
    private int dm;
    private int dn;
    private int do;
    
    public db() {
        this.a = ji.util.d.du;
        this.b = ji.util.d.dv;
        this.c = this.b;
        this.d = ji.util.d.dw;
        this.e = ji.util.d.dx;
        this.f = ji.util.d.dy;
        this.g = ji.util.d.dz;
        this.h = ji.util.d.dv;
        this.i = 0;
        this.j = 0;
        this.k = false;
        this.l = null;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.q = 0;
        this.r = 0;
        this.aa = false;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = 0;
        this.am = 0;
        this.an = false;
        this.ao = false;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = 0;
        this.av = 0;
        this.aw = 0;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.a0 = 0;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = 0;
        this.a5 = 0;
        this.a6 = 0;
        this.a7 = 0;
        this.a8 = 0;
        this.a9 = false;
        this.ba = false;
        this.bb = false;
        this.bc = 0;
        this.bd = 0;
        this.be = 1;
        this.bf = null;
        this.bg = null;
        this.bh = false;
        this.bi = null;
        this.bj = null;
        this.bk = null;
        this.bl = null;
        this.bm = null;
        this.bn = null;
        this.bo = null;
        this.bp = null;
        this.bq = 0;
        this.br = 0;
        this.bs = 0;
        this.bt = 0;
        this.bu = 0;
        this.bv = 0;
        this.bw = 0;
        this.bx = 0;
        this.by = 0;
        this.bz = 0;
        this.b0 = 0;
        this.b1 = false;
        this.b2 = false;
        this.b3 = null;
        this.b4 = null;
        this.b5 = null;
        this.b6 = null;
        this.b7 = null;
        this.b8 = null;
        this.b9 = false;
        this.ca = null;
        this.cb = null;
        this.cc = null;
        this.cd = false;
        this.ce = 0;
        this.cf = false;
        this.cg = false;
        this.ch = false;
        this.ci = false;
        this.cj = null;
        this.ck = new a6(this, 4, "");
        this.cl = false;
        this.cm = false;
        this.cn = false;
        this.co = false;
        this.cp = false;
        this.cq = false;
        this.cr = null;
        this.cs = null;
        this.ct = null;
        this.cv = new Vector(0);
        this.cw = null;
        this.cx = null;
        this.cy = null;
        this.cz = null;
        this.c0 = 0;
        this.c1 = false;
        this.c2 = false;
        this.c3 = 0;
        this.c4 = 0L;
        this.c5 = 0L;
        this.c6 = 0L;
        this.c7 = 0L;
        this.c8 = null;
        this.c9 = -1;
        this.da = -1;
        this.db = Color.white;
        this.dc = Color.black;
        this.dd = false;
        this.de = 0;
        this.df = true;
        this.dg = false;
        this.dh = true;
        this.di = -1L;
        this.dj = false;
        this.dk = 0;
    }
    
    public void a() {
        this.di = -1L;
    }
    
    public long b() {
        return this.di;
    }
    
    public final boolean c() {
        if (this.bb) {
            this.b1 = true;
            if (ji.util.d.cy()) {
                ji.util.d.eu();
            }
        }
        return this.b1;
    }
    
    public final boolean d() {
        return this.b1;
    }
    
    public final boolean e() {
        return this.bb;
    }
    
    public final void f() {
        this.aa = false;
        this.aj = null;
    }
    
    public final void a(final boolean cf) {
        this.cf = cf;
    }
    
    public final void b(final boolean dd) {
        if (this.dd != dd) {
            this.dd = dd;
            this.f();
        }
    }
    
    public final void a(final Color db) {
        boolean b = false;
        if (this.db == null && db != null) {
            b = true;
        }
        if (this.db != null && db == null) {
            b = true;
        }
        if (this.db != null && db != null && !this.db.equals(db)) {
            b = true;
        }
        if (b) {
            this.db = db;
            this.f();
        }
    }
    
    public final void b(final Color dc) {
        boolean b = false;
        if (this.dc == null && dc != null) {
            b = true;
        }
        if (this.dc != null && dc == null) {
            b = true;
        }
        if (this.dc != null && dc != null && !this.dc.equals(dc)) {
            b = true;
        }
        if (b) {
            this.dc = dc;
            this.f();
        }
    }
    
    public final void c(final boolean dg) {
        this.dg = dg;
    }
    
    public final boolean g() {
        if (this.c2) {
            return this.ao && this.an && !this.a9;
        }
        return this.an && !this.a9;
    }
    
    public final void d(final boolean ch) {
        this.ch = ch;
    }
    
    public final void e(final boolean ci) {
        this.ci = ci;
    }
    
    public final void a(final dx a1, final int n, final int n2, final boolean b, final int bt, final boolean cd, final int al, final int am, final af cj, final boolean cp, final boolean c2, final int c3, final int da, final boolean cq, final boolean b2, final boolean b3, final boolean df, final boolean ao) {
        boolean b4 = true;
        this.a1 = a1;
        this.al = al;
        this.am = am;
        this.cd = cd;
        this.cj = cj;
        this.cp = cp;
        this.c2 = c2;
        this.c9 = c3;
        this.da = da;
        this.cq = cq;
        this.b2 = b2;
        this.df = df;
        this.ao = ao;
        if (this.bt != bt) {
            this.bt = bt;
            this.f();
        }
        this.b1 = false;
        if (this.cc != null && a1 != null && !b3 && a1.f != null && this.cc.equals(a1.f)) {
            b4 = false;
        }
        if (a1.f != null && !b3) {
            if (this.ce == a1.v) {
                b4 = false;
            }
            this.cc = a1.f;
            this.ce = a1.v;
        }
        else {
            this.cc = null;
            this.ce = 0;
        }
        if (b4) {
            this.c3 = 0;
        }
        if (this.n != a1.n || this.m != a1.m || this.ay != n || this.az != n2 || this.an != b || b4) {
            this.m = this.k();
            this.n = this.l();
            this.ay = n;
            this.az = n2;
            this.an = b;
            final double n3 = this.n / n2;
            this.dl = (int)(n3 + 0.5);
            this.dm = (int)Math.round((n3 + 1.0) / 2.0);
            this.do = this.n - 1;
            final double n4 = this.m / n;
            this.dn = this.m - 1;
            if (c2 || ji.util.d.b()) {
                if (n > this.j() || n2 > this.m()) {
                    if (this.m() > 0 && this.j() > 0) {
                        this.a9 = true;
                    }
                }
                else {
                    this.a9 = false;
                }
                this.ba = false;
                if (!this.a9 && n == this.j() && n2 == this.m() && a1.ac == a1.ad) {
                    this.ba = true;
                }
            }
            else if (n + 5 >= this.j() || n2 + 5 >= this.m()) {
                if (this.m() > 0 && this.j() > 0) {
                    this.a9 = true;
                }
            }
            else {
                this.a9 = false;
            }
            this.t();
        }
        this.m = this.k();
        this.n = this.l();
        this.ay = n;
        this.az = n2;
        this.an = b;
        if (c2) {
            if (n > this.j() || n2 > this.m()) {
                if (this.m() > 0 && this.j() > 0) {
                    this.a9 = true;
                }
            }
            else {
                this.a9 = false;
            }
        }
        else if (n + 5 >= this.j() || n2 + 5 >= this.m()) {
            if (this.m() > 0 && this.j() > 0) {
                this.a9 = true;
            }
        }
        else {
            this.a9 = false;
        }
        this.aa = false;
    }
    
    public final int h() {
        return this.ay;
    }
    
    public final int i() {
        return this.az;
    }
    
    private final int d(int n) {
        if (this.a1 != null) {
            if (this.a1.ag) {
                n = (int)Math.round(n * this.a1.af / this.a1.ad);
            }
            if (this.a1.c()) {
                n /= this.a1.d();
            }
        }
        return n;
    }
    
    public final void f(final boolean dj) {
        this.dj = dj;
    }
    
    public final void a(final ds l, final Rectangle rectangle, final byte[] ca, final Object c8, final String bf) throws Exception {
        try {
            if (l != null) {
                l.c(true);
                l.b(this.b9);
            }
            this.b1 = false;
            this.bb = true;
            try {
                Label_0852: {
                    if (this.a1 != null && rectangle.height > 0 && rectangle.width > 0) {
                        this.c8 = c8;
                        this.bf = bf;
                        rectangle.x = Math.max(rectangle.x, 0);
                        rectangle.y = Math.max(rectangle.y, 0);
                        this.ct = null;
                        this.cn = false;
                        this.y();
                        this.u();
                        this.l = l;
                        this.m = this.k();
                        this.n = this.l();
                        this.o = l.am();
                        if (this.au != this.ay || this.av != this.az || this.aw != this.m || this.ax != this.n) {
                            this.au = this.ay;
                            this.av = this.az;
                            this.aw = this.m;
                            this.ax = this.n;
                            this.w();
                            this.t();
                        }
                        this.a4 = rectangle.height;
                        this.a5 = rectangle.width;
                        final int az = this.az;
                        final int ay = this.ay;
                        this.bc = rectangle.width;
                        this.bd = rectangle.height;
                        if (rectangle.y + this.a4 > az) {
                            this.a4 = az - rectangle.y + 1;
                        }
                        if (rectangle.x + this.a5 > ay) {
                            this.a5 = ay - rectangle.x + 1;
                        }
                        this.a6 = 0;
                        this.ca = ca;
                        this.v();
                        if (!(this.dh = this.a1.bs)) {
                            this.a0 = 0;
                        }
                        if (l != null) {
                            if (l.v == 1) {
                                this.co = l.j();
                                if (this.ch) {
                                    this.d(rectangle);
                                }
                                else {
                                    final int n = (int)Math.round(this.m / this.c9 + 0.5);
                                    ji.util.d.u(n);
                                    if (ji.util.e.i() && (this.bt == 0 || this.bt == 4 || this.bt == 5)) {
                                        final int bt = this.bt;
                                        try {
                                            if (this.cq) {
                                                this.bt = 1;
                                                this.c(rectangle);
                                            }
                                            else {
                                                int b4 = ji.util.d.b4();
                                                if (this.bt == 4) {
                                                    b4 = ji.util.d.b4() * 2;
                                                }
                                                if (this.bt == 5) {
                                                    b4 = ji.util.d.b4() * 4;
                                                }
                                                if (n >= b4 && ji.util.d.di() != 1 && this.df) {
                                                    this.bt = 3;
                                                    this.c(rectangle);
                                                }
                                                else {
                                                    if (this.bt == 0) {
                                                        this.bt = 1;
                                                    }
                                                    this.b(rectangle);
                                                }
                                            }
                                            break Label_0852;
                                        }
                                        finally {
                                            this.bt = bt;
                                        }
                                    }
                                    if (this.cq) {
                                        this.bt = 1;
                                        this.c(rectangle);
                                    }
                                    else if (ji.util.e.i() && this.bt == 1) {
                                        this.b(rectangle);
                                    }
                                    else if (ji.util.e.i() && this.bt == 2) {
                                        this.c(rectangle);
                                    }
                                    else if (ji.util.e.i() && this.bt == 3) {
                                        this.c(rectangle);
                                    }
                                    else {
                                        this.a(rectangle);
                                    }
                                }
                            }
                            else if (l.v == 2) {
                                if (this.b != ji.util.d.ds && this.c3 == 0) {
                                    this.c1 = true;
                                    this.aa = false;
                                    this.h(rectangle);
                                    this.c3 = (int)(this.c4 / this.c7);
                                    this.c1 = false;
                                    this.f();
                                    this.a(this.dj, l);
                                }
                                this.h(rectangle);
                            }
                            else if (l.v == 3) {
                                if (this.b != ji.util.d.ds && this.c3 == 0) {
                                    this.c1 = true;
                                    this.g(rectangle);
                                    this.c3 = (int)(this.c4 / this.c7);
                                    this.c1 = false;
                                    this.f();
                                    this.a(this.dj, l);
                                }
                                this.g(rectangle);
                            }
                        }
                    }
                }
            }
            finally {
                try {
                    if (this.cj != null && !this.c2) {
                        this.ck.a("0");
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                    }
                }
                catch (Exception ex) {}
                this.l = null;
                this.ca = null;
                this.c8 = null;
                this.ct = null;
                this.bb = false;
            }
        }
        finally {
            if (ji.util.i.c(227)) {
                final CRC32 crc32 = new CRC32();
                crc32.update(ca);
                this.di = crc32.getValue();
            }
            if (l != null) {
                l.c(false);
            }
        }
    }
    
    public final void a(final ds l, final Rectangle rectangle, final int[] cb, final Object c8, final String bf) throws Exception {
        this.b1 = false;
        this.bb = true;
        try {
            if (this.a1 != null && rectangle.height > 0 && rectangle.width > 0) {
                if (l != null) {
                    l.b(this.b9);
                }
                this.c8 = c8;
                this.bf = bf;
                rectangle.x = Math.max(rectangle.x, 0);
                rectangle.y = Math.max(rectangle.y, 0);
                this.y();
                this.u();
                this.ct = null;
                this.l = l;
                this.m = this.k();
                this.n = this.l();
                this.o = l.am();
                if (this.au != this.ay || this.av != this.az || this.aw != this.m || this.ax != this.n) {
                    this.au = this.ay;
                    this.av = this.az;
                    this.aw = this.m;
                    this.ax = this.n;
                    this.w();
                    this.t();
                }
                this.a4 = rectangle.height;
                this.a5 = rectangle.width;
                final int az = this.az;
                final int ay = this.ay;
                this.bc = rectangle.width;
                this.bd = rectangle.height;
                if (rectangle.y + this.a4 > az) {
                    this.a4 = az - rectangle.y + 1;
                }
                if (rectangle.x + this.a5 > ay) {
                    this.a5 = ay - rectangle.x + 1;
                }
                this.a6 = 0;
                this.cb = cb;
                this.v();
                if (!(this.dh = this.a1.bs)) {
                    this.a0 = 0;
                }
                if (l != null && l.v == 4) {
                    if (l.v()) {
                        this.f(rectangle);
                    }
                    else {
                        if (this.b != ji.util.d.ds && this.c3 == 0) {
                            this.c1 = true;
                            this.e(rectangle);
                            this.c3 = (int)((this.c4 + this.c5 + this.c6) / (3 * this.c7));
                            this.c1 = false;
                        }
                        this.e(rectangle);
                    }
                }
            }
        }
        finally {
            if (ji.util.i.c(227)) {
                final CRC32 crc32 = new CRC32();
                final byte[] array = new byte[4];
                for (int i = 0; i < cb.length; ++i) {
                    a(cb[i], array);
                    crc32.update(array);
                }
                this.di = crc32.getValue();
            }
            try {
                if (this.cj != null && !this.c2) {
                    this.ck.a("0");
                    if (!this.b9) {
                        this.cj.a(this.ck);
                    }
                }
            }
            catch (Exception ex) {}
            if (l != null) {
                l.c(false);
            }
            this.l = null;
            this.ca = null;
            this.cb = null;
            this.c8 = null;
            this.ct = null;
            this.bb = false;
        }
    }
    
    public static final byte[] a(final int n, byte[] array) {
        if (array == null) {
            array = new byte[4];
        }
        array[0] = (byte)(n >> 24);
        array[1] = (byte)(n >> 16 & 0xFF);
        array[2] = (byte)(n >> 8 & 0xFF);
        array[3] = (byte)(n & 0xFF);
        return array;
    }
    
    private final void t() {
        if (this.g()) {
            this.a0 = 1;
        }
        else {
            this.a0 = 0;
        }
    }
    
    private final void u() {
        this.cl = ((this.am & 0x2) > 0);
        this.cm = ((this.am & 0x1) > 0);
        if (this.ch) {
            if (this.cl && this.ci) {
                this.cl = false;
            }
            else {
                this.cl = (this.cl || this.ci);
            }
        }
    }
    
    private final void v() {
        this.b0 = (this.bc + 7) / 8;
        this.b0 = (this.b0 + 3) / 4;
        this.b0 *= 4;
    }
    
    private void a(final Rectangle rectangle) throws Exception {
        if (this.as == null || this.at == null) {
            this.w();
        }
        this.q = 1;
        this.r = 1;
        int n = -1;
        int n2 = -1;
        final int o = this.o;
        if (this.b3 != null && this.b3.length < rectangle.width) {
            this.b3 = null;
        }
        if (this.b3 == null) {
            this.b3 = new byte[rectangle.width];
        }
        if (this.b4 != null && this.b4.length < rectangle.width) {
            this.b4 = null;
        }
        if (this.b4 == null) {
            this.b4 = new byte[rectangle.width];
        }
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = 0;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        this.bn = this.l.ai();
        if (this.bn != null) {
            this.bq = this.bn.size();
        }
        this.br = 999999;
        boolean b = false;
        this.bv = 1 + this.m / 32;
        final double n4 = this.n / this.az;
        final int n5 = (int)(n4 + 0.5);
        final int n6 = (int)Math.round((n4 + 1.0) / 4.0);
        final int n7 = this.n - 1;
        final int n8 = (int)(this.m / this.ay + 0.5) / 2;
        final int n9 = this.m - 1;
        this.bu = n5 + 2;
        this.e(this.bv);
        if (this.bi == null) {
            b = true;
        }
        else if (this.bi[0].length < this.bv) {
            b = true;
        }
        else if (this.bi.length < this.bu) {
            b = true;
        }
        if (b) {
            if (this.bm != null && this.bm.length < this.bv) {
                this.bm = null;
            }
            if (this.bm == null) {
                this.bm = new int[this.bv];
            }
            if (this.a0 == 0) {
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[1][this.bv];
                }
            }
            else {
                if (this.bi != null && this.bi.length < this.bu) {
                    this.bi = null;
                }
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[this.bu][this.bv];
                }
            }
        }
        this.bw = Math.max(this.m * x / this.ay, 0);
        this.bx = Math.min(this.m * n3 / this.ay, this.m);
        this.by = Math.max(this.bw / 32 - 1, 0);
        this.bz = Math.min(32 + (this.bx - this.bw) / 32, this.bv - this.by);
        final int[] array = this.bi[0];
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.k) {
                final int f = this.f(y);
                final int n10 = this.f(y + this.a4) + 1;
                final int n11 = x * this.m / this.ay;
                final int n12 = (x + this.a5) * this.m / this.ay + 1;
                this.i = 0;
                this.j = (n10 - f) * (n12 - n11);
                int n13 = 0;
                for (int j = f; j < n10; ++j) {
                    this.a(j, 0);
                    for (int k = n11; k < n12; ++k) {
                        n13 += (0x1 & array[k / 32] >>> 31 - k % 32);
                    }
                }
                this.i = (this.j - n13) * 255;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (this.a0 == 0) {
            if (this.a9) {
                for (int n14 = 0; n14 < this.a4 && !this.b1; ++n14) {
                    try {
                        final int f2 = this.f(y + n14);
                        if (f2 != n) {
                            int n15 = 0;
                            this.a(f2, 0);
                            for (int l = x; l < n3; ++l) {
                                final int n16 = l * this.m / this.ay;
                                if (n16 != n2) {
                                    this.a6 = (byte)(0x1 & array[n16 / 32] >>> 31 - n16 % 32);
                                    n2 = n16;
                                }
                                this.b3[n15++] = this.a6;
                            }
                            this.a(n14, true);
                        }
                        else {
                            this.a(n14, false);
                        }
                        n = f2;
                    }
                    catch (Exception ex4) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n14 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis2;
                        }
                    }
                }
            }
            else {
                for (int n17 = 0; n17 < this.a4 && !this.b1; ++n17) {
                    try {
                        int n18 = 0;
                        this.a(this.f(y + n17), 0);
                        for (int n19 = x; n19 < n3; ++n19) {
                            final int n20 = n19 * this.m / this.ay;
                            this.b3[n18++] = (byte)(0x1 & array[n20 / 32] >>> 31 - n20 % 32);
                        }
                        this.a(n17, true);
                        if (this.cj != null && !this.c2) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            if (currentTimeMillis3 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n17 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis3;
                            }
                        }
                    }
                    catch (Exception ex5) {}
                }
            }
        }
        else {
            final int n21 = this.a7 / 2;
            final int[] array2 = this.as.clone();
            final int[] array3 = this.at.clone();
            final int[] array4 = new int[32];
            try {
                try {
                    final int n22 = y + this.a4;
                    for (int n23 = 0; n23 < this.a4; ++n23) {
                        final int n24 = y + n23;
                        final int n25 = n24 * this.n / this.az;
                        final int max = Math.max(Math.min(n25 - n6, n7), 0);
                        int n26 = Math.min(n25 + n6, n7);
                        if (n26 == max) {
                            n26 = Math.min(max + 1, n7);
                        }
                        array2[n24] = max;
                        array3[n24] = n26;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                int n27 = 1;
                for (int n28 = 0; n28 < 32; ++n28) {
                    array4[n28] = n27;
                    n27 = (n27 << 1 | 0x1);
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            final int m = this.m;
            final int n29 = this.bi.length - 1;
            final int n30 = this.a7 + 1;
            for (int n31 = 0; n31 < this.a4 && !this.b1; ++n31) {
                final int n32 = y + n31;
                final int n33 = array2[n32];
                final int n34 = array3[n32];
                final int n35 = n34 - n33;
                int n36 = 0;
                for (int n37 = n33; n37 < n34; n37 += this.q) {
                    this.a(n37, n36);
                    n36 += this.q;
                }
                int n38 = 0;
                for (int n39 = x; n39 < n3; ++n39) {
                    final int max2 = Math.max(n39 * this.m / this.ay, 0);
                    int n40 = max2 / 32;
                    int n41 = 31 - max2 % 32;
                    int n42 = 0;
                    int n43 = this.a7 / 2;
                    int n44 = 0;
                    Label_2198: {
                        try {
                            while (n41 <= n43) {
                                for (int n45 = 0; n45 < n35; n45 += this.q) {
                                    final int n46 = this.bi[Math.min(n45, n29)][n40];
                                    if (n46 != 0) {
                                        final int n47 = n46 & array4[n41];
                                        if (n41 < 16) {
                                            n44 += this.p[n47] * 255;
                                        }
                                        else {
                                            n44 += this.p[n47 & 0xFFFF] * 255;
                                            n44 += this.p[n47 >>> 16] * 255;
                                        }
                                    }
                                    n42 += n41 + 1;
                                    if (n44 > 0 && this.bt == 3) {
                                        n44 = 1;
                                        n42 = 1;
                                        break;
                                    }
                                }
                                n43 -= n41;
                                n41 = 31;
                                if (++n40 >= m) {
                                    break;
                                }
                                if (n44 > 0 && this.bt == 3) {
                                    n44 = 1;
                                    n42 = 1;
                                    break;
                                }
                            }
                            if (n43 < n41 && n40 < m) {
                                Block_90: {
                                    for (int n48 = 0; n48 < n35; n48 += this.q) {
                                        final int n49 = this.bi[Math.min(n48, n29)][n40];
                                        if (n49 != 0) {
                                            final int n50 = n49 >>> n41 - n43 & array4[n43];
                                            if (n41 < 16) {
                                                n44 += this.p[n50] * 255;
                                            }
                                            else {
                                                n44 += this.p[n50 & 0xFFFF] * 255;
                                                n44 += this.p[n50 >>> 16] * 255;
                                            }
                                        }
                                        n42 += n43 + 1;
                                        if (n44 > 0 && this.bt == 3) {
                                            break Block_90;
                                        }
                                    }
                                    break Label_2198;
                                }
                                n44 = 1;
                                n42 = 1;
                            }
                        }
                        catch (Exception ex6) {}
                    }
                    if (n42 > 0) {
                        this.b3[n38] = (byte)Math.min(n44 / n42, 255);
                    }
                    ++n38;
                }
                this.a(n31, true);
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis4 = System.currentTimeMillis();
                    if (currentTimeMillis4 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n31 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis4;
                    }
                }
            }
        }
        if (this.dk > 0) {
            if (ji.util.i.c(85)) {
                ji.io.h.d(this.bf, String.valueOf(String.valueOf(new StringBuffer("renderMonoPartial1: Color length exceeded line length line on ").append(this.dk).append(" lines"))));
            }
            this.dk = 0;
        }
        this.bn = null;
        this.bo = null;
        this.bp = null;
    }
    
    private void b(final Rectangle rectangle) throws Exception {
        if (this.as == null || this.at == null) {
            this.w();
        }
        this.q = 1;
        this.r = 1;
        int n = -1;
        int n2 = -1;
        final int o = this.o;
        this.dk = 0;
        if (this.b3 != null && this.b3.length < rectangle.width) {
            this.b3 = null;
        }
        if (this.b3 == null) {
            this.b3 = new byte[rectangle.width];
        }
        if (this.b4 != null && this.b4.length < rectangle.width) {
            this.b4 = null;
        }
        if (this.b4 == null) {
            this.b4 = new byte[rectangle.width];
        }
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = 0;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        this.bn = this.l.ai();
        if (this.bn != null) {
            this.bq = this.bn.size();
        }
        this.br = 999999;
        boolean b = false;
        this.bv = 1 + this.m / 32;
        this.bu = this.dl + 2;
        this.e(this.bv);
        if (this.bi == null) {
            b = true;
        }
        else if (this.bi[0].length < this.bv) {
            b = true;
        }
        else if (this.bi.length < this.bu) {
            b = true;
        }
        if (b) {
            if (this.bm != null && this.bm.length < this.bv) {
                this.bm = null;
            }
            if (this.bm == null) {
                this.bm = new int[this.bv];
            }
            if (this.a0 == 0) {
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[1][this.bv];
                }
            }
            else {
                if (this.bi != null && this.bi.length < this.bu) {
                    this.bi = null;
                }
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[this.bu][this.bv];
                }
            }
        }
        this.bw = Math.max(this.m * x / this.ay, 0);
        this.bx = Math.min(this.m * n3 / this.ay, this.m);
        this.by = Math.max(this.bw / 32 - 1, 0);
        this.bz = Math.min(32 + (this.bx - this.bw) / 32, this.bv - this.by);
        final int[] array = this.bi[0];
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.k) {
                final int f = this.f(y);
                final int n4 = this.f(y + this.a4) + 1;
                final int n5 = x * this.m / this.ay;
                final int n6 = (x + this.a5) * this.m / this.ay + 1;
                this.i = 0;
                this.j = (n4 - f) * (n6 - n5);
                int n7 = 0;
                for (int j = f; j < n4; ++j) {
                    this.a(j, 0);
                    for (int k = n5; k < n6; ++k) {
                        n7 += (0x1 & array[k / 32] >>> 31 - k % 32);
                    }
                }
                this.i = (this.j - n7) * 255;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (this.a0 == 0) {
            if (this.a9) {
                for (int n8 = 0; n8 < this.a4 && !this.b1; ++n8) {
                    try {
                        final int f2 = this.f(y + n8);
                        if (f2 != n) {
                            int n9 = 0;
                            this.a(f2, 0);
                            for (int l = x; l < n3; ++l) {
                                final int n10 = l * this.m / this.ay;
                                if (n10 != n2) {
                                    this.a6 = (byte)(0x1 & array[n10 / 32] >>> 31 - n10 % 32);
                                    n2 = n10;
                                }
                                this.b3[n9++] = this.a6;
                            }
                            this.a(n8, true);
                        }
                        else {
                            this.a(n8, false);
                        }
                        n = f2;
                    }
                    catch (Exception ex4) {}
                    if (this.b2) {
                        if (this.cj != null) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n8 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                        }
                    }
                    else if (this.cj != null && !this.c2) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n8 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis2;
                        }
                    }
                }
            }
            else if (this.ba) {
                for (int n11 = 0; n11 < this.a4 && !this.b1; ++n11) {
                    try {
                        int n12 = 0;
                        this.a(y + n11, 0);
                        for (int n13 = x; n13 < n3; ++n13) {
                            this.b3[n12++] = (byte)(0x1 & array[n13 / 32] >>> 31 - n13 % 32);
                        }
                        this.a(n11, true);
                        if (!ji.util.d.b() && this.cj != null && !this.c2) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            if (currentTimeMillis3 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n11 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis3;
                            }
                        }
                    }
                    catch (Exception ex5) {}
                }
            }
            else {
                for (int n14 = 0; n14 < this.a4 && !this.b1; ++n14) {
                    try {
                        int n15 = 0;
                        this.a(this.f(y + n14), 0);
                        for (int n16 = x; n16 < n3; ++n16) {
                            final int n17 = n16 * this.m / this.ay;
                            this.b3[n15++] = (byte)(0x1 & array[n17 / 32] >>> 31 - n17 % 32);
                        }
                        this.a(n14, true);
                        if (this.cj != null && !this.c2) {
                            final long currentTimeMillis4 = System.currentTimeMillis();
                            if (currentTimeMillis4 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n14 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis4;
                            }
                        }
                    }
                    catch (Exception ex6) {}
                }
            }
        }
        else {
            final int n18 = this.a7 / 2;
            final int[] array2 = this.as.clone();
            final int[] array3 = this.at.clone();
            final int n19 = 64;
            final int[] array4 = new int[32];
            final int[][] array5 = new int[n19][n19];
            final int[][] array6 = new int[n19][n19];
            try {
                try {
                    final int n20 = y + this.a4;
                    for (int n21 = 0; n21 < this.a4; ++n21) {
                        final int n22 = y + n21;
                        final int n23 = n22 * this.n / this.az;
                        final int max = Math.max(Math.min(n23, this.do), 0);
                        int n24 = Math.min(n23 + this.dm + 1, this.do);
                        if (n24 == max) {
                            n24 = Math.min(max + 1, this.do);
                        }
                        array2[n22] = max;
                        array3[n22] = n24;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                int n25 = 1;
                for (int n26 = 0; n26 < n19; ++n26) {
                    for (int n27 = 0; n27 < n19; ++n27) {
                        array6[n26][n27] = 0;
                    }
                }
                final int max2 = Math.max(1, Math.min(this.a7, n19) / 2);
                final double n28 = 1.0;
                final int n29 = Math.min(this.a7, n19) + 1;
                final double n30 = 0.1;
                double n31 = n28;
                final double n32 = (n28 - n30) / (max2 + 1);
                final double n33 = (n28 - n30) / (n29 + 1);
                for (int n34 = 0; n34 <= n29; ++n34) {
                    double n35 = max2;
                    for (int n36 = 0; n36 <= n29; ++n36) {
                        try {
                            array6[n34][n36] = (int)Math.round(n31 * n35 * 1000.0);
                        }
                        catch (Exception ex7) {}
                        n35 -= n33;
                    }
                    n31 -= n33;
                }
                for (int n37 = 0; n37 < n19; ++n37) {
                    for (int n38 = 0; n38 < n19; ++n38) {
                        array5[n37][n38] = (int)(255.0 * array6[n37][n38] / 1000.0);
                    }
                }
                for (int length = array4.length, n39 = 0; n39 < length; ++n39) {
                    array4[n39] = n25;
                    n25 = (n25 << 1 | 0x1);
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            final int m = this.m;
            final int min = Math.min(this.bi.length - 1, n19 - 1);
            final int n40 = this.a7 + 1;
            final int min2 = Math.min(Math.min(this.a7, n19), 31);
            boolean a = false;
            for (int n41 = 0; n41 < this.a4 && !this.b1; ++n41) {
                final int n42 = y + n41;
                final int n43 = array2[n42];
                final int min3 = Math.min(array3[n42] - n43, min);
                final int n44 = n43 + min3;
                int n45 = 0;
                int n46 = n43;
                while (n46 < n44) {
                    a = this.a(n46, n45);
                    if (!a) {
                        if (ji.util.i.c(85)) {
                            ji.io.h.d(this.bf, "decompressLine failed");
                            break;
                        }
                        break;
                    }
                    else {
                        n45 += this.q;
                        ++n46;
                    }
                }
                if (!a) {
                    break;
                }
                int n47 = 0;
                for (int n48 = x; n48 < n3; ++n48) {
                    final int n49 = n48 * this.m / this.ay;
                    int n50 = 0;
                    int n51 = 0;
                    for (int n52 = 0; n52 < min3; ++n52) {
                        int n53 = 0;
                        int n54 = n49 / 32;
                        int min4 = 31 - n49 % 32;
                        int n55 = min2;
                        int n56 = this.bi[n52][n54] & array4[min4];
                        while (n55 > 0) {
                            if (min4 < 0) {
                                ++n54;
                                final int n57 = this.bi[n52][n54] >>> 31 - n55;
                                min4 = Math.min(n55, 31);
                                n56 = (n57 & array4[min4]);
                            }
                            if (n54 >= m) {
                                break;
                            }
                            if ((n56 >>> min4 & 0x1) > 0) {
                                n50 += array5[n52][n53];
                            }
                            n51 += array6[n52][n53];
                            ++n53;
                            --min4;
                            --n55;
                        }
                    }
                    if (n51 > 0) {
                        this.b3[n47] = (byte)Math.min(n50 / (n51 / 1000), 255);
                    }
                    ++n47;
                }
                this.a(n41, true);
                if (this.cj != null && !this.c2 && !this.b9) {
                    final long currentTimeMillis5 = System.currentTimeMillis();
                    if (currentTimeMillis5 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n41 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis5;
                    }
                }
            }
        }
        if (this.dk > 0) {
            if (ji.util.i.c(85)) {
                ji.io.h.d(this.bf, String.valueOf(String.valueOf(new StringBuffer("renderMonoPartial2: Color length exceeded line length line on ").append(this.dk).append(" lines"))));
            }
            this.dk = 0;
        }
        this.bn = null;
        this.bo = null;
        this.bp = null;
    }
    
    private void c(final Rectangle rectangle) throws Exception {
        if (this.as == null || this.at == null) {
            this.w();
        }
        this.q = 1;
        this.r = 1;
        int n = -1;
        int n2 = -1;
        final int o = this.o;
        if (this.b3 != null && this.b3.length < rectangle.width) {
            this.b3 = null;
        }
        if (this.b3 == null) {
            this.b3 = new byte[rectangle.width];
        }
        if (this.b4 != null && this.b4.length < rectangle.width) {
            this.b4 = null;
        }
        if (this.b4 == null) {
            this.b4 = new byte[rectangle.width];
        }
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = 0;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        this.bn = this.l.ai();
        if (this.bn != null) {
            this.bq = this.bn.size();
        }
        this.br = 999999;
        boolean b = false;
        this.bv = 1 + this.m / 32;
        final double n4 = this.n / this.az;
        final int n5 = (int)(n4 + 0.5);
        final int n6 = (int)Math.round((n4 + 1.0) / 2.0);
        final int n7 = this.n - 1;
        final int n8 = (int)(this.m / this.ay + 0.5) / 2;
        final int n9 = this.m - 1;
        this.bu = n5 + 2;
        this.e(this.bv);
        if (this.bi == null) {
            b = true;
        }
        else if (this.bi[0].length < this.bv) {
            b = true;
        }
        else if (this.bi.length < this.bu) {
            b = true;
        }
        if (b) {
            if (this.bm != null && this.bm.length < this.bv) {
                this.bm = null;
            }
            if (this.bm == null) {
                this.bm = new int[this.bv];
            }
            if (this.a0 == 0) {
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[1][this.bv];
                }
            }
            else {
                if (this.bi != null && this.bi.length < this.bu) {
                    this.bi = null;
                }
                if (this.bi != null && this.bi[0].length < this.bv) {
                    this.bi = null;
                }
                if (this.bi == null) {
                    this.bi = new int[this.bu][this.bv];
                }
            }
        }
        this.bw = Math.max(this.m * x / this.ay, 0);
        this.bx = Math.min(this.m * n3 / this.ay, this.m);
        this.by = Math.max(this.bw / 32 - 1, 0);
        this.bz = Math.min(32 + (this.bx - this.bw) / 32, this.bv - this.by);
        final int[] array = this.bi[0];
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.k) {
                final int f = this.f(y);
                final int n10 = this.f(y + this.a4) + 1;
                final int n11 = x * this.m / this.ay;
                final int n12 = (x + this.a5) * this.m / this.ay + 1;
                this.i = 0;
                this.j = (n10 - f) * (n12 - n11);
                int n13 = 0;
                for (int j = f; j < n10; ++j) {
                    this.a(j, 0);
                    for (int k = n11; k < n12; ++k) {
                        n13 += (0x1 & array[k / 32] >>> 31 - k % 32);
                    }
                }
                this.i = (this.j - n13) * 255;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (this.a0 == 0) {
            if (this.a9) {
                for (int n14 = 0; n14 < this.a4 && !this.b1; ++n14) {
                    try {
                        final int f2 = this.f(y + n14);
                        if (f2 != n) {
                            int n15 = 0;
                            this.a(f2, 0);
                            for (int l = x; l < n3; ++l) {
                                final int n16 = l * this.m / this.ay;
                                if (n16 != n2) {
                                    this.a6 = (byte)(0x1 & array[n16 / 32] >>> 31 - n16 % 32);
                                    n2 = n16;
                                }
                                this.b3[n15++] = this.a6;
                            }
                            this.a(n14, true);
                        }
                        else {
                            this.a(n14, false);
                        }
                        n = f2;
                    }
                    catch (Exception ex4) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n14 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis2;
                        }
                    }
                }
            }
            else {
                for (int n17 = 0; n17 < this.a4 && !this.b1; ++n17) {
                    try {
                        int n18 = 0;
                        this.a(this.f(y + n17), 0);
                        for (int n19 = x; n19 < n3; ++n19) {
                            final int n20 = n19 * this.m / this.ay;
                            this.b3[n18++] = (byte)(0x1 & array[n20 / 32] >>> 31 - n20 % 32);
                        }
                        this.a(n17, true);
                        if (this.cj != null && !this.c2) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            if (currentTimeMillis3 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n17 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis3;
                            }
                        }
                    }
                    catch (Exception ex5) {}
                }
            }
        }
        else {
            final int n21 = this.a7 / 2;
            final int[] array2 = this.as.clone();
            final int[] array3 = this.at.clone();
            final int[] array4 = new int[32];
            try {
                try {
                    final int n22 = y + this.a4;
                    for (int n23 = 0; n23 < this.a4; ++n23) {
                        final int n24 = y + n23;
                        final int n25 = n24 * this.n / this.az;
                        final int max = Math.max(Math.min(n25 - n6, n7), 0);
                        int n26 = Math.min(n25 + n6 + 1, n7);
                        if (n26 == max) {
                            n26 = Math.min(max + 1, n7);
                        }
                        array2[n24] = max;
                        array3[n24] = n26;
                    }
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                int n27 = 1;
                for (int n28 = 0; n28 < 32; ++n28) {
                    array4[n28] = n27;
                    n27 = (n27 << 1 | 0x1);
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            final int m = this.m;
            final int n29 = this.bi.length - 1;
            final int n30 = this.a7 + 1;
            for (int n31 = 0; n31 < this.a4 && !this.b1; ++n31) {
                final int n32 = y + n31;
                final int n33 = array2[n32];
                final int n34 = array3[n32];
                final int n35 = n34 - n33;
                int n36 = 0;
                for (int n37 = n33; n37 < n34; n37 += this.q) {
                    this.a(n37, n36);
                    n36 += this.q;
                }
                int n38 = 0;
                for (int n39 = x; n39 < n3; ++n39) {
                    final int max2 = Math.max(n39 * this.m / this.ay - n21, 0);
                    int n40 = max2 / 32;
                    int n41 = 31 - max2 % 32;
                    int n42 = 0;
                    int a7 = this.a7;
                    int n43 = 0;
                    Label_2177: {
                        try {
                            while (n41 <= a7) {
                                for (int n44 = 0; n44 < n35; n44 += this.q) {
                                    final int n45 = this.bi[Math.min(n44, n29)][n40];
                                    if (n45 != 0) {
                                        final int n46 = n45 & array4[n41];
                                        if (n41 < 16) {
                                            n43 += this.p[n46];
                                        }
                                        else {
                                            n43 += this.p[n46 & 0xFFFF];
                                            n43 += this.p[n46 >>> 16];
                                        }
                                    }
                                    n42 += n41 + 1;
                                    if (n43 > 0 && this.bt == 3) {
                                        n43 = 1;
                                        n42 = 1;
                                        break;
                                    }
                                }
                                a7 -= n41;
                                n41 = 31;
                                if (++n40 >= m) {
                                    break;
                                }
                                if (n43 > 0 && this.bt == 3) {
                                    n43 = 1;
                                    n42 = 1;
                                    break;
                                }
                            }
                            if (a7 < n41 && n40 < m) {
                                Block_90: {
                                    for (int n47 = 0; n47 < n35; n47 += this.q) {
                                        final int n48 = this.bi[Math.min(n47, n29)][n40];
                                        if (n48 != 0) {
                                            final int n49 = n48 >>> n41 - a7 & array4[a7];
                                            if (n41 < 16) {
                                                n43 += this.p[n49];
                                            }
                                            else {
                                                n43 += this.p[n49 & 0xFFFF];
                                                n43 += this.p[n49 >>> 16];
                                            }
                                        }
                                        n42 += a7 + 1;
                                        if (n43 > 0 && this.bt == 3) {
                                            break Block_90;
                                        }
                                    }
                                    break Label_2177;
                                }
                                n43 = 1;
                                n42 = 1;
                            }
                        }
                        catch (Exception ex6) {}
                    }
                    if (n42 > 0) {
                        this.b3[n38] = (byte)Math.min(n43 * 255 / n42, 255);
                    }
                    ++n38;
                }
                this.a(n31, true);
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis4 = System.currentTimeMillis();
                    if (currentTimeMillis4 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n31 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis4;
                    }
                }
            }
        }
        if (this.dk > 0) {
            if (ji.util.i.c(85)) {
                ji.io.h.d(this.bf, String.valueOf(String.valueOf(new StringBuffer("renderMonoFull: Color length exceeded line length line on ").append(this.dk).append(" lines"))));
            }
            this.dk = 0;
        }
        this.bn = null;
        this.bo = null;
        this.bp = null;
    }
    
    private void d(final Rectangle rectangle) throws Exception {
        int n = -1;
        int n2 = -1;
        final int o = this.o;
        this.bs = 0;
        if (this.b3 != null && this.b3.length < rectangle.width) {
            this.b3 = null;
        }
        if (this.b3 == null) {
            this.b3 = new byte[rectangle.width];
        }
        if (this.b4 != null && this.b4.length < rectangle.width) {
            this.b4 = null;
        }
        if (this.b4 == null) {
            this.b4 = new byte[rectangle.width];
        }
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = 0;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        this.bn = this.l.ai();
        if (this.bn != null) {
            this.bq = this.bn.size();
        }
        this.br = 999999;
        boolean b = false;
        this.bu = 2 * this.q + 2 * this.a8;
        this.e(this.bv = 1 + this.m / 32);
        if (this.bi == null) {
            b = true;
        }
        else if (this.bi[0].length < this.bv) {
            b = true;
        }
        else if (this.bi.length < this.bu) {
            b = true;
        }
        if (b) {
            if (this.bm != null && this.bm.length < this.bv) {
                this.bm = null;
            }
            if (this.bm == null) {
                this.bm = new int[this.bv];
            }
            if (this.bi != null && this.bi[0].length < this.bv) {
                this.bi = null;
            }
            if (this.bi == null) {
                this.bi = new int[1][this.bv];
            }
            if (this.bl != null && this.bl.length < this.bv * 4) {
                this.bl = null;
            }
            if (this.bl == null) {
                this.bl = new byte[this.bv * 4];
            }
            if (this.bj != null && this.bj.length < this.bv * 4) {
                this.bj = null;
            }
            if (this.bj == null) {
                this.bj = new byte[this.bv * 4];
            }
            if (this.bk != null && this.bk.length < this.bv * 4) {
                this.bk = null;
            }
            if (this.bk == null) {
                this.bk = new byte[this.bv * 4];
            }
        }
        this.bw = Math.max(this.m * x / this.ay, 0);
        this.bx = Math.min(this.m * n3 / this.ay, this.m);
        this.by = Math.max(this.bw / 32 - 1, 0);
        this.bz = Math.min(32 + (this.bx - this.bw) / 32, this.bv - this.by);
        final int[] array = this.bi[0];
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.k) {
                final int f = this.f(y);
                final int n4 = this.f(y + this.a4) + 1;
                final int n5 = x * this.m / this.ay;
                final int n6 = (x + this.a5) * this.m / this.ay + 1;
                this.i = 0;
                this.j = (n4 - f) * (n6 - n5);
                int n7 = 0;
                for (int j = f; j < n4; ++j) {
                    this.a(j, 0);
                    for (int k = n5; k < n6; ++k) {
                        n7 += (0x1 & array[k / 32] >>> 31 - k % 32);
                    }
                }
                this.i = (this.j - n7) * 255;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (this.m == rectangle.width && this.a1.ac == this.a1.ad) {
            final int n8 = this.bj.length - 1;
            this.by = Math.max(this.bw / 8 - 1, 0);
            this.bz = Math.min(8 + (this.bx - this.bw) / 8, this.bv * 4 - this.by);
            for (int n9 = 0; n9 < this.bd && !this.b1; ++n9) {
                try {
                    int n10;
                    if (this.cl) {
                        n10 = (this.bd - n9 - 1) * this.b0;
                    }
                    else {
                        n10 = n9 * this.b0;
                    }
                    this.c(n9 + rectangle.y, 0);
                    if (this.cm) {
                        int n11 = 7;
                        int n12 = 0;
                        byte b2 = this.bj[n12];
                        byte b3 = 0;
                        for (int l = 0; l < this.bc; ++l) {
                            if ((b2 >> n11 & 0x1) > 0) {
                                b3 |= (byte)(1 << 7 - n11);
                            }
                            if (--n11 < 0) {
                                this.bk[n8 - n12 - 1] = b3;
                                n11 = 7;
                                ++n12;
                                b2 = this.bj[n12];
                                b3 = 0;
                            }
                        }
                        if (!this.dg) {
                            System.arraycopy(this.bk, 0, this.ca, n10, this.b0);
                        }
                    }
                    else if (!this.dg) {
                        System.arraycopy(this.bj, 0, this.ca, n10, this.b0);
                    }
                }
                catch (Exception ex2) {
                    if (ji.util.d.cy()) {
                        ex2.printStackTrace();
                    }
                }
            }
        }
        else if (this.a9) {
            for (int n13 = 0; n13 < this.a4 && !this.b1; ++n13) {
                try {
                    final int f2 = this.f(y + n13);
                    if (f2 != n) {
                        int n14 = 0;
                        this.a(f2, 0);
                        for (int n15 = x; n15 < n3; ++n15) {
                            final int n16 = n15 * this.m / this.ay;
                            if (n16 != n2) {
                                this.a6 = (byte)(0x1 & array[n16 / 32] >>> 31 - n16 % 32);
                                n2 = n16;
                            }
                            this.b3[n14++] = this.a6;
                        }
                        this.b(n13, true);
                    }
                    else {
                        this.b(n13, false);
                    }
                    n = f2;
                }
                catch (Exception ex3) {}
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n13 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis2;
                    }
                }
            }
        }
        else {
            for (int n17 = 0; n17 < this.a4 && !this.b1; ++n17) {
                try {
                    int n18 = 0;
                    this.a(this.f(y + n17), 0);
                    for (int n19 = x; n19 < n3; ++n19) {
                        final int n20 = n19 * this.m / this.ay;
                        this.b3[n18++] = (byte)(0x1 & array[n20 / 32] >>> 31 - n20 % 32);
                    }
                    this.b(n17, true);
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis3 = System.currentTimeMillis();
                        if (currentTimeMillis3 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n17 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis3;
                        }
                    }
                }
                catch (Exception ex4) {}
            }
        }
        if (this.dk > 0) {
            if (ji.util.i.c(85)) {
                ji.io.h.d(this.bf, String.valueOf(String.valueOf(new StringBuffer("renderMonoBitStream: Color length exceeded line length line on ").append(this.dk).append(" lines"))));
            }
            this.dk = 0;
        }
        this.bn = null;
        this.bo = null;
        this.bp = null;
    }
    
    private final void e(final int n) {
        this.cn = ji.util.d.dy();
        if (this.cn && this.cp) {
            this.bo = this.l.aj();
            this.bp = this.l.ak();
            if (this.bo != null) {
                if (this.bo.size() == 0) {
                    this.bp = new boolean[this.m()];
                    for (int i = 0; i < this.m(); ++i) {
                        this.bo.addElement(new int[n]);
                    }
                }
            }
            else {
                this.cn = false;
            }
        }
        else {
            this.cn = false;
        }
    }
    
    private final boolean a(int d, final int n) {
        d = this.d(d);
        if (this.co) {
            return this.b(d, n);
        }
        try {
            final int n2 = d;
            if (n2 < this.bq) {
                int n3 = 0;
                short n4 = 0;
                int n5 = 1;
                final int[] array = this.bi[n];
                if (this.cn) {
                    try {
                        if (this.bp[d]) {
                            final int[] array2 = this.bo.elementAt(d);
                            if (array2 != null) {
                                System.arraycopy(array2, 0, array, 0, array.length);
                                return true;
                            }
                        }
                    }
                    catch (Exception ex3) {}
                }
                System.arraycopy(this.bm, 0, array, this.by, this.bz);
                this.cs = (short[])this.bn.elementAt(n2);
                this.cu = this.cs.length;
                if (this.bw == 0 && this.bx == this.m) {
                    while (n4 < this.bx && n3 < this.cu) {
                        if (n5 == 0) {
                            final short n6 = (short)(n4 + this.cs[n3]);
                            for (short n7 = n4; n7 < n6; ++n7) {
                                final int[] array3 = array;
                                final short n8 = (short)(n7 / 32);
                                array3[n8] |= 1 << 31 - n7 % 32;
                            }
                            n4 = n6;
                        }
                        else {
                            n4 += this.cs[n3];
                        }
                        n5 = 1 - n5;
                        if (++n3 > this.br) {
                            n3 = this.br;
                        }
                    }
                    if (this.cn) {
                        try {
                            final int[] array4 = new int[array.length];
                            System.arraycopy(array, 0, array4, 0, array.length);
                            this.bo.setElementAt(array4, d);
                            this.bp[d] = true;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else {
                    while (n4 <= this.bx && n3 < this.cu) {
                        final short n9 = this.cs[n3];
                        if (n9 > 0) {
                            if (n5 == 0) {
                                final short n10 = (short)(n4 + n9);
                                if (n10 >= this.bw) {
                                    for (short n11 = n4; n11 < n10; ++n11) {
                                        final int[] array5 = array;
                                        final short n12 = (short)(n11 / 32);
                                        array5[n12] |= 1 << 31 - n11 % 32;
                                    }
                                }
                                n4 = n10;
                            }
                            else {
                                n4 += n9;
                            }
                        }
                        n5 = 1 - n5;
                        if (++n3 > this.br) {
                            n3 = this.br;
                        }
                    }
                }
                return true;
            }
            return false;
        }
        catch (Exception ex2) {
            d.a(ex2);
            return false;
        }
    }
    
    private final boolean b(final int n, final int n2) {
        try {
            final int[] array = this.bi[n2];
            if (this.cn) {
                try {
                    if (this.bp[n]) {
                        final int[] array2 = this.bo.elementAt(n);
                        if (array2 != null) {
                            System.arraycopy(array2, 0, array, 0, array.length);
                            return true;
                        }
                    }
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
            }
            System.arraycopy(this.bm, 0, array, this.by, this.bz);
            if (this.ct == null) {
                this.ct = new byte[this.l.u()];
            }
            this.l.b(n, this.ct);
            this.cu = this.ct.length;
            if (this.bw == 0 && this.bx == this.m) {
                this.a(array);
                if (this.cn) {
                    try {
                        final int[] array3 = new int[array.length];
                        System.arraycopy(array, 0, array3, 0, array.length);
                        this.bo.setElementAt(array3, n);
                        this.bp[n] = true;
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
            }
            else {
                this.a(array);
            }
            return true;
        }
        catch (Exception ex3) {
            ji.util.d.a(ex3);
            return false;
        }
    }
    
    private void a(final int[] array) {
        int n = 1;
        for (int min = 0, n2 = 0; n2 < this.bx && min < this.cu; min = Math.min(min + 1, this.br)) {
            final int n3 = this.ct[min] & 0xFF;
            if (n3 > 0) {
                int bx = n2 + n3;
                if (bx > this.bx) {
                    ++this.dk;
                    bx = this.bx;
                }
                if (n == 0 && bx >= this.bw) {
                    for (int i = n2; i < bx; ++i) {
                        final int n4 = i / 32;
                        array[n4] |= 1 << 31 - i % 32;
                    }
                }
                n2 = bx;
            }
            n = 1 - n;
        }
    }
    
    private final void c(final int n, final int n2) {
        if (this.co) {
            this.d(n, n2);
        }
        try {
            final int n3 = n + 1;
            if (n3 < this.bq) {
                int n4 = 0;
                short n5 = 0;
                int n6 = 1;
                final int[] array = this.bi[n2];
                System.arraycopy(this.bl, 0, this.bj, this.by, this.bz);
                final short[] array2 = this.bn.elementAt(n3);
                final int length = array2.length;
                if (this.bw == 0 && this.bx == this.m) {
                    while (n5 < this.bx && n4 < length) {
                        if (n6 == 0) {
                            final short n7 = (short)(n5 + array2[n4]);
                            for (short n8 = n5; n8 < n7; ++n8) {
                                final byte[] bj = this.bj;
                                final short n9 = (short)(n8 / 8);
                                bj[n9] |= (byte)(1 << 7 - n8 % 8);
                            }
                            n5 = n7;
                        }
                        else {
                            n5 += array2[n4];
                        }
                        n6 = 1 - n6;
                        n4 = Math.min(n4 + 1, this.br);
                    }
                }
                else {
                    while (n5 <= this.bx && n4 < length) {
                        final short n10 = array2[n4];
                        if (n10 > 0) {
                            if (n6 == 0) {
                                final short n11 = (short)(n5 + n10);
                                if (n11 >= this.bw) {
                                    for (short n12 = n5; n12 < n11; ++n12) {
                                        final byte[] bj2 = this.bj;
                                        final short n13 = (short)(n12 / 8);
                                        bj2[n13] |= (byte)(1 << 7 - n12 % 8);
                                    }
                                }
                                n5 = n11;
                            }
                            else {
                                n5 += n10;
                            }
                        }
                        n6 = 1 - n6;
                        n4 = Math.min(n4 + 1, this.br);
                    }
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void d(final int n, final int n2) {
        try {
            int n3 = 0;
            int n4 = 0;
            int n5 = 1;
            final int[] array = this.bi[n2];
            if (this.cn) {
                try {
                    if (this.bp[n]) {
                        final int[] array2 = this.bo.elementAt(n);
                        if (array2 != null) {
                            System.arraycopy(array2, 0, array, 0, array.length);
                            return;
                        }
                    }
                }
                catch (Exception ex3) {}
            }
            System.arraycopy(this.bl, 0, this.bj, this.by, this.bz);
            if (this.ct == null) {
                this.ct = new byte[this.l.u()];
            }
            this.l.b(n, this.ct);
            this.cu = this.ct.length;
            if (this.bw == 0 && this.bx == this.m) {
                while (n4 < this.bx && n3 < this.cu) {
                    if (n5 == 0) {
                        final int n6 = n4 + (this.ct[n3] & 0xFF);
                        for (int i = n4; i < n6; ++i) {
                            final byte[] bj = this.bj;
                            final int n7 = i / 8;
                            bj[n7] |= (byte)(1 << 7 - i % 8);
                        }
                        n4 = n6;
                    }
                    else {
                        n4 += (this.ct[n3] & 0xFF);
                    }
                    n5 = 1 - n5;
                    n3 = Math.min(n3 + 1, this.br);
                }
                if (this.cn) {
                    try {
                        final int[] array3 = new int[array.length];
                        System.arraycopy(array, 0, array3, 0, array.length);
                        this.bo.setElementAt(array3, n);
                        this.bp[n] = true;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else {
                while (n4 <= this.bx && n3 < this.cu) {
                    final int n8 = this.ct[n3] & 0xFF;
                    if (n8 > 0) {
                        if (n5 == 0) {
                            final int n9 = n4 + n8;
                            if (n9 >= this.bw) {
                                for (int j = n4; j < n9; ++j) {
                                    final byte[] bj2 = this.bj;
                                    final int n10 = j / 8;
                                    bj2[n10] |= (byte)(1 << 7 - j % 8);
                                }
                            }
                            n4 = n9;
                        }
                        else {
                            n4 += n8;
                        }
                    }
                    n5 = 1 - n5;
                    n3 = Math.min(n3 + 1, this.br);
                }
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
    }
    
    private void e(final Rectangle rectangle) throws Exception {
        int n = -1;
        int n2 = -1;
        int f = 0;
        final int o = this.o;
        int n3 = 0;
        this.b6 = new int[this.bc * 2];
        this.b7 = new int[this.bc * 2];
        if (this.dg) {
            this.b8 = new int[this.bc * 2];
            final int rgb = Color.white.getRGB();
            for (int i = 0; i < this.b8.length; ++i) {
                this.b8[i] = rgb;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n4 = x + this.a5;
        final int[] array = new int[this.m * 2];
        long currentTimeMillis = System.currentTimeMillis();
        this.c4 = 0L;
        this.c5 = 0L;
        this.c6 = 0L;
        this.c7 = 0L;
        if (this.a9) {
            for (int n5 = 0; n5 < this.a4 && !this.b1; ++n5) {
                try {
                    f = this.f(y + n5);
                    if (f != n) {
                        int n6 = 0;
                        final int n7 = f * this.m;
                        this.l.c(this.d(f));
                        if (!this.l.a(array, this.m)) {
                            break;
                        }
                        final int n8 = 0;
                        try {
                            for (int j = x; j < n4; ++j) {
                                final int n9 = j * this.m / this.ay;
                                if (n9 != n2) {
                                    n3 = array[n8 + n9];
                                    n2 = n9;
                                }
                                if (this.cd) {
                                    int max = (n3 & 0xFF0000) >> 16;
                                    int max2 = (n3 & 0xFF00) >> 8;
                                    int max3 = n3 & 0xFF;
                                    if (this.dd) {
                                        max = (max3 = (max2 = (2 * max + 2 * max2 + max3) / 5));
                                    }
                                    if (this.c1) {
                                        this.c4 += max;
                                        this.c5 += max2;
                                        this.c6 += max3;
                                        ++this.c7;
                                        ++n6;
                                    }
                                    else {
                                        if (this.b != this.h) {
                                            max = Math.max(Math.min(this.c3 + this.c * (max - this.c3) / this.f, 255), 0);
                                            max2 = Math.max(Math.min(this.c3 + this.c * (max2 - this.c3) / this.f, 255), 0);
                                            max3 = Math.max(Math.min(this.c3 + this.c * (max3 - this.c3) / this.f, 255), 0);
                                        }
                                        this.b6[n6++] = ((n3 & 0xFF000000) | Math.max(Math.min(this.cx[255 - max] + this.c0, 255), 0) << 16 | Math.max(Math.min(this.cy[255 - max2] + this.c0, 255), 0) << 8 | Math.max(Math.min(this.cz[255 - max3] + this.c0, 255), 0));
                                    }
                                }
                                else {
                                    int max4 = (n3 & 0xFF0000) >> 16;
                                    int max5 = (n3 & 0xFF00) >> 8;
                                    int max6 = n3 & 0xFF;
                                    if (this.dd) {
                                        max4 = (max6 = (max5 = (2 * max4 + 2 * max5 + max6) / 5));
                                    }
                                    if (this.c1) {
                                        this.c4 += max4;
                                        this.c5 += max5;
                                        this.c6 += max6;
                                        ++this.c7;
                                        ++n6;
                                    }
                                    else {
                                        if (this.b != this.h) {
                                            max4 = Math.max(Math.min(this.c3 + this.c * (max4 - this.c3) / this.f, 255), 0);
                                            max5 = Math.max(Math.min(this.c3 + this.c * (max5 - this.c3) / this.f, 255), 0);
                                            max6 = Math.max(Math.min(this.c3 + this.c * (max6 - this.c3) / this.f, 255), 0);
                                        }
                                        this.b6[n6++] = ((n3 & 0xFF000000) | Math.max(Math.min(this.cx[max4] + this.c0, 255), 0) << 16 | Math.max(Math.min(this.cy[max5] + this.c0, 255), 0) << 8 | Math.max(Math.min(this.cz[max6] + this.c0, 255), 0));
                                    }
                                }
                            }
                            if (!this.c1) {
                                this.a(n5, true, null);
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else if (!this.c1) {
                        this.a(n5, false, null);
                    }
                }
                catch (Exception ex2) {
                    if (ji.util.d.cy()) {
                        ex2.printStackTrace();
                    }
                }
                n = f;
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n5 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis2;
                    }
                }
            }
        }
        else if (this.ba) {
            boolean b = false;
            if (!this.cd && !this.dd && !this.c1 && this.c0 == 0 && this.b == this.h && this.al != 90 && this.al != 270) {
                b = true;
            }
            if (b) {
                for (int n10 = 0; n10 < this.a4 && !this.b1; ++n10) {
                    try {
                        f = y + n10;
                        if (f != n) {
                            final int n11 = f * this.m;
                            this.l.c(this.d(f));
                            if (!this.l.a(array, this.m)) {
                                break;
                            }
                            try {
                                this.a(n10, true, array);
                            }
                            catch (Exception ex3) {
                                ex3.printStackTrace();
                            }
                        }
                        else {
                            this.a(n10, false, array);
                        }
                    }
                    catch (Exception ex4) {
                        if (ji.util.d.cy()) {
                            ex4.printStackTrace();
                        }
                    }
                    n = f;
                    if (!ji.util.d.b() && this.cj != null && !this.c2) {
                        final long currentTimeMillis3 = System.currentTimeMillis();
                        if (currentTimeMillis3 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n10 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis3;
                        }
                    }
                }
            }
            else {
                for (int n12 = 0; n12 < this.a4 && !this.b1; ++n12) {
                    try {
                        f = y + n12;
                        if (f != n) {
                            int n13 = 0;
                            final int n14 = f * this.m;
                            this.l.c(this.d(f));
                            if (!this.l.a(array, this.m)) {
                                break;
                            }
                            final int n15 = 0;
                            try {
                                for (int k = x; k < n4; ++k) {
                                    final int n16 = k;
                                    if (n16 != n2) {
                                        n3 = array[n15 + n16];
                                        n2 = n16;
                                    }
                                    if (this.cd) {
                                        int max7 = (n3 & 0xFF0000) >> 16;
                                        int max8 = (n3 & 0xFF00) >> 8;
                                        int max9 = n3 & 0xFF;
                                        if (this.dd) {
                                            max7 = (max9 = (max8 = (2 * max7 + 2 * max8 + max9) / 5));
                                        }
                                        if (this.c1) {
                                            this.c4 += max7;
                                            this.c5 += max8;
                                            this.c6 += max9;
                                            ++this.c7;
                                            ++n13;
                                        }
                                        else {
                                            if (this.b != this.h) {
                                                max7 = Math.max(Math.min(this.c3 + this.c * (max7 - this.c3) / this.f, 255), 0);
                                                max8 = Math.max(Math.min(this.c3 + this.c * (max8 - this.c3) / this.f, 255), 0);
                                                max9 = Math.max(Math.min(this.c3 + this.c * (max9 - this.c3) / this.f, 255), 0);
                                            }
                                            int max10 = 255 - max7;
                                            int max11 = 255 - max8;
                                            int max12 = 255 - max9;
                                            if (this.c0 != 0) {
                                                max10 = Math.max(Math.min(this.cx[max10] + this.c0, 255), 0);
                                                max11 = Math.max(Math.min(this.cy[max11] + this.c0, 255), 0);
                                                max12 = Math.max(Math.min(this.cz[max12] + this.c0, 255), 0);
                                            }
                                            this.b6[n13++] = ((n3 & 0xFF000000) | max10 << 16 | max11 << 8 | max12);
                                        }
                                    }
                                    else {
                                        int n17 = (n3 & 0xFF0000) >> 16;
                                        int n18 = (n3 & 0xFF00) >> 8;
                                        int n19 = n3 & 0xFF;
                                        if (this.dd) {
                                            n17 = (n19 = (n18 = (2 * n17 + 2 * n18 + n19) / 5));
                                        }
                                        if (this.c1) {
                                            this.c4 += n17;
                                            this.c5 += n18;
                                            this.c6 += n19;
                                            ++this.c7;
                                            ++n13;
                                        }
                                        else {
                                            if (this.b != this.h) {
                                                n17 = Math.max(Math.min(this.c3 + this.c * (n17 - this.c3) / this.f, 255), 0);
                                                n18 = Math.max(Math.min(this.c3 + this.c * (n18 - this.c3) / this.f, 255), 0);
                                                n19 = Math.max(Math.min(this.c3 + this.c * (n19 - this.c3) / this.f, 255), 0);
                                            }
                                            if (this.c0 != 0) {
                                                n17 = Math.max(Math.min(this.cx[n17] + this.c0, 255), 0);
                                                n18 = Math.max(Math.min(this.cy[n18] + this.c0, 255), 0);
                                                n19 = Math.max(Math.min(this.cz[n19] + this.c0, 255), 0);
                                            }
                                            this.b6[n13++] = ((n3 & 0xFF000000) | n17 << 16 | n18 << 8 | n19);
                                        }
                                    }
                                }
                                if (!this.c1) {
                                    this.a(n12, true, null);
                                }
                            }
                            catch (Exception ex5) {
                                ex5.printStackTrace();
                            }
                        }
                        else if (!this.c1) {
                            this.a(n12, false, null);
                        }
                    }
                    catch (Exception ex6) {
                        if (ji.util.d.cy()) {
                            ex6.printStackTrace();
                        }
                    }
                    n = f;
                    if (!ji.util.d.b() && this.cj != null && !this.c2) {
                        final long currentTimeMillis4 = System.currentTimeMillis();
                        if (currentTimeMillis4 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n12 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis4;
                        }
                    }
                }
            }
        }
        else {
            boolean b2 = this.an && ji.util.d.cx() && this.dh;
            if (this.c1) {
                b2 = false;
            }
            if (b2) {
                final int[] array2 = this.as.clone();
                final int[] array3 = this.at.clone();
                final int[] array4 = new int[this.m];
                final int[] array5 = new int[this.m];
                final double n20 = this.m / this.ay;
                final int n21 = this.m - 1;
                for (int l = 0; l < this.m; ++l) {
                    final int n22 = (int)(l / n20);
                    final int n23 = l;
                    final int n24 = 1 + (int)((n22 + 1.7) * n20);
                    array4[l] = Math.max(n23, 0);
                    array5[l] = Math.min(n24, n21);
                }
                final int[][] array6 = new int[this.a8 + 1][this.m * 2];
                final int length = array6.length;
                boolean a = false;
                for (int n25 = 0; n25 < this.a4 && !this.b1; ++n25) {
                    try {
                        this.f(y + n25);
                        final int n26 = y + n25;
                        final int n27 = array2[n26];
                        final int n28 = array3[n26];
                        final int n29 = n28 - n27;
                        int n30 = 0;
                        for (int n31 = n27; n31 < n28; n31 += this.q) {
                            if (n30 < length) {
                                this.l.c(this.d(n31));
                                a = this.l.a(array6[n30], this.m);
                                if (!a) {
                                    break;
                                }
                                n30 += this.q;
                            }
                        }
                        if (!a) {
                            break;
                        }
                        int n32 = 0;
                        try {
                            for (int n33 = x; n33 < n4; ++n33) {
                                final int n34 = n33 * this.m / this.ay;
                                final int n35 = array4[n34];
                                final int n36 = array5[n34];
                                int n37 = 0;
                                int n38 = 0;
                                int n39 = 0;
                                int n40 = 0;
                                int n41 = 0;
                                int n42 = 0;
                                for (int n43 = n35; n43 < n36; n43 += this.r) {
                                    for (int n44 = 0; n44 < n29; n44 += this.q) {
                                        try {
                                            n38 = array6[n44][n43];
                                        }
                                        catch (Exception ex7) {}
                                        final int n45 = (n38 & 0xFF000000) >> 24;
                                        int max13 = (n38 & 0xFF0000) >> 16;
                                        int max14 = (n38 & 0xFF00) >> 8;
                                        int max15 = n38 & 0xFF;
                                        if (this.dd) {
                                            max13 = (max15 = (max14 = (2 * max13 + 2 * max14 + max15) / 5));
                                        }
                                        if (this.b != this.h) {
                                            max13 = Math.max(Math.min(this.c3 + this.c * (max13 - this.c3) / this.f, 255), 0);
                                            max14 = Math.max(Math.min(this.c3 + this.c * (max14 - this.c3) / this.f, 255), 0);
                                            max15 = Math.max(Math.min(this.c3 + this.c * (max15 - this.c3) / this.f, 255), 0);
                                        }
                                        if (this.cd) {
                                            max13 = 255 - max13;
                                            max14 = 255 - max14;
                                            max15 = 255 - max15;
                                        }
                                        final int max16 = Math.max(Math.min(this.cx[max13] + this.c0, 255), 0);
                                        final int max17 = Math.max(Math.min(this.cy[max14] + this.c0, 255), 0);
                                        final int max18 = Math.max(Math.min(this.cz[max15] + this.c0, 255), 0);
                                        n39 += n45;
                                        n40 += max16;
                                        n41 += max17;
                                        n42 += max18;
                                        ++n37;
                                    }
                                }
                                if (n37 > 0) {
                                    final int n46 = n39 / n37;
                                    final int n47 = n40 / n37;
                                    final int n48 = n41 / n37;
                                    final int n49 = n42 / n37;
                                    try {
                                        this.b6[n32] = (n46 << 24 | n47 << 16 | n48 << 8 | n49);
                                    }
                                    catch (Exception ex8) {}
                                }
                                ++n32;
                            }
                            this.a(n25, true, null);
                        }
                        catch (Exception ex9) {}
                    }
                    catch (Exception ex10) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis5 = System.currentTimeMillis();
                        if (currentTimeMillis5 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n25 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis5;
                        }
                    }
                }
            }
            else {
                for (int n50 = 0; n50 < this.a4 && !this.b1; ++n50) {
                    try {
                        final int f2 = this.f(y + n50);
                        final int n51 = f2 * this.m;
                        int n52 = 0;
                        this.l.c(this.d(f2));
                        if (!this.l.a(array, this.m)) {
                            break;
                        }
                        final int n53 = 0;
                        try {
                            for (int n54 = x; n54 < n4; ++n54) {
                                final int n55 = n54 * this.m / this.ay;
                                if (this.cd) {
                                    final int n56 = array[n53 + n55];
                                    int max19 = (n56 & 0xFF0000) >> 16;
                                    int max20 = (n56 & 0xFF00) >> 8;
                                    int max21 = n56 & 0xFF;
                                    if (this.dd) {
                                        max19 = (max21 = (max20 = (2 * max19 + 2 * max20 + max21) / 5));
                                    }
                                    if (this.c1) {
                                        this.c4 += max19;
                                        this.c5 += max20;
                                        this.c6 += max21;
                                        ++this.c7;
                                        ++n52;
                                    }
                                    else {
                                        if (this.b != this.h) {
                                            max19 = Math.max(Math.min(this.c3 + this.c * (max19 - this.c3) / this.f, 255), 0);
                                            max20 = Math.max(Math.min(this.c3 + this.c * (max20 - this.c3) / this.f, 255), 0);
                                            max21 = Math.max(Math.min(this.c3 + this.c * (max21 - this.c3) / this.f, 255), 0);
                                        }
                                        this.b6[n52++] = ((n56 & 0xFF000000) | Math.max(Math.min(this.cx[255 - max19] + this.c0, 255), 0) << 16 | Math.max(Math.min(this.cy[255 - max20] + this.c0, 255), 0) << 8 | Math.max(Math.min(this.cz[255 - max21] + this.c0, 255), 0));
                                    }
                                }
                                else {
                                    final int n57 = array[n53 + n55];
                                    int max22 = (n57 & 0xFF0000) >> 16;
                                    int max23 = (n57 & 0xFF00) >> 8;
                                    int max24 = n57 & 0xFF;
                                    this.c4 += max22;
                                    this.c5 += max23;
                                    this.c6 += max24;
                                    ++this.c7;
                                    if (this.c1) {
                                        this.c4 += max22;
                                        this.c5 += max23;
                                        this.c6 += max24;
                                        ++this.c7;
                                        ++n52;
                                    }
                                    else {
                                        if (this.b != this.h) {
                                            max22 = Math.max(Math.min(this.c3 + this.c * (max22 - this.c3) / this.f, 255), 0);
                                            max23 = Math.max(Math.min(this.c3 + this.c * (max23 - this.c3) / this.f, 255), 0);
                                            max24 = Math.max(Math.min(this.c3 + this.c * (max24 - this.c3) / this.f, 255), 0);
                                        }
                                        this.b6[n52++] = ((n57 & 0xFF000000) | Math.max(Math.min(this.cx[max22] + this.c0, 255), 0) << 16 | Math.max(Math.min(this.cy[max23] + this.c0, 255), 0) << 8 | Math.max(Math.min(this.cz[max24] + this.c0, 255), 0));
                                    }
                                }
                            }
                            if (!this.c1) {
                                this.a(n50, true, null);
                            }
                        }
                        catch (Exception ex11) {}
                    }
                    catch (Exception ex12) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis6 = System.currentTimeMillis();
                        if (currentTimeMillis6 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n50 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis6;
                        }
                    }
                }
            }
        }
        this.b6 = null;
        this.b3 = null;
        this.b8 = null;
    }
    
    private void f(final Rectangle rectangle) throws Exception {
        final int o = this.o;
        this.b6 = new int[this.bc];
        this.b7 = new int[this.bc];
        if (this.dg) {
            this.b8 = new int[this.bc * 2];
            final int rgb = Color.white.getRGB();
            for (int i = 0; i < this.b8.length; ++i) {
                this.b8[i] = rgb;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n = x + this.a5;
        System.currentTimeMillis();
        final FilteredImageSource filteredImageSource = new FilteredImageSource(this.l.w().getSource(), new CropImageFilter(x * this.m / this.ay, this.f(y), width * this.m / this.ay, height * this.n / this.az));
        int n2 = 2;
        if (this.an) {
            n2 = 4;
        }
        final Image scaledInstance = Toolkit.getDefaultToolkit().createImage(filteredImageSource).getScaledInstance(rectangle.width, rectangle.height, n2);
        final int[] array = new int[rectangle.width * rectangle.height];
        final c3 a = ji.util.d.a(scaledInstance, this.bf, this.c8);
        ji.util.d.a(this.c8, this.bf, scaledInstance, a, 0, 0, rectangle.width, rectangle.height, rectangle.width, array);
        ji.util.d.a(scaledInstance, a);
        if (this.an && !this.a9) {
            for (int n3 = 0; n3 < this.a4 && !this.b1; ++n3) {
                final int n4 = n3 * this.a5;
                for (int j = 0; j < this.a5; ++j) {
                    this.b6[j] = array[j + n4];
                }
                this.a(n3, true, null);
            }
        }
        else {
            for (int n5 = 0; n5 < this.a4 && !this.b1; ++n5) {
                final int n6 = n5 * this.a5;
                for (int k = 0; k < this.a5; ++k) {
                    this.b6[k] = array[k + n6];
                }
                this.a(n5, true, null);
            }
        }
        this.b6 = null;
        this.b3 = null;
        this.b8 = null;
    }
    
    private void g(final Rectangle rectangle) throws Exception {
        int n = -1;
        int n2 = -1;
        byte b = 0;
        final int o = this.o;
        this.b3 = new byte[rectangle.width];
        this.b4 = new byte[rectangle.width];
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = -1;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        final byte[] ag = this.l.ag();
        long currentTimeMillis = System.currentTimeMillis();
        this.c4 = 0L;
        this.c5 = 0L;
        this.c6 = 0L;
        this.c7 = 0L;
        if (!this.l.g()) {
            final byte[] array = new byte[this.m];
            if (this.a9) {
                for (int n4 = 0; n4 < this.a4 && !this.b1; ++n4) {
                    final int f = this.f(y + n4);
                    try {
                        if (f != n) {
                            this.l.a(f, array);
                            int n5 = 0;
                            final int n6 = f * this.m;
                            for (int j = x; j < n3; ++j) {
                                final int n7 = j * this.m / this.ay;
                                if (n7 != n2) {
                                    b = array[n7];
                                    n2 = n7;
                                }
                                if (this.c1) {
                                    this.c4 += (b & 0xFF);
                                    ++this.c7;
                                }
                                this.b3[n5++] = b;
                            }
                            if (!this.c1) {
                                this.a(n4, true);
                            }
                        }
                        else if (!this.c1) {
                            this.a(n4, false);
                        }
                    }
                    catch (Exception ex) {}
                    n = f;
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        if (currentTimeMillis2 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n4 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis2;
                        }
                    }
                }
            }
            else {
                int n8 = (this.an && ji.util.d.cx() && this.dh) ? 1 : 0;
                if (this.c1) {
                    n8 = 0;
                }
                if (n8 != 0) {
                    final int[] l = this.l.l();
                    if (l != null && !ji.util.d.a(l)) {
                        n8 = 0;
                    }
                }
                if (n8 != 0) {
                    final int[] array2 = this.as.clone();
                    final int[] array3 = this.at.clone();
                    final int[] array4 = new int[this.m];
                    final int[] array5 = new int[this.m];
                    final double n9 = this.m / this.ay;
                    final int n10 = this.m - 1;
                    for (int k = 0; k < this.m; ++k) {
                        final int n11 = (int)(k / n9);
                        final int n12 = k;
                        final int n13 = 1 + (int)((n11 + 1.7) * n9);
                        array4[k] = Math.max(n12, 0);
                        array5[k] = Math.min(n13, n10);
                    }
                    final byte[][] array6 = new byte[this.a8 + 1][this.m * 2];
                    final int length = array6.length;
                    for (int n14 = 0; n14 < this.a4 && !this.b1; ++n14) {
                        try {
                            this.f(y + n14);
                            final int n15 = y + n14;
                            final int n16 = array2[n15];
                            final int n17 = array3[n15];
                            final int n18 = n17 - n16;
                            int n19 = 0;
                            for (int n20 = n16; n20 < n17; n20 += this.q) {
                                if (n19 < length) {
                                    final int n21 = n20 * this.m;
                                    this.l.a(n20, array6[n19]);
                                    n19 += this.q;
                                }
                            }
                            int n22 = 0;
                            try {
                                for (int n23 = x; n23 < n3; ++n23) {
                                    final int n24 = n23 * this.m / this.ay;
                                    final int n25 = array4[n24];
                                    final int n26 = array5[n24];
                                    int n27 = 0;
                                    int n28 = 0;
                                    for (int n29 = n25; n29 < n26; n29 += this.r) {
                                        for (int n30 = 0; n30 < n18; n30 += this.q) {
                                            try {
                                                n28 += (array6[n30][n29] & 0xFF);
                                            }
                                            catch (Exception ex2) {}
                                            ++n27;
                                        }
                                    }
                                    if (n27 > 0) {
                                        this.b3[n22] = (byte)(n28 / n27);
                                    }
                                    ++n22;
                                }
                                this.a(n14, true);
                            }
                            catch (Exception ex3) {}
                        }
                        catch (Exception ex4) {}
                        if (this.cj != null && !this.c2) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            if (currentTimeMillis3 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n14 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis3;
                            }
                        }
                    }
                }
                else {
                    for (int n31 = 0; n31 < this.a4 && !this.b1; ++n31) {
                        final int f2 = this.f(y + n31);
                        final int n32 = f2 * this.m;
                        int n33 = 0;
                        try {
                            this.l.a(f2, array);
                            for (int n34 = x; n34 < n3; ++n34) {
                                final int n35 = n34 * this.m / this.ay;
                                this.b3[n33++] = array[n35];
                                if (this.c1) {
                                    this.c4 += (array[n35] & 0xFF);
                                    ++this.c7;
                                }
                            }
                            if (!this.c1) {
                                this.a(n31, true);
                            }
                        }
                        catch (Exception ex5) {}
                        if (this.cj != null && !this.c2) {
                            final long currentTimeMillis4 = System.currentTimeMillis();
                            if (currentTimeMillis4 - currentTimeMillis > 300) {
                                this.ck.a("".concat(String.valueOf(String.valueOf(100 * n31 / this.a4))));
                                if (!this.b9) {
                                    this.cj.a(this.ck);
                                }
                                currentTimeMillis = currentTimeMillis4;
                            }
                        }
                    }
                }
            }
        }
        else if (this.a9) {
            for (int n36 = 0; n36 < this.a4 && !this.b1; ++n36) {
                final int f3 = this.f(y + n36);
                try {
                    if (f3 != n) {
                        int n37 = 0;
                        final int n38 = f3 * this.m;
                        for (int n39 = x; n39 < n3; ++n39) {
                            final int n40 = n39 * this.m / this.ay;
                            if (n40 != n2) {
                                b = ag[n38 + n40];
                                n2 = n40;
                            }
                            if (this.c1) {
                                this.c4 += (b & 0xFF);
                                ++this.c7;
                            }
                            this.b3[n37++] = b;
                        }
                        if (!this.c1) {
                            this.a(n36, true);
                        }
                    }
                    else if (!this.c1) {
                        this.a(n36, false);
                    }
                }
                catch (Exception ex6) {}
                n = f3;
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis5 = System.currentTimeMillis();
                    if (currentTimeMillis5 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n36 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis5;
                    }
                }
            }
        }
        else {
            int n41 = (this.an && ji.util.d.cx() && this.dh) ? 1 : 0;
            if (this.c1) {
                n41 = 0;
            }
            if (n41 != 0) {
                final int[] m = this.l.l();
                if (m != null && !ji.util.d.a(m)) {
                    n41 = 0;
                }
            }
            if (n41 != 0) {
                final int[] array7 = this.as.clone();
                final int[] array8 = this.at.clone();
                final int[] array9 = new int[this.m];
                final int[] array10 = new int[this.m];
                final double n42 = this.m / this.ay;
                final int n43 = this.m - 1;
                for (int n44 = 0; n44 < this.m; ++n44) {
                    final int n45 = (int)(n44 / n42);
                    final int n46 = n44;
                    final int n47 = 1 + (int)((n45 + 1.7) * n42);
                    array9[n44] = Math.max(n46, 0);
                    array10[n44] = Math.min(n47, n43);
                }
                final byte[][] array11 = new byte[this.a8 + 1][this.m * 2];
                final int length2 = array11.length;
                for (int n48 = 0; n48 < this.a4 && !this.b1; ++n48) {
                    try {
                        this.f(y + n48);
                        final int n49 = y + n48;
                        final int n50 = array7[n49];
                        final int n51 = array8[n49];
                        final int n52 = n51 - n50;
                        int n53 = 0;
                        for (int n54 = n50; n54 < n51; n54 += this.q) {
                            if (n53 < length2) {
                                System.arraycopy(ag, n54 * this.m, array11[n53], 0, this.m);
                                n53 += this.q;
                            }
                        }
                        int n55 = 0;
                        try {
                            for (int n56 = x; n56 < n3; ++n56) {
                                final int n57 = n56 * this.m / this.ay;
                                final int n58 = array9[n57];
                                final int n59 = array10[n57];
                                int n60 = 0;
                                int n61 = 0;
                                for (int n62 = n58; n62 < n59; n62 += this.r) {
                                    for (int n63 = 0; n63 < n52; n63 += this.q) {
                                        try {
                                            n61 += (array11[n63][n62] & 0xFF);
                                        }
                                        catch (Exception ex7) {}
                                        ++n60;
                                    }
                                }
                                if (n60 > 0) {
                                    this.b3[n55] = (byte)(n61 / n60);
                                }
                                ++n55;
                            }
                            this.a(n48, true);
                        }
                        catch (Exception ex8) {}
                    }
                    catch (Exception ex9) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis6 = System.currentTimeMillis();
                        if (currentTimeMillis6 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n48 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis6;
                        }
                    }
                }
            }
            else {
                for (int n64 = 0; n64 < this.a4 && !this.b1; ++n64) {
                    try {
                        final int n65 = this.f(y + n64) * this.m;
                        int n66 = 0;
                        for (int n67 = x; n67 < n3; ++n67) {
                            final int n68 = n67 * this.m / this.ay;
                            this.b3[n66++] = ag[n65 + n68];
                            if (this.c1) {
                                this.c4 += (ag[n65 + n68] & 0xFF);
                                ++this.c7;
                            }
                        }
                        if (!this.c1) {
                            this.a(n64, true);
                        }
                    }
                    catch (Exception ex10) {}
                    if (this.cj != null && !this.c2) {
                        final long currentTimeMillis7 = System.currentTimeMillis();
                        if (currentTimeMillis7 - currentTimeMillis > 300) {
                            this.ck.a("".concat(String.valueOf(String.valueOf(100 * n64 / this.a4))));
                            if (!this.b9) {
                                this.cj.a(this.ck);
                            }
                            currentTimeMillis = currentTimeMillis7;
                        }
                    }
                }
            }
        }
        this.b3 = null;
        this.b4 = null;
    }
    
    private void h(final Rectangle rectangle) throws Exception {
        int n = -1;
        int n2 = -1;
        int f = 0;
        byte b = 0;
        final int o = this.o;
        this.b3 = new byte[rectangle.width];
        this.b4 = new byte[rectangle.width];
        if (this.dg) {
            this.b5 = new byte[rectangle.width];
            for (int i = 0; i < this.b5.length; ++i) {
                this.b5[i] = 0;
            }
        }
        final int x = rectangle.x;
        final int y = rectangle.y;
        final int width = rectangle.width;
        final int height = rectangle.height;
        final int n3 = x + this.a5;
        final byte[] ag = this.l.ag();
        int m = this.m;
        final int n4 = this.m % 2;
        if (n4 > 0) {
            m += 2 - n4;
        }
        final int n5 = m / 2;
        long currentTimeMillis = System.currentTimeMillis();
        this.c4 = 0L;
        this.c5 = 0L;
        this.c6 = 0L;
        this.c7 = 0L;
        if (this.a9) {
            for (int n6 = 0; n6 < this.a4 && !this.b1; ++n6) {
                try {
                    f = this.f(y + n6);
                    if (f != n) {
                        int n7 = 0;
                        final int n8 = f * n5;
                        for (int j = x; j < n3; ++j) {
                            final int n9 = j * this.m / this.ay;
                            if (n9 != n2) {
                                b = (byte)(ag[n8 + n9 / 2] & 0xFF);
                                b >>= (byte)(4 - 4 * (n9 % 2));
                                b &= 0xF;
                                n2 = n9;
                            }
                            if (this.c1) {
                                this.c4 += b;
                                ++this.c7;
                            }
                            this.b3[n7++] = b;
                        }
                        if (!this.c1) {
                            this.a(n6, true);
                        }
                    }
                    else if (!this.c1) {
                        this.a(n6, false);
                    }
                }
                catch (Exception ex) {}
                n = f;
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    if (currentTimeMillis2 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n6 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis2;
                    }
                }
            }
        }
        else {
            for (int n10 = 0; n10 < this.a4 && !this.b1; ++n10) {
                try {
                    final int n11 = this.f(y + n10) * n5;
                    int n12 = 0;
                    for (int k = x; k < n3; ++k) {
                        final int n13 = k * this.m / this.ay;
                        final byte b2 = (byte)((byte)((byte)(ag[n11 + n13 / 2] & 0xFF) >> 4 - 4 * (n13 % 2)) & 0xF);
                        if (this.c1) {
                            this.c4 += b2;
                            ++this.c7;
                        }
                        this.b3[n12++] = b2;
                    }
                    if (!this.c1) {
                        this.a(n10, true);
                    }
                }
                catch (Exception ex2) {}
                if (this.cj != null && !this.c2) {
                    final long currentTimeMillis3 = System.currentTimeMillis();
                    if (currentTimeMillis3 - currentTimeMillis > 300) {
                        this.ck.a("".concat(String.valueOf(String.valueOf(100 * n10 / this.a4))));
                        if (!this.b9) {
                            this.cj.a(this.ck);
                        }
                        currentTimeMillis = currentTimeMillis3;
                    }
                }
            }
        }
        this.b3 = null;
        this.b4 = null;
    }
    
    public final int j() {
        if (this.cr != null) {
            return this.cr.width;
        }
        if (this.a1 == null) {
            return 0;
        }
        if (this.a1.q != 0) {
            return this.a1.q;
        }
        return this.a1.m;
    }
    
    public final int k() {
        if (this.cr != null) {
            return this.cr.width;
        }
        if (this.a1 != null) {
            return this.a1.m;
        }
        return 0;
    }
    
    public final int l() {
        if (this.cr != null) {
            return this.cr.height;
        }
        if (this.a1 != null) {
            return this.a1.n;
        }
        return 0;
    }
    
    public final int m() {
        if (this.cr != null) {
            return this.de = this.cr.height;
        }
        if (this.a1 == null) {
            return this.de;
        }
        if (this.a1.r != 0) {
            return this.de = this.a1.r;
        }
        return this.de = this.a1.n;
    }
    
    public final void a(final ds l, final Rectangle rectangle, final Object o) throws Exception {
        final boolean cn = this.cn;
        try {
            this.cn = false;
            this.l = l;
            this.co = l.j();
            if (rectangle != null) {
                if (rectangle.x != 0 || rectangle.y != 0 || rectangle.width != this.j() || rectangle.height != this.m()) {
                    if (this.a1.am <= 1) {
                        this.b(l, rectangle, o);
                    }
                    else if (this.a1.am <= 4) {
                        this.c(l, rectangle, o);
                    }
                    else if (this.a1.am <= 8) {
                        this.d(l, rectangle, o);
                    }
                    else {
                        this.e(l, rectangle, o);
                    }
                }
                this.cr = new Rectangle(rectangle);
                this.cv.addElement(this.cr);
                this.m = this.j();
                this.n = this.m();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.cn = cn;
        }
    }
    
    public final Rectangle n() throws Exception {
        return this.cr;
    }
    
    private final void b(final ds ds, final Rectangle rectangle, final Object o) throws Exception {
        if (ds == null) {
            return;
        }
        final int x = rectangle.x;
        final int bx = x + rectangle.width;
        final int y = rectangle.y;
        final int n = y + rectangle.height;
        final int n2 = (int)(2L * (65535L * (1L + this.m / 65535L)));
        boolean b = false;
        this.bu = 2 * this.q + 2 * this.a8;
        this.bv = this.m;
        if (this.bi == null) {
            b = true;
        }
        else if (this.bi[0].length < this.bv) {
            b = true;
        }
        else if (this.bi.length < this.bu) {
            b = true;
        }
        if (b) {
            this.bm = new int[this.bv];
            if (this.a0 == 0) {
                this.bi = new int[1][this.bv];
            }
            else {
                this.bi = new int[this.bu][this.bv];
            }
        }
        final int[] array = new int[this.bv * 2];
        final boolean j = ds.j();
        final int[] array2 = this.bi[0];
        this.cw = new short[n2];
        this.bn = ds.ai();
        if (this.bn == null) {
            return;
        }
        this.bq = this.bn.size();
        this.bw = x;
        this.bx = bx;
        this.by = Math.max(this.bw / 32 - 1, 0);
        this.bz = Math.min(32 + (this.bx - this.bw) / 32, this.bv - this.by);
        ds.al();
        this.e(this.bv);
        final Vector<short[]> vector = new Vector<short[]>(rectangle.height, 10);
        int n3 = 0;
        if (j) {
            ds.a(rectangle.width, rectangle.height, o);
        }
        if (ds.o()) {
            for (int i = y; i < n; ++i) {
                this.a(i, 0);
                int n4 = 0;
                int n5 = 0x1 & array2[0] >>> 31 - x % 32;
                int n6 = 1;
                if (n5 != 0) {
                    array[n4++] = 0;
                }
                for (int k = x + 1; k < bx; ++k) {
                    final int n7 = 0x1 & array2[k / 32] >>> 31 - k % 32;
                    if (n7 == n5) {
                        ++n6;
                    }
                    else {
                        array[n4++] = (short)n6;
                        n6 = 1;
                        n5 = n7;
                    }
                }
                if (n6 > 0) {
                    array[n4++] = (short)n6;
                }
                if (j) {
                    ds.a(array, n4, i - y);
                }
                else if (this.m < 65535) {
                    for (int l = 0; l < n4; ++l) {
                        this.cw[l] = (short)(array[l] & 0xFFFF);
                    }
                }
                else {
                    int n8 = 0;
                    for (int n9 = 0; n9 < n4; ++n9) {
                        if (array[n9] > 65535) {
                            int n10 = array[n9];
                            short n11 = -1;
                            while (n10 > 0) {
                                this.cw[n8++] = n11;
                                n10 -= 65535;
                                if (n10 > 0) {
                                    this.cw[n8++] = 0;
                                }
                                if (n10 <= 65535) {
                                    n11 = (short)(n10 & 0xFFFF);
                                }
                            }
                        }
                        else {
                            this.cw[n8++] = (short)(array[n9] & 0xFFFF);
                        }
                    }
                }
                if (!j) {
                    final short[] array3 = new short[n4];
                    System.arraycopy(this.cw, 0, array3, 0, n4);
                    vector.addElement(array3);
                }
                n3 += n4;
            }
        }
        if (this.dk > 0) {
            if (ji.util.i.c(85)) {
                ji.io.h.d(this.bf, String.valueOf(String.valueOf(new StringBuffer("cropMono: Color length exceeded line length line on ").append(this.dk).append(" lines"))));
            }
            this.dk = 0;
        }
        if (j) {
            ds.c(o);
        }
        else {
            ds.a(vector, n3);
        }
    }
    
    private final void c(final ds ds, final Rectangle rectangle, final Object o) throws Exception {
        if (ds == null) {
            return;
        }
        final int x = rectangle.x;
        final int n = x + rectangle.width;
        final int y = rectangle.y;
        final int n2 = y + rectangle.height;
        int m;
        final int n3 = (m = ds.m()) % 2;
        if (n3 > 0) {
            m += 2 - n3;
        }
        int width = rectangle.width;
        final int n4 = rectangle.width % 2;
        if (n4 > 0) {
            width += 2 - n4;
        }
        ds.al();
        ds.a(rectangle.width, rectangle.height, o);
        final byte[] ag = ds.ag();
        final byte[] ah = ds.ah();
        final int n5 = m / 2;
        final int n6 = width / 2;
        for (int i = y; i < n2; ++i) {
            final int n7 = i * n5;
            final int n8 = (i - y) * n6;
            for (int j = x; j < n; ++j) {
                final byte b = (byte)((byte)((byte)((byte)(ag[n7 + j / 2] & 0xFF) >> 4 - 4 * (j % 2)) & 0xF) << 4 - 4 * ((j - x) % 2));
                final byte[] array = ah;
                final int n9 = n8 + (j - x) / 2;
                array[n9] |= b;
            }
        }
        ds.c(o);
    }
    
    private final void d(final ds ds, final Rectangle rectangle, final Object o) throws Exception {
        if (ds == null) {
            return;
        }
        final int x = rectangle.x;
        final int n = x + rectangle.width;
        final int y = rectangle.y;
        final int n2 = y + rectangle.height;
        final int m = ds.m();
        ds.al();
        ds.a(rectangle.width, rectangle.height, o);
        final byte[] ag = ds.ag();
        final byte[] ah = ds.ah();
        for (int i = y; i < n2; ++i) {
            int n3 = 0;
            final int n4 = i * m;
            final int n5 = (i - y) * rectangle.width;
            for (int j = x; j < n; ++j) {
                ah[n5 + n3++] = ag[n4 + j];
            }
        }
        ds.c(o);
    }
    
    private final void e(final ds ds, final Rectangle rectangle, final Object o) throws Exception {
        if (ds == null) {
            return;
        }
        final int x = rectangle.x;
        final int n = x + rectangle.width;
        final int y = rectangle.y;
        final int n2 = y + rectangle.height;
        final int m = ds.m();
        final int[] array = new int[m * 2];
        final Vector<int[]> vector = new Vector<int[]>(rectangle.height, 10);
        ds.al();
        for (int i = y; i < n2; ++i) {
            ds.c(this.d(i));
            ds.a(array, m);
            final int[] array2 = new int[rectangle.width];
            System.arraycopy(array, x, array2, 0, rectangle.width);
            vector.addElement(array2);
        }
        ds.a(rectangle.width, rectangle.height, o);
        for (int j = 0; j < rectangle.height; ++j) {
            ds.a(vector.elementAt(j), rectangle.width, o, j, j, true);
        }
        ds.e(o);
    }
    
    public final void o() {
        try {
            if (this.cv != null && this.cv.size() > 0) {
                this.cv.clear();
            }
            this.cr = null;
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ds ds, final Object o) throws Exception {
        ds.d(o);
        this.cr = null;
        if (this.cv.size() > 0 && ds != null) {
            this.cv.removeElementAt(this.cv.size() - 1);
            if (this.cv.size() - 1 >= 0) {
                final int size = this.cv.size();
                final Vector vector = new Vector<Rectangle>(size);
                for (int i = 0; i < size; ++i) {
                    vector.addElement(this.cv.elementAt(i));
                }
                this.cv.clear();
                for (int j = 0; j < size; ++j) {
                    this.cr = vector.elementAt(j);
                    this.m = this.j();
                    this.n = this.m();
                    this.a(ds, this.cr, o);
                }
            }
        }
    }
    
    private final void a(final int n, final boolean b) {
        try {
            if (this.dg) {
                System.arraycopy(this.b5, 0, this.b3, 0, this.bc);
            }
            if (this.cm && b) {
                System.arraycopy(this.b3, 0, this.b4, 0, this.bc);
                int n2 = this.bc - 1;
                for (int i = 0; i < this.bc; ++i) {
                    this.b3[i] = this.b4[n2--];
                }
            }
            switch (this.al) {
                case 0: {
                    if (this.cl) {
                        System.arraycopy(this.b3, 0, this.ca, (this.bd - n - 1) * this.bc, this.bc);
                        break;
                    }
                    System.arraycopy(this.b3, 0, this.ca, n * this.bc, this.bc);
                    break;
                }
                case 90: {
                    if (this.cl) {
                        for (int j = 0; j < this.bc; ++j) {
                            this.ca[n + j * this.bd] = this.b3[j];
                        }
                        break;
                    }
                    final int n3 = this.bd - n - 1;
                    for (int k = 0; k < this.bc; ++k) {
                        this.ca[n3 + k * this.bd] = this.b3[k];
                    }
                    break;
                }
                case 180: {
                    if (this.cl) {
                        final int n4 = (n + 1) * this.bc - 1;
                        for (int l = 0; l < this.bc; ++l) {
                            this.ca[n4 - l] = this.b3[l];
                        }
                        break;
                    }
                    final int n5 = (this.bd - n) * this.bc - 1;
                    for (int n6 = 0; n6 < this.bc; ++n6) {
                        this.ca[n5 - n6] = this.b3[n6];
                    }
                    break;
                }
                case 270: {
                    if (this.cl) {
                        final int n7 = this.bc * this.bd - n - 1;
                        for (int n8 = 0; n8 < this.bc; ++n8) {
                            this.ca[n7 - n8 * this.bd] = this.b3[n8];
                        }
                        break;
                    }
                    final int n9 = this.bc * this.bd - (this.bd - n);
                    for (int n10 = 0; n10 < this.bc; ++n10) {
                        this.ca[n9 - n10 * this.bd] = this.b3[n10];
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void b(final int n, final boolean b) {
        try {
            if (this.dg) {
                System.arraycopy(this.b5, 0, this.b3, 0, this.bc);
            }
            if (this.cm && b) {
                System.arraycopy(this.b3, 0, this.b4, 0, this.bc);
                int n2 = this.bc - 1;
                for (int i = 0; i < this.bc; ++i) {
                    this.b3[i] = this.b4[n2--];
                }
            }
            if (this.cl && b) {
                this.bs += this.a(this.b3, this.ca, this.b0 * (this.bd - n - 1));
            }
            else {
                this.bs += this.a(this.b3, this.ca, this.b0 * n);
            }
        }
        catch (Exception ex) {}
    }
    
    private final int a(final byte[] array, final byte[] array2, int n) {
        final int length = array.length;
        final int length2 = array2.length;
        int n2 = 7;
        array2[n] = 0;
        for (int i = 0; i < length; ++i) {
            if (array[i] > 0) {
                array2[n] |= (byte)(1 << n2);
            }
            if (--n2 < 0) {
                n2 = 7;
                if (++n < length2) {
                    array2[n] = 0;
                }
            }
        }
        return length;
    }
    
    private final void a(final int n, final boolean b, final int[] array) {
        try {
            int[] b2;
            if (array != null) {
                b2 = array;
            }
            else {
                b2 = this.b6;
            }
            if (this.dg) {
                System.arraycopy(this.b8, 0, b2, 0, this.bc);
            }
            if (this.cm && b) {
                System.arraycopy(b2, 0, this.b7, 0, this.bc);
                int n2 = this.bc - 1;
                for (int i = 0; i < this.bc; ++i) {
                    b2[i] = this.b7[n2--];
                }
            }
            if (ji.util.i.c(87) && ji.util.d.ak(this.bf)) {
                for (int j = 0; j < this.bc; ++j) {
                    b2[j] |= 0xFF000000;
                }
            }
            switch (this.al) {
                case 0: {
                    if (this.cl) {
                        System.arraycopy(b2, 0, this.cb, (this.bd - n - 1) * this.bc, this.bc);
                        break;
                    }
                    System.arraycopy(b2, 0, this.cb, n * this.bc, this.bc);
                    break;
                }
                case 90: {
                    if (this.cl) {
                        for (int k = 0; k < this.bc; ++k) {
                            this.cb[n + k * this.bd] = b2[k];
                        }
                        break;
                    }
                    final int n3 = this.bd - n - 1;
                    for (int l = 0; l < this.bc; ++l) {
                        this.cb[n3 + l * this.bd] = b2[l];
                    }
                    break;
                }
                case 180: {
                    if (this.cl) {
                        final int n4 = (n + 1) * this.bc - 1;
                        for (int n5 = 0; n5 < this.bc; ++n5) {
                            this.cb[n4 - n5] = b2[n5];
                        }
                        break;
                    }
                    final int n6 = (this.bd - n) * this.bc - 1;
                    for (int n7 = 0; n7 < this.bc; ++n7) {
                        this.cb[n6 - n7] = b2[n7];
                    }
                    break;
                }
                case 270: {
                    if (this.cl) {
                        final int n8 = this.bc * this.bd - n - 1;
                        for (int n9 = 0; n9 < this.bc; ++n9) {
                            this.cb[n8 - n9 * this.bd] = b2[n9];
                        }
                        break;
                    }
                    final int n10 = this.bc * this.bd - (this.bd - n);
                    for (int n11 = 0; n11 < this.bc; ++n11) {
                        this.cb[n10 - n11 * this.bd] = b2[n11];
                    }
                    break;
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    private void w() {
        try {
            if (this.a1 != null && this.ay > 0 && this.az > 0) {
                this.ap = null;
                this.aq = null;
                this.as = null;
                this.at = null;
                this.ar = null;
                this.a7 = (int)Math.round(this.j() / this.ay + 0.5);
                this.a8 = (int)Math.round(this.m() / this.az + 0.5);
                if (ji.util.e.i() && this.a1.am <= 1) {
                    this.q = 1;
                    this.r = 1;
                }
                else {
                    this.q = Math.min(Math.max((this.a8 + 7) / 8, 1), 3);
                    this.r = Math.min(Math.max((this.a7 + 7) / 8, 1), 3);
                    if (this.a1.am >= 24) {
                        this.q = Math.max(this.a8 / 3, 1);
                        this.r = Math.max(this.a7 / 3, 1);
                    }
                    this.a7 = Math.min(this.a7, 16);
                    this.a8 = Math.min(this.a8, 16);
                }
                this.aq = new int[this.n + 1];
                this.ap = new int[this.m + 1];
                if (this.n > 0) {
                    for (int i = 0; i <= this.n; ++i) {
                        this.aq[i] = i * this.az / this.n;
                        if (this.aq[i] >= this.az) {
                            this.aq[i] = this.az - 1;
                        }
                    }
                }
                if (this.m > 0) {
                    for (int j = 0; j <= this.m; ++j) {
                        this.ap[j] = j * this.ay / this.m;
                        if (this.ap[j] >= this.ay) {
                            this.ap[j] = this.ay - 1;
                        }
                    }
                }
                this.ar = new int[this.n + 1];
                for (int k = 0; k < this.n; ++k) {
                    this.ar[k] = k * this.o;
                }
                this.as = null;
                this.at = null;
                boolean b = false;
                if (this.c2 && !ji.util.d.b()) {
                    if (this.ay <= this.m || this.az <= this.n) {
                        b = true;
                    }
                }
                else if (this.ay < this.m || this.az < this.n) {
                    b = true;
                }
                if (b) {
                    this.as = new int[this.az + 1];
                    this.at = new int[this.az + 1];
                    final boolean b2 = this.a1.am == 8;
                    final int n = 5;
                    if (!ji.util.e.i() || this.a1.am > 1) {
                        final int n2 = 2;
                        for (int l = 0; l < this.az; ++l) {
                            if (l > 0) {
                                final int min = Math.min((this.f(l) - this.f(l - 1)) / n2, this.a8);
                                if (b2) {
                                    this.as[l] = Math.max(this.f(l) - Math.min(min, n), 0);
                                }
                                else {
                                    this.as[l] = Math.max(this.f(l) - min, 0);
                                }
                            }
                            else {
                                this.as[l] = 0;
                            }
                            if (l < this.az - 1) {
                                final int min2 = Math.min((this.f(l + 1) - this.f(l)) / n2, this.a8);
                                if (b2) {
                                    this.at[l] = this.f(l) + Math.min(min2, n) + 1;
                                }
                                else {
                                    this.at[l] = this.f(l) + min2 + 1;
                                }
                            }
                            else {
                                this.at[l] = Math.min(this.n - 1, this.as[l] + this.a8);
                            }
                        }
                    }
                }
                if (this.p == null) {
                    this.p = null;
                    this.p = new int[65536];
                    for (int n3 = 0; n3 < this.p.length; ++n3) {
                        int n4 = n3;
                        int n5 = 0;
                        for (int n6 = 0; n6 < 16; ++n6) {
                            n5 += (n4 & 0x1);
                            n4 >>>= 1;
                        }
                        this.p[n3] = n5;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final int f(final int n) {
        return Math.min(n * this.n / this.az, this.n - 1);
    }
    
    public final void p() {
        if (this.e()) {
            this.c();
            while (this.e() && !ji.util.d.ck(this.bf)) {
                ji.util.d.b(10, 124, this.bf);
            }
        }
        this.x();
        this.o();
    }
    
    private final void x() {
        this.bi = null;
        this.bm = null;
        this.aj = null;
        this.bj = null;
        this.bl = null;
        this.bk = null;
        this.b3 = null;
        this.b4 = null;
        this.b8 = null;
        this.b5 = null;
    }
    
    private final void y() {
        if (!this.aa) {
            this.ah = ColorModel.getRGBdefault();
            this.ai = ColorModel.getRGBdefault();
            final int d0 = ji.util.d.d0;
            final int d2 = ji.util.d.d1;
            final int d3 = ji.util.d.d2;
            this.c0 = 511 * this.a / d0 - 255;
            if (this.s == null) {
                this.s = new byte[256];
                this.t = new byte[256];
                this.u = new byte[256];
                this.v = new byte[256];
                this.w = new byte[256];
                this.y = new byte[256];
                this.x = new byte[256];
                this.z = new byte[256];
                this.cx = new int[256];
                this.cy = new int[256];
                this.cz = new int[256];
            }
            int n = 255 / 256 - 1;
            final int red = this.db.getRed();
            final int green = this.db.getGreen();
            final int blue = this.db.getBlue();
            final int red2 = this.dc.getRed();
            final int green2 = this.dc.getGreen();
            final int blue2 = this.dc.getBlue();
            int n2 = red - 0 * (red - red2) / 255;
            int n3 = green - 0 * (green - green2) / 255;
            int n4 = blue - 0 * (blue - blue2) / 255;
            for (int i = 1; i < 255; ++i) {
                this.s[i] = (byte)Math.max(Math.min(this.c0 + n2 * this.d / this.g, 255), 0);
                this.t[i] = (byte)Math.max(Math.min(this.c0 + n3 * this.d / this.g, 255), 0);
                this.u[i] = (byte)Math.max(Math.min(this.c0 + n4 * this.d / this.g, 255), 0);
                if (--n <= 0) {
                    n2 = red - i * (red - red2) / 255;
                    n3 = green - i * (green - green2) / 255;
                    n4 = blue - i * (blue - blue2) / 255;
                }
            }
            if (this.c3 > 0 && this.b != this.h) {
                final int n5 = ((this.s[this.c3] & 0xFF) + (this.t[this.c3] & 0xFF) + (this.u[this.c3] & 0xFF)) / 3;
                for (int j = 0; j < 255; ++j) {
                    this.s[j] = (byte)Math.max(Math.min(n5 + this.c * ((this.s[j] & 0xFF) - n5) / this.f, 255), 0);
                    this.t[j] = (byte)Math.max(Math.min(n5 + this.c * ((this.t[j] & 0xFF) - n5) / this.f, 255), 0);
                    this.u[j] = (byte)Math.max(Math.min(n5 + this.c * ((this.u[j] & 0xFF) - n5) / this.f, 255), 0);
                }
            }
            else if (this.c3 == 0 && this.b != this.h) {
                final int n6 = 127;
                final int n7 = ((this.s[n6] & 0xFF) + (this.t[n6] & 0xFF) + (this.u[n6] & 0xFF)) / 3;
                for (int k = 0; k < 255; ++k) {
                    this.s[k] = (byte)Math.max(Math.min(n7 + this.c * ((this.s[k] & 0xFF) - n7) / this.f, 255), 0);
                    this.t[k] = (byte)Math.max(Math.min(n7 + this.c * ((this.t[k] & 0xFF) - n7) / this.f, 255), 0);
                    this.u[k] = (byte)Math.max(Math.min(n7 + this.c * ((this.u[k] & 0xFF) - n7) / this.f, 255), 0);
                }
            }
            this.s[0] = (byte)red;
            this.t[0] = (byte)green;
            this.u[0] = (byte)blue;
            this.s[255] = (byte)Math.min(this.dc.getRed() * this.d / this.g, 255);
            this.t[255] = (byte)Math.min(this.dc.getGreen() * this.d / this.g, 255);
            this.u[255] = (byte)Math.min(this.dc.getBlue() * this.d / this.g, 255);
            this.a(this.s, this.t, this.u);
            for (int l = 0; l < 256; ++l) {
                this.w[l] = (byte)Math.max(Math.min(this.c0 + (255 - (this.s[l] & 0xFF)) * this.d / this.g, 255), 0);
                this.y[l] = (byte)Math.max(Math.min(this.c0 + (255 - (this.t[l] & 0xFF)) * this.d / this.g, 255), 0);
                this.x[l] = (byte)Math.max(Math.min(this.c0 + (255 - (this.u[l] & 0xFF)) * this.d / this.g, 255), 0);
            }
            final int n8 = 255 - red2;
            final int n9 = 255 - green2;
            final int n10 = 255 - blue2;
            this.w[0] = (byte)(255 - red);
            this.y[0] = (byte)(255 - green);
            this.x[0] = (byte)(255 - blue);
            this.w[255] = (byte)Math.min(n8 * this.d / this.g, 255);
            this.y[255] = (byte)Math.min(n9 * this.d / this.g, 255);
            this.x[255] = (byte)Math.min(n10 * this.d / this.g, 255);
            if (this.c3 > 0 && this.b != this.h) {
                final int n11 = ((this.w[this.c3] & 0xFF) + (this.y[this.c3] & 0xFF) + (this.x[this.c3] & 0xFF)) / 3;
                for (int n12 = 0; n12 < 255; ++n12) {
                    this.w[n12] = (byte)Math.max(Math.min(n11 + this.c * ((this.w[n12] & 0xFF) - n11) / this.f, 255), 0);
                    this.y[n12] = (byte)Math.max(Math.min(n11 + this.c * ((this.y[n12] & 0xFF) - n11) / this.f, 255), 0);
                    this.x[n12] = (byte)Math.max(Math.min(n11 + this.c * ((this.x[n12] & 0xFF) - n11) / this.f, 255), 0);
                }
            }
            else if (this.c3 == 0 && this.b != this.h) {
                final int n13 = 127;
                final int n14 = ((this.w[n13] & 0xFF) + (this.y[n13] & 0xFF) + (this.x[n13] & 0xFF)) / 3;
                for (int n15 = 0; n15 < 255; ++n15) {
                    this.w[n15] = (byte)Math.max(Math.min(n14 + this.c * ((this.w[n15] & 0xFF) - n14) / this.f, 255), 0);
                    this.y[n15] = (byte)Math.max(Math.min(n14 + this.c * ((this.y[n15] & 0xFF) - n14) / this.f, 255), 0);
                    this.x[n15] = (byte)Math.max(Math.min(n14 + this.c * ((this.x[n15] & 0xFF) - n14) / this.f, 255), 0);
                }
            }
            this.ad = null;
            this.ae = null;
            this.ad = new IndexColorModel(8, 256, this.s, this.t, this.u);
            this.ae = new IndexColorModel(8, 256, this.w, this.y, this.x);
            final int n16 = 16;
            final int red3 = this.db.getRed();
            final int green3 = this.db.getGreen();
            final int blue3 = this.db.getBlue();
            final int red4 = this.dc.getRed();
            final int green4 = this.dc.getGreen();
            final int blue4 = this.dc.getBlue();
            for (int n17 = 1; n17 < 16; ++n17) {
                final int n18 = red3 - n17 * (red3 - red4) / n16;
                final int n19 = green3 - n17 * (green3 - green4) / n16;
                final int n20 = blue3 - n17 * (blue3 - blue4) / n16;
                this.s[n17] = (byte)Math.max(Math.min(this.c0 + n18 * this.d / this.g, 255), 0);
                this.t[n17] = (byte)Math.max(Math.min(this.c0 + n19 * this.d / this.g, 255), 0);
                this.u[n17] = (byte)Math.max(Math.min(this.c0 + n20 * this.d / this.g, 255), 0);
            }
            final int n21 = 255 - red4;
            final int n22 = 255 - green4;
            final int n23 = 255 - blue4;
            this.w[0] = (byte)(255 - red3);
            this.y[0] = (byte)(255 - green3);
            this.x[0] = (byte)(255 - blue3);
            this.w[15] = (byte)Math.min(n21 * this.d / this.g, 255);
            this.y[15] = (byte)Math.min(n22 * this.d / this.g, 255);
            this.x[15] = (byte)Math.min(n23 * this.d / this.g, 255);
            if (this.c3 > 0 && this.b != this.h) {
                final int n24 = ((this.s[this.c3] & 0xFF) + (this.t[this.c3] & 0xFF) + (this.u[this.c3] & 0xFF)) / 3;
                for (int n25 = 0; n25 < 16; ++n25) {
                    this.s[n25] = (byte)Math.max(Math.min(n24 + this.c * ((this.s[n25] & 0xFF) - n24) / this.f, 255), 0);
                    this.t[n25] = (byte)Math.max(Math.min(n24 + this.c * ((this.t[n25] & 0xFF) - n24) / this.f, 255), 0);
                    this.u[n25] = (byte)Math.max(Math.min(n24 + this.c * ((this.u[n25] & 0xFF) - n24) / this.f, 255), 0);
                }
            }
            this.a(this.s, this.t, this.u);
            for (int n26 = 0; n26 < 256; ++n26) {
                this.w[n26] = (byte)Math.max(Math.min(this.c0 + (255 - (this.s[n26] & 0xFF)) * this.d / this.g, 255), 0);
                this.y[n26] = (byte)Math.max(Math.min(this.c0 + (255 - (this.t[n26] & 0xFF)) * this.d / this.g, 255), 0);
                this.x[n26] = (byte)Math.max(Math.min(this.c0 + (255 - (this.u[n26] & 0xFF)) * this.d / this.g, 255), 0);
            }
            if (this.c3 > 0 && this.b != this.h) {
                final int n27 = ((this.w[this.c3] & 0xFF) + (this.y[this.c3] & 0xFF) + (this.x[this.c3] & 0xFF)) / 3;
                for (int n28 = 0; n28 < 16; ++n28) {
                    this.w[n28] = (byte)Math.max(Math.min(n27 + this.c * ((this.w[n28] & 0xFF) - n27) / this.f, 255), 0);
                    this.y[n28] = (byte)Math.max(Math.min(n27 + this.c * ((this.y[n28] & 0xFF) - n27) / this.f, 255), 0);
                    this.x[n28] = (byte)Math.max(Math.min(n27 + this.c * ((this.x[n28] & 0xFF) - n27) / this.f, 255), 0);
                }
            }
            this.af = null;
            this.ag = null;
            this.af = new IndexColorModel(8, 16, this.s, this.t, this.u);
            this.ag = new IndexColorModel(8, 16, this.w, this.y, this.x);
            if (this.c3 > 0 && this.b != this.h) {
                final int n29 = ((this.s[this.c3] & 0xFF) + (this.t[this.c3] & 0xFF) + (this.u[this.c3] & 0xFF)) / 3;
                this.s[0] = (byte)Math.max(Math.min(n29 + this.c * (red3 - n29) / this.f, 255), 0);
                this.t[0] = (byte)Math.max(Math.min(n29 + this.c * (green3 - n29) / this.f, 255), 0);
                this.u[0] = (byte)Math.max(Math.min(n29 + this.c * (blue3 - n29) / this.f, 255), 0);
            }
            else {
                this.s[0] = (byte)red3;
                this.t[0] = (byte)green3;
                this.u[0] = (byte)blue3;
            }
            for (int n30 = 1; n30 < 256; ++n30) {
                this.s[n30] = (byte)Math.max(Math.min(this.c0 + this.dc.getRed() * this.d / this.g, 255), 0);
                this.t[n30] = (byte)Math.max(Math.min(this.c0 + this.dc.getGreen() * this.d / this.g, 255), 0);
                this.u[n30] = (byte)Math.max(Math.min(this.c0 + this.dc.getBlue() * this.d / this.g, 255), 0);
            }
            if (this.c3 > 0 && this.b != this.h) {
                final int n31 = ((this.s[this.c3] & 0xFF) + (this.t[this.c3] & 0xFF) + (this.u[this.c3] & 0xFF)) / 3;
                for (int n32 = 0; n32 < 256; ++n32) {
                    this.s[n32] = (byte)Math.max(Math.min(n31 + this.c * ((this.s[n32] & 0xFF) - n31) / this.f, 255), 0);
                    this.t[n32] = (byte)Math.max(Math.min(n31 + this.c * ((this.t[n32] & 0xFF) - n31) / this.f, 255), 0);
                    this.u[n32] = (byte)Math.max(Math.min(n31 + this.c * ((this.u[n32] & 0xFF) - n31) / this.f, 255), 0);
                }
            }
            this.a(this.s, this.t, this.u);
            if (this.c3 > 0 && this.b != this.h) {
                final int n33 = ((this.w[this.c3] & 0xFF) + (this.y[this.c3] & 0xFF) + (this.x[this.c3] & 0xFF)) / 3;
                this.w[0] = (byte)Math.max(Math.min(n33 + this.c * (this.dc.getRed() - n33) / this.f, 255), 0);
                this.y[0] = (byte)Math.max(Math.min(n33 + this.c * (this.dc.getGreen() - n33) / this.f, 255), 0);
                this.x[0] = (byte)Math.max(Math.min(n33 + this.c * (this.dc.getBlue() - n33) / this.f, 255), 0);
            }
            else {
                this.w[0] = (byte)(255 - this.db.getRed());
                this.y[0] = (byte)(255 - this.db.getGreen());
                this.x[0] = (byte)(255 - this.db.getBlue());
            }
            final int n34 = 255 - red4;
            final int n35 = 255 - green4;
            final int n36 = 255 - blue4;
            for (int n37 = 1; n37 < 256; ++n37) {
                this.w[n37] = (byte)Math.max(Math.min(this.c0 + n34 * this.d / this.g, 255), 0);
                this.y[n37] = (byte)Math.max(Math.min(this.c0 + n35 * this.d / this.g, 255), 0);
                this.x[n37] = (byte)Math.max(Math.min(this.c0 + n36 * this.d / this.g, 255), 0);
            }
            this.ab = null;
            this.ac = null;
            this.ab = new IndexColorModel(8, 256, this.s, this.t, this.u);
            this.ac = new IndexColorModel(8, 256, this.w, this.y, this.x);
            for (int n38 = 1; n38 < 255; ++n38) {
                this.s[n38] = (byte)Math.min(n38 * this.d / this.g, 255L);
                this.t[n38] = this.s[n38];
                this.u[n38] = this.s[n38];
                this.v[n38] = this.s[n38];
            }
            for (int n39 = 0; n39 < 16; ++n39) {
                this.w[n39] = (byte)(255 - this.s[n39] & 0xFF);
                this.y[n39] = (byte)(255 - this.t[n39] & 0xFF);
                this.x[n39] = (byte)(255 - this.u[n39] & 0xFF);
                this.z[n39] = (byte)(255 - this.v[n39] & 0xFF);
            }
            for (int n40 = 0; n40 < 256; ++n40) {
                this.cx[n40] = Math.min(n40 * this.d / this.g, 255);
                this.cz[n40] = Math.min(n40 * this.d / this.g, 255);
                this.cy[n40] = Math.min(n40 * this.d / this.g, 255);
            }
            this.aa = true;
        }
    }
    
    private final void a(final byte[] array, final byte[] array2, final byte[] array3) {
        try {
            if (ji.util.d.di() == 1) {
                final int red = Color.black.getRed();
                final int green = Color.black.getGreen();
                final int blue = Color.black.getBlue();
                final int red2 = Color.white.getRed();
                final int green2 = Color.white.getGreen();
                final int blue2 = Color.white.getBlue();
                if (this.a1.bi == 1) {
                    for (int i = 0; i < 256; ++i) {
                        array3[i] = (array2[i] = -1);
                    }
                }
                else if (this.a1.bi == 2) {
                    for (int j = 0; j < 256; ++j) {
                        array3[j] = (array[j] = -1);
                    }
                }
                else if (this.a1.bi == 3) {
                    for (int k = 0; k < 256; ++k) {
                        array2[k] = (array[k] = -1);
                    }
                }
                if (this.a1.bi != 0) {
                    for (int l = 0; l < 256; ++l) {
                        if (array[l] == red && array2[l] == green && array3[l] == blue) {
                            array[l] = (byte)red2;
                            array2[l] = (byte)green2;
                            array3[l] = (byte)blue2;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final ColorModel a(final boolean b, final ds ds) {
        this.y();
        if (ds != null) {
            final int[] l = ds.l();
            if (l != null) {
                boolean b2 = false;
                if (ji.util.d.a(l)) {
                    b2 = true;
                }
                if (this.aj == null) {
                    final byte[] array = new byte[l.length];
                    final byte[] array2 = new byte[l.length];
                    final byte[] array3 = new byte[l.length];
                    for (int length = l.length, i = 0; i < length; ++i) {
                        if (b2) {
                            array[i] = (byte)this.ad.getRed(255 - i);
                            array2[i] = (byte)this.ad.getGreen(255 - i);
                            array3[i] = (byte)this.ad.getBlue(255 - i);
                        }
                        else {
                            array[i] = (byte)((l[i] & 0xFF0000) >> 16);
                            array2[i] = (byte)((l[i] & 0xFF00) >> 8);
                            array3[i] = (byte)(l[i] & 0xFF);
                        }
                        if (this.dd) {
                            array2[i] = (array[i] = (byte)((2 * (array[i] & 0xFF) + 2 * (array2[i] & 0xFF) + (array3[i] & 0xFF)) / 5));
                            array3[i] = array[i];
                        }
                        array[i] = (byte)Math.max(Math.min(this.c0 + (array[i] & 0xFF) * this.d / this.g, 255), 0);
                        array2[i] = (byte)Math.max(Math.min(this.c0 + (array2[i] & 0xFF) * this.d / this.g, 255), 0);
                        array3[i] = (byte)Math.max(Math.min(this.c0 + (array3[i] & 0xFF) * this.d / this.g, 255), 0);
                    }
                    if (this.c3 > 0 && this.b != this.h) {
                        final int n = ((array[this.c3] & 0xFF) + (array2[this.c3] & 0xFF) + (array3[this.c3] & 0xFF)) / 3;
                        for (int j = 0; j < l.length; ++j) {
                            array[j] = (byte)Math.max(Math.min(n + this.c * ((array[j] & 0xFF) - n) / this.f, 255), 0);
                            array2[j] = (byte)Math.max(Math.min(n + this.c * ((array2[j] & 0xFF) - n) / this.f, 255), 0);
                            array3[j] = (byte)Math.max(Math.min(n + this.c * ((array3[j] & 0xFF) - n) / this.f, 255), 0);
                        }
                    }
                    this.aj = new IndexColorModel(8, l.length, array, array2, array3);
                    for (int k = 0; k < l.length; ++k) {
                        if (b2) {
                            array[k] = (byte)this.ae.getRed(255 - k);
                            array2[k] = (byte)this.ae.getGreen(255 - k);
                            array3[k] = (byte)this.ae.getBlue(255 - k);
                        }
                        else {
                            array[k] = (byte)(255 - ((l[k] & 0xFF0000) >> 16));
                            array2[k] = (byte)(255 - ((l[k] & 0xFF00) >> 8));
                            array3[k] = (byte)(255 - (l[k] & 0xFF));
                        }
                        if (this.dd) {
                            array2[k] = (array[k] = (byte)((2 * (array[k] & 0xFF) + 2 * (array2[k] & 0xFF) + (array3[k] & 0xFF)) / 5));
                            array3[k] = array[k];
                        }
                        array[k] = (byte)Math.max(Math.min(this.c0 + (array[k] & 0xFF) * this.d / this.g, 255), 0);
                        array2[k] = (byte)Math.max(Math.min(this.c0 + (array2[k] & 0xFF) * this.d / this.g, 255), 0);
                        array3[k] = (byte)Math.max(Math.min(this.c0 + (array3[k] & 0xFF) * this.d / this.g, 255), 0);
                    }
                    if (this.c3 > 0 && this.b != this.h) {
                        final int n2 = ((array[this.c3] & 0xFF) + (array2[this.c3] & 0xFF) + (array3[this.c3] & 0xFF)) / 3;
                        for (int n3 = 0; n3 < l.length; ++n3) {
                            array[n3] = (byte)Math.max(Math.min(n2 + this.c * ((array[n3] & 0xFF) - n2) / this.f, 255), 0);
                            array2[n3] = (byte)Math.max(Math.min(n2 + this.c * ((array2[n3] & 0xFF) - n2) / this.f, 255), 0);
                            array3[n3] = (byte)Math.max(Math.min(n2 + this.c * ((array3[n3] & 0xFF) - n2) / this.f, 255), 0);
                        }
                    }
                    this.ak = new IndexColorModel(8, l.length, array, array2, array3);
                }
                if (b) {
                    return this.ak;
                }
                return this.aj;
            }
        }
        if (this.a1.am > 8 || this.cf) {
            this.a2 = this.ah;
            this.a3 = this.ai;
        }
        else if (this.a1.am == 8) {
            this.a2 = this.ad;
            this.a3 = this.ae;
        }
        else if (this.a1.am == 4) {
            this.a2 = this.af;
            this.a3 = this.ag;
        }
        else if (this.a0 == 1) {
            this.a2 = this.ad;
            this.a3 = this.ae;
        }
        else if (this.a0 == 0) {
            this.a2 = this.ab;
            this.a3 = this.ac;
        }
        if (b) {
            return this.a3;
        }
        return this.a2;
    }
    
    public void a(final int b) {
        this.b = b;
        if (this.b > ji.util.d.dv) {
            this.c = 25 * (this.b - ji.util.d.dv) / ji.util.d.d1;
            if (this.c < 0) {
                this.c *= -this.c;
            }
            else {
                this.c *= this.c;
            }
            this.c = Math.max(Math.min(this.c + this.b, 2 * ji.util.d.d1), 0);
        }
        else {
            this.c = this.b;
        }
        this.f();
    }
    
    public void b(final int d) {
        this.d = d;
        this.f();
    }
    
    public void g(final boolean bh) {
        this.bh = bh;
    }
    
    public int q() {
        return this.b;
    }
    
    public void c(final int a) {
        this.a = a;
        this.f();
        this.w();
    }
    
    public int r() {
        return this.a;
    }
    
    public int s() {
        return this.d;
    }
    
    public void finalize() {
    }
}
