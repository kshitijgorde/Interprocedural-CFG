// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import prefuse.visual.expression.StartVisiblePredicate;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import java.awt.geom.Point2D;

public class CollapsedSubtreeLayout extends Layout
{
    private int m_orientation;
    private Point2D m_point;
    
    public CollapsedSubtreeLayout(final String s) {
        this(s, 4);
    }
    
    public CollapsedSubtreeLayout(final String s, final int orientation) {
        super(s);
        this.m_point = new Point2D.Double();
        this.m_orientation = orientation;
    }
    
    public int getOrientation() {
        return this.m_orientation;
    }
    
    public void setOrientation(final int orientation) {
        if (orientation < 0 || orientation >= 5) {
            throw new IllegalArgumentException("Unrecognized orientation value: " + orientation);
        }
        this.m_orientation = orientation;
    }
    
    public void run(final double n) {
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_group);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            if (visualItem instanceof NodeItem && !visualItem.isStartVisible()) {
                final NodeItem nodeItem = (NodeItem)visualItem;
                final Point2D point = this.getPoint(nodeItem, true);
                nodeItem.setStartX(point.getX());
                nodeItem.setStartY(point.getY());
            }
        }
        final Iterator items = this.m_vis.items(this.m_group, StartVisiblePredicate.TRUE);
        while (items.hasNext()) {
            final VisualItem visualItem2 = items.next();
            if (visualItem2 instanceof NodeItem && !visualItem2.isEndVisible()) {
                final NodeItem nodeItem2 = (NodeItem)visualItem2;
                final Point2D point2 = this.getPoint(nodeItem2, false);
                nodeItem2.setStartX(nodeItem2.getEndX());
                nodeItem2.setStartY(nodeItem2.getEndY());
                nodeItem2.setEndX(point2.getX());
                nodeItem2.setEndY(point2.getY());
            }
        }
    }
    
    private Point2D getPoint(final NodeItem nodeItem, final boolean b) {
        NodeItem nodeItem2 = (NodeItem)nodeItem.getParent();
        if (b) {
            while (nodeItem2 != null && !nodeItem2.isStartVisible()) {
                nodeItem2 = (NodeItem)nodeItem2.getParent();
            }
        }
        else {
            while (nodeItem2 != null && !nodeItem2.isEndVisible()) {
                nodeItem2 = (NodeItem)nodeItem2.getParent();
            }
        }
        if (nodeItem2 == null) {
            this.m_point.setLocation(nodeItem.getX(), nodeItem.getY());
            return this.m_point;
        }
        final double n = b ? nodeItem2.getStartX() : nodeItem2.getEndX();
        final double n2 = b ? nodeItem2.getStartY() : nodeItem2.getEndY();
        final Rectangle2D bounds = nodeItem2.getBounds();
        switch (this.m_orientation) {
            case 0: {
                this.m_point.setLocation(n + bounds.getWidth(), n2);
                break;
            }
            case 1: {
                this.m_point.setLocation(n - bounds.getWidth(), n2);
                break;
            }
            case 2: {
                this.m_point.setLocation(n, n2 + bounds.getHeight());
                break;
            }
            case 3: {
                this.m_point.setLocation(n, n2 - bounds.getHeight());
                break;
            }
            case 4: {
                this.m_point.setLocation(n, n2);
                break;
            }
        }
        return this.m_point;
    }
}
