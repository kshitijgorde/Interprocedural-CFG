// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

public class UnknownTagException extends RuntimeException
{
    public UnknownTagException() {
        super("Unknown tag specified");
    }
    
    public UnknownTagException(final String s) {
        super(s);
    }
}
