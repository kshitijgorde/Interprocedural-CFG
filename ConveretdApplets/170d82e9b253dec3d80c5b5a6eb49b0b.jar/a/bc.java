// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class bc extends bf
{
    protected aF q;
    protected cV q;
    protected dP q;
    
    public bc(final Component q, final cV q2) {
        super.q = q;
        this.q = q2;
    }
    
    public final void q(final int n, final int n2, final aF q) {
        this.q = q;
        this.t();
        this.y();
        this.q = new dP(this.q, ((bZ)q).q());
        if ((a.q() && !cf.w.y()) || (a.w() && !cf.w.i())) {
            super.q(n, n2);
        }
    }
}
