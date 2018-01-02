// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.Vector;

public class ConcurrentDispatcherAWT implements Runnable
{
    private int priority;
    private final Vector queue;
    private int threadCount;
    private int threadMax;
    public static int ENQUEUE_WHEN_BLOCKED;
    public static int RUN_WHEN_BLOCKED;
    private int blockingPolicy;
    
    public ConcurrentDispatcherAWT() {
        this(5, 5);
    }
    
    public ConcurrentDispatcherAWT(final int priority, final int threadMax) {
        this.queue = new Vector();
        this.blockingPolicy = ConcurrentDispatcherAWT.ENQUEUE_WHEN_BLOCKED;
        this.priority = priority;
        this.threadMax = threadMax;
    }
    
    public void setMaxThreads(final int threadMax) {
        this.threadMax = threadMax;
    }
    
    public void dispatch(final Runnable runnable) {
        synchronized (this.queue) {
            if (this.threadCount < this.threadMax) {
                this.queue.addElement(runnable);
                final Thread thread = new Thread(this, this + " Processor");
                ++this.threadCount;
                try {
                    thread.setDaemon(false);
                }
                catch (SecurityException ex) {}
                try {
                    thread.setPriority(this.priority);
                }
                catch (SecurityException ex2) {}
                thread.start();
                return;
            }
            if (this.blockingPolicy == ConcurrentDispatcherAWT.ENQUEUE_WHEN_BLOCKED) {
                this.queue.addElement(runnable);
                return;
            }
        }
        runnable.run();
    }
    
    public void run() {
        while (true) {
            final Runnable element;
            synchronized (this.queue) {
                if (this.queue.isEmpty()) {
                    --this.threadCount;
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
        ConcurrentDispatcherAWT.ENQUEUE_WHEN_BLOCKED = 0;
        ConcurrentDispatcherAWT.RUN_WHEN_BLOCKED = 1;
    }
}
