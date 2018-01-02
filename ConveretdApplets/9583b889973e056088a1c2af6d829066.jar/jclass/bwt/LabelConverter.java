// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCUtilConverter;
import jclass.util.JCConverter;
import java.awt.Component;

class LabelConverter
{
    static final int[] alignment_values;
    static final String[] alignment_strings;
    static final String[] shadowtype_strings;
    static final int[] shadowtype_values;
    
    static void getParams(final JCLabel jcLabel) {
        final JCConverter conv = JCComponent.conv;
        jcLabel.label = conv.toJCString(jcLabel, jcLabel.getParam("label"), jcLabel.label);
        jcLabel.alignment = toAlignment(conv, jcLabel.getParam("alignment"), jcLabel.alignment);
        jcLabel.setBorderStyle(conv.toEnum(jcLabel.getParam("shadowType"), "shadowType", LabelConverter.shadowtype_strings, LabelConverter.shadowtype_values, jcLabel.getBorderStyle()));
    }
    
    static int toAlignment(final JCConverter jcConverter, final String s, final int n) {
        return jcConverter.toEnum(s, "alignment", LabelConverter.alignment_strings, LabelConverter.alignment_values, n);
    }
    
    static void checkAlignment(final int n) {
        JCUtilConverter.checkEnum(n, "alignment", LabelConverter.alignment_values);
    }
    
    static {
        alignment_values = new int[] { 0, 0, 1, 1, 2, 2, 3, 4, 5, 6, 7, 8 };
        alignment_strings = new String[] { "TOPLEFT", "LEFT", "TOPCENTER", "CENTER", "TOPRIGHT", "RIGHT", "MIDDLELEFT", "MIDDLECENTER", "MIDDLERIGHT", "BOTTOMLEFT", "BOTTOMCENTER", "BOTTOMRIGHT" };
        shadowtype_strings = new String[] { "SHADOW_NONE", "SHADOW_ETCHED_IN", "SHADOW_ETCHED_OUT", "SHADOW_IN", "SHADOW_OUT", "SHADOW_PLAIN", "SHADOW_FRAME_IN", "SHADOW_FRAME_OUT", "CONTROL_SHADOW_IN", "CONTROL_SHADOW_OUT" };
        shadowtype_values = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    }
}
