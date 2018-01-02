// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.xy;

import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.geom.Ellipse2D;
import org.jfree.chart.entity.EntityCollection;
import java.util.Iterator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.plot.DrawingSupplier;
import java.util.Collection;
import org.jfree.util.ObjectUtilities;
import org.jfree.util.PublicCloneable;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.GradientPaintTransformer;
import java.awt.geom.Point2D;
import java.awt.Font;
import org.jfree.ui.RectangleAnchor;
import java.awt.GradientPaint;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.text.TextUtilities;
import org.jfree.ui.LengthAdjustmentType;
import java.awt.Composite;
import java.awt.AlphaComposite;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.Plot;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Shape;
import org.jfree.data.general.Dataset;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.ui.Layer;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.data.xy.XYDataset;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import java.util.ArrayList;
import org.jfree.chart.labels.XYSeriesLabelGenerator;
import java.util.List;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.util.ObjectList;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import java.io.Serializable;
import org.jfree.chart.renderer.AbstractRenderer;

public abstract class AbstractXYItemRenderer extends AbstractRenderer implements XYItemRenderer, Cloneable, Serializable
{
    private static final long serialVersionUID = 8019124836026607990L;
    private XYPlot plot;
    private XYItemLabelGenerator itemLabelGenerator;
    private ObjectList itemLabelGeneratorList;
    private XYItemLabelGenerator baseItemLabelGenerator;
    private XYToolTipGenerator toolTipGenerator;
    private ObjectList toolTipGeneratorList;
    private XYToolTipGenerator baseToolTipGenerator;
    private XYURLGenerator urlGenerator;
    private List backgroundAnnotations;
    private List foregroundAnnotations;
    private int defaultEntityRadius;
    private XYSeriesLabelGenerator legendItemLabelGenerator;
    private XYSeriesLabelGenerator legendItemToolTipGenerator;
    private XYSeriesLabelGenerator legendItemURLGenerator;
    
    protected AbstractXYItemRenderer() {
        this.itemLabelGenerator = null;
        this.itemLabelGeneratorList = new ObjectList();
        this.toolTipGenerator = null;
        this.toolTipGeneratorList = new ObjectList();
        this.urlGenerator = null;
        this.backgroundAnnotations = new ArrayList();
        this.foregroundAnnotations = new ArrayList();
        this.defaultEntityRadius = 3;
        this.legendItemLabelGenerator = new StandardXYSeriesLabelGenerator("{0}");
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
    
    public XYItemLabelGenerator getItemLabelGenerator(final int series, final int item) {
        if (this.itemLabelGenerator != null) {
            return this.itemLabelGenerator;
        }
        XYItemLabelGenerator generator = (XYItemLabelGenerator)this.itemLabelGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseItemLabelGenerator;
        }
        return generator;
    }
    
    public XYItemLabelGenerator getSeriesItemLabelGenerator(final int series) {
        return (XYItemLabelGenerator)this.itemLabelGeneratorList.get(series);
    }
    
    public XYItemLabelGenerator getItemLabelGenerator() {
        return this.itemLabelGenerator;
    }
    
    public void setItemLabelGenerator(final XYItemLabelGenerator generator) {
        this.itemLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setSeriesItemLabelGenerator(final int series, final XYItemLabelGenerator generator) {
        this.itemLabelGeneratorList.set(series, generator);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYItemLabelGenerator getBaseItemLabelGenerator() {
        return this.baseItemLabelGenerator;
    }
    
    public void setBaseItemLabelGenerator(final XYItemLabelGenerator generator) {
        this.baseItemLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYToolTipGenerator getToolTipGenerator(final int series, final int item) {
        if (this.toolTipGenerator != null) {
            return this.toolTipGenerator;
        }
        XYToolTipGenerator generator = (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseToolTipGenerator;
        }
        return generator;
    }
    
    public XYToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }
    
    public void setToolTipGenerator(final XYToolTipGenerator generator) {
        this.toolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYToolTipGenerator getSeriesToolTipGenerator(final int series) {
        return (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
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
    
    public void addAnnotation(final XYAnnotation annotation) {
        this.addAnnotation(annotation, Layer.FOREGROUND);
    }
    
    public void addAnnotation(final XYAnnotation annotation, final Layer layer) {
        if (annotation == null) {
            throw new IllegalArgumentException("Null 'annotation' argument.");
        }
        if (layer.equals(Layer.FOREGROUND)) {
            this.foregroundAnnotations.add(annotation);
            this.notifyListeners(new RendererChangeEvent(this));
        }
        else {
            if (!layer.equals(Layer.BACKGROUND)) {
                throw new RuntimeException("Unknown layer.");
            }
            this.backgroundAnnotations.add(annotation);
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean removeAnnotation(final XYAnnotation annotation) {
        boolean removed = this.foregroundAnnotations.remove(annotation);
        removed &= this.backgroundAnnotations.remove(annotation);
        this.notifyListeners(new RendererChangeEvent(this));
        return removed;
    }
    
    public void removeAnnotations() {
        this.foregroundAnnotations.clear();
        this.backgroundAnnotations.clear();
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public int getDefaultEntityRadius() {
        return this.defaultEntityRadius;
    }
    
    public void setDefaultEntityRadius(final int radius) {
        this.defaultEntityRadius = radius;
    }
    
    public XYSeriesLabelGenerator getLegendItemLabelGenerator() {
        return this.legendItemLabelGenerator;
    }
    
    public void setLegendItemLabelGenerator(final XYSeriesLabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("Null 'generator' argument.");
        }
        this.legendItemLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYSeriesLabelGenerator getLegendItemToolTipGenerator() {
        return this.legendItemToolTipGenerator;
    }
    
    public void setLegendItemToolTipGenerator(final XYSeriesLabelGenerator generator) {
        this.legendItemToolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public XYSeriesLabelGenerator getLegendItemURLGenerator() {
        return this.legendItemURLGenerator;
    }
    
    public void setLegendItemURLGenerator(final XYSeriesLabelGenerator generator) {
        this.legendItemURLGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public Range findDomainBounds(final XYDataset dataset) {
        if (dataset != null) {
            return DatasetUtilities.findDomainBounds(dataset, false);
        }
        return null;
    }
    
    public Range findRangeBounds(final XYDataset dataset) {
        if (dataset != null) {
            return DatasetUtilities.findRangeBounds(dataset, false);
        }
        return null;
    }
    
    public LegendItemCollection getLegendItems() {
        if (this.plot == null) {
            return new LegendItemCollection();
        }
        final LegendItemCollection result = new LegendItemCollection();
        final int index = this.plot.getIndexOf(this);
        final XYDataset dataset = this.plot.getDataset(index);
        if (dataset != null) {
            for (int seriesCount = dataset.getSeriesCount(), i = 0; i < seriesCount; ++i) {
                if (this.isSeriesVisibleInLegend(i)) {
                    final LegendItem item = this.getLegendItem(index, i);
                    if (item != null) {
                        result.add(item);
                    }
                }
            }
        }
        return result;
    }
    
    public LegendItem getLegendItem(final int datasetIndex, final int series) {
        LegendItem result = null;
        final XYPlot xyplot = this.getPlot();
        if (xyplot != null) {
            final XYDataset dataset = xyplot.getDataset(datasetIndex);
            if (dataset != null) {
                final String description;
                final String label = description = this.legendItemLabelGenerator.generateLabel(dataset, series);
                String toolTipText = null;
                if (this.getLegendItemToolTipGenerator() != null) {
                    toolTipText = this.getLegendItemToolTipGenerator().generateLabel(dataset, series);
                }
                String urlText = null;
                if (this.getLegendItemURLGenerator() != null) {
                    urlText = this.getLegendItemURLGenerator().generateLabel(dataset, series);
                }
                final Shape shape = this.lookupSeriesShape(series);
                final Paint paint = this.lookupSeriesPaint(series);
                final Paint outlinePaint = this.lookupSeriesOutlinePaint(series);
                final Stroke outlineStroke = this.lookupSeriesOutlineStroke(series);
                result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
                result.setSeriesKey(dataset.getSeriesKey(series));
                result.setSeriesIndex(series);
                result.setDataset(dataset);
                result.setDatasetIndex(datasetIndex);
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
    
    public void drawDomainLine(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double value, final Paint paint, final Stroke stroke) {
        final Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }
        final PlotOrientation orientation = plot.getOrientation();
        Line2D line = null;
        final double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }
        g2.setPaint(paint);
        g2.setStroke(stroke);
        g2.draw(line);
    }
    
    public void drawRangeLine(final Graphics2D g2, final XYPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double value, final Paint paint, final Stroke stroke) {
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
        g2.setPaint(paint);
        g2.setStroke(stroke);
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
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
            g2.setPaint(marker.getPaint());
            g2.setStroke(marker.getStroke());
            g2.draw(line);
            final String label = marker.getLabel();
            final RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                final Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
                g2.setPaint(marker.getLabelPaint());
                final Point2D coordinates = this.calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
                TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
            }
            g2.setComposite(originalComposite);
        }
        else if (marker instanceof IntervalMarker) {
            final IntervalMarker im = (IntervalMarker)marker;
            final double start = im.getStartValue();
            final double end = im.getEndValue();
            final Range range2 = domainAxis.getRange();
            if (!range2.intersects(start, end)) {
                return;
            }
            final double start2d = domainAxis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
            final double end2d = domainAxis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
            double low = Math.min(start2d, end2d);
            double high = Math.max(start2d, end2d);
            final PlotOrientation orientation2 = plot.getOrientation();
            Rectangle2D rect = null;
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                low = Math.max(low, dataArea.getMinY());
                high = Math.min(high, dataArea.getMaxY());
                rect = new Rectangle2D.Double(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                low = Math.max(low, dataArea.getMinX());
                high = Math.min(high, dataArea.getMaxX());
                rect = new Rectangle2D.Double(low, dataArea.getMinY(), high - low, dataArea.getHeight());
            }
            final Composite originalComposite2 = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
            final Paint p = marker.getPaint();
            if (p instanceof GradientPaint) {
                GradientPaint gp = (GradientPaint)p;
                final GradientPaintTransformer t = im.getGradientPaintTransformer();
                if (t != null) {
                    gp = t.transform(gp, rect);
                }
                g2.setPaint(gp);
            }
            else {
                g2.setPaint(p);
            }
            g2.fill(rect);
            if (im.getOutlinePaint() != null && im.getOutlineStroke() != null) {
                if (orientation2 == PlotOrientation.VERTICAL) {
                    final Line2D line2 = new Line2D.Double();
                    final double y0 = dataArea.getMinY();
                    final double y2 = dataArea.getMaxY();
                    g2.setPaint(im.getOutlinePaint());
                    g2.setStroke(im.getOutlineStroke());
                    if (range2.contains(start)) {
                        line2.setLine(start2d, y0, start2d, y2);
                        g2.draw(line2);
                    }
                    if (range2.contains(end)) {
                        line2.setLine(end2d, y0, end2d, y2);
                        g2.draw(line2);
                    }
                }
                else {
                    final Line2D line2 = new Line2D.Double();
                    final double x0 = dataArea.getMinX();
                    final double x2 = dataArea.getMaxX();
                    g2.setPaint(im.getOutlinePaint());
                    g2.setStroke(im.getOutlineStroke());
                    if (range2.contains(start)) {
                        line2.setLine(x0, start2d, x2, start2d);
                        g2.draw(line2);
                    }
                    if (range2.contains(end)) {
                        line2.setLine(x0, end2d, x2, end2d);
                        g2.draw(line2);
                    }
                }
            }
            final String label2 = marker.getLabel();
            final RectangleAnchor anchor2 = marker.getLabelAnchor();
            if (label2 != null) {
                final Font labelFont2 = marker.getLabelFont();
                g2.setFont(labelFont2);
                g2.setPaint(marker.getLabelPaint());
                final Point2D coordinates2 = this.calculateDomainMarkerTextAnchorPoint(g2, orientation2, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor2);
                TextUtilities.drawAlignedString(label2, g2, (float)coordinates2.getX(), (float)coordinates2.getY(), marker.getLabelTextAnchor());
            }
            g2.setComposite(originalComposite2);
        }
    }
    
    protected Point2D calculateDomainMarkerTextAnchorPoint(final Graphics2D g2, final PlotOrientation orientation, final Rectangle2D dataArea, final Rectangle2D markerArea, final RectangleInsets markerOffset, final LengthAdjustmentType labelOffsetType, final RectangleAnchor anchor) {
        Rectangle2D anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);
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
            final Composite originalComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
            g2.setPaint(marker.getPaint());
            g2.setStroke(marker.getStroke());
            g2.draw(line);
            final String label = marker.getLabel();
            final RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                final Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
                g2.setPaint(marker.getLabelPaint());
                final Point2D coordinates = this.calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
                TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
            }
            g2.setComposite(originalComposite);
        }
        else if (marker instanceof IntervalMarker) {
            final IntervalMarker im = (IntervalMarker)marker;
            final double start = im.getStartValue();
            final double end = im.getEndValue();
            final Range range2 = rangeAxis.getRange();
            if (!range2.intersects(start, end)) {
                return;
            }
            final double start2d = rangeAxis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
            final double end2d = rangeAxis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
            double low = Math.min(start2d, end2d);
            double high = Math.max(start2d, end2d);
            final PlotOrientation orientation2 = plot.getOrientation();
            Rectangle2D rect = null;
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                low = Math.max(low, dataArea.getMinX());
                high = Math.min(high, dataArea.getMaxX());
                rect = new Rectangle2D.Double(low, dataArea.getMinY(), high - low, dataArea.getHeight());
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                low = Math.max(low, dataArea.getMinY());
                high = Math.min(high, dataArea.getMaxY());
                rect = new Rectangle2D.Double(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
            }
            final Composite originalComposite2 = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
            final Paint p = marker.getPaint();
            if (p instanceof GradientPaint) {
                GradientPaint gp = (GradientPaint)p;
                final GradientPaintTransformer t = im.getGradientPaintTransformer();
                if (t != null) {
                    gp = t.transform(gp, rect);
                }
                g2.setPaint(gp);
            }
            else {
                g2.setPaint(p);
            }
            g2.fill(rect);
            if (im.getOutlinePaint() != null && im.getOutlineStroke() != null) {
                if (orientation2 == PlotOrientation.VERTICAL) {
                    final Line2D line2 = new Line2D.Double();
                    final double x0 = dataArea.getMinX();
                    final double x2 = dataArea.getMaxX();
                    g2.setPaint(im.getOutlinePaint());
                    g2.setStroke(im.getOutlineStroke());
                    if (range2.contains(start)) {
                        line2.setLine(x0, start2d, x2, start2d);
                        g2.draw(line2);
                    }
                    if (range2.contains(end)) {
                        line2.setLine(x0, end2d, x2, end2d);
                        g2.draw(line2);
                    }
                }
                else {
                    final Line2D line2 = new Line2D.Double();
                    final double y0 = dataArea.getMinY();
                    final double y2 = dataArea.getMaxY();
                    g2.setPaint(im.getOutlinePaint());
                    g2.setStroke(im.getOutlineStroke());
                    if (range2.contains(start)) {
                        line2.setLine(start2d, y0, start2d, y2);
                        g2.draw(line2);
                    }
                    if (range2.contains(end)) {
                        line2.setLine(end2d, y0, end2d, y2);
                        g2.draw(line2);
                    }
                }
            }
            final String label2 = marker.getLabel();
            final RectangleAnchor anchor2 = marker.getLabelAnchor();
            if (label2 != null) {
                final Font labelFont2 = marker.getLabelFont();
                g2.setFont(labelFont2);
                g2.setPaint(marker.getLabelPaint());
                final Point2D coordinates2 = this.calculateRangeMarkerTextAnchorPoint(g2, orientation2, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor2);
                TextUtilities.drawAlignedString(label2, g2, (float)coordinates2.getX(), (float)coordinates2.getY(), marker.getLabelTextAnchor());
            }
            g2.setComposite(originalComposite2);
        }
    }
    
    private Point2D calculateRangeMarkerTextAnchorPoint(final Graphics2D g2, final PlotOrientation orientation, final Rectangle2D dataArea, final Rectangle2D markerArea, final RectangleInsets markerOffset, final LengthAdjustmentType labelOffsetForRange, final RectangleAnchor anchor) {
        Rectangle2D anchorRect = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetForRange, LengthAdjustmentType.CONTRACT);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetForRange);
        }
        return RectangleAnchor.coordinates(anchorRect, anchor);
    }
    
    protected Object clone() throws CloneNotSupportedException {
        final AbstractXYItemRenderer clone = (AbstractXYItemRenderer)super.clone();
        if (this.itemLabelGenerator != null && this.itemLabelGenerator instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
            clone.itemLabelGenerator = (XYItemLabelGenerator)pc.clone();
        }
        clone.itemLabelGeneratorList = (ObjectList)this.itemLabelGeneratorList.clone();
        if (this.baseItemLabelGenerator != null && this.baseItemLabelGenerator instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.baseItemLabelGenerator;
            clone.baseItemLabelGenerator = (XYItemLabelGenerator)pc.clone();
        }
        if (this.toolTipGenerator != null && this.toolTipGenerator instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.toolTipGenerator;
            clone.toolTipGenerator = (XYToolTipGenerator)pc.clone();
        }
        clone.toolTipGeneratorList = (ObjectList)this.toolTipGeneratorList.clone();
        if (this.baseToolTipGenerator != null && this.baseToolTipGenerator instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.baseToolTipGenerator;
            clone.baseToolTipGenerator = (XYToolTipGenerator)pc.clone();
        }
        if (clone.legendItemLabelGenerator instanceof PublicCloneable) {
            clone.legendItemLabelGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemLabelGenerator);
        }
        if (clone.legendItemToolTipGenerator instanceof PublicCloneable) {
            clone.legendItemToolTipGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemToolTipGenerator);
        }
        if (clone.legendItemURLGenerator instanceof PublicCloneable) {
            clone.legendItemURLGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemURLGenerator);
        }
        clone.foregroundAnnotations = (List)ObjectUtilities.deepClone(this.foregroundAnnotations);
        clone.backgroundAnnotations = (List)ObjectUtilities.deepClone(this.backgroundAnnotations);
        if (clone.legendItemLabelGenerator instanceof PublicCloneable) {
            clone.legendItemLabelGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemLabelGenerator);
        }
        if (clone.legendItemToolTipGenerator instanceof PublicCloneable) {
            clone.legendItemToolTipGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemToolTipGenerator);
        }
        if (clone.legendItemURLGenerator instanceof PublicCloneable) {
            clone.legendItemURLGenerator = (XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemURLGenerator);
        }
        return clone;
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractXYItemRenderer)) {
            return false;
        }
        final AbstractXYItemRenderer that = (AbstractXYItemRenderer)obj;
        return ObjectUtilities.equal(this.itemLabelGenerator, that.itemLabelGenerator) && this.itemLabelGeneratorList.equals(that.itemLabelGeneratorList) && ObjectUtilities.equal(this.baseItemLabelGenerator, that.baseItemLabelGenerator) && ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator) && this.toolTipGeneratorList.equals(that.toolTipGeneratorList) && ObjectUtilities.equal(this.baseToolTipGenerator, that.baseToolTipGenerator) && ObjectUtilities.equal(this.urlGenerator, that.urlGenerator) && this.foregroundAnnotations.equals(that.foregroundAnnotations) && this.backgroundAnnotations.equals(that.backgroundAnnotations) && this.defaultEntityRadius == that.defaultEntityRadius && ObjectUtilities.equal(this.legendItemLabelGenerator, that.legendItemLabelGenerator) && ObjectUtilities.equal(this.legendItemToolTipGenerator, that.legendItemToolTipGenerator) && ObjectUtilities.equal(this.legendItemURLGenerator, that.legendItemURLGenerator) && super.equals(obj);
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
        this.updateCrosshairValues(crosshairState, x, y, 0, 0, transX, transY, orientation);
    }
    
    protected void updateCrosshairValues(final CrosshairState crosshairState, final double x, final double y, final int domainAxisIndex, final int rangeAxisIndex, final double transX, final double transY, final PlotOrientation orientation) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        if (crosshairState != null) {
            if (this.plot.isDomainCrosshairLockedOnData()) {
                if (this.plot.isRangeCrosshairLockedOnData()) {
                    crosshairState.updateCrosshairPoint(x, y, domainAxisIndex, rangeAxisIndex, transX, transY, orientation);
                }
                else {
                    crosshairState.updateCrosshairX(x, domainAxisIndex);
                }
            }
            else if (this.plot.isRangeCrosshairLockedOnData()) {
                crosshairState.updateCrosshairY(y, rangeAxisIndex);
            }
        }
    }
    
    protected void drawItemLabel(final Graphics2D g2, final PlotOrientation orientation, final XYDataset dataset, final int series, final int item, final double x, final double y, final boolean negative) {
        final XYItemLabelGenerator generator = this.getItemLabelGenerator(series, item);
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
            TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
        }
    }
    
    public void drawAnnotations(final Graphics2D g2, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis, final Layer layer, final PlotRenderingInfo info) {
        Iterator iterator = null;
        if (layer.equals(Layer.FOREGROUND)) {
            iterator = this.foregroundAnnotations.iterator();
        }
        else {
            if (!layer.equals(Layer.BACKGROUND)) {
                throw new RuntimeException("Unknown layer.");
            }
            iterator = this.backgroundAnnotations.iterator();
        }
        while (iterator.hasNext()) {
            final XYAnnotation annotation = iterator.next();
            annotation.draw(g2, this.plot, dataArea, domainAxis, rangeAxis, 0, info);
        }
    }
    
    protected void addEntity(final EntityCollection entities, Shape area, final XYDataset dataset, final int series, final int item, final double entityX, final double entityY) {
        if (!this.getItemCreateEntity(series, item)) {
            return;
        }
        if (area == null) {
            area = new Ellipse2D.Double(entityX - this.defaultEntityRadius, entityY - this.defaultEntityRadius, this.defaultEntityRadius * 2, this.defaultEntityRadius * 2);
        }
        String tip = null;
        final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
        if (generator != null) {
            tip = generator.generateToolTip(dataset, series, item);
        }
        String url = null;
        if (this.getURLGenerator() != null) {
            url = this.getURLGenerator().generateURL(dataset, series, item);
        }
        final XYItemEntity entity = new XYItemEntity(area, dataset, series, item, tip, url);
        entities.add(entity);
    }
}
