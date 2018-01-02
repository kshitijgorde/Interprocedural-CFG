// 
// Decompiled by Procyon v0.5.30
// 

package com.skype.connector;

public class ConnectorException extends Exception
{
    private static final long serialVersionUID = -764987191989792842L;
    
    public ConnectorException() {
    }
    
    public ConnectorException(final String message) {
        super(message);
    }
    
    public ConnectorException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
