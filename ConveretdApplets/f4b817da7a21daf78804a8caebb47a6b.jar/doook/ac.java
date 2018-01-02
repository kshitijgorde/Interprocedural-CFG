// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class ac extends i implements aj
{
    public String a(final Object o) {
        if (o != super.b) {
            return null;
        }
        final a a = (a)super.a.a();
        if (super.b.c()) {
            return aC.a(aG.a("Click here to enter %1: %2."), new String[] { a.g(), a.a });
        }
        if (a == null) {
            return aG.a("This button is disabled because no room is selected.");
        }
        return aC.a(aG.a("This button is disabled because you are already in %1."), new String[] { a.g() });
    }
    
    public ac(final be b) {
        super.b = b;
        super.a.resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (b.c(29) || b.c(38) || b.c(27) || b.c(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.c, gridBagConstraints);
            super.c.a(aG.a("Click here to create a new room."), null);
            this.add(super.c);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.b, gridBagConstraints);
        this.add(super.b);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final aX ax = new aX(super.a);
        layout.setConstraints(ax, gridBagConstraints);
        this.add(ax);
        final aB ab = new aB(aG.a("Name"), "name");
        final aB ab2 = new aB(be.c, "lock");
        final aB ab3 = new aB(b.a("usersIcon.gif", false, 20), "users");
        final aB ab4 = new aB(be.c, "arrow");
        ab4.c(0);
        ab4.e(12);
        ab2.c(0);
        ab2.e(12);
        ab.b(100);
        ab3.b(true);
        ab.b(true);
        super.a.a(true);
        super.a.b(ab4);
        super.a.b(ab2);
        super.a.b(ab);
        super.a.b(ab3);
        super.a.a(ab3);
    }
}
