// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

final class cA extends cv
{
    private cA(final cz cz, final byte b) {
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Dimension size = this.size();
        graphics.setColor(aT.w.q.darker());
        graphics.drawLine(10, 35, size.width - 12, 35);
        graphics.setColor(aT.w.q.brighter());
        graphics.drawLine(10, 36, size.width - 12, 36);
        graphics.setColor(aT.w.t);
        graphics.setFont(new Font("Dialog", 1, 16));
        graphics.drawString(al.q(ce.q("t>F9D5K@1BD939@1>DC")), 50, 25);
    }
    
    cA(final cz cz) {
        this(cz, (byte)0);
    }
}
