// 
// Decompiled by Procyon v0.5.30
// 

package ama;

import java.net.URLConnection;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.net.ContentHandler;

class MJPEGHandler extends ContentHandler
{
    private InputStream stream;
    private String boundary;
    private StringBuffer buffer;
    
    MJPEGHandler() {
        this.buffer = new StringBuffer(64);
    }
    
    private byte read() throws IOException {
        final int read = this.stream.read();
        if (read == -1) {
            throw new EOFException("End of File");
        }
        return (byte)read;
    }
    
    private void readLine() throws IOException {
        this.buffer.delete(0, this.buffer.length());
        for (byte b = this.read(); b != 13; b = this.read()) {
            this.buffer.append((char)b);
        }
        final byte read = this.read();
        if (read != 10) {
            System.err.println("Expected linefeed (10), got: " + read);
        }
    }
    
    private void readToken() throws IOException {
        this.buffer.delete(0, this.buffer.length());
        for (byte b = this.read(); b != 32; b = this.read()) {
            this.buffer.append((char)b);
        }
    }
    
    public Object getContent(final URLConnection urlConnection) throws IOException {
        this.stream = urlConnection.getInputStream();
        final String contentType = urlConnection.getContentType();
        if (!contentType.matches("^multipart/x-mixed-replace;\\sboundary=.+$")) {
            System.err.println("Wrong content type!");
            return null;
        }
        this.boundary = contentType.replaceFirst("^multipart/x-mixed-replace;\\sboundary=", "");
        return this;
    }
    
    public int getNextContentLength() throws IOException {
        this.readLine();
        while (true) {
            final int lastIndex = this.buffer.lastIndexOf(this.boundary);
            if (lastIndex == 0 || (lastIndex == 2 && this.buffer.lastIndexOf("--") == 0)) {
                break;
            }
            this.readLine();
        }
        this.readLine();
        if (this.buffer.lastIndexOf("Content-Type: image/jpeg") != 0) {
            System.err.println("Expected content type, got: " + (Object)this.buffer);
            return -1;
        }
        char c = '\0';
        this.readToken();
        if (this.buffer.lastIndexOf("Content-Length:") != 0) {
            System.err.println("Expected content length, got: " + (Object)this.buffer);
            return -1;
        }
        this.readLine();
        int i = this.buffer.length() - 1;
        for (char c2 = '\u0001'; i >= 0; --i, c2 *= '\n') {
            c += (char)((this.buffer.charAt(i) - '0') * c2);
        }
        return c;
    }
    
    public InputStream getNextContent() throws IOException {
        while (this.buffer.length() != 0 || this.buffer.lastIndexOf("Content-Type: ") == 0) {
            this.readLine();
        }
        return this.stream;
    }
    
    public void read(final byte[] array, final int n) throws IOException {
        this.getNextContent();
        for (int i = 0; i < n; i += this.stream.read(array, i, n - i)) {}
    }
}
