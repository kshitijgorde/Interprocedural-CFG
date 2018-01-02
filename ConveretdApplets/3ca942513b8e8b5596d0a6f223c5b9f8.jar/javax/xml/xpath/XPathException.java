// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.xpath;

import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class XPathException extends Exception
{
    Throwable m_cause;
    private static final long serialVersionUID = -1837080260374986980L;
    
    public XPathException(final String s) {
        super(s);
        if (s == null) {
            throw new NullPointerException();
        }
    }
    
    public XPathException(final Throwable cause) {
        super((cause == null) ? null : cause.toString());
        if (cause == null) {
            throw new NullPointerException("null cause for XPathException.");
        }
        this.m_cause = cause;
    }
    
    public Throwable getCause() {
        return this.m_cause;
    }
    
    public void printStackTrace() {
        this.printStackTrace(new PrintWriter(System.err, true));
    }
    
    public void printStackTrace(final PrintStream printStream) {
        this.printStackTrace(new PrintWriter(printStream));
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        if (this.getCause() != null) {
            this.getCause().printStackTrace(printWriter);
            printWriter.println("------------------------------------------");
        }
        super.printStackTrace(printWriter);
    }
}
