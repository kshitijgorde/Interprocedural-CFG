// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent.misc;

import EDU.oswego.cs.dl.util.concurrent.TimeoutException;
import javax.swing.SwingUtilities;
import EDU.oswego.cs.dl.util.concurrent.TimedCallable;
import EDU.oswego.cs.dl.util.concurrent.Callable;
import java.lang.reflect.InvocationTargetException;
import EDU.oswego.cs.dl.util.concurrent.FutureResult;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactory;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactoryUser;

public abstract class SwingWorker extends ThreadFactoryUser implements Runnable
{
    private static final ThreadFactory FACTORY;
    private final FutureResult result;
    private final long timeout;
    private Thread thread;
    
    static {
        FACTORY = new ThreadFactory() {
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable);
                thread.setPriority(2);
                return thread;
            }
        };
    }
    
    public SwingWorker() {
        this(SwingWorker.FACTORY, 0L);
    }
    
    public SwingWorker(final long n) {
        this(SwingWorker.FACTORY, n);
    }
    
    protected SwingWorker(final ThreadFactory threadFactory, final long timeout) {
        this.result = new FutureResult();
        this.setThreadFactory(threadFactory);
        if (timeout < 0L) {
            throw new IllegalArgumentException("timeout=" + timeout);
        }
        this.timeout = timeout;
    }
    
    protected abstract Object construct() throws Exception;
    
    protected void finished() {
    }
    
    public Object get() throws InterruptedException, InvocationTargetException {
        return this.result.get();
    }
    
    public InvocationTargetException getException() {
        return this.result.getException();
    }
    
    public long getTimeout() {
        return this.timeout;
    }
    
    public synchronized void interrupt() {
        if (this.thread != null) {
            try {
                this.thread.interrupt();
            }
            catch (Exception ex) {}
        }
        this.result.setException(new InterruptedException());
    }
    
    public boolean isReady() {
        return this.result.isReady();
    }
    
    public void run() {
        Callable callable = new Callable() {
            public Object call() throws Exception {
                return SwingWorker.this.construct();
            }
        };
        final Runnable runnable = new Runnable() {
            public void run() {
                SwingWorker.this.finished();
            }
        };
        final long timeout = this.getTimeout();
        if (timeout != 0L) {
            final TimedCallable timedCallable = new TimedCallable(callable, timeout);
            timedCallable.setThreadFactory(this.getThreadFactory());
            callable = timedCallable;
        }
        this.result.setter(callable).run();
        SwingUtilities.invokeLater(runnable);
    }
    
    public synchronized void start() {
        if (this.thread == null) {
            this.thread = this.getThreadFactory().newThread(this);
        }
        this.thread.start();
    }
    
    public Object timedGet(final long n) throws TimeoutException, InterruptedException, InvocationTargetException {
        return this.result.timedGet(n);
    }
}
