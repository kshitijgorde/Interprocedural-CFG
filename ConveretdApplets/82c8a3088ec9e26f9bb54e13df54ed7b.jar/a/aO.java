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

public final class aO extends aT implements bV
{
    private bj q;
    private W q;
    private aF q;
    private A q;
    private Canvas q;
    private Canvas w;
    private Canvas e;
    private cA q;
    public boolean q;
    public int q;
    private cg q;
    
    private final Canvas q(final String s, final String s2, final String s3, final String s4) {
        Canvas q = null;
        if (aS.w.j()) {
            q = aD.q(s, s2, s3, this.q);
        }
        if (q == null) {
            if (s3 == null) {
                q = new M(70, 20);
            }
            else {
                q = new M(s3, 70, 20);
            }
            ((M)q).q(s4, null);
        }
        else if (q instanceof w) {
            ((w)q).q(s4, null);
        }
        else {
            ((M)q).q(s4, null);
        }
        return q;
    }
    
    public final void q(final r r) {
        if (r.q != null) {
            this.q.q(r);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                return true;
            }
            case 401: {
                if (event.key == 10 || event.key == bD.q) {
                    if (this.q instanceof M) {
                        ((M)this.q).r();
                    }
                    else {
                        ((w)this.q).q();
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
                        final int e = (l = (l)this.q.e.w(this.q.s)).e;
                        l.i(rgb);
                        this.q(new r(this.q.w(s2), l, false, true, (av)this.q.w.w(e), this.q));
                    }
                    if (bD.q) {
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
    
    public aO(final W w, final int n, final aF q, final int q2) {
        super(false);
        this.q = false;
        this.q = q2;
        this.setSize(525, 375);
        this.setBackground(aS.w.q);
        this.q = w;
        this.q = new A();
        this.q = (this.q.s == n);
        this.q = w;
        ar ar;
        if ((ar = (ar)this.q.e.w(n)) == null) {
            ar = (ar)this.q.r.w(n);
        }
        this.setTitle(s.q(ak.q(ce.q("n?>65B5>35KeKP\\")), new String[] { ar.o }));
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final L l = new L(aS.w.e);
        final Panel panel = new Panel();
        final Panel panel2 = new Panel();
        panel.setLayout(layout);
        panel2.setLayout(layout);
        l.setBackground(aS.w.w);
        this.setLayout(layout);
        l.setLayout(layout);
        (this.q = q).setFont(aS.w.w());
        final boolean b;
        if (!(b = (this.q.u() || this.q.i())) || aS.w.j()) {
            gridBagConstraints.gridwidth = 0;
        }
        else {
            gridBagConstraints.gridwidth = 1;
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        final bb bb = new bb(this.q);
        layout.setConstraints(bb, gridBagConstraints);
        if (aS.w.j()) {
            panel.add(bb);
        }
        else {
            l.add(bb);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.fill = 1;
        layout.setConstraints(this.q = new bj(w, false, this.q, null, this, false), gridBagConstraints);
        if (aS.w.j()) {
            panel2.add(this.q);
        }
        else {
            l.add(this.q);
        }
        this.q = new cg(this.q);
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        layout.setConstraints(this.q, gridBagConstraints);
        if (aS.w.j()) {
            panel.add(this.q);
        }
        else {
            l.add(this.q);
        }
        gridBagConstraints.insets = new Insets(2, 3, 2, 3);
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = (aS.w.j() ? 0.0 : 1.0E-5);
        final String e = aS.w.e();
        final String q3 = ce.q("9>F9D5");
        final String q4 = ce.q("t>F9D5");
        final String q5 = ce.q("n<93;K85B5KD?K9>F9D5KEC5BC");
        final String q6 = ce.q("C5>4");
        final String q7 = ce.q("n<93;K85B5WK?BK@B5CCKD85K\"p$%\"yK?BKpy$p\"K;5IWKD?KC5>4KI?EBK=5CC175KD?K1<<KEC5BCK9>KD85K3EBB5>DK3?>65B5>35Y");
        final String q8 = ce.q("3<?C5");
        final String q9 = ce.q("n<?C5");
        final String q10 = ce.q("n<93;K85B5KD?K3<?C5KD89CKG9>4?G");
        this.e = this.q(e, q3, ak.q(q4), ak.q(q5));
        this.q = this.q(e, q6, ak.q(ce.q("#5>4")), ak.q(q7));
        this.w = this.q(e, q8, ak.q(q9), ak.q(q10));
        final Panel panel3;
        (panel3 = new Panel()).setLayout(layout);
        gridBagConstraints.insets = (aS.w.j() ? new Insets(0, 0, 0, 0) : new Insets(2, 2, 2, 2));
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
        if (!aS.w.j()) {
            l.add(panel3);
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
            l.add(panel);
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 3;
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.weighty = 0.0;
            layout.setConstraints(panel2, gridBagConstraints);
            l.add(panel2);
        }
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(l, gridBagConstraints);
        this.add(l);
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 2;
        gridBagConstraints.gridwidth = 0;
        final Component q11;
        (q11 = l.q(aS.w.e)).setBackground(aS.w.r);
        q11.setForeground(aS.w.e);
        layout.setConstraints(q11, gridBagConstraints);
        this.add(q11);
        l.q(aS.w.e());
        l.q(this.q.w);
        if (!(this.q = new cA(this.q.q, this.q, this))) {
            if (this.e instanceof M) {
                ((M)this.e).e();
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
        ar ar;
        if ((ar = (ar)this.q.e.w(n)) == null) {
            ar = (ar)this.q.r.w(n);
        }
        if (ar == null) {
            return;
        }
        this.q.q(ar, true);
        this.q.q(ar);
        this.q.q();
    }
    
    public final void w(final int n) {
        final l l = (l)this.q.w(n);
        final r r = new r(s.q(ak.q("User %1 has left"), l.o), l, false, true, (av)this.q.w.w(l.e), this.q);
        this.q.w(n);
        this.q.q.w(n);
        this.q.q();
        this.q.q(r);
    }
    
    public final int q() {
        return this.q;
    }
    
    public final void u(final int q) {
        this.q = q;
    }
}
