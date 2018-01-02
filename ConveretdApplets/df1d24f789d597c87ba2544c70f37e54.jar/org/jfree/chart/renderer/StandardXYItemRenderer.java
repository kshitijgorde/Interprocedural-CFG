// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import java.awt.Point;
import java.awt.Image;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.image.ImageObserver;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.Paint;
import java.awt.Shape;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.LegendItem;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.util.BooleanList;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class StandardXYItemRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    public static final int SHAPES = 1;
    public static final int LINES = 2;
    public static final int SHAPES_AND_LINES = 3;
    public static final int IMAGES = 4;
    public static final int DISCONTINUOUS = 8;
    public static final int DISCONTINUOUS_LINES = 10;
    private boolean plotShapes;
    private boolean plotLines;
    private boolean plotImages;
    private boolean plotDiscontinuous;
    private double gapThreshold;
    private Boolean shapesFilled;
    private BooleanList seriesShapesFilled;
    private Boolean defaultShapesFilled;
    
    public StandardXYItemRenderer() {
        this(2, null);
    }
    
    public StandardXYItemRenderer(final int type) {
        this(type, null);
    }
    
    public StandardXYItemRenderer(final int type, final XYToolTipGenerator toolTipGenerator) {
        this(type, toolTipGenerator, null);
    }
    
    public StandardXYItemRenderer(final int type, final XYToolTipGenerator toolTipGenerator, final XYURLGenerator urlGenerator) {
        this.gapThreshold = 1.0;
        this.setToolTipGenerator(toolTipGenerator);
        this.setURLGenerator(urlGenerator);
        if ((type & 0x1) != 0x0) {
            this.plotShapes = true;
        }
        if ((type & 0x2) != 0x0) {
            this.plotLines = true;
        }
        if ((type & 0x4) != 0x0) {
            this.plotImages = true;
        }
        if ((type & 0x8) != 0x0) {
            this.plotDiscontinuous = true;
        }
        this.shapesFilled = null;
        this.seriesShapesFilled = new BooleanList();
        this.defaultShapesFilled = Boolean.TRUE;
    }
    
    public boolean getPlotShapes() {
        return this.plotShapes;
    }
    
    public void setPlotShapes(final boolean flag) {
        if (this.plotShapes != flag) {
            this.plotShapes = flag;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean getItemShapeFilled(final int series, final int item) {
        return this.getSeriesShapesFilled(series);
    }
    
    public boolean getSeriesShapesFilled(final int series) {
        if (this.shapesFilled != null) {
            return this.shapesFilled;
        }
        final Boolean flag = this.seriesShapesFilled.getBoolean(series);
        if (flag != null) {
            return flag;
        }
        return this.defaultShapesFilled;
    }
    
    public void setShapesFilled(final boolean filled) {
        if (filled) {
            this.setShapesFilled(Boolean.TRUE);
        }
        else {
            this.setShapesFilled(Boolean.FALSE);
        }
    }
    
    public void setShapesFilled(final Boolean filled) {
        this.shapesFilled = filled;
    }
    
    public void setSeriesShapesFilled(final int series, final Boolean flag) {
        this.seriesShapesFilled.setBoolean(series, flag);
    }
    
    public Boolean getDefaultShapesFilled() {
        return this.defaultShapesFilled;
    }
    
    public void setDefaultShapesFilled(final Boolean flag) {
        this.defaultShapesFilled = flag;
    }
    
    public boolean getPlotLines() {
        return this.plotLines;
    }
    
    public void setPlotLines(final boolean flag) {
        if (this.plotLines != flag) {
            this.plotLines = flag;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public double getGapThreshold() {
        return this.gapThreshold;
    }
    
    public void setGapThreshold(final double t) {
        this.gapThreshold = t;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public boolean getPlotImages() {
        return this.plotImages;
    }
    
    public void setPlotImages(final boolean flag) {
        if (this.plotImages != flag) {
            this.plotImages = flag;
            this.notifyListeners(new RendererChangeEvent(this));
        }
    }
    
    public boolean getPlotDiscontinuous() {
        return this.plotDiscontinuous;
    }
    
    public LegendItem getLegendItem(final int datasetIndex, final int series) {
        LegendItem result = null;
        final XYPlot plot = this.getPlot();
        if (plot != null) {
            final XYDataset dataset = plot.getDataset(datasetIndex);
            if (dataset != null) {
                final String description;
                final String label = description = dataset.getSeriesName(series);
                final Shape shape = this.getSeriesShape(series);
                final boolean shapeFilled = this.getSeriesShapesFilled(series);
                final Paint paint = this.getSeriesPaint(series);
                final Paint outlinePaint = this.getSeriesOutlinePaint(series);
                final Stroke stroke = this.getSeriesStroke(series);
                result = new LegendItem(label, description, shape, shapeFilled, paint, stroke, outlinePaint, stroke);
            }
        }
        return result;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        Shape entityArea = null;
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        final PlotOrientation orientation = plot.getOrientation();
        final Paint paint = this.getItemPaint(series, item);
        final Stroke seriesStroke = this.getItemStroke(series, item);
        g2.setPaint(paint);
        g2.setStroke(seriesStroke);
        final Number x1n = dataset.getXValue(series, item);
        final Number y1n = dataset.getYValue(series, item);
        if (y1n == null || x1n == null) {
            return;
        }
        final double x1 = x1n.doubleValue();
        final double y1 = y1n.doubleValue();
        final RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
        final RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
        final double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
        final double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
        if (this.getPlotLines() && item > 0) {
            final Number x0n = dataset.getXValue(series, item - 1);
            final Number y0n = dataset.getYValue(series, item - 1);
            if (y0n != null && x0n != null) {
                final double x2 = x0n.doubleValue();
                final double y2 = y0n.doubleValue();
                boolean drawLine = true;
                if (this.getPlotDiscontinuous()) {
                    final int numX = dataset.getItemCount(series);
                    final double minX = dataset.getXValue(series, 0).doubleValue();
                    final double maxX = dataset.getXValue(series, numX - 1).doubleValue();
                    drawLine = (x1 - x2 <= (maxX - minX) / numX * this.getGapThreshold());
                }
                if (drawLine) {
                    final double transX2 = domainAxis.valueToJava2D(x2, dataArea, xAxisLocation);
                    final double transY2 = rangeAxis.valueToJava2D(y2, dataArea, yAxisLocation);
                    if (Double.isNaN(transX2) || Double.isNaN(transY2) || Double.isNaN(transX1) || Double.isNaN(transY1)) {
                        return;
                    }
                    if (orientation == PlotOrientation.HORIZONTAL) {
                        state.workingLine.setLine(transY2, transX2, transY1, transX1);
                    }
                    else if (orientation == PlotOrientation.VERTICAL) {
                        state.workingLine.setLine(transX2, transY2, transX1, transY1);
                    }
                    if (state.workingLine.intersects(dataArea)) {
                        g2.draw(state.workingLine);
                    }
                }
            }
        }
        if (this.getPlotShapes()) {
            Shape shape = this.getItemShape(series, item);
            if (orientation == PlotOrientation.HORIZONTAL) {
                shape = this.createTransformedShape(shape, transY1, transX1);
            }
            else if (orientation == PlotOrientation.VERTICAL) {
                shape = this.createTransformedShape(shape, transX1, transY1);
            }
            if (shape.intersects(dataArea)) {
                if (this.getItemShapeFilled(series, item)) {
                    g2.fill(shape);
                }
                else {
                    g2.draw(shape);
                }
            }
            entityArea = shape;
        }
        if (this.getPlotImages()) {
            final Image image = this.getImage(plot, series, item, transX1, transY1);
            if (image != null) {
                final Point hotspot = this.getImageHotspot(plot, series, item, transX1, transY1, image);
                g2.drawImage(image, (int)(transX1 - hotspot.getX()), (int)(transY1 - hotspot.getY()), null);
                entityArea = new Rectangle2D.Double(transX1 - hotspot.getX(), transY1 - hotspot.getY(), image.getWidth(null), image.getHeight(null));
            }
        }
        if (this.isItemLabelVisible(series, item)) {
            this.drawItemLabel(g2, orientation, dataset, series, item, transX1, transY1, y1 < 0.0);
        }
        this.updateCrosshairValues(crosshairState, x1, y1, transX1, transY1, orientation);
        if (entities != null) {
            if (entityArea == null) {
                entityArea = new Rectangle2D.Double(transX1 - 2.0, transY1 - 2.0, 4.0, 4.0);
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
            final XYItemEntity entity = new XYItemEntity(entityArea, dataset, series, item, tip, url);
            entities.addEntity(entity);
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof StandardXYItemRenderer) {
            final StandardXYItemRenderer r = (StandardXYItemRenderer)obj;
            if (super.equals(obj)) {
                final boolean b0 = this.plotShapes == r.plotShapes;
                final boolean b2 = this.plotLines == r.plotLines;
                final boolean b3 = this.plotImages == r.plotImages;
                final boolean b4 = this.plotDiscontinuous == r.plotDiscontinuous;
                final boolean b5 = this.gapThreshold == r.gapThreshold;
                return b0 && b2 && b3 && b4 && b5;
            }
        }
        return false;
    }
    
    protected Image getImage(final Plot plot, final int series, final int item, final double x, final double y) {
        return null;
    }
    
    protected Point getImageHotspot(final Plot plot, final int series, final int item, final double x, final double y, final Image image) {
        final int height = image.getHeight(null);
        final int width = image.getWidth(null);
        return new Point(width / 2, height / 2);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
