// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.imagemap;

public class OverLIBToolTipTagFragmentGenerator implements ToolTipTagFragmentGenerator
{
    public String generateToolTipFragment(final String toolTipText) {
        return " onMouseOver=\"return overlib('" + toolTipText + "');\" onMouseOut=\"return nd();\"";
    }
}
