// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class cY extends cv
{
    protected bJ q;
    protected ap w;
    protected aG q;
    
    public cY(final Component q, final ap w) {
        super.q = q;
        this.w = w;
    }
    
    public final void q(final int n, final int n2, final bJ q) {
        this.q = q;
        this.w();
        this.e();
        this.q = new aG(this.w, ((bp)q).s);
        if ((dN.q() && !bC.w.y()) || (dN.w() && !bC.w.i())) {
            super.q(n, n2);
        }
    }
}
