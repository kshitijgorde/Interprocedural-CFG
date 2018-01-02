// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.renderer.xy;

import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.ui.RectangleEdge;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;
import org.jfree.chart.renderer.xy.XYItemRendererState;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYSmoothLineAndShapeRenderer extends XYLineAndShapeRenderer
{
    protected void drawPrimaryLine(final XYItemRendererState state, final Graphics2D g2, final XYPlot plot, final XYDataset dataset, final int pass, final int series, final int item, final ValueAxis domainAxis, final ValueAxis rangeAxis, final Rectangle2D dataArea) {
        if (item == 0) {
            return;
        }
        final double x1 = dataset.getXValue(series, item);
        final double y1 = dataset.getYValue(series, item);
        if (Double.isNaN(y1) || Double.isNaN(x1)) {
            return;
        }
        final double x2 = dataset.getXValue(series, item - 1);
        final double y2 = dataset.getYValue(series, item - 1);
        if (Double.isNaN(y2) || Double.isNaN(x2)) {
            return;
        }
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        double transX0 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
        double transY0 = rangeAxis.valueToJava2D(y2, dataArea, yAxisLocation);
        double transX2 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        double transY2 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
        if (Double.isNaN(transX0) || Double.isNaN(transY0) || Double.isNaN(transX2) || Double.isNaN(transY2)) {
            return;
        }
        Point2D.Double point0 = new Point2D.Double();
        final Point2D.Double point2 = new Point2D.Double();
        final Point2D.Double point3 = new Point2D.Double();
        Point2D.Double point4 = new Point2D.Double();
        if (item == 1) {
            point0 = null;
        }
        else {
            point0.x = domainAxis.valueToJava2D(dataset.getXValue(series, item - 2), dataArea, xAxisLocation);
            point0.y = rangeAxis.valueToJava2D(dataset.getYValue(series, item - 2), dataArea, yAxisLocation);
        }
        point2.x = transX0;
        point2.y = transY0;
        point3.x = transX2;
        point3.y = transY2;
        if (item + 1 == dataset.getItemCount(series)) {
            point4 = null;
        }
        else {
            point4.x = domainAxis.valueToJava2D(dataset.getXValue(series, item + 1), dataArea, xAxisLocation);
            point4.y = rangeAxis.valueToJava2D(dataset.getYValue(series, item + 1), dataArea, yAxisLocation);
        }
        final int steps = ((int)((point3.x - point2.x) / 0.2) < 30) ? ((int)((point3.x - point2.x) / 0.2)) : 30;
        final Point2D.Double[] points = getBezierCurve(point0, point2, point3, point4, 1.0, steps);
        for (int i = 1; i < points.length; ++i) {
            transX0 = points[i - 1].x;
            transY0 = points[i - 1].y;
            transX2 = points[i].x;
            transY2 = points[i].y;
            final PlotOrientation orientation = plot.getOrientation();
            if (orientation == PlotOrientation.HORIZONTAL) {
                state.workingLine.setLine(transY0, transX0, transY2, transX2);
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                state.workingLine.setLine(transX0, transY0, transX2, transY2);
            }
            if (state.workingLine.intersects(dataArea)) {
                this.drawFirstPassShape(g2, pass, series, item, state.workingLine);
            }
        }
    }
    
    protected void drawSecondaryPass(final Graphics2D g2, final XYPlot plot, final XYDataset dataset, final int pass, final int series, final int item, final ValueAxis domainAxis, final Rectangle2D dataArea, final ValueAxis rangeAxis, final CrosshairState crosshairState, final EntityCollection entities) {
    }
    
    public static void getControlPoints(Point2D.Double point0, final Point2D.Double point1, final Point2D.Double point2, Point2D.Double point3, final Point2D.Double control1, final Point2D.Double control2, final double smooth) {
        if (point0 == null) {
            point0 = point1;
        }
        if (point3 == null) {
            point3 = point2;
        }
        final Point2D.Double c1 = new Point2D.Double((point0.x + point1.x) / 2.0, (point0.y + point1.y) / 2.0);
        final Point2D.Double c2 = new Point2D.Double((point1.x + point2.x) / 2.0, (point1.y + point2.y) / 2.0);
        final Point2D.Double c3 = new Point2D.Double((point2.x + point3.x) / 2.0, (point2.y + point3.y) / 2.0);
        final double len1 = point1.distance(point0);
        final double len2 = point2.distance(point1);
        final double len3 = point3.distance(point2);
        final double k1 = len1 / (len1 + len2);
        final double k2 = len2 / (len2 + len3);
        final Point2D.Double m1 = new Point2D.Double(c1.x + (c2.x - c1.x) * k1, c1.y + (c2.y - c1.y) * k1);
        final Point2D.Double m2 = new Point2D.Double(c2.x + (c3.x - c2.x) * k2, c2.y + (c3.y - c2.y) * k2);
        control1.setLocation(new Point2D.Double(m1.x + (c2.x - m1.x) * smooth + point1.x - m1.x, m1.y + (c2.y - m1.y) * smooth + point1.y - m1.y));
        control2.setLocation(new Point2D.Double(m2.x + (c2.x - m2.x) * smooth + point2.x - m2.x, m2.y + (c2.y - m2.y) * smooth + point2.y - m2.y));
    }
    
    public static Point2D.Double[] getBezierCurve(final Point2D.Double point0, final Point2D.Double point1, final Point2D.Double point2, final Point2D.Double point3, final double smooth, final int steps) {
        final Point2D.Double control1 = new Point2D.Double();
        final Point2D.Double control2 = new Point2D.Double();
        getControlPoints(point0, point1, point2, point3, control1, control2, smooth);
        final Point2D.Double C = new Point2D.Double(3.0 * (control1.x - point1.x), 3.0 * (control1.y - point1.y));
        final Point2D.Double B = new Point2D.Double(3.0 * (control2.x - control1.x) - C.x, 3.0 * (control2.y - control1.y) - C.y);
        final Point2D.Double A = new Point2D.Double(point2.x - point1.x - C.x - B.x, point2.y - point1.y - C.y - B.y);
        final Point2D.Double[] res = new Point2D.Double[steps + 1];
        double step;
        final double stepSize = step = 1.0 / steps;
        res[0] = point1;
        for (int i = 1; i < steps; ++i) {
            res[i] = new Point2D.Double(A.x * Math.pow(step, 3.0) + B.x * Math.pow(step, 2.0) + C.x * step + point1.x, A.y * Math.pow(step, 3.0) + B.y * Math.pow(step, 2.0) + C.y * step + point1.y);
            step += stepSize;
        }
        res[steps] = point2;
        return res;
    }
}
