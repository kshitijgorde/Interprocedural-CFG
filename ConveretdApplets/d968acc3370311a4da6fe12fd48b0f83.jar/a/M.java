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

public final class M extends u implements eo
{
    private P q;
    private cV q;
    private cP q;
    private dW q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private N q;
    private boolean q;
    private int q;
    private U q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (cf.w.k()) {
            q = dI.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new g(70, 20);
            }
            else {
                q = new g(s3, 70, 20);
            }
            ((g)q).q(s4, null);
        }
        else if (q instanceof p) {
            ((p)q).q(s4, null);
        }
        else {
            ((g)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final cS cs) {
        if (cs.q != null) {
            this.q.q(cs);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    if (this.q instanceof g) {
                        ((g)this.q).r();
                    }
                    else {
                        ((p)this.q).q();
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
                        this.q.r();
                        final String s = q;
                        final int rgb = this.q.q().getForeground().getRGB();
                        final String s2 = s;
                        final cz cz;
                        final int e = (cz = (cz)this.q.e.w(this.q.q())).e;
                        cz.r(rgb);
                        final cS cs;
                        (cs = new cS(this.q.w(s2), cz, false, true, (cm)this.q.w.w(e), this.q)).p = rgb;
                        this.q(cs);
                    }
                    if (ef.q) {
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
        if (!this.q.p()) {
            this.q.w("", this.q, 2, 0);
        }
        this.q.dispose();
        this.q.g.w(this.q);
        super.dispose();
    }
    
    public M(final cV cv, final int n, final cP q, final int q2) {
        super(false);
        this.q = false;
        this.q = q2;
        this.setSize(525, 375);
        this.setBackground(cf.w.q);
        this.q = cv;
        this.q = new dW();
        this.q = (this.q.q() == n);
        this.q = cv;
        dj dj;
        if ((dj = (dj)this.q.e.w(n)) == null) {
            dj = (dj)this.q.r.w(n);
        }
        this.setTitle(ec.q(eb.q(dV.q("n?>65B5>35KeKP\\")), new String[] { dj.getName() }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final K k = new K(cf.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        k.setBackground(cf.w.w);
        this.setLayout(layout);
        k.setLayout(layout);
        (this.q = q).setFont(cf.w.w());
        final boolean b;
        if (!(b = (this.q.i() || this.q.o())) || cf.w.k()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final t t = new t(this.q);
        layout.setConstraints(t, gridBagConstraints);
        if (cf.w.k()) {
            panel.add(t);
        }
        else {
            k.add(t);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q = new P(cv, false, this.q, null, this, false), gridBagConstraints);
        if (cf.w.k()) {
            panel2.add(this.q);
        }
        else {
            k.add(this.q);
        }
        this.q = new U(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (cf.w.k()) {
            panel.add(this.q);
        }
        else {
            k.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (cf.w.k() ? 0.0 : 1.0E-5);
        final String e = cf.w.e();
        final String q3 = dV.q("9>F9D5");
        final String q4 = dV.q("t>F9D5");
        final String q5 = dV.q("n<93;K85B5KD?K9>F9D5KEC5BC");
        final String q6 = dV.q("C5>4");
        final String q7 = dV.q("n<93;K85B5WK?BK@B5CCKD85K\"p$%\"yK?BKpy$p\"K;5IWKD?KC5>4KI?EBK=5CC175KD?K1<<KEC5BCK9>KD85K3EBB5>DK3?>65B5>35Y");
        final String q8 = dV.q("3<?C5");
        final String q9 = dV.q("n<?C5");
        final String q10 = dV.q("n<93;K85B5KD?K3<?C5KD89CKG9>4?G");
        this.e = this.q(e, q3, eb.q(q4), eb.q(q5));
        this.q = this.q(e, q6, eb.q(dV.q("#5>4")), eb.q(q7));
        this.w = this.q(e, q8, eb.q(q9), eb.q(q10));
        final Container container;
        (container = new Panel()).setLayout(layout);
        gridBagConstraints.insets = (cf.w.k() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (!cf.w.k()) {
            k.add(container);
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
            k.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            k.add(panel2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(k, gridBagConstraints);
        this.add(k);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q11;
        (q11 = k.q(cf.w.e)).setBackground(cf.w.r);
        q11.setForeground(cf.w.e);
        layout.setConstraints(q11, gridBagConstraints);
        this.add(q11);
        k.q(cf.w.e());
        k.q(this.q.w);
        if (!(this.q = new N(this.q.q, this.q, this))) {
            if (this.e instanceof g) {
                ((g)this.e).e();
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
        dj dj;
        if ((dj = (dj)this.q.e.w(n)) == null) {
            dj = (dj)this.q.r.w(n);
        }
        if (dj == null) {
            return;
        }
        this.q.q(dj, true);
        this.q.q(dj);
        this.q.q();
    }
    
    public final void w(final int n) {
        final cz cz = (cz)this.q.w(n);
        final cS cs = new cS(ec.q(eb.q("User %1 has left"), cz.getName()), cz, false, true, (cm)this.q.w.w(cz.e), this.q);
        this.q.w(n);
        this.q.q(n);
        this.q.q();
        this.q.q(cs);
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
