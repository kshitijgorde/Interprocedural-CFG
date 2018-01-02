// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class cp extends Thread
{
    private final dv q;
    
    cp(final dv q) {
        this.q = q;
    }
    
    public final void run() {
        final dd dd;
        (dd = new dd(this.q.q.q, be.w("Confirmation"), new String[] { be.w("OK"), be.w("Cancel") }, new String[] { be.w("To apply new settings you need to relogin. Relogin now?") }, null, this.q.q)).setVisible(true);
        if (be.w("OK").equals(dd.q)) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
            this.q.q.u();
        }
    }
}
