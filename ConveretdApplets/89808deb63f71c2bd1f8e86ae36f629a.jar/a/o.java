// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class o extends Thread
{
    private final P q;
    
    o(final P q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(3000L);
        }
        catch (InterruptedException ex) {}
        if (this.q.q.e()) {
            this.q.q.q();
        }
    }
}
