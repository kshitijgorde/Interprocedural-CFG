// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public final class df extends FilterOutputStream
{
    private boolean q;
    private int q;
    private byte[] q;
    private int w;
    private int e;
    private boolean w;
    private byte[] w;
    private boolean e;
    private int r;
    private byte[] e;
    
    public df(final OutputStream outputStream, final int r) {
        super(outputStream);
        this.w = ((r & 0x8) != 0x0);
        this.q = ((r & 0x1) != 0x0);
        this.w = (this.q ? 3 : 4);
        this.q = new byte[this.w];
        this.q = 0;
        this.e = 0;
        this.e = false;
        this.w = new byte[4];
        this.r = r;
        this.e = ak.q(r);
    }
    
    public final void write(int q) {
        if (this.q) {
            this.q[this.q++] = (byte)q;
            if (this.q >= this.w) {
                this.out.write(ak.q(this.w, this.q, this.w, this.r));
                this.e += 4;
                if (this.w && this.e >= 76) {
                    this.out.write(10);
                    this.e = 0;
                }
                this.q = 0;
            }
        }
        else if (this.e[q & 0x7F] > -5) {
            this.q[this.q++] = (byte)q;
            if (this.q >= this.w) {
                q = ak.q(this.q, 0, this.w, 0, this.r);
                this.out.write(this.w, 0, q);
                this.q = 0;
            }
        }
        else if (this.e[q & 0x7F] != -5) {
            throw new IOException("Invalid character in Base64 data.");
        }
    }
    
    public final void write(final byte[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            this.write(array[n + i]);
        }
    }
    
    public final void close() {
        if (this.q > 0) {
            if (!this.q) {
                throw new IOException("Base64 input not properly padded.");
            }
            this.out.write(ak.q(this.w, this.q, this.q, this.r));
            this.q = 0;
        }
        super.close();
        this.q = null;
        this.out = null;
    }
}
