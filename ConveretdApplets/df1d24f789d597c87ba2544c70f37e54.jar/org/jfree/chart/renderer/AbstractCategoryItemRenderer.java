// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.util.PublicCloneable;
import java.awt.geom.Point2D;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.DrawingSupplier;
import org.jfree.util.ObjectUtils;
import org.jfree.chart.LegendItem;
import org.jfree.ui.RectangleInsets;
import java.awt.Font;
import org.jfree.ui.RectangleAnchor;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.axis.ValueAxis;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Line2D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Dataset;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.CategoryDataset;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.util.ObjectList;
import org.jfree.chart.labels.CategoryLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import java.io.Serializable;

public abstract class AbstractCategoryItemRenderer extends AbstractRenderer implements CategoryItemRenderer, Cloneable, Serializable
{
    private CategoryPlot plot;
    private CategoryLabelGenerator labelGenerator;
    private ObjectList labelGeneratorList;
    private CategoryLabelGenerator baseLabelGenerator;
    private CategoryToolTipGenerator toolTipGenerator;
    private ObjectList toolTipGeneratorList;
    private CategoryToolTipGenerator baseToolTipGenerator;
    private CategoryURLGenerator itemURLGenerator;
    private ObjectList itemURLGeneratorList;
    private CategoryURLGenerator baseItemURLGenerator;
    private transient int rowCount;
    private transient int columnCount;
    
    protected AbstractCategoryItemRenderer() {
        this.labelGenerator = null;
        this.labelGeneratorList = new ObjectList();
        this.toolTipGenerator = null;
        this.toolTipGeneratorList = new ObjectList();
        this.itemURLGenerator = null;
        this.itemURLGeneratorList = new ObjectList();
    }
    
    public CategoryPlot getPlot() {
        return this.plot;
    }
    
    public void setPlot(final CategoryPlot plot) {
        if (plot == null) {
            throw new IllegalArgumentException("Null 'plot' argument.");
        }
        this.plot = plot;
    }
    
    public CategoryLabelGenerator getLabelGenerator(final int row, final int column) {
        return this.getSeriesLabelGenerator(row);
    }
    
    public CategoryLabelGenerator getSeriesLabelGenerator(final int series) {
        if (this.labelGenerator != null) {
            return this.labelGenerator;
        }
        CategoryLabelGenerator generator = (CategoryLabelGenerator)this.labelGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseLabelGenerator;
        }
        return generator;
    }
    
    public void setLabelGenerator(final CategoryLabelGenerator generator) {
        this.labelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public void setSeriesLabelGenerator(final int series, final CategoryLabelGenerator generator) {
        this.labelGeneratorList.set(series, generator);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public CategoryLabelGenerator getBaseLabelGenerator() {
        return this.baseLabelGenerator;
    }
    
    public void setBaseLabelGenerator(final CategoryLabelGenerator generator) {
        this.baseLabelGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public CategoryToolTipGenerator getToolTipGenerator(final int row, final int column) {
        CategoryToolTipGenerator result = null;
        if (this.toolTipGenerator != null) {
            result = this.toolTipGenerator;
        }
        else {
            result = this.getSeriesToolTipGenerator(row);
            if (result == null) {
                result = this.baseToolTipGenerator;
            }
        }
        return result;
    }
    
    public CategoryToolTipGenerator getToolTipGenerator() {
        return this.toolTipGenerator;
    }
    
    public void setToolTipGenerator(final CategoryToolTipGenerator generator) {
        this.toolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public CategoryToolTipGenerator getSeriesToolTipGenerator(final int series) {
        return (CategoryToolTipGenerator)this.toolTipGeneratorList.get(series);
    }
    
    public void setSeriesToolTipGenerator(final int series, final CategoryToolTipGenerator generator) {
        this.toolTipGeneratorList.set(series, generator);
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public CategoryToolTipGenerator getBaseToolTipGenerator() {
        return this.baseToolTipGenerator;
    }
    
    public void setBaseToolTipGenerator(final CategoryToolTipGenerator generator) {
        this.baseToolTipGenerator = generator;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public CategoryURLGenerator getItemURLGenerator(final int row, final int column) {
        return this.getSeriesItemURLGenerator(row);
    }
    
    public CategoryURLGenerator getSeriesItemURLGenerator(final int series) {
        if (this.itemURLGenerator != null) {
            return this.itemURLGenerator;
        }
        CategoryURLGenerator generator = (CategoryURLGenerator)this.itemURLGeneratorList.get(series);
        if (generator == null) {
            generator = this.baseItemURLGenerator;
        }
        return generator;
    }
    
    public void setItemURLGenerator(final CategoryURLGenerator generator) {
        this.itemURLGenerator = generator;
    }
    
    public void setSeriesItemURLGenerator(final int series, final CategoryURLGenerator generator) {
        this.itemURLGeneratorList.set(series, generator);
    }
    
    public CategoryURLGenerator getBaseItemURLGenerator() {
        return this.baseItemURLGenerator;
    }
    
    public void setBaseItemURLGenerator(final CategoryURLGenerator generator) {
        this.baseItemURLGenerator = generator;
    }
    
    public int getRowCount() {
        return this.rowCount;
    }
    
    public int getColumnCount() {
        return this.columnCount;
    }
    
    public CategoryItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final CategoryPlot plot, final int rendererIndex, final PlotRenderingInfo info) {
        this.setPlot(plot);
        final CategoryDataset data = plot.getDataset(rendererIndex);
        if (data != null) {
            this.rowCount = data.getRowCount();
            this.columnCount = data.getColumnCount();
        }
        else {
            this.rowCount = 0;
            this.columnCount = 0;
        }
        return new CategoryItemRendererState(info);
    }
    
    public Range getRangeExtent(final CategoryDataset dataset) {
        return DatasetUtilities.getRangeExtent(dataset);
    }
    
    public void drawBackground(final Graphics2D g2, final CategoryPlot plot, final Rectangle2D dataArea) {
        plot.drawBackground(g2, dataArea);
    }
    
    public void drawOutline(final Graphics2D g2, final CategoryPlot plot, final Rectangle2D dataArea) {
        plot.drawOutline(g2, dataArea);
    }
    
    public void drawDomainGridline(final Graphics2D g2, final CategoryPlot plot, final Rectangle2D dataArea, final double value) {
        Line2D line = null;
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(dataArea.getMinX(), value, dataArea.getMaxX(), value);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(value, dataArea.getMinY(), value, dataArea.getMaxY());
        }
        Paint paint = plot.getDomainGridlinePaint();
        if (paint == null) {
            paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
        }
        g2.setPaint(paint);
        Stroke stroke = plot.getDomainGridlineStroke();
        if (stroke == null) {
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        }
        g2.setStroke(stroke);
        g2.draw(line);
    }
    
    public void drawRangeGridline(final Graphics2D g2, final CategoryPlot plot, final ValueAxis axis, final Rectangle2D dataArea, final double value) {
        final Range range = axis.getRange();
        if (!range.contains(value)) {
            return;
        }
        final PlotOrientation orientation = plot.getOrientation();
        final double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
        Line2D line = null;
        if (orientation == PlotOrientation.HORIZONTAL) {
            line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
        }
        Paint paint = plot.getRangeGridlinePaint();
        if (paint == null) {
            paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
        }
        g2.setPaint(paint);
        Stroke stroke = plot.getRangeGridlineStroke();
        if (stroke == null) {
            stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
        }
        g2.setStroke(stroke);
        g2.draw(line);
    }
    
    public void drawRangeMarker(final Graphics2D g2, final CategoryPlot plot, final ValueAxis axis, final Marker marker, final Rectangle2D dataArea) {
        if (marker instanceof ValueMarker) {
            final ValueMarker vm = (ValueMarker)marker;
            final double value = vm.getValue();
            final Range range = axis.getRange();
            if (!range.contains(value)) {
                return;
            }
            final PlotOrientation orientation = plot.getOrientation();
            final double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
            Line2D line = null;
            if (orientation == PlotOrientation.HORIZONTAL) {
                line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
            }
            g2.setPaint(marker.getOutlinePaint());
            g2.setStroke(marker.getOutlineStroke());
            g2.draw(line);
            final String label = marker.getLabel();
            final RectangleAnchor anchor = marker.getLabelAnchor();
            if (label != null) {
                final Font labelFont = marker.getLabelFont();
                g2.setFont(labelFont);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates = this.calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), anchor);
                RefineryUtilities.drawAlignedString(label, g2, (float)coordinates[0], (float)coordinates[1], TextAnchor.CENTER);
            }
        }
        else if (marker instanceof IntervalMarker) {
            final IntervalMarker im = (IntervalMarker)marker;
            double start = im.getStartValue();
            double end = im.getEndValue();
            final Range range2 = axis.getRange();
            if (!range2.intersects(start, end)) {
                return;
            }
            start = range2.constrain(start);
            end = range2.constrain(end);
            final double v2 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
            final double v3 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
            final PlotOrientation orientation2 = plot.getOrientation();
            Rectangle2D rect = null;
            if (orientation2 == PlotOrientation.HORIZONTAL) {
                rect = new Rectangle2D.Double(v2, dataArea.getMinY(), v3 - v2, dataArea.getHeight());
            }
            else if (orientation2 == PlotOrientation.VERTICAL) {
                rect = new Rectangle2D.Double(dataArea.getMinX(), Math.min(v2, v3), dataArea.getWidth(), Math.abs(v3 - v2));
            }
            g2.setPaint(marker.getPaint());
            g2.fill(rect);
            final String label2 = marker.getLabel();
            final RectangleAnchor anchor2 = marker.getLabelAnchor();
            if (label2 != null) {
                final Font labelFont2 = marker.getLabelFont();
                g2.setFont(labelFont2);
                g2.setPaint(marker.getLabelPaint());
                final double[] coordinates2 = this.calculateRangeMarkerTextAnchorPoint(g2, orientation2, dataArea, rect, marker.getLabelOffset(), anchor2);
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
    
    public LegendItem getLegendItem(final int datasetIndex, final int series) {
        final CategoryPlot cp = this.getPlot();
        if (cp == null) {
            return null;
        }
        final CategoryDataset dataset = cp.getDataset(datasetIndex);
        final String description;
        final String label = description = dataset.getRowKey(series).toString();
        final Shape shape = this.getSeriesShape(series);
        final Paint paint = this.getSeriesPaint(series);
        final Paint outlinePaint = this.getSeriesOutlinePaint(series);
        final Stroke stroke = this.getSeriesStroke(series);
        return new LegendItem(label, description, shape, true, paint, stroke, outlinePaint, stroke);
    }
    
    public boolean equals(final Object obj) {
        boolean result = super.equals(obj);
        if (obj instanceof AbstractCategoryItemRenderer) {
            final AbstractCategoryItemRenderer r = (AbstractCategoryItemRenderer)obj;
            final boolean b0 = ObjectUtils.equal(this.labelGenerator, r.labelGenerator);
            final boolean b2 = ObjectUtils.equal(this.labelGeneratorList, r.labelGeneratorList);
            final boolean b3 = ObjectUtils.equal(this.baseLabelGenerator, r.baseLabelGenerator);
            final boolean b4 = ObjectUtils.equal(this.toolTipGenerator, r.toolTipGenerator);
            final boolean b5 = ObjectUtils.equal(this.toolTipGeneratorList, r.toolTipGeneratorList);
            final boolean b6 = ObjectUtils.equal(this.baseToolTipGenerator, r.baseToolTipGenerator);
            final boolean b7 = ObjectUtils.equal(this.itemURLGenerator, r.itemURLGenerator);
            final boolean b8 = ObjectUtils.equal(this.itemURLGeneratorList, r.itemURLGeneratorList);
            final boolean b9 = ObjectUtils.equal(this.baseItemURLGenerator, r.baseItemURLGenerator);
            result = (b0 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9);
        }
        return result;
    }
    
    public int hashCode() {
        final int result = super.hashCode();
        return result;
    }
    
    public DrawingSupplier getDrawingSupplier() {
        DrawingSupplier result = null;
        final CategoryPlot cp = this.getPlot();
        if (cp != null) {
            result = cp.getDrawingSupplier();
        }
        return result;
    }
    
    protected void drawItemLabel(final Graphics2D g2, final PlotOrientation orientation, final CategoryDataset dataset, final int row, final int column, final double x, final double y, final boolean negative) {
        final CategoryLabelGenerator generator = this.getLabelGenerator(row, column);
        if (generator != null) {
            final Font labelFont = this.getItemLabelFont(row, column);
            final Paint paint = this.getItemLabelPaint(row, column);
            g2.setFont(labelFont);
            g2.setPaint(paint);
            final String label = generator.generateLabel(dataset, row, column);
            ItemLabelPosition position = null;
            if (!negative) {
                position = this.getPositiveItemLabelPosition(row, column);
            }
            else {
                position = this.getNegativeItemLabelPosition(row, column);
            }
            final Point2D anchorPoint = this.calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
            RefineryUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getRotationAnchor(), position.getAngle());
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        final AbstractCategoryItemRenderer clone = (AbstractCategoryItemRenderer)super.clone();
        if (this.labelGenerator != null) {
            if (!(this.labelGenerator instanceof PublicCloneable)) {
                throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
            }
            final PublicCloneable pc = (PublicCloneable)this.labelGenerator;
            clone.labelGenerator = (CategoryLabelGenerator)pc.clone();
        }
        if (this.labelGeneratorList != null) {
            clone.labelGeneratorList = (ObjectList)this.labelGeneratorList.clone();
        }
        if (this.baseLabelGenerator != null) {
            if (!(this.baseLabelGenerator instanceof PublicCloneable)) {
                throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
            }
            final PublicCloneable pc = (PublicCloneable)this.baseLabelGenerator;
            clone.baseLabelGenerator = (CategoryLabelGenerator)pc.clone();
        }
        if (this.toolTipGenerator != null) {
            if (!(this.toolTipGenerator instanceof PublicCloneable)) {
                throw new CloneNotSupportedException("Tool tip generator not cloneable.");
            }
            final PublicCloneable pc = (PublicCloneable)this.toolTipGenerator;
            clone.toolTipGenerator = (CategoryToolTipGenerator)pc.clone();
        }
        if (this.toolTipGeneratorList != null) {
            clone.toolTipGeneratorList = (ObjectList)this.toolTipGeneratorList.clone();
        }
        if (this.baseToolTipGenerator != null) {
            if (!(this.baseToolTipGenerator instanceof PublicCloneable)) {
                throw new CloneNotSupportedException("Base tool tip generator not cloneable.");
            }
            final PublicCloneable pc = (PublicCloneable)this.baseToolTipGenerator;
            clone.baseToolTipGenerator = (CategoryToolTipGenerator)pc.clone();
        }
        if (this.itemURLGenerator != null) {
            clone.itemURLGenerator = (CategoryURLGenerator)this.itemURLGenerator.clone();
        }
        if (this.itemURLGeneratorList != null) {
            clone.itemURLGeneratorList = (ObjectList)this.itemURLGeneratorList.clone();
        }
        if (this.baseItemURLGenerator != null) {
            clone.baseItemURLGenerator = (CategoryURLGenerator)this.baseItemURLGenerator.clone();
        }
        return clone;
    }
    
    protected CategoryAxis getDomainAxis(final CategoryPlot plot, final int index) {
        CategoryAxis result = plot.getDomainAxis(index);
        if (result == null) {
            result = plot.getDomainAxis();
        }
        return result;
    }
    
    protected ValueAxis getRangeAxis(final CategoryPlot plot, final int index) {
        ValueAxis result = plot.getRangeAxis(index);
        if (result == null) {
            result = plot.getRangeAxis();
        }
        return result;
    }
}
