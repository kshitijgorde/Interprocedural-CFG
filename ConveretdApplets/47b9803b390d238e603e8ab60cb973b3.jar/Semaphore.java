// 
// Decompiled by Procyon v0.5.30
// 

class Semaphore
{
    private boolean taken;
    
    Semaphore() {
        this.taken = false;
    }
    
    Semaphore(final boolean taken) {
        this.taken = taken;
    }
    
    public synchronized void take() {
        while (this.taken) {
            try {
                this.wait();
            }
            catch (Exception ex) {}
        }
        this.taken = true;
    }
    
    public synchronized boolean peek() {
        return this.taken;
    }
    
    public synchronized void give() {
        this.taken = false;
        this.notify();
    }
}
