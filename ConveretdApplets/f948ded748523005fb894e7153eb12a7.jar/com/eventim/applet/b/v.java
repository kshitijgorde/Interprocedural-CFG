// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Rectangle;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

final class v extends JPanel
{
    private final ag a;
    private final Color b;
    
    v(final ag a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (ag.a(this.a)) {
            final Graphics2D graphics2D = (Graphics2D)graphics;
            final Rectangle bounds = this.getBounds();
            graphics2D.setPaint(this.b);
            graphics2D.drawLine(0, -1, 0, bounds.height);
            graphics2D.drawLine(bounds.width - 1, -1, bounds.width - 1, bounds.height);
            this.paintChildren(graphics);
        }
    }
}
