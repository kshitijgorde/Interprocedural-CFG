// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.deployment;

import org.jboss.util.NestedException;

public class DeploymentException extends NestedException
{
    private static final long serialVersionUID = 1416258464473965574L;
    
    public static void rethrowAsDeploymentException(final String message, final Throwable t) throws DeploymentException {
        if (t instanceof DeploymentException) {
            throw (DeploymentException)t;
        }
        throw new DeploymentException(message, t);
    }
    
    public DeploymentException(final String msg) {
        super(msg);
    }
    
    public DeploymentException(final String msg, final Throwable nested) {
        super(msg, nested);
    }
    
    public DeploymentException(final Throwable nested) {
        super(nested);
    }
    
    public DeploymentException() {
    }
}
