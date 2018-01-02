// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class bW extends Panel
{
    public u q;
    protected bI q;
    
    public final boolean q(final bV bv) {
        return this.q.q((aq)bv);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    protected final void q(final bV bv) {
        if (!bv.q(61)) {
            bv.q(62);
        }
        final Color[] q = bp.q(bv);
        this.q.q(bv, q[0], q[1]);
        if (bv.e) {
            this.q.q(bv, true);
            return;
        }
        this.q.q(bv, false);
    }
    
    public void q(final bV bv, final boolean b) {
        synchronized (this.q) {
            if ((!bv.q(23) || this.q.q(24)) && (!bv.q(25) || this.q.q(32))) {
                final int q;
                if ((q = this.q.q((aq)bv)) == -1) {
                    if (b) {
                        this.q.q((aq)bv);
                    }
                }
                else {
                    this.q.q(bv, q);
                }
                this.q(bv);
            }
        }
    }
    
    public bW(final bI q) {
        this.q = q;
        this.q = new u(q);
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
