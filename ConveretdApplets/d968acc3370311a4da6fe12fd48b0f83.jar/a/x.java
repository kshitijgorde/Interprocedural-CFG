// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class x extends Thread
{
    private final w q;
    
    x(final w q) {
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
