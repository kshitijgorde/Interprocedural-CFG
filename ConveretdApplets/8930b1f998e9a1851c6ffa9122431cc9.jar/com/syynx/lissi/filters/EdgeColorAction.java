// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.filters;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import com.syynx.lissi.Colors;
import prefuse.action.assignment.ColorAction;

public class EdgeColorAction extends ColorAction implements Colors
{
    public EdgeColorAction(final String group) {
        super(group, VisualItem.STROKECOLOR, ColorLib.color(EdgeColorAction.colorEdge));
        this.add("_hover", ColorLib.color(EdgeColorAction.colorEdgeHover));
        this.add("_highlight", ColorLib.color(EdgeColorAction.colorEdgeAlpha));
        this.add("ingroup('_search_')", ColorLib.color(EdgeColorAction.colorEdgeHover));
        this.add("ingroup('_focus_')", ColorLib.color(EdgeColorAction.colorEdgeFocus));
    }
}
