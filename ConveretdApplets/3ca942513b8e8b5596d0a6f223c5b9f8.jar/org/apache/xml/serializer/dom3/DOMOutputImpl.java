// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.serializer.dom3;

import java.io.OutputStream;
import java.io.Writer;
import org.w3c.dom.ls.LSOutput;

final class DOMOutputImpl implements LSOutput
{
    protected Writer fCharStream;
    protected OutputStream fByteStream;
    protected String fSystemId;
    protected String fEncoding;
    
    public DOMOutputImpl() {
        this.fCharStream = null;
        this.fByteStream = null;
        this.fSystemId = null;
        this.fEncoding = null;
    }
    
    public Writer getCharacterStream() {
        return this.fCharStream;
    }
    
    public void setCharacterStream(final Writer characterStream) {
        this.fCharStream = characterStream;
    }
    
    public OutputStream getByteStream() {
        return this.fByteStream;
    }
    
    public void setByteStream(final OutputStream byteStream) {
        this.fByteStream = byteStream;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public void setSystemId(final String systemId) {
        this.fSystemId = systemId;
    }
    
    public String getEncoding() {
        return this.fEncoding;
    }
    
    public void setEncoding(final String encoding) {
        this.fEncoding = encoding;
    }
}
