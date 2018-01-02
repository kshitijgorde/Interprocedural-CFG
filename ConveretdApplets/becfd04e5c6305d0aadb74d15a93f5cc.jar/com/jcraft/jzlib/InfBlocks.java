// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

final class InfBlocks
{
    private static final int MANY = 1440;
    private static final int[] inflate_mask;
    static final int[] border;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    private static final int TYPE = 0;
    private static final int LENS = 1;
    private static final int STORED = 2;
    private static final int TABLE = 3;
    private static final int BTREE = 4;
    private static final int DTREE = 5;
    private static final int CODES = 6;
    private static final int DRY = 7;
    private static final int DONE = 8;
    private static final int BAD = 9;
    int mode;
    int left;
    int table;
    int index;
    int[] blens;
    int[] bb;
    int[] tb;
    InfCodes codes;
    int last;
    int bitk;
    int bitb;
    int[] hufts;
    byte[] window;
    int end;
    int read;
    int write;
    Object checkfn;
    long check;
    
    int inflate_flush(final ZStream zStream, int n) {
        final int next_out_index = zStream.next_out_index;
        final int read = this.read;
        int avail_out = ((read <= this.write) ? this.write : this.end) - read;
        if (avail_out > zStream.avail_out) {
            avail_out = zStream.avail_out;
        }
        if (avail_out != 0 && n == -5) {
            n = 0;
        }
        zStream.avail_out -= avail_out;
        zStream.total_out += avail_out;
        if (this.checkfn != null) {
            final long adler32 = zStream._adler.adler32(this.check, this.window, read, avail_out);
            this.check = adler32;
            zStream.adler = adler32;
        }
        System.arraycopy(this.window, read, zStream.next_out, next_out_index, avail_out);
        int next_out_index2 = next_out_index + avail_out;
        int read2 = read + avail_out;
        if (read2 == this.end) {
            final int n2 = 0;
            if (this.write == this.end) {
                this.write = 0;
            }
            int avail_out2 = this.write - n2;
            if (avail_out2 > zStream.avail_out) {
                avail_out2 = zStream.avail_out;
            }
            if (avail_out2 != 0 && n == -5) {
                n = 0;
            }
            zStream.avail_out -= avail_out2;
            zStream.total_out += avail_out2;
            if (this.checkfn != null) {
                final long adler33 = zStream._adler.adler32(this.check, this.window, n2, avail_out2);
                this.check = adler33;
                zStream.adler = adler33;
            }
            System.arraycopy(this.window, n2, zStream.next_out, next_out_index2, avail_out2);
            next_out_index2 += avail_out2;
            read2 = n2 + avail_out2;
        }
        zStream.next_out_index = next_out_index2;
        this.read = read2;
        return n;
    }
    
    void reset(final ZStream zStream, final long[] array) {
        if (array != null) {
            array[0] = this.check;
        }
        if (this.mode == 4 || this.mode == 5) {
            this.blens = null;
        }
        if (this.mode == 6) {
            this.codes.free(zStream);
        }
        this.mode = 0;
        this.bitk = 0;
        this.bitb = 0;
        final boolean b = false;
        this.write = (b ? 1 : 0);
        this.read = (b ? 1 : 0);
        if (this.checkfn != null) {
            final long adler32 = zStream._adler.adler32(0L, null, 0, 0);
            this.check = adler32;
            zStream.adler = adler32;
        }
    }
    
    InfBlocks(final ZStream zStream, final Object checkfn, final int end) {
        this.bb = new int[1];
        this.tb = new int[1];
        this.hufts = new int[4320];
        this.window = new byte[end];
        this.end = end;
        this.checkfn = checkfn;
        this.mode = 0;
        this.reset(zStream, null);
    }
    
    void free(final ZStream zStream) {
        this.reset(zStream, null);
        this.window = null;
        this.hufts = null;
    }
    
    static {
        inflate_mask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        border = new int[] { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
    }
    
    void set_dictionary(final byte[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.window, 0, n2);
        this.write = n2;
        this.read = n2;
    }
    
    int sync_point() {
        if (this.mode == 1) {
            return 1;
        }
        return 0;
    }
    
    int proc(final ZStream zStream, int n) {
        int next_in_index = zStream.next_in_index;
        int avail_in = zStream.avail_in;
        int n2 = this.bitb;
        int i = this.bitk;
        int write = this.write;
        int n3 = (write < this.read) ? (this.read - write - 1) : (this.end - write);
        Label_2696: {
        Label_2576:
            while (true) {
                Label_2415: {
                    switch (this.mode) {
                        case 0: {
                            while (i < 3) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                i += 8;
                            }
                            final int n4 = n2 & 0x7;
                            this.last = (n4 & 0x1);
                            switch (n4 >>> 1) {
                                case 0: {
                                    final int n5 = n2 >>> 3;
                                    i -= 3;
                                    final int n6 = i & 0x7;
                                    n2 = n5 >>> n6;
                                    i -= n6;
                                    this.mode = 1;
                                    continue;
                                }
                                case 1: {
                                    final int[] array = { 0 };
                                    final int[] array2 = { 0 };
                                    final int[][] array3 = { null };
                                    final int[][] array4 = { null };
                                    InfTree.inflate_trees_fixed(array, array2, array3, array4, zStream);
                                    this.codes = new InfCodes(array[0], array2[0], array3[0], array4[0], zStream);
                                    n2 >>>= 3;
                                    i -= 3;
                                    this.mode = 6;
                                    continue;
                                }
                                case 2: {
                                    n2 >>>= 3;
                                    i -= 3;
                                    this.mode = 3;
                                    continue;
                                }
                                case 3: {
                                    final int bitb = n2 >>> 3;
                                    i -= 3;
                                    this.mode = 9;
                                    zStream.msg = "invalid block type";
                                    n = -3;
                                    this.bitb = bitb;
                                    this.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(zStream, n);
                                }
                            }
                            break;
                        }
                        case 1: {
                            while (i < 32) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                i += 8;
                            }
                            if ((~n2 >>> 16 & 0xFFFF) != (n2 & 0xFFFF)) {
                                this.mode = 9;
                                zStream.msg = "invalid stored block lengths";
                                n = -3;
                                this.bitb = n2;
                                this.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(zStream, n);
                            }
                            this.left = (n2 & 0xFFFF);
                            i = (n2 = 0);
                            this.mode = ((this.left != 0) ? 2 : ((this.last != 0) ? 7 : 0));
                            continue;
                        }
                        case 2: {
                            if (avail_in == 0) {
                                this.bitb = n2;
                                this.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(zStream, n);
                            }
                            if (n3 == 0) {
                                if (write == this.end && this.read != 0) {
                                    write = 0;
                                    n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                }
                                if (n3 == 0) {
                                    this.write = write;
                                    n = this.inflate_flush(zStream, n);
                                    write = this.write;
                                    n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                    if (write == this.end && this.read != 0) {
                                        write = 0;
                                        n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                    }
                                    if (n3 == 0) {
                                        this.bitb = n2;
                                        this.bitk = i;
                                        zStream.avail_in = avail_in;
                                        zStream.total_in += next_in_index - zStream.next_in_index;
                                        zStream.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(zStream, n);
                                    }
                                }
                            }
                            n = 0;
                            int left = this.left;
                            if (left > avail_in) {
                                left = avail_in;
                            }
                            if (left > n3) {
                                left = n3;
                            }
                            System.arraycopy(zStream.next_in, next_in_index, this.window, write, left);
                            next_in_index += left;
                            avail_in -= left;
                            write += left;
                            n3 -= left;
                            if ((this.left -= left) == 0) {
                                this.mode = ((this.last != 0) ? 7 : 0);
                                continue;
                            }
                            continue;
                        }
                        case 3: {
                            while (i < 14) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                i += 8;
                            }
                            final int n7 = this.table = (n2 & 0x3FFF);
                            if ((n7 & 0x1F) > 29 || (n7 >> 5 & 0x1F) > 29) {
                                this.mode = 9;
                                zStream.msg = "too many length or distance symbols";
                                n = -3;
                                this.bitb = n2;
                                this.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(zStream, n);
                            }
                            this.blens = new int[258 + (n7 & 0x1F) + (n7 >> 5 & 0x1F)];
                            n2 >>>= 14;
                            i -= 14;
                            this.index = 0;
                            this.mode = 4;
                        }
                        case 4: {
                            while (this.index < 4 + (this.table >>> 10)) {
                                while (i < 3) {
                                    if (avail_in == 0) {
                                        this.bitb = n2;
                                        this.bitk = i;
                                        zStream.avail_in = avail_in;
                                        zStream.total_in += next_in_index - zStream.next_in_index;
                                        zStream.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(zStream, n);
                                    }
                                    n = 0;
                                    --avail_in;
                                    n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                    i += 8;
                                }
                                this.blens[InfBlocks.border[this.index++]] = (n2 & 0x7);
                                n2 >>>= 3;
                                i -= 3;
                            }
                            while (this.index < 19) {
                                this.blens[InfBlocks.border[this.index++]] = 0;
                            }
                            this.bb[0] = 7;
                            final int inflate_trees_bits = InfTree.inflate_trees_bits(this.blens, this.bb, this.tb, this.hufts, zStream);
                            if (inflate_trees_bits != 0) {
                                n = inflate_trees_bits;
                                if (n == -3) {
                                    this.blens = null;
                                    this.mode = 9;
                                }
                                this.bitb = n2;
                                this.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(zStream, n);
                            }
                            this.index = 0;
                            this.mode = 5;
                        }
                        case 5: {
                            while (true) {
                                final int table = this.table;
                                if (this.index < 258 + (table & 0x1F) + (table >> 5 & 0x1F)) {
                                    int n8;
                                    for (n8 = this.bb[0]; i < n8; i += 8) {
                                        if (avail_in == 0) {
                                            this.bitb = n2;
                                            this.bitk = i;
                                            zStream.avail_in = avail_in;
                                            zStream.total_in += next_in_index - zStream.next_in_index;
                                            zStream.next_in_index = next_in_index;
                                            this.write = write;
                                            return this.inflate_flush(zStream, n);
                                        }
                                        n = 0;
                                        --avail_in;
                                        n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                    }
                                    if (this.tb[0] == -1) {}
                                    final int n9 = this.hufts[(this.tb[0] + (n2 & InfBlocks.inflate_mask[n8])) * 3 + 1];
                                    final int n10 = this.hufts[(this.tb[0] + (n2 & InfBlocks.inflate_mask[n9])) * 3 + 2];
                                    if (n10 < 16) {
                                        n2 >>>= n9;
                                        i -= n9;
                                        this.blens[this.index++] = n10;
                                    }
                                    else {
                                        final int n11 = (n10 == 18) ? 7 : (n10 - 14);
                                        final int n12 = (n10 == 18) ? 11 : 3;
                                        while (i < n9 + n11) {
                                            if (avail_in == 0) {
                                                this.bitb = n2;
                                                this.bitk = i;
                                                zStream.avail_in = avail_in;
                                                zStream.total_in += next_in_index - zStream.next_in_index;
                                                zStream.next_in_index = next_in_index;
                                                this.write = write;
                                                return this.inflate_flush(zStream, n);
                                            }
                                            n = 0;
                                            --avail_in;
                                            n2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                            i += 8;
                                        }
                                        final int n13 = n2 >>> n9;
                                        final int n14 = i - n9;
                                        int n15 = n12 + (n13 & InfBlocks.inflate_mask[n11]);
                                        n2 = n13 >>> n11;
                                        i = n14 - n11;
                                        int index = this.index;
                                        final int table2 = this.table;
                                        if (index + n15 > 258 + (table2 & 0x1F) + (table2 >> 5 & 0x1F) || (n10 == 16 && index < 1)) {
                                            this.blens = null;
                                            this.mode = 9;
                                            zStream.msg = "invalid bit length repeat";
                                            n = -3;
                                            this.bitb = n2;
                                            this.bitk = i;
                                            zStream.avail_in = avail_in;
                                            zStream.total_in += next_in_index - zStream.next_in_index;
                                            zStream.next_in_index = next_in_index;
                                            this.write = write;
                                            return this.inflate_flush(zStream, n);
                                        }
                                        final int n16 = (n10 == 16) ? this.blens[index - 1] : 0;
                                        do {
                                            this.blens[index++] = n16;
                                        } while (--n15 != 0);
                                        this.index = index;
                                    }
                                }
                                else {
                                    this.tb[0] = -1;
                                    final int[] array5 = { 0 };
                                    final int[] array6 = { 0 };
                                    final int[] array7 = { 0 };
                                    final int[] array8 = { 0 };
                                    array5[0] = 9;
                                    array6[0] = 6;
                                    final int table3 = this.table;
                                    final int inflate_trees_dynamic = InfTree.inflate_trees_dynamic(257 + (table3 & 0x1F), 1 + (table3 >> 5 & 0x1F), this.blens, array5, array6, array7, array8, this.hufts, zStream);
                                    if (inflate_trees_dynamic != 0) {
                                        if (inflate_trees_dynamic == -3) {
                                            this.blens = null;
                                            this.mode = 9;
                                        }
                                        n = inflate_trees_dynamic;
                                        this.bitb = n2;
                                        this.bitk = i;
                                        zStream.avail_in = avail_in;
                                        zStream.total_in += next_in_index - zStream.next_in_index;
                                        zStream.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(zStream, n);
                                    }
                                    this.codes = new InfCodes(array5[0], array6[0], this.hufts, array7[0], this.hufts, array8[0], zStream);
                                    this.blens = null;
                                    this.mode = 6;
                                    break Label_2415;
                                }
                            }
                            break;
                        }
                        case 6: {
                            this.bitb = n2;
                            this.bitk = i;
                            zStream.avail_in = avail_in;
                            zStream.total_in += next_in_index - zStream.next_in_index;
                            zStream.next_in_index = next_in_index;
                            this.write = write;
                            if ((n = this.codes.proc(this, zStream, n)) != 1) {
                                return this.inflate_flush(zStream, n);
                            }
                            n = 0;
                            this.codes.free(zStream);
                            next_in_index = zStream.next_in_index;
                            avail_in = zStream.avail_in;
                            n2 = this.bitb;
                            i = this.bitk;
                            write = this.write;
                            n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                            if (this.last == 0) {
                                this.mode = 0;
                                continue;
                            }
                            this.mode = 7;
                            break Label_2576;
                        }
                        case 7: {
                            break Label_2576;
                        }
                        case 8: {
                            break Label_2696;
                        }
                        case 9: {
                            n = -3;
                            this.bitb = n2;
                            this.bitk = i;
                            zStream.avail_in = avail_in;
                            zStream.total_in += next_in_index - zStream.next_in_index;
                            zStream.next_in_index = next_in_index;
                            this.write = write;
                            return this.inflate_flush(zStream, n);
                        }
                        default: {
                            n = -2;
                            this.bitb = n2;
                            this.bitk = i;
                            zStream.avail_in = avail_in;
                            zStream.total_in += next_in_index - zStream.next_in_index;
                            zStream.next_in_index = next_in_index;
                            this.write = write;
                            return this.inflate_flush(zStream, n);
                        }
                    }
                }
            }
            this.write = write;
            n = this.inflate_flush(zStream, n);
            write = this.write;
            final int n17 = (write < this.read) ? (this.read - write - 1) : (this.end - write);
            if (this.read != this.write) {
                this.bitb = n2;
                this.bitk = i;
                zStream.avail_in = avail_in;
                zStream.total_in += next_in_index - zStream.next_in_index;
                zStream.next_in_index = next_in_index;
                this.write = write;
                return this.inflate_flush(zStream, n);
            }
            this.mode = 8;
        }
        n = 1;
        this.bitb = n2;
        this.bitk = i;
        zStream.avail_in = avail_in;
        zStream.total_in += next_in_index - zStream.next_in_index;
        zStream.next_in_index = next_in_index;
        this.write = write;
        return this.inflate_flush(zStream, n);
    }
}
