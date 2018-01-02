// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.util.ObjectUtils;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.axis.ValueAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;
import org.jfree.ui.Drawable;
import java.io.Serializable;

public class XYDrawableAnnotation implements XYAnnotation, Cloneable, Serializable
{
    private double x;
    private double y;
    private double width;
    private double height;
    private Drawable drawable;
    
    public XYDrawableAnnotation(final double x, final double y, final double width, final double height, final Drawable drawable) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.drawable = drawable;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        final float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
        final float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
        final Rectangle2D area = new Rectangle2D.Double(j2DX - this.width / 2.0, j2DY - this.height / 2.0, this.width, this.height);
        this.drawable.draw(g2, area);
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (object instanceof XYDrawableAnnotation) {
            final XYDrawableAnnotation a = (XYDrawableAnnotation)object;
            final boolean b0 = this.x == a.x;
            final boolean b2 = this.y == a.y;
            final boolean b3 = this.width == a.width;
            final boolean b4 = this.height == a.height;
            final boolean b5 = ObjectUtils.equal(this.drawable, a.drawable);
            return b0 && b2 && b3 && b4 && b5;
        }
        return false;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
