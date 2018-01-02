// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.filterchain;

public final class IoFilterLifeCycleException extends RuntimeException
{
    private static final long serialVersionUID = -5542098881633506449L;
    
    public IoFilterLifeCycleException() {
    }
    
    public IoFilterLifeCycleException(final String s, final Throwable t) {
        super(s, t);
    }
}
