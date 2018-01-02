// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class CoercionException extends NestedRuntimeException
{
    public CoercionException(final String msg) {
        super(msg);
    }
    
    public CoercionException(final String msg, final Throwable nested) {
        super(msg, nested);
    }
    
    public CoercionException(final Throwable nested) {
        super(nested);
    }
    
    public CoercionException() {
    }
}
