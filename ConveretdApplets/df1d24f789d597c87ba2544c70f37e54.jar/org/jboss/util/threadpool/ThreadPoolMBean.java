// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

public interface ThreadPoolMBean
{
    String getName();
    
    void setName(final String p0);
    
    int getPoolNumber();
    
    int getMinimumPoolSize();
    
    void setMinimumPoolSize(final int p0);
    
    int getMaximumPoolSize();
    
    void setMaximumPoolSize(final int p0);
    
    ThreadPool getInstance();
    
    void stop();
}
