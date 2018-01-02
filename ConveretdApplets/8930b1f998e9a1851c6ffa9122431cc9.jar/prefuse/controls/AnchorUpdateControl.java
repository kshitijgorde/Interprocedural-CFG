// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import prefuse.Display;
import prefuse.visual.VisualItem;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import prefuse.action.layout.Layout;

public class AnchorUpdateControl extends ControlAdapter
{
    private boolean m_anchorOverItem;
    private Layout[] m_layouts;
    private String m_action;
    private Point2D m_tmp;
    
    public AnchorUpdateControl(final Layout layout) {
        this(layout, null);
    }
    
    public AnchorUpdateControl(final Layout layout, final String s) {
        this(new Layout[] { layout }, s);
    }
    
    public AnchorUpdateControl(final Layout layout, final String s, final boolean b) {
        this(new Layout[] { layout }, s, b);
    }
    
    public AnchorUpdateControl(final Layout[] array, final String s) {
        this(array, s, true);
    }
    
    public AnchorUpdateControl(final Layout[] array, final String action, final boolean anchorOverItem) {
        this.m_tmp = new Point2D.Double();
        this.m_layouts = array.clone();
        this.m_action = action;
        this.m_anchorOverItem = anchorOverItem;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        for (int i = 0; i < this.m_layouts.length; ++i) {
            this.m_layouts[i].setLayoutAnchor(null);
        }
        this.runAction(mouseEvent);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.moveEvent(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.moveEvent(mouseEvent);
    }
    
    public void itemDragged(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_anchorOverItem) {
            this.moveEvent(mouseEvent);
        }
    }
    
    public void itemMoved(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_anchorOverItem) {
            this.moveEvent(mouseEvent);
        }
    }
    
    public void moveEvent(final MouseEvent mouseEvent) {
        ((Display)mouseEvent.getSource()).getAbsoluteCoordinate(mouseEvent.getPoint(), this.m_tmp);
        for (int i = 0; i < this.m_layouts.length; ++i) {
            this.m_layouts[i].setLayoutAnchor(this.m_tmp);
        }
        this.runAction(mouseEvent);
    }
    
    private void runAction(final MouseEvent mouseEvent) {
        if (this.m_action != null) {
            ((Display)mouseEvent.getSource()).getVisualization().run(this.m_action);
        }
    }
}
