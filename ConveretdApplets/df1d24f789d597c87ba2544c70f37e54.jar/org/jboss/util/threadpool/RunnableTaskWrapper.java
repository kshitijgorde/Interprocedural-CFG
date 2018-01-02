// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

import org.jboss.logging.Logger;

public class RunnableTaskWrapper implements TaskWrapper
{
    private static final Logger log;
    private Runnable runnable;
    private boolean started;
    private Thread runThread;
    private long startTimeout;
    private long completionTimeout;
    static /* synthetic */ Class class$org$jboss$util$threadpool$RunnableTaskWrapper;
    
    public RunnableTaskWrapper(final Runnable runnable) {
        this(runnable, 0L, 0L);
    }
    
    public RunnableTaskWrapper(final Runnable runnable, final long startTimeout, final long completeTimeout) {
        if (runnable == null) {
            throw new IllegalArgumentException("Null runnable");
        }
        this.runnable = runnable;
        this.startTimeout = startTimeout;
        this.completionTimeout = completeTimeout;
    }
    
    public int getTaskWaitType() {
        return 0;
    }
    
    public int getTaskPriority() {
        return 5;
    }
    
    public long getTaskStartTimeout() {
        return this.startTimeout;
    }
    
    public long getTaskCompletionTimeout() {
        return this.completionTimeout;
    }
    
    public void acceptTask() {
    }
    
    public void rejectTask(final RuntimeException t) {
        throw t;
    }
    
    public void stopTask() {
        final boolean trace = RunnableTaskWrapper.log.isTraceEnabled();
        if (this.runThread != null && !this.runThread.isInterrupted()) {
            this.runThread.interrupt();
            if (trace) {
                RunnableTaskWrapper.log.trace("stopTask, interrupted thread=" + this.runThread);
            }
        }
        else if (this.runThread != null) {
            this.runThread.stop();
            if (trace) {
                RunnableTaskWrapper.log.trace("stopTask, stopped thread=" + this.runThread);
            }
        }
    }
    
    public void waitForTask() {
    }
    
    public boolean isComplete() {
        return this.started && this.runThread == null;
    }
    
    public void run() {
        final boolean trace = RunnableTaskWrapper.log.isTraceEnabled();
        try {
            if (trace) {
                RunnableTaskWrapper.log.trace("Begin run, wrapper=" + this);
            }
            this.runThread = Thread.currentThread();
            this.started = true;
            this.runnable.run();
            this.runThread = null;
            if (trace) {
                RunnableTaskWrapper.log.trace("End run, wrapper=" + this);
            }
        }
        catch (Throwable t) {
            RunnableTaskWrapper.log.warn("Unhandled throwable for runnable: " + this.runnable, t);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        log = Logger.getLogger((RunnableTaskWrapper.class$org$jboss$util$threadpool$RunnableTaskWrapper == null) ? (RunnableTaskWrapper.class$org$jboss$util$threadpool$RunnableTaskWrapper = class$("org.jboss.util.threadpool.RunnableTaskWrapper")) : RunnableTaskWrapper.class$org$jboss$util$threadpool$RunnableTaskWrapper);
    }
}
