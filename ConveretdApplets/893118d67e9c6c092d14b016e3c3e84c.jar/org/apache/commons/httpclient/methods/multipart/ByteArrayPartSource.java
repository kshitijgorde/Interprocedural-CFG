// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods.multipart;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPartSource implements PartSource
{
    private String fileName;
    private byte[] bytes;
    
    public ByteArrayPartSource(final String fileName, final byte[] bytes) {
        this.fileName = fileName;
        this.bytes = bytes;
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public long getLength() {
        return this.bytes.length;
    }
    
    public InputStream createInputStream() throws IOException {
        return new ByteArrayInputStream(this.bytes);
    }
}
