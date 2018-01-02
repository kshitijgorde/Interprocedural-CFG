// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.serialize;

import java.io.Writer;
import java.io.OutputStream;
import java.util.Properties;
import java.io.IOException;
import org.xml.sax.ContentHandler;

public interface Serializer
{
    ContentHandler asContentHandler() throws IOException;
    
    DOMSerializer asDOMSerializer() throws IOException;
    
    Properties getOutputFormat();
    
    OutputStream getOutputStream();
    
    Writer getWriter();
    
    boolean reset();
    
    void setOutputFormat(final Properties p0);
    
    void setOutputStream(final OutputStream p0);
    
    void setWriter(final Writer p0);
}
