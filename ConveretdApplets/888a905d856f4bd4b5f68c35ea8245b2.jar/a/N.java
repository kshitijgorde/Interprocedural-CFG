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

public final class N extends j
{
    private ad w;
    private ad e;
    private dS q;
    private dS w;
    private ad r;
    private Choice q;
    private M q;
    private TextField q;
    private TextField w;
    private ad t;
    private ad y;
    private Frame w;
    private ad u;
    private ad i;
    private ad o;
    private ad p;
    private ad a;
    private ad s;
    private ad d;
    private ad f;
    private ad g;
    private Checkbox q;
    private boolean q;
    private aq q;
    private aq w;
    private String o;
    private String p;
    private String a;
    private String s;
    
    public N(final Frame w, final dH dh, final p p5, final dI di, final int n) {
        super(w, dh, p5, di, n);
        this.w = w;
        this.w = new ad(70, 20);
        this.e = new ad(70, 20);
        this.r = new ad(70, 20);
        super.w = (p)this.q.r().w(this.q.q());
        this.q.q(super.w.w());
        this.q = p5.e();
        this.o = di.q(0, 0);
        this.p = di.q(0, 1);
        if (this.q.q(41)) {
            this.t = p5.u;
            this.y = p5.i;
            if (this.y != null && this.y.equals(this.t)) {
                this.y = null;
            }
            this.a = di.q(0, 2);
            this.s = di.q(0, 3);
        }
    }
    
    public final boolean q() {
        boolean b = false;
        if ((this.q.a_() && !this.w.a_()) || (this.q.b_() && !this.w.a_() && !this.w.b_())) {
            return super.q();
        }
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(0, 0, 0, 0);
        final Panel panel = new Panel(new GridBagLayout());
        if (this.q != null) {
            final bl bl;
            (bl = new bl()).w(this.q.q);
            panel.add(bl, gridBagConstraints);
        }
        final boolean b2;
        if (b2 = (this.q.q(52) && this.q.q(61))) {
            gridBagConstraints.gridwidth = 3;
        }
        else {
            gridBagConstraints.gridwidth = 0;
        }
        final Label label;
        (label = new Label(this.q + " (" + this.o + ")")).setFont(cb.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (b2) {
            (this.d = new ad(105, 20)).q(be.w("Remove"));
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
            if (this.q.q(8)) {
                final Label label2;
                (label2 = new Label(be.w("Display Name"))).setFont(cb.t);
                this.q.gridwidth = 3;
                this.q.setConstraints(label2, this.q);
                this.q.add(label2);
                (this.q = new TextField(this.w, 15)).setForeground(Color.black);
                this.q.setBackground(Color.white);
                gridBagConstraints.weightx = 1.0;
                this.q.fill = 1;
                this.q.gridwidth = 2;
                this.q.setConstraints(this.q, this.q);
                this.q.add(this.q);
                gridBagConstraints.weightx = 0.0;
                (this.t = new ad(70, 20)).q(be.w("Change"));
                this.t.resize(this.t.getFontMetrics(this.t.getFont()).stringWidth(be.w("Change")) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.t, this.q);
                this.q.add(this.t);
            }
            else {
                final Label label3 = new Label(this.w);
                final Label label4;
                (label4 = new Label(be.w("Display Name"))).setFont(cb.t);
                this.q.gridwidth = 3;
                this.q.setConstraints(label4, this.q);
                this.q.add(label4);
                this.q.gridwidth = 0;
                this.q.setConstraints(label3, this.q);
                this.q.add(label3);
            }
            b = true;
        }
        if (this.i != null && !"".equals(this.i)) {
            final Label label5;
            (label5 = new Label(be.w("Referer URL:"))).setFont(cb.t);
            this.q.gridwidth = 2;
            this.q.setConstraints(label5, this.q);
            this.q.add(label5);
            final Label label6 = new Label(this.i);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(label6, this.q);
            this.q.add(label6);
            b = true;
        }
        if (this.q.q(52)) {
            final Label label7;
            (label7 = new Label(be.w("Warn user"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label7, this.q);
            this.q.add(label7);
            (this.w = new TextField()).setForeground(Color.black);
            this.w.setBackground(Color.white);
            this.q.fill = 1;
            this.q.gridwidth = 2;
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            (this.y = new ad(70, 20)).q(be.w("Warn"));
            this.y.resize(this.y.getFontMetrics(this.y.getFont()).stringWidth(be.w("Warn")) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.y, this.q);
            this.q.add(this.y);
            b = true;
        }
        if (this.e != null) {
            final Label label8 = new Label(this.e);
            final Label label9;
            (label9 = new Label(be.w("Age"))).setFont(cb.t);
            this.q.gridwidth = -1;
            this.q.setConstraints(label9, this.q);
            this.q.add(label9);
            this.q.gridwidth = 0;
            this.q.setConstraints(label8, this.q);
            this.q.add(label8);
            b = true;
        }
        if (this.r != null) {
            final Label label10 = new Label(this.r);
            final Label label11;
            (label11 = new Label(be.w("Gender"))).setFont(cb.t);
            this.q.gridwidth = -1;
            this.q.setConstraints(label11, this.q);
            this.q.add(label11);
            this.q.gridwidth = 0;
            this.q.setConstraints(label10, this.q);
            this.q.add(label10);
            b = true;
        }
        if (this.a != null) {
            final Label label12 = new Label(this.a);
            final Label label13;
            (label13 = new Label(be.w("Country"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label13, this.q);
            this.q.add(label13);
            this.q.gridwidth = 2;
            this.q.setConstraints(label12, this.q);
            this.q.add(label12);
            (this.u = new ad(70, 20)).q(dX.S);
            this.u.resize(this.u.getFontMetrics(this.u.getFont()).stringWidth(dX.S) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.u, this.q);
            this.q.add(this.u);
            b = true;
        }
        if (this.p != null) {
            final Label label14 = new Label(this.p);
            final Label label15;
            (label15 = new Label(be.w("Online Time"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label15, this.q);
            this.q.add(label15);
            this.q.gridwidth = (this.q.q(9) ? 2 : 0);
            this.q.setConstraints(label14, this.q);
            this.q.add(label14);
            if (this.q.q(9)) {
                (this.i = new ad(70, 20)).q(dX.D);
                this.i.resize(this.i.getFontMetrics(this.i.getFont()).stringWidth(dX.D) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.i, this.q);
                this.q.add(this.i);
            }
            b = true;
        }
        if (this.q != null) {
            final boolean q = this.q.q(11);
            this.q = new u(this.u);
            final Label label16 = new Label(be.w("Room"));
            try {
                this.q.q(new URL("file:room"));
            }
            catch (MalformedURLException ex) {}
            label16.setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label16, this.q);
            this.q.add(label16);
            this.q.gridwidth = (q ? 2 : 0);
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            if (q) {
                (this.o = new ad(70, 20)).q(dX.F);
                this.o.resize(this.o.getFontMetrics(this.o.getFont()).stringWidth(dX.F) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.o, this.q);
                this.q.add(this.o);
            }
            b = true;
        }
        if (this.t != null) {
            final boolean b3 = (this.q.q(44) || this.q.q(52)) && !this.q.a.equalsIgnoreCase("Chatmaster");
            final Label label17 = new Label(this.t);
            final Label label18;
            (label18 = new Label(be.w("IP Address"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label18, this.q);
            this.q.add(label18);
            this.q.gridwidth = (b3 ? 2 : 0);
            this.q.setConstraints(label17, this.q);
            this.q.add(label17);
            if (b3) {
                (this.p = new ad(70, 20)).q(dX.Y);
                this.p.resize(this.p.getFontMetrics(this.p.getFont()).stringWidth(dX.Y) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.p, this.q);
                this.q.add(this.p);
            }
            b = true;
        }
        if (this.s != null) {
            final boolean q2 = this.q.q(49);
            final Label label19 = new Label(this.s);
            final Label label20;
            (label20 = new Label(be.w("Host Name"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label20, this.q);
            this.q.add(label20);
            this.q.gridwidth = (q2 ? 2 : 0);
            this.q.setConstraints(label19, this.q);
            this.q.add(label19);
            if (q2) {
                (this.a = new ad(70, 20)).q(dX.U);
                this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(dX.U) + 20, 20);
                this.q.fill = 1;
                this.q.gridwidth = 0;
                this.q.setConstraints(this.a, this.q);
                this.q.add(this.a);
            }
            b = true;
        }
        if ((this.q.q(3) && this.q.q() == this.q.s) || this.q.q(5)) {
            final Label label21;
            (label21 = new Label(be.w("Color"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label21, this.q);
            this.q.add(label21);
            this.q.fill = 0;
            this.q.gridwidth = 2;
            (this.q = new dS()).setBackground(new Color(this.q.w()));
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.gridwidth = 0;
            this.q.fill = 2;
            this.w.q(be.w("Set Color"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(be.w("Set Color")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            b = true;
        }
        if ((this.q.q(18) && !this.q.q(61)) || (this.q.s == this.q.q() && this.q.q(17))) {
            final Label label22;
            (label22 = new Label(be.w("Back Color"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label22, this.q);
            this.q.add(label22);
            this.q.fill = 0;
            this.q.gridwidth = 2;
            (this.w = new dS()).q(this.q.g);
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            this.q.gridwidth = 0;
            this.q.fill = 2;
            this.e.q(be.w("Set Back Color"));
            this.e.resize(this.e.getFontMetrics(this.e.getFont()).stringWidth(be.w("Set Background Color")) + 20, 20);
            this.q.setConstraints(this.e, this.q);
            this.q.add(this.e);
        }
        if (this.q.q(7)) {
            this.q.weightx = 0.0;
            this.q = new Choice();
            ((Component)(this.q = (M)this.q.y().clone())).setFont(cb.y);
            int n = 0;
            final FontMetrics fontMetrics = this.q.getFontMetrics(this.q.getFont());
            for (int i = 0; i < this.q.q; ++i) {
                this.q.addItem(q(((bp)this.q.q(i)).a, 240, fontMetrics));
                if (((bp)this.q.q(i)).s == this.q.r) {
                    n = i;
                }
            }
            this.q.setForeground(Color.black);
            final Label label23;
            (label23 = new Label(be.w("Room"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label23, this.q);
            this.q.add(label23);
            this.q.fill = 1;
            this.q.gridwidth = 2;
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.select(n);
            this.q.gridwidth = 0;
            this.r.q(be.w("Move To Room"));
            this.r.resize(this.r.getFontMetrics(this.r.getFont()).stringWidth(be.w("Move To Room")) + 20, 20);
            this.q.setConstraints(this.r, this.q);
            this.q.add(this.r);
            b = true;
        }
        if ((this.q.q(14) && !this.q.q(61)) || (this.q.q(52) && this.q.q(61))) {
            (this.q = new aq()).q();
            final cx cx;
            (cx = new cx(999, "No star")).q = a.cx.q(this);
            this.q.q(cx);
            for (int j = 0; j < this.q.q().q; ++j) {
                final cx cx2 = (cx)this.q.q().q(j);
                final de q3;
                if ((q3 = de.q(((cT)this.q).y, cx2.s)) != null && (this.q.q(61) || !q3.q(0))) {
                    this.q.q(cx2);
                }
            }
            if (this.q.t > 0) {
                this.q.q(this.q.t);
            }
            final Label label24;
            (label24 = new Label(be.w("Star"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label24, this.q);
            this.q.add(label24);
            this.q.gridwidth = 2;
            this.q.setConstraints(this.q, this.q);
            this.q.add(this.q);
            this.q.repaint();
            (this.f = new ad(70, 20)).q(be.w("Change"));
            this.f.resize(this.f.getFontMetrics(this.f.getFont()).stringWidth(this.f.q) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.f, this.q);
            this.q.add(this.f);
            b = true;
        }
        if ((this.q.q(10) && !this.q.q(61)) || (this.q.q(52) && this.q.q(61))) {
            (this.w = new aq()).q();
            for (int k = 0; k < this.q.t().q; ++k) {
                final aZ az;
                if ((az = (aZ)this.q.t().q(k)).s >= 0 && (az.q <= 0 || az.q == this.q.a)) {
                    this.w.q(az);
                }
            }
            if (this.q.e > 0) {
                this.w.q(this.q.e);
            }
            final Label label25;
            (label25 = new Label(be.w("Icon"))).setFont(cb.t);
            this.q.gridwidth = 3;
            this.q.setConstraints(label25, this.q);
            this.q.add(label25);
            this.q.gridwidth = 2;
            this.q.setConstraints(this.w, this.q);
            this.q.add(this.w);
            this.w.repaint();
            (this.g = new ad(70, 20)).q(be.w("Change"));
            this.g.resize(this.g.getFontMetrics(this.g.getFont()).stringWidth(this.g.q) + 20, 20);
            this.q.fill = 1;
            this.q.gridwidth = 0;
            this.q.setConstraints(this.g, this.q);
            this.q.add(this.g);
            b = true;
        }
        if (this.q.q(6) || this.q.q(12)) {
            if (this.q.q(6)) {
                final Label label26 = new Label(be.w(""));
                this.q.gridwidth = 3;
                this.q.setConstraints(label26, this.q);
                this.q.add(label26);
                this.q.gridwidth = 2;
                ((GridBagLayout)(this.q = new Checkbox(dX.A, this.q))).setConstraints(this.q, this.q);
                this.q.add(this.q);
            }
            if (this.q.q(12)) {
                (this.s = new ad(70, 20)).q(be.w("Give One Kick"));
                this.q.gridwidth = 0;
                this.q.add(this.s, this.q);
            }
            b = true;
        }
        return b;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final aG ag = new aG((dH)this.q, this.q.s);
                if (event.target == this.q) {
                    if (this.q.q(6) && this.q != null && this.q.getState() != this.q) {
                        ag.w(this.q.getState() ? "enable" : "disable");
                    }
                    this.dispose();
                    return true;
                }
                if (event.target == this.w) {
                    ag.w(this.q.q());
                    return true;
                }
                if (event.target == this.e) {
                    ag.e(this.w.q());
                    return true;
                }
                if (event.target == this.r) {
                    if (((cj)this.q.q(this.q.getSelectedIndex())).q != null) {
                        final db db;
                        (db = new db(this.w, this.q, (cj)this.q.q(this.q.getSelectedIndex()))).setVisible(true);
                        final String q;
                        if ((q = db.q) != null) {
                            ag.q(String.valueOf(((bp)this.q.q(this.q.getSelectedIndex())).s), q);
                        }
                    }
                    else {
                        ag.q(String.valueOf(((bp)this.q.q(this.q.getSelectedIndex())).s), null);
                    }
                    return true;
                }
                if (event.target == this.t) {
                    ag.e(this.q.getText());
                    return true;
                }
                if (event.target == this.y) {
                    ag.r(this.w.getText());
                    return true;
                }
                if (event.target == this.u) {
                    ag.q();
                    return true;
                }
                if (event.target == this.i) {
                    ag.w();
                    return true;
                }
                if (event.target == this.o) {
                    ag.t(this.q.a);
                    return true;
                }
                if (event.target == this.p) {
                    ag.e();
                    return true;
                }
                if (event.target == this.a) {
                    ag.r();
                    return true;
                }
                if (event.target == this.d) {
                    ag.t();
                    return true;
                }
                if (event.target == this.s) {
                    ag.y();
                    return true;
                }
                if (event.target == this.f) {
                    ag.r(this.q.q().s);
                    return true;
                }
                if (event.target == this.g) {
                    ag.t(this.w.q().s);
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
