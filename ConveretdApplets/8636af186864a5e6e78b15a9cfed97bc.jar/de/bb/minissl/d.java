// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.io.IOException;
import java.io.InputStream;

final class d extends InputStream
{
    private c e;
    private byte[] f;
    
    protected d(final c e) {
        this.f = new byte[1];
        this.e = e;
    }
    
    public int available() throws IOException {
        return this.e.A();
    }
    
    public void close() throws IOException {
        this.e.J();
    }
    
    public int read() throws IOException {
        return this.e.Q();
    }
    
    public int read(final byte[] array) throws IOException {
        return this.e.G(array);
    }
    
    public int read(final byte[] array, final int n, int g) throws IOException {
        final byte[] array2 = new byte[g];
        g = this.e.G(array2);
        if (g > 0) {
            System.arraycopy(array2, 0, array, n, g);
        }
        return g;
    }
}
