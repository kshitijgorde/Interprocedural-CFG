// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.rmi.RemoteException;

public class ServiceUnavailableException extends RemoteException
{
    public ServiceUnavailableException() {
    }
    
    public ServiceUnavailableException(final String s) {
        super(s);
    }
    
    public ServiceUnavailableException(final String s, final Throwable cause) {
        super(s, cause);
    }
}
