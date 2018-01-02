// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer;

import java.io.OutputStream;
import java.io.Writer;
import java.io.IOException;

interface WriterChain
{
    void write(final int p0) throws IOException;
    
    void write(final char[] p0) throws IOException;
    
    void write(final char[] p0, final int p1, final int p2) throws IOException;
    
    void write(final String p0) throws IOException;
    
    void write(final String p0, final int p1, final int p2) throws IOException;
    
    void flush() throws IOException;
    
    void close() throws IOException;
    
    Writer getWriter();
    
    OutputStream getOutputStream();
}
