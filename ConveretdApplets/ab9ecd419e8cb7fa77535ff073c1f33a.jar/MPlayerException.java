import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class MPlayerException extends Exception
{
    private Throwable exception;
    
    public MPlayerException() {
    }
    
    public MPlayerException(final String s) {
        super(s);
    }
    
    public MPlayerException(final String s, final Throwable exception) {
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
