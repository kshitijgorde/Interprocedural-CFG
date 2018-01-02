// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.io.OutputStream;

public final class ab extends OutputStream
{
    private byte[] a;
    OutputStream a;
    private int a;
    private boolean a;
    private al a;
    private static final DateFormat a;
    
    ab(final OutputStream a, final int n) {
        this.a = 9;
        this.a = null;
        this.a = new byte[n];
        this.a = a;
    }
    
    final void a(final al a) {
        this.a = a;
    }
    
    private void a(final byte[] array, final int n, final int n2) {
        if (this.a != null) {
            this.a.a(array, n, n + n2);
        }
        this.a.write(array, n, n2);
        this.a.flush();
    }
    
    final void a() {
        if (!this.a) {
            try {
                this.a[0] = 89;
                this.a(this.a, 0, 1);
            }
            catch (IOException ex) {
                this.a = true;
                throw ex;
            }
        }
    }
    
    public final void a(final String s) {
        if (!this.a) {
            try {
                this.a[0] = 111;
                int n = 1;
                final int length;
                y.a.a(length = s.length(), this.a, 1);
                n += 2;
                for (int i = 0; i < length; ++i) {
                    this.a[n] = (byte)s.charAt(i);
                    ++n;
                }
                this.a(this.a, 0, n);
            }
            catch (IOException ex) {
                this.a = true;
                throw ex;
            }
        }
    }
    
    public final void a(final int n) {
        if (!this.a) {
            try {
                this.a[2] = 100;
                y.a.a(n, this.a);
                y.a.a(this.a - 9, this.a, 7);
                this.a(this.a, 2, this.a - 2);
            }
            catch (IOException ex) {
                this.a = true;
                this.a = 9;
                throw ex;
            }
        }
        this.a = 9;
    }
    
    public final void b() {
        if (!this.a) {
            try {
                this.a[0] = 88;
                this.a(this.a, 0, 1);
            }
            catch (IOException ex) {
                this.a = true;
                throw ex;
            }
        }
    }
    
    public final void write(final int n) {
        this.a[this.a] = (byte)n;
        ++this.a;
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        try {
            System.arraycopy(array, n, this.a, this.a, n2);
            this.a += n2;
        }
        catch (Exception ex) {
            System.err.println(ab.a.format(new Date()) + " [ERROR] CPOutputStream.write() " + ex.getMessage());
            ex.printStackTrace();
            System.err.println("off=" + n);
            System.err.println("b=" + new String(array));
            System.err.println("p=" + this.a + "  len=" + n2);
            System.err.println("buf=" + new String(this.a));
            System.err.println("bufsize=" + this.a.length);
        }
    }
    
    static {
        a = DateFormat.getDateTimeInstance();
    }
}
