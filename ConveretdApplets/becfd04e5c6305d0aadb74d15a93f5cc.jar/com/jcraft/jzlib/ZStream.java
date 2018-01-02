// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

public final class ZStream
{
    private static final int MAX_WBITS = 15;
    private static final int DEF_WBITS = 15;
    private static final int Z_NO_FLUSH = 0;
    private static final int Z_PARTIAL_FLUSH = 1;
    private static final int Z_SYNC_FLUSH = 2;
    private static final int Z_FULL_FLUSH = 3;
    private static final int Z_FINISH = 4;
    private static final int MAX_MEM_LEVEL = 9;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    public byte[] next_in;
    public int next_in_index;
    public int avail_in;
    public long total_in;
    public byte[] next_out;
    public int next_out_index;
    public int avail_out;
    public long total_out;
    public String msg;
    Inflate istate;
    int data_type;
    public long adler;
    Adler32 _adler;
    
    public int inflateSync() {
        if (this.istate == null) {
            return -2;
        }
        return this.istate.inflateSync(this);
    }
    
    public int inflateInit() {
        return this.inflateInit(15);
    }
    
    public int inflateInit(final int n) {
        this.istate = new Inflate();
        return this.istate.inflateInit(this, n);
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
    
    public int inflateSetDictionary(final byte[] array, final int n) {
        if (this.istate == null) {
            return -2;
        }
        return this.istate.inflateSetDictionary(this, array, n);
    }
    
    public void free() {
        this.next_in = null;
        this.next_out = null;
        this.msg = null;
        this._adler = null;
    }
    
    public ZStream() {
        this._adler = new Adler32();
    }
}
