// 
// Decompiled by Procyon v0.5.30
// 

package powersoft.powerj.util;

import java.io.OutputStream;
import java.io.PrintStream;

public class DebugPrintStream extends PrintStream
{
    protected boolean _unicode;
    
    public DebugPrintStream(final OutputStream out) {
        super(out);
        Debug.checkArgument(out != null, "out != null");
    }
    
    public void writeByte(final int b) {
        super.write(b);
    }
    
    public void write(final int b) {
        super.write(b >> 8);
        super.write(b & 0xFF);
    }
    
    public synchronized void print(String s) {
        if (s == null) {
            s = "null";
        }
        for (int len = s.length(), i = 0; i < len; ++i) {
            this.write(s.charAt(i));
        }
    }
    
    public void print(final Object obj) {
        this.print(String.valueOf(obj));
    }
    
    public synchronized void print(final char[] s) {
        for (int i = 0; i < s.length; ++i) {
            this.write(s[i]);
        }
    }
    
    public void print(final char c) {
        this.print(String.valueOf(c));
    }
    
    public void print(final int i) {
        this.print(String.valueOf(i));
    }
    
    public void print(final long l) {
        this.print(String.valueOf(l));
    }
    
    public void print(final float f) {
        this.print(String.valueOf(f));
    }
    
    public void print(final double d) {
        this.print(String.valueOf(d));
    }
    
    public void print(final boolean b) {
        this.print(b ? "true" : "false");
    }
    
    public void println() {
        this.write(10);
    }
    
    public synchronized void println(final Object obj) {
        this.print(obj);
        this.write(10);
    }
    
    public synchronized void println(final String s) {
        this.print(s);
        this.write(10);
    }
    
    public synchronized void println(final char[] s) {
        this.print(s);
        this.write(10);
    }
    
    public synchronized void println(final char c) {
        this.print(c);
        this.write(10);
    }
    
    public synchronized void println(final int i) {
        this.print(i);
        this.write(10);
    }
    
    public synchronized void println(final long l) {
        this.print(l);
        this.write(10);
    }
    
    public synchronized void println(final float f) {
        this.print(f);
        this.write(10);
    }
    
    public synchronized void println(final double d) {
        this.print(d);
        this.write(10);
    }
    
    public synchronized void println(final boolean b) {
        this.print(b);
        this.write(10);
    }
    
    public synchronized void writeEscapeCode(final boolean unicode) {
        if (this._unicode) {
            this.write(62);
            this.write(62);
            this.write(32);
            this.write(33);
            this.write(64);
            this.write(unicode ? 85 : 65);
            this.write(64);
            this.write(33);
            this.write(35);
            this.write(10);
        }
        else {
            this.writeByte(62);
            this.writeByte(62);
            this.writeByte(32);
            this.writeByte(33);
            this.writeByte(64);
            this.writeByte(unicode ? 85 : 65);
            this.writeByte(64);
            this.writeByte(33);
            this.writeByte(35);
            this.writeByte(10);
        }
        this._unicode = unicode;
    }
}
