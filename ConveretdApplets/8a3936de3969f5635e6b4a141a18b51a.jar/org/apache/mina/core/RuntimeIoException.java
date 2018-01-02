// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core;

public final class RuntimeIoException extends RuntimeException
{
    private static final long serialVersionUID = 9029092241311939548L;
    
    public RuntimeIoException() {
    }
    
    public RuntimeIoException(final String s) {
        super(s);
    }
    
    public RuntimeIoException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public RuntimeIoException(final Throwable t) {
        super(t);
    }
}
