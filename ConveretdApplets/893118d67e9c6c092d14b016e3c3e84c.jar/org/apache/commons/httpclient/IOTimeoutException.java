// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class IOTimeoutException extends HttpTimeoutException
{
    public IOTimeoutException() {
    }
    
    public IOTimeoutException(final String message) {
        super(message);
    }
    
    public IOTimeoutException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
