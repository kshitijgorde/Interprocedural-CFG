// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

import java.util.Vector;

public final class ThreadPool
{
    private Vector objects;
    private int nObjects;
    private int nMaxThreads;
    private CondVar cvAvailable;
    private CondVar cvEmpty;
    private BusyFlag cvFlag;
    private ThreadPoolThread[] poolThreads;
    private boolean terminated;
    
    public ThreadPool(final String s, final int n) {
        this(s, n, 5);
    }
    
    public ThreadPool(String s, final int nMaxThreads, final int priority) {
        this.nObjects = 0;
        this.nMaxThreads = 0;
        this.terminated = false;
        this.cvFlag = new BusyFlag();
        this.cvAvailable = new CondVar(this.cvFlag);
        this.cvEmpty = new CondVar(this.cvFlag);
        this.objects = new Vector();
        this.nMaxThreads = nMaxThreads;
        this.poolThreads = new ThreadPoolThread[nMaxThreads];
        if (s == null) {
            s = "";
        }
        for (int i = 0; i < nMaxThreads; ++i) {
            (this.poolThreads[i] = new ThreadPoolThread(this, i, s)).setPriority(priority);
            this.poolThreads[i].setDaemon(true);
            this.poolThreads[i].start();
        }
    }
    
    public void shutdown() {
        for (int i = 0; i < this.poolThreads.length; ++i) {
            this.poolThreads[i].shutdown();
        }
    }
    
    private void add(final Runnable runnable, final Object o) {
        try {
            this.cvFlag.getBusyFlag();
            if (this.terminated) {
                throw new IllegalStateException("Thread pool has shutdown");
            }
            this.objects.addElement(new ThreadPoolRequest(runnable, o));
            ++this.nObjects;
            this.cvAvailable.cvSignal();
            while (this.nObjects > this.nMaxThreads) {
                try {
                    this.cvEmpty.cvWait();
                }
                catch (InterruptedException ex) {}
            }
        }
        finally {
            this.cvFlag.freeBusyFlag();
        }
    }
    
    public void addRequest(final Runnable runnable) {
        this.add(runnable, null);
    }
    
    public void addRequestAndWait(final Runnable runnable) throws InterruptedException {
        final Object o = new Object();
        synchronized (o) {
            this.add(runnable, o);
            o.wait();
        }
    }
    
    private final class ThreadPoolRequest
    {
        Runnable target;
        Object lock;
        
        ThreadPoolRequest(final Runnable target, final Object lock) {
            this.target = target;
            this.lock = lock;
        }
    }
    
    private final class ThreadPoolThread extends Thread
    {
        ThreadPool parent;
        boolean shouldRun;
        
        ThreadPoolThread(final ThreadPool parent, final int n, final String s) {
            super(s + " - ThreadPoolThread " + n);
            this.shouldRun = true;
            this.parent = parent;
        }
        
        public void shutdown() {
            this.shouldRun = false;
            while (this.isAlive()) {
                this.interrupt();
                Thread.yield();
            }
        }
        
        public void run() {
            ThreadPoolRequest threadPoolRequest = null;
            while (this.shouldRun) {
                try {
                    this.parent.cvFlag.getBusyFlag();
                    while (threadPoolRequest == null && this.shouldRun) {
                        try {
                            threadPoolRequest = this.parent.objects.elementAt(0);
                            this.parent.objects.removeElementAt(0);
                        }
                        catch (ArrayIndexOutOfBoundsException ex) {
                            threadPoolRequest = null;
                        }
                        catch (ClassCastException ex2) {
                            threadPoolRequest = null;
                        }
                        if (threadPoolRequest == null) {
                            try {
                                this.parent.cvAvailable.cvWait();
                            }
                            catch (InterruptedException ex3) {
                                return;
                            }
                        }
                    }
                }
                finally {
                    this.parent.cvFlag.freeBusyFlag();
                }
                if (!this.shouldRun) {
                    return;
                }
                threadPoolRequest.target.run();
                try {
                    this.parent.cvFlag.getBusyFlag();
                    ThreadPool.this.nObjects--;
                    if (ThreadPool.this.nObjects < ThreadPool.this.nMaxThreads) {
                        this.parent.cvEmpty.cvSignal();
                    }
                }
                finally {
                    this.parent.cvFlag.freeBusyFlag();
                }
                if (threadPoolRequest.lock != null) {
                    synchronized (threadPoolRequest.lock) {
                        threadPoolRequest.lock.notify();
                    }
                }
                threadPoolRequest = null;
            }
        }
    }
}
