// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.exception;

public class ValueException extends SyntaxException
{
    private static final long serialVersionUID = -196013852479929134L;
    
    public ValueException(final String message) {
        super(message);
    }
    
    public ValueException(final String message, final String str) {
        super(message.replaceAll("%n", str));
    }
    
    public ValueException(final String message, final byte[] bytes, final int p, final int end) {
        this(message, new String(bytes, p, end - p));
    }
}
