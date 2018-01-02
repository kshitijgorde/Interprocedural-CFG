// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class QueuedExecutor extends ThreadFactoryUser implements Executor
{
    protected Thread thread_;
    protected static Runnable ENDTASK;
    protected volatile boolean shutdown_;
    protected final Channel queue_;
    protected final RunLoop runLoop_;
    
    static {
        QueuedExecutor.ENDTASK = new Runnable() {
            public void run() {
            }
        };
    }
    
    public QueuedExecutor() {
        this(new BoundedLinkedQueue());
    }
    
    public QueuedExecutor(final Channel queue_) {
        this.queue_ = queue_;
        this.runLoop_ = new RunLoop();
    }
    
    protected synchronized void clearThread() {
        this.thread_ = null;
    }
    
    public void execute(final Runnable runnable) throws InterruptedException {
        this.restart();
        this.queue_.put(runnable);
    }
    
    public synchronized Thread getThread() {
        return this.thread_;
    }
    
    public synchronized void restart() {
        if (this.thread_ == null && !this.shutdown_) {
            (this.thread_ = super.threadFactory_.newThread(this.runLoop_)).start();
        }
    }
    
    public synchronized void shutdownAfterProcessingCurrentTask() {
        this.shutdown_ = true;
        try {
            while (this.queue_.poll(0L) != null) {}
            this.queue_.put(QueuedExecutor.ENDTASK);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    public synchronized void shutdownAfterProcessingCurrentlyQueuedTasks() {
        if (!this.shutdown_) {
            try {
                this.queue_.put(QueuedExecutor.ENDTASK);
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public synchronized void shutdownNow() {
        this.shutdown_ = true;
        final Thread thread_ = this.thread_;
        if (thread_ != null) {
            thread_.interrupt();
        }
        this.shutdownAfterProcessingCurrentTask();
    }
    
    protected class RunLoop implements Runnable
    {
        public void run() {
            try {
                while (!QueuedExecutor.this.shutdown_) {
                    final Runnable runnable = (Runnable)QueuedExecutor.this.queue_.take();
                    if (runnable == QueuedExecutor.ENDTASK) {
                        QueuedExecutor.this.shutdown_ = true;
                        break;
                    }
                    if (runnable == null) {
                        break;
                    }
                    runnable.run();
                }
            }
            catch (InterruptedException ex) {}
            finally {
                QueuedExecutor.this.clearThread();
            }
        }
    }
}
