// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public final class U extends q
{
    public U(final ac ac) {
        super(ac);
    }
    
    public final void q() {
        final A q2;
        final int q;
        if ((q = (q2 = ((ac)this.q).q.q()).q) == 0) {
            return;
        }
        final int n = (int)Math.sqrt(q);
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(n, n + 1));
        this.add(panel);
        for (int i = 0; i < q; ++i) {
            final an an;
            if ((an = (an)q2.q(i)).q(0)) {
                final Color color = new Color(an.w());
                final z z = new z(this, color);
                panel.add(z);
                this.q.put(color, z);
            }
        }
    }
}
