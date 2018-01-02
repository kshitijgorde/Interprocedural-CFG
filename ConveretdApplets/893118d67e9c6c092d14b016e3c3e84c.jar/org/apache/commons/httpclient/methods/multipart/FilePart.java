// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods.multipart;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import org.apache.commons.httpclient.HttpConstants;

public class FilePart extends Part
{
    public static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    public static final String DEFAULT_CHARSET = "ISO-8859-1";
    public static final String DEFAULT_TRANSFER_ENCODING = "binary";
    protected static final String FILE_NAME = "; filename=";
    protected static final byte[] FILE_NAME_BYTES;
    private PartSource source;
    private String charset;
    private String contentType;
    private String name;
    
    static {
        FILE_NAME_BYTES = HttpConstants.getAsciiBytes("; filename=");
    }
    
    public FilePart(final String name, final PartSource partSource, final String contentType, final String charset) {
        LOG.trace("enter FilePart(String, PartSource, String, String)");
        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.name = name;
        if (partSource == null) {
            throw new IllegalArgumentException("Source may not be null");
        }
        if (partSource.getLength() < 0L) {
            throw new IllegalArgumentException("Source length must be >= 0");
        }
        this.source = partSource;
        if (contentType != null) {
            this.contentType = contentType;
        }
        else {
            this.contentType = "application/octet-stream";
        }
        this.charset = charset;
    }
    
    public FilePart(final String name, final PartSource partSource) {
        this(name, partSource, null, null);
    }
    
    public FilePart(final String name, final File file) throws FileNotFoundException {
        this(name, new FilePartSource(file), null, null);
    }
    
    public FilePart(final String name, final File file, final String contentType, final String charset) throws FileNotFoundException {
        this(name, new FilePartSource(file), contentType, charset);
    }
    
    public FilePart(final String name, final String fileName, final File file) throws FileNotFoundException {
        this(name, new FilePartSource(fileName, file), null, null);
    }
    
    public FilePart(final String name, final String fileName, final File file, final String contentType, final String charset) throws FileNotFoundException {
        this(name, new FilePartSource(fileName, file), contentType, charset);
    }
    
    public String getCharSet() {
        return this.charset;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTransferEncoding() {
        return "binary";
    }
    
    protected long lengthOfData() throws IOException {
        LOG.trace("enter lengthOfData()");
        return this.source.getLength();
    }
    
    protected void sendData(final OutputStream out) throws IOException {
        LOG.trace("enter sendData(OutputStream out)");
        if (this.lengthOfData() == 0L) {
            LOG.debug("No data to send.");
            return;
        }
        final byte[] tmp = new byte[4096];
        final InputStream instream = this.source.createInputStream();
        try {
            int len;
            while ((len = instream.read(tmp)) >= 0) {
                out.write(tmp, 0, len);
            }
        }
        finally {
            instream.close();
        }
        instream.close();
    }
    
    protected void sendDispositionHeader(final OutputStream out) throws IOException {
        LOG.trace("enter sendDispositionHeader(OutputStream out)");
        super.sendDispositionHeader(out);
        final String filename = this.source.getFileName();
        if (filename != null) {
            out.write(FilePart.FILE_NAME_BYTES);
            out.write(Part.QUOTE_BYTES);
            out.write(HttpConstants.getAsciiBytes(filename));
            out.write(Part.QUOTE_BYTES);
        }
    }
}
