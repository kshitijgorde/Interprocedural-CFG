// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bg extends Thread
{
    private final aG q;
    
    bg(final aG q) {
        this.q = q;
    }
    
    public final void run() {
        try {
            Thread.sleep(200L);
        }
        catch (Exception ex) {}
        this.q.q.q();
    }
}
