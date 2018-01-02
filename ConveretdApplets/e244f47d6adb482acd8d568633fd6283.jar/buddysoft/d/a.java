// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.d;

import java.awt.Component;
import buddysoft.c.d;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;

public class a extends Panel
{
    public a(final buddysoft.a.a a) {
        this.setLayout(new BorderLayout(0, 0));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 8));
        panel.setForeground(a.d);
        panel.add(new d("reset", a.case, a.e));
        panel.add(new d("play", a.case, a.c));
        panel.add(new d("prev", a.case, a.else));
        panel.add(new d("next", a.case, a.goto));
        panel.add(new d("stop", a.case, a.long));
        panel.add(new d("pause", a.case, a.try));
        this.add("East", panel);
    }
}
