import java.awt.MenuComponent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.MalformedURLException;
import java.applet.AudioClip;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.net.URL;
import java.applet.AppletContext;
import java.awt.CheckboxMenuItem;
import java.awt.MenuBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class p extends Frame implements Runnable, ActionListener
{
    static String a;
    static int b;
    static int c;
    static boolean d;
    v e;
    int f;
    Dimension g;
    String h;
    String i;
    boolean j;
    String k;
    String l;
    String m;
    irc n;
    String o;
    Label p;
    Button q;
    TextField r;
    Checkbox s;
    Checkbox t;
    TextField u;
    bb v;
    bf w;
    bh x;
    bu y;
    br z;
    j A;
    bq B;
    i C;
    be D;
    bc E;
    f F;
    bk G;
    GridBagLayout H;
    GridBagConstraints I;
    MenuBar J;
    m K;
    CheckboxMenuItem[] L;
    String[] M;
    AppletContext N;
    URL O;
    String P;
    boolean Q;
    String[] R;
    CheckboxMenuItem[] S;
    String[] T;
    CheckboxMenuItem[] U;
    String V;
    String W;
    String X;
    int Y;
    Image Z;
    Image ba;
    int bb;
    int bc;
    int bd;
    int be;
    int bf;
    int bg;
    int bh;
    int bi;
    MediaTracker bj;
    Color bk;
    n bl;
    u bm;
    CheckboxMenuItem bn;
    CheckboxMenuItem bo;
    CheckboxMenuItem bp;
    CheckboxMenuItem bq;
    CheckboxMenuItem br;
    CheckboxMenuItem bs;
    CheckboxMenuItem bt;
    bl bu;
    String[] bv;
    MenuItem[] bw;
    j bx;
    MenuItem[] by;
    k bz;
    String[] bA;
    x bB;
    Menu bC;
    Menu bD;
    Menu bE;
    Menu bF;
    Menu bG;
    Menu bH;
    Menu bI;
    Menu bJ;
    Menu bK;
    Menu bL;
    Menu bM;
    Menu bN;
    
    void a(final Event event) {
        if (event.target == this.r) {
            this.q.requestFocus();
            return;
        }
        if (event.target == this.q) {
            this.r.requestFocus();
        }
    }
    
    void a() {
        this.J.add(this.bC);
        this.J.add(this.bD);
        this.J.add(this.bE);
        this.J.add(this.bF);
        if (this.bv != null) {
            this.J.add(this.bG);
        }
        if (this.bH != null) {
            this.J.add(this.bH);
        }
        this.J.add(this.bI);
        this.J.add(this.bJ);
    }
    
    void a(final String s) {
        final String a = irc.cy.a();
        String s2 = null;
        String s3 = null;
        final t h;
        v v;
        if ((h = this.D.h(a)) != null) {
            v = h.i;
            s2 = a;
        }
        else {
            final y d;
            if ((d = this.z.d(a)) != null) {
                v = d.d;
                s3 = a;
            }
            else {
                v = this.e;
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        while (true) {
            Label_0117: {
                if (!bm.dX) {
                    break Label_0117;
                }
                this.w.a(this.bu.a(stringTokenizer.nextToken(), v, s2, s3));
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return;
            }
            continue;
        }
    }
    
    void a(final char c) {
        switch (c) {
            case '+': {
                if (this.Y != this.R.length - 1) {
                    this.b(++this.Y);
                    this.W = this.R[this.Y];
                    this.e();
                    return;
                }
                break;
            }
            case '-': {
                if (this.Y != 0) {
                    this.b(--this.Y);
                    this.W = this.R[this.Y];
                    this.e();
                    return;
                }
                break;
            }
        }
    }
    
    p(final String h, final String i, final boolean j, final String k, final String s, final String l, final Image z, final String s2, final String s3, final Image ba, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final bd[] array, final irc n, final String s10, final String s11, final String s12, final String s13, final String s14, final String s15, final String s16, final String s17, final String s18, final String s19, final String s20, final String s21, final String s22, final String s23, final String s24, final String s25, final String s26, final String s27, final String s28, final String s29, final String s30, final String s31, final String s32, final String s33, final String s34, final String s35, final String s36, final String s37, final String s38, final String s39, final String s40, final String s41, final String s42, final String s43, final String s44, final AudioClip audioClip, final AppletContext n2, final String s45, final String p62, final boolean q, final String[] array2, final String[] array3, final String[] bv, final String[] ba2) {
        final boolean dx = bm.dX;
        this.o = new String(bm.b);
        this.p = new Label(bm.a);
        this.q = new Button(this.o);
        this.r = new TextField(20);
        this.u = new TextField(15);
        this.v = new bb(p.b, p.c);
        this.x = new bh(this);
        this.y = new bu();
        this.A = new j(bm.cR, "", e("\n$\"dl"), false);
        this.E = new bc();
        this.F = new f(this, true, this.E);
        this.G = new bk();
        this.H = new GridBagLayout();
        this.I = new GridBagConstraints();
        this.J = new MenuBar();
        this.K = new m();
        this.O = null;
        this.R = new String[] { e("u]"), e("u_"), e("uY"), e("u["), e("uU"), e("v]") };
        this.S = new CheckboxMenuItem[this.R.length];
        this.T = new String[] { bm.R, bm.S, bm.T };
        this.U = new CheckboxMenuItem[this.T.length];
        this.bl = new n();
        this.bm = new u();
        this.bn = new CheckboxMenuItem(bm.f);
        this.bo = new CheckboxMenuItem(bm.g);
        this.bp = new CheckboxMenuItem(bm.h);
        this.bq = new CheckboxMenuItem(bm.i);
        this.br = new CheckboxMenuItem(bm.j);
        this.bs = new CheckboxMenuItem(bm.k);
        this.bt = new CheckboxMenuItem(bm.l);
        this.bx = new j(bm.dj, bm.dk, e("\u000e\"(al"), false);
        this.bz = new k(this);
        if (!irc.e) {
            this.hide();
        }
        System.out.println(this.y.f() + e("d\u0005\u0015[<~BNX;3C\u000bN:-\u001f\u0002\u0001/+\u0000"));
        System.out.println(e("\u00edM1N86\u0004\u0002Dl\u0000\u0018\u0002]#0AA\u001eu}[L\u001d|t^M\u000f\r(\u0001A}%#\u0005\u0015\\l\u0016\b\u0012J>2\b\u0005"));
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.y.c(s);
        this.l = l;
        this.n = n;
        this.Z = z;
        this.ba = ba;
        this.N = n2;
        this.Q = q;
        Label_0643: {
            if (irc.bB) {
                this.bv = bv;
                if (!dx) {
                    break Label_0643;
                }
            }
            this.bv = null;
        }
        this.bA = ba2;
        try {
            if (s45 != null) {
                this.O = new URL(s45);
            }
        }
        catch (MalformedURLException ex) {
            this.O = null;
        }
        URL url = null;
        try {
            url = new URL(n.getCodeBase(), e("%\u0001\bN?!\u001eO[40"));
        }
        catch (MalformedURLException ex2) {}
        if (url != null) {
            this.E.a(url);
        }
        this.P = p62;
        Label_0774: {
            if (s6 == null) {
                this.bk = new Color(192, 192, 192);
                if (!dx) {
                    break Label_0774;
                }
            }
            this.bk = new Color(Integer.parseInt(s6, 16));
        }
        Label_0817: {
            if (s44 != null && s44.toUpperCase().equals(e("\u001d(2"))) {
                this.bo.setState(true);
                if (!dx) {
                    break Label_0817;
                }
            }
            this.bo.setState(false);
        }
        this.setTitle(this.y.f());
        this.B = new bq();
        this.C = new i(this, true, this.B);
        this.z = new br(this.y, this.B, this.E, this.bl, this.A, this.G, this, s9, array, s10, s11, s12, s13, s14, s15, s38, s39, s40, s41, s16, s17, s18, s36, s37, audioClip, this.bp, n2, this.O, p62, array2, array3, bv);
        this.D = new be(this.y, this.z, this.B, this.E, this.bl, this.A, this.G, this, s8, array, s19, s20, s21, s22, s23, s24, s25, s26, s27, s28, s29, s30, s31, s32, s33, s34, s35, s42, s43, n2, this.O, p62, array2, array3, bv);
        irc.cy.a(this.D, this.z, this.bm);
        this.bl.a(this.D);
        this.bu = new bl(this.y, this.z, this.B, this.D, this.E, this.bl, this.G, p62);
        n2.showStatus(bm.m);
        this.bj = new MediaTracker(this);
        int a = 0;
        if (z != null || ba != null) {
            if (z != null) {
                this.bf = Integer.parseInt(s2);
                this.bg = Integer.parseInt(s3);
                this.bj.addImage(z, a++);
            }
            if (ba != null) {
                this.bh = Integer.parseInt(s4);
                this.bi = Integer.parseInt(s5);
                this.bj.addImage(ba, a++);
            }
        }
        if (irc.z != null) {
            this.bj.addImage(irc.z, a++);
        }
        if (irc.A != null) {
            this.bj.addImage(irc.A, a++);
        }
        if (irc.B != null) {
            this.bj.addImage(irc.B, a++);
        }
        if (irc.C != null) {
            this.bj.addImage(irc.C, a++);
        }
        if (irc.D != null) {
            this.bj.addImage(irc.D, a++);
        }
        if (irc.x != null) {
            this.bj.addImage(irc.x, a++);
        }
        if (irc.y != null) {
            this.bj.addImage(irc.y, a++);
        }
        if (irc.E != null) {
            this.bj.addImage(irc.E, a++);
        }
        if (irc.F != null) {
            this.bj.addImage(irc.F, a++);
        }
        if (irc.G != null) {
            this.bj.addImage(irc.G, a++);
        }
        if (irc.H != null) {
            this.bj.addImage(irc.H, a++);
        }
        if (irc.bs) {
            a = irc.cz.a(this.bj, a);
        }
        if (irc.cw != null) {
            this.bj.addImage(irc.cw, a++);
        }
        if (irc.cx != null) {
            this.bj.addImage(irc.cx, a++);
        }
        int size = 0;
        int n3 = 0;
    Label_1605:
        while (true) {
            if (array[size] != null) {
                this.bj.addImage(array[size].b(), a++);
            }
            ++size;
            while (size == 10) {
                size = irc.L.size();
                n3 = 0;
                if (!dx) {
                    break Label_1605;
                }
                if (!dx) {
                    break Label_1605;
                }
            }
        }
        while (n3 != size) {
            this.bj.addImage((Image)irc.M.elementAt(n3), a++);
            ++n3;
        }
        if (irc.N != null) {
            this.bj.addImage(irc.N, a++);
        }
        if (a != 0) {
            try {
                this.bj.waitForAll(30000L);
            }
            catch (InterruptedException ex3) {}
        }
        if (z != null) {
            this.bb = z.getWidth(this);
            this.bc = z.getHeight(this);
        }
        if (ba != null) {
            this.bd = ba.getWidth(this);
            this.be = ba.getHeight(this);
        }
        this.bB = new x(this, z, ba, this.bb, this.bc, this.bd, this.be, this.bf, this.bg, this.bh, this.bi, this.bk);
        int n4 = 0;
    Block_37:
        while (true) {
            if (array[n4] != null) {
                array[n4].a(array[n4].b.getWidth(this), array[n4].b.getHeight(this));
            }
            int n5 = ++n4;
            while (n5 == 10) {
                n2.showStatus("");
                n.b();
                this.t = new Checkbox(bm.c);
                n5 = (q ? 1 : 0);
                if (!dx) {
                    break Block_37;
                }
            }
        }
        if (q) {
            this.t.setState(true);
        }
        Label_1903: {
            if (irc.e) {
                this.add(e("\u0007\b\u000f[)6"), this.bB);
                this.setVisible(true);
                if (!dx) {
                    break Label_1903;
                }
            }
            irc.cy.a(bm.e, this.bB, this.v);
        }
        if (j || k != null) {
            this.b();
            return;
        }
        this.repaint();
        this.setBackground(this.bk);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.bB.setLayout(layout);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.p, gridBagConstraints);
        this.bB.add(this.p);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.r, gridBagConstraints);
        this.bB.add(this.r);
        this.q.setBackground(bn.b(s7));
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(this.q, gridBagConstraints);
        this.bB.add(this.q);
        (this.s = new Checkbox(bm.d)).setState(true);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.s, gridBagConstraints);
        this.bB.add(this.s);
        this.u.setEditable(false);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        layout.setConstraints(this.u, gridBagConstraints);
        this.bB.add(this.u);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        layout.setConstraints(this.t, gridBagConstraints);
        this.bB.add(this.t);
        this.r.requestFocus();
        this.setBounds(irc.r, irc.s, irc.bd, irc.be);
        this.bB.validate();
        this.validate();
    }
    
    void a(final Font font) {
        bo.a(this.bC, font);
        bo.a(this.bD, font);
        bo.a(this.bE, font);
        bo.a(this.bF, font);
        bo.a(this.bI, font);
        bo.a(this.bJ, font);
        if (this.bH != null) {
            bo.a(this.bH, font);
        }
        if (this.bG != null) {
            bo.a(this.bG, font);
        }
        bo.a(this.bK, font);
        bo.a(this.bL, font);
        bo.a(this.bM, font);
        bo.a(this.bN, font);
        this.g();
        this.a();
    }
    
    void a(final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.L[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.M.length) {
                    continue;
                }
                break;
            }
            this.L[n].setState(true);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean dx = bm.dX;
        if (this.j || this.k != null || !this.r.getText().equals("")) {
            this.bB.setLayout(this.H);
            this.setBounds(irc.r, irc.s, irc.bf, irc.bg);
            irc.a();
            (this.e = new v(100, this.N, null, null, this.z, null)).a(irc.f);
            if (this.t.getState()) {
                this.Q = true;
            }
            Label_0258: {
                if (this.k != null) {
                    this.k = this.d(this.k);
                    this.y.e(this.k);
                    if (!dx) {
                        break Label_0258;
                    }
                }
                if (this.j) {
                    this.y.e(c(irc.cp));
                    if (!dx) {
                        break Label_0258;
                    }
                }
                this.y.e(this.d(this.r.getText()));
                this.bB.remove(this.p);
                this.bB.remove(this.r);
                this.bB.remove(this.q);
                this.bB.remove(this.s);
                this.bB.remove(this.u);
                this.bB.remove(this.t);
            }
            Label_0340: {
                if (this.s != null && !this.s.getState() && !this.u.getText().equals("")) {
                    this.y.b(this.u.getText());
                    if (!dx) {
                        break Label_0340;
                    }
                }
                if (irc.k != null) {
                    this.y.b(irc.k);
                    if (!dx) {
                        break Label_0340;
                    }
                }
                this.y.b(c(9));
            }
            if (this.bv != null) {
                this.bw = new MenuItem[this.bv.length];
            }
            if (this.bA != null) {
                this.by = new MenuItem[this.bA.length];
            }
            this.d();
            if (irc.o) {
                this.setMenuBar(this.J);
            }
            this.I.fill = 1;
            this.I.weightx = 1.0;
            this.I.weighty = 50.0;
            this.I.gridx = 0;
            this.I.gridy = 0;
            this.I.gridheight = 50;
            this.H.setConstraints(this.e, this.I);
            this.bB.add(this.e);
            this.I.weighty = 1.0;
            this.I.gridx = 0;
            this.I.gridy = 50;
            this.I.gridheight = 1;
            this.H.setConstraints(this.v, this.I);
            this.bB.add(this.v);
            this.v.addActionListener(this);
            this.bB.validate();
            this.v.requestFocus();
            Label_0582: {
                if (irc.f && irc.e) {
                    this.show();
                    if (!dx) {
                        break Label_0582;
                    }
                }
                this.hide();
            }
            this.Z = null;
            this.ba = null;
            if (irc.h && irc.bn) {
                new bj(this.y.d());
            }
            new Thread(this).start();
        }
    }
    
    void c() {
        final boolean dx = bm.dX;
        p.d = true;
        int n = 0;
    Label_0010:
        while (true) {
            Label_0123: {
                if (irc.f) {
                    this.e.a(bm.n + " " + this.h, bn.e, false);
                    if (!dx) {
                        break Label_0123;
                    }
                }
                if (!irc.e) {
                    this.e.a(true);
                    this.e.a(bm.n + " " + this.h, bn.e, false);
                    this.e.a(false);
                }
            }
            this.w = new bf(this, this.e, this.x, this.y, this.h, this.i, this.n, this.l, this.m, this.z, this.B, this.D, this.bm, this.bl, this.A, this.G, this.bn, this.bo, this.Q, this.bp, this.N);
            do {
                if (this.bA != null) {
                    this.h = this.bA[n++];
                    if (n == this.bA.length) {
                        n = 0;
                    }
                    if (!p.d) {
                        continue Label_0010;
                    }
                }
                this.z.a(this.w);
                this.z.a(this.D);
                this.D.a(this.w);
                this.bl.a(this.w);
                this.bm.a(this.w);
                this.G.a(this.w);
                this.B.a(this.w);
                this.A.a(this.w);
                this.bx.a(this.w);
            } while (dx);
            break;
        }
        if (irc.cl != null) {
            this.B.a(irc.cl);
        }
        if (irc.cm != null) {
            this.G.a(irc.cm);
        }
    }
    
    void d() {
        final boolean dx = bm.dX;
        this.bC = new Menu(bm.p);
        this.bD = new Menu(bm.q);
        this.bE = new Menu(bm.r);
        this.bK = new Menu(bm.s);
        this.bF = new Menu(bm.u);
        this.bG = new Menu(bm.di);
        this.bH = new Menu(bm.t);
        this.bI = new Menu(bm.v);
        this.bJ = new Menu(bm.x);
        this.J.add(this.bC);
        this.J.add(this.bD);
        if (irc.q) {
            this.J.add(this.bE);
        }
        this.J.add(this.bF);
        if (this.bv != null) {
            this.J.add(this.bG);
        }
        if (this.bA != null && irc.q) {
            this.J.add(this.bH);
        }
        this.J.add(this.bI);
        this.J.add(this.bJ);
        this.J.setHelpMenu(this.bJ);
        this.bC.add(new MenuItem(bm.B));
        this.bC.add(new MenuItem(bm.C));
        this.bC.addSeparator();
        this.bC.add(new MenuItem(bm.D));
        this.bD.add(this.bn);
        this.bD.add(this.bo);
        this.bD.add(this.bp);
        if (irc.cg) {
            this.bp.setState(true);
        }
        this.bD.add(this.bq);
        if (irc.bo) {
            this.bq.setState(true);
        }
        this.bD.add(this.br);
        if (irc.bp) {
            this.br.setState(true);
        }
        this.bD.add(this.bs);
        if (irc.bq) {
            this.bs.setState(true);
        }
        if (irc.bs) {
            this.bt.setState(true);
        }
        if (irc.U) {
            this.bE.add(new MenuItem(bm.E));
        }
        this.bE.add(new MenuItem(bm.F));
        this.bE.add(new MenuItem(bm.G));
        this.bE.add(new MenuItem(bm.H));
        if (irc.n && irc.bB) {
            this.bE.add(new MenuItem(bm.I));
        }
        this.bE.add(new MenuItem("-"));
        this.bE.add(this.bK);
        this.bK.add(new MenuItem(bm.J));
        this.bK.add(new MenuItem(bm.K));
        this.bF.add(new MenuItem(bm.L));
        this.bF.add(new MenuItem(bm.N));
        if (this.bv != null) {
            this.bG.add(new MenuItem(bm.be));
            this.bG.add(new MenuItem("-"));
            int n = 0;
            while (true) {
                Label_0767: {
                    if (!dx) {
                        break Label_0767;
                    }
                    this.bw[n] = new MenuItem(this.bv[n]);
                    this.bG.add(this.bw[n]);
                    ++n;
                }
                if (n != this.bv.length) {
                    continue;
                }
                break;
            }
        }
        int n2;
        while (true) {
            Label_0877: {
                if (this.bA == null) {
                    break Label_0877;
                }
                if (irc.T) {
                    this.bH.add(new MenuItem(bm.bf));
                    this.bH.add(new MenuItem("-"));
                }
                n2 = 0;
                while (true) {
                    Label_0868: {
                        if (!dx) {
                            break Label_0868;
                        }
                        this.by[n2] = new MenuItem(this.bA[n2]);
                        this.bH.add(this.by[n2]);
                        ++n2;
                    }
                    if (n2 != this.bA.length) {
                        continue;
                    }
                    break;
                }
            }
            this.bL = new Menu(bm.O);
            this.bM = new Menu(bm.P);
            this.bN = new Menu(bm.Q);
            this.bI.add(this.bL);
            this.bI.add(this.bM);
            this.bI.add(this.bN);
            this.M = this.e.b();
            this.L = new CheckboxMenuItem[this.M.length];
            n2 = 0;
            if (dx) {
                continue;
            }
            break;
        }
    Label_1021_Outer:
        while (true) {
            while (true) {
                Label_1024: {
                    if (!dx) {
                        break Label_1024;
                    }
                    this.L[n2] = new CheckboxMenuItem(this.M[n2]);
                    this.bL.add(this.L[n2]);
                    ++n2;
                }
                if (n2 == this.M.length) {
                    n2 = 0;
                    if (dx) {
                        if (dx) {
                            continue;
                        }
                    }
                    while (true) {
                        if (n2 == this.R.length) {
                            n2 = 0;
                            if (!dx) {
                                break;
                            }
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            this.S[n2] = new CheckboxMenuItem(this.R[n2]);
                            this.bM.add(this.S[n2]);
                        }
                        ++n2;
                    }
                    while (true) {
                        p p;
                        if (n2 == this.T.length) {
                            this.V = this.M[irc.bR];
                            this.W = this.R[irc.bQ];
                            this.X = this.T[irc.bS];
                            this.a(irc.bR);
                            this.d(irc.bS);
                            this.b(irc.bQ);
                            this.e();
                            p = this;
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            this.U[n2] = new CheckboxMenuItem(this.T[n2]);
                            p = this;
                        }
                        p.bN.add(this.U[n2]);
                        ++n2;
                    }
                    if (this.O != null) {
                        this.bJ.add(new MenuItem(bm.U));
                        this.bJ.add(new MenuItem("-"));
                    }
                    this.bJ.add(new MenuItem(bm.V));
                    return;
                }
                break;
            }
            continue Label_1021_Outer;
        }
    }
    
    void e() {
        final boolean dx = bm.dX;
        int n = 0;
        Label_0059: {
            if (this.X.equals(bm.R)) {
                n = 0;
                if (!dx) {
                    break Label_0059;
                }
            }
            if (this.X.equals(bm.T)) {
                n = 2;
                if (!dx) {
                    break Label_0059;
                }
            }
            if (this.X.equals(bm.S)) {
                n = 1;
            }
        }
        final Font font = new Font(this.V, n, Integer.parseInt(this.W));
        this.e.a(font, irc.bT);
        this.v.setFont(font);
        this.a(font);
    }
    
    public void b(final String s) {
        int n = 0;
        while (true) {
            Label_0039: {
                if (!bm.dX) {
                    break Label_0039;
                }
                if (s.equalsIgnoreCase(this.M[n])) {
                    this.V = this.M[n];
                    this.e();
                    return;
                }
                ++n;
            }
            if (n == this.M.length) {
                return;
            }
            continue;
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.v) {
            final String text = this.v.getText();
            this.v.setText("");
            this.w.a(this.bu.a(text, this.e, null, null));
        }
    }
    
    void b(final int y) {
        final boolean dx = bm.dX;
        int n = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.S[n].setState(false);
                    ++n;
                }
                if (n < this.R.length) {
                    continue;
                }
                break;
            }
            this.S[y].setState(true);
            this.Y = y;
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    static String c(final int n) {
        final byte[] array = new byte[n];
        int n2 = 0;
        while (true) {
            Label_0039: {
                if (!bm.dX) {
                    break Label_0039;
                }
                array[n2] = (byte)(97 + (byte)(Object)new Double(26.0 * Math.random()));
                ++n2;
            }
            if (n2 == n) {
                return new String(array);
            }
            continue;
        }
    }
    
    static {
        p.a = e("dE\u000f@8d\u001f\u0004H%7\u0019\u0004]) D");
        p.b = 80;
        p.c = 30;
    }
    
    int f() {
        int n = 0;
        if (irc.f) {
            n = 1;
        }
        return n + this.D.a() + this.z.c();
    }
    
    void c(final String h) {
        irc.d = true;
        if (this.w != null) {
            this.w.a(e("\u00158({l~") + this.P + "\n");
        }
        final int index = h.indexOf(58);
        Label_0103: {
            if (index < 0) {
                this.h = h;
                this.i = e("r[W\u0018");
                if (!bm.dX) {
                    break Label_0103;
                }
            }
            this.h = h.substring(0, index);
            this.i = h.substring(index + 1);
        }
        this.c();
    }
    
    void b(final Event event) {
        if (event.target == this.r) {
            this.q.requestFocus();
            return;
        }
        if (event.target == this.q) {
            this.r.requestFocus();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.w != null && ((event.modifiers & 0x2) != 0x0 || (event.modifiers & 0x4) != 0x0)) {
            this.w.a(e("\b82j\u001e\u0017g"));
        }
        if (this.v != null) {
            this.v.requestFocus();
        }
        return false;
    }
    
    public boolean action(final Event event, final Object o) {
        final boolean dx = bm.dX;
        if (event.target instanceof Button && this.o.equals(o)) {
            this.b();
        }
        Label_0079: {
            if (event.target == this.s) {
                if (!this.s.getState()) {
                    this.u.setEditable(true);
                    this.u.requestFocus();
                    if (!dx) {
                        break Label_0079;
                    }
                }
                this.u.setEditable(false);
            }
        }
        if (event.target == this.r || event.target == this.u) {
            this.b();
        }
        if (event.target == this.v) {
            final String text = this.v.getText();
            this.v.setText("");
            this.w.a(this.bu.a(text, this.e, null, null));
            return true;
        }
        if (event.target instanceof w) {
            String s = ((w)event.target).c();
            if (s.charAt(0) == '@') {
                s = s.substring(1);
            }
            this.w.a(e("\u0013%.f\u001fd") + s + "\n");
            return true;
        }
        if (event.target == this.bq) {
            if (this.bq.getState()) {
                irc.bo = true;
                if (!dx) {
                    return true;
                }
            }
            irc.bo = false;
            return true;
        }
        if (event.target == this.br) {
            if (this.br.getState()) {
                irc.bp = true;
                if (!dx) {
                    return true;
                }
            }
            irc.bp = false;
            return true;
        }
        if (event.target == this.bs) {
            if (this.bs.getState()) {
                irc.bq = true;
                if (!dx) {
                    return true;
                }
            }
            irc.bq = false;
            return true;
        }
        if (event.target == this.bt) {
            irc.bs = this.bt.getState();
            return true;
        }
        if (!(event.target instanceof CheckboxMenuItem)) {
            if (event.target instanceof MenuItem) {
                final String s2 = (String)o;
                if (s2.equals(bm.B)) {
                    irc.d = true;
                    this.w.a(e("\u00158({l~") + this.P + "\n");
                    this.c();
                    return true;
                }
                if (s2.equals(bm.C)) {
                    irc.d = true;
                    this.w.a(e("\u00158({l~") + this.P + "\n");
                    this.e.a(irc.R + bm.bU, bn.e, false);
                    return true;
                }
                if (s2.equals(bm.D)) {
                    irc.d = false;
                    this.w.a(e("\u00158({l~") + this.P + "\n");
                    return true;
                }
                if (s2.equals(bm.E)) {
                    this.A.a(bm.cS);
                    this.A.show();
                    return true;
                }
                if (s2.equals(bm.bf)) {
                    this.bz.show();
                    return true;
                }
                if (s2.equals(bm.F)) {
                    this.F.c();
                    return true;
                }
                if (s2.equals(bm.G)) {
                    this.C.b();
                    return true;
                }
                if (s2.equals(bm.H)) {
                    this.G.a();
                    return true;
                }
                if (s2.equals(bm.I)) {
                    if (irc.m < 0) {
                        this.w.a(e("\b$2{F"));
                        if (!dx) {
                            return true;
                        }
                    }
                    this.w.a(e("\b$2{lz") + irc.m + "\n");
                    return true;
                }
                if (s2.equals(bm.J)) {
                    this.bl.show();
                    return true;
                }
                if (s2.equals(bm.K)) {
                    this.w.a(e("\u0005: vF"));
                    this.bl.a();
                    return true;
                }
                if (s2.equals(bm.V)) {
                    this.K.show();
                    return true;
                }
                if (s2.equals(bm.L)) {
                    this.e.a();
                    return true;
                }
                if (s2.equals(bm.N)) {
                    this.hide();
                    irc.f = false;
                    this.e.a(false);
                    return true;
                }
                if (s2.equals(bm.U)) {
                    this.N.showDocument(this.O, e("\u001b\u000f\rN\"/"));
                    return true;
                }
                if (s2.equals(bm.be)) {
                    this.bx.show();
                    return true;
                }
                if (this.bv != null) {
                    int n = 0;
                    while (true) {
                        Label_1351: {
                            if (!dx) {
                                break Label_1351;
                            }
                            if (event.target == this.bw[n]) {
                                this.w.a(e("\u000e\"(al") + this.bv[n] + "\n");
                                return true;
                            }
                            ++n;
                        }
                        if (n != this.bv.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (this.bA != null) {
                    int n2 = 0;
                    while (true) {
                        Label_1406: {
                            if (!dx) {
                                break Label_1406;
                            }
                            if (event.target == this.by[n2]) {
                                this.c(this.bA[n2]);
                                return true;
                            }
                            ++n2;
                        }
                        if (n2 != this.bA.length) {
                            continue;
                        }
                        break;
                    }
                }
            }
            return true;
        }
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = 0;
        while (true) {
            while (true) {
                Label_0458: {
                    if (!dx) {
                        break Label_0458;
                    }
                    if (this.L[n6].getState()) {
                        n3 = n6;
                    }
                    Label_0455: {
                        if (this.L[n6] == event.target) {
                            this.a(n6);
                            this.V = this.M[n6];
                            this.e();
                            if (!dx) {
                                break Label_0455;
                            }
                        }
                        this.L[n6].setState(false);
                    }
                    ++n6;
                }
                if (n6 != this.M.length) {
                    continue;
                }
                break;
            }
            n6 = 0;
            if (dx) {
                continue;
            }
            break;
        }
    Label_0498_Outer:
        while (true) {
            while (true) {
                Label_0552: {
                    if (!dx) {
                        break Label_0552;
                    }
                    if (this.U[n6].getState()) {
                        n4 = n6;
                    }
                    Label_0549: {
                        if (this.U[n6] == event.target) {
                            this.d(n6);
                            this.X = this.T[n6];
                            this.e();
                            if (!dx) {
                                break Label_0549;
                            }
                        }
                        this.U[n6].setState(false);
                    }
                    ++n6;
                }
                if (n6 == this.T.length) {
                    n6 = 0;
                    if (dx) {
                        if (dx) {
                            continue;
                        }
                    }
                    int n7 = 0;
                    while (true) {
                        if (n6 == this.R.length) {
                            n7 = n3;
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            this.S[n6].getState();
                        }
                        if (n7 != 0) {
                            n5 = n6;
                        }
                        Label_0646: {
                            if (this.S[n6] == event.target) {
                                this.b(n6);
                                this.W = this.R[n6];
                                this.e();
                                if (!dx) {
                                    break Label_0646;
                                }
                            }
                            this.S[n6].setState(false);
                        }
                        ++n6;
                    }
                    if (n7 >= 0) {
                        this.L[n3].setState(true);
                    }
                    if (n4 >= 0) {
                        this.U[n4].setState(true);
                    }
                    if (n5 >= 0) {
                        this.S[n5].setState(true);
                    }
                    return true;
                }
                break;
            }
            continue Label_0498_Outer;
        }
    }
    
    private String d(String substring) {
        final int index = substring.indexOf(58);
        if (index >= 0) {
            this.m = substring.substring(index + 1);
            substring = substring.substring(0, index);
        }
        return substring;
    }
    
    public void run() {
        this.c();
    }
    
    public boolean handleEvent(final Event event) {
        final boolean dx = bm.dX;
        Label_0202: {
            switch (event.id) {
                case 201: {
                    if (this.w == null) {
                        this.dispose();
                        return true;
                    }
                    if (this.D.a() == 0 && this.z.c() == 0) {
                        irc.d = false;
                        this.w.a(e("\u00158({l~") + this.P + "\n");
                        if (!dx) {
                            return true;
                        }
                    }
                    irc.f = false;
                    this.e.a(false);
                    this.hide();
                    return true;
                }
                case 401: {
                    switch (event.key) {
                        case 9: {
                            if ((event.modifiers & 0x1) != 0x0) {
                                this.a(event);
                                if (!dx) {
                                    return true;
                                }
                            }
                            this.b(event);
                            return true;
                        }
                        case 10: {
                            if (event.target == this.q) {
                                this.b();
                                return true;
                            }
                            break Label_0202;
                        }
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    void d(final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.U[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.T.length) {
                    continue;
                }
                break;
            }
            this.U[n].setState(true);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    void g() {
        this.J.remove(this.bC);
        this.J.remove(this.bD);
        this.J.remove(this.bE);
        this.J.remove(this.bF);
        if (this.bG != null) {
            this.J.remove(this.bG);
        }
        if (this.bH != null) {
            this.J.remove(this.bH);
        }
        this.J.remove(this.bI);
        this.J.remove(this.bJ);
    }
    
    private static String e(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'D';
                    break;
                }
                case 1: {
                    c2 = 'm';
                    break;
                }
                case 2: {
                    c2 = 'a';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = 'L';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
