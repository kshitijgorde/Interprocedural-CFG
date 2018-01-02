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

public final class M extends bW implements cn
{
    private Component q;
    private cq q;
    private cq w;
    private cq e;
    private TextField q;
    private J q;
    
    private boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((I)this.q).q();
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return cv.q("Type text here to filter users list.");
        }
        return null;
    }
    
    public final void q(final bV bv, final boolean b) {
        if (b) {
            this.e.q(bv);
        }
        super.q(bv, b);
    }
    
    public final void q() {
        this.q(this.q());
    }
    
    private void q(final boolean b) {
        synchronized (this.e) {
            for (int i = 0; i < this.e.q(); ++i) {
                final bV bv;
                final String lowerCase = (bv = (bV)this.e.q(i)).getName().toLowerCase();
                final String lowerCase2 = this.q.getText().toLowerCase();
                boolean b2 = true;
                if (this.w != null && this.w.q(bv.q())) {
                    b2 = false;
                }
                boolean b3 = true;
                if (this.q != null && !this.q.q(bv.q())) {
                    b3 = false;
                }
                if ((b || bv.i == this.q.i) && cl.q(lowerCase, lowerCase2, bv.u) && (b || b2) && b3) {
                    super.q(bv, true);
                }
                else {
                    this.q(bv);
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final bV bv = (bV)this.q();
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
                if (bv == null) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public M(final bI bi, final boolean b, final cq cq, final cq cq2, final J j) {
        this(bi, true, null, cq2, null, true);
    }
    
    public M(final bI bi, final boolean b, final cq q, final cq w, final J q2, final boolean b2) {
        super(bi);
        this.e = new cq();
        this.q = q2;
        this.q = q;
        this.w = w;
        this.q = new TextField(20);
        this.setBackground(be.w.i);
        this.setForeground(be.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final String w2 = be.w.w();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        if (be.w.h() && bi.y()) {
            final Image q3 = bi.q(w2 + "allusers_unchecked.gif", true);
            final Image q4 = bi.q(w2 + "allusers_checked.gif", true);
            if (q3 != null && q4 != null) {
                this.q = new I(q3, q4);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(cv.q("Show users in all rooms"));
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
            final Label label = new Label(be.w.h() ? cv.q("Show users in all rooms") : "");
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
        this.q.setForeground(be.w.t);
        this.q.setBackground(be.w.r);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.q = new u(b);
        final u q5 = this.q;
        final u q6 = this.q;
        final bI q7 = this.q;
        final J q8 = this.q;
        final bI bi2 = q7;
        final u u = q6;
        aQ aq;
        if (ar.q()) {
            aq = new aV(u, bi2, q8);
        }
        else {
            aq = new aL(u, bi2, q8);
        }
        q5.q(aq);
        final r r = new r(super.q);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
        final w w3 = new w(null, "icon");
        final w w4 = new w(cv.q("Nickname"), "name");
        w3.e(28);
        w3.w(0);
        w4.q(true);
        super.q.q(true);
        super.q.w(w3);
        super.q.w(w4);
        super.q.q(w4);
        super.q.w(26);
        this.q();
    }
}
