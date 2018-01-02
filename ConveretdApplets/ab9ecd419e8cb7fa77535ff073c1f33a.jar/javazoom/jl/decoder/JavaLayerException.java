// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

import java.io.PrintStream;

public class JavaLayerException extends Exception
{
    private Throwable exception;
    
    public JavaLayerException() {
    }
    
    public JavaLayerException(final String s) {
        super(s);
    }
    
    public JavaLayerException(final String s, final Throwable exception) {
        super(s);
        this.exception = exception;
    }
    
    public Throwable getException() {
        return this.exception;
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
    
    public void printStackTrace(final PrintStream printStream) {
        if (this.exception == null) {
            super.printStackTrace(printStream);
        }
        else {
            this.exception.printStackTrace();
        }
    }
}
