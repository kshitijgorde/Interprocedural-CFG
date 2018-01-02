// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;

public class CircleLayout extends Layout
{
    private double m_radius;
    
    public CircleLayout(final String s) {
        super(s);
    }
    
    public CircleLayout(final String s, final double radius) {
        super(s);
        this.m_radius = radius;
    }
    
    public double getRadius() {
        return this.m_radius;
    }
    
    public void setRadius(final double radius) {
        this.m_radius = radius;
    }
    
    public void run(final double n) {
        final TupleSet group = this.m_vis.getGroup(this.m_group);
        final int tupleCount = group.getTupleCount();
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double height = layoutBounds.getHeight();
        final double width = layoutBounds.getWidth();
        final double centerX = layoutBounds.getCenterX();
        final double centerY = layoutBounds.getCenterY();
        double radius = this.m_radius;
        if (radius <= 0.0) {
            radius = 0.45 * ((height < width) ? height : width);
        }
        final Iterator tuples = group.tuples();
        int n2 = 0;
        while (tuples.hasNext()) {
            final VisualItem visualItem = tuples.next();
            final double n3 = 6.283185307179586 * n2 / tupleCount;
            final double n4 = Math.cos(n3) * radius + centerX;
            final double n5 = Math.sin(n3) * radius + centerY;
            this.setX(visualItem, null, n4);
            this.setY(visualItem, null, n5);
            ++n2;
        }
    }
}
