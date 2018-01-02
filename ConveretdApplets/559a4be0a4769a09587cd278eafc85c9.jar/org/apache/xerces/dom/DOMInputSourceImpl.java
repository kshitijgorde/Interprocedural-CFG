// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.io.Reader;
import java.io.InputStream;
import org.w3c.dom.ls.DOMInputSource;

public class DOMInputSourceImpl implements DOMInputSource
{
    protected String fPublicId;
    protected String fSystemId;
    protected String fBaseSystemId;
    protected InputStream fByteStream;
    protected Reader fCharStream;
    protected String fData;
    protected String fEncoding;
    
    public DOMInputSourceImpl() {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
    }
    
    public DOMInputSourceImpl(final String publicId, final String systemId, final String baseSystemId) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
    }
    
    public DOMInputSourceImpl(final String publicId, final String systemId, final String baseSystemId, final InputStream byteStream, final String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fByteStream = byteStream;
        this.fEncoding = encoding;
    }
    
    public DOMInputSourceImpl(final String publicId, final String systemId, final String baseSystemId, final Reader charStream, final String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fCharStream = charStream;
        this.fEncoding = encoding;
    }
    
    public DOMInputSourceImpl(final String publicId, final String systemId, final String baseSystemId, final String data, final String encoding) {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fBaseSystemId = null;
        this.fByteStream = null;
        this.fCharStream = null;
        this.fData = null;
        this.fEncoding = null;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fData = data;
        this.fEncoding = encoding;
    }
    
    public InputStream getByteStream() {
        return this.fByteStream;
    }
    
    public void setByteStream(final InputStream byteStream) {
        this.fByteStream = byteStream;
    }
    
    public Reader getCharacterStream() {
        return this.fCharStream;
    }
    
    public void setCharacterStream(final Reader characterStream) {
        this.fCharStream = characterStream;
    }
    
    public String getStringData() {
        return this.fData;
    }
    
    public void setStringData(final String stringData) {
        this.fData = stringData;
    }
    
    public String getEncoding() {
        return this.fEncoding;
    }
    
    public void setEncoding(final String encoding) {
        this.fEncoding = encoding;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public void setPublicId(final String publicId) {
        this.fPublicId = publicId;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public void setSystemId(final String systemId) {
        this.fSystemId = systemId;
    }
    
    public String getBaseURI() {
        return this.fBaseSystemId;
    }
    
    public void setBaseURI(final String baseURI) {
        this.fBaseSystemId = baseURI;
    }
}
