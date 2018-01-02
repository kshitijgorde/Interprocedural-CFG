// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class o extends Thread
{
    private final Q q;
    
    o(final Q q) {
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
