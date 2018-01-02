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

public final class aP extends aU implements bV
{
    private bk q;
    private W q;
    private aG q;
    private B q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private cz q;
    public boolean q;
    public int q;
    private cg q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (aT.w.g()) {
            q = aE.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new N(70, 20);
            }
            else {
                q = new N(s3, 70, 20);
            }
            ((N)q).q(s4, null);
        }
        else if (q instanceof x) {
            ((x)q).q(s4, null);
        }
        else {
            ((N)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final s s) {
        if (s.q != null) {
            this.q.q(s);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
                    if (this.q instanceof N) {
                        ((N)this.q).r();
                    }
                    else {
                        ((x)this.q).q();
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
                        this.q.e();
                        final String s = q;
                        final int rgb = this.q.q.getForeground().getRGB();
                        final String s2 = s;
                        final l l;
                        final int e = (l = (l)this.q.e.w(this.q.a)).e;
                        l.y(rgb);
                        this.q(new s(this.q.w(s2), l, false, true, (aw)this.q.w.w(e), this.q));
                    }
                    if (bE.q) {
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
        this.q.f.w(this.q);
        super.dispose();
    }
    
    public aP(final W w, final int n, final aG q, final int q2) {
        super(false);
        this.q = false;
        this.q = q2;
        this.setSize(525, 375);
        this.setBackground(aT.w.q);
        this.q = w;
        this.q = new B();
        this.q = (this.q.a == n);
        this.q = w;
        as as;
        if ((as = (as)this.q.e.w(n)) == null) {
            as = (as)this.q.r.w(n);
        }
        this.setTitle(t.q(al.q(ce.q("n?>65B5>35KeKP\\")), new String[] { as.o }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final M m = new M(aT.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        m.setBackground(aT.w.w);
        this.setLayout(layout);
        m.setLayout(layout);
        (this.q = q).setFont(aT.w.w());
        final boolean b;
        if (!(b = (this.q.u() || this.q.i())) || aT.w.g()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bc bc = new bc(this.q);
        layout.setConstraints(bc, gridBagConstraints);
        if (aT.w.g()) {
            panel.add(bc);
        }
        else {
            m.add(bc);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q = new bk(w, false, this.q, null, this, false), gridBagConstraints);
        if (aT.w.g()) {
            panel2.add(this.q);
        }
        else {
            m.add(this.q);
        }
        this.q = new cg(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (aT.w.g()) {
            panel.add(this.q);
        }
        else {
            m.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (aT.w.g() ? 0.0 : 1.0E-5);
        final String e = aT.w.e();
        final String q3 = ce.q("9>F9D5");
        final String q4 = ce.q("t>F9D5");
        final String q5 = ce.q("n<93;K85B5KD?K9>F9D5KEC5BC");
        final String q6 = ce.q("C5>4");
        final String q7 = ce.q("n<93;K85B5WK?BK@B5CCKD85K\"p$%\"yK?BKpy$p\"K;5IWKD?KC5>4KI?EBK=5CC175KD?K1<<KEC5BCK9>KD85K3EBB5>DK3?>65B5>35Y");
        final String q8 = ce.q("3<?C5");
        final String q9 = ce.q("n<?C5");
        final String q10 = ce.q("n<93;K85B5KD?K3<?C5KD89CKG9>4?G");
        this.e = this.q(e, q3, al.q(q4), al.q(q5));
        this.q = this.q(e, q6, al.q(ce.q("#5>4")), al.q(q7));
        this.w = this.q(e, q8, al.q(q9), al.q(q10));
        final Panel panel3;
        (panel3 = new Panel()).setLayout(layout);
        gridBagConstraints.insets = (aT.w.g() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (!aT.w.g()) {
            m.add(panel3);
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
            m.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            m.add(panel2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(m, gridBagConstraints);
        this.add(m);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q11;
        (q11 = m.q(aT.w.e)).setBackground(aT.w.r);
        q11.setForeground(aT.w.e);
        layout.setConstraints(q11, gridBagConstraints);
        this.add(q11);
        m.q(aT.w.e());
        m.q(this.q.w);
        if (!(this.q = new cz(this.q.q, this.q, this))) {
            if (this.e instanceof N) {
                ((N)this.e).e();
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
        as as;
        if ((as = (as)this.q.e.w(n)) == null) {
            as = (as)this.q.r.w(n);
        }
        if (as == null) {
            return;
        }
        this.q.q(as, true);
        this.q.q(as);
        this.q.q();
    }
    
    public final void w(final int n) {
        final l l = (l)this.q.w(n);
        final s s = new s(t.q(al.q("User %1 has left"), l.o), l, false, true, (aw)this.q.w.w(l.e), this.q);
        this.q.w(n);
        this.q.q.w(n);
        this.q.q();
        this.q.q(s);
    }
    
    public final int q() {
        return this.q;
    }
    
    public final void b_(final int q) {
        this.q = q;
    }
}
