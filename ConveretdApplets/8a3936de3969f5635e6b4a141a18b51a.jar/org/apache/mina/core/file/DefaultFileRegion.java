// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.file;

import java.nio.channels.FileChannel;

public class DefaultFileRegion implements FileRegion
{
    private final FileChannel channel;
    private long position;
    private long remainingBytes;
    
    public DefaultFileRegion(final FileChannel channel, final long position, final long remainingBytes) {
        if (channel == null) {
            throw new IllegalArgumentException("channel can not be null");
        }
        if (position < 0L) {
            throw new IllegalArgumentException("position may not be less than 0");
        }
        if (remainingBytes < 0L) {
            throw new IllegalArgumentException("remainingBytes may not be less than 0");
        }
        this.channel = channel;
        this.position = position;
        this.remainingBytes = remainingBytes;
    }
    
    @Override
    public final long getRemainingBytes() {
        return this.remainingBytes;
    }
    
    @Override
    public final FileChannel getFileChannel() {
        return this.channel;
    }
    
    @Override
    public final long getPosition() {
        return this.position;
    }
    
    @Override
    public final void update(final long n) {
        this.position += n;
        this.remainingBytes -= n;
    }
}
