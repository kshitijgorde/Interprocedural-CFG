// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.imagemap;

public class StandardToolTipTagFragmentGenerator implements ToolTipTagFragmentGenerator
{
    public String generateToolTipFragment(final String toolTipText) {
        return " title=\"" + toolTipText + "\"";
    }
}
