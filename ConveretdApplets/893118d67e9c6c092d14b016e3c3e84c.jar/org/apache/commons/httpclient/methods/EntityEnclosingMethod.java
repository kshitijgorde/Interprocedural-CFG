// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.methods;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.commons.httpclient.ContentLengthInputStream;
import org.apache.commons.httpclient.ChunkedOutputStream;
import org.apache.commons.httpclient.ProtocolException;
import java.io.ByteArrayInputStream;
import org.apache.commons.httpclient.HttpVersion;
import org.apache.commons.httpclient.HttpException;
import java.io.IOException;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpConstants;
import java.io.InputStream;

public abstract class EntityEnclosingMethod extends ExpectContinueMethod
{
    public static final long CONTENT_LENGTH_AUTO = -2L;
    public static final long CONTENT_LENGTH_CHUNKED = -1L;
    private byte[] buffer;
    private InputStream requestStream;
    private String requestString;
    private byte[] contentCache;
    private int repeatCount;
    private long requestContentLength;
    
    public EntityEnclosingMethod() {
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2L;
        this.setFollowRedirects(false);
    }
    
    public EntityEnclosingMethod(final String uri) {
        super(uri);
        this.buffer = null;
        this.requestStream = null;
        this.requestString = null;
        this.contentCache = null;
        this.repeatCount = 0;
        this.requestContentLength = -2L;
        this.setFollowRedirects(false);
    }
    
    protected boolean hasRequestContent() {
        LOG.trace("enter EntityEnclosingMethod.hasRequestContent()");
        return this.buffer != null || this.requestStream != null || this.requestString != null;
    }
    
    protected void clearRequestBody() {
        LOG.trace("enter EntityEnclosingMethod.clearRequestBody()");
        this.requestStream = null;
        this.requestString = null;
        this.buffer = null;
        this.contentCache = null;
    }
    
    protected byte[] generateRequestBody() {
        LOG.trace("enter EntityEnclosingMethod.renerateRequestBody()");
        if (this.requestStream != null) {
            this.bufferContent();
        }
        if (this.buffer != null) {
            return this.buffer;
        }
        if (this.requestString != null) {
            return HttpConstants.getContentBytes(this.requestString, this.getRequestCharSet());
        }
        return null;
    }
    
    public boolean getFollowRedirects() {
        return false;
    }
    
    public void setFollowRedirects(final boolean followRedirects) {
        if (followRedirects) {
            throw new IllegalArgumentException("Entity enclosing requests cannot be redirected without user intervention");
        }
        super.setFollowRedirects(false);
    }
    
    public void setRequestContentLength(final int length) {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        this.requestContentLength = length;
    }
    
    public void setRequestContentLength(final long length) {
        LOG.trace("enter EntityEnclosingMethod.setRequestContentLength(int)");
        this.requestContentLength = length;
    }
    
    protected long getRequestContentLength() {
        LOG.trace("enter EntityEnclosingMethod.getRequestContentLength()");
        if (!this.hasRequestContent()) {
            return 0L;
        }
        if (this.requestContentLength != -2L) {
            return this.requestContentLength;
        }
        if (this.contentCache == null) {
            this.contentCache = this.generateRequestBody();
        }
        return (this.contentCache == null) ? 0 : this.contentCache.length;
    }
    
    protected void addRequestHeaders(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addRequestHeaders(HttpState, HttpConnection)");
        super.addRequestHeaders(state, conn);
        this.addContentLengthRequestHeader(state, conn);
    }
    
    protected void addContentLengthRequestHeader(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.addContentLengthRequestHeader(HttpState, HttpConnection)");
        if (this.getRequestHeader("content-length") == null && this.getRequestHeader("Transfer-Encoding") == null) {
            final long len = this.getRequestContentLength();
            if (len >= 0L) {
                this.addRequestHeader("Content-Length", String.valueOf(len));
            }
            else if (len == -1L && this.getHttpVersion().greaterEquals(HttpVersion.HTTP_1_1)) {
                this.addRequestHeader("Transfer-Encoding", "chunked");
            }
        }
    }
    
    public void setRequestBody(final InputStream body) {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(InputStream)");
        this.clearRequestBody();
        this.requestStream = body;
    }
    
    public InputStream getRequestBody() {
        LOG.trace("enter EntityEnclosingMethod.getRequestBody()");
        final byte[] content = this.generateRequestBody();
        if (content != null) {
            return new ByteArrayInputStream(content);
        }
        return new ByteArrayInputStream(new byte[0]);
    }
    
    public void setRequestBody(final String body) {
        LOG.trace("enter EntityEnclosingMethod.setRequestBody(String)");
        this.clearRequestBody();
        this.requestString = body;
    }
    
    public String getRequestBodyAsString() throws IOException {
        LOG.trace("enter EntityEnclosingMethod.getRequestBodyAsString()");
        final byte[] content = this.generateRequestBody();
        if (content != null) {
            return HttpConstants.getContentString(content, this.getRequestCharSet());
        }
        return null;
    }
    
    protected boolean writeRequestBody(final HttpState state, final HttpConnection conn) throws IOException, HttpException {
        LOG.trace("enter EntityEnclosingMethod.writeRequestBody(HttpState, HttpConnection)");
        if (!this.hasRequestContent()) {
            LOG.debug("Request body has not been specified");
            return true;
        }
        final long contentLength = this.getRequestContentLength();
        if (contentLength == -1L && this.getHttpVersion().lessEquals(HttpVersion.HTTP_1_0)) {
            throw new ProtocolException("Chunked transfer encoding not allowed for " + this.getHttpVersion().toString());
        }
        InputStream instream = null;
        if (this.requestStream != null) {
            LOG.debug("Using unbuffered request body");
            instream = this.requestStream;
        }
        else {
            if (this.contentCache == null) {
                this.contentCache = this.generateRequestBody();
            }
            if (this.contentCache != null) {
                LOG.debug("Using buffered request body");
                instream = new ByteArrayInputStream(this.contentCache);
            }
        }
        if (instream == null) {
            LOG.debug("Request body is empty");
            return true;
        }
        if (this.repeatCount > 0 && this.contentCache == null) {
            throw new ProtocolException("Unbuffered entity enclosing request can not be repeated.");
        }
        ++this.repeatCount;
        OutputStream outstream = conn.getRequestOutputStream();
        if (contentLength == -1L) {
            outstream = new ChunkedOutputStream(outstream);
        }
        if (contentLength >= 0L) {
            instream = new ContentLengthInputStream(instream, contentLength);
        }
        final byte[] tmp = new byte[4096];
        int total = 0;
        int i = 0;
        while ((i = instream.read(tmp)) >= 0) {
            outstream.write(tmp, 0, i);
            total += i;
        }
        if (outstream instanceof ChunkedOutputStream) {
            ((ChunkedOutputStream)outstream).writeClosingChunk();
        }
        if (contentLength > 0L && total < contentLength) {
            throw new IOException("Unexpected end of input stream after " + total + " bytes (expected " + contentLength + " bytes)");
        }
        LOG.debug("Request body sent");
        return true;
    }
    
    public void recycle() {
        LOG.trace("enter EntityEnclosingMethod.recycle()");
        this.clearRequestBody();
        this.requestContentLength = -2L;
        this.repeatCount = 0;
        super.recycle();
    }
    
    private void bufferContent() {
        LOG.trace("enter EntityEnclosingMethod.bufferContent()");
        if (this.buffer != null) {
            return;
        }
        if (this.requestStream != null) {
            try {
                final ByteArrayOutputStream tmp = new ByteArrayOutputStream();
                final byte[] data = new byte[4096];
                int l = 0;
                while ((l = this.requestStream.read(data)) >= 0) {
                    tmp.write(data, 0, l);
                }
                this.buffer = tmp.toByteArray();
                this.requestStream = null;
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
                this.buffer = null;
                this.requestStream = null;
            }
        }
    }
}
