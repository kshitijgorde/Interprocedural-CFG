// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.MalformedURLException;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Frame;
import java.net.URL;
import java.awt.Event;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class j extends F
{
    protected ad q;
    private ad w;
    protected u q;
    protected p q;
    public cs q;
    public p w;
    protected dT q;
    protected q q;
    protected GridBagConstraints q;
    protected GridBagLayout q;
    protected String q;
    protected String w;
    private String o;
    private String p;
    private String a;
    protected String e;
    protected String r;
    protected String t;
    protected String y;
    protected String u;
    protected String i;
    protected cB q;
    protected aZ q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == 27) {
                    this.q.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.q(this.q.r);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.q.q((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.w) {
                    this.dispose();
                    ((dH)this.q).q(null, this.q);
                    return true;
                }
                if (event.target == this.q) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public j(final Frame frame, final dH q, final p q2, final dI di, final int n) {
        super(frame, false);
        this.q = new ad(70, 20);
        this.w = new ad(115, 20);
        this.setBackground(bC.w.q);
        this.q = q2;
        this.q = q;
        (this.q = new dT()).setBackground(bC.w.i);
        this.q.setForeground(bC.w.u);
        this.q = new q();
        this.q = new GridBagConstraints();
        this.q = new GridBagLayout();
        this.q = this.q.q(q2.a);
        this.o = this.q.q(di.q(n, 1));
        this.y = this.q.q(di.q(n, 3));
        this.a = null;
        this.e = null;
        this.r = null;
        this.t = null;
        this.y = null;
        this.u = null;
        this.i = di.q(0, 4);
        this.q = (cB)this.q.y().w(q2.r);
        if (this.q != null) {
            this.u = this.q.q(this.q.a);
        }
        if (!q2.q) {
            if (q2.q > 0) {
                this.e = String.valueOf(q2.q);
            }
            if (q2.w == 1) {
                this.r = be.w("Male");
            }
            else if (q2.w == 2) {
                this.r = be.w("Female");
            }
            this.w = q2.a;
            final int lastIndex;
            if ((lastIndex = this.w.lastIndexOf(".")) >= 0 && lastIndex + 4 == this.w.length()) {
                this.w = this.w.substring(0, lastIndex);
            }
            this.p = q2.e;
            this.a = q2.r;
            this.o = q2.t;
        }
        this.q = (aZ)this.q.t().w(q2.e);
        this.setResizable(false);
        this.setTitle(B.q(be.w("Profile of %1"), new String[] { this.w }));
        this.setLayout(this.q);
        this.q.setLayout(this.q);
        this.q.insets = new Insets(3, 5, 3, 5);
        this.q.anchor = 18;
        this.q.gridwidth = 1;
    }
    
    public void setVisible(final boolean visible) {
        if (!this.q()) {
            final Label label;
            (label = new Label(be.w("No profile is available."))).setFont(cb.t);
            this.q.gridwidth = 0;
            this.q.setConstraints(label, this.q);
            this.q.add(label);
        }
        this.q.weightx = 1.0;
        this.q.fill = 1;
        this.q.gridwidth = 0;
        this.q.gridheight = -1;
        this.q.setConstraints(this.q, this.q);
        this.add(this.q);
        this.q.fill = 0;
        this.q.anchor = 13;
        if (this.q.q(43)) {
            this.q.weightx = 1.0;
            this.q.gridwidth = -1;
            this.w.q(be.w("Send Message"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(be.w("Send Message")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.add(this.w);
            this.q.weightx = 0.0;
        }
        this.q.gridwidth = 0;
        this.q.q(be.w("OK"));
        this.q.resize(this.q.getFontMetrics(this.q.getFont()).stringWidth(be.w("OK")) + 20, 20);
        final as as = new as(this.q);
        this.q.setConstraints(as, this.q);
        this.add(as);
        this.pack();
        if (dN.q()) {
            this.invalidate();
            final Dimension size;
            if ((size = this.getSize()).width > 500 && !ap.t()) {
                size.width = 500;
            }
            if (size.height > 550) {
                size.height = 550;
            }
            this.setBounds(10, 10, size.width, size.height);
            this.validate();
        }
        super.setVisible(visible);
    }
    
    protected boolean q() {
        boolean b = false;
        if (this.q != null) {
            final bl bl;
            (bl = new bl()).w(this.q.q);
            this.q.setConstraints(bl, this.q);
            this.q.add(bl);
        }
        this.q.gridwidth = 0;
        final Label label;
        (label = new Label(this.w)).setFont(cb.q);
        this.q.setConstraints(label, this.q);
        this.q.add(label);
        this.q.weightx = 1.0;
        this.q.fill = 2;
        this.q.setConstraints(this.q, this.q);
        this.q.add(this.q);
        this.q.fill = 0;
        this.q.weightx = 0.0;
        if (this.q.q) {
            final Label label2;
            (label2 = new Label(be.w("This user blocks its profile."))).setFont(cb.q);
            this.q.add(label2);
            b = true;
        }
        else {
            if (this.w != null && this.w.trim().length() > 0) {
                final Label label3 = new Label(this.w);
                final Label label4;
                (label4 = new Label(be.w("Real Name"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label4, this.q);
                this.q.add(label4);
                this.q.gridwidth = 0;
                this.q.setConstraints(label3, this.q);
                this.q.add(label3);
                b = true;
            }
            if (this.e != null && this.e.trim().length() > 0) {
                final Label label5 = new Label(this.e);
                final Label label6;
                (label6 = new Label(be.w("Age"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label6, this.q);
                this.q.add(label6);
                this.q.gridwidth = 0;
                this.q.setConstraints(label5, this.q);
                this.q.add(label5);
                b = true;
            }
            if (this.r != null && this.r.trim().length() > 0) {
                final Label label7 = new Label(this.r);
                final Label label8;
                (label8 = new Label(be.w("Gender"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label8, this.q);
                this.q.add(label8);
                this.q.gridwidth = 0;
                this.q.setConstraints(label7, this.q);
                this.q.add(label7);
                b = true;
            }
            if (this.a != null && this.a.trim().length() > 0) {
                final u u = new u(this.a);
                final Label label9;
                (label9 = new Label(be.w("E-mail"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label9, this.q);
                this.q.add(label9);
                this.q.gridwidth = 0;
                this.q.setConstraints(u, this.q);
                try {
                    String s;
                    if (!(s = this.a.toLowerCase()).startsWith("mailto:")) {
                        s = "mailto:" + s;
                    }
                    u.q(new URL(s));
                }
                catch (MalformedURLException ex) {}
                this.q.add(u);
                b = true;
            }
            if (this.p != null && this.p.trim().length() > 0) {
                final u u2 = new u(this.p);
                final Label label10;
                (label10 = new Label(be.w("URL"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label10, this.q);
                this.q.add(label10);
                this.q.gridwidth = 0;
                this.q.setConstraints(u2, this.q);
                try {
                    u2.q(new URL(this.p));
                }
                catch (MalformedURLException ex2) {}
                this.q.add(u2);
                b = true;
            }
            if (this.o != null && this.o.trim().length() > 0) {
                final u u3 = new u(this.o);
                final Label label11;
                (label11 = new Label(be.w("Comments"))).setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label11, this.q);
                this.q.add(label11);
                this.q.gridwidth = 0;
                this.q.setConstraints(u3, this.q);
                this.q.add(u3);
                b = true;
            }
            if (this.u != null) {
                this.q = new u(this.u);
                final Label label12 = new Label(be.w("Room"));
                try {
                    this.q.q(new URL("file:room"));
                }
                catch (MalformedURLException ex3) {}
                label12.setFont(cb.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label12, this.q);
                this.q.add(label12);
                this.q.gridwidth = 0;
                this.q.setConstraints(this.q, this.q);
                this.q.add(this.q);
                b = true;
            }
        }
        return b;
    }
}
