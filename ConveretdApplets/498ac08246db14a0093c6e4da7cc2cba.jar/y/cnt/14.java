// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;
import java.io.OutputStream;

class 14 extends OutputStream
{
    byte[] a;
    OutputStream b;
    int e;
    boolean f;
    5 g;
    
    14(final OutputStream b, final int n) {
        this.e = 7;
        this.a = new byte[n];
        this.b = b;
    }
    
    void Fa(final 5 g) {
        this.g = g;
    }
    
    void Ga(final byte[] array, final int n, final int n2) throws IOException {
        if (this.g != null) {
            this.g.g(array, n, n + n2);
        }
        this.b.write(array, n, n2);
        this.b.flush();
    }
    
    int Ha(final String s, int n) throws IOException {
        final int length = s.length();
        10.ja(length, this.a, n);
        n += 2;
        for (int i = 0; i < length; ++i) {
            this.a[n] = (byte)s.charAt(i);
            ++n;
        }
        return n;
    }
    
    void Ja() throws IOException {
        if (!this.f) {
            try {
                this.a[0] = 89;
                this.Ga(this.a, 0, 1);
            }
            catch (IOException ex) {
                this.f = true;
                throw ex;
            }
        }
    }
    
    void La(final String s) throws IOException {
        if (!this.f) {
            try {
                this.a[0] = 111;
                this.Ga(this.a, 0, this.Ha(s, 1));
            }
            catch (IOException ex) {
                this.f = true;
                throw ex;
            }
        }
    }
    
    void Ua(final int n) throws IOException {
        if (!this.f) {
            try {
                this.a[0] = 100;
                10.ka(n, this.a, 1);
                10.ja(this.e - 7, this.a, 5);
                this.Ga(this.a, 0, this.e);
            }
            catch (IOException ex) {
                this.f = true;
                this.e = 7;
                throw ex;
            }
        }
        this.e = 7;
    }
    
    void Ya() throws IOException {
        if (!this.f) {
            try {
                this.a[0] = 88;
                this.Ga(this.a, 0, 1);
            }
            catch (IOException ex) {
                this.f = true;
                throw ex;
            }
        }
    }
    
    public void write(final int n) {
        this.a[this.e] = (byte)n;
        ++this.e;
    }
    
    public void write(final byte[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.a, this.e, n2);
        this.e += n2;
    }
}
