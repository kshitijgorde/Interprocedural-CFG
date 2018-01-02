// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

import com.ibm.xml.b2b.util.XMLString;
import java.io.Reader;
import java.io.InputStream;

public final class EntityInputSource
{
    private String fPublicId;
    private String fSystemId;
    private String fEncoding;
    private InputStream fStream;
    private Reader fReader;
    private String fBaseURI;
    private XMLString fContent;
    
    public EntityInputSource() {
    }
    
    public EntityInputSource(final String fPublicId, final String fSystemId) {
        this.fPublicId = fPublicId;
        this.fSystemId = fSystemId;
    }
    
    public void clear() {
        this.fPublicId = null;
        this.fSystemId = null;
        this.fEncoding = null;
        this.fStream = null;
        this.fReader = null;
        this.fBaseURI = null;
        this.fContent = null;
    }
    
    public void setPublicId(final String fPublicId) {
        this.fPublicId = fPublicId;
    }
    
    public String getPublicId() {
        return this.fPublicId;
    }
    
    public void setSystemId(final String fSystemId) {
        this.fSystemId = fSystemId;
    }
    
    public String getSystemId() {
        return this.fSystemId;
    }
    
    public void setEncoding(final String fEncoding) {
        this.fEncoding = fEncoding;
    }
    
    public String getEncoding() {
        return this.fEncoding;
    }
    
    public void setByteStream(final InputStream fStream) {
        this.fStream = fStream;
    }
    
    public InputStream getByteStream() {
        return this.fStream;
    }
    
    public void setCharacterStream(final Reader fReader) {
        this.fReader = fReader;
    }
    
    public Reader getCharacterStream() {
        return this.fReader;
    }
    
    public void setBaseURI(final String fBaseURI) {
        this.fBaseURI = fBaseURI;
    }
    
    public String getBaseURI() {
        return this.fBaseURI;
    }
    
    public void setContent(final XMLString fContent) {
        this.fContent = fContent;
    }
    
    public XMLString getContent() {
        return this.fContent;
    }
}
