// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.io;

import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.io.IOException;
import java.io.InputStream;

public class InputOutputStream extends InputStream
{
    private InternalOutputStream outputStream;
    private final byte[] buffer;
    private volatile int head;
    private volatile int tail;
    private boolean inputClosed;
    private boolean outputClosed;
    
    public InputOutputStream() throws IOException {
        this.buffer = new byte[5120];
        this.head = 0;
        this.tail = 0;
        this.outputStream = new InternalOutputStream();
        final boolean b = false;
        this.outputClosed = b;
        this.inputClosed = b;
    }
    
    public int read() throws IOException {
        synchronized (this.buffer) {
            while (this.head == this.tail && !this.inputClosed) {
                synchronized (this.buffer) {
                    try {
                        this.buffer.wait();
                    }
                    catch (InterruptedException ex) {
                        IOException ioException = null;
                        try {
                            final Constructor<IOException> constructor = IOException.class.getConstructor(Throwable.class);
                            ioException = constructor.newInstance(ex);
                            return (int)ioException;
                        }
                        catch (Exception ex2) {
                            ioException = new IOException(ex.toString());
                            return (int)ioException;
                        }
                        finally {
                            throw ioException;
                        }
                    }
                }
            }
            if (this.inputClosed) {
                throw new IOException("Stream is closed");
            }
            final byte next = this.buffer[this.head];
            this.head = (this.head + 1) % this.buffer.length;
            return next & 0xFF;
        }
    }
    
    public int read(final byte[] b, final int off, final int len) throws IOException {
        synchronized (this.buffer) {
            int available = this.available();
            if (available == 0) {
                synchronized (this.buffer) {
                    try {
                        this.buffer.wait();
                        available = this.available();
                    }
                    catch (InterruptedException ex) {
                        IOException ioException = null;
                        try {
                            final Constructor<IOException> constructor = IOException.class.getConstructor(Throwable.class);
                            ioException = constructor.newInstance(ex);
                            return (int)ioException;
                        }
                        catch (Exception ex2) {
                            ioException = new IOException(ex.toString());
                            return (int)ioException;
                        }
                        finally {
                            throw ioException;
                        }
                    }
                }
            }
            final int toRead = Math.min(len, available);
            final int ret = super.read(b, off, toRead);
            return ret;
        }
    }
    
    public void close() throws IOException {
        synchronized (this.buffer) {
            super.close();
            this.inputClosed = true;
            synchronized (this.buffer) {
                this.buffer.notifyAll();
            }
        }
    }
    
    public boolean isClosed() {
        return this.inputClosed;
    }
    
    public int available() throws IOException {
        synchronized (this.buffer) {
            return (this.tail >= this.head) ? (this.tail - this.head) : (this.tail + this.buffer.length - this.head);
        }
    }
    
    public OutputStream getOutputStream() {
        return this.outputStream;
    }
    
    private class InternalOutputStream extends OutputStream
    {
        public void write(final int b) throws IOException {
            synchronized (InputOutputStream.this.buffer) {
                if (InputOutputStream.this.outputClosed) {
                    throw new IOException("Stream is closed");
                }
                while (InputOutputStream.this.tail + 1 % InputOutputStream.this.buffer.length == InputOutputStream.this.head) {
                    Thread.yield();
                }
                InputOutputStream.this.buffer[InputOutputStream.this.tail] = (byte)b;
                InputOutputStream.this.tail = (InputOutputStream.this.tail + 1) % InputOutputStream.this.buffer.length;
                InputOutputStream.this.buffer.notify();
            }
        }
        
        public void write(final byte[] buffer, final int offset, final int length) throws IOException {
            synchronized (buffer) {
                super.write(buffer, offset, length);
            }
        }
        
        public void close() throws IOException {
            super.close();
            InputOutputStream.this.outputClosed = true;
        }
    }
}
