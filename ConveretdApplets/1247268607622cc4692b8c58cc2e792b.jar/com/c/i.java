// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class i extends FilterInputStream
{
    protected s z1;
    protected int bufsize;
    protected int flush;
    protected byte[] buf;
    protected byte[] buf1;
    protected boolean compress;
    protected InputStream in;
    private boolean nomoreinput;
    
    public i(final InputStream inputStream) {
        this(inputStream, false);
    }
    
    public i(final InputStream in, final boolean b) {
        super(in);
        this.z1 = new s();
        this.bufsize = 512;
        this.flush = 0;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.in = null;
        this.nomoreinput = false;
        this.in = in;
        this.z1.inflateInit(b);
        this.compress = false;
        this.z1.next_in = this.buf;
        this.z1.next_in_index = 0;
        this.z1.avail_in = 0;
    }
    
    public i(final InputStream in, final int n) {
        super(in);
        this.z1 = new s();
        this.bufsize = 512;
        this.flush = 0;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.in = null;
        this.nomoreinput = false;
        this.in = in;
        this.z1.deflateInit(n);
        this.compress = true;
        this.z1.next_in = this.buf;
        this.z1.next_in_index = 0;
        this.z1.avail_in = 0;
    }
    
    @Override
    public void close() throws IOException {
        this.in.close();
    }
    
    public int getFlushMode() {
        return this.flush;
    }
    
    public long getTotalIn() {
        return this.z1.total_in;
    }
    
    public long getTotalOut() {
        return this.z1.total_out;
    }
    
    @Override
    public int read() throws IOException {
        if (this.read(this.buf1, 0, 1) == -1) {
            return -1;
        }
        return this.buf1[0] & 0xFF;
    }
    
    @Override
    public int read(final byte[] next_out, final int next_out_index, final int avail_out) throws IOException {
        if (avail_out == 0) {
            return 0;
        }
        this.z1.next_out = next_out;
        this.z1.next_out_index = next_out_index;
        this.z1.avail_out = avail_out;
        int n;
        do {
            if (this.z1.avail_in == 0 && !this.nomoreinput) {
                this.z1.next_in_index = 0;
                this.z1.avail_in = this.in.read(this.buf, 0, this.bufsize);
                if (this.z1.avail_in == -1) {
                    this.z1.avail_in = 0;
                    this.nomoreinput = true;
                }
            }
            if (this.compress) {
                n = this.z1.deflate(this.flush);
            }
            else {
                n = this.z1.inflate(this.flush);
            }
            if (this.nomoreinput && n == -5) {
                return -1;
            }
            if (n != 0 && n != 1) {
                throw new e((this.compress ? "de" : "in") + "flating: " + this.z1.msg);
            }
            if ((this.nomoreinput || n == 1) && this.z1.avail_out == avail_out) {
                return -1;
            }
        } while (this.z1.avail_out == avail_out && n == 0);
        return avail_out - this.z1.avail_out;
    }
    
    public void setFlushMode(final int flush) {
        this.flush = flush;
    }
    
    @Override
    public long skip(final long n) throws IOException {
        int n2 = 512;
        if (n < n2) {
            n2 = (int)n;
        }
        return this.read(new byte[n2]);
    }
}
