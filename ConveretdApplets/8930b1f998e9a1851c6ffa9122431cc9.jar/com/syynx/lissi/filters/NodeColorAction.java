// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.filters;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import com.syynx.lissi.Colors;
import prefuse.action.assignment.ColorAction;

public class NodeColorAction extends ColorAction implements Colors
{
    public NodeColorAction(final String group) {
        super(group, VisualItem.FILLCOLOR, ColorLib.rgba(255, 255, 255, 0));
        this.add("_hover", ColorLib.color(NodeColorAction.colorNodeHover));
        this.add("_prefocus", ColorLib.color(NodeColorAction.colorNodeHover));
        this.add("_highlight", ColorLib.color(NodeColorAction.colorNodeAlpha));
        this.add("ingroup('_search_')", ColorLib.color(NodeColorAction.colorNodeHover));
        this.add("ingroup('_focus_')", ColorLib.color(NodeColorAction.colorNodeFocus));
    }
}
