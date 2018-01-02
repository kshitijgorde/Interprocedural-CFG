// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.InputStream;

public class u extends InputStream
{
    long b;
    InputStream c;
    
    public u(final InputStream c) {
        this.b = 0L;
        this.c = c;
    }
    
    public int read() {
        ++this.b;
        return this.c.read();
    }
    
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, final int n2) {
        final int read = this.c.read(array, n, n2);
        if (read > 0) {
            this.b += read;
        }
        return read;
    }
    
    public long skip(final long n) {
        final long skip = this.c.skip(n);
        if (skip > 0L) {
            this.b += skip;
        }
        return skip;
    }
    
    public long a() {
        return this.b;
    }
    
    public int available() {
        return this.c.available();
    }
    
    public void close() {
        this.c.close();
    }
}
