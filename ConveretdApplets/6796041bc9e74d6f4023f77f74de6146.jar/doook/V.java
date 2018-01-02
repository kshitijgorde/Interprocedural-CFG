// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.net.MalformedURLException;
import java.awt.Label;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.net.URL;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Choice;

public class V extends p
{
    private aS a;
    private aS b;
    private aS g;
    private aX d;
    private aX b;
    private aS h;
    private aS i;
    private Choice i;
    private Choice j;
    private aG u;
    private aG v;
    private c a;
    protected cG a;
    private u e;
    private TextField k;
    private aS j;
    private Frame f;
    private aS k;
    private aS l;
    private aS m;
    private aS n;
    private aS o;
    private TextField l;
    private aS p;
    private Checkbox m;
    private boolean aa;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.a.s();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    this.e.e(this.a.h);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.e.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    this.e.a((Z)null, this.a);
                    return true;
                }
                if (event.target == this.a) {
                    if (this.e.d(12) && this.m.getState() != this.aa) {
                        final cD cd = new cD(66305, 1);
                        cd.o = -1;
                        cd.j = this.a.h();
                        cd.a(0, 0, -1);
                        cd.a(0, 0, this.m.getState() ? "*enable" : "*disable");
                        this.e.o(cd);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.p) {
                    if (this.l.getText().trim().length() == 0) {
                        new E(this.f, ao.e("Note"), ao.e("Please write the warning message."), this.e).setVisible(true);
                        return true;
                    }
                    final cD cd2 = new cD(66305, 1);
                    cd2.o = -1;
                    cd2.j = this.a.h();
                    cd2.a(0, 0, -1);
                    cd2.a(0, 0, "warn=" + this.l.getText());
                    this.e.o(cd2);
                    return true;
                }
                else {
                    if (event.target == this.g) {
                        final cD cd3 = new cD(66305, 1);
                        cd3.o = -1;
                        cd3.j = this.a.h();
                        cd3.a(0, 0, -1);
                        cd3.a(0, 0, "color=" + this.d.aA + "," + this.b.aA + "," + this.d.name);
                        this.e.o(cd3);
                        this.g.setForeground(new Color(this.d.aA));
                        this.g.setBackground(new Color(this.b.aA));
                        this.g.repaint();
                        return true;
                    }
                    if (event.target == this.h) {
                        if (((T)this.u.a(this.i.getSelectedIndex())).a != null) {
                            new U(this.f, this.e, ((cF)this.u.a(this.i.getSelectedIndex())).h(), this.a.h()).setVisible(true);
                            return true;
                        }
                        final cD cd4 = new cD(66305, 1);
                        cd4.o = -1;
                        cd4.j = this.a.h();
                        cd4.a(0, 0, -1);
                        cd4.a(0, 0, "move=" + String.valueOf(((cF)this.u.a(this.i.getSelectedIndex())).h()));
                        this.e.o(cd4);
                        return true;
                    }
                    else {
                        if (event.target == this.i) {
                            final cD cd5 = new cD(66305, 1);
                            cd5.o = -1;
                            cd5.j = this.a.h();
                            cd5.a(0, 0, -1);
                            if (this.j.getSelectedIndex() != 0) {
                                cd5.a(0, 0, "group=" + String.valueOf(((cF)this.v.a(this.j.getSelectedIndex() - 1)).h()));
                            }
                            else {
                                cd5.a(0, 0, "group=0");
                            }
                            this.e.o(cd5);
                            return true;
                        }
                        if (event.target == this.j) {
                            final cD cd6 = new cD(66305, 1);
                            cd6.o = -1;
                            cd6.j = this.a.h();
                            cd6.a(0, 0, -1);
                            cd6.a(0, 0, "CN=" + this.k.getText());
                            this.e.o(cd6);
                            return true;
                        }
                        if (event.target == this.k) {
                            final cD cd7 = new cD(66305, 1);
                            cd7.o = -1;
                            cd7.j = this.a.h();
                            cd7.a(0, 0, -1);
                            cd7.a(0, 0, "ping");
                            this.e.o(cd7);
                            return true;
                        }
                        if (event.target == this.l) {
                            final cD cd8 = new cD(66305, 1);
                            cd8.o = -1;
                            cd8.j = this.a.h();
                            cd8.a(0, 0, -1);
                            cd8.a(0, 0, "IP");
                            this.e.o(cd8);
                            return true;
                        }
                        if (event.target == this.m) {
                            final cD cd9 = new cD(66305, 1);
                            cd9.o = -1;
                            cd9.j = this.a.h();
                            cd9.a(0, 0, -1);
                            cd9.a(0, 0, "IPX");
                            this.e.o(cd9);
                            return true;
                        }
                        if (event.target == this.n) {
                            final cD cd10 = new cD(66305, 1);
                            cd10.o = -1;
                            cd10.j = this.a.h();
                            cd10.a(0, 0, -1);
                            cd10.a(0, 0, "kick=");
                            this.e.o(cd10);
                            return true;
                        }
                        if (event.target == this.o) {
                            final cD cd11 = new cD(66305, 1);
                            cd11.o = -1;
                            cd11.j = this.a.h();
                            cd11.a(0, 0, -1);
                            cd11.a(0, 0, "bkick=");
                            this.e.o(cd11);
                            return true;
                        }
                        break;
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public V(final Frame f, final u e, final cG a, final cD cd, final int n) {
        super(f, false);
        this.f = f;
        this.a = new aS(70, 20);
        this.b = new aS(115, 20);
        this.g = new aS(70, 20);
        this.h = new aS(70, 20);
        this.i = new aS(70, 20);
        this.setBackground(e.a.a);
        this.a = a;
        this.e = e;
        final cA ca = new cA();
        ca.setBackground(e.a.g);
        ca.setForeground(e.a.f);
        final cH ch = new cH();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String d = e.d(a.f());
        final String a2 = cd.a(n, 4);
        final String a3 = cd.a(n, 5);
        final String d2 = e.d(cd.a(n, 0));
        final String d3 = e.d(cd.a(n, 1));
        final String d4 = e.d(cd.a(n, 2));
        final String d5 = e.d(cd.a(n, 3));
        String value = null;
        String s = null;
        String w = null;
        String d6 = null;
        final av av = (av)e.d.b(a.h);
        if (av != null) {
            d6 = e.d(av.f());
        }
        final int b = cd.b(n, 1);
        if (b != -999) {
            value = String.valueOf(b);
        }
        if (cd.b(n, 1)) {
            s = ao.e("Male");
        }
        else if (cd.b(n, 0)) {
            s = ao.e("Female");
        }
        if (e.d(33)) {
            w = a.W;
            final String f2 = a.f;
            if (f2 != null && f2.equals(w)) {}
        }
        final as as = (as)e.b.b(a.g);
        this.setResizable(false);
        this.setTitle(am.a(ao.e("Profile of %1"), new String[] { d }));
        this.setLayout(gridBagLayout);
        ca.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (as != null) {
            final B b2 = new B();
            b2.b(as.q);
            gridBagLayout.setConstraints(b2, gridBagConstraints);
            ca.add(b2);
        }
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(d);
        label.setFont(bL.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ca.add(label);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(ch, gridBagConstraints);
        ca.add(ch);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (a2 != null) {
            final Label label2 = new Label(a2);
            final Label label3 = new Label(ao.e("Real Name"));
            label3.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            ca.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            ca.add(label2);
        }
        final cd cd2 = (cd)this.e.l.b(cd.b(n, 4));
        if (cd2 != null && cd2.description.length() > 0) {
            final Label label4 = new Label(cd2.description);
            final Label label5 = new Label(ao.e("User Group"));
            label5.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            ca.add(label5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            ca.add(label4);
        }
        if (a3 != null) {
            final Label label6 = new Label(a3);
            final Label label7 = new Label(ao.e("Applet URL"));
            label7.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label7, gridBagConstraints);
            ca.add(label7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label6, gridBagConstraints);
            ca.add(label6);
        }
        if (value != null) {
            final Label label8 = new Label(value);
            final Label label9 = new Label(ao.e("Socket ID"));
            label9.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label9, gridBagConstraints);
            ca.add(label9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label8, gridBagConstraints);
            ca.add(label8);
        }
        if (d2 != null) {
            if (this.e.d(6) || (this.e.d(31) && this.e.h() == this.a.h())) {
                final Label label10 = new Label(ao.e("Display Name"));
                label10.setFont(bL.g);
                gridBagConstraints.gridwidth = 3;
                gridBagLayout.setConstraints(label10, gridBagConstraints);
                ca.add(label10);
                (this.k = new TextField(d2, 15)).setForeground(Color.black);
                this.k.setBackground(Color.white);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 2;
                gridBagLayout.setConstraints(this.k, gridBagConstraints);
                ca.add(this.k);
                (this.j = new aS(70, 20)).a(ao.e("Change"));
                this.j.resize(this.j.getFontMetrics(this.j.getFont()).stringWidth(ao.e("Change")) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.j, gridBagConstraints);
                ca.add(this.j);
            }
            else {
                final Label label11 = new Label(d2);
                final Label label12 = new Label(ao.e("Display Name"));
                label12.setFont(bL.g);
                gridBagConstraints.gridwidth = 3;
                gridBagLayout.setConstraints(label12, gridBagConstraints);
                ca.add(label12);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(label11, gridBagConstraints);
                ca.add(label11);
            }
        }
        if (this.e.d(3)) {
            final Label label13 = new Label(ao.e("Warn"));
            label13.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label13, gridBagConstraints);
            ca.add(label13);
            (this.l = new TextField("", 15)).setForeground(Color.black);
            this.l.setBackground(Color.white);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.l, gridBagConstraints);
            ca.add(this.l);
            (this.p = new aS(70, 20)).a(ao.e("Send Warning"));
            this.p.resize(this.p.getFontMetrics(this.p.getFont()).stringWidth(ao.e("Send Warning")) + 20, 20);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.p, gridBagConstraints);
            ca.add(this.p);
        }
        if (s != null) {
            final Label label14 = new Label(s);
            final Label label15 = new Label(ao.e("Gender"));
            label15.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label15, gridBagConstraints);
            ca.add(label15);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label14, gridBagConstraints);
            ca.add(label14);
        }
        if (d4 != null) {
            final c c = new c(d4);
            final Label label16 = new Label(ao.e("Country"));
            label16.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label16, gridBagConstraints);
            ca.add(label16);
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(c, gridBagConstraints);
            try {
                c.a(new URL(d4));
            }
            catch (MalformedURLException ex2) {}
            ca.add(c);
            (this.k = new aS(70, 20)).a(ao.e("Ping"));
            this.k.resize(this.k.getFontMetrics(this.k.getFont()).stringWidth(ao.e("Ping")) + 20, 20);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.k, gridBagConstraints);
            ca.add(this.k);
        }
        if (d3 != null) {
            final c c2 = new c(d3);
            final Label label17 = new Label(ao.e("Online Time"));
            label17.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label17, gridBagConstraints);
            ca.add(label17);
            gridBagConstraints.gridwidth = (this.e.d(9) ? 2 : 0);
            gridBagLayout.setConstraints(c2, gridBagConstraints);
            ca.add(c2);
            if (this.e.d(9)) {
                (this.l = new aS(70, 20)).a(ao.e("View All On IP"));
                this.l.resize(this.l.getFontMetrics(this.l.getFont()).stringWidth(ao.e("View All On IP")) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.l, gridBagConstraints);
                ca.add(this.l);
            }
        }
        if (d6 != null) {
            this.a = new c(d6);
            final Label label18 = new Label(ao.e("Room"));
            try {
                this.a.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label18.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label18, gridBagConstraints);
            ca.add(label18);
            gridBagConstraints.gridwidth = (this.e.d(11) ? 2 : 0);
            gridBagLayout.setConstraints(this.a, gridBagConstraints);
            ca.add(this.a);
            if (this.e.d(11)) {
                (this.m = new aS(70, 20)).a(ao.e("Kick All On IP"));
                this.m.resize(this.m.getFontMetrics(this.m.getFont()).stringWidth(ao.e("Kick All On IP")) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.m, gridBagConstraints);
                ca.add(this.m);
            }
        }
        if (w != null) {
            final Label label19 = new Label(w);
            final Label label20 = new Label(ao.e("IP Address"));
            label20.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label20, gridBagConstraints);
            ca.add(label20);
            gridBagConstraints.gridwidth = (this.e.d(44) ? 2 : 0);
            gridBagLayout.setConstraints(label19, gridBagConstraints);
            ca.add(label19);
            if (this.e.d(44)) {
                (this.n = new aS(70, 20)).a(ao.e("Kick User"));
                this.n.resize(this.n.getFontMetrics(this.n.getFont()).stringWidth(ao.e("Kick User")) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.n, gridBagConstraints);
                ca.add(this.n);
            }
        }
        if (d5 != null) {
            final Label label21 = new Label(d5);
            final Label label22 = new Label(ao.e("Host Name"));
            label22.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label22, gridBagConstraints);
            ca.add(label22);
            gridBagConstraints.gridwidth = (this.e.d(49) ? 2 : 0);
            gridBagLayout.setConstraints(label21, gridBagConstraints);
            ca.add(label21);
            if (this.e.d(49)) {
                (this.o = new aS(70, 20)).a(ao.e("Ban User"));
                this.o.resize(this.o.getFontMetrics(this.o.getFont()).stringWidth(ao.e("Ban User")) + 20, 20);
                gridBagConstraints.fill = 1;
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(this.o, gridBagConstraints);
                ca.add(this.o);
            }
        }
        if ((this.e.d(5) && this.e.h() != this.a.h()) || (this.e.d(21) && this.e.h() == this.a.h())) {
            this.d = new aX(e, this.g, "setForeground");
            this.d.aB = (this.a.d(33) ? 15 : 14);
            this.d.a(this.e.k, !this.e.d(2), true);
            this.d.a(new Color(cd.b(n, 2)));
            this.b = new aX(e, this.g, "setBackground");
            this.b.aB = (this.a.d(33) ? 15 : 14);
            this.b.a(this.e.k, !this.e.d(2), true);
            this.b.a(new Color(cd.b(n, 3)));
            this.d.setSize(20, 20);
            this.b.setSize(20, 20);
            final Label label23 = new Label(ao.e("Color"));
            label23.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label23, gridBagConstraints);
            ca.add(label23);
            gridBagConstraints.fill = 0;
            gridBagConstraints.gridwidth = 2;
            final Panel panel = new Panel();
            panel.setLayout(new FlowLayout());
            panel.add(this.d);
            if ((this.e.d(19) && this.e.h() != this.a.h()) || (this.e.d(51) && this.e.h() == this.a.h())) {
                panel.add(this.b);
            }
            gridBagLayout.setConstraints(panel, gridBagConstraints);
            ca.add(panel);
            gridBagConstraints.gridwidth = 0;
            this.g.a(ao.e("Set Color"));
            this.g.resize(this.g.getFontMetrics(this.g.getFont()).stringWidth(ao.e("Set Color")) + 20, 20);
            gridBagConstraints.fill = 2;
            gridBagLayout.setConstraints(this.g, gridBagConstraints);
            ca.add(this.g);
            this.g.setForeground(new Color(this.d.aA));
            this.g.setBackground(new Color(this.b.aA));
        }
        if (this.e.d(48) || (this.e.d(41) && this.e.h() != this.a.h() && !this.a.d(33)) || (this.e.d(39) && this.e.h() == this.a.h())) {
            gridBagConstraints.weightx = 0.0;
            this.j = new Choice();
            this.v = (aG)e.l.clone();
            int n2 = -1;
            final int b3 = cd.b(n, 4);
            this.j.addItem(" ");
            int i = 0;
            int b4 = this.v.b();
            while (i < b4) {
                if (!this.a.d(33) && !((cF)this.v.a(i)).d(5)) {
                    this.v.c(((cF)this.v.a(i)).h());
                    --b4;
                }
                else {
                    ++i;
                }
            }
            for (int j = 0; j < this.v.b(); ++j) {
                String s2 = ((cF)this.v.a(j)).f();
                if (s2.length() > 35) {
                    s2 = s2.substring(0, 35);
                }
                this.j.addItem(s2);
                if (((cF)this.v.a(j)).h() == b3) {
                    n2 = j;
                }
            }
            this.j.setForeground(Color.black);
            final Label label24 = new Label(ao.e("User Group"));
            label24.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label24, gridBagConstraints);
            ca.add(label24);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.j, gridBagConstraints);
            ca.add(this.j);
            this.j.select(n2 + 1);
            gridBagConstraints.gridwidth = 0;
            this.i.a(ao.e("Change Group"));
            this.i.resize(this.i.getFontMetrics(this.i.getFont()).stringWidth(ao.e("Change Group")) + 20, 20);
            gridBagLayout.setConstraints(this.i, gridBagConstraints);
            ca.add(this.i);
        }
        if (this.e.d(7)) {
            gridBagConstraints.weightx = 0.0;
            this.i = new Choice();
            this.u = (aG)e.d.clone();
            int n3 = 0;
            for (int k = 0; k < this.u.b(); ++k) {
                String s3 = ((cF)this.u.a(k)).f();
                if (s3.length() > 35) {
                    s3 = s3.substring(0, 35);
                }
                this.i.addItem(s3);
                if (((cF)this.u.a(k)).h() == a.h) {
                    n3 = k;
                }
            }
            this.i.setForeground(Color.black);
            final Label label25 = new Label(ao.e("Room"));
            label25.setFont(bL.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label25, gridBagConstraints);
            ca.add(label25);
            gridBagConstraints.fill = 1;
            gridBagConstraints.gridwidth = 2;
            gridBagLayout.setConstraints(this.i, gridBagConstraints);
            ca.add(this.i);
            this.i.select(n3);
            gridBagConstraints.gridwidth = 0;
            this.h.a(ao.e("Move To Room"));
            this.h.resize(this.h.getFontMetrics(this.h.getFont()).stringWidth(ao.e("Move To Room")) + 20, 20);
            gridBagLayout.setConstraints(this.h, gridBagConstraints);
            ca.add(this.h);
        }
        if (this.e.d(12)) {
            final Label label26 = new Label(ao.e(""));
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label26, gridBagConstraints);
            ca.add(label26);
            gridBagConstraints.gridwidth = 0;
            this.aa = cd.b(0, 40);
            gridBagLayout.setConstraints(this.m = new Checkbox(ao.e("Enable writing in public chat rooms"), this.aa), gridBagConstraints);
            ca.add(this.m);
        }
        if (!true) {
            final Label label27 = new Label(ao.e("No profile is available."));
            label27.setFont(bL.g);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label27, gridBagConstraints);
            ca.add(label27);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        this.add(ca);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (e.d(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(ao.e("Send Message"));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(ao.e("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(ao.e("OK"));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(ao.e("OK")) + 20, 20);
        final aQ aq = new aQ(this.a);
        gridBagLayout.setConstraints(aq, gridBagConstraints);
        this.add(aq);
        this.pack();
        this.setVisible(true);
    }
}
