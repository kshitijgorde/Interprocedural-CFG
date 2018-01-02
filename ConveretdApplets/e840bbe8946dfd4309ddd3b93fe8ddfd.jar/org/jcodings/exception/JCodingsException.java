// 
// Decompiled by Procyon v0.5.30
// 

package org.jcodings.exception;

public class JCodingsException extends RuntimeException
{
    public JCodingsException(final String message) {
        super(message);
    }
    
    public JCodingsException(final String message, final String str) {
        super(message.replaceAll("%n", str));
    }
    
    public JCodingsException(final String message, final byte[] bytes, final int p, final int end) {
        this(message, new String(bytes, p, end - p));
    }
}
