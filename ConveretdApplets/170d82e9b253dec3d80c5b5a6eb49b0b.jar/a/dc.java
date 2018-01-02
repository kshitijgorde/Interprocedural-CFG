// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class dc extends da implements dT
{
    public final String q(final Object o) {
        if (o != super.q) {
            return null;
        }
        final cr cr = (cr)super.q.q();
        if (super.q.q()) {
            return ec.q(eb.q("Click here to enter %1: %2."), new String[] { cr.getName(), cr.q });
        }
        if (cr == null) {
            return eb.q("This button is disabled because no room is selected.");
        }
        return ec.q(eb.q("This button is disabled because you are already in %1."), new String[] { cr.getName() });
    }
    
    public dc(final cU q) {
        ((w)(super.q = q)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (q.q(29) || q.q(38) || q.q(27) || q.q(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.w, gridBagConstraints);
            super.w.q(eb.q("Click here to create a new room."), null);
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
        final t t = new t(super.q);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final y y = new y(eb.q("Name"), "name");
        final y y2 = new y(a.w, "lock");
        final y y3 = new y(q.w("usersIcon.gif", false), "users");
        final y y4 = new y(a.e, "default");
        y2.e(0);
        y2.r(12);
        y4.e(0);
        y4.r(12);
        y.w(100);
        y2.w(20);
        y3.w(true);
        y.w(true);
        super.q.q(true);
        super.q.w(y2);
        super.q.w(y);
        super.q.w(y3);
        super.q.q(y3);
    }
}
