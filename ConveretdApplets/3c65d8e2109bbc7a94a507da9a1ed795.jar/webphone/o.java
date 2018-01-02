// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class o extends FilterInputStream
{
    private static byte[] a;
    
    public o(final InputStream inputStream) throws IOException {
        super(inputStream);
    }
    
    public int read() throws IOException {
        final int read = this.in.read();
        if (read == -1) {
            return -1;
        }
        return o.a[read] & 0xFF;
    }
    
    public int read(final byte[] array) throws IOException {
        final int read = this.in.read(array);
        for (int i = 0; i < read; ++i) {
            array[i] = o.a[array[i] & 0xFF];
        }
        return read;
    }
    
    public int read(final byte[] array, final int n, int read) throws IOException {
        read = this.in.read(array, n, read);
        for (int i = 0; i < read; ++i) {
            array[n + i] = o.a[array[n + i] & 0xFF];
        }
        return read;
    }
    
    static {
        o.a = null;
        o.a = new byte[256];
        int n = 0;
        for (int i = 0; i < 256; ++i) {
            o.a[i] = (byte)n;
            int n2 = 256;
            do {
                n2 >>= 1;
                n ^= n2;
            } while ((n ^ n2) > n);
        }
    }
}
