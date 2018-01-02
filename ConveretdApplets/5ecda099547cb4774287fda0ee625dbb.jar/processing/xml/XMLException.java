// 
// Decompiled by Procyon v0.5.30
// 

package processing.xml;

import java.io.PrintStream;
import java.io.PrintWriter;

public class XMLException extends Exception
{
    private String msg;
    private String systemID;
    private int lineNr;
    private Exception encapsulatedException;
    
    public XMLException(final String s) {
        this(null, -1, null, s, false);
    }
    
    public XMLException(final Exception ex) {
        this(null, -1, ex, "Nested Exception", false);
    }
    
    public XMLException(final String s, final int n, final Exception ex) {
        this(s, n, ex, "Nested Exception", true);
    }
    
    public XMLException(final String s, final int n, final String s2) {
        this(s, n, null, s2, true);
    }
    
    public XMLException(final String systemID, final int lineNr, final Exception encapsulatedException, final String s, final boolean b) {
        super(buildMessage(systemID, lineNr, encapsulatedException, s, b));
        this.systemID = systemID;
        this.lineNr = lineNr;
        this.encapsulatedException = encapsulatedException;
        this.msg = buildMessage(systemID, lineNr, encapsulatedException, s, b);
    }
    
    private static String buildMessage(final String s, final int n, final Exception ex, final String s2, final boolean b) {
        String s3 = s2;
        if (b) {
            if (s != null) {
                s3 = s3 + ", SystemID='" + s + "'";
            }
            if (n >= 0) {
                s3 = s3 + ", Line=" + n;
            }
            if (ex != null) {
                s3 = s3 + ", Exception: " + ex;
            }
        }
        return s3;
    }
    
    protected void finalize() throws Throwable {
        this.systemID = null;
        this.encapsulatedException = null;
        super.finalize();
    }
    
    public String getSystemID() {
        return this.systemID;
    }
    
    public int getLineNr() {
        return this.lineNr;
    }
    
    public Exception getException() {
        return this.encapsulatedException;
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.encapsulatedException != null) {
            printWriter.println("*** Nested Exception:");
            this.encapsulatedException.printStackTrace(printWriter);
        }
    }
    
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.encapsulatedException != null) {
            printStream.println("*** Nested Exception:");
            this.encapsulatedException.printStackTrace(printStream);
        }
    }
    
    public void printStackTrace() {
        super.printStackTrace();
        if (this.encapsulatedException != null) {
            System.err.println("*** Nested Exception:");
            this.encapsulatedException.printStackTrace();
        }
    }
    
    public String toString() {
        return this.msg;
    }
}
