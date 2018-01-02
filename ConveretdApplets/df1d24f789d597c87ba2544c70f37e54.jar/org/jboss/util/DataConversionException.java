// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class DataConversionException extends NestedRuntimeException
{
    public DataConversionException(final String msg) {
        super(msg);
    }
    
    public DataConversionException(final String msg, final Throwable detail) {
        super(msg, detail);
    }
    
    public DataConversionException(final Throwable detail) {
        super(detail);
    }
    
    public DataConversionException() {
    }
}
