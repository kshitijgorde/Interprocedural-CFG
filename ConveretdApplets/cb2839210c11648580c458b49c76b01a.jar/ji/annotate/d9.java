// 
// Decompiled by Procyon v0.5.30
// 

package ji.annotate;

import java.awt.event.WindowEvent;
import java.awt.event.FocusEvent;
import java.awt.Window;
import ji.v1base.gr;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import ji.util.k;
import ji.secure.ei;
import ji.io.h;
import ji.awt.bb;
import java.awt.Graphics;
import java.awt.Toolkit;
import ji.util.i;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import ji.util.e;
import java.awt.Dialog;
import ji.util.d;
import java.awt.Frame;
import ji.secure.dh;
import ji.document.ad;
import ji.v1event.af;
import java.awt.Component;
import ji.net.a0;
import ji.awt.c;
import ji.graphic.bs;
import java.awt.List;
import ji.awt.eb;
import java.awt.CheckboxGroup;
import ji.v1base.ek;
import java.awt.Checkbox;
import java.awt.Label;
import ji.v1base.jiPanel;
import java.awt.event.ItemListener;
import java.awt.event.FocusListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import ji.v1base.bn;
import ji.io.p;
import ji.v1base.bl;

public class d9 extends bl
{
    int a;
    int b;
    private p c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    String[] i;
    String[] j;
    boolean k;
    bn l;
    bn m;
    bn n;
    bn o;
    bn p;
    bn q;
    bn r;
    bn s;
    WindowAdapter t;
    KeyListener u;
    MouseListener v;
    ActionListener w;
    FocusListener x;
    ItemListener y;
    jiPanel z;
    Label aa;
    Label ab;
    Label ac;
    Label ad;
    Label ae;
    Label af;
    Label ag;
    Label ah;
    Label ai;
    Label aj;
    Label ak;
    Checkbox al;
    Checkbox am;
    Checkbox an;
    Checkbox ao;
    Checkbox ap;
    Checkbox aq;
    ek ar;
    ek as;
    ek at;
    Checkbox au;
    Checkbox av;
    Checkbox aw;
    CheckboxGroup ax;
    eb ay;
    eb az;
    eb a0;
    List a1;
    bs a2;
    c a3;
    c a4;
    ea a5;
    eg a6;
    a0 a7;
    boolean a8;
    boolean a9;
    String ba;
    int bb;
    int bc;
    int bd;
    int be;
    int bf;
    int bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    dg br;
    Component bs;
    int bt;
    String bu;
    dy bv;
    af bw;
    ad bx;
    dh by;
    Thread bz;
    
    public d9(final Frame frame, final dg dg, final String s, final eg eg, final int n, final dy dy, final a0 a0, final af af, final ad ad, final boolean b, final String s2) {
        super(frame, ji.util.d.b(569, s), true);
        this.c = null;
        this.d = true;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = false;
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
        this.a6 = null;
        this.a7 = null;
        this.a8 = false;
        this.a9 = false;
        this.ba = null;
        this.bd = 85;
        this.be = 25;
        this.bf = 10;
        this.bg = 15;
        this.bh = 10;
        this.bi = 170;
        this.bj = 100;
        this.bk = 20;
        this.bl = 35;
        this.bm = 0;
        this.bn = 0;
        this.bo = 55;
        this.bp = 20;
        this.bq = 5;
        this.br = null;
        this.bs = null;
        this.bt = 0;
        this.bu = "";
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.by = null;
        this.bz = null;
        this.a(dg, s, eg, n, dy, a0, af, ad, b, s2);
    }
    
    public d9(final Dialog dialog, final dg dg, final String s, final eg eg, final int n, final dy dy, final a0 a0, final af af, final ad ad, final boolean b, final String s2) {
        super(dialog, ji.util.d.b(569, s), true);
        this.c = null;
        this.d = true;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = false;
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
        this.a6 = null;
        this.a7 = null;
        this.a8 = false;
        this.a9 = false;
        this.ba = null;
        this.bd = 85;
        this.be = 25;
        this.bf = 10;
        this.bg = 15;
        this.bh = 10;
        this.bi = 170;
        this.bj = 100;
        this.bk = 20;
        this.bl = 35;
        this.bm = 0;
        this.bn = 0;
        this.bo = 55;
        this.bp = 20;
        this.bq = 5;
        this.br = null;
        this.bs = null;
        this.bt = 0;
        this.bu = "";
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.by = null;
        this.bz = null;
        this.a(dg, s, eg, n, dy, a0, af, ad, b, s2);
    }
    
    private void a(final dg br, final String bu, final eg a6, final int bt, final dy bv, final a0 a7, final af bw, final ad ad, final boolean a8, final String ba) {
        this.ba = ba;
        this.a9 = a8;
        this.bt = bt;
        this.bs = ad;
        this.a6 = a6;
        this.bv = bv;
        this.a7 = a7;
        this.bw = bw;
        this.bx = ad;
        br.al(false);
        this.bu = bu;
        this.br = br;
        try {
            if (ji.util.d.bf()) {
                if (bt == 0) {
                    this.a = 460;
                    this.b = 210;
                }
                else {
                    this.a = 350;
                    this.b = 220;
                }
            }
            else {
                this.a = 340;
                this.b = 210;
            }
            if (ji.util.d.av(bu)) {
                this.b += 40;
            }
            ji.util.e.a(this);
            this.setResizable(false);
            (this.z = new jiPanel(bu)).setBorderStyle(0);
            this.z.setLayout(null);
            this.d().add(this.z, "Center");
            this.u = new ach();
            this.w = new aci();
            this.x = new acj();
            this.addKeyListener(this.u);
            this.addWindowListener(this.t = new ack());
            if (ji.util.d.bf()) {
                this.j();
                this.d = this.c.al(this.d, this.bs);
                this.e = this.c.aj(this.e, this.bs);
                this.y = new acl();
                this.setTitle(br.du());
                this.bj = 250;
                if (bt == 0) {
                    this.by = br.bp();
                    this.c();
                    final Graphics graphics = ad.getGraphics();
                    int bj = 0;
                    if (graphics != null && graphics.getFontMetrics() != null && this.i != null) {
                        for (int i = 0; i < this.i.length; ++i) {
                            final int stringWidth = graphics.getFontMetrics().stringWidth(this.i[i]);
                            if (stringWidth > bj) {
                                bj = stringWidth;
                            }
                        }
                    }
                    bj += 35;
                    if (bj > this.bj) {
                        this.a += bj - this.bj;
                        this.bj = bj;
                    }
                    if (this.ax == null) {
                        this.ax = new CheckboxGroup();
                    }
                    ji.util.e.a(this.au = new Checkbox(this.a(582), this.d, this.ax));
                    this.au.addKeyListener(this.u);
                    this.au.addItemListener(this.y);
                    final int n = this.bj / 3;
                    this.au.setBounds(this.bg + this.bi + this.bf, this.bh, n, this.bk);
                    this.z.add(this.au);
                    ji.util.e.a(this.av = new Checkbox(this.a(583), this.e, this.ax));
                    this.av.addKeyListener(this.u);
                    this.av.addItemListener(this.y);
                    this.av.setBounds(this.bg + this.bi + this.bf + n + this.bf, this.bh, n, this.bk);
                    this.z.add(this.av);
                    ji.util.e.a(this.aw = new Checkbox(this.a(584), this.d && this.e, this.ax));
                    this.aw.addKeyListener(this.u);
                    this.aw.addItemListener(this.y);
                    this.aw.setBounds(this.bg + this.bi + this.bf + n + this.bf + n + this.bf, this.bh, n, this.bk);
                    this.z.add(this.aw);
                    this.bh += this.bl;
                    ji.util.e.a(this.ac = new Label(this.a(571)));
                    this.ac.addKeyListener(this.u);
                    this.ac.setBounds(this.bg, this.bh, this.bi, this.bk);
                    this.z.add(this.ac);
                    (this.ay = new eb(bu)).addKeyListener(this.u);
                    this.ay.setBounds(this.bg + this.bi + this.bf, this.bh, this.bj, this.bk);
                    this.z.add(this.ay);
                    this.bh += this.bl - 5;
                    ji.util.e.a(this.ae = new Label(this.a(572)));
                    this.ae.addKeyListener(this.u);
                    this.ae.setBounds(this.bg, this.bh, this.bi, this.bk);
                    this.z.add(this.ae);
                    (this.az = new eb(bu)).addKeyListener(this.u);
                    this.az.setBounds(this.bg + this.bi + this.bf, this.bh, this.bj, this.bk);
                    this.z.add(this.az);
                    this.bh += this.bl - 5;
                    ji.util.e.a(this.ae = new Label(this.a(573)));
                    this.ae.addKeyListener(this.u);
                    this.ae.setBounds(this.bg, this.bh, this.bi, this.bk);
                    this.z.add(this.ae);
                    (this.a0 = new eb(bu)).addKeyListener(this.u);
                    this.a0.setBounds(this.bg + this.bi + this.bf, this.bh, this.bj, this.bk);
                    this.z.add(this.a0);
                }
                else {
                    this.by = br.bp().q();
                    this.bm = this.a - 2 * this.bg - 10;
                    this.bn = this.bk * 5;
                    this.bo = 60;
                    this.bp = 20;
                    this.bq = 0;
                    this.i();
                    ji.util.e.a(this.n = new bn(bu, this.a(591)));
                    this.n.addKeyListener(this.u);
                    this.n.addActionListener(this.w);
                    final int n2 = this.bg + this.bm - this.bo;
                    final int n3 = this.bh + this.bn + 3;
                    this.n.setBounds(n2, n3, this.bo, this.bp);
                    this.z.add(this.n);
                    ji.util.e.a(this.p = new bn(bu, this.a(592)));
                    this.p.setEnabled(false);
                    this.p.addKeyListener(this.u);
                    this.p.addActionListener(this.w);
                    this.p.setBounds(n2 - (this.bo + this.bq), n3, this.bo, this.bp);
                    this.z.add(this.p);
                    ji.util.e.a(this.o = new bn(bu, this.a(593)));
                    this.o.setEnabled(false);
                    this.o.addKeyListener(this.u);
                    this.o.addActionListener(this.w);
                    this.o.setBounds(n2 - 2 * (this.bo + this.bq), n3, this.bo, this.bp);
                    this.z.add(this.o);
                }
            }
            else {
                this.by = br.bp();
                ji.util.e.a(this.ab = new Label(String.valueOf(String.valueOf(br.du())).concat(" ...")));
                this.ab.addKeyListener(this.u);
                this.ab.setBounds(this.bg - 5, this.bh, this.bj, this.bk);
                this.z.add(this.ab);
                this.bd = 85;
                this.be = 25;
                this.bf = 10;
                this.bh = 10;
                this.bi = 115;
                this.bk = 20;
                this.bj = 115;
                this.bg = 25;
                this.bh += 5;
                this.bh += this.bl - 5;
                ji.util.e.a(this.al = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(575)))), null, this.by.h()));
                this.al.addKeyListener(this.u);
                this.al.setBounds(this.bg, this.bh, this.bj, this.bk);
                this.z.add(this.al);
                this.bh += this.bl - 10;
                ji.util.e.a(this.am = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(576)))), null, this.by.i()));
                this.am.addKeyListener(this.u);
                this.am.setBounds(this.bg, this.bh, this.bj, this.bk);
                this.z.add(this.am);
                this.bh += this.bl - 10;
                ji.util.e.a(this.aq = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(577)))), null, this.by.m()));
                this.aq.addKeyListener(this.u);
                this.aq.setBounds(this.bg, this.bh, this.bj, this.bk);
                this.z.add(this.aq);
                this.bh -= this.bl - 10;
                this.bh -= this.bl - 10;
                ji.util.e.a(this.ap = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(579)))), null, this.by.l()));
                this.ap.addKeyListener(this.u);
                this.ap.setBounds(this.bg + this.bj + this.bf, this.bh, this.bj, this.bk);
                this.z.add(this.ap);
                this.bh += this.bl - 10;
                ji.util.e.a(this.ao = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(578)))), null, this.by.k()));
                this.ao.addKeyListener(this.u);
                this.ao.setBounds(this.bg + this.bj + this.bf, this.bh, this.bj, this.bk);
                this.z.add(this.ao);
                if (this.by.a(ba, br.bn()) || a8) {
                    this.bh += this.bl - 10;
                    ji.util.e.a(this.an = new Checkbox("      ".concat(String.valueOf(String.valueOf(this.a(580)))), null, this.by.j()));
                    this.an.addKeyListener(this.u);
                    this.an.setBounds(this.bg + this.bj + this.bf, this.bh, this.bj * 2, this.bk);
                    this.z.add(this.an);
                    this.b += 15;
                }
                this.bh += this.bl - 7;
                String s = this.by.n();
                if (ji.util.d.by(s)) {
                    s = this.a(581);
                }
                ji.util.e.a(this.ad = new Label(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(574)))).append(" ").append(s)))));
                this.ad.addKeyListener(this.u);
                this.ad.setBounds(this.bg + this.bj + this.bf, this.bh, this.bj * 2, this.bk);
                this.z.add(this.ad);
                if (br.d(ba, a8) || a8) {
                    final int n4 = 52;
                    final String a9 = this.a(656);
                    final int n5 = 60;
                    if (ji.util.i.c(8)) {
                        this.b += n4;
                        this.bh += this.bl - 7;
                        (this.aj = new Label(String.valueOf(String.valueOf(this.a(649))).concat(":"))).addKeyListener(this.u);
                        this.aj.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.aj);
                        (this.ar = new ek(bu, 40)).addKeyListener(this.u);
                        this.ar.addFocusListener(this.x);
                        this.ar.setEchoChar('*');
                        this.bh += this.bl - 12;
                        this.ar.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.ar);
                        if (!ji.util.d.by(this.by.e())) {
                            this.ar.setText("************");
                        }
                        ji.util.e.a(this.q = new bn(bu, a9));
                        this.q.addKeyListener(this.u);
                        this.q.addActionListener(this.w);
                        this.q.setBounds(this.bg + this.bj * 2 + 5, this.bh, n5, this.bk);
                        this.z.add(this.q);
                    }
                    if (ji.util.i.c(10) && br.d5() == 7) {
                        this.b += n4;
                        this.bh += this.bl - 7;
                        (this.ak = new Label(String.valueOf(String.valueOf(this.a(657))).concat(":"))).addKeyListener(this.u);
                        this.ak.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.ak);
                        (this.as = new ek(bu, 40)).addKeyListener(this.u);
                        this.as.addFocusListener(this.x);
                        this.as.setEchoChar('*');
                        this.bh += this.bl - 12;
                        this.as.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.as);
                        if (!ji.util.d.by(this.by.g())) {
                            this.as.setText("************");
                        }
                        ji.util.e.a(this.r = new bn(bu, a9));
                        this.r.addKeyListener(this.u);
                        this.r.addActionListener(this.w);
                        this.r.setBounds(this.bg + this.bj * 2 + 5, this.bh, n5, this.bk);
                        this.z.add(this.r);
                    }
                    if (ji.util.i.c(9)) {
                        this.b += n4;
                        this.bh += this.bl - 7;
                        (this.ai = new Label(String.valueOf(String.valueOf(this.a(650))).concat(":"))).addKeyListener(this.u);
                        this.ai.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.ai);
                        (this.at = new ek(bu, 40)).addKeyListener(this.u);
                        this.at.addFocusListener(this.x);
                        this.at.setEchoChar('*');
                        this.bh += this.bl - 12;
                        this.at.setBounds(this.bg, this.bh, this.bj * 2, this.bk);
                        this.z.add(this.at);
                        if (!ji.util.d.by(this.by.f())) {
                            this.at.setText("************");
                        }
                        ji.util.e.a(this.s = new bn(bu, a9));
                        this.s.addKeyListener(this.u);
                        this.s.addActionListener(this.w);
                        this.s.setBounds(this.bg + this.bj * 2 + 5, this.bh, n5, this.bk);
                        this.z.add(this.s);
                    }
                }
            }
            int n6 = 0;
            if (!a8 && !this.by.a(ba, br.bn())) {
                n6 = (this.by.j() ? 0 : 1);
            }
            if (n6 != 0) {
                if (this.al != null) {
                    this.al.setEnabled(false);
                }
                if (this.am != null) {
                    this.am.setEnabled(false);
                }
                if (this.aq != null) {
                    this.aq.setEnabled(false);
                }
                if (this.ap != null) {
                    this.ap.setEnabled(false);
                }
                if (this.ao != null) {
                    this.ao.setEnabled(false);
                }
            }
            ji.util.e.a(this.l = new bn(bu, this.a(232)));
            this.l.addKeyListener(this.u);
            this.l.addActionListener(this.w);
            ji.util.e.a(this.m = new bn(bu, this.a(235)));
            this.m.addKeyListener(this.u);
            this.m.addActionListener(this.w);
            this.bb = (this.a - 2 * this.bd - this.bf) / 2;
            this.bc = this.b - this.be - this.bf - 30;
            if (ji.util.d.av(bu)) {
                this.bc -= 40;
            }
            this.l.setBounds(this.bb, this.bc, this.bd, this.be);
            this.z.add(this.l);
            this.m.setBounds(this.bb + this.bd + this.bf, this.bc, this.bd, this.be);
            this.z.add(this.m);
            this.setSize(this.a, this.b);
            this.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.a / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.b / 2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void c() {
        if (this.a6 == null && this.bv != null) {
            this.bv.a(this.a7, this, this.bw, this.bu);
            this.a6 = this.bv.ep;
        }
        if (this.i == null) {
            this.i = this.a6.a();
        }
        if (this.j == null) {
            this.j = this.a6.b();
        }
    }
    
    public void show() {
        try {
            if (ji.util.d.bf()) {
                new bb(this.bu, new aar()).start();
                if (this.bt == 0) {
                    new bb(this.bu, new aas()).start();
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.show();
    }
    
    private String a(final int n) {
        return ji.util.d.b(n, this.bu);
    }
    
    public final void a() {
        try {
            this.br.al(false);
        }
        catch (Exception ex) {}
        this.b();
    }
    
    private final void f() {
        try {
            final boolean c = ji.util.i.c(57);
            if (c) {
                ji.io.h.d(this.bu, "jiAnnSecurityFrame1");
            }
            this.ay.a();
            this.az.a();
            this.a0.a();
            this.ay.removeAll();
            this.az.removeAll();
            this.a0.removeAll();
            this.ay.a("(NONE)");
            this.ay.a("(ANYONE)");
            this.az.a("(NONE)");
            this.az.a("(ANYONE)");
            this.a0.a("(NONE)");
            this.a0.a("(ANYONE)");
            if (this.a4 == null) {
                this.a4 = new c("jiAnnSecurityFrame");
            }
            else {
                this.a4.c();
            }
            this.a4.c(new Integer(0));
            this.a4.c(new Integer(0));
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            final ei ei = (ei)this.by.a(0).b(0);
            final ei ei2 = (ei)this.by.a(1).b(0);
            final ei ei3 = (ei)this.by.a(2).b(0);
            if (ei.h.equals("(NONE)")) {
                b = true;
            }
            if (ei2.h.equals("(NONE)")) {
                b2 = true;
            }
            if (ei3.h.equals("(NONE)")) {
                b3 = true;
            }
            if (ei.h.equals("(ANYONE)")) {
                b = true;
            }
            if (ei2.h.equals("(ANYONE)")) {
                b2 = true;
            }
            if (ei3.h.equals("(ANYONE)")) {
                b3 = true;
            }
            if (c) {
                ji.io.h.d(this.bu, "jiAnnSecurityFrame2");
            }
            if (this.d && this.i != null) {
                for (int i = 0; i < this.i.length; ++i) {
                    if (this.i[i].equals(ei.h)) {
                        b = true;
                    }
                    if (this.i[i].equals(ei2.h)) {
                        b2 = true;
                    }
                    if (this.i[i].equals(ei3.h)) {
                        b3 = true;
                    }
                    this.ay.a(this.i[i]);
                    this.az.a(this.i[i]);
                    this.a0.a(this.i[i]);
                    this.a4.c(new Integer(0));
                }
            }
            if (c) {
                ji.io.h.d(this.bu, "jiAnnSecurityFrame3");
            }
            if (this.e && this.j != null) {
                for (int j = 0; j < this.j.length; ++j) {
                    if (this.j[j].equals(ei.h)) {
                        b = true;
                    }
                    if (this.j[j].equals(ei2.h)) {
                        b2 = true;
                    }
                    if (this.j[j].equals(ei3.h)) {
                        b3 = true;
                    }
                    this.ay.a(this.j[j]);
                    this.az.a(this.j[j]);
                    this.a0.a(this.j[j]);
                    this.a4.c(new Integer(1));
                }
            }
            if (!b) {
                this.ay.a(ei.h);
                this.az.a(ei.h);
                this.a0.a(ei.h);
            }
            if (!b2 && !ei.h.equals(ei2.h)) {
                this.ay.a(ei2.h);
                this.az.a(ei2.h);
                this.a0.a(ei2.h);
            }
            if (!b3 && !ei.h.equals(ei3.h) && !ei2.h.equals(ei3.h)) {
                this.ay.a(ei3.h);
                this.az.a(ei3.h);
                this.a0.a(ei3.h);
            }
            try {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.bu, "READ1=".concat(String.valueOf(String.valueOf(ei.h))));
                }
                this.ay.b(ei.h);
            }
            catch (Exception ex2) {}
            try {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.bu, "WRITE1=".concat(String.valueOf(String.valueOf(ei2.h))));
                }
                this.az.b(ei2.h);
            }
            catch (Exception ex3) {}
            try {
                if (ji.util.d.cs()) {
                    ji.io.h.d(this.bu, "APPEND1=".concat(String.valueOf(String.valueOf(ei3.h))));
                }
                this.a0.b(ei3.h);
            }
            catch (Exception ex4) {}
            if (c) {
                ji.io.h.d(this.bu, "jiAnnSecurityFrame4");
            }
            this.ay.b();
            this.az.b();
            this.a0.b();
            if (c) {
                ji.io.h.d(this.bu, "jiAnnSecurityFrame5");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final String a(final String s) {
        String value = "";
        try {
            if (this.a3 == null) {
                this.a3 = new c("jiAnnSecurityFrame2");
            }
            this.a3.c();
            final c b = this.by.b();
            if (b != null) {
                final k k = new k(b.b());
                final String s2 = ">>>>7861wy87w>><<>>";
                for (int i = 0; i < b.b(); ++i) {
                    k.a(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(((ei)b.b(i)).h.toLowerCase()))).append(s2).append(i))));
                }
                for (int j = 0; j < b.b(); ++j) {
                    final String b2 = k.b();
                    final ei ei = (ei)b.b(ji.util.d.c(b2.substring(b2.indexOf(s2) + s2.length()), 0));
                    if (ei.i != 2) {
                        if (ei.k != 3) {
                            String s3 = null;
                            if (ei.l.toLowerCase().equals("owner".toLowerCase())) {
                                s3 = ji.util.d.b(588, this.bu);
                            }
                            else if (ei.l.toLowerCase().equals("author".toLowerCase())) {
                                s3 = ji.util.d.b(587, this.bu);
                            }
                            else if (ei.l.toLowerCase().equals("viewer".toLowerCase())) {
                                s3 = ji.util.d.b(586, this.bu);
                            }
                            else if (ei.l.toLowerCase().equals("none".toLowerCase())) {
                                s3 = ji.util.d.b(585, this.bu);
                            }
                            else if (ei.l.toLowerCase().equals("admin".toLowerCase())) {
                                s3 = ji.util.d.b(589, this.bu);
                            }
                            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(ei.h).append(s).append(s3).append("\n")));
                            this.a3.c(ei);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }
    
    private final void g() {
        try {
            this.br.al(true);
            if (ji.util.d.bf()) {
                if (this.bt == 0) {
                    final ei ei = (ei)this.by.a(0).b(0);
                    final ei ei2 = (ei)this.by.a(1).b(0);
                    final ei ei3 = (ei)this.by.a(2).b(0);
                    final String d = this.ay.d();
                    final String d2 = this.az.d();
                    final String d3 = this.a0.d();
                    if (!ei.h.equals(d)) {
                        this.by.b(ei);
                        this.by.a(new ei(d, (int)this.a4.b(this.ay.e()), 0, 1));
                    }
                    if (!ei2.h.equals(d2)) {
                        this.by.b(ei2);
                        this.by.a(new ei(d2, (int)this.a4.b(this.az.e()), 1, 1));
                    }
                    if (!ei3.h.equals(d3)) {
                        this.by.b(ei3);
                        this.by.a(new ei(d3, (int)this.a4.b(this.a0.e()), 2, 1));
                    }
                }
                else {
                    this.by.a();
                    this.br.a(this.by);
                    this.br.al(true);
                }
            }
            else if (this.by.i() || this.by.a(this.ba, this.br.bn()) || this.a9) {
                try {
                    if (this.by.a(this.ba, this.br.bn()) || this.a9) {
                        this.by.a(true);
                        this.by.d(this.an.getState());
                    }
                    this.by.b(this.al.getState());
                    this.by.e(this.ao.getState());
                    this.by.f(this.ap.getState());
                    this.by.h(this.aq.getState());
                    try {
                        if (this.f) {
                            this.by.b(this.ar.getText());
                        }
                        if (this.g) {
                            this.by.d(this.as.getText());
                        }
                        if (this.h) {
                            this.by.c(this.at.getText());
                        }
                    }
                    catch (Exception ex) {}
                    this.by.c(this.am.getState());
                }
                finally {
                    this.by.a(false);
                }
            }
        }
        catch (Exception ex2) {}
        this.b();
    }
    
    private final void h() {
        try {
            if (this.a2 != null) {
                this.z.remove(this.a2);
                this.a2.releaseResources();
                this.a2 = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final void i() {
        try {
            final String a = this.a("<====>");
            if (this.a2 == null) {
                (this.a2 = new bs(this.bx, (a.length() == 0) ? "<====>" : a, "<====>", this.bm, 0, this.bu)).setBounds(this.bg, this.bh, this.bm, this.bn);
                this.a2.addItemListener(this.y);
                this.a2.addKeyListener(this.u);
                this.z.add(this.a2);
            }
            else {
                this.a2.a(this.bx, (a.length() == 0) ? "<====>" : a, "<====>");
                this.a2.a();
                this.a2.validate();
            }
        }
        catch (Exception ex) {}
    }
    
    private final void j() {
        try {
            if (this.c == null) {
                this.c = new p(this.bu);
            }
        }
        catch (Exception ex) {}
    }
    
    public void b() {
        try {
            this.hide();
            this.h();
            try {
                if (this.a5 != null) {
                    this.a5.hide();
                    this.a5.a();
                    this.a5 = null;
                }
            }
            catch (Exception ex) {}
            this.br = null;
            this.bs = null;
            if (this.c != null) {
                this.c.a();
                this.c = null;
            }
            if (this.n != null) {
                if (this.u != null) {
                    this.n.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.n.removeActionListener(this.w);
                }
                this.z.remove(this.n);
                this.n = null;
            }
            if (this.o != null) {
                if (this.u != null) {
                    this.o.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.o.removeActionListener(this.w);
                }
                this.z.remove(this.o);
                this.o = null;
            }
            if (this.p != null) {
                if (this.u != null) {
                    this.p.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.p.removeActionListener(this.w);
                }
                this.z.remove(this.p);
                this.p = null;
            }
            if (this.l != null) {
                if (this.u != null) {
                    this.l.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.l.removeActionListener(this.w);
                }
                this.z.remove(this.l);
                this.l = null;
            }
            if (this.m != null) {
                if (this.u != null) {
                    this.m.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.m.removeActionListener(this.w);
                }
                this.z.remove(this.m);
                this.m = null;
            }
            if (this.al != null) {
                if (this.u != null) {
                    this.al.removeKeyListener(this.u);
                }
                this.z.remove(this.al);
                this.al = null;
            }
            if (this.am != null) {
                if (this.u != null) {
                    this.am.removeKeyListener(this.u);
                }
                this.z.remove(this.am);
                this.am = null;
            }
            if (this.an != null) {
                if (this.u != null) {
                    this.an.removeKeyListener(this.u);
                }
                this.z.remove(this.an);
                this.an = null;
            }
            if (this.ao != null) {
                if (this.u != null) {
                    this.ao.removeKeyListener(this.u);
                }
                this.z.remove(this.ao);
                this.ao = null;
            }
            if (this.ap != null) {
                if (this.u != null) {
                    this.ap.removeKeyListener(this.u);
                }
                this.z.remove(this.ap);
                this.ap = null;
            }
            if (this.aq != null) {
                if (this.u != null) {
                    this.aq.removeKeyListener(this.u);
                }
                this.z.remove(this.aq);
                this.aq = null;
            }
            if (this.q != null) {
                if (this.u != null) {
                    this.q.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.q.removeActionListener(this.w);
                }
                this.z.remove(this.q);
                this.q = null;
            }
            if (this.r != null) {
                if (this.u != null) {
                    this.r.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.r.removeActionListener(this.w);
                }
                this.z.remove(this.r);
                this.r = null;
            }
            if (this.s != null) {
                if (this.u != null) {
                    this.s.removeKeyListener(this.u);
                }
                if (this.w != null) {
                    this.s.removeActionListener(this.w);
                }
                this.z.remove(this.s);
                this.s = null;
            }
            if (this.ar != null) {
                if (this.u != null) {
                    this.ar.removeKeyListener(this.u);
                }
                if (this.x != null) {
                    this.ar.removeFocusListener(this.x);
                }
                this.z.remove(this.ar);
                this.ar = null;
            }
            if (this.as != null) {
                if (this.u != null) {
                    this.as.removeKeyListener(this.u);
                }
                if (this.x != null) {
                    this.as.removeFocusListener(this.x);
                }
                this.z.remove(this.as);
                this.as = null;
            }
            if (this.at != null) {
                if (this.u != null) {
                    this.at.removeKeyListener(this.u);
                }
                if (this.x != null) {
                    this.at.removeFocusListener(this.x);
                }
                this.z.remove(this.at);
                this.at = null;
            }
            if (this.ai != null) {
                if (this.u != null) {
                    this.ai.removeKeyListener(this.u);
                }
                this.z.remove(this.ai);
                this.ai = null;
            }
            if (this.aj != null) {
                if (this.u != null) {
                    this.aj.removeKeyListener(this.u);
                }
                this.z.remove(this.aj);
                this.aj = null;
            }
            if (this.ak != null) {
                if (this.u != null) {
                    this.ak.removeKeyListener(this.u);
                }
                this.z.remove(this.ak);
                this.ak = null;
            }
            if (this.aa != null) {
                if (this.u != null) {
                    this.aa.removeKeyListener(this.u);
                }
                this.z.remove(this.aa);
                this.aa = null;
            }
            if (this.ab != null) {
                if (this.u != null) {
                    this.ab.removeKeyListener(this.u);
                }
                this.z.remove(this.ab);
                this.ab = null;
            }
            if (this.ac != null) {
                if (this.u != null) {
                    this.ac.removeKeyListener(this.u);
                }
                this.z.remove(this.ac);
                this.ac = null;
            }
            if (this.ad != null) {
                if (this.u != null) {
                    this.ad.removeKeyListener(this.u);
                }
                this.z.remove(this.ad);
                this.ad = null;
            }
            if (this.ae != null) {
                if (this.u != null) {
                    this.ae.removeKeyListener(this.u);
                }
                this.z.remove(this.ae);
                this.ae = null;
            }
            if (this.af != null) {
                if (this.u != null) {
                    this.af.removeKeyListener(this.u);
                }
                this.z.remove(this.af);
                this.af = null;
            }
            if (this.ag != null) {
                if (this.u != null) {
                    this.ag.removeKeyListener(this.u);
                }
                this.z.remove(this.ag);
                this.ag = null;
            }
            if (this.ah != null) {
                if (this.u != null) {
                    this.ah.removeKeyListener(this.u);
                }
                this.z.remove(this.ah);
                this.ah = null;
            }
            if (this.au != null) {
                if (this.u != null) {
                    this.au.removeKeyListener(this.u);
                }
                if (this.z != null) {
                    this.z.remove(this.au);
                }
                this.au = null;
            }
            if (this.av != null) {
                if (this.u != null) {
                    this.av.removeKeyListener(this.u);
                }
                if (this.z != null) {
                    this.z.remove(this.av);
                }
                this.av = null;
            }
            if (this.aw != null) {
                if (this.u != null) {
                    this.aw.removeKeyListener(this.u);
                }
                if (this.z != null) {
                    this.z.remove(this.aw);
                }
                this.aw = null;
            }
            this.ax = null;
            if (this.a1 != null) {
                this.a1.removeAll();
                if (this.u != null) {
                    this.a1.removeKeyListener(this.u);
                }
                if (this.y != null) {
                    this.a1.removeItemListener(this.y);
                }
                if (this.z != null) {
                    this.z.remove(this.a1);
                }
                this.a1 = null;
            }
            if (this.ay != null) {
                this.ay.a();
                this.ay.removeAll();
                if (this.u != null) {
                    this.ay.removeKeyListener(this.u);
                }
                if (this.ay.getParent() != null) {
                    this.ay.getParent().removeAll();
                }
                this.ay.releaseResources();
                this.ay = null;
            }
            if (this.az != null) {
                this.az.a();
                this.az.removeAll();
                if (this.u != null) {
                    this.az.removeKeyListener(this.u);
                }
                if (this.az.getParent() != null) {
                    this.az.getParent().removeAll();
                }
                this.az = null;
            }
            if (this.a0 != null) {
                this.a0.a();
                this.a0.removeAll();
                if (this.u != null) {
                    this.a0.removeKeyListener(this.u);
                }
                if (this.a0.getParent() != null) {
                    this.a0.getParent().removeAll();
                }
                this.a0 = null;
            }
            if (this.z != null) {
                this.remove(this.z);
                this.z.releaseResources();
                this.z = null;
            }
            if (this.a4 != null) {
                this.a4.c();
                this.a4 = null;
            }
            if (this.a3 != null) {
                this.a3.c();
                this.a3 = null;
            }
            this.a6 = null;
            this.bv = null;
            this.a7 = null;
            this.bw = null;
            this.bx = null;
            this.by = null;
        }
        catch (Exception ex2) {}
        try {
            this.dispose();
        }
        catch (Exception ex3) {}
    }
    
    public void dispose() {
        this.hide();
    }
    
    class ach implements KeyListener
    {
        public void keyPressed(final KeyEvent keyEvent) {
            try {
                if (keyEvent.getKeyCode() == 27) {
                    d9.this.a();
                }
                else if (keyEvent.getKeyCode() == 10 && d9.this.l != null) {
                    if (keyEvent.getSource().equals(d9.this.l)) {
                        d9.this.g();
                        return;
                    }
                    if (d9.this.a2 != null && keyEvent.getSource().equals(d9.this.a2)) {
                        d9.this.g();
                        return;
                    }
                    if (d9.this.a1 != null && keyEvent.getSource().equals(d9.this.a1)) {
                        d9.this.g();
                        return;
                    }
                    if (d9.this.m != null && keyEvent.getSource().equals(d9.this.m)) {
                        d9.this.a();
                    }
                }
            }
            catch (Exception ex) {}
        }
        
        public void keyReleased(final KeyEvent keyEvent) {
        }
        
        public void keyTyped(final KeyEvent keyEvent) {
        }
    }
    
    class acl implements ItemListener
    {
        public void itemStateChanged(final ItemEvent itemEvent) {
            if (itemEvent.getStateChange() == 1) {
                try {
                    boolean b = false;
                    if (itemEvent.getSource().equals(d9.this.a2)) {
                        d9.this.p.setEnabled(true);
                        d9.this.o.setEnabled(true);
                    }
                    else if (itemEvent.getSource().equals(d9.this.au)) {
                        d9.this.d = true;
                        d9.this.e = false;
                        b = true;
                    }
                    else if (itemEvent.getSource().equals(d9.this.av)) {
                        d9.this.d = false;
                        d9.this.e = true;
                        b = true;
                    }
                    else if (itemEvent.getSource().equals(d9.this.aw)) {
                        d9.this.d = true;
                        d9.this.e = true;
                        b = true;
                    }
                    if (b) {
                        d9.this.c.ak(d9.this.d, d9.this.bs);
                        d9.this.c.ai(d9.this.e, d9.this.bs);
                        d9.this.f();
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    class aci implements ActionListener
    {
        public void actionPerformed(final ActionEvent actionEvent) {
            try {
                if (actionEvent.getSource().equals(d9.this.q)) {
                    d9.this.ar.setText("");
                    d9.this.f = true;
                }
                else if (actionEvent.getSource().equals(d9.this.r)) {
                    d9.this.as.setText("");
                    d9.this.g = true;
                }
                else if (actionEvent.getSource().equals(d9.this.s)) {
                    d9.this.at.setText("");
                    d9.this.h = true;
                }
                else if (actionEvent.getSource().equals(d9.this.l)) {
                    d9.this.g();
                }
                else if (actionEvent.getSource().equals(d9.this.m)) {
                    d9.this.a();
                }
                else if (actionEvent.getSource().equals(d9.this.n)) {
                    if (d9.this.a5 == null && d9.this.bz == null) {
                        (d9.this.bz = new bb(d9.this.bu, new acz())).start();
                    }
                }
                else if (actionEvent.getSource().equals(d9.this.p)) {
                    if (d9.this.a5 == null) {
                        d9.this.p.setEnabled(false);
                        d9.this.o.setEnabled(false);
                        Frame frame = null;
                        final Window f = ji.util.d.f(d9.this.bs);
                        if (f instanceof Frame) {
                            d9.this.a5 = new ea((Frame)f, d9.this.bx, d9.this.br, d9.this.by, (ei)d9.this.a3.b(d9.this.a2.d()), d9.this.a6, d9.this.bu, ji.util.d.b(595, d9.this.bu), 1, d9.this.a9);
                        }
                        else if (f instanceof Dialog) {
                            d9.this.a5 = new ea((Dialog)f, d9.this.bx, d9.this.br, d9.this.by, (ei)d9.this.a3.b(d9.this.a2.d()), d9.this.a6, d9.this.bu, ji.util.d.b(595, d9.this.bu), 1, d9.this.a9);
                        }
                        else {
                            frame = new gr();
                            d9.this.a5 = new ea(frame, d9.this.bx, d9.this.br, d9.this.by, (ei)d9.this.a3.b(d9.this.a2.d()), d9.this.a6, d9.this.bu, ji.util.d.b(595, d9.this.bu), 1, d9.this.a9);
                        }
                        d9.this.a5.show();
                        d9.this.a5 = null;
                        if (frame != null) {
                            ((gr)frame).c();
                        }
                        d9.this.i();
                    }
                }
                else if (actionEvent.getSource().equals(d9.this.o) && d9.this.a5 == null) {
                    d9.this.p.setEnabled(false);
                    d9.this.o.setEnabled(false);
                    ((ei)d9.this.a3.b(d9.this.a2.d())).k = 3;
                    d9.this.i();
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class acz implements Runnable
    {
        public void run() {
            try {
                d9.this.l.setEnabled(false);
                d9.this.m.setEnabled(false);
                d9.this.o.setEnabled(false);
                d9.this.p.setEnabled(false);
                d9.this.n.setEnabled(false);
                d9.this.c();
                Frame frame = null;
                final Window f = ji.util.d.f(d9.this.bs);
                if (f instanceof Frame) {
                    d9.this.a5 = new ea((Frame)f, d9.this.bx, d9.this.br, d9.this.by, null, d9.this.a6, d9.this.bu, ji.util.d.b(594, d9.this.bu), 0, d9.this.a9);
                }
                else if (f instanceof Dialog) {
                    d9.this.a5 = new ea((Dialog)f, d9.this.bx, d9.this.br, d9.this.by, null, d9.this.a6, d9.this.bu, ji.util.d.b(594, d9.this.bu), 0, d9.this.a9);
                }
                else {
                    frame = new gr();
                    d9.this.a5 = new ea(frame, d9.this.bx, d9.this.br, d9.this.by, null, d9.this.a6, d9.this.bu, ji.util.d.b(594, d9.this.bu), 0, d9.this.a9);
                }
                d9.this.a5.show();
                d9.this.a5 = null;
                if (frame != null) {
                    ((gr)frame).c();
                }
                d9.this.i();
            }
            catch (Exception ex) {}
            finally {
                d9.this.bz = null;
                d9.this.n.setEnabled(true);
                d9.this.l.setEnabled(true);
                d9.this.m.setEnabled(true);
            }
        }
    }
    
    class acj implements FocusListener
    {
        public void focusGained(final FocusEvent focusEvent) {
            if (focusEvent.getSource().equals(d9.this.ar)) {
                if (!d9.this.f) {
                    d9.this.ar.setText("");
                    d9.this.f = true;
                }
            }
            else if (focusEvent.getSource().equals(d9.this.as)) {
                if (!d9.this.g) {
                    d9.this.as.setText("");
                    d9.this.g = true;
                }
            }
            else if (focusEvent.getSource().equals(d9.this.at) && !d9.this.h) {
                d9.this.at.setText("");
                d9.this.h = true;
            }
        }
        
        public void focusLost(final FocusEvent focusEvent) {
        }
    }
    
    class ack extends WindowAdapter
    {
        public void windowClosing(final WindowEvent windowEvent) {
            if (d9.this.k) {
                d9.this.g();
            }
            else {
                d9.this.a();
            }
        }
    }
    
    class aar implements Runnable
    {
        public void run() {
            try {
                ji.util.d.b(250, 106, d9.this.bu);
                if (ji.util.d.bf()) {
                    if (d9.this.bt == 0) {
                        ji.util.e.b(d9.this.ay);
                    }
                    else {
                        ji.util.e.b(d9.this.m);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    class aas implements Runnable
    {
        public void run() {
            try {
                if (ji.util.d.bf() && d9.this.bt == 0) {
                    d9.this.f();
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
