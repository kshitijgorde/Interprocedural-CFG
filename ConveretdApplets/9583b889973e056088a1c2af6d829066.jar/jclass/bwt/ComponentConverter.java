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
        jcComponent.setBorderThickness(conv.toInt(jcComponent.getParam("ShadowThickness"), jcComponent.getBorderThickness()));
        jcComponent.setHighlightThickness(conv.toInt(jcComponent.getParam("HighlightThickness"), jcComponent.getHighlightThickness()));
        jcComponent.setHighlightColor(conv.toColor(jcComponent.getParam("HighlightColor"), jcComponent.getHighlightColor()));
        jcComponent.setInsets(conv.toInsets(jcComponent.getParam("insets"), jcComponent.getInsets()));
        jcComponent.setPreferredSize(conv.toInt(jcComponent.getParam("preferredWidth"), jcComponent.getPreferredWidthInternal()), conv.toInt(jcComponent.getParam("preferredHeight"), jcComponent.getPreferredHeightInternal()));
        jcComponent.setTraversable(conv.toBoolean(jcComponent.getParam("traversable"), jcComponent.isTraversable()));
        jcComponent.setDoubleBuffer(conv.toBoolean(jcComponent.getParam("doubleBuffer"), jcComponent.getDoubleBuffer()));
    }
}
