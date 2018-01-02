// 
// Decompiled by Procyon v0.5.30
// 

package ji.lzw;

import java.io.IOException;
import javax.imageio.stream.ImageOutputStream;

public class nv
{
    ImageOutputStream a;
    byte[] b;
    int c;
    int d;
    boolean e;
    
    public nv(final ImageOutputStream a, final boolean e) {
        this.e = false;
        this.a = a;
        this.e = e;
        this.b = new byte[256];
        this.c = 0;
        this.d = 8;
    }
    
    public void a() throws IOException {
        final int n = this.c + ((this.d != 8) ? 1 : 0);
        if (n > 0) {
            if (this.e) {
                this.a.write(n);
            }
            this.a.write(this.b, 0, n);
            this.b[0] = 0;
            this.c = 0;
            this.d = 8;
        }
    }
    
    public void a(int n, int i) throws IOException {
        int n2 = 0;
        final int n3 = 255;
        do {
            if ((this.c == 254 && this.d == 0) || this.c > 254) {
                if (this.e) {
                    this.a.write(n3);
                }
                this.a.write(this.b, 0, n3);
                this.b[0] = 0;
                this.c = 0;
                this.d = 8;
            }
            if (i <= this.d) {
                if (this.e) {
                    final byte[] b = this.b;
                    final int c = this.c;
                    b[c] |= (byte)((n & (1 << i) - 1) << 8 - this.d);
                    n2 += i;
                    this.d -= i;
                    i = 0;
                }
                else {
                    final byte[] b2 = this.b;
                    final int c2 = this.c;
                    b2[c2] |= (byte)((n & (1 << i) - 1) << this.d - i);
                    n2 += i;
                    this.d -= i;
                    i = 0;
                }
            }
            else if (this.e) {
                final byte[] b3 = this.b;
                final int c3 = this.c;
                b3[c3] |= (byte)((n & (1 << this.d) - 1) << 8 - this.d);
                n2 += this.d;
                n >>= this.d;
                i -= this.d;
                this.b[++this.c] = 0;
                this.d = 8;
            }
            else {
                final int n4 = n >>> i - this.d & (1 << this.d) - 1;
                final byte[] b4 = this.b;
                final int c4 = this.c;
                b4[c4] |= (byte)n4;
                i -= this.d;
                n2 += this.d;
                this.b[++this.c] = 0;
                this.d = 8;
            }
        } while (i != 0);
    }
}
