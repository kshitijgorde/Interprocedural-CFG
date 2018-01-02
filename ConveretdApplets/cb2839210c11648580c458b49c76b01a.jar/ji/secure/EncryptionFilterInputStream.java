// 
// Decompiled by Procyon v0.5.30
// 

package ji.secure;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class EncryptionFilterInputStream extends FilterInputStream
{
    public EncryptionFilterInputStream(final InputStream inputStream) {
        super(inputStream);
    }
    
    public int read() throws IOException {
        return this.a((byte)super.in.read());
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = super.read(array, n, n2);
        for (int i = n; i < n2; ++i) {
            array[i] = this.a(array[i]);
        }
        return read;
    }
    
    private byte a(final byte b) {
        return (byte)(255 - (b & 0xFF));
    }
}
