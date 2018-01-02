// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;

public final class ae extends aa
{
    private Color q;
    private int q;
    private int w;
    private int e;
    private int r;
    
    public ae(final int n, final int n2, final int n3, final int n4, final Color q) {
        this.e = 1;
        this.w = 1;
        this.r = 1;
        this.q = 1;
        this.q = q;
    }
    
    public final void q(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Insets q = this.q(component);
        final Color color = graphics.getColor();
        graphics.translate(n, n2);
        if (this.q != null) {
            graphics.setColor(this.q);
            graphics.fillRect(0, 0, n3 - q.right, q.top);
            graphics.fillRect(0, q.top, q.left, n4 - q.top);
            graphics.fillRect(q.left, n4 - q.bottom, n3 - q.left, q.bottom);
            graphics.fillRect(n3 - q.right, 0, q.right, n4 - q.bottom);
        }
        graphics.translate(-n, -n2);
        graphics.setColor(color);
    }
    
    public final Insets q(final Component component) {
        return this.q(new Insets(0, 0, 0, 0));
    }
    
    private Insets q(final Insets insets) {
        insets.left = this.q;
        insets.top = this.e;
        insets.right = this.w;
        insets.bottom = this.r;
        return insets;
    }
}
