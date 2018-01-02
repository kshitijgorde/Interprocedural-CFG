// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;
import java.awt.Container;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Component;

public final class bj extends cL implements aA
{
    private Component q;
    private A q;
    private A w;
    private A e;
    private TextField q;
    private aO q;
    
    private boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((T)this.q).q;
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return ak.q("Type text here to filter users list.");
        }
        return null;
    }
    
    public final void q(final ar ar, final boolean b) {
        if (b) {
            this.e.q(ar);
        }
        super.q(ar, b);
    }
    
    public final void q() {
        this.q(this.q());
    }
    
    private void q(final boolean b) {
        synchronized (this.e) {
            for (int i = 0; i < this.e.q; ++i) {
                final ar ar;
                final String lowerCase = (ar = (ar)this.e.q(i)).o.toLowerCase();
                final String lowerCase2 = this.q.getText().toLowerCase();
                boolean b2 = true;
                if (this.w != null && this.w.q(ar.s)) {
                    b2 = false;
                }
                boolean b3 = true;
                if (this.q != null && !this.q.q(ar.s)) {
                    b3 = false;
                }
                if ((b || ar.r == this.q.r) && ce.q(lowerCase, lowerCase2, ar.y) && (b || b2) && b3) {
                    super.q(ar, true);
                }
                else {
                    this.q(ar);
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final ar ar = (ar)this.q();
        this.q();
        switch (event.id) {
            case 402: {
                if (event.target == this.q) {
                    this.q(this.q());
                    break;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q(this.q());
                }
                if (ar == null) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bj(final W w, final boolean b, final A a, final A a2, final aO ao) {
        this(w, true, null, a2, null, true);
    }
    
    public bj(final W w, final boolean b, final A q, final A w2, final aO q2, final boolean b2) {
        super(w);
        this.e = new A();
        this.q = q2;
        this.q = q;
        this.w = w2;
        this.q = new TextField(20);
        this.setBackground(aS.w.i);
        this.setForeground(aS.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final String e = aS.w.e();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        if (aS.w.j() && w.u()) {
            final Image r = w.r(e + "allusers_unchecked.gif", true);
            final Image r2 = w.r(e + "allusers_checked.gif", true);
            if (r != null && r2 != null) {
                this.q = new T(r, r2);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(ak.q("Show users in all rooms"));
        }
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        if (b2) {
            final Container container;
            (container = new Panel()).setLayout(layout);
            gridBagConstraints.weightx = 0.0;
            gridBagConstraints.gridwidth = -1;
            gridBagConstraints.anchor = 17;
            gridBagConstraints.insets = new Insets(0, 5, 0, 0);
            layout.setConstraints(this.q, gridBagConstraints);
            container.add(this.q);
            final Label label = new Label(aS.w.j() ? ak.q("Show users in all rooms") : "");
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = 0;
            gridBagConstraints.fill = 2;
            gridBagConstraints.anchor = 17;
            layout.setConstraints(label, gridBagConstraints);
            container.add(label);
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(container, gridBagConstraints);
            this.add(container);
        }
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        this.q.setForeground(aS.w.t);
        this.q.setBackground(aS.w.r);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.q = new be(b);
        final be q3 = this.q;
        final be q4 = this.q;
        final W q5 = this.q;
        final aO q6 = this.q;
        final W w3 = q5;
        final be be = q4;
        cC cc;
        if (bL.q()) {
            cc = new cH(be, w3, q6);
        }
        else {
            cc = new cb(be, w3, q6);
        }
        q3.q(cc);
        final bb bb = new bb(super.q);
        layout.setConstraints(bb, gridBagConstraints);
        this.add(bb);
        final au au = new au(null, "icon");
        final au au2 = new au(ak.q("Nickname"), "name");
        au.e(28);
        au.w(0);
        au2.q(true);
        super.q.q(true);
        super.q.w(au);
        super.q.w(au2);
        super.q.q(au2);
        super.q.w(26);
        this.q();
    }
}
