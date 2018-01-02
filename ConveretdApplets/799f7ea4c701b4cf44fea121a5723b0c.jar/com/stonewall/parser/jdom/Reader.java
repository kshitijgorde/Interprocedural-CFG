// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.parser.jdom;

import java.io.IOException;

public class Reader extends java.io.Reader implements ParserInput
{
    private int line;
    private final String file;
    private final java.io.Reader reader;
    
    public Reader(final String file, final java.io.Reader reader) {
        this.line = 1;
        this.file = file;
        this.reader = reader;
    }
    
    @Override
    public int read() throws IOException {
        final int n = this.reader.read();
        if (n == 10) {
            ++this.line;
        }
        return n;
    }
    
    @Override
    public void close() throws IOException {
        if (this.reader != null) {
            this.reader.close();
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
    public int read(final char[] b, final int off, final int len) throws IOException {
        final int n = this.read();
        if (n != -1) {
            b[off] = (char)n;
            return 1;
        }
        return -1;
    }
}
