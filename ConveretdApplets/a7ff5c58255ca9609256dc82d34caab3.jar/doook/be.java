// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class be extends bd implements aO
{
    public String a(final Object o) {
        if (o != super.d) {
            return null;
        }
        final aW aw = (aW)super.e.a();
        if (super.d.b()) {
            return H.a(ar.b("Click here to enter %1: %2."), new String[] { aw.d(), aw.m });
        }
        if (aw == null) {
            return ar.b("This button is disabled because no room is selected.");
        }
        return H.a(ar.b("This button is disabled because you are already in %1."), new String[] { aw.d() });
    }
    
    public be(final as i) {
        super.i = i;
        super.e.resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (i.a(29) || i.a(38) || i.a(27) || i.a(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.e, gridBagConstraints);
            super.e.a(ar.b("Click here to create a new room."), null);
            this.add(super.e);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.d, gridBagConstraints);
        this.add(super.d);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final aw aw = new aw(super.e);
        layout.setConstraints(aw, gridBagConstraints);
        this.add(aw);
        final p p = new p(ar.b("Name"), "name");
        final p p2 = new p(as.c, "lock");
        final p p3 = new p(i.a("usersIcon.gif", false, 20), "users");
        final p p4 = new p(as.c, "arrow");
        p4.b(0);
        p4.c(12);
        p2.b(0);
        p2.c(12);
        p.a(100);
        p3.b(true);
        p.b(true);
        super.e.a(true);
        super.e.b(p4);
        super.e.b(p2);
        super.e.b(p);
        super.e.b(p3);
        super.e.a(p3);
    }
}
