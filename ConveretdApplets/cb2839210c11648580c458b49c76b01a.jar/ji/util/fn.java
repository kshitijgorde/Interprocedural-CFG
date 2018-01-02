// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import java.io.PrintWriter;
import java.io.PrintStream;

public class fn extends RuntimeException
{
    private final Throwable a;
    private final String b = "ExceptionWrapper: ";
    
    public fn(final Throwable a) {
        this.a = a;
    }
    
    public Throwable a() {
        return this.a;
    }
    
    public String getMessage() {
        return this.a.getMessage();
    }
    
    public String getLocalizedMessage() {
        return this.a.getLocalizedMessage();
    }
    
    public String toString() {
        return "ExceptionWrapper: ".concat(String.valueOf(String.valueOf(this.a)));
    }
    
    public void printStackTrace() {
        this.printStackTrace(System.err);
    }
    
    public void printStackTrace(final PrintStream printStream) {
        synchronized (printStream) {
            printStream.print("ExceptionWrapper: ");
            this.a.printStackTrace(printStream);
        }
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        synchronized (printWriter) {
            printWriter.print("ExceptionWrapper: ");
            this.a.printStackTrace(printWriter);
        }
    }
    
    public Throwable fillInStackTrace() {
        return this.a;
    }
}
