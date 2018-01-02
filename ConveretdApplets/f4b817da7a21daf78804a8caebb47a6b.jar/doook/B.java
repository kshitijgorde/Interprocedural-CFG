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

public class B extends N
{
    private al c;
    private al e;
    private D b;
    protected aI a;
    private aW c;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.c.e();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.b) {
                    this.c.b(this.a.w);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.c.a((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.e) {
                    this.dispose();
                    this.c.a((aN)null, this.a);
                    return true;
                }
                if (event.target == this.c) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public B(final Frame frame, final aW c, final aI a, final V v, final int n) {
        super(frame, false);
        this.c = new al(70, 20);
        this.e = new al(115, 20);
        this.setBackground(c.c.c);
        this.a = a;
        this.c = c;
        final n n2 = new n();
        n2.setBackground(c.c.k);
        n2.setForeground(c.c.j);
        final ad ad = new ad();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final String c2 = c.c(a.g());
        final String c3 = c.c(v.a(n, 0));
        final String c4 = c.c(v.a(n, 1));
        final String c5 = c.c(v.a(n, 2));
        final String c6 = c.c(v.a(n, 3));
        String value = null;
        String s = null;
        String w = null;
        String x = null;
        String c7 = null;
        boolean b = false;
        final a a2 = (a)c.e.b(a.w);
        if (a2 != null) {
            c7 = c.c(a2.g());
        }
        final int a3 = v.a(n, 1);
        if (a3 != -999) {
            value = String.valueOf(a3);
        }
        if (v.a(n, 1)) {
            s = aG.a("Male");
        }
        else if (v.a(n, 0)) {
            s = aG.a("Female");
        }
        if (c.c(33)) {
            w = a.w;
            x = a.x;
            if (x != null && x.equals(w)) {
                x = null;
            }
        }
        final bh bh = (bh)c.c.b(a.v);
        this.setResizable(false);
        this.setTitle(aC.a(aG.a("Profile of %1"), new String[] { c2 }));
        this.setLayout(gridBagLayout);
        n2.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(3, 5, 3, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 1;
        if (bh != null) {
            final bp bp = new bp();
            bp.b(bh.b);
            gridBagLayout.setConstraints(bp, gridBagConstraints);
            n2.add(bp);
        }
        gridBagConstraints.gridwidth = 0;
        final Label label = new Label(c2);
        label.setFont(aK.e);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        n2.add(label);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 2;
        gridBagLayout.setConstraints(ad, gridBagConstraints);
        n2.add(ad);
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        if (c3 != null) {
            final Label label2 = new Label(c3);
            final Label label3 = new Label(aG.a("Real Name"));
            label3.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label3, gridBagConstraints);
            n2.add(label3);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label2, gridBagConstraints);
            n2.add(label2);
            b = true;
        }
        final av av = (av)this.c.s.b(v.a(n, 4));
        if (av != null && av.b.length() > 0) {
            final Label label4 = new Label(av.b);
            final Label label5 = new Label(aG.a("User Group"));
            label5.setFont(aK.g);
            gridBagConstraints.gridwidth = 3;
            gridBagLayout.setConstraints(label5, gridBagConstraints);
            n2.add(label5);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label4, gridBagConstraints);
            n2.add(label4);
            b = true;
        }
        if (value != null) {
            final Label label6 = new Label(value);
            final Label label7 = new Label(aG.a("Age"));
            label7.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label7, gridBagConstraints);
            n2.add(label7);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label6, gridBagConstraints);
            n2.add(label6);
            b = true;
        }
        if (s != null) {
            final Label label8 = new Label(s);
            final Label label9 = new Label(aG.a("Gender"));
            label9.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label9, gridBagConstraints);
            n2.add(label9);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label8, gridBagConstraints);
            n2.add(label8);
            b = true;
        }
        if (c6 != null) {
            final D d = new D(c6);
            final Label label10 = new Label(aG.a("E-mail"));
            label10.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label10, gridBagConstraints);
            n2.add(label10);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(d, gridBagConstraints);
            try {
                String s2 = c6.toLowerCase();
                if (!s2.startsWith("mailto:")) {
                    s2 = "mailto:" + s2;
                }
                d.a(new URL(s2));
            }
            catch (MalformedURLException ex2) {}
            n2.add(d);
            b = true;
        }
        if (c5 != null) {
            final D d2 = new D(c5);
            final Label label11 = new Label(aG.a("URL"));
            label11.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label11, gridBagConstraints);
            n2.add(label11);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(d2, gridBagConstraints);
            try {
                d2.a(new URL(c5));
            }
            catch (MalformedURLException ex3) {}
            n2.add(d2);
            b = true;
        }
        if (c4 != null) {
            final D d3 = new D(c4);
            final Label label12 = new Label(aG.a("Comments"));
            label12.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label12, gridBagConstraints);
            n2.add(label12);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(d3, gridBagConstraints);
            n2.add(d3);
            b = true;
        }
        if (c7 != null) {
            this.b = new D(c7);
            final Label label13 = new Label(aG.a("Room"));
            try {
                this.b.a(new URL("file:room"));
            }
            catch (MalformedURLException ex) {
                ex.printStackTrace();
            }
            label13.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label13, gridBagConstraints);
            n2.add(label13);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            n2.add(this.b);
            b = true;
        }
        if (w != null) {
            final Label label14 = new Label(w);
            final Label label15 = new Label(aG.a("IP Address"));
            label15.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label15, gridBagConstraints);
            n2.add(label15);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label14, gridBagConstraints);
            n2.add(label14);
            b = true;
        }
        if (x != null) {
            final Label label16 = new Label(x);
            final Label label17 = new Label(aG.a("Host Name"));
            label17.setFont(aK.g);
            gridBagConstraints.gridwidth = -1;
            gridBagLayout.setConstraints(label17, gridBagConstraints);
            n2.add(label17);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label16, gridBagConstraints);
            n2.add(label16);
            b = true;
        }
        if (!b) {
            final Label label18 = new Label(aG.a("No profile is available."));
            label18.setFont(aK.g);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(label18, gridBagConstraints);
            n2.add(label18);
        }
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagLayout.setConstraints(n2, gridBagConstraints);
        this.add(n2);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        if (c.c(43)) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.e.a(aG.a("Send Message"));
            this.e.resize(this.e.getFontMetrics(this.e.getFont()).stringWidth(aG.a("Send Message")) + 20, 20);
            gridBagLayout.setConstraints(this.e, gridBagConstraints);
            this.add(this.e);
            gridBagConstraints.weightx = 0.0;
        }
        gridBagConstraints.gridwidth = 0;
        this.c.a(aG.a("OK"));
        this.c.resize(this.c.getFontMetrics(this.c.getFont()).stringWidth(aG.a("OK")) + 20, 20);
        final P p5 = new P(this.c);
        gridBagLayout.setConstraints(p5, gridBagConstraints);
        this.add(p5);
        this.pack();
        this.setVisible(true);
    }
}
