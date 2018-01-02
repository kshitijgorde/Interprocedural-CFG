// 
// Decompiled by Procyon v0.5.30
// 

package EDU.oswego.cs.dl.util.concurrent;

public class FJTaskRunnerGroup implements Executor
{
    protected final FJTaskRunner[] threads;
    protected final LinkedQueue entryQueue;
    protected int activeCount;
    protected int nstarted;
    static final boolean COLLECT_STATS = true;
    long initTime;
    int entries;
    static final int DEFAULT_SCAN_PRIORITY = 2;
    static final long SCANS_PER_SLEEP = 15L;
    static final long MAX_SLEEP_TIME = 100L;
    
    public FJTaskRunnerGroup(final int n) {
        this.entryQueue = new LinkedQueue();
        this.activeCount = 0;
        this.nstarted = 0;
        this.initTime = 0L;
        this.entries = 0;
        this.threads = new FJTaskRunner[n];
        this.initializeThreads();
        this.initTime = System.currentTimeMillis();
    }
    
    protected synchronized void checkActive(final FJTaskRunner inactive, final long n) {
        this.setInactive(inactive);
        try {
            if (this.activeCount == 0 && this.entryQueue.peek() == null) {
                this.wait();
            }
            else {
                long n2 = n / 15L;
                if (n2 > 100L) {
                    n2 = 100L;
                }
                this.wait(n2, (n2 == 0L) ? 1 : 0);
            }
        }
        catch (InterruptedException ex) {
            this.notify();
            Thread.currentThread().interrupt();
        }
    }
    
    public void execute(final Runnable runnable) throws InterruptedException {
        if (runnable instanceof FJTask) {
            this.entryQueue.put(runnable);
        }
        else {
            this.entryQueue.put(new FJTask.Wrap(runnable));
        }
        this.signalNewTask();
    }
    
    public void executeTask(final FJTask fjTask) {
        try {
            this.entryQueue.put(fjTask);
            this.signalNewTask();
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
    protected synchronized boolean getActive(final FJTaskRunner fjTaskRunner) {
        return fjTaskRunner.active;
    }
    
    public synchronized int getActiveCount() {
        return this.activeCount;
    }
    
    protected FJTaskRunner[] getArray() {
        return this.threads;
    }
    
    protected void initializeThreads() {
        for (int i = 0; i < this.threads.length; ++i) {
            this.threads[i] = new FJTaskRunner(this);
        }
    }
    
    public void interruptAll() {
        final Thread currentThread = Thread.currentThread();
        boolean b = false;
        for (int i = 0; i < this.threads.length; ++i) {
            final FJTaskRunner fjTaskRunner = this.threads[i];
            if (fjTaskRunner == currentThread) {
                b = true;
            }
            else {
                fjTaskRunner.interrupt();
            }
        }
        if (b) {
            currentThread.interrupt();
        }
    }
    
    public void invoke(final Runnable runnable) throws InterruptedException {
        final InvokableFJTask invokableFJTask = new InvokableFJTask(runnable);
        this.entryQueue.put(invokableFJTask);
        this.signalNewTask();
        invokableFJTask.awaitTermination();
    }
    
    protected FJTask pollEntryQueue() {
        try {
            return (FJTask)this.entryQueue.poll(0L);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
    
    protected synchronized void setActive(final FJTaskRunner fjTaskRunner) {
        if (!fjTaskRunner.active) {
            fjTaskRunner.active = true;
            ++this.activeCount;
            if (this.nstarted < this.threads.length) {
                this.threads[this.nstarted++].start();
            }
            else {
                this.notifyAll();
            }
        }
    }
    
    protected synchronized void setInactive(final FJTaskRunner fjTaskRunner) {
        if (fjTaskRunner.active) {
            fjTaskRunner.active = false;
            --this.activeCount;
        }
    }
    
    public synchronized void setRunPriorities(final int n) {
        for (int i = 0; i < this.threads.length; ++i) {
            final FJTaskRunner fjTaskRunner = this.threads[i];
            fjTaskRunner.setRunPriority(n);
            if (fjTaskRunner.active) {
                fjTaskRunner.setPriority(n);
            }
        }
    }
    
    public synchronized void setScanPriorities(final int n) {
        for (int i = 0; i < this.threads.length; ++i) {
            final FJTaskRunner fjTaskRunner = this.threads[i];
            fjTaskRunner.setScanPriority(n);
            if (!fjTaskRunner.active) {
                fjTaskRunner.setPriority(n);
            }
        }
    }
    
    protected synchronized void signalNewTask() {
        ++this.entries;
        if (this.nstarted < this.threads.length) {
            this.threads[this.nstarted++].start();
        }
        else {
            this.notify();
        }
    }
    
    public int size() {
        return this.threads.length;
    }
    
    public void stats() {
        final double n = (System.currentTimeMillis() - this.initTime) / 1000.0;
        long n2 = 0L;
        long n3 = 0L;
        long n4 = 0L;
        System.out.print("Thread\tQ Cap\tScans\tNew\tRuns\n");
        for (int i = 0; i < this.threads.length; ++i) {
            final FJTaskRunner fjTaskRunner = this.threads[i];
            final int runs = fjTaskRunner.runs;
            n2 += runs;
            final int scans = fjTaskRunner.scans;
            n3 += scans;
            final int steals = fjTaskRunner.steals;
            n4 += steals;
            System.out.print("T" + i + (this.getActive(fjTaskRunner) ? "*" : " ") + "\t" + fjTaskRunner.deqSize() + "\t" + scans + "\t" + steals + "\t" + runs + "\n");
        }
        System.out.print("Total\t    \t" + n3 + "\t" + n4 + "\t" + n2 + "\n");
        System.out.print("Execute: " + this.entries);
        System.out.print("\tTime: " + n);
        long round = 0L;
        if (n != 0.0) {
            round = Math.round(n2 / n);
        }
        System.out.println("\tRate: " + round);
    }
    
    protected static final class InvokableFJTask extends FJTask
    {
        protected final Runnable wrapped;
        protected boolean terminated;
        
        protected InvokableFJTask(final Runnable wrapped) {
            this.terminated = false;
            this.wrapped = wrapped;
        }
        
        protected synchronized void awaitTermination() throws InterruptedException {
            while (!this.terminated) {
                this.wait();
            }
        }
        
        public void run() {
            try {
                if (this.wrapped instanceof FJTask) {
                    FJTask.invoke((FJTask)this.wrapped);
                }
                else {
                    this.wrapped.run();
                }
            }
            finally {
                this.setTerminated();
            }
        }
        
        protected synchronized void setTerminated() {
            this.terminated = true;
            this.notifyAll();
        }
    }
}
