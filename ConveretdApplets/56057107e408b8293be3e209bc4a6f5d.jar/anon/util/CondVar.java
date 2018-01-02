// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public final class CondVar
{
    private BusyFlag SyncVar;
    
    public CondVar() {
        this(new BusyFlag());
    }
    
    public CondVar(final BusyFlag syncVar) {
        this.SyncVar = syncVar;
    }
    
    public void cvWait() throws InterruptedException {
        this.cvTimedWait(this.SyncVar, 0);
    }
    
    public void cvWait(final BusyFlag busyFlag) throws InterruptedException {
        this.cvTimedWait(busyFlag, 0);
    }
    
    public void cvTimedWait(final int n) throws InterruptedException {
        this.cvTimedWait(this.SyncVar, n);
    }
    
    public void cvTimedWait(final BusyFlag busyFlag, final int n) throws InterruptedException {
        int n2 = 0;
        InterruptedException ex = null;
        while (true) {
            Label_0095: {
                synchronized (this) {
                    if (busyFlag.getBusyFlagOwner() != Thread.currentThread()) {
                        throw new IllegalMonitorStateException("current thread not owner");
                    }
                    while (busyFlag.getBusyFlagOwner() == Thread.currentThread()) {
                        ++n2;
                        busyFlag.freeBusyFlag();
                    }
                    try {
                        if (n == 0) {
                            this.wait();
                        }
                        else {
                            this.wait(n);
                        }
                    }
                    catch (InterruptedException ex2) {
                        ex = ex2;
                    }
                    break Label_0095;
                }
                busyFlag.getBusyFlag();
                --n2;
            }
            if (n2 > 0) {
                continue;
            }
            break;
        }
        if (ex != null) {
            throw ex;
        }
    }
    
    public void cvSignal() {
        this.cvSignal(this.SyncVar);
    }
    
    public synchronized void cvSignal(final BusyFlag busyFlag) {
        if (busyFlag.getBusyFlagOwner() != Thread.currentThread()) {
            throw new IllegalMonitorStateException("current thread not owner");
        }
        this.notify();
    }
    
    public void cvBroadcast() {
        this.cvBroadcast(this.SyncVar);
    }
    
    public synchronized void cvBroadcast(final BusyFlag busyFlag) {
        if (busyFlag.getBusyFlagOwner() != Thread.currentThread()) {
            throw new IllegalMonitorStateException("current thread not owner");
        }
        this.notifyAll();
    }
}
