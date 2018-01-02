// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.filter.codec;

public class ProtocolDecoderException extends ProtocolCodecException
{
    private static final long serialVersionUID = 3545799879533408565L;
    private String hexdump;
    
    public ProtocolDecoderException() {
    }
    
    public ProtocolDecoderException(final String s) {
        super(s);
    }
    
    public ProtocolDecoderException(final Throwable t) {
        super(t);
    }
    
    public ProtocolDecoderException(final String s, final Throwable t) {
        super(s, t);
    }
    
    @Override
    public String getMessage() {
        String message;
        if ((message = super.getMessage()) == null) {
            message = "";
        }
        if (this.hexdump != null) {
            return message + ((message.length() > 0) ? " " : "") + "(Hexdump: " + this.hexdump + ')';
        }
        return message;
    }
    
    public final String getHexdump() {
        return this.hexdump;
    }
    
    public final void setHexdump(final String hexdump) {
        if (this.hexdump != null) {
            throw new IllegalStateException("Hexdump cannot be set more than once.");
        }
        this.hexdump = hexdump;
    }
}
