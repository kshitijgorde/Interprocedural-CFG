// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.log4j.config;

public class PropertySetterException extends Exception
{
    protected Throwable rootCause;
    
    public PropertySetterException(final String s) {
        super(s);
    }
    
    public PropertySetterException(final Throwable rootCause) {
        this.rootCause = rootCause;
    }
    
    public String getMessage() {
        String s = super.getMessage();
        if (s == null && this.rootCause != null) {
            s = this.rootCause.getMessage();
        }
        return s;
    }
}
