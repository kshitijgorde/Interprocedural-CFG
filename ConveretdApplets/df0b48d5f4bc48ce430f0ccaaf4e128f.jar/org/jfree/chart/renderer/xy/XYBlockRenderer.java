// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer.xy;

import org.jfree.util.PublicCloneable;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Shape;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYZDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.Range;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.PaintScale;
import org.jfree.ui.RectangleAnchor;
import java.io.Serializable;

public class XYBlockRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, Serializable
{
    private double blockWidth;
    private double blockHeight;
    private RectangleAnchor blockAnchor;
    private double xOffset;
    private double yOffset;
    private PaintScale paintScale;
    
    public XYBlockRenderer() {
        this.blockWidth = 1.0;
        this.blockHeight = 1.0;
        this.blockAnchor = RectangleAnchor.CENTER;
        this.updateOffsets();
        this.paintScale = new LookupPaintScale();
    }
    
    public double getBlockWidth() {
        return this.blockWidth;
    }
    
    public void setBlockWidth(final double width) {
        if (width <= 0.0) {
            throw new IllegalArgumentException("The 'width' argument must be > 0.0");
        }
        this.blockWidth = width;
        this.updateOffsets();
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public double getBlockHeight() {
        return this.blockHeight;
    }
    
    public void setBlockHeight(final double height) {
        if (height <= 0.0) {
            throw new IllegalArgumentException("The 'height' argument must be > 0.0");
        }
        this.blockHeight = height;
        this.updateOffsets();
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public RectangleAnchor getBlockAnchor() {
        return this.blockAnchor;
    }
    
    public void setBlockAnchor(final RectangleAnchor anchor) {
        if (anchor == null) {
            throw new IllegalArgumentException("Null 'anchor' argument.");
        }
        if (this.blockAnchor.equals(anchor)) {
            return;
        }
        this.blockAnchor = anchor;
        this.updateOffsets();
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    public PaintScale getPaintScale() {
        return this.paintScale;
    }
    
    public void setPaintScale(final PaintScale scale) {
        if (scale == null) {
            throw new IllegalArgumentException("Null 'scale' argument.");
        }
        this.paintScale = scale;
        this.notifyListeners(new RendererChangeEvent(this));
    }
    
    private void updateOffsets() {
        if (this.blockAnchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
            this.xOffset = 0.0;
            this.yOffset = 0.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.BOTTOM)) {
            this.xOffset = -this.blockWidth / 2.0;
            this.yOffset = 0.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
            this.xOffset = -this.blockWidth;
            this.yOffset = 0.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.LEFT)) {
            this.xOffset = 0.0;
            this.yOffset = -this.blockHeight / 2.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.CENTER)) {
            this.xOffset = -this.blockWidth / 2.0;
            this.yOffset = -this.blockHeight / 2.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.RIGHT)) {
            this.xOffset = -this.blockWidth;
            this.yOffset = -this.blockHeight / 2.0;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.TOP_LEFT)) {
            this.xOffset = 0.0;
            this.yOffset = -this.blockHeight;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.TOP)) {
            this.xOffset = -this.blockWidth / 2.0;
            this.yOffset = -this.blockHeight;
        }
        else if (this.blockAnchor.equals(RectangleAnchor.TOP_RIGHT)) {
            this.xOffset = -this.blockWidth;
            this.yOffset = -this.blockHeight;
        }
    }
    
    public Range findDomainBounds(final XYDataset dataset) {
        if (dataset != null) {
            final Range r = DatasetUtilities.findDomainBounds(dataset, false);
            return new Range(r.getLowerBound() + this.xOffset, r.getUpperBound() + this.blockWidth + this.xOffset);
        }
        return null;
    }
    
    public Range findRangeBounds(final XYDataset dataset) {
        if (dataset != null) {
            final Range r = DatasetUtilities.findRangeBounds(dataset, false);
            return new Range(r.getLowerBound() + this.yOffset, r.getUpperBound() + this.blockHeight + this.yOffset);
        }
        return null;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis domainAxis, final ValueAxis rangeAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        final double x = dataset.getXValue(series, item);
        final double y = dataset.getYValue(series, item);
        double z = 0.0;
        if (dataset instanceof XYZDataset) {
            z = ((XYZDataset)dataset).getZValue(series, item);
        }
        final Paint p = this.paintScale.getPaint(z);
        final double xx0 = domainAxis.valueToJava2D(x + this.xOffset, dataArea, plot.getDomainAxisEdge());
        final double yy0 = rangeAxis.valueToJava2D(y + this.yOffset, dataArea, plot.getRangeAxisEdge());
        final double xx2 = domainAxis.valueToJava2D(x + this.blockWidth + this.xOffset, dataArea, plot.getDomainAxisEdge());
        final double yy2 = rangeAxis.valueToJava2D(y + this.blockHeight + this.yOffset, dataArea, plot.getRangeAxisEdge());
        final PlotOrientation orientation = plot.getOrientation();
        Rectangle2D block;
        if (orientation.equals(PlotOrientation.HORIZONTAL)) {
            block = new Rectangle2D.Double(Math.min(yy0, yy2), Math.min(xx0, xx2), Math.abs(yy2 - yy0), Math.abs(xx0 - xx2));
        }
        else {
            block = new Rectangle2D.Double(Math.min(xx0, xx2), Math.min(yy0, yy2), Math.abs(xx2 - xx0), Math.abs(yy2 - yy0));
        }
        g2.setPaint(p);
        g2.fill(block);
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(block);
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XYBlockRenderer)) {
            return false;
        }
        final XYBlockRenderer that = (XYBlockRenderer)obj;
        return this.blockHeight == that.blockHeight && this.blockWidth == that.blockWidth && this.blockAnchor.equals(that.blockAnchor) && this.paintScale.equals(that.paintScale) && super.equals(obj);
    }
    
    public Object clone() throws CloneNotSupportedException {
        final XYBlockRenderer clone = (XYBlockRenderer)super.clone();
        if (this.paintScale instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.paintScale;
            clone.paintScale = (PaintScale)pc.clone();
        }
        return clone;
    }
}
