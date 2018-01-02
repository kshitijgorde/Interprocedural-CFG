// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class aM extends aO
{
    protected aq q;
    protected bI q;
    protected cj q;
    
    public aM(final Component q, final bI q2) {
        super.q = q;
        this.q = q2;
    }
    
    public final void q(final int n, final int n2, final aq q) {
        this.q = q;
        this.t();
        this.y();
        this.q = new cj(this.q, ((ba)q).q());
        if ((a.q() && !be.w.y()) || (a.w() && !be.w.i())) {
            super.q(n, n2);
        }
    }
}
