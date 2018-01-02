// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

import java.util.Iterator;
import java.util.Vector;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PooledExecutor extends ThreadFactoryUser implements Executor
{
    public static final int DEFAULT_MAXIMUMPOOLSIZE = Integer.MAX_VALUE;
    public static final int DEFAULT_MINIMUMPOOLSIZE = 1;
    public static final long DEFAULT_KEEPALIVETIME = 60000L;
    protected int maximumPoolSize_;
    protected int minimumPoolSize_;
    protected int poolSize_;
    protected long keepAliveTime_;
    protected boolean shutdown_;
    protected final Channel handOff_;
    protected final Map threads_;
    protected BlockedExecutionHandler blockedExecutionHandler_;
    
    public PooledExecutor() {
        this(new SynchronousChannel(), Integer.MAX_VALUE);
    }
    
    public PooledExecutor(final int n) {
        this(new SynchronousChannel(), n);
    }
    
    public PooledExecutor(final Channel channel) {
        this(channel, Integer.MAX_VALUE);
    }
    
    public PooledExecutor(final Channel handOff_, final int maximumPoolSize_) {
        this.maximumPoolSize_ = Integer.MAX_VALUE;
        this.minimumPoolSize_ = 1;
        this.poolSize_ = 0;
        this.keepAliveTime_ = 60000L;
        this.shutdown_ = false;
        this.maximumPoolSize_ = maximumPoolSize_;
        this.handOff_ = handOff_;
        this.runWhenBlocked();
        this.threads_ = new HashMap();
    }
    
    public void abortWhenBlocked() {
        this.setBlockedExecutionHandler((BlockedExecutionHandler)new AbortWhenBlocked());
    }
    
    protected void addThread(final Runnable runnable) {
        final Worker worker = new Worker(runnable);
        final Thread thread = this.getThreadFactory().newThread(worker);
        this.threads_.put(worker, thread);
        ++this.poolSize_;
        thread.start();
    }
    
    public synchronized void awaitTerminationAfterShutdown() throws InterruptedException {
        if (!this.shutdown_) {
            throw new IllegalStateException();
        }
        while (this.poolSize_ > 0) {
            this.wait();
        }
    }
    
    public synchronized boolean awaitTerminationAfterShutdown(final long n) throws InterruptedException {
        if (!this.shutdown_) {
            throw new IllegalStateException();
        }
        if (this.poolSize_ == 0) {
            return true;
        }
        long n2 = n;
        if (n2 <= 0L) {
            return false;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        do {
            this.wait(n2);
            if (this.poolSize_ == 0) {
                return true;
            }
            n2 = n - (System.currentTimeMillis() - currentTimeMillis);
        } while (n2 > 0L);
        return false;
    }
    
    public int createThreads(final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            synchronized (this) {
                if (this.poolSize_ >= this.maximumPoolSize_) {
                    // monitorexit(this)
                    break;
                }
                this.addThread(null);
                ++n2;
            }
        }
        return n2;
    }
    
    public void discardOldestWhenBlocked() {
        this.setBlockedExecutionHandler((BlockedExecutionHandler)new DiscardOldestWhenBlocked());
    }
    
    public void discardWhenBlocked() {
        this.setBlockedExecutionHandler((BlockedExecutionHandler)new DiscardWhenBlocked());
    }
    
    public List drain() {
        boolean b = false;
        final Vector<Object> vector = new Vector<Object>();
    Label_0010_Outer:
        while (true) {
            while (true) {
                try {
                    while (true) {
                        final Object poll = this.handOff_.poll(0L);
                        if (poll == null) {
                            break;
                        }
                        vector.addElement(poll);
                    }
                    break;
                }
                catch (InterruptedException ex) {
                    b = true;
                    continue Label_0010_Outer;
                }
                continue;
            }
        }
        if (b) {
            Thread.currentThread().interrupt();
        }
        return vector;
    }
    
    public void execute(final Runnable runnable) throws InterruptedException {
        do {
            synchronized (this) {
                if (this.shutdown_) {
                    continue;
                }
                final int poolSize_ = this.poolSize_;
                if (poolSize_ < this.minimumPoolSize_) {
                    this.addThread(runnable);
                    // monitorexit(this)
                    return;
                }
                if (this.handOff_.offer(runnable, 0L)) {
                    // monitorexit(this)
                    return;
                }
                if (poolSize_ < this.maximumPoolSize_) {
                    this.addThread(runnable);
                    // monitorexit(this)
                    return;
                }
                continue;
            }
        } while (!this.getBlockedExecutionHandler().blockedAction(runnable));
    }
    
    public synchronized BlockedExecutionHandler getBlockedExecutionHandler() {
        return this.blockedExecutionHandler_;
    }
    
    public synchronized long getKeepAliveTime() {
        return this.keepAliveTime_;
    }
    
    public synchronized int getMaximumPoolSize() {
        return this.maximumPoolSize_;
    }
    
    public synchronized int getMinimumPoolSize() {
        return this.minimumPoolSize_;
    }
    
    public synchronized int getPoolSize() {
        return this.poolSize_;
    }
    
    protected Runnable getTask() throws InterruptedException {
        final long n;
        synchronized (this) {
            if (this.poolSize_ > this.maximumPoolSize_) {
                // monitorexit(this)
                return null;
            }
            n = (this.shutdown_ ? 0L : this.keepAliveTime_);
        }
        if (n >= 0L) {
            return (Runnable)this.handOff_.poll(n);
        }
        return (Runnable)this.handOff_.take();
    }
    
    public synchronized void interruptAll() {
        final Iterator<Thread> iterator = this.threads_.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().interrupt();
        }
    }
    
    public synchronized boolean isTerminatedAfterShutdown() {
        return this.shutdown_ && this.poolSize_ == 0;
    }
    
    public void runWhenBlocked() {
        this.setBlockedExecutionHandler((BlockedExecutionHandler)new RunWhenBlocked());
    }
    
    public synchronized void setBlockedExecutionHandler(final BlockedExecutionHandler blockedExecutionHandler_) {
        this.blockedExecutionHandler_ = blockedExecutionHandler_;
    }
    
    public synchronized void setKeepAliveTime(final long keepAliveTime_) {
        this.keepAliveTime_ = keepAliveTime_;
    }
    
    public synchronized void setMaximumPoolSize(final int maximumPoolSize_) {
        if (maximumPoolSize_ <= 0) {
            throw new IllegalArgumentException();
        }
        this.maximumPoolSize_ = maximumPoolSize_;
    }
    
    public synchronized void setMinimumPoolSize(final int minimumPoolSize_) {
        if (minimumPoolSize_ < 0) {
            throw new IllegalArgumentException();
        }
        this.minimumPoolSize_ = minimumPoolSize_;
    }
    
    public void shutdownAfterProcessingCurrentlyQueuedTasks() {
        this.shutdownAfterProcessingCurrentlyQueuedTasks((BlockedExecutionHandler)new DiscardWhenBlocked());
    }
    
    public synchronized void shutdownAfterProcessingCurrentlyQueuedTasks(final BlockedExecutionHandler blockedExecutionHandler) {
        this.setBlockedExecutionHandler(blockedExecutionHandler);
        this.shutdown_ = true;
        if (this.poolSize_ == 0) {
            final boolean b = false;
            this.maximumPoolSize_ = (b ? 1 : 0);
            this.minimumPoolSize_ = (b ? 1 : 0);
        }
    }
    
    public void shutdownNow() {
        this.shutdownNow((BlockedExecutionHandler)new DiscardWhenBlocked());
    }
    
    public synchronized void shutdownNow(final BlockedExecutionHandler blockedExecutionHandler) {
        this.setBlockedExecutionHandler(blockedExecutionHandler);
        this.shutdown_ = true;
        final boolean b = false;
        this.maximumPoolSize_ = (b ? 1 : 0);
        this.minimumPoolSize_ = (b ? 1 : 0);
        this.interruptAll();
    }
    
    public void waitWhenBlocked() {
        this.setBlockedExecutionHandler((BlockedExecutionHandler)new WaitWhenBlocked());
    }
    
    protected synchronized void workerDone(final Worker worker) {
        this.threads_.remove(worker);
        final int poolSize_ = this.poolSize_ - 1;
        this.poolSize_ = poolSize_;
        if (poolSize_ == 0 && this.shutdown_) {
            final boolean b = false;
            this.minimumPoolSize_ = (b ? 1 : 0);
            this.maximumPoolSize_ = (b ? 1 : 0);
            this.notifyAll();
        }
        if (this.poolSize_ != 0) {
            if (this.poolSize_ >= this.minimumPoolSize_) {
                return;
            }
        }
        try {
            final Runnable runnable = (Runnable)this.handOff_.poll(0L);
            if (runnable != null && !this.shutdown_) {
                this.addThread(runnable);
            }
        }
        catch (InterruptedException ex) {}
    }
    
    protected class Worker implements Runnable
    {
        protected Runnable firstTask_;
        
        protected Worker(final Runnable firstTask_) {
            this.firstTask_ = firstTask_;
        }
        
        public void run() {
            try {
                final Runnable firstTask_ = this.firstTask_;
                this.firstTask_ = null;
                if (firstTask_ != null) {
                    firstTask_.run();
                }
                Runnable task;
                while ((task = PooledExecutor.this.getTask()) != null) {
                    task.run();
                }
            }
            catch (InterruptedException ex) {}
            finally {
                PooledExecutor.this.workerDone(this);
            }
        }
    }
    
    public interface BlockedExecutionHandler
    {
        boolean blockedAction(final Runnable p0) throws InterruptedException;
    }
    
    protected class RunWhenBlocked implements BlockedExecutionHandler
    {
        public boolean blockedAction(final Runnable runnable) {
            runnable.run();
            return true;
        }
    }
    
    protected class WaitWhenBlocked implements BlockedExecutionHandler
    {
        public boolean blockedAction(final Runnable runnable) throws InterruptedException {
            synchronized (PooledExecutor.this) {
                if (PooledExecutor.this.shutdown_) {
                    // monitorexit(this.this$0)
                    return true;
                }
            }
            // monitorexit(this.this$0)
            PooledExecutor.this.handOff_.put(runnable);
            return true;
        }
    }
    
    protected class DiscardWhenBlocked implements BlockedExecutionHandler
    {
        public boolean blockedAction(final Runnable runnable) {
            return true;
        }
    }
    
    protected class AbortWhenBlocked implements BlockedExecutionHandler
    {
        public boolean blockedAction(final Runnable runnable) {
            throw new RuntimeException("Pool is blocked");
        }
    }
    
    protected class DiscardOldestWhenBlocked implements BlockedExecutionHandler
    {
        public boolean blockedAction(final Runnable runnable) throws InterruptedException {
            PooledExecutor.this.handOff_.poll(0L);
            if (!PooledExecutor.this.handOff_.offer(runnable, 0L)) {
                runnable.run();
            }
            return true;
        }
    }
}
