// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.ui.RectangleEdge;
import java.awt.Paint;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.IntervalXYDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.XYPlot;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.DatasetUtilities;
import org.jfree.data.TableXYDataset;
import org.jfree.data.Range;
import org.jfree.data.XYDataset;

public class StackedXYBarRenderer extends XYBarRenderer
{
    static /* synthetic */ Class class$org$jfree$data$IntervalXYDataset;
    static /* synthetic */ Class class$org$jfree$data$TableXYDataset;
    
    public StackedXYBarRenderer() {
    }
    
    public StackedXYBarRenderer(final double margin) {
        super(margin);
    }
    
    public RangeType getRangeType() {
        return RangeType.STACKED;
    }
    
    public Range getRangeExtent(final XYDataset dataset) {
        return DatasetUtilities.getStackedRangeExtent((TableXYDataset)dataset);
    }
    
    public XYItemRendererState initialise(final Graphics2D g2, final Rectangle2D dataArea, final XYPlot plot, final XYDataset data, final PlotRenderingInfo info) {
        return new StackedXYBarRendererState(info);
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        if (!(dataset instanceof IntervalXYDataset) || !(dataset instanceof TableXYDataset)) {
            String message = "dataset (type " + dataset.getClass().getName() + ") has wrong type:";
            boolean and = false;
            if (!((StackedXYBarRenderer.class$org$jfree$data$IntervalXYDataset == null) ? (StackedXYBarRenderer.class$org$jfree$data$IntervalXYDataset = class$("org.jfree.data.IntervalXYDataset")) : StackedXYBarRenderer.class$org$jfree$data$IntervalXYDataset).isAssignableFrom(dataset.getClass())) {
                message += " it is no IntervalXYDataset";
                and = true;
            }
            if (!((StackedXYBarRenderer.class$org$jfree$data$TableXYDataset == null) ? (StackedXYBarRenderer.class$org$jfree$data$TableXYDataset = class$("org.jfree.data.TableXYDataset")) : StackedXYBarRenderer.class$org$jfree$data$TableXYDataset).isAssignableFrom(dataset.getClass())) {
                if (and) {
                    message += " and";
                }
                message += " it is no TableXYDataset";
            }
            throw new IllegalArgumentException(message);
        }
        final IntervalXYDataset intervalData = (IntervalXYDataset)dataset;
        final Paint seriesPaint = this.getItemPaint(series, item);
        final Paint seriesOutlinePaint = this.getItemOutlinePaint(series, item);
        final double ph = this.getPreviousHeight(dataset, series, item);
        final Number valueNumber = intervalData.getYValue(series, item);
        if (valueNumber == null) {
            return;
        }
        final double translatedStartY = rangeAxis.valueToJava2D(ph, dataArea, plot.getRangeAxisEdge());
        final double translatedEndY = rangeAxis.valueToJava2D(valueNumber.doubleValue() + ph, dataArea, plot.getRangeAxisEdge());
        final RectangleEdge location = plot.getDomainAxisEdge();
        final Number startXNumber = intervalData.getStartXValue(series, item);
        if (startXNumber == null) {
            return;
        }
        double translatedStartX = domainAxis.valueToJava2D(startXNumber.doubleValue(), dataArea, location);
        final Number endXNumber = intervalData.getEndXValue(series, item);
        if (endXNumber == null) {
            return;
        }
        final double translatedEndX = domainAxis.valueToJava2D(endXNumber.doubleValue(), dataArea, location);
        double translatedWidth = Math.max(1.0, Math.abs(translatedEndX - translatedStartX));
        final double translatedHeight = Math.abs(translatedEndY - translatedStartY);
        if (this.getMargin() > 0.0) {
            final double cut = translatedWidth * this.getMargin();
            translatedWidth -= cut;
            translatedStartX += cut / 2.0;
        }
        Rectangle2D bar = null;
        final PlotOrientation orientation = plot.getOrientation();
        if (orientation == PlotOrientation.HORIZONTAL) {
            bar = new Rectangle2D.Double(Math.min(translatedStartY, translatedEndY), translatedEndX, translatedHeight, translatedWidth);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            bar = new Rectangle2D.Double(translatedStartX, Math.min(translatedStartY, translatedEndY), translatedWidth, translatedHeight);
        }
        g2.setPaint(seriesPaint);
        g2.fill(bar);
        if (Math.abs(translatedEndX - translatedStartX) > 3.0) {
            g2.setStroke(this.getItemStroke(series, item));
            g2.setPaint(seriesOutlinePaint);
            g2.draw(bar);
        }
        if (info != null) {
            final EntityCollection entities = info.getOwner().getEntityCollection();
            if (entities != null) {
                String tip = null;
                final XYToolTipGenerator generator = this.getToolTipGenerator(series, item);
                if (generator != null) {
                    tip = generator.generateToolTip(dataset, series, item);
                }
                String url = null;
                if (this.getURLGenerator() != null) {
                    url = this.getURLGenerator().generateURL(dataset, series, item);
                }
                final XYItemEntity entity = new XYItemEntity(bar, dataset, series, item, tip, url);
                entities.addEntity(entity);
            }
        }
    }
    
    protected double getPreviousHeight(final XYDataset data, final int series, final int index) {
        double result = 0.0;
        for (int i = 0; i < series; ++i) {
            final Number tmp = data.getYValue(i, index);
            if (tmp != null) {
                result += tmp.doubleValue();
            }
        }
        return result;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static class StackedXYBarRendererState extends XYItemRendererState
    {
        public StackedXYBarRendererState(final PlotRenderingInfo info) {
            super(info);
        }
    }
}
