import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

// 
// Decompiled by Procyon v0.5.30
// 

class ProgressFilterInputStream extends FilterInputStream
{
    protected int mBytesRead;
    protected int mStreamEnd;
    protected DecodeListener listener;
    
    ProgressFilterInputStream(final DecodeListener listener, final InputStream inStream) {
        super(inStream);
        this.mBytesRead = 0;
        this.mStreamEnd = 0;
        this.listener = null;
        this.listener = listener;
    }
    
    public int read() throws IOException {
        final int c = super.read();
        if (c != -1) {
            ++this.mBytesRead;
        }
        return c;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int r = super.read(b, off, len);
        if (r != -1) {
            this.mBytesRead += r;
            if (this.mStreamEnd != 0) {
                this.listener.decodeUpdate(3, this.mBytesRead / this.mStreamEnd);
            }
        }
        return r;
    }
    
    void setStreamEnd(final int inEnd) {
        this.mStreamEnd = inEnd;
    }
}
