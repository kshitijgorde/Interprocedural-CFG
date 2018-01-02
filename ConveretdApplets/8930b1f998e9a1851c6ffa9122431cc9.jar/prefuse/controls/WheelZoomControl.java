// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.geom.Point2D;
import prefuse.Display;
import java.awt.event.MouseWheelEvent;
import prefuse.visual.VisualItem;
import java.awt.Point;

public class WheelZoomControl extends AbstractZoomControl
{
    private Point m_point;
    
    public WheelZoomControl() {
        this.m_point = new Point();
    }
    
    public void itemWheelMoved(final VisualItem visualItem, final MouseWheelEvent mouseWheelEvent) {
        if (this.m_zoomOverItem) {
            this.mouseWheelMoved(mouseWheelEvent);
        }
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        final Display display = (Display)mouseWheelEvent.getComponent();
        this.m_point.x = display.getWidth() / 2;
        this.m_point.y = display.getHeight() / 2;
        this.zoom(display, this.m_point, 1.0f + 0.1f * mouseWheelEvent.getWheelRotation(), false);
    }
}
