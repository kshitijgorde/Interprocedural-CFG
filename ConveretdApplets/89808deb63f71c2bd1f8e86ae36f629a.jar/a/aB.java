// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aB extends Thread
{
    private final bP q;
    
    aB(final bP q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (bP.q(this.q).e()) {
            this.q.q();
        }
    }
}
