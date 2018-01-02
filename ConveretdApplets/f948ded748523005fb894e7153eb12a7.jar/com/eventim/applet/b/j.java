// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.eventim.applet.EventimApplet;
import java.awt.Color;
import javax.swing.JPanel;

public final class j extends JPanel
{
    private Color a;
    private Color b;
    
    public j(final String s, final Color foreground, final Color a, final Color b, final EventimApplet eventimApplet) {
        this.a = a;
        this.b = b;
        this.setLayout(new BorderLayout());
        final JLabel label;
        (label = new JLabel(s, 0)).setForeground(foreground);
        final int n = SwingUtilities.computeStringWidth(eventimApplet.getGraphics().getFontMetrics(), s) + 4;
        this.add(label, "Center");
        this.setPreferredSize(new Dimension(n, 20));
        this.setMinimumSize(new Dimension(n, 20));
        this.setMaximumSize(new Dimension(n, 20));
        this.setOpaque(false);
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle bounds = this.getBounds();
        final RoundRectangle2D.Double double1 = new RoundRectangle2D.Double(0.0, 0.0, bounds.width - 1, bounds.height - 1, 2.0, 2.0);
        graphics2D.setPaint(this.a);
        graphics2D.fill(double1);
        graphics2D.setPaint(this.b);
        graphics2D.draw(double1);
        graphics2D.setPaint(this.getForeground());
        this.paintChildren(graphics);
    }
}
