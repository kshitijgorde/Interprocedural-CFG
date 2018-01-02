import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class av extends bu implements ay
{
    public static int h3;
    public byte[] h2;
    public int h1;
    public int h0;
    public int h_;
    public ci hz;
    
    public av(final int h_, final ci hz) throws IOException {
        super(new au(av.h3));
        this.h_ = h_;
        this.hz = hz;
        if (hz != null) {
            final au au = (au)super.out;
            ca.mf().mx(au.ht(), 8);
            au.hs(8);
        }
        else {
            for (int i = 0; i < 8; ++i) {
                this.write(0);
            }
        }
        this.write(h_);
    }
    
    public final ay h0() throws IOException {
        return new av(this.h_, this.hz);
    }
    
    public final void h_(final InputStream inputStream) throws IOException {
        if (this.h_ != 23 && this.h_ != 16) {
            throw new IOException("Trying to read raw data into non-data PDU");
        }
        final au au = (au)super.out;
        this.h2 = au.ht();
        this.h1 = au.size() + 4;
        this.h0 = inputStream.read(this.h2, this.h1, av.h3 - this.h1);
        if (this.h0 == -1) {
            throw new IOException("EOF");
        }
        this.writeInt(this.h0);
        au.hs(this.h1 + this.h0);
    }
    
    public final void hz(final OutputStream outputStream) throws IOException {
        final au au = (au)super.out;
        final int size = au.size();
        final int n = (size + 4) % 8;
        final int n2 = (int)b.h(au.ht(), n, size - n);
        final int n3 = size + 4 - n;
        this.writeInt(n2);
        final byte[] ht = au.ht();
        if (this.hz != null) {
            this.hz.nb(ht, n, ht, n, n3);
        }
        final int n4 = n3 - (8 - n);
        outputStream.write(n4 >>> 24 & 0xFF);
        outputStream.write(n4 >>> 16 & 0xFF);
        outputStream.write(n4 >>> 8 & 0xFF);
        outputStream.write(n4 & 0xFF);
        outputStream.write(ht, n, n3);
        outputStream.flush();
    }
    
    public final byte[] hy() {
        return this.h2;
    }
    
    public final void hx(final byte[] array) {
    }
    
    public final int hw() {
        return this.h1;
    }
    
    public final int hv() {
        final byte[] h2 = this.h2;
        int n = this.h1 - 4;
        return ((h2[n++] + 256 & 0xFF) << 24) + ((h2[n++] + 256 & 0xFF) << 16) + ((h2[n++] + 256 & 0xFF) << 8) + (h2[n] + 256 & 0xFF);
    }
    
    public final void hu(final int n) {
        ((au)super.out).hs(n);
    }
    
    static {
        av.h3 = 8192;
    }
}
