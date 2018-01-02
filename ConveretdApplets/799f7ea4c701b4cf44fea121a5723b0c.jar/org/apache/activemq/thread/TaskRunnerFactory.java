// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executor;

public class TaskRunnerFactory implements Executor
{
    private ExecutorService executor;
    private int maxIterationsPerRun;
    private String name;
    private int priority;
    private boolean daemon;
    private AtomicLong id;
    
    public TaskRunnerFactory() {
        this("ActiveMQ Task", 5, true, 1000);
    }
    
    private TaskRunnerFactory(final String name, final int priority, final boolean daemon, final int maxIterationsPerRun) {
        this(name, priority, daemon, maxIterationsPerRun, false);
    }
    
    public TaskRunnerFactory(final String name, final int priority, final boolean daemon, final int maxIterationsPerRun, final boolean dedicatedTaskRunner) {
        this.id = new AtomicLong(0L);
        this.name = name;
        this.priority = priority;
        this.daemon = daemon;
        this.maxIterationsPerRun = maxIterationsPerRun;
        if (dedicatedTaskRunner || "true".equalsIgnoreCase(System.getProperty("org.apache.activemq.UseDedicatedTaskRunner"))) {
            this.executor = null;
        }
        else {
            this.executor = this.createDefaultExecutor();
        }
    }
    
    public void shutdown() {
        if (this.executor != null) {
            this.executor.shutdownNow();
        }
    }
    
    public TaskRunner createTaskRunner(final Task task, final String name) {
        if (this.executor != null) {
            return new PooledTaskRunner(this.executor, task, this.maxIterationsPerRun);
        }
        return new DedicatedTaskRunner(task, name, this.priority, this.daemon);
    }
    
    @Override
    public void execute(final Runnable runnable) {
        this.execute(runnable, "ActiveMQ Task");
    }
    
    public void execute(final Runnable runnable, final String name) {
        if (this.executor != null) {
            this.executor.execute(runnable);
        }
        else {
            new Thread(runnable, name + "-" + this.id.incrementAndGet()).start();
        }
    }
    
    protected ExecutorService createDefaultExecutor() {
        final ThreadPoolExecutor rc = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable runnable) {
                final Thread thread = new Thread(runnable, TaskRunnerFactory.this.name + "-" + TaskRunnerFactory.this.id.incrementAndGet());
                thread.setDaemon(TaskRunnerFactory.this.daemon);
                thread.setPriority(TaskRunnerFactory.this.priority);
                return thread;
            }
        });
        return rc;
    }
}
