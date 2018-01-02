// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.io.IOException;
import java.io.InputStream;

public final class fj extends InputStream
{
    public byte[] a;
    public int a;
    public int b;
    public int c;
    InputStream a;
    al a;
    
    fj(final InputStream a, final int n) {
        this.a = null;
        this.a = a;
        this.a = new byte[n];
    }
    
    public final void a(int read) {
        if (this.b == this.a) {
            this.c -= this.a;
            final boolean b = false;
            this.a = (b ? 1 : 0);
            this.b = (b ? 1 : 0);
        }
        if (this.a + read > this.a.length) {
            System.arraycopy(this.a, this.a, this.a, 0, this.b - this.a);
            this.c -= this.a;
            this.b -= this.a;
            this.a = 0;
        }
        final int n;
        if ((n = this.a + read) > this.a.length) {
            throw new IOException("CPInputStream Buffer full size=" + read + " p1=" + this.a + " pend=" + this.c);
        }
        while (this.b < n) {
            if ((read = this.a.read(this.a, this.b, this.a.length - this.b)) == -1) {
                throw new IOException("Illegal cp protocol");
            }
            if (this.a != null) {
                this.a.a(this.a, this.b, this.b + read);
            }
            this.b += read;
        }
    }
    
    final void a() {
        this.a("YAHOO!".length());
        for (int i = 0; i < "YAHOO!".length(); ++i) {
            if (this.a[this.a + i] != "YAHOO!".charAt(i)) {
                throw new IOException("Illegal connection proxy header");
            }
        }
        this.a += "YAHOO!".length();
        this.c = this.a;
    }
    
    public final int a() {
        this.a(4);
        final int b = y.a.b(this.a, this.a);
        this.a += 4;
        return b;
    }
    
    public final String a() {
        this.a(2);
        final int a = y.a.a(this.a, this.a);
        this.a += 2;
        this.a(a);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < a; ++i) {
            sb.append((char)this.a[this.a]);
            ++this.a;
        }
        return new String(sb);
    }
    
    public final boolean a() {
        if (this.a == this.c) {
            return true;
        }
        this.a = this.c;
        return false;
    }
    
    public final int read() {
        if (this.c == this.a) {
            throw new IOException("Illegal CP Read pend=" + this.c + " p1=" + this.a);
        }
        final int n = this.a[this.a] & 0xFF;
        ++this.a;
        return n;
    }
    
    public final int read(final byte[] array, final int n, final int n2) {
        if (n2 > this.c - this.a) {
            throw new IOException("Illegal CP Read");
        }
        System.arraycopy(this.a, this.a, array, n, n2);
        this.a += n2;
        return n2;
    }
}
