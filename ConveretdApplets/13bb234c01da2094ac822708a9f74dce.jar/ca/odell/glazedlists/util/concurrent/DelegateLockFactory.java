// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.util.concurrent;

class DelegateLockFactory implements LockFactory
{
    private LockFactory b;
    
    DelegateLockFactory() {
        try {
            Class.forName("java.util.concurrent.locks.ReadWriteLock");
            this.b = (LockFactory)Class.forName("ca.odell.glazedlists.impl.java15.J2SE50LockFactory").newInstance();
        }
        catch (Throwable t) {
            this.b = new J2SE14LockFactory();
        }
    }
    
    public ReadWriteLock a() {
        return this.b.a();
    }
}
