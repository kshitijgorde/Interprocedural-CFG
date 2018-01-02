// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import java.awt.Component;
import javax.swing.JList;
import java.awt.Insets;
import java.awt.BasicStroke;
import java.awt.geom.Line2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Stroke;
import javax.swing.ListCellRenderer;
import javax.swing.JComponent;

public class StrokeSample extends JComponent implements ListCellRenderer
{
    private Stroke stroke;
    private Dimension preferredSize;
    
    public StrokeSample(final Stroke stroke) {
        this.stroke = stroke;
        this.preferredSize = new Dimension(80, 18);
    }
    
    public Stroke getStroke() {
        return this.stroke;
    }
    
    public void setStroke(final Stroke stroke) {
        this.stroke = stroke;
        this.repaint();
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final Dimension size = this.getSize();
        final Insets insets = this.getInsets();
        final double n = insets.left;
        final double n2 = insets.top;
        final double n3 = size.getWidth() - insets.left - insets.right;
        final double n4 = size.getHeight() - insets.top - insets.bottom;
        final Point2D.Double double1 = new Point2D.Double(n + 6.0, n2 + n4 / 2.0);
        final Point2D.Double double2 = new Point2D.Double(n + n3 - 6.0, n2 + n4 / 2.0);
        final Ellipse2D.Double double3 = new Ellipse2D.Double(double1.getX() - 5.0, double1.getY() - 5.0, 10.0, 10.0);
        final Ellipse2D.Double double4 = new Ellipse2D.Double(double2.getX() - 6.0, double2.getY() - 5.0, 10.0, 10.0);
        graphics2D.draw(double3);
        graphics2D.fill(double3);
        graphics2D.draw(double4);
        graphics2D.fill(double4);
        final Line2D.Double double5 = new Line2D.Double(double1, double2);
        if (this.stroke != null) {
            graphics2D.setStroke(this.stroke);
        }
        else {
            graphics2D.setStroke(new BasicStroke(0.0f));
        }
        graphics2D.draw(double5);
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o instanceof StrokeSample) {
            this.setStroke(((StrokeSample)o).getStroke());
        }
        return this;
    }
}
