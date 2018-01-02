// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha;

public class RuntimeStoreException extends RuntimeException
{
    private static final long serialVersionUID = 8807084681372365173L;
    
    public RuntimeStoreException() {
    }
    
    public RuntimeStoreException(final String message) {
        super(message);
    }
    
    public RuntimeStoreException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public RuntimeStoreException(final Throwable cause) {
        super(cause);
    }
}
