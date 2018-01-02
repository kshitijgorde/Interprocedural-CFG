// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.util.ObjectUtils;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.axis.AxisLocation;
import java.awt.image.ImageObserver;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.axis.ValueAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.Serializable;

public class XYImageAnnotation implements XYAnnotation, Cloneable, Serializable
{
    private double x;
    private double y;
    private Image image;
    
    public XYImageAnnotation(final double x, final double y, final Image image) {
        if (image == null) {
            throw new IllegalArgumentException("Null 'image' argument.");
        }
        this.x = x;
        this.y = y;
        this.image = image;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
        final AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(domainAxisLocation, orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(rangeAxisLocation, orientation);
        final float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
        final float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
        float xx = 0.0f;
        float yy = 0.0f;
        if (orientation == PlotOrientation.HORIZONTAL) {
            xx = j2DY;
            yy = j2DX;
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            xx = j2DX;
            yy = j2DY;
        }
        xx -= this.image.getWidth(null) / 2.0f;
        yy -= this.image.getHeight(null) / 2.0f;
        g2.drawImage(this.image, (int)xx, (int)yy, null);
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof XYImageAnnotation) {
            final XYImageAnnotation a = (XYImageAnnotation)object;
            final boolean b0 = this.x == a.x;
            final boolean b2 = this.y == a.y;
            final boolean b3 = ObjectUtils.equal(this.image, a.image);
            return b0 && b2 && b3;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
