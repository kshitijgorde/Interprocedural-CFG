// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

public final class ProtocolEncoderException extends ProtocolCodecException
{
    private static final long serialVersionUID = 8752989973624459604L;
    
    public ProtocolEncoderException() {
    }
    
    public ProtocolEncoderException(final String s) {
        super(s);
    }
    
    public ProtocolEncoderException(final Throwable t) {
        super(t);
    }
}
