import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class i
{
    public InputStream a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public boolean g;
    
    public i(final InputStream a, final boolean g) {
        this.a = a;
        this.g = g;
        this.b = 0;
        this.c = 0;
        this.d = 0;
    }
    
    public int a() throws IOException {
        while (this.c < this.e) {
            if (this.g) {
                if (this.d == 0) {
                    this.d = this.a.read();
                    if (this.d == -1) {
                        throw new EOFException();
                    }
                    this.d &= 0xFF;
                }
                --this.d;
            }
            final int read = this.a.read();
            if (read == -1) {
                throw new EOFException();
            }
            if (this.g) {
                this.b = ((this.b & (1 << this.c) - 1) | (read & 0xFF) << this.c);
            }
            else {
                this.b = ((this.b << 8 & 0xFFFFFF00) | (read & 0xFF));
            }
            this.c += 8;
        }
        int n;
        if (this.g) {
            n = (this.b & this.f);
            this.b >>>= this.e;
        }
        else {
            n = (this.b >>> this.c - this.e & this.f);
        }
        this.c -= this.e;
        return n;
    }
    
    public void a(final int e) {
        this.e = e;
        this.f = (1 << this.e) - 1;
    }
}
