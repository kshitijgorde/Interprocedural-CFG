// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class P extends O implements aB
{
    public String a(final Object o) {
        if (o != super.c) {
            return null;
        }
        final av av = (av)super.d.a();
        if (super.c.a()) {
            return am.a(ao.e("Click here to enter %1: %2."), new String[] { av.f(), av.a });
        }
        if (av == null) {
            return ao.e("This button is disabled because no room is selected.");
        }
        return am.a(ao.e("This button is disabled because you are already in %1."), new String[] { av.f() });
    }
    
    public P(final u c) {
        super.c = c;
        super.d.resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (c.d(29) || c.d(38) || c.d(27) || c.d(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.d, gridBagConstraints);
            super.d.a(ao.e("Click here to create a new room."), null);
            this.add(super.d);
        }
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(super.c, gridBagConstraints);
        this.add(super.c);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridheight = 0;
        final aR ar = new aR(super.d);
        layout.setConstraints(ar, gridBagConstraints);
        this.add(ar);
        final j j = new j(ao.e("Name"), "name");
        final j i = new j(t.a, "lock");
        final j k = new j(c.a("usersIcon.gif", false, 20), "users");
        final j l = new j(t.a, "arrow");
        l.c(0);
        l.d(12);
        i.c(0);
        i.d(12);
        j.b(100);
        k.c(true);
        j.c(true);
        super.d.a(true);
        super.d.b(i);
        super.d.b(l);
        super.d.b(j);
        super.d.b(k);
        super.d.a(k);
    }
}
