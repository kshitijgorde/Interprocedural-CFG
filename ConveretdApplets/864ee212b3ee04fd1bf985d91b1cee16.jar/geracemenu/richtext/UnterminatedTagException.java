// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

public class UnterminatedTagException extends RuntimeException
{
    public UnterminatedTagException() {
        super("Unterminated tag specified");
    }
    
    public UnterminatedTagException(final String s) {
        super(s);
    }
}
