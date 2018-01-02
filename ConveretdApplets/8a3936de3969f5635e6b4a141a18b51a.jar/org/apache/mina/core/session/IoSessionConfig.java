// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

public interface IoSessionConfig
{
    int getReadBufferSize();
    
    void setReadBufferSize(final int p0);
    
    int getMinReadBufferSize();
    
    int getMaxReadBufferSize();
    
    int getThroughputCalculationInterval();
    
    int getIdleTime(final IdleStatus p0);
    
    long getIdleTimeInMillis(final IdleStatus p0);
    
    int getWriteTimeout();
    
    long getWriteTimeoutInMillis();
    
    boolean isUseReadOperation();
    
    void setAll(final IoSessionConfig p0);
}
