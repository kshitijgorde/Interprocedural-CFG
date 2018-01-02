// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime.output;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.Writer;

class WriterOutputBuffer implements OutputBuffer
{
    private static final int KB = 1024;
    private static int BUFFER_SIZE;
    private Writer _writer;
    
    public WriterOutputBuffer(final Writer writer) {
        this._writer = new BufferedWriter(writer, WriterOutputBuffer.BUFFER_SIZE);
    }
    
    public String close() {
        try {
            this._writer.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return "";
    }
    
    public OutputBuffer append(final String s) {
        try {
            this._writer.write(s);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }
    
    public OutputBuffer append(final char[] s, final int from, final int to) {
        try {
            this._writer.write(s, from, to);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }
    
    public OutputBuffer append(final char ch) {
        try {
            this._writer.write(ch);
        }
        catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return this;
    }
    
    static {
        WriterOutputBuffer.BUFFER_SIZE = 4096;
        final String osName = System.getProperty("os.name");
        if (osName.equalsIgnoreCase("solaris")) {
            WriterOutputBuffer.BUFFER_SIZE = 32768;
        }
    }
}
