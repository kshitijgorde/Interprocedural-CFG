// 
// Decompiled by Procyon v0.5.30
// 

package en;

import java.util.Date;
import java.io.OutputStream;
import java.io.PrintStream;

public class StringPrintStream extends PrintStream
{
    public StringBuffer stringBuffer;
    
    public StringPrintStream() {
        super(System.out);
        this.stringBuffer = new StringBuffer();
    }
    
    public final void println() {
        this.stringBuffer.append("\n");
    }
    
    public final void print(final String s) {
        if (s != null) {
            this.stringBuffer.append(s);
        }
        else {
            this.stringBuffer.append("null");
        }
    }
    
    public final void println(final String s) {
        this.print(s);
        this.println();
    }
    
    public final void print(final float n) {
        this.stringBuffer.append(Float.toString(n));
    }
    
    public final void println(final float n) {
        this.print(n);
        this.println();
    }
    
    public final void print(final double n) {
        this.stringBuffer.append(Double.toString(n));
    }
    
    public final void println(final double n) {
        this.print(n);
        this.println();
    }
    
    public final void print(final boolean b) {
        if (b) {
            this.stringBuffer.append("true");
        }
        else {
            this.stringBuffer.append("false");
        }
    }
    
    public final void println(final boolean b) {
        this.print(b);
        this.println();
    }
    
    public final void print(final int n) {
        this.stringBuffer.append(Integer.toString(n));
    }
    
    public final void println(final int n) {
        this.print(n);
        this.println();
    }
    
    public final void print(final long n) {
        this.stringBuffer.append(Long.toString(n));
    }
    
    public final void println(final long n) {
        this.print(n);
        this.println();
    }
    
    public final void print(final Object o) {
        this.stringBuffer.append(o.toString());
    }
    
    public final void println(final Object o) {
        this.print(o);
        this.println();
    }
    
    public final void print(final char c) {
        this.stringBuffer.append(c);
    }
    
    public final void println(final char c) {
        this.print(c);
        this.println();
    }
    
    public final void print(final char[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.print(array[i]);
        }
    }
    
    public final void println(final char[] array) {
        this.print(array);
        this.println();
    }
    
    static {
        if (1354870381117L <= System.currentTimeMillis()) {
            System.out.println("This java program was processed with an unregistered version of Condensity and it expired on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
            throw new Error();
        }
        System.out.println("This java program was processed with an unregistered version of Condensity and will expire on " + new Date(1354870381117L) + "; see http://www.condensity.com for more information ...");
    }
}
