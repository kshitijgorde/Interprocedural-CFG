// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.player;

public interface DataConsumer extends Runnable
{
    void setPlugin(final Plugin p0);
    
    void consume(final MediaBuffer p0);
    
    long getQueuedTime();
    
    boolean isReady();
    
    void stop();
    
    void setEOS();
}
