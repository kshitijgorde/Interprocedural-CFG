// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class cK extends Panel
{
    public bf q;
    protected W q;
    
    public final boolean q(final as as) {
        return this.q.q((aX)as);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    protected final void q(final as as) {
        if (!as.q(61)) {
            as.q(62);
        }
        final Color[] q = l.q(as);
        this.q.q(as, q[0], q[1]);
        if (as.e) {
            this.q.q(as, true);
            return;
        }
        this.q.q(as, false);
    }
    
    public void q(final as as, final boolean b) {
        synchronized (this.q) {
            if ((!as.q(23) || this.q.q(24)) && (!as.q(25) || this.q.q(32))) {
                final int q;
                if ((q = this.q.q((aX)as)) == -1) {
                    if (b) {
                        this.q.q((aX)as);
                    }
                }
                else {
                    this.q.q(as, q);
                }
                this.q(as);
            }
        }
    }
    
    public cK(final W q) {
        this.q = q;
        this.q = new bf(q);
    }
    
    public final Vector q() {
        return this.q.q();
    }
    
    public final Object q() {
        return this.q.q();
    }
    
    public final void w() {
        this.q.y();
    }
    
    static {
        new Color(153);
        new Color(10079487);
        new Color(16711680);
        new Color(10079487);
        new Color(39168);
        new Color(245, 245, 0);
        new Color(0, 200, 200);
        new Color(200, 0, 200);
        new Color(230, 175, 0);
        new Color(255, 130, 130);
        new Color(160, 160, 160);
    }
}
