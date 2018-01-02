// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.Vector;

public class PooledSequentialDispatcherAWT implements Runnable
{
    private static ConcurrentDispatcherAWT threadPool;
    private volatile int state;
    private static final int STOPPED = 0;
    private static final int STARTING = 1;
    private static final int RUNNING = 2;
    private static final int STOPPING = 3;
    private final Vector queue;
    
    public PooledSequentialDispatcherAWT() {
        this.state = 0;
        this.queue = new Vector();
    }
    
    public static void dispatchConcurrently(final Runnable runnable) {
        PooledSequentialDispatcherAWT.threadPool.dispatch(runnable);
    }
    
    public void dispatch(final Runnable runnable) {
        this.dispatch(runnable, PooledSequentialDispatcherAWT.threadPool);
    }
    
    public void dispatch(final Runnable runnable, final ConcurrentDispatcherAWT concurrentDispatcherAWT) {
        synchronized (this.queue) {
            this.queue.addElement(runnable);
            if (this.state == 0) {
                this.state = 1;
                concurrentDispatcherAWT.dispatch(this);
            }
        }
    }
    
    public void reassign() {
        synchronized (this.queue) {
            this.stop();
            if (!this.queue.isEmpty()) {
                this.state = 1;
                PooledSequentialDispatcherAWT.threadPool.dispatch(this);
            }
        }
    }
    
    public void stop() {
        synchronized (this.queue) {
            if (this.state == 2) {
                this.state = 3;
                while (this.state != 0) {
                    try {
                        this.queue.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            else {
                this.state = 0;
            }
        }
    }
    
    public void run() {
        synchronized (this.queue) {
            if (this.state != 1) {
                return;
            }
            this.state = 2;
        }
        while (true) {
            final Runnable element;
            synchronized (this.queue) {
                if (this.queue.isEmpty() || this.state != 2) {
                    this.state = 0;
                    this.queue.notifyAll();
                    break;
                }
                element = this.queue.elementAt(0);
                this.queue.removeElementAt(0);
            }
            try {
                element.run();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    
    static {
        PooledSequentialDispatcherAWT.threadPool = new ConcurrentDispatcherAWT();
    }
}
