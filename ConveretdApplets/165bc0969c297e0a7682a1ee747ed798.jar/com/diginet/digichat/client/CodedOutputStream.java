// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.io.IOException;
import java.io.OutputStream;

public class CodedOutputStream extends OutputStream
{
    private static final byte[] bZero;
    private byte[] bKey;
    private int iKey;
    private OutputStream out;
    
    public CodedOutputStream(final OutputStream out) {
        this.out = out;
        this.iKey = 0;
        this.bKey = CodedOutputStream.bZero;
    }
    
    public void setKey(final byte[] bKey) {
        this.iKey = 0;
        this.bKey = bKey;
    }
    
    public void write(final int n) throws IOException {
        this.out.write((n & 0xFF) + this.bKey[this.iKey++] & 0xFF);
        if (this.iKey >= this.bKey.length) {
            this.iKey = 0;
        }
    }
    
    public void flush() throws IOException {
        this.out.flush();
    }
    
    public void close() throws IOException {
        this.out.close();
    }
    
    static {
        bZero = new byte[] { 0 };
    }
}
