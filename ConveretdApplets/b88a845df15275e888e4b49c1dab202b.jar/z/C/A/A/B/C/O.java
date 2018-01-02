// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import java.io.IOException;
import java.io.Reader;

public final class O
{
    static final int G = 2048;
    private Reader F;
    private int H;
    boolean B;
    int E;
    int A;
    int D;
    char[] C;
    
    O() {
        this.D = 0;
    }
    
    public O(final Reader f, final int h) {
        this.F = f;
        this.H = h;
        this.C = new char[h];
        final boolean a = false;
        this.D = (a ? 1 : 0);
        this.E = (a ? 1 : 0);
        this.A = (a ? 1 : 0);
        this.B = false;
    }
    
    public O(final Reader reader) {
        this(reader, 2048);
    }
    
    int A(final int n) throws IOException {
        if (this.B) {
            return this.E;
        }
        final int n2 = this.E - n;
        final char[] c = new char[n2 + this.H];
        final int read = this.F.read(c, n2, this.H);
        if (read > 0) {
            this.A += n;
            this.E = n2 + read;
            System.arraycopy(this.C, n, c, 0, n2);
            this.C = c;
            return n2;
        }
        this.B = true;
        if (read == 0) {
            throw new IOException("read from input stream returned 0 bytes.");
        }
        return this.E;
    }
    
    boolean A() throws IOException {
        this.A += this.E;
        this.E = this.F.read(this.C);
        this.B = (this.E == -1);
        return !this.B;
    }
    
    public boolean B() {
        return this.B;
    }
}
