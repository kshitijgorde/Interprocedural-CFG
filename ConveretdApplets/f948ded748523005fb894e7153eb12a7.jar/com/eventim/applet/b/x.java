// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

final class x extends JPanel
{
    private final ag a;
    private final Color b;
    
    x(final ag a, final Color b) {
        this.a = a;
        this.b = b;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle bounds = this.getBounds();
        final GeneralPath generalPath;
        (generalPath = new GeneralPath()).moveTo(bounds.x, 0.0f);
        generalPath.lineTo(bounds.x, bounds.height - 3);
        generalPath.lineTo(bounds.x + 2, bounds.height - 1);
        generalPath.lineTo(bounds.x + bounds.width - 2, bounds.height - 1);
        generalPath.lineTo(bounds.x + bounds.width, bounds.height - 3);
        generalPath.lineTo(bounds.x + bounds.width, 0.0f);
        graphics2D.setPaint(Color.white);
        graphics2D.fill(generalPath);
        if (ag.a(this.a)) {
            final GeneralPath generalPath2;
            (generalPath2 = new GeneralPath()).moveTo(bounds.x, 0.0f);
            generalPath2.lineTo(bounds.x, bounds.height - 3);
            generalPath2.lineTo(bounds.x + 2, bounds.height - 1);
            generalPath2.lineTo(bounds.x + bounds.width - 3, bounds.height - 1);
            generalPath2.lineTo(bounds.x + bounds.width - 1, bounds.height - 3);
            generalPath2.lineTo(bounds.x + bounds.width - 1, 0.0f);
            graphics2D.setPaint(this.b);
            graphics2D.draw(generalPath2);
        }
        this.paintChildren(graphics);
    }
}
