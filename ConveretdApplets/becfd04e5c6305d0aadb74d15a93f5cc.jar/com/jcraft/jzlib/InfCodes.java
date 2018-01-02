// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

final class InfCodes
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
    
    InfCodes(final int n, final int n2, final int[] ltree, final int ltree_index, final int[] dtree, final int dtree_index, final ZStream zStream) {
        this.tree_index = 0;
        this.mode = 0;
        this.lbits = (byte)n;
        this.dbits = (byte)n2;
        this.ltree = ltree;
        this.ltree_index = ltree_index;
        this.dtree = dtree;
        this.dtree_index = dtree_index;
    }
    
    InfCodes(final int n, final int n2, final int[] ltree, final int[] dtree, final ZStream zStream) {
        this.tree_index = 0;
        this.mode = 0;
        this.lbits = (byte)n;
        this.dbits = (byte)n2;
        this.ltree = ltree;
        this.ltree_index = 0;
        this.dtree = dtree;
        this.dtree_index = 0;
    }
    
    void free(final ZStream zStream) {
    }
    
    int inflate_fast(final int n, final int n2, final int[] array, final int n3, final int[] array2, final int n4, final InfBlocks infBlocks, final ZStream zStream) {
        int next_in_index = zStream.next_in_index;
        int avail_in = zStream.avail_in;
        int bitb = infBlocks.bitb;
        int bitk = infBlocks.bitk;
        int write = infBlocks.write;
        int n5 = (write < infBlocks.read) ? (infBlocks.read - write - 1) : (infBlocks.end - write);
        final int n6 = InfCodes.inflate_mask[n];
        final int n7 = InfCodes.inflate_mask[n2];
        while (true) {
            if (bitk >= 20) {
                int n8 = bitb & n6;
                Label_1365: {
                    int n9;
                    if ((n9 = array[(n3 + n8) * 3]) == 0) {
                        bitb >>= array[(n3 + n8) * 3 + 1];
                        bitk -= array[(n3 + n8) * 3 + 1];
                        infBlocks.window[write++] = (byte)array[(n3 + n8) * 3 + 2];
                        --n5;
                    }
                    else {
                        do {
                            bitb >>= array[(n3 + n8) * 3 + 1];
                            bitk -= array[(n3 + n8) * 3 + 1];
                            if ((n9 & 0x10) != 0x0) {
                                final int n10 = n9 & 0xF;
                                int n11 = array[(n3 + n8) * 3 + 2] + (bitb & InfCodes.inflate_mask[n10]);
                                int bitb2 = bitb >> n10;
                                int i;
                                for (i = bitk - n10; i < 15; i += 8) {
                                    --avail_in;
                                    bitb2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                }
                                int n12 = bitb2 & n7;
                                int n13 = array2[(n4 + n12) * 3];
                                while (true) {
                                    bitb2 >>= array2[(n4 + n12) * 3 + 1];
                                    i -= array2[(n4 + n12) * 3 + 1];
                                    if ((n13 & 0x10) != 0x0) {
                                        int n14;
                                        for (n14 = (n13 & 0xF); i < n14; i += 8) {
                                            --avail_in;
                                            bitb2 |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                                        }
                                        final int n15 = array2[(n4 + n12) * 3 + 2] + (bitb2 & InfCodes.inflate_mask[n14]);
                                        bitb = bitb2 >> n14;
                                        bitk = i - n14;
                                        n5 -= n11;
                                        int j;
                                        if (write >= n15) {
                                            j = write - n15;
                                            if (write - j > 0 && 2 > write - j) {
                                                infBlocks.window[write++] = infBlocks.window[j++];
                                                --n11;
                                                infBlocks.window[write++] = infBlocks.window[j++];
                                                --n11;
                                            }
                                            else {
                                                System.arraycopy(infBlocks.window, j, infBlocks.window, write, 2);
                                                write += 2;
                                                j += 2;
                                                n11 -= 2;
                                            }
                                        }
                                        else {
                                            j = write - n15;
                                            do {
                                                j += infBlocks.end;
                                            } while (j < 0);
                                            int n16 = infBlocks.end - j;
                                            if (n11 > n16) {
                                                n11 -= n16;
                                                if (write - j > 0 && n16 > write - j) {
                                                    do {
                                                        infBlocks.window[write++] = infBlocks.window[j++];
                                                    } while (--n16 != 0);
                                                }
                                                else {
                                                    System.arraycopy(infBlocks.window, j, infBlocks.window, write, n16);
                                                    write += n16;
                                                }
                                                j = 0;
                                            }
                                        }
                                        if (write - j > 0 && n11 > write - j) {
                                            do {
                                                infBlocks.window[write++] = infBlocks.window[j++];
                                            } while (--n11 != 0);
                                            break Label_1365;
                                        }
                                        System.arraycopy(infBlocks.window, j, infBlocks.window, write, n11);
                                        write += n11;
                                        break Label_1365;
                                    }
                                    else {
                                        if ((n13 & 0x40) != 0x0) {
                                            zStream.msg = "invalid distance code";
                                            final int n17 = zStream.avail_in - avail_in;
                                            final int n18 = (i >> 3 < n17) ? (i >> 3) : n17;
                                            final int avail_in2 = avail_in + n18;
                                            final int next_in_index2 = next_in_index - n18;
                                            final int bitk2 = i - (n18 << 3);
                                            infBlocks.bitb = bitb2;
                                            infBlocks.bitk = bitk2;
                                            zStream.avail_in = avail_in2;
                                            zStream.total_in += next_in_index2 - zStream.next_in_index;
                                            zStream.next_in_index = next_in_index2;
                                            infBlocks.write = write;
                                            return -3;
                                        }
                                        n12 = n12 + array2[(n4 + n12) * 3 + 2] + (bitb2 & InfCodes.inflate_mask[n13]);
                                        n13 = array2[(n4 + n12) * 3];
                                    }
                                }
                            }
                            else if ((n9 & 0x40) == 0x0) {
                                n8 = n8 + array[(n3 + n8) * 3 + 2] + (bitb & InfCodes.inflate_mask[n9]);
                            }
                            else {
                                if ((n9 & 0x20) != 0x0) {
                                    final int n19 = zStream.avail_in - avail_in;
                                    final int n20 = (bitk >> 3 < n19) ? (bitk >> 3) : n19;
                                    final int avail_in3 = avail_in + n20;
                                    final int next_in_index3 = next_in_index - n20;
                                    final int bitk3 = bitk - (n20 << 3);
                                    infBlocks.bitb = bitb;
                                    infBlocks.bitk = bitk3;
                                    zStream.avail_in = avail_in3;
                                    zStream.total_in += next_in_index3 - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index3;
                                    infBlocks.write = write;
                                    return 1;
                                }
                                zStream.msg = "invalid literal/length code";
                                final int n21 = zStream.avail_in - avail_in;
                                final int n22 = (bitk >> 3 < n21) ? (bitk >> 3) : n21;
                                final int avail_in4 = avail_in + n22;
                                final int next_in_index4 = next_in_index - n22;
                                final int bitk4 = bitk - (n22 << 3);
                                infBlocks.bitb = bitb;
                                infBlocks.bitk = bitk4;
                                zStream.avail_in = avail_in4;
                                zStream.total_in += next_in_index4 - zStream.next_in_index;
                                zStream.next_in_index = next_in_index4;
                                infBlocks.write = write;
                                return -3;
                            }
                        } while ((n9 = array[(n3 + n8) * 3]) != 0);
                        bitb >>= array[(n3 + n8) * 3 + 1];
                        bitk -= array[(n3 + n8) * 3 + 1];
                        infBlocks.window[write++] = (byte)array[(n3 + n8) * 3 + 2];
                        --n5;
                    }
                }
                if (n5 < 258 || avail_in < 10) {
                    final int n23 = zStream.avail_in - avail_in;
                    final int n24 = (bitk >> 3 < n23) ? (bitk >> 3) : n23;
                    final int avail_in5 = avail_in + n24;
                    final int next_in_index5 = next_in_index - n24;
                    final int bitk5 = bitk - (n24 << 3);
                    infBlocks.bitb = bitb;
                    infBlocks.bitk = bitk5;
                    zStream.avail_in = avail_in5;
                    zStream.total_in += next_in_index5 - zStream.next_in_index;
                    zStream.next_in_index = next_in_index5;
                    infBlocks.write = write;
                    return 0;
                }
                continue;
            }
            else {
                --avail_in;
                bitb |= (zStream.next_in[next_in_index++] & 0xFF) << bitk;
                bitk += 8;
            }
        }
    }
    
    static {
        inflate_mask = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
    }
    
    int proc(final InfBlocks infBlocks, final ZStream zStream, int n) {
        int next_in_index = zStream.next_in_index;
        int avail_in = zStream.avail_in;
        int bitb = infBlocks.bitb;
        int i = infBlocks.bitk;
        int n2 = infBlocks.write;
        int n3 = (n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2);
        Label_2099: {
            Label_2042: {
            Label_1986:
                while (true) {
                    switch (this.mode) {
                        case 0: {
                            if (n3 >= 258 && avail_in >= 10) {
                                infBlocks.bitb = bitb;
                                infBlocks.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                infBlocks.write = n2;
                                n = this.inflate_fast(this.lbits, this.dbits, this.ltree, this.ltree_index, this.dtree, this.dtree_index, infBlocks, zStream);
                                next_in_index = zStream.next_in_index;
                                avail_in = zStream.avail_in;
                                bitb = infBlocks.bitb;
                                i = infBlocks.bitk;
                                n2 = infBlocks.write;
                                n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
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
                                    infBlocks.bitb = bitb;
                                    infBlocks.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    infBlocks.write = n2;
                                    return infBlocks.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                            }
                            final int n4 = (this.tree_index + (bitb & InfCodes.inflate_mask[need])) * 3;
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
                            zStream.msg = "invalid literal/length code";
                            n = -3;
                            infBlocks.bitb = bitb;
                            infBlocks.bitk = i;
                            zStream.avail_in = avail_in;
                            zStream.total_in += next_in_index - zStream.next_in_index;
                            zStream.next_in_index = next_in_index;
                            infBlocks.write = n2;
                            return infBlocks.inflate_flush(zStream, n);
                        }
                        case 2: {
                            int get;
                            for (get = this.get; i < get; i += 8) {
                                if (avail_in == 0) {
                                    infBlocks.bitb = bitb;
                                    infBlocks.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    infBlocks.write = n2;
                                    return infBlocks.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                            }
                            this.len += (bitb & InfCodes.inflate_mask[get]);
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
                                    infBlocks.bitb = bitb;
                                    infBlocks.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    infBlocks.write = n2;
                                    return infBlocks.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                            }
                            final int n5 = (this.tree_index + (bitb & InfCodes.inflate_mask[need3])) * 3;
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
                            zStream.msg = "invalid distance code";
                            n = -3;
                            infBlocks.bitb = bitb;
                            infBlocks.bitk = i;
                            zStream.avail_in = avail_in;
                            zStream.total_in += next_in_index - zStream.next_in_index;
                            zStream.next_in_index = next_in_index;
                            infBlocks.write = n2;
                            return infBlocks.inflate_flush(zStream, n);
                        }
                        case 4: {
                            int get2;
                            for (get2 = this.get; i < get2; i += 8) {
                                if (avail_in == 0) {
                                    infBlocks.bitb = bitb;
                                    infBlocks.bitk = i;
                                    zStream.avail_in = avail_in;
                                    zStream.total_in += next_in_index - zStream.next_in_index;
                                    zStream.next_in_index = next_in_index;
                                    infBlocks.write = n2;
                                    return infBlocks.inflate_flush(zStream, n);
                                }
                                n = 0;
                                --avail_in;
                                bitb |= (zStream.next_in[next_in_index++] & 0xFF) << i;
                            }
                            this.dist += (bitb & InfCodes.inflate_mask[get2]);
                            bitb >>= get2;
                            i -= get2;
                            this.mode = 5;
                        }
                        case 5: {
                            int j;
                            for (j = n2 - this.dist; j < 0; j += infBlocks.end) {}
                            while (this.len != 0) {
                                if (n3 == 0) {
                                    if (n2 == infBlocks.end && infBlocks.read != 0) {
                                        n2 = 0;
                                        n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                    }
                                    if (n3 == 0) {
                                        infBlocks.write = n2;
                                        n = infBlocks.inflate_flush(zStream, n);
                                        n2 = infBlocks.write;
                                        n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                        if (n2 == infBlocks.end && infBlocks.read != 0) {
                                            n2 = 0;
                                            n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                        }
                                        if (n3 == 0) {
                                            infBlocks.bitb = bitb;
                                            infBlocks.bitk = i;
                                            zStream.avail_in = avail_in;
                                            zStream.total_in += next_in_index - zStream.next_in_index;
                                            zStream.next_in_index = next_in_index;
                                            infBlocks.write = n2;
                                            return infBlocks.inflate_flush(zStream, n);
                                        }
                                    }
                                }
                                infBlocks.window[n2++] = infBlocks.window[j++];
                                --n3;
                                if (j == infBlocks.end) {
                                    j = 0;
                                }
                                --this.len;
                            }
                            this.mode = 0;
                            continue;
                        }
                        case 6: {
                            if (n3 == 0) {
                                if (n2 == infBlocks.end && infBlocks.read != 0) {
                                    n2 = 0;
                                    n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                }
                                if (n3 == 0) {
                                    infBlocks.write = n2;
                                    n = infBlocks.inflate_flush(zStream, n);
                                    n2 = infBlocks.write;
                                    n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                    if (n2 == infBlocks.end && infBlocks.read != 0) {
                                        n2 = 0;
                                        n3 = ((n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2));
                                    }
                                    if (n3 == 0) {
                                        infBlocks.bitb = bitb;
                                        infBlocks.bitk = i;
                                        zStream.avail_in = avail_in;
                                        zStream.total_in += next_in_index - zStream.next_in_index;
                                        zStream.next_in_index = next_in_index;
                                        infBlocks.write = n2;
                                        return infBlocks.inflate_flush(zStream, n);
                                    }
                                }
                            }
                            n = 0;
                            infBlocks.window[n2++] = (byte)this.lit;
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
                            infBlocks.write = n2;
                            n = infBlocks.inflate_flush(zStream, n);
                            n2 = infBlocks.write;
                            final int n6 = (n2 < infBlocks.read) ? (infBlocks.read - n2 - 1) : (infBlocks.end - n2);
                            if (infBlocks.read != infBlocks.write) {
                                infBlocks.bitb = bitb;
                                infBlocks.bitk = i;
                                zStream.avail_in = avail_in;
                                zStream.total_in += next_in_index - zStream.next_in_index;
                                zStream.next_in_index = next_in_index;
                                infBlocks.write = n2;
                                return infBlocks.inflate_flush(zStream, n);
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
                infBlocks.bitb = bitb;
                infBlocks.bitk = i;
                zStream.avail_in = avail_in;
                zStream.total_in += next_in_index - zStream.next_in_index;
                zStream.next_in_index = next_in_index;
                infBlocks.write = n2;
                return infBlocks.inflate_flush(zStream, n);
            }
            n = -3;
            infBlocks.bitb = bitb;
            infBlocks.bitk = i;
            zStream.avail_in = avail_in;
            zStream.total_in += next_in_index - zStream.next_in_index;
            zStream.next_in_index = next_in_index;
            infBlocks.write = n2;
            return infBlocks.inflate_flush(zStream, n);
        }
        n = -2;
        infBlocks.bitb = bitb;
        infBlocks.bitk = i;
        zStream.avail_in = avail_in;
        zStream.total_in += next_in_index - zStream.next_in_index;
        zStream.next_in_index = next_in_index;
        infBlocks.write = n2;
        return infBlocks.inflate_flush(zStream, n);
    }
}
