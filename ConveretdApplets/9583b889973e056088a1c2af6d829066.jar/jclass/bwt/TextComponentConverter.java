// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCUtilConverter;
import jclass.util.JCConverter;

class TextComponentConverter
{
    static final String[] alignment_strings;
    static final int[] alignment_values;
    static final int[] case_values;
    static final String[] case_strings;
    
    static void getParams(final JCTextComponent jcTextComponent) {
        final JCConverter conv = JCComponent.conv;
        jcTextComponent.alignment = conv.toEnum(jcTextComponent.getParam("alignment"), "alignment", TextComponentConverter.alignment_strings, TextComponentConverter.alignment_values, jcTextComponent.alignment);
        jcTextComponent.string_case = conv.toEnum(jcTextComponent.getParam("StringCase"), "StringCase", TextComponentConverter.case_strings, TextComponentConverter.case_values, jcTextComponent.string_case);
        jcTextComponent.columns = conv.toInt(jcTextComponent.getParam("Columns"), jcTextComponent.columns);
        final String param = jcTextComponent.getParam("Text");
        if (param != null) {
            jcTextComponent.text = param.toCharArray();
        }
        jcTextComponent.max_length = conv.toInt(jcTextComponent.getParam("MaximumLength"), jcTextComponent.max_length);
        jcTextComponent.cursor_pos = conv.toInt(jcTextComponent.getParam("CursorPosition"), jcTextComponent.cursor_pos);
        jcTextComponent.display_cursor = conv.toBoolean(jcTextComponent.getParam("ShowCursorPosition"), jcTextComponent.display_cursor);
        jcTextComponent.overstrike = conv.toBoolean(jcTextComponent.getParam("Overstrike"), jcTextComponent.overstrike);
        jcTextComponent.editable = conv.toBoolean(jcTextComponent.getParam("Editable"), jcTextComponent.editable);
        jcTextComponent.setSelectedBackground(conv.toColor(jcTextComponent.getParam("SelectedBackground"), jcTextComponent.getSelectedBackground()));
        jcTextComponent.setSelectedForeground(conv.toColor(jcTextComponent.getParam("SelectedForeground"), jcTextComponent.getSelectedBackground()));
    }
    
    static void checkAlignment(final int n) {
        JCUtilConverter.checkEnum(n, "alignment", TextComponentConverter.alignment_values);
    }
    
    static void checkStringCase(final int n) {
        JCUtilConverter.checkEnum(n, "StringCase", TextComponentConverter.case_values);
    }
    
    static {
        alignment_strings = new String[] { "LEFT", "CENTER", "RIGHT" };
        alignment_values = new int[] { 0, 1, 2 };
        case_values = new int[] { 0, 1, 2 };
        case_strings = new String[] { "CASE_AS_IS", "CASE_LOWER", "CASE_UPPER" };
    }
}
