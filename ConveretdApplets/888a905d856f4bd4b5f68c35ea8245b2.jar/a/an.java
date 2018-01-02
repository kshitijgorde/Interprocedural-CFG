// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public final class an extends y
{
    public an(final av av) {
        super(av);
    }
    
    public final void q() {
        final M q2;
        final int q;
        if ((q = (q2 = ((av)this.q).q.q()).q) == 0) {
            return;
        }
        final int n = (int)Math.sqrt(q);
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(n, n + 1));
        this.add(panel);
        for (int i = 0; i < q; ++i) {
            final aJ aj;
            if ((aj = (aJ)q2.q(i)).q(0)) {
                final Color color = new Color(aj.w());
                final K k = new K(this, color);
                panel.add(k);
                this.q.put(color, k);
            }
        }
    }
}
