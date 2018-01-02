// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class IllegalTypeException extends RuntimeException
{
    public IllegalTypeException() {
        super("Illegal type specified");
    }
    
    public IllegalTypeException(final String s) {
        super("Illegal type specified: " + s);
    }
    
    public IllegalTypeException(final int n, final String s) {
        super("Line: [" + n + "]: Illegal type specified: " + s);
    }
}
