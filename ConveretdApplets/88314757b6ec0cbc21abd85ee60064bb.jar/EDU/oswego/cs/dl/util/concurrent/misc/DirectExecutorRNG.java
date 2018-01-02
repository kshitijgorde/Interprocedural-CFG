// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Executor;
import EDU.oswego.cs.dl.util.concurrent.DirectExecutor;

class DirectExecutorRNG extends ExecutorRNG
{
    public DirectExecutorRNG() {
        this.setDelegate(new PublicSynchRNG());
        this.setExecutor(new DirectExecutor());
    }
}
