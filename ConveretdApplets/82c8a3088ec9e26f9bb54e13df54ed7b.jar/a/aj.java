// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class aj extends V implements aA
{
    public final String q(final Object o) {
        if (o != super.q) {
            return null;
        }
        final bw bw = (bw)super.q.q();
        if (super.q.q()) {
            return s.q(ak.q("Click here to enter %1: %2."), new String[] { bw.o, bw.q });
        }
        if (bw == null) {
            return ak.q("This button is disabled because no room is selected.");
        }
        return s.q(ak.q("This button is disabled because you are already in %1."), new String[] { bw.o });
    }
    
    public aj(final co q) {
        ((be)(super.q = q)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (q.q(29) || q.q(38) || q.q(27) || q.q(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.w, gridBagConstraints);
            super.w.q(ak.q("Click here to create a new room."), null);
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
        final bb bb = new bb(super.q);
        layout.setConstraints(bb, gridBagConstraints);
        this.add(bb);
        final au au = new au(ak.q("Name"), "name");
        final au au2 = new au(cs.w, "lock");
        final au au3 = new au(q.q("usersIcon.gif", false), "users");
        final au au4 = new au(cs.e, "default");
        au2.w(0);
        au2.e(12);
        au4.w(0);
        au4.e(12);
        au.q(100);
        au2.q(20);
        au3.q(true);
        au.q(true);
        super.q.q(true);
        super.q.w(au2);
        super.q.w(au);
        super.q.w(au3);
        super.q.q(au3);
    }
}
