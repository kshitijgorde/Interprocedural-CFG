// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

public final class NotAttachedException extends ConnectorException
{
    private static final long serialVersionUID = 8424409627819350472L;
    private final int status;
    
    NotAttachedException(final int i) {
        this.status = i;
    }
    
    NotAttachedException(final int newStatus, final Throwable cause) {
        this(newStatus);
        this.initCause(cause);
    }
    
    public int getStatus() {
        return this.status;
    }
}
