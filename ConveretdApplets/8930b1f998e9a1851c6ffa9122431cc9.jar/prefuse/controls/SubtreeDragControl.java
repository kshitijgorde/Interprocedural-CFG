// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.util.Iterator;
import javax.swing.SwingUtilities;
import java.awt.Cursor;
import prefuse.Display;
import prefuse.visual.NodeItem;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import java.awt.geom.Point2D;

public class SubtreeDragControl extends ControlAdapter
{
    private Point2D down;
    private Point2D tmp;
    private boolean wasFixed;
    
    public SubtreeDragControl() {
        this.down = new Point2D.Double();
        this.tmp = new Point2D.Double();
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!(visualItem instanceof NodeItem)) {
            return;
        }
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!(visualItem instanceof NodeItem)) {
            return;
        }
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getDefaultCursor());
    }
    
    public void itemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        if (!(visualItem instanceof NodeItem)) {
            return;
        }
        this.down = ((Display)mouseEvent.getComponent()).getAbsoluteCoordinate(mouseEvent.getPoint(), this.down);
        this.wasFixed = visualItem.isFixed();
        visualItem.setFixed(true);
    }
    
    public void itemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        if (!(visualItem instanceof NodeItem)) {
            return;
        }
        visualItem.setFixed(this.wasFixed);
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        if (!(visualItem instanceof NodeItem)) {
            return;
        }
        this.tmp = ((Display)mouseEvent.getComponent()).getAbsoluteCoordinate(mouseEvent.getPoint(), this.tmp);
        this.updateLocations((NodeItem)visualItem, this.tmp.getX() - this.down.getX(), this.tmp.getY() - this.down.getY());
        this.down.setLocation(this.tmp);
        visualItem.getVisualization().repaint();
    }
    
    private void updateLocations(final NodeItem nodeItem, final double n, final double n2) {
        final double x = nodeItem.getX();
        final double y = nodeItem.getY();
        nodeItem.setStartX(x);
        nodeItem.setStartY(y);
        final double n3 = x + n;
        final double n4 = y + n2;
        nodeItem.setX(n3);
        nodeItem.setY(n4);
        nodeItem.setEndX(n3);
        nodeItem.setEndY(n4);
        final Iterator children = nodeItem.children();
        while (children.hasNext()) {
            this.updateLocations(children.next(), n, n2);
        }
    }
}
