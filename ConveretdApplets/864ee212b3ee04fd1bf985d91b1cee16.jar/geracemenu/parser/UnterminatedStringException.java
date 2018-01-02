// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class UnterminatedStringException extends RuntimeException
{
    public UnterminatedStringException() {
        super("Unterminated string specified");
    }
    
    public UnterminatedStringException(final String s) {
        super(s);
    }
    
    public UnterminatedStringException(final int n, final String s) {
        super("Line [" + n + "] " + s);
    }
}
