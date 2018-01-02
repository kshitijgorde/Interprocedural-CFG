// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bR extends Thread
{
    private final bQ q;
    
    bR(final bQ q) {
        this.q = q;
    }
    
    public final void run() {
        final b b;
        (b = new b(this.q.q.q, eb.q("Confirmation"), new String[] { eb.q("OK"), eb.q("Cancel") }, new String[] { eb.q("To apply new settings you need to relogin. Relogin now?") }, null, this.q.q)).setVisible(true);
        if (eb.q("OK").equals(b.q())) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
            this.q.q.w();
        }
    }
}
