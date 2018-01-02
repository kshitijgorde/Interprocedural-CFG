// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.d;
import java.io.InputStream;

public class a5 extends InputStream
{
    ac a;
    Object b;
    long c;
    
    public a5(final ac a, final Object b) {
        this.a = null;
        this.b = null;
        this.c = 0L;
        this.a = a;
        this.b = b;
    }
    
    public final int available() {
        try {
            return (int)this.a.v();
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            return 0;
        }
    }
    
    public final int read() {
        try {
            return this.a.j();
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            return 0;
        }
    }
    
    public final int read(final byte[] array) {
        try {
            return this.a.a(array);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            return 0;
        }
    }
    
    public final int read(final byte[] array, final int n, final int n2) {
        try {
            return this.a.a(array, n, n2);
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            return 0;
        }
    }
    
    public final long skip(final long n) {
        try {
            this.a.a(this.a.r() + n);
            return n;
        }
        catch (Exception ex) {
            if (d.cy()) {
                ex.printStackTrace();
            }
            return 0L;
        }
    }
    
    public final boolean markSupported() {
        return true;
    }
    
    public final void reset() {
        try {
            this.a.a(this.c);
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
    
    public final void a() {
        try {
            this.b = null;
            this.a.ab();
            this.a = null;
        }
        catch (Exception ex) {}
    }
}
