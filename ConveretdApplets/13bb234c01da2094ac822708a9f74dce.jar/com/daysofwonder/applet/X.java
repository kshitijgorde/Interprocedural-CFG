// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.io.InputStream;
import java.io.FilterInputStream;

public class X extends FilterInputStream
{
    private int a;
    private int b;
    private aF c;
    
    public X(final InputStream inputStream, final int b, final aF c) {
        super(inputStream);
        this.a = 0;
        this.b = 0;
        this.b = b;
        this.c = c;
    }
    
    public int read() {
        final int read = this.in.read();
        if (read >= 0) {
            this.c.b(++this.a, this.b);
        }
        return read;
    }
    
    public int read(final byte[] array) {
        final int read = this.in.read(array);
        if (read > 0) {
            this.a += read;
            this.c.b(this.a, this.b);
        }
        return read;
    }
    
    public int read(final byte[] array, final int n, final int n2) {
        final int read = this.in.read(array, n, n2);
        if (read > 0) {
            this.a += read;
            this.c.b(this.a, this.b);
        }
        return read;
    }
    
    public long skip(final long n) {
        final long skip = this.in.skip(n);
        if (skip > 0L) {
            this.a += (int)skip;
            this.c.b(this.a, this.b);
        }
        return skip;
    }
    
    public void close() {
        this.in.close();
    }
    
    public synchronized void reset() {
        this.in.reset();
        this.a = this.b - this.in.available();
        this.c.b(this.a, this.b);
    }
}
