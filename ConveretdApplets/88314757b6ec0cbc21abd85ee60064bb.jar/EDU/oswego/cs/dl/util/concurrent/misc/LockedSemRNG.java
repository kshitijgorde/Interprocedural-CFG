// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Executor;
import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.LockedExecutor;
import EDU.oswego.cs.dl.util.concurrent.Semaphore;

class LockedSemRNG extends ExecutorRNG
{
    public LockedSemRNG() {
        this.setDelegate(new NoSynchRNG());
        this.setExecutor(new LockedExecutor(new Semaphore(1L)));
    }
}
