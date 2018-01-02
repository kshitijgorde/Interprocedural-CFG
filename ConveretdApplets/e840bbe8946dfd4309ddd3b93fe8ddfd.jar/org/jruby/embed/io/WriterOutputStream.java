// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.io;

import java.nio.charset.CoderResult;
import java.nio.CharBuffer;
import java.nio.ByteBuffer;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.Charset;
import java.io.OutputStreamWriter;
import java.nio.charset.CharsetDecoder;
import java.io.Writer;
import java.io.OutputStream;

public class WriterOutputStream extends OutputStream
{
    private final Writer writer;
    private boolean isOpen;
    private CharsetDecoder decoder;
    
    public WriterOutputStream(final Writer writer) {
        this(writer, null);
    }
    
    public WriterOutputStream(final Writer writer, String encoding) {
        this.isOpen = true;
        this.writer = writer;
        if (encoding == null && writer instanceof OutputStreamWriter) {
            encoding = ((OutputStreamWriter)writer).getEncoding();
        }
        if (encoding == null) {
            encoding = Charset.defaultCharset().name();
        }
        else if (!Charset.isSupported(encoding)) {
            throw new IllegalArgumentException(encoding + " is not supported");
        }
        (this.decoder = Charset.forName(encoding).newDecoder()).onMalformedInput(CodingErrorAction.REPLACE);
        this.decoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
    }
    
    public void close() throws IOException {
        synchronized (this.writer) {
            if (!this.isOpen) {
                throw new IOException("This stream has been already closed.");
            }
            this.isOpen = false;
            this.decoder = null;
            this.writer.close();
        }
    }
    
    public void flush() throws IOException {
        synchronized (this.writer) {
            if (!this.isOpen) {
                return;
            }
            this.writer.flush();
        }
    }
    
    public void write(final int b) throws IOException {
        final byte[] bb = { (byte)b };
        this.write(bb, 0, 1);
    }
    
    public void write(final byte[] b) throws IOException {
        this.write(b, 0, b.length);
    }
    
    public void write(final byte[] b, final int off, final int len) throws IOException {
        synchronized (this.writer) {
            if (!this.isOpen) {
                return;
            }
            if (off < 0 || len <= 0 || off + len > b.length) {
                throw new IndexOutOfBoundsException();
            }
            final ByteBuffer bytes = ByteBuffer.wrap(b, off, len);
            final CharBuffer chars = CharBuffer.allocate(len);
            this.byte2char(bytes, chars);
            final char[] cbuf = new char[chars.length()];
            chars.get(cbuf, 0, chars.length());
            this.writer.write(cbuf);
            this.writer.flush();
        }
    }
    
    private void byte2char(final ByteBuffer bytes, final CharBuffer chars) throws IOException {
        this.decoder.reset();
        chars.clear();
        final CoderResult result = this.decoder.decode(bytes, chars, true);
        if (result.isError() || result.isOverflow()) {
            throw new IOException(result.toString());
        }
        if (result.isUnderflow()) {
            chars.flip();
        }
    }
}
