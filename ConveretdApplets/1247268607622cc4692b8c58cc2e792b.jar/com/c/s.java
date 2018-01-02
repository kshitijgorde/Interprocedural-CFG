// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

public final class s
{
    private static final int MAX_WBITS = 15;
    private static final int DEF_WBITS = 15;
    private static final int NO_FLUSH = 0;
    private static final int PARTIAL_FLUSH = 1;
    private static final int SYNC_FLUSH = 2;
    private static final int FULL_FLUSH = 3;
    private static final int FINISH = 4;
    private static final int MAX_MEM_LEVEL = 9;
    private static final int OK = 0;
    private static final int STREAM_END = 1;
    private static final int NEED_DICT = 2;
    private static final int ERRNO = -1;
    private static final int STREAM_ERROR = -2;
    private static final int DATA_ERROR = -3;
    private static final int MEM_ERROR = -4;
    private static final int BUF_ERROR = -5;
    private static final int VERSION_ERROR = -6;
    public byte[] next_in;
    public int next_in_index;
    public int avail_in;
    public long total_in;
    public byte[] next_out;
    public int next_out_index;
    public int avail_out;
    public long total_out;
    public String msg;
    d dstate;
    Inflate istate;
    int data_type;
    public long adler;
    a _adler;
    
    public s() {
        this._adler = new a();
    }
    
    public int deflate(final int n) {
        if (this.dstate == null) {
            return -2;
        }
        return this.dstate.deflate(this, n);
    }
    
    public int deflateEnd() {
        if (this.dstate == null) {
            return -2;
        }
        final int deflateEnd = this.dstate.deflateEnd();
        this.dstate = null;
        return deflateEnd;
    }
    
    public int deflateInit(final int n) {
        return this.deflateInit(n, 15);
    }
    
    public int deflateInit(final int n, final boolean b) {
        return this.deflateInit(n, 15, b);
    }
    
    public int deflateInit(final int n, final int n2) {
        return this.deflateInit(n, n2, false);
    }
    
    public int deflateInit(final int n, final int n2, final boolean b) {
        this.dstate = new d();
        return this.dstate.deflateInit(this, n, b ? (-n2) : n2);
    }
    
    public int deflateParams(final int n, final int n2) {
        if (this.dstate == null) {
            return -2;
        }
        return this.dstate.deflateParams(this, n, n2);
    }
    
    public int deflateSetDictionary(final byte[] array, final int n) {
        if (this.dstate == null) {
            return -2;
        }
        return this.dstate.deflateSetDictionary(this, array, n);
    }
    
    void flush_pending() {
        int n = this.dstate.pending;
        if (n > this.avail_out) {
            n = this.avail_out;
        }
        if (n == 0) {
            return;
        }
        if (this.dstate.pending_buf.length <= this.dstate.pending_out || this.next_out.length <= this.next_out_index || this.dstate.pending_buf.length < this.dstate.pending_out + n || this.next_out.length < this.next_out_index + n) {
            System.out.println(this.dstate.pending_buf.length + ", " + this.dstate.pending_out + ", " + this.next_out.length + ", " + this.next_out_index + ", " + n);
            System.out.println("avail_out=" + this.avail_out);
        }
        System.arraycopy(this.dstate.pending_buf, this.dstate.pending_out, this.next_out, this.next_out_index, n);
        this.next_out_index += n;
        final d dstate = this.dstate;
        dstate.pending_out += n;
        this.total_out += n;
        this.avail_out -= n;
        final d dstate2 = this.dstate;
        dstate2.pending -= n;
        if (this.dstate.pending == 0) {
            this.dstate.pending_out = 0;
        }
    }
    
    public void free() {
        this.next_in = null;
        this.next_out = null;
        this.msg = null;
        this._adler = null;
    }
    
    public int inflate(final int n) {
        if (this.istate == null) {
            return -2;
        }
        return this.istate.inflate(this, n);
    }
    
    public int inflateEnd() {
        if (this.istate == null) {
            return -2;
        }
        final int inflateEnd = this.istate.inflateEnd(this);
        this.istate = null;
        return inflateEnd;
    }
    
    public int inflateInit() {
        return this.inflateInit(15);
    }
    
    public int inflateInit(final boolean b) {
        return this.inflateInit(15, b);
    }
    
    public int inflateInit(final int n) {
        return this.inflateInit(n, false);
    }
    
    public int inflateInit(final int n, final boolean b) {
        this.istate = new Inflate();
        return this.istate.inflateInit(this, b ? (-n) : n);
    }
    
    public int inflateSetDictionary(final byte[] array, final int n) {
        if (this.istate == null) {
            return -2;
        }
        return this.istate.inflateSetDictionary(this, array, n);
    }
    
    public int inflateSync() {
        if (this.istate == null) {
            return -2;
        }
        return this.istate.inflateSync(this);
    }
    
    int read_buf(final byte[] array, final int n, final int n2) {
        int avail_in = this.avail_in;
        if (avail_in > n2) {
            avail_in = n2;
        }
        if (avail_in == 0) {
            return 0;
        }
        this.avail_in -= avail_in;
        if (this.dstate.noheader == 0) {
            this.adler = this._adler.a(this.adler, this.next_in, this.next_in_index, avail_in);
        }
        System.arraycopy(this.next_in, this.next_in_index, array, n, avail_in);
        this.next_in_index += avail_in;
        this.total_in += avail_in;
        return avail_in;
    }
}
