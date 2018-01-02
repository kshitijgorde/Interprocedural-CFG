// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class cd extends Thread
{
    private final bk q;
    
    cd(final bk q) {
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
