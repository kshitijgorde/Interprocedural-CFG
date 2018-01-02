import java.awt.event.ItemEvent;
import java.util.Enumeration;
import java.awt.event.KeyListener;
import java.awt.Component;
import java.awt.event.MouseListener;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Graphics;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.awt.Container;

// 
// Decompiled by Procyon v0.5.30
// 

public class h extends Container implements ItemListener
{
    private Dimension a;
    private v b;
    private t c;
    private bm d;
    private a2 e;
    private ah f;
    private int g;
    private at h;
    private at i;
    private at j;
    private at k;
    private a0 l;
    private a0 m;
    private a0 n;
    private a0 o;
    private a0 p;
    private at q;
    private a0 r;
    private a0 s;
    private a0 t;
    private a0 u;
    private a0 v;
    private a0 w;
    private a0 x;
    private a0 y;
    private a0 z;
    private a1 aa;
    public Hashtable ab;
    public static Hashtable ac;
    public Hashtable ad;
    private al ae;
    private al af;
    public aa ag;
    public aa ah;
    public aa ai;
    private aa aj;
    private aa ak;
    private aa al;
    private JComboBox am;
    private an an;
    private an ao;
    private an ap;
    private an aq;
    private ap ar;
    public ar as;
    public ar at;
    private static h au;
    
    public void setSize(final Dimension dimension) {
        super.setSize(dimension);
        this.a = dimension;
        this.a();
    }
    
    public void a() {
        if (this.g == 0) {
            final Rectangle bounds = new Rectangle(1, 50, 198, this.a.height - 55);
            final int n = 50 + bounds.height;
            this.ag.setBounds(bounds);
            this.ai.setBounds(bounds);
            this.ah.setBounds(bounds);
            this.ak.setBounds(bounds);
            this.al.setBounds(bounds);
        }
        else if (this.g == 1) {
            Rectangle bounds2;
            if (this.b.d()) {
                bounds2 = new Rectangle(1, 50, 198, this.a.height - 257);
                this.b.setBounds(new Rectangle(1, 50 + bounds2.height, 198, 195));
            }
            else {
                bounds2 = new Rectangle(1, 50, 198, this.a.height - 75);
                this.b.setBounds(new Rectangle(1, 50 + bounds2.height, 198, 16));
            }
            this.aj.setBounds(bounds2);
        }
        else {
            final Rectangle rectangle = new Rectangle(1, 50, 198, this.a.height - 59);
        }
        this.as.setLocation(1, this.a.height - 7);
        this.at.setLocation(1, this.a.height - 4);
    }
    
    public void a(final String s, final int n, final boolean b, final int n2) {
        final ba ba = this.ab.get(s);
        if (ba != null) {
            ba.f(n);
            ba.e(n2);
            ba.d(b);
            this.r.b(ba);
            this.s.b(ba);
            this.u.b(ba);
            this.t.b(ba);
            this.v.b(ba);
            this.w.b(ba);
            this.x.b(ba);
            this.y.b(ba);
            this.p.b(ba);
            if (Main.d(ba.s())) {
                this.p.a(ba);
            }
            switch (ba.u()) {
                case 0: {
                    this.v.a(ba);
                    break;
                }
                case 1: {
                    this.w.a(ba);
                    break;
                }
                case 2: {
                    this.x.a(ba);
                    break;
                }
                case 3: {
                    this.y.a(ba);
                    break;
                }
            }
            switch (ba.v()) {
                case 0: {
                    this.r.a(ba);
                    break;
                }
                case 1: {
                    this.s.a(ba);
                    break;
                }
                case 2: {
                    this.u.a(ba);
                    break;
                }
                case 3: {
                    this.t.a(ba);
                    break;
                }
            }
            this.n.b(ba);
            this.l.b(ba);
            this.m.b(ba);
            this.o.b(ba);
            ba.f(n);
            switch (ba.w()) {
                case 0: {
                    this.n.a(ba);
                    break;
                }
                case 1: {
                    this.l.a(ba);
                    break;
                }
                case 2: {
                    this.m.a(ba);
                    break;
                }
                case 3: {
                    this.o.a(ba);
                    break;
                }
                case 4: {
                    this.o.a(ba);
                    break;
                }
                case 5: {
                    this.o.a(ba);
                    break;
                }
                case 6: {
                    this.o.a(ba);
                    break;
                }
            }
            this.ak.a();
        }
    }
    
    public void a(final boolean b) {
        z z;
        if (b) {
            z = new aw();
        }
        else {
            z = new ci();
        }
        this.k.a(z);
        this.j.a(z);
        this.h.a(z);
        this.q.a(z);
        this.aj.a();
    }
    
    public void b() {
        this.ag.a();
        this.ah.a();
        this.ai.a();
        this.ak.a();
        this.al.a();
    }
    
    public void c() {
        this.aj.a();
    }
    
    public void a(final bh bh) {
        this.aa.a(bh);
    }
    
    public void b(final bh bh) {
        this.aa.b(bh);
    }
    
    public av[] d() {
        return this.aa.d();
    }
    
    public void a(final ba ba) {
        this.ab.put(ba.s(), ba);
        final l b = l.b();
        b.a(ba);
        b.a(ba.q(), ba.f(), true, ba.j());
        l.b(ba);
        if (Main.d(ba.s())) {
            this.p.a(ba);
        }
        switch (ba.w()) {
            case 0: {
                this.n.a(ba);
                break;
            }
            case 1: {
                this.l.a(ba);
                break;
            }
            case 2: {
                this.m.a(ba);
                break;
            }
            case 3: {
                this.o.a(ba);
                break;
            }
            case 4: {
                this.o.a(ba);
                break;
            }
            case 5: {
                this.o.a(ba);
                break;
            }
            case 6: {
                this.o.a(ba);
                break;
            }
        }
        switch (ba.v()) {
            case 0: {
                this.r.a(ba);
                break;
            }
            case 1: {
                this.s.a(ba);
                break;
            }
            case 2: {
                this.u.a(ba);
                break;
            }
            case 3: {
                this.t.a(ba);
                break;
            }
        }
        switch (ba.u()) {
            case 0: {
                this.v.a(ba);
                break;
            }
            case 1: {
                this.w.a(ba);
                break;
            }
            case 2: {
                this.x.a(ba);
                break;
            }
            case 3: {
                this.y.a(ba);
                break;
            }
        }
        if (!this.ah.a(ba.t())) {
            final a0 a0 = new a0(new y(), ba.t(), false);
            a0.a(ba);
            this.ah.a(a0);
        }
        else {
            ((au)this.ah.c(ba.t())).a(ba);
        }
        this.z.a(ba);
    }
    
    public void a(final bj bj) {
        switch (bj.w()) {
            case 0: {
                this.k.a(bj);
                break;
            }
            case 1: {
                this.j.a(bj);
                break;
            }
            case 2: {
                this.h.a(bj);
                break;
            }
            case 3: {
                this.i.a(bj);
                break;
            }
        }
        if (Main.e(bj.s())) {
            this.q.a(bj);
        }
        h.ac.put(bj.s(), bj);
    }
    
    public void b(final bj bj) {
        this.k.b(bj);
        this.j.b(bj);
        this.h.b(bj);
        this.q.b(bj);
        switch (bj.w()) {
            case 0: {
                this.k.a(bj);
                break;
            }
            case 1: {
                this.j.a(bj);
                break;
            }
            case 2: {
                this.h.a(bj);
                break;
            }
        }
        if (Main.e(bj.s())) {
            this.q.a(bj);
        }
        this.c();
    }
    
    public boolean a(final String s) {
        return h.ac.containsKey(s);
    }
    
    public void b(final String s) {
        final bj bj = h.ac.get(s);
        switch (bj.w()) {
            case 0: {
                this.k.b(bj);
                break;
            }
            case 1: {
                this.j.b(bj);
                break;
            }
            case 2: {
                this.h.b(bj);
                break;
            }
        }
        this.q.b(bj);
        h.ac.remove(s);
    }
    
    public void b(final ba ba) {
        l.c(ba);
        this.z.b(ba);
        this.n.b(ba);
        this.l.b(ba);
        this.m.b(ba);
        this.o.b(ba);
        this.r.b(ba);
        this.s.b(ba);
        this.u.b(ba);
        this.t.b(ba);
        this.v.b(ba);
        this.w.b(ba);
        this.x.b(ba);
        this.y.b(ba);
        if (Main.d(ba.s())) {
            this.p.b(ba);
        }
        if (this.ah.a(ba.t())) {
            ((au)this.ah.c(ba.t())).b(ba);
        }
        this.ab.remove(ba.s());
        l.b().a(ba.q(), ba.f(), false, ba.j());
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(dj.w);
        graphics.fillRect(0, 0, 200, this.getSize().height);
        graphics.setColor(dj.m);
        graphics.fillRect(0, 0, 200, 15);
        super.paint(graphics);
    }
    
    public void a(final int n) {
        this.ag.setVisible(false);
        this.ah.setVisible(false);
        this.aj.setVisible(false);
        this.ai.setVisible(false);
        this.ak.setVisible(false);
        this.al.setVisible(false);
        switch (n) {
            case 0: {
                this.ag.setVisible(true);
                this.ag.a();
                this.ag.validate();
                break;
            }
            case 1: {
                this.ah.setVisible(true);
                this.ah.a();
                this.ah.validate();
                break;
            }
            case 2: {
                this.ai.setVisible(true);
                this.ai.a();
                this.ai.validate();
                break;
            }
            case 5: {
                this.aj.setVisible(true);
                this.aj.a();
                this.aj.validate();
                break;
            }
            case 4: {
                this.ak.setVisible(true);
                this.ak.a();
                this.ak.validate();
                break;
            }
            case 3: {
                this.al.setVisible(true);
                this.al.a();
                this.al.validate();
                break;
            }
        }
    }
    
    public aa e() {
        if (this.ag.isVisible()) {
            return this.ag;
        }
        if (this.ah.isVisible()) {
            return this.ah;
        }
        if (this.ai.isVisible()) {
            return this.ai;
        }
        if (this.ak.isVisible()) {
            return this.ak;
        }
        if (this.al.isVisible()) {
            return this.al;
        }
        if (this.aj.isVisible()) {
            return this.aj;
        }
        return this.ag;
    }
    
    public static synchronized h f() {
        if (h.au == null) {
            h.au = new h();
        }
        return h.au;
    }
    
    public static synchronized void g() {
        h.au = null;
    }
    
    private h() {
        this.g = 0;
        this.b = v.a();
        this.ae = null;
        this.af = null;
        this.setBackground(dj.k);
        this.f = new ah();
        this.c = t.a();
        boolean b = false;
        if (this.c.a("chat.sortcount").equals("true")) {
            b = true;
        }
        this.ab = new Hashtable();
        h.ac = new Hashtable();
        this.ad = new Hashtable();
        this.setLayout(null);
        this.am = new JComboBox();
        this.ae = new al(Main.h(), Main.p.a("dialog.goldupgrade"), 400, 100);
        this.af = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
        this.af.a.addActionListener(new am());
        this.ae.a.addActionListener(new am());
        final y y = new y();
        this.ag = new aa();
        this.ai = new aa();
        this.ah = new aa(y);
        this.aj = new aa();
        this.ak = new aa(y);
        this.al = new aa();
        this.aj.a(dj.b);
        this.ak.a(dj.b);
        this.ah.a(dj.b);
        this.ai.a(dj.b);
        this.ag.a(dj.b);
        this.am.addItemListener(this);
        (this.ao = new an(ImageRes.az, ImageRes.ay, ImageRes.a0, ImageRes.az, ImageRes.ay, 1)).setBounds(new Rectangle(2, 0, 33, 30));
        this.ao.addMouseListener(new ao(this, 0));
        this.ao.d();
        this.ao.setToolTipText(Main.p.a("cams.heading"));
        (this.ap = new an(ImageRes.aw, ImageRes.av, ImageRes.ax, ImageRes.aw, ImageRes.av, 1)).setBounds(new Rectangle(147, 0, 33, 30));
        this.ap.addMouseListener(new ao(this, 3));
        this.ap.d();
        this.ap.setToolTipText("Launch Broadcaster");
        (this.aq = new an(ImageRes.at, ImageRes.as, ImageRes.au, ImageRes.at, ImageRes.as, 1)).setBounds(new Rectangle(36, 0, 33, 30));
        this.aq.addMouseListener(new ao(this, 1));
        this.aq.d();
        this.aq.setToolTipText(Main.p.a("chat.heading"));
        (this.an = new an(ImageRes.aq, ImageRes.ap, ImageRes.ar, ImageRes.aq, ImageRes.ap, 1)).d();
        this.an.setToolTipText(Main.p.a("cams.settings"));
        this.an.setBounds(new Rectangle(69, 0, 33, 30));
        this.an.addMouseListener(new ao(this, 2));
        (this.ar = new ap("Create a chat room")).setToolTipText(Main.p.a("chat.makeroom.maketip"));
        this.ar.setLocation(3, 36);
        this.ar.addMouseListener(new aq(this));
        this.am.setFont(dj.aj);
        this.am.addItem(Main.p.a("cams.sort.gender"));
        this.am.addItem(Main.p.a("cams.sort.country"));
        this.am.addItem(Main.p.a("cams.sort.sexuality"));
        this.am.addItem("Membership");
        this.am.addItem("All");
        this.am.setOpaque(false);
        this.am.setBackground(dj.e);
        this.am.setForeground(dj.m);
        this.ar.setVisible(false);
        this.am.setBounds(new Rectangle(2, 30, 180, 17));
        this.at = new ar(60L, 198, 4);
        this.as = new ar(30L, 198, 4);
        this.a = new Dimension(200, 600);
        this.a();
        if (!b) {
            final as as = new as();
            this.k = new at(as, Main.p.a("chat.rooms.awcrooms"), false, 2);
            this.j = new at(as, Main.p.a("chat.rooms.gamesrooms"), false, 2);
            this.h = new at(as, Main.p.a("chat.rooms.memberrooms"), false, 2);
            this.q = new at(as, Main.p.a("directory.fav"), false, 2);
            this.i = new at(as, Main.p.a("directory.international"), false, 2);
        }
        else {
            final aw aw = new aw();
            this.k = new at(aw, Main.p.a("chat.rooms.awcrooms"), false, 0);
            this.j = new at(aw, Main.p.a("chat.rooms.gamesrooms"), false, 0);
            this.h = new at(aw, Main.p.a("chat.rooms.memberrooms"), false, 0);
            this.q = new at(aw, Main.p.a("directory.fav"), false, 0);
            this.i = new at(aw, Main.p.a("directory.international"), false, 0);
        }
        if (n.b().s() == 10000) {
            final az az = new az("Bingo", 1, "Bingo Chat Room", "bingo", "http://www.bingocafe.com/anyweb.asp");
            this.j.a(new az("Poker", 1, "Poker Empire", "poker", "http://www.thepokerempire.com/index.htm?wm=1709709"));
            this.j.a(az);
        }
        this.m = new a0(y, Main.p.a("gender.female"), false);
        this.aa = new a1(y, "ImLive", false);
        this.o = new a0(y, Main.p.a("gender.group"), false);
        this.l = new a0(y, Main.p.a("gender.male"), false);
        this.n = new a0(y, Main.p.a("gender.other"), false);
        this.r = new a0(y, Main.p.a("cams.sexuality.straight"), false);
        this.t = new a0(y, Main.p.a("cams.sexuality.gay"), false);
        this.u = new a0(y, Main.p.a("cams.sexuality.lesbian"), false);
        this.s = new a0(y, Main.p.a("cams.sexuality.bi"), false);
        this.z = new a0(y, Main.p.a("cams.all"), false);
        this.p = new a0(y, Main.p.a("directory.fav"), false);
        this.y = new a0(y, Main.p.a("membership.staff"), false);
        this.x = new a0(y, Main.p.a("membership.gold"), false);
        this.w = new a0(y, Main.p.a("membership.silver"), false);
        this.v = new a0(y, Main.p.a("membership.standard"), false);
        this.add(this.aj);
        this.add(this.ag);
        this.add(this.ai);
        this.add(this.ah);
        this.add(this.ak);
        this.add(this.al);
        this.add(this.am);
        this.add(this.an);
        this.add(this.ao);
        if (!System.getProperty("os.name").startsWith("Mac")) {
            this.add(this.ap);
        }
        this.add(this.aq);
        this.add(this.ar);
        this.add(this.b);
        this.at.a(dj.m, dj.a, dj.x);
        this.as.a(dj.n, dj.a, dj.x);
        this.addKeyListener(this.aj);
        this.addKeyListener(this.ag);
        this.addKeyListener(this.ah);
        this.addKeyListener(this.ai);
        this.addKeyListener(this.ak);
        this.addKeyListener(this.al);
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        if (this.c.a("cam.gender.male").equals("true")) {
            b2 = true;
        }
        if (this.c.a("cam.gender.female").equals("true")) {
            b3 = true;
        }
        if (this.c.a("cam.gender.group").equals("true")) {
            b4 = true;
        }
        if (this.c.a("cam.gender.other").equals("true")) {
            b5 = true;
        }
        this.al.a(this.p);
        this.al.a(this.y);
        this.al.a(this.x);
        this.al.a(this.w);
        this.al.a(this.v);
        this.ag.a(this.p);
        this.ag.a(this.m);
        this.ag.a(this.o);
        this.ag.a(this.l);
        this.ag.a(this.n);
        this.ag.a(this.aa);
        this.ai.a(this.p);
        this.ai.a(this.r);
        this.ai.a(this.s);
        this.ai.a(this.u);
        this.ai.a(this.t);
        this.aj.a(this.q);
        this.aj.a(this.k);
        this.aj.a(this.i);
        this.aj.a(this.j);
        this.aj.a(this.h);
        this.ak.a(this.z);
        this.ag.setVisible(true);
        this.ah.setVisible(false);
        this.aj.setVisible(false);
        this.ai.setVisible(false);
        this.ak.setVisible(false);
        this.al.setVisible(false);
        this.e = new a2(b2, b3, b4, b5);
        this.add(this.as);
        this.add(this.at);
    }
    
    public static bj c(final String s) {
        return h.ac.get(s);
    }
    
    public static boolean d(final String s) {
        final Enumeration<bj> elements = h.ac.elements();
        while (elements.hasMoreElements()) {
            if (elements.nextElement().r().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static String e(final String s) {
        final Enumeration<bj> elements = h.ac.elements();
        while (elements.hasMoreElements()) {
            final bj bj = elements.nextElement();
            if (bj.e() != null && bj.e().equalsIgnoreCase(s)) {
                return bj.s();
            }
        }
        return "";
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.a(this.am.getSelectedIndex());
    }
    
    public void h() {
        if (this.g != 0) {
            this.a(this.am.getSelectedIndex());
            this.am.setVisible(true);
            this.ar.setVisible(false);
            this.g = 0;
            this.b.setVisible(false);
            this.a();
        }
    }
    
    public void i() {
        if (this.g != 1) {
            this.a(5);
            this.ar.setVisible(true);
            this.am.setVisible(false);
            this.g = 1;
            this.b.setVisible(true);
            this.a();
        }
    }
    
    public void j() {
        if (this.d == null) {
            this.d = new bm(this.e);
        }
        if (!this.d.isShowing()) {
            this.d.setSize(590, 400);
            this.d.setResizable(false);
            this.d.show();
        }
        else {
            this.d.toFront();
        }
    }
    
    public void k() {
        if (n.b().h() >= 2) {
            if (!this.f.isShowing()) {
                this.f.setSize(400, 400);
                this.f.setResizable(false);
                this.f.show();
            }
            else {
                this.f.toFront();
            }
        }
        else {
            this.ae.setVisible(true);
        }
    }
}
