// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class bQ extends bs
{
    protected aX q;
    protected W w;
    protected T q;
    
    public bQ(final Component q, final W w) {
        super.q = q;
        this.w = w;
    }
    
    public final void q(final int n, final int n2, final aX q) {
        this.q = q;
        this.e();
        this.w();
        this.q = new T(this.w, ((aK)q).a);
        if ((cs.q() && !aT.w.y()) || (cs.w() && !aT.w.i())) {
            super.q(n, n2);
        }
    }
}
