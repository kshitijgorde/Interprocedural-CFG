// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class bP extends br
{
    protected aW q;
    protected W w;
    protected S q;
    
    public bP(final Component q, final W w) {
        super.q = q;
        this.w = w;
    }
    
    public final void q(final int n, final int n2, final aW q) {
        this.q = q;
        this.e();
        this.w();
        this.q = new S(this.w, ((aJ)q).s);
        if ((cs.q() && !aS.w.y()) || (cs.w() && !aS.w.i())) {
            super.q(n, n2);
        }
    }
}
