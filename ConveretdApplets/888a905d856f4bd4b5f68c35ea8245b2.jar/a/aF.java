// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class aF extends ao implements bf
{
    public final String q(final Object o) {
        if (o != super.q) {
            return null;
        }
        final cB cb = (cB)super.q.q();
        if (super.q.q()) {
            return B.q(be.w("Click here to enter %1: %2."), new String[] { cb.a, cb.q });
        }
        if (cb == null) {
            return be.w("This button is disabled because no room is selected.");
        }
        return B.q(be.w("This button is disabled because you are already in %1."), new String[] { cb.a });
    }
    
    public aF(final dH q) {
        ((cc)(super.q = q)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (q.q(29) || q.q(38) || q.q(27) || q.q(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.w, gridBagConstraints);
            super.w.q(be.w("Click here to create a new room."), null);
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
        final bZ bz = new bZ(super.q);
        layout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
        final aX ax = new aX(be.w("Name"), "name");
        final aX ax2 = new aX(dN.w, "lock");
        final aX ax3 = new aX(q.q("usersIcon.gif", false), "users");
        final aX ax4 = new aX(dN.e, "default");
        ax2.e(0);
        ax2.r(12);
        ax4.e(0);
        ax4.r(12);
        ax.w(100);
        ax2.w(20);
        ax3.w(true);
        ax.w(true);
        super.q.q(true);
        super.q.w(ax2);
        super.q.w(ax);
        super.q.w(ax3);
        super.q.q(ax3);
    }
}
