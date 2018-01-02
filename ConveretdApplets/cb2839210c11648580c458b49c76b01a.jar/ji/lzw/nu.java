// 
// Decompiled by Procyon v0.5.30
// 

package ji.lzw;

import java.io.IOException;
import javax.imageio.stream.ImageOutputStream;

public class nu
{
    int a;
    int b;
    int c;
    int d;
    int e;
    short f;
    nv g;
    nw h;
    boolean i;
    
    public nu(final ImageOutputStream imageOutputStream, final int a, final boolean i) throws IOException {
        this.g = new nv(imageOutputStream, !i);
        this.a = a;
        this.i = i;
        this.b = 1 << this.a;
        this.c = this.b + 1;
        this.d = this.a + 1;
        this.e = (1 << this.d) - 1;
        if (this.i) {
            --this.e;
        }
        this.f = -1;
        (this.h = new nw()).a(this.a);
        this.g.a(this.b, this.d);
    }
    
    public void a(final byte[] array, final int n, final int n2) throws IOException {
        for (int n3 = n + n2, i = n; i < n3; ++i) {
            final byte b = array[i];
            final short b2;
            if ((b2 = this.h.b(this.f, b)) != -1) {
                this.f = b2;
            }
            else {
                this.g.a(this.f, this.d);
                if (this.h.a(this.f, b) > this.e) {
                    if (this.d == 12) {
                        this.g.a(this.b, this.d);
                        this.h.a(this.a);
                        this.d = this.a + 1;
                    }
                    else {
                        ++this.d;
                    }
                    this.e = (1 << this.d) - 1;
                    if (this.i) {
                        --this.e;
                    }
                }
                this.f = (short)(b & 0xFF);
            }
        }
    }
    
    public void a() throws IOException {
        if (this.f != -1) {
            this.g.a(this.f, this.d);
        }
        this.g.a(this.c, this.d);
        this.g.a();
    }
}
