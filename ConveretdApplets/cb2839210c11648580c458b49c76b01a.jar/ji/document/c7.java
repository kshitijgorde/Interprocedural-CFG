// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.v1event.g9;
import java.awt.event.ItemEvent;
import ji.v1event.fz;
import ji.annotate.df;
import java.awt.SystemColor;
import ji.v1event.b;
import ji.v1event.ae;
import ji.v1event.a6;
import java.awt.event.ItemListener;
import ji.awt.fu;
import ji.awt.ft;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Dimension;
import ji.v1event.fv;
import java.awt.event.ActionEvent;
import ji.v1event.a3;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Vector;
import ji.image.dv;
import ji.v1event.a9;
import ji.v1event.d7;
import ji.v1event.ag;
import java.net.URL;
import ji.res.z;
import ji.util.y;
import java.awt.Component;
import ji.v1event.as;
import ji.io.h;
import ji.util.d;
import ji.util.e;
import java.awt.Graphics;
import ji.util.i;
import ji.ext.v;
import ji.v1event.a2;
import ji.awt.fm;
import ji.net.a0;
import java.awt.Point;
import java.awt.Rectangle;
import ji.v1base.bz;
import ji.graphic.b3;
import ji.image.o7;
import java.awt.Cursor;
import ji.awt.bb;
import ji.image.cy;
import java.awt.Color;
import ji.image.dx;
import ji.awt.c;
import ji.filter.ck;
import ji.io.ac;
import ji.v1event.cx;
import ji.v1event.ak;
import java.awt.event.MouseMotionListener;
import ji.v1event.af;
import java.awt.event.MouseListener;
import ji.v1base.jiPanel;

class c7 extends jiPanel implements MouseListener, af, MouseMotionListener, ak, cx
{
    private int a;
    private int b;
    private vz[] c;
    private vz[] d;
    private boolean[] e;
    private boolean[] f;
    private boolean[] g;
    private boolean[] h;
    private boolean i;
    private int j;
    private int k;
    private int l;
    private ac m;
    private boolean n;
    private ck o;
    private c p;
    private dx q;
    private int r;
    private int s;
    private boolean t;
    private String[] u;
    private String v;
    private String[] w;
    private Color[] x;
    private Color[] y;
    private uw z;
    private int[] aa;
    private long[] ab;
    private es ac;
    private String[] ad;
    private int[] ae;
    private long[] af;
    private int ag;
    private String[] ah;
    private Object[] ai;
    private int aj;
    private boolean ak;
    private c al;
    private int am;
    private c an;
    private c ao;
    private int ap;
    private cy aq;
    private boolean ar;
    private cy as;
    private boolean at;
    private String au;
    private String av;
    private bb aw;
    private int ax;
    private int ay;
    private int az;
    private int a0;
    private int a1;
    private boolean a2;
    private c a3;
    private boolean a4;
    private Cursor a5;
    private Cursor a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private o7[] ba;
    private b3 bb;
    private bz bc;
    private bz bd;
    private boolean be;
    private b3 bf;
    private boolean bg;
    private boolean bh;
    private boolean bi;
    private int bj;
    private final ux bk;
    private Rectangle bl;
    private int bm;
    private Color bn;
    private Point bo;
    private Point bp;
    private boolean bq;
    private boolean br;
    private boolean bs;
    private boolean bt;
    private boolean bu;
    private boolean bv;
    private c6 bw;
    private static int bx;
    private String by;
    private boolean bz;
    private String b0;
    private boolean b1;
    private boolean b2;
    private boolean b3;
    private boolean b4;
    private Object[] b5;
    private a0 b6;
    private String b7;
    private int b8;
    private boolean b9;
    private boolean ca;
    private boolean[] cb;
    private boolean cc;
    private boolean cd;
    private int ce;
    private boolean cf;
    private boolean cg;
    private boolean ch;
    private boolean ci;
    private boolean cj;
    private boolean ck;
    private boolean cl;
    private boolean cm;
    private boolean cn;
    private boolean co;
    private boolean cp;
    private boolean cq;
    private static int cr;
    private int cs;
    private ad ct;
    private String cu;
    private aan cv;
    private int cw;
    private aao cx;
    private long cy;
    private int cz;
    private int c0;
    private int c1;
    private int c2;
    private Point c3;
    private Point c4;
    private long c5;
    private boolean c6;
    private long c7;
    private int c8;
    private c c9;
    private int da;
    private fm db;
    private fm dc;
    private es dd;
    private fm de;
    private boolean df;
    private boolean dg;
    private boolean dh;
    private boolean di;
    private boolean dj;
    private boolean dk;
    private static uz dl;
    private wn dm;
    private wo dn;
    private wp do;
    private int dp;
    private int dq;
    private int dr;
    private int ds;
    private boolean dt;
    private boolean du;
    private boolean dv;
    private a2 dw;
    private a2 dx;
    private int dy;
    private int dz;
    private Object d0;
    private Object[] d1;
    private Color d2;
    private Color[] d3;
    private Object d4;
    private xg d5;
    private boolean d6;
    private boolean d7;
    private int d8;
    private v d9;
    private boolean ea;
    private boolean eb;
    private String ec;
    private Color ed;
    private uy ee;
    private uy ef;
    private boolean eg;
    private boolean eh;
    private af ei;
    private boolean ej;
    private int ek;
    private boolean el;
    private boolean em;
    private String en;
    private Object eo;
    
    public final void a(final boolean eg) {
        try {
            this.eg = eg;
        }
        catch (Exception ex) {}
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public static final boolean a(final int n) {
        return n == 0 || n == 2;
    }
    
    public final boolean a() {
        return a(this.da);
    }
    
    public final boolean b() {
        return ji.util.i.c(115) && (this.da == 3 || this.da == 2);
    }
    
    private final void bh() {
        if (this.as == null) {
            (this.as = new cy(this.ct, this.ei, true, this.cu, this.cw, false, false)).aj(false);
            this.as.c0(false);
            this.as.be(this.be);
        }
    }
    
    public final void a(final c c9) {
        this.c9 = c9;
        if (this.aq != null) {
            this.aq.a(c9);
        }
    }
    
    public final void b(final boolean cp) {
        this.cp = cp;
    }
    
    public void paint(final Graphics graphics) {
        this.paintComponent(graphics);
    }
    
    public void paintComponent(final Graphics graphics) {
        if (!this.a()) {
            this.b3();
        }
        else if (this.b()) {
            if (this.ef != null) {
                this.ef.paintComponent(graphics);
            }
        }
        else if (this.ee != null) {
            this.ee.paintComponent(graphics);
        }
    }
    
    public void c() {
        ji.util.e.a(new ws(this), this.cu);
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        if (this.ee != null) {
            this.ee.addMouseListener(mouseListener);
        }
        if (this.ef != null) {
            this.ef.addMouseListener(mouseListener);
        }
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        if (this.ee != null) {
            this.ee.removeMouseListener(mouseListener);
        }
        if (this.ef != null) {
            this.ef.removeMouseListener(mouseListener);
        }
    }
    
    public c7(final c6 bw, final ad ct, final af ei, final String cu) {
        super(cu);
        this.a = 100;
        this.b = 90;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = 3;
        this.k = 1;
        this.l = 1;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = 0;
        this.s = 0;
        this.t = false;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = new uw((aeb)null);
        this.aa = null;
        this.ab = null;
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ag = -1;
        this.ah = null;
        this.ai = null;
        this.aj = 0;
        this.ak = true;
        this.am = 0;
        this.ao = null;
        this.ap = 3;
        this.aq = null;
        this.ar = false;
        this.as = null;
        this.at = false;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ax = 0;
        this.ay = 0;
        this.az = 0;
        this.a0 = 0;
        this.a1 = 0;
        this.a2 = false;
        this.a4 = false;
        this.a5 = new Cursor(0);
        this.a6 = new Cursor(12);
        this.a7 = false;
        this.a8 = false;
        this.a9 = false;
        this.ba = null;
        this.bb = new b3();
        this.bc = null;
        this.bd = null;
        this.be = true;
        this.bf = new b3();
        this.bg = false;
        this.bh = false;
        this.bi = false;
        this.bj = 6;
        this.bk = new ux();
        this.bl = new Rectangle(0, 0, 0, 0);
        this.bm = 6;
        this.bn = ji.util.e.a0();
        this.bo = new Point(0, 0);
        this.bp = new Point(0, 0);
        this.bq = false;
        this.br = false;
        this.bs = false;
        this.bt = true;
        this.bu = true;
        this.bv = false;
        this.bw = null;
        this.by = null;
        this.bz = false;
        this.b0 = null;
        this.b1 = false;
        this.b2 = true;
        this.b3 = false;
        this.b4 = false;
        this.b5 = null;
        this.b6 = null;
        this.b7 = null;
        this.b8 = 0;
        this.b9 = true;
        this.ca = false;
        this.cb = null;
        this.cc = false;
        this.cd = false;
        this.ce = 0;
        this.cf = true;
        this.cg = false;
        this.ch = true;
        this.ci = false;
        this.cj = true;
        this.ck = true;
        this.cl = false;
        this.cm = false;
        this.cn = false;
        this.co = false;
        this.cp = true;
        this.cq = false;
        this.cs = 0;
        this.ct = null;
        this.cu = null;
        this.cv = null;
        this.cw = 0;
        this.cx = null;
        this.cy = -1L;
        this.cz = -1;
        this.c0 = -1;
        this.c1 = -1;
        this.c2 = -1;
        this.c3 = new Point(0, 0);
        this.c4 = new Point(0, 0);
        this.c5 = -1L;
        this.c6 = false;
        this.c7 = -1L;
        this.c8 = -1;
        this.c9 = null;
        this.da = 0;
        this.db = null;
        this.dc = null;
        this.dd = null;
        this.de = null;
        this.df = false;
        this.dg = false;
        this.dh = false;
        this.di = false;
        this.dj = false;
        this.dk = false;
        this.dm = null;
        this.dn = null;
        this.do = null;
        this.dp = -1;
        this.dq = -1;
        this.dr = -1;
        this.ds = 0;
        this.dt = false;
        this.du = false;
        this.dv = false;
        this.dw = null;
        this.dx = null;
        this.dy = 1;
        this.dz = 1;
        this.d0 = null;
        this.d1 = null;
        this.d2 = null;
        this.d3 = null;
        this.d4 = null;
        this.d5 = null;
        this.d6 = false;
        this.d7 = false;
        this.d8 = 0;
        this.d9 = null;
        this.ea = false;
        this.eb = false;
        this.ec = null;
        this.ed = null;
        this.ee = null;
        this.ef = null;
        this.eg = false;
        this.eh = false;
        this.ei = null;
        this.ej = false;
        this.ek = 0;
        this.el = false;
        this.em = false;
        this.en = null;
        this.eo = new Object();
        try {
            this.cu = cu;
            this.bc = new bz(cu);
            this.bd = new bz(cu);
            this.ei = ei;
            this.setId("thumbs");
            this.setAllowClearInside(false);
            if (ji.util.d.dv()) {
                ji.io.h.e(cu, "jiThumbA1");
            }
            this.aq = new cy(ct, ei, true, cu, this.cw, false, false);
            if (ji.util.d.dv()) {
                ji.io.h.e(cu, "jiThumbA2");
            }
            this.aq.v(this.v);
            this.cc = false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.ct = ct;
        try {
            this.bw = bw;
            if (ji.util.d.dv()) {
                ji.io.h.e(cu, "jiThumbA3");
            }
            this.d();
            if (ji.util.d.dv()) {
                ji.io.h.e(cu, "jiThumbA4");
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        try {
            if (this.d5 == null) {
                this.d5 = new xg();
            }
            this.addWheelListener(this.d5, cu);
        }
        catch (Exception ex3) {
            ex3.printStackTrace();
        }
    }
    
    public final void d() throws Exception {
        try {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb1");
            }
            ++ji.document.c7.cr;
            this.cs = ji.document.c7.cr;
            this.ee = new uy(this.cu);
            this.ef = new uy(this.cu);
            this.add(this.ee);
            this.add(this.ef);
            this.setAcceptFocus(true);
            this.setRightMouseEmulator((ji.util.d.aj(this.cu) || ji.util.d.ay(this.cu)) && ji.util.d.di() != 1);
            this.bd.setVisible(false);
            this.bc.setVisible(false);
            ji.util.e.a(this);
            ji.util.e.a(this.bd);
            ji.util.e.a(this.bc);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb2");
            }
            this.setBorderStyle(0);
            this.setShadowWidth(0);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb3");
            }
            this.bd.setBorderStyle(0);
            this.bc.setBorderStyle(0);
            if (this.bj == 5) {
                this.bj = 4;
            }
            this.bf.a(this.bj);
            this.bb.a(1);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb4");
            }
            this.aq.aj(false);
            this.aq.c0(false);
            if (this.as != null) {
                this.as.aj(false);
                this.as.c0(false);
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb5");
            }
            this.ee.addMouseListener(this);
            this.ee.addMouseMotionListener(this);
            this.ef.addMouseListener(this);
            this.ef.addMouseMotionListener(this);
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb51");
            }
            this.b7 = String.valueOf(String.valueOf(this.v(315))).concat("...");
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb6");
            }
            if (ji.util.d.am()) {
                (this.dw = new a2("jiThumbsAreaEvents", this.cu)).b(this);
            }
            if (ji.util.d.am()) {}
            this.bt();
            this.bu();
            this.bv();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbA4 - jb7");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void c(final boolean be) {
        try {
            this.be = be;
            if (this.as != null) {
                this.as.be(be);
            }
        }
        catch (Exception ex) {}
    }
    
    public Object[] e() {
        return this.ai;
    }
    
    public void a(final Object[] ai, final boolean b) {
        if (ai != null && ji.util.i.c(115)) {
            this.bh();
            if (ai instanceof String[]) {
                if (b) {
                    this.ai = new Object[ai.length];
                    for (int i = 0; i < ai.length; ++i) {
                        this.ai[i] = ji.util.d.b(this.bi(), (String)ai[i], this.cu);
                    }
                }
                else {
                    final y y = new y();
                    this.ai = new Object[ai.length];
                    final String an = ji.util.e.an();
                    for (int j = 0; j < ai.length; ++j) {
                        final c e = ji.util.d.e(ji.res.z.a((String)ai[j], an, 3, null, null, this.cu, y));
                        String s = null;
                        if (e != null) {
                            s = (String)e.d("filename");
                        }
                        if (s == null) {
                            s = (String)e.d("page1");
                        }
                        if (s != null || e.d("fileList") != null) {}
                        if (s != null) {
                            this.ai[j] = ji.util.d.b(this.bi(), String.valueOf(String.valueOf(s)).concat("#1"), this.cu);
                        }
                        else {
                            this.ai[j] = null;
                        }
                    }
                }
            }
            else {
                this.ai = ai;
            }
        }
    }
    
    private URL bi() {
        return ji.util.e.am();
    }
    
    private String v(final int n) {
        return ji.util.d.b(n, this.cu);
    }
    
    public final void setEnabled(final boolean ch) {
        this.ch = ch;
    }
    
    public final boolean f() {
        return this.cg;
    }
    
    public void setRightMouseEmulator(final boolean rightMouseEmulator) {
        super.setRightMouseEmulator(rightMouseEmulator);
    }
    
    public boolean isRightMouseEmulator() {
        return super.isRightMouseEmulator();
    }
    
    public final void a(final ag ag) {
        if (this.a3 != null && this.a3.a(ag)) {
            this.a3.b(ag);
        }
    }
    
    public final void b(final ag ag) {
        if (this.a3 == null) {
            this.a3 = new c("jiThumbs2", 2);
        }
        if (!this.a3.a(ag)) {
            this.a3.c(ag);
        }
    }
    
    protected final void a(final d7 d7) {
        try {
            if (ji.util.d.am()) {
                if (this.dw != null) {
                    this.dw.a(new a9(this, d7, false));
                }
            }
            else {
                this.b(d7);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final d7 d7) {
        try {
            if (this.a3 != null) {
                final c a3 = this.a3;
                for (int b = a3.b(), i = 0; i < b; ++i) {
                    if (!d7.l()) {
                        ((ag)a3.b(i)).a(d7);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void d(final boolean ca) {
        this.ca = ca;
    }
    
    public boolean g() {
        return this.br;
    }
    
    public final int h() {
        if (this.aq != null) {
            return this.aq.dk();
        }
        return ji.util.d.du;
    }
    
    public final int i() {
        if (this.aq != null) {
            return this.aq.dl();
        }
        return ji.util.d.dv;
    }
    
    public final int j() {
        if (this.aq != null) {
            return this.aq.dm();
        }
        return ji.util.d.dw;
    }
    
    public void a(final String s) {
        try {
            if (this.aq != null) {
                this.aq.a(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final int n) {
        try {
            if (this.aq != null && this.h() != n) {
                this.aq.s(n);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void c(final int n) {
        try {
            if (this.aq != null && this.i() != n) {
                this.aq.t(n);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void e(final boolean b) {
        this.aq.bp(b);
    }
    
    public boolean k() {
        return this.aq != null && this.aq.cp();
    }
    
    public final void d(final int n) {
        try {
            if (this.aq != null && this.j() != n) {
                this.aq.u(n);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final int n, final int n2, final int n3) {
        try {
            this.m();
            if (this.aq != null) {
                final int h = this.h();
                final int i = this.i();
                final int j = this.j();
                if (h != n || i != n2 || j != n3) {
                    this.aq.a(n, n2, n3);
                    if (!this.b()) {
                        this.a(this.aq, this.k, this.ee, this.c, this.e);
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void f(final boolean b) {
        try {
            if (this.aq != null) {
                this.aq.b0(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final dv[] array, int k) {
        try {
            if (k <= 0) {
                k = this.k;
            }
            else {
                this.k = k;
            }
            this.m();
            if (this.aq != null) {
                if (k <= 0) {
                    k = this.k;
                }
                this.aq.a(array, k);
                if (!this.b()) {
                    this.a(this.aq, k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void l() {
        if (this.aq != null) {
            this.aq.c6();
        }
    }
    
    private final void b(final int n, final cy cy) {
        try {
            if (cy != null && !this.bh && this.aw == null && !this.a8 && this.a()) {
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cu, "Waiting T1...");
                }
                while (this.ci) {
                    ji.util.d.b(10, 111, this.cu);
                }
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cu, "Waited T1A...");
                }
                this.ci = true;
                if (cy.ea() != n && cy != null && !this.bh && this.aw == null && !this.a8) {
                    cy.k(true);
                    cy.m(this.ek);
                    cy.d(n, cy.z());
                }
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cu, "Waited T1B...");
                }
            }
        }
        catch (Exception ex) {}
        finally {
            this.ci = false;
        }
    }
    
    public final void m() {
        if (this.cj && this.eg) {
            this.a(this.k, this.aq);
        }
    }
    
    private boolean bj() {
        boolean b = false;
        if (this.ct != null) {
            b = (this.ct.cp() || this.ct.dc());
        }
        return b;
    }
    
    public final void a(final int n, final cy cy) {
        if (this.cj && this.aw == null) {
            try {
                if (cy != null && !this.bj()) {
                    this.b(n, cy);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void e(final int n) {
        try {
            if (this.aq != null) {
                this.b(n, this.aq);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Vector vector, final boolean b) {
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final Integer element = vector.elementAt(i);
                if (element instanceof Integer) {
                    this.c(element, b);
                }
            }
        }
    }
    
    public final void a(final int n, final boolean b) {
        this.c(n, b);
    }
    
    private final void c(final int n, final boolean b) {
        if (this.b()) {
            this.a(n, this.d, this.f, b);
        }
        else {
            this.a(n, this.c, this.e, b);
        }
    }
    
    private final void a(final int p0, final vz[] p1, final boolean[] p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_1       
        //     2: putfield        ji/document/c7.el:Z
        //     5: aload_0        
        //     6: invokevirtual   ji/document/c7.a:()Z
        //     9: ifeq            429
        //    12: aload_2        
        //    13: ifnull          429
        //    16: aload_0        
        //    17: iconst_0       
        //    18: putfield        ji/document/c7.cp:Z
        //    21: aload_0        
        //    22: getfield        ji/document/c7.ct:Lji/document/ad;
        //    25: invokevirtual   ji/document/ad.jn:()V
        //    28: aload_0        
        //    29: getfield        ji/document/c7.ct:Lji/document/ad;
        //    32: aload_0        
        //    33: getfield        ji/document/c7.ct:Lji/document/ad;
        //    36: invokevirtual   ji/document/ad.jo:()Ljava/lang/String;
        //    39: invokevirtual   ji/document/ad.a7:(Ljava/lang/String;)Lji/image/em;
        //    42: astore          5
        //    44: aload           5
        //    46: ifnull          104
        //    49: aload_0        
        //    50: aload           5
        //    52: getfield        ji/image/em.e:I
        //    55: invokevirtual   ji/document/c7.j:(I)V
        //    58: aload_0        
        //    59: aload           5
        //    61: getfield        ji/image/em.i:I
        //    64: invokevirtual   ji/document/c7.b:(I)V
        //    67: aload_0        
        //    68: aload           5
        //    70: getfield        ji/image/em.j:I
        //    73: invokevirtual   ji/document/c7.c:(I)V
        //    76: aload_0        
        //    77: aload           5
        //    79: getfield        ji/image/em.k:I
        //    82: invokevirtual   ji/document/c7.d:(I)V
        //    85: aload_0        
        //    86: aload           5
        //    88: getfield        ji/image/em.d:I
        //    91: invokevirtual   ji/document/c7.k:(I)V
        //    94: aload_0        
        //    95: aload           5
        //    97: getfield        ji/image/em.f:Z
        //   100: iconst_1       
        //   101: invokevirtual   ji/document/c7.b:(ZZ)V
        //   104: jsr             118
        //   107: goto            488
        //   110: astore          6
        //   112: jsr             118
        //   115: aload           6
        //   117: athrow         
        //   118: astore          7
        //   120: aload_0        
        //   121: iconst_1       
        //   122: putfield        ji/document/c7.cp:Z
        //   125: iconst_1       
        //   126: istore          8
        //   128: iload_1        
        //   129: aload_0        
        //   130: getfield        ji/document/c7.k:I
        //   133: if_icmpne       192
        //   136: aload_2        
        //   137: ifnull          247
        //   140: aload_2        
        //   141: iload_1        
        //   142: iconst_1       
        //   143: isub           
        //   144: aaload         
        //   145: ifnull          247
        //   148: aload_2        
        //   149: iload_1        
        //   150: iconst_1       
        //   151: isub           
        //   152: aaload         
        //   153: astore          9
        //   155: aload           9
        //   157: monitorenter   
        //   158: aload_2        
        //   159: iload_1        
        //   160: iconst_1       
        //   161: isub           
        //   162: aaload         
        //   163: invokevirtual   ji/document/c7$vz.b:()Z
        //   166: ifeq            179
        //   169: aload_0        
        //   170: invokespecial   ji/document/c7.bs:()Z
        //   173: ifne            179
        //   176: iconst_0       
        //   177: istore          8
        //   179: aload           9
        //   181: monitorexit    
        //   182: goto            247
        //   185: aload           9
        //   187: monitorexit    
        //   188: athrow         
        //   189: goto            247
        //   192: iload           4
        //   194: ifne            247
        //   197: aload_2        
        //   198: ifnull          247
        //   201: aload_2        
        //   202: iload_1        
        //   203: iconst_1       
        //   204: isub           
        //   205: aaload         
        //   206: ifnull          247
        //   209: aload_2        
        //   210: iload_1        
        //   211: iconst_1       
        //   212: isub           
        //   213: aaload         
        //   214: astore          9
        //   216: aload           9
        //   218: monitorenter   
        //   219: aload_2        
        //   220: iload_1        
        //   221: iconst_1       
        //   222: isub           
        //   223: aaload         
        //   224: invokevirtual   ji/document/c7$vz.d:()Z
        //   227: ifne            237
        //   230: aload           9
        //   232: monitorexit    
        //   233: jsr             515
        //   236: return         
        //   237: aload           9
        //   239: monitorexit    
        //   240: goto            247
        //   243: aload           9
        //   245: monitorexit    
        //   246: athrow         
        //   247: iload           8
        //   249: ifeq            360
        //   252: aload_0        
        //   253: invokevirtual   ji/document/c7.b:()Z
        //   256: ifeq            314
        //   259: iload_1        
        //   260: aload_0        
        //   261: getfield        ji/document/c7.l:I
        //   264: if_icmpne       294
        //   267: aload_0        
        //   268: aload_0        
        //   269: getfield        ji/document/c7.as:Lji/image/cy;
        //   272: aload_0        
        //   273: getfield        ji/document/c7.l:I
        //   276: aload_0        
        //   277: getfield        ji/document/c7.ef:Lji/document/c7$uy;
        //   280: aload_0        
        //   281: getfield        ji/document/c7.d:[Lji/document/c7$vz;
        //   284: aload_0        
        //   285: getfield        ji/document/c7.f:[Z
        //   288: invokespecial   ji/document/c7.a:(Lji/image/cy;ILji/document/c7$uy;[Lji/document/c7$vz;[Z)V
        //   291: goto            419
        //   294: aload_0        
        //   295: iload_1        
        //   296: aload_0        
        //   297: getfield        ji/document/c7.as:Lji/image/cy;
        //   300: aload_0        
        //   301: getfield        ji/document/c7.ef:Lji/document/c7$uy;
        //   304: aload_0        
        //   305: getfield        ji/document/c7.d:[Lji/document/c7$vz;
        //   308: invokespecial   ji/document/c7.a:(ILji/image/cy;Lji/document/c7$uy;[Lji/document/c7$vz;)V
        //   311: goto            419
        //   314: iload_1        
        //   315: aload_0        
        //   316: getfield        ji/document/c7.k:I
        //   319: if_icmpne       343
        //   322: aload_0        
        //   323: aload_0        
        //   324: getfield        ji/document/c7.aq:Lji/image/cy;
        //   327: aload_0        
        //   328: getfield        ji/document/c7.k:I
        //   331: aload_0        
        //   332: getfield        ji/document/c7.ee:Lji/document/c7$uy;
        //   335: aload_2        
        //   336: aload_3        
        //   337: invokespecial   ji/document/c7.a:(Lji/image/cy;ILji/document/c7$uy;[Lji/document/c7$vz;[Z)V
        //   340: goto            419
        //   343: aload_0        
        //   344: iload_1        
        //   345: aload_0        
        //   346: getfield        ji/document/c7.aq:Lji/image/cy;
        //   349: aload_0        
        //   350: getfield        ji/document/c7.ee:Lji/document/c7$uy;
        //   353: aload_2        
        //   354: invokespecial   ji/document/c7.a:(ILji/image/cy;Lji/document/c7$uy;[Lji/document/c7$vz;)V
        //   357: goto            419
        //   360: aload_2        
        //   361: ifnull          419
        //   364: aload_2        
        //   365: iload_1        
        //   366: iconst_1       
        //   367: isub           
        //   368: aaload         
        //   369: ifnull          419
        //   372: aload_2        
        //   373: iload_1        
        //   374: iconst_1       
        //   375: isub           
        //   376: aaload         
        //   377: astore          9
        //   379: aload           9
        //   381: monitorenter   
        //   382: aload_3        
        //   383: iload_1        
        //   384: iconst_1       
        //   385: isub           
        //   386: iconst_1       
        //   387: bastore        
        //   388: aload_2        
        //   389: iload_1        
        //   390: iconst_1       
        //   391: isub           
        //   392: aaload         
        //   393: aconst_null    
        //   394: aconst_null    
        //   395: iconst_0       
        //   396: iconst_0       
        //   397: invokevirtual   ji/document/c7$vz.a:(Ljava/awt/Component;Ljava/awt/Image;II)V
        //   400: aload_2        
        //   401: iload_1        
        //   402: iconst_1       
        //   403: isub           
        //   404: aaload         
        //   405: iconst_0       
        //   406: invokevirtual   ji/document/c7$vz.c:(Z)V
        //   409: aload           9
        //   411: monitorexit    
        //   412: goto            419
        //   415: aload           9
        //   417: monitorexit    
        //   418: athrow         
        //   419: aload_0        
        //   420: getfield        ji/document/c7.ct:Lji/document/ad;
        //   423: iconst_1       
        //   424: invokevirtual   ji/document/ad.fd:(Z)V
        //   427: ret             7
        //   429: aload_2        
        //   430: ifnull          488
        //   433: aload_2        
        //   434: iload_1        
        //   435: iconst_1       
        //   436: isub           
        //   437: aaload         
        //   438: ifnull          488
        //   441: aload_2        
        //   442: iload_1        
        //   443: iconst_1       
        //   444: isub           
        //   445: aaload         
        //   446: astore          5
        //   448: aload           5
        //   450: monitorenter   
        //   451: aload_3        
        //   452: iload_1        
        //   453: iconst_1       
        //   454: isub           
        //   455: iconst_1       
        //   456: bastore        
        //   457: aload_2        
        //   458: iload_1        
        //   459: iconst_1       
        //   460: isub           
        //   461: aaload         
        //   462: aconst_null    
        //   463: aconst_null    
        //   464: iconst_0       
        //   465: iconst_0       
        //   466: invokevirtual   ji/document/c7$vz.a:(Ljava/awt/Component;Ljava/awt/Image;II)V
        //   469: aload_2        
        //   470: iload_1        
        //   471: iconst_1       
        //   472: isub           
        //   473: aaload         
        //   474: iconst_0       
        //   475: invokevirtual   ji/document/c7$vz.c:(Z)V
        //   478: aload           5
        //   480: monitorexit    
        //   481: goto            488
        //   484: aload           5
        //   486: monitorexit    
        //   487: athrow         
        //   488: jsr             515
        //   491: goto            524
        //   494: astore          5
        //   496: aload           5
        //   498: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   501: jsr             515
        //   504: goto            524
        //   507: astore          10
        //   509: jsr             515
        //   512: aload           10
        //   514: athrow         
        //   515: astore          11
        //   517: aload_0        
        //   518: iconst_0       
        //   519: putfield        ji/document/c7.el:Z
        //   522: ret             11
        //   524: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  16     110    110    118    Any
        //  158    179    185    189    Any
        //  219    237    243    247    Any
        //  382    409    415    419    Any
        //  451    478    484    488    Any
        //  0      488    494    507    Ljava/lang/Exception;
        //  0      507    507    515    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #0494 (coming from #0117).
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
    
    private final void bk() {
        boolean b = false;
        if (ji.util.d.ak && this.n()) {
            ji.io.h.d(this.cu, "Waiting for Thumb draw...");
            b = true;
        }
        while (this.n()) {
            ji.util.d.b(10, 112, this.cu);
        }
        if (b && ji.util.d.ak) {
            ji.io.h.d(this.cu, "Wait for Thumb draw complete.");
        }
    }
    
    public final boolean n() {
        return !this.bq() && (this.bq || this.a8 || this.aw != null);
    }
    
    public final void o() {
        if (this.b()) {
            this.a(this.as, this.l, this.ef, this.d, this.f);
        }
        else {
            this.a(this.aq, this.k, this.ee, this.c, this.e);
        }
    }
    
    private final void a(final cy cy, final int n, final uy uy, final vz[] array, final boolean[] array2) {
        if (!this.a()) {
            this.a(new d7(this, 48, 0));
            return;
        }
        if (this.cp) {
            if (this.bz) {
                this.a(array, this.g, array2);
                return;
            }
            final boolean cj = this.cj;
            if (cy != null && !this.bj() && array != null) {
                try {
                    if (array[n - 1] != null) {
                        this.bk();
                        this.cj = false;
                        this.b(n, cy);
                        this.a8 = true;
                        if (ji.util.d.di() == 1) {
                            cy.dp();
                            if (this.as != null) {
                                this.as.dp();
                            }
                        }
                        synchronized (array[n - 1]) {
                            array[n - 1].a(null, null, 0, 0);
                            array[n - 1].c(false);
                        }
                        // monitorexit(array[n - 1])
                        if (this.bs() && !this.a2) {
                            final v9 a = this.a(n - 1, true, cy, array);
                            if (a.a != null) {
                                synchronized (array[n - 1]) {
                                    array[n - 1].a(this.getParent(), a.a, a.a.getWidth(null), a.a.getHeight(null));
                                    array[n - 1].c(true);
                                }
                                // monitorexit(array[n - 1])
                            }
                            this.a(this.getGraphics(), n - 1, true, uy, array);
                        }
                    }
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                    }
                }
                finally {
                    this.cj = cj;
                    this.a8 = false;
                    this.a(new d7(this, 48, 0));
                }
            }
        }
    }
    
    private final void a(final int n, final cy cy, final uy uy, final vz[] array) {
        if (!this.a()) {
            return;
        }
        if (cy != null && !this.bj() && array != null) {
            try {
                if (array[n - 1] != null) {
                    if (!this.y(n - 1)) {
                        return;
                    }
                    synchronized (array[n - 1]) {
                        array[n - 1].a(null, null, 0, 0);
                        array[n - 1].c(false);
                    }
                    // monitorexit(array[n - 1])
                    if (this.bs() && !this.a2) {
                        final v9 a = this.a(n - 1, true, cy, array);
                        synchronized (array[n - 1]) {
                            if (a.a != null) {
                                array[n - 1].a(this.getParent(), a.a, a.a.getWidth(null), a.a.getHeight(null));
                                array[n - 1].c(true);
                            }
                        }
                        // monitorexit(array[n - 1])
                        this.a(this.getGraphics(), n - 1, true, uy, array);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void f(final int da) {
        try {
            if (this.ct.bi(22) && this.da != da) {
                switch (da) {
                    case 0: {
                        this.da = da;
                        this.db.setVisible(false);
                        if (this.de != null) {
                            if (!this.ct.bi(22)) {
                                this.de.select(-1);
                            }
                            this.de.setVisible(false);
                        }
                        this.dc.setVisible(false);
                        this.ef.setVisible(false);
                        this.ee.setVisible(true);
                        break;
                    }
                    case 1: {
                        this.da = da;
                        this.db.setVisible(true);
                        if (this.de != null) {
                            if (!this.ct.bi(22)) {
                                this.de.select(-1);
                            }
                            this.de.setVisible(false);
                        }
                        this.ee.setVisible(false);
                        this.dc.setVisible(false);
                        this.ef.setVisible(false);
                        break;
                    }
                    case 2: {
                        this.da = da;
                        this.db.setVisible(false);
                        if (this.de != null) {
                            if (!this.ct.bi(22)) {
                                this.de.select(-1);
                            }
                            this.de.setVisible(false);
                        }
                        this.ee.setVisible(false);
                        this.dc.setVisible(false);
                        this.ef.setVisible(true);
                        break;
                    }
                    case 3: {
                        this.da = da;
                        this.db.setVisible(false);
                        if (this.de != null) {
                            if (!this.ct.bi(22)) {
                                this.de.select(-1);
                            }
                            this.de.setVisible(false);
                        }
                        this.ee.setVisible(false);
                        this.dc.setVisible(true);
                        this.ef.setVisible(false);
                        break;
                    }
                    case 4: {
                        this.da = da;
                        this.db.setVisible(false);
                        this.ee.setVisible(false);
                        this.dc.setVisible(false);
                        this.ef.setVisible(false);
                        if (this.de != null) {
                            this.de.setVisible(true);
                            break;
                        }
                        break;
                    }
                }
                this.k(false);
                this.j(false);
                this.validate();
                this.bw.ah();
                this.bw.ap();
                if (this.b()) {
                    this.q(this.l);
                }
                else {
                    this.s(this.ap());
                }
                this.repaint();
                if (!this.a()) {
                    this.b3();
                }
                else if (this.b()) {
                    this.a(this.d, this.h, this.f);
                }
                else {
                    this.a(this.c, this.g, this.e);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final int p() {
        return this.da;
    }
    
    public final void g(final boolean cf) {
        try {
            this.cf = cf;
            if (this.aq != null) {
                this.aq.v(this.cf);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int a, final int b) {
        this.a = a;
        this.b = b;
        this.bd.setSize(this.a, this.b);
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
        final int b = this.b(mouseEvent, false);
        if (b > 0) {
            this.x(b);
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.du) {
            ji.util.d.mt.x = System.currentTimeMillis();
            ji.util.d.mt.y = 0L;
            if (!this.ch) {
                return;
            }
            this.cn = false;
            this.b3 = true;
            this.c5 = mouseEvent.getWhen();
            this.c1 = this.g(mouseEvent);
            this.c3 = mouseEvent.getPoint();
        }
    }
    
    private final void bl() {
        try {
            if (this.cq != this.br) {
                this.cq = this.br;
                if (this.bw.d != null) {
                    this.bw.d.a(this.cq);
                }
                if (this.bw.e != null) {
                    this.bw.e.a(this.cq);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
        if (this.du) {
            if (!this.ch) {
                return;
            }
            if (!this.b3) {
                return;
            }
            if (this.bw.ag()) {
                if (!this.br) {
                    if (this.bw.z()) {
                        this.f(mouseEvent);
                        this.br = true;
                        this.bs = this.y();
                        if (this.bs) {
                            this.bl();
                        }
                    }
                }
                else {
                    this.bs = true;
                    boolean b = false;
                    switch (this.ap) {
                        case 1:
                        case 3:
                        case 5:
                        case 6: {
                            if (!this.bw.a(-this.bl.x, -Math.min(mouseEvent.getPoint().y - this.bp.y + this.bo.y, 0))) {
                                this.f(mouseEvent);
                                break;
                            }
                            this.bs = true;
                            b = true;
                            break;
                        }
                        case 2:
                        case 4: {
                            if (!this.bw.a(-Math.min(mouseEvent.getPoint().x - this.bp.x + this.bo.x, 0), -this.bl.y)) {
                                this.f(mouseEvent);
                                break;
                            }
                            this.bs = true;
                            b = true;
                            break;
                        }
                    }
                    if (b) {
                        this.cn = true;
                        this.bl();
                        this.k(false);
                    }
                }
            }
        }
    }
    
    private final void f(final MouseEvent mouseEvent) {
        this.bo.x = this.bl.x;
        this.bo.y = this.bl.y;
        this.bp.x = mouseEvent.getPoint().x;
        this.bp.y = mouseEvent.getPoint().y;
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.du) {
            ji.util.d.mt.z = System.currentTimeMillis();
            ji.util.d.mt.aa = 0L;
            final boolean br = this.br;
            boolean b = false;
            this.c7 = mouseEvent.getWhen();
            this.c2 = this.g(mouseEvent);
            this.c4 = mouseEvent.getPoint();
            if (!this.c3.equals(this.c4)) {
                if (this.c1 == this.c2 && !this.cn && this.c7 - this.c5 <= ji.util.d.aw()) {
                    this.cl = false;
                    b = true;
                    this.a(mouseEvent, true);
                }
            }
            else if (!ji.util.d.em()) {
                if (this.b()) {
                    this.a(mouseEvent);
                }
                else {
                    this.a(mouseEvent, false);
                }
            }
            if (!b) {
                this.d(mouseEvent);
            }
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (ji.util.d.em()) {
            if (this.b()) {
                this.a(mouseEvent);
            }
            else {
                this.a(mouseEvent, false);
            }
        }
    }
    
    public final void a(final MouseEvent mouseEvent) {
        try {
            if (this.cv == null) {
                this.cy = mouseEvent.getWhen();
                this.c0 = this.b(mouseEvent, false);
                if (this.c0 > 0 && this.c0 != this.l) {
                    (this.cv = new aan()).a(mouseEvent);
                    if (this.l != this.c0) {
                        this.a(new a3(mouseEvent.getSource(), 20, this.c0 - 1, "set doc", ji.util.d.e0()));
                    }
                    this.o(this.c0);
                    new bb(this.cu, this.cv).start();
                }
                else if (this.a(this.d, this.h) && this.aw == null) {
                    this.a(this.d, this.h, this.f);
                }
                else if (mouseEvent.getClickCount() > 1) {
                    this.y();
                    this.bw.a(new ActionEvent(mouseEvent.getSource(), 1001, "thumbsimages", mouseEvent.getModifiers()));
                }
            }
            else {
                boolean b = false;
                if (mouseEvent.getWhen() - this.cy <= ji.util.d.aw() && this.g(mouseEvent) == this.c0) {
                    b = true;
                }
                this.cy = mouseEvent.getWhen();
                this.c0 = this.b(mouseEvent, false);
                if (this.c0 > 0) {
                    if (!b) {
                        this.cv.a(mouseEvent);
                    }
                    else {
                        this.cv.a(new MouseEvent((Component)mouseEvent.getSource(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getPoint().x, mouseEvent.getPoint().y, 2, false));
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final MouseEvent mouseEvent, final boolean b) {
        if (this.du) {
            try {
                final int modifiers = mouseEvent.getModifiers();
                if ((modifiers & 0x8) <= 0 && (modifiers & 0x4) <= 0) {
                    if ((modifiers & 0x2) > 0 || (modifiers & 0x1) > 0) {
                        this.c(mouseEvent);
                    }
                    else if (this.cv == null) {
                        this.cy = mouseEvent.getWhen();
                        this.cz = this.b(mouseEvent, false);
                        if (this.cz > 0 && this.cz != this.k) {
                            (this.cv = new aan()).a(mouseEvent);
                            if (this.k != this.cz) {
                                this.p(this.cz);
                                this.dk = true;
                            }
                            new bb(this.cu, this.cv).start();
                        }
                        else if (this.a(this.c, this.g) && this.aw == null) {
                            this.a(this.c, this.g, this.e);
                        }
                        else if (mouseEvent.getClickCount() > 1) {
                            this.a(mouseEvent, this.cz);
                        }
                    }
                    else {
                        boolean b2 = false;
                        if (mouseEvent.getWhen() - this.cy <= ji.util.d.aw() && this.g(mouseEvent) == this.cz) {
                            b2 = true;
                        }
                        this.cy = mouseEvent.getWhen();
                        this.cz = this.b(mouseEvent, false);
                        if (this.cz > 0) {
                            if (!b2) {
                                this.cv.a(mouseEvent);
                            }
                            else {
                                this.cv.a(new MouseEvent((Component)mouseEvent.getSource(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getPoint().x, mouseEvent.getPoint().y, 2, false));
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final boolean b(final MouseEvent mouseEvent) {
        if (this.ch && this.du) {
            try {
                while (this.cx != null) {
                    ji.util.d.b(20, 114, this.cu);
                }
                final int b = this.b(mouseEvent, false);
                if (b > 0) {
                    if (mouseEvent.getClickCount() > 1) {
                        if (this.ct.e().equals("Select")) {
                            this.y();
                            this.bw.a(new ActionEvent(mouseEvent.getSource(), 1001, "thumbsimages", mouseEvent.getModifiers()));
                        }
                        return false;
                    }
                    this.bl();
                    if (ji.util.e.at()) {
                        ji.util.e.b(this);
                    }
                    if (this.bw.ag()) {
                        this.setCursor(this.a6);
                    }
                    if (b != this.l || this.dk) {
                        if (this.l != b) {
                            this.a(new a3(mouseEvent.getSource(), 20, b - 1, "set doc", ji.util.d.e0()));
                        }
                        this.o(b);
                    }
                }
            }
            catch (Exception ex) {}
            finally {
                this.dk = false;
            }
        }
        return true;
    }
    
    public final boolean c(final MouseEvent mouseEvent) {
        if (this.ch && this.du) {
            try {
                while (this.cx != null) {
                    ji.util.d.b(20, 114, this.cu);
                }
                final int b = this.b(mouseEvent, false);
                if (b > 0) {
                    if (mouseEvent.getClickCount() > 1) {
                        this.a(mouseEvent, b);
                        return false;
                    }
                    final int modifiers = mouseEvent.getModifiers();
                    if ((modifiers & 0x10) != 0x0 && (modifiers & 0x1) != 0x0) {
                        this.a(new fv(mouseEvent.getSource(), 6, b, mouseEvent.getModifiers()));
                        return false;
                    }
                    if ((modifiers & 0x10) != 0x0 && (modifiers & 0x2) != 0x0) {
                        this.a(new fv(mouseEvent.getSource(), 5, b, mouseEvent.getModifiers()));
                        return false;
                    }
                    if ((modifiers & 0x8) != 0x0 || (modifiers & 0x4) != 0x0) {
                        return true;
                    }
                    this.bl();
                    if (ji.util.e.at()) {
                        ji.util.e.b(this);
                    }
                    if (this.bw.ag()) {
                        this.setCursor(this.a6);
                    }
                    if ((b != this.k || this.dk) && ji.util.d.di() != 1) {
                        if (this.aw != null) {
                            this.a(new fv(mouseEvent.getSource(), 7, b, mouseEvent.getModifiers()));
                        }
                        else {
                            this.a(new fv(mouseEvent.getSource(), 3, b, mouseEvent.getModifiers()));
                        }
                        return false;
                    }
                }
            }
            catch (Exception ex) {}
            finally {
                this.dk = false;
            }
        }
        return true;
    }
    
    private void a(final MouseEvent mouseEvent, final int n) {
        final String e = this.ct.e();
        if (e.equals("Select")) {
            this.a(new fv(mouseEvent.getSource(), 4, n, mouseEvent.getModifiers()));
        }
        else if (e.equals("FullPage")) {
            this.y();
            this.a(new fv(mouseEvent.getSource(), 9, n, mouseEvent.getModifiers()));
        }
        else if (e.equals("NewWindow")) {
            if (this.ct.c()) {
                this.y();
                this.a(new fv(mouseEvent.getSource(), 9, n, mouseEvent.getModifiers()));
            }
            else {
                this.a(new fv(mouseEvent.getSource(), 11, n, mouseEvent.getModifiers()));
            }
        }
        try {
            this.ct.a(this, 8, "Thumbnail dbl-clicked", n, this.ae(), 1, 1);
        }
        catch (Exception ex) {}
    }
    
    public final void d(final MouseEvent mouseEvent) {
        if (this.du && this.cx == null) {
            this.cx = new aao(mouseEvent);
            new bb(this.cu, this.cx).start();
        }
    }
    
    public final void e(final MouseEvent mouseEvent) {
        if (!this.du) {
            return;
        }
        if (!this.ch) {
            return;
        }
        if (!this.b3) {
            return;
        }
        if ((mouseEvent.getModifiers() & 0x8) != 0x0 || (mouseEvent.getModifiers() & 0x4) != 0x0) {
            return;
        }
        this.b3 = false;
        if (!this.cl) {
            this.bl();
            this.setCursor(this.a5);
            if (this.br) {
                this.g("");
                this.br = false;
                this.bl();
                if (this.bs && this.cn) {
                    if (this.ap == 6) {
                        if (this.b()) {
                            if (!this.be()) {
                                this.a(this.d, this.h, this.f);
                            }
                        }
                        else if (!this.be()) {
                            this.a(this.c, this.g, this.e);
                        }
                    }
                    else {
                        this.bi = false;
                        this.aa(this.bg = false);
                        if (this.b()) {
                            this.a(this.d, this.h, this.f);
                        }
                        else {
                            this.a(this.c, this.g, this.e);
                        }
                    }
                }
                else {
                    this.aa(false);
                    if (this.b()) {
                        if (this.a(this.d, this.h)) {
                            this.a(this.d, this.h, this.f);
                        }
                    }
                    else if (this.a(this.c, this.g)) {
                        this.a(this.c, this.g, this.e);
                    }
                }
                this.bs = false;
            }
        }
        this.cl = false;
    }
    
    private final int g(final MouseEvent mouseEvent) {
        return this.b(mouseEvent, true);
    }
    
    private final int b(final MouseEvent mouseEvent, final boolean b) {
        if (!b || !this.n()) {
            vz[] array;
            if (this.b()) {
                array = this.d;
            }
            else {
                array = this.c;
            }
            int n = 0;
            try {
                if (array != null) {
                    Block_11: {
                        for (int i = 0; i < array.length; ++i) {
                            if (array[i].b() && this.y(i)) {
                                final Point a;
                                final Point point = a = array[i].a();
                                a.x += this.bl.x;
                                final Point point2 = point;
                                point2.y += this.bl.y;
                                if (new Rectangle(point.x, point.y, this.a, this.b).contains(mouseEvent.getPoint())) {
                                    break Block_11;
                                }
                            }
                        }
                        return n;
                    }
                    int i = 0;
                    n = i + 1;
                }
            }
            catch (Exception ex) {}
            return n;
        }
        if (this.b()) {
            return this.l;
        }
        return this.k;
    }
    
    public final int q() {
        return this.bm;
    }
    
    public final void setBorderStyle(final int bj) {
        if (this.bj != bj) {
            if (bj == 5) {
                this.bj = 4;
            }
            else {
                this.bj = bj;
            }
            this.bf.a(this.bj);
            this.y();
        }
    }
    
    public final int getBorderStyle() {
        return this.bj;
    }
    
    public final void setShadowWidth(final int j) {
        if (this.j != j) {
            this.j = j;
            this.bf.b(this.j);
            this.y();
        }
    }
    
    public final int getShadowWidth() {
        return this.j;
    }
    
    public final void g(final int ek) {
        this.ek = ek;
    }
    
    public final void h(final boolean em) {
        this.em = em;
    }
    
    public final void a(final o7[] ba) {
        this.ba = ba;
    }
    
    public final void r() {
        if (this.q != null) {
            this.du = false;
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbsPanelClose 1...");
            }
            this.bo();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbsPanelClose 2...");
            }
            this.bp();
            if (this.by != null) {
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanelClose 3...");
                }
                this.ca();
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanelClose 4...");
                }
                this.cc();
            }
            this.dt = false;
            this.t();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbsPanelClose 5...");
            }
            this.b0();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cu, "jiThumbsPanelClose 6...");
            }
            this.q = null;
            this.dv = false;
            this.bm();
            this.m = null;
            this.by = null;
            this.ba = null;
            this.b0 = null;
            this.b5 = null;
            this.cb = null;
            this.b6 = null;
            this.o = null;
            this.p = null;
            this.cg = false;
            this.cm = false;
            this.c8 = -1;
            this.d0 = null;
            this.d1 = null;
            this.d2 = null;
            this.d3 = null;
            this.s();
        }
    }
    
    private void bm() {
        this.r = 0;
    }
    
    public final void s() {
        this.y();
    }
    
    public final void t() {
        try {
            if (this.a()) {
                if (this.b()) {
                    this.as.q();
                }
                else {
                    this.aq.q();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void u() {
        try {
            try {
                if (this.db != null) {
                    if (!this.db.isSwing()) {
                        this.db.setVisible(false);
                    }
                    this.db.clear();
                }
            }
            catch (Exception ex) {}
            this.df = false;
            this.dp = -1;
            this.ag = -1;
        }
        catch (Exception ex2) {}
    }
    
    public final void v() {
        try {
            try {
                if (this.de != null) {
                    if (!this.de.isSwing()) {
                        this.de.setVisible(false);
                    }
                    this.de.clear();
                }
            }
            catch (Exception ex) {}
            this.dg = false;
            this.dh = false;
            this.dq = -1;
        }
        catch (Exception ex2) {}
    }
    
    private final void bn() {
        try {
            try {
                if (this.dc != null) {
                    this.dc.setVisible(false);
                    this.dc.clear();
                }
            }
            catch (Exception ex) {}
            this.di = false;
            this.dr = -1;
        }
        catch (Exception ex2) {}
    }
    
    private final void bo() {
        try {
            if (this.be || !this.b()) {
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanel.cT 1...");
                }
                this.y();
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanel.cT 2...");
                }
                this.cc();
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanel.cT 3...");
                }
                if (this.c != null) {
                    for (int i = 0; i < this.c.length; ++i) {
                        if (this.c[i].b() || this.c[i].c()) {
                            this.c[i].a(null, null, 0, 0);
                        }
                        this.c[i] = null;
                    }
                    this.c = null;
                }
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cu, "jiThumbsPanel.cT 4...");
                }
                this.bg = false;
            }
            this.g = null;
            this.aq.dc();
        }
        catch (Exception ex) {}
    }
    
    private final void bp() {
        try {
            if (this.be || !this.b()) {
                this.y();
                this.cd();
                if (this.d != null) {
                    for (int i = 0; i < this.d.length; ++i) {
                        if (this.d[i].b() || this.d[i].c()) {
                            this.d[i].a(null, null, 0, 0);
                        }
                        this.d[i] = null;
                    }
                    this.d = null;
                    this.bg = false;
                }
                this.h = null;
                if (this.as != null) {
                    this.as.dc();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void i(final boolean cm) {
        this.cm = cm;
    }
    
    public final boolean w() {
        if (this.b()) {
            return this.bu;
        }
        return this.bt;
    }
    
    public final void j(final boolean b) {
        this.a(b, false);
    }
    
    public final void a(final boolean b, final boolean b2) {
        try {
            if ((this.be || b2 || !this.b()) && this.d != null) {
                boolean b3 = false;
                if (!this.bu) {
                    for (int i = 0; i < this.d.length; ++i) {
                        if (this.d[i].d() || this.d[i].c()) {
                            this.d[i].a(null, null, 0, 0);
                            b3 = true;
                            this.d[i].c(false);
                            this.cg = false;
                        }
                        else if (b3) {
                            break;
                        }
                        this.f[i] = b;
                    }
                    this.bu = true;
                }
            }
        }
        catch (Exception ex) {}
        finally {
            if (this.be || !this.b()) {
                this.bg = false;
                this.b(this.getParent());
                if (this.d != null) {
                    if (this.d.length < 100) {
                        for (int j = 0; j < this.d.length; ++j) {
                            this.d[j].a(true);
                        }
                    }
                    else {
                        this.a(this.getParent(), this.d);
                    }
                }
            }
        }
    }
    
    public final void k(final boolean b) {
        try {
            if (this.c != null && (this.be || !this.b())) {
                boolean b2 = false;
                if (!this.bt) {
                    for (int i = 0; i < this.c.length; ++i) {
                        if (this.c[i].d() || this.c[i].c()) {
                            this.c[i].a(null, null, 0, 0);
                            b2 = true;
                            this.c[i].c(false);
                            this.cg = false;
                        }
                        else if (b2) {
                            break;
                        }
                        this.e[i] = b;
                    }
                    this.bt = true;
                }
            }
        }
        catch (Exception ex) {}
        finally {
            if (this.be || !this.b()) {
                this.bg = false;
                this.a(this.getParent());
                if (this.c != null) {
                    if (this.c.length < 100) {
                        for (int j = 0; j < this.c.length; ++j) {
                            this.c[j].a(true);
                        }
                    }
                    else {
                        this.a(this.getParent(), this.c);
                    }
                }
            }
        }
    }
    
    public final boolean x() {
        return this.y();
    }
    
    public final boolean l(final boolean b) {
        if (this.b()) {
            return this.a(b, this.as);
        }
        return this.a(b, this.aq);
    }
    
    public final boolean y() {
        if (this.b()) {
            return this.a(true, this.as);
        }
        return this.a(true, this.aq);
    }
    
    public final void z() {
        try {
            this.aq.gk();
        }
        catch (Exception ex) {}
    }
    
    public final void aa() {
        try {
            this.aq.gl();
        }
        catch (Exception ex) {}
    }
    
    private void aa(boolean b) {
        if (ji.util.i.c(142)) {
            if (this.bk == null) {
                return;
            }
            synchronized (this.bk) {
                if (b) {
                    b = b;
                }
                this.bk.a = b;
                // monitorexit(this.bk)
                return;
            }
        }
        synchronized (this) {
            if (this.bk != null) {
                final ux bk = this.bk;
                // monitorenter(bk)
                try {
                    if (b) {
                        b = b;
                    }
                    this.bk.a = b;
                }
                // monitorexit(bk)
                finally {}
            }
        }
    }
    
    private boolean bq() {
        if (this.bk != null) {
            synchronized (this.bk) {
                // monitorexit(this.bk)
                return this.bk.a;
            }
        }
        return false;
    }
    
    public final boolean a(final boolean ck, final cy cy) {
        if (!this.eg || ji.util.d.ck(this.cu)) {
            return false;
        }
        final boolean b = true;
        boolean b2 = false;
        final boolean b3 = false;
        try {
            synchronized (ji.document.c7.dl) {
                while (ji.document.c7.dl.a && !ji.util.d.ck(this.cu)) {
                    ji.document.c7.dl.wait(100L);
                }
                ji.document.c7.dl.a = true;
            }
            // monitorexit(c7.dl)
            if (!this.bq() && this.a()) {
                try {
                    this.ck = ck;
                    final bb aw = this.aw;
                    if (aw != null) {
                        try {
                            this.g(this.b7);
                            if (b) {
                                this.aa(true);
                            }
                            try {
                                ji.util.d.b(5, 117, this.cu);
                                final boolean bz = cy.bz();
                                cy.gk();
                                final boolean b4 = bz | cy.gn() | cy.gm();
                                cy.gl();
                                if (b4) {
                                    cy.bw();
                                }
                                ji.util.d.b(5, 1108, this.cu);
                            }
                            catch (Exception ex) {}
                            if (!aw.getName().equals(Thread.currentThread().getName())) {
                                if (aw.isAlive() && b) {
                                    b2 = true;
                                    this.g(String.valueOf(String.valueOf(this.v(340))).concat("..."));
                                    this.bz = true;
                                }
                                if (aw.isAlive() && !ji.util.d.ck(this.cu) && !this.bj()) {
                                    for (int n = 1200; aw.isAlive() && n > 0; n -= 100) {
                                        ji.util.d.b(100, 119, this.cu);
                                    }
                                }
                            }
                            this.g("");
                        }
                        catch (Exception ex2) {}
                        finally {
                            this.g("");
                        }
                    }
                }
                catch (Exception ex3) {}
                finally {
                    if (b) {
                        this.bg = false;
                    }
                }
            }
        }
        catch (Exception ex4) {}
        finally {
            synchronized (ji.document.c7.dl) {
                ji.document.c7.dl.a = false;
                ji.document.c7.dl.notifyAll();
            }
            // monitorexit(c7.dl)
        }
        return b2 || b3;
    }
    
    public void m(final boolean b2) {
        this.b2 = b2;
    }
    
    public final void n(final boolean t) {
        if (this.t != t) {
            this.t = t;
            this.k(false);
            this.j(false);
            this.repaint();
        }
    }
    
    public final boolean ab() {
        return this.t;
    }
    
    public final int ac() {
        int ae = this.ae();
        if (this.ea) {
            ae = 0;
            if (this.cb != null && this.c != null) {
                for (int i = 0; i < this.c.length; ++i) {
                    if (this.cb[i]) {
                        ++ae;
                    }
                }
            }
        }
        return ae;
    }
    
    public final boolean ad() {
        return this.ek > 0;
    }
    
    public final int ae() {
        if (!this.ad()) {
            if (this.b()) {
                int length;
                if (this.ai != null) {
                    length = this.ai.length;
                }
                else {
                    length = 0;
                }
                return length;
            }
            int n = 0;
            if (this.b5 != null) {
                n = this.b5.length;
            }
            else if (this.ct != null) {
                n = this.ct.j8();
            }
            else if (this.aq != null) {
                n = this.aq.fu();
            }
            return n;
        }
        else {
            if (this.b()) {
                return this.s;
            }
            return this.r;
        }
    }
    
    public final void af() {
        try {
            if (this.aq != null) {
                this.aq.b0();
                this.aq.g0();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ag() {
        try {
            if (this.aq != null) {
                this.aq.dd();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void h(final int b8) {
        this.bk();
        if (this.b8 != b8) {
            this.a9 = true;
        }
        boolean b9 = false;
        if (this.b8 != b8 && (this.ad() || this.em) && (b8 == 6 || this.b8 == 6)) {
            b9 = true;
        }
        this.b8 = b8;
        try {
            if (this.as != null) {
                this.as.dr();
                this.as.b(b8);
            }
            if (this.aq != null) {
                this.aq.dr();
                this.aq.b(b8);
            }
            if (b9) {
                this.k(true);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final int ah() {
        return this.b8;
    }
    
    private final boolean br() {
        return this.b8 != 0;
    }
    
    public final void a(final int n, final int n2, final boolean b) {
        if (this.a()) {
            this.m();
            if (this.aq != null) {
                this.aq.e(n, false);
            }
            if (this.aq != null) {
                this.aq.f(n2, false);
            }
            if (this.aq != null) {
                this.aq.cv(b);
            }
            this.y();
        }
    }
    
    public final Dimension ai() {
        if (this.aq != null) {
            return this.aq.c5();
        }
        return null;
    }
    
    public final void o(final boolean b) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.co(b);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final Color color) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.a(color);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void i(final int dz) {
        try {
            this.dz = dz;
            this.aq.aa(dz);
        }
        catch (Exception ex) {}
    }
    
    public final void b(final Color color) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.c(color);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b(final String s) {
        try {
            if (this.aq != null) {
                this.aq.l(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void p(final boolean b) {
        try {
            if (this.aq != null) {
                this.aq.bn(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public void q(final boolean ea) {
        this.ea = ea;
    }
    
    public void r(final boolean b) {
        if (this.aq != null) {
            this.aq.bo(b);
        }
    }
    
    public final void a(final boolean[] array) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.a(false, array);
                this.k(true);
                this.s();
                this.a(this.c, this.g, this.e);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b(final boolean[] array) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.b(false, array);
                this.k(true);
                this.s();
                this.a(this.c, this.g, this.e);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void c(final boolean[] array) {
        this.y();
        this.m();
        try {
            if (this.aq != null) {
                this.aq.c(false, array);
                this.k(true);
                this.s();
                this.a(this.c, this.g, this.e);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final int n, final boolean[] array) {
        this.y();
        this.m();
        try {
            if (this.aq != null && this.ak() != n) {
                this.aq.a(n, false, array);
                this.k(true);
                this.s();
                this.a(this.c, this.g, this.e);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b(final int n, final int n2, final boolean b) {
        this.y();
        this.m();
        try {
            this.e[n2 - 1] = true;
            this.c[n2 - 1].c(false);
        }
        catch (Exception ex2) {}
        try {
            if (this.aq != null) {
                this.aq.c(n, n2, b);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void j(final int n) {
        if (this.ak() == n) {
            return;
        }
        final boolean y = this.y();
        this.m();
        try {
            if (this.aq != null && this.ak() != n) {
                this.aq.e(n, false);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
                this.x(this.k);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            if (y) {
                this.a(this.c, this.g, this.e);
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public final void k(final int n) {
        if (this.al() == n) {
            return;
        }
        this.y();
        this.m();
        try {
            if (this.aq != null && this.al() != n) {
                this.aq.f(n, false);
                if (!this.b()) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
                this.x(this.k);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void s(final boolean a4) {
        this.a4 = a4;
        if (this.aq != null) {
            this.aq.aa(this.a4);
        }
    }
    
    public final int l(final int n) {
        this.e(n);
        int f8;
        if (this.aq != null) {
            f8 = this.aq.f8();
        }
        else {
            f8 = 0;
        }
        this.m();
        return f8;
    }
    
    public final dv[] aj() {
        dv[] c7 = null;
        if (this.aq != null) {
            c7 = this.aq.c7();
        }
        return c7;
    }
    
    public final int ak() {
        if (this.aq != null) {
            return this.aq.f8();
        }
        return 0;
    }
    
    public final int m(final int n) {
        this.e(n);
        int f7;
        if (this.aq != null) {
            f7 = this.aq.f7();
        }
        else {
            f7 = 0;
        }
        this.m();
        return f7;
    }
    
    public final int al() {
        if (this.aq != null) {
            return this.aq.f7();
        }
        return 0;
    }
    
    private final boolean bs() {
        boolean visible = true;
        try {
            final Container parent = this.getParent();
            if (parent != null) {
                visible = parent.isVisible();
            }
        }
        catch (Exception ex) {}
        return visible;
    }
    
    public final void b(final boolean b, final boolean b2) {
        if (this.am() == b) {
            return;
        }
        this.y();
        this.m();
        try {
            if (this.aq != null && this.am() != b) {
                this.aq.cv(b);
                if (!this.b() && b2) {
                    this.a(this.aq, this.k, this.ee, this.c, this.e);
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final boolean am() {
        return this.aq != null && this.aq.ge();
    }
    
    public final void n(final int c8) {
        try {
            this.b9();
            this.c8 = c8;
            this.aa(1);
        }
        catch (Exception ex) {}
    }
    
    public final void b(final int n, final boolean b) {
        try {
            this.cb[n - 1] = b;
            if (this.ea) {
                boolean b2 = false;
                for (int i = 0; i < this.cb.length; ++i) {
                    if (this.cb[i]) {
                        b2 = true;
                        break;
                    }
                }
                this.k(false);
                if (!b2) {
                    this.bw.s(false);
                }
                this.bw.ai();
                this.bw.ah();
                this.bw.ap();
                this.a(this.c, this.g, this.e);
            }
            else {
                this.repaint();
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void d(final boolean[] array) {
        try {
            boolean b = false;
            for (int i = 0; i < array.length; ++i) {
                if (this.cb[i] != array[i]) {
                    b = true;
                }
                this.cb[i] = array[i];
            }
            if (b) {
                if (this.ea) {
                    boolean b2 = false;
                    for (int j = 0; j < this.cb.length; ++j) {
                        if (this.cb[j]) {
                            b2 = true;
                            break;
                        }
                    }
                    this.k(false);
                    if (!b2) {
                        this.bw.s(false);
                    }
                    this.bw.ai();
                    this.bw.ah();
                    this.bw.ap();
                    this.a(this.c, this.g, this.e);
                }
                else {
                    this.repaint();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void an() {
        try {
            for (int i = 0; i < this.cb.length; ++i) {
                this.cb[i] = false;
            }
            if (this.ea) {
                this.k(false);
                this.bw.s(false);
                this.bw.ai();
                this.bw.ah();
                this.bw.ap();
                this.a(this.c, this.g, this.e);
            }
            else {
                this.repaint();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final Color d2) {
        this.d2 = d2;
        this.d3 = null;
    }
    
    public final void a(final Color[] d3) {
        this.d2 = null;
        this.d3 = d3;
    }
    
    public final void a(final Object d0, final int dy) {
        this.d0 = d0;
        this.dy = dy;
        this.d1 = null;
    }
    
    public final void a(final Object[] d1, final int dy) {
        this.d0 = null;
        this.dy = dy;
        this.d1 = d1;
    }
    
    public final void a(final ac m, final String by, final Object o, final String b0, final Object[] b2, final Object[] array, final boolean n, final ck o2, final dx q, final c p14, final a0 b3, final int k, final Object d4, final boolean b4) {
        try {
            this.r();
            this.d4 = d4;
            this.k = k;
            this.m = m;
            this.by = by;
            this.o = o2;
            this.p = p14;
            this.q = q;
            this.b0 = b0;
            this.b5 = b2;
            this.b6 = b3;
            this.n = n;
            this.cg = false;
            if (this.ai != null) {
                this.s = this.ai.length;
            }
            else {
                this.s = 0;
            }
            if (q != null) {
                if (b2 != null) {
                    this.r = b2.length;
                }
                else {
                    this.r = q.u;
                }
            }
            else {
                this.r = 0;
            }
            try {
                this.aq.a(this.ba);
                this.aq.aa(this.a4);
                if (this.d2 != null) {
                    this.aq.b(this.d2);
                }
                else if (this.d3 != null) {
                    this.aq.a(this.d3);
                }
                else {
                    this.aq.b((Color)null);
                }
                if (this.d0 != null) {
                    this.aq.a(this.d0, this.dy);
                }
                else if (this.d1 != null) {
                    this.aq.a(this.d1, this.dy);
                }
                else {
                    this.aq.a((Object)null, this.dy);
                }
                if (this.ai != null && this.as != null) {
                    this.as.a(null, by, o, b0, this.ai, array, n, o2, q, p14, b3, null, d4, b4);
                    this.ar = true;
                }
                this.aq.a(m, by, o, b0, b2, array, n, o2, q, p14, b3, null, d4, b4);
            }
            catch (Exception ex2) {}
            this.setLayout(null);
            this.bt();
            this.bu();
            this.bv();
            this.ab(false);
            if (k > 1 || this.l > 1) {
                this.at = true;
            }
            this.validate();
            this.dt = true;
            this.repaint();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void ab(final boolean b) {
        try {
            if (b && ji.util.i.c(275)) {
                this.u();
                this.bx();
                this.repaint();
            }
            if (this.cb == null) {
                this.cb = new boolean[this.ae()];
            }
            else {
                synchronized (this.cb) {
                    final int ae = this.ae();
                    if (ae != this.cb.length) {
                        final boolean[] cb = new boolean[ae];
                        for (int n = 0; n < cb.length && n < this.cb.length; ++n) {
                            cb[n] = this.cb[n];
                        }
                        this.cb = cb;
                    }
                }
                // monitorexit(this.cb)
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final void bt() {
        try {
            if (this.db == null) {
                if (ji.util.i.c(289) || this.ct.cj()) {
                    this.db = new ft(this.cu, true);
                }
                else {
                    this.db = new fu(this.cu);
                }
                this.db.setVisible(false);
                if (this.dm == null) {
                    this.dm = new wn();
                }
                this.db.addItemListener(this.dm);
                this.add(this.db.getComponent());
            }
        }
        catch (Exception ex) {}
    }
    
    private final void bu() {
        try {
            if (!this.ct.cj()) {
                return;
            }
            if (this.de == null) {
                (this.de = new ft(this.cu, ji.util.i.c(289) || this.ct.cj())).setVisible(false);
                if (this.dn == null) {
                    this.dn = new wo();
                }
                this.de.addItemListener(this.dn);
                this.add(this.de.getComponent());
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final void bv() {
        try {
            if (this.dc == null) {
                if (ji.util.i.c(289) || this.ct.cj()) {
                    this.dc = new ft(this.cu, true);
                }
                else {
                    this.dc = new fu(this.cu);
                }
                this.dc.setVisible(false);
                if (this.do == null) {
                    this.do = new wp();
                }
                this.dc.addItemListener(this.do);
                this.add(this.dc.getComponent());
            }
        }
        catch (Exception ex) {}
    }
    
    private boolean a(final ad ad) {
        return !ji.util.i.c(275) || ad.gz();
    }
    
    private final synchronized void bw() {
        try {
            if (!this.a() && !ji.util.d.w && this.dc != null && !this.di && this.a(this.ct)) {
                if (this.ee.isVisible()) {
                    this.ee.setVisible(false);
                }
                if (this.db.isVisible()) {
                    this.db.setVisible(false);
                }
                if (this.de != null && this.de.isVisible()) {
                    this.de.setVisible(false);
                }
                if (this.ef.isVisible()) {
                    this.ef.setVisible(false);
                }
                if (this.dc.isVisible()) {
                    this.dc.setVisible(false);
                }
                int length = 0;
                if (this.ai != null) {
                    length = this.ai.length;
                }
                final String v = this.v(1155);
                final int n = length;
                final int n2 = Math.max(n / 10, 10) - 1;
                int n3 = 0;
                int n4 = 0;
                if (this.ah != null) {
                    final int length2 = this.ah.length;
                    for (int i = 0; i < length2; ++i) {
                        if (this.ah[i] != null) {
                            this.dc.add(this.ah[i]);
                        }
                        else {
                            this.dc.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(i + 1))));
                        }
                        ++n4;
                        ++n3;
                        if (n4 > n2) {
                            n4 = 0;
                            if (n > 0) {
                                this.w(100 * n3 / n);
                            }
                        }
                    }
                    for (int j = length2; j < length; ++j) {
                        this.dc.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(j + 1))));
                        ++n4;
                        ++n3;
                        if (n4 > n2) {
                            n4 = 0;
                            if (n > 0) {
                                this.w(100 * n3 / n);
                            }
                        }
                    }
                }
                else {
                    for (int k = 0; k < length; ++k) {
                        this.dc.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(k + 1))));
                        ++n4;
                        ++n3;
                        if (n4 > n2) {
                            n4 = 0;
                            if (n > 0) {
                                this.w(100 * n3 / n);
                            }
                        }
                    }
                }
                this.di = true;
                this.b8();
            }
        }
        catch (Exception ex) {}
        finally {
            this.w(0);
        }
    }
    
    private final synchronized void bx() {
        try {
            if (!this.a() && !ji.util.d.w) {
                synchronized (this.z) {
                    if (this.z.a == null && this.ct.fu()) {
                        // monitorexit(this.z)
                        return;
                    }
                }
                // monitorexit(this.z)
                if (this.db != null && !this.df && this.a(this.ct)) {
                    try {
                        this.db.beginBulkAdd();
                        this.db.getSelectedIndex();
                        if (this.ee.isVisible()) {
                            this.ee.setVisible(false);
                        }
                        if (this.de != null && this.de.isVisible()) {
                            this.de.setVisible(false);
                        }
                        if (!this.db.isSwing() && this.db.isVisible()) {
                            this.db.setVisible(false);
                        }
                        if (this.ef.isVisible()) {
                            this.ef.setVisible(false);
                        }
                        if (this.dc != null && this.dc.isVisible()) {
                            this.dc.setVisible(false);
                        }
                        final int ae = this.ae();
                        final String v = this.v(302);
                        final int n = ae;
                        final int n2 = Math.max(n / 10, 10) - 1;
                        int n3 = 0;
                        int n4 = 0;
                        String[] a = null;
                        synchronized (this.z) {
                            a = this.z.a;
                        }
                        // monitorexit(this.z)
                        final int[] aa = this.aa;
                        if (a != null) {
                            final int length = a.length;
                            for (int i = 0; i < length; ++i) {
                                if (a[i] != null) {
                                    this.db.add(a[i]);
                                }
                                else {
                                    this.db.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(i + 1))));
                                }
                                ++n4;
                                ++n3;
                                if (n4 > n2) {
                                    n4 = 0;
                                    if (n > 0) {
                                        this.w(100 * n3 / n);
                                    }
                                }
                            }
                            if (aa == null) {
                                for (int j = length; j < ae; ++j) {
                                    this.db.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(j + 1))));
                                    ++n4;
                                    ++n3;
                                    if (n4 > n2) {
                                        n4 = 0;
                                        if (n > 0) {
                                            this.w(100 * n3 / n);
                                        }
                                    }
                                }
                            }
                            this.df = true;
                        }
                        else if (ae > 0) {
                            for (int k = 0; k < ae; ++k) {
                                this.db.add(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(v))).append(" ").append(k + 1))));
                                ++n4;
                                ++n3;
                                if (n4 > n2) {
                                    n4 = 0;
                                    if (n > 0) {
                                        this.w(100 * n3 / n);
                                    }
                                }
                            }
                            this.df = true;
                        }
                        if (this.df) {
                            this.ax();
                        }
                    }
                    finally {
                        this.db.finishBulkAdd();
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.w(0);
        }
    }
    
    private final synchronized void by() {
        try {
            if (!this.a() && !ji.util.d.w && this.de != null && !this.dg && this.a(this.ct)) {
                try {
                    this.de.beginBulkAdd();
                    int selectedIndex = this.de.getSelectedIndex();
                    boolean equals = this.ac == null;
                    if (!equals) {
                        equals = this.ac.equals(this.dd);
                    }
                    if (!equals || !this.dh) {
                        selectedIndex = -1;
                        this.de.clear();
                    }
                    if (this.ac != null) {
                        this.dd = (es)this.ac.d();
                    }
                    else {
                        this.dd = null;
                    }
                    if (this.ee.isVisible()) {
                        this.ee.setVisible(false);
                    }
                    if (this.db.isVisible()) {
                        this.db.setVisible(false);
                    }
                    if (!this.de.isSwing() && this.de.isVisible()) {
                        this.de.setVisible(false);
                    }
                    if (this.ef.isVisible()) {
                        this.ef.setVisible(false);
                    }
                    if (this.dc != null && this.dc.isVisible()) {
                        this.dc.setVisible(false);
                    }
                    final int ae = this.ae();
                    this.v(302);
                    final int n = ae;
                    final int n2 = Math.max(n / 10, 10) - 1;
                    int n3 = 0;
                    int n4 = 0;
                    final String[] ad = this.ad;
                    final int[] ae2 = this.ae;
                    final int rows = this.de.getRows();
                    if (ad != null) {
                        for (int length = ad.length, i = rows; i < length; ++i) {
                            if (ad[i] != null) {
                                this.de.add(ad[i]);
                            }
                            ++n4;
                            ++n3;
                            if (n4 > n2) {
                                n4 = 0;
                                if (n > 0) {
                                    this.w(100 * n3 / n);
                                }
                            }
                        }
                        if (rows == 0 && selectedIndex != -1) {
                            this.de.select(selectedIndex);
                        }
                        this.dg = true;
                        this.dh = true;
                    }
                    else {
                        if (!this.dh) {
                            final String d = this.ct.d(this.ac);
                            this.de.clear();
                            this.de.add(d);
                        }
                        this.dg = true;
                    }
                }
                finally {
                    this.de.finishBulkAdd();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            this.w(0);
        }
    }
    
    public final void o(final int l) throws Exception {
        if (l != this.l) {
            if (!this.a2) {
                this.a(this.getGraphics(), this.l - 1, this.aw == null, this.ef, this.d);
                this.a(this.getGraphics(), l - 1, this.aw == null, this.ef, this.d);
            }
            this.l = l;
            this.b8();
            this.z(10);
            if (this.aw == null) {
                this.m();
            }
        }
    }
    
    public final int ao() {
        return this.l;
    }
    
    public final void p(final int k) throws Exception {
        if (this.k != k) {
            final int i = this.k;
            this.k = k;
            if (this.ah() != 0) {
                if (this.aw == null && ji.util.d.di() == 1) {
                    this.aq.dp();
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.cu, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: THM SetPageA1: ").append(k))));
                }
                if (!this.a2 && !this.b()) {
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.cu, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: THM SetPageA2: ").append(k))));
                    }
                    boolean b = true;
                    if (this.c6 && !this.c[this.k - 1].d() && this.c[this.k - 1].c()) {
                        b = false;
                        this.c(this.k, true);
                    }
                    if (b) {
                        this.a(this.getGraphics(), i - 1, this.aw == null, this.ee, this.c);
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cu, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: THM SetPageA3: ").append(k))));
                        }
                        this.a(this.getGraphics(), this.k - 1, this.aw == null, this.ee, this.c);
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cu, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: THM SetPageA4: ").append(k))));
                        }
                        if (this.aw == null) {
                            this.m();
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cu, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: THM SetPageA5: ").append(k))));
                        }
                    }
                    if (this.da != 4) {
                        this.ax();
                    }
                    this.aa(10);
                }
            }
        }
    }
    
    public final int ap() {
        return this.k;
    }
    
    public final dx aq() {
        return this.aq.r();
    }
    
    public final void a(final a6 a6) {
        this.b(a6);
    }
    
    private final void g(final String s) {
        this.b(new a6(this, 1, s));
    }
    
    private final void w(final int n) {
        this.b(new a6(this, 4, n));
    }
    
    public final void a(final af af) {
        if (this.al != null && this.al.a(af)) {
            this.al.b(af);
        }
    }
    
    private final void x(final int n) {
        if (n > 0 && this.cj) {
            try {
                if (n > 0 && this.ae() > 0 && ji.util.d.di() == 0) {
                    this.cj = false;
                    String s;
                    if (this.b()) {
                        s = ji.util.d.d(n, this.ae(), this.cu);
                    }
                    else {
                        s = ji.util.d.d(n, this.ae(), this.cu);
                        if (n == this.k) {
                            if (this.l(n) != 0) {
                                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(", ").append(this.v(304)).append(" ").append(this.l(n)).append("'")));
                            }
                            if (this.m(n) != 0) {
                                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(", ").append(ji.util.d.a(this.m(n), this.cu))));
                            }
                        }
                    }
                    this.g(String.valueOf(String.valueOf(s)).concat("..."));
                }
                else {
                    this.g("");
                }
            }
            catch (Exception ex) {}
            finally {
                this.cj = true;
            }
        }
    }
    
    public final void b(final af af) {
        if (this.al == null) {
            this.al = new c("jiThumbs3", 2);
        }
        if (!this.al.a(af)) {
            this.al.c(af);
        }
    }
    
    protected final void b(final a6 a6) {
        if (ji.util.d.am()) {
            if (this.dw != null) {
                this.dw.a(new a9(this, a6, false));
            }
        }
        else {
            this.c(a6);
        }
    }
    
    protected final void c(final a6 a6) {
        try {
            if (a6.c() == 0) {
                a6.b(2);
            }
            this.bw.a(a6);
            if (!a6.b(this)) {
                a6.a(this);
                if (this.al != null) {
                    for (int i = 0; i < this.al.b(); ++i) {
                        ((af)this.al.b(i)).a(a6);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ae ae) {
        if (this.an != null && this.an.a(ae)) {
            this.an.b(ae);
        }
    }
    
    public final void b(final ae ae) {
        if (this.an == null) {
            this.an = new c("jiThumbs4", 2);
        }
        if (!this.an.a(ae)) {
            this.an.c(ae);
        }
    }
    
    public final void a(final b b) {
        if (this.ao != null && this.ao.a(b)) {
            this.ao.b(b);
        }
    }
    
    public final void b(final b b) {
        if (this.ao == null) {
            this.ao = new c("jiThumbs5", 2);
        }
        if (!this.ao.a(b)) {
            this.ao.c(b);
        }
    }
    
    public final void a(final a9 a9) {
        final Object d = a9.d();
        if (d instanceof a6) {
            this.c((a6)a9.d());
        }
        else if (d instanceof d7) {
            final d7 d2 = (d7)a9.d();
            switch (d2.d()) {
                case 59: {
                    this.b4();
                    break;
                }
                case 60: {
                    this.b2();
                    break;
                }
                case 61: {
                    this.aw();
                    break;
                }
                default: {
                    this.b(d2);
                    break;
                }
            }
        }
        else if (d instanceof fv) {
            this.b((fv)a9.d());
        }
        else if (d instanceof d7) {
            this.b((d7)a9.d());
        }
    }
    
    protected final void a(final fv fv) {
        if (ji.util.d.am()) {
            if (this.dw != null) {
                this.dw.a(new a9(this, fv, false));
            }
        }
        else {
            this.b(fv);
        }
    }
    
    private final String a(final a3 a3) {
        String concat = "";
        if (this.ao != null) {
            for (int i = 0; i < this.ao.b(); ++i) {
                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(((b)this.ao.b(i)).javaScriptUpdate(a3))));
            }
        }
        return concat;
    }
    
    private final void b(final fv fv) {
        if (this.an != null) {
            for (int i = 0; i < this.an.b(); ++i) {
                ((ae)this.an.b(i)).a(fv);
            }
        }
    }
    
    public final void t(final boolean b9) {
        this.b9 = b9;
        if (this.aq != null) {
            this.aq.b1(b9);
        }
    }
    
    public final void ar() {
        if (this.bw != null) {
            this.bw.an();
        }
    }
    
    public final boolean as() {
        return this.bw != null && this.bw.ao();
    }
    
    public final void u(final boolean b) {
        if (this.bw != null) {
            this.bw.m(b);
        }
    }
    
    public final void at() {
        if (this.b()) {
            this.a(this.d, this.h, this.f);
        }
        else {
            this.a(this.c, this.g, this.e);
        }
    }
    
    private final void a(final vz[] array, final boolean[] array2, final boolean[] array3) {
        synchronized (this) {
            if (this.q != null && !this.bi && this.b9 && this.b2) {
                boolean b = true;
                if (this.ct != null && this.ct.bi(4) && this.ct.k3()) {
                    b = false;
                }
                if (b && !this.bg && this.bs()) {
                    if (this.a()) {
                        if (this.aw == null) {
                            this.aa(this.b4 = false);
                            final Dimension size = this.getSize();
                            if (size != null) {
                                if (size.width > this.a && size.height > this.b) {
                                    if (this.bw != null && this.bw.d.getValue() != -this.bl.y && this.bw.d.isVisible()) {
                                        this.a(this.bl.x, -this.bw.d.getValue(), array);
                                    }
                                    try {
                                        if (this.b()) {
                                            this.b(this.getParent());
                                        }
                                        else {
                                            this.a(this.getParent());
                                        }
                                    }
                                    catch (Exception ex) {}
                                    this.bz = false;
                                    this.aw = new bb(this.cu, new v5(this));
                                    ++ji.document.c7.bx;
                                    this.aw.start();
                                }
                                else {
                                    this.a(new d7(this, 8, 0));
                                }
                            }
                        }
                        else if (this.getSize() != null) {
                            this.b4 = true;
                            this.y();
                        }
                    }
                    else {
                        this.bg = true;
                        this.a(new d7(this, 8, 0));
                        this.bz();
                    }
                }
                else {
                    if (b) {
                        this.bg = true;
                    }
                    this.a(new d7(this, 8, 0));
                    this.bz();
                }
            }
        }
    }
    
    private final void bz() {
        try {
            if (this.dt) {
                this.dt = false;
                "".concat(String.valueOf(String.valueOf(this.aq.r().f)));
                String s;
                if (this.aq.r().f == null) {
                    s = "".concat(String.valueOf(String.valueOf(this.aq.s())));
                }
                else {
                    s = "".concat(String.valueOf(String.valueOf(this.aq.r().f)));
                }
                this.ct.a(this, 5, "opened: ".concat(String.valueOf(String.valueOf(s))), this.ap(), this.ae(), 1, 1);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b0() {
        this.b2();
    }
    
    public final void v(final boolean eh) {
        this.eh = eh;
    }
    
    private final void b1() {
        if (!this.eh) {
            ji.util.d.ex();
        }
    }
    
    private final void b2() {
        try {
            final Graphics graphics = this.getGraphics();
            if (graphics != null && (this.be || !this.b())) {
                graphics.setColor(this.getBackground());
                this.a(graphics, 0, 0, this.getSize().width, this.getSize().height);
                graphics.setColor(this.getForeground());
                this.b1();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final cy cy) {
        try {
            if (this.a()) {
                cy.k(true);
                cy.m(this.ek);
                cy.bw(this.em);
                cy.cy(this.ak);
                cy.setBorderStyle(6);
                cy.an(this.t);
                cy.s(false);
                cy.aa(this.dz);
                cy.ad(this.c6);
                if (this.bj == 4) {
                    this.a1 = this.bf.i();
                }
                else if (this.bj == 5) {
                    this.a1 = this.bf.i() / 2;
                }
                else if (this.bj == 6) {
                    this.a1 = 1;
                }
                else {
                    this.a1 = 0;
                }
                cy.a(this.c9);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final int n, final int n2) {
        if (this.b()) {
            this.a(n, n2, this.d);
        }
        else {
            this.a(n, n2, this.c);
        }
    }
    
    private final void a(final int n, final int n2, final vz[] array) {
        if (this.bl.x != n || this.bl.y != n2) {
            this.a(this.getParent(), array);
            this.bl.x = Math.min(n, 0);
            this.bl.y = Math.min(n2, 0);
            this.a(this.getParent(), array);
            this.repaint();
        }
    }
    
    public final void c(final int width, final int height) {
        if (this.bl.width != width || this.bl.height != height) {
            this.bl.width = width;
            this.bl.height = height;
            this.a(this.getParent());
        }
    }
    
    public final Dimension au() {
        return new Dimension(this.bl.width, this.bl.height);
    }
    
    public final void av() {
        try {
            if (!this.d6) {
                this.d6 = true;
                if (this.d9 == null) {
                    this.d9 = new v();
                }
                this.d8 = this.d9.c(this.ct, this.cu, this);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final String s) {
        try {
            if (this.d6) {
                if (this.d9 != null) {
                    this.d9.a(this.ct, s, this.d8);
                }
                this.d6 = false;
                this.d9 = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void update(final Graphics graphics) {
        if (graphics != null) {
            if (this.isSwing()) {
                super.update(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
    }
    
    public void aw() {
        this.a(this.getGraphics());
    }
    
    public void a(final Graphics graphics) {
        if (!this.eb) {
            try {
                if (ji.util.d.ax(this.cu) && ji.util.d.dp() && this.a2) {
                    return;
                }
                this.b1();
                this.eb = true;
                this.av();
                try {
                    final Dimension size = this.getSize();
                    if (size.width < 10 || size.height < 10) {
                        return;
                    }
                }
                catch (Exception ex) {}
                try {
                    if (!this.co) {
                        if (this.bw != null) {
                            this.bw.b();
                        }
                        this.co = true;
                    }
                }
                catch (Exception ex2) {}
                if (!this.ca && graphics != null) {
                    try {
                        for (int n = 1200; this.a2 && n > 0; n -= 100) {
                            ji.util.d.b(100, 120, this.cu);
                        }
                        if (this.bi) {
                            this.b1();
                            this.bi = false;
                            if (this.b()) {
                                this.a(this.d, this.h, this.f);
                            }
                            else {
                                this.a(this.c, this.g, this.e);
                            }
                        }
                        else {
                            this.b1();
                            this.b3();
                        }
                    }
                    catch (Exception ex3) {}
                }
                if (this.b()) {
                    this.z(2);
                }
                else {
                    this.aa(2);
                }
            }
            finally {
                this.eb = false;
            }
        }
    }
    
    public final void w(final boolean i) {
        try {
            this.i = i;
        }
        catch (Exception ex) {}
    }
    
    private void b3() {
        this.b4();
    }
    
    private void b4() {
        this.a(this.getGraphics(), this.b5());
    }
    
    private vz[] b5() {
        if (this.b()) {
            return this.d;
        }
        return this.c;
    }
    
    private void a(final Graphics graphics, final vz[] array) {
        if (this.a2) {
            return;
        }
        final boolean b = this.b() && this.da == 3 && this.dc != null;
        boolean b2 = b || array != null;
        if (!b2 && !b) {
            b2 = !this.a();
        }
        if (b2 && !this.a2 && graphics != null && this.b9 && !this.ca) {
            try {
                this.a2 = true;
                if (graphics != null && array != null) {
                    try {
                        if (this.at) {
                            if (this.b()) {
                                this.q(this.l);
                            }
                            else if (this.da == 4) {
                                this.r(this.dq);
                            }
                            else {
                                this.s(this.k);
                            }
                        }
                    }
                    catch (Exception ex4) {}
                }
                if (this.a()) {
                    int n = -1;
                    Point a = null;
                    int n2 = 0;
                    int n3 = 0;
                    if (this.b() && !this.ef.isVisible()) {
                        this.ef.setVisible(true);
                        if (this.ee.isVisible()) {
                            this.ee.setVisible(false);
                        }
                    }
                    if (!this.b() && !this.ee.isVisible()) {
                        this.ee.setVisible(true);
                        if (this.ef.isVisible()) {
                            this.ef.setVisible(false);
                        }
                    }
                    if (this.db.isVisible()) {
                        this.db.setVisible(false);
                    }
                    if (this.de != null && this.de.isVisible()) {
                        this.de.setVisible(false);
                    }
                    if (this.dc.isVisible()) {
                        this.dc.setVisible(false);
                    }
                    for (int i = 0; i < array.length; ++i) {
                        try {
                            if (array[i] != null && array[i].b() && this.y(i)) {
                                final Point a2 = array[array.length - 1].a();
                                a = array[i].a();
                                n3 = this.bl.y + a2.y + this.b;
                                n2 = this.bl.x + a2.x + this.a;
                                try {
                                    if (this.b()) {
                                        this.a(graphics, i, true, this.ef, this.d);
                                    }
                                    else {
                                        this.a(graphics, i, true, this.ee, array);
                                    }
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                n = i;
                            }
                            else if (this.y(i) && n > 1 && this.b2 && !this.cm && i > 1 && i > n && array[n - 1] != null && array[n - 1].b()) {
                                break;
                            }
                        }
                        catch (Exception ex2) {
                            ji.util.d.b(ex2);
                            if (ji.util.d.cy()) {
                                ex2.printStackTrace();
                            }
                        }
                    }
                    if (graphics != null && array != null) {
                        Point a3 = array[array.length - 1].a();
                        if (this.ea) {
                            a3 = a;
                        }
                        final Point point = a3;
                        point.x += this.bl.x;
                        final Point point2 = a3;
                        point2.y += this.bl.y;
                        int max = a3.x + this.a;
                        int max2 = a3.y + this.b;
                        if (!this.ea) {
                            max = Math.max(a3.x + this.a, n2);
                            max2 = Math.max(a3.y + this.b, n3);
                        }
                        if (max2 < this.getSize().height) {
                            graphics.setColor(this.getBackground());
                            this.a(graphics, 1, max2, this.getSize().width - 2, this.getSize().height - max2);
                        }
                        if (max < this.getSize().width) {
                            graphics.setColor(this.getBackground());
                            this.a(graphics, max, max2 - this.b - this.bm, this.getSize().width - 2, this.b + 2 * this.bm);
                        }
                    }
                }
                else {
                    this.b6();
                }
            }
            catch (Exception ex3) {
                ji.util.d.a(ex3);
            }
            finally {
                this.a2 = false;
            }
            if (this.b()) {
                this.z(3);
            }
            else {
                this.aa(3);
            }
        }
        this.b1();
    }
    
    public void repaint() {
        if (!this.bj() && !this.cc) {
            if (this.b()) {
                if (!this.a2) {
                    this.ef.repaint();
                }
            }
            else if (this.ee != null) {
                this.ee.invalidate();
                if (!this.a2) {
                    this.ee.repaint();
                }
            }
        }
    }
    
    private final void b6() {
        synchronized (this) {
            if (this.da == 1 && this.db != null) {
                this.bx();
                final boolean b7 = this.b7();
                if (this.ee.isVisible()) {
                    this.ee.setVisible(false);
                }
                if (this.de != null && this.de.isVisible()) {
                    this.de.setVisible(false);
                }
                if (this.ef.isVisible()) {
                    this.ef.setVisible(false);
                }
                if (this.dc != null && this.dc.isVisible()) {
                    this.dc.setVisible(false);
                }
                if (!this.db.isVisible()) {
                    this.db.setVisible(true);
                }
                if (b7) {
                    this.s(this.ap());
                }
            }
            else if (this.ct.cj() && this.da == 4 && this.de != null) {
                this.by();
                final boolean b8 = this.b7();
                if (this.ee.isVisible()) {
                    this.ee.setVisible(false);
                }
                if (this.db != null && this.db.isVisible()) {
                    this.db.setVisible(false);
                }
                if (this.ef.isVisible()) {
                    this.ef.setVisible(false);
                }
                if (this.dc != null && this.dc.isVisible()) {
                    this.dc.setVisible(false);
                }
                if (!this.de.isVisible()) {
                    this.de.setVisible(true);
                }
                if (b8) {
                    this.r(this.dq);
                }
            }
            else if (this.b() && this.da == 3 && this.dc != null) {
                this.bw();
                final boolean b9 = this.b7();
                if (this.ee.isVisible()) {
                    this.ee.setVisible(false);
                }
                if (this.db != null && this.db.isVisible()) {
                    this.db.setVisible(false);
                }
                if (this.de != null && this.de.isVisible()) {
                    this.de.setVisible(false);
                }
                if (this.ef.isVisible()) {
                    this.ef.setVisible(false);
                }
                if (!this.dc.isVisible()) {
                    this.dc.setVisible(true);
                }
                if (b9) {
                    this.q(this.l);
                }
            }
        }
    }
    
    private final boolean b7() {
        synchronized (this) {
            boolean b = false;
            try {
                final Rectangle bounds = this.getBounds();
                final int height = bounds.height;
                if (this.db != null) {
                    b |= ji.util.e.a(this.db.getComponent(), 0, 0, bounds.width, height);
                }
                if (this.de != null) {
                    b |= ji.util.e.a(this.de.getComponent(), 0, 0, bounds.width, height);
                }
                if (this.ee != null) {
                    b |= ji.util.e.a(this.ee, 0, 0, bounds.width, bounds.height);
                }
                if (this.dc != null) {
                    b |= ji.util.e.a(this.dc.getComponent(), 0, 0, bounds.width, height);
                }
                if (this.ef != null) {
                    b |= ji.util.e.a(this.ef, 0, 0, bounds.width, bounds.height);
                }
            }
            catch (Exception ex) {}
            return b;
        }
    }
    
    private final boolean y(final int n) {
        boolean b = true;
        if (this.ea && !this.cb[n]) {
            b = false;
        }
        return b;
    }
    
    private final void a(final Graphics graphics, final int n, final boolean b, final uy uy, final vz[] array) {
        if (!this.a() || ji.util.d.w || array == null) {
            return;
        }
        final int n2 = this.b() ? this.l : this.k;
        try {
            if (!this.y(n)) {
                return;
            }
            this.bq = true;
            if (this.db != null && this.db.isVisible()) {
                this.db.setVisible(false);
            }
            if (this.de != null && this.de.isVisible()) {
                this.de.setVisible(false);
            }
            if (!uy.isVisible()) {
                uy.setVisible(true);
            }
            if (graphics != null && array != null && !this.ca) {
                final Point a;
                final Point point = a = array[n].a();
                a.x += this.bl.x;
                final Point point2 = point;
                point2.y += this.bl.y;
                this.bb.a(point.x, point.y);
                if (n == n2 - 1 && !this.i) {
                    this.bb.b(SystemColor.controlDkShadow);
                    this.bb.a(6);
                }
                else {
                    Color color = SystemColor.controlShadow;
                    if (this.b()) {
                        if (ji.util.e.g != null) {
                            color = ji.util.d.k(ji.util.e.g);
                        }
                        this.bb.b(color);
                        this.bb.a(2);
                    }
                    else {
                        this.bb.b(color);
                        this.bb.a(1);
                    }
                }
                Rectangle rectangle;
                if (this.b()) {
                    rectangle = this.b(graphics, n);
                }
                else {
                    rectangle = this.c(graphics, n);
                }
                graphics.setColor(this.getBackground());
                this.a(graphics, point.x - this.bm, point.y - this.bm, this.a + 2 * this.bm, this.bm);
                this.a(graphics, point.x - this.bm, point.y + this.b, this.a + 2 * this.bm, this.bm);
                this.a(graphics, point.x - this.bm, point.y, this.bm, this.b);
                this.a(graphics, point.x + this.a, point.y, this.bm, this.b);
                if (array == null) {
                    return;
                }
                if (array[n] == null) {
                    return;
                }
                if (!array[n].d() || array[n].e() || !array[n].c() || !b) {
                    if (n == n2 - 1 && !this.i) {
                        graphics.setColor(this.bn);
                    }
                    else {
                        graphics.setColor(this.getBackground());
                    }
                    this.a(graphics, point.x + 1, point.y + 1, this.a - 2, this.b - 2 - rectangle.height);
                    this.a(graphics, point.x + 1, point.y + this.b - 2 - rectangle.height, this.a - 2 - rectangle.width, rectangle.height + 1);
                    this.b1();
                }
                this.bb.d(this.bd, graphics);
                boolean b2 = false;
                if (n == array.length - 1) {
                    b2 = true;
                }
                else if (n < array.length - 1 && array[n].a().y != array[n + 1].a().y) {
                    b2 = true;
                }
                if (b2) {
                    graphics.setColor(this.getBackground());
                    this.a(graphics, point.x + this.a, point.y, this.getSize().width - point.x, this.b);
                }
                this.b1();
                if (b || array[n].h != null) {
                    if (array[n].f() && array[n].h == null) {
                        this.a(graphics, n, this.a(graphics, n), this.y);
                    }
                    else {
                        this.a(graphics, n, rectangle, array[n]);
                    }
                }
                if (this.b()) {
                    this.a(graphics, n, rectangle, array, this.av, this.y);
                }
                else {
                    this.a(graphics, n, rectangle, array, this.au, this.x);
                }
                if (this.b()) {
                    if (this.l - 1 == n) {
                        this.z(4);
                    }
                }
                else if (this.c8 - 1 == n) {
                    this.aa(4);
                }
                this.b1();
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        finally {
            this.bq = false;
        }
    }
    
    private final Rectangle a(final Graphics graphics, final int n) {
        if (graphics != null && this.c != null && this.a()) {
            final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
            final Point a;
            final Point point = a = this.c[n].a();
            a.x += this.bl.x;
            final Point point2 = point;
            point2.y += this.bl.y;
            if (this.en == null) {
                this.en = this.v(1157);
            }
            if (this.en != null) {
                final char[] charArray = this.en.toCharArray();
                String concat = "";
                int n2 = 0;
                final int height = graphics.getFontMetrics().getHeight();
                int n3 = height + this.aj;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '\n' || charArray[i] == '\r') {
                        n3 += height;
                        n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
                if (concat.length() > 0) {
                    n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                }
                rectangle.width = n2 + 3;
                rectangle.height = n3 + 1;
                rectangle.x = point.x + (this.a - n2 - 7) / 2;
                rectangle.y = point.y + (this.b - n3 - 4) / 2;
            }
            return rectangle;
        }
        return null;
    }
    
    public Graphics getGraphics() {
        if (this.b()) {
            return this.ef.getGraphics();
        }
        return this.ee.getGraphics();
    }
    
    private final void z(final int n) {
        try {
            if (this.a() && !ji.util.d.w && !this.i) {
                final Point a;
                final Point point = a = this.d[Math.max(this.l, 1) - 1].a();
                a.x += this.bl.x;
                final Point point2 = point;
                point2.y += this.bl.y;
                final Graphics graphics = this.ef.getGraphics();
                graphics.setColor(this.getBackground().darker().darker());
                final int n2 = this.b + 4;
                final int n3 = point.y - 2;
                if (n3 + n2 >= 0 && n3 < this.getSize().height) {
                    ji.util.d.a(graphics, point.x - 2, n3, this.a + 4, n2, 2);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void aa(final int n) {
        try {
            if (this.a() && !ji.util.d.w && !this.i) {
                final int n2 = Math.max(this.c8, 1) - 1;
                if (this.y(n2)) {
                    final Point a;
                    final Point point = a = this.c[n2].a();
                    a.x += this.bl.x;
                    final Point point2 = point;
                    point2.y += this.bl.y;
                    final Graphics graphics = this.ee.getGraphics();
                    graphics.setColor(this.getBackground().darker().darker());
                    final int n3 = this.b + 4;
                    final int n4 = point.y - 2;
                    if (n4 + n3 >= 0 && n4 < this.getSize().height) {
                        ji.util.d.a(graphics, point.x - 2, n4, this.a + 4, n3, 2);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ax() {
        try {
            if (this.db != null) {
                final int max = Math.max(this.ap() - 1, 0);
                if (this.dp != max) {
                    if (this.ag >= 0) {
                        this.db.select(this.ag);
                        this.dp = this.ag;
                    }
                    else {
                        this.db.select(max);
                        this.dp = max;
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ay() {
        try {
            if (this.de != null && this.de.isVisible()) {
                int ii = -1;
                if (this.ct != null) {
                    ii = this.ct.ii();
                }
                if (ii >= 0) {
                    this.de.select(ii, false);
                    this.de.makeVisible(ii);
                    this.dq = ii;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final void b8() {
        try {
            if (this.dc != null) {
                final int max = Math.max(this.l - 1, 0);
                this.dc.select(max);
                this.dr = max;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b9() {
        try {
            final Point a;
            final Point point = a = this.c[Math.max(this.c8, 1) - 1].a();
            a.x += this.bl.x;
            final Point point2 = point;
            point2.y += this.bl.y;
            final Graphics graphics = this.ee.getGraphics();
            graphics.setColor(this.getBackground());
            this.b(graphics, point.x - 2, point.y - 2, this.a + 4, this.b + 4);
        }
        catch (Exception ex) {}
    }
    
    public final Color getBackground() {
        if (this.ed != null) {
            return this.ed;
        }
        return super.getBackground();
    }
    
    public final void setBackground(final Color ed) {
        try {
            super.setBackground(ed);
            if (this.ee != null) {
                this.ee.setBackground(ed);
            }
            this.ed = ed;
            this.bn = ji.util.e.a0();
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    private final void a(final Graphics graphics, final int n, final Rectangle rectangle, final vz vz) {
        try {
            if (graphics != null && vz != null && !this.ca && !ji.util.d.w) {
                final Point a;
                final Point point = a = vz.a();
                a.x += this.bl.x;
                final Point point2 = point;
                point2.y += this.bl.y;
                final int n2 = this.b() ? this.l : this.k;
                if (vz.d() && vz.c()) {
                    final Image g = vz.g();
                    if (g != null) {
                        this.du = true;
                        this.az = g.getWidth(null);
                        this.a0 = g.getHeight(null);
                        this.ax = point.x + (this.a - this.az - this.a1) / 2;
                        this.ay = point.y + (this.b - this.a0 - this.a1) / 2;
                        if (this.ax == point.x) {
                            ++this.ax;
                            this.az -= 2;
                        }
                        if (this.ay == point.y) {
                            ++this.ay;
                            this.a0 -= 2;
                        }
                        if (this.bj == 0) {
                            graphics.drawImage(g, this.ax, this.ay, this.az, this.a0, null);
                        }
                        else if (this.bj == 6) {
                            graphics.drawImage(g, this.ax + 1, this.ay + 1, this.az, this.a0, null);
                            this.bc.setBounds(0, 0, this.az + this.a1 + 1, this.a0 + this.a1 + 1);
                            this.bf.a(this.ax, this.ay);
                            this.bf.d(this.bc, graphics);
                        }
                        else {
                            graphics.drawImage(g, this.ax + 1, this.ay + 1, this.az, this.a0, null);
                            this.bc.setBounds(0, 0, this.az + this.a1 + 1, this.a0 + this.a1 + 1);
                            this.bf.a(this.ax, this.ay);
                            this.bf.d(this.bc, graphics);
                        }
                        if (n == n2 - 1) {
                            graphics.setColor(this.bn);
                        }
                        else {
                            graphics.setColor(this.getBackground());
                        }
                        this.a(graphics, point.x + 1, point.y + 1, this.ax - point.x - 1, this.b - 2);
                        int n3 = 0;
                        if (this.bj != 0) {
                            n3 = 2;
                        }
                        this.a(graphics, this.ax + this.az + n3, point.y + 1, point.x + this.a - this.ax - this.az - 1 - n3, this.b - 2 - rectangle.height);
                        this.a(graphics, this.ax + this.az + n3, rectangle.y + 1, rectangle.x - this.ax - this.az + 1 - n3, rectangle.height);
                        this.a(graphics, point.x + 1, point.y + 1, this.a - 2, this.ay - point.y - 1);
                        this.a(graphics, point.x + 1, this.ay + this.a0 + n3, this.a - 4 - rectangle.width, point.y + this.b - this.ay - this.a0 - 1 - n3);
                        this.a(graphics, rectangle.x + 1, this.ay + this.a0 + n3, rectangle.width + 2, rectangle.y - this.ay - this.a0 - n3);
                    }
                    else {
                        if (n == n2 - 1) {
                            graphics.setColor(this.bn);
                        }
                        else {
                            graphics.setColor(this.getBackground());
                        }
                        this.a(graphics, point.x + 1, point.y + 1, this.a - 2, this.b - 2 - rectangle.height);
                        this.a(graphics, point.x + 1, point.y + this.b - 2 - rectangle.height, this.a - 2 - rectangle.width, rectangle.height + 1);
                        this.b1();
                    }
                    this.at = false;
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        try {
            if (n3 > 0 && n4 > 0) {
                graphics.fillRect(n, n2, n3, n4);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        try {
            if (n3 > 0 && n4 > 0) {
                graphics.drawRect(n, n2, n3, n4);
            }
        }
        catch (Exception ex) {}
    }
    
    public final String[] az() {
        return this.u;
    }
    
    public final String[] a0() {
        return this.w;
    }
    
    public void a(final String s, final int n) {
        if (!this.bv) {
            this.bt();
            synchronized (this.z) {
                this.z.a = null;
            }
            // monitorexit(this.z)
            this.aa = null;
            this.ag = -1;
            this.ab = null;
            this.df = false;
            this.db.clear();
            this.bv = true;
        }
        synchronized (this.z) {
            if (this.z.a == null) {
                this.z.a = null;
                this.z.a = new String[this.ae()];
                this.z.a[n - 1] = s;
            }
        }
        // monitorexit(this.z)
        this.b3();
    }
    
    public void d(final String s) {
    }
    
    public final String[] a1() {
        synchronized (this.z) {
            // monitorexit(this.z)
            return this.z.a;
        }
    }
    
    public final String[] a2() {
        return this.ah;
    }
    
    public final Color[] a3() {
        return this.x;
    }
    
    public final Color[] a4() {
        return this.y;
    }
    
    public final void x(final boolean c6) {
        this.c6 = c6;
    }
    
    public final void a(final df df, final c c, final Object o, final boolean b) {
        try {
            if (this.aq != null) {
                this.aq.a(df, c, o, b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final df a5() {
        if (this.aq != null) {
            return this.aq.cj();
        }
        return null;
    }
    
    public final Object a6() {
        if (this.aq != null) {
            return this.aq.bp();
        }
        return null;
    }
    
    public final c a7() {
        if (this.aq != null) {
            return this.aq.bq();
        }
        return null;
    }
    
    public final void a8() {
        try {
            if (this.aq != null) {
                this.aq.br();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void y(final boolean b) {
        try {
            if (this.aq != null) {
                this.aq.bm(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a9() {
        try {
            if (this.aq != null) {
                this.aq.gq();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void e(final String ec) {
        this.ec = ec;
    }
    
    public final void a(final String[] array, final Color[] array2) {
        try {
            if (array2 != null) {
                this.x = new Color[array2.length];
                for (int i = 0; i < array2.length; ++i) {
                    try {
                        if (array2[i] != null) {
                            this.x[i] = new Color(array2[i].getRGB());
                        }
                        else {
                            this.x[i] = null;
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            else {
                this.x = null;
            }
            if (array != null) {
                this.u = new String[array.length];
                for (int j = 0; j < array.length; ++j) {
                    this.u[j] = array[j];
                    this.u[j] = ji.util.d.b(this.u[j], ji.util.d.p(), "\n");
                }
            }
            else {
                this.ca();
            }
            if (ji.util.d.em()) {
                this.aj = 3;
            }
            else if (ji.util.d.eg()) {
                this.aj = 2;
            }
            else {
                this.aj = 0;
            }
            this.repaint();
        }
        catch (Exception ex2) {}
    }
    
    public final void b(final String[] array, final Color[] array2) {
        try {
            if (array2 != null) {
                this.y = new Color[array2.length];
                for (int i = 0; i < array2.length; ++i) {
                    try {
                        if (array2[i] != null) {
                            this.y[i] = new Color(array2[i].getRGB());
                        }
                        else {
                            this.y[i] = null;
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            else {
                this.y = null;
            }
            if (array != null) {
                this.w = new String[array.length];
                for (int j = 0; j < array.length; ++j) {
                    this.w[j] = array[j];
                    this.w[j] = ji.util.d.b(this.w[j], ji.util.d.p(), "\n");
                }
            }
            else {
                this.cb();
            }
            if (ji.util.d.em()) {
                this.aj = 3;
            }
            else if (ji.util.d.eg()) {
                this.aj = 2;
            }
            else {
                this.aj = 0;
            }
            this.repaint();
        }
        catch (Exception ex2) {}
    }
    
    public final void a(final String[] array, final es ac) {
        try {
            synchronized (this) {
                this.dg = false;
                if (array != null) {
                    final int length = array.length;
                    int length2 = 0;
                    if (this.ac != null && this.ac.equals(ac) && this.ad != null) {
                        length2 = this.ad.length;
                    }
                    final String[] ad = new String[length2 + length];
                    final int[] ae = new int[length2 + length];
                    final long[] af = new long[length2 + length];
                    if (this.ac != null && this.ac.equals(ac)) {
                        if (this.ad != null) {
                            System.arraycopy(this.ad, 0, ad, 0, this.ad.length);
                        }
                        if (this.ae != null) {
                            System.arraycopy(this.ae, 0, ae, 0, this.ae.length);
                        }
                        if (this.af != null) {
                            System.arraycopy(this.af, 0, af, 0, this.af.length);
                        }
                    }
                    this.ac = (es)ac.d();
                    this.ad = ad;
                    this.ae = ae;
                    this.af = af;
                    final int length3 = "<#v1page36#>".length();
                    final int length4 = "<#v1id24#>".length();
                    for (int i = 0; i < length; ++i) {
                        final String s = array[i];
                        this.af[length2 + i] = -1L;
                        if (s != null) {
                            final int index = s.indexOf("<#v1page36#>");
                            if (index >= 0) {
                                try {
                                    final String substring = s.substring(index + length3);
                                    final int index2 = substring.indexOf("<#v1id24#>");
                                    if (index2 >= 0) {
                                        final String substring2 = substring.substring(index2 + length4);
                                        this.ae[length2 + i] = ji.util.d.c(substring.substring(0, index2), 1);
                                        this.af[length2 + i] = ji.util.d.a(substring2, 1L);
                                    }
                                    else {
                                        this.ae[length2 + i] = ji.util.d.c(substring, 1);
                                    }
                                    this.ad[length2 + i] = s.substring(0, index);
                                }
                                catch (Exception ex) {}
                            }
                            else {
                                this.ad[length2 + i] = s;
                                this.ae[length2 + i] = i + 1;
                            }
                        }
                    }
                }
                else {
                    boolean b = true;
                    if (this.ac != null && this.ac.equals(ac)) {
                        b = false;
                    }
                    this.ac = ac;
                    if (b) {
                        this.ad = null;
                        this.ae = null;
                        this.af = null;
                    }
                }
            }
            final Rectangle bounds = this.de.getComponent().getBounds();
            if (bounds != null) {
                this.de.getComponent().repaint(bounds.x + 10, bounds.y + 10, bounds.width - 20, bounds.height - 20);
            }
        }
        catch (Exception ex2) {}
    }
    
    public final boolean ba() {
        return this.ad != null && this.ad.length > 0;
    }
    
    public final void a(final String[] array) {
        try {
            if (array != null) {
                this.cc();
                final int length = array.length;
                synchronized (this.z) {
                    this.z.a = new String[length];
                    this.aa = new int[length];
                    this.ab = new long[length];
                    final int length2 = "<#v1page36#>".length();
                    final int length3 = "<#v1id24#>".length();
                    for (int i = 0; i < length; ++i) {
                        final String s = array[i];
                        this.ab[i] = -1L;
                        if (s != null) {
                            final int index = s.indexOf("<#v1page36#>");
                            if (index >= 0) {
                                try {
                                    final String substring = s.substring(index + length2);
                                    final int index2 = substring.indexOf("<#v1id24#>");
                                    if (index2 >= 0) {
                                        final String substring2 = substring.substring(index2 + length3);
                                        this.aa[i] = ji.util.d.c(substring.substring(0, index2), 1);
                                        this.ab[i] = ji.util.d.a(substring2, 1L);
                                    }
                                    else {
                                        this.aa[i] = ji.util.d.c(substring, 1);
                                    }
                                    this.z.a[i] = s.substring(0, index);
                                }
                                catch (Exception ex2) {}
                            }
                            else {
                                this.z.a[i] = s;
                                this.aa[i] = i + 1;
                            }
                        }
                    }
                }
                // monitorexit(this.z)
            }
            this.bx();
            this.repaint();
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void b(final String[] array) {
        try {
            if (array != null) {
                final int length = array.length;
                this.ah = new String[length];
                for (int i = 0; i < length; ++i) {
                    this.ah[i] = array[i];
                }
            }
            else {
                this.cd();
            }
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    private final void ca() {
        try {
            if (this.x != null) {
                for (int i = 0; i < this.x.length; ++i) {
                    this.x[i] = null;
                }
            }
            this.x = null;
            this.u = null;
            if (ji.util.d.eg()) {
                this.aj = 2;
            }
            else {
                this.aj = 0;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void cb() {
        try {
            if (this.y != null) {
                for (int i = 0; i < this.y.length; ++i) {
                    this.y[i] = null;
                }
            }
            this.y = null;
            this.w = null;
            if (ji.util.d.eg()) {
                this.aj = 2;
            }
            else {
                this.aj = 0;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void cc() {
        synchronized (this.z) {
            this.z.a = null;
        }
        // monitorexit(this.z)
        this.aa = null;
        this.ab = null;
        this.ag = -1;
        this.u();
    }
    
    public final void bb() {
        this.ad = null;
        this.ae = null;
        this.af = null;
        this.ct.aw(-1);
        this.v();
    }
    
    private final void cd() {
        this.ah = null;
        this.bn();
    }
    
    private final Rectangle b(final Graphics graphics, final int n) {
        if (graphics != null && this.d != null && this.a()) {
            final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
            final Point a;
            final Point point = a = this.d[n].a();
            a.x += this.bl.x;
            final Point point2 = point;
            point2.y += this.bl.y;
            this.av = String.valueOf(String.valueOf(new StringBuffer("").append(n + 1)));
            try {
                if (this.w != null && this.w.length > n && this.w[n] != null && !ji.util.d.by(ji.util.d.bc(this.w[n]))) {
                    this.av = ji.util.d.t(this.w[n], this.ec);
                }
            }
            catch (Exception ex) {}
            if (this.av != null) {
                final char[] charArray = this.av.toCharArray();
                String concat = "";
                int n2 = 0;
                final int height = graphics.getFontMetrics().getHeight();
                int n3 = height + this.aj;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '\n' || charArray[i] == '\r') {
                        n3 += height;
                        n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
                if (concat.length() > 0) {
                    n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                }
                rectangle.width = n2 + 3;
                rectangle.height = n3 + 1;
                rectangle.x = point.x + this.a - n2 - 7;
                rectangle.y = point.y + this.b - n3 - 4;
            }
            return rectangle;
        }
        return null;
    }
    
    private final Rectangle c(final Graphics graphics, final int n) {
        if (graphics != null && this.c != null && this.a()) {
            final Rectangle rectangle = new Rectangle(0, 0, 0, 0);
            final Point a;
            final Point point = a = this.c[n].a();
            a.x += this.bl.x;
            final Point point2 = point;
            point2.y += this.bl.y;
            this.au = String.valueOf(String.valueOf(new StringBuffer("").append(n + 1)));
            if (this.aq.r() != null && this.aq.r().bh == 1 && this.ba != null) {
                switch (this.ba[n].a) {
                    case 0: {
                        this.au = this.v(399);
                        break;
                    }
                    case 1: {
                        this.au = this.v(400);
                        break;
                    }
                    case 2: {
                        this.au = this.v(401);
                        break;
                    }
                    case 3: {
                        this.au = this.v(402);
                        break;
                    }
                }
            }
            else {
                try {
                    if (this.u != null && this.u.length > n && this.u[n] != null && !ji.util.d.by(ji.util.d.bc(this.u[n]))) {
                        this.au = ji.util.d.t(this.u[n], this.ec);
                    }
                }
                catch (Exception ex) {}
            }
            if (this.au != null) {
                final char[] charArray = this.au.toCharArray();
                String concat = "";
                int n2 = 0;
                final int height = graphics.getFontMetrics().getHeight();
                int n3 = height + this.aj;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '\n' || charArray[i] == '\r') {
                        n3 += height;
                        n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
                if (concat.length() > 0) {
                    n2 = Math.max(n2, graphics.getFontMetrics().stringWidth(concat));
                }
                rectangle.width = n2 + 3;
                rectangle.height = n3 + 1;
                rectangle.x = point.x + this.a - n2 - 7;
                rectangle.y = point.y + this.b - n3 - 4;
            }
            return rectangle;
        }
        return null;
    }
    
    private final void a(final Graphics graphics, final int n, final Rectangle rectangle, final Color[] array) {
        if (this.a() && graphics != null && rectangle != null) {
            graphics.setColor(this.getForeground());
            if (!ji.util.d.by(this.en)) {
                final char[] charArray = this.en.toCharArray();
                String concat = "";
                final int height = graphics.getFontMetrics().getHeight();
                final int n2 = height + this.aj;
                int max = 0;
                int n3 = 10 + rectangle.y + 4;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '\n' || charArray[i] == '\r') {
                        graphics.drawString(concat, rectangle.x + 3, n3);
                        n3 += height;
                        max = Math.max(max, graphics.getFontMetrics().stringWidth(concat));
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
                if (concat.length() > 0) {
                    graphics.drawString(concat, rectangle.x + 3, n3);
                }
            }
            this.b1();
        }
    }
    
    private final void a(final Graphics graphics, final int n, final Rectangle rectangle, final vz[] array, final String s, final Color[] array2) {
        if (this.a() && graphics != null && array != null && rectangle != null) {
            boolean b = false;
            if (!this.b() && this.cb != null && n < this.cb.length && this.cb[n]) {
                b = true;
            }
            if (b) {
                graphics.setColor(Color.yellow);
            }
            else {
                try {
                    if (array2 != null) {
                        if (array2.length > n) {
                            if (array2[n] != null) {
                                graphics.setColor(array2[n]);
                            }
                            else {
                                graphics.setColor(Color.white);
                            }
                        }
                        else {
                            graphics.setColor(Color.white);
                        }
                    }
                    else {
                        graphics.setColor(Color.white);
                    }
                }
                catch (Exception ex) {
                    graphics.setColor(Color.blue);
                }
            }
            this.a(graphics, rectangle.x + 2, rectangle.y + 1, rectangle.width + 1, rectangle.height);
            graphics.setColor(this.getForeground());
            if (!ji.util.d.by(s)) {
                final char[] charArray = s.toCharArray();
                String concat = "";
                final int height = graphics.getFontMetrics().getHeight();
                final int n2 = height + this.aj;
                int max = 0;
                int n3 = 10 + rectangle.y + 4;
                for (int i = 0; i < charArray.length; ++i) {
                    if (charArray[i] == '\n' || charArray[i] == '\r') {
                        graphics.drawString(concat, rectangle.x + 3, n3);
                        n3 += height;
                        max = Math.max(max, graphics.getFontMetrics().stringWidth(concat));
                        concat = "";
                    }
                    else {
                        concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[i])));
                    }
                }
                if (concat.length() > 0) {
                    graphics.drawString(concat, rectangle.x + 3, n3);
                }
            }
            graphics.setColor(SystemColor.controlShadow);
            this.b(graphics, rectangle.x + 1, rectangle.y + 1, rectangle.width + 1, rectangle.height);
            this.b1();
        }
    }
    
    private final boolean ab(final int n) {
        return !this.c6 || n + 1 == this.k || this.aq.x(n + 1);
    }
    
    private final v9 a(final int n, final boolean b, final cy cy, final vz[] array) {
        synchronized (this.eo) {
            final v9 v9 = new v9((aeb)null);
            if (this.a()) {
                try {
                    while (this.a7 && array[n].h == null) {
                        ji.util.d.b(10, 121, this.cu);
                    }
                    this.a7 = true;
                    if (this.bj == 0) {
                        cy.setBounds(0, 0, this.a, this.b);
                    }
                    else if (this.bj == 6) {
                        cy.setBounds(0, 0, this.a, this.b - 1);
                    }
                    else {
                        cy.setBounds(0, 0, this.a - this.a1, this.b - this.a1);
                    }
                    cy.a(this.ba);
                    cy.k(true);
                    if (!this.bq()) {
                        if (this.b8 == 6) {
                            cy.dt();
                        }
                        cy.d(n + 1, 2);
                    }
                    final Dimension dimension = new Dimension();
                    cy.l(true);
                    cy.q();
                    if (!this.bq()) {
                        if (cy.m() != this.b8) {
                            cy.b(this.b8);
                        }
                        final Image a = v9.a;
                        Image bd = null;
                        int n2 = 3;
                        while (bd == null && n2-- > 0) {
                            try {
                                bd = cy.bd(b);
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                            }
                        }
                        v9.a = bd;
                        if (a != null && a != bd && bd != null) {
                            bd.flush();
                        }
                        this.cd = cy.bu();
                    }
                    if (v9.a != null) {
                        v9.b = cy.ak();
                    }
                }
                catch (Exception ex2) {
                    ji.util.d.a(ex2);
                    if (v9.a != null) {
                        v9.a.flush();
                    }
                    v9.a = null;
                }
                finally {
                    this.a7 = false;
                }
            }
            // monitorexit(this.eo)
            return v9;
        }
    }
    
    public final boolean e(final boolean[] array) {
        boolean b = false;
        if (this.a()) {
            this.a(this.getParent(), this.c);
            try {
                if (array != null) {
                    if (array.length != this.c.length) {
                        b = true;
                    }
                    else {
                        Block_7: {
                            for (int i = 0; i < this.c.length; ++i) {
                                if (array[i] != this.c[i].b()) {
                                    break Block_7;
                                }
                            }
                            return b;
                        }
                        b = true;
                    }
                }
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public final boolean[] bc() {
        this.a(this.getParent(), this.c);
        final boolean[] array = new boolean[this.c.length];
        if (this.a()) {
            try {
                for (int i = 0; i < this.c.length; ++i) {
                    array[i] = this.c[i].b();
                }
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public final boolean bd() {
        if (this.b()) {
            return this.a(this.d, this.h);
        }
        return this.a(this.c, this.g);
    }
    
    public final boolean a(final vz[] array, final boolean[] array2) {
        boolean b = false;
        if (this.a()) {
            this.a(this.getParent(), array);
            try {
                Block_13: {
                    for (int i = 0; i < array.length; ++i) {
                        if (array[i].c() && !array[i].d() && array[i].b()) {
                            final Point a;
                            final Point point = a = array[i].a();
                            a.x += this.bl.x;
                            final Point point2 = point;
                            point2.y += this.bl.y;
                            int b2 = array[i].b() ? 1 : 0;
                            if (b2 != 0) {
                                if (this.contains(point.x, point.y) || this.contains(point.x + this.a, point.y) || this.contains(point.x, point.y + this.b) || this.contains(point.x + this.a, point.y + this.b)) {
                                    b2 = 1;
                                }
                                else if (!array2[i]) {
                                    b2 = 0;
                                }
                            }
                            if (b2 != 0) {
                                break Block_13;
                            }
                        }
                    }
                    return b;
                }
                b = true;
            }
            catch (Exception ex) {}
        }
        return b;
    }
    
    public final void a(final Component component, final vz[] array) {
        try {
            if (this.a() && array != null && component != null) {
                this.ds = 0;
                final int n = this.b() ? this.l : this.k;
                for (int i = 0; i < array.length; ++i) {
                    synchronized (array[i]) {
                        if (this.y(i)) {
                            try {
                                final Point a = array[i].a();
                                final Point point = new Point(a.x + this.bl.x - this.bm, a.y + this.bl.y - this.bm);
                                if (component.contains(point.x, point.y - this.bm) || component.contains(point.x + this.a - this.bm, point.y) || component.contains(point.x, point.y + this.b) || component.contains(point.x + this.a, point.y + this.b) || i == n - 1) {
                                    array[i].a(true);
                                    if (component.contains(point.x, point.y) || component.contains(point.x + this.a, point.y - this.bm) || component.contains(point.x, point.y + this.b) || component.contains(point.x + this.a, point.y + this.b)) {
                                        array[i].b(true);
                                        ++this.ds;
                                    }
                                    else {
                                        array[i].b(false);
                                        array[i].a(null, null, 0, 0);
                                        this.bg = false;
                                    }
                                }
                                else if (array[i].b()) {
                                    array[i].a(null, null, 0, 0);
                                    array[i].a(false);
                                    this.bg = false;
                                }
                            }
                            catch (Exception ex) {}
                        }
                    }
                    // monitorexit(array[i])
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public final boolean be() {
        boolean b = false;
        vz[] array;
        if (this.b()) {
            array = this.d;
        }
        else {
            array = this.c;
        }
        if (array != null && this.bs()) {
            if (this.a()) {
                boolean b2 = false;
                int n = -1;
                int n2 = -1;
                int n3 = -1;
                int n4 = -1;
                for (int i = 0; i < array.length; ++i) {
                    boolean b3 = true;
                    if (this.ea && !this.cb[i]) {
                        b3 = false;
                    }
                    if (b3) {
                        boolean b4 = false;
                        boolean b5 = false;
                        final int n5 = array[i].a().y + this.bl.y;
                        final int n6 = array[i].a().y + this.b + this.bl.y;
                        if (n5 < this.getSize().height && n5 > 0) {
                            b2 = true;
                        }
                        if (n5 <= this.bm) {
                            b4 = true;
                        }
                        if (n6 < this.getSize().height && n6 > 0) {
                            b2 = true;
                        }
                        if (n6 >= this.getSize().height - this.bm) {
                            b5 = true;
                        }
                        if (b2 && b4 && n < 0) {
                            n = i;
                            n2 = this.b + n5;
                        }
                        if (b2 && b5 && n3 < 0) {
                            n3 = i;
                            n4 = this.getSize().height - n5;
                        }
                        if (n >= 0 && n3 >= 0) {
                            break;
                        }
                    }
                }
                if (n >= 0 || n3 >= 0) {
                    if (n2 >= n4) {
                        b = this.s(n + 1);
                    }
                    else {
                        b = this.s(n3 + 1);
                    }
                }
            }
            else {
                b = this.s(this.ap());
            }
        }
        return b;
    }
    
    public final boolean q(final int n) {
        if (this.a()) {
            return this.a(n, this.d, this.h, this.f);
        }
        return this.t(n);
    }
    
    public final boolean r(final int n) {
        return this.da == 4 && this.t(n);
    }
    
    public final boolean s(int n) {
        final boolean b = false;
        if (this.c == null || !this.bs()) {
            return b;
        }
        if (this.ea && !this.cb[n - 1]) {
            for (int i = n; i < this.c.length; ++i) {
                if (this.cb[i]) {
                    n = i + 1;
                    break;
                }
            }
        }
        if (this.a()) {
            return this.a(n, this.c, this.g, this.e);
        }
        if (this.da == 4) {
            return this.t(this.dq);
        }
        return this.t(n);
    }
    
    public final boolean t(final int n) {
        if (!this.dj) {
            if (!this.b() && this.da == 1 && this.db != null) {
                this.db.makeVisible(n - 1);
                this.at = false;
            }
            else if (this.b() && this.da == 3 && this.dc != null) {
                this.dc.makeVisible(n - 1);
                this.at = false;
            }
        }
        return this.dj = false;
    }
    
    public final boolean a(final int n, final vz[] array, final boolean[] array2, final boolean[] array3) {
        boolean b = false;
        try {
            final boolean ae = this.bw.ae();
            try {
                this.bw.k(false);
                if (array != null && this.bs() && n > 0 && n <= array.length) {
                    if (this.b()) {
                        this.b(this.getParent());
                    }
                    else {
                        this.a(this.getParent());
                    }
                    final int n2 = array[n - 1].a().y + this.bl.y;
                    final int n3 = array[n - 1].a().y + this.b + this.bl.y;
                    switch (this.ap) {
                        case 5: {
                            final int n4 = array[n - 1].a().y - this.bm;
                            if (this.bw.a(-this.bl.x, n4)) {
                                this.bl.y = -n4;
                                this.a(this.getParent(), array);
                                if (!this.a9) {
                                    this.b3();
                                }
                                if (!this.a9) {
                                    this.a(array, array2, array3);
                                }
                                this.bg = !this.a(array, array2);
                                b = true;
                            }
                            this.a9 = false;
                            break;
                        }
                        case 0:
                        case 2:
                        case 4: {
                            if (n3 > this.getSize().height) {
                                final int n5 = -this.bl.y + (n3 - this.getSize().height) + this.bm;
                                if (this.bw.a(-this.bl.x, n5)) {
                                    this.bl.y = -n5;
                                    this.a(this.getParent(), array);
                                    if (!this.a9) {
                                        this.b3();
                                    }
                                    this.bg = !this.a(array, array2);
                                    b = true;
                                }
                            }
                            else if (n2 < 0) {
                                final int n6 = -this.bl.y + n2 - this.bm;
                                if (this.bw.a(-this.bl.x, n6)) {
                                    this.bl.y = -n6;
                                    this.a(this.getParent(), array);
                                    if (!this.a9) {
                                        this.b3();
                                    }
                                    this.bg = !this.a(array, array2);
                                    b = true;
                                }
                            }
                            this.a9 = false;
                            break;
                        }
                        case 1:
                        case 3: {
                            final int n7 = array[n - 1].a().x + this.bl.x;
                            final int n8 = array[n - 1].a().x + this.a + this.bl.x;
                            if (n8 > this.getSize().width) {
                                final int n9 = -this.bl.x + (n8 - this.getSize().width) + this.bm;
                                if (this.bw.a(n9, -this.bl.y)) {
                                    this.bl.x = -n9;
                                    this.a(this.getParent(), array);
                                    if (!this.a9) {
                                        this.b3();
                                    }
                                    this.bg = !this.a(array, array2);
                                    b = true;
                                }
                            }
                            else if (n7 < 0) {
                                final int n10 = -this.bl.x + n7 - this.bm;
                                if (this.bw.a(n10, -this.bl.y)) {
                                    this.bl.x = -n10;
                                    this.a(this.getParent(), array);
                                    if (!this.a9) {
                                        this.b3();
                                    }
                                    this.bg = !this.a(array, array2);
                                    b = true;
                                }
                            }
                            this.a9 = false;
                            break;
                        }
                    }
                }
            }
            finally {
                this.bw.k(ae);
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public final void a(final Component component) {
        try {
            final int r = this.r;
            final int ae = this.ae();
            if (ae > 0) {
                int n = this.bm;
                int bm = this.bm;
                final Dimension dimension = new Dimension(this.bl.width, this.bl.height);
                if (this.c == null) {
                    this.c = new vz[ae];
                    this.e = new boolean[ae];
                }
                else if (this.c.length != ae) {
                    final vz[] c = new vz[ae];
                    final boolean[] e = new boolean[ae];
                    for (int i = 0; i < this.c.length; ++i) {
                        if (i < c.length) {
                            c[i] = this.c[i];
                        }
                        if (i < e.length && i < this.e.length) {
                            e[i] = this.e[i];
                        }
                    }
                    this.c = c;
                    this.e = e;
                }
                if (this.g == null) {
                    this.g = new boolean[ae];
                }
                else if (this.g.length != ae) {
                    final boolean[] g = new boolean[ae];
                    for (int j = 0; j < this.g.length; ++j) {
                        if (j < g.length) {
                            g[j] = this.g[j];
                        }
                    }
                    this.g = g;
                }
                for (int k = 0; k < this.c.length; ++k) {
                    if (this.y(k)) {
                        if (this.c[k] == null) {
                            this.c[k] = new vz(n, bm);
                        }
                        else {
                            this.c[k].a(n, bm);
                        }
                        n += this.a + this.bm;
                        if (n + this.a + this.bm > dimension.width) {
                            n = this.bm;
                            bm = bm + this.b + this.bm;
                        }
                    }
                    else if (this.c[k] == null) {
                        this.c[k] = new vz(n, bm);
                    }
                }
                this.a(component, this.c);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b(final Component component) {
        try {
            if (this.s > 0) {
                int n = this.bm;
                int bm = this.bm;
                final Dimension dimension = new Dimension(this.bl.width, this.bl.height);
                if (this.d == null) {
                    this.d = new vz[this.s];
                    this.f = new boolean[this.s];
                }
                else if (this.d.length != this.s) {
                    this.d = new vz[this.s];
                    this.f = new boolean[this.s];
                }
                if (this.h == null) {
                    this.h = new boolean[this.s];
                }
                else if (this.h.length != this.s) {
                    this.h = new boolean[this.s];
                }
                for (int i = 0; i < this.d.length; ++i) {
                    if (this.d[i] == null) {
                        this.d[i] = new vz(n, bm);
                    }
                    else {
                        this.d[i].a(n, bm);
                    }
                    n += this.a + this.bm;
                    if (n + this.a + this.bm > dimension.width) {
                        n = this.bm;
                        bm = bm + this.b + this.bm;
                    }
                }
                this.a(component, this.d);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void u(final int ap) {
        if (this.ap != ap) {
            this.ap = ap;
            this.y();
            this.b0();
            this.k(false);
            this.j(false);
        }
    }
    
    public final int bf() {
        return this.ap;
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        final Rectangle bounds = this.getBounds();
        if (bounds.x != n || bounds.y != n2 || bounds.width != n3 || bounds.height != n4) {
            super.setBounds(n, n2, n3, n4);
            if (!this.bw.z()) {
                this.a(0, 0, this.c);
                this.be();
            }
            else {
                this.bw.af();
            }
            this.a(this.getParent(), this.c);
            this.b7();
            this.bg = false;
        }
    }
    
    private void ce() {
        if (this.db != null) {
            this.db.setVisible(false);
            this.remove(this.db.getComponent());
            this.db.clear();
            this.db.releaseResources();
            this.db = null;
        }
    }
    
    private void cf() {
        if (this.de != null) {
            this.de.setVisible(false);
            this.remove(this.de.getComponent());
            this.de.clear();
            this.de.releaseResources();
            this.de = null;
        }
    }
    
    private void cg() {
        if (this.dc != null) {
            this.dc.setVisible(false);
            this.dc.releaseResources();
            this.remove(this.dc.getComponent());
            this.dc.clear();
            this.dc = null;
        }
    }
    
    public void releaseResources() {
        if (!this.cc) {
            try {
                this.cc = true;
                this.ei = null;
                this.ct = null;
                try {
                    this.c(this.cu);
                    if (this.d5 != null) {
                        this.removeWheelListener(this.d5);
                        this.d5 = null;
                    }
                }
                catch (Exception ex) {}
                if (this.bd != null) {
                    this.bd.releaseResources();
                    this.bd = null;
                }
                if (this.bc != null) {
                    this.bc.releaseResources();
                    this.bc = null;
                }
                this.ee.removeMouseListener(this);
                this.ee.removeMouseMotionListener(this);
                this.ef.removeMouseListener(this);
                this.ef.removeMouseMotionListener(this);
                if (this.dw != null) {
                    this.dw.a(this);
                    this.dw.g();
                    this.dw = null;
                }
                if (this.dx != null) {
                    this.dx.a(this);
                    this.dx.g();
                    this.dx = null;
                }
                try {
                    if (this.dm != null) {
                        if (this.db != null) {
                            this.db.removeItemListener(this.dm);
                        }
                        this.dm = null;
                    }
                    this.bv = false;
                    this.ce();
                    if (this.do != null) {
                        if (this.dc != null) {
                            this.dc.removeItemListener(this.do);
                        }
                        this.do = null;
                    }
                    this.cg();
                    if (this.dn != null) {
                        if (this.de != null) {
                            this.de.removeItemListener(this.dn);
                        }
                        this.dn = null;
                    }
                    this.cf();
                }
                catch (Exception ex2) {}
                if (this.al != null) {
                    this.al.c();
                }
                if (this.an != null) {
                    this.an.c();
                }
                if (this.a3 != null) {
                    this.a3.c();
                }
                if (this.aq != null) {
                    this.aq.releaseResources();
                    this.aq = null;
                }
                if (this.as != null) {
                    this.as.releaseResources();
                    this.as = null;
                }
                this.ca();
                this.cb();
                try {
                    if (this.ee != null) {
                        this.ee.setVisible(false);
                        this.remove(this.ee);
                        this.ee.releaseResources();
                        this.ee = null;
                    }
                    if (this.ef != null) {
                        this.ef.setVisible(false);
                        this.remove(this.ef);
                        this.ef.releaseResources();
                        this.ef = null;
                    }
                }
                catch (Exception ex3) {}
                this.bw = null;
                super.releaseResources();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void finalize() {
        try {
            this.releaseResources();
            super.finalize();
        }
        catch (Exception ex) {}
    }
    
    public void z(final boolean b) {
        this.aq.cm(b);
        this.ab(true);
    }
    
    public void a(final fz fz) {
        final int selectedIndex = this.de.getSelectedIndex();
        final int rows = this.de.getRows();
        switch (fz.a()) {
            case 0: {
                this.de.select(0);
                break;
            }
            case 1: {
                if (selectedIndex > 0) {
                    this.de.select(selectedIndex - 1);
                    break;
                }
                break;
            }
            case 2: {
                if (selectedIndex < rows - 1) {
                    this.de.select(selectedIndex + 1);
                    break;
                }
                break;
            }
            case 3: {
                if (rows > 0) {
                    this.de.select(rows - 1);
                    break;
                }
                break;
            }
        }
    }
    
    public Vector f(final String s) {
        if (this.aq != null) {
            return this.aq.r(s);
        }
        return null;
    }
    
    static {
        c7.bx = 0;
        c7.cr = 0;
        c7.dl = new uz(null);
    }
    
    private class uw
    {
        private String[] a;
        
        private uw(final c7 c7) {
            this.a = null;
        }
    }
    
    class v5 implements Runnable
    {
        private c7 a;
        
        public v5(final c7 a) {
            this.a = a;
        }
        
        public final void run() {
            try {
                ji.document.c7.this.b1();
                ji.document.c7.this.b4 = true;
                ji.document.c7.this.aa(false);
                ji.document.c7.this.d7 = false;
                while (ji.document.c7.this.b4 && !ji.document.c7.this.bq()) {
                    this.a.ar();
                    this.a.u(false);
                    ji.document.c7.this.b4 = false;
                    if (ji.document.c7.this.b()) {
                        this.a(this.a, ji.document.c7.this.as, ji.document.c7.this.ef, ji.document.c7.this.b5(), ji.document.c7.this.f, ji.document.c7.this.w, ji.document.c7.this.h);
                    }
                    else {
                        this.a(this.a, ji.document.c7.this.aq, ji.document.c7.this.ee, ji.document.c7.this.b5(), ji.document.c7.this.e, ji.document.c7.this.u, ji.document.c7.this.g);
                    }
                    if (this.a.as() && this.a.bs()) {
                        ji.document.c7.this.b4 = true;
                    }
                    if (ji.document.c7.this.b4) {
                        ji.document.c7.this.aa(false);
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                if (ji.document.c7.this.bq()) {
                    ji.document.c7.this.bg = false;
                    if (ji.document.c7.this.b()) {
                        if (ji.document.c7.this.ck) {
                            ji.document.c7.this.j(false);
                        }
                    }
                    else if (ji.document.c7.this.ck) {
                        ji.document.c7.this.k(false);
                    }
                    ji.document.c7.this.ck = true;
                }
                ji.document.c7.this.aw = null;
                --ji.document.c7.bx;
                try {
                    if (!ji.document.c7.this.bq() && ji.document.c7.this.bw != null && ji.document.c7.this.bw.d.getValue() != -ji.document.c7.this.bl.y && ji.document.c7.this.bw.d.isVisible()) {
                        if (ji.document.c7.this.b()) {
                            ji.document.c7.this.a(ji.document.c7.this.bl.x, -ji.document.c7.this.bw.d.getValue(), ji.document.c7.this.d);
                        }
                        else {
                            ji.document.c7.this.a(ji.document.c7.this.bl.x, -ji.document.c7.this.bw.d.getValue(), ji.document.c7.this.c);
                        }
                    }
                }
                catch (Exception ex2) {}
                if (ji.document.c7.this.bq() && !ji.document.c7.this.d7) {
                    ji.document.c7.this.aa(false);
                    ji.document.c7.this.d7 = true;
                    ji.document.c7.this.a(new d7(this, 8, 0));
                }
                ji.document.c7.this.aa(false);
            }
        }
        
        private void a(final c7 c7, final cy cy, final uy uy, final vz[] array, final boolean[] array2, final String[] array3, final boolean[] array4) {
            int max = 0;
            int n = c7.this.ae();
            if (array != null) {
                boolean b = false;
                boolean b2 = false;
                boolean d = false;
                c7.this.bh = true;
                try {
                    c7.this.b1();
                    ji.util.d.b(10, 122, c7.this.cu);
                    c7.this.a(cy);
                    c7.this.a(c7.this.getParent(), array);
                    final Graphics graphics = c7.this.getGraphics();
                    c7.this.b3();
                    ji.util.d.b(100, 123, c7.this.cu);
                    int n2 = 0;
                    cy.au(false);
                    for (int n3 = 0; n3 < array.length && !c7.this.bq() && c7.isVisible() && c7.this.br() && !c7.as(); ++n3) {
                        if (cy.bb()) {
                            break;
                        }
                        try {
                            final Point a;
                            final Point point = a = array[n3].a();
                            a.x += c7.this.bl.x;
                            final Point point2 = point;
                            point2.y += c7.this.bl.y;
                            int b3 = array[n3].b() ? 1 : 0;
                            if (b3 != 0) {
                                if (c7.contains(point.x, point.y) || c7.contains(point.x + c7.this.a, point.y) || c7.contains(point.x, point.y + c7.this.b) || c7.contains(point.x + c7.this.a, point.y + c7.this.b)) {
                                    b3 = 1;
                                }
                                else {
                                    array[n3].b(false);
                                    if (!array4[n3]) {
                                        b3 = 0;
                                    }
                                }
                            }
                            int n4 = (b3 != 0 && array[n3].c() && c7.this.br()) ? 1 : 0;
                            if (n4 != 0 && !c7.this.y(n3)) {
                                n4 = 0;
                            }
                            Label_1138: {
                                if (n4 != 0) {
                                    Image g = null;
                                    synchronized (array[n3]) {
                                        d = array[n3].d();
                                        g = array[n3].g();
                                    }
                                    // monitorexit(array[n3])
                                    if (!c7.this.dv) {
                                        c7.this.dv = true;
                                        if (c7.this.b8 == 5 || c7.this.b8 == 6) {
                                            c7.this.a(new d7(this, 49, c7.this.ap()));
                                        }
                                    }
                                    if (cy.bb()) {
                                        break;
                                    }
                                    if (d && g != null && !array2[n3]) {
                                        array2[n3] = false;
                                        array4[n3] = true;
                                        try {
                                            c7.this.a(graphics, n3, true, uy, array);
                                        }
                                        catch (Exception ex3) {}
                                    }
                                    else {
                                        c7.this.cd = false;
                                        cy.bx();
                                        if (cy.bb()) {
                                            break;
                                        }
                                        if (c7.this.ab(n3)) {
                                            final v9 a2 = c7.this.a(n3, array2[n3], cy, array);
                                            if (a2 != null) {
                                                b2 = a2.b;
                                            }
                                            c7.this.aa(c7.this.bq() | cy.by());
                                            if (c7.this.ds > 0) {
                                                c7.this.w(100 * n2 / c7.this.ds);
                                            }
                                            ++n2;
                                            array2[n3] = false;
                                            if (cy.bb()) {
                                                break;
                                            }
                                            if (a2.a != null && !c7.this.bq() && c7.this.br() && !cy.by()) {
                                                n = Math.min(n, n3 + 1);
                                                max = Math.max(max, n3 + 1);
                                                synchronized (array[n3]) {
                                                    array[n3].a(c7, a2.a, a2.a.getWidth(null), a2.a.getHeight(null));
                                                    array[n3].c(true);
                                                    array[n3].e(false);
                                                    array[n3].d(b2);
                                                }
                                                // monitorexit(array[n3])
                                                if (c7.this.b()) {
                                                    c7.this.bu = false;
                                                }
                                                else {
                                                    c7.this.bt = false;
                                                }
                                                array4[n3] = true;
                                                if (!c7.this.bq()) {
                                                    if (c7.this.b()) {
                                                        c7.this.b(graphics, n3);
                                                    }
                                                    else {
                                                        c7.this.c(graphics, n3);
                                                    }
                                                    if (!c7.this.cd) {
                                                        c7.this.g(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(c7.this.b7))).append("(").append(n3 + 1).append(")..."))));
                                                    }
                                                    try {
                                                        c7.this.a(graphics, n3, true, uy, array);
                                                    }
                                                    catch (Exception ex4) {}
                                                    b = true;
                                                }
                                            }
                                        }
                                        else {
                                            if (cy.bb()) {
                                                break;
                                            }
                                            if (c7.this.c6) {
                                                array[n3].e(true);
                                                try {
                                                    c7.this.a(graphics, n3, true, uy, array);
                                                }
                                                catch (Exception ex5) {}
                                            }
                                        }
                                    }
                                    if (!c7.this.as()) {
                                        if (!cy.by()) {
                                            break Label_1138;
                                        }
                                    }
                                    try {
                                        if (!d) {
                                            array[n3].c(false);
                                            array[n3].a(null, null, 0, 0);
                                            cy.bw();
                                        }
                                    }
                                    catch (Exception ex6) {}
                                }
                            }
                            if (c7.this.b()) {
                                if (n3 == c7.this.l - 1) {
                                    c7.this.z(5);
                                }
                                else if (n3 == c7.this.c8 - 1) {
                                    c7.this.aa(5);
                                }
                            }
                        }
                        catch (Exception ex) {
                            ji.util.d.b(ex);
                        }
                    }
                    if (c7.this.b()) {
                        c7.this.z(6);
                    }
                    else {
                        c7.this.aa(6);
                    }
                }
                catch (Exception ex2) {
                    ji.util.d.b(ex2);
                }
                finally {
                    c7.this.g("");
                    c7.this.w(0);
                    c7.this.bh = false;
                }
                if (!c7.this.bq()) {
                    c7.this.bg = b;
                    c7.this.cg = b;
                }
                c7.this.repaint();
                if (!c7.this.bq()) {
                    c7.this.d7 = true;
                    c7.this.a(new d7(this, 8, 0));
                    c7.this.a(new d7(this, 66, n, max));
                }
                c7.this.bz();
            }
        }
    }
    
    private class v9
    {
        public Image a;
        public boolean b;
        
        private v9(final c7 c7) {
            this.b = false;
        }
    }
    
    class vz
    {
        int a;
        int b;
        boolean c;
        boolean d;
        boolean e;
        boolean f;
        boolean g;
        Image h;
        
        public vz(final c7 c7, final int a, final int b) {
            this.a = 0;
            this.b = 0;
            this.c = false;
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = null;
            this.a = a;
            this.b = b;
        }
        
        public final void a(final int a, final int b) {
            this.a = a;
            this.b = b;
        }
        
        public final Point a() {
            return new Point(this.a, this.b);
        }
        
        public final void a(final boolean c) {
            this.c = c;
        }
        
        public final boolean b() {
            return this.c;
        }
        
        public final void b(final boolean d) {
            this.d = d;
        }
        
        public final boolean c() {
            return this.d;
        }
        
        public final void c(final boolean e) {
            if (!(this.e = e)) {
                this.f = false;
            }
        }
        
        public final boolean d() {
            return this.e && this.h != null;
        }
        
        public final void d(final boolean f) {
            this.f = f;
        }
        
        public final boolean e() {
            return this.f;
        }
        
        public final void e(final boolean g) {
            this.g = g;
        }
        
        public final boolean f() {
            return this.g;
        }
        
        public final void a(final Component component, final Image h, final int n, final int n2) {
            if (this.h != null && this.h != h) {
                this.h.flush();
            }
            this.h = h;
        }
        
        public final Image g() {
            return this.h;
        }
    }
    
    class wp implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getID() == 701) {
                final int n = ji.document.c7.this.dc.getSelectedIndex() + 1;
                if (n >= 0 && n <= ji.document.c7.this.ai.length && ji.document.c7.this.l != n) {
                    ji.document.c7.this.a(new a3(itemEvent.getSource(), 20, n - 1, "set doc", ji.util.d.e0()));
                    ji.document.c7.this.l = n;
                }
            }
        }
    }
    
    class wo implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            int selectedIndex = 0;
            if (itemEvent.getID() == 701 && ji.document.c7.this.de != null) {
                if (itemEvent.getStateChange() == 2) {
                    return;
                }
                long n = -1L;
                final int[] g = ji.document.c7.this.ae;
                final long[] i = ji.document.c7.this.af;
                if (g != null) {
                    selectedIndex = ji.document.c7.this.de.getSelectedIndex();
                    ji.document.c7.this.ct.aw(selectedIndex);
                    if (i != null) {
                        n = i[selectedIndex];
                    }
                }
                else {
                    ji.document.c7.this.ct.aw(-1);
                }
                if (n >= 0) {
                    ji.document.c7.this.dj = true;
                    ji.document.c7.this.de.makeVisible(selectedIndex);
                    if (!ji.document.c7.this.de.isAdjustingValue()) {
                        ji.document.c7.this.a(new fv(itemEvent.getSource(), 10, n, 0));
                    }
                    if (ji.document.c7.this.db != null) {
                        ji.document.c7.this.db.select(-1);
                    }
                }
            }
        }
    }
    
    class wn implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getID() == 701) {
                long n = -1L;
                final int ap = ji.document.c7.this.ap();
                final int[] b = ji.document.c7.this.aa;
                final long[] c = ji.document.c7.this.ab;
                int n2;
                if (b != null) {
                    ji.document.c7.this.ag = ji.document.c7.this.db.getSelectedIndex();
                    n2 = b[ji.document.c7.this.ag];
                    if (c != null) {
                        n = c[ji.document.c7.this.ag];
                    }
                }
                else {
                    ji.document.c7.this.ag = -1;
                    n2 = ji.document.c7.this.db.getSelectedIndex() + 1;
                }
                if (n >= 0) {
                    ji.document.c7.this.dj = true;
                    ji.document.c7.this.a(new fv(itemEvent.getSource(), 10, n, 0));
                }
                else if (n2 > 0 && n2 <= ji.document.c7.this.ae() && ap != n2) {
                    ji.document.c7.this.dj = true;
                    ji.document.c7.this.db.makeVisible(n2 - 1);
                    if (!ji.document.c7.this.db.isAdjustingValue()) {
                        ji.document.c7.this.a(new fv(itemEvent.getSource(), 8, n2, 0));
                    }
                }
            }
        }
    }
    
    private class aao implements Runnable
    {
        MouseEvent a;
        
        public aao(final MouseEvent a) {
            this.a = null;
            this.a = a;
        }
        
        public final void run() {
            try {
                ji.util.d.b(100, 115, ji.document.c7.this.cu);
                if (!ji.document.c7.this.cl) {
                    ji.document.c7.this.e(this.a);
                }
            }
            catch (Exception ex) {}
            finally {
                this.a = null;
                ji.document.c7.this.cx = null;
                ji.document.c7.this.cl = false;
            }
        }
    }
    
    class aan implements Runnable
    {
        MouseEvent a;
        
        aan() {
            this.a = null;
        }
        
        public void a(final MouseEvent a) {
            this.a = a;
        }
        
        public void run() {
            if (ji.document.c7.this.ch) {
                try {
                    ji.util.d.b((int)ji.util.d.aw() + 10, 113, ji.document.c7.this.cu);
                    boolean b;
                    if (ji.document.c7.this.b()) {
                        b = ji.document.c7.this.b(this.a);
                    }
                    else {
                        b = ji.document.c7.this.c(this.a);
                    }
                    if (b) {
                        ji.document.c7.this.e(this.a);
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    ji.document.c7.this.cv = null;
                }
            }
        }
    }
    
    class xg implements as
    {
        public void mouseWheelMoved(final g9 g9) {
            if (ji.document.c7.this.bw.z()) {
                ji.document.c7.this.bw.d.a(g9);
            }
        }
    }
    
    class uy extends bz
    {
        public uy(final String s) {
            super(s);
            this.setBorderStyle(0);
            this.a(false);
        }
        
        public void releaseResources() {
            super.releaseResources();
        }
        
        public final void update(final Graphics graphics) {
            if (graphics != null) {
                if (this.a()) {
                    super.update(graphics);
                }
                else {
                    this.paintComponent(graphics);
                }
            }
        }
        
        public void paint(final Graphics graphics) {
            ji.document.c7.this.av();
            if (this.a()) {
                super.paint(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
        
        public void setVisible(final boolean visible) {
            super.setVisible(visible);
        }
        
        public void paintComponent(final Graphics graphics) {
            ji.document.c7.this.a(graphics);
        }
    }
    
    final class ux
    {
        public boolean a;
        
        ux(final c7 c7) {
            this.a = false;
        }
    }
    
    private static class uz
    {
        private boolean a;
        
        private uz() {
            this.a = false;
        }
    }
    
    interface aeb
    {
    }
}
