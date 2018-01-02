// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

final class b
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
    c codes;
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
    t inftree;
    
    b(final s s, final Object checkfn, final int end) {
        this.bb = new int[1];
        this.tb = new int[1];
        this.codes = new c();
        this.inftree = new t();
        this.hufts = new int[4320];
        this.window = new byte[end];
        this.end = end;
        this.checkfn = checkfn;
        this.mode = 0;
        this.reset(s, null);
    }
    
    void free(final s s) {
        this.reset(s, null);
        this.window = null;
        this.hufts = null;
    }
    
    int inflate_flush(final s s, int n) {
        final int next_out_index = s.next_out_index;
        final int read = this.read;
        int avail_out = ((read <= this.write) ? this.write : this.end) - read;
        if (avail_out > s.avail_out) {
            avail_out = s.avail_out;
        }
        if (avail_out != 0 && n == -5) {
            n = 0;
        }
        s.avail_out -= avail_out;
        s.total_out += avail_out;
        if (this.checkfn != null) {
            final long a = s._adler.a(this.check, this.window, read, avail_out);
            this.check = a;
            s.adler = a;
        }
        System.arraycopy(this.window, read, s.next_out, next_out_index, avail_out);
        int next_out_index2 = next_out_index + avail_out;
        int read2 = read + avail_out;
        if (read2 == this.end) {
            final int n2 = 0;
            if (this.write == this.end) {
                this.write = 0;
            }
            int avail_out2 = this.write - n2;
            if (avail_out2 > s.avail_out) {
                avail_out2 = s.avail_out;
            }
            if (avail_out2 != 0 && n == -5) {
                n = 0;
            }
            s.avail_out -= avail_out2;
            s.total_out += avail_out2;
            if (this.checkfn != null) {
                final long a2 = s._adler.a(this.check, this.window, n2, avail_out2);
                this.check = a2;
                s.adler = a2;
            }
            System.arraycopy(this.window, n2, s.next_out, next_out_index2, avail_out2);
            next_out_index2 += avail_out2;
            read2 = n2 + avail_out2;
        }
        s.next_out_index = next_out_index2;
        this.read = read2;
        return n;
    }
    
    int proc(final s s, int n) {
        int next_in_index = s.next_in_index;
        int avail_in = s.avail_in;
        int n2 = this.bitb;
        int i = this.bitk;
        int write = this.write;
        int n3 = (write < this.read) ? (this.read - write - 1) : (this.end - write);
        Label_2757: {
        Label_2637:
            while (true) {
                Label_2476: {
                    switch (this.mode) {
                        case 0: {
                            while (i < 3) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
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
                                    t.inflate_trees_fixed(array, array2, array3, array4, s);
                                    this.codes.init(array[0], array2[0], array3[0], 0, array4[0], 0, s);
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
                                    s.msg = "invalid block type";
                                    n = -3;
                                    this.bitb = bitb;
                                    this.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(s, n);
                                }
                            }
                            continue;
                        }
                        case 1: {
                            while (i < 32) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                i += 8;
                            }
                            if ((~n2 >>> 16 & 0xFFFF) != (n2 & 0xFFFF)) {
                                this.mode = 9;
                                s.msg = "invalid stored block lengths";
                                n = -3;
                                this.bitb = n2;
                                this.bitk = i;
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(s, n);
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
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(s, n);
                            }
                            if (n3 == 0) {
                                if (write == this.end && this.read != 0) {
                                    write = 0;
                                    n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                }
                                if (n3 == 0) {
                                    this.write = write;
                                    n = this.inflate_flush(s, n);
                                    write = this.write;
                                    n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                    if (write == this.end && this.read != 0) {
                                        write = 0;
                                        n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                                    }
                                    if (n3 == 0) {
                                        this.bitb = n2;
                                        this.bitk = i;
                                        s.avail_in = avail_in;
                                        s.total_in += next_in_index - s.next_in_index;
                                        s.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(s, n);
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
                            System.arraycopy(s.next_in, next_in_index, this.window, write, left);
                            next_in_index += left;
                            avail_in -= left;
                            write += left;
                            n3 -= left;
                            if ((this.left -= left) != 0) {
                                continue;
                            }
                            this.mode = ((this.last != 0) ? 7 : 0);
                            continue;
                        }
                        case 3: {
                            while (i < 14) {
                                if (avail_in == 0) {
                                    this.bitb = n2;
                                    this.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    this.write = write;
                                    return this.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                i += 8;
                            }
                            final int n7 = this.table = (n2 & 0x3FFF);
                            if ((n7 & 0x1F) > 29 || (n7 >> 5 & 0x1F) > 29) {
                                this.mode = 9;
                                s.msg = "too many length or distance symbols";
                                n = -3;
                                this.bitb = n2;
                                this.bitk = i;
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(s, n);
                            }
                            final int n8 = 258 + (n7 & 0x1F) + (n7 >> 5 & 0x1F);
                            if (this.blens == null || this.blens.length < n8) {
                                this.blens = new int[n8];
                            }
                            else {
                                for (int j = 0; j < n8; ++j) {
                                    this.blens[j] = 0;
                                }
                            }
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
                                        s.avail_in = avail_in;
                                        s.total_in += next_in_index - s.next_in_index;
                                        s.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(s, n);
                                    }
                                    n = 0;
                                    --avail_in;
                                    n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                    i += 8;
                                }
                                this.blens[b.border[this.index++]] = (n2 & 0x7);
                                n2 >>>= 3;
                                i -= 3;
                            }
                            while (this.index < 19) {
                                this.blens[b.border[this.index++]] = 0;
                            }
                            this.bb[0] = 7;
                            final int inflate_trees_bits = this.inftree.inflate_trees_bits(this.blens, this.bb, this.tb, this.hufts, s);
                            if (inflate_trees_bits != 0) {
                                n = inflate_trees_bits;
                                if (n == -3) {
                                    this.blens = null;
                                    this.mode = 9;
                                }
                                this.bitb = n2;
                                this.bitk = i;
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                this.write = write;
                                return this.inflate_flush(s, n);
                            }
                            this.index = 0;
                            this.mode = 5;
                        }
                        case 5: {
                            while (true) {
                                final int table = this.table;
                                if (this.index >= 258 + (table & 0x1F) + (table >> 5 & 0x1F)) {
                                    this.tb[0] = -1;
                                    final int[] array5 = { 0 };
                                    final int[] array6 = { 0 };
                                    final int[] array7 = { 0 };
                                    final int[] array8 = { 0 };
                                    array5[0] = 9;
                                    array6[0] = 6;
                                    final int table2 = this.table;
                                    final int inflate_trees_dynamic = this.inftree.inflate_trees_dynamic(257 + (table2 & 0x1F), 1 + (table2 >> 5 & 0x1F), this.blens, array5, array6, array7, array8, this.hufts, s);
                                    if (inflate_trees_dynamic != 0) {
                                        if (inflate_trees_dynamic == -3) {
                                            this.blens = null;
                                            this.mode = 9;
                                        }
                                        n = inflate_trees_dynamic;
                                        this.bitb = n2;
                                        this.bitk = i;
                                        s.avail_in = avail_in;
                                        s.total_in += next_in_index - s.next_in_index;
                                        s.next_in_index = next_in_index;
                                        this.write = write;
                                        return this.inflate_flush(s, n);
                                    }
                                    this.codes.init(array5[0], array6[0], this.hufts, array7[0], this.hufts, array8[0], s);
                                    this.mode = 6;
                                    break Label_2476;
                                }
                                else {
                                    int n9;
                                    for (n9 = this.bb[0]; i < n9; i += 8) {
                                        if (avail_in == 0) {
                                            this.bitb = n2;
                                            this.bitk = i;
                                            s.avail_in = avail_in;
                                            s.total_in += next_in_index - s.next_in_index;
                                            s.next_in_index = next_in_index;
                                            this.write = write;
                                            return this.inflate_flush(s, n);
                                        }
                                        n = 0;
                                        --avail_in;
                                        n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                    }
                                    if (this.tb[0] == -1) {}
                                    final int n10 = this.hufts[(this.tb[0] + (n2 & b.inflate_mask[n9])) * 3 + 1];
                                    final int n11 = this.hufts[(this.tb[0] + (n2 & b.inflate_mask[n10])) * 3 + 2];
                                    if (n11 < 16) {
                                        n2 >>>= n10;
                                        i -= n10;
                                        this.blens[this.index++] = n11;
                                    }
                                    else {
                                        final int n12 = (n11 == 18) ? 7 : (n11 - 14);
                                        final int n13 = (n11 == 18) ? 11 : 3;
                                        while (i < n10 + n12) {
                                            if (avail_in == 0) {
                                                this.bitb = n2;
                                                this.bitk = i;
                                                s.avail_in = avail_in;
                                                s.total_in += next_in_index - s.next_in_index;
                                                s.next_in_index = next_in_index;
                                                this.write = write;
                                                return this.inflate_flush(s, n);
                                            }
                                            n = 0;
                                            --avail_in;
                                            n2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                            i += 8;
                                        }
                                        final int n14 = n2 >>> n10;
                                        final int n15 = i - n10;
                                        int n16 = n13 + (n14 & b.inflate_mask[n12]);
                                        n2 = n14 >>> n12;
                                        i = n15 - n12;
                                        int index = this.index;
                                        final int table3 = this.table;
                                        if (index + n16 > 258 + (table3 & 0x1F) + (table3 >> 5 & 0x1F) || (n11 == 16 && index < 1)) {
                                            this.blens = null;
                                            this.mode = 9;
                                            s.msg = "invalid bit length repeat";
                                            n = -3;
                                            this.bitb = n2;
                                            this.bitk = i;
                                            s.avail_in = avail_in;
                                            s.total_in += next_in_index - s.next_in_index;
                                            s.next_in_index = next_in_index;
                                            this.write = write;
                                            return this.inflate_flush(s, n);
                                        }
                                        final int n17 = (n11 == 16) ? this.blens[index - 1] : 0;
                                        do {
                                            this.blens[index++] = n17;
                                        } while (--n16 != 0);
                                        this.index = index;
                                    }
                                }
                            }
                            break;
                        }
                        case 6: {
                            this.bitb = n2;
                            this.bitk = i;
                            s.avail_in = avail_in;
                            s.total_in += next_in_index - s.next_in_index;
                            s.next_in_index = next_in_index;
                            this.write = write;
                            if ((n = this.codes.proc(this, s, n)) != 1) {
                                return this.inflate_flush(s, n);
                            }
                            n = 0;
                            this.codes.free(s);
                            next_in_index = s.next_in_index;
                            avail_in = s.avail_in;
                            n2 = this.bitb;
                            i = this.bitk;
                            write = this.write;
                            n3 = ((write < this.read) ? (this.read - write - 1) : (this.end - write));
                            if (this.last == 0) {
                                this.mode = 0;
                                continue;
                            }
                            this.mode = 7;
                            break Label_2637;
                        }
                        case 7: {
                            break Label_2637;
                        }
                        case 8: {
                            break Label_2757;
                        }
                        case 9: {
                            n = -3;
                            this.bitb = n2;
                            this.bitk = i;
                            s.avail_in = avail_in;
                            s.total_in += next_in_index - s.next_in_index;
                            s.next_in_index = next_in_index;
                            this.write = write;
                            return this.inflate_flush(s, n);
                        }
                        default: {
                            n = -2;
                            this.bitb = n2;
                            this.bitk = i;
                            s.avail_in = avail_in;
                            s.total_in += next_in_index - s.next_in_index;
                            s.next_in_index = next_in_index;
                            this.write = write;
                            return this.inflate_flush(s, n);
                        }
                    }
                }
            }
            this.write = write;
            n = this.inflate_flush(s, n);
            write = this.write;
            final int n18 = (write < this.read) ? (this.read - write - 1) : (this.end - write);
            if (this.read != this.write) {
                this.bitb = n2;
                this.bitk = i;
                s.avail_in = avail_in;
                s.total_in += next_in_index - s.next_in_index;
                s.next_in_index = next_in_index;
                this.write = write;
                return this.inflate_flush(s, n);
            }
            this.mode = 8;
        }
        n = 1;
        this.bitb = n2;
        this.bitk = i;
        s.avail_in = avail_in;
        s.total_in += next_in_index - s.next_in_index;
        s.next_in_index = next_in_index;
        this.write = write;
        return this.inflate_flush(s, n);
    }
    
    void reset(final s s, final long[] array) {
        if (array != null) {
            array[0] = this.check;
        }
        if (this.mode == 4 || this.mode == 5) {}
        if (this.mode == 6) {
            this.codes.free(s);
        }
        this.mode = 0;
        this.bitk = 0;
        this.bitb = 0;
        final boolean b = false;
        this.write = (b ? 1 : 0);
        this.read = (b ? 1 : 0);
        if (this.checkfn != null) {
            final long a = s._adler.a(0L, null, 0, 0);
            this.check = a;
            s.adler = a;
        }
    }
    
    void set_dictionary(final byte[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.window, 0, n2);
        this.write = n2;
        this.read = n2;
    }
    
    int sync_point() {
        return (this.mode == 1) ? 1 : 0;
    }
    
    static {
        inflate_mask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        border = new int[] { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
    }
}
