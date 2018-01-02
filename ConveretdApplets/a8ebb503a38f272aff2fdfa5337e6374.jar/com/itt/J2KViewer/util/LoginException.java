// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

public class LoginException extends Exception
{
    private static final long serialVersionUID = 1L;
    public boolean doRetry;
    
    public LoginException() {
    }
    
    public LoginException(final String s, final boolean doRetry) {
        super(s);
        this.doRetry = doRetry;
    }
    
    public LoginException(final String s, final Throwable t, final boolean doRetry) {
        super(s, t);
        this.doRetry = doRetry;
    }
}
