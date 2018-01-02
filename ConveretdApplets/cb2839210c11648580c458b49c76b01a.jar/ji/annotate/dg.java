// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.awt.ax;
import java.util.StringTokenizer;
import ji.res.ay;
import ji.util.c3;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import ji.io.ac;
import ji.image.fl;
import ji.font.go;
import java.awt.Polygon;
import ji.res.s;
import ji.wang.ej;
import java.awt.Font;
import ji.util.e;
import java.awt.Point;
import ji.document.bd;
import ji.awt.d5;
import ji.util.d0;
import ji.io.h;
import ji.image.dx;
import ji.v1event.af;
import ji.document.ad;
import java.awt.Component;
import ji.awt.dc;
import ji.util.d;
import ji.util.i;
import ji.font.j;
import ji.image.du;
import ji.image.dw;
import ji.render.c1;
import java.util.Hashtable;
import ji.secure.dh;
import ji.font.ct;
import java.awt.Rectangle;
import java.awt.Image;
import ji.font.d1;
import ji.io.q;
import java.awt.Dimension;
import java.util.GregorianCalendar;
import ji.awt.c;
import ji.awt.d4;
import ji.awt.da;
import java.awt.Color;

public class dg
{
    public static final Color a;
    public static final Color b;
    private static final int[] c;
    private static final int[] d;
    private static final String[] e;
    private static final String[] f;
    private static int g;
    private static int h;
    private static int i;
    private long j;
    private long k;
    private long l;
    private int m;
    private int n;
    private static int o;
    private long p;
    private long q;
    private long r;
    private long s;
    private long t;
    private long u;
    private long v;
    private long w;
    private long x;
    private long y;
    private long z;
    private long aa;
    private double ab;
    private double ac;
    private boolean ad;
    private boolean ae;
    private boolean af;
    private boolean ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private boolean al;
    private boolean am;
    private boolean an;
    private boolean ao;
    private int ap;
    private int aq;
    private boolean ar;
    private boolean as;
    private boolean at;
    private boolean au;
    private boolean av;
    private double aw;
    private boolean ax;
    private double ay;
    private boolean az;
    private boolean a0;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private int a4;
    private double a5;
    private Color a6;
    private Color a7;
    private Color a8;
    private Color a9;
    private Color ba;
    private Color bb;
    private Color bc;
    private Color bd;
    private Color be;
    private Color bf;
    private int bg;
    private int bh;
    private int bi;
    private da bj;
    private d4 bk;
    private d4 bl;
    private String bm;
    private String bn;
    private String bo;
    private da bp;
    private da bq;
    private da br;
    private c bs;
    private c bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private boolean by;
    private boolean bz;
    private boolean b0;
    private double b1;
    private double b2;
    private String b3;
    private GregorianCalendar b4;
    private String b5;
    private String b6;
    private GregorianCalendar b7;
    private String b8;
    private String b9;
    private Dimension ca;
    private boolean cb;
    private Dimension cc;
    private String cd;
    private String ce;
    private String cf;
    private String cg;
    private String ch;
    private boolean ci;
    private q cj;
    private String ck;
    private int cl;
    private Object cm;
    private d1 cn;
    private Image co;
    private Image cp;
    private int cq;
    private int cr;
    private boolean cs;
    private boolean ct;
    private boolean cu;
    private long cv;
    private long cw;
    private double cx;
    private boolean cy;
    private int cz;
    private Rectangle c0;
    private boolean c1;
    private boolean c2;
    private int c3;
    private boolean c4;
    private int c5;
    private int c6;
    private int c7;
    private Dimension c8;
    private int c9;
    private int da;
    private boolean db;
    private ct dc;
    private ct dd;
    private double de;
    private boolean df;
    private double dg;
    private double dh;
    private double di;
    private boolean dj;
    private boolean dk;
    private boolean dl;
    private dh dm;
    private dh dn;
    private int[] do;
    private String dp;
    private d3 dq;
    private da dr;
    private boolean ds;
    private long dt;
    private long du;
    private long dv;
    private long dw;
    private String dx;
    private static Hashtable dy;
    private long dz;
    private long d0;
    private long d1;
    private long d2;
    private long d3;
    private long d4;
    private long d5;
    private long d6;
    private double d7;
    private d4 d8;
    private int[] d9;
    private int[] ea;
    private int[] eb;
    private int[] ec;
    private String[] ed;
    private d4[] ee;
    private boolean ef;
    private boolean eg;
    private boolean eh;
    private double ei;
    private boolean ej;
    private int ek;
    private double el;
    private boolean em;
    private boolean en;
    private ed eo;
    private c1 ep;
    private String eq;
    private dw er;
    private du es;
    private int et;
    private int eu;
    private boolean ev;
    private boolean ew;
    private String[] ex;
    private boolean ey;
    private boolean ez;
    private boolean e0;
    private int e1;
    private double e2;
    private int e3;
    private boolean e4;
    private long e5;
    private double e6;
    private double e7;
    private Rectangle e8;
    private boolean e9;
    private boolean fa;
    private boolean fb;
    private boolean fc;
    private Color fd;
    private Color fe;
    private String ff;
    private boolean fg;
    private boolean fh;
    private boolean fi;
    private boolean fj;
    private boolean fk;
    private boolean fl;
    private u2 fm;
    private String fn;
    private c fo;
    public static double fp;
    private double fq;
    private double fr;
    private double fs;
    private double ft;
    private int fu;
    private c fv;
    private Rectangle fw;
    private boolean fx;
    private int fy;
    private int fz;
    private String f0;
    private Rectangle f1;
    private Rectangle f2;
    private int f3;
    private int f4;
    private Dimension f5;
    private int f6;
    private boolean f7;
    private boolean f8;
    private String f9;
    private int ga;
    private int gb;
    private int gc;
    private long gd;
    private long ge;
    private long gf;
    private long gg;
    private long gh;
    private long gi;
    private boolean gj;
    private int gk;
    private int gl;
    private int gm;
    private int gn;
    private boolean go;
    private boolean gp;
    private boolean gq;
    private long gr;
    private long gs;
    private c gt;
    private ct gu;
    private long gv;
    private long gw;
    private int gx;
    private boolean gy;
    private int gz;
    private int g0;
    private boolean g1;
    private boolean g2;
    private boolean g3;
    private d4 g4;
    private d4 g5;
    private d4 g6;
    private Rectangle g7;
    private Color g8;
    private int g9;
    private int ha;
    private int hb;
    private int hc;
    private boolean hd;
    private da he;
    private int hf;
    private boolean hg;
    private boolean hh;
    private boolean hi;
    private dw hj;
    private dw hk;
    private boolean hl;
    private boolean hm;
    private String hn;
    private boolean ho;
    private boolean hp;
    private boolean hq;
    private da hr;
    private boolean hs;
    private boolean ht;
    private boolean hu;
    boolean hv;
    private boolean hw;
    private int hx;
    private boolean hy;
    private boolean hz;
    String[] h0;
    private double h1;
    private int h2;
    
    private void gn() {
        if (this.dc == null) {
            this.dc = ji.font.j.h().c();
        }
        if (this.dd == null) {
            this.dd = ji.font.j.h().c();
        }
    }
    
    public final void a(final da da) {
        if (ji.util.i.c(99)) {
            if (da != null) {
                if (this.hr != null) {
                    this.hr.a = Math.min(this.hr.a, da.a);
                    this.hr.b = Math.min(this.hr.b, da.b);
                    final long n = this.hr.a + this.hr.c;
                    final long n2 = this.hr.b + this.hr.d;
                    final long n3 = da.a + da.c;
                    final long n4 = da.b + da.d;
                    final long max = Math.max(n, n3);
                    final long max2 = Math.max(n2, n4);
                    this.hr.c = max - this.hr.a;
                    this.hr.d = max2 - this.hr.b;
                }
                else {
                    this.hr = new da(da);
                }
            }
            else {
                this.hr = null;
            }
        }
    }
    
    public final da a() {
        return this.hr;
    }
    
    public final void a(final boolean hm) {
        this.hm = hm;
    }
    
    public final void b(final boolean ad) {
        this.ad = ad;
    }
    
    public final boolean b() {
        return this.ad;
    }
    
    public final boolean c() {
        return this.hm;
    }
    
    public final void c(final boolean hv) {
        this.hv = hv;
    }
    
    public final boolean d() {
        return this.ho;
    }
    
    public final boolean e() {
        return this.hq;
    }
    
    public final void d(final boolean hq) {
        this.hq = hq;
    }
    
    public final void e(final boolean ho) {
        this.ho = ho;
    }
    
    public final boolean f() {
        return this.hp;
    }
    
    public final void f(final boolean hp) {
        this.hp = hp;
    }
    
    public final void g(final boolean hi) {
        this.hi = hi;
    }
    
    public final boolean g() {
        return this.hi;
    }
    
    public final String h() {
        if (ji.util.i.c(67)) {
            return this.hn;
        }
        return "";
    }
    
    public final void a(final String s) {
        if (ji.util.i.c(67)) {
            this.hn = ji.util.d.bc(s);
        }
    }
    
    public final void h(final boolean hs) {
        this.ht = true;
        this.hs = hs;
    }
    
    public boolean i() {
        return this.hs;
    }
    
    public final void i(final boolean hu) {
        this.hu = hu;
    }
    
    public boolean j() {
        return this.hu;
    }
    
    private final void aj(final int n) {
        if (ji.util.i.c(67) && (n == 8 || (ji.util.d.bf() && this.ai(n))) && this.hn == null) {
            this.hn = "".concat(String.valueOf(String.valueOf(ji.util.d.f(this.fn))));
        }
    }
    
    public final void j(final boolean hh) {
        this.hh = hh;
    }
    
    public final boolean k() {
        return this.hh;
    }
    
    public final void k(final boolean hg) {
        this.hg = hg;
    }
    
    public final boolean l() {
        return this.hg;
    }
    
    public final void a(final int hf) {
        this.hf = hf;
    }
    
    public final void b(final da da) {
        if (da != null) {
            this.he = da.c();
        }
        else {
            this.he = null;
        }
    }
    
    public final boolean m() {
        return this.hd;
    }
    
    public final void l(final boolean hd) {
        this.hd = hd;
    }
    
    public final void a(final Color g8) {
        this.g8 = g8;
    }
    
    public final Color n() {
        return this.g8;
    }
    
    public final void a(final int g9, final int hb, final int ha, final int hc) {
        this.g9 = g9;
        this.ha = ha;
        this.hb = hb;
        this.hc = hc;
    }
    
    public final int o() {
        return this.g9;
    }
    
    public final int p() {
        return this.ha;
    }
    
    public final int q() {
        return this.hb;
    }
    
    public final int r() {
        return this.hc;
    }
    
    public final void a(final d4 d4) {
        if (d4 != null) {
            this.g6 = d4.a();
        }
        else {
            this.g6 = null;
        }
    }
    
    public final d4 s() {
        return this.g6;
    }
    
    public final void a(final Rectangle rectangle) {
        if (rectangle != null) {
            this.g7 = new Rectangle(rectangle);
        }
        else {
            this.g7 = null;
        }
    }
    
    public final Rectangle t() {
        return this.g7;
    }
    
    public final void b(final d4 d4) {
        if (d4 != null) {
            this.g4 = d4.a();
        }
        else {
            this.g4 = null;
        }
    }
    
    public final d4 u() {
        return this.g4;
    }
    
    public final void c(final d4 d4) {
        if (d4 != null) {
            this.g5 = d4.a();
        }
        else {
            this.g5 = null;
        }
    }
    
    public final d4 v() {
        return this.g5;
    }
    
    public final void m(final boolean g2) {
        this.g2 = g2;
    }
    
    public final boolean w() {
        return this.g2;
    }
    
    public final void n(final boolean g3) {
        this.g3 = g3;
    }
    
    public final boolean x() {
        return this.g3;
    }
    
    public final void o(final boolean g1) {
        this.g1 = g1;
    }
    
    public final boolean y() {
        return this.g1;
    }
    
    public final void p(final boolean gq) {
        this.gq = gq;
    }
    
    public final boolean z() {
        return this.gq;
    }
    
    public final void a(final long gr) {
        this.gr = gr;
    }
    
    public final long aa() {
        return this.gr;
    }
    
    public final void ab() {
        this.gr = 0L;
    }
    
    public final void b(final long gs) {
        this.gs = gs;
    }
    
    public final long ac() {
        return this.gs;
    }
    
    public final void ad() {
        this.gs = 0L;
    }
    
    public final void q(final boolean go) {
        this.go = go;
    }
    
    public final void r(final boolean c2) {
        this.c2 = c2;
    }
    
    public final void s(final boolean c1) {
        this.c1 = c1;
        if (this.c1) {
            this.c0 = null;
        }
    }
    
    public final boolean ae() {
        return this.c1 || this.c2;
    }
    
    public final void b(final Rectangle c0) {
        if (ji.util.d.ls) {
            this.c0 = c0;
        }
    }
    
    public final Rectangle c(final da da) {
        try {
            if (da == null || this.c1 || this.c2) {
                return this.c0;
            }
            if (this.c0 == null) {
                return da.a();
            }
            if (da.b(new da(this.c0)) != null) {
                return da.b(new da(this.c0)).a();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final boolean b(final int n) {
        return this.cq == 0 && this.e6 == 0 && n == 0;
    }
    
    public final void t(final boolean fx) {
        this.fx = fx;
    }
    
    public final void u(final boolean f7) {
        this.f7 = f7;
    }
    
    public final void c(final int fz) {
        this.fz = fz;
    }
    
    public final int af() {
        return this.fz;
    }
    
    public void b(final String f9) {
        this.f9 = f9;
    }
    
    public String ag() {
        return this.f9;
    }
    
    public final void v(final boolean fl) {
        this.fl = fl;
    }
    
    public final void a(final Dimension dimension) {
        this.f5.width = dimension.width;
        this.f5.height = dimension.height;
    }
    
    public final Dimension ah() {
        return this.f5;
    }
    
    public final Dimension ai() {
        if (this.v > 0 && this.w > 0) {
            return new Dimension((int)this.v, (int)this.w);
        }
        return null;
    }
    
    public final da aj() {
        return new da(this.r, this.s, this.gd, this.ge);
    }
    
    public final void w(final boolean az) {
        this.az = az;
    }
    
    public final boolean ak() {
        return this.az;
    }
    
    public final void al() {
        this.gj = false;
    }
    
    public final void am() {
        this.gj = true;
    }
    
    public final boolean an() {
        return this.gj;
    }
    
    public final void d(final da da) {
        this.r = da.a;
        this.s = da.b;
        this.gd = da.c;
        this.ge = da.d;
    }
    
    public final void a(final int n, final int n2) {
        this.gd = n;
        this.ge = n2;
    }
    
    public final void b(final int n, final int n2) {
        this.gf = this.gd;
        this.gg = this.ge;
    }
    
    public final dc ao() {
        if (this.gf >= 0) {
            return new dc(this.gf, this.gg);
        }
        return null;
    }
    
    public final long ap() {
        return this.gd;
    }
    
    public final long aq() {
        return this.ge;
    }
    
    public final dc ar() {
        return new dc(this.gd, this.ge);
    }
    
    public final boolean as() {
        return this.d4() == 9 && this.at();
    }
    
    public final boolean at() {
        return this.f8 && this.e6 == 0.0;
    }
    
    public final boolean au() {
        return this.f8;
    }
    
    public final void x(final boolean f8) {
        this.f8 = f8;
    }
    
    public final void y(final boolean as) {
        this.as = as;
    }
    
    public final void z(final boolean at) {
        this.at = at;
    }
    
    public final boolean av() {
        return this.a3;
    }
    
    public final void aa(final boolean a3) {
        this.a3 = a3;
    }
    
    public final boolean aw() {
        return this.as | this.at;
    }
    
    public final boolean ax() {
        return this.fl;
    }
    
    public final boolean ay() {
        return this.fx;
    }
    
    public final void d(final int fu) {
        this.fu = fu;
        if (fu == 3 && this.cn != null) {
            this.cn.c(false);
        }
    }
    
    public final int az() {
        return this.fu;
    }
    
    public final void c(final String f0) {
        this.f0 = f0;
    }
    
    public final String a0() {
        return this.f0;
    }
    
    public final boolean a1() {
        final boolean hy = this.hy;
        this.hy = false;
        return hy;
    }
    
    public final boolean a2() {
        if (this.az() != 0 && !this.bh()) {
            if (this.d5() != 9 && this.d5() != 7) {
                return !this.bs();
            }
            if (this.r != -100 && this.s != -100) {
                if (this.d5() != 7 && !this.e9()) {
                    return !this.fk;
                }
                if (this.a3()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public final boolean a3() {
        return ji.util.d.b3(ji.util.d.bc(this.ev())) > 0;
    }
    
    public final void ab(final boolean fh) {
        this.fh = fh;
        if (fh) {
            this.hd = true;
        }
    }
    
    public final boolean a4() {
        return this.fh;
    }
    
    public final void ac(final boolean fi) {
        this.fi = fi;
        if (fi) {
            this.hd = true;
        }
    }
    
    public final boolean a5() {
        return this.fi;
    }
    
    public String a6() {
        return "".concat(String.valueOf(String.valueOf(this.l)));
    }
    
    public void c(final long l) {
        this.l = l;
    }
    
    public String toString() {
        return this.ad(true);
    }
    
    public String ad(final boolean b) {
        String s = null;
        switch (this.fz) {
            case 0: {
                s = " Viewone";
                break;
            }
            case 1: {
                s = " Wang";
                break;
            }
            case 2: {
                s = " PDF";
                break;
            }
        }
        final String value = String.valueOf(String.valueOf(new StringBuffer(" ").append(ji.util.d.b2(ji.util.d.l(this.a9))).append("(").append(ji.util.d.a(this.a9)).append(") ")));
        String value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b2(ji.util.d.l(this.ba))))).append("(").append(ji.util.d.a(this.ba)).append(") ")));
        String s2 = s;
        if (!this.ei()) {
            value2 = "";
        }
        switch (this.n) {
            case 1: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 2: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 3: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 4: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(value)));
                break;
            }
            case 5: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(value)));
                break;
            }
            case 6: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 7: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 8: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 10: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(value)));
                break;
            }
            case 11: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 13: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 12: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 14: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 9: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
            case 16: {
                break;
            }
            case 17: {
                break;
            }
            case 18: {
                break;
            }
            default: {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(value2))))));
                break;
            }
        }
        String go;
        String s3;
        if (b) {
            go = this.go();
            s3 = String.valueOf(String.valueOf(new StringBuffer(" Page ").append(this.cl).append(" (").append(this.j).append("/").append(this.k).append(") - ZOrder=").append(this.bv).append(" ")));
        }
        else {
            go = "";
            s3 = String.valueOf(String.valueOf(new StringBuffer(" Page ").append(this.cl).append(" - ")));
        }
        if (ji.util.d.bg()) {
            s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" - FileNETZOrder=").append(this.bw).append(" "))))));
        }
        switch (this.n) {
            case 1: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.v).append(", ").append(this.w).append(")").append(s2)));
            }
            case 2: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.z).append(", ").append(this.aa).append(")").append(s2)));
            }
            case 3: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.bk).append(s2)));
            }
            case 4: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.t).append(", ").append(this.u).append(")").append(s2)));
            }
            case 5: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.t).append(", ").append(this.u).append(")").append(s2)));
            }
            case 6: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.bk).append(s2)));
            }
            case 7: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.ak).append("> \"").append(this.z(this.b3)).append("\")").append(" (").append(this.r).append(", ").append(this.s).append(")").append(s2)));
            }
            case 8: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(\"").append(this.z(this.b3)).append("\")").append(" (").append(this.r).append(", ").append(this.s).append(")").append(s2)));
            }
            case 10: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.bk).append(s2)));
            }
            case 11: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.v).append(", ").append(this.w).append(")").append(s2)));
            }
            case 13: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append("(").append(this.r).append(", ").append(this.s).append(", ").append(this.v).append(", ").append(this.w).append(")").append(s2)));
            }
            case 12: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.bk).append(s2)));
            }
            case 14: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.bk).append(s2)));
            }
            case 9: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append("- ").append(this.cm).append(" (").append(this.r).append(", ").append(this.s).append(")")));
            }
            case 16: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append(" ")));
            }
            case 17: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append(" ")));
            }
            case 18: {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(this.a(this.n, true)).append(" ").append(go).append(" ")));
            }
            default: {
                return "Unknown Type Page ".concat(String.valueOf(String.valueOf(this.cl)));
            }
        }
    }
    
    public String a7() {
        return "".concat(String.valueOf(String.valueOf(this.l)));
    }
    
    private final String z(String s) {
        try {
            if (!ji.util.d.by(s)) {
                s = ji.util.d.b(s, "\n", "");
                s = ji.util.d.b(s, "\r", "");
                s = String.valueOf(String.valueOf(s.substring(0, Math.min(32, s.length())))).concat("...");
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    private final String go() {
        switch (this.fu) {
            case 0: {
                return "STATUS_NO_CHANGE";
            }
            case 1: {
                return "STATUS_CHANGED";
            }
            case 2: {
                return "STATUS_NEW";
            }
            case 3: {
                return "STATUS_DELETED";
            }
            default: {
                return "STATUS_NO_CHANGE";
            }
        }
    }
    
    public String a(final Component component, final ad ad, final boolean b) {
        String s;
        try {
            final ee ee = new ee(0, this.fn);
            for (s = ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.bd(ee.a(this, 0, component, ad, b, false, false)), "\r\n", "\n"), "\r", "\n"), "\n", ji.util.d.a3()); s.startsWith(ji.util.d.a3()); s = s.substring(ji.util.d.a3().length())) {}
            while (s.endsWith(ji.util.d.a3())) {
                s = s.substring(0, s.length() - ji.util.d.a3().length());
            }
            ee.b();
        }
        catch (Exception ex) {
            s = "<unable to parse> ".concat(String.valueOf(String.valueOf(ex.toString())));
        }
        return "TYPE = ".concat(String.valueOf(String.valueOf(s)));
    }
    
    public void d(final String s) {
        if (!ji.util.d.by(s)) {
            Label_0066: {
                if (ji.util.i.c(238)) {
                    synchronized (this.fm) {
                        this.fm.a = true;
                        this.fm.c(s);
                        // monitorexit(this.fm)
                        break Label_0066;
                    }
                }
                this.fm.a = true;
                this.fm.c(s);
            }
            this.fv.c(s);
        }
    }
    
    public c a8() {
        return this.fv;
    }
    
    public void e(final String s) {
        this.fo.c(s);
    }
    
    public String f(final String s) {
        String substring = null;
        if (s != null) {
            final String concat = String.valueOf(String.valueOf(s.toLowerCase())).concat("=");
            for (int b = this.fo.b(), i = 0; i < b; ++i) {
                final String s2 = (String)this.fo.b(i);
                if (s2.toLowerCase().startsWith(concat)) {
                    substring = s2.substring(concat.length());
                }
            }
        }
        return substring;
    }
    
    public String[] a9() {
        final int b = this.fo.b();
        final String[] array = new String[b];
        for (int i = 0; i < b; ++i) {
            array[i] = (String)this.fo.b(i);
        }
        return array;
    }
    
    public void a(final c c) {
        for (int i = 0; i < c.b(); ++i) {
            this.d((String)c.b(i));
        }
    }
    
    public final boolean ba() {
        return this.ey;
    }
    
    private static String a(final String s, final String s2) {
        String bc = "";
        final int index = s2.indexOf("=");
        if (index >= 0 && ji.util.d.bc(s2.substring(0, index)).toLowerCase().equals(s.toLowerCase())) {
            bc = ji.util.d.bc(s2.substring(index + 1));
        }
        return bc;
    }
    
    public final double bb() {
        if (this.fq >= this.fr) {
            if (ji.util.d.bf() && ji.util.i.c(202)) {
                return this.fs;
            }
            return this.fq;
        }
        else {
            if (ji.util.d.bf() && ji.util.i.c(202)) {
                return this.ft;
            }
            return this.fr;
        }
    }
    
    public final double bc() {
        return this.bb();
    }
    
    public final void a(final Component component, final af af, final dx dx, final String s, final ad ad) {
        if (this.fm != null) {
            if (ji.util.i.c(238)) {
                synchronized (this.fm) {
                    this.b(component, af, dx, s, ad);
                    // monitorexit(this.fm)
                    return;
                }
            }
            this.b(component, af, dx, s, ad);
        }
    }
    
    private void b(final Component component, final af af, final dx dx, final String s, final ad ad) {
        try {
            if (this.fm.a && dx != null) {
                Point point = null;
                int n = 1;
                ji.io.h.a(this.fn, "Parsing extra annotation lines.");
                this.a(dx.ac, dx.ad, dx.a);
                this.a(dx.j().a(), dx.v);
                this.b(new Dimension(dx.m, dx.n), dx.v);
                double bb = this.bb();
                double bc = this.bc();
                if (dx.c()) {
                    bb *= dx.d();
                    bc *= dx.d();
                }
                if (ji.util.d.cs() && this.fm.b() > 0) {
                    ji.util.d.c(this.fn, "Annotation unit translation for: ".concat(String.valueOf(String.valueOf(this))));
                }
                if (ji.util.i.c(204)) {
                    for (int i = 0; i < this.fm.b(); ++i) {
                        final String a = a("linewidth", (String)this.fm.b(i));
                        if (!ji.util.d.by(a)) {
                            this.fm.d(i);
                            this.b(ji.util.d.c(a, 0), bb);
                            break;
                        }
                    }
                }
                else if (dx.c()) {
                    this.v(this.a(dx) * dx.d());
                }
                else {
                    this.v(this.a(dx));
                }
                final boolean b = ji.util.d.bf() && ji.util.i.c(247);
                String s2 = null;
                int c = 12;
                Label_5299: {
                    switch (this.n) {
                        case 1:
                        case 11:
                        case 13: {
                            while (this.fm.b() > 0) {
                                final String s3 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a2 = a("x", s3);
                                if (!ji.util.d.by(a2)) {
                                    this.r = ji.util.d0.a(ji.util.d.a(a2, 0.0), bb);
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X = ").append(a2).append(" inches = ").append(this.r).append(" pixels"))));
                                }
                                else {
                                    final String a3 = a("y", s3);
                                    if (!ji.util.d.by(a3)) {
                                        this.s = ji.util.d0.a(ji.util.d.a(a3, 0.0), bc);
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y = ").append(a3).append(" inches = ").append(this.s).append(" pixels"))));
                                    }
                                    else {
                                        final String a4 = a("width", s3);
                                        if (!ji.util.d.by(a4)) {
                                            this.v = ji.util.d0.a(ji.util.d.a(a4, 0.0), bb);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Width = ").append(a4).append(" inches = ").append(this.v).append(" pixels"))));
                                        }
                                        else {
                                            final String a5 = a("height", s3);
                                            if (ji.util.d.by(a5)) {
                                                continue;
                                            }
                                            this.w = ji.util.d0.a(ji.util.d.a(a5, 0.0), bc);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Height = ").append(a5).append(" inches = ").append(this.w).append(" pixels"))));
                                        }
                                    }
                                }
                            }
                            this.p = this.r;
                            this.q = this.s;
                            break;
                        }
                        case 2: {
                            while (this.fm.b() > 0) {
                                final String s4 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a6 = a("x", s4);
                                if (!ji.util.d.by(a6)) {
                                    this.r = ji.util.d0.a(ji.util.d.a(a6, 0.0), bb);
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X = ").append(a6).append(" inches = ").append(this.r).append(" pixels"))));
                                }
                                else {
                                    final String a7 = a("y", s4);
                                    if (!ji.util.d.by(a7)) {
                                        this.s = ji.util.d0.a(ji.util.d.a(a7, 0.0), bc);
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y = ").append(a7).append(" inches = ").append(this.s).append(" pixels"))));
                                    }
                                    else {
                                        final String a8 = a("width", s4);
                                        if (!ji.util.d.by(a8)) {
                                            this.z = ji.util.d0.a(ji.util.d.a(a8, 0.0), bb);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Width = ").append(a8).append(" inches = ").append(this.z).append(" pixels"))));
                                        }
                                        else {
                                            final String a9 = a("height", s4);
                                            if (ji.util.d.by(a9)) {
                                                continue;
                                            }
                                            this.aa = ji.util.d0.a(ji.util.d.a(a9, 0.0), bc);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Height = ").append(a9).append(" inches = ").append(this.aa).append(" pixels"))));
                                        }
                                    }
                                }
                            }
                            this.p = this.r;
                            this.q = this.s;
                            break;
                        }
                        case 4:
                        case 5: {
                            while (this.fm.b() > 0) {
                                final String s5 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a10 = a("x1", s5);
                                if (!ji.util.d.by(a10)) {
                                    this.r = ji.util.d0.a(ji.util.d.a(a10, 0.0), bb);
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X1 = ").append(a10).append(" inches = ").append(this.r).append(" pixels"))));
                                }
                                else {
                                    final String a11 = a("y1", s5);
                                    if (!ji.util.d.by(a11)) {
                                        this.s = ji.util.d0.a(ji.util.d.a(a11, 0.0), bc);
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y1 = ").append(a11).append(" inches = ").append(this.s).append(" pixels"))));
                                    }
                                    else {
                                        final String a12 = a("x2", s5);
                                        if (!ji.util.d.by(a12)) {
                                            this.t = ji.util.d0.a(ji.util.d.a(a12, 0.0), bb);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X2 = ").append(a12).append(" inches = ").append(this.t).append(" pixels"))));
                                        }
                                        else {
                                            final String a13 = a("y2", s5);
                                            if (ji.util.d.by(a13)) {
                                                continue;
                                            }
                                            this.u = ji.util.d0.a(ji.util.d.a(a13, 0.0), bc);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y2 = ").append(a13).append(" inches = ").append(this.u).append(" pixels"))));
                                        }
                                    }
                                }
                            }
                            this.p = this.r;
                            this.q = this.s;
                            break;
                        }
                        case 7: {
                            while (this.fm.b() > 0) {
                                final String s6 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a14 = a("x", s6);
                                if (!ji.util.d.by(a14)) {
                                    this.r = ji.util.d0.a(ji.util.d.a(a14, 0.0), bb);
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X = ").append(a14).append(" inches = ").append(this.r).append(" pixels"))));
                                }
                                else {
                                    final String a15 = a("y", s6);
                                    if (!ji.util.d.by(a15)) {
                                        this.s = ji.util.d0.a(ji.util.d.a(a15, 0.0), bc);
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y = ").append(a15).append(" inches = ").append(this.s).append(" pixels"))));
                                    }
                                    else {
                                        final String a16 = a("fontheight", s6);
                                        if (!ji.util.d.by(a16)) {
                                            s2 = a16;
                                        }
                                        if (!ji.util.d.by(a16) && !b) {
                                            c = ji.util.d.c(a16, 32);
                                            this.u(c);
                                            try {
                                                this.a(ad, this.fn, af, dx.d());
                                            }
                                            catch (Exception ex) {
                                                ex.printStackTrace();
                                            }
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("FontHeight (internal) = ").append(a16).append(" (original) = ").append(c).append(" pixels"))));
                                        }
                                        else {
                                            if (!this.f8) {
                                                continue;
                                            }
                                            final String a17 = a("height", s6);
                                            if (!ji.util.d.by(a17)) {
                                                this.ge = ji.util.d0.a(ji.util.d.a(a17, 0.0), bc);
                                                if (!ji.util.d.cs()) {
                                                    continue;
                                                }
                                                ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Height = ").append(a17).append(" inches = ").append(this.ge).append(" pixels"))));
                                            }
                                            else {
                                                final String a18 = a("width", s6);
                                                if (ji.util.d.by(a18)) {
                                                    continue;
                                                }
                                                this.gd = ji.util.d0.a(ji.util.d.a(a18, 0.0), bc);
                                                if (!ji.util.d.cs()) {
                                                    continue;
                                                }
                                                ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Width = ").append(a18).append(" inches = ").append(this.gd).append(" pixels"))));
                                            }
                                        }
                                    }
                                }
                            }
                            if (ji.util.d.ao(this.f4)) {}
                            if (this.cq != 0) {
                                this.f8 = false;
                            }
                            if (ji.util.d.bf() && this.f8 && !ji.util.i.c(74) && this.ge < c * 1.2) {
                                this.f8 = false;
                                if (ji.util.d.cs()) {
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Turning off annotation rubber banding because textRubberBandHeight (").append(this.ge).append(") is less than 1.2* fontHeight (").append(c).append(") =").append(1.2 * c))));
                                }
                            }
                            if (b && !ji.util.d.by(s2)) {
                                this.a(component, af, dx, s, ad, s2, bb);
                            }
                            this.cz();
                            this.ej();
                            this.ab(s);
                            this.p = this.r;
                            this.q = this.s;
                            break;
                        }
                        case 8: {
                            this.v = ji.util.d.cu();
                            this.w = ji.util.d.cw();
                            int a19 = 0;
                            int a20 = 0;
                            while (this.fm.b() > 0) {
                                final String s7 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a21 = a("x", s7);
                                if (!ji.util.d.by(a21)) {
                                    a19 = ji.util.d0.a(ji.util.d.a(a21, 0.0), bb);
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X = ").append(a21).append(" inches = ").append(a19).append(" pixels"))));
                                }
                                else {
                                    final String a22 = a("y", s7);
                                    if (!ji.util.d.by(a22)) {
                                        a20 = ji.util.d0.a(ji.util.d.a(a22, 0.0), bc);
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y = ").append(a22).append(" inches = ").append(a20).append(" pixels"))));
                                    }
                                    else {
                                        final String a23 = a("width", s7);
                                        if (!ji.util.d.by(a23)) {
                                            this.v = ji.util.d0.a(ji.util.d.a(a23, 0.0), bb);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Width = ").append(a23).append(" inches = ").append(this.v).append(" pixels"))));
                                        }
                                        else {
                                            final String a24 = a("height", s7);
                                            if (ji.util.d.by(a24)) {
                                                continue;
                                            }
                                            this.w = ji.util.d0.a(ji.util.d.a(a24, 0.0), bc);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Height = ").append(a24).append(" inches = ").append(this.w).append(" pixels"))));
                                        }
                                    }
                                }
                            }
                            this.r = a19 + this.v / 2;
                            this.s = a20 + this.w / 2;
                            this.bk = new d4(this.a(this.r, this.s, this.v, this.w));
                            this.p = this.r;
                            this.q = this.s;
                            break;
                        }
                        case 10: {
                            this.bk = new d4();
                            String s8 = null;
                            String s9 = null;
                            String s10 = null;
                            String s11 = null;
                            for (int j = 0; j < this.fm.b(); ++j) {
                                final String s12 = (String)this.fm.b(j);
                                if (s8 == null) {
                                    final String a25 = a("xoffset", s12);
                                    if (!ji.util.d.by(a25)) {
                                        s8 = a25;
                                        continue;
                                    }
                                }
                                if (s9 == null) {
                                    final String a26 = a("yoffset", s12);
                                    if (!ji.util.d.by(a26)) {
                                        s9 = a26;
                                        continue;
                                    }
                                }
                                if (s10 == null) {
                                    final String a27 = a("width", s12);
                                    if (!ji.util.d.by(a27)) {
                                        s10 = a27;
                                        continue;
                                    }
                                }
                                if (s11 == null) {
                                    final String a28 = a("height", s12);
                                    if (!ji.util.d.by(a28)) {
                                        s11 = a28;
                                    }
                                }
                            }
                            boolean b2 = false;
                            if (ji.util.d.cs()) {
                                ji.util.d.c(this.fn, "X Offset: ".concat(String.valueOf(String.valueOf(s8))));
                                ji.util.d.c(this.fn, "Y Offset: ".concat(String.valueOf(String.valueOf(s9))));
                                ji.util.d.c(this.fn, "Width: ".concat(String.valueOf(String.valueOf(s10))));
                                ji.util.d.c(this.fn, "Height: ".concat(String.valueOf(String.valueOf(s11))));
                            }
                            if (s8 != null && s9 != null && s10 != null && s11 != null) {
                                b2 = true;
                            }
                            if (ji.util.d.cs()) {
                                if (b2) {
                                    ji.util.d.c(this.fn, "Using relative calculations...");
                                }
                                else {
                                    ji.util.d.c(this.fn, "Using absolute calculations...");
                                }
                            }
                            double a29 = -1.0;
                            double a30 = -1.0;
                            final double n2 = -1.0;
                            final double n3 = -1.0;
                            double n4 = 1.0;
                            double n5 = 1.0;
                            if (b2) {
                                a29 = ji.util.d.a(s8, a29);
                                a30 = ji.util.d.a(s9, a30);
                                final double a31 = ji.util.d.a(s10, n2);
                                final double a32 = ji.util.d.a(s11, n3);
                                if (ji.util.d.cs()) {
                                    ji.util.d.c(this.fn, "(2) X Offset: ".concat(String.valueOf(String.valueOf(a29))));
                                    ji.util.d.c(this.fn, "(2) Y Offset: ".concat(String.valueOf(String.valueOf(a30))));
                                    ji.util.d.c(this.fn, "(2) Width: ".concat(String.valueOf(String.valueOf(a31))));
                                    ji.util.d.c(this.fn, "(2) Height: ".concat(String.valueOf(String.valueOf(a32))));
                                }
                                n4 = a31 / 256.0;
                                n5 = a32 / 256.0;
                                if (ji.util.d.cs()) {
                                    ji.util.d.c(this.fn, "X cell Size: ".concat(String.valueOf(String.valueOf(n4))));
                                    ji.util.d.c(this.fn, "Y cell size: ".concat(String.valueOf(String.valueOf(n5))));
                                    ji.util.d.c(this.fn, "X res: ".concat(String.valueOf(String.valueOf(bb))));
                                    ji.util.d.c(this.fn, "Y res: ".concat(String.valueOf(String.valueOf(bc))));
                                }
                                for (int k = 0; k < 4; ++k) {
                                    for (int l = 0; l < this.fm.b(); ++l) {
                                        final String s13 = (String)this.fm.b(l);
                                        if (!ji.util.d.by(a("xoffset", s13))) {
                                            this.fm.d(l);
                                            break;
                                        }
                                        if (!ji.util.d.by(a("yoffset", s13))) {
                                            this.fm.d(l);
                                            break;
                                        }
                                        if (!ji.util.d.by(a("width", s13))) {
                                            this.fm.d(l);
                                            break;
                                        }
                                        if (!ji.util.d.by(a("height", s13))) {
                                            this.fm.d(l);
                                            break;
                                        }
                                    }
                                }
                            }
                            int n6 = 1;
                            while (true) {
                                final String concat = "x".concat(String.valueOf(String.valueOf(n6)));
                                final String concat2 = "y".concat(String.valueOf(String.valueOf(n6)));
                                if (this.fm.b() == 0) {
                                    break Label_5299;
                                }
                                String a33 = null;
                                String a34 = null;
                                for (int n7 = 0; n7 < this.fm.b(); ++n7) {
                                    a33 = a(concat, (String)this.fm.b(n7));
                                    if (!ji.util.d.by(a33)) {
                                        this.fm.d(n7);
                                        break;
                                    }
                                }
                                for (int n8 = 0; n8 < this.fm.b(); ++n8) {
                                    a34 = a(concat2, (String)this.fm.b(n8));
                                    if (!ji.util.d.by(a34)) {
                                        this.fm.d(n8);
                                        break;
                                    }
                                }
                                if (ji.util.d.by(a33)) {
                                    break Label_5299;
                                }
                                if (ji.util.d.by(a34)) {
                                    break Label_5299;
                                }
                                if (b2) {
                                    final Point a35 = ji.util.d0.a(a29 + n4 * ji.util.d.c(a33, 0), a30 + n5 * ji.util.d.c(a34, 0), bb, bc);
                                    if (point != null && a35.x == point.x && a35.y == point.y) {
                                        if (ji.util.d.cs()) {
                                            ji.util.d.c(this.fn, "ignoring point because it is same last one");
                                        }
                                    }
                                    else {
                                        this.bk.a(a35);
                                    }
                                    point = a35;
                                    if (ji.util.d.cs()) {
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat.toUpperCase()))).append(" = ").append(a35.x).append(" pixels (parsed from ").append(a33).append(")"))));
                                    }
                                    if (ji.util.d.cs()) {
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat2.toUpperCase()))).append(" = ").append(a35.y).append(" pixels (parsed from ").append(a34).append(")"))));
                                    }
                                }
                                else {
                                    final Point a36 = ji.util.d0.a(ji.util.d.a(a33, 0.0), ji.util.d.a(a34, 0.0), bb, bc);
                                    if (ji.util.d.cs()) {
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat.toUpperCase()))).append(" = ").append(a33).append(" inches = ").append(a36.x).append(" pixels (parsed from ").append(a33).append(")"))));
                                    }
                                    if (ji.util.d.cs()) {
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat2.toUpperCase()))).append(" = ").append(a33).append(" inches = ").append(a36.y).append(" pixels (parsed from ").append(a34).append(")"))));
                                    }
                                    this.bk.a(a36);
                                }
                                ++n6;
                            }
                            break;
                        }
                        case 3:
                        case 6:
                        case 12:
                        case 14: {
                            this.bk = new d4();
                            while (true) {
                                final String concat3 = "x".concat(String.valueOf(String.valueOf(n)));
                                final String concat4 = "y".concat(String.valueOf(String.valueOf(n)));
                                if (this.fm.b() == 0) {
                                    break Label_5299;
                                }
                                String a37 = null;
                                String a38 = null;
                                for (int n9 = 0; n9 < this.fm.b(); ++n9) {
                                    a37 = a(concat3, (String)this.fm.b(n9));
                                    if (!ji.util.d.by(a37)) {
                                        this.fm.d(n9);
                                        break;
                                    }
                                }
                                for (int n10 = 0; n10 < this.fm.b(); ++n10) {
                                    a38 = a(concat4, (String)this.fm.b(n10));
                                    if (!ji.util.d.by(a38)) {
                                        this.fm.d(n10);
                                        break;
                                    }
                                }
                                if (ji.util.d.by(a37)) {
                                    break Label_5299;
                                }
                                if (ji.util.d.by(a38)) {
                                    break Label_5299;
                                }
                                d5 d5;
                                try {
                                    d5 = new d5(Integer.parseInt(a37), Integer.parseInt(a38));
                                }
                                catch (Exception ex3) {
                                    break Label_5299;
                                }
                                if (ji.util.d.cs()) {
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat3.toUpperCase()))).append(" = ").append(a37))));
                                }
                                if (ji.util.d.cs()) {
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat4.toUpperCase()))).append(" = ").append(a38))));
                                }
                                this.bk.a(dx.b(d5));
                                ++n;
                            }
                            break;
                        }
                        case 9: {
                            Integer n11 = null;
                            Integer n12 = null;
                            while (this.fm.b() > 0) {
                                final String s14 = (String)this.fm.b(0);
                                this.fm.d(0);
                                final String a39 = a("width", s14);
                                if (!ji.util.d.by(a39)) {
                                    n11 = new Integer(ji.util.d0.a(ji.util.d.a(a39, 0.0), bb));
                                    if (!ji.util.d.cs()) {
                                        continue;
                                    }
                                    ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Width = ").append(a39).append(" inches = ").append(this.v).append(" pixels"))));
                                }
                                else {
                                    final String a40 = a("height", s14);
                                    if (!ji.util.d.by(a40)) {
                                        n12 = new Integer(ji.util.d0.a(ji.util.d.a(a40, 0.0), bc));
                                        if (!ji.util.d.cs()) {
                                            continue;
                                        }
                                        ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Height = ").append(a40).append(" inches = ").append(this.w).append(" pixels"))));
                                    }
                                    else {
                                        final String a41 = a("fontheight", s14);
                                        if (!ji.util.d.by(a41)) {
                                            s2 = a41;
                                        }
                                        final String a42 = a("x", s14);
                                        if (!ji.util.d.by(a42)) {
                                            this.r = ji.util.d0.a(ji.util.d.a(a42, 0.0), bb);
                                            if (!ji.util.d.cs()) {
                                                continue;
                                            }
                                            ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("X = ").append(a42).append(" inches = ").append(this.r).append(" pixels"))));
                                        }
                                        else {
                                            final String a43 = a("y", s14);
                                            if (!ji.util.d.by(a43)) {
                                                this.s = ji.util.d0.a(ji.util.d.a(a43, 0.0), bc);
                                                if (!ji.util.d.cs()) {
                                                    continue;
                                                }
                                                ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("Y = ").append(a43).append(" inches = ").append(this.s).append(" pixels"))));
                                            }
                                            else {
                                                final String a44 = a("resource", s14);
                                                if (ji.util.d.by(a44)) {
                                                    continue;
                                                }
                                                String u = null;
                                                final Object jf = ad.jf();
                                                if (jf != null) {
                                                    u = ji.util.d.u(jf.toString(), a44);
                                                }
                                                this.a(component, u, af);
                                            }
                                        }
                                    }
                                }
                            }
                            this.cz();
                            this.ej();
                            this.ab(s);
                            if (b && !ji.util.d.by(s2)) {
                                this.a(component, af, dx, s, ad, s2, bb);
                            }
                            if (n12 != null) {
                                boolean b3 = true;
                                if (n11 == null || n12 == null) {
                                    b3 = false;
                                }
                                if (b3 && n11 > 0 && n12 > 0) {
                                    if (ji.util.d.cs()) {
                                        ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Using stamp width and height ( ").append(n11).append(", ").append(n12).append(")..."))));
                                    }
                                    this.d(n11, n12);
                                    this.ao(false);
                                }
                                else {
                                    ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("*** Warning: Annotaion width/height for stamp is invalid and will be ignored (width=").append(n11).append(", height=").append(n12).append(")"))));
                                }
                            }
                            else {
                                ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("*** Warning: Annotaion height for stamp is missing and so width will be ignored (width=").append(n11).append(")"))));
                            }
                            this.p = this.r;
                            this.q = this.s;
                        }
                    }
                }
                ji.io.h.a(this.fn, "Finished parsing extra annotation lines.");
                this.fm.a = false;
            }
            if (dx != null && !this.fm.b) {
                if (ad.bi(6) && !ad.bi(ji.document.bd.a) && !this.em) {
                    final int a45 = a(this, dx, this.fn, ad);
                    if (a45 > 1) {
                        a(this, a45, dx, af, ad, this.fn);
                        ji.io.h.a(this.fn, String.valueOf(String.valueOf(new StringBuffer("Out of Bounds annotation found, ").append(g(this.n)).append(" on page ").append(dx.v).append(" divided by ").append(a45))));
                        this.hy = true;
                        this.d(1);
                    }
                }
                this.fm.b = true;
            }
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
        }
    }
    
    public final void a(final Component component, final af af, final dx dx, final String s, final ad ad, final String s2, final double n) {
        String s3 = this.f("F_FONT_NAME");
        if (!ji.util.d.by(s3)) {
            final String f = this.f("F_FONT_BOLD");
            final String f2 = this.f("F_FONT_ITALIC");
            final String f3 = this.f("F_FONT_UNDERLINE");
            final String f4 = this.f("F_FONT_STRIKETHROUGH");
            final String f5 = this.f("F_FONT_SOURCE");
            int n2 = 0;
            final int n3 = (int)Math.round(n * ji.util.d.c(s2, 32) / 73.0);
            boolean h = false;
            if (f != null && f.toLowerCase().equals("true")) {
                ++n2;
            }
            if (f2 != null && f2.toLowerCase().equals("true")) {
                n2 += 2;
            }
            if (f3 != null && f3.toLowerCase().equals("true")) {
                h = true;
            }
            if (!this.ht && f4 != null) {
                this.ht = true;
                if (f4.toLowerCase().equals("true")) {
                    this.h(true);
                }
            }
            Font a = null;
            if (!ji.util.d.by(f5) && ji.util.e.av()) {
                try {
                    final String a2 = ji.font.j.a(ad, f5, af, this.fn);
                    if (a2 != null) {
                        a = ji.font.j.a(a2, n2, n3, ad, this.fn);
                        if (a != null) {
                            final String family = a.getFamily();
                            boolean b = false;
                            if (s3 == null) {
                                b = true;
                            }
                            else if (!family.equals(s3)) {
                                b = true;
                            }
                            if (b) {
                                s3 = a.getFamily();
                                this.j((String)null);
                                this.d(1);
                            }
                            this.c(f5);
                        }
                    }
                    else {
                        ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("ERROR: Unable to find font file, reverting to font name: '").append(s3).append("'"))));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (a == null) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Looking for font match on '").append(s3).append("'..."))));
                }
                s3 = ji.font.j.a(this.fn, s3, ad);
                if (!ji.font.j.ac()) {
                    final String g = ji.font.j.g();
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Not found exact matching font, so trying default font '").append(g).append("'..."))));
                    }
                    s3 = ji.font.j.a(this.fn, g, ad);
                    if (!ji.font.j.ac()) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Not found exact matching default font, so using '").append(s3).append("'..."))));
                        }
                    }
                    else if (ji.util.d.cs()) {
                        ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Found matching default font '").append(s3).append("'..."))));
                    }
                }
                else if (ji.util.d.cs()) {
                    ji.io.h.d(this.fn, String.valueOf(String.valueOf(new StringBuffer("Found matching font '").append(s3).append("'..."))));
                }
                a = new Font(s3, n2, n3);
            }
            final ct ct = new ct(s3, n3, a.isBold(), a.isItalic(), a.isPlain(), a);
            ct.c = a.getFamily();
            ct.a = a.getSize();
            ct.b = a.getSize();
            ct.d = a.isBold();
            ct.e = a.isItalic();
            ct.f = a.isPlain();
            ct.h = h;
            ct.g = this.i();
            this.i(h);
            ct.j = true;
            this.a(ct);
        }
    }
    
    public static final int a(final dg dg, final dx dx, final String s, final Component component) {
        final int m = dx.m;
        int n = dx.n;
        if (dx.ac > dx.ad) {
            n = (int)(n * dx.ac / dx.ad);
        }
        switch (dg.d5()) {
            case 1:
            case 11:
            case 13: {
                return a(dg.r, dg.s, dg.v, dg.w, true, true, m, n);
            }
            case 2: {
                return a(dg.r, dg.s, dg.z, dg.aa, true, true, m, n);
            }
            case 4: {
                return a(dg.r, dg.s, dg.t, dg.u, true, false, m, n);
            }
            case 5: {
                return a(dg.r, dg.s, dg.t, dg.u, true, false, m, n);
            }
            case 7: {
                return a(dg.r, dg.s, dg.v, dg.w, dg.au(), true, m, n);
            }
            case 8: {
                return a(dg.r, dg.s, dg.v, dg.w, true, true, m, n);
            }
            case 10: {
                return a(dg.bk, m, n);
            }
            case 3:
            case 6:
            case 12:
            case 14: {
                return a(dg.bk, m, n);
            }
            case 9: {
                return a(dg.r, dg.s, (dg.c8 != null) ? dg.c8.width : 0, (dg.c8 != null) ? dg.c8.height : 0, dg.db, true, m, n);
            }
        }
        return 1;
    }
    
    public static final void a(final dg dg, final int n, final dx dx, final af af, final ad ad, final String s) {
        dg.v(dg.a(dx) / n);
        switch (dg.n) {
            case 1:
            case 11:
            case 13: {
                dg.r /= n;
                dg.s /= n;
                dg.v /= n;
                dg.w /= n;
                dg.p = dg.r;
                dg.q = dg.s;
                break;
            }
            case 2: {
                dg.r /= n;
                dg.s /= n;
                dg.z /= n;
                dg.aa /= n;
                dg.p = dg.r;
                dg.q = dg.s;
                break;
            }
            case 4:
            case 5: {
                dg.r /= n;
                dg.s /= n;
                dg.t /= n;
                dg.u /= n;
                dg.p = dg.r;
                dg.q = dg.s;
                break;
            }
            case 7: {
                dg.r /= n;
                dg.s /= n;
                dg.u(dg.f3 / n);
                try {
                    dg.a(ad, s, af, dx.d());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (dg.f8) {
                    dg.ge /= n;
                    dg.gd /= n;
                }
                dg.p = dg.r;
                dg.q = dg.s;
                break;
            }
            case 8: {
                dg.r /= n;
                dg.s /= n;
                if (dg.v != ji.util.d.cu()) {
                    dg.v /= n;
                }
                if (dg.w != ji.util.d.cw()) {
                    dg.w /= n;
                }
                dg.bk = new d4(dg.a(dg.r, dg.s, dg.v, dg.w));
                dg.p = dg.r;
                dg.q = dg.s;
                break;
            }
            case 3:
            case 6:
            case 10:
            case 12:
            case 14: {
                final d4 bk = new d4();
                final int b = dg.bk.b();
                final int[] c = dg.bk.c();
                final int[] d = dg.bk.d();
                for (int i = 0; i < b; ++i) {
                    bk.addPoint(c[i] / n, d[i] / n);
                }
                dg.bk = bk;
                break;
            }
            case 9: {
                dg.r /= n;
                dg.s /= n;
                if (dg.db && dg.c8 != null) {
                    final Dimension c2 = dg.c8;
                    c2.width /= n;
                    final Dimension c3 = dg.c8;
                    c3.height /= n;
                    break;
                }
                break;
            }
        }
    }
    
    private static int a(final d4 d4, final int n, final int n2) {
        int n3 = 1;
        for (int i = 0; i < d4.npoints; ++i) {
            final int a = a(d4.xpoints[i], d4.ypoints[i], 0L, 0L, false, false, n, n2);
            if (a > n3) {
                n3 = a;
                if (n3 == 4) {
                    break;
                }
            }
        }
        return n3;
    }
    
    private static int a(final long n, final long n2, final long n3, final long n4, final boolean b, final boolean b2, final int n5, final int n6) {
        if (n > n5 * 2) {
            return 4;
        }
        if (n2 > n6 * 2) {
            return 4;
        }
        if (b) {
            if (b2) {
                if (n + n3 > n5 * 2) {
                    return 4;
                }
                if (n2 + n4 > n6 * 2) {
                    return 4;
                }
            }
            else {
                if (n3 > n5 * 2) {
                    return 4;
                }
                if (n4 > n6 * 2) {
                    return 4;
                }
            }
        }
        if (n > n5) {
            return 2;
        }
        if (n2 > n6) {
            return 2;
        }
        if (b) {
            if (b2) {
                if (n + n3 > n5) {
                    return 2;
                }
                if (n2 + n4 > n6) {
                    return 2;
                }
            }
            else {
                if (n3 > n5) {
                    return 2;
                }
                if (n4 > n6) {
                    return 2;
                }
            }
        }
        return 1;
    }
    
    public final void ae(final boolean fg) {
        this.fg = fg;
    }
    
    public final boolean bd() {
        return this.fg;
    }
    
    public final void af(final boolean fa) {
        this.fa = fa;
        final String a7 = this.a7();
        try {
            if (this.fa) {
                ji.annotate.dg.dy.remove(a7);
                ji.annotate.dg.dy.put(a7, a7);
            }
            else {
                ji.annotate.dg.dy.remove(a7);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean be() {
        return this.fa || ji.annotate.dg.dy.get(this.a7()) != null;
    }
    
    public static final void bf() {
        dg.dy.clear();
    }
    
    public final void ag(final boolean fc) {
        this.fc = fc;
    }
    
    public final boolean bg() {
        return this.fc;
    }
    
    public final void b(final Color fd) {
        this.fd = fd;
    }
    
    public final void g(final String ff) {
        this.ff = ff;
    }
    
    public final void a(final boolean e0, final int e2) {
        this.e0 = e0;
        this.e1 = e2;
    }
    
    public final void a(final int e3, final double e4) {
        this.e3 = e3;
        this.e2 = e4;
    }
    
    public final boolean bh() {
        return this.e0;
    }
    
    public final boolean e(final int n) {
        return this.e0 && this.e1 == n;
    }
    
    public final boolean c(final int n, final int n2) {
        return this.e(n) && this.e3 == n2;
    }
    
    public final void d(final long e5) {
        this.e5 = e5;
    }
    
    public final long bi() {
        return this.e5;
    }
    
    public final void ah(final boolean ez) {
        this.ez = ez;
    }
    
    public final boolean bj() {
        return this.ez;
    }
    
    protected da bk() {
        return this.bj;
    }
    
    protected void ai(final boolean ef) {
        this.ef = ef;
    }
    
    protected boolean bl() {
        return this.ef;
    }
    
    protected void e(final da da) {
        this.bj = new da(da);
    }
    
    protected void aj(final boolean ev) {
        this.ev = ev;
    }
    
    protected boolean bm() {
        return this.ev;
    }
    
    protected boolean a(final boolean b, final String s, final boolean b2) {
        if (b) {
            return this.a(s, b2);
        }
        return this.b(s, b2);
    }
    
    public int bn() {
        if (ji.util.d.bf()) {
            return 2;
        }
        return this.cz;
    }
    
    protected void f(final int n) {
        this.cz = ji.secure.dh.b(n);
    }
    
    protected boolean a(final String s, final boolean b) {
        if (this.e0) {
            return false;
        }
        if (b) {
            return this.dm.l();
        }
        if (this.cz == 1) {
            return this.dm.l();
        }
        if (!this.aw() && this.dm.a(s, this.bn()) && this.gp()) {
            return this.dm.l();
        }
        return this.dm.l() && this.gp();
    }
    
    public boolean b(final String s, final boolean b) {
        if (b) {
            return true;
        }
        if (this.cz == 1) {
            return this.dm.h();
        }
        return (!this.aw() && this.dm.a(s, this.bn()) && this.gp()) || (this.dm.h() && this.gp());
    }
    
    private final boolean gp() {
        if (this.dm != null) {
            final String d = this.dm.d();
            return ji.util.d.by(d) || ji.util.d.o(d.toLowerCase());
        }
        return true;
    }
    
    private final boolean gq() {
        if (this.dm != null) {
            final String e = this.dm.e();
            return ji.util.d.by(e) || ji.util.d.o(e.toLowerCase());
        }
        return true;
    }
    
    public final boolean c(final String s, final boolean b) {
        if (this.e0) {
            return false;
        }
        if (b) {
            return !this.aw();
        }
        if (this.cz == 1) {
            return (this.dm.i() || this.dm.m()) && !this.aw();
        }
        return (!this.aw() && this.dm.a(s, this.bn()) && this.gq()) || (this.dm.i() && !this.aw() && this.gq());
    }
    
    public final boolean ak(final boolean b) {
        return this.c((String)null, b);
    }
    
    public final boolean d(final String s, final boolean b) {
        if (this.e0) {
            return false;
        }
        if (this.cz == 1) {
            return this.c(s, b);
        }
        if (b) {
            return !this.aw();
        }
        return (!this.aw() && this.dm.a(s, this.bn())) || (this.dm.j() && !this.aw());
    }
    
    public final boolean e(final String s, final boolean b) {
        if (this.e0) {
            return false;
        }
        if (this.cz == 1) {
            return this.c(s, b) && !this.aw();
        }
        if (b) {
            return !this.aw();
        }
        return (!this.aw() && this.dm.a(s, this.bn())) || (this.dm.m() && !this.aw());
    }
    
    public final boolean f(final String s, final boolean b) {
        if (this.e0) {
            return false;
        }
        if (this.cz == 1) {
            return this.b(s, b);
        }
        return b || (!this.aw() && this.dm.a(s, this.bn())) || this.dm.k();
    }
    
    protected void al(final boolean e9) {
        this.e9 = e9;
    }
    
    protected boolean bo() {
        return this.e9;
    }
    
    protected void a(final dh dh) {
        this.dm = dh.o();
    }
    
    public dh bp() {
        return this.dm;
    }
    
    protected boolean bq() {
        switch (this.n) {
            case 8: {
                return false;
            }
            default: {
                return true;
            }
        }
    }
    
    protected final void am(final boolean ct) {
        this.ct = ct;
    }
    
    public final boolean br() {
        return this.ct;
    }
    
    protected boolean a(final boolean b, final boolean b2, final ad ad) {
        boolean b3 = true;
        if (this.fk) {
            b3 = false;
        }
        else {
            boolean bi = false;
            if (ad != null) {
                bi = ad.bi(20);
            }
            if (this.f() || (this.d() && !bi)) {
                b3 = false;
            }
            else if (this.fz == 2) {
                b3 = false;
            }
            else if (b2 && (ji.util.d.b3(b) == 1 || ji.util.d.b3(b) == 3)) {
                b3 = !ji.wang.ej.a(this.d5());
            }
        }
        return b3;
    }
    
    public String a(final int n, final boolean b) {
        return h(n);
    }
    
    public boolean h(final String s) {
        switch (this.n) {
            case 1: {
                return s.equalsIgnoreCase("RECTANGLE") || (s.equalsIgnoreCase("SQUARE") && "1:1".equals(this.ch));
            }
            case 2: {
                return s.equalsIgnoreCase("OVAL") || (s.equalsIgnoreCase("CIRCLE") && "1:1".equals(this.ch));
            }
            case 3: {
                return s.equalsIgnoreCase("POLY") || s.equalsIgnoreCase("POLYGON");
            }
            case 4: {
                return s.equalsIgnoreCase("LINE") || (s.equalsIgnoreCase("RULER") && this.a0);
            }
            case 5: {
                return s.equalsIgnoreCase("ARROW");
            }
            case 6: {
                if (s.equalsIgnoreCase("OPENPOLYGON") || s.equalsIgnoreCase("OPENPOLY")) {
                    return true;
                }
                if (this.a1) {
                    if (!this.ax) {
                        if (s.equalsIgnoreCase("ANGLE")) {
                            return true;
                        }
                    }
                    else if (s.equalsIgnoreCase("ANGLEREVERSED")) {
                        return true;
                    }
                }
                return false;
            }
            case 7: {
                return s.equalsIgnoreCase("TEXT") || s.equalsIgnoreCase("SOLIDTEXT") || s.equalsIgnoreCase("TEXTSOLID");
            }
            case 8: {
                return s.equalsIgnoreCase("NOTE");
            }
            case 9: {
                return s.equalsIgnoreCase("STAMP") || s.equalsIgnoreCase("STAMPMENU");
            }
            case 10: {
                return s.equalsIgnoreCase("FREEHAND");
            }
            case 11: {
                return s.equalsIgnoreCase("REDACT") || s.equalsIgnoreCase("REDACTION");
            }
            case 12: {
                return s.equalsIgnoreCase("REDACTPOLY") || s.equalsIgnoreCase("REDACTPOLYGON");
            }
            case 13: {
                return s.equalsIgnoreCase("HIGHLIGHT") || s.equalsIgnoreCase("HYPERLINK");
            }
            case 14: {
                return s.equalsIgnoreCase("HIGHLIGHTPOLY");
            }
            case 16: {
                return s.equalsIgnoreCase("CUSTOM");
            }
            case 17: {
                return s.equalsIgnoreCase("DOCUMENT");
            }
            default: {
                return false;
            }
        }
    }
    
    public static String g(final int n) {
        switch (n) {
            case 1: {
                return "RECTANGLE";
            }
            case 2: {
                return "OVAL";
            }
            case 3: {
                return "POLYGON";
            }
            case 4: {
                return "LINE";
            }
            case 5: {
                return "ARROW";
            }
            case 6: {
                return "OPENPOLYGON";
            }
            case 7: {
                return "TEXT";
            }
            case 8: {
                return "NOTE";
            }
            case 9: {
                return "STAMP";
            }
            case 10: {
                return "FREEHAND";
            }
            case 11: {
                return "REDACT";
            }
            case 12: {
                return "REDACTPOLYGON";
            }
            case 13: {
                return "HIGHLIGHT";
            }
            case 14: {
                return "HIGHLIGHTPOLYGON";
            }
            case 16: {
                return "CUSTOM";
            }
            case 17: {
                return "DOCUMENT";
            }
            case 18: {
                return "REMOVE";
            }
            default: {
                return null;
            }
        }
    }
    
    public static int i(final String s) {
        if (s == null || s.length() <= 0) {
            return -1;
        }
        if (s.equalsIgnoreCase("RECTANGLE") || s.equalsIgnoreCase("SQUARE")) {
            return 1;
        }
        if (s.equalsIgnoreCase("OVAL") || s.equalsIgnoreCase("CIRCLE")) {
            return 2;
        }
        if (s.equalsIgnoreCase("POLYGON") || s.equalsIgnoreCase("POLY")) {
            return 3;
        }
        if (s.equalsIgnoreCase("LINE") || s.equalsIgnoreCase("RULER")) {
            return 4;
        }
        if (s.equalsIgnoreCase("ARROW")) {
            return 5;
        }
        if (s.equalsIgnoreCase("OPENPOLYGON") || s.equalsIgnoreCase("OPENPOLY") || s.equalsIgnoreCase("ANGLE") || s.equalsIgnoreCase("ANGLEREVERSED")) {
            return 6;
        }
        if (s.equalsIgnoreCase("TEXT") || s.equalsIgnoreCase("SOLIDTEXT") || s.equalsIgnoreCase("TEXTSOLID")) {
            return 7;
        }
        if (s.equalsIgnoreCase("NOTE")) {
            return 8;
        }
        if (s.equalsIgnoreCase("FREEHAND")) {
            return 10;
        }
        if (s.equalsIgnoreCase("REDACT") || s.equalsIgnoreCase("REDACTION")) {
            return 11;
        }
        if (s.equalsIgnoreCase("HIGHLIGHT") || s.equalsIgnoreCase("HYPERLINK")) {
            return 13;
        }
        if (s.equalsIgnoreCase("REDACTPOLYGON") || s.equalsIgnoreCase("REDACTPOLY")) {
            return 12;
        }
        if (s.equalsIgnoreCase("HIGHLIGHTPOLYGON") || s.equalsIgnoreCase("HIGHLIGHTPOLY")) {
            return 14;
        }
        if (s.equalsIgnoreCase("STAMP") || s.equalsIgnoreCase("STAMPMENU")) {
            return 9;
        }
        if (s.equalsIgnoreCase("CUSTOM")) {
            return 16;
        }
        if (s.equalsIgnoreCase("DOCUMENT")) {
            return 17;
        }
        return -1;
    }
    
    public static String h(final int n) {
        String s = null;
        switch (n) {
            case 1: {
                s = "RECTANGLE";
                break;
            }
            case 2: {
                s = "OVAL";
                break;
            }
            case 3: {
                s = "POLY";
                break;
            }
            case 4: {
                s = "LINE";
                break;
            }
            case 5: {
                s = "ARROW";
                break;
            }
            case 6: {
                s = "OPENPOLY";
                break;
            }
            case 7: {
                s = "TEXT";
                break;
            }
            case 8: {
                s = "NOTE";
                break;
            }
            case 10: {
                s = "FREEHAND";
                break;
            }
            case 11: {
                s = "REDACT";
                break;
            }
            case 13: {
                s = "HIGHLIGHT";
                break;
            }
            case 12: {
                s = "REDACTPOLY";
                break;
            }
            case 14: {
                s = "HIGHLIGHTPOLY";
                break;
            }
            case 9: {
                s = "STAMP";
                break;
            }
            case 16: {
                s = "CUSTOM";
                break;
            }
            case 17: {
                s = "DOCUMENT";
                break;
            }
            case 18: {
                s = "REMOVE";
                break;
            }
            default: {
                s = "Unknown Type";
                break;
            }
        }
        return s;
    }
    
    public dg(final String s, final String s2, final int n, final int n2) {
        this(s, ji.annotate.dg.a, 1, false, ji.annotate.dg.b, s2, n, n2);
    }
    
    public dg(final String s, final Color color, final String s2, final int n, final int n2) {
        this(s, color, 1, false, ji.annotate.dg.b, s2, n, n2);
    }
    
    public dg(final String s, final boolean b, final Color color, final String s2, final int n, final int n2) {
        this(s, ji.annotate.dg.a, 1, b, color, s2, n, n2);
    }
    
    public dg(final String s, final Color color, final int n, final String s2, final int n2, final int n3) {
        this(s, color, n, false, ji.annotate.dg.b, s2, n2, n3);
    }
    
    public dg(final String bm, final Color a9, final int bg, final boolean ag, final Color ba, final String fn, final int f4, final int cz) {
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.m = -1;
        this.n = -1;
        this.p = 0L;
        this.q = 0L;
        this.r = 0L;
        this.s = 0L;
        this.t = 0L;
        this.u = 0L;
        this.v = 0L;
        this.w = 0L;
        this.x = 0L;
        this.y = 0L;
        this.z = 0L;
        this.aa = 0L;
        this.ab = -1.0;
        this.ac = -1.0;
        this.ad = false;
        this.ae = false;
        this.af = true;
        this.ag = false;
        this.ah = true;
        this.ai = false;
        this.aj = false;
        this.ak = false;
        this.al = false;
        this.am = false;
        this.an = true;
        this.ao = false;
        this.ap = 1;
        this.aq = 1;
        this.ar = this.an;
        this.as = false;
        this.at = false;
        this.au = false;
        this.av = true;
        this.aw = -100.0;
        this.ax = false;
        this.ay = -100.0;
        this.az = false;
        this.a0 = false;
        this.a1 = false;
        this.a2 = false;
        this.a3 = false;
        this.a4 = 0;
        this.a5 = 1.0;
        this.a6 = null;
        this.a7 = null;
        this.a8 = null;
        this.ba = Color.white;
        this.bb = null;
        this.bc = null;
        this.bd = null;
        this.be = null;
        this.bf = null;
        this.bh = -1;
        this.bi = -1;
        this.bj = null;
        this.bp = null;
        this.bq = null;
        this.br = null;
        this.bs = new c("jiAnnotationItem1");
        this.bt = new c("selectionMappings");
        this.bu = -1;
        this.bv = -1;
        this.bw = -1;
        this.bx = -1;
        this.by = true;
        this.bz = false;
        this.b0 = false;
        this.b1 = 1.0;
        this.b2 = 1.0;
        this.b3 = null;
        this.b4 = null;
        this.b5 = null;
        this.b6 = null;
        this.b7 = null;
        this.b8 = null;
        this.b9 = null;
        this.ca = null;
        this.cb = false;
        this.cc = null;
        this.cd = "-";
        this.ce = "";
        this.cf = null;
        this.cg = null;
        this.ch = null;
        this.ci = false;
        this.cj = null;
        this.ck = null;
        this.cl = 1;
        this.cm = null;
        this.cn = null;
        this.co = null;
        this.cp = null;
        this.cq = 0;
        this.cr = 0;
        this.cs = false;
        this.ct = true;
        this.cu = false;
        this.cv = 0L;
        this.cw = 0L;
        this.cx = 1.0;
        this.cy = false;
        this.cz = 1;
        this.c0 = null;
        this.c1 = false;
        this.c2 = false;
        this.c3 = -1;
        this.c4 = true;
        this.c5 = 0;
        this.c6 = 0;
        this.c7 = 0;
        this.c8 = null;
        this.c9 = 0;
        this.da = 0;
        this.db = false;
        this.dc = null;
        this.dd = null;
        this.de = 0.0;
        this.df = false;
        this.dg = 1.0;
        this.dh = 1.0;
        this.di = 1.0;
        this.dj = false;
        this.dk = false;
        this.dl = false;
        this.dm = null;
        this.dn = null;
        this.do = null;
        this.dp = null;
        this.dq = null;
        this.dr = null;
        this.ds = false;
        this.dt = 0L;
        this.du = 0L;
        this.dv = 0L;
        this.dw = 0L;
        this.dx = null;
        this.dz = 0L;
        this.d0 = 0L;
        this.d1 = 0L;
        this.d2 = 0L;
        this.d3 = 0L;
        this.d4 = 0L;
        this.d5 = 0L;
        this.d6 = 0L;
        this.d7 = 0.0;
        this.d8 = null;
        this.d9 = null;
        this.ea = null;
        this.eb = null;
        this.ec = null;
        this.ed = null;
        this.ee = null;
        this.ef = false;
        this.eg = true;
        this.eh = true;
        this.ei = 1.0;
        this.ej = false;
        this.ek = -1;
        this.el = 1.0;
        this.em = false;
        this.en = false;
        this.eo = null;
        this.ep = null;
        this.eq = null;
        this.er = null;
        this.es = null;
        this.et = 0;
        this.eu = 1;
        this.ev = false;
        this.ew = false;
        this.ex = null;
        this.ey = false;
        this.ez = false;
        this.e0 = false;
        this.e1 = 0;
        this.e2 = 0.0;
        this.e3 = 0;
        this.e4 = false;
        this.e5 = -1L;
        this.e6 = 0.0;
        this.e7 = 0.0;
        this.e8 = new Rectangle();
        this.e9 = false;
        this.fa = false;
        this.fb = false;
        this.fc = (ji.util.d.i7 || ji.util.i.c(234));
        this.fd = ji.util.d.i8;
        this.fe = ji.util.d.i9;
        this.ff = ji.util.d.ja;
        this.fg = false;
        this.fh = false;
        this.fi = false;
        this.fj = false;
        this.fk = false;
        this.fl = false;
        this.fm = new u2("jiAnnotationItem3");
        this.fn = null;
        this.fo = new c("jiAnnotationItem4");
        this.fq = ji.annotate.dg.fp;
        this.fr = ji.annotate.dg.fp;
        this.fs = ji.annotate.dg.fp;
        this.ft = ji.annotate.dg.fp;
        this.fu = 0;
        this.fv = new c("jiAnnotationItem5");
        this.fw = new Rectangle();
        this.fx = false;
        this.fy = -1;
        this.fz = 0;
        this.f0 = null;
        this.f1 = new Rectangle();
        this.f2 = new Rectangle();
        this.f3 = 12;
        this.f4 = 0;
        this.f5 = new Dimension(1, 1);
        this.f6 = 0;
        this.f7 = false;
        this.f8 = false;
        this.gc = -1;
        this.gd = -1L;
        this.ge = -1L;
        this.gf = -1L;
        this.gg = -1L;
        this.gh = -1L;
        this.gi = -1L;
        this.gj = false;
        this.gk = 0;
        this.gl = 0;
        this.gm = 0;
        this.gn = 0;
        this.go = false;
        this.gp = false;
        this.gq = false;
        this.gr = 0L;
        this.gs = 0L;
        this.gt = null;
        this.gu = null;
        this.gv = -1L;
        this.gw = -1L;
        this.gx = 1;
        this.gy = false;
        this.gz = 0;
        this.g0 = 0;
        this.g1 = true;
        this.g2 = false;
        this.g3 = false;
        this.g4 = null;
        this.g5 = null;
        this.g6 = null;
        this.g7 = null;
        this.g8 = null;
        this.g9 = -1;
        this.ha = -1;
        this.hb = -1;
        this.hc = -1;
        this.hd = true;
        this.he = null;
        this.hf = 0;
        this.hg = false;
        this.hh = true;
        this.hi = false;
        this.hj = null;
        this.hk = null;
        this.hl = true;
        this.hm = false;
        this.hn = null;
        this.ho = false;
        this.hp = false;
        this.hq = false;
        this.hr = null;
        this.hs = false;
        this.ht = false;
        this.hu = false;
        this.hv = false;
        this.hw = false;
        this.hx = -1;
        this.hy = false;
        this.hz = false;
        this.h0 = new String[0];
        this.h1 = 0.0;
        this.h2 = 0;
        ++ji.annotate.dg.o;
        this.f4 = f4;
        this.cz = cz;
        this.dm = new dh(true, true, true, true, true, true, true, null, null, null, null, null, f4);
        this.dn = new dh(true, true, true, true, true, true, true, null, null, null, null, null, f4);
        this.gw();
        this.ej();
        this.fn = fn;
        this.j = ji.annotate.dg.g++;
        this.k = ji.annotate.dg.h++;
        this.l = ji.annotate.dg.i++;
        if (bm != null) {
            this.bm = bm;
        }
        this.a9 = a9;
        this.ba = ba;
        this.bg = bg;
        this.ag = ag;
        this.b4 = ji.util.d.e2();
        this.b7 = ji.util.d.e2();
        if (ji.res.s.j(fn)) {
            this.f6 = 1;
        }
    }
    
    public final void an(final boolean a2) {
        this.a2 = a2;
    }
    
    public final void a(final ad ad) {
        this.hz = ad.bi(40);
    }
    
    public final boolean bs() {
        return this.a2;
    }
    
    public final void bt() {
        this.j = ji.annotate.dg.g++;
    }
    
    protected final void d(final d4 d4) {
        this.d8 = ji.util.d.a(new d4(d4));
    }
    
    protected final d4 bu() {
        return this.d8;
    }
    
    protected final void a(final d5 d5) {
        this.dz = d5.a;
        this.d0 = d5.b;
    }
    
    protected final d5 bv() {
        return new d5(this.dz, this.d0);
    }
    
    protected final void b(final d5 d5) {
        this.d1 = d5.a;
        this.d2 = d5.b;
    }
    
    protected final d5 bw() {
        return new d5(this.d1, this.d2);
    }
    
    protected final void e(final long d5) {
        this.d5 = d5;
    }
    
    protected final void f(final long d6) {
        this.d6 = d6;
    }
    
    protected final void f(final da da) {
        this.dz = da.a;
        this.d0 = da.b;
        this.d3 = da.c;
        this.d4 = da.d;
    }
    
    protected final void g(final da da) {
        this.dt = da.a;
        this.du = da.b;
        this.dv = da.c;
        this.dw = da.d;
    }
    
    protected final dc bx() {
        return new dc(this.dv, this.dw);
    }
    
    protected final da by() {
        return new da(this.dt, this.du, this.dv, this.dw);
    }
    
    protected final da bz() {
        return new da(this.dz, this.d0, this.d3, this.d4);
    }
    
    public final int i(final int n) {
        if (this.as) {
            return Math.max(n, 1);
        }
        if (this.cl < 0) {
            return n;
        }
        return this.cl;
    }
    
    public final void j(final int n) {
        if (n >= 0) {
            this.cl = Math.max(n, 1);
        }
        else {
            this.cl = -1;
        }
    }
    
    public final int b0() {
        return this.gc;
    }
    
    public final void b1() {
        if (this.gi()) {
            return;
        }
        this.b5();
        if (this.n == 9) {
            final long n = this.w - this.v;
            switch (this.cq) {
                case 0: {
                    this.s += this.v;
                    break;
                }
                case 90: {
                    this.r -= this.v;
                    break;
                }
                case 180: {
                    this.s -= this.w;
                    this.r += n;
                    break;
                }
                case 270: {
                    this.r += this.w;
                    this.s -= n;
                    break;
                }
            }
            this.l((int)(this.cq - 90.0));
            this.ds = false;
            this.dk = false;
        }
        else {
            this.k((int)(this.e6 + 45.0));
        }
        this.b5();
    }
    
    public final void b2() {
        if (this.gi()) {
            return;
        }
        this.b5();
        if (this.n == 9) {
            final long n = this.w - this.v;
            switch (this.cq) {
                case 0: {
                    this.r += this.w;
                    break;
                }
                case 90: {
                    this.s += this.v;
                    this.r += n;
                    break;
                }
                case 180: {
                    this.r -= this.v;
                    this.s -= n;
                    break;
                }
                case 270: {
                    this.s -= this.w;
                    break;
                }
            }
            this.l((int)(this.cq + 90.0));
            this.ds = false;
            this.dk = false;
        }
        else {
            this.k((int)(this.e6 - 45.0));
        }
        this.b5();
    }
    
    public final int b3() {
        if (this.gi()) {
            return 0;
        }
        return this.cq;
    }
    
    public final int b4() {
        return (int)this.e6;
    }
    
    public final void b5() {
        if (this.f8 && this.e6 == 0.0 && this.e9() && this.gd >= 20 && this.gd >= 20 && this.gd > 0 && this.ge > 0) {
            this.gv = this.gd;
            this.gw = this.ge;
        }
    }
    
    public final void k(final int n) {
        if (this.e9()) {
            final double e6 = this.e6;
            this.e6 = n;
            while (this.e6 >= 360) {
                this.e6 -= 360;
            }
            while (this.e6 < 0) {
                this.e6 += 360;
            }
            this.co = null;
            this.cp = null;
            if (e6 != this.e6 && this.e6 == 0.0 && this.gv > 0 && this.gw > 0) {
                this.gd = this.gv;
                this.ge = this.gw;
            }
            this.do = null;
            this.cy();
        }
    }
    
    public final void a(final boolean eg, final double ei, final boolean ej, final int ek) {
        this.eg = eg;
        this.ei = ei;
        this.ej = ej;
        this.ek = ek;
    }
    
    public final boolean b6() {
        return this.eg;
    }
    
    public final void ao(final boolean av) {
        this.av = av;
    }
    
    public final boolean b7() {
        return this.av;
    }
    
    public final double b8() {
        return this.el;
    }
    
    public final double b9() {
        return this.ei;
    }
    
    public final boolean b(final boolean b, final int n) {
        return b != this.ej || n != this.ek;
    }
    
    public final void l(int i) {
        while (i >= 360) {
            i -= 360;
        }
        while (i < 0) {
            i += 360;
        }
        switch (i) {
            case 0:
            case 90:
            case 180:
            case 270: {
                this.cq = this.m(i);
                if (this.e9() || this.n == 9) {
                    this.co = null;
                    this.cp = null;
                    this.do = null;
                    this.cy();
                    break;
                }
                break;
            }
        }
    }
    
    private final void gr() {
        try {
            if (this.ep != null) {
                this.ep.i();
                this.ep = null;
            }
            this.eq = null;
        }
        catch (Exception ex) {}
    }
    
    public final int m(final int n) {
        int i;
        for (i = n; i >= 360; i -= 360) {}
        while (i < 0) {
            i += 360;
        }
        switch (i) {
            case 0:
            case 90:
            case 180:
            case 270: {
                return i;
            }
            default: {
                return 0;
            }
        }
    }
    
    public final int ca() {
        return this.cr;
    }
    
    public final void n(final int cr) {
        this.cr = cr;
        if (this.e9()) {
            this.co = null;
            this.cp = null;
            this.do = null;
            this.cy();
        }
    }
    
    public final double cb() {
        if (this.v > 0 && this.x > 0) {
            this.cx = this.v / this.x;
        }
        return this.cx;
    }
    
    public final void a(final double h1) {
        this.h1 = h1;
    }
    
    public final double cc() {
        final double h1 = this.h1;
        this.h1 = this.cx;
        return h1;
    }
    
    public final void b(final double cx) {
        this.cx = cx;
        if (!this.e9()) {
            this.cp = null;
        }
    }
    
    public final void a(final double ab, final double ac) {
        this.ab = ab;
        this.ac = ac;
    }
    
    public final boolean cd() {
        return this.cs;
    }
    
    public final void ap(final boolean cs) {
        this.cs = cs;
        if (this.e9()) {
            this.co = null;
            this.cp = null;
            this.do = null;
            this.cy();
        }
    }
    
    public final int ce() {
        return this.bv;
    }
    
    public final void cf() {
        if (!ji.util.i.c(257) && this.gh()) {
            this.bv = -1;
        }
    }
    
    public final void o(final int n) {
        if (this.gh()) {
            if (!ji.util.i.c(257)) {
                this.bv = Math.max(n, 0);
            }
            else {
                this.bv = Math.max(n, 0);
            }
        }
        else if (!this.gh() && n == -1) {
            this.bv = -1;
        }
        else if (!ji.util.i.c(257)) {
            this.bv = Math.max(n, 0);
        }
        else {
            this.bv = Math.max(n, 0);
        }
    }
    
    public final void p(final int bw) {
        this.o(this.bw = bw);
    }
    
    public final int cg() {
        return this.bw;
    }
    
    public final int ch() {
        return this.bx;
    }
    
    public final void q(final int bx) {
        if (this.bx == -1) {
            this.bx = bx;
        }
    }
    
    public final void ci() {
        this.bx = -1;
    }
    
    public int cj() {
        return this.c6;
    }
    
    public int ck() {
        return this.c7;
    }
    
    protected final boolean a(int m, final int n, final boolean b) {
        m = this.m(m);
        if (this.c6 != m || this.c7 != n || this.df != b || this.ar != this.an || this.a7 != this.a6 || this.aq != this.ap) {
            this.dk = false;
        }
        if (this.e9()) {
            if (this.cy != this.ai) {
                this.dk = false;
            }
            if (!this.dc.a(this.dd)) {
                this.dk = false;
            }
            if (this.bb == null) {
                this.dk = false;
            }
            else if (!this.bb.equals(this.a9)) {
                this.dk = false;
            }
            if (this.bc == null) {
                this.dk = false;
            }
            else if (!this.bc.equals(this.ba)) {
                this.dk = false;
            }
        }
        return this.dk;
    }
    
    private boolean ak(final int n) {
        final int m = this.m(n);
        boolean b = (m == 0 || m == 180) && (this.da == 90 || this.da == 270);
        if (!b) {
            b = ((m == 90 || m == 270) && (this.da == 0 || this.da == 180));
        }
        return b;
    }
    
    protected final void a(final Image co, final Dimension dimension, final int n, final int n2, final int n3, final int n4, final int n5, final int c7, final boolean df, final int c8) throws Exception {
        final boolean ak = this.ak(this.b3() + n5);
        if (this.c4) {
            this.c4 = false;
            this.v = n;
            this.w = n2;
        }
        else {
            if (ak) {
                final long v = this.v;
                this.v = this.w;
                this.w = v;
            }
            if (dimension != null) {
                if (ak) {
                    this.v = dimension.height;
                    this.w = dimension.width;
                    this.d(dimension.height, dimension.width);
                }
                else {
                    this.v = dimension.width;
                    this.w = dimension.height;
                    this.d(dimension.width, dimension.height);
                }
            }
        }
        this.x = n3;
        this.y = n4;
        this.co = co;
        this.cp = null;
        this.c5 = c8;
        this.do = null;
        this.cz();
        this.c6 = this.m(n5);
        this.c7 = c7;
        this.df = df;
        this.ds = true;
        if (co != null) {
            this.dk = true;
        }
        else {
            this.dk = false;
        }
    }
    
    public final boolean cl() {
        return this.ds;
    }
    
    protected final boolean cm() {
        return this.cu;
    }
    
    protected final void aq(final boolean cu) {
        this.cu = cu;
    }
    
    protected final d4 r(final int n) {
        try {
            if (this.ee == null) {
                return null;
            }
            if (n < this.ee.length) {
                d4 d4 = this.ee[n];
                this.gz();
                if (this.dq.e() && n > 0) {
                    final d4 d5 = this.ee[n - 1];
                    final d4 d6 = new d4();
                    final int n2 = this.eb[n - 1];
                    for (int i = 0; i < d5.b(); ++i) {
                        final d5 a = d5.a(i);
                        d6.addPoint((int)(n2 + a.a), (int)a.b);
                    }
                    d4 = d6;
                }
                return d4;
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final int[] cn() {
        return this.d9;
    }
    
    public final String[] co() {
        return this.ed;
    }
    
    public final int[] cp() {
        return this.ea;
    }
    
    public final int[] cq() {
        return this.eb;
    }
    
    public final int[] cr() {
        return this.ec;
    }
    
    public final int a(final Component component, final ct ct, final af af) throws Exception {
        this.c(component, ct, af);
        return this.cn.h();
    }
    
    public final boolean a(final Component component) {
        try {
            return this.dd != null && this.b(component, this.dd, null) <= 1;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected final int b(final Component component, final ct ct, final af af) throws Exception {
        this.c(component, ct, af);
        return (int)(this.cn.h() * this.c6());
    }
    
    protected final void ar(final boolean em) {
        this.em = em;
    }
    
    protected final boolean cs() {
        return this.em;
    }
    
    public final boolean ct() {
        if (this.ao) {
            return false;
        }
        if (this.n != 9 && !this.e9()) {
            return true;
        }
        if (this.er != null) {
            return this.c7 == 0;
        }
        return this.es == null || this.c7 == 0;
    }
    
    public final ct cu() {
        this.gn();
        this.dd.a(this.hs, this.hu);
        return this.dd;
    }
    
    public final void a(final ct ct) {
        this.a(ct, false);
    }
    
    public final void a(final ct ct, final boolean b) {
        boolean b2 = b;
        if (this.dd == null) {
            b2 = true;
        }
        else if (!this.dd.a(ct)) {
            b2 = true;
        }
        if (b2) {
            if (ct != null) {
                this.dd = ct.c();
            }
            this.cv();
            if (this.e9()) {
                this.co = null;
                this.cp = null;
                this.gs();
                this.do = null;
                this.cz();
            }
        }
    }
    
    public final void cv() {
        if (this.cn != null) {
            try {
                this.cn.c(false);
                this.cn = null;
            }
            catch (Exception ex) {}
        }
    }
    
    public void a(final ad ad, final dx dx) {
        try {
            this.en = true;
            this.a(this.gt, this.fy, ad, dx, this.c6, this.c7, this.df, this.dg, null, this.di, false, this.gu, false, false, this.gx, this.gy, this.gz, this.g0);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final Dimension a(final ad ad, dx kn, final dc dc, final int n) {
        Dimension dimension = null;
        boolean b = false;
        if (ad != null) {
            b = ad.b();
        }
        if (this.cs() && b) {
            dx bg = null;
            boolean b2 = false;
            if (ad != null) {
                bg = ad.bg(n);
            }
            if (bg != null && bg.m / kn.m == bg.n / kn.n) {
                b2 = true;
            }
            if (b2) {
                dimension = new Dimension(ji.annotate.dy.a(bg), ji.annotate.dy.b(bg));
            }
            else {
                dimension = this.dn();
                if (ad != null) {
                    dimension = new Dimension(dimension.width * ad.kr(), dimension.height * ad.kr());
                }
            }
        }
        else {
            if (this.cs()) {
                kn = ad.kn();
            }
            if (kn != null) {
                dimension = new Dimension(ji.annotate.dy.a(kn), ji.annotate.dy.b(kn));
            }
        }
        if (dimension == null || dc == null) {
            dimension = this.ca;
        }
        return dimension;
    }
    
    protected final Image a(final c gt, final int fy, final ad ad, final dx dx, int m, final int c7, final boolean df, final double dg, final af af, final double n, final boolean b, final ct dc, final boolean b2, final boolean b3, final int gx, final boolean gy, final int gz, final int g0) throws Exception {
        if (b2 && this.hk != null && !b && ji.util.i.c(99) && this.hl) {
            return null;
        }
        if (this.e9()) {
            this.c(ad, dc, af);
            m = this.m(m);
            boolean b4 = false;
            boolean b5 = false;
            if (this.c6 != m || this.c7 != c7 || this.df != df || this.dg != dg || this.dj != this.ak || !this.dc.a(dc) || this.de != this.a5 || this.ar != this.an || this.a7 != this.a6 || this.aq != this.ap) {
                b5 = true;
            }
            if (this.bb == null) {
                b5 = true;
            }
            else if (!this.bb.equals(this.a9)) {
                b5 = true;
            }
            if (this.e9() && this.cy != this.ai) {
                b5 = true;
            }
            if (this.e6 != this.e7 && this.b3 != null) {
                b5 = true;
            }
            if (this.bc == null) {
                b5 = true;
            }
            else if (!this.bc.equals(this.ba)) {
                b5 = true;
            }
            this.gt = gt;
            if (dc != null) {
                this.gu = dc.c();
            }
            this.gx = gx;
            this.gy = gy;
            this.gz = gz;
            this.g0 = g0;
            if (gt != null && this.aw() && fy != this.fy && this.e9()) {
                this.fy = fy;
                b5 = true;
                this.co = null;
                this.cp = null;
                this.do = null;
                this.gs();
                this.cz();
            }
            if (b5) {
                this.c6 = m;
                this.c7 = c7;
                this.df = df;
                this.dg = dg;
                this.bb = this.a9;
                this.dj = this.ak;
                this.dc = dc;
                this.de = this.c6();
                this.bc = this.ba;
                this.cy = this.ai;
                this.co = null;
                this.ar = this.an;
                this.a7 = this.a6;
                this.aq = this.ap;
                this.e7 = this.e6;
            }
            this.di = n;
            if ((this.co == null || this.en) && this.b3 != null) {
                this.dl = false;
                Dimension a = new Dimension();
                Dimension a2 = new Dimension();
                final String a3 = this.a(gt, fy, this.b3);
                final int b6 = ji.util.d.b3(a3);
                this.d9 = new int[b6 + 1];
                this.ea = new int[b6 + 1];
                this.eb = new int[b6 + 1];
                this.ec = new int[b6 + 1];
                this.ed = new String[b6 + 1];
                this.gs();
                if (b) {
                    this.ee = new d4[b6 + 1];
                }
                this.gz();
                dc dc2 = null;
                dc dc3 = null;
                double n2 = 0.0;
                double n3 = 0.0;
                boolean b7 = this.ai || this.an;
                if (this.ai && ji.util.d.ls && this.b(gz)) {
                    b7 = false;
                }
                if (this.f8 && this.e6 == 0.0 && !this.e8()) {
                    b7 = false;
                }
                if (this.f8 && this.e6 == 0.0 && !this.gj && this.gd >= 1 && this.gd >= 1 && this.gd > 0 && this.ge > 0) {
                    if (ji.util.i.c(247)) {
                        final dc ao = this.ao();
                        if (ao != null) {
                            dc3 = new dc(ao);
                        }
                        else {
                            dc3 = new dc(this.gd, this.ge);
                        }
                    }
                    else {
                        dc3 = new dc(this.gd, this.ge);
                    }
                    dc2 = new dc(this.dv, this.dw);
                    n2 = this.dv / this.gd;
                    n3 = this.dw / this.ge;
                }
                if (dc2 != null) {
                    b7 = false;
                }
                String s = a3;
                double n4 = 1.0;
                if (this.fr != 0) {
                    n4 = this.fq / this.fr;
                }
                double n5 = 1.0;
                if (this.fr > this.fq && this.fq != 0) {
                    n5 = this.fr / this.fq;
                }
                if (this.bz && this.f8) {
                    if (this.as()) {
                        if (this.d4() != 9 && s == null) {
                            s = "";
                        }
                    }
                    else if (this.d4() != 9 || this.e6 == 0.0) {
                        s = "";
                    }
                }
                final int gf = this.gf();
                Dimension dimension;
                if (ji.util.i.c(247)) {
                    dimension = this.a(ad, dx, dc2, fy);
                }
                else {
                    dimension = this.ca;
                }
                dw a4 = ji.font.go.a(this.er, s, this.cn, this.ak, this.al, this.a9, this.ba, m, c7, df, this.c6() * dg, a, a2, this.e8, this.fw, b7, this.d9, this.ea, this.eb, this.ec, this.ed, this.dq.h(), this.dq.i(), b, af, ad, this.fn, b2, gx, gy, this.dy(), dimension, this.ff(), this.a5, dc2, n2, n3, dc3, this.a5, this, gz, g0, n5, n4, this.hs, this.hu, gf);
                this.gh = this.fw.width;
                this.gi = this.fw.height;
                if (this.er != null) {
                    this.bd = this.er.a();
                }
                if (this.f8 && this.e6 != 0.0) {
                    this.gd = a.width;
                    this.ge = a.height;
                }
                if (a4 != null && b) {
                    a4.a(this.a6);
                    a4.a(this.ap);
                    for (int i = 0; i < this.ee.length; ++i) {
                        this.eb[i] = Math.max(this.eb[i], 2);
                        (this.ee[i] = new d4()).addPoint(this.d9[i], this.ea[i]);
                        this.ee[i].addPoint(this.d9[i] + 2, this.ea[i]);
                        this.ee[i].addPoint(this.d9[i] + 2, this.ea[i] + this.ec[i]);
                        this.ee[i].addPoint(this.d9[i], this.ea[i] + this.ec[i]);
                    }
                }
                if (a4 != null && b) {
                    if (a4.g()) {
                        if (this.e6 != 0.0 || this.cj() != 0) {
                            final double n6 = this.e6 + (360 - this.cj());
                            a4.i();
                            a4.k();
                            final dw a5 = ji.image.fl.a(n6, a4, this.ee, this.e8, ad, this.fn, b2, new Point(0, 0), "ImageAdjust");
                            if (a5 != null && a5 != a4) {
                                a4.c(1);
                                a4 = a5;
                            }
                        }
                        final int j = a4.j();
                        final int l = a4.l();
                        final int length = a4.b(0, 0, j, b7).length;
                        this.gr();
                        if (this.cj == null) {
                            this.cj = ji.io.q.a(ad, this.fn);
                        }
                        this.dp = this.cj.o();
                        ac ac = null;
                        final boolean b8 = false;
                        if (b8) {
                            final int n7 = 204800;
                            ac = new ac(this.dp, true, true, n7, false, ad, this.fn);
                            int max = 0;
                            if (length > 0) {
                                max = Math.max(n7 / length, 1);
                            }
                            int n8 = 0;
                            final int n9 = length * max;
                            final byte[] array = new byte[n9];
                            for (int k = 0; k < l; ++k) {
                                try {
                                    final byte[] a6 = a4.a(k, 0, length, b7);
                                    final int n10 = n8 * length;
                                    System.arraycopy(a6, 0, array, n10, Math.min(length, n9 - n10));
                                    ++n8;
                                }
                                catch (Exception ex) {
                                    break;
                                }
                                if (n8 < max) {
                                    ac.b(array, 0, n8 * length);
                                    n8 = 0;
                                }
                            }
                            if (n8 > 0) {
                                ac.b(array, 0, n8 * length);
                            }
                            b4 = true;
                        }
                        else if (!this.dl) {
                            final int n11 = 204800;
                            ac = new ac(this.dp, true, true, n11, false, ad, this.fn);
                            int max2 = 0;
                            if (length > 0) {
                                max2 = Math.max(n11 / (4 * length), 1);
                            }
                            int n12 = 0;
                            final int n13 = length * max2;
                            final int[] array2 = new int[n13];
                            for (int n14 = 0; n14 < l; ++n14) {
                                try {
                                    final int[] b9 = a4.b(n14, 0, length, b7);
                                    final int n15 = n12 * length;
                                    System.arraycopy(b9, 0, array2, n15, Math.min(length, n13 - n15));
                                    ++n12;
                                }
                                catch (Exception ex2) {
                                    break;
                                }
                                if (n12 < max2) {
                                    ac.b(array2, 0, n12 * length);
                                    n12 = 0;
                                }
                            }
                            if (n12 > 0) {
                                ac.b(array2, 0, n12 * length);
                            }
                            b4 = true;
                        }
                        if (ac != null) {
                            ac.a(ad);
                        }
                        if (b8) {
                            (this.ep = new c1(this.dp, length, l, length, l, 8, this.a8, af, this.fn)).a(a4.h());
                        }
                        else {
                            this.ep = new c1(this.dp, length, l, length, l, 24, this.a8, af, this.fn);
                        }
                        this.co = Toolkit.getDefaultToolkit().createImage(this.ep);
                        this.en = false;
                    }
                    this.d((Component)ad);
                }
                this.eq = null;
                this.cj();
                if (this.e6 != 0.0 || this.cj() != 0) {
                    a = ji.image.fl.a(this.e6, a, new Point(0, 0), new Point());
                    a2 = ji.image.fl.a(this.e6, a2, new Point(0, 0), new Point());
                }
                this.v = a.width;
                this.w = a.height;
                this.cv = a2.width;
                this.cw = a2.height;
                this.a4 = Math.max(this.cn.g() / 15, 1);
                this.v += this.a4;
                this.g1();
                if (b2 && ji.util.i.c(99) && this.hl) {
                    this.hk = a4;
                    b4 = false;
                }
                if (b4 && a4 != null) {
                    a4.c(10);
                }
                if (b && this.co == null) {
                    this.dl = true;
                }
            }
            if (this.n == 7) {
                this.v = Math.max(this.v, this.a4);
                this.w = Math.max(this.w, this.cn.h());
                if (this.eq() == 0) {
                    this.a(ad, (char[])null);
                }
            }
            return this.co;
        }
        if ((b ? (this.dh != dg) : (this.dg != dg)) || this.cp == null) {
            if (b) {
                this.di = n;
                this.dh = dg;
            }
            else {
                this.dg = dg;
            }
            if (this.es == null) {
                final int n16 = (int)(this.x * this.y);
                final int[] array3 = new int[n16];
                final c3 a7 = ji.util.d.a(this.co, this.fn, ad);
                ji.util.d.a(ad, this.fn, this.co, a7, 0, 0, (int)this.x, (int)this.y, (int)this.x, array3);
                ji.util.d.a(this.co, a7);
                this.es = new du((int)this.y, (int)this.x, ad, ji.util.d.b(ji.util.d.cd(), b2, b3, this.fn), this.fn, b2, this.c5, false);
                if (this.gt() && this.a8 != null) {
                    final int red = this.a8.getRed();
                    final int green = this.a8.getGreen();
                    final int blue = this.a8.getBlue();
                    for (int n17 = 0; n17 < n16; ++n17) {
                        final int n18 = (array3[n17] & 0xFF0000) >> 16;
                        final int n19 = (array3[n17] & 0xFF00) >> 8;
                        final int n20 = array3[n17] & 0xFF;
                        if (n18 == red && n19 == green && n20 == blue) {
                            array3[n17] &= 0xFFFFFF;
                        }
                    }
                }
                final int[] array4 = new int[(int)this.x];
                for (int n21 = 0; n21 < this.y; ++n21) {
                    System.arraycopy(array3, (int)(n21 * this.x), array4, 0, (int)this.x);
                    this.es.a(n21, array4);
                }
            }
            int n22;
            int n23;
            double n25;
            double n26;
            if (this.hz) {
                if (this.v > Integer.MAX_VALUE) {
                    ji.io.h.f(this.fn, "w > Integer.MAX_VALUE");
                }
                if (this.w > Integer.MAX_VALUE) {
                    ji.io.h.f(this.fn, "h > Integer.MAX_VALUE");
                }
                n22 = (int)this.v;
                n23 = (int)this.w;
                if (!this.au || this.ab < 0) {
                    if (this.cx == 1.0) {
                        this.cx = dx.o / this.ca.width;
                    }
                    else if (dx.ah && this.a2) {
                        this.cx *= dx.ac / 300;
                    }
                    else if (dx.ah) {
                        this.cx *= dx.m / this.cc.width;
                    }
                    else {
                        this.cx *= dx.d();
                    }
                    this.h(dx.m * dx.d(), dx.n * dx.d());
                    this.au = true;
                }
                if (!this.em && this.db && this.c8 != null) {
                    if (this.c8.width > 0) {
                        n22 = this.c8.width;
                    }
                    if (this.c8.height > 0) {
                        n23 = this.c8.height;
                    }
                    this.ab = this.a(this.v, dx.m);
                    this.ac = this.a(this.w, dx.n);
                    this.v = n22;
                    this.w = n23;
                    this.db = false;
                }
                else if (!this.fi && !this.e9()) {
                    final double a8 = this.a(this.v, dx.m);
                    final double a9 = this.a(this.w, dx.n);
                    if (this.ab > 0) {
                        if (a8 < this.ab - 0.01 || a8 > this.ab + 0.01 || a9 < this.ac - 0.01 || a9 > this.ac - 0.01) {
                            n22 = (int)(this.ab * dx.m / 100.0);
                            n23 = (int)(this.ac * dx.n / 100.0);
                            if (n22 != this.v) {
                                this.db = false;
                                this.v = n22;
                                this.w = n23;
                            }
                        }
                    }
                    else {
                        this.ab = a8;
                        this.ac = a9;
                        n22 = (int)this.v;
                        n23 = (int)this.w;
                    }
                }
                final double cx = n22 / this.x / this.d7;
                final double n24 = n23 / this.y / this.d7;
                this.cx = cx;
                n25 = cx * n;
                n26 = n24 * n;
            }
            else {
                final int n27 = (int)this.x;
                final int n28 = (int)this.y;
                if (!this.au) {
                    if (this.cx != 1.0) {
                        this.v = (int)(this.x * this.cx);
                        this.w = (int)(this.y * this.cx);
                    }
                    this.cx = 1.0;
                    this.au = true;
                }
                else {
                    this.cx = 1.0;
                }
                n22 = (int)this.v;
                n23 = (int)this.w;
                if (this.db && this.c8 != null) {
                    if (this.c8.width > 0) {
                        n22 = this.c8.width;
                    }
                    if (this.c8.height > 0) {
                        n23 = this.c8.height;
                    }
                    this.v = n22;
                    this.w = n23;
                    this.db = false;
                }
                final double cx2 = n22 / this.x;
                final double n29 = n23 / this.y;
                this.cx = cx2;
                n25 = cx2 * n;
                n26 = n29 * n;
            }
            this.c8 = new Dimension(n22, n23);
            this.c9 = this.cj();
            this.da = this.m(this.cj() + this.b3());
            if (this.ak(this.b3() + m)) {
                final double n30 = n25;
                n25 = n26;
                n26 = n30;
            }
            if (this.hz) {
                this.es.a(this.cq, this.c7, n25, n26, (int)(this.x * n25), (int)(this.y * n26));
            }
            else {
                this.es.a(this.cq, this.c7, n25, n26, (int)(n22 * n), (int)(n23 * n));
            }
            final int g2 = this.es.g();
            final int i2 = this.es.i();
            final int length2 = this.es.a(0, 0, g2).length;
            this.gr();
            if (b) {
                if (this.cj == null) {
                    this.cj = ji.io.q.a(ad, this.fn);
                }
                this.dp = this.cj.o();
                final ac ac2 = new ac(this.dp, true, true, 204800, false, ad, this.fn);
                final int n31 = 204800 / (4 * length2);
                int n32 = 0;
                final int[] array5 = new int[length2 * n31];
                for (int n33 = 0; n33 < i2; ++n33) {
                    if (n32 < n31) {
                        System.arraycopy(this.es.a(n33, 0, length2), 0, array5, n32 * length2, length2);
                        ++n32;
                    }
                    else {
                        ac2.b(array5);
                        n32 = 0;
                    }
                }
                if (n32 > 0) {
                    ac2.b(array5, 0, n32 * length2);
                }
                ac2.a(ad);
                this.ep = new c1(this.dp, length2, i2, length2, i2, 24, this.a8, af, this.fn);
                this.cp = Toolkit.getDefaultToolkit().createImage(this.ep);
            }
            this.cv = length2;
            this.cw = i2;
        }
        return this.cp;
    }
    
    public final void d(final int n, final int n2) {
        this.db = true;
        if (this.hz && this.n == 9 && !this.e9() && this.ca != null) {
            if (n != this.v) {
                this.ab = n * 100.0 / this.ca.width;
            }
            if (n2 != this.w) {
                this.ac = n2 * 100.0 / this.ca.height;
            }
        }
        this.c8 = new Dimension(n, n2);
    }
    
    public final Dimension cw() {
        return this.c8;
    }
    
    public final int cx() {
        return this.c9;
    }
    
    public final void cy() {
        this.en = true;
        this.co = null;
        this.ds = false;
        this.gs();
        this.dk = false;
    }
    
    private final void gs() {
        try {
            if (this.ee != null) {
                for (int i = 0; i < this.ee.length; ++i) {
                    this.ee[i] = null;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void cz() {
        try {
            if (this.dp != null) {
                if (this.cj == null) {
                    this.cj = ji.io.q.a((Object)null, this.fn);
                }
                this.cj.d(this.dp);
            }
        }
        catch (Exception ex) {}
        this.dp = null;
        try {
            if (this.er != null) {
                this.er.c(2);
                this.er = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.es != null) {
                this.es.p();
                this.es = null;
            }
        }
        catch (Exception ex3) {}
        this.en = true;
        this.gr();
    }
    
    public final boolean c0() {
        return this.dl;
    }
    
    protected final dw a(final c c, final int fy, final ad ad, final dx dx, final af af, final boolean b, final int n, final boolean b2, final int n2, final int n3) {
        try {
            if (b && this.hj != null && ji.util.i.c(99) && this.hl) {
                return this.hj;
            }
            if (this.e9()) {
                try {
                    if (c != null && this.aw() && fy != this.fy && this.e9()) {
                        this.fy = fy;
                        this.co = null;
                        this.cp = null;
                        this.do = null;
                        this.gs();
                        this.cz();
                        this.en = true;
                    }
                    if (this.en) {
                        try {
                            if (this.er != null && this.et != this.c7) {
                                this.er.c(3);
                                this.er = null;
                            }
                        }
                        catch (Exception ex3) {}
                    }
                    if (this.er == null || this.en) {
                        final Dimension dimension = new Dimension();
                        final Dimension dimension2 = new Dimension();
                        final String a = this.a(c, fy, this.b3);
                        final int b3 = ji.util.d.b3(a);
                        this.d9 = new int[b3 + 1];
                        this.ea = new int[b3 + 1];
                        this.eb = new int[b3 + 1];
                        this.ec = new int[b3 + 1];
                        this.ed = new String[b3 + 1];
                        this.dl = false;
                        this.et = this.c7;
                        Color color = this.ba;
                        if (n == 1 && b) {
                            color = ji.util.d.e4();
                        }
                        this.gz();
                        boolean b4 = this.ai || this.an;
                        if (this.f8 && this.e6 == 0.0 && !this.e8()) {
                            b4 = false;
                        }
                        dc dc = null;
                        dc dc2 = null;
                        double n4 = 0.0;
                        double n5 = 0.0;
                        if (this.f8 && this.e6 == 0.0 && !this.gj && this.gd >= 1 && this.gd >= 1 && this.gd > 0 && this.ge > 0) {
                            if (ji.util.i.c(247)) {
                                final dc ao = this.ao();
                                if (ao != null) {
                                    dc2 = new dc(ao);
                                }
                                else {
                                    dc2 = new dc(this.gd, this.ge);
                                }
                            }
                            else {
                                dc2 = new dc(this.gd, this.ge);
                            }
                            dc = new dc(this.dv, this.dw);
                            n4 = this.dv / this.gd;
                            n5 = this.dw / this.ge;
                        }
                        if (dc != null) {
                            b4 = false;
                        }
                        double n6 = 1.0;
                        if (this.fr != 0) {
                            n6 = this.fq / this.fr;
                        }
                        double n7 = 1.0;
                        if (this.fr > this.fq && this.fq != 0) {
                            n7 = this.fr / this.fq;
                        }
                        final int gf = this.gf();
                        Dimension dimension3;
                        if (ji.util.i.c(247)) {
                            dimension3 = this.a(ad, dx, dc, fy);
                        }
                        else {
                            dimension3 = this.ca;
                        }
                        this.er = ji.font.go.a(this.er, a, this.cn, this.ak, this.al, this.a9, color, this.c6, this.c7, this.df, this.de * this.dg, dimension, dimension2, this.e8, this.fw, b4, this.d9, this.ea, this.eb, this.ec, this.ed, this.dq.h(), this.dq.i(), true, af, ad, this.fn, b, n, b2, this.dy(), dimension3, this.bf(true), this.a5, dc, n4, n5, dc2, this.de, this, n2, n3, n7, n6, this.hs, this.hu, gf);
                        if (this.er != null) {
                            this.bd = this.er.a();
                        }
                        this.gh = dimension.width;
                        this.gi = dimension.height;
                        if (this.f8 && this.e6 != 0.0) {
                            this.gd = dimension.width;
                            this.ge = dimension.height;
                        }
                        if (this.er != null) {
                            this.er.a(this.a6);
                            this.er.a(this.ap);
                        }
                    }
                    if ((this.e6 != 0.0 || this.cj() != 0) && this.en) {
                        final dw a2 = ji.image.fl.a(this.e6, this.er, null, this.e8, ad, this.fn, b, new Point(0, 0), "ImageAdjust");
                        if (a2 != null) {
                            this.er = a2;
                        }
                    }
                    this.en = false;
                    this.d((Component)ad);
                    this.g0();
                    if (b && ji.util.i.c(99) && this.hl) {
                        this.hj = this.er;
                    }
                    return this.er;
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                        return null;
                    }
                    return null;
                }
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        return null;
    }
    
    protected final du a(final Component component, final af af) {
        return this.es;
    }
    
    public final String c1() {
        return this.dx;
    }
    
    public final void j(final String dx) {
        this.dx = dx;
    }
    
    public final d1 c(final Component component, final ct ct, final af af) throws Exception {
        try {
            if (this.cn != null && this.cn.i()) {
                this.cn = null;
            }
            if (this.cn == null) {
                String b = null;
                if (!ct.b()) {
                    b = ji.font.j.b(component, ct, af, this.fn);
                }
                this.cn = ji.font.j.a(component, b, af, ji.res.s.i(this.fn) || ct.b(), this.fn, ct, 1.0, ji.font.j.u());
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return this.cn;
    }
    
    public final Font c2() {
        if (this.cn != null) {
            return this.cn.f();
        }
        if (this.dd != null) {
            return this.dd.i;
        }
        return null;
    }
    
    public final String c3() {
        if (this.cn != null) {
            final Font f = this.cn.f();
            if (f != null) {
                return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(f.getFamily()))).append(", ").append(f.getStyle())));
            }
        }
        return null;
    }
    
    public final boolean c4() {
        if (this.cn != null) {
            final Font f = this.cn.f();
            if (f != null) {
                return f.isItalic();
            }
        }
        return false;
    }
    
    public final int c5() {
        if (this.cn != null) {
            return this.cn.g();
        }
        return 2;
    }
    
    public final double c6() {
        return this.a5 * ji.util.d.a();
    }
    
    public final void c(final double a5) {
        if ((!ji.util.i.c(247) || !ji.util.d.bf()) && this.a5 != a5 && !ji.util.i.c(247)) {
            this.a5 = a5;
            this.cv();
            if (this.e9()) {
                this.co = null;
                this.cp = null;
                this.do = null;
                this.gs();
                this.cz();
            }
        }
    }
    
    public final void a(final ad ad, final String s, final af af, final int n) throws Exception {
        if (this.d5() == 7 || (this.d5() == 9 && this.e9())) {
            final int ed = this.ed();
            double n2 = 0.0;
            int max;
            if (ji.util.d.bf()) {
                max = Math.max((int)(ed * 2 * this.bb() / 100.0), 6) * 100 / 120;
            }
            else {
                max = Math.max((int)(ed * this.bb() / 100.0), 6);
            }
            if (max != 0) {
                double n3;
                if (ad.kd()) {
                    if (ji.font.j.a(this.dd)) {
                        n3 = max / this.dd.a;
                    }
                    else {
                        n3 = max / ji.font.j.a(ad, this.dd, af, s);
                    }
                }
                else {
                    n3 = max / ji.font.j.a(ad, this.dd, af, s);
                }
                n2 = n3 * n;
            }
            this.c(n2);
            if (ji.util.d.cs()) {
                ji.util.d.c(this.fn, String.valueOf(String.valueOf(new StringBuffer("FontHeight (internal) = ").append(ed).append(" (original) = ").append(max).append(" pixels"))));
            }
        }
    }
    
    public final void c7() {
        if (this.e9()) {
            this.co = null;
            this.cp = null;
            this.gs();
            this.f1 = new Rectangle();
            this.do = null;
            this.cz();
        }
    }
    
    public final String c8() {
        return this.cd;
    }
    
    public final String c9() {
        return this.ce;
    }
    
    public final String da() {
        return this.cf;
    }
    
    public final String g(final String s, final boolean b) {
        int n = 1;
        final String da = this.da();
        final String c9 = this.c9();
        if (ji.util.d.by(da)) {
            n = 0;
        }
        if (n != 0 && !ji.util.d.by(c9) && !ji.util.i.c(225)) {
            if (!ji.util.d.bf()) {
                n = 0;
            }
            else if (this.dg() == null) {
                n = 0;
            }
        }
        String s2;
        if (n != 0) {
            s2 = da;
        }
        else {
            s2 = c9;
        }
        if (ji.util.d.by(s2) && this.z() && this.at() && !ji.util.d.by(this.ev())) {
            s2 = this.ev();
        }
        if (this.d5() == 8) {
            String ev = "";
            if (ji.util.i.c(68) && (this.b(s, b) || this.c(s, b) || this.f(s, b))) {
                ev = this.ev();
            }
            if (!ji.util.d.by(ev)) {
                if (ji.util.d.bf()) {
                    s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf("-".concat(String.valueOf(String.valueOf(ev))))));
                }
                else if (ji.util.d.by(s2)) {
                    s2 = ev;
                }
            }
        }
        return s2;
    }
    
    public final boolean db() {
        return !ji.util.d.by(this.ce);
    }
    
    public final String b(final ad ad) {
        this.e(ad);
        return this.cg;
    }
    
    private final void e(final ad ad) {
        if (this.cg != null && ad != null && ji.util.d.a(ad) && new ed(this.cg, this.fn).b() == 1) {
            this.cg = null;
            this.ck = null;
        }
    }
    
    public final String dc() {
        return this.ck;
    }
    
    public final boolean dd() {
        return !ji.util.d.by(this.cg);
    }
    
    public final boolean c(final ad ad) {
        this.e(ad);
        return !ji.util.d.by(this.ck);
    }
    
    public final boolean de() {
        return this.b0;
    }
    
    public final void k(final String cd) {
        this.cd = cd;
        this.b0 = true;
    }
    
    public final ed df() {
        if (this.cg != null) {
            try {
                if (this.eo == null) {
                    this.eo = new ed(this.cg, this.fn);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return this.eo;
        }
        return null;
    }
    
    public final void l(final String cg) {
        this.cg = cg;
    }
    
    public final void a(final String ck, final ad ad) {
        this.ck = ck;
        this.e(ad);
    }
    
    public final void m(final String ce) {
        this.ce = ce;
    }
    
    public final void n(final String cf) {
        this.cf = cf;
    }
    
    public final String dg() {
        return this.b6;
    }
    
    public final boolean dh() {
        return !ji.util.d.by(this.b6);
    }
    
    public final GregorianCalendar di() {
        return this.b4;
    }
    
    public final String dj() {
        return this.b5;
    }
    
    public final void o(final String b5) {
        this.b5 = b5;
    }
    
    public final void a(final GregorianCalendar b4, final String b5, final boolean ew) {
        if (b4 != null) {
            this.b4 = b4;
            this.b6 = b5;
            this.ew = ew;
        }
    }
    
    public final Dimension dk() {
        return this.ca;
    }
    
    public final void as(final boolean cb) {
        this.cb = cb;
    }
    
    public final boolean dl() {
        return this.cb;
    }
    
    public final void a(final Dimension dimension, final double n, final double n2, final int n3) {
        this.a(dimension, n, n2, n3, false, false);
    }
    
    public final synchronized boolean dm() {
        return this.c(1, 1) && !this.e4;
    }
    
    public final synchronized d5 a(Dimension ca, final double n, final double n2) {
        d5 fn = this.fn();
        if (this.ca != null) {
            ca = this.ca;
        }
        if (ca != null && this.bh() && this.e3 == 1 && !this.e4) {
            try {
                final double n3 = this.e2 / n;
                fn = new d5(Math.round(this.r / n3), Math.round(this.s / n3));
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
        return fn;
    }
    
    public final synchronized void b(final Dimension dimension, final double n, final double n2) {
        if (this.ca == null && this.bh() && this.e3 == 1) {
            try {
                final double n3 = this.e2 / n;
                final double n4 = this.r / n3;
                final double n5 = this.s / n3;
                final double n6 = this.v / n3;
                final double n7 = this.w / n3;
                this.r = (long)n4;
                this.s = (long)(dimension.height - n5);
                this.v = (long)(n6 - this.r + 4);
                this.w = (long)(dimension.height - n7 - this.s + 4);
                this.e4 = true;
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
        if (dimension != null) {
            this.ca = new Dimension(dimension);
        }
        else {
            this.ca = null;
        }
        this.a(n, n2, -1);
    }
    
    public final void a(final Dimension dimension, final double n, final double n2, final int n3, final boolean b, final boolean b2) {
        try {
            if (this.dm()) {
                this.b(dimension, n, n2);
            }
            if (this.ca != null && dimension != null && !this.ca.equals(dimension)) {
                final double n4 = dimension.width / this.ca.width;
                final double n5 = dimension.height / this.ca.height;
                final double n6 = (n4 + n5) / 2.0;
                final int max = Math.max((int)Math.round(this.bg * n6), 1);
                if (this.bg > 0 && max > 0) {
                    this.bg = max;
                }
                this.bp = null;
                this.c7();
                if (this.aw() || this.cl == -1) {
                    this.gc();
                }
                this.cz();
                if (this.e9() && this.n != 8) {
                    this.r = Math.round(this.r * n4);
                    this.s = Math.round(this.s * n5);
                    if (!ji.util.i.c(247) || !ji.util.d.bf()) {
                        this.a5 *= n6;
                    }
                    if (this.at()) {
                        this.gd = Math.round(this.gd * n4);
                        this.ge = Math.round(this.ge * n5);
                    }
                }
                else if (this.n == 8 && this.bk != null) {
                    if (this.bk != null) {
                        final d4 bk = new d4();
                        for (int i = 0; i < this.bk.b(); ++i) {
                            final d5 a = this.bk.a(i);
                            a.a = Math.round(a.a * n4);
                            a.b = Math.round(a.b * n5);
                            bk.a(a);
                        }
                        this.bk = bk;
                    }
                }
                else if (this.n == 4 || this.n == 5) {
                    this.r = Math.round(this.r * n4);
                    this.s = Math.round(this.s * n5);
                    this.t = Math.round(this.t * n4);
                    this.u = Math.round(this.u * n5);
                }
                else if (this.n == 10) {
                    if (this.bk != null) {
                        final d4 bk2 = new d4();
                        for (int j = 0; j < this.bk.b(); ++j) {
                            final d5 a2 = this.bk.a(j);
                            a2.a = Math.round(a2.a * n4);
                            a2.b = Math.round(a2.b * n5);
                            bk2.a(a2);
                        }
                        this.bk = bk2;
                    }
                }
                else {
                    if (this.n == 9 && this.c8 != null) {
                        this.c8.width = (int)Math.round(this.c8.width * n4);
                        this.c8.height = (int)Math.round(this.c8.height * n5);
                    }
                    this.r = Math.round(this.r * n4);
                    this.s = Math.round(this.s * n5);
                    this.v = Math.round(this.v * n4);
                    this.w = Math.round(this.w * n5);
                    this.z = Math.round(this.z * n4);
                    this.aa = Math.round(this.aa * n5);
                    if (!this.hz) {
                        this.x = Math.round(this.x * n4);
                        this.y = Math.round(this.y * n5);
                    }
                    if (this.hz) {
                        if (this.n == 9 && !this.e9() && b) {
                            this.cx *= n6;
                        }
                    }
                    else if (b && this.d5() == 9 && !this.e9() && !b2) {
                        this.cx *= n6;
                    }
                    if (this.bk != null) {
                        final d4 bk3 = new d4();
                        for (int k = 0; k < this.bk.b(); ++k) {
                            final d5 a3 = this.bk.a(k);
                            a3.a = Math.round(a3.a * n4);
                            a3.b = Math.round(a3.b * n5);
                            bk3.a(a3);
                        }
                        this.bk = bk3;
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (dimension != null) {
            this.ca = new Dimension(dimension);
        }
        else {
            this.ca = null;
        }
        this.a(n, n2, n3);
    }
    
    public final void a(final Dimension dimension, final int n) {
        if (dimension != null) {
            this.cc = new Dimension(dimension);
        }
    }
    
    public final Dimension dn() {
        return this.cc;
    }
    
    public final void b(final Dimension dimension, final int n) {
        if (dimension != null && this.cl == n) {
            this.ca = new Dimension(dimension);
        }
    }
    
    public final void a(final double n, final double n2, final int n3) {
        if (n >= 0.0) {
            this.fq = n;
        }
        if (n2 >= 0.0) {
            this.fr = n2;
        }
        if (ji.util.d.bf() && ji.util.i.c(202)) {
            if (n3 == 2) {
                if (n > 0.0) {
                    if (n < 100) {
                        this.fs = 100.0;
                    }
                    else {
                        this.fs = (int)(n / 100) * 100;
                    }
                }
                if (n2 > 0.0) {
                    if (n2 < 100) {
                        this.ft = 100.0;
                    }
                    else {
                        this.ft = (int)(n2 / 100) * 100;
                    }
                }
            }
            else if (n3 == 3 || n3 == 4) {
                if (n > 0.0) {
                    this.fs = 100.0;
                }
                if (n2 > 0.0) {
                    this.ft = 100.0;
                }
            }
            else {
                this.fs = n;
                this.ft = n2;
            }
        }
    }
    
    private final void b(final int bi, final double n) {
        if (ji.util.d.bf() && ji.util.i.c(204)) {
            this.bi = bi;
            this.v((int)Math.round(bi * n / 100));
            this.eh();
            ji.io.h.a(this.fn, String.valueOf(String.valueOf(new StringBuffer("Annotation Type: ").append(a(this.n, this.fn)).append(" Filenet Line Width=").append(bi).append(" Internal Line Width=").append(this.bg))));
        }
    }
    
    public final boolean do() {
        return this.ca != null;
    }
    
    public final String dp() {
        return this.b9;
    }
    
    public final boolean dq() {
        return !ji.util.d.by(this.b9);
    }
    
    public final GregorianCalendar dr() {
        return this.b7;
    }
    
    public final String ds() {
        return this.b8;
    }
    
    public final void p(final String b8) {
        this.b8 = b8;
    }
    
    public final void a(final GregorianCalendar gregorianCalendar, final String s) {
        if (gregorianCalendar != null) {
            this.b7 = gregorianCalendar;
            this.b9 = s;
            if (this.ew) {
                this.ew = false;
                this.b4 = gregorianCalendar;
                this.b6 = s;
            }
        }
        this.p(null);
    }
    
    protected final boolean dt() {
        switch (this.n) {
            case 8:
            case 10: {
                return true;
            }
            case 9: {
                return false;
            }
            case 7: {
                return !this.f8 || this.e6 != 0.0;
            }
            default: {
                return false;
            }
        }
    }
    
    protected final Polygon a(final long n, final long n2, final int n3, final int n4) {
        return this.a(n, n2, n3, (long)n4);
    }
    
    protected final Polygon a(final long n, final long n2, final long n3, final long n4) {
        final int n5 = (int)(n - n3 / 2);
        final int n6 = (int)(n2 - n4 / 2);
        final Polygon polygon = new Polygon();
        if (this.ey) {
            polygon.addPoint(n5, n6);
            polygon.addPoint((int)(n5 + n3), n6);
            polygon.addPoint((int)(n5 + n3), (int)(n6 + n4));
            polygon.addPoint(n5, (int)(n6 + n4));
        }
        else {
            polygon.addPoint(n5, n6);
            polygon.addPoint((int)(n5 + n3), n6);
            final int n7 = Math.min((int)n3, (int)n4) / 4;
            polygon.addPoint((int)(n5 + n3), (int)(n6 + n4 - n7));
            polygon.addPoint((int)(n5 + n3 - n7), (int)(n6 + n4));
            polygon.addPoint(n5, (int)(n6 + n4));
        }
        return polygon;
    }
    
    public final String du() {
        if (this.bm == null) {
            return "".concat(String.valueOf(String.valueOf(ji.annotate.dg.g)));
        }
        return this.bm;
    }
    
    public final boolean dv() {
        return this.bm != null;
    }
    
    public final void q(final String bm) {
        this.bm = bm;
    }
    
    public final void r(final String bn) {
        this.bn = bn;
    }
    
    public final String dw() {
        return this.bn;
    }
    
    public final String dx() {
        return this.bo;
    }
    
    public final void s(final String bo) {
        this.bo = bo;
    }
    
    public final boolean dy() {
        return this.ai;
    }
    
    public final void at(final boolean ai) {
        this.ai = ai;
        this.bu = -1;
    }
    
    private final boolean gt() {
        boolean ak = this.ak;
        if (this.n == 8) {
            ak = false;
        }
        return ak;
    }
    
    public static final boolean a(final dg dg, final boolean b) {
        if (b) {
            final int d5 = dg.d5();
            if (d5 == 11 || d5 == 12) {
                return true;
            }
        }
        return dg.gt();
    }
    
    public final void au(final boolean ak) {
        this.ak = ak;
        if (ji.util.d.cs()) {
            ji.io.h.d(this.fn, "Annotation: FN6...".concat(String.valueOf(String.valueOf(ak))));
        }
        if (this.n == 8) {
            this.ak = false;
        }
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final boolean dz() {
        return this.n != 8 && this.al;
    }
    
    public final void av(final boolean al) {
        this.al = al;
        if (ji.util.d.cs()) {
            ji.io.h.d(this.fn, "Annotation: FN7...".concat(String.valueOf(String.valueOf(al))));
        }
        if (this.n == 8) {
            this.al = false;
        }
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final long d0() {
        return this.j;
    }
    
    public final long d1() {
        this.j = ji.annotate.dg.g++;
        this.k = ji.annotate.dg.h++;
        this.l = ji.annotate.dg.i++;
        return this.j;
    }
    
    public final void b(final boolean ae, final String s, final boolean b) {
        if (this.c(s, b)) {
            this.ae = ae;
        }
        else {
            this.ae = false;
        }
    }
    
    public final boolean d2() {
        return !this.ho && !this.ao && this.ae;
    }
    
    public final void aw(final boolean bz) {
        this.bz = bz;
    }
    
    public final boolean d3() {
        return this.bz;
    }
    
    public final int d4() {
        return this.m;
    }
    
    public final int d5() {
        return this.n;
    }
    
    private final Color gu() {
        if (ji.util.d.a2()) {
            return ji.util.d.n(this.a9);
        }
        return this.a9;
    }
    
    public static final Color b(final dg dg, final boolean b) {
        if (b) {
            final int d5 = dg.d5();
            if ((d5 == 11 || d5 == 12) && !dg.d3()) {
                return ji.util.d.cr();
            }
        }
        return dg.gu();
    }
    
    public final void c(final Color a9) {
        this.a9 = a9;
        if (ji.util.d.a2()) {
            this.a9 = ji.util.d.n(this.a9);
        }
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    private final Color gv() {
        if (ji.util.d.a2()) {
            return ji.util.d.n(this.ba);
        }
        return this.ba;
    }
    
    public static final Color c(final dg dg, final boolean b) {
        if (b) {
            final int d5 = dg.d5();
            if (d5 == 11 || d5 == 12) {
                return ji.util.d.cr();
            }
        }
        return dg.gv();
    }
    
    public final void d(final Color ba) {
        this.ba = ba;
        if (ji.util.d.a2()) {
            this.ba = ji.util.d.n(this.ba);
        }
    }
    
    public final Color d6() {
        return this.a8;
    }
    
    public final void e(final Color a8) {
        this.a8 = a8;
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final void ax(final boolean an) {
        this.an = an;
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final boolean d7() {
        return this.an;
    }
    
    public final void f(final Color a6) {
        this.a6 = a6;
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final Color d8() {
        return this.a6;
    }
    
    public final Color d9() {
        return this.bd;
    }
    
    public final void s(final int ap) {
        this.ap = ap;
        if (this.e9()) {
            this.dk = false;
        }
    }
    
    public final int ea() {
        return this.ap;
    }
    
    private final void gw() {
        this.br = null;
    }
    
    private final int al(final int n) {
        return n;
    }
    
    public final int eb() {
        return this.eu;
    }
    
    public final void t(final int n) {
        this.eu = Math.max(n, 1);
    }
    
    public final int a(final dx dx) {
        if (this.n == 8) {
            return 0;
        }
        return this.al(this.bg);
    }
    
    public final int d(final double n) {
        return (int)(this.bg * this.d7 * n);
    }
    
    public final double ec() {
        return this.d7;
    }
    
    public final void e(final double d7) {
        this.d7 = d7;
    }
    
    public final void u(final int n) {
        this.f3 = Math.max(n, 4);
    }
    
    public final int ed() {
        return this.f3;
    }
    
    public final int a(final dx dx, final double n) {
        if (this.n == 8) {
            return 0;
        }
        this.bg = this.al(this.bg);
        if (!ji.util.d.bq() || !ji.util.d.bf()) {
            return this.bg;
        }
        if (ji.util.i.c(204)) {
            if (this.bi != -1) {
                ji.io.h.a(this.fn, "Line Width has not changed so use original Filenet Line Width: ".concat(String.valueOf(String.valueOf(this.bi))));
                return this.bi;
            }
            return (int)(this.bg * 100 / n);
        }
        else {
            if (dx.c()) {
                return this.bg / dx.d();
            }
            return this.bg;
        }
    }
    
    public final int ee() {
        if (ji.util.d.bq() && ji.util.d.bf()) {
            return -1;
        }
        if (this.n == 8) {
            return 0;
        }
        return this.bg = this.al(this.bg);
    }
    
    public final void v(final int bg) {
        this.gw();
        this.bg = bg;
        this.bg = this.al(this.bg);
    }
    
    public final void w(final int bh) {
        this.bh = bh;
    }
    
    public final void ef() {
        ++this.eu;
    }
    
    public final void eg() {
        this.eu = Math.max(1, this.eu - 1);
    }
    
    public final void x(final int n) {
        this.gw();
        this.bg += Math.max(n, 1);
        this.bi = -1;
    }
    
    public final void y(final int n) {
        this.gw();
        this.bg = Math.max(this.bg - Math.max(n, 1), this.gx());
        this.bi = -1;
    }
    
    public final void eh() {
        this.bg = Math.max(this.bg, this.gx());
    }
    
    private final int gx() {
        int n = 0;
        if (this.n == 4) {
            n = 1;
        }
        if (this.n == 5) {
            n = 1;
        }
        if (this.n == 10) {
            n = 1;
        }
        if (this.n == 6) {
            n = 1;
        }
        return n;
    }
    
    public final boolean ei() {
        return this.ag;
    }
    
    private final String aa(final String s) {
        String s2 = s;
        try {
            if (!ji.util.d.by(s)) {
                s2 = ji.util.d.b(s2, "\r", "");
                final char[] charArray = s2.toCharArray();
                if (!ji.util.i.c(66)) {
                    final StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < charArray.length; ++i) {
                        if (charArray[i] >= ' ') {
                            sb.append(charArray[i]);
                        }
                        else if (charArray[i] == '\r') {
                            sb.append(charArray[i]);
                        }
                        else if (charArray[i] == '\n') {
                            sb.append(charArray[i]);
                        }
                    }
                    s2 = sb.toString();
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public final void ej() {
        this.gz();
        this.dq.j();
        this.c7();
    }
    
    public final void ek() {
        this.gz();
        this.dq.m();
    }
    
    public final void el() {
        this.gz();
        this.dq.l();
    }
    
    public final void b(final Component component) {
        this.gz();
        this.dq.b(component, this.cn);
        this.b3 = this.dq.a();
        this.d(component);
    }
    
    public final int em() {
        this.gz();
        return this.dq.b();
    }
    
    public final void ay(final boolean ao) {
        this.ao = ao;
    }
    
    public final boolean en() {
        return this.ao;
    }
    
    public final void a(final Component component, final String s) {
        this.a(component, s, null);
    }
    
    public final void a(final Component component, final String s, final af af) {
        if (!ji.util.d.by(s) && this.n != 8 && this.hv && ji.util.d.f() > 0 && s.length() > ji.util.d.f()) {
            if (ji.util.d.by(this.b3)) {
                if (ji.util.i.c(108)) {
                    ji.util.d.a(ji.res.ay.a(), ji.util.d.b(ji.util.d.b(1090, this.fn), "<#>", "".concat(String.valueOf(String.valueOf(ji.util.d.f())))), af, this.fn);
                }
                return;
            }
            if (s.length() >= this.b3.length()) {
                if (ji.util.i.c(108)) {
                    ji.util.d.a(ji.res.ay.a(), ji.util.d.b(ji.util.d.b(1090, this.fn), "<#>", "".concat(String.valueOf(String.valueOf(ji.util.d.f())))), af, this.fn);
                }
                return;
            }
        }
        this.gw();
        this.b3 = this.aa(s);
        this.gz();
        this.dq.a(this.b3);
        if (this.e9()) {
            this.co = null;
            this.cp = null;
            this.gs();
            this.do = null;
            this.cz();
        }
        this.ej();
        this.d(component);
    }
    
    private final void d(final Component component) {
        this.gz();
        this.dq.a(component, this.d9, this.ea, this.eb, this.ec, this.cn);
    }
    
    private final void a(final Component component, final char[] array) {
        this.gz();
        this.dq.a(component, this.cn);
    }
    
    public final boolean eo() {
        this.gz();
        return this.dq.f();
    }
    
    protected final int ep() {
        this.gz();
        return this.dq.b();
    }
    
    public final int eq() {
        this.gz();
        return this.dq.k();
    }
    
    public final int er() {
        this.gz();
        return this.dq.d();
    }
    
    public final int es() {
        this.gz();
        return this.dq.c();
    }
    
    public final void az(final boolean b) {
        this.gz();
        if (this.dq.j(b)) {
            this.c7();
        }
    }
    
    public final void a0(final boolean b) {
        this.gz();
        if (this.dq.a(b)) {
            this.c7();
        }
    }
    
    public final void a1(final boolean b) {
        this.gz();
        if (this.dq.b(b)) {
            this.c7();
        }
    }
    
    public final void a2(final boolean b) {
        this.gz();
        if (this.dq.c(b)) {
            this.c7();
        }
    }
    
    public final void a3(final boolean b) {
        this.gz();
        if (this.dq.g(b)) {
            this.c7();
        }
    }
    
    public final void a4(final boolean b) {
        this.gz();
        if (this.dq.i(b)) {
            this.c7();
        }
    }
    
    public final void a5(final boolean b) {
        this.gz();
        if (this.dq.d(b)) {
            this.c7();
        }
    }
    
    public final void a6(final boolean b) {
        this.gz();
        if (this.dq.f(b)) {
            this.c7();
        }
    }
    
    public final void t(final String s) {
        this.gz();
        this.dq.b(s);
    }
    
    public final int et() {
        this.gz();
        return this.dq.h();
    }
    
    public final int eu() {
        this.gz();
        return this.dq.i();
    }
    
    public final void a7(final boolean b) {
        this.gz();
        if (this.dq.h(b)) {
            this.c7();
        }
    }
    
    public final void a8(final boolean b) {
        this.gz();
        if (this.dq.e(b)) {
            this.c7();
        }
    }
    
    public final void z(final int n) {
        this.gz();
        this.dq.a(n);
    }
    
    public final String ev() {
        return this.b3;
    }
    
    public final int ew() {
        if (this.b3 != null) {
            return this.b3.length();
        }
        return 0;
    }
    
    protected final void a9(final boolean aj) {
        this.aj = aj;
    }
    
    protected final boolean ex() {
        return this.aj;
    }
    
    public final void ba(final boolean ag) {
        this.ag = ag;
    }
    
    protected final boolean ey() {
        return this.ah;
    }
    
    protected final void bb(final boolean by) {
        this.by = by;
    }
    
    protected final boolean ez() {
        return this.by;
    }
    
    public final boolean e0() {
        return this.af;
    }
    
    private final void f(final int n, final int n2) throws Exception {
        this.am = false;
        throw new Exception(String.valueOf(String.valueOf(new StringBuffer("Invalid annotation type (").append(this.a(n2, true)).append(") for this method call ").append(n).append(" (please refer to API manual)"))));
    }
    
    private final void g(final int n, final int n2) throws Exception {
        throw new Exception(String.valueOf(String.valueOf(new StringBuffer("(").append(this.a(n2, true)).append("/").append(n).append(") Annotation type has already been set to ").append(this.a(this.n, true)))));
    }
    
    public final void bc(final boolean a0) {
        this.a0 = a0;
    }
    
    public final boolean e1() {
        return this.a0;
    }
    
    public final void bd(final boolean a1) {
        this.a1 = a1;
    }
    
    public final boolean e2() {
        return this.a1;
    }
    
    public final void f(final double ay) {
        this.ay = ay;
    }
    
    public final double e3() {
        return this.ay;
    }
    
    public final void g(final double aw) {
        this.aw = aw;
    }
    
    public final double e4() {
        return this.aw;
    }
    
    public final void be(final boolean ax) {
        this.ax = ax;
    }
    
    public final boolean e5() {
        return this.ax;
    }
    
    public final void h(final double n) {
        final char c = '°';
        String s = null;
        switch (ji.util.d.u()) {
            case 1: {
                final int n2 = (int)n;
                final double n3 = n - n2;
                final double n4 = 0.016666666666666666;
                final int n5 = (int)(n3 / n4);
                s = String.valueOf(String.valueOf(new StringBuffer("").append(n2).append(c).append(" ").append(n5).append("' ").append((int)((n3 - n5 * n4) / (n4 / 60.0))).append("\"")));
                break;
            }
            case 2: {
                s = String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(6.283185307179586 * n / 360.0)))))).concat(" rad");
                break;
            }
            default: {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(n))))))).append(" ").append(c)));
                break;
            }
        }
        this.m(s);
    }
    
    public final void i(final double n) {
        final double n2 = ji.util.d.s() * n / this.bc();
        String s = null;
        switch (ji.util.d.v()) {
            case 1: {
                s = String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(n2 * 2.54)))))).concat(" cm");
                break;
            }
            case 2: {
                s = String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(n2 * 25.4)))))).concat(" mm");
                break;
            }
            case 3: {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(n2))))))).append("\", ").append(ji.util.d.i("".concat(String.valueOf(String.valueOf(n2 * 2.54))))).append(" cm")));
                break;
            }
            default: {
                s = String.valueOf(String.valueOf(ji.util.d.i("".concat(String.valueOf(String.valueOf(n2)))))).concat("\"");
                break;
            }
        }
        this.m(s);
    }
    
    public final void u(final String ch) {
        this.ch = ch;
    }
    
    public final String e6() {
        return this.ch;
    }
    
    public final boolean e7() {
        return !ji.util.d.by(this.ch);
    }
    
    public static final double[] v(final String s) {
        double[] array = null;
        if (!ji.util.d.by(s)) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
            if (stringTokenizer.countTokens() == 2) {
                array = new double[] { ji.util.d.a(stringTokenizer.nextToken(), 1.0), ji.util.d.a(stringTokenizer.nextToken(), 1.0) };
                if (array[0] <= 0.0 || array[1] <= 0.0) {
                    array = null;
                }
            }
        }
        return array;
    }
    
    public final void a(final int n, final long n2, final long n3, final long n4, final long n5) throws Exception {
        this.gw();
        if (n != 1 && n != 2 && n != 11 && n != 13 && n != 4 && n != 5) {
            this.f(1, n);
        }
        else {
            if (this.am) {
                this.g(1, n);
            }
            this.aj(n);
            this.am = true;
            switch (this.n = n) {
                case 1: {
                    this.m = n;
                    this.r = n2;
                    this.s = n3;
                    this.v = n4;
                    this.w = n5;
                    break;
                }
                case 11:
                case 13: {
                    this.m = 1;
                    this.r = n2;
                    this.s = n3;
                    this.v = n4;
                    this.w = n5;
                    break;
                }
                case 4:
                case 5: {
                    this.m = n;
                    this.r = n2;
                    this.s = n3;
                    this.t = n4;
                    this.u = n5;
                    this.ba = this.a9;
                    break;
                }
                case 2: {
                    this.m = n;
                    this.r = n2;
                    this.s = n3;
                    this.z = n4;
                    this.aa = n5;
                    break;
                }
            }
        }
        this.p = this.r;
        this.q = this.s;
    }
    
    public final void a(final int n, final long p7, final long q, final long n2, final long n3, final String s, final boolean ey) throws Exception {
        this.gw();
        if (n != 8) {
            this.f(2, n);
        }
        else {
            final Color ba = this.ba;
            this.ey = ey;
            this.a(n, s, this.ba, ey);
            this.aj(n);
            if (n2 > 0 && n3 > 0) {
                this.bk = new d4(this.a(0L, 0L, n2, n3));
            }
            if (p7 > 0 && q > 0) {
                this.bk.translate((int)p7, (int)q);
            }
        }
        this.p = p7;
        this.q = q;
    }
    
    public final void a(final String s, final int n, final int n2, final String s2, final int n3, final int n4, final boolean f8) throws Exception {
        this.gw();
        if (n == 7) {
            this.f8 = f8;
        }
        if (n != 7 && n != 9) {
            this.f(2, n);
        }
        else {
            if (this.am) {
                this.g(2, n);
            }
            this.aj(n);
            this.am = true;
            this.n = n;
            this.m = n;
            this.co = null;
            this.cp = null;
            this.gs();
            this.cz();
            this.do = null;
            this.ak = true;
            this.b3 = this.aa(s2);
            this.r = n3;
            this.s = n4;
            this.ej();
            this.ab(s);
        }
        this.p = n3;
        this.q = n4;
    }
    
    public final void a(final String s, final int n, final String b3, final int n2, final int n3) throws Exception {
        this.gw();
        if (n != 9) {
            this.f(2, n);
        }
        else {
            if (this.am) {
                this.g(2, n);
            }
            this.aj(n);
            this.am = true;
            this.n = n;
            this.m = 9;
            this.co = null;
            this.cp = null;
            this.gs();
            this.cz();
            this.do = null;
            this.ak = true;
            this.b3 = b3;
            this.r = n2;
            this.s = n3;
            this.ab(s);
        }
        this.p = n2;
        this.q = n3;
    }
    
    private final void ab(final String s) {
        if (this.b3 != null && this.e9()) {
            this.b3 = ji.util.d.b(this.b3, "<date>", ji.util.d.a(ji.util.d.e2(), true));
            this.b3 = ji.util.d.b(this.b3, "<dateonly>", ji.util.d.b(ji.util.d.e2(), true));
            this.b3 = ji.util.d.b(this.b3, "<user>", s);
            this.b3 = ji.util.d.b(this.b3, "<user>", "<userId>");
            if (s != null) {
                this.b3 = ji.util.d.b(this.b3, "<userid>", s);
            }
            this.n = 7;
        }
    }
    
    private final String a(final c c, final int n, final String s) {
        String b = s;
        if (c != null && s != null) {
            try {
                if ((this.n == 7 || this.n == 8) && !ji.util.d.by(b)) {
                    final String al = ji.util.d.al();
                    final ax d = c.d();
                    while (d.a()) {
                        final fw fw = (fw)c.d(d.b());
                        if (fw.a == n || fw.a < 0) {
                            if (fw.c != null && fw.c.toLowerCase().equals(al)) {
                                fw.c = "";
                            }
                            b = ji.util.d.b(b, fw.b, fw.c);
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public final boolean e8() {
        boolean b = true;
        if (this.e9() && this.ev() != null && ji.util.d.bc(this.ev()).length() > 0) {
            b = false;
        }
        return b;
    }
    
    public final boolean e9() {
        return this.b3 != null && !this.b3.toLowerCase().startsWith("image:".toLowerCase());
    }
    
    public final String fa() {
        this.be = null;
        String s = ji.util.d.bc(this.b3.substring("image:".length()));
        try {
            if (s.endsWith(">")) {
                for (int i = s.length() - 1; i >= 0; --i) {
                    if (s.charAt(i) == '<') {
                        final String substring = s.substring(i + 1, s.length() - 1);
                        s = s.substring(0, i);
                        this.be = ji.util.d.b1(substring);
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    protected final Color fb() {
        if (this.bf != null) {
            return this.bf;
        }
        return this.be;
    }
    
    public final void g(final Color bf) {
        this.bf = bf;
        Label_0081: {
            try {
                this.b3 = ji.util.d.bc(this.b3);
                if (this.b3.endsWith(">")) {
                    Block_6: {
                        for (int i = this.b3.length() - 1; i >= 0; --i) {
                            if (this.b3.charAt(i) == '<') {
                                break Block_6;
                            }
                        }
                        break Label_0081;
                    }
                    int i = 0;
                    this.b3 = this.b3.substring(0, i);
                }
            }
            catch (Exception ex) {}
        }
        if (this.bf != null) {
            this.b3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.b3))).append("<").append(this.bf.getRed()).append(",").append(this.bf.getGreen()).append(",").append(this.bf.getBlue()).append(">")));
        }
    }
    
    public final void a(final int n, final Polygon polygon) throws Exception {
        this.gw();
        if (n != 3 && n != 6 && n != 10 && n != 12 && n != 14) {
            this.f(4, n);
        }
        else {
            if (this.am) {
                this.g(4, n);
            }
            this.aj(n);
            this.am = true;
            this.n = n;
            this.m = n;
            this.bk = new d4(polygon);
            if (n == 6 || n == 10) {
                this.ah = false;
                this.m = 6;
            }
            else {
                this.ah = true;
                this.m = 3;
            }
        }
        this.p = this.r;
        this.q = this.s;
    }
    
    protected final void a(final int n, final String s, Color a9, final boolean ey) throws Exception {
        this.gw();
        if (n != 8) {
            this.f(7, n);
        }
        else {
            if (this.am) {
                this.g(7, n);
            }
            this.aj(n);
            this.am = true;
            this.ey = ey;
            this.ag = true;
            this.n = n;
            this.m = 3;
            this.b3 = this.aa(s);
            this.bk = new d4(this.a(0L, 0L, ji.util.d.cu(), (long)ji.util.d.cw()));
            this.bg = 1;
            this.ej();
            a9 = new Color(255, 255, 153);
            this.a9 = a9;
        }
        this.p = this.r;
        this.q = this.s;
    }
    
    public final void a(final int n, final String[] array) throws Exception {
        this.gw();
        if (n != 16 && n != 17) {
            this.f(8, n);
        }
        else {
            if (this.am) {
                this.g(8, n);
            }
            this.aj(n);
            this.am = true;
            this.ag = true;
            this.n = n;
            this.m = 16;
            this.ex = new String[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.ex[i] = array[i];
            }
            this.ej();
        }
    }
    
    public final void aa(final int n) throws Exception {
        this.gw();
        if (n != 18) {
            this.f(80, n);
        }
        else {
            if (this.am) {
                this.g(8, n);
            }
            this.aj(n);
            this.am = true;
            this.ag = true;
            this.n = n;
            this.m = 16;
            this.ej();
        }
    }
    
    public final String[] fc() {
        return this.ex;
    }
    
    public final String[] fd() {
        return this.ex;
    }
    
    public final void a(final Polygon polygon) {
        if (this.bk != null) {
            this.bk = ji.util.d.a(new d4(polygon));
            this.bp = null;
        }
    }
    
    public final da fe() {
        return this.a(true, true);
    }
    
    public final da ff() {
        return this.bf(true);
    }
    
    public final da bf(final boolean b) {
        return this.a(b, false);
    }
    
    private final da a(final boolean b, final boolean b2) {
        if (!this.e9() && this.n == 9 && this.c8 != null) {
            da da;
            if (b) {
                da = new da(this.r, this.s, this.v, this.w);
            }
            else if (this.c9 == 90 || this.c9 == 270) {
                da = new da(this.r, this.s, this.w, this.v);
            }
            else {
                da = new da(this.r, this.s, this.v, this.w);
            }
            if (b2) {
                da.c = Math.max(da.c, this.bg);
                da.d = Math.max(da.d, this.bg);
            }
            return da;
        }
        if (this.e9() && this.n != 8) {
            da da2;
            if (b) {
                da2 = new da(this.f3());
            }
            else {
                da2 = new da(this.f4());
            }
            da2.a = this.r;
            da2.b = this.s;
            return da2;
        }
        if (this.n == 8 && this.bk != null) {
            return new da(this.bk.getBounds());
        }
        if (this.n == 4 || this.n == 5) {
            long r = this.r;
            long s = this.s;
            long max = this.t - this.r;
            long max2 = this.u - this.s;
            if (max < 0) {
                r += max;
                max = -max;
            }
            if (max2 < 0) {
                s += max2;
                max2 = -max2;
            }
            if (b2) {
                max = Math.max(max, this.bg);
                max2 = Math.max(max2, this.bg);
            }
            return new da(r, s, max, max2);
        }
        if (this.n == 10) {
            final da da3 = new da(this.bk.getBounds());
            if (b2) {
                da3.c = Math.max(da3.c, this.bg);
                da3.d = Math.max(da3.d, this.bg);
            }
            return da3;
        }
        final da da4 = new da(this.r, this.s, this.v, this.w);
        if (b2) {
            da4.c = Math.max(da4.c, this.bg);
            da4.d = Math.max(da4.d, this.bg);
        }
        return da4;
    }
    
    public final boolean fg() {
        if (this.n == 16) {
            return true;
        }
        if (this.n == 18) {
            return true;
        }
        da da;
        if (this.bk != null) {
            da = new da(this.bk.getBounds());
        }
        else {
            if (this.n == 7 || this.n == 9 || this.n == 8) {
                return true;
            }
            if (this.n == 4 || this.n == 5) {
                long r = this.r;
                long s = this.s;
                long n = this.t - this.r;
                long n2 = this.u - this.s;
                if (n < 0) {
                    r += n;
                    n = -n;
                }
                if (n2 < 0) {
                    s += n2;
                    n2 = -n2;
                }
                da = new da(r, s, n, n2);
            }
            else if (this.n == 2) {
                da = new da(this.r - this.z / 2, this.s - this.aa / 2, this.z * 2, this.aa * 2);
            }
            else {
                da = new da(this.r, this.s, this.v, this.w);
            }
        }
        return da != null && (da.c > 0 || da.d > 0);
    }
    
    public final da fh() {
        return new da(this.r, this.s, this.v, this.w);
    }
    
    public final double fi() {
        return this.di;
    }
    
    public final int fj() {
        return (int)this.v;
    }
    
    public final void h(final da da) {
        final double[] v = v(this.ch);
        if (v != null) {
            if (da.d != this.w) {
                da.c = Math.round(v[1] / v[0] * da.d);
            }
            if (da.c != this.v) {
                da.d = Math.round(v[0] / v[1] * da.c);
            }
        }
        this.r = da.a;
        this.v = da.c;
        this.s = da.b;
        this.w = da.d;
        this.bp = null;
    }
    
    public final void a(final long v, final long w) {
        this.v = v;
        this.w = w;
        this.bp = null;
    }
    
    public final long fk() {
        return this.z;
    }
    
    public final long fl() {
        return this.aa;
    }
    
    public final void b(long round, long round2) {
        final double[] v = v(this.ch);
        if (v != null) {
            if (round2 != this.aa) {
                round = Math.round(v[1] / v[0] * round2);
            }
            if (round != this.z) {
                round2 = Math.round(v[0] / v[1] * round);
            }
        }
        this.aa = round2;
        this.z = round;
        this.bp = null;
    }
    
    public final d4 fm() {
        if (this.bk != null) {
            return new d4(this.bk);
        }
        return null;
    }
    
    public final d5 fn() {
        return new d5(this.r, this.s);
    }
    
    public final void c(final d5 d5) {
        this.r = d5.a;
        this.s = d5.b;
    }
    
    public final d5 fo() {
        return new d5(this.t, this.u);
    }
    
    public final void d(final d5 d5) {
        this.t = d5.a;
        this.u = d5.b;
    }
    
    protected final void i(final da bp) {
        this.bp = bp;
    }
    
    public final da fp() {
        return new da(this.bp);
    }
    
    protected final void j(final da bq) {
        this.bq = bq;
    }
    
    protected final da fq() {
        return new da(this.bq);
    }
    
    public final void k(final da da) {
        if (da == null) {
            this.dr = null;
        }
        else {
            this.a(this.dr = new da(da));
        }
    }
    
    public final da fr() {
        return new da(this.dr);
    }
    
    public final dc fs() {
        return new dc(this.cv, this.cw);
    }
    
    public final void ab(final int c3) {
        this.c3 = c3;
    }
    
    public final long ft() {
        return this.p;
    }
    
    public final void g(final long p) {
        this.p = p;
    }
    
    public final long fu() {
        return this.q;
    }
    
    public final void h(final long q) {
        this.q = q;
    }
    
    public final boolean a(final dg dg, final ad ad) {
        return dg != null && dg.d(ad).equals(this.d(ad));
    }
    
    public final da fv() {
        switch (this.m) {
            case 1: {
                this.br = this.ff();
                break;
            }
            case 7:
            case 9: {
                this.br = this.ff();
                this.br.c = this.v;
                this.br.d = this.w;
                break;
            }
            case 2: {
                this.br = new da(this.r - this.z, this.s - this.aa, 2 * this.z, 2 * this.aa);
                break;
            }
            case 3:
            case 6: {
                this.br = new da(this.bk.getBounds());
                break;
            }
            case 4:
            case 5: {
                final int min = Math.min((int)this.r, (int)this.t);
                final int max = Math.max((int)this.r, (int)this.t);
                final int min2 = Math.min((int)this.s, (int)this.u);
                this.br = new da(min, min2, max - min, Math.max((int)this.s, (int)this.u) - min2);
                break;
            }
        }
        return this.br;
    }
    
    public final void ac(final int bu) {
        this.bu = bu;
    }
    
    protected final int fw() {
        return this.bu;
    }
    
    public final boolean fx() {
        if (this.bu >= 0) {
            this.bu = -1;
            return true;
        }
        return false;
    }
    
    protected final void b(final long n, final long n2, final long n3, final long n4) {
        this.bs.c(new da(n, n2, n3, n4));
    }
    
    protected final da ad(final int n) {
        if (this.bs.b() > n) {
            return (da)this.bs.b(n);
        }
        return null;
    }
    
    protected final int fy() {
        return this.bs.b();
    }
    
    protected final void fz() {
        this.bs.c();
    }
    
    protected final void e(final int n, final int n2) {
        this.bt.a("".concat(String.valueOf(String.valueOf(n2))), new Integer(n));
    }
    
    protected final int ae(final int n) {
        try {
            final Integer n2 = (Integer)this.bt.d("".concat(String.valueOf(String.valueOf(n))));
            if (n2 != null) {
                return n2;
            }
            return -1;
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    protected final void f0() {
        this.bt.c();
    }
    
    protected final void f1() {
        this.bp = null;
    }
    
    private final int b(final dx dx) {
        return ji.annotate.dy.b(dx);
    }
    
    private final int c(final dx dx) {
        return ji.annotate.dy.a(dx);
    }
    
    public final void c(final Rectangle rectangle) {
        if (rectangle != null) {
            this.f1 = new Rectangle(rectangle);
            this.f2();
        }
    }
    
    public final void f2() {
        if (this.f1 != null) {
            final int cj = this.cj();
            this.f2 = new Rectangle(this.f1);
            if (cj == 90 || cj == 270) {
                final int width = this.f2.width;
                this.f2.width = this.f2.height;
                this.f2.height = width;
            }
        }
    }
    
    public final Rectangle f3() {
        return this.f1;
    }
    
    public final Rectangle f4() {
        return this.f2;
    }
    
    public final String f5() {
        this.gz();
        return this.dq.g();
    }
    
    public final boolean a(final dx dx, final int n, final int n2) {
        boolean b = true;
        final int b2 = this.b(dx);
        final int c = this.c(dx);
        switch (this.m) {
            case 1:
            case 2: {
                if (ji.util.d.bf()) {
                    if (this.r + n < 0) {
                        b = false;
                    }
                    if (this.s + n2 < 0) {
                        b = false;
                    }
                    if (dx != null) {
                        if (this.r + n + this.v >= c) {
                            b = false;
                        }
                        if (this.s + n2 + this.w >= b2) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    this.r += n;
                    this.s += n2;
                    break;
                }
                break;
            }
            case 7:
            case 9: {
                if (ji.util.d.bf()) {
                    if (this.r + n < 0) {
                        b = false;
                    }
                    if (this.s + n2 < 0) {
                        b = false;
                    }
                }
                if (b) {
                    this.r += n;
                    this.s += n2;
                    break;
                }
                break;
            }
            case 3:
            case 6: {
                final Rectangle bounds = this.bk.getBounds();
                if (ji.util.d.bf()) {
                    if (bounds.x + n < 0) {
                        b = false;
                    }
                    if (bounds.y + n2 < 0) {
                        b = false;
                    }
                    if (dx != null) {
                        if (bounds.x + n + bounds.width >= c) {
                            b = false;
                        }
                        if (bounds.y + n2 + bounds.height >= b2) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    this.bk.translate(n, n2);
                    break;
                }
                break;
            }
            case 4:
            case 5: {
                if (ji.util.d.bf()) {
                    if (this.r + n < 0) {
                        b = false;
                    }
                    if (this.s + n2 < 0) {
                        b = false;
                    }
                    if (this.t + n < 0) {
                        b = false;
                    }
                    if (this.u + n2 < 0) {
                        b = false;
                    }
                    if (dx != null) {
                        if (this.r + n + this.v >= c) {
                            b = false;
                        }
                        if (this.s + n2 + this.w >= b2) {
                            b = false;
                        }
                        if (this.t + n + this.v >= c) {
                            b = false;
                        }
                        if (this.u + n2 + this.w >= b2) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    this.r += n;
                    this.s += n2;
                    this.t += n;
                    this.u += n2;
                    break;
                }
                break;
            }
        }
        this.bp = null;
        return b;
    }
    
    protected final void e(final d4 d4) {
        if (d4 != null) {
            this.bl = ji.util.d.a(d4.a());
        }
        else {
            this.bl = null;
            this.b1 = 1.0;
            this.b2 = 1.0;
        }
    }
    
    protected final d4 f6() {
        return this.bl;
    }
    
    protected final void j(final double b1) {
        this.b1 = b1;
    }
    
    protected final double f7() {
        return this.b1;
    }
    
    protected final void k(final double b2) {
        this.b2 = b2;
    }
    
    protected final double f8() {
        return this.b2;
    }
    
    public final String d(final ad ad) {
        this.e(ad);
        final StringBuffer sb = new StringBuffer("");
        sb.append("".concat(String.valueOf(String.valueOf(this.af))));
        sb.append("".concat(String.valueOf(String.valueOf(this.af))));
        sb.append("".concat(String.valueOf(String.valueOf(this.m))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bm))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bn))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bo))));
        sb.append("".concat(String.valueOf(String.valueOf(this.r))));
        sb.append("".concat(String.valueOf(String.valueOf(this.s))));
        sb.append("".concat(String.valueOf(String.valueOf(this.p))));
        sb.append("".concat(String.valueOf(String.valueOf(this.q))));
        sb.append("".concat(String.valueOf(String.valueOf(this.t))));
        sb.append("".concat(String.valueOf(String.valueOf(this.u))));
        sb.append("".concat(String.valueOf(String.valueOf(this.v))));
        sb.append("".concat(String.valueOf(String.valueOf(this.w))));
        sb.append("".concat(String.valueOf(String.valueOf(this.z))));
        sb.append("".concat(String.valueOf(String.valueOf(this.aa))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ai))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ag))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ah))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a9))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ba))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bg))));
        sb.append("".concat(String.valueOf(String.valueOf(this.eu))));
        sb.append("".concat(String.valueOf(String.valueOf(this.j))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ae))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bu))));
        sb.append("".concat(String.valueOf(String.valueOf(this.aj))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bv))));
        sb.append("".concat(String.valueOf(String.valueOf(this.by))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ak))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b1))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b2))));
        sb.append("".concat(String.valueOf(String.valueOf(this.n))));
        sb.append("".concat(String.valueOf(String.valueOf(this.aa(this.b3)))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b6))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b9))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b4))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b7))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b5))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b8))));
        sb.append("".concat(String.valueOf(String.valueOf(new Dimension(this.ca)))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cd))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ce))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cg))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ck))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b0))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cl))));
        sb.append("".concat(String.valueOf(String.valueOf(this.gc))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cm))));
        sb.append("".concat(String.valueOf(String.valueOf(this.dd))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cq))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cr))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cs))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cu))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cx))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a8))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a5))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ev))));
        sb.append("".concat(String.valueOf(String.valueOf(this.al))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ew))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fm.a))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ey))));
        sb.append("".concat(String.valueOf(String.valueOf(this.e6))));
        sb.append("".concat(String.valueOf(String.valueOf(this.an))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a6))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ap))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fl))));
        sb.append("".concat(String.valueOf(String.valueOf(this.c5))));
        sb.append("".concat(String.valueOf(String.valueOf(this.el))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fc))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fd))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fe))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ff))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fa))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fb))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ao))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fq))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fr))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fu))));
        sb.append("".concat(String.valueOf(String.valueOf(this.cz))));
        sb.append("".concat(String.valueOf(String.valueOf(this.dm.p()))));
        sb.append("".concat(String.valueOf(String.valueOf(this.dn.p()))));
        sb.append("".concat(String.valueOf(String.valueOf(new Rectangle(this.fw)))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fx))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f4))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a2))));
        sb.append("".concat(String.valueOf(String.valueOf(new Rectangle(this.f1)))));
        sb.append("".concat(String.valueOf(String.valueOf(new Rectangle(this.f2)))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f3))));
        sb.append("".concat(String.valueOf(String.valueOf(this.as))));
        sb.append("".concat(String.valueOf(String.valueOf(this.at))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a3))));
        sb.append("".concat(String.valueOf(String.valueOf(this.eg))));
        sb.append("".concat(String.valueOf(String.valueOf(this.av))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ei))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ek))));
        sb.append("".concat(String.valueOf(String.valueOf(this.eh))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f5.width))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f5.height))));
        sb.append("".concat(String.valueOf(String.valueOf(this.bf))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fz))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f9))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ch))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a0))));
        sb.append("".concat(String.valueOf(String.valueOf(this.a1))));
        sb.append("".concat(String.valueOf(String.valueOf(this.aw))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ax))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ay))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f8))));
        sb.append("".concat(String.valueOf(String.valueOf(this.gd))));
        sb.append("".concat(String.valueOf(String.valueOf(this.ge))));
        sb.append("".concat(String.valueOf(String.valueOf(this.hs))));
        sb.append("".concat(String.valueOf(String.valueOf(this.hu))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fk))));
        sb.append("".concat(String.valueOf(String.valueOf(this.fj))));
        try {
            if (this.bl != null) {
                sb.append("".concat(String.valueOf(String.valueOf(this.bl))));
            }
        }
        catch (Exception ex) {}
        try {
            if (this.bk != null) {
                sb.append("".concat(String.valueOf(String.valueOf(this.bk))));
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.bp != null) {
                sb.append("".concat(String.valueOf(String.valueOf(this.bp))));
            }
        }
        catch (Exception ex3) {}
        try {
            if (this.bq != null) {
                sb.append("".concat(String.valueOf(String.valueOf(this.bq))));
            }
        }
        catch (Exception ex4) {}
        try {
            if (this.br != null) {
                sb.append("".concat(String.valueOf(String.valueOf(this.br))));
            }
        }
        catch (Exception ex5) {}
        try {
            if (this.ex != null) {
                for (int i = 0; i < this.ex.length; ++i) {
                    sb.append("".concat(String.valueOf(String.valueOf(this.ex[i]))));
                }
            }
        }
        catch (Exception ex6) {}
        try {
            if (this.fo != null) {
                for (int j = 0; j < this.fo.b(); ++j) {
                    sb.append("".concat(String.valueOf(String.valueOf(this.fo.b(j)))));
                }
            }
        }
        catch (Exception ex7) {}
        Label_2741: {
            if (this.fm != null) {
                if (ji.util.i.c(238)) {
                    synchronized (this.fm) {
                        Label_2686: {
                            try {
                                for (int k = 0; k < this.fm.b(); ++k) {
                                    sb.append("".concat(String.valueOf(String.valueOf(this.fm.b(k)))));
                                }
                                break Label_2686;
                            }
                            catch (Exception ex8) {
                                // monitorexit(this.fm)
                                break Label_2741;
                            }
                        }
                    }
                }
                try {
                    for (int l = 0; l < this.fm.b(); ++l) {
                        sb.append("".concat(String.valueOf(String.valueOf(this.fm.b(l)))));
                    }
                }
                catch (Exception ex9) {}
            }
            try {
                if (this.fv != null) {
                    for (int n = 0; n < this.fv.b(); ++n) {
                        sb.append("".concat(String.valueOf(String.valueOf(this.fv.b(n)))));
                    }
                }
            }
            catch (Exception ex10) {}
        }
        try {
            if (this.bs != null) {
                for (int n2 = 0; n2 < this.bs.b(); ++n2) {
                    sb.append("".concat(String.valueOf(String.valueOf(this.bs.b(n2)))));
                }
            }
        }
        catch (Exception ex11) {}
        this.gz();
        sb.append("".concat(String.valueOf(String.valueOf(this.dq.n()))));
        try {
            if (this.bt != null) {
                for (int n3 = 0; n3 < this.bt.b(); ++n3) {
                    sb.append("".concat(String.valueOf(String.valueOf(this.bt.b(n3)))));
                }
            }
        }
        catch (Exception ex12) {}
        return sb.toString();
    }
    
    public final dg c(final Component component) {
        final dg dg = new dg(this.bm, this.fn, this.f4, this.cz);
        try {
            dg.hp = this.hp;
            dg.ho = this.ho;
            dg.hq = this.hq;
            dg.af = this.af;
            dg.m = this.m;
            dg.bm = this.bm;
            dg.bn = this.bn;
            dg.bo = this.bo;
            dg.r = this.r;
            dg.s = this.s;
            dg.p = this.p;
            dg.q = this.q;
            dg.t = this.t;
            dg.u = this.u;
            dg.v = this.v;
            dg.w = this.w;
            dg.hz = this.hz;
            dg.e5 = this.e5;
            dg.e0 = this.e0;
            dg.e1 = this.e1;
            dg.e2 = this.e2;
            dg.e3 = this.e3;
            dg.e4 = this.e4;
            dg.x = this.x;
            dg.y = this.y;
            dg.z = this.z;
            dg.aa = this.aa;
            dg.ab = this.ab;
            dg.ac = this.ac;
            dg.ai = this.ai;
            dg.ag = this.ag;
            dg.ah = this.ah;
            dg.a9 = this.a9;
            dg.ba = this.ba;
            dg.bg = this.bg;
            dg.bi = this.bi;
            dg.eu = this.eu;
            dg.j = this.j;
            dg.ae = this.ae;
            dg.bu = this.bu;
            dg.aj = this.aj;
            dg.bv = this.bv;
            dg.bw = this.bw;
            dg.by = this.by;
            dg.ak = this.ak;
            dg.b1 = this.b1;
            dg.b2 = this.b2;
            dg.n = this.n;
            dg.a(component, this.aa(this.b3), null);
            dg.b6 = this.b6;
            dg.b9 = this.b9;
            dg.b4 = this.b4;
            dg.b7 = this.b7;
            dg.b5 = this.b5;
            dg.b8 = this.b8;
            if (this.ca != null) {
                dg.ca = new Dimension(this.ca);
            }
            if (this.cc != null) {
                dg.cc = new Dimension(this.cc);
            }
            dg.cd = this.cd;
            dg.ce = this.ce;
            dg.cf = this.cf;
            dg.cg = this.cg;
            dg.ck = this.ck;
            dg.b0 = this.b0;
            dg.cl = this.cl;
            dg.gc = this.gc;
            dg.cm = this.cm;
            if (this.dd != null) {
                dg.dd = this.dd.c();
            }
            dg.cq = this.cq;
            dg.c6 = this.c6;
            dg.da = this.da;
            dg.c4 = this.c4;
            dg.cr = this.cr;
            dg.cs = this.cs;
            dg.cu = this.cu;
            dg.cx = this.cx;
            dg.au = this.au;
            dg.ci = this.ci;
            dg.dx = this.dx;
            dg.f0 = this.f0;
            dg.a8 = this.a8;
            dg.a5 = this.a5;
            dg.ev = this.ev;
            dg.al = this.al;
            dg.ew = this.ew;
            dg.ey = this.ey;
            dg.e6 = this.e6;
            dg.an = this.an;
            dg.a6 = this.a6;
            dg.ap = this.ap;
            dg.fl = this.fl;
            dg.c5 = this.c5;
            dg.el = this.el;
            dg.l = this.l;
            dg.fc = this.fc;
            dg.fd = this.fd;
            dg.fe = this.fe;
            dg.ff = this.ff;
            dg.fa = this.fa;
            dg.fb = this.fb;
            dg.ao = this.ao;
            dg.fq = this.fq;
            dg.fr = this.fr;
            dg.fu = this.fu;
            dg.cz = this.cz;
            dg.dm = this.dm.o();
            dg.dn = this.dn.o();
            dg.fw = new Rectangle(this.fw);
            dg.fx = this.fx;
            dg.f4 = this.f4;
            dg.a2 = this.a2;
            dg.d(component);
            dg.f1 = new Rectangle(this.f1);
            dg.f2 = new Rectangle(this.f2);
            dg.f3 = this.f3;
            dg.as = this.as;
            dg.at = this.at;
            dg.av = this.av;
            dg.a3 = this.a3;
            dg.eg = this.eg;
            dg.ei = this.ei;
            dg.ek = this.ek;
            dg.eh = this.eh;
            dg.f5.width = this.f5.width;
            dg.f5.height = this.f5.height;
            dg.bf = this.bf;
            dg.fz = this.fz;
            dg.f9 = this.f9;
            dg.ch = this.ch;
            dg.a0 = this.a0;
            dg.a1 = this.a1;
            dg.aw = this.aw;
            dg.ax = this.ax;
            dg.ay = this.ay;
            dg.f8 = this.f8;
            dg.gd = this.gd;
            dg.ge = this.ge;
            dg.bd = this.bd;
            dg.az = this.az;
            dg.c0 = this.c0;
            dg.c1 = this.c1;
            dg.c2 = this.c2;
            dg.c3 = this.c3;
            if (this.c8 != null) {
                dg.c8 = new Dimension(this.c8.width, this.c8.height);
                dg.c9 = this.c9;
                dg.db = true;
            }
            dg.g1 = this.g1;
            dg.g2 = this.g2;
            dg.g8 = this.g8;
            dg.g9 = this.g9;
            dg.ha = this.ha;
            dg.hb = this.hb;
            dg.hc = this.hc;
            dg.hd = this.hd;
            dg.g3 = this.g3;
            dg.hf = this.hf;
            dg.hg = this.hg;
            dg.hh = this.hh;
            dg.hn = this.hn;
            dg.hi = this.hi;
            dg.hv = this.hv;
            dg.hm = this.hm;
            dg.ad = this.ad;
            dg.hs = this.hs;
            dg.hu = this.hu;
            dg.f6 = this.f6;
            dg.gf = this.gf;
            dg.gg = this.gg;
            dg.bh = this.bh;
            dg.fj = this.fj;
            dg.fk = this.fk;
            dg.hw = this.hw;
            dg.hx = this.hx;
            try {
                if (this.he != null) {
                    dg.he = this.he.c();
                }
            }
            catch (Exception ex) {}
            try {
                if (this.g4 != null) {
                    dg.g4 = this.g4.a();
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.g5 != null) {
                    dg.g5 = this.g5.a();
                }
            }
            catch (Exception ex3) {}
            try {
                if (this.g6 != null) {
                    dg.g6 = this.g6.a();
                }
            }
            catch (Exception ex4) {}
            try {
                if (this.g7 != null) {
                    dg.g7 = new Rectangle(this.g7);
                }
            }
            catch (Exception ex5) {}
            try {
                if (this.bl != null) {
                    dg.bl = this.bl.a();
                }
            }
            catch (Exception ex6) {}
            try {
                if (this.bk != null) {
                    dg.bk = this.bk.a();
                }
            }
            catch (Exception ex7) {}
            try {
                if (this.bp != null) {
                    dg.bp = this.bp.c();
                }
            }
            catch (Exception ex8) {}
            try {
                if (this.bq != null) {
                    dg.bq = this.bq.c();
                }
            }
            catch (Exception ex9) {}
            try {
                if (this.br != null) {
                    dg.br = this.br.c();
                }
            }
            catch (Exception ex10) {}
            try {
                if (this.ex != null) {
                    dg.ex = new String[this.ex.length];
                    for (int i = 0; i < this.ex.length; ++i) {
                        dg.ex[i] = this.ex[i];
                    }
                }
            }
            catch (Exception ex11) {}
            try {
                if (this.fo != null) {
                    for (int j = 0; j < this.fo.b(); ++j) {
                        dg.fo.c(this.fo.b(j));
                    }
                }
            }
            catch (Exception ex12) {}
            Label_1981: {
                try {
                    if (this.fm != null) {
                        if (ji.util.i.c(238)) {
                            synchronized (this.fm) {
                                for (int k = 0; k < this.fm.b(); ++k) {
                                    dg.fm.c(this.fm.b(k));
                                }
                                dg.fm.a = this.fm.a;
                                dg.fm.b = this.fm.b;
                                // monitorexit(this.fm)
                                break Label_1981;
                            }
                        }
                        for (int l = 0; l < this.fm.b(); ++l) {
                            dg.fm.c(this.fm.b(l));
                        }
                        dg.fm.a = this.fm.a;
                        dg.fm.b = this.fm.b;
                    }
                }
                catch (Exception ex13) {}
                try {
                    if (this.fv != null) {
                        for (int n = 0; n < this.fv.b(); ++n) {
                            dg.fv.c(this.fv.b(n));
                        }
                    }
                }
                catch (Exception ex14) {}
            }
            try {
                if (this.bs != null) {
                    for (int n2 = 0; n2 < this.bs.b(); ++n2) {
                        dg.bs.c(this.bs.b(n2));
                    }
                }
            }
            catch (Exception ex15) {}
            this.gz();
            dg.a(this.dq);
            try {
                if (this.bt != null) {
                    dg.bt = this.bt.a();
                }
            }
            catch (Exception ex16) {}
        }
        catch (Exception ex17) {}
        return dg;
    }
    
    public final void a(final d3 d3) {
        this.gz();
        this.dq.a(d3);
    }
    
    private final void gy() {
        try {
            if (this.fv != null) {
                this.fv.c();
                this.fv = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean f9() {
        return this.ga > 0 && this.gb > 0 && this.cx == 1;
    }
    
    public final int ga() {
        return this.ga;
    }
    
    public final int gb() {
        return this.gb;
    }
    
    public final void af(final int ga) {
        this.ga = ga;
    }
    
    public final void ag(final int gb) {
        this.gb = gb;
    }
    
    private final void gz() {
        try {
            if (this.dq == null) {
                this.dq = new d3(this.fn);
            }
            if (this.b3 != null) {
                this.dq.a(this.b3);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean gc() {
        final boolean g0 = this.g0();
        final boolean g2 = this.g1();
        this.c7();
        return g0 && g2;
    }
    
    private final boolean g0() {
        try {
            if (this.hj != null) {
                this.hj.c(4);
                this.hj = null;
            }
            return true;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    private final boolean g1() {
        try {
            if (this.hk != null) {
                this.hk.c(5);
                this.hk = null;
            }
            return true;
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
            return false;
        }
    }
    
    private final void g2() {
        try {
            if (this.dq != null) {
                this.dq.p();
                this.dq = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void gd() {
        try {
            this.gc();
            this.cv();
            this.co = null;
            this.cp = null;
            this.he = null;
            this.ge();
            try {
                if (this.gt != null) {
                    this.gt.c();
                    this.gt = null;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.gu != null) {
                    this.gu.a();
                    this.gu = null;
                }
            }
            catch (Exception ex2) {}
            this.gs();
            this.gy();
            this.cj = null;
            this.cz();
            this.do = null;
            this.bl = null;
            this.g4 = null;
            this.g5 = null;
            this.g6 = null;
            this.g7 = null;
            this.g8 = null;
            this.fz();
            this.g2();
        }
        catch (Exception ex3) {}
        this.cv();
    }
    
    public void ge() {
        if ((this.hw || this.f7) && !this.e9() && this.b3 != null) {
            try {
                String s = this.fa();
                if (s.startsWith("file:")) {
                    s = s.substring("file:".length());
                }
                if (s != null && ji.io.ac.d(s, this.fn)) {
                    ji.io.ac.c(s, this.fn);
                }
            }
            catch (Exception ex) {
                ji.io.h.f(this.fn, "Unable to delete cache file: ".concat(String.valueOf(String.valueOf(this.fa()))));
            }
        }
    }
    
    public final int gf() {
        return this.f6;
    }
    
    public final void ah(final int f6) {
        switch (f6) {
            case 0:
            case 1: {
                this.f6 = f6;
                break;
            }
        }
    }
    
    public void gg() {
        if (this.f6 == 0) {
            this.f6 = 1;
        }
        else {
            this.f6 = 0;
        }
        this.c7();
    }
    
    public final boolean gh() {
        return this.ai(this.d5());
    }
    
    public final boolean ai(final int n) {
        if (this.ci) {
            return n != 17 && n != 16 && n != 15;
        }
        return n != 17 && n != 16 && n != 15 && n != 18;
    }
    
    public final void bg(final boolean ci) {
        this.ci = ci;
    }
    
    public static final boolean a(final dg dg, final boolean b, final ad ad) {
        boolean b2 = dg.f() || ji.util.d.b();
        boolean bi = false;
        if (ad != null) {
            bi = ad.bi(21);
        }
        if (b2 || bi) {
            b2 = false;
            final int[] array = b ? dg.d : dg.c;
            for (int i = 0; i < array.length; ++i) {
                if (dg.d5() == array[i]) {
                    b2 = true;
                    break;
                }
            }
        }
        return b2;
    }
    
    public static final boolean w(final String s) {
        final String upperCase = s.toUpperCase();
        if (ac(upperCase)) {
            return true;
        }
        for (int i = 0; i < dg.e.length; ++i) {
            if (upperCase.equals(dg.e[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static final boolean x(final String s) {
        final String upperCase = s.toUpperCase();
        for (int i = 0; i < dg.f.length; ++i) {
            if (upperCase.equals(dg.f[i])) {
                return true;
            }
        }
        return upperCase.startsWith("STAMPMENU");
    }
    
    public static final int y(final String s) {
        if (!w(s)) {
            return -2;
        }
        if (ac(s)) {
            return -1;
        }
        return i(s);
    }
    
    private static final boolean ac(final String s) {
        return s.toUpperCase().equals("ANY");
    }
    
    public static final String a(final int n, final String s) {
        String s2 = null;
        switch (n) {
            case 4: {
                s2 = ji.util.d.b(554, s);
                break;
            }
            case 5: {
                s2 = ji.util.d.b(555, s);
                break;
            }
            case 7: {
                s2 = ji.util.d.b(556, s);
                break;
            }
            case 9: {
                s2 = ji.util.d.b(557, s);
                break;
            }
            case 1: {
                s2 = ji.util.d.b(558, s);
                break;
            }
            case 8: {
                s2 = ji.util.d.b(559, s);
                break;
            }
            case 11: {
                s2 = ji.util.d.b(560, s);
                break;
            }
            case 12: {
                s2 = ji.util.d.b(561, s);
                break;
            }
            case 13: {
                s2 = ji.util.d.b(562, s);
                break;
            }
            case 14: {
                s2 = ji.util.d.b(564, s);
                break;
            }
            case 2: {
                s2 = ji.util.d.b(565, s);
                break;
            }
            case 3: {
                s2 = ji.util.d.b(566, s);
                break;
            }
            case 6: {
                s2 = ji.util.d.b(567, s);
                break;
            }
            case 10: {
                s2 = ji.util.d.b(568, s);
                break;
            }
        }
        return s2;
    }
    
    public final void c(final boolean hw, final int hx) {
        this.hw = hw;
        this.hx = hx;
    }
    
    public final boolean gi() {
        return this.hw;
    }
    
    public final int gj() {
        return this.hx;
    }
    
    public boolean gk() {
        boolean b = true;
        final Color[] array = { this.a6, this.a7, this.a8, this.a9, this.ba };
        switch (this.n) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14: {
                boolean b2 = true;
                for (int i = 0; i < array.length; ++i) {
                    if (!this.h(array[i])) {
                        b2 = false;
                        break;
                    }
                }
                b = !b2;
            }
            case 9: {
                if (!this.e9()) {
                    b = true;
                    break;
                }
                boolean b3 = true;
                for (int j = 0; j < array.length; ++j) {
                    if (!this.h(array[j])) {
                        b3 = false;
                        break;
                    }
                }
                b = !b3;
                break;
            }
            case 16:
            case 17: {
                b = false;
                break;
            }
        }
        return b;
    }
    
    private boolean h(final Color color) {
        boolean b = true;
        if (color != null && !color.equals(Color.black) && !color.equals(Color.white)) {
            b = false;
        }
        return b;
    }
    
    private final void h(final int n, final int n2) {
        if (this.cx != 1.0) {
            this.v = (int)(this.x * this.cx);
            this.w = (int)(this.y * this.cx);
            if (this.ab < 0) {
                this.ab = this.a(this.v, n);
                this.ac = this.a(this.w, n2);
            }
        }
    }
    
    private final double a(final long n, final int n2) {
        if (n > 0 && n2 > 0) {
            return n / n2 * 100.0;
        }
        return 0.0;
    }
    
    public final boolean gl() {
        return this.fk;
    }
    
    public final void gm() {
        this.fk = true;
        this.fj = true;
        this.dm.g(false);
        this.dm.h(false);
        this.dm.c(true);
        this.dm.f(false);
        this.dm.d(false);
        this.dm.e(true);
    }
    
    static {
        a = Color.black;
        b = Color.white;
        c = new int[] { 1, 2, 4, 5, 6, 7, 9, 10, 11, 12 };
        d = new int[] { 11 };
        e = new String[] { "LINE", "ARROW", "TEXT", "NOTE", "HIGHLIGHT", "HIGHLIGHTPOLY", "RECTANGLE", "REDACT", "REDACTPOLY", "POLY", "OPENPOLY", "OVAL", "FREEHAND", "STAMP" };
        f = new String[] { "LINE", "ARROW", "TEXT", "TEXTSOLID", "NOTE", "HIGHLIGHT", "HIGHLIGHTPOLY", "HYPERLINK", "RECTANGLE", "SQUARE", "REDACT", "REDACTPOLY", "POLY", "OPENPOLY", "OVAL", "CIRCLE", "FREEHAND", "RULER", "ANGLE", "ANGLEREVERSED", "STAMP" };
        dg.g = 0;
        dg.h = 0;
        dg.i = 0;
        dg.o = 0;
        dg.dy = new Hashtable();
        dg.fp = -1.0;
    }
    
    private class u2 extends c
    {
        boolean a;
        boolean b;
        
        public u2(final dg dg, final String s) {
            super(s);
            this.a = false;
            this.b = false;
        }
    }
}
