// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Panel;

public class dk extends Panel
{
    public w q;
    protected cV q;
    
    public final boolean q(final dj dj) {
        return this.q.q((aF)dj);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    protected final void q(final dj dj) {
        if (!dj.q(61)) {
            dj.q(62);
        }
        final Color[] q = cz.q(dj);
        this.q.q(dj, q[0], q[1]);
        if (dj.r) {
            this.q.w(dj, true);
            return;
        }
        this.q.w(dj, false);
    }
    
    public void q(final dj dj, final boolean b) {
        synchronized (this.q) {
            if ((!dj.q(23) || this.q.q(24)) && (!dj.q(25) || this.q.q(32))) {
                final int q;
                if ((q = this.q.q((aF)dj)) == -1) {
                    if (b) {
                        this.q.e(dj);
                    }
                }
                else {
                    this.q.q(dj, q);
                }
                this.q(dj);
            }
        }
    }
    
    public dk(final cV q) {
        this.q = q;
        this.q = new w(q);
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
