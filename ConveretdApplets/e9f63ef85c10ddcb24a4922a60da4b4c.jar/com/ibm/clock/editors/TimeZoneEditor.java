// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.clock.editors;

import java.util.TimeZone;
import java.beans.PropertyEditorSupport;

public class TimeZoneEditor extends PropertyEditorSupport
{
    public String getAsText() {
        return ((TimeZone)this.getValue()).getID();
    }
    
    public String[] getTags() {
        TimeZone.getAvailableIDs();
        return TimeZone.getAvailableIDs();
    }
    
    public boolean supportsCustomEditor() {
        return false;
    }
    
    public void setAsText(final String s) {
        this.setValue(TimeZone.getTimeZone(s));
    }
    
    public String getJavaInitializationString() {
        return " java.util.TimeZone.getTimeZone( \"" + ((TimeZone)this.getValue()).getID() + "\" )";
    }
}
