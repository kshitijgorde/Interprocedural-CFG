// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.pie;

import java.awt.font.GlyphVector;
import java.util.Iterator;
import java.awt.Shape;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Arc2D;
import org.xidget.chart.Point;
import java.util.List;
import org.xidget.ifeature.IPointsFeature;
import javax.swing.JPanel;

public class PieChart extends JPanel implements IPointsFeature
{
    private List<Point> points;
    private double total;
    private Arc2D.Double arc;
    private Path2D.Double path;
    private List<Color> colors;
    private List<Slice> q1;
    private List<Slice> q2;
    private List<Slice> q3;
    private List<Slice> q4;
    private List<Slice> slices;
    
    public PieChart() {
        this.points = new ArrayList<Point>();
        this.arc = new Arc2D.Double();
        this.path = new Path2D.Double();
        this.setFont(this.getFont().deriveFont(10.0f));
    }
    
    @Override
    public void add(final Point point) {
        this.add(this.points.size(), point);
    }
    
    @Override
    public void add(final int n, final Point point) {
        if (n < 0 || n > this.points.size()) {
            return;
        }
        if (n > 0) {
            final Point prev = this.points.get(n - 1);
            if (prev.next != null) {
                prev.next.prev = point;
            }
            prev.next = point;
            point.prev = prev;
            point.next = prev.next;
        }
        this.total += point.coords[0];
        this.points.add(n, point);
        this.q1 = null;
        this.repaint();
    }
    
    @Override
    public void remove(final int n) {
        this.total -= this.points.remove(n).coords[0];
        this.q1 = null;
        this.repaint();
    }
    
    @Override
    public void update(final Point point, final int n, final double n2) {
        this.total -= point.coords[n];
        point.coords[n] = n2;
        this.total += n2;
        this.q1 = null;
        this.repaint();
    }
    
    @Override
    public void update(final Point point, final String label) {
        point.label = label;
        this.repaint();
    }
    
    private void computeColors() {
        this.colors = new ArrayList<Color>(this.points.size());
        for (int i = 0; i < this.points.size(); ++i) {
            this.colors.add(Color.getHSBColor((i + 0.8f) / this.points.size(), 0.5f, 0.9f));
        }
    }
    
    private void computeSlices(final Graphics2D graphics2D) {
        final Font font = graphics2D.getFont();
        final FontRenderContext fontRenderContext = graphics2D.getFontRenderContext();
        this.q1 = new ArrayList<Slice>();
        this.q2 = new ArrayList<Slice>();
        this.q3 = new ArrayList<Slice>();
        this.q4 = new ArrayList<Slice>();
        this.slices = new ArrayList<Slice>();
        double startAngle = 0.0;
        for (int i = 0; i < this.points.size(); ++i) {
            final Point point = this.points.get(i);
            final Slice slice = new Slice(null);
            slice.label = font.createGlyphVector(fontRenderContext, point.label);
            slice.startAngle = startAngle;
            slice.angleExtent = point.coords[0] / this.total;
            final double n = (slice.startAngle + slice.angleExtent / 2.0) * 6.283185307179586;
            slice.midUX = Math.cos(n);
            slice.midUY = Math.sin(n);
            if (slice.midUX < 0.0) {
                if (slice.midUY < 0.0) {
                    this.q3.add(slice);
                }
                else {
                    this.q2.add(0, slice);
                }
            }
            else if (slice.midUY < 0.0) {
                this.q4.add(0, slice);
            }
            else {
                this.q1.add(slice);
            }
            this.slices.add(slice);
            startAngle += slice.angleExtent;
        }
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.q1 == null) {
            this.computeSlices(graphics2D);
        }
        if (this.colors == null || this.colors.size() != this.points.size()) {
            this.computeColors();
        }
        final int width = this.getWidth();
        final int height = this.getHeight();
        final int n = (width < height) ? width : height;
        final double n2 = this.getWidth() / 2.0;
        final double n3 = this.getHeight() / 2.0;
        final double n4 = n / 2.0 - 25.0;
        int n5 = 0;
        for (final Slice slice : this.slices) {
            this.arc.setArcByCenter(n2, n3, n4, -slice.startAngle * 360.0, -slice.angleExtent * 360.0 - 1.0, 2);
            graphics2D.setColor(this.colors.get(n5++));
            graphics2D.fill(this.arc);
        }
        final double n6 = n4 + 10.0;
        final double n7 = 3 + graphics2D.getFontMetrics().getHeight();
        final double n8 = n4 + 10.0;
        double n9 = -n7 / 2.0;
        for (final Slice slice2 : this.q1) {
            double n10 = n6 * slice2.midUY;
            if (n10 - n9 < n7) {
                n10 = n9 + n7;
            }
            final double n11 = n4 * slice2.midUX;
            final double n12 = n4 * slice2.midUY;
            final double n13 = n10 * slice2.midUX / slice2.midUY;
            final double n14 = 10.0;
            final double n15 = slice2.label.getVisualBounds().getHeight() / 2.0;
            this.path.reset();
            this.path.moveTo(n2 + n11, n3 + n12);
            if (n13 < n8) {
                this.path.lineTo(n2 + n13, n3 + n10);
            }
            this.path.lineTo(n2 + n8, n3 + n10);
            graphics2D.setColor(Color.gray);
            graphics2D.draw(this.path);
            graphics2D.setColor(Color.black);
            graphics2D.drawGlyphVector(slice2.label, (float)(n2 + n8 + n14), (float)(n3 + n10 + n15));
            n9 = n10;
        }
        double n16 = n7 / 2.0;
        for (final Slice slice3 : this.q4) {
            double n17 = n6 * slice3.midUY;
            if (n16 - n17 < n7) {
                n17 = n16 - n7;
            }
            final double n18 = n4 * slice3.midUX;
            final double n19 = n4 * slice3.midUY;
            final double n20 = n17 * slice3.midUX / slice3.midUY;
            final double n21 = 10.0;
            final double n22 = slice3.label.getVisualBounds().getHeight() / 2.0;
            this.path.reset();
            this.path.moveTo(n2 + n18, n3 + n19);
            if (n20 < n8) {
                this.path.lineTo(n2 + n20, n3 + n17);
            }
            this.path.lineTo(n2 + n8, n3 + n17);
            graphics2D.setColor(Color.gray);
            graphics2D.draw(this.path);
            graphics2D.setColor(Color.black);
            graphics2D.drawGlyphVector(slice3.label, (float)(n2 + n8 + n21), (float)(n3 + n17 + n22));
            n16 = n17;
        }
        final double n23 = -(n4 + 10.0);
        double n24 = -n7 / 2.0;
        for (final Slice slice4 : this.q2) {
            double n25 = n6 * slice4.midUY;
            if (n25 - n24 < n7) {
                n25 = n24 + n7;
            }
            final double n26 = n4 * slice4.midUX;
            final double n27 = n4 * slice4.midUY;
            final double n28 = n25 * slice4.midUX / slice4.midUY;
            final double n29 = -slice4.label.getVisualBounds().getWidth() - 10.0;
            final double n30 = slice4.label.getVisualBounds().getHeight() / 2.0;
            this.path.reset();
            this.path.moveTo(n2 + n26, n3 + n27);
            if (n28 > n23) {
                this.path.lineTo(n2 + n28, n3 + n25);
            }
            this.path.lineTo(n2 + n23, n3 + n25);
            graphics2D.setColor(Color.gray);
            graphics2D.draw(this.path);
            graphics2D.setColor(Color.black);
            graphics2D.drawGlyphVector(slice4.label, (float)(n2 + n23 + n29), (float)(n3 + n25 + n30));
            n24 = n25;
        }
        double n31 = n7 / 2.0;
        for (final Slice slice5 : this.q3) {
            double n32 = n6 * slice5.midUY;
            if (n31 - n32 < n7) {
                n32 = n31 - n7;
            }
            final double n33 = n4 * slice5.midUX;
            final double n34 = n4 * slice5.midUY;
            final double n35 = n32 * slice5.midUX / slice5.midUY;
            final double n36 = -slice5.label.getVisualBounds().getWidth() - 10.0;
            final double n37 = slice5.label.getVisualBounds().getHeight() / 2.0;
            this.path.reset();
            this.path.moveTo(n2 + n33, n3 + n34);
            if (n35 > n23) {
                this.path.lineTo(n2 + n35, n3 + n32);
            }
            this.path.lineTo(n2 + n23, n3 + n32);
            graphics2D.setColor(Color.gray);
            graphics2D.draw(this.path);
            graphics2D.setColor(Color.black);
            graphics2D.drawGlyphVector(slice5.label, (float)(n2 + n23 + n36), (float)(n3 + n32 + n37));
            n31 = n32;
        }
    }
    
    private static final class Slice
    {
        public GlyphVector label;
        public double startAngle;
        public double angleExtent;
        public double midUX;
        public double midUY;
    }
}
