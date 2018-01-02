// 
// Decompiled by Procyon v0.5.30
// 

package ji.document;

import ji.image.jiJ2Interface;
import java.io.OutputStream;
import ji.graphic.jiImageButton;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.Frame;
import java.awt.PrintJob;
import ji.sec.f;
import ji.clip.gz;
import java.awt.Insets;
import ji.v1event.ob;
import ji.v1event.a9;
import java.util.Enumeration;
import ji.awt.ax;
import ji.wang.ej;
import ji.annotate.b8;
import ji.burn.jiBurnerListener;
import ji.font.ct;
import ji.annotate.gj;
import ji.image.ev;
import ji.annotate.df;
import ji.annotate.dg;
import ji.filter.r9;
import ji.image.ds;
import ji.filter.ew;
import ji.filter.fx;
import ji.res.ay;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import ji.v1event.c9;
import java.util.EventObject;
import java.io.IOException;
import ji.v1event.a6;
import ji.util.bh;
import java.util.Properties;
import ji.v1event.ao;
import ji.v1event.fz;
import ji.image.dv;
import ji.image.em;
import ji.adjustment.fi;
import ji.adjustment.eh;
import java.awt.Color;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import ji.v1event.a3;
import ji.v1event.d7;
import java.awt.Point;
import java.awt.Image;
import java.net.URL;
import ji.util.i;
import java.awt.Cursor;
import java.awt.LayoutManager;
import ji.v1event.b;
import java.awt.Component;
import ji.util.e;
import ji.io.h;
import ji.util.d;
import java.awt.Dimension;
import ji.ext.v;
import ji.v1event.a2;
import ji.io.p;
import ji.v1event.fv;
import ji.image.dx;
import ji.ext.fo;
import ji.util.jiPrinti;
import ji.awt.bb;
import ji.io.at;
import ji.io.q;
import ji.graphic.dq;
import java.awt.Rectangle;
import ji.image.cy;
import ji.net.a0;
import ji.awt.c;
import ji.filter.ck;
import ji.io.ac;
import ji.image.o7;
import ji.v1event.cx;
import ji.v1event.ak;
import java.awt.event.ActionListener;
import ji.v1event.ah;
import ji.v1event.ag;
import ji.v1event.ae;
import ji.v1event.af;
import ji.v1base.jiPanel;

public class cw extends jiPanel implements af, ae, ag, ah, ActionListener, ak, cx
{
    public ub a;
    private int b;
    public Object c;
    private o7[] d;
    public ac e;
    public ac f;
    public ck g;
    public ck h;
    public ck i;
    public static ac j;
    public c k;
    public a0 l;
    public a0 m;
    public boolean n;
    public cy o;
    public cy p;
    public boolean q;
    public boolean r;
    public Object[] s;
    public Object[] t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;
    private Rectangle aa;
    private int ab;
    private int ac;
    private c ad;
    private int ae;
    private int af;
    private cy ag;
    private c6 ah;
    private di ai;
    private dj aj;
    private dq ak;
    private int al;
    private c am;
    private c an;
    private c ao;
    private boolean ap;
    private boolean aq;
    private int ar;
    private q as;
    private boolean at;
    private boolean au;
    private boolean av;
    private static int aw;
    private boolean ax;
    private boolean ay;
    private boolean az;
    private boolean a0;
    private boolean a1;
    private boolean a2;
    private boolean a3;
    private String a4;
    private boolean a5;
    private static at a6;
    private boolean a7;
    private boolean a8;
    private bb a9;
    private wc ba;
    private boolean bb;
    private boolean bc;
    private boolean bd;
    private boolean be;
    private boolean bf;
    private boolean bg;
    private boolean bh;
    private boolean bi;
    private boolean bj;
    private ad bk;
    private acn bl;
    private acm bm;
    private static Object bn;
    private jiPrinti bo;
    private boolean bp;
    private boolean bq;
    private int br;
    private boolean bs;
    private Object bt;
    private Object[] bu;
    private String bv;
    private Object bw;
    private String bx;
    private static we by;
    private static we bz;
    private boolean b0;
    private boolean b1;
    private boolean b2;
    private boolean b3;
    private boolean b4;
    private boolean b5;
    private boolean b6;
    private boolean b7;
    private fo b8;
    private int b9;
    private int ca;
    private int cb;
    private int cc;
    private boolean cd;
    private boolean ce;
    private String cf;
    private String cg;
    private boolean ch;
    private boolean ci;
    private boolean cj;
    private String ck;
    private bb cl;
    private Thread cm;
    private boolean cn;
    private int co;
    public String cp;
    public String cq;
    private static int cr;
    private z3 cs;
    private boolean ct;
    private int cu;
    private ac cv;
    private String cw;
    private dx cx;
    private boolean cy;
    private ck cz;
    private c c0;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private boolean c5;
    private boolean c6;
    private boolean c7;
    private boolean c8;
    private boolean c9;
    private boolean da;
    private boolean db;
    private boolean dc;
    private boolean dd;
    private boolean de;
    private z5 df;
    private c dg;
    private int dh;
    private int di;
    private int dj;
    private int dk;
    private int dl;
    private fv dm;
    private p dn;
    private a2 do;
    private boolean dp;
    private boolean dq;
    private boolean dr;
    private boolean ds;
    private boolean dt;
    private boolean du;
    private v dv;
    private Dimension dw;
    private String dx;
    private bb dy;
    final Object dz;
    private int d0;
    private boolean d1;
    private int d2;
    private boolean d3;
    private boolean d4;
    private boolean d5;
    private c d6;
    private String d7;
    private ck d8;
    private ck d9;
    private ck ea;
    private dx eb;
    private dx ec;
    private dx ed;
    private boolean ee;
    private boolean ef;
    private boolean[] eg;
    private String eh;
    private String ei;
    String ej;
    Object ek;
    Object el;
    Object[] em;
    Object[] en;
    String eo;
    boolean ep;
    cy eq;
    boolean er;
    
    public cw(final boolean ax, final ad bk, final String cp, final String cq) {
        super(cp);
        this.a = new ub((ad9)null);
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.o = null;
        this.p = null;
        this.q = false;
        this.r = false;
        this.s = null;
        this.t = null;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = true;
        this.y = false;
        this.z = false;
        this.aa = new Rectangle(0, 0, 0, 0);
        this.ae = 30;
        this.af = 30;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = -1;
        this.ap = false;
        this.aq = false;
        this.ar = -1;
        this.as = null;
        this.at = false;
        this.au = false;
        this.av = false;
        this.ax = false;
        this.ay = false;
        this.az = false;
        this.a0 = false;
        this.a1 = false;
        this.a2 = false;
        this.a3 = false;
        this.a4 = null;
        this.a5 = false;
        this.a7 = false;
        this.a8 = false;
        this.a9 = null;
        this.ba = null;
        this.bb = false;
        this.bc = false;
        this.bd = false;
        this.be = false;
        this.bf = false;
        this.bg = false;
        this.bh = false;
        this.bi = false;
        this.bj = false;
        this.bk = null;
        this.bl = null;
        this.bm = null;
        this.bo = null;
        this.bp = false;
        this.bq = false;
        this.br = 1;
        this.bs = false;
        this.bt = null;
        this.bu = null;
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.b0 = false;
        this.b1 = false;
        this.b2 = false;
        this.b3 = false;
        this.b4 = true;
        this.b5 = false;
        this.b6 = false;
        this.b7 = false;
        this.b8 = null;
        this.b9 = 0;
        this.ca = 0;
        this.cb = 0;
        this.cc = 255;
        this.cd = false;
        this.ce = true;
        this.cf = null;
        this.cg = null;
        this.ch = true;
        this.ci = true;
        this.cj = false;
        this.ck = null;
        this.cl = null;
        this.cm = null;
        this.cn = false;
        this.co = 0;
        this.cp = null;
        this.cq = null;
        this.cs = null;
        this.ct = true;
        this.cu = 0;
        this.cv = null;
        this.cw = null;
        this.cx = null;
        this.cy = false;
        this.cz = null;
        this.c0 = null;
        this.c1 = null;
        this.c2 = null;
        this.c3 = null;
        this.c4 = null;
        this.c5 = false;
        this.c6 = false;
        this.c7 = false;
        this.c8 = false;
        this.c9 = false;
        this.da = true;
        this.db = false;
        this.dc = false;
        this.dd = false;
        this.de = false;
        this.df = null;
        this.dg = null;
        this.dh = -1;
        this.di = -1;
        this.dj = 10;
        this.dk = 0;
        this.dl = 0;
        this.dm = null;
        this.dn = null;
        this.do = null;
        this.dp = false;
        this.dq = false;
        this.dr = false;
        this.ds = false;
        this.dt = false;
        this.du = false;
        this.dv = null;
        this.dw = null;
        this.dx = null;
        this.dy = null;
        this.dz = new Object();
        this.d0 = 0;
        this.d1 = false;
        this.d2 = 0;
        this.d3 = false;
        this.d4 = false;
        this.d5 = true;
        this.d6 = null;
        this.d7 = null;
        this.d8 = null;
        this.d9 = null;
        this.ea = null;
        this.eb = null;
        this.ec = null;
        this.ed = null;
        this.ee = true;
        this.ef = true;
        this.eg = null;
        this.eh = null;
        this.ei = null;
        this.ej = null;
        this.ek = null;
        this.el = null;
        this.em = null;
        this.en = null;
        this.eo = null;
        this.ep = false;
        this.eq = null;
        this.er = false;
        if (ji.util.d.dv()) {
            ji.io.h.e(cp, "jiDocH1");
        }
        this.bk = bk;
        this.cp = cp;
        this.cq = cq;
        this.setAllowClearInside(false);
        (this.ag = new cy(bk, this, cp, this.cu, false, false)).cr(this.d1);
        if (ji.util.d.dv()) {
            ji.io.h.e(cp, "jiDocH11");
        }
        if (!ji.util.d.b()) {
            (this.ai = new di(cp, bk)).a(this.d1);
            this.ai.b(bk);
            (this.aj = new dj(cp, bk)).a(this.d1);
            this.aj.b(bk);
            this.aj.a((cx)this);
        }
        (this.ak = new dq(cp, bk)).b(this);
        (this.ah = new c6(bk, this, cp)).u(this.d1);
        if (ji.util.d.dv()) {
            ji.io.h.e(cp, "jiDocH2");
        }
        try {
            this.ax = ax;
            this.g(bk);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(cp, "jiDocH3");
        }
    }
    
    public void a(final Object[] array, final boolean b) {
        if (this.ah != null) {
            this.ah.a(array, b);
        }
    }
    
    public Object[] a() {
        if (this.ah != null) {
            return this.ah.a();
        }
        return null;
    }
    
    public final void a(final boolean d1) {
        this.d1 = d1;
        if (this.ag != null) {
            this.ag.cr(d1);
        }
        if (this.ah != null) {
            this.ah.u(d1);
        }
        if (this.ai != null) {
            this.ai.a(d1);
        }
    }
    
    public final void b(final boolean bc) {
        try {
            this.bc = bc;
            if (this.ah != null) {
                this.ah.a(bc);
            }
            if (this.ag != null) {
                this.ag.a(bc);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void g(final Object o) throws Exception {
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb1");
        }
        if (ji.document.cw.j == null) {
            ji.document.cw.j = new ac(o, this.cp);
        }
        ++ji.document.cw.aw;
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb2");
        }
        this.setAcceptFocus(false);
        ji.util.d.b4(this.ax);
        ji.util.e.a(this);
        ji.util.e.a(this.ag);
        this.setBorderStyle(6);
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb3");
        }
        try {
            this.h(o);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.ag.b((af)this);
        this.ag.b((ag)this);
        this.ag.b((ah)this);
        this.ag.a((ae)this);
        this.ag.b((ActionListener)this);
        this.ag.c0(true);
        if (this.ah != null) {
            this.ah.b((ag)this);
            this.ah.b((af)this);
            this.ah.b((ae)this);
            this.ah.b((ActionListener)this);
            this.ah.b((b)this.bk);
            this.ah.b((ActionListener)this.bk);
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb4");
        }
        this.setLayout(null);
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb41");
        }
        this.ag.setVisible(false);
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb42");
        }
        if (this.ah != null) {
            this.ah.setVisible(false);
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb5");
        }
        this.add(this.ag);
        if (this.ah != null) {
            this.add(this.ah);
        }
        this.fg();
        this.bo(false);
        this.fe();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb6");
        }
        this.af(0);
        this.setCursor(new Cursor(0));
        this.ar = this.ag.z();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb7");
        }
        (this.do = new a2("jiDocHandlerEvents", this.cp)).b(this);
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "jiDocH - jb8");
        }
    }
    
    public final void b() {
        this.ag.setVisible(true);
    }
    
    public final void c() throws Exception {
        try {
            if (this.ag != null) {
                this.ag.d(true);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final boolean b) {
        if (this.ag != null) {
            this.ag.cm(b);
        }
        if (this.ah != null) {
            this.ah.aa(b);
        }
    }
    
    public final void d() {
        try {
            if (this.d5 && !ji.util.i.c(110)) {
                if (!ji.util.e.j()) {
                    if (!ji.util.e.h(this.cp)) {
                        return;
                    }
                }
                try {
                    ji.io.ac.a(this.bk, this.cp, ji.util.i.c(69), this.c);
                    if (!ji.util.i.c(69)) {
                        if (this.l == null) {
                            (this.l = new a0(this.bk, this.cp)).b(this);
                        }
                        this.l.a(this.bk.c(), this, this.cp);
                    }
                }
                catch (Exception ex) {}
            }
        }
        catch (Exception ex2) {}
    }
    
    public final void e() {
        try {
            if (!ji.util.i.c(110)) {
                ji.io.ac.a(this.bk, this.cp, ji.util.i.c(69), this.c);
                if (this.l == null) {
                    (this.l = new a0(this.bk, this.cp)).b(this);
                }
                this.l.a(this.bk.c(), this, this.cp);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Object o, final int n, final Object[] array, final Object o2, final Object[] array2, final ad ad, final boolean b, final int n2, final boolean b2) throws Exception {
        if (this.cd) {
            return;
        }
        this.bp = false;
        this.a3 = true;
        this.ag.l(true);
        this.h(ad);
        if (!ad.c() && !ji.util.i.c(110)) {
            this.d();
        }
        this.b(o, n, array, o2, array2, ad, b, n2, b2);
        ad.g7();
        this.ap = true;
        this.er = true;
        this.af(this.al);
        this.fp();
    }
    
    public void requestFocus() {
        try {
            if (ji.util.d.ck(this.cp)) {
                return;
            }
            if (this.al == 5 || this.al == 6) {
                if (ji.util.i.c(102)) {
                    ji.io.h.d(this.cp, "Thumbs: request focus");
                }
                ji.util.e.b(this.ah);
            }
            else {
                if (ji.util.i.c(102)) {
                    ji.io.h.d(this.cp, "Fullpage: request focus");
                }
                ji.util.e.b(this.ag);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final Object f() {
        return this.c;
    }
    
    public final boolean g() {
        if (this.bk.cj()) {
            try {
                boolean b = false;
                final ck am = this.am();
                if (am != null) {
                    b = am.a();
                }
                if (!b && this.j()) {
                    b = this.d3;
                }
                return b;
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
        return false;
    }
    
    public final boolean h() {
        return this.j() && this.d3;
    }
    
    public final Object[] i() {
        return this.s;
    }
    
    public final boolean j() {
        return this.d2 > 0;
    }
    
    public final URL a(final es es, final boolean b) {
        try {
            if (this.ag != null) {
                return this.ag.a(es, b);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return null;
    }
    
    public final Object a(final int n, final boolean b) {
        try {
            return this.ag.c(n, b);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public String a(final int n) {
        try {
            if (this.j()) {
                Object a = this.a(n + 1, false);
                if (a == null) {
                    a = "Unknown";
                }
                return "".concat(String.valueOf(String.valueOf(a)));
            }
            if (this.s != null) {
                if (ji.util.i.c(137) && (n < 0 || n + 1 > this.s.length)) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Error: PageURL for Page: ").append(n + 1).append(", max: ").append(this.s.length))));
                }
                return "".concat(String.valueOf(String.valueOf(this.s[n])));
            }
            return "".concat(String.valueOf(String.valueOf(this.c)));
        }
        catch (Exception ex) {
            if (ji.util.i.c(137)) {
                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Exception: PageURL(").append(n).append("): ").append(ex.getMessage()))));
            }
            ji.util.d.a(ex);
            return "Unknown";
        }
    }
    
    public final Object k() {
        return this.bt;
    }
    
    public final Object[] l() {
        return this.bu;
    }
    
    public void m() {
        try {
            this.cn = true;
            try {
                if (this.l != null && !this.dc) {
                    this.l.d();
                }
            }
            catch (Exception ex) {}
            if (!this.dc) {
                this.ag.gk();
            }
            if (!this.dc) {
                this.ah.a3();
            }
        }
        catch (Exception ex2) {}
    }
    
    public void n() {
        try {
            if (this.cn && this.l != null) {
                this.l.e();
            }
            this.cn = false;
            this.ag.gl();
            this.ah.a4();
        }
        catch (Exception ex) {}
    }
    
    public final void o() {
        try {
            this.ag.ax();
        }
        catch (Exception ex) {}
    }
    
    public final void p() {
        try {
            this.ag.gu();
            if (ji.util.i.c(227)) {
                this.ag.gt();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ad ad) {
        this.a(ad, true);
    }
    
    public final boolean q() {
        try {
            return this.ah != null && this.ah.aj();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void r() {
        try {
            if (this.ag != null) {
                this.ag.e2();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ad ad, final boolean b) {
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 1a...");
        }
        if (ji.util.i.c(241)) {
            if (this.cd) {
                if (this.ag != null) {
                    this.ag.gm();
                }
                if (this.cd) {
                    return;
                }
            }
        }
        else if (this.cd) {
            return;
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 1b...");
        }
        if (!this.az && !this.ay) {
            return;
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 1...");
        }
        try {
            this.de = false;
            if (this.ay || this.az) {
                this.bp = true;
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 2...");
            }
            if (b && this.ay) {
                while (this.ay) {
                    ji.util.d.b(100, 133, this.cp);
                }
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 3...");
            }
            while (this.av) {
                ji.util.d.b(100, 134, this.cp);
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 4...");
            }
            this.av = true;
            if (this.cj) {
                if (ji.util.i.c(101)) {
                    this.et();
                }
                else {
                    this.ga();
                }
            }
            else if (ad.cp()) {
                this.et();
            }
            else {
                this.ga();
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 4a..");
            }
            this.dc = false;
            this.er = false;
            this.eo();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 5...");
            }
            try {
                this.m();
                this.ei();
                this.dr = false;
                try {
                    if (ji.util.d.dv()) {
                        ji.io.h.e(this.cp, "DocClose 6...");
                    }
                    this.ag.gm();
                    this.ah.o(false);
                }
                catch (Exception ex) {}
            }
            finally {
                this.n();
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 7...");
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 8...");
            }
            this.ag.bj();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 9...");
            }
            this.ag.dn();
            this.fr();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 10...");
            }
            this.ag.an(false);
            if (this.d5) {
                if (!ji.util.i.c(115) || !this.ah.aj()) {
                    this.ah.p(false);
                }
            }
            if (this.d5) {
                this.ah.as();
            }
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 11...");
            }
            this.ag.dc();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 12...");
            }
            this.ag.b2(false);
            this.f5();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 13...");
            }
            this.dg = null;
            this.dd = false;
            if (this.ak != null && this.ak.isVisible() && this.d5) {
                this.bo(false);
            }
            if (this.ai != null && this.ai.isVisible() && this.d5) {
                this.ai.setVisible(false);
            }
            try {
                if (this.e != null) {
                    this.e.a(ad);
                    this.e = null;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.f != null) {
                    this.f.a(ad);
                    this.f = null;
                }
            }
            catch (Exception ex3) {}
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 14...");
            }
            this.fa();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 15...");
            }
            try {
                if (this.c != null) {
                    this.q(this.c.toString());
                    if (!this.k(this.c.toString())) {
                        if (ji.util.d.dv()) {
                            ji.io.h.e(this.cp, "DocClose 16...");
                        }
                        if (ji.util.d.dv()) {
                            ji.io.h.e(this.cp, "DocClose 17...");
                        }
                        this.fb();
                    }
                }
            }
            catch (Exception ex4) {}
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 18...");
            }
            if (this.d5) {
                if (ji.util.i.c(110)) {
                    if (ji.util.e.j()) {
                        ji.io.ac.a(ad, this.cp, true, this.c);
                    }
                }
                else if (ji.util.e.j() || ji.util.e.h(this.cp)) {
                    ji.io.ac.a(ad, this.cp, ji.util.i.c(69), this.c);
                }
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cp, "DocClose 19...");
                }
                try {
                    if (!ji.util.i.c(110)) {
                        if (ji.util.i.c(83)) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("DocClose 19a...").append(ji.util.e.j()).append(", ").append(ji.util.e.h(this.cp)).append(", ").append((this.l == null) ? "false" : "true"))));
                        }
                        if (this.l != null && (ji.util.e.j() || ji.util.e.h(this.cp))) {
                            this.l.a(ad.c(), this, this.cp);
                        }
                    }
                    else if (ji.util.i.c(83)) {
                        ji.io.h.d(this.cp, "DocClose 19b...");
                    }
                }
                catch (Exception ex5) {}
            }
            this.eh = null;
            this.e8();
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 20...");
            }
            this.al(0);
            this.fa();
        }
        catch (Exception ex6) {}
        finally {
            if (ji.util.d.dv()) {
                ji.io.h.e(this.cp, "DocClose 21...");
            }
            this.a3 = true;
            try {
                if (this.ag != null) {
                    this.ag.l(true);
                }
            }
            catch (Exception ex7) {}
            this.c = null;
            this.s = null;
            this.bt = null;
            this.bu = null;
            this.bv = null;
            this.a4 = null;
            this.k = null;
            this.ac = 0;
            this.e9();
            this.az = false;
            this.av = false;
            this.dm = null;
            this.eg = null;
            ji.util.d.d(true, this.cp);
        }
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 22...");
        }
        this.fx();
        this.fx();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 23...");
        }
        this.repaint();
        if (ji.util.d.dv()) {
            ji.io.h.e(this.cp, "DocClose 24...");
        }
    }
    
    private final void e8() {
        this.ej = null;
        this.ek = null;
        this.em = null;
        this.el = null;
        this.bu = null;
        this.eo = null;
        this.z = false;
        this.y = false;
    }
    
    private void e9() {
        this.ab = 0;
    }
    
    private final void fa() {
        if (!this.d1) {
            ji.util.d.ew();
        }
    }
    
    public final void a(final Object[] t) {
        try {
            if (t != null) {
                this.t = t;
            }
            else {
                this.fb();
            }
        }
        catch (Exception ex) {}
    }
    
    public final Object[] s() {
        return this.t;
    }
    
    private final void fb() {
        try {
            if (this.t != null) {
                for (int i = 0; i < this.t.length; ++i) {
                    if (this.t[i] instanceof String) {
                        if (ji.util.d.bj((String)this.t[i])) {
                            this.l.a(new URL((String)this.t[i]), (Object)this.bk, this.cp);
                        }
                        else {
                            this.l.b((String)this.t[i]);
                        }
                    }
                    else {
                        this.l.a((URL)this.t[i], (Object)this.bk, this.cp);
                    }
                }
            }
        }
        catch (Exception ex) {}
        finally {
            this.t = null;
        }
    }
    
    public final void d(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.bx(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final Image t() throws Exception {
        this.fj();
        if (this.al == 5 || this.al == 6) {
            throw new Exception(this.ao(310));
        }
        return this.ag.bd(false);
    }
    
    private String ao(final int n) {
        return ji.util.d.b(n, this.cp);
    }
    
    public final Point a(final int n, final int n2) {
        try {
            if (this.ag != null) {
                return this.ag.e(n, n2);
            }
            return new Point(0, 0);
        }
        catch (Exception ex) {
            return new Point(0, 0);
        }
    }
    
    public void a(final String s) {
        try {
            if (this.ag != null) {
                this.ag.a(s);
            }
            if (this.ah != null) {
                this.ah.a(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final String u() {
        try {
            if (this.ag != null) {
                return this.ag.j();
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public final String v() {
        try {
            if (this.ag != null) {
                return this.ag.k();
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public final void a(final ad ad, final String s, final boolean b) {
        try {
            final dx r = this.ag.r();
            try {
                r.s = this.ag.ei();
                r.t = this.ag.ej();
                r.cl = this.ag.ek();
                r.ck = this.ag.el();
                r.cm = ji.util.d.z();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            ji.util.d.a(ad, r, this, s, this.cp, b, this.af(), this.ae());
        }
        catch (Exception ex2) {}
    }
    
    public final dx w() {
        try {
            if (this.c1() == 5 || this.c1() == 6) {
                if (this.ah != null) {
                    return this.ah.aw();
                }
            }
            else if (this.ag != null) {
                return this.ag.r();
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public dx b(final int n) {
        if (this.ag != null) {
            return this.ag.p(n);
        }
        return null;
    }
    
    public final boolean x() {
        return this.ag != null && this.ag.er();
    }
    
    public final boolean y() {
        return this.ag != null && this.ag.es();
    }
    
    public final int z() {
        int m = 0;
        try {
            m = this.ag.r().m;
        }
        catch (Exception ex) {}
        return Math.max(m, 10);
    }
    
    public final int aa() {
        int n = 0;
        try {
            n = this.ag.r().n;
        }
        catch (Exception ex) {}
        return Math.max(n, 10);
    }
    
    public final String ab() {
        String l = null;
        try {
            l = this.ag.r().l;
        }
        catch (Exception ex) {}
        return l;
    }
    
    public final String ac() {
        try {
            return this.ag.du();
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public final Object ad() {
        return this.c;
    }
    
    public final int ae() {
        return this.e(false);
    }
    
    public final int e(final boolean b) {
        return this.a(b, 0, true);
    }
    
    public final int a(final boolean b, final int n, final boolean b2) {
        if (!this.j()) {
            return this.ag.a(b, n, true);
        }
        return this.d2;
    }
    
    public final int af() {
        try {
            if (this.c1() == 5 || this.c1() == 6) {
                if (this.ah != null) {
                    return this.ah.av();
                }
            }
            else if (this.ag != null) {
                return this.ag.ea();
            }
            return this.ac;
        }
        catch (Exception ex) {
            return this.ac;
        }
    }
    
    public final void c(final int n) {
        if (this.cd) {
            return;
        }
        try {
            if (this.ag != null) {
                this.ag.l(n);
            }
            if (this.ah != null) {
                this.ah.j(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int ag() {
        try {
            if (this.ag != null) {
                return this.ag.c2();
            }
        }
        catch (Exception ex) {}
        return -1;
    }
    
    public final void b(final int n, final boolean b) {
        if (this.cd) {
            return;
        }
        try {
            if (this.ag != null) {
                this.ag.b(n, b);
            }
            if (this.ah != null) {
                this.ah.a(n, b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final boolean[] array) {
        if (this.cd) {
            return;
        }
        try {
            if (this.ag != null) {
                this.ag.a(array);
            }
            if (this.ah != null) {
                this.ah.b(array);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ah() {
        if (this.cd) {
            return;
        }
        try {
            if (this.ag != null) {
                this.ag.c3();
            }
            if (this.ah != null) {
                this.ah.at();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void f(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.bm(b);
            }
            if (this.ah != null) {
                this.ah.d(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean ai() {
        try {
            if (this.ag != null) {
                return this.ag.cl();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public final void a(final int n, final boolean b, final boolean b2) throws Exception {
        if (b) {
            this.i(n, b2);
        }
        else {
            if (this.e != null && !this.dc) {
                if (ji.util.i.c(36)) {
                    ji.io.h.d(this.cp, "doc: page-run synchronizing");
                }
                synchronized (this.dz) {
                    if (ji.util.i.c(36)) {
                        ji.io.h.d(this.cp, "doc: page-run synchronized");
                    }
                    this.j(n, b2);
                    if (ji.util.i.c(36)) {
                        ji.io.h.d(this.cp, "doc: page-run end synchronized");
                    }
                    // monitorexit(this.dz)
                    return;
                }
            }
            this.j(n, b2);
        }
    }
    
    private final void i(final int n, final boolean b) throws Exception {
        try {
            if (this.co > 0) {
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: Slow up!, waiting for previots stop..."));
                }
                ++this.co;
                while (this.co > 0) {
                    ji.util.d.b(50, 135, this.cp);
                }
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: waited for previots stop..."));
                }
            }
            if (this.cm != null) {
                this.v = true;
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("> Net: DC Stopping previous pthread..."));
                }
                try {
                    this.au = false;
                    if (ji.util.d.ak) {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Stopping1: ").append(n))));
                    }
                    this.m();
                    if (!this.dc) {
                        this.ag.gm();
                    }
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Stopping2: ").append(n))));
                    }
                    if (!this.dc) {
                        this.ah.o(false);
                    }
                    if (ji.util.d.ak) {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Stopping3: ").append(n))));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    if (!this.dc) {
                        this.aj();
                    }
                    this.n();
                }
                if (ji.util.d.ak) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: Stopping4: ").append(n))));
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            --this.co;
        }
        this.co = Math.max(this.co, 0);
        this.v = false;
        if (this.co == 0) {
            (this.cm = new bb(this.cp, new wt(n, b))).start();
        }
    }
    
    public void aj() {
        try {
            while (this.cm != null) {
                ji.util.d.b(100, 136, this.cp);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void j(final int d0, final boolean b) throws Exception {
        try {
            synchronized (this.a) {
                this.a.a = true;
            }
            // monitorexit(this.a)
            this.v = false;
            try {
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cp, "Setting page (0)...");
                }
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageA: ").append(d0))));
                }
                this.c(d0);
                final int ac = this.ac;
                if (d0 == 0) {
                    this.a(this.bk);
                }
                else {
                    this.fj();
                    int ae;
                    if ((this.d0 = d0) > this.ae()) {
                        ae = this.ae();
                    }
                    else if (d0 < 1) {
                        ae = 1;
                    }
                    else {
                        ae = d0;
                    }
                    boolean[] o = null;
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageE: ").append(d0))));
                    }
                    if (!this.v && !this.cn) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageF: ").append(d0))));
                        }
                        if (this.c1() == 5 || this.c1() == 6) {
                            o = this.ah.o();
                        }
                    }
                    if (!this.v && !this.cn && (this.ac != ae || this.c1() == 5 || this.c1() == 6)) {
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageG: ").append(d0))));
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageH: ").append(d0))));
                        }
                        this.ac = ae;
                        if (!this.dc) {
                            this.ag.gp();
                        }
                        if (this.ar < 0) {
                            this.ar = 0;
                        }
                        if (this.c1() == 5 || this.c1() == 6) {
                            if (ji.util.d.dr()) {
                                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageI: ").append(d0))));
                            }
                            if (!this.v && !this.cn) {
                                this.ah.k(this.ac);
                            }
                        }
                        else {
                            if (ji.util.d.dr()) {
                                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageJ1: ").append(d0))));
                            }
                            if (!this.v && !this.cn) {
                                if (!this.dc) {
                                    this.ag.d(this.ac, this.ar);
                                    if (this.eg != null) {
                                        this.eg[this.ac - 1] = this.ag.ds().f();
                                    }
                                }
                            }
                            if (!this.v && !this.cn) {
                                this.ah.k(this.ac);
                            }
                            if (ji.util.d.dr()) {
                                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageJ2: ").append(d0))));
                            }
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageK: ").append(d0))));
                        }
                        if (!this.dc) {
                            this.b(this.ag.r());
                        }
                        if (!this.dc) {
                            this.al(0);
                        }
                    }
                    this.bk.g7();
                    if (!this.v && !this.cn) {
                        if (!this.dc) {
                            this.repaint();
                        }
                        if (ji.util.d.dr()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageL: ").append(d0))));
                        }
                    }
                    if (!this.v && !this.cn && !this.dc) {
                        boolean b2 = true;
                        if (this.bk != null && this.bk.bi(4) && this.bk.k3()) {
                            b2 = false;
                        }
                        if (ji.util.d.b()) {
                            b2 = false;
                        }
                        if (b2) {
                            if (this.al != 5 && this.al != 6) {
                                if (this.al != 0) {
                                    if (this.ah != null && this.ah.a9()) {
                                        this.au = true;
                                        this.fu();
                                    }
                                    else {
                                        this.fs();
                                    }
                                }
                                else {
                                    this.fs();
                                }
                            }
                            else if (this.ah != null && this.ah.a9()) {
                                if (this.ah.a(o)) {
                                    this.au = true;
                                    this.fu();
                                }
                                else {
                                    this.b(new d7(this, 3, this.af()));
                                    this.b(new d7(this, 50, this.af()));
                                }
                            }
                            else {
                                this.b(new d7(this, 3, this.af()));
                                this.b(new d7(this, 50, this.af()));
                            }
                        }
                        else {
                            this.b(new d7(this, 3, this.af()));
                        }
                        if (b && this.ag.v()) {
                            this.ag.b(new a3(this, 49, 16, d0));
                        }
                    }
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageO: ").append(d0))));
                    }
                }
            }
            catch (Exception ex) {
                ji.util.d.b(ex);
                throw ex;
            }
            finally {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageP: ").append(d0))));
                }
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cp, "Set page.");
                }
            }
            if (ji.util.d.dr()) {
                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.currentTimeMillis()))).append("> Net: SetPageQ: ").append(d0))));
            }
        }
        finally {
            synchronized (this.a) {
                this.a.a = false;
                this.a.notifyAll();
            }
            // monitorexit(this.a)
        }
    }
    
    public final void ak() {
        synchronized (this.a) {
            while (this.a.a && !ji.util.d.ck(this.cp)) {
                try {
                    this.a.wait(100L);
                }
                catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        // monitorexit(this.a)
    }
    
    public final void g(final boolean w) {
        this.w = w;
        try {
            if (this.ag != null) {
                this.ag.n(w);
            }
            if (this.ah != null) {
                this.ah.l(w);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final int n, final boolean b) {
        try {
            if (n >= 0 && n <= 3) {
                this.ah.q(n);
                this.ag.f(n, false);
                this.fc();
            }
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 9, n));
        }
    }
    
    public final int al() {
        if (this.al == 5 || this.al == 6) {
            return this.ah.n(this.af());
        }
        return this.ag.f7();
    }
    
    public final ck am() {
        return this.ag.ds();
    }
    
    public final boolean an() {
        try {
            return this.ag.b6();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean a(final KeyEvent keyEvent) {
        try {
            return this.ag.a(keyEvent);
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void b(final String ck) {
        this.ck = ck;
    }
    
    public final void ao() {
        this.ck = null;
    }
    
    public final void h(final boolean b) {
        try {
            this.ag.cl(b);
        }
        catch (Exception ex) {}
    }
    
    public final Dimension d(final int n) {
        Dimension dimension = null;
        try {
            if (this.ag != null && n == this.ag.ea()) {
                dimension = this.ag.c5();
            }
            if (dimension == null && this.ah != null) {
                dimension = this.ah.a2();
            }
        }
        catch (Exception ex) {}
        return dimension;
    }
    
    public final int b(final int n, final int n2) {
        return this.ag.a(n, n2);
    }
    
    public final String ap() {
        return this.ag.e0();
    }
    
    public final boolean c(final String s) {
        return this.ag.t(s);
    }
    
    public final void d(final String s) {
        try {
            this.ag.k(s);
        }
        catch (Exception ex) {}
    }
    
    public final void i(final boolean b) {
        try {
            this.ag.bv(b);
        }
        catch (Exception ex) {}
    }
    
    public final void j(final boolean b) {
        try {
            this.ag.by(b);
        }
        catch (Exception ex) {}
    }
    
    public final void k(final boolean b) {
        try {
            this.ag.bt(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean aq() {
        try {
            return this.ag.cv();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final boolean ar() {
        try {
            return this.ag.cw();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void as() {
        try {
            this.ag.cx();
        }
        catch (Exception ex) {}
    }
    
    public final boolean at() {
        try {
            return this.ag.c0();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final boolean au() {
        try {
            return this.ag.cg();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void update(final Graphics graphics) {
        if (this.isSwing()) {
            super.update(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public final void d(final int n, final boolean b) {
        this.k(n, b);
    }
    
    public final void a(final Vector vector, final boolean b) {
        this.b(vector, b);
    }
    
    public final void l(final boolean b) {
        try {
            this.ag.co(b);
            this.ah.v(b);
            this.dt();
            this.d5();
        }
        catch (Exception ex) {}
    }
    
    public final boolean av() {
        try {
            return this.ag.fw();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final Color aw() {
        return this.ag.fx();
    }
    
    public final void a(final Color color) {
        try {
            this.ah.l();
            this.ah.b(color);
            this.ag.a(color);
            this.d5();
        }
        catch (Exception ex) {}
    }
    
    public final Color ax() {
        return this.ag.fy();
    }
    
    public final void b(final Color color) {
        try {
            this.ah.l();
            this.ag.c(color);
            this.ah.c(color);
            this.d5();
        }
        catch (Exception ex) {}
    }
    
    public final boolean ay() {
        try {
            return this.ag == null || this.ag.dx();
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public final void m(final boolean b) {
        try {
            this.ag.ca(b);
        }
        catch (Exception ex) {}
    }
    
    public final void e(final int n) {
        try {
            this.ag.ab(n);
        }
        catch (Exception ex) {}
    }
    
    public final void f(final int n) {
        try {
            this.ag.ac(n);
        }
        catch (Exception ex) {}
    }
    
    public final void az() {
        try {
            this.ag.dy();
        }
        catch (Exception ex) {}
    }
    
    public final void g(final int n) {
        try {
            this.ag.ad(n);
        }
        catch (Exception ex) {}
    }
    
    public final void h(final int n) {
        try {
            this.ag.ae(n);
        }
        catch (Exception ex) {}
    }
    
    public final void i(final int n) {
        try {
            this.ag.af(n);
        }
        catch (Exception ex) {}
    }
    
    public final eh a0() {
        eh dz = null;
        try {
            dz = this.ag.dz();
        }
        catch (Exception ex) {}
        return dz;
    }
    
    public final void j(final int n) {
        try {
            this.ag.al(n);
        }
        catch (Exception ex) {}
    }
    
    public final void a(final fi fi) {
        try {
            this.ag.a(fi);
        }
        catch (Exception ex) {}
    }
    
    public final void b(final fi fi) {
        try {
            this.ag.b(fi);
        }
        catch (Exception ex) {}
    }
    
    public final void k(final int n) {
        try {
            this.ag.ak(n);
        }
        catch (Exception ex) {}
    }
    
    public final void l(final int n) {
        try {
            this.ag.aj(n);
        }
        catch (Exception ex) {}
    }
    
    public final void m(final int n) {
        try {
            this.ag.ai(n);
        }
        catch (Exception ex) {}
    }
    
    public final void a(final Point point, final boolean b) {
        try {
            this.ag.a(point, b);
        }
        catch (Exception ex) {}
    }
    
    public void b(final Point point, final boolean b) {
        try {
            this.ag.b(point, b);
        }
        catch (Exception ex) {}
    }
    
    public void a(final double n) {
        try {
            this.ag.c(n);
        }
        catch (Exception ex) {}
    }
    
    public void b(final double n) {
        try {
            this.ag.d(n);
        }
        catch (Exception ex) {}
    }
    
    public void n(final int n) {
        try {
            this.ag.ag(n);
        }
        catch (Exception ex) {}
    }
    
    public void o(final int n) {
        try {
            this.ag.ah(n);
        }
        catch (Exception ex) {}
    }
    
    public final void p(final int n) {
        try {
            this.ag.aa(n);
            this.ah.i(n);
        }
        catch (Exception ex) {}
    }
    
    public final int a1() {
        if (this.ag != null) {
            return this.ag.dw();
        }
        return 1;
    }
    
    public void q(final int cu) {
        try {
            this.cu = cu;
            if (this.ag != null) {
                this.ag.a(cu);
            }
            if (this.ah != null) {
                this.ag.a(cu);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void n(final boolean b) {
        try {
            this.ag.f(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean a2() {
        try {
            return this.ag.h();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void e(final String s) {
        try {
            if (this.ag != null) {
                this.ag.l(s);
            }
            if (this.ah != null) {
                this.ah.d(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void o(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.bn(b);
            }
            if (this.ah != null) {
                this.ah.r(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void p(final boolean b) {
        if (this.ag != null) {
            this.ag.aw(b);
        }
    }
    
    public final void q(final boolean b) {
        if (this.ag != null) {
            this.ag.c(b);
        }
    }
    
    public final boolean a3() {
        return this.ag != null && this.ag.b();
    }
    
    public final boolean a4() {
        return this.ag != null && this.ag.bf();
    }
    
    public final boolean a5() {
        try {
            return this.ag.f2();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void r(final boolean b) {
        if (this.ag != null) {
            this.ag.cq(b);
        }
    }
    
    public final String a6() {
        if (this.ag != null) {
            return this.ag.f3();
        }
        return null;
    }
    
    public final boolean a7() {
        return this.ag != null && this.ag.f4();
    }
    
    public final void a8() {
        if (this.ag != null) {
            this.ag.cb();
        }
    }
    
    public final void s(final boolean b) {
        if (this.ag != null) {
            this.ag.ck(b);
        }
    }
    
    public final boolean a9() {
        return this.ag != null && this.ag.f5();
    }
    
    public final boolean ba() {
        return this.ag != null && this.ag.f6();
    }
    
    public final void t(final boolean b) {
        if (this.ag.gh() != b) {
            this.ag.cz(b);
            if (this.al != 5 && this.al != 6 && this.ag.cg()) {
                this.d5();
            }
        }
    }
    
    public final void r(final int n) {
        if (this.ag != null) {
            this.ag.v(n);
        }
    }
    
    public final void u(final boolean cy) {
        this.cy = cy;
        if (this.ag != null) {
            this.ag.c1(cy);
        }
    }
    
    public final void v(final boolean dt) {
        this.dt = dt;
        try {
            this.ag.al(dt);
        }
        catch (Exception ex) {}
    }
    
    public final void c(final int n, final int n2) {
        try {
            this.ag.a(-n, -n2, true, false);
        }
        catch (Exception ex) {}
    }
    
    public final void c(final double n) {
        try {
            this.ag.a(n, false);
        }
        catch (Exception ex) {}
    }
    
    public final void c(final Point point, final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.ch(true);
                this.ag.c(point, b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void f(final String d7) {
        try {
            this.d7 = d7;
            if (this.ag != null) {
                this.ag.v(d7);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void s(final int n) {
        this.e(n, true);
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        try {
            if (this.ag != null) {
                this.ag.a(n, n2, n3, n4);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final boolean b, final boolean b2, final boolean b3, final boolean b4) {
        try {
            if (this.ag != null) {
                this.ag.a(b, b2, b3, b4);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean bb() {
        return this.ag.ez();
    }
    
    public final void e(final int n, final boolean b) {
        try {
            final int ac = ji.util.d.ac(n);
            if (ac >= 0 && ac <= 270) {
                this.ah.p(ac);
                this.ag.e(ac, false);
                this.fc();
            }
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 5, n));
        }
    }
    
    public void w(final boolean b) {
        try {
            this.ah.t(b);
            this.ag.bo(b);
        }
        catch (Exception ex) {}
    }
    
    public final int t(final int n) {
        if (this.ag != null) {
            return this.ag.n(n);
        }
        return 0;
    }
    
    public final void a(final int[] array) {
        try {
            for (int i = 0; i < array.length; ++i) {
                final int ac = ji.util.d.ac(array[i]);
                if (ac >= 0 && ac <= 270) {
                    this.ah.a(ac, i + 1, false);
                    this.ag.c(ac, i + 1, false);
                }
            }
            this.fc();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(final int n, final boolean[] array) {
        this.a(n, array, true);
    }
    
    public final void a(final int n, final boolean[] array, final boolean b) {
        try {
            final int ac = ji.util.d.ac(n);
            if (ac >= 0 && ac <= 270) {
                this.ah.a(ac, array);
                this.ag.a(ac, false, array);
                this.fc();
            }
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 6, n));
        }
    }
    
    public final void a(final boolean[] array, final boolean b) {
        try {
            this.ah.c(array);
            this.ag.a(false, array);
            this.fc();
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 10));
        }
    }
    
    public final void b(final boolean[] array, final boolean b) {
        try {
            this.ah.d(array);
            this.ag.b(false, array);
            this.fc();
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 11));
        }
    }
    
    public final void bc() {
        try {
            if (this.ag != null) {
                this.ag.ak(false);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void bd() {
        try {
            if (this.ag != null) {
                this.ag.aq();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void c(final boolean[] array, final boolean b) {
        try {
            this.ah.e(array);
            this.ag.c(false, array);
            this.fc();
        }
        catch (Exception ex) {}
        if (b && this.ag.v()) {
            this.ag.b(new a3(this, 49, 8));
        }
    }
    
    public final int be() {
        if (this.al == 5 || this.al == 6) {
            return this.ah.m(this.af());
        }
        return this.ag.f8();
    }
    
    private final void fc() {
        if (this.bc && !ji.util.d.ck(this.cq) && this.az && this.bk != null && !this.bk.cp() && !this.bk.dc()) {
            try {
                if (this.al == 0) {
                    this.fs();
                }
                else if (this.ah.a9()) {
                    this.d5();
                }
                else {
                    this.au = true;
                    this.ah.y();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void b(final boolean[] array) {
        switch (this.be()) {
            case 0: {
                this.a(90, array);
                break;
            }
            case 90: {
                this.a(180, array);
                break;
            }
            case 180: {
                this.a(270, array);
                break;
            }
            case 270: {
                this.a(0, array);
                break;
            }
        }
    }
    
    public final void bf() {
        switch (this.be()) {
            case 0: {
                this.s(90);
                break;
            }
            case 90: {
                this.s(180);
                break;
            }
            case 180: {
                this.s(270);
                break;
            }
            case 270: {
                this.s(0);
                break;
            }
        }
    }
    
    public final void c(final boolean[] array) {
        switch (this.be()) {
            case 0: {
                this.a(180, array);
                break;
            }
            case 90: {
                this.a(270, array);
                break;
            }
            case 180: {
                this.a(0, array);
                break;
            }
            case 270: {
                this.a(90, array);
                break;
            }
        }
    }
    
    public final void d(final boolean[] array) {
        switch (this.be()) {
            case 0: {
                this.a(270, array);
                break;
            }
            case 90: {
                this.a(0, array);
                break;
            }
            case 180: {
                this.a(90, array);
                break;
            }
            case 270: {
                this.a(180, array);
                break;
            }
        }
    }
    
    public final void bg() {
        switch (this.be()) {
            case 0: {
                this.s(270);
                break;
            }
            case 90: {
                this.s(0);
                break;
            }
            case 180: {
                this.s(90);
                break;
            }
            case 270: {
                this.s(180);
                break;
            }
        }
    }
    
    public final void bh() {
        switch (this.be()) {
            case 0: {
                this.s(180);
                break;
            }
            case 90: {
                this.s(270);
                break;
            }
            case 180: {
                this.s(0);
                break;
            }
            case 270: {
                this.s(90);
                break;
            }
        }
    }
    
    public void x(final boolean b) {
        if (this.ag != null) {
            this.ag.e(b);
        }
    }
    
    public final void y(final boolean ct) {
        this.ct = ct;
    }
    
    public void z(final boolean b) {
        if (this.ag != null) {
            this.ag.g(b);
        }
    }
    
    public final boolean bi() {
        return this.ag.gh();
    }
    
    public boolean bj() {
        return this.ag.i();
    }
    
    public void aa(final boolean b) {
        if (this.ag != null) {
            this.ag.i(b);
        }
    }
    
    public void ab(final boolean b) {
        if (this.ag != null) {
            this.ag.s(b);
        }
        if (this.ah != null) {
            this.ah.b(b);
        }
    }
    
    public void ac(final boolean b) {
        if (this.ag != null) {
            this.ag.r(b);
        }
    }
    
    public final void a(final double n, final boolean b) {
        if (this.ag != null) {
            this.ag.b(n, b);
        }
    }
    
    public final double bk() {
        if (this.ag != null) {
            return this.ag.ao();
        }
        return 25.0;
    }
    
    public final void f(final int ar, final boolean b) {
        try {
            if (ar >= 0 && ar <= 3) {
                if (ar == 1 || ar == 0 || ar == 2) {
                    this.ar = ar;
                }
                this.ag.a(ar, b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final em em) {
        this.a(em, false);
    }
    
    public final void a(final em em, final boolean b) {
        if (em == null) {
            this.b6 = false;
        }
        else {
            this.b6 = true;
        }
        if (this.ag != null) {
            this.ag.a(em, b);
        }
    }
    
    public final void bl() {
        if (this.ag != null) {
            this.ag.ex();
        }
    }
    
    public final void g(final String s) {
        if (this.ag != null) {
            this.ag.s(s);
        }
    }
    
    public final String bm() {
        if (this.ag != null) {
            return this.ag.ev();
        }
        return "";
    }
    
    public final String bn() {
        if (this.ag != null) {
            return this.ag.ew();
        }
        return "";
    }
    
    public final boolean bo() {
        return this.ag != null && this.ag.eu();
    }
    
    public final void a(final Rectangle rectangle, final boolean b, final int n) {
        this.ag.a(rectangle, b, n);
    }
    
    public final void ad(final boolean b) {
        if (this.bp() != 3) {
            this.ar = this.bp();
        }
        if (this.ag.ag(b)) {
            this.fs();
        }
    }
    
    public final void ae(final boolean b) {
        if (this.bp() != 3) {
            this.ar = this.bp();
        }
        this.ar = -1;
        if (this.ag.af(b) && !ji.util.d.b()) {
            this.fs();
        }
    }
    
    public final void af(final boolean b) {
        if (this.bp() != 3) {
            this.ar = this.bp();
        }
        if (this.ag.ah(b)) {
            this.fs();
        }
    }
    
    public final int bp() {
        try {
            return this.ag.z();
        }
        catch (Exception ex) {
            ji.util.d.b(ex);
            return 2;
        }
    }
    
    public final void ag(final boolean b) {
        if (this.ag != null) {
            this.ag.c7(b);
        }
    }
    
    public final void ah(final boolean b) {
        if (this.ag != null) {
            this.ag.c3(b);
        }
    }
    
    public final void ai(final boolean b) {
        if (this.ag != null) {
            this.ag.c4(b);
        }
    }
    
    public final void aj(final boolean b) {
        if (this.ag != null) {
            this.ag.c5(b);
        }
    }
    
    public final void ak(final boolean b) {
        if (this.ag != null) {
            this.ag.c6(b);
        }
    }
    
    public final void al(final boolean b) {
        if (this.ag != null) {
            this.ag.c8(b);
        }
    }
    
    public final void a(final boolean b, final boolean b2, final boolean b3) {
        if (this.ag != null) {
            this.ag.a(b, b2, b3);
        }
    }
    
    public final boolean bq() {
        return this.ag != null && this.ag.gx();
    }
    
    public final void a(final int n, final Object o, final boolean b) {
        if (this.ag != null) {
            this.ag.a(n, o, b);
        }
    }
    
    public final int a(final Object o) {
        if (this.ag != null) {
            return this.ag.b(o);
        }
        return 0;
    }
    
    public final boolean[] br() {
        if (this.ag != null) {
            return this.ag.dv();
        }
        return null;
    }
    
    public final void u(final int n) {
        try {
            if (this.ag != null) {
                this.ag.ap(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int bs() {
        try {
            if (this.ag != null) {
                return this.ag.g2();
            }
        }
        catch (Exception ex) {}
        return ji.util.d.lc;
    }
    
    public final void v(final int n) {
        try {
            if (this.ag != null) {
                this.ag.aq(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int bt() {
        try {
            if (this.ag != null) {
                return this.ag.g3();
            }
        }
        catch (Exception ex) {}
        return ji.util.d.k9;
    }
    
    public final void am(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.c9(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean bu() {
        try {
            if (this.ag != null) {
                return this.ag.g4();
            }
        }
        catch (Exception ex) {}
        return ji.util.d.ld;
    }
    
    public final void w(final int n) {
        try {
            if (this.ag != null) {
                this.ag.ar(n);
            }
        }
        catch (Exception ex) {}
    }
    
    public final int bv() {
        try {
            if (this.ag != null) {
                return this.ag.g5();
            }
        }
        catch (Exception ex) {}
        return ji.util.d.le;
    }
    
    public final boolean x(final int n) {
        return this.ag != null && this.ag.z(n);
    }
    
    public final void c(final Color color) {
        if (this.ag != null) {
            this.ag.b(color);
        }
        if (this.ah != null) {
            this.ah.a(color);
        }
    }
    
    public final void a(final Color[] array) {
        if (this.ag != null) {
            this.ag.a(array);
        }
        if (this.ah != null) {
            this.ah.a(array);
        }
    }
    
    public final void a(final Object o, final int n) {
        if (this.ag != null) {
            this.ag.a(o, n);
        }
        if (this.ah != null) {
            this.ah.a(o, n);
        }
    }
    
    public final void a(final Object[] array, final int n) {
        if (this.ag != null) {
            this.ag.a(array, n);
        }
        if (this.ah != null) {
            this.ah.a(array, n);
        }
    }
    
    public final void an(final boolean b) {
        if (this.ag != null) {
            this.ag.cb(b);
        }
    }
    
    public final void y(final int n) {
        if (this.ag != null) {
            this.ag.am(n);
        }
    }
    
    public final void bw() {
        if (this.ag != null) {
            final boolean[] array = new boolean[this.ae()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = true;
            }
            this.ag.b(array);
        }
    }
    
    public final void z(final int n) {
        if (this.ag != null) {
            this.ag.an(n);
        }
    }
    
    public final void bx() {
        if (this.ag != null) {
            this.ag.d9();
        }
    }
    
    public final String aa(final int n) {
        if (this.ag != null) {
            return this.ag.y(n);
        }
        return null;
    }
    
    public final void a(final Rectangle rectangle, final Object o) {
        if (this.ag != null) {
            this.ag.a(rectangle, o);
        }
    }
    
    public final Rectangle b(final Object o) {
        if (this.ag != null) {
            return this.ag.c(o);
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    public final void ao(final boolean b) {
        if (this.al != 5 && this.al != 6 && this.b7()) {
            this.ag.t(b);
        }
    }
    
    public final boolean by() {
        return this.ag.aa();
    }
    
    public final void ap(final boolean b) {
        if (this.al != 5 && this.al != 6 && this.b7()) {
            this.ag.u(b);
        }
    }
    
    public final boolean bz() {
        return this.ag.ab();
    }
    
    public final void aq(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.w(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ar(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.x(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void as(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.y(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void at(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.z(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean b0() {
        boolean al = false;
        try {
            if (this.ag != null) {
                al = this.ag.al();
            }
        }
        catch (Exception ex) {}
        return al;
    }
    
    public final void au(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.q(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean b1() {
        boolean u = false;
        try {
            if (this.ag != null) {
                u = this.ag.u();
            }
        }
        catch (Exception ex) {}
        return u;
    }
    
    public final void av(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.aa(b);
            }
            if (this.ah != null) {
                this.ah.q(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void d(final double n) {
        try {
            this.ag.a(n, false);
            if (!ji.util.d.b()) {
                this.fs();
            }
        }
        catch (Exception ex) {}
    }
    
    public final double b2() {
        if (this.ag != null) {
            return this.ag.aj();
        }
        return 0.0;
    }
    
    public final int b3() {
        return this.ag.af();
    }
    
    public final double b4() {
        return this.ag.ag();
    }
    
    public final int b5() {
        return this.ag.ah();
    }
    
    public final double b6() {
        return this.ag.ai();
    }
    
    public final void aw(final boolean b) {
        try {
            this.ag.ct(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean b7() {
        return this.ag.gb();
    }
    
    public final void ax(final boolean b) {
        try {
            this.ag.cu(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean b8() {
        return this.ag.gc();
    }
    
    public final void ay(final boolean b) {
        try {
            this.ag.cs(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean b9() {
        return this.ag.f9();
    }
    
    public final void a(final boolean b, final boolean b2) {
        try {
            this.ah.a(b, false);
            this.ag.g(b, false);
            this.fc();
        }
        catch (Exception ex) {}
        if (b2 && this.ag.v()) {
            this.ag.b(new a3(this, 49, 7, b));
        }
    }
    
    public final boolean ca() {
        if (this.al == 5 || this.al == 6) {
            return this.ah.c();
        }
        return this.ag.ge();
    }
    
    public boolean cb() {
        return this.dr;
    }
    
    public boolean cc() {
        return this.dr || (this.s != null && this.s.length > 0 && this.s[0] != null);
    }
    
    public boolean cd() {
        return this.ag != null && this.ag.fv();
    }
    
    public final boolean ce() {
        return this.ag != null && this.ag.a2();
    }
    
    public final void b(final boolean b, final int n, final boolean b2) {
        if (this.ag != null) {
            this.ag.b(b, n, b2);
        }
    }
    
    public final void a(final boolean b, final int n) {
        if (this.ag != null) {
            this.ag.a(b, n);
        }
    }
    
    public final int cf() {
        try {
            return this.ag.gg();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public final void az(final boolean b) {
        try {
            this.ag.cw(b);
        }
        catch (Exception ex) {}
    }
    
    public final boolean cg() {
        return this.ag.gj();
    }
    
    public final void h(final String s) {
        if (this.ag != null) {
            this.ag.e(s);
        }
        if (this.ah != null) {
            this.ah.c(s);
        }
    }
    
    public final void a(final String[] array, final Color[] array2, final String[] array3, final Color[] array4) {
        if (this.ag != null) {
            this.ag.a(array, array2);
        }
        if (this.ah != null) {
            this.ah.a(array3, array4);
        }
    }
    
    public final void a(final String[] array, final Color[] array2) {
        if (this.ah != null) {
            this.ah.b(array, array2);
        }
    }
    
    public final String[] ch() {
        return this.ag.a3();
    }
    
    public final void a(final String[] array) {
        if (this.ah != null) {
            this.ah.a(array);
        }
    }
    
    public final void a(final String[] array, final es es) {
        if (this.ah != null) {
            this.ah.a(array, es);
        }
    }
    
    public final void e(final double n) {
        if (this.aj != null) {
            final int b = this.aj.b();
            this.aj.a(n);
            if (n == -1) {
                this.aj.c(false);
                if (this.ah != null) {
                    if (this.ah.p()) {
                        this.aj.d(true);
                    }
                    else {
                        this.aj.d(false);
                    }
                }
                this.fx();
            }
            else if (b != this.aj.b()) {
                this.fx();
            }
        }
    }
    
    public final void ci() {
        if (this.aj != null) {
            this.aj.a();
        }
    }
    
    public final void a(final String s, final boolean b) {
        if (this.aj != null) {
            this.aj.a(s, b);
        }
    }
    
    public final void cj() {
        if (this.ah != null) {
            this.ah.q();
        }
    }
    
    public final Vector b(final String s, final boolean b) {
        if (!b) {
            if (this.ag != null) {
                return this.ag.r(s);
            }
            return null;
        }
        else {
            if (this.ah != null) {
                return this.ah.e(s);
            }
            return null;
        }
    }
    
    public final void ck() {
        if (this.ah != null) {
            this.ah.r();
        }
    }
    
    public void a(final String s, final int n) {
        try {
            this.ah.a(s, n);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void i(final String s) {
        try {
            this.ah.b(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void b(final String[] array) {
        if (this.ah != null) {
            this.ah.b(array);
        }
    }
    
    public final String[] cl() {
        try {
            return this.ah.s();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final String[] cm() {
        try {
            return this.ah.t();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final int cn() {
        try {
            return this.ah.au();
        }
        catch (Exception ex) {
            return 1;
        }
    }
    
    public final void ab(final int n) {
        try {
            this.ah.l(n);
        }
        catch (Exception ex) {}
    }
    
    public final Color[] co() {
        return this.ag.a4();
    }
    
    public final String[] cp() {
        return this.ah.u();
    }
    
    public final Color[] cq() {
        return this.ah.v();
    }
    
    public final String[] cr() {
        return this.ah.w();
    }
    
    public final Color[] cs() {
        return this.ah.x();
    }
    
    public final void a0(final boolean b) {
        if (this.ag != null) {
            this.ag.ad(b);
        }
        if (this.ah != null) {
            this.ah.e(b);
        }
    }
    
    public final void a1(final boolean b) {
        if (this.ag != null) {
            this.ag.ae(b);
        }
    }
    
    public final boolean ct() {
        return this.ag != null && this.ag.an();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public final int cu() {
        if (this.al == 6 || this.al == 5) {
            if (this.ah != null) {
                return this.ah.d();
            }
            return ji.util.d.du;
        }
        else {
            if (this.ag != null) {
                return this.ag.dk();
            }
            return ji.util.d.du;
        }
    }
    
    public final int cv() {
        if (this.al == 6 || this.al == 5) {
            if (this.ah != null) {
                return this.ah.e();
            }
            return ji.util.d.dv;
        }
        else {
            if (this.ag != null) {
                return this.ag.dl();
            }
            return ji.util.d.dv;
        }
    }
    
    public final int cw() {
        if (this.al == 6 || this.al == 5) {
            if (this.ah != null) {
                return this.ah.f();
            }
            return ji.util.d.dw;
        }
        else {
            if (this.ag != null) {
                return this.ag.dm();
            }
            return ji.util.d.dw;
        }
    }
    
    public final void ac(final int n) {
        if (this.ah != null) {
            this.ah.a(n);
        }
        if (this.ag != null) {
            this.ag.s(n);
        }
    }
    
    public final void ad(final int n) {
        if (this.ah != null) {
            this.ah.b(n);
        }
        if (this.ag != null) {
            this.ag.t(n);
        }
    }
    
    public final void ae(final int n) {
        if (this.ah != null) {
            this.ah.c(n);
        }
        if (this.ag != null) {
            this.ag.u(n);
        }
    }
    
    public final void a(final int n, final int n2, final int n3) {
        if (this.ah != null) {
            this.ah.a(n, n2, n3);
        }
        if (this.ag != null) {
            this.ag.a(n, n2, n3);
        }
    }
    
    public final void a2(final boolean b) {
        try {
            if (this.ah != null) {
                this.ah.c(b);
            }
            if (this.ag != null) {
                this.ag.b0(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final dv[] array, final int n) {
        if (this.ah != null) {
            this.ah.a(array, n);
        }
        if (this.ag != null) {
            this.ag.a(array, n);
        }
    }
    
    public final void cx() {
        if (this.ah != null) {
            this.ah.g();
        }
        if (this.ag != null) {
            this.ag.c6();
        }
    }
    
    public final dv[] cy() {
        if (this.ag != null) {
            return this.ag.c7();
        }
        return this.ah.ax();
    }
    
    public final void cz() {
        if (this.ag != null) {
            this.ag.c();
        }
    }
    
    public void a3(final boolean b) {
        if (this.ag != null) {
            this.ag.b8(b);
        }
    }
    
    public final boolean c0() {
        return this.ag != null && this.ag.d8();
    }
    
    public final int c1() {
        return this.al;
    }
    
    public final Rectangle c2() {
        return this.aa;
    }
    
    private final boolean fd() {
        return ji.util.i.c(7) && ji.util.e.u();
    }
    
    public final void b(int a, final boolean b, final boolean b2) {
        try {
            if (this.fd() || ji.util.d.av()) {
                if (this.bk.bi(22) && this.ah != null) {
                    a = ji.util.d.a(a, this.bk);
                    this.ag.l(false);
                    this.ah.n(false);
                    try {
                        this.as(a);
                        this.ah.f(a);
                        if (this.ai != null) {
                            this.ai.a(a);
                        }
                    }
                    finally {
                        this.ag.l(true);
                        this.ah.n(true);
                    }
                    if (!b2 && this.az) {
                        this.d5();
                    }
                }
                if (b && this.ag.v()) {
                    this.ag.b(new a3(this, 49, 27, a));
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final int c3() {
        try {
            if (this.ah != null) {
                return this.ah.ab();
            }
        }
        catch (Exception ex) {}
        return this.bk.dt();
    }
    
    public final void a4(final boolean d5) {
        try {
            this.d5 = d5;
            if (this.ag != null) {
                this.ag.be(d5);
            }
            if (this.ah != null) {
                this.ah.w(d5);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void af(final int n) {
        this.c(n, false, true);
    }
    
    public final void c(final int al, boolean b, final boolean b2) {
        if (!this.z && this.y && al != 0) {
            this.ap(al);
        }
        if (this.ah != null) {
            if (this.db != this.ah.a1()) {
                b = true;
            }
            this.db = this.ah.a1();
        }
        Label_0998: {
            if (this.al == al && !this.ap) {
                if (!b) {
                    break Label_0998;
                }
            }
            try {
                boolean b3 = false;
                if (this.al == 5 || this.al == 6) {
                    b3 = true;
                }
                this.a2 = true;
                if (this.al != 0 && !this.dc) {
                    this.ah.aq();
                }
                if (this.al != 5 && this.al != 6) {
                    this.ag.gm();
                }
                if (this.dm != null) {
                    this.ac = this.dm.c();
                    this.au = false;
                    b3 = true;
                    this.dm = null;
                }
                this.al = al;
                this.ag.c2(false);
                if (ji.util.d.av(this.cp)) {
                    this.ah.setVisible(false);
                }
                this.ah.al();
                this.ag.a6();
                if (this.al != 0) {
                    this.ah.r(al);
                }
                this.ah.ay();
                this.ag.t();
                if (this.bk.cj()) {
                    switch (this.al) {
                        case 0: {
                            this.aj.setVisible(false);
                            break;
                        }
                        case 6: {
                            this.aj.setVisible(false);
                            this.aj.b(false);
                            break;
                        }
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5: {
                            this.aj.setVisible(true);
                            this.aj.b(true);
                            break;
                        }
                    }
                }
                else {
                    this.aj.setVisible(false);
                }
                this.fz();
                this.fa();
                switch (this.al) {
                    case 0: {
                        this.dc = false;
                        this.ag.c0(true);
                        this.ag.gz();
                        this.ah.setVisible(false);
                        this.ah.ar();
                        this.ag.b2(false);
                        this.ah.o(this.al);
                        this.ag.b(this.al);
                        if (b3) {
                            this.ag.d(this.ah.av(), this.ag.z());
                            this.ag.a(this.ah.ax(), -1);
                        }
                        if (!this.ag.isVisible()) {
                            this.ag.setVisible(true);
                            break;
                        }
                        break;
                    }
                    case 5:
                    case 6: {
                        ji.util.d.a1(false);
                        this.ag.gy();
                        this.ag.c0(false);
                        this.a5 = true;
                        if (this.ag.isVisible()) {
                            this.ag.setVisible(false);
                        }
                        this.ah.o(this.al);
                        this.ag.b(this.al);
                        this.bo(false);
                        this.ah.setVisible(true);
                        if (this.bi && this.ah.getSize().width > 16) {
                            this.ai.setVisible(true);
                        }
                        if (this.bj && this.ah.getSize().width > 16) {
                            this.aj.setVisible(true);
                            break;
                        }
                        break;
                    }
                    case 1:
                    case 2:
                    case 3:
                    case 4: {
                        this.ag.c2(false);
                        this.ag.c0(false);
                        this.ag.gz();
                        this.ah.o(this.al);
                        this.ag.b(this.al);
                        if (b3) {
                            this.ag.d(this.ah.av(), this.ag.z());
                            this.ag.a(this.ah.ax(), -1);
                        }
                        if (!this.ag.isVisible()) {
                            this.ag.setVisible(true);
                        }
                        if (this.ah.getSize().width > 16) {
                            this.ah.setVisible(true);
                        }
                        if (this.bi && this.ah.getSize().width > 16) {
                            this.ai.setVisible(true);
                        }
                        if (this.bj && this.ah.getSize().width > 16) {
                            this.aj.setVisible(true);
                        }
                        if (this.bh && this.ah.getSize().width > 16) {
                            this.bo(true);
                            break;
                        }
                        break;
                    }
                }
                if (this.bk.cj()) {
                    this.ag(this.af());
                }
                this.fa();
                if (!this.ay && !ji.util.d.b()) {
                    this.d5();
                }
                this.ap = false;
            }
            catch (Exception ex) {}
            finally {
                this.a2 = false;
            }
        }
        if (b2 && this.ag.v()) {
            this.ag.b(new a3(this, 49, 3, al, b));
        }
    }
    
    public void a5(final boolean b) {
        try {
            this.ag.aj(b);
            this.fs();
        }
        catch (Exception ex) {}
    }
    
    public boolean c4() {
        return this.ag.ap();
    }
    
    public final void c5() {
        this.ag.ay();
    }
    
    public final void c6() {
        this.ag.az();
    }
    
    public final void c7() {
        this.ag.a0();
    }
    
    public final void c8() {
        this.ag.a1();
    }
    
    public final void setEnabled(final boolean b) {
        this.b(b, false);
    }
    
    public final void c9() {
        try {
            this.ag.fs();
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n, final int n2, final long n3, final boolean b) {
        try {
            this.ag.a(n, n2, n3, b);
        }
        catch (Exception ex) {}
    }
    
    public final void da() {
        try {
            this.ah.a(new fz(this, 0));
        }
        catch (Exception ex) {}
    }
    
    public final void ag(final int n) {
        try {
            this.ag.w(n);
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    public final void b(final boolean enabled, final boolean b) {
        try {
            this.ah.setEnabled(enabled);
            this.ag.i(enabled, b);
        }
        catch (Exception ex) {}
    }
    
    public String db() throws Exception {
        if (this.ag.df() && this.d2 == 0) {
            if (this.s[this.af() - 1] instanceof String) {
                return this.l.a((String)this.s[this.af() - 1], this.u(), this.bk, this);
            }
            return this.l.a((URL)this.s[this.af() - 1], true, true, this.u(), this.fl(), this.fk(), true, this.bk, this, this.ag);
        }
        else {
            if (ji.util.d.by(this.ag.dg())) {
                return this.a4;
            }
            return this.ag.dg();
        }
    }
    
    public final boolean dc() {
        return this.bb;
    }
    
    public final boolean[] a(final String p0, final int p1, final boolean[] p2, final ad p3, final boolean p4, final boolean p5, final boolean p6, final boolean p7, final boolean p8, final boolean p9, final boolean p10, final Properties p11) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          13
        //     3: iconst_m1      
        //     4: istore          14
        //     6: getstatic       ji/util/d.e:Z
        //     9: ifeq            25
        //    12: aload_0        
        //    13: getfield        ji/document/cw.cp:Ljava/lang/String;
        //    16: ldc             "Debugging thumbs saving...."
        //    18: nop            
        //    19: invokestatic    ji/io/h.d:(Ljava/lang/String;Ljava/lang/String;)V
        //    22: iconst_1       
        //    23: istore          9
        //    25: aconst_null    
        //    26: astore          15
        //    28: invokestatic    ji/util/d.dx:()Z
        //    31: ifne            93
        //    34: getstatic       ji/document/ad.a:Z
        //    37: ifeq            71
        //    40: aload_0        
        //    41: sipush          276
        //    44: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //    47: aload_0        
        //    48: sipush          277
        //    51: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //    54: aload_0        
        //    55: getfield        ji/document/cw.bk:Lji/document/ad;
        //    58: aconst_null    
        //    59: aload_0        
        //    60: aload_0        
        //    61: getfield        ji/document/cw.cp:Ljava/lang/String;
        //    64: iconst_0       
        //    65: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;Z)V
        //    68: goto            93
        //    71: aload_0        
        //    72: sipush          276
        //    75: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //    78: aload_0        
        //    79: sipush          277
        //    82: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //    85: aload_0        
        //    86: aload_0        
        //    87: getfield        ji/document/cw.cp:Ljava/lang/String;
        //    90: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Lji/v1event/af;Ljava/lang/String;)V
        //    93: aload_0        
        //    94: getfield        ji/document/cw.a8:Z
        //    97: ifne            2163
        //   100: aload_0        
        //   101: invokespecial   ji/document/cw.fj:()V
        //   104: aload_0        
        //   105: aload           4
        //   107: invokespecial   ji/document/cw.h:(Ljava/lang/Object;)V
        //   110: iconst_0       
        //   111: istore          16
        //   113: aconst_null    
        //   114: astore          17
        //   116: iload           5
        //   118: ifne            127
        //   121: aload_1        
        //   122: invokestatic    ji/util/d.bh:(Ljava/lang/String;)Ljava/lang/String;
        //   125: astore          17
        //   127: aload           17
        //   129: ifnull          146
        //   132: aload_0        
        //   133: getfield        ji/document/cw.as:Lji/io/q;
        //   136: aload           17
        //   138: invokevirtual   ji/io/q.c:(Ljava/lang/String;)Ljava/lang/String;
        //   141: astore          18
        //   143: goto            155
        //   146: aload_0        
        //   147: getfield        ji/document/cw.as:Lji/io/q;
        //   150: invokevirtual   ji/io/q.n:()Ljava/lang/String;
        //   153: astore          18
        //   155: aconst_null    
        //   156: astore          19
        //   158: aload_0        
        //   159: invokevirtual   ji/document/cw.db:()Ljava/lang/String;
        //   162: astore          15
        //   164: aload           15
        //   166: ifnull          2163
        //   169: new             Ljava/lang/StringBuffer;
        //   172: dup            
        //   173: aload_0        
        //   174: sipush          269
        //   177: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //   180: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   183: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   186: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   189: ldc             " "
        //   191: nop            
        //   192: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   195: aload_0        
        //   196: sipush          270
        //   199: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //   202: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   205: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   208: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   211: astore          20
        //   213: aload_0        
        //   214: sipush          266
        //   217: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //   220: astore          21
        //   222: aload_0        
        //   223: iconst_0       
        //   224: invokevirtual   ji/document/cw.al:(I)V
        //   227: aload_0        
        //   228: aload           20
        //   230: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   233: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   236: ldc             "..."
        //   238: nop            
        //   239: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   242: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //   245: iconst_1       
        //   246: istore          22
        //   248: iconst_0       
        //   249: istore          23
        //   251: iconst_0       
        //   252: istore          24
        //   254: iconst_0       
        //   255: istore          25
        //   257: iload_2        
        //   258: ifle            290
        //   261: iconst_1       
        //   262: istore          24
        //   264: iconst_0       
        //   265: istore          26
        //   267: iload           26
        //   269: aload_3        
        //   270: arraylength    
        //   271: if_icmpge       290
        //   274: aload_3        
        //   275: iload           26
        //   277: baload         
        //   278: ifeq            284
        //   281: iinc            25, 1
        //   284: iinc            26, 1
        //   287: goto            267
        //   290: iconst_0       
        //   291: istore          26
        //   293: aload_0        
        //   294: invokevirtual   ji/document/cw.cb:()Z
        //   297: ifeq            410
        //   300: iload           8
        //   302: ifeq            340
        //   305: iload           25
        //   307: iconst_1       
        //   308: if_icmpne       340
        //   311: iconst_0       
        //   312: istore          27
        //   314: iload           27
        //   316: aload_3        
        //   317: arraylength    
        //   318: if_icmpge       340
        //   321: aload_3        
        //   322: iload           27
        //   324: baload         
        //   325: ifeq            334
        //   328: iload           27
        //   330: iconst_1       
        //   331: iadd           
        //   332: istore          14
        //   334: iinc            27, 1
        //   337: goto            314
        //   340: bipush          44
        //   342: invokestatic    ji/util/i.c:(I)Z
        //   345: ifeq            486
        //   348: aload_0        
        //   349: invokevirtual   ji/document/cw.ce:()Z
        //   352: ifne            486
        //   355: iload           24
        //   357: ifeq            370
        //   360: iload           25
        //   362: iconst_1       
        //   363: if_icmpgt       370
        //   366: iconst_1       
        //   367: goto            371
        //   370: iconst_0       
        //   371: istore          26
        //   373: iload           26
        //   375: ifeq            400
        //   378: aload_0        
        //   379: aload_0        
        //   380: invokevirtual   ji/document/cw.dx:()Lji/annotate/df;
        //   383: iload           5
        //   385: ifne            392
        //   388: iconst_1       
        //   389: goto            393
        //   392: iconst_0       
        //   393: aconst_null    
        //   394: invokevirtual   ji/document/cw.a:(Lji/annotate/df;Z[I)Z
        //   397: ifeq            404
        //   400: iconst_1       
        //   401: goto            405
        //   404: iconst_0       
        //   405: istore          23
        //   407: goto            486
        //   410: aload_0        
        //   411: invokevirtual   ji/document/cw.ae:()I
        //   414: iconst_1       
        //   415: if_icmple       465
        //   418: aload_0        
        //   419: getfield        ji/document/cw.ag:Lji/image/cy;
        //   422: invokevirtual   ji/image/cy.d7:()Z
        //   425: ifeq            486
        //   428: iload           24
        //   430: ifne            455
        //   433: aload_0        
        //   434: aload_0        
        //   435: invokevirtual   ji/document/cw.dx:()Lji/annotate/df;
        //   438: iload           5
        //   440: ifne            447
        //   443: iconst_1       
        //   444: goto            448
        //   447: iconst_0       
        //   448: aconst_null    
        //   449: invokevirtual   ji/document/cw.a:(Lji/annotate/df;Z[I)Z
        //   452: ifeq            459
        //   455: iconst_1       
        //   456: goto            460
        //   459: iconst_0       
        //   460: istore          23
        //   462: goto            486
        //   465: aload_0        
        //   466: aload_0        
        //   467: invokevirtual   ji/document/cw.dx:()Lji/annotate/df;
        //   470: iload           5
        //   472: ifne            479
        //   475: iconst_1       
        //   476: goto            480
        //   479: iconst_0       
        //   480: aconst_null    
        //   481: invokevirtual   ji/document/cw.a:(Lji/annotate/df;Z[I)Z
        //   484: istore          23
        //   486: aload_0        
        //   487: getfield        ji/document/cw.ag:Lji/image/cy;
        //   490: invokevirtual   ji/image/cy.r:()Lji/image/dx;
        //   493: astore          27
        //   495: aload           27
        //   497: getfield        ji/image/dx.cs:Z
        //   500: ifeq            513
        //   503: aload_0        
        //   504: invokevirtual   ji/document/cw.ce:()Z
        //   507: ifne            513
        //   510: iconst_1       
        //   511: istore          23
        //   513: iload           11
        //   515: ifeq            521
        //   518: iconst_1       
        //   519: istore          23
        //   521: aload_1        
        //   522: invokestatic    ji/util/d.bh:(Ljava/lang/String;)Ljava/lang/String;
        //   525: astore          28
        //   527: aload           28
        //   529: invokestatic    ji/util/d.by:(Ljava/lang/String;)Z
        //   532: ifne            564
        //   535: aload           4
        //   537: iconst_2       
        //   538: invokevirtual   ji/document/ad.bi:(I)Z
        //   541: ifne            553
        //   544: sipush          158
        //   547: invokestatic    ji/util/i.c:(I)Z
        //   550: ifeq            564
        //   553: aload           28
        //   555: invokestatic    ji/util/d.cy:(Ljava/lang/String;)Z
        //   558: ifeq            564
        //   561: iconst_1       
        //   562: istore          23
        //   564: iload           9
        //   566: ifeq            572
        //   569: iconst_1       
        //   570: istore          23
        //   572: iload           7
        //   574: ifne            586
        //   577: aload           4
        //   579: iconst_1       
        //   580: invokevirtual   ji/document/ad.bi:(I)Z
        //   583: ifeq            589
        //   586: iconst_1       
        //   587: istore          23
        //   589: iload           23
        //   591: ifeq            1112
        //   594: iconst_0       
        //   595: istore          22
        //   597: aconst_null    
        //   598: astore          29
        //   600: iload_2        
        //   601: ifle            639
        //   604: aload_3        
        //   605: ifnull          639
        //   608: aload_3        
        //   609: arraylength    
        //   610: newarray        Z
        //   612: astore          29
        //   614: iconst_0       
        //   615: istore          30
        //   617: iload           30
        //   619: aload_3        
        //   620: arraylength    
        //   621: if_icmpge       639
        //   624: aload           29
        //   626: iload           30
        //   628: aload_3        
        //   629: iload           30
        //   631: baload         
        //   632: bastore        
        //   633: iinc            30, 1
        //   636: goto            617
        //   639: aload_0        
        //   640: getfield        ji/document/cw.ah:Lji/document/c6;
        //   643: invokevirtual   ji/document/c6.ac:()Ljava/awt/Dimension;
        //   646: astore          30
        //   648: iload           9
        //   650: ifeq            903
        //   653: aload_0        
        //   654: getfield        ji/document/cw.ah:Lji/document/c6;
        //   657: ifnull          903
        //   660: iconst_0       
        //   661: istore          22
        //   663: iconst_1       
        //   664: istore          31
        //   666: aload_0        
        //   667: invokevirtual   ji/document/cw.ae:()I
        //   670: newarray        Z
        //   672: astore          29
        //   674: ldc             "tif"
        //   676: nop            
        //   677: astore          32
        //   679: aload_1        
        //   680: invokestatic    ji/util/d.bi:(Ljava/lang/String;)Ljava/lang/String;
        //   683: astore          33
        //   685: aload           18
        //   687: ldc             "."
        //   689: nop            
        //   690: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //   693: istore          34
        //   695: iconst_0       
        //   696: istore          35
        //   698: iload           35
        //   700: aload           29
        //   702: arraylength    
        //   703: if_icmpge       960
        //   706: aconst_null    
        //   707: astore          36
        //   709: iload_2        
        //   710: iconst_1       
        //   711: if_icmpeq       765
        //   714: new             Ljava/lang/StringBuffer;
        //   717: dup            
        //   718: aload           33
        //   720: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   723: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   726: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //   729: ldc             "_"
        //   731: nop            
        //   732: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   735: iload           31
        //   737: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //   740: ldc             "."
        //   742: nop            
        //   743: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   746: aload           32
        //   748: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //   751: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   754: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   757: astore          36
        //   759: iinc            31, 1
        //   762: goto            768
        //   765: aload_1        
        //   766: astore          36
        //   768: aload_3        
        //   769: ifnull          800
        //   772: iload           35
        //   774: aload_3        
        //   775: arraylength    
        //   776: if_icmpge       791
        //   779: aload           29
        //   781: iload           35
        //   783: aload_3        
        //   784: iload           35
        //   786: baload         
        //   787: bastore        
        //   788: goto            806
        //   791: aload           29
        //   793: iload           35
        //   795: iconst_0       
        //   796: bastore        
        //   797: goto            806
        //   800: aload           29
        //   802: iload           35
        //   804: iconst_1       
        //   805: bastore        
        //   806: aload           29
        //   808: iload           35
        //   810: baload         
        //   811: ifeq            891
        //   814: aload           36
        //   816: aload_0        
        //   817: getfield        ji/document/cw.cp:Ljava/lang/String;
        //   820: invokestatic    ji/io/ac.c:(Ljava/lang/String;Ljava/lang/String;)Z
        //   823: pop            
        //   824: new             Lji/io/ac;
        //   827: dup            
        //   828: aload           36
        //   830: iconst_1       
        //   831: iconst_0       
        //   832: iconst_0       
        //   833: aload           4
        //   835: aload_0        
        //   836: getfield        ji/document/cw.cp:Ljava/lang/String;
        //   839: invokespecial   ji/io/ac.<init>:(Ljava/lang/String;ZZILjava/lang/Object;Ljava/lang/String;)V
        //   842: astore          19
        //   844: aload_0        
        //   845: getfield        ji/document/cw.ag:Lji/image/cy;
        //   848: aload           29
        //   850: aload           19
        //   852: aload_0        
        //   853: invokevirtual   ji/document/cw.f:()Ljava/lang/Object;
        //   856: iload           5
        //   858: iload           6
        //   860: iload           9
        //   862: aload           30
        //   864: getfield        java/awt/Dimension.width:I
        //   867: aload           30
        //   869: getfield        java/awt/Dimension.height:I
        //   872: iload           10
        //   874: aload           12
        //   876: invokevirtual   ji/image/cy.a:([ZLji/io/ac;Ljava/lang/Object;ZZZIIZLjava/util/Properties;)[Z
        //   879: astore          13
        //   881: aload           19
        //   883: aload           4
        //   885: invokevirtual   ji/io/ac.a:(Ljava/lang/Object;)V
        //   888: aconst_null    
        //   889: astore          19
        //   891: aload           29
        //   893: iload           35
        //   895: iconst_0       
        //   896: bastore        
        //   897: iinc            35, 1
        //   900: goto            698
        //   903: new             Lji/io/ac;
        //   906: dup            
        //   907: aload           18
        //   909: iconst_1       
        //   910: iconst_0       
        //   911: iconst_0       
        //   912: aload           4
        //   914: aload_0        
        //   915: getfield        ji/document/cw.cp:Ljava/lang/String;
        //   918: invokespecial   ji/io/ac.<init>:(Ljava/lang/String;ZZILjava/lang/Object;Ljava/lang/String;)V
        //   921: astore          19
        //   923: aload_0        
        //   924: getfield        ji/document/cw.ag:Lji/image/cy;
        //   927: aload           29
        //   929: aload           19
        //   931: aload_0        
        //   932: invokevirtual   ji/document/cw.f:()Ljava/lang/Object;
        //   935: iload           5
        //   937: iload           6
        //   939: iload           9
        //   941: aload           30
        //   943: getfield        java/awt/Dimension.width:I
        //   946: aload           30
        //   948: getfield        java/awt/Dimension.height:I
        //   951: iload           10
        //   953: aload           12
        //   955: invokevirtual   ji/image/cy.a:([ZLji/io/ac;Ljava/lang/Object;ZZZIIZLjava/util/Properties;)[Z
        //   958: astore          13
        //   960: iload           9
        //   962: ifne            1101
        //   965: aload           13
        //   967: ifnull          1101
        //   970: aload           13
        //   972: arraylength    
        //   973: ifle            1002
        //   976: aload_0        
        //   977: aload_0        
        //   978: sipush          272
        //   981: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //   984: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   987: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   990: ldc             "."
        //   992: nop            
        //   993: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   996: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //   999: goto            1112
        //  1002: iload           8
        //  1004: ifne            1095
        //  1007: new             Ljava/lang/StringBuffer;
        //  1010: dup            
        //  1011: aload_0        
        //  1012: sipush          989
        //  1015: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1018: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1021: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1024: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1027: ldc             "\n"
        //  1029: nop            
        //  1030: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1033: aload_0        
        //  1034: sipush          998
        //  1037: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1040: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1043: ldc             "\n"
        //  1045: nop            
        //  1046: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1049: aload_0        
        //  1050: sipush          275
        //  1053: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1056: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1059: ldc             "\n \n"
        //  1061: nop            
        //  1062: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1065: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1068: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1071: astore          31
        //  1073: invokestatic    ji/res/ay.a:()Ljava/lang/String;
        //  1076: aload           31
        //  1078: aload_0        
        //  1079: getfield        ji/document/cw.bk:Lji/document/ad;
        //  1082: aload_0        
        //  1083: aload_0        
        //  1084: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1087: invokestatic    ji/util/d.a:(Ljava/lang/String;Ljava/lang/String;Ljava/awt/Component;Lji/v1event/af;Ljava/lang/String;)Z
        //  1090: istore          22
        //  1092: goto            1112
        //  1095: iconst_0       
        //  1096: istore          22
        //  1098: goto            1112
        //  1101: iload           9
        //  1103: ifne            1112
        //  1106: iconst_0       
        //  1107: istore          22
        //  1109: iconst_1       
        //  1110: istore          16
        //  1112: iload           22
        //  1114: ifeq            1599
        //  1117: iload           9
        //  1119: ifne            1599
        //  1122: new             Lji/io/ac;
        //  1125: dup            
        //  1126: aload           15
        //  1128: iconst_0       
        //  1129: iconst_0       
        //  1130: iconst_0       
        //  1131: aload           4
        //  1133: aload_0        
        //  1134: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1137: invokespecial   ji/io/ac.<init>:(Ljava/lang/String;ZZILjava/lang/Object;Ljava/lang/String;)V
        //  1140: astore          29
        //  1142: aload           29
        //  1144: invokevirtual   ji/io/ac.w:()J
        //  1147: lstore          30
        //  1149: aload           29
        //  1151: invokevirtual   ji/io/ac.s:()Z
        //  1154: ifeq            1164
        //  1157: aload           29
        //  1159: invokevirtual   ji/io/ac.v:()J
        //  1162: lstore          30
        //  1164: ldc2_w          2048
        //  1167: lload           30
        //  1169: bipush          25
        //  1171: i2l            
        //  1172: ldiv           
        //  1173: invokestatic    java/lang/Math.max:(JJ)J
        //  1176: l2i            
        //  1177: istore          32
        //  1179: lconst_0       
        //  1180: lstore          33
        //  1182: lconst_0       
        //  1183: lstore          35
        //  1185: iload           32
        //  1187: sipush          2048
        //  1190: invokestatic    java/lang/Math.min:(II)I
        //  1193: istore          32
        //  1195: iload           32
        //  1197: sipush          10240
        //  1200: invokestatic    java/lang/Math.max:(II)I
        //  1203: istore          32
        //  1205: aload_0        
        //  1206: getfield        ji/document/cw.as:Lji/io/q;
        //  1209: invokevirtual   ji/io/q.n:()Ljava/lang/String;
        //  1212: astore          18
        //  1214: new             Lji/io/ac;
        //  1217: dup            
        //  1218: aload           18
        //  1220: iconst_1       
        //  1221: iconst_0       
        //  1222: iconst_0       
        //  1223: iconst_0       
        //  1224: aload           4
        //  1226: aload_0        
        //  1227: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1230: invokespecial   ji/io/ac.<init>:(Ljava/lang/String;ZZIZLjava/lang/Object;Ljava/lang/String;)V
        //  1233: astore          19
        //  1235: iload           32
        //  1237: newarray        B
        //  1239: astore          37
        //  1241: iconst_0       
        //  1242: istore          38
        //  1244: lload           30
        //  1246: aload_0        
        //  1247: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1250: invokestatic    ji/util/d.a:(JLjava/lang/String;)Ljava/lang/String;
        //  1253: astore          39
        //  1255: lconst_0       
        //  1256: lstore          40
        //  1258: aload_0        
        //  1259: aload           20
        //  1261: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1264: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1267: ldc             "..."
        //  1269: nop            
        //  1270: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1273: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //  1276: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1279: lstore          33
        //  1281: aload           29
        //  1283: aload           37
        //  1285: iconst_0       
        //  1286: iload           32
        //  1288: invokevirtual   ji/io/ac.a:([BII)I
        //  1291: istore          38
        //  1293: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1296: lload           33
        //  1298: lsub           
        //  1299: lstore          35
        //  1301: lload           40
        //  1303: iload           38
        //  1305: i2l            
        //  1306: ladd           
        //  1307: lstore          40
        //  1309: iload           38
        //  1311: ifle            1454
        //  1314: iload           16
        //  1316: ifne            1454
        //  1319: aload_0        
        //  1320: bipush          100
        //  1322: i2l            
        //  1323: lload           40
        //  1325: lmul           
        //  1326: lload           30
        //  1328: ldiv           
        //  1329: l2i            
        //  1330: invokevirtual   ji/document/cw.al:(I)V
        //  1333: aload_0        
        //  1334: new             Ljava/lang/StringBuffer;
        //  1337: dup            
        //  1338: aload           20
        //  1340: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1343: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1346: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1349: ldc             " "
        //  1351: nop            
        //  1352: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1355: lload           40
        //  1357: aload_0        
        //  1358: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1361: invokestatic    ji/util/d.a:(JLjava/lang/String;)Ljava/lang/String;
        //  1364: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1367: ldc             " "
        //  1369: nop            
        //  1370: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1373: aload           21
        //  1375: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1378: ldc             " "
        //  1380: nop            
        //  1381: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1384: aload           39
        //  1386: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1389: ldc             "..."
        //  1391: nop            
        //  1392: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1395: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1398: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1401: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //  1404: aload           19
        //  1406: aload           37
        //  1408: iconst_0       
        //  1409: iload           38
        //  1411: invokevirtual   ji/io/ac.b:([BII)V
        //  1414: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1417: lstore          33
        //  1419: aload           29
        //  1421: aload           37
        //  1423: iconst_0       
        //  1424: iload           32
        //  1426: invokevirtual   ji/io/ac.a:([BII)I
        //  1429: istore          38
        //  1431: invokestatic    java/lang/System.currentTimeMillis:()J
        //  1434: lload           33
        //  1436: lsub           
        //  1437: lstore          35
        //  1439: lload           40
        //  1441: iload           38
        //  1443: i2l            
        //  1444: ladd           
        //  1445: lstore          40
        //  1447: aload_0        
        //  1448: invokespecial   ji/document/cw.fa:()V
        //  1451: goto            1309
        //  1454: iload           16
        //  1456: ifne            1482
        //  1459: aload_0        
        //  1460: aload_0        
        //  1461: sipush          272
        //  1464: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1467: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1470: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1473: ldc             "."
        //  1475: nop            
        //  1476: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1479: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //  1482: aload_0        
        //  1483: invokevirtual   ji/document/cw.f:()Ljava/lang/Object;
        //  1486: astore          42
        //  1488: aload_0        
        //  1489: getfield        ji/document/cw.ag:Lji/image/cy;
        //  1492: ifnull          1516
        //  1495: aload           42
        //  1497: ifnull          1516
        //  1500: aload_0        
        //  1501: getfield        ji/document/cw.ag:Lji/image/cy;
        //  1504: aload           42
        //  1506: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //  1509: aload_0        
        //  1510: invokevirtual   ji/document/cw.af:()I
        //  1513: invokevirtual   ji/image/cy.a:(Ljava/lang/String;I)V
        //  1516: getstatic       ji/util/d.bp:I
        //  1519: iconst_1       
        //  1520: iadd           
        //  1521: putstatic       ji/util/d.bp:I
        //  1524: iload           26
        //  1526: ifeq            1535
        //  1529: aload_3        
        //  1530: astore          13
        //  1532: goto            1566
        //  1535: aload_0        
        //  1536: invokevirtual   ji/document/cw.ae:()I
        //  1539: newarray        Z
        //  1541: astore          13
        //  1543: iconst_0       
        //  1544: istore          43
        //  1546: iload           43
        //  1548: aload           13
        //  1550: arraylength    
        //  1551: if_icmpge       1566
        //  1554: aload           13
        //  1556: iload           43
        //  1558: iconst_1       
        //  1559: bastore        
        //  1560: iinc            43, 1
        //  1563: goto            1546
        //  1566: jsr             1580
        //  1569: goto            1599
        //  1572: astore          44
        //  1574: jsr             1580
        //  1577: aload           44
        //  1579: athrow         
        //  1580: astore          45
        //  1582: aload           29
        //  1584: aload           4
        //  1586: invokevirtual   ji/io/ac.a:(Ljava/lang/Object;)V
        //  1589: aconst_null    
        //  1590: astore          29
        //  1592: goto            1597
        //  1595: astore          46
        //  1597: ret             45
        //  1599: aload_0        
        //  1600: invokespecial   ji/document/cw.fa:()V
        //  1603: aload_0        
        //  1604: iconst_0       
        //  1605: invokevirtual   ji/document/cw.al:(I)V
        //  1608: jsr             1653
        //  1611: goto            2163
        //  1614: astore          20
        //  1616: aload_0        
        //  1617: bipush          31
        //  1619: ldc             "SaveDocument Failed(1)"
        //  1621: nop            
        //  1622: iconst_0       
        //  1623: invokevirtual   ji/document/cw.a:(ILjava/lang/String;Z)Ljava/lang/String;
        //  1626: pop            
        //  1627: aload           20
        //  1629: ldc             "SaveDocument"
        //  1631: nop            
        //  1632: aload_0        
        //  1633: iconst_5       
        //  1634: aload_0        
        //  1635: aload_0        
        //  1636: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1639: invokestatic    ji/util/d.a:(Ljava/lang/Exception;Ljava/lang/String;Ljava/awt/Component;ILji/v1event/af;Ljava/lang/String;)V
        //  1642: aload           20
        //  1644: athrow         
        //  1645: astore          47
        //  1647: jsr             1653
        //  1650: aload           47
        //  1652: athrow         
        //  1653: astore          48
        //  1655: aload           19
        //  1657: ifnull          1670
        //  1660: aload           19
        //  1662: aload           4
        //  1664: invokevirtual   ji/io/ac.a:(Ljava/lang/Object;)V
        //  1667: aconst_null    
        //  1668: astore          19
        //  1670: iload           16
        //  1672: ifeq            1688
        //  1675: aload           18
        //  1677: aload_0        
        //  1678: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1681: invokestatic    ji/io/ac.c:(Ljava/lang/String;Ljava/lang/String;)Z
        //  1684: pop            
        //  1685: goto            2096
        //  1688: iload           9
        //  1690: ifne            2057
        //  1693: aload_1        
        //  1694: astore          49
        //  1696: iload           5
        //  1698: ifeq            1874
        //  1701: aload_0        
        //  1702: sipush          1100
        //  1705: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1708: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1711: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1714: ldc             "..."
        //  1716: nop            
        //  1717: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1720: invokestatic    ji/util/e.ag:(Ljava/lang/String;)V
        //  1723: iload           14
        //  1725: iconst_m1      
        //  1726: if_icmpeq       1824
        //  1729: aload           49
        //  1731: ldc             "?"
        //  1733: nop            
        //  1734: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //  1737: iconst_m1      
        //  1738: if_icmpne       1784
        //  1741: new             Ljava/lang/StringBuffer;
        //  1744: dup            
        //  1745: aload           49
        //  1747: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1750: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1753: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1756: ldc             "?"
        //  1758: nop            
        //  1759: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1762: ldc             "page="
        //  1764: nop            
        //  1765: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1768: iload           14
        //  1770: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //  1773: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1776: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1779: astore          49
        //  1781: goto            1824
        //  1784: new             Ljava/lang/StringBuffer;
        //  1787: dup            
        //  1788: aload           49
        //  1790: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1793: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1796: invokespecial   java/lang/StringBuffer.<init>:(Ljava/lang/String;)V
        //  1799: ldc             "&"
        //  1801: nop            
        //  1802: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1805: ldc             "page="
        //  1807: nop            
        //  1808: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
        //  1811: iload           14
        //  1813: invokevirtual   java/lang/StringBuffer.append:(I)Ljava/lang/StringBuffer;
        //  1816: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1819: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1822: astore          49
        //  1824: aload_0        
        //  1825: aload           49
        //  1827: aload           4
        //  1829: invokevirtual   ji/document/ad.kx:()I
        //  1832: aload_0        
        //  1833: aload           18
        //  1835: invokespecial   ji/document/cw.a:(Ljava/lang/String;ILji/v1event/af;Ljava/lang/String;)Z
        //  1838: istore          50
        //  1840: iload           50
        //  1842: ifne            2066
        //  1845: aload_0        
        //  1846: aload_0        
        //  1847: sipush          1101
        //  1850: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1853: invokevirtual   ji/document/cw.l:(Ljava/lang/String;)V
        //  1856: aconst_null    
        //  1857: astore          13
        //  1859: new             Lji/net/jiServerException;
        //  1862: dup            
        //  1863: aload_0        
        //  1864: sipush          1101
        //  1867: invokespecial   ji/document/cw.ao:(I)Ljava/lang/String;
        //  1870: invokespecial   ji/net/jiServerException.<init>:(Ljava/lang/String;)V
        //  1873: athrow         
        //  1874: aload           18
        //  1876: aload_0        
        //  1877: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  1880: invokestatic    ji/sec/g.b:(Ljava/lang/String;Ljava/lang/String;)V
        //  1883: aload           4
        //  1885: invokevirtual   ji/document/ad.a5:()Ljava/lang/String;
        //  1888: astore          50
        //  1890: aload           50
        //  1892: ifnull          1975
        //  1895: aload           49
        //  1897: ldc             "."
        //  1899: nop            
        //  1900: invokevirtual   java/lang/String.indexOf:(Ljava/lang/String;)I
        //  1903: istore          51
        //  1905: iload           51
        //  1907: ifle            1942
        //  1910: aload           49
        //  1912: iconst_0       
        //  1913: iload           51
        //  1915: iconst_1       
        //  1916: iadd           
        //  1917: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //  1920: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1923: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1926: aload           50
        //  1928: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1931: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1934: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1937: astore          49
        //  1939: goto            1975
        //  1942: aload           49
        //  1944: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1947: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1950: ldc             "."
        //  1952: nop            
        //  1953: aload           50
        //  1955: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1958: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1961: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1964: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1967: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1970: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1973: astore          49
        //  1975: iconst_0       
        //  1976: istore          51
        //  1978: aload_0        
        //  1979: getfield        ji/document/cw.ag:Lji/image/cy;
        //  1982: aload           49
        //  1984: invokevirtual   ji/image/cy.c:(Ljava/lang/String;)Z
        //  1987: istore          51
        //  1989: goto            1999
        //  1992: astore          52
        //  1994: aload           52
        //  1996: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //  1999: iload           51
        //  2001: ifeq            2011
        //  2004: aload_0        
        //  2005: getfield        ji/document/cw.ag:Lji/image/cy;
        //  2008: invokevirtual   ji/image/cy.w:()V
        //  2011: aload           49
        //  2013: aload_0        
        //  2014: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  2017: invokestatic    ji/io/ac.c:(Ljava/lang/String;Ljava/lang/String;)Z
        //  2020: pop            
        //  2021: goto            2026
        //  2024: astore          52
        //  2026: aload           18
        //  2028: aload           49
        //  2030: aload_0        
        //  2031: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  2034: iconst_1       
        //  2035: invokestatic    ji/io/ac.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V
        //  2038: aload           49
        //  2040: aload_0        
        //  2041: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  2044: invokestatic    ji/sec/g.b:(Ljava/lang/String;Ljava/lang/String;)V
        //  2047: aload_0        
        //  2048: getfield        ji/document/cw.ag:Lji/image/cy;
        //  2051: invokevirtual   ji/image/cy.x:()V
        //  2054: goto            2066
        //  2057: aload           18
        //  2059: aload_0        
        //  2060: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  2063: invokestatic    ji/sec/g.b:(Ljava/lang/String;Ljava/lang/String;)V
        //  2066: aload           13
        //  2068: ifnonnull       2085
        //  2071: aload_0        
        //  2072: bipush          31
        //  2074: ldc             "SaveDocument Failed(2)"
        //  2076: nop            
        //  2077: iconst_0       
        //  2078: invokevirtual   ji/document/cw.a:(ILjava/lang/String;Z)Ljava/lang/String;
        //  2081: pop            
        //  2082: goto            2096
        //  2085: aload_0        
        //  2086: bipush          42
        //  2088: ldc             "SaveDocument Complete"
        //  2090: nop            
        //  2091: iconst_0       
        //  2092: invokevirtual   ji/document/cw.a:(ILjava/lang/String;Z)Ljava/lang/String;
        //  2095: pop            
        //  2096: jsr             2126
        //  2099: goto            2157
        //  2102: astore          49
        //  2104: aload_0        
        //  2105: bipush          31
        //  2107: ldc             "SaveDocument Failed(2)"
        //  2109: nop            
        //  2110: iconst_0       
        //  2111: invokevirtual   ji/document/cw.a:(ILjava/lang/String;Z)Ljava/lang/String;
        //  2114: pop            
        //  2115: aload           49
        //  2117: athrow         
        //  2118: astore          53
        //  2120: jsr             2126
        //  2123: aload           53
        //  2125: athrow         
        //  2126: astore          54
        //  2128: aload           18
        //  2130: aload_0        
        //  2131: getfield        ji/document/cw.cp:Ljava/lang/String;
        //  2134: invokestatic    ji/io/ac.c:(Ljava/lang/String;Ljava/lang/String;)Z
        //  2137: pop            
        //  2138: goto            2143
        //  2141: astore          55
        //  2143: aload_0        
        //  2144: getfield        ji/document/cw.ag:Lji/image/cy;
        //  2147: invokevirtual   ji/image/cy.x:()V
        //  2150: goto            2155
        //  2153: astore          55
        //  2155: ret             54
        //  2157: aload_0        
        //  2158: invokespecial   ji/document/cw.fp:()V
        //  2161: ret             48
        //  2163: aload           13
        //  2165: areturn        
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  1142   1572   1572   1580   Any
        //  1582   1592   1595   1597   Ljava/lang/Exception;
        //  169    1608   1614   1645   Ljava/lang/Exception;
        //  169    1645   1645   1653   Any
        //  1978   1989   1992   1999   Ljava/lang/Exception;
        //  2011   2021   2024   2026   Ljava/lang/Exception;
        //  1655   2096   2102   2118   Ljava/lang/Exception;
        //  1655   2118   2118   2126   Any
        //  2128   2138   2141   2143   Ljava/lang/Exception;
        //  2143   2150   2153   2155   Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Inconsistent stack size at #3246 (coming from #2686).
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
    
    public final void setBackground(final Color background) {
        try {
            super.setBackground(background);
            ji.util.e.a(this, background);
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    private boolean a(final String s, final int n, final af af, final String s2) throws Exception {
        bh bh = null;
        boolean a = true;
        ac ac = null;
        try {
            int n2;
            try {
                ac = new ac(s2, false, false, 0, this.bk, this.cp);
                n2 = (int)ac.w();
            }
            catch (Exception ex4) {
                ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Failed to open ").append(s2).append(" for reading"))));
                return false;
            }
            final Object b = ji.util.d.b(ji.util.e.am(), s, this.cp);
            URL url = null;
            if (b instanceof URL) {
                url = (URL)b;
            }
            if (url == null) {
                ji.io.h.d(this.cp, "Failed to validate URL: ".concat(String.valueOf(String.valueOf(s))));
                return false;
            }
            if (n == 0) {
                a = ji.util.bh.a(url.toString(), s2, this.cp, af, this.bk, this.bk);
            }
            else {
                final boolean b2 = n == 2;
                try {
                    bh = new bh(url, this.cp, b2, n2, this.bk, false);
                }
                catch (Exception ex5) {
                    ji.io.h.d(this.cp, "Failed to make connection to ".concat(String.valueOf(String.valueOf(url))));
                    return false;
                }
                if (ji.util.d.cy()) {
                    if (b2) {
                        ji.io.h.d(this.cp, "Sending image data to servlet ".concat(String.valueOf(String.valueOf(url))));
                    }
                    else {
                        ji.io.h.d(this.cp, "Sending image using POST ".concat(String.valueOf(String.valueOf(url))));
                    }
                }
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Sending total of ").append(n2).append(" bytes..."))));
                }
                try {
                    if (b2) {
                        bh.a(af, ac);
                    }
                    else {
                        bh.a(af, ac, false);
                    }
                }
                catch (Exception ex) {
                    ji.io.h.d(this.cp, "Send failed ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
                    if (af != null) {
                        af.a(new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.e("imageSaveFailed", this.cp))).concat(".")));
                    }
                    a = false;
                }
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.cp, "Waiting for reply...");
                }
                String s3 = bh.b();
                String s4 = ji.util.bh.a(s3);
                boolean b3 = !s4.equals("") & (s4 == null || !ji.util.bh.b(s4));
                if (!b3) {
                    try {
                        s3 = bh.a(1024);
                    }
                    catch (IOException ex6) {
                        final String headerField = bh.a().getHeaderField(0);
                        if (headerField != null) {
                            throw new IOException("Error saving annotations, server returned: ".concat(String.valueOf(String.valueOf(headerField))));
                        }
                        throw new IOException("Error saving annotations");
                    }
                    if (s3 != null) {
                        if (s3.toLowerCase().indexOf("doctype html") >= 0) {
                            s3 = "<ok>";
                        }
                        s4 = ji.util.bh.a(s3);
                        b3 = (!s4.equals("") && !ji.util.bh.b(s4));
                    }
                }
                if (b3) {
                    a = false;
                    final String s5 = "";
                    ji.io.h.d(this.cp, "Annotation Save: Server response: ".concat(String.valueOf(String.valueOf(s4))));
                    ji.io.h.d(this.cp, String.valueOf(String.valueOf(s5)).concat(String.valueOf(String.valueOf(this.ao(1197)))));
                    if (ji.util.d.by(s3)) {
                        ji.io.h.d(this.cp, "Reply received: <EMPTY>");
                    }
                    else {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Reply received (").append(s3.length()).append(" bytes): ").append(s3))));
                    }
                }
                else if (ji.util.d.cy() && b2) {
                    if (ji.util.d.by(s3)) {
                        ji.io.h.d(this.cp, "Reply received: <EMPTY>");
                    }
                    else {
                        ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Reply received (").append(s3.length()).append(" bytes): ").append(s3))));
                    }
                }
            }
        }
        finally {
            try {
                if (ac != null) {
                    try {
                        ac.a(this.bk);
                    }
                    catch (Exception ex7) {}
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
            try {
                if (bh != null) {
                    bh.e();
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
        }
        return a;
    }
    
    public final Color getBackground() {
        return super.getBackground();
    }
    
    public final boolean a(final EventObject eventObject) {
        return this.ag != null && this.ag.a(eventObject);
    }
    
    public final void a(final af af) {
        if (this.ad != null && this.ad.a(af)) {
            this.ad.b(af);
        }
    }
    
    public void setRightMouseEmulator(final boolean rightMouseEmulator) {
        if (this.ag != null) {
            this.ag.setRightMouseEmulator(rightMouseEmulator);
        }
    }
    
    public boolean isRightMouseEmulator() {
        return this.ag != null && this.ag.isRightMouseEmulator();
    }
    
    public void addToolTipListener(final c9 c9) {
        if (this.ag != null) {
            this.ag.addToolTipListener(c9);
        }
    }
    
    public void removeToolTipListener(final c9 c9) {
        if (this.ag != null) {
            this.ag.removeToolTipListener(c9);
        }
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        if (this.ag != null) {
            this.ag.addMouseListener(mouseListener);
        }
        if (this.ah != null) {
            this.ah.addMouseListener(mouseListener);
        }
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        if (this.ag != null) {
            this.ag.removeMouseListener(mouseListener);
        }
        if (this.ah != null) {
            this.ah.removeMouseListener(mouseListener);
        }
    }
    
    public void addMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.ag != null) {
            this.ag.addMouseMotionListener(mouseMotionListener);
        }
        if (this.ah != null) {
            this.ah.addMouseMotionListener(mouseMotionListener);
        }
    }
    
    public void removeMouseMotionListener(final MouseMotionListener mouseMotionListener) {
        if (this.ag != null) {
            this.ag.removeMouseMotionListener(mouseMotionListener);
        }
        if (this.ah != null) {
            this.ah.removeMouseMotionListener(mouseMotionListener);
        }
    }
    
    public boolean c(final Object o) {
        return this.ah != null && this.ah.a(o);
    }
    
    public final void b(final af af) {
        if (this.ad == null) {
            this.ad = new c("jiDocHandler1", 2);
        }
        if (!this.ad.a(af)) {
            this.ad.c(af);
        }
    }
    
    public final void a(final ah ah) {
        if (this.ao != null && this.ao.a(ah)) {
            this.ao.b(ah);
        }
    }
    
    public final void b(final ah ah) {
        if (this.ao == null) {
            this.ao = new c("jiDocHandler2c", 2);
        }
        if (!this.ao.a(ah)) {
            this.ao.c(ah);
        }
    }
    
    public final void a(final ag ag) {
        if (this.an != null && this.an.a(ag)) {
            this.an.b(ag);
        }
    }
    
    public final void a(final b b) {
        if (this.ag != null) {
            this.ag.a(b);
        }
    }
    
    public final void b(final b b) {
        if (this.ag != null) {
            this.ag.b(b);
        }
    }
    
    public final void b(final ag ag) {
        if (this.an == null) {
            this.an = new c("jiDocHandler2", 2);
        }
        if (!this.an.a(ag)) {
            this.an.c(ag);
        }
    }
    
    public final void a(final ae ae) {
        if (this.am != null && this.am.a(ae)) {
            this.am.b(ae);
        }
    }
    
    public final void b(final ae ae) {
        if (this.am == null) {
            this.am = new c("jiDocHandler3", 2);
        }
        if (!this.am.a(ae)) {
            this.am.c(ae);
        }
    }
    
    public void releaseResources() {
        if (!this.u) {
            try {
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel1");
                }
                this.u = true;
                try {
                    this.f5();
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
                this.ef();
                if (this.b8 != null) {
                    this.b8.a();
                    this.b8 = null;
                }
                this.bo = null;
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel2");
                }
                if (this.ag != null) {
                    if (ji.util.d.ao) {
                        ji.io.h.d(this.cp, "Doc-Rel2a");
                    }
                    this.ag.releaseResources();
                    if (ji.util.d.ao) {
                        ji.io.h.d(this.cp, "Doc-Rel2b");
                    }
                    this.fa();
                    this.remove(this.ag);
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel3");
                }
                if (this.ah != null) {
                    this.ah.releaseResources();
                    this.remove(this.ah);
                }
                if (this.do != null) {
                    this.do.a(this);
                    this.do.g();
                    this.do = null;
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel4");
                }
                try {
                    if (ji.document.cw.j != null) {
                        ji.document.cw.j.ab();
                        ji.document.cw.j = null;
                    }
                }
                catch (Exception ex2) {}
                this.dg = null;
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel51");
                }
                if (this.ad != null) {
                    this.ad.c();
                }
                if (this.an != null) {
                    this.an.c();
                }
                if (this.ao != null) {
                    this.ao.c();
                }
                if (this.am != null) {
                    this.am.c();
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel6");
                }
                if (this.ag != null) {
                    this.ag.a((af)this);
                    this.ag.bj();
                    this.ag.a((ag)this);
                    this.ag.a((ah)this);
                    this.ag.a((ActionListener)this);
                    this.remove(this.ag);
                    this.ag.releaseResources();
                    this.ag = null;
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel7");
                }
                if (this.ah != null) {
                    this.ah.a((af)this);
                    this.ah.a((ag)this);
                    this.ah.a((ae)this);
                    this.ah.a((ActionListener)this);
                    this.ah.a((b)this.bk);
                    this.ah.a((ActionListener)this.bk);
                    this.remove(this.ah);
                    this.ah.releaseResources();
                    this.ah = null;
                }
                if (this.ai != null) {
                    if (this.c6) {
                        this.remove(this.ai);
                        this.c6 = false;
                    }
                    this.ai.a((ActionListener)this.bk);
                    this.ai.releaseResources();
                    this.ai = null;
                }
                if (this.aj != null) {
                    if (this.c7) {
                        this.remove(this.aj);
                        this.c7 = false;
                    }
                    this.aj.a(this.bk);
                    this.aj.b((cx)this);
                    this.aj.releaseResources();
                    this.aj = null;
                }
                if (this.dn != null) {
                    this.dn.a();
                    this.dn = null;
                }
                if (this.ak != null) {
                    if (this.c8) {
                        this.bo(false);
                        this.remove(this.ak);
                        this.c8 = false;
                    }
                    this.ak.a(this);
                    this.ak.releaseResources();
                    this.ak = null;
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel8");
                }
                if (this.e != null) {
                    this.e.a(this.bk);
                    this.e = null;
                }
                if (ji.util.d.ao) {
                    ji.io.h.d(this.cp, "Doc-Rel9a");
                }
                super.releaseResources();
            }
            catch (Exception ex3) {}
        }
        this.bk = null;
        try {
            if (this.l != null) {
                this.l.a();
            }
        }
        catch (Exception ex4) {}
        if (ji.util.d.ao) {
            ji.io.h.d(this.cp, "Doc-Rel10");
        }
    }
    
    private final void fe() {
        if (!this.c6 && this.ai != null) {
            this.add(this.ai);
            this.c6 = true;
        }
    }
    
    private final void ff() {
        if (!this.c7 && this.aj != null) {
            this.add(this.aj);
            this.c7 = true;
        }
    }
    
    private final void fg() {
        try {
            if (!this.c8 && this.ak != null) {
                this.add(this.ak);
                this.c8 = true;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void bo(final boolean b) {
        try {
            if (this.c9 != b) {
                this.c9 = b;
                this.ak.setVisible(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a6(final boolean b) {
        try {
            if (this.ah != null) {
                this.ah.s(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean dd() {
        try {
            if (this.ah != null) {
                return this.ah.a1();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public final void a(final c dg) {
        try {
            this.dg = dg;
            if (this.ag != null) {
                this.ag.a(dg);
            }
            if (this.ah != null) {
                this.ah.a(dg);
            }
        }
        catch (Exception ex) {}
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(this.ae, this.af);
    }
    
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public final void a7(final boolean b) {
        try {
            if (this.ah != null) {
                this.ah.i(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final Dimension dimension) {
        try {
            this.ah.a(dimension);
        }
        catch (Exception ex) {}
    }
    
    public Dimension de() {
        return this.ah.ac();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        try {
            this.bb(false);
            this.getBounds();
            super.setBounds(n, n2, n3, n4);
            if (!this.a2) {
                if (n >= 0 && n2 >= 0 && n3 > 0 && n4 > 0) {
                    this.fh();
                }
            }
            else if (n >= 0 && n2 >= 0 && n3 > 0 && n4 > 0) {
                this.bb(true);
                this.fz();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void fh() {
        if (ji.util.i.c(291)) {
            if (this.cs == null) {
                this.cs = new z3();
                new bb(this.cp, this.cs).start();
            }
            else {
                this.cs.a();
            }
        }
        else {
            ji.util.e.a(new z4(this), true, this.cp);
        }
    }
    
    public final void setBounds(final Rectangle rectangle) {
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private final void h(final Object o) throws Exception {
        if (this.as == null) {
            this.as = ji.io.q.a(o, this.cp);
            if (ji.util.i.c(110)) {
                if (ji.document.cw.aw <= 1 && ji.util.d.dn()) {
                    this.as.j();
                }
            }
            else if (!ji.util.i.c(69) && ji.document.cw.aw <= 1 && ji.util.d.dn()) {
                this.as.j();
            }
        }
    }
    
    public final void df() {
        if (!ji.util.i.c(110)) {
            this.fi();
        }
    }
    
    private final void fi() {
        try {
            if (ji.util.d.dn()) {
                this.as.j();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void fj() throws Exception {
        if (this.c == null) {
            throw new Exception(this.ao(1084));
        }
    }
    
    public final void a(final o7[] d) {
        this.d = d;
    }
    
    public final o7[] dg() {
        if (this.d != null) {
            return this.d;
        }
        if (this.ag != null) {
            return this.ag.c1();
        }
        return null;
    }
    
    private final String fk() {
        if (this.cb()) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(1095)))).append(" ").append(this.d0).append("...")));
        }
        return this.ao(1093);
    }
    
    private final String fl() {
        if (this.cb()) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(1094)))).append(" ").append(this.d0).append("...")));
        }
        return this.ao(1092);
    }
    
    public final boolean dh() {
        return this.ag != null && this.ag.cp();
    }
    
    private final URL a(final URL url, final int n) throws Exception {
        URL url2 = url;
        try {
            boolean b = true;
            final String concat = "".concat(String.valueOf(String.valueOf(url)));
            if (concat.toLowerCase().indexOf("notfound.tif") >= 0) {
                b = false;
            }
            else if (concat.toLowerCase().indexOf("notfound.txt") >= 0) {
                b = false;
            }
            else if (concat.toLowerCase().indexOf("openerror.tif") >= 0) {
                b = false;
            }
            else if (concat.toLowerCase().indexOf("openerror.txt") >= 0) {
                b = false;
            }
            if (b) {
                switch (n) {
                    case 1: {
                        url2 = new URL(String.valueOf(String.valueOf(new StringBuffer("").append(url).append(ji.document.ad.d))));
                        break;
                    }
                }
                return url2;
            }
            return url;
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return url;
        }
    }
    
    private void bp(final boolean b) {
        if (this.ag != null) {
            this.ag.bp(b);
        }
        if (this.ah != null) {
            this.ah.y(b);
        }
    }
    
    private final void fm() {
        if (this.l == null) {
            (this.l = new a0(this.bk, this.cp)).b(this);
        }
        if (this.bk.bi(43) && this.m == null && this.l != null) {
            this.m = this.l.a(this.bk, this.cp, "thumbs");
        }
        if (ji.util.i.c(86)) {
            if (this.l != null) {
                this.l.f(true);
            }
            if (this.m != null) {
                this.m.f(true);
            }
        }
    }
    
    private final a0 fn() {
        if (this.bk.bi(43)) {
            return this.m;
        }
        return this.l;
    }
    
    private final void b(final Object o, int n, final Object[] array, final Object o2, final Object[] array2, final ad ad, final boolean b, final int n2, final boolean b2) throws Exception {
        this.h = null;
        if (this.cd) {
            return;
        }
        dg e = null;
        String g = null;
        String j = "None";
        String i = "";
        String eo = "Undefined";
        String m = null;
        boolean b3 = false;
        long q = 0L;
        long r = 0L;
        long p9 = 0L;
        this.e8();
        if (ji.document.cw.a6 == null) {
            ji.document.cw.a6 = new at(this.cp);
        }
        try {
            while (this.ay) {
                ji.util.d.b(100, 137, this.cp);
            }
            while (this.av) {
                ji.util.d.b(100, 138, this.cp);
            }
            this.ay = true;
            if (this.e != null) {
                this.a(ad, false);
            }
            this.fm();
            this.bg = false;
            this.d0 = n;
            this.d2 = 0;
            this.eh = null;
            this.d4 = false;
            this.bb = b;
            this.dr = false;
            if (array != null) {
                if (array.length > 1) {
                    this.dr = true;
                }
                this.eg = new boolean[array.length];
            }
            boolean b4 = false;
            if (o instanceof Image) {
                this.a4 = "Import";
            }
            else {
                try {
                    if (ji.util.d.dr() && !ji.util.d.by(o.toString())) {
                        ji.io.h.d(this.cp, "");
                        ji.io.h.d(this.cp, "Opening ...".concat(String.valueOf(String.valueOf(o))));
                        ji.io.h.d(this.cp, "");
                    }
                }
                catch (Exception ex4) {}
                ji.util.d.mt.ak = 0L;
                ji.util.d.mt.u = 0L;
                ji.util.d.mt.v = 0L;
                ji.util.d.mt.w = 0L;
                if (!b2) {
                    this.bp(false);
                }
                try {
                    if (o instanceof String) {
                        String substring = (String)o;
                        final int index = substring.indexOf("#");
                        if (index >= 0) {
                            substring = substring.substring(0, index);
                        }
                        this.a4 = this.l.a(substring, this.u(), ad, this);
                    }
                    else {
                        this.a4 = this.l.a(this.a((URL)o, n2), true, true, this.u(), this.fl(), this.fk(), true, ad, this, this.ag);
                        g = this.l.g();
                    }
                }
                catch (Exception ex) {
                    if (!this.bp) {
                        b3 = true;
                        String s;
                        if (ex instanceof FileNotFoundException) {
                            s = ji.util.e.a6();
                        }
                        else {
                            s = ji.util.e.a5();
                        }
                        if (ji.util.d.b() || ad.cz()) {
                            throw ex;
                        }
                        if (!ji.util.d.by(s)) {
                            throw new FileNotFoundException(ex.getMessage());
                        }
                        if (ji.util.d.do()) {
                            throw ex;
                        }
                        this.a4 = ji.util.e.a(ex, o, ad, this.cp);
                        this.bp(true);
                    }
                }
                q = this.l.q();
                r = this.l.r();
                p9 = this.l.p();
                if (!this.bp) {
                    if (this.ag != null && this.ag.cp()) {
                        b4 = true;
                    }
                    if (this.ah != null && this.ah.a6()) {
                        b4 = true;
                    }
                    i = this.l.i();
                    if (ji.util.e.u()) {
                        this.eh = this.l.l();
                        m = this.l.m();
                    }
                    else {
                        final String l = this.l.l();
                        if (!ji.util.d.by(l)) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("** HTTP custom paramaters are only available for Pro (").append(l).append(") **"))));
                        }
                    }
                    if (!b4) {
                        j = this.l.j();
                        eo = this.l.k();
                    }
                    else {
                        j = (eo = ji.util.d.bh(this.a4));
                    }
                    if (ji.util.d.dr()) {
                        ji.io.h.d(this.cp, "File meta : Time = ".concat(String.valueOf(String.valueOf(i))));
                        ji.io.h.d(this.cp, "File meta : Mime(1) = ".concat(String.valueOf(String.valueOf(j))));
                        ji.io.h.d(this.cp, "File meta : Mime(2) = ".concat(String.valueOf(String.valueOf(eo))));
                        ji.io.h.d(this.cp, "File meta : Custom = ".concat(String.valueOf(String.valueOf(this.eh))));
                        if (!ji.util.d.by(m)) {
                            ji.io.h.d(this.cp, "File meta : Server Version = ".concat(String.valueOf(String.valueOf(m))));
                        }
                    }
                }
            }
            long t = 0L;
            if (ji.util.i.c(86)) {
                t = this.l.t();
                this.l.f(false);
            }
            if (j != null && !b3 && !this.bp && (ji.util.d.bc(j.toLowerCase()).equals("html") || ji.util.d.bc(j.toLowerCase()).equals("htm")) && ji.util.d.eg() && o instanceof URL && ji.util.i.c(17)) {
                ji.util.e.a((URL)o, String.valueOf(String.valueOf(ji.res.ay.a())).concat("HTML"), ad, this, this.cp, "DocHShowHTMLError");
                throw new Exception(this.ao(659));
            }
            if (this.a4 != null && !this.bp) {
                if (!(o instanceof Image)) {
                    this.e = new ac(this.a4, false, false, 0, ad, this.cp);
                }
                this.c = o;
                this.s = array;
                this.bt = o2;
                this.bu = array2;
                this.p(o.toString());
                if (o != null && !ad.km()) {
                    c c = null;
                    if (this.ag == null) {
                        return;
                    }
                    this.ag.m(0);
                    this.ag.bw(false);
                    dx eb;
                    if (o instanceof Image) {
                        this.g = new fx();
                        eb = ((fx)this.g).a((Image)o, o.toString(), this, ad);
                        if (eb != null) {
                            eb.ak = this.cy;
                        }
                    }
                    else {
                        this.f6();
                        if (this.eh != null) {
                            c = ji.net.a0.c(this.eh);
                        }
                        eb = ew.a(o, this.e, o.toString(), this.a4, this, false, ad, j, eo, this.cp, false, this.dw.width, this.dw.height, n, false, this.al, this.getSize(), false, ji.util.i.c(159), false, this.fo(), c, null, false, false, false, b2 || b4);
                        if ((this.eb = eb) != null) {
                            this.d8 = eb.bj;
                        }
                        if (eb != null) {
                            eb.ak = this.cy;
                        }
                        this.g = eb.bj;
                        if (this.eg != null) {
                            this.eg[0] = this.g.f();
                        }
                        eb.bj = null;
                    }
                    eb.a3 = g;
                    eb.a8 = i;
                    if (ji.util.d.b()) {
                        if (j != null) {
                            eb.a6 = j;
                            eb.a7 = eo;
                        }
                    }
                    else {
                        eb.a6 = j;
                        eb.a7 = eo;
                    }
                    eb.bf = q;
                    eb.bg = r;
                    eb.j = p9;
                    ji.util.d.mt.u = eb.bf;
                    ji.util.d.mt.v = eb.bg;
                    ji.util.d.mt.w = 0L;
                    if (!(this.g instanceof r9)) {
                        eb.f = o.toString();
                        eb.h = this.a4;
                    }
                    if (ji.util.i.c(86)) {
                        eb.bd = t;
                    }
                    this.ag.m(0);
                    if (this.s != null) {
                        this.ab = this.s.length;
                    }
                    else {
                        this.ab = eb.u;
                        if (c != null) {
                            if (ji.net.a0.a(this.cp, c, "burn")) {
                                final String f = eb.f;
                                if (!ji.util.d.by(f)) {
                                    char c2 = '?';
                                    if (f.indexOf(63) > -1) {
                                        c2 = '&';
                                    }
                                    ad.d(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(f))).append(c2).append("burn=true"))), false);
                                }
                            }
                            eb.bb = ji.net.a0.a(this.cp, c);
                            final String s2 = (String)c.d("sourcelength");
                            if (!ji.util.d.by(s2)) {
                                eb.s = ji.util.d.a(s2, 0L);
                            }
                            if (!ji.util.d.by(m)) {
                                eb.t = m;
                            }
                            final String s3 = (String)c.d("pagecount");
                            if (!ji.util.d.by(s3)) {
                                this.d2 = ji.util.d.c(s3, 0);
                                if (this.d2 > 0) {
                                    this.ab = this.d2;
                                    eb.u = this.ab;
                                    eb.bc = this.d2;
                                    this.dr = true;
                                    this.ag.m(this.d2);
                                    if (!ji.util.d.by((String)c.d("separatethumbs"))) {
                                        this.d4 = true;
                                    }
                                    this.ag.bw(this.d4);
                                }
                            }
                            final String s4 = (String)c.d("searchable");
                            if (!ji.util.d.by(s4)) {
                                this.d3 = ji.util.d.b(s4, false);
                            }
                        }
                    }
                    this.ag.a(this.d);
                    if (!this.b6 && this.ar >= 0) {
                        this.f(this.ar, true);
                    }
                    this.ag.a(this.e, eb.f, eb.f, this.a4, this.s, this.s, o2 != null, this.g, eb, this.k, this.l, this.ck, o, this.bb);
                    try {
                        if (this.ck != null) {
                            final df dx = this.dx();
                            if (dx != null) {
                                e = dx.e(this.ck);
                                if (e != null) {
                                    n = e.i(-1);
                                }
                            }
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    if (this.al != 0) {
                        this.a(this.al, j, n, o, array, o2, array2, ad, b, eo, eb, b2 || b4);
                    }
                    else {
                        this.ej = j;
                        this.ek = o;
                        this.em = array;
                        this.el = o2;
                        this.en = array2;
                        this.ep = b;
                        this.eo = eo;
                        if (!b2 && !b4) {
                            this.y = true;
                        }
                    }
                    if (n > this.ae()) {
                        this.ac = this.ae();
                    }
                    else {
                        this.ac = n;
                    }
                    this.ac = Math.max(this.ac, 1);
                    try {
                        this.ag.bz(false);
                        if (this.al == 0) {
                            this.ag.d(this.ac, this.ar);
                        }
                        else {
                            this.bg = true;
                        }
                        if (this.ah != null) {
                            this.ah.k(this.ac);
                        }
                    }
                    finally {
                        this.ag.bz(true);
                    }
                    try {
                        this.ag.ao(false);
                        this.b(this.ag.r());
                        this.af(this.al);
                        this.ag.an(true);
                        if (this.ah != null) {
                            this.ah.p(true);
                        }
                        this.ag.ce(true);
                        this.az = true;
                    }
                    finally {
                        this.ag.ao(true);
                    }
                    if (e != null) {
                        n = e.i(-1);
                        if (this.al != 0 && e.c(ad)) {
                            final em a7 = ad.a7(e.dc());
                            if (a7 != null && this.ah != null) {
                                try {
                                    this.ah.f(false);
                                    this.ah.p(a7.e);
                                    this.ah.q(a7.d);
                                    this.ah.x(a7.f);
                                    this.ah.a(a7.i);
                                    this.ah.b(a7.j);
                                    this.ah.c(a7.k);
                                }
                                finally {
                                    this.ah.f(true);
                                }
                            }
                        }
                    }
                    if (this.ah != null && this.ah.ab() == 1) {
                        this.ah.a7();
                    }
                }
                else {
                    this.a(ad, false);
                }
            }
            else {
                this.a(ad, false);
            }
        }
        catch (Exception ex3) {
            this.a(ad, false);
            throw ex3;
        }
        finally {
            this.ay = false;
        }
    }
    
    private final ev fo() {
        try {
            return this.ag.d0();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final void ap(final int n) {
        if (!this.z && this.y) {
            this.a(n, this.ej, this.af(), this.ek, this.em, this.el, this.bu, this.bk, this.ep, this.eo, this.w(), false);
        }
    }
    
    private final void a(final int n, final String a6, final int n2, final Object o, final Object[] array, Object o2, final Object[] array2, final ad ad, final boolean b, final String a7, final dx dx, final boolean b2) {
        try {
            if (this.ah == null) {
                return;
            }
            this.z = true;
            dx a8 = null;
            this.ah.d(this.d2);
            this.ah.g(false);
            if (o2 != null || this.d4) {
                boolean b3 = false;
                if (this.d4) {
                    if (n == 6) {
                        o2 = this.ag.c(n2, false);
                    }
                    else {
                        o2 = this.ag.c(n2, true);
                    }
                }
                try {
                    "".concat(String.valueOf(String.valueOf(o2)));
                    if (o2 instanceof String) {
                        this.bv = this.fn().a((String)o2, this.u(), ad, this);
                    }
                    else {
                        this.bv = this.fn().a((URL)o2, true, true, this.u(), this.fl(), this.fk(), true, ad, this, this.ag);
                    }
                }
                catch (Exception ex) {
                    b3 = true;
                    this.bv = ji.util.e.a(ex, o, ad, this.cp);
                }
                String s;
                String k;
                if (!b3) {
                    s = this.fn().j();
                    k = this.fn().k();
                }
                else {
                    s = ji.util.d.bh(this.bv);
                    k = a6;
                }
                this.f = new ac(this.bv, false, false, 0, ad, this.cp);
                this.f6();
                final dx a9 = ew.a(o, this.f, o2.toString(), this.bv, this, false, ad, s, k, this.cp, true, this.dw.width, this.dw.height, n2, false, this.al, this.getSize(), false, ji.util.i.c(159), false, this.fo(), null, null, false, false, false, b2);
                this.ec = a9;
                if (this.ec != null) {
                    this.d9 = this.ec.bj;
                }
                if (a9 != null) {
                    a9.ak = this.cy;
                }
                if (a9 != null && dx != null) {
                    a9.f = dx.f;
                    a9.h = dx.h;
                }
                if (a9 != null) {
                    if (this.d2 > 0) {
                        this.ab = this.d2;
                        a9.u = this.d2;
                        a9.bc = this.d2;
                        this.dr = true;
                        this.ah.d(this.d2);
                        this.ah.g(this.d4);
                    }
                    else if (array2 != null) {
                        this.ab = Math.min(this.ab, array2.length);
                    }
                    else {
                        this.ab = Math.min(this.ab, a9.u);
                    }
                    final ck bj = a9.bj;
                    a9.bj = null;
                    a9.a6 = a6;
                    a9.a7 = a7;
                    this.ah.a(this.d);
                    this.ah.a(this.f, dx.f, dx.f, this.bv, array2, this.s, true, bj, a9, this.k, this.fn(), n2, o, this.bb);
                }
                else {
                    this.ah.a(this.d);
                    this.ah.a(this.e, dx.f, dx.f, this.a4, this.s, this.s, false, this.g, dx, this.k, this.fn(), n2, o, this.bb);
                }
            }
            else {
                this.e.a(0L);
                try {
                    c c = null;
                    if (this.eh != null) {
                        c = ji.net.a0.c(this.eh);
                    }
                    boolean b4 = false;
                    if (this.e == null) {
                        b4 = true;
                    }
                    else if (!this.e.aa()) {
                        b4 = true;
                    }
                    try {
                        if (b4) {
                            try {
                                if (o instanceof String) {
                                    this.bv = this.fn().a((String)o, this.u(), ad, this);
                                }
                                else {
                                    this.bv = this.fn().a((URL)o, true, true, this.u(), this.fl(), this.fk(), true, ad, this, this.ag);
                                }
                            }
                            catch (Exception ex2) {
                                this.bv = ji.util.e.a(ex2, o, ad, this.cp);
                            }
                            this.e = new ac(this.bv, false, false, 0, ad, this.cp);
                        }
                    }
                    catch (Exception ex3) {
                        ex3.printStackTrace();
                    }
                    try {
                        a8 = ew.a(o, this.e, o.toString(), this.a4, this, false, ad, a6, a7, this.cp, true, this.dw.width, this.dw.height, n2, false, this.al, this.getSize(), false, ji.util.i.c(159), false, this.fo(), c, null, false, false, false, b2);
                        this.ec = a8;
                        if (this.ec != null) {
                            this.d9 = this.ec.bj;
                        }
                        if (a8 != null && dx != null) {
                            a8.f = dx.f;
                            a8.h = dx.h;
                        }
                        if (a8 != null) {
                            a8.ak = this.cy;
                        }
                    }
                    catch (Exception ex4) {
                        ex4.printStackTrace();
                    }
                    if (this.d2 > 0) {
                        a8.u = this.d2;
                        this.ah.d(this.d2);
                        this.ah.g(this.d4);
                        this.dr = true;
                    }
                    this.i = a8.bj;
                    this.ah.a(this.d);
                    this.ah.a(this.e, dx.f, dx.f, this.a4, this.s, this.s, o2 != null, this.i, a8, this.k, this.fn(), n2, o, this.bb);
                }
                catch (Exception ex5) {
                    try {
                        final String a10 = ji.util.e.a(ex5, o, this, this.cp);
                        if (o != null) {
                            final ac ac = new ac(a10, false, false, 0, false, ad, this.cp);
                            final dx a11 = ew.a(a10, ac, a10, a10, this, false, ad, "txt", "txt", this.cp, true, this.dw.width, this.dw.height, n2, false, this.al, this.getSize(), false, ji.util.i.c(159), false, this.fo(), null, null, false, false, false, b2);
                            this.ec = a11;
                            if (this.ec != null) {
                                this.d9 = this.ec.bj;
                            }
                            if (a11 != null && dx != null) {
                                a11.f = dx.f;
                                a11.h = dx.h;
                            }
                            if (a11 != null && dx != null) {
                                a11.f = dx.f;
                                a11.h = dx.h;
                            }
                            if (a11 != null) {
                                a11.ak = this.cy;
                            }
                            this.i = a11.bj;
                            this.ah.a(this.d);
                            this.ah.a(ac, dx.f, dx.f, this.a4, this.s, this.s, o2 != null, this.i, a11, this.k, this.fn(), n2, o, this.bb);
                        }
                    }
                    catch (Exception ex7) {}
                }
            }
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
        }
    }
    
    public final long di() {
        try {
            if (this.al != 6 && this.al != 5 && this.ag != null) {
                return this.ag.b4();
            }
            return -1L;
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    
    public final int dj() {
        if (this.ag != null) {
            return this.ag.cu();
        }
        return -1;
    }
    
    public final void ah(final int n) {
        if (this.ag != null) {
            this.ag.j(n);
        }
    }
    
    public final int dk() {
        if (this.ag != null) {
            return this.ag.cq();
        }
        return 0;
    }
    
    public final boolean dl() {
        return this.ag != null && this.ag.d4();
    }
    
    public final void a(final gj gj) {
        if (this.ag != null) {
            this.ag.a(gj);
        }
    }
    
    public final gj dm() {
        if (this.ag != null) {
            return this.ag.cr();
        }
        return null;
    }
    
    public final void ai(final int n) {
        if (this.ag != null) {
            this.ag.k(n);
        }
    }
    
    public final int dn() {
        if (this.ag != null) {
            return this.ag.cs();
        }
        return 0;
    }
    
    public final void do() {
        if (this.ag != null) {
            this.ag.ey();
        }
    }
    
    public final void dp() {
        if (this.ag != null) {
            this.ag.ct();
        }
    }
    
    public final boolean a(final String s, final String s2) {
        return this.ag != null && this.ag.a(s, s2);
    }
    
    public final boolean a(final String s, final String s2, final String s3, final boolean b) {
        return this.ag != null && this.ag.a(s, s2, s3, b);
    }
    
    public final void a8(final boolean b) {
        if (this.ag != null) {
            this.ag.bq(b);
        }
    }
    
    public final boolean j(final String s) {
        return this.ag != null && this.ag.m(s);
    }
    
    public final boolean d(final int n, final int n2) {
        return this.ag != null && this.ag.c(n, n2);
    }
    
    public final boolean a(final int n, final Color color, final Color color2, final String s, final ct ct, final int n2, final int n3, final String s2, final String s3, final String s4, final gj gj) {
        return this.ag != null && this.ag.a(n, color, color2, s, ct, n2, n3, s2, s3, s4, gj);
    }
    
    public final void a(final jiBurnerListener jiBurnerListener, final String s, final int n, final int n2) {
        if (this.ag != null) {
            this.ag.a(jiBurnerListener, s, n, n2);
        }
    }
    
    public final void dq() {
        if (this.ag != null) {
            this.ag.br(false);
        }
    }
    
    public final void dr() {
        if (this.ag != null) {
            this.ag.cy();
        }
    }
    
    public final void ds() {
        try {
            if (this.ah != null) {
                this.ah.a0();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void dt() {
        try {
            if (this.ah != null) {
                this.ah.l();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final df df, final c c, final Object o, final boolean b) {
        try {
            boolean a = false;
            if (this.ag != null) {
                a = this.ag.a(df, c, o, b);
            }
            if (this.ah != null) {
                if (!a) {
                    this.ah.a(df, c, o, b);
                }
                if (this.al != 0) {
                    this.ag.b2(true);
                    this.ah.ad();
                    this.dd = true;
                    this.au = true;
                    this.ag.q();
                    this.fu();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void du() {
        try {
            if (this.ag != null) {
                this.ag.b1();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void dv() {
        try {
            if (this.ag != null) {
                this.ag.b0();
            }
        }
        catch (Exception ex) {}
    }
    
    public final void dw() {
        try {
            if (this.ah != null) {
                this.ah.az();
            }
        }
        catch (Exception ex) {}
    }
    
    public final df dx() {
        try {
            if (this.ag != null) {
                return this.ag.cj();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final df dy() {
        try {
            if (this.ah != null) {
                return this.ah.h();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final df aj(final int n) {
        try {
            if (this.ag != null) {
                return this.ag.i(n);
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void dz() {
        try {
            if (this.ag != null) {
                this.ag.br();
            }
        }
        catch (Exception ex) {}
        try {
            if (this.ah != null) {
                this.ah.k();
            }
        }
        catch (Exception ex2) {}
    }
    
    public final Object d0() {
        try {
            if (this.ag != null) {
                return this.ag.bp();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final c d1() {
        try {
            if (this.ag != null) {
                return this.ag.bq();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final df d2() {
        try {
            if (this.ag != null) {
                return this.ag.ck();
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void d3() {
        try {
            if (this.ag != null) {
                this.ag.gq();
            }
            if (this.ah != null) {
                this.ah.m();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void p(final String s) {
        if (this.cd) {
            return;
        }
        try {
            if (s != null && !this.a7) {
                if (this.k(s)) {
                    ji.document.cw.a6.e(s);
                }
                else {
                    ji.document.cw.a6.a(s, s);
                    ji.document.cw.a6.e(s);
                }
                this.a7 = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean k(final String s) {
        boolean b = false;
        try {
            if (ji.document.cw.a6.d(s) > 0) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private final void q(final String s) {
        if (this.cd) {
            return;
        }
        try {
            if (s != null && this.a7) {
                ji.document.cw.a6.f(s);
                if (!this.k(s)) {
                    ji.document.cw.a6.b(s);
                }
                this.a7 = false;
            }
        }
        catch (Exception ex) {}
        this.r(s);
        if (this.bk.dq() != null) {
            this.r("".concat(String.valueOf(String.valueOf(this.bk.dq()))));
        }
    }
    
    private final void r(final String s) {
        try {
            if (!ji.util.d.by(s) && ji.util.d.a(this.bk)) {
                ji.annotate.b8.a(this.cp, this.bk, s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a9(final boolean b) {
        if (this.cd) {
            return;
        }
        try {
            this.ag.b1(b);
            this.ah.z(b);
        }
        catch (Exception ex) {}
    }
    
    public final void ba(final boolean b) {
        try {
            this.ag.bj(b);
        }
        catch (Exception ex) {}
    }
    
    public void bb(final boolean a3) {
        if (this.cd) {
            return;
        }
        this.a3 = a3;
        this.ag.l(a3);
        if (this.ah != null) {
            this.ah.n(a3);
        }
    }
    
    public final boolean d4() {
        try {
            if (this.bk != null) {
                return this.bk.ib();
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public final void d5() {
        if (this.a3 && this.az) {
            this.getSize();
            if (!this.d4()) {
                if (ji.util.d.b()) {
                    this.d9();
                }
                else if (this.ba == null) {
                    if (ji.util.i.c(142)) {
                        this.ba = new wb();
                    }
                    else {
                        this.ba = new wd();
                    }
                    (this.a9 = new bb(this.cp, this.ba)).start();
                }
                else {
                    this.ba.a();
                }
            }
        }
    }
    
    public Object d6() {
        return this.ag.c8();
    }
    
    public String[] d7() {
        return this.ag.c9();
    }
    
    public String d8() {
        return this.ag.db();
    }
    
    public final void d9() {
        if (this.c != null && this.a3 && !this.at) {
            try {
                this.at = true;
                this.au = false;
                if (this.al != 0) {
                    this.ag.b2(true);
                    this.ah.ad();
                    this.dd = true;
                    this.ag.q();
                    this.au = true;
                    if (ji.util.d.dv()) {
                        ji.io.h.e(this.cp, "Gen thumbs (0)...");
                    }
                    this.fu();
                }
                else {
                    if (ji.util.d.dv()) {
                        ji.io.h.e(this.cp, "Gen fp (0)...");
                    }
                    this.fs();
                }
            }
            catch (Exception ex) {}
            finally {
                this.at = false;
                if (ji.util.d.dv()) {
                    ji.io.h.e(this.cp, "Gen thumbs/fp done.");
                }
            }
        }
    }
    
    private final void fp() {
        try {
            final int ae = this.ae();
            if (this.af() > 0 && ae > 0) {
                String s = "";
                if (ji.util.d.di() == 1 && this.d != null) {
                    switch (this.d[this.af() - 1].a) {
                        case 0: {
                            s = this.ao(399);
                            break;
                        }
                        case 1: {
                            s = this.ao(400);
                            break;
                        }
                        case 2: {
                            s = this.ao(401);
                            break;
                        }
                        case 3: {
                            s = this.ao(402);
                            break;
                        }
                    }
                }
                else {
                    s = ji.util.d.d(this.af(), this.ae(), this.cp);
                }
                if (this.ag.f8() != 0) {
                    s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(", ").append(this.ao(304)).append(" ").append(this.ag.f8()).append("'")));
                }
                this.l(String.valueOf(String.valueOf(s)).concat("..."));
            }
            else {
                this.l("");
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final ck h) {
        this.h = h;
    }
    
    public final boolean a(final dx dx) {
        if (dx != null) {
            if (dx == this.eb) {
                return true;
            }
            if (dx == this.ec) {
                return true;
            }
        }
        return false;
    }
    
    private final void fq() {
        try {
            if (this.d8 != null) {
                this.d8.close(this.eb, this.bk);
                this.d8 = null;
            }
        }
        catch (Exception ex) {}
        try {
            if (this.d9 != null) {
                this.d9.close(this.ec, this.bk);
                this.d9 = null;
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.ea != null) {
                this.ea.close(this.ed, this.bk);
                this.ea = null;
            }
        }
        catch (Exception ex3) {}
    }
    
    private final void fr() {
        this.fq();
        try {
            if (this.g != null) {
                final dx r = this.ag.r();
                this.g.abort(r);
                this.g.close(r, this.bk);
                final dx aw = this.ah.aw();
                this.g.abort(aw);
                this.g.close(aw, this.bk);
                this.g = null;
            }
            else if (this.h != null) {
                this.h.abort(this.ag.r());
                this.h.close(this.ag.r(), this.bk);
                this.h.abort(this.ah.aw());
                this.h.close(this.ah.aw(), this.bk);
            }
            this.h = null;
            if (this.i != null) {
                this.i.close(this.ah.aw(), this.bk);
                this.i = null;
            }
            if (this.cz != null) {
                this.cz.close(this.cx, this.bk);
                this.cz = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final boolean aq(final int n) {
        return this.eg != null && n >= 0 && n < this.eg.length && this.eg[n];
    }
    
    public boolean[] d(final boolean[] array, final boolean b) {
        boolean[] array2;
        if (array == null) {
            array2 = new boolean[] { this.ea() };
        }
        else {
            array2 = new boolean[array.length];
            if (b) {
                for (int i = 0; i < array.length; ++i) {
                    if (array[i]) {
                        array2[i] = this.g(i, b);
                    }
                }
            }
            else {
                final boolean ea = this.ea();
                for (int j = 0; j < array.length; ++j) {
                    array2[j] = ea;
                }
            }
        }
        return array2;
    }
    
    public boolean g(final int n, final boolean b) {
        boolean b2 = false;
        if (b) {
            if (this.ag != null) {
                b2 = this.aq(n);
            }
        }
        else {
            b2 = this.ea();
        }
        return b2;
    }
    
    public boolean ea() {
        boolean f = false;
        if (this.g != null) {
            f = this.g.f();
        }
        return f;
    }
    
    public final void bc(final boolean cj) {
        this.cj = cj;
    }
    
    public boolean a(final df df, final boolean b, final int[] array) {
        boolean b2 = false;
        final boolean cb = this.cb();
        if (df != null) {
            final ax g = df.g();
            if (ji.util.d.b3(b) == 0) {
                for (int n = 0; n < df.d() && !b2; ++n) {
                    final dg b3 = df.b(g.b());
                    if (b3.af() == 1) {
                        if (array == null) {
                            b2 = true;
                        }
                        else {
                            final int i = b3.i(-1);
                            for (int j = 0; j < array.length; ++j) {
                                if (array[j] == i) {
                                    b2 = true;
                                }
                            }
                        }
                    }
                }
            }
            else {
                final boolean b4 = ji.util.d.b3(b) == 1;
                for (int n2 = 0; n2 < df.d() && !b2; ++n2) {
                    final dg b5 = df.b(g.b());
                    if (b5.af() == 1) {
                        if (array == null) {
                            b2 = (b5.az() == 1);
                        }
                        else {
                            final int k = b5.i(-1);
                            for (int l = 0; l < array.length; ++l) {
                                if (array[l] == k) {
                                    b2 = (b5.az() == 1);
                                }
                            }
                        }
                    }
                    else if (b5.af() == 0) {
                        final int m = b5.i(-1);
                        if (array == null) {
                            b2 = (b5.az() != 0 && this.g(m - 1, cb) && ji.wang.ej.a(b5.d5()));
                        }
                        else {
                            for (int n3 = 0; n3 < array.length; ++n3) {
                                if (array[n3] == m && this.g(m - 1, cb)) {
                                    b2 = (b5.az() != 0 && ji.wang.ej.a(b5.d5()));
                                }
                            }
                        }
                    }
                }
                if (!b2) {
                    for (ax h = df.h(); h.a() && !b2; b2 = true) {
                        final dg c = df.c(h.b());
                        if (ji.wang.ej.a(c.d5())) {
                            if (array != null) {
                                final int i2 = c.i(-1);
                                for (int n4 = 0; n4 < array.length; ++n4) {
                                    if (array[n4] == i2 && this.g(i2 - 1, cb)) {
                                        b2 = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    private final void b(final dx dx) {
        try {
            this.k = null;
            (this.k = new c("docproperties2")).a("Page", "".concat(String.valueOf(String.valueOf(this.ac))));
            this.k.a("Document", this.c);
            this.k.a("NumPages", new Integer(this.ae()));
            this.k.a("Width", new Integer(dx.m));
            this.k.a("Height", new Integer(dx.n));
            this.k.a("ImageType", dx.l);
            this.k.a("FileLength", new Long(dx.i));
            if (dx.bk != null && dx.bk.size() > 0) {
                final Enumeration<String> keys = dx.bk.keys();
                while (keys.hasMoreElements()) {
                    final String s = keys.nextElement();
                    this.k.a(s.toString(), dx.bk.get(s));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void fs() {
        if (this.cj) {
            return;
        }
        if (this.bc) {
            this.ft();
        }
    }
    
    private final void ft() {
        if (this.cj) {
            return;
        }
        if (this.bc && this.a3 && this.az) {
            try {
                if (this.ag.ea() != this.ac) {
                    this.bg = true;
                }
                if (this.bg) {
                    this.ag.d(this.ac, this.ar);
                    this.bg = false;
                }
                if (this.al != 5 && this.al != 6) {
                    this.ag.c2(true);
                    this.ag.b3(true);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void k(final int n, final boolean b) {
        if (this.a3 && this.az && this.bc) {
            try {
                if (ji.util.e.af()) {
                    this.ah.a(this.dx(), this.d1(), this.d0(), false);
                }
                if (this.ah.av() == n) {
                    this.ah.b(n, b);
                }
                else {
                    this.l(n, b);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    private final void b(final Vector vector, final boolean b) {
        if (this.a3 && this.az && this.bc) {
            try {
                if (ji.util.e.af()) {
                    this.ah.a(this.dx(), this.d1(), this.d0(), false);
                }
                this.c(vector, b);
            }
            catch (Exception ex) {}
        }
    }
    
    private final void l(final int n, final boolean b) {
        if (this.cj) {
            return;
        }
        final Vector<Integer> vector = new Vector<Integer>();
        vector.addElement(new Integer(n));
        this.c(vector, b);
    }
    
    private final void c(final Vector vector, final boolean b) {
        if (this.cj) {
            return;
        }
        if (this.bc) {
            try {
                new bb(this.cp, new w8(vector, b)).start();
            }
            catch (Exception ex) {}
        }
    }
    
    private final void fu() {
        if (this.cj) {
            return;
        }
        if (this.a3 && this.az) {
            try {
                if (this.al != 0) {
                    if (this.a5) {
                        this.a5 = false;
                        this.ah.g(this.ac);
                    }
                    this.dc = true;
                    this.ah.a5();
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
    }
    
    public final void ak(final int n) {
        if (this.a3 && this.az) {
            try {
                if (this.al != 0) {
                    this.ah.g(n);
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void b(final a6 a6) {
        if (this.cj) {
            return;
        }
        if (!a6.b(this)) {
            a6.a(this);
            if (a6.d() == 14) {
                if (this.bk != null) {
                    this.bk.a(a6);
                }
            }
            else if (a6.d() == 4) {
                if (ji.util.i.c(7) && ji.util.e.t()) {
                    if (this.bk != null) {
                        this.bk.b(a6);
                    }
                }
                else {
                    this.d(a6);
                    this.c(a6);
                    if (this.bk != null) {
                        this.bk.b(a6);
                    }
                }
            }
            else {
                this.d(a6);
                this.c(a6);
            }
        }
    }
    
    private final void c(final a6 a6) {
        if (this.cj) {
            return;
        }
        if (this.ag != null && a6.c() != 1) {
            this.ag.b(a6);
        }
    }
    
    private final void d(final a6 a6) {
        if (this.cj) {
            return;
        }
        if (this.ah != null && a6.c() != 2) {
            this.ah.a(a6);
        }
    }
    
    public final void eb() {
        if (this.ah != null) {
            this.ah.a7();
        }
    }
    
    public final void a(final a9 a9) {
        final Object d = a9.d();
        if (d instanceof a6) {
            this.e((a6)a9.d());
        }
        else if (d instanceof d7) {
            if (((d7)d).d() == 62) {
                this.c(this.c1(), true, true);
            }
            else {
                this.c((d7)a9.d());
            }
        }
        else if (d instanceof ob) {
            this.c((ob)a9.d());
        }
        else if (d instanceof fv) {
            this.c((fv)a9.d());
        }
    }
    
    private final void b(final d7 d7) {
        if (d7.m()) {
            this.c(d7);
        }
        else if (ji.util.d.am()) {
            boolean b = false;
            if (d7.d() == 47) {
                b = true;
            }
            if (this.do != null) {
                this.do.a(new a9(this, d7, b));
            }
        }
        else {
            this.c(d7);
        }
    }
    
    private final void fv() {
        try {
            if (this.dy == null) {
                (this.dy = new bb(this.cp, new z6())).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void fw() {
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, new d7(this, 62, true), true));
            }
        }
        else {
            this.c(this.c1(), true, true);
        }
    }
    
    private final void c(final d7 d7) {
        this.bs = true;
        try {
            if (this.an != null) {
                final c an = this.an;
                for (int b = an.b(), i = 0; i < b; ++i) {
                    if (!d7.l()) {
                        ((ag)an.b(i)).a(d7);
                    }
                }
            }
        }
        finally {
            this.bs = false;
        }
    }
    
    private final void e(final a6 a6) {
        try {
            if (a6.d() == 19) {
                this.ag.a(this.ah.h(), this.ah.j(), this.ah.i(), false);
            }
            else if (a6.d() == 20) {
                this.ah.a(this.ag.cj(), this.ag.bq(), this.ag.bp(), false);
            }
            else if (a6.d() == 5) {
                ++this.br;
                if (this.br > 6) {
                    this.br = 1;
                }
                if (this.ag != null) {
                    this.ag.h(this.br);
                }
                if (this.ah != null) {
                    this.ah.e(this.br);
                }
            }
            else if (a6.d() != 7) {
                if (a6.d() != 6) {
                    this.b(a6);
                    if (this.ad != null) {
                        final c ad = this.ad;
                        for (int b = ad.b(), i = 0; i < b; ++i) {
                            ((af)ad.b(i)).a(a6);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final String a(final int n, final String s, final boolean b) {
        if (this.ag != null && !b) {
            return this.ag.a(n, s);
        }
        return null;
    }
    
    public final void a(final a6 a6) {
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, a6, a6.d() == 4));
            }
        }
        else {
            this.e(a6);
        }
    }
    
    public final void a(final fv dm) {
        if (this.cd) {
            return;
        }
        if (dm.b() == 7) {
            this.dm = dm;
        }
        else if (dm.b() == 3) {
            try {
                final int c = dm.c();
                if (ji.util.d.di() == 1) {
                    if (c != this.ag.ea()) {
                        if ((dm.e() & 0x2) > 0 || (dm.e() & 0x1) > 0) {
                            if (this.x(c)) {
                                this.z(c);
                            }
                            else {
                                this.y(c);
                            }
                        }
                        else {
                            this.b(dm);
                        }
                    }
                    else {
                        this.bx();
                    }
                }
                else {
                    this.b(dm);
                }
                this.c(this.ac = c);
            }
            catch (Exception ex) {}
        }
        else if (dm.b() == 10) {
            if (this.bk != null) {
                this.bk.b(dm.a());
            }
        }
        else if (dm.b() == 8) {
            try {
                final int c2 = dm.c();
                if (ji.util.d.di() == 1) {
                    if (c2 != this.ag.ea()) {
                        if ((dm.e() & 0x2) > 0 || (dm.e() & 0x1) > 0) {
                            if (this.x(c2)) {
                                this.z(c2);
                            }
                            else {
                                this.y(c2);
                            }
                        }
                        else {
                            this.b(dm);
                        }
                    }
                    else {
                        this.bx();
                    }
                }
                else {
                    this.b(dm);
                }
            }
            catch (Exception ex2) {}
        }
        else if (dm.b() == 2) {
            if (!this.dc) {
                this.b(dm);
            }
        }
        else if (dm.b() == 9) {
            this.dc = false;
            this.b(dm);
        }
        else if (dm.b() == 11) {
            this.dc = false;
            this.b(dm);
        }
        else {
            this.b(dm);
        }
    }
    
    public final void a(final ob ob) {
        this.b(ob);
    }
    
    private final void b(final ob ob) {
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, ob, false));
            }
        }
        else {
            this.c(ob);
        }
    }
    
    private final void c(final ob ob) {
        try {
            if (this.ao != null) {
                final c ao = this.ao;
                for (int b = ao.b(), i = 0; i < b; ++i) {
                    if (!ob.c()) {
                        ((ah)ao.b(i)).a(ob);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final d7 d7) {
        try {
            if (this.cn) {
                return;
            }
            if (d7.d() == 43 && this.al != 5 && this.al != 6 && this.al != 0) {
                this.m();
                try {
                    this.ah.ad();
                    this.ah.j(true);
                    this.dt();
                    this.ah.n(false);
                    this.ag.gm();
                    this.ag.q();
                    this.ag.l(false);
                    this.ah.am();
                    this.ag.b2();
                }
                finally {
                    this.n();
                }
                this.de = true;
                this.f0();
            }
            else if (d7.d() == 44 && this.al != 5 && this.al != 6 && this.al != 0) {
                if (this.de) {
                    this.ah.j(false);
                    this.de = false;
                    this.e((Object)this.bk);
                }
            }
            else if (d7.d() == 45 && this.al != 5 && this.al != 6 && this.al != 0) {
                try {
                    final int n = d7.h().x - this.getLocationOnScreen().x;
                    int dh = this.dh;
                    switch (this.al) {
                        case 3: {
                            dh = this.getSize().width - Math.max(n, this.dj) - this.ak.getSize().width / 2;
                            break;
                        }
                        case 1: {
                            dh = Math.max(n, this.dj) - this.ak.getSize().width / 2;
                            break;
                        }
                    }
                    if (dh != this.dh) {
                        this.dh = dh;
                        this.di = this.dh;
                    }
                }
                catch (Exception ex) {}
            }
            else if (d7.d() == 46 && this.al != 5 && this.al != 6 && this.al != 0) {
                final int n2 = d7.h().y - this.getLocationOnScreen().y;
                int di = this.di;
                switch (this.al) {
                    case 4: {
                        di = this.getSize().height - (Math.max(n2, this.dj) - this.ak.getSize().height / 2);
                        break;
                    }
                    case 2: {
                        di = Math.max(n2, this.dj) - this.ak.getSize().height / 2;
                        break;
                    }
                }
                if (di != this.di) {
                    this.di = di;
                    this.dh = this.di;
                }
            }
            else if (d7.d() == 8 && this.al != 5 && this.al != 6) {
                if (!this.ah.isVisible()) {
                    this.fv();
                }
                else {
                    this.dc = false;
                    if (this.dd) {
                        if (this.ag.dh()) {
                            this.ag.q();
                        }
                        this.ag.b2(false);
                    }
                    if (this.dm != null) {
                        this.au = true;
                        this.a(new fv(this.dm.getSource(), 3, this.dm.c(), this.dm.e()));
                        this.dm = null;
                    }
                    if (this.au) {
                        this.au = false;
                        this.fs();
                    }
                    this.ah.aa();
                    this.b(new d7(this, 47, this.af()));
                }
            }
            else if (d7.d() == 8) {
                this.dc = false;
                if (this.dm != null) {
                    this.au = false;
                    this.a(new fv(this.dm.getSource(), 3, this.dm.c(), this.dm.e()));
                    this.dm = null;
                }
                this.ah.aa();
                this.b(new d7(this, 47, this.af()));
                if (this.al == 5 || this.al == 6) {
                    this.b(new d7(this, 3, this.af()));
                    this.b(new d7(this, 50, this.af()));
                }
            }
            else if (d7.d() == 15) {
                this.ah.b(this.ag.f8(), this.ag.f7(), this.ag.ge());
                this.fu();
                this.b(d7);
            }
            else if (d7.d() == 48) {
                if (this.al != 6 && this.al != 0) {
                    this.dc = false;
                    if (this.au && !this.ah.a9()) {
                        this.au = false;
                        this.fs();
                    }
                }
            }
            else {
                try {
                    if (d7.d() == 16 && this.al != 6 && this.al != 0 && this.ah != null && !this.ah.a8() && this.ah.a9()) {
                        this.fu();
                    }
                }
                catch (Exception ex2) {}
                this.fa();
                this.b(d7);
            }
        }
        catch (Exception ex3) {}
    }
    
    public final void l(final String s) {
        final a6 a6 = new a6(this, 1, s);
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, a6, false));
            }
        }
        else {
            this.e(a6);
        }
    }
    
    public final void al(final int n) {
        final a6 a6 = new a6(this, 1, "".concat(String.valueOf(String.valueOf(n))));
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, a6, true));
            }
        }
        else {
            this.e(a6);
        }
    }
    
    public final boolean ec() {
        if (this.al == 6 || this.al == 5) {
            return this.ah != null && this.ah.n();
        }
        return this.ag != null && this.ag.do();
    }
    
    private void fx() {
        this.ar(-1);
    }
    
    private void ar(final int n) {
        try {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final Dimension b = this.ah.b(width, height, n);
            if (this.ak != null) {
                this.ak.f(true);
            }
            final Insets insets = this.getInsets();
            final int n2 = width - (insets.left + insets.right);
            final int n3 = height - (insets.top + insets.bottom);
            this.bi = false;
            this.bj = false;
            if ((this.az || !this.d5) && (this.fd() || ji.util.d.av()) && this.bk.bi(22) && this.ai.a(this.bk) && this.al != 6) {
                this.bi = true;
                if (this.bk.ck()) {
                    int ab = this.ah.ab();
                    if (n != -1) {
                        ab = n;
                    }
                    if (ab == 4 || this.bk.bm(this.al) || (this.aj != null && this.aj.c())) {
                        this.bj = true;
                    }
                }
            }
            this.bh = false;
            if ((this.az || !this.d5) && ji.util.d.au() && this.al != 5 && this.al != 6 && this.al != 0) {
                if (this.dh < 0 || this.di < 0) {
                    final Dimension d = this.d((Object)this.bk);
                    this.dh = d.width;
                    this.di = d.height;
                }
                this.bh = true;
            }
            int n4 = 0;
            if (this.bh) {
                n4 = 9;
                switch (this.al) {
                    case 2: {
                        if (this.dh == 0 && b.width > this.dj) {
                            this.dh = b.width;
                        }
                        this.dh = Math.max(this.dh, this.ah.b(n2, n3, n).width);
                        this.dh = Math.min(this.dh, n2 - this.dj);
                        if (this.dh > this.dj) {
                            b.width = this.dh;
                            break;
                        }
                        break;
                    }
                    case 0: {
                        if (this.dh == 0 && b.width > this.dj) {
                            this.dh = b.width;
                        }
                        this.dh = Math.max(this.dh, this.ah.b(n2, n3, n).width);
                        this.dh = Math.min(this.dh, n2 - this.dj);
                        if (this.dh > this.dj) {
                            b.width = this.dh;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (this.di == 0 && b.height > this.dj) {
                            this.di = b.height;
                        }
                        int height2 = this.ah.b(n2, n3, n).height;
                        if (this.bj) {
                            height2 += this.aj.b();
                        }
                        this.di = Math.max(this.di, height2);
                        this.di = Math.min(this.di, n3 - this.dj);
                        if (this.di > this.dj) {
                            b.height = this.di;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.di == 0 && b.height > this.dj) {
                            this.di = b.height;
                        }
                        int height3 = this.ah.b(n2, n3, n).height;
                        if (this.bj) {
                            height3 += this.aj.b();
                        }
                        this.di = Math.max(this.di, height3);
                        this.di = Math.min(this.di, n3 - this.dj);
                        if (this.di > this.dj) {
                            b.height = this.di;
                            break;
                        }
                        break;
                    }
                }
            }
            int n5 = 0;
            int n6 = 0;
            int n7 = 1;
            int n8 = 1;
            int n9 = 0;
            int n10 = 0;
            int n11 = 1;
            int n12 = 1;
            int n13 = 0;
            int n14 = 0;
            int n15 = n4;
            if (!this.az && this.d5) {
                b.width = 0;
                b.height = 0;
                n15 = 0;
            }
            else {
                b.width = Math.min(n2, b.width);
                b.height = Math.min(n3, b.height);
            }
            if (this.bi) {
                switch (this.al) {
                    case 0: {
                        this.ai.setVisible(false);
                        break;
                    }
                    case 3: {
                        n6 = 15;
                        if (this.ai != null) {
                            n6 = this.ai.a();
                        }
                        n9 = b.width;
                        n10 = n6;
                        n7 = (n11 = n2 - n9);
                        n12 = n8 + n10;
                        n13 = n9;
                        break;
                    }
                    case 1: {
                        n6 = 15;
                        if (this.ai != null) {
                            n6 = this.ai.a();
                        }
                        n9 = b.width;
                        n10 = n6;
                        n11 = n7;
                        n12 = n8 + n10;
                        n13 = n9;
                        break;
                    }
                    case 2: {
                        n5 = (n9 = 18);
                        n10 = b.height;
                        n11 = n7 + n9;
                        n12 = n8;
                        break;
                    }
                    case 4: {
                        n5 = (n9 = 18);
                        n10 = b.height;
                        n8 = insets.top + n3 - b.height;
                        n11 = n7 + n9;
                        n12 = n8;
                        break;
                    }
                    case 5: {
                        n6 = 15;
                        if (this.ai != null) {
                            n6 = this.ai.a();
                        }
                        n9 = n2;
                        n10 = n6;
                        n11 = n7;
                        n12 = n8 + n10;
                        break;
                    }
                    case 6: {
                        n6 = 15;
                        if (this.ai != null) {
                            n6 = this.ai.a();
                        }
                        n9 = n2;
                        n10 = n6;
                        break;
                    }
                }
                ji.util.e.a(this.ai, n7, n8, n9, n10);
                this.fe();
            }
            if (this.bj) {
                switch (this.al) {
                    case 0: {
                        this.ai.setVisible(false);
                        break;
                    }
                    case 3: {
                        n14 = this.aj.b();
                        n6 += n14;
                        break;
                    }
                    case 1: {
                        n14 = this.aj.b();
                        n6 += n14;
                        break;
                    }
                    case 2: {
                        n14 = this.aj.b();
                        n13 = n2 - n5;
                        n6 += n14;
                        break;
                    }
                    case 4: {
                        n14 = this.aj.b();
                        n13 = n2 - n5;
                        n6 += n14;
                        break;
                    }
                    case 5: {
                        n13 = n2;
                        n14 = this.aj.b();
                        n6 += n14;
                        break;
                    }
                    case 6: {
                        this.ai.setVisible(false);
                        break;
                    }
                }
                ji.util.e.a(this.aj, n11, n12, n13, n14);
                this.ff();
            }
            int n16 = 0;
            int n17 = 0;
            int n18 = 0;
            int n19 = 0;
            if (ji.util.d.ax(this.cp) && ji.util.d.dp()) {
                n16 = 1;
                n17 = 1;
                n18 = 1;
                n19 = 1;
            }
            switch (this.al) {
                case 0: {
                    this.ag.setBounds(insets.left + n16, insets.top + n17, n2 - n18, n3 - n19);
                    break;
                }
                case 3: {
                    this.ag.setBounds(insets.left + n16, insets.top + n17, n2 - b.width - 1 - n15 - n18, n3 - n19);
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(n2 - b.width + n5, insets.top + n6, b.width, n3 - n6);
                        break;
                    }
                    break;
                }
                case 1: {
                    this.ag.setBounds(insets.left + b.width + n15 + n16, insets.top + n17, n2 - b.width - n15 - n18, n3 - n19);
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(insets.left + n5, insets.top + n6, b.width, n3 - n6);
                        break;
                    }
                    break;
                }
                case 2: {
                    this.ag.setBounds(insets.left + n16, insets.top + b.height + n15 + n17, n2 - n18, n3 - b.height - n15 - n19);
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(insets.left + n5, insets.top + n6, n2 - n5, b.height - n6);
                        break;
                    }
                    break;
                }
                case 4: {
                    this.ag.setBounds(insets.left + n16, insets.top + n17, n2 - n18, n3 - b.height - n15 - n19);
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(insets.left + n5, insets.top + n3 + n6 - b.height, n2 - n5, b.height - n6);
                        break;
                    }
                    break;
                }
                case 5: {
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(insets.left + n5 + n16, insets.top + n6 + n17, n2 - n18, n3 - n6 - n19);
                        break;
                    }
                    break;
                }
                case 6: {
                    if (b.width > 0 && b.height > 0) {
                        this.ah.setBounds(insets.left + n5 + n16, insets.top + n6 + n17, n2 - n18, n3 - n6 - n19);
                        break;
                    }
                    break;
                }
            }
            try {
                final Rectangle bounds = this.ag.getBounds();
                this.aa.x = bounds.x;
                this.aa.y = bounds.y;
                this.aa.width = bounds.width;
                this.aa.height = bounds.height;
                if (this.bh) {
                    this.fg();
                    switch (this.al) {
                        case 2: {
                            this.ak.a(1);
                            ji.util.e.a(this.ak, this.aa.x + this.aa.width, this.aa.y, n4, this.aa.height);
                            break;
                        }
                        case 0: {
                            this.ak.a(3);
                            ji.util.e.a(this.ak, this.aa.x - n4, this.aa.y, n4, this.aa.height);
                            break;
                        }
                        case 1: {
                            this.ak.a(0);
                            ji.util.e.a(this.ak, this.aa.x, this.aa.y - n4, this.aa.width, n4);
                            break;
                        }
                        case 3: {
                            this.ak.a(2);
                            ji.util.e.a(this.ak, this.aa.x, this.aa.y + this.aa.height, this.aa.width, n4);
                            break;
                        }
                    }
                }
                else {
                    this.bo(false);
                }
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
        finally {
            if (this.ak != null) {
                this.ak.f(false);
            }
        }
    }
    
    private final boolean fy() {
        return this.ag.au() || this.ag.dj() || this.ag.aw();
    }
    
    private void fz() {
        this.as(-1);
    }
    
    private void as(final int n) {
        if (this.al != 5 && this.al != 6 && this.fy()) {
            this.ag.gn();
            while (this.fy()) {
                ji.util.d.b(100, 129, this.cp);
            }
        }
        this.ar(n);
        try {
            if (this.getSize().width > 0 && this.x) {
                this.x = false;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void f0() {
        try {
            if (this.df == null) {
                this.df = new z5();
                final bb bb = new bb(this.cp, this.df);
                bb.setPriority(1);
                bb.start();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void f1() {
        try {
            this.dk = this.dh;
            this.dl = this.di;
        }
        catch (Exception ex) {}
    }
    
    private final void f2() {
        try {
            this.ah.j(false);
            this.ag.l(true);
            this.ah.n(true);
            this.d5();
        }
        catch (Exception ex) {}
    }
    
    private final void f3() {
        try {
            if (this.dk != this.dh || this.dl != this.di) {
                this.fz();
                this.dk = this.dh;
                this.dl = this.di;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void bd(final boolean b) {
        if (this.ag != null) {
            this.ag.b9(b);
        }
    }
    
    public final boolean ed() {
        return this.ag != null && this.ag.dq();
    }
    
    private final void f4() {
        try {
            if (this.bk.ba()) {
                ji.io.h.d(this.cp, "releaseP86");
            }
            this.b1 = false;
            this.b7 = false;
            if (this.bo != null) {
                if (!ji.util.e.r()) {
                    this.bo.endPage();
                    ji.util.e.i(true);
                }
                if (this.b5) {
                    this.b5 = false;
                    this.bo.endDoc();
                }
                this.bo.releaseResources();
                this.bo = null;
            }
            if (this.b8 != null) {
                this.b8.a();
                this.b8 = null;
            }
        }
        catch (Exception ex) {
            this.b0 = false;
            this.bo = null;
        }
    }
    
    private final void f5() {
        if (this.bk != null && this.bk.ba()) {
            ji.io.h.d(this.cp, "closeP86");
        }
        try {
            if (this.bo != null) {
                if (!ji.util.e.r()) {
                    this.bo.endPage();
                    ji.util.e.i(true);
                }
                if (this.b5) {
                    this.b5 = false;
                    this.bo.endDoc();
                }
            }
            if (ji.util.d.cb()) {
                this.f4();
            }
            else if (this.bo != null) {
                this.bo.releasePrintingResources();
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final void f6() {
        try {
            if (this.dn == null) {
                this.dn = new p(this.cp);
            }
            this.dw = this.dn.b(new Dimension(120, 120), this);
        }
        catch (Exception ex) {}
    }
    
    public final Dimension d(final Object o) {
        this.f6();
        return this.dn.d(new Dimension(this.dj, this.dj), o);
    }
    
    public final void e(final Object o) {
        this.f6();
        this.dn.c(new Dimension(this.dh, this.di), o);
    }
    
    public final void ee() {
        try {
            if (this.da && !this.ds) {
                this.ds = true;
                if (this.dv == null) {
                    this.dv = new v();
                }
                if (this.dv.a(this.cp)) {
                    this.du = this.dv.a(this.bk, this.cp, this);
                }
                else {
                    this.da = false;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ef() {
        try {
            if (this.ds && this.du) {
                if (this.dv != null) {
                    this.dv.b(this.cp);
                }
                this.ds = false;
                this.dv = null;
                this.du = false;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void e(final ad ad) {
        try {
            this.b0 = (ji.util.d.db() && ji.util.e.u(this.cp));
            this.b2 = ji.util.d.dd();
            if (ad.ba()) {
                ji.io.h.d(this.cp, "pa: ".concat(String.valueOf(String.valueOf(this.b0))));
            }
            if (!ji.util.d.m("Print Accelerator", this.cp)) {
                this.b0 = false;
            }
            if (this.b0) {
                if (this.bo == null) {
                    if (this.b8 == null) {
                        this.b8 = new fo(this.cp);
                    }
                    this.bo = (jiPrinti)this.b8.a("ji.util.jiPrintLib", "jiP86l.class", "jiP86.class", 759, "jip86.dll", "jip86.dll", ad, this, this.cp, false, null);
                    if (this.bo.isLoaded()) {
                        if (ad.ba()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Load4..."));
                        }
                        this.bo.setDebug(ad.ba());
                        this.bo.setMessages(ad.bb());
                    }
                }
                if (ad.ba()) {
                    ji.io.h.d(this.cp, "p86: ".concat(String.valueOf(String.valueOf(this.bo))));
                }
                this.b1 = this.bo.isLoaded();
                this.b7 = this.b1;
                if (ad.ba()) {
                    ji.io.h.d(this.cp, "Use p86: ".concat(String.valueOf(String.valueOf(this.b1))));
                }
                if (!this.b1) {
                    this.b0 = false;
                }
            }
            else {
                this.b1 = false;
                this.b7 = false;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b0 = false;
            this.b1 = false;
            this.b7 = false;
        }
    }
    
    public final boolean eg() {
        return this.cd;
    }
    
    public final void a(final boolean b, final int n, final boolean[] array, final boolean b2) {
        boolean[] array2 = null;
        boolean b3 = false;
        if (array != null) {
            array2 = new boolean[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[i];
                if (array2[i]) {
                    b3 = true;
                }
            }
        }
        else {
            b3 = true;
        }
        if (b3) {
            new bb(this.cp, new abh(b, n, array2, false, false, this.bk, b2)).start();
        }
        else {
            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("jiDoc-Print used with numSelected = ").append(n).append(" but nothing actually selected???!!!"))));
        }
    }
    
    public final void a(final Object o, final boolean b) {
        new bb(this.cp, new abh(false, 0, null, true, false, o, b)).start();
    }
    
    public final void b(final Object o, final boolean b) {
        new bb(this.cp, new abh(false, 0, null, false, true, o, b)).start();
    }
    
    public final void m(final String cf) {
        this.cf = cf;
    }
    
    public final void n(final String cg) {
        this.cg = cg;
    }
    
    public final void be(final boolean ce) {
        this.ce = ce;
    }
    
    public final void bf(final boolean b3) {
        this.b3 = b3;
    }
    
    public final void b(final ad ad) {
        this.e(ad);
        if (this.bo != null) {
            this.bo.initTopWindow();
        }
    }
    
    public final void eh() {
        try {
            this.ag.ae();
        }
        catch (Exception ex) {}
    }
    
    public final void c(final ad ad) {
        this.ee();
        if (this.da && this.du) {
            this.ag.b(ad, this.dv);
        }
    }
    
    public final void bg(final boolean ci) {
        this.ci = ci;
    }
    
    public final void bh(final boolean ch) {
        this.ch = ch;
    }
    
    public final void ei() {
        try {
            if (this.ch && this.ci && this.dv != null) {
                this.ag.a(this.dv);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ad ad, final String s) {
        try {
            this.ee();
            if (this.da) {
                if (this.du) {
                    this.a(ad, this.dv, s);
                }
                else if (ji.util.e.av()) {
                    ((gz)ji.util.d.a2("ji.clip.jiJ2Clipboard")).setClipboardText(this.s(s));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final String d(final ad ad) {
        String t = "";
        this.ee();
        try {
            if (this.du) {
                return this.a(ad, this.dv);
            }
            if (ji.util.e.av()) {
                final String clipboardText = ((gz)ji.util.d.a2("ji.clip.jiJ2Clipboard")).getClipboardText();
                if (!ji.util.d.by(clipboardText)) {
                    t = this.t(clipboardText);
                }
            }
        }
        catch (Exception ex) {}
        return t;
    }
    
    public final void a(final ad ad, final v v, final String s) {
        if (!this.dp) {
            this.ee();
            if (this.da) {
                try {
                    if (this.du) {
                        this.dp = true;
                        v.e(this.s(s));
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                finally {
                    this.dp = false;
                }
            }
        }
    }
    
    private final String s(final String s) {
        return ji.util.d.b(ji.util.d.b(ji.util.d.b(s, "\r", ""), "\n", "<CRLF>"), "<CRLF>", "\r\n");
    }
    
    private final String t(final String s) {
        return ji.util.d.b(ji.util.d.b(s, "\r\n", "<CRLF>"), "<CRLF>", "\n");
    }
    
    public final String a(final ad ad, final v v) {
        String t = "";
        if (this.dq) {
            return null;
        }
        while (this.dp) {
            ji.util.d.b(100, 1003, this.cp);
        }
        try {
            this.ee();
            this.dp = true;
            ji.util.d.fd();
            final String o = this.as.o();
            if (v.f(o)) {
                final ac ac = new ac(o, false, false, 0, false, ad, this.cp);
                final byte[] array = new byte[(int)ac.w()];
                ac.a(array);
                ac.a(ad);
                t = new String(array);
                ji.io.ac.c(o, this.cp);
            }
            t = this.t(t);
        }
        catch (Exception ex) {
            this.dq = true;
            ex.printStackTrace();
        }
        finally {
            this.dp = false;
            ji.util.d.ch(t);
        }
        return t;
    }
    
    public boolean ej() {
        try {
            return this.ag.ac();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public boolean ek() {
        try {
            return this.ag.ad();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private final int f7() {
        int am = 1;
        final dx w = this.w();
        if (w != null) {
            am = w.am;
        }
        return am;
    }
    
    public final void bi(final boolean b4) {
        this.b4 = b4;
    }
    
    public final void o(final String dx) {
        this.dx = dx;
    }
    
    public final String el() {
        if (ji.util.d.by(this.dx)) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.ay.a()))).append(" ").append(this.ao(270))));
        }
        return this.dx;
    }
    
    public final String am(final int n) {
        String s;
        if (ji.util.d.by(this.dx)) {
            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.res.ay.a()))).append(" ").append(this.ao(270))));
        }
        else {
            s = this.dx;
        }
        if (n > 0) {
            s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(n).append(" ").append(this.ao(377)).append(")"))))));
        }
        return s;
    }
    
    public final void em() {
        try {
            if (this.er && this.eq == null) {
                final dx r = this.ag.r();
                if (this.d2 > 0) {
                    r.u = this.d2;
                }
                (this.eq = new cy(this.bk, this, this.cp, this.cu, true, true)).a(this.e, r.f, r.f, r.h, this.s, this.s, false, this.g, r, this.k, this.l, null, this.c, this.ag.c4());
                this.eq.ac(false);
                if (this.d2 > 0) {
                    this.eq.m(this.d2);
                    this.eq.bw(this.d4);
                }
                this.eq.ab(true);
                this.ag.ab(true);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void an(final int n) {
        try {
            if (this.er && this.eq != null && this.ag.r(n) == null) {
                this.eq.d(n, 2);
                this.eq.b3(false);
                if (this.eq.r(n) != null && !this.ag.b(this.eq.r(n), n)) {
                    ji.io.ac.c(this.eq.r(n), this.cp);
                }
                this.eq.q(n);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void en() {
        try {
            if (this.eq != null) {
                this.eq.gn();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void eo() {
        try {
            if (this.eq != null) {
                this.eq.dc();
                this.eq.releaseResources();
                this.eq = null;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void a(boolean b, int n, boolean[] array, final boolean q, final boolean b2, final ad ad) {
        int n2 = 1;
        this.ga();
        this.e(ad);
        int b3 = this.b1 ? 1 : 0;
        final boolean b4 = false;
        this.q = q;
        PrintJob a = null;
        this.r = false;
        if (!this.b1) {
            b3 = 1;
            if (b3 != 0 && b3 == 0) {
                try {
                    final SecurityManager securityManager = System.getSecurityManager();
                    if (securityManager != null) {
                        securityManager.checkPrintJobAccess();
                        b3 = 1;
                    }
                    else {
                        b3 = 1;
                    }
                }
                catch (Exception ex3) {}
            }
        }
        if (b3 != 0) {
            try {
                final Frame b5 = ji.util.d.b(this);
                if (b5 != null || this.b1) {
                    this.o = null;
                    this.p = null;
                    final Properties properties = new Properties();
                    String s;
                    if (this.e(true) > 1) {
                        if (n > 0) {
                            s = this.ao(379);
                        }
                        else if (b) {
                            s = this.ao(370);
                        }
                        else {
                            s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(367)))).append(" ").append(this.af()).append(this.ao(380)).append(" ").append(this.e(true))));
                        }
                    }
                    else {
                        s = this.ao(371);
                    }
                    if (this.ag != null && (this.ag.bc() || this.ag.bd() || !this.ag.de())) {
                        return;
                    }
                    this.cd = true;
                    this.be = false;
                    this.requestFocus();
                    if (this.b0 && ji.util.d.ad(this.cp) && ji.util.d.db()) {
                        try {
                            if (this.b3 && ji.util.d.da()) {
                                this.f6();
                                if (this.b4 && ji.util.e.ae()) {
                                    if (!this.dn.p(false, ad) && (this.f7() > 1 || this.ag.bl(true))) {
                                        ad.em(!ji.util.d.a(this.ao(636), String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(637)))).append("\n").append(this.ao(638)).append("\n").append(this.ao(639)).append("\n \n").append(this.ao(640)).append("\n").append(this.ao(641)).append("\n").append(this.ao(642)).append("\n \n").append(this.ao(643)).append("\n").append(this.ao(644)).append("\n \n").append(this.ao(645)).append("\n \n"))), this.bk, this, this.cp));
                                        this.dn.o(true, ad);
                                    }
                                }
                                else if (this.dn.p(false, ad)) {
                                    this.dn.o(false, ad);
                                }
                            }
                        }
                        catch (Exception ex4) {}
                        this.b3 = false;
                        String l = "";
                        if (ji.util.d.ca()) {
                            l = this.as.l();
                        }
                        if (ad.ba()) {
                            ji.io.h.d(this.cp, "Getting print job");
                        }
                        try {
                            ad.j4();
                            boolean da = false;
                            int n4;
                            int n3 = n4 = this.af();
                            if (this.e(true) > 1) {
                                if (n <= 0) {
                                    if (b) {
                                        n3 = 1;
                                        n4 = this.e(true);
                                        da = ji.util.d.da();
                                    }
                                    else if (array != null) {
                                        for (int i = 0; i < array.length; ++i) {
                                            if (array[i]) {
                                                n3 = i + 1;
                                                n4 = i + 1;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            else if (array != null) {
                                for (int j = 0; j < array.length; ++j) {
                                    if (array[j]) {
                                        n3 = j + 1;
                                        n4 = j + 1;
                                        break;
                                    }
                                }
                            }
                            boolean b6 = true;
                            if (ji.util.e.s(this.cp) && !ji.util.i.c(276)) {
                                b6 = false;
                            }
                            this.b1 = this.bo.getPrintJob(s, 0, 0, b6, ji.util.e.r(this.cp), ji.util.d.da(), l, (ji.util.d.eg() & ji.util.d.av(this.cp)) && !ji.util.d.do(), ji.util.d.br(), n3, n4, ji.util.i.c(77));
                            if (da && (this.bo.getFromPage() != n3 || this.bo.getToPage() != n4)) {
                                array = new boolean[this.e(true)];
                                b = false;
                                final int min = Math.min(Math.max(this.bo.getFromPage(), 0), array.length);
                                final int min2 = Math.min(Math.min(this.bo.getToPage(), this.e(true)), array.length);
                                n = 0;
                                for (int k = min - 1; k < min2; ++k) {
                                    array[k] = true;
                                    ++n;
                                }
                            }
                        }
                        finally {
                            ad.j5();
                        }
                        if (this.bo.getCopies() < 0) {
                            n2 = -this.bo.getCopies();
                        }
                        if (ad.ba()) {
                            ji.io.h.d(this.cp, String.valueOf(String.valueOf(new StringBuffer("Job: ").append(this.b1).append("/").append(this.bo.isLoaded()).append("/").append(n2))));
                        }
                        this.requestFocus();
                        if (!this.bo.isLoaded()) {
                            this.f5();
                        }
                    }
                    Label_1251: {
                        if (!this.b7) {
                            Label_1241: {
                                if (ji.util.e.av()) {
                                    if (!this.bd) {
                                        break Label_1241;
                                    }
                                }
                                try {
                                    ad.j4();
                                    a = ji.sec.f.a(b5, s, properties, this.cp);
                                    break Label_1251;
                                }
                                finally {
                                    ad.j5();
                                }
                            }
                            this.be = true;
                            this.n = true;
                        }
                    }
                    if (a != null || this.be || this.b1) {
                        final boolean b7 = false;
                        try {
                            this.ag.am(true);
                        }
                        catch (Exception ex5) {}
                        if (this.q) {
                            ji.util.e.e(true);
                            (this.o = this.ag).bc(false);
                            this.o.a4(true);
                            this.o.a5(false);
                        }
                        else {
                            ji.util.e.e(false);
                            (this.o = new cy(ad, this, this.cp, this.cu, true, false)).ar(true);
                            this.o.p(true);
                            this.o.q(this.ag.u());
                            this.o.b((af)this);
                            this.o.a5(true);
                            this.o.a(this.ag.a3(), this.ag.a4());
                            this.o.ae(this.ct());
                            this.o.a4(!this.b1);
                            this.o.aw(this.a4());
                            this.o.cq(this.a5());
                            this.o.c(this.a3());
                            this.o.cx(this.ep());
                            this.o.e(this.ag.a5());
                            if (ji.util.e.i()) {
                                this.o.cy(this.cg());
                            }
                            this.o.bc(true);
                            this.ag.a(this.o);
                        }
                        this.o.bi(q);
                        this.o.a(this.dg);
                        this.o.as(true);
                        try {
                            this.o.b(this.ag.da());
                        }
                        catch (Exception ex6) {}
                        if (this.cf != null) {
                            this.o.g(this.cf);
                        }
                        if (this.cg != null) {
                            this.o.h(this.cg);
                        }
                        this.o.a6(this.ce);
                        if (this.t != null) {
                            (this.p = new cy(ad, this, this.cp, this.cu, true, false)).p(this.b1 && !this.q);
                            this.p.bc(false);
                            this.p.a4(!this.b1);
                            this.p.a5(true);
                            this.p.b((af)this);
                            this.p.g("false");
                            this.p.cx(this.ep());
                            this.p.h("false");
                            this.p.b(true);
                        }
                        if (this.ag.fz() != null) {
                            this.o.b(this.ag.fz());
                        }
                        else if (this.ag.f0() != null) {
                            this.o.a(this.ag.f0());
                        }
                        else {
                            this.o.b((Color)null);
                        }
                        final int d1 = this.ag.d1();
                        if (this.ag.d2() != null) {
                            this.o.a(this.ag.d2(), d1);
                        }
                        else if (this.ag.d3() != null) {
                            this.o.a(this.ag.d3(), d1);
                        }
                        if (this.be) {
                            this.br = 1;
                            this.ag.h(this.br);
                            this.ah.e(this.br);
                            this.o.cz(this.ag.gh());
                            this.bm = new acm(this, this.o, a, this.bo, b, b7, n, array, this.q, b2, n2);
                            (ji.document.cw.bz = new we(this.cp, this.bm)).start();
                            return;
                        }
                        boolean lastPageFirst;
                        int n5;
                        if (this.b1) {
                            lastPageFirst = false;
                            n5 = this.bo.getXResolution();
                        }
                        else {
                            n5 = a.getPageResolution();
                            lastPageFirst = a.lastPageFirst();
                        }
                        boolean b8 = true;
                        if (!ji.util.i.c(20) && ji.util.e.az()) {
                            b8 = false;
                        }
                        this.bl = new acn(this, a, this.bo, n5, b, lastPageFirst, n, array, this.q, b2, n2);
                        if (b8) {
                            (ji.document.cw.by = new we(this.cp, this.bl)).start();
                            return;
                        }
                        try {
                            ad.setEnabled(false);
                            this.bl.run();
                            return;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            return;
                        }
                        finally {
                            ad.setEnabled(true);
                        }
                    }
                    if (!this.b7) {
                        ji.util.d.a(this.ao(371), this.ao(383), this, 1, null, this, this.cp);
                    }
                    int ea = 1;
                    try {
                        if (this.o != null) {
                            ea = this.o.ea();
                        }
                    }
                    catch (Exception ex7) {}
                    this.h(ea, false);
                    this.f5();
                }
                else {
                    if (!this.b7) {
                        ji.util.d.a(this.ao(371), this.ao(384), this, 5, null, this, this.cp);
                    }
                    int ea2 = 1;
                    try {
                        if (this.o != null) {
                            ea2 = this.o.ea();
                        }
                    }
                    catch (Exception ex8) {}
                    this.h(ea2, false);
                    this.f5();
                }
            }
            catch (Exception ex2) {
                if (a != null) {
                    a.end();
                }
                this.f5();
                ji.util.d.a(ex2, this.ao(371), this, 5, this, this.cp);
            }
        }
        else {
            int ea3 = 1;
            try {
                if (this.o != null) {
                    ea3 = this.o.ea();
                }
            }
            catch (Exception ex9) {}
            this.h(ea3, false);
            String s2;
            if (b4 || ji.util.d.dj() || ji.util.d.dk()) {
                if (ji.util.d.aj(this.cp)) {
                    s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(245)))).append("\n").append(ji.util.d.af(this.cp))));
                }
                else {
                    s2 = this.ao(244);
                }
            }
            else {
                s2 = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.ao(387)))).append("\n").append(this.ao(388)).append("\n").append(this.ao(19))));
            }
            try {
                ji.util.d.a(this.ao(371), s2, this, 30, null, this, this.cp);
            }
            catch (Exception ex10) {}
        }
    }
    
    public final boolean ep() {
        try {
            return this.ag != null && this.ag.gf();
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public final void bj(final boolean b) {
        try {
            if (this.ag != null) {
                this.ag.cx(b);
            }
        }
        catch (Exception ex) {}
    }
    
    public void bk(final boolean bq) {
        this.bq = bq;
    }
    
    public void h(final int n, final boolean b) {
        this.bq = true;
        if (!this.q && this.o != null) {
            ji.util.d.a(this.o, this.cp);
        }
        this.o = null;
        this.bl = null;
        ji.document.cw.bn = null;
        ji.document.cw.by = null;
        this.f5();
        try {
            this.ag.am(false);
        }
        catch (Exception ex) {}
        this.f8();
        if (b) {
            this.a(27, "printended", false);
            if (ji.util.d.e5()) {
                try {
                    ji.util.d.a(this.ao(371), this.ao(381), this, 30, null, this, this.cp);
                }
                catch (Exception ex2) {}
            }
            if (this.ct) {
                ji.util.e.ag(String.valueOf(String.valueOf(this.ao(381))).concat("."));
                this.b(new a6(this, 9, ""));
            }
        }
        else {
            if (this.ct) {
                ji.util.e.ag(String.valueOf(String.valueOf(this.ao(383))).concat("."));
                this.b(new a6(this, 9, ""));
            }
            this.a(26, "printcancelled", false);
        }
        this.b(new a6(this, 10, ""));
        ji.util.e.ag(null);
        this.e(new a6(this, 4, 0));
    }
    
    private final void ag(final cw cw) {
        try {
            this.c5 = false;
            if (this.p != null) {
                this.cv = null;
                this.cw = null;
                this.cx = null;
                this.cz = null;
                this.c0 = new c("docsepproperties1");
                this.c1 = null;
                this.c2 = null;
                this.c3 = null;
                this.c4 = null;
                this.p.aa(false);
                boolean b = false;
                try {
                    if (this.t[0] instanceof String) {
                        this.cw = this.l.a((String)this.t[0], this.u(), this.bk, this);
                    }
                    else {
                        this.cw = this.l.a((URL)this.t[0], true, true, this.u(), this.fl(), this.fk(), true, this.bk, this, this.ag);
                        this.c1 = this.l.g();
                    }
                }
                catch (Exception ex) {
                    this.cw = ji.util.e.a(ex, cw, this.bk, this.cp);
                    b = true;
                }
                this.c2 = this.l.i();
                if (!b) {
                    this.c3 = this.l.j();
                    this.c4 = this.l.k();
                }
                else {
                    this.c3 = ji.util.d.bh(this.cw);
                    this.c4 = this.c3;
                }
                if (this.cw != null) {
                    this.cv = new ac(this.cw, false, false, 0, this.bk, this.cp);
                    this.f6();
                    this.cx = ew.a(this.t[0], this.cv, this.t[0].toString(), this.cw, cw, false, this.bk, this.c3, this.c4, this.cp, false, this.dw.width, this.dw.height, 1, false, this.al, this.getSize(), false, ji.util.i.c(159), false, null, null, null, false, false, false, b);
                    this.ed = this.cx;
                    if (this.ed != null) {
                        this.ea = this.ed.bj;
                    }
                    this.c2 = this.l.i();
                    this.cz = this.cx.bj;
                    this.cx.bj = null;
                    this.cx.a3 = this.c1;
                    this.cx.a8 = this.c3;
                    this.cx.a6 = this.c3;
                    this.cx.a7 = this.c4;
                    this.cx.f = this.t[0].toString();
                    this.cx.bn = true;
                    this.cx.bo = this.ag.cm();
                    this.cx.bp = this.ag.j();
                    this.cx.h = this.a4;
                    this.p.l(this.ag.cm());
                    this.p.a(this.ag.j());
                    final df dr = this.bk.dr();
                    if (dr != null) {
                        if (this.d6 == null) {
                            this.d6 = new c("jiDocumentAnnotTemplatePages");
                        }
                        this.p.k(this.bk.n());
                        this.p.a(dr, this.d6, this.bk.dq(), false);
                    }
                    this.p.a(this.cv, this.cx.f, this.cx.f, this.cx.h, this.t, this.t, false, this.cz, this.cx, this.c0, this.l, null, cw, this.bb);
                    this.p.a2(this.b1);
                    this.p.a3(this.b2);
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    public void bl(final boolean ef) {
        this.ef = ef;
    }
    
    public boolean eq() {
        return this.ef;
    }
    
    public void bm(final boolean ee) {
        this.ee = ee;
    }
    
    public boolean er() {
        return this.ee;
    }
    
    private final void f8() {
        try {
            if (this.p != null) {
                this.p.bj();
                this.p.q();
                this.p.dc();
                this.p.t();
                this.p.releaseResources();
                this.p = null;
                if (this.c0 != null) {
                    this.c0.c();
                }
            }
        }
        catch (Exception ex) {}
        this.es();
    }
    
    public final void es() {
        try {
            if (this.d6 != null) {
                this.d6.c();
                this.d6 = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private void a(final o7[] array, final int n) {
        if (array != null) {
            switch (array[n - 1].a) {
                case 0: {
                    this.b9 = 0;
                    this.ca = 0;
                    this.cb = 0;
                    this.cc = 255;
                    break;
                }
                case 1: {
                    this.b9 = 0;
                    this.ca = 255;
                    this.cb = 255;
                    this.cc = 255;
                    break;
                }
                case 2: {
                    this.b9 = 255;
                    this.ca = 0;
                    this.cb = 255;
                    this.cc = 255;
                    break;
                }
                case 3: {
                    this.b9 = 255;
                    this.ca = 255;
                    this.cb = 0;
                    this.cc = 255;
                    break;
                }
            }
        }
        else {
            this.b9 = 0;
            this.ca = 0;
            this.cb = 0;
            this.cc = 255;
        }
    }
    
    private void f9() {
        try {
            if (ji.util.d.cy()) {
                ji.io.h.d(this.cp, "Checking print thread #1");
            }
            final we by = ji.document.cw.by;
            if (by != null) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.cp, "Existing print thread #1 is set");
                }
                if (by.a()) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.cp, "Existing print thread #1 needs terminating");
                    }
                    by.interrupt();
                    ji.document.cw.by = null;
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        try {
            if (ji.util.d.cy()) {
                ji.io.h.d(this.cp, "Checking print thread #2");
            }
            final we bz = ji.document.cw.bz;
            if (bz != null) {
                if (ji.util.d.cy()) {
                    ji.io.h.d(this.cp, "Existing print thread #2 is set");
                }
                if (bz.a()) {
                    if (ji.util.d.cy()) {
                        ji.io.h.d(this.cp, "Existing print thread #2 needs terminating");
                    }
                    bz.interrupt();
                    ji.document.cw.bz = null;
                }
            }
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
        }
    }
    
    private void ga() {
        try {
            this.f9();
            while (ji.document.cw.by != null || ji.document.cw.bz != null) {
                this.l(String.valueOf(String.valueOf(this.ao(391))).concat("..."));
                ji.util.d.b(500, 131, this.cp);
                this.l("");
                ji.util.d.b(500, 132, this.cp);
            }
            ji.util.d.c(ji.document.cw.by, this.cp);
            this.l("");
        }
        catch (Exception ex) {}
    }
    
    public final void et() {
        try {
            if (this.o != null) {
                this.al(0);
                this.r = true;
                this.o.gm();
            }
        }
        catch (Exception ex) {}
    }
    
    public void print(final Graphics graphics) {
        if (this.cd) {
            return;
        }
        if (!this.bf) {
            try {
                this.ga();
                if (this.al == 5 || this.al == 6) {
                    this.ah.print(graphics);
                }
                else {
                    this.ag.print(graphics);
                }
                this.ga();
            }
            finally {
                this.bf = false;
            }
        }
    }
    
    public final boolean eu() {
        try {
            return this.al == 5 || this.al == 6 || this.ag.g();
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        try {
            if (actionEvent.getSource() instanceof jiImageButton) {
                this.et();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void b(final fv fv) {
        if (ji.util.d.am()) {
            if (this.do != null) {
                this.do.a(new a9(this, fv, false));
            }
        }
        else {
            this.c(fv);
        }
    }
    
    private final void c(final fv fv) {
        if (this.cd) {
            return;
        }
        this.dm = null;
        if (this.am != null) {
            final c am = this.am;
            for (int b = am.b(), i = 0; i < b; ++i) {
                if (!fv.d()) {
                    ((ae)am.b(i)).a(fv);
                }
            }
        }
    }
    
    public void finalize() {
        if (ji.document.cw.aw > 0) {
            --ji.document.cw.aw;
        }
        super.finalize();
        if (ji.util.d.cz()) {
            ji.io.h.d(this.cp, "finalize doc handler ".concat(String.valueOf(String.valueOf(this))));
        }
    }
    
    public final String ev() {
        if (this.ag != null) {
            return this.ag.d6();
        }
        return null;
    }
    
    public long ew() {
        return this.ag.g6();
    }
    
    public final boolean ex() {
        return this.bk.hb();
    }
    
    public final void ey() {
        if (this.ag != null) {
            this.ag.g1();
        }
    }
    
    public final void bn(final boolean b) {
        if (this.ag != null) {
            this.ag.da(b);
        }
    }
    
    public final boolean ez() {
        return this.ag != null && this.ag.e3();
    }
    
    public final boolean e0() {
        return this.ag != null && this.ag.ds() != null && this.ag.ds().isAttachmentAvailable();
    }
    
    public final gm[] e1() {
        gm[] attachmentData = new gm[0];
        if (this.ag != null && this.ag.ds() != null) {
            try {
                attachmentData = this.ag.ds().getAttachmentData();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return attachmentData;
    }
    
    public final void a(final gm gm, final OutputStream outputStream) throws Exception {
        if (this.ag != null && this.ag.ds() != null) {
            this.ag.ds().streamAttachment(gm, outputStream);
        }
    }
    
    public void a(final fz fz) {
        if (this.ah != null) {
            this.ah.a(fz);
        }
    }
    
    public void e2() {
        if (this.ag != null) {
            this.ag.gi();
        }
    }
    
    static {
        cw.j = null;
        cw.aw = 0;
        cw.a6 = null;
        cw.bn = null;
        cw.by = null;
        cw.bz = null;
        cw.cr = 0;
    }
    
    private class ub
    {
        public boolean a;
        public boolean b;
        
        private ub(final cw cw) {
            this.a = false;
            this.b = false;
        }
    }
    
    class acn implements Runnable
    {
        cw a;
        boolean b;
        int c;
        boolean d;
        PrintJob e;
        jiPrinti f;
        int g;
        boolean[] h;
        boolean i;
        boolean j;
        boolean k;
        int l;
        
        public acn(final cw a, final PrintJob e, final jiPrinti f, final int c, final boolean b, final boolean b2, final int g, final boolean[] h, final boolean i, final boolean j, final int l) {
            this.a = null;
            this.b = false;
            this.c = 0;
            this.d = false;
            this.e = null;
            this.f = null;
            this.g = 0;
            this.h = null;
            this.i = false;
            this.j = false;
            this.k = true;
            this.l = 1;
            this.e = e;
            this.f = f;
            this.c = c;
            this.a = a;
            this.b = b;
            this.g = g;
            this.h = h;
            this.i = i;
            this.j = j;
            this.l = l;
        }
        
        public void run() {
            this.a(this.a, this.e, this.f, this.c, this.b, this.d, this.g, this.h, this.i, this.j, this.l);
        }
        
        public void a(final cw cw, final PrintJob printJob, final jiPrinti jiPrinti, final int n, final boolean b, final boolean b2, final int n2, final boolean[] array, final boolean b3, final boolean b4, int n3) {
            boolean b5 = true;
            boolean[] dv = null;
            boolean b6 = false;
            boolean b7 = true;
            boolean ba = false;
            if (cw != null && cw.bk != null) {
                ba = cw.bk.ba();
            }
            if (!ji.util.d.da()) {
                if (ji.util.d.bs() > 1) {
                    n3 = ji.util.d.bs();
                }
                else {
                    n3 = 1;
                }
            }
            try {
                if (b3) {
                    cw.this.b(new d7(this, 12, cw.this.af()));
                }
                else {
                    cw.this.b(new d7(this, 13, cw.this.af()));
                }
                cw.this.r = false;
                final dx r = cw.this.ag.r();
                try {
                    cw.this.br = 1;
                    cw.this.o.be();
                    cw.this.ag.h(cw.this.br);
                    cw.this.ag.bg(cw.this.ct);
                    cw.this.ah.e(cw.this.br);
                    cw.this.ag.a1(true);
                    cw.this.ah.h(true);
                    if (!b3) {
                        cw.this.o.aa(cw.this.ag.am());
                        cw.this.o.a(cw.this.d);
                        if (cw.this.d2 > 0) {
                            cw.this.o.m(cw.this.d2);
                            cw.this.o.bw(cw.this.d4);
                            r.u = cw.this.d2;
                        }
                        cw.this.o.a(cw.this.e, r.f, r.f, r.h, cw.this.s, cw.this.s, false, cw.this.g, r.a(true), cw.this.k, cw.this.l, null, cw.this.c, cw.this.ag.c4());
                        if (ji.util.d.ey() || b4) {
                            cw.this.o.a(cw.this.ag.c7(), -1);
                        }
                    }
                    final c c = new c("docsepproperties1");
                    if (cw.this.p != null) {
                        cw.this.ag(cw);
                    }
                    if (ji.util.e.ag() && !b3) {
                        cw.this.o.a(cw.this.ag.co());
                        cw.this.o.a(cw.this.ag.cj(), cw.this.ag.bq(), cw.this.ag.bp(), false);
                        cw.this.o.cz(cw.this.ag.gh());
                    }
                    cw.this.o.a2(cw.this.b1);
                    cw.this.o.a3(cw.this.b2);
                    if (ji.util.d.di() == 1) {
                        dv = cw.this.ag.dv();
                        b6 = false;
                        for (int i = 0; i < dv.length; ++i) {
                            if (dv[i]) {
                                b6 = true;
                            }
                        }
                    }
                    boolean b8 = !ji.util.e.t(cw.this.cp) && !ji.util.e.v(cw.this.cp);
                    if (ji.util.i.c(148)) {
                        if (ba) {
                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Threading disabled by override"));
                        }
                        b8 = false;
                    }
                    if (cw.this.b1) {
                        if (ba) {
                            ji.util.d.c(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc1..."));
                        }
                        if (ji.util.i.c(38)) {
                            cw.this.bo.reverseMonoColors();
                        }
                        if (ji.util.i.c(39)) {
                            cw.this.bo.forgetPrintSettings();
                        }
                        if (b6) {
                            if (ba) {
                                ji.util.d.c(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc2..."));
                            }
                            b7 = cw.this.bo.startDoc(cw.this.am(0), b8);
                            cw.this.b5 = true;
                        }
                        else if (b && cw.this.cd()) {
                            if (ba) {
                                ji.util.d.c(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc3..."));
                            }
                            b7 = cw.this.bo.startDoc(cw.this.am(cw.this.e(true)), b8);
                            cw.this.b5 = true;
                        }
                        else if (n2 > 1) {
                            if (ba) {
                                ji.util.d.c(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc4..."));
                            }
                            b7 = cw.this.bo.startDoc(cw.this.am(n2), b8);
                            cw.this.b5 = true;
                        }
                        else {
                            if (ba) {
                                ji.util.d.c(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc5..."));
                            }
                            b7 = cw.this.bo.startDoc(cw.this.am(0), b8);
                            cw.this.b5 = true;
                        }
                    }
                    if (ba) {
                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: StartDoc6..."));
                    }
                    final int n4 = 0;
                    n3 = Math.max(n3, 1);
                    for (int n5 = 0; n5 < n3 && cw.this.az; ++n5) {
                        if (b7 && n2 > 0 && !b6 && !b3) {
                            if (b2) {
                                final int e = cw.this.e(true);
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageA..."));
                                }
                                this.a(printJob, jiPrinti, n, b, e, cw.this.a(e), e);
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageB..."));
                                }
                                for (int n6 = e; n6 > 0 && !cw.this.r && cw.this.az; --n6) {
                                    if (array[n6 - 1]) {
                                        this.a(printJob, jiPrinti, n, b, n6 - 1, cw.this.a(n6 - 1), e);
                                        cw.this.o.dr();
                                        cw.this.o.d(n6, 2);
                                        if (ba) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageA..."));
                                        }
                                        if (jiPrinti != null && cw.this.b1) {
                                            cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                        }
                                        else {
                                            cw.this.o.a(printJob, null, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, n4, null, b4);
                                        }
                                        if (ba) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageB..."));
                                        }
                                        if (cw.this.o != null) {
                                            cw.this.o.bb(false);
                                        }
                                    }
                                    if (cw.this.o != null && cw.this.o.bd()) {
                                        break;
                                    }
                                }
                            }
                            else {
                                final int e2 = cw.this.e(true);
                                for (int n7 = 1; n7 < e2 + 1 && !cw.this.r && cw.this.az; ++n7) {
                                    if (array[n7 - 1]) {
                                        if (ba) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageC..."));
                                        }
                                        this.a(printJob, jiPrinti, n, b, n7 - 1, cw.this.a(n7 - 1), e2);
                                        if (ba) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageD..."));
                                        }
                                        cw.this.o.dr();
                                        cw.this.o.d(n7, 2);
                                        if (ba) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageC..."));
                                        }
                                        if (jiPrinti != null && cw.this.b1) {
                                            cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                        }
                                        else {
                                            cw.this.o.a(printJob, null, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, n4, null, b4);
                                        }
                                        if (cw.bk.ba()) {
                                            ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageD..."));
                                        }
                                        if (cw.this.o != null) {
                                            cw.this.o.bb(false);
                                        }
                                    }
                                    if (cw.this.o != null && cw.this.o.bd()) {
                                        break;
                                    }
                                }
                                if (cw.this.o != null && cw.this.o.bd()) {
                                    break;
                                }
                                this.a(printJob, jiPrinti, n, b, e2, cw.this.a(e2), e2);
                            }
                            if (cw.this.o != null && cw.this.o.bd()) {
                                break;
                            }
                            if (cw.this.b1 && cw.this.ee) {
                                if (!ji.util.e.r()) {
                                    cw.this.bo.endPage();
                                    ji.util.e.i(true);
                                }
                                int n8 = 0;
                                final df cj = cw.this.ag.cj();
                                if (cj != null) {
                                    final boolean ch = cw.this.ag.ch();
                                    for (int e3 = cw.this.e(true), n9 = 0; n9 < e3 && cw.this.az; ++n9) {
                                        if (array[n9]) {
                                            final dg[] a = cj.a(n9 + 1, 8, true, cw.this.ag.cn(), cw.this.ag.cm(), ch, cw.this.a5());
                                            final int length = a.length;
                                            final String[] array2 = new String[length];
                                            final String[] array3 = new String[length];
                                            for (int j = 0; j < length; ++j) {
                                                array2[j] = a[j].h();
                                                array3[j] = a[j].ev();
                                            }
                                            if (length > 0) {
                                                if (n8 == 0) {
                                                    jiPrinti.startNoteAnnotationPages(cw.this.bk, cw.this.cp);
                                                    n8 = 1;
                                                }
                                                jiPrinti.printNoteAnnotationPageText(cw.this.bk, n9 + 1, array2, array3, cw.this.cp, e3);
                                            }
                                        }
                                    }
                                    if (n8 != 0) {
                                        jiPrinti.endNoteAnnotationPages();
                                    }
                                }
                            }
                        }
                        else if (b7 && b && !b6 && !b3) {
                            if (b2 && cw.this.az) {
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageE..."));
                                }
                                final int e4 = cw.this.e(true);
                                this.a(printJob, jiPrinti, n, b, e4, cw.this.a(e4), e4);
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageF..."));
                                }
                                for (int n10 = e4; n10 > 0 && !cw.this.r && cw.this.az; --n10) {
                                    this.a(printJob, jiPrinti, n, b, n10 - 1, cw.this.a(n10 - 1), e4);
                                    cw.this.o.dr();
                                    cw.this.o.d(n10, 2);
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageE..."));
                                    }
                                    if (jiPrinti != null && cw.this.b1) {
                                        cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    else {
                                        cw.this.o.a(printJob, null, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageF..."));
                                    }
                                    if (cw.this.o != null) {
                                        cw.this.o.bb(false);
                                    }
                                }
                            }
                            else if (cw.this.az) {
                                final int e5 = cw.this.e(true);
                                for (int n11 = 1; n11 < e5 + 1 && !cw.this.r && cw.this.az; ++n11) {
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageG..."));
                                    }
                                    this.a(printJob, jiPrinti, n, b, n11 - 1, cw.this.a(n11 - 1), e5);
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageH..."));
                                    }
                                    cw.this.o.dr();
                                    cw.this.o.d(n11, 2);
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageG..."));
                                    }
                                    if (jiPrinti != null && cw.this.b1) {
                                        cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    else {
                                        cw.this.o.a(printJob, null, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageH..."));
                                    }
                                    if (cw.this.o != null) {
                                        cw.this.o.bb(false);
                                    }
                                    if (cw.this.o != null && cw.this.o.bd()) {
                                        break;
                                    }
                                }
                                if (cw.this.o != null && cw.this.o.bd()) {
                                    break;
                                }
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageI..."));
                                }
                                this.a(printJob, jiPrinti, n, b, e5, cw.this.a(e5), e5);
                                if (ba) {
                                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print Sep PageJ..."));
                                }
                            }
                            if (cw.this.b1 && cw.this.ee && cw.this.az) {
                                if (!ji.util.e.r()) {
                                    cw.this.bo.endPage();
                                    ji.util.e.i(true);
                                }
                                int n12 = 0;
                                final df cj2 = cw.this.ag.cj();
                                if (cj2 != null) {
                                    final boolean ch2 = cw.this.ag.ch();
                                    cw.this.ag.f2();
                                    for (int e6 = cw.this.e(true), n13 = 0; n13 < e6 && cw.this.az; ++n13) {
                                        final dg[] a2 = cj2.a(n13 + 1, 8, true, cw.this.ag.cn(), cw.this.ag.cm(), ch2, cw.this.a5());
                                        final int length2 = a2.length;
                                        final String[] array4 = new String[length2];
                                        final String[] array5 = new String[length2];
                                        for (int k = 0; k < length2; ++k) {
                                            array4[k] = a2[k].h();
                                            array5[k] = a2[k].ev();
                                        }
                                        if (length2 > 0) {
                                            if (n12 == 0) {
                                                jiPrinti.startNoteAnnotationPages(cw.this.bk, cw.this.cp);
                                                n12 = 1;
                                            }
                                            jiPrinti.printNoteAnnotationPageText(cw.this.bk, n13 + 1, array4, array5, cw.this.cp, e6);
                                        }
                                    }
                                    if (n12 != 0) {
                                        jiPrinti.endNoteAnnotationPages();
                                    }
                                }
                            }
                        }
                        else if (b7 && cw.this.az) {
                            boolean b9 = true;
                            if (ji.util.d.di() == 1 && !b3 && b6 && cw.this.az) {
                                if (jiPrinti != null && cw.this.b1 && cw.this.az) {
                                    final o7[] c2 = cw.this.ag.c1();
                                    cw.this.a(c2, cw.this.af());
                                    cw.this.o.a(true, cw.this.b9, cw.this.ca, cw.this.cb, cw.this.cc, true, true);
                                    final int af = cw.this.af();
                                    cw.this.o.dr();
                                    cw.this.o.d(af, 2);
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageK..."));
                                    }
                                    cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageL..."));
                                    }
                                    for (int n14 = 0; n14 < dv.length && cw.this.az; ++n14) {
                                        if (dv[n14]) {
                                            cw.this.a(c2, n14 + 1);
                                            cw.this.o.a(true, cw.this.b9, cw.this.ca, cw.this.cb, cw.this.cc, true, false);
                                            cw.this.o.dr();
                                            cw.this.o.d(n14 + 1, 2);
                                            if (ba) {
                                                ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageM..."));
                                            }
                                            cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                            if (ba) {
                                                ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageN..."));
                                            }
                                            if (cw.this.o != null) {
                                                cw.this.o.bb(false);
                                            }
                                        }
                                        if (cw.this.o != null && cw.this.o.bd()) {
                                            break;
                                        }
                                    }
                                    if (cw.this.b1 && cw.this.ee && cw.this.az) {
                                        if (!ji.util.e.r()) {
                                            cw.this.bo.endPage();
                                            ji.util.e.i(true);
                                        }
                                        int n15 = 0;
                                        final df cj3 = cw.this.ag.cj();
                                        if (cj3 != null) {
                                            final boolean ch3 = cw.this.ag.ch();
                                            for (int n16 = 0; n16 < dv.length && cw.this.az; ++n16) {
                                                final dg[] a3 = cj3.a(n16 + 1, 8, true, cw.this.ag.cn(), cw.this.ag.cm(), ch3, cw.this.a5());
                                                final int length3 = a3.length;
                                                final String[] array6 = new String[length3];
                                                final String[] array7 = new String[length3];
                                                for (int l = 0; l < length3; ++l) {
                                                    array6[l] = a3[l].h();
                                                    array7[l] = a3[l].ev();
                                                }
                                                if (length3 > 0) {
                                                    if (n15 == 0) {
                                                        jiPrinti.startNoteAnnotationPages(cw.this.bk, cw.this.cp);
                                                        n15 = 1;
                                                    }
                                                    jiPrinti.printNoteAnnotationPageText(cw.this.bk, n16 + 1, array6, array7, cw.this.cp, cw.this.e(true));
                                                }
                                            }
                                            if (n15 != 0) {
                                                jiPrinti.endNoteAnnotationPages();
                                            }
                                        }
                                    }
                                    cw.this.o.a(false, 0, 0, 0, 0, false, false);
                                    b9 = false;
                                }
                                else {
                                    cw.this.o.b(cw.this.ag.dv());
                                }
                            }
                            if (cw.this.o != null && cw.this.o.bd()) {
                                break;
                            }
                            if (b9 && cw.this.az) {
                                this.a(printJob, jiPrinti, n, b, cw.this.af() - 1, cw.this.a(cw.this.af() - 1), cw.this.e(true));
                                if (!b3) {
                                    final int af2 = cw.this.af();
                                    cw.this.o.dr();
                                    cw.this.o.d(af2, 2);
                                }
                                if (cw.this.az) {
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageO..."));
                                    }
                                    if (jiPrinti != null && cw.this.b1) {
                                        cw.this.o.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    else {
                                        cw.this.o.a(printJob, null, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, n4, null, b4);
                                    }
                                    if (cw.this.o != null) {
                                        cw.this.o.bb(false);
                                    }
                                    if (ba) {
                                        ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print PageP..."));
                                    }
                                }
                            }
                        }
                        if (cw.this.o != null) {
                            cw.this.o.bb(false);
                        }
                    }
                }
                catch (Exception ex) {
                    b5 = false;
                    ji.util.d.a(ex, "Print Problem", cw, 5, null, cw.this.cp);
                }
                try {
                    if (!cw.this.r && !cw.this.n) {
                        if (cw.this.ag != null) {
                            cw.this.ag.h(7);
                        }
                        if (cw.this.ah != null) {
                            cw.this.ah.e(7);
                        }
                    }
                }
                catch (Exception ex3) {}
                cw.this.fa();
                cw.this.l("");
            }
            finally {
                cw.this.b(new d7(this, 11, cw.this.af()));
                cw.this.fa();
                try {
                    if (printJob != null) {
                        printJob.end();
                    }
                }
                catch (Exception ex2) {
                    if (ji.util.d.cy()) {
                        ex2.printStackTrace();
                    }
                }
                cw.this.fa();
                try {
                    if (cw.this.o != null) {
                        cw.this.o.bj();
                        if (!b3) {
                            cw.this.o.q();
                            cw.this.o.dc();
                            cw.this.o.t();
                            cw.this.o.releaseResources();
                        }
                        cw.this.o = null;
                    }
                }
                catch (Exception ex4) {}
                cw.this.f8();
                if (ba) {
                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print CloseA..."));
                }
                cw.this.f5();
                if (ba) {
                    ji.io.h.d(cw.this.cp, String.valueOf(String.valueOf(System.currentTimeMillis())).concat("p86: Print CloseB..."));
                }
                try {
                    if (cw.this.ag != null) {
                        cw.this.ag.a1(false);
                    }
                    if (cw.this.ah != null) {
                        cw.this.ah.h(false);
                    }
                }
                catch (Exception ex5) {}
                int ea = 1;
                try {
                    if (cw.this.o != null) {
                        ea = cw.this.o.ea();
                    }
                }
                catch (Exception ex6) {}
                cw.h(ea, b5 && !cw.this.r);
                if (cw.this.r) {
                    cw.this.l(String.valueOf(String.valueOf(cw.this.ao(382))).concat("."));
                }
                else {
                    cw.this.l(String.valueOf(String.valueOf(cw.this.ao(381))).concat("."));
                }
                cw.by = null;
                cw.this.r = false;
                if (cw.this.ag != null) {
                    cw.this.ag.setEnabled(true);
                }
                if (cw.this.ah != null) {
                    cw.this.ah.setEnabled(true);
                }
                cw.this.b(new d7(this, 11, cw.this.af()));
            }
        }
        
        private final void a(final PrintJob printJob, final jiPrinti jiPrinti, final int n, final boolean b, int n2, final String s, final int n3) {
            try {
                int n4 = 0;
                final int n5 = n2;
                if (ji.document.cw.this.p != null) {
                    if (n2 < ji.document.cw.this.t.length && ji.document.cw.this.t[n2] != null && !ji.document.cw.this.t[n2].toString().endsWith("-")) {
                        n4 = 1;
                    }
                    if (n4 == 0 && !ji.document.cw.this.c5) {
                        ji.document.cw.this.c5 = true;
                        if (n2 > 0 && ji.document.cw.this.t != null && n2 < n3 && ji.document.cw.this.t[0] != null && !ji.document.cw.this.t[0].toString().endsWith("-")) {
                            n4 = 1;
                            n2 = 0;
                        }
                    }
                    if (n2 < ji.document.cw.this.t.length && n4 != 0 && ji.document.cw.this.az) {
                        ji.document.cw.this.c5 = true;
                        ji.document.cw.this.p.dr();
                        ji.document.cw.this.p.d(n2 + 1, 2);
                        ji.document.cw.this.p.q(s);
                        ji.document.cw.this.p.r().v = n5 + 1;
                        if (jiPrinti != null && ji.document.cw.this.b1) {
                            ji.document.cw.this.p.a(printJob, jiPrinti, null, n, jiPrinti.getPageDimension(), true, b, 0, null, false);
                            if (!ji.util.e.r()) {
                                ji.document.cw.this.bo.endPage();
                                ji.util.e.i(true);
                            }
                        }
                        else {
                            ji.document.cw.this.p.a(printJob, jiPrinti, printJob.getGraphics(), n, printJob.getPageDimension(), true, b, 0, null, false);
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class acm implements Runnable
    {
        cw a;
        boolean b;
        boolean c;
        PrintJob d;
        jiPrinti e;
        int f;
        boolean[] g;
        boolean h;
        boolean i;
        boolean[] j;
        boolean k;
        boolean l;
        boolean m;
        int n;
        cy o;
        
        public acm(final cw a, final cy o, final PrintJob d, final jiPrinti e, final boolean b, final boolean b2, final int f, final boolean[] g, final boolean k, final boolean l, final int n) {
            this.a = null;
            this.b = false;
            this.c = false;
            this.d = null;
            this.e = null;
            this.f = 0;
            this.g = null;
            this.h = true;
            this.i = false;
            this.j = null;
            this.k = false;
            this.l = false;
            this.m = true;
            this.n = 1;
            this.o = null;
            this.o = o;
            this.d = d;
            this.e = e;
            this.a = a;
            this.b = b;
            this.f = f;
            this.g = g;
            this.k = k;
            this.l = l;
            this.n = n;
        }
        
        public void run() {
            try {
                ji.document.cw.bn = ji.util.d.a2("ji.image.jiPrintThread12");
                String s;
                if (this.b && ji.document.cw.this.cd()) {
                    s = ji.document.cw.this.am(ji.document.cw.this.e(true));
                }
                else if (this.f > 1) {
                    s = ji.document.cw.this.am(this.f);
                }
                else {
                    s = ji.document.cw.this.am(0);
                }
                ((jiJ2Interface)ji.document.cw.bn).initialize(this.o, ji.document.cw.this.p, ji.document.cw.this.t, ji.document.cw.this.ag, ji.document.cw.this.ah, this.a, this.b, this.c, this.f, this.g, this.k, this.l, ji.document.cw.this.bk, this.a, ji.document.cw.this.cp, ji.document.cw.this.cu, s);
                if (((jiJ2Interface)ji.document.cw.bn).isAccepted()) {
                    ji.document.cw.by = new we(ji.document.cw.this.cp, (Runnable)ji.document.cw.bn);
                    if (ji.document.cw.this.p != null) {
                        ji.document.cw.this.ag(this.a);
                    }
                    ji.document.cw.by.start();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                this.d = null;
                this.e = null;
                this.a = null;
                this.o = null;
                ji.document.cw.this.bm = null;
                ji.document.cw.bz = null;
            }
        }
    }
    
    private class we extends bb
    {
        String a;
        
        public we(final cw cw, final String a, final Runnable runnable) {
            super(a, runnable);
            this.a = null;
            this.a = a;
        }
        
        public boolean a() {
            return d.ck(ji.document.ad.a(this.a));
        }
    }
    
    class abh implements Runnable
    {
        boolean a;
        int b;
        boolean[] c;
        boolean d;
        boolean e;
        boolean f;
        
        public abh(final boolean a, final int b, final boolean[] c, final boolean d, final boolean f, final Object o, final boolean e) {
            this.a = false;
            this.b = 0;
            this.c = null;
            this.d = false;
            this.e = false;
            this.f = false;
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            this.f = f;
            if (d) {
                ji.document.cw.this.b(new d7(this, 12, ji.document.cw.this.af()));
                if (ji.document.cw.this.ag != null) {
                    ji.document.cw.this.ag.setEnabled(false);
                }
                if (ji.document.cw.this.ah != null) {
                    ji.document.cw.this.ah.setEnabled(false);
                }
            }
            else {
                ji.document.cw.this.b(new d7(this, 13, ji.document.cw.this.af()));
                if (ji.document.cw.this.ag != null) {
                    ji.document.cw.this.ag.setEnabled(true);
                }
                if (ji.document.cw.this.ah != null) {
                    ji.document.cw.this.ah.setEnabled(true);
                }
            }
        }
        
        public void run() {
            try {
                ji.document.cw.this.a(this.a, this.b, this.c, this.d, this.f, ji.document.cw.this.bk);
                this.c = null;
            }
            catch (Exception ex) {
                ji.document.cw.this.h(1, false);
                if (!(ex instanceof NullPointerException)) {
                    ji.util.d.a(ex, "Printing", null, 30, null, ji.document.cw.this.cp);
                }
                else {
                    ji.util.d.a(ex);
                }
            }
            finally {
                ji.document.cw.this.cd = false;
                try {
                    if (this.e) {
                        ji.util.e.au();
                    }
                }
                catch (Exception ex2) {}
                if (ji.document.cw.this.bl == null) {
                    if (ji.document.cw.this.ag != null) {
                        ji.document.cw.this.ag.setEnabled(true);
                    }
                    if (ji.document.cw.this.ah != null) {
                        ji.document.cw.this.ah.setEnabled(true);
                    }
                    ji.document.cw.this.b(new d7(this, 11, ji.document.cw.this.af()));
                }
            }
        }
    }
    
    class z5 implements Runnable
    {
        public void run() {
            try {
                ji.document.cw.this.f1();
                while (ji.document.cw.this.de && !ji.document.cw.this.av && ji.document.cw.this.az) {
                    ji.util.d.b(35, 130, ji.document.cw.this.cp);
                    ji.document.cw.this.f3();
                }
            }
            catch (Exception ex) {}
            finally {
                ji.document.cw.this.df = null;
                ji.document.cw.this.f2();
            }
        }
    }
    
    class z6 implements Runnable
    {
        public void run() {
            try {
                ji.document.cw.this.b(new d7(this, 63, ji.document.cw.this.af()));
                Dimension dimension = ji.document.cw.this.getSize();
                if (dimension.width <= 0) {
                    while (dimension.width <= 0 && !ji.document.cw.this.av && ji.document.cw.this.az && ji.document.cw.this.c != null && !ji.document.cw.this.d4()) {
                        ji.util.d.b(500, 2004, ji.document.cw.this.cp);
                        dimension = ji.document.cw.this.getSize();
                    }
                }
                else {
                    ji.util.d.b(200, 2004, ji.document.cw.this.cp);
                }
                if (!ji.document.cw.this.d4()) {
                    ji.document.cw.this.fw();
                }
                else {
                    ji.document.cw.this.dc = false;
                    ji.document.cw.this.b(new d7(this, 3, ji.document.cw.this.af()));
                }
            }
            catch (Exception ex) {}
            finally {
                ji.document.cw.this.dy = null;
            }
        }
    }
    
    class w8 implements Runnable
    {
        Vector a;
        private boolean b;
        
        public w8(final Vector a, final boolean b) {
            this.a = null;
            this.a = a;
            this.b = b;
        }
        
        public final void run() {
            ji.document.cw.this.ah.a(this.a, this.b);
        }
    }
    
    class wd implements Runnable, wc
    {
        boolean a;
        
        public wd() {
            this.a = false;
        }
        
        public void a() {
            this.a = true;
        }
        
        public void run() {
            try {
                this.a = true;
                if (ji.util.d.dv()) {
                    ji.io.h.e(ji.document.cw.this.cp, "Gen page (0)...");
                }
                while (ji.document.cw.cr > 0) {
                    ji.util.d.b(100, 127, ji.document.cw.this.cp);
                }
                if (ji.util.d.dv()) {
                    ji.io.h.e(ji.document.cw.this.cp, "Gen page (1)...");
                }
                ++ji.document.cw.cr;
                while (this.a) {
                    this.a = false;
                    ji.document.cw.this.d9();
                }
                if (ji.util.d.dv()) {
                    ji.io.h.e(ji.document.cw.this.cp, "Gen page done.");
                }
            }
            catch (Throwable t) {
                ji.util.d.a(t);
            }
            finally {
                ji.document.cw.this.a9 = null;
                ji.document.cw.this.ba = null;
                --ji.document.cw.cr;
            }
        }
    }
    
    private interface wc extends Runnable
    {
        void a();
    }
    
    class wb implements Runnable, wc
    {
        int a;
        
        public wb() {
            this.a = 0;
            synchronized (this) {
                ++this.a;
            }
        }
        
        public void a() {
            synchronized (this) {
                ++this.a;
                this.notify();
            }
        }
        
        private synchronized int b() {
            return this.a;
        }
        
        private synchronized void c() {
            this.a = 0;
        }
        
        public void run() {
            while (!ji.util.d.ck(ji.document.cw.this.cp)) {
                try {
                    if (ji.util.d.dv()) {
                        ji.io.h.e(ji.document.cw.this.cp, "Gen page (0)...");
                    }
                    while (this.b() > 0) {
                        this.c();
                        ji.document.cw.this.d9();
                    }
                    synchronized (this) {
                        if (this.b() <= 0 && !ji.util.d.ck(ji.document.cw.this.cp)) {
                            this.wait(500L);
                        }
                    }
                    if (!ji.util.d.dv()) {
                        continue;
                    }
                    ji.io.h.e(ji.document.cw.this.cp, "Gen page done.");
                }
                catch (Throwable t) {
                    ji.util.d.a(t);
                }
            }
        }
    }
    
    class z3 implements Runnable
    {
        boolean a;
        
        z3() {
            this.a = false;
        }
        
        public void a() {
            this.a = true;
        }
        
        public void run() {
            try {
                this.a = true;
                while (this.a && !ji.document.cw.this.w) {
                    this.a = false;
                    ji.document.cw.this.fz();
                    ji.document.cw.this.bb(true);
                    ji.document.cw.this.d5();
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
            finally {
                ji.document.cw.this.cs = null;
            }
        }
    }
    
    private class wt implements Runnable
    {
        int a;
        boolean b;
        
        public wt(final int a, final boolean b) {
            this.a = 1;
            this.b = true;
            this.a = a;
            this.b = b;
        }
        
        public void run() {
            try {
                if (ji.document.cw.this.e != null && !ji.document.cw.this.dc) {
                    if (ji.util.i.c(36)) {
                        ji.io.h.d(ji.document.cw.this.cp, "doc: page-run synchronizing");
                    }
                    synchronized (ji.document.cw.this.dz) {
                        if (ji.util.i.c(36)) {
                            ji.io.h.d(ji.document.cw.this.cp, "doc: page-run synchronized");
                        }
                        ji.document.cw.this.j(this.a, this.b);
                        if (ji.util.i.c(36)) {
                            ji.io.h.d(ji.document.cw.this.cp, "doc: page-run end synchronized");
                        }
                        // monitorexit(this.c.dz)
                        return;
                    }
                }
                ji.document.cw.this.j(this.a, this.b);
            }
            catch (Throwable t) {
                if (ji.util.d.cy()) {
                    t.printStackTrace();
                }
                ji.util.d.a(t);
            }
            finally {
                ji.document.cw.this.cm = null;
            }
        }
    }
    
    interface ad9
    {
    }
}
