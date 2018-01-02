// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

public class ProtocolCodecException extends Exception
{
    private static final long serialVersionUID = 5939878548186330695L;
    
    public ProtocolCodecException() {
    }
    
    public ProtocolCodecException(final String s) {
        super(s);
    }
    
    public ProtocolCodecException(final Throwable t) {
        super(t);
    }
    
    public ProtocolCodecException(final String s, final Throwable t) {
        super(s, t);
    }
}
