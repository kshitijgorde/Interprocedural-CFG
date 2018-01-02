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

public final class P extends dk implements dT
{
    private Component q;
    private dW q;
    private dW w;
    private dW e;
    private TextField q;
    private M q;
    
    private boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((L)this.q).q();
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return eb.q("Type text here to filter users list.");
        }
        return null;
    }
    
    public final void q(final dj dj, final boolean b) {
        if (b) {
            this.e.q(dj);
        }
        super.q(dj, b);
    }
    
    public final dj q(final int n) {
        if (this.e != null) {
            return (dj)this.e.q(0);
        }
        return null;
    }
    
    public final void q() {
        this.q(this.q());
    }
    
    private void q(final boolean b) {
        synchronized (this.e) {
            for (int i = 0; i < this.e.q(); ++i) {
                final dj dj;
                final String lowerCase = (dj = (dj)this.e.q(i)).getName().toLowerCase();
                final String lowerCase2 = this.q.getText().toLowerCase();
                boolean b2 = true;
                if (this.w != null && this.w.q(dj.q())) {
                    b2 = false;
                }
                boolean b3 = true;
                if (this.q != null && !this.q.q(dj.q())) {
                    b3 = false;
                }
                if ((b || dj.o == this.q.o) && dV.q(lowerCase, lowerCase2, dj.i) && (b || b2) && b3) {
                    super.q(dj, true);
                }
                else {
                    this.q(dj);
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final dj dj = (dj)this.q();
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
                if (dj == null) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public P(final cV cv, final boolean b, final dW dw, final dW dw2, final M m) {
        this(cv, true, null, dw2, null, true);
    }
    
    public P(final cV cv, final boolean b, final dW q, final dW w, final M q2, final boolean b2) {
        super(cv);
        this.e = new dW();
        this.q = q2;
        this.q = q;
        this.w = w;
        this.q = new TextField(20);
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final String e = cf.w.e();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        if (cf.w.k() && cv.i()) {
            final Image q3 = cv.q(e + "allusers_unchecked.gif", true);
            final Image q4 = cv.q(e + "allusers_checked.gif", true);
            if (q3 != null && q4 != null) {
                this.q = new L(q3, q4);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(eb.q("Show users in all rooms"));
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
            final Label label = new Label(cf.w.k() ? eb.q("Show users in all rooms") : "");
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
        this.q.setForeground(cf.w.t);
        this.q.setBackground(cf.w.r);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.q = new w(b);
        final w q5 = this.q;
        final w q6 = this.q;
        final cV q7 = this.q;
        final M q8 = this.q;
        final cV cv2 = q7;
        final w w2 = q6;
        bh bh;
        if (aG.q()) {
            bh = new bn(w2, cv2, q8);
        }
        else {
            bh = new bb(w2, cv2, q8);
        }
        q5.q(bh);
        final t t = new t(super.q);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final y y = new y(null, "icon");
        final y y2 = new y(eb.q("Nickname"), "name");
        y.r(28);
        y.e(0);
        y2.w(true);
        super.q.q(true);
        super.q.w(y);
        super.q.w(y2);
        super.q.q(y2);
        super.q.w(26);
        this.q();
    }
}
