// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.distortion;

import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import prefuse.visual.VisualItem;
import java.awt.geom.Point2D;
import prefuse.action.layout.Layout;

public abstract class Distortion extends Layout
{
    private Point2D m_tmp;
    protected boolean m_distortSize;
    protected boolean m_distortX;
    protected boolean m_distortY;
    
    public Distortion() {
        this.m_tmp = new Point2D.Double();
        this.m_distortSize = true;
        this.m_distortX = true;
        this.m_distortY = true;
    }
    
    public Distortion(final String s) {
        super(s);
        this.m_tmp = new Point2D.Double();
        this.m_distortSize = true;
        this.m_distortX = true;
        this.m_distortY = true;
    }
    
    public void setSizeDistorted(final boolean distortSize) {
        this.m_distortSize = distortSize;
    }
    
    public boolean isSizeDistorted() {
        return this.m_distortSize;
    }
    
    public void run(final double n) {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final Point2D correct = this.correct(this.m_anchor, layoutBounds);
        final Iterator visibleItems = this.getVisualization().visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            if (visualItem.isFixed()) {
                continue;
            }
            visualItem.setX(visualItem.getEndX());
            visualItem.setY(visualItem.getEndY());
            visualItem.setSize(visualItem.getEndSize());
            if (correct == null) {
                continue;
            }
            final Rectangle2D bounds = visualItem.getBounds();
            double n2 = visualItem.getX();
            double n3 = visualItem.getY();
            if (this.m_distortX) {
                visualItem.setX(n2 = this.distortX(n2, correct, layoutBounds));
            }
            if (this.m_distortY) {
                visualItem.setY(n3 = this.distortY(n3, correct, layoutBounds));
            }
            if (!this.m_distortSize) {
                continue;
            }
            visualItem.setSize(this.distortSize(bounds, n2, n3, correct, layoutBounds) * visualItem.getSize());
        }
    }
    
    protected Point2D correct(final Point2D point2D, final Rectangle2D rectangle2D) {
        if (point2D == null) {
            return point2D;
        }
        final double x = point2D.getX();
        final double y = point2D.getY();
        final double minX = rectangle2D.getMinX();
        final double minY = rectangle2D.getMinY();
        final double maxX = rectangle2D.getMaxX();
        final double maxY = rectangle2D.getMaxY();
        this.m_tmp.setLocation((x < minX) ? minX : ((x > maxX) ? maxX : x), (y < minY) ? minY : ((y > maxY) ? maxY : y));
        return this.m_tmp;
    }
    
    protected abstract double distortX(final double p0, final Point2D p1, final Rectangle2D p2);
    
    protected abstract double distortY(final double p0, final Point2D p1, final Rectangle2D p2);
    
    protected abstract double distortSize(final Rectangle2D p0, final double p1, final double p2, final Point2D p3, final Rectangle2D p4);
}
