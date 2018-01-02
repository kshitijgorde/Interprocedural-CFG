// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Color;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.JPanel;

final class ab extends JPanel
{
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
        this.paintChildren(graphics);
    }
}
