// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.ssh;

public final class SSHPduQueue
{
    static final int SSH_QUEUE_DEPTH = 512;
    static final int SSH_QUEUE_HIWATER = 384;
    Object[] queue;
    Object queueLock;
    boolean isWaitGet;
    boolean isWaitPut;
    int rOffset;
    int wOffset;
    int maxQueueDepth;
    
    public SSHPduQueue() {
        this.queue = new Object[513];
        this.queueLock = new Object();
        this.isWaitGet = false;
        this.isWaitPut = false;
        this.rOffset = 0;
        this.wOffset = 0;
        this.maxQueueDepth = 512;
    }
    
    public void setMaxDepth(final int maxDepth) {
        synchronized (this.queueLock) {
            this.maxQueueDepth = maxDepth;
        }
    }
    
    public void putLast(final SSHPdu pdu) {
        synchronized (this.queueLock) {
            this.putFlowControl();
            this.queue[this.wOffset++] = pdu;
            if (this.wOffset == 513) {
                this.wOffset = 0;
            }
            if (this.isWaitGet) {
                this.queueLock.notify();
            }
        }
    }
    
    public void putFirst(final SSHPdu pdu) {
        synchronized (this.queueLock) {
            this.putFlowControl();
            --this.rOffset;
            if (this.rOffset == -1) {
                this.rOffset = 512;
            }
            this.queue[this.rOffset] = pdu;
            if (this.isWaitGet) {
                this.queueLock.notify();
            }
        }
    }
    
    public void release() {
        synchronized (this.queueLock) {
            if (this.isWaitGet) {
                this.queueLock.notify();
            }
        }
    }
    
    public boolean isEmpty() {
        final boolean isEmpty;
        synchronized (this.queueLock) {
            isEmpty = (this.rOffset == this.wOffset);
        }
        return isEmpty;
    }
    
    private final void putFlowControl() {
        final int fs = this.freeSpace();
        if (fs == 512 - this.maxQueueDepth) {
            this.isWaitPut = true;
        }
        if (this.isWaitPut) {
            try {
                this.queueLock.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private final int freeSpace() {
        int fSpc = this.rOffset - this.wOffset;
        if (fSpc <= 0) {
            fSpc += 513;
        }
        return --fSpc;
    }
    
    public SSHPdu getFirst() {
        SSHPdu pdu = null;
        synchronized (this.queueLock) {
            if (this.isEmpty()) {
                this.isWaitGet = true;
                try {
                    this.queueLock.wait();
                }
                catch (InterruptedException ex) {}
            }
            this.isWaitGet = false;
            pdu = (SSHPdu)this.queue[this.rOffset];
            this.queue[this.rOffset++] = null;
            if (this.rOffset == 513) {
                this.rOffset = 0;
            }
            if (this.isWaitPut && this.freeSpace() > 128) {
                this.queueLock.notifyAll();
                this.isWaitPut = false;
            }
        }
        return pdu;
    }
}
