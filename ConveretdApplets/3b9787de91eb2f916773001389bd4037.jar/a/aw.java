// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aw extends Thread
{
    private final av q;
    
    aw(final av q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (av.q(this.q).t()) {
            av.q(this.q).q();
        }
    }
}
