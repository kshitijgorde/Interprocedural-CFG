// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

final class BitReserve
{
    private static final int BUFSIZE = 32768;
    private static final int BUFSIZE_MASK = 32767;
    private int offset;
    private int totbit;
    private int buf_byte_idx;
    private final int[] buf;
    private int buf_bit_idx;
    
    BitReserve() {
        this.buf = new int[32768];
        this.offset = 0;
        this.totbit = 0;
        this.buf_byte_idx = 0;
    }
    
    public int hsstell() {
        return this.totbit;
    }
    
    public int hgetbits(int n) {
        this.totbit += n;
        int n2 = 0;
        int buf_byte_idx = this.buf_byte_idx;
        if (buf_byte_idx + n < 32768) {
            while (n-- > 0) {
                n2 = (n2 << 1 | ((this.buf[buf_byte_idx++] != 0) ? 1 : 0));
            }
        }
        else {
            while (n-- > 0) {
                n2 = (n2 << 1 | ((this.buf[buf_byte_idx] != 0) ? 1 : 0));
                buf_byte_idx = (buf_byte_idx + 1 & 0x7FFF);
            }
        }
        this.buf_byte_idx = buf_byte_idx;
        return n2;
    }
    
    public int hget1bit() {
        ++this.totbit;
        final int n = this.buf[this.buf_byte_idx];
        this.buf_byte_idx = (this.buf_byte_idx + 1 & 0x7FFF);
        return n;
    }
    
    public void hputbuf(final int n) {
        int offset = this.offset;
        this.buf[offset++] = (n & 0x80);
        this.buf[offset++] = (n & 0x40);
        this.buf[offset++] = (n & 0x20);
        this.buf[offset++] = (n & 0x10);
        this.buf[offset++] = (n & 0x8);
        this.buf[offset++] = (n & 0x4);
        this.buf[offset++] = (n & 0x2);
        this.buf[offset++] = (n & 0x1);
        if (offset == 32768) {
            this.offset = 0;
        }
        else {
            this.offset = offset;
        }
    }
    
    public void rewindNbits(final int n) {
        this.totbit -= n;
        this.buf_byte_idx -= n;
        if (this.buf_byte_idx < 0) {
            this.buf_byte_idx += 32768;
        }
    }
    
    public void rewindNbytes(final int n) {
        final int n2 = n << 3;
        this.totbit -= n2;
        this.buf_byte_idx -= n2;
        if (this.buf_byte_idx < 0) {
            this.buf_byte_idx += 32768;
        }
    }
}
