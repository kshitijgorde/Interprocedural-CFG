// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

import java.io.IOException;
import java.io.OutputStream;

public class o extends OutputStream
{
    protected int flush;
    protected s z1;
    protected int bufsize;
    protected byte[] buf;
    protected byte[] buf1;
    protected boolean compress;
    protected OutputStream out;
    
    public o(final OutputStream out) {
        this.flush = 0;
        this.z1 = new s();
        this.bufsize = 512;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.out = out;
        this.z1.inflateInit();
        this.compress = false;
    }
    
    public o(final OutputStream outputStream, final int n) {
        this(outputStream, n, false);
    }
    
    public o(final OutputStream out, final int n, final boolean b) {
        this.flush = 0;
        this.z1 = new s();
        this.bufsize = 512;
        this.buf = new byte[this.bufsize];
        this.buf1 = new byte[1];
        this.out = out;
        this.z1.deflateInit(n, b);
        this.compress = true;
    }
    
    @Override
    public void close() throws IOException {
        try {
            this.finish();
        }
        catch (IOException ex) {}
        finally {
            this.end();
            this.out.close();
            this.out = null;
        }
    }
    
    public void end() {
        if (this.z1 == null) {
            return;
        }
        if (this.compress) {
            this.z1.deflateEnd();
        }
        else {
            this.z1.inflateEnd();
        }
        this.z1.free();
        this.z1 = null;
    }
    
    public void finish() throws IOException {
        do {
            this.z1.next_out = this.buf;
            this.z1.next_out_index = 0;
            this.z1.avail_out = this.bufsize;
            int n;
            if (this.compress) {
                n = this.z1.deflate(4);
            }
            else {
                n = this.z1.inflate(4);
            }
            if (n != 1 && n != 0) {
                throw new e((this.compress ? "de" : "in") + "flating: " + this.z1.msg);
            }
            if (this.bufsize - this.z1.avail_out <= 0) {
                continue;
            }
            this.out.write(this.buf, 0, this.bufsize - this.z1.avail_out);
        } while (this.z1.avail_in > 0 || this.z1.avail_out == 0);
        this.flush();
    }
    
    @Override
    public void flush() throws IOException {
        this.out.flush();
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
    
    public void setFlushMode(final int flush) {
        this.flush = flush;
    }
    
    @Override
    public void write(final byte[] next_in, final int next_in_index, final int avail_in) throws IOException {
        if (avail_in == 0) {
            return;
        }
        this.z1.next_in = next_in;
        this.z1.next_in_index = next_in_index;
        this.z1.avail_in = avail_in;
        do {
            this.z1.next_out = this.buf;
            this.z1.next_out_index = 0;
            this.z1.avail_out = this.bufsize;
            int n;
            if (this.compress) {
                n = this.z1.deflate(this.flush);
            }
            else {
                n = this.z1.inflate(this.flush);
            }
            if (n != 0) {
                throw new e((this.compress ? "de" : "in") + "flating: " + this.z1.msg);
            }
            this.out.write(this.buf, 0, this.bufsize - this.z1.avail_out);
        } while (this.z1.avail_in > 0 || this.z1.avail_out == 0);
    }
    
    @Override
    public void write(final int n) throws IOException {
        this.buf1[0] = (byte)n;
        this.write(this.buf1, 0, 1);
    }
}
