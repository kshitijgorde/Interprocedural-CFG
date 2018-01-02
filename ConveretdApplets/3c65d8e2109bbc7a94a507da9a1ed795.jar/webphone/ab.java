// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class ab extends FilterOutputStream
{
    private static byte[] do;
    private static byte[] if;
    private byte[] a;
    
    public ab(final OutputStream outputStream, final boolean b) throws IOException {
        super(outputStream);
        this.a = null;
        this.a = (b ? ab.do : ab.if);
    }
    
    public void write(final int n) throws IOException {
        throw new IOException(this.getClass().getName() + ".write() :\n\tDo not support simple write(int b).");
    }
    
    public void write(final byte[] array, int n, int n2) throws IOException {
        n2 >>= 1;
        for (int i = 0; i < n2; ++i) {
            this.out.write(this.a[(array[n++] & 0xFF) | (array[n++] & 0xFF) << 8]);
        }
    }
    
    static {
        ab.do = new byte[65536];
        ab.if = new byte[65536];
        final byte[] array = new byte[256];
        int n = 0;
        for (int i = 0; i < 256; ++i) {
            array[i] = (byte)n;
            int n2 = 256;
            do {
                n2 >>= 1;
                n ^= n2;
            } while ((n ^ n2) > n);
        }
        final cb cb = new cb();
        final bm bm = new bm();
        for (int j = 0; j < 65536; ++j) {
            ab.do[j] = array[cb.a((short)j) & 0xFF];
            ab.if[j] = array[bm.a((short)j) & 0xFF];
        }
    }
}
