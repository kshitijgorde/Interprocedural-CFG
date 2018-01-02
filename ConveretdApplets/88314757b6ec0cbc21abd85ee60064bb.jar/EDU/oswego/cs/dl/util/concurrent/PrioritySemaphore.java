// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class PrioritySemaphore extends QueuedSemaphore
{
    public PrioritySemaphore(final long n) {
        super((WaitQueue)new PriorityWaitQueue(), n);
    }
    
    protected static class PriorityWaitQueue extends WaitQueue
    {
        protected final FIFOSemaphore.FIFOWaitQueue[] cells_;
        protected int maxIndex_;
        
        protected PriorityWaitQueue() {
            this.cells_ = new FIFOSemaphore.FIFOWaitQueue[10];
            this.maxIndex_ = -1;
            for (int i = 0; i < this.cells_.length; ++i) {
                this.cells_[i] = new FIFOSemaphore.FIFOWaitQueue();
            }
        }
        
        protected WaitNode extract() {
            while (true) {
                final int maxIndex_ = this.maxIndex_;
                if (maxIndex_ < 0) {
                    return null;
                }
                final WaitNode extract = this.cells_[maxIndex_].extract();
                if (extract != null) {
                    return extract;
                }
                --this.maxIndex_;
            }
        }
        
        protected void insert(final WaitNode waitNode) {
            final int maxIndex_ = Thread.currentThread().getPriority() - 1;
            this.cells_[maxIndex_].insert(waitNode);
            if (maxIndex_ > this.maxIndex_) {
                this.maxIndex_ = maxIndex_;
            }
        }
    }
}
