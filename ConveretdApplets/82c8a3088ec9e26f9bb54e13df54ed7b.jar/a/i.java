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

public final class i extends v
{
    private M q;
    private M w;
    private p q;
    private l q;
    private bp q;
    private cw q;
    private m q;
    private GridBagConstraints q;
    private GridBagLayout q;
    private String q;
    private String w;
    private String e;
    private String r;
    private String t;
    private String y;
    private String u;
    private bw q;
    private av q;
    
    public final boolean handleEvent(final Event event) {
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
                    ((co)this.q).q(null, this.q);
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
    
    public i(final Frame frame, final co q, final l q2, final cp cp, final int n) {
        super(frame, false);
        this.q = new M(70, 20);
        this.w = new M(115, 20);
        this.setBackground(aS.w.q);
        this.q = q2;
        this.q = q;
        (this.q = new cw()).setBackground(aS.w.i);
        this.q.setForeground(aS.w.u);
        this.q = new m();
        this.q = new GridBagConstraints();
        ((bp)(this.q = new GridBagLayout())).q(q2.o);
        this.w = this.q.q(cp.q(n, 1));
        this.q.q(cp.q(n, 3));
        this.r = null;
        this.t = null;
        this.y = null;
        this.u = null;
        cp.q(0, 4);
        this.q = (bw)this.q.r().w(q2.r);
        if (this.q != null) {
            this.u = this.q.q(this.q.o);
        }
        if (!q2.q) {
            if (q2.q > 0) {
                this.t = String.valueOf(q2.q);
            }
            if (q2.w == 1) {
                this.y = ak.q("Male");
            }
            else if (q2.w == 2) {
                this.y = ak.q("Female");
            }
            this.q = q2.o;
            final int lastIndex;
            if ((lastIndex = this.q.lastIndexOf(".")) >= 0 && lastIndex + 4 == this.q.length()) {
                this.q = this.q.substring(0, lastIndex);
            }
            this.e = q2.w;
            this.r = q2.e;
            this.w = q2.r;
        }
        this.q = (av)this.q.e().w(q2.e);
        this.setResizable(false);
        this.setTitle(s.q(ak.q("Profile of %1"), new String[] { this.q }));
        this.setLayout(this.q);
        this.q.setLayout(this.q);
        this.q.insets = new Insets(3, 5, 3, 5);
        this.q.anchor = 18;
        this.q.gridwidth = 1;
    }
    
    public final void setVisible(final boolean visible) {
        if (!this.q()) {
            final Label label;
            (label = new Label(ak.q("No profile is available."))).setFont(bd.t);
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
            this.w.q(ak.q("Send Message"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(ak.q("Send Message")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.add(this.w);
            this.q.weightx = 0.0;
        }
        this.q.gridwidth = 0;
        this.q.q(ak.q("OK"));
        this.q.resize(this.q.getFontMetrics(this.q.getFont()).stringWidth(ak.q("OK")) + 20, 20);
        final Z z = new Z(this.q);
        this.q.setConstraints(z, this.q);
        this.add(z);
        this.pack();
        if (cs.q()) {
            this.invalidate();
            final Dimension size;
            if ((size = this.getSize()).width > 500 && !W.e()) {
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
    
    private boolean q() {
        boolean b = false;
        if (this.q != null) {
            final aG ag;
            (ag = new aG()).w(this.q.q);
            this.q.setConstraints(ag, this.q);
            this.q.add(ag);
        }
        this.q.gridwidth = 0;
        final Label label;
        (label = new Label(this.q)).setFont(bd.q);
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
            (label2 = new Label(ak.q("This user blocks its profile."))).setFont(bd.q);
            this.q.add(label2);
            b = true;
        }
        else {
            if (this.q != null && this.q.trim().length() > 0) {
                final Label label3 = new Label(this.q);
                final Label label4;
                (label4 = new Label(ak.q("Real Name"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label4, this.q);
                this.q.add(label4);
                this.q.gridwidth = 0;
                this.q.setConstraints(label3, this.q);
                this.q.add(label3);
                b = true;
            }
            if (this.t != null && this.t.trim().length() > 0) {
                final Label label5 = new Label(this.t);
                final Label label6;
                (label6 = new Label(ak.q("Age"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label6, this.q);
                this.q.add(label6);
                this.q.gridwidth = 0;
                this.q.setConstraints(label5, this.q);
                this.q.add(label5);
                b = true;
            }
            if (this.y != null && this.y.trim().length() > 0) {
                final Label label7 = new Label(this.y);
                final Label label8;
                (label8 = new Label(ak.q("Gender"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label8, this.q);
                this.q.add(label8);
                this.q.gridwidth = 0;
                this.q.setConstraints(label7, this.q);
                this.q.add(label7);
                b = true;
            }
            if (this.r != null && this.r.trim().length() > 0) {
                final p p = new p(this.r);
                final Label label9;
                (label9 = new Label(ak.q("E-mail"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label9, this.q);
                this.q.add(label9);
                this.q.gridwidth = 0;
                this.q.setConstraints(p, this.q);
                try {
                    String s;
                    if (!(s = this.r.toLowerCase()).startsWith("mailto:")) {
                        s = "mailto:" + s;
                    }
                    p.q(new URL(s));
                }
                catch (MalformedURLException ex) {}
                this.q.add(p);
                b = true;
            }
            if (this.e != null && this.e.trim().length() > 0) {
                final p p2 = new p(this.e);
                final Label label10;
                (label10 = new Label(ak.q("URL"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label10, this.q);
                this.q.add(label10);
                this.q.gridwidth = 0;
                this.q.setConstraints(p2, this.q);
                try {
                    p2.q(new URL(this.e));
                }
                catch (MalformedURLException ex2) {}
                this.q.add(p2);
                b = true;
            }
            if (this.w != null && this.w.trim().length() > 0) {
                final p p3 = new p(this.w);
                final Label label11;
                (label11 = new Label(ak.q("Comments"))).setFont(bd.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label11, this.q);
                this.q.add(label11);
                this.q.gridwidth = 0;
                this.q.setConstraints(p3, this.q);
                this.q.add(p3);
                b = true;
            }
            if (this.u != null) {
                this.q = new p(this.u);
                final Label label12 = new Label(ak.q("Room"));
                try {
                    this.q.q(new URL("file:room"));
                }
                catch (MalformedURLException ex3) {}
                label12.setFont(bd.t);
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
