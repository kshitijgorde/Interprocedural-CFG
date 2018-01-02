// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.awt.Event;
import java.awt.Canvas;

public final class bz extends bE implements dh
{
    private ck q;
    private ap q;
    private bk q;
    private M q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private ea q;
    public boolean q;
    public int q;
    private dw q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (bC.w.z()) {
            q = bi.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new ad(70, 20);
            }
            else {
                q = new ad(s3, 70, 20);
            }
            ((ad)q).q(s4, null);
        }
        else if (q instanceof H) {
            ((H)q).q(s4, null);
        }
        else {
            ((ad)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final A a) {
        if (a.q != null) {
            this.q.q(a);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
                    if (this.q instanceof ad) {
                        ((ad)this.q).r();
                    }
                    else {
                        ((H)this.q).q();
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
                        this.q.q(q, this.q, 0, this.q.q.getForeground().getRGB());
                        this.q.r();
                        final String s = q;
                        final int rgb = this.q.q.getForeground().getRGB();
                        final String s2 = s;
                        final p p;
                        final int e = (p = (p)this.q.a.w(this.q.s)).e;
                        p.i(rgb);
                        this.q(new A(this.q.w(s2), p, false, true, (aZ)this.q.p.w(e), this.q));
                    }
                    if (cK.q) {
                        this.q.q.requestFocus();
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
        this.q.q("", this.q, 2, 0);
        this.q.dispose();
        this.q.c.w(this.q);
        super.dispose();
    }
    
    public bz(final ap ap, final int n, final bk q, final int q2) {
        super(false);
        this.q = false;
        this.q = q2;
        this.setSize(525, 375);
        this.setBackground(bC.w.q);
        this.q = ap;
        this.q = new M();
        this.q = (this.q.s == n);
        this.q = ap;
        aO ao;
        if ((ao = (aO)this.q.a.w(n)) == null) {
            ao = (aO)this.q.s.w(n);
        }
        this.setTitle(B.q(be.w(ds.q("n?>65B5>35KeKP\\")), new String[] { ao.a }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final ac ac = new ac(bC.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        ac.setBackground(bC.w.w);
        this.setLayout(layout);
        ac.setLayout(layout);
        (this.q = q).setFont(bC.w.w());
        final boolean b;
        if (!(b = (this.q.i() || this.q.o())) || bC.w.z()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bZ bz = new bZ(this.q);
        layout.setConstraints(bz, gridBagConstraints);
        if (bC.w.z()) {
            panel.add(bz);
        }
        else {
            ac.add(bz);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q = new ck(ap, false, this.q, null, this, false), gridBagConstraints);
        if (bC.w.z()) {
            panel2.add(this.q);
        }
        else {
            ac.add(this.q);
        }
        this.q = new dw(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (bC.w.z()) {
            panel.add(this.q);
        }
        else {
            ac.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (bC.w.z() ? 0.0 : 1.0E-5);
        final String r = bC.w.r();
        final String q3 = ds.q("9>F9D5");
        final String q4 = ds.q("t>F9D5");
        final String q5 = ds.q("n<93;K85B5KD?K9>F9D5KEC5BC");
        final String q6 = ds.q("C5>4");
        final String q7 = ds.q("n<93;K85B5WK?BK@B5CCKD85K\"p$%\"yK?BKpy$p\"K;5IWKD?KC5>4KI?EBK=5CC175KD?K1<<KEC5BCK9>KD85K3EBB5>DK3?>65B5>35Y");
        final String q8 = ds.q("3<?C5");
        final String q9 = ds.q("n<?C5");
        final String q10 = ds.q("n<93;K85B5KD?K3<?C5KD89CKG9>4?G");
        this.e = this.q(r, q3, be.w(q4), be.w(q5));
        this.q = this.q(r, q6, be.w(ds.q("#5>4")), be.w(q7));
        this.w = this.q(r, q8, be.w(q9), be.w(q10));
        final Panel panel3;
        (panel3 = new Panel()).setLayout(layout);
        gridBagConstraints.insets = (bC.w.z() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
        gridBagConstraints.gridwidth = -1;
        layout.setConstraints(this.e, gridBagConstraints);
        panel3.add(this.e);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.w, gridBagConstraints);
        panel3.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        panel3.add(this.q);
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0E-5;
        layout.setConstraints(panel3, gridBagConstraints);
        if (!bC.w.z()) {
            ac.add(panel3);
        }
        else {
            if (b) {
                panel2.add(panel3);
            }
            else {
                panel.add(panel3);
            }
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.fill = 1;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            layout.setConstraints(panel, gridBagConstraints);
            ac.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            ac.add(panel2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(ac, gridBagConstraints);
        this.add(ac);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q11;
        (q11 = ac.q(bC.w.e)).setBackground(bC.w.r);
        q11.setForeground(bC.w.e);
        layout.setConstraints(q11, gridBagConstraints);
        this.add(q11);
        ac.q(bC.w.e());
        ac.q(this.q.w);
        if (!(this.q = new ea(this.q.q, this.q, this))) {
            if (this.e instanceof ad) {
                ((ad)this.e).e();
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
        aO ao;
        if ((ao = (aO)this.q.a.w(n)) == null) {
            ao = (aO)this.q.s.w(n);
        }
        if (ao == null) {
            return;
        }
        this.q.q(ao, true);
        this.q.q(ao);
        this.q.q();
    }
    
    public final void w(final int n) {
        final p p = (p)this.q.w(n);
        final A a = new A(B.q(be.w("User %1 has left"), p.a), p, false, true, (aZ)this.q.p.w(p.e), this.q);
        this.q.w(n);
        this.q.q.w(n);
        this.q.q();
        this.q.q(a);
    }
    
    public final int q() {
        return this.q;
    }
    
    public final void u(final int q) {
        this.q = q;
    }
}
