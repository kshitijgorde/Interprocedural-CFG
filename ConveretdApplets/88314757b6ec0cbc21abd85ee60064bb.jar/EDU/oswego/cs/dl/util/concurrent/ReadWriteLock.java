// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public interface ReadWriteLock
{
    Sync readLock();
    
    Sync writeLock();
}
