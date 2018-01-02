// 
// Decompiled by Procyon v0.5.30
// 

package z.A.A.C;

import java.io.PrintWriter;
import java.io.PrintStream;

public class C extends Exception
{
    private final Throwable A;
    
    public C(final String s) {
        this(s, null);
    }
    
    public C(final Throwable t) {
        this(null, t);
    }
    
    public C(final String s, final Throwable a) {
        super(s);
        this.A = a;
    }
    
    public Throwable A() {
        return this.A;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.toString());
        if (this.A != null) {
            sb.append("\n");
            sb.append("--- inner exception ---");
            sb.append("\n");
            sb.append(this.A.toString());
        }
        return sb.toString();
    }
    
    public void printStackTrace(final PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.A != null) {
            printStream.println("--- inner exception ---");
            this.A.printStackTrace(printStream);
        }
    }
    
    public void printStackTrace(final PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.A != null) {
            printWriter.println("--- inner exception ---");
            this.A.printStackTrace(printWriter);
        }
    }
    
    public void printStackTrace() {
        super.printStackTrace();
        if (this.A != null) {
            System.err.println("--- inner exception ---");
            this.A.printStackTrace();
        }
    }
}
