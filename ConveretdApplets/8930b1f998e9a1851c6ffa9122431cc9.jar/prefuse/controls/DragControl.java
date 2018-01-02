// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.data.Table;
import javax.swing.SwingUtilities;
import java.awt.Cursor;
import prefuse.Display;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import prefuse.visual.VisualItem;
import prefuse.data.event.TableListener;

public class DragControl extends ControlAdapter implements TableListener
{
    private VisualItem activeItem;
    protected String action;
    protected Point2D down;
    protected Point2D temp;
    protected boolean dragged;
    protected boolean wasFixed;
    protected boolean resetItem;
    private boolean fixOnMouseOver;
    protected boolean repaint;
    
    public DragControl() {
        this.down = new Point2D.Double();
        this.temp = new Point2D.Double();
        this.fixOnMouseOver = true;
        this.repaint = true;
    }
    
    public DragControl(final boolean repaint) {
        this.down = new Point2D.Double();
        this.temp = new Point2D.Double();
        this.fixOnMouseOver = true;
        this.repaint = true;
        this.repaint = repaint;
    }
    
    public DragControl(final boolean repaint, final boolean fixOnMouseOver) {
        this.down = new Point2D.Double();
        this.temp = new Point2D.Double();
        this.fixOnMouseOver = true;
        this.repaint = true;
        this.repaint = repaint;
        this.fixOnMouseOver = fixOnMouseOver;
    }
    
    public DragControl(final String action) {
        this.down = new Point2D.Double();
        this.temp = new Point2D.Double();
        this.fixOnMouseOver = true;
        this.repaint = true;
        this.repaint = false;
        this.action = action;
    }
    
    public DragControl(final String action, final boolean fixOnMouseOver) {
        this.down = new Point2D.Double();
        this.temp = new Point2D.Double();
        this.fixOnMouseOver = true;
        this.repaint = true;
        this.repaint = false;
        this.fixOnMouseOver = fixOnMouseOver;
        this.action = action;
    }
    
    public void setFixPositionOnMouseOver(final boolean fixOnMouseOver) {
        this.fixOnMouseOver = fixOnMouseOver;
    }
    
    public void itemEntered(final VisualItem activeItem, final MouseEvent mouseEvent) {
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getPredefinedCursor(12));
        this.activeItem = activeItem;
        if (this.fixOnMouseOver) {
            this.wasFixed = activeItem.isFixed();
            activeItem.setFixed(this.resetItem = true);
            activeItem.getTable().addTableListener(this);
        }
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.activeItem == visualItem) {
            this.activeItem = null;
            visualItem.getTable().removeTableListener(this);
            if (this.resetItem) {
                visualItem.setFixed(this.wasFixed);
            }
        }
        ((Display)mouseEvent.getSource()).setCursor(Cursor.getDefaultCursor());
    }
    
    public void itemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        if (!this.fixOnMouseOver) {
            this.wasFixed = visualItem.isFixed();
            visualItem.setFixed(this.resetItem = true);
            visualItem.getTable().addTableListener(this);
        }
        this.dragged = false;
        ((Display)mouseEvent.getComponent()).getAbsoluteCoordinate(mouseEvent.getPoint(), this.down);
    }
    
    public void itemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        if (this.dragged) {
            this.activeItem = null;
            visualItem.getTable().removeTableListener(this);
            if (this.resetItem) {
                visualItem.setFixed(this.wasFixed);
            }
            this.dragged = false;
        }
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
            return;
        }
        this.dragged = true;
        final Display display = (Display)mouseEvent.getComponent();
        display.getAbsoluteCoordinate(mouseEvent.getPoint(), this.temp);
        final double n = this.temp.getX() - this.down.getX();
        final double n2 = this.temp.getY() - this.down.getY();
        final double x = visualItem.getX();
        final double y = visualItem.getY();
        visualItem.setStartX(x);
        visualItem.setStartY(y);
        visualItem.setX(x + n);
        visualItem.setY(y + n2);
        visualItem.setEndX(x + n);
        visualItem.setEndY(y + n2);
        if (this.repaint) {
            visualItem.getVisualization().repaint();
        }
        this.down.setLocation(this.temp);
        if (this.action != null) {
            display.getVisualization().run(this.action);
        }
    }
    
    public void tableChanged(final Table table, final int n, final int n2, final int n3, final int n4) {
        if (this.activeItem == null || n4 != 0 || n3 != table.getColumnNumber(VisualItem.FIXED)) {
            return;
        }
        final int row = this.activeItem.getRow();
        if (row >= n && row <= n2) {
            this.resetItem = false;
        }
    }
}
