// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.controls;

import prefuse.visual.EdgeItem;
import prefuse.Display;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import prefuse.controls.ToolTipControl;

public class EdgeToolTipControl extends ToolTipControl
{
    public EdgeToolTipControl() {
        super("");
    }
    
    public void itemEntered(final VisualItem item, final MouseEvent e) {
        final Display d = (Display)e.getSource();
        if (item instanceof EdgeItem) {
            d.setToolTipText(String.valueOf(item.getString("weight")) + " co-publications");
        }
    }
}
