// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

public final class e extends Canvas
{
    private Color q;
    private Color w;
    private int q;
    
    public e(final Color q, final Color w, final int n) {
        if (q == null) {
            this.q = this.getBackground().brighter();
        }
        else {
            this.q = q;
        }
        if (w == null) {
            this.w = this.getBackground().darker();
        }
        else {
            this.w = w;
        }
        this.q = 1;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final int width = this.getBounds().width;
        graphics.setColor((this.q == 0) ? this.w : this.q);
        graphics.drawRect(0, 0, width - 2, 0);
        graphics.setColor((this.q == 0) ? this.q : this.w);
        graphics.drawLine(1, 1, width - 3, 1);
    }
}
