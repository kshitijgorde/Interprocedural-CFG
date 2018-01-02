// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCConverter;

class ComponentConverter
{
    static void getParams(final JCComponent jcComponent) {
        final JCConverter conv = JCComponent.conv;
        jcComponent.setBackground(conv.toColor(jcComponent.getParam("background"), jcComponent.getBackground()));
        jcComponent.setForeground(conv.toColor(jcComponent.getParam("foreground"), jcComponent.getForeground()));
        jcComponent.setFont(conv.toFont(jcComponent.getParam("font"), jcComponent.getFont()));
        jcComponent.shadow = conv.toInt(jcComponent.getParam("ShadowThickness"), jcComponent.shadow);
        jcComponent.highlight = conv.toInt(jcComponent.getParam("HighlightThickness"), jcComponent.highlight);
        jcComponent.highlight_color = conv.toColor(jcComponent.getParam("HighlightColor"), jcComponent.highlight_color);
        jcComponent.insets = conv.toInsets(jcComponent.getParam("insets"), jcComponent.insets);
        jcComponent.pref_width = conv.toInt(jcComponent.getParam("preferredWidth"), jcComponent.pref_width);
        jcComponent.pref_height = conv.toInt(jcComponent.getParam("preferredHeight"), jcComponent.pref_height);
        jcComponent.traversable = conv.toBoolean(jcComponent.getParam("traversable"), jcComponent.traversable);
        jcComponent.double_buffer = conv.toBoolean(jcComponent.getParam("doubleBuffer"), jcComponent.double_buffer);
    }
}
