// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.ap;
import com.diginet.digichat.awt.t;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.a5;
import com.esial.util.c;
import com.diginet.digichat.common.bg;
import com.diginet.digichat.util.s;

public class bw extends bx implements s
{
    public String a(final Object o) {
        if (o != super.c) {
            return null;
        }
        final bg bg = (bg)super.e.g();
        if (super.c.a()) {
            return a5.a(com.esial.util.c.a("Click here to enter %1: %2."), new String[] { bg.x(), bg.a });
        }
        if (bg == null) {
            return com.esial.util.c.a("This button is disabled because no room is selected.");
        }
        return a5.a(com.esial.util.c.a("This button is disabled because you are already in %1."), new String[] { bg.x() });
    }
    
    public bw(final i f) {
        super.f = f;
        super.e.resize(141, 140);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 0.5;
        if (f.i(29) || f.i(38) || f.i(27) || f.i(28)) {
            gridBagConstraints.gridwidth = -1;
            layout.setConstraints(super.d, gridBagConstraints);
            super.d.a(com.esial.util.c.a("Click here to create a new room."), null);
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
        final t t = new t(super.e);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        final ap ap = new ap(i.imgPointer, "current");
        final ap ap2 = new ap(i.c, "lock");
        final ap ap3 = new ap(com.esial.util.c.a("Name"), "name");
        final ap ap4 = new ap(f.a("usersIcon.gif", false, 20), "users");
        ap.c(0);
        ap.d(10);
        ap2.c(1);
        ap2.d(12);
        ap3.b(100);
        ap4.c(true);
        ap3.c(true);
        super.e.a(true);
        super.e.b(ap);
        super.e.b(ap2);
        super.e.b(ap3);
        super.e.b(ap4);
        super.e.a(ap4);
    }
}
