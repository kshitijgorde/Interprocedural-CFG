// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.ReadWriteLock;
import EDU.oswego.cs.dl.util.concurrent.WriterPreferenceReadWriteLock;

class WpRWlockRNG extends RWLockRNG
{
    public WpRWlockRNG() {
        super(new WriterPreferenceReadWriteLock());
    }
}
