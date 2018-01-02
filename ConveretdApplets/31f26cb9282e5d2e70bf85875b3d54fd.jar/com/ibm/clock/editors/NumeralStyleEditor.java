// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock.editors;

import java.beans.PropertyEditorSupport;

public class NumeralStyleEditor extends PropertyEditorSupport
{
    private static final String[] NAMES;
    private static final int[] VALUES;
    
    private int getIndex(final int n) {
        int n2 = -1;
        for (int i = 0; i < NumeralStyleEditor.VALUES.length; ++i) {
            if (NumeralStyleEditor.VALUES[i] == n) {
                n2 = i;
                break;
            }
        }
        return n2;
    }
    
    private int getIndex(final String s) {
        int n = -1;
        for (int i = 0; i < NumeralStyleEditor.NAMES.length; ++i) {
            if (NumeralStyleEditor.NAMES[i].equals(s)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    public String getAsText() {
        return NumeralStyleEditor.NAMES[this.getIndex((int)this.getValue())];
    }
    
    public String[] getTags() {
        return NumeralStyleEditor.NAMES;
    }
    
    public boolean supportsCustomEditor() {
        return false;
    }
    
    public void setAsText(final String s) {
        this.setValue(new Integer(NumeralStyleEditor.VALUES[this.getIndex(s)]));
    }
    
    public String getJavaInitializationString() {
        return String.valueOf((int)this.getValue());
    }
    
    static {
        NAMES = new String[] { "ARABIC", "ROMAN" };
        VALUES = new int[] { 1, 0 };
    }
}
