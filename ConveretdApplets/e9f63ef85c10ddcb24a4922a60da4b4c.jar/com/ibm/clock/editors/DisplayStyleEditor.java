// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock.editors;

import java.beans.PropertyEditorSupport;

public class DisplayStyleEditor extends PropertyEditorSupport
{
    private int value;
    
    public String getAsText() {
        String s = new String();
        this.value = (int)this.getValue();
        if (this.value == 0) {
            s = "FULL";
        }
        if (this.value == 1) {
            s = "LONG";
        }
        if (this.value == 2) {
            s = "SHORT";
        }
        return s;
    }
    
    public String[] getTags() {
        return new String[] { "FULL", "LONG", "SHORT" };
    }
    
    public boolean supportsCustomEditor() {
        return false;
    }
    
    public void setAsText(final String s) {
        int n;
        if (s.equals("FULL")) {
            n = 0;
        }
        else if (s.equals("LONG")) {
            n = 1;
        }
        else if (s.equals("SHORT")) {
            n = 2;
        }
        else {
            n = -1;
        }
        this.setValue(new Integer(n));
    }
    
    public String getJavaInitializationString() {
        return String.valueOf((int)this.getValue());
    }
}
