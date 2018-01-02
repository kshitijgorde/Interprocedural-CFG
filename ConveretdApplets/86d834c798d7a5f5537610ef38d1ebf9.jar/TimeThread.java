// 
// Decompiled by Procyon v0.5.30
// 

class TimeThread extends Thread
{
    TimeThreadable controller;
    boolean destroyed;
    int delay;
    
    public TimeThread(final TimeThreadable controller, final int delay) {
        this.destroyed = false;
        this.controller = controller;
        this.delay = delay;
    }
    
    public void run() {
        while (!this.destroyed) {
            this.controller.updateTime();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stopThread() {
        this.destroyed = true;
    }
}
