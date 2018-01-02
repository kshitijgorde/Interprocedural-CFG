// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class cQ extends Thread
{
    private final cP q;
    
    cQ(final cP q) {
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
