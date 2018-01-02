// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public final class am extends af
{
    public am(final ai ai) {
        super(ai);
    }
    
    public final void q() {
        final cq q2;
        final int q;
        if ((q = (q2 = ((ai)this.q).q.q()).q()) == 0) {
            return;
        }
        final int n = (int)Math.sqrt(q);
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(n, n + 1));
        this.add(panel);
        for (int i = 0; i < q; ++i) {
            final Color color = new Color(((bd)q2.q(i)).r());
            final ag ag = new ag(this, color);
            panel.add(ag);
            this.q.put(color, ag);
        }
    }
}
