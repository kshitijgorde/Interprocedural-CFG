// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

class PublicSynchRNG extends NoSynchRNG
{
    public synchronized long get() {
        return this.internalGet();
    }
    
    public synchronized long next() {
        this.internalUpdate();
        return this.internalGet();
    }
    
    public synchronized void update() {
        this.internalUpdate();
    }
}
