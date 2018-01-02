// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

public final class d
{
    private static final int MAX_MEM_LEVEL = 9;
    private static final int Z_DEFAULT_COMPRESSION = -1;
    private static final int MAX_WBITS = 15;
    private static final int DEF_MEM_LEVEL = 8;
    private static final int STORED = 0;
    private static final int FAST = 1;
    private static final int SLOW = 2;
    private static final Config[] config_table;
    private static final String[] z_errmsg;
    private static final int NeedMore = 0;
    private static final int BlockDone = 1;
    private static final int FinishStarted = 2;
    private static final int FinishDone = 3;
    private static final int PRESET_DICT = 32;
    private static final int Z_FILTERED = 1;
    private static final int Z_HUFFMAN_ONLY = 2;
    private static final int Z_DEFAULT_STRATEGY = 0;
    private static final int Z_NO_FLUSH = 0;
    private static final int Z_PARTIAL_FLUSH = 1;
    private static final int Z_SYNC_FLUSH = 2;
    private static final int Z_FULL_FLUSH = 3;
    private static final int Z_FINISH = 4;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    private static final int INIT_STATE = 42;
    private static final int BUSY_STATE = 113;
    private static final int FINISH_STATE = 666;
    private static final int Z_DEFLATED = 8;
    private static final int STORED_BLOCK = 0;
    private static final int STATIC_TREES = 1;
    private static final int DYN_TREES = 2;
    private static final int Z_BINARY = 0;
    private static final int Z_ASCII = 1;
    private static final int Z_UNKNOWN = 2;
    private static final int Buf_size = 16;
    private static final int REP_3_6 = 16;
    private static final int REPZ_3_10 = 17;
    private static final int REPZ_11_138 = 18;
    private static final int MIN_MATCH = 3;
    private static final int MAX_MATCH = 258;
    private static final int MIN_LOOKAHEAD = 262;
    private static final int MAX_BITS = 15;
    private static final int D_CODES = 30;
    private static final int BL_CODES = 19;
    private static final int LENGTH_CODES = 29;
    private static final int LITERALS = 256;
    private static final int L_CODES = 286;
    private static final int HEAP_SIZE = 573;
    private static final int END_BLOCK = 256;
    s strm;
    int status;
    byte[] pending_buf;
    int pending_buf_size;
    int pending_out;
    int pending;
    int noheader;
    byte data_type;
    byte method;
    int last_flush;
    int w_size;
    int w_bits;
    int w_mask;
    byte[] window;
    int window_size;
    short[] prev;
    short[] head;
    int ins_h;
    int hash_size;
    int hash_bits;
    int hash_mask;
    int hash_shift;
    int block_start;
    int match_length;
    int prev_match;
    int match_available;
    int strstart;
    int match_start;
    int lookahead;
    int prev_length;
    int max_chain_length;
    int max_lazy_match;
    int level;
    int strategy;
    int good_match;
    int nice_match;
    short[] dyn_ltree;
    short[] dyn_dtree;
    short[] bl_tree;
    q l_desc;
    q d_desc;
    q bl_desc;
    short[] bl_count;
    int[] heap;
    int heap_len;
    int heap_max;
    byte[] depth;
    int l_buf;
    int lit_bufsize;
    int last_lit;
    int d_buf;
    int opt_len;
    int static_len;
    int matches;
    int last_eob_len;
    short bi_buf;
    int bi_valid;
    
    static boolean smaller(final short[] array, final int n, final int n2, final byte[] array2) {
        final short n3 = array[n * 2];
        final short n4 = array[n2 * 2];
        return n3 < n4 || (n3 == n4 && array2[n] <= array2[n2]);
    }
    
    d() {
        this.l_desc = new q();
        this.d_desc = new q();
        this.bl_desc = new q();
        this.bl_count = new short[16];
        this.heap = new int[573];
        this.depth = new byte[573];
        this.dyn_ltree = new short[1146];
        this.dyn_dtree = new short[122];
        this.bl_tree = new short[78];
    }
    
    void _tr_align() {
        this.send_bits(2, 3);
        this.send_code(256, v.static_ltree);
        this.bi_flush();
        if (1 + this.last_eob_len + 10 - this.bi_valid < 9) {
            this.send_bits(2, 3);
            this.send_code(256, v.static_ltree);
            this.bi_flush();
        }
        this.last_eob_len = 7;
    }
    
    void _tr_flush_block(final int n, final int n2, final boolean b) {
        int build_bl_tree = 0;
        int n3;
        int n4;
        if (this.level > 0) {
            if (this.data_type == 2) {
                this.set_data_type();
            }
            this.l_desc.build_tree(this);
            this.d_desc.build_tree(this);
            build_bl_tree = this.build_bl_tree();
            n3 = this.opt_len + 3 + 7 >>> 3;
            n4 = this.static_len + 3 + 7 >>> 3;
            if (n4 <= n3) {
                n3 = n4;
            }
        }
        else {
            n4 = (n3 = n2 + 5);
        }
        if (n2 + 4 <= n3 && n != -1) {
            this._tr_stored_block(n, n2, b);
        }
        else if (n4 == n3) {
            this.send_bits(2 + (b ? 1 : 0), 3);
            this.compress_block(v.static_ltree, v.static_dtree);
        }
        else {
            this.send_bits(4 + (b ? 1 : 0), 3);
            this.send_all_trees(this.l_desc.max_code + 1, this.d_desc.max_code + 1, build_bl_tree + 1);
            this.compress_block(this.dyn_ltree, this.dyn_dtree);
        }
        this.init_block();
        if (b) {
            this.bi_windup();
        }
    }
    
    void _tr_stored_block(final int n, final int n2, final boolean b) {
        this.send_bits(0 + (b ? 1 : 0), 3);
        this.copy_block(n, n2, true);
    }
    
    boolean _tr_tally(int n, final int n2) {
        this.pending_buf[this.d_buf + this.last_lit * 2] = (byte)(n >>> 8);
        this.pending_buf[this.d_buf + this.last_lit * 2 + 1] = (byte)n;
        this.pending_buf[this.l_buf + this.last_lit] = (byte)n2;
        ++this.last_lit;
        if (n == 0) {
            final short[] dyn_ltree = this.dyn_ltree;
            final int n3 = n2 * 2;
            ++dyn_ltree[n3];
        }
        else {
            ++this.matches;
            --n;
            final short[] dyn_ltree2 = this.dyn_ltree;
            final int n4 = (q._length_code[n2] + 256 + 1) * 2;
            ++dyn_ltree2[n4];
            final short[] dyn_dtree = this.dyn_dtree;
            final int n5 = q.d_code(n) * 2;
            ++dyn_dtree[n5];
        }
        if ((this.last_lit & 0x1FFF) == 0x0 && this.level > 2) {
            int n6 = this.last_lit * 8;
            final int n7 = this.strstart - this.block_start;
            for (int i = 0; i < 30; ++i) {
                n6 += (int)(this.dyn_dtree[i * 2] * (5L + q.extra_dbits[i]));
            }
            final int n8 = n6 >>> 3;
            if (this.matches < this.last_lit / 2 && n8 < n7 / 2) {
                return true;
            }
        }
        return this.last_lit == this.lit_bufsize - 1;
    }
    
    void bi_flush() {
        if (this.bi_valid == 16) {
            this.put_short(this.bi_buf);
            this.bi_buf = 0;
            this.bi_valid = 0;
        }
        else if (this.bi_valid >= 8) {
            this.put_byte((byte)this.bi_buf);
            this.bi_buf >>>= 8;
            this.bi_valid -= 8;
        }
    }
    
    void bi_windup() {
        if (this.bi_valid > 8) {
            this.put_short(this.bi_buf);
        }
        else if (this.bi_valid > 0) {
            this.put_byte((byte)this.bi_buf);
        }
        this.bi_buf = 0;
        this.bi_valid = 0;
    }
    
    int build_bl_tree() {
        this.scan_tree(this.dyn_ltree, this.l_desc.max_code);
        this.scan_tree(this.dyn_dtree, this.d_desc.max_code);
        this.bl_desc.build_tree(this);
        int n;
        for (n = 18; n >= 3 && this.bl_tree[q.bl_order[n] * 2 + 1] == 0; --n) {}
        this.opt_len += 3 * (n + 1) + 5 + 5 + 4;
        return n;
    }
    
    void compress_block(final short[] array, final short[] array2) {
        int i = 0;
        if (this.last_lit != 0) {
            do {
                int n = (this.pending_buf[this.d_buf + i * 2] << 8 & 0xFF00) | (this.pending_buf[this.d_buf + i * 2 + 1] & 0xFF);
                final int n2 = this.pending_buf[this.l_buf + i] & 0xFF;
                ++i;
                if (n == 0) {
                    this.send_code(n2, array);
                }
                else {
                    final byte b = q._length_code[n2];
                    this.send_code(b + 256 + 1, array);
                    final int n3 = q.extra_lbits[b];
                    if (n3 != 0) {
                        this.send_bits(n2 - q.base_length[b], n3);
                    }
                    final int d_code = q.d_code(--n);
                    this.send_code(d_code, array2);
                    final int n4 = q.extra_dbits[d_code];
                    if (n4 == 0) {
                        continue;
                    }
                    this.send_bits(n - q.base_dist[d_code], n4);
                }
            } while (i < this.last_lit);
        }
        this.send_code(256, array);
        this.last_eob_len = array[513];
    }
    
    void copy_block(final int n, final int n2, final boolean b) {
        this.bi_windup();
        this.last_eob_len = 8;
        if (b) {
            this.put_short((short)n2);
            this.put_short((short)~n2);
        }
        this.put_byte(this.window, n, n2);
    }
    
    int deflate(final s strm, final int last_flush) {
        if (last_flush > 4 || last_flush < 0) {
            return -2;
        }
        if (strm.next_out == null || (strm.next_in == null && strm.avail_in != 0) || (this.status == 666 && last_flush != 4)) {
            strm.msg = d.z_errmsg[4];
            return -2;
        }
        if (strm.avail_out == 0) {
            strm.msg = d.z_errmsg[7];
            return -5;
        }
        this.strm = strm;
        final int last_flush2 = this.last_flush;
        this.last_flush = last_flush;
        if (this.status == 42) {
            final int n = 8 + (this.w_bits - 8 << 4) << 8;
            int n2 = (this.level - 1 & 0xFF) >> 1;
            if (n2 > 3) {
                n2 = 3;
            }
            int n3 = n | n2 << 6;
            if (this.strstart != 0) {
                n3 |= 0x20;
            }
            final int n4 = n3 + (31 - n3 % 31);
            this.status = 113;
            this.putShortMSB(n4);
            if (this.strstart != 0) {
                this.putShortMSB((int)(strm.adler >>> 16));
                this.putShortMSB((int)(strm.adler & 0xFFFFL));
            }
            strm.adler = strm._adler.a(0L, null, 0, 0);
        }
        if (this.pending != 0) {
            strm.flush_pending();
            if (strm.avail_out == 0) {
                this.last_flush = -1;
                return 0;
            }
        }
        else if (strm.avail_in == 0 && last_flush <= last_flush2 && last_flush != 4) {
            strm.msg = d.z_errmsg[7];
            return -5;
        }
        if (this.status == 666 && strm.avail_in != 0) {
            strm.msg = d.z_errmsg[7];
            return -5;
        }
        if (strm.avail_in != 0 || this.lookahead != 0 || (last_flush != 0 && this.status != 666)) {
            int n5 = -1;
            switch (d.config_table[this.level].func) {
                case 0: {
                    n5 = this.deflate_stored(last_flush);
                    break;
                }
                case 1: {
                    n5 = this.deflate_fast(last_flush);
                    break;
                }
                case 2: {
                    n5 = this.deflate_slow(last_flush);
                    break;
                }
            }
            if (n5 == 2 || n5 == 3) {
                this.status = 666;
            }
            if (n5 == 0 || n5 == 2) {
                if (strm.avail_out == 0) {
                    this.last_flush = -1;
                }
                return 0;
            }
            if (n5 == 1) {
                if (last_flush == 1) {
                    this._tr_align();
                }
                else {
                    this._tr_stored_block(0, 0, false);
                    if (last_flush == 3) {
                        for (int i = 0; i < this.hash_size; ++i) {
                            this.head[i] = 0;
                        }
                    }
                }
                strm.flush_pending();
                if (strm.avail_out == 0) {
                    this.last_flush = -1;
                    return 0;
                }
            }
        }
        if (last_flush != 4) {
            return 0;
        }
        if (this.noheader != 0) {
            return 1;
        }
        this.putShortMSB((int)(strm.adler >>> 16));
        this.putShortMSB((int)(strm.adler & 0xFFFFL));
        strm.flush_pending();
        this.noheader = -1;
        return (this.pending == 0) ? 1 : 0;
    }
    
    int deflate_fast(final int n) {
        int n2 = 0;
        while (true) {
            if (this.lookahead < 262) {
                this.fill_window();
                if (this.lookahead < 262 && n == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    this.flush_block_only(n == 4);
                    if (this.strm.avail_out != 0) {
                        return (n == 4) ? 3 : 1;
                    }
                    if (n == 4) {
                        return 2;
                    }
                    return 0;
                }
            }
            if (this.lookahead >= 3) {
                this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 2] & 0xFF)) & this.hash_mask);
                n2 = (this.head[this.ins_h] & 0xFFFF);
                this.prev[this.strstart & this.w_mask] = this.head[this.ins_h];
                this.head[this.ins_h] = (short)this.strstart;
            }
            if (n2 != 0L && (this.strstart - n2 & 0xFFFF) <= this.w_size - 262 && this.strategy != 2) {
                this.match_length = this.longest_match(n2);
            }
            boolean b;
            if (this.match_length >= 3) {
                b = this._tr_tally(this.strstart - this.match_start, this.match_length - 3);
                this.lookahead -= this.match_length;
                if (this.match_length <= this.max_lazy_match && this.lookahead >= 3) {
                    --this.match_length;
                    do {
                        ++this.strstart;
                        this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 2] & 0xFF)) & this.hash_mask);
                        n2 = (this.head[this.ins_h] & 0xFFFF);
                        this.prev[this.strstart & this.w_mask] = this.head[this.ins_h];
                        this.head[this.ins_h] = (short)this.strstart;
                    } while (--this.match_length != 0);
                    ++this.strstart;
                }
                else {
                    this.strstart += this.match_length;
                    this.match_length = 0;
                    this.ins_h = (this.window[this.strstart] & 0xFF);
                    this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 1] & 0xFF)) & this.hash_mask);
                }
            }
            else {
                b = this._tr_tally(0, this.window[this.strstart] & 0xFF);
                --this.lookahead;
                ++this.strstart;
            }
            if (b) {
                this.flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
                continue;
            }
        }
    }
    
    int deflate_slow(final int n) {
        int n2 = 0;
        while (true) {
            if (this.lookahead < 262) {
                this.fill_window();
                if (this.lookahead < 262 && n == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    if (this.match_available != 0) {
                        this._tr_tally(0, this.window[this.strstart - 1] & 0xFF);
                        this.match_available = 0;
                    }
                    this.flush_block_only(n == 4);
                    if (this.strm.avail_out != 0) {
                        return (n == 4) ? 3 : 1;
                    }
                    if (n == 4) {
                        return 2;
                    }
                    return 0;
                }
            }
            if (this.lookahead >= 3) {
                this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 2] & 0xFF)) & this.hash_mask);
                n2 = (this.head[this.ins_h] & 0xFFFF);
                this.prev[this.strstart & this.w_mask] = this.head[this.ins_h];
                this.head[this.ins_h] = (short)this.strstart;
            }
            this.prev_length = this.match_length;
            this.prev_match = this.match_start;
            this.match_length = 2;
            if (n2 != 0 && this.prev_length < this.max_lazy_match && (this.strstart - n2 & 0xFFFF) <= this.w_size - 262) {
                if (this.strategy != 2) {
                    this.match_length = this.longest_match(n2);
                }
                if (this.match_length <= 5 && (this.strategy == 1 || (this.match_length == 3 && this.strstart - this.match_start > 4096))) {
                    this.match_length = 2;
                }
            }
            if (this.prev_length >= 3 && this.match_length <= this.prev_length) {
                final int n3 = this.strstart + this.lookahead - 3;
                final boolean tr_tally = this._tr_tally(this.strstart - 1 - this.prev_match, this.prev_length - 3);
                this.lookahead -= this.prev_length - 1;
                this.prev_length -= 2;
                do {
                    if (++this.strstart <= n3) {
                        this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 2] & 0xFF)) & this.hash_mask);
                        n2 = (this.head[this.ins_h] & 0xFFFF);
                        this.prev[this.strstart & this.w_mask] = this.head[this.ins_h];
                        this.head[this.ins_h] = (short)this.strstart;
                    }
                } while (--this.prev_length != 0);
                this.match_available = 0;
                this.match_length = 2;
                ++this.strstart;
                if (!tr_tally) {
                    continue;
                }
                this.flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
                continue;
            }
            else if (this.match_available != 0) {
                if (this._tr_tally(0, this.window[this.strstart - 1] & 0xFF)) {
                    this.flush_block_only(false);
                }
                ++this.strstart;
                --this.lookahead;
                if (this.strm.avail_out == 0) {
                    return 0;
                }
                continue;
            }
            else {
                this.match_available = 1;
                ++this.strstart;
                --this.lookahead;
            }
        }
    }
    
    int deflate_stored(final int n) {
        int n2 = 65535;
        if (n2 > this.pending_buf_size - 5) {
            n2 = this.pending_buf_size - 5;
        }
        while (true) {
            if (this.lookahead <= 1) {
                this.fill_window();
                if (this.lookahead == 0 && n == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    this.flush_block_only(n == 4);
                    if (this.strm.avail_out == 0) {
                        return (n == 4) ? 2 : 0;
                    }
                    return (n == 4) ? 3 : 1;
                }
            }
            this.strstart += this.lookahead;
            this.lookahead = 0;
            final int strstart = this.block_start + n2;
            if (this.strstart == 0 || this.strstart >= strstart) {
                this.lookahead = this.strstart - strstart;
                this.strstart = strstart;
                this.flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
            }
            if (this.strstart - this.block_start >= this.w_size - 262) {
                this.flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
                continue;
            }
        }
    }
    
    int deflateEnd() {
        if (this.status != 42 && this.status != 113 && this.status != 666) {
            return -2;
        }
        this.pending_buf = null;
        this.head = null;
        this.prev = null;
        this.window = null;
        return (this.status == 113) ? -3 : 0;
    }
    
    int deflateInit(final s s, final int n) {
        return this.deflateInit(s, n, 15);
    }
    
    int deflateInit(final s s, final int n, final int n2) {
        return this.deflateInit2(s, n, 8, n2, 8, 0);
    }
    
    int deflateInit2(final s s, int level, final int n, int w_bits, final int n2, final int strategy) {
        int noheader = 0;
        s.msg = null;
        if (level == -1) {
            level = 6;
        }
        if (w_bits < 0) {
            noheader = 1;
            w_bits = -w_bits;
        }
        if (n2 < 1 || n2 > 9 || n != 8 || w_bits < 9 || w_bits > 15 || level < 0 || level > 9 || strategy < 0 || strategy > 2) {
            return -2;
        }
        s.dstate = this;
        this.noheader = noheader;
        this.w_bits = w_bits;
        this.w_size = 1 << this.w_bits;
        this.w_mask = this.w_size - 1;
        this.hash_bits = n2 + 7;
        this.hash_size = 1 << this.hash_bits;
        this.hash_mask = this.hash_size - 1;
        this.hash_shift = (this.hash_bits + 3 - 1) / 3;
        this.window = new byte[this.w_size * 2];
        this.prev = new short[this.w_size];
        this.head = new short[this.hash_size];
        this.lit_bufsize = 1 << n2 + 6;
        this.pending_buf = new byte[this.lit_bufsize * 4];
        this.pending_buf_size = this.lit_bufsize * 4;
        this.d_buf = this.lit_bufsize / 2;
        this.l_buf = 3 * this.lit_bufsize;
        this.level = level;
        this.strategy = strategy;
        this.method = (byte)n;
        return this.deflateReset(s);
    }
    
    int deflateParams(final s s, int level, final int strategy) {
        int deflate = 0;
        if (level == -1) {
            level = 6;
        }
        if (level < 0 || level > 9 || strategy < 0 || strategy > 2) {
            return -2;
        }
        if (d.config_table[this.level].func != d.config_table[level].func && s.total_in != 0L) {
            deflate = s.deflate(1);
        }
        if (this.level != level) {
            this.level = level;
            this.max_lazy_match = d.config_table[this.level].max_lazy;
            this.good_match = d.config_table[this.level].good_length;
            this.nice_match = d.config_table[this.level].nice_length;
            this.max_chain_length = d.config_table[this.level].max_chain;
        }
        this.strategy = strategy;
        return deflate;
    }
    
    int deflateReset(final s s) {
        final long n = 0L;
        s.total_out = n;
        s.total_in = n;
        s.msg = null;
        s.data_type = 2;
        this.pending = 0;
        this.pending_out = 0;
        if (this.noheader < 0) {
            this.noheader = 0;
        }
        this.status = ((this.noheader != 0) ? 113 : 42);
        s.adler = s._adler.a(0L, null, 0, 0);
        this.last_flush = 0;
        this.tr_init();
        this.lm_init();
        return 0;
    }
    
    int deflateSetDictionary(final s s, final byte[] array, final int n) {
        int n2 = n;
        int n3 = 0;
        if (array == null || this.status != 42) {
            return -2;
        }
        s.adler = s._adler.a(s.adler, array, 0, n);
        if (n2 < 3) {
            return 0;
        }
        if (n2 > this.w_size - 262) {
            n2 = this.w_size - 262;
            n3 = n - n2;
        }
        System.arraycopy(array, n3, this.window, 0, n2);
        this.strstart = n2;
        this.block_start = n2;
        this.ins_h = (this.window[0] & 0xFF);
        this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[1] & 0xFF)) & this.hash_mask);
        for (int i = 0; i <= n2 - 3; ++i) {
            this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[i + 2] & 0xFF)) & this.hash_mask);
            this.prev[i & this.w_mask] = this.head[this.ins_h];
            this.head[this.ins_h] = (short)i;
        }
        return 0;
    }
    
    void fill_window() {
        do {
            int w_size = this.window_size - this.lookahead - this.strstart;
            if (w_size == 0 && this.strstart == 0 && this.lookahead == 0) {
                w_size = this.w_size;
            }
            else if (w_size == -1) {
                --w_size;
            }
            else if (this.strstart >= this.w_size + this.w_size - 262) {
                System.arraycopy(this.window, this.w_size, this.window, 0, this.w_size);
                this.match_start -= this.w_size;
                this.strstart -= this.w_size;
                this.block_start -= this.w_size;
                int hash_size;
                int n = hash_size = this.hash_size;
                do {
                    final int n2 = this.head[--hash_size] & 0xFFFF;
                    this.head[hash_size] = (short)((n2 >= this.w_size) ? ((short)(n2 - this.w_size)) : 0);
                } while (--n != 0);
                int w_size2;
                int n3 = w_size2 = this.w_size;
                do {
                    final int n4 = this.prev[--w_size2] & 0xFFFF;
                    this.prev[w_size2] = (short)((n4 >= this.w_size) ? ((short)(n4 - this.w_size)) : 0);
                } while (--n3 != 0);
                w_size += this.w_size;
            }
            if (this.strm.avail_in == 0) {
                return;
            }
            this.lookahead += this.strm.read_buf(this.window, this.strstart + this.lookahead, w_size);
            if (this.lookahead < 3) {
                continue;
            }
            this.ins_h = (this.window[this.strstart] & 0xFF);
            this.ins_h = ((this.ins_h << this.hash_shift ^ (this.window[this.strstart + 1] & 0xFF)) & this.hash_mask);
        } while (this.lookahead < 262 && this.strm.avail_in != 0);
    }
    
    void flush_block_only(final boolean b) {
        this._tr_flush_block((this.block_start >= 0) ? this.block_start : -1, this.strstart - this.block_start, b);
        this.block_start = this.strstart;
        this.strm.flush_pending();
    }
    
    void init_block() {
        for (int i = 0; i < 286; ++i) {
            this.dyn_ltree[i * 2] = 0;
        }
        for (int j = 0; j < 30; ++j) {
            this.dyn_dtree[j * 2] = 0;
        }
        for (int k = 0; k < 19; ++k) {
            this.bl_tree[k * 2] = 0;
        }
        this.dyn_ltree[512] = 1;
        final boolean b = false;
        this.static_len = (b ? 1 : 0);
        this.opt_len = (b ? 1 : 0);
        final boolean b2 = false;
        this.matches = (b2 ? 1 : 0);
        this.last_lit = (b2 ? 1 : 0);
    }
    
    void lm_init() {
        this.window_size = 2 * this.w_size;
        this.head[this.hash_size - 1] = 0;
        for (int i = 0; i < this.hash_size - 1; ++i) {
            this.head[i] = 0;
        }
        this.max_lazy_match = d.config_table[this.level].max_lazy;
        this.good_match = d.config_table[this.level].good_length;
        this.nice_match = d.config_table[this.level].nice_length;
        this.max_chain_length = d.config_table[this.level].max_chain;
        this.strstart = 0;
        this.block_start = 0;
        this.lookahead = 0;
        final int n = 2;
        this.prev_length = n;
        this.match_length = n;
        this.match_available = 0;
        this.ins_h = 0;
    }
    
    int longest_match(int match_start) {
        int max_chain_length = this.max_chain_length;
        int strstart = this.strstart;
        int prev_length = this.prev_length;
        final int n = (this.strstart > this.w_size - 262) ? (this.strstart - (this.w_size - 262)) : 0;
        int n2 = this.nice_match;
        final int w_mask = this.w_mask;
        final int n3 = this.strstart + 258;
        byte b = this.window[strstart + prev_length - 1];
        byte b2 = this.window[strstart + prev_length];
        if (this.prev_length >= this.good_match) {
            max_chain_length >>= 2;
        }
        if (n2 > this.lookahead) {
            n2 = this.lookahead;
        }
        do {
            int n4 = match_start;
            if (this.window[n4 + prev_length] == b2 && this.window[n4 + prev_length - 1] == b && this.window[n4] == this.window[strstart]) {
                if (this.window[++n4] != this.window[strstart + 1]) {
                    continue;
                }
                strstart += 2;
                ++n4;
                while (this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && this.window[++strstart] == this.window[++n4] && strstart < n3) {}
                final int n5 = 258 - (n3 - strstart);
                strstart = n3 - 258;
                if (n5 <= prev_length) {
                    continue;
                }
                this.match_start = match_start;
                if ((prev_length = n5) >= n2) {
                    break;
                }
                b = this.window[strstart + prev_length - 1];
                b2 = this.window[strstart + prev_length];
            }
        } while ((match_start = (this.prev[match_start & w_mask] & 0xFFFF)) > n && --max_chain_length != 0);
        if (prev_length <= this.lookahead) {
            return prev_length;
        }
        return this.lookahead;
    }
    
    void pqdownheap(final short[] array, int n) {
        final int n2 = this.heap[n];
        for (int i = n << 1; i <= this.heap_len; i <<= 1) {
            if (i < this.heap_len && smaller(array, this.heap[i + 1], this.heap[i], this.depth)) {
                ++i;
            }
            if (smaller(array, n2, this.heap[i], this.depth)) {
                break;
            }
            this.heap[n] = this.heap[i];
            n = i;
        }
        this.heap[n] = n2;
    }
    
    final void put_byte(final byte b) {
        this.pending_buf[this.pending++] = b;
    }
    
    final void put_byte(final byte[] array, final int n, final int n2) {
        System.arraycopy(array, n, this.pending_buf, this.pending, n2);
        this.pending += n2;
    }
    
    final void put_short(final int n) {
        this.put_byte((byte)n);
        this.put_byte((byte)(n >>> 8));
    }
    
    final void putShortMSB(final int n) {
        this.put_byte((byte)(n >> 8));
        this.put_byte((byte)n);
    }
    
    void scan_tree(final short[] array, final int n) {
        short n2 = -1;
        short n3 = array[1];
        short n4 = 0;
        short n5 = 7;
        short n6 = 4;
        if (n3 == 0) {
            n5 = 138;
            n6 = 3;
        }
        array[(n + 1) * 2 + 1] = -1;
        for (int i = 0; i <= n; ++i) {
            final short n7 = n3;
            n3 = array[(i + 1) * 2 + 1];
            if (++n4 >= n5 || n7 != n3) {
                if (n4 < n6) {
                    final short[] bl_tree = this.bl_tree;
                    final short n8 = (short)(n7 * 2);
                    bl_tree[n8] += n4;
                }
                else if (n7 != 0) {
                    if (n7 != n2) {
                        final short[] bl_tree2 = this.bl_tree;
                        final short n9 = (short)(n7 * 2);
                        ++bl_tree2[n9];
                    }
                    final short[] bl_tree3 = this.bl_tree;
                    final int n10 = 32;
                    ++bl_tree3[n10];
                }
                else if (n4 <= 10) {
                    final short[] bl_tree4 = this.bl_tree;
                    final int n11 = 34;
                    ++bl_tree4[n11];
                }
                else {
                    final short[] bl_tree5 = this.bl_tree;
                    final int n12 = 36;
                    ++bl_tree5[n12];
                }
                n4 = 0;
                n2 = n7;
                if (n3 == 0) {
                    n5 = 138;
                    n6 = 3;
                }
                else if (n7 == n3) {
                    n5 = 6;
                    n6 = 3;
                }
                else {
                    n5 = 7;
                    n6 = 4;
                }
            }
        }
    }
    
    void send_all_trees(final int n, final int n2, final int n3) {
        this.send_bits(n - 257, 5);
        this.send_bits(n2 - 1, 5);
        this.send_bits(n3 - 4, 4);
        for (int i = 0; i < n3; ++i) {
            this.send_bits(this.bl_tree[q.bl_order[i] * 2 + 1], 3);
        }
        this.send_tree(this.dyn_ltree, n - 1);
        this.send_tree(this.dyn_dtree, n2 - 1);
    }
    
    void send_bits(final int n, final int n2) {
        if (this.bi_valid > 16 - n2) {
            this.put_short(this.bi_buf |= (short)(n << this.bi_valid & 0xFFFF));
            this.bi_buf = (short)(n >>> 16 - this.bi_valid);
            this.bi_valid += n2 - 16;
        }
        else {
            this.bi_buf |= (short)(n << this.bi_valid & 0xFFFF);
            this.bi_valid += n2;
        }
    }
    
    final void send_code(final int n, final short[] array) {
        final int n2 = n * 2;
        this.send_bits(array[n2] & 0xFFFF, array[n2 + 1] & 0xFFFF);
    }
    
    void send_tree(final short[] array, final int n) {
        short n2 = -1;
        short n3 = array[1];
        int n4 = 0;
        int n5 = 7;
        int n6 = 4;
        if (n3 == 0) {
            n5 = 138;
            n6 = 3;
        }
        for (int i = 0; i <= n; ++i) {
            final short n7 = n3;
            n3 = array[(i + 1) * 2 + 1];
            if (++n4 >= n5 || n7 != n3) {
                if (n4 < n6) {
                    do {
                        this.send_code(n7, this.bl_tree);
                    } while (--n4 != 0);
                }
                else if (n7 != 0) {
                    if (n7 != n2) {
                        this.send_code(n7, this.bl_tree);
                        --n4;
                    }
                    this.send_code(16, this.bl_tree);
                    this.send_bits(n4 - 3, 2);
                }
                else if (n4 <= 10) {
                    this.send_code(17, this.bl_tree);
                    this.send_bits(n4 - 3, 3);
                }
                else {
                    this.send_code(18, this.bl_tree);
                    this.send_bits(n4 - 11, 7);
                }
                n4 = 0;
                n2 = n7;
                if (n3 == 0) {
                    n5 = 138;
                    n6 = 3;
                }
                else if (n7 == n3) {
                    n5 = 6;
                    n6 = 3;
                }
                else {
                    n5 = 7;
                    n6 = 4;
                }
            }
        }
    }
    
    void set_data_type() {
        int i = 0;
        short n = 0;
        short n2 = 0;
        while (i < 7) {
            n2 += this.dyn_ltree[i * 2];
            ++i;
        }
        while (i < 128) {
            n += this.dyn_ltree[i * 2];
            ++i;
        }
        while (i < 256) {
            n2 += this.dyn_ltree[i * 2];
            ++i;
        }
        this.data_type = (byte)((n2 <= n >>> 2) ? 1 : 0);
    }
    
    void tr_init() {
        this.l_desc.dyn_tree = this.dyn_ltree;
        this.l_desc.stat_desc = v.static_l_desc;
        this.d_desc.dyn_tree = this.dyn_dtree;
        this.d_desc.stat_desc = v.static_d_desc;
        this.bl_desc.dyn_tree = this.bl_tree;
        this.bl_desc.stat_desc = v.static_bl_desc;
        this.bi_buf = 0;
        this.bi_valid = 0;
        this.last_eob_len = 8;
        this.init_block();
    }
    
    static {
        (config_table = new Config[10])[0] = new Config(0, 0, 0, 0, 0);
        d.config_table[1] = new Config(4, 4, 8, 4, 1);
        d.config_table[2] = new Config(4, 5, 16, 8, 1);
        d.config_table[3] = new Config(4, 6, 32, 32, 1);
        d.config_table[4] = new Config(4, 4, 16, 16, 2);
        d.config_table[5] = new Config(8, 16, 32, 32, 2);
        d.config_table[6] = new Config(8, 16, 128, 128, 2);
        d.config_table[7] = new Config(8, 32, 128, 256, 2);
        d.config_table[8] = new Config(32, 128, 258, 1024, 2);
        d.config_table[9] = new Config(32, 258, 258, 4096, 2);
        z_errmsg = new String[] { "need dictionary", "stream end", "", "file error", "stream error", "data error", "insufficient memory", "buffer error", "incompatible version", "" };
    }
    
    static class Config
    {
        int good_length;
        int max_lazy;
        int nice_length;
        int max_chain;
        int func;
        
        Config(final int good_length, final int max_lazy, final int nice_length, final int max_chain, final int func) {
            this.good_length = good_length;
            this.max_lazy = max_lazy;
            this.nice_length = nice_length;
            this.max_chain = max_chain;
            this.func = func;
        }
    }
}
