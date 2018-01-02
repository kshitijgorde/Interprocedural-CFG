// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class ak extends X implements aB
{
    public final String q(final Object o) {
        if (o != super.q) {
            return null;
        }
        final bx bx = (bx)super.q.q();
        if (super.q.q()) {
            return t.q(al.q("Click here to enter %1: %2."), new String[] { bx.o, bx.q });
        }
        if (bx == null) {
            return al.q("This button is disabled because no room is selected.");
        }
        return t.q(al.q("This button is disabled because you are already in %1."), new String[] { bx.o });
    }
    
    public ak(final co q) {
        ((bf)(super.q = q)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (q.q(29) || q.q(38) || q.q(27) || q.q(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.w, gridBagConstraints);
            super.w.q(al.q("Click here to create a new room."), null);
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
        final bc bc = new bc(super.q);
        layout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
        final av av = new av(al.q("Name"), "name");
        final av av2 = new av(cs.w, "lock");
        final av av3 = new av(q.q("usersIcon.gif", false), "users");
        final av av4 = new av(cs.e, "default");
        av2.w(0);
        av2.e(12);
        av4.w(0);
        av4.e(12);
        av.q(100);
        av2.q(20);
        av3.q(true);
        av.q(true);
        super.q.q(true);
        super.q.w(av2);
        super.q.w(av);
        super.q.w(av3);
        super.q.q(av3);
    }
}
