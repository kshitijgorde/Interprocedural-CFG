// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

import java.io.IOException;
import com.fluendo.utils.Debug;
import java.io.InputStream;

public class StoreInputStream extends InputStream
{
    private InputStream inputStream;
    private boolean eos;
    private ByteCollector storedStream;
    private int streamPos;
    private int displayCounter;
    private int displayCounterMax;
    private int maxSize;
    
    public StoreInputStream(final InputStream inputStream) {
        this.streamPos = 0;
        this.displayCounter = 0;
        this.displayCounterMax = 1024;
        this.maxSize = 3145728;
        this.realInit(inputStream, 1048576);
    }
    
    public StoreInputStream(final InputStream inputStream, final int n) {
        this.streamPos = 0;
        this.displayCounter = 0;
        this.displayCounterMax = 1024;
        this.maxSize = 3145728;
        this.realInit(inputStream, n);
    }
    
    private synchronized void realInit(final InputStream inputStream, final int n) {
        this.inputStream = inputStream;
        this.eos = false;
        Debug.log(3, "==>reading from stream");
        Debug.log(3, "TAILLE: " + n);
        this.storedStream = new ByteCollector(n);
    }
    
    public synchronized int read() throws IOException {
        Debug.log(3, "==>read()!");
        return -1;
    }
    
    public synchronized int available() throws IOException {
        return this.inputStream.available();
    }
    
    public synchronized void close() {
    }
    
    public synchronized void mark(final int n) {
        this.inputStream.mark(n);
    }
    
    public synchronized boolean markSupported() {
        return this.inputStream.markSupported();
    }
    
    public synchronized int read(final byte[] array) throws IOException {
        return this.inputStream.read(array);
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        if (this.eos) {
            return this.storedStream.read(array, n, n2);
        }
        final int read = this.inputStream.read(array, n, n2);
        if (read == -1) {
            this.eos = true;
            return -1;
        }
        if (read != 0) {
            final int currentSize = this.storedStream.getCurrentSize();
            if (currentSize < this.maxSize) {
                int n3;
                if (currentSize + read > this.maxSize) {
                    n3 = this.maxSize - currentSize;
                }
                else {
                    n3 = read;
                }
                this.storedStream.append(array, n, n3);
            }
        }
        return read;
    }
    
    public synchronized void reset() throws IOException {
        Debug.log(4, "==>reset !");
        this.inputStream.reset();
    }
    
    public synchronized long skip(final long n) throws IOException {
        Debug.log(4, "==>skip !");
        return this.inputStream.skip(n);
    }
}
