// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class FIFOReadWriteLock implements ReadWriteLock
{
    protected final FIFOSemaphore entryLock;
    protected volatile int readers;
    protected int exreaders;
    protected final Sync readerSync;
    protected final Sync writerSync;
    
    public FIFOReadWriteLock() {
        this.entryLock = new FIFOSemaphore(1L);
        this.readerSync = new ReaderSync();
        this.writerSync = new WriterSync();
    }
    
    protected void acquireRead() throws InterruptedException {
        this.entryLock.acquire();
        ++this.readers;
        this.entryLock.release();
    }
    
    protected void acquireWrite() throws InterruptedException {
        this.entryLock.acquire();
        final int readers = this.readers;
        try {
            synchronized (this) {
                while (this.exreaders != readers) {
                    this.wait();
                }
            }
        }
        catch (InterruptedException ex) {
            this.entryLock.release();
            throw ex;
        }
    }
    
    protected boolean attemptRead(final long n) throws InterruptedException {
        if (!this.entryLock.attempt(n)) {
            return false;
        }
        ++this.readers;
        this.entryLock.release();
        return true;
    }
    
    protected boolean attemptWrite(final long n) throws InterruptedException {
        final long n2 = (n <= 0L) ? 0L : System.currentTimeMillis();
        if (!this.entryLock.attempt(n)) {
            return false;
        }
        final int readers = this.readers;
        try {
            synchronized (this) {
                while (this.exreaders != readers) {
                    final long n3 = (n <= 0L) ? 0L : (n - (System.currentTimeMillis() - n2));
                    if (n3 <= 0L) {
                        this.entryLock.release();
                        // monitorexit(this)
                        return false;
                    }
                    this.wait(n3);
                }
                return true;
            }
        }
        catch (InterruptedException ex) {
            this.entryLock.release();
            throw ex;
        }
    }
    
    public Sync readLock() {
        return this.readerSync;
    }
    
    protected synchronized void releaseRead() {
        if (++this.exreaders == this.readers) {
            this.notify();
        }
    }
    
    protected void releaseWrite() {
        this.entryLock.release();
    }
    
    public Sync writeLock() {
        return this.writerSync;
    }
    
    protected class ReaderSync implements Sync
    {
        public void acquire() throws InterruptedException {
            FIFOReadWriteLock.this.acquireRead();
        }
        
        public boolean attempt(final long n) throws InterruptedException {
            return FIFOReadWriteLock.this.attemptRead(n);
        }
        
        public void release() {
            FIFOReadWriteLock.this.releaseRead();
        }
    }
    
    protected class WriterSync implements Sync
    {
        public void acquire() throws InterruptedException {
            FIFOReadWriteLock.this.acquireWrite();
        }
        
        public boolean attempt(final long n) throws InterruptedException {
            return FIFOReadWriteLock.this.attemptWrite(n);
        }
        
        public void release() {
            FIFOReadWriteLock.this.releaseWrite();
        }
    }
}
