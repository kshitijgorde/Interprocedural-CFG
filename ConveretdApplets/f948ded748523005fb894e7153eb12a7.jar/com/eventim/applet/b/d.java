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
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.JPanel;

final class d extends JPanel
{
    private final r a;
    private final Color b;
    private final Color c;
    private final Color d;
    private final Color e;
    
    d(final r a, final LayoutManager layoutManager, final Color c, final Color b, final Color e, final Color d) {
        super(layoutManager);
        this.a = a;
        this.c = c;
        this.b = b;
        this.e = e;
        this.d = d;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle bounds = this.getBounds();
        if (r.a(this.a) == 0) {
            final GeneralPath generalPath;
            (generalPath = new GeneralPath()).moveTo(bounds.x, bounds.y + 20);
            generalPath.lineTo(bounds.x, bounds.y + 2);
            generalPath.lineTo(bounds.x + 2, bounds.y);
            generalPath.lineTo(bounds.x + bounds.width / 2 - 2, bounds.y);
            generalPath.lineTo(bounds.x + bounds.width / 2, bounds.y + 2);
            generalPath.lineTo(bounds.x + bounds.width / 2, bounds.y + 20);
            final GeneralPath generalPath2;
            (generalPath2 = new GeneralPath()).moveTo(bounds.x + bounds.width / 2 - 1, bounds.y + 20);
            generalPath2.lineTo(bounds.x + bounds.width / 2 - 1, bounds.y + 1);
            generalPath2.lineTo(bounds.x + bounds.width - 2, bounds.y + 1);
            generalPath2.lineTo(bounds.x + bounds.width, bounds.y + 3);
            generalPath2.lineTo(bounds.x + bounds.width, bounds.y + 20);
            graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.c, new Point(0, 20), this.b));
            graphics2D.fill(generalPath);
            graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.e, new Point(0, 20), this.d));
            graphics2D.fill(generalPath2);
        }
        else {
            final GeneralPath generalPath3;
            (generalPath3 = new GeneralPath()).moveTo(bounds.x, bounds.y + 20);
            generalPath3.lineTo(bounds.x, bounds.y + 3);
            generalPath3.lineTo(bounds.x + 2, bounds.y + 1);
            generalPath3.lineTo(bounds.x + bounds.width / 2 - 1, bounds.y + 1);
            generalPath3.lineTo(bounds.x + bounds.width / 2 - 1, bounds.y + 20);
            final GeneralPath generalPath4;
            (generalPath4 = new GeneralPath()).moveTo(bounds.x + bounds.width / 2 - 1, bounds.y + 20);
            generalPath4.lineTo(bounds.x + bounds.width / 2 - 1, bounds.y + 1);
            generalPath4.lineTo(bounds.x + bounds.width / 2, bounds.y);
            generalPath4.lineTo(bounds.x + bounds.width - 2, bounds.y);
            generalPath4.lineTo(bounds.x + bounds.width, bounds.y + 2);
            generalPath4.lineTo(bounds.x + bounds.width, bounds.y + 20);
            graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.e, new Point(0, 20), this.d));
            graphics2D.fill(generalPath3);
            graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.c, new Point(0, 20), this.b));
            graphics2D.fill(generalPath4);
        }
        this.paintChildren(graphics);
    }
}
