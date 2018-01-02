// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

final class cB extends cw
{
    private cB(final cA ca, final byte b) {
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.size();
        graphics.setColor(aS.w.q.darker());
        graphics.drawLine(10, 35, size.width - 12, 35);
        graphics.setColor(aS.w.q.brighter());
        graphics.drawLine(10, 36, size.width - 12, 36);
        graphics.setColor(aS.w.t);
        graphics.setFont(new Font("Dialog", 1, 16));
        graphics.drawString(ak.q(ce.q("t>F9D5K@1BD939@1>DC")), 50, 25);
    }
    
    cB(final cA ca) {
        this(ca, (byte)0);
    }
}
