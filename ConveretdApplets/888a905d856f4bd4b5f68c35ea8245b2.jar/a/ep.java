// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class ep extends Panel
{
    public cc q;
    protected ap q;
    
    public final boolean q(final aO ao) {
        return this.q.q((bJ)ao);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    protected final void q(final aO ao) {
        if (!ao.q(61)) {
            ao.q(62);
        }
        final Color[] q = p.q(ao);
        this.q.q(ao, q[0], q[1]);
        if (ao.e) {
            this.q.w(ao, true);
            return;
        }
        this.q.w(ao, false);
    }
    
    public void q(final aO ao, final boolean b) {
        synchronized (this.q) {
            if ((!ao.q(23) || this.q.q(24)) && (!ao.q(25) || this.q.q(32))) {
                final int q;
                if ((q = this.q.q((bJ)ao)) == -1) {
                    if (b) {
                        this.q.e(ao);
                    }
                }
                else {
                    this.q.q(ao, q);
                }
                this.q(ao);
            }
        }
    }
    
    public ep(final ap q) {
        this.q = q;
        this.q = new cc(q);
    }
    
    public final Vector q() {
        return this.q.q();
    }
    
    public final Object q() {
        return this.q.q();
    }
    
    public final void e() {
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
