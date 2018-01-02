// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

class WireLogInputStream extends FilterInputStream
{
    private InputStream in;
    
    public WireLogInputStream(final InputStream in) {
        super(in);
        this.in = in;
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        final int l = this.in.read(b, off, len);
        if (l > 0) {
            Wire.input(b, off, l);
        }
        return l;
    }
    
    public int read() throws IOException {
        final int l = this.in.read();
        if (l > 0) {
            Wire.input(l);
        }
        return l;
    }
    
    public int read(final byte[] b) throws IOException {
        final int l = this.in.read(b);
        if (l > 0) {
            Wire.input(b, 0, l);
        }
        return l;
    }
}
