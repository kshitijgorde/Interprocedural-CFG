// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class UncompressInputStream extends FilterInputStream
{
    byte[] one;
    private static final int TBL_CLEAR = 256;
    private static final int TBL_FIRST = 257;
    private int[] tab_prefix;
    private byte[] tab_suffix;
    private int[] zeros;
    private byte[] stack;
    private boolean block_mode;
    private int n_bits;
    private int maxbits;
    private int maxmaxcode;
    private int maxcode;
    private int bitmask;
    private int oldcode;
    private byte finchar;
    private int stackp;
    private int free_ent;
    private byte[] data;
    private int bit_pos;
    private int end;
    private int got;
    private boolean eof;
    private static final int EXTRA = 64;
    private static final int LZW_MAGIC = 8093;
    private static final int MAX_BITS = 16;
    private static final int INIT_BITS = 9;
    private static final int HDR_MAXBITS = 31;
    private static final int HDR_EXTENDED = 32;
    private static final int HDR_FREE = 64;
    private static final int HDR_BLOCK_MODE = 128;
    private static final boolean debug = false;
    
    public UncompressInputStream(final InputStream inputStream) throws IOException {
        super(inputStream);
        this.one = new byte[1];
        this.zeros = new int[256];
        this.data = new byte[10000];
        this.bit_pos = 0;
        this.end = 0;
        this.got = 0;
        this.eof = false;
        this.parse_header();
    }
    
    public synchronized int read() throws IOException {
        if (super.in.read(this.one, 0, 1) == 1) {
            return this.one[0] & 0xFF;
        }
        return -1;
    }
    
    public synchronized int read(final byte[] array, int n, int n2) throws IOException {
        if (this.eof) {
            return -1;
        }
        final int n3 = n;
        final int[] tab_prefix = this.tab_prefix;
        final byte[] tab_suffix = this.tab_suffix;
        final byte[] stack = this.stack;
        int n_bits = this.n_bits;
        int maxcode = this.maxcode;
        final int maxmaxcode = this.maxmaxcode;
        int bitmask = this.bitmask;
        int oldcode = this.oldcode;
        byte finchar = this.finchar;
        int stackp = this.stackp;
        int free_ent = this.free_ent;
        final byte[] data = this.data;
        int i = this.bit_pos;
        final int n4 = stack.length - stackp;
        if (n4 > 0) {
            final int n5 = (n4 >= n2) ? n2 : n4;
            System.arraycopy(stack, stackp, array, n, n5);
            n += n5;
            n2 -= n5;
            stackp += n5;
        }
        if (n2 == 0) {
            this.stackp = stackp;
            return n - n3;
        }
    Label_0785:
        do {
            if (this.end < 64) {
                this.fill();
            }
            while (i < ((this.got > 0) ? (this.end - this.end % n_bits << 3) : ((this.end << 3) - (n_bits - 1)))) {
                if (free_ent > maxcode) {
                    final int n6 = n_bits << 3;
                    final int n7 = i - 1 + n6 - (i - 1 + n6) % n6;
                    maxcode = ((++n_bits == this.maxbits) ? maxmaxcode : ((1 << n_bits) - 1));
                    bitmask = (1 << n_bits) - 1;
                    i = this.resetbuf(n7);
                    continue Label_0785;
                }
                final int n8 = i >> 3;
                int j = ((data[n8] & 0xFF) | (data[n8 + 1] & 0xFF) << 8 | (data[n8 + 2] & 0xFF) << 16) >> (i & 0x7) & bitmask;
                i += n_bits;
                if (oldcode == -1) {
                    if (j >= 256) {
                        throw new IOException("corrupt input: " + j + " > 255");
                    }
                    finchar = (byte)(oldcode = j);
                    array[n++] = finchar;
                    --n2;
                }
                else {
                    if (j == 256 && this.block_mode) {
                        System.arraycopy(this.zeros, 0, tab_prefix, 0, this.zeros.length);
                        free_ent = 256;
                        final int n9 = n_bits << 3;
                        final int n10 = i - 1 + n9 - (i - 1 + n9) % n9;
                        n_bits = 9;
                        maxcode = (bitmask = (1 << n_bits) - 1);
                        i = this.resetbuf(n10);
                        continue Label_0785;
                    }
                    final int n11 = j;
                    int length = stack.length;
                    if (j >= free_ent) {
                        if (j > free_ent) {
                            throw new IOException("corrupt input: code=" + j + ", free_ent=" + free_ent);
                        }
                        stack[--length] = finchar;
                        j = oldcode;
                    }
                    while (j >= 256) {
                        stack[--length] = tab_suffix[j];
                        j = tab_prefix[j];
                    }
                    finchar = tab_suffix[j];
                    array[n++] = finchar;
                    --n2;
                    final int n12 = stack.length - length;
                    final int n13 = (n12 >= n2) ? n2 : n12;
                    System.arraycopy(stack, length, array, n, n13);
                    n += n13;
                    n2 -= n13;
                    stackp = length + n13;
                    if (free_ent < maxmaxcode) {
                        tab_prefix[free_ent] = oldcode;
                        tab_suffix[free_ent] = finchar;
                        ++free_ent;
                    }
                    oldcode = n11;
                    if (n2 == 0) {
                        this.n_bits = n_bits;
                        this.maxcode = maxcode;
                        this.bitmask = bitmask;
                        this.oldcode = oldcode;
                        this.finchar = finchar;
                        this.stackp = stackp;
                        this.free_ent = free_ent;
                        this.bit_pos = i;
                        return n - n3;
                    }
                    continue;
                }
            }
            i = this.resetbuf(i);
        } while (this.got > 0);
        this.n_bits = n_bits;
        this.maxcode = maxcode;
        this.bitmask = bitmask;
        this.oldcode = oldcode;
        this.finchar = finchar;
        this.stackp = stackp;
        this.free_ent = free_ent;
        this.bit_pos = i;
        this.eof = true;
        return n - n3;
    }
    
    private final int resetbuf(final int n) {
        final int n2 = n >> 3;
        System.arraycopy(this.data, n2, this.data, 0, this.end - n2);
        this.end -= n2;
        return 0;
    }
    
    private final void fill() throws IOException {
        this.got = super.in.read(this.data, this.end, this.data.length - 1 - this.end);
        if (this.got > 0) {
            this.end += this.got;
        }
    }
    
    public synchronized long skip(final long n) throws IOException {
        final int read = this.read(new byte[(int)n], 0, (int)n);
        if (read > 0) {
            return read;
        }
        return 0L;
    }
    
    public synchronized int available() throws IOException {
        if (this.eof) {
            return 0;
        }
        return super.in.available();
    }
    
    private void parse_header() throws IOException {
        final int read = super.in.read();
        if (read < 0) {
            throw new EOFException("Failed to read magic number");
        }
        final int n = (read & 0xFF) << 8;
        final int read2 = super.in.read();
        if (read2 < 0) {
            throw new EOFException("Failed to read magic number");
        }
        final int n2 = n + (read2 & 0xFF);
        if (n2 != 8093) {
            throw new IOException("Input not in compress format (read magic number 0x" + Integer.toHexString(n2) + ")");
        }
        final int read3 = super.in.read();
        if (read3 < 0) {
            throw new EOFException("Failed to read header");
        }
        this.block_mode = ((read3 & 0x80) > 0);
        this.maxbits = (read3 & 0x1F);
        if (this.maxbits > 16) {
            throw new IOException("Stream compressed with " + this.maxbits + " bits, but can only handle " + 16 + " bits");
        }
        if ((read3 & 0x20) > 0) {
            throw new IOException("Header extension bit set");
        }
        if ((read3 & 0x40) > 0) {
            throw new IOException("Header bit 6 set");
        }
        this.maxmaxcode = 1 << this.maxbits;
        this.n_bits = 9;
        this.maxcode = (1 << this.n_bits) - 1;
        this.bitmask = this.maxcode;
        this.oldcode = -1;
        this.finchar = 0;
        this.free_ent = (this.block_mode ? 257 : 256);
        this.tab_prefix = new int[1 << this.maxbits];
        this.tab_suffix = new byte[1 << this.maxbits];
        this.stack = new byte[1 << this.maxbits];
        this.stackp = this.stack.length;
        for (int i = 255; i >= 0; --i) {
            this.tab_suffix[i] = (byte)i;
        }
    }
    
    public static void main(final String[] array) throws Exception {
        if (array.length != 1) {
            System.err.println("Usage: UncompressInputStream <file>");
            System.exit(1);
        }
        final UncompressInputStream uncompressInputStream = new UncompressInputStream(new FileInputStream(array[0]));
        final byte[] array2 = new byte[100000];
        int n = 0;
        final long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            final int read = uncompressInputStream.read(array2);
            if (read < 0) {
                break;
            }
            System.out.write(array2, 0, read);
            n += read;
        }
        final long currentTimeMillis2 = System.currentTimeMillis();
        System.err.println("Decompressed " + n + " bytes");
        System.err.println("Time: " + (currentTimeMillis2 - currentTimeMillis) / 1000.0 + " seconds");
    }
}
