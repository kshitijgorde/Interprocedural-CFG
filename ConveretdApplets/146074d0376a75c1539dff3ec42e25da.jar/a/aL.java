// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aL extends Thread
{
    private final aK q;
    
    aL(final aK q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (aK.q(this.q).t()) {
            aK.q(this.q).q();
        }
    }
}
