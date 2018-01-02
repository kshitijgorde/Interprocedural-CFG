// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class WriterPreferenceReadWriteLock implements ReadWriteLock
{
    protected long activeReaders_;
    protected Thread activeWriter_;
    protected long waitingReaders_;
    protected long waitingWriters_;
    protected final ReaderLock readerLock_;
    protected final WriterLock writerLock_;
    
    public WriterPreferenceReadWriteLock() {
        this.activeReaders_ = 0L;
        this.activeWriter_ = null;
        this.waitingReaders_ = 0L;
        this.waitingWriters_ = 0L;
        this.readerLock_ = new ReaderLock();
        this.writerLock_ = new WriterLock();
    }
    
    protected boolean allowReader() {
        return this.activeWriter_ == null && this.waitingWriters_ == 0L;
    }
    
    protected synchronized void cancelledWaitingReader() {
        --this.waitingReaders_;
    }
    
    protected synchronized void cancelledWaitingWriter() {
        --this.waitingWriters_;
    }
    
    protected synchronized Signaller endRead() {
        final long activeReaders_ = this.activeReaders_ - 1L;
        this.activeReaders_ = activeReaders_;
        if (activeReaders_ == 0L && this.waitingWriters_ > 0L) {
            return (Signaller)this.writerLock_;
        }
        return null;
    }
    
    protected synchronized Signaller endWrite() {
        this.activeWriter_ = null;
        if (this.waitingReaders_ > 0L && this.allowReader()) {
            return (Signaller)this.readerLock_;
        }
        if (this.waitingWriters_ > 0L) {
            return (Signaller)this.writerLock_;
        }
        return null;
    }
    
    public Sync readLock() {
        return this.readerLock_;
    }
    
    protected synchronized boolean startRead() {
        final boolean allowReader = this.allowReader();
        if (allowReader) {
            ++this.activeReaders_;
        }
        return allowReader;
    }
    
    protected synchronized boolean startReadFromNewReader() {
        final boolean startRead = this.startRead();
        if (!startRead) {
            ++this.waitingReaders_;
        }
        return startRead;
    }
    
    protected synchronized boolean startReadFromWaitingReader() {
        final boolean startRead = this.startRead();
        if (startRead) {
            --this.waitingReaders_;
        }
        return startRead;
    }
    
    protected synchronized boolean startWrite() {
        final boolean b = this.activeWriter_ == null && this.activeReaders_ == 0L;
        if (b) {
            this.activeWriter_ = Thread.currentThread();
        }
        return b;
    }
    
    protected synchronized boolean startWriteFromNewWriter() {
        final boolean startWrite = this.startWrite();
        if (!startWrite) {
            ++this.waitingWriters_;
        }
        return startWrite;
    }
    
    protected synchronized boolean startWriteFromWaitingWriter() {
        final boolean startWrite = this.startWrite();
        if (startWrite) {
            --this.waitingWriters_;
        }
        return startWrite;
    }
    
    public Sync writeLock() {
        return this.writerLock_;
    }
    
    protected abstract class Signaller
    {
        abstract void signalWaiters();
    }
    
    protected class ReaderLock extends Signaller implements Sync
    {
        public void acquire() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            InterruptedException ex = null;
            synchronized (this) {
                if (!WriterPreferenceReadWriteLock.this.startReadFromNewReader()) {
                    try {
                        do {
                            this.wait();
                        } while (!WriterPreferenceReadWriteLock.this.startReadFromWaitingReader());
                        // monitorexit(this)
                        return;
                    }
                    catch (InterruptedException ex2) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
                        ex = ex2;
                    }
                }
            }
            if (ex != null) {
                WriterPreferenceReadWriteLock.this.writerLock_.signalWaiters();
                throw ex;
            }
        }
        
        public boolean attempt(final long n) throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            InterruptedException ex = null;
            synchronized (this) {
                if (n <= 0L) {
                    // monitorexit(this)
                    return WriterPreferenceReadWriteLock.this.startRead();
                }
                if (WriterPreferenceReadWriteLock.this.startReadFromNewReader()) {
                    // monitorexit(this)
                    return true;
                }
                long n2 = n;
                final long currentTimeMillis = System.currentTimeMillis();
                while (true) {
                    try {
                        this.wait(n2);
                    }
                    catch (InterruptedException ex2) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
                        ex = ex2;
                        break;
                    }
                    if (WriterPreferenceReadWriteLock.this.startReadFromWaitingReader()) {
                        // monitorexit(this)
                        return true;
                    }
                    n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                    if (n2 <= 0L) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingReader();
                        break;
                    }
                }
            }
            WriterPreferenceReadWriteLock.this.writerLock_.signalWaiters();
            if (ex != null) {
                throw ex;
            }
            return false;
        }
        
        public void release() {
            final Signaller endRead = WriterPreferenceReadWriteLock.this.endRead();
            if (endRead != null) {
                endRead.signalWaiters();
            }
        }
        
        synchronized void signalWaiters() {
            this.notifyAll();
        }
    }
    
    protected class WriterLock extends Signaller implements Sync
    {
        public void acquire() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            InterruptedException ex = null;
            synchronized (this) {
                if (!WriterPreferenceReadWriteLock.this.startWriteFromNewWriter()) {
                    try {
                        do {
                            this.wait();
                        } while (!WriterPreferenceReadWriteLock.this.startWriteFromWaitingWriter());
                        // monitorexit(this)
                        return;
                    }
                    catch (InterruptedException ex2) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
                        this.notify();
                        ex = ex2;
                    }
                }
            }
            if (ex != null) {
                WriterPreferenceReadWriteLock.this.readerLock_.signalWaiters();
                throw ex;
            }
        }
        
        public boolean attempt(final long n) throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            InterruptedException ex = null;
            synchronized (this) {
                if (n <= 0L) {
                    // monitorexit(this)
                    return WriterPreferenceReadWriteLock.this.startWrite();
                }
                if (WriterPreferenceReadWriteLock.this.startWriteFromNewWriter()) {
                    // monitorexit(this)
                    return true;
                }
                long n2 = n;
                final long currentTimeMillis = System.currentTimeMillis();
                while (true) {
                    try {
                        this.wait(n2);
                    }
                    catch (InterruptedException ex2) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
                        this.notify();
                        ex = ex2;
                        break;
                    }
                    if (WriterPreferenceReadWriteLock.this.startWriteFromWaitingWriter()) {
                        // monitorexit(this)
                        return true;
                    }
                    n2 = n - (System.currentTimeMillis() - currentTimeMillis);
                    if (n2 <= 0L) {
                        WriterPreferenceReadWriteLock.this.cancelledWaitingWriter();
                        this.notify();
                        break;
                    }
                }
            }
            WriterPreferenceReadWriteLock.this.readerLock_.signalWaiters();
            if (ex != null) {
                throw ex;
            }
            return false;
        }
        
        public void release() {
            final Signaller endWrite = WriterPreferenceReadWriteLock.this.endWrite();
            if (endWrite != null) {
                endWrite.signalWaiters();
            }
        }
        
        synchronized void signalWaiters() {
            this.notify();
        }
    }
}
