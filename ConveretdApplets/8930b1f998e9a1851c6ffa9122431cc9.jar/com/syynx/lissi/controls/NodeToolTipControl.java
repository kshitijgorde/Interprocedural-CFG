// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.controls;

import prefuse.visual.NodeItem;
import prefuse.Display;
import java.awt.event.MouseEvent;
import prefuse.visual.VisualItem;
import prefuse.controls.ToolTipControl;

public class NodeToolTipControl extends ToolTipControl
{
    public NodeToolTipControl() {
        super("");
    }
    
    public void itemEntered(final VisualItem item, final MouseEvent e) {
        final Display d = (Display)e.getSource();
        if (item instanceof NodeItem) {
            d.setToolTipText(String.valueOf(item.getString("doccount")) + " publications");
        }
    }
}
