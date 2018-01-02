// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.richtext;

public class IllegallyTerminatedStringException extends RuntimeException
{
    public IllegallyTerminatedStringException() {
        super("String has been illegally terminated");
    }
    
    public IllegallyTerminatedStringException(final String s) {
        super(s);
    }
}
