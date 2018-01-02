// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.threadpool;

import java.util.Collections;
import org.jboss.util.collection.WeakValueHashMap;
import EDU.oswego.cs.dl.util.concurrent.ThreadFactory;
import EDU.oswego.cs.dl.util.concurrent.Channel;
import EDU.oswego.cs.dl.util.concurrent.Heap;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedBoolean;
import EDU.oswego.cs.dl.util.concurrent.BoundedLinkedQueue;
import org.jboss.logging.Logger;
import EDU.oswego.cs.dl.util.concurrent.SynchronizedInt;
import java.util.Map;

public class BasicThreadPool implements ThreadPool, BasicThreadPoolMBean
{
    private static final ThreadGroup JBOSS_THREAD_GROUP;
    private static final Map threadGroups;
    private static final SynchronizedInt lastPoolNumber;
    private static Logger log;
    private String name;
    private int poolNumber;
    private BlockingMode blockingMode;
    private MinPooledExecutor executor;
    private BoundedLinkedQueue queue;
    private ThreadGroup threadGroup;
    private SynchronizedInt lastThreadNumber;
    private SynchronizedBoolean stopped;
    private Heap tasksWithTimeouts;
    private TimeoutMonitor timeoutTask;
    private boolean trace;
    static /* synthetic */ Class class$org$jboss$util$threadpool$BasicThreadPool;
    
    public BasicThreadPool() {
        this("ThreadPool");
    }
    
    public BasicThreadPool(final String name) {
        this(name, BasicThreadPool.JBOSS_THREAD_GROUP);
    }
    
    public BasicThreadPool(final String name, final ThreadGroup threadGroup) {
        this.blockingMode = BlockingMode.ABORT;
        this.lastThreadNumber = new SynchronizedInt(0);
        this.stopped = new SynchronizedBoolean(false);
        this.tasksWithTimeouts = new Heap(13);
        this.trace = BasicThreadPool.log.isTraceEnabled();
        final ThreadFactory factory = (ThreadFactory)new ThreadPoolThreadFactory();
        this.queue = new BoundedLinkedQueue(1024);
        (this.executor = new MinPooledExecutor((Channel)this.queue, 100)).setMinimumPoolSize(100);
        this.executor.setKeepAliveTime(60000L);
        this.executor.setThreadFactory(factory);
        this.executor.abortWhenBlocked();
        this.poolNumber = BasicThreadPool.lastPoolNumber.increment();
        this.setName(name);
        this.threadGroup = threadGroup;
    }
    
    public void stop(final boolean immediate) {
        BasicThreadPool.log.debug("stop, immediate=" + immediate);
        this.stopped.set(true);
        if (immediate) {
            this.executor.shutdownNow();
        }
        else {
            this.executor.shutdownAfterProcessingCurrentlyQueuedTasks();
        }
    }
    
    public void waitForTasks() throws InterruptedException {
        this.executor.awaitTerminationAfterShutdown();
    }
    
    public void waitForTasks(final long maxWaitTime) throws InterruptedException {
        this.executor.awaitTerminationAfterShutdown(maxWaitTime);
    }
    
    public void runTaskWrapper(final TaskWrapper wrapper) {
        if (this.trace) {
            BasicThreadPool.log.trace("runTaskWrapper, wrapper=" + wrapper);
        }
        if (this.stopped.get()) {
            wrapper.rejectTask(new ThreadPoolStoppedException("Thread pool has been stopped"));
            return;
        }
        wrapper.acceptTask();
        final long completionTimeout = wrapper.getTaskCompletionTimeout();
        TimeoutInfo info = null;
        if (completionTimeout > 0L) {
            this.checkTimeoutMonitor();
            info = new TimeoutInfo(wrapper, completionTimeout);
            this.tasksWithTimeouts.insert((Object)info);
        }
        final int waitType = wrapper.getTaskWaitType();
        switch (waitType) {
            case 2: {
                this.executeOnThread(wrapper);
                break;
            }
            default: {
                this.execute(wrapper);
                break;
            }
        }
        this.waitForTask(wrapper);
    }
    
    public void runTask(final Task task) {
        final BasicTaskWrapper wrapper = new BasicTaskWrapper(task);
        this.runTaskWrapper(wrapper);
    }
    
    public void run(final Runnable runnable) {
        this.run(runnable, 0L, 0L);
    }
    
    public void run(final Runnable runnable, final long startTimeout, final long completeTimeout) {
        final RunnableTaskWrapper wrapper = new RunnableTaskWrapper(runnable, startTimeout, completeTimeout);
        this.runTaskWrapper(wrapper);
    }
    
    public ThreadGroup getThreadGroup() {
        return this.threadGroup;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public int getPoolNumber() {
        return this.poolNumber;
    }
    
    public String getThreadGroupName() {
        return this.threadGroup.getName();
    }
    
    public void setThreadGroupName(final String threadGroupName) {
        ThreadGroup group;
        synchronized (BasicThreadPool.threadGroups) {
            group = BasicThreadPool.threadGroups.get(threadGroupName);
            if (group == null) {
                group = new ThreadGroup(BasicThreadPool.JBOSS_THREAD_GROUP, threadGroupName);
                BasicThreadPool.threadGroups.put(threadGroupName, group);
            }
        }
        this.threadGroup = group;
    }
    
    public int getQueueSize() {
        return this.queue.size();
    }
    
    public int getMaximumQueueSize() {
        return this.queue.capacity();
    }
    
    public void setMaximumQueueSize(final int size) {
        this.queue.setCapacity(size);
    }
    
    public int getPoolSize() {
        return this.executor.getPoolSize();
    }
    
    public int getMinimumPoolSize() {
        return this.executor.getMinimumPoolSize();
    }
    
    public void setMinimumPoolSize(final int size) {
        synchronized (this.executor) {
            this.executor.setKeepAliveSize(size);
            if (this.executor.getMaximumPoolSize() < size) {
                this.executor.setMinimumPoolSize(size);
                this.executor.setMaximumPoolSize(size);
            }
        }
    }
    
    public int getMaximumPoolSize() {
        return this.executor.getMaximumPoolSize();
    }
    
    public void setMaximumPoolSize(final int size) {
        synchronized (this.executor) {
            this.executor.setMinimumPoolSize(size);
            this.executor.setMaximumPoolSize(size);
            if (this.executor.getKeepAliveSize() > size) {
                this.executor.setKeepAliveSize(size);
            }
        }
    }
    
    public long getKeepAliveTime() {
        return this.executor.getKeepAliveTime();
    }
    
    public void setKeepAliveTime(final long time) {
        this.executor.setKeepAliveTime(time);
    }
    
    public BlockingMode getBlockingMode() {
        return this.blockingMode;
    }
    
    public void setBlockingMode(final BlockingMode mode) {
        this.blockingMode = mode;
        if (this.blockingMode == BlockingMode.RUN) {
            this.executor.runWhenBlocked();
        }
        else if (this.blockingMode == BlockingMode.WAIT) {
            this.executor.waitWhenBlocked();
        }
        else if (this.blockingMode == BlockingMode.DISCARD) {
            this.executor.discardWhenBlocked();
        }
        else if (this.blockingMode == BlockingMode.DISCARD_OLDEST) {
            this.executor.discardOldestWhenBlocked();
        }
        else {
            if (this.blockingMode != BlockingMode.ABORT) {
                throw new IllegalArgumentException("Failed to recognize mode: " + mode);
            }
            this.executor.abortWhenBlocked();
        }
    }
    
    public void setBlockingMode(final String name) {
        this.blockingMode = BlockingMode.toBlockingMode(name);
        if (this.blockingMode == null) {
            this.blockingMode = BlockingMode.ABORT;
        }
    }
    
    public ThreadPool getInstance() {
        return this;
    }
    
    public void stop() {
        this.stop(false);
    }
    
    public String toString() {
        return this.name + '(' + this.poolNumber + ')';
    }
    
    protected void executeOnThread(final TaskWrapper wrapper) {
        if (this.trace) {
            BasicThreadPool.log.trace("executeOnThread, wrapper=" + wrapper);
        }
        wrapper.run();
    }
    
    protected void execute(final TaskWrapper wrapper) {
        if (this.trace) {
            BasicThreadPool.log.trace("execute, wrapper=" + wrapper);
        }
        try {
            this.executor.execute((Runnable)wrapper);
        }
        catch (Throwable t) {
            wrapper.rejectTask(new ThreadPoolFullException(t.toString()));
        }
    }
    
    protected void waitForTask(final TaskWrapper wrapper) {
        wrapper.waitForTask();
    }
    
    protected synchronized void checkTimeoutMonitor() {
        if (this.timeoutTask == null) {
            this.timeoutTask = new TimeoutMonitor(this.name, BasicThreadPool.log);
        }
    }
    
    protected TimeoutInfo getNextTimeout() {
        final TimeoutInfo info = (TimeoutInfo)this.tasksWithTimeouts.extract();
        return info;
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
        JBOSS_THREAD_GROUP = new ThreadGroup("JBoss Pooled Threads");
        threadGroups = Collections.synchronizedMap((Map<Object, Object>)new WeakValueHashMap());
        lastPoolNumber = new SynchronizedInt(0);
        BasicThreadPool.log = Logger.getLogger((BasicThreadPool.class$org$jboss$util$threadpool$BasicThreadPool == null) ? (BasicThreadPool.class$org$jboss$util$threadpool$BasicThreadPool = class$("org.jboss.util.threadpool.BasicThreadPool")) : BasicThreadPool.class$org$jboss$util$threadpool$BasicThreadPool);
    }
    
    private class ThreadPoolThreadFactory implements ThreadFactory
    {
        public Thread newThread(final Runnable runnable) {
            final String threadName = BasicThreadPool.this.toString() + "-" + BasicThreadPool.this.lastThreadNumber.increment();
            final Thread thread = new Thread(BasicThreadPool.this.threadGroup, runnable, threadName);
            thread.setDaemon(true);
            return thread;
        }
    }
    
    private static class TimeoutInfo implements Comparable
    {
        long start;
        long timeoutMS;
        TaskWrapper wrapper;
        boolean firstStop;
        
        TimeoutInfo(final TaskWrapper wrapper, final long timeout) {
            this.start = System.currentTimeMillis();
            this.timeoutMS = this.start + timeout;
            this.wrapper = wrapper;
        }
        
        public void setTimeout(final long timeout) {
            this.start = System.currentTimeMillis();
            this.timeoutMS = this.start + timeout;
        }
        
        public int compareTo(final Object o) {
            final TimeoutInfo ti = (TimeoutInfo)o;
            final long to0 = this.timeoutMS;
            final long to2 = ti.timeoutMS;
            final int diff = (int)(to0 - to2);
            return diff;
        }
        
        TaskWrapper getTaskWrapper() {
            return this.wrapper;
        }
        
        public long getTaskCompletionTimeout() {
            return this.wrapper.getTaskCompletionTimeout();
        }
        
        public long getTaskCompletionTimeout(final long now) {
            return this.timeoutMS - now;
        }
        
        public boolean stopTask() {
            this.wrapper.stopTask();
            final boolean wasFirstStop = !this.firstStop;
            this.firstStop = true;
            return wasFirstStop;
        }
    }
    
    private class TimeoutMonitor implements Runnable
    {
        final Logger log;
        
        TimeoutMonitor(final String name, final Logger log) {
            this.log = log;
            final Thread t = new Thread(this, name + " TimeoutMonitor");
            t.setDaemon(true);
            t.start();
        }
        
        public void run() {
            for (boolean isStopped = BasicThreadPool.this.stopped.get(); !isStopped; isStopped = BasicThreadPool.this.stopped.get()) {
                final boolean trace = this.log.isTraceEnabled();
                try {
                    final TimeoutInfo info = BasicThreadPool.this.getNextTimeout();
                    if (info != null) {
                        final long now = System.currentTimeMillis();
                        final long timeToTimeout = info.getTaskCompletionTimeout(now);
                        if (timeToTimeout > 0L) {
                            if (trace) {
                                this.log.trace("Will check wrapper=" + info.getTaskWrapper() + " after " + timeToTimeout);
                            }
                            Thread.sleep(timeToTimeout);
                        }
                        final TaskWrapper wrapper = info.getTaskWrapper();
                        if (!wrapper.isComplete()) {
                            if (trace) {
                                this.log.trace("Failed completion check for wrapper=" + wrapper);
                            }
                            if (info.stopTask()) {
                                info.setTimeout(1000L);
                                BasicThreadPool.this.tasksWithTimeouts.insert((Object)info);
                                if (trace) {
                                    this.log.trace("Rescheduled completion check for wrapper=" + wrapper);
                                }
                            }
                        }
                    }
                    else {
                        Thread.sleep(1000L);
                    }
                }
                catch (InterruptedException e) {
                    this.log.debug("Timeout monitor has been interrupted", e);
                }
                catch (Throwable e2) {
                    this.log.debug("Timeout monitor saw unexpected error", e2);
                }
            }
        }
    }
}
