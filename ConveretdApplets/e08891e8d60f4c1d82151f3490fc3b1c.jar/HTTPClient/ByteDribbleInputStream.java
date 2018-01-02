// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.io.FilterInputStream;

class ByteDribbleInputStream extends FilterInputStream
{
    public int position;
    private final int MAX = 100;
    private final Random rng;
    
    public ByteDribbleInputStream(final InputStream in) {
        super(in);
        this.rng = new Random();
    }
    
    public ByteDribbleInputStream(final byte[] data) {
        this(new ByteArrayInputStream(data));
    }
    
    public ByteDribbleInputStream(final String file) throws FileNotFoundException {
        this(new FileInputStream(file));
    }
    
    public int read() throws IOException {
        ++this.position;
        return super.in.read();
    }
    
    public int read(final byte[] b, final int off, int len) throws IOException {
        if (len < 0) {
            throw new IOException("Can't read negative number of bytes");
        }
        if (len == 0) {
            return 0;
        }
        if (len > 100) {
            len = 100;
        }
        len = (int)(this.rng.nextDouble() * len + 1.0);
        return super.in.read(b, off, len);
    }
}
