// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCUtilConverter;
import jclass.util.JCConverter;

class ScrolledWindowConverter
{
    static final String[] display_strings;
    static final int[] display_values;
    
    static void getParams(final JCScrolledWindow jcScrolledWindow) {
        final JCConverter conv = JCContainer.conv;
        jcScrolledWindow.setScrollbarDisplay(conv.toEnum(jcScrolledWindow.getParam("ScrollbarDisplay"), "ScrollbarDisplay", ScrolledWindowConverter.display_strings, ScrolledWindowConverter.display_values, jcScrolledWindow.getScrollbarDisplay()));
        jcScrolledWindow.setScrollbarOffset(conv.toInt(jcScrolledWindow.getParam("ScrollbarOffset"), jcScrolledWindow.getScrollbarOffset()));
    }
    
    static void checkDisplay(final int n) {
        JCUtilConverter.checkEnum(n, "scrollbar display", ScrolledWindowConverter.display_values);
    }
    
    static {
        display_strings = new String[] { "DISPLAY_ALWAYS", "DISPLAY_AS_NEEDED", "DISPLAY_VERTICAL_ONLY", "DISPLAY_HORIZONTAL_ONLY", "DISPLAY_NONE" };
        display_values = new int[] { 1, 0, 3, 4, 2 };
    }
}
