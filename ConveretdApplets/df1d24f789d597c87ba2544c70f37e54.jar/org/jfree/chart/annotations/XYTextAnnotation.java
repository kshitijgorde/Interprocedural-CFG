// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.annotations;

import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.axis.ValueAxis;
import java.awt.geom.Rectangle2D;
import org.jfree.chart.plot.XYPlot;
import java.awt.Graphics2D;
import java.io.Serializable;

public class XYTextAnnotation extends TextAnnotation implements XYAnnotation, Cloneable, Serializable
{
    private double x;
    private double y;
    
    public XYTextAnnotation(final String text, final double x, final double y) {
        super(text);
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void draw(final Graphics2D g2, final XYPlot plot, final Rectangle2D dataArea, final ValueAxis domainAxis, final ValueAxis rangeAxis) {
        final PlotOrientation orientation = plot.getOrientation();
        final RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
        final RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
        float anchorX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
        float anchorY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
        if (orientation == PlotOrientation.HORIZONTAL) {
            final float tempAnchor = anchorX;
            anchorX = anchorY;
            anchorY = tempAnchor;
        }
        g2.setFont(this.getFont());
        g2.setPaint(this.getPaint());
        RefineryUtilities.drawRotatedString(this.getText(), g2, anchorX, anchorY, this.getTextAnchor(), this.getRotationAnchor(), this.getRotationAngle());
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
