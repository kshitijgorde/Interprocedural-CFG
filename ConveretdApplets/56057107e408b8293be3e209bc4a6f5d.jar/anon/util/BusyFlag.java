// 
// Decompiled by Procyon v0.5.30
// 

package anon.util;

public final class BusyFlag
{
    protected Thread busyflag;
    protected int busycount;
    
    public BusyFlag() {
        this.busyflag = null;
        this.busycount = 0;
    }
    
    public void getBusyFlag() {
        while (!this.tryGetBusyFlag()) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
        }
    }
    
    public synchronized boolean tryGetBusyFlag() {
        if (this.busyflag == null) {
            this.busyflag = Thread.currentThread();
            this.busycount = 1;
            return true;
        }
        if (this.busyflag == Thread.currentThread()) {
            ++this.busycount;
            return true;
        }
        return false;
    }
    
    public synchronized void freeBusyFlag() {
        if (this.getBusyFlagOwner() == Thread.currentThread()) {
            --this.busycount;
            if (this.busycount == 0) {
                this.busyflag = null;
            }
        }
    }
    
    public synchronized Thread getBusyFlagOwner() {
        return this.busyflag;
    }
}
