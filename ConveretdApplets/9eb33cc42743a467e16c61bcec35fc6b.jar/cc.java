// 
// Decompiled by Procyon v0.5.30
// 

public final class cc implements Runnable
{
    public final cd cv;
    
    public final void run() {
        this.cv.mv.setPriority(this.cv.mv.getPriority() - 1);
        while (true) {
            try {
                Thread.sleep(10000L);
            }
            catch (InterruptedException ex) {}
            this.cv.c3();
        }
    }
    
    public cc(final cd cv) {
        this.cv = cv;
    }
}
