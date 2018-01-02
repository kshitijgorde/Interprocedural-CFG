// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class HttpTimeoutException extends HttpRecoverableException
{
    public HttpTimeoutException() {
    }
    
    public HttpTimeoutException(final String message) {
        super(message);
    }
    
    public HttpTimeoutException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
