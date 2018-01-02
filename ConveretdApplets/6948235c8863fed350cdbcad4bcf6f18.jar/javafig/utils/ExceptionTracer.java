// 
// Decompiled by Procyon v0.5.30
// 

package javafig.utils;

import java.io.PrintStream;

public class ExceptionTracer
{
    private static boolean printEnabled;
    private static PrintStream stream;
    
    public static boolean getEnabled() {
        return ExceptionTracer.printEnabled;
    }
    
    public static void setEnabled(final boolean printEnabled) {
        ExceptionTracer.printEnabled = printEnabled;
    }
    
    public static void setPrintStream(final PrintStream stream) {
        ExceptionTracer.stream = stream;
    }
    
    public static void trace(final Throwable t) {
        if (ExceptionTracer.printEnabled) {
            t.printStackTrace(ExceptionTracer.stream);
        }
    }
    
    public static void message(final String s) {
        if (ExceptionTracer.printEnabled) {
            ExceptionTracer.stream.println(s);
        }
    }
    
    static {
        ExceptionTracer.printEnabled = true;
        ExceptionTracer.stream = System.err;
    }
}
