// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCUtilConverter;
import jclass.util.JCConverter;

class ScrollbarConverter
{
    static final int[] orientation_values;
    static final String[] orientation_strings;
    
    static void getParams(final JCScrollbar jcScrollbar) {
        final JCConverter conv = JCContainer.conv;
        jcScrollbar.setMinimum(conv.toInt(jcScrollbar.getParam("Minimum"), jcScrollbar.min));
        jcScrollbar.setMaximum(conv.toInt(jcScrollbar.getParam("Maximum"), jcScrollbar.max));
        jcScrollbar.setVisibleAmount(conv.toInt(jcScrollbar.getParam("VisibleAmount"), jcScrollbar.getVisibleAmount()));
        jcScrollbar.setBlockIncrement(conv.toInt(jcScrollbar.getParam("BlockIncrement"), jcScrollbar.getBlockIncrement()));
        jcScrollbar.setUnitIncrement(conv.toInt(jcScrollbar.getParam("UnitIncrement"), jcScrollbar.getUnitIncrement()));
        jcScrollbar.filter_time = conv.toInt(jcScrollbar.getParam("FilterTime"), (int)jcScrollbar.filter_time);
    }
    
    static void checkOrientation(final int n) {
        JCUtilConverter.checkEnum(n, "orientation", ScrollbarConverter.orientation_values);
    }
    
    static {
        orientation_values = new int[] { 0, 1 };
        orientation_strings = new String[] { "HORIZONTAL", "VERTICAL" };
    }
}
