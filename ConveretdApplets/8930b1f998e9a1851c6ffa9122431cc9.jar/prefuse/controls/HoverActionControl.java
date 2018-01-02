// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.controls;

import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;

public class HoverActionControl extends ControlAdapter
{
    private String m_action;
    
    public HoverActionControl(final String action) {
        this.m_action = action;
    }
    
    public void itemEntered(final VisualItem visualItem, final MouseEvent mouseEvent) {
        visualItem.getVisualization().run(this.m_action);
    }
    
    public void itemExited(final VisualItem visualItem, final MouseEvent mouseEvent) {
        visualItem.getVisualization().run(this.m_action);
    }
}
