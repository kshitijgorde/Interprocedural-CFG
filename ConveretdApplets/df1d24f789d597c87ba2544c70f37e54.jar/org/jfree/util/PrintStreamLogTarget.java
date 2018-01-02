// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.PrintStream;
import java.io.Serializable;

public class PrintStreamLogTarget implements LogTarget, Serializable
{
    private PrintStream printStream;
    
    public PrintStreamLogTarget() {
        this(System.out);
    }
    
    public PrintStreamLogTarget(final PrintStream printStream) {
        if (printStream == null) {
            throw new NullPointerException();
        }
        this.printStream = printStream;
    }
    
    public void log(int n, final Object o) {
        if (n > 3) {
            n = 3;
        }
        this.printStream.print(LogTarget.LEVELS[n]);
        this.printStream.println(o);
        if (n < 3) {
            System.out.flush();
        }
    }
    
    public void log(int n, final Object o, final Exception ex) {
        if (n > 3) {
            n = 3;
        }
        this.printStream.print(LogTarget.LEVELS[n]);
        this.printStream.println(o);
        ex.printStackTrace(this.printStream);
        if (n < 3) {
            System.out.flush();
        }
    }
}
