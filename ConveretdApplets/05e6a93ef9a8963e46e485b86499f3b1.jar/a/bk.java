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

public final class bk extends cK implements aB
{
    private Component q;
    private B q;
    private B w;
    private B e;
    private TextField q;
    private aP q;
    
    private boolean q() {
        if (this.q instanceof Checkbox) {
            return ((Checkbox)this.q).getState();
        }
        return ((U)this.q).q;
    }
    
    public final String q(final Object o) {
        if (o == this.q) {
            return al.q("Type text here to filter users list.");
        }
        return null;
    }
    
    public final void q(final as as, final boolean b) {
        if (b) {
            this.e.q(as);
        }
        super.q(as, b);
    }
    
    public final void q() {
        this.q(this.q());
    }
    
    private void q(final boolean b) {
        synchronized (this.e) {
            for (int i = 0; i < this.e.q; ++i) {
                final as as;
                final String lowerCase = (as = (as)this.e.q(i)).o.toLowerCase();
                final String lowerCase2 = this.q.getText().toLowerCase();
                boolean b2 = true;
                if (this.w != null && this.w.q(as.a)) {
                    b2 = false;
                }
                boolean b3 = true;
                if (this.q != null && !this.q.q(as.a)) {
                    b3 = false;
                }
                if ((b || as.r == this.q.r) && ce.q(lowerCase, lowerCase2, as.y) && (b || b2) && b3) {
                    super.q(as, true);
                }
                else {
                    this.q(as);
                }
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final as as = (as)this.q();
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
                if (as == null) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bk(final W w, final boolean b, final B b2, final B b3, final aP ap) {
        this(w, true, null, b3, null, true);
    }
    
    public bk(final W w, final boolean b, final B q, final B w2, final aP q2, final boolean b2) {
        super(w);
        this.e = new B();
        this.q = q2;
        this.q = q;
        this.w = w2;
        this.q = new TextField(20);
        this.setBackground(aT.w.i);
        this.setForeground(aT.w.u);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final String e = aT.w.e();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        if (aT.w.g() && w.u()) {
            final Image r = w.r(e + "allusers_unchecked.gif", true);
            final Image r2 = w.r(e + "allusers_checked.gif", true);
            if (r != null && r2 != null) {
                this.q = new U(r, r2);
            }
        }
        if (this.q == null) {
            this.q = new Checkbox(al.q("Show users in all rooms"));
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
            final Label label = new Label(aT.w.g() ? al.q("Show users in all rooms") : "");
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
        this.q.setForeground(aT.w.t);
        this.q.setBackground(aT.w.r);
        layout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        super.q = new bf(b);
        final bf q3 = this.q;
        final bf q4 = this.q;
        final W q5 = this.q;
        final aP q6 = this.q;
        final W w3 = q5;
        final bf bf = q4;
        cB cb;
        if (bM.q()) {
            cb = new cG(bf, w3, q6);
        }
        else {
            cb = new cb(bf, w3, q6);
        }
        q3.q(cb);
        final bc bc = new bc(super.q);
        layout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
        final av av = new av(null, "icon");
        final av av2 = new av(al.q("Nickname"), "name");
        av.e(28);
        av.w(0);
        av2.q(true);
        super.q.q(true);
        super.q.w(av);
        super.q.w(av2);
        super.q.q(av2);
        super.q.w(26);
        this.q();
    }
}
