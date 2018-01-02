// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm;

public class DeviceException extends Exception
{
    static final long serialVersionUID = 3223373393714091186L;
    
    public DeviceException() {
    }
    
    public DeviceException(final String message) {
        super(message);
    }
    
    public DeviceException(final Exception e) {
        super(e);
    }
}
