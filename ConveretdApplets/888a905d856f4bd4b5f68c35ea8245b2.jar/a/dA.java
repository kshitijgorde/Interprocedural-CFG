// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class dA extends Thread
{
    private final cc q;
    
    dA(final cc q) {
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
