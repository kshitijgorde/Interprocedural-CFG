// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;

public abstract class bR extends bt
{
    protected aY q;
    protected W w;
    protected S q;
    
    public bR(final Component q, final W w) {
        super.q = q;
        this.w = w;
    }
    
    public final void q(final int n, final int n2, final aY q) {
        this.q = q;
        this.e();
        this.w();
        this.q = new S(this.w, ((aJ)q).s);
        if ((cu.q() && !aU.w.y()) || (cu.w() && !aU.w.i())) {
            super.q(n, n2);
        }
    }
}
