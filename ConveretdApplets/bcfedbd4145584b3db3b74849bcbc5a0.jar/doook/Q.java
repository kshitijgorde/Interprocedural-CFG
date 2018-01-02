// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.net.MalformedURLException;
import java.awt.Label;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;

public class Q extends aA
{
    private ax a;
    private ax b;
    private R b;
    protected F a;
    private at d;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.a.l();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    this.d.d(this.a.t);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.d.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.b) {
                    this.dispose();
                    this.d.a((bv)null, this.a);
                    return true;
                }
                if (event.target == this.a) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public Q(final Frame frame, final at d, final F a, final aJ aj, final int n) {
        super(frame, false);
        this.a = new ax(70, 20);
        this.b = new ax(115, 20);
        this.setBackground(d.b.a);
        this.a = a;
        this.d = d;
        final aC ac = new aC();
        ac.setBackground(d.b.g);
        ac.setForeground(d.b.f);
        final K k = new K();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String e = d.e(a.d());
        final String e2 = d.e(aj.a(n, 0));
        final String e3 = d.e(aj.a(n, 1));
        final String e4 = d.e(aj.a(n, 2));
        final String e5 = d.e(aj.a(n, 3));
        String value = null;
        String s = null;
        String l = null;
        String d2 = null;
        String e6 = null;
        boolean b = false;
        final aW aw = (aW)d.f.b(a.t);
        if (aw != null) {
            e6 = d.e(aw.d());
        }
        final int b2 = aj.b(n, 1);
        if (b2 != -999) {
            value = String.valueOf(b2);
        }
        if (aj.a(n, 1)) {
            s = ar.b("Male");
        }
        else if (aj.a(n, 0)) {
            s = ar.b("Female");
        }
        if (d.a(33)) {
            l = a.l;
            d2 = a.d;
            if (d2 != null && d2.equals(l)) {
                d2 = null;
            }
        }
        final af af = (af)d.d.b(a.c);
        this.setResizable(false);
        this.setTitle(H.a(ar.b("Profile of %1"), new String[] { e }));
        this.setLayout(gridBagLayout);
        ac.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (af != null) {
            final M m = new M();
            m.b(af.a);
            gridBagLayout.setConstraints(m, gridBagConstraints);
            ac.add(m);
        }
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(e);
        label.setFont(ay.d);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        ac.add(label);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        ac.add(k);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (e2 != null) {
            final Label label2 = new Label(e2);
            final Label label3 = new Label(ar.b("Real Name"));
            label3.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            ac.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            ac.add(label2);
            b = true;
        }
        final l i = (l)this.d.t.b(aj.b(n, 4));
        if (i != null && i.description.length() > 0) {
            final Label label4 = new Label(i.description);
            final Label label5 = new Label(ar.b("User Group"));
            label5.setFont(ay.b);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            ac.add(label5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            ac.add(label4);
            b = true;
        }
        if (value != null) {
            final Label label6 = new Label(value);
            final Label label7 = new Label(ar.b("Age"));
            label7.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label7, gridBagConstraints);
            ac.add(label7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label6, gridBagConstraints);
            ac.add(label6);
            b = true;
        }
        if (s != null) {
            final Label label8 = new Label(s);
            final Label label9 = new Label(ar.b("Gender"));
            label9.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label9, gridBagConstraints);
            ac.add(label9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label8, gridBagConstraints);
            ac.add(label8);
            b = true;
        }
        if (e5 != null) {
            final R r = new R(e5);
            final Label label10 = new Label(ar.b("E-mail"));
            label10.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label10, gridBagConstraints);
            ac.add(label10);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(r, gridBagConstraints);
            try {
                String s2 = e5.toLowerCase();
                if (!s2.startsWith("mailto:")) {
                    s2 = "mailto:" + s2;
                }
                r.a(new URL(s2));
            }
            catch (MalformedURLException ex2) {}
            ac.add(r);
            b = true;
        }
        if (e4 != null) {
            final R r2 = new R(e4);
            final Label label11 = new Label(ar.b("URL"));
            label11.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label11, gridBagConstraints);
            ac.add(label11);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(r2, gridBagConstraints);
            try {
                r2.a(new URL(e4));
            }
            catch (MalformedURLException ex3) {}
            ac.add(r2);
            b = true;
        }
        if (e3 != null) {
            final R r3 = new R(e3);
            final Label label12 = new Label(ar.b("Comments"));
            label12.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label12, gridBagConstraints);
            ac.add(label12);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(r3, gridBagConstraints);
            ac.add(r3);
            b = true;
        }
        if (e6 != null) {
            this.b = new R(e6);
            final Label label13 = new Label(ar.b("Room"));
            try {
                this.b.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label13.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label13, gridBagConstraints);
            ac.add(label13);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            ac.add(this.b);
            b = true;
        }
        if (l != null) {
            final Label label14 = new Label(l);
            final Label label15 = new Label(ar.b("IP Address"));
            label15.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label15, gridBagConstraints);
            ac.add(label15);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label14, gridBagConstraints);
            ac.add(label14);
            b = true;
        }
        if (d2 != null) {
            final Label label16 = new Label(d2);
            final Label label17 = new Label(ar.b("Host Name"));
            label17.setFont(ay.b);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label17, gridBagConstraints);
            ac.add(label17);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label16, gridBagConstraints);
            ac.add(label16);
            b = true;
        }
        if (!b) {
            final Label label18 = new Label(ar.b("No profile is available."));
            label18.setFont(ay.b);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label18, gridBagConstraints);
            ac.add(label18);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (d.a(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(ar.b("Send Message"));
            this.b.resize(this.b.getFontMetrics(this.b.getFont()).stringWidth(ar.b("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.a.a(ar.b("OK"));
        this.a.resize(this.a.getFontMetrics(this.a.getFont()).stringWidth(ar.b("OK")) + 20, 20);
        final au au = new au(this.a);
        gridBagLayout.setConstraints(au, gridBagConstraints);
        this.add(au);
        this.pack();
        this.setVisible(true);
    }
}
