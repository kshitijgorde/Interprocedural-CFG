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

public final class ck extends ep implements bf
{
    private Component q;
    private M w;
    private M e;
    public M q;
    private TextField q;
    private bz q;
    
    private boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((am)this.q).q;
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return be.w("Type text here to filter users list.");
        }
        return null;
    }
    
    public final void q(final aO ao, final boolean b) {
        if (b) {
            this.q.q(ao);
        }
        super.q(ao, b);
    }
    
    public final void q() {
        this.q(this.q());
    }
    
    private void q(final boolean b) {
        synchronized (this.q) {
            for (int i = 0; i < this.q.q; ++i) {
                final aO ao;
                final String lowerCase = (ao = (aO)this.q.q(i)).a.toLowerCase();
                final String lowerCase2 = this.q.getText().toLowerCase();
                boolean b2 = true;
                if (this.e != null && this.e.q(ao.s)) {
                    b2 = false;
                }
                boolean b3 = true;
                if (this.w != null && !this.w.q(ao.s)) {
                    b3 = false;
                }
                if ((b || ao.r == this.q.r) && ds.q(lowerCase, lowerCase2, ao.u) && (b || b2) && b3) {
                    super.q(ao, true);
                }
                else {
                    this.q(ao);
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final aO ao = (aO)this.q();
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
                if (ao == null) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ck(final ap ap, final boolean b, final M m, final M i, final bz bz) {
        this(ap, true, null, i, null, true);
    }
    
    public ck(final ap ap, final boolean b, final M w, final M e, final bz q, final boolean b2) {
        super(ap);
        this.q = new M();
        this.q = q;
        this.w = w;
        this.e = e;
        this.q = new TextField(20);
        this.setBackground(bC.w.i);
        this.setForeground(bC.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final String r = bC.w.r();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        if (bC.w.z() && ap.i()) {
            final Image r2 = ap.r(r + "allusers_unchecked.gif", true);
            final Image r3 = ap.r(r + "allusers_checked.gif", true);
            if (r2 != null && r3 != null) {
                this.q = new am(r2, r3);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(be.w("Show users in all rooms"));
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
            final Label label = new Label(bC.w.z() ? be.w("Show users in all rooms") : "");
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
        this.q.setForeground(bC.w.t);
        this.q.setBackground(bC.w.r);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.q = new cc(b);
        final cc q2 = this.q;
        final cc q3 = this.q;
        final ap q4 = this.q;
        final bz q5 = this.q;
        final ap ap2 = q4;
        final cc cc = q3;
        eb eb;
        if (cU.q()) {
            eb = new ej(cc, ap2, q5);
        }
        else {
            eb = new dp(cc, ap2, q5);
        }
        q2.q(eb);
        final bZ bz = new bZ(super.q);
        layout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
        final aX ax = new aX(null, "icon");
        final aX ax2 = new aX(be.w("Nickname"), "name");
        ax.r(28);
        ax.e(0);
        ax2.w(true);
        super.q.q(true);
        super.q.w(ax);
        super.q.w(ax2);
        super.q.q(ax2);
        super.q.w(26);
        this.q();
    }
}
