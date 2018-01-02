// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

import java.awt.geom.Point2D;
import java.awt.Point;
import javax.swing.JComponent;

public interface Projector
{
    Point map(final ModelPoint p0, final JComponent p1);
    
    ModelPoint inversMap(final Point p0, final JComponent p1);
    
    Point2D getScale(final ModelPoint p0, final JComponent p1);
    
    Point[] getLineSegments(final ModelPoint p0, final ModelPoint p1, final JComponent p2);
}
