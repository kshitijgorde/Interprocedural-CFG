// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

final class y extends JPanel
{
    private final Color a;
    private final Color b;
    
    y(final Color b, final Color a) {
        this.b = b;
        this.a = a;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle bounds = this.getBounds();
        final GeneralPath generalPath;
        (generalPath = new GeneralPath()).moveTo(bounds.x, bounds.y + 20);
        generalPath.lineTo(bounds.x, bounds.y + 2);
        generalPath.lineTo(bounds.x + 2, bounds.y);
        generalPath.lineTo(bounds.x + bounds.width - 2, bounds.y);
        generalPath.lineTo(bounds.x + bounds.width, bounds.y + 2);
        generalPath.lineTo(bounds.x + bounds.width, bounds.y + 20);
        graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.b, new Point(0, 20), this.a));
        graphics2D.fill(generalPath);
        this.paintChildren(graphics);
    }
}
