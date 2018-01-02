// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

class WriterToASCI extends Writer implements WriterChain
{
    private final OutputStream m_os;
    
    public WriterToASCI(final OutputStream os) {
        this.m_os = os;
    }
    
    public void write(final char[] chars, final int start, final int length) throws IOException {
        for (int n = length + start, i = start; i < n; ++i) {
            this.m_os.write(chars[i]);
        }
    }
    
    public void write(final int c) throws IOException {
        this.m_os.write(c);
    }
    
    public void write(final String s) throws IOException {
        for (int n = s.length(), i = 0; i < n; ++i) {
            this.m_os.write(s.charAt(i));
        }
    }
    
    public void flush() throws IOException {
        this.m_os.flush();
    }
    
    public void close() throws IOException {
        this.m_os.close();
    }
    
    public OutputStream getOutputStream() {
        return this.m_os;
    }
    
    public Writer getWriter() {
        return null;
    }
}
