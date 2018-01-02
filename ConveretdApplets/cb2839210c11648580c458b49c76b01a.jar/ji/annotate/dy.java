// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import ji.res.z;
import ji.graphic.bw;
import java.awt.event.ItemEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import ji.util.k;
import java.awt.Toolkit;
import java.awt.LayoutManager;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import ji.wang.ej;
import ji.awt.b4;
import ji.io.ac;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import ji.net.bi;
import java.io.IOException;
import ji.util.bh;
import ji.zip.a4;
import java.net.URL;
import ji.v1event.a6;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.GregorianCalendar;
import java.io.FileNotFoundException;
import java.awt.event.MouseEvent;
import ji.v1event.ob;
import java.awt.event.KeyEvent;
import ji.res.ay;
import java.awt.Window;
import ji.v1base.gr;
import java.awt.Dialog;
import java.awt.Frame;
import ji.graphic.b0;
import ji.util.d0;
import java.util.StringTokenizer;
import ji.secure.ei;
import ji.secure.dh;
import ji.font.j;
import ji.io.h;
import ji.font.ct;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.image.ImageObserver;
import java.awt.Shape;
import ji.graphic.d6;
import java.awt.Color;
import ji.document.cw;
import ji.v1event.d7;
import java.awt.Polygon;
import ji.util.e;
import ji.awt.d5;
import ji.awt.d4;
import ji.net.a0;
import ji.render.dt;
import ji.awt.ax;
import ji.v1event.d8;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import ji.graphic.og;
import ji.util.i;
import ji.v1event.af;
import java.awt.Graphics;
import ji.util.d;
import java.awt.Choice;
import java.awt.Label;
import java.awt.CheckboxGroup;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyAdapter;
import ji.v1base.ek;
import ji.v1base.jiPanel;
import ji.v1base.bl;
import ji.io.q;
import ji.graphic.bj;
import ji.awt.bb;
import ji.v1base.bz;
import ji.graphic.b3;
import ji.awt.c;
import ji.document.ad;
import ji.image.cy;
import ji.image.dx;
import ji.awt.dc;
import ji.awt.da;

public class dy
{
    private static Object a;
    private dz b;
    private boolean c;
    private boolean d;
    private String e;
    private double f;
    private int g;
    private int h;
    private da i;
    private da j;
    private da k;
    private da l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    static int s;
    private dc t;
    private dx u;
    private static int v;
    private static int w;
    private int x;
    private long y;
    private int z;
    private cy aa;
    private ad ab;
    private static int ac;
    private static int ad;
    private da ae;
    private int af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private double ak;
    private int al;
    private int am;
    private int an;
    private int ao;
    private int ap;
    private int aq;
    private int ar;
    private long as;
    private long at;
    private static boolean au;
    private static boolean av;
    private boolean aw;
    private boolean ax;
    private int ay;
    private boolean az;
    private int a0;
    private int a1;
    private int a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private static int a6;
    private int a7;
    private boolean a8;
    private c a9;
    private static int ba;
    private static int bb;
    private int bc;
    private int bd;
    private int be;
    private int bf;
    private int bg;
    private b3 bh;
    private bz bi;
    private int bj;
    private boolean bk;
    private boolean bl;
    private bb bm;
    private bb bn;
    private long bo;
    private boolean bp;
    private boolean bq;
    private boolean br;
    private boolean bs;
    private int bt;
    private int bu;
    private static int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    private long b0;
    private long b1;
    private double b2;
    private int b3;
    private int b4;
    private bj b5;
    private c b6;
    private boolean b7;
    private boolean b8;
    private boolean b9;
    private boolean ca;
    private boolean cb;
    private boolean cc;
    private double cd;
    private da ce;
    private String cf;
    private c cg;
    private q ch;
    private ee ci;
    private String cj;
    private boolean ck;
    private boolean cl;
    private boolean cm;
    private da cn;
    private da co;
    private Thread cp;
    private String cq;
    private bl cr;
    private jiPanel cs;
    private ek ct;
    private ek cu;
    private Thread cv;
    private KeyAdapter cw;
    private FocusAdapter cx;
    private ActionListener cy;
    private MouseListener cz;
    private WindowAdapter c0;
    private acf c1;
    private Component c2;
    private Button c3;
    private Button c4;
    private Checkbox c5;
    private Checkbox c6;
    private Checkbox c7;
    private Checkbox c8;
    private Checkbox c9;
    private CheckboxGroup da;
    private Button db;
    private Button dc;
    private jiPanel dd;
    private dg de;
    private Label df;
    private Label dg;
    private Label dh;
    private Label di;
    private Choice dj;
    private Choice dk;
    private Checkbox dl;
    private ek dm;
    private boolean dn;
    private boolean do;
    private String dp;
    private String dq;
    private int dr;
    private int ds;
    private String dt;
    private String du;
    private ed[] dv;
    private int dw;
    private int dx;
    private int dy;
    private int dz;
    private int d0;
    private int d1;
    private boolean d2;
    private boolean d3;
    private xf d4;
    private xf d5;
    private xf d6;
    private xf d7;
    private xf d8;
    private static String d9;
    private String ea;
    private String eb;
    private boolean ec;
    private String ed;
    private String ee;
    private c ef;
    private int eg;
    private int eh;
    private int ei;
    private boolean ej;
    private boolean ek;
    private boolean el;
    private boolean em;
    private double en;
    private d9 eo;
    protected eg ep;
    private int eq;
    private int er;
    private c es;
    private Object et;
    private boolean eu;
    private boolean ev;
    private int ew;
    private int ex;
    private boolean ey;
    private static final boolean ez;
    private boolean e0;
    private boolean e1;
    private c e2;
    boolean e3;
    private Object e4;
    private String e5;
    private int e6;
    private String e7;
    vn e8;
    
    public final void a(final c es) {
        this.es = es;
    }
    
    private String c(final int n) {
        return ji.util.d.b(n, this.ed);
    }
    
    public final boolean a() {
        return this.bj < 0;
    }
    
    public void a(final int n, final int n2, final Graphics graphics, final ad ad, final af af) {
        try {
            if (this.bj >= 0) {
                final xf xf = (xf)this.a9.b(this.bj);
                if (this.c(xf.e())) {
                    xf.b(1);
                    this.a(xf, graphics, ad, af, true, this.aa.b3());
                    this.bw = -1;
                    this.bx = -1;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Component component, final int n, final int n2, final Graphics graphics, final ad ad, final af af) {
        try {
            if (this.bj >= 0) {
                this.a3 = true;
                final xf xf = (xf)this.a9.b(this.bj);
                xf.b(2);
                final dg b3 = this.aa.b3();
                this.a(xf, graphics, ad, af, true, b3);
                final String e = xf.e();
                if (e != null && this.aa != null) {
                    ji.util.d.d4();
                    this.z();
                    final String lowerCase = e.toLowerCase();
                    this.w();
                    if (this.c(lowerCase)) {
                        if (lowerCase.equals("annfix")) {
                            this.ag();
                        }
                        else if (lowerCase.equals("annback")) {
                            this.aa.e6();
                        }
                        else if (lowerCase.equals("anndelete")) {
                            this.aa.fr();
                        }
                        else if (lowerCase.equals("annheadmore")) {
                            this.aa.fc();
                        }
                        else if (lowerCase.equals("annheadless")) {
                            this.aa.fd();
                        }
                        else if (lowerCase.equals("annrotatel")) {
                            this.aa.fe();
                        }
                        else if (lowerCase.equals("annrotater")) {
                            this.aa.ff();
                        }
                        else if (lowerCase.equals("annlinewidthmore")) {
                            this.aa.fb();
                        }
                        else if (lowerCase.equals("annlinewidthmore2")) {
                            this.aa.fb();
                        }
                        else if (lowerCase.equals("annlinewidthless")) {
                            this.aa.fm();
                        }
                        else if (lowerCase.equals("annlinewidthless2")) {
                            this.aa.fm();
                        }
                        else if (lowerCase.equals("annlinecolor")) {
                            this.aa.fn();
                            try {
                                this.aa.ap(true);
                            }
                            catch (Exception ex) {}
                        }
                        else if (lowerCase.equals("anntxcolor")) {
                            this.aa.fo();
                        }
                        else if (lowerCase.equals("annstrikethrough")) {
                            this.aa.fp();
                            try {
                                this.aa.ap(true);
                            }
                            catch (Exception ex2) {}
                        }
                        else if (lowerCase.equals("annfillcolor")) {
                            this.aa.fq();
                            try {
                                this.aa.ap(true);
                            }
                            catch (Exception ex3) {}
                        }
                        else if (lowerCase.equals("anntransparent")) {
                            this.aa.e7();
                        }
                        if (lowerCase.equals("annalignment")) {
                            final dg b4 = this.aa.b3();
                            if (b4 != null) {
                                b4.gg();
                                this.aa.e8();
                            }
                        }
                        else if (lowerCase.equals("annedittext")) {
                            new bb(this.ed, new aac()).start();
                        }
                        else if (lowerCase.equals("anneditsecurity")) {
                            this.aa.e1();
                        }
                        else if (lowerCase.equals("annhyperlink")) {
                            this.aa.e4();
                            try {
                                this.aa.ap(true);
                            }
                            catch (Exception ex4) {}
                        }
                        else if (lowerCase.equals("annangleflip")) {
                            this.aa.fa();
                        }
                        else if (lowerCase.equals("annfont") && ji.util.i.c(247)) {
                            final og og = new og();
                            final Point locationOnScreen = this.aa.getLocationOnScreen();
                            final Dimension size = this.aa.getSize();
                            final Rectangle rectangle = new Rectangle(locationOnScreen.x, locationOnScreen.y, size.width, size.height);
                            this.aa.ci(false);
                            try {
                                this.f(false);
                                final Font a = og.a(this.aa, this.ed, this.aa.e9(), this.aa.b5(), b3.cu(), rectangle, b3.a0());
                                if (a != null) {
                                    this.aa.a(a, og.b(), og.c(), og.d(), !og.a());
                                }
                            }
                            finally {
                                this.f(true);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex5) {}
        this.bw = -1;
        this.bx = -1;
    }
    
    public void b(final int n, final int n2, final Graphics graphics, final ad ad, final af af) {
        try {
            if (this.p()) {
                return;
            }
            this.bj = -1;
            if (this.ae != null) {
                final dg b3 = this.aa.b3();
                if (this.ae.a(n, n2)) {
                    boolean b4 = false;
                    this.bk = true;
                    for (int i = 0; i < this.bc; ++i) {
                        final xf xf = (xf)this.a9.b(i);
                        final da f = xf.f();
                        if (f.a(n, n2) && this.c(xf.e())) {
                            if (xf.g() != 2) {
                                xf.b(2);
                                this.a(xf, graphics, ad, af, true, b3);
                            }
                            b4 = true;
                            this.bj = i;
                            this.b(xf.h());
                            if (!this.a3 && this.ey) {
                                this.a(xf.h(), (int)f.a, (int)(f.b - f.d * 5 / 2), false);
                            }
                        }
                        else if (xf.g() != 0) {
                            this.a3 = false;
                            xf.b(0);
                            this.a(xf, graphics, ad, af, false, b3);
                        }
                    }
                    if (!b4) {
                        this.bk = false;
                        this.b("");
                        this.ad();
                        this.a3 = false;
                    }
                }
                else {
                    for (int j = 0; j < this.bc; ++j) {
                        final xf xf2 = (xf)this.a9.b(j);
                        if (xf2.g() != 0) {
                            xf2.b(0);
                            this.a(xf2, graphics, ad, af, false, b3);
                            this.b("");
                            this.ad();
                        }
                    }
                    this.a3 = false;
                    this.bk = false;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final String s) {
        try {
            if (this.aa != null) {
                this.aa.u(String.valueOf(String.valueOf(s)).concat("..."));
            }
        }
        catch (Exception ex) {}
    }
    
    private final Dimension ab() {
        try {
            if (this.aa != null) {
                return this.aa.c5();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final int ac() {
        try {
            if (this.aa != null) {
                return this.aa.ea();
            }
            return 1;
        }
        catch (Exception ex) {
            return 1;
        }
    }
    
    private final void ad() {
        this.bl = false;
        this.bm = null;
        this.bk = false;
        this.a(new d8(this.aa, 2, 0, 0));
    }
    
    public final void a(final boolean d3) {
        this.d3 = d3;
    }
    
    public final void a(final boolean cl, final boolean em) {
        this.cl = cl;
        this.em = em;
    }
    
    public final void b(final boolean a4) {
        this.a4 = a4;
    }
    
    private final void a(final String s, final int n, final int n2, final boolean b) {
        if (this.aa != null) {
            if (this.p()) {
                return;
            }
            this.aa.d(s);
            if (!this.bl) {
                if (this.bm == null) {
                    (this.bm = new bb(this.ed, new aam(n, n2, b))).start();
                }
            }
            else {
                this.a(new d8(this.aa, 1, n, n2, s, b));
            }
        }
    }
    
    private final void a(final d8 d8) {
        try {
            if (this.aa != null) {
                this.aa.fireTipEvent(d8);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void ae() {
        try {
            if (this.bi != null) {
                this.bi.releaseResources();
                this.bi = null;
            }
            if (this.a9 != null) {
                this.a9.c();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final Component component) {
        try {
            if (this.a9 == null || this.bh == null || this.bi == null) {
                if (this.a9 != null) {
                    this.a9.c();
                }
                this.bh = new b3();
                this.bi = new bz(this.ed);
                this.bh.a(0);
                this.bh.b(component.getBackground().darker().darker());
                this.bh.c(component.getBackground().brighter());
                (this.a9 = new c("jiAnnotate2")).c(new xf("annfix", 15, 15, 450, 0, "save"));
                this.a9.c(new xf("anneditsecurity", 15, 15, 466, 16, "security"));
                this.a9.c(new xf("annedittext", 15, 15, 458, 5, "text"));
                if (ji.util.i.c(247)) {
                    this.a9.c(this.d8 = new xf("annfont", 15, 15, 1267, 23, "changeFont"));
                }
                this.a9.c(new xf("annlinewidthmore", 15, 15, 454, 4, "increaseline"));
                this.a9.c(new xf("annlinewidthmore2", 15, 15, 456, 4, "increasefont"));
                this.a9.c(new xf("annlinewidthless", 15, 15, 455, 7, "decreaseline"));
                this.a9.c(new xf("annlinewidthless2", 15, 15, 457, 7, "decreasefont"));
                this.a9.c(new xf("annlinecolor", 15, 15, 453, 3, "linecolor"));
                this.a9.c(new xf("annfillcolor", 15, 15, 452, 2, "fillcolor"));
                this.a9.c(new xf("anntxcolor", 15, 15, 646, 2, "txcolor"));
                if (!ji.util.i.c(247)) {
                    this.a9.c(this.d7 = new xf("annstrikethrough", 15, 15, 1089, 19, 20, "strikethrough"));
                }
                this.a9.c(this.d4 = new xf("anntransparent", 15, 15, 491, 10, 11, "transparent"));
                if (ji.util.i.c(150)) {
                    this.a9.c(this.d5 = new xf("annalignment", 15, 15, 1167, 21, 22, "alignment"));
                }
                this.a9.c(new xf("annhyperlink", 15, 15, 460, 6, "hyperlink"));
                this.a9.c(new xf("annback", 15, 15, 463, 9, "behind"));
                this.a9.c(new xf("annheadmore", 15, 15, 464, 12, "increasearrowhead"));
                this.a9.c(new xf("annheadless", 15, 15, 465, 13, "decreasearrowhead"));
                this.a9.c(this.d6 = new xf("annangleflip", 15, 15, 784, 17, 18, "angleflip"));
                if (ji.util.d.a5()) {
                    this.a9.c(new xf("annrotater", 15, 15, 122, 15, "rotater"));
                    this.a9.c(new xf("annrotatel", 15, 15, 123, 14, "rotatel"));
                }
                this.a9.c(new xf("anndelete", 15, 15, 451, 1, "delete"));
            }
        }
        catch (Exception ex) {}
    }
    
    public dy(final String ed, final int eq, final boolean d, final String e) {
        this.c = false;
        this.d = false;
        this.e = null;
        this.n = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.t = null;
        this.u = null;
        this.x = -1;
        this.y = -1L;
        this.z = -1;
        this.aa = null;
        this.ab = null;
        this.ae = null;
        this.af = 40;
        this.ag = 40;
        this.ah = 20;
        this.ai = 60;
        this.aj = 4;
        this.ak = 0.0;
        this.al = 0;
        this.am = 0;
        this.an = 0;
        this.ao = 0;
        this.ap = 0;
        this.aq = 0;
        this.ar = 0;
        this.as = 0L;
        this.at = 0L;
        this.aw = false;
        this.ax = false;
        this.ay = -1;
        this.az = true;
        this.a0 = 0;
        this.a1 = 0;
        this.a2 = 0;
        this.a3 = false;
        this.a4 = false;
        this.a5 = true;
        this.a7 = ji.annotate.dy.a6;
        this.a8 = false;
        this.a9 = null;
        this.bc = 0;
        this.bd = 0;
        this.be = 0;
        this.bf = 0;
        this.bg = 2;
        this.bh = null;
        this.bi = null;
        this.bj = -1;
        this.bk = false;
        this.bl = false;
        this.bm = null;
        this.bn = null;
        this.bo = 0L;
        this.bp = false;
        this.bq = false;
        this.br = false;
        this.bs = false;
        this.bt = 0;
        this.bu = 0;
        this.bw = -1;
        this.bx = -1;
        this.by = 0;
        this.bz = 0;
        this.b0 = 0L;
        this.b1 = 0L;
        this.b2 = 1.0;
        this.b3 = 0;
        this.b4 = 0;
        this.b5 = null;
        this.b6 = new c("jiAnnotateDialogs");
        this.b7 = true;
        this.b8 = false;
        this.b9 = false;
        this.ca = false;
        this.cb = false;
        this.cc = true;
        this.cd = 0.0;
        this.ce = null;
        this.cf = null;
        this.cg = new c("jiAnnotate1");
        this.ch = null;
        this.ci = null;
        this.cj = null;
        this.ck = true;
        this.cl = false;
        this.cm = false;
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
        this.dn = true;
        this.do = false;
        this.dp = null;
        this.dq = null;
        this.dr = -1;
        this.ds = -1;
        this.dt = null;
        this.du = null;
        this.dv = null;
        this.dw = 0;
        this.dx = 0;
        this.dy = 0;
        this.dz = 0;
        this.d0 = 0;
        this.d1 = 0;
        this.d2 = false;
        this.d3 = false;
        this.d4 = null;
        this.d5 = null;
        this.d6 = null;
        this.d7 = null;
        this.d8 = null;
        this.ea = null;
        this.eb = null;
        this.ec = false;
        this.ed = null;
        this.ee = null;
        this.ef = null;
        this.eg = 0;
        this.eh = 0;
        this.ei = 0;
        this.ej = false;
        this.ek = false;
        this.el = false;
        this.em = false;
        this.en = 1.0;
        this.eo = null;
        this.ep = null;
        this.eq = 0;
        this.er = -1;
        this.es = null;
        this.et = null;
        this.eu = ji.util.d.d8;
        this.ev = false;
        this.ew = 0;
        this.ex = 0;
        this.ey = false;
        this.e0 = false;
        this.e1 = false;
        this.e2 = null;
        this.e3 = false;
        this.e4 = new Object();
        this.e5 = null;
        this.e6 = 0;
        this.e7 = "";
        this.e8 = null;
        this.ed = ed;
        this.eq = eq;
        this.d = d;
        this.e = e;
    }
    
    public void a(final cy aa, final int eq, final boolean d, final String e, final Object et, final boolean b) {
        this.aa = aa;
        this.eq = eq;
        this.d = d;
        this.e = e;
        this.et = et;
        this.b = new dz(this.ed, e, d, aa, b);
    }
    
    public void c(final boolean eu) {
        this.eu = eu;
    }
    
    public void a(final boolean cm, final da da, final boolean ek) {
        this.cm = cm;
        this.ek = ek;
        if (da != null) {
            this.cn = new da(da);
        }
    }
    
    public static final dg[] a(final df df, final int n, final int n2, final int n3) {
        final c c = new c("jiAnnotate3");
        if (df != null && df.d() > 0) {
            final ax g = df.g();
            if (n2 >= 0) {
                if (n >= 0) {
                    while (g.a()) {
                        final dg b = df.b(g.b());
                        if (b.i(n) == n && b.d5() == n2) {
                            c.c(b);
                        }
                    }
                }
                else {
                    while (g.a()) {
                        final dg b2 = df.b(g.b());
                        if (b2.d5() == n2) {
                            c.c(b2);
                        }
                    }
                }
            }
            else if (n >= 0) {
                while (g.a()) {
                    final dg b3 = df.b(g.b());
                    if (b3.i(n) == n) {
                        c.c(b3);
                    }
                }
            }
            else {
                while (g.a()) {
                    c.c(df.b(g.b()));
                }
            }
        }
        final c c2 = new c("sortdItems");
        for (int i = 0; i < c.b(); ++i) {
            final dg dg = (dg)c.b(i);
            if (n3 == 1) {
                final String concat = "0000000000".concat(String.valueOf(String.valueOf(dg.i(1))));
                concat.substring(concat.length() - 10, concat.length());
                final String concat2 = "0000000000".concat(String.valueOf(String.valueOf(dg.fn().a)));
                final String substring = concat2.substring(concat2.length() - 10, concat2.length());
                final String concat3 = "0000000000".concat(String.valueOf(String.valueOf(dg.fn().b)));
                final String substring2 = concat3.substring(concat3.length() - 10, concat3.length());
                final String concat4 = "0000000000".concat(String.valueOf(String.valueOf(dg.ce())));
                c2.a(String.valueOf(String.valueOf(new StringBuffer("").append(dg.i(1)).append(substring2).append(substring).append(concat4.substring(concat4.length() - 10, concat4.length())).append(":").append(dg.du()).append(":").append(i))), dg);
            }
            else {
                final String concat5 = "0000000000".concat(String.valueOf(String.valueOf(dg.ce())));
                c2.a(String.valueOf(String.valueOf(new StringBuffer("").append(concat5.substring(concat5.length() - 10, concat5.length())).append(":").append(dg.du()).append(":").append(i))), dg);
            }
        }
        final dg[] array = new dg[c.b()];
        final ax d = c2.d();
        int n4 = 0;
        while (d.a()) {
            array[n4++] = (dg)c2.d(d.b());
        }
        return array;
    }
    
    public static dg a(final df df, final String s) {
        if (df != null && df.d() > 0) {
            final c c = new c("newList");
            final ax g = df.g();
            int max = 0;
            while (g.a()) {
                max = Math.max(max, df.b(g.b()).ce());
            }
            final ax g2 = df.g();
            int n = -2;
            while (g2.a()) {
                final dg b = df.b(g2.b());
                if (b.gh()) {
                    c.a("".concat(String.valueOf(String.valueOf(b.ce()))), b);
                }
                else {
                    c.a("".concat(String.valueOf(String.valueOf(n--))), b);
                }
            }
            final ax d = c.d();
            final String lowerCase = s.toLowerCase();
            while (d.a()) {
                final dg dg = (dg)c.d(d.b());
                if (dg.du().toLowerCase().equals(lowerCase)) {
                    return dg;
                }
            }
        }
        return null;
    }
    
    public void a(final Component component, final af af, final df df, final dx dx, final int n, final ad ad) {
        if (df != null && df.d() > 0) {
            final ax g = df.g();
            while (g.a()) {
                final dg b = df.b(g.b());
                if (b.i(n) == n) {
                    b.a(component, af, dx, this.e, ad);
                }
            }
        }
    }
    
    public final void a(final dx u, final dt dt, final Graphics graphics, final df df, final double f, final int g, final int h, final boolean b, final da da, final da da2, final int m, final boolean q, final Component component, final ad ad, final da l, final dc t, final da k, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final int b6, final int b7, int max, final af af, final a0 a0, final boolean b8, final boolean b9, final int n, final boolean ax) throws Exception {
        Rectangle clipBounds = null;
        try {
            this.a8 = true;
            this.el = false;
            if (graphics != null) {
                clipBounds = graphics.getClipBounds();
            }
            if (df != null) {
                if (!b8) {
                    this.g();
                }
                if (df.d() > 0) {
                    this.f = f;
                    this.ax = ax;
                    this.u = u;
                    this.g = g;
                    this.h = h;
                    this.o = (b && ji.util.i.c(163));
                    this.i = new da(da);
                    this.j = new da(da2);
                    this.m = m;
                    this.q = q;
                    this.l = l;
                    this.t = t;
                    this.k = k;
                    this.b3 = b6;
                    this.b4 = b7;
                    max = Math.max(max, 1);
                    final c c = new c("newAnnotList");
                    if (b5) {
                        final ax g2 = df.g();
                        int n2 = df.d();
                        int max2 = 0;
                        final boolean b10 = false;
                        while (g2.a()) {
                            final dg b11 = df.b(g2.b());
                            if (!b11.d2() && !b11.dy() && b11.i(max) == max && b10) {
                                c.a("".concat(String.valueOf(String.valueOf(b11.ce()))), b11);
                                max2 = Math.max(max2, b11.ce());
                                n2 = Math.min(n2, b11.ce());
                            }
                        }
                        if (c.b() > 0) {
                            this.a(u, c, dt, graphics, component, ad, n2, max2, max, af, a0, b8, b9, n);
                        }
                        final ax g3 = df.g();
                        c.c();
                        int n3 = df.d();
                        int max3 = 0;
                        while (g3.a()) {
                            final dg b12 = df.b(g3.b());
                            if (!b12.d2() && !b12.dy() && b12.i(max) == max) {
                                c.a("".concat(String.valueOf(String.valueOf(b12.ce()))), b12);
                                max3 = Math.max(max3, b12.ce());
                                n3 = Math.min(n3, b12.ce());
                            }
                        }
                        if (c.b() > 0) {
                            this.a(u, c, dt, graphics, component, ad, n3, max3, max, af, a0, b8, b9, n);
                        }
                    }
                    if (b4) {
                        final ax g4 = df.g();
                        c.c();
                        int n4 = df.d();
                        int n5 = 0;
                        while (g4.a()) {
                            final dg b13 = df.b(g4.b());
                            if (b13.d2() && !b13.dy() && b13.i(max) == max) {
                                if (b13.d5() == 8 && q) {
                                    if (!ad.kg()) {
                                        continue;
                                    }
                                    c.a("".concat(String.valueOf(String.valueOf(b13.ce()))), b13);
                                    n5 = Math.max(n5, b13.ce());
                                    n4 = Math.min(n4, b13.ce());
                                }
                                else {
                                    c.a("".concat(String.valueOf(String.valueOf(b13.ce()))), b13);
                                    n5 = Math.max(n5, b13.ce());
                                    n4 = Math.min(n4, b13.ce());
                                }
                            }
                        }
                        if (c.b() > 0) {
                            this.a(u, c, dt, graphics, component, ad, n4, n5, max, af, a0, b8, b9, n);
                        }
                    }
                    if (b3) {
                        final ax g5 = df.g();
                        c.c();
                        int n6 = df.d();
                        int max4 = 0;
                        while (g5.a()) {
                            final dg b14 = df.b(g5.b());
                            if (b14.d2() && b14.dy() && b14.i(max) == max) {
                                c.a("".concat(String.valueOf(String.valueOf(b14.ce()))), b14);
                                max4 = Math.max(max4, b14.ce());
                                n6 = Math.min(n6, b14.ce());
                            }
                        }
                        if (c.b() > 0) {
                            this.a(u, c, dt, graphics, component, ad, n6, max4, max, af, a0, b8, b9, n);
                        }
                    }
                    if (b2) {
                        final ax g6 = df.g();
                        c.c();
                        int n7 = df.d();
                        int max5 = 0;
                        while (g6.a()) {
                            final dg b15 = df.b(g6.b());
                            if (b15.d2() && b15.i(max) == max) {
                                c.a("".concat(String.valueOf(String.valueOf(b15.ce()))), b15);
                                max5 = Math.max(max5, b15.ce());
                                n7 = Math.min(n7, b15.ce());
                            }
                        }
                        if (c.b() > 0) {
                            this.a(u, c, dt, graphics, component, ad, n7, max5, max, af, a0, b8, b9, n);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (clipBounds != null) {
                    graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
                }
            }
            catch (Exception ex2) {}
            this.a8 = false;
        }
    }
    
    private final void a(final dx dx, final c c, final dt dt, final Graphics graphics, final Component component, final ad ad, final int n, final int n2, final int n3, final af af, final a0 a0, final boolean b, final boolean b2, final int n4) throws Exception {
        if (ji.util.i.c(257)) {
            for (int i = n2; i >= n; --i) {
                this.a(dx, c, dt, graphics, component, ad, n3, af, a0, b, b2, n4, "".concat(String.valueOf(String.valueOf(i))));
            }
        }
        else {
            for (int j = n; j <= n2; ++j) {
                this.a(dx, c, dt, graphics, component, ad, n3, af, a0, b, b2, n4, "".concat(String.valueOf(String.valueOf(j))));
            }
        }
    }
    
    private void a(final dx dx, final c c, final dt dt, final Graphics graphics, final Component component, final ad ad, final int n, final af af, final a0 a0, final boolean b, final boolean b2, final int n2, final String s) {
        try {
            final dg dg = (dg)c.d(s);
            if (dg != null && dg.i(n) == n) {
                this.a(dx, dt, graphics, dg, component, ad, n, af, a0, b, b2, n2);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void a(final da da, final da da2, final da l) {
        this.i = new da(da);
        this.j = new da(da2);
        this.l = l;
    }
    
    public final void a(final dx u, final dt dt, final Graphics graphics, final dg dg, final df df, final double f, final int g, final int h, final boolean b, final da da, final da da2, final int m, final boolean q, final Component component, final ad ad, final da l, final dc t, final da k, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final int b6, final int b7, int max, final af af, final a0 a0, final boolean b8, final boolean b9, final int n, final boolean ax) throws Exception {
        Rectangle clipBounds = null;
        try {
            if (graphics != null) {
                clipBounds = graphics.getClipBounds();
            }
            if (dg != null) {
                this.f = f;
                this.u = u;
                this.g = g;
                this.h = h;
                this.o = (b && ji.util.i.c(163));
                this.i = new da(da);
                this.j = new da(da2);
                this.m = m;
                this.q = q;
                this.l = l;
                this.t = t;
                this.k = k;
                this.b3 = b6;
                this.b4 = b7;
                this.ax = ax;
                max = Math.max(max, 1);
                boolean b10 = !b5;
                if (!b3 && dg.d2()) {
                    b10 = false;
                }
                if (!b4 && !dg.d2()) {
                    b10 = false;
                }
                if (b8) {
                    b10 = true;
                }
                if (b10) {
                    this.a(u, dt, graphics, dg, component, ad, max, af, a0, b8, b9, n);
                }
            }
        }
        catch (Exception ex) {
            try {
                if (clipBounds != null) {
                    graphics.setClip(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
                }
            }
            catch (Exception ex2) {}
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final boolean b() {
        return this.cl;
    }
    
    public final boolean c() {
        return this.d3;
    }
    
    public final boolean d() {
        return this.d2;
    }
    
    public final void e() {
        this.d2 = false;
    }
    
    private final void a(final dx dx, final dt dt, final Graphics graphics, final dg dg, final Component component, final ad ad, int max, final af af, final a0 a0, boolean b, final boolean b2, final int n) throws Exception {
        try {
            if (this.q) {
                if (dg.bh()) {
                    return;
                }
            }
            else if (this.a4) {
                final boolean e = dg.e(1);
                if (dg.bh() && !e) {
                    return;
                }
                if (ad != null && e && !ad.bi(35)) {
                    return;
                }
            }
            boolean b3 = ji.util.d.c5();
            if (this.aa != null && this.aa.a() != null) {
                b3 = this.aa.a().cp();
            }
            if (b3) {
                return;
            }
            if (this.a4) {
                boolean b4 = false;
                if (ad != null) {
                    b4 = ad.b();
                }
                try {
                    if (dg.dk() == null) {
                        dg.a(ad.a(max), ad.kb(), ad.kc(), ad.kn().a);
                    }
                }
                catch (Exception ex2) {}
                if (b4 && dg.dk() == null) {
                    return;
                }
            }
            else {
                dg.a(dx.ac, dx.ad, dx.a);
                final Dimension dn = dg.dn();
                if (!ji.util.d.bq() && dn == null && dx != null && dx.j() != null) {
                    final Dimension a2 = dx.j().a();
                    if (a2 != null) {
                        dg.a(a2, max);
                    }
                }
                final Dimension dk = dg.dk();
                final Dimension ab = this.ab();
                if (dk == null) {
                    if (dg.dm()) {
                        dg.b(ab, dx.ac, dx.ad);
                        this.a(component, af, dg, dx, ad);
                    }
                    else {
                        final Dimension a3 = dx.j().a();
                        if (!a3.equals(ab)) {
                            dg.b(a3, max);
                        }
                        dg.a(ab, dx.ac, dx.ad, dx.a);
                        dg.as(true);
                        if (!a3.equals(ab)) {
                            this.a(component, af, dg, dx, ad);
                        }
                    }
                    this.d2 = true;
                }
                else if (dk != null && ab != null && !dk.equals(ab)) {
                    dg.a(ab, dx.ac, dx.ad, dx.a);
                    dg.as(true);
                    this.a(component, af, dg, dx, ad);
                }
            }
            if (graphics != null && this.l != null) {
                ji.util.d.a(graphics, this.l.a());
            }
            if (dg != null) {
                max = Math.max(max, 1);
                if (dg.be() && ad.ig()) {
                    b = true;
                }
                if (dg.e0() && dg.k() && dg.i(max) == max && dg.a(this.q, this.e, this.d)) {
                    dg.ar(this.a4);
                    if (!b) {
                        dg.ah(false);
                        dg.ae(false);
                    }
                    switch (dg.d4()) {
                        case 15: {}
                        case 6:
                        case 8: {
                            if (b) {
                                this.a(graphics, dg, component, b2, af, n);
                                break;
                            }
                            if (ji.util.d.b()) {
                                synchronized (ji.annotate.dy.a) {
                                    if (this.b != null) {
                                        this.b.a(dx, dt, graphics, dg, component, ad, af, a0, max, this, this.ed, this.l, this.b7, this.q, this.h, this.f, this.ak, this.x, this.y, this.eq, this.a4, this.d3, this.cl, this.em, this.o, this.en, this.ev, this.es, this.g);
                                    }
                                    // monitorexit(dy.a)
                                    break;
                                }
                            }
                            if (this.b != null) {
                                this.b.a(dx, dt, graphics, dg, component, ad, af, a0, max, this, this.ed, this.l, this.b7, this.q, this.h, this.f, this.ak, this.x, this.y, this.eq, this.a4, this.d3, this.cl, this.em, this.o, this.en, this.ev, this.es, this.g);
                                break;
                            }
                            break;
                        }
                        case 0: {
                            if (b) {
                                this.a(graphics, dg, component, b2, af, n);
                                break;
                            }
                            this.a(dx, dt, graphics, dg, component, ad, af);
                            break;
                        }
                        case 3:
                        case 4: {
                            if (b) {
                                this.a(graphics, dg, component, b2, af, n);
                                break;
                            }
                            this.b(dx, dt, graphics, dg, component, ad, af);
                            break;
                        }
                        case 1: {
                            if (b) {
                                this.a(graphics, dg, component, b2, af, n);
                                break;
                            }
                            this.a(dx, dt, graphics, dg, component, ad, af, true);
                            break;
                        }
                        case 2:
                        case 5: {
                            if (dg.e2()) {
                                int n2 = 0;
                                if (dg.e5()) {
                                    n2 = 1;
                                }
                                if (this.d6 != null && this.d6.d() != n2) {
                                    this.d6.a(n2);
                                    if (!this.bp) {
                                        this.a(graphics, component, af, dg);
                                    }
                                }
                            }
                            final int m = this.m;
                            if (dg.d5() == 8) {
                                this.m = 0;
                            }
                            try {
                                if (b) {
                                    this.a(graphics, dg, component, b2, af, n);
                                }
                                else {
                                    this.a(dx, dt, graphics, dg, ad, af);
                                }
                            }
                            finally {
                                if (dg.d5() == 8) {
                                    this.m = m;
                                }
                            }
                            break;
                        }
                    }
                    if (dg.d4() == 7 || dg.d4() == 9) {
                        if (this.d4 != null && dg.dy()) {
                            int n3 = 0;
                            if (dg.dz()) {
                                n3 = 1;
                            }
                            if (this.d4.d() != n3) {
                                this.d4.a(n3);
                                if (!this.bp) {
                                    this.a(graphics, component, af, dg);
                                }
                            }
                        }
                        if (this.d5 != null && dg.dy()) {
                            this.a(graphics, dg, component, af);
                        }
                        if (this.d7 != null && dg.dy()) {
                            int n4 = 0;
                            if (dg.i()) {
                                n4 = 1;
                            }
                            if (this.d7.d() != n4) {
                                this.d7.a(n4);
                                if (!this.bp) {
                                    this.a(graphics, component, af, dg);
                                }
                            }
                        }
                    }
                    if (ji.util.d.ls && dg.b(this.g)) {
                        dg.ab(false);
                    }
                }
                else if (dg.d4() != 16 && dg.d4() != 17 && this.d3 && !this.a4) {
                    dg.aj(false);
                }
            }
            dg.k(true);
            dg.r(false);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final void a(final Graphics graphics, final dg dg, final Component component, final af af) {
        int n = 0;
        if (dg.gf() == 0) {
            n = 0;
        }
        if (dg.gf() == 1) {
            n = 1;
        }
        if (this.d5.d() != n) {
            this.d5.a(n);
            if (!this.bp) {
                this.a(graphics, component, af, dg);
            }
        }
    }
    
    public final int a(final int n, final int n2, final dg dg) {
        final int n3 = -1;
        this.r = false;
        if (dg != null) {
            try {
                Block_4: {
                    for (int fy = dg.fy(), i = 0; i < fy; ++i) {
                        if (dg.ad(i).a(n, n2)) {
                            break Block_4;
                        }
                    }
                    return n3;
                }
                int i = 0;
                int ae = dg.ae(i);
                if (ae < 0) {
                    ae = 999;
                    this.r = true;
                }
                if ((dg.d4() == 4 || dg.d4() == 5) && ae == 2) {
                    ae = 999;
                    this.r = true;
                }
                if (this.r && !dg.ez()) {
                    this.r = false;
                    return -1;
                }
                dg.ac(i);
                return ae;
            }
            catch (Exception ex) {}
        }
        return n3;
    }
    
    private final void a(final dg dg, final Graphics graphics, final dt dt) {
        try {
            if (dg != null) {
                switch (dg.d4()) {
                    case 4:
                    case 5: {
                        if (dg.f6() == null) {
                            final d5 fn = dg.fn();
                            final d5 fo = dg.fo();
                            final d4 d4 = new d4();
                            d4.a(fn);
                            d4.a(fo);
                            dg.e(d4);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (dg.f6() == null) {
                            final d4 d5 = new d4();
                            final d5 fn2 = dg.fn();
                            final long fk = dg.fk();
                            final long fl = dg.fl();
                            d5.a(fn2);
                            d5.addPoint((int)fk, (int)fl);
                            dg.e(d5);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (dg.f6() == null) {
                            final da ff = dg.ff();
                            final d4 d6 = new d4();
                            d6.addPoint((int)ff.a, (int)ff.b);
                            d6.addPoint((int)(ff.a + ff.c), (int)ff.b);
                            d6.addPoint((int)(ff.a + ff.c), (int)(ff.b + ff.d));
                            d6.addPoint((int)ff.a, (int)(ff.b + ff.d));
                            dg.e(d6);
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if (dg.f6() == null) {
                            final double fi = dg.fi();
                            final da ff2 = dg.ff();
                            da da = new da(ff2.a, ff2.b, (int)(ff2.c * fi), (int)(ff2.d * fi));
                            if (!dg.b6()) {
                                da = new da(this.a(dg, graphics, dt, this.u).a());
                            }
                            final d4 d7 = new d4();
                            d7.addPoint((int)da.a, (int)da.b);
                            d7.addPoint((int)(da.a + da.c), (int)da.b);
                            d7.addPoint((int)(da.a + da.c), (int)(da.b + da.d));
                            d7.addPoint((int)da.a, (int)(da.b + da.d));
                            dg.e(d7);
                            break;
                        }
                        break;
                    }
                    case 3:
                    case 6: {
                        if (dg.f6() == null) {
                            dg.e(dg.fm());
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final dg dg, double n, double n2, final Graphics graphics, final dt dt, final dx dx, final int n3, final int n4) {
        try {
            if (dg != null) {
                if (dg.d5() == 9) {
                    n = (n2 = (n + n2) / 2);
                }
                else if (ji.annotate.dy.av) {
                    if (dg.d4() == 4 || dg.d4() == 5) {
                        if (n != 1.0 && n2 != 1.0) {
                            n = (n2 = (n + n2) / 2);
                        }
                    }
                    else {
                        n = (n2 = (n + n2) / 2);
                    }
                }
                dg.ac(true);
                switch (dg.d4()) {
                    case 4:
                    case 5: {
                        this.a(dg, graphics, dt);
                        final d4 f6 = dg.f6();
                        final d5 fn = dg.fn();
                        final d5 fo = dg.fo();
                        final int[] c = f6.c();
                        final int[] d = f6.d();
                        final int n5 = (c[0] + c[1]) / 2;
                        final int n6 = (d[0] + d[1]) / 2;
                        final long abs = Math.abs((long)((n5 - c[0]) * n));
                        if (fn.a <= n5) {
                            fn.a = n5 - abs;
                        }
                        else {
                            fn.a = n5 + abs;
                        }
                        final long abs2 = Math.abs((long)((n6 - d[0]) * n2));
                        if (fn.b <= n6) {
                            fn.b = n6 - abs2;
                        }
                        else {
                            fn.b = n6 + abs2;
                        }
                        final long abs3 = Math.abs((long)((c[1] - n5) * n));
                        if (fo.a >= n5) {
                            fo.a = n5 + abs3;
                        }
                        else {
                            fo.a = n5 - abs3;
                        }
                        final long abs4 = Math.abs((long)((d[1] - n6) * n2));
                        if (fo.b >= n6) {
                            fo.b = n6 + abs4;
                        }
                        else {
                            fo.b = n6 - abs4;
                        }
                        if (Math.abs(fo.a - fn.a) < ji.util.e.bb && Math.abs(fo.b - fn.b) < ji.util.e.bb) {
                            break;
                        }
                        boolean b = true;
                        if (fn.a <= 0) {
                            b = false;
                        }
                        if (fn.b <= 0) {
                            b = false;
                        }
                        if (fo.a <= 0) {
                            b = false;
                        }
                        if (fo.b <= 0) {
                            b = false;
                        }
                        if (fn.a >= dx.q) {
                            b = false;
                        }
                        if (fn.b >= dx.r) {
                            b = false;
                        }
                        if (fo.a >= dx.q) {
                            b = false;
                        }
                        if (fo.b >= dx.r) {
                            b = false;
                        }
                        if (b) {
                            dg.c(fn);
                            dg.d(fo);
                            dg.j(n);
                            dg.k(n2);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.a(dg, graphics, dt);
                        final d4 f7 = dg.f6();
                        final d5 fn2 = dg.fn();
                        dg.fk();
                        dg.fl();
                        final int[] c2 = f7.c();
                        final int[] d2 = f7.d();
                        final long n7 = (long)(c2[1] * n);
                        final long n8 = (long)(d2[1] * n2);
                        if (n7 < ji.util.e.bb || n8 < ji.util.e.bb) {
                            break;
                        }
                        boolean b2 = true;
                        if (fn2.a - n7 <= 0) {
                            b2 = false;
                        }
                        if (fn2.b - n8 <= 0) {
                            b2 = false;
                        }
                        if (fn2.a + n7 >= dx.q) {
                            b2 = false;
                        }
                        if (fn2.b + n8 >= dx.r) {
                            b2 = false;
                        }
                        if (b2) {
                            dg.b(n7, n8);
                            dg.j(n);
                            dg.k(n2);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        this.a(dg, graphics, dt);
                        final d4 f8 = dg.f6();
                        final da ff = dg.ff();
                        final int[] c3 = f8.c();
                        final int[] d3 = f8.d();
                        final da da = new da(c3[0], d3[0], c3[1] - c3[0], d3[3] - d3[0]);
                        final da da2 = new da(ff);
                        ff.c = (long)(da.c * n);
                        ff.d = (long)(da.d * n2);
                        final da da3 = ff;
                        da3.a -= (ff.c - da2.c) / 2;
                        final da da4 = ff;
                        da4.b -= (ff.d - da2.d) / 2;
                        if (ff.c < ji.util.e.bb || ff.d < ji.util.e.bb) {
                            break;
                        }
                        boolean b3 = true;
                        if (ff.a <= 0) {
                            b3 = false;
                        }
                        if (ff.b <= 0) {
                            b3 = false;
                        }
                        if (ff.a + ff.c >= dx.q) {
                            b3 = false;
                        }
                        if (ff.b + ff.d >= dx.r) {
                            b3 = false;
                        }
                        if (b3) {
                            if (dg.d5() == 9) {
                                dg.b(ff.c / dg.fj());
                                dg.h(ff);
                            }
                            else {
                                dg.h(ff);
                            }
                            dg.j(n);
                            dg.k(n2);
                            break;
                        }
                        break;
                    }
                    case 9: {
                        this.a(dg, graphics, dt);
                        if (!dg.b6()) {
                            dg.cy();
                        }
                        final da ff2 = dg.ff();
                        if (ff2.c >= ji.util.e.bb && ff2.d >= ji.util.e.bb) {
                            final Dimension ah = dg.ah();
                            final double fi = dg.fi();
                            long n9 = (long)(ff2.c + n3 * fi);
                            long n10 = (long)(ff2.d + n4 * fi);
                            if (dg.b7()) {
                                if (n3 > n4) {
                                    n10 = (int)(n9 * ah.height / ah.width);
                                }
                                else {
                                    n9 = (int)(n10 * ah.width / ah.height);
                                }
                            }
                            dg.h(new da(ff2.a, ff2.b, n9, n10));
                            n = n9 / ah.width / fi;
                            n2 = n10 / ah.height / fi;
                            dg.b(n);
                            dg.j(n);
                            dg.k(n2);
                            break;
                        }
                        break;
                    }
                    case 3:
                    case 6: {
                        this.a(dg, graphics, dt);
                        final d4 f9 = dg.f6();
                        final d4 fm = dg.fm();
                        final int b4 = fm.b();
                        final int[] c4 = fm.c();
                        final int[] d4 = fm.d();
                        final int[] c5 = f9.c();
                        final int[] d5 = f9.d();
                        final Rectangle bounds = f9.getBounds();
                        final int n11 = bounds.x + bounds.width / 2;
                        final int n12 = bounds.y + bounds.height / 2;
                        for (int i = 0; i < b4; ++i) {
                            c4[i] = n11 + (int)((c5[i] - n11) * n);
                            d4[i] = n12 + (int)((d5[i] - n12) * n2);
                        }
                        final d4 d6 = new d4(c4, d4, b4);
                        final Rectangle bounds2 = d6.getBounds();
                        if (bounds2.width < ji.util.e.bb || bounds2.height < ji.util.e.bb) {
                            break;
                        }
                        boolean b5 = true;
                        if (bounds2.x <= 0) {
                            b5 = false;
                        }
                        if (bounds2.y <= 0) {
                            b5 = false;
                        }
                        if (bounds2.x + bounds2.width >= dx.q) {
                            b5 = false;
                        }
                        if (bounds2.y + bounds2.height >= dx.r) {
                            b5 = false;
                        }
                        if (b5) {
                            dg.a((Polygon)d6);
                            dg.j(n);
                            dg.k(n2);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
        this.b(dg);
    }
    
    public final void f() {
        this.bp = true;
    }
    
    public final void a(int n, int n2, int n3, final dg dg, final int n4, final int n5, final Graphics graphics, final dt dt, final dx dx) {
        if (this.p()) {
            return;
        }
        if (dg != null) {
            try {
                this.bp = true;
                int b = 0;
                if (n == 999) {
                    switch (n4) {
                        case 90: {
                            n3 = -n3;
                            break;
                        }
                        case 180: {
                            n2 = -n2;
                            n3 = -n3;
                            break;
                        }
                        case 270: {
                            n2 = -n2;
                            break;
                        }
                    }
                    if ((n5 & 0x1) > 0) {
                        n2 = -n2;
                    }
                    if ((n5 & 0x2) > 0) {
                        n3 = -n3;
                    }
                }
                switch (dg.d4()) {
                    case 7: {
                        if (this.b != null) {
                            this.b.a(dg, n, n2, n3, n4);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        final da ff = dg.ff();
                        switch (n) {
                            case 999: {
                                final boolean b2 = true;
                                this.a(dg, graphics, dt);
                                final Rectangle bounds = dg.f6().getBounds();
                                final Rectangle a = dg.ff().a();
                                final double n6 = Math.abs(a.width / 2 + n2) / (bounds.width / 2);
                                final double n7 = Math.abs(a.height / 2 + n3) / (bounds.height / 2);
                                if (b2) {
                                    this.a(dg, n6, n7, graphics, dt, dx, n2, n3);
                                    break;
                                }
                                break;
                            }
                            case 0: {
                                final da da = ff;
                                da.a += n2;
                                final da da2 = ff;
                                da2.c -= n2;
                                final da da3 = ff;
                                da3.b += n3;
                                final da da4 = ff;
                                da4.d -= n3;
                                dg.ac(true);
                                break;
                            }
                            case 1: {
                                final da da5 = ff;
                                da5.c += n2;
                                final da da6 = ff;
                                da6.b += n3;
                                final da da7 = ff;
                                da7.d -= n3;
                                dg.ac(true);
                                break;
                            }
                            case 2: {
                                final da da8 = ff;
                                da8.c += n2;
                                final da da9 = ff;
                                da9.d += n3;
                                dg.ac(true);
                                break;
                            }
                            case 3: {
                                final da da10 = ff;
                                da10.a += n2;
                                final da da11 = ff;
                                da11.c -= n2;
                                final da da12 = ff;
                                da12.d += n3;
                                dg.ac(true);
                                break;
                            }
                        }
                        if (ff.c >= ji.util.e.bb && ff.d >= ji.util.e.bb && n != 999) {
                            dg.h(ff);
                            dg.e((d4)null);
                            break;
                        }
                        break;
                    }
                    case 9: {
                        if (this.b != null && dg.as()) {
                            this.b.a(dg, n, n2, n3, n4);
                            break;
                        }
                        final da ff2 = dg.ff();
                        final Dimension ah = dg.ah();
                        final int m = dg.m(dg.cj() + dg.b3());
                        final int b3 = dg.b3();
                        final long c = ff2.c;
                        final long d = ff2.d;
                        final int n8 = ji.util.e.bb * 2;
                        final int n9 = ji.util.e.bb * 2;
                        switch (n) {
                            case 999: {
                                if (m == 90 || m == 270) {
                                    final da da13 = ff2;
                                    da13.c += n3;
                                    final da da14 = ff2;
                                    da14.d += n2;
                                }
                                else {
                                    final da da15 = ff2;
                                    da15.c += n2;
                                    final da da16 = ff2;
                                    da16.d += n3;
                                }
                                if (c != ff2.c) {
                                    ff2.d = (int)(ff2.c * d / c);
                                }
                                else {
                                    ff2.c = (int)(ff2.d * c / d);
                                }
                                if (ff2.c >= n8 && ff2.d >= n8) {
                                    dg.ac(true);
                                    this.a(dg, 1.0, 1.0, graphics, dt, dx, 0, 0);
                                    break;
                                }
                                break;
                            }
                            case 0: {
                                if (b3 == 180) {
                                    if (m == 90 || m == 270) {
                                        final da da17 = ff2;
                                        da17.a += n2;
                                        final da da18 = ff2;
                                        da18.d += n2;
                                        final da da19 = ff2;
                                        da19.c += n3;
                                        final da da20 = ff2;
                                        da20.b += n3;
                                    }
                                    else {
                                        final da da21 = ff2;
                                        da21.b += n3;
                                        final da da22 = ff2;
                                        da22.d += n3;
                                        final da da23 = ff2;
                                        da23.c += n2;
                                        final da da24 = ff2;
                                        da24.a += n2;
                                    }
                                }
                                else if (b3 == 90) {
                                    if (m == 90 || m == 270) {
                                        final da da25 = ff2;
                                        da25.b += n3;
                                        final da da26 = ff2;
                                        da26.c -= n3;
                                        final da da27 = ff2;
                                        da27.d += n2;
                                        final da da28 = ff2;
                                        da28.a += n2;
                                    }
                                    else {
                                        final da da29 = ff2;
                                        da29.b += n3;
                                        final da da30 = ff2;
                                        da30.d -= n3;
                                        final da da31 = ff2;
                                        da31.a += n2;
                                        final da da32 = ff2;
                                        da32.c += n2;
                                    }
                                }
                                else if (b3 == 270) {
                                    if (m == 90 || m == 270) {
                                        final da da33 = ff2;
                                        da33.c += n3;
                                        final da da34 = ff2;
                                        da34.b += n3;
                                        final da da35 = ff2;
                                        da35.a += n2;
                                        final da da36 = ff2;
                                        da36.d -= n2;
                                    }
                                    else {
                                        final da da37 = ff2;
                                        da37.c -= n2;
                                        final da da38 = ff2;
                                        da38.d += n3;
                                        final da da39 = ff2;
                                        da39.a += n2;
                                        final da da40 = ff2;
                                        da40.b += n3;
                                    }
                                }
                                else {
                                    final da da41 = ff2;
                                    da41.a += n2;
                                    final da da42 = ff2;
                                    da42.b += n3;
                                    if (m == 90 || m == 270) {
                                        final da da43 = ff2;
                                        da43.c -= n3;
                                        final da da44 = ff2;
                                        da44.d -= n2;
                                    }
                                    else {
                                        final da da45 = ff2;
                                        da45.c -= n2;
                                        final da da46 = ff2;
                                        da46.d -= n3;
                                    }
                                }
                                if (dg.b7()) {
                                    if (c != ff2.c) {
                                        ff2.d = (int)(ff2.c * ah.height / ah.width);
                                    }
                                    else {
                                        ff2.c = (int)(ff2.d * ah.width / ah.height);
                                    }
                                }
                                if (ff2.c < n8 || ff2.d < n8) {
                                    break;
                                }
                                dg.ac(true);
                                if (!ji.util.i.c(130)) {
                                    this.a(dg, 1.0, 1.0, graphics, dt, dx, 0, 0);
                                    break;
                                }
                                break;
                            }
                            case 1: {
                                if (b3 == 180) {
                                    if (m == 90 || m == 270) {
                                        final da da47 = ff2;
                                        da47.b += n3;
                                        final da da48 = ff2;
                                        da48.c += n3;
                                        final da da49 = ff2;
                                        da49.d -= n2;
                                    }
                                    else {
                                        final da da50 = ff2;
                                        da50.b += n3;
                                        final da da51 = ff2;
                                        da51.d += n3;
                                        final da da52 = ff2;
                                        da52.c -= n2;
                                    }
                                }
                                else if (b3 == 90) {
                                    if (m == 90 || m == 270) {
                                        final da da53 = ff2;
                                        da53.c += n3;
                                        final da da54 = ff2;
                                        da54.d += n2;
                                        final da da55 = ff2;
                                        da55.a += n2;
                                    }
                                    else {
                                        final da da56 = ff2;
                                        da56.a += n2;
                                        final da da57 = ff2;
                                        da57.d += n3;
                                        final da da58 = ff2;
                                        da58.c += n2;
                                    }
                                }
                                else if (b3 == 270) {
                                    if (m == 90 || m == 270) {
                                        final da da59 = ff2;
                                        da59.c -= n3;
                                        final da da60 = ff2;
                                        da60.a += n2;
                                        final da da61 = ff2;
                                        da61.d -= n2;
                                    }
                                    else {
                                        final da da62 = ff2;
                                        da62.a += n2;
                                        final da da63 = ff2;
                                        da63.c -= n2;
                                        final da da64 = ff2;
                                        da64.d -= n3;
                                    }
                                }
                                else if (m == 90 || m == 270) {
                                    final da da65 = ff2;
                                    da65.c -= n3;
                                    final da da66 = ff2;
                                    da66.b += n3;
                                    final da da67 = ff2;
                                    da67.d += n2;
                                }
                                else {
                                    final da da68 = ff2;
                                    da68.c += n2;
                                    final da da69 = ff2;
                                    da69.b += n3;
                                    final da da70 = ff2;
                                    da70.d -= n3;
                                }
                                if (dg.b7()) {
                                    if (c != ff2.c) {
                                        ff2.d = (int)(ff2.c * ah.height / ah.width);
                                    }
                                    else {
                                        ff2.c = (int)(ff2.d * ah.width / ah.height);
                                    }
                                }
                                if (ff2.c < n8 || ff2.d < n8) {
                                    break;
                                }
                                dg.ac(true);
                                if (!ji.util.i.c(130)) {
                                    this.a(dg, 1.0, 1.0, graphics, dt, dx, 0, 0);
                                    break;
                                }
                                break;
                            }
                            case 2: {
                                if (b3 == 180) {
                                    if (m == 90 || m == 270) {
                                        final da da71 = ff2;
                                        da71.d -= n2;
                                        final da da72 = ff2;
                                        da72.c -= n3;
                                    }
                                    else {
                                        final da da73 = ff2;
                                        da73.c -= n2;
                                        final da da74 = ff2;
                                        da74.d -= n3;
                                    }
                                }
                                else if (b3 == 90) {
                                    if (m == 90 || m == 270) {
                                        final da da75 = ff2;
                                        da75.c += n3;
                                        final da da76 = ff2;
                                        da76.d -= n2;
                                    }
                                    else {
                                        final da da77 = ff2;
                                        da77.c -= n2;
                                        final da da78 = ff2;
                                        da78.d += n3;
                                    }
                                }
                                else if (b3 == 270) {
                                    if (m == 90 || m == 270) {
                                        final da da79 = ff2;
                                        da79.c -= n3;
                                        final da da80 = ff2;
                                        da80.d += n2;
                                    }
                                    else {
                                        final da da81 = ff2;
                                        da81.c += n2;
                                        final da da82 = ff2;
                                        da82.d -= n3;
                                    }
                                }
                                else if (m == 90 || m == 270) {
                                    final da da83 = ff2;
                                    da83.c += n3;
                                    final da da84 = ff2;
                                    da84.d += n2;
                                }
                                else {
                                    final da da85 = ff2;
                                    da85.c += n2;
                                    final da da86 = ff2;
                                    da86.d += n3;
                                }
                                if (dg.b7()) {
                                    if (c != ff2.c) {
                                        ff2.d = (int)(ff2.c * ah.height / ah.width);
                                    }
                                    else {
                                        ff2.c = (int)(ff2.d * ah.width / ah.height);
                                    }
                                }
                                if (ff2.c < n8 || ff2.d < n8) {
                                    break;
                                }
                                dg.ac(true);
                                if (!ji.util.i.c(130)) {
                                    this.a(dg, 1.0, 1.0, graphics, dt, dx, 0, 0);
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                if (b3 == 180) {
                                    if (m == 90 || m == 270) {
                                        final da da87 = ff2;
                                        da87.a += n2;
                                        final da da88 = ff2;
                                        da88.d += n2;
                                        final da da89 = ff2;
                                        da89.c -= n3;
                                    }
                                    else {
                                        final da da90 = ff2;
                                        da90.d -= n3;
                                        final da da91 = ff2;
                                        da91.c += n2;
                                        final da da92 = ff2;
                                        da92.a += n2;
                                    }
                                }
                                else if (b3 == 90) {
                                    if (m == 90 || m == 270) {
                                        final da da93 = ff2;
                                        da93.d -= n2;
                                        final da da94 = ff2;
                                        da94.c -= n3;
                                        final da da95 = ff2;
                                        da95.b += n3;
                                    }
                                    else {
                                        final da da96 = ff2;
                                        da96.c -= n2;
                                        final da da97 = ff2;
                                        da97.d -= n3;
                                        final da da98 = ff2;
                                        da98.b += n3;
                                    }
                                }
                                else if (b3 == 270) {
                                    if (m == 90 || m == 270) {
                                        final da da99 = ff2;
                                        da99.d += n2;
                                        final da da100 = ff2;
                                        da100.c += n3;
                                        final da da101 = ff2;
                                        da101.b += n3;
                                    }
                                    else {
                                        final da da102 = ff2;
                                        da102.c += n2;
                                        final da da103 = ff2;
                                        da103.d += n3;
                                        final da da104 = ff2;
                                        da104.b += n3;
                                    }
                                }
                                else if (m == 90 || m == 270) {
                                    final da da105 = ff2;
                                    da105.a += n2;
                                    final da da106 = ff2;
                                    da106.c += n3;
                                    final da da107 = ff2;
                                    da107.d -= n2;
                                }
                                else {
                                    final da da108 = ff2;
                                    da108.a += n2;
                                    final da da109 = ff2;
                                    da109.c -= n2;
                                    final da da110 = ff2;
                                    da110.d += n3;
                                }
                                if (dg.b7()) {
                                    if (c != ff2.c) {
                                        ff2.d = (int)(ff2.c * ah.height / ah.width);
                                    }
                                    else {
                                        ff2.c = (int)(ff2.d * ah.width / ah.height);
                                    }
                                }
                                if (ff2.c < n8 || ff2.d < n8) {
                                    break;
                                }
                                dg.ac(true);
                                if (!ji.util.i.c(130)) {
                                    this.a(dg, 1.0, 1.0, graphics, dt, dx, 0, 0);
                                    break;
                                }
                                break;
                            }
                        }
                        if (ff2.c >= n8 && ff2.d >= n8 && dg.a5()) {
                            dg.h(ff2);
                            dg.e((d4)null);
                            break;
                        }
                        break;
                    }
                    case 2: {
                        final d5 fn = dg.fn();
                        long fk = dg.fk();
                        long fl = dg.fl();
                        if (n4 == 0 || n4 == 180) {
                            int n10 = n2;
                            int n11 = n3;
                            if (n4 == 180) {
                                n10 = -n10;
                                n11 = -n11;
                            }
                            if ((n5 & 0x1) > 0) {
                                n10 = -n10;
                            }
                            if ((n5 & 0x2) > 0) {
                                n11 = -n11;
                            }
                            switch (n) {
                                case 999: {
                                    fk += n2;
                                    fl += n3;
                                    this.a(dg, graphics, dt);
                                    final d4 f6 = dg.f6();
                                    this.a(dg, fk / f6.c()[1], fl / f6.d()[1], graphics, dt, dx, n2, n3);
                                    break;
                                }
                                case 0: {
                                    fl += -n11;
                                    dg.ac(true);
                                    break;
                                }
                                case 1: {
                                    fk += n10;
                                    dg.ac(true);
                                    break;
                                }
                                case 2: {
                                    fk += -n10;
                                    dg.ac(true);
                                    break;
                                }
                                case 3: {
                                    fl += n11;
                                    dg.ac(true);
                                    break;
                                }
                            }
                            if (fk < ji.util.e.bb || fl < ji.util.e.bb || n == 999) {
                                break;
                            }
                            boolean b4 = true;
                            if (fn.a - fk <= 0) {
                                b4 = false;
                            }
                            if (fn.b - fl <= 0) {
                                b4 = false;
                            }
                            if (fn.a + fk >= dx.q) {
                                b4 = false;
                            }
                            if (fn.b + fl >= dx.r) {
                                b4 = false;
                            }
                            if (b4) {
                                dg.b(fk, fl);
                                dg.c(fn);
                                dg.e((d4)null);
                                break;
                            }
                            break;
                        }
                        else {
                            int n12 = n2;
                            int n13 = n3;
                            if (n4 == 270) {
                                n12 = -n12;
                                n13 = -n13;
                            }
                            switch (n) {
                                case 999: {
                                    fk += n2;
                                    fl += n3;
                                    this.a(dg, graphics, dt);
                                    final d4 f7 = dg.f6();
                                    this.a(dg, fk / f7.c()[1], fl / f7.d()[1], graphics, dt, dx, n2, n3);
                                    break;
                                }
                                case 0: {
                                    fk += -n12;
                                    dg.ac(true);
                                    break;
                                }
                                case 1: {
                                    fl += -n13;
                                    dg.ac(true);
                                    break;
                                }
                                case 2: {
                                    fl += n13;
                                    dg.ac(true);
                                    break;
                                }
                                case 3: {
                                    fk += n12;
                                    dg.ac(true);
                                    break;
                                }
                            }
                            if (fk >= ji.util.e.bb && fl >= ji.util.e.bb && n != 999) {
                                dg.b(fk, fl);
                                dg.c(fn);
                                dg.e((d4)null);
                                break;
                            }
                            break;
                        }
                        break;
                    }
                    case 3:
                    case 6: {
                        if (n == 999) {
                            final d4 fm = dg.fm();
                            this.a(dg, graphics, dt);
                            final Rectangle bounds2 = dg.f6().getBounds();
                            final Rectangle bounds3 = fm.getBounds();
                            this.a(dg, Math.abs(bounds3.width / 2 + n2) / (bounds2.width / 2), Math.abs(bounds3.height / 2 + n3) / (bounds2.height / 2), graphics, dt, dx, n2, n3);
                        }
                        else {
                            final d4 fm2 = dg.fm();
                            b = fm2.b();
                            final int[] c2 = fm2.c();
                            final int[] d2 = fm2.d();
                            dg.e((d4)null);
                            if (n >= b) {
                                n = 0;
                            }
                            if (n < 0) {
                                n = b - 1;
                            }
                            final int[] array = c2;
                            final int n14 = n;
                            array[n14] += n2;
                            final int[] array2 = d2;
                            final int n15 = n;
                            array2[n15] += n3;
                            dg.ac(true);
                            dg.a((Polygon)new d4(c2, d2, b));
                            dg.e((d4)null);
                        }
                        if (dg.e2() && b == 3) {
                            dg.h(dg.e4());
                            break;
                        }
                        break;
                    }
                    case 4:
                    case 5: {
                        final d5 fn2 = dg.fn();
                        final d5 fo = dg.fo();
                        switch (n) {
                            case 2:
                            case 999: {
                                this.a(dg, graphics, dt);
                                final d4 f8 = dg.f6();
                                final int[] c3 = f8.c();
                                final int[] d3 = f8.d();
                                double n16 = 1.0;
                                double n17 = 1.0;
                                try {
                                    if (c3[1] != c3[0]) {
                                        n16 = (n2 + Math.abs(fo.a - fn2.a) / 2.0) / (Math.abs(c3[1] - c3[0]) / 2.0);
                                    }
                                    if (d3[1] != d3[0]) {
                                        n17 = (n3 + Math.abs(fo.b - fn2.b) / 2.0) / (Math.abs(d3[1] - d3[0]) / 2.0);
                                    }
                                }
                                catch (Exception ex) {}
                                this.a(dg, n16, n17, graphics, dt, dx, n2, n3);
                                break;
                            }
                            case 0: {
                                dg.e((d4)null);
                                final d5 d4 = fn2;
                                d4.a += n2;
                                final d5 d5 = fn2;
                                d5.b += n3;
                                dg.ac(true);
                                break;
                            }
                            case 1: {
                                dg.e((d4)null);
                                final d5 d6 = fo;
                                d6.a += n2;
                                final d5 d7 = fo;
                                d7.b += n3;
                                dg.ac(true);
                                break;
                            }
                        }
                        if ((Math.abs(fo.a - fn2.a) >= ji.util.e.bb || Math.abs(fo.b - fn2.b) >= ji.util.e.bb) && n != 999 && n != 2) {
                            dg.c(fn2);
                            dg.d(fo);
                            dg.e((d4)null);
                        }
                        if (dg.e1()) {
                            dg.i(dg.e3() / dx.d());
                            break;
                        }
                        break;
                    }
                }
                dg.f1();
            }
            catch (Exception ex2) {}
            this.b(dg);
        }
    }
    
    public final boolean a(final Point point) {
        boolean b = false;
        if (this.ae != null && this.ae.a(point.x, point.y)) {
            b = true;
        }
        return b;
    }
    
    public final dg a(final dg dg, final dx u, final int n, final int n2, final df df, final double f, final da da, final da k, final Graphics graphics, final int g, final int h, final boolean b, final boolean b2, int max, final boolean b3, final boolean b4, final boolean b5, final boolean ax, final ad ad) {
        if (df != null) {
            try {
                if (this.ae != null && this.ae.a(n, n2) && dg != null) {
                    return dg;
                }
                int eh;
                int ei;
                if (this.ef == null) {
                    this.ef = new c("selectItems");
                    df.i();
                    eh = this.af();
                    ei = df.d();
                    try {
                        final ax g2 = df.g();
                        while (g2.a()) {
                            final dg b6 = df.b(g2.b());
                            if (b6.i(max) == max && b6.bm()) {
                                if (b3) {
                                    if (b6.d5() != 8) {
                                        continue;
                                    }
                                    if (this.cl) {
                                        if (!b6.c(this.e, this.d) && !this.em) {
                                            this.ef.a("".concat(String.valueOf(String.valueOf(b6.ce()))), b6);
                                        }
                                    }
                                    else {
                                        this.ef.a("".concat(String.valueOf(String.valueOf(b6.ce()))), b6);
                                    }
                                    eh = Math.max(eh, b6.ce());
                                    ei = Math.min(ei, b6.ce());
                                }
                                else {
                                    if (this.cl) {
                                        if (!b6.c(this.e, this.d) && !this.em) {
                                            this.ef.a("".concat(String.valueOf(String.valueOf(b6.ce()))), b6);
                                        }
                                    }
                                    else {
                                        this.ef.a("".concat(String.valueOf(String.valueOf(b6.ce()))), b6);
                                    }
                                    eh = Math.max(eh, b6.ce());
                                    ei = Math.min(ei, b6.ce());
                                }
                            }
                        }
                    }
                    catch (Exception ex) {
                        if (ji.util.d.cy()) {
                            ex.printStackTrace();
                        }
                    }
                    this.eh = eh;
                    this.ei = ei;
                }
                else {
                    eh = this.eh;
                    ei = this.ei;
                }
                dg dg2 = null;
                final int n3 = 6;
                final int n4 = n3 / 2;
                if (this.ef != null) {
                    max = Math.max(max, 1);
                    this.u = u;
                    this.f = f;
                    this.ax = ax;
                    this.j = new da(da);
                    this.k = k;
                    this.g = g;
                    this.h = h;
                    int n5 = this.c(ei, eh);
                    ++eh;
                    --ei;
                    while (this.a(n5, eh, ei)) {
                        boolean b7 = true;
                        dg dg3 = null;
                        try {
                            dg3 = (dg)this.ef.d("".concat(String.valueOf(String.valueOf(n5))));
                        }
                        catch (Exception ex4) {}
                        n5 = this.d(n5);
                        if (dg3 != null && dg3.i(max) == max) {
                            if (!b && dg3.d2()) {
                                b7 = false;
                            }
                            if (!b2 && !dg3.d2()) {
                                b7 = false;
                            }
                            if (!b7) {
                                continue;
                            }
                            final da a = this.a(dg3, graphics, dg3.dy(), true, u);
                            if (a == null) {
                                continue;
                            }
                            if (a.c < n3 || dg3.dy()) {
                                final da da2 = a;
                                da2.a -= n4;
                                final da da3 = a;
                                da3.c += n3;
                            }
                            else {
                                final da da4 = a;
                                --da4.a;
                                final da da5 = a;
                                da5.c += 2;
                            }
                            if (a.d < n3 || dg3.dy()) {
                                final da da6 = a;
                                da6.b -= n4;
                                final da da7 = a;
                                da7.d += n3;
                            }
                            else {
                                final da da8 = a;
                                --da8.b;
                                final da da9 = a;
                                da9.d += 2;
                            }
                            if (dg3.dy()) {
                                final da da10 = a;
                                da10.c += this.d1;
                                final da da11 = a;
                                da11.d += this.d1;
                            }
                            final da da12 = a;
                            --da12.a;
                            final da da13 = a;
                            --da13.b;
                            final da da14 = a;
                            ++da14.c;
                            final da da15 = a;
                            ++da15.d;
                            if (g == 180) {
                                final da da16 = a;
                                da16.c += 4;
                                final da da17 = a;
                                da17.d += 4;
                            }
                            if (!a.a(n, n2)) {
                                continue;
                            }
                            if (b4) {
                                this.aa.a(new d7(this.aa, 51, dg3.d(ad)));
                                boolean b8 = false;
                                Label_1073: {
                                    try {
                                        final ax g3 = df.g();
                                        final long d0 = dg3.d0();
                                        final da fv = dg3.fv();
                                        final int ce = dg3.ce();
                                        Block_59: {
                                            while (g3.a()) {
                                                final dg b9 = df.b(g3.b());
                                                if (b9.d0() != d0 && this.a(b9, ce)) {
                                                    final da fv2 = b9.fv();
                                                    if (fv2 == null) {
                                                        continue;
                                                    }
                                                    final da b10 = fv2.b(fv);
                                                    if (b10 != null && b10.c >= 0 && b10.d >= 0) {
                                                        break Block_59;
                                                    }
                                                    continue;
                                                }
                                            }
                                            break Label_1073;
                                        }
                                        b8 = true;
                                    }
                                    catch (Exception ex2) {
                                        ex2.printStackTrace();
                                        b8 = true;
                                    }
                                }
                                if (df.d(dg3, false) && !ji.util.d.bf() && b8) {
                                    this.aa.h(dg3);
                                }
                            }
                            final dg e = df.e(dg3);
                            if (b5) {
                                if (dg2 == null) {
                                    dg2 = e;
                                }
                                if (!e.dd()) {
                                    continue;
                                }
                                if (e.b(this.e, this.d)) {
                                    return e;
                                }
                                return null;
                            }
                            else {
                                final dg e2 = df.e(dg3);
                                if (e2 == null) {
                                    return null;
                                }
                                if (e2.b(this.e, this.d)) {
                                    return e2;
                                }
                                return null;
                            }
                        }
                    }
                }
                if (dg2 != null) {
                    return dg2;
                }
                return null;
            }
            catch (Exception ex3) {
                this.g();
                if (ji.util.d.cy()) {
                    ex3.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }
    
    private int c(final int n, final int n2) {
        if (!ji.util.i.c(257)) {
            return n2;
        }
        return n;
    }
    
    private int d(final int n) {
        if (!ji.util.i.c(257)) {
            return n - 1;
        }
        return n + 1;
    }
    
    private int af() {
        return (ji.util.i.c(257) && false) ? 1 : 0;
    }
    
    private boolean a(final int n, final int n2, final int n3) {
        if (!ji.util.i.c(257)) {
            return n > n3;
        }
        return n < n2;
    }
    
    private boolean a(final dg dg, final int n) {
        if (!ji.util.i.c(257)) {
            return n <= dg.ce();
        }
        return dg.ce() <= n;
    }
    
    public final void g() {
        try {
            if (this.ef != null) {
                this.ef.c();
                this.ef = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final da a(final dg dg, final Graphics graphics, final dt dt, final dx dx) {
        return this.a(dg, graphics, dt, false, true, dx);
    }
    
    private final da a(final dg dg, final Graphics graphics, final dt dt, final boolean b, final boolean b2, final dx dx) {
        return this.a(dg, graphics, b, b2, dx);
    }
    
    private final da a(final dg dg, final Graphics graphics, final boolean b, final boolean b2, final dx dx) {
        da da = null;
        if (dg.d5() == 9 || dg.d5() == 7 || dg.d5() == 5) {
            if (dg.d5() == 5) {
                if (b2) {
                    da = dg.fr();
                    if (da != null && (da.c <= 0 || da.d <= 0)) {
                        da = null;
                    }
                }
            }
            else {
                da = dg.fr();
                if (da != null && (da.c <= 0 || da.d <= 0)) {
                    da = null;
                }
            }
        }
        if (da == null) {
            da = this.a(this.a(dg.fv(), dg, dx), graphics, null);
            da.c = Math.max(da.c, 1L);
            da.d = Math.max(da.d, 1L);
        }
        if (b && dg.d5() != 9 && dg.d5() != 7) {
            final da fp = dg.fp();
            if (fp != null && da != null) {
                final int min = Math.min((int)fp.a, (int)da.a);
                final int min2 = Math.min((int)fp.b, (int)da.b);
                final int max = Math.max((int)(fp.a + fp.c), (int)(da.a + da.c));
                final int max2 = Math.max((int)(fp.b + fp.d), (int)(da.b + da.d));
                da.a = min;
                da.b = min2;
                da.c = max - min;
                da.d = max2 - min2;
            }
        }
        return da;
    }
    
    private final d5 a(final d5 d5, final dg dg, final dx dx) {
        return this.a(d5.a, d5.b, dg, dx);
    }
    
    public final void a(final da da) {
        this.cd = this.ak;
        this.aq = this.ao;
        this.ar = this.ap;
        this.ce = new da(da);
    }
    
    public final void h() {
        this.ak = this.cd;
        this.ao = this.aq;
        this.ap = this.ar;
        this.cd = 0.0;
        this.aq = 0;
        this.ar = 0;
        this.ce = null;
    }
    
    private final d5 a(final long n, final long n2, final dg dg, final dx dx) {
        long n3 = n;
        long n4 = n2;
        if (this.a4) {
            Dimension dk = null;
            if (dg != null) {
                dk = dg.dk();
            }
            if (dk != null && dx != null) {
                if (dk.width != dx.m) {
                    n3 = n3 * dx.m / dk.width;
                }
                if (dk.height != dx.n) {
                    n4 = n4 * dx.n / dk.height;
                }
            }
        }
        long n7;
        long n8;
        if (this.cd != 0.0 && this.b8) {
            if ((this.al & 0x1) > 0) {
                n3 = this.aq - n3;
            }
            if ((this.al & 0x2) > 0) {
                n4 = this.ar - n4;
            }
            switch (this.am) {
                case 90: {
                    final long n5 = n3;
                    n3 = this.ar - n4;
                    n4 = n5;
                    break;
                }
                case 180: {
                    n3 = this.aq - n3;
                    n4 = this.ar - n4;
                    break;
                }
                case 270: {
                    final long n6 = n4;
                    n4 = this.aq - n3;
                    n3 = n6;
                    break;
                }
            }
            n7 = (long)(n3 * this.cd);
            n8 = (long)(n4 * this.cd);
        }
        else {
            if ((this.al & 0x1) > 0) {
                n3 = this.ao - n3;
            }
            if ((this.al & 0x2) > 0) {
                n4 = this.ap - n4;
            }
            switch (this.am) {
                case 90: {
                    final long n9 = n3;
                    n3 = this.ap - n4;
                    n4 = n9;
                    break;
                }
                case 180: {
                    n3 = this.ao - n3;
                    n4 = this.ap - n4;
                    break;
                }
                case 270: {
                    final long n10 = n4;
                    n4 = this.ao - n3;
                    n3 = n10;
                    break;
                }
            }
            n7 = (long)(n3 * this.ak);
            n8 = (long)(n4 * this.ak);
        }
        return new d5(n7, n8);
    }
    
    private final d4 a(final d4 d4, final dg dg, final dx dx) throws Exception {
        final d4 d5 = new d4();
        if (d4 != null) {
            for (int b = d4.b(), i = 0; i < b; ++i) {
                d5.a(this.a(d4.a(i), dg, dx));
            }
        }
        return d5;
    }
    
    private final da a(final da da, final dg dg, final dx dx) {
        final d5 a = this.a(da.a, da.b, dg, dx);
        final d5 a2 = this.a(da.a + da.c, da.b, dg, dx);
        final d5 a3 = this.a(da.a + da.c, da.b + da.d, dg, dx);
        final d5 a4 = this.a(da.a, da.b + da.d, dg, dx);
        final d4 d4 = new d4();
        d4.a(a);
        d4.a(a2);
        d4.a(a3);
        d4.a(a4);
        return new da(d4.getBounds());
    }
    
    private final double a(final dg dg, final dx dx, final ad ad) {
        double n = 1.0;
        if (ad.bi(40)) {
            Dimension dn = null;
            if (this.a4) {
                if (dg != null) {
                    dn = dg.dn();
                }
                int kr = (dx == null) ? 1 : dx.d();
                if (ad.gv()) {
                    int d = kr;
                    if (ad.b()) {
                        kr = ad.kr();
                        final cw dm = ad.dm();
                        if (dm != null) {
                            final dx w = dm.w();
                            if (w != null) {
                                d = w.d();
                            }
                        }
                        if (d > kr) {
                            kr = d;
                        }
                    }
                }
                if (dn != null && dx != null) {
                    final long n2 = dn.width * kr;
                    if (n2 != dx.m) {
                        n = n * dx.m / n2;
                    }
                    else {
                        final long n3 = dn.height * kr;
                        if (n3 != dx.n) {
                            n = n * dx.n / n3;
                        }
                    }
                }
            }
        }
        else if (this.a4) {
            Dimension dn2 = null;
            final int kr2 = ad.kr();
            if (dg != null) {
                dn2 = dg.dn();
            }
            if (dn2 != null && dx != null) {
                final long n4 = dn2.width * kr2;
                if (n4 != dx.m) {
                    n = n * dx.m / n4;
                }
                else {
                    final long n5 = dn2.height * kr2;
                    if (n5 != dx.n) {
                        n = n * dx.n / n5;
                    }
                }
            }
        }
        return n;
    }
    
    private final double a(final dg dg, final dx dx, final boolean b, final ad ad) {
        double n = 1.0;
        if (this.a4) {
            Dimension dk = null;
            if (dg != null) {
                dk = dg.dk();
            }
            if (dk != null && dx != null) {
                if (dk.width != dx.m) {
                    n = n * dx.m / dk.width;
                }
                else if (dk.height != dx.n) {
                    n = n * dx.n / dk.height;
                }
            }
        }
        return n;
    }
    
    protected final d5 a(final d5 d5, final Graphics graphics, final dt dt) {
        return this.a(d5.a, d5.b, graphics, dt);
    }
    
    private final int b(final dt dt) {
        return dt.i();
    }
    
    private final int c(final dt dt) {
        return dt.j();
    }
    
    public final void a(final int ew, final int ex) {
        this.ew = ew;
        this.ex = ex;
    }
    
    public final void d(final boolean ev) {
        this.ev = ev;
    }
    
    private final d5 a(final long n, final long n2, final Graphics graphics, final dt dt) {
        if (dt != null) {
            int n3 = this.b(dt);
            int n4 = this.c(dt);
            if (this.cm) {
                if (ji.util.e.av() && !this.ev) {
                    if (n3 >= 0) {
                        n3 = -1;
                    }
                    if (n4 >= 0) {
                        n4 = -1;
                    }
                    if (this.ek) {
                        n3 = this.aa.cc();
                        n4 = this.aa.cd();
                    }
                }
                else if (this.ev) {
                    if (this.ek) {
                        if (this.cn != null) {
                            n3 = (int)this.cn.a;
                            n4 = (int)this.cn.b + this.aa.cd();
                        }
                        else {
                            if (n3 >= 0) {
                                n3 = -1;
                            }
                            if (n4 >= 0) {
                                n4 = -1;
                            }
                        }
                    }
                    else {
                        if (n3 >= 0) {
                            n3 = -1;
                        }
                        if (n4 >= 0) {
                            n4 = -1;
                        }
                    }
                }
                else if (ji.util.d.av(this.ed)) {
                    n3 = (int)this.cn.a;
                    n4 = (int)this.cn.b;
                    if (this.ek && this.aa.b7()) {
                        n3 = this.aa.cc();
                        n4 = this.aa.cd();
                    }
                }
                else {
                    n3 = (int)this.cn.a;
                    n4 = (int)this.cn.b;
                    if (this.ek && this.aa.b7()) {
                        n3 = this.aa.cc();
                        n4 = this.aa.cd();
                    }
                }
            }
            else {
                if (n3 >= 0) {
                    n3 = -1;
                }
                if (n4 >= 0) {
                    n4 = -1;
                }
            }
            long n5 = n + n3;
            long n6 = n2 + n4;
            if (this.a4) {
                n5 += 2;
                n6 += 2;
            }
            return new d5(n5 + this.b0, n6 + this.b1);
        }
        int n7 = 0;
        int n8 = 0;
        if (this.a4) {
            n7 += 2;
            n8 += 2;
        }
        return new d5(n + this.as + n7, n2 + this.at + n8);
    }
    
    protected final da a(final da da, final Graphics graphics, final dt dt) {
        final da da2 = new da();
        final d5 a = this.a(da.a, da.b, graphics, dt);
        final d5 a2 = this.a(da.a + da.c, da.b, graphics, dt);
        final d5 a3 = this.a(da.a + da.c, da.b + da.d, graphics, dt);
        da2.a = a.a;
        da2.b = a.b;
        da2.c = Math.abs(a2.a - a.a);
        da2.d = Math.abs(a3.b - a2.b);
        return da2;
    }
    
    private final d4 a(final d4 d4, final Graphics graphics, final dt dt) {
        final d4 d5 = new d4();
        if (d4 != null) {
            for (int b = d4.b(), i = 0; i < b; ++i) {
                d5.a(this.a(d4.a(i), graphics, dt));
            }
        }
        return d5;
    }
    
    public final long a(final long n, final dg dg, final dx dx) {
        long n2 = (long)(n * this.ak);
        Dimension dk = null;
        if (this.a4) {
            if (dg != null) {
                dk = dg.dk();
            }
            if (dk != null && dx != null) {
                if (dk.width != dx.m) {
                    n2 = n2 * dx.m / dk.width;
                }
                else if (dk.height != dx.n) {
                    n2 = n2 * dx.n / dk.height;
                }
            }
        }
        return n2;
    }
    
    public final void a(final Component component, final af af, final dg dg, final double n, final int n2, final int n3, final int n4, final dx dx, final ad ad) throws Exception {
        if (this.a(n, n2, n3, n4, dx) || dg.a4()) {
            this.a(component, af, dg, dx, ad);
        }
    }
    
    protected final void a(final Component component, final af af, final dg dg, final dx dx, final ad ad) throws Exception {
        if (dg != null) {
            if (dg.e0() && dg.i(this.an) == this.an) {
                dg.a(component, af, dx, this.e, ad);
                switch (dg.d4()) {
                    case 7:
                    case 9: {
                        dg.a(this.a(dg.fn(), dg, dx));
                        if (dg.at()) {
                            long n;
                            long n2;
                            if (dg.cs()) {
                                n = Math.max(dg.ap(), 1L);
                                n2 = Math.max(dg.aq(), 1L);
                            }
                            else {
                                n = Math.max(dg.ap(), 20L);
                                n2 = Math.max(dg.aq(), 20L);
                            }
                            dg.g(this.a(new da(dg.fn().a, dg.fn().b, n, n2), dg, dx));
                            break;
                        }
                        break;
                    }
                    case 1: {
                        dg.f(this.a(dg.fh(), dg, dx));
                        break;
                    }
                    case 4:
                    case 5: {
                        dg.a(this.a(dg.fn(), dg, dx));
                        dg.b(this.a(dg.fo(), dg, dx));
                        break;
                    }
                    case 2: {
                        dg.a(this.a(dg.fn(), dg, dx));
                        dg.e(this.a(dg.fk(), dg, dx));
                        dg.f(this.a(dg.fl(), dg, dx));
                        break;
                    }
                    case 3:
                    case 6: {
                        dg.d(this.a(dg.fm(), dg, dx));
                        break;
                    }
                }
            }
            if (dg.d4() != 9 || dg.e9()) {
                dg.e(this.a(dg, dx, ad.b(), ad));
            }
            else if (dg.d4() == 9) {
                dg.e(this.a(dg, dx, ad));
            }
        }
    }
    
    public final void a(final long as, final long at) {
        this.as = as;
        this.at = at;
    }
    
    public final void b(final long b0, final long b2) {
        this.b0 = b0;
        this.b1 = b2;
    }
    
    public final void a(final Component component, final af af, final df df, final double n, final int n2, final int n3, final int n4, final dx dx, final ad ad) {
        try {
            if (df != null && dx != null && this.a(n, n2, n3, n4, dx) && df.d() > 0) {
                final ax g = df.g();
                while (g.a()) {
                    this.a(component, af, df.b(g.b()), dx, ad);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean a(final double ak, final int am, final int al, final int n, final dx dx) {
        try {
            if (dx != null) {
                int ap;
                int ao;
                if (dx.ac >= dx.ad) {
                    ap = Math.max(dx.r, dx.n);
                    ao = dx.m;
                }
                else {
                    ap = dx.n;
                    ao = Math.max(dx.q, dx.m);
                }
                if (this.ak != ak || this.am != am || this.al != al || this.an != n || this.ao != ao || this.ap != ap) {
                    this.ak = ak;
                    this.am = am;
                    this.al = al;
                    this.an = Math.max(n, 1);
                    this.ao = ao;
                    this.ap = ap;
                }
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    private final Color c(final dg dg, final boolean b) {
        return this.a(dg.b(dg, b), dg.a(dg, b));
    }
    
    private final Color d(final dg dg, final boolean b) {
        return this.a(dg.c(dg, b), dg.a(dg, b));
    }
    
    private final Color a(Color color, final boolean b) {
        if (this.m != 0 && !b) {
            final int n = this.m * 255 / 100;
            color = new Color(Math.min(color.getRed() + n, 255), Math.min(color.getGreen() + n, 255), Math.min(color.getBlue() + n, 255));
        }
        if (this.o) {
            return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
        }
        return color;
    }
    
    public final void a(final double en) {
        this.en = en;
    }
    
    protected final int a(final dt dt) {
        if (dt != null) {
            return dt.b();
        }
        return 24;
    }
    
    public final void i() {
        if (this.b != null) {
            this.b.c();
        }
    }
    
    private final String a(final c c, final int n, final String s) {
        String b = s;
        if (c != null && s != null) {
            try {
                if (!ji.util.d.by(b)) {
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
    
    protected final void a(final dx dx, final dt dt, final Graphics graphics, final dg dg, final Component component, final ad ad, final af af) throws Exception {
        final da a = this.a(dg.bz(), graphics, dt);
        if (this.a(dg.ff(), dg, graphics, dt, dx)) {
            if (!this.a4) {
                dg.aj(true);
            }
            if (dg.dy()) {
                dg.f0();
            }
            final d5 d5 = new d5(a.a, a.b);
            final d5 d6 = new d5(a.a + a.c, a.b);
            final d5 d7 = new d5(a.a + a.c, a.b + a.d);
            final d5 d8 = new d5(a.a, a.b + a.d);
            final d4 d9 = new d4();
            switch (dg.m(this.g + dg.b3())) {
                case 90: {
                    d9.a(d6);
                    d9.a(d7);
                    d9.a(d8);
                    d9.a(d5);
                    break;
                }
                case 180: {
                    d9.a(d7);
                    d9.a(d8);
                    d9.a(d5);
                    d9.a(d6);
                    break;
                }
                case 270: {
                    d9.a(d8);
                    d9.a(d5);
                    d9.a(d6);
                    d9.a(d7);
                    break;
                }
                default: {
                    d9.a(d5);
                    d9.a(d6);
                    d9.a(d7);
                    d9.a(d8);
                    break;
                }
            }
            final da da = new da();
            final d4 a2 = this.a(dt, graphics, d9, dg, dg.ey(), dg.ei(), component, ad, ji.annotate.b8.a(dg, dx, this.ak, this.a4), false, da, null, 0);
            if (dg.dy()) {
                final da a3 = this.a(graphics, dg, a2, component, ad, ji.annotate.b8.a(1, dg, dx, this.ak, this.a4), af);
                if (a3 != null) {
                    dg.b(a3.a, a3.b, a3.c, a3.d);
                }
            }
            dg.i(da);
        }
        else if (this.d3 && !this.a4) {
            dg.aj(false);
        }
    }
    
    private final void b(final dx dx, final dt dt, final Graphics graphics, final dg dg, final Component component, final ad ad, final af af) throws Exception {
        final d5 fn = dg.fn();
        final d5 fo = dg.fo();
        final d5 a = this.a(dg.bv(), graphics, dt);
        final d5 a2 = this.a(dg.bw(), graphics, dt);
        boolean a3 = true;
        if (fn.a == fo.a && fn.b == fo.b) {
            a3 = false;
        }
        final da fp = dg.fp();
        if (a3 && fp != null) {
            a3 = this.a(fp, dg, graphics, dt, dx);
        }
        if (a3) {
            if (!this.a4) {
                dg.aj(true);
            }
            dg.k((da)null);
            if (dg.dy()) {
                dg.f0();
            }
            d4 a4 = new d4();
            a4.a(a);
            a4.a(a2);
            final d4 a5 = a4.a();
            d4 d4 = null;
            d4 d5 = null;
            d4 a6;
            if (dg.d4() == 5 || dg.e1()) {
                a6 = new d4();
                a6.a(a);
                d4 = new d4();
                final int n = (int)(fo.a - fn.a);
                final int n2 = (int)(fo.b - fn.b);
                double n3 = 4.71238898038469 - Math.atan(n2 / n);
                if (n > 0 && n2 > 0) {
                    n3 += 3.141592653589793;
                }
                if (n > 0 && n2 < 0) {
                    n3 += 3.141592653589793;
                }
                if (n == 0 && n2 != 0) {
                    n3 += 3.141592653589793;
                }
                if (n2 == 0 && n > 0) {
                    n3 += 3.141592653589793;
                }
                if (n3 > 6.283185307179586) {
                    n3 -= 6.283185307179586;
                }
                final double n4 = 0.4363323129985824;
                int eb = dg.eb();
                if (ji.util.d.bf()) {
                    eb *= 2;
                    if (dx.ac <= 100.0) {
                        eb = eb * 100 / 125;
                    }
                }
                int n5 = eb * 2 * dg.a(dx);
                if (!this.a4) {
                    if (dx.c()) {
                        n5 += 15 * dx.d();
                    }
                    else {
                        n5 += 15;
                    }
                }
                final int n6 = (int)(n5 * Math.cos(n3 + n4));
                final int n7 = (int)(n5 * Math.sin(n3 + n4));
                final int n8 = (int)(n5 * Math.cos(n3 - n4));
                final int n9 = (int)(n5 * Math.sin(n3 - n4));
                final d5 d6 = new d5(fo.a, fo.b);
                final d5 d7 = new d5(fo.a - n7, fo.b - n6);
                final d5 d8 = new d5(fo.a - n9, fo.b - n8);
                final d4 d9 = new d4();
                if (!dg.e1()) {
                    d9.a(fn);
                    d9.addPoint((int)(fo.a - n5 / 2 * Math.cos(1.5707963267948966 - n3)), (int)(fo.b - n5 / 2 * Math.sin(1.5707963267948966 - n3)));
                }
                else {
                    d9.a(fn);
                    d9.a(fo);
                }
                a4 = this.a(this.a(d9, dg, dx), graphics, dt);
                final d5 a7 = this.a(this.a(d6, dg, dx), graphics, dt);
                final d5 a8 = this.a(this.a(d7, dg, dx), graphics, dt);
                final d5 a9 = this.a(this.a(d8, dg, dx), graphics, dt);
                d4.a(a7);
                d4.a(a8);
                d4.a(a9);
                a5.a(a7);
                a5.a(a8);
                a5.a(a9);
                a6.a(a7);
                final Rectangle boundingBox = d4.getBoundingBox();
                final Rectangle boundingBox2 = a5.getBoundingBox();
                if (boundingBox.width * boundingBox.height >= boundingBox2.width * boundingBox2.height) {
                    dg.g(true);
                }
                else {
                    dg.g(false);
                }
                if (dg.e1()) {
                    final d5 d10 = new d5(fn.a, fn.b);
                    final d5 d11 = new d5(fn.a + n7, fn.b + n6);
                    final d5 d12 = new d5(fn.a + n9, fn.b + n8);
                    final d5 a10 = this.a(this.a(d10, dg, dx), graphics, dt);
                    final d5 a11 = this.a(this.a(d11, dg, dx), graphics, dt);
                    final d5 a12 = this.a(this.a(d12, dg, dx), graphics, dt);
                    d5 = new d4();
                    d5.a(a10);
                    d5.a(a11);
                    d5.a(a12);
                    a5.a(a10);
                    a5.a(a11);
                    a5.a(a12);
                    a6.a(a10);
                    try {
                        final d5 a13 = d9.a(0);
                        final d5 a14 = d9.a(1);
                        final long n10 = a14.a - a13.a;
                        final long n11 = a14.b - a13.b;
                        dg.f(Math.sqrt(n10 * n10 + n11 * n11));
                    }
                    catch (Exception ex) {}
                }
            }
            else {
                a6 = a4.a();
            }
            final d4 d13 = new d4(a4);
            if (d4 != null) {
                for (int i = 0; i < d4.b(); ++i) {
                    d13.a(d4.a(i));
                }
            }
            if (d5 != null) {
                for (int j = 0; j < d5.b(); ++j) {
                    d13.a(d5.a(j));
                }
            }
            final da da = new da(d13.getBoundingBox());
            final boolean a15 = this.a(da, dg, graphics, dt, dx);
            dg.i(da);
            if (a15) {
                da da2 = null;
                da da3 = null;
                if (d4 != null) {
                    if (da2 == null) {
                        da2 = new da();
                    }
                    if (dg.e1()) {
                        final d4 d14 = new d4();
                        d14.a(d4.a(0));
                        d14.a(d4.a(1));
                        this.a(dt, graphics, d14, dg, false, false, component, ad, 1, false, da2, null, 0);
                        final d4 d15 = new d4();
                        d15.a(d4.a(0));
                        d15.a(d4.a(2));
                        this.a(dt, graphics, d15, dg, false, false, component, ad, 1, false, da2, null, 0);
                    }
                    else {
                        this.a(dt, graphics, d4, dg, false, true, component, ad, 1, false, da2, null, 0);
                    }
                    da3 = dg.fr();
                }
                if (d5 != null) {
                    if (da2 == null) {
                        da2 = new da();
                    }
                    if (dg.e1()) {
                        final d4 d16 = new d4();
                        d16.a(d5.a(0));
                        d16.a(d5.a(1));
                        this.a(dt, graphics, d16, dg, false, false, component, ad, 1, false, da2, null, 0);
                        final d4 d17 = new d4();
                        d17.a(d5.a(0));
                        d17.a(d5.a(2));
                        this.a(dt, graphics, d17, dg, false, false, component, ad, 1, false, da2, null, 0);
                    }
                    da3 = dg.fr();
                }
                da da4 = new da();
                this.a(dt, graphics, a4, dg, false, true, component, ad, ji.annotate.b8.a(dg, dx, this.ak, this.a4), false, da4, null, 0);
                if (da3 != null) {
                    dg.k(dg.fr().c(da3));
                }
                if (da2 != null) {
                    final d4 d18 = new d4();
                    d18.addPoint((int)da2.a, (int)da2.b);
                    d18.addPoint((int)(da2.a + da2.c), (int)(da2.b + da2.d));
                    d18.addPoint((int)da4.a, (int)da4.b);
                    d18.addPoint((int)(da4.a + da4.c), (int)(da4.b + da4.d));
                    da4 = new da(d18.getBounds());
                }
                dg.i(da4);
                if (dg.dy()) {
                    dg.e(da4);
                    boolean b = true;
                    if (fn.a == fo.a || fn.b == fo.b) {
                        b = false;
                    }
                    final da a16 = this.a(graphics, dg, a6, component, ad, ji.annotate.b8.a(1, dg, dx, this.ak, this.a4), d13, b, af, false, false);
                    if (a16 != null) {
                        dg.b(a16.a, a16.b, a16.c, a16.d);
                    }
                }
            }
        }
        else if (this.d3 && !this.a4) {
            dg.aj(false);
        }
    }
    
    private final void a(final dx dx, final dt dt, final Graphics graphics, final dg dg, final ad ad, final af af) throws Exception {
        final d4 a = this.a(dg.bu(), graphics, dt);
        final da da = new da(a.getBounds());
        int aj = 0;
        int aj2 = 0;
        if (dg.d5() == 8) {
            aj2 = this.aj;
            aj = this.aj;
        }
        final int a2 = dg.a(dx);
        if (a2 > 1) {
            aj2 = a2 * 5;
        }
        final da da2 = da;
        da2.a -= aj2;
        final da da3 = da;
        da3.b -= aj2;
        final da da4 = da;
        da4.c += 2 * aj2 + this.aj / 2;
        final da da5 = da;
        da5.d += 2 * aj2 + this.aj / 2;
        if (this.a(da, dg, graphics, dt, dx)) {
            if (!this.a4) {
                dg.aj(true);
            }
            if (dg.dy()) {
                dg.f0();
            }
            da da6 = new da();
            final boolean ey = dg.ey();
            final int a3 = ji.annotate.b8.a(dg, dx, this.ak, this.a4);
            d4 a4 = this.a(dt, graphics, a, dg, ey, dg.ei(), ad, ad, a3, false, da6, null, 0);
            if (dg.e2() && a.b() == 3) {
                final d5 a5 = a.a(0);
                final d5 a6 = a.a(1);
                final d5 a7 = a.a(2);
                final int max = Math.max((int)Math.round(2 * a3 + 25 * this.ak), 1);
                double a8 = 0.0;
                double a9 = 0.0;
                try {
                    a8 = this.a(a5, a6, dg);
                    a9 = this.a(a7, a6, dg);
                    if (dg.e5()) {
                        final double n = a8;
                        a8 = a9;
                        a9 = n;
                    }
                    double n2;
                    if (a9 > a8) {
                        n2 = a8 - (a9 - 6.283185307179586);
                    }
                    else {
                        n2 = a8 - a9;
                    }
                    dg.g(360.0 * n2 / 6.283185307179586);
                }
                catch (Exception ex) {}
                final d4 d4 = new d4();
                d4.a(new d5(da6.a, da6.b));
                d4.a(new d5(da6.a + da6.c, da6.b + da6.d));
                final Rectangle bounds = a4.getBounds();
                if (bounds.width > max && bounds.height > max) {
                    d4.a(this.a(dt, graphics, (int)a6.a, (int)a6.b, max, max, a3, dg, ad, ad, af, a8, a9));
                    da6 = new da(d4.getBounds());
                    if (a3 <= 2) {
                        final da da7 = da6;
                        --da7.a;
                        final da da8 = da6;
                        --da8.b;
                        final da da9 = da6;
                        da9.c += 2;
                        final da da10 = da6;
                        da10.d += 2;
                    }
                }
            }
            if (dg.d5() == 8) {
                final d4 d5 = new d4(a4);
                if (dg.ba()) {
                    final int a10 = ji.annotate.b8.a(dg, dx, this.ak, this.a4);
                    final d4 fm = dg.fm();
                    final Color darker = dg.c(dg, ad.o9).darker();
                    final Color black = Color.black;
                    this.aj = fm.getBounds().width / 6;
                    final da da11 = new da();
                    final d4 a11 = dg.fm().a();
                    a11.translate(this.aj / 2, this.aj / 2);
                    final d4 d6 = new d4();
                    final d5 a12 = a11.a(1);
                    final d5 a13 = a11.a(2);
                    final d5 a14 = a11.a(3);
                    d6.addPoint((int)a12.a, (int)a12.b + this.aj / 2);
                    d6.addPoint((int)a13.a, (int)a13.b);
                    d6.addPoint((int)a14.a + this.aj / 2, (int)a14.b);
                    final int n3 = 1 + (int)this.a(this.aj, dg, dx);
                    d5.a(this.a(dt, graphics, this.a(this.a(d6, dg, dx), graphics, dt), dg, false, false, ad, ad, n3, false, new da(), black, n3));
                    this.a(dt, graphics, a, dg, dg.ey(), dg.ei(), ad, ad, ji.annotate.b8.a(dg, dx, this.ak, this.a4), false, da11, null, 0);
                    this.a(dt, graphics, a4, dg, true, false, ad, ad, a10, false, new da(), darker, 1);
                }
                else {
                    final da da12 = new da(a4.getBounds());
                    final int a15 = ji.annotate.b8.a(dg, dx, this.ak, this.a4);
                    final int n4 = (int)(da12.d / 4);
                    final d4 fm2 = dg.fm();
                    fm2.a(1);
                    final d5 a16 = fm2.a(2);
                    final d5 a17 = fm2.a(3);
                    fm2.a(4);
                    final Color darker2 = dg.c(dg, ad.o9).darker();
                    final Color black2 = Color.black;
                    this.aj = fm2.getBounds().width / 6;
                    final da da13 = new da();
                    final d4 a18 = dg.fm().a();
                    a18.translate(this.aj / 2, this.aj / 2);
                    final d4 d7 = new d4();
                    final d5 a19 = a18.a(1);
                    final d5 a20 = a18.a(2);
                    final d5 a21 = a18.a(3);
                    final d5 a22 = a18.a(4);
                    d7.addPoint((int)a19.a, (int)a19.b + this.aj / 2);
                    d7.addPoint((int)a20.a, (int)a20.b - this.aj / 2);
                    d7.addPoint((int)a21.a - this.aj / 2, (int)a21.b);
                    d7.addPoint((int)a22.a + this.aj / 2, (int)a22.b);
                    final int n5 = 1 + (int)this.a(this.aj, dg, dx);
                    d5.a(this.a(dt, graphics, this.a(this.a(d7, dg, dx), graphics, dt), dg, false, false, ad, ad, n5, false, new da(), black2, n5));
                    final da da14 = new da();
                    d5.a(this.a(dt, graphics, a, dg, dg.ey(), dg.ei(), ad, ad, ji.annotate.b8.a(dg, dx, this.ak, this.a4), false, da13, null, 0));
                    final d4 a23 = dg.fm().a();
                    final d5 a24 = a23.a(2);
                    final d5 a25 = a23.a(3);
                    final d4 d8 = new d4();
                    d8.a(new d5(a17.a, a16.b + aj / 2));
                    d8.addPoint((int)a25.a, (int)a24.b);
                    d8.a(a24);
                    d8.a(a25);
                    d5.a(this.a(dt, graphics, this.a(this.a(d8, dg, dx), graphics, dt), dg, false, true, ad, ad, a15, false, new da(), darker2, 0));
                    d5.a(this.a(dt, graphics, a4, dg, true, false, ad, ad, a15, false, new da(), darker2, 1));
                }
                a4 = new d4(d5.getBounds());
                da6 = new da(d5.getBounds());
            }
            dg.i(da6);
            if (dg.dy()) {
                final da a26 = this.a(graphics, dg, a4, ad, ad, ji.annotate.b8.a(1, dg, dx, this.ak, this.a4), af);
                if (a26 != null) {
                    dg.b(a26.a, a26.b, a26.c, a26.d);
                }
            }
            try {
                if (dg.d5() == 8) {
                    this.b.a(graphics, dg, this.f, dx, ad, this, this.l, a4, this.aj, dt, this.q, this.g);
                }
            }
            catch (Exception ex2) {}
        }
        else if (this.d3 && !this.a4) {
            dg.aj(false);
        }
    }
    
    private final double a(final d5 d5, final d5 d6, final dg dg) {
        double atan;
        if (d5.a - d6.a != 0) {
            atan = Math.atan((d5.b - d6.b) / (d5.a - d6.a));
        }
        else if (d5.b > d6.b) {
            atan = -1.5707963267948966;
        }
        else {
            atan = 1.5707963267948966;
        }
        if (d5.a > d6.a) {
            atan += 3.141592653589793;
        }
        else if (d5.b > d6.b) {
            atan += 6.283185307179586;
        }
        return atan;
    }
    
    protected final d4 a(final dt dt, final Graphics graphics, final d4 d4, final dg dg, final boolean b, final boolean b2, final Component component, final ad ad, final int n, final boolean b3, final da da, final Color color, final int n2) throws Exception {
        final boolean b4 = dg.d4() == 7;
        final d4 a = ji.util.d.a(d4);
        d4 d5 = new d4();
        final d4 d6 = new d4();
        Rectangle rectangle = a.getBoundingBox();
        final d4 d7 = new d4();
        int n3 = n;
        final boolean o9 = ad.o9;
        final boolean a2 = dg.a(dg, o9);
        ji.util.d.am(true);
        boolean b5 = false;
        Color b6 = null;
        if (dg.d3() && !b4 && dg.c(dg, o9) == null && dg.a(this.u) <= 0) {
            b6 = dg.b(dg, o9);
            b5 = true;
            dg.v(1);
            dg.c(Color.black);
        }
        final Point point = new Point(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);
        if (b2) {
            if (graphics != null) {
                if (color != null) {
                    if (this.b7) {
                        graphics.setColor(this.a(color, a2));
                    }
                }
                else if (a2) {
                    if (this.b7) {
                        graphics.setColor(component.getBackground().brighter().brighter());
                        Color color2 = dg.c(dg, o9);
                        if (color2 == null) {
                            color2 = Color.white;
                        }
                        ji.graphic.d6.a(graphics, color2);
                    }
                }
                else if (this.b7) {
                    graphics.setColor(this.d(dg, o9));
                }
                if (this.b7) {
                    ji.graphic.d6.a(this.aa, graphics, a);
                }
                if (a2 && this.b7) {
                    ji.graphic.d6.a(graphics);
                }
            }
            else {
                if (color != null) {
                    dt.a(this.a(color, a2));
                }
                else {
                    dt.a(this.d(dg, o9));
                    if (a2) {
                        dt.e(true);
                    }
                }
                if (dg.d5() == 8 && dt != null && this.a(dt) == 1 && this.q) {
                    dt.a(ji.util.d.e4());
                }
                this.b(dt, a, component);
                if (a2) {
                    dt.e(false);
                }
            }
        }
        if (color != null) {
            if (graphics != null) {
                if (this.b7) {
                    graphics.setColor(this.a(color, a2));
                }
            }
            else {
                dt.a(this.a(color, a2));
            }
        }
        else if (graphics != null) {
            if (this.b7) {
                graphics.setColor(this.c(dg, o9));
            }
        }
        else {
            dt.a(this.c(dg, o9));
        }
        if (n2 > 0) {
            n3 = n2;
        }
        d4 d8;
        if (dg.a(this.u) <= 0 && n2 < 1) {
            d8 = a.a();
            for (int b7 = a.b(), i = 0; i < b7; ++i) {
                dg.e(i, i);
            }
        }
        else if (n3 <= 2) {
            if (graphics != null) {
                if (b) {
                    if (this.b7) {
                        ji.graphic.d6.a(graphics, a);
                    }
                }
                else if (this.b7) {
                    ji.graphic.d6.b(graphics, a.xpoints, a.ypoints, a.b());
                }
            }
            else if (b) {
                this.a(dt, a, component);
            }
            else {
                this.a(dt, a.xpoints, a.ypoints, a.b(), component);
            }
            d8 = a.a();
            for (int b8 = a.b(), j = 0; j < b8; ++j) {
                dg.e(j, j);
            }
        }
        else {
            final int b9 = a.b();
            final int[] xpoints = a.xpoints;
            final int[] ypoints = a.ypoints;
            final double n4 = 6.283185307179586;
            final double n5 = 1.5707963267948966;
            final double n6 = n3 / 2.0;
            if (b9 < 1) {
                d8 = a.a();
                for (int b10 = a.b(), k = 0; k < b10; ++k) {
                    dg.e(k, k);
                }
            }
            else if (b9 < 2) {
                d8 = a.a();
                for (int b11 = a.b(), l = 0; l < b11; ++l) {
                    dg.e(l, l);
                }
            }
            else if (b9 < 3) {
                d8 = new d4();
                final int n7 = xpoints[0];
                final int n8 = ypoints[0];
                final int n9 = xpoints[1];
                final int n10 = ypoints[1];
                dg.e(0, 0);
                dg.e(1, 1);
                final int n11 = n7 - n9;
                final int n12 = n8 - n10;
                double asin = Math.asin(n12 / (int)Math.sqrt(n11 * n11 + n12 * n12));
                if (n7 < n9) {
                    asin = 3.141592653589793 - asin;
                }
                double n13 = asin + n5;
                if (n13 < 0.0) {
                    n13 += n4;
                }
                final double n14 = n4 - n13;
                final int n15 = (int)(n6 * Math.cos(n14));
                final int n16 = (int)(n6 * Math.sin(n14));
                final d5 d9 = new d5(n7 + n15, n8 - n16);
                final d5 d10 = new d5(n7 - n15, n8 + n16);
                d8.addPoint(n7, n8);
                d5 = new d4();
                d5.a(d9);
                d5.a(d10);
                final d5 d11 = new d5(d9.a + (n9 - n7), d9.b + (n10 - n8));
                final d5 d12 = new d5(d10.a + (n9 - n7), d10.b + (n10 - n8));
                d8.addPoint(n9, n10);
                d5.a(d9);
                d5.a(d11);
                d5.a(d12);
                d5.a(d10);
                d6.a(d9);
                d6.a(d11);
                d6.a(d12);
                d6.a(d10);
                if (graphics != null) {
                    if (this.b7) {
                        ji.graphic.d6.a(graphics, d5.c(), d5.d(), d5.b());
                    }
                }
                else {
                    this.b(dt, d5, component);
                }
            }
            else {
                d8 = new d4();
                if (!dg.ey()) {
                    d8.addPoint(xpoints[b9 - 1], ypoints[b9 - 1]);
                }
                for (int n17 = 0; n17 < b9; ++n17) {
                    try {
                        final int n18 = xpoints[n17];
                        final int n19 = ypoints[n17];
                        int n20;
                        int n21;
                        if (n17 + 1 < b9) {
                            n20 = xpoints[n17 + 1];
                            n21 = ypoints[n17 + 1];
                        }
                        else {
                            n20 = xpoints[0];
                            n21 = ypoints[0];
                        }
                        int n22;
                        int n23;
                        if (n17 + 2 < b9) {
                            n22 = xpoints[n17 + 2];
                            n23 = ypoints[n17 + 2];
                        }
                        else if (n17 + 1 < b9) {
                            n22 = xpoints[0];
                            n23 = ypoints[0];
                            if (!b) {
                                dg.e(b9 - 1, 0);
                                dg.e(0, b9 - 1);
                                dg.e(n17, d8.b() - 1);
                                d8.addPoint(n22, n23);
                                break;
                            }
                        }
                        else {
                            n22 = xpoints[1];
                            n23 = ypoints[1];
                        }
                        final int n24 = n18 - n20;
                        final int n25 = n19 - n21;
                        final int n26 = (int)Math.sqrt(n24 * n24 + n25 * n25);
                        final int n27 = n20 - n22;
                        final int n28 = n21 - n23;
                        final int n29 = (int)Math.sqrt(n27 * n27 + n28 * n28);
                        if (n26 != 0.0) {
                            if (n29 != 0.0) {
                                double asin2 = Math.asin(n25 / n26);
                                if (n18 < n20) {
                                    asin2 = 3.141592653589793 - asin2;
                                }
                                double n30 = asin2 + n5;
                                if (n30 < 0.0) {
                                    n30 += n4;
                                }
                                double n31 = -(Math.asin(n28 / n29) - n5);
                                if (n22 < n20) {
                                    n31 = n4 - n31;
                                }
                                double n32 = (n30 + n31 + n4) / 2.0;
                                if (n32 > n4) {
                                    n32 -= n4;
                                }
                                double n33 = n30 + n5;
                                if (n33 > n4) {
                                    n33 -= n4;
                                }
                                double n34 = n32 + 3.141592653589793;
                                if (n34 > n4) {
                                    n34 -= n4;
                                }
                                final double n35 = n34 - n33;
                                final double n36 = n32 - n5;
                                final double n37 = n4 - n30;
                                final int n38 = (int)(n6 * Math.cos(n37));
                                final int n39 = (int)(n6 * Math.sin(n37));
                                final d5 d13 = new d5(n18 + n38, n19 - n39);
                                final d5 d14 = new d5(n18 - n38, n19 + n39);
                                final double n40 = n4 - n31 + n5;
                                final int n41 = (int)(n6 * Math.sin(n40));
                                final int n42 = (int)(n6 * Math.cos(n40));
                                final d5 d15 = new d5(n22 + n41, n23 + n42);
                                final d5 d16 = new d5(n22 - n41, n23 - n42);
                                double n44;
                                double n43 = n44 = -(n3 / (2.0 * Math.cos(n35)));
                                if (n43 < 0) {
                                    Math.max(-n26 + n6, -n29 + n6);
                                }
                                else {
                                    Math.min(n26 - n6, n29 - n6);
                                }
                                final double n45 = 360.0 * (n30 - n31) / 6.283185307179586;
                                d5 = new d4();
                                if (n45 <= 4.0 && n45 >= -4.0) {
                                    final d5 d17 = new d5(d13.a + (n20 - n18), d13.b + (n21 - n19));
                                    final d5 d18 = new d5(d14.a + (n20 - n18), d14.b + (n21 - n19));
                                    if (dg.ey()) {
                                        if (d8.b() > 0) {
                                            dg.e(n17, d8.b() - 1);
                                        }
                                        else {
                                            dg.e(n17, b9 - 1);
                                        }
                                    }
                                    d8.a(d17);
                                    d5.a(d13);
                                    d5.a(d17);
                                    d5.a(d18);
                                    d5.a(d14);
                                    d6.a(d13);
                                    d6.a(d17);
                                    d6.a(d18);
                                    d6.a(d14);
                                    if (graphics != null) {
                                        if (this.b7) {
                                            ji.graphic.d6.a(graphics, d5.c(), d5.d(), d5.b());
                                        }
                                    }
                                    else {
                                        this.b(dt, d5, component);
                                    }
                                    d5 = new d4();
                                    d5.a(d15);
                                    d5.a(d17);
                                    d5.a(d18);
                                    d5.a(d16);
                                    d6.a(d15);
                                    d6.a(d17);
                                    d6.a(d18);
                                    d6.a(d16);
                                    if (graphics != null) {
                                        if (this.b7) {
                                            ji.graphic.d6.a(graphics, d5.c(), d5.d(), d5.b());
                                        }
                                    }
                                    else {
                                        this.b(dt, d5, component);
                                    }
                                }
                                else {
                                    if (n45 > 180.0 || n45 < 0.0) {
                                        if (n43 < 0) {
                                            n43 = Math.max(n43, -n3);
                                        }
                                        else {
                                            n43 = Math.min(n43, n3);
                                        }
                                    }
                                    else if (n44 < 0) {
                                        n44 = Math.max(n44, -n3);
                                    }
                                    else {
                                        n44 = Math.min(n44, n3);
                                    }
                                    final d5 d19 = new d5((int)(n20 + n43 * Math.cos(n36)), (int)(n21 + n43 * Math.sin(n36)));
                                    final d5 d20 = new d5((int)(n20 - n44 * Math.cos(n36)), (int)(n21 - n44 * Math.sin(n36)));
                                    d5.a(d13);
                                    d5.a(d19);
                                    d5.a(d16);
                                    d5.a(d15);
                                    d5.a(d20);
                                    d5.a(d14);
                                    d6.a(d13);
                                    d6.a(d19);
                                    d6.a(d16);
                                    d6.a(d15);
                                    d6.a(d20);
                                    d6.a(d14);
                                    if (dg.ey()) {
                                        if (d8.b() > 0) {
                                            dg.e(n17, d8.b() - 1);
                                        }
                                        else {
                                            dg.e(n17, b9 - 1);
                                        }
                                    }
                                    else if (d8.b() > 1) {
                                        dg.e(n17, n17);
                                    }
                                    if ((n45 <= 0.0 && n45 >= -180.0) || n45 >= 270.0) {
                                        d8.a(d20);
                                    }
                                    else {
                                        d8.a(d19);
                                    }
                                    if (graphics != null) {
                                        if (this.b7) {
                                            ji.graphic.d6.a(graphics, d5.c(), d5.d(), d5.b());
                                        }
                                    }
                                    else {
                                        this.b(dt, d5, component);
                                    }
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
            }
            rectangle = d6.getBounds();
        }
        for (int n46 = 0; n46 < d5.b(); ++n46) {
            d7.a(d5.a(n46));
        }
        for (int n47 = 0; n47 < d6.b(); ++n47) {
            d7.a(d6.a(n47));
        }
        for (int n48 = 0; n48 < a.b(); ++n48) {
            d7.a(a.a(n48));
        }
        final d4 a3 = ji.util.d.a(d7);
        ji.util.d.a(d5);
        if (da.c > 0 && da.d > 0) {
            a3.addPoint((int)da.a, (int)da.b);
            a3.addPoint((int)da.a + (int)da.c, (int)da.b + (int)da.d);
        }
        final Rectangle bounds = a3.getBounds();
        da.a = bounds.x;
        da.b = bounds.y;
        da.c = bounds.width;
        da.d = bounds.height;
        if (n3 <= 2) {
            --da.a;
            --da.b;
            da.c += 2;
            da.d += 2;
        }
        if (dt != null) {
            final da da2 = new da(da);
            int b12 = this.b(dt);
            int c = this.c(dt);
            if (b12 >= 0) {
                b12 = -1;
            }
            if (c >= 0) {
                c = -1;
            }
            da2.a = da2.a - b12 + this.as;
            da2.b = da2.b - c + this.at;
            dg.k(da2);
        }
        dg.i(new da(rectangle));
        if (this.p) {
            if (graphics != null) {
                if (this.b7) {
                    graphics.setColor(Color.lightGray);
                    ji.graphic.d6.a(graphics, rectangle.x - n3 / 2, rectangle.y - n3 / 2, rectangle.width + n3, rectangle.height + n3);
                }
            }
            else {
                dt.a(Color.lightGray);
                this.b(dt, rectangle.x - n3 / 2, rectangle.y - n3 / 2, rectangle.width + n3, rectangle.height + n3, component);
            }
        }
        if (b5) {
            dg.v(0);
            dg.c(b6);
        }
        return d8;
    }
    
    private final boolean a(final da da, final dg dg, final Graphics graphics, final dt dt, final dx dx) {
        boolean b = false;
        try {
            if (dg.c(this.e, this.d)) {
                if (this.cl) {
                    return false;
                }
            }
            else if (this.cl && this.em) {
                return false;
            }
            if (this.cm) {
                return true;
            }
            if (this.q) {
                return true;
            }
            this.b8 = true;
            da da2 = this.a(dg, graphics, dt, false, false, dx);
            if (da2 != null) {
                int n = 0;
                if (dg.d4() == 5) {
                    n = 20 + 2 * dg.a(dx);
                }
                if (dg.d5() == 8) {
                    n = 2 * this.aj + 1;
                }
                final da da3 = da2;
                da3.a -= n;
                final da da4 = da2;
                da4.b -= n;
                final da da5 = da2;
                da5.c += 2 * n;
                final da da6 = da2;
                da6.d += 2 * n;
                if (graphics != null) {
                    final Rectangle clipRect = graphics.getClipRect();
                    if (clipRect != null && (clipRect.width > 0 || clipRect.height > 0)) {
                        da2 = da2.b(new da(clipRect));
                    }
                }
                da da7;
                if (this.ce != null) {
                    da7 = this.ce.b(da2);
                }
                else {
                    da7 = this.l.b(da2);
                }
                b = (da7.c > 0 && da7.d > 0);
            }
            else {
                b = true;
            }
        }
        finally {
            this.b8 = false;
        }
        return b;
    }
    
    private final d4 a(final dt dt, final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final dg dg, final Component component, final ad ad, final af af, final double n6, final double n7) throws Exception {
        final d4 d4 = new d4();
        int n8 = n;
        int n9 = n2;
        int n10 = n3;
        int n11 = n4;
        final boolean o9 = ad.o9;
        if (n5 > 1) {
            n8 -= n5 / 2;
            n9 -= n5 / 2;
            n10 += n5 / 2;
            n11 += n5 / 2;
        }
        int min = Math.min(Math.max((int)(6.283185307179586 * Math.max(n10, n11) / 3.0), 2), 360);
        final d4 d5 = new d4();
        final int n12 = n5 / 2;
        if (n6 != n7) {
            double n13;
            if (n7 > n6) {
                n13 = n6 - (n7 - 6.283185307179586);
            }
            else {
                n13 = n6 - n7;
            }
            min = (int)(360.0 * n13 / 6.283185307179586);
            int n14 = -100;
            int n15 = -100;
            for (int i = 0; i < min; ++i) {
                final double n16 = -3.141592653589793 + (n6 - i * n13 / min);
                final int n17 = n9 + n12 + (int)(n11 * Math.sin(n16));
                final int n18 = n8 + n12 + (int)(n10 * Math.cos(n16));
                if (n18 != n14 || n17 != n15) {
                    if (n18 > 0 && n17 > 0) {
                        d5.addPoint(n18, n17);
                    }
                    n14 = n18;
                    n15 = n17;
                }
            }
        }
        else {
            for (int j = 0; j < min; ++j) {
                final double n19 = j * 2 * 3.141592653589793 / min;
                d5.addPoint(n8 + n12 + (int)(n10 * Math.cos(n19)), n9 + n12 + (int)(n11 * Math.sin(n19)));
            }
        }
        if (graphics != null) {
            if (this.b7) {
                graphics.setColor(this.c(dg, o9));
            }
        }
        else {
            dt.a(this.c(dg, o9));
        }
        if (n5 > 2 && n6 == n7) {
            if (dg.ei()) {
                final d4 d6 = new d4();
                final int n20 = n10 - n5 / 2;
                final int n21 = n11 - n5 / 2;
                for (int k = 0; k < min; ++k) {
                    d6.addPoint(n8 + n12 + (int)(n20 * Math.cos(k * 2 * 3.141592653589793 / min)), n9 + n12 + (int)(n21 * Math.sin(k * 2 * 3.141592653589793 / min)));
                }
                if (graphics != null) {
                    if (this.b7) {
                        graphics.setColor(this.d(dg, o9));
                        ji.graphic.d6.a(this.aa, graphics, d6);
                    }
                }
                else {
                    dt.a(this.d(dg, o9));
                    this.b(dt, d6, component);
                }
            }
            if (graphics != null) {
                if (this.b7) {
                    graphics.setColor(this.c(dg, o9));
                }
            }
            else {
                dt.a(this.c(dg, o9));
            }
            final int n22 = n10 - n5;
            final int n23 = n11 - n5;
            for (int l = 0; l < min; ++l) {
                d5.addPoint(n8 + n12 + (int)(n22 * Math.cos(l * 2 * 3.141592653589793 / min)), n9 + n12 + (int)(n23 * Math.sin(l * 2 * 3.141592653589793 / min)));
            }
            if (graphics != null) {
                if (this.b7) {
                    ji.graphic.d6.a(this.aa, graphics, d5);
                }
            }
            else {
                this.b(dt, d5, component);
            }
            final int n24 = n9 - 2 + n12;
            final int n25 = n8 + n12 + n10 - n5;
            if (graphics != null) {
                if (this.b7) {
                    ji.graphic.d6.b(graphics, n25, n24, n5 - 1, 2);
                }
            }
            else {
                this.c(dt, n25, n24, n5 - 1, 2, component);
            }
            dg.i(new da(d5.getBoundingBox()));
            if (this.p) {
                final da da = new da(d5.getBoundingBox());
                if (graphics != null) {
                    if (this.b7) {
                        ji.graphic.d6.a(graphics, (int)da.a, (int)da.b, (int)da.c, (int)da.d);
                    }
                }
                else {
                    this.b(dt, (int)da.a, (int)da.b, (int)da.c, (int)da.d, component);
                }
            }
        }
        else {
            if (dg.ei() && n6 == n7) {
                if (graphics != null) {
                    if (this.b7) {
                        graphics.setColor(this.d(dg, o9));
                        ji.graphic.d6.a(this.aa, graphics, d5);
                        graphics.setColor(this.c(dg, o9));
                    }
                }
                else {
                    dt.a(this.d(dg, o9));
                    this.b(dt, d5, component);
                    dt.a(this.c(dg, o9));
                }
            }
            if (graphics != null) {
                if (this.b7) {
                    if (n6 != n7) {
                        ji.graphic.d6.b(graphics, d5.c(), d5.d(), d5.b());
                    }
                    else {
                        ji.graphic.d6.a(graphics, d5);
                    }
                }
            }
            else if (n6 != n7) {
                this.a(dt, d5, false, component);
            }
            else {
                this.a(dt, d5, true, component);
            }
            dg.i(new da(d5.getBoundingBox()));
            if (this.p) {
                final da da2 = new da(d5.getBoundingBox());
                if (graphics != null) {
                    if (this.b7) {
                        ji.graphic.d6.a(graphics, (int)da2.a, (int)da2.b, (int)da2.c, (int)da2.d);
                    }
                }
                else {
                    this.b(dt, (int)da2.a, (int)da2.b, (int)da2.c, (int)da2.d, component);
                }
            }
        }
        final Rectangle bounds = d5.getBounds();
        d4.addPoint(bounds.x + bounds.width / 2, bounds.y);
        d4.addPoint(bounds.x + bounds.width, bounds.y + bounds.height / 2);
        d4.addPoint(bounds.x, bounds.y + bounds.height / 2);
        d4.addPoint(bounds.x + bounds.width / 2, bounds.y + bounds.height);
        if (dt != null) {
            final da da3 = new da(d4.getBoundingBox());
            int b = this.b(dt);
            int c = this.c(dt);
            if (b >= 0) {
                b = -1;
            }
            if (c >= 0) {
                c = -1;
            }
            da3.a = da3.a - b + this.as;
            da3.b = da3.b - c + this.at;
            dg.k(da3);
        }
        return d4;
    }
    
    private final void a(final dx dx, final dt dt, final Graphics graphics, final dg dg, final Component component, final ad ad, final af af, final boolean b) throws Exception {
        final d5 fn = dg.fn();
        final d5 a = this.a(dg.bv(), graphics, dt);
        final long fk = dg.fk();
        final long fl = dg.fl();
        long n = this.a(fk, dg, dx);
        long n2 = this.a(fl, dg, dx);
        if (this.g == 90 || this.g == 270) {
            n = this.a(fl, dg, dx);
            n2 = this.a(fk, dg, dx);
        }
        if (this.a(new da(fn.a - fk, fn.b - fl, 2 * fk, 2 * fl), dg, graphics, dt, dx)) {
            if (!this.a4) {
                dg.aj(true);
            }
            if (dg.dy()) {
                dg.f0();
            }
            final d4 a2 = this.a(dt, graphics, (int)a.a, (int)a.b, (int)n, (int)n2, ji.annotate.b8.a(dg, dx, this.ak, this.a4), dg, component, ad, af, 0.0, 0.0);
            if (dg.dy()) {
                for (int b2 = a2.b(), i = 0; i < b2; ++i) {
                    dg.e(i, i);
                }
                final da a3 = this.a(graphics, dg, a2, component, ad, ji.annotate.b8.a(1, dg, dx, this.ak, this.a4), af);
                if (a3 != null && b) {
                    dg.b(a3.a, a3.b, a3.c, a3.d);
                }
            }
        }
        else {
            dg.aj(false);
        }
    }
    
    private final int m(final dg dg) {
        int n = 0;
        try {
            this.a(dg);
            for (int i = 0; i < this.bc; ++i) {
                if (((xf)this.a9.b(i)).c()) {
                    ++n;
                }
            }
        }
        catch (Exception ex) {}
        return n;
    }
    
    public final void a(final dg dg) {
        if (dg != null) {
            try {
                for (int i = 0; i < this.bc; ++i) {
                    ((xf)this.a9.b(i)).b(true);
                }
                this.a("annedittext", false);
                if (ji.util.d.am(this.eq) && !ji.util.e.av()) {
                    this.a("anneditsecurity", false);
                }
                else {
                    this.a("anneditsecurity", dg.d(this.e, this.d) && dg.bn() == 2);
                }
                this.b("annfont", true);
                this.b("annlinewidthmore", true);
                this.b("annlinewidthmore2", true);
                this.b("annlinewidthless", true);
                this.b("annlinewidthless2", !dg.a(this.aa));
                this.a("annfont", false);
                this.a("annlinewidthmore2", false);
                this.a("annlinewidthless2", false);
                this.a("anntransparent", false);
                this.a("annalignment", false);
                this.a("annheadmore", false);
                this.a("annheadless", false);
                this.a("annrotatel", false);
                this.a("annrotater", false);
                this.a("anndelete", dg.e(this.e, this.d));
                this.a("anntxColor", false);
                this.a("annangleflip", false);
                this.a("annstrikethrough", false);
                final boolean b = this.aa.f8() == 0 && this.aa.f7() == 0;
                Label_0427: {
                    switch (dg.d4()) {
                        case 3: {
                            switch (dg.d5()) {
                                case 8: {
                                    this.a("annlinewidthmore", false);
                                    this.a("annlinewidthless", false);
                                    this.a("annlinecolor", false);
                                    this.a("annedittext", true);
                                    if (ji.util.d.bf()) {
                                        this.a("annfillcolor", false);
                                    }
                                    break Label_0427;
                                }
                                case 14: {
                                    this.a("annedittext", false);
                                    break Label_0427;
                                }
                            }
                            break;
                        }
                        case 1: {
                            switch (dg.d5()) {
                                case 9: {
                                    this.a("annlinewidthmore", false);
                                    this.a("annlinewidthless", false);
                                    this.a("annlinecolor", false);
                                    this.a("annfillcolor", false);
                                    this.a("annedittext", false);
                                    if (!ji.util.d.bf()) {
                                        this.a("annrotatel", b);
                                        this.a("annrotater", b);
                                        break;
                                    }
                                    break;
                                }
                                case 13: {
                                    this.a("annedittext", false);
                                    break;
                                }
                                case 11: {
                                    this.a("annedittext", false);
                                    break;
                                }
                            }
                            break;
                        }
                        case 7: {
                            this.a("annlinewidthmore", false);
                            this.a("annlinewidthless", false);
                            this.a("annlinewidthmore2", true);
                            this.a("annlinewidthless2", true);
                            if (ji.util.i.c(247)) {
                                this.a("annfont", true);
                            }
                            this.a("anntransparent", true);
                            if (ji.util.i.c(150)) {
                                this.a("annalignment", true);
                            }
                            this.a("annstrikethrough", true);
                            if (!ji.util.d.bf()) {
                                this.a("annrotatel", true);
                                this.a("annrotater", true);
                                break;
                            }
                            if (dg.d4() == 9 || dg.ax()) {
                                this.a("annrotatel", true);
                                this.a("annrotater", true);
                                break;
                            }
                            break;
                        }
                        case 9: {
                            switch (dg.d5()) {
                                case 9: {
                                    this.a("annlinewidthmore", false);
                                    this.a("annlinewidthless", false);
                                    this.a("annlinecolor", false);
                                    this.a("annfillcolor", false);
                                    this.a("annedittext", false);
                                    if (!ji.util.d.bf()) {
                                        this.a("annrotatel", b);
                                        this.a("annrotater", b);
                                        break;
                                    }
                                    break;
                                }
                                case 7: {
                                    this.a("annlinewidthmore", false);
                                    this.a("annlinewidthless", false);
                                    this.a("annlinewidthmore2", true);
                                    this.a("annlinewidthless2", true);
                                    if (ji.util.i.c(247)) {
                                        this.a("annfont", true);
                                    }
                                    this.a("anntransparent", true);
                                    if (ji.util.i.c(150)) {
                                        this.a("annalignment", true);
                                    }
                                    this.a("annrotatel", true);
                                    this.a("annrotater", true);
                                    this.a("annstrikethrough", true);
                                    break;
                                }
                            }
                            break;
                        }
                        case 4: {
                            this.a("annfillcolor", false);
                            if (dg.e1()) {
                                this.a("annlinewidthmore", false);
                                this.a("annlinewidthless", false);
                                break;
                            }
                            break;
                        }
                        case 5: {
                            this.a("annfillcolor", false);
                            this.a("annheadmore", true);
                            this.a("annheadless", true);
                            break;
                        }
                        case 6: {
                            this.a("annfillcolor", false);
                            if (dg.e2()) {
                                this.a("annlinewidthmore", false);
                                this.a("annlinewidthless", false);
                                this.a("annangleflip", true);
                                break;
                            }
                            break;
                        }
                    }
                }
                c ae = null;
                if (this.aa.a() != null) {
                    ae = this.aa.a().ae();
                }
                if (ae != null && ae.b() > 0) {
                    final String lowerCase = dg.a(dg.d5(), false).toLowerCase();
                    for (int j = 0; j < this.bc; ++j) {
                        final xf xf = (xf)this.a9.b(j);
                        final String[] array = (String[])ae.d(xf.a());
                        if (array != null) {
                            if (array.length == 0) {
                                this.a(xf.e(), false);
                            }
                            else {
                                for (int k = 0; k < array.length; ++k) {
                                    if (array[k].toLowerCase().equals(lowerCase)) {
                                        this.a(xf.e(), false);
                                    }
                                }
                            }
                        }
                    }
                    this.cc = false;
                    Block_31: {
                        for (int l = 0; l < this.bc; ++l) {
                            if (((xf)this.a9.b(l)).c()) {
                                break Block_31;
                            }
                        }
                        return;
                    }
                    this.cc = true;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void a(final String s, final boolean b) {
        if (s != null) {
            try {
                final String lowerCase = s.toLowerCase();
                for (int i = 0; i < this.bc; ++i) {
                    final xf xf = (xf)this.a9.b(i);
                    final String e = xf.e();
                    if (e != null && e.toLowerCase().equals(lowerCase)) {
                        xf.b(b);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final int b(final String s, final boolean b) {
        int n = -1;
        if (s != null) {
            try {
                final String lowerCase = s.toLowerCase();
                for (int i = 0; i < this.bc; ++i) {
                    final xf xf = (xf)this.a9.b(i);
                    final String e = xf.e();
                    if (e != null && e.toLowerCase().equals(lowerCase)) {
                        n = i;
                        xf.a(b);
                        if (!b) {
                            if (i == this.bj) {
                                this.bj = -1;
                            }
                            if (xf.g() != 0) {
                                xf.b(0);
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        return n;
    }
    
    public final void a(final dg dg, final Graphics graphics, final ad ad, final af af) {
        try {
            if (this.bj >= 0) {
                final xf xf = (xf)this.a9.b(this.bj);
                if (this.d4 != null && xf.equals(this.d4)) {
                    if (dg.d4() == 7 || dg.d4() == 9) {
                        int n = 0;
                        if (dg.dz()) {
                            n = 1;
                        }
                        if (this.d4.d() != n) {
                            this.d4.a(n);
                            this.a(xf, graphics, ad, af, false, dg);
                        }
                    }
                    if (dg.d4() == 7) {
                        int n2 = 0;
                        if (dg.i()) {
                            n2 = 1;
                        }
                        if (this.d7.d() != n2) {
                            this.d7.a(n2);
                            this.a(xf, graphics, ad, af, false, dg);
                        }
                    }
                }
                if (this.d5 != null && xf.equals(this.d5) && (dg.d4() == 7 || dg.d4() == 9)) {
                    this.a(graphics, dg, ad, af);
                }
                if (this.d6 != null && xf.equals(this.d6) && dg.d4() == 6) {
                    int n3 = 0;
                    if (dg.e5()) {
                        n3 = 1;
                    }
                    if (this.d6.d() != n3) {
                        this.d6.a(n3);
                        this.a(xf, graphics, ad, af, false, dg);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final boolean b, final Graphics graphics, final ad ad, final af af) {
        try {
            for (int i = 0; i < this.bc; ++i) {
                final xf xf = (xf)this.a9.b(i);
                if (xf.e() != null) {
                    if (!xf.e().endsWith("2")) {
                        xf.a(b);
                    }
                    if (!xf.b()) {
                        if (xf.g() != 0) {
                            xf.b(0);
                        }
                    }
                    else if (i == this.bj && xf.g() != 2) {
                        xf.b(2);
                    }
                    final dg b2 = this.aa.b3();
                    if (i == this.bj) {
                        this.a(xf, graphics, ad, af, true, b2);
                    }
                    else {
                        this.a(xf, graphics, ad, af, false, b2);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final boolean c(final String s) {
        if (s != null) {
            try {
                final String lowerCase = s.toLowerCase();
                for (int i = 0; i < this.bc; ++i) {
                    final xf xf = (xf)this.a9.b(i);
                    final String e = xf.e();
                    if (e != null && e.toLowerCase().equals(lowerCase)) {
                        return xf.b();
                    }
                }
                return true;
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    protected final da a(final Graphics graphics, final dg dg, final d4 d4, final Component component, final ad ad, final int n, final af af) {
        return this.a(graphics, dg, d4, component, ad, n, null, true, af, false, false);
    }
    
    protected final da a(final Graphics graphics, final dg dg, final d4 d4, final Component component, final ad ad, final int n, final d4 d5, final boolean b, final af af, final boolean b2, final boolean b3) {
        Rectangle rectangle = null;
        da da = null;
        if (d4 != null && this.t != null && graphics != null && !this.e0) {
            try {
                this.e0 = true;
                boolean b4 = true;
                if (!dg.y()) {
                    b4 = false;
                }
                if (!b3 && this.aa.ci() > 0 && this.e8 != null) {
                    b4 = false;
                }
                dg.b(d4);
                dg.c(d5);
                if (b2) {
                    if (this.l != null) {
                        if (rectangle == null) {
                            rectangle = graphics.getClipBounds();
                        }
                        graphics.setClip(this.l.a());
                    }
                }
                else {
                    final Rectangle c = dg.c(this.l);
                    if (c != null && graphics.getClipBounds() == null) {
                        if (rectangle == null) {
                            rectangle = graphics.getClipBounds();
                        }
                        graphics.setClip(c);
                    }
                }
                if (b4 && graphics.getClipBounds() != null) {
                    if (rectangle == null) {
                        rectangle = graphics.getClipBounds();
                    }
                    graphics.setClip((int)this.l.a, (int)this.l.b, (int)this.l.c, (int)this.l.d);
                }
                final boolean gl = dg.gl();
                final int b5 = d4.b();
                int n2 = (int)this.t.a;
                int n3 = (int)this.t.b;
                int n4 = 0;
                int n5 = 0;
                if (b4) {
                    dg.m(true);
                }
                if (dg.dt() || gl) {
                    for (int i = 0; i < b5; ++i) {
                        final d5 a = d4.a(i);
                        n2 = Math.min(n2, (int)a.a);
                        n3 = Math.min(n3, (int)a.b);
                        n4 = Math.max(n4, (int)a.a);
                        n5 = Math.max(n5, (int)a.b);
                    }
                    dg.f0();
                    dg.fz();
                }
                else {
                    n2 = (int)this.t.a;
                    n3 = (int)this.t.b;
                    n4 = 0;
                    n5 = 0;
                }
                final int b6 = d4.b();
                final int n6 = ji.annotate.dy.w + 2;
                this.a0 = n6 / 2;
                this.a1 = n6 - 2;
                this.a2 = this.a1 / 2;
                final int fw = dg.fw();
                if (fw < 0) {
                    this.r = false;
                }
                dg.fz();
                if (this.b7) {
                    graphics.setColor(Color.yellow);
                }
                if (!dg.dt() && !gl) {
                    for (int j = 0; j < b6; ++j) {
                        final d5 a2 = d4.a(j);
                        if (this.b7 && this.b7) {
                            if (fw == j && this.aa.en()) {
                                Color color = dg.b(dg, ad.o9);
                                if (color != null) {
                                    if (color.equals(Color.yellow)) {
                                        color = Color.red;
                                    }
                                }
                                else {
                                    color = Color.red;
                                }
                                graphics.setColor(color);
                            }
                            else {
                                graphics.setColor(Color.yellow);
                            }
                            if (b4) {
                                ji.graphic.d6.b(graphics, (int)(a2.a - this.a2), (int)(a2.b - this.a2), this.a1, this.a1);
                            }
                            graphics.setColor(Color.black);
                            if (b4) {
                                ji.graphic.d6.a(this.aa, graphics, (int)(a2.a - 1), (int)a2.b, (int)(a2.a + 1), (int)a2.b);
                            }
                        }
                    }
                }
                if (this.b7) {
                    graphics.setColor(Color.black);
                }
                final int n7 = 1;
                boolean b7 = true;
                if (!dg.dt() && !gl) {
                    for (int k = 0; k < b6; ++k) {
                        final d5 a3 = d4.a(k);
                        if (this.b7 && b4) {
                            for (int l = 0; l < n7; ++l) {
                                ji.graphic.d6.a(graphics, (int)(a3.a - this.a2 + l), (int)(a3.b - this.a2 + l), this.a1 - 2 * l, this.a1 - 2 * l);
                            }
                        }
                        dg.b(a3.a - this.a0, a3.b - this.a0, n6, n6);
                        n2 = Math.min(n2, (int)(a3.a - this.a0));
                        n3 = Math.min(n3, (int)(a3.b - this.a0));
                        n4 = Math.max(n4, (int)(a3.a + this.a0));
                        n5 = Math.max(n5, (int)(a3.b + this.a0));
                    }
                }
                final da bk = dg.bk();
                if (bk != null) {
                    n2 = Math.min(n2, (int)bk.a);
                    n3 = Math.min(n3, (int)bk.b);
                    n4 = Math.max(n4, (int)bk.a);
                    n5 = Math.max(n5, (int)bk.b);
                }
                if (dg.d5() != 9 && dg.d5() != 8 && dg.d5() != 7) {
                    final da fp = dg.fp();
                    if (fp != null) {
                        if (fp.c == 0 || fp.c == 0) {
                            b7 = false;
                        }
                        else {
                            n2 = Math.min(n2, (int)fp.a);
                            n3 = Math.min(n3, (int)fp.b);
                            n4 = Math.max(n4, (int)(fp.a + fp.c));
                            n5 = Math.max(n5, (int)(fp.b + fp.d));
                        }
                    }
                }
                final boolean b8 = !dg.gl();
                if (b && b8) {
                    final int n8 = 2;
                    this.d1 = 2 * this.a2;
                    da = new da(n4 + 1, n5 + 1, this.d1, this.d1);
                    if (this.b7) {
                        if (this.r) {
                            graphics.setColor(Color.white);
                        }
                        else {
                            graphics.setColor(Color.pink);
                        }
                        if (b4) {
                            ji.graphic.d6.b(graphics, (int)da.a, (int)da.b, (int)da.c, (int)da.d);
                        }
                        graphics.setColor(Color.black);
                        if (b4) {
                            ji.graphic.d6.a(graphics, (int)da.a, (int)da.b, (int)da.c, (int)da.d);
                            ji.graphic.d6.a(this.aa, graphics, n4 + 1 + this.a2 - n8, n5 + 1 + this.a2, n4 + 1 + this.a2 + n8, n5 + 1 + this.a2);
                            ji.graphic.d6.a(this.aa, graphics, n4 + 1 + this.a2, n5 + 1 + this.a2 - n8, n4 + 1 + this.a2, n5 + 1 + this.a2 + n8);
                        }
                    }
                }
                dg.bb(b);
                final da da3;
                da da2 = da3 = new da(n2, n3, n4 - n2, n5 - n3);
                ++da3.c;
                final da da4 = da2;
                ++da4.d;
                if (b7) {
                    if (dg.d5() == 7) {
                        if (dg.at() && b4) {
                            this.a(graphics, null, da2, Color.gray, component, ji.annotate.dy.ad);
                        }
                    }
                    else if (b4) {
                        this.a(graphics, null, da2, Color.gray, component, ji.annotate.dy.ad);
                    }
                }
                if (!dg.gl()) {
                    try {
                        if (this.aa != null && dg.dy()) {
                            this.a(component);
                            if (this.bp) {
                                final int m = this.m(dg);
                                this.bd = m * 16;
                                this.be = m * 16;
                                final int n9 = this.bd + 4 + (2 * this.bf - 1) + this.bg * (m - 1);
                                final int n10 = ji.annotate.dy.bb + 4 + (2 * this.bf - 1);
                                final int n11 = this.be + 3 + (2 * this.bf - 1) + this.bg * (m - 1);
                                final int n12 = ji.annotate.dy.ba + 5 + (2 * this.bf - 1);
                                final Rectangle clipBounds = graphics.getClipBounds();
                                int n13 = 0;
                                int n14 = 0;
                                final int n15 = 3;
                                try {
                                    while (n14 == 0) {
                                        switch (n13) {
                                            case 0: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)da2.a;
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 1: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a + da2.c - this.ai);
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 2: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)da2.a;
                                                this.ag = (int)(da2.b + da2.d + n15 + 2 + this.a0);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 3: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a + da2.c - this.ai) - 3;
                                                this.ag = (int)(da2.b + da2.d + n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 4: {
                                                this.ah = n11;
                                                this.ai = n12;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)da2.b;
                                                this.ay = n13;
                                                break;
                                            }
                                            case 5: {
                                                this.ah = n11;
                                                this.ai = n12;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)(da2.b + da2.d - this.ah);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 6: {
                                                this.ah = n11;
                                                this.ai = n12;
                                                this.af = (int)(da2.a + da2.c + n15 + 2 + this.a0);
                                                this.ag = (int)da2.b;
                                                this.ay = n13;
                                                break;
                                            }
                                            case 7: {
                                                this.ah = n11;
                                                this.ai = n12;
                                                this.af = (int)(da2.a + da2.c + n15);
                                                this.ag = (int)(da2.b + da2.d - this.ah) - 3;
                                                this.ay = n13;
                                                break;
                                            }
                                            case 8: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 9: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a + da2.c + n15);
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 10: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)(da2.b + da2.d + n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 11: {
                                                this.ai = n9;
                                                this.ah = n10;
                                                this.af = (int)(da2.a + da2.c + n15 + this.a0) + 2;
                                                this.ag = (int)(da2.b + da2.d + n15 + this.a0) + 2;
                                                this.ay = n13;
                                                break;
                                            }
                                            case 12: {
                                                this.ai = n12;
                                                this.ah = n11;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 13: {
                                                this.ai = n12;
                                                this.ah = n11;
                                                this.af = (int)(da2.a + da2.c + n15);
                                                this.ag = (int)(da2.b - this.ah - n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 14: {
                                                this.ai = n12;
                                                this.ah = n11;
                                                this.af = (int)(da2.a - this.ai - n15);
                                                this.ag = (int)(da2.b + da2.d + n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            case 15: {
                                                this.ai = n12;
                                                this.ah = n11;
                                                this.af = (int)(da2.a + da2.c + n15);
                                                this.ag = (int)(da2.b + da2.d + n15);
                                                this.ay = n13;
                                                break;
                                            }
                                            default: {
                                                try {
                                                    final da b9 = this.l.b(da2);
                                                    this.ai = n9;
                                                    this.ah = n10;
                                                    this.af = (int)b9.a;
                                                    this.ag = (int)b9.b;
                                                    this.ae = new da(this.af, this.ag, this.ai, this.ah);
                                                    n14 = 1;
                                                    this.ay = n13;
                                                }
                                                catch (Exception ex2) {
                                                    this.ai = n9;
                                                    this.ah = n10;
                                                    this.af = (int)da2.a;
                                                    this.ag = (int)(da2.b - this.ah - n15);
                                                    this.ae = new da(this.af, this.ag, this.ai, this.ah);
                                                    n14 = 1;
                                                    this.ay = n13;
                                                }
                                                break;
                                            }
                                        }
                                        if (n14 == 0) {
                                            this.ae = new da(this.af, this.ag, this.ai + 4, this.ah + 4);
                                            final Rectangle intersection = clipBounds.intersection(this.ae.a());
                                            if (intersection.width >= this.ai + 4 && intersection.height >= this.ah + 4) {
                                                this.ae = new da(this.af, this.ag, this.ai, this.ah);
                                                n14 = 1;
                                            }
                                            else {
                                                ++n13;
                                            }
                                        }
                                    }
                                }
                                catch (Exception ex3) {}
                                this.ae = new da(this.af, this.ag, this.ai, this.ah);
                            }
                            this.a(graphics, this.ae, component, this.aa, af, dg);
                        }
                    }
                    catch (Exception ex4) {}
                }
                final da da5 = da2;
                da5.a -= this.a0;
                final da da6 = da2;
                da6.b -= this.a0;
                final da da7 = da2;
                da7.c += 1 + 3 * this.a0;
                final da da8 = da2;
                da8.d += 1 + 3 * this.a0;
                if (this.ae != null) {
                    final int n16 = (int)Math.min(da2.a, this.ae.a);
                    final int n17 = (int)Math.min(da2.b, this.ae.b);
                    final int max = Math.max((int)(da2.a + da2.c), (int)(this.ae.a + this.ae.c));
                    final int max2 = Math.max((int)(da2.b + da2.d), (int)(this.ae.b + this.ae.d));
                    da2.a = n16 - 2;
                    da2.b = n17 - 2;
                    da2.c = max - da2.a + 12;
                    da2.d = max2 - da2.b + 12;
                }
                if (d5 != null) {
                    final Rectangle bounds = d5.getBounds();
                    if (bounds.x < da2.a) {
                        final da da9 = da2;
                        da9.c += da2.a - bounds.x;
                        da2.a = bounds.x;
                    }
                    if (bounds.y < da2.b) {
                        final da da10 = da2;
                        da10.d += da2.b - bounds.y;
                        da2.b = bounds.y;
                    }
                    da2.c = Math.max(da2.c, bounds.width);
                    da2.d = Math.max(da2.d, bounds.height);
                }
                final da da11 = da2;
                --da11.a;
                final da da12 = da2;
                --da12.b;
                final da da13 = da2;
                da13.c += 2;
                final da da14 = da2;
                da14.d += 2;
                final da fp2 = dg.fp();
                if (fp2 != null && fp2.c > 0 && fp2.d > 0) {
                    final d4 d6 = new d4();
                    d6.addPoint((int)fp2.a, (int)fp2.b);
                    d6.addPoint((int)(fp2.a + fp2.c), (int)(fp2.b + fp2.d));
                    d6.addPoint((int)da2.a, (int)da2.b);
                    d6.addPoint((int)(da2.a + da2.c), (int)(da2.b + da2.d));
                    da2 = new da(d6.getBounds());
                }
                dg.j(da2);
            }
            catch (Exception ex5) {}
            finally {
                this.e0 = false;
            }
        }
        if (rectangle != null && graphics != null) {
            try {
                graphics.setClip(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return da;
    }
    
    private final void a(final Graphics graphics, final dg dg, final Component component, final boolean b, final af af, final int d0) {
        if (graphics != null) {
            if ((d0 & 0x4) > 0) {
                this.a(graphics, dg, component, b, af, d0, 4);
            }
            else if (b) {
                if (!dg.bj()) {
                    this.b(graphics, dg, component, b, af, this.d0 = d0);
                    dg.ah(true);
                }
            }
            else if (dg.bj()) {
                this.b(graphics, dg, component, b, af, this.d0);
                dg.ah(false);
            }
        }
    }
    
    private final void b(final Graphics graphics, final dg dg, final Component component, final boolean b, final af af, final int n) {
        this.a(graphics, dg, component, b, af, n, 1);
        this.a(graphics, dg, component, b, af, n, 2);
    }
    
    private final void a(final Graphics graphics, final dg dg, final Component component, final boolean b, final af af, final int n, final int n2) {
        if (dg.bm() && dg.y()) {
            final Graphics create = graphics.create();
            final Color white = Color.white;
            final da fr = dg.fr();
            switch (n & n2) {
                case 1: {
                    final int n3 = 1;
                    final int n4 = 1;
                    final da da = new da(fr.a());
                    da.a = da.a - n4 - n3;
                    da.b = da.b - n4 - n3;
                    da.c += 2 * (n4 + n3);
                    da.d += 2 * (n4 + n3);
                    ji.graphic.d6.a(create);
                    final Color gray = Color.gray;
                    create.setColor(gray);
                    if (!dg.bj()) {
                        this.a(create, null, da, gray, component, ji.annotate.dy.ad);
                        break;
                    }
                    final Rectangle clipBounds = create.getClipBounds();
                    create.setClip(da.a());
                    this.aa.e().paint(create);
                    create.setClip(clipBounds);
                    dg.ae(false);
                    break;
                }
                case 4: {
                    final int n5 = 1;
                    final int n6 = 4;
                    final da da2 = new da(fr.a());
                    da2.a = da2.a - n6 - n5;
                    da2.b = da2.b - n6 - n5;
                    da2.c += 2 * (n6 + n5);
                    da2.d += 2 * (n6 + n5);
                    ji.graphic.d6.a(create);
                    final Color blue = Color.blue;
                    create.setColor(blue);
                    if (dg.bd()) {
                        this.a(create, null, da2, blue, component, 0);
                        break;
                    }
                    final Rectangle clipBounds2 = create.getClipBounds();
                    create.setClip(da2.a());
                    this.aa.e().paint(create);
                    create.setClip(clipBounds2);
                    dg.ae(false);
                    break;
                }
                case 2: {
                    ji.graphic.d6.a(create, Color.black);
                    final int n7 = 12;
                    final int n8 = 4;
                    int n9 = 5;
                    final int n10 = 2 * n8 + 2 * n7 + 4;
                    if (fr.c < n10) {
                        n9 = (int)Math.max(n9, (n10 - fr.c) / 4);
                    }
                    if (fr.d < n10) {
                        n9 = (int)Math.max(n9, (n10 - fr.d) / 4);
                    }
                    final Rectangle rectangle = new Rectangle(fr.a());
                    rectangle.x = rectangle.x - n9 - n8;
                    rectangle.y = rectangle.y - n9 - n8;
                    rectangle.width = 1 + rectangle.width + 2 * (n9 + n8);
                    rectangle.height = 1 + rectangle.height + 2 * (n9 + n8);
                    create.setColor(white);
                    ji.graphic.d6.b(create, rectangle.x, rectangle.y, n7, n8);
                    ji.graphic.d6.b(create, rectangle.x, rectangle.y + rectangle.height - n8, n7, n8);
                    ji.graphic.d6.b(create, rectangle.x + rectangle.width - n7, rectangle.y, n7, n8);
                    ji.graphic.d6.b(create, rectangle.x + rectangle.width - n7, rectangle.y + rectangle.height - n8, n7, n8);
                    ji.graphic.d6.b(create, rectangle.x, rectangle.y + n8, n8, n7 - n8);
                    ji.graphic.d6.b(create, rectangle.x + rectangle.width - n8, rectangle.y + n8, n8, n7 - n8);
                    ji.graphic.d6.b(create, rectangle.x, rectangle.y + rectangle.height - n7, n8, n7 - n8);
                    ji.graphic.d6.b(create, rectangle.x + rectangle.width - n8, rectangle.y + rectangle.height - n7, n8, n7 - n8);
                    ji.graphic.d6.a(create);
                    break;
                }
            }
        }
    }
    
    public final void a(final Graphics graphics, final Component component, final af af, final dg dg) {
        this.a(graphics, this.ae, component, this.aa, af, dg);
    }
    
    private final void a(final Graphics graphics, final da da, final Component component, final cy cy, final af af, final dg dg) {
        if (dg.gl()) {
            return;
        }
        if (!cy.en() && dg.dy() && dg.b(this.e, this.d)) {
            try {
                this.ec = true;
                if (ji.util.d.ls && dg.b(this.g)) {
                    if (dg.ac() > 0) {
                        if (this.e8 == null) {
                            this.e8 = new vn(graphics, da, component, cy, af, dg);
                            new bb(this.ed, this.e8).start();
                            return;
                        }
                        this.e8.a(dg, da);
                        return;
                    }
                }
                else if (dg.aa() > 0) {
                    if (this.e8 == null) {
                        this.e8 = new vn(graphics, da, component, cy, af, dg);
                        new bb(this.ed, this.e8).start();
                        return;
                    }
                    this.e8.a(dg, da);
                    return;
                }
                this.co = new da(da);
                if (this.cp != null) {
                    return;
                }
                if (this.ck) {
                    if (this.cc) {
                        (this.cp = new bb(this.ed, new vo(graphics, component, cy, af))).start();
                    }
                }
                else if (this.e8 == null) {
                    this.e8 = new vn(graphics, da, component, cy, af, dg);
                    new bb(this.ed, this.e8).start();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void j() {
        this.ey = false;
    }
    
    public final boolean k() {
        return this.ey;
    }
    
    private final void a(final Graphics graphics, final cy cy, final af af) {
        if (!this.aa.en() && this.aa.ci() == 0 && !this.e1) {
            try {
                this.e1 = true;
                da da = new da(this.co);
                int i = 1;
                if (this.cc) {
                    final dg b3 = this.aa.b3();
                    while (i != 0) {
                        i = 0;
                        this.a(da, cy);
                        final Color background = cy.getBackground();
                        this.ey = true;
                        if (this.b7) {
                            graphics.setColor(background);
                            ji.graphic.d6.b(graphics, (int)da.a + 1, (int)da.b + 1, (int)da.c, (int)da.d);
                            graphics.setColor(background.darker());
                            ji.graphic.d6.a(graphics, (int)da.a, (int)da.b, (int)da.c, (int)da.d);
                        }
                        for (int j = 0; j < this.bc; ++j) {
                            final xf xf = (xf)this.a9.b(j);
                            if (xf.g() == 2) {
                                this.a(xf, graphics, cy, af, true, b3);
                            }
                            else {
                                this.a(xf, graphics, cy, af, false, b3);
                            }
                            if (this.co != null && (da.a != this.co.a || da.b != this.co.b || da.c != this.co.c || da.d != this.co.d)) {
                                da = new da(this.co);
                                this.a(da, cy);
                                i = 1;
                                break;
                            }
                        }
                    }
                    if (b3 != null) {
                        b3.o(true);
                        if (!b3.w() && ji.util.d.ls && b3.b(this.g)) {
                            final d4 u = b3.u();
                            if (u != null) {
                                this.a(graphics, b3, u, cy, cy.a(), ji.annotate.b8.a(1, b3, this.u, this.ak, this.a4), b3.v(), false, af, true, false);
                            }
                            if (b3.e9() && b3.n() != null && b3.o() >= 0 && b3.w()) {
                                graphics.setColor(b3.n());
                                final int o = b3.o();
                                final int p3 = b3.p();
                                final int q = b3.q();
                                final int r = b3.r();
                                int n = 0;
                                if (b3.at()) {
                                    n = 5;
                                }
                                if (!b3.at() && ji.util.d.ls) {
                                    ji.graphic.d6.a(this.aa, graphics, o + n + 1, q, p3 - n - 1, q);
                                    ji.graphic.d6.a(this.aa, graphics, o + 1, q + n + 1, o + 1, r - n - 1);
                                    ji.graphic.d6.a(this.aa, graphics, p3 - 1, q + n + 1, p3 - 1, r - n - 1);
                                    ji.graphic.d6.a(this.aa, graphics, o + n + 1, r - 1, p3 - n - 2, r - 1);
                                }
                                else {
                                    ji.graphic.d6.a(this.aa, graphics, o + n, q, p3 - n, q);
                                    ji.graphic.d6.a(this.aa, graphics, o, q + n, o, r - n);
                                    ji.graphic.d6.a(this.aa, graphics, p3, q + n, p3, r - n);
                                    ji.graphic.d6.a(this.aa, graphics, o + n, r, p3 - n, r);
                                }
                                b3.n(true);
                            }
                            final d4 s = b3.s();
                            if (s != null && this.b != null) {
                                this.b.a(s, graphics, ji.annotate.dg.b(b3, cy.a().o9), b3.t(), ji.util.d.ls && b3.b(this.g), this.l, this, b3);
                            }
                        }
                    }
                }
            }
            finally {
                this.e1 = false;
            }
        }
    }
    
    public void e(final boolean bp) {
        this.bp = bp;
    }
    
    private final void a(final xf xf, final Graphics graphics, final Component component, final af af, final boolean b, final dg dg) {
        if (xf.c()) {
            if (dg != null && !this.ey) {
                return;
            }
            this.bh.a(xf.g());
            final da f = xf.f();
            if (this.b7) {
                graphics.setColor(component.getBackground());
                ji.graphic.d6.b(graphics, (int)f.a, (int)f.b, (int)f.c, (int)f.d);
            }
            try {
                int n = 2;
                if (xf.g() == 1) {
                    ++n;
                }
                if (this.b7) {
                    Image image;
                    if (ji.util.d.a8()) {
                        if (b && xf.s) {
                            graphics.setColor(ji.util.e.a0());
                            image = xf.a(af, ji.util.e.a0());
                            if (ji.util.i.c(7) && ji.util.e.t() && ji.util.d.ar()) {
                                final Rectangle a;
                                final Rectangle rectangle = a = f.a();
                                a.width -= 2;
                                final Rectangle rectangle2 = rectangle;
                                --rectangle2.height;
                                ji.util.d.a(graphics, rectangle, ji.util.e.a0(), ji.util.e.a3(), true);
                            }
                            else {
                                ji.graphic.d6.b(graphics, (int)f.a, (int)f.b, (int)f.c, (int)f.d);
                            }
                        }
                        else {
                            graphics.setColor(ji.util.e.ao());
                            image = xf.a(af);
                            ji.graphic.d6.b(graphics, (int)f.a, (int)f.b, (int)f.c, (int)f.d);
                        }
                    }
                    else {
                        image = xf.a(af);
                    }
                    if (image != null) {
                        if (xf.g() == 1) {
                            ji.graphic.d6.a(graphics, image, (int)(f.a + 1 + n), (int)(f.b + n), null);
                        }
                        else {
                            ji.graphic.d6.a(graphics, image, (int)(f.a + 1 + n), (int)(f.b + n), null);
                        }
                    }
                }
            }
            catch (Exception ex) {}
            this.bi.setBounds(f.a());
            this.bh.a((int)f.a, (int)f.b);
            if (b && xf.s) {
                Color color = this.bi.e();
                if (color == null) {
                    if (ji.util.e.h != null) {
                        color = ji.util.e.h;
                    }
                    else {
                        color = SystemColor.controlLtHighlight;
                    }
                }
                Color color2 = this.bi.d();
                if (color2 == null) {
                    if (ji.util.e.g != null) {
                        color2 = ji.util.e.g;
                    }
                    else {
                        color2 = SystemColor.controlShadow;
                    }
                }
                final int n2 = (int)f.a;
                final int n3 = (int)f.b;
                final int n4 = (int)(f.a + f.c - 1);
                final int n5 = (int)(f.b + f.d - 1);
                if (xf.g() == 1) {
                    graphics.setColor(color2);
                }
                else {
                    graphics.setColor(color);
                }
                ji.graphic.d6.a(this.aa, graphics, n2, n3, n2, n5);
                ji.graphic.d6.a(this.aa, graphics, n2, n3, n4, n3);
                if (xf.g() == 1) {
                    graphics.setColor(color);
                }
                else {
                    graphics.setColor(color2);
                }
                ji.graphic.d6.a(this.aa, graphics, n4, n3, n4, n5);
                ji.graphic.d6.a(this.aa, graphics, n2, n5, n4, n5);
            }
        }
    }
    
    private final void a(final da da, final cy cy) {
        if (this.bp) {
            this.bp = false;
            long n = da.a + this.bf;
            long n2 = da.b + this.bf;
            long max = 0L;
            for (int i = 0; i < this.bc; ++i) {
                final xf xf = (xf)this.a9.b(i);
                if (xf.c()) {
                    final dc a = xf.a((jiPanel)cy);
                    final da da2 = new da(n + 1, n2 + 1, a.a + 3, a.b + 2);
                    max = Math.max(max, a.b);
                    n += a.a + 1 + this.bg;
                    if (n > da.a + da.c - 4) {
                        n = da.a + this.bf;
                        n2 += max + 3;
                        max = 0L;
                    }
                    xf.a(da2);
                }
            }
        }
    }
    
    public final Rectangle l() {
        if (this.ae != null) {
            return this.ae.a();
        }
        return null;
    }
    
    public final da m() {
        return this.ae;
    }
    
    public final int n() {
        return this.ay;
    }
    
    public final boolean a(final cy cy) {
        return false;
    }
    
    public final void o() {
        this.a3 = false;
        this.bj = -1;
        this.r = false;
        this.ad();
        this.bw = -1;
        this.bx = -1;
    }
    
    private void a(final Graphics graphics, final dt dt, final long n, final long n2, final long n3, final long n4, final Color color, final Component component, final int n5) {
        if (graphics != null) {
            if (this.b7) {
                graphics.setColor(color);
            }
        }
        else {
            dt.a(color);
        }
        if (n5 > 0) {
            final int max = Math.max((int)((n3 - n) / (ji.annotate.dy.ac + n5)), (int)((n4 - n2) / (ji.annotate.dy.ac + n5)));
            final double n6 = (n3 - n) / max;
            final double max2 = Math.max(n6 - n5, 0.0);
            final double n7 = (n4 - n2) / max;
            final double max3 = Math.max(n7 - n5, 0.0);
            double n8 = n;
            double n9 = n2;
            if (graphics != null) {
                for (int i = 0; i < max; ++i) {
                    if (this.b7) {
                        ji.graphic.d6.a(this.aa, graphics, (int)n8, (int)n9, (int)(n8 + max2), (int)(n9 + max3));
                    }
                    n8 += n6;
                    n9 += n7;
                }
                if (this.b7) {
                    ji.graphic.d6.a(this.aa, graphics, (int)(n3 - max2), (int)n2, (int)n3, (int)n2);
                    ji.graphic.d6.a(this.aa, graphics, (int)n3, (int)(n4 - max3), (int)n3, (int)n4);
                }
            }
            else {
                for (int j = 0; j < max; ++j) {
                    this.a(dt, (int)n8, (int)n9, (int)(n8 + max2), (int)(n9 + max3), component);
                    n8 += n6;
                    n9 += n7;
                }
            }
        }
        else if (graphics != null) {
            ji.graphic.d6.a(this.aa, graphics, (int)n, (int)n2, (int)n3, (int)n4);
        }
        else {
            this.a(dt, (int)n, (int)n2, (int)n3, (int)n4, component);
        }
    }
    
    private void a(final Graphics graphics, final dt dt, final da da, Color gray, final Component component, final int n) {
        if (gray == null) {
            gray = Color.gray;
        }
        if (n > 0) {
            this.a(graphics, dt, da.a, da.b, da.a + da.c, da.b, gray, component, n);
            this.a(graphics, dt, da.a + da.c, da.b, da.a + da.c, da.b + da.d, gray, component, n);
            this.a(graphics, dt, da.a, da.b + da.d, da.a + da.c, da.b + da.d, gray, component, n);
            this.a(graphics, dt, da.a, da.b, da.a, da.b + da.d, gray, component, n);
        }
        else {
            this.a(graphics, dt, da.a, da.b, da.a + da.c, da.b, gray, component, n);
            this.a(graphics, dt, da.a + da.c, da.b + 1, da.a + da.c, da.b + da.d, gray, component, n);
            this.a(graphics, dt, da.a, da.b + da.d, da.a + da.c - 1, da.b + da.d, gray, component, n);
            this.a(graphics, dt, da.a, da.b + 1, da.a, da.b + da.d, gray, component, n);
        }
    }
    
    public void b(final dg dg) {
        try {
            this.aa.h(dg);
        }
        catch (Exception ex) {}
    }
    
    private final int a(final dx dx, final int n, final int n2) {
        if ((n2 & 0x1) > 0) {
            return dx.m - n;
        }
        return n;
    }
    
    private final int b(final dx dx, final int n, final int n2) {
        if ((n2 & 0x2) > 0) {
            return dx.n - n;
        }
        return n;
    }
    
    public dg a(final dx dx, final int n, final int x, final int n2, final int n3, Color color, Color t, int max, final ad ad, final double n4, String cc, final int n5, ct v, final int n6, int w, final df df, final af af, String q, String s, String r, final gj gj) throws Exception {
        if (!this.a(ad)) {
            ji.io.h.a(this.ed, "Adding an annotation has been disabled");
            return null;
        }
        if (gj != null) {
            color = gj.u;
        }
        if (gj != null) {
            t = gj.t;
        }
        if (gj != null) {
            v = gj.v;
        }
        if (gj != null) {
            w = gj.w;
        }
        final int a = this.a(dx, n2, n);
        final int b = this.b(dx, n3, n);
        boolean aa = false;
        boolean ab = false;
        boolean ac = false;
        if (gj != null) {
            aa = gj.aa;
        }
        if (gj != null) {
            ab = gj.ab;
        }
        if (gj != null) {
            ac = gj.ac;
        }
        double ap = 1.0;
        if (gj != null) {
            ap = gj.ap;
        }
        this.n = false;
        int n7 = ji.util.d.w();
        dg dg = null;
        max = Math.max(max, 1);
        this.q();
        this.x = x;
        this.z = -1;
        this.a5 = true;
        this.bs = false;
        boolean b2 = false;
        switch (x) {
            case 4: {
                int max2;
                if (!ji.util.d.bf()) {
                    max2 = Math.max((int)(n7 * 0.45 / n4), n7);
                }
                else {
                    max2 = (int)(n7 * dx.ac / 200.0);
                }
                if (aa) {
                    max2 = 1;
                }
                dg = new dg(df.a(this.c(554)), ji.util.d.cm(), Math.max(max2, 1), this.ed, this.eq, ad.hc());
                dg.a(4, a, b, a, b);
                dg.a(ad);
                if (aa) {
                    dg.bc(true);
                }
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 5: {
                int max3;
                if (!ji.util.d.bf()) {
                    max3 = Math.max((int)(n7 * 0.7 / n4), n7);
                }
                else {
                    max3 = (int)(n7 * dx.ac / 200.0);
                }
                dg = new dg(df.a(this.c(555)), ji.util.d.cm(), Math.max(max3, 1), this.ed, this.eq, ad.hc());
                dg.a(5, a, b, a, b);
                dg.a(ad);
                if (color != null) {
                    dg.c(color);
                    dg.d(color);
                    break;
                }
                break;
            }
            case 7: {
                dg = new dg(df.a(this.c(556)), ji.util.d.cn(), ji.util.d.w(), this.ed, this.eq, ad.hc());
                dg.a(dx.ac, dx.ad, dx.a);
                dg.a(ad);
                dg.w(true);
                dg.a(true);
                dg.a(v);
                if (ji.util.i.c(247)) {
                    int n8 = ji.font.j.aa();
                    if (ji.util.d.bf() && ji.util.i.c(247)) {
                        int n9 = (int)Math.round(dx.ac * n8 / 73.0);
                        if (n4 > 0.0) {
                            n9 /= (int)(2.0 * n4);
                        }
                        n8 = Math.max(n9, 12);
                    }
                    v.i = new Font(ji.font.j.g(), ji.font.j.i(), n8);
                    v.c = v.i.getFamily();
                    v.a = v.i.getSize();
                    v.b = v.i.getSize();
                    v.d = v.i.isBold();
                    v.e = v.i.isItalic();
                    v.f = v.i.isPlain();
                    dg.u(v.b);
                    dg.a(v);
                }
                else {
                    dg.u(n6);
                }
                dg.a(this.e, 7, 0, " ", a, b, this.eu);
                if (ji.util.i.c(247)) {
                    if (dx.c()) {
                        dg.c(1.0 * dx.d());
                    }
                    else {
                        dg.c(1.0);
                    }
                }
                else {
                    dg.a(ad, this.ed, af, dx.c() ? dx.d() : 1);
                }
                dg.k(w);
                if (ji.util.d.bf()) {
                    dg.au(true);
                }
                if (color != null) {
                    dg.c(color);
                }
                if (t == null) {
                    dg.ax(false);
                }
                if (ad.iv() != 0 || ad.hw() != 0) {
                    dg.x(false);
                    break;
                }
                break;
            }
            case 9: {
                if (color == null) {
                    color = ji.util.d.cn();
                }
                cc = ji.util.d.cc(cc);
                dg = new dg(df.a(this.c(557)), color, 0, this.ed, this.eq, ad.hc());
                dg.a(dx.ac, dx.ad, dx.a);
                dg.a(ad);
                this.a(ad, dg, cc);
                if (!dg.e9()) {
                    dg.a(this.e, 9, 0, cc, a, b, this.eu);
                    if (color != null) {
                        dg.c(color);
                    }
                    dg.b(ap);
                    dg.ao(ji.util.i.c(130));
                    break;
                }
                dg.a(v);
                if (ji.util.i.c(247)) {
                    int n10 = ji.font.j.aa();
                    if (ji.util.d.bf() && ji.util.i.c(247)) {
                        int n11 = (int)Math.round(dx.ac * n10 / 73.0);
                        if (n4 > 0.0) {
                            n11 /= (int)(2.0 * n4);
                        }
                        n10 = Math.max(n11, 12);
                    }
                    v.i = new Font(ji.font.j.g(), ji.font.j.i(), n10);
                    v.c = v.i.getFamily();
                    v.a = v.i.getSize();
                    v.b = v.i.getSize();
                    v.d = v.i.isBold();
                    v.e = v.i.isItalic();
                    v.f = v.i.isPlain();
                    dg.u(v.b);
                    dg.a(v);
                }
                else {
                    dg.u(n6);
                }
                dg.a(this.e, 9, 0, cc, a, b, this.eu);
                if (color != null) {
                    dg.c(color);
                }
                if (ji.util.i.c(247)) {
                    if (dx.c()) {
                        dg.c(1.0 * dx.d());
                    }
                    else {
                        dg.c(1.0);
                    }
                }
                else {
                    dg.a(ad, this.ed, af, dx.c() ? dx.d() : 1);
                }
                if (ji.util.i.c(113)) {
                    boolean eu = this.eu;
                    if (eu && (ad.iv() != 0 || ad.hw() != 0)) {
                        eu = false;
                    }
                    dg.am();
                    dg.x(eu);
                }
                dg.k(w);
                if (ji.util.d.bf()) {
                    dg.au(true);
                }
                if (t == null) {
                    dg.ax(false);
                    break;
                }
                break;
            }
            case 1: {
                if (!ji.util.d.bf()) {
                    if (t == null) {
                        n7 = Math.max((int)(n7 * 0.45 / n4), n7);
                    }
                }
                else {
                    n7 = (int)(n7 * dx.ac / 200.0);
                }
                dg = new dg(df.a(this.c(558)), ji.util.d.cm(), Math.max(n7, 1), this.ed, this.eq, ad.hc());
                dg.a(1, a, b, 0L, 0L);
                dg.a(ad);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 8: {
                b2 = true;
                this.b2 = 0.4 / n4;
                dg = new dg(df.a(this.c(559)), ji.util.d.cm(), 1, this.ed, this.eq, ad.hc());
                dg.a(8, "", ji.util.d.cl(), ji.util.d.bl());
                dg.j(false);
                dg.a(ad);
                break;
            }
            case 11: {
                dg = new dg(df.a(this.c(560)), ji.util.d.cm(), 0, this.ed, this.eq, ad.hc());
                dg.a(11, a, b, 0L, 0L);
                dg.a(ad);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 12: {
                dg = new dg(df.a(this.c(561)), ji.util.d.cm(), 1, this.ed, this.eq, ad.hc());
                dg.a(ad);
                final Polygon polygon = new Polygon();
                polygon.addPoint(a, b);
                dg.a(12, polygon);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 13: {
                String s2;
                if (n5 == 1) {
                    s2 = df.a(this.c(563));
                }
                else {
                    s2 = df.a(this.c(562));
                }
                dg = new dg(s2, ji.util.d.cm(), 0, this.ed, this.eq, ad.hc());
                dg.a(13, a, b, 0L, 0L);
                dg.au(true);
                if (color != null) {
                    dg.c(color);
                }
                if (n5 == 1) {
                    dg.d((Color)null);
                    t = null;
                    break;
                }
                break;
            }
            case 14: {
                dg = new dg(df.a(this.c(564)), ji.util.d.cm(), 1, this.ed, this.eq, ad.hc());
                dg.a(ad);
                final Polygon polygon2 = new Polygon();
                polygon2.addPoint(a, b);
                dg.a(14, polygon2);
                dg.au(true);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 2: {
                if (!ji.util.d.bf()) {
                    if (t == null) {
                        n7 = Math.max((int)(n7 * 0.45 / n4), n7);
                    }
                }
                else {
                    n7 = (int)(n7 * dx.ac / 200.0);
                }
                dg = new dg(df.a(this.c(565)), ji.util.d.cm(), Math.max(n7, 1), this.ed, this.eq, ad.hc());
                dg.a(ad);
                dg.a(2, a, b, 1L, 1L);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 3: {
                if (!ji.util.d.bf()) {
                    if (t == null) {
                        n7 = Math.max((int)(n7 * 0.45 / n4), n7);
                    }
                }
                else {
                    n7 = (int)(n7 * dx.ac / 200.0);
                }
                dg = new dg(df.a(this.c(566)), ji.util.d.cm(), Math.max(n7, 1), this.ed, this.eq, ad.hc());
                dg.a(ad);
                final Polygon polygon3 = new Polygon();
                polygon3.addPoint(a, b);
                dg.a(3, polygon3);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 6: {
                int max4;
                if (!ji.util.d.bf()) {
                    max4 = Math.max((int)(n7 * 0.45 / n4), n7);
                }
                else {
                    max4 = (int)(n7 * dx.ac / 200.0);
                }
                if (ab) {
                    max4 = 1;
                }
                dg = new dg(df.a(this.c(567)), ji.util.d.cm(), Math.max(max4, 1), this.ed, this.eq, ad.hc());
                dg.a(ad);
                final Polygon polygon4 = new Polygon();
                polygon4.addPoint(a, b);
                dg.a(6, polygon4);
                if (ab) {
                    dg.bd(true);
                }
                if (ac) {
                    dg.be(true);
                }
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
            case 10: {
                this.bs = true;
                int max5;
                if (!ji.util.d.bf()) {
                    max5 = Math.max((int)(n7 * 0.45 / n4), n7);
                }
                else {
                    max5 = (int)(n7 * dx.ac / 200.0);
                }
                dg = new dg(df.a(this.c(568)), ji.util.d.cm(), Math.max(max5, 1), this.ed, this.eq, ad.hc());
                dg.a(ad);
                final Polygon polygon5 = new Polygon();
                polygon5.addPoint(a, b);
                dg.a(10, polygon5);
                if (color != null) {
                    dg.c(color);
                    break;
                }
                break;
            }
        }
        if (dg != null) {
            dg.c(true);
            try {
                this.a(dg, ad.d(), ad, true);
            }
            catch (Exception ex) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, ex.getMessage());
                }
            }
            if (gj != null) {
                dg.u(gj.z);
            }
            if (gj != null && gj.an != null) {
                dg.q(gj.an);
            }
            if (gj != null && gj.ao != null) {
                dg.m(gj.ao);
            }
            if (ji.util.d.bf() && ad.af()) {
                dg.r(dg.du());
                this.a(dg, String.valueOf(String.valueOf(new StringBuffer("<type> ").append(Integer.toString(max)).append('-').append(dg.dw()))), ad);
            }
            dh dh = dg.bp();
            if (ji.util.d.bf() && ad.af()) {
                dh = this.a(dg, dh, ad);
            }
            else {
                if (ad.x() != null) {
                    dh = this.b(dg, dh, ad);
                }
                dh.e(this.e);
            }
            if (ji.util.d.al(this.eq) && b2 && ad.y() != -1) {
                dh.c(ad.y());
            }
            dg.d(2);
            dg.j(max);
            dg.ai(true);
            dg.a(new Dimension(dx.m, dx.n), dx.ac, dx.ad, dx.a);
            dg.a(dx.j().a(), max);
            if (t != null) {
                if (dg.d5() == 7) {
                    dg.ba(true);
                    dg.d(t);
                    dg.au(false);
                }
                else if (dg.d5() == 1) {
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() == 11) {
                    if (color == null) {
                        dg.c(t);
                    }
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() == 12) {
                    if (color == null) {
                        dg.c(t);
                    }
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() == 13) {
                    if (n5 != 1) {
                        if (color == null) {
                            dg.c(t);
                        }
                        dg.ba(true);
                        dg.d(t);
                    }
                }
                else if (dg.d5() == 14) {
                    if (n5 != 1) {
                        if (color == null) {
                            dg.c(t);
                        }
                        dg.ba(true);
                        dg.d(t);
                    }
                }
                else if (dg.d5() == 3) {
                    if (color == null) {
                        dg.c(t);
                    }
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() == 6) {
                    if (color == null) {
                        dg.c(t);
                    }
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() == 10) {
                    if (color == null) {
                        dg.c(t);
                    }
                    dg.ba(true);
                    dg.d(t);
                }
                else if (dg.d5() != 5) {
                    dg.c(t);
                    dg.ba(true);
                    dg.d(t);
                }
            }
            dg.bp().a(true);
            if (gj != null) {
                q = gj.q;
            }
            if (gj != null) {
                s = gj.s;
            }
            if (gj != null) {
                r = gj.r;
            }
            if (q != null) {
                dg.bp().b(ji.util.d.cf(q));
            }
            if (s != null) {
                dg.bp().c(ji.util.d.cf(s));
            }
            if (r != null) {
                dg.bp().d(ji.util.d.cf(r));
            }
            if (gj != null) {
                dg.bp().b(gj.ad);
            }
            if (gj != null) {
                dg.bp().f(gj.af);
            }
            if (gj != null) {
                dg.bp().e(gj.ag);
            }
            if (gj != null) {
                dg.bp().h(gj.ah);
            }
            if (gj != null) {
                dg.bp().c(gj.ae);
            }
            if (gj != null) {
                dg.bp().d(gj.ai);
            }
            if (gj != null && gj.y != null) {
                dg.bp().e(gj.y);
            }
            dg.bp().a(false);
            dg.b(true, this.e, this.d);
            dg.aj(true);
            this.y = dg.d0();
            dg.a(ji.util.d.e2(), this.e, true);
            dg.o(null);
            dg.am(false);
            if (ji.util.i.e(0) != null) {
                a(dg, ji.util.i.e(0), null, this.e, ad);
            }
        }
        dg.an(true);
        this.aa.a(new d7(this.aa, 19, this.x));
        return dg;
    }
    
    private dh a(final dg dg, final dh dh, final ad ad) {
        if (this.eq == 0) {
            final ei ei = new ei(ji.secure.ei.b, ji.secure.ei.c, 0, 2);
            final ei ei2 = new ei(ji.secure.ei.d, ji.secure.ei.e, 1, 2);
            final ei ei3 = new ei(ji.secure.ei.f, ji.secure.ei.g, 2, 2);
            dh.a(ei);
            dh.a(ei2);
            dh.a(ei3);
            final ei ei4 = new ei(ji.secure.ei.a, 2, 99, 0);
            dh.a(ei4);
            dh.f(ei4.h);
        }
        else if (ji.util.d.am(this.eq)) {
            dh.a(true, ji.secure.ei.n, ji.secure.ei.o);
            final eq a = this.a(this.ed, ad);
            if (a != null) {
                final String ag = dg.ag();
                final String v = ad.v();
                String a2 = null;
                try {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Calling P8 Security dialog for new annotation - docID=").append(ag).append(" annotationID=null requestURL=").append(v).append(" show:false"))));
                    }
                    a2 = a.a(ag, null, v, false);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "XML returned from security dialog:".concat(String.valueOf(String.valueOf(a2))));
                    }
                }
                catch (Exception ex) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "Failed during call to Filenet Ce Security library: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                        ex.printStackTrace();
                    }
                }
                if (a2 != null) {
                    dg.bp().a(new ei(a2, 0, 0));
                }
                try {
                    a.a();
                }
                catch (Exception ex2) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "Failed during call to clean up Ce Security library");
                    }
                }
            }
        }
        else {
            final ei[] ax = ji.util.d.ax();
            if (ax != null) {
                for (int i = 0; i < ax.length; ++i) {
                    ax[i].k = 2;
                    if (ji.util.d.by(ax[i].m)) {
                        ax[i].m = ax[i].h;
                    }
                    dh.a(ax[i]);
                }
            }
        }
        final ei ei5 = new ei(ji.secure.ei.a, 2, 99, 0);
        dh.a(ei5);
        dh.f(ei5.h);
        return dh;
    }
    
    private dh b(final dg dg, final dh dh, final ad ad) {
        final gk b = this.b(this.ed, ad);
        if (b != null) {
            final String be = ad.be(dg.i(-1));
            final String w = ad.w();
            String a = null;
            try {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Calling Custom Security dialog for new annotation - docID=").append(be).append(" annotationID=null requestURL=").append(w).append(" show:false"))));
                }
                a = b.a(be, dg.du(), w, false);
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Data returned from security dialog:".concat(String.valueOf(String.valueOf(a))));
                }
            }
            catch (Exception ex) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Failed during call to Custom Security library: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                    ex.printStackTrace();
                }
            }
            if (a != null) {
                dg.bp().a(new ei(a, 1, 0));
            }
            try {
                b.a();
            }
            catch (Exception ex2) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Failed during call to clean up Custom Security library");
                }
            }
        }
        return dh;
    }
    
    private final void a(final dg dg, final String s, final ad ad, final boolean b) throws Exception {
        this.a(dg, s, ad, null, b);
    }
    
    private final void a(final dg dg, final String s, final ad ad, final String s2, final boolean b) throws Exception {
        String s3 = null;
        boolean b2 = false;
        try {
            if (dg != null && s != null) {
                dg.d(!ji.util.i.c(127));
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "}");
                while (stringTokenizer.hasMoreElements()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf("{");
                    if (index == -1) {
                        s3 = this.c(1173);
                        break;
                    }
                    final String substring = nextToken.substring(0, index);
                    final String substring2 = nextToken.substring(index + 1, nextToken.length());
                    final String trim = substring.trim();
                    if (ji.util.d.by(trim)) {
                        continue;
                    }
                    if (dg.h(trim) || trim.equals("all")) {
                        ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("annotationDefaults specified type '").append(trim).append("' is matched."))));
                        final StringTokenizer stringTokenizer2 = new StringTokenizer(substring2.trim(), ";");
                        while (stringTokenizer2.hasMoreElements()) {
                            final String nextToken2 = stringTokenizer2.nextToken();
                            final int index2 = nextToken2.indexOf(61);
                            if (index2 == -1) {
                                s3 = this.c(1173);
                                break;
                            }
                            final String trim2 = nextToken2.substring(0, index2).trim();
                            final String trim3 = nextToken2.substring(index2 + 1, nextToken2.length()).trim();
                            if (ji.util.d.by(trim2) || ji.util.d.by(trim3)) {
                                continue;
                            }
                            if ((s2 == null || s2.equalsIgnoreCase(trim2)) && trim2.equalsIgnoreCase("burnable")) {
                                ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("annotationDefaults specified attribute '").append(trim2).append("' is matched."))));
                                if (trim3.equalsIgnoreCase("true")) {
                                    dg.f(true);
                                    b2 = true;
                                    ji.io.h.a(this.ed, "annotationDefaults setting burnable=true");
                                }
                                else if (trim3.equalsIgnoreCase("false")) {
                                    dg.f(false);
                                    b2 = true;
                                    ji.io.h.a(this.ed, "annotationDefaults setting burnable=false");
                                }
                                else {
                                    s3 = String.valueOf(String.valueOf(new StringBuffer("annotationDefaults, ").append(trim2).append("=").append(trim3).append(": ").append(this.c(1172))));
                                    ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("annotationDefaults: attribute value '").append(trim3).append("' is unknown for attribute '").append(trim2).append("'"))));
                                }
                            }
                            if ((s2 == null || s2.equalsIgnoreCase(trim2)) && trim2.equalsIgnoreCase("init")) {
                                boolean b3 = false;
                                if (ad.hc() == 2) {
                                    final dh bp = dg.bp();
                                    if (bp != null) {
                                        if (trim3.length() >= 13 && ((trim3.startsWith("\"") && trim3.endsWith("\"")) || (trim3.startsWith("'") && trim3.endsWith("'")))) {
                                            b2 = true;
                                            final String substring3 = trim3.substring(1, trim3.length() - 1);
                                            if (!ji.util.d.by(substring3) && substring3.indexOf(",") > -1) {
                                                final StringTokenizer stringTokenizer3 = new StringTokenizer(substring3, ",");
                                                for (int n = 0; stringTokenizer3.hasMoreElements() && n < 6; ++n) {
                                                    final String nextToken3 = stringTokenizer3.nextToken();
                                                    if (ji.util.d.by(nextToken3)) {
                                                        b3 = true;
                                                        ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                                        break;
                                                    }
                                                    final String trim4 = nextToken3.trim();
                                                    boolean b4 = false;
                                                    Label_0788: {
                                                        try {
                                                            final int int1 = Integer.parseInt(trim4);
                                                            if (int1 == 0) {
                                                                b4 = false;
                                                                break Label_0788;
                                                            }
                                                            if (int1 == 1) {
                                                                b4 = true;
                                                                break Label_0788;
                                                            }
                                                            b3 = true;
                                                            ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                                        }
                                                        catch (NumberFormatException ex2) {
                                                            b3 = true;
                                                            ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                                        }
                                                        break;
                                                    }
                                                    switch (n) {
                                                        case 0: {
                                                            bp.b(b4);
                                                            break;
                                                        }
                                                        case 1: {
                                                            bp.c(b4);
                                                            break;
                                                        }
                                                        case 2: {
                                                            bp.e(b4);
                                                            break;
                                                        }
                                                        case 3: {
                                                            bp.f(b4);
                                                            break;
                                                        }
                                                        case 4: {
                                                            bp.h(b4);
                                                            break;
                                                        }
                                                        case 5: {
                                                            bp.d(b4);
                                                            break;
                                                        }
                                                    }
                                                }
                                                if (stringTokenizer3.hasMoreElements()) {
                                                    b3 = true;
                                                    ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                                }
                                            }
                                            else {
                                                b3 = true;
                                                ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                            }
                                        }
                                        else {
                                            b3 = true;
                                            ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                        }
                                    }
                                }
                                else {
                                    b3 = true;
                                    ji.io.h.a(this.ed, "annotationDefaults: invalid value for init parameter");
                                }
                                if (!b3) {
                                    continue;
                                }
                                s3 = this.c(1193);
                            }
                            else {
                                ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("annotationDefaults unrecognised attribute name '").append(trim2).append("'"))));
                            }
                        }
                        if (s3 != null) {
                            break;
                        }
                        continue;
                    }
                    else {
                        b2 = true;
                        ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("annotationDefaults specified type '").append(trim).append("' did not match annotation item."))));
                    }
                }
            }
            if (s3 == null && !b2 && ad != null && ad.ga() != null && !ad.bi(21)) {
                s3 = this.c(1174);
                ji.io.h.a(this.ed, "annotationDefaults: Empty property value");
            }
            if (s3 != null) {
                try {
                    s3 = ji.util.d.f(s3, 70);
                }
                catch (Exception ex3) {}
                throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append("\n").append(this.c(1175)).append(" 'annotationDefaults'"))));
            }
        }
        catch (Exception ex) {
            if (b) {
                ji.util.d.a(ex, this.c(569), ad, 120, null, this.ed);
            }
            else {
                ji.util.d.a(ex);
            }
            throw ex;
        }
    }
    
    private final eq a(final String s, final ad ad) {
        eq eq = null;
        String x = ad.x();
        if (ji.util.d.by(x)) {
            x = "com.filenet.wcm.apps.client.securityDlg.WcmSecurityInterface";
        }
        try {
            if (ji.util.d.cs()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Loading ").append(x).append("..."))));
            }
            eq = new eq(Class.forName(x), s);
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Failed to load Filenet CE Security library: ".concat(String.valueOf(String.valueOf(x))));
            ji.io.h.a(s, ex);
        }
        return eq;
    }
    
    private final gk b(final String s, final ad ad) {
        gk gk = null;
        final String x = ad.x();
        if (ji.util.d.by(x)) {
            return null;
        }
        try {
            if (ji.util.d.cs()) {
                ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Loading ").append(x).append("..."))));
            }
            gk = new gk(Class.forName(x), s);
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Failed to load Custom Security library: ".concat(String.valueOf(String.valueOf(x))));
            ji.io.h.a(s, ex);
        }
        return gk;
    }
    
    private final void ag() {
        try {
            this.aa.br(true);
        }
        catch (Exception ex) {}
    }
    
    public boolean a(final dg dg, final df df) {
        if (dg != null) {
            dg.aw(false);
            this.bp = true;
            if (dg.d4() == 7) {
                if (!ji.util.d.by(dg.ev())) {
                    this.aa.a(new d7(this.aa, 21, this.x));
                    return true;
                }
            }
            else {
                if (dg.d4() == 9) {
                    this.aa.a(new d7(this.aa, 21, this.x));
                    return true;
                }
                if (dg.d5() == 8) {
                    this.aa.a(new d7(this.aa, 21, this.x));
                    return true;
                }
                if (dg.d4() == 3 || dg.d4() == 6) {
                    if (dg.fm() != null) {
                        final d4 fm = dg.fm();
                        if (fm.b() > 0) {
                            final Rectangle boundingBox = fm.getBoundingBox();
                            if (boundingBox != null && (boundingBox.width > ji.util.e.bb || boundingBox.height > ji.util.e.bb)) {
                                this.b(dg);
                                this.aa.a(new d7(this.aa, 21, this.x));
                                return true;
                            }
                        }
                    }
                }
                else {
                    final da fp = dg.fp();
                    if (fp != null && (fp.c > ji.util.e.bb || fp.d > ji.util.e.bb)) {
                        this.b(dg);
                        this.aa.a(new d7(this.aa, 21, this.x));
                        return true;
                    }
                }
            }
        }
        df.d(dg);
        return false;
    }
    
    public final boolean p() {
        boolean a = false;
        if (this.b != null) {
            a = this.b.a();
        }
        return this.cp != null || a || this.a8;
    }
    
    public void q() {
        this.w();
        if (this.x >= 0) {
            this.aa.a(new d7(this.aa, 20, this.x));
            this.x = -1;
            this.z = -1;
            this.bs = false;
            this.y = -1L;
        }
        this.bw = -1;
        this.bx = -1;
    }
    
    public boolean a(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final int n4, final int n5, final boolean b, final ad ad, final af af) {
        return this.x >= 0 && n4 > 1 && this.b(dx, n, graphics, n2, n3, dg, n4, n5, b, ad, af);
    }
    
    public boolean a(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final int n4, final boolean b, final ad ad, final af af) {
        return this.x >= 0 && this.b(dx, n, graphics, n2, n3, dg, 1, n4, b, ad, af);
    }
    
    public boolean r() {
        return !this.a5;
    }
    
    static final int a(final dx dx) {
        if (dx.ac == dx.ad) {
            return dx.m;
        }
        if (dx.ac > dx.ad) {
            return dx.m;
        }
        return (int)(dx.m * dx.ad / dx.ac);
    }
    
    static final int b(final dx dx) {
        if (dx.ac == dx.ad) {
            return dx.n;
        }
        if (dx.ac > dx.ad) {
            return (int)(dx.n * dx.ac / dx.ad);
        }
        return dx.n;
    }
    
    private final void ah() {
        try {
            if (this.aa != null) {
                this.aa.a(new d7(this.aa, 23, this.aa.ea()));
            }
        }
        catch (Exception ex) {}
    }
    
    private boolean b(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final int n4, final int n5, final boolean b, final ad ad, final af af) {
        final int z = this.z;
        final boolean c = this.c(dx, n, graphics, n2, n3, dg, n4, n5, b, ad, af);
        if (this.z != z) {
            this.ah();
        }
        return c;
    }
    
    private boolean c(final dx dx, final int n, final Graphics graphics, int min, int min2, final dg dg, final int n2, final int n3, final boolean b, final ad ad, final af af) {
        try {
            dg.ai(false);
            min = Math.min(min, a(dx));
            min2 = Math.min(min2, b(dx));
            final int a = this.a(dx, min, n);
            final int b2 = this.b(dx, min2, n);
            dg.am(true);
            dg.g((long)min);
            dg.h((long)min2);
            final boolean b3 = !ji.util.d.bf() || ji.util.d.ao(this.eq);
            switch (dg.d4()) {
                case 7: {
                    if (dg.at()) {
                        if (this.z <= 0) {
                            dg.d(new da(a, b2, 1L, 1L));
                            this.z = 1;
                            dg.c(new d5(a, b2));
                            if (b3) {
                                dg.l(-n3);
                            }
                            dg.n(n);
                            dg.ap(b);
                            break;
                        }
                        if (this.z == 1) {
                            final d5 fn = dg.fn();
                            final long abs = Math.abs(a - fn.a);
                            final long abs2 = Math.abs(b2 - fn.b);
                            if (a < fn.a) {
                                fn.a = a;
                            }
                            if (b2 < fn.b) {
                                fn.b = b2;
                            }
                            dg.d(new da(fn.a, fn.b, abs, abs2));
                            this.bt = 6;
                            if (abs < 20 || abs2 < 20) {
                                dg.am();
                            }
                            else {
                                dg.w(false);
                                dg.a(false);
                            }
                            dg.b(this.aa.cv());
                            this.a5 = false;
                            return true;
                        }
                        break;
                    }
                    else {
                        if (this.a5) {
                            dg.c(new d5(a, b2));
                            if (b3) {
                                dg.l(-n3);
                            }
                            dg.n(n);
                            dg.ap(b);
                            this.bq = false;
                            this.br = false;
                            this.a5 = false;
                            return true;
                        }
                        dg.b(this.aa.cv());
                        this.w();
                        this.ag();
                        break;
                    }
                    break;
                }
                case 9: {
                    if (this.a5) {
                        dg.c(new d5(a, b2));
                        if (b3) {
                            dg.l(-n3);
                        }
                        dg.n(n);
                        dg.ap(b);
                        this.bq = false;
                        this.br = false;
                        this.a5 = false;
                        try {
                            this.aa.a(new d7(this.aa, 23, this.aa.ea()));
                        }
                        catch (Exception ex2) {}
                        return true;
                    }
                    this.ag();
                    break;
                }
                case 4:
                case 5: {
                    if (this.z <= 0) {
                        dg.c(new d5(a, b2));
                        dg.d(new d5(a, b2));
                        this.z = 1;
                        break;
                    }
                    if (this.z == 1) {
                        this.ag();
                        break;
                    }
                    break;
                }
                case 1: {
                    if (this.z <= 0) {
                        dg.h(new da(a, b2, 1L, 1L));
                        this.z = 1;
                        break;
                    }
                    if (this.z == 1) {
                        this.ag();
                        break;
                    }
                    break;
                }
                case 2: {
                    if (this.z <= 0) {
                        dg.c(new d5(a, b2));
                        dg.b(1L, 1L);
                        this.z = 1;
                        break;
                    }
                    if (this.z == 1) {
                        this.ag();
                        break;
                    }
                    break;
                }
                case 3:
                case 6: {
                    if (dg.d5() == 8) {
                        if (ji.util.d.bq() && ad.af()) {
                            dg.a(dg.a(a, (long)b2, ji.util.d0.a(ji.util.d.cv(), dx.ac), ji.util.d0.a(ji.util.d.cv(), dx.ac)));
                        }
                        else {
                            dg.a(dg.a(a, (long)b2, ji.util.d.cu(), ji.util.d.cw()));
                        }
                        this.bq = false;
                        this.br = false;
                        return true;
                    }
                    if (this.z <= 0) {
                        final d4 fm = dg.fm();
                        final int b4 = fm.b();
                        if (b4 < 2) {
                            final d4 d4 = new d4();
                            d4.a(new d5(a, b2));
                            d4.a(new d5(a, b2));
                            dg.a((Polygon)new d4(d4));
                            this.z = 1;
                            break;
                        }
                        if (b4 > 0) {
                            final int[] c = fm.c();
                            final int[] d5 = fm.d();
                            c[0] = a;
                            d5[0] = b2;
                            c[1] = a;
                            d5[1] = b2;
                            dg.a((Polygon)new d4(c, d5, b4));
                            this.z = 1;
                            break;
                        }
                        break;
                    }
                    else {
                        if (n2 > 1) {
                            this.ag();
                            break;
                        }
                        final d4 fm2 = dg.fm();
                        final int b5 = fm2.b();
                        if (b5 <= 0) {
                            break;
                        }
                        if (this.bs) {
                            final int az = ji.util.d.az();
                            if (az > 0 && b5 >= az) {
                                return false;
                            }
                        }
                        final int[] c2 = fm2.c();
                        final int[] d6 = fm2.d();
                        if (new da(c2[0] - 3, d6[0] - 3, 6L, 6L).a(a, b2) && !this.bs) {
                            this.ag();
                            break;
                        }
                        boolean b6 = false;
                        final int n4 = b5 - 1;
                        final int n5 = 2;
                        if (!this.bs) {
                            for (int i = 0; i < n4; ++i) {
                                if (new da(c2[i] - n5, d6[i] - n5, 2 * n5, 2 * n5).a(a, b2)) {
                                    b6 = true;
                                    break;
                                }
                            }
                        }
                        if (!b6 || this.bs) {
                            boolean b7 = true;
                            if (this.bs) {
                                final int n6 = (int)(Math.abs(a - c2[c2.length - 1]) * this.f);
                                final int n7 = (int)(Math.abs(b2 - d6[d6.length - 1]) * this.f);
                                if (n6 < 3 && n7 < 3) {
                                    b7 = false;
                                }
                            }
                            if (b7) {
                                if (dg.e2() && b5 == 3) {
                                    dg.h(dg.e4());
                                    this.ag();
                                }
                                else if (dg.e1() && b5 == 2) {
                                    dg.i(dg.e3() / dx.d());
                                }
                                else {
                                    fm2.a(new d5(a, b2));
                                    dg.a((Polygon)new d4(fm2));
                                    ++this.z;
                                }
                            }
                        }
                        else {
                            dg.a((Polygon)new d4(c2, d6, b5 - 1));
                            this.ag();
                        }
                        if (this.bs) {
                            return true;
                        }
                        break;
                    }
                    break;
                }
            }
            this.bq = false;
            this.br = false;
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    public boolean b(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final int n4, final boolean b, final ad ad, final af af) {
        if (this.x < 0) {
            return false;
        }
        int n5 = 1;
        dg.ai(false);
        if (dg.d5() == 8) {
            return true;
        }
        if (dg.d5() == 9) {
            return true;
        }
        if (dg.d5() == 7 && dg.at()) {
            return this.b(dx, n, graphics, n2, n3, dg, n5, n4, b, ad, af);
        }
        if (this.bs) {
            n5 = 2;
        }
        if (this.bq && this.bt > 5) {
            return this.b(dx, n, graphics, n2, n3, dg, n5, n4, b, ad, af);
        }
        return this.bs && this.br && this.b(dx, n, graphics, n2, n3, dg, n5, n4, b, ad, af);
    }
    
    public boolean c(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final int n4, final boolean b, final ad ad, final af af) {
        return this.b(dx, n, graphics, n2, n3, dg, 1, n4, b, ad, af);
    }
    
    private final boolean d(final int bw, final int bx) {
        int n = ji.annotate.dy.bv + 1;
        int n2 = ji.annotate.dy.bv + 1;
        if (this.bw >= 0) {
            n = bw - this.bw;
        }
        if (this.bx >= 0) {
            n2 = bx - this.bx;
        }
        if (Math.abs(n) > ji.annotate.dy.bv || Math.abs(n2) > ji.annotate.dy.bv) {
            this.bw = bw;
            this.bx = bx;
            return true;
        }
        return false;
    }
    
    public final boolean b(final int n, final int n2) {
        return Math.abs(n) > ji.annotate.dy.bv || Math.abs(n2) > ji.annotate.dy.bv;
    }
    
    public boolean a(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final ad ad, final af af) {
        if (this.p()) {
            return false;
        }
        final int a = this.a(dx, n2, n);
        final int b = this.b(dx, n3, n);
        this.bq = false;
        if (this.z >= 0) {
            if (!this.d(a, b)) {
                return false;
            }
            if (!this.bq) {
                this.bu = 0;
            }
            ++this.bu;
            this.br = true;
            switch (dg.d4()) {
                case 7: {
                    if (dg.at()) {
                        final d5 fn = dg.fn();
                        final long abs = Math.abs(a - fn.a);
                        final long abs2 = Math.abs(b - fn.b);
                        if (a < fn.a) {
                            fn.a = a;
                        }
                        if (b < fn.b) {
                            fn.b = b;
                        }
                        dg.d(new da(fn.a, fn.b, abs, abs2));
                        return true;
                    }
                    break;
                }
                case 4:
                case 5: {
                    if (this.z == 0) {
                        dg.c(new d5(a, b));
                        dg.d(new d5(a, b));
                    }
                    else {
                        dg.c(new d5(a, b));
                    }
                    if (dg.e1()) {
                        dg.i(dg.e3() / dx.d());
                        this.aa.a(dg.c9(), n2, n3, dg);
                    }
                    return true;
                }
                case 1: {
                    if (this.z == 0) {
                        dg.h(new da(a, b, 1L, 1L));
                        dg.g((long)a);
                        dg.h((long)b);
                    }
                    else {
                        final da ff = dg.ff();
                        if (b < dg.fu()) {
                            ff.d = dg.fu() - b;
                            ff.b = b;
                        }
                        else {
                            ff.d = b - dg.fu();
                        }
                        if (a < dg.ft()) {
                            ff.c = dg.ft() - a;
                            ff.a = a;
                        }
                        else {
                            ff.c = a - dg.ft();
                        }
                        if (ff.c == 0) {
                            ff.c = 1L;
                        }
                        if (ff.d == 0) {
                            ff.d = 1L;
                        }
                        dg.h(ff);
                    }
                    return true;
                }
                case 2: {
                    if (this.z == 0) {
                        dg.c(new d5(a, b));
                        dg.b(1L, 1L);
                    }
                    else {
                        final d5 fn2 = dg.fn();
                        long abs3 = Math.abs(a - fn2.a);
                        long abs4 = Math.abs(b - fn2.b);
                        if (abs3 == 0) {
                            abs3 = 1L;
                        }
                        if (abs4 == 0) {
                            abs4 = 1L;
                        }
                        if (fn2.a - abs3 < 1) {
                            abs3 = fn2.a - 1;
                        }
                        if (fn2.a + abs3 >= dx.q) {
                            abs3 = dx.q - fn2.a - 1;
                        }
                        if (fn2.b - abs4 < 1) {
                            abs4 = fn2.b - 1;
                        }
                        if (fn2.b + abs4 >= dx.r) {
                            abs4 = dx.r - fn2.b - 1;
                        }
                        dg.b(abs3, abs4);
                    }
                    return true;
                }
                case 3:
                case 6: {
                    if (this.bs) {
                        return this.c(dx, n, graphics, n2, n3, dg, this.g, this.o, ad, af);
                    }
                    if (this.z == 0) {
                        final d4 fm = dg.fm();
                        final int b2 = fm.b();
                        if (b2 > 0) {
                            final int[] c = fm.c();
                            final int[] d = fm.d();
                            c[0] = a;
                            d[0] = b;
                            dg.a((Polygon)new d4(c, d, b2));
                        }
                    }
                    else if (this.z > 0) {
                        final d4 fm2 = dg.fm();
                        final int b3 = fm2.b();
                        if (b3 > 0) {
                            final int[] c2 = fm2.c();
                            final int[] d2 = fm2.d();
                            c2[this.z] = a;
                            d2[this.z] = b;
                            dg.a((Polygon)new d4(c2, d2, b3));
                            if (dg.e2() && b3 == 3) {
                                dg.h(dg.e4());
                                this.aa.a(dg.c9(), n2, n3, dg);
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean b(final dx dx, final int n, final Graphics graphics, final int n2, final int n3, final dg dg, final ad ad, final af af) {
        if (this.p()) {
            return false;
        }
        if (this.x < 0) {
            return false;
        }
        if (!this.bq) {
            this.bt = 0;
        }
        this.br = false;
        final boolean a = this.a(dx, n, graphics, n2, n3, dg, ad, af);
        ++this.bt;
        this.bq = true;
        return a;
    }
    
    public long c(final dg dg) {
        return dg.d0();
    }
    
    public d4 d(final dg dg) {
        return dg.f6();
    }
    
    public boolean e(final dg dg) {
        return dg.dy();
    }
    
    public boolean f(final dg dg) {
        return dg.d2();
    }
    
    public da g(final dg dg) {
        return dg.fq();
    }
    
    public boolean h(final dg dg) {
        return dg.fx();
    }
    
    private void n(final dg dg) {
        if (dg.d5() == 7) {
            dg.a0(false);
        }
    }
    
    public void a(final dg dg, final boolean b) {
        dg.at(b);
        if (!b) {
            dg.j((da)null);
            dg.o(true);
        }
        else {
            this.n(dg);
        }
    }
    
    public void a(final dg dg, final d4 d4) {
        dg.e(d4);
    }
    
    public double i(final dg dg) {
        return dg.f7();
    }
    
    public double j(final dg dg) {
        return dg.f8();
    }
    
    public void a(final dg dg, final double n) {
        dg.j(n);
    }
    
    public void b(final dg dg, final double n) {
        dg.k(n);
    }
    
    private final void ai() {
        try {
            if (this.ep != null) {
                this.ep.d();
                this.ep = null;
            }
        }
        catch (Exception ex) {}
    }
    
    protected final boolean a(final a0 a0, final Component component, final af af, final String s) {
        boolean b = false;
        try {
            if (this.ep == null) {
                this.ep = new eg(s);
            }
            b = this.ep.c();
            if (!b) {
                this.ep = new eg(s);
                b = this.ep.a(component, af, a0, ji.annotate.dy.d9);
                if (!this.ep.c() || !b) {
                    this.ai();
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex, "Security Problem", component, 30, af, s);
            ex.printStackTrace();
            this.ai();
        }
        return b;
    }
    
    private String a(final dh dh) {
        String s = null;
        if (dh != null) {
            final c a = dh.a(0);
            if (a != null) {
                if (a.b() <= 1) {
                    if (a.b() > 0) {
                        final String h = ((ei)a.b(0)).h;
                        if (h != null) {
                            s = h;
                        }
                    }
                }
                else if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("There are ").append(a.b()).append(" securityXML present. There should be just one"))));
                }
            }
        }
        return s;
    }
    
    private String b(final dh dh) {
        String s = null;
        if (dh != null) {
            final c a = dh.a(1);
            if (a != null) {
                if (a.b() <= 1) {
                    if (a.b() > 0) {
                        final String h = ((ei)a.b(0)).h;
                        if (h != null) {
                            s = h;
                        }
                    }
                }
                else if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("There are ").append(a.b()).append(" securityXML present. There should be just one"))));
                }
            }
        }
        return s;
    }
    
    public boolean a(final dg dg, final a0 a0, final ad ad, final af af, final String s) {
        if (ji.util.d.bf()) {
            if (ji.util.d.am(this.eq)) {
                return this.a(dg, ad, s);
            }
            return this.b(dg, a0, ad, af, s);
        }
        else {
            boolean b = true;
            final dh bp = dg.bp();
            String s2 = bp.f();
            if (ji.util.d.by(s2) && !this.d) {
                s2 = bp.e();
            }
            if (ji.util.d.by(s2) && !this.d && dg.d5() == 7) {
                s2 = bp.g();
            }
            if (!ji.util.d.by(s2) && !this.d && bp.j() && !dg.aw() && !ji.util.d.o(s2)) {
                final String bc = ji.util.d.bc(ji.util.d.a(this.c(569), this.c(653), "", false, true, s, 20, '*', true, false, null, false, 0));
                if (ji.util.d.by(bc)) {
                    b = false;
                }
                else {
                    final String lowerCase = bc.toLowerCase();
                    if (!ji.util.d.a(lowerCase, s2.toLowerCase(), a0, ad, s, af)) {
                        b = false;
                        ji.util.d.a(this.c(569), this.c(654), af, s);
                    }
                    else {
                        ji.util.d.n(lowerCase);
                    }
                }
            }
            if (!b) {
                return false;
            }
            if (!ji.util.d.by(ad.x())) {
                return this.c(dg, a0, ad, af, s);
            }
            this.a(dg, a0, ad, af, s, null);
            if (dg.bo()) {
                final dh bp2 = dg.bp();
                ji.util.d.n(bp2.e());
                ji.util.d.n(bp2.f());
                ji.util.d.n(bp2.g());
                this.b(dg);
                return true;
            }
            return false;
        }
    }
    
    private boolean b(final dg dg, final a0 a0, final ad ad, final af af, final String s) {
        final boolean b = this.eq != 0 || this.a(a0, ad, af, s);
        if ((this.ep == null && this.eq != 1) || !b) {
            return false;
        }
        this.a(dg, a0, ad, af, s, this.ep);
        if (dg.bo()) {
            this.b(dg);
            return true;
        }
        return false;
    }
    
    private boolean a(final dg dg, final ad ad, final String s) {
        if (dg == null || ad == null) {
            throw new IllegalArgumentException("Item or parent is null");
        }
        boolean b = false;
        try {
            final String a = this.a(dg.bp());
            final eq a2 = this.a(s, ad);
            if (a2 != null) {
                final String v = ad.v();
                final String ag = dg.ag();
                final String dx = dg.dx();
                String s2;
                if (a == null) {
                    if (dx == null) {
                        throw new IllegalArgumentException("fn_AnnotatedId paramater not set");
                    }
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Calling P8 Security dialog for annotation (no previous xml) - docID=").append(ag).append(" annotationID=").append(dx).append(" requestURL=").append(v).append(" show:true"))));
                    }
                    s2 = a2.a(ag, dx, v, true);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "XML returned from security dialog:".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                else {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Calling P8 Security dialog for annotation (existing xml) - docID=").append(ag).append(" annotationID=").append(dx).append(" requestURL=").append(v).append(" xml:").append(a))));
                    }
                    s2 = a2.a(ag, dx, v, a);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "XML returned from security dialog:".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                if (s2 != null) {
                    if (a != null) {
                        dg.bp().c(new ei(a, 0, 0));
                    }
                    dg.bp().a(new ei(s2, 0, 0));
                    b = true;
                }
                try {
                    a2.a();
                }
                catch (Exception ex2) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "Failed during call to clean up Ce Security library");
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (ji.util.d.cs()) {
                ji.io.h.d(s, "Failed during call to Filenet Ce Security library");
            }
        }
        return b;
    }
    
    private void a(final dg dg, final a0 a0, final ad ad, final af af, final String s, final eg eg) {
        Frame frame = null;
        final Window f = ji.util.d.f(ad);
        if (f instanceof Frame) {
            this.eo = new d9((Frame)f, dg, s, eg, this.eq, this, a0, af, ad, this.d, this.e);
        }
        else if (f instanceof Dialog) {
            this.eo = new d9((Dialog)f, dg, s, eg, this.eq, this, a0, af, ad, this.d, this.e);
        }
        else {
            frame = new gr();
            this.eo = new d9(frame, dg, s, eg, this.eq, this, a0, af, ad, this.d, this.e);
        }
        this.eo.show();
        this.eo.b();
        if (frame != null) {
            ((gr)frame).c();
        }
    }
    
    private boolean c(final dg dg, final a0 a0, final ad ad, final af af, final String s) {
        if (dg == null || ad == null) {
            throw new IllegalArgumentException("Item or parent is null");
        }
        boolean b = false;
        try {
            final String b2 = this.b(dg.bp());
            final gk b3 = this.b(s, ad);
            if (b3 != null) {
                final String w = ad.w();
                final String be = ad.be(dg.i(-1));
                final String du = dg.du();
                String s2;
                if (b2 == null) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Calling Custom Security dialog for annotation (no previous text) - docID=").append(be).append(" annotationID=").append(du).append(" requestURL=").append(w).append(" show:true"))));
                    }
                    s2 = b3.a(be, du, w, true);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "XML returned from security dialog:".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                else {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("Calling P8 Security dialog for annotation (existing xml) - docID=").append(be).append(" annotationID=").append(du).append(" requestURL=").append(w).append(" xml:").append(b2))));
                    }
                    s2 = b3.a(be, du, w, b2);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "Text returned from security dialog:".concat(String.valueOf(String.valueOf(s2))));
                    }
                }
                boolean b4 = s2 != null;
                if (b4 && b2 != null) {
                    b4 = !s2.equals(b2);
                }
                if (b4) {
                    if (b2 != null) {
                        dg.bp().c(new ei(b2, 1, 0));
                    }
                    dg.bp().a(new ei(s2, 1, 0));
                    b = true;
                }
                try {
                    b3.a();
                }
                catch (Exception ex2) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(s, "Failed during call to clean up Custom Security library");
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (ji.util.d.cs()) {
                ji.io.h.d(s, "Failed during call to Custom Security library");
            }
        }
        return b;
    }
    
    private final void aj() {
        try {
            if (this.eo != null) {
                this.eo.a();
                this.eo = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean a(final dg dg, final String s, final boolean b, final boolean b2) {
        try {
            if (dg.d5() == 8) {
                boolean b3 = true;
                bj b4;
                if (ji.util.d.lw) {
                    if (this.e2 != null) {
                        if (this.e2.b() > 0) {
                            b3 = false;
                        }
                        for (int i = 0; i < this.e2.b(); ++i) {
                            final vb vb = (vb)this.e2.b(i);
                            if (vb.d() == dg.d0()) {
                                vb.b();
                                return true;
                            }
                        }
                    }
                    b4 = new bj(s);
                    this.b6.c(b4);
                }
                else {
                    if (this.b5 == null) {
                        this.b5 = new bj(s);
                    }
                    b4 = this.b5;
                }
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c(485)))).append(" ").append(ji.util.d.a(dg.dr(), true)).append("\n").append(this.c(484)).append(" ").append(ji.util.d.a(dg.di(), true))));
                String ev = dg.ev();
                if (ji.util.d.by(ev)) {
                    ev = "";
                }
                try {
                    if (!ji.util.d.lw) {
                        this.ej = true;
                    }
                    boolean c0 = false;
                    try {
                        c0 = this.aa.c0();
                    }
                    catch (Exception ex) {}
                    b0 b5 = null;
                    if (ji.util.d.lw) {
                        b5 = new vb(b4, dg.d0(), b2, ev, b);
                        if (this.e2 == null) {
                            this.e2 = new c("jiAnnotateDialogListeners");
                        }
                        this.e2.c(b5);
                    }
                    final boolean b6 = ji.util.d.lw && !b3;
                    this.aa.ap(true);
                    final String c2 = this.c(482);
                    String s2;
                    if (c2.indexOf("...") >= 0) {
                        s2 = ji.util.d.b(c2, "...", String.valueOf(String.valueOf(new StringBuffer(" ").append(dg.h()).append("..."))));
                    }
                    else {
                        s2 = String.valueOf(String.valueOf(c2)).concat(String.valueOf(String.valueOf(" ".concat(String.valueOf(String.valueOf(dg.h()))))));
                    }
                    String s3 = null;
                    int j = 1;
                    String ev2 = dg.ev();
                    while (j != 0) {
                        j = 0;
                        if (ji.util.i.c(109)) {
                            s3 = b4.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c(302)))).append(" ").append(dg.i(-1)).append(": ").append(s2))), value, ev2, this.aa, true, false, s, 48, '\0', c0, !ji.util.d.lw, b, b5, b6, this.e6, 1);
                        }
                        else {
                            s3 = b4.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c(302)))).append(" ").append(dg.i(-1)).append(": ").append(s2))), value, ev2, this.aa, true, false, s, 48, '\0', c0, !ji.util.d.lw, b, b5, b6, this.e6, 0);
                        }
                        if (!ji.util.d.lw && s3 != null && ji.util.d.f() > 0 && s3.length() > ji.util.d.f()) {
                            ji.util.d.a(ji.res.ay.a(), ji.util.d.b(this.c(1090), "<#>", "".concat(String.valueOf(String.valueOf(ji.util.d.f())))), this.aa, s);
                            j = 1;
                            this.e6 = b4.f();
                            ev2 = s3;
                        }
                    }
                    this.e6 = 0;
                    if (ji.util.d.lw) {
                        return true;
                    }
                    return this.a(dg, s3, ev, b);
                }
                finally {
                    try {
                        this.aa.cz();
                    }
                    catch (Exception ex2) {}
                    if (!ji.util.d.lw) {
                        this.ej = false;
                    }
                }
            }
        }
        catch (Exception ex3) {}
        return false;
    }
    
    public final boolean s() {
        return this.e3;
    }
    
    public final boolean a(final dg dg, String s, final String s2, final boolean b) {
        if (s != null) {
            if (ji.util.d.by(s)) {
                s = "";
            }
            if (!ji.util.d.by(s) || b) {
                boolean b2 = true;
                if ((!ji.util.d.by(s2) || b) && s.equals(s2)) {
                    b2 = false;
                }
                if (b2) {
                    this.a(this.ab, dg, s);
                    dg.a(ji.util.d.e2(), this.e);
                    dg.b(new Dimension(this.u.m, this.u.n), this.u.v);
                    this.b(dg);
                }
                return b2 || b;
            }
        }
        return false;
    }
    
    public void k(final dg dg) {
        try {
            if (dg.d5() == 8) {
                String s = dg.ev();
                final String c = this.c(482);
                if (ji.util.d.by(s)) {
                    s = this.c(483);
                }
                final int n = c.length() - s.length();
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(" \n \n").append(ji.util.d.a(dg.dr(), true))));
                if (this.b5 == null) {
                    this.b5 = new bj(this.ed);
                }
                if (ji.util.i.c(109)) {
                    this.b5.a(this.c(482), "", value, this.aa, true, false, this.ed, 48, '\0', false, true, false, null, ji.util.d.lw, 0, 1);
                }
                else {
                    this.b5.a(this.c(482), "", value, this.aa, true, false, this.ed, 48, '\0', false, true, false, null, ji.util.d.lw, 0, 0);
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void t() {
        try {
            if (this.ci == null) {
                this.ci = new ee(this.ed);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void u() {
        try {
            if (this.ci != null) {
                this.ci.b();
            }
        }
        catch (Exception ex) {}
        this.ci = null;
    }
    
    public void v() {
        try {
            this.es = null;
            this.d4 = null;
            this.d8 = null;
            this.d5 = null;
            this.d7 = null;
            this.d6 = null;
            this.z();
            this.g();
            this.u();
            this.am();
            this.ai();
            this.ae = null;
            this.o();
            this.aa = null;
            this.ae();
            if (this.b != null) {
                this.b.b();
            }
            try {
                if (this.b5 != null) {
                    this.b5.i();
                }
            }
            catch (Exception ex) {}
            try {
                if (this.e2 != null) {
                    for (int i = 0; i < this.e2.b(); ++i) {
                        ((vb)this.e2.b(i)).a();
                    }
                    this.e2.c();
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.b6 != null) {
                    for (int j = 0; j < this.b6.b(); ++j) {
                        ((bj)this.b6.b(j)).i();
                    }
                    this.b6.c();
                }
            }
            catch (Exception ex3) {}
        }
        catch (Exception ex4) {}
    }
    
    public final void a(final Component component, final dg dg, final String s) {
        final String ev = dg.ev();
        if (ev != null && s != null) {
            if (!ev.equals(s)) {
                if (dg.ak() || dg.c()) {
                    dg.am();
                }
                dg.a(component, s);
            }
            else {
                dg.a(component, s);
            }
        }
        else if (ev == null && s != null) {
            dg.a(component, s);
        }
        else if (ev != null && s == null) {
            if (dg.ak() || dg.c()) {
                dg.am();
            }
            dg.a(component, s);
        }
        else {
            dg.a(component, s);
        }
        if (this.aa != null) {
            this.aa.v(this.aa.ea());
        }
    }
    
    public boolean a(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final char keyChar = keyEvent.getKeyChar();
        return keyCode >= 32 || keyCode == 10 || (keyCode == 0 && keyChar != '\uffff');
    }
    
    public final boolean a(final cy cy, final ad ad, final dg dg, KeyEvent keyEvent, final d4 d4, final c c) {
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        final int keyCode = keyEvent.getKeyCode();
        final String ev = dg.ev();
        final int max = Math.max(dg.em(), 0);
        Object o = null;
        Object o2 = null;
        Object o3 = null;
        Object o4 = null;
        Object o5 = null;
        final Rectangle a = dg.fp().a();
        final d4 d5 = new d4();
        final int et = dg.et();
        final int eu = dg.eu();
        boolean b4 = false;
        dg.r(false);
        if (dg.cn() != null) {
            final int[] cn = dg.cn();
            o = new int[cn.length];
            System.arraycopy(cn, 0, o, 0, cn.length);
            final int[] cp = dg.cp();
            o2 = new int[cp.length];
            System.arraycopy(cp, 0, o2, 0, cp.length);
            final int[] cq = dg.cq();
            o3 = new int[cq.length];
            System.arraycopy(cq, 0, o3, 0, cq.length);
            final int[] cr = dg.cr();
            o4 = new int[cr.length];
            System.arraycopy(cr, 0, o4, 0, cr.length);
            final String[] co = dg.co();
            o5 = new String[co.length];
            System.arraycopy(co, 0, o5, 0, co.length);
        }
        try {
            if ((!ji.util.d.ls || !dg.b(this.g)) && this.b != null) {
                this.b.b(true);
            }
            final boolean b5 = ji.util.d.ls && dg.b(this.g);
            for (int i = 1; i != 0; i = 1) {
                i = 0;
                final boolean b6 = (keyEvent.getModifiers() & 0x1) > 0;
                final boolean b7 = (keyEvent.getModifiers() & 0x2) > 0;
                final boolean b8 = (keyEvent.getModifiers() & 0x8) > 0;
                final String ev2 = dg.ev();
                switch (keyCode) {
                    case 37: {
                        b = true;
                        if (b6) {
                            b4 = true;
                        }
                        if (b7) {
                            dg.a4(b6);
                            break;
                        }
                        dg.a3(b6);
                        break;
                    }
                    case 39: {
                        b = true;
                        if (b6) {
                            b4 = true;
                        }
                        if (b7) {
                            dg.a6(b6);
                            break;
                        }
                        dg.a5(b6);
                        break;
                    }
                    case 8: {
                        if (!ji.util.d.by(ev2)) {
                            dg.el();
                            dg.a3(false);
                            this.a(ad, dg, String.valueOf(String.valueOf(ev2.substring(0, dg.ep()))).concat(String.valueOf(String.valueOf(ev2.substring(dg.ep() + 1)))));
                            b4 = true;
                            break;
                        }
                        break;
                    }
                    case 127: {
                        if (ji.util.d.by(ev2)) {
                            break;
                        }
                        if (dg.et() >= 0 && dg.eu() >= 0) {
                            this.a(dg, true, true);
                            b4 = true;
                            b3 = true;
                            break;
                        }
                        if (dg.ep() < ji.util.d.b3(dg.ev())) {
                            dg.el();
                            this.a(ad, dg, String.valueOf(String.valueOf(ev2.substring(0, dg.ep()))).concat(String.valueOf(String.valueOf(ev2.substring(dg.ep() + 1)))));
                            b4 = true;
                            break;
                        }
                        break;
                    }
                    case 38: {
                        if (b6) {
                            b4 = true;
                        }
                        b = true;
                        dg.a7(b6);
                        break;
                    }
                    case 36: {
                        b = true;
                        if (b6) {
                            b4 = true;
                        }
                        if (b7) {
                            dg.az(b6);
                            break;
                        }
                        dg.a2(b6);
                        break;
                    }
                    case 40: {
                        b = true;
                        if (b6) {
                            b4 = true;
                        }
                        dg.a8(b6);
                        break;
                    }
                    case 155: {
                        if (b6) {
                            this.a(ad, dg, dg.f5(), true, ji.annotate.dy.d9);
                            b2 = true;
                            b4 = true;
                            break;
                        }
                        break;
                    }
                    case 33:
                    case 34: {
                        break;
                    }
                    case 35: {
                        b = true;
                        if (b6) {
                            b4 = true;
                        }
                        if (b7) {
                            dg.a0(b6);
                            break;
                        }
                        dg.a1(b6);
                        break;
                    }
                    case 9: {
                        this.al();
                        this.a(ad, dg, this.cq, false, ji.annotate.dy.d9);
                        b2 = true;
                        b4 = true;
                        break;
                    }
                    default: {
                        boolean b9 = true;
                        if (!this.a(keyEvent)) {
                            break;
                        }
                        if (keyCode == 120 || keyCode == 88) {
                            if (b7) {
                                this.a(dg, true, true);
                                b3 = true;
                                b9 = false;
                                b4 = true;
                            }
                        }
                        else if (keyCode == 118 || keyCode == 86) {
                            if (b7) {
                                String s = this.ak();
                                if (s != null) {
                                    if (ji.util.d.by(s)) {
                                        s = dg.f5();
                                    }
                                    this.a(ad, dg, s, true, ji.annotate.dy.d9);
                                    b2 = true;
                                    b9 = false;
                                    b4 = true;
                                }
                            }
                        }
                        else if (keyCode == 122 || keyCode == 90) {
                            if (b7) {
                                this.o(dg);
                                b4 = true;
                                b9 = false;
                            }
                        }
                        else if ((keyCode == 99 || keyCode == 67) && b7) {
                            this.a(dg, true, false);
                            b3 = true;
                            b4 = true;
                            b9 = false;
                        }
                        final gs fh = ji.util.d.fh();
                        for (int j = 0; j < fh.a(); ++j) {
                            if (fh.e(j).equals(KeyEvent.getKeyText(keyCode)) && fh.b(j) == b7 && fh.a(j) == b6 && fh.c(j) == b8) {
                                if (dg.et() >= 0 && dg.eu() >= 0) {
                                    this.a(dg, false, true);
                                    b3 = true;
                                }
                                this.a(ad, dg, fh.d(j), true, ji.annotate.dy.d9);
                                b2 = true;
                                b4 = true;
                                b9 = false;
                            }
                        }
                        if (b9) {
                            if (dg.et() >= 0 && dg.eu() >= 0) {
                                this.a(dg, false, true);
                                b3 = true;
                            }
                            this.a(ad, dg, "".concat(String.valueOf(String.valueOf(keyEvent.getKeyChar()))), true, ji.annotate.dy.d9);
                            b2 = true;
                            b4 = true;
                            break;
                        }
                        break;
                    }
                }
                if (c != null && c.b() > 0) {
                    keyEvent = (KeyEvent)c.b(0);
                    c.d(0);
                }
            }
            if (b5 && ji.util.d.ls && dg.b(this.g)) {
                if (b4) {
                    dg.a(ad, this.u);
                }
                try {
                    if (o != null) {
                        final int[] cn2 = dg.cn();
                        final int[] cp2 = dg.cp();
                        final int[] cq2 = dg.cq();
                        final int[] cr2 = dg.cr();
                        final String[] co2 = dg.co();
                        try {
                            final int length = dg.ev().length();
                            int n2;
                            final int n = n2 = ev.length();
                            int n3 = -1;
                            int n4 = length;
                            int n5 = -1;
                            for (int k = 0; k < n; ++k) {
                                boolean b10 = false;
                                final String s2 = o5[k];
                                for (int l = 0; l < length; ++l) {
                                    if (co2[l].equals(s2)) {
                                        b10 = true;
                                        break;
                                    }
                                }
                                if (!b10 && k < n) {
                                    d5.addPoint(o[k], o2[k]);
                                    d5.addPoint(o[k] + o3[k], o2[k] + o4[k]);
                                    n2 = Math.min(k, n2);
                                    n3 = Math.max(k, n3);
                                }
                            }
                            for (int n6 = 0; n6 < length; ++n6) {
                                boolean b11 = false;
                                final String s3 = co2[n6];
                                for (int n7 = 0; n7 < o5.length; ++n7) {
                                    if (o5[n7].equals(s3)) {
                                        b11 = true;
                                        break;
                                    }
                                }
                                if (!b11) {
                                    d5.addPoint(cn2[n6], cp2[n6]);
                                    d5.addPoint(cn2[n6] + cq2[n6], cp2[n6] + cr2[n6]);
                                    n4 = Math.min(n6, n4);
                                    n5 = Math.max(n6, n5);
                                }
                            }
                            if (b2) {
                                try {
                                    for (int max2 = Math.max(dg.em() - 1, 0), n8 = max; n8 <= max2; ++n8) {
                                        d5.addPoint(cn2[n8], cp2[n8]);
                                        d5.addPoint(cn2[n8] + cq2[n8] * 3, cp2[n8] + cr2[n8]);
                                        n4 = Math.min(n8, n4);
                                        n5 = Math.max(n8, n5);
                                    }
                                }
                                catch (Exception ex) {}
                            }
                            final int et2 = dg.et();
                            final int eu2 = dg.eu();
                            if (et2 >= 0 && eu2 >= 0) {
                                for (int n9 = et2; n9 < eu2 + 1; ++n9) {
                                    if (et >= 0 && eu >= 0) {
                                        if ((n9 < et || n9 > eu) && n9 < cn2.length) {
                                            d5.addPoint(cn2[n9], cp2[n9]);
                                            d5.addPoint(cn2[n9] + cq2[n9], cp2[n9] + cr2[n9]);
                                            n4 = Math.min(n9, n4);
                                            n5 = Math.max(n9, n5);
                                        }
                                    }
                                    else if (n9 < cn2.length) {
                                        d5.addPoint(cn2[n9], cp2[n9]);
                                        d5.addPoint(cn2[n9] + cq2[n9], cp2[n9] + cr2[n9]);
                                        n4 = Math.min(n9, n4);
                                        n5 = Math.max(n9, n5);
                                    }
                                }
                            }
                            if (et >= 0 && eu >= 0) {
                                for (int n10 = et; n10 < eu + 1; ++n10) {
                                    if (et2 >= 0 && eu2 >= 0) {
                                        if ((n10 < et2 || n10 > eu2) && n10 < o.length) {
                                            d5.addPoint(o[n10], o2[n10]);
                                            d5.addPoint(o[n10] + o3[n10], o2[n10] + o4[n10]);
                                            n2 = Math.min(n10, n2);
                                            n3 = Math.max(n10, n3);
                                        }
                                    }
                                    else if (n10 < o.length) {
                                        d5.addPoint(o[n10], o2[n10]);
                                        d5.addPoint(o[n10] + o3[n10], o2[n10] + o4[n10]);
                                        n2 = Math.min(n10, n2);
                                        n3 = Math.max(n10, n3);
                                    }
                                }
                            }
                            for (int n11 = n2; n11 < n3; ++n11) {
                                d5.addPoint(o[n11], o2[n11]);
                                d5.addPoint(o[n11] + o3[n11], o2[n11] + o4[n11]);
                            }
                            for (int n12 = n4; n12 < n5; ++n12) {
                                d5.addPoint(cn2[n12], cp2[n12]);
                                d5.addPoint(cn2[n12] + cq2[n12], cp2[n12] + cr2[n12]);
                            }
                            if (d5.b() == 0 && max != Math.max(dg.em(), 0) && max < n) {
                                d5.addPoint(o[max] - 4, o2[max]);
                                d5.addPoint(o[max] + o3[max] + 4, o2[max] + o4[max]);
                            }
                        }
                        catch (Exception ex2) {}
                    }
                }
                catch (Exception ex3) {}
                if (b3) {
                    dg.r(true);
                }
                else {
                    final int min = Math.min(a.x, a.x + a.width);
                    final int max3 = Math.max(a.y, a.y + a.height);
                    final d4 d6 = new d4();
                    try {
                        if (dg.cj() + dg.b4() == 0) {
                            for (int n13 = 0; n13 < d5.b(); ++n13) {
                                final d5 a2 = d5.a(n13);
                                d6.addPoint((int)(min + a2.a), (int)(max3 - (a.height - a2.b)));
                            }
                        }
                        else {
                            for (int n14 = 0; n14 < d5.b(); ++n14) {
                                final d5 a3 = d5.a(n14);
                                d6.addPoint((int)(min + a3.a), (int)(max3 - a3.b));
                            }
                        }
                    }
                    catch (Exception ex4) {}
                    final Rectangle bounds;
                    final Rectangle rectangle = bounds = d6.getBounds();
                    bounds.width += 2;
                    final Rectangle rectangle2 = rectangle;
                    rectangle2.x -= 4;
                    final Rectangle rectangle3 = rectangle;
                    rectangle3.width += 8;
                    if (!dg.ae()) {
                        d4.a(new da(rectangle));
                    }
                }
            }
        }
        catch (Exception ex5) {}
        if (!b) {
            dg.a(System.currentTimeMillis());
        }
        return b;
    }
    
    private final String ak() {
        String fe = "";
        try {
            if (this.aa != null) {
                ji.util.d.fd();
                new bb(this.ed, new z9()).start();
                final int n = 100;
                int n2 = 0;
                final int n3 = 10000;
                while (!ji.util.d.ff() && n2 < n3) {
                    n2 += n;
                    ji.util.d.b(n, 1002, this.ed);
                }
                fe = ji.util.d.fe();
            }
        }
        catch (Exception ex) {}
        return fe;
    }
    
    private final void al() {
        try {
            if (this.cq == null) {
                final char[] array = new char[ji.util.d.ck()];
                for (int i = 0; i < array.length; ++i) {
                    array[i] = ' ';
                }
                this.cq = new String(array);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void o(final dg dg) {
        try {
            dg.b((Component)this.ab);
        }
        catch (Exception ex) {}
    }
    
    private final String a(final dg dg, final boolean b, final boolean b2) {
        String b3 = null;
        try {
            final String ev = dg.ev();
            if (!ji.util.d.by(ev)) {
                b3 = this.b(dg, b);
                if (!ji.util.d.by(b3) && b3 != null && b2) {
                    final int ep = dg.ep();
                    dg.el();
                    this.a(this.ab, dg, String.valueOf(String.valueOf(ev.substring(0, dg.et()))).concat(String.valueOf(String.valueOf(ev.substring(dg.eu() + 1)))));
                    dg.c7();
                    dg.z(ep);
                }
            }
        }
        catch (Exception ex) {}
        return b3;
    }
    
    public final String b(final dg dg, final boolean b) {
        String substring = null;
        try {
            final String ev = dg.ev();
            if (!ji.util.d.by(ev)) {
                if (dg.et() >= 0 && dg.eu() >= 0) {
                    substring = ev.substring(dg.et(), dg.eu() + 1);
                }
                else {
                    substring = ev;
                }
                if (!ji.util.d.by(substring) && b) {
                    dg.t(substring);
                    if (this.aa != null) {
                        this.aa.a(new ob(this.aa, 1, substring));
                    }
                }
            }
        }
        catch (Exception ex) {}
        return substring;
    }
    
    public final void a(final Component component, final dg dg, final String s, final boolean b, final String s2) {
        try {
            if (!ji.util.d.by(s)) {
                String s3;
                try {
                    if (s2 != null) {
                        s3 = new String(s.getBytes(s2), s2);
                    }
                    else {
                        s3 = s;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    s3 = s;
                }
                String ev = dg.ev();
                if (ji.util.d.by(ev)) {
                    ev = "";
                }
                if (b) {
                    dg.el();
                }
                if (ji.util.d.by(ev)) {
                    this.a(component, dg, s3);
                    dg.a1(false);
                }
                else if (dg.eo()) {
                    this.a(component, dg, String.valueOf(String.valueOf(ev)).concat(String.valueOf(String.valueOf(s3))));
                    dg.a0(false);
                }
                else {
                    this.a(component, dg, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ev.substring(0, Math.min(dg.ep(), ev.length()))))).append(s3).append(ev.substring(Math.min(dg.ep(), ev.length()))))));
                    for (int i = 0; i < s3.length(); ++i) {
                        dg.a5(false);
                    }
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public final int a(final dg dg, final dx dx) {
        return ji.annotate.b8.a(dg, dx, this.ak, this.a4);
    }
    
    public final void f(final boolean b) {
        if (this.b != null) {
            this.b.a(b);
        }
    }
    
    public final boolean g(final boolean b) {
        return this.b != null && this.b.b(b);
    }
    
    public final boolean w() {
        return this.b != null && this.b.b(false);
    }
    
    public final void a(final MouseEvent mouseEvent) {
        this.n = true;
    }
    
    public final boolean x() {
        return this.n;
    }
    
    public final void b(final MouseEvent mouseEvent) {
        this.n = false;
    }
    
    public final void h(final boolean b7) {
        this.b7 = b7;
    }
    
    public void i(final boolean el) {
        this.el = el;
    }
    
    private void a(final dt dt, final int[] array, final int[] array2, final int n, final Component component) {
        ji.util.d.am(true);
        this.el = dt.a(array, array2, n, component, this.ax);
    }
    
    private void a(final dt dt, final d4 d4, final Component component) {
        ji.util.d.am(true);
        this.el = dt.a(ji.util.d.a(d4), component, this.ax);
    }
    
    private void a(final dt dt, final d4 d4, final boolean b, final Component component) {
        ji.util.d.am(true);
        this.el = dt.a(ji.util.d.a(d4), b, component, this.ax);
    }
    
    private void b(final dt dt, final d4 d4, final Component component) {
        ji.util.d.am(true);
        this.el = dt.b(ji.util.d.a(d4), component, this.ax);
    }
    
    protected void a(final dt dt, final int n, final int n2, final int n3, final int n4, final Component component) {
        ji.util.d.am(true);
        this.el = dt.a(n, n2, n3, n4, component, this.ax);
    }
    
    protected void b(final dt dt, final int n, final int n2, final int n3, final int n4, final Component component) {
        ji.util.d.am(true);
        this.el = dt.b(n, n2, n3, n4, component, this.ax);
    }
    
    protected void c(final dt dt, final int n, final int n2, final int n3, final int n4, final Component component) {
        try {
            ji.util.d.am(true);
            this.el = dt.c(n, n2, n3, n4, component, this.ax);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final String d9) {
        ji.annotate.dy.d9 = d9;
    }
    
    public final String y() {
        return ji.annotate.dy.d9;
    }
    
    private String[] a(final String ea, final ad ad, final String s, final boolean b, final boolean b2) throws Exception {
        try {
            this.ea = ea;
            final String[] a = ji.util.d.a(ea, ad, s, this.ed, b, false);
            if (!b2 && a == null) {
                throw new FileNotFoundException(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ea))).append("\n ----- ").append(this.c(286)).append(" -----"))));
            }
            return a;
        }
        catch (FileNotFoundException ex) {
            throw ex;
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    private int a(final String[] array, final int n) {
        if (ji.util.d.cs()) {
            ji.io.h.d(this.ed, "Next annotation...");
        }
        int i = n;
        try {
            while (i < array.length) {
                final String bc = ji.util.d.bc(array[i]);
                if (bc == null) {
                    break;
                }
                if (bc.startsWith("[")) {
                    break;
                }
                ++i;
            }
        }
        catch (Exception ex) {}
        return i;
    }
    
    private final int a(String a, final String s, final int n) {
        if (this.cf != null) {
            return 0;
        }
        if (a == null) {
            return 0;
        }
        if (n > 0 && this.es != null) {
            a = this.a(this.es, n, a);
        }
        if (ji.util.d.bb(a)) {
            final int c = ji.util.d.c(a, 0);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(c).append(" (").append(a).append(")"))));
            }
            return c;
        }
        this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s.toUpperCase()).append(" parameter - numeric required, found '").append(a).append("'")));
        return 0;
    }
    
    private final long b(String a, final String s, final int n) {
        if (this.cf != null) {
            return 0L;
        }
        if (a == null) {
            return 0L;
        }
        if (n > 0 && this.es != null) {
            a = this.a(this.es, n, a);
        }
        if (ji.util.d.bb(a)) {
            final long a2 = ji.util.d.a(a, 0L);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(a2).append(" (").append(a).append(")"))));
            }
            return a2;
        }
        this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s.toUpperCase()).append(" parameter - numeric required, found '").append(a).append("'")));
        return 0L;
    }
    
    private final double c(String a, final String s, final int n) {
        if (this.cf != null) {
            return 1.0;
        }
        if (a == null) {
            return 1.0;
        }
        if (n > 0 && this.es != null) {
            a = this.a(this.es, n, a);
        }
        if (ji.util.d.bb(a)) {
            final double a2 = ji.util.d.a(a, 1.0);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(a2).append(" (").append(a).append(")"))));
            }
            return a2;
        }
        this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s.toUpperCase()).append(" parameter - numeric required")));
        return 1.0;
    }
    
    private final boolean d(String a, final String s, final int n) {
        boolean b = false;
        if (this.cf != null) {
            return b;
        }
        if (a == null) {
            return b;
        }
        if (n > 0 && this.es != null) {
            a = this.a(this.es, n, a);
        }
        if (ji.util.d.bb(a)) {
            final int c = ji.util.d.c(a, 0);
            if (c != 0) {
                b = true;
            }
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(c).append(" (").append(a).append(")"))));
            }
            return b;
        }
        this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s.toUpperCase()).append(" parameter - numeric required - 0 or 1")));
        return b;
    }
    
    private final Dimension e(String a, final String s, final int n) {
        Dimension dimension = null;
        if (this.cf == null) {
            try {
                if (n > 0 && this.es != null) {
                    a = this.a(this.es, n, a);
                }
                final int index = a.indexOf(",");
                if (index >= 0) {
                    dimension = new Dimension(ji.util.d.c(a.substring(0, index), 0), ji.util.d.c(a.substring(index + 1), 0));
                }
            }
            catch (Exception ex) {}
        }
        return dimension;
    }
    
    private final GregorianCalendar f(String a, final String s, final int n) {
        GregorianCalendar e2 = ji.util.d.e2();
        if (this.cf == null) {
            try {
                if (a == null) {
                    return e2;
                }
                if (n > 0 && this.es != null) {
                    a = this.a(this.es, n, a);
                }
                SimpleDateFormat simpleDateFormat;
                if (ji.util.d.bf()) {
                    simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, kk:mm:ss, z", Locale.ENGLISH);
                }
                else {
                    simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, kk:mm:ss, z");
                }
                String concat = a;
                if (ji.util.d.bf()) {
                    final int lastIndex = concat.lastIndexOf(58);
                    final int lastIndex2 = concat.lastIndexOf(44);
                    if (lastIndex != -1 && lastIndex > lastIndex2) {
                        concat = String.valueOf(String.valueOf(concat.substring(0, lastIndex))).concat(String.valueOf(String.valueOf(concat.substring(lastIndex + 1))));
                    }
                }
                e2 = new GregorianCalendar();
                e2.setTime(simpleDateFormat.parse(concat));
                if (e2 != null) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(ji.util.d.a(e2, false)).append(" (").append(a).append(")"))));
                    }
                    return e2;
                }
                this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s.toUpperCase()).append(" parameter - date required")));
                return e2;
            }
            catch (Exception ex) {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Date Error:".concat(String.valueOf(String.valueOf(ex.toString()))));
                }
                return e2;
            }
        }
        return e2;
    }
    
    private final Color a(final String s, final String s2, final ad ad, final int n) {
        if (this.cf != null) {
            return null;
        }
        if (ji.util.d.by(s)) {
            return null;
        }
        if (ji.util.d.bp() && ad.af()) {
            final int a = this.a(s, s2, n);
            int n2 = (a & ji.util.d.if) >> ji.util.d.ig;
            int n3 = (a & ji.util.d.ih) >> ji.util.d.ii;
            int n4 = (a & ji.util.d.ij) >> ji.util.d.ik;
            int n5 = (a & ji.util.d.il) >> ji.util.d.im;
            if (ji.util.d.if == 0) {
                n2 = 255;
            }
            if (ji.util.d.ih == 0) {
                n3 = 0;
            }
            if (ji.util.d.ij == 0) {
                n4 = 0;
            }
            if (ji.util.d.il == 0) {
                n5 = 0;
            }
            final Color color = new Color((n2 << 24) + (n3 << 16) + (n4 << 8) + n5);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2.toUpperCase()))).append(" = ").append(ji.util.d.a(color)).append(" (").append(s).append(")"))));
            }
            return color;
        }
        final Color b1 = ji.util.d.b1(s);
        if (b1 != null) {
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2.toUpperCase()))).append(" = ").append(ji.util.d.a(b1)).append(" (").append(s).append(")"))));
            }
            return b1;
        }
        this.cf = String.valueOf(String.valueOf(new StringBuffer("Invalid ").append(s2.toUpperCase()).append(" parameter - color required")));
        return null;
    }
    
    private final String[] a(final String[] array, final int n, final int n2) {
        final c c = new c("jiAnnotate6");
        for (int i = n + 1; i < array.length; ++i) {
            String s = ji.util.d.bc(array[i]);
            if (!ji.util.d.by(s)) {
                if (s.startsWith("[")) {
                    break;
                }
                if (n2 > 0 && this.es != null) {
                    s = this.a(this.es, n2, s);
                }
                c.c(s);
            }
        }
        final String[] array2 = new String[c.b()];
        for (int j = 0; j < c.b(); ++j) {
            array2[j] = (String)c.b(j);
        }
        return array2;
    }
    
    private final String g(String a, final String s, final int n) {
        if (this.cf == null) {
            if (ji.util.d.cs()) {
                if (s.toLowerCase().equals("text")) {
                    String s2 = "";
                    final char[] charArray = a.toCharArray();
                    for (int i = 0; i < charArray.length; ++i) {
                        if (s2.length() > 0) {
                            s2 = String.valueOf(String.valueOf(s2)).concat("/");
                        }
                        s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer("").append((int)charArray[i]).append("(").append(charArray[i]).append(")"))))));
                    }
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(a))));
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append("(codes) = ").append(s2))));
                }
                if (!s.toLowerCase().equals("id")) {
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s.toUpperCase()))).append(" = ").append(a))));
                }
            }
            if (n > 0 && this.es != null) {
                a = this.a(this.es, n, a);
                if (ji.util.d.cs() && s.toLowerCase().equals("text")) {
                    ji.io.h.d(this.ed, "Text after subs = ".concat(String.valueOf(String.valueOf(a))));
                }
            }
            return a;
        }
        return a;
    }
    
    private final String a(final String s, final String[] array, final int n, final boolean b, final int n2, final String s2) {
        return this.a(s, array, n, b, false, n2, s2);
    }
    
    private final String a(final String s, final String[] array, final int n, final boolean b, final boolean b2, final int n2, final String s2) {
        if (this.cf == null) {
            String a = null;
            final String lowerCase = s.toLowerCase();
            int eg = n;
            this.eg = eg;
            while (a == null && eg < array.length) {
                String s3 = ji.util.d.bc(array[eg]);
                if (!ji.util.d.by(s3)) {
                    String eb = s3;
                    if (ji.annotate.dy.ez) {
                        try {
                            final int index = s3.indexOf(61);
                            if (index > 0) {
                                final String substring = s3.substring(0, index);
                                final String substring2 = s3.substring(s3.indexOf(61));
                                s3 = substring.toLowerCase(Locale.ENGLISH);
                                s3 = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(substring2.toLowerCase())));
                            }
                            else if (s3.indexOf(91) == 0) {
                                s3 = s3.toLowerCase(Locale.ENGLISH);
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    else {
                        s3 = s3.toLowerCase();
                    }
                    if (s3.startsWith("[")) {
                        break;
                    }
                    if (s3.startsWith(lowerCase)) {
                        this.eb = eb;
                        final String bc = ji.util.d.bc(s3.substring(lowerCase.length()));
                        if (b2) {
                            eb = ji.util.d.bc(eb.substring(lowerCase.length()));
                        }
                        if (bc.startsWith("=")) {
                            final String bc2 = ji.util.d.bc(bc.substring(1));
                            if (b2) {
                                eb = ji.util.d.bc(eb.substring(1));
                            }
                            if (!ji.util.d.by(bc2)) {
                                if (b2) {
                                    a = eb;
                                    break;
                                }
                                a = bc2;
                                break;
                            }
                        }
                    }
                }
                ++eg;
                ++this.eg;
            }
            if (b && ji.util.d.by(a)) {
                this.cf = String.valueOf(String.valueOf(new StringBuffer("missing ").append(s.toUpperCase()).append(" parameter")));
            }
            if (n2 > 0 && this.es != null) {
                a = this.a(this.es, n2, a);
            }
            return a;
        }
        return null;
    }
    
    public final void a(final af af, final String s) {
        if (af != null) {
            af.a(new a6(this, 1, s));
        }
    }
    
    public final void a(final af af, final a6 a6) {
        if (af != null) {
            af.a(a6);
        }
    }
    
    public final boolean a(final df df, final URL url, final String s, final String s2, final ad ad, final af af, final ad ad2, final boolean b, final String s3, final String s4, final boolean b2, final boolean[] array, final boolean[] array2) {
        boolean a = true;
        try {
            if (this.cg != null) {
                this.cg.c(df);
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Annotations queued for sending to ".concat(String.valueOf(String.valueOf(url))));
                }
                w6 w6 = null;
                if (this.bn == null) {
                    if (af != null) {
                        ji.util.e.ag(this.c(487));
                        af.a(new a6(this, 9, ""));
                        af.a(new a6(this, 15, ""));
                    }
                    w6 = new w6(url, s, s2, ad, af, ad2, s3, s4, b2, array, array2);
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "sendThread create ");
                    }
                    if (!b2) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "sendThread run");
                        }
                        w6.run();
                    }
                    else {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "sendThread start");
                        }
                        (this.bn = new bb(this.ed, w6)).start();
                    }
                }
                if (b && b2 && this.bn != null) {
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "sendThread w/cr");
                    }
                    if (ji.util.e.av()) {
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "j2 sendThread j");
                        }
                        ji.util.d.a(this.bn, this.ed);
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "j2 sendThread finished ");
                        }
                    }
                    else {
                        int n = 0;
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "msvm sendThread s");
                        }
                        while (this.bn != null && !ji.util.d.c3()) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(this.ed, "msvm Waiting for sendThread to finish count ".concat(String.valueOf(String.valueOf(++n))));
                            }
                            ji.util.d.b(100, 40, this.ed);
                        }
                    }
                }
                else if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "sendThread go");
                }
                if (w6 != null) {
                    a = w6.a();
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "sendThread s: ".concat(String.valueOf(String.valueOf(a))));
                    }
                }
            }
        }
        catch (Exception ex) {
            a = false;
            ji.util.d.a(ex);
            try {
                if (af != null) {
                    af.a(new a6(this, 10, ""));
                    af.a(new a6(this, 16, ""));
                    return a;
                }
                return a;
            }
            catch (Exception ex2) {
                ji.util.d.a(ex2);
            }
        }
        return a;
    }
    
    private final void a(final af af, final int n) {
        try {
            if (af != null) {
                af.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n)))));
            }
        }
        catch (Exception ex) {}
    }
    
    private final boolean a(final df df, final URL url, final String s, final String s2, final ad ad, final af af, final String s3, final boolean b, final String s4, final boolean b2, final boolean[] array, final boolean[] array2) {
        boolean b3 = true;
        boolean b4 = true;
        bh bh = null;
        final boolean cc = ji.util.d.cc();
        try {
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, "Sending annotations encoding : ".concat(String.valueOf(String.valueOf(s3))));
            }
            if (this.cg != null) {
                if (s != null && s.toLowerCase().startsWith("post")) {
                    b4 = false;
                }
                if (ji.util.d.cs()) {
                    if (b4) {
                        ji.io.h.d(this.ed, "Sending annotations to servlet ".concat(String.valueOf(String.valueOf(url))));
                    }
                    else {
                        ji.io.h.d(this.ed, "Sending annotations using POST ".concat(String.valueOf(String.valueOf(url))));
                    }
                }
                this.a(af, String.valueOf(String.valueOf(this.c(487))).concat("..."));
                byte[] array3 = this.a(df, ad, ad, false, array, s3, b, true, array2);
                if (ad.bi(17)) {
                    array3 = ji.zip.a4.a(array3);
                    if (ji.util.i.c(253)) {
                        ji.io.h.d(this.ed, "Annotation Data is being sent with a GZIP content encoding.");
                        ji.io.h.d(this.ed, "GZipped Data: \n".concat(String.valueOf(String.valueOf(new String(array3)))));
                    }
                }
                bh = new bh(url, this.ed, b4, array3.length, ad, ad.bi(17));
                final String concat = String.valueOf(String.valueOf(this.c(1100))).concat("...");
                final a6 a6 = new a6(ad, 17, concat);
                a6.a(true);
                a6.a(0);
                a6.a(concat);
                this.a(af, a6);
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Sending total of ").append(array3.length).append(" bytes..."))));
                }
                if (b4) {
                    bh.a(af, array3);
                }
                else {
                    bh.a(af, array3, ji.util.d.bf() && ad.af(), cc, s3, s4, ad.bi(17));
                }
                final String concat2 = String.valueOf(String.valueOf(this.c(1091))).concat("...");
                final a6 a7 = new a6(ad, 17, concat2);
                a7.a(true);
                a7.a(0);
                a7.a(concat2);
                this.a(af, a7);
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Waiting for reply...");
                }
                String s5 = bh.b();
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Header response: ".concat(String.valueOf(String.valueOf(s5))));
                }
                String s6 = null;
                Object substring = null;
                if (s5 != null) {
                    s6 = ji.util.bh.a(s5);
                }
                boolean b5 = s6 != null;
                if (s6 != null) {
                    b5 = (b5 & !s6.equals("") & !ji.util.bh.b(s6));
                }
                if (!b5) {
                    try {
                        s5 = bh.a(1024);
                    }
                    catch (IOException ex3) {
                        final String headerField = bh.a().getHeaderField(0);
                        if (headerField != null) {
                            throw new IOException("Error saving annotations, server returned: ".concat(String.valueOf(String.valueOf(headerField))));
                        }
                        throw new IOException("Error saving annotations");
                    }
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, "Response3: ".concat(String.valueOf(String.valueOf(s5))));
                    }
                    if (s5 != null) {
                        if (s5.toLowerCase().indexOf("doctype html") >= 0) {
                            s5 = "<ok>";
                        }
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "Response4: ".concat(String.valueOf(String.valueOf(s5))));
                        }
                        if (s5.toUpperCase().startsWith("RESPONSE")) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(this.ed, "Replacing FileNET ID's");
                            }
                            int n = 0;
                            for (int i = 0; i == 0; i = 1) {
                                n = s5.indexOf("<N>", n + 1) + 2;
                                if (n == -1 || s5.charAt(n + 1) != '#') {}
                            }
                            final String substring2 = s5.substring(n + 1);
                            int j = 0;
                            int n2 = 0;
                            while (j == 0) {
                                final int index = substring2.indexOf(61, n2);
                                final int index2 = substring2.indexOf("<N>", n2);
                                if (index != -1) {
                                    final String trim = substring2.substring(n2, index).trim();
                                    if (trim.toLowerCase().equals("status")) {
                                        j = 1;
                                    }
                                    else {
                                        String s7;
                                        if (index2 != -1) {
                                            s7 = substring2.substring(index + 1, index2).trim();
                                        }
                                        else {
                                            s7 = substring2.substring(index + 1).trim();
                                            j = 1;
                                        }
                                        if (s7.length() > 0) {
                                            this.a(trim, s7, df);
                                        }
                                        n2 = index2 + 3;
                                    }
                                }
                                else {
                                    j = 1;
                                }
                            }
                        }
                        else {
                            s6 = ji.util.bh.a(s5);
                            if (ji.util.d.cs()) {
                                ji.io.h.d(this.ed, "Response5: ".concat(String.valueOf(String.valueOf(s6))));
                            }
                            b5 = (!s6.equals("") && !ji.util.bh.b(s6));
                        }
                    }
                }
                if (b5) {
                    this.b9 = true;
                    b3 = false;
                    if (s6 != null && s6.toLowerCase().indexOf("<dialog>") > -1) {
                        substring = s6.substring(s6.toLowerCase().indexOf("<dialog>") + 8);
                    }
                    if (substring != null) {
                        this.ee = String.valueOf(String.valueOf(this.ee)).concat(String.valueOf(String.valueOf(substring)));
                    }
                    else {
                        ji.io.h.d(this.ed, "Annotation Save: Server response: ".concat(String.valueOf(String.valueOf(s6))));
                        this.ee = String.valueOf(String.valueOf(this.ee)).concat(String.valueOf(String.valueOf(this.c(1197))));
                    }
                    if (ji.util.d.cs()) {
                        ji.io.h.d(this.ed, this.ee);
                        if (ji.util.d.by(s5)) {
                            ji.io.h.d(this.ed, "Reply received: <EMPTY>");
                        }
                        else {
                            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Reply received (").append(s5.length()).append(" bytes): ").append(s5))));
                        }
                    }
                    if (ji.util.e.ad != null && s6 != null) {
                        if (s6.equals("")) {
                            ji.util.e.a("<EMPTY>", ad, this.ed);
                        }
                        else {
                            ji.util.e.a(s6, ad, this.ed);
                        }
                    }
                }
                else if (ji.util.d.cs() && b4) {
                    if (ji.util.d.by(s5)) {
                        ji.io.h.d(this.ed, "Reply received: <EMPTY>");
                    }
                    else {
                        ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Reply received (").append(s5.length()).append(" bytes): ").append(s5))));
                    }
                }
                bh.d();
                if (ji.util.d.cs()) {
                    if (b4) {
                        ji.io.h.d(this.ed, "Closing servlet stream...");
                    }
                    else {
                        ji.io.h.d(this.ed, "Closing POST stream...");
                    }
                }
                bh.c();
                if (ji.util.d.cs()) {
                    if (b4) {
                        ji.io.h.d(this.ed, "Closed servlet stream.");
                    }
                    else {
                        ji.io.h.d(this.ed, "Closed POST stream.");
                    }
                }
                if (ji.util.d.cs()) {
                    if (this.b9) {
                        ji.io.h.d(this.ed, "Send failed");
                    }
                    else {
                        ji.io.h.d(this.ed, "Sent OK");
                    }
                }
                this.a(af, 0);
                if (this.b9) {
                    this.a(af, String.valueOf(String.valueOf(this.c(486))).concat("."));
                    ji.util.e.ag(this.c(486));
                    af.a(new a6(this, 9, ""));
                }
                else {
                    this.a(af, String.valueOf(String.valueOf(this.c(480))).concat("."));
                    ji.util.e.ag(this.c(480));
                    af.a(new a6(this, 9, ""));
                }
                if (!this.b9) {
                    try {
                        ad.i();
                    }
                    catch (Exception ex4) {}
                    if (this.ci != null) {
                        this.ci.a(df, this.eq);
                    }
                }
            }
            else {
                this.b9 = true;
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Send aborted");
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.ee = String.valueOf(String.valueOf(this.ee)).concat(String.valueOf(String.valueOf(ex.toString())));
            this.b9 = true;
            b3 = false;
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, "Annotation send failed...");
            }
            if (af != null) {
                af.a(new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.e("annotSaveFailed", this.ed))).concat(".")));
            }
            try {
                ji.io.h.d(this.ed, url.toString());
            }
            catch (Exception ex5) {}
            try {
                if (bh != null) {
                    final String a8 = ji.net.bi.a(bh.a(), this.ed);
                    ji.io.h.d(this.ed, a8);
                    if (ji.net.bi.a()) {
                        ji.util.d.l(false);
                        ji.util.d.a(this.c(479), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.c(486)))).append("#1: ").append(a8))), null, 120, null, null, this.ed);
                    }
                }
            }
            catch (Exception ex6) {}
            finally {
                ji.util.d.l(true);
            }
        }
        finally {
            this.a(af, 0);
            try {
                bh.e();
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        if (b3) {
            ad.a(this, 24, "annotationsave: ok", 1, 1, 0, 0);
        }
        else {
            ad.a(this, 25, "annotationsave: failed", 1, 1, 0, 0);
        }
        return b3;
    }
    
    private final boolean a(final String s, final String s2, final df df) {
        final int d = df.d();
        boolean b = false;
        if (ji.util.d.cs()) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Replacing ID:").append(s).append(" with:").append(s2))));
        }
        if (d > 0) {
            final ax g = df.g();
            while (g.a() && !b) {
                final dg b2 = df.b(g.b());
                if (b2 != null) {
                    final String dw = b2.dw();
                    if (dw == null || !dw.equals(s)) {
                        continue;
                    }
                    b2.r(s2);
                    b = true;
                }
            }
        }
        return b;
    }
    
    private final boolean a(final df df, final URL url, String s, final Component component, final ad ad, final af af, final String s2, final boolean[] array, final boolean[] array2) {
        String a = null;
        boolean a2 = true;
        if (this.cg != null) {
            try {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.ed, "Sending annotations");
                }
                this.a(af, String.valueOf(String.valueOf(this.c(487))).concat("..."));
                a = this.a(df, ad, true, null, array, s2, ad.dx(), array2, ad.bi(12));
                if (ad.bi(17) && !ad.bi(12)) {
                    ji.io.h.a(this.ed, "Annotation Data not gzipped by the annotationGZip parameter when using annotationSave parameter.");
                }
                if (s == null) {
                    s = "-";
                }
                a2 = ji.util.bh.a(url.toString(), a, this.ed, af, this, ad);
            }
            catch (Exception ex) {
                ex.printStackTrace();
                a2 = false;
            }
            try {
                if (this.ch != null && a != null) {
                    this.ch.d(a);
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        else {
            this.b9 = true;
            this.ee = String.valueOf(String.valueOf(this.ee)).concat("Send aborted");
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, this.ee);
            }
        }
        return a2;
    }
    
    public final byte[] a(final df df, final Component component, final ad ad, final boolean b, final boolean[] array, String bm, final boolean b2, final boolean b3, final boolean[] array2) throws Exception {
        if (bm == null) {
            bm = ji.util.d.bm(this.ed);
        }
        ji.annotate.dy.d9 = bm;
        this.t();
        this.ci.a(0, this.ed);
        boolean b4 = false;
        byte[] array3 = null;
        if (ji.util.d.a(ad)) {
            final Vector j = ad.j();
            if (j != null) {
                final int size = j.size();
                if (size > 0) {
                    b4 = true;
                    final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                    for (int i = 0; i < size; ++i) {
                        hashtable.put(j.elementAt(i), j.elementAt(i));
                    }
                    final Enumeration<String> keys = hashtable.keys();
                    final df df2 = new df(this.ed);
                    while (keys.hasMoreElements()) {
                        final int c = ji.util.d.c(hashtable.get(keys.nextElement()), 1);
                        if (ji.annotate.b8.c(c, this.ed, ad, false)) {
                            if (ji.util.d.cs()) {
                                ji.io.h.d(this.ed, "Sending update for annotations on page: ".concat(String.valueOf(String.valueOf(c))));
                            }
                            if (!ji.annotate.b8.a(c, ad.jf(), df2, this.ed, component, ad) || df2.c(c) != 0) {
                                continue;
                            }
                            final dg dg = new dg("Remove", this.ed, this.eq, ad.hc());
                            dg.a(ad);
                            dg.aa(18);
                            dg.j(c);
                            dg.d(1);
                            df2.a(dg);
                        }
                    }
                    df2.d(true);
                    array3 = this.ci.a(this.ed, df2, component, ad, b, array, bm, b2, b3 && ad.af(), this.eq, this.d, array2, false, false);
                }
            }
        }
        if (!b4) {
            array3 = this.ci.a(this.ed, df, component, ad, b, array, bm, b2, b3 && ad.af(), this.eq, this.d, array2, false, false);
        }
        if (ji.util.d.cs()) {
            final String s = new String(array3, bm);
            if (ji.util.d.bf() && ad.af()) {
                ji.io.h.d(this.ed, "Sending Unescape for Reference:\n".concat(String.valueOf(String.valueOf(ji.util.d.cs(s)))));
            }
            else {
                ji.io.h.d(this.ed, "Sending:\n".concat(String.valueOf(String.valueOf(s))));
            }
        }
        return array3;
    }
    
    public final String a(final df df, final ad ad, final boolean b, String n, final boolean[] array, final String s, final boolean b2, final boolean[] array2, final boolean b3) throws Exception {
        byte[] array3 = this.a(df, ad, ad, b, array, s, b2, false, array2);
        if (array3 != null) {
            if (b3) {
                array3 = ji.zip.a4.a(array3);
                if (ji.util.i.c(253)) {
                    ji.io.h.d(this.ed, "Annotation Data saved to file is being gzipped.");
                    ji.io.h.d(this.ed, "GZipped Data: \n".concat(String.valueOf(String.valueOf(new String(array3)))));
                }
            }
            if (n == null) {
                if (this.ch == null) {
                    this.ch = ji.io.q.a(ad, this.ed);
                }
                n = this.ch.n();
            }
            if (ji.io.ac.d(n, this.ed)) {
                ji.io.ac.c(n, this.ed);
            }
            final ac ac = new ac(n, true, false, 0, false, ad, this.ed);
            ac.b(array3);
            ac.a(ad);
            return n;
        }
        return null;
    }
    
    private final void am() {
        try {
            if (this.cg != null) {
                this.cg.c();
                this.cg = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.ch != null) {
                this.ch = null;
            }
        }
        catch (Exception ex2) {}
    }
    
    private final Rectangle a(final String[] array, final int n, final String s, final String s2, final String s3, final String s4, final int n2, final String s5) {
        final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
        rectangle.x = this.a(this.a(s, array, n + 1, true, n2, s5), s, n2);
        rectangle.y = this.a(this.a(s2, array, n + 1, true, n2, s5), s2, n2);
        rectangle.width = this.a(this.a(s3, array, n + 1, true, n2, s5), s3, n2);
        rectangle.height = this.a(this.a(s4, array, n + 1, true, n2, s5), s4, n2);
        return rectangle;
    }
    
    private final Dimension a(final String[] array, final int n, final String s, final String s2, final int n2, final String s3) {
        final Dimension dimension = new Dimension(0, 0);
        dimension.width = this.a(this.a(s, array, n + 1, true, n2, s3), s, n2);
        dimension.height = this.a(this.a(s2, array, n + 1, true, n2, s3), s2, n2);
        return dimension;
    }
    
    private final c a(final String[] array, final int n, final int n2, final String s) {
        final c c = new c("jiAnnotate7");
        int n3 = 1;
        while (true) {
            final String concat = "x".concat(String.valueOf(String.valueOf(n3)));
            final String concat2 = "y".concat(String.valueOf(String.valueOf(n3)));
            if (ji.util.d.by(this.a(concat, array, n + 1, false, n2, s))) {
                break;
            }
            c.c(this.eb);
            if (ji.util.d.by(this.a(concat2, array, n + 1, false, n2, s))) {
                break;
            }
            c.c(this.eb);
            ++n3;
        }
        return c;
    }
    
    private final c d(final String s) {
        final c c = new c("jiAnnotate7a");
        try {
            final b4 b4 = new b4(s, " ");
            if (b4.a() > 0) {
                boolean b5 = true;
                int n = 1;
                while (b4.b()) {
                    final String c2 = b4.c();
                    if (b5) {
                        c.c(String.valueOf(String.valueOf(new StringBuffer("X").append(n).append(" = ").append(c2))));
                    }
                    else {
                        c.c(String.valueOf(String.valueOf(new StringBuffer("Y").append(n).append(" = ").append(c2))));
                        ++n;
                    }
                    b5 = !b5;
                }
            }
        }
        catch (Exception ex) {}
        return c;
    }
    
    private final String[] b(final String[] array, final int n, final int n2, final String s) {
        final c c = new c("jiAnnotate7a");
        int eg = n;
        while (true) {
            final String a = this.a("customproperty", array, eg + 1, false, true, n2, s);
            if (ji.util.d.by(a)) {
                break;
            }
            c.c(a);
            eg = this.eg;
        }
        final String[] array2 = new String[c.b()];
        for (int i = 0; i < c.b(); ++i) {
            array2[i] = (String)c.b(i);
        }
        return array2;
    }
    
    private final dh a(final String[] array, final int n, final int n2, final dh dh, final String s) {
        final dh o = dh.o();
        final String s2 = "fnedit";
        final String a = this.a(s2, array, n + 1, true, n2, s);
        final boolean b = a != null && this.d(a, s2, n2);
        if (ji.util.d.cs()) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s2))).append(" = ").append(b))));
        }
        final String s3 = "fneditsecurity";
        final String a2 = this.a(s3, array, n + 1, true, n2, s);
        boolean b2;
        if (a2 == null) {
            b2 = ji.secure.ei.n;
        }
        else {
            b2 = this.d(a2, s3, n2);
        }
        if (ji.util.d.cs()) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s3))).append(" = ").append(b2))));
        }
        final String s4 = "fndelete";
        final String a3 = this.a(s4, array, n + 1, true, n2, s);
        boolean b3;
        if (a3 == null) {
            b3 = ji.secure.ei.o;
        }
        else {
            b3 = this.d(a3, s4, n2);
        }
        if (ji.util.d.cs()) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s4))).append(" = ").append(b3))));
        }
        o.a(b, b2, b3);
        return o;
    }
    
    private final ei[] a(final String s, final String[] array, final int n, final int n2, final String s2) {
        final c c = new c("jiAnnotate7b");
        int eg = n;
        final boolean b = false;
        while (true) {
            final String concat = String.valueOf(String.valueOf(s)).concat("permissionid");
            final String a = this.a(concat, array, eg + 1, false, true, n2, s2);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(" = ").append(a))));
            }
            if (ji.util.d.by(a)) {
                break;
            }
            final String concat2 = String.valueOf(String.valueOf(s)).concat("permissionname");
            final String a2 = this.a(concat2, array, eg + 1, false, true, n2, s2);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat2))).append(" = ").append(a2))));
            }
            if (ji.util.d.by(a2)) {
                break;
            }
            final String concat3 = String.valueOf(String.valueOf(s)).concat("permissiontype");
            final String a3 = this.a(concat3, array, eg + 1, false, true, n2, s2);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat3))).append(" = ").append(a3))));
            }
            if (ji.util.d.by(a3)) {
                break;
            }
            final String concat4 = String.valueOf(String.valueOf(s)).concat("permissionlevel");
            final String a4 = this.a(concat4, array, eg + 1, false, true, n2, s2);
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat4))).append(" = ").append(a4))));
            }
            if (ji.util.d.by(a4)) {
                break;
            }
            final ei ei = new ei(a2, a, this.e(a3), a4, 0);
            if (b) {
                break;
            }
            c.c(ei);
            eg = this.eg;
        }
        final ei[] array2 = new ei[c.b()];
        for (int i = 0; i < c.b(); ++i) {
            array2[i] = (ei)c.b(i);
        }
        return array2;
    }
    
    private final String b(final String s, final String[] array, final int n, final boolean b, final int n2, final String s2) {
        if (!ji.util.d.by(this.a(s, array, n, b, n2, s2))) {
            return this.eb;
        }
        return "";
    }
    
    private final d4 c(final String[] array, final int n, final int n2, final String s) {
        final d4 d4 = new d4();
        int n3 = 1;
        while (true) {
            final String concat = "x".concat(String.valueOf(String.valueOf(n3)));
            final String concat2 = "y".concat(String.valueOf(String.valueOf(n3)));
            final String a = this.a(concat, array, n + 1, false, n2, s);
            final String a2 = this.a(concat2, array, n + 1, false, n2, s);
            if (ji.util.d.by(a)) {
                break;
            }
            if (ji.util.d.by(a2)) {
                break;
            }
            d4.addPoint(this.a(a, concat, n2), this.a(a2, concat2, n2));
            ++n3;
        }
        return d4;
    }
    
    public final df a(final String s, final ad ad, final af af, final String s2, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) throws Exception {
        final long currentTimeMillis = System.currentTimeMillis();
        final String[] a = this.a(s, ad, s2, b3, b5);
        if (ji.util.d.dr() || ji.util.i.c(89)) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a4))).append(": Read annotations in ").append(System.currentTimeMillis() - currentTimeMillis).append(" milliseconds"))));
        }
        ji.io.h.a(this.ed, String.valueOf(String.valueOf(new StringBuffer("Parsing annotations :  ").append(ji.util.d.cd(s)).append("\n"))));
        final long currentTimeMillis2 = System.currentTimeMillis();
        df a2 = null;
        ad.av((String)null);
        if (a != null) {
            a2 = this.a(ad, af, s2, a, true, b, b2);
        }
        if (ji.util.d.dr() || ji.util.i.c(89)) {
            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a4))).append(": Parsed annotations in ").append(System.currentTimeMillis() - currentTimeMillis2).append(" milliseconds"))));
        }
        return a2;
    }
    
    public final df a(String b, final ad ad, final af af, final String s, final boolean b2) throws Exception {
        b = ji.util.d.b(b, ji.util.d.a3(), "\r\n");
        return this.a(ad, af, s, ji.util.d.a(b.getBytes(), ad, s, "JS", s, true), false, b2, false);
    }
    
    private final int e(final String s) {
        int n = 0;
        if (s != null && s.toLowerCase().startsWith("group")) {
            n = 1;
        }
        return n;
    }
    
    public final df a(final ad ad, final af af, final String s, String[] array, final boolean b, final boolean b2, final boolean b3) throws Exception {
        df df = null;
        ad.av((String)null);
        final boolean al = ji.util.d.al(this.eq);
        final boolean am = ji.util.d.am(this.eq);
        boolean b4 = false;
        boolean b5 = false;
        if (array != null) {
            final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
            final Dimension dimension = new Dimension(0, 0);
            int n = 0;
            boolean b6 = false;
            Color color = null;
            int a = 0;
            String s2 = null;
            int a2 = 0;
            boolean b7 = ji.util.d.i7 || ji.util.i.c(234);
            Color color2 = ji.util.d.i8;
            String s3 = ji.util.d.ja;
            boolean d = false;
            int a3 = -1;
            boolean d2 = false;
            String trim = null;
            final boolean b8 = true;
            final long currentTimeMillis = System.currentTimeMillis();
            if (ji.util.i.c(253)) {
                ji.io.h.d(this.ed, "");
                ji.io.h.d(this.ed, "Original Annotation Data:");
                if (array != null) {
                    for (int i = 0; i < array.length; ++i) {
                        if (array[i] != null) {
                            ji.io.h.d(this.ed, array[i]);
                        }
                    }
                }
                ji.io.h.d(this.ed, "End Original Annotation Data");
                ji.io.h.d(this.ed, "");
                ji.io.h.d(this.ed, "");
            }
            try {
                if (array.length > 0 && array[0] != null && !ji.util.d.by(array[0])) {
                    final int min = Math.min(array[0].length(), 6);
                    boolean b9 = true;
                    for (int j = 0; j < min; ++j) {
                        final char c = array[0].toLowerCase().toCharArray()[j];
                        if ((c < '0' || c > '9') && (c < 'a' || c > 'z')) {
                            b9 = false;
                            break;
                        }
                    }
                    if (b9) {
                        final c c2 = new c("jiAnnotate8");
                        for (int k = 0; k < array.length; ++k) {
                            final String s4 = array[k];
                            String concat = "";
                            String concat2 = "";
                            for (int l = 0; l < s4.length(); l += 2) {
                                final String substring = s4.substring(l, l + 2);
                                concat2 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(substring)));
                                final int intValue = Integer.valueOf(substring, 16);
                                if (intValue == 13 || intValue == 10) {
                                    if (concat != null) {
                                        final String bd = ji.util.d.bd(ji.util.d.bc(concat));
                                        if (!ji.util.d.by(bd)) {
                                            c2.c(bd);
                                        }
                                    }
                                    concat = "";
                                    concat2 = "";
                                }
                                else {
                                    concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf((char)intValue)));
                                }
                            }
                            if (concat != null && concat.length() > 0) {
                                final String bd2 = ji.util.d.bd(ji.util.d.bc(concat));
                                if (!ji.util.d.by(bd2)) {
                                    c2.c(bd2);
                                }
                                c2.c(bd2);
                            }
                        }
                        if (c2.b() > 0) {
                            array = new String[c2.b()];
                            for (int n2 = 0; n2 < c2.b(); ++n2) {
                                final String bd3 = ji.util.d.bd(ji.util.d.bc((String)c2.b(n2)));
                                if (!ji.util.d.by(bd3) && !bd3.startsWith("//")) {
                                    array[n2] = bd3;
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            int n3 = 0;
            for (int n4 = 0; n4 < array.length; ++n4) {
                if (!ji.util.d.by(array[n4])) {
                    array[n4] = ji.util.d.a5(array[n4]);
                    if (n3 == 0 && array[n4].indexOf("[") >= 0) {
                        n3 = 1;
                        if (n4 > 0) {
                            for (int n5 = 0; n5 < n4; ++n5) {
                                array[n5] = null;
                            }
                        }
                    }
                }
            }
            final Vector vector = new Vector<Object>(Math.max(array.length / 5, 10));
            boolean b10 = false;
            boolean b11 = true;
            final int length = array.length;
            int n6 = 0;
            int max;
            final int n7 = max = Math.max(length / 50, 1);
            for (int n8 = 0; n8 < array.length; ++n8) {
                final int n9 = n8;
                try {
                    if (!ji.util.d.by(array[n8])) {
                        String h = "";
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, " \n");
                        }
                        final String c3 = ji.util.d.c(array[n8], true);
                        if (ji.util.d.cs()) {
                            ji.io.h.d(this.ed, "Type:".concat(String.valueOf(String.valueOf(c3))));
                        }
                        dg dg = null;
                        if (c3 != null) {
                            String s5 = c3.toLowerCase(Locale.ENGLISH);
                            this.cf = null;
                            boolean b12 = false;
                            try {
                                Color color3 = null;
                                color = Color.black;
                                if (ji.util.d.a2()) {
                                    color = ji.util.d.n(color);
                                }
                                a3 = -1;
                                final String a4 = this.a("page", array, n8 + 1, false, -1, s);
                                if (a4 != null && this.es != null) {
                                    a3 = this.a(a4, "page", -1);
                                }
                                d2 = false;
                                final String a5 = this.a("template", array, n8 + 1, false, 0, s);
                                if (!ji.util.d.by(a5)) {
                                    d2 = this.d(a5, "template", 0);
                                }
                                final String a6 = this.a("fillcolor", array, n8 + 1, false, a3, s);
                                if (!ji.util.d.by(a6)) {
                                    color3 = this.a(a6, "fillcolor", ad, a3);
                                    if (ji.util.d.a2()) {
                                        color3 = ji.util.d.n(color3);
                                    }
                                }
                                final String a7 = this.a("color", array, n8 + 1, false, a3, s);
                                if (!ji.util.d.by(a7)) {
                                    color = this.a(a7, "color", ad, a3);
                                    if (ji.util.d.a2()) {
                                        color = ji.util.d.n(color);
                                    }
                                }
                                final String a8 = this.a("linewidth", array, n8 + 1, false, a3, s);
                                if (a8 != null) {
                                    a = this.a(a8, "linewidth", a3);
                                }
                                final int index = s5.indexOf("[");
                                if (index > 0) {
                                    s5 = s5.substring(index);
                                }
                                b7 = ji.util.d.i7;
                                color2 = ji.util.d.i8;
                                s3 = ji.util.d.ja;
                                d = false;
                                final String a9 = this.a("SELECTABLE", array, n8 + 1, false, a3, s);
                                if (a9 != null) {
                                    b7 = this.d(a9, "SELECTABLE", a3);
                                }
                                final String a10 = this.a("BLANKOUTIMAGE", array, n8 + 1, false, a3, s);
                                if (a10 != null) {
                                    d = this.d(a10, "BLANKOUTIMAGE", a3);
                                }
                                final String a11 = this.a("SELECTCOLOR", array, n8 + 1, false, a3, s);
                                if (a11 != null) {
                                    color2 = this.a(a11, "SELECTCOLOR", ad, a3);
                                }
                                final String a12 = this.a("SELECTOR", array, n8 + 1, false, a3, s);
                                if (a12 != null) {
                                    ji.io.h.a(this.ed, "SELECTOR = ".concat(String.valueOf(String.valueOf(a12))));
                                    s3 = a12;
                                }
                                if (al) {
                                    final String a13 = this.a("CUSTOMBYTES", array, n8 + 1, false, a3, s);
                                    if (a13 != null) {
                                        ji.io.h.a(this.ed, "CUSTOMBYTES = ".concat(String.valueOf(String.valueOf(a13))));
                                        h = this.h(a13);
                                    }
                                }
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(this.ed, "Annotation: FN0..: ".concat(String.valueOf(String.valueOf(s5))));
                                }
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(this.ed, "Annotation: FN0A...".concat(String.valueOf(String.valueOf(ji.util.d.bf()))));
                                }
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(this.ed, "Annotation: FN0B...".concat(String.valueOf(String.valueOf(ad.af()))));
                                }
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(this.ed, "Annotation: FN0C...".concat(String.valueOf(String.valueOf(am))));
                                }
                                if (this.f(s5)) {
                                    if (ji.util.d.cs()) {
                                        ji.io.h.d(this.ed, "Version section");
                                    }
                                    final String a14 = this.a("xslversion", array, n8 + 1, false, a3, s);
                                    if (a14 != null) {
                                        a2 = this.a(a14, "xslversion", a3);
                                    }
                                    b11 = false;
                                }
                                else if (this.a(s5, 18)) {
                                    ji.io.h.a(this.ed, "Remove section");
                                    b11 = false;
                                }
                                else if (this.a(s5, 16)) {
                                    if (ji.util.d.cs()) {
                                        ji.io.h.d(this.ed, "Custom section");
                                    }
                                    final String[] a15 = this.a(array, n8, a3);
                                    dg = new dg("Custom", this.ed, this.eq, ad.hc());
                                    dg.a(ad);
                                    dg.a(16, a15);
                                    b11 = false;
                                }
                                else if (this.a(s5, 17)) {
                                    if (ji.util.d.cs()) {
                                        ji.io.h.d(this.ed, "Document section");
                                    }
                                    final String[] a16 = this.a(array, n8, a3);
                                    boolean b13 = false;
                                    int n10 = 0;
                                    while (n10 < a16.length) {
                                        if (a16[n10].startsWith("ID")) {
                                            if (a16[n10].length() > 3) {
                                                trim = a16[n10].substring(4).trim();
                                            }
                                            else {
                                                trim = "";
                                            }
                                            b13 = true;
                                            if (ji.util.d.cs()) {
                                                ji.io.h.d(this.ed, "Document ID = ".concat(String.valueOf(String.valueOf(trim))));
                                                break;
                                            }
                                            break;
                                        }
                                        else {
                                            ++n10;
                                        }
                                    }
                                    if (!b13) {
                                        b4 = true;
                                        this.cf = "No Document ID Tag present";
                                    }
                                    dg = new dg("Document", this.ed, this.eq, ad.hc());
                                    dg.a(ad);
                                    dg.a(17, a16);
                                    if (ji.util.d.bf() && ad.af()) {
                                        if (this.eq == 0) {
                                            final String a17 = this.a("fndefaultclientpermission", array, n8 + 1, false, true, a3, s);
                                            if (a17 != null) {
                                                ji.io.h.a(this.ed, "fndefaultclientpermission = ".concat(String.valueOf(String.valueOf(a17))));
                                                ji.secure.ei.a = a17;
                                            }
                                            final String a18 = this.a("fndefaultread", array, n8 + 1, false, true, a3, s);
                                            if (a18 != null) {
                                                ji.io.h.a(this.ed, "fndefaultread = ".concat(String.valueOf(String.valueOf(a18))));
                                                ji.secure.ei.b = a18;
                                            }
                                            final String a19 = this.a("fndefaultreadtype", array, n8 + 1, false, true, a3, s);
                                            if (a19 != null) {
                                                ji.io.h.a(this.ed, "fndefaultreadtype = ".concat(String.valueOf(String.valueOf(a19))));
                                                ji.secure.ei.c = this.e(a19);
                                            }
                                            final String a20 = this.a("fndefaultwrite", array, n8 + 1, false, true, a3, s);
                                            if (a20 != null) {
                                                ji.io.h.a(this.ed, "fndefaultwrite = ".concat(String.valueOf(String.valueOf(a20))));
                                                ji.secure.ei.d = a20;
                                            }
                                            final String a21 = this.a("fndefaultwritetype", array, n8 + 1, false, true, a3, s);
                                            if (a21 != null) {
                                                ji.io.h.a(this.ed, "fndefaultwritetype = ".concat(String.valueOf(String.valueOf(a21))));
                                                ji.secure.ei.e = this.e(a21);
                                            }
                                            final String a22 = this.a("fndefaultappend", array, n8 + 1, false, true, a3, s);
                                            if (a22 != null) {
                                                ji.io.h.a(this.ed, "fndefaultappend = ".concat(String.valueOf(String.valueOf(a22))));
                                                ji.secure.ei.f = a22;
                                            }
                                            final String a23 = this.a("fndefaultappendtype", array, n8 + 1, false, true, a3, s);
                                            if (a23 != null) {
                                                ji.io.h.a(this.ed, "fndefaultappendtype = ".concat(String.valueOf(String.valueOf(a23))));
                                                ji.secure.ei.g = this.e(a23);
                                            }
                                        }
                                        else if (ji.util.d.am(this.eq)) {
                                            final String a24 = this.a("fndefaulteditsecurity", array, n8 + 1, false, true, a3, s);
                                            if (a24 != null) {
                                                ji.secure.ei.n = this.d(a24, "fndefaulteditsecurity", a3);
                                            }
                                            final String a25 = this.a("fndefaultdelete", array, n8 + 1, false, true, a3, s);
                                            if (a25 != null) {
                                                ji.secure.ei.o = this.d(a25, "fndefaultdelete", a3);
                                            }
                                        }
                                        else {
                                            ji.util.d.a(this.a("fndefault", array, n8 + 1, a3, s));
                                            final String a26 = this.a("fndefaultclientpermission", array, n8 + 1, false, true, a3, s);
                                            if (a26 != null) {
                                                ji.io.h.a(this.ed, "fndefaultclientpermission = ".concat(String.valueOf(String.valueOf(a26))));
                                                ji.secure.ei.a = a26;
                                            }
                                            if (ji.secure.ei.a.toLowerCase().equals("admin".toLowerCase())) {
                                                ad.ds(true);
                                            }
                                            else {
                                                ad.ds(false);
                                            }
                                        }
                                    }
                                    b11 = false;
                                }
                                else {
                                    if (this.g(s5)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Empty annotations file");
                                        }
                                        return null;
                                    }
                                    if (this.a(s5, 4)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: LINE...");
                                        }
                                        Rectangle a27;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a27 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a27 = this.a(array, n8, "x1", "y1", "x2", "y2", a3, s);
                                        }
                                        dg = new dg("Line", this.ed, this.eq, ad.hc());
                                        dg.a(4, a27.x, a27.y, a27.width, a27.height);
                                        dg.a(ad);
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x1", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y1", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("x2", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y2", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 5)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: ARROW...");
                                        }
                                        Rectangle a28;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a28 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a28 = this.a(array, n8, "x1", "y1", "x2", "y2", a3, s);
                                        }
                                        if (!ji.util.i.c(204) && ji.util.d.bf() && !am) {
                                            a *= (int)2.0;
                                        }
                                        dg = new dg("Arrow", this.ed, this.eq, ad.hc());
                                        dg.a(5, a28.x, a28.y, a28.width, a28.height);
                                        dg.a(ad);
                                        final String a29 = this.a("arrowheadsize", array, n8 + 1, false, a3, s);
                                        if (a29 != null) {
                                            dg.t(this.a(a29, "arrowheadsize", a3));
                                        }
                                        if (color3 != null) {
                                            dg.d(color3);
                                        }
                                        else if (color != null) {
                                            dg.d(color);
                                        }
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x1", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y1", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("x2", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y2", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 13)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: HIGHLIGHT...");
                                        }
                                        Rectangle a30;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a30 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a30 = this.a(array, n8, "x", "y", "width", "height", a3, s);
                                        }
                                        dg = new dg("Highlight", true, color3, this.ed, this.eq, ad.hc());
                                        dg.a(ad);
                                        dg.a(13, a30.x, a30.y, a30.width, a30.height);
                                        dg.c(color3);
                                        if (color3 != null) {
                                            dg.au(true);
                                        }
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 1)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: RECTANGLE...");
                                        }
                                        Rectangle a31;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a31 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a31 = this.a(array, n8, "x", "y", "width", "height", a3, s);
                                        }
                                        if (color3 == null) {
                                            dg = new dg("Rectangle", false, null, this.ed, this.eq, ad.hc());
                                        }
                                        else {
                                            dg = new dg("Rectangle", true, color3, this.ed, this.eq, ad.hc());
                                        }
                                        dg.a(1, a31.x, a31.y, a31.width, a31.height);
                                        dg.a(ad);
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 11)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: REDACT...");
                                        }
                                        Rectangle a32;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a32 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a32 = this.a(array, n8, "x", "y", "width", "height", a3, s);
                                        }
                                        if (color3 == null) {
                                            color3 = ji.util.d.cq();
                                            if (ji.util.d.a2()) {
                                                color3 = ji.util.d.n(color3);
                                            }
                                        }
                                        dg = new dg("Redact", true, color3, this.ed, this.eq, ad.hc());
                                        dg.a(11, a32.x, a32.y, a32.width, a32.height);
                                        dg.a(ad);
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 8)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: NOTE...");
                                        }
                                        Rectangle a33;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a33 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a33 = this.a(array, n8, "x", "y", "width", "height", a3, s);
                                        }
                                        final String a34 = this.a("text", array, n8 + 1, false, true, 0, s);
                                        String g;
                                        if (a34 != null) {
                                            g = this.g(a34, "text", 0);
                                        }
                                        else {
                                            g = "";
                                        }
                                        final String cc = ji.util.d.cc(g);
                                        if (color3 == null) {
                                            color3 = ji.util.d.cl();
                                            if (ji.util.d.a2()) {
                                                color3 = ji.util.d.n(color3);
                                            }
                                        }
                                        final int g2 = ji.util.d.g(this.ed);
                                        dg = new dg("Note", true, color3, this.ed, this.eq, ad.hc());
                                        dg.a(ad);
                                        boolean b14 = ji.util.d.bl();
                                        if (!am) {
                                            final String a35 = this.a("rectangular", array, n8 + 1, false, a3, s);
                                            if (a35 != null) {
                                                b14 = this.d(a35, "rectangular", a3);
                                            }
                                        }
                                        dg.a(8, a33.x + a33.width / 2, a33.y + a33.height / 2, a33.width, a33.height, cc, b14);
                                        final String a36 = this.a("noteid", array, n8 + 1, false, false, a3, s);
                                        if (a36 != null) {
                                            ji.io.h.a(this.ed, "noteid = ".concat(String.valueOf(String.valueOf(a36))));
                                            dg.a(a36);
                                            ji.util.d.a(this.ed, g2);
                                            if (!ji.util.d.bf()) {
                                                final int g3 = ji.util.d.g(this.ed);
                                                if (ji.util.d.bb(a36)) {
                                                    final int c4 = ji.util.d.c(a36, g3);
                                                    if (g3 < c4) {
                                                        ji.util.d.a(this.ed, c4);
                                                    }
                                                }
                                            }
                                        }
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 2)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: OVAL...");
                                        }
                                        Rectangle a37;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a37 = new Rectangle(0, 0, 10000, 10000);
                                        }
                                        else {
                                            a37 = this.a(array, n8, "x", "y", "width", "height", a3, s);
                                        }
                                        if (color3 == null) {
                                            dg = new dg("Oval", false, null, this.ed, this.eq, ad.hc());
                                        }
                                        else {
                                            dg = new dg("Oval", true, color3, this.ed, this.eq, ad.hc());
                                        }
                                        dg.a(ad);
                                        dg.a(2, a37.x, a37.y, a37.width, a37.height);
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 7)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: TEXT...");
                                        }
                                        Dimension a38;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a38 = new Dimension(0, 0);
                                        }
                                        else {
                                            a38 = this.a(array, n8, "x", "y", a3, s);
                                        }
                                        final String a39 = this.a("text", array, n8 + 1, false, true, 0, s);
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Text1 encoding = ".concat(String.valueOf(String.valueOf(s))));
                                        }
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Text1 = ".concat(String.valueOf(String.valueOf(a39))));
                                        }
                                        String g4;
                                        if (a39 != null) {
                                            g4 = this.g(a39, "text", 0);
                                        }
                                        else {
                                            g4 = "";
                                        }
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Text2 = ".concat(String.valueOf(String.valueOf(a39))));
                                        }
                                        final String b15 = ji.util.d.b(g4, "<n>", "\n");
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Text3 = ".concat(String.valueOf(String.valueOf(a39))));
                                        }
                                        int n11 = Math.max(this.a(this.a("fontheight", array, n8 + 1, true, false, a3, s), "fontheight", a3), 1);
                                        double n12 = 1.0;
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: FN8...".concat(String.valueOf(String.valueOf(color3))));
                                        }
                                        if (color3 != null) {
                                            dg = new dg("Text", ji.util.d.cn(), ji.util.d.w(), true, color3, this.ed, this.eq, ad.hc());
                                        }
                                        else {
                                            dg = new dg("Text", ji.util.d.cn(), ji.util.d.w(), this.ed, this.eq, ad.hc());
                                        }
                                        dg.a(s2, 7, 0, b15, a38.width, a38.height, false);
                                        dg.a(ad);
                                        dg.ab(n11);
                                        if (ji.util.d.bf()) {
                                            final String a40 = this.a("fnstamp", array, n8 + 1, false, a3, s);
                                            if (a40 != null && this.d(a40, "fnstamp", a3)) {
                                                dg.v(true);
                                            }
                                        }
                                        final String a41 = this.a("scale", array, n8 + 1, false, a3, s);
                                        if (!ji.util.d.by(a41)) {
                                            dg.b(this.c(a41, "scale", a3));
                                        }
                                        String s6 = this.a("fontspec", array, n8 + 1, false, a3, s);
                                        String a42 = null;
                                        String s7 = null;
                                        if (ji.util.i.c(247)) {
                                            a42 = this.a("fontsource", array, n8 + 1, false, true, a3, s);
                                            if (ji.util.d.cs()) {
                                                ji.io.h.d(this.ed, "FONTSOURCE = ".concat(String.valueOf(String.valueOf(a42))));
                                            }
                                        }
                                        if (ji.util.d.by(s6) && ji.util.i.c(247)) {
                                            s6 = this.a("fonttype", array, n8 + 1, false, true, a3, s);
                                            if (!ji.util.d.by(s6)) {
                                                n11 = n11 * 95 / 100;
                                                s6 = (s7 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s6))).append(", ").append(1))));
                                            }
                                        }
                                        else if (!ji.util.d.by(s6) && ji.util.i.c(247)) {
                                            s7 = this.a("fontspec", array, n8 + 1, false, true, a3, s);
                                        }
                                        if (!ji.util.d.by(s6) && ji.util.i.c(247)) {
                                            boolean b16 = false;
                                            final StringTokenizer stringTokenizer = new StringTokenizer(s7, ",");
                                            if (stringTokenizer.countTokens() >= 2) {
                                                dg.j(s7);
                                                String s8 = ji.util.d.bc(stringTokenizer.nextToken());
                                                final int c5 = ji.util.d.c(ji.util.d.bc(stringTokenizer.nextToken()), 0);
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "FONTSPEC = ".concat(String.valueOf(String.valueOf(s7))));
                                                }
                                                if (ji.util.d.by(a42)) {
                                                    s8 = ji.font.j.a(this.ed, s8, ad);
                                                }
                                                n11 = Math.min(n11, 667);
                                                Font a43 = null;
                                                if (!ji.util.d.by(a42) && ji.util.e.av()) {
                                                    try {
                                                        final String a44 = ji.font.j.a(ad, a42, af, this.ed);
                                                        if (a44 != null) {
                                                            a43 = ji.font.j.a(a44, c5, n11, ad, this.ed);
                                                            if (a43 != null) {
                                                                final String family = a43.getFamily();
                                                                boolean b17 = false;
                                                                if (s8 == null) {
                                                                    b17 = true;
                                                                }
                                                                else if (!family.equals(s8)) {
                                                                    b17 = true;
                                                                }
                                                                if (b17) {
                                                                    s8 = a43.getFamily();
                                                                    dg.j((String)null);
                                                                    dg.d(1);
                                                                }
                                                                dg.c(a42);
                                                            }
                                                        }
                                                        else {
                                                            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("ERROR: Unable to find font file, reverting to font name: '").append(s8).append("'"))));
                                                        }
                                                    }
                                                    catch (Exception ex2) {
                                                        ex2.printStackTrace();
                                                    }
                                                }
                                                if (a43 == null) {
                                                    a43 = new Font(s8, c5, n11);
                                                }
                                                b16 = true;
                                                boolean g5 = false;
                                                boolean h2 = false;
                                                final String a45 = this.a("strikethrough", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a45) && this.d(a45, "strikethrough", a3)) {
                                                    dg.h(true);
                                                    g5 = true;
                                                }
                                                final String a46 = this.a("underline", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a46) && this.d(a46, "underline", a3)) {
                                                    dg.i(true);
                                                    h2 = true;
                                                }
                                                final ct ct = new ct(s8, n11, a43.isBold(), a43.isItalic(), a43.isPlain(), a43);
                                                ct.c = a43.getFamily();
                                                ct.a = a43.getSize();
                                                ct.b = a43.getSize();
                                                ct.d = a43.isBold();
                                                ct.e = a43.isItalic();
                                                ct.f = a43.isPlain();
                                                ct.h = h2;
                                                ct.g = g5;
                                                ct.j = true;
                                                dg.a(ct);
                                            }
                                            else {
                                                ji.io.h.d(this.ed, "Annotation font error! (incorrect format spec - need 'name' and 'style' properties): ".concat(String.valueOf(String.valueOf(s6))));
                                            }
                                            if (!b16) {
                                                final String a47 = this.a("strikethrough", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a47) && this.d(a47, "strikethrough", a3)) {
                                                    dg.h(true);
                                                }
                                                final String a48 = this.a("underline", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a48) && this.d(a48, "underline", a3)) {
                                                    dg.i(true);
                                                }
                                            }
                                        }
                                        else {
                                            final String a49 = this.a("fonttype", array, n8 + 1, false, a3, s);
                                            if (!ji.util.d.by(a49)) {
                                                dg.a(ji.font.j.a(ad, af, this.ed, a49));
                                            }
                                            final String a50 = this.a("strikethrough", array, n8 + 1, false, a3, s);
                                            if (!ji.util.d.by(a50) && this.d(a50, "strikethrough", a3)) {
                                                dg.h(true);
                                            }
                                        }
                                        if (ji.util.i.c(150)) {
                                            final String a51 = this.a("alignment", array, n8 + 1, false, a3, s);
                                            if (!ji.util.d.by(a51)) {
                                                final String g6 = this.g(a51, "alignment", a3);
                                                if (!ji.util.d.by(g6) && g6.equalsIgnoreCase("right")) {
                                                    dg.ah(1);
                                                }
                                            }
                                        }
                                        if (n11 != 0) {
                                            n12 = n11 / dg.cu().a;
                                        }
                                        dg.c(n12);
                                        final String a52 = this.a("border", array, n8 + 1, false, false, a3, s);
                                        if (a52 != null) {
                                            dg.ax(this.d(a52, "border", a3));
                                        }
                                        else if (!ji.util.d.bf()) {
                                            b6 = this.d(this.a("transparent", array, n8 + 1, false, a3, s), "transparent", a3);
                                            if (b6) {
                                                dg.ax(false);
                                            }
                                            else {
                                                dg.ax(true);
                                            }
                                        }
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: FN1...".concat(String.valueOf(String.valueOf(ji.util.d.bf()))));
                                        }
                                        if (ji.util.d.bf()) {
                                            final String a53 = this.a("transparent", array, n8 + 1, false, a3, s);
                                            if (a53 != null) {
                                                b12 = true;
                                                b6 = this.d(a53, "transparent", a3);
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "Annotation: FN2...".concat(String.valueOf(String.valueOf(b6))));
                                                }
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "Annotation: FN2A...".concat(String.valueOf(String.valueOf(color3))));
                                                }
                                                if (b6) {
                                                    if (color3 != null) {
                                                        if (color3.equals(Color.white)) {
                                                            if (ji.util.d.cs()) {
                                                                ji.io.h.d(this.ed, "Annotation: FN3...");
                                                            }
                                                            dg.au(true);
                                                        }
                                                        else {
                                                            if (ji.util.d.cs()) {
                                                                ji.io.h.d(this.ed, "Annotation: FN4...");
                                                            }
                                                            dg.au(false);
                                                            dg.av(true);
                                                        }
                                                    }
                                                    else {
                                                        dg.au(true);
                                                    }
                                                }
                                                else {
                                                    dg.au(false);
                                                }
                                            }
                                        }
                                        else {
                                            final String a54 = this.a("semitransparent", array, n8 + 1, false, a3, s);
                                            if (a54 != null) {
                                                dg.av(this.d(a54, "semitransparent", a3));
                                            }
                                        }
                                        final String a55 = this.a("bordercolor", array, n8 + 1, false, false, a3, s);
                                        if (a55 != null) {
                                            Color color4 = this.a(a55, "bordercolor", ad, a3);
                                            if (ji.util.d.a2()) {
                                                color4 = ji.util.d.n(color4);
                                            }
                                            dg.f(color4);
                                        }
                                        final String a56 = this.a("borderwidth", array, n8 + 1, false, false, a3, s);
                                        if (a56 != null) {
                                            dg.s(this.a(a56, "borderwidth", a3));
                                        }
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("fonttype", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("fontheight", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                        }
                                        if (b2) {
                                            if (ji.util.d.bq() && ad.af()) {
                                                final String b18 = this.b("width", array, n8 + 1, false, a3, s);
                                                final String b19 = this.b("height", array, n8 + 1, false, a3, s);
                                                if (b18 != null) {
                                                    if (b18.length() != 0) {
                                                        if (b19 != null) {
                                                            if (b19.length() != 0) {
                                                                dg.d(b18);
                                                                dg.d(b19);
                                                                dg.x(true);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            else {
                                                final String a57 = this.a("width", array, n8 + 1, false, a3, s);
                                                final String a58 = this.a("height", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a57)) {
                                                    if (!ji.util.d.by(a58)) {
                                                        final int a59 = this.a(a57, "width", a3);
                                                        final int a60 = this.a(a58, "height", a3);
                                                        dg.a(a59, a60);
                                                        dg.b(a59, a60);
                                                        dg.x(true);
                                                    }
                                                }
                                            }
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 3)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: POLYGON...");
                                        }
                                        d4 c6;
                                        if (ji.util.d.bq() && ad.af()) {
                                            c6 = new d4();
                                            c6.addPoint(0, 0);
                                            c6.addPoint(1, 1);
                                            c6.addPoint(2, 2);
                                        }
                                        else {
                                            c6 = this.c(array, n8, a3, s);
                                        }
                                        if (c6.b() > 2) {
                                            if (color3 == null) {
                                                dg = new dg("Polygon", false, null, this.ed, this.eq, ad.hc());
                                            }
                                            else {
                                                dg = new dg("Polygon", true, color3, this.ed, this.eq, ad.hc());
                                            }
                                            dg.a(3, c6);
                                            dg.a(ad);
                                            if (ji.util.d.bq() && ad.af()) {
                                                if (ji.util.d.bf()) {
                                                    dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                }
                                                else {
                                                    dg.a(this.a(array, n8, a3, s));
                                                }
                                            }
                                        }
                                        else {
                                            this.cf = "polygons need at least 3 points";
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 10)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: FREEHAND...");
                                        }
                                        d4 c7;
                                        if (ji.util.d.bq() && ad.af()) {
                                            c7 = new d4();
                                            c7.addPoint(0, 0);
                                            c7.addPoint(1, 1);
                                            c7.addPoint(2, 2);
                                        }
                                        else {
                                            c7 = this.c(array, n8, a3, s);
                                        }
                                        if (c7.b() > 2) {
                                            if (!ji.util.i.c(204) && ji.util.d.bf() && !am) {
                                                a *= (int)2.0;
                                            }
                                            dg = new dg("freehand", false, null, this.ed, this.eq, ad.hc());
                                            dg.a(ad);
                                            dg.a(10, c7);
                                            if (ji.util.d.bq() && ad.af()) {
                                                final String a61 = this.a("xoffset", array, n8 + 1, false, a3, s);
                                                final String a62 = this.a("yoffset", array, n8 + 1, false, a3, s);
                                                final String a63 = this.a("width", array, n8 + 1, false, a3, s);
                                                final String a64 = this.a("height", array, n8 + 1, false, a3, s);
                                                if (a61 != null || a62 != null || a63 != null || a64 != null) {
                                                    if (a61 != null && a62 != null && a63 != null && a64 != null) {
                                                        dg.d(this.b("xoffset", array, n8 + 1, false, a3, s));
                                                        dg.d(this.b("yoffset", array, n8 + 1, false, a3, s));
                                                        dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                                        dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                                        dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                    }
                                                    else {
                                                        this.cf = "To use relative free hand co-ordinates, you must specify xoffset, yoffset, width and height parameters";
                                                    }
                                                }
                                                else {
                                                    dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                }
                                            }
                                        }
                                        else {
                                            this.cf = "freehand shapes need at least 3 points";
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 6)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: OPENPOLYGON...");
                                        }
                                        d4 c8;
                                        if (ji.util.d.bq() && ad.af()) {
                                            c8 = new d4();
                                            c8.addPoint(0, 0);
                                            c8.addPoint(1, 1);
                                            c8.addPoint(2, 2);
                                        }
                                        else {
                                            c8 = this.c(array, n8, a3, s);
                                        }
                                        if (c8.b() > 2) {
                                            dg = new dg("OpenPolygon", false, null, this.ed, this.eq, ad.hc());
                                            dg.a(ad);
                                            dg.a(6, c8);
                                            if (ji.util.d.bq() && ad.af()) {
                                                if (ji.util.d.bf()) {
                                                    dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                }
                                                else {
                                                    dg.a(this.a(array, n8, a3, s));
                                                }
                                            }
                                        }
                                        else {
                                            this.cf = "polygons need at least 3 points";
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 12)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: REDACTPOLYGON...");
                                        }
                                        d4 c9;
                                        if (ji.util.d.bq() && ad.af()) {
                                            c9 = new d4();
                                            c9.addPoint(0, 0);
                                            c9.addPoint(1, 1);
                                            c9.addPoint(2, 2);
                                        }
                                        else {
                                            c9 = this.c(array, n8, a3, s);
                                        }
                                        if (c9.b() > 2) {
                                            if (color3 == null) {
                                                color3 = ji.util.d.cq();
                                                if (ji.util.d.a2()) {
                                                    color3 = ji.util.d.n(color3);
                                                }
                                            }
                                            dg = new dg("RedactPolygon", true, color3, this.ed, this.eq, ad.hc());
                                            dg.a(ad);
                                            dg.a(12, c9);
                                            if (ji.util.d.bq() && ad.af()) {
                                                if (ji.util.d.bf()) {
                                                    dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                }
                                                else {
                                                    dg.a(this.a(array, n8, a3, s));
                                                }
                                            }
                                        }
                                        else {
                                            this.cf = "polygons need at least 3 points";
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 14)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: HIGHLIGHTPOLYGON...");
                                        }
                                        d4 c10;
                                        if (ji.util.d.bq() && ad.af()) {
                                            c10 = new d4();
                                            c10.addPoint(0, 0);
                                            c10.addPoint(1, 1);
                                            c10.addPoint(2, 2);
                                        }
                                        else {
                                            c10 = this.c(array, n8, a3, s);
                                        }
                                        if (c10.b() > 2) {
                                            if (color3 == null) {
                                                color3 = Color.yellow;
                                                if (ji.util.d.a2()) {
                                                    color3 = ji.util.d.n(color3);
                                                }
                                            }
                                            dg = new dg("HighlightPolygon", true, color3, this.ed, this.eq, ad.hc());
                                            dg.a(14, c10);
                                            dg.a(ad);
                                            if (ji.util.d.bq() && ad.af()) {
                                                if (ji.util.d.bf()) {
                                                    dg.a(this.d(this.a("f_points", array, n8 + 1, false, a3, s)));
                                                }
                                                else {
                                                    dg.a(this.a(array, n8, a3, s));
                                                }
                                            }
                                        }
                                        else {
                                            this.cf = "polygons need at least 3 points";
                                        }
                                        b11 = false;
                                    }
                                    else if (this.a(s5, 9)) {
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "Annotation: STAMP...");
                                        }
                                        Dimension a65;
                                        if (ji.util.d.bq() && ad.af()) {
                                            a65 = new Dimension(0, 0);
                                        }
                                        else {
                                            a65 = this.a(array, n8, "x", "y", a3, s);
                                        }
                                        if (color3 == null) {
                                            dg = new dg("Stamp", false, null, this.ed, this.eq, ad.hc());
                                        }
                                        else {
                                            dg = new dg("Stamp", true, color3, this.ed, this.eq, ad.hc());
                                        }
                                        dg.a(ad);
                                        String a66;
                                        if (al) {
                                            dg.d(this.b("resource", array, n8 + 1, false, a3, s));
                                            a66 = null;
                                        }
                                        else {
                                            a66 = this.a("resource", array, n8 + 1, true, a3, s);
                                        }
                                        dg.a(s2, 9, 1, a66, a65.width, a65.height, b2);
                                        final String a67 = this.a("OVERLAY", array, n8 + 1, false, a3, s);
                                        final boolean d3 = this.d(a67, "OVERLAY", a3);
                                        if (a67 != null) {
                                            dg.aa(!d3);
                                        }
                                        final String a68 = this.a("scale", array, n8 + 1, false, a3, s);
                                        if (!ji.util.d.by(a68)) {
                                            dg.b(this.c(a68, "scale", a3));
                                        }
                                        dg.ao(ji.util.i.c(130));
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("width", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("height", array, n8 + 1, false, a3, s));
                                        }
                                        else {
                                            final String a69 = this.a("width", array, n8 + 1, false, a3, s);
                                            if (!ji.util.d.by(a69)) {
                                                final String a70 = this.a("height", array, n8 + 1, false, a3, s);
                                                if (!ji.util.d.by(a70)) {
                                                    final int a71 = this.a(a69, "width", a3);
                                                    final int a72 = this.a(a70, "height", a3);
                                                    if (a71 > 0 && a72 > 0) {
                                                        if (ji.util.d.cs()) {
                                                            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("Using stamp width and height ( ").append(a71).append(", ").append(a72).append(")..."))));
                                                        }
                                                        dg.d(a71, a72);
                                                        dg.ao(false);
                                                    }
                                                    else {
                                                        ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("*** Warning: Annotaion width/height for stamp is invalid and will be ignored (width=").append(a69).append(", height=").append(a70).append(")"))));
                                                    }
                                                }
                                                else {
                                                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("*** Warning: Annotaion height for stamp is missing and so width will be ignored (width=").append(a69).append(")"))));
                                                }
                                            }
                                        }
                                        if (ji.util.d.bq() && ad.af()) {
                                            dg.d(this.b("x", array, n8 + 1, false, a3, s));
                                            dg.d(this.b("y", array, n8 + 1, false, a3, s));
                                        }
                                        b11 = false;
                                    }
                                    else {
                                        if (s5 != null) {
                                            s5 = s5.toUpperCase();
                                        }
                                        else {
                                            s5 = "";
                                        }
                                        if (!b11) {
                                            this.cf = String.valueOf(String.valueOf(s5)).concat(" - This line must define annotation type (e.g. [LINE])");
                                            ji.io.h.d(this.ed, "--ERROR-- ".concat(String.valueOf(String.valueOf(this.cf))));
                                        }
                                    }
                                }
                            }
                            catch (Exception ex3) {
                                ex3.printStackTrace();
                                this.cf = ex3.toString();
                            }
                            if (dg != null) {
                                dg.t(b);
                                dg.c(true);
                                if (dg.d4() != 16 && dg.d4() != 17) {
                                    n = this.a(this.a("page", array, n8 + 1, true, a3, s), "page", a3);
                                    final String g7 = this.g(this.a("label", array, n8 + 1, false, a3, s), "label", a3);
                                    if (g7 != null) {
                                        if (!ji.util.d.bf() || !ad.af()) {
                                            dg.q(g7);
                                        }
                                        else {
                                            dg.q(ji.util.d.b(g7, "<type>", ji.annotate.dg.a(dg.d5(), this.ed)));
                                        }
                                    }
                                    final String g8 = this.g(this.a("aspectRatio", array, n8 + 1, false, a3, s), "aspectRatio", a3);
                                    if (g8 != null) {
                                        if (ji.annotate.dg.v(g8) != null) {
                                            dg.u(g8);
                                        }
                                        else {
                                            this.cf = String.valueOf(String.valueOf(new StringBuffer("invalid aspect ratio value ").append(g8).append(", expect format 1:1")));
                                        }
                                    }
                                    final String a73 = this.a("rulertool", array, n8 + 1, false, a3, s);
                                    if (a73 != null) {
                                        dg.bc(this.d(a73, "ruler", a3));
                                    }
                                    final String a74 = this.a("angletool", array, n8 + 1, false, a3, s);
                                    if (a74 != null) {
                                        dg.bd(this.d(a74, "angletool", a3));
                                    }
                                    final String a75 = this.a("angletoolflip", array, n8 + 1, false, a3, s);
                                    if (a75 != null) {
                                        dg.be(this.d(a75, "angletoolglip", a3));
                                    }
                                    if (ji.util.d.bf() && ad.af()) {
                                        final String g9 = this.g(this.a("fnid", array, n8 + 1, false, a3, s), "fnid", a3);
                                        if (g9 != null) {
                                            dg.r(g9);
                                        }
                                    }
                                    if (am) {
                                        final String g10 = this.g(this.a("fnannotatedid", array, n8 + 1, false, a3, s), "fnannotatedid", a3);
                                        if (g10 != null) {
                                            dg.s(g10);
                                        }
                                    }
                                    final String g11 = this.g(this.a("tooltip", array, n8 + 1, false, true, a3, s), "tooltip", a3);
                                    if (g11 != null) {
                                        this.a(dg, g11, ad);
                                    }
                                    String s9;
                                    if (al) {
                                        s9 = this.a("hyperlink", false, h);
                                    }
                                    else {
                                        s9 = this.a("hyperlink", array, n8 + 1, false, true, a3, s);
                                    }
                                    final String g12 = this.g(s9, "hyperlink", a3);
                                    if (g12 != null) {
                                        dg.l(g12);
                                    }
                                    String s10;
                                    if (al) {
                                        s10 = this.a("hyperlinksettings", false, h);
                                    }
                                    else {
                                        s10 = this.a("hyperlinksettings", array, n8 + 1, false, a3, s);
                                    }
                                    final String g13 = this.g(s10, "hyperlinksettings", a3);
                                    if (g13 != null) {
                                        dg.a(g13, ad);
                                    }
                                    final String g14 = this.g(this.a("id", array, n8 + 1, false, a3, s), "id", a3);
                                    if (g14 != null) {
                                        dg.k(g14);
                                    }
                                    if (color != null) {
                                        dg.c(color);
                                    }
                                    if (ji.util.d.bq() && ad.af() && ji.util.i.c(204)) {
                                        dg.d(this.b("linewidth", array, n8 + 1, false, a3, s));
                                    }
                                    dg.v(a);
                                    dg.w(a);
                                    final String a76 = this.a("rotation", array, n8 + 1, false, a3, s);
                                    final int a77 = this.a(a76, "rotation", a3);
                                    if (a76 != null) {
                                        dg.l(a77);
                                    }
                                    if (dg.d4() == 7) {
                                        final String a78 = this.a("textrotation", array, n8 + 1, false, a3, s);
                                        final int a79 = this.a(a78, "textrotation", a3);
                                        if (a78 != null) {
                                            dg.k(a79);
                                        }
                                    }
                                    final String a80 = this.a("flip", array, n8 + 1, false, a3, s);
                                    final int a81 = this.a(a80, "flip", a3);
                                    if (a80 != null) {
                                        dg.n(a81);
                                    }
                                    final String a82 = this.a("invert", array, n8 + 1, false, a3, s);
                                    final boolean d4 = this.d(a82, "invert", a3);
                                    if (a82 != null) {
                                        dg.ap(d4);
                                    }
                                    final String a83 = this.a("hash", array, n8 + 1, false, a3, s);
                                    final long b20 = this.b(a83, "hash", a3);
                                    if (a83 != null) {
                                        dg.c(b20);
                                    }
                                    if (!ji.util.d.bf() || am) {
                                        boolean b21 = true;
                                        if (am) {
                                            if (ji.util.d.cs()) {
                                                ji.io.h.d(this.ed, "Annotation: FN5C...".concat(String.valueOf(String.valueOf(ad.ac()))));
                                            }
                                            if (!ad.ac()) {
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "Annotation: FN5A...".concat(String.valueOf(String.valueOf(b6))));
                                                }
                                                b21 = false;
                                            }
                                        }
                                        if (b21) {
                                            final String a84 = this.a("transparent", array, n8 + 1, false, a3, s);
                                            b6 = this.d(a84, "transparent", a3);
                                            if (ji.util.d.cs()) {
                                                ji.io.h.d(this.ed, "Annotation: FN5...".concat(String.valueOf(String.valueOf(b6))));
                                            }
                                            if (a84 != null) {
                                                dg.au(b6);
                                            }
                                        }
                                    }
                                    dg.ag(b7);
                                    dg.b(color2);
                                    dg.g(s3);
                                    dg.ay(d);
                                    if (am) {
                                        dg.b(trim);
                                    }
                                    s2 = null;
                                    final String a85 = this.a("createdid", array, n8 + 1, false, true, a3, s);
                                    if (a85 != null) {
                                        s2 = a85;
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "CREATEDID = ".concat(String.valueOf(String.valueOf(s2))));
                                        }
                                    }
                                    else {
                                        s2 = null;
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "CREATEDID = null");
                                        }
                                    }
                                    final String a86 = this.a("createdate", array, n8 + 1, false, a3, s);
                                    final GregorianCalendar f = this.f(a86, "createdate", a3);
                                    if (a86 != null && f != null) {
                                        dg.a(f, s2, false);
                                        dg.o(a86);
                                    }
                                    if (!ji.util.d.bf()) {
                                        final String a87 = this.a("securityModel", array, n8 + 1, false, a3, s);
                                        if (a87 != null) {
                                            dg.f(this.a(a87, "securityModel", a3));
                                        }
                                    }
                                    else {
                                        dg.f(2);
                                    }
                                    try {
                                        dg.bp().a(true);
                                        if (dg.bn() == 1) {
                                            final String a88 = this.a("edit", array, n8 + 1, false, a3, s);
                                            if (a88 != null) {
                                                final boolean d5 = this.d(a88, "edit", a3);
                                                dg.bp().c(d5);
                                                dg.bp().h(d5);
                                            }
                                            final String a89 = this.a("view", array, n8 + 1, false, a3, s);
                                            final int a90 = this.a(a89, "view", a3);
                                            if (a89 != null) {
                                                dg.bp().c(a90);
                                            }
                                        }
                                        else if (ji.util.d.bf() && ad.af()) {
                                            if (this.eq == 0) {
                                                final dh bp = dg.bp();
                                                String s11 = this.a("fnread", array, n8 + 1, false, true, a3, s);
                                                if (s11 == null) {
                                                    s11 = ji.secure.ei.b;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnread = ".concat(String.valueOf(String.valueOf(s11))));
                                                }
                                                final String a91 = this.a("fnreadtype", array, n8 + 1, false, a3, s);
                                                int n13;
                                                if (a91 == null) {
                                                    n13 = ji.secure.ei.c;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnreadtype = ".concat(String.valueOf(String.valueOf(a91))));
                                                    n13 = this.e(a91);
                                                }
                                                bp.a(new ei(s11, n13, 0, 0));
                                                String s12 = this.a("fnwrite", array, n8 + 1, false, true, a3, s);
                                                if (s12 == null) {
                                                    s12 = ji.secure.ei.d;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnwrite = ".concat(String.valueOf(String.valueOf(s12))));
                                                }
                                                final String a92 = this.a("fnwritetype", array, n8 + 1, false, a3, s);
                                                int n14;
                                                if (a92 == null) {
                                                    n14 = ji.secure.ei.e;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnwritetype = ".concat(String.valueOf(String.valueOf(a92))));
                                                    n14 = this.e(a92);
                                                }
                                                bp.a(new ei(s12, n14, 1, 0));
                                                String s13 = this.a("fnappend", array, n8 + 1, false, true, a3, s);
                                                if (s13 == null) {
                                                    s13 = ji.secure.ei.f;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnappend = ".concat(String.valueOf(String.valueOf(s13))));
                                                }
                                                final String a93 = this.a("fnappendtype", array, n8 + 1, false, a3, s);
                                                int n15;
                                                if (a93 == null) {
                                                    n15 = ji.secure.ei.g;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnappendtype = ".concat(String.valueOf(String.valueOf(a93))));
                                                    n15 = this.e(a93);
                                                }
                                                bp.a(new ei(s13, n15, 2, 0));
                                                String s14 = this.a("fnclientpermission", array, n8 + 1, false, true, a3, s);
                                                if (s14 == null) {
                                                    s14 = ji.secure.ei.a;
                                                }
                                                else {
                                                    ji.io.h.a(this.ed, "fnclientpermission = ".concat(String.valueOf(String.valueOf(s14))));
                                                }
                                                final ei ei = new ei(s14, 2, 99, 0);
                                                bp.a(ei);
                                                bp.f(ei.h);
                                                if (ji.util.i.c(28) && dg.d5() == 8) {
                                                    final String a94 = this.a("view", array, n8 + 1, false, a3, s);
                                                    if (a94 != null) {
                                                        bp.c(this.a(a94, "view", a3));
                                                    }
                                                }
                                            }
                                            else if (ji.util.d.am(this.eq)) {
                                                final dh bp2 = dg.bp();
                                                dh dh;
                                                if (b) {
                                                    dh = this.a(array, n8 + 1, a3, bp2, s);
                                                }
                                                else {
                                                    dh = this.a(dg, bp2, ad);
                                                }
                                                if (dg.d5() == 8) {
                                                    final String a95 = this.a("view", array, n8 + 1, false, a3, s);
                                                    if (a95 != null) {
                                                        dh.c(this.a(a95, "view", a3));
                                                    }
                                                }
                                                dg.a(dh);
                                            }
                                            else {
                                                final dh bp3 = dg.bp();
                                                final ei[] a96 = this.a("fn", array, n8 + 1, a3, s);
                                                for (int n16 = 0; n16 < a96.length; ++n16) {
                                                    bp3.a(a96[n16]);
                                                }
                                                String s15 = this.a("fnclientpermission", array, n8 + 1, false, true, a3, s);
                                                if (s15 == null) {
                                                    s15 = ji.secure.ei.a;
                                                }
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "fnclientpermission = ".concat(String.valueOf(String.valueOf(s15))));
                                                }
                                                final ei ei2 = new ei(s15, 2, 99, 0);
                                                bp3.a(ei2);
                                                bp3.f(ei2.h);
                                                if (ji.util.i.c(28) && dg.d5() == 8) {
                                                    final String a97 = this.a("view", array, n8 + 1, false, a3, s);
                                                    if (a97 != null) {
                                                        bp3.c(this.a(a97, "view", a3));
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            try {
                                                this.a(dg, ad.d(), ad, "init", false);
                                            }
                                            catch (Exception ex4) {
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, ex4.getMessage());
                                                }
                                            }
                                            final String a98 = this.a("customsecurity", array, n8 + 1, false, true, a3, s);
                                            if (a98 != null) {
                                                if (ji.util.d.cs()) {
                                                    ji.io.h.d(this.ed, "CUSTOMSECURITY = ".concat(String.valueOf(String.valueOf(a98))));
                                                }
                                                dg.bp().a(new ei(a98, 1, 0));
                                            }
                                            final String a99 = this.a("read", array, n8 + 1, false, a3, s);
                                            if (a99 != null) {
                                                dg.bp().b(this.d(a99, "read", a3));
                                            }
                                            final String a100 = this.a("modify", array, n8 + 1, false, a3, s);
                                            if (a100 != null) {
                                                dg.bp().c(this.d(a100, "modify", a3));
                                            }
                                            final String a101 = this.a("execute", array, n8 + 1, false, a3, s);
                                            if (a101 != null) {
                                                dg.bp().e(this.d(a101, "execute", a3));
                                            }
                                            final String a102 = this.a("print", array, n8 + 1, false, a3, s);
                                            if (a102 != null) {
                                                dg.bp().f(this.d(a102, "print", a3));
                                            }
                                            final String a103 = this.a("delete", array, n8 + 1, false, a3, s);
                                            if (a103 != null) {
                                                dg.bp().h(this.d(a103, "delete", a3));
                                            }
                                            final String a104 = this.a("owner", array, n8 + 1, false, true, a3, s);
                                            if (a104 != null) {
                                                ji.io.h.a(this.ed, "owner = ".concat(String.valueOf(String.valueOf(a104))));
                                                dg.bp().e(a104);
                                            }
                                            else {
                                                dg.bp().e(null);
                                            }
                                            final String a105 = this.a("passwordmodify", array, n8 + 1, false, a3, s);
                                            if (a105 != null) {
                                                ji.io.h.a(this.ed, "passwordmodify = ****");
                                                try {
                                                    dg.bp().b(ji.util.d.cf(a105));
                                                }
                                                catch (Exception ex5) {
                                                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("PASSWORDMODIFY: ").append(ex5.toString()).append(" on ").append(a105))));
                                                    dg.bp().c(false);
                                                }
                                            }
                                            final String a106 = this.a("passwordsecurity", array, n8 + 1, false, a3, s);
                                            if (a106 != null) {
                                                ji.io.h.a(this.ed, "passwordsecurity = ****");
                                                try {
                                                    dg.bp().c(ji.util.d.cf(a106));
                                                }
                                                catch (Exception ex6) {
                                                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("PASSWORDSECURITY: ").append(ex6.toString()).append(" on ").append(a106))));
                                                    dg.bp().d(false);
                                                }
                                            }
                                            final String a107 = this.a("passwordtext", array, n8 + 1, false, a3, s);
                                            if (a107 != null) {
                                                ji.io.h.a(this.ed, "passwordsecurity = ****");
                                                try {
                                                    dg.bp().d(ji.util.d.cf(a107));
                                                }
                                                catch (Exception ex7) {
                                                    ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("PASSWORDTEXT: ").append(ex7.toString()).append(" on ").append(a107))));
                                                    dg.bp().d(false);
                                                }
                                            }
                                            final String a108 = this.a("modifysecurity", array, n8 + 1, false, a3, s);
                                            if (a108 != null) {
                                                dg.bp().d(this.d(a108, "modifysecurity", a3));
                                            }
                                        }
                                    }
                                    finally {
                                        dg.bp().a(false);
                                    }
                                    s2 = null;
                                    final String a109 = this.a("modifiedid", array, n8 + 1, false, true, a3, s);
                                    if (a109 != null) {
                                        s2 = a109;
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "MODIFIEDID = ".concat(String.valueOf(String.valueOf(s2))));
                                        }
                                    }
                                    else {
                                        s2 = null;
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "MODIFIEDID = null");
                                        }
                                    }
                                    final String a110 = this.a("modifieddate", array, n8 + 1, false, a3, s);
                                    final GregorianCalendar f2 = this.f(a110, "modifieddate", a3);
                                    if (a110 != null && f2 != null) {
                                        dg.a(f2, s2);
                                        dg.p(a110);
                                    }
                                    final String a111 = this.a("pagesize", array, n8 + 1, false, a3, s);
                                    final Dimension e = this.e(a111, "pagesize", a3);
                                    if (e != null) {
                                        dg.b(e, this.ac());
                                        dg.a(e, this.ac());
                                        if (ji.util.d.cs()) {
                                            ji.io.h.d(this.ed, "PAGESIZE = ".concat(String.valueOf(String.valueOf(a111))));
                                        }
                                    }
                                    else if (ji.util.d.cs()) {
                                        ji.io.h.d(this.ed, "PAGESIZE = null");
                                    }
                                    final String a112 = this.a("xres", array, n8 + 1, false, a3, s);
                                    if (a112 != null) {
                                        final double double1 = Double.parseDouble(a112);
                                        ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("xres = ").append(double1).append(" (").append(a112).append(")"))));
                                        final String a113 = this.a("yres", array, n8 + 1, false, a3, s);
                                        double double2;
                                        if (a113 != null) {
                                            double2 = Double.parseDouble(a113);
                                            ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("yres = ").append(double2).append(" (").append(a113).append(")"))));
                                        }
                                        else {
                                            double2 = double1;
                                        }
                                        dg.a(double1, double2, 0);
                                    }
                                    final String[] b22 = this.b(array, n8, a3, s);
                                    if (b22 != null && b22.length > 0) {
                                        if (ji.util.d.cs()) {
                                            for (int n17 = 0; n17 < b22.length; ++n17) {
                                                ji.io.h.d(this.ed, "CUSTOMPROPERTY = ".concat(String.valueOf(String.valueOf(b22[n17]))));
                                            }
                                        }
                                        try {
                                            if (ji.util.d.bg()) {
                                                final String a114 = this.a("F_ORDINAL", b22, 0, false, a3, s);
                                                if (a114 != null) {
                                                    dg.p(this.a(a114, "F_ORDINAL", a3));
                                                    b10 = true;
                                                }
                                            }
                                            if (ji.util.i.c(150)) {
                                                final String a115 = this.a("ALIGNMENT", b22, 0, false, a3, s);
                                                if (!ji.util.d.by(a115)) {
                                                    final String g15 = this.g(a115, "ALIGNMENT", a3);
                                                    if (!ji.util.d.by(g15) && g15.equalsIgnoreCase("right")) {
                                                        dg.ah(1);
                                                    }
                                                }
                                            }
                                            if (ji.util.d.bf() && !b12) {
                                                for (int n18 = 0; n18 < b22.length; ++n18) {
                                                    final String bc = ji.util.d.bc(b22[n18].toLowerCase());
                                                    if (bc.startsWith("f_text_backmode")) {
                                                        final String substring2 = bc.substring("f_text_backmode".length());
                                                        final int index2 = substring2.indexOf("=");
                                                        if (index2 >= 0) {
                                                            if (ji.util.d.c(substring2.substring(index2 + 1), 2) == 1) {
                                                                dg.au(true);
                                                                dg.ax(false);
                                                            }
                                                            else {
                                                                dg.au(false);
                                                                dg.ax(true);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        catch (Exception ex9) {}
                                        for (int length2 = b22.length, n19 = 0; n19 < length2; ++n19) {
                                            final String s16 = b22[n19];
                                            if (!ji.util.d.bg()) {
                                                dg.e(s16);
                                            }
                                            else if (!s16.trim().toUpperCase().startsWith("F_ORDINAL".toUpperCase())) {
                                                dg.e(b22[n19]);
                                            }
                                        }
                                    }
                                    dg.eh();
                                    if (d2) {
                                        dg.z(true);
                                    }
                                }
                                else if (dg.d4() == 16) {
                                    final String g16 = this.g(this.a("label", array, n8 + 1, false, a3, s), "label", a3);
                                    if (g16 != null && (!ji.util.d.bf() || !ad.af())) {
                                        dg.q(g16);
                                    }
                                    n = 0;
                                    n = this.a(this.a("page", array, n8 + 1, false, a3, s), "page", a3);
                                    final String[] b23 = this.b(array, n8, a3, s);
                                    if (b23 != null && b23.length > 0) {
                                        if (ji.util.d.cs()) {
                                            for (int n20 = 0; n20 < b23.length; ++n20) {
                                                ji.io.h.d(this.ed, "CUSTOMPROPERTY = ".concat(String.valueOf(String.valueOf(b23[n20]))));
                                            }
                                        }
                                        for (int length3 = b23.length, n21 = 0; n21 < length3; ++n21) {
                                            dg.e(b23[n21]);
                                        }
                                    }
                                }
                            }
                            if (b3) {
                                if (!ji.wang.ej.a(dg.d5())) {
                                    this.cf = String.valueOf(String.valueOf(new StringBuffer("Annotations of type ").append(s5).append(" are not allowed when Wang annotation saving has been specified")));
                                }
                                else {
                                    dg.d(1);
                                }
                            }
                            if (this.cf != null) {
                                b4 = true;
                                String upperCase;
                                if (s5 != null) {
                                    upperCase = s5.toUpperCase();
                                }
                                else {
                                    upperCase = "";
                                }
                                ad.av(this.cf);
                                ji.io.h.d(this.ed, String.valueOf(String.valueOf(new StringBuffer("--ERROR-- ").append(upperCase).append(" - Annotation failed (").append(this.cf).append(")"))));
                            }
                            else if (dg != null) {
                                dg.j(n);
                                if (ji.util.i.e(0) != null) {
                                    a(dg, ji.util.i.e(0), s2, this.e, ad);
                                }
                                if (df == null) {
                                    df = new df(this.ed);
                                }
                                vector.addElement(dg);
                                if (ji.util.d.cs()) {
                                    ji.io.h.d(this.ed, "Annotation OK");
                                }
                            }
                            n8 = this.a(array, n8 + 1) - 1;
                        }
                    }
                }
                catch (Exception ex8) {
                    ex8.printStackTrace();
                }
                if (b8) {
                    try {
                        n6 += n8 - n9;
                        max -= n8 - n9;
                        if (max <= 0) {
                            max = n7;
                            if (System.currentTimeMillis() - currentTimeMillis > 1000) {
                                b5 = true;
                                this.a(ad, 100 * n6 / length);
                            }
                        }
                    }
                    catch (Exception ex10) {}
                }
            }
            if (b5) {
                this.a(ad, 100);
            }
            if (b11) {
                b4 = true;
                ad.av("Annotation data is not in the required format.");
                ji.io.h.d(this.ed, "Annotation data is not in the required format.");
            }
            if (!b4 && df != null) {
                final boolean a116 = df.a(true);
                try {
                    if (vector.size() > 0) {
                        df.h(vector.size());
                        while (vector.size() > 0) {
                            df.b(vector.elementAt(0));
                            vector.removeElementAt(0);
                        }
                        df.e();
                    }
                }
                finally {
                    df.a(a116);
                }
            }
            if (ji.util.d.bg() && b10) {
                this.a(df);
            }
            if (ji.util.d.cs()) {
                ji.io.h.d(this.ed, "End of file.");
                if (b4) {
                    ji.io.h.d(this.ed, "Parse complete (with errors).");
                }
                else {
                    ji.io.h.d(this.ed, "Parse complete.");
                }
            }
            if (ji.util.d.bf() && ad.af() && b && a2 < 311142) {
                ji.io.h.d(this.ed, "*** ANNOTATION VERSION ERROR: Incorrect XSL version: ".concat(String.valueOf(String.valueOf(a2))));
                ji.io.h.d(this.ed, "*** ANNOTATION VERSION ERROR: Require as least version: 311142");
                ji.io.h.d(this.ed, "*** ANNOTATION VERSION ERROR: Annotations may not display correctly.");
                ji.util.d.a(this.c(479), "***** Incorrect XSL version, annotations may not display correctly *****\n Minimum XSL version: 311142\nBut found version: ".concat(String.valueOf(String.valueOf(a2))), null, 120, null, null, this.ed);
            }
        }
        if (b5) {
            this.a(ad, 1);
        }
        return df;
    }
    
    private boolean f(final String s) {
        return s.equals("[version]");
    }
    
    private boolean g(final String s) {
        return s.equals("[empty]");
    }
    
    private boolean a(final String s, final int n) {
        return s.equals(this.e(n));
    }
    
    private String e(final int n) {
        return String.valueOf(String.valueOf(new StringBuffer("[").append(ji.annotate.dg.g(n).toLowerCase()).append("]")));
    }
    
    private void a(final df df) {
        final int d = df.d();
        int n = 0;
        int n2 = -1;
        for (int i = 0; i < d; ++i) {
            final dg f = df.f(i);
            final int j = f.i(0);
            if (f.gh()) {
                ++n;
            }
            n2 = ((n2 < j) ? j : n2);
        }
        int n3 = n;
        for (int k = 1; k <= n2; ++k) {
            final c b = df.b(k, this.ed);
            final ax d2 = b.d();
            while (d2.a()) {
                final dg dg = (dg)b.d(d2.b());
                if (dg.gh()) {
                    final int cg = dg.cg();
                    if (ji.util.i.c(257)) {
                        dg.o(n3);
                    }
                    --n3;
                    dg.a("".concat(String.valueOf(String.valueOf(cg))));
                    if (ji.util.d.g(this.ed) >= cg) {
                        continue;
                    }
                    ji.util.d.a(this.ed, cg);
                }
            }
        }
    }
    
    private void a(final dg dg, final String s, final ad ad) {
        if (!ji.util.d.bf() || !ad.af()) {
            dg.m(s);
        }
        else {
            dg.m(ji.util.d.b(s, "<type>", dg.a(dg.d5(), this.ed)));
        }
    }
    
    public static void a(final dg dg, final String s, final String s2, final String s3, final ad ad) {
        if (d.cs()) {
            h.d(ad.getId(), "Setting custom annotation tooltip for ".concat(String.valueOf(String.valueOf(dg.toString()))));
        }
        if (s != null) {
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '<') {
                    ++n;
                }
                else if (s.charAt(i) == '>') {
                    ++n2;
                }
            }
            if (n != n2) {
                if (d.cs()) {
                    h.d(ad.getId(), "Error setting custom annotation tooltip due to unmatched brackets in string: ".concat(String.valueOf(String.valueOf(s))));
                }
                return;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "<");
            final StringBuffer sb = new StringBuffer();
            int n3 = 1;
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                final String substring = nextToken.substring(0, nextToken.length() - nextToken.length());
                final int index = nextToken.indexOf(">");
                if (index > -1) {
                    final String substring2 = nextToken.substring(0, index);
                    final String substring3 = nextToken.substring(index + 1, nextToken.length());
                    sb.append(substring);
                    if (substring2.equalsIgnoreCase("user")) {
                        if (dg.bp() != null) {
                            String n4 = dg.bp().n();
                            if (d.by(n4)) {
                                if (!d.by(s2)) {
                                    n4 = s2;
                                }
                                else if (!d.by(s3)) {
                                    n4 = s3;
                                }
                                else {
                                    n4 = "";
                                }
                            }
                            sb.append(n4);
                        }
                    }
                    else if (substring2.equalsIgnoreCase("date")) {
                        GregorianCalendar gregorianCalendar = dg.dr();
                        if (gregorianCalendar == null) {
                            gregorianCalendar = dg.di();
                        }
                        if (gregorianCalendar != null) {
                            sb.append(d.a(gregorianCalendar, true));
                        }
                    }
                    else if (substring2.equalsIgnoreCase("dateonly")) {
                        GregorianCalendar gregorianCalendar2 = dg.dr();
                        if (gregorianCalendar2 == null) {
                            gregorianCalendar2 = dg.di();
                        }
                        if (gregorianCalendar2 != null) {
                            sb.append(d.b(gregorianCalendar2, true));
                        }
                    }
                    else if (substring2.equalsIgnoreCase("creator")) {
                        if (dg.dh()) {
                            sb.append(dg.dg());
                        }
                    }
                    else if (substring2.equalsIgnoreCase("createdate")) {
                        final GregorianCalendar di = dg.di();
                        if (di != null) {
                            sb.append(d.a(di, true));
                        }
                    }
                    else if (substring2.equalsIgnoreCase("createdateonly")) {
                        final GregorianCalendar di2 = dg.di();
                        if (di2 != null) {
                            sb.append(d.b(di2, true));
                        }
                    }
                    else if (substring2.equalsIgnoreCase("tooltip")) {
                        final String c9 = dg.c9();
                        if (c9 != null) {
                            sb.append(c9);
                        }
                    }
                    sb.append(substring3);
                }
                else {
                    if (n3 == 0) {
                        if (d.cs()) {
                            h.d(ad.getId(), "Error setting custom annotation tooltip due to bracket matching problem in string: ".concat(String.valueOf(String.valueOf(s))));
                        }
                        return;
                    }
                    sb.append(nextToken);
                }
                n3 = 0;
            }
            dg.n(sb.toString());
        }
        else if (d.cs()) {
            h.d(ad.getId(), "Error setting custom annotation tooltip due to empty string");
        }
    }
    
    private String h(final String s) {
        final StringBuffer sb = new StringBuffer();
        int n = s.indexOf(32, 0);
        int n2 = 0;
        final String[] array = new String[256];
        int n3;
        for (n3 = 0; n != -1 && n3 < 256; ++n3, n2 = n + 1, n = s.indexOf(32, n2)) {
            array[n3] = s.substring(n2, n);
        }
        final byte[] array2 = new byte[n3];
        for (int i = 0; i < n3; ++i) {
            array2[i] = Byte.parseByte(array[i]);
        }
        return new String(array2);
    }
    
    private String a(final String s, final boolean b, final String s2) {
        final int index = s2.toLowerCase().indexOf(String.valueOf(String.valueOf(s.toLowerCase())).concat("="));
        String substring;
        if (index != -1) {
            final int n = index + (s.length() + 1);
            final int index2 = s2.indexOf(";", n);
            if (index2 != -1) {
                substring = s2.substring(n, index2);
            }
            else {
                substring = "";
            }
        }
        else {
            substring = "";
        }
        return substring;
    }
    
    public final boolean z() {
        boolean b = false;
        if (!this.ca) {
            try {
                this.aj();
                this.ca = true;
                this.dr = -1;
                this.ab = null;
                if (this.cr != null) {
                    b = true;
                    try {
                        try {
                            if (this.b5 != null) {
                                this.b5.i();
                                this.b5 = null;
                            }
                        }
                        catch (Exception ex) {}
                        this.cr.setVisible(false);
                        if (ji.annotate.dy.au && this.de != null) {
                            this.de.l(this.dp);
                            this.de.a(this.dq, this.ab);
                        }
                        this.c2 = null;
                        this.de = null;
                        if (this.cs != null) {
                            if (this.ct != null) {
                                if (this.cw != null) {
                                    this.ct.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.ct.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.ct.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.ct.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.ct);
                                this.ct = null;
                            }
                            if (this.cu != null) {
                                if (this.cw != null) {
                                    this.cu.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.cu.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.cu.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.cu.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.cu);
                                this.cu = null;
                            }
                            if (this.c5 != null) {
                                if (this.cw != null) {
                                    this.c5.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c5.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.c5.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.c5.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c5);
                                this.c5 = null;
                            }
                            if (this.c6 != null) {
                                if (this.cw != null) {
                                    this.c6.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c6.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.c6.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.c5.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c6);
                                this.c6 = null;
                            }
                            if (this.c7 != null) {
                                if (this.cw != null) {
                                    this.c7.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c7.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.c7.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.c7.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c7);
                                this.c7 = null;
                            }
                            if (this.c8 != null) {
                                if (this.cw != null) {
                                    this.c8.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c8.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.c8.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.c8.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c8);
                                this.c8 = null;
                            }
                            if (this.c9 != null) {
                                if (this.cw != null) {
                                    this.c9.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c9.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.c9.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.c9.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c9);
                                this.c9 = null;
                            }
                            if (this.dl != null) {
                                if (this.cw != null) {
                                    this.dl.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dl.removeFocusListener(this.cx);
                                }
                                if (this.c1 != null) {
                                    this.dl.removeItemListener(this.c1);
                                }
                                if (this.cz != null) {
                                    this.dl.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.dl);
                                this.dl = null;
                            }
                            if (this.df != null) {
                                if (this.cw != null) {
                                    this.df.removeKeyListener(this.cw);
                                }
                                if (this.cz != null) {
                                    this.df.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.df);
                                this.df = null;
                            }
                            if (this.dg != null) {
                                if (this.cw != null) {
                                    this.dg.removeKeyListener(this.cw);
                                }
                                if (this.cz != null) {
                                    this.dg.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.dg);
                                this.dg = null;
                            }
                            if (this.dh != null) {
                                if (this.cw != null) {
                                    this.dh.removeKeyListener(this.cw);
                                }
                                if (this.cz != null) {
                                    this.dh.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.dh);
                                this.dh = null;
                            }
                            if (this.di != null) {
                                if (this.cw != null) {
                                    this.di.removeKeyListener(this.cw);
                                }
                                if (this.cz != null) {
                                    this.di.removeMouseListener(this.cz);
                                }
                                if (this.dd != null) {
                                    this.dd.remove(this.di);
                                }
                                this.di = null;
                            }
                            if (this.dm != null) {
                                if (this.cw != null) {
                                    this.dm.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dm.removeFocusListener(this.cx);
                                }
                                if (this.cz != null) {
                                    this.dm.removeMouseListener(this.cz);
                                }
                                if (this.cy != null) {
                                    this.dm.removeActionListener(this.cy);
                                }
                                if (this.dd != null) {
                                    this.dd.remove(this.dm);
                                }
                                this.dm = null;
                            }
                            if (this.dj != null) {
                                this.dj.removeAll();
                                if (this.cw != null) {
                                    this.dj.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dj.removeFocusListener(this.cx);
                                }
                                if (this.cz != null) {
                                    this.dj.removeMouseListener(this.cz);
                                }
                                if (this.dd != null) {
                                    this.dd.remove(this.dj);
                                }
                                this.dj = null;
                            }
                            if (this.dk != null) {
                                this.dk.removeAll();
                                if (this.cw != null) {
                                    this.dk.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dk.removeFocusListener(this.cx);
                                }
                                if (this.cz != null) {
                                    this.dk.removeMouseListener(this.cz);
                                }
                                if (this.dk != null) {
                                    this.dd.remove(this.dk);
                                }
                                this.dk = null;
                            }
                            if (this.da != null) {
                                this.da = null;
                            }
                            if (this.dd != null) {
                                this.dd.removeAll();
                                if (this.cw != null) {
                                    this.dd.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dd.removeFocusListener(this.cx);
                                }
                                if (this.cz != null) {
                                    this.dd.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.dd);
                                this.dd.releaseResources();
                                this.dd = null;
                            }
                            if (this.db != null) {
                                if (this.cw != null) {
                                    this.db.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.db.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.db.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.db.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.db);
                                this.db = null;
                            }
                            if (this.dc != null) {
                                if (this.cw != null) {
                                    this.dc.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.dc.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.dc.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.dc.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.dc);
                                this.dc = null;
                            }
                            if (this.c3 != null) {
                                if (this.cw != null) {
                                    this.c3.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c3.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.c3.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.c3.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c3);
                                this.c3 = null;
                            }
                            if (this.c4 != null) {
                                if (this.cw != null) {
                                    this.c4.removeKeyListener(this.cw);
                                }
                                if (this.cx != null) {
                                    this.c4.removeFocusListener(this.cx);
                                }
                                if (this.cy != null) {
                                    this.c4.removeActionListener(this.cy);
                                }
                                if (this.cz != null) {
                                    this.c4.removeMouseListener(this.cz);
                                }
                                this.cs.remove(this.c4);
                                this.c4 = null;
                            }
                            if (this.cz != null) {
                                this.cr.removeMouseListener(this.cz);
                            }
                            if (this.cw != null) {
                                this.cr.removeKeyListener(this.cw);
                            }
                            this.cx = null;
                            this.cy = null;
                            this.c1 = null;
                            this.cz = null;
                            this.cw = null;
                            this.cr.remove(this.cs);
                            this.cs.releaseResources();
                            this.cs = null;
                        }
                    }
                    catch (Exception ex2) {}
                    if (this.cr != null) {
                        if (this.c0 != null) {
                            this.cr.removeWindowListener(this.c0);
                            this.c0 = null;
                        }
                        this.cr = null;
                    }
                }
            }
            catch (Exception ex3) {}
            finally {
                this.ca = false;
            }
        }
        return b;
    }
    
    public final boolean aa() {
        boolean b = false;
        if (!this.cb) {
            try {
                this.cb = true;
                try {
                    if (this.b6 != null) {
                        int n = 2 * this.b6.b();
                        int n2 = 1;
                        while (n2 != 0 && n > 0) {
                            --n;
                            n2 = 0;
                            for (int i = 0; i < this.b6.b(); ++i) {
                                b = true;
                                ((bj)this.b6.b(i)).i();
                                n2 = 1;
                            }
                        }
                        this.b6.c();
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    if (this.e2 != null) {
                        for (int j = 0; j < this.e2.b(); ++j) {
                            b = true;
                            ((vb)this.e2.b(j)).a();
                        }
                        this.e2.c();
                    }
                }
                catch (Exception ex2) {}
            }
            catch (Exception ex3) {}
            finally {
                this.cb = false;
            }
        }
        return b;
    }
    
    public final boolean l(final dg dg) {
        boolean b = false;
        try {
            Object c = null;
            if (this.e2 != null) {
                for (int i = 0; i < this.e2.b(); ++i) {
                    final vb vb = (vb)this.e2.b(i);
                    if (vb.d() == dg.d0()) {
                        c = vb.c();
                        break;
                    }
                }
            }
            if (c != null) {
                Label_0144: {
                    try {
                        if (this.b6 != null) {
                            bj bj = null;
                            Block_11: {
                                for (int j = 0; j < this.b6.b(); ++j) {
                                    b = true;
                                    bj = (bj)this.b6.b(j);
                                    if (bj.equals(c)) {
                                        break Block_11;
                                    }
                                }
                                break Label_0144;
                            }
                            int j = 0;
                            this.b6.d(j);
                            bj.i();
                        }
                    }
                    catch (Exception ex) {}
                    try {
                        if (this.e2 != null) {
                            vb vb2 = null;
                            Block_15: {
                                for (int k = 0; k < this.e2.b(); ++k) {
                                    vb2 = (vb)this.e2.b(k);
                                    if (vb2.d() == dg.d0()) {
                                        break Block_15;
                                    }
                                }
                                return b;
                            }
                            int k = 0;
                            this.e2.d(k);
                            vb2.a();
                            b = true;
                            return b;
                        }
                        return b;
                    }
                    catch (Exception ex2) {}
                }
            }
        }
        catch (Exception ex3) {}
        return b;
    }
    
    public final void a(final dg de, final ad ab) {
        if (de != null) {
            try {
                if (this.cr == null) {
                    this.ab = ab;
                    ji.annotate.dy.au = true;
                    final boolean b = de.bq() && de.f(this.e, this.d);
                    this.dp = de.b(ab);
                    this.dt = this.dp;
                    this.dq = de.dc();
                    this.du = this.dq;
                    final int n = 530;
                    int n2 = 430;
                    int n3 = 0;
                    if (ji.util.d.av(this.ed)) {
                        n2 += 30;
                    }
                    this.cz = new aca();
                    this.cw = new acb();
                    final Window b2 = ji.util.d.b(this.aa, this.ed);
                    String s;
                    if (de.bq()) {
                        s = this.c(492);
                    }
                    else {
                        s = this.c(493);
                    }
                    if (b2 instanceof Frame) {
                        this.cr = new bl((Frame)b2, s, true);
                    }
                    else if (b2 instanceof Dialog) {
                        this.cr = new bl((Dialog)b2, s, true);
                    }
                    this.cr.setResizable(false);
                    this.cr.addKeyListener(this.cw);
                    this.cr.addMouseListener(this.cz);
                    this.c0 = new acc();
                    this.cr.addWindowListener(this.c0);
                    (this.cs = new jiPanel(this.ed)).setBorderStyle(0);
                    this.cr.d().add(this.cs, "Center");
                    (this.ct = new ek(this.ed, de.du())).addMouseListener(this.cz);
                    this.ct.addKeyListener(this.cw);
                    (this.cu = new ek(this.ed, de.c9())).addMouseListener(this.cz);
                    this.cu.addKeyListener(this.cw);
                    (this.dm = new ek(this.ed)).addMouseListener(this.cz);
                    this.dm.addKeyListener(this.cw);
                    this.cx = new acd();
                    this.ct.addFocusListener(this.cx);
                    this.cu.addFocusListener(this.cx);
                    this.dm.addFocusListener(this.cx);
                    if (b) {
                        this.dn = true;
                        ji.util.e.a(this.db = new Button(this.c(515)));
                        this.db.addMouseListener(this.cz);
                        this.db.addKeyListener(this.cw);
                        ji.util.e.a(this.dc = new Button(this.c(517)));
                        this.dc.addMouseListener(this.cz);
                        this.dc.addKeyListener(this.cw);
                    }
                    ji.util.e.a(this.c3 = new Button(this.c(232)));
                    this.c3.addMouseListener(this.cz);
                    this.c3.addKeyListener(this.cw);
                    ji.util.e.a(this.c4 = new Button(this.c(235)));
                    this.c4.addMouseListener(this.cz);
                    this.c4.addKeyListener(this.cw);
                    this.c3.addFocusListener(this.cx);
                    this.c4.addFocusListener(this.cx);
                    this.de = de;
                    this.cy = new ace();
                    this.c3.addActionListener(this.cy);
                    this.c4.addActionListener(this.cy);
                    if (b) {
                        this.db.addActionListener(this.cy);
                        this.dc.addActionListener(this.cy);
                    }
                    this.ct.addActionListener(this.cy);
                    this.cu.addActionListener(this.cy);
                    this.dm.addActionListener(this.cy);
                    this.cs.setLayout(null);
                    this.df = new Label(String.valueOf(String.valueOf(this.c(495))).concat(":"));
                    this.dg = new Label(String.valueOf(String.valueOf(this.c(494))).concat(":"));
                    this.dh = new Label(String.valueOf(String.valueOf(this.c(499))).concat(":"));
                    (this.di = new Label(this.c(505))).setAlignment(0);
                    this.df.addMouseListener(this.cz);
                    this.df.addKeyListener(this.cw);
                    this.dg.addMouseListener(this.cz);
                    this.dg.addKeyListener(this.cw);
                    this.dh.addMouseListener(this.cz);
                    this.dh.addKeyListener(this.cw);
                    this.di.addMouseListener(this.cz);
                    this.di.addKeyListener(this.cw);
                    int n4 = 10;
                    int n5 = 10;
                    int n6 = n - 30;
                    final int n7 = 20;
                    this.df.setBounds(n5, n4, n6, n7);
                    this.cs.add(this.df);
                    n4 += 20;
                    this.cu.setBounds(10, n4, n6, n7);
                    this.cs.add(this.cu);
                    n4 += 30;
                    this.dg.setBounds(n5, n4, n6, n7);
                    this.cs.add(this.dg);
                    n4 += 20;
                    this.ct.setBounds(n5, n4, n6, n7);
                    this.cs.add(this.ct);
                    n4 += 40;
                    if (b) {
                        this.dh.setBounds(n5, n4, n6, n7);
                        this.cs.add(this.dh);
                        n4 += 30;
                        this.da = new CheckboxGroup();
                        this.c1 = new acf();
                        n5 += 20;
                        n6 = 100;
                        n3 = n - n6 - 20 - n5;
                        final int n8 = 165;
                        (this.c9 = new Checkbox(this.c(504), this.da, false)).setBounds(n5, n4, n6, n7);
                        this.c9.addItemListener(this.c1);
                        this.c9.addMouseListener(this.cz);
                        this.c9.addKeyListener(this.cw);
                        this.c9.addFocusListener(this.cx);
                        this.cs.add(this.c9);
                        n4 += 30;
                        (this.dd = new jiPanel(this.ed)).setLayout(null);
                        this.dd.setBounds(n5 + n6, n4 - 10 - 30, n3, n8);
                        this.dd.addMouseListener(this.cz);
                        this.dd.addKeyListener(this.cw);
                        this.dw = n3 - 25;
                        this.dx = this.dw - 120;
                        this.dy = this.dw - 120;
                        final int n9 = 120;
                        final int n10 = (n3 - this.dw) / 2;
                        final int n11 = 10;
                        this.dz = 20;
                        this.dj = new Choice();
                        this.dk = new Choice();
                        this.ds = n11 + 30;
                        this.dm.setBounds(n10, n11, this.dx, this.dz);
                        this.dj.setBounds(n10, n11, this.dw / 2, this.dz);
                        this.dj.addFocusListener(this.cx);
                        this.dj.addMouseListener(this.cz);
                        this.dj.addKeyListener(this.cw);
                        this.dk.setBounds(n10 + this.dw + 10, n11, n9, this.dz);
                        this.dk.addFocusListener(this.cx);
                        this.dk.addMouseListener(this.cz);
                        this.dk.addKeyListener(this.cw);
                        this.di.setBounds(n10, n11, this.dw, this.dz);
                        (this.c5 = new Checkbox(this.c(500), this.da, false)).setBounds(n5, n4, n6, n7);
                        this.c5.addMouseListener(this.cz);
                        this.c5.addKeyListener(this.cw);
                        this.c5.addItemListener(this.c1);
                        this.c5.addFocusListener(this.cx);
                        this.cs.add(this.c5);
                        n4 += 30;
                        if (ji.util.d.a(ab)) {
                            (this.c6 = new Checkbox(this.c(501), this.da, false)).setBounds(n5, n4, n6, n7);
                            this.c6.addItemListener(this.c1);
                            this.c6.addMouseListener(this.cz);
                            this.c6.addKeyListener(this.cw);
                            this.c6.addFocusListener(this.cx);
                            this.cs.add(this.c6);
                            n4 += 30;
                        }
                        (this.c7 = new Checkbox(this.c(502), this.da, false)).setBounds(n5, n4, n6, n7);
                        this.c7.addItemListener(this.c1);
                        this.c7.addMouseListener(this.cz);
                        this.c7.addKeyListener(this.cw);
                        this.c7.addFocusListener(this.cx);
                        this.cs.add(this.c7);
                        n4 += 30;
                        (this.c8 = new Checkbox(this.c(503), this.da, false)).setBounds(n5, n4, n6, n7);
                        this.c8.addMouseListener(this.cz);
                        this.c8.addKeyListener(this.cw);
                        this.c8.addItemListener(this.c1);
                        this.c8.addFocusListener(this.cx);
                        this.cs.add(this.c8);
                        n4 += 30;
                        this.dd.add(this.dj);
                        this.dd.add(this.dk);
                        this.dd.add(this.dm);
                        this.dd.add(this.di);
                        this.cs.add(this.dd);
                        n4 += 30;
                    }
                    final int n12 = 85;
                    final int n13 = 25;
                    final int n14 = 10;
                    if (b) {
                        this.b(de, ab);
                    }
                    else {
                        n2 = 180;
                        if (ji.util.d.av(this.ed)) {
                            n2 += 30;
                        }
                    }
                    int n15 = n2 - 45;
                    if (ji.util.d.av(this.ed)) {
                        n15 -= 30;
                    }
                    if (b) {
                        final int n16 = (n3 - 10) / 2;
                        this.db.setBounds(n5 + n6, n15 - 70, n16, n13);
                        this.cs.add(this.db);
                        this.dc.setBounds(n5 + n6 + n16 + 10, n15 - 70, n16, n13);
                        this.cs.add(this.dc);
                    }
                    n15 -= 20;
                    final int n17 = (n - 2 * n12 - n14) / 2;
                    this.c3.setBounds(n17, n15, n12, n13);
                    this.cs.add(this.c3);
                    this.c4.setBounds(n17 + (n14 + n12), n15, n12, n13);
                    this.cs.add(this.c4);
                    this.cr.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - n / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - n2 / 2, n, n2);
                    this.cr.show();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void an() {
        try {
            this.c(302);
            if (this.c9.getState()) {
                this.dt = null;
            }
            else if (this.c5.getState()) {
                if (this.dj.getSelectedIndex() >= 0) {
                    if (this.dv[this.dj.getSelectedIndex()] != null) {
                        this.dt = this.dv[this.dj.getSelectedIndex()].g();
                    }
                    else {
                        this.dt = new ed(0, 1, this.ed).g();
                    }
                }
                else {
                    this.dt = new ed(0, 1, this.ed).g();
                }
            }
            else if (this.c7.getState()) {
                this.dt = new ed(2, this.dm.getText(), this.ed).g();
            }
            else if (this.c8.getState()) {
                final String[] ak = ji.util.e.ak(this.ed);
                int max = 0;
                try {
                    max = Math.max(this.dk.getSelectedIndex(), 0);
                }
                catch (Exception ex) {}
                this.dt = new ed(3, this.dm.getText(), ak[max], this.ed).g();
            }
            else if (this.c6 != null && this.c6.getState()) {
                if (this.dj.getSelectedIndex() >= 0) {
                    if (this.dv[this.dj.getSelectedIndex()] != null) {
                        this.dt = this.dv[this.dj.getSelectedIndex()].g();
                    }
                    else {
                        this.dt = new ed(1, "-", this.ed).g();
                    }
                }
                else {
                    this.dt = new ed(1, "-", this.ed).g();
                }
            }
        }
        catch (Exception ex2) {
            this.dt = null;
        }
    }
    
    private final void b(final dg dg, final ad ad) {
        if (dg.bq()) {
            try {
                if (this.c5 != null && dg != null) {
                    dg.b(ad);
                    int b = -1;
                    this.dn = false;
                    if (dg.c(ad)) {
                        this.db.setLabel(this.c(516));
                        this.dc.setEnabled(true);
                        this.dn = false;
                    }
                    else {
                        this.db.setLabel(this.c(515));
                        this.dc.setEnabled(false);
                        this.dn = true;
                    }
                    if (dg.b(ad) != null) {
                        final ed ed = new ed(dg.b(ad), this.ed);
                        b = ed.b();
                        this.c(302);
                        switch (b) {
                            case 0: {
                                if (this.dr != b || this.dr < 0) {
                                    this.dv = null;
                                    this.dj.removeAll();
                                    final int fu = this.aa.fu();
                                    this.dv = new ed[fu];
                                    for (int i = 0; i < fu; ++i) {
                                        this.dv[i] = new ed(0, i + 1, this.ed);
                                        this.dj.add(this.dv[i].h());
                                    }
                                }
                                this.dj.setBounds(this.dj.getLocation().x, this.ds, this.dw / 2, this.dz);
                                this.dj.select(ed.c() - 1);
                                this.c5.setState(true);
                                this.dj.setVisible(true);
                                this.dm.setVisible(false);
                                this.di.setVisible(false);
                                this.dk.setVisible(false);
                                break;
                            }
                            case 2: {
                                this.dm.setText(ed.h());
                                this.dm.setBounds(this.dm.getLocation().x, this.ds + 60, this.dw, this.dz);
                                this.c7.setState(true);
                                this.dm.setVisible(true);
                                this.di.setVisible(false);
                                this.dj.setVisible(false);
                                this.dk.setVisible(false);
                                break;
                            }
                            case 3: {
                                this.dm.setText(ed.h());
                                if (this.dk.getItemCount() == 0) {
                                    final String[] aj = ji.util.e.aj(this.ed);
                                    for (int j = 0; j < aj.length; ++j) {
                                        this.dk.addItem(aj[j]);
                                    }
                                }
                                final Point location = this.dm.getLocation();
                                this.dm.setBounds(location.x, this.ds + 90, this.dx, this.dz);
                                this.dk.setLocation(location.x + this.dx + 2, this.ds + 90);
                                this.dk.select(ji.util.e.d(ed.a(), this.ed));
                                this.c8.setState(true);
                                this.dk.setVisible(true);
                                this.dm.setVisible(true);
                                this.di.setVisible(false);
                                this.dj.setVisible(false);
                                break;
                            }
                            case 1: {
                                if (this.dr == b && this.dr >= 0) {
                                    break;
                                }
                                this.dj.removeAll();
                                final df cj = this.aa.cj();
                                if (cj != null) {
                                    if (cj.d() > 0) {
                                        final String c = this.c(507);
                                        this.de.d0();
                                        int n = 0;
                                        this.aa.fu();
                                        this.dv = new ed[cj.d()];
                                        int n2 = 0;
                                        final c c2 = new c("newAnnotList2");
                                        final ax g = cj.g();
                                        final k k = new k(cj.d());
                                        while (g.a()) {
                                            final dg b2 = cj.b(g.b());
                                            if (b2.b(this.e, this.d)) {
                                                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a(b2.i(-1), 10)))).append(b2.du()).append(b2.ce()).append(b2.d0())));
                                                c2.a(value, b2);
                                                k.a(value);
                                            }
                                        }
                                        for (int l = 0; l < c2.b(); ++l) {
                                            final dg dg2 = (dg)c2.d(k.b());
                                            final String du = dg2.du();
                                            if (dg2.i(-1) >= 0) {
                                                if (ji.util.d.by(du)) {
                                                    this.dv[n2] = new ed(1, c, this.ed);
                                                }
                                                else {
                                                    this.dv[n2] = new ed(1, du, this.ed);
                                                }
                                                this.dv[n2].a(dg2.i(-1));
                                                ++n2;
                                            }
                                        }
                                        final String g2 = ed.g();
                                        for (int n3 = 0; n3 < this.dv.length; ++n3) {
                                            if (this.dv[n3] != null) {
                                                this.dj.add(this.dv[n3].h());
                                                if (g2.toLowerCase().equals(this.dv[n3].g().toLowerCase())) {
                                                    n = n3;
                                                }
                                            }
                                        }
                                        this.dj.setBounds(this.dj.getLocation().x, this.ds + 30, this.dy, this.dz);
                                        this.dj.select(n);
                                    }
                                    else {
                                        b = -1;
                                    }
                                }
                                else {
                                    b = -1;
                                }
                                if (b >= 0 && this.c6 != null) {
                                    this.c6.setState(true);
                                    this.di.setVisible(false);
                                    this.dj.setVisible(true);
                                    this.dk.setVisible(false);
                                    this.dm.setVisible(false);
                                    break;
                                }
                                break;
                            }
                            default: {
                                b = -1;
                                break;
                            }
                        }
                    }
                    if (b < 0) {
                        this.c9.setState(true);
                        this.dj.setVisible(false);
                        this.dk.setVisible(false);
                        this.dm.setVisible(false);
                        this.di.setVisible(true);
                    }
                    this.dr = b;
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void ao() {
        try {
            if (this.de != null) {
                this.an();
                ji.annotate.dy.au = false;
                final boolean c = ji.util.e.c(this.de.du(), this.ct.getText());
                final boolean c2 = ji.util.e.c(this.de.c9(), this.cu.getText());
                final boolean c3 = ji.util.e.c(this.dt, this.dp);
                final boolean c4 = ji.util.e.c(this.du, this.dq);
                final String text = this.ct.getText();
                String lowerCase;
                if (ji.util.d.by(text)) {
                    lowerCase = "";
                }
                else {
                    lowerCase = text.toLowerCase();
                }
                if (c) {
                    this.de.q(lowerCase.toLowerCase());
                }
                if (c2) {
                    this.de.m(this.cu.getText());
                }
                if (!c3) {
                    this.de.l(this.dp);
                }
                if (!c4) {
                    this.de.a(this.dq, this.ab);
                }
                if (c || c2 || c3 || c4) {
                    if (c3) {
                        this.de.l(this.dt);
                    }
                    if (c4) {
                        this.de.a(this.du, this.ab);
                    }
                    this.b(this.de);
                }
            }
            this.z();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean a(final ad ad) {
        return !ji.util.i.c(228) || !ji.util.d.bf() || !ad.af() || ad.ak() != 0 || !ji.secure.ei.f.equals("(NONE)");
    }
    
    private final void ap() {
        new bb(this.ed, new acy()).start();
    }
    
    static {
        dy.a = new Object();
        dy.s = 0;
        dy.v = 1;
        dy.w = 10;
        dy.ac = 3;
        dy.ad = 2;
        dy.au = true;
        dy.av = true;
        dy.a6 = 100;
        dy.ba = 0;
        dy.bb = 0;
        dy.bv = 2;
        dy.d9 = null;
        ez = Locale.getDefault().getCountry().toLowerCase().startsWith("tr");
    }
    
    class acc extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            ji.annotate.dy.this.z();
        }
    }
    
    class acy implements Runnable
    {
        public void run() {
            try {
                ji.annotate.dy.this.aa.f(false, false);
            }
            catch (Exception ex) {}
        }
    }
    
    class ace implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            try {
                if (actionEvent.getSource().equals(ji.annotate.dy.this.c3)) {
                    ji.annotate.dy.this.ao();
                }
                else if (actionEvent.getSource().equals(ji.annotate.dy.this.c4)) {
                    ji.annotate.dy.this.z();
                }
                else if (actionEvent.getSource().equals(ji.annotate.dy.this.db)) {
                    if (ji.annotate.dy.this.dn) {
                        ji.annotate.dy.this.du = ji.annotate.dy.this.ab.jo();
                    }
                    else {
                        ji.annotate.dy.this.du = null;
                    }
                    ji.annotate.dy.this.de.a(ji.annotate.dy.this.du, ji.annotate.dy.this.ab);
                    ji.annotate.dy.this.b(ji.annotate.dy.this.de, ji.annotate.dy.this.ab);
                }
                else if (actionEvent.getSource().equals(ji.annotate.dy.this.dc)) {
                    ji.annotate.dy.this.ab.a6(ji.annotate.dy.this.de.dc());
                    ji.annotate.dy.this.ap();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class aca extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            try {
                if (mouseEvent.getSource().equals(ji.annotate.dy.this.db) || mouseEvent.getSource().equals(ji.annotate.dy.this.dc)) {
                    ji.annotate.dy.this.c2 = (Component)mouseEvent.getSource();
                }
            }
            catch (Exception ex) {}
            ji.annotate.dy.this.do = true;
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            ji.annotate.dy.this.do = false;
        }
    }
    
    class acb extends KeyAdapter
    {
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 27) {
                    ji.annotate.dy.this.z();
                }
                else if (keyEvent.getKeyCode() == 10 && ji.annotate.dy.this.c3 != null) {
                    if (keyEvent.getSource().equals(ji.annotate.dy.this.c3)) {
                        ji.annotate.dy.this.ao();
                    }
                    else {
                        ji.annotate.dy.this.z();
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class acd extends FocusAdapter
    {
        public void focusGained(final FocusEvent focusEvent) {
            try {
                ji.annotate.dy.this.c2 = (Component)focusEvent.getSource();
            }
            catch (Exception ex) {}
        }
    }
    
    class acf implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            try {
                try {
                    if (ji.annotate.dy.this.dm != null) {
                        ji.annotate.dy.this.dm.setText("");
                    }
                }
                catch (Exception ex2) {}
                try {
                    if (ji.annotate.dy.this.dj != null) {
                        if (itemEvent.getSource().equals(ji.annotate.dy.this.c5) || itemEvent.getSource().equals(ji.annotate.dy.this.c7) || itemEvent.getSource().equals(ji.annotate.dy.this.c8) || itemEvent.getSource().equals(ji.annotate.dy.this.c9)) {
                            ji.annotate.dy.this.dj.removeAll();
                        }
                        if (ji.annotate.dy.this.c6 != null && itemEvent.getSource().equals(ji.annotate.dy.this.c6)) {
                            ji.annotate.dy.this.dj.removeAll();
                        }
                    }
                }
                catch (Exception ex3) {}
                ji.annotate.dy.this.an();
                ji.annotate.dy.this.de.l(ji.annotate.dy.this.dt);
                ji.annotate.dy.this.b(ji.annotate.dy.this.de, ji.annotate.dy.this.ab);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class xc implements Runnable
    {
        af a;
        int b;
        
        public xc(final af a, final int b) {
            this.a = null;
            this.b = 2000;
            this.a = a;
            this.b = b;
        }
        
        public final void run() {
            try {
                ji.util.d.b(this.b, 41, ji.annotate.dy.this.ed);
                this.a.a(new a6(this, 10, ""));
                this.a.a(new a6(this, 16, ""));
                ji.util.e.ag(null);
            }
            catch (Exception ex) {}
            finally {
                this.a = null;
            }
        }
    }
    
    class w6 implements Runnable
    {
        ad a;
        URL b;
        af c;
        String d;
        String e;
        ad f;
        String g;
        String h;
        boolean i;
        boolean[] j;
        boolean[] k;
        boolean l;
        
        public w6(final URL b, final String e, final String d, final ad a, final af c, final ad f, final String g, final String h, final boolean i, final boolean[] j, final boolean[] k) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.i = true;
            this.j = null;
            this.k = null;
            this.l = true;
            this.a = a;
            this.b = b;
            this.e = e;
            this.c = c;
            this.d = d;
            this.f = f;
            this.g = g;
            this.h = h;
            this.i = i;
            this.j = j;
            this.k = k;
        }
        
        public boolean a() {
            return this.l;
        }
        
        public void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //     4: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //     7: invokestatic    ji/util/e.ai:(Ljava/lang/String;)Ljava/lang/String;
            //    10: astore_1       
            //    11: aload_0        
            //    12: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //    15: iconst_1       
            //    16: invokevirtual   ji/document/ad.df:(Z)V
            //    19: aload_0        
            //    20: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //    23: ldc             ""
            //    25: invokestatic    ji/annotate/dy.b:(Lji/annotate/dy;Ljava/lang/String;)Ljava/lang/String;
            //    28: pop            
            //    29: iconst_1       
            //    30: istore_2       
            //    31: aconst_null    
            //    32: astore_3       
            //    33: iconst_0       
            //    34: istore          4
            //    36: iload_2        
            //    37: ifeq            1466
            //    40: invokestatic    ji/util/d.cs:()Z
            //    43: ifeq            58
            //    46: aload_0        
            //    47: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //    50: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //    53: ldc             "sendThread 1"
            //    55: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //    58: iconst_0       
            //    59: istore_2       
            //    60: invokestatic    java/lang/System.currentTimeMillis:()J
            //    63: lstore          5
            //    65: aload_0        
            //    66: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //    69: iconst_0       
            //    70: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;Z)Z
            //    73: pop            
            //    74: aload_0        
            //    75: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //    78: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //    81: ifnull          453
            //    84: aload_0        
            //    85: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //    88: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //    91: invokevirtual   ji/awt/c.b:()I
            //    94: ifle            435
            //    97: aload_0        
            //    98: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   101: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //   104: invokevirtual   ji/awt/c.b:()I
            //   107: ifle            453
            //   110: aload_0        
            //   111: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   114: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //   117: iconst_0       
            //   118: invokevirtual   ji/awt/c.b:(I)Ljava/lang/Object;
            //   121: checkcast       Lji/annotate/df;
            //   124: astore_3       
            //   125: aconst_null    
            //   126: astore          7
            //   128: aload_3        
            //   129: ifnull          142
            //   132: aload_3        
            //   133: aload_0        
            //   134: getfield        ji/annotate/dy$w6.f:Lji/document/ad;
            //   137: invokevirtual   ji/annotate/df.b:(Lji/document/ad;)Lji/annotate/df;
            //   140: astore          7
            //   142: aload_0        
            //   143: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   146: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //   149: iconst_0       
            //   150: invokevirtual   ji/awt/c.d:(I)V
            //   153: iconst_1       
            //   154: istore          8
            //   156: aload_0        
            //   157: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   160: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   163: istore          9
            //   165: iconst_0       
            //   166: istore          10
            //   168: aload_0        
            //   169: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   172: iconst_0       
            //   173: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;Z)Z
            //   176: pop            
            //   177: aload_0        
            //   178: getfield        ji/annotate/dy$w6.e:Ljava/lang/String;
            //   181: invokestatic    ji/util/d.by:(Ljava/lang/String;)Z
            //   184: ifeq            281
            //   187: iload           8
            //   189: ifle            390
            //   192: aload_0        
            //   193: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   196: aload_3        
            //   197: aload_0        
            //   198: getfield        ji/annotate/dy$w6.b:Ljava/net/URL;
            //   201: aload_0        
            //   202: getfield        ji/annotate/dy$w6.d:Ljava/lang/String;
            //   205: aload_0        
            //   206: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   209: aload_0        
            //   210: getfield        ji/annotate/dy$w6.f:Lji/document/ad;
            //   213: aload_0        
            //   214: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //   217: aload_0        
            //   218: getfield        ji/annotate/dy$w6.g:Ljava/lang/String;
            //   221: aload_0        
            //   222: getfield        ji/annotate/dy$w6.j:[Z
            //   225: aload_0        
            //   226: getfield        ji/annotate/dy$w6.k:[Z
            //   229: invokestatic    ji/annotate/dy.a:(Lji/annotate/dy;Lji/annotate/df;Ljava/net/URL;Ljava/lang/String;Ljava/awt/Component;Lji/document/ad;Lji/v1event/af;Ljava/lang/String;[Z[Z)Z
            //   232: dup            
            //   233: istore          10
            //   235: ifne            390
            //   238: iinc            8, -1
            //   241: aload_0        
            //   242: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   245: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   248: ifne            187
            //   251: aload_0        
            //   252: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   255: iconst_0       
            //   256: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;Z)Z
            //   259: pop            
            //   260: invokestatic    ji/util/d.cs:()Z
            //   263: ifeq            187
            //   266: aload_0        
            //   267: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   270: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   273: ldc             "Retrying..."
            //   275: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   278: goto            187
            //   281: iload           8
            //   283: ifle            390
            //   286: aload_0        
            //   287: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   290: aload_3        
            //   291: aload_0        
            //   292: getfield        ji/annotate/dy$w6.b:Ljava/net/URL;
            //   295: aload_0        
            //   296: getfield        ji/annotate/dy$w6.e:Ljava/lang/String;
            //   299: aload_0        
            //   300: getfield        ji/annotate/dy$w6.d:Ljava/lang/String;
            //   303: aload_0        
            //   304: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   307: aload_0        
            //   308: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //   311: aload_0        
            //   312: getfield        ji/annotate/dy$w6.g:Ljava/lang/String;
            //   315: aload_0        
            //   316: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   319: invokevirtual   ji/document/ad.dx:()Z
            //   322: aload_0        
            //   323: getfield        ji/annotate/dy$w6.h:Ljava/lang/String;
            //   326: aload_0        
            //   327: getfield        ji/annotate/dy$w6.i:Z
            //   330: aload_0        
            //   331: getfield        ji/annotate/dy$w6.j:[Z
            //   334: aload_0        
            //   335: getfield        ji/annotate/dy$w6.k:[Z
            //   338: invokestatic    ji/annotate/dy.a:(Lji/annotate/dy;Lji/annotate/df;Ljava/net/URL;Ljava/lang/String;Ljava/lang/String;Lji/document/ad;Lji/v1event/af;Ljava/lang/String;ZLjava/lang/String;Z[Z[Z)Z
            //   341: dup            
            //   342: istore          10
            //   344: ifne            390
            //   347: iinc            8, -1
            //   350: aload_0        
            //   351: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   354: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   357: ifne            281
            //   360: aload_0        
            //   361: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   364: iconst_0       
            //   365: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;Z)Z
            //   368: pop            
            //   369: invokestatic    ji/util/d.cs:()Z
            //   372: ifeq            281
            //   375: aload_0        
            //   376: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   379: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   382: ldc             "Retrying..."
            //   384: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   387: goto            281
            //   390: aload_0        
            //   391: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   394: iload           9
            //   396: invokestatic    ji/annotate/dy.e:(Lji/annotate/dy;Z)Z
            //   399: pop            
            //   400: aload_0        
            //   401: iload           10
            //   403: putfield        ji/annotate/dy$w6.l:Z
            //   406: aload_0        
            //   407: getfield        ji/annotate/dy$w6.l:Z
            //   410: ifne            97
            //   413: aload           7
            //   415: ifnull          97
            //   418: aload_3        
            //   419: ifnull          97
            //   422: aload_3        
            //   423: invokevirtual   ji/annotate/df.j:()V
            //   426: aload_3        
            //   427: aload           7
            //   429: invokevirtual   ji/annotate/df.a:(Lji/annotate/df;)V
            //   432: goto            97
            //   435: invokestatic    ji/util/d.cs:()Z
            //   438: ifeq            453
            //   441: aload_0        
            //   442: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   445: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   448: ldc             "Annotations not sent (empty)"
            //   450: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   453: jsr             595
            //   456: goto            36
            //   459: astore          7
            //   461: aload_0        
            //   462: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   465: iconst_1       
            //   466: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;Z)Z
            //   469: pop            
            //   470: aload_0        
            //   471: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   474: aload           7
            //   476: invokevirtual   java/lang/Throwable.toString:()Ljava/lang/String;
            //   479: invokestatic    ji/annotate/dy.c:(Lji/annotate/dy;Ljava/lang/String;)Ljava/lang/String;
            //   482: pop            
            //   483: invokestatic    ji/util/d.cs:()Z
            //   486: ifeq            501
            //   489: aload_0        
            //   490: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   493: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   496: ldc             "Annotation send failed..."
            //   498: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   501: aload           7
            //   503: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //   506: aload_0        
            //   507: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   510: sipush          479
            //   513: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   516: new             Ljava/lang/StringBuffer;
            //   519: dup            
            //   520: aload_0        
            //   521: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   524: sipush          486
            //   527: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   530: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   533: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   536: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   539: ldc             "\n"
            //   541: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   544: aload           7
            //   546: invokevirtual   java/lang/Throwable.toString:()Ljava/lang/String;
            //   549: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   552: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   555: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   558: aload_0        
            //   559: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   562: bipush          60
            //   564: aconst_null    
            //   565: aconst_null    
            //   566: aload_0        
            //   567: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   570: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   573: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;ILjava/lang/String;Lji/v1event/af;Ljava/lang/String;)V
            //   576: goto            581
            //   579: astore          8
            //   581: jsr             595
            //   584: goto            36
            //   587: astore          11
            //   589: jsr             595
            //   592: aload           11
            //   594: athrow         
            //   595: astore          12
            //   597: iconst_0       
            //   598: istore          13
            //   600: aload_0        
            //   601: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   604: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   607: ifeq            613
            //   610: iconst_1       
            //   611: istore          4
            //   613: invokestatic    ji/util/d.cs:()Z
            //   616: ifne            629
            //   619: aload_0        
            //   620: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   623: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   626: ifeq            1145
            //   629: aload_0        
            //   630: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   633: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //   636: ifeq            1096
            //   639: aload_0        
            //   640: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   643: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   646: new             Ljava/lang/StringBuffer;
            //   649: dup            
            //   650: ldc             "Send complete with errors ("
            //   652: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   655: invokestatic    java/lang/System.currentTimeMillis:()J
            //   658: lload           5
            //   660: lsub           
            //   661: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
            //   664: ldc             " milliseconds)"
            //   666: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   669: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   672: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   675: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   678: invokestatic    ji/util/d.c4:()Z
            //   681: ifne            1091
            //   684: aload_0        
            //   685: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   688: invokestatic    ji/annotate/dy.q:(Lji/annotate/dy;)Ljava/lang/String;
            //   691: ifnull          715
            //   694: aload_0        
            //   695: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   698: invokestatic    ji/annotate/dy.q:(Lji/annotate/dy;)Ljava/lang/String;
            //   701: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
            //   704: ldc             "nullpointer"
            //   706: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
            //   709: iflt            715
            //   712: iconst_1       
            //   713: istore          13
            //   715: iload           13
            //   717: ifne            1083
            //   720: iconst_0       
            //   721: invokestatic    ji/util/d.l:(Z)V
            //   724: bipush          98
            //   726: invokestatic    ji/util/i.c:(I)Z
            //   729: ifeq            868
            //   732: invokestatic    ji/util/d.cs:()Z
            //   735: ifeq            750
            //   738: aload_0        
            //   739: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   742: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   745: ldc             "sendThread qr1"
            //   747: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   750: aload_0        
            //   751: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   754: sipush          479
            //   757: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   760: new             Ljava/lang/StringBuffer;
            //   763: dup            
            //   764: aload_0        
            //   765: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   768: sipush          486
            //   771: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   774: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   777: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   780: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   783: ldc             "\n("
            //   785: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   788: aload_0        
            //   789: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   792: invokestatic    ji/annotate/dy.q:(Lji/annotate/dy;)Ljava/lang/String;
            //   795: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   798: ldc             ")\n"
            //   800: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   803: aload_0        
            //   804: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   807: sipush          489
            //   810: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   813: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   816: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   819: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   822: aload_0        
            //   823: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   826: aload_0        
            //   827: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //   830: aload_0        
            //   831: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   834: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   837: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Lji/v1event/af;Ljava/lang/String;)Z
            //   840: ifeq            1053
            //   843: iconst_1       
            //   844: istore_2       
            //   845: aload_0        
            //   846: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   849: invokestatic    ji/annotate/dy.n:(Lji/annotate/dy;)Lji/awt/c;
            //   852: aload_3        
            //   853: invokevirtual   ji/awt/c.c:(Ljava/lang/Object;)V
            //   856: aload_0        
            //   857: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   860: aconst_null    
            //   861: invokestatic    ji/annotate/dy.b:(Lji/annotate/dy;Ljava/lang/String;)Ljava/lang/String;
            //   864: pop            
            //   865: goto            1053
            //   868: invokestatic    ji/util/d.cs:()Z
            //   871: ifeq            886
            //   874: aload_0        
            //   875: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   878: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   881: ldc             "sendThread f1"
            //   883: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //   886: iconst_0       
            //   887: istore_2       
            //   888: getstatic       ji/document/ad.a:Z
            //   891: ifeq            978
            //   894: aload_0        
            //   895: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   898: sipush          479
            //   901: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   904: new             Ljava/lang/StringBuffer;
            //   907: dup            
            //   908: aload_0        
            //   909: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   912: sipush          486
            //   915: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   918: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   921: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   924: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //   927: ldc             "\n"
            //   929: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   932: ldc             "\n("
            //   934: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   937: aload_0        
            //   938: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   941: invokestatic    ji/annotate/dy.q:(Lji/annotate/dy;)Ljava/lang/String;
            //   944: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   947: ldc             ")"
            //   949: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   952: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   955: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   958: aload_0        
            //   959: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //   962: aconst_null    
            //   963: aconst_null    
            //   964: aload_0        
            //   965: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   968: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //   971: iconst_0       
            //   972: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;Z)V
            //   975: goto            1053
            //   978: aload_0        
            //   979: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   982: sipush          479
            //   985: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //   988: new             Ljava/lang/StringBuffer;
            //   991: dup            
            //   992: aload_0        
            //   993: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //   996: sipush          486
            //   999: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1002: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1005: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1008: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //  1011: ldc             "\n"
            //  1013: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1016: ldc             "\n("
            //  1018: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1021: aload_0        
            //  1022: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1025: invokestatic    ji/annotate/dy.q:(Lji/annotate/dy;)Ljava/lang/String;
            //  1028: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1031: ldc             ")"
            //  1033: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1036: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1039: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1042: aconst_null    
            //  1043: aload_0        
            //  1044: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1047: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1050: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;)V
            //  1053: jsr             1075
            //  1056: goto            1145
            //  1059: astore          14
            //  1061: jsr             1075
            //  1064: goto            1145
            //  1067: astore          15
            //  1069: jsr             1075
            //  1072: aload           15
            //  1074: athrow         
            //  1075: astore          16
            //  1077: iconst_1       
            //  1078: invokestatic    ji/util/d.l:(Z)V
            //  1081: ret             16
            //  1083: iconst_0       
            //  1084: istore_2       
            //  1085: iconst_1       
            //  1086: istore          13
            //  1088: goto            1145
            //  1091: iconst_0       
            //  1092: istore_2       
            //  1093: goto            1145
            //  1096: aload_0        
            //  1097: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1100: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1103: new             Ljava/lang/StringBuffer;
            //  1106: dup            
            //  1107: ldc             "Send complete ("
            //  1109: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //  1112: invokestatic    java/lang/System.currentTimeMillis:()J
            //  1115: lload           5
            //  1117: lsub           
            //  1118: invokevirtual   java/lang/StringBuffer.append:(J)Ljava/lang/StringBuffer;
            //  1121: ldc             " milliseconds)"
            //  1123: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1126: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1129: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1132: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //  1135: goto            1145
            //  1138: astore          14
            //  1140: aload           14
            //  1142: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //  1145: iload           13
            //  1147: ifeq            1242
            //  1150: invokestatic    ji/util/d.cs:()Z
            //  1153: ifeq            1168
            //  1156: aload_0        
            //  1157: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1160: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1163: ldc             "sendThread fs"
            //  1165: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //  1168: aload_0        
            //  1169: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1172: sipush          479
            //  1175: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1178: new             Ljava/lang/StringBuffer;
            //  1181: dup            
            //  1182: aload_0        
            //  1183: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1186: sipush          528
            //  1189: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1192: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1195: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1198: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
            //  1201: ldc             "\n"
            //  1203: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1206: aload_0        
            //  1207: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1210: sipush          529
            //  1213: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1216: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //  1219: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1222: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //  1225: aload_0        
            //  1226: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //  1229: aconst_null    
            //  1230: aconst_null    
            //  1231: aload_0        
            //  1232: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1235: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1238: iconst_0       
            //  1239: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;Z)V
            //  1242: invokestatic    ji/util/d.c4:()Z
            //  1245: ifne            1300
            //  1248: iload           13
            //  1250: ifne            1300
            //  1253: aload_0        
            //  1254: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1257: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //  1260: ifeq            1300
            //  1263: aload_0        
            //  1264: getfield        ji/annotate/dy$w6.f:Lji/document/ad;
            //  1267: new             Lji/v1event/d7;
            //  1270: dup            
            //  1271: aload_0        
            //  1272: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //  1275: bipush          23
            //  1277: aload_0        
            //  1278: getfield        ji/annotate/dy$w6.f:Lji/document/ad;
            //  1281: invokevirtual   ji/document/ad.j7:()I
            //  1284: invokespecial   ji/v1event/d7.<init>:(Ljava/lang/Object;II)V
            //  1287: invokevirtual   ji/document/ad.a:(Lji/v1event/d7;)V
            //  1290: goto            1300
            //  1293: astore          14
            //  1295: aload           14
            //  1297: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //  1300: iload_2        
            //  1301: ifne            1464
            //  1304: invokestatic    ji/util/d.c4:()Z
            //  1307: ifne            1449
            //  1310: iload           13
            //  1312: ifne            1449
            //  1315: iload           4
            //  1317: ifeq            1330
            //  1320: aload_0        
            //  1321: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1324: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //  1327: ifeq            1340
            //  1330: aload_0        
            //  1331: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1334: invokestatic    ji/annotate/dy.x:(Lji/annotate/dy;)Lji/image/cy;
            //  1337: ifnonnull       1449
            //  1340: invokestatic    ji/util/d.cs:()Z
            //  1343: ifeq            1358
            //  1346: aload_0        
            //  1347: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1350: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1353: ldc             "sendThread rc"
            //  1355: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //  1358: iload           4
            //  1360: ifeq            1410
            //  1363: aload_0        
            //  1364: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1367: invokestatic    ji/annotate/dy.o:(Lji/annotate/dy;)Z
            //  1370: ifne            1410
            //  1373: aload_0        
            //  1374: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1377: sipush          479
            //  1380: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1383: aload_0        
            //  1384: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1387: sipush          480
            //  1390: invokestatic    ji/annotate/dy.d:(Lji/annotate/dy;I)Ljava/lang/String;
            //  1393: aload_0        
            //  1394: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //  1397: aconst_null    
            //  1398: aconst_null    
            //  1399: aload_0        
            //  1400: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1403: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1406: iconst_0       
            //  1407: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;Z)V
            //  1410: new             Lji/awt/bb;
            //  1413: dup            
            //  1414: aload_0        
            //  1415: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1418: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1421: new             Lji/annotate/dy$xc;
            //  1424: dup            
            //  1425: aload_0        
            //  1426: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1429: aload_0        
            //  1430: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //  1433: sipush          300
            //  1436: invokespecial   ji/annotate/dy$xc.<init>:(Lji/annotate/dy;Lji/v1event/af;I)V
            //  1439: invokespecial   ji/awt/bb.<init>:(Ljava/lang/String;Ljava/lang/Runnable;)V
            //  1442: astore          14
            //  1444: aload           14
            //  1446: invokevirtual   java/lang/Thread.start:()V
            //  1449: aload_0        
            //  1450: aconst_null    
            //  1451: putfield        ji/annotate/dy$w6.b:Ljava/net/URL;
            //  1454: aload_0        
            //  1455: aconst_null    
            //  1456: putfield        ji/annotate/dy$w6.d:Ljava/lang/String;
            //  1459: aload_0        
            //  1460: aconst_null    
            //  1461: putfield        ji/annotate/dy$w6.f:Lji/document/ad;
            //  1464: ret             12
            //  1466: jsr             1480
            //  1469: goto            1584
            //  1472: astore          17
            //  1474: jsr             1480
            //  1477: aload           17
            //  1479: athrow         
            //  1480: astore          18
            //  1482: aconst_null    
            //  1483: invokestatic    ji/util/e.ag:(Ljava/lang/String;)V
            //  1486: aload_0        
            //  1487: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //  1490: ifnull          1519
            //  1493: aload_0        
            //  1494: getfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //  1497: new             Lji/v1event/a6;
            //  1500: dup            
            //  1501: aload_0        
            //  1502: bipush          16
            //  1504: ldc             ""
            //  1506: invokespecial   ji/v1event/a6.<init>:(Ljava/lang/Object;ILjava/lang/String;)V
            //  1509: invokeinterface ji/v1event/af.a:(Lji/v1event/a6;)V
            //  1514: goto            1519
            //  1517: astore          19
            //  1519: aload_0        
            //  1520: aconst_null    
            //  1521: putfield        ji/annotate/dy$w6.c:Lji/v1event/af;
            //  1524: aload_0        
            //  1525: getfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //  1528: iconst_0       
            //  1529: invokevirtual   ji/document/ad.df:(Z)V
            //  1532: jsr             1546
            //  1535: goto            1582
            //  1538: astore          20
            //  1540: jsr             1546
            //  1543: aload           20
            //  1545: athrow         
            //  1546: astore          21
            //  1548: aload_0        
            //  1549: aconst_null    
            //  1550: putfield        ji/annotate/dy$w6.a:Lji/document/ad;
            //  1553: invokestatic    ji/util/d.cs:()Z
            //  1556: ifeq            1571
            //  1559: aload_0        
            //  1560: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1563: invokestatic    ji/annotate/dy.ag:(Lji/annotate/dy;)Ljava/lang/String;
            //  1566: ldc             "Send thread finishing"
            //  1568: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
            //  1571: aload_0        
            //  1572: getfield        ji/annotate/dy$w6.m:Lji/annotate/dy;
            //  1575: aconst_null    
            //  1576: invokestatic    ji/annotate/dy.b:(Lji/annotate/dy;Lji/awt/bb;)Lji/awt/bb;
            //  1579: pop            
            //  1580: ret             21
            //  1582: ret             18
            //  1584: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  74     453    459    587    Ljava/lang/Exception;
            //  506    576    579    581    Ljava/lang/Exception;
            //  74     587    587    595    Any
            //  720    1053   1059   1067   Ljava/lang/Exception;
            //  720    1067   1067   1075   Any
            //  600    1135   1138   1145   Ljava/lang/Exception;
            //  1253   1290   1293   1300   Ljava/lang/Exception;
            //  11     1472   1472   1480   Any
            //  1482   1514   1517   1519   Ljava/lang/Exception;
            //  1519   1538   1538   1546   Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Inconsistent stack size at #0036 (coming from #0593).
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2181)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
    }
    
    class z9 implements Runnable
    {
        public void run() {
            if (ji.annotate.dy.this.aa != null) {
                ji.annotate.dy.this.aa.a(new ob(ji.annotate.dy.this.aa, 2));
            }
        }
    }
    
    class vb implements b0
    {
        long a;
        boolean b;
        String c;
        boolean d;
        bj e;
        bw f;
        
        public vb(final bj e, final long a, final boolean b, final String c, final boolean d) {
            this.a = -1L;
            this.b = true;
            this.c = null;
            this.d = false;
            this.e = null;
            this.f = null;
            this.e = e;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        public void a(final bw f) {
            this.f = f;
        }
        
        public final void b() {
            if (this.f != null) {
                this.f.requestFocus();
            }
        }
        
        public final bj c() {
            return this.e;
        }
        
        public final long d() {
            return this.a;
        }
        
        public void a(final bw bw, final String s, final boolean b) {
            boolean b2 = false;
            dg b3 = null;
            try {
                boolean a = false;
                boolean b4 = true;
                b3 = ji.annotate.dy.this.aa.cj().b("".concat(String.valueOf(String.valueOf(this.a))));
                if (b3 != null) {
                    if (!b || ji.annotate.dy.this.cb) {
                        if (ji.util.d.f() > 0 && s.length() > ji.util.d.f()) {
                            ji.util.d.a(ji.res.ay.a(), ji.util.d.b(ji.annotate.dy.this.c(1090), "<#>", "".concat(String.valueOf(String.valueOf(ji.util.d.f())))), ji.annotate.dy.this.aa, ji.annotate.dy.this.ed);
                            b4 = false;
                            ji.annotate.dy.this.e6 = bw.a();
                            if (ji.annotate.dy.this.e5 == null) {
                                ji.annotate.dy.this.e5 = this.c;
                            }
                        }
                        a = ji.annotate.dy.this.a(b3, s, this.c, this.d);
                    }
                    else if (b && ji.annotate.dy.this.e5 != null) {
                        b3.a(ji.annotate.dy.this.aa, ji.annotate.dy.this.e5);
                        ji.annotate.dy.this.e5 = null;
                    }
                    if (b4) {
                        ji.annotate.dy.this.e5 = null;
                        ji.annotate.dy.this.aa.c(b3, a, this.b);
                    }
                    else {
                        b2 = true;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                this.a();
            }
            if (b2) {
                ji.annotate.dy.this.aa.b(b3, this.b, this.d);
            }
        }
        
        public final void a() {
            Label_0070: {
                try {
                    if (ji.annotate.dy.this.e2 != null) {
                        Block_5: {
                            for (int i = 0; i < ji.annotate.dy.this.e2.b(); ++i) {
                                if (((vb)ji.annotate.dy.this.e2.b(i)).equals(this)) {
                                    break Block_5;
                                }
                            }
                            break Label_0070;
                        }
                        int i = 0;
                        ji.annotate.dy.this.e2.d(i);
                    }
                }
                catch (Exception ex) {}
                try {
                    if (ji.annotate.dy.this.b6 != null) {
                        Block_9: {
                            for (int j = 0; j < ji.annotate.dy.this.b6.b(); ++j) {
                                if (((bj)ji.annotate.dy.this.b6.b(j)).equals(this.e)) {
                                    break Block_9;
                                }
                            }
                            break Label_0070;
                        }
                        int j = 0;
                        ji.annotate.dy.this.b6.d(j);
                    }
                }
                catch (Exception ex2) {}
            }
            this.e = null;
            this.f = null;
        }
        
        public void a(final bw bw, final FocusEvent focusEvent) {
            ji.annotate.dy.this.e3 = true;
        }
        
        public void b(final bw bw, final FocusEvent focusEvent) {
            ji.annotate.dy.this.e3 = false;
        }
    }
    
    class vo implements Runnable
    {
        Graphics a;
        Component b;
        af c;
        cy d;
        
        public vo(final Graphics a, final Component b, final cy d, final af c) {
            this.d = null;
            this.a = a;
            this.b = b;
            this.d = d;
            this.c = c;
        }
        
        public void run() {
            try {
                if (this.d != null) {
                    ji.annotate.dy.this.a(this.d.b8(), this.d, this.c);
                }
                else {
                    ji.annotate.dy.this.a(this.a, this.d, this.c);
                }
            }
            catch (Exception ex) {}
            finally {
                this.a = null;
                this.b = null;
                this.d = null;
                this.c = null;
                ji.annotate.dy.this.ck = false;
                ji.annotate.dy.this.cp = null;
            }
        }
    }
    
    class vn implements Runnable
    {
        Graphics a;
        da b;
        Component c;
        af d;
        dg e;
        long f;
        cy g;
        
        public vn(final Graphics a, final da b, final Component c, final cy g, final af d, final dg e) {
            this.e = null;
            this.f = 0L;
            this.g = null;
            this.a = a;
            this.c = c;
            this.g = g;
            this.d = d;
            this.e = e;
            this.b = b;
        }
        
        public void a(final dg e, final da b) {
            this.e = e;
            this.b = b;
        }
        
        public void run() {
            synchronized (ji.annotate.dy.this.e4) {
                try {
                    ji.annotate.dy.this.ec = true;
                    while (ji.annotate.dy.this.ec) {
                        ji.annotate.dy.this.ec = false;
                        final int n = 250;
                        boolean b = false;
                        if ((!ji.util.d.ls || !this.e.b(ji.annotate.dy.this.g)) && this.e.b(ji.annotate.dy.this.e, ji.annotate.dy.this.d)) {
                            if (this.e.aa() > 0 && this.e.dy()) {
                                while (this.e.aa() > 0 && this.e.dy()) {
                                    if (System.currentTimeMillis() - this.e.aa() >= n) {
                                        b = true;
                                        break;
                                    }
                                    ji.util.d.b(100, 4000, ji.annotate.dy.this.ed);
                                }
                            }
                            else if (this.e.aa() <= 0 && this.e.dy()) {
                                while (this.e.dy()) {
                                    if (this.g.ci() == 0) {
                                        b = true;
                                        break;
                                    }
                                    ji.util.d.b(100, 4000, ji.annotate.dy.this.ed);
                                }
                            }
                        }
                        else if (this.e.ac() > 0 && this.e.dy()) {
                            while (this.e.ac() > 0 && this.e.dy()) {
                                if (System.currentTimeMillis() - this.e.ac() >= n) {
                                    if (this.g.ci() == 0) {
                                        b = true;
                                        break;
                                    }
                                    ji.util.d.b(100, 4000, ji.annotate.dy.this.ed);
                                }
                                else {
                                    ji.util.d.b(100, 4000, ji.annotate.dy.this.ed);
                                }
                            }
                        }
                        else if (this.e.ac() <= 0 && this.e.dy()) {
                            while (this.e.dy()) {
                                if (this.g.ci() == 0) {
                                    b = true;
                                    break;
                                }
                                ji.util.d.b(100, 4000, ji.annotate.dy.this.ed);
                            }
                        }
                        if (b && this.e.dy()) {
                            ji.annotate.dy.this.co = this.b;
                            this.e.ab();
                            this.e.ad();
                            if (this.g != null) {
                                while (ji.annotate.dy.this.e0 || ji.annotate.dy.this.e1) {
                                    ji.util.d.b(100, 4001, ji.annotate.dy.this.ed);
                                }
                                ji.annotate.dy.this.a(this.g.b8(), this.g, this.d);
                            }
                            else {
                                ji.annotate.dy.this.a(this.a, this.g, this.d);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
                finally {
                    ji.annotate.dy.this.e8 = null;
                    this.e = null;
                    this.b = null;
                    this.c = null;
                    this.g = null;
                    this.d = null;
                    this.a = null;
                }
            }
            // monitorexit(dy.c(this.h))
        }
    }
    
    class aam implements Runnable
    {
        int a;
        int b;
        boolean c;
        
        public aam(final int a, final int b, final boolean c) {
            this.a = 0;
            this.b = 0;
            this.c = true;
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        public final void run() {
            try {
                ji.util.d.b(ji.util.e.bc, 37, ji.annotate.dy.this.ed);
                if (ji.annotate.dy.this.bk && ji.annotate.dy.this.bj >= 0) {
                    ji.annotate.dy.this.bl = true;
                    ji.annotate.dy.this.bo = System.currentTimeMillis();
                    ji.annotate.dy.this.a(new d8(ji.annotate.dy.this.aa, 1, this.a, this.b, ji.annotate.dy.this.aa.y(), this.c));
                }
            }
            catch (Exception ex) {}
            finally {
                ji.annotate.dy.this.bm = null;
            }
        }
    }
    
    class aac implements Runnable
    {
        public void run() {
            try {
                ji.annotate.dy.this.aa.b(ji.annotate.dy.this.aa.b3(), false, ji.util.d.lv);
            }
            catch (Exception ex) {}
        }
    }
    
    class xf
    {
        int a;
        int b;
        String c;
        String d;
        da e;
        int f;
        String g;
        int h;
        Image i;
        Image j;
        Image k;
        Image l;
        Image m;
        int n;
        int o;
        int p;
        int q;
        String r;
        boolean s;
        boolean t;
        int u;
        
        public xf(final String s, final int n, final int n2, final int n3, final int n4, final String s2) {
            this(ji.annotate.dy.this, s, n, n2, n3, n4, -1, s2);
        }
        
        public xf(final String c, final int a, final int b, final int h, final int n, final int o, final String d) {
            this.d = null;
            this.e = new da(0L, 0L, 0L, 0L);
            this.f = 0;
            this.g = null;
            this.h = -1;
            this.i = null;
            this.j = null;
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = 0;
            this.o = 0;
            this.p = 11;
            this.q = 11;
            this.r = ji.util.d.ai(4);
            this.s = true;
            this.t = true;
            this.u = 0;
            this.c = c;
            this.a = a;
            this.b = b;
            this.h = h;
            this.n = n;
            this.o = o;
            this.d = d;
            ji.annotate.dy.ba = Math.max(a, this.a);
            ji.annotate.dy.bb = Math.max(b, this.b);
            ji.annotate.dy.this.bd += a + 1;
            ji.annotate.dy.this.be += b + 1;
            ++ji.annotate.dy.this.bc;
        }
        
        public String a() {
            return this.d;
        }
        
        public void a(final boolean s) {
            this.s = s;
        }
        
        public boolean b() {
            return this.s;
        }
        
        public void b(final boolean t) {
            this.t = t;
        }
        
        public boolean c() {
            return this.t;
        }
        
        public Image a(final af af) {
            return this.a(af, null);
        }
        
        public Image a(final af af, final Color color) {
            if (this.s) {
                return this.b(af, color);
            }
            return this.b(af);
        }
        
        public final void a(final int u) {
            this.u = u;
        }
        
        public final int d() {
            return this.u;
        }
        
        public Image b(final af af, final Color color) {
            try {
                if (this.u == 0) {
                    if (color != null) {
                        if (this.j == null) {
                            this.j = ji.res.z.a(this.n, 0, this.p, this.q, ji.annotate.dy.this.aa, this.r, ji.util.d.b(this.r, ".gif", ".v1"), af, color, ji.annotate.dy.this.ed);
                        }
                        return this.j;
                    }
                    if (this.i == null) {
                        this.i = ji.res.z.a(this.n, 0, this.p, this.q, ji.annotate.dy.this.aa, this.r, ji.util.d.b(this.r, ".gif", ".v1"), af, null, ji.annotate.dy.this.ed);
                    }
                }
                else {
                    if (color != null) {
                        if (this.l == null) {
                            this.l = ji.res.z.a(this.o, 0, this.p, this.q, ji.annotate.dy.this.aa, this.r, ji.util.d.b(this.r, ".gif", ".v1"), af, color, ji.annotate.dy.this.ed);
                        }
                        return this.l;
                    }
                    if (this.k == null) {
                        this.k = ji.res.z.a(this.o, 0, this.p, this.q, ji.annotate.dy.this.aa, this.r, ji.util.d.b(this.r, ".gif", ".v1"), af, null, ji.annotate.dy.this.ed);
                    }
                    return this.k;
                }
            }
            catch (Exception ex) {}
            return this.i;
        }
        
        public Image b(final af af) {
            try {
                if (this.m == null) {
                    this.m = ji.res.z.a(this.n, 1, this.p, this.q, ji.annotate.dy.this.aa, this.r, ji.util.d.b(this.r, ".gif", ".v1"), af, null, ji.annotate.dy.this.ed);
                }
            }
            catch (Exception ex) {}
            return this.m;
        }
        
        public String e() {
            return this.c;
        }
        
        public dc a(final jiPanel jiPanel) {
            return new dc(this.a, this.b);
        }
        
        public void a(final da da) {
            this.e.a = da.a;
            this.e.b = da.b;
            this.e.c = da.c;
            this.e.d = da.d;
        }
        
        public da f() {
            return this.e;
        }
        
        public void b(final int f) {
            this.f = f;
        }
        
        public int g() {
            return this.f;
        }
        
        public String h() {
            return String.valueOf(String.valueOf(this.i())).concat(String.valueOf(String.valueOf(ji.annotate.dy.this.e7)));
        }
        
        private String i() {
            if (this.g != null) {
                return this.g;
            }
            if (this.h >= 0) {
                return this.g = ji.annotate.dy.this.c(this.h);
            }
            return null;
        }
    }
}
