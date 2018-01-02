// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class ck extends Thread
{
    private final bg q;
    
    ck(final bg q) {
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
