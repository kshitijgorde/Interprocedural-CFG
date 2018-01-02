// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bg extends Thread
{
    private final cW q;
    
    bg(final cW q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (cW.q(this.q).e()) {
            this.q.q();
        }
    }
}
