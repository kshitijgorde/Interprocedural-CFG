// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

public final class IoSessionInitializationException extends RuntimeException
{
    private static final long serialVersionUID = -1205810145763696189L;
    
    public IoSessionInitializationException() {
    }
    
    public IoSessionInitializationException(final String s, final Throwable t) {
        super(s, t);
    }
}
