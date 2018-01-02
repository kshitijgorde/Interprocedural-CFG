// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import ji.awt.o6;
import ji.awt.dc;
import java.util.Enumeration;
import ji.io.q;
import ji.document.cw;
import ji.document.ad;
import ji.awt.d5;
import ji.awt.da;
import ji.util.i;
import ji.util.d;
import ji.awt.c;
import java.util.Hashtable;
import ji.filter.ck;
import java.awt.Dimension;

public class dx implements Cloneable
{
    public int a;
    private static int b;
    public int c;
    public boolean d;
    public boolean e;
    public String f;
    public String g;
    public String h;
    public long i;
    public long j;
    public long k;
    public String l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public long s;
    public String t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public int ab;
    public double ac;
    public double ad;
    public double ae;
    public double af;
    public boolean ag;
    public boolean ah;
    public double ai;
    private int aj;
    public boolean ak;
    public int al;
    public int am;
    public int an;
    public int ao;
    public int ap;
    public boolean aq;
    public boolean ar;
    public boolean as;
    public int at;
    public short[] au;
    public int av;
    public int aw;
    public Object ax;
    public boolean ay;
    public boolean az;
    public boolean a0;
    public int a1;
    public boolean a2;
    public String a3;
    public int a4;
    public int a5;
    public String a6;
    public String a7;
    public String a8;
    public int a9;
    public Dimension ba;
    public long bb;
    public int bc;
    public long bd;
    public long be;
    public long bf;
    public long bg;
    public int bh;
    public int bi;
    public ck bj;
    public Hashtable bk;
    public Hashtable bl;
    public boolean bm;
    public boolean bn;
    public String bo;
    public String bp;
    public String bq;
    public int br;
    public boolean bs;
    public long bt;
    public String bu;
    public int bv;
    private double bw;
    private double bx;
    private int by;
    private int bz;
    public String b0;
    public int b1;
    public int b2;
    public int b3;
    public int b4;
    public int b5;
    public int b6;
    public int b7;
    public boolean b8;
    public int b9;
    public int ca;
    public ev cb;
    public String cc;
    public dx cd;
    public int ce;
    public double cf;
    private int cg;
    private int ch;
    private int ci;
    private int cj;
    public long ck;
    public long cl;
    public double cm;
    public int cn;
    public int co;
    public int[] cp;
    public int[] cq;
    public int cr;
    public boolean cs;
    public ds ct;
    public int cu;
    public c cv;
    public int cw;
    public int cx;
    public int cy;
    public int cz;
    public int c0;
    public int c1;
    public boolean c2;
    
    public dx() {
        this.a = 0;
        this.c = 0;
        this.d = true;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = 0L;
        this.j = 0L;
        this.k = 0L;
        this.l = null;
        this.m = 0;
        this.n = 0;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0L;
        this.t = null;
        this.u = 0;
        this.v = 0;
        this.w = 1;
        this.x = 0;
        this.y = 0;
        this.z = 1;
        this.aa = 1;
        this.ab = -1;
        this.ac = 100.0;
        this.ad = 100.0;
        this.ae = 100.0;
        this.af = 100.0;
        this.ag = false;
        this.ah = false;
        this.ai = -1.0;
        this.aj = 1;
        this.ak = false;
        this.al = -1;
        this.am = 1;
        this.an = 0;
        this.ao = 0;
        this.ap = 0;
        this.aq = false;
        this.ar = false;
        this.as = false;
        this.at = 0;
        this.au = null;
        this.av = 0;
        this.aw = 0;
        this.ax = null;
        this.ay = false;
        this.az = false;
        this.a0 = false;
        this.a1 = 0;
        this.a2 = false;
        this.a3 = null;
        this.a4 = 0;
        this.a5 = 0;
        this.a6 = "Undefined";
        this.a7 = "Undefined";
        this.a8 = "";
        this.a9 = 0;
        this.ba = new Dimension(0, 0);
        this.bb = 0L;
        this.bc = 0;
        this.bd = 0L;
        this.be = 0L;
        this.bh = 0;
        this.bi = 0;
        this.bj = null;
        this.bk = null;
        this.bl = null;
        this.bm = false;
        this.bn = false;
        this.bo = null;
        this.bp = null;
        this.bq = null;
        this.br = 0;
        this.bs = true;
        this.bt = 0L;
        this.bu = null;
        this.bv = 1;
        this.bw = 0.0;
        this.bx = 0.0;
        this.by = 0;
        this.bz = 0;
        this.b0 = null;
        this.b1 = 0;
        this.b2 = 0;
        this.b3 = 0;
        this.b4 = 0;
        this.b5 = 0;
        this.b6 = 0;
        this.b7 = 0;
        this.b8 = false;
        this.b9 = 0;
        this.ca = 0;
        this.cb = new ev();
        this.cc = null;
        this.cd = null;
        this.ce = 0;
        this.cf = 0.0;
        this.cg = 0;
        this.ch = 0;
        this.ci = 0;
        this.cj = 0;
        this.ck = 0L;
        this.cl = 0L;
        this.cm = 0.0;
        this.cn = -1;
        this.co = -1;
        this.cp = null;
        this.cq = null;
        this.cr = -1;
        this.cs = false;
        this.ct = null;
        this.cu = 0;
        this.cv = new c("jiImageHeaderTempFiles");
        this.cw = 0;
        this.cx = 0;
        this.cy = 0;
        this.cz = 0;
        this.c0 = 0;
        this.c1 = 0;
        this.c2 = true;
        this.c = dx.b++;
    }
    
    public dx a(final boolean b) {
        final dx dx = new dx();
        dx.a = this.a;
        dx.d = this.d;
        dx.e = this.e;
        dx.f = this.f;
        dx.g = this.g;
        dx.h = this.h;
        dx.i = this.i;
        dx.j = this.j;
        dx.k = this.k;
        dx.l = this.l;
        dx.m = this.m;
        dx.n = this.n;
        dx.o = this.o;
        dx.p = this.p;
        dx.q = this.q;
        dx.r = this.r;
        dx.s = this.s;
        dx.t = this.t;
        dx.u = this.u;
        dx.v = this.v;
        dx.w = this.w;
        dx.x = this.x;
        dx.y = this.y;
        dx.z = this.z;
        dx.aa = this.aa;
        dx.ab = this.ab;
        dx.ac = this.ac;
        dx.ad = this.ad;
        dx.ae = this.ae;
        dx.af = this.af;
        dx.ag = this.ag;
        dx.aj = this.aj;
        dx.ak = this.ak;
        dx.al = this.al;
        dx.am = this.am;
        dx.an = this.an;
        dx.ao = this.ao;
        dx.ap = this.ap;
        dx.aq = this.aq;
        dx.ar = this.ar;
        dx.as = this.as;
        dx.at = this.at;
        dx.au = this.au;
        dx.av = this.av;
        dx.aw = this.aw;
        dx.ax = this.ax;
        dx.ay = this.ay;
        dx.az = this.az;
        dx.a0 = this.a0;
        dx.a1 = this.a1;
        dx.a2 = this.a2;
        dx.a3 = this.a3;
        dx.a4 = this.a4;
        dx.a5 = this.a5;
        dx.a6 = this.a6;
        dx.a7 = this.a7;
        dx.a8 = this.a8;
        dx.a9 = this.a9;
        dx.ba = this.ba;
        dx.bb = this.bb;
        dx.bc = this.bc;
        dx.bd = this.bd;
        dx.be = this.be;
        dx.bf = this.bf;
        dx.bg = this.bg;
        dx.bh = this.bh;
        dx.bi = this.bi;
        dx.bj = this.bj;
        if (this.bk != null && b) {
            dx.bk = (Hashtable)this.bk.clone();
        }
        else {
            dx.bk = this.bk;
        }
        dx.bl = this.bl;
        dx.bm = this.bm;
        dx.bn = this.bn;
        dx.bo = this.bo;
        dx.bp = this.bp;
        dx.bq = this.bq;
        dx.br = this.br;
        dx.bs = this.bs;
        dx.bt = this.bt;
        dx.bu = this.bu;
        dx.bv = this.bv;
        dx.bw = this.bw;
        dx.bx = this.bx;
        dx.by = this.by;
        dx.bz = this.bz;
        dx.b0 = this.b0;
        dx.b1 = this.b1;
        dx.b2 = this.b2;
        dx.b3 = this.b3;
        dx.b4 = this.b4;
        dx.b5 = this.b5;
        dx.b6 = this.b6;
        dx.b7 = this.b7;
        dx.b8 = this.b8;
        dx.b9 = this.b9;
        dx.ca = this.ca;
        if (this.cb != null) {
            dx.cb.a(this.cb);
        }
        dx.ce = this.ce;
        dx.cf = this.cf;
        dx.cg = this.cg;
        dx.ch = this.ch;
        dx.ci = this.ci;
        dx.cj = this.cj;
        dx.cc = this.cc;
        dx.ck = this.ck;
        dx.cl = this.cl;
        dx.cm = this.cm;
        dx.cd = this.cd;
        dx.cn = this.cn;
        dx.co = this.co;
        dx.cp = this.cp;
        dx.cq = this.cq;
        dx.cr = this.cr;
        dx.cs = this.cs;
        dx.ct = this.ct;
        dx.cu = this.cu;
        dx.cv = this.cv;
        dx.cw = this.cw;
        dx.cx = this.cx;
        dx.cy = this.cy;
        dx.cz = this.cz;
        dx.c0 = this.c0;
        dx.c1 = this.c1;
        dx.c2 = this.c2;
        return dx;
    }
    
    public final boolean a() {
        return this.i() && (this.bt > 0 || !ji.util.d.by(this.bu));
    }
    
    public final void a(final int aj) {
        this.aj = aj;
    }
    
    public final void b() {
        this.aj = 1;
    }
    
    public final boolean c() {
        return ji.util.i.c(224) && this.aj != 1;
    }
    
    public final int d() {
        if (!ji.util.i.c(224)) {
            return 1;
        }
        return this.aj;
    }
    
    public final da a(final da da) {
        if (!this.c() && this.ac == this.ad) {
            return da;
        }
        final da da2 = new da();
        da2.a = this.a(da.a);
        da2.b = this.b(da.b);
        da2.c = this.a(da.c);
        da2.d = this.b(da.d);
        return da2;
    }
    
    public final d5 a(final d5 d5) {
        if (!this.c() && this.ac == this.ad) {
            return d5;
        }
        final d5 d6 = new d5();
        d6.a = this.a(d5.a);
        d6.b = this.b(d5.b);
        return d6;
    }
    
    public final long a(long n) {
        if (this.c()) {
            n /= this.d();
        }
        if (this.ac < this.ad) {
            n *= (long)(this.ac / this.ad);
        }
        return n;
    }
    
    public final long b(long n) {
        if (this.c()) {
            n /= this.d();
        }
        if (this.ac > this.ad) {
            n *= (long)(this.ad / this.ac);
        }
        return n;
    }
    
    public final double a(double n) {
        if (this.c()) {
            n /= this.d();
        }
        if (this.ac > this.ad) {
            n *= this.ac / this.ad;
        }
        return n;
    }
    
    public final long c(final long n) {
        if (this.c()) {
            return n / this.d();
        }
        return n;
    }
    
    public final long d(long n) {
        if (this.c()) {
            n *= this.d();
        }
        if (this.ac < this.ad) {
            n *= (long)(this.ad / this.ac);
        }
        return n;
    }
    
    public final long e(long n) {
        if (this.c()) {
            n *= this.d();
        }
        return n;
    }
    
    public final d5 b(final d5 d5) {
        if (!this.c() && this.ac == this.ad) {
            return d5;
        }
        final d5 d6 = new d5();
        d6.a = this.d(d5.a);
        d6.b = this.e(d5.b);
        return d6;
    }
    
    public final void e() {
        this.f = null;
        this.g = null;
        this.h = null;
        this.l = null;
        this.au = null;
        this.ax = null;
        this.ba = null;
        this.bj = null;
        this.bk = null;
        this.bl = null;
        this.cb.a = null;
        this.cd = null;
        this.cp = null;
        this.cq = null;
        this.ct = null;
        this.cv = null;
    }
    
    public void a(final Object o, final String s) {
        try {
            if (o instanceof ad) {
                if (((ad)o).a(this)) {
                    return;
                }
            }
            else if (o instanceof cw && ((cw)o).a(this)) {
                return;
            }
            if (this.bk != null) {
                this.bk.clear();
            }
            if (this.bl != null) {
                this.bl.clear();
            }
            if (this.cv != null) {
                while (this.cv.b() > 0) {
                    final String s2 = (String)this.cv.b(0);
                    this.cv.d(0);
                    try {
                        ji.io.q.a(o, s).d(s2);
                    }
                    catch (Exception ex) {}
                }
            }
            this.e();
        }
        catch (Exception ex2) {}
    }
    
    public String toString() {
        String s = String.valueOf(String.valueOf(new StringBuffer("Header (").append(this.c).append("):\n \n")));
        try {
            final Hashtable bk = this.bk;
            if (bk != null) {
                final Enumeration<Object> keys = bk.keys();
                while (keys.hasMoreElements()) {
                    final String string = keys.nextElement().toString();
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(string).append(" = ").append(bk.get(string)).append("\n")));
                }
            }
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" \nProcessed:").append(" \n \n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Compression = ").append(this.ap).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("BitsPerSample = ").append(this.z).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("FileLength = ").append(this.i).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Filename = ").append(this.f).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Format = ").append(this.l).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Height = ").append(this.n).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Width = ").append(this.m).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("NumPages = ").append(this.u).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("PixelDepth = ").append(this.am).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("SamplesPerPixel = ").append(this.aa).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Predictor = ").append(this.aw).append("\n")));
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Filter = ").append(this.bj).append("\n")));
            if (ji.util.d.bn()) {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("xResolution = ").append(this.ac).append("\n")));
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("yResolution = ").append(this.ad).append("\n")));
            }
            if (this.a3 != null && this.a3.length() > 0) {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("Content = <").append(this.a3).append(">\n")));
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public final boolean f() {
        return this.a == 2;
    }
    
    public final boolean g() {
        return this.a == 1;
    }
    
    public final boolean h() {
        return this.a == 5;
    }
    
    public final boolean i() {
        return this.a == 7;
    }
    
    public final dc j() {
        return new dc(this.c() ? this.o : this.m, this.c() ? this.p : this.n);
    }
    
    public final o6 k() {
        return new o6(this.c() ? this.ae : this.ac, this.c() ? this.af : this.ad);
    }
    
    public final o6 l() {
        final dc j = this.j();
        final o6 k = this.k();
        return new o6(j.a / k.b(), j.b / k.a());
    }
    
    public final int m() {
        if (this.by > 0) {
            return this.by;
        }
        return (int)(this.bw * this.k().b());
    }
    
    public final int n() {
        if (this.bz > 0) {
            return this.bz;
        }
        return (int)(this.bx * this.k().a());
    }
    
    public final void a(final double bw, final double bx) {
        this.bw = bw;
        this.bx = bx;
    }
    
    public final void a(final int by, final int bz) {
        this.by = by;
        this.bz = bz;
    }
    
    public final void b(final int ce) {
        this.ce = ce;
    }
    
    public final int o() {
        return this.ce / this.d();
    }
    
    public final int p() {
        return this.ce;
    }
    
    public final void a(final int cg, final int ci, final int ch, final int cj) {
        this.cg = cg;
        this.ci = ci;
        this.ch = ch;
        this.cj = cj;
    }
    
    public final int q() {
        return this.cg / this.d();
    }
    
    public final int r() {
        return this.ci / this.d();
    }
    
    public final int s() {
        return this.ch / this.d();
    }
    
    public final int t() {
        return this.cj / this.d();
    }
    
    public double u() {
        if (this.ai != -1) {
            return this.ai;
        }
        return this.ac;
    }
    
    static {
        dx.b = 0;
    }
}
