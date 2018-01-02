// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.PrioritySemaphore;

class PrioritySemRNG extends SyncDelegatedRNG
{
    public PrioritySemRNG() {
        super(new PrioritySemaphore(1L));
    }
}
