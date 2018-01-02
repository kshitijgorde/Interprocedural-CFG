// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.FIFOSemaphore;

class FifoRNG extends SyncDelegatedRNG
{
    public FifoRNG() {
        super(new FIFOSemaphore(1L));
    }
}
