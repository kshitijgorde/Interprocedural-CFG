// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Shape;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.GradientPaint;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import com.eventim.applet.a.i;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.LayoutManager;
import java.awt.Color;
import javax.swing.Icon;
import com.eventim.applet.EventimApplet;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public final class c extends JPanel implements MouseListener
{
    private int a;
    private int b;
    private boolean c;
    private EventimApplet d;
    
    public c(final String s, final Icon icon, final Color background, final EventimApplet d) {
        this.c = false;
        this.d = d;
        this.setBackground(background);
        if (d.d().l().trim().length() == 0) {
            this.c = true;
            this.setLayout(null);
            final Graphics graphics;
            final int n = ((graphics = d.getGraphics()) == null) ? 120 : (SwingUtilities.computeStringWidth(graphics.getFontMetrics(), s) + (EventimApplet.l() ? 8 : 20));
            final JLabel label;
            (label = new JLabel("<html><b>" + s + "</b></html>")).setForeground(d.d().b());
            label.setBounds(new Rectangle(8, 8, n, 16));
            this.add(label);
            final JLabel label2 = new JLabel(icon);
            this.b = n - 3;
            label2.setBounds(new Rectangle(this.b, 3, 41, 26));
            this.add(label2);
            this.a = n + label2.getWidth();
            this.setPreferredSize(new Dimension(this.a, 33));
        }
        else {
            this.add(new JLabel(i.a(d.d().l(), d.d())));
        }
        this.addMouseListener(this);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.d.o();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        if (this.c) {
            final Graphics2D graphics2D;
            (graphics2D = (Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            final RoundRectangle2D.Double double1 = new RoundRectangle2D.Double(0.0, 5.0, this.a, 22.0, 12.0, 12.0);
            final Ellipse2D.Double double2 = new Ellipse2D.Double(this.b - 3.5, 0.0, 32.0, 32.0);
            graphics2D.setPaint(new GradientPaint(new Point(0, 0), this.d.d().L(), new Point(0, 32), this.d.d().K()));
            graphics2D.fill(double1);
            graphics2D.fill(double2);
            this.paintChildren(graphics);
        }
    }
}
