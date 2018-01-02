// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.utility;

public class MalformedGuidException extends Exception
{
    private static final long serialVersionUID = -9013165946060985983L;
    private String guid;
    
    public MalformedGuidException() {
        this.guid = "";
    }
    
    public MalformedGuidException(final String guid) {
        this.guid = "";
        this.setGuid(guid);
    }
    
    public final void setGuid(final String guid) {
        this.guid = guid;
    }
    
    public final String getGuid() {
        return this.guid;
    }
}
