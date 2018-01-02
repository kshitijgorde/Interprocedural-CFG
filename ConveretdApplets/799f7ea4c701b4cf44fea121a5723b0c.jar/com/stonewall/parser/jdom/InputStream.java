// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.jdom;

import java.io.IOException;

public class InputStream extends java.io.InputStream implements ParserInput
{
    private int line;
    private final String file;
    private final java.io.InputStream istr;
    
    public InputStream(final String file, final java.io.InputStream istr) {
        this.line = 1;
        this.file = file;
        this.istr = istr;
    }
    
    @Override
    public int read() throws IOException {
        final int n = this.istr.read();
        if (n == 10) {
            ++this.line;
        }
        return n;
    }
    
    @Override
    public void close() throws IOException {
        if (this.istr != null) {
            this.istr.close();
        }
    }
    
    @Override
    public String file() {
        return this.file;
    }
    
    @Override
    public int line() {
        return this.line;
    }
    
    @Override
    public String location() {
        return String.valueOf(this.file) + ":" + this.line;
    }
    
    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return super.read(b, off, 1);
    }
}
