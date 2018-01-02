// 
// Decompiled by Procyon v0.5.30
// 

package anon.shared;

import java.io.IOException;

final class IOQueue
{
    private byte[] buff;
    private int readPos;
    private int writePos;
    private boolean bWriteClosed;
    private boolean bReadClosed;
    private static final int BUFF_SIZE = 10000;
    private boolean bFull;
    
    public IOQueue() {
        this.buff = new byte[10000];
        this.readPos = 0;
        this.writePos = 0;
        this.bWriteClosed = false;
        this.bReadClosed = false;
        this.bFull = false;
    }
    
    public synchronized void write(final byte[] array, int n, int i) throws IOException {
        while (i > 0) {
            if (this.bReadClosed || this.bWriteClosed) {
                throw new IOException("IOQueue closed");
            }
            if (this.bFull) {
                this.notify();
                try {
                    this.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    throw new IOException("IOQueue write interrupted");
                }
            }
            int n2;
            if (this.readPos <= this.writePos) {
                n2 = 10000 - this.writePos;
            }
            else {
                n2 = this.readPos - this.writePos;
            }
            if (n2 > i) {
                n2 = i;
            }
            System.arraycopy(array, n, this.buff, this.writePos, n2);
            n += n2;
            this.writePos += n2;
            i -= n2;
            if (this.writePos >= 10000) {
                this.writePos = 0;
            }
            if (this.readPos != this.writePos) {
                continue;
            }
            this.bFull = true;
        }
        this.notify();
    }
    
    public synchronized int read() throws IOException {
        while (!this.bReadClosed) {
            if (this.readPos == this.writePos && !this.bFull) {
                if (this.bWriteClosed) {
                    return -1;
                }
                this.notify();
                try {
                    this.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    throw new IOException("IOQueue read() interrupted");
                }
            }
            final int n = this.buff[this.readPos++] & 0xFF;
            if (this.readPos >= 10000) {
                this.readPos = 0;
            }
            if (this.bFull) {
                this.bFull = false;
                this.notify();
            }
            return n;
        }
        throw new IOException("IOQueue closed");
    }
    
    public synchronized int read(final byte[] array, final int n, final int n2) throws IOException {
        if (n2 <= 0) {
            return 0;
        }
        while (!this.bReadClosed) {
            if (this.readPos == this.writePos && !this.bFull) {
                if (this.bWriteClosed) {
                    return -1;
                }
                this.notify();
                try {
                    this.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    throw new IOException("IOQueue read() interrupted");
                }
            }
            int n3;
            if (this.writePos <= this.readPos) {
                n3 = 10000 - this.readPos;
            }
            else {
                n3 = this.writePos - this.readPos;
            }
            if (n3 > n2) {
                n3 = n2;
            }
            System.arraycopy(this.buff, this.readPos, array, n, n3);
            this.readPos += n3;
            if (this.readPos >= 10000) {
                this.readPos = 0;
            }
            if (this.bFull) {
                this.bFull = false;
                this.notify();
            }
            return n3;
        }
        throw new IOException("IOQueue closed");
    }
    
    public synchronized int available() {
        if (this.bFull) {
            return 10000;
        }
        if (this.readPos == this.writePos && !this.bFull) {
            return 0;
        }
        if (this.writePos <= this.readPos) {
            return 10000 - this.readPos;
        }
        return this.writePos - this.readPos;
    }
    
    public synchronized void closeWrite() {
        this.bWriteClosed = true;
        this.notify();
    }
    
    public synchronized void closeRead() {
        this.bReadClosed = true;
        this.notify();
    }
    
    public synchronized void finalize() throws Throwable {
        this.bReadClosed = true;
        this.bWriteClosed = true;
        this.notify();
        this.buff = null;
        super.finalize();
    }
}
