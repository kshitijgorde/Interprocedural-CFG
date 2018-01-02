// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Component;

class ButtonConverter
{
    static void getParams(final JCButton jcButton) {
        jcButton.arm_label = JCComponent.conv.toJCString(jcButton, jcButton.getParam("armLabel"), jcButton.arm_label);
    }
}
