// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.d;
import java.io.OutputStream;

public class a8 extends OutputStream
{
    ac a;
    Object b;
    long c;
    
    public a8(final ac a, final Object b) {
        this.a = null;
        this.b = null;
        this.c = 0L;
        this.a = a;
        this.b = b;
    }
    
    public final void write(final int n) {
        try {
            this.a.a((byte)n);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void flush() {
        try {
            this.a.y();
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        try {
            this.a.b(array, n, n2);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void write(final byte[] array) {
        try {
            this.a.b(array);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void close() {
        try {
            this.a.a(this.b);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
        }
    }
}
