// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

public interface LockFactory
{
    public static final LockFactory a = new DelegateLockFactory();
    
    ReadWriteLock a();
}
