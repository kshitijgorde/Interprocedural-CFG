// 
// Decompiled by Procyon v0.5.30
// 

package de.bb.minissl;

import java.io.IOException;
import java.io.OutputStream;

final class b extends OutputStream
{
    private c C;
    private byte[] D;
    
    protected b(final c c) {
        this.D = new byte[1];
        this.C = c;
    }
    
    public void close() throws IOException {
        this.C.J();
    }
    
    public void flush() throws IOException {
        this.C.I.flush();
    }
    
    public void write(final int n) throws IOException {
        this.D[0] = (byte)n;
        this.C.F(this.D);
    }
    
    public void write(final byte[] array) throws IOException {
        this.C.F(array);
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        final byte[] array2 = new byte[n2];
        System.arraycopy(array, n, array2, 0, n2);
        this.C.F(array2);
    }
}
