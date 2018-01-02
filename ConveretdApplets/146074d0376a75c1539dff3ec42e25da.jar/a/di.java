// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Choice;

public final class di extends dh
{
    private g w;
    private g e;
    private j q;
    private j w;
    private g r;
    private Choice q;
    private dW q;
    private TextField q;
    private TextField w;
    private g t;
    private g y;
    private Frame w;
    private g u;
    private g i;
    private g o;
    private g p;
    private g a;
    private g s;
    private g d;
    private g f;
    private g g;
    private Checkbox q;
    private Checkbox w;
    private boolean q;
    private boolean w;
    private n q;
    private n w;
    private String o;
    private String p;
    private String a;
    private String s;
    
    public di(final Frame w, final cU cu, final cz cz, final es es, final int n) {
        super(w, cu, cz, es, n);
        this.w = w;
        this.w = new g(70, 20);
        this.e = new g(70, 20);
        this.r = new g(70, 20);
        super.w = (cz)this.q.e().w(this.q.q());
        this.q.q(super.w.q());
        this.q = cz.t();
        this.w = cz.q(82);
        this.o = es.q(0, 0);
        this.p = es.q(0, 1);
        if (this.q.q(41)) {
            this.t = cz.i;
            this.y = cz.o;
            if (this.y != null && this.y.equals(this.t)) {
                this.y = null;
            }
            this.a = es.q(0, 2);
            this.s = es.q(0, 3);
        }
    }
    
    public final boolean q() {
        boolean b = false;
        if ((this.q.e() && !this.w.e()) || (this.q.r() && !this.w.e() && !this.w.r())) {
            return super.q();
        }
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 0, 0);
        final Panel panel = new Panel(new GridBagLayout());
        if (this.q != null) {
            final d d;
            (d = new d()).w(this.q.q);
            panel.add(d, gridBagConstraints);
        }
        final boolean b2;
        if (b2 = (this.q.q(52) && this.q.a_())) {
            gridBagConstraints.gridwidth = 3;
        }
        else {
            gridBagConstraints.gridwidth = 0;
        }
        final Label label;
        (label = new Label(this.q)).setFont(m.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (b2) {
            (this.d = new g(105, 20)).q(eb.q("Remove"));
            gridBagConstraints.anchor = 13;
            gridBagConstraints.gridwidth = 0;
            panel.add(this.d, gridBagConstraints);
        }
        this.q.weightx = 1.0;
        this.q.fill = 2;
        this.q.gridwidth = 0;
        this.q.add(panel, this.q);
        this.q.add(this.q, this.q);
        this.q.fill = 0;
        this.q.weightx = 0.0;
        if (this.w != null) {
            if (this.q.q(75)) {
                final Label label2 = new Label(this.o);
                final Label label3;
                (label3 = new Label(eb.q("Real Name"))).setFont(m.t);
                this.q.gridwidth = 3;
                this.q.setConstraints(label3, this.q);
                this.q.add(label3);
                this.q.gridwidth = 0;
                this.q.setConstraints(label2, this.q);
                this.q.add(label2);
            }
            if (this.q.q(8)) {
                final Label label4;
                (label4 = new Label(eb.q("Display Name"))).setFont(m.t);
                this.q.gridwidth = 3;
                this.q.setConstraints(label4, this.q);
                this.q.add(label4);
                (this.q = new TextField(this.w, 15)).setForeground(Color.black);
                this.q.setBackground(Color.white);
                gridBagConstraints.weightx = 1.0;
                this.q.fill = 1;
                this.q.gridwidth = 2;
                this.q.setConstraints(this.q, this.q);
                this.q.add(this.q);
                gridBagConstraints.weightx = 0.0;
                (this.t = new g(70, 20)).q(eb.q("Change"));
                this.t.resize(this.t.getFontMetrics(this.t.getFont()).stringWidth(eb.q("Change")) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.t, this.q);
                this.q.add(this.t);
            }
            else {
                final Label label5 = new Label(this.w);
                final Label label6;
                (label6 = new Label(eb.q("Display Name"))).setFont(m.t);
                this.q.gridwidth = 3;
                this.q.setConstraints(label6, this.q);
                this.q.add(label6);
                this.q.gridwidth = 0;
                this.q.setConstraints(label5, this.q);
                this.q.add(label5);
            }
            b = true;
        }
        if (this.i != null && !"".equals(this.i)) {
            final Label label7;
            (label7 = new Label(eb.q("Referer URL:"))).setFont(m.t);
            this.q.gridwidth = 2;
            this.q.setConstraints(label7, this.q);
            this.q.add(label7);
            final Label label8 = new Label(this.i);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(label8, this.q);
            this.q.add(label8);
            b = true;
        }
        if (this.q.q(52) || this.q.q(68)) {
            final Label label9;
            (label9 = new Label(eb.q("Warn user"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label9, this.q);
            this.q.add(label9);
            (this.w = new TextField()).setForeground(Color.black);
            this.w.setBackground(Color.white);
            this.q.fill = 1;
            this.q.gridwidth = 2;
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            (this.y = new g(70, 20)).q(eb.q("Warn"));
            this.y.resize(this.y.getFontMetrics(this.y.getFont()).stringWidth(eb.q("Warn")) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.y, this.q);
            this.q.add(this.y);
            b = true;
        }
        if (this.e != null) {
            final Label label10 = new Label(this.e);
            final Label label11;
            (label11 = new Label(eb.q("Age"))).setFont(m.t);
            this.q.gridwidth = -1;
            this.q.setConstraints(label11, this.q);
            this.q.add(label11);
            this.q.gridwidth = 0;
            this.q.setConstraints(label10, this.q);
            this.q.add(label10);
            b = true;
        }
        if (this.r != null) {
            final Label label12 = new Label(this.r);
            final Label label13;
            (label13 = new Label(eb.q("Gender"))).setFont(m.t);
            this.q.gridwidth = -1;
            this.q.setConstraints(label13, this.q);
            this.q.add(label13);
            this.q.gridwidth = 0;
            this.q.setConstraints(label12, this.q);
            this.q.add(label12);
            b = true;
        }
        if (this.a != null && this.w.q(66)) {
            final Label label14 = new Label(this.a);
            final Label label15;
            (label15 = new Label(eb.q("Country"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label15, this.q);
            this.q.add(label15);
            this.q.gridwidth = 2;
            this.q.setConstraints(label14, this.q);
            this.q.add(label14);
            (this.u = new g(70, 20)).q(aJ.F);
            this.u.resize(this.u.getFontMetrics(this.u.getFont()).stringWidth(aJ.F) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.u, this.q);
            this.q.add(this.u);
            b = true;
        }
        if (this.p != null) {
            final Label label16 = new Label(this.p);
            final Label label17;
            (label17 = new Label(eb.q("Online Time"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label17, this.q);
            this.q.add(label17);
            this.q.gridwidth = (this.q.q(9) ? 2 : 0);
            this.q.setConstraints(label16, this.q);
            this.q.add(label16);
            if (this.q.q(9)) {
                (this.i = new g(70, 20)).q(aJ.G);
                this.i.resize(this.i.getFontMetrics(this.i.getFont()).stringWidth(aJ.G) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.i, this.q);
                this.q.add(this.i);
            }
            b = true;
        }
        if (this.q != null) {
            final boolean q = this.q.q(11);
            this.q = new H(this.u);
            final Label label18 = new Label(eb.q("Room"));
            try {
                this.q.q(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label18.setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label18, this.q);
            this.q.add(label18);
            this.q.gridwidth = (q ? 2 : 0);
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            if (q) {
                (this.o = new g(70, 20)).q(aJ.H);
                this.o.resize(this.o.getFontMetrics(this.o.getFont()).stringWidth(aJ.H) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.o, this.q);
                this.q.add(this.o);
            }
            b = true;
        }
        if (this.t != null) {
            final boolean b3 = (this.q.q(44) || this.q.q(52)) && !this.q.getName().equalsIgnoreCase("Chatmaster");
            final Label label19 = new Label(this.t);
            final Label label20;
            (label20 = new Label(eb.q("IP Address"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label20, this.q);
            this.q.add(label20);
            this.q.gridwidth = (b3 ? 2 : 0);
            this.q.setConstraints(label19, this.q);
            this.q.add(label19);
            if (b3) {
                (this.p = new g(70, 20)).q(aJ.U);
                this.p.resize(this.p.getFontMetrics(this.p.getFont()).stringWidth(aJ.U) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.p, this.q);
                this.q.add(this.p);
            }
            b = true;
        }
        if (this.s != null) {
            final boolean b4 = this.q.q(52) || this.q.q(85);
            final Label label21 = new Label(this.s);
            final Label label22;
            (label22 = new Label(eb.q("Host Name"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label22, this.q);
            this.q.add(label22);
            this.q.gridwidth = (b4 ? 2 : 0);
            this.q.setConstraints(label21, this.q);
            this.q.add(label21);
            if (b4) {
                (this.a = new g(70, 20)).q(aJ.O);
                this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(aJ.O) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.a, this.q);
                this.q.add(this.a);
            }
            b = true;
        }
        if ((this.q.q(3) && this.q.q() == this.q.q()) || this.q.q(5)) {
            final Label label23;
            (label23 = new Label(eb.q("Color"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label23, this.q);
            this.q.add(label23);
            this.q.fill = 0;
            this.q.gridwidth = 2;
            (this.q = new j()).setBackground(new Color(this.q.y()));
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.gridwidth = 0;
            this.q.fill = 2;
            this.w.q(eb.q("Set Color"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(eb.q("Set Color")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            b = true;
        }
        if ((this.q.q(18) && !this.q.a_()) || (this.q.q() == this.q.q() && this.q.q(17))) {
            final Label label24;
            (label24 = new Label(eb.q("Back Color"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label24, this.q);
            this.q.add(label24);
            this.q.fill = 0;
            this.q.gridwidth = 2;
            (this.w = new j()).q(this.q.t());
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            this.q.gridwidth = 0;
            this.q.fill = 2;
            this.e.q(eb.q("Set Back Color"));
            this.e.resize(this.e.getFontMetrics(this.e.getFont()).stringWidth(eb.q("Set Background Color")) + 20, 20);
            this.q.setConstraints(this.e, this.q);
            this.q.add(this.e);
        }
        if (this.q.q(7)) {
            this.q.weightx = 0.0;
            this.q = new Choice();
            ((Component)(this.q = (dW)this.q.t().clone())).setFont(m.y);
            int n = 0;
            final FontMetrics fontMetrics = this.q.getFontMetrics(this.q.getFont());
            for (int i = 0; i < this.q.q(); ++i) {
                this.q.addItem(q(((bZ)this.q.q(i)).getName(), 240, fontMetrics));
                if (((bZ)this.q.q(i)).q() == this.q.o) {
                    n = i;
                }
            }
            this.q.setForeground(Color.black);
            final Label label25;
            (label25 = new Label(eb.q("Room"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label25, this.q);
            this.q.add(label25);
            this.q.fill = 1;
            this.q.gridwidth = 2;
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.select(n);
            this.q.gridwidth = 0;
            this.r.q(eb.q("Move To Room"));
            this.r.resize(this.r.getFontMetrics(this.r.getFont()).stringWidth(eb.q("Move To Room")) + 20, 20);
            this.q.setConstraints(this.r, this.q);
            this.q.add(this.r);
            b = true;
        }
        if ((this.q.q(14) && !this.q.a_()) || (this.q.q(52) && this.q.a_()) || (this.q.q(73) && this.q.q() == this.q.q())) {
            (this.q = new n()).q();
            final cx cx;
            (cx = new cx(999, "No star")).q = a.cx.q(this);
            this.q.q(cx);
            for (int j = 0; j < this.q.w().q(); ++j) {
                final cx cx2 = (cx)this.q.w().q(j);
                final cl q2;
                if ((q2 = cl.q(((dz)this.q).x, cx2.q())) != null && (this.q.a_() || !q2.q(0))) {
                    this.q.q(cx2);
                }
            }
            if (this.q.p > 0) {
                this.q.q(this.q.p);
            }
            final Label label26;
            (label26 = new Label(eb.q("Star"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label26, this.q);
            this.q.add(label26);
            this.q.gridwidth = 2;
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.repaint();
            (this.f = new g(70, 20)).q(eb.q("Change"));
            this.f.resize(this.f.getFontMetrics(this.f.getFont()).stringWidth(this.f.q()) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.f, this.q);
            this.q.add(this.f);
            b = true;
        }
        if ((this.q.q(10) && !this.q.a_()) || (this.q.q(52) && this.q.a_())) {
            (this.w = new n()).q();
            for (int k = 0; k < this.q.r().q(); ++k) {
                final cm cm;
                if ((cm = (cm)this.q.r().q(k)).q() >= 0 && (cm.w() <= 0 || cm.w() == this.q.i())) {
                    this.w.q(cm);
                }
            }
            if (this.q.e > 0) {
                this.w.q(this.q.e);
            }
            final Label label27;
            (label27 = new Label(eb.q("Icon"))).setFont(m.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label27, this.q);
            this.q.add(label27);
            this.q.gridwidth = 2;
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            this.w.repaint();
            (this.g = new g(70, 20)).q(eb.q("Change"));
            this.g.resize(this.g.getFontMetrics(this.g.getFont()).stringWidth(this.g.q()) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.g, this.q);
            this.q.add(this.g);
            b = true;
        }
        if (this.q.q(6) || this.q.q(12)) {
            if (this.q.q(6)) {
                final Label label28 = new Label(eb.q(""));
                this.q.gridwidth = 3;
                this.q.setConstraints(label28, this.q);
                this.q.add(label28);
                this.q.gridwidth = 2;
                ((GridBagLayout)(this.q = new Checkbox(aJ.D, this.q))).setConstraints(this.q, this.q);
                this.q.add(this.q);
            }
            if (this.q.q(12)) {
                (this.s = new g(70, 20)).q(eb.q("Give One Kick"));
                this.q.gridwidth = 0;
                this.q.add(this.s, this.q);
            }
            b = true;
        }
        if (this.q.q(81) && this.q.b_()) {
            final Label label29 = new Label(eb.q(""));
            this.q.gridwidth = 3;
            this.q.setConstraints(label29, this.q);
            this.q.add(label29);
            this.q.gridwidth = 2;
            (this.w = new Checkbox(eb.q("Up Guest Nickname"), this.w)).setState(this.q.q(82));
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
        }
        return b;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final dP dp = new dP((cU)this.q, this.q.q());
                if (event.target == this.q) {
                    if (this.q.q(6) && this.q != null && this.q.getState() != this.q) {
                        dp.w(this.q.getState() ? "enable" : "disable");
                    }
                    if (this.q.q(81) && this.q.b_() && this.w.getState() != this.w) {
                        final dP dp2 = dp;
                        final int q = this.q.q();
                        final boolean state = this.w.getState();
                        final int n = q;
                        final dP dp3 = dp2;
                        final es es;
                        (es = new es(4202624, 1)).w = -1;
                        es.q = -1;
                        es.q(0, 0, n);
                        es.q(0, 0, state);
                        dp3.q.q(es);
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.w) {
                    dp.q(this.q.q());
                    return true;
                }
                if (event.target == this.e) {
                    dp.w(this.w.q());
                    return true;
                }
                if (event.target == this.r) {
                    if (((db)this.q.q(this.q.getSelectedIndex())).q != null) {
                        final ae ae;
                        (ae = new ae(this.w, this.q, (db)this.q.q(this.q.getSelectedIndex()))).setVisible(true);
                        final String q2;
                        if ((q2 = ae.q) != null) {
                            dp.q(String.valueOf(((bZ)this.q.q(this.q.getSelectedIndex())).q()), q2);
                        }
                    }
                    else {
                        dp.q(String.valueOf(((bZ)this.q.q(this.q.getSelectedIndex())).q()), null);
                    }
                    return true;
                }
                if (event.target == this.t) {
                    dp.q("/cn " + this.q.getText());
                    return true;
                }
                if (event.target == this.y) {
                    dp.q("/Warn " + this.w.getText());
                    return true;
                }
                if (event.target == this.u) {
                    dp.q("/Ping");
                    return true;
                }
                if (event.target == this.i) {
                    dp.q("/AllIP");
                    return true;
                }
                if (event.target == this.o) {
                    dp.e(this.q.getName());
                    return true;
                }
                if (event.target == this.p) {
                    dp.q("/MustKick");
                    return true;
                }
                if (event.target == this.a) {
                    dp.q("/MustBan");
                    return true;
                }
                if (event.target == this.d) {
                    dp.q("/Remove");
                    return true;
                }
                if (event.target == this.s) {
                    dp.q("/GiveKick");
                    return true;
                }
                if (event.target == this.f) {
                    dp.q("/ChangeStar " + this.q.q().q());
                    return true;
                }
                if (event.target == this.g) {
                    dp.q("/ChangeIcon " + this.w.q().q());
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private static String q(String string, int stringWidth, final FontMetrics fontMetrics) {
        if ((stringWidth = fontMetrics.stringWidth(string)) > 240) {
            final int stringWidth2 = fontMetrics.stringWidth("...");
            final int length = string.length();
            int n = 0;
            int i = length;
            int n2 = length;
            String substring = string;
            if ("...".length() < length) {
                while (i > n) {
                    substring = string.substring(0, n2);
                    if ((stringWidth = fontMetrics.stringWidth(substring) + stringWidth2) <= 240 && n2 == length) {
                        break;
                    }
                    if (stringWidth < 240) {
                        n = n2 + 1;
                    }
                    else {
                        i = n2;
                    }
                    n2 = (n + i) / 2;
                }
            }
            string = substring + "...";
        }
        return string;
    }
}
