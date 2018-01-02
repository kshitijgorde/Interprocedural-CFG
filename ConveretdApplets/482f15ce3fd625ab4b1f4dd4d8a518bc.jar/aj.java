import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aj extends InputStream
{
    public InputStream a;
    public byte a;
    
    public aj(final InputStream a) throws Exception {
        this.a = a;
        if (this.a.read() != 68 || this.a.read() != 68 || this.a.read() != 78 || this.a.read() != 66) {
            throw new Exception("Invalid DDNB Metadata Resource File");
        }
        final int n;
        if ((n = this.a.read() * 256 + this.a.read()) != 1) {
            throw new Exception("DDNB: Cannot understand codec version '" + n + "'");
        }
        this.a = (byte)this.a.read();
        if (this.a.read() != 0) {
            throw new Exception("DDNB: Header Marker is invalid");
        }
    }
    
    public final int available() throws IOException {
        return this.a.available();
    }
    
    public final void close() throws IOException {
        this.a.close();
    }
    
    public final void reset() throws IOException {
        this.a.reset();
    }
    
    public final long skip(final long n) throws IOException {
        return this.a.skip(n);
    }
    
    public final int read() throws IOException {
        final int read = this.a.read();
        final byte a = this.a;
        this.a = (byte)(a + 1);
        return read ^ a;
    }
    
    public final int read(final byte[] array) throws IOException {
        final int read = this.a.read(array);
        for (int i = 0; i < read; ++i) {
            final int n = i;
            final byte b = array[n];
            final byte a = this.a;
            this.a = (byte)(a + 1);
            array[n] = (byte)(b ^ a);
        }
        return read;
    }
    
    public final int read(final byte[] array, final int n, final int n2) throws IOException {
        final int read = this.a.read(array, n, n2);
        for (int i = 0; i < read; ++i) {
            final int n3 = i;
            final byte b = array[n3];
            final byte a = this.a;
            this.a = (byte)(a + 1);
            array[n3] = (byte)(b ^ a);
        }
        return read;
    }
}
