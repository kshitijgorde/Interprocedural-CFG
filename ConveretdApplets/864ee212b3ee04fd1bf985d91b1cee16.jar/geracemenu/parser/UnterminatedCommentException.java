// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class UnterminatedCommentException extends RuntimeException
{
    public UnterminatedCommentException() {
        super("Unterminated multi-line comment specified");
    }
    
    public UnterminatedCommentException(final String s) {
        super(s);
    }
    
    public UnterminatedCommentException(final int n, final String s) {
        super("Line [" + n + "] " + s);
    }
}
