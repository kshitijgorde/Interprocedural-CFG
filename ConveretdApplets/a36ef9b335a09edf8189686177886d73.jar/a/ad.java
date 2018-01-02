// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;

public final class ad extends aa
{
    private ab q;
    private ab w;
    
    public ad() {
        this.q = null;
        this.w = null;
    }
    
    public ad(final ab q, final ab w) {
        this.q = q;
        this.w = w;
    }
    
    public final void q(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        int n5 = n;
        int n6 = n2;
        int n7 = n3;
        int n8 = n4;
        if (this.q != null) {
            this.q.q(component, graphics, n, n2, n3, n4);
            final Insets q = this.q.q(component);
            n5 = n + q.left;
            n6 = n2 + q.top;
            n7 = n3 - q.right - q.left;
            n8 = n4 - q.bottom - q.top;
        }
        if (this.w != null) {
            this.w.q(component, graphics, n5, n6, n7, n8);
        }
    }
    
    public final Insets q(final Component component) {
        final Insets insets5;
        final Insets insets4;
        final Insets insets3;
        final Insets insets2;
        final Insets insets = insets2 = (insets3 = (insets4 = (insets5 = new Insets(0, 0, 0, 0))));
        final boolean b = false;
        insets2.bottom = (b ? 1 : 0);
        insets3.right = (b ? 1 : 0);
        insets4.left = (b ? 1 : 0);
        insets5.top = (b ? 1 : 0);
        if (this.q != null) {
            final Insets q = this.q.q(component);
            final Insets insets6 = insets;
            insets6.top += q.top;
            final Insets insets7 = insets;
            insets7.left += q.left;
            final Insets insets8 = insets;
            insets8.right += q.right;
            final Insets insets9 = insets;
            insets9.bottom += q.bottom;
        }
        if (this.w != null) {
            final Insets q2 = this.w.q(component);
            final Insets insets10 = insets;
            insets10.top += q2.top;
            final Insets insets11 = insets;
            insets11.left += q2.left;
            final Insets insets12 = insets;
            insets12.right += q2.right;
            final Insets insets13 = insets;
            insets13.bottom += q2.bottom;
        }
        return insets;
    }
}
