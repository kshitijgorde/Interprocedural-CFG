// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.HashMap;

public class ReentrantWriterPreferenceReadWriteLock extends WriterPreferenceReadWriteLock
{
    protected long writeHolds_;
    protected HashMap readers_;
    protected static final Integer IONE;
    
    static {
        IONE = new Integer(1);
    }
    
    public ReentrantWriterPreferenceReadWriteLock() {
        this.writeHolds_ = 0L;
        this.readers_ = new HashMap();
    }
    
    protected boolean allowReader() {
        return (super.activeWriter_ == null && super.waitingWriters_ == 0L) || super.activeWriter_ == Thread.currentThread();
    }
    
    protected synchronized Signaller endRead() {
        final Thread currentThread = Thread.currentThread();
        final Object value = this.readers_.get(currentThread);
        if (value == null) {
            throw new IllegalStateException();
        }
        --super.activeReaders_;
        if (value != ReentrantWriterPreferenceReadWriteLock.IONE) {
            final int n = (int)value - 1;
            this.readers_.put(currentThread, (n == 1) ? ReentrantWriterPreferenceReadWriteLock.IONE : new Integer(n));
            return null;
        }
        this.readers_.remove(currentThread);
        if (this.writeHolds_ > 0L) {
            return null;
        }
        if (super.activeReaders_ == 0L && super.waitingWriters_ > 0L) {
            return super.writerLock_;
        }
        return null;
    }
    
    protected synchronized Signaller endWrite() {
        --this.writeHolds_;
        if (this.writeHolds_ > 0L) {
            return null;
        }
        super.activeWriter_ = null;
        if (super.waitingReaders_ > 0L && this.allowReader()) {
            return super.readerLock_;
        }
        if (super.waitingWriters_ > 0L) {
            return super.writerLock_;
        }
        return null;
    }
    
    protected synchronized boolean startRead() {
        final Thread currentThread = Thread.currentThread();
        final Object value = this.readers_.get(currentThread);
        if (value != null) {
            this.readers_.put(currentThread, new Integer((int)value + 1));
            ++super.activeReaders_;
            return true;
        }
        if (this.allowReader()) {
            this.readers_.put(currentThread, ReentrantWriterPreferenceReadWriteLock.IONE);
            ++super.activeReaders_;
            return true;
        }
        return false;
    }
    
    protected synchronized boolean startWrite() {
        if (super.activeWriter_ == Thread.currentThread()) {
            ++this.writeHolds_;
            return true;
        }
        if (this.writeHolds_ != 0L) {
            return false;
        }
        if (super.activeReaders_ == 0L || (this.readers_.size() == 1 && this.readers_.get(Thread.currentThread()) != null)) {
            super.activeWriter_ = Thread.currentThread();
            this.writeHolds_ = 1L;
            return true;
        }
        return false;
    }
}
