// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import org.xidget.swing.chart.PointShapes;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Iterator;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.xidget.chart.Point;
import org.xidget.chart.Scale;
import java.util.List;
import org.xidget.ifeature.IPointsFeature;
import javax.swing.JPanel;

public class LineChart extends JPanel implements IPointsFeature
{
    private List<Scale.Tick> xGrid;
    private List<Scale.Tick> yGrid;
    private List<Point> points;
    private boolean lines;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private Map<String, Axis> axes;
    
    public LineChart() {
        this.axes = new HashMap<String, Axis>();
        this.points = new ArrayList<Point>();
        this.setBackground(Color.white);
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
        if (this.points.size() == 0) {
            final double n2 = point.coords[0];
            this.maxX = n2;
            this.minX = n2;
            final double n3 = point.coords[1];
            this.maxY = n3;
            this.minY = n3;
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
        if (this.minX > point.coords[0]) {
            this.minX = point.coords[0];
        }
        if (this.minY > point.coords[1]) {
            this.minY = point.coords[1];
        }
        if (this.maxX < point.coords[0]) {
            this.maxX = point.coords[0];
        }
        if (this.maxY < point.coords[1]) {
            this.maxY = point.coords[1];
        }
        for (final Axis axis : this.axes.values()) {
            if (axis instanceof XAxis) {
                axis.setExtrema(this.minX, this.maxX);
            }
            else {
                axis.setExtrema(this.minY, this.maxY);
            }
        }
        this.points.add(n, point);
        this.repaint(point);
    }
    
    @Override
    public void update(final Point point, final String label) {
        this.repaint(point);
        point.label = label;
        this.repaint(point);
    }
    
    @Override
    public void update(final Point point, final int n, final double n2) {
        this.repaint(point);
        point.coords[n] = n2;
        if (this.minX > point.coords[0] || this.minY > point.coords[1] || this.maxX < point.coords[0] || this.maxY < point.coords[1]) {
            this.findExtrema();
        }
        this.repaint(point);
    }
    
    @Override
    public void remove(final int n) {
        if (n < 0 || n >= this.points.size()) {
            return;
        }
        final Point point = this.points.remove(n);
        if (point.prev != null) {
            point.prev.next = point.next;
        }
        if (point.next != null) {
            point.next.prev = point.prev;
        }
        if (this.minX > point.coords[0] || this.minY > point.coords[1] || this.maxX < point.coords[0] || this.maxY < point.coords[1]) {
            this.findExtrema();
        }
        for (final Axis axis : this.axes.values()) {
            if (axis instanceof XAxis) {
                axis.setExtrema(this.minX, this.maxX);
            }
            else {
                axis.setExtrema(this.minY, this.maxY);
            }
        }
        this.repaint(point);
    }
    
    public void findExtrema() {
        if (this.points == null || this.points.size() == 0) {
            this.minX = 0.0;
            this.minY = 0.0;
            this.maxX = 0.0;
            this.maxY = 0.0;
            return;
        }
        final double n = this.points.get(0).coords[0];
        this.maxX = n;
        this.minX = n;
        final double n2 = this.points.get(0).coords[1];
        this.maxY = n2;
        this.minY = n2;
        for (int i = 1; i < this.points.size(); ++i) {
            final Point point = this.points.get(i);
            if (point.coords[0] < this.minX) {
                this.minX = point.coords[0];
            }
            if (point.coords[1] < this.minY) {
                this.minY = point.coords[1];
            }
            if (point.coords[0] > this.maxX) {
                this.maxX = point.coords[0];
            }
            if (point.coords[1] > this.maxY) {
                this.maxY = point.coords[1];
            }
        }
    }
    
    private void repaint(final Point point) {
        final Axis axis = this.axes.get("x");
        final Axis axis2 = this.axes.get("y");
        if (axis == null || axis2 == null) {
            return;
        }
        final Scale scale = axis.getScale();
        final Scale scale2 = axis2.getScale();
        if (scale == null || scale2 == null) {
            return;
        }
        final int n = this.getWidth() - 1;
        final int n2 = this.getHeight() - 1;
        final int n3 = (int)(scale.plot(point.coords[0]) * n);
        final int n4 = (int)(scale2.plot(point.coords[1]) * n2);
        this.repaint(n3 - 5, n4 - 5, 10, 10);
        if (point.prev != null) {
            final int n5 = (int)(scale.plot(point.prev.coords[0]) * n);
            final int n6 = (int)(scale2.plot(point.prev.coords[1]) * n2);
            this.repaint(n5, n6, n3 - n5 + 2, n4 - n6 + 2);
        }
        if (point.next != null) {
            this.repaint(n3, n4, (int)(scale.plot(point.next.coords[0]) * n) - n3 + 2, (int)(scale2.plot(point.next.coords[1]) * n2) - n4 + 2);
        }
    }
    
    public void addAxis(final String s, final Axis axis) {
        this.axes.put(s, axis);
    }
    
    public void axisResized(final String s) {
        this.repaint();
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        final Axis axis = this.axes.get("x");
        final Axis axis2 = this.axes.get("y");
        if (axis == null || axis2 == null) {
            return;
        }
        final Scale scale = axis.getScale();
        final int height = this.getHeight();
        if (this.xGrid != null) {
            graphics2D.setColor(Color.lightGray);
            final Iterator<Scale.Tick> iterator = this.xGrid.iterator();
            while (iterator.hasNext()) {
                final int n = (int)Math.round(scale.plot(iterator.next().value));
                graphics2D.drawLine(n, 0, n, height);
            }
        }
        final Scale scale2 = axis2.getScale();
        final int width = this.getWidth();
        if (this.yGrid != null) {
            graphics2D.setColor(Color.lightGray);
            final Iterator<Scale.Tick> iterator2 = this.yGrid.iterator();
            while (iterator2.hasNext()) {
                final int n2 = (int)Math.round(scale2.plot(iterator2.next().value));
                graphics2D.drawLine(0, n2, width, n2);
            }
        }
        int n3 = 0;
        int n4 = 0;
        final int n5 = this.getWidth() - 1;
        final int n6 = this.getHeight() - 1;
        for (int i = 0; i < this.points.size(); ++i) {
            final Point point = this.points.get(i);
            final double n7 = scale.plot(point.coords[0]) * n5;
            final double n8 = n6 - scale2.plot(point.coords[1]) * n6;
            graphics2D.setColor(Color.black);
            if (this.lines && i > 0) {
                graphics2D.drawLine(n3, n4, (int)n7, (int)n8);
                n3 = (int)n7;
                n4 = (int)n8;
            }
            PointShapes.drawShape(graphics2D, point.shape, n7, n8);
        }
    }
}
