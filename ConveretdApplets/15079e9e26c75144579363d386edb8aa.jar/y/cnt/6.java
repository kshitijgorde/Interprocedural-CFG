// 
// Decompiled by Procyon v0.5.30
// 

package y.cnt;

import java.io.IOException;
import java.io.InputStream;

class 6 extends InputStream
{
    byte[] a;
    int b;
    int c;
    int d;
    InputStream e;
    5 f;
    
    6(final InputStream e, final int n) throws IOException {
        this.e = e;
        this.a = new byte[n];
    }
    
    void h(final int n) throws IOException {
        if (this.c == this.b) {
            this.d -= this.b;
            final boolean b = false;
            this.b = (b ? 1 : 0);
            this.c = (b ? 1 : 0);
        }
        if (this.b + n > this.a.length) {
            System.arraycopy(this.a, this.b, this.a, 0, this.c - this.b);
            this.d -= this.b;
            this.c -= this.b;
            this.b = 0;
        }
        final int n2 = this.b + n;
        if (n2 > this.a.length) {
            throw new IOException("CPInputStream Buffer full size=" + n + " p1=" + this.b + " pend=" + this.d);
        }
        while (this.c < n2) {
            final int read = this.e.read(this.a, this.c, this.a.length - this.c);
            if (read == -1) {
                throw new IOException("Illegal cp protocol");
            }
            if (this.f != null) {
                this.f.g(this.a, this.c, this.c + read);
            }
            this.c += read;
        }
    }
    
    public 5 i() throws IOException {
        final 5 5 = new 5(this.l());
        this.f = new 5(this.l());
        return 5;
    }
    
    public void j() throws IOException {
        this.h("YAHOO!".length());
        for (int i = 0; i < "YAHOO!".length(); ++i) {
            if (this.a[this.b + i] != "YAHOO!".charAt(i)) {
                throw new IOException("Illegal connection proxy header");
            }
        }
        this.b += "YAHOO!".length();
        this.d = this.b;
    }
    
    public int k() throws IOException {
        this.h(1);
        final byte b = this.a[this.b];
        ++this.b;
        this.d = this.b;
        return b;
    }
    
    public int l() throws IOException {
        this.h(4);
        final int ia = 10.ia(this.a, this.b);
        this.b += 4;
        return ia;
    }
    
    public String m() throws IOException {
        this.h(2);
        final int ga = 10.ga(this.a, this.b);
        this.b += 2;
        this.h(ga);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ga; ++i) {
            sb.append((char)this.a[this.b]);
            ++this.b;
        }
        return new String(sb);
    }
    
    public int o() throws IOException {
        this.h(2);
        final int ga = 10.ga(this.a, this.b);
        if (ga < 0) {
            throw new IOException("illegal packet length");
        }
        this.b += 2;
        this.d = this.b + ga;
        this.h(ga);
        return ga;
    }
    
    public int p() throws IOException {
        this.h(4);
        final int ia = 10.ia(this.a, this.b);
        if (ia < 0) {
            throw new IOException("illegal packet length");
        }
        this.b += 4;
        this.d = this.b + ia;
        this.h(ia);
        return ia;
    }
    
    public void q() {
        this.b = this.d;
    }
    
    public int read() throws IOException {
        if (this.d == this.b) {
            throw new IOException("Illegal CP Read pend=" + this.d + " p1=" + this.b);
        }
        final int n = this.a[this.b] & 0xFF;
        ++this.b;
        return n;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        if (n2 > this.d - this.b) {
            throw new IOException("Illegal CP Read");
        }
        System.arraycopy(this.a, this.b, array, n, n2);
        this.b += n2;
        return n2;
    }
}
