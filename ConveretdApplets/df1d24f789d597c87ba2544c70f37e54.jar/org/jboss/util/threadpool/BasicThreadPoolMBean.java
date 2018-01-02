// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public interface BasicThreadPoolMBean extends ThreadPoolMBean
{
    int getQueueSize();
    
    int getMaximumQueueSize();
    
    void setMaximumQueueSize(final int p0);
    
    BlockingMode getBlockingMode();
    
    void setBlockingMode(final BlockingMode p0);
    
    String getThreadGroupName();
    
    void setThreadGroupName(final String p0);
    
    long getKeepAliveTime();
    
    void setKeepAliveTime(final long p0);
}
