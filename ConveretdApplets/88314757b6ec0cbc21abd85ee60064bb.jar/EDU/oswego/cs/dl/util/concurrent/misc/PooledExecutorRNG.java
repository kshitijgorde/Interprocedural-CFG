// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Executor;
import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

class PooledExecutorRNG extends ExecutorRNG
{
    static final PooledExecutor exec;
    
    static {
        exec = Threads.pool;
    }
    
    public PooledExecutorRNG() {
        this.setDelegate(new PublicSynchRNG());
        this.setExecutor(PooledExecutorRNG.exec);
    }
}
