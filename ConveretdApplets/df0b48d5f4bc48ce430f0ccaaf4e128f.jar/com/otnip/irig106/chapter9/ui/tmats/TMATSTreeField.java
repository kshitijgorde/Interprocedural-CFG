// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.ui.tmats;

public class TMATSTreeField
{
    String textDisplay;
    String fieldName;
    Object object;
    String iconPath;
    
    TMATSTreeField(final String textDisplay, final String fieldName, final Object object) {
        this.textDisplay = textDisplay;
        this.fieldName = fieldName;
        this.object = object;
    }
    
    TMATSTreeField(final String textDisplay, final String fieldName, final Object object, final String iconPath) {
        this.textDisplay = textDisplay;
        this.fieldName = fieldName;
        this.object = object;
        this.iconPath = iconPath;
    }
}
