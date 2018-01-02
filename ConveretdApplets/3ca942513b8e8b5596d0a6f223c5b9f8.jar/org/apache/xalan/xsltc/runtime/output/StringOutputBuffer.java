// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.runtime.output;

class StringOutputBuffer implements OutputBuffer
{
    private StringBuffer _buffer;
    
    public StringOutputBuffer() {
        this._buffer = new StringBuffer();
    }
    
    public String close() {
        return this._buffer.toString();
    }
    
    public OutputBuffer append(final String s) {
        this._buffer.append(s);
        return this;
    }
    
    public OutputBuffer append(final char[] s, final int from, final int to) {
        this._buffer.append(s, from, to);
        return this;
    }
    
    public OutputBuffer append(final char ch) {
        this._buffer.append(ch);
        return this;
    }
}
