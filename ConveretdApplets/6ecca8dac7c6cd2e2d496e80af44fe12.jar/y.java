import java.util.GregorianCalendar;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.KeyListener;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;
import java.util.Date;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import netscape.security.PrivilegeManager;
import java.awt.MenuComponent;
import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.awt.Event;
import java.awt.Menu;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.CheckboxMenuItem;
import java.awt.PopupMenu;
import java.awt.MenuBar;
import java.io.FileWriter;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class y extends Panel implements ActionListener, ItemListener, WindowListener
{
    final int a = 100;
    final int b = 50;
    final int c = 30;
    v d;
    bb e;
    String f;
    String g;
    String h;
    boolean i;
    FileWriter j;
    bf k;
    p l;
    boolean m;
    bu n;
    br o;
    bq p;
    i q;
    be r;
    bc s;
    n t;
    j u;
    bk v;
    boolean w;
    bd[] x;
    f y;
    bl z;
    MenuBar A;
    PopupMenu B;
    m C;
    CheckboxMenuItem[] D;
    String[] E;
    boolean F;
    String[] G;
    CheckboxMenuItem[] H;
    String[] I;
    CheckboxMenuItem[] J;
    String K;
    String L;
    String M;
    int N;
    String[] O;
    String[] P;
    String[] Q;
    MenuItem[] R;
    MenuItem[] S;
    j T;
    j U;
    b V;
    Frame W;
    q X;
    z Y;
    a Z;
    Menu ba;
    Menu bb;
    Menu bc;
    Menu bd;
    Menu be;
    Menu bf;
    Menu bg;
    Menu bh;
    Menu bi;
    Menu bj;
    Menu bk;
    Menu bl;
    Menu bm;
    Menu bn;
    MenuItem bo;
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.W.setCursor(0);
        return false;
    }
    
    void a() {
        this.A.add(this.ba);
        this.A.add(this.bb);
        this.A.add(this.bc);
        this.A.add(this.bd);
        if (this.be != null) {
            this.A.add(this.be);
        }
        if (this.bf != null) {
            this.A.add(this.bf);
        }
        this.A.add(this.bg);
        this.A.add(this.bh);
    }
    
    void a(final char c) {
        switch (c) {
            case '+': {
                if (this.N != this.G.length - 1) {
                    this.e(++this.N);
                    this.L = this.G[this.N];
                    this.c();
                    return;
                }
                break;
            }
            case '-': {
                if (this.N != 0) {
                    this.e(--this.N);
                    this.L = this.G[this.N];
                    this.c();
                    return;
                }
                break;
            }
        }
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
                    this.D[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.E.length) {
                    continue;
                }
                break;
            }
            this.D[n].setState(true);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        final boolean dx = bm.dX;
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = 0;
        while (true) {
            while (true) {
                Label_0090: {
                    if (!dx) {
                        break Label_0090;
                    }
                    if (this.D[n4].getState()) {
                        n = n4;
                    }
                    Label_0087: {
                        if (this.D[n4] == itemEvent.getSource()) {
                            this.a(n4);
                            this.K = this.E[n4];
                            this.c();
                            if (!dx) {
                                break Label_0087;
                            }
                        }
                        this.D[n4].setState(false);
                    }
                    ++n4;
                }
                if (n4 != this.E.length) {
                    continue;
                }
                break;
            }
            n4 = 0;
            if (dx) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0183: {
                    if (!dx) {
                        break Label_0183;
                    }
                    if (this.J[n4].getState()) {
                        n2 = n4;
                    }
                    Label_0180: {
                        if (this.J[n4] == itemEvent.getSource()) {
                            this.c(n4);
                            this.M = this.I[n4];
                            this.c();
                            if (!dx) {
                                break Label_0180;
                            }
                        }
                        this.J[n4].setState(false);
                    }
                    ++n4;
                }
                if (n4 != this.I.length) {
                    continue;
                }
                break;
            }
            n4 = 0;
            if (dx) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_0277: {
                    if (!dx) {
                        break Label_0277;
                    }
                    this.H[n4].getState();
                    final int n5;
                    if (n5 != 0) {
                        n3 = n4;
                    }
                    Label_0274: {
                        if (this.H[n4] == itemEvent.getSource()) {
                            this.e(n4);
                            this.L = this.G[n4];
                            this.c();
                            if (!dx) {
                                break Label_0274;
                            }
                        }
                        this.H[n4].setState(false);
                    }
                    ++n4;
                }
                if (n4 != this.G.length) {
                    continue;
                }
                break;
            }
            final int n5 = n;
            if (!dx) {
                if (n5 >= 0) {
                    this.D[n].setState(true);
                }
                if (n2 >= 0) {
                    this.J[n2].setState(true);
                }
                if (n3 >= 0) {
                    this.H[n3].setState(true);
                }
                return;
            }
            continue;
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.g();
    }
    
    void b() {
        if (irc.bb) {
            this.B.add("-");
            this.B.add(this.bo);
        }
    }
    
    void c() {
        final boolean dx = bm.dX;
        int n = 0;
        Label_0059: {
            if (this.M.equals(bm.R)) {
                n = 0;
                if (!dx) {
                    break Label_0059;
                }
            }
            if (this.M.equals(bm.T)) {
                n = 2;
                if (!dx) {
                    break Label_0059;
                }
            }
            if (this.M.equals(bm.S)) {
                n = 1;
            }
        }
        final Font font = new Font(this.K, n, Integer.parseInt(this.L));
        this.d.a(font, irc.cb);
        this.e.setFont(font);
        this.X.setFont(font);
        this.a(font);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean dx = bm.dX;
        if (actionEvent.getSource() instanceof TextField && this.m) {
            final String a = this.e.a();
            this.k.a(this.z.a(a, this.d, null, this.f));
            if (a.length() != 0 && a.charAt(0) != '/') {
                this.a("<" + this.n.a + c("\u0013\u0004") + a, bn.c, true);
            }
            this.Y.a();
            return;
        }
        if (actionEvent.getSource() instanceof MenuItem) {
            if (this.O != null) {
                int n = 0;
                while (true) {
                    Label_0316: {
                        if (!dx) {
                            break Label_0316;
                        }
                        if (actionEvent.getSource() == this.R[n]) {
                            String string = this.O[n];
                            if (this.P[n] != null) {
                                string = string + this.f + this.P[n];
                            }
                            this.a(c("\u0007\u0004") + this.n.a() + " " + string, bn.p, true);
                            this.k.a(c("}v\u0012\u001d8~c{") + this.f + c("\r\u001eZ\n6ym\u0014\u0005U") + string + c(",\u0004Q"));
                            return;
                        }
                        ++n;
                    }
                    if (n != this.O.length) {
                        continue;
                    }
                    break;
                }
            }
            final String actionCommand = actionEvent.getActionCommand();
            if (actionCommand.equals(bm.X)) {
                if (this.l.f() == 1) {
                    irc.d = false;
                    this.k.a(c("|q\u0012\u001fU\u0017") + this.o.I + "\n");
                    return;
                }
                this.W.dispose();
                this.m = false;
            }
            else {
                if (actionCommand.equals(bm.D)) {
                    irc.d = false;
                    this.k.a(c("|q\u0012\u001fU\u0017") + this.o.I + "\n");
                    return;
                }
                if (actionCommand.equals(bm.E)) {
                    this.u.a(bm.cS);
                    this.u.show();
                    return;
                }
                if (actionCommand.equals(bm.F)) {
                    this.y.c();
                    return;
                }
                if (actionCommand.equals(bm.G)) {
                    this.q.b();
                    return;
                }
                if (actionCommand.equals(bm.H)) {
                    this.v.a();
                    return;
                }
                if (actionCommand.equals(bm.I)) {
                    if (irc.m < 0) {
                        this.k.a(c("am\b\u001f\u007f"));
                        return;
                    }
                    this.k.a(c("am\b\u001fU\u0013") + irc.m + "\n");
                }
                else {
                    if (actionCommand.equals(bm.J)) {
                        this.t.show();
                        return;
                    }
                    if (actionCommand.equals(bm.K)) {
                        this.k.a(c("ls\u001a\u0012\u007f"));
                        this.t.a();
                        return;
                    }
                    if (actionCommand.equals(bm.L)) {
                        this.d.a();
                        return;
                    }
                    if (actionCommand.equals(bm.M)) {
                        this.l.show();
                        irc.f = true;
                        this.l.e.a(true);
                        return;
                    }
                    if (actionCommand.equals(bm.bb)) {
                        this.k.a(c("zl\u0014\u0002&\r") + this.f + "\n");
                    }
                    if (actionCommand.equals(bm.bc) && this.f != null) {
                        this.k();
                        return;
                    }
                    if (actionCommand.equals(bm.Y)) {
                        this.d.a(irc.R + this.v.c(this.f), bn.e, false);
                        return;
                    }
                    if (actionCommand.equals(bm.Z)) {
                        this.d.a(irc.R + this.v.d(this.f), bn.e, false);
                        return;
                    }
                    if (this.Q != null) {
                        int n2 = 0;
                        while (true) {
                            Label_0966: {
                                if (!dx) {
                                    break Label_0966;
                                }
                                if (actionEvent.getSource() == this.S[n2]) {
                                    this.k.a(c("gk\u0012\u0005U") + this.Q[n2] + "\n");
                                    return;
                                }
                                ++n2;
                            }
                            if (n2 != this.Q.length) {
                                continue;
                            }
                            break;
                        }
                    }
                    if (actionCommand.equals(bm.be)) {
                        this.U.show();
                        return;
                    }
                    if (actionCommand.equals(bm.bd)) {
                        this.T.show();
                        return;
                    }
                    if (actionCommand.equals(bm.V)) {
                        this.C.show();
                        return;
                    }
                    if (actionCommand.equals(bm.U)) {
                        this.o.m.showDocument(this.o.n, c("rF7*\u001bF"));
                        return;
                    }
                    if (actionCommand.equals(bm.bg) || actionCommand.equals(bm.bh) || actionCommand.equals(bm.bi) || actionCommand.equals(bm.bj) || actionCommand.equals(bm.bk) || actionCommand.equals(bm.bl)) {
                        this.k.a(c("}v\u0012\u001d8~c{") + this.f + c("\r\u001eZ") + actionCommand.toUpperCase() + c(",."));
                        return;
                    }
                    if (actionCommand.equals(c("cA/\u0006\u0010HP2%\u0012")) && this.h != null) {
                        this.k.a(c("}v\u0012\u001d8~c{") + this.f + c("\r\u001eZ\u00050yi\u001e\u000e!dj\u001cJ\u007f"));
                    }
                }
            }
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void b(final int n) {
        this.Y.b(n);
    }
    
    String d() {
        return this.h;
    }
    
    void a(final String s) {
        if (this.h == null) {
            this.b();
        }
        this.h = s.substring(s.indexOf(64) + 1);
        String title = null;
        Label_0127: {
            if (irc.bz) {
                title = this.f + c("\r\f") + s + ")" + bm.cE + this.g;
                if (!bm.dX) {
                    break Label_0127;
                }
            }
            title = this.f + " " + bm.cE + this.g;
        }
        this.W.setTitle(title);
        this.F = true;
    }
    
    public void a(final Panel panel, final int n, final int n2) {
        this.B.show(panel, n, n2);
    }
    
    boolean e() {
        return this.m;
    }
    
    void c(final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.J[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.I.length) {
                    continue;
                }
                break;
            }
            this.J[n].setState(true);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    void f() {
        this.A.remove(this.ba);
        this.A.remove(this.bb);
        this.A.remove(this.bc);
        this.A.remove(this.bd);
        if (this.be != null) {
            this.A.remove(this.be);
        }
        if (this.bf != null) {
            this.A.remove(this.bf);
        }
        this.A.remove(this.bg);
        this.A.remove(this.bh);
    }
    
    void g() {
        this.a(false);
        if (this.l.f() == 1) {
            irc.d = false;
            this.k.a(c("|q\u0012\u001fU\u0017") + this.o.I + "\n");
            return;
        }
        if (this.w && irc.e) {
            this.V.a();
        }
        this.W.dispose();
        irc.cy.e(this.f);
        this.m = false;
    }
    
    void a(final boolean b) {
        try {
            if (b) {
                try {
                    if (System.getProperty(c("GE-*[[A5/\u001a_")).startsWith(c("cA/8\u0016LT>"))) {
                        PrivilegeManager.enablePrivilege(c("xJ2=\u0010_W:'3DH>\u001c\u0007DP>"));
                    }
                }
                catch (Exception ex) {}
                try {
                    if (System.getProperty(c("GE-*[[A5/\u001a_")).startsWith(c("`M89\u001a^K=?"))) {
                        PolicyEngine.assertPermission(PermissionID.SYSTEM);
                    }
                }
                catch (Exception ex2) {}
                this.j = new FileWriter(irc.cf + "/" + this.f + c("\u0003H4,"), true);
                this.i = b;
                this.d.a(irc.R + bm.dL, bn.e, false);
                this.j.write(bm.dP + new Date().toString() + c(" ."));
                return;
            }
            this.i = b;
            if (this.j != null) {
                this.j.write(bm.dQ + new Date().toString() + c(" ."));
                this.j.close();
                this.d.a(irc.R + bm.dM, bn.e, false);
            }
        }
        catch (IOException ex3) {
            this.d.a(irc.R + bm.dO, bn.e, false);
        }
    }
    
    v h() {
        return this.d;
    }
    
    boolean i() {
        return this.i;
    }
    
    y(final String f, final String g, final p l, final bf k, final bu n, final br o, final bq p17, final be r, final bc s, final n t, final j u, final bk v, final boolean w, final bd[] x, final String[] o2, final String[] p18, final String[] q) {
        this.e = new bb(50, 30);
        this.h = null;
        this.i = false;
        this.m = false;
        this.A = new MenuBar();
        this.B = new PopupMenu();
        this.C = new m();
        this.G = new String[] { c("\u001c\u0014"), c("\u001c\u0016"), c("\u001c\u0010"), c("\u001c\u0012"), c("\u001c\u001c"), c("\u001f\u0014") };
        this.H = new CheckboxMenuItem[this.G.length];
        this.I = new String[] { bm.R, bm.S, bm.T };
        this.J = new CheckboxMenuItem[this.I.length];
        this.U = new j(bm.dj, bm.dk, c("gk\u0012\u0005U"), false);
        this.V = null;
        this.W = new Frame();
        this.X = new q();
        this.be = null;
        this.bf = null;
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.n = n;
        this.o = o;
        this.p = p17;
        this.r = r;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.l = l;
        this.w = w;
        this.x = x;
        this.O = o2;
        this.P = p18;
        this.Q = q;
        (this.bo = new MenuItem(c("cA/\u0006\u0010HP2%\u0012"))).addActionListener(this);
        if (irc.F != null) {
            this.X.a(irc.F);
        }
        this.Y = new z(this.e, q, k);
        if (o2 != null) {
            this.R = new MenuItem[o2.length];
        }
        this.y = new f(new Frame(), true, s);
        this.q = new i(new Frame(), true, p17);
        this.f = f;
        this.g = g;
        this.W.setTitle(f + bm.cE + g);
        this.X.setText(bm.cD + f + bm.cE + g);
        this.U.a(k);
        this.F = false;
        this.d = new v(100, o.m, irc.cx, this, o, null);
        final Panel panel = new Panel();
        this.setLayout(new BorderLayout());
        Label_0765: {
            if (w && irc.e) {
                this.V = new b(x, o.r, this.W);
                this.setLayout(gridBagLayout);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagLayout.setConstraints(this.V, gridBagConstraints);
                this.add(this.V);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 50.0;
                gridBagLayout.setConstraints(panel, gridBagConstraints);
                this.add(panel);
                if (!bm.dX) {
                    break Label_0765;
                }
            }
            this.add(c("nA5?\u0010_"), panel);
        }
        panel.setLayout(gridBagLayout);
        int gridy = 0;
        final Panel panel2 = new Panel();
        this.Z = null;
        if (!irc.e) {
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            panel2.setLayout(layout);
            gridBagConstraints2.fill = 1;
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.weightx = 99.0;
            layout.setConstraints(this.X, gridBagConstraints2);
            panel2.add(this.X);
            this.Z = new a(this.f, o, null);
            gridBagConstraints2.gridx = 1;
            gridBagConstraints2.weightx = 1.0;
            layout.setConstraints(this.Z, gridBagConstraints2);
            panel2.add(this.Z);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 0.5;
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            panel.add(panel2);
            gridy = 1;
        }
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 30.0;
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        panel.add(this.d);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = gridy + 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        panel.add(this.e);
        this.e.addActionListener(this);
        this.e.addKeyListener(this.e);
        if (irc.by) {
            this.e.setEnabled(false);
        }
        if (irc.bt) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = gridy + 2;
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridheight = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagLayout.setConstraints(this.Y, gridBagConstraints);
            panel.add(this.Y);
        }
        if (this.Z != null) {
            if (o.z != null) {
                this.Z.setBackground(o.z);
            }
            if (o.A != null) {
                this.Z.setForeground(o.A);
            }
        }
        if (o.x != null && this.X != null) {
            this.X.setBackground(o.x);
        }
        if (o.y != null && this.X != null) {
            this.X.setForeground(o.y);
        }
        if (o.s != null) {
            this.d.c(o.s);
            panel.setBackground(o.s);
            panel2.setBackground(o.s);
        }
        if (o.u != null) {
            this.d.a(o.u);
        }
        if (o.t != null) {
            this.d.b(o.t);
        }
        if (o.w != null) {
            this.e.setForeground(o.w);
        }
        if (o.v != null) {
            this.e.setBackground(o.v);
        }
        if (o.B != null) {
            this.Y.setBackground(o.B);
        }
        if (o.E != null) {
            this.Y.b.setBackground(o.E);
            this.Y.c.setBackground(o.E);
            this.Y.f.setBackground(o.E);
        }
        if (o.F != null) {
            this.Y.b.setForeground(o.F);
            this.Y.c.setForeground(o.F);
            this.Y.f.setForeground(o.F);
        }
        if (o.C != null) {
            this.Y.d.setBackground(o.C);
            this.Y.e.setBackground(o.C);
        }
        if (o.D != null) {
            this.Y.d.setForeground(o.D);
            this.Y.e.setForeground(o.D);
        }
        this.k = k;
        this.z = new bl(n, o, p17, r, s, t, v, o.I);
        this.j();
        (this.T = new j(bm.cU, bm.cV, c("}v\u0012\u001d8~c{") + f + c("\r\u001eZ\n6ym\u0014\u0005U"), true)).a(k);
        this.T.a(this.d, n);
        this.e.requestFocus();
        this.d.size();
        this.m = true;
        if (irc.e) {
            this.l();
            return;
        }
        irc.cy.a(this.f, panel, this.e);
        panel.validate();
        if (irc.cy.c(this.f) < 0) {
            this.l();
            return;
        }
        if (irc.p) {
            panel.add(this.B);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    void a(final Font font) {
        bo.a(this.ba, font);
        bo.a(this.bb, font);
        bo.a(this.bc, font);
        bo.a(this.bd, font);
        bo.a(this.bg, font);
        bo.a(this.bh, font);
        if (this.be != null) {
            bo.a(this.be, font);
        }
        if (this.bf != null) {
            bo.a(this.bf, font);
        }
        bo.a(this.bk, font);
        bo.a(this.bj, font);
        bo.a(this.bi, font);
        bo.a(this.bl, font);
        bo.a(this.bm, font);
        bo.a(this.bn, font);
        this.f();
        this.a();
    }
    
    public void d(final int n) {
        this.Y.a(n);
    }
    
    void j() {
        final boolean dx = bm.dX;
        this.ba = new Menu(bm.p);
        this.bb = new Menu(bm.r);
        this.bi = new Menu(bm.s);
        this.bc = new Menu(bm.u);
        this.bd = new Menu(bm.w);
        this.bg = new Menu(bm.v);
        this.bh = new Menu(bm.x);
        this.bk = new Menu(bm.y);
        this.A.add(this.ba);
        if (irc.q) {
            this.A.add(this.bb);
        }
        this.A.add(this.bc);
        if (irc.q) {
            this.A.add(this.bd);
        }
        final MenuItem menuItem;
        this.ba.add(menuItem = new MenuItem(bm.X));
        menuItem.addActionListener(this);
        this.ba.add(new MenuItem("-"));
        final MenuItem menuItem2;
        this.ba.add(menuItem2 = new MenuItem(bm.D));
        menuItem2.addActionListener(this);
        if (irc.U) {
            final MenuItem menuItem3;
            this.bb.add(menuItem3 = new MenuItem(bm.E));
            menuItem3.addActionListener(this);
        }
        final MenuItem menuItem4;
        this.bb.add(menuItem4 = new MenuItem(bm.F));
        menuItem4.addActionListener(this);
        final MenuItem menuItem5;
        this.bb.add(menuItem5 = new MenuItem(bm.G));
        menuItem5.addActionListener(this);
        final MenuItem menuItem6;
        this.bb.add(menuItem6 = new MenuItem(bm.H));
        menuItem6.addActionListener(this);
        if (irc.n && irc.bB) {
            final MenuItem menuItem7;
            this.bb.add(menuItem7 = new MenuItem(bm.I));
            menuItem7.addActionListener(this);
        }
        this.bb.add(new MenuItem("-"));
        this.bb.add(this.bi);
        final MenuItem menuItem8;
        this.bi.add(menuItem8 = new MenuItem(bm.J));
        menuItem8.addActionListener(this);
        final MenuItem menuItem9;
        this.bi.add(menuItem9 = new MenuItem(bm.K));
        menuItem9.addActionListener(this);
        final MenuItem menuItem10;
        this.bc.add(menuItem10 = new MenuItem(bm.L));
        menuItem10.addActionListener(this);
        final MenuItem menuItem11;
        this.bc.add(menuItem11 = new MenuItem(bm.M));
        menuItem11.addActionListener(this);
        final MenuItem menuItem12;
        this.bd.add(menuItem12 = new MenuItem(bm.bb));
        menuItem12.addActionListener(this);
        this.B.add(menuItem12);
        if (irc.ch != null) {
            final MenuItem menuItem13;
            this.bd.add(menuItem13 = new MenuItem(bm.bc));
            menuItem13.addActionListener(this);
            this.B.add(menuItem13);
        }
        this.bj = new Menu(bm.z);
        this.bd.add(this.bj);
        final MenuItem menuItem14;
        this.bj.add(menuItem14 = new MenuItem(bm.Y));
        menuItem14.addActionListener(this);
        final MenuItem menuItem15;
        this.bj.add(menuItem15 = new MenuItem(bm.Z));
        menuItem15.addActionListener(this);
        this.B.add(this.bj);
        this.bd.add(this.bk);
        final MenuItem menuItem16;
        this.bk.add(menuItem16 = new MenuItem(bm.bg));
        menuItem16.addActionListener(this);
        final MenuItem menuItem17;
        this.bk.add(menuItem17 = new MenuItem(bm.bh));
        menuItem17.addActionListener(this);
        final MenuItem menuItem18;
        this.bk.add(menuItem18 = new MenuItem(bm.bi));
        menuItem18.addActionListener(this);
        final MenuItem menuItem19;
        this.bk.add(menuItem19 = new MenuItem(bm.bj));
        menuItem19.addActionListener(this);
        final MenuItem menuItem20;
        this.bk.add(menuItem20 = new MenuItem(bm.bk));
        menuItem20.addActionListener(this);
        final MenuItem menuItem21;
        this.bk.add(menuItem21 = new MenuItem(bm.bl));
        menuItem21.addActionListener(this);
        if (this.O != null) {
            this.be = new Menu(bm.A);
            this.A.add(this.be);
            final MenuItem menuItem22;
            this.be.add(menuItem22 = new MenuItem(bm.bd));
            menuItem22.addActionListener(this);
            this.be.add(new MenuItem("-"));
            int n = 0;
            while (true) {
                Label_1017: {
                    if (!dx) {
                        break Label_1017;
                    }
                    String string = this.O[n];
                    if (this.P[n] != null) {
                        string = string + c("\r\u00185\"\u0016F\u001a{") + this.P[n];
                    }
                    this.R[n] = new MenuItem(string);
                    this.be.add(this.R[n]);
                    this.R[n].addActionListener(this);
                    ++n;
                }
                if (n != this.O.length) {
                    continue;
                }
                break;
            }
        }
        int n2;
        while (true) {
            Label_1175: {
                if (this.Q == null) {
                    break Label_1175;
                }
                this.bf = new Menu(bm.di);
                this.A.add(this.bf);
                final MenuItem menuItem23;
                this.bf.add(menuItem23 = new MenuItem(bm.be));
                menuItem23.addActionListener(this);
                this.bf.add(new MenuItem("-"));
                this.S = new MenuItem[this.Q.length];
                n2 = 0;
                while (true) {
                    Label_1166: {
                        if (!dx) {
                            break Label_1166;
                        }
                        this.S[n2] = new MenuItem(this.Q[n2]);
                        this.bf.add(this.S[n2]);
                        this.S[n2].addActionListener(this);
                        ++n2;
                    }
                    if (n2 != this.Q.length) {
                        continue;
                    }
                    break;
                }
            }
            this.A.add(this.bg);
            this.A.add(this.bh);
            this.A.setHelpMenu(this.bh);
            this.bl = new Menu(bm.O);
            this.bm = new Menu(bm.P);
            this.bn = new Menu(bm.Q);
            this.bg.add(this.bl);
            this.bg.add(this.bm);
            this.bg.add(this.bn);
            this.E = this.d.b();
            this.D = new CheckboxMenuItem[this.E.length];
            n2 = 0;
            if (dx) {
                continue;
            }
            break;
        }
    Label_1366_Outer:
        while (true) {
            while (true) {
                Label_1369: {
                    if (!dx) {
                        break Label_1369;
                    }
                    this.D[n2] = new CheckboxMenuItem(this.E[n2]);
                    this.bl.add(this.D[n2]);
                    this.D[n2].addItemListener(this);
                    ++n2;
                }
                if (n2 == this.E.length) {
                    n2 = 0;
                    if (dx) {
                        if (dx) {
                            continue;
                        }
                    }
                    while (true) {
                        if (n2 == this.G.length) {
                            n2 = 0;
                            if (!dx) {
                                break;
                            }
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            this.H[n2] = new CheckboxMenuItem(this.G[n2]);
                            this.bm.add(this.H[n2]);
                            this.H[n2].addItemListener(this);
                        }
                        ++n2;
                    }
                    while (true) {
                        y y;
                        if (n2 == this.I.length) {
                            this.K = this.E[irc.bZ];
                            this.L = this.G[irc.bY];
                            this.M = this.I[irc.ca];
                            this.a(irc.bZ);
                            this.c(irc.ca);
                            this.e(irc.bY);
                            this.c();
                            y = this;
                            if (!dx) {
                                break;
                            }
                        }
                        else {
                            this.J[n2] = new CheckboxMenuItem(this.I[n2]);
                            this.bn.add(this.J[n2]);
                            y = this;
                        }
                        y.J[n2].addItemListener(this);
                        ++n2;
                    }
                    if (this.o.n != null) {
                        final MenuItem menuItem24;
                        this.bh.add(menuItem24 = new MenuItem(bm.U));
                        menuItem24.addActionListener(this);
                        this.bh.add(new MenuItem("-"));
                    }
                    final MenuItem menuItem25;
                    this.bh.add(menuItem25 = new MenuItem(bm.V));
                    menuItem25.addActionListener(this);
                    if (irc.cS) {
                        bm.dX = !dx;
                    }
                    return;
                }
                break;
            }
            continue Label_1366_Outer;
        }
    }
    
    public void b(final String s) {
        int n = 0;
        while (true) {
            Label_0039: {
                if (!bm.dX) {
                    break Label_0039;
                }
                if (s.equalsIgnoreCase(this.E[n])) {
                    this.K = this.E[n];
                    this.c();
                    return;
                }
                ++n;
            }
            if (n == this.E.length) {
                return;
            }
            continue;
        }
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    void e(final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.H[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.G.length) {
                    continue;
                }
                break;
            }
            this.H[n].setState(true);
            this.N = n;
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    public void k() {
        if (irc.ch != null) {
            try {
                int index = irc.ch.indexOf(63);
                if (index++ <= 0) {
                    return;
                }
                if (irc.ch.substring(index).equals(c("CM8 H"))) {
                    this.o.m.showDocument(new URL(irc.ch + this.f + irc.ck), c("}V4-\u001cAA"));
                    return;
                }
                if (irc.ch.substring(index).equals(c("XW>9\u001bLI>v"))) {
                    irc.ci = true;
                    this.k.a(c("zl\u0014\u0002&\r") + this.f + "\n");
                    return;
                }
                if (irc.ch.substring(index).equals(c("DV8%\u0014@Af"))) {
                    irc.cj = true;
                    this.k.a(c("zl\u0014\u0002&\r") + this.f + "\n");
                    return;
                }
                this.o.m.showDocument(new URL(irc.ch + this.f + irc.ck), c("}V4-\u001cAA"));
                return;
            }
            catch (MalformedURLException ex) {
                return;
            }
        }
        this.k.a(c("zl\u0014\u0002&\r") + this.f + "\n");
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.k != null && ((event.modifiers & 0x2) != 0x0 || (event.modifiers & 0x4) != 0x0)) {
            this.k();
        }
        if (this.e != null) {
            this.e.requestFocus();
        }
        return false;
    }
    
    private void l() {
        if (irc.o) {
            this.W.setMenuBar(this.A);
        }
        this.W.add(c("nA5?\u0010_"), this);
        this.W.addWindowListener(this);
        Label_0097: {
            if (irc.bL) {
                this.W.reshape(0, 0, irc.t.width, irc.t.height);
                if (!bm.dX) {
                    break Label_0097;
                }
            }
            irc.a();
            this.W.reshape(irc.r, irc.s, irc.bj, irc.bk);
        }
        if (irc.z != null) {
            this.W.setIconImage(irc.z);
        }
        this.W.show();
    }
    
    synchronized void a(String string, final Color color, final boolean b) {
        if (irc.bD) {
            string = "[" + irc.bO.format(new GregorianCalendar().getTime()) + c("p\u0004") + string;
        }
        this.d.a(string, color, b);
        if (!this.e.b()) {
            this.o.a();
            if (!irc.e && !irc.cy.a().equals(this.f)) {
                irc.cy.a(this.f, bn.t);
            }
        }
        if (this.i) {
            try {
                this.j.write(d.a(string) + c(" ."));
                this.j.flush();
            }
            catch (IOException ex) {
                this.d.a(bm.dO, bn.e, false);
            }
        }
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '-';
                    break;
                }
                case 1: {
                    c2 = '$';
                    break;
                }
                case 2: {
                    c2 = '[';
                    break;
                }
                case 3: {
                    c2 = 'K';
                    break;
                }
                default: {
                    c2 = 'u';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
