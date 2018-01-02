// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

class WireLogOutputStream extends FilterOutputStream
{
    private OutputStream out;
    
    public WireLogOutputStream(final OutputStream out) {
        super(out);
        this.out = out;
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.out.write(b, off, len);
        Wire.output(b, off, len);
    }
    
    public void write(final int b) throws IOException {
        this.out.write(b);
        Wire.output(b);
    }
    
    public void write(final byte[] b) throws IOException {
        this.out.write(b);
        Wire.output(b);
    }
}
