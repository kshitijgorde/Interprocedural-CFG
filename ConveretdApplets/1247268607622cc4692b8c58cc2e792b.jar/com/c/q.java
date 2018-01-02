// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

final class q
{
    private static final int MAX_BITS = 15;
    private static final int BL_CODES = 19;
    private static final int D_CODES = 30;
    private static final int LITERALS = 256;
    private static final int LENGTH_CODES = 29;
    private static final int L_CODES = 286;
    private static final int HEAP_SIZE = 573;
    static final int MAX_BL_BITS = 7;
    static final int END_BLOCK = 256;
    static final int REP_3_6 = 16;
    static final int REPZ_3_10 = 17;
    static final int REPZ_11_138 = 18;
    static final int[] extra_lbits;
    static final int[] extra_dbits;
    static final int[] extra_blbits;
    static final byte[] bl_order;
    static final int Buf_size = 16;
    static final int DIST_CODE_LEN = 512;
    static final byte[] _dist_code;
    static final byte[] _length_code;
    static final int[] base_length;
    static final int[] base_dist;
    short[] dyn_tree;
    int max_code;
    v stat_desc;
    
    static int bi_reverse(int n, int n2) {
        int n3 = 0;
        do {
            final int n4 = n3 | (n & 0x1);
            n >>>= 1;
            n3 = n4 << 1;
        } while (--n2 > 0);
        return n3 >>> 1;
    }
    
    static int d_code(final int n) {
        return (n < 256) ? q._dist_code[n] : q._dist_code[256 + (n >>> 7)];
    }
    
    static void gen_codes(final short[] array, final int n, final short[] array2) {
        final short[] array3 = new short[16];
        short n2 = 0;
        for (int i = 1; i <= 15; ++i) {
            n2 = (array3[i] = (short)(n2 + array2[i - 1] << 1));
        }
        for (int j = 0; j <= n; ++j) {
            final short n3 = array[j * 2 + 1];
            if (n3 != 0) {
                final int n4 = j * 2;
                final short[] array4 = array3;
                final short n5 = n3;
                final short n6 = array4[n5];
                array4[n5] = (short)(n6 + 1);
                array[n4] = (short)bi_reverse(n6, n3);
            }
        }
    }
    
    void build_tree(final d d) {
        final short[] dyn_tree = this.dyn_tree;
        final short[] static_tree = this.stat_desc.static_tree;
        final int elems = this.stat_desc.elems;
        int max_code = -1;
        d.heap_len = 0;
        d.heap_max = 573;
        for (int i = 0; i < elems; ++i) {
            if (dyn_tree[i * 2] != 0) {
                max_code = (d.heap[++d.heap_len] = i);
                d.depth[i] = 0;
            }
            else {
                dyn_tree[i * 2 + 1] = 0;
            }
        }
        while (d.heap_len < 2) {
            final int[] heap = d.heap;
            final int n = ++d.heap_len;
            final int n2 = (max_code < 2) ? (++max_code) : 0;
            heap[n] = n2;
            final int n3 = n2;
            dyn_tree[n3 * 2] = 1;
            d.depth[n3] = 0;
            --d.opt_len;
            if (static_tree != null) {
                d.static_len -= static_tree[n3 * 2 + 1];
            }
        }
        this.max_code = max_code;
        for (int j = d.heap_len / 2; j >= 1; --j) {
            d.pqdownheap(dyn_tree, j);
        }
        int n4 = elems;
        do {
            final int n5 = d.heap[1];
            d.heap[1] = d.heap[d.heap_len--];
            d.pqdownheap(dyn_tree, 1);
            final int n6 = d.heap[1];
            d.heap[--d.heap_max] = n5;
            d.heap[--d.heap_max] = n6;
            dyn_tree[n4 * 2] = (short)(dyn_tree[n5 * 2] + dyn_tree[n6 * 2]);
            d.depth[n4] = (byte)(Math.max(d.depth[n5], d.depth[n6]) + 1);
            dyn_tree[n5 * 2 + 1] = (dyn_tree[n6 * 2 + 1] = (short)n4);
            d.heap[1] = n4++;
            d.pqdownheap(dyn_tree, 1);
        } while (d.heap_len >= 2);
        d.heap[--d.heap_max] = d.heap[1];
        this.gen_bitlen(d);
        gen_codes(dyn_tree, max_code, d.bl_count);
    }
    
    void gen_bitlen(final d d) {
        final short[] dyn_tree = this.dyn_tree;
        final short[] static_tree = this.stat_desc.static_tree;
        final int[] extra_bits = this.stat_desc.extra_bits;
        final int extra_base = this.stat_desc.extra_base;
        final int max_length = this.stat_desc.max_length;
        int i = 0;
        for (int j = 0; j <= 15; ++j) {
            d.bl_count[j] = 0;
        }
        dyn_tree[d.heap[d.heap_max] * 2 + 1] = 0;
        int k;
        for (k = d.heap_max + 1; k < 573; ++k) {
            final int n = d.heap[k];
            short n2 = (short)(dyn_tree[dyn_tree[n * 2 + 1] * 2 + 1] + 1);
            if (n2 > max_length) {
                n2 = (short)max_length;
                ++i;
            }
            dyn_tree[n * 2 + 1] = n2;
            if (n <= this.max_code) {
                final short[] bl_count = d.bl_count;
                final short n3 = n2;
                ++bl_count[n3];
                int n4 = 0;
                if (n >= extra_base) {
                    n4 = extra_bits[n - extra_base];
                }
                final short n5 = dyn_tree[n * 2];
                d.opt_len += n5 * (n2 + n4);
                if (static_tree != null) {
                    d.static_len += n5 * (static_tree[n * 2 + 1] + n4);
                }
            }
        }
        if (i == 0) {
            return;
        }
        do {
            int n6;
            for (n6 = max_length - 1; d.bl_count[n6] == 0; --n6) {}
            final short[] bl_count2 = d.bl_count;
            final int n7 = n6;
            --bl_count2[n7];
            final short[] bl_count3 = d.bl_count;
            final int n8 = n6 + 1;
            bl_count3[n8] += 2;
            final short[] bl_count4 = d.bl_count;
            final short n9 = (short)max_length;
            --bl_count4[n9];
            i -= 2;
        } while (i > 0);
        for (short n10 = (short)max_length; n10 != 0; --n10) {
            int l = d.bl_count[n10];
            while (l != 0) {
                final int n11 = d.heap[--k];
                if (n11 > this.max_code) {
                    continue;
                }
                if (dyn_tree[n11 * 2 + 1] != n10) {
                    d.opt_len += (n10 - dyn_tree[n11 * 2 + 1]) * dyn_tree[n11 * 2];
                    dyn_tree[n11 * 2 + 1] = n10;
                }
                --l;
            }
        }
    }
    
    static {
        extra_lbits = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0 };
        extra_dbits = new int[] { 0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13 };
        extra_blbits = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 7 };
        bl_order = new byte[] { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
        _dist_code = new byte[] { 0, 1, 2, 3, 4, 4, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 16, 17, 18, 18, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29 };
        _length_code = new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28 };
        base_length = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 0 };
        base_dist = new int[] { 0, 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 3072, 4096, 6144, 8192, 12288, 16384, 24576 };
    }
}
