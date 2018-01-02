// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock.editors;

import java.beans.PropertyEditorSupport;

public class DisplayModeEditor extends PropertyEditorSupport
{
    private static final String[] NAMES;
    private static final int[] VALUES;
    
    private int getIndex(final int n) {
        int n2 = -1;
        for (int i = 0; i < DisplayModeEditor.VALUES.length; ++i) {
            if (DisplayModeEditor.VALUES[i] == n) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    private int getIndex(final String s) {
        int n = -1;
        for (int i = 0; i < DisplayModeEditor.NAMES.length; ++i) {
            if (DisplayModeEditor.NAMES[i].equals(s)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public String getAsText() {
        return DisplayModeEditor.NAMES[this.getIndex((int)this.getValue())];
    }
    
    public String[] getTags() {
        return DisplayModeEditor.NAMES;
    }
    
    public boolean supportsCustomEditor() {
        return false;
    }
    
    public void setAsText(final String s) {
        this.setValue(new Integer(DisplayModeEditor.VALUES[this.getIndex(s)]));
    }
    
    public String getJavaInitializationString() {
        return String.valueOf((int)this.getValue());
    }
    
    static {
        NAMES = new String[] { "DIGITAL", "ANALOG" };
        VALUES = new int[] { 5, 4 };
    }
}
