import java.net.URLConnection;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.NoSuchElementException;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Container;
import java.net.URL;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import ABLwidgets.edge;
import java.util.Enumeration;
import ABLjemsty.EmuMenuOption;
import ABLjemsty.jemTabPanel;
import java.awt.Rectangle;
import ABLjemsty.jemQuadrantPanel;
import java.awt.Window;
import ABLwidgets.menu_item;
import ABLjemsty.EmuButton;
import ABLjemsty.jemScrollPanel;
import java.awt.Component;
import ABLjemsty.CommandStatusPanel;
import ABLjemsty.EmuRangeVector;
import java.awt.MenuItem;
import ABLwidgets.new_font;
import java.awt.Insets;
import ABLjemsty.EmuRect;
import ABLjemsty.EmuPanel;
import java.util.Date;
import java.util.Random;
import ABLjemsty.StylerField;
import java.awt.Event;
import ABLjemsty.TipTarget;
import ABLjemsty.StyleTarget;
import ABLjemsty.Styler;
import ABLjemsty.Enhanced;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Point;
import ABLjemsty.EmuRange;
import java.awt.Color;
import java.awt.Font;
import java.io.FileOutputStream;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.List;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import ABLwidgets.web_context;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljema extends Thread
{
    int a;
    int b;
    int c;
    private int d;
    abljem e;
    web_context f;
    String g;
    boolean h;
    int i;
    abljema j;
    String k;
    String l;
    boolean m;
    boolean n;
    abljema[] o;
    int p;
    abljemta q;
    char r;
    char s;
    char t;
    boolean u;
    boolean v;
    boolean w;
    String x;
    boolean y;
    boolean z;
    Thread aa;
    boolean ab;
    boolean ac;
    int ad;
    boolean ae;
    boolean af;
    int ag;
    String ah;
    String ai;
    char[] aj;
    boolean[] ak;
    boolean[] al;
    byte[][][] am;
    byte[][][] an;
    int[] ao;
    boolean ap;
    String aq;
    Socket ar;
    DataInputStream as;
    DataOutputStream at;
    String au;
    boolean av;
    boolean aw;
    boolean ax;
    String ay;
    boolean az;
    boolean a0;
    boolean a1;
    String a2;
    boolean a3;
    boolean a4;
    boolean a5;
    char a6;
    char a7;
    String a8;
    boolean a9;
    List ba;
    boolean bb;
    int bc;
    boolean bd;
    boolean be;
    String bf;
    String bg;
    String bh;
    private boolean bi;
    String bj;
    String bk;
    boolean bl;
    abljems bm;
    abljemr bn;
    boolean bo;
    boolean bp;
    boolean bq;
    boolean br;
    boolean bs;
    boolean bt;
    boolean bu;
    boolean bv;
    String bw;
    boolean bx;
    boolean by;
    int bz;
    boolean b0;
    boolean b1;
    boolean b2;
    boolean b3;
    boolean b4;
    abljemtf b5;
    boolean b6;
    boolean b7;
    abljemtf b8;
    boolean b9;
    boolean ca;
    boolean cb;
    boolean cc;
    boolean cd;
    boolean ce;
    boolean cf;
    boolean cg;
    boolean ch;
    boolean ci;
    boolean cj;
    int ck;
    boolean cl;
    boolean cm;
    boolean cn;
    boolean co;
    boolean cp;
    boolean cq;
    Image cr;
    Image cs;
    Image ct;
    Image cu;
    MediaTracker cv;
    int cw;
    int cx;
    int cy;
    int cz;
    boolean c0;
    int c1;
    int c2;
    int c3;
    int c4;
    int c5;
    int c6;
    int c7;
    boolean c8;
    boolean c9;
    boolean da;
    boolean db;
    boolean dc;
    boolean dd;
    boolean de;
    boolean df;
    boolean dg;
    byte dh;
    boolean di;
    String dj;
    String dk;
    byte dl;
    byte[] dm;
    byte[] dn;
    byte[] do;
    byte[] dp;
    int dq;
    int dr;
    int ds;
    int dt;
    int du;
    int dv;
    String dw;
    StringBuffer dx;
    byte[][] dy;
    int[] dz;
    boolean d0;
    int d1;
    FileOutputStream d2;
    String d3;
    String d4;
    String d5;
    String d6;
    String d7;
    String d8;
    String d9;
    boolean ea;
    boolean eb;
    int ec;
    boolean ed;
    String ee;
    char ef;
    Image eg;
    Image eh;
    Image ei;
    Image ej;
    Image ek;
    byte[] el;
    byte[] em;
    byte[] en;
    int[] eo;
    boolean ep;
    int[] eq;
    boolean er;
    int es;
    int et;
    int eu;
    boolean ev;
    boolean ew;
    abljemob[] ex;
    int ey;
    abljemtf[] ez;
    abljemtf[] e0;
    int e1;
    int e2;
    int e3;
    abljemtf e4;
    int e5;
    int e6;
    int e7;
    int e8;
    int e9;
    boolean fa;
    abljemf fb;
    Thread fc;
    char fd;
    char fe;
    int ff;
    abljemtf[] fg;
    String fh;
    int[] fi;
    int[] fj;
    int[] fk;
    int[] fl;
    int[] fm;
    int[] fn;
    int[] fo;
    char[] fp;
    Font[] fq;
    Color[] fr;
    Color[] fs;
    Color[] ft;
    EmuRange[] fu;
    int fv;
    int fw;
    abljemcp fx;
    MediaTracker fy;
    String[] fz;
    Image[] f0;
    abljemtc[] f1;
    int[] f2;
    int[] f3;
    int[] f4;
    int[] f5;
    int[] f6;
    int[] f7;
    int[] f8;
    int[] f9;
    int ga;
    boolean gb;
    boolean gc;
    int gd;
    int ge;
    byte gf;
    byte gg;
    byte[] gh;
    byte[] gi;
    byte[] gj;
    byte[] gk;
    int gl;
    boolean gm;
    boolean gn;
    boolean go;
    boolean gp;
    String gq;
    boolean gr;
    boolean gs;
    boolean gt;
    boolean gu;
    String gv;
    String gw;
    byte[] gx;
    byte[] gy;
    int gz;
    byte[] g0;
    byte[] g1;
    int g2;
    int g3;
    int g4;
    int g5;
    int g6;
    int g7;
    abljemnp g8;
    abljemlb g9;
    Point ha;
    String hb;
    String hc;
    boolean hd;
    boolean he;
    boolean hf;
    boolean hg;
    int hh;
    int hi;
    boolean hj;
    boolean hk;
    boolean hl;
    boolean hm;
    boolean hn;
    boolean ho;
    boolean hp;
    boolean hq;
    boolean hr;
    boolean hs;
    boolean ht;
    boolean hu;
    boolean hv;
    Vector hw;
    Hashtable hx;
    long hy;
    long hz;
    boolean h0;
    Enhanced h1;
    boolean h2;
    boolean h3;
    private Styler h4;
    StyleTarget h5;
    TipTarget h6;
    Event h7;
    byte[] h8;
    byte[] h9;
    StylerField[] ia;
    String ib;
    String ic;
    boolean id;
    public boolean ie;
    private String if;
    private int ig;
    private int ih;
    private Random ii;
    private long ij;
    boolean ik;
    private String il;
    private String im;
    private boolean in;
    String io;
    public String ip;
    public String iq;
    String ir;
    static byte[] is;
    private static char it;
    private static String iu;
    static String iv;
    static String iw;
    static String[] ix;
    
    abljema(final abljem e, final String s, final String s2) {
        super(String.valueOf(s2) + "abljema");
        this.a = 10;
        this.b = 30;
        this.c = 30;
        this.d = 20;
        this.h = false;
        this.k = "";
        this.l = "";
        this.m = false;
        this.n = true;
        this.r = '0';
        this.s = 'S';
        this.t = '0';
        this.u = false;
        this.v = false;
        this.w = false;
        this.y = false;
        this.z = false;
        this.ab = true;
        this.ac = false;
        this.ae = false;
        this.af = false;
        this.ag = 1;
        this.ai = " - ";
        this.aj = new char[] { 'U', 'A', 'U', 'U', 'U', 'U', 'U', 'U', 'U', 'U' };
        this.ak = new boolean[this.aj.length];
        this.al = new boolean[this.aj.length];
        this.am = new byte[this.aj.length][10][];
        this.an = new byte[this.aj.length][10][];
        this.ao = new int[this.aj.length];
        this.ap = false;
        this.av = false;
        this.aw = false;
        this.ax = false;
        this.a1 = false;
        this.a3 = false;
        this.a4 = false;
        this.a5 = true;
        this.a6 = 'A';
        this.a7 = 'Y';
        this.a9 = true;
        this.bb = false;
        this.bd = false;
        this.be = false;
        this.bf = "";
        this.bi = false;
        this.bj = "*BROWSER";
        this.bk = "%1";
        this.bl = true;
        this.bo = false;
        this.bp = false;
        this.bq = false;
        this.br = false;
        this.bs = false;
        this.bt = false;
        this.bu = false;
        this.bv = false;
        this.bx = false;
        this.by = false;
        this.b0 = false;
        this.b1 = false;
        this.b2 = false;
        this.b3 = false;
        this.b4 = false;
        this.b6 = false;
        this.b7 = false;
        this.b9 = false;
        this.ca = false;
        this.cb = false;
        this.cc = false;
        this.cd = false;
        this.ce = false;
        this.cf = false;
        this.cg = false;
        this.ch = false;
        this.ci = true;
        this.cj = true;
        this.ck = 2;
        this.cl = false;
        this.cm = false;
        this.cn = false;
        this.co = false;
        this.cp = true;
        this.cq = true;
        this.cw = 2;
        this.cx = 3;
        this.cy = 4;
        this.cz = 6;
        this.c0 = false;
        this.c1 = 2;
        this.c4 = 1;
        this.c5 = 2;
        this.c6 = 3;
        this.c7 = 4;
        this.c8 = false;
        this.c9 = false;
        this.da = true;
        this.db = false;
        this.dc = false;
        this.dd = true;
        this.de = false;
        this.df = false;
        this.dg = false;
        this.dh = 63;
        this.di = false;
        this.dj = "\u00c8\u00e2\u00e3\u00c5\u00d4\u00e4\u00e2\u00e5\u00c3\u00e2\u00e5\u0000\u00d9\u0000\u00d4\u0000";
        this.dk = "\u00d1\u00c5\u00d4\u00c1\u00d7\u00d7\u00d3\u00c5\u00e3@\u00e5\u00f1\u00d9\u00f0\u00d4\u00f0";
        this.dl = 22;
        this.dm = new byte[] { 49, 22, 74, 22 };
        this.dn = new byte[] { 49, 23, 74, 23 };
        this.do = new byte[] { 48, 22, 74, 22 };
        this.dp = new byte[] { 48, 23, 74, 23 };
        this.dq = 8;
        this.dr = 4;
        this.ds = 12;
        this.dt = 3;
        this.du = 9;
        this.dv = 8;
        this.dw = "        ";
        this.dx = new StringBuffer("");
        this.dy = new byte[10][];
        this.dz = new int[10];
        this.d0 = false;
        this.d3 = "<SCREEN>";
        this.d4 = "</SCREEN>";
        this.d5 = "<BODY>";
        this.d6 = "</BODY>";
        this.d7 = "<ASYNC>";
        this.d8 = "</ASYNC>";
        this.ea = true;
        this.eb = false;
        this.ed = false;
        this.fd = '1';
        this.fe = '\0';
        this.fv = -1;
        this.gb = false;
        this.gc = false;
        this.gl = 107;
        this.gm = false;
        this.gn = false;
        this.go = false;
        this.gp = false;
        this.gq = "never";
        this.gr = false;
        this.gs = false;
        this.gt = false;
        this.gu = false;
        this.hd = false;
        this.he = false;
        this.hf = false;
        this.hg = false;
        this.hi = 10;
        this.hj = false;
        this.hk = false;
        this.hl = false;
        this.hm = false;
        this.hn = false;
        this.ho = false;
        this.hp = false;
        this.hq = true;
        this.hr = false;
        this.hs = false;
        this.ht = false;
        this.hu = false;
        this.hv = false;
        this.hx = new Hashtable();
        this.hy = 600000L;
        this.h0 = false;
        this.h2 = true;
        this.h3 = true;
        this.id = false;
        this.ie = false;
        this.ik = false;
        this.il = "/*EVENT=*JEMTUNNEL";
        this.im = "/*TGD=CS";
        this.in = true;
        this.io = "default";
        this.ip = "UniversalSystemClipboardAccess";
        this.iq = "UniversalFileAccess";
        this.j = this;
        this.e = e;
        (this.o = new abljema[10])[0] = this.j;
        int index = s.indexOf(47);
        ++index;
        this.g = s.substring(index);
        this.a2 = "Failed - see Java Console for detail";
        this.h1 = new Enhanced();
        this.x();
    }
    
    abljema(final abljema j, final int i, final String s) {
        super(String.valueOf(s) + "abljema" + i);
        this.a = 10;
        this.b = 30;
        this.c = 30;
        this.d = 20;
        this.h = false;
        this.k = "";
        this.l = "";
        this.m = false;
        this.n = true;
        this.r = '0';
        this.s = 'S';
        this.t = '0';
        this.u = false;
        this.v = false;
        this.w = false;
        this.y = false;
        this.z = false;
        this.ab = true;
        this.ac = false;
        this.ae = false;
        this.af = false;
        this.ag = 1;
        this.ai = " - ";
        this.aj = new char[] { 'U', 'A', 'U', 'U', 'U', 'U', 'U', 'U', 'U', 'U' };
        this.ak = new boolean[this.aj.length];
        this.al = new boolean[this.aj.length];
        this.am = new byte[this.aj.length][10][];
        this.an = new byte[this.aj.length][10][];
        this.ao = new int[this.aj.length];
        this.ap = false;
        this.av = false;
        this.aw = false;
        this.ax = false;
        this.a1 = false;
        this.a3 = false;
        this.a4 = false;
        this.a5 = true;
        this.a6 = 'A';
        this.a7 = 'Y';
        this.a9 = true;
        this.bb = false;
        this.bd = false;
        this.be = false;
        this.bf = "";
        this.bi = false;
        this.bj = "*BROWSER";
        this.bk = "%1";
        this.bl = true;
        this.bo = false;
        this.bp = false;
        this.bq = false;
        this.br = false;
        this.bs = false;
        this.bt = false;
        this.bu = false;
        this.bv = false;
        this.bx = false;
        this.by = false;
        this.b0 = false;
        this.b1 = false;
        this.b2 = false;
        this.b3 = false;
        this.b4 = false;
        this.b6 = false;
        this.b7 = false;
        this.b9 = false;
        this.ca = false;
        this.cb = false;
        this.cc = false;
        this.cd = false;
        this.ce = false;
        this.cf = false;
        this.cg = false;
        this.ch = false;
        this.ci = true;
        this.cj = true;
        this.ck = 2;
        this.cl = false;
        this.cm = false;
        this.cn = false;
        this.co = false;
        this.cp = true;
        this.cq = true;
        this.cw = 2;
        this.cx = 3;
        this.cy = 4;
        this.cz = 6;
        this.c0 = false;
        this.c1 = 2;
        this.c4 = 1;
        this.c5 = 2;
        this.c6 = 3;
        this.c7 = 4;
        this.c8 = false;
        this.c9 = false;
        this.da = true;
        this.db = false;
        this.dc = false;
        this.dd = true;
        this.de = false;
        this.df = false;
        this.dg = false;
        this.dh = 63;
        this.di = false;
        this.dj = "\u00c8\u00e2\u00e3\u00c5\u00d4\u00e4\u00e2\u00e5\u00c3\u00e2\u00e5\u0000\u00d9\u0000\u00d4\u0000";
        this.dk = "\u00d1\u00c5\u00d4\u00c1\u00d7\u00d7\u00d3\u00c5\u00e3@\u00e5\u00f1\u00d9\u00f0\u00d4\u00f0";
        this.dl = 22;
        this.dm = new byte[] { 49, 22, 74, 22 };
        this.dn = new byte[] { 49, 23, 74, 23 };
        this.do = new byte[] { 48, 22, 74, 22 };
        this.dp = new byte[] { 48, 23, 74, 23 };
        this.dq = 8;
        this.dr = 4;
        this.ds = 12;
        this.dt = 3;
        this.du = 9;
        this.dv = 8;
        this.dw = "        ";
        this.dx = new StringBuffer("");
        this.dy = new byte[10][];
        this.dz = new int[10];
        this.d0 = false;
        this.d3 = "<SCREEN>";
        this.d4 = "</SCREEN>";
        this.d5 = "<BODY>";
        this.d6 = "</BODY>";
        this.d7 = "<ASYNC>";
        this.d8 = "</ASYNC>";
        this.ea = true;
        this.eb = false;
        this.ed = false;
        this.fd = '1';
        this.fe = '\0';
        this.fv = -1;
        this.gb = false;
        this.gc = false;
        this.gl = 107;
        this.gm = false;
        this.gn = false;
        this.go = false;
        this.gp = false;
        this.gq = "never";
        this.gr = false;
        this.gs = false;
        this.gt = false;
        this.gu = false;
        this.hd = false;
        this.he = false;
        this.hf = false;
        this.hg = false;
        this.hi = 10;
        this.hj = false;
        this.hk = false;
        this.hl = false;
        this.hm = false;
        this.hn = false;
        this.ho = false;
        this.hp = false;
        this.hq = true;
        this.hr = false;
        this.hs = false;
        this.ht = false;
        this.hu = false;
        this.hv = false;
        this.hx = new Hashtable();
        this.hy = 600000L;
        this.h0 = false;
        this.h2 = true;
        this.h3 = true;
        this.id = false;
        this.ie = false;
        this.ik = false;
        this.il = "/*EVENT=*JEMTUNNEL";
        this.im = "/*TGD=CS";
        this.in = true;
        this.io = "default";
        this.ip = "UniversalSystemClipboardAccess";
        this.iq = "UniversalFileAccess";
        this.j = j;
        this.e = j.e;
        this.f = j.f;
        this.i = i;
        (this.o = new abljema[1])[0] = j;
        this.g = j.g;
        this.a2 = "Failed - Style Level " + (i - 1) + " error";
        this.x();
    }
    
    private void x() {
        this.k = this.e.l;
        this.l = this.e.m;
        this.ip = abljemza.authorized(this.ip);
        this.iq = abljemza.authorized(this.iq);
        this.el = new byte[Styler.dd];
        this.h8 = new byte[Styler.dd];
        this.h9 = new byte[Styler.dd];
        this.eo = new int[Styler.dd];
        this.eq = new int[Styler.dd];
        this.es = 80;
        this.et = 24;
        this.eu = this.et * this.es;
        this.ex = new abljemob[200];
        this.ez = new abljemtf[300];
        this.e0 = new abljemtf[300];
        this.ia = new StylerField[300];
        char c = ' ';
        final String a = this.e.a("Dup_Character", "");
        if (a.length() >= 1) {
            c = a.charAt(0);
        }
        if (c <= ' ') {
            c = abljema.it;
        }
        if (c == abljema.iu.charAt(0)) {
            this.ir = abljema.iu;
        }
        this.ir = abljema.iu.replace(abljema.iu.charAt(0), c);
        this.fi = new int[2000];
        this.fj = new int[2000];
        this.fk = new int[2000];
        this.fl = new int[2000];
        this.fk = new int[2000];
        this.fo = new int[2000];
        this.fp = new char[2000];
        this.fq = new Font[2000];
        this.fr = new Color[2000];
        this.fs = new Color[2000];
        this.ft = new Color[2000];
        this.fu = new EmuRange[2000];
        this.fm = new int[2000];
        this.fn = new int[2000];
        this.fz = new String[100];
        this.f0 = new Image[100];
        this.f1 = new abljemtc[100];
        this.f2 = new int[100];
        this.f3 = new int[100];
        this.f4 = new int[100];
        this.f5 = new int[100];
        this.f6 = new int[100];
        this.f7 = new int[100];
        this.f8 = new int[100];
        this.f9 = new int[100];
        this.gh = new byte[7];
        this.gi = new byte[7];
        this.gj = new byte[256];
        this.gk = new byte[256];
        this.g0 = new byte[256];
        this.g1 = new byte[256];
        this.ba = new List(4, false);
        this.ec = (int)(new Date().getTime() / 1000L);
        if (this.i > 0) {
            this.y();
        }
    }
    
    private void y() {
        if (this.i < 1) {
            return;
        }
        final abljema abljema = this.j.o[0];
        if (abljema == null) {
            return;
        }
        this.bf = abljema.bf;
        this.bg = abljema.bg;
        this.bh = abljema.bh;
        this.bi = abljema.bi;
        this.bj = abljema.bj;
        this.bk = abljema.bk;
        this.bl = abljema.bl;
        this.bm = abljema.bm;
        this.bn = abljema.bn;
        this.bo = false;
        this.bp = true;
        this.bq = false;
        this.br = abljema.br;
    }
    
    public void a() {
        if (!this.h2 && !this.av) {
            return;
        }
        this.aw = this.av;
        this.av = !this.av;
        if (this.av) {
            this.fb.hide();
        }
        else {
            this.fb.b(this.fb.ea);
            this.fb.x();
        }
    }
    
    public Point a(int n, int n2) {
        Point point = null;
        try {
            for (EmuPanel emuPanel = this.fb.ap.ao.a(); emuPanel != null; emuPanel = this.fb.ap.ao.b()) {
                for (EmuRect emuRect = emuPanel.l.a(); emuRect != null; emuRect = emuPanel.l.b()) {
                    if (point == null) {
                        point = new Point(emuRect.a, emuRect.b + 1);
                    }
                    if (n >= emuRect.g && n < emuRect.g + emuRect.d) {
                        n -= emuRect.g - emuRect.a;
                        if (n2 >= emuRect.h && n2 < emuRect.h + emuRect.c) {
                            n2 -= emuRect.h - emuRect.b;
                            return new Point(n, n2);
                        }
                        point.setLocation(n, emuRect.b + 1);
                    }
                }
            }
        }
        catch (Exception ex) {
            abljem.d("unstyled_rowcol error=" + ex);
        }
        return point;
    }
    
    private boolean d(final int n) {
        final abljema abljema = new abljema(this.j, n, this.l);
        this.j.o[n] = abljema;
        if (this.bw != null) {
            abljema.b(this.bw);
        }
        String s = this.j.au;
        if (s.length() != 0) {
            s = String.valueOf(s) + ((n == 1) ? "" : (" - Popup Level " + (n - 1)));
        }
        final abljemf fb = new abljemf(s, abljema);
        (abljema.fb = fb).a(this.e);
        try {
            fb.d5 = (Insets)this.j.fb.d5.clone();
        }
        catch (Throwable t) {}
        fb.b(true);
        (abljema.fc = new Thread(fb, String.valueOf(this.l) + "maifra" + n)).start();
        return true;
    }
    
    public void a(final boolean b) {
        for (int i = 0; i <= this.j.p; ++i) {
            try {
                this.j.o[i].fb.a(b);
            }
            catch (Throwable t) {}
        }
    }
    
    public void a(final String s, final char c) {
        this.j.fb.a(s, c);
        if (this.j.p > 0) {
            try {
                this.j.o[this.j.p].fb.a(s, c);
            }
            catch (Throwable t) {}
        }
    }
    
    public String a(final String s) {
        abljemf abljemf = this.j.fb;
        try {
            abljemf = this.o[this.p].fb;
            if (!abljemf.isVisible()) {
                abljemf = this.j.fb;
            }
        }
        catch (Throwable t2) {}
        try {
            if (!abljemf.isVisible()) {
                return "Window not visible";
            }
            abljemf.e0.b(s);
            abljemf.a(null, 'P');
            return null;
        }
        catch (Throwable t) {
            abljem.d("injectKeystrokes failed");
            t.printStackTrace();
            return "injectKeystrokes failed - see Java console";
        }
    }
    
    public abljemf b() {
        abljemf fb = null;
        try {
            int p;
            for (p = 1; p < this.p && this.am[this.ag][p] == null; ++p) {}
            for (int i = p + 1; i <= this.p; ++i) {
                try {
                    this.o[i].fb.hide();
                }
                catch (Throwable t2) {}
            }
            this.p = p;
            fb = this.o[p].fb;
        }
        catch (Throwable t) {
            abljem.d("GuiStyle session level reset failed");
            t.printStackTrace();
        }
        return fb;
    }
    
    public boolean b(final boolean b) {
        if (!b) {
            return true;
        }
        this.ev = true;
        boolean aa = false;
        try {
            if (this.h2) {
                aa = this.aa();
            }
            else if (this.av) {
                this.a();
            }
        }
        catch (Exception ex) {
            aa = false;
            abljem.d("GUIStyle failed: " + ex);
            ex.printStackTrace();
        }
        if (aa) {
            if (!this.av && !this.aw) {
                this.av = true;
                this.fb.hide();
            }
        }
        else {
            for (int i = 1; i <= this.p; ++i) {
                try {
                    this.o[i].fb.hide();
                }
                catch (Throwable t) {}
            }
            this.p = 0;
            this.av = false;
        }
        return aa;
    }
    
    public String c() {
        final long n = System.currentTimeMillis() + this.d * 1000;
        new_font.a();
        while (this.ew && System.currentTimeMillis() < n) {
            a(100L);
        }
        if (this.ew) {
            return "Timed out waiting for display update";
        }
        this.h4 = null;
        this.h3 = true;
        final abljema abljema = this.j.o[0];
        for (int i = 0; i < abljema.e1; ++i) {
            abljema.ez[i].a();
        }
        for (int j = 0; j < abljema.ey; ++j) {
            abljema.ex[j].b = false;
        }
        final abljema abljema2 = this.j.o[1];
        if (abljema2 != null) {
            abljema2.fb.ad = false;
        }
        if (!this.z()) {
            return "Reload failed - see Java Console for detail";
        }
        if (!this.b(true)) {
            return "Reloaded GUIStyle but failed repainting screen - see Java Console for detail";
        }
        return null;
    }
    
    private boolean z() {
        try {
            if (this.h2 && this.h4 == null) {
                this.h4 = new Styler(this.f, this.c, this.e.a("guistyle_load_gsc_paths", true), this.e.a("backslash_escape", true));
                if (this.h4 == null) {
                    return false;
                }
                if (this.h6 == null) {
                    this.h6 = new TipTarget(this.l);
                }
                if (this.h5 == null) {
                    this.h5 = new StyleTarget();
                }
                this.h4.a(this.ib, this.ic, this.id);
            }
            return true;
        }
        catch (Throwable t) {
            abljem.d("GUIStyle preload threw: " + t);
            t.printStackTrace();
        }
        finally {
            this.h3 = false;
        }
        return false;
    }
    
    private boolean aa() {
        if (this.aj[this.ag] == 'E') {
            this.b();
        }
        final abljema abljema;
        final abljemf fb;
        if (this.fb.l && this.p > 0 && (abljema = this.j.o[this.p]) != null && (fb = abljema.fb) != null) {
            final String trim = new String(this.el, 0, 0, this.es).trim();
            final String trim2 = new String(this.el, 0, this.es, this.es).trim();
            String s = "";
            if (trim.length() > 0 && trim2.length() > 0) {
                if (trim.charAt(trim.length() - 1) != '.') {
                    s = String.valueOf(s) + ".";
                }
                s = String.valueOf(s) + " ";
            }
            fb.d(String.valueOf(trim) + s + trim2);
            fb.a(true, false);
            if (fb.aq != null) {
                fb.aq.c(false);
            }
            return true;
        }
        if (this.h2 && this.h3) {
            final long currentTimeMillis = System.currentTimeMillis();
            final long n = currentTimeMillis + this.c * 1000;
            abljem.d("Waiting for Styles to load");
            while (this.h3 && System.currentTimeMillis() < n) {
                a(100L);
            }
            if (this.h3) {
                this.h4 = null;
                abljem.d("GUIStyle aborted - style load timed out");
                abljem.d("Timeout values: beg=" + currentTimeMillis + " wai=" + this.c + " end=" + n + " cur=" + System.currentTimeMillis());
                return false;
            }
            abljem.d("Styles loaded after " + (System.currentTimeMillis() - currentTimeMillis + 1000L) / 1000L + " seconds");
        }
        if (this.h4 == null) {
            return false;
        }
        try {
            this.h4.ad = this.g8.l.getForeground();
            this.h4.ae = this.g8.l.getBackground();
        }
        catch (Exception ex) {
            abljem.d("Styled button color set failed: " + ex);
        }
        try {
            System.arraycopy(this.el, 0, this.h8, 0, this.h8.length);
            for (int i = 0; i < this.fw; ++i) {
                final byte b = (byte)this.fo[i];
                final int n2 = this.fl[i] - 1;
                if (n2 >= 0) {
                    this.h9[n2] = (byte)(((b & 0x1) == 0x1) ? 32 : b);
                }
                for (int j = n2 + 1; j < j + this.fk[i]; ++j) {
                    this.h9[j] = b;
                }
            }
            this.ia = new StylerField[(this.e1 > 0) ? this.e1 : 0];
            for (int k = 0; k < this.e1; ++k) {
                final abljemtf abljemtf = this.ez[k];
                this.ia[k] = new StylerField(abljemtf, abljemtf.ak, abljemtf.ad, abljemtf.ab, abljemtf.ac, abljemtf.ae, abljemtf.ap, abljemtf.av);
                if (abljemtf.ak != 80) {
                    int ad = abljemtf.ad;
                    for (int l = abljemtf.ae; l > 0; --l) {
                        this.h8[ad] = 32;
                        ++ad;
                    }
                }
            }
        }
        catch (Throwable t) {
            abljem.d("Style screen copy failed - " + t);
            t.printStackTrace();
            return false;
        }
        if (!this.h4.a(this.h8, this.h9, this.ia, this.h1, this.et, this.es, this.e5, this.e6, this.h5, this.h6, this.ag, this.ak)) {
            return false;
        }
        this.ax = this.h4.a;
        final byte[] a2 = this.h4.a2;
        final byte[] a3 = this.h4.a3;
        final int[] array = new int[this.fl.length];
        final int[] array2 = new int[this.fl.length];
        final int[] array3 = new int[this.fl.length];
        final char[] array4 = new char[this.fl.length];
        final Font[] array5 = new Font[this.fl.length];
        int n3 = 0;
        try {
            for (int n4 = 0; n4 < this.e1; ++n4) {
                final StylerField stylerField = this.ia[n4];
                if (stylerField.j) {
                    ((abljemtf)stylerField.i).w = true;
                }
                if (stylerField.k) {
                    ((abljemtf)stylerField.i).x = true;
                }
            }
            for (int n5 = 0; n5 < this.e1; ++n5) {
                final abljemtf abljemtf2 = this.ez[n5];
                if (!abljemtf2.x && (!abljemtf2.w || abljemtf2.ad <= 0 || a3[abljemtf2.ad - 1] != 0)) {
                    if (abljemtf2.ad > 0) {
                        a3[abljemtf2.ad - 1] = 64;
                    }
                    int ad2 = abljemtf2.ad;
                    for (int ae = abljemtf2.ae; ae > 0; --ae) {
                        a2[ad2] = this.el[ad2];
                        ++ad2;
                    }
                }
            }
            int n6 = 0;
            int n7 = 0;
            while (n6 < this.fw) {
                array[n7] = this.fl[n6];
                array2[n7] = this.fk[n6];
                array3[n7] = this.fo[n6];
                array4[n7] = '\0';
                array5[n7] = this.h4.b[5].c[array3[n7] - 32];
                final int n8 = this.fl[n6] - 1;
                if (n8 >= 0 && a3[n8] == 64) {
                    array4[n7] = '0';
                }
                Label_1391: {
                    if (n8 >= 0 && a3[n8] == 0) {
                        int n9 = n8 + 1;
                        final int n10 = n9 + this.fk[n6];
                        while (n9 < n10) {
                            if (a3[n9] != 0) {
                                if (a2[n9] == 32) {
                                    ++n9;
                                    break;
                                }
                                if (a2[n9 - 1] != 32) {
                                    ++n9;
                                    break;
                                }
                                break;
                            }
                            else {
                                ++n9;
                            }
                        }
                        if (n9 >= n10) {
                            if (n7 > 0) {
                                final int[] array6 = array2;
                                final int n11 = n7 - 1;
                                array6[n11] += this.fk[n6] + 1;
                                break Label_1391;
                            }
                            array3[n7] = 32;
                        }
                        else if (n7 > 0) {
                            final int n12 = n9 - this.fl[n6];
                            final int[] array7 = array2;
                            final int n13 = n7 - 1;
                            array7[n13] += n12;
                            final int[] array8 = array;
                            final int n14 = n7;
                            array8[n14] += n12;
                            final int[] array9 = array2;
                            final int n15 = n7;
                            array9[n15] -= n12;
                        }
                    }
                    n3 = ++n7;
                }
                ++n6;
            }
        }
        catch (Throwable t2) {
            abljem.d("Style screen retrieve failed - " + t2);
            t2.printStackTrace();
            return false;
        }
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        final int p = this.h4.b[5].ap + 1;
        if (p < 1) {
            return false;
        }
        for (int n16 = p + 1; n16 <= this.j.p; ++n16) {
            try {
                this.j.o[n16].fb.hide();
            }
            catch (Throwable t4) {}
        }
        if (p > this.j.p && this.j.p > 0) {
            this.o[this.j.p].fb.y();
        }
        if (this.o[p] == null) {
            this.d(p);
        }
        this.j.p = p;
        for (int n17 = 1; n17 < p; ++n17) {
            try {
                final abljemf fb2 = this.o[n17].fb;
                if (fb2.l) {
                    fb2.d("Screen detail not retained on styling");
                    fb2.bl = true;
                }
            }
            catch (Throwable t5) {}
        }
        this.h4.b[5].ap = p - 1;
        if (p < 1 || this.j.o[p] == null) {
            return false;
        }
        final abljema abljema2 = this.j.o[0];
        final abljemf fb3 = abljema2.fb;
        final abljema abljema3 = this.j.o[p];
        final abljemf fb4 = abljema3.fb;
        this.af = (this.h4.ab > 0);
        if (this.af && !this.ae) {
            this.bn.a(this.h4.ab);
        }
        fb4.b(this.h4.b[5].ah);
        try {
            for (int n18 = 0; n18 < this.e1; ++n18) {
                final StylerField stylerField2 = this.ia[n18];
                final abljemtf abljemtf3 = (abljemtf)stylerField2.i;
                abljemtf3.c();
                if (stylerField2.l != null && stylerField2.m != null) {
                    abljemtf3.a(stylerField2.l, stylerField2.m, stylerField2.n);
                }
                if (stylerField2.p != null) {
                    abljemtf3.a(stylerField2.o, stylerField2.p, stylerField2.q, stylerField2.r, stylerField2.s);
                }
                if (stylerField2.t != null) {
                    abljemtf3.bt = stylerField2.t;
                }
                if (stylerField2.u != null) {
                    abljemtf3.a(stylerField2.u, stylerField2.v, stylerField2.w);
                }
                abljemtf3.s = stylerField2.z;
            }
        }
        catch (Throwable t3) {
            abljem.d("Style screen field update failed - " + t3);
            t3.printStackTrace();
            return false;
        }
        abljema3.ew = true;
        fb4.f();
        this.h5.a = fb4;
        ((abljemf)(this.h6.b = fb4)).a();
        final jemQuadrantPanel ap = fb4.ap;
        fb4.ap = this.h4.b[5];
        abljema3.ed = (fb4.bn == '1');
        abljema3.fd = ((abljema3.fe == '\0') ? this.h4.b[5].ai : abljema3.fe);
        fb4.c3 = this.h4.av;
        fb4.c2 = this.h4.aw;
        fb4.a = null;
        if (this.h4.ap != null) {
            fb4.a = (abljemtf)this.h4.ap.i;
        }
        fb4.e = this.h4.al;
        fb4.f = this.h4.an;
        fb4.g = this.h4.am;
        fb4.h = new MenuItem[(fb4.g == null) ? 0 : fb4.g.length];
        for (int n19 = 0; n19 < fb4.h.length; ++n19) {
            fb4.h[n19] = new MenuItem(fb4.g[n19]);
        }
        fb4.bo = this.h4.u;
        fb4.az = this.h4.n;
        if (this.h4.c != null && this.h4.c.length() > 0) {
            fb4.b(this.h4.c);
        }
        else {
            fb4.b(fb4.m);
        }
        if (this.h4.d != null && this.h4.d.length() > 0) {
            fb4.cd = this.h4.d;
        }
        else {
            fb4.cd = null;
        }
        fb4.ce = new EmuRangeVector();
        fb4.cf = this.h4.v;
        fb4.t = this.h4.x;
        fb4.u = this.h4.y;
        fb4.b8 = null;
        if (this.h4.aa != null) {
            (fb4.b8 = new Color[2])[0] = this.h4.aa[0];
            fb4.b8[1] = this.h4.aa[1];
        }
        abljema3.ee = abljema2.ee;
        abljema3.ef = this.h4.af;
        abljema3.eg = this.h4.ag;
        abljema3.eh = this.h4.ah;
        abljema3.ei = this.h4.ai;
        abljema3.ej = this.h4.aj;
        abljema3.ek = this.h4.ak;
        if (fb4.bu == null) {
            fb4.bu = fb4.bx;
        }
        fb4.setBackground(fb4.bx = fb4.ap.getBackground());
        if (fb4.bw == null) {
            fb4.bw = fb4.b0;
            fb4.bv = fb4.by;
            fb4.cc = fb4.cb;
        }
        if (fb4.ap.g != null && fb4.ap.h != null) {
            fb4.b0 = fb4.ap.g;
            fb4.by = fb4.ap.h;
            fb4.cb = true;
        }
        else {
            fb4.b0 = fb4.bw;
            fb4.by = fb4.bv;
            fb4.cb = fb4.cc;
        }
        fb4.a(fb4.ap.m, fb4.ap.n);
        String v = fb4.ap.u;
        if (v != null && (v.equalsIgnoreCase("None") || v.equalsIgnoreCase("Fixed"))) {
            v = null;
        }
        if (v == null) {
            v = fb3.v;
        }
        if (!v.equals(fb4.v)) {
            fb4.v = v;
            b4 = true;
        }
        if (!((ap != null && ap.v != null) ? ap.v : "!NOT!SET!").equals((fb4.ap != null && fb4.ap.v != null) ? fb4.ap.v : "!NOT!SET!")) {
            b4 = true;
        }
        if (((ap != null) ? ap.z : 0) != fb4.ap.z) {
            b4 = true;
        }
        if (!fb4.z) {
            fb4.bm = (fb4.ap.aa == '1');
        }
        char p2 = '0';
        int q = 48;
        if (fb4.ap.p == '0' || fb4.ap.p == '1') {
            p2 = fb4.ap.p;
        }
        if (fb4.ap.q == '0' || fb4.ap.q == '1') {
            q = fb4.ap.q;
        }
        if (fb4.aq == null) {
            fb4.aq = new CommandStatusPanel();
            fb4.aq.i = fb4.d;
            fb4.aq.c(this.e.a("Command_Prompt", (String)null));
            fb4.add(fb4.aq);
        }
        fb4.aq.w = null;
        fb4.aq.a(this.h4.i);
        fb4.aq.a("");
        fb4.aq.b(this.h4.e);
        abljemtf a4 = null;
        if (this.h4.j >= 0 && this.h4.j < this.ia.length) {
            try {
                a4 = ((abljemtf)this.ia[this.h4.j].i).a(abljema3, 0, 0);
                a4.a0 = false;
            }
            catch (Throwable t6) {
                a4 = null;
                abljem.d("Command Field setup failed");
            }
            if (a4 != null) {
                a4.b = true;
                final int n20 = (a4.ab - 1) * abljema2.es + (a4.ac - 1);
                if (this.h4.a6 >= n20 && this.h4.a6 <= n20 + a4.ae) {
                    a4.a = true;
                }
            }
        }
        fb4.aq.a(a4);
        fb4.aq.a(this.j.bb);
        for (int n21 = 0; n21 < 32; ++n21) {
            Color black = fb4.ap.e[n21];
            if (black == null) {
                black = Color.black;
            }
            fb4.b3[n21] = black;
            fb4.b2[n21] = black;
            Color white = fb4.ap.g[n21];
            if (white == null) {
                white = Color.white;
            }
            fb4.b1[n21] = white;
        }
        fb4.ar = null;
        fb4.de = fb4.ap.b;
        final Insets b5 = fb4.ap.b();
        if (b5 != null && (b5.left != 0 || b5.right != 0 || b5.top != 0 || b5.bottom != 0)) {
            fb4.ar = b5;
        }
        fb4.c9 = this.h4.k;
        fb4.db = null;
        fb4.da = null;
        final Insets a5 = fb4.c9.a();
        if (a5 != null && (a5.left != 0 || a5.right != 0 || a5.top != 0 || a5.bottom != 0) && fb4.c9.e != null) {
            fb4.db = a5;
            fb4.da = fb4.c9.e;
        }
        fb4.dd = 0;
        fb4.dc = null;
        if (fb4.c9.b > 0 && fb4.c9.a != null) {
            fb4.dd = fb4.c9.b;
            fb4.dc = fb4.c9.a;
        }
        fb4.cg = this.h4.aq;
        fb4.ch = this.h4.ar;
        fb4.ci = this.h4.as;
        fb4.cj = this.h4.at;
        fb4.ck = this.h4.au;
        fb4.cl = this.h4.ax;
        fb4.cm = this.h4.ay;
        fb4.cn = this.h4.az;
        fb4.co = this.h4.a0;
        fb4.cp = this.h4.a1;
        abljema3.et = 2;
        abljema3.es = 10;
        for (EmuPanel emuPanel = fb4.ap.ao.a(); emuPanel != null; emuPanel = fb4.ap.ao.b()) {
            if (emuPanel.b + emuPanel.c - 1 > abljema3.es) {
                abljema3.es = emuPanel.b + emuPanel.c - 1;
            }
            if (emuPanel.a + emuPanel.d - 1 > abljema3.et) {
                abljema3.et = emuPanel.a + emuPanel.d - 1;
            }
        }
        if (this.h4.l > abljema3.et) {
            abljema3.et = this.h4.l;
        }
        if (this.h4.m > abljema3.es) {
            abljema3.es = this.h4.m;
        }
        abljema3.eu = abljema3.et * abljema3.es;
        if (p == 1) {
            final int n22 = (this.h4.l > 0) ? this.h4.l : abljema2.et;
            final int n23 = (this.h4.m > 0) ? this.h4.m : abljema2.es;
            if (abljema3.et < n22) {
                abljema3.et = n22;
            }
            if (abljema3.es < n23) {
                abljema3.es = n23;
            }
            abljema3.eu = abljema3.et * abljema3.es;
            if (fb4.t != 1) {
                if (!b4) {
                    b4 = (fb4.w != n22 || fb4.x != n23);
                }
                fb4.w = n22;
                fb4.x = n23;
            }
            else {
                if (!b4) {
                    b4 = (fb4.w != abljema3.et || fb4.x != abljema3.es);
                }
                fb4.w = abljema3.et;
                fb4.x = abljema3.es;
            }
        }
        else {
            if (!b4) {
                b4 = (fb4.w != abljema3.et || fb4.x != abljema3.es);
            }
            fb4.w = abljema3.et;
            fb4.x = abljema3.es;
        }
        if (abljema3.et * abljema3.es > Styler.dd) {
            abljem.d("Styled screen " + abljema3.et + "*" + abljema3.es + " too big for " + Styler.dd + " arrays");
            return false;
        }
        try {
            if (fb4.ap.ao.a().l.a().c < 1) {
                abljem.d("GUISyle first rectangle not set");
                return false;
            }
        }
        catch (Exception ex4) {
            abljem.c("GUIStyle null ");
            if (fb4 == null) {
                abljem.d("f1");
            }
            else if (fb4.ap == null) {
                abljem.d("f1.emuqdr");
            }
            else if (fb4.ap.ao == null) {
                abljem.d("f1.emuqdr.emp (no panels defined)");
            }
            else if (fb4.ap.ao.a() == null) {
                abljem.d("f1.emuqdr.emp.first() (no panels defined)");
            }
            else if (fb4.ap.ao.a().l == null) {
                abljem.d("f1.emuqdr.emp.first().emr (no rectangles in first panel)");
            }
            else if (fb4.ap.ao.a().l.a() == null) {
                abljem.d("f1.emuqdr.emp.first().emr.first() (no rectangles in first panel)");
            }
            else {
                abljem.d("but f1.emuqdr.emp.first().emr.first() ok!");
            }
            return false;
        }
        int a6 = this.a('B', p, fb4, this.a('2', p, fb4, this.a('H', p, fb4, this.a('A', p, fb4, this.a('T', p, fb4, b4))))) ? 1 : 0;
        fb4.c8 = this.h4.ac;
        for (int n24 = 1; n24 <= 9; ++n24) {
            if (n24 != 5 && fb4.df[n24] != this.h4.b[n24]) {
                this.a(fb4, n24);
                this.a(fb4, n24, this.h4.b[n24], p);
                a6 = 1;
            }
        }
        final Rectangle bounds = fb4.getBounds();
        final boolean b6 = this.h4.z == null && fb4.e();
        if (p == 1 && this.h4.w) {
            fb4.d();
            if (!b6) {
                fb4.ad = false;
                if (fb4.t == 4) {
                    fb4.ae = true;
                }
            }
        }
        if (p > 1 && !fb4.cf && a6 != 0) {
            fb4.hide();
        }
        if (p > 1 && !fb4.cf) {
            a6 = 1;
        }
        if (a6 != 0) {
            fb4.k();
        }
        else {
            fb4.l();
        }
        fb4.ae = false;
        if (p == 1 && fb4.t == 3) {
            fb4.a0 = true;
        }
        if (p == 1 && fb4.t == 4 && this.h4.z != null) {
            int x = this.h4.z.x;
            int y = this.h4.z.y;
            int n25 = this.h4.z.width;
            int n26 = this.h4.z.height;
            if (n25 == 0) {
                n25 = fb4.bounds().width;
            }
            if (n26 == 0) {
                n26 = fb4.bounds().height;
            }
            if (n25 > fb4.p) {
                n25 = fb4.p;
            }
            if (n26 > fb4.q) {
                n26 = fb4.q;
            }
            if (x + n25 > fb4.p) {
                x = (fb4.p - n25) / 2;
            }
            if (y + n26 > fb4.q) {
                y = (fb4.q - n26) / 2;
            }
            fb4.a(x, y, n25, n26);
            fb4.l();
        }
        else if (p == 1 && fb4.t == 4 && this.h4.w && !b6) {
            fb4.ad = false;
            fb4.a(bounds.x, bounds.y, bounds.width, bounds.height);
            fb4.l();
        }
        abljema.iv.getBytes(0, abljema3.el.length, abljema3.el, 0);
        abljema3.ey = 0;
        abljema3.e1 = 0;
        abljema3.e2 = 0;
        int a7 = 0;
        abljema3.e5 = 1;
        abljema3.e6 = 1;
        abljema3.fa = true;
        abljema3.e7 = this.h4.f;
        abljema3.e8 = this.h4.g;
        abljema3.e9 = this.h4.h;
        jemScrollPanel[] cz = fb4.cz;
        if (cz == null) {
            cz = new jemScrollPanel[0];
        }
        fb4.cz = new jemScrollPanel[this.h4.o];
        for (EmuPanel emuPanel2 = fb4.ap.ao.a(); emuPanel2 != null; emuPanel2 = fb4.ap.ao.b()) {
            char m = p2;
            int j2 = q;
            if (emuPanel2.i == '0' || emuPanel2.i == '1') {
                m = emuPanel2.i;
            }
            if (emuPanel2.j == '0' || emuPanel2.j == '1') {
                j2 = emuPanel2.j;
            }
            if (emuPanel2.ac != null) {
                final jemTabPanel ac = emuPanel2.ac;
                this.a('S', p, fb4, false, ac);
                if (fb4.cw != null) {
                    b3 = true;
                    fb4.cw.d();
                    fb4.cw.a = emuPanel2.a - ac.d;
                    fb4.cw.b = emuPanel2.b;
                }
            }
            for (EmuRect emuRect = emuPanel2.l.a(); emuRect != null; emuRect = emuPanel2.l.b()) {
                if (emuPanel2.m == null) {
                    emuPanel2.m = Color.white;
                    abljem.d("Normal background defaulted to white");
                }
                if (emuPanel2.o == null) {
                    emuPanel2.o = Color.white;
                    abljem.d("Normal reverse defaulted to white");
                }
                if (emuPanel2.n == null) {
                    emuPanel2.n = Color.white;
                    abljem.d("Hilite background defaulted to white");
                }
                if (emuPanel2.p == null) {
                    emuPanel2.p = Color.white;
                    abljem.d("Hilite reverse defaulted to white");
                }
                if (emuPanel2.s == null) {
                    emuPanel2.s = Color.white;
                    abljem.d("Selection background defaulted to white");
                }
                Color color = null;
                if (emuRect.i == 'H') {
                    if (!emuPanel2.p.equals(emuPanel2.n)) {
                        color = emuPanel2.p;
                    }
                }
                else if (!emuPanel2.o.equals(emuPanel2.m)) {
                    color = emuPanel2.o;
                }
                char j3 = m;
                int k2 = j2;
                if (emuRect.j == '0' || emuRect.j == '1') {
                    j3 = emuRect.j;
                }
                if (emuRect.k == '0' || emuRect.k == '1') {
                    k2 = emuRect.k;
                }
                if (emuRect.l != null) {
                    this.a(fb4, emuRect.l, p, cz);
                    emuRect.l.g = 0;
                    fb4.c0 = false;
                }
                if (emuRect.a >= 1) {
                    if (emuRect.b >= 1) {
                        if (this.h4.a4 >= emuRect.a && this.h4.a4 < emuRect.a + emuRect.d && this.h4.a5 >= emuRect.b && this.h4.a5 < emuRect.b + emuRect.c) {
                            abljema3.e5 = this.h4.a4 + emuRect.g - emuRect.a;
                            abljema3.e6 = this.h4.a5 + emuRect.h - emuRect.b;
                            abljema3.fa = this.h4.a7;
                        }
                        for (EmuRange emuRange = this.h4.s.a(); emuRange != null; emuRange = this.h4.s.b()) {
                            if (emuRange.a >= emuRect.a) {
                                if (emuRange.a < emuRect.a + emuRect.d) {
                                    if (emuRange.b >= emuRect.b) {
                                        if (emuRange.b < emuRect.b + emuRect.c) {
                                            fb4.ce.b(emuRange.a(emuRect.g - emuRect.a, emuRect.h - emuRect.b, abljema3.es));
                                        }
                                    }
                                }
                            }
                        }
                        for (int a8 = emuRect.a, g = emuRect.g; a8 < emuRect.a + emuRect.d; ++a8, ++g) {
                            final int n27 = (a8 - 1) * abljema2.es + (emuRect.b - 1);
                            final int n28 = n27 + emuRect.c - 1;
                            final int n29 = emuRect.g + (a8 - emuRect.a);
                            final int n30 = (g - 1) * abljema3.es + (emuRect.h - 1);
                            if (n27 < 0 || n27 + emuRect.c >= a2.length) {
                                abljem.d("Invalid 'from' rectangle " + emuRect.a + "," + emuRect.b + " " + emuRect.c + "x" + emuRect.d + " (" + n27 + "<0 || " + n27 + "+" + emuRect.c + ">=" + a2.length + ")");
                            }
                            if (n30 < 0 || n30 + emuRect.c >= abljema3.el.length) {
                                abljem.d("Invalid 'to' rectangle " + emuRect.g + "," + emuRect.h + " " + emuRect.c + "x" + emuRect.d + " (" + n30 + "<0 || " + n30 + "+" + emuRect.c + ">=" + abljema3.el.length + ")");
                            }
                            System.arraycopy(a2, n27, abljema3.el, n30, emuRect.c);
                            for (int n31 = 0; n31 < n3; ++n31) {
                                final int n32 = array[n31];
                                final int n33 = n32 + array2[n31] - 1;
                                if (n32 <= n28) {
                                    if (n33 >= n27) {
                                        int n34 = n32 - n27;
                                        if (n34 < 0) {
                                            n34 = 0;
                                        }
                                        final int n35 = array3[n31];
                                        abljema3.e(a7);
                                        abljema3.fi[a7] = n29;
                                        abljema3.fj[a7] = emuRect.h + n34;
                                        abljema3.fl[a7] = n30 + n34;
                                        abljema3.fk[a7] = Math.min(n33, n28) - Math.max(n32, n27) + 1;
                                        abljema3.fo[a7] = n35;
                                        abljema3.fp[a7] = ((array4[n31] == '\0') ? j3 : array4[n31]);
                                        abljema3.fq[a7] = array5[n31];
                                        if (emuRect.i == 'H' && emuPanel2.q != null) {
                                            abljema3.ft[a7] = emuPanel2.q;
                                        }
                                        if ((n35 & 0x1) != 0x0 && (n35 & 0x7) != 0x7) {
                                            if (color != null) {
                                                abljema3.fr[a7] = color;
                                            }
                                            else if (k2 == 49) {
                                                abljema3.fr[a7] = fb4.b1[n35 - 32];
                                            }
                                            abljema3.fs[a7] = abljema3.fr[a7];
                                        }
                                        a7 = this.a(abljema3, a7, fb4);
                                        ++a7;
                                    }
                                }
                            }
                        }
                        abljema3.fw = a7;
                        if (this.h4.ao != null) {
                            if (abljema3.ex == null || abljema3.ex.length != this.h4.ao.length) {
                                abljema3.ex = new abljemob[this.h4.ao.length];
                            }
                            for (int n36 = 0; n36 < this.h4.ao.length; ++n36) {
                                final EmuMenuOption emuMenuOption = this.h4.ao[n36];
                                if (emuMenuOption != null) {
                                    final int b7 = emuMenuOption.b;
                                    if (b7 >= emuRect.a) {
                                        if (b7 < emuRect.a + emuRect.d) {
                                            int c = emuMenuOption.c;
                                            if (c >= emuRect.b) {
                                                if (c < emuRect.b + emuRect.c) {
                                                    c -= 3;
                                                    final abljemob abljemob = new abljemob(abljema3, b7 + emuRect.g - emuRect.a, c + emuRect.h - emuRect.b, emuMenuOption.h, emuMenuOption.g);
                                                    abljemob.m = emuMenuOption.d;
                                                    this.h4.ao[n36] = null;
                                                    abljemob.hide();
                                                    if (emuPanel2.m != null) {
                                                        abljemob.setBackground(emuPanel2.m);
                                                    }
                                                    abljema3.ex[abljema3.ey++] = abljemob;
                                                    if (!abljema3.bu || !abljema3.he) {
                                                        abljema3.fb.add(abljemob);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            if (abljema3.ex == null || abljema3.ex.length != abljema2.ey) {
                                abljema3.ex = new abljemob[abljema2.ey];
                            }
                            for (int n37 = 0; n37 < abljema2.ey; ++n37) {
                                final abljemob abljemob2 = abljema2.ex[n37];
                                if (abljemob2.c >= emuRect.a) {
                                    if (abljemob2.c < emuRect.a + emuRect.d) {
                                        if (abljemob2.d >= emuRect.b) {
                                            if (abljemob2.d < emuRect.b + emuRect.c) {
                                                if (!abljemob2.b) {
                                                    final abljemob a9 = abljemob2.a(abljema3, emuRect.g - emuRect.a, emuRect.h - emuRect.b);
                                                    if (a9 != null) {
                                                        a9.h = 0;
                                                        a9.hide();
                                                        if (emuPanel2.m != null) {
                                                            a9.setBackground(emuPanel2.m);
                                                        }
                                                        abljema3.ex[abljema3.ey++] = a9;
                                                        if (!abljema3.bu || !abljema3.he) {
                                                            abljema3.fb.add(a9);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        final Enumeration<EmuButton> elements = (Enumeration<EmuButton>)this.h4.t.elements();
                        while (elements.hasMoreElements()) {
                            final EmuButton emuButton = elements.nextElement();
                            if (emuButton.a >= emuRect.a) {
                                if (emuButton.a >= emuRect.a + emuRect.d) {
                                    continue;
                                }
                                if (emuButton.b < emuRect.b) {
                                    continue;
                                }
                                if (emuButton.b >= emuRect.b + emuRect.c) {
                                    continue;
                                }
                                if (emuButton.m) {
                                    continue;
                                }
                                emuButton.a(emuRect.g - emuRect.a, emuRect.h - emuRect.b);
                                if (abljema3.bu) {
                                    continue;
                                }
                                abljema3.fb.add(emuButton);
                            }
                        }
                        for (int n38 = 0; n38 < abljema2.e1; ++n38) {
                            final abljemtf abljemtf4 = abljema2.ez[n38];
                            if (abljemtf4.ab >= emuRect.a) {
                                if (abljemtf4.ab < emuRect.a + emuRect.d) {
                                    if (abljemtf4.ac >= emuRect.b) {
                                        if (abljemtf4.ac < emuRect.b + emuRect.c) {
                                            if (!abljemtf4.u) {
                                                final abljemtf a10 = abljemtf4.a(abljema3, emuRect.g - emuRect.a, emuRect.h - emuRect.b);
                                                a10.z = emuPanel2;
                                                a10.j = ((emuRect.i == 'H') ? emuPanel2.n : emuPanel2.m);
                                                final edge ah = emuPanel2.ah;
                                                a10.a(ah.j > 0, ah.f, ah.g, ah.h, ah.i);
                                                a10.a0 = false;
                                                a10.e();
                                                a10.ax = 0;
                                                a10.hide();
                                                abljema3.ez[abljema3.e1++] = a10;
                                                if (emuPanel2.v > 0 && emuRect.i == 'S') {
                                                    a10.w = true;
                                                    if (abljema3.e5 == a10.ab && abljema3.e6 >= a10.ac && abljema3.e6 <= a10.ac + a10.ae) {
                                                        abljema3.e5 = 1;
                                                        abljema3.e6 = 1;
                                                        abljema3.fa = true;
                                                    }
                                                }
                                                else if (!a10.w && !a10.h) {
                                                    if (a10.az != 0) {
                                                        a10.az = ++abljema3.e2;
                                                        abljema3.e0[a10.az] = a10;
                                                    }
                                                    if (!abljema3.bu) {
                                                        abljema3.fb.add(a10);
                                                    }
                                                }
                                                if (a10.k != null) {
                                                    final abljemfb abljemfb = new abljemfb(abljema3, a10.k, a10.l, a10, a10.m);
                                                    if (!abljema3.bu) {
                                                        abljema3.fb.add(abljemfb);
                                                    }
                                                }
                                                if (a10.f != null) {
                                                    final abljemtf[] b8 = a10.b();
                                                    final Enhanced.Selfield f = a10.f;
                                                    if (f.h == 'B') {
                                                        try {
                                                            final jemTabPanel a11 = this.h4.a(p);
                                                            this.a('M', p, fb4, false, a11);
                                                            a10.p = a11;
                                                            a11.c = f.o;
                                                            for (int c2 = 0; c2 < f.o; ++c2) {
                                                                final Enhanced.ChoiceDetail choiceDetail = f.s[c2];
                                                                if (choiceDetail.b) {
                                                                    a11.c = c2;
                                                                }
                                                                a11.a(new menu_item(choiceDetail.g, "MBRACT:" + abljema.iv.substring(0, c2) + "S" + abljema.iv.substring(0, f.o - c2 - 1) + e(choiceDetail.r, 4), null));
                                                            }
                                                            a11.d = 1;
                                                            a11.k = emuPanel2;
                                                            a11.l = null;
                                                        }
                                                        catch (Exception ex2) {
                                                            abljem.d("mbrpnl set failed:" + ex2);
                                                            ex2.printStackTrace();
                                                            this.a('M', p);
                                                        }
                                                    }
                                                    if (f.h == 'P') {
                                                        try {
                                                            final abljempu abljempu = new abljempu(abljema3, fb4, 0);
                                                            for (int n39 = 0; n39 < f.o; ++n39) {
                                                                abljempu.a(new abljemf.abljempi(new menu_item(f.s[n39].g, "PDNACT:" + abljema.iv.substring(0, n39) + "S" + abljema.iv.substring(0, f.o - n39 - 1))));
                                                            }
                                                            a10.p = abljempu;
                                                            fb4.cv = abljempu;
                                                        }
                                                        catch (Exception ex3) {
                                                            abljem.d("mnupdn set failed:" + ex3);
                                                            ex3.printStackTrace();
                                                        }
                                                    }
                                                    if (fb4.cu != null && fb4.cu == a10.p) {
                                                        b2 = true;
                                                        fb4.cu.a = a10.ab;
                                                        fb4.cu.b = a10.ac;
                                                        fb4.cu.g = 0;
                                                    }
                                                    if (fb4.cv == null || fb4.cv != a10.p) {}
                                                    for (int n40 = 1; n40 < b8.length; ++n40) {
                                                        final abljemtf abljemtf5 = b8[n40];
                                                        if (abljemtf5.az != 0) {
                                                            abljemtf5.az = ++abljema3.e2;
                                                            abljema3.e0[abljemtf5.az] = abljemtf5;
                                                        }
                                                        if (!abljema3.bu) {
                                                            abljema3.fb.add(abljemtf5);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        fb4.a(emuPanel2, emuRect);
                    }
                }
            }
        }
        abljema3.d();
        if (!b2) {
            this.a('M', p);
        }
        if (!b3) {
            this.a('S', p);
        }
        if (fb4.cu != null && fb4.cv != null) {
            fb4.cu.l = fb4.cv;
            fb4.cv.f = fb4.cu;
        }
        if (a4 != null) {
            final abljemtf abljemtf6 = a4;
            abljema3.ez[abljema3.e1++] = abljemtf6;
            if (abljemtf6.az != 0) {
                abljemtf6.az = ++abljema3.e2;
                abljema3.e0[abljemtf6.az] = abljemtf6;
            }
        }
        if (abljema2.e1 > 0 && !abljema2.ez[0].u) {
            final abljemtf a12 = abljema2.ez[0].a(abljema3, 0, 0);
            a12.hide();
            abljema3.ez[abljema3.e1++] = a12;
        }
        this.a(cz, p);
        if (fb4.cz == null || fb4.cz.length == 0 || fb4.cz[0] == null) {
            fb4.cz = null;
        }
        fb4.bp = this.h4.r;
        fb4.f("*OD");
        abljema3.e3 = 1;
        abljema3.e4 = abljema3.ez[0];
        if (abljema3.e4 != null) {
            abljema3.e4.c(true);
        }
        abljema3.ff = 0;
        abljema3.em = null;
        if (abljema2.em != null) {
            abljema3.em = new byte[abljema2.em.length];
            System.arraycopy(abljema2.em, 0, abljema3.em, 0, abljema3.em.length);
        }
        abljema3.en = null;
        if (abljema2.en != null) {
            abljema3.en = new byte[abljema2.en.length];
            System.arraycopy(abljema2.en, 0, abljema3.en, 0, abljema3.en.length);
        }
        this.ep = false;
        this.er = false;
        abljema3.bs = true;
        abljema3.bt = false;
        abljema3.bu = false;
        abljema3.bv = abljema2.bv;
        try {
            fb4.ad();
        }
        catch (Throwable t7) {}
        abljema3.fb.l = abljema2.fb.l;
        if (this.h7 == null && !abljema3.fb.l && !abljema2.fb.ea && !this.j.ap) {
            abljema3.fb.a("R", 'I');
            abljema3.fb.b(false);
        }
        else {
            abljema3.fb.a(true, false);
        }
        abljema3.ev = true;
        abljema3.ew = false;
        abljema3.fb.show();
        if (this.h7 != null) {
            final Event h7 = this.h7;
            this.h7 = null;
            fb4.b(h7);
        }
        return true;
    }
    
    public void d() {
        for (int i = 0; i < this.e1; ++i) {
            final abljemtf e = this.ez[i];
            if ((e.aq == 77 || e.aq == 76) && i > 0) {
                final abljemtf d = this.ez[i - 1];
                if (d.aq == 70 || d.aq == 77) {
                    e.d = d;
                    d.e = e;
                }
            }
            if (e.aq == 76) {
                abljemtf abljemtf;
                for (abljemtf = e; abljemtf != null && abljemtf.av.length() == 0; abljemtf = (abljemtf)abljemtf.d) {}
                for (abljemtf abljemtf2 = (abljemtf == null) ? null : ((abljemtf)abljemtf.d); abljemtf2 != null; abljemtf2 = (abljemtf)abljemtf2.d) {
                    final String av = abljemtf2.av;
                    final int length = av.length();
                    if (length != abljemtf2.ae) {
                        abljemtf2.a(av.concat(abljema.iv.substring(0, abljemtf2.ae - length)));
                    }
                }
            }
        }
    }
    
    private int a(final abljema abljema, int n, final abljemf abljemf) {
        int n2 = abljema.fl[n];
        int n3 = abljema.fk[n];
        for (EmuRange emuRange = abljemf.ce.a(); emuRange != null; emuRange = abljemf.ce.b()) {
            if (emuRange.c >= n2 && emuRange.c < n2 + n3 && this.j.ax) {
                final int n4 = emuRange.c - n2;
                final int c = emuRange.c;
                int d = emuRange.d;
                final int n5 = emuRange.c + emuRange.d;
                final int n6 = n3 - n4 - d;
                if (c + d > n2 + n3) {
                    abljem.d("rnglen=" + n2 + "+" + n3 + "-" + c + " because " + c + "+" + d + ">" + n2 + "+" + n3);
                }
                if (c + d > n2 + n3) {
                    d = n2 + n3 - c;
                }
                if (n4 > 0 || n6 > 0) {
                    abljema.h(n + 1, n);
                }
                if (n4 > 0 && n6 > 0) {
                    abljema.h(n + 2, n);
                }
                if (n4 > 0) {
                    abljema.fk[n] = n4 - 1;
                    ++n;
                }
                abljema.fi[n] = emuRange.a;
                abljema.fj[n] = emuRange.b;
                abljema.fl[n] = c;
                abljema.fk[n] = d;
                abljema.fu[n] = emuRange;
                switch (emuRange.f) {
                    case 'L': {
                        if (abljemf.ch != null) {
                            abljema.fs[n] = abljemf.ch;
                        }
                        if (abljemf.cg != null) {
                            abljema.ft[n] = abljemf.cg;
                        }
                        if (abljemf.ck > '0') {
                            abljema.fp[n] = ((abljemf.ck == '2') ? '2' : '0');
                            break;
                        }
                        break;
                    }
                    case 'M': {
                        if (abljemf.cm != null) {
                            abljema.fs[n] = abljemf.cm;
                        }
                        if (abljemf.cl != null) {
                            abljema.ft[n] = abljemf.cl;
                        }
                        if (abljemf.cp > '0') {
                            abljema.fp[n] = ((abljemf.cp == '2') ? '2' : '0');
                            break;
                        }
                        break;
                    }
                }
                if (n6 < 1) {
                    break;
                }
                ++n;
                n2 = n5 + 1;
                n3 = n6 - 1;
                abljema.fi[n] = n2 / abljema.es + 1;
                abljema.fj[n] = n2 % abljema.es + 1;
                abljema.fl[n] = n2;
                abljema.fk[n] = n3;
            }
        }
        return n;
    }
    
    private boolean a(final char c, final int n, final abljemf abljemf, final boolean b) {
        return this.a(c, n, abljemf, b, null);
    }
    
    private boolean a(final char c, final int n, final abljemf abljemf, boolean b, jemTabPanel jemTabPanel) {
        try {
            jemTabPanel jemTabPanel2 = null;
            switch (c) {
                case 'T': {
                    jemTabPanel = abljemf.ap.aj;
                    jemTabPanel2 = abljemf.cq;
                    break;
                }
                case 'A': {
                    jemTabPanel = abljemf.ap.ak;
                    jemTabPanel2 = abljemf.cr;
                    break;
                }
                case 'H': {
                    jemTabPanel = abljemf.ap.al;
                    jemTabPanel2 = abljemf.cs;
                    break;
                }
                case '2': {
                    jemTabPanel = abljemf.ap.am;
                    jemTabPanel2 = abljemf.ct;
                    break;
                }
                case 'M': {
                    jemTabPanel2 = abljemf.cw;
                    break;
                }
                case 'S': {
                    jemTabPanel2 = abljemf.cw;
                    break;
                }
                case 'B': {
                    jemTabPanel = abljemf.ap.an;
                    jemTabPanel2 = abljemf.cx;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown panel type in replace");
                }
            }
            if (jemTabPanel != null) {
                jemTabPanel.setBackground(abljemf.getBackground());
            }
            if (jemTabPanel != jemTabPanel2) {
                if (jemTabPanel != null) {
                    jemTabPanel.hide();
                }
                this.a(c, n);
                this.a(c, n, abljemf, jemTabPanel);
                b = true;
            }
            if (jemTabPanel != null) {
                jemTabPanel.g = 0;
            }
        }
        catch (Throwable t) {
            abljem.d("jemtabpnl replace failed");
            t.printStackTrace();
            this.a(c, n);
        }
        return b;
    }
    
    private void a(final char c, final int n) {
        try {
            final abljemf fb = this.j.o[n].fb;
            jemTabPanel jemTabPanel = null;
            switch (c) {
                case 'T': {
                    jemTabPanel = fb.cq;
                    fb.cq = null;
                    break;
                }
                case 'A': {
                    jemTabPanel = fb.cr;
                    fb.cr = null;
                    break;
                }
                case 'H': {
                    jemTabPanel = fb.cs;
                    fb.cs = null;
                    break;
                }
                case '2': {
                    jemTabPanel = fb.ct;
                    fb.ct = null;
                    break;
                }
                case 'M': {
                    jemTabPanel = fb.cu;
                    fb.cu = null;
                    break;
                }
                case 'S': {
                    jemTabPanel = fb.cw;
                    fb.cw = null;
                    break;
                }
                case 'B': {
                    jemTabPanel = fb.cx;
                    fb.cx = null;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown panel type in remove");
                }
            }
            if (jemTabPanel != null) {
                fb.remove(jemTabPanel);
                jemTabPanel.i = -1;
            }
        }
        catch (Throwable t) {
            abljem.d("GUIStyle level " + n + " " + c + "-panel remove failed: " + t);
        }
    }
    
    private void a(final char c, final int n, final abljemf abljemf, final jemTabPanel cx) {
        try {
            switch (c) {
                case 'T': {
                    abljemf.cq = cx;
                    break;
                }
                case 'A': {
                    abljemf.cr = cx;
                    break;
                }
                case 'H': {
                    abljemf.cs = cx;
                    break;
                }
                case '2': {
                    abljemf.ct = cx;
                    break;
                }
                case 'M': {
                    abljemf.cu = cx;
                    break;
                }
                case 'S': {
                    abljemf.cw = cx;
                    break;
                }
                case 'B': {
                    abljemf.cx = cx;
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown panel type in add");
                }
            }
            if (cx == null) {
                return;
            }
            abljemf.add(cx);
            cx.i = n - 1;
        }
        catch (Throwable t) {
            abljem.d("GUIStyle level " + n + " " + c + "-panel add failed: " + t);
        }
    }
    
    private void a(jemScrollPanel[] cz, final int n) {
        try {
            final abljemf fb = this.j.o[n].fb;
            if (cz == null) {
                cz = fb.cz;
                fb.cz = null;
            }
            if (cz == null) {
                return;
            }
            for (int i = 0; i < cz.length; ++i) {
                final jemScrollPanel jemScrollPanel = cz[i];
                if (jemScrollPanel != null) {
                    fb.remove(jemScrollPanel);
                    jemScrollPanel.i = -1;
                }
            }
        }
        catch (Exception ex) {
            abljem.d("GUIStyle level " + n + " scrollbar remove failed: " + ex);
        }
    }
    
    private void a(final abljemf abljemf, final jemScrollPanel jemScrollPanel, final int n, final jemScrollPanel[] array) {
        if (jemScrollPanel == null) {
            return;
        }
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == jemScrollPanel) {
                array[i] = null;
                b = true;
                break;
            }
        }
        for (int j = 0; j < abljemf.cz.length; ++j) {
            if (abljemf.cz[j] == null) {
                abljemf.cz[j] = jemScrollPanel;
                if (!b) {
                    abljemf.add(jemScrollPanel);
                }
                jemScrollPanel.i = n - 1;
                return;
            }
        }
    }
    
    private void a(final abljemf abljemf, final int n) {
        final jemQuadrantPanel jemQuadrantPanel = abljemf.df[n];
        if (jemQuadrantPanel == null) {
            return;
        }
        abljemf.df[n] = null;
        if (jemQuadrantPanel instanceof jemQuadrantPanel && jemQuadrantPanel.a != n) {
            return;
        }
        abljemf.remove(jemQuadrantPanel);
        jemQuadrantPanel.ap = -1;
    }
    
    private void a(final abljemf abljemf, final int n, final jemQuadrantPanel jemQuadrantPanel, final int n2) {
        abljemf.df[n] = jemQuadrantPanel;
        if (jemQuadrantPanel == null) {
            return;
        }
        if (jemQuadrantPanel instanceof jemQuadrantPanel && jemQuadrantPanel.a != n) {
            return;
        }
        abljemf.add(jemQuadrantPanel);
        jemQuadrantPanel.ap = n2 - 1;
    }
    
    public void run() {
        System.currentTimeMillis();
        if (this.i > 0) {
            return;
        }
        Runtime.getRuntime();
        if (this.ip != null && this.cm) {
            abljemgt.f(true);
        }
        if (this.ie && this.ch) {
            this.ac();
        }
        this.z();
        while (this.az) {
            if (this.ga > 0 && this.fb != null) {
                for (int i = 0; i < this.ga; ++i) {
                    if (this.f1[i].c == 0 && this.f1[i].a() > 0) {
                        this.fb.repaint();
                    }
                }
            }
            if (this.d9 != null) {
                this.bn.a(this.d9);
                this.d9 = null;
            }
            try {
                final long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis >= this.hz) {
                    this.hz += this.hy / 10L;
                    synchronized (this.hx) {
                        final Vector<String> vector = new Vector<String>();
                        final Enumeration<String> keys = this.hx.keys();
                        while (keys.hasMoreElements()) {
                            vector.addElement(keys.nextElement());
                        }
                        for (int j = 0; j < vector.size(); ++j) {
                            final String s = vector.elementAt(j);
                            if (currentTimeMillis >= (long)this.hx.get(s)) {
                                this.hx.remove(s);
                            }
                        }
                    }
                    // monitorexit(this.hx)
                }
            }
            catch (Throwable t) {
                if (!this.h0) {
                    this.h0 = true;
                    abljem.d("itmdlt purge error (further errors not logged)");
                    t.printStackTrace();
                }
            }
            this.ec = (int)(new Date().getTime() / 1000L);
            if (!a(1000L)) {
                break;
            }
        }
        if (this.d2 != null) {
            try {
                this.d2.close();
            }
            catch (Throwable t2) {}
        }
        if (!a(1000L)) {
            return;
        }
        if (this.a2 != null) {
            abljem.d(this.a2);
            final abljemem abljemem = new abljemem(this.ee, this.a2);
        }
        if (this.as != null) {
            try {
                this.as.close();
            }
            catch (Throwable t3) {}
        }
        if (this.at != null) {
            try {
                this.at.close();
            }
            catch (Throwable t4) {}
        }
        if (this.ar != null) {
            try {
                this.ar.close();
            }
            catch (Throwable t5) {}
        }
        if (this.bn != null && this.bn.b != null) {
            try {
                this.bn.b.a();
            }
            catch (Throwable t6) {}
        }
        if (this.bg != null) {
            try {
                this.ad();
            }
            catch (Throwable t7) {}
        }
        if (this.g8 != null) {
            try {
                this.g8.dispose();
            }
            catch (Throwable t8) {}
            this.g8 = null;
        }
        for (int k = 1; k < this.o.length; ++k) {
            try {
                if (this.o[k].fc != null) {
                    try {
                        this.o[k].fc.stop();
                        this.o[k].fc = null;
                    }
                    catch (Throwable t9) {}
                }
                try {
                    abljemf.a(this.o[k].fb);
                    this.o[k].fb = null;
                }
                catch (Throwable t10) {}
                try {
                    a(this.o[k]);
                }
                catch (Throwable t11) {}
                try {
                    this.o[k].start();
                }
                catch (Throwable t12) {}
                try {
                    this.o[k] = null;
                }
                catch (Throwable t13) {}
            }
            catch (Throwable t14) {}
        }
        if (this.g9 != null) {
            try {
                this.g9.dispose();
            }
            catch (Throwable t15) {}
            this.g9 = null;
        }
        if (this.fc != null) {
            try {
                this.fc.stop();
            }
            catch (Throwable t16) {}
            this.fc = null;
        }
        if (this.fb != null) {
            try {
                abljemf.a(this.fb);
            }
            catch (Throwable t17) {}
            this.fb = null;
        }
        if (this.h6 != null) {
            this.h6.b = null;
            this.h6 = null;
        }
        if (this.h5 != null) {
            this.h5.a = null;
            this.h5 = null;
        }
        if (this.h4 != null) {
            this.h4.b = null;
            this.h4 = null;
        }
        if (this.aa != null) {
            try {
                this.aa.stop();
            }
            catch (Throwable t18) {}
            this.aa = null;
        }
        if (this.bn != null) {
            if (this.bn.a != null) {
                try {
                    this.bn.a.stop();
                }
                catch (Throwable t19) {}
            }
            try {
                this.bn.stop();
            }
            catch (Throwable t20) {}
            this.bn = null;
        }
        for (int n = 20; this.ie && this.bm != null && this.bm.isAlive() && n > 0; --n) {
            a(1000L);
        }
        if (this.bm != null) {
            try {
                this.bm.stop();
            }
            catch (Throwable t21) {}
            this.bm = null;
        }
        if (this.e != null) {
            this.e.a = null;
            this.e.destroy();
            this.e = null;
        }
        abljemtf.bz = null;
        a(this);
        System.gc();
        abljem.d("End guistyle");
        if (!this.f.a()) {
            System.exit(0);
        }
    }
    
    static void a(final abljema abljema) {
        abljema.cv = null;
        abljema.bn = null;
        abljema.bm = null;
        abljema.j = null;
        abljema.o = null;
        b(abljema);
        abljema.ex = null;
        abljema.ez = null;
        abljema.e0 = null;
        abljema.e4 = null;
        abljema.fg = null;
        if (abljema.fx != null && abljema.fx.getPeer() != null) {
            abljema.fx.removeNotify();
        }
        abljema.fx = null;
        abljema.f1 = null;
        abljema.b5 = null;
        abljema.b8 = null;
    }
    
    static void b(final abljema abljema) {
        try {
            for (int i = 0; i < abljema.e1; ++i) {
                if (abljema.ez[i] != null) {
                    abljema.ez[i].v();
                }
            }
        }
        catch (Throwable t) {}
    }
    
    public void e() {
        this.fb.g("ENT");
    }
    
    public void a(final String s, final String s2) {
        this.fb.h(s);
        abljem.d(String.valueOf(s) + ": " + s2);
    }
    
    public String b(String bw) {
        final String s = " Sun";
        final String s2 = "MSIE ";
        final String s3 = "Mozilla/";
        final String s4 = "WinNT";
        final String s5 = "Windows NT";
        int equalsIgnoreCase = 0;
        boolean b = false;
        boolean b2 = false;
        String s6 = "";
        final String s7 = "HJ";
        final String s8 = "IE";
        final String s9 = "IE5";
        final String s10 = "IE4E";
        final String s11 = "IE4L";
        final String s12 = "IE3";
        final String s13 = "NS";
        final String s14 = "NS6";
        final String s15 = "NS4";
        final String s16 = "NS4B114";
        final String s17 = "NS4W115";
        final String s18 = "NS4W114";
        final String s19 = "NS3";
        if (bw == null) {
            if (!this.e.c) {
                this.e.b("No user agent parameter - applet unlikely to work correctly");
                return null;
            }
            bw = "Application";
        }
        this.bw = bw;
        String property;
        if ((property = System.getProperty("java.vendor")) == null) {
            property = "?";
        }
        String property2;
        if ((property2 = System.getProperty("java.version")) == null) {
            property2 = "0";
        }
        if (property.length() > 4) {
            equalsIgnoreCase = (property.substring(0, 4).equalsIgnoreCase("Sun ") ? 1 : 0);
        }
        if (equalsIgnoreCase != 0 && property2.compareTo("1.3") > 0) {
            equalsIgnoreCase = 0;
            b = true;
        }
        if (equalsIgnoreCase != 0 && this.e.c) {
            equalsIgnoreCase = 0;
        }
        String property3;
        if ((property3 = System.getProperty("os.name")) == null) {
            property3 = "?";
        }
        if (property3.indexOf("Linux") >= 0) {
            b2 = true;
        }
        if (property2.startsWith("1.3")) {
            this.e.b("This applet is not compatible with the 1.3 plugin JVM.");
            this.e.b("Please upgrade your JVM to at least 1.4");
            return "Unsupported Java Version (1.3)";
        }
        String string = "in unknown browser";
        if (this.e.c) {
            string = "running as application";
        }
        if (this.bw.indexOf(s) >= 0) {
            s6 = s7;
            string = "in HotJava";
        }
        else {
            final int index;
            if ((index = this.bw.indexOf(s2)) >= 0) {
                final String substring = this.bw.substring(index + s2.length());
                if (substring.length() >= 1 && substring.charAt(0) >= '5') {
                    s6 = s9;
                    string = "in Explorer 5 + ";
                }
                else if (substring.length() >= 4 && substring.substring(0, 4).equals("4.0;")) {
                    s6 = s10;
                    string = "in early Explorer 4";
                }
                else if (substring.length() >= 1 && substring.charAt(0) == '4') {
                    s6 = s11;
                    string = "in later Explorer 4";
                }
                else if (substring.length() >= 1 && substring.charAt(0) == '3') {
                    s6 = s12;
                    string = "in Explorer 3x";
                }
                else {
                    s6 = s8;
                    string = "in unknown Explorer version:" + this.bw;
                }
            }
            else if (this.bw.length() > s3.length() && this.bw.substring(0, s3.length()).equals(s3)) {
                final String substring2 = this.bw.substring(s3.length());
                if (substring2.length() > 0 && substring2.charAt(0) >= '5') {
                    s6 = s14;
                    string = "in Netscape 6 + ";
                }
                else if (substring2.length() > 0 && substring2.charAt(0) >= '4') {
                    if (property2.compareTo("1.1.4") < 0) {
                        s6 = s16;
                        string = "in Netscape 4 before JVM 1.1.4 patches";
                    }
                    else if (property2.compareTo("1.1.5") >= 0) {
                        s6 = s17;
                        string = "in Netscape 4 with JVM 1.1.5 + ";
                    }
                    else {
                        s6 = s18;
                        string = "in Netscape 4 with JVM 1.1.4 + patches";
                    }
                }
                else {
                    s6 = s19;
                    string = "in Netscape 3-";
                }
            }
        }
        if (this.i == 0) {
            if (!this.e.c) {
                if (b) {
                    this.e.a("Plugin");
                }
                else if (equalsIgnoreCase != 0) {
                    this.e.a("Activator");
                }
                else {
                    this.e.a("Browser");
                }
            }
            abljem.d(" Java version " + property2);
            this.e.b(string);
        }
        this.cp = this.e.a("clear_gc", this.cp);
        this.cq = this.e.a("substring_paint", this.cq);
        if (property2.compareTo("1.1.4") < 0) {
            this.ci = false;
            return "Unsupported Java Version (less than 1.1.4)";
        }
        if (this.bw.indexOf(s4) >= 0 || this.bw.indexOf(s5) >= 0) {
            if (this.i == 0) {
                this.e.b("on NT-based Windows");
            }
            this.bx = true;
        }
        if (s6.startsWith(s7)) {
            this.b1 = true;
            this.ce = true;
            if (equalsIgnoreCase == 0) {
                this.bx = true;
            }
            this.b0 = true;
            this.b3 = true;
            this.b4 = true;
            this.b6 = true;
            this.b7 = true;
            this.b2 = true;
            this.b9 = true;
            this.cl = true;
        }
        else if (b) {
            this.b1 = true;
            this.ce = true;
            this.bx = true;
            this.b0 = true;
            this.b3 = true;
            this.b4 = true;
            this.b6 = true;
            this.b7 = true;
            this.b2 = true;
            this.b9 = true;
        }
        else if (this.e.c) {
            this.b1 = true;
            this.ce = true;
            this.bx = true;
            this.b0 = true;
            this.b3 = true;
            this.b4 = true;
            this.b6 = true;
            this.b7 = true;
            this.b2 = true;
            this.b9 = true;
            this.cl = true;
        }
        else if (s6.startsWith(s8)) {
            this.b1 = true;
            if (s6.startsWith(s12)) {
                this.ck = 3;
                this.cf = true;
                this.cg = true;
                return "Microsoft Internet Explorer v3 not supported by this applet";
            }
            if (s6.compareTo(s9) >= 0 || equalsIgnoreCase != 0) {
                this.ce = true;
            }
            else if (s6.startsWith(s10)) {
                this.ch = true;
            }
            if (equalsIgnoreCase == 0) {
                this.bx = true;
            }
            this.b0 = true;
            this.b3 = true;
            this.b4 = true;
            this.b6 = true;
            this.b7 = true;
            this.b2 = true;
            this.b9 = true;
            if (property2.equals("1.1.4")) {
                this.cn = true;
                this.co = true;
            }
        }
        else if (s6.startsWith(s13)) {
            if (s6.compareTo(s15) >= 0) {
                if (s6.startsWith(s16)) {
                    this.ca = true;
                    this.cc = true;
                    this.cj = false;
                }
                else {
                    if (s6.startsWith(s17)) {
                        this.bx = true;
                    }
                    this.b0 = true;
                    this.b3 = true;
                    this.b4 = true;
                    this.b7 = true;
                    this.cd = true;
                    this.ce = true;
                    this.by = true;
                }
            }
            else if (s6.startsWith(s19)) {
                return "Netscape 3 not supported";
            }
        }
        if (b2) {
            if (this.e.a("lnxfntadj", false)) {
                if (this.i == 0) {
                    abljem.d("Linux font adjustment enabled, may load and run slow");
                }
            }
            else {
                if (this.i == 0) {
                    abljem.d("Linux font adjustment forced to NONE for speed");
                }
                this.fe = '0';
                this.fd = this.fe;
            }
        }
        if (b2) {
            this.ac = true;
        }
        this.ab = this.e.a("blink_cursor", this.ab);
        this.ac = this.e.a("simple_cursor", this.ac);
        if (b2) {
            this.ad = 2;
        }
        final int a = this.e.a("add_descent", 0);
        if (a != 0) {
            this.ad += a;
            abljem.d("addDescent+=" + a + "=" + this.ad);
        }
        if (new abljemtf(this).q()) {
            this.cm = true;
            this.bx = true;
        }
        return null;
    }
    
    public String f() {
        return String.valueOf(this.ea ? "D" : "A") + ((this.ir != null && this.ir.length() > 0) ? ("D" + this.ir.substring(0, 1)) : " #");
    }
    
    public String g() {
        String s = "";
        if (this.bp) {
            s = (this.bo ? "1" : "8");
        }
        return s;
    }
    
    public String h() {
        int n = 0;
        int d1 = 0;
        int n2 = 0;
        final String s = "Record_Screens_To_File";
        final String s2 = "Playback_Screens_From_File";
        final String s3 = "Playback_Screens_From_URL";
        this.d2 = null;
        final String a = this.e.a(s, (String)null);
        final String a2 = this.e.a(s2, (String)null);
        final String a3 = this.e.a(s3, (String)null);
        if (a != null) {
            ++n;
        }
        if (a2 != null) {
            ++n;
        }
        if (a3 != null) {
            ++n;
        }
        if (n > 1) {
            String s4 = "Both ";
            if (a != null) {
                s4 = String.valueOf(s4) + s;
                if (--n > 0) {
                    s4 = String.valueOf(s4) + " and ";
                }
            }
            if (a2 != null) {
                s4 = String.valueOf(s4) + s2;
                if (--n > 0) {
                    s4 = String.valueOf(s4) + " and ";
                }
            }
            if (a3 != null) {
                s4 = String.valueOf(s4) + s3;
                if (--n > 0) {
                    s4 = String.valueOf(s4) + " and ";
                }
            }
            return String.valueOf(s4) + " specified";
        }
        if (a != null) {
            try {
                final File file = new File(a);
                this.d2 = new FileOutputStream(a, true);
            }
            catch (Throwable t) {
                t.printStackTrace();
                return String.valueOf(s) + " " + a + " setup failed";
            }
        }
        if (a2 != null) {
            ++d1;
            try {
                this.a(0, a2);
            }
            catch (Throwable t2) {
                t2.printStackTrace();
                return String.valueOf(s2) + " " + a2 + " read failed";
            }
        }
        if (a3 != null) {
            ++d1;
            try {
                this.b(0, a3);
            }
            catch (Throwable t3) {
                if (t3 instanceof FileNotFoundException) {
                    abljem.d(t3.getMessage());
                }
                else {
                    t3.printStackTrace();
                }
                return String.valueOf(s3) + " " + a3 + " retrieve failed";
            }
        }
        for (int i = 1; i <= 9; ++i) {
            final String string = "ssn_" + i + "_" + s2;
            final String string2 = "ssn_" + i + "_" + s3;
            final String a4 = this.e.a(string, (String)null);
            final String a5 = this.e.a(string2, (String)null);
            final boolean b = a4 != null && a4.equalsIgnoreCase("*LIVE");
            final boolean b2 = a5 != null && a5.equalsIgnoreCase("*LIVE");
            if (b || b2) {
                ++n2;
            }
            if (a4 != null || a5 != null) {
                if (a2 != null) {
                    return "Both " + s2 + " and " + ((a4 == null) ? string2 : string) + " specified";
                }
                if (a3 != null) {
                    return "Both " + s3 + " and " + ((a4 == null) ? string2 : string) + " specified";
                }
                if (a4 != null && a5 != null) {
                    if (!b || !b2) {
                        return String.valueOf(string) + " and " + string2 + " both specified";
                    }
                }
                else if (!b) {
                    if (!b2) {
                        ++d1;
                        if (a4 != null) {
                            try {
                                this.a(i, a4);
                            }
                            catch (Throwable t4) {
                                t4.printStackTrace();
                                return String.valueOf(string) + " " + a4 + " read failed";
                            }
                        }
                        if (a5 != null) {
                            try {
                                this.b(i, a5);
                            }
                            catch (Throwable t5) {
                                if (t5 instanceof FileNotFoundException) {
                                    abljem.d(t5.getMessage());
                                }
                                else {
                                    t5.printStackTrace();
                                }
                                return String.valueOf(string2) + " " + a5 + " retrieve failed";
                            }
                        }
                    }
                }
            }
        }
        this.d1 = d1;
        this.d0 = (d1 > 0 && n2 == 0);
        if (this.d0) {
            for (int j = 1; j < this.dy.length; ++j) {
                if (this.dy[j] != null) {
                    this.ag = j;
                    break;
                }
            }
        }
        return null;
    }
    
    private void a(final int n, final String s) throws Throwable {
        final FileInputStream fileInputStream = new FileInputStream(s);
        final int n2 = (int)new File(s).length();
        final byte[] array = new byte[n2];
        int read;
        for (int n3 = 0, i = n2; i > 0; i -= read, n3 += read) {
            read = fileInputStream.read(array, n3, i);
        }
        fileInputStream.close();
        this.a(n, array);
    }
    
    private void b(final int n, final String s) throws Throwable {
        this.f.c();
        final byte[] a = this.a(this.a(new URL(this.f.c(), s)));
        if (a == null || a.length == 0) {
            throw new FileNotFoundException(String.valueOf(s) + " empty or not found");
        }
        this.a(n, a);
    }
    
    private void a(int n, final byte[] array) {
        String string = "<SCREEN><BODY>J0141921SPlaybackStartScreen                                                                                                                                                                                                                                                   START SCREEN FOR PLAYBACK                                                                                                                     On each screen, including this one, the following keys control playback                                                                                                   Enter   Advance to the next screen in sequence                                                                                                               PageDown   Forward one screen in sequence                                                                                                                         PageUp   Back one screen in sequence                                                                                                                                F1   Return to this screen                                                                                                                                      F3   End playback and exit                                                                                                                                      F5   Reset current screen                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      </BODY></SCREEN>";
        byte[] bytes = new byte[0];
        if (n == 0) {
            n = 1;
        }
        else {
            final int n2 = 40;
            final String string2 = "Session " + n;
            string = String.valueOf(string.substring(0, n2)) + string2 + string.substring(n2 + string2.length());
        }
        if (this.e.a("Playback_Start_Screen", false)) {
            bytes = string.getBytes();
        }
        System.arraycopy(bytes, this.dz[n] = 0, this.dy[n] = new byte[bytes.length + array.length], 0, bytes.length);
        System.arraycopy(array, 0, this.dy[n], bytes.length, array.length);
    }
    
    public void i() {
        if (this.bu) {
            return;
        }
        int i = 1;
        int n = 0;
        while (i <= this.e2) {
            final abljemtf abljemtf = this.e0[i];
            if (abljemtf.af < abljemtf.ae && abljemtf.ae != 1919) {
                for (int j = 0, ad = abljemtf.ad; j < abljemtf.ae; ++j, ++ad) {
                    this.el[ad] = 32;
                }
                while (n < this.fw && this.fl[n] < abljemtf.ad) {
                    ++n;
                }
                if (n < this.fw && this.fo[n] != 32 && this.fl[n] == abljemtf.ad) {
                    this.fo[n] = 32;
                }
            }
            ++i;
        }
    }
    
    static int a(final byte[] array, final int n, final abljema abljema) {
        final abljema j = abljema.j;
        j.bb = (array[n + 1] == 49);
        for (int i = 1; i < j.o.length; ++i) {
            try {
                j.o[i].fb.aq.a(j.bb);
            }
            catch (Throwable t) {}
        }
        return 2;
    }
    
    public void a(final int n, final int n2, final boolean b) {
        int e3 = this.g(n, n2);
        if (b) {
            int n3 = (n2 == 1 && n > 0 && n <= this.e2 && "ML".indexOf((char)this.e0[n].aq) >= 0) ? 1 : 0;
            int e4 = this.e2;
            while (e3 > 0 && e3 <= this.e2) {
                final byte aq = this.e0[e3].aq;
                if (e4-- < 0) {
                    e3 = n;
                    break;
                }
                if ((aq != 70 || n3 == 0) && aq != 77 && aq != 76) {
                    break;
                }
                if (aq == 70) {
                    n3 = 0;
                }
                e3 = this.g(e3, n2);
            }
        }
        if (e3 > 0 && e3 <= this.e2) {
            this.e3 = e3;
            final abljemtf abljemtf = this.e0[e3];
            abljemtf.c(abljemtf.d = true);
            abljemtf.requestFocus();
        }
    }
    
    private int g(int e2, final int n) {
        if (n == 1) {
            if (--e2 < 1) {
                e2 = this.e2;
            }
        }
        else if (++e2 > this.e2) {
            e2 = 1;
        }
        return e2;
    }
    
    public boolean j() {
        if (this.ff < 1) {
            return false;
        }
        if (this.en != null || this.em != null) {
            return false;
        }
        for (int i = 0; i < this.eu - 5; ++i) {
            if (this.el[i] == 61 && a(this.el, i, this.eu, "=Select") && this.a(i)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean b(final int n, final int n2) {
        return this.a((n - 1) * this.es + (n2 - 1));
    }
    
    public boolean a(final int n) {
        boolean b = false;
        if (this.ff < 1) {
            return false;
        }
        if (this.fh != null) {
            this.fh = null;
            this.fb.aa();
            this.fb.d(-1);
            b = true;
        }
        for (int i = 0; i < this.e1; ++i) {
            final abljemtf abljemtf = this.ez[i];
            if (n >= abljemtf.ad && n <= abljemtf.ad + abljemtf.ae) {
                if (b) {
                    this.ev = true;
                }
                return false;
            }
        }
        if (this.a(this.el[n])) {
            if (b) {
                this.ev = true;
            }
            return false;
        }
        int n2 = n;
        for (int n3 = n % this.es; n2 > 0 && n3 > 0 && !this.a(this.el[n2]); --n2, --n3) {}
        if (this.a(this.el[n2])) {
            ++n2;
        }
        if (n2 > this.eu - 5) {
            if (b) {
                this.ev = true;
            }
            return false;
        }
        int n4;
        int n5;
        for (n4 = 0, n5 = n2; n4 < 3 && n5 < this.eu && this.el[n5] != 61; ++n4, ++n5) {}
        if (n4 < 1 || n4 > 2 || n5 >= this.eu) {
            if (b) {
                this.ev = true;
            }
            return false;
        }
        if (n4 > 1 && n2 < this.eu - 2 && this.el[n2] == 70 && this.el[n2 + 1] >= 48 && this.el[n2 + 1] <= 57) {
            if (b) {
                this.ev = true;
            }
            return false;
        }
        this.fh = new String(this.el, 0, n2, n4);
        int n6 = n5;
        for (int n7 = n5 % this.es; n6 < this.eu && n7 <= this.es && !this.a(this.el[n6]); ++n6, ++n7) {}
        if (this.fb != null) {
            this.fb.d(n2);
        }
        String substring = new String(this.el, 0, n2, n6 - n2);
        if (substring.length() > 20) {
            substring = substring.substring(0, 20);
        }
        this.fb.i("Click on line for " + substring + ", DoubleClick to Enter");
        return this.ev = true;
    }
    
    public boolean a(final byte b) {
        return " :;.,|(){}[]<>".indexOf(b) >= 0;
    }
    
    public void a(final Container container, Component component, final GridBagLayout gridBagLayout, final GridBagConstraints gridBagConstraints, final int n, final int n2, int gridwidth, int gridheight, final char c) {
        if (component == null) {
            component = new Label("");
        }
        if (n == -1) {
            gridBagConstraints.gridy = -1;
        }
        else {
            gridBagConstraints.gridy = n - 1;
        }
        if (n2 == -1) {
            gridBagConstraints.gridx = -1;
        }
        else {
            gridBagConstraints.gridx = n2 - 1;
        }
        if (gridwidth == -1) {
            gridwidth = 0;
        }
        else if (gridwidth == -2) {
            gridwidth = -1;
        }
        if (gridheight == -1) {
            gridheight = 0;
        }
        else if (gridheight == -2) {
            gridheight = -1;
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        switch (c) {
            case 'W': {
                gridBagConstraints.anchor = 17;
                break;
            }
            case 'E': {
                gridBagConstraints.anchor = 13;
                break;
            }
            case 'N': {
                gridBagConstraints.anchor = 11;
                break;
            }
            case 'S': {
                gridBagConstraints.anchor = 15;
                break;
            }
            default: {
                gridBagConstraints.anchor = 10;
                break;
            }
        }
        gridBagLayout.setConstraints(component, gridBagConstraints);
        container.add(component);
    }
    
    public void k() {
        final Enumeration a = this.h1.a();
        while (a.hasMoreElements()) {
            final Enhanced.Selfield selfield = a.nextElement();
            if (selfield.h == 'B') {
                final Enumeration a2 = this.h1.a();
                while (a2.hasMoreElements()) {
                    final Enhanced.Selfield selfield2 = a2.nextElement();
                    final int n = selfield2.c % this.es;
                    if (selfield2.h == 'P') {
                        for (int i = 0; i < selfield.s.length; ++i) {
                            final Enhanced.ChoiceDetail choiceDetail = selfield.s[i];
                            final int n2 = choiceDetail.r % this.es;
                            final int n3 = n2 + choiceDetail.q;
                            if (n >= n2 && n <= n3) {
                                choiceDetail.b = true;
                                break;
                            }
                        }
                        break;
                    }
                }
                break;
            }
        }
        final Enumeration a3 = this.h1.a();
        while (a3.hasMoreElements()) {
            final Enhanced.Selfield selfield3 = a3.nextElement();
            int j = 0;
            while (j < this.e1) {
                final abljemtf abljemtf = this.ez[j];
                if (abljemtf.ad == selfield3.c) {
                    String s = "?buttons";
                    String s2 = "?format";
                    final StringBuffer sb = new StringBuffer(abljema.iv.substring(0, abljemtf.ae));
                    if (abljemtf.ak != 69) {
                        abljem.d("Selection field not enhanced at " + abljemtf.ad);
                        break;
                    }
                    final Vector<String> vector = new Vector<String>();
                    switch (selfield3.g) {
                        case 'S': {
                            s = "RadioButtons";
                            break;
                        }
                        case 'M': {
                            s = "CheckBoxes";
                            break;
                        }
                        case 'P': {
                            s = "PushButtons";
                            break;
                        }
                    }
                    switch (selfield3.h) {
                        case 'B': {
                            s2 = "MenuBar";
                            if (selfield3.g == 'S') {
                                s = "";
                                break;
                            }
                            break;
                        }
                        case 'F': {
                            s2 = "";
                            break;
                        }
                        case 'L': {
                            s2 = "List";
                            break;
                        }
                        case 'P': {
                            s2 = "Pulldown";
                            if (selfield3.g == 'S') {
                                s = "";
                                break;
                            }
                            break;
                        }
                    }
                    vector.addElement(String.valueOf(s2) + ((s2.length() > 0 && s.length() > 0) ? " " : "") + s);
                    for (int k = 0; k < selfield3.s.length; ++k) {
                        final Enhanced.ChoiceDetail choiceDetail2 = selfield3.s[k];
                        String s3 = " " + (k + 1) + " " + choiceDetail2.g + " ";
                        if (selfield3.h == 'B') {
                            s3 = String.valueOf(s3) + e(choiceDetail2.r, 4) + " ";
                        }
                        vector.addElement(s3);
                        if (choiceDetail2.b && selfield3.f && k < sb.length()) {
                            sb.setCharAt(k, 'S');
                        }
                    }
                    if (selfield3.g == 'P') {
                        abljem.d("...Pushbuttons not supported yet");
                        break;
                    }
                    if (selfield3.h == 'B') {
                        final abljemtf abljemtf2 = abljemtf;
                        ++abljemtf2.ab;
                        final abljemtf abljemtf3 = abljemtf;
                        ++abljemtf3.ad;
                    }
                    abljemtf.a(selfield3, vector, sb);
                    if (selfield3.h != 'F') {
                        abljemtf.h = true;
                        break;
                    }
                    break;
                }
                else {
                    ++j;
                }
            }
            if ((selfield3.h == 'F' || selfield3.h == 'L') && (selfield3.g == 'M' || (selfield3.g == 'S' && j >= this.e1))) {
                for (int l = 0; l < selfield3.s.length; ++l) {
                    final Enhanced.ChoiceDetail choiceDetail3 = selfield3.s[l];
                    for (int n4 = 0, r = choiceDetail3.r; n4 < choiceDetail3.g.length() && r < this.el.length; ++n4, ++r) {
                        this.el[r] = (byte)choiceDetail3.g.charAt(n4);
                    }
                    if (j >= this.e1) {
                        if (choiceDetail3.b && selfield3.f) {
                            switch (selfield3.g) {
                                case 'M': {
                                    this.el[choiceDetail3.r - 2] = 88;
                                    break;
                                }
                                case 'S': {
                                    this.el[choiceDetail3.r - 2] = 79;
                                    break;
                                }
                            }
                        }
                        else {
                            this.el[choiceDetail3.r - 2] = 45;
                        }
                    }
                }
            }
        }
        final Enumeration b = this.h1.b();
        while (b.hasMoreElements()) {
            final Enhanced.Rollbar rollbar = b.nextElement();
            int n5;
            for (n5 = 0; n5 < this.e1; ++n5) {
                final abljemtf abljemtf4 = this.ez[n5];
                if (abljemtf4.ad == rollbar.c) {
                    final Vector<String> vector2 = new Vector<String>();
                    vector2.addElement("Scrollbar");
                    abljemtf4.a(rollbar, vector2);
                    abljemtf4.h = true;
                    abljemtf4.i = new abljemp(vector2);
                    break;
                }
            }
            if (n5 >= this.e1) {
                abljem.d("Unmatched scrollbar definition at " + rollbar.c);
            }
        }
        final Enumeration c = this.h1.c();
        while (c.hasMoreElements()) {
            final Enhanced.FieldControl fieldControl = c.nextElement();
            int n6 = 0;
            while (n6 < this.e1) {
                final abljemtf abljemtf5 = this.ez[n6];
                if (abljemtf5.ad == fieldControl.c) {
                    if (abljemtf5.ak != 70) {
                        abljem.d("Ignored FieldControl at " + fieldControl.c + " for field pro=" + (char)abljemtf5.ak);
                        break;
                    }
                    abljemtf5.ak = (byte)fieldControl.d;
                    abljemtf5.am = (byte)fieldControl.e;
                    abljemtf5.au = fieldControl.h + 1;
                    if (abljemtf5.au > 0) {
                        abljem.d("Cursor progression value of " + fieldControl.h + " ignored");
                    }
                    if (fieldControl.f == 'C') {
                        for (int n7 = n6 + 1; n7 < this.e1; ++n7) {
                            final abljemtf abljemtf6 = this.ez[n7];
                            if (abljemtf6.ak != 77 && abljemtf6.ak != 76) {
                                break;
                            }
                            abljemtf5.a(abljemtf6, abljemtf6.ak);
                            if (abljemtf6.aq == 76) {
                                break;
                            }
                        }
                        abljemtf5.a(abljemtf5, (byte)70);
                        break;
                    }
                    break;
                }
                else {
                    ++n6;
                }
            }
            if (n6 >= this.e1) {
                abljem.d("Unmatched feld control definition at " + fieldControl.c);
            }
        }
    }
    
    public void l() {
        if (!this.ep) {
            return;
        }
        for (int i = 0; i < this.e1; ++i) {
            final abljemtf abljemtf = this.ez[i];
            int n;
            int ad;
            for (n = abljemtf.ad + abljemtf.ae, ad = abljemtf.ad; ad < n && this.eo[ad] == 0; ++ad) {}
            if (ad < n) {
                abljemtf.a0 = true;
            }
        }
    }
    
    public void m() {
        if (this.er) {
            return;
        }
        for (int i = 0; i < this.eq.length; ++i) {
            this.eq[i] = 0;
        }
        try {
            for (int j = 0; j < this.fw; ++j) {
                this.eq[this.fl[j] - 1] = this.fo[j];
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            return;
        }
        this.er = true;
    }
    
    public int a(final byte[] array, final int n, final int n2) {
        this.fw = 0;
        if (n2 <= 1 || (array[n + 1] >= 65 && array[n + 1] <= 85)) {
            this.e(this.fw);
            this.fo[this.fw] = 32;
            this.fi[this.fw] = 1;
            this.fj[this.fw] = 0;
            this.fk[this.fw] = 0;
            this.fl[this.fw] = -1;
            ++this.fw;
        }
        for (int i = 0; i < this.eo.length; ++i) {
            this.eo[i] = 0;
        }
        for (int j = 0; j < this.eq.length; ++j) {
            this.eq[j] = 0;
        }
        int n3 = 0;
        for (int k = 1; k < n2; ++k) {
            final byte b = array[n + k];
            if (b == 65) {
                if (++k >= n2) {
                    return 0;
                }
                n3 += (array[n + k] - 65) * 20;
            }
            else if (b >= 65 && b <= 85) {
                n3 += b - 65;
            }
            else {
                int n4 = 0;
                if (b >= 97 && b <= 122) {
                    n4 = (byte)(32 + b - 97);
                }
                if (b >= 48 && b <= 53) {
                    n4 = (byte)(63 + b - 53);
                }
                if (n4 != 0) {
                    this.eo[n3] = (this.fw << 8) + b;
                    this.eq[n3] = n4;
                    this.e(this.fw);
                    this.fo[this.fw] = n4;
                    this.fi[this.fw] = n3 / this.es + 1;
                    this.fj[this.fw] = n3 % this.es + 1;
                    this.fk[this.fw] = 0;
                    this.fl[this.fw] = n3;
                    ++this.fw;
                }
                ++n3;
            }
        }
        for (int l = 0; l < this.fw; ++l) {
            this.fk[l] = ((l < this.fw - 1) ? this.fl[l + 1] : this.eu) - this.fl[l];
            final int[] fl = this.fl;
            final int n5 = l;
            ++fl[n5];
            final int[] fj = this.fj;
            final int n6 = l;
            ++fj[n6];
            if (this.fj[l] > this.es) {
                this.fj[l] = 1;
                final int[] fi = this.fi;
                final int n7 = l;
                ++fi[n7];
            }
            if (this.fk[l] > 0) {
                final int[] fk = this.fk;
                final int n8 = l;
                --fk[n8];
            }
        }
        this.ep = true;
        this.er = true;
        return n2;
    }
    
    public int a(final byte[] array, final int n) {
        int c = c(array, n + 1, 2);
        int c2 = c(array, n + 3, 3);
        int n2 = (c - 1) * this.es + (c2 - 1);
        int c3 = c(array, n + 6, 4);
        int n3 = array[n + 11];
        if (n3 <= 57) {
            n3 -= 48;
        }
        else {
            n3 = n3 + 10 - 65;
        }
        n3 += 32;
        if (array[n + 10] == 51) {
            n3 += 16;
        }
        if (n2 > 0 || this.el[n2] == 32) {
            ++n2;
            if (++c2 > this.es) {
                c2 = 1;
                ++c;
            }
            --c3;
        }
        if (n2 < this.el.length && c <= this.et && c3 > 0) {
            this.e(this.fw);
            this.fi[this.fw] = c;
            this.fj[this.fw] = c2;
            this.fl[this.fw] = n2;
            this.fk[this.fw] = c3;
            this.fo[this.fw] = n3;
            ++this.fw;
        }
        return 12;
    }
    
    public void c(String a) {
        final int n = 0;
        if (a == null) {
            return;
        }
        a = a(a, this.es);
        final byte[] bytes = a.getBytes();
        if (bytes.length == 0) {
            return;
        }
        int n2 = bytes.length;
        if (n2 > this.el.length) {
            n2 = this.el.length;
        }
        System.arraycopy(bytes, 0, this.el, 0, n2);
        this.fo[n] = 32;
        this.fp[n] = '0';
        this.fq[n] = null;
        this.fr[n] = null;
        this.fs[n] = null;
        this.ft[n] = null;
        this.fu[n] = null;
        this.fi[n] = 1;
        this.fj[n] = 1;
        this.fk[n] = n2;
        this.fl[n] = 0;
        this.fm[n] = 0;
        this.fn[n] = 0;
        this.fw = 1;
    }
    
    private void e(final int n) {
        if (n < 0 || n >= this.fi.length) {
            return;
        }
        this.fo[n] = 0;
        this.fp[n] = '0';
        this.fq[n] = null;
        this.fr[n] = null;
        this.fs[n] = null;
        this.ft[n] = null;
        this.fu[n] = null;
        this.fi[n] = 0;
        this.fj[n] = 0;
        this.fk[n] = 0;
        this.fl[n] = 0;
        this.fm[n] = 0;
        this.fn[n] = 0;
    }
    
    private void h(final int n, final int n2) {
        if (n < 0 || n >= this.fi.length || n2 < 0 || n2 >= this.fi.length) {
            return;
        }
        this.fo[n] = this.fo[n2];
        this.fp[n] = this.fp[n2];
        this.fq[n] = this.fq[n2];
        this.fr[n] = this.fr[n2];
        this.fs[n] = this.fs[n2];
        this.ft[n] = this.ft[n2];
        this.fu[n] = this.fu[n2];
        this.fi[n] = this.fi[n2];
        this.fj[n] = this.fj[n2];
        this.fk[n] = this.fk[n2];
        this.fl[n] = this.fl[n2];
        this.fm[n] = this.fm[n2];
        this.fn[n] = this.fn[n2];
    }
    
    public void c(final int n, final int n2) {
        int n3 = 0;
        this.b(n);
        int n4 = -1;
        for (int i = n; i < n + n2 - 1; ++i) {
            this.eo[i] = this.eo[i + 1];
            if (this.eo[i] != 0) {
                n4 = this.eo[i] >> 8;
                if (n4 >= 0 && n4 < this.fw) {
                    if (n3 == 0) {
                        if (n4 > 0) {
                            final int[] fk = this.fk;
                            final int n5 = n4 - 1;
                            --fk[n5];
                        }
                        n3 = 1;
                    }
                    final int[] fl = this.fl;
                    final int n6 = n4;
                    --fl[n6];
                    final int[] fj = this.fj;
                    final int n7 = n4;
                    --fj[n7];
                    if (this.fj[n4] < 1) {
                        this.fj[n4] = this.es;
                        final int[] fi = this.fi;
                        final int n8 = n4;
                        --fi[n8];
                    }
                    this.fm[n4] = 0;
                    this.fn[n4] = 0;
                }
            }
        }
        if (n4 >= 0) {
            final int[] fk2 = this.fk;
            final int n9 = n4;
            ++fk2[n9];
        }
    }
    
    public void d(final int n, final int n2) {
        for (int i = n + n2 - 1; i > n; --i) {
            this.eo[i] = this.eo[i - 1];
        }
        this.eo[n] = 0;
        int n3;
        for (n3 = n - 1; n3 >= 0 && this.eo[n3] == 0; --n3) {}
        if (n3 >= 0) {
            final int n4 = this.eo[n3] >> 8;
            final int[] fk = this.fk;
            final int n5 = n4;
            ++fk[n5];
        }
    }
    
    public void b(final int n) {
        if (this.eo[n] == 0) {
            return;
        }
        int i = this.eo[n] >> 8;
        this.eo[n] = 0;
        if (i >= 0 && i < this.fw) {
            if (i == 0) {
                this.e(0);
                this.fi[0] = 1;
                this.fj[0] = 1;
                this.fl[0] = 0;
                this.fk[0] = ((this.fw > 1) ? (this.fl[1] - 1) : this.eu);
                if (this.fk[0] < 0) {
                    this.fk[0] = 0;
                }
                this.fo[0] = 32;
            }
            else if (i > 0) {
                final int[] fk = this.fk;
                final int n2 = i - 1;
                fk[n2] += this.fk[i] + 1;
                while (i < this.fw - 1) {
                    this.h(i, i + 1);
                    ++i;
                }
                --this.fw;
            }
        }
    }
    
    public void a(final char c) {
        this.a(c, false);
    }
    
    public void a(final char c, final boolean b) {
        this.j.aj[this.j.ag] = c;
        this.j.ak[this.j.ag] = b;
    }
    
    public int n() {
        if (!this.j.ae) {
            return 1;
        }
        for (int i = 0; i < this.j.aj.length; ++i) {
            if (this.j.aj[i] == 'A') {
                return i;
            }
        }
        return -1;
    }
    
    public String o() {
        Object o = null;
        for (int i = 0; i < this.j.aj.length; ++i) {
            final char c = (char)(48 + i);
            if (this.j.aj[i] == 'A' && this.j.dy[i] == null) {
                if (o == null) {
                    o = String.valueOf(c);
                }
                else {
                    o = String.valueOf(o) + "," + c;
                }
            }
        }
        return (String)o;
    }
    
    public int b(final byte[] array, final int n, final int n2) {
        if (n2 > 1 && this.aj[this.ag] != 'E') {
            switch (array[n + 1]) {
                case 49: {
                    this.a('S');
                    break;
                }
                case 48: {
                    this.a('A');
                    break;
                }
            }
        }
        if (n2 > 2 && array[n + 2] == 49) {
            this.bu = true;
        }
        if (n2 > 3) {
            switch (array[n + 3]) {
                case 49: {
                    this.bn.d = Boolean.TRUE;
                    break;
                }
                case 48: {
                    this.bn.d = Boolean.FALSE;
                    break;
                }
            }
        }
        return n2;
    }
    
    public void p() {
        for (int i = 0; i < this.fw; ++i) {
            if ((this.fo[i] & 0x7) == 0x7) {
                int j = this.fk[i];
                final int n = this.fl[i];
                while (j > 0) {
                    this.el[n + j - 1] = 32;
                    --j;
                }
            }
        }
    }
    
    public void q() {
        this.e(0);
        this.fi[0] = 1;
        this.fj[0] = 1;
        this.fl[0] = 0;
        this.fk[0] = this.eu;
        this.fo[0] = 32;
        this.fw = 1;
        int i = 0;
        int n = 0;
        while (i < this.eu) {
            if (this.el[i] == 60) {
                n = 1;
            }
            if (n != 0) {
                if (this.el[i] == 62) {
                    n = 0;
                }
                this.el[i] = 32;
            }
            ++i;
        }
    }
    
    public void r() {
        if (this.g9 != null) {
            if (this.g9.b) {
                this.ha = this.g9.location();
            }
            this.g9.dispose();
        }
        this.g9 = new abljemlb(this.o[this.p]);
        final Point ha = this.ha;
        if (ha != null && ha.x > 0 && ha.y > 0 && ha.x < this.fb.p && ha.y < this.fb.q) {
            this.g9.move(ha.x, ha.y);
            this.g9.b = true;
        }
    }
    
    public void s() {
        this.j.hr = true;
        this.bm.b();
        if (this.j.g9 != null) {
            this.j.g9.hide();
        }
    }
    
    static Event a(final Event event) {
        final Event event2 = new Event(event.target, event.id, event.arg);
        event2.arg = event.arg;
        event2.clickCount = event.clickCount;
        event2.evt = event.evt;
        event2.id = event.id;
        event2.key = event.key;
        event2.modifiers = event.modifiers;
        event2.target = event.target;
        event2.when = event.when;
        event2.x = event.x;
        event2.y = event.y;
        return event2;
    }
    
    static String a(final String s, final int n) {
        if (s == null) {
            return null;
        }
        final int length = s.length();
        final StringBuffer sb = new StringBuffer();
        int n2 = 0;
        int n3;
        while ((n3 = n2 + n) < length) {
            int n4 = 0;
            if (s.charAt(n3) != ' ' && s.charAt(n3 - 1) != ' ') {
                for (n4 = 1; n4 < n && s.charAt(n3 - n4 - 1) != ' '; ++n4) {}
                if (n4 >= n) {
                    n4 = 0;
                }
                n3 -= n4;
            }
            sb.append(s.substring(n2, n3));
            while (n4-- > 0) {
                sb.append(' ');
            }
            for (n2 = n3; n2 < length && s.charAt(n2) == ' '; ++n2) {}
        }
        sb.append(s.substring(n2));
        return sb.toString();
    }
    
    static Color a(final Color color, final Color color2) {
        return new Color((color.getRed() + color2.getRed()) / 2, (color.getBlue() + color2.getBlue()) / 2, (color.getGreen() + color2.getGreen()) / 2);
    }
    
    static String b(final char c) {
        final char[] array = new char[Styler.de];
        for (int i = 0; i < array.length; ++i) {
            array[i] = c;
        }
        return new String(array);
    }
    
    static boolean c(final char c) {
        return c >= '0' && c <= '9';
    }
    
    static String e(final int n, final int n2) {
        final String value = String.valueOf(n);
        final int length = value.length();
        if (n2 > length && n2 < abljema.iw.length()) {
            return abljema.iw.substring(0, n2 - length).concat(value);
        }
        if (n2 < length && n2 > 0) {
            return value.substring(length - n2, length);
        }
        return value;
    }
    
    static int c(final byte[] array, int n, int n2) {
        int n3 = 0;
        while (n2-- > 0) {
            n3 = n3 * 10 + (array[n++] - 48);
        }
        return n3;
    }
    
    static int a(final char[] array, int n, int n2) {
        int n3 = 0;
        while (n2-- > 0) {
            n3 = n3 * 10 + (array[n++] - '0');
        }
        return n3;
    }
    
    static int d(final String s) {
        final char[] array = { '\0' };
        String concat = new String();
        final int length = s.length();
        int n = 0;
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if ((char1 != '\"' || i != 0) && (char1 > ' ' || n != 0)) {
                if ((char1 == '-' && n != 0) || (char1 != '-' && (char1 < '0' || char1 > '9'))) {
                    break;
                }
                n = 1;
                array[0] = char1;
                concat = concat.concat(new String(array));
            }
        }
        if (concat.length() == 0) {
            return 0;
        }
        return Integer.parseInt(concat);
    }
    
    static void d(final byte[] array, final int n, final int n2) {
        int n3 = n;
        int i = n;
        for (int j = n2 / 2; j > 0; --j) {
            array[i] = (byte)b(array, n3);
            n3 += 2;
            ++i;
        }
        while (i < n2) {
            array[i++] = 0;
        }
    }
    
    static int b(final byte[] array, int n) {
        byte b = 0;
        for (int i = 0; i < 2; ++i) {
            final byte b2 = array[n];
            b *= 16;
            if (b2 >= 48 && b2 <= 57) {
                b += (byte)(b2 - 48);
            }
            else if (b2 >= 65 && b2 <= 70) {
                b += (byte)(b2 - 65 + 10);
            }
            else if (b2 >= 97 && b2 <= 102) {
                b += (byte)(b2 - 97 + 10);
            }
            ++n;
        }
        return b;
    }
    
    static int e(final String s) {
        final byte[] array = { 48, 48 };
        final int length = s.length();
        if (length > 1) {
            array[0] = (byte)s.charAt(0);
            array[1] = (byte)s.charAt(1);
        }
        else if (length > 0) {
            array[1] = (byte)s.charAt(0);
        }
        return b(array, 0);
    }
    
    static String c(final byte[] array, final int n) {
        String string = "";
        if (array == null) {
            return "null";
        }
        for (int n2 = 0; n2 < n && n2 < array.length; ++n2) {
            string = String.valueOf(string) + ((n2 == 0) ? "" : ",") + c(array[n2]);
        }
        return string;
    }
    
    static String c(final int n) {
        return new String(new char[] { Character.forDigit(n / 16, 16), Character.forDigit(n % 16, 16) });
    }
    
    static String d(final char c) {
        if (" ;?\"#%<>+=&'".indexOf(c) < 0) {
            return String.valueOf(c);
        }
        return "%" + c((int)c);
    }
    
    static String f(final String s) {
        final int length = s.length();
        String concat = new String();
        for (int i = 0; i < length; ++i) {
            concat = concat.concat(d(s.charAt(i)));
        }
        return concat;
    }
    
    static Color g(final String s) {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        final int length = s.length();
        int n4;
        if (length > 0 && s.charAt(0) == '\"') {
            n4 = 1;
        }
        else {
            n4 = 0;
        }
        if (s.charAt(n4) == '#') {
            final int n5 = ++n4 + 2;
            final int n6 = n5 + 2;
            if (length > n4 + 1) {
                n = e(s.substring(n4));
            }
            if (length > n5 + 1) {
                n2 = e(s.substring(n5));
            }
            if (length > n6 + 1) {
                n3 = e(s.substring(n6));
            }
        }
        else {
            int i;
            for (i = n4; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (char1 != ' ') {
                    if (char1 < '0') {
                        break;
                    }
                    if (char1 > '9') {
                        break;
                    }
                }
            }
            int j;
            int n7;
            for (n7 = (j = i + 1); j < length; ++j) {
                final char char2 = s.charAt(j);
                if (char2 != ' ') {
                    if (char2 < '0') {
                        break;
                    }
                    if (char2 > '9') {
                        break;
                    }
                }
            }
            final int n8 = j + 1;
            if (n4 < length) {
                n = d(s.substring(n4));
            }
            if (n7 < length) {
                n2 = d(s.substring(n7));
            }
            if (n8 < length) {
                n3 = d(s.substring(n8));
            }
        }
        return new Color(n, n2, n3);
    }
    
    static byte[] a(final StringBuffer sb) {
        final String string = sb.toString();
        final int length = string.length();
        final byte[] array = new byte[length];
        string.getBytes(0, length, array, 0);
        return array;
    }
    
    static boolean a(final byte[] array, int n, final byte[] array2, int n2, int n3) {
        while (n3-- > 0) {
            if (array[n++] != array2[n2++]) {
                return false;
            }
        }
        return true;
    }
    
    static boolean a(final byte[] array, final int n, final String s) {
        if (array == null) {
            return s == null || s.length() == 0;
        }
        return a(array, n, array.length, s);
    }
    
    static boolean a(final byte[] array, int n, final int n2, final String s) {
        if (array == null) {
            return s == null || s.length() == 0;
        }
        int length = s.length();
        int n3 = 0;
        if (n + length > n2) {
            return false;
        }
        while (length-- > 0) {
            if (array[n] != (byte)s.charAt(n3)) {
                return false;
            }
            ++n;
            ++n3;
        }
        return true;
    }
    
    static Object a(final Vector vector) {
        try {
            final Object firstElement = vector.firstElement();
            vector.removeElementAt(0);
            return firstElement;
        }
        catch (NoSuchElementException ex) {
            return null;
        }
        catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }
    
    static int e(final byte[] array, final int n, final int n2) {
        for (int i = n; i < n2; ++i) {
            final char c = (char)array[i];
            if (c == ' ' || c == '>') {
                return i - n;
            }
            if (!Character.isLetterOrDigit(c) && "~@#$%^&_-,.?".indexOf(c) < 0) {
                return 0;
            }
        }
        return 0;
    }
    
    static String a(final String s, final String s2, final String s3) {
        String string = s;
        int n = 0;
        if (s2.length() > 0) {
            int index;
            while (n < string.length() && (index = string.indexOf(s2, n)) >= 0) {
                string = String.valueOf(string.substring(0, index)) + s3 + string.substring(index + s2.length());
                n = index + s3.length();
            }
        }
        return string;
    }
    
    static String b(String concat, final char c) {
        int length = concat.length();
        int index;
        while ((index = concat.indexOf(c)) >= 0) {
            if (length-- < 0) {
                abljem.d("jem error 43");
                return concat;
            }
            concat = concat.substring(0, index).concat(concat.substring(index + 1));
        }
        return concat;
    }
    
    static String b(final String s, final int n) {
        final int length = s.length();
        if (n > length && n < abljema.iv.length()) {
            return s.concat(abljema.iv.substring(0, n - length));
        }
        if (n < length && n > 0) {
            return s.substring(0, n);
        }
        return s;
    }
    
    static int t() {
        return (int)(System.currentTimeMillis() / 1000L);
    }
    
    static int[] a(final int[] array, final int n) {
        final int[] array2 = new int[n];
        if (array != null) {
            int length = array.length;
            if (length > n) {
                length = n;
            }
            System.arraycopy(array, 0, array2, 0, length);
        }
        return array2;
    }
    
    static String[] a(final String[] array, final int n) {
        final String[] array2 = new String[n];
        if (array != null) {
            int length = array.length;
            if (length > n) {
                length = n;
            }
            System.arraycopy(array, 0, array2, 0, length);
        }
        return array2;
    }
    
    static Dimension[] a(final Dimension[] array, final int n) {
        final Dimension[] array2 = new Dimension[n];
        if (array != null) {
            int length = array.length;
            if (length > n) {
                length = n;
            }
            System.arraycopy(array, 0, array2, 0, length);
        }
        return array2;
    }
    
    public static void a(final Component component, final Component component2) {
        try {
            if (component == null || component2 == null) {
                return;
            }
            if (!component2.isVisible()) {
                return;
            }
            final Rectangle bounds = component.getBounds();
            final Rectangle bounds2 = component2.getBounds();
            final Rectangle intersection = bounds.intersection(bounds2);
            if (intersection.width == 0 || intersection.height == 0) {
                return;
            }
            if (intersection.width >= intersection.height) {
                final int n = Toolkit.getDefaultToolkit().getScreenSize().height - 50;
                final int a = a(bounds.y, bounds.height, intersection.y, intersection.height, bounds2.y, bounds2.height);
                final Rectangle rectangle = bounds;
                rectangle.y += a;
                if (bounds.y < 0) {
                    bounds.y = bounds2.y + bounds2.height + 2;
                }
                if (bounds.y + bounds.height > n) {
                    bounds.y = bounds2.y - bounds.height - 2;
                }
                if (bounds.y < 0) {
                    bounds.y = 0;
                }
            }
            else {
                final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
                final int a2 = a(bounds.x, bounds.width, intersection.x, intersection.width, bounds2.x, bounds2.width);
                final Rectangle rectangle2 = bounds;
                rectangle2.x += a2;
                if (bounds.x < 0) {
                    bounds.x = bounds2.x + bounds2.width + 2;
                }
                if (bounds.x + bounds.width > width) {
                    bounds.x = bounds2.x - bounds.width - 2;
                }
                if (bounds.x < 0) {
                    bounds.x = 0;
                }
            }
            component.setBounds(bounds);
        }
        catch (Throwable t) {}
    }
    
    private static int a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final int n7 = n2 / 2;
        final int n8 = n4 / 2;
        final int n9 = n6 / 2;
        final int n10 = n + n7;
        final int n11 = n3 + n8;
        int n12 = n7 - Math.abs(n10 - n11) + (n9 - Math.abs(n5 + n9 - n11)) + 2;
        if (n10 <= n11) {
            n12 = -n12;
        }
        return n12;
    }
    
    static boolean b(final Event event) {
        return event.id == 503 || event.id == 504 || event.id == 505 || event.id == 506;
    }
    
    static boolean a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    private void ab() {
        if (this.cg) {
            return;
        }
        if (this.bi) {
            return;
        }
        this.bi = true;
        try {
            this.bf = System.getProperty("file.separator");
            final String property = System.getProperty("user.home");
            abljem.d("Java home directory=" + property);
            if (this.bf == null || this.bf.length() == 0) {
                abljem.d("No file.separator");
                return;
            }
            if (property == null || property.length() == 0) {
                abljem.d("No user.home");
                return;
            }
            final File file = new File(property);
            final String string = String.valueOf(property) + this.bf + "strategi.pth";
            String bh = String.valueOf(property) + this.bf + "strategi";
            final File file2 = new File(string);
            if (!file.isDirectory()) {
                abljem.d("user.home=" + property + " not a directory");
                return;
            }
            Label_0425: {
                if (!file2.isFile()) {
                    if (!file.canWrite()) {
                        abljem.d("Cannot write to user.home=" + property);
                        return;
                    }
                    final FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    final byte[] array = new byte[bh.length()];
                    bh.getBytes(0, bh.length(), array, 0);
                    try {
                        try {
                            fileOutputStream.write(array);
                        }
                        catch (Exception ex2) {
                            abljem.d("Failed writing pathfile " + string);
                            return;
                        }
                        break Label_0425;
                    }
                    finally {
                        try {
                            fileOutputStream.close();
                        }
                        catch (Exception ex3) {}
                    }
                }
                final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file2));
                bh = a(dataInputStream);
                try {
                    dataInputStream.close();
                }
                catch (Exception ex4) {}
                if (bh == null) {
                    abljem.d("Failed reading pathfile " + string);
                    return;
                }
            }
            abljem.d("Application root directory=" + bh);
            final File file3 = new File(bh);
            if (!file3.isDirectory()) {
                try {
                    file3.mkdirs();
                }
                catch (Exception ex5) {
                    abljem.d("Failed creating Strategi root directory " + bh);
                    return;
                }
            }
            if (!file3.isDirectory()) {
                abljem.d("Invalid Strategi directory " + bh);
                return;
            }
            this.bh = bh;
        }
        catch (Exception ex) {
            abljem.d("Strategi root failed " + ex);
        }
    }
    
    private void ac() {
        if (this.bg != null) {
            return;
        }
        if (this.iq == null) {
            return;
        }
        this.ab();
        if (this.bh == null) {
            return;
        }
        final String string = String.valueOf(this.bh) + this.bf + "tmp1";
        final File file = new File(string);
        if (!file.isDirectory()) {
            try {
                file.mkdirs();
            }
            catch (Exception ex) {
                abljem.d("Failed creating temporary directory " + string);
                return;
            }
        }
        this.bg = string;
        this.ad();
    }
    
    private void ad() {
        if (this.bg == null) {
            return;
        }
        final File file = new File(this.bg);
        if (!file.isDirectory()) {
            return;
        }
        String[] list;
        try {
            list = file.list();
        }
        catch (Exception ex) {
            return;
        }
        for (int i = 0; i < list.length; ++i) {
            final File file2 = new File(String.valueOf(this.bg) + this.bf + list[i]);
            try {
                file2.delete();
            }
            catch (Exception ex2) {}
        }
    }
    
    private File ae() {
        if (this.iq == null) {
            return null;
        }
        this.ab();
        this.ac();
        if (this.bh == null) {
            return null;
        }
        return new File(String.valueOf(this.bh) + this.bf + "prtbin.pth");
    }
    
    static String a(final DataInputStream dataInputStream) {
        String line;
        try {
            line = dataInputStream.readLine();
        }
        catch (Exception ex) {
            return null;
        }
        if (line == null || line.length() == 0) {
            return null;
        }
        int n;
        for (n = line.length() - 1; n >= 0 && line.charAt(n) >= '\0' && line.charAt(n) <= ' '; --n) {}
        if (n < 0) {
            return "";
        }
        return line.substring(0, n + 1);
    }
    
    public boolean h(final String hb) {
        if (this.iq == null) {
            return false;
        }
        this.j.hb = hb;
        final File ae = this.ae();
        if (ae == null) {
            return false;
        }
        DataOutputStream dataOutputStream;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(ae));
        }
        catch (Exception ex) {
            return false;
        }
        try {
            dataOutputStream.writeBytes(hb);
        }
        catch (Exception ex2) {
            return false;
        }
        finally {
            try {
                dataOutputStream.close();
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public boolean u() {
        String a = null;
        if (this.iq == null) {
            return false;
        }
        final File ae = this.ae();
        if (ae == null) {
            return false;
        }
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream(ae));
            a = a(dataInputStream);
            dataInputStream.close();
        }
        catch (Exception ex) {}
        if (a == null) {
            a = "";
        }
        if (a.equalsIgnoreCase(this.bj)) {
            this.j.hb = null;
            return false;
        }
        this.j.hb = a;
        return true;
    }
    
    public void a(final byte[] array, String string, final String s) throws IOException {
        String s2 = null;
        if (this.iq == null) {
            throw new IOException("No File Access privileges");
        }
        if (s != null && string.indexOf(this.bk) >= 0) {
            s2 = string;
            string = String.valueOf(this.bg) + this.bf + s;
        }
        final FileOutputStream fileOutputStream = new FileOutputStream(new File(string));
        fileOutputStream.write(array);
        fileOutputStream.close();
        if (s2 != null) {
            Runtime.getRuntime().exec(a(s2, this.bk, string));
        }
    }
    
    static int f(final int n, final int n2) {
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    public static byte[] f(final byte[] array, final int n, final int n2) {
        DeflaterOutputStream deflaterOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream(n2);
            deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream);
            deflaterOutputStream.write(array, n, n2);
            deflaterOutputStream.finish();
            return byteArrayOutputStream.toByteArray();
        }
        catch (Throwable t) {
            abljem.d("Failed deflating " + ((array == null) ? -1 : array.length) + " ofs=" + n + " len=" + n2);
            abljem.d(t.toString());
            throw new RuntimeException("Failed deflating");
        }
        finally {
            try {
                deflaterOutputStream.close();
            }
            catch (Throwable t2) {}
            try {
                byteArrayOutputStream.close();
            }
            catch (Throwable t3) {}
        }
    }
    
    public static byte[] g(final byte[] array, final int n, final int n2) {
        InflaterInputStream inflaterInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            final byte[] array2 = new byte[100];
            inflaterInputStream = new InflaterInputStream(new ByteArrayInputStream(array, n, n2));
            byteArrayOutputStream = new ByteArrayOutputStream(n2 * 2);
            int read;
            while ((read = inflaterInputStream.read(array2, 0, array2.length)) != -1) {
                byteArrayOutputStream.write(array2, 0, read);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (Throwable t) {
            abljem.d("Failed inflating " + ((array == null) ? -1 : array.length) + " ofs=" + n + " len=" + n2);
            abljem.d(t.toString());
            throw new RuntimeException("Failed inflating");
        }
        finally {
            try {
                inflaterInputStream.close();
            }
            catch (Throwable t2) {}
            try {
                byteArrayOutputStream.close();
            }
            catch (Throwable t3) {}
        }
    }
    
    void v() {
        final byte[] p = this.g8.p;
        final int length = p.length;
        if (!this.gb && !this.df) {
            return;
        }
        if (this.gb) {
            for (int i = 0, n = 0; i < length; ++i, ++n) {
                if (n > 7) {
                    n = 0;
                    for (int j = 0; j < 140; ++j) {
                        this.ah();
                        this.ai();
                    }
                }
                if (n == 7) {
                    this.gf ^= p[i];
                    this.gg ^= p[i];
                }
                if (n < 7) {
                    final byte[] gh = this.gh;
                    final int n2 = n;
                    gh[n2] ^= p[i];
                    final byte[] gi = this.gi;
                    final int n3 = n;
                    gi[n3] ^= p[i];
                }
            }
            for (int k = 0; k < 140; ++k) {
                this.ah();
                this.ai();
            }
            for (int l = 0; l < 256; ++l) {
                this.ah();
                this.ai();
                this.gj[l] = this.ah();
                this.gk[l] = this.ai();
            }
            this.gc = true;
            return;
        }
        int n4 = length;
        if (this.gn) {
            return;
        }
        if (this.dh == 69 && n4 > 5) {
            n4 = 5;
        }
        final byte[] array = new byte[2 * n4];
        for (int n5 = 0; n5 < array.length; ++n5) {
            array[n5] = 0;
        }
        int n6 = 0;
        for (int n7 = 0; n7 < length; ++n7) {
            if (n6 >= n4) {
                n6 = 0;
            }
            final byte[] array2 = array;
            final int n8 = n6;
            array2[n8] += p[n7];
            final byte[] array3 = array;
            final int n9 = n6 + n4;
            array3[n9] += p[n7];
            ++n6;
        }
        for (int n10 = 0; n10 < n4 && n10 < this.g0.length; ++n10) {
            final byte[] array4 = array;
            final int n11 = n10;
            array4[n11] += this.g0[n10];
            final byte[] array5 = array;
            final int n12 = n10 + n4;
            array5[n12] += this.g1[n10];
        }
        this.j(array, 0, n4);
        this.k(array, 0, n4);
        abljem.d("Link encryption is RC4 with a 40-bit passphrase-based key");
    }
    
    private byte af() {
        if (!this.gc) {
            return this.ah();
        }
        this.ah();
        int ah = this.ah();
        if (ah < 0) {
            ah += 256;
        }
        final byte b = this.gj[ah];
        this.gj[ah] = this.ah();
        return b;
    }
    
    private byte ag() {
        if (!this.gc) {
            return this.ai();
        }
        this.ai();
        int ai = this.ai();
        if (ai < 0) {
            ai += 256;
        }
        final byte b = this.gk[ai];
        this.gk[ai] = this.ai();
        return b;
    }
    
    private byte ah() {
        final boolean b = false;
        if (this.gd < 0 || this.gd >= 7) {
            abljem.d("jem error 41");
            this.az = false;
            return (byte)(b ? 1 : 0);
        }
        final byte b2 = this.gh[this.gd++];
        if (this.gd >= 7) {
            final byte[] gh = this.gh;
            final int n = 0;
            gh[n] ^= this.gh[6];
            final byte[] gh2 = this.gh;
            final int n2 = 0;
            gh2[n2] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh3 = this.gh;
            final int n3 = 1;
            gh3[n3] ^= this.gh[0];
            final byte[] gh4 = this.gh;
            final int n4 = 1;
            gh4[n4] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh5 = this.gh;
            final int n5 = 2;
            gh5[n5] ^= this.gh[1];
            final byte[] gh6 = this.gh;
            final int n6 = 2;
            gh6[n6] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh7 = this.gh;
            final int n7 = 3;
            gh7[n7] ^= this.gh[2];
            final byte[] gh8 = this.gh;
            final int n8 = 3;
            gh8[n8] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh9 = this.gh;
            final int n9 = 4;
            gh9[n9] ^= this.gh[3];
            final byte[] gh10 = this.gh;
            final int n10 = 4;
            gh10[n10] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh11 = this.gh;
            final int n11 = 5;
            gh11[n11] ^= this.gh[4];
            final byte[] gh12 = this.gh;
            final int n12 = 5;
            gh12[n12] ^= this.gf;
            this.gf += (byte)this.gl;
            final byte[] gh13 = this.gh;
            final int n13 = 6;
            gh13[n13] ^= this.gh[5];
            final byte[] gh14 = this.gh;
            final int n14 = 6;
            gh14[n14] ^= this.gf;
            this.gf += (byte)this.gl;
            this.gd = 0;
        }
        return b2;
    }
    
    private byte ai() {
        final boolean b = false;
        if (this.ge < 0 || this.ge >= 7) {
            abljem.d("jem error 42");
            this.az = false;
            return (byte)(b ? 1 : 0);
        }
        final byte b2 = this.gi[this.ge++];
        if (this.ge >= 7) {
            final byte[] gi = this.gi;
            final int n = 0;
            gi[n] ^= this.gi[6];
            final byte[] gi2 = this.gi;
            final int n2 = 0;
            gi2[n2] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi3 = this.gi;
            final int n3 = 1;
            gi3[n3] ^= this.gi[0];
            final byte[] gi4 = this.gi;
            final int n4 = 1;
            gi4[n4] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi5 = this.gi;
            final int n5 = 2;
            gi5[n5] ^= this.gi[1];
            final byte[] gi6 = this.gi;
            final int n6 = 2;
            gi6[n6] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi7 = this.gi;
            final int n7 = 3;
            gi7[n7] ^= this.gi[2];
            final byte[] gi8 = this.gi;
            final int n8 = 3;
            gi8[n8] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi9 = this.gi;
            final int n9 = 4;
            gi9[n9] ^= this.gi[3];
            final byte[] gi10 = this.gi;
            final int n10 = 4;
            gi10[n10] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi11 = this.gi;
            final int n11 = 5;
            gi11[n11] ^= this.gi[4];
            final byte[] gi12 = this.gi;
            final int n12 = 5;
            gi12[n12] ^= this.gg;
            this.gg += (byte)this.gl;
            final byte[] gi13 = this.gi;
            final int n13 = 6;
            gi13[n13] ^= this.gi[5];
            final byte[] gi14 = this.gi;
            final int n14 = 6;
            gi14[n14] ^= this.gg;
            this.gg += (byte)this.gl;
            this.ge = 0;
        }
        return b2;
    }
    
    void h(final byte[] array, final int n, int n2) {
        int n3 = n;
        while (n2-- > 0) {
            final int n4 = n3++;
            array[n4] ^= this.af();
        }
    }
    
    void i(final byte[] array, final int n, int n2) {
        int n3 = n;
        while (n2-- > 0) {
            final int n4 = n3++;
            array[n4] ^= this.ag();
        }
    }
    
    public void j(final byte[] array, final int n, final int n2) {
        this.g2 = 0;
        this.g3 = 0;
        this.a(this.g0, array, n, n + n2);
    }
    
    public void k(final byte[] array, final int n, final int n2) {
        this.g4 = 0;
        this.g5 = 0;
        this.a(this.g1, array, n + n2, n + n2 + n2);
    }
    
    private void a(final byte[] array, final byte[] array2, final int n, final int n2) {
        for (int i = 0; i < 256; ++i) {
            array[i] = (byte)i;
        }
        int j = 0;
        int n3 = n;
        int n4 = 0;
        while (j < 256) {
            n4 = (n4 + array2[n3] + array[j] & 0xFF);
            final byte b = array[n4];
            array[n4] = array[j];
            array[j] = b;
            if (++n3 == n2) {
                n3 = n;
            }
            ++j;
        }
        for (int k = n; k < n2; ++k) {
            array2[k] = 0;
        }
    }
    
    void l(final byte[] array, final int n, final int n2) {
        this.g6 = this.g4;
        this.g7 = this.g5;
        this.a(array, n, n + n2, this.g1);
        this.g4 = this.g6;
        this.g5 = this.g7;
    }
    
    void m(final byte[] array, final int n, final int n2) {
        this.g6 = this.g2;
        this.g7 = this.g3;
        this.a(array, n, n + n2, this.g0);
        this.g2 = this.g6;
        this.g3 = this.g7;
    }
    
    private void a(final byte[] array, final int n, final int n2, final byte[] array2) {
        for (int i = n; i < n2; ++i) {
            this.g6 = (this.g6 + 1 & 0xFF);
            final byte b = array2[this.g6];
            this.g7 = (this.g7 + b & 0xFF);
            final byte b2 = array2[this.g7];
            array2[this.g6] = b2;
            array2[this.g7] = b;
            array[i] ^= array2[b2 + b & 0xFF];
        }
    }
    
    static int n(final byte[] array, final int n, final int n2) {
        return a(0, array, n, n2);
    }
    
    static int a(int n, final byte[] array, final int n2, int n3) {
        int n4 = n2;
        while (n3-- > 0) {
            n ^= array[n4++] << 8;
            for (int i = 0; i < 8; ++i) {
                if ((n & 0x8000) != 0x0) {
                    n = (n << 1 ^ 0x1021);
                }
                else {
                    n <<= 1;
                }
            }
        }
        n &= 0xFFFF;
        return n;
    }
    
    public boolean c(final boolean b) {
        if (this.ch && this.iq == null) {
            if (!b) {
                abljem.d("");
                abljem.d("Could not establish a dedicated TCP connection back to the server,");
                abljem.d("because of a problem with the server or your firewall,");
                abljem.d("and could not use HTTP tunneling for the reason below.");
                abljem.d("");
                abljem.d("To see if there is a server problem, contact the Webmaster at the server.");
                abljem.d("To configure your firewall, have your adminstrator contact the Webmaster.");
                abljem.d("To enable HTTP tunneling, see below.");
            }
            abljem.d("");
            abljem.d("This early version of Internet Explorer needs more access to your system");
            abljem.d("if it is to run HTTP tunneling at an acceptable speed.");
            abljem.d("You can use another browser (eg Netscape),");
            abljem.d("upgrade your version of Internet Explorer,");
            abljem.d("or ask the server Webmaster how to make use of a signed cabinet file.");
            abljem.d("");
            return false;
        }
        if (this.io.equalsIgnoreCase("comsserver")) {
            this.il = "";
            this.in = false;
        }
        else if (this.io.equalsIgnoreCase("event")) {
            this.im = "";
        }
        else if (!this.io.equalsIgnoreCase("default")) {
            abljem.d("Invalid HTTP_Tunnel_Handler parameter value " + this.io);
        }
        if (this.gq.equalsIgnoreCase("always")) {
            this.gp = true;
            if (!this.in) {
                throw new RuntimeException("SSL tunneling required but not supported by server");
            }
        }
        else {
            if (!this.gq.equalsIgnoreCase("never")) {
                throw new RuntimeException("Invalid SSL tunneling option \"" + this.gq + "\"");
            }
            this.gp = false;
        }
        if (this.gp && !this.gm && abljemza.authorized("HTTPS") == null) {
            throw new RuntimeException("Unsigned applet cannot SSL tunnel because was not loaded with HTTPS");
        }
        this.ii = new Random();
        this.if = "";
        this.ih = 0;
        this.ig = 0;
        final byte[] w = this.w();
        if (w == null) {
            return false;
        }
        final String s = new String(w, 0);
        if (s.length() < 4 || !s.substring(0, 3).equals("TVS")) {
            abljem.d("TVS invalid: " + s);
            return false;
        }
        this.if = s.substring(3);
        return this.ie = true;
    }
    
    public boolean a(final byte[] array) {
        ++this.ig;
        final String string = "C" + this.if + this.i(String.valueOf(this.ig)) + " ";
        while (System.currentTimeMillis() < System.currentTimeMillis() + 20000L) {
            final URLConnection aj = this.aj();
            if (aj != null) {
                final byte[] a = this.a(aj, string, array);
                if (a(a, 0, "OK")) {
                    return true;
                }
                String substring = "";
                if (a != null && a.length > 0) {
                    int length = a.length;
                    if (length > 50) {
                        length = 50;
                    }
                    substring = new String(a, 0, 0, length);
                    if (substring.startsWith("ERR") && !substring.startsWith("ERROR")) {
                        substring = substring.substring(3);
                    }
                }
                final String trim = substring.trim();
                abljem.d("Send failed" + ((trim.length() == 0) ? "" : (" " + trim)));
                try {
                    Thread.currentThread();
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        return this.az = false;
    }
    
    public byte[] w() {
        String string = "S";
        if (this.ih > 0) {
            string = String.valueOf(string) + this.if + this.i(String.valueOf(this.ih)) + "T";
        }
        final byte[] array = new byte[string.length()];
        string.getBytes(0, string.length(), array, 0);
        int n = 0;
        long n2 = 0L;
        while (n == 0 || System.currentTimeMillis() < n2) {
            if (this.ih > 0 && !this.az) {
                return null;
            }
            final URLConnection aj = this.aj();
            if (aj == null) {
                return null;
            }
            final byte[] a = this.a(aj, array);
            if (this.ih > 0 && !this.az) {
                return null;
            }
            array[array.length - 1] = 84;
            if (a(a, 0, "TVS")) {
                ++this.ih;
                return a;
            }
            if (a(a, 0, "NOD")) {
                n = 0;
                a(100L);
            }
            else {
                if (a(a, 0, "ERR")) {
                    if (this.az || this.ih == 0) {
                        this.a2 = new String(a, 0, 3, a.length - 3);
                    }
                    n = 0;
                    break;
                }
                if (a != null && a.length >= 4 && a[1] == this.dl && a[3] == this.dl) {
                    ++this.ih;
                    return a;
                }
                if (n == 0) {
                    n2 = System.currentTimeMillis() + 20000L;
                }
                ++n;
                abljem.d("TNLRCV unknown block start=" + c(a, 4));
                try {
                    Thread.currentThread();
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex) {}
            }
        }
        if (n > 0 && (this.az || this.ih == 0)) {
            abljem.d("Receive failed");
        }
        this.az = false;
        return null;
    }
    
    private URLConnection aj() {
        String s = "http";
        if (this.gp) {
            s = "https";
        }
        final String string = String.valueOf(s) + "://" + this.f.c().getHost() + this.im + this.il + "/" + this.ak() + "/response.txt";
        URL url;
        try {
            url = new URL(string);
        }
        catch (Exception ex) {
            abljem.d("TGD error: " + ex.getMessage());
            return null;
        }
        return this.a(url);
    }
    
    private String ak() {
        if (this.ik) {
            return this.i(String.valueOf(this.ii.nextLong()));
        }
        if (this.ij == 0L) {
            this.ij = this.ii.nextLong();
        }
        return this.i(String.valueOf(this.ij));
    }
    
    private String i(final String s) {
        final int length = s.length();
        if (length > 10) {
            return s.substring(length - 10);
        }
        if (length < 10) {
            return "0000000000".substring(length).concat(s);
        }
        return s;
    }
    
    public URLConnection a(final URL url) {
        URLConnection urlConnection = null;
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setAllowUserInteraction(false);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Connection", " close");
            urlConnection = openConnection;
        }
        catch (Exception ex) {
            abljem.d("Connect error: " + ex.getMessage());
        }
        return urlConnection;
    }
    
    public byte[] a(final URLConnection urlConnection, final byte[] array) {
        return this.a(urlConnection, null, array);
    }
    
    public byte[] a(final URLConnection urlConnection, String s, byte[] array) {
        byte[] a = null;
        if (urlConnection == null) {
            return null;
        }
        if (s != null && s.length() == 0) {
            s = null;
        }
        if (array != null && array.length == 0) {
            array = null;
        }
        try {
            if (s != null || array != null) {
                urlConnection.setDoOutput(true);
                final OutputStream outputStream = urlConnection.getOutputStream();
                if (s != null) {
                    final byte[] array2 = new byte[s.length()];
                    s.getBytes(0, s.length(), array2, 0);
                    outputStream.write(array2);
                }
                if (array != null) {
                    outputStream.write(array);
                }
                outputStream.flush();
                outputStream.close();
            }
            a = this.a(urlConnection);
        }
        catch (IOException ex) {
            if (this.az) {
                abljem.d("HTTP error: " + ex.getMessage());
                return null;
            }
        }
        return a;
    }
    
    public byte[] a(final URLConnection urlConnection) {
        byte[] array = null;
        try {
            final InputStream inputStream = urlConnection.getInputStream();
            int i;
            final int n = i = urlConnection.getContentLength();
            if (i > 0) {
                byte[] array2;
                for (array2 = new byte[i]; i > 0; i -= inputStream.read(array2, n - i, i)) {}
                inputStream.close();
                if (i == 0) {
                    array = array2;
                }
            }
        }
        catch (IOException ex) {
            if (this.az) {
                abljem.d("GetURL IOException - " + ex.getMessage());
            }
        }
        return array;
    }
    
    static {
        abljema.it = '#';
        final char[] array = new char[Styler.dd];
        for (int i = 0; i < array.length; ++i) {
            array[i] = ' ';
        }
        abljema.iv = new String(array);
        abljema.is = abljema.iv.getBytes();
        for (int j = 0; j < array.length; ++j) {
            array[j] = '0';
        }
        abljema.iw = new String(array);
        for (int k = 0; k < array.length; ++k) {
            array[k] = abljema.it;
        }
        abljema.iu = new String(array);
        (abljema.ix = new String[4])[0] = b('-');
        abljema.ix[1] = b('=');
        abljema.ix[2] = b('*');
        abljema.ix[3] = b('_');
    }
}
