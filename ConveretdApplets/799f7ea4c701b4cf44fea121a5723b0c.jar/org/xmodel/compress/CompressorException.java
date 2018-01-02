// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

public class CompressorException extends RuntimeException
{
    public CompressorException() {
    }
    
    public CompressorException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public CompressorException(final String s) {
        super(s);
    }
    
    public CompressorException(final Throwable t) {
        super(t);
    }
}
