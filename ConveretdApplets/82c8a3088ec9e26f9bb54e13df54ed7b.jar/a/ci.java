// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class ci extends Thread
{
    private final be q;
    
    ci(final be q) {
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
