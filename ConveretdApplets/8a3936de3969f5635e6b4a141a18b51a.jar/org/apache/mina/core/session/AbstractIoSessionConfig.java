// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

public abstract class AbstractIoSessionConfig implements IoSessionConfig
{
    private int minReadBufferSize;
    private int readBufferSize;
    private int maxReadBufferSize;
    private int idleTimeForRead;
    private int idleTimeForWrite;
    private int idleTimeForBoth;
    private int writeTimeout;
    private boolean useReadOperation;
    private int throughputCalculationInterval;
    
    protected AbstractIoSessionConfig() {
        this.minReadBufferSize = 64;
        this.readBufferSize = 2048;
        this.maxReadBufferSize = 65536;
        this.writeTimeout = 60;
        this.throughputCalculationInterval = 3;
    }
    
    @Override
    public final void setAll(final IoSessionConfig ioSessionConfig) {
        if (ioSessionConfig == null) {
            throw new IllegalArgumentException("config");
        }
        this.setReadBufferSize(ioSessionConfig.getReadBufferSize());
        final int minReadBufferSize = ioSessionConfig.getMinReadBufferSize();
        if (minReadBufferSize <= 0) {
            throw new IllegalArgumentException("minReadBufferSize: " + minReadBufferSize + " (expected: 1+)");
        }
        if (minReadBufferSize > this.maxReadBufferSize) {
            throw new IllegalArgumentException("minReadBufferSize: " + minReadBufferSize + " (expected: smaller than " + this.maxReadBufferSize + ')');
        }
        this.minReadBufferSize = minReadBufferSize;
        final int maxReadBufferSize = ioSessionConfig.getMaxReadBufferSize();
        if (maxReadBufferSize <= 0) {
            throw new IllegalArgumentException("maxReadBufferSize: " + maxReadBufferSize + " (expected: 1+)");
        }
        if (maxReadBufferSize < this.minReadBufferSize) {
            throw new IllegalArgumentException("maxReadBufferSize: " + maxReadBufferSize + " (expected: greater than " + this.minReadBufferSize + ')');
        }
        this.maxReadBufferSize = maxReadBufferSize;
        this.setIdleTime(IdleStatus.BOTH_IDLE, ioSessionConfig.getIdleTime(IdleStatus.BOTH_IDLE));
        this.setIdleTime(IdleStatus.READER_IDLE, ioSessionConfig.getIdleTime(IdleStatus.READER_IDLE));
        this.setIdleTime(IdleStatus.WRITER_IDLE, ioSessionConfig.getIdleTime(IdleStatus.WRITER_IDLE));
        final int writeTimeout = ioSessionConfig.getWriteTimeout();
        if (writeTimeout < 0) {
            throw new IllegalArgumentException("Illegal write timeout: " + writeTimeout);
        }
        this.writeTimeout = writeTimeout;
        this.useReadOperation = ioSessionConfig.isUseReadOperation();
        final int throughputCalculationInterval = ioSessionConfig.getThroughputCalculationInterval();
        if (throughputCalculationInterval < 0) {
            throw new IllegalArgumentException("throughputCalculationInterval: " + throughputCalculationInterval);
        }
        this.throughputCalculationInterval = throughputCalculationInterval;
        this.doSetAll(ioSessionConfig);
    }
    
    protected abstract void doSetAll(final IoSessionConfig p0);
    
    @Override
    public final int getReadBufferSize() {
        return this.readBufferSize;
    }
    
    @Override
    public final void setReadBufferSize(final int readBufferSize) {
        if (readBufferSize <= 0) {
            throw new IllegalArgumentException("readBufferSize: " + readBufferSize + " (expected: 1+)");
        }
        this.readBufferSize = readBufferSize;
    }
    
    @Override
    public final int getMinReadBufferSize() {
        return this.minReadBufferSize;
    }
    
    @Override
    public final int getMaxReadBufferSize() {
        return this.maxReadBufferSize;
    }
    
    @Override
    public final int getIdleTime(final IdleStatus idleStatus) {
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            return this.idleTimeForBoth;
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            return this.idleTimeForRead;
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            return this.idleTimeForWrite;
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }
    
    @Override
    public final long getIdleTimeInMillis(final IdleStatus idleStatus) {
        return this.getIdleTime(idleStatus) * 1000L;
    }
    
    private void setIdleTime(final IdleStatus idleStatus, final int idleTimeForWrite) {
        if (idleTimeForWrite < 0) {
            throw new IllegalArgumentException("Illegal idle time: " + idleTimeForWrite);
        }
        if (idleStatus == IdleStatus.BOTH_IDLE) {
            this.idleTimeForBoth = idleTimeForWrite;
            return;
        }
        if (idleStatus == IdleStatus.READER_IDLE) {
            this.idleTimeForRead = idleTimeForWrite;
            return;
        }
        if (idleStatus == IdleStatus.WRITER_IDLE) {
            this.idleTimeForWrite = idleTimeForWrite;
            return;
        }
        throw new IllegalArgumentException("Unknown idle status: " + idleStatus);
    }
    
    @Override
    public final int getWriteTimeout() {
        return this.writeTimeout;
    }
    
    @Override
    public final long getWriteTimeoutInMillis() {
        return this.writeTimeout * 1000L;
    }
    
    @Override
    public final boolean isUseReadOperation() {
        return this.useReadOperation;
    }
    
    @Override
    public final int getThroughputCalculationInterval() {
        return this.throughputCalculationInterval;
    }
}
