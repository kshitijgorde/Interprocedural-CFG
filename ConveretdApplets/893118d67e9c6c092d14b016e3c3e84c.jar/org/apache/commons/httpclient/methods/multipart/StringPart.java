// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods.multipart;

import java.io.OutputStream;
import java.io.IOException;
import org.apache.commons.httpclient.HttpConstants;

public class StringPart extends Part
{
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_CHARSET = "US-ASCII";
    public static final String DEFAULT_TRANSFER_ENCODING = "8bit";
    private String charset;
    private String name;
    private byte[] content;
    
    public StringPart(final String name, final String value, final String charset) {
        LOG.trace("enter StringPart(String, String, String)");
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        if (charset != null) {
            this.charset = charset;
        }
        else {
            this.charset = "US-ASCII";
        }
        if (value == null) {
            throw new IllegalArgumentException("Value may not be null");
        }
        if (value.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        }
        this.content = HttpConstants.getContentBytes(value, this.charset);
    }
    
    public StringPart(final String name, final String value) {
        this(name, value, null);
    }
    
    public String getCharSet() {
        return this.charset;
    }
    
    public String getContentType() {
        return "text/plain";
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTransferEncoding() {
        return "8bit";
    }
    
    protected long lengthOfData() throws IOException {
        LOG.trace("enter lengthOfData()");
        return this.content.length;
    }
    
    protected void sendData(final OutputStream out) throws IOException {
        LOG.trace("enter sendData(OutputStream)");
        out.write(this.content);
    }
}
