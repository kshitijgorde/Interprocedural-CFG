// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public final class V extends r
{
    public V(final ad ad) {
        super(ad);
    }
    
    public final void q() {
        final B q2;
        final int q;
        if ((q = (q2 = ((ad)this.q).q.q()).q) == 0) {
            return;
        }
        final int n = (int)Math.sqrt(q);
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(n, n + 1));
        this.add(panel);
        for (int i = 0; i < q; ++i) {
            final ao ao;
            if ((ao = (ao)q2.q(i)).q(0)) {
                final Color color = new Color(ao.w());
                final A a = new A(this, color);
                panel.add(a);
                this.q.put(color, a);
            }
        }
    }
}
