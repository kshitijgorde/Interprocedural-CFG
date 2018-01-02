// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aR extends Thread
{
    private final aP q;
    
    aR(final aP q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (aP.q(this.q).t()) {
            this.q.q();
        }
    }
}
