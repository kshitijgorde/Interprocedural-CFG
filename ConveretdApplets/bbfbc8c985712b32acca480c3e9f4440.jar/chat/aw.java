// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public final class aw extends az implements aB
{
    public final String a(final Object o) {
        if (o != super.a) {
            return null;
        }
        final ak ak = (ak)super.a.a();
        if (super.a.a()) {
            return bm.a(aS.a(476), new String[] { ak.d, ak.a });
        }
        if (ak == null) {
            return aS.a(477);
        }
        return bm.a(aS.a(478), new String[] { ak.d });
    }
    
    public aw(final cs a) {
        ((K)(super.a = a)).resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (a.a(29) || a.a(38) || a.a(27) || a.a(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.b, gridBagConstraints);
            super.b.a(aS.a(479), null);
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
        final i i = new i(super.a);
        layout.setConstraints(i, gridBagConstraints);
        this.add(i);
        final I j = new I(aS.a(27), "name");
        final I k = new I(null, "lock");
        final I l = new I(a.a("usersIcon.gif", false, 20), "users");
        final I m = new I(null, "arrow");
        k.b(0);
        m.b(1);
        k.c(10);
        m.c(11);
        j.a(120);
        l.b = true;
        j.b = true;
        super.a.a();
        super.a.b(m);
        super.a.b(k);
        super.a.b(j);
        super.a.b(l);
        super.a.a(l);
    }
}
