// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

import EDU.oswego.cs.dl.util.concurrent.Channel;
import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

public class MinPooledExecutor extends PooledExecutor
{
    protected int keepAliveSize;
    
    public MinPooledExecutor(final int poolSize) {
        super(poolSize);
    }
    
    public MinPooledExecutor(final Channel channel, final int poolSize) {
        super(channel, poolSize);
    }
    
    public int getKeepAliveSize() {
        return this.keepAliveSize;
    }
    
    public void setKeepAliveSize(final int keepAliveSize) {
        this.keepAliveSize = keepAliveSize;
    }
    
    protected Runnable getTask() throws InterruptedException {
        Runnable task;
        for (task = super.getTask(); task == null && this.keepAlive(); task = super.getTask()) {}
        return task;
    }
    
    protected synchronized boolean keepAlive() {
        return !this.shutdown_ && this.poolSize_ <= this.keepAliveSize;
    }
}
