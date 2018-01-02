// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class ConnectTimeoutException extends HttpTimeoutException
{
    public ConnectTimeoutException() {
    }
    
    public ConnectTimeoutException(final String message) {
        super(message);
    }
    
    public ConnectTimeoutException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
