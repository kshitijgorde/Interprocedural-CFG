// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods.multipart;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.commons.httpclient.HttpConstants;

public abstract class Part
{
    protected static final String BOUNDARY = "----------------314159265358979323846";
    protected static final byte[] BOUNDARY_BYTES;
    protected static final String CRLF = "\r\n";
    protected static final byte[] CRLF_BYTES;
    protected static final String QUOTE = "\"";
    protected static final byte[] QUOTE_BYTES;
    protected static final String EXTRA = "--";
    protected static final byte[] EXTRA_BYTES;
    protected static final String CONTENT_DISPOSITION = "Content-Disposition: form-data; name=";
    protected static final byte[] CONTENT_DISPOSITION_BYTES;
    protected static final String CONTENT_TYPE = "Content-Type: ";
    protected static final byte[] CONTENT_TYPE_BYTES;
    protected static final String CHARSET = "; charset=";
    protected static final byte[] CHARSET_BYTES;
    protected static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding: ";
    protected static final byte[] CONTENT_TRANSFER_ENCODING_BYTES;
    
    static {
        BOUNDARY_BYTES = HttpConstants.getAsciiBytes("----------------314159265358979323846");
        CRLF_BYTES = HttpConstants.getAsciiBytes("\r\n");
        QUOTE_BYTES = HttpConstants.getAsciiBytes("\"");
        EXTRA_BYTES = HttpConstants.getAsciiBytes("--");
        CONTENT_DISPOSITION_BYTES = HttpConstants.getAsciiBytes("Content-Disposition: form-data; name=");
        CONTENT_TYPE_BYTES = HttpConstants.getAsciiBytes("Content-Type: ");
        CHARSET_BYTES = HttpConstants.getAsciiBytes("; charset=");
        CONTENT_TRANSFER_ENCODING_BYTES = HttpConstants.getAsciiBytes("Content-Transfer-Encoding: ");
    }
    
    public static String getBoundary() {
        return "----------------314159265358979323846";
    }
    
    public abstract String getCharSet();
    
    public abstract String getContentType();
    
    public abstract String getName();
    
    public abstract String getTransferEncoding();
    
    public static long getLengthOfParts(final Part[] parts) throws IOException {
        LOG.trace("getLengthOfParts(Parts[])");
        if (parts == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        long total = 0L;
        for (int i = 0; i < parts.length; ++i) {
            total += parts[i].length();
        }
        total += Part.EXTRA_BYTES.length;
        total += Part.BOUNDARY_BYTES.length;
        total += Part.EXTRA_BYTES.length;
        total += Part.CRLF_BYTES.length;
        return total;
    }
    
    public long length() throws IOException {
        LOG.trace("enter length()");
        final ByteArrayOutputStream overhead = new ByteArrayOutputStream();
        this.sendStart(overhead);
        this.sendDispositionHeader(overhead);
        this.sendContentTypeHeader(overhead);
        this.sendTransferEncodingHeader(overhead);
        this.sendEndOfHeader(overhead);
        this.sendEnd(overhead);
        return overhead.size() + this.lengthOfData();
    }
    
    public void send(final OutputStream out) throws IOException {
        LOG.trace("enter send(OutputStream out)");
        this.sendStart(out);
        this.sendDispositionHeader(out);
        this.sendContentTypeHeader(out);
        this.sendTransferEncodingHeader(out);
        this.sendEndOfHeader(out);
        this.sendData(out);
        this.sendEnd(out);
    }
    
    public static void sendParts(final OutputStream out, final Part[] parts) throws IOException {
        LOG.trace("enter sendParts(OutputStream out, Parts[])");
        if (parts == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        for (int i = 0; i < parts.length; ++i) {
            parts[i].send(out);
        }
        out.write(Part.EXTRA_BYTES);
        out.write(Part.BOUNDARY_BYTES);
        out.write(Part.EXTRA_BYTES);
        out.write(Part.CRLF_BYTES);
    }
    
    public String toString() {
        return this.getName();
    }
    
    protected abstract long lengthOfData() throws IOException;
    
    protected void sendContentTypeHeader(final OutputStream out) throws IOException {
        LOG.trace("enter sendContentTypeHeader(OutputStream out)");
        final String contentType = this.getContentType();
        if (contentType != null) {
            out.write(Part.CRLF_BYTES);
            out.write(Part.CONTENT_TYPE_BYTES);
            out.write(HttpConstants.getAsciiBytes(contentType));
            final String charSet = this.getCharSet();
            if (charSet != null) {
                out.write(Part.CHARSET_BYTES);
                out.write(HttpConstants.getAsciiBytes(charSet));
            }
        }
    }
    
    protected abstract void sendData(final OutputStream p0) throws IOException;
    
    protected void sendDispositionHeader(final OutputStream out) throws IOException {
        LOG.trace("enter sendDispositionHeader(OutputStream out)");
        out.write(Part.CONTENT_DISPOSITION_BYTES);
        out.write(Part.QUOTE_BYTES);
        out.write(HttpConstants.getAsciiBytes(this.getName()));
        out.write(Part.QUOTE_BYTES);
    }
    
    protected void sendEnd(final OutputStream out) throws IOException {
        LOG.trace("enter sendEnd(OutputStream out)");
        out.write(Part.CRLF_BYTES);
    }
    
    protected void sendEndOfHeader(final OutputStream out) throws IOException {
        LOG.trace("enter sendEndOfHeader(OutputStream out)");
        out.write(Part.CRLF_BYTES);
        out.write(Part.CRLF_BYTES);
    }
    
    protected void sendStart(final OutputStream out) throws IOException {
        LOG.trace("enter sendStart(OutputStream out)");
        out.write(Part.EXTRA_BYTES);
        out.write(Part.BOUNDARY_BYTES);
        out.write(Part.CRLF_BYTES);
    }
    
    protected void sendTransferEncodingHeader(final OutputStream out) throws IOException {
        LOG.trace("enter sendTransferEncodingHeader(OutputStream out)");
        final String transferEncoding = this.getTransferEncoding();
        if (transferEncoding != null) {
            out.write(Part.CRLF_BYTES);
            out.write(Part.CONTENT_TRANSFER_ENCODING_BYTES);
            out.write(HttpConstants.getAsciiBytes(transferEncoding));
        }
    }
}
