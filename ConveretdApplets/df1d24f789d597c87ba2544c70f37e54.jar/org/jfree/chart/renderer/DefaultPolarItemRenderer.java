// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Stroke;
import java.awt.Paint;
import org.jfree.chart.LegendItem;
import java.awt.geom.Ellipse2D;
import org.jfree.chart.axis.ValueAxis;
import java.util.Iterator;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.axis.NumberTick;
import java.util.List;
import org.jfree.util.BooleanUtils;
import java.awt.Point;
import java.awt.Shape;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Polygon;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.util.BooleanList;
import org.jfree.chart.plot.PolarPlot;

public class DefaultPolarItemRenderer extends AbstractRenderer implements PolarItemRenderer
{
    private PolarPlot plot;
    private BooleanList seriesFilled;
    
    public DefaultPolarItemRenderer() {
        this.seriesFilled = new BooleanList();
    }
    
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        final PolarPlot p = this.getPlot();
        if (p != null) {
            result = p.getDrawingSupplier();
        }
        return result;
    }
    
    public void setPlot(final PolarPlot plot) {
        this.plot = plot;
    }
    
    public PolarPlot getPlot() {
        return this.plot;
    }
    
    public void drawSeries(final Graphics2D g2, final Rectangle2D dataArea, final PlotRenderingInfo info, final PolarPlot plot, final XYDataset dataset, final int seriesIndex) {
        final Polygon poly = new Polygon();
        for (int numPoints = dataset.getItemCount(seriesIndex), i = 0; i < numPoints; ++i) {
            final double theta = dataset.getXValue(seriesIndex, i).doubleValue();
            final double radius = dataset.getYValue(seriesIndex, i).doubleValue();
            final Point p = plot.translateValueThetaRadiusToJava2D(theta, radius, dataArea);
            poly.addPoint(p.x, p.y);
        }
        g2.setPaint(this.getSeriesPaint(seriesIndex));
        g2.setStroke(this.getSeriesStroke(seriesIndex));
        if (this.isSeriesFilled(seriesIndex)) {
            final Composite savedComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, 0.5f));
            g2.fill(poly);
            g2.setComposite(savedComposite);
        }
        else {
            g2.draw(poly);
        }
    }
    
    public boolean isSeriesFilled(final int series) {
        boolean result = false;
        final Boolean b = this.seriesFilled.getBoolean(series);
        if (b != null) {
            result = b;
        }
        return result;
    }
    
    public void setSeriesFilled(final int series, final boolean filled) {
        this.seriesFilled.setBoolean(series, BooleanUtils.valueOf(filled));
    }
    
    public void drawAngularGridLines(final Graphics2D g2, final PolarPlot plot, final List ticks, final Rectangle2D dataArea) {
        g2.setFont(plot.getAngleLabelFont());
        g2.setStroke(plot.getAngleGridlineStroke());
        g2.setPaint(plot.getAngleGridlinePaint());
        final double maxRadius = plot.getMaxRadius();
        final Point center = plot.translateValueThetaRadiusToJava2D(0.0, 0.0, dataArea);
        for (final NumberTick tick : ticks) {
            final Point p = plot.translateValueThetaRadiusToJava2D(tick.getNumber().doubleValue(), maxRadius, dataArea);
            g2.setPaint(plot.getAngleGridlinePaint());
            g2.drawLine(center.x, center.y, p.x, p.y);
            if (plot.isAngleLabelsVisible()) {
                final int x = p.x;
                final int y = p.y;
                g2.setPaint(plot.getAngleLabelPaint());
                RefineryUtilities.drawAlignedString(tick.getText(), g2, x, y, TextAnchor.CENTER);
            }
        }
    }
    
    public void drawRadialGridLines(final Graphics2D g2, final PolarPlot plot, final ValueAxis radialAxis, final List ticks, final Rectangle2D dataArea) {
        g2.setFont(radialAxis.getTickLabelFont());
        g2.setPaint(plot.getRadiusGridlinePaint());
        g2.setStroke(plot.getRadiusGridlineStroke());
        final Point center = plot.translateValueThetaRadiusToJava2D(0.0, 0.0, dataArea);
        for (final NumberTick tick : ticks) {
            final Point p = plot.translateValueThetaRadiusToJava2D(90.0, tick.getNumber().doubleValue(), dataArea);
            final int r = p.x - center.x;
            final int upperLeftX = center.x - r;
            final int upperLeftY = center.y - r;
            final int d = 2 * r;
            final Ellipse2D ring = new Ellipse2D.Double(upperLeftX, upperLeftY, d, d);
            g2.setPaint(plot.getRadiusGridlinePaint());
            g2.draw(ring);
        }
    }
    
    public LegendItem getLegendItem(final int series) {
        LegendItem result = null;
        final PolarPlot polarPlot = this.getPlot();
        if (polarPlot != null) {
            final XYDataset dataset = polarPlot.getDataset();
            if (dataset != null) {
                final String description;
                final String label = description = dataset.getSeriesName(series);
                final Shape shape = this.getSeriesShape(series);
                final Paint paint = this.getSeriesPaint(series);
                final Paint outlinePaint = this.getSeriesOutlinePaint(series);
                final Stroke stroke = this.getSeriesStroke(series);
                result = new LegendItem(label, description, shape, true, paint, stroke, outlinePaint, stroke);
            }
        }
        return result;
    }
}
