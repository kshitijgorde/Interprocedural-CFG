// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom3;

public interface DOMError
{
    public static final short SEVERITY_WARNING = 0;
    public static final short SEVERITY_ERROR = 1;
    public static final short SEVERITY_FATAL_ERROR = 2;
    
    short getSeverity();
    
    String getMessage();
    
    Object getRelatedException();
    
    DOMLocator getLocation();
}
