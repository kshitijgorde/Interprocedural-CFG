// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import java.io.IOException;
import java.io.InputStream;

public class CodedInputStream extends InputStream
{
    private static final byte[] bZero;
    private byte[] bKey;
    private int iKey;
    private InputStream in;
    
    public CodedInputStream(final InputStream in) {
        this.in = in;
        this.iKey = 0;
        this.bKey = CodedInputStream.bZero;
    }
    
    public void setKey(final byte[] bKey) {
        this.iKey = 0;
        this.bKey = bKey;
    }
    
    public int read() throws IOException {
        int read;
        if ((read = this.in.read()) >= 0) {
            read = ((read & 0xFF) - this.bKey[this.iKey++] & 0xFF);
            if (this.iKey >= this.bKey.length) {
                this.iKey = 0;
            }
        }
        return read;
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    static {
        bZero = new byte[] { 0 };
    }
}
