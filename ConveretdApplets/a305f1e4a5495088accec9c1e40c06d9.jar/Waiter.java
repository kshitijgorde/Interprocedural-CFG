// 
// Decompiled by Procyon v0.5.30
// 

class Waiter
{
    int time;
    int delay;
    
    int set() {
        final int n = (int)System.currentTimeMillis();
        this.time = n + this.delay;
        return n;
    }
    
    public Waiter(final int delay) {
        this.delay = delay;
        this.set();
    }
    
    public void go() {
        final int n = this.time - this.set();
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (Exception ex) {}
        }
    }
}
