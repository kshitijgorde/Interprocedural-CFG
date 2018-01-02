// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.Latch;

class ForcedStartRunnable implements Runnable
{
    protected final Latch latch_;
    protected final Runnable command_;
    
    ForcedStartRunnable(final Runnable command_) {
        this.latch_ = new Latch();
        this.command_ = command_;
    }
    
    public void run() {
        this.latch_.release();
        this.command_.run();
    }
    
    public Latch started() {
        return this.latch_;
    }
}
