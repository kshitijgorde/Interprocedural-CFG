import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ax extends bv implements ay
{
    public int h_;
    public int h5;
    public byte[] h4;
    public ci hz;
    
    public ax(final int h_, final ci hz) {
        super(null);
        this.h_ = h_;
        this.hz = hz;
    }
    
    public final boolean h4() throws IOException {
        final int n = this.h5 + 8 & 0xFFFFFFF8;
        this.skip(n - 4);
        final int int1 = this.readInt();
        this.reset();
        return (int)b.h(this.h4, 0, n - 4) == int1;
    }
    
    public final ay h0() {
        return new ax(this.h_, this.hz);
    }
    
    public final void h_(final InputStream inputStream) throws IOException {
        final bv bv = new bv(inputStream);
        final int int1 = bv.readInt();
        final int n = int1 + 8 & 0xFFFFFFF8;
        if (n > 256000) {
            throw new IOException("Corrupt incoming packet, too large");
        }
        final byte[] h4 = new byte[n];
        bv.readFully(h4);
        if (this.hz != null) {
            this.hz.na(h4, 0, h4, 0, n);
        }
        super.in = new aw(h4);
        this.h4 = h4;
        this.h5 = int1;
        if (!this.h4()) {
            throw new IOException("Invalid checksum in packet");
        }
        this.skip(8 - int1 % 8);
        final byte byte1 = this.readByte();
        if (byte1 == 36) {
            ca.mc("MSG_DEBUG: " + super.jw());
            this.h_(inputStream);
        }
        else if (byte1 == 32) {
            ca.mb(this);
            this.h_(inputStream);
        }
        else if (this.h_ != -1 && this.h_ != byte1) {
            if (byte1 == 1) {
                throw new IOException("Server disconnected: " + super.jw());
            }
            throw new IOException("Invalid type: " + byte1 + " (expected: " + this.h_ + ")");
        }
        else {
            this.h_ = byte1;
        }
    }
    
    public final void hz(final OutputStream outputStream) throws IOException {
        if (this.h_ != 23 && this.h_ != 17 && this.h_ != 18) {
            throw new IOException("Trying to write raw data from non-data PDU");
        }
        outputStream.write(this.h4, ((aw)super.in).h3(), this.readInt());
        outputStream.flush();
    }
    
    public final byte[] hy() {
        return this.h4;
    }
    
    public final void hx(final byte[] array) {
        final aw aw = (aw)super.in;
        this.h4 = new byte[array.length + 4];
        aw.h2(0);
        final int length = array.length;
        int n = 0;
        this.h4[n++] = (byte)(length >>> 24 & 0xFF);
        this.h4[n++] = (byte)(length >>> 16 & 0xFF);
        this.h4[n++] = (byte)(length >>> 8 & 0xFF);
        this.h4[n++] = (byte)(length & 0xFF);
        System.arraycopy(array, 0, this.h4, n, array.length);
        aw.h1(this.h4);
    }
    
    public final int hw() {
        return ((aw)super.in).h3() + 4;
    }
    
    public final int hv() {
        int h3 = ((aw)super.in).h3();
        return ((this.h4[h3++] + 256 & 0xFF) << 24) + ((this.h4[h3++] + 256 & 0xFF) << 16) + ((this.h4[h3++] + 256 & 0xFF) << 8) + (this.h4[h3] + 256 & 0xFF);
    }
    
    public final void hu(final int n) {
        final aw aw = (aw)super.in;
        final int hv = this.hv();
        if (n >= hv) {
            return;
        }
        int n2 = aw.h3() + (hv - n);
        aw.h2(n2);
        this.h4[n2++] = (byte)(n >>> 24 & 0xFF);
        this.h4[n2++] = (byte)(n >>> 16 & 0xFF);
        this.h4[n2++] = (byte)(n >>> 8 & 0xFF);
        this.h4[n2++] = (byte)(n & 0xFF);
    }
}
