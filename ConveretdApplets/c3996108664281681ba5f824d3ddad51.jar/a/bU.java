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

public final class bU extends W
{
    private e q;
    private e w;
    private E q;
    private bp q;
    private bz q;
    private q q;
    private o q;
    private GridBagConstraints q;
    private GridBagLayout q;
    private String q;
    private String w;
    private String e;
    private String r;
    private String t;
    private String y;
    private String u;
    private bk q;
    private bj q;
    
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
                    this.q.q(this.q.i);
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.q.q((URL)event.arg, "_blank");
                    return true;
                }
                if (event.target == this.w) {
                    this.dispose();
                    ((bH)this.q).q(null, this.q);
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
    
    public bU(final Frame frame, final bH q, final bp q2, final cJ cj, final int n) {
        super(frame, false);
        this.q = new e(70, 20);
        this.w = new e(115, 20);
        this.setBackground(be.w.q);
        this.q = q2;
        this.q = q;
        (this.q = new q()).setBackground(be.w.i);
        this.q.setForeground(be.w.u);
        this.q = new o();
        this.q = new GridBagConstraints();
        ((bz)(this.q = new GridBagLayout())).q(q2.getName());
        this.w = this.q.q(cj.q(n, 1));
        this.q.q(cj.q(n, 3));
        this.r = null;
        this.t = null;
        this.y = null;
        this.u = null;
        cj.q(0, 4);
        this.q = (bk)this.q.e().w(q2.i);
        if (this.q != null) {
            this.u = this.q.q(this.q.getName());
        }
        if (!q2.q) {
            if (q2.t > 0) {
                this.t = String.valueOf(q2.t);
            }
            if (q2.y == 1) {
                this.y = cv.q("Male");
            }
            else if (q2.y == 2) {
                this.y = cv.q("Female");
            }
            this.q = q2.getName();
            final int lastIndex;
            if ((lastIndex = this.q.lastIndexOf(".")) >= 0 && lastIndex + 4 == this.q.length()) {
                this.q = this.q.substring(0, lastIndex);
            }
            this.e = q2.w;
            this.r = q2.r;
            this.w = q2.t;
        }
        this.q = (bj)this.q.w().w(q2.u);
        this.setResizable(false);
        this.setTitle(cv.q(cv.q("Profile of %1"), new String[] { this.q }));
        this.setLayout(this.q);
        this.q.setLayout(this.q);
        this.q.insets = new Insets(3, 5, 3, 5);
        this.q.anchor = 18;
        this.q.gridwidth = 1;
    }
    
    public final void setVisible(final boolean visible) {
        if (!this.q()) {
            final Label label;
            (label = new Label(cv.q("No profile is available."))).setFont(k.t);
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
            this.w.q(cv.q("Send Message"));
            this.w.resize(this.w.getFontMetrics(this.w.getFont()).stringWidth(cv.q("Send Message")) + 20, 20);
            this.q.setConstraints(this.w, this.q);
            this.add(this.w);
            this.q.weightx = 0.0;
        }
        this.q.gridwidth = 0;
        this.q.q(cv.q("OK"));
        this.q.resize(this.q.getFontMetrics(this.q.getFont()).stringWidth(cv.q("OK")) + 20, 20);
        final d d = new d(this.q);
        this.q.setConstraints(d, this.q);
        this.add(d);
        this.pack();
        if (a.q()) {
            this.invalidate();
            final Dimension size;
            if ((size = this.getSize()).width > 500 && !bI.a()) {
                size.width = 500;
            }
            this.setBounds(10, 10, size.width, size.height);
            this.validate();
        }
        super.setVisible(visible);
    }
    
    private boolean q() {
        boolean b = false;
        if (this.q != null) {
            final c c;
            (c = new c()).w(this.q.q);
            this.q.setConstraints(c, this.q);
            this.q.add(c);
        }
        this.q.gridwidth = 0;
        final Label label;
        (label = new Label(this.q)).setFont(k.q);
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
            (label2 = new Label(cv.q("This user blocks its profile."))).setFont(k.q);
            this.q.add(label2);
            b = true;
        }
        else {
            if (this.q != null && this.q.trim().length() > 0) {
                final Label label3 = new Label(this.q);
                final Label label4;
                (label4 = new Label(cv.q("Real Name"))).setFont(k.t);
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
                (label6 = new Label(cv.q("Age"))).setFont(k.t);
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
                (label8 = new Label(cv.q("Gender"))).setFont(k.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label8, this.q);
                this.q.add(label8);
                this.q.gridwidth = 0;
                this.q.setConstraints(label7, this.q);
                this.q.add(label7);
                b = true;
            }
            if (this.r != null && this.r.trim().length() > 0) {
                final E e = new E(this.r);
                final Label label9;
                (label9 = new Label(cv.q("E-mail"))).setFont(k.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label9, this.q);
                this.q.add(label9);
                this.q.gridwidth = 0;
                this.q.setConstraints(e, this.q);
                try {
                    String s;
                    if (!(s = this.r.toLowerCase()).startsWith("mailto:")) {
                        s = "mailto:" + s;
                    }
                    e.q(new URL(s));
                }
                catch (MalformedURLException ex2) {}
                this.q.add(e);
                b = true;
            }
            if (this.e != null && this.e.trim().length() > 0) {
                final E e2 = new E(this.e);
                final Label label10;
                (label10 = new Label(cv.q("URL"))).setFont(k.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label10, this.q);
                this.q.add(label10);
                this.q.gridwidth = 0;
                this.q.setConstraints(e2, this.q);
                try {
                    e2.q(new URL(this.e));
                }
                catch (MalformedURLException ex3) {}
                this.q.add(e2);
                b = true;
            }
            if (this.w != null && this.w.trim().length() > 0) {
                final E e3 = new E(this.w);
                final Label label11;
                (label11 = new Label(cv.q("Comments"))).setFont(k.t);
                this.q.gridwidth = -1;
                this.q.setConstraints(label11, this.q);
                this.q.add(label11);
                this.q.gridwidth = 0;
                this.q.setConstraints(e3, this.q);
                this.q.add(e3);
                b = true;
            }
            if (this.u != null) {
                this.q = new E(this.u);
                final Label label12 = new Label(cv.q("Room"));
                try {
                    this.q.q(new URL("file:room"));
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                }
                label12.setFont(k.t);
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
