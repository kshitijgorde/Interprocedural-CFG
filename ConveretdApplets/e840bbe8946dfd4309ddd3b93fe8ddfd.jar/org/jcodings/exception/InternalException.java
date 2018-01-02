// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.exception;

public class InternalException extends JCodingsException
{
    private static final long serialVersionUID = -3871816465397927992L;
    
    public InternalException(final String message) {
        super(message);
    }
    
    public InternalException(final String message, final String str) {
        super(message, str);
    }
    
    public InternalException(final String message, final byte[] bytes, final int p, final int end) {
        super(message, bytes, p, end);
    }
}
