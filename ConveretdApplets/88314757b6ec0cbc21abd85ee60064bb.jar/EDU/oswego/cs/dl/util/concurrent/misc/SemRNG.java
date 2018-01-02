// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.Semaphore;

class SemRNG extends SyncDelegatedRNG
{
    public SemRNG() {
        super(new Semaphore(1L));
    }
}
