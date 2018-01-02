// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

import java.io.IOException;
import java.io.OutputStream;

public class ZOutputStream extends OutputStream
{
    protected ZStream z;
    protected int bufsize;
    protected int flush;
    protected byte[] buf;
    protected byte[] buf1;
    protected boolean compress;
    private OutputStream out;
    
    public void close() throws IOException {
        try {
            this.out.flush();
        }
        catch (IOException ex) {}
        this.z.free();
        this.z = null;
        this.out.close();
        this.out = null;
    }
    
    public ZOutputStream(final OutputStream out) {
        this.z = new ZStream();
        this.bufsize = 512;
        this.flush = 0;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.out = out;
        this.z.inflateInit();
        this.compress = false;
    }
    
    public long getTotalOut() {
        return this.z.total_out;
    }
    
    public void write(final int n) throws IOException {
        this.buf1[0] = (byte)n;
        this.write(this.buf1, 0, 1);
    }
    
    public void write(final byte[] next_in, final int next_in_index, final int avail_in) throws IOException {
        if (avail_in == 0) {
            return;
        }
        this.z.next_in = next_in;
        this.z.next_in_index = next_in_index;
        this.z.avail_in = avail_in;
        do {
            this.z.next_out = this.buf;
            this.z.next_out_index = 0;
            this.z.avail_out = this.bufsize;
            final int inflate = this.z.inflate(this.flush);
            if (inflate != 0 && inflate != 1) {
                throw new ZStreamException((this.compress ? "de" : "in") + "flating: " + this.z.msg);
            }
            this.out.write(this.buf, 0, this.bufsize - this.z.avail_out);
        } while (this.z.avail_in > 0 || this.z.avail_out == 0);
    }
    
    public long getTotalIn() {
        return this.z.total_in;
    }
    
    public void flush() throws IOException {
        this.out.flush();
    }
    
    public int getFlushMode() {
        return this.flush;
    }
    
    public void setFlushMode(final int flush) {
        this.flush = flush;
    }
}
