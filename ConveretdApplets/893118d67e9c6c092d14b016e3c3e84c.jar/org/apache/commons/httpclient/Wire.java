// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class Wire
{
    public static final boolean enabled() {
        return LOG.isDebugEnabled();
    }
    
    public static final void input(final InputStream instream) throws IOException {
        if (instream == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", instream);
    }
    
    public static final void input(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", new ByteArrayInputStream(b, off, len));
    }
    
    public static final void input(final byte[] b) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Input may not be null");
        }
        wire("<< ", new ByteArrayInputStream(b));
    }
    
    public static final void input(final int b) throws IOException {
        input(new byte[] { (byte)b });
    }
    
    public static final void input(final String s) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Input may noy be null");
        }
        input(s.getBytes());
    }
    
    public static final void output(final InputStream outstream) throws IOException {
        if (outstream == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", outstream);
    }
    
    public static final void output(final byte[] b, final int off, final int len) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", new ByteArrayInputStream(b, off, len));
    }
    
    public static final void output(final byte[] b) throws IOException {
        if (b == null) {
            throw new IllegalArgumentException("Output may not be null");
        }
        wire(">> ", new ByteArrayInputStream(b));
    }
    
    public static final void output(final int b) throws IOException {
        output(new byte[] { (byte)b });
    }
    
    public static final void output(final String s) throws IOException {
        if (s == null) {
            throw new IllegalArgumentException("Output may noy be null");
        }
        output(s.getBytes());
    }
    
    private static void wire(final String header, final InputStream instream) throws IOException {
        Reader reader = null;
        try {
            reader = new InputStreamReader(instream, "US-ASCII");
        }
        catch (UnsupportedEncodingException e) {
            reader = new InputStreamReader(instream);
        }
        final StringBuffer buffer = new StringBuffer();
        int ch;
        while ((ch = reader.read()) != -1) {
            if (ch == 13) {
                buffer.append("[\\r]");
            }
            else if (ch == 10) {
                buffer.append("[\\n]\"");
                buffer.insert(0, "\"");
                buffer.insert(0, header);
                LOG.debug(buffer.toString());
                buffer.setLength(0);
            }
            else if (ch < 32 || ch > 127) {
                buffer.append("[0x");
                buffer.append(Integer.toHexString(ch));
                buffer.append("]");
            }
            else {
                buffer.append((char)ch);
            }
        }
        if (buffer.length() > 0) {
            buffer.append("\"");
            buffer.insert(0, "\"");
            buffer.insert(0, header);
            LOG.debug(buffer.toString());
        }
    }
}
