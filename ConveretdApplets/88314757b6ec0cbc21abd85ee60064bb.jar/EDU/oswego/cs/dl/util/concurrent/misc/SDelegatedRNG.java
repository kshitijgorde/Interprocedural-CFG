// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

class SDelegatedRNG extends DelegatedRNG
{
    public SDelegatedRNG() {
        this.setDelegate(new NoSynchRNG());
    }
    
    public synchronized long get() {
        return this.getDelegate().get();
    }
    
    public synchronized long next() {
        return this.getDelegate().next();
    }
    
    public synchronized void update() {
        this.getDelegate().update();
    }
}
