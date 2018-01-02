// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

import java.io.IOException;
import java.io.InputStream;

public class InputStreamSource implements Source
{
    private final InputStream in;
    
    public InputStreamSource(final InputStream in) {
        if (in == null) {
            throw new NullPointerException("in");
        }
        this.in = in;
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.in.read(array, n, n2);
    }
    
    public boolean willReadBlock() {
        return true;
    }
    
    public boolean isSeekable() {
        return false;
    }
    
    public long tell() {
        return -1L;
    }
    
    public long seek(final long n) {
        return -1L;
    }
    
    public long length() {
        return -1L;
    }
}
