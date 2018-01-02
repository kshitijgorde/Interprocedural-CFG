// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import java.io.InputStream;

public class d extends u
{
    long a;
    
    public d(final InputStream inputStream, final long a) {
        super(inputStream);
        this.a = a;
    }
    
    public int read() {
        if (this.available() == 0) {
            return -1;
        }
        return super.read();
    }
    
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    public int read(final byte[] array, final int n, int available) {
        if (this.available() == 0) {
            return -1;
        }
        if (this.available() < available) {
            available = this.available();
        }
        return super.read(array, n, available);
    }
    
    public long skip(long n) {
        if (this.available() == 0) {
            return -1L;
        }
        if (this.available() < n) {
            n = this.available();
        }
        return super.skip(n);
    }
    
    public int available() {
        return (int)(this.a - this.a());
    }
}
