// 
// Decompiled by Procyon v0.5.30
// 

package a;

final class bD extends Thread
{
    private final bC q;
    
    bD(final bC q) {
        this.q = q;
    }
    
    public final void run() {
        final String e = cf.w.e();
        if (bC.q(this.q) == null) {
            bC.q(this.q, dI.q(e, "italic", "italicIcon.GIF", bC.q(this.q)));
        }
        if (bC.w(this.q) == null) {
            bC.w(this.q, dI.q(e, "bold", "boldIcon.GIF", bC.q(this.q)));
        }
    }
}
