// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Sync;
import EDU.oswego.cs.dl.util.concurrent.WaiterPreferenceSemaphore;

class WpSemRNG extends SyncDelegatedRNG
{
    public WpSemRNG() {
        super(new WaiterPreferenceSemaphore(1L));
    }
}
