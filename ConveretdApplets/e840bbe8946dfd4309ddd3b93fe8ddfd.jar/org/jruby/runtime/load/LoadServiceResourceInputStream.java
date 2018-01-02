// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.runtime.load;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class LoadServiceResourceInputStream extends ByteArrayInputStream
{
    public LoadServiceResourceInputStream(final byte[] bytes) {
        super(bytes);
    }
    
    public LoadServiceResourceInputStream(final InputStream stream) throws IOException {
        super(new byte[0]);
        this.bufferEntireStream(stream);
    }
    
    public byte[] getBytes() {
        if (this.buf.length != this.count) {
            final byte[] b = new byte[this.count];
            System.arraycopy(this.buf, 0, b, 0, this.count);
            return b;
        }
        return this.buf;
    }
    
    private void bufferEntireStream(final InputStream stream) throws IOException {
        final byte[] b = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = stream.read(b)) != -1) {
            final byte[] newbuf = new byte[this.buf.length + bytesRead];
            System.arraycopy(this.buf, 0, newbuf, 0, this.buf.length);
            System.arraycopy(b, 0, newbuf, this.buf.length, bytesRead);
            this.buf = newbuf;
            this.count = this.buf.length;
        }
    }
}
