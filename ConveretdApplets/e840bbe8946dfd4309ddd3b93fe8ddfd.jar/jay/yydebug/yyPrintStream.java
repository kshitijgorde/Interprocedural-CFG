// 
// Decompiled by Procyon v0.5.30
// 

package jay.yydebug;

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.PrintStream;

public abstract class yyPrintStream extends PrintStream
{
    protected static final String nl;
    
    public yyPrintStream() {
        super(new FilterOutputStream(null), true);
    }
    
    public boolean checkError() {
        return false;
    }
    
    public abstract void close();
    
    public void flush() {
    }
    
    public void print(final boolean b) {
        this.print("" + b);
    }
    
    public void print(final char c) {
        this.print("" + c);
    }
    
    public void print(final char[] array) {
        this.print((array != null) ? ("" + (Object)array) : ("" + (Object)null));
    }
    
    public void print(final double n) {
        this.print("" + n);
    }
    
    public void print(final float n) {
        this.print("" + n);
    }
    
    public void print(final int n) {
        this.print("" + n);
    }
    
    public void print(final long n) {
        this.print("" + n);
    }
    
    public void print(final Object o) {
        this.print("" + o);
    }
    
    public void print(final String s) {
        final byte[] bytes = ((s != null) ? s : ("" + (Object)null)).getBytes();
        if (bytes.length > 0) {
            this.write(bytes, 0, bytes.length);
        }
    }
    
    public void println() {
        this.print(yyPrintStream.nl);
    }
    
    public void println(final boolean b) {
        this.print("" + b + yyPrintStream.nl);
    }
    
    public void println(final char c) {
        this.print("" + c + yyPrintStream.nl);
    }
    
    public void println(final char[] array) {
        this.print((array != null) ? ((Object)array + yyPrintStream.nl) : ((Object)null + yyPrintStream.nl));
    }
    
    public void println(final double n) {
        this.print("" + n + yyPrintStream.nl);
    }
    
    public void println(final float n) {
        this.print("" + n + yyPrintStream.nl);
    }
    
    public void println(final int n) {
        this.print("" + n + yyPrintStream.nl);
    }
    
    public void println(final long n) {
        this.print("" + n + yyPrintStream.nl);
    }
    
    public void println(final Object o) {
        this.print("" + o + yyPrintStream.nl);
    }
    
    public void println(final String s) {
        this.print((s != null) ? (s + yyPrintStream.nl) : ((Object)null + yyPrintStream.nl));
    }
    
    public abstract void write(final byte[] p0, final int p1, final int p2);
    
    public abstract void write(final int p0);
    
    static {
        nl = System.getProperty("line.separator", "\n");
    }
}
