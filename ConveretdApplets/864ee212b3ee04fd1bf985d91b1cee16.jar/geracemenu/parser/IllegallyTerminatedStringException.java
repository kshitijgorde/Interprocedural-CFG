// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

public class IllegallyTerminatedStringException extends RuntimeException
{
    public IllegallyTerminatedStringException() {
        super("String has been illegally terminated");
    }
    
    public IllegallyTerminatedStringException(final String s) {
        super(s);
    }
    
    public IllegallyTerminatedStringException(final int n) {
        super("Line [" + n + "] String has been illegally terminated.");
    }
}
