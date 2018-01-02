// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.filters;

import prefuse.util.ColorLib;
import prefuse.visual.VisualItem;
import com.syynx.lissi.Colors;
import prefuse.action.assignment.ColorAction;

public class TextColorAction extends ColorAction implements Colors
{
    public TextColorAction(final String group) {
        super(group, VisualItem.TEXTCOLOR, ColorLib.gray(0));
        this.add("_hover", ColorLib.color(TextColorAction.colorTextHover));
        this.add("_prefocus", ColorLib.rgb(255, 255, 255));
        this.add("_highlight", ColorLib.color(TextColorAction.colorTextAlpha));
        this.add("ingroup('_focus_')", ColorLib.rgb(255, 255, 255));
        this.add("ingroup('_search_')", ColorLib.rgb(255, 255, 255));
    }
}
