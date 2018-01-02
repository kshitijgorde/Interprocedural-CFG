// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class u extends FilterOutputStream
{
    private static byte[] a;
    
    public u(final OutputStream outputStream) throws IOException {
        super(outputStream);
    }
    
    public void write(final int n) throws IOException {
        this.out.write(u.a[n & 0xFF]);
    }
    
    public void write(final byte[] array) throws IOException {
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i] = u.a[array[i] & 0xFF];
        }
        this.out.write(array);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        for (int i = 0; i < n2; ++i) {
            array[n + i] = u.a[array[n + i] & 0xFF];
        }
        this.out.write(array, n, n2);
    }
    
    static {
        u.a = null;
        u.a = new byte[256];
        int n = 0;
        for (int i = 0; i < 256; ++i) {
            u.a[i] = (byte)n;
            int n2 = 256;
            do {
                n2 >>= 1;
                n ^= n2;
            } while ((n ^ n2) > n);
        }
    }
}
