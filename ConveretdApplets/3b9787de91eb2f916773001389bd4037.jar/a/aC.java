// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class aC extends Thread
{
    private final aA q;
    
    aC(final aA q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (aA.q(this.q).t()) {
            this.q.q();
        }
    }
}
