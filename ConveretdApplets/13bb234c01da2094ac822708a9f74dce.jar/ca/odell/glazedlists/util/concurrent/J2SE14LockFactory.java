// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

final class J2SE14LockFactory implements LockFactory
{
    public ReadWriteLock a() {
        return new J2SE14ReadWriteLock();
    }
}
