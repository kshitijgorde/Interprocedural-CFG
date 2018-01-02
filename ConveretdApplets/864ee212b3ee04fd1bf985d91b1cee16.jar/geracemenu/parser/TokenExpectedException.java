// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class TokenExpectedException extends RuntimeException
{
    public TokenExpectedException() {
        super("Token expected");
    }
    
    public TokenExpectedException(final String s) {
        super("Token expected: " + s);
    }
    
    public TokenExpectedException(final int n, final String s) {
        super("Line [" + n + "] Token expected: " + s);
    }
    
    public TokenExpectedException(final String s, final String s2) {
        super("Token expected: " + s + " but found: " + s2);
    }
    
    public TokenExpectedException(final int n, final String s, final String s2) {
        super("Line [" + n + "] Token expected: " + s + " but found: " + s2);
    }
}
