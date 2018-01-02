// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.util.Log;
import java.awt.geom.Point2D;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.util.ObjectUtils;
import org.jfree.util.PublicCloneable;
import org.jfree.ui.RectangleInsets;
import java.awt.Font;
import org.jfree.ui.RectangleAnchor;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Shape;
import org.jfree.chart.LegendItem;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.XYDataset;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.util.LogContext;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.util.ObjectList;
import org.jfree.chart.labels.XYLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import java.io.Serializable;

public abstract class AbstractXYItemRenderer extends AbstractRenderer implements XYItemRenderer, Cloneable, Serializable
{
    private XYPlot plot;
    private XYLabelGenerator itemLabelGenerator;
    private ObjectList itemLabelGeneratorList;
    private XYLabelGenerator baseItemLabelGenerator;
    private XYToolTipGenerator toolTipGenerator;
    private ObjectList toolTipGeneratorList;
    private XYToolTipGenerator baseToolTipGenerator;
    private XYURLGenerator urlGenerator;
    private static final LogContext LOGGER;
    static /* synthetic */ Class class$org$jfree$chart$renderer$AbstractXYItemRenderer;
    
    protected AbstractXYItemRenderer() {
        this.itemLabelGenerator = null;
        this.itemLabelGeneratorList = new ObjectList();
        this.toolTipGenerator = null;
        this.toolTipGeneratorList = new ObjectList();
        this.urlGenerator = null;
    }
    
    public int getPassCount() {
        return 1;
    }
    
    public XYPlot getPlot() {
        return this.plot;
    }
    
    public void setPlot(final XYPlot plot) {
        this.plot = plot;
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        final XYItemRendererState state = new XYItemRendererState(info);
        return state;
    }
    
    public XYLabelGenerator getLabelGenerator(final int row, final int column) {
        return this.getSeriesLabelGenerator(row);
    }
    
    public XYLabelGenerator getSeriesLabelGenerator(final int series) {
        if (this.itemLabelGenerator != null) {
            return this.itemLabelGenerator;
        }
        XYLabelGenerator generator = (XYLabelGenerator)this.itemLabelGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseItemLabelGenerator;
        }
        return generator;
    }
    
    public void setLabelGenerator(final XYLabelGenerator generator) {
        this.itemLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setSeriesLabelGenerator(final int series, final XYLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYLabelGenerator getBaseLabelGenerator() {
        return this.baseItemLabelGenerator;
    }
    
    public void setBaseLabelGenerator(final XYLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYToolTipGenerator getToolTipGenerator(final int row, final int column) {
        return this.getSeriesToolTipGenerator(row);
    }
    
    public XYToolTipGenerator getSeriesToolTipGenerator(final int series) {
        if (this.toolTipGenerator != null) {
            return this.toolTipGenerator;
        }
        XYToolTipGenerator generator = (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseToolTipGenerator;
        }
        return generator;
    }
    
    public void setToolTipGenerator(final XYToolTipGenerator generator) {
        this.toolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setSeriesToolTipGenerator(final int series, final XYToolTipGenerator generator) {
        this.toolTipGeneratorList.set(series, generator);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYToolTipGenerator getBaseToolTipGenerator() {
        return this.baseToolTipGenerator;
    }
    
    public void setBaseToolTipGenerator(final XYToolTipGenerator generator) {
        this.baseToolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYURLGenerator getURLGenerator() {
        return this.urlGenerator;
    }
    
    public void setURLGenerator(final XYURLGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public RangeType getRangeType() {
        return RangeType.STANDARD;
    }
    
    public Range getRangeExtent(final XYDataset dataset) {
        return DatasetUtilities.getRangeExtent(dataset);
    }
    
    public LegendItem getLegendItem(final int datasetIndex, final int series) {
        LegendItem result = null;
        final XYPlot xyplot = this.getPlot();
        if (xyplot != null) {
            final XYDataset dataset = xyplot.getDataset(datasetIndex);
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
    
    public void fillDomainGridBand(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double start, final double end) {
        final double x1 = axis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
        final double x2 = axis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
        final Rectangle2D band = new Rectangle2D.Double(x1, dataArea.getMinY(), x2 - x1, dataArea.getMaxY() - dataArea.getMinY());
        final Paint paint = plot.getDomainTickBandPaint();
        if (paint != null) {
            g2.setPaint(paint);
            g2.fill(band);
        }
    }
    
    public void fillRangeGridBand(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double start, final double end) {
        final double y1 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
        final double y2 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
        final Rectangle2D band = new Rectangle2D.Double(dataArea.getMinX(), y2, dataArea.getWidth(), y1 - y2);
        final Paint paint = plot.getRangeTickBandPaint();
        if (paint != null) {
            g2.setPaint(paint);
            g2.fill(band);
        }
    }
    
    public void drawDomainGridLine(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double value) {
        final Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }
        final PlotOrientation orientation = plot.getOrientation();
        final double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
        Line2D line = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }
        final Paint paint = plot.getDomainGridlinePaint();
        final Stroke stroke = plot.getDomainGridlineStroke();
        g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
        g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
        g2.draw(line);
    }
    
    public void drawRangeGridLine(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double value) {
        final Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }
        final PlotOrientation orientation = plot.getOrientation();
        Line2D line = null;
        final double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        }
        final Paint paint = plot.getRangeGridlinePaint();
        final Stroke stroke = plot.getRangeGridlineStroke();
        g2.setPaint((paint != null) ? paint : Plot.DEFAULT_OUTLINE_PAINT);
        g2.setStroke((stroke != null) ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
        g2.draw(line);
    }
    
    public void drawDomainMarker(final Graphics2D g2, final XYPlot plot, final ValueAxis domainAxis, final Marker marker, final Rectangle2D dataArea) {
        if (marker instanceof ValueMarker) {
            final ValueMarker vm = (ValueMarker)marker;
            final double value = vm.getValue();
            final Range range = domainAxis.getRange();
            if (!range.contains(value)) {
                return;
            }
            final double v = domainAxis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
            final PlotOrientation orientation = plot.getOrientation();
            Line2D line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
            }
            g2.setPaint(marker.getPaint());
            g2.setStroke(marker.getStroke());
            g2.draw(line);
            final String label = marker.getLabel();
            final RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                final Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates = this.calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), anchor, false);
                RefineryUtilities.drawAlignedString(label, g2, (float)coordinates[0], (float)coordinates[1], marker.getLabelTextAnchor());
            }
        }
        else if (marker instanceof IntervalMarker) {
            final IntervalMarker im = (IntervalMarker)marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            final Range range2 = domainAxis.getRange();
            if (!range2.intersects(start, end)) {
                return;
            }
            start = range2.constrain(start);
            end = range2.constrain(end);
            final double v2 = domainAxis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
            final double v3 = domainAxis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
            final PlotOrientation orientation2 = plot.getOrientation();
            Rectangle2D rect = null;
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v2, v3), dataArea.getWidth(), Math.abs(v3 - v2));
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                rect = new Rectangle2D.Double(Math.min(v2, v3), dataArea.getMinY(), Math.abs(v3 - v2), dataArea.getHeight());
            }
            g2.setPaint(marker.getPaint());
            g2.fill(rect);
            final String label2 = marker.getLabel();
            final RectangleAnchor anchor2 = marker.getLabelAnchor();
            if (label2 != null) {
                final Font labelFont2 = marker.getLabelFont();
                g2.setFont(labelFont2);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates2 = this.calculateDomainMarkerTextAnchorPoint(g2, orientation2, dataArea, rect, marker.getLabelOffset(), anchor2, true);
                RefineryUtilities.drawAlignedString(label2, g2, (float)coordinates2[0], (float)coordinates2[1], marker.getLabelTextAnchor());
            }
        }
    }
    
    private double[] calculateDomainMarkerTextAnchorPoint(final Graphics2D g2, final PlotOrientation orientation, final Rectangle2D dataArea, final Rectangle2D markerArea, final RectangleInsets markerOffset, final RectangleAnchor anchor, final boolean inset) {
        double[] result = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            Rectangle2D anchorRect = null;
            if (inset) {
                anchorRect = markerOffset.createInsetRectangle(markerArea, false, true);
            }
            else {
                anchorRect = markerOffset.createOutsetRectangle(markerArea, false, true);
            }
            result = RectangleAnchor.coordinates(anchorRect, anchor);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            Rectangle2D anchorRect = null;
            if (inset) {
                anchorRect = markerOffset.createInsetRectangle(markerArea, false, true);
            }
            else {
                anchorRect = markerOffset.createOutsetRectangle(markerArea, false, true);
            }
            result = RectangleAnchor.coordinates(anchorRect, anchor);
        }
        return result;
    }
    
    public void drawRangeMarker(final Graphics2D g2, final XYPlot plot, final ValueAxis rangeAxis, final Marker marker, final Rectangle2D dataArea) {
        if (marker instanceof ValueMarker) {
            final ValueMarker vm = (ValueMarker)marker;
            final double value = vm.getValue();
            final Range range = rangeAxis.getRange();
            if (!range.contains(value)) {
                return;
            }
            final double v = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
            final PlotOrientation orientation = plot.getOrientation();
            Line2D line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            }
            g2.setPaint(marker.getPaint());
            g2.setStroke(marker.getStroke());
            g2.draw(line);
            final String label = marker.getLabel();
            final RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                final Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates = this.calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), anchor);
                RefineryUtilities.drawAlignedString(label, g2, (float)coordinates[0], (float)coordinates[1], marker.getLabelTextAnchor());
            }
        }
        else if (marker instanceof IntervalMarker) {
            final IntervalMarker im = (IntervalMarker)marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            final Range range2 = rangeAxis.getRange();
            if (!range2.intersects(start, end)) {
                return;
            }
            start = range2.constrain(start);
            end = range2.constrain(end);
            final double v2 = rangeAxis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
            final double v3 = rangeAxis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
            final PlotOrientation orientation2 = plot.getOrientation();
            Rectangle2D rect = null;
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                rect = new Rectangle2D.Double(Math.min(v2, v3), dataArea.getMinY(), Math.abs(v3 - v2), dataArea.getHeight());
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v2, v3), dataArea.getWidth(), Math.abs(v2 - v3));
            }
            g2.setPaint(marker.getPaint());
            g2.fill(rect);
            final String label2 = marker.getLabel();
            final RectangleAnchor anchor2 = marker.getLabelAnchor();
            if (label2 != null) {
                final Font labelFont2 = marker.getLabelFont();
                g2.setFont(labelFont2);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates2 = this.calculateDomainMarkerTextAnchorPoint(g2, orientation2, dataArea, rect, marker.getLabelOffset(), anchor2, true);
                RefineryUtilities.drawAlignedString(label2, g2, (float)coordinates2[0], (float)coordinates2[1], marker.getLabelTextAnchor());
            }
        }
    }
    
    private double[] calculateRangeMarkerTextAnchorPoint(final Graphics2D g2, final PlotOrientation orientation, final Rectangle2D dataArea, final Rectangle2D markerArea, final RectangleInsets markerOffset, final RectangleAnchor anchor) {
        double[] result = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            final Rectangle2D anchorRect = markerOffset.createOutsetRectangle(markerArea, true, false);
            result = RectangleAnchor.coordinates(anchorRect, anchor);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final Rectangle2D anchorRect = markerOffset.createOutsetRectangle(markerArea, false, true);
            result = RectangleAnchor.coordinates(anchorRect, anchor);
        }
        return result;
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final AbstractXYItemRenderer clone = (AbstractXYItemRenderer)super.clone();
        if (this.itemLabelGenerator != null && this.itemLabelGenerator instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
            clone.itemLabelGenerator = (XYLabelGenerator)pc.clone();
        }
        return clone;
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractXYItemRenderer)) {
            return false;
        }
        final AbstractXYItemRenderer renderer = (AbstractXYItemRenderer)obj;
        return super.equals(obj) && ObjectUtils.equal(this.itemLabelGenerator, renderer.itemLabelGenerator) && ObjectUtils.equal(this.urlGenerator, renderer.urlGenerator);
    }
    
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        final XYPlot p = this.getPlot();
        if (p != null) {
            result = p.getDrawingSupplier();
        }
        return result;
    }
    
    protected void updateCrosshairValues(final CrosshairState crosshairState, final double x, final double y, final double transX, final double transY, final PlotOrientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        if (crosshairState != null) {
            if (this.plot.isDomainCrosshairLockedOnData()) {
                if (this.plot.isRangeCrosshairLockedOnData()) {
                    crosshairState.updateCrosshairPoint(x, y, transX, transY, orientation);
                }
                else {
                    crosshairState.updateCrosshairX(x);
                }
            }
            else if (this.plot.isRangeCrosshairLockedOnData()) {
                crosshairState.updateCrosshairY(y);
            }
        }
    }
    
    protected void drawItemLabel(final Graphics2D g2, final PlotOrientation orientation, final XYDataset dataset, final int series, final int item, final double x, final double y, final boolean negative) {
        final XYLabelGenerator generator = this.getLabelGenerator(series, item);
        if (generator != null) {
            final Font labelFont = this.getItemLabelFont(series, item);
            final Paint paint = this.getItemLabelPaint(series, item);
            g2.setFont(labelFont);
            g2.setPaint(paint);
            final String label = generator.generateLabel(dataset, series, item);
            ItemLabelPosition position = null;
            if (!negative) {
                position = this.getPositiveItemLabelPosition(series, item);
            }
            else {
                position = this.getNegativeItemLabelPosition(series, item);
            }
            final Point2D anchorPoint = this.calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
            RefineryUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getRotationAnchor(), position.getAngle());
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        LOGGER = Log.createContext((AbstractXYItemRenderer.class$org$jfree$chart$renderer$AbstractXYItemRenderer == null) ? (AbstractXYItemRenderer.class$org$jfree$chart$renderer$AbstractXYItemRenderer = class$("org.jfree.chart.renderer.AbstractXYItemRenderer")) : AbstractXYItemRenderer.class$org$jfree$chart$renderer$AbstractXYItemRenderer);
    }
}
