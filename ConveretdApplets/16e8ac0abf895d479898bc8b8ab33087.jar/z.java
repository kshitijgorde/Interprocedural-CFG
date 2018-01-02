// 
// Decompiled by Procyon v0.5.30
// 

public final class z extends Thread
{
    public x a;
    
    public z(final x a) {
        this.a = a;
    }
    
    public final void run() {
        while (true) {
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
            this.a.c();
        }
    }
}
