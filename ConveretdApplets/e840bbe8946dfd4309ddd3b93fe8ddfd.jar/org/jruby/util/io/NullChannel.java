// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import java.io.IOException;
import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class NullChannel implements WritableByteChannel, ReadableByteChannel
{
    private boolean isOpen;
    
    public NullChannel() {
        this.isOpen = true;
    }
    
    public int write(final ByteBuffer buffer) throws IOException {
        if (!this.isOpen) {
            throw new EOFException();
        }
        final int length = buffer.remaining();
        buffer.position(buffer.position() + length);
        return length;
    }
    
    public boolean isOpen() {
        return this.isOpen;
    }
    
    public void close() throws IOException {
        this.isOpen = false;
    }
    
    public int read(final ByteBuffer dst) throws IOException {
        if (!this.isOpen) {
            throw new EOFException();
        }
        return -1;
    }
}
