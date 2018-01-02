// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.Event;
import java.awt.Canvas;

public final class J extends s implements cG
{
    private M q;
    private bI q;
    private bC q;
    private cq q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private K q;
    private boolean q;
    private int q;
    private Q q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (be.w.h()) {
            q = cc.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new e(70, 20);
            }
            else {
                q = new e(s3, 70, 20);
            }
            ((e)q).q(s4, null);
        }
        else if (q instanceof n) {
            ((n)q).q(s4, null);
        }
        else {
            ((e)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final bF bf) {
        if (bf.q != null) {
            this.q.q(bf);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == cx.q) {
                    if (this.q instanceof e) {
                        ((e)this.q).r();
                    }
                    else {
                        ((n)this.q).q();
                    }
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.w) {
                    this.dispose();
                    return true;
                }
                if (event.target == this.e) {
                    this.q.setVisible(true);
                    return true;
                }
                if (event.target == this.q) {
                    final String q;
                    if ((q = this.q.q(true)).length() > 0) {
                        this.q.w(q, this.q, 0, this.q.q().getForeground().getRGB());
                        this.q.e();
                        final String s = q;
                        final int rgb = this.q.q().getForeground().getRGB();
                        final String s2 = s;
                        final bp bp;
                        final int u = (bp = (bp)this.q.e.w(this.q.q())).u;
                        bp.b_(rgb);
                        final bF bf;
                        (bf = new bF(this.q.w(s2), bp, false, true, (bj)this.q.w.w(u), this.q)).o = rgb;
                        this.q(bf);
                    }
                    if (cx.q) {
                        this.q.q().requestFocus();
                    }
                    return true;
                }
                if (event.arg instanceof URL) {
                    this.q.q((URL)event.arg, "_blank");
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void dispose() {
        if (!this.q.o()) {
            this.q.w("", this.q, 2, 0);
        }
        this.q.dispose();
        this.q.f.w(this.q);
        super.dispose();
    }
    
    public J(final bI bi, final int n, final bC q, final int q2) {
        super(false);
        this.q = false;
        this.q = q2;
        this.setSize(525, 375);
        this.setBackground(be.w.q);
        this.q = bi;
        this.q = new cq();
        this.q = (this.q.q() == n);
        this.q = bi;
        bV bv;
        if ((bv = (bV)this.q.e.w(n)) == null) {
            bv = (bV)this.q.r.w(n);
        }
        this.setTitle(cv.q(cv.q(cl.q("n?>65B5>35KeKP\\")), new String[] { bv.getName() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final H h = new H(be.w.u);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        h.setBackground(be.w.w);
        this.setLayout(layout);
        h.setLayout(layout);
        (this.q = q).setFont(be.w.w());
        final boolean b;
        if (!(b = (this.q.y() || this.q.u())) || be.w.h()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final r r = new r(this.q);
        layout.setConstraints(r, gridBagConstraints);
        if (be.w.h()) {
            panel.add(r);
        }
        else {
            h.add(r);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q = new M(bi, false, this.q, null, this, false), gridBagConstraints);
        if (be.w.h()) {
            panel2.add(this.q);
        }
        else {
            h.add(this.q);
        }
        this.q = new Q(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (be.w.h()) {
            panel.add(this.q);
        }
        else {
            h.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (be.w.h() ? 0.0 : 1.0E-5);
        final String w = be.w.w();
        final String q3 = cl.q("9>F9D5");
        final String q4 = cl.q("t>F9D5");
        final String q5 = cl.q("n<93;K85B5KD?K9>F9D5KEC5BC");
        final String q6 = cl.q("C5>4");
        final String q7 = cl.q("n<93;K85B5WK?BK@B5CCKD85K\"p$%\"yK?BKpy$p\"K;5IWKD?KC5>4KI?EBK=5CC175KD?K1<<KEC5BCK9>KD85K3EBB5>DK3?>65B5>35Y");
        final String q8 = cl.q("3<?C5");
        final String q9 = cl.q("n<?C5");
        final String q10 = cl.q("n<93;K85B5KD?K3<?C5KD89CKG9>4?G");
        this.e = this.q(w, q3, cv.q(q4), cv.q(q5));
        this.q = this.q(w, q6, cv.q(cl.q("#5>4")), cv.q(q7));
        this.w = this.q(w, q8, cv.q(q9), cv.q(q10));
        final Container container;
        (container = new Panel()).setLayout(layout);
        gridBagConstraints.insets = (be.w.h() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.e, gridBagConstraints);
        container.add(this.e);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.w, gridBagConstraints);
        container.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        container.add(this.q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        layout.setConstraints(container, gridBagConstraints);
        if (!be.w.h()) {
            h.add(container);
        }
        else {
            if (b) {
                panel2.add(container);
            }
            else {
                panel.add(container);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            layout.setConstraints(panel, gridBagConstraints);
            h.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            h.add(panel2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(h, gridBagConstraints);
        this.add(h);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q11;
        (q11 = h.q(be.w.u)).setBackground(be.w.r);
        q11.setForeground(be.w.e);
        layout.setConstraints(q11, gridBagConstraints);
        this.add(q11);
        h.q(be.w.e());
        h.q(this.q.w);
        if (!(this.q = new K(this.q.q, this.q, this))) {
            if (this.e instanceof e) {
                ((e)this.e).e();
            }
            else {
                this.e.setEnabled(false);
            }
            this.q.q("", q2, 1);
        }
        this.setVisible(true);
    }
    
    public final void q(final int n) {
        if (!this.q.isVisible()) {
            this.q.setVisible(true);
            this.setVisible(true);
        }
        bV bv;
        if ((bv = (bV)this.q.e.w(n)) == null) {
            bv = (bV)this.q.r.w(n);
        }
        if (bv == null) {
            return;
        }
        this.q.q(bv, true);
        this.q.q(bv);
        this.q.q();
    }
    
    public final void w(final int n) {
        final bp bp = (bp)this.q.w(n);
        final bF bf = new bF(cv.q(cv.q("User %1 has left"), bp.getName()), bp, false, true, (bj)this.q.w.w(bp.u), this.q);
        this.q.w(n);
        this.q.q(n);
        this.q.q();
        this.q.q(bf);
    }
    
    public final int q() {
        return this.q;
    }
    
    public final void e(final int q) {
        this.q = q;
    }
    
    public final boolean q() {
        return this.q;
    }
}
