// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.embed.io;

import java.util.Iterator;
import java.nio.charset.CoderResult;
import java.util.List;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.nio.charset.CharsetEncoder;
import java.io.Reader;
import java.io.InputStream;

public class ReaderInputStream extends InputStream
{
    private static final int DEFAULT_CHAR_BUFFER_SIZE = 8192;
    private static final int DEFAULT_BYTE_BUFFER_SIZE = 8192;
    private final Reader reader;
    private byte[] bytes;
    private int totalBytes;
    private int position;
    private int markedIndex;
    private int readlimit;
    private boolean isOpen;
    private CharsetEncoder encoder;
    private final Object lock;
    
    public ReaderInputStream(final Reader reader) {
        this(reader, null);
    }
    
    public ReaderInputStream(final Reader reader, String encoding) {
        this.bytes = null;
        this.totalBytes = 0;
        this.position = 0;
        this.markedIndex = -1;
        this.readlimit = 0;
        this.isOpen = true;
        this.lock = new Object();
        this.reader = reader;
        if (encoding == null) {
            if (reader instanceof InputStreamReader) {
                encoding = ((InputStreamReader)reader).getEncoding();
            }
            else {
                encoding = Charset.defaultCharset().name();
            }
        }
        else if (!Charset.isSupported(encoding)) {
            throw new IllegalArgumentException(encoding + " is not supported");
        }
        (this.encoder = Charset.forName(encoding).newEncoder()).onMalformedInput(CodingErrorAction.REPLACE);
        this.encoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        try {
            this.fillByteBuffer(reader);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private void fillByteBuffer(final Reader reader) throws IOException {
        final CharBuffer cbuf = CharBuffer.allocate(8192);
        final ByteBuffer bbuf = ByteBuffer.allocate(8192);
        final List<byte[]> list = new ArrayList<byte[]>();
        while (true) {
            cbuf.clear();
            final int size = reader.read(cbuf);
            if (size <= 0) {
                break;
            }
            cbuf.limit(cbuf.position());
            cbuf.rewind();
            boolean eof = false;
            while (!eof) {
                final CoderResult cr = this.encoder.encode(cbuf, bbuf, eof);
                if (cr.isError()) {
                    cr.throwException();
                }
                else if (cr.isUnderflow()) {
                    this.appendBytes(list, bbuf);
                    eof = true;
                }
                else {
                    if (!cr.isOverflow()) {
                        continue;
                    }
                    this.appendBytes(list, bbuf);
                    bbuf.clear();
                }
            }
        }
        this.getByteArray(list);
    }
    
    private void appendBytes(final List<byte[]> list, final ByteBuffer bb) {
        bb.flip();
        final int length = bb.limit();
        this.totalBytes += length;
        final byte[] dst = new byte[length];
        System.arraycopy(bb.array(), bb.position(), dst, 0, length);
        list.add(dst);
    }
    
    private void getByteArray(final List<byte[]> list) {
        this.bytes = new byte[this.totalBytes];
        int index = 0;
        for (final byte[] bb : list) {
            for (int i = 0; i < bb.length; ++i) {
                this.bytes[index++] = bb[i];
            }
        }
    }
    
    private void confirmOpen() throws IOException {
        if (!this.isOpen) {
            throw new IOException("This stream has been closed.");
        }
    }
    
    public int available() throws IOException {
        synchronized (this.lock) {
            this.confirmOpen();
            if (this.bytes == null) {
                throw new IOException("This stream is not available.");
            }
            return this.totalBytes - this.position;
        }
    }
    
    public void close() throws IOException {
        synchronized (this.lock) {
            this.confirmOpen();
            this.isOpen = false;
            this.encoder = null;
            this.bytes = null;
        }
    }
    
    public synchronized void mark(final int readlimit) {
        if (readlimit < 0) {
            throw new IllegalArgumentException("Read limit < 0");
        }
        synchronized (this.lock) {
            if (this.isOpen) {
                this.readlimit = readlimit;
                this.markedIndex = this.position;
            }
        }
    }
    
    public boolean markSupported() {
        return true;
    }
    
    public int read() throws IOException {
        synchronized (this.lock) {
            this.confirmOpen();
            if (this.position >= this.totalBytes) {
                return -1;
            }
            return this.bytes[this.position++];
        }
    }
    
    public int read(final byte[] b) throws IOException {
        return this.read(b, 0, b.length);
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        synchronized (this.lock) {
            this.confirmOpen();
            if (len == 0) {
                return 0;
            }
            if (this.position >= this.totalBytes) {
                return -1;
            }
            if (off < 0 || off > this.totalBytes || len < 0) {
                throw new IllegalArgumentException("Either one of, or both of off and len are invalid.");
            }
            int start = this.position + off;
            start = ((start < this.totalBytes) ? start : (this.totalBytes - 1));
            int end = start + len;
            end = ((end < this.totalBytes) ? end : (this.totalBytes - 1));
            final int actuallyRead = Math.min(end - start + 1, len);
            System.arraycopy(this.bytes, start, b, 0, actuallyRead);
            this.position += actuallyRead;
            return actuallyRead;
        }
    }
    
    public synchronized void reset() throws IOException {
        synchronized (this.lock) {
            if (!this.isOpen) {
                throw new IOException("This stream has been closed.");
            }
            if (this.markedIndex < 0) {
                throw new IOException("This stream is not marked.");
            }
            if (this.position - this.markedIndex > this.readlimit) {
                throw new IOException("Mark is invalidated.");
            }
            this.position = this.markedIndex;
        }
    }
    
    public long skip(final long n) throws IOException {
        if (n < 0L) {
            throw new IllegalArgumentException("Negarive skip");
        }
        synchronized (this.lock) {
            if (!this.isOpen) {
                throw new IOException("This stream has been closed.");
            }
            long skipped;
            if (this.totalBytes - this.position < n) {
                skipped = this.totalBytes - this.position;
                this.position = this.totalBytes;
            }
            else {
                skipped = n;
                this.position += (int)n;
            }
            return skipped;
        }
    }
}
