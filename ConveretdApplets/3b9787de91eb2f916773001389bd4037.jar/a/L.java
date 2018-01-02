// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

final class L extends q
{
    private L(final K k, final byte b) {
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.size();
        graphics.setColor(be.w.q.darker());
        graphics.drawLine(10, 35, size.width - 12, 35);
        graphics.setColor(be.w.q.brighter());
        graphics.drawLine(10, 36, size.width - 12, 36);
        graphics.setColor(be.w.t);
        graphics.setFont(new Font("Dialog", 1, 16));
        graphics.drawString(cv.q(cl.q("t>F9D5K@1BD939@1>DC")), 50, 25);
    }
    
    L(final K k) {
        this(k, (byte)0);
    }
}
