// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

import java.io.IOException;
import java.io.OutputStream;

final class LEDataOutputStream extends OutputStream
{
    OutputStream out;
    
    public LEDataOutputStream(final OutputStream out) {
        this.out = out;
    }
    
    public void write(final byte[] array, final int n, final int n2) throws IOException {
        this.out.write(array, n, n2);
    }
    
    public void write(final int n) throws IOException {
        this.out.write(n);
    }
    
    public void writeByte(final byte b) throws IOException {
        this.out.write(b & 0xFF);
    }
    
    public void writeInt(final int n) throws IOException {
        this.out.write(n & 0xFF);
        this.out.write(n >> 8 & 0xFF);
        this.out.write(n >> 16 & 0xFF);
        this.out.write(n >> 24 & 0xFF);
    }
    
    public void writeShort(final int n) throws IOException {
        this.out.write(n & 0xFF);
        this.out.write(n >> 8 & 0xFF);
    }
}
