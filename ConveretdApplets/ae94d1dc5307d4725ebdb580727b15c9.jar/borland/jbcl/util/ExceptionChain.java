// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

import java.io.PrintStream;

public class ExceptionChain
{
    private ExceptionChain next;
    private Exception ex;
    
    public ExceptionChain() {
    }
    
    private ExceptionChain(final ExceptionChain next, final Exception ex) {
        this.next = next;
        this.ex = ex;
    }
    
    public void append(final Exception ex) {
        if (this.ex == null) {
            this.ex = ex;
        }
        else {
            this.next = new ExceptionChain(this.next, ex);
        }
    }
    
    public void printStackTrace(final PrintStream out) {
        this.printStackTrace(this, out);
    }
    
    private void printStackTrace(final ExceptionChain exChain, final PrintStream out) {
        if (exChain != null && exChain.next != null) {
            exChain.printStackTrace(exChain.next, out);
        }
        if (this.ex != null) {
            this.ex.printStackTrace(out);
        }
    }
    
    public boolean hasExceptions() {
        return this.ex != null;
    }
    
    public ExceptionChain getNext() {
        return this.next;
    }
    
    public Exception getException() {
        return this.ex;
    }
}
