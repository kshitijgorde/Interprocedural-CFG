// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

final class dZ extends dT
{
    private dZ(final ea ea, final byte b) {
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.size();
        graphics.setColor(bC.w.q.darker());
        graphics.drawLine(10, 35, size.width - 12, 35);
        graphics.setColor(bC.w.q.brighter());
        graphics.drawLine(10, 36, size.width - 12, 36);
        graphics.setColor(bC.w.t);
        graphics.setFont(new Font("Dialog", 1, 16));
        graphics.drawString(be.w(ds.q("t>F9D5K@1BD939@1>DC")), 50, 25);
    }
    
    dZ(final ea ea) {
        this(ea, (byte)0);
    }
}
