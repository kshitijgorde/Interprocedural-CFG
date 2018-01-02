// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bh extends Thread
{
    private final aF q;
    
    bh(final aF q) {
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
