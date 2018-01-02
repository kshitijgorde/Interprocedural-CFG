// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public final class IoServiceStatistics
{
    private final AtomicLong readBytes;
    private final AtomicLong writtenBytes;
    private final AtomicLong readMessages;
    private final AtomicLong writtenMessages;
    private long lastReadTime;
    private long lastWriteTime;
    private final AtomicInteger scheduledWriteBytes;
    private final AtomicInteger scheduledWriteMessages;
    
    public IoServiceStatistics(final AbstractIoService abstractIoService) {
        this.readBytes = new AtomicLong();
        this.writtenBytes = new AtomicLong();
        this.readMessages = new AtomicLong();
        this.writtenMessages = new AtomicLong();
        this.scheduledWriteBytes = new AtomicInteger();
        this.scheduledWriteMessages = new AtomicInteger();
        new Object();
    }
    
    public final long getLastReadTime() {
        return this.lastReadTime;
    }
    
    public final long getLastWriteTime() {
        return this.lastWriteTime;
    }
    
    protected final void setLastReadTime(final long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }
    
    protected final void setLastWriteTime(final long lastWriteTime) {
        this.lastWriteTime = lastWriteTime;
    }
    
    public final void increaseReadBytes(final long n, final long lastReadTime) {
        this.readBytes.addAndGet(n);
        this.lastReadTime = lastReadTime;
    }
    
    public final void increaseReadMessages(final long lastReadTime) {
        this.readMessages.incrementAndGet();
        this.lastReadTime = lastReadTime;
    }
    
    public final void increaseWrittenBytes(final int n, final long lastWriteTime) {
        this.writtenBytes.addAndGet(n);
        this.lastWriteTime = lastWriteTime;
    }
    
    public final void increaseWrittenMessages(final long lastWriteTime) {
        this.writtenMessages.incrementAndGet();
        this.lastWriteTime = lastWriteTime;
    }
    
    public final void increaseScheduledWriteBytes(final int n) {
        this.scheduledWriteBytes.addAndGet(n);
    }
    
    public final void increaseScheduledWriteMessages() {
        this.scheduledWriteMessages.incrementAndGet();
    }
    
    public final void decreaseScheduledWriteMessages() {
        this.scheduledWriteMessages.decrementAndGet();
    }
}
