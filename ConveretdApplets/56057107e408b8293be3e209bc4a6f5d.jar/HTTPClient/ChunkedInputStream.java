// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class ChunkedInputStream extends FilterInputStream
{
    byte[] one;
    private int chunk_len;
    private boolean eof;
    
    ChunkedInputStream(final InputStream inputStream) {
        super(inputStream);
        this.one = new byte[1];
        this.chunk_len = -1;
        this.eof = false;
    }
    
    public synchronized int read() throws IOException {
        if (this.read(this.one, 0, 1) == 1) {
            return this.one[0] & 0xFF;
        }
        return -1;
    }
    
    public synchronized int read(final byte[] array, final int n, int chunk_len) throws IOException {
        if (this.eof) {
            return -1;
        }
        if (this.chunk_len == -1) {
            try {
                this.chunk_len = Codecs.getChunkLength(super.in);
            }
            catch (ParseException ex) {
                throw new IOException(ex.toString());
            }
        }
        if (this.chunk_len <= 0) {
            new Response(new Request(null, null, null, null, null, null, false), null).readTrailers(super.in);
            this.eof = true;
            return -1;
        }
        if (chunk_len > this.chunk_len) {
            chunk_len = this.chunk_len;
        }
        final int read = super.in.read(array, n, chunk_len);
        if (read == -1) {
            throw new EOFException("Premature EOF encountered");
        }
        this.chunk_len -= read;
        if (this.chunk_len == 0) {
            super.in.read();
            super.in.read();
            this.chunk_len = -1;
        }
        return read;
    }
    
    public synchronized long skip(final long n) throws IOException {
        final int read = this.read(new byte[(int)n], 0, (int)n);
        if (read > 0) {
            return read;
        }
        return 0L;
    }
    
    public synchronized int available() throws IOException {
        if (this.eof) {
            return 0;
        }
        if (this.chunk_len != -1) {
            return this.chunk_len + super.in.available();
        }
        return super.in.available();
    }
}
