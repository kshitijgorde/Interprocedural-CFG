// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Channel;

class ChanRNG extends DelegatedRNG
{
    boolean single_;
    
    ChanRNG() {
        this.setDelegate(new PublicSynchRNG());
    }
    
    public long consumerNext(final Channel channel) throws InterruptedException {
        RNG rng = null;
        if (super.cmode == 0) {
            rng = (RNG)channel.take();
        }
        else {
            while (rng == null) {
                rng = (RNG)channel.poll(super.waitTime);
            }
        }
        if (super.pcBias == 0) {
            rng.update();
        }
        else if (super.pcBias > 0) {
            rng.update();
            rng.update();
        }
        return rng.get();
    }
    
    public synchronized boolean isSingle() {
        return this.single_;
    }
    
    public long producerNext(final Channel channel) throws InterruptedException {
        RNG delegate = this.getDelegate();
        if (this.isSingle()) {
            channel.put(delegate);
            delegate = (RNG)channel.take();
            delegate.update();
        }
        else {
            if (super.pcBias < 0) {
                delegate.update();
                delegate.update();
            }
            else if (super.pcBias == 0) {
                delegate.update();
            }
            if (super.pmode == 0) {
                channel.put(delegate);
            }
            else {
                while (!channel.offer(delegate, super.waitTime)) {}
            }
        }
        return delegate.get();
    }
    
    public synchronized void setSingle(final boolean single_) {
        this.single_ = single_;
    }
}
