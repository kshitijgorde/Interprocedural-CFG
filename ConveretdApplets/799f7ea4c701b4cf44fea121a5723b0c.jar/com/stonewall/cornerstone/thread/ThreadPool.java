// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.thread;

import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import org.xmodel.log.Log;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Stack;
import java.util.List;

public class ThreadPool
{
    final int minThreads;
    final int maxThreads;
    private final String name;
    private final List<PooledThread> threads;
    private final Stack<Object> toStringStack;
    private final ReentrantLock lock;
    static final Log log;
    public static final int AnyThread = -1;
    
    static {
        log = Log.getLog(ThreadPool.class);
    }
    
    public ThreadPool(final int maxThreads) {
        this("", maxThreads);
    }
    
    public ThreadPool(final String name, final int maxThreads) {
        this(name, (maxThreads > 0) ? 1 : 0, maxThreads);
    }
    
    public ThreadPool(final String name, int minThreads, int maxThreads) {
        this.toStringStack = new Stack<Object>();
        this.lock = new ReentrantLock();
        final int processors = Runtime.getRuntime().availableProcessors();
        minThreads *= processors;
        maxThreads *= processors;
        this.name = name;
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.threads = new ArrayList<PooledThread>(maxThreads);
        if (minThreads > maxThreads) {
            final String msg = "minThreads " + minThreads + " must be <= maxThreads " + maxThreads;
            throw new IllegalArgumentException(msg);
        }
        ThreadPool.log.info("Created, thread-pool (" + name + ") min:" + minThreads + " max:" + maxThreads);
        for (int i = 0; maxThreads > 0 && i < minThreads; ++i) {
            this.addThread();
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("thread-pool: [" + this.name);
        sb.append("]\n{\n");
        sb.append("\tmin=" + this.minThreads);
        sb.append(", allocated=" + this.threads.size());
        sb.append(", max=" + this.maxThreads);
        sb.append(", threads:\n");
        this.appendThreads(sb);
        sb.append("}\n");
        return sb.toString();
    }
    
    void submit(final ThreadDispatch dispatch) {
        this.lock.lock();
        Label_0092: {
            try {
                if (this.maxThreads == 0) {
                    dispatch.run();
                }
                else if (dispatch.bind != -1) {
                    this.selectThread(dispatch.bind).submit(dispatch);
                }
                else {
                    if (dispatch.group == null) {
                        this.selectThread().submit(dispatch);
                        break Label_0092;
                    }
                    this.selectThread(dispatch.group).submit(dispatch);
                }
                return;
            }
            finally {
                this.lock.unlock();
            }
        }
        this.lock.unlock();
    }
    
    PooledThread selectThread() {
        this.lock.lock();
        try {
            PooledThread selected = null;
            selected = this.threads.get(0);
            for (final PooledThread thread : this.threads) {
                if (thread.queue.size() == 0) {
                    return thread;
                }
                if (thread.queue.size() >= selected.queue.size()) {
                    continue;
                }
                selected = thread;
            }
            if (this.threads.size() < this.maxThreads) {
                selected = this.addThread();
            }
            return selected;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    PooledThread selectThread(final int bind) {
        this.lock.lock();
        try {
            return this.getThread(bind);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    PooledThread selectThread(final Object group) {
        this.lock.lock();
        try {
            for (final PooledThread thread : this.threads) {
                if (thread.containsGroup(group)) {
                    return thread;
                }
            }
            return this.selectThread();
        }
        finally {
            this.lock.unlock();
        }
    }
    
    PooledThread getThread(final int index) {
        this.lock.lock();
        try {
            final int nThreads = this.threads.size();
            final int needed = index + 1;
            if (nThreads < needed) {
                for (int i = nThreads; i < needed; ++i) {
                    this.addThread();
                }
            }
            return this.threads.get(index);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    PooledThread addThread() {
        this.lock.lock();
        try {
            final String tn = String.valueOf(this.name) + ":" + this.threads.size();
            final PooledThread newThread = new PooledThread(tn);
            this.threads.add(newThread);
            newThread.start();
            ThreadPool.log.debug("Thread (" + tn + ") - created.");
            return newThread;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private List<PooledThread> copyOfThreads() {
        final List<PooledThread> result = new ArrayList<PooledThread>();
        this.lock.lock();
        try {
            result.addAll(this.threads);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
        return result;
    }
    
    private void appendThreads(final StringBuilder sb) {
        if (this.toStringStack.contains(this)) {
            sb.append("\t<recursion-detected>\n");
            return;
        }
        this.toStringStack.push(this);
        for (final PooledThread t : this.copyOfThreads()) {
            sb.append(t);
        }
        this.toStringStack.pop();
    }
}
