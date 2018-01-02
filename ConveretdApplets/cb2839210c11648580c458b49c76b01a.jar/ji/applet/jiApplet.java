// 
// Decompiled by Procyon v0.5.30
// 

package ji.applet;

import ji.sec.f;
import ji.io.jiLogger;
import ji.net.cookie.bc;
import ji.annotate.dg;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.awt.Container;
import ji.v1event.a2;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.awt.SystemColor;
import ji.annotate.gj;
import java.awt.Graphics;
import ji.adjustment.eh;
import ji.v1event.ah;
import ji.util.ch;
import java.io.InputStream;
import java.net.URLConnection;
import ji.res.s;
import ji.res.ab;
import ji.ext.cr;
import ji.font.j;
import ji.util.cn;
import ji.filter.pdf.ci;
import ji.res.z;
import ji.util.y;
import ji.v1event.af;
import ji.res.ay;
import ji.sec.g;
import java.util.StringTokenizer;
import ji.document.bd;
import java.awt.event.ActionEvent;
import java.awt.Point;
import ji.v1event.a3;
import java.awt.Frame;
import ji.io.q;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import ji.document.cl;
import ji.util.r;
import ji.applet.support.cc;
import java.awt.Component;
import ji.applet.support.b9;
import java.applet.AppletContext;
import ji.util.m;
import ji.res.aa;
import ji.io.p;
import ji.io.ac;
import ji.io.h;
import ji.util.e;
import ji.util.d;
import ji.util.i;
import ji.secure.EncryptedParameters;
import ji.ext.cb;
import ji.applet.support.cd;
import ji.ext.jiQuickStart;
import ji.awt.c;
import java.util.Vector;
import java.awt.Dimension;
import ji.util.ba;
import ji.awt.bb;
import java.awt.Color;
import java.net.URL;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.applet.Applet;
import ji.document.ad;
import java.awt.Window;
import ji.v1event.b;
import ji.v1base.jiAppletContainer;

public class jiApplet extends jiAppletContainer implements b
{
    public static final boolean SHUTDOWN_DIALOG_CODE_ON = true;
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    private int a;
    private int b;
    private Window c;
    public static final Object EventProcessorLOCK;
    public static final int FULLPAGE = 0;
    public static final int THUMBS_WEST = 1;
    public static final int THUMBS_NORTH = 2;
    public static final int THUMBS_EAST = 3;
    public static final int THUMBS_SOUTH = 4;
    public static final int THUMBS_ONLY = 5;
    public static final int TWO_PAGE = 6;
    public static final String PARAM_ENCRYPTED_PARAMETERS = "encConfig";
    public static final int SCALE_FTOH = 0;
    public static final int SCALE_FTOW = 1;
    public static final int SCALE_BEST_FIT = 2;
    public static final int SCALE_ZOOM = 3;
    public static final int ROTATION_0 = 0;
    public static final int ROTATION_90 = 90;
    public static final int ROTATION_180 = 180;
    public static final int ROTATION_270 = 270;
    public static final int FLIP_NONE = 0;
    public static final int FLIP_H = 1;
    public static final int FLIP_V = 2;
    public static final int FLIP_HV = 3;
    ad d;
    private Applet e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;
    private String aa;
    private String ab;
    private String ac;
    private String ad;
    private String[] ae;
    private String[] af;
    private String[] ag;
    private String[] ah;
    private String[] ai;
    private String[] aj;
    private String ak;
    private String al;
    private String am;
    private String an;
    private String[] ao;
    private String ap;
    private String aq;
    private String ar;
    private String as;
    private String at;
    private String au;
    private String av;
    private String aw;
    private String ax;
    private String ay;
    private String az;
    private String a0;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String a7;
    private String a8;
    private String a9;
    private String ba;
    private String[] bb;
    private String[] bc;
    private String[] bd;
    private String be;
    private String bf;
    private String bg;
    private String bh;
    private String bi;
    private String bj;
    private String[] bk;
    private int bl;
    private String[] bm;
    private String[] bn;
    private String bo;
    private String[] bp;
    private String[] bq;
    private String br;
    private String bs;
    private String bt;
    private String[] bu;
    private String[] bv;
    private String[] bw;
    private String[] bx;
    private String[] by;
    private String[] bz;
    private String[] b0;
    private String b1;
    private String b2;
    private String b3;
    private String b4;
    private String b5;
    private String b6;
    private String b7;
    private String b8;
    private String b9;
    private String ca;
    private String cb;
    private String cc;
    private String cd;
    private String ce;
    private String cf;
    private String cg;
    private String ch;
    private String ci;
    private String cj;
    private String ck;
    private String cl;
    private String cm;
    private String cn;
    private String co;
    private String cp;
    private String cq;
    private String cr;
    private String cs;
    private String ct;
    private String cu;
    private String cv;
    private String cw;
    private String cx;
    private String cy;
    private String cz;
    private String c0;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String[] c5;
    private String c6;
    private String c7;
    private String c8;
    private String c9;
    private String da;
    private String db;
    private String dc;
    private String dd;
    private String de;
    private String df;
    private String dg;
    private String dh;
    private String di;
    private String dj;
    private String dk;
    private String dl;
    private String dm;
    private String dn;
    private String do;
    private String dp;
    private String dq;
    private String dr;
    private String ds;
    private String dt;
    private String du;
    private String dv;
    private String dw;
    private String dx;
    private String dy;
    private String dz;
    private String d0;
    private String d1;
    private String d2;
    private String d3;
    private String d4;
    private String d5;
    private String d6;
    private String d7;
    private String d8;
    private String d9;
    private String ea;
    private String eb;
    private String ec;
    private String ed;
    private String ee;
    private String ef;
    private String eg;
    private String eh;
    private String ei;
    private String ej;
    private String ek;
    private String el;
    private String em;
    private String en;
    private String eo;
    private String ep;
    private String eq;
    private String er;
    private String es;
    private String et;
    private String eu;
    private String ev;
    private String ew;
    private String ex;
    private String ey;
    private String ez;
    private String e0;
    private String e1;
    private String e2;
    private String e3;
    private String e4;
    private String e5;
    private String e6;
    private String e7;
    private String e8;
    private String e9;
    private String fa;
    private String fb;
    private String fc;
    private String fd;
    private String fe;
    private String ff;
    private String fg;
    private String fh;
    private String fi;
    private String fj;
    private String fk;
    private String fl;
    private String fm;
    private String fn;
    private String fo;
    private String fp;
    private String fq;
    private String fr;
    private String fs;
    private String ft;
    private String fu;
    private String fv;
    private String fw;
    private String fx;
    private String fy;
    private String fz;
    private String f0;
    private String f1;
    private String f2;
    private String f3;
    private String f4;
    private String f5;
    private String f6;
    private String f7;
    private String f8;
    private String f9;
    private String ga;
    private String gb;
    private String gc;
    private String gd;
    private String ge;
    private String gf;
    private String gg;
    private String gh;
    private String gi;
    private String gj;
    private String gk;
    private String gl;
    private String gm;
    private String gn;
    private String go;
    private String gp;
    private String gq;
    private String gr;
    private String gs;
    private String gt;
    private String gu;
    private String gv;
    private String gw;
    private String gx;
    private String gy;
    private String gz;
    private String g0;
    private String g1;
    private String g2;
    private String g3;
    private String g4;
    private String g5;
    private String g6;
    private String g7;
    private String g8;
    private String g9;
    private String ha;
    private String hb;
    private String hc;
    private String hd;
    private String he;
    private String hf;
    private String hg;
    private String hh;
    private String hi;
    private String hj;
    private String hk;
    private String hl;
    private String hm;
    private String hn;
    private String ho;
    private String hp;
    private String hq;
    private String hr;
    private String hs;
    private String ht;
    private String hu;
    private String hv;
    private String hw;
    private String hx;
    private String hy;
    private String hz;
    private String h0;
    private String h1;
    private String h2;
    private String h3;
    private String h4;
    private String h5;
    private String h6;
    private String h7;
    private String h8;
    private String h9;
    private String ia;
    private String ib;
    private String ic;
    private String id;
    private String ie;
    private String if;
    private String ig;
    private String ih;
    private String ii;
    private String ij;
    private String ik;
    private String il;
    private String im;
    private String in;
    private String io;
    private String ip;
    private String iq;
    private String ir;
    private String is;
    private String it;
    private String iu;
    private boolean iv;
    private boolean iw;
    private boolean ix;
    private boolean iy;
    private int iz;
    private String i0;
    private String i1;
    private String i2;
    private String i3;
    private String i4;
    private String i5;
    private String i6;
    private String i7;
    private String i8;
    private String i9;
    private String ja;
    private String jb;
    private String jc;
    private String jd;
    private String je;
    private String jf;
    private String jg;
    private String jh;
    private String ji;
    private String[] jj;
    private String jk;
    private String jl;
    private String jm;
    private String jn;
    private String jo;
    private String jp;
    private String jq;
    private String jr;
    private String js;
    private String jt;
    private boolean ju;
    private boolean jv;
    private String jw;
    private String jx;
    private String jy;
    private String jz;
    private String j0;
    private String j1;
    private String j2;
    private String j3;
    private String j4;
    private String j5;
    private String j6;
    private String j7;
    private String j8;
    private String j9;
    private String ka;
    private String kb;
    private String kc;
    private String kd;
    private String ke;
    private String kf;
    private String kg;
    private String kh;
    private String ki;
    private String kj;
    private String kk;
    private String kl;
    private String km;
    private String kn;
    private String ko;
    private String kp;
    private String kq;
    private String kr;
    private String ks;
    private String kt;
    private String ku;
    private String kv;
    private String kw;
    private String kx;
    private String ky;
    private String kz;
    private String k0;
    private String k1;
    private String k2;
    private String k3;
    private String k4;
    private String k5;
    private String k6;
    private String k7;
    private String k8;
    private String k9;
    private String la;
    private String lb;
    private String lc;
    private String ld;
    private String le;
    private String lf;
    private String lg;
    private String lh;
    private String li;
    private String lj;
    private String lk;
    private String ll;
    private String lm;
    private String ln;
    private String lo;
    private String lp;
    private String lq;
    private String lr;
    private String ls;
    private String lt;
    private String lu;
    private String lv;
    private String lw;
    private String lx;
    private String ly;
    private String lz;
    private String l0;
    private String l1;
    private String l2;
    private String l3;
    private String l4;
    private String l5;
    private String l6;
    private String l7;
    private String l8;
    private String l9;
    private String ma;
    private String mb;
    private String mc;
    private String md;
    private String me;
    private String mf;
    private String mg;
    private String mh;
    private String mi;
    private String mj;
    private String mk;
    private String ml;
    private String mm;
    private String mn;
    private String mo;
    private String mp;
    private String mq;
    private String mr;
    private String ms;
    private String mt;
    private String mu;
    private String mv;
    private String mw;
    private String mx;
    private String my;
    private String mz;
    private String m0;
    private String m1;
    private String m2;
    private String m3;
    private String m4;
    private String m5;
    private String m6;
    private String m7;
    private String m8;
    private String m9;
    private String na;
    private String nb;
    private String nc;
    private String nd;
    private String ne;
    private String nf;
    private String ng;
    private String nh;
    private String ni;
    private String nj;
    private String nk;
    private String nl;
    private String nm;
    private String nn;
    private String no;
    private String np;
    private String nq;
    private String nr;
    private String ns;
    private String nt;
    private String nu;
    private String nv;
    private String nw;
    private String nx;
    private String ny;
    private String nz;
    private String n0;
    private String n1;
    private String n2;
    private String n3;
    private String n4;
    private String n5;
    private String n6;
    private String n7;
    private String n8;
    private Hashtable n9;
    private String oa;
    private String ob;
    private String oc;
    private String od;
    private String oe;
    private String of;
    private String og;
    private String oh;
    private String oi;
    private String oj;
    private String ok;
    private String ol;
    private String om;
    private String on;
    private String oo;
    private String op;
    private String oq;
    private String or;
    private String os;
    private String ot;
    private String ou;
    private String ov;
    private String ow;
    private String ox;
    private String oy;
    private String oz;
    private String[] o0;
    private String o1;
    private String o2;
    private String o3;
    private String o4;
    private boolean o5;
    private String o6;
    private String o7;
    private String o8;
    private String o9;
    private String pa;
    private String pb;
    private String pc;
    private String pd;
    private String pe;
    private String[] pf;
    private String[] pg;
    private String ph;
    private String pi;
    private String pj;
    private String pk;
    private String pl;
    private int pm;
    private String pn;
    private String po;
    private String pp;
    private String pq;
    private String pr;
    private String[] ps;
    private String pt;
    private String pu;
    private String pv;
    private String pw;
    private String px;
    private String[] py;
    private String[] pz;
    private String p0;
    private String p1;
    private String p2;
    private String p3;
    private String p4;
    private String p5;
    private String p6;
    private String p7;
    private String[] p8;
    private String[] p9;
    private String[] qa;
    private String qb;
    private String qc;
    private String qd;
    private String qe;
    private String qf;
    private String qg;
    private boolean qh;
    private int qi;
    private int qj;
    private int qk;
    private int ql;
    private String qm;
    private String qn;
    private String qo;
    private String qp;
    private String qq;
    private String qr;
    gu qs;
    private String qt;
    private String qu;
    private Rectangle qv;
    Object[] qw;
    URL[] qx;
    String[] qy;
    String[] qz;
    Object[] q0;
    String[] q1;
    Color[] q2;
    String[] q3;
    Color[] q4;
    String[] q5;
    Color[] q6;
    int[] q7;
    private String q8;
    private String q9;
    private String ra;
    private String rb;
    private String rc;
    private String rd;
    private String re;
    private String rf;
    private String rg;
    private String rh;
    private String ri;
    private String rj;
    private String rk;
    private String rl;
    private String rm;
    private String rn;
    private bb ro;
    private ba rp;
    private static int rq;
    private static int rr;
    private static int rs;
    private static int rt;
    private boolean ru;
    private boolean rv;
    private boolean rw;
    private boolean rx;
    private boolean ry;
    private int rz;
    private ba r0;
    private boolean r1;
    private boolean r2;
    private boolean r3;
    private boolean r4;
    private boolean r5;
    private boolean r6;
    private boolean r7;
    private boolean r8;
    private Dimension r9;
    private static int sa;
    private boolean sb;
    private boolean sc;
    private boolean sd;
    private boolean se;
    private boolean sf;
    private static boolean sg;
    private boolean sh;
    private boolean si;
    private String sj;
    private String sk;
    private String sl;
    private String sm;
    private String sn;
    private String so;
    private String sp;
    private String sq;
    private String sr;
    private String ss;
    private String st;
    private String su;
    private String sv;
    private String sw;
    private String sx;
    private String sy;
    private String sz;
    private String s0;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;
    private String ta;
    private String[] tb;
    private String[] tc;
    private String[] td;
    private String[] te;
    private String[] tf;
    private String[] tg;
    private String[] th;
    private String[] ti;
    private String[] tj;
    private String[] tk;
    private String[] tl;
    private String[] tm;
    private String[] tn;
    private String[] to;
    private String[] tp;
    private String[] tq;
    private String[] tr;
    private String[] ts;
    private String[] tt;
    private String[] tu;
    private bb tv;
    private tv tw;
    private boolean tx;
    private boolean ty;
    private boolean tz;
    private boolean t0;
    private boolean t1;
    private boolean t2;
    private Vector t3;
    private c t4;
    private c t5;
    private c t6;
    private boolean t7;
    private boolean t8;
    private static boolean t9;
    private static boolean ua;
    private static boolean ub;
    private static boolean uc;
    private static boolean ud;
    private static String ue;
    private static c uf;
    private static int ug;
    private static int uh;
    private boolean ui;
    private String uj;
    private Vector uk;
    private String ul;
    private tt um;
    private String un;
    private jiQuickStart uo;
    private cd up;
    public cb java_1_3_hangEventQueuePatch;
    private EncryptedParameters uq;
    private boolean ur;
    private String us;
    private String ut;
    private String uu;
    private String uv;
    private String uw;
    private String ux;
    private String uy;
    String uz;
    private final Object u0;
    static final Object u1;
    final Object u2;
    static final Object u3;
    private static boolean u4;
    Rectangle u5;
    
    public boolean isInterfaceOK() {
        return true;
    }
    
    private Object b() {
        if (ji.util.i.c(279)) {
            return jiApplet.u1;
        }
        return this.u2;
    }
    
    public jiApplet() {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = null;
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
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = null;
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ax = null;
        this.ay = null;
        this.az = null;
        this.a0 = null;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
        this.a5 = null;
        this.a6 = ":";
        this.a7 = ",";
        this.a8 = "=";
        this.a9 = null;
        this.ba = null;
        this.bb = null;
        this.bc = null;
        this.bd = null;
        this.be = null;
        this.bf = null;
        this.bg = null;
        this.bh = null;
        this.bi = null;
        this.bj = null;
        this.bk = null;
        this.bl = 0;
        this.bm = null;
        this.bn = null;
        this.bo = null;
        this.bp = null;
        this.bq = null;
        this.br = null;
        this.bs = null;
        this.bt = null;
        this.bu = null;
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.by = null;
        this.bz = null;
        this.b0 = null;
        this.b1 = null;
        this.b2 = null;
        this.b3 = null;
        this.b4 = null;
        this.b5 = null;
        this.b6 = null;
        this.b7 = null;
        this.b8 = null;
        this.b9 = null;
        this.ca = null;
        this.cb = null;
        this.cc = null;
        this.cd = null;
        this.ce = null;
        this.cf = null;
        this.cg = null;
        this.ch = null;
        this.ci = null;
        this.cj = null;
        this.ck = null;
        this.cl = null;
        this.cm = null;
        this.cn = null;
        this.co = null;
        this.cp = null;
        this.cq = null;
        this.cr = null;
        this.cs = null;
        this.ct = null;
        this.cu = null;
        this.cv = null;
        this.cw = null;
        this.cx = null;
        this.cy = null;
        this.cz = null;
        this.c0 = null;
        this.c1 = null;
        this.c2 = null;
        this.c3 = null;
        this.c4 = null;
        this.c5 = null;
        this.c6 = null;
        this.c7 = null;
        this.c8 = null;
        this.c9 = null;
        this.da = null;
        this.db = null;
        this.dc = null;
        this.dd = null;
        this.de = null;
        this.df = null;
        this.dg = null;
        this.dh = null;
        this.di = null;
        this.dj = null;
        this.dk = null;
        this.dl = null;
        this.dm = null;
        this.dn = null;
        this.do = null;
        this.dp = null;
        this.dq = null;
        this.dr = null;
        this.ds = null;
        this.dt = null;
        this.du = null;
        this.dv = null;
        this.dw = null;
        this.dx = null;
        this.dy = null;
        this.dz = null;
        this.d0 = null;
        this.d1 = null;
        this.d2 = null;
        this.d3 = null;
        this.d4 = null;
        this.d5 = null;
        this.d6 = null;
        this.d7 = null;
        this.d8 = null;
        this.d9 = null;
        this.ea = null;
        this.eb = null;
        this.ec = null;
        this.ed = null;
        this.ee = null;
        this.ef = null;
        this.eg = null;
        this.eh = null;
        this.ei = null;
        this.ej = null;
        this.ek = null;
        this.el = null;
        this.em = null;
        this.en = null;
        this.eo = null;
        this.ep = null;
        this.eq = null;
        this.er = null;
        this.es = null;
        this.et = null;
        this.eu = null;
        this.ev = null;
        this.ew = null;
        this.ex = null;
        this.ey = null;
        this.ez = null;
        this.e0 = null;
        this.e1 = null;
        this.e2 = null;
        this.e3 = null;
        this.e4 = null;
        this.e5 = null;
        this.e6 = null;
        this.e7 = null;
        this.e8 = null;
        this.e9 = null;
        this.fa = null;
        this.fb = null;
        this.fc = null;
        this.fd = null;
        this.fe = null;
        this.ff = null;
        this.fg = null;
        this.fh = null;
        this.fi = null;
        this.fj = null;
        this.fk = null;
        this.fl = null;
        this.fm = null;
        this.fn = null;
        this.fo = null;
        this.fp = null;
        this.fq = null;
        this.fr = null;
        this.fs = null;
        this.ft = null;
        this.fu = null;
        this.fv = null;
        this.fw = null;
        this.fx = null;
        this.fy = null;
        this.fz = null;
        this.f0 = null;
        this.f1 = null;
        this.f2 = null;
        this.f3 = null;
        this.f4 = null;
        this.f5 = null;
        this.f6 = null;
        this.f7 = null;
        this.f8 = null;
        this.f9 = null;
        this.ga = null;
        this.gb = null;
        this.gc = null;
        this.gd = null;
        this.ge = null;
        this.gf = null;
        this.gg = null;
        this.gh = null;
        this.gi = null;
        this.gj = null;
        this.gk = null;
        this.gl = null;
        this.gm = null;
        this.gn = null;
        this.go = null;
        this.gp = null;
        this.gq = null;
        this.gr = null;
        this.gs = null;
        this.gt = null;
        this.gu = null;
        this.gv = null;
        this.gw = null;
        this.gx = null;
        this.gy = null;
        this.gz = null;
        this.g0 = null;
        this.g1 = null;
        this.g2 = null;
        this.g3 = null;
        this.g4 = null;
        this.g5 = null;
        this.g6 = null;
        this.g7 = null;
        this.g8 = null;
        this.g9 = null;
        this.ha = null;
        this.hb = null;
        this.hc = null;
        this.hd = null;
        this.he = null;
        this.hf = null;
        this.hg = null;
        this.hh = null;
        this.hi = null;
        this.hj = null;
        this.hk = null;
        this.hl = null;
        this.hm = null;
        this.hn = null;
        this.ho = null;
        this.hp = null;
        this.hq = null;
        this.hr = null;
        this.hs = null;
        this.ht = null;
        this.hu = null;
        this.hv = null;
        this.hw = null;
        this.hx = null;
        this.hy = null;
        this.hz = null;
        this.h0 = null;
        this.h1 = null;
        this.h2 = null;
        this.h3 = null;
        this.h4 = null;
        this.h5 = null;
        this.h6 = null;
        this.h7 = null;
        this.h8 = null;
        this.h9 = null;
        this.ia = null;
        this.ib = null;
        this.ic = null;
        this.id = null;
        this.ie = null;
        this.if = null;
        this.ig = null;
        this.ih = null;
        this.ii = null;
        this.ij = null;
        this.ik = null;
        this.il = null;
        this.im = null;
        this.in = null;
        this.io = null;
        this.ip = null;
        this.iq = null;
        this.ir = null;
        this.is = null;
        this.it = null;
        this.iu = null;
        this.iv = false;
        this.iw = false;
        this.ix = false;
        this.iy = false;
        this.iz = 0;
        this.i0 = null;
        this.i1 = null;
        this.i2 = null;
        this.i3 = null;
        this.i4 = null;
        this.i5 = null;
        this.i6 = null;
        this.i7 = null;
        this.i8 = null;
        this.i9 = null;
        this.ja = null;
        this.jb = null;
        this.jc = null;
        this.jd = null;
        this.je = null;
        this.jf = null;
        this.jg = null;
        this.jh = null;
        this.ji = null;
        this.jj = null;
        this.jk = null;
        this.jl = null;
        this.jm = null;
        this.jn = null;
        this.jo = null;
        this.jp = null;
        this.jq = null;
        this.jr = null;
        this.js = null;
        this.jt = null;
        this.ju = false;
        this.jv = false;
        this.jw = null;
        this.jx = null;
        this.jy = null;
        this.jz = null;
        this.j0 = null;
        this.j1 = null;
        this.j2 = null;
        this.j3 = null;
        this.j4 = null;
        this.j5 = null;
        this.j6 = null;
        this.j7 = null;
        this.j8 = null;
        this.j9 = null;
        this.ka = null;
        this.kb = null;
        this.kc = null;
        this.kd = null;
        this.ke = null;
        this.kf = null;
        this.kg = null;
        this.kh = null;
        this.ki = null;
        this.kj = null;
        this.kk = null;
        this.kl = null;
        this.km = null;
        this.kn = null;
        this.ko = null;
        this.kp = null;
        this.kq = null;
        this.kr = null;
        this.ks = null;
        this.kt = null;
        this.ku = null;
        this.kv = null;
        this.kw = null;
        this.kx = null;
        this.ky = null;
        this.kz = null;
        this.k0 = null;
        this.k1 = null;
        this.k2 = null;
        this.k3 = null;
        this.k4 = null;
        this.k5 = null;
        this.k6 = null;
        this.k7 = null;
        this.k8 = null;
        this.k9 = null;
        this.la = null;
        this.lb = null;
        this.lc = null;
        this.ld = null;
        this.le = null;
        this.lf = null;
        this.lg = null;
        this.lh = null;
        this.li = null;
        this.lj = null;
        this.lk = null;
        this.ll = null;
        this.lm = null;
        this.ln = null;
        this.lo = null;
        this.lp = null;
        this.lq = null;
        this.lr = null;
        this.ls = null;
        this.lt = null;
        this.lu = null;
        this.lv = null;
        this.lw = null;
        this.lx = null;
        this.ly = null;
        this.lz = null;
        this.l0 = null;
        this.l1 = null;
        this.l2 = null;
        this.l3 = null;
        this.l4 = null;
        this.l5 = null;
        this.l6 = null;
        this.l7 = null;
        this.l8 = null;
        this.l9 = null;
        this.ma = null;
        this.mb = null;
        this.mc = null;
        this.md = null;
        this.me = null;
        this.mf = null;
        this.mg = null;
        this.mh = null;
        this.mi = null;
        this.mj = null;
        this.mk = null;
        this.ml = null;
        this.mm = null;
        this.mn = null;
        this.mo = null;
        this.mp = null;
        this.mq = null;
        this.mr = null;
        this.ms = null;
        this.mt = null;
        this.mu = null;
        this.mv = null;
        this.mw = null;
        this.mx = null;
        this.my = null;
        this.mz = null;
        this.m0 = null;
        this.m1 = null;
        this.m2 = null;
        this.m3 = null;
        this.m4 = null;
        this.m5 = null;
        this.m6 = null;
        this.m7 = null;
        this.m8 = null;
        this.m9 = null;
        this.na = null;
        this.nb = null;
        this.nc = null;
        this.nd = null;
        this.ne = null;
        this.nf = null;
        this.ng = null;
        this.nh = null;
        this.ni = null;
        this.nj = null;
        this.nk = null;
        this.nl = null;
        this.nm = null;
        this.nn = null;
        this.no = null;
        this.np = null;
        this.nq = null;
        this.nr = null;
        this.ns = null;
        this.nt = null;
        this.nu = null;
        this.nv = null;
        this.nw = null;
        this.nx = null;
        this.ny = null;
        this.nz = null;
        this.n0 = null;
        this.n1 = null;
        this.n2 = null;
        this.n3 = null;
        this.n4 = null;
        this.n5 = null;
        this.n6 = null;
        this.n7 = null;
        this.n8 = null;
        this.n9 = new Hashtable();
        this.oa = null;
        this.ob = null;
        this.oc = null;
        this.od = null;
        this.oe = null;
        this.of = null;
        this.og = null;
        this.oh = null;
        this.oi = null;
        this.oj = null;
        this.ok = null;
        this.ol = null;
        this.om = null;
        this.on = null;
        this.oo = null;
        this.op = null;
        this.oq = null;
        this.or = null;
        this.os = null;
        this.ot = null;
        this.ou = null;
        this.ov = null;
        this.ow = null;
        this.ox = null;
        this.oy = null;
        this.oz = null;
        this.o0 = null;
        this.o1 = null;
        this.o2 = null;
        this.o3 = null;
        this.o4 = null;
        this.o5 = false;
        this.o6 = null;
        this.o7 = null;
        this.o8 = null;
        this.o9 = null;
        this.pa = null;
        this.pb = null;
        this.pc = null;
        this.pd = null;
        this.pe = null;
        this.pf = null;
        this.pg = null;
        this.ph = null;
        this.pi = null;
        this.pj = null;
        this.pk = null;
        this.pl = null;
        this.pm = -1;
        this.pn = null;
        this.po = null;
        this.pp = null;
        this.pq = null;
        this.pr = null;
        this.ps = null;
        this.pt = null;
        this.pu = null;
        this.pv = null;
        this.pw = null;
        this.px = null;
        this.py = null;
        this.pz = null;
        this.p0 = null;
        this.p1 = null;
        this.p2 = null;
        this.p3 = null;
        this.p4 = null;
        this.p5 = null;
        this.p6 = null;
        this.p7 = null;
        this.p8 = null;
        this.p9 = null;
        this.qa = null;
        this.qb = null;
        this.qc = null;
        this.qd = null;
        this.qe = null;
        this.qf = null;
        this.qg = null;
        this.qh = false;
        this.qi = 0;
        this.qj = 0;
        this.qk = 0;
        this.ql = 0;
        this.qm = null;
        this.qn = null;
        this.qo = null;
        this.qp = null;
        this.qq = null;
        this.qr = null;
        this.qs = null;
        this.qt = null;
        this.qu = null;
        this.qv = null;
        this.qw = null;
        this.qx = null;
        this.qy = null;
        this.qz = null;
        this.q0 = null;
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
        this.q7 = null;
        this.q8 = null;
        this.q9 = null;
        this.ra = null;
        this.rb = null;
        this.rc = null;
        this.rd = null;
        this.re = null;
        this.rf = null;
        this.rg = null;
        this.rh = null;
        this.ri = null;
        this.rj = null;
        this.rk = null;
        this.rl = "<";
        this.rm = ">";
        this.rn = "=";
        this.ro = null;
        this.rp = null;
        this.ru = true;
        this.rv = false;
        this.rw = false;
        this.rx = false;
        this.ry = false;
        this.rz = 0;
        this.r0 = null;
        this.r1 = false;
        this.r2 = true;
        this.r3 = false;
        this.r4 = false;
        this.r5 = false;
        this.r6 = false;
        this.r7 = false;
        this.r8 = false;
        this.r9 = null;
        this.sb = false;
        this.sc = false;
        this.sd = false;
        this.se = false;
        this.sf = false;
        this.sh = false;
        this.si = false;
        this.sj = null;
        this.sk = null;
        this.sl = null;
        this.sm = null;
        this.sn = null;
        this.so = null;
        this.sp = null;
        this.sq = null;
        this.sr = null;
        this.ss = null;
        this.st = null;
        this.su = null;
        this.sv = null;
        this.sw = null;
        this.sx = null;
        this.sy = null;
        this.sz = null;
        this.s0 = null;
        this.s1 = null;
        this.s2 = null;
        this.s3 = null;
        this.s4 = null;
        this.s5 = null;
        this.s6 = null;
        this.s7 = null;
        this.s8 = null;
        this.s9 = null;
        this.ta = null;
        this.tv = null;
        this.tw = null;
        this.tx = true;
        this.ty = false;
        this.tz = false;
        this.t0 = true;
        this.t1 = true;
        this.t2 = false;
        this.t3 = null;
        this.t4 = null;
        this.t5 = null;
        this.t6 = new c("htmlParamsList");
        this.t7 = false;
        this.t8 = false;
        this.ui = false;
        this.uj = null;
        this.uk = null;
        this.ul = null;
        this.um = null;
        this.un = null;
        this.uo = null;
        this.ur = false;
        this.us = null;
        this.ut = null;
        this.uu = null;
        this.uv = null;
        this.uw = null;
        this.ux = null;
        this.uy = null;
        this.uz = null;
        this.u0 = new Object();
        this.u2 = new Object();
        this.u5 = new Rectangle();
        synchronized (jiApplet.u3) {
            ++jiApplet.uh;
            ji.util.d.a((Object)this, this.uz = "-a".concat(String.valueOf(String.valueOf(jiApplet.uh))));
            if (ji.util.e.v(this.uz) || ji.util.e.t(this.uz)) {
                jiApplet.u4 = false;
            }
            ji.io.h.a(this.uz);
            this.setParentApplet(this);
            ji.util.d.cj(this.uz);
            ji.util.d.f(this.uz, false);
        }
        // monitorexit(jiApplet.u3)
    }
    
    public final int getInstanceCount() {
        return jiApplet.uh;
    }
    
    public String getInstanceId() {
        return this.uz;
    }
    
    private final String a(final String s) {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.uz))).append(":").append(s)));
    }
    
    private static final boolean b(final String s) {
        return d.av(s) || d.an(s);
    }
    
    private static final void a(final String s, final ac ac, final String s2) {
        try {
            if (ac != null) {
                ac.b(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(d.cd(false)))).append(": ").append(s2).append("\r\n"))).getBytes());
            }
            else {
                h.d(s, s2);
            }
        }
        catch (Exception ex) {}
    }
    
    public void setIsApplet(final boolean b, final String re) {
        this.sf = !b;
        this.re = re;
        if (!b) {
            ji.util.i.a(7);
        }
    }
    
    public void setInstallationPath(final String s) {
        ji.util.d.j(s);
    }
    
    public void setParentApplet(final Object o) {
        this.e = (Applet)o;
        ji.util.d.b(o, this.uz);
    }
    
    public final void setUsingCustomClassLoader(final boolean b) {
        ji.util.d.c(b);
    }
    
    public void initQuickstart() {
        if (jiQuickStart.d(this.uz)) {
            if (ji.util.i.c(105)) {
                ji.io.h.d(this.uz, "QuickStart Applet: initQuickstart");
            }
            try {
                if (this.uo == null) {
                    boolean ap = false;
                    try {
                        ap = new p(this.uz).ap(false, this.d);
                    }
                    catch (Exception ex2) {
                        if (ji.util.i.c(105)) {
                            ji.io.h.d(this.uz, "QuickStart Applet: unable to read quickstart preference");
                        }
                    }
                    this.uo = new jiQuickStart(this, this.uz, ap, !this.iy);
                }
            }
            catch (Exception ex) {
                this.uo = null;
                ex.printStackTrace();
            }
            if (ji.util.i.c(105)) {
                ji.io.h.d(this.uz, "QuickStart Applet: initQuickstart help = ".concat(String.valueOf(String.valueOf(this.uo))));
            }
        }
    }
    
    public final jiQuickStart getQuickstart() {
        return this.uo;
    }
    
    public final boolean isMainQuickstartWindow() {
        return this.iy;
    }
    
    public final int getQuickstartLaunchHandle() {
        return this.iz;
    }
    
    public void shutdownQuickstart() {
        if (jiQuickStart.d(this.uz)) {
            if (ji.util.i.c(105)) {
                ji.io.h.d(this.uz, "QuickStart Applet: shutdownQuickstart help = ".concat(String.valueOf(String.valueOf(this.uo))));
            }
            try {
                if (this.uo != null) {
                    this.uo.h(this.uz);
                }
                this.uo = null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final Applet c() {
        if (this.e != null) {
            return this.e;
        }
        return this;
    }
    
    public static void setCachedMessageInfo(final byte[] array) {
        d.b(array);
    }
    
    public static void setCachedLocalsInfo(final byte[] array) {
        d.a(array);
    }
    
    public static void setCachedVersionInfo(final byte[] array) {
        aa.a(array);
    }
    
    public static void setCachedLicInfo(final byte[] array) {
        d.c(array);
    }
    
    private final Object c(final String s) {
        final Object y = ji.util.d.y(s);
        if (y != null) {
            return y;
        }
        if (this.e == null) {
            return null;
        }
        ji.util.d.b((Object)null, s);
        ji.util.d.b((Object)this.e, s);
        final Object y2 = ji.util.d.y(s);
        if (y2 != null) {
            return y2;
        }
        ji.io.h.d(this.uz, "*** Failed to recover parent applet ***");
        return this.e;
    }
    
    private final m d(final String s) {
        final m z = ji.util.d.z(s);
        if (z != null) {
            return z;
        }
        if (this.e == null) {
            return null;
        }
        ji.util.d.b((Object)null, s);
        ji.util.d.b((Object)this.e, s);
        final m z2 = ji.util.d.z(s);
        if (z2 != null) {
            return z2;
        }
        ji.io.h.d(this.uz, "*** Failed to recover parent applet invoke ***");
        return new m(this.e);
    }
    
    public URL getCodeBase() {
        try {
            final Object c = this.c(this.uz);
            if (ji.util.i.c(78)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase: myId = ").append(this.uz).append(", getParentApplet = ").append(c))));
            }
            if (c != null && c != this) {
                try {
                    return (URL)this.d(this.uz).c("getCodeBase");
                }
                catch (Exception ex) {
                    try {
                        if (ji.util.i.c(78)) {
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", getParentAppletInvoke = ").append(ji.util.d.z(this.uz)))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", parentApplets = ").append(ji.util.d.l6))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", parentAppletsInvoke = ").append(ji.util.d.l7))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", lastParentId = ").append(ji.util.d.l8))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", lastParent = ").append(ji.util.d.l9))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", lastParentInvokeId = ").append(ji.util.d.ma))));
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase1: myId = ").append(this.uz).append(", lastParentInvoke = ").append(ji.util.d.mb))));
                            return super.getCodeBase();
                        }
                        return super.getCodeBase();
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (Exception ex3) {
            try {
                if (ji.util.i.c(78)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", getParentAppletInvoke = ").append(ji.util.d.z(this.uz)))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", parentApplets = ").append(ji.util.d.l6))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", parentAppletsInvoke = ").append(ji.util.d.l7))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", lastParentId = ").append(ji.util.d.l8))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", lastParent = ").append(ji.util.d.l9))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", lastParentInvokeId = ").append(ji.util.d.ma))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("getCodeBase2: myId = ").append(this.uz).append(", lastParentInvoke = ").append(ji.util.d.mb))));
                    return super.getCodeBase();
                }
                return super.getCodeBase();
            }
            catch (Exception ex4) {}
        }
        return super.getCodeBase();
    }
    
    public AppletContext getAppletContext() {
        try {
            final Object c = this.c(this.uz);
            if (c != null && c != this) {
                return (AppletContext)this.d(this.uz).c("getAppletContext");
            }
            return super.getAppletContext();
        }
        catch (Exception ex) {
            return super.getAppletContext();
        }
    }
    
    public URL getDocumentBase() {
        try {
            final Object c = this.c(this.uz);
            if (c != null && c != this) {
                return (URL)this.d(this.uz).c("getDocumentBase");
            }
            return super.getDocumentBase();
        }
        catch (Exception ex) {
            return super.getDocumentBase();
        }
    }
    
    public void showStatus(final String s) {
        if (!ji.util.d.ck(this.uz)) {
            try {
                final Object c = this.c(this.uz);
                if (c != null && c != this) {
                    this.d(this.uz).a("showStatus", s);
                }
                else {
                    super.showStatus(s);
                }
            }
            catch (Exception ex) {
                super.showStatus(s);
            }
        }
    }
    
    private final void d() {
        this.a(222, "useJRECacheForRepFiles");
        this.a(279, "useStaticAppletDispatchLock");
        this.a(70, "jvmShutdownFix");
        this.a(141, "enableShutdownDialogs");
        try {
            this.a(273, "plumtreeContentLengthFix");
            this.a(273, "contentLengthFix");
        }
        catch (Exception ex) {}
        try {
            this.a(274, "contentLengthIgnoreUnset");
        }
        catch (Exception ex2) {}
    }
    
    private final void e() {
        this.a(285, "disableTraceHTML");
        if (this.d4 == null) {
            try {
                this.d4 = this.g("traceNet");
            }
            catch (Exception ex) {}
            if (!ji.util.d.by(this.d4)) {
                ji.util.d.bs(p(this.d4));
            }
        }
        if (this.j8 == null) {
            try {
                this.j8 = this.g("trace");
            }
            catch (Exception ex2) {}
            if (!ji.util.d.by(this.j8)) {
                ji.util.d.a7(p(this.j8));
            }
        }
        if (this.d5 != null) {
            try {
                this.j8 = this.g("traceextendedcache");
            }
            catch (Exception ex3) {}
            if (!ji.util.d.by(this.d5)) {
                ji.util.i.a(195, p(this.d5));
            }
        }
        if (this.d7 == null) {
            try {
                this.d7 = this.g("traceDecomp");
            }
            catch (Exception ex4) {}
            if (!ji.util.d.by(this.d7)) {
                ji.util.i.a(89, p(this.d7));
            }
        }
        if (this.d6 == null) {
            try {
                this.d7 = this.g("tracePerformance");
            }
            catch (Exception ex5) {}
            if (!ji.util.d.by(this.d6)) {
                ji.util.i.a(159, p(this.d6));
            }
        }
        try {
            String s = this.getParameter("traceShutdownDialog");
            if (ji.util.d.by(s)) {
                s = this.getParameter("traceShutdownDialogs");
            }
            if (s != null && s.length() > 0) {
                ji.util.i.a(124, p(s));
            }
        }
        catch (Exception ex6) {}
        if (this.d8 == null) {
            try {
                this.d8 = this.getParameter("traceAppletLifecycle");
            }
            catch (Exception ex7) {}
            if (!ji.util.d.by(this.d8)) {
                ji.util.i.a(131, p(this.d8));
            }
        }
        if (this.en == null) {
            try {
                this.en = this.getParameter("traceException");
            }
            catch (Exception ex8) {}
            if (!ji.util.d.by(this.en)) {
                ji.util.i.a(137, p(this.en));
            }
        }
        if (this.eo == null) {
            try {
                this.eo = this.g("traceAnnotManagement");
            }
            catch (Exception ex9) {}
            if (!ji.util.d.by(this.eo)) {
                ji.util.i.a(145, p(this.eo));
            }
        }
        if (this.er == null) {
            try {
                this.er = this.getParameter("winCloseShutdownFix");
            }
            catch (Exception ex10) {}
            if (ji.util.d.by(this.er)) {
                try {
                    this.er = this.getParameter("IMCWVshutdownFix");
                }
                catch (Exception ex11) {}
            }
            if (!ji.util.d.by(this.er)) {
                ji.util.i.a(135, p(this.er));
            }
        }
        try {
            final String parameter = this.getParameter("shutdownDialogCode");
            if (!ji.util.d.by(parameter)) {
                ji.document.ad.a = p(parameter);
            }
            else {
                ji.document.ad.a = false;
            }
            if (ji.document.ad.a) {
                ji.io.h.d(this.uz, "Shutdown dialog code enabled");
            }
        }
        catch (Exception ex12) {}
        try {
            final String parameterWithDefault = this.getParameterWithDefault("disableShutdownDialogs", null);
            if (parameterWithDefault != null && parameterWithDefault.length() > 0) {
                this.iw = p(parameterWithDefault);
            }
            else {
                this.iw = false;
            }
        }
        catch (Exception ex13) {}
        try {
            final String parameterWithDefault2 = this.getParameterWithDefault("disableShutdownFix", null);
            if (parameterWithDefault2 != null && parameterWithDefault2.length() > 0) {
                this.ix = p(parameterWithDefault2);
            }
            else {
                this.ix = false;
            }
        }
        catch (Exception ex14) {}
        this.f();
        if (!this.iy) {
            ji.util.e.ap();
        }
        this.a(227, "javascriptTestAPI");
    }
    
    private final void f() {
        if (jiQuickStart.d(this.uz)) {
            try {
                final String parameterWithDefault = this.getParameterWithDefault("mainQuickstartWindow", null);
                if (parameterWithDefault != null && p(parameterWithDefault)) {
                    this.iy = true;
                    final String parameterWithDefault2 = this.getParameterWithDefault("launchHandle", null);
                    if (parameterWithDefault2 != null) {
                        this.iz = q(parameterWithDefault2);
                    }
                    else {
                        this.iz = 0;
                        try {
                            final URL documentBase = this.getDocumentBase();
                            if (documentBase != null) {
                                String substring = null;
                                try {
                                    documentBase.getClass().getMethod("getQuery", (Class<?>[])null);
                                    final Object c = new m(documentBase).c("getQuery");
                                    if (c != null && c instanceof String) {
                                        substring = (String)c;
                                    }
                                }
                                catch (NoSuchMethodException ex2) {}
                                if (substring == null) {
                                    final String string = documentBase.toString();
                                    final int index = string.indexOf("?");
                                    if (index > -1) {
                                        substring = string.substring(index + 1);
                                    }
                                }
                                if (substring != null) {
                                    final int index2 = substring.indexOf("launchHandle=");
                                    if (index2 > -1) {
                                        final int n = index2 + "launchHandle=".length();
                                        int n2 = substring.indexOf("&", n);
                                        if (n2 == -1) {
                                            n2 = substring.length();
                                        }
                                        this.iz = q(substring.substring(n, n2));
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
                    jiQuickStart.a(this.uz);
                }
            }
            catch (Exception ex3) {}
        }
    }
    
    public void init() {
        try {
            Thread.currentThread().setName(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(Thread.currentThread().getName()))).append(" ").append(this.uz))));
            this.e();
            this.d();
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("init start ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
            if (ji.util.d.dr() || ji.util.i.c(89)) {
                ji.io.h.d(this.uz, "Applet Init");
            }
            this.c = ji.applet.support.b9.a(this.uz, this.c());
            this.a = ji.applet.support.b9.a(this.uz, this.c);
            this.b = ji.applet.support.b9.a(this.a, this.c, this.uz);
            if (this.c == null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "parent container null, aborting");
                }
                return;
            }
            if (ji.applet.support.b9.b(this.a, this.b) && this.c != null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "Installing 1.3 hang fix");
                }
                ji.applet.support.b9.a(this.uz, this.a, this.c, this);
            }
            if (!this.iw && !this.ix) {
                if (ji.applet.support.cc.a(this.a, this.b, this.uz)) {
                    if (this.up == null) {
                        if (ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "Installing EP4");
                        }
                        this.up = new cc(this.uz, this.c, this.a, this.b);
                        if (this.java_1_3_hangEventQueuePatch != null) {
                            if (ji.util.i.c(131)) {
                                ji.io.h.d(this.uz, "Setting shutdown listener");
                            }
                            this.up.a(this.java_1_3_hangEventQueuePatch);
                        }
                    }
                }
                else if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "Not installing shutdown fix - invalid container type");
                }
            }
            if (this.ix && ji.util.i.c(131)) {
                ji.io.h.d(this.uz, "Not installing shutdown fix - disabled by parameter");
            }
            if (this.iw && ji.util.i.c(131)) {
                ji.io.h.d(this.uz, "Disabling shutdown dialogs - disabled by parameter");
            }
            boolean g = this.g();
            if (!this.ix) {
                g = (g && this.up != null);
            }
            ji.util.d.e(this.uz, g);
            if (ji.util.i.c(131) || ji.util.i.c(124)) {
                ji.io.h.d(this.uz, "Can use dialogs on shutdown: ".concat(String.valueOf(String.valueOf(ji.util.d.cl(this.uz)))));
            }
            this.r2 = true;
            if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                if (jiApplet.u4) {
                    ji.io.h.d(this.uz, "init synchronizing...");
                }
                else {
                    ji.io.h.d(this.uz, "init pre-start...");
                }
            }
            if (jiApplet.u4) {
                synchronized (this.b()) {
                    final Object u0 = this.u0;
                    // monitorenter(u0)
                    try {
                        if (ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "init synchronized");
                        }
                        this.h();
                        if (ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "init end synchronized");
                        }
                    }
                    // monitorexit(u0)
                    finally {}
                    // monitorexit(this.b())
                    return;
                }
            }
            this.h();
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        finally {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.uz, "Applet Init done");
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("init end ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
        }
    }
    
    private boolean g() {
        if (this.iw) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.uz, "Shutdown dialog: all shutdown dialogs disabled");
            }
            return false;
        }
        if (ji.util.i.c(141)) {
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.uz, "Shutdown dialog: all shutdown dialogs enabled override");
            }
            if (ji.util.i.c(124)) {
                ji.io.h.d(this.uz, "Shutdown dialog: answer would have been ".concat(String.valueOf(String.valueOf(ji.applet.support.b9.a(this.a, this.b)))));
            }
            return true;
        }
        final boolean a = ji.applet.support.b9.a(this.a, this.b);
        if (ji.util.i.c(124)) {
            ji.io.h.d(this.uz, "Shutdown dialog: supports? ".concat(String.valueOf(String.valueOf(a))));
        }
        return a;
    }
    
    private void h() {
        try {
            ++jiApplet.rq;
            if (ji.util.d.cy()) {
                ji.io.h.d(this.uz, "initWorker jiInitRunning ".concat(String.valueOf(String.valueOf(jiApplet.rq))));
            }
            jiApplet.t9 = true;
            ji.util.d.b1();
            this.t2 = false;
            if (b(this.uz)) {
                ji.util.d.as(false);
            }
            if (jiApplet.ub) {
                ji.util.d.bv(true);
                ji.io.h.e(this.uz, "Start1");
            }
            if (!this.t2) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "initA shuttingDown=".concat(String.valueOf(String.valueOf(jiApplet.ua))));
                }
                if (ji.util.d.do()) {
                    this.t1 = false;
                }
                if (ji.util.d.em() && !ji.util.e.t(this.uz)) {
                    this.t7 = true;
                }
                if (ji.util.d.as(this.uz)) {
                    this.t7 = true;
                }
                if (ji.util.d.dq()) {
                    this.t7 = false;
                }
                if (!this.t1 || b(this.uz) || !ji.util.r.a()) {
                    ji.util.e.ba();
                    this.p();
                }
                else {
                    this.a(new cl(44));
                }
            }
            else {
                this.setLayout(new BorderLayout());
                final TextArea textArea = new TextArea("A previous version of viewONE needed to be removed which has just been completed.\nPlease restart your browser to use viewONE", 2, 1, 3);
                textArea.setEditable(false);
                this.add(textArea, "Center");
                this.validate();
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                ji.io.h.d(this.uz, "initC shuttingDown=".concat(String.valueOf(String.valueOf(jiApplet.ua))));
            }
        }
        finally {
            --jiApplet.rq;
        }
        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
            ji.io.h.d(this.uz, "init complete.");
        }
    }
    
    public void start() {
        try {
            this.si = false;
            this.e();
            this.d();
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("start start ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
            if (this.c == null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "parent container null, aborting");
                }
                return;
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.uz, "Applet Start");
            }
            ji.util.d.al(this.uz);
            if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                if (jiApplet.u4) {
                    ji.io.h.d(this.uz, "start synchronizing...");
                }
                else {
                    ji.io.h.d(this.uz, "start pre-start...");
                }
            }
            if (jiApplet.u4) {
                synchronized (this.b()) {
                    final Object u0 = this.u0;
                    // monitorenter(u0)
                    try {
                        if (ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "start synchronized");
                        }
                        this.i();
                        if (ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "start end synchronized");
                        }
                    }
                    // monitorexit(u0)
                    finally {}
                    // monitorexit(this.b())
                    return;
                }
            }
            this.i();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.uz, "Applet Start done");
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("start end ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
        }
    }
    
    private void i() {
        while (true) {
            if (ji.util.d.cy()) {
                ji.io.h.d(this.uz, "startWorker called");
                try {
                    try {
                        if (this.d != null) {
                            this.d.ex(false);
                        }
                    }
                    catch (Exception ex) {}
                    if (b(this.uz)) {
                        ji.util.d.as(false);
                    }
                    if (!this.r8 && this.t7) {
                        ji.io.q.c();
                        this.p();
                    }
                    if (!this.t2) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                            ji.io.h.d(this.uz, "start");
                        }
                        if (!this.t1 || b(this.uz) || !ji.util.r.a()) {
                            this.q();
                        }
                        else {
                            this.a(new cl(45));
                        }
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                            ji.io.h.d(this.uz, "start done");
                        }
                        ji.util.d.ew();
                    }
                }
                catch (Exception ex2) {}
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                    ji.io.h.d(this.uz, "start complete.");
                }
                return;
            }
            continue;
        }
    }
    
    public void stop() {
        try {
            if (ji.util.d.dr()) {
                ji.io.h.d(this.uz, "Applet Stop");
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("stop start ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
            if (this.c == null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "parent container null, aborting");
                }
                return;
            }
            if (this.si) {
                return;
            }
            this.si = true;
            ji.util.d.f(this.uz, true);
            this.sh = true;
            try {
                if (this.d != null) {
                    this.d.ex(true);
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                if (jiApplet.u4) {
                    ji.io.h.d(this.uz, "stop synchronizing...");
                }
                else {
                    ji.io.h.d(this.uz, "stop pre-start...");
                }
            }
            if (jiApplet.u4) {
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "stop synchronizing...");
                }
                synchronized (this.b()) {
                    final Object u0 = this.u0;
                    // monitorenter(u0)
                    try {
                        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "stop synchronized");
                        }
                        this.a(false);
                        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "stop end synchronized");
                        }
                    }
                    // monitorexit(u0)
                    finally {}
                    // monitorexit(this.b())
                    return;
                }
            }
            this.a(true);
        }
        catch (Throwable t) {
            ji.util.d.a(t);
        }
        finally {
            if (this.tw != null) {
                this.tw.b();
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, "stopFinished called in stop()");
            }
            if (this.up != null) {
                this.up.a();
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.uz, "Applet Stop Done");
            }
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("stop end ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
        }
    }
    
    private void a(final boolean b) {
        try {
            if (b) {
                this.am();
            }
            if (!this.t2) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv() || ji.util.i.c(124) || ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "stop quick=".concat(String.valueOf(String.valueOf(this.t7))));
                    ji.io.h.d(this.uz, "stop useCommandQueue=".concat(String.valueOf(String.valueOf(this.t1))));
                    ji.io.h.d(this.uz, "stop isNetscapeStyle(myId)=".concat(String.valueOf(String.valueOf(b(this.uz)))));
                }
                ji.util.d.e1();
                if (!this.t7) {
                    if (!this.t1 || b(this.uz)) {
                        new Frame("test").setBounds(100, 100, 300, 100);
                        ji.util.e.ba();
                        this.al();
                    }
                    else {
                        this.a(new cl(46));
                    }
                }
                else {
                    jiApplet.ua = true;
                    if (this.tw != null) {
                        this.tw.b();
                    }
                    try {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop1");
                        }
                        if (b(this.uz)) {
                            ji.util.d.as(true);
                        }
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop2");
                        }
                        ji.util.e.ba();
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop3");
                        }
                        this.al();
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop4");
                        }
                        boolean b2 = this.d != null;
                        if (ji.document.ad.a) {
                            b2 = (this.d != null && b(this.uz));
                        }
                        if (b2) {
                            this.j();
                        }
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop5");
                        }
                        this.g(b);
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Stop6");
                        }
                    }
                    finally {
                        jiApplet.ua = false;
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Stop7");
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, "stopFinished called in stopWorker");
            }
            if (this.up != null) {
                this.up.a();
            }
        }
        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
            ji.io.h.d(this.uz, "stop complete.");
        }
    }
    
    private final void j() {
        try {
            if (this.d != null && !this.d.a3()) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "doAppletStopped3 ");
                }
                this.d.a4();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void destroy() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: invokestatic    ji/util/d.dr:()Z
        //     3: ifne            12
        //     6: getstatic       ji/util/d.ao:Z
        //     9: ifeq            22
        //    12: aload_0        
        //    13: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //    16: ldc             "Applet Destroy"
        //    18: nop            
        //    19: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    22: sipush          131
        //    25: invokestatic    ji/util/i.c:(I)Z
        //    28: ifeq            76
        //    31: aload_0        
        //    32: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //    35: new             Ljava/lang/StringBuffer;
        //    38: dup            
        //    39: ldc             "destroy start "
        //    41: nop            
        //    42: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //    45: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    48: invokevirtual   java/lang/Thread.getName:()Ljava/lang/String;
        //    51: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    54: ldc             " "
        //    56: nop            
        //    57: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    60: aload_0        
        //    61: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //    64: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //    67: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    70: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    73: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    76: aload_0        
        //    77: getfield        ji/applet/jiApplet.c:Ljava/awt/Window;
        //    80: ifnonnull       106
        //    83: sipush          131
        //    86: invokestatic    ji/util/i.c:(I)Z
        //    89: ifeq            102
        //    92: aload_0        
        //    93: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //    96: ldc             "parent container null, aborting"
        //    98: nop            
        //    99: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   102: jsr             392
        //   105: return         
        //   106: invokestatic    ji/util/d.cy:()Z
        //   109: ifne            124
        //   112: getstatic       ji/util/d.an:Z
        //   115: ifne            124
        //   118: getstatic       ji/util/d.ao:Z
        //   121: ifeq            134
        //   124: aload_0        
        //   125: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   128: ldc             "JVM-destroy1"
        //   130: nop            
        //   131: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   134: invokestatic    ji/util/d.cy:()Z
        //   137: ifne            169
        //   140: getstatic       ji/util/d.ap:Z
        //   143: ifne            169
        //   146: getstatic       ji/util/d.ao:Z
        //   149: ifne            169
        //   152: bipush          36
        //   154: invokestatic    ji/util/i.c:(I)Z
        //   157: ifne            169
        //   160: sipush          131
        //   163: invokestatic    ji/util/i.c:(I)Z
        //   166: ifeq            198
        //   169: getstatic       ji/applet/jiApplet.u4:Z
        //   172: ifeq            188
        //   175: aload_0        
        //   176: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   179: ldc             "destroy synchronizing..."
        //   181: nop            
        //   182: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   185: goto            198
        //   188: aload_0        
        //   189: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   192: ldc             "destroy pre-start.."
        //   194: nop            
        //   195: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   198: getstatic       ji/applet/jiApplet.u4:Z
        //   201: ifeq            336
        //   204: invokestatic    ji/util/d.cy:()Z
        //   207: ifne            222
        //   210: getstatic       ji/util/d.an:Z
        //   213: ifne            222
        //   216: getstatic       ji/util/d.ao:Z
        //   219: ifeq            232
        //   222: aload_0        
        //   223: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   226: ldc             "JVM-destroy2"
        //   228: nop            
        //   229: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   232: aload_0        
        //   233: invokespecial   ji/applet/jiApplet.b:()Ljava/lang/Object;
        //   236: astore_1       
        //   237: aload_1        
        //   238: monitorenter   
        //   239: aload_0        
        //   240: getfield        ji/applet/jiApplet.u0:Ljava/lang/Object;
        //   243: astore_2       
        //   244: aload_2        
        //   245: monitorenter   
        //   246: bipush          36
        //   248: invokestatic    ji/util/i.c:(I)Z
        //   251: ifne            269
        //   254: getstatic       ji/util/d.ao:Z
        //   257: ifne            269
        //   260: sipush          131
        //   263: invokestatic    ji/util/i.c:(I)Z
        //   266: ifeq            279
        //   269: aload_0        
        //   270: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   273: ldc             "destroy synchronized"
        //   275: nop            
        //   276: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   279: aload_0        
        //   280: iconst_1       
        //   281: invokespecial   ji/applet/jiApplet.b:(Z)V
        //   284: bipush          36
        //   286: invokestatic    ji/util/i.c:(I)Z
        //   289: ifne            307
        //   292: getstatic       ji/util/d.ao:Z
        //   295: ifne            307
        //   298: sipush          131
        //   301: invokestatic    ji/util/i.c:(I)Z
        //   304: ifeq            317
        //   307: aload_0        
        //   308: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   311: ldc             "destroy end synchronized"
        //   313: nop            
        //   314: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   317: aload_2        
        //   318: monitorexit    
        //   319: goto            325
        //   322: aload_2        
        //   323: monitorexit    
        //   324: athrow         
        //   325: aload_1        
        //   326: monitorexit    
        //   327: goto            341
        //   330: aload_1        
        //   331: monitorexit    
        //   332: athrow         
        //   333: goto            341
        //   336: aload_0        
        //   337: iconst_1       
        //   338: invokespecial   ji/applet/jiApplet.b:(Z)V
        //   341: invokestatic    ji/util/d.cy:()Z
        //   344: ifne            359
        //   347: getstatic       ji/util/d.an:Z
        //   350: ifne            359
        //   353: getstatic       ji/util/d.ao:Z
        //   356: ifeq            369
        //   359: aload_0        
        //   360: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   363: ldc             "JVM-destroy3"
        //   365: nop            
        //   366: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   369: jsr             392
        //   372: goto            730
        //   375: astore_1       
        //   376: aload_1        
        //   377: invokestatic    ji/util/d.a:(Ljava/lang/Throwable;)V
        //   380: jsr             392
        //   383: goto            730
        //   386: astore_3       
        //   387: jsr             392
        //   390: aload_3        
        //   391: athrow         
        //   392: astore          4
        //   394: aload_0        
        //   395: getfield        ji/applet/jiApplet.up:Lji/applet/support/cd;
        //   398: ifnull          432
        //   401: sipush          131
        //   404: invokestatic    ji/util/i.c:(I)Z
        //   407: ifeq            420
        //   410: aload_0        
        //   411: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   414: ldc             "notifying shutdown object destroyFinished"
        //   416: nop            
        //   417: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   420: aload_0        
        //   421: getfield        ji/applet/jiApplet.up:Lji/applet/support/cd;
        //   424: invokeinterface ji/applet/support/cd.b:()V
        //   429: goto            451
        //   432: sipush          131
        //   435: invokestatic    ji/util/i.c:(I)Z
        //   438: ifeq            451
        //   441: aload_0        
        //   442: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   445: ldc             "not notifying shutdown object destroyFinished (null)"
        //   447: nop            
        //   448: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   451: jsr             478
        //   454: goto            482
        //   457: astore          5
        //   459: aload           5
        //   461: invokestatic    ji/util/d.a:(Ljava/lang/Throwable;)V
        //   464: jsr             478
        //   467: goto            482
        //   470: astore          6
        //   472: jsr             478
        //   475: aload           6
        //   477: athrow         
        //   478: astore          7
        //   480: ret             7
        //   482: invokestatic    ji/util/d.em:()Z
        //   485: ifeq            527
        //   488: new             Lji/awt/bb;
        //   491: dup            
        //   492: aload_0        
        //   493: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   496: new             Lji/applet/jiApplet$xw;
        //   499: dup            
        //   500: aload_0        
        //   501: invokespecial   ji/applet/jiApplet$xw.<init>:(Lji/applet/jiApplet;)V
        //   504: invokespecial   ji/awt/bb.<init>:(Ljava/lang/String;Ljava/lang/Runnable;)V
        //   507: astore          8
        //   509: aload           8
        //   511: invokevirtual   ji/awt/bb.start:()V
        //   514: aload           8
        //   516: invokevirtual   java/lang/Thread.join:()V
        //   519: goto            569
        //   522: astore          9
        //   524: goto            569
        //   527: sipush          240
        //   530: invokestatic    ji/util/i.c:(I)Z
        //   533: ifeq            544
        //   536: aload_0        
        //   537: aload_0        
        //   538: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   541: invokestatic    ji/sec/f.a:(Ljava/lang/Object;Ljava/lang/String;)V
        //   544: aconst_null    
        //   545: aload_0        
        //   546: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   549: invokestatic    ji/io/q.a:(Ljava/lang/Object;Ljava/lang/String;)Lji/io/q;
        //   552: astore          8
        //   554: aload           8
        //   556: ifnull          569
        //   559: aload           8
        //   561: invokevirtual   ji/io/q.r:()V
        //   564: goto            569
        //   567: astore          8
        //   569: jsr             583
        //   572: goto            652
        //   575: astore          10
        //   577: jsr             583
        //   580: aload           10
        //   582: athrow         
        //   583: astore          11
        //   585: aconst_null    
        //   586: aload_0        
        //   587: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   590: invokestatic    ji/util/d.b:(Ljava/lang/Object;Ljava/lang/String;)V
        //   593: invokestatic    ji/util/d.em:()Z
        //   596: ifeq            638
        //   599: new             Lji/awt/bb;
        //   602: dup            
        //   603: aload_0        
        //   604: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   607: new             Lji/applet/jiApplet$xx;
        //   610: dup            
        //   611: aload_0        
        //   612: invokespecial   ji/applet/jiApplet$xx.<init>:(Lji/applet/jiApplet;)V
        //   615: invokespecial   ji/awt/bb.<init>:(Ljava/lang/String;Ljava/lang/Runnable;)V
        //   618: astore          12
        //   620: aload           12
        //   622: invokevirtual   ji/awt/bb.start:()V
        //   625: aload           12
        //   627: invokevirtual   java/lang/Thread.join:()V
        //   630: goto            645
        //   633: astore          13
        //   635: goto            645
        //   638: aload_0        
        //   639: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   642: invokestatic    ji/util/d.v:(Ljava/lang/String;)V
        //   645: aload_0        
        //   646: aconst_null    
        //   647: putfield        ji/applet/jiApplet.e:Ljava/applet/Applet;
        //   650: ret             11
        //   652: invokestatic    ji/util/d.dr:()Z
        //   655: ifne            664
        //   658: getstatic       ji/util/d.ao:Z
        //   661: ifeq            674
        //   664: aload_0        
        //   665: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   668: ldc             "Applet Destroy done"
        //   670: nop            
        //   671: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   674: sipush          131
        //   677: invokestatic    ji/util/i.c:(I)Z
        //   680: ifeq            728
        //   683: aload_0        
        //   684: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   687: new             Ljava/lang/StringBuffer;
        //   690: dup            
        //   691: ldc             "destroy end "
        //   693: nop            
        //   694: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   697: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //   700: invokevirtual   java/lang/Thread.getName:()Ljava/lang/String;
        //   703: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   706: ldc             " "
        //   708: nop            
        //   709: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   712: aload_0        
        //   713: getfield        ji/applet/jiApplet.uz:Ljava/lang/String;
        //   716: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   719: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   722: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   725: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //   728: ret             4
        //   730: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  246    317    322    325    Any
        //  239    325    330    333    Any
        //  0      369    375    386    Ljava/lang/Throwable;
        //  0      386    386    392    Any
        //  394    451    457    470    Ljava/lang/Exception;
        //  394    470    470    478    Any
        //  514    519    522    527    Ljava/lang/InterruptedException;
        //  544    564    567    569    Ljava/lang/Throwable;
        //  482    575    575    583    Any
        //  625    630    633    638    Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #1276 (coming from #1266).
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void b(final boolean b) {
        try {
            if (!this.t2) {
                if (!this.t7 && b(this.uz)) {
                    ji.util.d.b(300, 2, this.uz);
                }
                if (!this.t7) {
                    jiApplet.ua = true;
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "A-Destroy");
                }
                if (!this.t7) {
                    if (!this.t1 || b(this.uz)) {
                        this.g(b);
                    }
                    else {
                        this.a(new cl(47));
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "A-Destroyed");
                }
            }
        }
        catch (Exception ex) {}
        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
            ji.io.h.d(this.uz, "destroy complete.");
        }
    }
    
    public final void performLinkCommand(final Object o, final String s) {
        if (this.d != null) {
            final a3 a3 = (a3)o;
            if (this.o5) {
                this.a(a3, s, this.d);
                if (this.d.d1()) {
                    this.a(a3, this.ki, this.d.d2());
                }
            }
        }
    }
    
    public final String getRenderedImageChecksum() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getRenderedImageChecksum()"));
        }
        String value = null;
        if (this.rx && !ji.util.d.ck(this.uz)) {
            if (this.d != null) {
                try {
                    final long ks = this.d.ks();
                    if (ks != -1) {
                        value = String.valueOf(ks);
                    }
                }
                catch (Exception ex) {
                    if (ji.util.i.c(6)) {
                        ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getRenderedImageChecksum() failed ").append(ex.getMessage()))));
                    }
                }
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getRenderedImageChecksum() returning ").append(value))));
                }
            }
            else if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getRenderedImageChecksum() null image"));
            }
        }
        else if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getRenderedImageChecksum() no init"));
        }
        return value;
    }
    
    private final void a(final a3 a3, final String s, final ad ad) {
        try {
            if (ad != null && this.rx && a3.j() == 49 && ad.g3()) {
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: Event link = ").append(a3.b()))));
                }
                switch (a3.b()) {
                    case 1: {
                        ad.c(a3.c(), a3.d());
                        break;
                    }
                    case 2: {
                        ad.i(a3.e());
                        break;
                    }
                    case 3: {
                        ad.c(a3.c(), a3.f());
                        break;
                    }
                    case 4: {
                        ad.a1(a3.c());
                        break;
                    }
                    case 12: {
                        ad.ir();
                        break;
                    }
                    case 13: {
                        ad.is();
                        break;
                    }
                    case 14: {
                        ad.it();
                        break;
                    }
                    case 5: {
                        ad.au(a3.c());
                        break;
                    }
                    case 6: {
                        ad.ax(a3.c());
                        break;
                    }
                    case 9: {
                        ad.ay(a3.c());
                        break;
                    }
                    case 10: {
                        ad.il();
                        break;
                    }
                    case 11: {
                        ad.im();
                        break;
                    }
                    case 7: {
                        ad.e3(a3.f());
                        break;
                    }
                    case 8: {
                        ad.iq();
                        break;
                    }
                    case 15: {
                        ad.b(a3.c(), a3.d(), a3.g(), a3.h());
                        break;
                    }
                    case 16: {
                        ad.a0(a3.c());
                        break;
                    }
                    case 17: {
                        ad.d(a3.f(), a3.i());
                        break;
                    }
                    case 18: {
                        ad.d(a3.c(), a3.d());
                        break;
                    }
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26: {
                        ad.a(a3.b(), a3.c(), a3.e());
                        break;
                    }
                    case 27: {
                        ad.az(a3.c());
                        break;
                    }
                    case 28: {
                        ad.ip();
                        break;
                    }
                    case 29: {
                        ad.in();
                        break;
                    }
                    case 30: {
                        ad.io();
                        break;
                    }
                    case 31: {
                        ad.e4(a3.f());
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private static int e(final String s) {
        int q;
        if (s.toLowerCase().equals("ant")) {
            q = 0;
        }
        else if (s.toLowerCase().equals("wang")) {
            q = 1;
        }
        else if (s.toLowerCase().equals("antnwang")) {
            q = 3;
        }
        else if (s.toLowerCase().equals("embedded")) {
            q = 2;
        }
        else {
            q = q(s);
        }
        return q;
    }
    
    public String getParameterDecode(final String s) {
        if (this.uq != null) {
            return this.uq.getParameter(s);
        }
        return null;
    }
    
    private boolean f(final String s) {
        return s.equalsIgnoreCase("encConfig") || s.equalsIgnoreCase("resourcePath") || s.equalsIgnoreCase("mayscript") || s.equalsIgnoreCase("cabbase") || s.equalsIgnoreCase("archive") || s.equalsIgnoreCase("doc") || s.equalsIgnoreCase("docRef") || s.equalsIgnoreCase("customResource");
    }
    
    private String g(final String s) {
        try {
            return super.getParameter(s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public String getParameter(final String s) {
        String s2;
        try {
            if (!this.ur || this.f(s)) {
                if (this.e == null) {
                    s2 = super.getParameter(s);
                }
                else if (this.e != this) {
                    s2 = this.e.getParameter(s);
                }
                else {
                    s2 = super.getParameter(s);
                }
            }
            else {
                s2 = this.getParameterDecode(s);
            }
        }
        catch (Exception ex) {
            if (!this.ur || this.f(s)) {
                if (this.e == null) {
                    s2 = super.getParameter(s);
                }
                else if (this.e != this) {
                    s2 = this.e.getParameter(s);
                }
                else {
                    s2 = super.getParameter(s);
                }
            }
            else {
                s2 = this.getParameterDecode(s);
            }
        }
        try {
            if (jiApplet.uc && s2 != null && jiApplet.uf != null && !jiApplet.uf.a((Object)s)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("TAG: ").append(s).append(" = ").append(s2))));
                jiApplet.uf.c(s);
            }
        }
        catch (Exception ex2) {}
        return s2;
    }
    
    public String getParameterWithDefault(final String s, final String s2) {
        return this.a(s, s2, true);
    }
    
    private String a(final String s, final String s2, final boolean b) {
        String parameter = null;
        try {
            if (this.t4 != null) {
                parameter = (String)this.t4.d(s.toLowerCase());
            }
        }
        catch (Exception ex) {
            parameter = null;
        }
        if (parameter == null) {
            parameter = this.getParameter(s);
            if (parameter == null) {
                parameter = s2;
            }
        }
        if (parameter != null) {
            try {
                if (b) {
                    this.t6.a(s, parameter);
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (jiApplet.uc && parameter != null && b && !jiApplet.uf.a((Object)s)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("TAGL: ").append(s).append(" = ").append(parameter))));
                jiApplet.uf.c(s);
            }
        }
        catch (Exception ex3) {}
        if (ji.util.d.by(parameter)) {
            return null;
        }
        return parameter;
    }
    
    private final void a(final cl cl) {
        if (ji.util.i.c(36)) {
            ji.io.h.d(this.uz, "add command synchronizing");
        }
        synchronized (this) {
            if (ji.util.i.c(36)) {
                ji.io.h.d(this.uz, "add command synchronized");
            }
            this.b(cl);
            if (ji.util.i.c(36)) {
                ji.io.h.d(this.uz, "add command end synchronized");
            }
        }
    }
    
    private final void b(final cl cl) {
        try {
            if (this.t3 == null) {
                this.t3 = new Vector(0);
            }
            this.t3.addElement(cl);
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        try {
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "Queue Thread: ".concat(String.valueOf(String.valueOf(this.tv))));
            }
            if (this.tv == null) {
                if (ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "Creating new applet command processor");
                }
                this.r3 = true;
                this.tw = new tv(this);
                (this.tv = new bb(this.uz, this.tw)).start();
            }
            if (this.tw != null) {
                this.tw.a();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public final String getMyName() {
        return this.ki;
    }
    
    private final void k() {
        try {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.uz, "jiJustOPenDocument Open doc");
            }
            this.c(false);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.uz, "jiJustOPenDocument Done");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void nextDoc() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: nextDoc()"));
        }
        try {
            if (this.d != null) {
                this.d.a("nextd", 0, new Point(10, 10), new ActionEvent(this, 1001, "nextd"));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void l() {
        this.d.h((Object)null);
        this.openDoc(this.bl + 1);
    }
    
    public final void prevDoc() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: prevDoc()"));
        }
        try {
            if (this.d != null) {
                this.d.a("prevd", 0, new Point(10, 10), new ActionEvent(this, 1001, "prevd"));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void m() {
        this.d.h((Object)null);
        this.openDoc(this.bl - 1);
    }
    
    public final void firstDoc() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: firstDoc()"));
        }
        try {
            if (this.d != null) {
                this.d.a("firstd", 0, new Point(10, 10), new ActionEvent(this, 1001, "firstd"));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void n() {
        this.d.h((Object)null);
        this.openDoc(0);
    }
    
    public final void lastDoc() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: lastDoc()"));
        }
        try {
            if (this.d != null) {
                this.d.a("lastd", 0, new Point(10, 10), new ActionEvent(this, 1001, "lastd"));
            }
        }
        catch (Exception ex) {}
    }
    
    private final void o() {
        this.d.h((Object)null);
        this.openDoc(this.bc.length - 1);
    }
    
    public final void openDoc(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openDoc(").append(n).append(")"))));
        }
        try {
            if (this.bc != null && n != this.bl && n < this.bc.length && n >= 0) {
                if (!this.ty) {
                    this.d.c3();
                }
                this.bl = Math.min(n, this.bc.length - 1);
                this.bl = Math.max(n, 0);
                ji.util.e.b8 = this.bl;
                if (!ji.util.d.by(this.bc[this.bl])) {
                    if (!this.d.gm()) {
                        this.d.dg(true);
                    }
                    this.a(new cl(88, this.bc[this.bl]));
                    if (this.tx) {
                        this.a(new cl(93));
                    }
                }
                else {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: Error openDoc(").append(n).append(") not defined."))));
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final int getDocIndex() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getDocIndex()"));
        }
        try {
            if (this.bc != null) {
                return this.bl + 1;
            }
            return 1;
        }
        catch (Exception ex) {
            return 1;
        }
    }
    
    public final String getDocReference() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getDocReference()"));
        }
        try {
            if (this.bd != null) {
                return this.bd[this.getDocIndex() - 1];
            }
            return "";
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public final int getNumDocs() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getNumDocs()"));
        }
        try {
            if (this.bc != null) {
                return this.bc.length;
            }
            return 1;
        }
        catch (Exception ex) {
            return 1;
        }
    }
    
    private void p() {
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
            ji.io.h.d(this.uz, "jiInit");
        }
        ++this.rz;
        this.sd = false;
        this.se = false;
        this.ry = false;
        try {
            if (this.nd == null) {
                this.nd = this.getParameterWithDefault("cabbase", "");
            }
        }
        catch (Exception ex) {}
        try {
            if (this.ne == null) {
                this.ne = this.getParameterWithDefault("archive", "");
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.jw == null) {
                this.jw = this.getParameterWithDefault("mayscript", null);
            }
        }
        catch (Exception ex3) {}
        try {
            if (this.fq == null) {
                this.fq = this.getParameterWithDefault("resourcePath", null);
            }
            if (this.fq != null) {
                ji.util.e.l(this.fq);
            }
        }
        catch (Exception ex4) {}
        if (ji.util.d.by(this.nd)) {
            this.nd = "";
        }
        if (ji.util.d.by(this.ne)) {
            this.ne = "";
        }
        ji.util.d.ab(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ne))).append("\n").append(this.nd))));
        try {
            this.bc = this.m("doc");
            this.bd = this.m("docRef");
            ji.util.e.b6 = this.bd;
            ji.util.e.b7 = this.bc;
            this.be = this.getParameterWithDefault("docThumbsEnabled", null);
            this.bf = this.getParameterWithDefault("docThumbsImageEnabled", null);
            this.bg = this.getParameterWithDefault("docThumbsTextEnabled", null);
            this.bh = this.getParameterWithDefault("thumbsImageEnabled", null);
            this.bi = this.getParameterWithDefault("thumbsTextEnabled", null);
        }
        catch (Exception ex5) {}
        try {
            this.bk = this.m("customResource");
        }
        catch (Exception ex6) {}
        try {
            if (this.bc != null) {
                this.h(this.bc[this.bl]);
            }
            else {
                this.h((String)null);
            }
        }
        catch (Exception ex7) {}
    }
    
    private void h(final String s) {
        try {
            if (ji.util.d.em()) {
                this.um = new tt(s);
                new bb(this.uz, this.um).start();
                while (this.um != null) {
                    ji.util.d.b(1, -1, this.uz);
                }
            }
            else {
                this.l(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void a(final int n, final String s) {
        try {
            final String parameterWithDefault = this.getParameterWithDefault(s, null);
            if (!ji.util.d.by(parameterWithDefault)) {
                ji.util.i.a(n, p(parameterWithDefault));
            }
        }
        catch (Exception ex) {
            ji.io.h.a("", ex);
        }
    }
    
    private void i(final String s) {
        final String parameterWithDefault = this.getParameterWithDefault(s, null);
        if (!ji.util.d.by(parameterWithDefault) && ji.document.bd.f(s)) {
            this.n9.put(s, parameterWithDefault);
        }
    }
    
    private void j(final String s) {
        final String[] m = this.m(s);
        if (m != null && ji.document.bd.f(s)) {
            this.n9.put(s, m);
        }
    }
    
    private void b(final int n, final String s) {
        try {
            final String parameterWithDefault = this.getParameterWithDefault(s, null);
            if (!ji.util.d.by(parameterWithDefault)) {
                ji.util.i.a(n, parameterWithDefault);
            }
        }
        catch (Exception ex) {
            ji.io.h.a("", ex);
        }
    }
    
    private void k(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (nextToken.indexOf("&") != -1) {
                continue;
            }
            final int index = nextToken.indexOf(61);
            if (index <= -1 || nextToken.indexOf(61, index + 1) != -1) {
                continue;
            }
            final String substring = nextToken.substring(0, index);
            final String substring2 = nextToken.substring(index + 1);
            if (substring.equalsIgnoreCase("data")) {
                ji.util.i.a(4, substring2);
            }
            else if (substring.equalsIgnoreCase("size")) {
                ji.util.i.a(3, substring2);
            }
            else {
                if (!substring.equalsIgnoreCase("numdata")) {
                    continue;
                }
                ji.util.i.a(5, substring2);
            }
        }
    }
    
    private void l(final String s) throws Exception {
        if (this.fq == null) {
            try {
                this.fq = this.getParameterWithDefault("resourcePath", null);
            }
            catch (Exception ex3) {}
        }
        if (this.fq != null) {
            ji.util.e.l(this.fq);
        }
        try {
            this.gf = this.getParameterWithDefault("cachePath", null);
        }
        catch (Exception ex4) {}
        if (this.gf != null) {
            while (this.gf.endsWith("/")) {
                this.gf = this.gf.substring(0, this.gf.length() - 1);
            }
            while (this.gf.endsWith("\\")) {
                this.gf = this.gf.substring(0, this.gf.length() - 1);
            }
            ji.util.d.a(this.gf, this, this.uz);
        }
        try {
            this.gi = this.getParameterWithDefault("maxOptimizedMemory", null);
        }
        catch (Exception ex5) {}
        if (this.gi != null) {
            ji.sec.g.b = q(this.gi) * 1024 * 1024;
        }
        try {
            this.gj = this.getParameterWithDefault("maxOptimizedAgeout", null);
        }
        catch (Exception ex6) {}
        if (this.gj != null) {
            ji.sec.g.c = q(this.gj) * 1000;
        }
        try {
            this.gg = this.getParameterWithDefault("overrideExtendedCaching", null);
        }
        catch (Exception ex7) {}
        if (this.gg != null) {
            ji.util.i.a(201, true);
            ji.util.i.a(199, true);
            ji.util.i.a(200, p(this.gg));
        }
        try {
            this.gh = this.getParameterWithDefault("allowOptimizePerformance", null);
        }
        catch (Exception ex8) {}
        if (this.gh != null) {
            ji.util.i.a(201, p(this.gh));
            ji.util.i.a(198, p(this.gh));
        }
        try {
            this.gm = this.getParameterWithDefault("processClip", null);
        }
        catch (Exception ex9) {}
        if (this.gm != null) {
            ji.util.i.a(214, p(this.gm));
        }
        this.a(270, "allowDocumentBaseMacro");
        Label_0722: {
            if (this.getParameter("encConfig") != null) {
                this.ur = true;
                final String parameter = this.getParameter("encConfig");
                if (parameter == null || parameter.length() <= 0) {
                    break Label_0722;
                }
                try {
                    this.uq = new EncryptedParameters(parameter);
                    break Label_0722;
                }
                catch (Exception ex10) {
                    if (!this.ui) {
                        this.ui = true;
                        ji.util.d.a(ji.res.ay.a(), ji.util.d.b(1010, this.uz), this.c(), null, null, this.uz, false);
                        throw new Exception("Encrypted parameter string invalid");
                    }
                    break Label_0722;
                }
            }
            if (s != null) {
                try {
                    ji.util.d.b4(true);
                    ji.util.e.v = this;
                    ji.util.e.a(this.getCodeBase());
                    try {
                        this.dn = this.getParameterWithDefault("docBase", null);
                    }
                    catch (Exception ex11) {}
                    final y y = new y();
                    if (ji.util.i.c(79)) {
                        ji.io.h.d(this.uz, "Reading document parameters from: ".concat(String.valueOf(String.valueOf(s))));
                    }
                    final byte[] a = ji.res.z.a(s, this.ag(), 3, null, null, this.uz, y);
                    this.t4 = ji.util.d.e(a);
                    if (a == null) {
                        String s2 = ji.util.d.es();
                        if (s2 == null) {
                            if (this.fq != null) {
                                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.fq))).append("/").append(s)));
                            }
                            else {
                                s2 = "/".concat(String.valueOf(String.valueOf(s)));
                            }
                        }
                        ji.util.d.a(ji.res.ay.a(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("\n ----- ").append(ji.util.d.b(260, this.uz)).append(" -----\n").append(s2))), this, 30, null, null, this.uz);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    this.bc = null;
                    this.bd = null;
                }
            }
        }
        ji.util.d.dl = this.ur;
        try {
            jiApplet.ue = this.getParameterWithDefault("showtags", null);
        }
        catch (Exception ex12) {}
        if (jiApplet.ue != null) {
            jiApplet.uc = p(jiApplet.ue);
        }
        try {
            this.f = this.getParameterWithDefault("language", null);
        }
        catch (Exception ex13) {}
        try {
            this.ef = this.getParameterWithDefault("traceQuickStart", null);
        }
        catch (Exception ex14) {}
        this.f();
        if (this.iy) {
            String parameterWithDefault = null;
            try {
                this.m7 = this.getParameterWithDefault("resCompany", null);
            }
            catch (Exception ex15) {}
            try {
                this.m8 = this.getParameterWithDefault("resProduct", null);
            }
            catch (Exception ex16) {}
            try {
                parameterWithDefault = this.getParameterWithDefault("resProductId", null);
            }
            catch (Exception ex17) {}
            if (this.m7 != null || this.m8 != null) {
                if (this.m7 != null && this.m7.toLowerCase().indexOf("filenet") > -1) {
                    this.mb = "true";
                    if (parameterWithDefault != null) {
                        this.md = parameterWithDefault;
                    }
                }
                this.y();
            }
            return;
        }
        try {
            this.g = this.getParameterWithDefault("description", null);
        }
        catch (Exception ex18) {}
        try {
            this.h = this.getParameterWithDefault("filename", null);
        }
        catch (Exception ex19) {}
        try {
            this.i = this.getParameterWithDefault("basefilename", null);
        }
        catch (Exception ex20) {}
        try {
            this.am = this.getParameterWithDefault("backgroundOption", null);
        }
        catch (Exception ex21) {}
        try {
            this.al = this.getParameterWithDefault("backgroundImagePageNumber", null);
        }
        catch (Exception ex22) {}
        try {
            this.an = this.getParameterWithDefault("backgroundImage", null);
        }
        catch (Exception ex23) {}
        try {
            this.ap = this.getParameterWithDefault("DocIdMarker", null);
        }
        catch (Exception ex24) {}
        try {
            this.aq = this.getParameterWithDefault("PageIdMarker", null);
        }
        catch (Exception ex25) {}
        try {
            this.ar = this.getParameterWithDefault("fileNetCOLDTemplateResource", null);
        }
        catch (Exception ex26) {}
        try {
            this.as = this.getParameterWithDefault("fileNetCOLDSimpleValidation", null);
        }
        catch (Exception ex27) {}
        try {
            this.at = this.getParameterWithDefault("fileNetCOLDUseTemplateResolution", null);
        }
        catch (Exception ex28) {}
        try {
            this.au = this.getParameterWithDefault("fileNetCOLDUseDefaultFontHeight", null);
        }
        catch (Exception ex29) {}
        try {
            this.av = this.getParameterWithDefault("textIgnoreBlankLines", null);
        }
        catch (Exception ex30) {}
        try {
            this.aw = this.getParameterWithDefault("filenetCOLDUseAllCRLFChars", null);
        }
        catch (Exception ex31) {}
        try {
            this.cz = this.getParameterWithDefault("coldTextResolution", null);
        }
        catch (Exception ex32) {}
        try {
            this.l9 = this.getParameterWithDefault("mouseRightButtonEnabled", null);
        }
        catch (Exception ex33) {}
        if (this.l9 == null) {
            try {
                this.l9 = this.getParameterWithDefault("enableAlternativeOpOnButtons", null);
            }
            catch (Exception ex34) {}
        }
        try {
            this.ax = this.getParameterWithDefault("maxColorMemory", null);
        }
        catch (Exception ex35) {}
        try {
            this.ay = this.getParameterWithDefault("maxColorMemoryLimit", null);
        }
        catch (Exception ex36) {}
        try {
            this.k = this.getParameterWithDefault("buttonResource1", null);
        }
        catch (Exception ex37) {}
        try {
            this.l = this.getParameterWithDefault("buttonResource2", null);
        }
        catch (Exception ex38) {}
        try {
            this.m = this.getParameterWithDefault("buttonResource3", null);
        }
        catch (Exception ex39) {}
        try {
            this.n = this.getParameterWithDefault("buttonResource4", null);
        }
        catch (Exception ex40) {}
        try {
            this.j = this.getParameterWithDefault("pagecount", null);
        }
        catch (Exception ex41) {}
        try {
            this.dm = this.getParameterWithDefault("filebase", null);
        }
        catch (Exception ex42) {}
        try {
            this.dr = this.getParameterWithDefault("keepAlive", null);
        }
        catch (Exception ex43) {}
        try {
            this.ds = this.getParameterWithDefault("keepAliveTime", null);
        }
        catch (Exception ex44) {}
        try {
            this.q = this.getParameterWithDefault("fileDir", null);
        }
        catch (Exception ex45) {}
        try {
            this.p = this.getParameterWithDefault("filelist", null);
        }
        catch (Exception ex46) {}
        try {
            this.ab = this.getParameterWithDefault("hyperlink", null);
        }
        catch (Exception ex47) {}
        try {
            this.r = this.getParameterWithDefault("timeout", null);
        }
        catch (Exception ex48) {}
        try {
            this.ad = this.getParameterWithDefault("pagenumber", null);
        }
        catch (Exception ex49) {}
        try {
            this.s = this.getParameterWithDefault("zoomRate", null);
        }
        catch (Exception ex50) {}
        try {
            this.kf = this.getParameterWithDefault("demo", null);
        }
        catch (Exception ex51) {}
        try {
            this.kl = this.getParameterWithDefault("netscapewidth", null);
        }
        catch (Exception ex52) {}
        try {
            this.ko = this.getParameterWithDefault("netscapeheight", null);
        }
        catch (Exception ex53) {}
        try {
            this.kp = this.getParameterWithDefault("netscapewidthoffset", null);
        }
        catch (Exception ex54) {}
        try {
            this.kq = this.getParameterWithDefault("netscapeheightoffset", null);
        }
        catch (Exception ex55) {}
        try {
            this.km = this.getParameterWithDefault("netscapemaxwidth", null);
        }
        catch (Exception ex56) {}
        try {
            this.kn = this.getParameterWithDefault("netscapemaxheight", null);
        }
        catch (Exception ex57) {}
        try {
            this.j4 = this.getParameterWithDefault("saveExtension", null);
        }
        catch (Exception ex58) {}
        try {
            this.j5 = this.getParameterWithDefault("emptyOnClose", null);
        }
        catch (Exception ex59) {}
        try {
            this.j6 = this.getParameterWithDefault("emptyClipboardOnClose", null);
        }
        catch (Exception ex60) {}
        try {
            this.j7 = this.getParameterWithDefault("oldStyleClipCopy", null);
        }
        catch (Exception ex61) {}
        try {
            this.oo = this.getParameterWithDefault("annotationTabLength", null);
        }
        catch (Exception ex62) {}
        try {
            this.az = this.getParameterWithDefault("webAnnotationReadMode", null);
        }
        catch (Exception ex63) {}
        try {
            this.a0 = this.getParameterWithDefault("wangtextborder", null);
        }
        catch (Exception ex64) {}
        try {
            this.a5 = this.getParameterWithDefault("saveAntAndWang", null);
        }
        catch (Exception ex65) {}
        try {
            this.a1 = this.getParameterWithDefault("localAnnotationReadMode", null);
        }
        catch (Exception ex66) {}
        try {
            this.a2 = this.getParameterWithDefault("webAnnotationWriteMode", null);
        }
        catch (Exception ex67) {}
        try {
            this.a3 = this.getParameterWithDefault("localAnnotationWriteMode", null);
        }
        catch (Exception ex68) {}
        try {
            this.a4 = this.getParameterWithDefault("unsupportedWangError", null);
        }
        catch (Exception ex69) {}
        try {
            this.kv = this.getParameterWithDefault("viewmode", null);
        }
        catch (Exception ex70) {}
        try {
            this.kw = this.getParameterWithDefault("nwviewmode", null);
        }
        catch (Exception ex71) {}
        this.i("defaultThumbsDisplayMode");
        try {
            this.kx = this.getParameterWithDefault("showNoteIds", null);
        }
        catch (Exception ex72) {}
        try {
            this.ky = this.getParameterWithDefault("showNoteTooltips", null);
        }
        catch (Exception ex73) {}
        try {
            this.ky = this.getParameterWithDefault("showNoteTooltips", null);
        }
        catch (Exception ex74) {}
        try {
            this.kz = this.getParameterWithDefault("tooltipsMaxLength", null);
        }
        catch (Exception ex75) {}
        try {
            this.k1 = this.getParameterWithDefault("keepscroll", null);
        }
        catch (Exception ex76) {}
        this.i("thumbDblClickSelect");
        this.i("thumbDblClick");
        try {
            this.nu = this.getParameterWithDefault("annotate", null);
        }
        catch (Exception ex77) {}
        try {
            this.o1 = this.getParameterWithDefault("annotationFile", null);
        }
        catch (Exception ex78) {}
        try {
            this.o2 = this.getParameterWithDefault("annotationTemplate", null);
        }
        catch (Exception ex79) {}
        try {
            this.ba = this.getParameterWithDefault("separatorAnnotationFile", null);
        }
        catch (Exception ex80) {}
        try {
            this.ak = this.getParameterWithDefault("annotationTemplateValuesFile", null);
        }
        catch (Exception ex81) {}
        try {
            this.o3 = this.getParameterWithDefault("annotationEncoding", null);
        }
        catch (Exception ex82) {}
        try {
            this.o4 = this.getParameterWithDefault("annotationUseEncoding", null);
        }
        catch (Exception ex83) {}
        try {
            this.a6 = this.getParameterWithDefault("annotationSubstitutionDelimiter1", null);
        }
        catch (Exception ex84) {}
        try {
            this.a7 = this.getParameterWithDefault("annotationSubstitutionDelimiter2", null);
        }
        catch (Exception ex85) {}
        try {
            this.a8 = this.getParameterWithDefault("annotationSubstitutionDelimiter3", null);
        }
        catch (Exception ex86) {}
        try {
            this.a9 = this.getParameterWithDefault("annotationEmptyDelimiter", null);
        }
        catch (Exception ex87) {}
        try {
            this.rl = this.getParameterWithDefault("annotationPropertyDelimiter1", null);
        }
        catch (Exception ex88) {}
        try {
            this.rm = this.getParameterWithDefault("annotationPropertyDelimiter2", null);
        }
        catch (Exception ex89) {}
        try {
            this.rn = this.getParameterWithDefault("annotationPropertyDelimiter3", null);
        }
        catch (Exception ex90) {}
        try {
            this.qg = this.getParameterWithDefault("textEncoding", null);
        }
        catch (Exception ex91) {}
        try {
            this.qn = this.getParameterWithDefault("imageSaveAllToServer", null);
        }
        catch (Exception ex92) {}
        try {
            this.qm = this.getParameterWithDefault("imageSave", null);
        }
        catch (Exception ex93) {}
        try {
            this.qo = this.getParameterWithDefault("imageSaveServlet", null);
        }
        catch (Exception ex94) {}
        try {
            this.qp = this.getParameterWithDefault("imageSavePost", null);
        }
        catch (Exception ex95) {}
        try {
            this.qq = this.getParameterWithDefault("saveButtonToServer", null);
        }
        catch (Exception ex96) {}
        try {
            this.qr = this.getParameterWithDefault("annotationSaveSeparatePages", null);
        }
        catch (Exception ex97) {}
        try {
            this.rh = this.getParameterWithDefault("annotationNoteTextWrapping", null);
        }
        catch (Exception ex98) {}
        try {
            this.ri = this.getParameterWithDefault("defaultAnnotationSelection", null);
        }
        catch (Exception ex99) {}
        try {
            this.q8 = this.getParameterWithDefault("thirdPartyFileCache", null);
        }
        catch (Exception ex100) {}
        try {
            this.q9 = this.getParameterWithDefault("java2PrintSetupDialog", null);
        }
        catch (Exception ex101) {}
        try {
            this.ra = this.getParameterWithDefault("htmlListShortcutEnabled", null);
        }
        catch (Exception ex102) {}
        try {
            this.rb = this.getParameterWithDefault("systemInfoShortcutEnabled", null);
        }
        catch (Exception ex103) {}
        try {
            this.rc = this.getParameterWithDefault("makeSaveNameSafe", null);
        }
        catch (Exception ex104) {}
        try {
            this.rd = this.getParameterWithDefault("noDirectDraw", null);
        }
        catch (Exception ex105) {}
        try {
            this.rf = this.getParameterWithDefault("addEscapeCharactersToXML", null);
        }
        catch (Exception ex106) {}
        try {
            this.rg = this.getParameterWithDefault("filenetKeepBackground", null);
        }
        catch (Exception ex107) {}
        try {
            this.oj = this.getParameterWithDefault("annotationCache", null);
        }
        catch (Exception ex108) {}
        try {
            this.p1 = this.getParameterWithDefault("annotationLocalSave", null);
        }
        catch (Exception ex109) {}
        try {
            this.nr = this.getParameterWithDefault("fastJPEG", null);
        }
        catch (Exception ex110) {}
        try {
            this.ns = this.getParameterWithDefault("newColor", null);
        }
        catch (Exception ex111) {}
        try {
            this.nv = this.getParameterWithDefault("annotateEdit", null);
        }
        catch (Exception ex112) {}
        try {
            this.og = this.getParameterWithDefault("annotateFile", null);
        }
        catch (Exception ex113) {}
        try {
            this.ha = this.getParameterWithDefault("adjustFontScale", null);
        }
        catch (Exception ex114) {}
        try {
            this.nx = this.getParameterWithDefault("annotationFont", null);
        }
        catch (Exception ex115) {}
        try {
            this.nw = this.getParameterWithDefault("defaultAnnotationFont", null);
        }
        catch (Exception ex116) {}
        try {
            this.cn = this.getParameterWithDefault("snapZoom", null);
        }
        catch (Exception ex117) {}
        try {
            this.co = this.getParameterWithDefault("zoomWithMouseWheel", null);
        }
        catch (Exception ex118) {}
        try {
            this.ny = this.getParameterWithDefault("annotationFontHeight", null);
        }
        catch (Exception ex119) {}
        try {
            this.nz = this.getParameterWithDefault("useFontStyle", null);
        }
        catch (Exception ex120) {}
        try {
            this.n0 = this.getParameterWithDefault("useFonts", null);
        }
        catch (Exception ex121) {}
        try {
            this.n1 = this.getParameterWithDefault("useFontMode", null);
        }
        catch (Exception ex122) {}
        try {
            this.n2 = this.getParameterWithDefault("useFontsWithCold", null);
        }
        catch (Exception ex123) {}
        try {
            this.n3 = this.getParameterWithDefault("newAnnotAntiAlias", null);
        }
        catch (Exception ex124) {}
        try {
            this.oi = this.getParameterWithDefault("annotationStart", null);
        }
        catch (Exception ex125) {}
        try {
            this.om = this.getParameterWithDefault("annotationHyperlinkWeb", null);
        }
        catch (Exception ex126) {}
        try {
            this.kr = this.getParameterWithDefault("mode", null);
        }
        catch (Exception ex127) {}
        try {
            this.ks = this.getParameterWithDefault("imageDPIX", null);
        }
        catch (Exception ex128) {}
        try {
            this.kt = this.getParameterWithDefault("imageDPIY", null);
        }
        catch (Exception ex129) {}
        try {
            this.ku = this.getParameterWithDefault("combine", null);
        }
        catch (Exception ex130) {}
        try {
            this.jh = this.getParameterWithDefault("backColor", null);
        }
        catch (Exception ex131) {}
        this.i("findResultColor");
        this.i("findResultHighlightColor");
        this.i("findResultHighlightWidth");
        this.i("findResultHistoryListSize");
        this.i("pdfDllRenderMode");
        this.i("userLogoutRedirectMatch");
        try {
            this.ji = this.getParameterWithDefault("overrideImageForeColor", null);
        }
        catch (Exception ex132) {}
        try {
            this.n4 = this.getParameterWithDefault("textFont", null);
        }
        catch (Exception ex133) {}
        try {
            this.n5 = this.getParameterWithDefault("textFontHeight", null);
        }
        catch (Exception ex134) {}
        try {
            this.n7 = this.getParameterWithDefault("tiffSaveVersion6Jpeg", null);
        }
        catch (Exception ex135) {}
        try {
            this.n8 = this.getParameterWithDefault("tiffSaveJpegQuality", null);
        }
        catch (Exception ex136) {}
        try {
            this.oa = this.getParameterWithDefault("tiffSaveColor", null);
        }
        catch (Exception ex137) {}
        try {
            this.ob = this.getParameterWithDefault("tiffForceLZWsave", null);
        }
        catch (Exception ex138) {}
        try {
            this.oc = this.getParameterWithDefault("tiffForceJPEGsave", null);
        }
        catch (Exception ex139) {}
        try {
            this.od = this.getParameterWithDefault("tiffSaveRotation", null);
        }
        catch (Exception ex140) {}
        try {
            this.oe = this.getParameterWithDefault("tiffSaveThumbsAntiAliased", null);
        }
        catch (Exception ex141) {}
        try {
            this.n6 = this.getParameterWithDefault("retrievalMode", null);
        }
        catch (Exception ex142) {}
        try {
            this.of = this.getParameterWithDefault("retrievalModeValuePair", null);
        }
        catch (Exception ex143) {}
        try {
            this.jk = this.getParameterWithDefault("activeColor", null);
        }
        catch (Exception ex144) {}
        try {
            this.jl = this.getParameterWithDefault("activeColor1", null);
        }
        catch (Exception ex145) {}
        try {
            this.jm = this.getParameterWithDefault("activeColor2", null);
        }
        catch (Exception ex146) {}
        try {
            this.jn = this.getParameterWithDefault("activeButtons", null);
        }
        catch (Exception ex147) {}
        try {
            this.cp = this.getParameterWithDefault("scale", null);
        }
        catch (Exception ex148) {}
        try {
            this.c4 = this.getParameterWithDefault("rotation", null);
        }
        catch (Exception ex149) {}
        try {
            this.dg = this.getParameterWithDefault("flip", null);
        }
        catch (Exception ex150) {}
        try {
            this.kd = this.getParameterWithDefault("urltimeout", null);
        }
        catch (Exception ex151) {}
        try {
            this.ke = this.getParameterWithDefault("urlretries", null);
        }
        catch (Exception ex152) {}
        try {
            this.k2 = this.getParameterWithDefault("magFactor", null);
        }
        catch (Exception ex153) {}
        try {
            this.k3 = this.getParameterWithDefault("magBounds", null);
        }
        catch (Exception ex154) {}
        try {
            this.c6 = this.getParameterWithDefault("textScale", null);
        }
        catch (Exception ex155) {}
        try {
            this.c7 = this.getParameterWithDefault("annotationTextScale", null);
        }
        catch (Exception ex156) {}
        try {
            this.c8 = this.getParameterWithDefault("annotationTextBold", null);
        }
        catch (Exception ex157) {}
        try {
            this.dc = this.getParameterWithDefault("routeDocs", null);
        }
        catch (Exception ex158) {}
        try {
            this.dd = this.getParameterWithDefault("routeHTML", null);
        }
        catch (Exception ex159) {}
        try {
            this.nm = this.getParameterWithDefault("annotationUnits", null);
        }
        catch (Exception ex160) {}
        try {
            this.ni = this.getParameterWithDefault("rulerUnits", null);
        }
        catch (Exception ex161) {}
        try {
            this.nk = this.getParameterWithDefault("rulerScale", null);
        }
        catch (Exception ex162) {}
        try {
            this.nj = this.getParameterWithDefault("angleUnits", null);
        }
        catch (Exception ex163) {}
        try {
            this.nl = this.getParameterWithDefault("unitDecimalPlaces", null);
        }
        catch (Exception ex164) {}
        try {
            this.nn = this.getParameterWithDefault("annotationColorMask", null);
        }
        catch (Exception ex165) {}
        try {
            this.dt = this.getParameterWithDefault("simulateNetDelayResource", null);
        }
        catch (Exception ex166) {}
        try {
            this.du = this.getParameterWithDefault("simulateNetDelayImage", null);
        }
        catch (Exception ex167) {}
        try {
            this.bo = this.getParameterWithDefault("pageLabelNullifier", null);
        }
        catch (Exception ex168) {}
        try {
            this.df = this.getParameterWithDefault("redirectTarget", null);
        }
        catch (Exception ex169) {}
        try {
            this.de = this.getParameterWithDefault("routeTarget", null);
        }
        catch (Exception ex170) {}
        try {
            this.c9 = this.getParameterWithDefault("thumbsWidth", null);
        }
        catch (Exception ex171) {}
        try {
            this.da = this.getParameterWithDefault("thumbsHeight", null);
        }
        catch (Exception ex172) {}
        try {
            this.db = this.getParameterWithDefault("thumbsText", null);
        }
        catch (Exception ex173) {}
        try {
            this.op = this.getParameterWithDefault("annotationNoteSize", null);
        }
        catch (Exception ex174) {}
        try {
            this.o6 = this.getParameterWithDefault("annotationNoteColor", null);
        }
        catch (Exception ex175) {}
        try {
            this.o7 = this.getParameterWithDefault("annotationLineColor", null);
        }
        catch (Exception ex176) {}
        try {
            this.o8 = this.getParameterWithDefault("annotationTextColor", null);
        }
        catch (Exception ex177) {}
        try {
            this.o9 = this.getParameterWithDefault("annotationTextFillColor", null);
        }
        catch (Exception ex178) {}
        try {
            this.pa = this.getParameterWithDefault("annotationHighlightColor", null);
        }
        catch (Exception ex179) {}
        try {
            this.pb = this.getParameterWithDefault("annotationDefaults", null);
        }
        catch (Exception ex180) {}
        try {
            this.pc = this.getParameterWithDefault("annotationRedactColor", null);
        }
        catch (Exception ex181) {}
        try {
            this.ut = this.getParameterWithDefault("allowAnnotationInvert", null);
        }
        catch (Exception ex182) {}
        try {
            this.ph = this.getParameterWithDefault("annotationLimitedColors", null);
        }
        catch (Exception ex183) {}
        try {
            this.pv = this.getParameterWithDefault("xmlDebug", null);
        }
        catch (Exception ex184) {}
        try {
            this.p2 = this.getParameterWithDefault("showMousePosition", null);
        }
        catch (Exception ex185) {}
        try {
            this.p3 = this.getParameterWithDefault("showPrintStatus", null);
        }
        catch (Exception ex186) {}
        try {
            this.p4 = this.getParameterWithDefault("useFileNetStandardStamps", null);
        }
        catch (Exception ex187) {}
        try {
            this.uu = this.getParameterWithDefault("stampAnnotationResize", null);
        }
        catch (Exception ex188) {}
        try {
            this.p5 = this.getParameterWithDefault("implementCOLD400200Fix", null);
        }
        catch (Exception ex189) {}
        try {
            this.p6 = this.getParameterWithDefault("implementCOLD300200Fix", null);
        }
        catch (Exception ex190) {}
        try {
            this.uv = this.getParameterWithDefault("stampAnnotationKeepAspect", null);
        }
        catch (Exception ex191) {}
        try {
            this.qb = this.getParameterWithDefault("matchStampResolution", null);
        }
        catch (Exception ex192) {}
        try {
            this.qc = this.getParameterWithDefault("autoScaleNewStamps", null);
        }
        catch (Exception ex193) {}
        try {
            this.pi = this.getParameterWithDefault("annotationDebug", null);
        }
        catch (Exception ex194) {}
        try {
            this.p7 = this.getParameterWithDefault("annotationAutoWrap", null);
        }
        catch (Exception ex195) {}
        try {
            this.ok = this.getParameterWithDefault("annotationAllowControlCodes", null);
        }
        catch (Exception ex196) {}
        try {
            this.e7 = this.getParameterWithDefault("imageForeColor", null);
        }
        catch (Exception ex197) {}
        try {
            this.e8 = this.getParameterWithDefault("imageBackColor", null);
        }
        catch (Exception ex198) {}
        try {
            this.kj = this.getParameterWithDefault("width", null);
        }
        catch (Exception ex199) {}
        try {
            this.kk = this.getParameterWithDefault("height", null);
        }
        catch (Exception ex200) {}
        try {
            this.sj = this.getParameterWithDefault("filenetKey", null);
        }
        catch (Exception ex201) {}
        try {
            this.sk = this.getParameterWithDefault("allowOpenWithoutAnnotations", null);
        }
        catch (Exception ex202) {}
        try {
            this.uj = this.getParameterWithDefault("alternativeurlretries", null);
        }
        catch (Exception ex203) {}
        try {
            this.uw = this.getParameterWithDefault("displayAnnotationsIfError", null);
        }
        catch (Exception ex204) {}
        try {
            this.ux = this.getParameterWithDefault("checkExtensionForJPEG", null);
        }
        catch (Exception ex205) {}
        this.a(142, "newGenerateCode");
        this.a(138, "filenetZOrderEnabled");
        this.a(161, "filenetZOrderChangeEnabled");
        this.a(212, "filenetUpdateStatusOnZOrderChange");
        this.a(188, "nonFilenetSystemColdDebug");
        this.a(189, "filenetColdBackgroundImageResolutionFix");
        this.a(211, "filenetColdUseForegroundResolution");
        this.a(202, "filenetIDMResolution");
        this.a(204, "filenetIDMLinewidth");
        this.a(205, "filenetLaserCOLD20x32FontHasDPINoSpacingFix");
        this.a(203, "cancelClientBurnOnBrowserClose");
        this.a(243, "filenetcoldTestBackgroundForTextFile");
        this.a(217, "abortSaveOnShutdown");
        this.b(0, "customAnnotationToolTip");
        this.a(225, "alwaysShowCustomAnnotationTooltip");
        this.b(2, "fixedSavePath");
        this.b(1, "FilenetColdExternalProperties");
        this.a(277, "FilenetColdLaserMono200LineHeight");
        this.a(280, "useAWTList");
        this.a(289, "useSwingList");
        this.a(290, "useImageHeaderDIB");
        this.a(291, "useStartBoundsThread");
        this.a(293, "uvPostscript");
        this.b(6, "licPostfix");
        this.a(219, "streamerBulkResourceMode");
        this.a(221, "persistViewProcessingProperties");
        this.i("multipleSaveFormats");
        this.a(228, "filenetAnnotationSecurityNoAdd");
        this.a(230, "filenetAnnotationOutputCreator");
        this.a(231, "cacheLockCheck");
        this.a(233, "repLockCheck");
        this.a(240, "unloadNativeLibs");
        this.a(241, "accuratePageCount");
        this.a(242, "fireAnnotationUpdatedOnSaveableOnly");
        this.a(232, "filenetAllowAnnotationPostPrefix");
        this.a(237, "allowTextFind");
        this.i("displaySearchBoxThumbnailView");
        this.a(255, "useOldEventDispatcher");
        this.a(256, "useOldFilterDetection");
        this.a(260, "sendCookiesWithCookieHeader");
        this.i("allowSaveBurnableAnnotations");
        this.i("burnAllAnnotations");
        this.i("allowTextFilterDisplayAnyFile");
        this.i("textfilterMimetypes");
        this.i("textfilterLocalFileExtensions");
        this.i("ignoreTextFilterMimetypes");
        this.i("saveAnnotationsIgnoreFileDisplayErrors");
        this.a(257, "useOldZOrderValues");
        this.i("textFilterWrapping");
        this.i("textFilterPageBreaks");
        this.i("textFilterPageHeight");
        this.i("textFilterPageWidth");
        this.i("annotationSaveNoteId");
        this.a(265, "annotationStrikethruSmallScale");
        this.i("saveTIFFLZWMemoryImprovement");
        this.i("filenetCOLDUsePageBackgroundImage");
        this.i("filenetCSAdminPermissionsOnChange");
        this.i("annotationTextClip");
        this.i("keepActiveAnnotOnResize");
        final String parameterWithDefault2 = this.getParameterWithDefault("annotationSavePostParamNames", null);
        if (!ji.util.d.by(parameterWithDefault2)) {
            this.k(parameterWithDefault2);
        }
        try {
            this.uy = this.getParameterWithDefault("applyPngMonoPrefix", null);
        }
        catch (Exception ex206) {}
        try {
            this.sp = this.getParameterWithDefault("overlayAdjustmentUIAllowed", null);
        }
        catch (Exception ex207) {}
        try {
            this.s7 = this.getParameterWithDefault("overlayAdjustmentUIEnabled", null);
        }
        catch (Exception ex208) {}
        try {
            this.sl = this.getParameterWithDefault("overlayAdjustmentX", null);
        }
        catch (Exception ex209) {}
        try {
            this.sm = this.getParameterWithDefault("overlayAdjustmentY", null);
        }
        catch (Exception ex210) {}
        try {
            this.sq = this.getParameterWithDefault("overlayAdjustmentSaveURL", null);
        }
        catch (Exception ex211) {}
        try {
            this.sr = this.getParameterWithDefault("overlayRotationAdjustment", null);
        }
        catch (Exception ex212) {}
        try {
            this.ss = this.getParameterWithDefault("overlayBackgroundAdjustmentX", null);
        }
        catch (Exception ex213) {}
        try {
            this.st = this.getParameterWithDefault("overlayBackgroundAdjustmentY", null);
        }
        catch (Exception ex214) {}
        try {
            this.su = this.getParameterWithDefault("overlayBackgroundRotationAdjustment", null);
        }
        catch (Exception ex215) {}
        try {
            this.sx = this.getParameterWithDefault("overlayRotationOriginX", null);
        }
        catch (Exception ex216) {}
        try {
            this.sy = this.getParameterWithDefault("overlayRotationOriginY", null);
        }
        catch (Exception ex217) {}
        try {
            this.sz = this.getParameterWithDefault("overlayBackgroundRotationOriginX", null);
        }
        catch (Exception ex218) {}
        try {
            this.s0 = this.getParameterWithDefault("overlayBackgroundRotationOriginY", null);
        }
        catch (Exception ex219) {}
        try {
            this.s6 = this.getParameterWithDefault("traceImageAdjustment", null);
        }
        catch (Exception ex220) {}
        try {
            this.s1 = this.getParameterWithDefault("overlayRotationDegrees", null);
        }
        catch (Exception ex221) {}
        try {
            this.s2 = this.getParameterWithDefault("overlayBackgroundRotationDegrees", null);
        }
        catch (Exception ex222) {}
        try {
            this.sn = this.getParameterWithDefault("overlayAdjustmentUnits", null);
        }
        catch (Exception ex223) {}
        try {
            this.sv = this.getParameterWithDefault("overlayBackgroundAdjustmentUnits", null);
        }
        catch (Exception ex224) {}
        try {
            this.so = this.getParameterWithDefault("overlayAdjustmentStep", null);
        }
        catch (Exception ex225) {}
        try {
            this.sw = this.getParameterWithDefault("overlayBackgroundAdjustmentStep", null);
        }
        catch (Exception ex226) {}
        try {
            this.s3 = this.getParameterWithDefault("overlayRotationUnits", null);
        }
        catch (Exception ex227) {}
        try {
            this.s4 = this.getParameterWithDefault("overlayBackgroundRotationUnits", null);
        }
        catch (Exception ex228) {}
        try {
            this.s5 = this.getParameterWithDefault("overlayBackgroundAsDefault", null);
        }
        catch (Exception ex229) {}
        try {
            this.s8 = this.getParameterWithDefault("overlayRotationCentre", null);
        }
        catch (Exception ex230) {}
        try {
            this.s9 = this.getParameterWithDefault("overlayBackgroundRotationCentre", null);
        }
        catch (Exception ex231) {}
        try {
            this.ta = this.getParameterWithDefault("imageAdjustmentPermissions", null);
        }
        catch (Exception ex232) {}
        this.tb = this.m("overlayAdjustmentX");
        this.tc = this.m("overlayAdjustmentY");
        this.td = this.m("overlayBackgroundAdjustmentX");
        this.te = this.m("overlayBackgroundAdjustmentY");
        this.tf = this.m("overlayRotationAdjustment");
        this.tg = this.m("overlayBackgroundRotationAdjustment");
        this.th = this.m("overlayRotationOriginX");
        this.ti = this.m("overlayRotationOriginY");
        this.tj = this.m("overlayBackgroundRotationOriginX");
        this.tk = this.m("overlayBackgroundRotationOriginY");
        this.tl = this.m("overlayRotationAngle");
        this.tm = this.m("overlayBackgroundRotationAngle");
        this.tr = this.m("overlayAdjustmentUnits");
        this.ts = this.m("overlayBackgroundAdjustmentUnits");
        this.tt = this.m("overlayAdjustmentStep");
        this.tu = this.m("overlayBackgroundAdjustmentStep");
        this.tn = this.m("overlayRotationCentre");
        this.to = this.m("overlayBackgroundRotationCentre");
        this.tp = this.m("overlayRotationUnits");
        this.tq = this.m("overlayBackgroundRotationUnits");
        this.qz = this.m("replaceText");
        this.qy = this.m("errorText");
        this.af = this.m("page");
        this.ai = this.m("separator");
        this.bm = this.m("pagecolor");
        this.ag = this.m("thumb");
        this.ah = this.m("docThumb");
        this.bb = this.m("doc");
        this.ae = this.m("hyperlink");
        this.bn = this.m("pagelabel");
        this.bu = this.m("pageLabelColor");
        this.bv = this.m("thumblabel");
        this.bw = this.m("docThumbLabel");
        this.bx = this.m("thumbLabelColor");
        this.by = this.m("docThumbLabelColor");
        this.bz = this.m("bar1button");
        this.b0 = this.m("bar2button");
        this.aj = this.m("annotationSubstitution");
        this.p8 = this.m("annotationStamp");
        this.p9 = this.m("annotationStampProperties");
        this.qa = this.m("annotationTarget");
        this.pf = this.m("annotationDefaultLineColor");
        this.pg = this.m("annotationDefaultFillColor");
        this.ps = this.m("annotationInputKeyMapping");
        this.o0 = this.m("annotationClearTextList");
        this.ao = this.m("backgroundImage");
        this.jj = this.m("overrideImageForeColor");
        this.c5 = this.m("pageRotation");
        this.bp = this.m("index");
        this.bq = this.m("documentIndex");
        try {
            this.uk = null;
            final String parameterWithDefault3 = this.getParameterWithDefault("cookieAllowedDomainList", null);
            if (!ji.util.d.by(parameterWithDefault3)) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameterWithDefault3, ",");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    if (!ji.util.d.by(nextToken)) {
                        final String trim = nextToken.trim();
                        if (ji.util.d.by(trim)) {
                            continue;
                        }
                        if (this.uk == null) {
                            this.uk = new Vector();
                        }
                        this.uk.addElement(trim);
                    }
                }
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        try {
            this.ul = this.getParameterWithDefault("documentCookies", null);
        }
        catch (Exception ex233) {}
        try {
            this.j8 = this.getParameterWithDefault("trace", null);
        }
        catch (Exception ex234) {}
        try {
            this.j9 = this.getParameterWithDefault("traceFinalize", null);
        }
        catch (Exception ex235) {}
        try {
            this.kb = this.getParameterWithDefault("allowFileAccess", null);
        }
        catch (Exception ex236) {}
        try {
            this.ka = this.getParameterWithDefault("obfuscate", null);
        }
        catch (Exception ex237) {}
        try {
            this.kc = this.getParameterWithDefault("allowPrinting", null);
        }
        catch (Exception ex238) {}
        try {
            this.in = this.getParameterWithDefault("printComplete", null);
        }
        catch (Exception ex239) {}
        try {
            this.io = this.getParameterWithDefault("printDocumentAllowed", null);
        }
        catch (Exception ex240) {}
        try {
            this.ip = this.getParameterWithDefault("printRangeAllowed", null);
        }
        catch (Exception ex241) {}
        try {
            this.iq = this.getParameterWithDefault("printVisibleAllowed", null);
        }
        catch (Exception ex242) {}
        try {
            this.ir = this.getParameterWithDefault("printSelectedAllowed", null);
        }
        catch (Exception ex243) {}
        try {
            this.is = this.getParameterWithDefault("printOriginalSizeAllowed", null);
        }
        catch (Exception ex244) {}
        try {
            this.i0 = this.getParameterWithDefault("printOriginalSizeOverride", null);
        }
        catch (Exception ex245) {}
        try {
            this.br = this.getParameterWithDefault("indexList", null);
        }
        catch (Exception ex246) {}
        try {
            this.bs = this.getParameterWithDefault("documentIndexList", null);
        }
        catch (Exception ex247) {}
        try {
            this.bt = this.getParameterWithDefault("separatorList", null);
        }
        catch (Exception ex248) {}
        try {
            this.it = this.getParameterWithDefault("quickStartAllowed", null);
        }
        catch (Exception ex249) {}
        try {
            this.iu = this.getParameterWithDefault("quickstartURL", null);
            if (ji.util.i.c(105)) {
                if (this.iu == null) {
                    ji.io.h.d(this.uz, "QuickStart has not been enabled (no quickstartURL parameter set)");
                }
                else {
                    ji.io.h.d(this.uz, "QuickStart: quickstartURL set to ".concat(String.valueOf(String.valueOf(this.iu))));
                }
            }
        }
        catch (Exception ex250) {}
        try {
            final String parameterWithDefault4 = this.getParameterWithDefault("launchQuickstart", null);
            if (parameterWithDefault4 != null) {
                if (parameterWithDefault4.length() > 0) {
                    this.iv = p(parameterWithDefault4);
                }
            }
            else {
                this.iv = false;
            }
        }
        catch (Exception ex251) {}
        this.a(128, "burnMultiPage");
        this.a(129, "burnUseGZip");
        this.a(127, "burnIncrementalAnnotations");
        this.a(132, "excludeBurnableAnnotsFromSave");
        this.a(133, "excludeBurnedAnnotsFromSave");
        this.a(136, "oleAccLockupFix");
        this.a(140, "RTLrightAlign");
        this.a(150, "annotationAlignmentSupport");
        this.a(148, "printAcceleratorDisableThreading");
        this.i("convertToTIFFOnSave");
        this.i("burnPDFToPDF");
        this.i("filenetAnnotBoundsCheck");
        this.i("annotBoundsCheck");
        this.i("filenetAnnotBoundsCheckAllPages");
        this.i("annotBoundsCheckAllPages");
        this.a(155, "invisibleAppletNewWindow");
        this.a(165, "allowCorruptDIBHeaders");
        this.a(166, "loadPdfDllsInNativeCode");
        this.a(169, "coldDocZeroDefaultSoftMargins");
        this.a(173, "allowStickyAnnotationsNoRightButton");
        this.a(174, "stickyAndSelectionAnnotationInteractions");
        this.a(188, "nonFilenetSystemDebug");
        this.a(206, "filenetColdExternal");
        this.a(207, "filenetColdExternalVanilla");
        this.a(208, "obfuscateFilenetCOLD");
        this.a(213, "obfuscateUV");
        this.a(220, "allowAccentedCharsInCachePath");
        this.a(238, "enableAnnotationParsingSync");
        this.a(249, "showAnnotationBurnPrompt");
        if (this.iu != null && this.it == null) {
            this.it = "true";
        }
        try {
            this.lc = this.getParameterWithDefault("printAccelerator", null);
        }
        catch (Exception ex252) {}
        try {
            this.ld = this.getParameterWithDefault("printAcceleratorReverseMono", null);
        }
        catch (Exception ex253) {}
        try {
            this.le = this.getParameterWithDefault("printAcceleratorForgetSettings", null);
        }
        catch (Exception ex254) {}
        try {
            this.lf = this.getParameterWithDefault("printAcceleratorDebug", null);
        }
        catch (Exception ex255) {}
        try {
            this.lg = this.getParameterWithDefault("printAcceleratorSuppressMessages", null);
        }
        catch (Exception ex256) {}
        try {
            this.lh = this.getParameterWithDefault("printDialog", null);
        }
        catch (Exception ex257) {}
        try {
            this.li = this.getParameterWithDefault("printer", null);
        }
        catch (Exception ex258) {}
        try {
            this.lk = this.getParameterWithDefault("reUsePrinter", null);
        }
        catch (Exception ex259) {}
        try {
            this.lj = this.getParameterWithDefault("useErrorFiles", null);
        }
        catch (Exception ex260) {}
        try {
            this.ll = this.getParameterWithDefault("renderAccelerator", null);
        }
        catch (Exception ex261) {}
        try {
            this.lm = this.getParameterWithDefault("rgbCache", null);
        }
        catch (Exception ex262) {}
        try {
            this.ln = this.getParameterWithDefault("localDetect", null);
        }
        catch (Exception ex263) {}
        try {
            this.lo = this.getParameterWithDefault("externalMagnifier", null);
        }
        catch (Exception ex264) {}
        try {
            this.lp = this.getParameterWithDefault("magnifierPersistent", null);
        }
        catch (Exception ex265) {}
        try {
            this.lq = this.getParameterWithDefault("magnifierFollowMouse", null);
        }
        catch (Exception ex266) {}
        try {
            this.l4 = this.getParameterWithDefault("annotationSuppressEmptyMessages", null);
        }
        catch (Exception ex267) {}
        try {
            this.l6 = this.getParameterWithDefault("annotationFreeHandLimit", null);
        }
        catch (Exception ex268) {}
        try {
            this.l2 = this.getParameterWithDefault("annotationNoteRectangular", null);
        }
        catch (Exception ex269) {}
        try {
            this.l0 = this.getParameterWithDefault("annotationSecurityModel", null);
        }
        catch (Exception ex270) {}
        try {
            this.lx = this.getParameterWithDefault("annotationEditPasswordModify", null);
        }
        catch (Exception ex271) {}
        try {
            this.ly = this.getParameterWithDefault("annotationEditPasswordSecurity", null);
        }
        catch (Exception ex272) {}
        try {
            this.lz = this.getParameterWithDefault("annotationEditPasswordText", null);
        }
        catch (Exception ex273) {}
        try {
            this.l1 = this.getParameterWithDefault("annotationPostChunkSize", null);
        }
        catch (Exception ex274) {}
        try {
            this.lu = this.getParameterWithDefault("annotationTextLimit", null);
        }
        catch (Exception ex275) {}
        try {
            this.lv = this.getParameterWithDefault("annotationTextLimitReachedMessage", null);
        }
        catch (Exception ex276) {}
        try {
            this.ls = this.getParameterWithDefault("displaymonoprogressively", null);
        }
        catch (Exception ex277) {}
        try {
            this.lt = this.getParameterWithDefault("displaycolorprogressively", null);
        }
        catch (Exception ex278) {}
        this.i("annotationStream");
        try {
            this.lw = this.getParameterWithDefault("annotationSaveAlways", null);
        }
        catch (Exception ex279) {}
        this.i("annotationGZip");
        this.i("annotationGZipGet");
        this.i("annotationGZipLocal");
        try {
            this.l8 = this.getParameterWithDefault("ProcessKeys", null);
        }
        catch (Exception ex280) {}
        try {
            this.mb = this.getParameterWithDefault("filenet", null);
        }
        catch (Exception ex281) {}
        try {
            this.mc = this.getParameterWithDefault("filenetUG", null);
        }
        catch (Exception ex282) {}
        try {
            this.md = this.getParameterWithDefault("filenetSystem", null);
        }
        catch (Exception ex283) {}
        try {
            this.ma = this.getParameterWithDefault("vignette", null);
        }
        catch (Exception ex284) {}
        try {
            this.me = this.getParameterWithDefault("filenetExtendedAnnotations", null);
        }
        catch (Exception ex285) {}
        try {
            this.mf = this.getParameterWithDefault("filenetAlwaysRubberband", null);
        }
        catch (Exception ex286) {}
        if (this.md != null) {
            this.y();
        }
        if (this.ma != null) {
            ji.util.i.a(239, p(this.ma));
            this.y();
        }
        try {
            this.mg = this.getParameterWithDefault("iconect", null);
        }
        catch (Exception ex287) {}
        try {
            this.mo = this.getParameterWithDefault("htmlencoding", null);
        }
        catch (Exception ex288) {}
        try {
            this.mk = this.getParameterWithDefault("djvu", null);
        }
        catch (Exception ex289) {}
        try {
            this.ml = this.getParameterWithDefault("png", null);
        }
        catch (Exception ex290) {}
        try {
            this.mm = this.getParameterWithDefault("extraRetrievalMessages", null);
        }
        catch (Exception ex291) {}
        try {
            this.mn = this.getParameterWithDefault("estimatedTimes", null);
        }
        catch (Exception ex292) {}
        try {
            this.mh = this.getParameterWithDefault("version3Features", null);
        }
        catch (Exception ex293) {}
        try {
            this.mi = this.getParameterWithDefault("showHotKeys", null);
        }
        catch (Exception ex294) {}
        try {
            this.mj = this.getParameterWithDefault("ramAnnots", null);
        }
        catch (Exception ex295) {}
        try {
            this.my = this.getParameterWithDefault("gradientColors", null);
        }
        catch (Exception ex296) {}
        this.i("thumbsTextModeAllowed");
        this.i("pdfDibMemoryBuffer");
        try {
            this.mp = this.getParameterWithDefault("splitBarAllowed", null);
        }
        catch (Exception ex297) {}
        try {
            this.mx = this.getParameterWithDefault("progressBarAllowed", null);
        }
        catch (Exception ex298) {}
        try {
            this.mq = this.getParameterWithDefault("toolbarExpandAllowed", null);
        }
        catch (Exception ex299) {}
        try {
            this.mr = this.getParameterWithDefault("toolbarCollapseDelay", null);
        }
        catch (Exception ex300) {}
        try {
            this.ms = this.getParameterWithDefault("toolbarExpandDelay", null);
        }
        catch (Exception ex301) {}
        try {
            this.mt = this.getParameterWithDefault("imageToolbarFolded", null);
        }
        catch (Exception ex302) {}
        try {
            this.mu = this.getParameterWithDefault("imageToolbarCollapsed", null);
        }
        catch (Exception ex303) {}
        try {
            this.mv = this.getParameterWithDefault("viewToolbarFolded", null);
        }
        catch (Exception ex304) {}
        try {
            this.mw = this.getParameterWithDefault("viewToolbarCollapsed", null);
        }
        catch (Exception ex305) {}
        try {
            this.mz = this.getParameterWithDefault("toolbarStyle", null);
        }
        catch (Exception ex306) {}
        try {
            this.m6 = this.getParameterWithDefault("prefetchSettleTime", null);
        }
        catch (Exception ex307) {}
        try {
            this.m4 = this.getParameterWithDefault("prefetchPages", null);
        }
        catch (Exception ex308) {}
        try {
            this.m5 = this.getParameterWithDefault("prefetchThumbs", null);
        }
        catch (Exception ex309) {}
        try {
            this.m3 = this.getParameterWithDefault("extendedPrefetch", null);
        }
        catch (Exception ex310) {}
        try {
            this.m0 = this.getParameterWithDefault("autoPaging", null);
        }
        catch (Exception ex311) {}
        try {
            this.m1 = this.getParameterWithDefault("autoPagingAlways", null);
        }
        catch (Exception ex312) {}
        try {
            this.m2 = this.getParameterWithDefault("autoPagingTime", null);
        }
        catch (Exception ex313) {}
        try {
            this.l7 = this.getParameterWithDefault("delimiter", null);
        }
        catch (Exception ex314) {}
        try {
            this.l5 = this.getParameterWithDefault("javascriptExtensions", null);
        }
        catch (Exception ex315) {}
        try {
            this.l3 = this.getParameterWithDefault("annotationJavascriptExtensions", null);
        }
        catch (Exception ex316) {}
        try {
            this.jz = this.getParameterWithDefault("annotationDateStyle", null);
        }
        catch (Exception ex317) {}
        try {
            this.m7 = this.getParameterWithDefault("resCompany", null);
        }
        catch (Exception ex318) {}
        try {
            this.m8 = this.getParameterWithDefault("resProduct", null);
        }
        catch (Exception ex319) {}
        try {
            this.m9 = this.getParameterWithDefault("resWebSite", null);
        }
        catch (Exception ex320) {}
        try {
            this.na = this.getParameterWithDefault("resEmail", null);
        }
        catch (Exception ex321) {}
        try {
            this.ng = this.getParameterWithDefault("synchronizeThumbs", null);
        }
        catch (Exception ex322) {}
        try {
            this.nh = this.getParameterWithDefault("magnifierOnTop", null);
        }
        catch (Exception ex323) {}
        try {
            this.nb = this.getParameterWithDefault("dialogs", null);
        }
        catch (Exception ex324) {}
        try {
            this.ac = this.getParameterWithDefault("hyperlinkDblClick", null);
        }
        catch (Exception ex325) {}
        try {
            this.pd = this.getParameterWithDefault("annotationDblClick", null);
        }
        catch (Exception ex326) {}
        try {
            this.qd = this.getParameterWithDefault("userId", null);
        }
        catch (Exception ex327) {}
        try {
            this.qe = this.getParameterWithDefault("userAdmin", null);
        }
        catch (Exception ex328) {}
        try {
            this.qf = this.getParameterWithDefault("userCanViewLic", null);
        }
        catch (Exception ex329) {}
        try {
            this.qt = this.getParameterWithDefault("dialogOffset", null);
        }
        catch (Exception ex330) {}
        try {
            this.qu = this.getParameterWithDefault("dialogRecycleOffets", null);
        }
        catch (Exception ex331) {}
        try {
            this.cw = this.getParameterWithDefault("autoCOLDResolution", null);
        }
        catch (Exception ex332) {}
        try {
            this.cv = this.getParameterWithDefault("autoEnhanceBlack", null);
        }
        catch (Exception ex333) {}
        try {
            this.cr = this.getParameterWithDefault("enhance", null);
        }
        catch (Exception ex334) {}
        try {
            this.cs = this.getParameterWithDefault("enhanceMode", null);
        }
        catch (Exception ex335) {}
        try {
            this.cu = this.getParameterWithDefault("enhanceMenu", null);
        }
        catch (Exception ex336) {}
        try {
            this.ct = this.getParameterWithDefault("enhanceModeAutoSharp", null);
        }
        catch (Exception ex337) {}
        this.a(224, "enableSharpEnhanceModes");
        try {
            this.cx = this.getParameterWithDefault("resolutionEqualization", null);
        }
        catch (Exception ex338) {}
        try {
            this.cm = this.getParameterWithDefault("printMargin", null);
        }
        catch (Exception ex339) {}
        try {
            this.cy = this.getParameterWithDefault("resolutionPrintAdjustment", null);
        }
        catch (Exception ex340) {}
        try {
            this.bj = this.getParameterWithDefault("thumbsRetrieveOnView", null);
        }
        catch (Exception ex341) {}
        try {
            this.c1 = this.getParameterWithDefault("fastAlias", null);
        }
        catch (Exception ex342) {}
        try {
            this.c0 = this.getParameterWithDefault("enhanceColor", null);
        }
        catch (Exception ex343) {}
        try {
            this.b1 = this.getParameterWithDefault("printLabels", null);
        }
        catch (Exception ex344) {}
        try {
            this.b2 = this.getParameterWithDefault("printLabelBorder", null);
        }
        catch (Exception ex345) {}
        try {
            this.b3 = this.getParameterWithDefault("printingFontSelectionEnabled", null);
        }
        catch (Exception ex346) {}
        try {
            this.b5 = this.getParameterWithDefault("printingFontName", null);
        }
        catch (Exception ex347) {}
        try {
            this.b4 = this.getParameterWithDefault("printingFontSize", null);
        }
        catch (Exception ex348) {}
        try {
            this.b6 = this.getParameterWithDefault("printingFontBold", null);
        }
        catch (Exception ex349) {}
        try {
            this.cb = this.getParameterWithDefault("printingNotesFontName", null);
        }
        catch (Exception ex350) {}
        try {
            this.cc = this.getParameterWithDefault("printingNotesFontSize", null);
        }
        catch (Exception ex351) {}
        try {
            this.ce = this.getParameterWithDefault("printingColorHeader", null);
        }
        catch (Exception ex352) {}
        try {
            this.cf = this.getParameterWithDefault("printingNotesText", null);
        }
        catch (Exception ex353) {}
        try {
            this.ca = this.getParameterWithDefault("abortPrintOnAppletClose", null);
        }
        catch (Exception ex354) {}
        try {
            this.b7 = this.getParameterWithDefault("allowSelectAnnotation", null);
        }
        catch (Exception ex355) {}
        try {
            this.b8 = this.getParameterWithDefault("allowAnnotationAutoScroll", null);
        }
        catch (Exception ex356) {}
        try {
            this.b9 = this.getParameterWithDefault("allowAnnotationMove", null);
        }
        catch (Exception ex357) {}
        if (this.cf == null) {
            try {
                this.cf = this.getParameterWithDefault("defaultPrintingNotesText", null);
            }
            catch (Exception ex358) {}
        }
        try {
            this.cg = this.getParameterWithDefault("printNoteAnnotation", null);
        }
        catch (Exception ex359) {}
        if (this.cg == null) {
            try {
                this.cg = this.getParameterWithDefault("defaultPrintNoteAnnotation", null);
            }
            catch (Exception ex360) {}
        }
        try {
            this.cd = this.getParameterWithDefault("printingNotesTitleFontSize", null);
        }
        catch (Exception ex361) {}
        try {
            this.ch = this.getParameterWithDefault("printingNotesTextTitle", null);
        }
        catch (Exception ex362) {}
        try {
            this.ci = this.getParameterWithDefault("printingNotesTextPageTitle", null);
        }
        catch (Exception ex363) {}
        try {
            this.cj = this.getParameterWithDefault("printingNotesStandardFont", null);
        }
        catch (Exception ex364) {}
        this.a(269, "printingNotesStandardFontBidiAlign");
        this.b(8, "bidiBaseDirection");
        try {
            this.cl = this.getParameterWithDefault("disablePrintCopies", null);
        }
        catch (Exception ex365) {}
        try {
            this.ck = this.getParameterWithDefault("displayPageLabels", null);
        }
        catch (Exception ex366) {}
        try {
            this.k5 = this.getParameterWithDefault("docCompare", null);
        }
        catch (Exception ex367) {}
        try {
            this.k6 = this.getParameterWithDefault("defaultLinkViewer", null);
        }
        catch (Exception ex368) {}
        try {
            this.c2 = this.getParameterWithDefault("border", null);
        }
        catch (Exception ex369) {}
        try {
            this.c3 = this.getParameterWithDefault("invert", null);
        }
        catch (Exception ex370) {}
        try {
            this.k4 = this.getParameterWithDefault("fullPrint", null);
        }
        catch (Exception ex371) {}
        try {
            this.k7 = this.getParameterWithDefault("printWithUserSettings", null);
        }
        catch (Exception ex372) {}
        try {
            this.k9 = this.getParameterWithDefault("printAutoRotate", null);
        }
        catch (Exception ex373) {}
        try {
            this.oh = this.getParameterWithDefault("printAnnotations", null);
        }
        catch (Exception ex374) {}
        try {
            this.la = this.getParameterWithDefault("rightMouseEmulator", null);
        }
        catch (Exception ex375) {}
        try {
            this.k8 = this.getParameterWithDefault("printJobName", null);
        }
        catch (Exception ex376) {}
        try {
            this.lb = this.getParameterWithDefault("msJVMMouseWheelExtensions", null);
        }
        catch (Exception ex377) {}
        try {
            this.us = this.getParameterWithDefault("transparentRedactionColor", null);
        }
        catch (Exception ex378) {}
        try {
            this.on = this.getParameterWithDefault("annotationTrace", null);
        }
        catch (Exception ex379) {}
        if (this.on == null) {
            try {
                this.on = this.getParameterWithDefault("traceAnnotation", null);
            }
            catch (Exception ex380) {}
        }
        try {
            this.et = this.getParameterWithDefault("simulateSlowConnection", null);
        }
        catch (Exception ex381) {}
        try {
            this.dz = this.getParameterWithDefault("traceFocus", null);
        }
        catch (Exception ex382) {}
        try {
            this.el = this.getParameterWithDefault("tracePaint", null);
        }
        catch (Exception ex383) {}
        try {
            this.d0 = this.getParameterWithDefault("traceClose", null);
        }
        catch (Exception ex384) {}
        try {
            this.d4 = this.getParameterWithDefault("traceNet", null);
        }
        catch (Exception ex385) {}
        try {
            this.en = this.getParameterWithDefault("traceException", null);
        }
        catch (Exception ex386) {}
        try {
            this.d7 = this.getParameterWithDefault("traceDecomp", null);
        }
        catch (Exception ex387) {}
        try {
            this.d6 = this.getParameterWithDefault("tracePerformance", null);
        }
        catch (Exception ex388) {}
        try {
            this.eo = this.getParameterWithDefault("traceAnnotManagement", null);
        }
        catch (Exception ex389) {}
        try {
            this.dy = this.getParameterWithDefault("useWindowsTempPath", null);
        }
        catch (Exception ex390) {}
        try {
            this.d2 = this.getParameterWithDefault("traceCache", null);
        }
        catch (Exception ex391) {}
        if (this.dy != null) {
            ji.util.i.a(106, p(this.dy));
        }
        if (this.d2 != null) {
            ji.util.i.a(83, p(this.d2));
        }
        try {
            this.d1 = this.getParameterWithDefault("extraTrace", null);
        }
        catch (Exception ex392) {}
        try {
            this.d3 = this.getParameterWithDefault("traceFile", null);
        }
        catch (Exception ex393) {}
        try {
            this.lr = this.getParameterWithDefault("traceSource", null);
        }
        catch (Exception ex394) {}
        try {
            this.d9 = this.getParameterWithDefault("tracePro", null);
        }
        catch (Exception ex395) {}
        try {
            this.ea = this.getParameterWithDefault("traceFNSecurity", null);
        }
        catch (Exception ex396) {}
        try {
            this.eb = this.getParameterWithDefault("traceSync", null);
        }
        catch (Exception ex397) {}
        try {
            this.ec = this.getParameterWithDefault("traceKeys", null);
        }
        catch (Exception ex398) {}
        try {
            this.ee = this.getParameterWithDefault("traceJavascript", null);
        }
        catch (Exception ex399) {}
        try {
            this.eg = this.getParameterWithDefault("tracePrint", null);
        }
        catch (Exception ex400) {}
        try {
            this.ed = this.getParameterWithDefault("traceDir", null);
        }
        catch (Exception ex401) {}
        try {
            this.eh = this.getParameterWithDefault("traceFilter", null);
        }
        catch (Exception ex402) {}
        try {
            this.ei = this.getParameterWithDefault("tracePDFDecoderCommands", null);
        }
        catch (Exception ex403) {}
        try {
            this.ej = this.getParameterWithDefault("traceBurn", null);
        }
        catch (Exception ex404) {}
        try {
            this.ek = this.getParameterWithDefault("tracePrefetch", null);
        }
        catch (Exception ex405) {}
        try {
            this.em = this.getParameterWithDefault("traceTime", null);
        }
        catch (Exception ex406) {}
        try {
            this.dw = this.getParameterWithDefault("traceShutdown", null);
        }
        catch (Exception ex407) {}
        try {
            this.dx = this.getParameterWithDefault("renderSupport", null);
        }
        catch (Exception ex408) {}
        try {
            this.z = this.getParameterWithDefault("ACMDownloadPrompt", null);
        }
        catch (Exception ex409) {}
        try {
            this.aa = this.getParameterWithDefault("ACMDownloadPromptTrigger", null);
        }
        catch (Exception ex410) {}
        try {
            this.t = this.getParameterWithDefault("ACMRedirect", null);
        }
        catch (Exception ex411) {}
        try {
            this.u = this.getParameterWithDefault("ACMReInstall", null);
        }
        catch (Exception ex412) {}
        try {
            this.v = this.getParameterWithDefault("ACMUpdate", null);
        }
        catch (Exception ex413) {}
        try {
            this.w = this.getParameterWithDefault("ACMRemove", null);
        }
        catch (Exception ex414) {}
        try {
            this.x = this.getParameterWithDefault("ACMEnabled", null);
        }
        catch (Exception ex415) {}
        try {
            this.y = this.getParameterWithDefault("ACMCanRemoveCachedVersion", null);
        }
        catch (Exception ex416) {}
        try {
            this.eq = this.getParameterWithDefault("java1NTBackgroundPrinting", null);
        }
        catch (Exception ex417) {}
        try {
            this.ep = this.getParameterWithDefault("printFrame", null);
        }
        catch (Exception ex418) {}
        try {
            this.dq = this.getParameterWithDefault("traceLoad", null);
        }
        catch (Exception ex419) {}
        try {
            this.dv = this.getParameterWithDefault("traceRepository", null);
        }
        catch (Exception ex420) {}
        try {
            this.do = this.getParameterWithDefault("xScroll", null);
        }
        catch (Exception ex421) {}
        try {
            this.dp = this.getParameterWithDefault("yScroll", null);
        }
        catch (Exception ex422) {}
        try {
            this.dk = this.getParameterWithDefault("areaZoom", null);
        }
        catch (Exception ex423) {}
        try {
            this.eu = this.getParameterWithDefault("annotationsSticky", null);
        }
        catch (Exception ex424) {}
        try {
            this.ex = this.getParameterWithDefault("annotationsStickyAcrossPages", null);
        }
        catch (Exception ex425) {}
        try {
            this.ev = this.getParameterWithDefault("emptyStickyNotesEnabled", null);
        }
        catch (Exception ex426) {}
        try {
            this.ew = this.getParameterWithDefault("allowMultipleStickyNotes", null);
        }
        catch (Exception ex427) {}
        try {
            this.di = this.getParameterWithDefault("offscreenHighlightEnabled", null);
        }
        catch (Exception ex428) {}
        try {
            this.dh = this.getParameterWithDefault("flickScrollEnabled", null);
        }
        catch (Exception ex429) {}
        try {
            this.ez = this.getParameterWithDefault("areaZoomSticky", null);
        }
        catch (Exception ex430) {}
        try {
            this.dj = this.getParameterWithDefault("areaZoomEnabled", null);
        }
        catch (Exception ex431) {}
        try {
            this.e1 = this.getParameterWithDefault("draggingEnabled", null);
        }
        catch (Exception ex432) {}
        try {
            this.e5 = this.getParameterWithDefault("newWindowButtons", null);
        }
        catch (Exception ex433) {}
        try {
            this.gd = this.getParameterWithDefault("newWindow", null);
        }
        catch (Exception ex434) {}
        try {
            this.e6 = this.getParameterWithDefault("highlightColor", null);
        }
        catch (Exception ex435) {}
        try {
            this.e3 = this.getParameterWithDefault("enhanceButton", null);
        }
        catch (Exception ex436) {}
        try {
            this.e2 = this.getParameterWithDefault("docButtonsInNW", null);
        }
        catch (Exception ex437) {}
        try {
            this.e4 = this.getParameterWithDefault("annotationEditButton", null);
        }
        catch (Exception ex438) {}
        try {
            this.ey = this.getParameterWithDefault("allowTextRubberband", null);
        }
        catch (Exception ex439) {}
        try {
            this.es = this.getParameterWithDefault("prefOverrideAnnotationToolbar", null);
        }
        catch (Exception ex440) {}
        try {
            this.dl = this.getParameterWithDefault("areaCrop", null);
        }
        catch (Exception ex441) {}
        try {
            this.e0 = this.getParameterWithDefault("areaCropSticky", null);
        }
        catch (Exception ex442) {}
        try {
            this.hq = this.getParameterWithDefault("cacheDecomp", null);
        }
        catch (Exception ex443) {}
        try {
            this.fa = this.getParameterWithDefault("progressBar", null);
        }
        catch (Exception ex444) {}
        try {
            this.fb = this.getParameterWithDefault("hideAnnotationToolbar", null);
        }
        catch (Exception ex445) {}
        try {
            this.e9 = this.getParameterWithDefault("allButtons", null);
        }
        catch (Exception ex446) {}
        try {
            this.fc = this.getParameterWithDefault("allMenus", null);
        }
        catch (Exception ex447) {}
        try {
            this.fd = this.getParameterWithDefault("allKeys", null);
        }
        catch (Exception ex448) {}
        try {
            this.fe = this.getParameterWithDefault("supportKeys", null);
        }
        catch (Exception ex449) {}
        try {
            this.fg = this.getParameterWithDefault("dbCompressMode", null);
        }
        catch (Exception ex450) {}
        try {
            this.ff = this.getParameterWithDefault("resizeButtonDelay", null);
        }
        catch (Exception ex451) {}
        try {
            this.ol = this.getParameterWithDefault("annotationsLoadMode", null);
        }
        catch (Exception ex452) {}
        try {
            this.fh = this.getParameterWithDefault("eventHandler", null);
        }
        catch (Exception ex453) {}
        try {
            this.fi = this.getParameterWithDefault("eventInterest", null);
        }
        catch (Exception ex454) {}
        try {
            this.oq = this.getParameterWithDefault("annotationSave", null);
        }
        catch (Exception ex455) {}
        try {
            this.ot = this.getParameterWithDefault("annotationSaveServlet", null);
        }
        catch (Exception ex456) {}
        try {
            this.or = this.getParameterWithDefault("annotationBurnURL", null);
        }
        catch (Exception ex457) {}
        try {
            this.os = this.getParameterWithDefault("demoDisplayURL", null);
        }
        catch (Exception ex458) {}
        try {
            this.ou = this.getParameterWithDefault("annotationSavePost", null);
        }
        catch (Exception ex459) {}
        try {
            this.ov = this.getParameterWithDefault("annotationPostPrefix", null);
        }
        catch (Exception ex460) {}
        try {
            this.ow = this.getParameterWithDefault("annotationAutoSave", null);
        }
        catch (Exception ex461) {}
        try {
            this.ox = this.getParameterWithDefault("annotationAutoSaveJ2Shutdown", null);
        }
        catch (Exception ex462) {}
        try {
            this.oy = this.getParameterWithDefault("annotationAutoPrompt", null);
        }
        catch (Exception ex463) {}
        try {
            this.oz = this.getParameterWithDefault("annotationClearTextOnSelection", null);
        }
        catch (Exception ex464) {}
        try {
            this.fj = this.getParameterWithDefault("serverEventHandler", null);
        }
        catch (Exception ex465) {}
        try {
            this.fk = this.getParameterWithDefault("serverFileMonitor", null);
        }
        catch (Exception ex466) {}
        try {
            this.fm = this.getParameterWithDefault("documentId", null);
        }
        catch (Exception ex467) {}
        try {
            this.fl = this.getParameterWithDefault("logHandler", null);
        }
        catch (Exception ex468) {}
        try {
            this.fn = this.getParameterWithDefault("openOnClick", null);
        }
        catch (Exception ex469) {}
        try {
            this.fo = this.getParameterWithDefault("openOnClickKey", null);
        }
        catch (Exception ex470) {}
        try {
            this.fp = this.getParameterWithDefault("openOnClickTarget", null);
        }
        catch (Exception ex471) {}
        try {
            this.fr = this.getParameterWithDefault("memoryTrigger", null);
        }
        catch (Exception ex472) {}
        try {
            this.fs = this.getParameterWithDefault("toolTips", null);
        }
        catch (Exception ex473) {}
        try {
            this.ft = this.getParameterWithDefault("toolTipsBackground", null);
        }
        catch (Exception ex474) {}
        try {
            this.fu = this.getParameterWithDefault("toolTipsForeground", null);
        }
        catch (Exception ex475) {}
        try {
            this.f3 = this.getParameterWithDefault("autoScroll", null);
        }
        catch (Exception ex476) {}
        try {
            this.f2 = this.getParameterWithDefault("focusBorder", null);
        }
        catch (Exception ex477) {}
        try {
            this.fv = this.getParameterWithDefault("serviceNotFound", null);
        }
        catch (Exception ex478) {}
        try {
            this.fx = this.getParameterWithDefault("serviceOpenError", null);
        }
        catch (Exception ex479) {}
        try {
            this.fz = this.getParameterWithDefault("redirectNotFound", null);
        }
        catch (Exception ex480) {}
        try {
            this.fy = this.getParameterWithDefault("redirectOpenError", null);
        }
        catch (Exception ex481) {}
        try {
            this.f1 = this.getParameterWithDefault("serviceAnnotationError", null);
        }
        catch (Exception ex482) {}
        try {
            this.fw = this.getParameterWithDefault("serviceTransformation", null);
        }
        catch (Exception ex483) {}
        try {
            this.gs = this.getParameterWithDefault("defaultPrintDoc", null);
        }
        catch (Exception ex484) {}
        try {
            this.g1 = this.getParameterWithDefault("delayRepaint", null);
        }
        catch (Exception ex485) {}
        try {
            this.gz = this.getParameterWithDefault("defaultZoomDoc", null);
        }
        catch (Exception ex486) {}
        try {
            this.gv = this.getParameterWithDefault("defaultRotateDoc", null);
        }
        catch (Exception ex487) {}
        try {
            this.gw = this.getParameterWithDefault("defaultTransformDoc", null);
        }
        catch (Exception ex488) {}
        try {
            this.gt = this.getParameterWithDefault("rotateAnnotationsWithView", null);
        }
        catch (Exception ex489) {}
        try {
            this.gu = this.getParameterWithDefault("overrideRotateAnnotationsWithViewP8Test", null);
        }
        catch (Exception ex490) {}
        try {
            this.g9 = this.getParameterWithDefault("textFileFont", null);
        }
        catch (Exception ex491) {}
        try {
            this.hb = this.getParameterWithDefault("defaultFontHeight", null);
        }
        catch (Exception ex492) {}
        try {
            this.g8 = this.getParameterWithDefault("defaultLineWidth", null);
        }
        catch (Exception ex493) {}
        try {
            this.g6 = this.getParameterWithDefault("defaultPDFPixelDepth", null);
        }
        catch (Exception ex494) {}
        try {
            this.g7 = this.getParameterWithDefault("defaultPDFResolution", null);
        }
        catch (Exception ex495) {}
        try {
            this.g2 = this.getParameterWithDefault("defaultAutoLimitPDFResolution", null);
        }
        catch (Exception ex496) {}
        try {
            this.g3 = this.getParameterWithDefault("autoLimitPDFResolutionValue", null);
        }
        catch (Exception ex497) {}
        try {
            this.f0 = this.getParameterWithDefault("annotationClipping", null);
        }
        catch (Exception ex498) {}
        try {
            this.g4 = this.getParameterWithDefault("autoLimitJPEG2000Resolution", null);
        }
        catch (Exception ex499) {}
        try {
            this.g5 = this.getParameterWithDefault("jpeg2000PixelLimit", null);
        }
        catch (Exception ex500) {}
        try {
            this.hd = this.getParameterWithDefault("defaultSaveFilename", null);
        }
        catch (Exception ex501) {}
        try {
            this.hc = this.getParameterWithDefault("defaultSavePage", null);
        }
        catch (Exception ex502) {}
        try {
            this.f4 = this.getParameterWithDefault("cropOptions", null);
        }
        catch (Exception ex503) {}
        try {
            this.f5 = this.getParameterWithDefault("adjustOptions", null);
        }
        catch (Exception ex504) {}
        try {
            this.ga = this.getParameterWithDefault("adjustBars", null);
        }
        catch (Exception ex505) {}
        try {
            this.gb = this.getParameterWithDefault("brightness", null);
        }
        catch (Exception ex506) {}
        try {
            this.gc = this.getParameterWithDefault("contrast", null);
        }
        catch (Exception ex507) {}
        try {
            this.ge = this.getParameterWithDefault("luminance", null);
        }
        catch (Exception ex508) {}
        try {
            this.gq = this.getParameterWithDefault("keepPageAdjustments", null);
        }
        catch (Exception ex509) {}
        try {
            this.gr = this.getParameterWithDefault("keepDocumentAdjustments", null);
        }
        catch (Exception ex510) {}
        try {
            this.f6 = this.getParameterWithDefault("adjustControls1", null);
        }
        catch (Exception ex511) {}
        try {
            this.f7 = this.getParameterWithDefault("adjustControls4", null);
        }
        catch (Exception ex512) {}
        try {
            this.f8 = this.getParameterWithDefault("adjustControls8", null);
        }
        catch (Exception ex513) {}
        try {
            this.f9 = this.getParameterWithDefault("adjustControls24", null);
        }
        catch (Exception ex514) {}
        try {
            this.he = this.getParameterWithDefault("fileButtons", null);
        }
        catch (Exception ex515) {}
        try {
            this.hf = this.getParameterWithDefault("fileButtonOpen", null);
        }
        catch (Exception ex516) {}
        try {
            this.hg = this.getParameterWithDefault("fileButtonClose", null);
        }
        catch (Exception ex517) {}
        try {
            this.hh = this.getParameterWithDefault("fileButtonSave", null);
        }
        catch (Exception ex518) {}
        try {
            this.hi = this.getParameterWithDefault("allowHeaderTags", null);
        }
        catch (Exception ex519) {}
        try {
            this.hj = this.getParameterWithDefault("allowHeaderTagsOnPrint", null);
        }
        catch (Exception ex520) {}
        try {
            this.hk = this.getParameterWithDefault("allowAnnotationRotationOnTags", null);
        }
        catch (Exception ex521) {}
        try {
            this.hl = this.getParameterWithDefault("allowResolutionTags", null);
        }
        catch (Exception ex522) {}
        try {
            this.hm = this.getParameterWithDefault("fileKeys", null);
        }
        catch (Exception ex523) {}
        try {
            this.hn = this.getParameterWithDefault("fileKeyOpen", null);
        }
        catch (Exception ex524) {}
        try {
            this.ho = this.getParameterWithDefault("fileKeyClose", null);
        }
        catch (Exception ex525) {}
        try {
            this.hp = this.getParameterWithDefault("fileKeySave", null);
        }
        catch (Exception ex526) {}
        try {
            this.hr = this.getParameterWithDefault("newWindowKey", null);
        }
        catch (Exception ex527) {}
        try {
            this.h4 = this.getParameterWithDefault("fileMenus", null);
        }
        catch (Exception ex528) {}
        try {
            this.h5 = this.getParameterWithDefault("fileMenuOpen", null);
        }
        catch (Exception ex529) {}
        try {
            this.h6 = this.getParameterWithDefault("fileMenuClose", null);
        }
        catch (Exception ex530) {}
        try {
            this.h7 = this.getParameterWithDefault("fileMenuSave", null);
        }
        catch (Exception ex531) {}
        try {
            this.h8 = this.getParameterWithDefault("menuNewWindow", null);
        }
        catch (Exception ex532) {}
        try {
            this.ht = this.getParameterWithDefault("infoOptions", null);
        }
        catch (Exception ex533) {}
        try {
            this.hs = this.getParameterWithDefault("flipOptions", null);
        }
        catch (Exception ex534) {}
        try {
            this.hw = this.getParameterWithDefault("helpURL", null);
        }
        catch (Exception ex535) {}
        try {
            this.hx = this.getParameterWithDefault("helpTarget", null);
        }
        catch (Exception ex536) {}
        try {
            this.hy = this.getParameterWithDefault("saveAllOptions", null);
        }
        catch (Exception ex537) {}
        try {
            this.hz = this.getParameterWithDefault("clipOptions", null);
        }
        catch (Exception ex538) {}
        try {
            this.h0 = this.getParameterWithDefault("magOptions", null);
        }
        catch (Exception ex539) {}
        try {
            this.h1 = this.getParameterWithDefault("zoom100Options", null);
        }
        catch (Exception ex540) {}
        try {
            this.h2 = this.getParameterWithDefault("zoom100", null);
        }
        catch (Exception ex541) {}
        try {
            this.h3 = this.getParameterWithDefault("zoom", null);
        }
        catch (Exception ex542) {}
        try {
            this.gf = this.getParameterWithDefault("cachePath", null);
        }
        catch (Exception ex543) {}
        try {
            this.hu = this.getParameterWithDefault("infoOptionsShowSize", null);
        }
        catch (Exception ex544) {}
        try {
            this.hv = this.getParameterWithDefault("infoOptionsShowSizeCm", null);
        }
        catch (Exception ex545) {}
        if (this.gf != null) {
            while (this.gf.endsWith("/")) {
                this.gf = this.gf.substring(0, this.gf.length() - 1);
            }
            while (this.gf.endsWith("\\")) {
                this.gf = this.gf.substring(0, this.gf.length() - 1);
            }
        }
        try {
            this.jo = this.getParameterWithDefault("printHeader", null);
        }
        catch (Exception ex546) {}
        try {
            this.jp = this.getParameterWithDefault("printHeaderLF", null);
        }
        catch (Exception ex547) {}
        try {
            this.nt = this.getParameterWithDefault("nativePDFCache", null);
        }
        catch (Exception ex548) {}
        try {
            this.jq = this.getParameter("imageTooLargePrintHeader");
        }
        catch (Exception ex549) {}
        try {
            this.jr = this.getParameterWithDefault("allowPrintHeader", null);
        }
        catch (Exception ex550) {}
        try {
            this.js = this.getParameterWithDefault("forcePrintsPerPage", null);
        }
        catch (Exception ex551) {}
        try {
            this.jt = this.getParameterWithDefault("forcegc", null);
        }
        catch (Exception ex552) {}
        try {
            this.jx = this.getParameterWithDefault("states", null);
        }
        catch (Exception ex553) {}
        try {
            this.jy = this.getParameterWithDefault("photometric", null);
        }
        catch (Exception ex554) {}
        try {
            this.gk = this.getParameterWithDefault("retrievalText", null);
        }
        catch (Exception ex555) {}
        try {
            this.go = this.getParameterWithDefault("retrievalTextDelay", null);
        }
        catch (Exception ex556) {}
        try {
            this.gp = this.getParameterWithDefault("imageDecodeTextDelay", null);
        }
        catch (Exception ex557) {}
        try {
            this.gl = this.getParameterWithDefault("maxDBCache", null);
        }
        catch (Exception ex558) {}
        try {
            this.gn = this.getParameterWithDefault("maxDBCacheMem", null);
        }
        catch (Exception ex559) {}
        try {
            this.j0 = this.getParameterWithDefault("progressMessage", null);
        }
        catch (Exception ex560) {}
        try {
            this.j1 = this.getParameterWithDefault("progressThread", null);
        }
        catch (Exception ex561) {}
        try {
            this.no = this.getParameterWithDefault("multiPrintNum", null);
        }
        catch (Exception ex562) {}
        try {
            this.np = this.getParameterWithDefault("multiPrintGap", null);
        }
        catch (Exception ex563) {}
        try {
            this.nq = this.getParameterWithDefault("multiPrintSingleHeader", null);
        }
        catch (Exception ex564) {}
        try {
            this.ia = this.getParameterWithDefault("visibleImageButtons", null);
        }
        catch (Exception ex565) {}
        try {
            this.pe = this.getParameterWithDefault("annotationHideButtons", null);
        }
        catch (Exception ex566) {}
        try {
            this.pw = this.getParameterWithDefault("annotationHideContextButtons", null);
        }
        catch (Exception ex567) {}
        try {
            this.px = this.getParameterWithDefault("annotationHideContextButtonsIds", null);
        }
        catch (Exception ex568) {}
        try {
            this.py = this.m("annotationHideContextButtons");
        }
        catch (Exception ex569) {}
        try {
            this.pz = this.m("annotationHideContextButtonsIds");
        }
        catch (Exception ex570) {}
        try {
            this.pj = this.getParameterWithDefault("annotationAllowHideAll", null);
        }
        catch (Exception ex571) {}
        try {
            this.p0 = this.getParameterWithDefault("annotationStatusLength", null);
        }
        catch (Exception ex572) {}
        try {
            this.pk = this.getParameterWithDefault("fnSecURL", null);
        }
        catch (Exception ex573) {}
        try {
            this.pl = this.getParameterWithDefault("annotationSecurityDialogDataString", null);
        }
        catch (Exception ex574) {}
        try {
            this.pq = this.getParameterWithDefault("securityDialogClass", null);
        }
        catch (Exception ex575) {}
        try {
            this.pr = this.getParameterWithDefault("securityDialogJar", null);
        }
        catch (Exception ex576) {}
        try {
            final String parameterWithDefault5 = this.getParameterWithDefault("fnStickyNoteView", null);
            if (parameterWithDefault5 != null && !parameterWithDefault5.equals("")) {
                this.pm = q(parameterWithDefault5);
            }
        }
        catch (Exception ex577) {}
        try {
            this.pn = this.getParameterWithDefault("signatureSelector", null);
        }
        catch (Exception ex578) {}
        try {
            this.po = this.getParameterWithDefault("signatureSelectorURL", null);
        }
        catch (Exception ex579) {}
        try {
            this.pp = this.getParameterWithDefault("signatureSelectorWindowName", null);
        }
        catch (Exception ex580) {}
        try {
            this.pu = this.getParameterWithDefault("suppressMenusDuringOpen", null);
        }
        catch (Exception ex581) {}
        try {
            this.pt = this.getParameterWithDefault("annotationDynamicTextCreation", null);
        }
        catch (Exception ex582) {}
        try {
            ji.util.d.lg = this.getParameterWithDefault("dotNetSecurityErrorURL", null);
        }
        catch (Exception ex583) {}
        try {
            ji.util.d.lh = this.getParameterWithDefault("dotNetSecurityErrorText", null);
        }
        catch (Exception ex584) {}
        try {
            ji.util.d.li = this.getParameterWithDefault("dotNetSecurityErrorFile", null);
        }
        catch (Exception ex585) {}
        try {
            this.ic = this.getParameterWithDefault("visibleImageKeys", null);
        }
        catch (Exception ex586) {}
        try {
            this.ie = this.getParameterWithDefault("visibleImageMenus", null);
        }
        catch (Exception ex587) {}
        try {
            this.h9 = this.getParameterWithDefault("imageButtons", null);
        }
        catch (Exception ex588) {}
        try {
            this.ib = this.getParameterWithDefault("imageKeys", null);
        }
        catch (Exception ex589) {}
        try {
            this.id = this.getParameterWithDefault("imageMenus", null);
        }
        catch (Exception ex590) {}
        try {
            this.if = this.getParameterWithDefault("invertButtons", null);
        }
        catch (Exception ex591) {}
        try {
            this.ig = this.getParameterWithDefault("invertKeys", null);
        }
        catch (Exception ex592) {}
        try {
            this.ih = this.getParameterWithDefault("invertMenus", null);
        }
        catch (Exception ex593) {}
        try {
            this.ii = this.getParameterWithDefault("pageButtons", null);
        }
        catch (Exception ex594) {}
        try {
            this.ij = this.getParameterWithDefault("pageKeys", null);
        }
        catch (Exception ex595) {}
        try {
            this.ik = this.getParameterWithDefault("selectKeys", null);
        }
        catch (Exception ex596) {}
        try {
            this.il = this.getParameterWithDefault("pageMenus", null);
        }
        catch (Exception ex597) {}
        try {
            this.im = this.getParameterWithDefault("selectMenus", null);
        }
        catch (Exception ex598) {}
        try {
            this.i1 = this.getParameterWithDefault("printButtons", null);
        }
        catch (Exception ex599) {}
        try {
            this.i2 = this.getParameterWithDefault("printKeys", null);
        }
        catch (Exception ex600) {}
        try {
            this.i3 = this.getParameterWithDefault("printMenus", null);
        }
        catch (Exception ex601) {}
        try {
            this.i5 = this.getParameterWithDefault("printMonoAllowed", null);
        }
        catch (Exception ex602) {}
        try {
            this.i6 = this.getParameterWithDefault("printMono", null);
        }
        catch (Exception ex603) {}
        try {
            this.i4 = this.getParameterWithDefault("printStickyMenus", null);
        }
        catch (Exception ex604) {}
        try {
            this.jc = this.getParameterWithDefault("cacheMenus", null);
        }
        catch (Exception ex605) {}
        try {
            this.i7 = this.getParameterWithDefault("viewButtons", null);
        }
        catch (Exception ex606) {}
        try {
            this.i8 = this.getParameterWithDefault("viewButtonsDetail", null);
        }
        catch (Exception ex607) {}
        try {
            this.i9 = this.getParameterWithDefault("viewKeys", null);
        }
        catch (Exception ex608) {}
        try {
            this.ja = this.getParameterWithDefault("zoomKeys", null);
        }
        catch (Exception ex609) {}
        try {
            this.jb = this.getParameterWithDefault("viewMenus", null);
        }
        catch (Exception ex610) {}
        try {
            this.je = this.getParameterWithDefault("preferenceMenus", null);
        }
        catch (Exception ex611) {}
        try {
            this.jd = this.getParameterWithDefault("helpMenus", null);
        }
        catch (Exception ex612) {}
        try {
            this.jf = this.getParameterWithDefault("scrollbars", null);
        }
        catch (Exception ex613) {}
        try {
            this.jg = this.getParameterWithDefault("statusBar", null);
        }
        catch (Exception ex614) {}
        try {
            this.j2 = this.getParameterWithDefault("initialfocus", null);
        }
        catch (Exception ex615) {}
        try {
            this.j3 = this.getParameterWithDefault("crc32checksumFile", null);
        }
        catch (Exception ex616) {}
        try {
            this.rj = this.getParameterWithDefault("filenetColdHeaderMode", null);
        }
        catch (Exception ex617) {}
        try {
            this.rk = this.getParameterWithDefault("textAndStampBoundsChecking", null);
        }
        catch (Exception ex618) {}
        this.a(85, "traceRender");
        this.a(172, "java2filedialogs");
        this.a(176, "ie7filedialogtest");
        this.a(229, "enableFirstLastDocButtons");
        this.a(254, "checkComponentVersion");
        this.a(261, "ignoreURLAnchorInCache");
        this.a(283, "convertHttpResponseCodes");
        try {
            final String parameterWithDefault6 = this.getParameterWithDefault("pdfLibraryVersion", null);
            if (parameterWithDefault6 != null) {
                ji.filter.pdf.ci.a(this.uz, q(parameterWithDefault6));
            }
        }
        catch (Exception ex619) {}
        this.a(286, "useStaticPDFJNILOCK");
        this.i("nwJSsetRedactionIsSemiTransparent");
        try {
            this.kg = this.getParameterWithDefault("cabbase", null);
        }
        catch (Exception ex620) {}
        if (this.kg == null) {
            try {
                this.kg = this.getParameterWithDefault("cabinets", null);
            }
            catch (Exception ex621) {}
        }
        try {
            this.kh = this.getParameterWithDefault("archive", null);
        }
        catch (Exception ex622) {}
        if (this.kh == null) {
            try {
                this.kh = this.getParameterWithDefault("cache_archive", null);
            }
            catch (Exception ex623) {}
        }
        if (this.kh == null) {
            try {
                this.kh = this.getParameterWithDefault("cache-archive", null);
            }
            catch (Exception ex624) {}
        }
        try {
            this.ki = this.getParameterWithDefault("name", null);
        }
        catch (Exception ex625) {}
        if (this.ki == null) {
            try {
                this.ki = this.getParameterWithDefault("id", null);
            }
            catch (Exception ex626) {}
        }
        this.y();
        ji.util.d.l(this.kh, this.kg);
        ji.util.d.a(this.t6);
        if (this.fh != null) {
            if (ji.util.d.ay(this.uz) && !ji.util.d.am(this.uz)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("The event handler functionality is not available on MacOS due to browser limitations. (").append(this.jw).append(")"))));
            }
            else if (this.jw == null) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("The MayScript tag needs to be included in your HTML, and set to a value of 'TRUE' for your EventHandler to work (").append(this.fh).append(")."))));
            }
            else if (!this.ai()) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("The MayScript tag needs to be set to a value of 'TRUE' for your EventHandler to work (").append(this.fh).append(")."))));
            }
        }
        this.i("allowSaveAttachments");
        this.i("allowExternalImages");
        this.i("htmlDecoderMax");
        this.i("blockOfficeModule");
        if (this.md != null) {
            final int q = q(this.md);
            if (ji.util.d.am(q)) {
                String pr = this.pr;
                if (ji.util.d.by(pr)) {
                    if (q == 3) {
                        pr = "p8securityDlg.jar";
                    }
                    else if (q == 4) {
                        pr = "viewer-security-dialog.jar";
                    }
                }
                if (!ji.util.d.by(pr)) {
                    if (ji.util.d.f8 != null) {
                        if (ji.util.d.f8.toLowerCase().indexOf(pr.toLowerCase()) < 0) {
                            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("*** ERROR IN HTML *** Missing \"").append(pr).append("\" value from ARCHIVE parameter! (\"").append(ji.util.d.f8).append("\")"))));
                        }
                    }
                    else {
                        ji.io.h.d(this.uz, "*** ERROR IN HTML *** Missing ARCHIVE parameter!");
                    }
                }
            }
        }
        else if (!ji.util.d.by(this.pq)) {
            final String pr2 = this.pr;
            if (ji.util.d.by(pr2)) {
                ji.io.h.d(this.uz, "*** ERROR IN HTML *** HTML Parameter securityDialogJar has not been set.");
            }
            else if (ji.util.d.f8 != null) {
                if (ji.util.d.f8.toLowerCase().indexOf(pr2.toLowerCase()) < 0) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("*** ERROR IN HTML *** Missing \"").append(pr2).append("\" value from ARCHIVE parameter! (\"").append(ji.util.d.f8).append("\")"))));
                }
            }
            else {
                ji.io.h.d(this.uz, "*** ERROR IN HTML *** Missing ARCHIVE parameter!");
            }
        }
        this.i("pdf7RenderFix1");
        this.i("pdf7RenderFix2");
        this.i("newAnnotationStampMethod");
        this.i("cookieHandler");
        this.i("separateThumbsNetCache");
        this.i("disableIORedirectPDF");
        this.i("tiffConsolidateStripsG31D");
        this.i("showEMLAttachments");
        this.i("attachmentTarget");
        this.i("attachmentBaseURL");
        this.j("attachmentID");
        this.j("attachmentBaseURLPage");
        this.i("bulkJSQueue");
        this.i("preventStartAnnotationRender");
        if (this.d != null) {
            this.d.a(this.n9);
        }
    }
    
    private String[] m(final String s) {
        String[] array = null;
        int n = 0;
        int i = 1;
        try {
            while (i != 0) {
                if (this.getParameterWithDefault(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(n + 1))), null) == null) {
                    i = 0;
                }
                else {
                    ++n;
                }
            }
        }
        catch (Exception ex) {}
        if (n > 0) {
            array = new String[n];
            int j = 1;
            int n2 = 0;
            try {
                while (j != 0) {
                    final String parameterWithDefault = this.getParameterWithDefault(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(n2 + 1))), null);
                    if (parameterWithDefault == null) {
                        j = 0;
                    }
                    else {
                        if (parameterWithDefault.length() <= 0) {
                            array[n2] = null;
                        }
                        else {
                            array[n2] = parameterWithDefault;
                        }
                        ++n2;
                    }
                }
            }
            catch (Exception ex2) {}
        }
        return array;
    }
    
    public void finalize() {
        if (ji.util.d.cz() || ji.util.d.an || ji.util.d.ao) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("finalize ---- applet ").append(this.uz).append("/").append(this))));
        }
    }
    
    private void q() {
        try {
            if (this.tx) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "jiStartAA1");
                }
                this.t();
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "jiStartAA2");
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "jiStartAA3");
                }
            }
        }
        catch (Exception ex) {}
        finally {
            jiApplet.t9 = false;
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                ji.io.h.d(this.uz, "jiStartAA4");
            }
        }
    }
    
    private final boolean r() {
        try {
            if (ji.util.d.em()) {
                this.t8 = false;
                final bb bb = new bb(this.uz, new tw(this));
                bb.start();
                ji.util.d.a(bb, this.uz);
            }
            else {
                this.t8 = ji.util.cn.b(this.getCodeBase(), this, this.uz);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.t8;
    }
    
    private final void s() {
        try {
            if (ji.util.d.em()) {
                this.t8 = false;
                final bb bb = new bb(this.uz, new ty(this));
                bb.start();
                ji.util.d.a(bb, this.uz);
            }
            else {
                ji.io.h.d(this.uz, "Removing local installation...");
                ji.util.cn.d(this.getCodeBase(), this, this.uz);
                ji.io.h.d(this.uz, "Removed...");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void t() {
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
            ji.io.h.d(this.uz, "jiStart");
        }
        this.sd = false;
        this.se = false;
        try {
            ++jiApplet.rr;
            if (ji.util.d.ai(this.uz)) {
                this.p();
            }
            if (this.z != null) {
                ji.util.i.a(27, p(this.z));
            }
            else {
                ji.util.i.b(27);
            }
            if (this.aa != null) {
                ji.util.d.i(q(this.aa));
            }
            if (this.x != null) {
                ji.util.i.a(25, p(this.x));
            }
            else {
                ji.util.i.a(25);
            }
            if (this.y != null) {
                ji.util.i.a(26, p(this.y));
            }
            else {
                ji.util.i.a(26);
            }
            if (this.cw != null) {
                ji.util.i.a(35, p(this.cw));
            }
            if (this.cz != null) {
                ji.util.i.b(1, ji.font.j.h(q(this.cz)));
            }
            if (this.fq != null) {
                ji.util.e.l(this.fq);
            }
            if (this.gf != null) {
                ji.util.d.a(this.gf, this, this.uz);
            }
            ji.util.e.a(this.getCodeBase());
            ji.util.e.v = this;
            if (jiApplet.sg) {
                ji.io.h.d(this.uz, "*** User did not restart all browser instances as requested before trying to use viewONE!");
                ji.io.h.d(this.uz, "*** Re-promtping user (in English - because local langiage files have been removed)");
                ji.util.d.a("Please restart your browser!", (af)null, this.uz);
                try {
                    if (ji.util.d.c) {
                        ji.io.h.d(this.uz, "System Exit 5");
                    }
                    else {
                        System.exit(0);
                    }
                }
                catch (Exception ex2) {}
                return;
            }
            if (this.r()) {
                this.z();
                if (ji.util.i.c(26)) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.uz, "Local removal required....");
                    }
                    this.y();
                    final ab u = this.u();
                    ji.util.d.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a(684, this.uz, u)))).append("\n").append(ji.util.d.a(685, this.uz, u)).append("\n").append(ji.util.d.a(686, this.uz, u)).append("\n").append(ji.util.d.a(687, this.uz, u)).append("\n").append(ji.util.d.a(688, this.uz, u)).append("\n").append(ji.util.d.a(689, this.uz, u)).append("\n").append(ji.util.d.a(690, this.uz, u)).append("\n").append(ji.util.d.a(691, this.uz, u)))), (af)null, this.uz);
                    this.s();
                    ji.io.h.d(this.uz, "Requesting user restarts browser.");
                    ji.util.d.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a(692, this.uz, u)))).append("\n").append(ji.util.d.a(693, this.uz, u)).append("\n").append(ji.util.d.a(694, this.uz, u)).append("\n").append(ji.util.d.a(695, this.uz, u)).append("\n").append(ji.util.d.a(696, this.uz, u)).append("\n").append(ji.util.d.a(697, this.uz, u)))), (af)null, this.uz);
                    jiApplet.sg = true;
                    ji.io.h.d(this.uz, "User clicked OK. Ending applet to permit restart.");
                    ji.util.cn.a(this.getCodeBase(), this.uz);
                    try {
                        if (ji.util.d.c) {
                            ji.io.h.d(this.uz, "System Exit 6");
                        }
                        else {
                            System.exit(0);
                        }
                    }
                    catch (Exception ex3) {}
                    return;
                }
                ji.io.h.d(this.uz, "Unable to remove cached version due to ACMCanRemoveCachedVersion=FALSE");
                ji.io.h.d(this.uz, "Continuing...");
            }
            if (!this.sd && !this.se) {
                if (!this.iy) {
                    if (ji.util.i.c(70) && (this.a == 5 || this.a == 3)) {
                        if (ji.util.d.cy() || ji.util.i.c(131)) {
                            ji.io.h.d(this.uz, "Attempting to install IE shutdown handler");
                        }
                        try {
                            if (this.up != null) {
                                if (ji.util.d.cy() || ji.util.i.c(131)) {
                                    ji.io.h.d(this.uz, "Setting IE shutdown handler on EP4");
                                }
                                this.up.a(new cr(this));
                            }
                        }
                        catch (Exception ex) {
                            if (ji.util.d.cy() || ji.util.i.c(131)) {
                                ji.io.h.d(this.uz, "Unable to install IE shutdown handler: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                            }
                        }
                    }
                }
                else if (ji.util.i.c(124) || ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "Not installing IE shutdown handler, this is a quickstart instance");
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                    ji.io.h.d(this.uz, "jiStartA");
                }
                if (!this.r4) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                        ji.io.h.d(this.uz, "jiStartB");
                    }
                    if (this.r9 == null || ji.util.d.ai(this.uz)) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                            ji.io.h.d(this.uz, "jiStartC");
                        }
                        this.initializeImage(false);
                    }
                    else {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                            ji.io.h.d(this.uz, "jiStartD");
                        }
                        this.ao();
                    }
                }
                else {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                        ji.io.h.d(this.uz, "jiStartE1");
                    }
                    this.initializeImage(false);
                }
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                ji.io.h.d(this.uz, "jiStartF");
            }
            if (this.d != null && b(this.uz)) {
                this.d.a1();
            }
        }
        finally {
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
                ji.io.h.d(this.uz, "jiStartD");
            }
            --jiApplet.rr;
        }
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao) {
            ji.io.h.d(this.uz, "jiStartE2");
        }
    }
    
    private ab u() {
        final String n = ji.res.s.n(this.uz);
        final ab ab = new ab(this.uz, "lanMessages");
        final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n))).append(".").append("v1")));
        try {
            final byte[] a = ji.util.cn.a(this.getCodeBase(), value, this, this.uz);
            if (a != null) {
                if (ji.util.d.c6) {
                    ab.a(null, a, null, false);
                }
                else {
                    ab.a(null, a, null);
                }
            }
            else {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("Local translation ").append(n).append(" not loaded, using server messages."))));
            }
        }
        catch (Exception ex) {
            try {
                final byte[] a2 = this.a(new URL(this.getCodeBase(), value), false);
                if (a2 == null) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("Server based, local translation ").append(n).append(" not loaded, using server messages."))));
                    return ab;
                }
                final byte[] b = ji.util.d.b(a2, value, false, new y(), this.uz);
                if (ji.util.d.c6) {
                    ab.a(null, b, null, false);
                    return ab;
                }
                ab.a(null, b, null);
                return ab;
            }
            catch (Exception ex2) {
                ex.printStackTrace();
            }
        }
        return ab;
    }
    
    private final byte[] a(final URL url, final boolean b) {
        try {
            int read = -1;
            final URLConnection a = ji.util.d.a(url, b, this.uz);
            int i = a.getContentLength();
            final InputStream inputStream = a.getInputStream();
            boolean b2 = false;
            if (i < 0) {
                i = inputStream.available();
                if (i == 0) {
                    read = inputStream.read();
                    if (read == -1) {
                        i = 0;
                    }
                    else {
                        i = inputStream.available();
                    }
                }
                b2 = true;
            }
            final int bb = ji.util.d.bb();
            if (b2) {
                int j = i;
                byte[] array = null;
                int n = 0;
                final Vector vector = new Vector<byte[]>();
                if (read != -1) {
                    ++n;
                    vector.addElement(new byte[] { (byte)read });
                }
                while (j > 0) {
                    if (bb > 0) {
                        ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("> Net: Simulated delay(1) ").append(bb).append(" milliseconds: ").append(url))));
                        ji.util.d.b(bb, 3000, this.uz);
                    }
                    if (array == null) {
                        array = new byte[j];
                    }
                    else if (array.length < j) {
                        array = new byte[j];
                    }
                    final int read2 = inputStream.read(array, 0, j);
                    n += read2;
                    if (read2 > 0) {
                        final byte[] array2 = new byte[read2];
                        System.arraycopy(array, 0, array2, 0, read2);
                        vector.addElement(array2);
                        j = inputStream.available();
                    }
                }
                final byte[] array3 = new byte[n];
                int n2 = 0;
                while (vector.size() > 0) {
                    final byte[] array4 = vector.elementAt(0);
                    System.arraycopy(array4, 0, array3, n2, array4.length);
                    n2 += array4.length;
                    vector.removeElementAt(0);
                }
                inputStream.close();
                return array3;
            }
            final byte[] array5 = new byte[i];
            int n3 = 0;
            while (i > 0) {
                if (bb > 0) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("> Net: Simulated delay(2) ").append(bb).append(" milliseconds: ").append(url))));
                    ji.util.d.b(bb, 3001, this.uz);
                }
                final int read3 = inputStream.read(array5, n3, i);
                n3 += read3;
                i -= read3;
            }
            inputStream.close();
            return array5;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private final void v() {
        try {
            if (this.rp != null) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "Unlocking lockCommand");
                }
                this.rp.a();
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "Unlocked lockCommand");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void w() {
        try {
            if (this.r0 != null) {
                this.r0.a();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void x() {
        try {
            if (this.d != null) {
                this.d.f8();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void y() {
        if (this.mb != null && p(this.mb)) {
            this.m7 = "IBM Corporation";
            if (this.md != null) {
                this.m8 = ji.util.ch.a(q(this.md));
            }
            else {
                this.m8 = "FileNet JavaView";
            }
            this.m9 = "http://www.ibm.com/support";
            this.na = "";
            ji.res.s.a(true);
        }
        if (ji.util.i.c(239)) {
            ji.util.d.at = "vnbt1.v1";
            ji.util.d.au = "vnbt2.v1";
            ji.util.d.av = "vnbt3.v1";
            ji.util.d.aw = "vnbt4.v1";
            this.m8 = "Vignette Viewer";
            this.m7 = "Vignette Corporation";
            this.m9 = "http://connect.vignette.com";
            this.na = "support@vignette.com";
            ji.res.s.a(true);
        }
        if (this.m7 != null) {
            ji.res.s.g(this.m7);
        }
        if (this.m8 != null) {
            ji.res.s.f(this.m8);
        }
        if (this.m9 != null) {
            ji.res.s.h(this.m9);
        }
        if (this.na != null) {
            ji.res.s.l(this.na);
        }
    }
    
    private final void z() {
        try {
            if (this.eb != null) {
                ji.util.i.a(36, p(this.eb));
            }
            if (this.d1 != null) {
                ji.util.i.a(78, p(this.d1));
            }
            if (this.d4 != null) {
                ji.util.d.bs(p(this.d4));
            }
            if (this.d6 != null) {
                ji.util.i.a(159, p(this.d6));
            }
            if (this.dz != null) {
                ji.util.i.a(102, p(this.dz));
            }
            this.a(168, "traceScrollbar");
            if (this.d7 != null) {
                ji.util.i.a(89, p(this.d7));
            }
            if (this.eo != null) {
                ji.util.i.a(145, p(this.eo));
            }
            if (this.d9 != null) {
                ji.util.i.a(37, p(this.d9));
            }
            if (this.ea != null) {
                ji.util.i.a(57, p(this.ea));
            }
            if (this.d3 != null) {
                ji.util.i.a(84, p(this.d3));
            }
            if (this.ed != null) {
                ji.util.i.a(4, p(this.ed));
            }
            if (this.ec != null) {
                ji.util.i.a(34, p(this.ec));
            }
            if (this.eg != null) {
                ji.util.i.a(40, p(this.eg));
            }
            if (this.ee != null) {
                ji.util.i.a(6, p(this.ee));
            }
            if (this.ef != null) {
                ji.util.i.a(105, p(this.ef));
            }
            if (this.eh != null) {
                ji.util.i.a(5, p(this.eh));
            }
            if (this.ei != null) {
                ji.util.i.a(284, p(this.ei));
            }
            if (this.ej != null) {
                ji.util.i.a(126, p(this.ej));
            }
            if (this.ek != null) {
                ji.util.d.bt(p(this.ek));
            }
            if (this.el != null) {
                ji.util.d.bu(p(this.el));
            }
            if (this.em != null) {
                ji.util.d.bv(p(this.em));
            }
            if (this.lr != null) {
                ji.util.i.a(95, p(this.lr));
            }
            else {
                ji.util.i.a(95, false);
            }
        }
        catch (Exception ex) {}
    }
    
    public void initializeImage(final boolean b) {
        boolean b2 = false;
        boolean p = true;
        boolean b3 = true;
        boolean b4 = false;
        boolean b5 = false;
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "jiStartInit1");
        }
        try {
            this.y();
            try {
                if (this.qz != null) {
                    for (int i = 0; i < this.qz.length; ++i) {
                        try {
                            final int index = this.qz[i].indexOf("=");
                            ji.res.s.b(ji.util.d.bc(this.qz[i].substring(0, index)), ji.util.d.bc(this.qz[i].substring(index + 1)));
                        }
                        catch (Exception ex5) {}
                    }
                }
            }
            catch (Exception ex6) {}
            this.r8 = true;
            this.ry = false;
            if (this.gf != null) {
                ji.util.d.o(true);
            }
            if (this.k0 != null) {
                ji.util.i.a(110, p(this.k0));
            }
            final URL codeBase = this.getCodeBase();
            try {
                if (this.rd != null) {
                    ((Hashtable<String, String>)System.getProperties()).put("sun.java2d.noddraw", this.rd);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.sj != null) {
                ji.util.i.a(60, q(this.sj) % 3 == 0);
            }
            else if (this.sk != null) {
                ji.util.i.a(60, p(this.sk));
            }
            else {
                ji.util.i.a(60, false);
            }
            ji.util.d.by(true);
            if (this.j8 != null) {
                b4 |= p(this.j8);
            }
            if (this.dv != null) {
                b4 |= p(this.dv);
            }
            if (this.d5 != null) {
                b4 |= p(this.d5);
            }
            if (this.dq != null) {
                b4 |= p(this.dq);
            }
            if (this.d4 != null) {
                b4 |= p(this.d4);
            }
            if (this.d6 != null) {
                b4 |= p(this.d6);
            }
            if (this.d9 != null) {
                b4 |= p(this.d9);
            }
            if (this.ea != null) {
                b4 |= p(this.ea);
            }
            if (this.ed != null) {
                b4 |= p(this.ed);
            }
            if (this.d2 != null) {
                b4 |= p(this.d2);
            }
            if (this.d3 != null) {
                b4 |= p(this.d3);
            }
            if (this.d1 != null) {
                b4 |= p(this.d1);
            }
            if (this.d7 != null) {
                b4 |= p(this.d7);
            }
            if (this.ek != null) {
                b4 |= p(this.ek);
            }
            if (this.el != null) {
                b4 |= p(this.el);
            }
            if (this.em != null) {
                b4 |= p(this.em);
            }
            if (this.on != null) {
                b4 |= p(this.on);
            }
            if (this.ee != null) {
                b4 |= p(this.ee);
            }
            if (this.eg != null) {
                b4 |= p(this.eg);
            }
            if (b4) {
                ji.util.d.a(this, null, null, this.uz);
            }
            if (this.j8 != null) {
                ji.util.d.a7(p(this.j8));
            }
            if (this.j9 != null) {
                ji.util.d.a8(p(this.j9));
            }
            if (this.f != null) {
                ji.res.s.e(this.f.toLowerCase());
            }
            if (this.mh != null) {
                ji.util.i.a(7, p(this.mh));
            }
            else {
                ji.util.i.a(7, true);
            }
            String parameterWithDefault = null;
            try {
                parameterWithDefault = this.getParameterWithDefault("SwingLookAndFeel", null);
            }
            catch (Exception ex7) {}
            if (parameterWithDefault != null && ji.util.e.av()) {
                this.n(parameterWithDefault);
            }
            if (!this.iy) {
                if (this.it != null) {
                    ji.util.i.a(103, p(this.it));
                }
                else {
                    ji.util.i.a(103, false);
                }
                if (this.hw != null) {
                    ji.util.i.a(16, false);
                }
                if (this.mi != null) {
                    ji.util.i.a(15, p(this.mi));
                }
                if (this.mj != null) {
                    ji.util.i.a(120, p(this.mj));
                }
                if (this.mo != null) {
                    ji.util.i.a(14, p(this.mo));
                }
                if (this.mk != null) {
                    ji.util.i.a(12, p(this.mk));
                }
                if (this.ml != null) {
                    ji.util.i.a(21, p(this.ml));
                }
                if (this.mm != null) {
                    ji.util.i.a(94, p(this.mm));
                }
                if (this.mn != null) {
                    ji.util.i.a(97, p(this.mn));
                }
                if (this.mz != null) {
                    ji.util.d.l(q(this.mz));
                }
                if (this.mq != null) {
                    final boolean p2 = p(this.mq);
                    ji.util.d.m(p2);
                    if (!p2) {
                        ji.util.d.l(2);
                    }
                }
                if (this.my != null) {
                    ji.util.d.p(p(this.my));
                }
                if (this.mp != null) {
                    ji.util.d.q(p(this.mp));
                }
                if (this.mx != null) {
                    ji.util.i.a(1, p(this.mx));
                }
                if (this.mr != null) {
                    ji.util.d.k(q(this.mr));
                }
                if (this.ms != null) {
                    ji.util.d.j(q(this.ms));
                }
                if (this.az != null) {
                    ji.util.d.ad(e(this.az));
                }
                if (this.a0 != null) {
                    ji.util.i.a(177, p(this.a0));
                }
                boolean p3 = false;
                if (this.a5 != null) {
                    p3 = p(this.a5);
                }
                if (this.a1 != null) {
                    ji.util.d.ae(e(this.a1));
                }
                if (this.a2 != null) {
                    final int e = e(this.a2);
                    if (e == 0 || e == 1 || p3) {
                        ji.util.d.ag(e);
                    }
                    else {
                        ji.io.h.d(this.uz, "The web annotation write mode can no longer be set to ANT and WANG mode. Please contact Daeja if this functionality is required.");
                    }
                }
                if (this.a3 != null) {
                    final int e2 = e(this.a3);
                    if (e2 == 0 || e2 == 1 || p3) {
                        ji.util.d.af(e2);
                    }
                    else {
                        ji.io.h.d(this.uz, "The local annotation write mode can no longer be set to ANT and WANG mode. Please contact Daeja if this functionality is required.");
                    }
                }
                if (this.a4 != null) {
                    ji.util.i.a(48, p(this.a4));
                }
                if (this.mg != null) {
                    ji.util.d.r(p(this.mg));
                }
                if (this.k != null) {
                    ji.util.d.at = this.k;
                }
                if (this.l != null) {
                    ji.util.d.au = this.l;
                }
                if (this.m != null) {
                    ji.util.d.av = this.m;
                }
                if (this.n != null) {
                    ji.util.d.aw = this.n;
                }
                if (this.jh != null && !this.iy) {
                    try {
                        ji.util.e.a(ji.util.d.b1(this.jh));
                        this.setBackground(ji.util.d.b1(this.jh));
                    }
                    catch (Exception ex8) {}
                }
                else if (this.jh == null && !this.iy && ji.util.d.aw(this.uz) && ji.util.d.dp()) {
                    this.jh = "192,192,192";
                }
                if (this.jl != null) {
                    try {
                        ji.util.e.e(ji.util.d.b1(this.jl));
                    }
                    catch (Exception ex9) {}
                }
                else if (this.jk != null) {
                    try {
                        ji.util.e.e(ji.util.d.b1(this.jk));
                    }
                    catch (Exception ex10) {}
                }
                if (this.jm != null) {
                    try {
                        ji.util.e.f(ji.util.d.b1(this.jm));
                    }
                    catch (Exception ex11) {}
                }
                if (this.jn != null) {
                    try {
                        ji.util.d.w(p(this.jn));
                    }
                    catch (Exception ex12) {}
                }
                if (this.e6 != null) {
                    if (ji.util.d.bc(this.e6.toLowerCase()).startsWith("non")) {
                        ji.util.d.m((Color)null);
                    }
                    else {
                        ji.util.d.m(ji.util.d.b1(this.e6));
                    }
                }
            }
            if (this.dq != null) {
                ji.util.d.ba(p(this.dq));
            }
            if (this.dv != null) {
                ji.res.z.a(p(this.dv));
            }
            if (this.mb != null) {
                ji.util.d.z(p(this.mb));
            }
            if (this.t7) {
                this.t0 = true;
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiStartInit2");
            }
            if (this.d != null) {
                b3 = false;
                if (this.kr != null) {
                    this.d.ar(q(this.kr));
                }
            }
            else {
                this.getSize();
                jiApplet.sa = 0;
                if (this.d0 != null) {
                    ji.util.d.ao = p(this.d0);
                    ji.util.d.ak = p(this.d0);
                }
                if (this.et != null) {
                    ji.document.ad.dn(p(this.et));
                }
                this.z();
                if (this.cu != null) {
                    ji.util.i.a(186, p(this.cu));
                }
                if (this.fi != null && !this.iy) {
                    this.clearEventInterest();
                    this.registerEventsInterest(this.fi);
                }
                else if (this.fh != null && !this.iy) {
                    ji.io.h.d(this.uz, "The eventHandler parameter has been set but no events have been registered as being of interest.");
                    ji.io.h.d(this.uz, "Use the eventInterest parameter to specify events you wish to register an interest in and receive events for.");
                }
                if (!this.iy) {
                    if (this.ks != null) {
                        ji.util.d.p(q(this.ks));
                    }
                    if (this.kt != null) {
                        ji.util.d.q(q(this.kt));
                    }
                    if (this.dx != null) {
                        ji.document.ad.l(p(this.dx));
                    }
                    if (this.ep != null) {
                        ji.util.d.az(p(this.ep));
                    }
                    if (this.eq != null) {
                        if (p(this.eq)) {
                            ji.util.i.a(20);
                        }
                        else {
                            ji.util.i.b(20);
                        }
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStartInit3");
                    }
                    if (ji.util.d.dv()) {
                        ji.io.h.e(this.uz, "Start2");
                    }
                    if (this.es != null) {
                        ji.util.i.a(11, p(this.es));
                    }
                    ji.util.i.b(2);
                }
                ji.util.d.ac(this.gf);
                b5 = true;
                if (this.n1 != null && ji.util.e.av()) {
                    ji.util.i.a(10, q(this.n1));
                }
                if (this.n0 != null) {
                    ji.util.i.a(247, p(this.n0));
                }
                if (this.n3 != null) {
                    ji.util.i.a(271, p(this.n3));
                }
                if (this.n2 != null) {
                    ji.util.i.a(263, p(this.n2));
                }
                if (this.sf) {
                    this.d = new ad(false, null, null, this.re, null, this.gf, this.uz, false);
                }
                else {
                    final Applet c = this.c();
                    if (c == null && ji.util.i.c(105)) {
                        ji.io.h.d(this.uz, "QuickStart Applet: actual applet is null");
                    }
                    (this.d = new ad(true, c, this, this.fq, null, this.gf, this.uz, false)).aa(this.iy);
                }
                b5 = false;
                if (this.it != null) {
                    if (ji.util.i.c(105)) {
                        ji.io.h.d(this.uz, "QuickStart Applet: quickStartAllowed = ".concat(String.valueOf(String.valueOf(p(this.it)))));
                    }
                    if (p(this.it)) {
                        this.initQuickstart();
                    }
                }
                else if (jiQuickStart.d(this.uz) && ji.util.i.c(103)) {
                    this.initQuickstart();
                }
                if (ji.util.i.c(105)) {
                    ji.io.h.d(this.uz, "QuickStart Applet: helper = ".concat(String.valueOf(String.valueOf(this.uo))));
                }
                if (this.iy) {
                    this.ac();
                }
                else {
                    if (ji.util.d.dv()) {
                        ji.io.h.e(this.uz, "Start3");
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStartInit3A");
                    }
                    if (this.on != null) {
                        ji.document.ad.m(p(this.on));
                    }
                    if (this.qy != null) {
                        try {
                            for (int j = 0; j < this.qy.length; ++j) {
                                this.d.b(this.qy[j]);
                            }
                        }
                        catch (Exception ex13) {}
                    }
                    if (this.bk != null) {
                        final String[] array = new String[this.bk.length];
                        for (int k = 0; k < this.bk.length; ++k) {
                            array[k] = ji.util.d.b(this.af(), this.bk[k], this.uz).toString();
                        }
                        ji.res.z.a(array);
                    }
                    boolean b6 = false;
                    if (this.bz != null) {
                        for (int l = 0; l < this.bz.length; ++l) {
                            this.d.e(this.bz[l], 1);
                        }
                        b6 = true;
                    }
                    if (this.b0 != null) {
                        for (int n = 0; n < this.b0.length; ++n) {
                            this.d.e(this.b0[n], 2);
                        }
                        b6 = true;
                    }
                    if (b6) {
                        this.d.am();
                    }
                    b2 = true;
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit4");
                }
                this.d.a(this.af());
                if (this.ku != null) {
                    this.d.bu(p(this.ku));
                }
                if (this.mt != null) {
                    this.d.bq(p(this.mt));
                }
                else if (this.mu != null) {
                    this.d.bq(p(this.mu));
                }
                if (this.mv != null) {
                    this.d.br(p(this.mv));
                }
                else if (this.mw != null) {
                    this.d.br(p(this.mw));
                }
            }
            if (this.iy) {
                if (this.t0 && this.d.a(this)) {
                    this.t0 = false;
                }
            }
            else if (b2 || ji.util.d.ai(this.uz) || b || this.t7) {
                if (this.t0) {
                    this.r4 = ji.util.d.em();
                    if (this.r4) {
                        this.getContentPane().setLayout(new BorderLayout());
                        this.getContentPane().add(this.d, "Center");
                    }
                    else {
                        this.getContentPane().setLayout(null);
                        this.getContentPane().add(this.d);
                    }
                    if (this.kr != null) {
                        this.d.ar(q(this.kr));
                        if (q(this.kr) == 1) {
                            ji.util.d.d(209715200L);
                        }
                    }
                    if (this.f != null) {
                        this.d.an(this.f.toLowerCase());
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit5");
                }
                if (this.ki != null) {
                    this.d.t(this.ki);
                }
                if (this.e7 != null && !ji.util.d.by(this.e7)) {
                    this.d.k(ji.util.d.b1(this.e7));
                }
                if (this.e8 != null && !ji.util.d.by(this.e8)) {
                    this.d.j(ji.util.d.b1(this.e8));
                }
                if (this.fr != null) {
                    this.d.h(q(this.fr));
                }
                if (this.dt != null) {
                    this.d.f(ji.util.d.c(this.dt, 0));
                }
                if (this.du != null) {
                    this.d.g(ji.util.d.c(this.du, 0));
                }
                if (this.j0 != null) {
                    ji.document.ad.dy(p(this.j0));
                }
                if (this.j1 != null) {
                    ji.document.ad.dx(p(this.j1));
                }
                if (this.ka != null) {
                    ji.document.ad.ab(p(this.ka));
                }
                if (this.kb != null) {
                    this.d.ae(p(this.kb));
                }
                if (this.kc != null) {
                    this.d.af(p(this.kc));
                }
                if (this.lc != null) {
                    this.d.ai(p(this.lc));
                }
                if (this.lf != null) {
                    this.d.ag(p(this.lf));
                }
                if (this.lg != null) {
                    this.d.ah(p(this.lg));
                }
                if (this.ld != null) {
                    ji.util.i.a(38, p(this.ld));
                }
                else {
                    ji.util.i.a(38, false);
                }
                if (this.le != null) {
                    ji.util.i.a(39, p(this.le));
                }
                else {
                    ji.util.i.a(39, false);
                }
                if (this.lh != null) {
                    this.d.aj(p(this.lh));
                }
                if (this.lk != null) {
                    ji.document.ad.ak(p(this.lk));
                }
                if (this.lj != null) {
                    ji.util.i.a(32, p(this.lj));
                }
                if (this.li != null) {
                    this.d.v(this.li);
                }
                if (this.jz != null) {
                    this.jz = ji.util.d.bc(this.jz);
                    if (this.jz.toLowerCase().equals("short")) {
                        ji.util.d.m(2);
                    }
                    else {
                        ji.util.d.m(1);
                    }
                }
                if (this.l8 != null) {
                    final boolean p4 = p(this.l8);
                    this.d.a9(p4);
                    if (!p4) {
                        ji.util.i.a(15, false);
                    }
                }
                if (this.mc != null) {
                    ji.util.d.a(ji.util.d.b(codeBase, this.mc, this.uz));
                }
                if (this.md != null) {
                    this.d.e(q(this.md));
                }
                if (this.me != null) {
                    ji.util.i.a(28, p(this.me));
                }
                else {
                    ji.util.i.a(28, false);
                }
                if (this.mf != null) {
                    ji.util.i.a(74, p(this.mf));
                }
                else {
                    ji.util.i.a(74, false);
                }
                if (this.m6 != null) {
                    ji.util.d.a(q(this.m6));
                }
                if (this.m4 != null) {
                    this.d.bb(q(this.m4));
                }
                if (this.m5 != null) {
                    this.d.bc(q(this.m5));
                }
                if (this.m3 != null) {
                    this.d.e1(p(this.m3));
                }
                if (this.m0 != null) {
                    int q = 0;
                    if (this.m2 != null) {
                        q = q(this.m2);
                    }
                    this.d.a(p(this.m0), q, false);
                }
                if (this.m1 != null) {
                    this.d.e0(p(this.m1));
                }
                if (this.ll != null) {
                    this.d.al(p(this.ll));
                }
                if (this.t0 && this.lm != null) {
                    ji.util.d.d(q(this.lm) * 1024L * 1024L);
                }
                if (this.l7 != null) {
                    ji.util.d.s(this.l7);
                }
                if (this.ln != null) {
                    ji.util.d.bx(p(this.ln));
                }
                if (this.lo != null) {
                    this.d.d6(p(this.lo));
                }
                if (this.l4 != null) {
                    this.d.d2(p(this.l4));
                }
                if (this.l6 != null) {
                    ji.util.d.o(q(this.l6));
                }
                if (this.l2 != null) {
                    ji.document.ad.d3(p(this.l2));
                }
                if (this.l3 != null) {
                    ji.document.ad.d0(p(this.l3));
                }
                if (this.l5 != null) {
                    ji.document.ad.d1(p(this.l5));
                }
                if (this.lp != null) {
                    this.d.d4(p(this.lp));
                }
                else {
                    this.d.d4(false);
                }
                if (this.lq != null) {
                    this.d.d5(p(this.lq));
                }
                if (this.lx != null) {
                    ji.util.i.a(8, p(this.lx));
                }
                else {
                    ji.util.i.a(8, false);
                }
                if (this.ly != null) {
                    ji.util.i.a(9, p(this.ly));
                }
                else {
                    ji.util.i.a(9, false);
                }
                if (this.lz != null) {
                    ji.util.i.a(10, p(this.lz));
                }
                else {
                    ji.util.i.a(10, false);
                }
                if (this.l0 != null) {
                    this.d.am(q(this.l0));
                }
                if (this.l1 != null) {
                    ji.util.d.n(q(this.l1));
                }
                if (this.lw != null) {
                    this.d.d(p(this.lw));
                }
                if (this.lu != null) {
                    ji.util.d.b(q(this.lu));
                }
                if (this.lv != null) {
                    ji.util.i.a(108, p(this.lv));
                }
                if (this.lt != null) {
                    ji.util.i.a(181, true);
                    ji.util.i.a(183, p(this.lt));
                }
                if (this.ls != null) {
                    ji.util.i.a(180, true);
                    ji.util.i.a(182, p(this.ls));
                }
                if (this.nb != null) {
                    ji.document.ad.dt(p(this.nb));
                }
                if (this.kf != null) {
                    ji.util.d.ae(this.kf);
                }
                if (this.j4 != null) {
                    this.d.u(this.j4);
                }
                if (this.j5 != null) {
                    ji.document.ad.ac(p(this.j5));
                }
                if (this.j6 != null) {
                    this.d.eu(true);
                    this.d.ev(p(this.j6));
                }
                else {
                    this.d.eu(false);
                }
                if (this.j7 != null) {
                    ji.util.i.a(90, p(this.j7));
                }
                if (this.oo != null) {
                    ji.document.ad.j(q(this.oo));
                }
                if (this.n4 != null) {
                    ji.font.j.a(this.n4);
                }
                if (this.n5 != null) {
                    ji.font.j.a(q(this.n5));
                }
                if (this.g9 != null) {
                    ji.document.ad.i(q(this.g9));
                }
                if (this.hb != null) {
                    ji.font.j.b(q(this.hb));
                }
                if (this.g8 != null) {
                    ji.util.d.h(q(this.g8));
                }
                if (this.n7 != null) {
                    ji.util.i.a(153, p(this.n7));
                    ji.util.i.a(162, p(this.n7));
                }
                if (this.n8 != null) {
                    ji.util.i.a(5, q(this.n8));
                }
                if (this.oe != null) {
                    ji.util.i.a(160, q(this.oe));
                }
                if (this.ob != null) {
                    ji.util.i.a(156, p(this.ob));
                }
                if (this.oc != null) {
                    ji.util.i.a(157, p(this.oc));
                }
                if (this.od != null) {
                    ji.util.i.a(158, p(this.od));
                }
                this.i("tiffSaveColor");
                this.i("waitForSignalRender");
                if (this.d != null) {
                    this.d.a(this.n9);
                }
                if (this.d.bi(6) && this.d.bi(ji.document.bd.a)) {
                    int q2 = 0;
                    if (this.m2 != null) {
                        q2 = q(this.m2);
                    }
                    this.d.a(true, q2, false);
                }
                if (this.km == null) {
                    this.km = "window.innerWidth";
                }
                if (this.kp == null) {
                    this.kp = "15";
                }
                if (this.kn == null) {
                    this.kn = "window.innerHeight";
                }
                if (this.kq == null) {
                    this.kq = "15";
                }
                this.qk = ji.util.d.c(this.kp, 15);
                this.ql = ji.util.d.c(this.kq, 15);
                if (this.kl != null && this.ko != null) {
                    this.qi = ji.util.d.c(this.kl, 0);
                    this.qj = ji.util.d.c(this.ko, 0);
                }
                if (this.n6 != null) {
                    this.d.at(q(this.n6));
                }
                else {
                    this.d.at(0);
                }
                if (this.of != null) {
                    this.d.a1(this.of);
                }
                if (this.pf != null) {
                    for (int n2 = 0; n2 < this.pf.length; ++n2) {
                        this.d.k(this.pf[n2]);
                    }
                }
                if (this.pg != null) {
                    for (int n3 = 0; n3 < this.pg.length; ++n3) {
                        this.d.q(this.pg[n3]);
                    }
                }
                if (this.ps != null) {
                    ji.util.d.a(this.ps, this.uz);
                }
                if (this.pj != null) {
                    this.d.et(p(this.pj));
                }
                if (this.pk != null) {
                    this.d.l(this.pk);
                }
                if (this.pl != null) {
                    this.d.m(this.pl);
                }
                if (this.pq != null) {
                    this.d.n(this.pq);
                }
                if (this.pm != -1) {
                    this.d.c(this.pm);
                }
                else {
                    this.d.c(-1);
                }
                if (this.pn != null) {
                    this.d.u(p(this.pn));
                }
                if (this.po != null) {
                    this.d.o(this.po);
                }
                if (this.pp != null) {
                    this.d.p(this.pp);
                }
                if (this.pu != null) {
                    ji.util.i.a(56, p(this.pu));
                }
                if (this.pt != null) {
                    ji.util.i.a(93, p(this.pt));
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit6a");
                }
                this.a(258, "hideAnnotationButtonsInHtmlOnly");
                if (ji.util.i.c(258)) {
                    this.aa();
                }
                this.a(264, "useOldPDFFileDetection");
                this.a(266, "MultiAppletDisplayFix");
                this.a(272, "uniqueKeyGeneration");
                this.a(276, "printDialogWin2kOnTop");
                this.a(275, "updateThumbsAfterDocOpened");
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit6b");
                }
                if (this.g6 != null) {
                    this.d.an(q(this.g6));
                }
                if (this.g7 != null) {
                    this.d.ao(q(this.g7));
                }
                if (this.g2 != null) {
                    this.d.ea(p(this.g2));
                }
                if (this.g3 != null) {
                    this.d.ap(q(this.g3));
                }
                if (this.g2 != null) {
                    ji.util.i.a(31, p(this.g2));
                }
                if (this.g4 != null) {
                    ji.util.i.a(51, p(this.g4));
                }
                if (this.g5 != null) {
                    ji.util.d.d(q(this.g5));
                }
                if (this.t0) {
                    if (this.ia != null) {
                        final boolean[] s = this.s(this.ia);
                        if (s != null) {
                            final boolean[] array2 = new boolean[11];
                            for (int n4 = 0; n4 < s.length && n4 < array2.length; ++n4) {
                                array2[n4] = s[n4];
                            }
                            this.d.a(array2[0], array2[1], array2[2], array2[3], array2[4], array2[5], array2[6], array2[7], array2[8], array2[9], array2[10]);
                        }
                    }
                    if (this.ic != null) {
                        final boolean[] s2 = this.s(this.ic);
                        if (s2 != null) {
                            final boolean[] array3 = new boolean[11];
                            for (int n5 = 0; n5 < s2.length && n5 < array3.length; ++n5) {
                                array3[n5] = s2[n5];
                            }
                            this.d.c(array3[0], array3[1], array3[2], array3[3], array3[4], array3[5], array3[6], array3[7], array3[8], array3[9], array3[10]);
                        }
                    }
                    if (this.ie != null) {
                        final boolean[] s3 = this.s(this.ie);
                        if (s3 != null) {
                            final boolean[] array4 = new boolean[11];
                            for (int n6 = 0; n6 < s3.length && n6 < array4.length; ++n6) {
                                array4[n6] = s3[n6];
                            }
                            this.d.b(array4[0], array4[1], array4[2], array4[3], array4[4], array4[5], array4[6], array4[7], array4[8], array4[9], array4[10]);
                        }
                    }
                }
                if (this.i8 != null) {
                    final String[] t = this.t(this.i8);
                    if (t != null) {
                        final boolean[] array5 = new boolean[7];
                        for (int n7 = 0; n7 < t.length; ++n7) {
                            if (t[n7].equals("page")) {
                                array5[0] = true;
                            }
                            else if (t[n7].equals("thumbs")) {
                                array5[1] = true;
                            }
                            else if (t[n7].equals("twopage")) {
                                array5[2] = true;
                            }
                            else if (t[n7].equals("left")) {
                                array5[3] = true;
                            }
                            else if (t[n7].equals("bottom")) {
                                array5[4] = true;
                            }
                            else if (t[n7].equals("right")) {
                                array5[5] = true;
                            }
                            else if (t[n7].equals("top")) {
                                array5[6] = true;
                            }
                        }
                        this.d.a(array5[0], array5[1], array5[2], array5[3], array5[4], array5[5], array5[6]);
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit7");
                }
                if (this.fv != null) {
                    this.d.ai(this.fv);
                }
                if (this.fx != null) {
                    this.d.ak(this.fx);
                }
                if (this.fz != null) {
                    this.fz = ji.util.d.b(codeBase, this.fz, this.uz).toString();
                }
                if (this.fy != null) {
                    this.fy = ji.util.d.b(codeBase, this.fz, this.uz).toString();
                }
                if (this.f0 != null) {
                    ji.util.d.ls = p(this.f0);
                }
                if (this.fz != null) {
                    this.d.aj(this.fz);
                }
                else if (this.fy != null) {
                    this.d.aj(this.fy);
                }
                if (this.fy != null) {
                    this.d.al(this.fy);
                }
                else if (this.fz != null) {
                    this.d.al(this.fz);
                }
                if (this.df != null) {
                    ji.document.ad.ao(this.df);
                }
                else {
                    ji.document.ad.ao((String)null);
                }
                if (this.f1 != null) {
                    this.d.am(this.f1);
                }
                if (this.fw != null) {
                    this.d.ay(this.fw);
                }
                this.a(codeBase, this.d);
                if (this.os != null) {
                    this.d.ah(this.os);
                }
                if (this.c5 != null) {
                    final int[] array6 = new int[this.c5.length];
                    for (int n8 = 0; n8 < this.c5.length; ++n8) {
                        array6[n8] = ji.util.d.c(this.c5[n8], 0);
                    }
                    this.d.c(array6);
                }
                if (this.kd != null) {
                    this.d.a8(ji.util.d.c(this.kd, 60000));
                }
                if (this.ke != null) {
                    this.d.a9(ji.util.d.c(this.ke, 3));
                }
                if (this.jp != null) {
                    ji.util.d.h(this.jp);
                }
                if (this.jo != null) {
                    this.d.a4(this.jo);
                }
                if (this.jq != null) {
                    this.d.a5(this.jq);
                }
                if (this.jr != null) {
                    this.d.e6(p(this.jr));
                }
                if (this.js != null) {
                    ji.util.i.a(64, p(this.js));
                }
                if (this.jt != null) {
                    ji.util.i.a(194, p(this.jt));
                }
                if (this.gk != null) {
                    ji.util.e.ah(this.gk);
                }
                if (this.go != null) {
                    this.d.a6(q(this.go));
                }
                if (this.gl != null) {
                    final int q3 = q(this.gl);
                    if (q3 > 0) {
                        ji.util.i.b(7, q3);
                    }
                }
                if (this.gn != null) {
                    final int q4 = q(this.gn);
                    if (q4 > 0) {
                        ji.util.i.b(8, q4);
                    }
                }
                if (this.gp != null) {
                    final int q5 = q(this.gp);
                    if (q5 > 0) {
                        ji.util.i.b(0, q5);
                    }
                }
                if (this.jy != null) {
                    this.d.ad(p(this.jy));
                }
                if (this.s != null) {
                    this.d.e(ji.util.d.a(this.s, 25.0));
                }
                if (this.kx != null) {
                    ji.util.i.a(67, p(this.kx));
                }
                else {
                    ji.util.i.a(67);
                }
                if (this.kz != null) {
                    ji.util.i.b(4, q(this.kz));
                }
                if (this.ky != null) {
                    ji.util.i.a(68, p(this.ky));
                }
                else {
                    ji.util.i.a(68, !ji.util.d.bf());
                }
                if (this.cp != null) {
                    if (this.cp.toLowerCase().equals("ftow")) {
                        this.d.a7(1);
                    }
                    if (this.cp.toLowerCase().equals("fittowidth")) {
                        this.d.a7(1);
                    }
                    if (this.cp.toLowerCase().equals("fitwidth")) {
                        this.d.a7(1);
                    }
                    if (this.cp.toLowerCase().equals("ftoh")) {
                        this.d.a7(0);
                    }
                    if (this.cp.toLowerCase().equals("fittoheight")) {
                        this.d.a7(0);
                    }
                    if (this.cp.toLowerCase().equals("fitheight")) {
                        this.d.a7(0);
                    }
                    if (this.cp.toLowerCase().equals("best")) {
                        this.d.a7(2);
                    }
                    if (this.cp.toLowerCase().equals("bestfit")) {
                        this.d.a7(2);
                    }
                }
                if (this.kv != null) {
                    if (this.kv.toLowerCase().equals("fullpage")) {
                        this.d.ac(0);
                    }
                    if (this.kv.toLowerCase().equals("thumbsonly")) {
                        this.d.ac(5);
                    }
                    if (this.kv.toLowerCase().equals("twopage")) {
                        this.d.ac(6);
                    }
                    if (this.kv.toLowerCase().equals("thumbsleft")) {
                        this.d.ac(1);
                    }
                    if (this.kv.toLowerCase().equals("thumbsright")) {
                        this.d.ac(3);
                    }
                    if (this.kv.toLowerCase().equals("thumbstop")) {
                        this.d.ac(2);
                    }
                    if (this.kv.toLowerCase().equals("thumbsbottom")) {
                        this.d.ac(4);
                    }
                }
                if (this.kw != null) {
                    if (this.kw.toLowerCase().equals("fullpage")) {
                        this.d.ad(0);
                    }
                    if (this.kw.toLowerCase().equals("thumbsonly")) {
                        this.d.ad(5);
                    }
                    if (this.kw.toLowerCase().equals("twopage")) {
                        this.d.ad(6);
                    }
                    if (this.kw.toLowerCase().equals("thumbsleft")) {
                        this.d.ad(1);
                    }
                    if (this.kw.toLowerCase().equals("thumbsright")) {
                        this.d.ad(3);
                    }
                    if (this.kw.toLowerCase().equals("thumbstop")) {
                        this.d.ad(2);
                    }
                    if (this.kw.toLowerCase().equals("thumbsbottom")) {
                        this.d.ad(4);
                    }
                }
                if (this.t0 && this.j2 != null) {
                    p = p(this.j2);
                    this.d.w(p);
                }
                if (this.t0 && this.c2 != null) {
                    this.d.x(p(this.c2));
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit9");
                }
                if (this.lb != null) {
                    ji.util.i.a(146, p(this.lb));
                }
                if (this.la != null) {
                    this.d.setRightMouseEmulator(p(this.la));
                }
                if (this.k4 != null) {
                    this.d.cq(p(this.k4));
                }
                if (this.k7 != null) {
                    this.d.cn(p(this.k7));
                }
                if (this.k8 != null) {
                    this.d.s(this.k8);
                }
                if (this.k9 != null) {
                    this.d.co(p(this.k9));
                }
                if (this.oh != null) {
                    this.d.cp(p(this.oh));
                }
                if (this.k5 != null) {
                    this.o5 = p(this.k5);
                    this.d.e2(this.o5);
                }
                if (this.k6 != null && this.o5) {
                    this.d.a((ad.t8)null);
                    this.d.do(p(this.k6));
                }
                if (this.us != null) {
                    ji.util.d.b(ji.util.d.b1(this.us));
                }
                if (this.k1 != null) {
                    this.d.cm(p(this.k1));
                }
                if (this.ct != null) {
                    ji.util.i.a(209, p(this.ct));
                }
                if (this.cr != null && this.cs != null) {
                    this.d.c(p(this.cr), q(this.cs));
                }
                else if (this.cr != null) {
                    this.d.c(p(this.cr), 0);
                }
                else if (this.cs != null) {
                    this.d.c(true, q(this.cs));
                }
                if (this.cx != null) {
                    ji.util.i.a(187, p(this.cx));
                }
                if (this.cy != null) {
                    ji.util.i.a(218, p(this.cy));
                }
                if (this.cv != null) {
                    this.d.ag(q(this.cv));
                }
                if (this.c0 != null) {
                    ji.document.ad.ec(p(this.c0));
                }
                if (this.c1 != null) {
                    this.d.eb(p(this.c1));
                }
                if (this.b1 != null) {
                    this.d.bz(p(this.b1));
                }
                if (this.bj != null) {
                    this.d.by(p(this.bj));
                }
                if (this.b2 != null) {
                    ji.util.i.a(43, p(this.b2));
                }
                if (this.b3 != null) {
                    ji.util.i.a(65, p(this.b3));
                }
                if (this.b5 != null) {
                    ji.font.j.d(this.b5);
                }
                if (this.b4 != null) {
                    ji.font.j.e(q(this.b4));
                }
                if (this.b6 != null) {
                    ji.font.j.a(p(this.b6));
                }
                if (this.cb != null) {
                    ji.font.j.e(this.cb);
                }
                if (this.cc != null) {
                    ji.font.j.f(q(this.cc));
                }
                if (this.cd != null) {
                    ji.font.j.d(q(this.cd));
                }
                if (this.ca != null) {
                    ji.util.i.a(101, p(this.ca));
                }
                if (this.b7 != null) {
                    final boolean p5 = p(this.b7);
                    ji.util.i.a(234, p5);
                    ji.util.d.i7 = p5;
                }
                if (this.b8 != null) {
                    ji.util.i.a(235, p(this.b8));
                }
                if (this.b9 != null) {
                    ji.util.i.a(236, p(this.b9));
                }
                if (this.ce != null) {
                    ji.util.i.a(71, p(this.ce));
                }
                if (this.cf != null) {
                    this.d.fi(p(this.cf));
                }
                else {
                    this.d.fi(true);
                }
                if (this.cg != null) {
                    this.d.fk(p(this.cg));
                }
                else {
                    this.d.fk(true);
                }
                ji.util.d.b(this.ch, this.ci);
                if (this.cj != null) {
                    ji.util.i.a(75, p(this.cj));
                }
                if (this.ck != null) {
                    ji.util.i.a(76, p(this.ck));
                }
                if (this.cl != null) {
                    ji.util.i.a(77, p(this.cl));
                }
                if (this.cm != null) {
                    ji.util.d.d(72.0 * r(this.cm));
                }
                if (this.c3 != null) {
                    this.d.e7(p(this.c3));
                }
                if (this.j3 != null) {
                    if (!ji.util.d.by(this.or)) {
                        ji.util.i.a(86, true);
                    }
                    else {
                        ji.util.i.a(86, p(this.j3));
                    }
                }
                if (this.dk != null) {
                    this.d.fb(p(this.dk));
                }
                if (this.dl != null) {
                    this.d.fa(p(this.dl));
                }
                if (this.pi != null) {
                    this.d.bm(p(this.pi));
                }
                if (this.ok != null) {
                    ji.util.i.a(66, p(this.ok));
                }
                if (this.ph != null) {
                    ji.document.ad.g(p(this.ph));
                }
                if (this.pv != null) {
                    ji.util.d.c9 = p(this.pv);
                }
                if (this.p2 != null) {
                    ji.util.d.t(p(this.p2));
                }
                if (this.p3 != null) {
                    this.d.c(p(this.p3));
                }
                if (this.p4 != null) {
                    ji.util.i.a(41, p(this.p4));
                }
                if (this.ut != null) {
                    ji.util.i.a(163, p(this.ut));
                }
                if (this.uu != null) {
                    ji.util.i.a(113, p(this.uu));
                }
                if (this.p5 != null) {
                    ji.util.i.a(139, p(this.p5));
                }
                if (this.p6 != null) {
                    ji.util.i.a(259, p(this.p6));
                }
                if (this.uv != null) {
                    ji.util.i.a(130, p(this.uv));
                }
                if (this.qb != null) {
                    ji.util.i.a(121, p(this.qb));
                }
                if (this.p7 != null) {
                    ji.util.i.a(42, p(this.p7));
                }
                if (this.qc != null) {
                    ji.util.i.a(151, p(this.qc));
                }
                if (this.uw != null) {
                    ji.util.i.a(114, p(this.uw));
                }
                if (this.ux != null) {
                    ji.util.i.a(122, p(this.ux));
                }
                if (this.bf != null) {
                    ji.util.i.a(118, p(this.bf));
                }
                if (this.bg != null) {
                    ji.util.i.a(119, p(this.bg));
                }
                if (this.bh != null) {
                    ji.util.i.a(116, p(this.bh));
                }
                if (this.bi != null) {
                    ji.util.i.a(117, p(this.bi));
                }
                if (this.rj != null) {
                    ji.util.i.b(2, q(this.rj));
                }
                if (this.rk != null) {
                    ji.util.i.a(125, p(this.rk));
                }
                if (this.uj != null) {
                    ji.util.i.b(3, q(this.uj));
                }
                if (this.uy != null) {
                    ji.util.i.a(143, p(this.uy));
                }
                if (this.hu != null) {
                    ji.util.i.a(170, p(this.hu));
                }
                if (this.hv != null) {
                    ji.util.i.a(171, !p(this.hv));
                }
                if (this.ha != null) {
                    ji.font.j.a(r(this.ha));
                }
                if (this.nx != null) {
                    ji.font.j.c(this.nx);
                }
                if (this.ny != null) {
                    ji.font.j.b(q(this.ny));
                }
                if (this.nz != null) {
                    ji.font.j.c(q(this.nz));
                }
                if (this.nw != null) {
                    ji.font.j.b(this.nw);
                }
                if (this.og != null) {
                    this.d.q(p(this.og));
                }
                if (this.e2 != null) {
                    ji.util.i.a(175, p(this.e2));
                }
                if (this.cn != null) {
                    ji.util.i.a(267, p(this.cn));
                }
                if (this.co != null) {
                    ji.util.i.a(268, p(this.co));
                }
                if (this.t0) {
                    boolean p6 = false;
                    if (this.be != null) {
                        p6 = p(this.be);
                    }
                    ji.util.i.a(115, p6);
                    if (this.bc != null) {
                        this.d.dg(true);
                        if (p6) {
                            if (this.ah != null) {
                                this.d.a((Object[])this.ah, true);
                            }
                            else {
                                this.d.a((Object[])this.bc, false);
                            }
                        }
                    }
                    if (this.he != null) {
                        this.d.dh(p(this.he));
                    }
                    if (this.hf != null) {
                        this.d.dj(p(this.hf));
                    }
                    if (this.hg != null) {
                        this.d.dk(p(this.hg));
                    }
                    if (this.hh != null) {
                        this.d.dl(p(this.hh));
                    }
                    if (this.e3 != null) {
                        this.d.ce(p(this.e3));
                    }
                }
                if (this.ey != null) {
                    this.d.ey(p(this.ey));
                }
                if (this.eu != null) {
                    this.d.bg(p(this.eu));
                }
                if (this.ex != null) {
                    ji.util.i.a(104, p(this.ex));
                }
                if (this.ev != null) {
                    ji.util.d.lv = p(this.ev);
                }
                if (this.ew != null) {
                    ji.util.d.lw = p(this.ew);
                }
                if (this.dh != null) {
                    ji.util.i.a(281, p(this.dh));
                }
                if (this.di != null) {
                    ji.util.i.a(282, p(this.di));
                }
                if (this.ez != null) {
                    final boolean p7 = p(this.ez);
                    if (!p7) {
                        this.d.fb(false);
                    }
                    this.d.ee(p7);
                }
                if (this.e0 != null) {
                    final boolean p8 = p(this.e0);
                    if (!p8) {
                        this.d.fa(false);
                    }
                    this.d.ef(p8);
                }
                if (this.dj != null) {
                    this.d.fc(p(this.dj));
                }
                if (this.e1 != null) {
                    this.d.b1(p(this.e1));
                }
                if (this.hy != null) {
                    this.d.an(p(this.hy));
                }
                this.d.ao(this.d.bi(30));
                if (this.hw != null) {
                    try {
                        if (ji.util.d.by(this.hw)) {
                            this.d.a((URL)null, this.hx);
                        }
                        else {
                            this.d.a((URL)ji.util.d.b(codeBase, this.hw, this.uz), this.hx);
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                if (this.t0) {
                    if (this.ht != null) {
                        this.d.aq(p(this.ht));
                    }
                    if (this.hs != null) {
                        this.d.ap(p(this.hs));
                    }
                    if (this.hz != null) {
                        this.d.ar(p(this.hz));
                    }
                    if (this.h0 != null) {
                        this.d.at(p(this.h0));
                    }
                    else {
                        this.d.at(true);
                    }
                    if (this.h1 != null) {
                        this.d.au(p(this.h1));
                    }
                    else {
                        this.d.au(true);
                    }
                    if (ji.util.d.di() == 0 && this.e5 != null) {
                        this.d.fh(p(this.e5));
                    }
                    if (this.fs != null) {
                        this.d.dm(p(this.fs));
                    }
                    if (this.ft != null) {
                        this.d.i(ji.util.d.b1(this.ft));
                    }
                    if (this.fu != null) {
                        this.d.h(ji.util.d.b1(this.fu));
                    }
                }
                if (this.in != null) {
                    this.d.c2(p(this.in));
                }
                if (this.io != null) {
                    this.d.c3(p(this.io));
                }
                if (this.ip != null) {
                    this.d.c4(p(this.ip));
                }
                if (this.iq != null) {
                    this.d.c6(p(this.iq));
                }
                if (this.ir != null) {
                    this.d.c7(p(this.ir));
                }
                if (this.i0 != null) {
                    this.d.ep(true);
                    this.d.eq(p(this.i0));
                    this.d.eo(p(this.i0));
                }
                else {
                    this.d.ep(false);
                }
                if (this.is != null) {
                    this.d.c5(p(this.is));
                }
                if (this.gs != null && this.d.fv()) {
                    this.d.c8(p(this.gs));
                }
                if (this.g1 != null) {
                    if (p(this.g1)) {
                        ji.util.i.a(23);
                    }
                    else {
                        ji.util.i.b(23);
                    }
                }
                if (this.gz != null) {
                    if (p(this.gz)) {
                        ji.util.i.b(19);
                    }
                    else {
                        ji.util.i.a(19);
                    }
                }
                if (this.gt != null) {
                    ji.util.i.a(244, p(this.gt));
                }
                if (this.gu != null) {
                    ji.util.i.a(250, p(this.gu));
                }
                if (this.gv != null) {
                    this.d.c9(p(this.gv));
                }
                else if (this.gw != null) {
                    this.d.c9(p(this.gw));
                }
                if (this.hc != null) {
                    this.d.da(p(this.hc));
                }
                if (this.hd != null) {
                    this.d.as(this.hd);
                }
                if (this.f3 != null) {
                    this.d.ed(p(this.f3));
                }
                if (this.f4 != null) {
                    this.d.di(p(this.f4));
                }
                if (this.f2 != null) {
                    this.d.i(p(this.f2));
                }
                if (this.nk != null) {
                    ji.util.d.b(r(this.nk));
                }
                if (this.ni != null) {
                    this.x(ji.util.d.bc(this.ni.toLowerCase()));
                }
                if (this.nj != null) {
                    this.nj = ji.util.d.bc(this.nj.toLowerCase());
                    if (this.nj.startsWith("dms")) {
                        ji.util.d.f(1);
                    }
                    else if (this.nj.startsWith("rad")) {
                        ji.util.d.f(2);
                    }
                    else {
                        ji.util.d.f(0);
                    }
                }
                if (this.nl != null) {
                    ji.util.d.e(q(this.nl));
                }
                if (this.nm != null) {
                    this.nm = ji.util.d.bc(this.nm.toLowerCase());
                    if (this.nm.startsWith("inches")) {
                        ji.document.ad.ba(true);
                    }
                    else {
                        ji.document.ad.ba(false);
                    }
                }
                else {
                    ji.document.ad.ba(false);
                }
                if (this.op != null) {
                    final int index2 = this.op.indexOf(",");
                    if (index2 >= 0) {
                        final String substring = this.op.substring(0, index2);
                        final String substring2 = this.op.substring(index2 + 1);
                        if (ji.document.ad.bz()) {
                            ji.document.ad.a(r(substring), r(substring2));
                        }
                        else {
                            final Dimension m = ji.document.ad.l();
                            final Dimension dimension = new Dimension(q(substring), q(substring2));
                            if (dimension.width <= 0) {
                                dimension.width = m.width;
                            }
                            if (dimension.height <= 0) {
                                dimension.height = m.height;
                            }
                            ji.document.ad.a(dimension);
                        }
                    }
                }
                if (this.nn != null) {
                    this.nn = ji.util.d.bc(this.nn.toLowerCase());
                    this.nn = "0000".concat(String.valueOf(String.valueOf(this.nn)));
                    this.nn = this.nn.substring(this.nn.length() - 4);
                    final int index3 = this.nn.indexOf("a");
                    final int index4 = this.nn.indexOf("r");
                    final int index5 = this.nn.indexOf("g");
                    final int index6 = this.nn.indexOf("b");
                    int n9;
                    if (index3 >= 0) {
                        n9 = 255 << 8 * (3 - index3);
                    }
                    else {
                        n9 = 0;
                    }
                    int n10;
                    if (index4 >= 0) {
                        n10 = 255 << 8 * (3 - index4);
                    }
                    else {
                        n10 = 0;
                    }
                    int n11;
                    if (index5 >= 0) {
                        n11 = 255 << 8 * (3 - index5);
                    }
                    else {
                        n11 = 0;
                    }
                    int n12;
                    if (index6 >= 0) {
                        n12 = 255 << 8 * (3 - index6);
                    }
                    else {
                        n12 = 0;
                    }
                    ji.document.ad.a(n9, n10, n11, n12);
                    ji.document.ad.bb(true);
                }
                else {
                    ji.document.ad.bb(false);
                }
                if (this.o6 != null) {
                    ji.document.ad.a(ji.util.d.b1(this.o6));
                }
                if (this.o7 != null) {
                    ji.document.ad.b(ji.util.d.b1(this.o7));
                }
                if (this.o8 != null) {
                    ji.document.ad.c(ji.util.d.b1(this.o8));
                }
                if (this.o9 != null) {
                    ji.document.ad.d(ji.util.d.b1(this.o9));
                }
                if (this.pa != null) {
                    ji.document.ad.e(ji.util.d.b1(this.pa));
                }
                if (this.pc != null) {
                    ji.document.ad.f(ji.util.d.b1(this.pc));
                }
                if (this.nt != null) {
                    ji.util.d.d(p(this.nt));
                }
                if (this.s6 != null) {
                    ji.util.i.a(61, p(this.s6));
                }
                if (this.sp != null) {
                    ji.util.i.a(59, p(this.sp));
                }
                if (this.s7 != null) {
                    ji.util.i.a(63, p(this.s7));
                }
                if (this.sq != null) {
                    ji.util.d.ci(this.sq);
                }
                if (this.ta != null) {
                    ji.util.d.ah(q(this.ta));
                }
                if (this.s5 != null) {
                    ji.util.i.a(62, p(this.s5));
                }
                if (ji.util.i.c(59)) {
                    this.b(this.d);
                    this.a(this.d);
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit10");
                }
                if (this.t0) {
                    if (this.f5 != null) {
                        this.d.as(p(this.f5));
                    }
                    if (this.ga != null) {
                        this.d.fl(p(this.ga));
                    }
                    if (this.f6 != null) {
                        final boolean[] s4 = this.s(this.f6);
                        if (s4 != null) {
                            final boolean[] array7 = { true, true, true };
                            for (int n13 = 0; n13 < s4.length && n13 < 3; ++n13) {
                                array7[n13] = s4[n13];
                            }
                            this.d.a(1, array7[0], array7[1], array7[2]);
                        }
                    }
                    if (this.f7 != null) {
                        final boolean[] s5 = this.s(this.f7);
                        if (s5 != null) {
                            final boolean[] array8 = { true, true, true };
                            for (int n14 = 0; n14 < s5.length && n14 < 3; ++n14) {
                                array8[n14] = s5[n14];
                            }
                            this.d.a(4, array8[0], array8[1], array8[2]);
                        }
                    }
                    if (this.f8 != null) {
                        final boolean[] s6 = this.s(this.f8);
                        if (s6 != null) {
                            final boolean[] array9 = { true, true, true };
                            for (int n15 = 0; n15 < s6.length && n15 < 3; ++n15) {
                                array9[n15] = s6[n15];
                            }
                            this.d.a(8, array9[0], array9[1], array9[2]);
                        }
                    }
                    if (this.f9 != null) {
                        final boolean[] s7 = this.s(this.f9);
                        if (s7 != null) {
                            final boolean[] array10 = { true, true, true };
                            for (int n16 = 0; n16 < s7.length && n16 < 3; ++n16) {
                                array10[n16] = s7[n16];
                            }
                            this.d.a(24, array10[0], array10[1], array10[2]);
                        }
                    }
                }
                if (this.no != null) {
                    this.d.a5(q(this.no));
                }
                else {
                    this.d.a5(1);
                }
                if (this.np != null) {
                    this.d.j(r(this.np));
                }
                else {
                    this.d.j(ji.util.e.o());
                }
                if (this.nq != null) {
                    this.d.e5(p(this.nq));
                }
                else {
                    this.d.e5(false);
                }
                if (this.gb != null) {
                    this.d.z(q(this.gb) * ji.util.d.d0 / 100);
                }
                if (this.gc != null) {
                    this.d.aa(q(this.gc) * ji.util.d.d1 / 100);
                }
                if (this.ge != null) {
                    this.d.ab(q(this.ge) * ji.util.d.d2 / 100);
                }
                if (this.gq != null) {
                    this.d.fe(p(this.gq));
                }
                if (this.gr != null) {
                    this.d.ff(p(this.gr));
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit11");
                }
                if (this.gb != null || this.gb != null || this.gb != null) {
                    this.d.fe(true);
                    this.d.ff(true);
                }
                this.qh = false;
                if (this.qd != null) {
                    this.d.aw(this.qd);
                }
                if (this.qe != null) {
                    this.qh = p(this.qe);
                    this.d.ds(this.qh);
                }
                if (this.qt != null) {
                    ji.util.d.lz = Math.max(q(this.qt), 0);
                }
                if (this.qu != null) {
                    ji.util.d.l0 = Math.max(q(this.qu), 0);
                }
                if (this.qf != null) {
                    ji.util.d.i(p(this.qf));
                }
                else {
                    ji.util.d.i(false);
                }
                if (this.hl != null) {
                    this.d.dz(p(this.hl));
                }
                if (this.hk != null) {
                    ji.util.i.a(96, p(this.hk));
                }
                if (this.t0) {
                    if (this.hi != null) {
                        ji.util.i.a(248, p(this.hi));
                    }
                    if (this.hj != null) {
                        if (p(this.hj)) {
                            ji.util.i.b(11, 1);
                        }
                        else {
                            ji.util.i.b(11, -1);
                        }
                    }
                    if (this.hm != null) {
                        this.d.b4(p(this.hm));
                    }
                    if (this.hn != null) {
                        this.d.b5(p(this.hn));
                    }
                    if (this.ho != null) {
                        this.d.b6(p(this.ho));
                    }
                    if (this.hp != null) {
                        this.d.b7(p(this.hp));
                    }
                    if (this.hr != null) {
                        this.d.b8(p(this.hr));
                    }
                    if (this.h4 != null) {
                        this.d.av(p(this.h4));
                    }
                    if (this.h5 != null) {
                        this.d.aw(p(this.h5));
                    }
                    if (this.h6 != null) {
                        this.d.ax(p(this.h6));
                    }
                    if (this.h7 != null) {
                        this.d.ay(p(this.h7));
                    }
                    if (ji.util.d.di() == 0) {
                        if (this.h8 != null) {
                            this.d.az(p(this.h8));
                        }
                    }
                    else {
                        this.d.az(false);
                    }
                    if (this.h9 != null) {
                        this.d.cd(p(this.h9));
                    }
                    if (this.ib != null) {
                        this.d.b2(p(this.ib));
                    }
                    if (this.id != null) {
                        this.d.a0(p(this.id));
                    }
                    if (this.if != null) {
                        this.d.cf(p(this.if));
                    }
                    if (this.ig != null) {
                        this.d.b3(p(this.ig));
                    }
                    if (this.ih != null) {
                        this.d.a1(p(this.ih));
                    }
                    if (this.ii != null) {
                        this.d.ck(p(this.ii));
                    }
                    if (this.ij != null) {
                        this.d.cb(p(this.ij));
                    }
                    if (this.ik != null) {
                        this.d.cc(p(this.ik));
                    }
                    if (this.il != null) {
                        this.d.a2(p(this.il));
                    }
                    if (this.im != null) {
                        this.d.a3(p(this.im));
                    }
                    if (this.i1 != null) {
                        this.d.ci(p(this.i1));
                    }
                    if (this.i2 != null) {
                        this.d.b9(p(this.i2));
                    }
                    if (this.i3 != null) {
                        this.d.a4(p(this.i3));
                    }
                    if (this.i5 != null) {
                        this.d.es(p(this.i5));
                    }
                    if (this.i6 != null) {
                        this.d.el(p(this.i6));
                    }
                    if (this.i4 != null) {
                        this.d.fj(p(this.i4));
                    }
                    if (this.jc != null) {
                        this.d.a5(p(this.jc));
                    }
                    if (this.i7 != null) {
                        this.d.cj(p(this.i7));
                    }
                    if (this.i9 != null) {
                        this.d.ca(p(this.i9));
                    }
                    if (this.ja != null) {
                        this.d.eg(p(this.ja));
                    }
                    if (this.jb != null) {
                        this.d.a6(p(this.jb));
                    }
                    if (this.je != null) {
                        this.d.a7(p(this.je));
                    }
                    if (this.jd != null) {
                        this.d.a8(p(this.jd));
                    }
                    if (this.rf != null) {
                        ji.util.i.a(47, p(this.rf));
                    }
                    if (this.oj != null) {
                        this.d.de(p(this.oj));
                    }
                    else {
                        this.d.de(false);
                    }
                    if (this.q8 != null) {
                        ji.util.i.a(33, p(this.q8));
                    }
                    if (this.q9 != null) {
                        ji.util.i.a(49, p(this.q9));
                    }
                    if (this.ra != null) {
                        ji.util.i.a(54, p(this.ra));
                    }
                    if (this.rb != null) {
                        ji.util.i.a(53, p(this.rb));
                    }
                    if (this.rc != null) {
                        ji.util.i.a(55, p(this.rc));
                    }
                    if (this.p0 != null) {
                        ji.document.ad.b(q(this.p0));
                    }
                    if (this.p1 != null) {
                        this.d.b(!p(this.p1));
                    }
                    if (this.rg != null) {
                        ji.util.i.a(58, p(this.rg));
                    }
                    if (this.nh != null) {
                        ji.util.d.k(p(this.nh));
                    }
                    if (this.ng != null) {
                        ji.document.ad.am(p(this.ng));
                    }
                    a(this.d, this.p8, this.p9, this.rl, this.rm, this.rn);
                    if (ji.util.d.bf()) {
                        this.d.m();
                    }
                    if (this.qa != null) {
                        this.d.d(this.qa);
                    }
                    if (this.hq != null) {
                        this.d.cr(p(this.hq));
                    }
                    if (this.fa != null) {
                        this.d.cu(p(this.fa));
                    }
                    if (this.e9 != null) {
                        this.d.cv(p(this.e9));
                    }
                    if (this.fc != null) {
                        this.d.c1(p(this.fc));
                    }
                    if (this.fd != null) {
                        this.d.c0(p(this.fd));
                    }
                    boolean p9 = true;
                    if (this.ur) {
                        p9 = false;
                    }
                    if (this.fe != null) {
                        p9 = p(this.fe);
                    }
                    ji.util.i.a(88, p9);
                    if (this.ol != null && q(this.ol) == 1) {
                        ji.util.i.a(91);
                    }
                    if (this.fg != null) {
                        ji.util.d.c(q(this.fg));
                    }
                    if (this.ff != null) {
                        ji.util.d.mf = q(this.ff);
                    }
                    if (this.jf != null) {
                        this.d.b0(p(this.jf));
                    }
                    if (this.jg != null) {
                        this.d.cg(p(this.jg));
                    }
                    if (this.fl != null) {
                        final Object b7 = ji.util.d.b(codeBase, this.fl, this.uz);
                        if (b7 == null || b7 instanceof String) {
                            ji.util.d.a("ViewONE Startup problem", String.valueOf(String.valueOf(new StringBuffer("The logHandler parameter is an incorrect URL (").append(this.fl).append(")"))), (af)null, this.uz);
                        }
                        else {
                            this.d.b((URL)ji.util.d.b(codeBase, this.fl, this.uz));
                        }
                    }
                    if (this.fk != null) {
                        final Object b8 = ji.util.d.b(codeBase, this.fk, this.uz);
                        if (b8 == null || b8 instanceof String) {
                            ji.util.d.a("ViewONE Startup problem", String.valueOf(String.valueOf(new StringBuffer("The serverFileMonitor parameter is an incorrect URL (").append(this.fk).append(")"))), (af)null, this.uz);
                        }
                        else {
                            this.d.c((URL)b8);
                        }
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12");
                }
                if (this.fm != null) {
                    this.d.az(this.fm);
                }
                if (this.c4 != null) {
                    this.d.a2(q(this.c4));
                }
                if (this.dg != null) {
                    this.d.as(q(this.dg));
                }
                if (this.k2 != null) {
                    this.d.aq(q(this.k2));
                }
                if (this.c6 != null) {
                    ji.document.ad.a(r(this.c6));
                }
                if (this.db != null) {
                    this.d.z(p(this.db));
                }
                if (this.c7 != null) {
                    ji.util.d.a(r(this.c7));
                }
                if (this.c8 != null) {
                    if (p(this.c8)) {
                        ji.font.j.m = true;
                        ji.font.j.n = false;
                        ji.font.j.o = false;
                    }
                    else {
                        ji.font.j.m = false;
                        ji.font.j.n = false;
                        ji.font.j.o = true;
                    }
                }
                if (this.t0) {
                    final Dimension av = this.d.av();
                    if (this.c9 != null) {
                        av.width = q(this.c9);
                    }
                    if (this.da != null) {
                        av.height = q(this.da);
                    }
                    this.d.b(av);
                    if (this.k3 != null) {
                        this.d.a(this.u(this.k3));
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A1");
                }
                if (this.ab != null) {
                    final Object b9 = ji.util.d.b(codeBase, this.ab, this.uz);
                    if (b9 != null) {
                        this.d.a((URL)b9, this.getAppletContext(), p(this.ac));
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A2");
                }
                if (this.pd != null) {
                    if (p(this.pd)) {
                        ji.document.ad.al(2);
                    }
                    else {
                        ji.document.ad.al(1);
                    }
                }
                try {
                    this.d.gd();
                    if (ji.util.d.by(this.a6)) {
                        this.a6 = ":";
                    }
                    if (ji.util.d.by(this.a7)) {
                        this.a7 = ",";
                    }
                    if (ji.util.d.by(this.a8)) {
                        this.a8 = "=";
                    }
                    if (!ji.util.d.by(this.a9)) {
                        ji.util.d.p(this.a9);
                    }
                    if (this.ak != null) {
                        this.d.a(ji.util.d.b(this.af(), ji.util.d.bc(this.ak), this.uz), this.a6, this.a7, this.a8);
                    }
                    else if (this.aj != null) {
                        this.d.a(this.aj, this.a6, this.a7, this.a8);
                    }
                }
                catch (Exception ex14) {}
                if (this.dd != null) {
                    if (p(this.dd)) {
                        ji.util.i.a(17);
                    }
                    else {
                        ji.util.i.b(17);
                    }
                }
                if (this.dc != null && p(this.dc)) {
                    this.d.ct(true);
                }
                if (this.de != null) {
                    ji.document.ad.ap(this.de);
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A3");
                }
                this.ao();
                if (this.r != null) {
                    this.d.ai(q(this.r));
                }
                ji.util.d.bf(true);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A4");
                }
                if (this.jh != null) {
                    try {
                        boolean b10 = false;
                        final Color b11 = ji.util.d.b1(this.jh);
                        try {
                            final Color background = this.d.getBackground();
                            if (background == null) {
                                b10 = true;
                            }
                            else if (!background.equals(b11)) {
                                b10 = true;
                            }
                        }
                        catch (Exception ex15) {}
                        if (b10) {
                            this.d.setBackground(ji.util.d.b1(this.jh));
                        }
                    }
                    catch (Exception ex16) {}
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A5");
                }
                this.validate();
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit12A6");
                }
                if (this.d != null) {
                    this.d.o(this.dr, q(this.ds));
                }
                if (this.t0) {
                    if (this.sf) {
                        ji.document.ad.d0(true);
                        this.d.a(true, true);
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStartInit12A7");
                    }
                    if (!this.sd && !this.se && !ji.util.d.ck(this.uz)) {
                        if (this.d.a(this)) {
                            this.t0 = false;
                            this.d.c(this);
                        }
                        else {
                            this.shutdownQuickstart();
                        }
                    }
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStartInit13");
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.uz, "Applet paramaters processed");
                }
                this.d.bp(true);
                if (!this.t0 && !this.sd && !this.se) {
                    if (this.d.at()) {
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.uz, "jiStartInit license is ok");
                        }
                        b3 = false;
                        if (this.jx != null) {
                            this.d.a6(this.jx);
                        }
                        else {
                            boolean p10 = true;
                            if (this.hi != null) {
                                p10 = p(this.hi);
                            }
                            if (this.h3 != null) {
                                final double r = r(this.h3);
                                if (r > 0) {
                                    if (this.do != null && this.dp != null) {
                                        this.d.a6(this.d.a(r, q(this.do), q(this.dp), p10));
                                    }
                                    else {
                                        this.d.a6(this.d.a(r, p10));
                                    }
                                }
                            }
                            else if (this.h2 != null && p(this.h2)) {
                                this.d.a6(this.d.a(1.0, p10));
                                this.d.hs();
                            }
                        }
                        if (this.bo != null) {
                            this.d.y(this.bo);
                        }
                        if (this.bp != null && this.br == null) {
                            this.d.a(this.bp);
                        }
                        if (this.bq != null && this.bs == null) {
                            this.d.b(this.bq);
                        }
                        this.d.j(true);
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "jiStartInit12B");
                        }
                        if (ji.util.d.dv()) {
                            ji.io.h.e(this.uz, "Start Open doc");
                        }
                        this.c(true);
                        if (ji.util.d.dv()) {
                            ji.io.h.e(this.uz, "Start Done 1");
                        }
                    }
                    else {
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.uz, "jiStartInit license failure");
                        }
                        if (ji.util.d.dv()) {
                            ji.io.h.e(this.uz, "Start Done 2");
                        }
                    }
                    if (!this.sd && !this.se && this.ae != null) {
                        final URL[] array11 = new URL[this.ae.length];
                        for (int n17 = 0; n17 < this.ae.length; ++n17) {
                            final Object b12 = ji.util.d.b(codeBase, this.ae[n17], this.uz);
                            if (b12 != null && b12 instanceof URL) {
                                array11[n17] = (URL)b12;
                            }
                        }
                        this.d.a(array11, this.getAppletContext(), p(this.ac));
                    }
                }
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiStartInit14");
            }
            if (!this.sd && !this.se && !this.iy) {
                if (b3) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.uz, "jiStartInit destroying image due to start failure");
                    }
                    ji.util.e.a(new Color(153, 153, 153));
                    this.setBackground(new Color(153, 153, 153));
                    this.an();
                    this.ry = true;
                }
                else {
                    if (p && !this.iy) {
                        this.d.requestFocus();
                    }
                    if (!this.rw) {
                        this.rx = true;
                    }
                }
            }
        }
        catch (Exception ex3) {
            if (ji.util.d.cy()) {
                ji.io.h.d(this.uz, "jiStartInit exception occurred:".concat(String.valueOf(String.valueOf(ex3.getMessage()))));
            }
            ex3.printStackTrace();
            try {
                this.an();
                this.ry = true;
            }
            catch (Exception ex17) {}
        }
        finally {
            if (b3) {
                try {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.uz, "jiStartInit startFailed=".concat(String.valueOf(String.valueOf(b3))));
                    }
                    this.v();
                }
                catch (Exception ex18) {}
                this.rp = null;
                this.tw.b();
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "set commandLock to null due to failed start");
                }
                this.rx = false;
                try {
                    if (b5 && ji.util.d.e()) {
                        final String concat = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1124, this.uz))).concat("\n"))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1125, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1126, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1127, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1128, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1129, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1130, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1131, this.uz))).concat("\n")))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(ji.util.d.b(1132, this.uz))).concat("\n"))));
                        ji.io.h.d(this.uz, concat);
                        this.a(concat, ji.util.d.b(192, this.uz));
                    }
                }
                catch (Exception ex4) {
                    ex4.printStackTrace();
                }
            }
        }
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "jiStartInit15");
        }
    }
    
    private void n(String lowerCase) {
        if (ji.util.e.av()) {
            try {
                final Class<?> forName = Class.forName("javax.swing.UIManager");
                final m m = new m();
                m.a(forName);
                if (ji.util.i.c(252)) {
                    ji.io.h.d(this.uz, "Initial Look And Feel:".concat(String.valueOf(String.valueOf(m.c("getLookAndFeel").toString()))));
                }
                lowerCase = lowerCase.toLowerCase();
                Object o = null;
                if (lowerCase.equals("system")) {
                    o = m.c("getSystemLookAndFeelClassName");
                }
                else if (lowerCase.equals("default")) {
                    o = m.c("getCrossPlatformLookAndFeelClassName");
                }
                else {
                    final Object[] array = (Object[])m.c("getInstalledLookAndFeels");
                    for (int i = 0; i < array.length; ++i) {
                        final m j = new m(array[i]);
                        if (((String)j.c("getName")).toLowerCase().equals(lowerCase)) {
                            o = j.c("getClassName");
                            break;
                        }
                    }
                }
                if (o != null) {
                    m.a(m.a("setLookAndFeel", new String[] { "java.lang.String" }), o);
                    if (ji.util.i.c(252)) {
                        ji.io.h.d(this.uz, "Look and Feel set to: ".concat(String.valueOf(String.valueOf(o))));
                    }
                }
                else if (ji.util.i.c(252)) {
                    ji.io.h.d(this.uz, "Could not find requested Look and Feel so left as default.");
                }
            }
            catch (Exception ex) {
                String s = ex.getMessage();
                if (s == null) {
                    s = ex.toString();
                }
                ji.io.h.d(this.uz, "Could not set the system Look and Feel: ".concat(String.valueOf(String.valueOf(s))));
                ji.util.d.a(ex);
            }
        }
    }
    
    private void aa() {
        String s = this.pe;
        boolean b = false;
        if (ji.util.d.bf() || ji.util.d.av()) {
            String concat;
            if (s == null) {
                concat = "";
            }
            else {
                concat = String.valueOf(String.valueOf(s)).concat(", ");
            }
            s = String.valueOf(String.valueOf(concat)).concat("RULER, ANGLE, SQUARE, CIRCLE");
        }
        if (s != null) {
            final String[] t = this.t(s);
            this.d.z();
            boolean b2 = true;
            if (t != null) {
                for (int i = 0; i < t.length; ++i) {
                    if (t[i].toLowerCase().equals("show")) {
                        b2 = false;
                    }
                    else {
                        this.d.r(t[i]);
                        b = true;
                    }
                }
            }
            if (b2) {
                if (!this.d.ab()) {
                    this.d.v(true);
                }
            }
            else if (this.d.ab()) {
                this.d.v(false);
            }
        }
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "jiStartInit6");
        }
        String[][] array = null;
        boolean b3 = false;
        boolean b4 = false;
        this.d.ad();
        if (this.px != null) {
            array = new String[][] { this.t(this.px) };
            b3 = true;
        }
        else if (this.pz != null) {
            array = new String[this.pz.length][];
            for (int j = 0; j < this.pz.length; ++j) {
                array[j] = this.t(this.pz[j]);
            }
            b4 = true;
        }
        if (this.pw != null) {
            final String[] t2 = this.t(this.pw);
            if (t2 != null) {
                b = true;
                for (int k = 0; k < t2.length; ++k) {
                    if (b3) {
                        this.d.a(t2[k], array[0]);
                    }
                    else {
                        this.d.a(t2[k], new String[0]);
                    }
                }
            }
        }
        else if (this.py != null) {
            if (this.py.length > 0) {
                b = true;
            }
            for (int l = 0; l < this.py.length; ++l) {
                final String[] t3 = this.t(this.py[l]);
                for (int n = 0; n < t3.length; ++n) {
                    if (b4 && l < array.length) {
                        this.d.a(t3[n], array[l]);
                    }
                    else {
                        this.d.a(t3[n], new String[0]);
                    }
                }
            }
        }
        if (b) {
            this.d.an();
        }
    }
    
    private void ab() {
        Color[] array = null;
        Color[] array2 = null;
        Color[] array3 = null;
        if (this.bu != null) {
            array = new Color[this.bu.length];
            for (int i = 0; i < this.bu.length; ++i) {
                array[i] = ji.util.d.b1(this.bu[i]);
            }
        }
        if (this.bx != null) {
            array2 = new Color[this.bx.length];
            for (int j = 0; j < this.bx.length; ++j) {
                array2[j] = ji.util.d.b1(this.bx[j]);
            }
        }
        if (this.by != null) {
            array3 = new Color[this.by.length];
            for (int k = 0; k < this.by.length; ++k) {
                array3[k] = ji.util.d.b1(this.by[k]);
            }
        }
        this.d.a(this.bn, array, this.bv, array2);
        if (this.bw != null || array3 != null) {
            this.d.a(this.bw, array3);
        }
    }
    
    private final void a(final URL url, final ad ad) {
        if (this.ov != null) {
            ad.at(this.ov);
        }
        if (this.ou != null) {
            ad.a(ji.util.d.b(url, this.ou, this.uz).toString(), "post");
        }
        else if (this.oq != null) {
            ad.au(ji.util.d.b(url, this.oq, this.uz).toString());
        }
        else if (this.ot != null) {
            ad.a(ji.util.d.b(url, this.ot, this.uz).toString(), "servlet");
        }
        if (this.or != null) {
            ad.d(ji.util.d.b(url, this.or, this.uz).toString(), true);
        }
        if (this.ow != null) {
            ad.db(p(this.ow));
        }
        if (this.oy != null) {
            ad.dc(p(this.oy));
        }
        if (this.ox != null) {
            ji.util.d.h(p(this.ox));
        }
        if (this.oz != null) {
            ad.dd(p(this.oz));
        }
        if (this.o0 != null) {
            ad.c(this.o0);
        }
        else {
            ad.c((String[])null);
        }
        if (this.nu != null) {
            ji.util.i.a(223, p(this.nu));
        }
        if (this.nv != null && ad != null) {
            ad.a(p(this.nv), false);
        }
        if (this.e4 != null) {
            ji.util.i.a(0, p(this.e4));
        }
        if (this.fb != null) {
            if (p(this.fb)) {
                ji.util.i.a(0, false);
            }
            ad.ew(p(this.fb));
        }
        if (!ji.util.i.c(258)) {
            this.aa();
        }
    }
    
    private void a(final ad ad) {
        int[] array;
        if (this.ss != null) {
            array = new int[] { q(this.ss) };
        }
        else if (this.td != null) {
            array = new int[this.td.length];
            for (int i = 0; i < this.td.length; ++i) {
                array[i] = q(this.td[i]);
            }
        }
        else {
            array = new int[0];
        }
        final int length = array.length;
        int[] array2;
        if (this.st != null) {
            array2 = new int[] { q(this.st) };
        }
        else if (this.te != null) {
            array2 = new int[this.te.length];
            for (int j = 0; j < this.te.length; ++j) {
                array2[j] = q(this.te[j]);
            }
        }
        else {
            array2 = new int[0];
        }
        final int max = Math.max(length, array2.length);
        double[] array3;
        if (this.su != null) {
            array3 = new double[] { r(this.su) };
        }
        else if (this.tg != null) {
            array3 = new double[this.tg.length];
            for (int k = 0; k < this.tg.length; ++k) {
                array3[k] = q(this.tg[k]);
            }
        }
        else {
            array3 = new double[0];
        }
        final int max2 = Math.max(max, array3.length);
        Point[] array4;
        if (this.sz != null || this.s0 != null) {
            array4 = new Point[] { ji.adjustment.eh.x };
            if (this.sz != null) {
                array4[0].x = q(this.sz);
            }
            if (this.s0 != null) {
                array4[0].y = q(this.s0);
            }
        }
        else if (this.tj != null || this.tk != null) {
            final int n = (this.tj.length > this.tk.length) ? this.tj.length : this.tk.length;
            array4 = new Point[n];
            for (int l = 0; l < n; ++l) {
                if (this.tj != null && this.tj.length > l) {
                    array4[l].x = q(this.tj[l]);
                }
                if (this.tk != null) {
                    array4[l].y = q(this.tk[l]);
                }
            }
        }
        else {
            array4 = new Point[0];
        }
        final int max3 = Math.max(max2, array4.length);
        double[] array5;
        if (this.s2 != null) {
            array5 = new double[] { r(this.s2) };
        }
        else if (this.tm != null) {
            array5 = new double[this.tm.length];
            for (int n2 = 0; n2 < this.tm.length; ++n2) {
                array5[n2] = r(this.tm[n2]);
            }
        }
        else {
            array5 = new double[0];
        }
        final int max4 = Math.max(max3, array5.length);
        int[] array6;
        if (this.sv != null) {
            array6 = new int[] { q(this.sv) };
        }
        else if (this.ts != null) {
            array6 = new int[this.ts.length];
            for (int n3 = 0; n3 < this.ts.length; ++n3) {
                array6[n3] = q(this.ts[n3]);
            }
        }
        else {
            array6 = new int[0];
        }
        final int max5 = Math.max(max4, array6.length);
        int[] array7;
        if (this.sw != null) {
            array7 = new int[] { q(this.sw) };
        }
        else if (this.tu != null) {
            array7 = new int[this.tu.length];
            for (int n4 = 0; n4 < this.tu.length; ++n4) {
                array7[n4] = q(this.tu[n4]);
            }
        }
        else {
            array7 = new int[0];
        }
        final int max6 = Math.max(max5, array7.length);
        int[] array8;
        if (this.s4 != null) {
            array8 = new int[] { q(this.s4) };
        }
        else if (this.tq != null) {
            array8 = new int[this.tq.length];
            for (int n5 = 0; n5 < this.tq.length; ++n5) {
                array8[n5] = q(this.tq[n5]);
            }
        }
        else {
            array8 = new int[0];
        }
        final int max7 = Math.max(max6, array8.length);
        boolean[] array9;
        if (this.s9 != null) {
            array9 = new boolean[] { p(this.s9) };
        }
        else if (this.to != null) {
            array9 = new boolean[this.to.length];
            for (int n6 = 0; n6 < this.to.length; ++n6) {
                array9[n6] = p(this.to[n6]);
            }
        }
        else {
            array9 = new boolean[0];
        }
        ad.b(Math.max(max7, array9.length), array, array2, array3, array4, array5, array7, array6, array8, array9);
    }
    
    private void b(final ad ad) {
        int[] array;
        if (this.sl != null) {
            array = new int[] { q(this.sl) };
        }
        else if (this.tb != null) {
            array = new int[this.tb.length];
            for (int i = 0; i < this.tb.length; ++i) {
                array[i] = q(this.tb[i]);
            }
        }
        else {
            array = new int[0];
        }
        final int length = array.length;
        int[] array2;
        if (this.sm != null) {
            array2 = new int[] { q(this.sm) };
        }
        else if (this.tc != null) {
            array2 = new int[this.tc.length];
            for (int j = 0; j < this.tc.length; ++j) {
                array2[j] = q(this.tc[j]);
            }
        }
        else {
            array2 = new int[0];
        }
        final int max = Math.max(length, array2.length);
        double[] array3;
        if (this.sr != null) {
            array3 = new double[] { r(this.sr) };
        }
        else if (this.tf != null) {
            array3 = new double[this.tf.length];
            for (int k = 0; k < this.tf.length; ++k) {
                array3[k] = q(this.tf[k]);
            }
        }
        else {
            array3 = new double[0];
        }
        final int max2 = Math.max(max, array3.length);
        Point[] array4;
        if (this.sx != null || this.sy != null) {
            array4 = new Point[] { ji.adjustment.eh.x };
            if (this.sx != null) {
                array4[0].x = q(this.sx);
            }
            if (this.sy != null) {
                array4[0].y = q(this.sy);
            }
        }
        else if (this.th != null || this.ti != null) {
            final int n = (this.th.length > this.ti.length) ? this.th.length : this.ti.length;
            array4 = new Point[n];
            for (int l = 0; l < n; ++l) {
                if (this.th != null && this.th.length > l) {
                    array4[l].x = q(this.th[l]);
                }
                if (this.ti != null) {
                    array4[l].y = q(this.ti[l]);
                }
            }
        }
        else {
            array4 = new Point[0];
        }
        final int max3 = Math.max(max2, array4.length);
        double[] array5;
        if (this.s1 != null) {
            array5 = new double[] { r(this.s1) };
        }
        else if (this.tl != null) {
            array5 = new double[this.tl.length];
            for (int n2 = 0; n2 < this.tl.length; ++n2) {
                array5[n2] = r(this.tl[n2]);
            }
        }
        else {
            array5 = new double[0];
        }
        final int max4 = Math.max(max3, array5.length);
        int[] array6;
        if (this.sn != null) {
            array6 = new int[] { q(this.sn) };
        }
        else if (this.tr != null) {
            array6 = new int[this.tr.length];
            for (int n3 = 0; n3 < this.tr.length; ++n3) {
                array6[n3] = q(this.tr[n3]);
            }
        }
        else {
            array6 = new int[0];
        }
        final int max5 = Math.max(max4, array6.length);
        int[] array7;
        if (this.so != null) {
            array7 = new int[] { q(this.so) };
        }
        else if (this.tt != null) {
            array7 = new int[this.tt.length];
            for (int n4 = 0; n4 < this.tt.length; ++n4) {
                array7[n4] = q(this.tt[n4]);
            }
        }
        else {
            array7 = new int[0];
        }
        final int max6 = Math.max(max5, array7.length);
        int[] array8;
        if (this.s3 != null) {
            array8 = new int[] { q(this.s3) };
        }
        else if (this.tp != null) {
            array8 = new int[this.tp.length];
            for (int n5 = 0; n5 < this.tp.length; ++n5) {
                array8[n5] = q(this.tp[n5]);
            }
        }
        else {
            array8 = new int[0];
        }
        final int max7 = Math.max(max6, array8.length);
        boolean[] array9;
        if (this.s8 != null) {
            array9 = new boolean[] { p(this.s8) };
        }
        else if (this.tn != null) {
            array9 = new boolean[this.tn.length];
            for (int n6 = 0; n6 < this.tn.length; ++n6) {
                array9[n6] = p(this.tn[n6]);
            }
        }
        else {
            array9 = new boolean[0];
        }
        ad.a(Math.max(max7, array9.length), array, array2, array3, array4, array5, array7, array6, array8, array9);
    }
    
    private final void a(final String s, final String s2) {
        try {
            if (this.d != null) {
                this.remove(this.d);
            }
            this.setLayout(null);
            this.qs = new gu(s2);
            final Graphics graphics = this.getGraphics();
            final int n = 10;
            int max = 2 * 10;
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
            final int countTokens = stringTokenizer.countTokens();
            int height;
            final int n2 = height = graphics.getFontMetrics().getHeight();
            for (int i = 0; i < countTokens; ++i) {
                max = Math.max(max, graphics.getFontMetrics().stringWidth(stringTokenizer.nextToken()));
                height += n2;
            }
            final int n3 = height + 2 * n2;
            final int n4 = max + 2 * n;
            this.qs.getSize();
            this.qs.a("", false);
            this.qs.a(n4);
            this.qs.b(n3);
            this.ad();
            this.add(this.qs);
            this.validate();
            this.qs.b(s, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void ac() {
        try {
            this.initQuickstart();
            if (this.uo != null) {
                if (this.uo.d()) {
                    if (this.uo.e()) {
                        this.uo.a(false, this.iy);
                    }
                    else if (ji.util.i.c(105)) {
                        ji.io.h.d(this.uz, "QuickStart Applet: not starting quick start, because this browser is not supported");
                    }
                }
                else if (ji.util.i.c(105)) {
                    ji.io.h.d(this.uz, "QuickStart Applet: not starting quick start, because this classloader is not linked to the DLL");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void ad() {
        try {
            final Dimension size = this.getSize();
            final Dimension size2 = this.qs.getSize();
            this.qs.setBounds(Math.max((size.width - size2.width) / 2, 0), Math.max((size.height - size2.height) / 2, 0), size2.width, size2.height);
        }
        catch (Exception ex) {}
    }
    
    public String getAnnotationDefaults() {
        return this.pb;
    }
    
    public String getQuickstartURL() {
        try {
            if (this.iu == null) {
                return null;
            }
            final Object b = ji.util.d.b(this.getCodeBase(), this.iu, this.uz);
            if (b instanceof String) {
                ji.io.h.d(this.uz, "QuickStart URL is not valid: ".concat(String.valueOf(String.valueOf(b))));
                return null;
            }
            return b.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private static final void a(final ad ad, final String[] array, final String[] array2, String s, String s2, String s3) {
        if (array != null) {
            try {
                final gj gj = new gj();
                final int t = j.t;
                for (int i = 0; i < array.length; ++i) {
                    if (array[i] != null) {
                        String s4 = array[i];
                        final boolean startsWith = s4.toLowerCase().startsWith("image:".toLowerCase());
                        gj.a();
                        gj.x = j.t;
                        String aj = s4;
                        Color u = null;
                        Color t2 = null;
                        String q = null;
                        String s5 = null;
                        String r = null;
                        int w = 0;
                        int n = j.t;
                        double ap = 1.0;
                        if (array2 != null && array2.length > i) {
                            if (s == null) {
                                s = "<";
                            }
                            if (s2 == null) {
                                s2 = ">";
                            }
                            if (s3 == null) {
                                s3 = "=";
                            }
                            final String[] b = d.b(aj, array2[i], s, s2);
                            if (b != null) {
                                boolean b2 = true;
                                for (int j = 1; j < b.length; ++j) {
                                    String s6 = b[j];
                                    if (s6.startsWith(s)) {
                                        s6 = s6.substring(1);
                                    }
                                    if (s6.endsWith(s)) {
                                        s6 = s6.substring(0, s6.length() - 1);
                                    }
                                    final int index = s6.toLowerCase().indexOf(s3.toLowerCase());
                                    if (!d.by(s6) && (index < 0 || index >= s6.length() - 1)) {
                                        b2 = false;
                                        break;
                                    }
                                }
                                if (b2) {
                                    a(s4, gj, array2[i], s, s2, s3);
                                    aj = gj.aj;
                                    s4 = gj.ak;
                                    u = gj.al;
                                    t2 = gj.t;
                                    n = gj.x;
                                    w = gj.w;
                                    final String q2 = gj.q;
                                    s5 = gj.s;
                                    r = gj.r;
                                    q = gj.q;
                                    ap = gj.ap;
                                }
                                else if (b.length > 1) {
                                    aj = b[1];
                                    try {
                                        if (b.length > 2) {
                                            if (startsWith) {
                                                s4 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s4))).append("<").append(b[2]).append(">")));
                                            }
                                            else {
                                                u = d.b1(b[2]);
                                                if (u != null) {
                                                    if (u.equals(SystemColor.control)) {
                                                        u = null;
                                                    }
                                                    gj.u = u;
                                                }
                                            }
                                        }
                                        if (!startsWith) {
                                            if (b.length > 3) {
                                                t2 = d.b1(b[3]);
                                                if (t2 != null) {
                                                    if (t2.equals(SystemColor.control)) {
                                                        t2 = null;
                                                    }
                                                    gj.t = t2;
                                                }
                                            }
                                            if (b.length > 5) {
                                                w = Math.max(q(b[5]), 0);
                                                gj.w = w;
                                            }
                                        }
                                    }
                                    catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                        }
                        ad.a("raw:".concat(String.valueOf(String.valueOf(aj))), s4, u, t2, j.v(), n, w, q, s5, r, gj, true, ap);
                    }
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    private static final gj a(final String s, gj gj, final String s2, String s3, String s4, String s5) {
        try {
            if (gj == null) {
                gj = new gj();
            }
            if (s3 == null) {
                s3 = "<";
            }
            if (s4 == null) {
                s4 = ">";
            }
            if (s5 == null) {
                s5 = "=";
            }
            final String[] b = d.b("", String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(s3).append(s4))), s3, s4);
            boolean b2 = true;
            c c = null;
            if (b != null) {
                c = new c("jiAppletTagProps");
                for (int i = 1; i < b.length; ++i) {
                    String s6 = b[i];
                    if (s6.startsWith(s3)) {
                        s6 = s6.substring(1);
                    }
                    if (s6.endsWith(s3)) {
                        s6 = s6.substring(0, s6.length() - 1);
                    }
                    final int index = s6.toLowerCase().indexOf(s5.toLowerCase());
                    if (!d.by(s6)) {
                        if (index < 0 || index >= s6.length() - 1) {
                            b2 = false;
                            System.out.println(String.valueOf(String.valueOf(new StringBuffer("Property list seems ot be missing a value pair (see '").append(s6).append("')"))));
                            break;
                        }
                        c.c(s6);
                    }
                }
            }
            if (b2) {
                gj.a();
                gj.x = j.t;
                gj.ak = s;
                boolean startsWith = false;
                if (s != null) {
                    gj.am = s;
                    startsWith = s.toLowerCase().startsWith("image:".toLowerCase());
                }
                for (int j = 0; j < c.b(); ++j) {
                    try {
                        final String s7 = (String)c.b(j);
                        final int index2 = s7.toLowerCase().indexOf(s5.toLowerCase());
                        if (index2 >= 0 && index2 < s7.length() - 1) {
                            final String lowerCase = s7.substring(0, index2).toLowerCase();
                            final String bc = d.bc(s7.substring(index2 + 1));
                            if (lowerCase.equals("menu")) {
                                gj.aj = bc;
                            }
                            else if (lowerCase.equals("color")) {
                                if (startsWith) {
                                    gj.ak = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(gj.ak))).append("<").append(bc).append(">")));
                                }
                                else {
                                    gj.al = d.b1(bc);
                                    if (gj.al != null && gj.al.equals(SystemColor.control)) {
                                        gj.al = null;
                                    }
                                    gj.u = gj.al;
                                }
                            }
                            else if (lowerCase.equals("fillcolor")) {
                                if (!startsWith) {
                                    gj.t = d.b1(bc);
                                    if (gj.t.equals(SystemColor.control)) {
                                        gj.t = null;
                                    }
                                }
                            }
                            else if (lowerCase.equals("rotation")) {
                                if (!startsWith) {
                                    gj.w = d.c(bc, 0);
                                }
                            }
                            else if (lowerCase.equals("fontheight")) {
                                if (!startsWith) {
                                    gj.x = d.c(bc, -1);
                                }
                            }
                            else if (lowerCase.equals("pwmodify")) {
                                gj.q = bc;
                            }
                            else if (lowerCase.equals("pwsecurity")) {
                                gj.s = bc;
                            }
                            else if (lowerCase.equals("pwtext")) {
                                gj.r = bc;
                            }
                            else if (lowerCase.equals("read")) {
                                gj.ad = p(bc);
                            }
                            else if (lowerCase.equals("modify")) {
                                gj.ae = p(bc);
                            }
                            else if (lowerCase.equals("print")) {
                                gj.af = p(bc);
                            }
                            else if (lowerCase.equals("execute")) {
                                gj.ag = p(bc);
                            }
                            else if (lowerCase.equals("delete")) {
                                gj.ah = p(bc);
                            }
                            else if (lowerCase.equals("modifysecurity")) {
                                gj.ai = p(bc);
                            }
                            else if (lowerCase.equals("owner")) {
                                gj.y = bc;
                            }
                            else if (lowerCase.equals("label")) {
                                gj.an = bc;
                            }
                            else if (lowerCase.equals("helptext")) {
                                gj.ao = bc;
                            }
                            else if (lowerCase.equals("scale")) {
                                gj.ap = r(bc);
                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
        catch (Exception ex2) {}
        return gj;
    }
    
    private final void c(final boolean b) throws Exception {
        try {
            ++jiApplet.rt;
            this.rw = true;
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "A-doOpenDoc1");
            }
            if (this.ae()) {
                if (jiApplet.ud || ji.util.d.an) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.a("OpenDocAction"))).concat(": task start-up aborted due to pending shutown for this task."));
                }
            }
            else if (!ji.util.d.ck(this.uz)) {
                new bb(this.uz, new xu(b)).start();
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "A-doOpenDoc2");
            }
        }
        finally {
            --jiApplet.rt;
        }
    }
    
    private boolean ae() {
        return this.sh;
    }
    
    private final void d(final boolean b) {
        try {
            if (this.ae()) {
                if (jiApplet.ud || ji.util.d.an) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.a("OpenDocAction"))).concat(": task aborted due to pending shutown for this task."));
                }
            }
            else {
                ++jiApplet.rt;
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-jiOpenDoc1");
                }
                this.e(b);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-jiOpenDoc2");
                }
            }
        }
        catch (Exception ex) {
            if (ex.toString().toLowerCase().indexOf("null") < 0 || ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        finally {
            --jiApplet.rt;
        }
    }
    
    private final void e(final boolean b) throws Exception {
        try {
            boolean b2 = false;
            int bl = -1;
            int length = -1;
            if (!this.sd && !this.se) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-Open1");
                }
                if (this.bc != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open2");
                    }
                    this.d.b((int[])null);
                    bl = this.bl;
                    length = this.bc.length;
                }
                this.d.a(bl, length);
                final URL codeBase = this.getCodeBase();
                if (this.g != null) {
                    this.d.ag(this.g);
                }
                if (this.hd != null) {
                    this.d.as(this.hd);
                }
                if (this.o4 != null) {
                    ji.util.d.aw(p(this.o4));
                }
                if (this.o3 != null) {
                    this.d.f(this.o3);
                }
                if (this.qg != null) {
                    this.d.g(this.qg);
                }
                if (this.qo != null || this.qp != null || this.qm != null) {
                    String s = null;
                    int n = 0;
                    if (this.qm != null) {
                        s = this.qm;
                    }
                    else if (this.qo != null) {
                        n = 2;
                        s = this.qo;
                    }
                    else if (this.qp != null) {
                        n = 1;
                        s = this.qp;
                    }
                    boolean p = false;
                    if (this.qq != null) {
                        p = p(this.qq);
                    }
                    this.d.a(s, n, p);
                    if (this.qn != null) {
                        ji.util.i.a(184, p(this.qn));
                    }
                }
                if (this.qr != null) {
                    ji.util.i.a(107, p(this.qr));
                }
                if (this.rh != null) {
                    ji.util.i.a(109, p(this.rh));
                }
                if (this.ri != null) {
                    this.d.s(p(this.ri));
                }
                if (this.nr != null) {
                    this.d.eh(p(this.nr));
                }
                if (this.ns != null) {
                    ji.util.i.a(87, p(this.ns));
                }
                if (this.ba != null) {
                    final Object b3 = ji.util.d.b(this.af(), ji.util.d.bc(this.ba), this.uz);
                    if (b3 != null) {
                        if (b3 instanceof String) {
                            this.d.k(b3);
                        }
                        else {
                            this.d.k(b3);
                        }
                    }
                }
                if (this.o1 != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open3");
                    }
                    final Object b4 = ji.util.d.b(this.af(), ji.util.d.bc(this.o1), this.uz);
                    if (b4 != null) {
                        if (b4 instanceof String) {
                            this.d.h(b4);
                        }
                        else {
                            this.d.h(b4);
                        }
                    }
                }
                if (this.o2 != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open4");
                    }
                    final Object b5 = ji.util.d.b(this.af(), ji.util.d.bc(this.o2), this.uz);
                    if (b5 != null) {
                        if (b5 instanceof String) {
                            this.d.i(b5);
                        }
                        else {
                            this.d.i(b5);
                        }
                    }
                }
                int max = 1;
                if (this.l9 != null) {
                    ji.util.i.a(111, p(this.l9));
                }
                if (this.ax != null) {
                    ji.util.i.a(92, p(this.ax));
                }
                if (this.ay != null) {
                    ji.util.d.a((long)(q(this.ay) * 1024 * 1024));
                }
                if (this.au != null) {
                    ji.util.i.a(179, p(this.au));
                }
                if (this.av != null) {
                    ji.util.i.a(178, p(this.av));
                }
                if (this.as != null) {
                    ji.util.d.j(p(this.as));
                }
                try {
                    this.at = this.getParameterWithDefault("fileNetCOLDUseTemplateResolution", null);
                }
                catch (Exception ex) {}
                if (this.at != null) {
                    ji.util.i.a(246, p(this.at));
                }
                if (this.ar != null) {
                    ji.util.d.k(this.ar);
                }
                if (this.ap != null) {
                    ji.util.d.l(this.ap);
                }
                if (this.aq != null) {
                    ji.util.d.m(this.aq);
                }
                if (this.am != null) {
                    this.d.aj(q(this.am));
                }
                if (this.al != null) {
                    max = Math.max(q(this.al), 1);
                }
                if (this.an != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-OpenBack1");
                    }
                    final Object b6 = ji.util.d.b(this.af(), this.an, this.uz);
                    if (b6 != null) {
                        b2 = true;
                        if (b6 instanceof String) {
                            this.d.a(b6, max);
                        }
                        else {
                            this.d.a(b6, max);
                        }
                    }
                }
                else if (this.ao != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-OpenBack2");
                    }
                    final Object[] array = new Object[this.ao.length];
                    try {
                        for (int i = 0; i < this.ao.length; ++i) {
                            array[i] = ji.util.d.b(this.af(), this.ao[i], this.uz);
                        }
                    }
                    catch (Exception ex2) {}
                    this.d.b(array, max);
                }
                if (this.ji != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-OpenForeCol1");
                    }
                    this.d.g(ji.util.d.b1(this.ji));
                }
                else if (this.jj != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-OpenForeCol12");
                    }
                    final Color[] array2 = new Color[this.jj.length];
                    try {
                        for (int j = 0; j < this.jj.length; ++j) {
                            array2[j] = ji.util.d.b1(this.jj[j]);
                        }
                    }
                    catch (Exception ex3) {}
                    this.d.a(array2);
                }
                int q = 1;
                if (this.ad != null) {
                    q = q(this.ad);
                }
                if (this.om != null) {
                    this.d.a8(this.om);
                }
                if (this.oi != null) {
                    this.d.a9(this.oi);
                }
                if (this.br != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open5");
                    }
                    final Object b7 = ji.util.d.b(codeBase, this.br, this.uz);
                    if (b7 != null) {
                        this.d.d(b7);
                    }
                }
                if (this.bs != null) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open5a");
                    }
                    final Object b8 = ji.util.d.b(codeBase, this.bs, this.uz);
                    if (b8 != null) {
                        this.d.e(b8);
                    }
                }
                this.d.bv(b);
                if (!this.ae()) {
                    if (this.q != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open6");
                        }
                        this.d.k(this.q, q);
                        b2 = true;
                    }
                    else if (this.p != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open7");
                        }
                        final Object b9 = ji.util.d.b(codeBase, this.p, this.uz);
                        if (b9 != null) {
                            b2 = true;
                            if (b9 instanceof String) {
                                this.d.j((String)b9, q);
                            }
                            else {
                                this.d.c((URL)b9, q);
                            }
                        }
                    }
                    else if (this.h != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open8");
                        }
                        final Object b10 = ji.util.d.b(this.af(), this.h, this.uz);
                        if (b10 != null) {
                            b2 = true;
                            if (b10 instanceof String) {
                                this.d.h((String)b10, q);
                            }
                            else {
                                this.d.a((URL)b10, q);
                            }
                        }
                    }
                    else if (this.i != null && this.j != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open9");
                        }
                        final int q2 = q(this.j);
                        if (q2 > 0) {
                            final Object[] array3 = new Object[q2];
                            try {
                                for (int k = 0; k < q2; ++k) {
                                    array3[k] = ji.util.d.b(this.af(), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append("&page=").append(k + 1))), this.uz);
                                }
                            }
                            catch (Exception ex4) {}
                            b2 = true;
                            this.d.a(this.i, array3, q);
                            this.j = null;
                            this.i = null;
                        }
                    }
                    else if (this.af != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open10");
                        }
                        this.ah();
                        if (this.af != null) {
                            Object[] array4 = null;
                            if (this.ag != null) {
                                array4 = new Object[this.ag.length];
                                try {
                                    for (int l = 0; l < this.ag.length; ++l) {
                                        array4[l] = ji.util.d.b(this.af(), this.ag[l], this.uz);
                                    }
                                }
                                catch (Exception ex5) {}
                            }
                            final Object[] array5 = new Object[this.af.length];
                            try {
                                for (int n2 = 0; n2 < this.af.length; ++n2) {
                                    array5[n2] = ji.util.d.b(this.af(), this.af[n2], this.uz);
                                }
                            }
                            catch (Exception ex6) {}
                            b2 = true;
                            Object o = null;
                            if (this.bc != null && this.bl < this.bc.length && this.bl >= 0) {
                                o = this.bc[this.bl];
                            }
                            if (array4 != null) {
                                this.d.a(o, array5, array4, q);
                            }
                            else {
                                this.d.a(o, array5, q);
                            }
                        }
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open11");
                    }
                    if (this.gd != null && ji.util.i.c(155)) {
                        this.d.a(p(this.gd), -1);
                    }
                    this.d.gw();
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "A-Open12");
                    }
                    this.ab();
                    if (this.bt != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open13");
                        }
                        final Object b11 = ji.util.d.b(codeBase, this.bt, this.uz);
                        if (b11 != null) {
                            if (b11 instanceof String) {
                                this.d.c(b11);
                            }
                            else {
                                this.d.c(b11);
                            }
                        }
                    }
                    else if (this.ai != null) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "A-Open14");
                        }
                        final Object[] array6 = new Object[this.ai.length];
                        try {
                            for (int n3 = 0; n3 < this.ai.length; ++n3) {
                                array6[n3] = ji.util.d.b(this.af(), this.ai[n3], this.uz);
                            }
                        }
                        catch (Exception ex7) {}
                        this.d.a(array6);
                    }
                }
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "A-Open15 openIssued=".concat(String.valueOf(String.valueOf(b2))));
            }
            if (!this.ae()) {
                if (!b2 && (ji.util.d.do() || ji.util.d.as(this.uz))) {
                    this.d.k(true);
                    this.d.a((URL)ji.util.d.b(this.getCodeBase(), "notfound.tif", this.uz), 1);
                    this.d.gw();
                }
                if (this.gd != null && !ji.util.i.c(155)) {
                    this.d.a(p(this.gd), -1);
                }
                if (!b2) {
                    ji.util.i.a(2);
                    this.d.ap();
                    this.d.ao();
                }
                this.d.a(true, b2, this.iv);
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "A-Open16");
            }
        }
        finally {
            this.rw = false;
            this.rx = true;
            this.x();
        }
    }
    
    private URL af() throws Exception {
        if (this.dm != null) {
            URL a;
            if (ji.util.i.c(270)) {
                a = this.a(this.dm, true);
            }
            else {
                a = new URL(this.dm);
            }
            if (ji.util.i.c(79)) {
                ji.io.h.d(this.uz, "FileBase Value: ".concat(String.valueOf(String.valueOf(a.toString()))));
            }
            return a;
        }
        return this.getCodeBase();
    }
    
    private String ag() throws Exception {
        if (this.dn != null) {
            String s;
            if (ji.util.i.c(270)) {
                s = this.a(this.dn, false).toString();
            }
            else {
                s = this.dn;
            }
            if (ji.util.i.c(79)) {
                ji.io.h.d(this.uz, "DocumentBase Value: ".concat(String.valueOf(String.valueOf(s))));
            }
            return s;
        }
        return this.fq;
    }
    
    private URL a(final String s, final boolean b) throws Exception {
        if (s.toLowerCase().indexOf("<documentbase>") != -1) {
            final URL a = ji.util.d.a(this.getDocumentBase(), this.uz);
            if (a != null) {
                return ji.util.d.a(s, "<documentbase>", a, b, this.uz);
            }
        }
        return new URL(s);
    }
    
    private final void ah() {
        if (this.bm != null) {
            final int[] array = new int[this.bm.length];
            try {
                for (int i = 0; i < this.bm.length; ++i) {
                    array[i] = ji.util.d.c(this.bm[i], 0);
                }
            }
            catch (Exception ex) {}
            this.d.a(array);
        }
    }
    
    private void a(final a3 a3) {
        if (this.o5 && this.rx) {
            try {
                if (this.c() != null) {
                    try {
                        final Enumeration<Applet> applets = this.getAppletContext().getApplets();
                        while (applets.hasMoreElements()) {
                            final Applet applet = applets.nextElement();
                            if (applet != null) {
                                boolean b = true;
                                boolean b2 = false;
                                if (applet.equals(this)) {
                                    b2 = true;
                                }
                                if (this.e != null && applet.equals(this.e)) {
                                    b2 = true;
                                }
                                if (b2) {
                                    b = false;
                                    if (a3.q()) {
                                        this.a(a3, this.ki, this.d);
                                    }
                                    else if (this.d.d1()) {
                                        this.a(a3, this.ki, this.d.d2());
                                    }
                                }
                                if (!b) {
                                    continue;
                                }
                                m m = null;
                                try {
                                    m = new m(applet);
                                    final Method b3 = m.b("performLinkCommand");
                                    if (b3 != null) {
                                        m.a(b3, a3, this.ki);
                                    }
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                finally {
                                    m.b();
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ji.util.d.a(ex2);
                    }
                }
            }
            catch (Exception ex3) {
                ji.util.d.a(ex3);
            }
        }
    }
    
    public final String javaScriptUpdate(final a3 a3) {
        return this.b(a3);
    }
    
    private final String b(final a3 a3) {
        String s = "";
        if (!this.iy) {
            try {
                if (b(this.uz) || ji.util.d.bo(this.uz) || ji.util.d.ai(this.uz) || ji.util.d.do() || ji.util.d.am(this.uz)) {
                    if (a3.j() == 49) {
                        this.a(a3);
                    }
                    else if (a3.j() == 16) {
                        if (ji.util.i.c(115) && this.d.c2() && this.bc != null) {
                            this.ty = true;
                        }
                        this.l();
                        a3.a(20);
                        a3.a(String.valueOf(String.valueOf(new StringBuffer("Doc ").append(this.getDocIndex()).append(" of ").append(this.getNumDocs()))));
                    }
                    else if (a3.j() == 17) {
                        if (ji.util.i.c(115) && this.d.c2() && this.bc != null) {
                            this.ty = true;
                        }
                        this.m();
                        a3.a(20);
                        a3.a(String.valueOf(String.valueOf(new StringBuffer("Doc ").append(this.getDocIndex()).append(" of ").append(this.getNumDocs()))));
                    }
                    else if (a3.j() == 18) {
                        if (ji.util.i.c(115) && this.d.c2() && this.bc != null) {
                            this.ty = true;
                        }
                        this.n();
                        a3.a(20);
                        a3.a(String.valueOf(String.valueOf(new StringBuffer("Doc ").append(this.getDocIndex()).append(" of ").append(this.getNumDocs()))));
                    }
                    else if (a3.j() == 19) {
                        if (ji.util.i.c(115) && this.d.c2() && this.bc != null) {
                            this.ty = true;
                        }
                        this.o();
                        a3.a(20);
                        a3.a(String.valueOf(String.valueOf(new StringBuffer("Doc ").append(this.getDocIndex()).append(" of ").append(this.getNumDocs()))));
                    }
                    else if (a3.j() == 20) {
                        try {
                            this.ty = true;
                            this.openDoc(a3.k());
                        }
                        finally {
                            this.ty = false;
                        }
                    }
                    if (a3.j() == 11) {
                        s = this.a(a3.l(), a3.j());
                    }
                    else if (this.fh != null || this.fj != null || this.fi != null || this.fn != null) {
                        if (this.fh == null && this.fj == null && this.fi != null && this.fn == null) {
                            s = "Javascript event handler has not been defined";
                        }
                        else if (this.fh != null || this.fj != null || this.fn != null) {
                            if (this.jw == null) {
                                if (this.fn == null || a3.j() == 23) {
                                    return String.valueOf(String.valueOf(new StringBuffer("The MayScript tag needs to be included in your HTML, and set to a value of 'TRUE' for your EventHandler to work (").append(this.fh).append(").")));
                                }
                            }
                            else if (!this.ai()) {
                                return String.valueOf(String.valueOf(new StringBuffer("The MayScript tag needs to be set to a value of 'TRUE' for your EventHandler to work (").append(this.fh).append(").")));
                            }
                            if (this.fh != null) {
                                boolean b = false;
                                if (this.t5 != null && this.t5.d("".concat(String.valueOf(String.valueOf(a3.j())))) != null) {
                                    b = true;
                                }
                                if (ji.util.i.c(6)) {
                                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: Event propogate = ").append(b).append(", event = ").append(a3))));
                                }
                                if (b) {
                                    if (a3.q() && a3.j() != 23) {
                                        s = this.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.fh))).append("(").append(a3.j()).append(", '").append(a3.l()).append(" (NW)')"))), a3.j());
                                    }
                                    else {
                                        s = this.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.fh))).append("(").append(a3.j()).append(", '").append(a3.l()).append("')"))), a3.j());
                                    }
                                }
                            }
                            if (this.fj != null && a3.j() != 34 && a3.j() != 44 && a3.j() != 45) {
                                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(this.getCodeBase(), new String(this.fj), this.uz).toString()))).append("id=").append(a3.j()).append("&description=").append(ji.util.d.bq(a3.l()))));
                                if (ji.util.d.cy()) {
                                    ji.io.h.d(this.uz, "Server object = ".concat(String.valueOf(String.valueOf(value))));
                                }
                                ji.util.d.a(value, this, null, true, this.uz);
                            }
                            if (this.fn != null && a3.j() != 23 && a3.j() != 44 && a3.j() != 45) {
                                int n = 1;
                                boolean b2 = false;
                                if (this.ac != null) {
                                    n = (p(this.ac) ? 0 : 1);
                                }
                                if (n != 0) {
                                    if (a3.j() == 7) {
                                        b2 = true;
                                    }
                                }
                                else if (a3.j() == 8) {
                                    b2 = true;
                                }
                                if (b2) {
                                    final String s2 = a3.s();
                                    if (s2 != null && s2.toLowerCase().equals("image")) {
                                        boolean b3 = true;
                                        if (this.fo != null) {
                                            final char[] charArray = this.fo.toLowerCase().toCharArray();
                                            b3 = false;
                                            if (a3.m() == charArray[0]) {
                                                b3 = true;
                                            }
                                            if (a3.m() == this.fo.toUpperCase().toCharArray()[0]) {
                                                b3 = true;
                                            }
                                            if (b3) {
                                                ji.util.d.e1();
                                            }
                                        }
                                        if (b3) {
                                            final String bq = ji.util.d.bq(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(this.getCodeBase(), new String(this.fn), this.uz).toString(), "<x>", "".concat(String.valueOf(String.valueOf(a3.n())))), "<y>", "".concat(String.valueOf(String.valueOf(a3.o())))), "<r>", "".concat(String.valueOf(String.valueOf(a3.r())))), "<f>", "".concat(String.valueOf(String.valueOf(a3.t())))), "<fo>", "".concat(String.valueOf(String.valueOf(ji.util.d.h(a3.t().toString(), this.uz))))), "<z>", "".concat(String.valueOf(String.valueOf(a3.p())))));
                                            if (ji.util.d.cy()) {
                                                ji.io.h.d(this.uz, "Server object = ".concat(String.valueOf(String.valueOf(bq))));
                                            }
                                            if (this.fp != null) {
                                                ji.util.e.a(new URL(bq), this.fp, this, null, this.uz, "AppletOpenOnClick1");
                                            }
                                            else {
                                                ji.util.e.a(new URL(bq), this, null, this.uz, "AppletOpenOnClick2");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                s = ex.toString();
                ex.printStackTrace();
            }
        }
        return s;
    }
    
    private final boolean ai() {
        try {
            if (!this.sc) {
                if (this.jw != null) {
                    this.sb = p(this.jw);
                }
                else {
                    this.sb = false;
                }
                this.sc = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return this.sb;
    }
    
    private Object aj() {
        try {
            Object window = null;
            try {
                final Object c = this.c(this.uz);
                if (c != null && c != this) {
                    window = this.d(this.uz).c("getJSWindow");
                }
                else {
                    window = JSObject.getWindow((Applet)this);
                }
            }
            catch (Throwable t) {
                if (ji.util.d.cy()) {
                    t.printStackTrace();
                }
            }
            if (window == null) {
                try {
                    final Object c2 = this.c(this.uz);
                    if (c2 != null && c2 instanceof Applet) {
                        return JSObject.getWindow((Applet)c2);
                    }
                    return JSObject.getWindow((Applet)this);
                }
                catch (Exception ex) {
                    if (ex instanceof JSException) {
                        ji.io.h.d(this.uz, "Unable to retrieve JS parent window. Ensure that a mayscript=\"true\" element is listed in the <applet> tag");
                    }
                    return null;
                }
            }
            return window;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            ji.io.h.d(this.uz, "getJSWindow failed: ".concat(String.valueOf(String.valueOf(noClassDefFoundError.getMessage()))));
            if (ji.util.d.cy()) {
                noClassDefFoundError.printStackTrace();
            }
            return null;
        }
    }
    
    private Object o(final String s) {
        try {
            final Object c = this.c(this.uz);
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: Parent = ").append(c))));
            }
            if (c != null && c != this) {
                return this.d(this.uz).a("evalJSCommand", s);
            }
            final JSObject window = JSObject.getWindow((Applet)this);
            if (window != null) {
                return window.eval(s);
            }
            return null;
        }
        catch (Exception ex) {
            try {
                final JSObject window2 = JSObject.getWindow((Applet)this);
                if (window2 != null) {
                    return window2.eval(s);
                }
                return null;
            }
            catch (Exception ex2) {
                return null;
            }
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            ji.io.h.d(this.uz, "evalJSCommand failed: ".concat(String.valueOf(String.valueOf(noClassDefFoundError.getMessage()))));
            if (ji.util.d.cy()) {
                noClassDefFoundError.printStackTrace();
            }
            return null;
        }
    }
    
    private final boolean ak() {
        boolean b = true;
        try {
            if (this.ai()) {
                final Object aj = this.aj();
                JSObject jsObject = null;
                if (aj instanceof JSObject) {
                    jsObject = (JSObject)this.aj();
                }
                if (jsObject == null) {
                    b = false;
                }
                if (jsObject.toString().toLowerCase().indexOf("self") >= 0) {
                    b = false;
                }
            }
            else {
                b = false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            ji.io.h.d(this.uz, "initJS failed: ".concat(String.valueOf(String.valueOf(noClassDefFoundError.getMessage()))));
            if (ji.util.d.cy()) {
                noClassDefFoundError.printStackTrace();
            }
            b = false;
        }
        return b;
    }
    
    public final void setEventHandlerResponse(final String un) {
        this.un = un;
    }
    
    private final String a(final String s, final int n) {
        try {
            if (this.ai() && (!ji.util.d.ay(this.uz) || ji.util.d.am(this.uz))) {
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, "EventHandler: calling ".concat(String.valueOf(String.valueOf(s))));
                }
                this.un = null;
                Object o = this.o(s);
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("EventHandler: ").append(this.fh).append(" returned <").append(o).append(">"))));
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("EventHandler: ").append(this.fh).append(" response <").append(this.un).append(">"))));
                }
                if (o == null) {
                    o = this.un;
                }
                else if (o.toString().toLowerCase().startsWith("undefined")) {
                    o = this.un;
                }
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("EventHandler: ").append(this.fh).append(" final response <").append(o).append(">"))));
                }
                try {
                    if (o == null) {
                        return "";
                    }
                    if (n == 23) {
                        return "";
                    }
                    return o.toString();
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex) {
            if (!this.se) {
                ji.io.h.d(this.uz, "Error processing : ".concat(String.valueOf(String.valueOf(s))));
                this.nc = ex.toString();
                ex.printStackTrace();
                return ex.toString();
            }
        }
        return "";
    }
    
    private final void f(final boolean b) {
        if (!this.r7 && b(this.uz)) {
            try {
                final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
                if (!this.ak()) {
                    return;
                }
                final String a = this.a(this.km, -1);
                final String a2 = this.a(this.kn, -1);
                final double doubleValue = Double.valueOf(a);
                final double doubleValue2 = Double.valueOf(a2);
                final int n = (int)Math.round(doubleValue);
                final int n2 = (int)Math.round(doubleValue2);
                boolean b2 = false;
                if (this.r9 == null) {
                    this.r9 = new Dimension(n, n2);
                }
                else if (this.r9.width != n || this.r9.height != n2) {
                    b2 = true;
                }
                final double n3 = doubleValue * this.qi / 100.0;
                final double n4 = doubleValue2 * this.qj / 100.0;
                rectangle.width = n - this.qk;
                rectangle.height = n2 - this.ql;
                final Rectangle bounds = this.getBounds();
                if ((rectangle.x < bounds.x || rectangle.y < bounds.y || rectangle.width > bounds.width + 5 || rectangle.height > bounds.height + 5) && b2 && this.r5) {
                    this.r6 = true;
                    if (!b && !this.r7) {
                        this.r7 = true;
                        this.remove(this.d);
                        this.a("location.reload()", -1);
                    }
                }
                if (!this.r7 && !b) {
                    if (this.qv == null) {
                        this.qv = new Rectangle(0, 0, 0, 0);
                    }
                    this.qv.x = rectangle.x;
                    this.qv.y = rectangle.y;
                    this.qv.width = rectangle.width;
                    this.qv.height = rectangle.height;
                    if (this.qv.x < bounds.x) {
                        this.qv.x = bounds.x;
                    }
                    if (this.qv.y < bounds.y) {
                        this.qv.y = bounds.y;
                    }
                    if (this.qv.width > bounds.width) {
                        this.qv.width = bounds.width;
                    }
                    if (this.qv.height > bounds.height) {
                        this.qv.height = bounds.height;
                    }
                    this.r5 = true;
                }
                if (!b && this.qv != null && (this.qv.width <= 0 || this.qv.height <= 0)) {
                    this.qv = null;
                }
            }
            catch (Exception ex) {
                if (!b) {
                    this.qv = null;
                }
            }
        }
    }
    
    public void setBackgound(final Color background) {
        try {
            if (background != null && !this.iy) {
                ji.util.e.a(ji.util.d.b1(this.jh));
                super.setBackground(ji.util.d.b1(this.jh));
                if (this.d != null) {
                    this.d.setBackground(background);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private static boolean p(final String s) {
        final String bc = d.bc(s);
        return bc != null && bc.toLowerCase().equals("true");
    }
    
    private static int q(final String s) {
        int int1 = 0;
        if (!d.by(s)) {
            final String bc = d.bc(s);
            try {
                if (bc != null && v(bc)) {
                    int1 = Integer.parseInt(bc);
                }
            }
            catch (Exception ex) {}
        }
        return int1;
    }
    
    private static double r(final String s) {
        return d.a(d.bc(s), 0.0);
    }
    
    private boolean[] s(final String s) {
        final String bc = ji.util.d.bc(s);
        if (bc != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(bc, ",");
            final int countTokens = stringTokenizer.countTokens();
            final boolean[] array = new boolean[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken != null && nextToken.toLowerCase().indexOf("true") >= 0) {
                    array[i] = true;
                }
            }
            return array;
        }
        return null;
    }
    
    private String[] t(final String s) {
        final String bc = ji.util.d.bc(s);
        if (bc != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(bc, ",");
            final int countTokens = stringTokenizer.countTokens();
            final String[] array = new String[countTokens];
            for (int i = 0; i < countTokens; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken != null) {
                    array[i] = ji.util.d.bc(nextToken);
                }
            }
            return array;
        }
        return null;
    }
    
    private Rectangle u(final String s) {
        final String bc = ji.util.d.bc(s);
        if (bc == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(bc, ",");
        if (stringTokenizer.countTokens() == 4) {
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            final String nextToken3 = stringTokenizer.nextToken();
            final String nextToken4 = stringTokenizer.nextToken();
            Rectangle rectangle = null;
            if (nextToken != null && nextToken2 != null && nextToken3 != null && nextToken4 != null) {
                rectangle = new Rectangle(ji.util.d.c(nextToken, 40), ji.util.d.c(nextToken2, 40), ji.util.d.c(nextToken3, 200), ji.util.d.c(nextToken4, 220));
            }
            return rectangle;
        }
        return null;
    }
    
    private static boolean v(final String s) {
        final String bc = d.bc(s);
        boolean b = true;
        if (bc == null) {
            return false;
        }
        if (bc.equals("")) {
            return false;
        }
        final char[] charArray = bc.toCharArray();
        for (int i = 0; i < bc.length(); ++i) {
            if (!Character.isDigit(charArray[i]) && charArray[i] != '-') {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public final void print(final Graphics graphics) {
        try {
            if (this.d != null) {
                this.d.print(graphics);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void al() {
        ji.util.d.ef();
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "jiStop1 ");
        }
        try {
            this.shutdownQuickstart();
            this.sh = true;
            if (this.rp != null) {
                this.rp.a(false);
            }
            if (this.r0 != null) {
                this.r0.a(false);
            }
            ++jiApplet.rs;
            this.se = true;
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiStop1a ");
            }
            if (this.uo != null) {
                this.uo.a(true);
            }
            if (this.d != null) {
                this.d.bk(true);
                this.d.d8(false);
                this.d.a(false, -1);
                if (!b(this.uz)) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStop1b1 ");
                    }
                    this.d.ar(0);
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStop1b2 ");
                    }
                    this.j();
                }
                else {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiStop1c ");
                    }
                    this.d.a0();
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiStop1d ");
                }
            }
        }
        catch (Exception ex) {
            this.sh = false;
        }
        finally {
            if (this.d != null) {
                this.d.bk(false);
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiStop2 ");
            }
            --jiApplet.rs;
        }
    }
    
    private void g(final boolean b) {
        try {
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("jiDestroy start ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
            boolean b2 = false;
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy1a");
            }
            int n = 0;
            if ((ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) && jiApplet.t9 && n < 15000 && b(this.uz)) {
                ji.io.h.d(this.uz, "jiDestroy1aA....!.....");
            }
            while (jiApplet.t9 && n < 15000 && b(this.uz)) {
                ji.util.d.b(25, 3, this.uz);
                b2 = true;
                n += 25;
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy1a2");
            }
            if (n >= 15000) {
                ji.io.h.d(this.uz, "Time-out while waiting for startup to complete: ".concat(String.valueOf(String.valueOf(n))));
            }
            else if (b2) {
                ji.util.d.b(100, 4, this.uz);
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy1a23");
            }
            ji.util.d.ef();
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy1b");
            }
            try {
                this.sd = true;
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy2");
                }
                if (!this.ry) {
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiDestroy3");
                    }
                    if (b(this.uz)) {
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "jiDestroy4");
                        }
                        this.d.ar(0);
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "jiDestroy5");
                        }
                        this.j();
                        this.d.a2();
                        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                            ji.io.h.d(this.uz, "jiDestroy6");
                        }
                    }
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiDestroy7");
                    }
                    ji.util.d.ef();
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiDestroy8");
                    }
                    this.an();
                    if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(this.uz, "jiDestroy9");
                    }
                    this.ry = true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy9a");
                }
                try {
                    if (this.t4 != null) {
                        this.t4.c();
                        this.t4 = null;
                    }
                }
                catch (Exception ex2) {}
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy9b");
                }
                try {
                    if (this.t6 != null) {
                        this.t6.c();
                        this.t6 = null;
                    }
                }
                catch (Exception ex3) {}
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy9c");
                }
                try {
                    if (jiApplet.uf != null) {
                        jiApplet.uf.c();
                        jiApplet.uf = null;
                    }
                }
                catch (Exception ex4) {}
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy9d");
                }
                this.h(b);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "jiDestroy10");
                }
                if (!ji.util.i.c(69)) {
                    try {
                        ji.io.ac.a(null, this.uz, false, (Object)null);
                    }
                    catch (Exception ex5) {}
                }
                jiApplet.ua = false;
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy11");
            }
            ji.util.d.ef();
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy12");
            }
            ji.util.d.ew();
            if (!this.iy) {
                ji.util.d.ex();
            }
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy13");
            }
            this.clearEventInterest();
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "jiDestroy14");
            }
        }
        finally {
            this.sh = false;
            if (ji.util.i.c(131)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("jiDestroy end ").append(Thread.currentThread().getName()).append(" ").append(this.uz))));
            }
        }
    }
    
    private final void am() {
        if (this.t3 != null && !ji.util.d.dq()) {
            if (this.tw != null) {
                if (ji.util.d.cy() || ji.util.i.c(131)) {
                    ji.io.h.d(this.uz, "Applet command processor waiting for commands");
                }
                this.tw.a();
                if (this.t3.size() > 0) {
                    while (this.t3.size() > 0) {
                        ji.util.d.b(10, 0, this.uz);
                    }
                }
            }
            while (this.tz) {
                ji.util.d.b(10, 0, this.uz);
            }
        }
    }
    
    private final void h(final boolean b) {
        try {
            this.r2 = false;
            if (ji.util.d.cy() || ji.util.i.c(131)) {
                ji.io.h.d(this.uz, "Applet command processor releasing commands: ".concat(String.valueOf(String.valueOf(b))));
            }
            if (this.tw != null) {
                this.tw.a();
            }
            this.v();
            this.w();
            if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                ji.io.h.d(this.uz, "set commandLock to null in releaseCommands");
            }
            this.rp = null;
            this.r0 = null;
            if (this.tv != null) {
                try {
                    if (b) {
                        ji.util.d.a(this.tv, this.uz);
                    }
                }
                catch (InterruptedException ex) {}
            }
        }
        catch (Exception ex2) {}
        ji.v1event.a2.a(this.uz);
    }
    
    public void resize(int width, int height) {
        if (width == 0 && height == 0 && this.c != null && ji.util.d.ak(this.uz) && this.c.getSize().width == 0 && this.c.getSize().height == 0) {
            this.c.validate();
            int n = 0;
            while (this.c.getSize().width == 0 && this.c.getSize().height == 0 && n++ < 10) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            width = this.c.getSize().width;
            height = this.c.getSize().height;
        }
        super.resize(width, height);
    }
    
    private void an() {
        ji.util.d.ef();
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "A-DestroyImage1");
        }
        this.rx = false;
        this.r5 = false;
        this.r7 = false;
        try {
            if (this.d != null) {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage2");
                }
                this.d.b(this);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage3-1");
                }
                try {
                    this.d.c5();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage3-2");
                }
                try {
                    this.d.az();
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage4");
                }
                this.remove(this.d);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage4a");
                }
                ji.util.d.a((Container)this, this.uz);
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage5");
                }
                ji.util.d.ef();
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage7");
                }
                if (this.t4 != null) {
                    this.t4.c();
                    this.t4 = null;
                }
                if (this.t6 != null) {
                    this.t6.c();
                    this.t6 = null;
                }
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(this.uz, "A-DestroyImage8");
                }
                this.d = null;
            }
        }
        catch (Exception ex3) {}
        finally {
            ji.util.d.a((Object)null, this.uz);
            ji.util.d.ef();
            this.r8 = false;
        }
        if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
            ji.io.h.d(this.uz, "A-DestroyImage9");
        }
    }
    
    public static void main(final String[] array) {
        final String concat = "-ap".concat(String.valueOf(String.valueOf(jiApplet.uh++)));
        d.a(new Integer(0), concat);
        d.al(concat);
        d.b1();
        if (jiApplet.ub) {
            d.bv(true);
            h.e(concat, "Start1");
        }
        try {
            String substring = null;
            String substring2 = null;
            Object substring3 = null;
            String substring4 = null;
            if (array != null && array.length > 0) {
                for (int i = 1; i < array.length; ++i) {
                    final String s = array[i];
                    if (s.startsWith("debug=")) {
                        jiApplet.ug = d.c(s.substring(6), 0);
                        break;
                    }
                }
            }
            if (array != null && array.length > 0) {
                final String lowerCase = array[0].toLowerCase();
                if (lowerCase.startsWith("res=")) {
                    substring4 = lowerCase.substring(4);
                }
                if (array.length > 2) {
                    final String lowerCase2 = array[1].toLowerCase();
                    final String lowerCase3 = array[2].toLowerCase();
                    if (lowerCase2.startsWith("listfile=")) {
                        substring = lowerCase2.substring(9);
                    }
                    if (array.length > 3) {
                        final String lowerCase4 = array[3].toLowerCase();
                        if (lowerCase4.startsWith("logfile=")) {
                            substring2 = lowerCase4.substring(8);
                        }
                    }
                    if (lowerCase3.startsWith("rootdir=")) {
                        substring3 = lowerCase3.substring(8);
                        h.d(concat, " ");
                        h.d(concat, " - viewONE image search and test -");
                        h.d(concat, " ");
                        h.d(concat, "Using resource directory : ".concat(String.valueOf(String.valueOf(substring4))));
                        h.d(concat, "Using listfile : ".concat(String.valueOf(String.valueOf(substring))));
                        h.d(concat, "Using rootdir : ".concat(String.valueOf(String.valueOf(substring3))));
                        if (substring2 != null) {
                            h.d(concat, "Using logfile : ".concat(String.valueOf(String.valueOf(substring2))));
                        }
                    }
                }
            }
            final String s2 = null;
            if (jiApplet.ug == 1) {
                i.a(206, true);
                i.a(237, true);
            }
            else if (jiApplet.ug == 2) {
                i.a(7, true);
                i.a(59);
                i.a(115);
            }
            else if (jiApplet.ug == 4) {
                i.a(237, true);
                i.a(6, "pro");
                i.a(7, true);
            }
            if (jiApplet.ug == 1) {}
            ad ad = new ad(false, null, null, substring4, null, s2, concat, false);
            ad.bp(true);
            if (jiApplet.ug == 1) {}
            ad.bo(true);
            if (substring3 != null) {
                h.d(concat, " ");
                h.d(concat, "Starting...");
                ac ac = null;
                if (substring2 != null) {
                    try {
                        ji.io.ac.c(substring2, concat);
                    }
                    catch (Exception ex4) {}
                    try {
                        ac = new ac(substring2, true, false, 0, false, ad, concat);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        if (d.c) {
                            h.d(concat, "System Exit 5");
                        }
                        else {
                            System.exit(0);
                        }
                    }
                }
                FileOutputStream fileOutputStream = null;
                if (substring != null) {
                    try {
                        fileOutputStream = new FileOutputStream(substring, true);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                        if (d.c) {
                            h.d(concat, "System Exit 7");
                        }
                        else {
                            System.exit(0);
                        }
                    }
                }
                int n = 0;
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                long n5 = System.currentTimeMillis();
                long n6 = 0L;
                long n7 = 0L;
                int n8 = 0;
                if (!ad.a((b)null)) {
                    h.d(concat, "Problem starting.");
                }
                else {
                    h.d(concat, "Started OK...");
                    h.d(concat, " ");
                    if (ac != null) {
                        h.d(concat, "Working...");
                        a(concat, ac, "Searching...");
                    }
                    else {
                        h.d(concat, "Searching...");
                    }
                    a(concat, ac, " ");
                    final c c = new c("jiApplet3");
                    final c c2 = new c("jiApplet4");
                    c.c(substring3);
                    final String bu = d.bu(concat);
                    n2 = 1;
                    final int n9 = 10000;
                    ji.document.ad.dt(false);
                    n5 = System.currentTimeMillis();
                    while (c.b() > 0) {
                        a(concat, ac, " ");
                        int n10 = 0;
                        int n11 = 0;
                        final String s3 = (String)c.b(0);
                        c.d(0);
                        final String[] h = ji.io.ac.h(s3, concat);
                        if (h != null) {
                            for (int j = 0; j < h.length; ++j) {
                                if (ji.io.ac.f(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bu).append(h[j]))), concat)) {
                                    ++n2;
                                    ++n10;
                                    c.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bu).append(h[j]))));
                                }
                                else {
                                    ++n;
                                    ++n8;
                                    ++n11;
                                    c2.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(bu).append(h[j]))));
                                }
                            }
                            a(concat, ac, "---Directory--- ");
                            a(concat, ac, " ");
                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(n10).append(" subdirectories"))));
                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(n11).append(" files"))));
                            if (ac != null) {
                                ji.io.h.d(concat, " ");
                                ji.io.h.d(concat, "---Directory--- ");
                                ji.io.h.d(concat, " ");
                                ji.io.h.d(concat, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(n10).append(" subdirectories"))));
                                ji.io.h.d(concat, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(n11).append(" files"))));
                            }
                            a(concat, ac, " ");
                            int n12 = 0;
                            int n13 = 0;
                            while (c2.b() > 0) {
                                final String s4 = (String)c2.b(0);
                                c2.d(0);
                                if (!d.by(s4)) {
                                    final long currentTimeMillis = System.currentTimeMillis();
                                    ad.h(s4, 1);
                                    int n14 = 0;
                                    try {
                                        while (!ad.gv()) {
                                            Thread.sleep(100L);
                                            n14 += 100;
                                            if (n14 > n9) {
                                                break;
                                            }
                                        }
                                    }
                                    catch (Exception ex5) {}
                                    boolean g2;
                                    String de;
                                    if (n14 > n9) {
                                        g2 = false;
                                        de = "failed to load";
                                    }
                                    else {
                                        g2 = ad.g2();
                                        de = ji.document.ad.de();
                                    }
                                    if (g2) {
                                        n6 += System.currentTimeMillis() - currentTimeMillis;
                                        if (ad.j8() > 1) {
                                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": ").append(s4).append(" (").append(ad.j8()).append(" pages)"))));
                                        }
                                        else {
                                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": ").append(s4))));
                                        }
                                        if (ad.j8() > 1) {
                                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": OK Page 1"))));
                                            for (int k = 1; k < ad.j8(); ++k) {
                                                ++n8;
                                                final long currentTimeMillis2 = System.currentTimeMillis();
                                                ad.ba(k + 1);
                                                int n15 = 0;
                                                while (!ad.g1()) {
                                                    Thread.sleep(100L);
                                                    n15 += 100;
                                                    if (n15 > n9) {
                                                        break;
                                                    }
                                                }
                                                boolean g3;
                                                String s5;
                                                if (n15 > n9) {
                                                    g3 = false;
                                                    s5 = String.valueOf(String.valueOf(new StringBuffer("page ").append(k + 1).append(" failed to load")));
                                                }
                                                else {
                                                    g3 = ad.g2();
                                                    s5 = ji.document.ad.de();
                                                }
                                                if (!g3) {
                                                    n7 += System.currentTimeMillis() - currentTimeMillis2;
                                                    ++n3;
                                                    ++n4;
                                                    a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": FAILED PAGE + ").append(k + 1).append(" (").append(s5).append(")"))));
                                                    fileOutputStream.write(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n3))).append(": <PAGE ").append(k + 1).append(" ").append(s4).append("> (").append(s5).append(")\r\n"))).getBytes());
                                                    break;
                                                }
                                                n6 += System.currentTimeMillis() - currentTimeMillis2;
                                                a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": OK Page ").append(k + 1))));
                                            }
                                        }
                                        else {
                                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": OK"))));
                                        }
                                    }
                                    else {
                                        a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": ").append(s4))));
                                        n7 += System.currentTimeMillis() - currentTimeMillis;
                                        ++n3;
                                        ++n4;
                                        a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12 + 1))).append(": FAILED (").append(de).append(")"))));
                                        fileOutputStream.write(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n3))).append(": <").append(s4).append("> (").append(de).append(")\r\n"))).getBytes());
                                    }
                                    ++n12;
                                    if (++n13 > 9) {
                                        n13 = 0;
                                        ji.io.h.d(concat, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(n12))).append(" of ").append(n11).append(" processed (").append(n3).append(" failed so far)"))));
                                    }
                                    ad.c3();
                                }
                            }
                            a(concat, ac, " ");
                        }
                        else {
                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(0).append(" subdirectories"))));
                            a(concat, ac, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" : ").append(0).append(" files"))));
                        }
                    }
                }
                a(concat, ac, " ");
                final long n16 = (System.currentTimeMillis() - n5) / 1000;
                a(concat, ac, "Complete.");
                a(concat, ac, " ");
                a(concat, ac, "Total directories processed : ".concat(String.valueOf(String.valueOf(n2))));
                a(concat, ac, "Total files processed: ".concat(String.valueOf(String.valueOf(n))));
                a(concat, ac, "Total files OK: ".concat(String.valueOf(String.valueOf(n - n3))));
                a(concat, ac, "Total images processed: ".concat(String.valueOf(String.valueOf(n8))));
                a(concat, ac, "Total images OK: ".concat(String.valueOf(String.valueOf(n8 - n4))));
                a(concat, ac, "Total files failed: ".concat(String.valueOf(String.valueOf(n3))));
                a(concat, ac, "Total images failed: ".concat(String.valueOf(String.valueOf(n4))));
                a(concat, ac, String.valueOf(String.valueOf(new StringBuffer("Total time taken : ").append(n16).append(" seconds"))));
                a(concat, ac, String.valueOf(String.valueOf(new StringBuffer("Total time processing OK images: ").append(n6 / 1000).append(" seconds"))));
                a(concat, ac, String.valueOf(String.valueOf(new StringBuffer("Total time processing failed images: ").append(n7 / 1000).append(" seconds"))));
                if (n8 - n4 > 0) {
                    a(concat, ac, String.valueOf(String.valueOf(new StringBuffer("Average time per OK image: ").append(n6 / (n8 - n4) / 1000.0).append(" seconds"))));
                }
                if (n4 > 0) {
                    a(concat, ac, String.valueOf(String.valueOf(new StringBuffer("Average time per failed image: ").append(n6 / n4 / 1000.0).append(" seconds"))));
                }
                if (ac != null) {
                    h.d(concat, "Complete.");
                    h.d(concat, " ");
                    h.d(concat, "Total directories processed : ".concat(String.valueOf(String.valueOf(n2))));
                    h.d(concat, "Total files processed: ".concat(String.valueOf(String.valueOf(n))));
                    h.d(concat, "Total files OK: ".concat(String.valueOf(String.valueOf(n - n3))));
                    h.d(concat, "Total images processed: ".concat(String.valueOf(String.valueOf(n8))));
                    h.d(concat, "Total images OK: ".concat(String.valueOf(String.valueOf(n8 - n4))));
                    h.d(concat, "Total files failed: ".concat(String.valueOf(String.valueOf(n3))));
                    h.d(concat, "Total images failed: ".concat(String.valueOf(String.valueOf(n4))));
                    h.d(concat, String.valueOf(String.valueOf(new StringBuffer("Total time taken : ").append(n16).append(" seconds"))));
                    h.d(concat, String.valueOf(String.valueOf(new StringBuffer("Total time processing OK images: ").append(n6 / 1000).append(" seconds"))));
                    h.d(concat, String.valueOf(String.valueOf(new StringBuffer("Total time processing failed images: ").append(n7 / 1000).append(" seconds"))));
                    if (n8 - n4 > 0) {
                        h.d(concat, String.valueOf(String.valueOf(new StringBuffer("Average time per OK image: ").append(n6 / (n - n4) / 1000.0).append(" seconds"))));
                    }
                    if (n4 > 0) {
                        h.d(concat, String.valueOf(String.valueOf(new StringBuffer("Average time per failed image: ").append(n6 / n4 / 1000.0).append(" seconds"))));
                    }
                }
                h.d(concat, " ");
                try {
                    if (ac != null) {
                        ac.a(ad);
                    }
                }
                catch (Exception ex6) {}
                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                }
                catch (Exception ex7) {}
                try {
                    ad.az();
                    ad.releaseResources();
                    ad = null;
                }
                catch (Exception ex8) {}
                if (d.c) {
                    h.d(concat, "System Exit 8");
                }
                else {
                    System.exit(0);
                }
            }
            final String a = ay.a();
            final gw gw = new gw(a, ad, concat);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            gw.a().setLayout(new BorderLayout());
            ad.a(true, true);
            Rectangle c3 = new p(concat).c(ad);
            if (c3 == null) {
                final int n17 = screenSize.width / 2;
                final int n18 = screenSize.height / 2;
                c3 = new Rectangle((screenSize.width - n17) / 2, -20 + (screenSize.height - n18) / 2, n17, n18);
            }
            gw.setBounds(c3.x, c3.y, c3.width, c3.height);
            gw.a().add(ad, "Center");
            ad.at(true);
            ad.au(true);
            ad.ap(true);
            ad.ar(true);
            d.bf(true);
            i.a(2);
            ad.a((b)null);
            ad.eh();
            gw.setVisible(true);
            if (ad.at()) {
                if (ad.as()) {
                    gw.setTitle(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append(" - ").append(d.b(81, concat)))));
                }
                if (array != null && array.length > 0) {
                    if (array[0].startsWith("res=")) {
                        for (int l = 1; l < array.length; ++l) {
                            final String s6 = array[l];
                            if (s6.startsWith("file=")) {
                                ad.h(s6.substring(5), 1);
                                break;
                            }
                        }
                    }
                    else {
                        ad.h(array[0], 1);
                    }
                }
                if (jiApplet.ug == 1) {
                    final String[] array2 = { "image:v1t.bmp", "image:v1.bmp", "image:v1tmono.bmp", "image:manualp1.jpg", "some text1<n>line2", "image:http://henry/LeslieDemo/docs/filenet/signature1.tif", "image:http://henry/LeslieDemo/docs/filenet/sig.tif", "image:http://henry.mk1.daeja.com/reuben/docs/mixed/signature.tif", "image:http://henry.mk1.daeja.com/reuben/docs/mixed/fmoeller.tif" };
                    final String[] array3 = { "<Watermark - 4 bit trans><white>", "<ViewONE Watermark><white>", "<ViewONE Watermark - 1 bit trans><white>" };
                    ad.aw("test");
                    d.i(true);
                    i.a(235, true);
                    i.a(234, true);
                    d.i7 = true;
                    ad.a7(1);
                    i.a(247, true);
                    ad.h("D:/images/big.tif", 2);
                    if (ad.jf() == null) {
                        i.a(2);
                        ad.ao();
                        ad.al();
                    }
                }
                else if (jiApplet.ug == 2) {
                    i.a(137, true);
                    ad.a(true, true);
                    ad.e("redact {burnable=true}");
                    ad.h("http://localhost:8080/burner/burner?file=pdf.pdf", 1);
                }
                else if (jiApplet.ug == 4) {
                    ad.j(true);
                    i.a(188, true);
                    ad.e(24, true);
                    i.a(79, true);
                    ad.a(new URL("http://henry/david/Java/doctypes/docs/spiro2.ps"), 1);
                    if (ad.jf() == null) {
                        i.a(2);
                        ad.ao();
                        ad.al();
                    }
                }
                else {
                    i.a(2);
                    ad.ao();
                    ad.al();
                }
            }
            else {
                gw.setVisible(false);
                try {
                    gw.removeNotify();
                    gw.dispose();
                }
                catch (Exception ex9) {}
                h.d(concat, "Start error: ".concat(String.valueOf(String.valueOf(ad.aw()))));
                if (d.c) {
                    h.d(concat, "System Exit 9");
                }
                else {
                    System.exit(0);
                }
            }
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    private final void w(final String s) {
        try {
            final String value = String.valueOf(String.valueOf(new StringBuffer("You do not have sufficient privileges to call the ").append(s).append("() Javascript method.")));
            ji.io.h.d(this.uz, value);
            ji.util.d.a(value, this.d, this.uz);
        }
        catch (Exception ex) {}
    }
    
    public void setPrintAutoRotate(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintAutoRotate(").append(b).append(")"))));
        }
        if (this.d != null) {
            this.d.co(b);
        }
    }
    
    public void setPrintJobName(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintJobName(").append(s).append(")"))));
        }
        if (this.d != null) {
            this.d.s(s);
        }
    }
    
    public String getAppletInfo() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getAppletInfo()"));
        }
        return this.getVersion();
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void waitForAnnotationSave() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: waitForAnnotationSave()"));
        }
        if (this.ap()) {
            this.d.da();
        }
    }
    
    public void closeDocument() {
        this.i(true);
    }
    
    private void i(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: closeDocument()"));
        }
        if (this.ap()) {
            this.qw = null;
            this.q0 = null;
            if (b) {
                this.d.c3();
            }
            else {
                this.d.c6();
            }
            this.d.da();
        }
    }
    
    public void setSize(final int n, final int n2) {
        ji.util.d.me = System.currentTimeMillis();
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setSize(").append(n).append(", ").append(n2).append(")"))));
        }
        super.setSize(n, n2);
        this.validate();
    }
    
    public void setInnerBounds(final int n, final int n2, final int n3, final int n4) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: setInnerBounds()"));
        }
        this.qv = null;
        this.qv = new Rectangle(n, n2, n3, n4);
        this.ao();
    }
    
    public final int getImageWidth() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getImageWidth()"));
        }
        try {
            return this.d.j9();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int getImageHeight() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getImageHeight()"));
        }
        try {
            return this.d.ka();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int getXResolution() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getXResolution()"));
        }
        try {
            return this.d.kb();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int getYResolution() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getYResolution()"));
        }
        try {
            return this.d.kc();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public int getWidth() {
        int width = 0;
        try {
            width = this.getSize().width;
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        return width;
    }
    
    public int getInnerWidth() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getInnerWidth()"));
        }
        try {
            if (this.qv != null) {
                return this.qv.width;
            }
            return this.getSize().width;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public int getHeight() {
        int height = 0;
        try {
            height = this.getSize().height;
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        return height;
    }
    
    public Dimension getSize() {
        Dimension dimension = super.getSize();
        if ((dimension == null || (dimension.width == 0 && dimension.height == 0)) && ji.util.d.ak(this.uz) && this.c != null) {
            dimension = this.c.getSize();
        }
        return dimension;
    }
    
    public int getInnerHeight() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getInnerHeight()"));
        }
        try {
            if (this.qv != null) {
                return this.qv.height;
            }
            return this.getSize().height;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public int getInnerX() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getInnerX()"));
        }
        try {
            if (this.qv != null) {
                return this.qv.x;
            }
            return 0;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public int getInnerY() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getInnerY()"));
        }
        try {
            if (this.qv != null) {
                return this.qv.y;
            }
            return 0;
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final int getLoadCount() {
        return this.rz;
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        ji.util.d.me = System.currentTimeMillis();
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBounds(").append(n).append(", ").append(n2).append(", ").append(n3).append(", ").append(n4).append(")"))));
        }
        if (ji.util.d.ao(this.uz)) {
            final Rectangle u5 = new Rectangle(n, n2, n3, n4);
            if (!u5.equals(this.u5)) {
                this.u5 = u5;
                super.setBounds(n, n2, n3, n4);
                if (!b(this.uz)) {
                    this.ao();
                }
            }
        }
        else {
            super.setBounds(n, n2, n3, n4);
            if (!b(this.uz)) {
                this.ao();
            }
        }
    }
    
    public final void setBounds(final Rectangle bounds) {
        ji.util.d.me = System.currentTimeMillis();
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBounds(").append(bounds).append(")"))));
        }
        super.setBounds(bounds);
    }
    
    private void ao() {
        if (ji.util.e.p(this.uz)) {
            try {
                if (this.r4) {
                    if (this.d != null) {
                        this.d.validate();
                    }
                }
                else if (!this.r4) {
                    if (this.qi > 0 && this.qi > 0) {
                        this.f(false);
                    }
                    if (this.qv == null) {
                        if (this.d != null) {
                            final Rectangle bounds = this.getBounds();
                            this.d.setBounds(0, 0, bounds.width, bounds.height);
                        }
                    }
                    else if (this.d != null) {
                        Rectangle bounds2 = this.d.getBounds();
                        if (bounds2 == null) {
                            bounds2 = new Rectangle(0, 0, 0, 0);
                        }
                        if (this.qv.width == 0 && this.qv.height == 0) {
                            this.d.setBounds(0, 0, bounds2.width, bounds2.height);
                        }
                        else if (bounds2.width != this.qv.width || bounds2.height != this.qv.height || bounds2.x != this.qv.x || bounds2.y != this.qv.y) {
                            this.d.setBounds(this.qv);
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean isEventHandlerOK() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isEventHandlerOK()"));
        }
        this.nc = null;
        if (!this.ai()) {
            this.nc = "No Mayscript tag";
            return false;
        }
        if (this.fh == null) {
            this.nc = "No EventHandler tag";
            return false;
        }
        if (!b(this.uz) && !ji.util.d.bo(this.uz)) {
            this.nc = "Not Netscape or Internet Explorer";
            return false;
        }
        this.a(String.valueOf(String.valueOf(this.fh)).concat("(0, 'test')"), -1);
        if (this.nc != null) {
            this.nc = String.valueOf(String.valueOf(new StringBuffer("function ").append(this.fh).append("(id, text) not found")));
            return false;
        }
        return true;
    }
    
    public String getEventHandlerError() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getEventHandlerError()"));
        }
        return this.nc;
    }
    
    public boolean isReady() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isReady()"));
        }
        if (this.rx) {
            if (this.d.dd() != null) {
                final boolean gv = this.d.gv();
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, ">JS: doc loaded? ".concat(String.valueOf(String.valueOf(gv))));
                }
                return gv;
            }
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, ">JS: orginaldoc is null");
            }
        }
        return this.rx;
    }
    
    public void setDescription(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDescription(").append(s).append(")"))));
        }
        if (this.ap() && s != null) {
            this.d.ag(s);
        }
    }
    
    public String getDescription() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getDescription()"));
        }
        if (this.ap()) {
            try {
                if (this.d != null) {
                    return this.d.df();
                }
            }
            catch (Exception ex) {}
        }
        return null;
    }
    
    public void save(final String s) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: save(").append(s).append(")"))));
            }
            if (this.ap()) {
                this.d.z(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void savePage(final String s) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: savePage(").append(s).append(")"))));
            }
            if (this.ap()) {
                this.d.aa(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void saveSelected(final String s) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: saveSelected(").append(s).append(")"))));
            }
            if (this.ap()) {
                this.d.ae(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isSaveComplete() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isSaveComplete()"));
        }
        return !this.ap() || this.d.cy();
    }
    
    public void setDefaultSaveFilename(final String s) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDefaultSaveFilename(").append(s).append(")"))));
            }
            if (this.ap()) {
                this.d.as(s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isMultipageTif() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isMultipageTif()"));
        }
        return !this.ap() || this.d.dw();
    }
    
    public boolean isMultipageSingleFile() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isMultipageSingleFile()"));
        }
        return !this.ap() || this.d.dx();
    }
    
    public void openDirectory(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openDirectory(").append(s).append(")"))));
        }
        if (this.ap()) {
            if (this.d != null) {
                final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                if (b != null) {
                    if (b instanceof String) {
                        this.d.l((String)b, 1);
                    }
                    else if (ji.util.i.c(6)) {
                        ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: invalid path specified"));
                    }
                }
                else if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: invalid path specified"));
                }
            }
            else if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: image not ready"));
            }
        }
        else if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: not ready"));
        }
    }
    
    public void setRedactionIsSemiTransparent(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRedactionIsSemiTransparent(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bj()) {
                this.d.bh(b);
            }
            else {
                this.w("setRedactionIsSemiTransparent");
            }
        }
    }
    
    public void copyPageToClipboard() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: copyPageToClipboard()"));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.cd();
            }
            else {
                this.w("copyPageToClipboard");
            }
        }
    }
    
    public void setBackgroundImageEnabled(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBackgroundImageEnabled(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ei(b);
        }
    }
    
    public boolean isBackgroundImageEnabled() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isBackgroundImageEnabled()"));
        }
        return !this.ap() || this.d.h2();
    }
    
    public void setBackgroundImageMode(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBackgroundImage(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.aj(n);
        }
    }
    
    public void setBackgroundImage(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBackgroundImage(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
            if (b != null) {
                if (b instanceof String) {
                    this.d.a((Object)s, n);
                }
                else {
                    this.d.a(b, n);
                }
            }
        }
    }
    
    public void initializeBackgroundImageArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeBackgroundImageArray(").append(n).append(")"))));
        }
        try {
            if (this.ap()) {
                this.ao = new String[n];
                this.an = null;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void setBackgroundImageArrayItem(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBackgroundImageArrayItem(").append(s).append(", ").append(n).append(")"))));
        }
        try {
            if (this.ap()) {
                this.ao[n] = s;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void useBackgroundImageArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: useBackgroundImageArray(").append(n).append(")"))));
        }
        try {
            if (this.ap() && this.ao != null) {
                final Object[] array = new Object[this.ao.length];
                try {
                    for (int i = 0; i < this.ao.length; ++i) {
                        array[i] = ji.util.d.b(this.af(), this.ao[i], this.uz);
                    }
                }
                catch (Exception ex2) {}
                this.d.b(array, n);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public void setOverrideImageForeColor(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setOverrideImageForeColor(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.g(ji.util.d.b1(s));
        }
    }
    
    public void initializeOverrideForeColorArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeOverrideForeColorArray(").append(n).append(")"))));
        }
        try {
            if (this.ap()) {
                this.jj = new String[n];
                this.ji = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void setOverrideForeColorArrayItem(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setOverrideForeColorArrayItem(").append(s).append(", ").append(n).append(")"))));
        }
        try {
            if (this.ap()) {
                this.jj[n] = s;
            }
        }
        catch (Exception ex) {}
    }
    
    public void useOverrideForeColorArray() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: useOverrideForeColorArray()"));
        }
        try {
            if (this.ap() && this.jj != null) {
                final Color[] array = new Color[this.jj.length];
                try {
                    for (int i = 0; i < this.jj.length; ++i) {
                        array[i] = ji.util.d.b1(this.jj[i]);
                    }
                }
                catch (Exception ex) {}
                this.d.a(array);
            }
        }
        catch (Exception ex2) {}
    }
    
    public final void showImageBackColorDialog() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: showImageBackColorDialog()"));
        }
        if (this.ap()) {
            this.d.hy();
        }
    }
    
    public final void showImageForeColorDialog() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: showImageForeColorDialog()"));
        }
        if (this.ap()) {
            this.d.hz();
        }
    }
    
    public void setStickyAnnotations(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setStickyAnnotations(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.bg(b);
        }
    }
    
    public void openFile(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openFileName(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
            if (b != null) {
                if (this.d.gm()) {
                    this.d.dg(false);
                }
                try {
                    if (b instanceof String) {
                        this.d.h((String)b, n);
                    }
                    else {
                        this.d.a((URL)b, n);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void openByteArray(final byte[] array, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openByteStream(<data>, ").append(n).append(")"))));
        }
        if (this.ap() && array != null) {
            this.d.a(array, n);
        }
    }
    
    public void openList(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openList(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
            if (b != null) {
                if (b instanceof String) {
                    this.d.j((String)b, n);
                }
                else {
                    this.d.c((URL)b, n);
                }
            }
        }
    }
    
    public void reloadList() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: reloadList()"));
        }
        if (this.ap()) {
            this.d.dh();
        }
    }
    
    public void initializeDocumentArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeDocumentArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.i(false);
            this.bc = new String[n];
            this.d.h((Object)null);
            ji.util.e.b6 = null;
            ji.util.e.b7 = null;
        }
    }
    
    public void setDocumentArray(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDocumentArray(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.bc[n] = s;
            }
            catch (Exception ex) {}
        }
    }
    
    public void openDocumentArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openDocumentArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            ji.util.e.b7 = this.bc;
            try {
                if (this.bc != null) {
                    if (!ji.util.d.by(this.bc[0])) {
                        this.bl = -1;
                        if (ji.util.i.c(115)) {
                            if (this.ah != null) {
                                this.d.a(this.ah, true);
                            }
                            else {
                                this.d.a(this.bc, false);
                            }
                        }
                        this.openDoc(Math.max(Math.min(n - 1, this.bc.length - 1), 0));
                    }
                    else {
                        ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: Error - openDocumentArray(), document 0 not defined."));
                    }
                }
                else {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: Error - openDocumentArray(), documents not defined."));
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void initializePageArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializePageArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.k8();
            this.i(false);
            this.qw = new Object[n];
            this.q0 = null;
        }
    }
    
    public void initializePageAndThumbsArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializePageAndThumbsArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.i(false);
            this.qw = new Object[n];
            this.q0 = new Object[n];
        }
    }
    
    public void initializeLabels(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeLabels(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.q1 = new String[n];
            this.q3 = new String[n];
            this.q2 = new Color[n];
            this.q4 = new Color[n];
        }
    }
    
    public void initializeSeparatorArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeSeparatorArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.qx = new URL[n];
        }
    }
    
    public final void setImageSaveURL(final String s, final int n, final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageSaveURL(").append(s).append(",").append(n).append(",").append(b).append(")"))));
        }
        if (this.ap() && this.d != null) {
            this.d.a(s, n, b);
        }
    }
    
    public void clearLabels() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearLabels()"));
        }
        try {
            this.q1 = null;
            this.q3 = null;
            if (this.q2 != null) {
                for (int i = 0; i < this.q2.length; ++i) {
                    this.q2[i] = null;
                }
            }
            if (this.q4 != null) {
                for (int j = 0; j < this.q4.length; ++j) {
                    this.q4[j] = null;
                }
            }
            this.q2 = null;
            this.q4 = null;
        }
        catch (Exception ex) {}
        try {
            if (this.d != null) {
                this.d.a(null, null, null, (Color[])null);
            }
        }
        catch (Exception ex2) {}
    }
    
    public void setLabel(final String s, final String s2, final String s3, final String s4, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setLabel(").append(s).append(", ").append(s2).append(", ").append(s3).append(", ").append(s4).append(", ").append(n).append(")"))));
        }
        try {
            if (s != null && !ji.util.d.by(s)) {
                this.q1[n] = s;
            }
            if (s3 != null && !ji.util.d.by(s3)) {
                this.q3[n] = s3;
            }
            if (s2 != null) {
                this.q2[n] = ji.util.d.b1(s2);
            }
            else {
                this.q2[n] = null;
            }
            if (s4 != null) {
                this.q4[n] = ji.util.d.b1(s4);
            }
            else {
                this.q4[n] = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void useLabels() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: useLabels()"));
        }
        try {
            boolean b = false;
            boolean b2 = false;
            if (this.q1 != null) {
                for (int i = 0; i < this.q1.length; ++i) {
                    if (!ji.util.d.by(this.q1[i])) {
                        b = true;
                        break;
                    }
                }
            }
            if (this.q3 != null) {
                for (int j = 0; j < this.q3.length; ++j) {
                    if (!ji.util.d.by(this.q3[j])) {
                        b2 = true;
                        break;
                    }
                }
            }
            String[] q1 = null;
            String[] q2 = null;
            Color[] q3 = null;
            Color[] q4 = null;
            if (b) {
                q1 = this.q1;
                q3 = this.q2;
            }
            if (b2) {
                q2 = this.q3;
                q4 = this.q4;
            }
            this.d.a(q1, q3, q2, q4);
        }
        catch (Exception ex) {}
    }
    
    public void clearDocLabels() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearDocLabels()"));
        }
        try {
            this.q5 = null;
            if (this.q6 != null) {
                for (int i = 0; i < this.q6.length; ++i) {
                    this.q6[i] = null;
                }
            }
            this.q6 = null;
        }
        catch (Exception ex) {}
        try {
            if (this.d != null) {
                this.d.a(null, (Color[])null);
            }
        }
        catch (Exception ex2) {}
    }
    
    public void initializeDocLabels(final int n) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeDocLabels(").append(n).append(")"))));
            }
            if (this.ap()) {
                this.q5 = new String[n];
                this.q6 = new Color[n];
            }
        }
        catch (Exception ex) {}
    }
    
    public void setDocLabel(final String s, final String s2, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDocLabel(").append(s).append(", ").append(s2).append(", ").append(n).append(")"))));
        }
        try {
            if (s != null && !ji.util.d.by(s)) {
                this.q5[n] = s;
            }
            if (s2 != null) {
                this.q6[n] = ji.util.d.b1(s2);
            }
            else {
                this.q6[n] = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void useDocLabels() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: useDocLabels()"));
        }
        try {
            boolean b = false;
            if (this.q5 != null) {
                for (int i = 0; i < this.q5.length; ++i) {
                    if (!ji.util.d.by(this.q5[i])) {
                        b = true;
                        break;
                    }
                }
            }
            String[] q5 = null;
            Color[] q6 = null;
            if (b) {
                q5 = this.q5;
                q6 = this.q6;
            }
            this.d.a(q5, q6);
        }
        catch (Exception ex) {}
    }
    
    public void setPageArray(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPageArray(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                if (b != null) {
                    this.qw[n] = b;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void setSeparatorArray(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setSeparatorArray(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                if (b != null && b instanceof URL) {
                    this.qx[n] = (URL)b;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void setThumbsArray(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setThumbsArray(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                if (b != null) {
                    this.q0[n] = b;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void openPageArray(final int n) {
        try {
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: openPageArray(").append(n).append(")"))));
            }
            if (this.ap()) {
                if (this.d.gm()) {
                    this.d.dg(false);
                }
                if (this.qw != null && this.q0 != null) {
                    this.d.a(this.qw, this.q0, n);
                }
                else if (this.qw != null) {
                    this.d.a(this.qw, n);
                }
                this.d.k9();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void openSeparatorArray() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: openSeparatorArray()"));
        }
        if (this.ap() && this.qx != null) {
            this.d.a(this.qx);
        }
    }
    
    public final void makeThumbnailVisible(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: makeThumbnailVisible(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.l(n);
            }
            catch (Exception ex) {}
        }
    }
    
    public void selectPage(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: selectPage(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.n(n);
            }
            catch (Exception ex) {}
        }
    }
    
    public final int getPrefetchPages() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPrefetchPages()"));
        }
        if (this.ap()) {
            try {
                return this.d.jv();
            }
            catch (Exception ex) {}
        }
        return 0;
    }
    
    public void setPrefetchPages(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrefetchPages(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.bb(n);
            }
            catch (Exception ex) {}
        }
    }
    
    public final int getPrefetchThumbs() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPrefetchThumbs()"));
        }
        if (this.ap()) {
            try {
                return this.d.jw();
            }
            catch (Exception ex) {}
        }
        return 0;
    }
    
    public void setPrefetchThumbs(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrefetchThumbs(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.bc(n);
            }
            catch (Exception ex) {}
        }
    }
    
    public void setPage(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPage(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                ji.util.d.mt.x = System.currentTimeMillis();
                ji.util.d.mt.y = 0L;
                ji.util.d.mt.z = System.currentTimeMillis();
                ji.util.d.mt.aa = 0L;
                this.d.ba(n);
            }
            catch (Exception ex) {}
        }
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPage(").append(n).append(") finished"))));
        }
    }
    
    public void nextPage() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: nextPage()"));
        }
        if (this.ap()) {
            try {
                ji.util.d.mt.x = System.currentTimeMillis();
                ji.util.d.mt.y = 0L;
                ji.util.d.mt.z = System.currentTimeMillis();
                ji.util.d.mt.aa = 0L;
                this.d.ba(this.d.j7() + 1);
            }
            catch (Exception ex) {}
        }
    }
    
    public void previousPage() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: previousPage()"));
        }
        if (this.ap()) {
            try {
                ji.util.d.mt.x = System.currentTimeMillis();
                ji.util.d.mt.y = 0L;
                ji.util.d.mt.z = System.currentTimeMillis();
                ji.util.d.mt.aa = 0L;
                this.d.ba(this.d.j7() - 1);
            }
            catch (Exception ex) {}
        }
    }
    
    public int getPage() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPage()"));
        }
        int max;
        if (this.ap()) {
            final int j6 = this.d.j6();
            final int j7 = this.d.j7();
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: p=").append(j6).append(", a=").append(j7))));
            }
            max = Math.max(j7, 1);
        }
        else if (this.d.dd() != null) {
            max = 1;
        }
        else {
            max = 0;
        }
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getPage() result ").append(max))));
        }
        return max;
    }
    
    public final String getPageObject() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPageObject()"));
        }
        if (this.ap()) {
            final Object bx = this.d.bx();
            return (bx == null) ? null : bx.toString();
        }
        return "unknown";
    }
    
    public final String[] getPageObjects() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPageObjects()"));
        }
        if (this.rx && !jiApplet.ua && ji.util.d.bk()) {
            return this.d.by();
        }
        return null;
    }
    
    public void setHyperlink(final String s, final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setHyperlink(").append(s).append(", ").append(b).append(")"))));
        }
        if (this.ap()) {
            final Object b2 = ji.util.d.b(this.getCodeBase(), s, this.uz);
            if (b2 != null) {
                this.d.a((URL)b2, this.getAppletContext(), b);
            }
        }
    }
    
    public String getSelection() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getSelection()"));
        }
        String s = "";
        try {
            if (this.ap()) {
                final boolean[] b0 = this.d.b0();
                if (b0 != null) {
                    for (int i = 0; i < b0.length; ++i) {
                        if (b0[i]) {
                            if (s.length() > 0) {
                                s = String.valueOf(String.valueOf(s)).concat(",");
                            }
                            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(i + 1)));
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return s;
    }
    
    public void clearHyperlink() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearHyperlink()"));
        }
        if (this.ap()) {
            this.d.a((URL)null, this.getAppletContext(), false);
        }
    }
    
    public void invert() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: invert()"));
        }
        if (this.ap()) {
            this.d.e7(!this.d.jg());
        }
    }
    
    public void setInverted(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: setInverted()"));
        }
        if (this.ap()) {
            this.d.e7(b);
        }
    }
    
    public boolean isInverted() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isInverted()"));
        }
        return this.ap() && this.d.jg();
    }
    
    public void toggleAdjustTool() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: toggleAdjustTool()"));
        }
        if (this.ap()) {
            this.d.b(new Point(0, 0));
        }
    }
    
    public boolean isAdjustToolVisible() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAdjustTool()"));
        }
        return this.ap() && this.d.cc();
    }
    
    public void setAdjustToolVisible(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAdjustToolVisible(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a(b, new Point(0, 0));
        }
    }
    
    public void setEnhance(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setEnhance(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.bx(b);
        }
    }
    
    public boolean isEnhance() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isEnhance()"));
        }
        return this.ap() && this.d.el();
    }
    
    public void setEnhanceMode(int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setEnhance(").append(n).append(")"))));
        }
        if (this.ap()) {
            boolean b = false;
            if (ji.util.i.c(227) && n == -1) {
                b = true;
            }
            if (n < 0) {
                n = 0;
            }
            if (n > 3) {
                n = 3;
            }
            if (!b && n == 0) {
                this.d.c(false, 0);
            }
            else {
                this.d.c(true, n);
            }
        }
        if (ji.util.i.c(227)) {
            this.d.gt();
            if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setEnhance(").append(n).append(") finished"))));
            }
        }
    }
    
    public int getEnhanceMode() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getEnhanceMode()"));
        }
        int ek;
        if (this.ap()) {
            if (this.d.el()) {
                ek = this.d.ek();
                if (ji.util.i.c(227) && ek == 0) {
                    ek = -1;
                }
            }
            else {
                ek = 0;
            }
        }
        else {
            ek = 1;
        }
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getEnhanceMode() returning ").append(ek))));
        }
        return ek;
    }
    
    public void setFlip(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setFlip(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.as(n);
        }
    }
    
    public int getFlip() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getFlip()"));
        }
        if (this.ap()) {
            return this.d.hw();
        }
        return 0;
    }
    
    public void setDocumentId(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDocumentId(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.az(s);
        }
    }
    
    public String getDocumentId() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getDocumentId()"));
        }
        if (this.ap()) {
            return this.d.hx();
        }
        return "";
    }
    
    public void setAllowTextRubberband(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAllowTextRubberband(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ey(b);
        }
    }
    
    public void setRotationAll(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRotation(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.a3(n);
        }
    }
    
    public void setRotation(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRotation(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.a2(n);
        }
    }
    
    public final void setShowAnnotations(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setShowAnnotations(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ez(b);
        }
    }
    
    public final boolean isShowAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isShowAnnotations()"));
        }
        return !this.ap() || this.d.id();
    }
    
    public void initializeRotationArray(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: initializeRotationArray(").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.q7 = new int[n];
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean setRotationArray(final int n, final int n2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRotation(").append(n).append(",").append(n2).append(")"))));
        }
        boolean b = false;
        if (this.ap()) {
            try {
                if (this.q7 != null && this.q7.length > n2) {
                    this.q7[n2] = n;
                    b = true;
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public void applyRotationArray() {
        try {
            if (this.q7 != null && this.q7.length > 0 && this.d != null) {
                this.d.c(this.q7);
            }
        }
        catch (Exception ex) {}
    }
    
    public int getRotation() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getRotation()"));
        }
        if (this.ap()) {
            return this.d.iv();
        }
        return 0;
    }
    
    public final int getRotationTransformation(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getRotationTransformation()"));
        }
        if (this.ap()) {
            return this.d.a4(n);
        }
        return 0;
    }
    
    public final void rotateClockwise() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: rotateClockwise()"));
        }
        if (this.ap()) {
            this.d.a2(this.d.iv() + 90);
        }
    }
    
    public final void rotateAnticlockwise() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: rotateAnticlockwise()"));
        }
        if (this.ap()) {
            this.d.a2(this.d.iv() - 90);
        }
    }
    
    public final void rotateCounterclockwise() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: rotateCounterclockwise()"));
        }
        this.rotateAnticlockwise();
    }
    
    public final void rotate180() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: rotate180()"));
        }
        if (this.ap()) {
            this.d.i1();
        }
    }
    
    public void setScale(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setScale(").append(n).append(")"))));
        }
        if (this.ap()) {
            switch (n) {
                case 1: {
                    this.d.a7(1);
                    break;
                }
                case 2: {
                    this.d.a7(0);
                    break;
                }
                default: {
                    this.d.a7(2);
                    break;
                }
            }
            if (ji.util.i.c(227)) {
                this.d.gt();
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setScale(").append(n).append(") finished"))));
                }
            }
        }
    }
    
    public int getScale() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getScale()"));
        }
        if (!this.ap()) {
            return 0;
        }
        final int jp = this.d.jp();
        if (jp == 1) {
            return 1;
        }
        if (jp == 0) {
            return 2;
        }
        return 0;
    }
    
    public void setView(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setView(").append(n).append(")"))));
        }
        if (this.ap()) {
            switch (n) {
                case 0: {
                    this.d.ac(0);
                    break;
                }
                case 1: {
                    this.d.ac(6);
                    break;
                }
                case 2: {
                    this.d.ac(5);
                    break;
                }
                case 3: {
                    this.d.ac(1);
                    break;
                }
                case 4: {
                    this.d.ac(3);
                    break;
                }
                case 5: {
                    this.d.ac(2);
                    break;
                }
                case 6: {
                    this.d.ac(4);
                    break;
                }
            }
        }
    }
    
    public int getView() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getView()"));
        }
        if (!this.ap()) {
            return 0;
        }
        switch (this.d.ej()) {
            case 6: {
                return 1;
            }
            case 5: {
                return 2;
            }
            case 1: {
                return 3;
            }
            case 3: {
                return 4;
            }
            case 2: {
                return 5;
            }
            case 4: {
                return 6;
            }
            default: {
                return 0;
            }
        }
    }
    
    public void setScrollbars(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setScrollbars(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.b0(b);
        }
    }
    
    public boolean isScrollbars() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isScrollbars()"));
        }
        return this.ap() && this.d.em();
    }
    
    public void setDraggingEnabled(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDraggingEnabled(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.b1(b);
        }
    }
    
    public boolean isDraggingEnabled() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isDraggingEnabled()"));
        }
        return this.ap() && this.d.es();
    }
    
    public void setAreaZoomEnabled(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAreaZoomEnabled(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.fc(b);
        }
    }
    
    public boolean isAreaZoomEnabled() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAreaZoomEnabled()"));
        }
        return this.ap() && this.d.jl();
    }
    
    public void setAreaZoom(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAreaZoom(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.e8(b);
        }
    }
    
    public final void toggleAreaZoom() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: toggleAreaZoom()"));
        }
        if (this.ap()) {
            this.d.jh();
        }
    }
    
    public boolean isAreaZoom() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAreaZoom()"));
        }
        return this.ap() && this.d.jk();
    }
    
    public String getStates() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getStates()"));
        }
        if (this.ap()) {
            return this.d.jo();
        }
        return "";
    }
    
    public void setStates(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setStates(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.a6(s);
            if (ji.util.i.c(227)) {
                this.d.gt();
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setStates(").append(s).append(") finished"))));
                }
            }
        }
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setStates(").append(s).append(") finished"))));
        }
    }
    
    public void setStatusBar(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setStatusBar(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cg(b);
        }
    }
    
    public boolean isStatusBar() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isStatusBar()"));
        }
        return this.ap() && this.d.e7();
    }
    
    public void setPrinter(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrinter(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.v(s);
        }
    }
    
    public void setPrintDialog(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintDialog(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.aj(b);
        }
    }
    
    public boolean isPrintDialog() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPrintDialog()"));
        }
        return !this.ap() || this.d.bc();
    }
    
    public void toggleMagnifier() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: toggleMagnifier()"));
        }
        if (this.ap()) {
            this.d.d7(!this.d.hk());
        }
    }
    
    public void setMagnifier(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setMagnifier(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.d6(true);
            this.d.b(b, true);
        }
    }
    
    public boolean isMagnifier() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isMagnifier()"));
        }
        return this.ap() && this.d.hk();
    }
    
    public void setMagnifierInternal(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setMagnifierInternal(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.d6(false);
            this.d.b(b, false);
        }
    }
    
    public void setMagFactor(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setMagFactor(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.aq(n);
        }
    }
    
    public int getMagFactor() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getMagFactor()"));
        }
        if (this.ap()) {
            return this.d.hl();
        }
        return 0;
    }
    
    public void setMagBounds(final int n, final int n2, final int n3, final int n4) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setMagBounds(").append(n).append(", ").append(n2).append(", ").append(n3).append(", ").append(n4).append(")"))));
        }
        if (this.ap()) {
            this.d.a(new Rectangle(n, n2, n3, n4));
        }
    }
    
    public void setPDFPixelDepth(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPDFPixelDepth(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.an(n);
        }
    }
    
    public int getPDFPixelDepth() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPDFPixelDepth()"));
        }
        if (this.ap()) {
            return this.d.hg();
        }
        return ji.util.d.lc;
    }
    
    public void setPDFResolution(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPDFResolution(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.ao(n);
        }
    }
    
    public int getPDFResolution() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getPDFResolution()"));
        }
        if (this.ap()) {
            return this.d.hj();
        }
        return ji.util.d.k9;
    }
    
    public void setAutoLimitPDFResolution(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAutoLimitPDFResolution(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ea(b);
        }
    }
    
    public final boolean isAutoLimitPDFResolution() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAutoLimitPDFResolution()"));
        }
        if (this.ap()) {
            return this.d.hh();
        }
        return ji.util.d.ld;
    }
    
    public final void setAutoLimitPDFMemoryValue(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAutoLimitPDFMemoryValue(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.ap(n);
        }
    }
    
    public final int getAutoLimitPDFMemoryValue() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getAutoLimitPDFMemoryValue()"));
        }
        if (this.ap()) {
            return this.d.hi();
        }
        return ji.util.d.le;
    }
    
    public void setFileButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setFileButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.dh(b);
            }
            else {
                this.w("setFileButtons");
            }
        }
    }
    
    public boolean isFileButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isFileButtons()"));
        }
        return this.ap() && this.d.gn();
    }
    
    public void setPrintButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.ci(b);
            }
            else {
                this.w("setPrintButtons");
            }
        }
    }
    
    public boolean isPrintButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPrintButtons()"));
        }
        return this.ap() && this.d.e9();
    }
    
    public void setImageButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.cd(b);
            }
            else {
                this.w("setImageButtons");
            }
        }
    }
    
    public boolean isImageButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isImageButtons()"));
        }
        return this.ap() && this.d.e5();
    }
    
    public void setTopButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setTopButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cy(b);
        }
    }
    
    public boolean isTopButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isTopButtons()"));
        }
        return this.ap() && this.d.fh();
    }
    
    public void setViewAndTopButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setViewAndTopButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cz(b);
        }
    }
    
    public boolean isViewAndTopButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isViewAndTopButtons()"));
        }
        return this.ap() && this.d.fi();
    }
    
    public void setEventHandler(final String fh) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setEventHandler(").append(fh).append(")"))));
        }
        if (ji.util.d.bk()) {
            this.fh = fh;
        }
        else {
            this.w("setEventHandler");
        }
    }
    
    public void registerEventInterest(final int n) {
        try {
            if (this.t5 == null) {
                this.t5 = new c("jiApplet-jsEventList");
            }
            if (this.t5.d("".concat(String.valueOf(String.valueOf(n)))) == null) {
                this.t5.a("".concat(String.valueOf(String.valueOf(n))), "".concat(String.valueOf(String.valueOf(n))));
                if (ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: registerEventInterest(").append(n).append(")"))));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void registerEventsInterest(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: registerEventsInterest(").append(s).append(")"))));
        }
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            while (stringTokenizer.hasMoreTokens()) {
                this.registerEventInterest(q(stringTokenizer.nextToken()));
            }
        }
        catch (Exception ex) {}
    }
    
    public void unregisterEventInterest(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: unregisterEventInterest(").append(n).append(")"))));
        }
        try {
            if (this.t5 != null && this.t5.d("".concat(String.valueOf(String.valueOf(n)))) == null) {
                this.t5.a("".concat(String.valueOf(String.valueOf(n))));
            }
        }
        catch (Exception ex) {}
    }
    
    public void clearEventInterest() {
        try {
            if (this.t5 != null) {
                if (this.t5.b() > 0 && ji.util.i.c(6)) {
                    ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearEventInterest()"));
                }
                this.t5.c();
                this.t5 = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public void setServerEventHandler(final String fj) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setServerEventHandler(").append(fj).append(")"))));
        }
        this.fj = fj;
    }
    
    public void setNewWindowVisible(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setNewWindowVisible(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a(b, -1);
        }
    }
    
    public boolean isNewWindowVisible() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isNewWindowVisible()"));
        }
        return this.ap() && this.d.d1();
    }
    
    public void setNewWindowButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setNewWindowButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.fh(b);
        }
    }
    
    public boolean isNewWindowButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isNewWindowButtons()"));
        }
        return this.ap() && this.d.kf();
    }
    
    public void setViewButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setViewButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cj(b);
        }
    }
    
    public boolean isViewButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isViewButtons()"));
        }
        return this.ap() && this.d.fa();
    }
    
    public void setAllButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAllButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.cv(b);
            }
            else {
                this.w("setAllButtons");
            }
        }
    }
    
    public boolean isAllButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAllButtons()"));
        }
        return this.ap() && this.d.fg();
    }
    
    public void setPageButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPageButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ck(b);
        }
    }
    
    public boolean isPageButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPageButtons()"));
        }
        return this.ap() && this.d.fb();
    }
    
    public void setFileKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setFileKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.b4(b);
            }
            else {
                this.w("setFileKeys");
            }
        }
    }
    
    public boolean isFileKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isFileKeys()"));
        }
        return this.ap() && this.d.ev();
    }
    
    public void setPrintKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.b9(b);
            }
            else {
                this.w("setPrintKeys");
            }
        }
    }
    
    public boolean isPrintKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPrintKeys()"));
        }
        return this.ap() && this.d.e1();
    }
    
    public void setImageKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.b2(b);
        }
    }
    
    public boolean isImageKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isImageKeys()"));
        }
        return this.ap() && this.d.et();
    }
    
    public void setPageKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPageKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cb(b);
        }
    }
    
    public boolean isPageKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPageKeys()"));
        }
        return this.ap() && this.d.e3();
    }
    
    public void setSelectKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setSelectKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cc(b);
        }
    }
    
    public boolean isSelectKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isSelectKeys()"));
        }
        return this.ap() && this.d.e4();
    }
    
    public void setViewKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setViewKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ca(b);
        }
    }
    
    public boolean isViewKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isViewKeys()"));
        }
        return this.ap() && this.d.e2();
    }
    
    public void setAllKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAllKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.c0(b);
            }
            else {
                this.w("setAllKeys");
            }
        }
    }
    
    public boolean isAllKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAllKeys()"));
        }
        return this.ap() && this.d.fj();
    }
    
    public void setFileMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setFileMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.av(b);
            }
            else {
                this.w("setFileMenus");
            }
        }
    }
    
    public boolean isFileMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isFileMenus()"));
        }
        return this.ap() && this.d.bk();
    }
    
    public void setPreferenceMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPreferenceMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a7(b);
        }
    }
    
    public boolean isPreferenceMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPreferenceMenus()"));
        }
        return this.ap() && this.d.bw();
    }
    
    public void setPrintMonoAllowed(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintMonoAllowed(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.es(b);
        }
    }
    
    public boolean isPrintMonoAllowed() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPrintMonoAllowed()"));
        }
        return this.ap() && this.d.h7();
    }
    
    public void setPrintMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.a4(b);
            }
            else {
                this.w("setPrintMenus");
            }
        }
    }
    
    public boolean isPrintMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPrintMenus()"));
        }
        return this.ap() && this.d.bs();
    }
    
    public void setCacheMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setCacheMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.a5(b);
            }
            else {
                this.w("setCacheMenus");
            }
        }
    }
    
    public boolean isCacheMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isCacheMenus()"));
        }
        return this.ap() && this.d.bt();
    }
    
    public void setImageMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a0(b);
        }
    }
    
    public boolean isImageMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isImageMenus()"));
        }
        return this.ap() && this.d.bo();
    }
    
    public void setViewMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setViewMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a6(b);
        }
    }
    
    public boolean isViewMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isViewMenus()"));
        }
        return this.ap() && this.d.bu();
    }
    
    public void setPageMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPageMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a2(b);
        }
    }
    
    public boolean isPageMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isPageMenus()"));
        }
        return this.ap() && this.d.bq();
    }
    
    public void setSelectMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setSelectMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a3(b);
        }
    }
    
    public boolean isSelectMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isSelectMenus()"));
        }
        return this.ap() && this.d.br();
    }
    
    public void setAllMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAllMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            if (ji.util.d.bk()) {
                this.d.c1(b);
            }
            else {
                this.w("setAllMenus");
            }
        }
    }
    
    public boolean isAllMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAllMenus()"));
        }
        return this.ap() && this.d.fk();
    }
    
    public final String getVersion() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getVersion()"));
        }
        if (this.ap()) {
            return this.d.du(true);
        }
        return "";
    }
    
    public final String getCompany() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getCompany()"));
        }
        if (this.ap()) {
            return this.d.dw(true);
        }
        return "";
    }
    
    public final String getCopyright() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getCopyright()"));
        }
        if (this.ap()) {
            return this.d.dv(true);
        }
        return "";
    }
    
    public final String getCacheFile() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getCacheFile()"));
        }
        if (this.ap()) {
            return this.d.ha();
        }
        return "";
    }
    
    public void setAppletBorder(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAppletBorder(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.x(b);
        }
    }
    
    public boolean isAppletBorder() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAppletBorder()"));
        }
        return this.ap() && this.d.au();
    }
    
    public int getXScroll() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getXScroll()"));
        }
        return this.d.hp();
    }
    
    public int getYScroll() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getYScroll()"));
        }
        return this.d.hq();
    }
    
    public void setXYScroll(final int n, final int n2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setXYScroll(").append(n).append(", ").append(n2).append(")"))));
        }
        if (this.ap()) {
            boolean p2 = true;
            if (this.hi != null) {
                p2 = p(this.hi);
            }
            this.d.f(this.d.a(this.d.ho(), n, n2, false), p2);
        }
    }
    
    public void setZoomAndXYScroll(final double n, final int n2, final int n3) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setZoomAndXYScroll(").append(n).append(", ").append(n2).append(", ").append(n3).append(")"))));
        }
        if (this.ap()) {
            boolean p3 = true;
            if (this.hi != null) {
                p3 = p(this.hi);
            }
            this.d.f(this.d.a(n, n2, n3, false), p3);
        }
    }
    
    public final void setZoomRate(final double n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setZoomRate(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.e(n);
        }
    }
    
    public final double getZoomRate() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getZoomRate()"));
        }
        if (this.ap()) {
            return this.d.he();
        }
        return 25.0;
    }
    
    public final void setZoom(final double n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setZoom(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.g(n);
        }
    }
    
    public final double getZoom() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getZoom()"));
        }
        if (this.ap()) {
            return this.d.ho();
        }
        return 0.0;
    }
    
    public final void zoomIn() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: zoomIn()"));
        }
        if (this.ap()) {
            this.d.hr();
        }
    }
    
    public final void zoomArea(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: zoomArea(").append(n).append(", ").append(n2).append(", ").append(n3).append(", ").append(n4).append(", ").append(b).append(", ").append(n5).append(")"))));
        }
        if (this.ap()) {
            this.d.a(n, n2, n3, n4, b, Math.max(n5, 0));
        }
    }
    
    public final void zoomOut() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: zoomOut()"));
        }
        if (this.ap()) {
            this.d.ht();
        }
    }
    
    public final void zoom100() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: zoom100()"));
        }
        if (this.ap()) {
            this.d.hs();
        }
    }
    
    public final void printDocument() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printDocument()"));
        }
        if (ji.util.d.bk()) {
            this.d.ja();
        }
        else {
            this.w("printDocument");
        }
    }
    
    public final void printTransformed() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printTransformed()"));
        }
        if (ji.util.d.bk()) {
            this.d.jc();
        }
        else {
            this.w("printTransformed");
        }
    }
    
    public final void printPage() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printPage()"));
        }
        if (ji.util.d.bk()) {
            this.d.i8();
        }
        else {
            this.w("printPage");
        }
    }
    
    public final void setPrintCopies(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintCopies(").append(n).append(")"))));
        }
        ji.util.d.t(n);
    }
    
    public final void clearSelections() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearSelections()"));
        }
        if (this.ap()) {
            this.d.b1();
        }
    }
    
    public final void printSelected() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printSelected()"));
        }
        if (ji.util.d.bk()) {
            this.d.jd();
        }
        else {
            this.w("printSelected");
        }
    }
    
    public final void printRange() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printRange()"));
        }
        if (ji.util.d.bk()) {
            this.d.i9();
        }
        else {
            this.w("printRange");
        }
    }
    
    public final void printVisible() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: printVisible()"));
        }
        if (ji.util.d.bk()) {
            this.d.jb();
        }
        else {
            this.w("printVisible");
        }
    }
    
    public final void setPrintHeader(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setPrintHeader(").append(s).append(")"))));
        }
        if (ji.util.d.bk()) {
            this.d.a4(s);
        }
        else {
            this.w("setPrintHeader");
        }
    }
    
    public final void setImageTooLargePrintHeader(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setIMageTooLargePrintHeader(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.a5(s);
        }
    }
    
    public final String getTransformation() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getTransformation()"));
        }
        if (this.ap()) {
            return this.d.hn();
        }
        return "";
    }
    
    public final void setBrightness(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setBrightness(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.z(n * ji.util.d.d0 / 100);
        }
    }
    
    public final void resetBrightness() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: resetBrightness()"));
        }
        if (this.ap()) {
            this.d.d7();
        }
    }
    
    public final int getBrightness() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getBrightness()"));
        }
        if (this.ap()) {
            return 100 * this.d.d3() / ji.util.d.d0;
        }
        return 100 * ji.util.d.du / ji.util.d.d0;
    }
    
    public final void setContrast(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setContrast(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.aa(n * ji.util.d.d1 / 100);
        }
    }
    
    public final void resetContrast() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: resetContrast()"));
        }
        if (this.ap()) {
            this.d.eb();
        }
    }
    
    public final int getContrast() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getContrast()"));
        }
        if (this.ap()) {
            return 100 * this.d.d4() / ji.util.d.d1;
        }
        return 100 * ji.util.d.dv / ji.util.d.d1;
    }
    
    public final void setLuminance(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setLuminance(").append(n).append(")"))));
        }
        if (this.ap()) {
            this.d.ab(n * ji.util.d.d2 / 100);
        }
    }
    
    public final void resetLuminance() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: resetLuminance()"));
        }
        if (this.ap()) {
            this.d.ee();
        }
    }
    
    public final void wakeUp() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: wakeUp()"));
        }
        if (ji.util.d.bk()) {
            if (this.d != null) {
                this.d.f5();
            }
        }
        else {
            this.w("wakeUp");
        }
    }
    
    public final boolean isTimedOut() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isTimedOut()"));
        }
        return this.ap() && this.d.f4();
    }
    
    public final void setTimeout(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setTimeout(").append(n).append(")"))));
        }
        if (ji.util.d.bk()) {
            if (this.d != null) {
                this.d.ai(n);
            }
        }
        else {
            this.w("setTimeout");
        }
    }
    
    public final int getTimeout() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getTimeout()"));
        }
        if (this.d != null) {
            return this.d.f2();
        }
        return 0;
    }
    
    public final void stopTimeout() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: stopTimeout()"));
        }
        if (ji.util.d.bk()) {
            if (this.d != null) {
                this.d.f7();
            }
        }
        else {
            this.w("stopTimeout");
        }
    }
    
    public final int getTimeLeft() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getTimeLeft()"));
        }
        if (this.ap()) {
            return this.d.f3();
        }
        return -1;
    }
    
    public final int getLuminance() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getLuminance()"));
        }
        if (this.ap()) {
            return 100 * this.d.d5() / ji.util.d.d2;
        }
        return 100 * ji.util.d.dw / ji.util.d.d2;
    }
    
    public final void setMagOptions(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setMagOptions(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.at(b);
        }
    }
    
    public boolean isMagOptions() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isMagOptions()"));
        }
        return this.ap() && this.d.bi();
    }
    
    public final void setZoom100Options(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setZoom100Options(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.au(b);
        }
    }
    
    public boolean isZoom100Options() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isZoom100Options()"));
        }
        return this.ap() && this.d.bj();
    }
    
    public final void setFlipOptions(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setFlipOptions(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ap(b);
        }
    }
    
    public boolean isFlipOptions() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isFlipOptions()"));
        }
        return this.ap() && this.d.bd();
    }
    
    public final void setInfoOptions(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setInfoOptions(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.aq(b);
        }
    }
    
    public boolean isInfoOptions() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isInfoOptions()"));
        }
        return this.ap() && this.d.be();
    }
    
    public final void setClipOptions(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setClipOptions(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.ar(b);
        }
    }
    
    public boolean isClipOptions() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isClipOptions()"));
        }
        return this.ap() && this.d.bf();
    }
    
    public final void setAdjustOptions(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAdjustOptions(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.as(b);
        }
    }
    
    public boolean isAdjustOptions() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAdjustOptions()"));
        }
        return this.ap() && this.d.bh();
    }
    
    public final void setInvertButtons(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setInvertButtons(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.cf(b);
        }
    }
    
    public final void setInvertMenus(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setInvertMenus(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.a1(b);
        }
    }
    
    public final void setInvertKeys(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setInvertKeys(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.b3(b);
        }
    }
    
    public boolean isInvertButtons() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isInvertButtons()"));
        }
        return this.ap() && this.d.e6();
    }
    
    public boolean isInvertKeys() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isInvertKeys()"));
        }
        return this.ap() && this.d.eu();
    }
    
    public boolean isInvertMenus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isInvertMenus()"));
        }
        return this.ap() && this.d.bp();
    }
    
    public boolean isImageOK() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isImageOK()"));
        }
        if (!this.ap()) {
            return false;
        }
        if (this.d.dl() != null) {
            try {
                while (!this.d.gv()) {
                    Thread.sleep(100L);
                }
            }
            catch (Exception ex) {}
            return this.d.g2();
        }
        return false;
    }
    
    public void requestFocus() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: requestFocus()"));
        }
        try {
            if (!this.iy) {
                if (this.ap()) {
                    this.d.requestFocus();
                }
                else {
                    super.requestFocus();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean isCacheAccessFailed() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isCacheAccessFailed()"));
        }
        return ji.util.i.c(52);
    }
    
    public void setImageForeColor(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageForeColor(").append(s).append(")"))));
        }
        if (this.ap() && !ji.util.d.by(s)) {
            this.d.k(ji.util.d.b1(s));
        }
    }
    
    public void setImageBackColor(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setImageBackColor(").append(s).append(")"))));
        }
        if (this.ap() && !ji.util.d.by(s)) {
            this.d.j(ji.util.d.b1(s));
        }
    }
    
    public void addDocumentIndexItem(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: addDocumentIndexItem(").append(s).append(", ").append(n).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.m(s, n);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void removeDocumentIndexItem(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: removeDocumentIndexItem(").append(s).append(")"))));
        }
        if (this.ap()) {
            try {
                this.d.aq(s);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setIndexListFile(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setIndexListFile(").append(s).append(")"))));
        }
        if (this.ap()) {
            try {
                if (s != null) {
                    final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                    if (b != null) {
                        this.d.d(b);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setDocumentIndexListFile(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDocumentIndexListFile(").append(s).append(")"))));
        }
        if (this.ap()) {
            try {
                if (s != null) {
                    final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                    if (b != null) {
                        this.d.e(b);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void setAnnotationBurnURL(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationBurnURL(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.d(ji.util.d.b(this.getCodeBase(), s, this.uz).toString(), true);
        }
    }
    
    public void setAnnotationFile(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationFile(").append(s).append(")"))));
        }
        ji.util.i.a(50, true);
        if (this.ap()) {
            if (ji.util.d.bj()) {
                if (s != null) {
                    if (s.length() == 0) {
                        this.d.h((Object)null);
                    }
                    else {
                        final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
                        if (b != null) {
                            if (b instanceof String) {
                                this.d.h(b);
                            }
                            else {
                                this.d.h(b);
                            }
                        }
                    }
                }
            }
            else {
                this.w("setAnnotationFile");
            }
        }
    }
    
    public void setAnnotationTemplate(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationTemplate(").append(s).append(")"))));
        }
        if (this.ap() && s != null) {
            final Object b = ji.util.d.b(this.getCodeBase(), s, this.uz);
            if (b != null) {
                if (b instanceof String) {
                    this.d.i(b);
                }
                else {
                    this.d.i(b);
                }
            }
        }
    }
    
    public void reloadAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: reloadAnnotations()"));
        }
        if (this.ap()) {
            if (ji.util.d.bj()) {
                this.d.di();
            }
            else {
                this.w("reloadAnnotations");
            }
        }
    }
    
    public void saveAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: saveAnnotations()"));
        }
        if (this.ap()) {
            this.d.do();
        }
    }
    
    public final void burnAnnotations(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: burnAnnotations(").append(b).append(")"))));
        }
        if (this.ap()) {
            this.d.bs(b);
        }
    }
    
    public final boolean isAnnotationsUpdated() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAnnotationsUpdated()"));
        }
        final boolean b = this.ap() && this.d.ei();
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: isAnnotationsUpdated() returning ").append(b))));
        }
        return b;
    }
    
    public void setAnnotationSave(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationSave(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.au(ji.util.d.b(this.getCodeBase(), s, this.uz).toString());
        }
    }
    
    public void setAnnotationSaveServlet(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationSaveServlet(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.a(ji.util.d.b(this.getCodeBase(), s, this.uz).toString(), "servlet");
        }
    }
    
    public void setAnnotationSavePost(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationSavePost(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.a(ji.util.d.b(this.getCodeBase(), s, this.uz).toString(), "post");
        }
    }
    
    public void setAnnotationPostPrefix(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationPostPrefix(").append(s).append(")"))));
        }
        if (this.ap()) {
            this.d.at(s);
        }
    }
    
    public void setAnnotateEdit(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotateEdit(").append(b).append(")"))));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                this.d.a(b, true);
            }
        }
        else {
            this.w("setAnnotateEdit");
        }
    }
    
    public boolean isAnnotateEdit() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAnnotateEdit()"));
        }
        return this.ap() && this.d.f();
    }
    
    public void showAnnotationToolbar(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: showAnnotationToolbar(").append(b).append(")"))));
        }
        try {
            if (this.ap()) {
                this.d.r(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean isAnnotationToolbar() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: isAnnotationToolbar()"));
        }
        return this.ap() && this.d.p();
    }
    
    public int getNumAnnotations(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getNumAnnotations(").append(s).append(", ").append(n).append(")"))));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                return this.d.a(s, n);
            }
        }
        else {
            this.w("getNumAnnotations");
        }
        return 0;
    }
    
    public String getAnnotationLabels(final String s, final int n, final int n2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getAnnotationLabels(").append(s).append(", ").append(n).append(", ").append(n2).append(")"))));
        }
        if (!ji.util.d.bj()) {
            this.w("getAnnotationLabels");
            return null;
        }
        if (this.ap()) {
            return this.d.a(s, n, n2);
        }
        return null;
    }
    
    public String getAnnotation(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getAnnotation(").append(s).append(")"))));
        }
        if (!ji.util.d.bj()) {
            this.w("getAnnotation");
            return "OPTION DISABLED";
        }
        if (!this.ap()) {
            return "NONE";
        }
        final String c = this.d.c(s);
        if (c != null) {
            return c;
        }
        return "NONE";
    }
    
    private boolean ap() {
        if (this.rx) {
            return this.rx && !jiApplet.ua;
        }
        return this.d != null && !jiApplet.ua;
    }
    
    public String getAnnotationOnPage(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: getAnnotationOnPage(").append(s).append(", ").append(n).append(")"))));
        }
        if (!ji.util.d.bj()) {
            this.w("getAnnotationOnPage");
            return "OPTION DISABLED";
        }
        if (!this.ap()) {
            return "NONE";
        }
        final String b = this.d.b(s, n);
        if (b != null) {
            return b;
        }
        return "NONE";
    }
    
    public void signalRender() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: signalRender()"));
        }
        if (this.ap()) {
            if (this.d != null) {
                this.d.kz();
            }
            else if (ji.util.i.c(6)) {
                ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: signalRender(): Could not send signal to render."));
            }
        }
        else if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: signalRender(): JavaScript is not ready"));
        }
    }
    
    public boolean addAnnotation(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: addAnnotation(").append(s).append(")"))));
        }
        boolean h = false;
        if (ji.util.d.bj()) {
            if (this.ap()) {
                h = this.d.h(s);
            }
        }
        else {
            this.w("addAnnotation");
        }
        return h;
    }
    
    public void startModifyAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: startModifyAnnotation()"));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                this.ju = true;
            }
        }
        else {
            this.w("startModifyAnnotation");
        }
        this.jv = false;
    }
    
    public void endModifyAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: endModifyAnnotation()"));
        }
        if (ji.util.d.bj()) {
            if (this.ap() && this.ju && this.jv) {
                this.d.o();
            }
        }
        else {
            this.w("startModifyAnnotation");
        }
        this.ju = false;
    }
    
    public boolean modifyAnnotation(final String s, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: modifyAnnotation(").append(s).append(", ").append(s2).append(")"))));
        }
        boolean b = false;
        if (ji.util.d.bj()) {
            if (this.ap()) {
                if (this.ju) {
                    b = this.d.a(s, s2, false);
                }
                else {
                    b = this.d.a(s, s2, true);
                }
            }
        }
        else {
            this.w("modifyAnnotation");
        }
        if (b && this.ju) {
            this.jv = true;
        }
        return b;
    }
    
    public boolean modifyMultipleAnnotation(final String s, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: modifyAnnotation(").append(s).append(", ").append(s2).append(")"))));
        }
        boolean a = false;
        if (ji.util.d.bj()) {
            if (this.ap()) {
                a = this.d.a(s, s2, false);
            }
        }
        else {
            this.w("modifyAnnotation");
        }
        return a;
    }
    
    public boolean deleteAnnotation(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: deleteAnnotation(").append(s).append(")"))));
        }
        boolean i = false;
        if (ji.util.d.bj()) {
            if (this.ap()) {
                i = this.d.i(s);
            }
        }
        else {
            this.w("deleteAnnotation");
        }
        return i;
    }
    
    public boolean deleteAllAnnotations(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: deleteAllAnnotations(").append(s).append(", ").append(n).append(")"))));
        }
        boolean d = false;
        if (ji.util.d.bj()) {
            if (this.ap()) {
                d = this.d.d(s, n);
            }
        }
        else {
            this.w("deleteAllAnnotations");
        }
        return d;
    }
    
    public void startAnnotation(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: startAnnotation(").append(s).append(")"))));
        }
        this.a(new cl(89, s, null));
    }
    
    public void startAnnotationWithProperties(final String s, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: startAnnotation(").append(s).append(")"))));
        }
        this.a(new cl(89, s, s2));
    }
    
    public void endAnnotation() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: endAnnotation()"));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                this.d.r();
            }
        }
        else {
            this.w("endAnnotation");
        }
    }
    
    public void addAnnotationStamp(final String s, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: addAnnotationStamp(").append(s).append(",").append(s2).append(")"))));
        }
        if (ji.util.d.bj()) {
            this.startAnnotation("STAMPMENU".concat(String.valueOf(String.valueOf(Integer.toString(this.d.a(s, s2, (gj)null))))));
        }
        else {
            this.w("addAnnotationStamp");
        }
    }
    
    public boolean setAnnotationStampText(final String s, final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setAnnotationStampText(").append(s).append(",").append(n).append(")"))));
        }
        return this.d.f(s, n);
    }
    
    public void clearAnnotationStamps() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: clearAnnotationStamps"));
        }
        this.d.a7();
    }
    
    public boolean insertAnnotationStamp(final String s, final int n, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: insertAnnotationStamp(").append(s).append(",").append(n).append(")"))));
        }
        gj a = null;
        try {
            final gj gj = new gj();
            gj.a();
            gj.x = ji.font.j.t;
            a = a(s, gj, s2, null, null, null);
        }
        catch (Exception ex) {}
        return this.d.a(s, n, a);
    }
    
    public boolean removeAnnotationStamp(final int n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: insertAnnotationStamp(").append(n).append(")"))));
        }
        return this.d.k(n);
    }
    
    public void addAnnotationStampWithProperties(final String s, final String s2, final String s3) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: addAnnotationStamp(").append(s).append(",").append(s2).append(")"))));
        }
        if (ji.util.d.bj()) {
            gj a = null;
            if (s3 != null) {
                a = a(s2, null, s3, this.rl, this.rm, this.rn);
            }
            this.startAnnotationWithProperties("STAMPMENU".concat(String.valueOf(String.valueOf(Integer.toString(this.d.a(s, s2, a))))), s3);
        }
        else {
            this.w("addAnnotationStamp");
        }
    }
    
    private void b(String s, final String s2) {
        if (ji.util.d.bj()) {
            if (this.ap()) {
                gj a = null;
                if (s2 != null) {
                    a = a(null, null, s2, this.rl, this.rm, this.rn);
                }
                if (!ji.util.d.by(s)) {
                    if (!ji.annotate.dg.x(s)) {
                        ji.io.h.b(this.uz, String.valueOf(String.valueOf(s)).concat(" is an invalid annotation type."));
                        return;
                    }
                    if (s.toUpperCase().equals("RULER")) {
                        s = "LINE";
                        if (a == null) {
                            a = new gj();
                        }
                        a.aa = true;
                    }
                    if (s.toUpperCase().equals("ANGLE")) {
                        s = "OPENPOLY";
                        if (a == null) {
                            a = new gj();
                        }
                        a.ab = true;
                    }
                    if (s.toUpperCase().equals("ANGLEREVERSED")) {
                        s = "OPENPOLY";
                        if (a == null) {
                            a = new gj();
                        }
                        a.ab = true;
                        a.ac = true;
                    }
                    if (s.toUpperCase().equals("SQUARE")) {
                        s = "RECTANGLE";
                        if (a == null) {
                            a = new gj();
                        }
                        a.z = "1:1";
                    }
                    if (s.toUpperCase().equals("CIRCLE")) {
                        s = "OVAL";
                        if (a == null) {
                            a = new gj();
                        }
                        a.z = "1:1";
                    }
                    if (s.toUpperCase().equals("HYPERLINK")) {
                        this.d.s("annhyperlink", null, 0, a);
                    }
                    else if (s.toUpperCase().equals("TEXTSOLID")) {
                        this.d.e("anntext2", null, 0, a);
                    }
                    else if (s.toUpperCase().startsWith("STAMPMENU")) {
                        this.d.a(ji.util.d.c(s.substring(9), 1) - 1, a);
                    }
                    else {
                        switch (ji.annotate.dg.y(s)) {
                            case 4: {
                                this.d.b("annline", null, 0, a);
                                break;
                            }
                            case 5: {
                                this.d.a("annarrow", null, 0, a);
                                break;
                            }
                            case 7: {
                                this.d.d("anntext", null, 0, a);
                                break;
                            }
                            case 8: {
                                this.d.q("annnote", null, 0, a);
                                break;
                            }
                            case 13: {
                                this.d.t("annhighlight", null, 0, a);
                                break;
                            }
                            case 14: {
                                this.d.r("annhighlightpoly", null, 0, a);
                                break;
                            }
                            case 1: {
                                this.d.n("annrectangle", null, 0, a);
                                break;
                            }
                            case 11: {
                                this.d.p("annredact", null, 0, a);
                                break;
                            }
                            case 12: {
                                this.d.o("annredactpoly", null, 0, a);
                                break;
                            }
                            case 3: {
                                this.d.m("annpoly", null, 0, a);
                                break;
                            }
                            case 6: {
                                this.d.j("annpolyopen", null, 0, a);
                                break;
                            }
                            case 2: {
                                this.d.c("annoval", null, 0, a);
                                break;
                            }
                            case 10: {
                                this.d.k("annfreehand", null, 0, a);
                                break;
                            }
                            case 9: {
                                this.d.l("annstamp", null, 0, a);
                                break;
                            }
                        }
                    }
                }
            }
        }
        else {
            this.w("startAnnotationAction");
        }
    }
    
    public final String getAnnotationError() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getAnnotationError()"));
        }
        if (ji.util.d.bj()) {
            return this.d.gh();
        }
        this.w("getAnnotationError");
        return "OPTION DISABLED";
    }
    
    public final String getSelectedAnnotations() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getSelectedAnnotations()"));
        }
        if (!ji.util.d.bj()) {
            this.w("getSelectedAnnotations");
            return "";
        }
        if (this.ap()) {
            return this.d.q();
        }
        return "";
    }
    
    public boolean selectAnnotation(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: selectAnnotation(").append(s).append(")"))));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                return this.d.j(s);
            }
        }
        else {
            this.w("selectAnnotation");
        }
        return false;
    }
    
    public void setDefaultSelectAnnotation(final boolean b) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDefaultSelectAnnotation(").append(b).append(")"))));
        }
        if (ji.util.d.bj()) {
            if (this.ap()) {
                this.d.t(b);
            }
        }
        else {
            this.w("setDefaultSelectAnnotation");
        }
    }
    
    public final String parseProperty(final String s, final String s2) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: parseProperty(").append(s).append(", ").append(s2).append(")"))));
        }
        return ji.util.d.r(s, s2);
    }
    
    public final void setDelimiter(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setDelimiter(").append(s).append(")"))));
        }
        ji.util.d.s(s);
    }
    
    public final String getDelimiter() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getDelimiter()"));
        }
        return ji.util.d.a3();
    }
    
    public final int getNumPages() {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(this.rx)).concat(">JS: getNumPages()"));
        }
        if (this.ap()) {
            return this.d.fg(true);
        }
        return 0;
    }
    
    public final void setRulerScale(final double n) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRulerScale(").append(n).append(")"))));
        }
        this.nk = Double.toString(n);
        ji.util.d.b(n);
    }
    
    public final void setRulerUnits(final String s) {
        if (ji.util.i.c(6)) {
            ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.rx))).append(">JS: setRulerUnits(").append(s).append(")"))));
        }
        this.x(s);
    }
    
    private final void x(final String ni) {
        this.ni = ni;
        if (this.ni.startsWith("cm")) {
            ji.util.d.g(1);
        }
        else if (this.ni.startsWith("mm")) {
            ji.util.d.g(2);
        }
        else if (this.ni.startsWith("inchesandcm")) {
            ji.util.d.g(3);
        }
        else {
            ji.util.d.g(0);
        }
    }
    
    public Hashtable getCookies() {
        Hashtable<Object, Object> a = null;
        if (this.uk != null && this.uk.size() > 0) {
            try {
                String ul = null;
                if (!ji.util.d.by(this.ul)) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.uz, "Obtaining cookies via HTML parameter");
                    }
                    ul = this.ul;
                }
                else {
                    try {
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.uz, "Obtaining cookies via JS");
                        }
                        final Object aj = this.aj();
                        if (aj instanceof JSObject) {
                            ul = (String)((JSObject)((JSObject)aj).getMember("document")).getMember("cookie");
                        }
                        else if (ji.util.d.cy()) {
                            ji.io.h.d(this.uz, "JS window is: ".concat(String.valueOf(String.valueOf(aj))));
                        }
                    }
                    catch (Exception ex) {
                        if (ji.util.d.cy()) {
                            ji.io.h.d(this.uz, "cookieDomainList is set, but a failure was detected when trying to obtain them from the parent browser window: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                        }
                        if (ji.util.d.cy()) {
                            ex.printStackTrace();
                        }
                    }
                    catch (NoClassDefFoundError noClassDefFoundError) {
                        ji.io.h.d(this.uz, "getCookies failed: ".concat(String.valueOf(String.valueOf(noClassDefFoundError.getMessage()))));
                        if (ji.util.d.cy()) {
                            noClassDefFoundError.printStackTrace();
                        }
                    }
                }
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.uz, "Cookie string: ".concat(String.valueOf(String.valueOf(ul))));
                }
                if (!ji.util.d.by(ul)) {
                    a = null;
                    a = (Hashtable<Object, Object>)ji.net.cookie.bc.a(ul, (jiLogger)null);
                    if (ji.util.d.cy()) {
                        for (int n = 0; this.uk != null && n < this.uk.size(); ++n) {
                            ji.io.h.d(this.uz, "Cookie allowed domain: ".concat(String.valueOf(String.valueOf(this.uk.elementAt(n)))));
                        }
                        if (a != null) {
                            final Enumeration<Object> keys = a.keys();
                            while (keys.hasMoreElements()) {
                                final Object nextElement = keys.nextElement();
                                ji.io.h.d(this.uz, String.valueOf(String.valueOf(new StringBuffer("Cookie name: ").append(nextElement).append(", value: ").append(a.get(nextElement)))));
                            }
                        }
                        else {
                            ji.io.h.d(this.uz, "No cookies found.");
                        }
                    }
                }
                else if (ji.util.d.cy()) {
                    ji.io.h.d(this.uz, "No cookies found");
                }
            }
            catch (Exception ex2) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.uz, "cookieDomainList is set, but a failure was detected when trying to obtain them from the parent browser window: ".concat(String.valueOf(String.valueOf(ex2.getMessage()))));
                }
                if (ji.util.d.cy()) {
                    ex2.printStackTrace();
                }
            }
        }
        else if (ji.util.d.cy()) {
            ji.io.h.d(this.uz, "cookieDomainList is not set or empty, not returning cookies");
        }
        return a;
    }
    
    public Vector getCookieAllowedDomainList() {
        return this.uk;
    }
    
    static {
        EventProcessorLOCK = new Object();
        jiApplet.rq = 0;
        jiApplet.rr = 0;
        jiApplet.rs = 0;
        jiApplet.rt = 0;
        jiApplet.sa = 0;
        jiApplet.sg = false;
        jiApplet.t9 = false;
        jiApplet.ua = false;
        jiApplet.ub = false;
        jiApplet.uc = false;
        jiApplet.ud = false;
        jiApplet.ue = null;
        jiApplet.uf = new c("jiApplet1");
        jiApplet.ug = 0;
        jiApplet.uh = 0;
        u1 = new Object();
        u3 = new Object();
        jiApplet.u4 = true;
    }
    
    class xu implements Runnable
    {
        boolean a;
        
        public xu(final boolean a) {
            this.a = true;
            this.a = a;
        }
        
        public void run() {
            try {
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                    ji.io.h.d(jiApplet.this.uz, "OpenDoc synchronizing...");
                }
                if (jiApplet.u4) {
                    synchronized (jiApplet.this.u0) {
                        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(jiApplet.this.uz, "OpenDoc synchronized");
                        }
                        jiApplet.this.d(this.a);
                        if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                            ji.io.h.d(jiApplet.this.uz, "OpenDoc complete..");
                        }
                        // monitorexit(jiApplet.e(this.b))
                        return;
                    }
                }
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                    ji.io.h.d(jiApplet.this.uz, "OpenDoc pre-start..");
                }
                jiApplet.this.d(this.a);
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36) || ji.util.i.c(131)) {
                    ji.io.h.d(jiApplet.this.uz, "OpenDoc complete..");
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            finally {
                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                    ji.io.h.d(jiApplet.this.uz, "OpenDoc finally complete..");
                }
            }
        }
    }
    
    class ty implements Runnable
    {
        Applet a;
        
        public ty(final Applet a) {
            this.a = null;
            this.a = a;
        }
        
        public void run() {
            try {
                ji.io.h.d(jiApplet.this.uz, "Removing local installation...");
                ji.util.cn.d(this.a.getCodeBase(), this.a, jiApplet.this.uz);
                ji.io.h.d(jiApplet.this.uz, "Removed...");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class tw implements Runnable
    {
        Applet a;
        
        public tw(final Applet a) {
            this.a = null;
            this.a = a;
        }
        
        public void run() {
            jiApplet.this.t8 = ji.util.cn.b(jiApplet.this.getCodeBase(), this.a, jiApplet.this.uz);
        }
    }
    
    class tt implements Runnable
    {
        String a;
        
        public tt(final String a) {
            this.a = null;
            this.a = a;
        }
        
        public void run() {
            try {
                jiApplet.this.l(this.a);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                jiApplet.this.um = null;
            }
        }
    }
    
    private class tv implements Runnable
    {
        jiApplet a;
        private boolean b;
        Object c;
        
        public tv(final jiApplet a) {
            this.b = false;
            this.c = new Object();
            this.a = a;
        }
        
        public void a() {
            if (this.c != null) {
                synchronized (this.c) {
                    this.c.notify();
                }
                // monitorexit(this.c)
            }
        }
        
        public void run() {
            cl cl = null;
            try {
                while (jiApplet.this.r2 && !ji.util.d.x) {
                    while (jiApplet.this.t3.size() > 0) {
                        try {
                            jiApplet.this.tz = true;
                            cl = new cl(jiApplet.this.t3.elementAt(0));
                            this.a(cl);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        finally {
                            jiApplet.this.tz = false;
                        }
                        try {
                            jiApplet.this.t3.removeElementAt(0);
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                        }
                        try {
                            if (cl == null) {
                                continue;
                            }
                            cl.o();
                        }
                        catch (Exception ex4) {}
                    }
                    if (jiApplet.this.r2 && this.c != null) {
                        synchronized (this.c) {
                            Label_0208: {
                                try {
                                    this.c.wait(5000L);
                                    break Label_0208;
                                }
                                catch (InterruptedException ex5) {
                                    // monitorexit(this.c)
                                    continue;
                                }
                            }
                        }
                        break;
                    }
                }
            }
            catch (Exception ex3) {
                ji.util.d.a(ex3);
            }
            catch (Error error) {
                ji.util.d.a(error);
            }
            finally {
                this.a = null;
                synchronized (this.c) {
                    if (ji.util.i.c(131)) {
                        ji.io.h.d(jiApplet.this.uz, "notifyAll monitor");
                    }
                    this.c.notifyAll();
                }
                // monitorexit(this.c)
                jiApplet.this.r3 = false;
                ji.util.d.x = false;
                jiApplet.this.tv = null;
            }
        }
        
        protected void finalize() throws Throwable {
            try {
                if (this.c != null) {
                    synchronized (this.c) {
                        this.b = true;
                        this.c.notify();
                    }
                    // monitorexit(this.c)
                }
            }
            catch (Exception ex) {}
            finally {
                super.finalize();
            }
        }
        
        private final void a(final cl cl) {
            try {
                if (cl != null) {
                    if (ji.util.d.dr() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(jiApplet.this.uz, "Applet processing command: ".concat(String.valueOf(String.valueOf(cl))));
                    }
                    if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                        ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("command ").append(cl).append(" synchronizing..."))));
                    }
                    synchronized (jiApplet.this.b()) {
                        final Object e = jiApplet.this.u0;
                        // monitorenter(e)
                        try {
                            if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                                ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("command = ").append(cl).append(" synchronized"))));
                            }
                            boolean b = !this.b && cl.a != 47;
                            if (ji.document.ad.a) {
                                b = (!this.b || cl.a == 47);
                            }
                            if (b) {
                                if (cl.a == 47) {
                                    if (ji.util.i.c(131)) {
                                        ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("cmdAppletDestroy start ").append(Thread.currentThread().getName()).append(" ").append(jiApplet.this.uz))));
                                    }
                                }
                                else if (cl.a == 46 && ji.util.i.c(131)) {
                                    ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("cmdAppletStop start ").append(Thread.currentThread().getName()).append(" ").append(jiApplet.this.uz))));
                                }
                                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                                    ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("command ").append(cl).append(" starting.."))));
                                }
                                this.b(cl);
                                if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                                    ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("command ").append(cl).append(" complete.."))));
                                }
                                if (cl.a == 47) {
                                    if (ji.util.i.c(131)) {
                                        ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("cmdAppletDestroy end ").append(Thread.currentThread().getName()).append(" ").append(jiApplet.this.uz))));
                                    }
                                }
                                else if (cl.a == 46 && ji.util.i.c(131)) {
                                    ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("cmdAppletStop end ").append(Thread.currentThread().getName()).append(" ").append(jiApplet.this.uz))));
                                }
                            }
                            else if (ji.util.d.cy() || ji.util.d.ap || ji.util.d.ao || ji.util.i.c(36)) {
                                ji.io.h.d(jiApplet.this.uz, String.valueOf(String.valueOf(new StringBuffer("command ").append(cl).append(" not executed because stop already called"))));
                            }
                        }
                        // monitorexit(e)
                        finally {}
                    }
                    // monitorexit(jiApplet.d(this.d))
                    if (ji.util.d.dr() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                        ji.io.h.d(jiApplet.this.uz, "Applet processed command: ".concat(String.valueOf(String.valueOf(cl))));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public void b() {
            if (ji.util.d.cy() || ji.util.i.c(131)) {
                ji.io.h.d(jiApplet.this.uz, "Applet command processor stop request");
            }
            this.b = true;
            this.a();
        }
        
        private final void b(final cl cl) {
            try {
                switch (cl.a) {
                    case 44: {
                        this.a.p();
                        break;
                    }
                    case 45: {
                        this.a.q();
                        break;
                    }
                    case 46: {
                        this.b = true;
                        this.a.al();
                        break;
                    }
                    case 47: {
                        this.a.g(false);
                        break;
                    }
                    case 93: {
                        this.a.k();
                        break;
                    }
                    case 88: {
                        jiApplet.this.h((String)cl.n);
                        jiApplet.this.a(jiApplet.this.getCodeBase(), jiApplet.this.d);
                        if (jiApplet.this.d.d2() != null) {
                            jiApplet.this.a(jiApplet.this.getCodeBase(), jiApplet.this.d.d2());
                            break;
                        }
                        break;
                    }
                    case 89: {
                        jiApplet.this.b(cl.d, cl.e);
                        break;
                    }
                }
            }
            finally {
                if (ji.util.d.cy() || ji.util.d.an || ji.util.d.ao || ji.util.d.dv()) {
                    ji.io.h.d(jiApplet.this.uz, "Processed: ".concat(String.valueOf(String.valueOf(cl))));
                }
            }
        }
    }
    
    class xx implements Runnable
    {
        public void run() {
            ji.util.d.v(jiApplet.this.uz);
        }
    }
    
    class xw implements Runnable
    {
        public void run() {
            if (ji.util.i.c(240)) {
                ji.sec.f.a(this, jiApplet.this.uz);
            }
            try {
                final q a = ji.io.q.a((Object)null, jiApplet.this.uz);
                if (a != null) {
                    a.r();
                }
            }
            catch (Throwable t) {}
        }
    }
}
