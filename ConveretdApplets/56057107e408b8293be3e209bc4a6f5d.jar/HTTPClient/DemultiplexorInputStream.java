// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public abstract class DemultiplexorInputStream extends FilterInputStream
{
    public DemultiplexorInputStream(final InputStream inputStream) {
        super(inputStream);
    }
    
    public abstract int read() throws IOException;
    
    public abstract int read(final byte[] p0, final int p1, final int p2) throws IOException;
    
    public abstract int available() throws IOException;
    
    public abstract void setTerminator(final byte[] p0, final int[] p1);
    
    public abstract boolean atEnd();
    
    public abstract boolean startsWithCRLF() throws IOException;
}
