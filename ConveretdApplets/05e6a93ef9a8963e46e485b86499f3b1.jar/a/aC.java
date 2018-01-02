// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aC extends Thread
{
    private final bO q;
    
    aC(final bO q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (bO.q(this.q).e()) {
            this.q.q();
        }
    }
}
