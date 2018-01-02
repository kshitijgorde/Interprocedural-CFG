// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bD extends Thread
{
    private final bC q;
    
    bD(final bC q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        this.q.q.q();
    }
}
