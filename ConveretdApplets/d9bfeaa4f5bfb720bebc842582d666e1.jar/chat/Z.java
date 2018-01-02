// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class Z extends aa implements ab
{
    public final String a(final Object o) {
        if (o != super.a) {
            return null;
        }
        final O o2 = (O)super.a.a();
        if (super.a.a()) {
            return ak.a(ak.a(476), new String[] { o2.c, o2.a });
        }
        if (o2 == null) {
            return ak.a(477);
        }
        return ak.a(ak.a(478), new String[] { o2.c });
    }
    
    public Z(final bh a) {
        ((y)(super.a = a)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (a.a(29) || a.a(38) || a.a(27) || a.a(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.b, gridBagConstraints);
            super.b.a(ak.a(479), null);
            this.add(super.b);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.a, gridBagConstraints);
        this.add(super.a);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final g g = new g(super.a);
        layout.setConstraints(g, gridBagConstraints);
        this.add(g);
        final w w = new w(ak.a(27), "name");
        final w w2 = new w(null, "lock");
        final w w3 = new w(a.a("usersIcon.gif", false, 20), "users");
        final w w4 = new w(null, "arrow");
        w2.b(0);
        w4.b(1);
        w2.c(10);
        w4.c(11);
        w.a(120);
        w3.a = true;
        w.a = true;
        super.a.a();
        super.a.b(w4);
        super.a.b(w2);
        super.a.b(w);
        super.a.b(w3);
        super.a.a(w3);
    }
}
