// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.cipher;

import java.util.Random;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.io.InputStream;

public class CipherInputStream extends InputStream
{
    private GZIPInputStream in;
    
    public CipherInputStream(final InputStream inputStream) throws IOException {
        this(inputStream, null);
    }
    
    public CipherInputStream(final InputStream inputStream, final byte[] array) throws IOException {
        this.in = new GZIPInputStream(new RawCipherInputStream(inputStream, array));
    }
    
    public int read() throws IOException {
        return this.in.read();
    }
    
    public int read(final byte[] array) throws IOException {
        return this.in.read(array);
    }
    
    public int read(final byte[] array, final int n, final int n2) throws IOException {
        return this.in.read(array, n, n2);
    }
    
    public int available() throws IOException {
        return this.in.available();
    }
    
    public void close() throws IOException {
        this.in.close();
    }
    
    public void mark(final int n) {
        this.in.mark(n);
    }
    
    public boolean markSupported() {
        return this.in.markSupported();
    }
    
    public void reset() throws IOException {
        this.in.reset();
    }
    
    public long skip(final long n) throws IOException {
        return this.in.skip(n);
    }
    
    class RawCipherInputStream extends FilterInputStream
    {
        private int x;
        private int y;
        private byte[] state;
        
        RawCipherInputStream(final CipherInputStream cipherInputStream, final InputStream inputStream, byte[] array) throws IOException {
            super(inputStream);
            this.state = new byte[256];
            int n = 0;
            int n2 = 0;
            if (array == null || array.length == 0) {
                array = new byte[256];
                new Random(347509238L).nextBytes(array);
            }
            for (int i = 0; i < 256; ++i) {
                this.state[i] = (byte)i;
            }
            for (int j = 0; j < 256; ++j) {
                final byte b = this.state[j];
                n2 = (n2 + array[n] + b & 0xFF);
                final byte b2 = this.state[n2];
                this.state[n2] = (byte)(b & 0xFF);
                this.state[j] = (byte)(b2 & 0xFF);
                if (++n >= array.length) {
                    n = 0;
                }
            }
        }
        
        private final int crypt() {
            final int x = this.x + 1 & 0xFF;
            final byte b = this.state[x];
            final int y = b + this.y & 0xFF;
            final byte b2 = this.state[y];
            this.x = x;
            this.y = y;
            this.state[y] = (byte)(b & 0xFF);
            this.state[x] = (byte)(b2 & 0xFF);
            return this.state[b + b2 & 0xFF];
        }
        
        public int read() throws IOException {
            int n = super.read() ^ this.crypt();
            if (n < 0) {
                n += 256;
            }
            return n;
        }
        
        public int read(final byte[] array) throws IOException {
            return this.read(array, 0, array.length);
        }
        
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            final int read = super.read(array, n, n2);
            for (int i = 0; i < read; ++i) {
                array[i + n] = (byte)((array[i + n] ^ this.crypt()) & 0xFF);
            }
            return read;
        }
    }
}
