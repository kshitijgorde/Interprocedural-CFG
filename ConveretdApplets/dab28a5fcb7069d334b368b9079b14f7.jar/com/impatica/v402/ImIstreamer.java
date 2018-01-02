// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.IOException;
import java.io.InputStream;

public class ImIstreamer extends InputStream implements ImIstream
{
    boolean arraycopy;
    boolean close;
    InputStream currentTimeMillis;
    int read;
    int I;
    long Z;
    byte[][] C;
    
    public ImIstreamer() {
        this.C = new byte[10][];
    }
    
    public ImIstreamer(final boolean close) {
        this.C = new byte[10][];
        this.close = close;
    }
    
    public final boolean I(final InputStream currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
        this.read = 0;
        this.I = 0;
        this.Z = System.currentTimeMillis();
        this.arraycopy(0);
        this.arraycopy = (this.read == 0);
        return this.read != 0;
    }
    
    public final int I() {
        return this.I;
    }
    
    public final int read() {
        final int i = this.I;
        final int n = i >> 16;
        byte[] arraycopy;
        if (n < this.C.length) {
            arraycopy = this.C[n];
        }
        else {
            arraycopy = null;
        }
        if (arraycopy == null && !this.arraycopy) {
            for (int j = this.read >> 16; j <= n; arraycopy = this.arraycopy(j++)) {}
        }
        if (i > this.read) {
            this.arraycopy = true;
            return -1;
        }
        ++this.I;
        return arraycopy[i & 0xFFFF] & 0xFF;
    }
    
    public final int read(final byte[] array, int n, int n2) {
        if (n2 == 0) {
            return 0;
        }
        final int read = this.read();
        if (read == -1) {
            return -1;
        }
        array[n++] = (byte)read;
        if (--n2 == 0) {
            return 1;
        }
        final int n3 = this.I & 0xFFFF;
        final int n4 = 65536 - n3;
        if (n4 == 65536) {
            return 1;
        }
        if (n2 > n4) {
            n2 = n4;
        }
        System.arraycopy(this.C[this.I >> 16], n3, array, n, n2);
        this.I += n2;
        return n2 + 1;
    }
    
    public final void I(final int n) {
        if (this.arraycopy || n == 0) {
            return;
        }
        final int n2 = (int)((System.currentTimeMillis() - this.Z) / 1000L);
        if (n2 == 0) {
            return;
        }
        if (this.read / n2 < n) {
            final int n3 = this.read >> 16;
            if (n3 >= this.C.length || this.C[n3] == null) {
                this.arraycopy(n3);
            }
        }
    }
    
    final byte[] arraycopy(final int n) {
        byte[] array = null;
        byte[][] c = this.C;
        if (this.close) {
            for (int i = (this.I >> 16) - 1; i >= 0; --i) {
                array = c[i];
                if (array != null) {
                    c[i] = null;
                    break;
                }
            }
        }
        if (array == null) {
            array = new byte[65536];
        }
        final int length = c.length;
        if (n >= length) {
            System.arraycopy(c, 0, c = new byte[n + 10][], 0, length);
            this.C = c;
        }
        c[n] = array;
        int j = 65536;
        int n2 = 0;
        while (j > 0) {
            int read;
            try {
                read = this.currentTimeMillis.read(array, n2, j);
            }
            catch (IOException ex) {
                read = -1;
            }
            if (read == -1) {
                try {
                    this.currentTimeMillis.close();
                }
                catch (IOException ex2) {}
                this.arraycopy = true;
                return array;
            }
            this.read += read;
            n2 += read;
            j -= read;
        }
        return array;
    }
    
    public final void Z(final int i) {
        this.I = i;
    }
}
