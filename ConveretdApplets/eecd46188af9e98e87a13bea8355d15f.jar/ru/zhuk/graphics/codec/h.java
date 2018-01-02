// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class h extends FilterInputStream
{
    int b;
    int a;
    
    public h(final InputStream inputStream, final int b) {
        super(inputStream);
        this.b = b;
    }
    
    public final int read() throws IOException {
        if (this.b > 0) {
            final int read = super.in.read();
            if (read != -1) {
                --this.b;
            }
            return read;
        }
        return -1;
    }
    
    public final int read(final byte[] array) throws IOException {
        return this.read(array, 0, array.length);
    }
    
    public final int read(final byte[] array, final int n, int n2) throws IOException {
        if (this.b > 0) {
            n2 = ((n2 > this.b) ? this.b : n2);
            final int read = super.in.read(array, n, n2);
            if (read > 0) {
                this.b -= read;
            }
            return read;
        }
        return -1;
    }
    
    public final long skip(long n) throws IOException {
        n = ((n > this.b) ? this.b : n);
        final long skip = super.in.skip(n);
        if (skip > 0) {
            this.b -= (int)skip;
        }
        return skip;
    }
    
    public final int available() throws IOException {
        final int available = super.in.available();
        return (available > this.b) ? this.b : available;
    }
    
    public final void close() throws IOException {
    }
    
    public final void mark(final int n) {
        this.a = this.b;
        super.in.mark(n);
    }
    
    public final void reset() throws IOException {
        super.in.reset();
        this.b = this.a;
    }
    
    public final boolean markSupported() {
        return super.in.markSupported();
    }
}
