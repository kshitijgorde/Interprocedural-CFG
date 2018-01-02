// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.geom.Rectangle2D;
import prefuse.util.display.DisplayLib;
import prefuse.util.GraphicsLib;
import java.awt.event.InputEvent;
import prefuse.util.ui.UILib;
import prefuse.Display;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import prefuse.Visualization;

public class ZoomToFitControl extends ControlAdapter
{
    private long m_duration;
    private int m_margin;
    private int m_button;
    private boolean m_zoomOverItem;
    private String m_group;
    
    public ZoomToFitControl() {
        this.m_duration = 2000L;
        this.m_margin = 50;
        this.m_button = 4;
        this.m_zoomOverItem = true;
        this.m_group = Visualization.ALL_ITEMS;
    }
    
    public ZoomToFitControl(final String group) {
        this.m_duration = 2000L;
        this.m_margin = 50;
        this.m_button = 4;
        this.m_zoomOverItem = true;
        this.m_group = Visualization.ALL_ITEMS;
        this.m_group = group;
    }
    
    public ZoomToFitControl(final int button) {
        this.m_duration = 2000L;
        this.m_margin = 50;
        this.m_button = 4;
        this.m_zoomOverItem = true;
        this.m_group = Visualization.ALL_ITEMS;
        this.m_button = button;
    }
    
    public ZoomToFitControl(final String group, final int button) {
        this.m_duration = 2000L;
        this.m_margin = 50;
        this.m_button = 4;
        this.m_zoomOverItem = true;
        this.m_group = Visualization.ALL_ITEMS;
        this.m_group = group;
        this.m_button = button;
    }
    
    public ZoomToFitControl(final String group, final int margin, final long duration, final int button) {
        this.m_duration = 2000L;
        this.m_margin = 50;
        this.m_button = 4;
        this.m_zoomOverItem = true;
        this.m_group = Visualization.ALL_ITEMS;
        this.m_group = group;
        this.m_margin = margin;
        this.m_duration = duration;
        this.m_button = button;
    }
    
    public void itemClicked(final VisualItem visualItem, final MouseEvent mouseEvent) {
        if (this.m_zoomOverItem) {
            this.mouseClicked(mouseEvent);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Display display = (Display)mouseEvent.getComponent();
        if (!display.isTranformInProgress() && UILib.isButtonPressed(mouseEvent, this.m_button)) {
            final Rectangle2D bounds = display.getVisualization().getBounds(this.m_group);
            GraphicsLib.expand(bounds, this.m_margin + (int)(1.0 / display.getScale()));
            DisplayLib.fitViewToBounds(display, bounds, this.m_duration);
        }
    }
    
    public boolean isZoomOverItem() {
        return this.m_zoomOverItem;
    }
    
    public void setZoomOverItem(final boolean zoomOverItem) {
        this.m_zoomOverItem = zoomOverItem;
    }
    
    public int getMargin() {
        return this.m_margin;
    }
    
    public void setMargin(final int margin) {
        this.m_margin = margin;
    }
}
