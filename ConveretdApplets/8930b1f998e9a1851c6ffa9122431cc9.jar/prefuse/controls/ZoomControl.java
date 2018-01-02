// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.visual.VisualItem;
import java.awt.Cursor;
import prefuse.Display;
import java.awt.event.InputEvent;
import prefuse.util.ui.UILib;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class ZoomControl extends AbstractZoomControl
{
    private int yLast;
    private Point2D down;
    private int button;
    
    public ZoomControl() {
        this.down = new Point2D.Float();
        this.button = 4;
    }
    
    public ZoomControl(final int button) {
        this.down = new Point2D.Float();
        this.button = 4;
        this.button = button;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.button)) {
            final Display display = (Display)mouseEvent.getComponent();
            if (display.isTranformInProgress()) {
                this.yLast = -1;
                System.err.println("can't move");
                return;
            }
            display.setCursor(Cursor.getPredefinedCursor(8));
            display.getAbsoluteCoordinate(mouseEvent.getPoint(), this.down);
            this.yLast = mouseEvent.getY();
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.button)) {
            final Display display = (Display)mouseEvent.getComponent();
            if (display.isTranformInProgress() || this.yLast == -1) {
                this.yLast = -1;
                return;
            }
            final int y = mouseEvent.getY();
            final int zoom = this.zoom(display, this.down, 1.0 + (y - this.yLast) / 100.0, true);
            int n = 8;
            if (zoom == 3) {
                n = 3;
            }
            display.setCursor(Cursor.getPredefinedCursor(n));
            this.yLast = y;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (UILib.isButtonPressed(mouseEvent, this.button)) {
            mouseEvent.getComponent().setCursor(Cursor.getDefaultCursor());
        }
    }
    
    public void itemPressed(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_zoomOverItem) {
            this.mousePressed(mouseEvent);
        }
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_zoomOverItem) {
            this.mouseDragged(mouseEvent);
        }
    }
    
    public void itemReleased(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_zoomOverItem) {
            this.mouseReleased(mouseEvent);
        }
    }
}
