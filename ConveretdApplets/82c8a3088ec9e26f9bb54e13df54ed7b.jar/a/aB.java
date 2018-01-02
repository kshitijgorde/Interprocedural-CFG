// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aB extends Thread
{
    private final bN q;
    
    aB(final bN q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (bN.q(this.q).e()) {
            this.q.q();
        }
    }
}
