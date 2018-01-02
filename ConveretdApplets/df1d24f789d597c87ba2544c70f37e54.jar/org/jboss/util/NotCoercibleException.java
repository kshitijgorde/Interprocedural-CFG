// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

public class NotCoercibleException extends CoercionException
{
    public NotCoercibleException(final String msg) {
        super(msg);
    }
    
    public NotCoercibleException(final String msg, final Throwable nested) {
        super(msg, nested);
    }
    
    public NotCoercibleException(final Throwable nested) {
        super(nested);
    }
    
    public NotCoercibleException() {
    }
    
    public NotCoercibleException(final Object obj) {
        super(String.valueOf(obj));
    }
}
