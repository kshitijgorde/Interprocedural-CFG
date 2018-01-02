// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Panel;

public final class aB extends au
{
    public aB(final ax ax) {
        super(ax);
    }
    
    public final void q() {
        final dW q2;
        final int q;
        if ((q = (q2 = ((ax)this.q).q.q()).q()) == 0) {
            return;
        }
        final int n = (int)Math.sqrt(q);
        final Panel panel;
        (panel = new Panel()).setLayout(new GridLayout(n, n + 1));
        this.add(panel);
        for (int i = 0; i < q; ++i) {
            final Color color = new Color(((ce)q2.q(i)).y());
            final av av = new av(this, color);
            panel.add(av);
            this.q.put(color, av);
        }
    }
}
