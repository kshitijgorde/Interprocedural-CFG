// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class bP extends bN implements cn
{
    public final String q(final Object o) {
        if (o != super.q) {
            return null;
        }
        final bk bk = (bk)super.q.q();
        if (super.q.q()) {
            return cv.q(cv.q("Click here to enter %1: %2."), new String[] { bk.getName(), bk.q });
        }
        if (bk == null) {
            return cv.q("This button is disabled because no room is selected.");
        }
        return cv.q(cv.q("This button is disabled because you are already in %1."), new String[] { bk.getName() });
    }
    
    public bP(final bH q) {
        ((u)(super.q = q)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (q.q(29) || q.q(38) || q.q(27) || q.q(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.w, gridBagConstraints);
            super.w.q(cv.q("Click here to create a new room."), null);
            this.add(super.w);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.q, gridBagConstraints);
        this.add(super.q);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final r r = new r(super.q);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
        final w w = new w(cv.q("Name"), "name");
        final w w2 = new w(a.w, "lock");
        final w w3 = new w(q.w("usersIcon.gif", false), "users");
        final w w4 = new w(a.e, "default");
        w2.w(0);
        w2.e(12);
        w4.w(0);
        w4.e(12);
        w.q(100);
        w2.q(20);
        w3.q(true);
        w.q(true);
        super.q.q(true);
        super.q.w(w2);
        super.q.w(w);
        super.q.w(w3);
        super.q.q(w3);
    }
}
