// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.renderer;

import org.jfree.chart.labels.XYToolTipGenerator;
import java.awt.Stroke;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.entity.ChartEntity;
import org.jfree.chart.entity.XYItemEntity;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import org.jfree.data.SignalsDataset;
import org.jfree.chart.plot.CrosshairState;
import org.jfree.data.XYDataset;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class SignalRenderer extends AbstractXYItemRenderer implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
{
    private double markOffset;
    private double shapeWidth;
    private double shapeHeight;
    
    public SignalRenderer() {
        this.markOffset = 5.0;
        this.shapeWidth = 15.0;
        this.shapeHeight = 25.0;
    }
    
    public double getMarkOffset() {
        return this.markOffset;
    }
    
    public void setMarkOffset(final double offset) {
        this.markOffset = offset;
    }
    
    public double getShapeWidth() {
        return this.shapeWidth;
    }
    
    public void setShapeWidth(final double width) {
        this.shapeWidth = width;
    }
    
    public double getShapeHeight() {
        return this.shapeHeight;
    }
    
    public void setShapeHeight(final double height) {
        this.shapeHeight = height;
    }
    
    public void drawItem(final Graphics2D g2, final XYItemRendererState state, final Rectangle2D dataArea, final PlotRenderingInfo info, final XYPlot plot, final ValueAxis horizontalAxis, final ValueAxis verticalAxis, final XYDataset dataset, final int series, final int item, final CrosshairState crosshairState, final int pass) {
        EntityCollection entities = null;
        if (info != null) {
            entities = info.getOwner().getEntityCollection();
        }
        final SignalsDataset signalData = (SignalsDataset)dataset;
        final Number x = signalData.getXValue(series, item);
        final Number y = signalData.getYValue(series, item);
        final int type = signalData.getType(series, item);
        final double xx = horizontalAxis.valueToJava2D(x.doubleValue(), dataArea, plot.getDomainAxisEdge());
        double yy = verticalAxis.valueToJava2D(y.doubleValue(), dataArea, plot.getRangeAxisEdge());
        final Paint p = this.getItemPaint(series, item);
        final Stroke s = this.getItemStroke(series, item);
        g2.setPaint(p);
        g2.setStroke(s);
        int direction = 1;
        if (type == 1 || type == -2) {
            yy += this.markOffset;
            direction = -1;
        }
        else {
            yy -= this.markOffset;
        }
        final GeneralPath path = new GeneralPath();
        if (type == 1 || type == -1) {
            path.moveTo((float)xx, (float)yy);
            path.lineTo((float)(xx + this.shapeWidth / 2.0), (float)(yy - direction * this.shapeHeight / 3.0));
            path.lineTo((float)(xx + this.shapeWidth / 6.0), (float)(yy - direction * this.shapeHeight / 3.0));
            path.lineTo((float)(xx + this.shapeWidth / 6.0), (float)(yy - direction * this.shapeHeight));
            path.lineTo((float)(xx - this.shapeWidth / 6.0), (float)(yy - direction * this.shapeHeight));
            path.lineTo((float)(xx - this.shapeWidth / 6.0), (float)(yy - direction * this.shapeHeight / 3.0));
            path.lineTo((float)(xx - this.shapeWidth / 2.0), (float)(yy - direction * this.shapeHeight / 3.0));
            path.lineTo((float)xx, (float)yy);
        }
        else {
            path.moveTo((float)xx, (float)yy);
            path.lineTo((float)xx, (float)(yy - direction * this.shapeHeight));
            final Ellipse2D.Double ellipse = new Ellipse2D.Double(xx - this.shapeWidth / 2.0, yy + ((direction == 1) ? (-this.shapeHeight) : (this.shapeHeight - this.shapeWidth)), this.shapeWidth, this.shapeWidth);
            path.append(ellipse, false);
        }
        g2.fill(path);
        g2.setPaint(Color.black);
        g2.draw(path);
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
            final XYItemEntity entity = new XYItemEntity(path, dataset, series, item, tip, url);
            entities.addEntity(entity);
        }
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
