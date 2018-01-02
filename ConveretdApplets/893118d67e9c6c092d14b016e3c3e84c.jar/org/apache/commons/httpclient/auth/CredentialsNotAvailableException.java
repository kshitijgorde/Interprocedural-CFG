// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

public class CredentialsNotAvailableException extends AuthenticationException
{
    public CredentialsNotAvailableException() {
    }
    
    public CredentialsNotAvailableException(final String message) {
        super(message);
    }
    
    public CredentialsNotAvailableException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
