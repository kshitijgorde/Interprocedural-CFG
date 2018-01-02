// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.io;

import java.io.IOException;
import java.util.Locale;
import org.apache.xerces.util.MessageFormatter;
import java.io.InputStream;
import java.io.Reader;

public class ASCIIReader extends Reader
{
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    protected InputStream fInputStream;
    protected byte[] fBuffer;
    private MessageFormatter fFormatter;
    private Locale fLocale;
    
    public ASCIIReader(final InputStream inputStream, final MessageFormatter messageFormatter, final Locale locale) {
        this(inputStream, 2048, messageFormatter, locale);
    }
    
    public ASCIIReader(final InputStream inputStream, final int size, final MessageFormatter messageFormatter, final Locale locale) {
        this.fFormatter = null;
        this.fLocale = null;
        this.fInputStream = inputStream;
        this.fBuffer = new byte[size];
        this.fFormatter = messageFormatter;
        this.fLocale = locale;
    }
    
    public int read() throws IOException {
        final int b0 = this.fInputStream.read();
        if (b0 > 128) {
            throw new IOException(this.fFormatter.formatMessage(this.fLocale, "InvalidASCII", new Object[] { Integer.toString(b0) }));
        }
        return b0;
    }
    
    public int read(final char[] ch, final int offset, int length) throws IOException {
        if (length > this.fBuffer.length) {
            length = this.fBuffer.length;
        }
        final int count = this.fInputStream.read(this.fBuffer, 0, length);
        for (int i = 0; i < count; ++i) {
            final int b0 = this.fBuffer[i];
            if (b0 > 128) {
                throw new IOException(this.fFormatter.formatMessage(this.fLocale, "InvalidASCII", new Object[] { Integer.toString(b0) }));
            }
            ch[offset + i] = (char)b0;
        }
        return count;
    }
    
    public long skip(final long n) throws IOException {
        return this.fInputStream.skip(n);
    }
    
    public boolean ready() throws IOException {
        return false;
    }
    
    public boolean markSupported() {
        return this.fInputStream.markSupported();
    }
    
    public void mark(final int readAheadLimit) throws IOException {
        this.fInputStream.mark(readAheadLimit);
    }
    
    public void reset() throws IOException {
        this.fInputStream.reset();
    }
    
    public void close() throws IOException {
        this.fInputStream.close();
    }
}
