// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.Mutex;

class MutexRNG extends SyncDelegatedRNG
{
    public MutexRNG() {
        super(new Mutex());
    }
}
