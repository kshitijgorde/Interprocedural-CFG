// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl.java15;

import ca.odell.glazedlists.util.concurrent.Lock;

final class LockAdapter implements Lock
{
    private final java.util.concurrent.locks.Lock a;
    
    LockAdapter(final java.util.concurrent.locks.Lock a) {
        this.a = a;
    }
    
    public void a() {
        this.a.lock();
    }
    
    public void b() {
        this.a.unlock();
    }
}
