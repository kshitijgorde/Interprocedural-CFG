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

public class dh extends ah
{
    protected g q;
    private g w;
    protected H q;
    protected cz q;
    public cM q;
    public cz w;
    protected s q;
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
    protected cr q;
    protected cm q;
    
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
                    this.q.q(this.q.o);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.q.q((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.w) {
                    this.dispose();
                    ((cU)this.q).q(null, this.q);
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
    
    public dh(final Frame frame, final cU q, final cz q2, final es es, final int n) {
        super(frame, false);
        this.q = new g(70, 20);
        this.w = new g(115, 20);
        this.setBackground(cf.w.q);
        this.q = q2;
        this.q = q;
        (this.q = new s()).setBackground(cf.w.i);
        this.q.setForeground(cf.w.u);
        this.q = new q();
        this.q = new GridBagConstraints();
        this.q = new GridBagLayout();
        this.q = this.q.q(q2.getName());
        this.o = this.q.q(es.q(n, 1));
        this.y = this.q.q(es.q(n, 3));
        this.a = null;
        this.e = null;
        this.r = null;
        this.t = null;
        this.y = null;
        this.u = null;
        this.i = es.q(0, 4);
        this.q = (cr)this.q.t().w(q2.o);
        if (this.q != null) {
            this.u = this.q.q(this.q.getName());
        }
        if (!q2.w) {
            if (q2.q > 0) {
                this.e = String.valueOf(q2.q);
            }
            if (q2.w == 1) {
                this.r = eb.q("Male");
            }
            else if (q2.w == 2) {
                this.r = eb.q("Female");
            }
            this.w = q2.getName();
            final int lastIndex;
            if ((lastIndex = this.w.lastIndexOf(".")) >= 0 && lastIndex + 4 == this.w.length()) {
                this.w = this.w.substring(0, lastIndex);
            }
            this.p = q2.e;
            this.a = q2.r;
            this.o = q2.y;
        }
        this.q = (cm)this.q.r().w(q2.e);
        this.setResizable(false);
        this.setTitle(ec.q(eb.q("Profile of %1"), new String[] { this.w }));
        this.setLayout(this.q);
        this.q.setLayout(this.q);
        this.q.insets = new Insets(3, 5, 3, 5);
        this.q.anchor = 18;
        this.q.gridwidth = 1;
    }
    
    public void setVisible(final boolean visible) {
        if (!this.q()) {
            final Label label;
            (label = new Label(eb.q("No profile is available."))).setFont(m.t);
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
            this.w.q(eb.q("Send Message"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(eb.q("Send Message")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.add(this.w);
            this.q.weightx = 0.0;
        }
        this.q.gridwidth = 0;
        this.q.q(eb.q("OK"));
        this.q.resize(this.q.getFontMetrics(this.q.getFont()).stringWidth(eb.q("OK")) + 20, 20);
        final f f = new f(this.q);
        this.q.setConstraints(f, this.q);
        this.add(f);
        this.pack();
        if (a.a.q()) {
            this.invalidate();
            final Dimension size;
            if ((size = this.getSize()).width > 500 && !cV.s()) {
                size.width = 500;
            }
            this.setBounds(10, 10, size.width, size.height);
            this.validate();
        }
        super.setVisible(visible);
    }
    
    protected boolean q() {
        boolean b = false;
        if (this.q != null) {
            final d d;
            (d = new d()).w(this.q.q);
            this.q.setConstraints(d, this.q);
            this.q.add(d);
        }
        this.q.gridwidth = 0;
        final Label label;
        (label = new Label(this.w)).setFont(m.q);
        this.q.setConstraints(label, this.q);
        this.q.add(label);
        this.q.weightx = 1.0;
        this.q.fill = 2;
        this.q.setConstraints(this.q, this.q);
        this.q.add(this.q);
        this.q.fill = 0;
        this.q.weightx = 0.0;
        if (this.q.w) {
            final Label label2;
            (label2 = new Label(eb.q("This user blocks its profile."))).setFont(m.q);
            this.q.add(label2);
            b = true;
        }
        else {
            if (this.w != null && this.w.trim().length() > 0) {
                final Label label3 = new Label(this.w);
                final Label label4;
                (label4 = new Label(eb.q("Real Name"))).setFont(m.t);
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
                (label6 = new Label(eb.q("Age"))).setFont(m.t);
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
                (label8 = new Label(eb.q("Gender"))).setFont(m.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label8, this.q);
                this.q.add(label8);
                this.q.gridwidth = 0;
                this.q.setConstraints(label7, this.q);
                this.q.add(label7);
                b = true;
            }
            if (this.a != null && this.a.trim().length() > 0) {
                final H h = new H(this.a);
                final Label label9;
                (label9 = new Label(eb.q("E-mail"))).setFont(m.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label9, this.q);
                this.q.add(label9);
                this.q.gridwidth = 0;
                this.q.setConstraints(h, this.q);
                try {
                    String s;
                    if (!(s = this.a.toLowerCase()).startsWith("mailto:")) {
                        s = "mailto:" + s;
                    }
                    h.q(new URL(s));
                }
                catch (MalformedURLException ex2) {}
                this.q.add(h);
                b = true;
            }
            if (this.p != null && this.p.trim().length() > 0) {
                final H h2 = new H(this.p);
                final Label label10;
                (label10 = new Label(eb.q("URL"))).setFont(m.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label10, this.q);
                this.q.add(label10);
                this.q.gridwidth = 0;
                this.q.setConstraints(h2, this.q);
                try {
                    h2.q(new URL(this.p));
                }
                catch (MalformedURLException ex3) {}
                this.q.add(h2);
                b = true;
            }
            if (this.o != null && this.o.trim().length() > 0) {
                final H h3 = new H(this.o);
                final Label label11;
                (label11 = new Label(eb.q("Comments"))).setFont(m.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label11, this.q);
                this.q.add(label11);
                this.q.gridwidth = 0;
                this.q.setConstraints(h3, this.q);
                this.q.add(h3);
                b = true;
            }
            if (this.u != null) {
                this.q = new H(this.u);
                final Label label12 = new Label(eb.q("Room"));
                try {
                    this.q.q(new URL("file:room"));
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                label12.setFont(m.t);
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
