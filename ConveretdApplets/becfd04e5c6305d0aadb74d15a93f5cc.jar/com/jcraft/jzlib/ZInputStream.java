// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class ZInputStream extends FilterInputStream
{
    protected ZStream z;
    protected int bufsize;
    protected int flush;
    protected byte[] buf;
    protected byte[] buf1;
    protected boolean compress;
    private InputStream in;
    private boolean nomoreinput;
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public ZInputStream(final InputStream in) {
        super(in);
        this.z = new ZStream();
        this.bufsize = 512;
        this.flush = 0;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.in = null;
        this.nomoreinput = false;
        this.in = in;
        this.z.inflateInit();
        this.compress = false;
        this.z.next_in = this.buf;
        this.z.next_in_index = 0;
        this.z.avail_in = 0;
    }
    
    public int read() throws IOException {
        if (this.read(this.buf1, 0, 1) == -1) {
            return -1;
        }
        return this.buf1[0] & 0xFF;
    }
    
    public int read(final byte[] next_out, final int next_out_index, final int avail_out) throws IOException {
        if (avail_out == 0) {
            return 0;
        }
        this.z.next_out = next_out;
        this.z.next_out_index = next_out_index;
        this.z.avail_out = avail_out;
        do {
            if (this.z.avail_in == 0 && !this.nomoreinput) {
                this.z.next_in_index = 0;
                this.z.avail_in = this.in.read(this.buf, 0, this.bufsize);
                if (this.z.avail_in == -1) {
                    this.z.avail_in = 0;
                    this.nomoreinput = true;
                }
            }
            final int inflate = this.z.inflate(this.flush);
            if (this.nomoreinput && inflate == -5) {
                return -1;
            }
            if (inflate != 0 && inflate != 1) {
                throw new ZStreamException((this.compress ? "de" : "in") + "flating: " + this.z.msg);
            }
            if (this.nomoreinput && this.z.avail_out == avail_out) {
                return -1;
            }
        } while (this.z.avail_out == avail_out);
        return avail_out - this.z.avail_out;
    }
    
    public long getTotalOut() {
        return this.z.total_out;
    }
    
    public long getTotalIn() {
        return this.z.total_in;
    }
    
    public long skip(final long n) throws IOException {
        int n2 = 512;
        if (n < n2) {
            n2 = (int)n;
        }
        return this.read(new byte[n2]);
    }
    
    public int getFlushMode() {
        return this.flush;
    }
    
    public void setFlushMode(final int flush) {
        this.flush = flush;
    }
}
