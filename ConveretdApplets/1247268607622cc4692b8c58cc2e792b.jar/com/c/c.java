// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

final class c
{
    private static final int[] inflate_mask;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    private static final int START = 0;
    private static final int LEN = 1;
    private static final int LENEXT = 2;
    private static final int DIST = 3;
    private static final int DISTEXT = 4;
    private static final int COPY = 5;
    private static final int LIT = 6;
    private static final int WASH = 7;
    private static final int END = 8;
    private static final int BADCODE = 9;
    int mode;
    int len;
    int[] tree;
    int tree_index;
    int need;
    int lit;
    int get;
    int dist;
    byte lbits;
    byte dbits;
    int[] ltree;
    int ltree_index;
    int[] dtree;
    int dtree_index;
    
    c() {
        this.tree_index = 0;
    }
    
    void free(final s s) {
    }
    
    int inflate_fast(final int n, final int n2, final int[] array, final int n3, final int[] array2, final int n4, final b b, final s s) {
        int next_in_index = s.next_in_index;
        int avail_in = s.avail_in;
        int bitb = b.bitb;
        int bitk = b.bitk;
        int write = b.write;
        int n5 = (write < b.read) ? (b.read - write - 1) : (b.end - write);
        final int n6 = c.inflate_mask[n];
        final int n7 = c.inflate_mask[n2];
        while (true) {
            if (bitk < 20) {
                --avail_in;
                bitb |= (s.next_in[next_in_index++] & 0xFF) << bitk;
                bitk += 8;
            }
            else {
                int n8 = bitb & n6;
                int n9 = (n3 + n8) * 3;
                Label_1308: {
                    int n10;
                    if ((n10 = array[n9]) == 0) {
                        bitb >>= array[n9 + 1];
                        bitk -= array[n9 + 1];
                        b.window[write++] = (byte)array[n9 + 2];
                        --n5;
                    }
                    else {
                        do {
                            bitb >>= array[n9 + 1];
                            bitk -= array[n9 + 1];
                            if ((n10 & 0x10) != 0x0) {
                                final int n11 = n10 & 0xF;
                                int n12 = array[n9 + 2] + (bitb & c.inflate_mask[n11]);
                                int bitb2 = bitb >> n11;
                                int i;
                                for (i = bitk - n11; i < 15; i += 8) {
                                    --avail_in;
                                    bitb2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                }
                                int n13 = bitb2 & n7;
                                int n14 = (n4 + n13) * 3;
                                int n15 = array2[n14];
                                while (true) {
                                    bitb2 >>= array2[n14 + 1];
                                    i -= array2[n14 + 1];
                                    if ((n15 & 0x10) != 0x0) {
                                        int n16;
                                        for (n16 = (n15 & 0xF); i < n16; i += 8) {
                                            --avail_in;
                                            bitb2 |= (s.next_in[next_in_index++] & 0xFF) << i;
                                        }
                                        final int n17 = array2[n14 + 2] + (bitb2 & c.inflate_mask[n16]);
                                        bitb = bitb2 >> n16;
                                        bitk = i - n16;
                                        n5 -= n12;
                                        int j;
                                        if (write >= n17) {
                                            j = write - n17;
                                            if (write - j > 0 && 2 > write - j) {
                                                b.window[write++] = b.window[j++];
                                                b.window[write++] = b.window[j++];
                                                n12 -= 2;
                                            }
                                            else {
                                                System.arraycopy(b.window, j, b.window, write, 2);
                                                write += 2;
                                                j += 2;
                                                n12 -= 2;
                                            }
                                        }
                                        else {
                                            j = write - n17;
                                            do {
                                                j += b.end;
                                            } while (j < 0);
                                            int n18 = b.end - j;
                                            if (n12 > n18) {
                                                n12 -= n18;
                                                if (write - j > 0 && n18 > write - j) {
                                                    do {
                                                        b.window[write++] = b.window[j++];
                                                    } while (--n18 != 0);
                                                }
                                                else {
                                                    System.arraycopy(b.window, j, b.window, write, n18);
                                                    write += n18;
                                                }
                                                j = 0;
                                            }
                                        }
                                        if (write - j > 0 && n12 > write - j) {
                                            do {
                                                b.window[write++] = b.window[j++];
                                            } while (--n12 != 0);
                                            break Label_1308;
                                        }
                                        System.arraycopy(b.window, j, b.window, write, n12);
                                        write += n12;
                                        break Label_1308;
                                    }
                                    else {
                                        if ((n15 & 0x40) != 0x0) {
                                            s.msg = "invalid distance code";
                                            final int n19 = s.avail_in - avail_in;
                                            final int n20 = (i >> 3 < n19) ? (i >> 3) : n19;
                                            final int avail_in2 = avail_in + n20;
                                            final int next_in_index2 = next_in_index - n20;
                                            final int bitk2 = i - (n20 << 3);
                                            b.bitb = bitb2;
                                            b.bitk = bitk2;
                                            s.avail_in = avail_in2;
                                            s.total_in += next_in_index2 - s.next_in_index;
                                            s.next_in_index = next_in_index2;
                                            b.write = write;
                                            return -3;
                                        }
                                        n13 = n13 + array2[n14 + 2] + (bitb2 & c.inflate_mask[n15]);
                                        n14 = (n4 + n13) * 3;
                                        n15 = array2[n14];
                                    }
                                }
                            }
                            else if ((n10 & 0x40) == 0x0) {
                                n8 = n8 + array[n9 + 2] + (bitb & c.inflate_mask[n10]);
                                n9 = (n3 + n8) * 3;
                            }
                            else {
                                if ((n10 & 0x20) != 0x0) {
                                    final int n21 = s.avail_in - avail_in;
                                    final int n22 = (bitk >> 3 < n21) ? (bitk >> 3) : n21;
                                    final int avail_in3 = avail_in + n22;
                                    final int next_in_index3 = next_in_index - n22;
                                    final int bitk3 = bitk - (n22 << 3);
                                    b.bitb = bitb;
                                    b.bitk = bitk3;
                                    s.avail_in = avail_in3;
                                    s.total_in += next_in_index3 - s.next_in_index;
                                    s.next_in_index = next_in_index3;
                                    b.write = write;
                                    return 1;
                                }
                                s.msg = "invalid literal/length code";
                                final int n23 = s.avail_in - avail_in;
                                final int n24 = (bitk >> 3 < n23) ? (bitk >> 3) : n23;
                                final int avail_in4 = avail_in + n24;
                                final int next_in_index4 = next_in_index - n24;
                                final int bitk4 = bitk - (n24 << 3);
                                b.bitb = bitb;
                                b.bitk = bitk4;
                                s.avail_in = avail_in4;
                                s.total_in += next_in_index4 - s.next_in_index;
                                s.next_in_index = next_in_index4;
                                b.write = write;
                                return -3;
                            }
                        } while ((n10 = array[n9]) != 0);
                        bitb >>= array[n9 + 1];
                        bitk -= array[n9 + 1];
                        b.window[write++] = (byte)array[n9 + 2];
                        --n5;
                    }
                }
                if (n5 < 258 || avail_in < 10) {
                    final int n25 = s.avail_in - avail_in;
                    final int n26 = (bitk >> 3 < n25) ? (bitk >> 3) : n25;
                    final int avail_in5 = avail_in + n26;
                    final int next_in_index5 = next_in_index - n26;
                    final int bitk5 = bitk - (n26 << 3);
                    b.bitb = bitb;
                    b.bitk = bitk5;
                    s.avail_in = avail_in5;
                    s.total_in += next_in_index5 - s.next_in_index;
                    s.next_in_index = next_in_index5;
                    b.write = write;
                    return 0;
                }
                continue;
            }
        }
    }
    
    void init(final int n, final int n2, final int[] ltree, final int ltree_index, final int[] dtree, final int dtree_index, final s s) {
        this.mode = 0;
        this.lbits = (byte)n;
        this.dbits = (byte)n2;
        this.ltree = ltree;
        this.ltree_index = ltree_index;
        this.dtree = dtree;
        this.dtree_index = dtree_index;
        this.tree = null;
    }
    
    int proc(final b b, final s s, int n) {
        int next_in_index = s.next_in_index;
        int avail_in = s.avail_in;
        int bitb = b.bitb;
        int i = b.bitk;
        int n2 = b.write;
        int n3 = (n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2);
        Label_2099: {
            Label_2042: {
            Label_1986:
                while (true) {
                    switch (this.mode) {
                        case 0: {
                            if (n3 >= 258 && avail_in >= 10) {
                                b.bitb = bitb;
                                b.bitk = i;
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                b.write = n2;
                                n = this.inflate_fast(this.lbits, this.dbits, this.ltree, this.ltree_index, this.dtree, this.dtree_index, b, s);
                                next_in_index = s.next_in_index;
                                avail_in = s.avail_in;
                                bitb = b.bitb;
                                i = b.bitk;
                                n2 = b.write;
                                n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                if (n != 0) {
                                    this.mode = ((n == 1) ? 7 : 9);
                                    continue;
                                }
                            }
                            this.need = this.lbits;
                            this.tree = this.ltree;
                            this.tree_index = this.ltree_index;
                            this.mode = 1;
                        }
                        case 1: {
                            int need;
                            for (need = this.need; i < need; i += 8) {
                                if (avail_in == 0) {
                                    b.bitb = bitb;
                                    b.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    b.write = n2;
                                    return b.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (s.next_in[next_in_index++] & 0xFF) << i;
                            }
                            final int n4 = (this.tree_index + (bitb & c.inflate_mask[need])) * 3;
                            bitb >>>= this.tree[n4 + 1];
                            i -= this.tree[n4 + 1];
                            final int need2 = this.tree[n4];
                            if (need2 == 0) {
                                this.lit = this.tree[n4 + 2];
                                this.mode = 6;
                                continue;
                            }
                            if ((need2 & 0x10) != 0x0) {
                                this.get = (need2 & 0xF);
                                this.len = this.tree[n4 + 2];
                                this.mode = 2;
                                continue;
                            }
                            if ((need2 & 0x40) == 0x0) {
                                this.need = need2;
                                this.tree_index = n4 / 3 + this.tree[n4 + 2];
                                continue;
                            }
                            if ((need2 & 0x20) != 0x0) {
                                this.mode = 7;
                                continue;
                            }
                            this.mode = 9;
                            s.msg = "invalid literal/length code";
                            n = -3;
                            b.bitb = bitb;
                            b.bitk = i;
                            s.avail_in = avail_in;
                            s.total_in += next_in_index - s.next_in_index;
                            s.next_in_index = next_in_index;
                            b.write = n2;
                            return b.inflate_flush(s, n);
                        }
                        case 2: {
                            int get;
                            for (get = this.get; i < get; i += 8) {
                                if (avail_in == 0) {
                                    b.bitb = bitb;
                                    b.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    b.write = n2;
                                    return b.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (s.next_in[next_in_index++] & 0xFF) << i;
                            }
                            this.len += (bitb & c.inflate_mask[get]);
                            bitb >>= get;
                            i -= get;
                            this.need = this.dbits;
                            this.tree = this.dtree;
                            this.tree_index = this.dtree_index;
                            this.mode = 3;
                        }
                        case 3: {
                            int need3;
                            for (need3 = this.need; i < need3; i += 8) {
                                if (avail_in == 0) {
                                    b.bitb = bitb;
                                    b.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    b.write = n2;
                                    return b.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (s.next_in[next_in_index++] & 0xFF) << i;
                            }
                            final int n5 = (this.tree_index + (bitb & c.inflate_mask[need3])) * 3;
                            bitb >>= this.tree[n5 + 1];
                            i -= this.tree[n5 + 1];
                            final int need4 = this.tree[n5];
                            if ((need4 & 0x10) != 0x0) {
                                this.get = (need4 & 0xF);
                                this.dist = this.tree[n5 + 2];
                                this.mode = 4;
                                continue;
                            }
                            if ((need4 & 0x40) == 0x0) {
                                this.need = need4;
                                this.tree_index = n5 / 3 + this.tree[n5 + 2];
                                continue;
                            }
                            this.mode = 9;
                            s.msg = "invalid distance code";
                            n = -3;
                            b.bitb = bitb;
                            b.bitk = i;
                            s.avail_in = avail_in;
                            s.total_in += next_in_index - s.next_in_index;
                            s.next_in_index = next_in_index;
                            b.write = n2;
                            return b.inflate_flush(s, n);
                        }
                        case 4: {
                            int get2;
                            for (get2 = this.get; i < get2; i += 8) {
                                if (avail_in == 0) {
                                    b.bitb = bitb;
                                    b.bitk = i;
                                    s.avail_in = avail_in;
                                    s.total_in += next_in_index - s.next_in_index;
                                    s.next_in_index = next_in_index;
                                    b.write = n2;
                                    return b.inflate_flush(s, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (s.next_in[next_in_index++] & 0xFF) << i;
                            }
                            this.dist += (bitb & c.inflate_mask[get2]);
                            bitb >>= get2;
                            i -= get2;
                            this.mode = 5;
                        }
                        case 5: {
                            int j;
                            for (j = n2 - this.dist; j < 0; j += b.end) {}
                            while (this.len != 0) {
                                if (n3 == 0) {
                                    if (n2 == b.end && b.read != 0) {
                                        n2 = 0;
                                        n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                    }
                                    if (n3 == 0) {
                                        b.write = n2;
                                        n = b.inflate_flush(s, n);
                                        n2 = b.write;
                                        n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                        if (n2 == b.end && b.read != 0) {
                                            n2 = 0;
                                            n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                        }
                                        if (n3 == 0) {
                                            b.bitb = bitb;
                                            b.bitk = i;
                                            s.avail_in = avail_in;
                                            s.total_in += next_in_index - s.next_in_index;
                                            s.next_in_index = next_in_index;
                                            b.write = n2;
                                            return b.inflate_flush(s, n);
                                        }
                                    }
                                }
                                b.window[n2++] = b.window[j++];
                                --n3;
                                if (j == b.end) {
                                    j = 0;
                                }
                                --this.len;
                            }
                            this.mode = 0;
                            continue;
                        }
                        case 6: {
                            if (n3 == 0) {
                                if (n2 == b.end && b.read != 0) {
                                    n2 = 0;
                                    n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                }
                                if (n3 == 0) {
                                    b.write = n2;
                                    n = b.inflate_flush(s, n);
                                    n2 = b.write;
                                    n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                    if (n2 == b.end && b.read != 0) {
                                        n2 = 0;
                                        n3 = ((n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2));
                                    }
                                    if (n3 == 0) {
                                        b.bitb = bitb;
                                        b.bitk = i;
                                        s.avail_in = avail_in;
                                        s.total_in += next_in_index - s.next_in_index;
                                        s.next_in_index = next_in_index;
                                        b.write = n2;
                                        return b.inflate_flush(s, n);
                                    }
                                }
                            }
                            n = 0;
                            b.window[n2++] = (byte)this.lit;
                            --n3;
                            this.mode = 0;
                            continue;
                        }
                        case 7: {
                            if (i > 7) {
                                i -= 8;
                                ++avail_in;
                                --next_in_index;
                            }
                            b.write = n2;
                            n = b.inflate_flush(s, n);
                            n2 = b.write;
                            final int n6 = (n2 < b.read) ? (b.read - n2 - 1) : (b.end - n2);
                            if (b.read != b.write) {
                                b.bitb = bitb;
                                b.bitk = i;
                                s.avail_in = avail_in;
                                s.total_in += next_in_index - s.next_in_index;
                                s.next_in_index = next_in_index;
                                b.write = n2;
                                return b.inflate_flush(s, n);
                            }
                            this.mode = 8;
                            break Label_1986;
                        }
                        case 8: {
                            break Label_1986;
                        }
                        case 9: {
                            break Label_2042;
                        }
                        default: {
                            break Label_2099;
                        }
                    }
                }
                n = 1;
                b.bitb = bitb;
                b.bitk = i;
                s.avail_in = avail_in;
                s.total_in += next_in_index - s.next_in_index;
                s.next_in_index = next_in_index;
                b.write = n2;
                return b.inflate_flush(s, n);
            }
            n = -3;
            b.bitb = bitb;
            b.bitk = i;
            s.avail_in = avail_in;
            s.total_in += next_in_index - s.next_in_index;
            s.next_in_index = next_in_index;
            b.write = n2;
            return b.inflate_flush(s, n);
        }
        n = -2;
        b.bitb = bitb;
        b.bitk = i;
        s.avail_in = avail_in;
        s.total_in += next_in_index - s.next_in_index;
        s.next_in_index = next_in_index;
        b.write = n2;
        return b.inflate_flush(s, n);
    }
    
    static {
        inflate_mask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
    }
}
