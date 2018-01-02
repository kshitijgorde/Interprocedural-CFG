// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class FIFOSemaphore extends QueuedSemaphore
{
    public FIFOSemaphore(final long n) {
        super((WaitQueue)new FIFOWaitQueue(), n);
    }
    
    protected static class FIFOWaitQueue extends WaitQueue
    {
        protected WaitNode head_;
        protected WaitNode tail_;
        
        protected FIFOWaitQueue() {
            this.head_ = null;
            this.tail_ = null;
        }
        
        protected WaitNode extract() {
            if (this.head_ == null) {
                return null;
            }
            final WaitNode head_ = this.head_;
            this.head_ = head_.next;
            if (this.head_ == null) {
                this.tail_ = null;
            }
            head_.next = null;
            return head_;
        }
        
        protected void insert(final WaitNode waitNode) {
            if (this.tail_ == null) {
                this.tail_ = waitNode;
                this.head_ = waitNode;
            }
            else {
                this.tail_.next = waitNode;
                this.tail_ = waitNode;
            }
        }
    }
}
