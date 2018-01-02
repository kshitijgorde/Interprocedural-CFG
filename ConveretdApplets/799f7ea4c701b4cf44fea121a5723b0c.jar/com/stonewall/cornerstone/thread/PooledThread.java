// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.thread;

import java.util.Iterator;
import java.util.HashSet;
import org.xmodel.log.Log;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

class PooledThread extends Thread
{
    private ReentrantLock lock;
    long queuePriority;
    final Set<Object> groups;
    RunQueue<ThreadDispatch> queue;
    static final Log log;
    
    static {
        log = Log.getLog(PooledThread.class);
    }
    
    PooledThread(final String name) {
        super(name);
        this.lock = new ReentrantLock();
        this.queuePriority = 0L;
        this.groups = new HashSet<Object>();
        this.queue = new RunQueue<ThreadDispatch>();
    }
    
    void submit(final ThreadDispatch dispatch) {
        if (dispatch.group != null) {
            this.groups.add(dispatch.group);
        }
        dispatch.queuePriority = this.nextQPriority();
        this.queue.add((ThreadDispatch)dispatch.clone());
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                while (true) {
                    final ThreadDispatch dispatch = this.queue.blockingPeek();
                    if (dispatch == null) {
                        continue;
                    }
                    try {
                        dispatch.run();
                    }
                    finally {
                        this.queue.remove();
                        this.removeGroup(dispatch.group);
                    }
                    this.queue.remove();
                    this.removeGroup(dispatch.group);
                }
            }
            catch (Exception e) {
                PooledThread.log.error(this, e);
                continue;
            }
            break;
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\tthread: [");
        sb.append(this.getName());
        sb.append("] pty=" + this.getPriority());
        sb.append(", qlen=");
        sb.append(this.queue.size());
        sb.append('\n');
        this.appendQueue(sb);
        return sb.toString();
    }
    
    void addGroup(final Object group) {
        if (group == null) {
            return;
        }
        this.lock.lock();
        try {
            this.groups.add(group);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    void removeGroup(final Object group) {
        if (group == null) {
            return;
        }
        this.lock.lock();
        try {
            this.groups.remove(group);
        }
        finally {
            this.lock.unlock();
        }
        this.lock.unlock();
    }
    
    boolean containsGroup(final Object group) {
        if (group == null) {
            return false;
        }
        this.lock.lock();
        try {
            return this.groups.contains(group);
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private long nextQPriority() {
        this.lock.lock();
        try {
            if (this.queuePriority == Long.MAX_VALUE) {
                this.queuePriority = 0L;
            }
            return this.queuePriority++;
        }
        finally {
            this.lock.unlock();
        }
    }
    
    private void appendQueue(final StringBuilder sb) {
        for (final ThreadDispatch d : this.queue) {
            sb.append("\t\t");
            sb.append(d);
            sb.append('\n');
        }
    }
}
