// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

public class UnterminatedStringException extends RuntimeException
{
    public UnterminatedStringException() {
        super("Unterminated string specified");
    }
    
    public UnterminatedStringException(final String s) {
        super(s);
    }
}
