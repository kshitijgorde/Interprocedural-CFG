// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.applet.jiApplet;
import java.lang.reflect.Method;
import java.awt.event.KeyEvent;
import ji.v1event.ao;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.awt.Dialog;
import java.awt.Window;
import ji.awt.d5;
import ji.awt.d4;
import ji.image.cy;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import ji.awt.ax;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Toolkit;
import ji.zip.a4;
import java.util.Locale;
import java.applet.Applet;
import ji.awt.dc;
import ji.awt.o6;
import ji.image.dx;
import ji.sec.u;
import java.util.Enumeration;
import java.util.Properties;
import ji.v1event.ah;
import ji.graphic.b0;
import java.awt.Graphics;
import ji.v1base.bz;
import ji.v1base.jiPanel;
import java.awt.event.KeyListener;
import java.io.File;
import java.awt.MediaTracker;
import java.awt.Frame;
import ji.v1event.a6;
import java.io.InputStream;
import java.awt.image.PixelGrabber;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import ji.image.c2;
import java.awt.image.ImageObserver;
import ji.render.c1;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import ji.res.ab;
import ji.net.bg;
import ji.sec.bf;
import java.io.FileNotFoundException;
import java.net.URLConnection;
import java.net.URL;
import ji.res.ay;
import java.awt.Container;
import ji.sec.f;
import java.awt.Dimension;
import ji.io.h;
import ji.filter.ew;
import ji.io.q;
import ji.net.a0;
import ji.font.j;
import ji.document.bd;
import ji.res.aa;
import ji.res.s;
import ji.v1event.jiJava2KeyInterface;
import ji.sec.g;
import ji.net.cookie.bc;
import java.awt.SystemColor;
import ji.res.z;
import ji.v1event.af;
import java.awt.Component;
import ji.document.ad;
import ji.res.o;
import ji.annotate.gs;
import ji.secure.cg;
import ji.secure.ei;
import java.awt.Image;
import ji.graphic.bj;
import ji.io.ac;
import java.awt.Rectangle;
import ji.v1event.dr;
import ji.v1event.bu;
import java.util.Hashtable;
import java.awt.Color;
import ji.awt.c;

public final class d
{
    private static String a;
    public static boolean b;
    public static boolean c;
    public static boolean d;
    public static boolean e;
    public static c f;
    private static c g;
    public static Color h;
    public static Color i;
    public static Color j;
    private static Hashtable k;
    private static bu l;
    private static dr m;
    public static int n;
    public static Rectangle o;
    public static boolean p;
    public static boolean q;
    public static boolean r;
    public static boolean s;
    public static boolean t;
    public static boolean u;
    public static boolean v;
    public static boolean w;
    public static boolean x;
    public static long y;
    private static int z;
    private static int aa;
    private static boolean ab;
    private static long ac;
    private static long ad;
    private static long ae;
    private static long af;
    public static double ag;
    public static long ah;
    public static int ai;
    public static int aj;
    public static boolean ak;
    public static boolean al;
    public static boolean am;
    public static boolean an;
    public static boolean ao;
    public static boolean ap;
    private static int aq;
    public static int ar;
    public static String as;
    public static String at;
    public static String au;
    public static String av;
    public static String aw;
    public static String ax;
    private static boolean ay;
    private static boolean az;
    private static boolean a0;
    private static boolean a1;
    private static boolean a2;
    private static boolean a3;
    public static boolean a4;
    public static boolean a5;
    public static String a6;
    public static boolean a7;
    public static boolean a8;
    public static boolean a9;
    private static boolean ba;
    private static boolean bb;
    private static boolean bc;
    public static String bd;
    public static long be;
    public static String bf;
    public static String bg;
    public static long bh;
    public static String bi;
    public static long bj;
    public static long bk;
    public static long bl;
    public static int bm;
    public static int bn;
    public static int bo;
    public static int bp;
    public static int bq;
    public static int br;
    public static int bs;
    public static long bt;
    public static long bu;
    public static long bv;
    public static int bw;
    public static int bx;
    public static int by;
    public static int bz;
    public static int b0;
    public static int b1;
    public static int b2;
    public static int b3;
    public static int b4;
    public static int b5;
    public static int b6;
    public static int b7;
    public static int b8;
    public static int b9;
    public static int ca;
    public static int cb;
    public static int cc;
    public static int cd;
    public static int ce;
    public static int cf;
    public static int cg;
    public static int ch;
    public static int ci;
    public static int cj;
    public static int ck;
    public static int cl;
    public static int cm;
    public static int cn;
    public static int co;
    public static int cp;
    public static int cq;
    public static int cr;
    public static int cs;
    public static int ct;
    public static int cu;
    public static int cv;
    public static int cw;
    public static int cx;
    public static int cy;
    public static int cz;
    public static int c0;
    public static long c1;
    public static byte[] c2;
    public static byte[] c3;
    public static byte[] c4;
    public static boolean c5;
    public static boolean c6;
    public static boolean c7;
    private static int c8;
    public static boolean c9;
    public static boolean da;
    private static boolean db;
    private static boolean dc;
    private static boolean dd;
    private static boolean de;
    private static boolean df;
    private static long dg;
    private static boolean dh;
    private static boolean di;
    private static boolean dj;
    private static boolean dk;
    public static boolean dl;
    public static boolean dm;
    public static boolean dn;
    public static int do;
    public static boolean dp;
    public static int dq;
    public static int dr;
    public static int ds;
    public static int dt;
    public static int du;
    public static int dv;
    public static int dw;
    public static int dx;
    public static int dy;
    public static int dz;
    public static int d0;
    public static int d1;
    public static int d2;
    private static boolean d3;
    private static int d4;
    private static int d5;
    private static int d6;
    private static int d7;
    public static boolean d8;
    public static boolean d9;
    public static boolean ea;
    public static boolean eb;
    public static int ec;
    private static long ed;
    private static long ee;
    private static boolean ef;
    private static boolean eg;
    private static boolean eh;
    private static boolean ei;
    public static int ej;
    public static int ek;
    private static boolean el;
    private static boolean em;
    private static boolean en;
    private static int eo;
    private static int ep;
    private static boolean eq;
    private static boolean er;
    private static boolean es;
    private static String et;
    private static boolean eu;
    private static String ev;
    private static String ew;
    private static ac ex;
    private static byte[] ey;
    private static boolean ez;
    private static boolean e0;
    private static boolean e1;
    private static String e2;
    public static int e3;
    public static int e4;
    private static boolean e5;
    public static boolean e6;
    private static boolean e7;
    private static boolean e8;
    private static boolean e9;
    private static boolean fa;
    private static boolean fb;
    private static boolean fc;
    private static boolean fd;
    private static boolean fe;
    private static boolean ff;
    private static boolean fg;
    private static boolean fh;
    private static boolean fi;
    private static boolean fj;
    private static boolean fk;
    private static boolean fl;
    private static boolean fm;
    private static boolean fn;
    private static boolean fo;
    public static boolean fp;
    private static int fq;
    private static boolean fr;
    public static boolean fs;
    private static boolean ft;
    private static boolean fu;
    public static boolean fv;
    private static long fw;
    public static String fx;
    public static String fy;
    private static boolean fz;
    public static boolean f0;
    private static boolean f1;
    private static boolean f2;
    private static boolean f3;
    private static boolean f4;
    private static boolean f5;
    private static boolean f6;
    private static String f7;
    public static String f8;
    public static String f9;
    private static c ga;
    private static int gb;
    private static int gc;
    private static double gd;
    private static double ge;
    private static boolean gf;
    public static boolean gg;
    private static boolean gh;
    private static int gi;
    private static Color gj;
    private static Color gk;
    private static Color gl;
    private static Color gm;
    private static Color gn;
    private static Color go;
    private static boolean gp;
    private static bj gq;
    private static String[] gr;
    private static Color[] gs;
    private static boolean gt;
    private static boolean gu;
    private static boolean gv;
    private static int gw;
    private static String gx;
    private static int gy;
    private static boolean gz;
    private static boolean g0;
    private static boolean g1;
    private static boolean g2;
    private static long g3;
    private static long g4;
    private static long g5;
    private static long g6;
    public static String g7;
    public static long g8;
    private static boolean g9;
    private static boolean ha;
    private static boolean hb;
    public static boolean hc;
    private static boolean hd;
    private static boolean he;
    private static boolean hf;
    private static boolean hg;
    private static boolean hh;
    private static boolean hi;
    private static int hj;
    private static int hk;
    private static Color hl;
    private static Image hm;
    private static Image hn;
    private static String ho;
    private static String hp;
    private static String hq;
    private static String hr;
    private static String hs;
    private static String ht;
    private static String hu;
    private static String hv;
    private static boolean hw;
    private static int hx;
    private static String[] hy;
    private static String[] hz;
    private static int h0;
    public static String h1;
    private static String[] h2;
    private static String[] h3;
    private static String[] h4;
    private static String[] h5;
    private static String[] h6;
    private static String[] h7;
    private static String[] h8;
    private static boolean h9;
    private static boolean ia;
    public static boolean ib;
    private static String ic;
    private static String id;
    private static boolean ie;
    public static int if;
    public static int ig;
    public static int ih;
    public static int ii;
    public static int ij;
    public static int ik;
    public static int il;
    public static int im;
    private static boolean in;
    private static boolean io;
    private static boolean ip;
    private static boolean iq;
    private static boolean ir;
    private static boolean is;
    private static boolean it;
    private static boolean iu;
    private static boolean iv;
    private static int iw;
    private static int ix;
    private static int iy;
    private static int iz;
    public static boolean i0;
    private static boolean i1;
    private static boolean i2;
    private static boolean i3;
    private static boolean i4;
    private static String i5;
    public static boolean i6;
    public static boolean i7;
    public static Color i8;
    public static Color i9;
    public static String ja;
    public static long jb;
    public static long jc;
    private static c jd;
    private static String je;
    private static Object jf;
    private static Object jg;
    public static String jh;
    private static boolean ji;
    private static boolean jj;
    public static boolean jk;
    private static int jl;
    private static int jm;
    private static ei[] jn;
    private static int jo;
    private static String jp;
    private static long jq;
    private static boolean jr;
    private static boolean js;
    private static int jt;
    private static boolean ju;
    private static boolean jv;
    private static boolean jw;
    private static boolean jx;
    private static boolean jy;
    private static boolean jz;
    private static boolean j0;
    private static boolean j1;
    private static int j2;
    private static int j3;
    private static String j4;
    private static c j5;
    private static cg j6;
    private static String[] j7;
    private static boolean j8;
    private static String j9;
    private static String ka;
    private static String kb;
    private static String kc;
    private static boolean kd;
    public static boolean ke;
    public static boolean kf;
    public static boolean kg;
    public static boolean kh;
    public static boolean ki;
    public static String kj;
    public static double kk;
    public static String kl;
    public static c km;
    private static boolean kn;
    public static boolean ko;
    public static boolean kp;
    public static boolean kq;
    private static boolean kr;
    private static int ks;
    private static double kt;
    private static int ku;
    private static int kv;
    private static boolean kw;
    private static boolean kx;
    private static boolean ky;
    private static boolean kz;
    static boolean k0;
    static boolean k1;
    static String k2;
    private static int k3;
    public static int k4;
    public static int k5;
    public static int k6;
    public static int k7;
    private static int k8;
    public static int k9;
    public static int la;
    public static int lb;
    public static int lc;
    public static boolean ld;
    public static int le;
    public static boolean lf;
    public static String lg;
    public static String lh;
    public static String li;
    private static String lj;
    private static String lk;
    private static String ll;
    private static String lm;
    private static String ln;
    private static boolean lo;
    private static gs lp;
    private static String lq;
    private static int lr;
    public static boolean ls;
    private static long lt;
    private static long lu;
    public static boolean lv;
    public static boolean lw;
    public static boolean lx;
    public static boolean ly;
    public static int lz;
    public static int l0;
    public static c l1;
    public static c l2;
    public static String l3;
    public static Object l4;
    public static Hashtable l5;
    public static c l6;
    public static c l7;
    public static String l8;
    public static Object l9;
    public static String ma;
    public static Object mb;
    private static String mc;
    private static String md;
    public static long me;
    public static long mf;
    private static int mg;
    private static String mh;
    private static long mi;
    private static long mj;
    private static long mk;
    private static int ml;
    public static boolean mm;
    private static int mn;
    private static boolean mo;
    private static o mp;
    private static Color mq;
    public static boolean mr;
    public static int ms;
    public static el mt;
    public static boolean mu;
    public static Thread mv;
    public static long mw;
    private static String mx;
    public static double my;
    static Object mz;
    private static final String[] m0;
    private static final String[] m1;
    static /* synthetic */ Class m2;
    static /* synthetic */ Class m3;
    
    public static final double a() {
        return ji.util.d.my;
    }
    
    public static final void a(final double my) {
        ji.util.d.my = my;
    }
    
    public static final void a(final boolean mu) {
        if (mu) {
            ji.util.i.a(82);
        }
        else {
            ji.util.i.b(82);
        }
        if (mu) {
            ji.util.i.a(209, false);
        }
        if (mu) {
            ji.util.i.b(185);
        }
        ji.util.d.mu = mu;
    }
    
    public static boolean b() {
        return ji.util.d.mu;
    }
    
    public static final void a(final String s) {
        ji.util.d.mt.a(s);
    }
    
    public static final int c() {
        return ji.util.d.ms;
    }
    
    public static final void a(final int ms) {
        ji.util.d.ms = ms;
    }
    
    public static final boolean d() {
        return ji.util.d.mr;
    }
    
    public static final void b(final boolean mr) {
        ji.util.d.mr = mr;
    }
    
    public static final void c(final boolean mo) {
        ji.util.d.mo = mo;
    }
    
    public static final boolean e() {
        return ji.util.d.mo;
    }
    
    private static final void c5(final String s) {
        if (ji.util.d.mp == null) {
            ji.util.d.mp = new o(s);
        }
    }
    
    public static final String a(final String s, final String s2) {
        c5(s);
        try {
            if (s2 != null) {
                return ji.util.d.mp.b(s2);
            }
            return ji.util.d.mp.b();
        }
        catch (Exception ex) {
            c(s, String.valueOf(String.valueOf(new StringBuffer("Unable to locate usual temp path, using alternative methods...(").append(ex).append(")..."))));
            return null;
        }
    }
    
    public static final String b(final String s) {
        c5(s);
        return ji.util.d.mp.b();
    }
    
    public static final String c(final String s) {
        c5(s);
        return ji.util.d.mp.a();
    }
    
    public static final void b(final int ml) {
        ji.util.d.ml = ml;
    }
    
    public static final int f() {
        return ji.util.d.ml;
    }
    
    public static final void a(final long mi) {
        ji.util.d.mi = mi;
    }
    
    public static final boolean g() {
        return ji.util.d.mi > 0;
    }
    
    public static final long h() {
        long n = 0L;
        if (ji.util.d.mi > 0) {
            n = ji.util.d.mi;
        }
        else {
            try {
                if (dp()) {
                    n = Math.min(Runtime.getRuntime().maxMemory(), 52428800L);
                }
                else {
                    n = ji.util.d.mk;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return n;
    }
    
    public static final long i() {
        long n;
        if (ji.util.e.av()) {
            n = j() - ji.util.d.mj;
        }
        else {
            n = 1L;
        }
        return n;
    }
    
    public static final long j() {
        if (dp()) {
            return Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory();
        }
        return -1L;
    }
    
    public static final String k() {
        return ji.util.d.mh;
    }
    
    public static final void d(final String mh) {
        ji.util.d.mh = mh;
    }
    
    public static final void c(final int mg) {
        ji.util.d.mg = mg;
    }
    
    public static final int l() {
        return ji.util.d.mg;
    }
    
    public static final void e(final String s) {
        if (ji.util.d.l1 != null) {
            ji.util.d.l1.a(s);
        }
    }
    
    public static final int f(final String s) {
        if (ji.util.d.l1 == null) {
            ji.util.d.l1 = new c("jiUtilNoteCounters");
        }
        final Integer n = (Integer)ji.util.d.l1.d(s);
        Integer n2;
        if (n == null) {
            n2 = new Integer(1);
            ji.util.d.l1.a(s, n2);
        }
        else {
            ji.util.d.l1.a(s);
            n2 = new Integer(n + 1);
            ji.util.d.l1.a(s, n2);
        }
        return n2;
    }
    
    public static final int g(final String s) {
        if (ji.util.d.l1 == null) {
            ji.util.d.l1 = new c("jiUtilNoteCounters");
        }
        Integer n = (Integer)ji.util.d.l1.d(s);
        if (n == null) {
            n = new Integer(1);
        }
        return n;
    }
    
    public static final void a(final String s, final int n) {
        if (ji.util.d.l1 == null) {
            ji.util.d.l1 = new c("jiUtilNoteCounters");
        }
        if (ji.util.d.l1.d(s) == null) {
            ji.util.d.l1.a(s, new Integer(n));
        }
        else {
            ji.util.d.l1.a(s);
            ji.util.d.l1.a(s, new Integer(n));
        }
    }
    
    public static final String m() {
        if (ji.util.d.lu <= 0) {
            ji.util.d.lu = System.currentTimeMillis();
            ji.util.d.lt = System.currentTimeMillis();
        }
        final long n = System.currentTimeMillis() - ji.util.d.lt;
        final long n2 = System.currentTimeMillis() - ji.util.d.lu;
        ji.util.d.lu = System.currentTimeMillis();
        String s;
        if (n > 1000) {
            s = String.valueOf(String.valueOf(new StringBuffer("").append(n / 1000).append(",").append(n - 1000 * (n / 1000))));
        }
        else {
            s = "".concat(String.valueOf(String.valueOf(n)));
        }
        String s2;
        if (n2 > 1000) {
            s2 = String.valueOf(String.valueOf(new StringBuffer("").append(n2 / 1000).append(",").append(n2 - 1000 * (n2 / 1000))));
        }
        else {
            s2 = "".concat(String.valueOf(String.valueOf(n2)));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(cd(false)))).append(" (TIME DIFF: ").append(s).append(" / ").append(s2).append(")")));
    }
    
    public static final void d(final boolean lo) {
        ji.util.d.lo = lo;
    }
    
    public static final boolean n() {
        return ji.util.d.lo;
    }
    
    public static final void h(final String lj) {
        ji.util.d.lj = lj;
    }
    
    public static final String o() {
        return ji.util.d.lj;
    }
    
    public static final String p() {
        return ji.util.d.lk;
    }
    
    public static final void d(final int k8) {
        ji.util.d.k8 = k8;
    }
    
    public static final int q() {
        if (ji.util.d.k8 > 0) {
            return ji.util.d.k8;
        }
        return 3000000;
    }
    
    public static boolean r() {
        return ji.util.d.k8 != -1;
    }
    
    public static final void a(final int[] array, final int n, final int n2, final byte[] array2, final int n3, final int n4) {
        for (int i = 0; i < n4; i += 4) {
            final int n5 = array[n + i / 4];
            array2[i] = (byte)(n5 >> 24);
            array2[i + 1] = (byte)(n5 >> 16 & 0xFF);
            array2[i + 2] = (byte)(n5 >> 8 & 0xFF);
            array2[i + 3] = (byte)(n5 & 0xFF);
        }
    }
    
    public static final double s() {
        return ji.util.d.kt;
    }
    
    public static final void b(final double kt) {
        ji.util.d.kt = kt;
    }
    
    public static final String i(final String s) {
        return b(s, t());
    }
    
    public static final String b(final String s, final int n) {
        String s2 = s;
        try {
            if (!by(s)) {
                final int index = s.indexOf(".");
                if (index >= 0) {
                    if (n > 0) {
                        s2 = s2.substring(0, Math.min(index + Math.max(n + 1, 0), s.length()));
                    }
                    else {
                        s2 = s2.substring(0, Math.min(index, s.length()));
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final int t() {
        return ji.util.d.k3;
    }
    
    public static final void e(final int k3) {
        ji.util.d.k3 = k3;
    }
    
    public static final int u() {
        return ji.util.d.kv;
    }
    
    public static final void f(final int kv) {
        ji.util.d.kv = kv;
    }
    
    public static final int v() {
        return ji.util.d.ku;
    }
    
    public static final void g(final int ku) {
        ji.util.d.ku = ku;
    }
    
    public static final int w() {
        return ji.util.d.ks;
    }
    
    public static final void h(final int ks) {
        ji.util.d.ks = ks;
    }
    
    public static final void i(final int n) {
        ji.util.d.n = n;
    }
    
    public static final void b(final long n) {
        ji.util.d.ah += n;
    }
    
    public static final void x() {
        ji.util.d.ah = 0L;
    }
    
    public static final long y() {
        return ji.util.d.ah;
    }
    
    public static final void c(final double n) {
        ji.util.d.ag = n;
        if (ji.util.i.c(159)) {
            ji.util.d.mt.b = n;
        }
    }
    
    public static final double z() {
        return ji.util.d.ag;
    }
    
    public static final void e(final boolean ko) {
        ji.util.d.ko = ko;
    }
    
    public static final void f(final boolean kp) {
        ji.util.d.kp = kp;
    }
    
    public static final void g(final boolean kq) {
        ji.util.d.kq = kq;
    }
    
    public static final boolean aa() {
        return ji.util.d.kq;
    }
    
    public static final void h(final boolean ki) {
        ji.util.d.ki = ki;
    }
    
    public static final boolean ab() {
        return ji.util.d.ki;
    }
    
    public static final void d(final double kk) {
        ji.util.d.kk = kk;
    }
    
    public static final double ac() {
        return ji.util.d.kk;
    }
    
    public static final void j(final String kj) {
        ji.util.d.kj = kj;
    }
    
    public static final String ad() {
        return ji.util.d.kj;
    }
    
    public static final boolean ae() {
        return true;
    }
    
    public static final void j(final int j3) {
        ji.util.d.j3 = j3;
    }
    
    public static final int af() {
        return ji.util.d.j3;
    }
    
    public static void i(final boolean ke) {
        ji.util.d.ke = ke;
    }
    
    public static boolean ag() {
        return ji.util.d.ke;
    }
    
    public static void k(final String kc) {
        if (!by(kc)) {
            ji.util.d.kc = kc;
        }
    }
    
    public static String ah() {
        return ji.util.d.kc;
    }
    
    public static void j(final boolean kd) {
        ji.util.d.kd = kd;
    }
    
    public static boolean ai() {
        return ji.util.d.kd;
    }
    
    public static void l(final String ka) {
        if (!by(ka)) {
            ji.util.d.ka = ka;
        }
    }
    
    public static String aj() {
        return ji.util.d.ka;
    }
    
    public static void m(final String kb) {
        if (!by(kb)) {
            ji.util.d.kb = kb;
        }
    }
    
    public static String ak() {
        return ji.util.d.kb;
    }
    
    public static final void n(final String s) {
        try {
            if (!by(s) && !o(s)) {
                if (ji.util.d.j5 == null) {
                    ji.util.d.j5 = new c("jiUtilPasswords");
                }
                ji.util.d.j5.c(s.toLowerCase());
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean o(String lowerCase) {
        boolean b = false;
        try {
            if (ji.util.d.j5 != null && !by(lowerCase)) {
                lowerCase = lowerCase.toLowerCase();
                Block_7: {
                    for (int i = 0; i < ji.util.d.j5.b(); ++i) {
                        final String s = (String)ji.util.d.j5.b(i);
                        if (!by(s) && s.equals(lowerCase)) {
                            break Block_7;
                        }
                    }
                    return b;
                }
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final void p(final String s) {
        if (s != null) {
            ji.util.d.j4 = s.toLowerCase();
        }
    }
    
    public static final String al() {
        return ji.util.d.j4;
    }
    
    public static final void k(final boolean j1) {
        ji.util.d.j1 = j1;
    }
    
    public static final boolean am() {
        return true;
    }
    
    public static final boolean an() {
        return ji.util.d.jx;
    }
    
    public static final void l(final boolean jx) {
        ji.util.d.jx = jx;
    }
    
    public static final void k(final int j2) {
        ji.util.d.j2 = j2;
    }
    
    public static final int ao() {
        return ji.util.d.j2;
    }
    
    public static final void m(final boolean j0) {
        ji.util.d.j0 = j0;
    }
    
    public static final boolean q(final String s) {
        return ji.util.d.j0 && !dq();
    }
    
    public static final void n(final boolean jy) {
        ji.util.d.jy = jy;
    }
    
    public static final boolean ap() {
        return ji.util.d.jy || ji.util.e.u();
    }
    
    public static final boolean a(final ad ad) {
        return ad.bi(14) && (ji.util.d.jr || ji.util.e.u());
    }
    
    public static final void o(final boolean jv) {
        ji.util.d.jv = jv;
    }
    
    public static final boolean aq() {
        return ji.util.d.jv;
    }
    
    public static final void p(final boolean ju) {
        ji.util.d.ju = ju;
    }
    
    public static final boolean ar() {
        return ji.util.d.ju;
    }
    
    public static final int as() {
        if (ji.util.i.c(7) && ji.util.e.t()) {
            return ji.util.d.jt;
        }
        return 2;
    }
    
    public static final void l(final int jt) {
        switch (jt) {
            case 2:
            case 3: {
                ji.util.d.jt = jt;
                break;
            }
        }
    }
    
    public static final boolean at() {
        return ji.util.i.c(7) && ji.util.e.t() && as() > 2;
    }
    
    public static boolean au() {
        return ji.util.d.js;
    }
    
    public static void q(final boolean js) {
        ji.util.d.js = js;
    }
    
    public static void r(final boolean jr) {
        ji.util.d.jr = jr;
    }
    
    public static boolean av() {
        return ji.util.d.jr;
    }
    
    public static final boolean r(final String s) {
        return aj(s) && a(1, 3, s);
    }
    
    public static long aw() {
        return ji.util.d.jq;
    }
    
    public static void m(final int jo) {
        ji.util.d.jo = jo;
    }
    
    public static void a(final ei[] jn) {
        ji.util.d.jn = jn;
    }
    
    public static ei[] ax() {
        return ji.util.d.jn;
    }
    
    public static void n(final int jm) {
        ji.util.d.jm = jm;
    }
    
    public static int ay() {
        return ji.util.d.jm;
    }
    
    public static void o(final int jl) {
        ji.util.d.jl = jl;
    }
    
    public static int az() {
        return ji.util.d.jl;
    }
    
    public static void s(final boolean jk) {
        ji.util.d.jk = jk;
    }
    
    public static void t(final boolean jj) {
        ji.util.d.jj = jj;
    }
    
    public static boolean a0() {
        return ji.util.d.jj;
    }
    
    public static void a(final Object jg) {
        ji.util.d.jg = jg;
    }
    
    public static Object a1() {
        return ji.util.d.jg;
    }
    
    public static final boolean a2() {
        return ji.util.d.io;
    }
    
    public static final void u(final boolean io) {
        ji.util.d.io = io;
    }
    
    public static final void s(final String i5) {
        ji.util.d.i5 = i5;
    }
    
    public static final String a3() {
        return ji.util.d.i5;
    }
    
    public static final boolean a4() {
        return true;
    }
    
    public static final boolean a5() {
        return true;
    }
    
    public static final void v(final boolean i2) {
        ji.util.d.i2 = i2;
    }
    
    public static final boolean a6() {
        return !ji.util.i.c(87) && ji.util.d.i2;
    }
    
    public static final void c(final long ac) {
        ji.util.d.ab = true;
        ji.util.d.ac = ac;
        ji.util.d.ad = 15 * ji.util.d.ac;
        ji.util.d.ae = ji.util.d.ad / 4;
    }
    
    public static final long a7() {
        return ji.util.d.ae;
    }
    
    public static final void w(final boolean i1) {
        ji.util.d.i1 = i1;
    }
    
    public static final boolean a8() {
        return ji.util.d.i1;
    }
    
    public static final void p(final int iy) {
        ji.util.d.iy = iy;
    }
    
    public static final int a9() {
        return ji.util.d.iy;
    }
    
    public static final void q(final int iz) {
        ji.util.d.iz = iz;
    }
    
    public static final int ba() {
        return ji.util.d.iz;
    }
    
    public static final int bb() {
        return ji.util.d.iw;
    }
    
    public static final void r(final int iw) {
        ji.util.d.iw = iw;
    }
    
    public static final int bc() {
        return ji.util.d.ix;
    }
    
    public static final void s(final int ix) {
        ji.util.d.ix = ix;
    }
    
    public static boolean bd() {
        return ji.util.d.iv;
    }
    
    public static void x(final boolean iv) {
        ji.util.d.iv = iv;
    }
    
    public static boolean be() {
        return ji.util.d.iu;
    }
    
    public static void y(final boolean iu) {
        ji.util.d.iu = iu;
    }
    
    public static void z(final boolean it) {
        ji.util.d.it = it;
    }
    
    public static boolean bf() {
        return ji.util.d.it;
    }
    
    public static final boolean bg() {
        return bf() && ji.util.i.c(138);
    }
    
    public static final boolean bh() {
        return bg() && ji.util.i.c(161);
    }
    
    public static final boolean bi() {
        return bg() && !ji.util.i.c(161);
    }
    
    public static void aa(final boolean i3) {
        ji.util.d.i3 = i3;
    }
    
    public static boolean bj() {
        return ji.util.d.i3;
    }
    
    public static void ab(final boolean i4) {
        ji.util.d.i4 = i4;
    }
    
    public static boolean bk() {
        return ji.util.d.i4;
    }
    
    public static void ac(final boolean is) {
        ji.util.d.is = is;
    }
    
    public static boolean bl() {
        return ji.util.d.is;
    }
    
    public static boolean bm() {
        return ji.util.d.ip;
    }
    
    public static boolean bn() {
        return ji.util.d.ir;
    }
    
    public static void ad(final boolean ir) {
        ji.util.d.ir = ir;
    }
    
    public static void ae(final boolean ip) {
        ji.util.d.ip = ip;
    }
    
    public static void af(final boolean iq) {
        ji.util.d.iq = iq;
    }
    
    public static boolean bo() {
        return ji.util.d.iq;
    }
    
    public static void a(final int if1, final int ih, final int ij, final int il) {
        ji.util.d.if = if1;
        ji.util.d.ih = ih;
        ji.util.d.ij = ij;
        ji.util.d.il = il;
        ji.util.d.ig = 0;
        ji.util.d.ii = 0;
        ji.util.d.ik = 0;
        ji.util.d.im = 0;
        if (if1 > 0) {
            while ((if1 >> ji.util.d.ig & 0x1) <= 0) {
                ++ji.util.d.ig;
            }
        }
        if (ih > 0) {
            while ((ih >> ji.util.d.ii & 0x1) <= 0) {
                ++ji.util.d.ii;
            }
        }
        if (ij > 0) {
            while ((ij >> ji.util.d.ik & 0x1) <= 0) {
                ++ji.util.d.ik;
            }
        }
        if (il > 0) {
            while ((il >> ji.util.d.im & 0x1) <= 0) {
                ++ji.util.d.im;
            }
        }
    }
    
    public static String a(final Color color) {
        if (color == null) {
            return "-";
        }
        String s = color.toString();
        final int rgb = color.getRGB();
        if (rgb == Color.black.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (BLACK)");
        }
        else if (rgb == Color.blue.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (BLUE)");
        }
        else if (rgb == Color.cyan.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (CYAN)");
        }
        else if (rgb == Color.darkGray.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (DARK GRAY)");
        }
        else if (rgb == Color.gray.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (GRAY)");
        }
        else if (rgb == Color.green.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (GREEN)");
        }
        else if (rgb == Color.lightGray.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (LIGHT GRAY)");
        }
        else if (rgb == Color.magenta.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (MAGENTA)");
        }
        else if (rgb == Color.orange.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat("(ORANGE)");
        }
        else if (rgb == Color.pink.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat("(PINK)");
        }
        else if (rgb == Color.red.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (RED)");
        }
        else if (rgb == Color.white.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (WHITE)");
        }
        else if (rgb == Color.yellow.getRGB()) {
            s = String.valueOf(String.valueOf(s)).concat(" (YELLOW)");
        }
        return s;
    }
    
    public static void ag(final boolean in) {
        ji.util.d.in = in;
    }
    
    public static boolean bp() {
        return ji.util.d.in;
    }
    
    public static void ah(final boolean ie) {
        ji.util.d.ie = ie;
    }
    
    public static boolean bq() {
        return ji.util.d.ie;
    }
    
    public static void t(final String id) {
        ji.util.d.id = id;
    }
    
    public static String br() {
        return ji.util.d.id;
    }
    
    public static void t(final int hx) {
        ji.util.d.hx = hx;
    }
    
    public static int bs() {
        return ji.util.d.hx;
    }
    
    public static final void u(final String ic) {
        ji.util.d.ic = ic;
    }
    
    public static final String bt() {
        return ji.util.d.ic;
    }
    
    public static final String[] bu() {
        return ji.util.d.hz;
    }
    
    public static final String[] bv() {
        return ji.util.d.hy;
    }
    
    public static void ai(final boolean hi) {
        ji.util.d.hi = hi;
    }
    
    public static boolean bw() {
        return ji.util.d.hi;
    }
    
    private static final String fk() {
        if (ji.util.i.c(239)) {
            return "vv";
        }
        if (ji.util.e.u()) {
            return "vlpro";
        }
        return "vl1";
    }
    
    public static Image a(final int n, final Component component, final af af, final String s) {
        try {
            switch (n) {
                case 1: {
                    if (ji.util.d.hm == null) {
                        if (b0()) {
                            ji.util.d.hm = ji.res.z.a(component, "v1rl.gif", "v1rl.v1", af, ji.util.e.aq(), s);
                        }
                        else {
                            ji.util.d.hm = ji.res.z.a(component, String.valueOf(String.valueOf(fk())).concat(".gif"), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(fk()))).append(".").append("v1"))), af, ji.util.e.aq(), s);
                        }
                        return ji.util.d.hm;
                    }
                    return ji.util.d.hm;
                }
                case 2: {
                    if (ji.util.d.hn == null) {
                        if (b0()) {
                            ji.util.d.hn = ji.res.z.a(component, "v1rl.gif", "v1rl.v1", af, bx(), s);
                        }
                        else {
                            ji.util.d.hn = ji.res.z.a(component, String.valueOf(String.valueOf(fk())).concat(".gif"), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(fk()))).append(".").append("v1"))), af, bx(), s);
                        }
                        return ji.util.d.hn;
                    }
                    return ji.util.d.hn;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static final Color bx() {
        Color color = ji.util.e.ao();
        if (color == null) {
            color = SystemColor.control;
        }
        return ji.util.e.c(color);
    }
    
    public static void aj(final boolean hh) {
        ji.util.d.hh = hh;
    }
    
    public static boolean by() {
        return ji.util.d.hh;
    }
    
    public static boolean bz() {
        return ji.util.d.a8 && !bf();
    }
    
    public static boolean b0() {
        return ji.util.d.a9;
    }
    
    public static void ak(final boolean a8) {
        ji.util.d.a8 = a8;
    }
    
    public static void al(final boolean a9) {
        ji.util.d.a9 = a9;
    }
    
    public static final void b1() {
        ++ji.util.d.hk;
    }
    
    public static final int b2() {
        return ji.util.d.hk;
    }
    
    public static final void v(final String s) {
        try {
            ji.net.cookie.bc.a(s);
            --ji.util.d.hk;
            if (ji.util.d.hk <= 0) {
                if (ji.util.i.c(185) && ji.util.i.c(198) && ji.util.i.c(201)) {
                    ji.util.i.b(185);
                    ji.sec.g.d();
                }
                b(s, (Component)null);
                if (ji.util.d.jf != null) {
                    ((jiJava2KeyInterface)ji.util.d.jf).releaseResources();
                    ji.util.d.jf = null;
                }
                if (ji.util.d.jd != null) {
                    ji.util.d.jd.c();
                    ji.util.d.jd = null;
                }
                try {
                    ji.util.d.l6.c();
                }
                catch (Exception ex) {}
                try {
                    while (ji.util.d.l7.b() > 0) {
                        final m m = (m)ji.util.d.l7.b(0);
                        ji.util.d.l7.d(0);
                        m.b();
                    }
                }
                catch (Exception ex2) {}
                d3();
                if (ji.util.d.ex != null) {
                    ji.util.d.ex.a((Object)null);
                    ji.util.d.ex = null;
                }
                ji.res.z.c();
                s.o();
                ji.res.aa.d();
                fl();
                s.m();
                ji.document.bd.b();
                try {
                    if (ji.util.d.k != null && s != null) {
                        synchronized (ji.util.d.k) {
                            ji.util.d.k.remove(w(null, s));
                        }
                        // monitorexit(d.k)
                    }
                }
                catch (Exception ex3) {}
                if (ji.util.d.ga != null) {
                    ji.util.d.ga.c();
                    ji.util.d.ga = null;
                }
                ji.font.j.c(s, true);
                if (ji.util.d.j6 != null) {
                    ji.util.d.j6.a();
                    ji.util.d.j6 = null;
                }
                if (ji.util.d.km != null) {
                    ji.util.d.km.c();
                    ji.util.d.km = null;
                }
                ji.net.a0.a(s);
                ji.io.q.q();
                ji.filter.ew.a();
            }
        }
        catch (Exception ex4) {}
    }
    
    private static void fl() {
        ji.util.d.hm = null;
        ji.util.d.hn = null;
    }
    
    public static final void am(final boolean hf) {
        ji.util.d.hf = hf;
    }
    
    public static final void an(final boolean he) {
        ji.util.d.he = he;
    }
    
    public static final boolean b3() {
        return ji.util.d.he;
    }
    
    public static final int b4() {
        return ji.util.d.ec;
    }
    
    public static final void u(final int hj) {
        ji.util.d.hj = hj;
    }
    
    public static final void v(final int ec) {
        ji.util.d.ec = ec;
    }
    
    public static final void ao(final boolean hd) {
        ji.util.d.hd = hd;
    }
    
    public static final boolean b5() {
        return ji.util.d.hd;
    }
    
    public static final Object w(final String l3) {
        if (ji.util.d.l3 != null && l3 != null && ji.util.d.l4 != null && ji.util.d.l3.equals(l3)) {
            return ji.util.d.l4;
        }
        if (ji.util.d.l2 != null) {
            ji.util.d.l4 = ji.util.d.l2.d(l3);
            if (ji.util.d.l4 != null) {
                ji.util.d.l3 = l3;
                return ji.util.d.l4;
            }
        }
        else {
            ji.io.h.d("Unknown", "getParent failed");
        }
        return null;
    }
    
    public static final ad x(final String s) {
        final Object w = w(s);
        if (w instanceof ad) {
            return (ad)w;
        }
        return null;
    }
    
    public static final void a(final Object o, final String s) {
        synchronized (ji.util.d.l2) {
            if (o == null) {
                ji.util.d.l3 = null;
                ji.util.d.l4 = null;
                ji.util.d.l2.a(s);
            }
            else {
                ji.util.d.l2.a(s);
                if (s != null) {
                    ji.util.d.l2.a(s, o);
                }
            }
        }
        // monitorexit(d.l2)
        ji.util.d.l3 = null;
        ji.util.d.l4 = null;
    }
    
    public static final void b(final Object o, final String s) {
        if (ji.util.i.c(78)) {
            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("setParentApplet: parentAppletIn = ").append(o).append(", parentId = ").append(s))));
        }
        synchronized (ji.util.d.l6) {
            if (o == null) {
                ji.util.d.l8 = null;
                ji.util.d.l9 = null;
                ji.util.d.ma = null;
                ji.util.d.mb = null;
                ji.util.d.l6.a(s);
                final m m = (m)ji.util.d.l7.d(s);
                if (m != null) {
                    ji.util.d.l7.a(s);
                    m.b();
                }
            }
            else {
                ji.util.d.l6.a(s);
                ji.util.d.l6.a(s, o);
                ji.util.d.l7.a(s);
                ji.util.d.l7.a(s, new m(o));
            }
        }
        // monitorexit(d.l6)
    }
    
    public static final Object y(final String l8) {
        if (ji.util.d.l8 != null && l8 != null && ji.util.d.l8.equals(l8)) {
            return ji.util.d.l9;
        }
        if (ji.util.d.l6 != null) {
            ji.util.d.l9 = ji.util.d.l6.d(l8);
            if (ji.util.d.l9 != null) {
                ji.util.d.l8 = l8;
            }
        }
        else {
            ji.io.h.d("Unknown", "getParentApplet failed");
        }
        return ji.util.d.l9;
    }
    
    public static final m z(final String ma) {
        if (ji.util.d.ma != null && ma != null && ji.util.d.ma.equals(ma)) {
            return (m)ji.util.d.mb;
        }
        if (ji.util.d.l7 != null) {
            ji.util.d.mb = ji.util.d.l7.d(ma);
            if (ji.util.d.mb != null) {
                ji.util.d.ma = ma;
            }
        }
        else {
            ji.io.h.d(ma, "getParentAppletInvoke failed");
        }
        if (ji.util.d.mb != null) {
            return (m)ji.util.d.mb;
        }
        return null;
    }
    
    public static final void ap(final boolean g9) {
        ji.util.d.g9 = g9;
    }
    
    public static final boolean b6() {
        return ji.util.d.g9;
    }
    
    public static final void aq(final boolean ha) {
        ji.util.d.ha = ha;
    }
    
    public static final boolean b7() {
        return ji.util.d.ha;
    }
    
    public static final void ar(final boolean hb) {
        ji.util.d.hb = hb;
    }
    
    public static final void a(final String[] array) {
        if (array != null) {
            ji.util.d.j7 = new String[array.length];
            for (int i = 0; i < array.length; ++i) {
                ji.util.d.j7[i] = array[i];
            }
        }
        else {
            ji.util.d.j7 = null;
        }
    }
    
    public static final boolean aa(final String s) {
        if (!ji.util.d.hb || by(s)) {
            return false;
        }
        if (ji.util.d.j7 != null) {
            boolean b = false;
            final String bc = bc(s.toLowerCase());
            for (int i = 0; i < ji.util.d.j7.length; ++i) {
                if (bc(ji.util.d.j7[i]).toLowerCase().equals(bc)) {
                    b = true;
                }
            }
            return b;
        }
        return true;
    }
    
    public static final long b8() {
        return ji.util.d.g8;
    }
    
    public static final void as(final boolean g2) {
        ji.util.d.g2 = g2;
    }
    
    public static final boolean b9() {
        return ji.util.d.g2;
    }
    
    public static final boolean ca() {
        return ji.util.d.g0;
    }
    
    public static final boolean cb() {
        return ji.util.d.g1;
    }
    
    public static final void at(final boolean g1) {
        ji.util.d.g1 = g1;
    }
    
    public static final void au(final boolean g0) {
        ji.util.d.g0 = g0;
    }
    
    public static final boolean cc() {
        return ji.util.d.ia;
    }
    
    public static final void av(final boolean dc) {
        ji.util.d.dc = dc;
    }
    
    public static final void aw(final boolean ia) {
        ji.util.d.ia = ia;
    }
    
    public static final void ax(final boolean gz) {
        ji.util.d.gz = gz;
    }
    
    public static final boolean a(final boolean b, final String s) {
        if (!b) {
            return ji.util.d.gz;
        }
        if (av(s)) {
            return !ad(s) || !ji.util.e.ae() || ji.util.d.gz;
        }
        return ji.util.d.gz;
    }
    
    public static final void w(final int gy) {
        ji.util.d.gy = gy;
    }
    
    public static final int cd() {
        return ji.util.d.gy;
    }
    
    public static final void b(final String mc, final String md) {
        ji.util.d.mc = mc;
        ji.util.d.md = md;
    }
    
    public static final String ce() {
        return ji.util.d.mc;
    }
    
    public static final String cf() {
        return ji.util.d.md;
    }
    
    public static final long a(final int n, final boolean b, final boolean b2, final String s) {
        if (!ji.util.d.ab) {
            ji.util.d.ab = true;
            if (dp()) {
                ji.util.d.ac = ji.util.d.aa;
            }
            else {
                ji.util.d.ac = ji.util.d.z;
            }
        }
        long n2 = ji.util.d.ac;
        if (ji.util.d.p) {
            n2 = 0L;
        }
        else if (a(b, s)) {
            n2 = ji.util.d.ac * 500;
        }
        else if (ax(s) && dp()) {
            if (!ji.util.d.ba) {
                n2 *= 10;
            }
            else {
                n2 = ji.util.d.ac / 2;
            }
        }
        else if (av(s) || ay(s)) {
            if (n == 6) {
                n2 = ji.util.d.ac / 2;
            }
        }
        else if (em()) {
            n2 *= 10;
        }
        else if (!ji.util.d.ba) {
            n2 *= 10;
        }
        else if (b()) {
            n2 = ji.util.d.ac;
        }
        else {
            n2 = ji.util.d.ac / 2;
        }
        return n2;
    }
    
    public static final void ay(final boolean ba) {
        ji.util.d.ba = ba;
    }
    
    public static final long b(final int n, final boolean b, final boolean b2, final String s) {
        return a(n, b, b2, s);
    }
    
    public static final int cg() {
        return ji.util.d.gw;
    }
    
    public static final void x(final int gw) {
        ji.util.d.gw = gw;
    }
    
    public static final boolean ch() {
        return ji.util.d.fz;
    }
    
    public static final void az(final boolean fz) {
        ji.util.d.fz = fz;
    }
    
    public static final void a0(final boolean gu) {
        ji.util.d.gu = gu;
    }
    
    public static final boolean ci() {
        return ji.util.d.gu;
    }
    
    public static final void a1(final boolean gt) {
        ji.util.d.gt = gt;
    }
    
    public static final void a2(final boolean f6) {
        ji.util.d.f6 = f6;
    }
    
    public static final void a3(final boolean gp) {
        ji.util.d.gp = gp;
    }
    
    public static final boolean cj() {
        return ji.util.d.gp;
    }
    
    public static final int ck() {
        return ji.util.d.gi;
    }
    
    public static final void y(final int gi) {
        ji.util.d.gi = gi;
    }
    
    public static final Color cl() {
        if (ji.util.d.gj == null) {
            ji.util.d.gj = new Color(255, 255, 153);
        }
        return ji.util.d.gj;
    }
    
    public static final Color cm() {
        if (ji.util.d.gk == null) {
            ji.util.d.gk = Color.red;
        }
        return ji.util.d.gk;
    }
    
    public static final Color cn() {
        if (ji.util.d.gl == null) {
            ji.util.d.gl = Color.red;
        }
        return ji.util.d.gl;
    }
    
    public static final Color co() {
        if (ji.util.d.gm == null) {
            ji.util.d.gm = new Color(255, 255, 153);
        }
        return ji.util.d.gm;
    }
    
    public static final Color cp() {
        if (ji.util.d.gn == null) {
            ji.util.d.gn = Color.yellow;
        }
        return ji.util.d.gn;
    }
    
    public static final Color cq() {
        return ji.util.d.go;
    }
    
    public static final Color cr() {
        return ji.util.d.mq;
    }
    
    public static final void b(final Color mq) {
        ji.util.d.mq = mq;
    }
    
    public static final void c(final Color gj) {
        if (gj != null) {
            ji.util.d.gj = gj;
        }
    }
    
    public static final void d(final Color gk) {
        if (gk != null) {
            ji.util.d.gk = gk;
        }
    }
    
    public static final void e(final Color gl) {
        if (gl != null) {
            ji.util.d.gl = gl;
        }
    }
    
    public static final void f(final Color gm) {
        if (gm != null) {
            ji.util.d.gm = gm;
        }
    }
    
    public static final void g(final Color gn) {
        if (gn != null) {
            ji.util.d.gn = gn;
        }
    }
    
    public static final void h(final Color go) {
        if (go != null) {
            ji.util.d.go = go;
        }
    }
    
    public static final void a4(final boolean b) {
        ji.util.i.a(253, b);
    }
    
    public static final boolean cs() {
        return ji.util.i.c(253);
    }
    
    public static final boolean ct() {
        return ji.util.d.gf;
    }
    
    public static final void a5(final boolean gf) {
        ji.util.d.gf = gf;
    }
    
    public static final void a(final Dimension dimension) {
        if (dimension != null) {
            ji.util.d.gb = dimension.width;
            ji.util.d.gc = dimension.height;
        }
    }
    
    public static final void a(final double gd, final double ge) {
        ji.util.d.gd = gd;
        ji.util.d.ge = ge;
    }
    
    public static final int cu() {
        return ji.util.d.gb;
    }
    
    public static final double cv() {
        return ji.util.d.gd;
    }
    
    public static final int cw() {
        return ji.util.d.gc;
    }
    
    public static final boolean cx() {
        return ji.util.d.db;
    }
    
    public static final void a6(final boolean db) {
        ji.util.d.db = db;
    }
    
    public static final boolean cy() {
        return ji.util.i.c(252);
    }
    
    private static final boolean fm() {
        return cy() || ji.util.i.c(137) || ji.util.i.c(131);
    }
    
    public static final void a(final Throwable t) {
        b(t);
    }
    
    public static final void b(final Throwable t) {
        if (fm()) {
            ji.io.h.a("", t);
        }
    }
    
    public static final boolean cz() {
        return ji.util.d.ea;
    }
    
    public static final void a7(final boolean b) {
        ji.util.i.a(252, b);
    }
    
    public static final void a8(final boolean ea) {
        ji.util.d.ea = ea;
    }
    
    public static final void ab(final String f7) {
        ji.util.d.f7 = f7;
    }
    
    public static final String c0() {
        return ji.util.d.f7;
    }
    
    private static final String w(final String s, final String s2) {
        if (b()) {
            return s2;
        }
        String s3 = s;
        if (s3 != null) {
            final int index = s3.indexOf(",");
            if (index >= 0) {
                s3 = s3.substring(0, index);
            }
        }
        if (b() && s2 != null) {
            s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("-").append(s2)));
        }
        return s3;
    }
    
    public static final void ac(final String jh) {
        ji.util.d.jh = jh;
    }
    
    public static final String c1() {
        return ji.util.d.jh;
    }
    
    public static final void a(final String s, Object o, final String s2) {
        synchronized (ji.util.d.k) {
            if (o == null) {
                o = new Integer(0);
            }
            try {
                if (ji.util.d.k == null) {
                    ji.util.d.k = new Hashtable();
                }
                ji.util.d.k.remove(w(o.toString(), s2));
                if (s2 != null) {
                    ji.util.d.k.remove(s2);
                }
            }
            catch (Exception ex2) {}
            try {
                if (s != null) {
                    ji.util.d.k.remove(w(o.toString(), s2));
                    ji.util.d.k.put(w(o.toString(), s2), s);
                }
                if (s2 != null) {
                    ji.util.d.k.remove(s2);
                    ji.util.d.k.put(s2, s);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // monitorexit(d.k)
    }
    
    public static final String c(final Object o, final String s) {
        String mx = e(o, s);
        if (mx == null) {
            mx = ji.util.d.mx;
        }
        else {
            ji.util.d.mx = mx;
        }
        return mx;
    }
    
    private static final String e(Object o, final String s) {
        synchronized (ji.util.d.k) {
            if (o == null) {
                o = new Integer(0);
            }
            try {
                String s2 = ji.util.d.k.get(w(o.toString(), s));
                if (s2 == null) {
                    s2 = ji.util.d.k.get(s);
                }
                if (s2 != null || o == null) {
                    // monitorexit(d.k)
                    return s2;
                }
                if (o instanceof Component) {
                    Component parent = (Component)o;
                    while ((parent = parent.getParent()) != null) {
                        s2 = (String)ji.util.d.k.get(w(parent.toString(), s));
                        if (s2 != null) {
                            ji.util.d.k.put(w(parent.toString(), s), s2);
                            break;
                        }
                    }
                }
                if (s2 == null) {
                    // monitorexit(d.k)
                    return ji.util.d.k.get(new Integer(0).toString());
                }
                // monitorexit(d.k)
                return s2;
            }
            catch (Exception ex) {
                // monitorexit(d.k)
                return null;
            }
        }
    }
    
    public static final void a9(final boolean f1) {
        ji.util.d.f1 = f1;
    }
    
    public static final boolean c2() {
        return ji.util.d.f1;
    }
    
    public static final void ba(final boolean b) {
        ji.util.i.a(251, b);
    }
    
    public static final void bb(final boolean dd) {
        if (!(ji.util.d.dd = dd)) {
            ji.res.s.o();
            ji.res.aa.d();
            fl();
        }
    }
    
    public static final void bc(final boolean ft) {
        ji.util.d.ft = ft;
    }
    
    public static final void bd(final boolean kr) {
        ji.util.d.kr = kr;
    }
    
    public static final boolean c3() {
        return ji.util.d.kr;
    }
    
    public static final boolean c4() {
        return ji.util.d.ft || b9();
    }
    
    public static final void be(final boolean fo) {
        ji.util.d.fo = fo;
    }
    
    public static final boolean c5() {
        return ji.util.d.fo;
    }
    
    public static final void z(final int n) {
        ji.util.d.af = n;
    }
    
    public static final long c6() {
        return ji.util.d.af;
    }
    
    public static final void bf(final boolean a3) {
        ji.util.d.a3 = a3;
    }
    
    public static final boolean c7() {
        return ji.util.d.a3;
    }
    
    public static final void bg(final boolean az) {
        ji.util.d.az = az;
    }
    
    public static final boolean ad(final String s) {
        return (ji.util.d.ay || ji.util.e.af()) && ji.util.d.az && ji.util.d.ei && ji.util.e.u(s);
    }
    
    public static final boolean c8() {
        return ji.util.d.dm;
    }
    
    public static final boolean c9() {
        return ji.util.d.dn;
    }
    
    public static final void bh(final boolean a0) {
        ji.util.d.a0 = a0;
    }
    
    public static final boolean da() {
        return ji.util.d.ay && ji.util.d.a0 && ji.util.d.ei;
    }
    
    public static final void b(final boolean ay, final String s) {
        if (s != null && s.equals("skmok")) {
            ji.util.d.ay = ay;
        }
    }
    
    public static final boolean db() {
        return ji.util.d.ay && ji.util.d.az;
    }
    
    public static final void c(final boolean bb, final String s) {
        if (s != null && s.equals("skmok1")) {
            ji.util.d.bb = bb;
        }
    }
    
    public static final boolean dc() {
        return ji.util.d.bb;
    }
    
    public static final boolean dd() {
        return ji.util.d.a1;
    }
    
    public static final String a(final String s, final String s2, final String s3) {
        return ji.io.ac.b(s, s2, s3);
    }
    
    public static final void de() {
        ++ji.util.d.fq;
    }
    
    public static final void df() {
        --ji.util.d.fq;
    }
    
    public static final void bi(final boolean ez) {
        ji.util.d.ez = ez;
    }
    
    public static final boolean dg() {
        return ji.util.d.ez;
    }
    
    public static final void bj(final boolean e5) {
        ji.util.d.e5 = e5;
    }
    
    public static final boolean dh() {
        return ji.util.d.e5;
    }
    
    public static final void ae(final String et) {
        ji.util.d.et = et;
    }
    
    public static final void bk(final boolean dj) {
        ji.util.d.dj = dj;
    }
    
    public static final void bl(final boolean dk) {
        ji.util.d.dk = dk;
    }
    
    public static final String af(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(247, s)))).append("\n").append(b(248, s)).append("\n").append(b(249, s)).append("\n")));
    }
    
    public static final void aa(final int c8) {
        ji.util.d.c8 = c8;
    }
    
    public static final int di() {
        return ji.util.d.c8;
    }
    
    public static final void bm(final boolean eg) {
        ji.util.d.eg = eg;
    }
    
    public static final boolean dj() {
        return ji.util.d.eg;
    }
    
    public static final void bn(final boolean eh) {
        ji.util.d.eh = eh;
    }
    
    public static final boolean dk() {
        return ji.util.d.em;
    }
    
    public static final void bo(final boolean em) {
        ji.util.d.em = em;
    }
    
    public static final void bp(final boolean es) {
        ji.util.d.es = es;
    }
    
    public static final boolean dl() {
        return ji.util.d.es;
    }
    
    public static final void bq(final boolean e7) {
        ji.util.d.e7 = e7;
    }
    
    public static final boolean dm() {
        return ji.util.d.eh;
    }
    
    public static final void br(final boolean ei) {
        ji.util.d.ei = ei;
    }
    
    public static final boolean dn() {
        return ji.util.d.ei;
    }
    
    public static final String ag(final String s) {
        try {
            if (ji.util.d.ln == null) {
                ji.util.d.ln = ji.sec.f.a("java.vendor", s);
            }
        }
        catch (Exception ex) {
            ji.util.d.ln = ex.toString();
        }
        return ji.util.d.ln;
    }
    
    public static final String ah(final String s) {
        String a;
        try {
            a = ji.sec.f.a("browser", s);
        }
        catch (Exception ex) {
            a = "";
        }
        return a;
    }
    
    public static final boolean ai(final String s) {
        boolean b = false;
        if (aj(s)) {
            try {
                final String a = ji.sec.f.a("user.dir", s);
                if (a != null && a.toLowerCase().indexOf("netscape") >= 0) {
                    b = true;
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public static final boolean aj(final String s) {
        try {
            final String ag = ag(s);
            if (ag != null && ag.toLowerCase().indexOf("apple") >= 0) {
                ji.util.d.e9 = true;
            }
            ji.util.d.e8 = true;
        }
        catch (Exception ex) {}
        return ji.util.d.e9 && !dp();
    }
    
    public static final boolean ak(final String s) {
        if (!ji.util.d.fa) {
            try {
                final String ag = ag(s);
                if (ag != null && ag.toLowerCase().indexOf("microsoft") >= 0) {
                    ji.util.d.fb = true;
                }
                ji.util.d.fa = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.fb;
    }
    
    public static final void al(final String s) {
        ji.util.e.o(s);
        c6(s);
        c8(s);
        dd(s);
        ji.util.e.ab(s);
        c7(s);
    }
    
    private static final boolean c6(final String s) {
        if (!ji.util.d.f3) {
            try {
                if (!em()) {
                    final String a = ji.sec.f.a("mozilla.workaround", s);
                    if (a != null && a.toLowerCase().indexOf("true") >= 0) {
                        ji.util.d.f2 = true;
                    }
                }
                ji.util.d.f3 = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.f2;
    }
    
    public static final boolean do() {
        return ji.util.d.f2;
    }
    
    public static final boolean am(final String s) {
        aj(s);
        ji.util.d.kf = (ji.util.d.e9 && dp());
        ji.util.d.kg = true;
        return ji.util.d.kf;
    }
    
    public static final boolean an(final String s) {
        if (!ji.util.d.kh) {
            ji.util.d.kf = (aj(s) && dp());
            ji.util.d.kh = true;
        }
        return ji.util.d.kf;
    }
    
    private static final boolean c7(final String s) {
        if (!ji.util.d.k1) {
            int intValue = -1;
            int intValue2 = -1;
            try {
                if (ji.util.d.ll == null) {
                    ji.util.d.ll = ji.sec.f.a("java.version", s);
                }
                final int index = ji.util.d.ll.indexOf(".");
                intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
                intValue2 = Integer.valueOf(ji.util.d.ll.substring(index + 1, ji.util.d.ll.indexOf(".", index + 1)));
            }
            catch (Exception ex) {}
            ji.util.d.k0 = (intValue > 1 || intValue2 >= 4);
            ji.util.d.k1 = true;
        }
        return ji.util.d.k0;
    }
    
    public static final boolean dp() {
        return ji.util.d.k0;
    }
    
    public static final boolean ao(final String s) {
        int intValue = -1;
        int intValue2 = -1;
        try {
            if (ji.util.d.ll == null) {
                ji.util.d.ll = ji.sec.f.a("java.version", s);
            }
            final int index = ji.util.d.ll.indexOf(".");
            intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
            intValue2 = Integer.valueOf(ji.util.d.ll.substring(index + 1, ji.util.d.ll.indexOf(".", index + 1)));
        }
        catch (Exception ex) {}
        return intValue > 1 || intValue2 >= 5;
    }
    
    public static final boolean a(final int n, final int n2, final int n3, final int n4, final String s) {
        try {
            if (ji.util.d.ll == null) {
                ji.util.d.ll = ji.sec.f.a("java.version", s);
            }
            final int index = ji.util.d.ll.indexOf(".");
            final int index2 = ji.util.d.ll.indexOf(".", index + 1);
            final int index3 = ji.util.d.ll.indexOf("_", index2 + 1);
            final int intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
            if (intValue != n) {
                return n < intValue;
            }
            final int intValue2 = Integer.valueOf(ji.util.d.ll.substring(index + 1, ji.util.d.ll.indexOf(".", index + 1)));
            if (intValue2 != n2) {
                return n2 < intValue2;
            }
            int n5;
            int intValue3;
            if (index3 > -1) {
                n5 = Integer.valueOf(ji.util.d.ll.substring(index2 + 1, ji.util.d.ll.indexOf("_", index2 + 1)));
                intValue3 = Integer.valueOf(ji.util.d.ll.substring(index3 + 1));
            }
            else {
                n5 = Integer.valueOf(ji.util.d.ll.substring(index2 + 1));
                intValue3 = 0;
            }
            if (n5 != n3) {
                return n3 < n5;
            }
            return intValue3 == n4 || n4 < intValue3;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static final boolean a(final int n, final int n2, final String s) {
        return b(n, n2, 0, 0, s);
    }
    
    public static final boolean b(final int n, final int n2, final int n3, final int n4, final String s) {
        int intValue = -1;
        int intValue2 = -1;
        int n5 = 0;
        int intValue3 = 0;
        if (!em()) {
            try {
                if (ji.util.d.ll == null) {
                    ji.util.d.ll = System.getProperty("java.version");
                }
                if (ji.util.d.ll != null) {
                    final int index = ji.util.d.ll.indexOf(".");
                    intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
                    final String substring = ji.util.d.ll.substring(index + 1);
                    final int index2 = substring.indexOf(".");
                    intValue2 = Integer.valueOf(substring.substring(0, index2));
                    final String substring2 = substring.substring(index2 + 1);
                    final int index3 = substring2.indexOf("_");
                    if (index3 >= 0) {
                        n5 = Integer.valueOf(substring2.substring(0, index3));
                        intValue3 = Integer.valueOf(substring2.substring(index3 + 1));
                    }
                    else {
                        n5 = Integer.valueOf(substring2);
                        intValue3 = 0;
                    }
                }
            }
            catch (Exception ex) {}
        }
        boolean b = false;
        if (ji.util.d.ll != null) {
            if (intValue == n) {
                if (intValue2 == n2) {
                    if (n5 == n3) {
                        if (intValue3 >= n4) {
                            b = true;
                        }
                    }
                    else if (n5 > n3) {
                        b = true;
                    }
                }
                else if (intValue2 > n2) {
                    b = true;
                }
            }
            else if (intValue > n) {
                b = true;
            }
        }
        return b;
    }
    
    public static final String ap(final String s) {
        if (!em()) {
            try {
                if (ji.util.d.lm == null) {
                    ji.util.d.lm = System.getProperty("javaplugin.version");
                }
                return ji.util.d.lm;
            }
            catch (Exception ex) {
                a(ex);
            }
        }
        return null;
    }
    
    public static final boolean c(final int n, final int n2, final int n3, final int n4, final String s) {
        int intValue = -1;
        int intValue2 = -1;
        int n5 = 0;
        int intValue3 = 0;
        if (!em()) {
            try {
                if (ji.util.d.lm == null) {
                    ji.util.d.lm = System.getProperty("javaplugin.version");
                }
                if (ji.util.d.lm != null) {
                    final int index = ji.util.d.lm.indexOf(".");
                    intValue = Integer.valueOf(ji.util.d.lm.substring(0, index));
                    final String substring = ji.util.d.lm.substring(index + 1);
                    final int index2 = substring.indexOf(".");
                    intValue2 = Integer.valueOf(substring.substring(0, index2));
                    final String substring2 = substring.substring(index2 + 1);
                    final int index3 = substring2.indexOf("_");
                    if (index3 >= 0) {
                        n5 = Integer.valueOf(substring2.substring(0, index3));
                        intValue3 = Integer.valueOf(substring2.substring(index3 + 1));
                    }
                    else {
                        n5 = Integer.valueOf(substring2);
                        intValue3 = 0;
                    }
                }
            }
            catch (Exception ex) {}
        }
        boolean b = false;
        if (ji.util.d.lm != null) {
            if (intValue == n) {
                if (intValue2 == n2) {
                    if (n5 == n3) {
                        if (intValue3 >= n4) {
                            b = true;
                        }
                    }
                    else if (n5 > n3) {
                        b = true;
                    }
                }
                else if (intValue2 > n2) {
                    b = true;
                }
            }
            else if (intValue > n) {
                b = true;
            }
        }
        return b;
    }
    
    public static final String aq(final String s) {
        if (!em()) {
            try {
                if (ji.util.d.ll == null) {
                    ji.util.d.ll = System.getProperty("java.version");
                }
                return ji.util.d.ll;
            }
            catch (Exception ex) {
                a(ex);
            }
        }
        return null;
    }
    
    public static final boolean ar(final String s) {
        int intValue = -1;
        int intValue2 = -1;
        int intValue3 = -1;
        if (!em()) {
            try {
                if (ji.util.d.ll == null) {
                    ji.util.d.ll = ji.sec.f.a("java.version", s);
                }
                if (ji.util.d.ll != null) {
                    final int index = ji.util.d.ll.indexOf(".");
                    intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
                    final String substring = ji.util.d.ll.substring(index + 1);
                    final int index2 = substring.indexOf(".");
                    intValue2 = Integer.valueOf(substring.substring(0, index2));
                    final String substring2 = substring.substring(index2 + 1);
                    final int index3 = substring2.indexOf("_");
                    intValue3 = Integer.valueOf(substring2.substring(0, index3));
                    Integer.valueOf(substring2.substring(index3 + 1));
                }
            }
            catch (Exception ex) {}
        }
        boolean b = false;
        if (ji.util.d.ll != null) {
            if (intValue < 1) {
                b = true;
            }
            else if (intValue == 1 && intValue2 < 4) {
                b = true;
            }
            else if (intValue == 1 && intValue2 == 4 && intValue3 == 0) {
                b = true;
            }
        }
        return b;
    }
    
    public static final boolean as(final String s) {
        try {
            if (ji.util.d.ll == null) {
                ji.util.d.ll = ji.sec.f.a("java.version", s);
            }
            final int index = ji.util.d.ll.indexOf(".");
            final int intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
            final int intValue2 = Integer.valueOf(ji.util.d.ll.substring(index + 1, ji.util.d.ll.indexOf(".", index + 1)));
            return intValue > 2 || intValue2 >= 0;
        }
        catch (Exception ex) {
            if (cy()) {
                ex.printStackTrace();
            }
            return false;
        }
    }
    
    public static final boolean at(final String s) {
        try {
            if (ji.util.d.ll == null) {
                ji.util.d.ll = ji.sec.f.a("java.version", s);
            }
            final int index = ji.util.d.ll.indexOf(".");
            final int intValue = Integer.valueOf(ji.util.d.ll.substring(0, index));
            final int intValue2 = Integer.valueOf(ji.util.d.ll.substring(index + 1, ji.util.d.ll.indexOf(".", index + 1)));
            return intValue <= 1 && intValue2 <= 3;
        }
        catch (Exception ex) {
            if (cy()) {
                ex.printStackTrace();
            }
            return false;
        }
    }
    
    public static final boolean au(final String s) {
        if (!ji.util.d.f5) {
            try {
                if (!em() && ji.sec.f.a("http.agent", s).toLowerCase().indexOf("mozilla") >= 0) {
                    ji.util.d.f4 = true;
                }
                ji.util.d.f5 = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.f4;
    }
    
    public static final boolean av(final String s) {
        if (!ji.util.d.fc) {
            try {
                final String ag = ag(s);
                if (ag != null && ag.toLowerCase().indexOf("netscape") >= 0) {
                    ji.util.d.fd = true;
                }
                ji.util.d.fc = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.fd;
    }
    
    public static final boolean aw(final String s) {
        return ax(s);
    }
    
    public static final boolean ax(final String s) {
        if (!ji.util.d.ff) {
            try {
                final String q = ji.util.e.q(s);
                if (q != null && (q.toLowerCase().indexOf("os.name") >= 0 || q.toLowerCase().indexOf("mac os x") >= 0)) {
                    ji.util.d.fg = true;
                }
                ji.util.d.ff = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.fg;
    }
    
    public static final boolean ay(final String s) {
        if (!ji.util.d.fe) {
            try {
                final String q = ji.util.e.q(s);
                if (q != null && (q.toLowerCase().indexOf("macos") >= 0 || q.toLowerCase().indexOf("mac os") >= 0)) {
                    ji.util.d.fi = true;
                }
                ji.util.d.fe = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.fi;
    }
    
    private static final boolean c8(final String s) {
        if (!ji.util.d.fh) {
            try {
                if (ay(s)) {
                    if (ji.util.d.ll == null) {
                        ji.util.d.ll = ji.sec.f.a("java.version", s);
                    }
                    if (ji.util.d.ll != null) {
                        try {
                            if (ji.util.d.ll.substring(0, 3).indexOf("1.1") >= 0) {
                                ji.util.d.fj = true;
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
                ji.util.d.fh = true;
            }
            catch (Exception ex2) {}
        }
        return ji.util.d.fj;
    }
    
    public static final boolean dq() {
        return ji.util.d.fj;
    }
    
    public static final boolean az(final String s) {
        if (!ji.util.d.fm) {
            try {
                final String ah = ah(s);
                if (ah != null && ah.toLowerCase().indexOf("lotus notes") >= 0) {
                    ji.util.d.fn = true;
                }
                ji.util.d.fm = true;
            }
            catch (Exception ex) {}
        }
        return ji.util.d.fn;
    }
    
    public static final boolean a0(final String s) {
        boolean b = false;
        try {
            final String a = ji.sec.f.a("os.arch", s);
            if (a != null && a.toLowerCase().indexOf("x86") >= 0) {
                b = true;
            }
            if (!b && ji.sec.f.a("os.name", s).toLowerCase().indexOf("windows") >= 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final String a1(final String s) {
        return ji.sec.f.a("user.dir", s);
    }
    
    public static final boolean dr() {
        return ji.util.i.c(79);
    }
    
    public static final void bs(final boolean b) {
        if (b) {
            ji.util.i.a(79);
        }
        else {
            ji.util.i.b(79);
        }
    }
    
    public static final boolean ds() {
        return ji.util.i.c(80);
    }
    
    public static final void bt(final boolean b) {
        if (b) {
            ji.util.i.a(80);
        }
        else {
            ji.util.i.b(80);
        }
    }
    
    public static final boolean dt() {
        return ji.util.i.c(81);
    }
    
    public static boolean du() {
        return ji.util.i.c(183) || ji.util.i.c(182);
    }
    
    public static final void bu(final boolean b) {
        if (b) {
            ji.util.i.a(81);
        }
        else {
            ji.util.i.b(81);
        }
    }
    
    public static final boolean dv() {
        return ji.util.d.d9;
    }
    
    public static final void bv(final boolean d9) {
        ji.util.d.d9 = d9;
    }
    
    public static void c(final String s, final String s2) {
        ji.io.h.d(s, s2);
    }
    
    public static void d(final String s, final String s2) {
        ji.io.h.e(s, s2);
    }
    
    public static final boolean dw() {
        return ji.util.d.el;
    }
    
    public static final boolean dx() {
        return ji.util.d.ei && (dj() || dm() || dw());
    }
    
    public static final void bw(final boolean ef) {
        ji.util.d.ef = ef;
    }
    
    public static final boolean dy() {
        return ji.util.d.ef;
    }
    
    public static final void d(final long ee) {
        ji.util.d.ee = ee;
    }
    
    public static final long dz() {
        return ji.util.d.ee;
    }
    
    public static final long d0() {
        return ji.util.d.ed;
    }
    
    public static final void bx(final boolean fr) {
        ji.util.d.fr = fr;
    }
    
    public static final boolean d1() {
        return ji.util.d.fr;
    }
    
    public static void a(final Container container, final String s) {
        if (container != null) {
            try {
                while (container.getComponentCount() > 0) {
                    container.remove(container.getComponent(0));
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public static boolean d2() {
        try {
            if (ji.util.d.hz != null) {
                for (int i = 0; i < ji.util.d.hz.length; ++i) {
                    if (ji.util.d.hz[i].toLowerCase().equals("gif")) {
                        return true;
                    }
                }
                return false;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    private static void c9(final String s) {
        try {
            d4();
            if (ji.util.d.gq == null) {
                ji.util.d.gq = new bj(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void d3() {
        try {
            d4();
            if (ji.util.d.gq != null) {
                ji.util.d.gq.i();
                ji.util.d.gq = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public static boolean d4() {
        try {
            if (ji.util.d.gq != null) {
                final boolean dg = dg();
                ji.util.d.gq.b();
                return dg;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static final void by(final boolean bc) {
        ji.util.d.bc = bc;
    }
    
    public static final boolean d5() {
        return ji.util.d.bc;
    }
    
    public static final Object a2(final String s) throws Exception {
        Object instance = null;
        if (s.length() > 0) {
            final Class a3 = a3(s);
            if (a3 != null) {
                instance = a3.newInstance();
            }
        }
        return instance;
    }
    
    public static final Class a3(final String s) throws Exception {
        return Class.forName(s);
    }
    
    public static final void a(final String s, final String s2, final Component component, final String s3, final af af, final String s4, final boolean b) {
        a(s, s2, component, s3, af, s4, null, b);
    }
    
    public static final void a(final String s, final String s2, final Component component, final String s3, final af af, final String s4, final String s5, final boolean b) {
        a(s, s2, component, s3, af, s4, s5, 0, false, b);
    }
    
    public static final void a(final String s, final String s2, final Component component, final String s3, final af af, final String s4, final String s5, final int n, final boolean b, final boolean b2) {
        if (!b() && ji.util.d.f6) {
            try {
                if (component instanceof ad && ((ad)component).cz()) {
                    throw new Exception(s2);
                }
                d7();
                if (s2 != null && s2.toLowerCase().indexOf("server") >= 0 && s2.toLowerCase().indexOf("status") >= 0 && s2.toLowerCase().indexOf("200") >= 0) {
                    return;
                }
                c9(s4);
                if (s2 == null) {
                    ji.util.d.gq.a(s, s2, component, n, s3, s4, s5, false, b2);
                }
                else if (s2.toLowerCase().indexOf(ji.util.d.ev.toLowerCase()) >= 0) {
                    if (ji.util.d.eu) {
                        ji.util.d.gq.a(s, s2, component, n, s3, s4, s5, false, b2);
                    }
                }
                else {
                    ji.util.d.gq.a(s, s2, component, n, s3, s4, s5, b, b2);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (ji.util.i.c(82) || b()) {
            String s6;
            if (b()) {
                s6 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": ").append(s2)));
            }
            else {
                s6 = String.valueOf(String.valueOf(new StringBuffer("Dialogs disabled: message dialog title: ").append(s).append(", text: ").append(s2)));
            }
            ji.io.h.d(s4, s6);
        }
    }
    
    public static final void bz(final boolean hg) {
        ji.util.d.hg = hg;
    }
    
    public static final Color a(final String s, final Component component, final af af, final Color color, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2) {
        if (ji.util.d.f6) {
            try {
                d7();
                c9(s2);
                ji.util.d.hg = false;
                final Color a = ji.util.d.gq.a(s, component, af, color, b, b2, b3, b4, s2);
                ji.util.d.gh = ji.util.d.gq.a();
                return a;
            }
            catch (Exception ex) {
                return null;
            }
        }
        if (ji.util.i.c(82)) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("Dialogs disabled: attempt to open color selection with title: ").append(s).append(" ignored"))));
        }
        return null;
    }
    
    public static final boolean d6() {
        return ji.util.d.gh;
    }
    
    public static final boolean a(final String s, final String s2, final Component component, final String s3, final af af, final String s4) {
        if (ji.util.d.f6) {
            try {
                d7();
                c9(s4);
                if (s2 == null) {
                    return ji.util.d.gq.a(s, "--------null--------", component, true, s3, s4, null, false, false);
                }
                return ji.util.d.gq.a(s, s2, component, true, s3, s4, null, false, false);
            }
            catch (Exception ex) {
                return false;
            }
        }
        if (ji.util.i.c(82)) {
            ji.io.h.d(s4, String.valueOf(String.valueOf(new StringBuffer("Dialogs disabled: yes/no dialog (automatically answering no) title: ").append(s).append(", text: ").append(s2))));
        }
        return false;
    }
    
    public static final boolean a(final String s, final String s2, final Component component, final af af, final String s3) {
        return a(s, s2, component, null, af, s3);
    }
    
    public static final void a(final String s, final String s2, final af af, final String s3) {
        a(s, s2, null, null, af, s3, false);
    }
    
    public static final void a(final String s, final af af, final String s2) {
        a(ji.res.ay.a(), s, null, null, af, s2, false);
    }
    
    public static final void a(final String s, final String s2, final Component component, final int n, final String s3, final af af, final String s4) throws Exception {
        if (ji.util.d.f6) {
            d7();
            if (s2.toLowerCase().indexOf("server") >= 0 && s2.toLowerCase().indexOf("status") >= 0 && s2.toLowerCase().indexOf("200") >= 0) {
                return;
            }
            c9(s4);
            if (s2 == null) {
                ji.util.d.gq.a(s, "--------null--------", component, n, s3, s4, null, false, false);
            }
            else if (s2.toLowerCase().indexOf(ji.util.d.ev.toLowerCase()) >= 0) {
                if (ji.util.d.eu) {
                    ji.util.d.gq.a(s, s2, component, n, s3, s4, null, false, false);
                }
            }
            else {
                ji.util.d.gq.a(s, s2, component, n, s3, s4, null, false, false);
            }
        }
        else if (ji.util.i.c(82)) {
            ji.io.h.d(s4, String.valueOf(String.valueOf(new StringBuffer("Dialogs disabled: message dialog title: ").append(s).append(", text: ").append(s2))));
        }
    }
    
    public static final void a(final Exception ex, final String s, final Component component, final af af, final String s2) {
        try {
            a(ex);
            final String string = ex.toString();
            if (string.toLowerCase().indexOf("server") >= 0 && string.toLowerCase().indexOf("status") >= 0 && string.toLowerCase().indexOf("200") >= 0) {
                return;
            }
            final String b = b(311, s2);
            String s3;
            if (b != null) {
                final String bc = bc(b);
                if (bc.length() > 0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(bc))).append(" : ").append(s)));
                }
                else {
                    s3 = s;
                }
            }
            else {
                s3 = s;
            }
            if (s3 == null) {
                s3 = ji.res.ay.a();
            }
            if (ex.toString().toLowerCase().indexOf(ji.util.d.ev.toLowerCase()) >= 0) {
                if (ji.util.d.eu) {
                    a(s3, b(289, s2), component, null, af, s2, false);
                }
            }
            else {
                a(s3, ex.toString(), component, null, af, s2, false);
            }
        }
        catch (Exception ex2) {}
    }
    
    public static final void d7() {
        try {
            if (ji.util.d.gq != null) {
                ji.util.d.gq.e();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void b0(final boolean b) {
        try {
            if (ji.util.d.gq != null) {
                ji.util.d.gq.a(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final boolean d8() {
        try {
            if (ji.util.d.gq != null) {
                return ji.util.d.gq.c();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static final boolean d9() {
        try {
            if (ji.util.d.gq != null) {
                return ji.util.d.gq.d();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static final void b1(final boolean er) {
        ji.util.d.er = er;
    }
    
    public static final void a(final Exception ex, final String s, final Component component, final int n, final af af, final String s2) {
        a(ex, s, null, component, n, af, s2);
    }
    
    public static final void a(final Exception ex, final String s, final String s2, final Component component, final int n, final af af, final String s3) {
        try {
            a(ex);
            final String b = b(311, s3);
            String value;
            if (!b.equals("")) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b))).append(" : ").append(s)));
            }
            else {
                value = s;
            }
            String s4 = ex.toString();
            if (!by(s2)) {
                s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf("\n\n".concat(String.valueOf(String.valueOf(s2))))));
            }
            a(value, s4, component, n, null, af, s3);
        }
        catch (Exception ex2) {}
    }
    
    public static final void b(final String s, final String s2, final af af, final String s3) {
        if (!ji.util.e.ak()) {
            a(s, s2, null, null, af, s3, false);
            ji.util.d.eb = true;
        }
    }
    
    public static final String a(final int n, final String s) {
        try {
            switch (n) {
                case 1: {
                    return b(305, s);
                }
                case 2: {
                    return b(306, s);
                }
                case 3: {
                    return b(307, s);
                }
                default: {
                    return b(308, s);
                }
            }
        }
        catch (Exception ex) {
            return b(308, s);
        }
    }
    
    public static final void ea() {
        try {
            Thread.yield();
        }
        catch (Exception ex) {}
    }
    
    public static final void b(final int n, final int n2, final String s) {
        try {
            if (ji.util.d.s) {
                if (ji.util.d.ak) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Sleep id:").append(n2).append(", time:").append(n))));
                }
                Thread.sleep(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final URLConnection a(final URL url, final boolean b, final String s) throws Exception {
        return a(url, b, false, s);
    }
    
    public static final URLConnection b(final URL url, final boolean b, final String s) throws Exception {
        return a(url, b, true, s);
    }
    
    private static final URLConnection a(final URL url, final boolean useCaches, final boolean b, final String s) throws Exception {
        URLConnection openConnection = null;
        int n = 1;
        int n2 = 2;
        ji.util.d.i0 = false;
        while (n != 0 && !ji.util.d.i0) {
            try {
                c(url, s);
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Opening connection..(").append(url).append(")..."))));
                }
                openConnection = url.openConnection();
                if (openConnection != null) {
                    final String m = s.m(s);
                    if (m != null) {
                        openConnection.setRequestProperty("Accept-Language", m);
                    }
                }
                try {
                    openConnection.setUseCaches(useCaches);
                }
                catch (Exception ex) {
                    a(ex);
                }
                if (b) {
                    openConnection.setDoOutput(true);
                }
                else {
                    openConnection.setAllowUserInteraction(true);
                    if (url.toString().toLowerCase().indexOf("file:") >= 0) {
                        if (dr()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: getting file length..."));
                        }
                        if (openConnection.getContentLength() < 0) {
                            n2 = 0;
                            throw new FileNotFoundException(url.toString());
                        }
                    }
                    if (dr()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: Waiting for connection..."));
                    }
                    a(openConnection, s);
                    ji.net.cookie.bc.c(openConnection, s);
                    ji.net.cookie.bc.b(openConnection, s);
                    ji.sec.bf.a(openConnection, s);
                    if (dr()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: Waited for connection..."));
                    }
                    ji.util.d.g8 = openConnection.getLastModified();
                    ji.net.cookie.bc.a(openConnection, s);
                    final String string = url.toString();
                    final String string2 = openConnection.getURL().toString();
                    if (!by(string) && !by(string2) && !string.equals(string2)) {
                        if (dr()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: URLs seem to differ, detecting logged out redirection"));
                        }
                        final bd a = ji.document.bd.a(s);
                        if (a != null) {
                            final String b2 = a.b(8);
                            if (b2 != null && string2.indexOf(b2) > -1) {
                                if (dr()) {
                                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: URL ").append(string2).append(" contains logged out redirection string."))));
                                }
                                throw new bg(b(1332, s));
                            }
                        }
                        else if (cy()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: Can't get config object"));
                        }
                    }
                    if (dr()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Connection date: ").append(ji.util.d.g8))));
                    }
                    if (dr()) {
                        int n3 = 0;
                        try {
                            String headerField;
                            while ((headerField = openConnection.getHeaderField(n3)) != null) {
                                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Net: Connection Header: ").append(openConnection.getHeaderFieldKey(n3)).append(" = ").append(headerField))));
                                ++n3;
                            }
                        }
                        catch (Exception ex3) {}
                    }
                }
                n = 0;
                continue;
            }
            catch (bg bg) {
                throw bg;
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
                if (n2 > 0) {
                    --n2;
                    b(250, 33, s);
                    continue;
                }
                throw ex2;
            }
            break;
        }
        if (dr()) {
            ji.io.h.d(s, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: Connection processed..."));
        }
        return openConnection;
    }
    
    private static final void c(final URL url, final String s) {
        try {
            final String string = url.toString();
            final int index = url.toString().toLowerCase().indexOf("jsessionid");
            if (index >= 0) {
                String s2 = string.substring(index + 11);
                final int index2 = s2.indexOf("&");
                if (index2 >= 0) {
                    s2 = s2.substring(0, index2);
                }
                if (!by(s2)) {
                    ji.util.d.jp = "JSESSIONID=".concat(String.valueOf(String.valueOf(s2)));
                }
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Got jSessionId: ").append(ji.util.d.jp))));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final URLConnection urlConnection, final String s) {
        try {
            if (ji.util.d.jp != null) {
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Setting request property \"Cookie\": ").append(ji.util.d.jp).append(" on ").append(urlConnection.getURL()))));
                }
                urlConnection.setRequestProperty("Cookie", ji.util.d.jp);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a4(String bc) {
        bc = bc(bc);
        if (!by(bc) && !bc.equals("-")) {
            ji.util.d.je = bc;
        }
    }
    
    public static final String a5(final String s) {
        return b(b(b(b(s, "&lt;", "<"), "&gt;", ">"), "&amp;", "&"), "&quot;", "\"");
    }
    
    public static final String a(final String s, final boolean b) {
        String s2 = a6(s);
        if (b) {
            s2 = b(b(b(b(s2, "'", "&apos;"), "\t", "&#9;"), "\n", "&#10;"), "\r", "&#13;");
        }
        return s2;
    }
    
    public static final String a6(final String s) {
        return b(b(b(b(s, "&", "&amp;"), "<", "&lt;"), ">", "&gt;"), "\"", "&quot;");
    }
    
    public static final String a7(final String s) {
        String s2 = "";
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            if (s2.length() > 0) {
                s2 = String.valueOf(String.valueOf(s2)).concat("/");
            }
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("").append((int)charArray[i]).append("(").append(charArray[i]).append(")"))))));
        }
        return s2;
    }
    
    public static final String a8(final String s) {
        String concat = "";
        if (!by(s)) {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                final String concat2 = "0000".concat(String.valueOf(String.valueOf(Integer.toHexString(charArray[i] & '\uffff'))));
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(concat2.substring(concat2.length() - 4, concat2.length()).toUpperCase())));
            }
        }
        return concat;
    }
    
    public static final String e(final String s, final String s2) {
        return s.a(s, s2);
    }
    
    public static final String b(final int n, final String s) {
        return s.a(n, s);
    }
    
    public static final String a(final int n, final String s, final ab ab) {
        return s.a(n, s, ab);
    }
    
    public static String a9(final String s) {
        try {
            if (ay(s)) {
                return "mac";
            }
            if (!ay(s) && !ji.util.e.u(s)) {
                return "uni";
            }
        }
        catch (Exception ex) {}
        return "win";
    }
    
    public static String f(final String s, final String s2) {
        if (!ji.util.d.gg) {
            try {
                String b = b(b(b(s.toLowerCase(), "win_", ""), "mac_", ""), "uni_", "");
                if (b != null) {
                    if (b.equals("zh_tw")) {
                        b = "zh_tw";
                    }
                    else if (b.equals("zh_hk")) {
                        b = "zh_tw";
                    }
                    else if (b.equals("zh_mo")) {
                        b = "zh_tw";
                    }
                    else if (b.startsWith("zh")) {
                        b = "zh_cn";
                    }
                }
                Block_9: {
                    for (int i = 0; i < ji.util.d.h0; ++i) {
                        if (ji.util.d.h3[i].equals(b)) {
                            break Block_9;
                        }
                    }
                    return null;
                }
                int i = 0;
                final String x = x(ji.util.d.h4[i], a9(s2));
                bw(ji.util.d.h6[i]);
                if (ay(s2)) {
                    bw(ji.util.d.h7[i]);
                }
                else if (!ay(s2) && !ji.util.e.u(s2)) {
                    bw(ji.util.d.h8[i]);
                }
                ji.util.d.h1 = ji.util.d.h2[i];
                return x;
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    private static String x(final String s, final String s2) {
        final int lastIndex = s.lastIndexOf("/");
        String s3;
        if (lastIndex >= 0) {
            final String substring = s.substring(lastIndex + 1);
            s3 = substring.substring(0, substring.indexOf("."));
        }
        else {
            s3 = s.substring(0, s.indexOf("."));
        }
        return s3;
    }
    
    public static void a(final byte[] c2) {
        ji.util.d.c2 = c2;
    }
    
    public static void b(final byte[] c3) {
        ji.util.d.c3 = c3;
    }
    
    public static void c(final byte[] c4) {
        ji.util.d.c4 = c4;
    }
    
    public static String ba(final String s) {
        try {
            if (!ji.util.d.gg) {
                final y y = new y();
                if (ji.util.d.c2 == null) {
                    ji.util.d.c2 = ji.res.z.a("locals.txt", ji.util.e.an(), 3, null, null, s, y);
                }
                if (ji.util.d.c2 != null) {
                    f(ji.util.d.c2);
                }
                else {
                    ji.util.d.gg = true;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.gg = true;
            ex.printStackTrace();
            return ex.toString();
        }
        return null;
    }
    
    private static void f(final byte[] array) {
        try {
            final c c = new c("jiUtil2");
            final c c2 = new c("jiUtil3");
            final c c3 = new c("jiUtil4");
            final c c4 = new c("jiUtil5");
            final c c5 = new c("jiUtil6");
            final c c6 = new c("jiUtil7");
            final c c7 = new c("jiUtil8");
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == 10) {
                    array[i] = 44;
                }
                else if (array[i] < 32) {
                    array[i] = 32;
                }
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(new String(array), ",");
            final int countTokens = stringTokenizer.countTokens();
            Object da = null;
            String da2 = null;
            String da3 = null;
            String da4 = null;
            String da5 = null;
            String da6 = null;
            String da7 = null;
            for (int j = 0; j < countTokens; j += 7) {
                try {
                    da = da(stringTokenizer.nextToken());
                    da2 = da(stringTokenizer.nextToken());
                    da3 = da(stringTokenizer.nextToken());
                    da4 = da(stringTokenizer.nextToken());
                    da5 = da(stringTokenizer.nextToken());
                    da6 = da(stringTokenizer.nextToken());
                    da7 = da(stringTokenizer.nextToken());
                }
                catch (Exception ex2) {}
                if (da == null || da2 == null || da3 == null || da4 == null || da5 == null || da6 == null || da7 == null) {
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer("Incorrect file format: line ").append(j / 7 + 1))));
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(da))).append(", ").append(da2).append(", ").append(da3).append(", ").append(da4).append(", ").append(da5).append(", ").append(da6).append(", ").append(da7))));
                    break;
                }
                c.c(da);
                c2.c(da2);
                c3.c(da3);
                c4.c(da4);
                c5.c(da5);
                c6.c(da6);
                c7.c(da7);
            }
            ji.util.d.h0 = c.b();
            if (ji.util.d.h0 > 0) {
                ji.util.d.h2 = new String[ji.util.d.h0];
                ji.util.d.h3 = new String[ji.util.d.h0];
                ji.util.d.h4 = new String[ji.util.d.h0];
                ji.util.d.h5 = new String[ji.util.d.h0];
                ji.util.d.h6 = new String[ji.util.d.h0];
                ji.util.d.h7 = new String[ji.util.d.h0];
                ji.util.d.h8 = new String[ji.util.d.h0];
                for (int k = 0; k < ji.util.d.h0; ++k) {
                    ji.util.d.h2[k] = (String)c.b(k);
                    ji.util.d.h3[k] = (String)c2.b(k);
                    if (ji.util.d.h3[k] != null) {
                        ji.util.d.h3[k] = ji.util.d.h3[k].toLowerCase();
                    }
                    ji.util.d.h5[k] = (String)c3.b(k);
                    ji.util.d.h4[k] = (String)c4.b(k);
                    ji.util.d.h6[k] = (String)c5.b(k);
                    ji.util.d.h7[k] = (String)c6.b(k);
                    ji.util.d.h8[k] = (String)c7.b(k);
                }
            }
            ji.util.d.mm = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static String da(String substring) {
        String substring2 = null;
        if (substring != null) {
            final int index = substring.indexOf("\"");
            if (index >= 0) {
                substring = substring.substring(index + 1);
                final int index2 = substring.indexOf("\"");
                if (index2 >= 0) {
                    substring2 = substring.substring(0, index2);
                }
            }
        }
        return substring2;
    }
    
    public static final String eb() {
        return ji.util.d.fx;
    }
    
    public static final String a(final long n, final String s) {
        String s2;
        if (n < 1024) {
            s2 = String.valueOf(String.valueOf(new StringBuffer("").append(n).append(" ").append(b(312, s))));
        }
        else if (n < 1048576) {
            final long n2 = n / 1024;
            final long n3 = 100 * (n - n2 * 1024) / n;
            if (n3 < 10) {
                if (n3 != 0) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n2))).append(".0").append(n3).append(b(313, s))));
                }
                else {
                    s2 = String.valueOf(String.valueOf(n2)).concat(String.valueOf(String.valueOf(b(313, s))));
                }
            }
            else {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n2))).append(".").append(n3).append(b(313, s))));
            }
        }
        else {
            final long n4 = n / 1048576;
            final long n5 = 100 * (n - n4 * 1024 * 1024) / n;
            if (n5 < 10) {
                if (n5 != 0) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n4))).append(".0").append(n5).append(b(314, s))));
                }
                else {
                    s2 = String.valueOf(String.valueOf(n4)).concat(String.valueOf(String.valueOf(b(314, s))));
                }
            }
            else {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n4))).append(".").append(n5).append(b(314, s))));
            }
        }
        return s2;
    }
    
    public static final String b(final long n, final String s) {
        String s2;
        if (n < 1024) {
            s2 = String.valueOf(String.valueOf(new StringBuffer("").append(n).append(" ").append(b(312, s))));
        }
        else if (n < 1048576) {
            s2 = String.valueOf(String.valueOf(n / 1024)).concat(String.valueOf(String.valueOf(b(313, s))));
        }
        else {
            final long n2 = n / 1048576;
            final long n3 = 100 * (n - n2 * 1024 * 1024) / n;
            if (n3 < 10) {
                if (n3 != 0) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n2))).append(".0").append(n3).append(b(314, s))));
                }
                else {
                    s2 = String.valueOf(String.valueOf(n2)).concat(String.valueOf(String.valueOf(b(314, s))));
                }
            }
            else {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n2))).append(".").append(n3).append(b(314, s))));
            }
        }
        return s2;
    }
    
    public static final boolean g(final String s, final String s2) {
        if (s2.indexOf("#") < 0 && s2.indexOf("@") < 0 && s2.indexOf("*") < 0) {
            return s.equals(s2);
        }
        if (s.length() != s2.length()) {
            return false;
        }
        final char[] charArray = s.toCharArray();
        final char[] charArray2 = s2.toCharArray();
        for (int i = 0; i < s.length(); ++i) {
            if (charArray[i] != charArray2[i]) {
                if (charArray2[i] == '#') {
                    if (!Character.isDigit(charArray[i])) {
                        return false;
                    }
                }
                else if (charArray2[i] == '@') {
                    if (Character.isDigit(charArray[i])) {
                        return false;
                    }
                }
                else if (charArray2[i] != '*') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static final int ab(final int n) {
        return (n & 0xFF000000) | 255 - ((n & 0xFF0000) >> 16) << 16 | 255 - ((n & 0xFF00) >> 8) << 8 | 255 - (n & 0xFF);
    }
    
    public static final Color i(final Color color) {
        return new Color(ab(color.getRGB()));
    }
    
    public static final boolean bb(final String s) {
        boolean b = true;
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (!Character.isDigit(char1) && char1 != '.' && char1 != '-') {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public static final int ac(final int n) {
        int n2 = 0;
        int i;
        for (i = n; i < 0; i += 360) {}
        switch (i % 360) {
            case 0: {
                n2 = 0;
                break;
            }
            case 90: {
                n2 = 90;
                break;
            }
            case 180: {
                n2 = 180;
                break;
            }
            case 270: {
                n2 = 270;
                break;
            }
        }
        return n2;
    }
    
    public static final int a(String substring, final int n, final boolean b) {
        if (substring == null) {
            return n;
        }
        if (substring.length() == 0) {
            return n;
        }
        if (b) {
            final int index = substring.indexOf(".");
            if (index > 0) {
                substring = substring.substring(0, index);
            }
        }
        return c(substring, n);
    }
    
    public static final int c(final String s, final int n) {
        if (s == null) {
            return n;
        }
        if (s.length() == 0) {
            return n;
        }
        String s2 = null;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 > ' ' || char1 == '-') {
                if (s2 != null) {
                    s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(char1)));
                }
            }
            else if (s2 == null) {
                if (i == 0) {
                    s2 = "";
                }
                else {
                    s2 = s.substring(0, i - 1);
                }
            }
        }
        if (s2 == null) {
            if (bb(s)) {
                try {
                    return Integer.parseInt(s);
                }
                catch (NumberFormatException ex) {
                    return (int)Double.parseDouble(s);
                }
            }
            return n;
        }
        if (bb(s2)) {
            return Integer.parseInt(s2);
        }
        return n;
    }
    
    public static final long a(final String s, final long n) {
        if (s == null) {
            return n;
        }
        if (s.equals("")) {
            return n;
        }
        String value = "";
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); ++i) {
            if (charArray[i] > ' ' || charArray[i] == '-') {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("").append(charArray[i])));
            }
        }
        if (bb(value)) {
            return Long.parseLong(value);
        }
        return n;
    }
    
    public static final boolean b(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        if (s.equals("")) {
            return b;
        }
        return s.toLowerCase().equals("true") || (!s.toLowerCase().equals("false") && b);
    }
    
    public static final double a(final String s, final double n) {
        if (s == null) {
            return n;
        }
        if (s.equals("")) {
            return n;
        }
        final String upperCase = b(be(s), ",", ".").toUpperCase();
        try {
            if (upperCase.indexOf("E-") >= 0) {
                final StringBuffer sb = new StringBuffer();
                final StringBuffer sb2 = new StringBuffer();
                boolean b = false;
                final char[] charArray = upperCase.toCharArray();
                boolean b2 = false;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == 'E') {
                        b2 = true;
                    }
                    else if (b2) {
                        if (charArray[i] == '-') {
                            b = true;
                        }
                        else if (charArray[i] != '+') {
                            sb2.append(charArray[i]);
                        }
                    }
                    else {
                        sb.append(charArray[i]);
                    }
                }
                int int1 = 0;
                double doubleValue = 0.0;
                try {
                    doubleValue = ((DecimalFormat)NumberFormat.getInstance()).parse(sb.toString()).doubleValue();
                    int1 = Integer.parseInt(sb2.toString());
                }
                catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
                double n2;
                if (b) {
                    n2 = doubleValue * Math.pow(10.0, -int1);
                }
                else {
                    n2 = doubleValue * Math.pow(10.0, int1);
                }
                return n2;
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        String value = "";
        final char[] charArray2 = upperCase.toCharArray();
        for (int j = 0; j < upperCase.length(); ++j) {
            if (charArray2[j] > ' ' || charArray2[j] == '.') {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("").append(charArray2[j])));
            }
        }
        if (bb(value)) {
            return Double.valueOf(value);
        }
        return n;
    }
    
    public static final int b2(final boolean b) {
        if (!b) {
            return ji.util.d.d4;
        }
        return ji.util.d.d5;
    }
    
    public static final void ad(final int d4) {
        ji.util.d.d4 = d4;
    }
    
    public static final void ae(final int d5) {
        ji.util.d.d5 = d5;
    }
    
    public static final void af(final int d7) {
        ji.util.d.d7 = d7;
    }
    
    public static final void ag(final int d6) {
        ji.util.d.d6 = d6;
    }
    
    public static final int b3(final boolean b) {
        if (!b) {
            return ji.util.d.d6;
        }
        return ji.util.d.d7;
    }
    
    public static final boolean ec() {
        return ji.util.d.lr == 0 || ji.util.d.lr == 2;
    }
    
    public static final boolean ed() {
        return ji.util.d.lr == 1 || ji.util.d.lr == 2;
    }
    
    public static final void ah(final int lr) {
        if (lr == 0 || lr == 1 || lr == 2) {
            ji.util.d.lr = lr;
        }
    }
    
    public static void a(final String s, final String s2, String s3, final String s4) throws Exception {
        final String s5 = (s != null) ? s : s2;
        if (!ji.util.d.fs) {
            if (s3 == null) {
                s3 = String.valueOf(String.valueOf(s5)).concat(" not found");
            }
            else if (s != null) {
                s3 = b(s3, s2, s);
            }
            s3 = String.valueOf(String.valueOf(s3)).concat("\n\nPlease ensure the above file exists before using this version of viewONE");
            a(s3, (af)null, s4);
            ji.util.d.fs = true;
        }
        if (s3 == null) {
            throw new Exception(String.valueOf(String.valueOf(s5)).concat(" not found"));
        }
        throw new Exception(s3);
    }
    
    public static final String bc(final String s) {
        return c(s, false);
    }
    
    public static final String bd(final String s) {
        String s2 = s;
        try {
            if (s2 != null) {
                while (s2.startsWith("\n")) {
                    s2 = s2.substring(1);
                }
                while (s2.startsWith("\r")) {
                    s2 = s2.substring(1);
                }
                while (s2.endsWith("\r")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                while (s2.endsWith("\n")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final String c(final String s, final boolean b) {
        String s2 = s;
        try {
            if (s2 != null) {
                while (s2.startsWith(" ")) {
                    s2 = s2.substring(1);
                }
                while (s2.endsWith(" ")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                if (b && s2 != null) {
                    final char[] charArray = s2.toCharArray();
                    s2 = "";
                    for (int i = 0; i < charArray.length; ++i) {
                        if ((charArray[i] & '\u00ff') > ' ') {
                            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(charArray[i])));
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final String be(final String s) {
        String s2 = s;
        try {
            if (s2 != null) {
                while (s2.startsWith(" ")) {
                    s2 = s2.substring(1);
                }
                while (s2.endsWith(" ")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                if (s2 != null) {
                    final char[] charArray = s2.toCharArray();
                    s2 = "";
                    for (int i = 0; i < charArray.length; ++i) {
                        if ((charArray[i] & '\u00ff') != ' ') {
                            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(charArray[i])));
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final String bf(final String s) {
        String s2 = s;
        try {
            if (s2 != null) {
                while (s2.startsWith(" ")) {
                    s2 = s2.substring(1);
                }
                while (s2.endsWith(" ")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                if (s2 != null) {
                    final char[] charArray = s2.toCharArray();
                    s2 = "";
                    for (int i = 0; i < charArray.length; ++i) {
                        if ((charArray[i] & '\u00ff') > ' ' && charArray[i] != '.' && charArray[i] != '/' && charArray[i] != '\\') {
                            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(charArray[i])));
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final String bg(final String s) {
        String concat = s;
        try {
            if (concat != null) {
                concat = String.valueOf(String.valueOf(s.toUpperCase().substring(0, 1))).concat(String.valueOf(String.valueOf(s.substring(1))));
            }
        }
        catch (Exception ex) {}
        return concat;
    }
    
    public static final String ai(final int n) {
        switch (n) {
            case 1: {
                return ji.util.d.at;
            }
            case 2: {
                return ji.util.d.au;
            }
            case 3: {
                return ji.util.d.av;
            }
            case 5: {
                return ji.util.d.ax;
            }
            default: {
                return ji.util.d.aw;
            }
        }
    }
    
    public static final c3 a(final Image image, final String s, final Object o) throws Exception {
        final ImageProducer source = image.getSource();
        c2 c2 = null;
        if (source instanceof c1) {
            if (!((c1)source).g()) {
                c2 = new c2(s, o, null, source, 0, 0, image.getWidth(null), image.getHeight(null), 0, null, 24);
            }
        }
        else {
            c2 = new c2(s, o, null, source, 0, 0, image.getWidth(null), image.getHeight(null), 0, null, 24);
        }
        return new c3(image.getWidth(null), image.getHeight(null), s, c2);
    }
    
    public static final void a(final Image image, final c3 c3) throws Exception {
        try {
            final ImageProducer source = image.getSource();
            if (source instanceof c1 && c3.e == null) {
                ((c1)source).f();
            }
        }
        catch (Exception ex) {}
        if (c3 != null) {
            c3.a();
        }
    }
    
    public static final ColorModel a(final Object o, final String s, final Image image, final c3 c3, final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) throws Exception {
        final ImageProducer source = image.getSource();
        if (source instanceof c1 && c3.e == null) {
            return a((c1)source, c3, n, n2, n3, n4, n5, array);
        }
        final int height = image.getHeight(null);
        if (n2 != 0 || n5 != n3 || height != n4) {
            final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, 0, n5);
            pixelGrabber.grabPixels();
            return pixelGrabber.getColorModel();
        }
        final ColorModel a = a(source, c3.e, n, n2, n3, n4, n3, array);
        if (c3.e.f() && !c3.e.e()) {
            return a;
        }
        final PixelGrabber pixelGrabber2 = new PixelGrabber(image, n, n2, n3, n4, array, 0, n5);
        pixelGrabber2.grabPixels();
        return pixelGrabber2.getColorModel();
    }
    
    public static final ColorModel a(final Object o, final String s, final Image image, final c3 c3, final int n, final int n2, final int n3, final int n4, final int n5, final byte[] array) throws Exception {
        final ImageProducer source = image.getSource();
        if (source instanceof c1 && c3.e == null) {
            return a((c1)source, c3, n, n2, n3, n4, n5, array);
        }
        return a(source, c3.e, n, n2, n3, n4, n3, array);
    }
    
    private static final ColorModel a(final ImageProducer imageProducer, final c2 c2, final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) throws Exception {
        c2.g();
        c2.a(array, true);
        return c2.c();
    }
    
    private static final ColorModel a(final ImageProducer imageProducer, final c2 c2, final int n, final int n2, final int n3, final int n4, final int n5, final byte[] array) throws Exception {
        c2.a(array, true);
        return c2.c();
    }
    
    public static final ColorModel a(final c1 c1, final c3 c2, final int n, final int n2, final int n3, final int n4, final int n5, final int[] array) throws Exception {
        c1.a(n, n2, n3, n4, array);
        return c1.e();
    }
    
    public static final ColorModel a(final c1 c1, final c3 c2, final int n, final int n2, final int n3, final int n4, final int n5, final byte[] array) throws Exception {
        c1.a(n, n2, n3, n4, array);
        return c1.e();
    }
    
    public static final byte[] a(final URL url, final af af, int b, final Object o, final String s) throws Exception {
        try {
            if (dr()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("URL: URLFile ").append(url).append("..."))));
            }
            final URLConnection a = a(url, true, s);
            if (a != null) {
                if (b < 0) {
                    b = b(a, s);
                }
                if (b > 0) {
                    return a(a, af, b, url.toString(), null, o, s);
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static final int a(final URL url, final String s, final String s2) throws Exception {
        try {
            if (dr()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("(").append(s2).append(") URL: Retieving URL Size..."))));
            }
            final URLConnection a = a(url, true, s);
            if (a != null) {
                return b(a, s);
            }
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public static final boolean a(final int[] array, final int n, final int n2, final Color color, final Color color2, final int n3) {
        boolean b = false;
        try {
            if (array != null && n > 0 && n2 > 0) {
                final int n4 = color.getRGB() & 0xFFFFFF;
                final int n5 = color2.getRGB() & 0xFFFFFF;
                for (int i = 0; i < array.length; ++i) {
                    if ((array[i] & 0xFFFFFF) == n4) {
                        array[i] = (n3 | n5);
                        b = true;
                    }
                    else if ((array[i] & 0xFF000000) == 0x0) {
                        array[i] = (n3 | n5);
                        b = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public static final boolean a(final int[] array, final Color color, final Color color2, final int n) {
        boolean b = false;
        try {
            if (array != null) {
                final int n2 = color.getRGB() & 0xFFFFFF;
                final int n3 = color2.getRGB() & 0xFFFFFF;
                for (int i = 0; i < array.length; ++i) {
                    if ((array[i] & 0xFFFFFF) == n2) {
                        array[i] = (n | n3);
                        b = true;
                    }
                    else if ((array[i] & 0xFF000000) == 0x0) {
                        array[i] = (n | n3);
                        b = true;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public static final boolean a(final int[] array, final int n, final int n2, final Color color, final Color color2, final Color color3, final int n3, int n4) {
        final boolean b = false;
        try {
            if (array != null && n > 0 && n2 > 0) {
                final int red = color2.getRed();
                final int green = color2.getGreen();
                final int blue = color2.getBlue();
                final int red2 = color3.getRed();
                final int green2 = color3.getGreen();
                final int blue2 = color3.getBlue();
                final int n5 = red2 - red;
                final int n6 = green2 - green;
                final int n7 = blue2 - blue;
                final int n8 = 0;
                final int n9 = 0;
                int n10 = n2;
                if (n3 != 0) {
                    n10 = n2 / 2;
                }
                final int n11 = color.getRGB() & 0xFFFFFF;
                int n12 = 0;
                if (n4 > 13) {
                    n12 = 2;
                    n4 = 31;
                }
                else if (n4 == 13) {
                    n12 = 1;
                    n4 = 23;
                }
                else if (n4 == 11) {
                    n12 = 1;
                    n4 = 16;
                }
                int n13 = n12;
                for (int i = n9; i < n10; ++i) {
                    final int n14 = i * n;
                    final int n15 = n3 | red + n13 * n5 / n4 << 16 | green + n13 * n6 / n4 << 8 | blue + n13 * n7 / n4;
                    for (int j = n8; j < n; ++j) {
                        if ((array[j + n14] & 0xFFFFFF) == n11) {
                            array[j + n14] = n15;
                        }
                    }
                    if (++n13 > n4) {
                        n13 = n12;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }
    
    public static final int b(final URLConnection urlConnection, final String s) throws Exception {
        return a(urlConnection, true, s);
    }
    
    private static final int a(final URLConnection urlConnection, final boolean b, final String s) throws Exception {
        int contentLength = 0;
        try {
            if (dr()) {
                ji.io.h.d(s, "ContentLength: Retrieving length...");
            }
            contentLength = urlConnection.getContentLength();
            if (dr()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: length1 = ").append(contentLength).append("..."))));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (contentLength <= 0 && b) {
                final String string = urlConnection.getURL().toString();
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: Reconnecting = ").append(string).append("..."))));
                }
                final InputStream openStream = urlConnection.getURL().openStream();
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: stream = ").append(openStream).append("..."))));
                }
                if (ji.util.i.c(273)) {
                    contentLength = 0;
                    while (openStream.read() != -1) {
                        ++contentLength;
                    }
                    if (dr()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: length1a = ").append(contentLength).append("..."))));
                    }
                }
                else {
                    contentLength = 0;
                    while (openStream.available() > 0) {
                        if (dr()) {
                            ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: available = ").append(openStream.available()).append("..."))));
                        }
                        while (openStream.read() != -1) {
                            ++contentLength;
                        }
                    }
                }
                if (dr()) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("ContentLength: length2 = ").append(contentLength).append("..."))));
                }
                openStream.close();
            }
        }
        catch (Exception ex2) {
            if (!ji.util.e.t(s) && !ji.util.e.v(s) && ji.util.d.e3 < 5) {
                ++ji.util.d.e3;
                ex2.printStackTrace();
            }
        }
        return contentLength;
    }
    
    public static final byte[] a(final URLConnection urlConnection, final af af, final int n, final String s, final String s2, final Object o, final String s3) throws Exception {
        return a(urlConnection, af, n, s, s2, o, s3, true);
    }
    
    private static boolean f(final Object o) {
        boolean a = true;
        if (o instanceof ad) {
            a = ((ad)o).a();
        }
        return a;
    }
    
    public static final byte[] a(final URLConnection urlConnection, final af af, final int n, final String s, final String s2, final Object o, final String s3, final boolean b) throws Exception {
        int n2 = 0;
        final int be = ji.util.e.be();
        final long currentTimeMillis = System.currentTimeMillis();
        int i = 1;
        byte[] array = null;
        if (dr()) {
            ji.io.h.d(s3, "Connection: Reading connection...");
        }
        if (dr()) {
            ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Connection: Retrieving ").append(s).append("..."))));
        }
        int b2;
        if (n < 0) {
            b2 = b(urlConnection, s3);
        }
        else {
            b2 = n;
        }
        if (dr()) {
            ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Connection: Retrieving ").append(b2).append(" bytes..."))));
        }
        ac ac = null;
        try {
            final int n3 = 2048;
            final int n4 = 102400;
            int n5 = n3;
            int n6 = 0;
            final int be2 = ji.util.e.be();
            if (s2 != null) {
                ac = new ac(s2, true, false, 0, false, o, false, s3);
            }
            while (i != 0) {
                i = 0;
                if (ac != null || !b) {
                    array = new byte[n5];
                }
                else {
                    array = new byte[b2];
                }
                if (array != null) {
                    if (dr()) {
                        ji.io.h.d(s3, "Retrieving...");
                    }
                    final InputStream inputStream = urlConnection.getInputStream();
                    int n7 = 0;
                    int n8 = 0;
                    if (inputStream == null) {
                        continue;
                    }
                    final long currentTimeMillis2 = System.currentTimeMillis();
                    int length = array.length;
                    if (ac != null) {
                        length = b2;
                    }
                    int n10;
                    final int n9 = n10 = length / 100;
                    while (n7 < length && n8 >= 0) {
                        if (ac == null) {
                            n5 = Math.min(n5, array.length - n7);
                            n8 = inputStream.read(array, n7, n5);
                        }
                        else {
                            n8 = inputStream.read(array, 0, n5);
                        }
                        if (n8 > 0) {
                            if (ac != null) {
                                ac.b(array, 0, n8);
                            }
                            n7 += n8;
                            n10 -= n8;
                        }
                        if (ji.util.e.b()) {
                            b(100, 35, s3);
                        }
                        if (System.currentTimeMillis() - currentTimeMillis2 < 400 && n8 >= n5) {
                            ++n6;
                        }
                        else {
                            n6 = 0;
                            if (System.currentTimeMillis() - currentTimeMillis > be2) {
                                final boolean f = f(o);
                                if (n2 == 0 && f && b2 > n3) {
                                    try {
                                        if (af != null) {
                                            af.a(new a6(af, 9, ""));
                                        }
                                        n2 = 1;
                                    }
                                    catch (Exception ex2) {}
                                }
                            }
                        }
                        if (n6 > 3) {
                            n5 = Math.min(n4, n5 * 2);
                            if (ac != null && array.length != n5) {
                                array = new byte[n5];
                            }
                        }
                        else {
                            n5 = Math.max(n5 / 2, n3);
                            if (ac != null && array.length != n5) {
                                array = new byte[n5];
                            }
                        }
                        if (b2 > n3) {
                            try {
                                if (n10 > 0) {
                                    continue;
                                }
                                n10 = n9;
                                if (af == null) {
                                    continue;
                                }
                                af.a(new a6(af, 4, "".concat(String.valueOf(String.valueOf(100 * n7 / length)))));
                                af.a(new a6(af, 14, "".concat(String.valueOf(String.valueOf(100 * n7 / length)))));
                            }
                            catch (Exception ex3) {}
                        }
                    }
                    if (n < 0) {
                        int j = inputStream.available();
                        if (j > 0 && j != b2) {
                            while (j > 0) {
                                if (n2 == 0 && f(o) && System.currentTimeMillis() - currentTimeMillis > be && b2 > n3) {
                                    try {
                                        if (af != null) {
                                            af.a(new a6(af, 9, ""));
                                        }
                                        n2 = 1;
                                    }
                                    catch (Exception ex4) {}
                                }
                                j = inputStream.available();
                                final byte[] array2 = new byte[j];
                                final int read = inputStream.read(array2, 0, array2.length);
                                if (read > 0) {
                                    if (ac != null) {
                                        ac.b(array2, 0, read);
                                    }
                                    n7 += read;
                                }
                            }
                            i = 1;
                            b2 = n7;
                        }
                    }
                    else {
                        b2 = n7;
                    }
                    inputStream.close();
                }
            }
        }
        catch (Exception ex) {
            a(ex);
            if (dr()) {
                ji.io.h.d(s3, "Exception reading ...".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            }
        }
        finally {
            try {
                if (af != null) {
                    af.a(new a6(af, 4, ""));
                    af.a(new a6(af, 14, ""));
                }
            }
            catch (Exception ex5) {}
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex6) {}
            if (n2 != 0 && f(o)) {
                try {
                    if (af != null) {
                        af.a(new a6(af, 10, null));
                    }
                }
                catch (Exception ex7) {}
            }
        }
        if (dr()) {
            ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer("Connection: Retrieved ").append(b2).append(" of ").append(array.length).append(" bytes"))));
        }
        try {
            if (b2 < array.length && s2 == null && b) {
                final byte[] array3 = new byte[b2];
                System.arraycopy(array, 0, array3, 0, b2);
                array = array3;
            }
        }
        catch (Exception ex8) {}
        return array;
    }
    
    public static final byte[] a(final InputStream inputStream, final af af, final Object o, final String s, final String s2, final boolean b) throws Exception {
        int n = 0;
        final int be = ji.util.e.be();
        final long currentTimeMillis = System.currentTimeMillis();
        ac ac = null;
        final int available = inputStream.available();
        byte[] array;
        if (!b && s2 != null) {
            array = new byte[Math.min(available, 2048)];
        }
        else {
            array = new byte[available];
        }
        try {
            if (inputStream != null && array != null) {
                int i = 0;
                if (s2 != null) {
                    ac = new ac(s2, true, false, 0, false, o, true, s);
                }
                int n3;
                final int n2 = n3 = available;
                while (i < available) {
                    if (n == 0 && f(o) && System.currentTimeMillis() - currentTimeMillis > be) {
                        try {
                            if (af != null) {
                                af.a(new a6(af, 9, ""));
                            }
                            n = 1;
                        }
                        catch (Exception ex) {}
                    }
                    int n4;
                    if (!b && s2 != null) {
                        n4 = inputStream.read(array);
                        if (ac != null) {
                            ac.b(array, 0, n4);
                        }
                    }
                    else {
                        n4 = inputStream.read(array, i, available - i);
                    }
                    i += n4;
                    n3 -= n4;
                    try {
                        if (n3 > 0) {
                            continue;
                        }
                        n3 = n2;
                        if (af == null) {
                            continue;
                        }
                        af.a(new a6(af, 4, "".concat(String.valueOf(String.valueOf(100 * i / available)))));
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex3) {}
            try {
                if (af != null) {
                    af.a(new a6(af, 4, ""));
                }
            }
            catch (Exception ex4) {}
            if (n != 0 && f(o)) {
                try {
                    if (af != null) {
                        af.a(new a6(af, 10, null));
                    }
                }
                catch (Exception ex5) {}
            }
        }
        return array;
    }
    
    public static final void a(final Component component, final Image image) {
        if (image != null) {
            MediaTracker mediaTracker;
            if (component == null) {
                mediaTracker = new MediaTracker(new Frame());
            }
            else {
                mediaTracker = new MediaTracker(component);
            }
            try {
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                mediaTracker.removeImage(image, 0);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static final String bh(final String s) {
        if (by(s)) {
            return null;
        }
        String s2 = null;
        boolean b = false;
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) == '.') {
                b = true;
                break;
            }
            if (s.charAt(i) == File.separatorChar) {
                break;
            }
            if (s2 != null) {
                s2 = String.valueOf(String.valueOf(s.charAt(i))).concat(String.valueOf(String.valueOf(s2)));
            }
            else {
                s2 = new Character(s.charAt(i)).toString();
            }
        }
        if (!b && b()) {
            s2 = null;
        }
        if (s2 != null) {
            final char[] charArray = s2.toCharArray();
            String s3 = "".concat(String.valueOf(String.valueOf(charArray[0])));
            for (int j = 1; j < charArray.length; ++j) {
                if (charArray[j] >= 'a' && charArray[j] <= 'z') {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(charArray[j])));
                }
                else if (charArray[j] >= 'A' && charArray[j] <= 'Z') {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(charArray[j])));
                }
                else if (charArray[j] >= '0' && charArray[j] <= '9') {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(charArray[j])));
                }
                else {
                    if (charArray[j] != '-' && charArray[j] != '_') {
                        break;
                    }
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(charArray[j])));
                }
            }
            s2 = s3;
        }
        return s2;
    }
    
    private static final String db(final String s) {
        String s2 = "";
        try {
            if (s != null) {
                final char[] charArray = s.toCharArray();
                final char[] charArray2 = s.toUpperCase().toCharArray();
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '%') {
                        int n = (charArray2[++i] & '\u00ff') - '0';
                        int n2 = (charArray2[++i] & '\u00ff') - '0';
                        if (n > 9) {
                            n -= 7;
                        }
                        if (n2 > 9) {
                            n2 -= 7;
                        }
                        charArray[i] = (char)(n2 + n * 16);
                        s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                    else {
                        s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final String bi(final String s) {
        String s2 = b(b(db(s), "|/", ":/"), "|\\", ":\\");
        if (s2.toLowerCase().startsWith("file:")) {
            s2 = s2.substring(6);
        }
        Object o = null;
        String substring = s2;
        int i = s2.length() - 1;
        while (i >= 0) {
            if (s2.charAt(i) == '.') {
                if (o != null) {
                    substring = s2.substring(0, i);
                    break;
                }
                break;
            }
            else {
                if (o != null) {
                    o = String.valueOf(String.valueOf(s2.charAt(i))).concat(String.valueOf(String.valueOf(o)));
                }
                else {
                    o = new Character(s2.charAt(i)).toString();
                }
                --i;
            }
        }
        return substring;
    }
    
    public static final String ee() {
        return "V!RF";
    }
    
    public static final String h(final String s, final String s2) {
        if (s == null) {
            return null;
        }
        final String db = db(s);
        String concat = "";
        final String bu = bu(s2);
        for (int i = db.length() - 1; i >= 0; --i) {
            if (db.charAt(i) == '/') {
                break;
            }
            if (db.charAt(i) == '\\') {
                break;
            }
            if (db.charAt(i) == '?') {
                break;
            }
            if (db.charAt(i) == bu.charAt(0)) {
                break;
            }
            concat = String.valueOf(String.valueOf(db.charAt(i))).concat(String.valueOf(String.valueOf(concat)));
        }
        return concat;
    }
    
    public static final String i(final String s, final String s2) {
        final String db = db(s);
        String substring = "";
        final String bu = bu(s2);
        int i;
        for (i = db.length() - 1; i >= 0; --i) {
            if (db.charAt(i) == '/') {
                break;
            }
            if (db.charAt(i) == '\\') {
                break;
            }
            if (db.charAt(i) == bu.charAt(0)) {
                break;
            }
        }
        if (i > 0) {
            substring = db.substring(0, i + 1);
        }
        return substring;
    }
    
    public static final String j(String concat, final String s) {
        final String i = i(concat, s);
        final String b = b(k(concat, s), ".", "");
        final String bh = bh(concat);
        if (dq() && b.length() > 32) {
            concat = String.valueOf(String.valueOf(i)).concat(String.valueOf(String.valueOf(b(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b.substring(0, 32)))).append(".").append(bh))), "..", "."))));
        }
        return concat;
    }
    
    public static final String k(final String s, final String s2) {
        return a(s, true, s2);
    }
    
    public static final String a(final String s, final boolean b, final String s2) {
        String s4;
        final String s3 = s4 = db(s);
        final String bu = bu(s2);
        final String bh = bh(s3);
        if (!by(bh)) {
            s4 = "";
            final int n = s3.length() - 1 - bh.length();
            if (n >= 0) {
                for (int i = n; i >= 0; --i) {
                    if (i >= 0) {
                        if (s3.charAt(i) == '/') {
                            break;
                        }
                        if (s3.charAt(i) == '\\') {
                            break;
                        }
                        if (s3.charAt(i) == bu.charAt(0)) {
                            break;
                        }
                        s4 = String.valueOf(String.valueOf(s3.charAt(i))).concat(String.valueOf(String.valueOf(s4)));
                    }
                }
            }
        }
        if (b) {
            return s4;
        }
        if (!by(s4)) {
            return s4.substring(0, s4.length() - 1);
        }
        return s3;
    }
    
    public static final int j(final Color color) {
        return -16777216 + (color.getRed() << 16) + (color.getGreen() << 8) + color.getBlue();
    }
    
    public static String e(final long n) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" milliseconds (").append(n / 1000.0).append(" seconds)")));
    }
    
    public static final void ef() {
        if (ji.util.i.c(194)) {
            boolean b = true;
            if (ji.util.d.mw != 0 && System.currentTimeMillis() - ji.util.d.mw < 3000L) {
                b = false;
            }
            if (b && ji.util.d.mv == null) {
                (ji.util.d.mv = new Thread(new gp())).start();
            }
        }
    }
    
    public static final void d(final boolean b, final String s) {
        if (!ji.util.d.fo) {
            try {
                if (!ji.util.d.dh && eg() && (em() || av(s))) {
                    ji.util.d.dh = true;
                    if (!b) {
                        if (System.currentTimeMillis() - ji.util.d.dg > 5000) {}
                    }
                    ji.util.d.dg = System.currentTimeMillis();
                    ji.util.d.dh = false;
                }
            }
            catch (Exception ex) {
                ji.util.d.dh = false;
            }
        }
    }
    
    public static final void b4(final boolean di) {
        try {
            ji.util.d.di = di;
        }
        catch (Exception ex) {}
    }
    
    public static final boolean eg() {
        return ji.util.e.v != null || ji.util.d.di;
    }
    
    public static final boolean bj(final String s) {
        boolean b = false;
        try {
            final URL url = new URL(s);
            b = true;
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final void b5(final boolean ky) {
        ji.util.d.ky = ky;
    }
    
    public static final boolean eh() {
        return ji.util.d.ky || dm();
    }
    
    public static final boolean ei() {
        return ji.util.r.b();
    }
    
    public static final void b6(final boolean kz) {
        ji.util.d.kz = kz;
    }
    
    public static final boolean ej() {
        return ji.util.d.kz || dm();
    }
    
    public static final void a(final String s, final Component component) {
        boolean b = false;
        try {
            if (ji.util.e.av()) {
                if (ji.util.d.jf == null) {
                    ji.util.d.jf = a2("ji.v1event.jiJava2KeyHandler");
                    ((jiJava2KeyInterface)ji.util.d.jf).setKeyManager(ji.util.d.l);
                    ((jiJava2KeyInterface)ji.util.d.jf).addJavaKeyManager(component);
                }
                b = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!b) {
            component.addKeyListener(ji.util.d.l);
        }
        if (!ji.util.d.d3) {
            ji.util.d.l.a(ji.util.e.m(s));
            ji.util.d.d3 = true;
        }
    }
    
    public static final void b(final String s, final Component component) {
        boolean b = false;
        if (ji.util.e.av()) {
            if (ji.util.d.jf != null && ji.util.d.hk == 0) {
                ((jiJava2KeyInterface)ji.util.d.jf).removeJavaKeyManager();
            }
            b = true;
        }
        if (!b && component != null) {
            component.removeKeyListener(ji.util.d.l);
        }
    }
    
    public static final void a(final KeyListener keyListener, final Component component) {
        if (component instanceof jiPanel) {
            ((jiPanel)component).disableDefaultKeyListener();
        }
        else if (component instanceof bz) {
            ((bz)component).b();
        }
        ji.util.d.l.a(keyListener, component);
    }
    
    public static final void a(final KeyListener keyListener) {
        ji.util.d.l.a(keyListener);
    }
    
    public static final void a(final int n, final Component component) {
        ji.util.d.l.a(n, component);
    }
    
    public static final void b(final int n, final Component component) {
        ji.util.d.l.b(n, component);
    }
    
    public static final void b(final ad ad) {
        ji.util.d.l.a((Component)ad);
    }
    
    public static final void b7(final boolean b) {
        ji.util.d.l.a(b);
    }
    
    public static final void bk(final String kl) {
        ji.util.d.kl = kl;
    }
    
    public static final String ek() {
        return ji.util.d.kl;
    }
    
    public static final String a(final Component component) {
        String s = "";
        try {
            if (component != null) {
                if (ji.util.d.km == null) {
                    ji.util.d.km = new c("jiUtilParentId");
                }
                final Object d = ji.util.d.km.d("".concat(String.valueOf(String.valueOf(component.hashCode()))));
                if (d != null) {
                    return (String)d;
                }
                Component parent = component;
                if (parent instanceof ad) {
                    s = ((ad)parent).iu();
                    ji.util.d.km.a("".concat(String.valueOf(String.valueOf(component.hashCode()))), s);
                }
                else {
                    Block_8: {
                        while ((parent = parent.getParent()) != null) {
                            if (parent instanceof ad) {
                                break Block_8;
                            }
                        }
                        return s;
                    }
                    s = ((ad)parent).iu();
                    ji.util.d.km.a("".concat(String.valueOf(String.valueOf(component.hashCode()))), s);
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public static final boolean a(final Component component, final Component component2) {
        boolean b = false;
        try {
            if (component != null && component2 != null) {
                Component parent = component;
                if (parent.equals(component2)) {
                    b = true;
                }
                if (!b) {
                    while ((parent = parent.getParent()) != null) {
                        if (parent.equals(component2)) {
                            b = true;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            a(ex);
        }
        return b;
    }
    
    public static final Frame b(final Component component) {
        if (component != null) {
            Component parent = component;
            if (parent instanceof Frame) {
                return (Frame)parent;
            }
            while ((parent = parent.getParent()) != null) {
                if (parent instanceof Frame) {
                    return (Frame)parent;
                }
            }
        }
        return null;
    }
    
    public static final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        for (int i = n; i <= n2; i += n4 + 1) {
            graphics.drawLine(i, n3, Math.min(i + n4 - 1, n2), n3);
        }
    }
    
    public static final void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        for (int i = n; i <= n2; i += n4 + 1) {
            graphics.drawLine(n3, i, n3, Math.min(i + n4 - 1, n2));
        }
    }
    
    public static final void c(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        a(graphics, n, n2, n3, n4, 1);
    }
    
    public static final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        a(graphics, n, n + n3, n2, n5);
        a(graphics, n, n + n3, n2 + n4, n5);
        b(graphics, n2, n2 + n4, n, n5);
        b(graphics, n2, n2 + n4, n + n3, n5);
    }
    
    public static final int a(final int n, final int n2, final byte[] array, final int n3, final short[] array2) {
        int n4 = 0;
        short n5 = 0;
        final int length = array.length;
        int n6 = 0;
        int n7 = n3;
        int n8 = 7;
        byte b = array[n7++];
        try {
            for (int i = n; i < n2; ++i) {
                final int n9 = 0x1 & b >>> n8--;
                if (n8 < 0) {
                    n8 = 7;
                    if (n7 == length) {
                        break;
                    }
                    b = array[n7++];
                }
                if (n9 != n4) {
                    array2[n6++] = n5;
                    n4 = n9;
                    n5 = 1;
                }
                else {
                    ++n5;
                }
            }
            if (n5 > 0) {
                array2[n6++] = n5;
            }
        }
        catch (Exception ex) {}
        return n6;
    }
    
    public static final int a(final int n, final byte[] array, final short[] array2) {
        int n2 = 0;
        try {
            if (array[0] == 0) {
                array2[n2++] = 0;
            }
            byte b = array[0];
            short n3 = 1;
            for (int i = 1; i < n; ++i) {
                final byte b2 = array[i];
                if (b2 != b) {
                    array2[n2++] = n3;
                    b = b2;
                    n3 = 1;
                }
                else {
                    ++n3;
                }
            }
            if (n3 > 0) {
                array2[n2++] = n3;
            }
        }
        catch (Exception ex) {}
        return n2;
    }
    
    public static final String a(final String s, final String s2, final String s3, final boolean b, final boolean b2, final String s4, final int n, final char c, final boolean b3, final boolean b4, final b0 b5, final boolean b6, final int n2) {
        return a(s, s2, s3, b, b2, s4, n, c, b3, b4, b5, b6, n2, null);
    }
    
    public static final String a(final String s, final String s2, final String s3, final boolean b, final boolean b2, final String s4, final int n, final char c, final boolean b3, final boolean b4, final b0 b5, final boolean b6, final int n2, final Component component) {
        c9(s4);
        return ji.util.d.gq.a(s, s2, s3, component, b, b2, s4, n, c, true, b3, b4, b5, b6, n2, 0);
    }
    
    public static final int bl(final String s) {
        c9(s);
        return ji.util.d.gq.f();
    }
    
    public static final String a(final String s, final int n, final boolean[] array, final String s2) {
        c9(s2);
        return ji.util.d.gq.a(s, null, n, array, s2);
    }
    
    public static final String el() {
        if (em()) {
            return ji.util.d.f9;
        }
        return ji.util.d.f8;
    }
    
    public static final void l(final String f8, final String f9) {
        ji.util.d.f8 = f8;
        ji.util.d.f9 = f9;
        try {
            if (ji.util.d.f8 != null) {
                final int index = ji.util.d.f8.toLowerCase().indexOf("null");
                if (index >= 0) {
                    ji.util.d.f8 = ji.util.d.f8.substring(index + 4);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final String d(final Object o, final String s) {
        try {
            if (ji.util.i.c(285)) {
                return "HTML document fetch disabled by parameter";
            }
            final URL k = ji.util.e.k(s);
            if (k != null) {
                String s2 = String.valueOf(String.valueOf(k)).concat(": \n \n");
                final byte[] a = a(k, null, -1, o, s);
                if (a != null) {
                    s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(new String(a))));
                }
                return new String(s2);
            }
            return null;
        }
        catch (Exception ex) {
            a(ex);
            return ex.toString();
        }
    }
    
    public static final void a(final boolean b, final af af, final ah ah, final String s, final ad ad) {
        try {
            final String bt = bt(s);
            final String b2 = b(426, s);
            String s2;
            if (b) {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(428, s)))).append("\n").append(b(429, s))));
            }
            else {
                s2 = b(427, s);
            }
            ji.io.h.d(s, bt);
            ji.io.h.d(s, c(ad));
            a(b2, s2, null, bt, af, s, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static final String y(final String s, final String s2) {
        String s3 = "";
        if (ji.util.d.f8 != null && !ak(s)) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(ji.util.d.f8, ",");
                if (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (s3 != null) {
                        s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                    }
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Archive (").append(nextToken).append("): "))))));
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(a(new URL(ji.util.e.am(), nextToken), s, String.valueOf(String.valueOf(s2)).concat("/UtilGetArchSizes1")))));
                }
            }
            catch (Exception ex) {}
        }
        if (ji.util.d.f9 != null && ak(s)) {
            try {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(ji.util.d.f9, ",");
                if (stringTokenizer2.hasMoreTokens()) {
                    final String nextToken2 = stringTokenizer2.nextToken();
                    if (s3 != null) {
                        s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                    }
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Cabbase (").append(nextToken2).append("): "))))));
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(a(new URL(ji.util.e.am(), nextToken2), s, "UtilGetArchSizes2"))));
                }
            }
            catch (Exception ex2) {}
        }
        return s3;
    }
    
    public static final void a(final Component component, final af af, final ah ah, final String s) {
        if (!em() && !ji.util.d.jw) {
            ji.io.h.d(s, "");
            ji.io.h.d(s, "Options...");
            ji.io.h.d(s, "Annot = ".concat(String.valueOf(String.valueOf(cs()))));
            ji.io.h.d(s, "Annotation Management = ".concat(String.valueOf(String.valueOf(ji.util.i.c(145)))));
            ji.io.h.d(s, "Applet = ".concat(String.valueOf(String.valueOf(ji.util.d.an))));
            ji.io.h.d(s, "Applet Lifecycle = ".concat(String.valueOf(String.valueOf(ji.util.i.c(131)))));
            ji.io.h.d(s, "Burn = ".concat(String.valueOf(String.valueOf(ji.util.i.c(126)))));
            ji.io.h.d(s, "Cache = ".concat(String.valueOf(String.valueOf(ji.util.i.c(83)))));
            ji.io.h.d(s, "Decomp = ".concat(String.valueOf(String.valueOf(ji.util.i.c(89)))));
            ji.io.h.d(s, "Dir = ".concat(String.valueOf(String.valueOf(ji.util.i.c(4)))));
            ji.io.h.d(s, "Disabled Dialogs = ".concat(String.valueOf(String.valueOf(ji.util.i.c(82)))));
            ji.io.h.d(s, "Exception = ".concat(String.valueOf(String.valueOf(ji.util.i.c(137)))));
            ji.io.h.d(s, "Extended Cache = ".concat(String.valueOf(String.valueOf(ji.util.i.c(195)))));
            ji.io.h.d(s, "File = ".concat(String.valueOf(String.valueOf(ji.util.i.c(84)))));
            ji.io.h.d(s, "Filter = ".concat(String.valueOf(String.valueOf(ji.util.i.c(5)))));
            ji.io.h.d(s, "Finalize = ".concat(String.valueOf(String.valueOf(cz()))));
            ji.io.h.d(s, "Focus = ".concat(String.valueOf(String.valueOf(ji.util.i.c(102)))));
            ji.io.h.d(s, "Image Adjustment = ".concat(String.valueOf(String.valueOf(ji.util.i.c(61)))));
            ji.io.h.d(s, "Javacript = ".concat(String.valueOf(String.valueOf(ji.util.i.c(6)))));
            ji.io.h.d(s, "Keys = ".concat(String.valueOf(String.valueOf(ji.util.i.c(34)))));
            ji.io.h.d(s, "Net = ".concat(String.valueOf(String.valueOf(ji.util.i.c(79)))));
            ji.io.h.d(s, "Paint = ".concat(String.valueOf(String.valueOf(ji.util.i.c(81)))));
            ji.io.h.d(s, "Prefetch = ".concat(String.valueOf(String.valueOf(ji.util.i.c(80)))));
            ji.io.h.d(s, "Print = ".concat(String.valueOf(String.valueOf(ji.util.i.c(40)))));
            ji.io.h.d(s, "Pro = ".concat(String.valueOf(String.valueOf(ji.util.i.c(37)))));
            ji.io.h.d(s, "Quick = ".concat(String.valueOf(String.valueOf(ji.util.i.c(105)))));
            ji.io.h.d(s, "Scrollbar = ".concat(String.valueOf(String.valueOf(ji.util.i.c(168)))));
            ji.io.h.d(s, "Sec1 = ".concat(String.valueOf(String.valueOf(ji.util.i.c(57)))));
            ji.io.h.d(s, "Shutdown Dialog = ".concat(String.valueOf(String.valueOf(ji.util.i.c(124)))));
            ji.io.h.d(s, "Source = ".concat(String.valueOf(String.valueOf(ji.util.i.c(95)))));
            ji.io.h.d(s, "Sync = ".concat(String.valueOf(String.valueOf(ji.util.i.c(36)))));
            ji.io.h.d(s, "Trace = ".concat(String.valueOf(String.valueOf(cy()))));
            ji.io.h.d(s, "Time = ".concat(String.valueOf(String.valueOf(dv()))));
            a(component, af, ah, s, false);
            if (eg()) {
                ji.io.h.d(s, "HTML = ".concat(String.valueOf(String.valueOf(d(component, s)))));
            }
            ji.util.d.jw = true;
        }
    }
    
    public static final void a(final Component component, final af af, final ah ah, final String s, final boolean b) {
        try {
            final String s2 = "";
            ji.io.h.d(s, "");
            if (ji.res.aa.a(s) == null) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("V1 Version: ").append(ji.res.aa.e()).append(" ").append(s.a))));
            }
            ji.io.h.d(s, y(s, "UtilshowProps"));
            ji.io.h.d(s, "Platform: ".concat(String.valueOf(String.valueOf(a9(s)))));
            if (ad() != null) {
                ji.io.h.d(s, "Installation Path: ".concat(String.valueOf(String.valueOf(ad()))));
            }
            ji.io.h.d(s, "Character encoding: ".concat(String.valueOf(String.valueOf(ji.util.d.hu))));
            final Properties b2 = ji.sec.f.b(s);
            final Enumeration<?> propertyNames = b2.propertyNames();
            String s3 = "";
            int n = 1;
            while (propertyNames.hasMoreElements()) {
                final String string = propertyNames.nextElement().toString();
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(string).append(" = ").append(b2.getProperty(string))));
                if (--n == 0) {
                    n = n;
                    s3 = String.valueOf(String.valueOf(value)).concat("\n");
                }
                else {
                    s3 = String.valueOf(String.valueOf(value)).concat(",   ");
                }
            }
            if (!dw()) {
                ji.io.h.d(s, s3);
            }
            String s4 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s3)));
            if (b) {
                try {
                    s4 = String.valueOf(String.valueOf(s4)).concat("\n");
                    s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(ji.font.j.a(component, af, s))));
                }
                catch (Exception ex2) {}
            }
            String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("\n\nPlatform: ").append(a9(s)).append(", default encoding: ").append(bm(s)).append(", using: ").append(ji.util.d.hu))))));
        }
        catch (Exception ex) {
            ji.io.h.d(s, ex.toString());
        }
    }
    
    public static final String bm(final String s) {
        try {
            if (ji.util.d.dc) {
                return null;
            }
            if (!ji.util.d.hw) {
                ji.util.d.hv = new u("", s).l();
                ji.util.d.hw = true;
            }
        }
        catch (Exception ex) {}
        return ji.util.d.hv;
    }
    
    public static final String bn(final String s) {
        try {
            if (ji.util.d.dc) {
                return null;
            }
            if (!ji.util.d.hw) {
                if (ji.util.d.hu != null) {
                    ji.util.d.hv = ji.util.d.hu;
                }
                else {
                    ji.util.d.hv = new u("", s).l();
                }
                ji.util.d.hw = true;
            }
        }
        catch (Exception ex) {}
        return ji.util.d.hv;
    }
    
    public static final String b(final Object o) {
        if (o != null) {
            return b(o.toString(), "<equals>", "=");
        }
        return "";
    }
    
    public static final void a(final ad ad, final dx dx, final af af, final String s, final String s2, final boolean b, final int n, final int n2) {
        String s3 = b(ad, dx, af, s, s2, b, n, n2);
        if (dx.cd != null) {
            s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("\n\n------------------------------------------- = \n\n").append(b(ad, dx.cd, af, s, s2, b, 0, 0))));
        }
        String value = s;
        if (by(value)) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.ay.a()))).append(" ").append(b(246, s2))));
        }
        a(value, s3, ad, null, af, s2, "=", 0, true, true);
    }
    
    public static final String b(final ad ad, final dx dx, final af af, final String s, final String s2, final boolean b, final int n, final int n2) {
        String s3 = "";
        if (dx != null) {
            try {
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.ay.a()))).append("  = ").append(bg(b(100, s2))).append(" ").append(ji.res.aa.e()).append("\n")));
                if (!by(dx.t)) {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.a(1304, s2)))).append(" = ").append(bg(b(100, s2))).append(" ").append(dx.t).append("\n"))))));
                }
                s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                if (!by(s)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bg(b(270, s2))).append(" = ").append(s).append("\n\n")));
                }
                if (b) {
                    final Hashtable bl = dx.bl;
                    if (bl != null) {
                        final Enumeration<Object> keys = bl.keys();
                        while (keys.hasMoreElements()) {
                            final String string = keys.nextElement().toString();
                            s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(string).append(" = ").append(dc(bl.get(string).toString())).append("\n")));
                        }
                        s3 = String.valueOf(String.valueOf(s3)).concat("\n\n");
                    }
                }
                if (dx.g != null) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(669, s2)).append(" = ").append(dc(c((Object)dx.g))).append("\n")));
                }
                else if (dx.f != null) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(669, s2)).append(" = ").append(dc(c((Object)dx.f))).append("\n")));
                }
                if (dx.s > 0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1201, s2)).append(" = ").append(a(dx.s, s2)).append("\n")));
                }
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(670, s2)).append(" = ").append(a(dx.i, s2)).append("\n")));
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(671, s2)).append(" = ").append(dx.a7).append("\n")));
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(672, s2)).append(" = ").append(dx.l).append("\n")));
                if (n2 > 1) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(302, s2)).append(" = ").append(n).append("\n")));
                }
                if (n2 > 0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(778, s2)).append(" = ").append(n2).append("\n")));
                }
                else {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(778, s2)).append(" = ").append(dx.u).append("\n")));
                }
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("\n").append(b(677, s2)).append(" = ").append(dx.am).append("\n")));
                final o6 k = dx.k();
                final dc j = dx.j();
                final o6 l = dx.l();
                String s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(673, s2)))).append(" = ").append(j.a).append(" ").append(b(675, s2))));
                String s5 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(674, s2)))).append(" = ").append(j.b).append(" ").append(b(675, s2))));
                if (ji.util.i.c(170) && bn()) {
                    if (k.b() > 0.0) {
                        if (ji.util.i.c(171)) {
                            s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(b("".concat(String.valueOf(String.valueOf(l.b()))), 2)).append(" ").append(b(1186, s2).toLowerCase()).append(")"))))));
                        }
                        else {
                            s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(b("".concat(String.valueOf(String.valueOf(2.54 * l.b()))), 2)).append(" ").append(b(1188, s2).toLowerCase()).append(")"))))));
                        }
                    }
                    if (k.a() > 0.0) {
                        if (ji.util.i.c(171)) {
                            s5 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(b("".concat(String.valueOf(String.valueOf(l.a()))), 2)).append(" ").append(b(1186, s2).toLowerCase()).append(")"))))));
                        }
                        else {
                            s5 = String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(b("".concat(String.valueOf(String.valueOf(2.54 * l.a()))), 2)).append(" ").append(b(1188, s2).toLowerCase()).append(")"))))));
                        }
                    }
                }
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(s4).append("\n")));
                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(s5).append("\n")));
                if (bn()) {
                    if (dx.ah && dx.u() != dx.ac) {
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(s.a(1308, s2, new Object[] { i("".concat(String.valueOf(String.valueOf(dx.u())))), b("".concat(String.valueOf(String.valueOf(k.b()))), 2) })).append("\n")));
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(s.a(1309, s2, new Object[] { i("".concat(String.valueOf(String.valueOf(dx.u())))), b("".concat(String.valueOf(String.valueOf(k.a()))), 2) })).append("\n")));
                    }
                    else {
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("X ").append(b(676, s2)).append(" = ").append(b("".concat(String.valueOf(String.valueOf(k.b()))), 2)).append(" dpi\n")));
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("Y ").append(b(676, s2)).append(" = ").append(b("".concat(String.valueOf(String.valueOf(k.a()))), 2)).append(" dpi\n")));
                    }
                }
                if (dx.cw > 0 || dx.cx > 0) {
                    s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1221, s2)).append(" = ").append(ad.ie()).append("\n")));
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1222, s2)).append(" = ").append(ad.if()).append("\n")));
                    s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1217, s2)).append(" = ").append(dx.cy).append("\n")));
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1219, s2)).append(" = ").append(dx.c0).append("\n")));
                    s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1218, s2)).append(" = ").append(dx.cz).append("\n")));
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1220, s2)).append(" = ").append(dx.c1).append("\n")));
                }
                int n3 = 0;
                if (!by(dx.a8)) {
                    try {
                        final StringTokenizer stringTokenizer = new StringTokenizer(dx.a8, ":");
                        if (stringTokenizer.countTokens() > 0) {
                            s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                            n3 = 1;
                            while (stringTokenizer.hasMoreTokens()) {
                                s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bc(stringTokenizer.nextToken())).append("\n")));
                            }
                        }
                    }
                    catch (Exception ex3) {}
                }
                if (dx.cm > 0.0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(723, s2)).append(" = ").append(a((long)(int)dx.cm, s2)).append("/").append(b(663, s2)).append("\n")));
                }
                try {
                    if (dx.ck > 0) {
                        final double n4 = dx.ck / 1000.0;
                        String s6 = "".concat(String.valueOf(String.valueOf(n4)));
                        final int index = s6.indexOf(".");
                        if (index > 0) {
                            s6 = s6.substring(0, Math.min(index + 4, s6.length()));
                        }
                        String s7;
                        if (n4 == 1.0) {
                            s7 = String.valueOf(String.valueOf(s6)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(663, s2)))))));
                        }
                        else {
                            s7 = String.valueOf(String.valueOf(s6)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(664, s2)))))));
                        }
                        if (n3 == 0) {
                            s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                        }
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(678, s2)).append(" = ").append(s7).append("\n")));
                        n3 = 1;
                    }
                    if (dx.cl > 0) {
                        final double n5 = dx.cl / 1000.0;
                        String s8 = "".concat(String.valueOf(String.valueOf(n5)));
                        final int index2 = s8.indexOf(".");
                        if (index2 > 0) {
                            s8 = s8.substring(0, Math.min(index2 + 4, s8.length()));
                        }
                        String s9;
                        if (n5 == 1.0) {
                            s9 = String.valueOf(String.valueOf(s8)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(663, s2)))))));
                        }
                        else {
                            s9 = String.valueOf(String.valueOf(s8)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(664, s2)))))));
                        }
                        if (n3 == 0) {
                            s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                        }
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(679, s2)).append(" = ").append(s9).append("\n")));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (!by(dx.a8)) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1178, s2)).append(" = ").append(a(y(), s2)).append(" ").append(b(312, s2)).append("\n")));
                }
                s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                if (ji.util.d.mt.q > 0 && ji.util.d.mt.w == 0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1179, s2)).append(" = ").append(d(ji.util.d.mt.q, s2)).append("\n")));
                }
                if (ji.util.d.mt.w > 0) {
                    s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1180, s2)).append(" = ").append(d(ji.util.d.mt.w, s2)).append("\n")));
                }
                if (ji.util.d.mt.y > 0 || ji.util.d.mt.aa > 0) {
                    if (ji.util.d.mt.y == ji.util.d.mt.aa) {
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1181, s2)).append(" = ").append(d(ji.util.d.mt.y, s2)).append("\n")));
                    }
                    else {
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1182, s2)).append(" = ").append(d(ji.util.d.mt.y, s2)).append("\n")));
                        s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(b(1183, s2)).append(" = ").append(d(ji.util.d.mt.aa, s2)).append("\n")));
                    }
                    s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                }
                else {
                    s3 = String.valueOf(String.valueOf(s3)).concat("\n");
                }
                final Hashtable bk = dx.bk;
                if (bk != null) {
                    final Enumeration<Object> keys2 = bk.keys();
                    final String[] array = { b(835, s2).toLowerCase(), b(836, s2).toLowerCase(), b(824, s2).toLowerCase(), b(825, s2).toLowerCase(), b(874, s2).toLowerCase(), b(853, s2).toLowerCase() };
                    while (keys2.hasMoreElements()) {
                        final String string2 = keys2.nextElement().toString();
                        boolean b2 = false;
                        final String lowerCase = string2.toLowerCase();
                        for (int i = 0; i < array.length; ++i) {
                            if (lowerCase.equals(array[i])) {
                                b2 = true;
                                break;
                            }
                        }
                        if (!b2) {
                            s3 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(string2).append(" = ").append(bk.get(string2)).append("\n")));
                        }
                    }
                }
            }
            catch (Exception ex2) {
                ji.io.h.d(s2, ex2.toString());
            }
        }
        return s3;
    }
    
    private static final String dc(final String s) {
        return b(s, "=", "<EQ>");
    }
    
    private static final String d(final long n, final String s) {
        String s2 = "0";
        if (n > 0) {
            final double n2 = n / 1000.0;
            String s3 = "".concat(String.valueOf(String.valueOf(n2)));
            final int index = s3.indexOf(".");
            if (index > 0) {
                s3 = s3.substring(0, Math.min(index + 4, s3.length()));
            }
            if (n2 == 1.0) {
                s2 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(663, s)))))));
            }
            else {
                s2 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(b(664, s)))))));
            }
        }
        return s2;
    }
    
    public static String c(final Object o) {
        return b(b(o.toString(), "/\\", "/"), "\\", "/");
    }
    
    private static final boolean dd(final String s) {
        boolean en = false;
        if (!ji.util.d.da) {
            ji.util.d.da = true;
            if (ak(s)) {
                try {
                    ji.util.d.ek = 0;
                    ji.util.d.ej = 0;
                    final Properties a = ji.util.cm.a();
                    if (a != null) {
                        en = true;
                        final String property = a.getProperty("MajorVersion");
                        final String property2 = a.getProperty("BuildIncrement");
                        if (property != null) {
                            ji.util.d.ek = c(property, 0);
                            if (property2 != null) {
                                ji.util.d.ej = c(property2, 0);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
            }
            ji.util.d.en = en;
        }
        else {
            en = ji.util.d.en;
        }
        return en;
    }
    
    public static final boolean em() {
        return ji.util.d.en;
    }
    
    public static final boolean bo(final String s) {
        boolean b = true;
        if (em() && ji.util.d.ek < 4) {
            b = false;
        }
        return b;
    }
    
    public static final boolean bp(final String s) {
        boolean b = true;
        if (ji.util.d.di) {
            try {
                if (em() && ji.util.d.ek < 4) {
                    b = false;
                    a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.ay.a()))).append(b(92, s)).append("\n").append(b(93, s)).append("\n").append(b(94, s)).append("\n").append(b(95, s)))), (af)null, s);
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public static boolean m(final String s, final String s2) {
        if (em() && ji.util.d.ej < 3167) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(s)).concat(" disabled for MS JVM builds earlier than 3167 (JNI limitations)"));
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer("This MS JVM is release ").append(ji.util.d.ek).append(" build ").append(ji.util.d.ej))));
        }
        return true;
    }
    
    public static final void a(final Thread thread) {
    }
    
    public static final void b(final Thread thread) {
        try {
            thread.setPriority(1);
        }
        catch (Exception ex) {}
    }
    
    public static final void aj(final int eo) {
        ji.util.d.eo = eo;
    }
    
    public static final int en() {
        return ji.util.d.eo;
    }
    
    public static final void ak(final int ep) {
        ji.util.d.ep = ep;
    }
    
    public static final int eo() {
        return ji.util.d.ep;
    }
    
    public static URL a(final String s, String lowerCase, final URL url, final boolean b, final String s2) throws Exception {
        String s3 = s;
        lowerCase = lowerCase.toLowerCase();
        final int index = s.toLowerCase().indexOf(lowerCase);
        if (index != -1) {
            int n = index + lowerCase.length();
            if (s.length() > n && s.charAt(n) == '/') {
                ++n;
            }
            s3 = b(url, s.substring(n), s2).toString();
            if (b) {
                if (!s3.endsWith("/")) {
                    s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf('/')));
                }
            }
            else if (s3.endsWith("/")) {
                s3 = s3.substring(0, s3.length() - 1);
            }
        }
        return new URL(s3);
    }
    
    public static URL a(final URL url, final String s) {
        try {
            if (ji.util.i.c(79)) {
                ji.io.h.d(s, "Retrieving Parent URL from: ".concat(String.valueOf(String.valueOf(url.toExternalForm()))));
            }
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(url.getProtocol()))).append("://").append(b(url, s))));
            String s2 = url.getFile();
            s2.length();
            final int index = s2.indexOf(63);
            if (index > -1) {
                s2 = s2.substring(0, index - 1);
            }
            if (s2.charAt(s2.length() - 1) == '/') {
                s2 = s2.substring(0, s2.length() - 1);
            }
            final String concat = String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(s2.substring(0, s2.lastIndexOf(47) + 1))));
            if (ji.util.i.c(79)) {
                ji.io.h.d(s, "Parent URL is: ".concat(String.valueOf(String.valueOf(concat))));
            }
            return new URL(concat);
        }
        catch (Exception ex) {
            a(ex);
            return null;
        }
    }
    
    public static final String b(final URL url, final String s) {
        String s2 = url.getHost();
        final int port = url.getPort();
        if (port > -1) {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(":".concat(String.valueOf(String.valueOf(port))))));
        }
        return s2;
    }
    
    public static Object b(final URL url, final String s, final String s2) {
        String s3 = bc(s);
        boolean b = false;
        if (s3.toLowerCase().startsWith("file:")) {
            final char[] charArray = s3.toCharArray();
            final int n = s3.indexOf(":") + 1;
            int n2 = 0;
            int i;
            for (i = n; i < charArray.length; ++i) {
                if (charArray[i] != '\\' && charArray[i] != '/') {
                    break;
                }
                if (charArray[i] == '\\' && n2 != 0) {
                    b = true;
                    break;
                }
                if (charArray[i] == '/') {
                    n2 = 1;
                }
            }
            if (!s3.substring(0, i).toLowerCase().equals("file:///")) {
                s3 = "file:///".concat(String.valueOf(String.valueOf(s3.substring(i))));
            }
        }
        URL url2 = a(url, s3, b);
        if (url2 == null) {
            return s3;
        }
        if ((av(s2) || ji.util.e.av()) && !b && s3.indexOf(" ") >= 0) {
            url2 = a(url, b(s3, " ", "%20"), b);
        }
        return url2;
    }
    
    public static URL a(final URL url, final String s, final boolean b) {
        String s2 = bc(s);
        URL url2 = null;
        try {
            if (!b) {
                s2 = b(s2, "\\\\", "/");
            }
            try {
                url2 = new URL(s2);
            }
            catch (Exception ex) {}
            if (url2 == null) {
                url2 = new URL(url, s2);
            }
        }
        catch (Exception ex2) {}
        return url2;
    }
    
    public static final Color k(final Color color) {
        try {
            return new Color(Math.min((int)(color.getRed() * 1.15), 255), Math.min((int)(color.getGreen() * 1.15), 255), Math.min((int)(color.getBlue() * 1.15), 255));
        }
        catch (Exception ex) {
            return color.brighter();
        }
    }
    
    public static final void b8(final boolean gv) {
        ji.util.d.gv = gv;
    }
    
    public static final boolean ep() {
        return ji.util.d.gv;
    }
    
    public static void a(final Applet applet, final String s) {
        try {
            if (applet != null) {
                if (s != null) {
                    applet.showStatus(s);
                }
                else {
                    applet.showStatus("");
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final String bq(final String s) {
        String s2 = s;
        try {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == ' ') {
                    charArray[i] = '+';
                }
            }
            s2 = new String(charArray);
        }
        catch (Exception ex) {
            b(ex);
        }
        return s2;
    }
    
    private static final String de(final String s) {
        String s2 = s;
        try {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                if (charArray[i] == ' ') {
                    charArray[i] = '+';
                }
            }
            s2 = new String(charArray);
        }
        catch (Exception ex) {
            b(ex);
        }
        return s2;
    }
    
    public static final String a(final String s, final Component component, final af af, final boolean b, final String s2) throws Exception {
        String concat = null;
        try {
            String de;
            if (b) {
                de = de(s);
            }
            else {
                de = s;
            }
            if (de != null) {
                if (dr()) {
                    ji.io.h.d(s2, "HTTP: Issue ".concat(String.valueOf(String.valueOf(s))));
                }
                final URLConnection a = a(new URL(de), false, s2);
                final int max = Math.max(a(a, false, s2), 1);
                try {
                    if (max > 0) {
                        final InputStream inputStream = a.getInputStream();
                        final byte[] array = { 0 };
                        concat = "";
                        for (int i = inputStream.read(array); i >= 0; i = inputStream.read(array)) {
                            concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(new String(array))));
                        }
                        inputStream.close();
                    }
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            a("HTTP problem", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ex.toString()))).append(": ").append(s).append("\n\nPlease check this URL is valid"))), component, 60, null, af, s2);
        }
        return concat;
    }
    
    public static final void eq() {
        ji.util.d.bf = "";
        ji.util.d.be = 0L;
        ji.util.d.bg = "";
        ji.util.d.bh = 0L;
        ji.util.d.bj = 0L;
        ji.util.d.bi = "";
        ji.util.d.bk = 0L;
        ji.util.d.bl = 0L;
        ji.util.d.bd = "";
        ji.util.d.bm = 0;
        ji.util.d.bn = 0;
        ji.util.d.bo = 0;
        ji.util.d.bp = 0;
        ji.util.d.bq = 0;
        ji.util.d.br = 0;
        ji.util.d.bs = 0;
        ji.util.d.bw = 0;
        ji.util.d.bx = 0;
        ji.util.d.by = 0;
        ji.util.d.bz = 0;
        ji.util.d.b0 = 0;
        ji.util.d.b1 = 0;
        ji.util.d.b2 = 0;
        ji.util.d.b3 = 0;
        ji.util.d.b4 = 0;
        ji.util.d.b5 = 0;
        ji.util.d.cs = 0;
        ji.util.d.b6 = 0;
        ji.util.d.b7 = 0;
        ji.util.d.b8 = 0;
        ji.util.d.cb = 0;
        ji.util.d.b9 = 0;
        ji.util.d.ca = 0;
        ji.util.d.cc = 0;
        ji.util.d.cd = 0;
        ji.util.d.ce = 0;
        ji.util.d.cf = 0;
        ji.util.d.cg = 0;
        ji.util.d.ch = 0;
        ji.util.d.ci = 0;
        ji.util.d.cj = 0;
        ji.util.d.ck = 0;
        ji.util.d.cl = 0;
        ji.util.d.cn = 0;
        ji.util.d.co = 0;
        ji.util.d.cp = 0;
        ji.util.d.cq = 0;
        ji.util.d.cr = 0;
        ji.util.d.ct = 0;
        ji.util.d.cu = 0;
        ji.util.d.cv = 0;
        ji.util.d.cw = 0;
        ji.util.d.cx = 0;
        ji.util.d.cy = 0;
        ji.util.d.c0 = 0;
    }
    
    public static final String br(final String s) {
        return d(s, true);
    }
    
    public static final String d(final String s, final boolean b) {
        String s2 = s;
        try {
            if (s != null) {
                String s3 = "";
                final char[] charArray = s.toCharArray();
                if (b) {
                    for (int i = 0; i < charArray.length; ++i) {
                        if (charArray[i] == '.' || b(charArray[i])) {
                            s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf("".concat(String.valueOf(String.valueOf(charArray[i]))))));
                        }
                    }
                }
                else {
                    for (int j = 0; j < charArray.length; ++j) {
                        if (charArray[j] == '.' || b(charArray[j]) || a(charArray[j]) || charArray[j] == '_' || charArray[j] == '~') {
                            s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf("".concat(String.valueOf(String.valueOf(charArray[j]))))));
                        }
                    }
                }
                s2 = s3;
            }
        }
        catch (Exception ex) {}
        return s2;
    }
    
    public static final boolean a(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public static final boolean b(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    public static final boolean bs(final String s) {
        boolean b = true;
        try {
            if (ak(s) && ay(s)) {
                b = false;
                bj(false);
                a(ji.res.ay.a(), String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(411, s))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(412, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(e("platNoMSMacOS3", s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(413, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(414, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(415, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(416, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(417, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b(418, s))).concat("\n")))))).concat(String.valueOf(String.valueOf(b(419, s)))), null, 40, null, null, s);
                while (dg()) {
                    b(200, 36, s);
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final String bt(final String s) {
        String s2 = "";
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("T: ").append(ji.res.ay.a()).append("\n"))))));
        }
        catch (Exception ex) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("V: ").append(ji.res.aa.e()).append("\n"))))));
        }
        catch (Exception ex2) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("C: ").append(ji.util.e.am()).append("\n"))))));
        }
        catch (Exception ex3) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("D: ").append(ji.util.e.k(s)).append("\n"))))));
        }
        catch (Exception ex4) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("L1: ").append(Locale.getDefault()).append("\n"))))));
        }
        catch (Exception ex5) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("L2: ").append(s.c()).append("\n"))))));
        }
        catch (Exception ex6) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("L3: ").append(s.a()).append("\n"))))));
        }
        catch (Exception ex7) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("L4: ").append(ji.util.d.h1).append("\n"))))));
        }
        catch (Exception ex8) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("L5: ").append(s.b()).append("\n"))))));
        }
        catch (Exception ex9) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("E: ").append(ji.util.d.hu).append("\n"))))));
        }
        catch (Exception ex10) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("I: ").append(ji.util.cn.a(ji.util.e.am(), eg(), s)).append("\n"))))));
        }
        catch (Exception ex11) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("01: ").append(ji.sec.f.a("os.name", s)).append("\n"))))));
        }
        catch (Exception ex12) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("02: ").append(ji.sec.f.a("os.arch", s)).append("\n"))))));
        }
        catch (Exception ex13) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("03: ").append(ji.sec.f.a("os.version", s)).append("\n"))))));
        }
        catch (Exception ex14) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("04: ").append(ji.sec.f.a("java.vendor", s)).append("\n"))))));
        }
        catch (Exception ex15) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("05: ").append(ji.sec.f.a("java.vm.vendor", s)).append("\n"))))));
        }
        catch (Exception ex16) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("06: ").append(ji.sec.f.a("java.specification.name", s)).append("\n"))))));
        }
        catch (Exception ex17) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("07: ").append(ji.sec.f.a("java.version", s)).append("\n"))))));
        }
        catch (Exception ex18) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("08: ").append(ji.sec.f.a("java.vm.version", s)).append("\n"))))));
        }
        catch (Exception ex19) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("09: ").append(ji.sec.f.a("java.vm.specification.version", s)).append("\n"))))));
        }
        catch (Exception ex20) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("10: ").append(ji.sec.f.a("java.vm.name", s)).append("\n"))))));
        }
        catch (Exception ex21) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("11: ").append(ji.sec.f.a("java.vm.info", s)).append("\n"))))));
        }
        catch (Exception ex22) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("12: ").append(ji.sec.f.a("java.class.version", s)).append("\n"))))));
        }
        catch (Exception ex23) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("13: ").append(ji.sec.f.a("java.compiler", s)).append("\n"))))));
        }
        catch (Exception ex24) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("14: ").append(ji.sec.f.a("browser", s)).append("\n"))))));
        }
        catch (Exception ex25) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("15: ").append(ji.sec.f.a("awt.toolkit", s)).append("\n"))))));
        }
        catch (Exception ex26) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("16: ").append(ji.sec.f.a("user.timezone", s)).append("\n"))))));
        }
        catch (Exception ex27) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("17: ").append(ji.sec.f.a("user.region", s)).append("\n"))))));
        }
        catch (Exception ex28) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("18: ").append(c((Object)null, s)).append("\n"))))));
        }
        catch (Exception ex29) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("19: ").append(a9(s)).append("\n"))))));
        }
        catch (Exception ex30) {}
        if (em()) {
            try {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("20: ").append(ji.util.d.ek).append(".").append(ji.util.d.ej).append("\n"))))));
            }
            catch (Exception ex31) {}
        }
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("\n").append(y(s, "UtilGetSystemInfo")).append("\n"))))));
        }
        catch (Exception ex32) {}
        try {
            s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("\nHTML:\n\n").append(d((Object)null, s)).append("\n"))))));
        }
        catch (Exception ex33) {}
        return s2;
    }
    
    public static final String c(final ad ad) {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append(ji.util.i.a());
        sb.append(ad.ky());
        sb.append("\n");
        return sb.toString();
    }
    
    public static final String b(final String s, final String s2, final String s3) {
        return ji.util.t.a(s, s2, s3);
    }
    
    public static final String d(final String s, final int n) {
        long n2 = -1L;
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            n2 += '\u00ff' - charArray[i] - '\u0001';
        }
        final String concat = "00000000000000000000".concat(String.valueOf(String.valueOf(n2)));
        return concat.substring(concat.length() - n, concat.length());
    }
    
    public static final String bu(final String s) {
        try {
            if (ji.util.d.e2 == null) {
                ji.util.d.e2 = ji.sec.f.a("file.separator", s);
            }
        }
        catch (Exception ex) {
            ji.util.d.e2 = "/";
        }
        return b(ji.util.d.e2, "\\", "/");
    }
    
    public static final String n(final String s, final String s2) {
        boolean b = false;
        int index = s.indexOf("file:");
        final String bu = bu(s2);
        String s3 = s;
        if (index >= 0) {
            try {
                final char[] charArray = s.toCharArray();
                index += 5;
                for (int n = index; n < charArray.length && charArray[n] == '/'; ++n) {
                    ++index;
                }
                s3 = s3.substring(index, s3.length());
                b = true;
            }
            catch (Exception ex) {}
        }
        final int index2 = s3.indexOf("localhost/");
        if (index2 >= 0) {
            try {
                s3 = s3.substring(index2 + 10, s3.length());
                b = true;
            }
            catch (Exception ex2) {}
        }
        final int index3 = s3.indexOf("localhost".concat(String.valueOf(String.valueOf(bu))));
        if (index3 >= 0) {
            try {
                s3 = s3.substring(index3 + 10, s3.length());
                b = true;
            }
            catch (Exception ex3) {}
        }
        if (b) {
            if (s3.indexOf("|") >= 0) {
                try {
                    final char[] charArray2 = s3.toCharArray();
                    String s4 = "";
                    for (int i = 0; i < s3.length(); ++i) {
                        if (charArray2[i] == '|') {
                            s4 = String.valueOf(String.valueOf(s4)).concat(":");
                        }
                        else {
                            s4 = String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(charArray2[i])));
                        }
                    }
                    s3 = s4;
                }
                catch (Exception ex4) {}
            }
            if (!s3.startsWith("\\\\")) {
                if (s3.startsWith("/") || s3.startsWith("\\") || s3.startsWith(bu)) {
                    s3 = s3.substring(1, s3.length());
                }
            }
        }
        if (b) {
            return s3;
        }
        return null;
    }
    
    public static void a(final Thread thread, final String s) throws Exception {
        if (ji.util.d.ak) {
            ji.io.h.d(s, "joinA1..");
        }
        ea();
        thread.join();
        if (ji.util.d.ak) {
            ji.io.h.d(s, "joinAB..");
        }
    }
    
    public static void b(final Thread thread, final String s) throws Exception {
        if (ji.util.e.av()) {
            if (ji.util.d.ak) {
                ji.io.h.d(s, "compatibleJoinA1..");
            }
            ea();
            thread.join();
            if (ji.util.d.ak) {
                ji.io.h.d(s, "compatibleJoinAB..");
            }
        }
        else {
            while (thread.isAlive() && !c3()) {
                b(100, 42, s);
            }
        }
    }
    
    public static void c(final Thread thread, final String s) {
        if (thread != null) {
            try {
                if (ji.util.d.ak) {
                    ji.io.h.d(s, "join1..");
                }
                while (thread.isAlive()) {
                    b(10, 10, s);
                }
                a(thread, s);
            }
            catch (Exception ex) {}
        }
    }
    
    public static final void b9(final boolean v) {
        ji.util.d.v = v;
    }
    
    public static final boolean er() {
        return ji.util.d.v;
    }
    
    public static final String o(final String s, final String s2) {
        String a = s;
        try {
            a = new u(s, s2).a();
        }
        catch (Exception ex) {}
        return a;
    }
    
    public static final String es() {
        return ji.util.d.fy;
    }
    
    public static final boolean et() {
        return ji.util.d.f0;
    }
    
    public static final void eu() {
        synchronized (ji.util.d.mz) {
            try {
                ex();
                System.out.flush();
                throw new Exception("trace");
            }
            catch (Exception ex) {
                ex.printStackTrace(System.out);
            }
            // monitorexit(d.mz)
        }
    }
    
    public static final byte[] a(final byte[] array, final String s, final boolean b, final y y, final String s2) throws b6 {
        byte[] array2;
        if (s.toLowerCase().startsWith(cu(s2)) && (s.toLowerCase().endsWith(".v1") || s.toLowerCase().endsWith(".txt"))) {
            array2 = b(array, s, true, y, s2);
        }
        else {
            array2 = b(array, s, b, y, s2);
        }
        return array2;
    }
    
    private static final int a(final int n, final byte[] array) {
        return (array[n] & 0xFF) << 24 | (array[n + 1] & 0xFF) << 16 | (array[n + 2] & 0xFF) << 8 | (array[n + 3] & 0xFF);
    }
    
    public static void bv(final String hu) {
        ji.util.d.h9 = true;
        ji.util.d.hu = hu;
    }
    
    public static void bw(final String hu) {
        if (!ji.util.d.h9) {
            ji.util.d.hu = hu;
        }
    }
    
    public static String ev() {
        return ji.util.d.hu;
    }
    
    public static final byte[] b(byte[] array, final String s, final boolean b, final y y, final String s2) throws b6 {
        byte[] array2 = null;
        boolean b2 = false;
        System.currentTimeMillis();
        if (!b) {
            final int n = 9;
            if (array.length > 2 * n) {
                final byte[] array3 = new byte[n];
                final byte[] array4 = new byte[n];
                System.arraycopy(array, 0, array3, 0, array3.length);
                System.arraycopy(array, n, array4, 0, array4.length);
                System.arraycopy(array4, 0, array, 0, array3.length);
                System.arraycopy(array3, 0, array, n, array3.length);
            }
            final int n2 = 64;
            array2 = new byte[array.length - n2];
            final byte[] array5 = new byte[n2];
            System.arraycopy(array, 0, array5, 0, n2);
            System.arraycopy(array, n2, array2, 0, array2.length);
            array = array2;
            y.a = a(6, array5);
            y.b = a(10, array5);
            final int a = a(14, array5);
            final int b3 = b(0, array2);
            if (a != 0 && b3 != a) {
                ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" is corrupted (#1/").append(a).append("/").append(b3).append(")."))));
            }
            final int a2 = a(18, array5);
            if (a2 > 0 && a2 > 128) {
                a(14, array5);
            }
            if (s.toLowerCase().indexOf(ji.util.d.as.toLowerCase()) >= 0) {}
            b2 = true;
        }
        else if (array != null) {
            final int a3 = a(0, array);
            if (a3 > 16) {
                final int a4 = a(14, array);
                if (a4 != 0) {
                    final int b4 = b(a3, array);
                    if (b4 != a4) {
                        ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" is corrupted (#2/").append(a4).append("/").append(b4).append(")."))));
                        throw new b6();
                    }
                }
            }
            fn();
            final int n3 = array.length - a3;
            if (n3 > 0) {
                if (array[5] != 0) {
                    b2 = true;
                }
                array2 = new byte[n3];
                for (int i = 0; i < n3; i += 2) {
                    if (i + 1 < n3) {
                        final int n4 = array[a3 + i] & 0xFF;
                        final int n5 = array[a3 + i + 1] & 0xFF;
                        final int n6 = n4 & 0xF;
                        final int n7 = (n4 & 0xF0) >>> 4;
                        final int n8 = n5 & 0xF;
                        final int n9 = (n5 & 0xF0) >>> 4;
                        array[a3 + i] = (byte)(n8 + (n6 << 4));
                        array[a3 + i + 1] = (byte)(n9 + (n7 << 4));
                    }
                }
                for (int j = 0; j < n3; ++j) {
                    array2[n3 - j - 1] = ji.util.d.ey[array[a3 + j] & 0xFF];
                }
            }
        }
        if (cy()) {
            ji.io.h.d(s2, "UC2 ".concat(String.valueOf(String.valueOf(b2))));
        }
        byte[] g;
        if (b2) {
            g = g(array2);
        }
        else {
            g = array2;
        }
        if (cy()) {
            ji.io.h.d(s2, "UC3/".concat(String.valueOf(String.valueOf(s))));
        }
        return g;
    }
    
    private static final int b(final int n, final byte[] array) {
        byte b = 0;
        for (int i = n; i < array.length; ++i) {
            b += array[i];
        }
        return b;
    }
    
    private static final void fn() {
        if (ji.util.d.ey == null) {
            ji.util.d.ey = new byte[256];
            for (int i = 0; i < 256; ++i) {
                ji.util.d.ey[i] = (byte)(255 - i);
            }
        }
    }
    
    public static final String a(final String s, final String s2, final String s3, final boolean b, final Object o, final String s4, final af af) {
        final String s5 = null;
        final byte[] array = new byte[2];
        final byte[] array2 = { 0 };
        final boolean b2 = false;
        ac ac = null;
        ac ac2 = null;
        System.currentTimeMillis();
        try {
            if (!b) {
                ac = new ac(s, false, false, 0, false, o, false, s4);
                ac2 = new ac(s2, true, false, 0, false, o, true, s4);
                final byte[] array3 = new byte[(int)ac.w()];
                ac.a(array3);
                ac2.b(b(array3, s, true, new y(), s4));
            }
            else if (s != null && s2 != null) {
                ac = new ac(s, false, false, 0, false, o, false, s4);
                final int n = (int)ac.w();
                final int p7 = ac.p();
                ac.a(0L);
                int i = n - p7;
                final byte[] array4 = new byte[p7];
                ac.a(array4);
                if (i > 0) {
                    if (array4[5] != 0) {
                        ji.io.h.d(s4, "Method option not yet supported for zipped v1 files");
                    }
                    ac2 = new ac(s2, true, false, 0, false, o, true, s4);
                    final byte[] array5 = new byte[102400];
                    while (i > 0) {
                        final int a = ac.a(array5);
                        if (a <= 0) {
                            break;
                        }
                        i -= a;
                        ac2.b(array5, 0, a);
                    }
                }
            }
            if (cy()) {
                ji.io.h.d(s4, "UC2 ".concat(String.valueOf(String.valueOf(b2))));
            }
            if (b2) {
                ji.io.h.d(s4, "zipped uncrypt with file method not yet supported");
            }
            if (cy()) {
                ji.io.h.d(s4, "UC3");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (ac != null) {
                    ac.a(o);
                }
            }
            catch (Exception ex2) {}
            try {
                if (ac2 != null) {
                    ac2.a(o);
                }
            }
            catch (Exception ex3) {}
        }
        return s5;
    }
    
    private static final byte[] g(final byte[] array) {
        if (ji.util.d.a2) {
            try {
                return ji.zip.a4.a(array, null);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return array;
            }
        }
        return array;
    }
    
    public static boolean a(final URL url) {
        return url != null && bx(url.toString());
    }
    
    public static boolean bx(final String s) {
        boolean b = false;
        if (s != null) {
            if (s.indexOf("file:") >= 0) {
                b = true;
            }
            if (s.indexOf("localhost/") >= 0) {
                b = true;
            }
        }
        return b;
    }
    
    public static String a(final int n, final int n2) {
        String s = "".concat(String.valueOf(String.valueOf(n)));
        final int length = s.length();
        if (length < n2) {
            for (int i = 0; i < n2 - length; ++i) {
                s = "0".concat(String.valueOf(String.valueOf(s)));
            }
        }
        else if (length > n2) {
            s = s.substring(s.length() - length + 1, s.length());
        }
        return s;
    }
    
    public static boolean b(final String[] array) {
        boolean b = true;
        if (array != null && array.length > 0) {
            b = false;
        }
        return b;
    }
    
    public static boolean by(final String s) {
        boolean b = true;
        if (s != null && s.length() > 0) {
            b = false;
        }
        return b;
    }
    
    public static final void ca(final boolean fv) {
        ji.util.d.fv = fv;
    }
    
    public static final void bz(final String s) {
        ji.util.d.kn = (!ax(s) || !dp());
    }
    
    public static final void ew() {
        if (ji.util.d.fv && ji.util.d.kn) {
            ex();
        }
    }
    
    public static final void ex() {
        if (!ji.util.e.av()) {
            Toolkit.getDefaultToolkit().sync();
        }
    }
    
    public static final void cb(final boolean e0) {
        ji.util.d.e0 = e0;
    }
    
    public static final boolean ey() {
        return ji.util.d.e0;
    }
    
    public static final void cc(final boolean e1) {
        ji.util.d.e1 = e1;
    }
    
    public static final boolean ez() {
        return ji.util.d.e1;
    }
    
    public static final int e0() {
        int b = -1;
        try {
            if (ji.util.d.l != null) {
                b = ji.util.d.l.b();
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final void e1() {
        try {
            if (ji.util.d.l != null) {
                ji.util.d.l.a();
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void c(final Component component) {
        try {
            if (ji.util.d.m != null) {
                component.addMouseListener(ji.util.d.m);
                component.addMouseMotionListener(ji.util.d.m);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void d(final Component component) {
        try {
            if (ji.util.d.m != null) {
                component.removeMouseListener(ji.util.d.m);
                component.removeMouseMotionListener(ji.util.d.m);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final MouseListener mouseListener) {
        try {
            if (ji.util.d.m != null) {
                ji.util.d.m.a(mouseListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void b(final MouseListener mouseListener) {
        try {
            if (ji.util.d.m != null) {
                ji.util.d.m.b(mouseListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final MouseMotionListener mouseMotionListener) {
        try {
            if (ji.util.d.m != null) {
                ji.util.d.m.a(mouseMotionListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void b(final MouseMotionListener mouseMotionListener) {
        try {
            if (ji.util.d.m != null) {
                ji.util.d.m.b(mouseMotionListener);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final int e(final String s, final int n) {
        int c = n;
        try {
            final int index = s.indexOf("#");
            if (index >= 0) {
                c = c(s.substring(index + 1), 1);
            }
        }
        catch (Exception ex) {
            c = n;
        }
        return c;
    }
    
    public static final int b0(final String s) {
        int c = 1;
        try {
            final int index = s.indexOf("#");
            if (index >= 0) {
                c = c(s.substring(index + 1), 1);
            }
        }
        catch (Exception ex) {
            c = 1;
        }
        return c;
    }
    
    public static final int d(final Object o) {
        if (o == null) {
            return 1;
        }
        if (o instanceof String) {
            return b0((String)o);
        }
        return b0(((URL)o).toString());
    }
    
    public static final void a(final c ga) {
        ji.util.d.ga = ga;
    }
    
    public static final void b(final Component component, final af af, final ah ah, final String s) {
        if (ji.util.d.ga != null) {
            try {
                final c c = new c("jiUtil.HTMLParam");
                final ax d = ji.util.d.ga.d();
                String value = "";
                final String s2 = "<=====>";
                while (d.a()) {
                    String b = null;
                    try {
                        b = d.b();
                    }
                    catch (Exception ex2) {}
                    if (!by(b) && c.d(b) == null) {
                        c.a(b, b);
                        value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append((Object)b).append(" ").append(s2).append(" ").append((String)ji.util.d.ga.d(b)).append("\n")));
                    }
                }
                c.c();
                a("Processed tags", value, component, null, af, s, s2, true);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static final void a(final Graphics graphics, final Rectangle rectangle) {
        if (graphics != null) {
            final Rectangle clipRect = graphics.getClipRect();
            Rectangle intersection = new Rectangle(rectangle);
            if (clipRect != null && (clipRect.width > 0 || clipRect.height > 0)) {
                intersection = intersection.intersection(clipRect);
            }
            graphics.setClip(intersection.x, intersection.y, intersection.width, intersection.height);
        }
    }
    
    public static final void a(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            final Rectangle clipRect = graphics.getClipRect();
            if (clipRect != null) {
                final Rectangle rectangle = clipRect;
                rectangle.x -= n;
                final Rectangle rectangle2 = clipRect;
                rectangle2.y -= n2;
                final Rectangle rectangle3 = clipRect;
                rectangle3.width += 2 * n;
                final Rectangle rectangle4 = clipRect;
                rectangle4.height += 2 * n2;
                graphics.setClip(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
            }
        }
    }
    
    public static final GregorianCalendar e2() {
        final Date time = new Date();
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(time);
        return new GregorianCalendar(gregorianCalendar.get(1), gregorianCalendar.get(2), gregorianCalendar.get(5), gregorianCalendar.get(11), gregorianCalendar.get(12), gregorianCalendar.get(13));
    }
    
    public static String cd(final boolean b) {
        return a(e2(), b);
    }
    
    public static int e3() {
        if (ji.util.d.mn < 0) {
            ji.util.d.mn = 1900 + new Date().getYear();
        }
        return ji.util.d.mn;
    }
    
    public static final String a(final GregorianCalendar gregorianCalendar, final boolean b) {
        if (gregorianCalendar != null) {
            try {
                if (b) {
                    return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(DateFormat.getDateInstance(ji.util.d.jo).format(gregorianCalendar.getTime())))).append(", ").append(new SimpleDateFormat("kk:mm:ss, z").format(gregorianCalendar.getTime()))));
                }
                return new SimpleDateFormat("d MMM yyyy, kk:mm:ss, z").format(gregorianCalendar.getTime());
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return "";
    }
    
    public static final String b(final GregorianCalendar gregorianCalendar, final boolean b) {
        if (gregorianCalendar != null) {
            try {
                if (b) {
                    return DateFormat.getDateInstance(ji.util.d.jo).format(gregorianCalendar.getTime());
                }
                return new SimpleDateFormat("d MMM yyyy").format(gregorianCalendar.getTime());
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return "";
            }
        }
        return "";
    }
    
    public static final String a(final Component component, final String s) throws Exception {
        final String n = ji.io.q.a(component, s).n();
        if (n != null) {
            final ac ac = new ac(n, true, false, 0, false, component, s);
            if (ac != null) {
                final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Report start: ").append(a(e2(), false)).append("\n\n"))))).concat("Document:\n\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("DocumentId: ").append(ji.util.d.bf).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("NumPages: ").append(ji.util.d.be).append("\n\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Opened: ").append(ji.util.d.bg).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Closed: ").append(ji.util.d.bi).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("TotalOpenTime: ").append((ji.util.d.bj - ji.util.d.bh) / 1000).append(" seconds\n\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("PageViews: ").append(ji.util.d.bl).append("\n"))))));
                String s2;
                if (ji.util.d.bl > 0) {
                    s2 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("AverageTimePerPageView: ").append((ji.util.d.bj - ji.util.d.bh) / 1000 / ji.util.d.bl).append(" seconds\n\n"))))));
                }
                else {
                    s2 = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("AverageTimePerPageView: ").append((ji.util.d.bj - ji.util.d.bh) / 1000).append(" seconds\n\n"))))));
                }
                ac.b(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2)).concat("Function usage:\n\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Help: ").append(ji.util.d.cs).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Open: ").append(ji.util.d.bm).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Close: ").append(ji.util.d.bn).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Save: ").append(ji.util.d.bp).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("PrintPage: ").append(ji.util.d.bs).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ZoomArea: ").append(ji.util.d.bw).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ZoomIn: ").append(ji.util.d.by).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ZoomOut: ").append(ji.util.d.b0).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("FtoW: ").append(ji.util.d.b1).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("FtoH: ").append(ji.util.d.b2).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("BestFit: ").append(ji.util.d.b3).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("RotateClockwise: ").append(ji.util.d.b4).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("RotateAntiClockwise: ").append(ji.util.d.b5).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Rotate180: ").append(ji.util.d.b6).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("EnhanceOn: ").append(ji.util.d.b7).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("EnhanceOf: ").append(ji.util.d.b8).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("AnnotationsOn: ").append(ji.util.d.b9).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("AnnotationsOff: ").append(ji.util.d.ca).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("InvertOn: ").append(ji.util.d.cb).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("InvertOff: ").append(ji.util.d.cc).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("MagnifierOn: ").append(ji.util.d.cd).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("MagnifierOff: ").append(ji.util.d.ce).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("FullPage: ").append(ji.util.d.cf).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("TwoPage: ").append(ji.util.d.cg).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("Thumbs: ").append(ji.util.d.ch).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ThumbeWest: ").append(ji.util.d.ci).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ThumbsTop: ").append(ji.util.d.cj).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ThumbsEast: ").append(ji.util.d.ck).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ThumbsSouth: ").append(ji.util.d.cl).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("FlipH: ").append(ji.util.d.cp).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("FlipV: ").append(ji.util.d.cr).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ImageDragged: ").append(ji.util.d.ct).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("ImageScrolled: ").append(ji.util.d.cu).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("MenusUsed: ").append(ji.util.d.cv).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("KeysUsed: ").append(ji.util.d.cw).append("\n")))))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("NewWindow: ").append(ji.util.d.bq).append("\n")))))))).concat("\n"))).concat("Report End.").getBytes());
                ac.a(component);
            }
        }
        return n;
    }
    
    public static final void a(final String s, final Applet applet, final Component component, final af af, final String s2) throws Exception {
        if (s != null) {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("save=").append(ji.util.d.bp).append("&printpage=").append(ji.util.d.bs)));
            if (cy()) {
                ji.io.h.d(s2, "Server object = ".concat(String.valueOf(String.valueOf(value))));
            }
            a(value, component, af, true, s2);
        }
    }
    
    public static final void a(final af af, final int n, final String s) {
        try {
            if (af != null) {
                boolean b = true;
                if (af instanceof cy && ((cy)af).ba()) {
                    b = false;
                }
                if (b) {
                    if (ji.util.d.gx == null) {
                        ji.util.d.gx = b(524, s);
                    }
                    af.a(new a6(af, 4, "".concat(String.valueOf(String.valueOf(n)))));
                    if (n > 0) {
                        af.a(new a6(af, 1, ji.util.d.gx));
                    }
                    else {
                        af.a(new a6(af, 1, ""));
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final Color e4() {
        return new Color(0, 0, 1);
    }
    
    public static final Color b1(final String s) {
        String bc = bc(s);
        if (bc != null && by(bc)) {
            bc = null;
        }
        if (bc == null) {
            return SystemColor.control;
        }
        final String lowerCase = bc.toLowerCase();
        if (ji.util.d.gr == null) {
            try {
                ji.util.d.gr = new String[13];
                ji.util.d.gs = new Color[ji.util.d.gr.length];
                int n = 0;
                ji.util.d.gr[n] = "black";
                ji.util.d.gs[n++] = Color.black;
                ji.util.d.gr[n] = "blue";
                ji.util.d.gs[n++] = Color.blue;
                ji.util.d.gr[n] = "cyan";
                ji.util.d.gs[n++] = Color.cyan;
                ji.util.d.gr[n] = "darkgray";
                ji.util.d.gs[n++] = Color.darkGray;
                ji.util.d.gr[n] = "gray";
                ji.util.d.gs[n++] = Color.gray;
                ji.util.d.gr[n] = "green";
                ji.util.d.gs[n++] = Color.green;
                ji.util.d.gr[n] = "lightgray";
                ji.util.d.gs[n++] = Color.lightGray;
                ji.util.d.gr[n] = "magenta";
                ji.util.d.gs[n++] = Color.magenta;
                ji.util.d.gr[n] = "orange";
                ji.util.d.gs[n++] = Color.orange;
                ji.util.d.gr[n] = "pink";
                ji.util.d.gs[n++] = Color.pink;
                ji.util.d.gr[n] = "red";
                ji.util.d.gs[n++] = Color.red;
                ji.util.d.gr[n] = "white";
                ji.util.d.gs[n++] = Color.white;
                ji.util.d.gr[n] = "yellow";
                ji.util.d.gs[n++] = Color.yellow;
            }
            catch (Exception ex) {}
        }
        try {
            for (int i = 0; i < ji.util.d.gr.length; ++i) {
                if (lowerCase.equals(ji.util.d.gr[i])) {
                    return ji.util.d.gs[i];
                }
            }
        }
        catch (Exception ex2) {}
        final boolean b = false;
        final StringTokenizer stringTokenizer = new StringTokenizer(bc, ",");
        if (stringTokenizer.countTokens() == 3) {
            final String bc2 = bc(stringTokenizer.nextToken());
            final String bc3 = bc(stringTokenizer.nextToken());
            final String bc4 = bc(stringTokenizer.nextToken());
            if (bc2 != null && bc3 != null && bc4 != null) {
                return new Color(df(bc2) * 256 * 256 + df(bc3) * 256 + df(bc4));
            }
        }
        if (b) {
            return new Color(b ? 1 : 0);
        }
        return SystemColor.control;
    }
    
    private static final int df(final String s) {
        int int1 = 0;
        final String bc = bc(s);
        try {
            if (bc != null && bb(bc)) {
                int1 = Integer.parseInt(bc);
            }
        }
        catch (Exception ex) {}
        return int1;
    }
    
    public static final String l(final Color color) {
        if (color != null) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(color.getRed()))).append(", ").append(color.getGreen()).append(", ").append(color.getBlue())));
        }
        return "";
    }
    
    public static final String b2(final String s) {
        if (s != null) {
            return s;
        }
        return "";
    }
    
    public static final void ce(final boolean fu) {
        ji.util.d.fu = fu;
    }
    
    public static final boolean e5() {
        return ji.util.d.fu;
    }
    
    public static final int b3(final String s) {
        if (by(s)) {
            return 0;
        }
        return s.length();
    }
    
    public static final String[] b(final String s, final String s2, final String s3, final String s4) {
        String[] array = null;
        if (s2 != null) {
            final String concat = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(s3)));
            String.valueOf(String.valueOf(s4)).concat(String.valueOf(String.valueOf(s4)));
            String s5 = b(b(s2, concat, s3), concat, s4);
            String substring = s;
            final c c = new c("jiUtil10");
            int i = s5.indexOf(s3);
            if (i < 0) {
                substring = s5;
            }
            if (i > 0) {
                substring = s5.substring(0, i);
            }
            while (i >= 0) {
                final int index = s5.indexOf(s4);
                if (index < 0) {
                    break;
                }
                c.c(s5.substring(i + 1, index));
                s5 = s5.substring(index + 1);
                i = s5.indexOf(s3);
            }
            array = new String[c.b() + 1];
            array[0] = substring;
            for (int j = 0; j < c.b(); ++j) {
                array[j + 1] = (String)c.b(j);
            }
        }
        return array;
    }
    
    public static d4 a(d4 d4) {
        for (int i = 1; i != 0; i = 1) {
            i = 0;
            for (int b = d4.b(), j = 1; j < b; ++j) {
                final d5 a = d4.a(j);
                if (a.a > 250000 || a.b > 250000) {
                    final d4 d5 = new d4();
                    for (int k = 0; k < b; ++k) {
                        if (k != j) {
                            d5.a(d4.a(k));
                        }
                    }
                    d4 = d5;
                    break;
                }
            }
        }
        return d4;
    }
    
    public static final void a(final Image image) {
        try {
            if (image != null) {
                final ImageProducer source = image.getSource();
                if (source instanceof c1) {
                    ((c1)source).i();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final long p(final String s, final String s2) {
        if (s != null) {
            long n = 0L;
            try {
                final String[] h = ji.io.ac.h(s, s2);
                for (int i = 0; i < h.length; ++i) {
                    final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("/").append(h[i])));
                    if (ji.io.ac.f(value, s2)) {
                        n += ji.io.ac.a(value, s2);
                        n += p(value, s2);
                    }
                    else {
                        n += ji.io.ac.a(value, s2);
                    }
                }
            }
            catch (Exception ex) {}
            return n;
        }
        return 0L;
    }
    
    public static final void q(final String s, final String s2) {
        a(s, s2, false);
    }
    
    public static final void a(final String s, final String s2, final boolean b) {
        if (s != null) {
            try {
                final String[] h = ji.io.ac.h(s, s2);
                for (int i = 0; i < h.length; ++i) {
                    ji.io.ac.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("/").append(h[i]))), s2, b);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static final byte[] b(final String s, final af af, final String s2) {
        byte[] array = null;
        try {
            if (!h(s, s2).toLowerCase().startsWith(cu(s2).toLowerCase())) {
                final ac ac = new ac(s, false, false, 0, false, af, s2);
                array = new byte[(int)ac.w()];
                ac.a(array);
                ac.a(af);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return array;
    }
    
    public static final void m(final Color hl) {
        ji.util.d.hl = hl;
    }
    
    public static final Color e6() {
        return ji.util.d.hl;
    }
    
    public static final void b4(final String ho) {
        ji.util.d.ho = ho;
    }
    
    public static final void b5(final String hp) {
        ji.util.d.hp = hp;
    }
    
    public static final void b6(final String hs) {
        ji.util.d.hs = hs;
    }
    
    public static final void b7(final String hq) {
        ji.util.d.hq = hq;
    }
    
    public static final void b8(final String hr) {
        ji.util.d.hr = hr;
    }
    
    public static final String e7() {
        return ji.util.d.hp;
    }
    
    public static final String e8() {
        return ji.util.d.ho;
    }
    
    public static final String e9() {
        return ji.util.d.hq;
    }
    
    public static final String fa() {
        return ji.util.d.hr;
    }
    
    public static final String fb() {
        return ji.util.d.hs;
    }
    
    public static final String b9(final String s) {
        String ht = "";
        if (ji.util.e.ad()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(959, s))));
        }
        if (ji.util.e.ae()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(958, s))));
        }
        if (ji.util.e.w()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(cn(b(746, s)))));
        }
        if (ji.util.e.aa()) {
            if (co(s)) {
                ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(1238, s))));
            }
            else {
                ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(933, s))));
            }
        }
        if (ap()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(955, s))));
        }
        if (ji.util.i.c(3)) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(960, s))));
        }
        if (ji.util.i.c(21) && aa()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(748, s))));
        }
        if (ji.util.i.c(12) && ji.util.e.v()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(747, s))));
        }
        if (ji.util.e.x()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(cn(b(1160, s)))));
        }
        if (ji.util.e.z()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(1161, s))));
        }
        if (ji.util.e.u()) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(749, s))));
        }
        if (ji.sec.g.a(s)) {
            ht = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ht))).append("\n").append(b(1228, s))));
        }
        if (!ht.equals("")) {
            ht = String.valueOf(String.valueOf(new StringBuffer("\n").append(b(54, s)).append(ht)));
        }
        return ji.util.d.ht = ht;
    }
    
    private static final void a(final int n, final Component component, final String s, final ah ah) throws Exception {
        String s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.aa.a(component, s, false)))).append(" - ").append(e8())));
        final String a = ji.res.aa.a(component, s);
        if (eg()) {}
        if (ji.util.d.eh) {
            s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.aa.a(component, s, true)))).append(" - ").append(b(81, s))));
        }
        String s3 = String.valueOf(String.valueOf(s.b(s, false))).concat("\n");
        if (dc()) {
            s3 = String.valueOf(String.valueOf(s3)).concat("(Support License)\n");
        }
        a(s2, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\n").append(a).append("\n").append(e7()).append("\n").append(e9()).append("\n").append(fa()).append(b(957, s)).append(s.a(s, false)).append("\n").append(s3).append(fb()).append("\n \n").append(b9(s)))), component, n, null, null, s);
    }
    
    public static final void a(final Component component, final String s, final ah ah) throws Exception {
        a(120, component, s, ah);
    }
    
    public static final String ca(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(9, s)))).append(" \n").append(b(965, s))));
    }
    
    public static final Frame e(final Component component) {
        Component parent;
        for (parent = component; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        return null;
    }
    
    public static final Window f(final Component component) {
        boolean fo;
        Component parent;
        for (fo = fo(), parent = component; parent != null && !(parent instanceof Frame) && (!fo || !(parent instanceof Dialog)); parent = parent.getParent()) {}
        if (parent instanceof Frame) {
            return (Frame)parent;
        }
        if (fo && parent instanceof Dialog) {
            return (Dialog)parent;
        }
        return null;
    }
    
    private static boolean fo() {
        final Class clazz = (ji.util.d.m2 == null) ? (ji.util.d.m2 = class$("java.awt.Dialog")) : ji.util.d.m2;
        boolean b;
        try {
            b = (clazz.getConstructor((ji.util.d.m2 == null) ? (ji.util.d.m2 = class$("java.awt.Dialog")) : ji.util.d.m2) != null);
        }
        catch (NoSuchMethodException ex) {
            b = false;
        }
        return b;
    }
    
    public static Color n(final Color color) {
        final int rgb = color.getRGB();
        final int n = (rgb & 0xFF0000) >> 24;
        final int[] array = { (rgb & 0xFF0000) >> 16, (rgb & 0xFF00) >> 8, rgb & 0xFF };
        final int max = Math.max(Math.max(array[0], array[1]), array[2]);
        final int min = Math.min(Math.min(array[0], array[1]), array[2]);
        if (max != min || min >= 192) {
            if (max == min && min > 191) {
                array[0] = Math.max(0, array[0] - 25);
                array[1] = Math.max(0, array[1] - 25);
                array[2] = Math.max(0, array[2] - 25);
            }
            else if (max - min < 64) {
                final int min2 = Math.min(255, max + 32);
                final int max2 = Math.max(0, min - (64 - (max - min)));
                for (int i = 0; i < 3; ++i) {
                    if (array[i] == max) {
                        array[i] = min2;
                    }
                    else if (array[i] == min) {
                        array[i] = max2;
                    }
                }
            }
        }
        return new Color(n << 24 | array[0] << 16 | array[1] << 8 | array[2]);
    }
    
    public static String cb(final String s) {
        return bc(b(b(b(s, "\r\n", "\n"), "\r", "<N>"), "\n", "<N>"));
    }
    
    public static String cc(final String s) {
        if (s != null) {
            return b(s, "<N>", "\n");
        }
        return null;
    }
    
    public static final String r(final String s, final String s2) {
        String s3 = "";
        if (s2 != null) {
            String s4 = String.valueOf(String.valueOf(a3())).concat(String.valueOf(String.valueOf(bc(s2))));
            String s5 = s4.toLowerCase();
            final String lowerCase = a3().toLowerCase();
            final int length = lowerCase.length();
            for (int i = s5.indexOf(lowerCase); i >= 0; i = s5.indexOf(lowerCase)) {
                s5 = s5.substring(i + length);
                s4 = s4.substring(i + length);
                final int index = s5.indexOf(lowerCase);
                String s6;
                if (index >= 0) {
                    s6 = bc(s4.substring(0, index));
                }
                else {
                    s6 = bc(s4);
                }
                final String lowerCase2 = s6.toLowerCase();
                if (!by(lowerCase2) && lowerCase2.startsWith(s.toLowerCase())) {
                    final String bc = bc(s6.substring(s.length()));
                    if (bc.startsWith("=")) {
                        final String bc2 = bc(bc.substring(1));
                        if (!by(bc2)) {
                            s3 = bc2;
                            break;
                        }
                    }
                }
            }
        }
        return s3;
    }
    
    public static final int[] a(final int n, final int n2, final int n3) {
        if (ji.util.d.jd == null) {
            ji.util.d.jd = new c("jiUtilLineFills");
        }
        final int max = Math.max(n2, 0);
        final String concat = "".concat(String.valueOf(String.valueOf(n)));
        int[] array = (int[])ji.util.d.jd.d(concat);
        try {
            if (array == null) {
                array = new int[max];
                for (int i = 0; i < max; i += n3) {
                    array[i] = n;
                }
                ji.util.d.jd.a(concat, array);
            }
            else if (array.length < max) {
                ji.util.d.jd.a(concat);
                array = new int[max];
                for (int j = 0; j < max; j += n3) {
                    array[j] = n;
                }
                ji.util.d.jd.a(concat, array);
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    public static final String[] s(final String s, final String s2) {
        final String[] array = { "", "" };
        try {
            if (!by(s)) {
                final int index = s.indexOf(s2.toLowerCase());
                if (index >= 0) {
                    array[0] = bc(s.substring(0, index));
                    array[1] = bc(s.substring(index + 1));
                }
                else {
                    array[0] = bc(s);
                    array[1] = bc(s);
                }
            }
        }
        catch (Exception ex) {}
        return array;
    }
    
    public static final boolean fc() {
        return ji.util.d.ji;
    }
    
    public static final void cf(final boolean ji) {
        ji.util.d.ji = ji;
    }
    
    public static final void a(final Graphics graphics, final Rectangle rectangle, final Color color, final Color color2, final boolean b) {
        try {
            final int red = color.getRed();
            final int green = color.getGreen();
            final int blue = color.getBlue();
            final int red2 = color2.getRed();
            final int green2 = color2.getGreen();
            final int blue2 = color2.getBlue();
            final int n = red2 - red;
            final int n2 = green2 - green;
            final int n3 = blue2 - blue;
            final int x = rectangle.x;
            final int y = rectangle.y;
            if (b) {
                final int n4 = rectangle.x + rectangle.width;
                final int n5 = rectangle.y + rectangle.height;
                final int n6 = n5 - y;
                for (int i = y; i < n5; ++i) {
                    graphics.setColor(new Color(red + (i - y) * n / n6, green + (i - y) * n2 / n6, blue + (i - y) * n3 / n6));
                    graphics.drawLine(x, i, n4, i);
                }
            }
            else {
                final int n7 = rectangle.x + rectangle.width;
                final int n8 = rectangle.y + rectangle.height;
                final int n9 = n7 - x;
                for (int j = x; j < n7; ++j) {
                    graphics.setColor(new Color(red + (j - x) * n / n9, green + (j - x) * n2 / n9, blue + (j - x) * n3 / n9));
                    graphics.drawLine(j, y, j, n8);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final int[] array, final Rectangle rectangle, final Color color, final Color color2, final boolean b) {
        try {
            final int red = color.getRed();
            final int green = color.getGreen();
            final int blue = color.getBlue();
            final int red2 = color2.getRed();
            final int green2 = color2.getGreen();
            final int blue2 = color2.getBlue();
            final int n = red2 - red;
            final int n2 = green2 - green;
            final int n3 = blue2 - blue;
            final int x = rectangle.x;
            final int y = rectangle.y;
            if (b) {
                final int width = rectangle.width;
                final int height = rectangle.height;
                final int n4 = height - y;
                for (int i = y; i < height; ++i) {
                    final int n5 = i * rectangle.width;
                    final int n6 = 0xFF000000 | red + (i - y) * n / n4 << 16 | green + (i - y) * n2 / n4 << 8 | blue + (i - y) * n3 / n4;
                    for (int j = x; j < width; ++j) {
                        array[j + n5] = n6;
                    }
                }
            }
            else {
                final int width2 = rectangle.width;
                final int height2 = rectangle.height;
                final int n7 = width2 - x;
                for (int k = x; k < width2; ++k) {
                    final int n8 = 0xFF000000 | red + (k - x) * n / n7 << 16 | green + (k - x) * n2 / n7 << 8 | blue + (k - x) * n3 / n7;
                    for (int l = y; l < height2; ++l) {
                        array[l * rectangle.width + k] = n8;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public static String cd(final String s) {
        if (by(s)) {
            return s;
        }
        if (s.startsWith("V!RF")) {
            return "MEM";
        }
        return s;
    }
    
    public static final String[] a(String substring, final ad ad, final String s, final String s2, final boolean b, final boolean b2) throws Exception {
        try {
            byte[] array;
            if (substring.startsWith("V!RF")) {
                substring = substring.substring("V!RF".length());
                if (s != null) {
                    array = substring.getBytes(s);
                }
                else {
                    array = substring.getBytes();
                }
            }
            else {
                final ac ac = new ac(substring, false, false, 0, ad, s2);
                array = new byte[(int)ac.v()];
                ac.a(array);
                ac.a(ad);
                if (b) {
                    array = ji.zip.a4.a(array, null);
                }
            }
            return a(array, ad, s, substring, s2, b2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static final String[] a(final byte[] array, final ad ad, final String s, final String s2, final String s3, final boolean b) throws Exception {
        String[] array2 = null;
        try {
            c c = new c("jiUtilConvert1");
            String s4;
            if (s != null) {
                s4 = s;
            }
            else {
                s4 = bm(s3);
            }
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferedReader = null;
            int i = 2;
            while (i > 0) {
                try {
                    if (!by(s4)) {
                        inputStreamReader = new InputStreamReader(byteArrayInputStream, s4);
                    }
                    else {
                        inputStreamReader = new InputStreamReader(byteArrayInputStream);
                    }
                }
                catch (Exception ex) {
                    ji.io.h.d(s3, "Error processing file with encoding: ".concat(String.valueOf(String.valueOf(s4))));
                    ex.printStackTrace();
                    s4 = bm(s3);
                    ji.io.h.d(s3, "Using default encoding : ".concat(String.valueOf(String.valueOf(s4))));
                    inputStreamReader = new InputStreamReader(byteArrayInputStream, s4);
                }
                if (dr()) {
                    ji.io.h.d(s3, "Encoding: ".concat(String.valueOf(String.valueOf(s4))));
                }
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                    String s5 = bufferedReader.readLine();
                    int n = 0;
                    while (s5 != null) {
                        if (s5 != null) {
                            ++n;
                            c.c(s5);
                        }
                        s5 = bufferedReader.readLine();
                    }
                    i = 0;
                }
                catch (Exception ex2) {
                    ji.io.h.d(s3, "Error processing file: ".concat(String.valueOf(String.valueOf(ex2))));
                    ji.io.h.d(s3, "Error processing file with encoding: ".concat(String.valueOf(String.valueOf(s4))));
                    if (--i > 0) {
                        byteArrayInputStream.reset();
                        final String bm = bm(s3);
                        if (bm != null && s4 != null) {
                            if (bm.equalsIgnoreCase(s4)) {
                                s4 = "";
                            }
                            else {
                                s4 = bm(s3);
                            }
                        }
                        else {
                            s4 = bm(s3);
                        }
                        ji.io.h.d(s3, "Retrying with with encoding: ".concat(String.valueOf(String.valueOf(by(s4) ? "DEFAULT" : s4))));
                        try {
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                        }
                        catch (IOException ex4) {}
                        try {
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                        }
                        catch (IOException ex5) {}
                    }
                    else {
                        ji.io.h.d(s3, "Aborting");
                    }
                    if (!dr()) {
                        continue;
                    }
                    ex2.printStackTrace();
                }
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            }
            catch (IOException ex6) {}
            if (c.b() > 0) {
                final c c2 = new c("jiUtilConvert2");
                for (int j = 0; j < c.b(); ++j) {
                    final String s6 = (String)c.b(j);
                    if (!by(bd(bc(s6)))) {
                        c2.c(s6);
                    }
                }
                c = c2;
            }
            if (c.b() > 0) {
                array2 = new String[c.b()];
                for (int k = 0; k < c.b(); ++k) {
                    final String bd = bd(bc((String)c.b(k)));
                    if (!by(bd)) {
                        if (bd.startsWith("//")) {
                            array2[k] = "";
                        }
                        else if (bd.startsWith("<")) {
                            array2[k] = "";
                        }
                        else {
                            array2[k] = bd;
                        }
                    }
                }
            }
            else {
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append("\n ----- ").append(b(286, s3)).append(" -----")));
                if (!ad.hb() && b) {
                    a(ji.res.ay.a(), value, ad, 60, null, null, s3);
                }
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
        return array2;
    }
    
    public static final String ce(final String s) throws Exception {
        if (by(s)) {
            return s;
        }
        if (s.toLowerCase().startsWith("url:")) {
            return s;
        }
        if (ji.util.d.j6 == null) {
            ji.util.d.j6 = new cg();
        }
        return new String(ji.util.d.j6.a(s.getBytes(), 0, true)).toUpperCase();
    }
    
    public static final String cf(final String s) throws Exception {
        if (!by(s)) {
            if (s.toLowerCase().startsWith("url:")) {
                return s;
            }
            try {
                if (ji.util.d.j6 == null) {
                    ji.util.d.j6 = new cg();
                }
                return new String(ji.util.d.j6.a(s.toUpperCase().getBytes(), true));
            }
            catch (Exception ex) {
                return s;
            }
        }
        return s;
    }
    
    public static final boolean a(final String s, final String s2, final a0 a0, final Object o, final String s3, final af af) {
        if (by(s) || by(s2)) {
            return false;
        }
        if (s2.toLowerCase().startsWith("url:")) {
            final String concat = String.valueOf(String.valueOf(s2.substring(4))).concat(String.valueOf(String.valueOf(s)));
            URL url;
            String b;
            try {
                url = new URL(ji.util.e.am(), concat);
                b = a0.b(url, false, false, "", o, af, null);
            }
            catch (Exception ex) {
                a(ex, ji.res.ay.a(), (Component)o, 120, af, s3);
                return false;
            }
            if (b != null) {
                try {
                    final ac ac = new ac(b, false, false, 0, false, o, false, s3);
                    final byte[] array = new byte[(int)ac.v()];
                    ac.a(array);
                    ac.a(o);
                    try {
                        ji.io.ac.c(b, s3);
                    }
                    catch (Exception ex3) {}
                    final String s4 = new String(array);
                    if (dr()) {
                        ji.io.h.d(s3, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> NetPW: Response:").append(s4))));
                    }
                    if (by(s4)) {
                        ji.io.h.d(s3, "Empty response from password server: ".concat(String.valueOf(String.valueOf(url))));
                        return false;
                    }
                    if (s4.toLowerCase().indexOf("passwordok") >= 0) {
                        if (dr()) {
                            ji.io.h.d(s3, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> NetPW: Response was PASSWORD OK!"));
                        }
                        return true;
                    }
                    if (dr()) {
                        ji.io.h.d(s3, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> NetPW: Response was INVALID PASSWORD!"));
                    }
                    return false;
                }
                catch (Exception ex2) {
                    a(ex2, ji.res.ay.a(), (Component)o, 120, af, s3);
                    return false;
                }
            }
            return false;
        }
        return s.equals(s2);
    }
    
    public static final boolean cg(final String s) {
        return ji.util.e.u(s) || ay(s) || dq() || b();
    }
    
    public static void fd() {
        ji.util.d.j8 = false;
        ji.util.d.j9 = null;
    }
    
    public static void ch(final String j9) {
        ji.util.d.j8 = true;
        ji.util.d.j9 = j9;
    }
    
    public static String fe() {
        return ji.util.d.j9;
    }
    
    public static boolean ff() {
        return ji.util.d.j8;
    }
    
    public static final String a(final String s, int n, final String s2) {
        if (!ji.util.i.c(15)) {
            return s2;
        }
        final String bc;
        final String s3 = bc = bc(s);
        if (by(bc)) {
            return s2;
        }
        if (bc.equals("-")) {
            return s2;
        }
        if (bc.toLowerCase().equals("f1")) {
            n = 112;
        }
        if (n >= 0) {
            final String b = b(b(bc, "s-", String.valueOf(String.valueOf(KeyEvent.getKeyText(16))).concat("-")), "c-", String.valueOf(String.valueOf(KeyEvent.getKeyText(17))).concat("-"));
            final int lastIndex = b.lastIndexOf("-");
            Object substring = null;
            if (lastIndex >= 0) {
                substring = b.substring(0, lastIndex);
            }
            String upperCase = s3;
            switch (n) {
                case 107:
                case 521: {
                    upperCase = "+";
                    break;
                }
                case 45:
                case 109: {
                    upperCase = "-";
                    break;
                }
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105: {
                    break;
                }
                default: {
                    upperCase = KeyEvent.getKeyText(n).toUpperCase();
                    break;
                }
            }
            String value;
            if (substring != null) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(substring))).append("-").append(upperCase)));
            }
            else {
                value = upperCase;
            }
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(" (").append(value).append(")")));
        }
        return s2;
    }
    
    public static final String b(final String s, final int n, final String s2) {
        return a(s, n, s2);
    }
    
    public static final String c(final String s, final String s2, final String s3) {
        String s4;
        if (s2 != null && s3 != null) {
            s4 = bc(s2.toLowerCase());
        }
        else {
            s4 = bh(s.toLowerCase());
        }
        if (!by(s4) && s4.toLowerCase().startsWith("unknown")) {
            s4 = bh(s.toLowerCase());
        }
        if (!by(s4) && s4.toLowerCase().indexOf("pdf") >= 0) {
            s4 = "pdf";
        }
        return s4;
    }
    
    public static final byte[] a(final short[] array, final int n, int n2) {
        byte[] array2 = new byte[n];
        int n3 = 1;
        int n4 = 1;
        final int length = array.length;
        if (n2 < 0) {
            n2 = n;
        }
        if (length == 0) {
            return new byte[n2];
        }
        try {
            for (int n5 = array[0] & 0xFFFF; n5 < n && n4 < length; ++n4) {
                if (n3 == 1) {
                    final int n6 = n5 + (array[n4] & 0xFFFF);
                    for (int i = n5; i < n6; ++i) {
                        array2[i] = -1;
                    }
                    n5 = n6;
                }
                else {
                    n5 += (array[n4] & 0xFFFF);
                }
                n3 = 1 - n3;
            }
        }
        catch (Exception ex) {}
        try {
            if (n2 > 0 && n != n2) {
                final byte[] array3 = new byte[n2];
                for (int j = 0; j < n2; ++j) {
                    array3[j] = array2[j * n / n2];
                }
                array2 = array3;
            }
        }
        catch (Exception ex2) {}
        return array2;
    }
    
    public static final byte[] a(final int[] array, final int n, final int n2) {
        byte[] array2 = new byte[n];
        int n3 = 1;
        int n4 = array[0] & 0xFFFF;
        int n5 = 1;
        final int length = array.length;
        try {
            while (n4 < n && n5 < length) {
                if (n3 == 1) {
                    final int n6 = n4 + (array[n5] & 0xFFFF);
                    for (int i = n4; i < n6; ++i) {
                        array2[i] = -1;
                    }
                    n4 = n6;
                }
                else {
                    n4 += (array[n5] & 0xFFFF);
                }
                n3 = 1 - n3;
                ++n5;
            }
        }
        catch (Exception ex) {}
        try {
            if (n2 > 0 && n != n2) {
                final byte[] array3 = new byte[n2];
                for (int j = 0; j < n2; ++j) {
                    array3[j] = array2[j * n / n2];
                }
                array2 = array3;
            }
        }
        catch (Exception ex2) {}
        return array2;
    }
    
    public static byte[] a(final int[] array, final int n, final int n2, final int n3, final boolean b) {
        int n4 = n / 8;
        if (n4 * 8 != n) {
            ++n4;
        }
        if (b) {
            final int n5 = n4 % 4;
            if (n5 > 0) {
                n4 += 4 - n5;
            }
        }
        final byte[] array2 = new byte[n4 * n2];
        try {
            boolean b2 = false;
            if (n3 != -1) {
                b2 = true;
            }
            for (int i = 0; i < n2; ++i) {
                final int n6 = i * n;
                final int n7 = i * n4;
                int n8 = 0;
                int n9 = 128;
                for (int j = 0; j < n; ++j) {
                    final int n10 = array[n6 + j];
                    final int n11 = (n10 & 0xFF0000) >> 16;
                    final int n12 = (n10 & 0xFF00) >> 8;
                    final int n13 = n10 & 0xFF;
                    if (b2) {
                        if (n11 + n12 + n13 < 384) {
                            final byte[] array3 = array2;
                            final int n14 = n7 + n8;
                            array3[n14] |= (byte)n9;
                        }
                    }
                    else if (n11 + n12 + n13 > 384) {
                        final byte[] array4 = array2;
                        final int n15 = n7 + n8;
                        array4[n15] |= (byte)n9;
                    }
                    n9 >>= 1;
                    if (n9 == 0) {
                        n9 = 128;
                        ++n8;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return array2;
    }
    
    public static final short[] d(final byte[] array) {
        return a(array, false);
    }
    
    public static final short[] a(final byte[] array, final boolean b) {
        final int length = array.length;
        final short[] array2 = new short[length + 2];
        int n = 0;
        byte b2 = 0;
        short n2 = 0;
        if (b) {
            array2[n++] = 0;
        }
        for (final byte b3 : array) {
            if (b3 != b2) {
                array2[n++] = n2;
                b2 = b3;
                n2 = 1;
            }
            else {
                ++n2;
            }
        }
        if (n2 > 0) {
            array2[n++] = n2;
        }
        final short[] array3 = new short[n];
        System.arraycopy(array2, 0, array3, 0, n);
        return array3;
    }
    
    public static final void ci(final String lq) {
        ji.util.d.lq = lq;
    }
    
    public static String fg() {
        return ji.util.d.lq;
    }
    
    public static final short[] a(final byte[] array, final int n, final int[] array2) {
        final int length = array.length;
        final short[] array3 = new short[length + 1];
        int n2 = 0;
        boolean b = false;
        short n3 = 0;
        for (int i = 0; i < length; ++i) {
            final int n4 = array2[array[i] & 0xFF];
            final int n5 = (n4 & 0xFF0000) >> 16 & 0xFF;
            final int n6 = (n4 & 0xFF00) >> 8 & 0xFF;
            final int n7 = n4 & 0xFF;
            boolean b2 = true;
            if ((n5 + n6 + n7) / 3 < n) {
                b2 = false;
            }
            if (b2 != b) {
                array3[n2++] = n3;
                b = b2;
                n3 = 1;
            }
            else {
                ++n3;
            }
        }
        if (n3 > 0) {
            array3[n2++] = n3;
        }
        final short[] array4 = new short[n2];
        System.arraycopy(array3, 0, array4, 0, n2);
        return array4;
    }
    
    public static final short[] a(final int[] array, final int n) {
        final int length = array.length;
        final short[] array2 = new short[length + 1];
        int n2 = 0;
        boolean b = false;
        short n3 = 0;
        for (int i = 0; i < length; ++i) {
            final int n4 = array[i] & 0xFFFFFF;
            final int n5 = (n4 & 0xFF0000) >> 16 & 0xFF;
            final int n6 = (n4 & 0xFF00) >> 8 & 0xFF;
            final int n7 = n4 & 0xFF;
            boolean b2 = true;
            if ((n5 + n6 + n7) / 3 < n) {
                b2 = false;
            }
            if (b2 != b) {
                array2[n2++] = n3;
                b = b2;
                n3 = 1;
            }
            else {
                ++n3;
            }
        }
        if (n3 > 0) {
            array2[n2++] = n3;
        }
        final short[] array3 = new short[n2];
        System.arraycopy(array2, 0, array3, 0, n2);
        return array3;
    }
    
    public static final String a(final a0 a0, final Object o, final String s, final af af) {
        return null;
    }
    
    public static final String t(final String s, final String s2) {
        if (s != null && s2 != null && s.toLowerCase().equals(s2.toLowerCase())) {
            return null;
        }
        return s;
    }
    
    public static final Object a(final Object o, final String s, final long n, final String s2, final int n2, final boolean b) {
        return a(o, s, "".concat(String.valueOf(String.valueOf(n))), s2, n2, b);
    }
    
    public static final Object a(final Object o, final String s, final String s2, final String s3, final int n, final boolean b) {
        try {
            String s4 = o.toString();
            final int index = o.toString().toLowerCase().indexOf(s.toLowerCase());
            if (index >= 0) {
                final String substring = s4.substring(0, index + s.length());
                final String substring2 = s4.substring(index + s.length(), s4.length());
                final int index2 = substring2.indexOf("&");
                if (index2 >= 0) {
                    if (b) {
                        s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(substring))).append("%7B").append(s2).append("%7D").append(substring2.substring(index2))));
                    }
                    else {
                        s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(substring))).append(s2).append(substring2.substring(index2))));
                    }
                }
                else {
                    s4 = String.valueOf(String.valueOf(substring)).concat(String.valueOf(String.valueOf(s2)));
                }
            }
            final int index3 = s4.toLowerCase().indexOf(s3.toLowerCase());
            if (index3 >= 0) {
                final String substring3 = s4.substring(0, index3 + s3.length());
                final String substring4 = s4.substring(index3 + s3.length(), s4.length());
                final int index4 = substring4.indexOf("&");
                if (index4 >= 0) {
                    s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(substring3))).append(n).append(substring4.substring(index4))));
                }
                else {
                    s4 = String.valueOf(String.valueOf(substring3)).concat(String.valueOf(String.valueOf(n)));
                }
            }
            return new URL(s4);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static final boolean al(final int n) {
        return bf() && (am(n) || ji.util.i.c(28));
    }
    
    public static final boolean am(final int n) {
        return bf() && (n == 3 || n == 4);
    }
    
    public static final String u(final String s, final String s2) {
        String substring;
        String substring2;
        if (s2.indexOf(60) > -1) {
            substring = s2.substring(0, s2.indexOf(60));
            substring2 = s2.substring(s2.indexOf(60));
        }
        else {
            substring = s2;
            substring2 = "";
        }
        String value = null;
        final int index = s.toLowerCase().indexOf("id=");
        if (index > -1) {
            value = String.valueOf(String.valueOf(new StringBuffer("image:").append(s.substring(0, index + 3)).append(substring).append(s.substring(s.indexOf("&", index + 3))).append(substring2)));
        }
        return value;
    }
    
    public static gs fh() {
        if (ji.util.d.lp == null) {
            ji.util.d.lp = new gs();
        }
        return ji.util.d.lp;
    }
    
    public static void a(final String[] array, final String s) {
        ji.util.d.lp = new gs(array, s);
    }
    
    public static final boolean a(final int[] array) {
        boolean b = true;
        try {
            final int length = array.length;
            if (length < 255) {
                return false;
            }
            int n = -1;
            Block_8: {
                for (int i = 0; i < length; ++i) {
                    final int n2 = array[i];
                    final int n3 = (n2 & 0xFF0000) >> 16;
                    final int n4 = (n2 & 0xFF00) >> 8;
                    final int n5 = n2 & 0xFF;
                    if (n3 != n4) {
                        return false;
                    }
                    if (n3 != n5) {
                        return false;
                    }
                    if (i > 0 && n == n2) {
                        break Block_8;
                    }
                    n = n2;
                }
                return b;
                b = false;
                return b;
                b = false;
                return b;
            }
            b = false;
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final boolean b(final int[] array) {
        boolean b = true;
        try {
            final int length = array.length;
            int n = 0;
            final int[] array2 = new int[length];
            Block_11: {
                for (final int n2 : array) {
                    if (n2 != 0) {
                        final int n3 = (n2 & 0xFF0000) >> 16;
                        final int n4 = (n2 & 0xFF00) >> 8;
                        final int n5 = n2 & 0xFF;
                        if ((n3 != 255 || n4 != 255 || n5 != 255) && n3 != n5) {
                            return false;
                        }
                    }
                    boolean b2 = false;
                    for (int j = 0; j < length; ++j) {
                        if (array2[j] == n2) {
                            b2 = true;
                            break;
                        }
                    }
                    if (!b2) {
                        array2[n++] = n2;
                        if (n > 2) {
                            break Block_11;
                        }
                    }
                }
                return b;
                b = false;
                return b;
            }
            b = false;
        }
        catch (Exception ex) {}
        return b;
    }
    
    public static final String c(final long n, final String s) {
        String s2 = "";
        if (ji.util.d.ag > 0) {
            double n2 = 0.0;
            try {
                n2 = n / ji.util.d.ag;
            }
            catch (Exception ex) {}
            final int n3 = (int)Math.round(n2 + 0.5);
            String s3;
            if (n3 <= 1) {
                s3 = "1 ".concat(String.valueOf(String.valueOf(b(663, s))));
            }
            else if (n3 < 60) {
                s3 = String.valueOf(String.valueOf(new StringBuffer("").append(n3).append(" ").append(b(664, s))));
            }
            else if (n3 == 60) {
                s3 = "1 ".concat(String.valueOf(String.valueOf(b(776, s))));
            }
            else {
                final int n4 = (n3 + 15) / 60;
                String s4;
                if (n4 <= 1) {
                    s4 = b(776, s);
                }
                else {
                    s4 = b(777, s);
                }
                s3 = String.valueOf(String.valueOf(new StringBuffer("").append(n4).append(" ").append(s4)));
            }
            s2 = s3;
        }
        return s2;
    }
    
    public static final boolean a(final String s, final Object o, final URL url, final String s2, final String s3, final String s4, final Component component) {
        if (s2 != null) {
            ji.util.r.b(s2);
        }
        if (s3 != null) {
            ji.util.r.a(s3);
        }
        if (s4 != null) {
            ji.util.r.c(s4);
        }
        final boolean a = ji.util.r.a();
        if (!a) {
            final String a2 = ji.util.r.a(component, s, o, null);
            if (a2 != null) {
                a(b(806, s), a2, component, null, null, s, false);
            }
            if (ji.util.r.c()) {
                ji.util.r.a(component, s, null, url);
            }
        }
        return a;
    }
    
    public static final int fi() {
        int n = 512000;
        if (ji.util.e.av()) {
            n = 3145728;
        }
        else if (em()) {
            n = 31457280;
        }
        return n;
    }
    
    public static final void cj(final String s) {
        if (s != null) {
            synchronized (ji.util.d.l5) {
                ji.util.d.l5.put(s, new boolean[] { false, false });
            }
            // monitorexit(d.l5)
        }
    }
    
    public static final void e(final String s, final boolean b) {
        if (s != null) {
            synchronized (ji.util.d.l5) {
                final boolean[] array = ji.util.d.l5.get(s);
                if (array != null) {
                    array[0] = b;
                }
            }
            // monitorexit(d.l5)
        }
    }
    
    public static final void f(final String s, final boolean b) {
        if (s != null) {
            synchronized (ji.util.d.l5) {
                final boolean[] array = ji.util.d.l5.get(s);
                if (array != null) {
                    array[1] = b;
                }
            }
            // monitorexit(d.l5)
        }
    }
    
    public static final boolean ck(final String s) {
        if (s != null) {
            final String a = ji.document.ad.a(s);
            synchronized (ji.util.d.l5) {
                final boolean[] array = ji.util.d.l5.get(a);
                if (array != null) {
                    // monitorexit(d.l5)
                    return array[1];
                }
            }
            // monitorexit(d.l5)
        }
        return false;
    }
    
    public static final boolean cl(final String s) {
        if (s != null) {
            final String a = ji.document.ad.a(s);
            synchronized (ji.util.d.l5) {
                final boolean[] array = ji.util.d.l5.get(a);
                if (array != null) {
                    // monitorexit(d.l5)
                    return array[0];
                }
            }
            // monitorexit(d.l5)
        }
        return false;
    }
    
    public static Window b(final Component component, final String s) {
        if (ji.util.i.c(124)) {
            ji.io.h.d(s, "getParentFrameOrDialogOrNewFrame(): component ".concat(String.valueOf(String.valueOf(component))));
        }
        Window f = null;
        if (ck(s)) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(s, "getParentFrameOrDialogOrNewFrame(): creating new frame because we're shutting down");
            }
            f = new Frame();
        }
        else if (ji.util.i.c(124)) {
            ji.io.h.d(s, "getParentFrameOrDialogOrNewFrame(): not creating new frame");
        }
        if (f == null && ak(s) && component != null && !component.isShowing()) {
            f = new Frame();
        }
        if (f == null) {
            f = f(component);
        }
        if (f == null) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(s, "getParentFrameOrDialogOrNewFrame(): creating new frame");
            }
            f = new Frame();
        }
        return f;
    }
    
    public static final void a(final Object o, final int n) {
        m m = null;
        try {
            if (o != null) {
                m = new m(o);
                final Method b = m.b("setCaretPosition");
                if (b != null) {
                    m.a(b, new Integer(n));
                }
            }
        }
        catch (Exception ex) {}
        finally {
            try {
                if (m != null) {
                    m.b();
                }
            }
            catch (Exception ex2) {}
        }
    }
    
    public static final int e(final Object o) {
        int intValue = 0;
        m m = null;
        try {
            if (o != null) {
                m = new m(o);
                final Method b = m.b("getCaretPosition");
                if (b != null) {
                    final Object a = m.a(b);
                    if (a != null && a instanceof Integer) {
                        intValue = (int)a;
                    }
                }
            }
        }
        catch (Exception ex) {}
        finally {
            try {
                if (m != null) {
                    m.b();
                }
            }
            catch (Exception ex2) {}
        }
        return intValue;
    }
    
    public static final c e(final byte[] array) {
        c c = null;
        try {
            int i = 0;
            String value = "";
            String value2 = "";
            int n = 1;
            while (i < array.length) {
                final char c2 = (char)array[i++];
                if (c2 < ' ') {
                    final String cm = cm(bc(value.toLowerCase()));
                    final String cm2 = cm(bc(value2));
                    if (!cm2.equals("") && !cm.equals("")) {
                        if (c == null) {
                            c = new c("paramsList");
                        }
                        c.a(cm, cm2);
                    }
                    value = "";
                    value2 = "";
                    n = 1;
                }
                else if (c2 == '=' && n != 0) {
                    n = 0;
                }
                else if (n != 0) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append("").append(c2)));
                }
                else {
                    value2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value2))).append("").append(c2)));
                }
            }
            if (!value2.equals("") && !value.equals("")) {
                final String cm3 = cm(bc(value.toLowerCase()));
                final String cm4 = cm(bc(value2));
                if (!cm4.equals("") && !cm3.equals("")) {
                    if (c == null) {
                        c = new c("paramsList2");
                    }
                    c.a(cm3, cm4);
                }
            }
        }
        catch (Exception ex) {}
        return c;
    }
    
    public static final String cm(final String s) {
        String s2 = s;
        if (s2.length() > 1) {
            final char[] charArray = s.toCharArray();
            if (charArray[0] == '\"') {
                s2 = s2.substring(1);
            }
            if (charArray[charArray.length - 1] == '\"') {
                s2 = s2.substring(0, s2.length() - 1);
            }
        }
        return s2;
    }
    
    public static final int a(final int n, final ad ad) {
        int n2 = 0;
        final boolean[] e = e(ad);
        if (e[n]) {
            n2 = n;
        }
        else {
            for (int i = 0; i < e.length; ++i) {
                if (e[i]) {
                    n2 = i;
                    break;
                }
            }
        }
        return n2;
    }
    
    public static final int d(final ad ad) {
        int n = 0;
        final boolean[] e = e(ad);
        for (int i = 0; i < e.length; ++i) {
            if (e[i]) {
                ++n;
            }
        }
        return n;
    }
    
    public static final boolean b(final int n, final ad ad) {
        final boolean[] e = e(ad);
        return n >= 0 && n < e.length && e[n];
    }
    
    private static boolean[] e(final ad ad) {
        return new boolean[] { ji.util.i.c(116), ji.util.i.c(117), ji.util.i.c(115) && ji.util.i.c(118), ji.util.i.c(115) && ji.util.i.c(119), ad.cj() };
    }
    
    public static final String a(final int n, final String s, final File file) {
        return a(n, 0, s, file);
    }
    
    private static synchronized int fp() {
        return ji.util.d.aq++;
    }
    
    private static final String a(final int n, int n2, final String s, final File file) {
        if (n2 > 100) {
            return null;
        }
        String s2 = Integer.toHexString(fp());
        String s3 = Integer.toHexString((int)System.currentTimeMillis());
        if (s2.length() < 3) {
            s2 = String.valueOf(String.valueOf("0000".substring(0, 3 - s2.length()))).concat(String.valueOf(String.valueOf(s2)));
        }
        if (s3.length() < 3) {
            s3 = String.valueOf(String.valueOf("0000".substring(0, 3 - s3.length()))).concat(String.valueOf(String.valueOf(s3)));
        }
        final File file2 = new File(file, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(n)))).append("_").append(s3).append(s2).append(".").append(s))));
        String s4;
        if (file2.exists()) {
            s4 = a(n, ++n2, s, file);
        }
        else {
            s4 = file2.getAbsolutePath();
        }
        return s4;
    }
    
    public static String cn(final String s) {
        if (s != null) {
            int n = 0;
            String s2 = "";
            int index;
            for (int n2 = 0; (index = s.indexOf("\\n", n)) > -1 && index != n2; n2 = index) {
                if (index > -1) {
                    s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n, index)))))).concat("\n");
                    n += index + 2 - n;
                }
            }
            if (n < s.length()) {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n, s.length()))));
            }
            return s2;
        }
        return null;
    }
    
    public static String f(final String s, final int n) {
        if (s != null && n > 0) {
            int n2 = 0;
            int i = n;
            String s2 = "";
            int n3 = 0;
            while (i < s.length()) {
                int lastIndex;
                while ((lastIndex = s.lastIndexOf("\n", i)) > -1 && lastIndex != n3) {
                    if (lastIndex > -1) {
                        s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n2, lastIndex + 1))));
                        final int n4 = lastIndex + 1 - n2;
                        n2 += n4;
                        i += n4;
                    }
                    n3 = lastIndex;
                }
                i = s.indexOf(" ", i);
                if (i <= -1) {
                    s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n2, s.length()))));
                    break;
                }
                s2 = String.valueOf(String.valueOf(String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n2, i)))))).concat("\n");
                n2 = i + 1;
                i += n;
            }
            if (i > -1) {
                if (i > s.length()) {
                    i = s.length();
                }
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(s.substring(n2, i))));
            }
            return s2;
        }
        return null;
    }
    
    public static String c(final int n, final int n2, final String s) {
        if (s.n()) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(271, s)))).append(": ").append(e(n, n2, s))));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(271, s)))).append(" ").append(e(n, n2, s))));
    }
    
    public static String d(final int n, final int n2, final String s) {
        if (s.n()) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(302, s)))).append(": ").append(e(n, n2, s))));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(b(302, s)))).append(" ").append(e(n, n2, s))));
    }
    
    public static String e(final int n, final int n2, final String s) {
        if (s.n()) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append("/").append(n2)));
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(" ").append(b(303, s)).append(" ").append(n2)));
    }
    
    public static final boolean co(final String s) {
        return ji.util.e.ab() && (bf() || ji.util.i.c(188)) && ji.util.i.c(206) && ji.util.e.u(s);
    }
    
    public static final String cp(String substring) {
        if (ji.util.i.c(261) && bj(substring)) {
            final int index = substring.indexOf(35);
            if (index != -1) {
                substring = substring.substring(0, index);
            }
        }
        return substring;
    }
    
    public static final long cq(final String s) {
        try {
            if (s != null) {
                long n = 55665L;
                final byte[] bytes = s.getBytes("UTF8");
                long n2 = 0L;
                final long n3 = bytes.length;
                for (int n4 = 0; n4 < n3; ++n4) {
                    final long n5 = (bytes[n4] & 0xF) ^ n >> 8;
                    n = (n5 + n) * 52845L + 22719L;
                    n2 += n5;
                }
                return Math.abs(n2);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0L;
    }
    
    public static boolean cr(final String s) {
        return !ji.util.e.av() && !dp() && em() && !ji.util.e.v(s) && !ji.util.e.t(s) && !ji.util.e.az() && ji.util.i.c(146);
    }
    
    public static String cs(final String s) {
        return a(s, ji.util.d.m1, ji.util.d.m0);
    }
    
    public static String ct(final String s) {
        return a(s, ji.util.d.m0, ji.util.d.m1);
    }
    
    private static String a(String value, final String[] array, final String[] array2) {
        if (value != null) {
            for (int i = 0; i < array.length; ++i) {
                final String s = array[i];
                final String s2 = array2[i];
                int index;
                for (int n = 0; (index = value.indexOf(s, n)) > -1; value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value.substring(0, index)))).append(s2).append(value.substring(index + s.length(), value.length())))), n = index + s2.length()) {}
            }
        }
        return value;
    }
    
    public static String cu(final String s) {
        String concat = "lic";
        if (ji.util.d.mu) {
            concat = String.valueOf(String.valueOf(concat)).concat("-server");
        }
        if (!by(ji.util.i.e(6))) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append("-").append(ji.util.i.e(6))));
        }
        return concat;
    }
    
    public static final boolean b(final String s, final boolean b, final String s2) {
        boolean b2 = s.startsWith(cu(s2).toLowerCase());
        if (b2) {
            b2 = s.endsWith(".v1".toLowerCase());
            if (!b2 && b) {
                b2 = s.toLowerCase().endsWith(".txt");
            }
        }
        return b2;
    }
    
    public static final String cv(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(cu(s)))).append(".").append("v1")));
    }
    
    public static final String cw(final String s) {
        return String.valueOf(String.valueOf(cu(s))).concat(".txt");
    }
    
    public static final String cx(final String s) {
        final String e = ji.util.i.e(7);
        if (!by(e)) {
            return e;
        }
        return ji.util.e.an();
    }
    
    public static boolean v(final String s, final String s2) {
        boolean b = false;
        if (s != null && s2 != null) {
            try {
                if (new File(s).equals(new File(s2))) {
                    b = true;
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public static final boolean fj() {
        final int d = ji.util.i.d(11);
        if (d == 0) {
            return ji.util.i.c(248);
        }
        return d == 1;
    }
    
    public static final boolean an(final int n) {
        if (!bf()) {
            return ji.util.i.c(96);
        }
        return !ao(n) && ji.util.i.c(96);
    }
    
    public static final boolean ao(final int n) {
        return ji.util.i.c(244) && (am(n) || ji.util.i.c(250));
    }
    
    public static final boolean cy(final String s) {
        return s != null && (s.equalsIgnoreCase("tif") || s.equalsIgnoreCase("tiff"));
    }
    
    public static final boolean a(String lowerCase, final String[] array) {
        if (lowerCase != null) {
            final int index = lowerCase.indexOf(47);
            if (index > -1) {
                lowerCase = lowerCase.toLowerCase();
                final String substring = lowerCase.substring(0, index);
                lowerCase.substring(index + 1);
                for (final String s : array) {
                    if (s != null) {
                        final String lowerCase2 = s.toLowerCase();
                        final int index2 = lowerCase2.indexOf(47);
                        if (index2 > -1 || index2 < lowerCase2.length() - 1) {
                            final String substring2 = lowerCase2.substring(0, index2);
                            if (lowerCase2.substring(index2 + 1).equals("*")) {
                                if (substring2.equals(substring)) {
                                    return true;
                                }
                            }
                            else if (lowerCase2.equals(lowerCase)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static final boolean cz(final String s) {
        return !by(s) && s.indexOf(47) != -1;
    }
    
    public static boolean c0(String trim) {
        if (trim != null) {
            trim = trim.trim();
            if (trim.length() > 0 && (trim.equalsIgnoreCase("yes") || trim.equalsIgnoreCase("true") || trim.equalsIgnoreCase("1"))) {
                return true;
            }
        }
        return false;
    }
    
    public static final String[] c(final String[] array) {
        final int length = array.length;
        final String[] array2 = new String[length];
        int n = 0;
        for (int i = length - 1; i >= 0; --i) {
            array2[n] = array[i];
            ++n;
        }
        return array2;
    }
    
    public static final String c1(final String s) {
        try {
            if (ji.util.d.a == null) {
                if (cy()) {
                    ji.io.h.d(s, "detect instance id");
                }
                final Class c2 = c2(s);
                if (c2 != null) {
                    if (cy()) {
                        ji.io.h.d(s, "detect 3");
                    }
                    final Object invoke = c2.getMethod("getJVMInstanceId", (Class[])null).invoke(null, (Object[])null);
                    if (invoke != null) {
                        if (invoke instanceof String) {
                            ji.util.d.a = (String)invoke;
                            if (cy()) {
                                ji.io.h.d(s, "detect 4: ".concat(String.valueOf(String.valueOf(ji.util.d.a))));
                            }
                        }
                        else if (cy()) {
                            ji.io.h.d(s, "detect 5: ");
                        }
                    }
                    else if (cy()) {
                        ji.io.h.d(s, "detect 6: ");
                    }
                }
                else if (cy()) {
                    ji.io.h.d(s, "detect 7: ");
                }
            }
            return fq();
        }
        catch (Exception ex) {
            a(ex);
            return fq();
        }
        catch (Error error) {
            a(error);
            return fq();
        }
    }
    
    public static Class c2(final String s) {
        Class<?> clazz = null;
        final Object y = y(s);
        if (y instanceof Applet) {
            if (!(y instanceof jiApplet)) {
                if (cy()) {
                    ji.io.h.d(s, "Detected parent ".concat(String.valueOf(String.valueOf(y.getClass().getName()))));
                }
                if (y.getClass().getName().endsWith("start.jiViewONE")) {
                    clazz = y.getClass();
                }
                else {
                    final ClassLoader classLoader = y.getClass().getClassLoader();
                    if (classLoader != null) {
                        try {
                            clazz = Class.forName("start.jiViewONE", true, classLoader);
                        }
                        catch (ClassNotFoundException ex) {
                            if (cy()) {
                                ji.io.h.d(s, "ClassNotFoundException detecting start.jiViewONE");
                            }
                        }
                        if (clazz != null && cy()) {
                            ji.io.h.d(s, "Detected class ".concat(String.valueOf(String.valueOf(clazz.getName()))));
                        }
                    }
                    else if (cy()) {
                        ji.io.h.d(s, "Null CL.");
                    }
                }
            }
            else if (cy()) {
                ji.io.h.d(s, "Wrong parent type, jiApplet.");
            }
        }
        else if (cy()) {
            ji.io.h.d(s, "detect 2b");
        }
        return clazz;
    }
    
    private static final String fq() {
        if (ji.util.d.a == null) {
            ji.util.d.a = "".concat(String.valueOf(String.valueOf(System.currentTimeMillis())));
        }
        return ji.util.d.a;
    }
    
    public static final String c3(final String s) {
        final String b = b(s, "+", " ");
        int i = b.indexOf("%");
        if (i >= 0) {
            String s2 = "";
            if (i > 0) {
                s2 = b.substring(0, i);
            }
            final byte[] array = { 0 };
            while (i >= 0) {
                int n = b.indexOf("%", i + 3);
                if (n < 0) {
                    n = b.length();
                }
                array[0] = (byte)(int)Integer.valueOf(b.substring(i + 1, i + 3), 16);
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new String(array))).concat(String.valueOf(String.valueOf(b.substring(i + 3, n)))))));
                i = b.indexOf("%", n);
            }
            return s2;
        }
        return b;
    }
    
    public static String c4(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (a(char1) || b(char1) || char1 == '.' || char1 == '*' || char1 == '-' || char1 == '_') {
                sb.append(char1);
            }
            else if (char1 == ' ') {
                sb.append('+');
            }
            else {
                sb.append("%");
                String s2 = Integer.toHexString(char1).toUpperCase();
                if (s2.length() < 2) {
                    s2 = "0".concat(String.valueOf(String.valueOf(s2)));
                }
                else if (s2.length() > 2) {
                    final byte[] bytes = new String(new char[] { char1 }).getBytes();
                    char c = Character.forDigit(bytes[0] >> 4 & 0xF, 16);
                    if (Character.isLetter(c)) {
                        c = Character.toUpperCase(c);
                    }
                    final char forDigit = Character.forDigit(bytes[0] & 0xF, 16);
                    if (Character.isLetter(forDigit)) {
                        c = Character.toUpperCase(forDigit);
                    }
                    s2 = String.valueOf(String.valueOf(new StringBuffer("").append(c).append(forDigit)));
                }
                sb.append(s2);
            }
        }
        return sb.toString();
    }
    
    public static final boolean a(final Graphics graphics) {
        boolean b = false;
        if (ji.util.e.av()) {
            try {
                final Class<? extends Graphics> class1 = graphics.getClass();
                final Class[] array = new Class[2];
                final Class<?> forName = Class.forName("java.awt.RenderingHints");
                final Class[] declaredClasses = forName.getDeclaredClasses();
                Class<?> clazz = null;
                for (int i = 0; i < declaredClasses.length; ++i) {
                    final Class clazz2 = declaredClasses[i];
                    if (clazz2.getName().equals("java.awt.RenderingHints$Key")) {
                        clazz = (Class<?>)clazz2;
                        break;
                    }
                }
                if (clazz != null) {
                    array[0] = clazz;
                    array[1] = ((ji.util.d.m3 == null) ? (ji.util.d.m3 = class$("java.lang.Object")) : ji.util.d.m3);
                    final Method method = class1.getMethod("setRenderingHint", (Class[])array);
                    if (method != null) {
                        method.invoke(graphics, forName.getDeclaredField("KEY_ANTIALIASING").get(null), forName.getDeclaredField("VALUE_ANTIALIAS_ON").get(null));
                        b = true;
                    }
                }
            }
            catch (Throwable t) {
                a(t);
            }
        }
        return b;
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        ji.util.d.a = null;
        ji.util.d.b = true;
        ji.util.d.c = false;
        ji.util.d.d = false;
        ji.util.d.e = false;
        ji.util.d.f = new c("jiUtil1");
        ji.util.d.g = new c("jiUtil15");
        ji.util.d.h = new Color(255, 255, 198);
        ji.util.d.i = new Color(107, 107, 107);
        ji.util.d.j = new Color(99, 99, 99);
        ji.util.d.k = new Hashtable();
        ji.util.d.l = new bu("jiUtil");
        ji.util.d.m = new dr();
        ji.util.d.n = 15;
        ji.util.d.o = null;
        ji.util.d.p = false;
        ji.util.d.q = false;
        ji.util.d.r = true;
        ji.util.d.s = true;
        ji.util.d.t = false;
        ji.util.d.u = false;
        ji.util.d.v = false;
        ji.util.d.w = false;
        ji.util.d.x = false;
        ji.util.d.y = 0L;
        ji.util.d.z = 1048576;
        ji.util.d.aa = 2097152;
        ji.util.d.ab = false;
        ji.util.d.ac = ji.util.d.z;
        ji.util.d.ad = 15 * ji.util.d.ac;
        ji.util.d.ae = ji.util.d.ad / 4;
        ji.util.d.af = 10000L;
        ji.util.d.ag = 0.0;
        ji.util.d.ah = 0L;
        ji.util.d.ai = 512000;
        ji.util.d.aj = 10485760;
        ji.util.d.ak = false;
        ji.util.d.al = false;
        ji.util.d.am = false;
        ji.util.d.an = false;
        ji.util.d.ao = false;
        ji.util.d.ap = false;
        ji.util.d.aq = 1;
        ji.util.d.ar = 2097152;
        ji.util.d.as = "jpd.zip";
        ji.util.d.at = "res1.gif";
        ji.util.d.au = "res2.gif";
        ji.util.d.av = "res3.gif";
        ji.util.d.aw = "res4.gif";
        ji.util.d.ax = "res5.gif";
        ji.util.d.ay = false;
        ji.util.d.az = true;
        ji.util.d.a0 = true;
        ji.util.d.a1 = true;
        ji.util.d.a2 = true;
        ji.util.d.a3 = false;
        ji.util.d.a4 = true;
        ji.util.d.a5 = false;
        ji.util.d.a6 = "";
        ji.util.d.a7 = true;
        ji.util.d.a8 = true;
        ji.util.d.a9 = false;
        ji.util.d.ba = false;
        ji.util.d.bb = false;
        ji.util.d.bc = true;
        ji.util.d.bd = "";
        ji.util.d.be = 0L;
        ji.util.d.bf = "";
        ji.util.d.bg = "";
        ji.util.d.bh = 0L;
        ji.util.d.bi = "";
        ji.util.d.bj = 0L;
        ji.util.d.bk = 0L;
        ji.util.d.bl = 0L;
        ji.util.d.bm = 0;
        ji.util.d.bn = 0;
        ji.util.d.bo = 0;
        ji.util.d.bp = 0;
        ji.util.d.bq = 0;
        ji.util.d.br = 0;
        ji.util.d.bs = 0;
        ji.util.d.bt = 0L;
        ji.util.d.bu = 0L;
        ji.util.d.bv = 0L;
        ji.util.d.bw = 0;
        ji.util.d.bx = 0;
        ji.util.d.by = 0;
        ji.util.d.bz = 0;
        ji.util.d.b0 = 0;
        ji.util.d.b1 = 0;
        ji.util.d.b2 = 0;
        ji.util.d.b3 = 0;
        ji.util.d.b4 = 0;
        ji.util.d.b5 = 0;
        ji.util.d.b6 = 0;
        ji.util.d.b7 = 0;
        ji.util.d.b8 = 0;
        ji.util.d.b9 = 0;
        ji.util.d.ca = 0;
        ji.util.d.cb = 0;
        ji.util.d.cc = 0;
        ji.util.d.cd = 0;
        ji.util.d.ce = 0;
        ji.util.d.cf = 0;
        ji.util.d.cg = 0;
        ji.util.d.ch = 0;
        ji.util.d.ci = 0;
        ji.util.d.cj = 0;
        ji.util.d.ck = 0;
        ji.util.d.cl = 0;
        ji.util.d.cm = 0;
        ji.util.d.cn = 0;
        ji.util.d.co = 0;
        ji.util.d.cp = 0;
        ji.util.d.cq = 0;
        ji.util.d.cr = 0;
        ji.util.d.cs = 0;
        ji.util.d.ct = 0;
        ji.util.d.cu = 0;
        ji.util.d.cv = 0;
        ji.util.d.cw = 0;
        ji.util.d.cx = 0;
        ji.util.d.cy = 0;
        ji.util.d.cz = 0;
        ji.util.d.c0 = 0;
        ji.util.d.c1 = 0L;
        ji.util.d.c2 = null;
        ji.util.d.c3 = null;
        ji.util.d.c4 = null;
        ji.util.d.c5 = false;
        ji.util.d.c6 = true;
        ji.util.d.c7 = false;
        ji.util.d.c8 = 0;
        ji.util.d.da = false;
        ji.util.d.db = true;
        ji.util.d.dc = false;
        ji.util.d.dd = false;
        ji.util.d.de = false;
        ji.util.d.df = false;
        ji.util.d.dg = 0L;
        ji.util.d.dh = false;
        ji.util.d.di = false;
        ji.util.d.dj = false;
        ji.util.d.dk = false;
        ji.util.d.dl = false;
        ji.util.d.dm = false;
        ji.util.d.dn = false;
        ji.util.d.do = 0;
        ji.util.d.dp = false;
        ji.util.d.dq = 0;
        ji.util.d.dr = 256;
        ji.util.d.ds = 256;
        ji.util.d.dt = 256;
        ji.util.d.du = ji.util.d.dr;
        ji.util.d.dv = ji.util.d.ds;
        ji.util.d.dw = ji.util.d.dt;
        ji.util.d.dx = 256;
        ji.util.d.dy = 256;
        ji.util.d.dz = 256;
        ji.util.d.d0 = 512;
        ji.util.d.d1 = 512;
        ji.util.d.d2 = 512;
        ji.util.d.d3 = false;
        ji.util.d.d4 = 0;
        ji.util.d.d5 = 0;
        ji.util.d.d6 = 0;
        ji.util.d.d7 = 0;
        ji.util.d.d8 = true;
        ji.util.d.d9 = false;
        ji.util.d.ea = false;
        ji.util.d.eb = false;
        ji.util.d.ec = 16;
        ji.util.d.ed = 2097152L;
        ji.util.d.ee = 20971520L;
        ji.util.d.ef = false;
        ji.util.d.eg = true;
        ji.util.d.eh = true;
        ji.util.d.ei = true;
        ji.util.d.ej = 0;
        ji.util.d.ek = 0;
        ji.util.d.el = false;
        ji.util.d.em = false;
        ji.util.d.en = false;
        ji.util.d.eo = 60000;
        ji.util.d.ep = 2;
        ji.util.d.eq = true;
        ji.util.d.er = false;
        ji.util.d.es = true;
        ji.util.d.et = null;
        ji.util.d.eu = false;
        ji.util.d.ev = "NullPointer";
        ji.util.d.ew = null;
        ji.util.d.ex = null;
        ji.util.d.ey = null;
        ji.util.d.ez = false;
        ji.util.d.e0 = false;
        ji.util.d.e1 = false;
        ji.util.d.e2 = null;
        ji.util.d.e3 = 0;
        ji.util.d.e4 = 0;
        ji.util.d.e5 = true;
        ji.util.d.e6 = false;
        ji.util.d.e7 = false;
        ji.util.d.e8 = false;
        ji.util.d.e9 = false;
        ji.util.d.fa = false;
        ji.util.d.fb = false;
        ji.util.d.fc = false;
        ji.util.d.fd = false;
        ji.util.d.fe = false;
        ji.util.d.ff = false;
        ji.util.d.fg = false;
        ji.util.d.fh = false;
        ji.util.d.fi = false;
        ji.util.d.fj = false;
        ji.util.d.fk = false;
        ji.util.d.fl = false;
        ji.util.d.fm = false;
        ji.util.d.fn = false;
        ji.util.d.fo = false;
        ji.util.d.fp = false;
        ji.util.d.fq = 0;
        ji.util.d.fr = true;
        ji.util.d.fs = false;
        ji.util.d.ft = false;
        ji.util.d.fu = false;
        ji.util.d.fv = true;
        ji.util.d.fw = 0L;
        ji.util.d.fx = "";
        ji.util.d.fy = null;
        ji.util.d.fz = false;
        ji.util.d.f0 = false;
        ji.util.d.f1 = false;
        ji.util.d.f2 = false;
        ji.util.d.f3 = false;
        ji.util.d.f4 = false;
        ji.util.d.f5 = false;
        ji.util.d.f6 = true;
        ji.util.d.f7 = null;
        ji.util.d.f8 = null;
        ji.util.d.f9 = null;
        ji.util.d.ga = null;
        ji.util.d.gb = 75;
        ji.util.d.gc = 105;
        ji.util.d.gd = 0.5;
        ji.util.d.ge = 0.5;
        ji.util.d.gf = false;
        ji.util.d.gg = false;
        ji.util.d.gh = false;
        ji.util.d.gi = 3;
        ji.util.d.gj = null;
        ji.util.d.gk = null;
        ji.util.d.gl = null;
        ji.util.d.gm = null;
        ji.util.d.gn = null;
        ji.util.d.go = Color.black;
        ji.util.d.gp = false;
        ji.util.d.gq = null;
        ji.util.d.gr = null;
        ji.util.d.gs = null;
        ji.util.d.gt = false;
        ji.util.d.gu = false;
        ji.util.d.gv = false;
        ji.util.d.gw = 128;
        ji.util.d.gx = null;
        ji.util.d.gy = 0;
        ji.util.d.gz = false;
        ji.util.d.g0 = true;
        ji.util.d.g1 = false;
        ji.util.d.g2 = false;
        ji.util.d.g3 = 0L;
        ji.util.d.g4 = 0L;
        ji.util.d.g5 = 0L;
        ji.util.d.g6 = 0L;
        ji.util.d.g7 = null;
        ji.util.d.g8 = 0L;
        ji.util.d.g9 = false;
        ji.util.d.ha = true;
        ji.util.d.hb = false;
        ji.util.d.hc = false;
        ji.util.d.hd = false;
        ji.util.d.he = true;
        ji.util.d.hf = false;
        ji.util.d.hg = false;
        ji.util.d.hh = true;
        ji.util.d.hi = false;
        ji.util.d.hj = 0;
        ji.util.d.hk = 0;
        ji.util.d.hl = Color.yellow;
        ji.util.d.hm = null;
        ji.util.d.hn = null;
        ji.util.d.ho = null;
        ji.util.d.hp = null;
        ji.util.d.hq = null;
        ji.util.d.hr = null;
        ji.util.d.hs = null;
        ji.util.d.ht = null;
        ji.util.d.hu = null;
        ji.util.d.hv = "ISO-8859-1";
        ji.util.d.hw = false;
        ji.util.d.hx = 1;
        ji.util.d.hy = null;
        ji.util.d.hz = null;
        ji.util.d.h0 = 0;
        ji.util.d.h1 = null;
        ji.util.d.h2 = null;
        ji.util.d.h3 = null;
        ji.util.d.h4 = null;
        ji.util.d.h5 = null;
        ji.util.d.h6 = null;
        ji.util.d.h7 = null;
        ji.util.d.h8 = null;
        ji.util.d.h9 = false;
        ji.util.d.ia = true;
        ji.util.d.ib = false;
        ji.util.d.ic = null;
        ji.util.d.id = null;
        ji.util.d.ie = false;
        ji.util.d.if = -16777216;
        ji.util.d.ig = 0;
        ji.util.d.ih = 16711680;
        ji.util.d.ii = 0;
        ji.util.d.ij = 65280;
        ji.util.d.ik = 0;
        ji.util.d.il = 255;
        ji.util.d.im = 0;
        ji.util.d.in = false;
        ji.util.d.io = false;
        ji.util.d.ip = false;
        ji.util.d.iq = true;
        ji.util.d.ir = true;
        ji.util.d.is = false;
        ji.util.d.it = false;
        ji.util.d.iu = true;
        ji.util.d.iv = true;
        ji.util.d.iw = 0;
        ji.util.d.ix = 0;
        ji.util.d.iy = 0;
        ji.util.d.iz = 0;
        ji.util.d.i0 = false;
        ji.util.d.i1 = true;
        ji.util.d.i2 = true;
        ji.util.d.i3 = false;
        ji.util.d.i4 = false;
        ji.util.d.i5 = "<P>";
        ji.util.d.i6 = false;
        ji.util.d.i7 = false;
        ji.util.d.i8 = Color.gray;
        ji.util.d.i9 = Color.lightGray;
        ji.util.d.ja = "SELF";
        ji.util.d.jb = 0L;
        ji.util.d.jc = 0L;
        ji.util.d.jd = null;
        ji.util.d.je = null;
        ji.util.d.jf = null;
        ji.util.d.jg = null;
        ji.util.d.jh = null;
        ji.util.d.ji = false;
        ji.util.d.jj = false;
        ji.util.d.jk = false;
        ji.util.d.jl = -1;
        ji.util.d.jm = 65536;
        ji.util.d.jn = null;
        ji.util.d.jo = 1;
        ji.util.d.jp = null;
        ji.util.d.jq = 330L;
        ji.util.d.jr = false;
        ji.util.d.js = true;
        ji.util.d.jt = 3;
        ji.util.d.ju = true;
        ji.util.d.jv = true;
        ji.util.d.jw = false;
        ji.util.d.jx = true;
        ji.util.d.jy = false;
        ji.util.d.jz = true;
        ji.util.d.j0 = true;
        ji.util.d.j1 = true;
        ji.util.d.j2 = 1000;
        ji.util.d.j3 = 300;
        ji.util.d.j4 = "<empty>";
        ji.util.d.j5 = null;
        ji.util.d.j6 = null;
        ji.util.d.j7 = null;
        ji.util.d.j8 = false;
        ji.util.d.j9 = null;
        ji.util.d.ka = "id=";
        ji.util.d.kb = "page=";
        ji.util.d.kc = null;
        ji.util.d.kd = false;
        ji.util.d.ke = false;
        ji.util.d.kf = false;
        ji.util.d.kg = false;
        ji.util.d.kh = false;
        ji.util.d.ki = true;
        ji.util.d.kj = null;
        ji.util.d.kk = 18.0;
        ji.util.d.kl = null;
        ji.util.d.km = null;
        ji.util.d.kn = true;
        ji.util.d.ko = false;
        ji.util.d.kp = false;
        ji.util.d.kq = false;
        ji.util.d.kr = false;
        ji.util.d.ks = 8;
        ji.util.d.kt = 1.0;
        ji.util.d.ku = 0;
        ji.util.d.kv = 0;
        ji.util.d.kw = false;
        ji.util.d.kx = false;
        ji.util.d.ky = false;
        ji.util.d.kz = true;
        ji.util.d.k0 = false;
        ji.util.d.k1 = false;
        ji.util.d.k2 = null;
        ji.util.d.k3 = 3;
        ji.util.d.k4 = 64;
        ji.util.d.k5 = 128;
        ji.util.d.k6 = 512000;
        ji.util.d.k7 = 1048576;
        ji.util.d.k8 = -1;
        ji.util.d.k9 = 200;
        ji.util.d.la = 32;
        ji.util.d.lb = 10;
        ji.util.d.lc = 24;
        ji.util.d.ld = true;
        ji.util.d.le = 10485760;
        ji.util.d.lf = true;
        ji.util.d.lg = null;
        ji.util.d.lh = null;
        ji.util.d.li = null;
        ji.util.d.lj = "<N>";
        ji.util.d.lk = "<N>";
        ji.util.d.ll = null;
        ji.util.d.lm = null;
        ji.util.d.ln = null;
        ji.util.d.lo = false;
        ji.util.d.lp = null;
        ji.util.d.lq = null;
        ji.util.d.lr = 2;
        ji.util.d.ls = true;
        ji.util.d.lt = 0L;
        ji.util.d.lu = 0L;
        ji.util.d.lv = true;
        ji.util.d.lw = true;
        ji.util.d.lx = false;
        ji.util.d.ly = false;
        ji.util.d.lz = 24;
        ji.util.d.l0 = 5;
        ji.util.d.l1 = null;
        ji.util.d.l2 = new c("jiUtilParents");
        ji.util.d.l3 = null;
        ji.util.d.l4 = null;
        ji.util.d.l5 = new Hashtable();
        ji.util.d.l6 = new c("jiUtilParents");
        ji.util.d.l7 = new c("jiUtilParentInvokes");
        ji.util.d.l8 = null;
        ji.util.d.l9 = null;
        ji.util.d.ma = null;
        ji.util.d.mb = null;
        ji.util.d.mc = null;
        ji.util.d.md = null;
        ji.util.d.me = 0L;
        ji.util.d.mf = 300L;
        ji.util.d.mg = 1;
        ji.util.d.mh = null;
        ji.util.d.mi = 0L;
        ji.util.d.mj = 16777216L;
        ji.util.d.mk = 8388608L;
        ji.util.d.ml = -1;
        ji.util.d.mm = false;
        ji.util.d.mn = -1;
        ji.util.d.mo = false;
        ji.util.d.mp = null;
        ji.util.d.mq = new Color(245, 245, 245);
        ji.util.d.mr = true;
        ji.util.d.ms = 1000;
        ji.util.d.mt = new el();
        ji.util.d.mu = false;
        ji.util.d.mv = null;
        ji.util.d.mw = 0L;
        ji.util.d.mx = null;
        ji.util.d.my = 1.0;
        ji.util.d.mz = new Object();
        m0 = new String[] { "&", "<", ">", "\"" };
        m1 = new String[] { "&amp;", "&lt;", "&gt;", "&quot;" };
    }
}
