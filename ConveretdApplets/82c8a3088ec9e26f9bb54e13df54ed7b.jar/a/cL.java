// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class cL extends Panel
{
    public be q;
    protected W q;
    
    public final boolean q(final ar ar) {
        return this.q.q((aW)ar);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    protected final void q(final ar ar) {
        if (!ar.q(61)) {
            ar.q(62);
        }
        final Color[] q = l.q(ar);
        this.q.q(ar, q[0], q[1]);
        if (ar.e) {
            this.q.q(ar, true);
            return;
        }
        this.q.q(ar, false);
    }
    
    public void q(final ar ar, final boolean b) {
        synchronized (this.q) {
            if ((!ar.q(23) || this.q.q(24)) && (!ar.q(25) || this.q.q(32))) {
                final int q;
                if ((q = this.q.q((aW)ar)) == -1) {
                    if (b) {
                        this.q.q((aW)ar);
                    }
                }
                else {
                    this.q.q(ar, q);
                }
                this.q(ar);
            }
        }
    }
    
    public cL(final W q) {
        this.q = q;
        this.q = new be(q);
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
