// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import jclass.util.JCConverter;

class ContainerConverter
{
    static void getParams(final JCContainer jcContainer) {
        final JCConverter conv = JCContainer.conv;
        jcContainer.setBackground(conv.toColor(jcContainer.getParam("background"), jcContainer.getBackground()));
        jcContainer.setForeground(conv.toColor(jcContainer.getParam("foreground"), jcContainer.getForeground()));
        jcContainer.setFont(conv.toFont(jcContainer.getParam("font"), jcContainer.getFont()));
        jcContainer.insets = conv.toInsets(jcContainer.getParam("insets"), jcContainer.insets);
        jcContainer.pref_width = conv.toInt(jcContainer.getParam("preferredWidth"), jcContainer.pref_width);
        jcContainer.pref_height = conv.toInt(jcContainer.getParam("preferredHeight"), jcContainer.pref_height);
    }
}
