import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import netscape.security.PrivilegeManager;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.awt.Color;
import java.awt.MenuComponent;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.util.Date;
import java.net.URL;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.ItemEvent;
import java.util.Vector;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.CheckboxMenuItem;
import java.awt.MenuBar;
import java.awt.Label;
import java.io.FileWriter;
import java.awt.event.WindowListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class t extends Panel implements ActionListener, ItemListener, WindowListener
{
    final int a = 40;
    final int b = 100;
    final int c = 30;
    String d;
    bf e;
    p f;
    boolean g;
    FileWriter h;
    v i;
    v j;
    bb k;
    Label l;
    w m;
    boolean n;
    bl o;
    bu p;
    br q;
    bq r;
    i s;
    be t;
    b u;
    bc v;
    f w;
    n x;
    j y;
    bk z;
    String A;
    String B;
    MenuBar C;
    m D;
    CheckboxMenuItem[] E;
    String[] F;
    String[] G;
    CheckboxMenuItem[] H;
    String[] I;
    CheckboxMenuItem[] J;
    String K;
    String L;
    String M;
    int N;
    boolean O;
    boolean P;
    bd[] Q;
    String[] R;
    String[] S;
    String[] T;
    Menu U;
    MenuItem[] V;
    MenuItem[] W;
    j X;
    j Y;
    Frame Z;
    z ba;
    q bb;
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
    Menu bo;
    MenuItem bp;
    MenuItem bq;
    MenuItem br;
    MenuItem bs;
    MenuItem bt;
    MenuItem bu;
    MenuItem bv;
    MenuItem bw;
    MenuItem bx;
    MenuItem by;
    Vector bz;
    Vector bA;
    a bB;
    Vector bC;
    Vector bD;
    
    void a() {
        if (!irc.q) {
            return;
        }
        this.m.a();
        this.bf.add(this.bp);
        this.bp.addActionListener(this);
        this.bf.add(this.bq);
        this.bq.addActionListener(this);
        if (irc.bI) {
            this.bf.add(this.bx);
            this.bx.addActionListener(this);
            this.bf.add(this.by);
            this.by.addActionListener(this);
        }
        this.b();
    }
    
    void b() {
        if (!irc.q) {
            return;
        }
        this.m.b();
        this.bf.add(this.br);
        this.br.addActionListener(this);
        this.bf.add(this.bs);
        this.bs.addActionListener(this);
        this.bf.add(this.bt);
        this.bt.addActionListener(this);
        this.bf.add(this.bu);
        this.bu.addActionListener(this);
        this.bf.add(this.bv);
        this.bv.addActionListener(this);
        this.bf.add(this.bw);
        this.bw.addActionListener(this);
        bo.a(this.bf, this.bk.getFont());
    }
    
    void c() {
        this.C.add(this.bc);
        this.C.add(this.bd);
        this.C.add(this.be);
        this.C.add(this.bf);
        if (this.bg != null) {
            this.C.add(this.bg);
        }
        this.C.add(this.bh);
        this.C.add(this.bi);
    }
    
    void a(final String s, final String s2) {
        final boolean dx = bm.dX;
        final int index = this.bz.indexOf(s);
        if (index >= 0) {
            String s3 = this.bA.elementAt(index);
            if (s3.equals("")) {
                s3 = s;
            }
            final String substring = s2.substring(0, s2.indexOf(64));
            Label_0281: {
                switch (irc.bM) {
                    case 0: {
                        this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f<~<\r~\u001e") + s2.substring(s2.indexOf(64) + 1) + "\n");
                        if (dx) {
                            break Label_0281;
                        }
                        break;
                    }
                    case 1: {
                        this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f<~") + s + m("\r~\u001et\u001c"));
                        if (dx) {
                            break Label_0281;
                        }
                        break;
                    }
                    case 2: {
                        this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f<~") + s + "!" + substring + m("l~T"));
                        if (dx) {
                            break Label_0281;
                        }
                        break;
                    }
                    case 3: {
                        this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f<~<\r") + substring + m("l~T"));
                        break;
                    }
                }
            }
            this.e.a(m("g\u001d\u001d\u00156") + this.d + " " + s + " " + s3 + "\n");
            this.bz.removeElementAt(index);
            this.bA.removeElementAt(index);
        }
    }
    
    void a(final char c) {
        switch (c) {
            case '+': {
                if (this.N != this.G.length - 1) {
                    this.e(++this.N);
                    this.L = this.G[this.N];
                    this.e();
                    return;
                }
                break;
            }
            case '-': {
                if (this.N != 0) {
                    this.e(--this.N);
                    this.L = this.G[this.N];
                    this.e();
                    return;
                }
                break;
            }
        }
    }
    
    boolean a(final String s) {
        return this.m.e(s) != -1 || this.m.e("@" + s) != -1 || this.m.e("+" + s) != -1 || this.m.e("%" + s) != -1 || (irc.bJ && (this.m.e("*" + s) != -1 || this.m.e("!" + s) != -1));
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
                    if (this.E[n4].getState()) {
                        n = n4;
                    }
                    Label_0087: {
                        if (this.E[n4] == itemEvent.getSource()) {
                            this.a(n4);
                            this.K = this.F[n4];
                            this.e();
                            if (!dx) {
                                break Label_0087;
                            }
                        }
                        this.E[n4].setState(false);
                    }
                    ++n4;
                }
                if (n4 != this.F.length) {
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
                            this.e();
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
                            this.e();
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
                    this.E[n].setState(true);
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
    
    void a(final int n) {
        final boolean dx = bm.dX;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0023: {
                    if (!dx) {
                        break Label_0023;
                    }
                    this.E[n2].setState(false);
                    ++n2;
                }
                if (n2 < this.F.length) {
                    continue;
                }
                break;
            }
            this.E[n].setState(true);
            if (!dx) {
                return;
            }
            continue;
        }
    }
    
    public void d() {
        if (irc.q) {
            this.i.a(irc.R + bm.bW, bn.h, false);
            this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f<T"));
            this.O = true;
        }
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a(false);
    }
    
    void b(final String s, final String s2) {
        this.a("", s, s2);
        this.a("@", s, s2);
        this.a("+", s, s2);
        this.a("%", s, s2);
        this.a("~", s, s2);
        this.a("&", s, s2);
    }
    
    void e() {
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
        this.i.a(font, irc.bX);
        this.j.a(font, irc.bX);
        this.k.setFont(font);
        this.l.setFont(font);
        this.m.a(font);
        this.m.b(font);
        this.bb.setFont(font);
        this.a(font);
    }
    
    void b(final String s) {
        this.bC.addElement(s);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        final boolean dx = bm.dX;
        if (actionEvent.getSource() instanceof TextField && this.n) {
            final String a = this.k.a();
            this.ba.a();
            final int index = a.indexOf(32);
            String trim = null;
            Label_0068: {
                if (index > 0) {
                    trim = a.substring(0, index).trim();
                    if (!dx) {
                        break Label_0068;
                    }
                }
                trim = a;
            }
            if (!trim.toUpperCase().equals(m("\u0003\u0018\u001b\u001f@i")) || irc.bC) {
                this.e.a(this.o.a(a, this.i, this.d, null));
                return;
            }
            if (this.f.f() == 1) {
                irc.d = false;
                this.e.a(m("}\u0001\u0017\n6\u0016") + this.t.o + "\n");
                return;
            }
            this.a(false);
            this.e.a(m("|\u0015\f\n6") + this.d + "\n");
        }
        else {
            if (actionEvent.getSource() != this.m || !this.n) {
                if (actionEvent.getSource() instanceof MenuItem) {
                    final String actionCommand = actionEvent.getActionCommand();
                    String s = this.m.c();
                    if (s != null) {
                        s = this.m.b(s);
                    }
                    if (this.R != null) {
                        int n = 0;
                        while (true) {
                            Label_0546: {
                                if (!dx) {
                                    break Label_0546;
                                }
                                if (actionEvent.getSource() == this.V[n]) {
                                    String string = this.R[n];
                                    Label_0439: {
                                        if (this.S[n] != null) {
                                            if (s != null) {
                                                string = string + s + this.S[n];
                                                if (!dx) {
                                                    break Label_0439;
                                                }
                                            }
                                            return;
                                        }
                                    }
                                    this.a(m("\u0006t") + this.p.a() + " " + string, bn.p, true);
                                    this.e.a(m("|\u0006\u0017\b[\u007f\u0013~") + this.d + m("\fn_\u001fUx\u001d\u0011\u00106") + string + m("-^"));
                                    return;
                                }
                                ++n;
                            }
                            if (n != this.R.length) {
                                continue;
                            }
                            break;
                        }
                    }
                    if (actionCommand.equals(bm.bd)) {
                        this.X.show();
                        return;
                    }
                    if (actionCommand.equals(bm.E)) {
                        this.y.a(bm.cS);
                        this.y.show();
                        return;
                    }
                    if (actionCommand.equals(bm.W)) {
                        this.a(false);
                        this.e.a(m("|\u0015\f\n6") + this.d + "\n");
                        return;
                    }
                    if (actionCommand.equals(bm.D)) {
                        irc.d = false;
                        this.e.a(m("}\u0001\u0017\n6\u0016") + this.t.o + "\n");
                        return;
                    }
                    if (actionCommand.equals(bm.F)) {
                        this.w.c();
                        return;
                    }
                    if (actionCommand.equals(bm.G)) {
                        this.s.b();
                        return;
                    }
                    if (actionCommand.equals(bm.H)) {
                        this.z.a();
                        return;
                    }
                    if (actionCommand.equals(bm.I)) {
                        if (irc.m < 0) {
                            this.e.a(m("`\u001d\r\n\u001c"));
                            return;
                        }
                        this.e.a(m("`\u001d\r\n6\u0012") + irc.m + "\n");
                    }
                    else {
                        if (actionCommand.equals(bm.J)) {
                            this.x.show();
                            return;
                        }
                        if (actionCommand.equals(bm.K)) {
                            this.e.a(m("m\u0003\u001f\u0007\u001c"));
                            this.x.a();
                            return;
                        }
                        if (actionCommand.equals(bm.L)) {
                            this.i.a();
                            return;
                        }
                        if (actionCommand.equals(bm.M)) {
                            this.f.show();
                            irc.f = true;
                            this.f.e.a(true);
                            return;
                        }
                        if (actionCommand.equals(bm.ba) && s != null) {
                            if (this.q.d() < 0) {
                                this.a(irc.R + bm.cF + m("\fuT"), bn.e, false);
                                return;
                            }
                            this.q.c(s, false);
                            irc.cy.b(s);
                        }
                        else {
                            if (actionCommand.equals(bm.bb) && s != null) {
                                this.e.a(m("{\u001c\u0011\u0017E\f") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.bc) && s != null) {
                                try {
                                    int index2 = irc.ch.indexOf(63);
                                    if (index2++ > 0) {
                                        if (irc.ch.substring(index2).equals(m("B==5+"))) {
                                            this.t.m.showDocument(new URL(irc.ch + s + irc.ck), m("|&18\u007f@1"));
                                            return;
                                        }
                                        if (irc.ch.substring(index2).equals(m("Y';,xM9;c"))) {
                                            irc.ci = true;
                                            this.e.a(m("{\u001c\u0011\u0017E\f") + s + "\n");
                                            return;
                                        }
                                        if (irc.ch.substring(index2).equals(m("E&=0wA1c"))) {
                                            irc.cj = true;
                                            this.e.a(m("{\u001c\u0011\u0017E\f") + s + "\n");
                                            return;
                                        }
                                        this.t.m.showDocument(new URL(irc.ch + s + irc.ck), m("|&18\u007f@1"));
                                    }
                                }
                                catch (Exception ex) {}
                                return;
                            }
                            if (actionCommand.equals(bm.Y) && s != null) {
                                this.a(irc.R + this.z.c(s), bn.e, false);
                                return;
                            }
                            if (actionCommand.equals(bm.Z) && s != null) {
                                this.a(irc.R + this.z.d(s), bn.e, false);
                                return;
                            }
                            if (actionCommand.equals(bm.bm) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f1~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.bn) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\fy1~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.dT) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f6~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.dU) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\fy6~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.bo) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\f\u007f(~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.bp) && s != null) {
                                this.e.a(m("a\u001b\u001a\u001b6") + this.d + m("\fy(~") + s + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.bq) && s != null) {
                                this.e.a(m("g\u001d\u001d\u00156") + this.d + " " + s + m("\f6';") + "\n");
                                return;
                            }
                            if (actionCommand.equals(bm.br) && s != null) {
                                final j j = new j(bm.br, bm.cj, m("g\u001d\u001d\u00156") + this.d + " " + s + m("\fn"), false);
                                j.a(this.e);
                                if (this.t.s != null) {
                                    j.setBackground(this.t.s);
                                }
                                j.show();
                                return;
                            }
                            if (actionCommand.equals(bm.dJ) && s != null) {
                                this.e.a(m("y\u0007\u001b\f^c\u0007\n~,") + s + "\n");
                                this.bz.addElement(s);
                                this.bA.addElement("");
                                return;
                            }
                            if (actionCommand.equals(bm.dK) && s != null) {
                                final j i = new j(bm.br, bm.cj, m("y\u0007\u001b\f^c\u0007\n~") + s, false);
                                i.a(this.e);
                                if (this.t.s != null) {
                                    i.setBackground(this.t.s);
                                }
                                i.show();
                                if (!i.a()) {
                                    this.e.a(m("y\u0007\u001b\f^c\u0007\n~,") + s + "\n");
                                    this.bz.addElement(s);
                                    this.bA.addElement(i.b());
                                }
                                return;
                            }
                            if (this.T != null) {
                                int n2 = 0;
                                while (true) {
                                    Label_2348: {
                                        if (!dx) {
                                            break Label_2348;
                                        }
                                        if (actionEvent.getSource() == this.W[n2]) {
                                            if (!irc.bB) {
                                                this.a(irc.R + bm.bY, bn.e, false);
                                                return;
                                            }
                                            this.e.a(m("f\u001b\u0017\u00106") + this.T[n2] + "\n");
                                            return;
                                        }
                                        else {
                                            ++n2;
                                        }
                                    }
                                    if (n2 != this.T.length) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            if (actionCommand.equals(bm.be)) {
                                this.Y.show();
                                return;
                            }
                            if (actionCommand.equals(bm.U)) {
                                this.t.m.showDocument(this.t.n, m("s62?xG"));
                                return;
                            }
                            if (actionCommand.equals(bm.V)) {
                                this.D.show();
                                return;
                            }
                            if (s != null && actionCommand.equals(bm.bg)) {
                                this.e.a(m("|\u0006\u0017\b[\u007f\u0013~") + s + m("\fn_") + actionCommand.toUpperCase() + " " + new Date().getTime() / 1000L + m("-^"));
                                return;
                            }
                            if ((s != null && actionCommand.equals(bm.bh)) || actionCommand.equals(bm.bi) || actionCommand.equals(bm.bj) || actionCommand.equals(bm.bk) || actionCommand.equals(bm.bl)) {
                                this.e.a(m("|\u0006\u0017\b[\u007f\u0013~") + s + m("\fn_") + actionCommand.toUpperCase() + m("-^"));
                            }
                        }
                    }
                }
                return;
            }
            final String b = ((w)actionEvent.getSource()).b(((w)actionEvent.getSource()).c());
            if (this.q.d() < 0) {
                this.i.a(irc.R + bm.cF, bn.e, false);
                return;
            }
            this.q.c(b, false);
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void b(final int n) {
        this.ba.b(n);
    }
    
    public boolean a(final Event event, final int n, final int n2) {
        this.Z.setCursor(0);
        return false;
    }
    
    void f() {
        final boolean dx = bm.dX;
        String s = null;
        Label_0134: {
            if (this.A.equals("") || !irc.bA) {
                if (this.B.equals("")) {
                    s = this.d;
                    if (!dx) {
                        break Label_0134;
                    }
                }
                s = this.d + m("\u0016t") + this.B;
                if (!dx) {
                    break Label_0134;
                }
            }
            s = this.d + m("\f|") + this.A + m("\u0005t") + this.B;
        }
        this.Z.setTitle(s);
        this.bb.setText(s);
    }
    
    void g() {
        if (this.R != null) {
            final MenuItem menuItem;
            this.U.add(menuItem = new MenuItem(bm.bd));
            menuItem.addActionListener(this);
            this.U.add(new MenuItem("-"));
            int n = 0;
            while (true) {
                Label_0149: {
                    if (!bm.dX) {
                        break Label_0149;
                    }
                    String string = this.R[n];
                    if (this.S[n] != null) {
                        string = string + m("\fh07uGj~") + this.S[n];
                    }
                    this.V[n] = new MenuItem(string);
                    this.U.add(this.V[n]);
                    this.V[n].addActionListener(this);
                    ++n;
                }
                if (n != this.R.length) {
                    continue;
                }
                break;
            }
        }
    }
    
    boolean isChannelOp() {
        return this.isChannelOp(this.p.a());
    }
    
    boolean isChannelOp(final String s) {
        return this.m.e("@" + s) >= 0 || this.m.e(m("\u0007\u0014") + s) >= 0;
    }
    
    boolean h() {
        return this.d(this.p.a());
    }
    
    void c(final String s) {
        this.bD.removeElement(s);
    }
    
    boolean d(final String s) {
        return this.bD.indexOf(s) >= 0;
    }
    
    void a(final GridBagConstraints gridBagConstraints, final int gridx, final int gridy, final int gridwidth, final int gridheight, final double weightx, final double weighty, final int anchor, final int fill, final Insets insets, final int ipadx, final int ipady) {
        gridBagConstraints.fill = fill;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.gridwidth = gridwidth;
        gridBagConstraints.gridheight = gridheight;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = insets;
        gridBagConstraints.ipadx = ipadx;
        gridBagConstraints.ipady = ipady;
    }
    
    boolean i() {
        return this.n;
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
    
    void j() {
        this.l.setText(" " + Integer.toString(this.m.d()) + bm.ci);
    }
    
    void k() {
        this.C.remove(this.bc);
        this.C.remove(this.bd);
        this.C.remove(this.be);
        this.C.remove(this.bf);
        if (this.bg != null) {
            this.C.remove(this.bg);
        }
        this.C.remove(this.bh);
        this.C.remove(this.bi);
    }
    
    void e(final String s) {
        this.bD.addElement(s);
    }
    
    boolean l() {
        return this.O;
    }
    
    synchronized void a(String string, Color w, final boolean b) {
        Label_0064: {
            if (!this.k.b() && !irc.e) {
                if (irc.cy.a().equals(this.d)) {
                    break Label_0064;
                }
                irc.cy.a(this.d, bn.t);
                if (!bm.dX) {
                    break Label_0064;
                }
            }
            irc.cy.a(this.d, bn.r);
        }
        int channelOp = 0;
        if (d.a(string).charAt(0) == '<') {
            final int index = string.indexOf(62);
            if (index > 0) {
                channelOp = (this.isChannelOp(string.substring(string.indexOf(60) + 1, index)) ? 1 : 0);
            }
        }
        if (irc.bD) {
            string = "[" + irc.bO.format(new GregorianCalendar().getTime()) + m("qt") + string;
        }
        if (channelOp == 1) {
            if (bn.w != null) {
                w = bn.w;
            }
            if (irc.bH) {
                this.j.a(string, w, b);
            }
        }
        this.i.a(string, w, b);
        if (this.g) {
            try {
                this.h.write(d.a(string) + m("!^"));
                this.h.flush();
            }
            catch (IOException ex) {
                this.i.a(bm.dO, bn.e, b);
            }
        }
    }
    
    void a(final boolean b) {
        if (irc.bC && !b) {
            return;
        }
        this.c(false);
        if (this.P && irc.e) {
            this.u.a();
        }
        this.Z.dispose();
        irc.cy.e(this.d);
        Label_0161: {
            if (this.f.f() == 1) {
                irc.d = false;
                if (!p.d) {
                    break Label_0161;
                }
                this.e.a(m("}\u0001\u0017\n6\u0016") + this.t.o + "\n");
                if (!bm.dX) {
                    break Label_0161;
                }
            }
            this.e.a(m("|\u0015\f\n6") + this.d + "\n");
        }
        this.n = false;
    }
    
    void b(final boolean o) {
        this.O = o;
    }
    
    void f(final String s) {
        this.B = d.a(s);
        this.f();
    }
    
    void c(final boolean b) {
        try {
            if (b) {
                try {
                    if (System.getProperty(m("F5(?8Z10:y^")).startsWith(m("b1*-uM$;"))) {
                        PrivilegeManager.enablePrivilege(m("y:7(s^'?2PE8;\tdE ;"));
                    }
                }
                catch (Exception ex) {}
                try {
                    if (System.getProperty(m("F5(?8Z10:y^")).startsWith(m("a==,y_;8*"))) {
                        PolicyEngine.assertPermission(PermissionID.SYSTEM);
                    }
                }
                catch (Exception ex2) {}
                this.h = new FileWriter(irc.cf + "/" + this.d + m("\u0002819"), true);
                this.g = b;
                this.i.a(irc.R + bm.dL, bn.e, false);
                this.h.write(bm.dP + new Date().toString() + m("!^"));
                return;
            }
            this.g = b;
            if (this.h != null) {
                this.h.write(bm.dQ + new Date().toString() + m("!^"));
                this.h.close();
                this.i.a(irc.R + bm.dM, bn.e, false);
            }
        }
        catch (IOException ex3) {
            this.i.a(irc.R + bm.dO, bn.e, false);
        }
    }
    
    boolean m() {
        return this.g;
    }
    
    void g(final String s) {
        this.bC.removeElement(s);
    }
    
    boolean h(final String s) {
        return this.bC.indexOf(s) >= 0;
    }
    
    t(final String s, final p f, final bf e, final bu p16, final br q, final bq r, final be t, final bc v, final n x, final j y, final bk z, final boolean p17, final bd[] q2, final String[] r2, final String[] s2, final String[] t2) {
        final boolean dx = bm.dX;
        this.g = false;
        this.k = new bb(40, 30);
        this.l = new Label();
        this.n = false;
        this.A = "";
        this.B = "";
        this.C = new MenuBar();
        this.D = new m();
        this.G = new String[] { m("\u001dd"), m("\u001df"), m("\u001d`"), m("\u001db"), m("\u001dl"), m("\u001ed") };
        this.H = new CheckboxMenuItem[this.G.length];
        this.I = new String[] { bm.R, bm.S, bm.T };
        this.J = new CheckboxMenuItem[this.I.length];
        this.O = false;
        this.U = null;
        this.Y = new j(bm.dj, bm.dk, m("f\u001b\u0017\u00106"), false);
        this.Z = new Frame();
        this.bb = new q();
        this.bg = null;
        this.bz = new Vector();
        this.bA = new Vector();
        this.bB = null;
        this.bC = new Vector();
        this.bD = new Vector();
        this.p = p16;
        this.q = q;
        this.r = r;
        this.t = t;
        this.f = f;
        this.v = v;
        this.x = x;
        this.y = y;
        this.z = z;
        this.R = r2;
        this.S = s2;
        this.T = t2;
        this.ba = new z(this.k, t2, e);
        if (r2 != null) {
            this.V = new MenuItem[r2.length];
        }
        this.w = new f(new Frame(), true, v);
        this.s = new i(new Frame(), true, r);
        this.o = new bl(p16, q, r, t, v, x, z, t.o);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.d = s;
        this.e = e;
        this.P = p17;
        this.Q = q2;
        this.Z.setTitle(s);
        this.Y.a(e);
        this.setLayout(gridBagLayout);
        this.m = new w(10, false, this, p16, z, r);
        this.i = new v(100, t.m, irc.cw, null, q, this.m);
        this.j = new v(100, t.m, irc.cw, null, q, this.m);
        (this.X = new j(bm.cU, bm.cV, m("|\u0006\u0017\b[\u007f\u0013~") + s + m("\fn_\u001fUx\u001d\u0011\u00106"), true)).a(e);
        this.X.a(this.i, p16);
        if (r2 != null) {
            this.U = new Menu(bm.A);
        }
        final Panel panel = new Panel();
        this.setLayout(new BorderLayout());
        Label_0912: {
            if (p17 && irc.e) {
                this.u = new b(q2, t.r, this.Z);
                this.setLayout(gridBagLayout);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 0;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                gridBagLayout.setConstraints(this.u, gridBagConstraints);
                this.add(this.u);
                gridBagConstraints.gridx = 0;
                gridBagConstraints.gridy = 1;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 50.0;
                gridBagLayout.setConstraints(panel, gridBagConstraints);
                this.add(panel);
                if (!dx) {
                    break Label_0912;
                }
                irc.cS = !irc.cS;
            }
            this.add(m("o10*s^"), panel);
        }
        panel.setLayout(gridBagLayout);
        final Panel panel2 = new Panel();
        int n = 0;
        if (!irc.e) {
            if (irc.E != null) {
                this.bb.a(irc.E);
            }
            final GridBagLayout layout = new GridBagLayout();
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
            panel2.setLayout(layout);
            gridBagConstraints2.fill = 1;
            gridBagConstraints2.gridx = 0;
            gridBagConstraints2.gridy = 0;
            gridBagConstraints2.weightx = 99.0;
            layout.setConstraints(this.bb, gridBagConstraints2);
            panel2.add(this.bb);
            if (irc.bG) {
                this.bb.addMouseListener(new l(this.bb, e, s));
            }
            if (!irc.bC) {
                this.bB = new a(s, null, t);
                gridBagConstraints2.gridx = 1;
                gridBagConstraints2.weightx = 1.0;
                layout.setConstraints(this.bB, gridBagConstraints2);
                panel2.add(this.bB);
            }
            this.a(gridBagConstraints, 0, 0, 2, 1, 1.0, 1.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
            gridBagLayout.setConstraints(panel2, gridBagConstraints);
            panel.add(panel2);
            n = 1;
        }
        int n2 = 0;
        if (irc.bH) {
            this.a(gridBagConstraints, 0, n, 1, 1, 50.0, 20.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            panel.add(this.j);
            n2 = 1;
        }
        if (t.s != null) {
            this.i.c(t.s);
            panel.setBackground(t.s);
            this.X.setBackground(t.s);
            y.setBackground(t.s);
        }
        if (t.t != null) {
            this.bb.setBackground(t.t);
        }
        if (t.u != null) {
            this.bb.setForeground(t.u);
        }
        if (this.bB != null) {
            if (t.v != null) {
                this.bB.setBackground(t.v);
            }
            if (t.w != null) {
                this.bB.setForeground(t.w);
            }
        }
        if (t.x != null) {
            this.i.b(t.x);
        }
        if (t.y != null) {
            this.i.a(t.y);
        }
        this.a(gridBagConstraints, 0, n + n2, 1, 1, 50.0, 98.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(this.i, gridBagConstraints);
        panel.add(this.i);
        final Panel panel3 = new Panel();
        panel3.setLayout(gridBagLayout);
        if (t.B != null) {
            this.m.setBackground(t.B);
        }
        if (t.C != null) {
            this.m.setForeground(t.C);
        }
        this.a(gridBagConstraints, 0, 0, 1, 10, 10.0, 98.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(this.m, gridBagConstraints);
        panel3.add(this.m);
        this.a(gridBagConstraints, 1, n, 1, (n2 == 1) ? 2 : 1, 5.0, 98.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(panel3, gridBagConstraints);
        panel.add(panel3);
        if (irc.p) {
            this.m.a(panel);
        }
        if (t.z != null) {
            this.k.setBackground(t.z);
        }
        if (t.A != null) {
            this.k.setForeground(t.A);
        }
        this.a(gridBagConstraints, 0, 1 + n + n2, 1, 1, 10.0, 2.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(this.k, gridBagConstraints);
        panel.add(this.k);
        this.k.addActionListener(this);
        this.k.addKeyListener(this.k);
        if (irc.bx) {
            this.k.setEnabled(false);
        }
        this.m.a(this.k);
        this.k.a(this.m);
        if (irc.bt) {
            if (t.F != null) {
                this.ba.setBackground(t.F);
            }
            if (t.I != null) {
                this.ba.b.setBackground(t.I);
                this.ba.c.setBackground(t.I);
                this.ba.f.setBackground(t.I);
            }
            if (t.J != null) {
                this.ba.b.setForeground(t.J);
                this.ba.c.setForeground(t.J);
                this.ba.f.setForeground(t.J);
            }
            if (t.G != null) {
                this.ba.d.setBackground(t.G);
                this.ba.e.setBackground(t.G);
            }
            if (t.H != null) {
                this.ba.d.setForeground(t.H);
                this.ba.e.setForeground(t.H);
            }
            this.ba.a(s);
            t.g(s);
            q.c(s);
            this.a(gridBagConstraints, 0, 2 + n + n2, 2, 1, 50.0, 2.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
            gridBagLayout.setConstraints(this.ba, gridBagConstraints);
            panel.add(this.ba);
        }
        if (t.D != null) {
            this.l.setBackground(t.D);
        }
        if (t.E != null) {
            this.l.setForeground(t.E);
        }
        this.a(gridBagConstraints, 1, n + ((n2 == 1) ? 2 : 1), 1, 1, 5.0, 2.0, 10, 1, new Insets(0, 0, 0, 0), 0, 0);
        gridBagLayout.setConstraints(this.l, gridBagConstraints);
        panel.add(this.l);
        this.p();
        this.k.requestFocus();
        this.m.size();
        this.i.size();
        this.n = true;
        if (irc.e) {
            this.q();
            return;
        }
        irc.cy.a(this.d, panel, this.k);
        panel.validate();
        if (irc.cy.c(this.d) < 0) {
            this.q();
            return;
        }
        irc.cy.b(this.d);
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    void i(final String a) {
        this.A = a;
        this.f();
    }
    
    void a(final Font font) {
        bo.a(this.bc, font);
        bo.a(this.bd, font);
        bo.a(this.be, font);
        bo.a(this.bf, font);
        bo.a(this.bh, font);
        bo.a(this.bi, font);
        if (this.bg != null) {
            bo.a(this.bg, font);
        }
        bo.a(this.bk, font);
        bo.a(this.bl, font);
        bo.a(this.bj, font);
        bo.a(this.bm, font);
        bo.a(this.bn, font);
        bo.a(this.bo, font);
        this.k();
        this.c();
    }
    
    public void d(final int n) {
        this.ba.a(n);
    }
    
    void n() {
        if (!irc.q) {
            return;
        }
        this.m.e();
        this.bp.removeActionListener(this);
        this.bf.remove(this.bp);
        this.bq.removeActionListener(this);
        this.bf.remove(this.bq);
        if (irc.bI) {
            this.bx.removeActionListener(this);
            this.bf.remove(this.bx);
            this.by.removeActionListener(this);
            this.bf.remove(this.by);
        }
        this.o();
    }
    
    void o() {
        if (!irc.q) {
            return;
        }
        this.m.f();
        this.br.removeActionListener(this);
        this.bf.remove(this.br);
        this.bs.removeActionListener(this);
        this.bf.remove(this.bs);
        this.bt.removeActionListener(this);
        this.bf.remove(this.bt);
        this.bu.removeActionListener(this);
        this.bf.remove(this.bu);
        this.bv.removeActionListener(this);
        this.bf.remove(this.bv);
        this.bw.removeActionListener(this);
        this.bf.remove(this.bw);
    }
    
    void p() {
        final boolean dx = bm.dX;
        this.bc = new Menu(bm.p);
        this.bd = new Menu(bm.r);
        this.bj = new Menu(bm.s);
        this.be = new Menu(bm.u);
        this.bf = new Menu(bm.w);
        this.bh = new Menu(bm.v);
        this.bi = new Menu(bm.x);
        this.bk = new Menu(bm.y);
        this.C.add(this.bc);
        if (irc.q) {
            this.C.add(this.bd);
        }
        this.C.add(this.be);
        if (irc.q) {
            this.C.add(this.bf);
        }
        if (!irc.bC) {
            final MenuItem menuItem;
            this.bc.add(menuItem = new MenuItem(bm.W));
            menuItem.addActionListener(this);
            this.bc.add(new MenuItem("-"));
        }
        final MenuItem menuItem2;
        this.bc.add(menuItem2 = new MenuItem(bm.D));
        menuItem2.addActionListener(this);
        if (irc.U) {
            final MenuItem menuItem3;
            this.bd.add(menuItem3 = new MenuItem(bm.E));
            menuItem3.addActionListener(this);
        }
        final MenuItem menuItem4;
        this.bd.add(menuItem4 = new MenuItem(bm.F));
        menuItem4.addActionListener(this);
        final MenuItem menuItem5;
        this.bd.add(menuItem5 = new MenuItem(bm.G));
        menuItem5.addActionListener(this);
        final MenuItem menuItem6;
        this.bd.add(menuItem6 = new MenuItem(bm.H));
        menuItem6.addActionListener(this);
        if (irc.n && irc.bB) {
            final MenuItem menuItem7;
            this.bd.add(menuItem7 = new MenuItem(bm.I));
            menuItem7.addActionListener(this);
        }
        this.bd.add(new MenuItem("-"));
        this.bd.add(this.bj);
        final MenuItem menuItem8;
        this.bj.add(menuItem8 = new MenuItem(bm.J));
        menuItem8.addActionListener(this);
        final MenuItem menuItem9;
        this.bj.add(menuItem9 = new MenuItem(bm.K));
        menuItem9.addActionListener(this);
        final MenuItem menuItem10;
        this.be.add(menuItem10 = new MenuItem(bm.L));
        menuItem10.addActionListener(this);
        final MenuItem menuItem11;
        this.be.add(menuItem11 = new MenuItem(bm.M));
        menuItem11.addActionListener(this);
        this.bl = new Menu(bm.z);
        final MenuItem menuItem12;
        this.bf.add(menuItem12 = new MenuItem(bm.bb));
        menuItem12.addActionListener(this);
        if (irc.ch != null) {
            final MenuItem menuItem13;
            this.bf.add(menuItem13 = new MenuItem(bm.bc));
            menuItem13.addActionListener(this);
        }
        final MenuItem menuItem14;
        this.bf.add(menuItem14 = new MenuItem(bm.ba));
        menuItem14.addActionListener(this);
        this.bf.add(this.bl);
        final MenuItem menuItem15;
        this.bl.add(menuItem15 = new MenuItem(bm.Y));
        menuItem15.addActionListener(this);
        final MenuItem menuItem16;
        this.bl.add(menuItem16 = new MenuItem(bm.Z));
        menuItem16.addActionListener(this);
        if (irc.q) {
            this.bf.add(this.bk);
            final MenuItem menuItem17;
            this.bk.add(menuItem17 = new MenuItem(bm.bg));
            menuItem17.addActionListener(this);
            final MenuItem menuItem18;
            this.bk.add(menuItem18 = new MenuItem(bm.bh));
            menuItem18.addActionListener(this);
            final MenuItem menuItem19;
            this.bk.add(menuItem19 = new MenuItem(bm.bi));
            menuItem19.addActionListener(this);
            final MenuItem menuItem20;
            this.bk.add(menuItem20 = new MenuItem(bm.bj));
            menuItem20.addActionListener(this);
            final MenuItem menuItem21;
            this.bk.add(menuItem21 = new MenuItem(bm.bk));
            menuItem21.addActionListener(this);
            final MenuItem menuItem22;
            this.bk.add(menuItem22 = new MenuItem(bm.bl));
            menuItem22.addActionListener(this);
        }
        this.g();
        int n;
        while (true) {
            Label_1007: {
                if (this.T == null) {
                    break Label_1007;
                }
                this.bg = new Menu(bm.di);
                this.C.add(this.bg);
                final MenuItem menuItem23;
                this.bg.add(menuItem23 = new MenuItem(bm.be));
                menuItem23.addActionListener(this);
                this.bg.add(new MenuItem("-"));
                this.W = new MenuItem[this.T.length];
                n = 0;
                while (true) {
                    Label_0998: {
                        if (!dx) {
                            break Label_0998;
                        }
                        this.W[n] = new MenuItem(this.T[n]);
                        this.bg.add(this.W[n]);
                        this.W[n].addActionListener(this);
                        ++n;
                    }
                    if (n != this.T.length) {
                        continue;
                    }
                    break;
                }
            }
            this.C.add(this.bh);
            this.C.add(this.bi);
            this.C.setHelpMenu(this.bi);
            this.bm = new Menu(bm.O);
            this.bn = new Menu(bm.P);
            this.bo = new Menu(bm.Q);
            this.bh.add(this.bm);
            this.bh.add(this.bn);
            this.bh.add(this.bo);
            this.F = this.i.b();
            this.E = new CheckboxMenuItem[this.F.length];
            n = 0;
            if (dx) {
                if (dx) {
                    continue;
                }
            }
            break;
        }
        while (true) {
            if (n == this.F.length) {
                n = 0;
                if (!dx) {
                    break;
                }
            }
            else {
                this.E[n] = new CheckboxMenuItem(this.F[n]);
                this.bm.add(this.E[n]);
                this.E[n].addItemListener(this);
            }
            ++n;
        }
        while (true) {
            while (true) {
                Label_1267: {
                    if (!dx) {
                        break Label_1267;
                    }
                    this.H[n] = new CheckboxMenuItem(this.G[n]);
                    this.bn.add(this.H[n]);
                    this.H[n].addItemListener(this);
                    ++n;
                }
                if (n != this.G.length) {
                    continue;
                }
                break;
            }
            n = 0;
            if (dx) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_1332: {
                    if (!dx) {
                        break Label_1332;
                    }
                    this.J[n] = new CheckboxMenuItem(this.I[n]);
                    this.bo.add(this.J[n]);
                    final t t = this;
                    t.J[n].addItemListener(this);
                    ++n;
                }
                if (n != this.I.length) {
                    continue;
                }
                break;
            }
            this.K = this.F[irc.bV];
            this.L = this.G[irc.bU];
            this.M = this.I[irc.bW];
            this.a(irc.bV);
            this.c(irc.bW);
            this.e(irc.bU);
            this.e();
            final t t = this;
            if (!dx) {
                if (this.t.n != null) {
                    final MenuItem menuItem24;
                    this.bi.add(menuItem24 = new MenuItem(bm.U));
                    menuItem24.addActionListener(this);
                    this.bi.add(new MenuItem("-"));
                }
                final MenuItem menuItem25;
                this.bi.add(menuItem25 = new MenuItem(bm.V));
                menuItem25.addActionListener(this);
                this.bp = new MenuItem(bm.bm);
                this.bq = new MenuItem(bm.bn);
                this.br = new MenuItem(bm.bo);
                this.bs = new MenuItem(bm.bp);
                this.bt = new MenuItem(bm.bq);
                this.bu = new MenuItem(bm.br);
                this.bv = new MenuItem(bm.dJ);
                this.bw = new MenuItem(bm.dK);
                this.bx = new MenuItem(bm.dT);
                this.by = new MenuItem(bm.dU);
                return;
            }
            continue;
        }
    }
    
    private void a(final String s, final String s2, final String s3) {
        if (this.m.e(s + s2) >= 0) {
            this.m.d(s + s2);
            this.m.a(s + s3);
        }
    }
    
    public void j(final String s) {
        int n = 0;
        while (true) {
            Label_0039: {
                if (!bm.dX) {
                    break Label_0039;
                }
                if (s.equalsIgnoreCase(this.F[n])) {
                    this.K = this.F[n];
                    this.e();
                    return;
                }
                ++n;
            }
            if (n == this.F.length) {
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
    
    void k(final String s) {
        this.m.a(s);
        this.j();
    }
    
    void l(final String s) {
        this.m.d(s);
        this.m.d("@" + s);
        this.m.d("+" + s);
        this.m.d('%' + s);
        if (irc.bJ) {
            this.m.d("*" + s);
            this.m.d('!' + s);
        }
        this.j();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if ((event.modifiers & 0x2) != 0x0 || (event.modifiers & 0x4) != 0x0) {
            this.d();
            if (!bm.dX) {
                return false;
            }
        }
        if (this.k != null) {
            this.k.requestFocus();
        }
        return false;
    }
    
    private void q() {
        if (irc.o) {
            this.Z.setMenuBar(this.C);
        }
        if (irc.A != null) {
            this.Z.setIconImage(irc.A);
        }
        Label_0090: {
            if (irc.bK) {
                this.Z.reshape(0, 0, irc.t.width, irc.t.height);
                if (!bm.dX) {
                    break Label_0090;
                }
            }
            irc.a();
            this.Z.reshape(irc.r, irc.s, irc.bh, irc.bi);
        }
        this.Z.add(m("o10*s^"), this);
        this.Z.addWindowListener(this);
        this.Z.show();
    }
    
    void r() {
        this.m.i();
    }
    
    void a(final String s, final boolean b) {
        if (b) {
            this.a(m("\u0006t") + this.p.a() + bm.bL + s, bn.p, false);
            this.e.a(m("|\u0006\u0017\b[\u007f\u0013~") + this.d + m("\fn_\u001fUx\u001d\u0011\u0010") + bm.bL + s + m("-^"));
            return;
        }
        this.a(m("\u0006t") + this.p.a() + bm.cC + s, bn.p, false);
        this.e.a(m("|\u0006\u0017\b[\u007f\u0013~") + this.d + m("\fn_\u001fUx\u001d\u0011\u0010") + bm.cC + s + m("-^"));
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    private static String m(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ',';
                    break;
                }
                case 1: {
                    c2 = 'T';
                    break;
                }
                case 2: {
                    c2 = '^';
                    break;
                }
                case 3: {
                    c2 = '^';
                    break;
                }
                default: {
                    c2 = '\u0016';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
