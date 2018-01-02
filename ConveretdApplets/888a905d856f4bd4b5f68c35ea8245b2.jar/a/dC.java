// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class dC extends Thread
{
    private final ci q;
    
    dC(final ci q) {
        this.q = q;
    }
    
    public final void run() {
        final String r = bC.w.r();
        if (this.q.w == null) {
            this.q.w = bi.q(r, "italic", "italicIcon.GIF", this.q.q);
        }
        if (this.q.q == null) {
            this.q.q = bi.q(r, "bold", "boldIcon.GIF", this.q.q);
        }
    }
}
